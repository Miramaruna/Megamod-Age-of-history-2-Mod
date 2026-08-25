package age.of.civilizations2.jakowski.lukasz;

import java.util.ArrayList;
import java.util.List;

public class AI_Build_Fort extends AI_Build {
   public List<Integer> lBuildCost = new ArrayList<>();

   public AI_Build_Fort(int nCivID, long nMoney) {
      super(nCivID, nMoney);

      try {
         for (int i = 0; i < BuildingsManager.getFort_MaxLevel(); i++) {
            this.lBuildCost.add(BuildingsManager.getFort_BuildCost(i + 1, CFG.game.getCiv(nCivID).getProvinceID(0)));
            this.lProvincesToBuild.add(new ArrayList<>());
         }

         if (this.getMoney(nCivID) >= this.lBuildCost.get(0).intValue()) {
            for (int var8 = 0; var8 < CFG.game.getCiv(nCivID).getNumOfProvinces(); var8++) {
               if (!CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(var8)).isOccupied()

                  && CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(var8)).getProvinceStability()
                     > CFG.game.getCiv(nCivID).civGameData.civPersonality.BUILD_MIN_STABILITY
                  && CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(var8)).getRevolutionaryRisk()
                     <= CFG.game.getCiv(nCivID).civGameData.civPersonality.BUILD_MAX_REV_RISK
                  && BuildingsManager.canBuildFort(CFG.game.getCiv(nCivID).getProvinceID(var8))
                  && CFG.game.getCiv(nCivID).isInConstruction(CFG.game.getCiv(nCivID).getProvinceID(var8), ConstructionType.FORT) == 0) {
                  try {
                     if (nMoney >= this.lBuildCost.get(CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(var8)).getLevelOfFort()).intValue()) {
                        this.lProvincesToBuild
                           .get(CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(var8)).getLevelOfFort())
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

      if (iBestProvinceID >= 0 && BuildingsManager.constructFort(iBestProvinceID, nCivID)) {
         out = true;
      }

      return out;
   }

   public float getProvinceBuildScore(int nCivID, int nProvinceID) {
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
         * (1.0F - CFG.game.getProvince(nProvinceID).getRevolutionaryRisk());
   }
}
