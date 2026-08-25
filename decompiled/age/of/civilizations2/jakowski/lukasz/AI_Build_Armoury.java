package age.of.civilizations2.jakowski.lukasz;

import java.util.ArrayList;
import java.util.List;

public class AI_Build_Armoury extends AI_Build {
   public List<Integer> lBuildCost = new ArrayList<>();

   public AI_Build_Armoury(int nCivID, long nMoney) {
      super(nCivID, nMoney);

      try {
         for (int i = 0; i < BuildingsManager.getArmoury_MaxLevel(); i++) {
            this.lBuildCost.add(BuildingsManager.getArmoury_BuildCost(i + 1, CFG.game.getCiv(nCivID).getProvinceID(0)));
            this.lProvincesToBuild.add(new ArrayList<>());
         }

         if (nMoney >= this.lBuildCost.get(0).intValue()) {
            for (int var8 = 0; var8 < CFG.game.getCiv(nCivID).getNumOfProvinces(); var8++) {
               if (!CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(var8)).isOccupied()
                  && CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(var8)).getProvinceStability()
                     > CFG.game.getCiv(nCivID).civGameData.civPersonality.BUILD_MIN_STABILITY
                  && CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(var8)).getRevolutionaryRisk()
                     <= CFG.game.getCiv(nCivID).civGameData.civPersonality.BUILD_MAX_REV_RISK
                  && BuildingsManager.canBuildArmoury(CFG.game.getCiv(nCivID).getProvinceID(var8))
                  && CFG.game.getCiv(nCivID).isInConstruction(CFG.game.getCiv(nCivID).getProvinceID(var8), ConstructionType.ARMOURY) == 0) {
                  try {
                     if (nMoney >= this.lBuildCost.get(CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(var8)).getLevelOfArmoury()).intValue()) {
                        this.lProvincesToBuild
                           .get(CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(var8)).getLevelOfArmoury())
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
      return CFG.game.getCiv(nCivID).iNumOf_Libraries;
   }

   @Override
   public boolean build(int nCivID, int iteration, boolean out) {
      int iBestProvinceID = -1;
      float iBestProvinceID_Score = 0.0F;
      int maxRecruitablePopulation = 1;

      for (int i = this.lProvincesToBuild.size() - 1; i >= 0; i--) {
         for (int j = this.lProvincesToBuild.get(i).size() - 1; j >= 0; j--) {
            if (CFG.gameAction.getRecruitableArmy(this.lProvincesToBuild.get(i).get(j), nCivID) > maxRecruitablePopulation) {
               maxRecruitablePopulation = CFG.gameAction.getRecruitableArmy(this.lProvincesToBuild.get(i).get(j), nCivID);
            }
         }
      }

      for (int var10 = this.lProvincesToBuild.size() - 1; var10 >= 0; var10--) {
         for (int jx = this.lProvincesToBuild.get(var10).size() - 1; jx >= 0; jx--) {
            if (iBestProvinceID < 0) {
               iBestProvinceID = this.lProvincesToBuild.get(var10).get(jx);
               iBestProvinceID_Score = this.getProvinceBuildScore(nCivID, iBestProvinceID, maxRecruitablePopulation);
            } else if (this.getProvinceBuildScore(nCivID, this.lProvincesToBuild.get(var10).get(jx), maxRecruitablePopulation) > iBestProvinceID_Score) {
               iBestProvinceID = this.lProvincesToBuild.get(var10).get(jx);
               iBestProvinceID_Score = this.getProvinceBuildScore(nCivID, iBestProvinceID, maxRecruitablePopulation);
            }
         }
      }

      if (iBestProvinceID >= 0 && BuildingsManager.constructArmoury(iBestProvinceID, nCivID)) {
         out = true;
      }

      return out;
   }

   public float getProvinceBuildScore(int nCivID, int nProvinceID, int maxRecruitablePopulation) {
      return CFG.game.getProvince(nProvinceID).getPopulationData().getPopulation()
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
         * (
            1.0F
               - CFG.game.getCiv(nCivID).civGameData.civPersonality.BUILD_ARMOURY_RECRUITABLE_SCORE
               + CFG.game.getCiv(nCivID).civGameData.civPersonality.BUILD_ARMOURY_RECRUITABLE_SCORE
                  * CFG.gameAction.getRecruitableArmy(nProvinceID, nCivID)
                  / maxRecruitablePopulation
         )
         * (1.0F - CFG.game.getProvince(nProvinceID).getRevolutionaryRisk());
   }
}
