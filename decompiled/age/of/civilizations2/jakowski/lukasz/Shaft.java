package age.of.civilizations2.jakowski.lukasz;

public class Shaft {
   private static final float[] SHAFT_BONUS = new float[]{0.0F, 2.0F, 2.5F, 3.0F, 5.0F, 25.0F, 55.0F, 75.0F, 75.0F, 100.0F, 100.0F};
   private static final float[] SHAFT_BUILD_COST = new float[]{
      0.0F, 0.024999999F, 0.049999997F, 0.099999994F, 0.165F, 0.19999999F, 0.25F, 0.5F, 1.0F, 1.2F, 1.5F
   };
   private static final int[] SHAFT_BUILD_MOVEMENT_COST = new int[]{0, 14, 16, 18, 24, 26, 40, 60, 100, 100, 100};
   private static final int[] SHAFT_CONSTRUCTION = new int[]{0, 1, 3, 6, 8, 9, 13, 16, 21, 21, 26};
   private static final String[] SHAFT_NAMES = new String[]{
      "", "Shaft1", "Shaft2", "Shaft3", "Shaft4", "Shaft5", "Shaft6", "Shaft7", "Shaft8", "Shaft9", "Shaft10"
   };
   private static final float[] SHAFT_TECH_LEVEL = new float[]{0.0F, 0.15F, 0.3F, 0.4F, 0.55F, 0.7F, 1.0F, 1.5F, 2.0F, 2.5F, 3.0F};

   protected static boolean buildShaft(int var0, int var1) {
      boolean var2 = false;
      if (!CFG.game.getProvince(var0).getSeaProvince() && CFG.game.getProvince(var0).getLevelOfShaft() < getShaft_MaxLevel()) {
         CFG.game.getProvince(var0).setLevelOfShaft(CFG.game.getProvince(var0).getLevelOfShaft() + 1);
         Save_Civ_GameData var3 = CFG.game.getCiv(var1).civGameData;
         var3.iNumOfBuildingsConstructed++;
         CFG.game.getCiv(var1).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_Bulit_Shaft(var1, var0));
         var2 = true;
      }

