package age.of.civilizations2.jakowski.lukasz;

public class CustomBuilding {
   String[] NAMES;
   float[] BUILD_COST;
   int[] BUILD_MOVEMENT_COST;
   int[] DEFENSE_BONUS;
   float[] TECH_LEVEL;
   int[] CONSTRUCTION;
   String[] IMAGE;
   int[] TURN_POPGROWTH;
   int[] TURN_GOLDINCOME;
   int[] TURN_SOLDIERS;
   int[] TURN_MOVEMENTPOINTS;
   int[] TURN_ECONOMY;
   Building_Action[] BUILDING_ACTION;

   protected CustomBuilding(
      String[] Names,
      float[] Build_Cost,
      int[] Build_Movement_Cost,
      int[] Defense_Bonus,
      float[] Tech_Level,
      int[] Construction,
      String[] Image,
      int[] Turn_PopGrowth,
      int[] Turn_GoldIncome,
      int[] Turn_Soldiers,
      int[] Turn_MovementPoints,
      int[] Turn_Economy
   ) {
      this.NAMES = Names;
      this.BUILD_COST = Build_Cost;
      this.BUILD_MOVEMENT_COST = Build_Movement_Cost;
      this.DEFENSE_BONUS = Defense_Bonus;
      this.TECH_LEVEL = Tech_Level;
      this.CONSTRUCTION = Construction;
      this.IMAGE = Image;
      this.TURN_POPGROWTH = Turn_PopGrowth;
      this.TURN_GOLDINCOME = Turn_GoldIncome;
      this.TURN_SOLDIERS = Turn_Soldiers;
      this.TURN_MOVEMENTPOINTS = Turn_MovementPoints;
      this.TURN_ECONOMY = Turn_Economy;
      this.BUILDING_ACTION = new Building_Action[this.NAMES.length];
   }

   protected CustomBuilding() {
      this.NAMES = new String[1];
      this.NAMES[0] = "";
      this.BUILD_COST = new float[1];
      this.BUILD_COST[0] = 0.0F;
      this.BUILD_MOVEMENT_COST = new int[1];
      this.BUILD_MOVEMENT_COST[0] = 0;
      this.DEFENSE_BONUS = new int[1];
      this.DEFENSE_BONUS[0] = 0;
      this.TECH_LEVEL = new float[1];
      this.TECH_LEVEL[0] = 0.0F;
      this.CONSTRUCTION = new int[1];
      this.CONSTRUCTION[0] = 0;
      this.IMAGE = new String[1];
      this.IMAGE[0] = "";
      this.TURN_POPGROWTH = new int[1];
      this.TURN_POPGROWTH[0] = 0;
      this.TURN_GOLDINCOME = new int[1];
      this.TURN_GOLDINCOME[0] = 0;
      this.TURN_SOLDIERS = new int[1];
      this.TURN_SOLDIERS[0] = 0;
      this.TURN_MOVEMENTPOINTS = new int[1];
      this.TURN_MOVEMENTPOINTS[0] = 0;
      this.TURN_ECONOMY = new int[1];
      this.TURN_ECONOMY[0] = 0;
      this.BUILDING_ACTION = new Building_Action[1];
      this.BUILDING_ACTION[0] = new Building_Action();
   }
}
