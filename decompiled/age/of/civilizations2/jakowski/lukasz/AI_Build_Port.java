package age.of.civilizations2.jakowski.lukasz;

import java.util.ArrayList;
import java.util.List;

public class AI_Build_Port extends AI_Build {
   public List<Integer> lBuildCost = new ArrayList<>();
   public List<Boolean> haveAccessToBasins = new ArrayList<>();

   public AI_Build_Port(int nCivID, long nMoney) {
      super(nCivID, nMoney);

      try {
         for (int i = 0; i < BuildingsManager.getPort_MaxLevel(); i++) {
            this.lBuildCost.add(BuildingsManager.getPort_BuildCost(i + 1, CFG.game.getCiv(nCivID).getProvinceID(0)));
            this.lProvincesToBuild.add(new ArrayList<>());
         }

         if (nMoney >= this.lBuildCost.get(0).intValue()) {
            for (int var8 = 0; var8 < CFG.game.getCiv(nCivID).getNumOfProvinces(); var8++) {
               if (!CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(var8)).isOccupied()
                  && CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(var8)).getProvinceStability()
                     > CFG.game.getCiv(nCivID).civGameData.civPersonality.BUILD_MIN_STABILITY
                  && CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(var8)).getRevolutionaryRisk()
                     <= CFG.game.getCiv(nCivID).civGameData.civPersonality.BUILD_MAX_REV_RISK
                  && BuildingsManager.canBuildPort(CFG.game.getCiv(nCivID).getProvinceID(var8))
                  && CFG.game.getCiv(nCivID).isInConstruction(CFG.game.getCiv(nCivID).getProvinceID(var8), ConstructionType.PORT) == 0) {
                  try {
                     if (nMoney >= this.lBuildCost.get(CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(var8)).getLevelOfPort()).intValue()) {
                        this.lProvincesToBuild
                           .get(CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(var8)).getLevelOfPort())
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

      for (int i = 0; i < CFG.map.iNumOfBasins; i++) {
         this.haveAccessToBasins.add(false);
      }

      for (int var9 = CFG.game.getCiv(nCivID).getSeaAccess_Provinces_Size() - 1; var9 >= 0; var9--) {
         for (int j = 0; j < CFG.game.getProvince(CFG.game.getCiv(nCivID).getSeaAccess_Provinces().get(var9)).getNeighboringSeaProvincesSize(); j++) {
            this.haveAccessToBasins
               .set(
                  CFG.game
                     .getProvince(CFG.game.getProvince(CFG.game.getCiv(nCivID).getSeaAccess_Provinces().get(var9)).getNeighboringSeaProvinces(j))
                     .getBasinID(),
                  true
               );
         }
      }

      for (int var10 = this.lProvincesToBuild.size() - 1; var10 >= 0; var10--) {
         for (int j = this.lProvincesToBuild.get(var10).size() - 1; j >= 0; j--) {
            if (iBestProvinceID < 0) {
               iBestProvinceID = this.lProvincesToBuild.get(var10).get(j);
               iBestProvinceID_Score = this.getProvinceBuildScore(nCivID, iBestProvinceID);
            } else if (this.getProvinceBuildScore(nCivID, this.lProvincesToBuild.get(var10).get(j)) > iBestProvinceID_Score) {
               iBestProvinceID = this.lProvincesToBuild.get(var10).get(j);
               iBestProvinceID_Score = this.getProvinceBuildScore(nCivID, iBestProvinceID);
            }
         }
      }

      if (iBestProvinceID >= 0 && BuildingsManager.constructPort(iBestProvinceID, nCivID)) {
         out = true;
      }

      this.haveAccessToBasins.clear();
      return out;
   }

   public float getProvinceBuildScore(int nCivID, int nProvinceID) {
      return AI_Style.assistantEcoPriority(
         nCivID,
         nProvinceID,
         (
            this.civRegion_HaveBuiltPort(nCivID, nProvinceID)
               ? (float)CFG.game.getProvince(nProvinceID).getPopulationData().getPopulation()
                  / CFG.game.getGameScenarios().getScenario_StartingPopulation()
                  * (
                     1.0F
                        - CFG.game.getCiv(nCivID).civGameData.civPersonality.BUILD_STABILITY_SCORE
                        + CFG.game.getCiv(nCivID).civGameData.civPersonality.BUILD_STABILITY_SCORE
                           * CFG.game.getProvince(nProvinceID).getProvinceStability()
                  )
                  * (
                     1.0F
                        - CFG.game.getCiv(nCivID).civGameData.civPersonality.BUILD_DANGER_SCORE
                           * CFG.game.getProvince(nProvinceID).getDangerLevel()
                           / this.iMaxDangerLevel
                  )
                  * (1.0F - CFG.game.getProvince(nProvinceID).getRevolutionaryRisk())
               : CFG.game.getGameScenarios().getScenario_StartingPopulation()
                  * (1.0F + CFG.game.getProvince(nProvinceID).getGrowthRate_Population() * 10.0F)
         )
            * this.getPortStrategicBonus(nCivID, nProvinceID)
      );
   }

   public float getPortStrategicBonus(int nCivID, int nProvinceID) {
      try {
         Province tProvince = CFG.game.getProvince(nProvinceID);

         for (int i = 0; i < tProvince.getNeighboringProvincesSize(); i++) {
            int tNeighborCivID = CFG.game.getProvince(tProvince.getNeighboringProvinces(i)).getCivID();
            if (tNeighborCivID > 0 && tNeighborCivID != nCivID && CFG.game.getCivsAtWar(nCivID, tNeighborCivID)) {
               return 2.5F;
            }
         }

         if (tProvince.getDangerLevel() > 0) {
            return 1.0F + Math.min(1.0F, (float)tProvince.getDangerLevel() / 3.0F);
         }
      } catch (IndexOutOfBoundsException var4) {
         CFG.exceptionStack(var4);
      } catch (NullPointerException var5) {
         CFG.exceptionStack(var5);
      }

      return 1.0F;
   }

   public boolean civRegion_HaveBuiltPort(int nCivID, int nProvinceID) {
      try {
         return CFG.game
            .getCiv(CFG.game.getProvince(nProvinceID).getCivID())
            .getCivRegion(CFG.game.getProvince(nProvinceID).getCivRegionID())
            .getSeaAccess_HavePort();
      } catch (IndexOutOfBoundsException var4) {
         CFG.exceptionStack(var4);
         return true;
      } catch (NullPointerException var5) {
         CFG.exceptionStack(var5);
         return true;
      }
   }

   public boolean haveAccessToBasinWithoutPort(int nProvinceID) {
      boolean out = false;

      for (int i = 0; i < CFG.game.getProvince(nProvinceID).getNeighboringSeaProvincesSize(); i++) {
         if (!this.haveAccessToBasins.get(CFG.game.getProvince(CFG.game.getProvince(nProvinceID).getNeighboringSeaProvinces(i)).getBasinID())) {
            out = true;
            break;
         }
      }

      return out;
   }
}