      return var2;
   }

   protected static boolean canBuildShaft(int var0) {
      boolean var1 = false;
      boolean var2;
      if (CFG.game.getProvince(var0).getLevelOfShaft() >= getShaft_MaxLevel()) {
         var2 = var1;
      } else {
         var2 = var1;
         if (CFG.game.getCiv(CFG.game.getProvince(var0).getCivID()).getTechnologyLevel()
            >= getShaft_TechLevel(CFG.game.getProvince(var0).getLevelOfShaft() + 1)) {
            var2 = var1;
            if (CFG.game.getCiv(CFG.game.getProvince(var0).getCivID()).getMovePoints()
               >= getShaft_BuildMovementCost(CFG.game.getProvince(var0).getLevelOfShaft() + 1)) {
               var2 = true;
            }
         }
      }

      return var2;
   }

   protected static boolean constructShaft(int var0, int var1) {
      boolean var2 = false;
      if (!CFG.game.getProvince(var0).getSeaProvince()) {
         var2 = false;
         if (CFG.game.getProvince(var0).getLevelOfShaft() < getShaft_MaxLevel()) {
            var2 = false;
            if (CFG.game.getCiv(var1).getTechnologyLevel() >= getShaft_TechLevel(CFG.game.getProvince(var0).getLevelOfShaft() + 1)) {
               var2 = false;
               if (CFG.game.getCiv(var1).getMovePoints() >= getShaft_BuildMovementCost(CFG.game.getProvince(var0).getLevelOfShaft() + 1)) {
                  var2 = false;
                  if (CFG.game.getCiv(var1).getMoney() >= getShaft_BuildCost(CFG.game.getProvince(var0).getLevelOfShaft() + 1, var0)) {
                     CFG.game
                        .getCiv(var1)
                        .setMovePoints(CFG.game.getCiv(var1).getMovePoints() - getShaft_BuildMovementCost(CFG.game.getProvince(var0).getLevelOfShaft() + 1));
                     CFG.game
                        .getCiv(var1)
                        .setMoney(CFG.game.getCiv(var1).getMoney() - getShaft_BuildCost(CFG.game.getProvince(var0).getLevelOfShaft() + 1, var0));
                     CFG.game
                        .getCiv(var1)
                        .addNewConstruction(new Construction_GameData_Shaft(var0, getShaft_Construction(CFG.game.getProvince(var0).getLevelOfShaft() + 1)));
                     var2 = true;
                  }
               }
            }
         }
      }

      return var2;
   }

   protected static boolean destroyShaft(int var0, int var1) {
      boolean var2 = false;
      if (!CFG.game.getProvince(var0).getSeaProvince()) {
         var2 = false;
         if (CFG.game.getProvince(var0).getLevelOfShaft() > 0) {
            var2 = false;
            if (CFG.game.getCiv(var1).getMovePoints() >= 4) {
               CFG.game.getCiv(var1).setMovePoints(CFG.game.getCiv(var1).getMovePoints() - 4);
               CFG.game.getProvince(var0).setLevelOfSupply(0);
               var2 = true;
            }
         }
      }

      return var2;
   }

   protected static float getShaft_Bonus(int var0) {
      float var1;
      try {
         var1 = SHAFT_BONUS[var0];
      } catch (IndexOutOfBoundsException var3) {
         if (!CFG.LOGS) {
            var1 = SHAFT_BONUS[SHAFT_BONUS.length - 1];
         } else {
            CFG.exceptionStack(var3);
            var1 = SHAFT_BONUS[SHAFT_BONUS.length - 1];
         }
      }

      return var1;
   }

   protected static int getShaft_BuildCost(int var0, int var1) {
      byte var2 = 0;
      int var3 = 0;

      for (int var4 = 0; var4 < CFG.game.getCiv(CFG.game.getProvince(var1).getCivID()).getNumOfProvinces(); var4++) {
         if (CFG.game.getProvince(CFG.game.getCiv(CFG.game.getProvince(var1).getCivID()).getProvinceID(var4)).getLevelOfShaft() > 0) {
            var3++;
         }
      }

      float var5;
      float var6;
      try {
         var5 = CFG.game.getGameScenarios().getScenario_StartingPopulation();
         var6 = SHAFT_BUILD_COST[var0];
      } catch (IndexOutOfBoundsException var10) {
         byte var11 = var2;
         if (CFG.LOGS) {
            CFG.exceptionStack(var10);
            var11 = var2;
         }

         return var11;
      }

      float var7 = var3;
      float var8 = CFG.game.getGameScenarios().getScenario_StartingPopulation();
      return (int)(
         ((0.0115F * var7 + var6) * var5 + 0.3F * (1.0F - CFG.game.getProvince(var1).getDevelopmentLevel()) * var8)
            * (CFG.terrainTypesManager.getBuildCost(CFG.game.getProvince(var1).getTerrainTypeID()) + 1.0F)
      );
   }

   protected static int getShaft_BuildMovementCost(int var0) {
      byte var1 = 0;

      try {
         var0 = SHAFT_BUILD_MOVEMENT_COST[var0];
      } catch (IndexOutOfBoundsException var3) {
         var0 = var1;
         if (CFG.LOGS) {
            CFG.exceptionStack(var3);
            var0 = var1;
         }
      }

      return var0;
   }

   protected static int getShaft_Construction(int var0) {
      byte var1 = 0;

      try {
         var0 = SHAFT_CONSTRUCTION[var0];
      } catch (IndexOutOfBoundsException var3) {
         var0 = var1;
         if (CFG.LOGS) {
            CFG.exceptionStack(var3);
            var0 = var1;
         }
      }

      return var0;
   }

   protected static int getShaft_MaxLevel() {
      return SHAFT_NAMES.length - 1;
   }

   protected static String getShaft_Name(int var0) {
      String var1;
      try {
         var1 = SHAFT_NAMES[var0];
      } catch (IndexOutOfBoundsException var2) {
         if (!CFG.LOGS) {
            var1 = SHAFT_NAMES[SHAFT_NAMES.length - 1];
         } else {
            CFG.exceptionStack(var2);
            var1 = SHAFT_NAMES[SHAFT_NAMES.length - 1];
         }
      }

      return var1;
   }

   protected static float getShaft_TechLevel(int var0) {
      float var1 = 0.0F;

      float var2;
      try {
         var2 = SHAFT_TECH_LEVEL[var0];
      } catch (IndexOutOfBoundsException var4) {
         var2 = var1;
         if (CFG.LOGS) {
            CFG.exceptionStack(var4);
            var2 = var1;
         }
      }

      return var2;
   }
}
