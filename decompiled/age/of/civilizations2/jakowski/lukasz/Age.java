package age.of.civilizations2.jakowski.lukasz;

public class Age {
   public String sName;
   public int iAgeBeginningYear;
   public int iAgeEndYear;
   public float fPopulationGrowthRate;
   public float fEconomyGrowthRate;
   public float FOG_OF_WAR_DISCOVERY_MET_PROVINCES = 1.0F;
   public int BASE_MOVEMENT_POINTS = 20;
   public float DEVELOPMENT_LEVEL_INCREASE = 1.0F;
   public float BASE_INCOME_TAXATION = 0.004654F;
   public float INCOME_TAXATION_MODIFIER = 1.0F;
   public float INCOME_PRODUCTION_MODIFIER = 1.0F;
   public float EXPENSES_ADMINSTRATION_MODIFIER = 1.0F;
   public float EXPENSES_MILITARY_UPKEEP_MODIFIER = 1.0F;
   public float MOVEMENT_POINTS_MODIFIER = 1.0F;
   public int BASE_DIPLOMACY_POINTS = 10;
   public float EXPENSES_ADMINSTRATION_DISTANCE = 3.5F;
   public int DIPLOMACY_ALLIANCE_PROPOSAL_NAGATIVE_DISTANCE = 350;
   public float INCOME_TAXATION_PER_TECHNOLOGY_MODIFIER = 4.1254E-4F;
   public float BASE_MILITARY_UPKEEP = 0.109189F;
   public float GAME_STARTING_DEVELOPMENT = 0.44215F;
   public int GAME_DAYS_PER_TURN = 34;
   public float BASE_INCOME_PRODUCTION = 0.015954F;
   public float INCOME_PRODUCTIONN_PER_DEVELOPMENT_MODIFIER = 0.0015456F;
   public float REVOLUTIONARY_RISK_MODIFIER = 1.0F;
   public float DISEASE_CHANCE = 0.05F;
   public int COLONIZE_COST_MOVEMENT_POINTS = 16;
   public int COLONIZE_COST_DIPLOMACY_POINTS = 14;
   public float COLONIZE_COST_GOLD_PERC = 0.1675F;

   public Age(String sName, int iAgeBeginningYear, int iAgeEndYear, float fPopulationGrowthRate, float fEconomyGrowthRate) {
      this.sName = CFG.langManager.get(sName);
      this.iAgeBeginningYear = iAgeBeginningYear;
      this.iAgeEndYear = iAgeEndYear;
      this.fPopulationGrowthRate = fPopulationGrowthRate;
      this.fEconomyGrowthRate = fEconomyGrowthRate;
   }

