package age.of.civilizations2.jakowski.lukasz;

import java.util.ArrayList;
import java.util.List;

public class AI_Build_Workshop extends AI_Build {
   public List<Integer> lBuildCost = new ArrayList<>();

   public AI_Build_Workshop(int nCivID, long nMoney) {
      super(nCivID, nMoney);

      try {
         for (int i = 0; i < BuildingsManager.getWorkshop_MaxLevel(); i++) {
            this.lBuildCost.add(BuildingsManager.getWorkshop_BuildCost(i + 1, CFG.game.getCiv(nCivID).getProvinceID(0)));
            this.lProvincesToBuild.add(new ArrayList<>());
         }

         if (nMoney >= this.lBuildCost.get(0).intValue()) {
            for (int var8 = 0; var8 < CFG.game.getCiv(nCivID).getNumOfProvinces(); var8++) {
               if (!CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(var8)).isOccupied()
                  && CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(var8)).getProvinceStability()
                     > CFG.game.getCiv(nCivID).civGameData.civPersonality.BUILD_MIN_STABILITY
                  && CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(var8)).getRevolutionaryRisk()
                     <= CFG.game.getCiv(nCivID).civGameData.civPersonality.BUILD_MAX_REV_RISK
                  && BuildingsManager.canBuildWorkshop(CFG.game.getCiv(nCivID).getProvinceID(var8))
                  && CFG.game.getCiv(nCivID).isInConstruction(CFG.game.getCiv(nCivID).getProvinceID(var8), ConstructionType.WORKSHOP) == 0) {
                  try {
                     if (nMoney >= this.lBuildCost.get(CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(var8)).getLevelOfWorkshop()).intValue()) {
                        this.lProvincesToBuild
                           .get(CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(var8)).getLevelOfWorkshop())
                           .add(CFG.game.getCiv(nCivID).getProvinceID(var8));
                        this.iProvincesToBuild_NumOfElements++;
                        this.iMaxDangerLevel = Math.max(
                           this.iMaxDangerLevel, CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(var8)).getDangerLevel()
                        );
                     }
                  } catch (IndexOutOfBoundsException var6) {
                  }
               }
            }
         }
      } catch (IndexOutOfBoundsException var7) {
         CFG.exceptionStack(var7);
      }
   }

   @Override
   public int getNumOfAlreadyBuilt(int nCivID) {
      return CFG.game.getCiv(nCivID).iNumOf_Workshops;
   }

   @Override
   public boolean build(int nCivID, int iteration, boolean out) {
      int iBestProvinceID = -1;
      float iBestProvinceID_Score = 0.0F;

      for (int i = this.lProvincesToBuild.size() - 1; i >= 0; i--) {
         for (int j = this.lProvincesToBuild.get(i).size() - 1; j >= 0; j--) {
            if (iBestProvinceID < 0) {
               iBestProvinceID = this.lProvincesToBuild.get(i).get(j);
               iBestProvinceID_Score = this.getProvinceBuildScore(nCivID, iBestProvinceID);
            } else if (this.getProvinceBuildScore(nCivID, this.lProvincesToBuild.get(i).get(j)) > iBestProvinceID_Score) {
               iBestProvinceID = this.lProvincesToBuild.get(i).get(j);
               iBestProvinceID_Score = this.getProvinceBuildScore(nCivID, iBestProvinceID);
            }
         }
      }

      if (iBestProvinceID >= 0 && BuildingsManager.constructWorkshop(iBestProvinceID, nCivID)) {
         out = true;
         if (this.getMoney(nCivID) > this.lBuildCost.get(0).intValue()
            && BuildingsManager.getWorkshop_BuildMovementCost(1) <= CFG.game.getCiv(nCivID).getMovePoints()) {
            int tSize = 0;

            for (int i = this.lProvincesToBuild.size() - 1; i >= 0; i--) {
               for (int jx = this.lProvincesToBuild.get(i).size() - 1; jx >= 0; jx--) {
                  if (this.lProvincesToBuild.get(i).get(jx) == iBestProvinceID) {
                     this.lProvincesToBuild.get(i).remove(jx);
                  } else {
                     tSize++;
                  }
               }
            }

            if (tSize > 0 && iteration < 4) {
               return this.build(nCivID, ++iteration, out);
            }
         }
      }

      return out;
   }

   public float getProvinceBuildScore(int nCivID, int nProvinceID) {
      return AI_Style.assistantEcoPriority(
         nCivID,
         nProvinceID,
         (
            CFG.game.getProvince(nProvinceID).getPopulationData().getPopulation() * CFG.game.getCiv(nCivID).civGameData.civPersonality.BUILD_WORKSHOP_POP_SCORE
               + CFG.game.getProvince(nProvinceID).getEconomy() * CFG.game.getCiv(nCivID).civGameData.civPersonality.BUILD_WORKSHOP_ECO_SCORE
         )
         * (
            1.0F
               - CFG.game.getCiv(nCivID).civGameData.civPersonality.BUILD_STABILITY_SCORE
               + CFG.game.getCiv(nCivID).civGameData.civPersonality.BUILD_STABILITY_SCORE * CFG.game.getProvince(nProvinceID).getProvinceStability()
         )
         * (
            1.0F
               - CFG.game.getCiv(nCivID).civGameData.civPersonality.BUILD_DANGER_SCORE
                  * CFG.game.getProvince(nProvinceID).getDangerLevel()
                  / this.iMaxDangerLevel
         )
         * (1.0F - CFG.game.getProvince(nProvinceID).getRevolutionaryRisk())
      );
   }
}
