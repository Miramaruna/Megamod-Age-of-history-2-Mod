package age.of.civilizations2.jakowski.lukasz;

class Bonus_Politics {
   private float incomeProductionModifier;
   private float popGrowthModifier;
   private float movementPointsModifier;
   private float defenseBonusModifier;
   private float genocidePowerModifier;
   private float attackBonusModifier;
   private float militaryUpkeepModifier;
   private float researchModifier;
   private float administrationModifier;
   private float incomeTaxationModifier;
   private float economyGrowthModifier;

   public void setModifiers(
      float incomeProductionModifier,
      float popGrowthModifier,
      float movementPointsModifier,
      float defenseBonusModifier,
      float genocidePowerModifier,
      float attackBonusModifier,
      float militaryUpkeepModifier,
      float researchModifier,
      float administrationModifier,
      float incomeTaxationModifier,
      float economyGrowthModifier
   ) {
      this.incomeProductionModifier = incomeProductionModifier;
      this.popGrowthModifier = popGrowthModifier;
      this.movementPointsModifier = movementPointsModifier;
      this.defenseBonusModifier = defenseBonusModifier;
      this.genocidePowerModifier = genocidePowerModifier;
      this.attackBonusModifier = attackBonusModifier;
      this.militaryUpkeepModifier = militaryUpkeepModifier;
      this.researchModifier = researchModifier;
      this.administrationModifier = administrationModifier;
      this.incomeTaxationModifier = incomeTaxationModifier;
      this.economyGrowthModifier = economyGrowthModifier;
   }

   public void applyModifiers(int nCivID) {
      Civilization civ = CFG.game.getCiv(nCivID);
      civ.setModifier_IncomeProduction(civ.getModifier_IncomeProduction() + this.incomeProductionModifier / 100.0F);
      civ.setModifier_PopGrowth(civ.getModifier_PopGrowth() + this.popGrowthModifier / 100.0F);
      civ.setModifier_MovementPoints(civ.getModifier_MovementPoints() + this.movementPointsModifier / 100.0F);
      civ.setModifier_DefenseBonus(civ.getModifier_DefenseBonus() + this.defenseBonusModifier / 100.0F);
      civ.setModifier_GenocidePower(civ.getModifier_GenocidePower() + this.genocidePowerModifier / 100.0F);
      civ.setModifier_AttackBonus(civ.getModifier_AttackBonus() + this.attackBonusModifier / 100.0F);
      civ.setModifier_MilitaryUpkeep(civ.getModifier_MilitaryUpkeep() + this.militaryUpkeepModifier / 100.0F);
      civ.setModifier_Research(civ.getModifier_Research() + this.researchModifier / 100.0F);
      civ.setModifier_Administation(civ.getModifier_Administation() + this.administrationModifier / 100.0F);
      civ.setModifier_IncomeTaxation(civ.getModifier_IncomeTaxation() + this.incomeTaxationModifier / 100.0F);
      civ.setModifier_EconomyGrowth(civ.getModifier_EconomyGrowth() + this.economyGrowthModifier / 100.0F);
   }

   public void removePreviousModifiers(int nCivID) {
      Civilization civ = CFG.game.getCiv(nCivID);
      civ.setModifier_IncomeProduction(civ.getModifier_IncomeProduction() - this.incomeProductionModifier / 100.0F);
      civ.setModifier_PopGrowth(civ.getModifier_PopGrowth() - this.popGrowthModifier / 100.0F);
      civ.setModifier_MovementPoints(civ.getModifier_MovementPoints() - this.movementPointsModifier / 100.0F);
      civ.setModifier_DefenseBonus(civ.getModifier_DefenseBonus() - this.defenseBonusModifier / 100.0F);
      civ.setModifier_GenocidePower(civ.getModifier_GenocidePower() - this.genocidePowerModifier / 100.0F);
      civ.setModifier_AttackBonus(civ.getModifier_AttackBonus() - this.attackBonusModifier / 100.0F);
      civ.setModifier_MilitaryUpkeep(civ.getModifier_MilitaryUpkeep() - this.militaryUpkeepModifier / 100.0F);
      civ.setModifier_Research(civ.getModifier_Research() - this.researchModifier / 100.0F);
      civ.setModifier_Administation(civ.getModifier_Administation() - this.administrationModifier / 100.0F);
      civ.setModifier_IncomeTaxation(civ.getModifier_IncomeTaxation() - this.incomeTaxationModifier / 100.0F);
      civ.setModifier_EconomyGrowth(civ.getModifier_EconomyGrowth() - this.economyGrowthModifier / 100.0F);
   }
}
