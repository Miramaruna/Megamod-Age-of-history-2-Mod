package age.of.civilizations2.jakowski.lukasz;

import java.util.List;

public class CustomBuildingsManager {
   public static final List<CustomBuilding> CustomBuildings = CFG.game.getGame_CustomBuildings().loadCustomBuildings();

   public static final int getBuilding_MaxLevel(int n) {
      return CustomBuildings.get(n).NAMES.length - 1;
   }

   public static final int getBuilding_MaxLevel_CanBuild(int nCivID, int n) {
      for (int i = 0; i < CustomBuildings.get(n).TECH_LEVEL.length; i++) {
         if (CustomBuildings.get(n).TECH_LEVEL[i] > CFG.game.getCiv(nCivID).getTechnologyLevel()) {
            return i - 1;
         }
      }

      return getBuilding_MaxLevel(n);
   }

   public static final String getBuilding_Name(int nLevel, int n) {
      try {
         return CustomBuildings.get(n).NAMES[nLevel];
      } catch (IndexOutOfBoundsException var3) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var3);
         }

         return CustomBuildings.get(n).NAMES[CustomBuildings.get(n).NAMES.length - 1];
      }
   }

   public static final int getBuilding_BuildCost(int nLevel, int nProvinceID, int n) {
      try {
         int iNumOfBuildigns = 0;

         for (int i = 0; i < CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getNumOfProvinces(); i++) {
            if (CFG.game.getProvince(CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getProvinceID(i)).getLevelOfBuilding(n) > 0) {
               iNumOfBuildigns += CFG.game.getProvince(CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getProvinceID(i)).getLevelOfBuilding(n);
            }
         }

         return (int)(
            (
                  CFG.game.getGameScenarios().getScenario_StartingPopulation() * (CustomBuildings.get(n).BUILD_COST[nLevel] + 0.004721F * iNumOfBuildigns)
                     + CFG.game.getGameScenarios().getScenario_StartingPopulation()
                        * 0.0275F
                        * (1.0F - CFG.game.getProvince(nProvinceID).getDevelopmentLevel())
               )
               * (1.0F + CFG.terrainTypesManager.getBuildCost(CFG.game.getProvince(nProvinceID).getTerrainTypeID()))
         );
      } catch (IndexOutOfBoundsException var5) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var5);
         }

         return 0;
      }
   }

   public static final int getBuilding_BuildMovementCost(int nLevel, int n) {
      try {
         return CustomBuildings.get(n).BUILD_MOVEMENT_COST[nLevel];
      } catch (IndexOutOfBoundsException var3) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var3);
         }

         return 0;
      }
   }

   public static final int getBuilding_DefenseBonus(int nLevel, int n) {
      try {
         return CustomBuildings.get(n).DEFENSE_BONUS[nLevel];
      } catch (IndexOutOfBoundsException var3) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var3);
         }

         return 0;
      }
   }

   public static final float getBuilding_TechLevel(int nLevel, int n) {
      try {
         return CustomBuildings.get(n).TECH_LEVEL[nLevel];
      } catch (IndexOutOfBoundsException var3) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var3);
         }

         return 0.0F;
      }
   }

   public static final int getBuilding_Construction(int nLevel, int n) {
      try {
         return CustomBuildings.get(n).CONSTRUCTION[nLevel];
      } catch (IndexOutOfBoundsException var3) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var3);
         }

         return 0;
      }
   }

   public static final boolean canBuildBuilding(int nProvinceID, int n) {
      return CFG.game.getProvince(nProvinceID).getLevelOfBuilding(n) < getBuilding_MaxLevel(n)
         && CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getTechnologyLevel()
            >= getBuilding_TechLevel(CFG.game.getProvince(nProvinceID).getLevelOfBuilding(n) + 1, n)
         && CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getMovePoints()
            >= getBuilding_BuildMovementCost(CFG.game.getProvince(nProvinceID).getLevelOfBuilding(n) + 1, n);
   }

   public static final boolean constructBuilding(int nProvinceID, int nCivID, int n) {
      if (CFG.game.getProvince(nProvinceID).getSeaProvince()
         || CFG.game.getProvince(nProvinceID).getLevelOfBuilding(n) >= getBuilding_MaxLevel(n)
         || !(CFG.game.getCiv(nCivID).getTechnologyLevel() >= getBuilding_TechLevel(CFG.game.getProvince(nProvinceID).getLevelOfBuilding(n) + 1, n))) {
         return false;
      } else if (CFG.game.getCiv(nCivID).getMovePoints() >= getBuilding_BuildMovementCost(CFG.game.getProvince(nProvinceID).getLevelOfBuilding(n) + 1, n)
         && CFG.game.getCiv(nCivID).getMoney() >= getBuilding_BuildCost(CFG.game.getProvince(nProvinceID).getLevelOfBuilding(n) + 1, nProvinceID, n)) {
         CFG.game
            .getCiv(nCivID)
            .setMovePoints(
               CFG.game.getCiv(nCivID).getMovePoints() - getBuilding_BuildMovementCost(CFG.game.getProvince(nProvinceID).getLevelOfBuilding(n) + 1, n)
            );
         CFG.game
            .getCiv(nCivID)
            .setMoney(CFG.game.getCiv(nCivID).getMoney() - getBuilding_BuildCost(CFG.game.getProvince(nProvinceID).getLevelOfBuilding(n) + 1, nProvinceID, n));
         CFG.game
            .getCiv(nCivID)
            .addNewCustomConstruction(
               new Construction_GameData_CustomBuilding(
                  nProvinceID, getBuilding_Construction(CFG.game.getProvince(nProvinceID).getLevelOfBuilding(n) + 1, n), n
               )
            );
         return true;
      } else {
         return false;
      }
   }

   public static final boolean buildBuilding(int nProvinceID, int nCivID, int n) {
      if (!CFG.game.getProvince(nProvinceID).getSeaProvince() && CFG.game.getProvince(nProvinceID).getLevelOfBuilding(n) < getBuilding_MaxLevel(n)) {
         CFG.game.getProvince(nProvinceID).setLevelOfBuilding(CFG.game.getProvince(nProvinceID).getLevelOfBuilding(n) + 1, n);
         CFG.game.getCiv(nCivID).civGameData.iNumOfBuildingsConstructed++;

         for (int i = 0; i < CFG.game.getPlayersSize(); i++) {
            if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getNumOfProvinces() > 0) {
               CFG.game.getProvince(nProvinceID).updateFogOfWar(i);
            }
         }

         CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_Bulit_Fort(nCivID, nProvinceID));
         return true;
      } else {
         return false;
      }
   }

   public static final boolean destroyBuilding(int nProvinceID, int nCivID, int n) {
      if (CFG.game.getProvince(nProvinceID).getSeaProvince() || CFG.game.getProvince(nProvinceID).getLevelOfBuilding(n) <= 0) {
         return false;
      } else if (CFG.game.getCiv(nCivID).getMovePoints() >= 4) {
         CFG.game.getCiv(nCivID).setMovePoints(CFG.game.getCiv(nCivID).getMovePoints() - 4);
         CFG.game.getProvince(nProvinceID).setLevelOfBuilding(0, n);
         return true;
      } else {
         return false;
      }
   }

   public static final List<CustomBuilding> getCustomBuildings() {
      return CustomBuildings;
   }

   public static final Integer getCustomBuildingsAmount() {
      return CustomBuildings.size();
   }

   public static final Integer getBuilding_Turn_GoldIncome(int nLevel, int n) {
      Integer local = CustomBuildings.get(n).TURN_GOLDINCOME[nLevel];
      return local != null ? local : 0;
   }

   public static final Integer getBuilding_Turn_Soldiers(int nLevel, int n) {
      Integer local = CustomBuildings.get(n).TURN_SOLDIERS[nLevel];
      return local != null ? local : 0;
   }

   public static final Integer getBuilding_Turn_Economy(int nLevel, int n) {
      Integer local = CustomBuildings.get(n).TURN_ECONOMY[nLevel];
      return local != null ? local : 0;
   }

   public static final int getBuildings_Image(int nLevel, int n) {
      String local = CustomBuildings.get(n).IMAGE[nLevel];
      return local != "" | local != null ? ImageManager.addImage("UI/icons/" + local) : ImageManager.addImage("UI/icons/fort.png");
   }

   public static final Integer getBuilding_Turn_Money(int nLevel, int n) {
      Integer local = CustomBuildings.get(n).TURN_GOLDINCOME[nLevel];
      return local != null ? local : 0;
   }

   public static final Integer getBuilding_Turn_MovementPoints(int nLevel, int n) {
      Integer local = CustomBuildings.get(n).TURN_MOVEMENTPOINTS[nLevel];
      return local != null ? local : 0;
   }

   public static final Integer getBuilding_Turn_PopGrowth(int nLevel, int n) {
      Integer local = CustomBuildings.get(n).TURN_POPGROWTH[nLevel];
      return local != null ? local : 0;
   }

   public static final Building_Action getBuilding_Action(int nLevel, int n) {
      Building_Action local = CustomBuildings.get(n).BUILDING_ACTION[nLevel];
      return local != null ? local : new Building_Action();
   }

   public static void updateAmount() {
      for (int i = 0; i < CFG.game.getProvincesSize(); i++) {
         CFG.game.getGame_CustomBuildingsManager();
         int[] newCustomBuildings = new int[getCustomBuildingsAmount()];

         for (int i1 = 0; i1 < CFG.game.getProvince(i).saveProvinceData.iCustomBuilding.length; i1++) {
            newCustomBuildings[i1] = CFG.game.getProvince(i).saveProvinceData.iCustomBuilding[i1];
         }

         CFG.game.getProvince(i).saveProvinceData.iCustomBuilding = newCustomBuildings;
      }
   }

   public static void addBuilding(CustomBuilding building) {
      CustomBuildings.add(building);
      updateAmount();
   }
}
