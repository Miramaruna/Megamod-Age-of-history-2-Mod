package age.of.civilizations2.jakowski.lukasz;

public class AI_Skills {
   public int iPoints;
   public int iPointsMax;

   public AI_Skills(int iPoints, int iPointsMax) {
      this.iPoints = iPoints;
      this.iPointsMax = iPointsMax;
   }

   public void addPoint_CivID(int nCivID) {
      SkillsManager.add_PopGrowth(nCivID);
      this.iPoints = CFG.game.getCiv(nCivID).civGameData.skills.POINTS_POP_GROWTH;
   }

   public float getScore_Personality(int nCivID) {
      return CFG.game.getCiv(nCivID).civGameData.civPersonality.TECH_POP;
   }

   public float getScore(int nCivID) {
      return this.getScore_Personality(nCivID) * (1.0F - (float)this.iPoints / this.iPointsMax);
   }
}
