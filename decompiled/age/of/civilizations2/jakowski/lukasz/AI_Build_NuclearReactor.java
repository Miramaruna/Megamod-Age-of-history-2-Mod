package age.of.civilizations2.jakowski.lukasz;

import java.util.ArrayList;
import java.util.List;

class AI_Build_NuclearReactor extends AI_Build {
   private List<Integer> lBuildCost = new ArrayList<>();

   // $VF: Duplicated exception handlers to handle obfuscated exceptions
   protected AI_Build_NuclearReactor(int var1, long var2) {
      super(var1, var2);
      int var4 = 0;

      while (true) {
         label67: {
            try {
               if (var4 < BuildingsManager.getNuclearReactor_MaxLevel()) {
                  this.lBuildCost.add(BuildingsManager.getNuclearReactor_BuildCost(var4 + 1, CFG.game.getCiv(var1).getProvinceID(0)));
                  List var5 = this.lProvincesToBuild;
                  ArrayList var6 = new ArrayList();
                  var5.add(var6);
                  break label67;
               }
            } catch (IndexOutOfBoundsException var11) {
               CFG.exceptionStack(var11);
               break;
            }

            try {
               if (var2 < this.lBuildCost.get(0).intValue()) {
                  break;
               }
            } catch (IndexOutOfBoundsException var10) {
               CFG.exceptionStack(var10);
               break;
            }

            var4 = 0;

            while (true) {
               label47: {
                  int var7;
                  try {
                     if (var4 >= CFG.game.getCiv(var1).getNumOfProvinces()) {
                        return;
                     }

                     if (CFG.game.getProvince(CFG.game.getCiv(var1).getProvinceID(var4)).isOccupied()
                        || !(
                           CFG.game.getProvince(CFG.game.getCiv(var1).getProvinceID(var4)).getProvinceStability()
                              > CFG.game.getCiv(var1).civGameData.civPersonality.BUILD_MIN_STABILITY
                        )
                        || !(
                           CFG.game.getProvince(CFG.game.getCiv(var1).getProvinceID(var4)).getRevolutionaryRisk()
                              <= CFG.game.getCiv(var1).civGameData.civPersonality.BUILD_MAX_REV_RISK
                        )
                        || !BuildingsManager.canBuildNuclearReactor(CFG.game.getCiv(var1).getProvinceID(var4))) {
                        break label47;
                     }

                     var7 = CFG.game.getCiv(var1).isInConstruction(CFG.game.getCiv(var1).getProvinceID(var4), ConstructionType.NUCLEAR_REACTOR);
                  } catch (IndexOutOfBoundsException var9) {
                     CFG.exceptionStack(var9);
                     return;
                  }

                  if (var7 == 0) {
                     try {
                        if (var2 >= this.lBuildCost.get(CFG.game.getProvince(CFG.game.getCiv(var1).getProvinceID(var4)).getLevelOfNuclearReactor()).intValue()) {
                           this.lProvincesToBuild
                              .get(CFG.game.getProvince(CFG.game.getCiv(var1).getProvinceID(var4)).getLevelOfNuclearReactor())
                              .add(CFG.game.getCiv(var1).getProvinceID(var4));
                           this.iProvincesToBuild_NumOfElements++;
                           this.iMaxDangerLevel = Math.max(
                              this.iMaxDangerLevel, CFG.game.getProvince(CFG.game.getCiv(var1).getProvinceID(var4)).getDangerLevel()
                           );
                        }
                     } catch (IndexOutOfBoundsException var8) {
                     }
                  }
               }

               var4++;
            }
         }

         var4++;
      }
   }

   @Override
   protected boolean build(int var1, int var2, boolean var3) {
      int var4 = -1;
      float var5 = 0.0F;
      int var6 = 1;

      for (int var10 = this.lProvincesToBuild.size() - 1; var10 >= 0; var10--) {
         for (int var7 = this.lProvincesToBuild.get(var10).size() - 1; var7 >= 0; var7--) {
            if (CFG.gameAction.getRecruitableArmy(this.lProvincesToBuild.get(var10).get(var7), var1) > var6) {
               var6 = CFG.gameAction.getRecruitableArmy(this.lProvincesToBuild.get(var10).get(var7), var1);
            }
         }
      }

      int var13 = this.lProvincesToBuild.size() - 1;

      for (var2 = var4; var13 >= 0; var13--) {
         var4 = this.lProvincesToBuild.get(var13).size() - 1;

         while (var4 >= 0) {
            float var8;
            if (var2 < 0) {
               var2 = this.lProvincesToBuild.get(var13).get(var4);
               var8 = this.getProvinceBuildScore(var1, var2, var6);
            } else {
               var8 = var5;
               if (this.getProvinceBuildScore(var1, this.lProvincesToBuild.get(var13).get(var4), var6) > var5) {
                  var2 = this.lProvincesToBuild.get(var13).get(var4);
                  var8 = this.getProvinceBuildScore(var1, var2, var6);
               }
            }

            var4--;
            var5 = var8;
         }
      }

      boolean var9 = var3;
      if (var2 >= 0) {
         var9 = var3;
         if (BuildingsManager.constructNuclearReactor(var2, var1)) {
            var9 = true;
         }
      }

      return var9;
   }

   @Override
   protected int getNumOfAlreadyBuilt(int var1) {
      return CFG.game.getCiv(var1).iNumOf_NuclearReactors;
   }

   protected float getProvinceBuildScore(int var1, int var2, int var3) {
      return CFG.game.getProvince(var2).getPopulationData().getPopulation()
         * (
            1.0F
               - CFG.game.getCiv(var1).civGameData.civPersonality.BUILD_STABILITY_SCORE
               + CFG.game.getCiv(var1).civGameData.civPersonality.BUILD_STABILITY_SCORE * CFG.game.getProvince(var2).getProvinceStability()
         )
         * (1.0F - CFG.game.getCiv(var1).civGameData.civPersonality.BUILD_DANGER_SCORE * CFG.game.getProvince(var2).getDangerLevel() / this.iMaxDangerLevel)
         * (
            1.0F
               - CFG.game.getCiv(var1).civGameData.civPersonality.BUILD_NUCLEAR_REACTOR_RECRUITABLE_SCORE
               + CFG.game.getCiv(var1).civGameData.civPersonality.BUILD_NUCLEAR_REACTOR_RECRUITABLE_SCORE
                  * CFG.gameAction.getRecruitableArmy(var2, var1)
                  / var3
         )
         * (1.0F - CFG.game.getProvince(var2).getRevolutionaryRisk());
   }
}
