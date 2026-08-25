package age.of.civilizations2.jakowski.lukasz;

public class CustomBuildingBuilder {
   private CustomBuilding building = new CustomBuilding();
   private int activeLevel = 0;

   public static CustomBuildingBuilder create() {
      return new CustomBuildingBuilder();
   }

   public CustomBuilding build() {
      return this.building;
   }

   public CustomBuildingBuilder addLevel() {
      this.activeLevel++;
      this.activeLevel++;
      String[] names = new String[this.activeLevel];
      float[] build_cost = new float[this.activeLevel];
      int[] build_movement_cost = new int[this.activeLevel];
      int[] defense_bonus = new int[this.activeLevel];
      float[] tech_level = new float[this.activeLevel];
      int[] construction = new int[this.activeLevel];
      String[] image = new String[this.activeLevel];
      int[] turn_popGrowth = new int[this.activeLevel];
      int[] turn_goldIncome = new int[this.activeLevel];
      int[] turn_soldiers = new int[this.activeLevel];
      int[] turn_movementPoints = new int[this.activeLevel];
      int[] turn_economy = new int[this.activeLevel];
      Building_Action[] building_action = new Building_Action[this.activeLevel];
      System.arraycopy(this.building.NAMES, 0, names, 0, this.building.NAMES.length);
      System.arraycopy(this.building.BUILD_COST, 0, build_cost, 0, this.building.BUILD_COST.length);
      System.arraycopy(this.building.BUILD_MOVEMENT_COST, 0, build_movement_cost, 0, this.building.BUILD_MOVEMENT_COST.length);
      System.arraycopy(this.building.DEFENSE_BONUS, 0, defense_bonus, 0, this.building.DEFENSE_BONUS.length);
      System.arraycopy(this.building.TECH_LEVEL, 0, tech_level, 0, this.building.TECH_LEVEL.length);
      System.arraycopy(this.building.CONSTRUCTION, 0, construction, 0, this.building.CONSTRUCTION.length);
      System.arraycopy(this.building.IMAGE, 0, image, 0, this.building.IMAGE.length);
      System.arraycopy(this.building.TURN_POPGROWTH, 0, turn_popGrowth, 0, this.building.TURN_POPGROWTH.length);
      System.arraycopy(this.building.TURN_GOLDINCOME, 0, turn_goldIncome, 0, this.building.TURN_GOLDINCOME.length);
      System.arraycopy(this.building.TURN_SOLDIERS, 0, turn_soldiers, 0, this.building.TURN_SOLDIERS.length);
      System.arraycopy(this.building.TURN_MOVEMENTPOINTS, 0, turn_movementPoints, 0, this.building.TURN_MOVEMENTPOINTS.length);
      System.arraycopy(this.building.TURN_ECONOMY, 0, turn_economy, 0, this.building.TURN_ECONOMY.length);
      System.arraycopy(this.building.BUILDING_ACTION, 0, building_action, 0, this.building.BUILDING_ACTION.length);
      this.building.NAMES = names;
      this.building.BUILD_COST = build_cost;
      this.building.BUILD_MOVEMENT_COST = build_movement_cost;
      this.building.DEFENSE_BONUS = defense_bonus;
      this.building.TECH_LEVEL = tech_level;
      this.building.CONSTRUCTION = construction;
      this.building.IMAGE = image;
      this.building.TURN_POPGROWTH = turn_popGrowth;
      this.building.TURN_GOLDINCOME = turn_goldIncome;
      this.building.TURN_SOLDIERS = turn_soldiers;
      this.building.TURN_MOVEMENTPOINTS = turn_movementPoints;
      this.building.TURN_ECONOMY = turn_economy;
      this.building.BUILDING_ACTION = building_action;
      this.activeLevel--;
      return this;
   }

   public CustomBuildingBuilder name(String name) {
      this.building.NAMES[this.activeLevel] = name;
      return this;
   }

   public CustomBuildingBuilder build_cost(float cost) {
      this.building.BUILD_COST[this.activeLevel] = cost;
      return this;
   }

   public CustomBuildingBuilder build_movement_cost(int cost) {
      this.building.BUILD_MOVEMENT_COST[this.activeLevel] = cost;
      return this;
   }

   public CustomBuildingBuilder defense_bonus(int bonus) {
      this.building.DEFENSE_BONUS[this.activeLevel] = bonus;
      return this;
   }

   public CustomBuildingBuilder tech_level(float level) {
      this.building.TECH_LEVEL[this.activeLevel] = level;
      return this;
   }

   public CustomBuildingBuilder construction(int turns) {
      this.building.CONSTRUCTION[this.activeLevel] = turns;
      return this;
   }

   public CustomBuildingBuilder image(String imagePath) {
      this.building.IMAGE[this.activeLevel] = imagePath;
      return this;
   }

   public CustomBuildingBuilder turn_popGrowth(int growth) {
      this.building.TURN_POPGROWTH[this.activeLevel] = growth;
      return this;
   }

   public CustomBuildingBuilder turn_goldIncome(int income) {
      this.building.TURN_GOLDINCOME[this.activeLevel] = income;
      return this;
   }

   public CustomBuildingBuilder turn_soldiers(int soldiers) {
      this.building.TURN_SOLDIERS[this.activeLevel] = soldiers;
      return this;
   }

   public CustomBuildingBuilder turn_movementPoints(int points) {
      this.building.TURN_MOVEMENTPOINTS[this.activeLevel] = points;
      return this;
   }

   public CustomBuildingBuilder turn_economy(int economy) {
      this.building.TURN_ECONOMY[this.activeLevel] = economy;
      return this;
   }

   public CustomBuildingBuilder custom_action(Building_Action buildingAction) {
      this.building.BUILDING_ACTION[this.activeLevel] = buildingAction;
      return this;
   }
}
