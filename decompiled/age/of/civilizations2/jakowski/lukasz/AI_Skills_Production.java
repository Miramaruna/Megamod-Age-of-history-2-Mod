package age.of.civilizations2.jakowski.lukasz;

public class AI_Skills_Production extends AI_Skills {
   public AI_Skills_Production(int iPoints, int iPointsMax) {
      super(iPoints, iPointsMax);
   }

   @Override
   public void addPoint_CivID(int nCivID) {
      SkillsManager.add_IncomeProduction(nCivID);
      this.iPoints = CFG.game.getCiv(nCivID).civGameData.skills.POINTS_INCOME_PRODUCTION;
   }

   @Override
   public float getScore_Personality(int nCivID) {
      return CFG.game.getCiv(nCivID).civGameData.civPersonality.TECH_PRODUCTION;
   }
}
