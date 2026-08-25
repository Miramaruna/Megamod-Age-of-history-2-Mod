package age.of.civilizations2.jakowski.lukasz;

class AI_Skills_DefenseMilitary extends AI_Skills {
   protected AI_Skills_DefenseMilitary(int var1, int var2) {
      super(var1, var2);
   }

   @Override
   protected void addPoint_CivID(int var1) {
      SkillsManager.add_DefenseBonus(var1);
      this.iPoints = CFG.game.getCiv(var1).civGameData.skills.POINTS_DEFENSE_BONUS;
   }

   @Override
   protected float getScore_Personality(int var1) {
      return (float)CFG.game.getCiv(var1).civGameData.civPersonality.TECH_DEFENSE_BONUS;
   }
}