   public Age(
      String sName,
      int iAgeBeginningYear,
      int iAgeEndYear,
      float fPopulationGrowthRate,
      float fEconomyGrowthRate,
      float FOG_OF_WAR_DISCOVERY_MET_PROVINCES,
      float DEVELOPMENT_LEVEL_INCREASE,
      float INCOME_TAXATION_MODIFIER,
      float INCOME_PRODUCTION_MODIFIER,
      float EXPENSES_ADMINSTRATION_MODIFIER,
      float EXPENSES_MILITARY_UPKEEP_MODIFIER,
      int BASE_MOVEMENT_POINTS,
      float MOVEMENT_POINTS_MODIFIER,
      int BASE_DIPLOMACY_POINTS,
      float EXPENSES_ADMINSTRATION_DISTANCE,
      int DIPLOMACY_ALLIANCE_PROPOSAL_NAGATIVE_DISTANCE,
      float BASE_INCOME_TAXATION,
      float INCOME_TAXATION_PER_TECHNOLOGY_MODIFIER,
      float BASE_MILITARY_UPKEEP,
      float GAME_STARTING_DEVELOPMENT,
      int GAME_DAYS_PER_TURN,
      float BASE_INCOME_PRODUCTION,
      float INCOME_PRODUCTIONN_PER_DEVELOPMENT_MODIFIER,
      float REVOLUTIONARY_RISK_MODIFIER,
      float DISEASE_CHANCE,
      float COLONIZE_COST_GOLD_PERC,
      int COLONIZE_COST_MOVEMENT_POINTS,
      int COLONIZE_COST_DIPLOMACY_POINTS
   ) {
      this.sName = CFG.langManager.get(sName);
      this.iAgeBeginningYear = iAgeBeginningYear;
      this.iAgeEndYear = iAgeEndYear;
      this.fPopulationGrowthRate = fPopulationGrowthRate;
      this.fEconomyGrowthRate = fEconomyGrowthRate;
      this.FOG_OF_WAR_DISCOVERY_MET_PROVINCES = FOG_OF_WAR_DISCOVERY_MET_PROVINCES;
      this.DEVELOPMENT_LEVEL_INCREASE = DEVELOPMENT_LEVEL_INCREASE;
      this.INCOME_TAXATION_MODIFIER = INCOME_TAXATION_MODIFIER;
      this.INCOME_PRODUCTION_MODIFIER = INCOME_PRODUCTION_MODIFIER;
      this.EXPENSES_ADMINSTRATION_MODIFIER = EXPENSES_ADMINSTRATION_MODIFIER;
      this.EXPENSES_MILITARY_UPKEEP_MODIFIER = EXPENSES_MILITARY_UPKEEP_MODIFIER;
      this.BASE_MOVEMENT_POINTS = BASE_MOVEMENT_POINTS;
      this.MOVEMENT_POINTS_MODIFIER = MOVEMENT_POINTS_MODIFIER;
      this.BASE_DIPLOMACY_POINTS = BASE_DIPLOMACY_POINTS;
      this.EXPENSES_ADMINSTRATION_DISTANCE = EXPENSES_ADMINSTRATION_DISTANCE;
      this.DIPLOMACY_ALLIANCE_PROPOSAL_NAGATIVE_DISTANCE = DIPLOMACY_ALLIANCE_PROPOSAL_NAGATIVE_DISTANCE;
      this.BASE_INCOME_TAXATION = BASE_INCOME_TAXATION;
      this.INCOME_TAXATION_PER_TECHNOLOGY_MODIFIER = INCOME_TAXATION_PER_TECHNOLOGY_MODIFIER;
      this.BASE_MILITARY_UPKEEP = BASE_MILITARY_UPKEEP;
      this.GAME_STARTING_DEVELOPMENT = GAME_STARTING_DEVELOPMENT;
      this.GAME_DAYS_PER_TURN = GAME_DAYS_PER_TURN;
      this.BASE_INCOME_PRODUCTION = BASE_INCOME_PRODUCTION;
      this.INCOME_PRODUCTIONN_PER_DEVELOPMENT_MODIFIER = INCOME_PRODUCTIONN_PER_DEVELOPMENT_MODIFIER;
      this.REVOLUTIONARY_RISK_MODIFIER = REVOLUTIONARY_RISK_MODIFIER;
      this.COLONIZE_COST_GOLD_PERC = COLONIZE_COST_GOLD_PERC;
      this.COLONIZE_COST_MOVEMENT_POINTS = COLONIZE_COST_MOVEMENT_POINTS;
      this.COLONIZE_COST_DIPLOMACY_POINTS = COLONIZE_COST_DIPLOMACY_POINTS;
      this.DISEASE_CHANCE = DISEASE_CHANCE;
   }

   public final String getName() {
      return this.sName;
   }

   public final void setName(String sName) {
      this.sName = CFG.langManager.get(sName);
   }

   public final int getBeginningYear() {
      return this.iAgeBeginningYear;
   }

   public final int getEndYear() {
      return this.iAgeEndYear;
   }

   public final float getPopulationGrowthRate() {
      return this.fPopulationGrowthRate;
   }

   public final void setPopulationGrowthRate(float fPopulationGrowthRate) {
      this.fPopulationGrowthRate = fPopulationGrowthRate;
   }

   public final float getEconomyGrowthRate() {
      return this.fEconomyGrowthRate;
   }

   public final void setEconomyGrowthRate(float fEconomyGrowthRate) {
      this.fEconomyGrowthRate = fEconomyGrowthRate;
   }
}
