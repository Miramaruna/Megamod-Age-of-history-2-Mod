package age.of.civilizations2.jakowski.lukasz;

public class SkillsManager {
   public static final int MAX_POINTS_POP_GROWTH = 25;
   public static final float PER_POINT_POP_GROWTH = 0.75F;
   public static final int MAX_POINTS_ECONOMY_GROWTH = 25;
   public static final float PER_POINT_ECONOMY_GROWTH = 0.75F;
   public static final int MAX_POINTS_INCOME_TAXATION = 25;
   public static final float PER_POINT_INCOME_TAXATION = 0.2F;
   public static final int MAX_POINTS_INCOME_PRODUCTION = 25;
   public static final float PER_POINT_INCOME_PRODUCTION = 0.25F;
   public static final int MAX_POINTS_ADMINISTRATION = 20;
   public static final float PER_POINT_ADMINISTRATION = 0.3F;
   public static final int MAX_POINTS_MILITARY_UPKEEP = 30;
   public static final float PER_POINT_MILITARY_UPKEEP = 0.35F;
   public static final int MAX_POINTS_RESEARCH = 30;
   public static final float PER_POINT_RESEARCH = 0.75F;
   public static final int MAX_POINTS_COLONIZATION = 15;
   public static final float PER_POINT_COLONIZATION = 1.0F;

   SkillsManager() {
   }

   public static final boolean canAdd_PopGrowth(int nCivID) {
      return CFG.game.getCiv(nCivID).civGameData.skills.POINTS_POP_GROWTH < 120;
   }

   public static final void add_PopGrowth(int nCivID) {
      if (CFG.game.getCiv(nCivID).civGameData.skills.getPointsLeft(nCivID) > 0) {
         Save_Civ_GameData var10000 = CFG.game.getCiv(nCivID).civGameData;
         var10000.fModifier_PopGrowth = var10000.fModifier_PopGrowth - CFG.game.getCiv(nCivID).civGameData.skills.POINTS_POP_GROWTH * 1.2F / 100.0F;
         CFG.game.getCiv(nCivID).civGameData.skills.POINTS_POP_GROWTH = Math.min(CFG.game.getCiv(nCivID).civGameData.skills.POINTS_POP_GROWTH + 1, 125);
         var10000 = CFG.game.getCiv(nCivID).civGameData;
         var10000.fModifier_PopGrowth = var10000.fModifier_PopGrowth + CFG.game.getCiv(nCivID).civGameData.skills.POINTS_POP_GROWTH * 1.2F / 100.0F;
      }
   }

   public static final boolean canAdd_EcoGrowth(int nCivID) {
      return CFG.game.getCiv(nCivID).civGameData.skills.POINTS_ECONOMY_GROWTH < 125;
   }

   public static final void add_EcoGrowth(int nCivID) {
      if (CFG.game.getCiv(nCivID).civGameData.skills.getPointsLeft(nCivID) > 0) {
         Save_Civ_GameData var10000 = CFG.game.getCiv(nCivID).civGameData;
         var10000.fModifier_EconomyGrowth = var10000.fModifier_EconomyGrowth - CFG.game.getCiv(nCivID).civGameData.skills.POINTS_ECONOMY_GROWTH * 1.2F / 100.0F;
         CFG.game.getCiv(nCivID).civGameData.skills.POINTS_ECONOMY_GROWTH = Math.min(CFG.game.getCiv(nCivID).civGameData.skills.POINTS_ECONOMY_GROWTH + 1, 125);
         var10000 = CFG.game.getCiv(nCivID).civGameData;
         var10000.fModifier_EconomyGrowth = var10000.fModifier_EconomyGrowth + CFG.game.getCiv(nCivID).civGameData.skills.POINTS_ECONOMY_GROWTH * 1.2F / 100.0F;
      }
   }

   public static final boolean canAdd_IncomeTaxation(int nCivID) {
      return CFG.game.getCiv(nCivID).civGameData.skills.POINTS_INCOME_TAXATION < 100;
   }

   public static final void add_IncomeTaxation(int nCivID) {
      if (CFG.game.getCiv(nCivID).civGameData.skills.getPointsLeft(nCivID) > 0) {
         Save_Civ_GameData var10000 = CFG.game.getCiv(nCivID).civGameData;
         var10000.fModifier_IncomeTaxation = var10000.fModifier_IncomeTaxation
            - CFG.game.getCiv(nCivID).civGameData.skills.POINTS_INCOME_TAXATION * 0.5F / 100.0F;
         CFG.game.getCiv(nCivID).civGameData.skills.POINTS_INCOME_TAXATION = Math.min(
            CFG.game.getCiv(nCivID).civGameData.skills.POINTS_INCOME_TAXATION + 1, 100
         );
         var10000 = CFG.game.getCiv(nCivID).civGameData;
         var10000.fModifier_IncomeTaxation = var10000.fModifier_IncomeTaxation
            + CFG.game.getCiv(nCivID).civGameData.skills.POINTS_INCOME_TAXATION * 0.5F / 100.0F;
      }
   }

   public static final boolean canAdd_IncomeProduction(int nCivID) {
      return CFG.game.getCiv(nCivID).civGameData.skills.POINTS_INCOME_PRODUCTION < 100;
   }

   public static final void add_IncomeProduction(int nCivID) {
      if (CFG.game.getCiv(nCivID).civGameData.skills.getPointsLeft(nCivID) > 0) {
         Save_Civ_GameData var10000 = CFG.game.getCiv(nCivID).civGameData;
         var10000.fModifier_IncomeProduction = var10000.fModifier_IncomeProduction
            - CFG.game.getCiv(nCivID).civGameData.skills.POINTS_INCOME_PRODUCTION * 0.5F / 100.0F;
         CFG.game.getCiv(nCivID).civGameData.skills.POINTS_INCOME_PRODUCTION = Math.min(
            CFG.game.getCiv(nCivID).civGameData.skills.POINTS_INCOME_PRODUCTION + 1, 100
         );
         var10000 = CFG.game.getCiv(nCivID).civGameData;
         var10000.fModifier_IncomeProduction = var10000.fModifier_IncomeProduction
            + CFG.game.getCiv(nCivID).civGameData.skills.POINTS_INCOME_PRODUCTION * 0.5F / 100.0F;
      }
   }

   public static final boolean canAdd_Administration(int nCivID) {
      return CFG.game.getCiv(nCivID).civGameData.skills.POINTS_ADMINISTRATION < 80;
   }

   public static final void add_Administration(int nCivID) {
      if (CFG.game.getCiv(nCivID).civGameData.skills.getPointsLeft(nCivID) > 0) {
         Save_Civ_GameData var10000 = CFG.game.getCiv(nCivID).civGameData;
         var10000.fModifier_Administration = var10000.fModifier_Administration
            + CFG.game.getCiv(nCivID).civGameData.skills.POINTS_ADMINISTRATION * 0.75F / 100.0F;
         CFG.game.getCiv(nCivID).civGameData.skills.POINTS_ADMINISTRATION = Math.min(CFG.game.getCiv(nCivID).civGameData.skills.POINTS_ADMINISTRATION + 1, 80);
         var10000 = CFG.game.getCiv(nCivID).civGameData;
         var10000.fModifier_Administration = var10000.fModifier_Administration
            - CFG.game.getCiv(nCivID).civGameData.skills.POINTS_ADMINISTRATION * 0.75F / 100.0F;
      }
   }

   public static final boolean canAdd_MilitaryUpkeep(int nCivID) {
      return CFG.game.getCiv(nCivID).civGameData.skills.POINTS_MILITARY_UPKEEP < 30;
   }

   public static final void add_MilitaryUpkeep(int nCivID) {
      if (CFG.game.getCiv(nCivID).civGameData.skills.getPointsLeft(nCivID) > 0) {
         Save_Civ_GameData var10000 = CFG.game.getCiv(nCivID).civGameData;
         var10000.fModifier_MilitaryUpkeep = var10000.fModifier_MilitaryUpkeep
            + CFG.game.getCiv(nCivID).civGameData.skills.POINTS_MILITARY_UPKEEP * 0.35F / 100.0F;
         CFG.game.getCiv(nCivID).civGameData.skills.POINTS_MILITARY_UPKEEP = Math.min(CFG.game.getCiv(nCivID).civGameData.skills.POINTS_MILITARY_UPKEEP + 1, 30);
         var10000 = CFG.game.getCiv(nCivID).civGameData;
         var10000.fModifier_MilitaryUpkeep = var10000.fModifier_MilitaryUpkeep
            - CFG.game.getCiv(nCivID).civGameData.skills.POINTS_MILITARY_UPKEEP * 0.35F / 100.0F;
      }
   }

   public static final boolean canAdd_Research(int nCivID) {
      return CFG.game.getCiv(nCivID).civGameData.skills.POINTS_RESEARCH < 100;
   }

   public static final void add_Research(int nCivID) {
      if (CFG.game.getCiv(nCivID).civGameData.skills.getPointsLeft(nCivID) > 0) {
         Save_Civ_GameData var10000 = CFG.game.getCiv(nCivID).civGameData;
         var10000.fModifier_Research = var10000.fModifier_Research - CFG.game.getCiv(nCivID).civGameData.skills.POINTS_RESEARCH * 1.25F / 100.0F;
         CFG.game.getCiv(nCivID).civGameData.skills.POINTS_RESEARCH = Math.min(CFG.game.getCiv(nCivID).civGameData.skills.POINTS_RESEARCH + 1, 100);
         var10000 = CFG.game.getCiv(nCivID).civGameData;
         var10000.fModifier_Research = var10000.fModifier_Research + CFG.game.getCiv(nCivID).civGameData.skills.POINTS_RESEARCH * 1.25F / 100.0F;
      }
   }

   public static final void add_Education(int nCivID) {
      if (CFG.game.getCiv(nCivID).civGameData.skills.getPointsLeft(nCivID) > 0) {
         Save_Civ_GameData var10000 = CFG.game.getCiv(nCivID).civGameData;
         var10000.fModifier_Research = var10000.fModifier_Research - CFG.game.getCiv(nCivID).civGameData.skills.POINTS_Education * 1.9F / 100.0F;
         CFG.game.getCiv(nCivID).civGameData.skills.POINTS_Education = Math.min(CFG.game.getCiv(nCivID).civGameData.skills.POINTS_Education + 1, 25);
         var10000 = CFG.game.getCiv(nCivID).civGameData;
         var10000.fModifier_Research = var10000.fModifier_Research + CFG.game.getCiv(nCivID).civGameData.skills.POINTS_Education * 1.9F / 100.0F;
         if (CFG.game.getCiv(nCivID).civGameData.skills.getPointsLeft(nCivID) > 0) {
            var10000 = CFG.game.getCiv(nCivID).civGameData;
            var10000.fModifier_IncomeProduction = var10000.fModifier_IncomeProduction
               - CFG.game.getCiv(nCivID).civGameData.skills.POINTS_Education * -0.3F / 100.0F;
            var10000 = CFG.game.getCiv(nCivID).civGameData;
            var10000.fModifier_IncomeProduction = var10000.fModifier_IncomeProduction
               + CFG.game.getCiv(nCivID).civGameData.skills.POINTS_Education * -0.3F / 100.0F;
         }
      }
   }

   public static final void add_BonusGenocidePower(int nCivID) {
      if (CFG.game.getCiv(nCivID).civGameData.skills.getPointsUpgradingArmy(nCivID) > 25
         && CFG.game.getCiv(nCivID).civGameData.skills.POINTS_BONUS_GENOCIDE < 50) {
         CFG.game.getCiv(nCivID).setMilitaryPoints(CFG.game.getCiv(nCivID).getMilitaryPoints() - 25);
         CFG.game.getCiv(nCivID).civGameData.skills.POINTS_BONUS_GENOCIDE = Math.min(CFG.game.getCiv(nCivID).civGameData.skills.POINTS_BONUS_GENOCIDE + 1, 50);
         Save_Civ_GameData var10000 = CFG.game.getCiv(nCivID).civGameData;
         var10000.fModifier_GenocidePower = var10000.fModifier_GenocidePower
            + CFG.game.getCiv(nCivID).civGameData.skills.POINTS_BONUS_GENOCIDE * 0.25F / 100.0F;
         CFG.game.getCiv(nCivID).civGameData.fModifier_IncomeProduction -= 1.0E-4F;
      }
   }

   public static final void add_Attack(int nCivID) {
      if (CFG.game.getCiv(nCivID).civGameData.skills.getPointsUpgradingArmy(nCivID) > 25 && CFG.game.getCiv(nCivID).civGameData.skills.POINTS_ATTACK < 200) {
         CFG.game.getCiv(nCivID).setMilitaryPoints(CFG.game.getCiv(nCivID).getMilitaryPoints() - 25);
         CFG.game.getCiv(nCivID).civGameData.skills.POINTS_ATTACK = Math.min(CFG.game.getCiv(nCivID).civGameData.skills.POINTS_ATTACK + 1, 200);
         Save_Civ_GameData var10000 = CFG.game.getCiv(nCivID).civGameData;
         var10000.fModifier_AttackBonus = var10000.fModifier_AttackBonus + CFG.game.getCiv(nCivID).civGameData.skills.POINTS_ATTACK * 0.04F / 100.0F;
         CFG.game.getCiv(nCivID).civGameData.fModifier_IncomeProduction -= 8.0E-4F;
      }
   }

   public static final void add_Defense(int nCivID) {
      if (CFG.game.getCiv(nCivID).civGameData.skills.getPointsUpgradingArmy(nCivID) > 25 && CFG.game.getCiv(nCivID).civGameData.skills.POINTS_Defense < 150) {
         CFG.game.getCiv(nCivID).setMilitaryPoints(CFG.game.getCiv(nCivID).getMilitaryPoints() - 25);
         CFG.game.getCiv(nCivID).civGameData.skills.POINTS_Defense = Math.min(CFG.game.getCiv(nCivID).civGameData.skills.POINTS_Defense + 1, 150);
         Save_Civ_GameData var10000 = CFG.game.getCiv(nCivID).civGameData;
         var10000.fModifier_DefenseBonus = var10000.fModifier_DefenseBonus + CFG.game.getCiv(nCivID).civGameData.skills.POINTS_Defense * 0.05F / 100.0F;
         CFG.game.getCiv(nCivID).civGameData.fModifier_IncomeProduction -= 9.0000004E-4F;
      }
   }

   public static final void add_AttackLaws(int nCivID) {
      if (CFG.game.getCiv(nCivID).civGameData.skills.getPointsLeftLaws(nCivID) > 0) {
         Save_Civ_GameData var10000 = CFG.game.getCiv(nCivID).civGameData;
         var10000.fModifier_AttackBonus = var10000.fModifier_AttackBonus - CFG.game.getCiv(nCivID).civGameData.skills.POINTS_ATTACKLAWS * 30.25F / 100.0F;
         CFG.game.getCiv(nCivID).civGameData.skills.POINTS_ATTACKLAWS = Math.min(CFG.game.getCiv(nCivID).civGameData.skills.POINTS_ATTACKLAWS + 1, 100);
         var10000 = CFG.game.getCiv(nCivID).civGameData;
         var10000.fModifier_AttackBonus = var10000.fModifier_AttackBonus + CFG.game.getCiv(nCivID).civGameData.skills.POINTS_ATTACKLAWS * 30.25F / 100.0F;
      }
   }

   public static final void add_DefenseLaws(int nCivID) {
      if (CFG.game.getCiv(nCivID).civGameData.skills.getPointsLeftLaws(nCivID) > 0) {
         Save_Civ_GameData var10000 = CFG.game.getCiv(nCivID).civGameData;
         var10000.fModifier_DefenseBonus = var10000.fModifier_DefenseBonus - CFG.game.getCiv(nCivID).civGameData.skills.POINTS_DefenseLaws * 10.0F / 100.0F;
         CFG.game.getCiv(nCivID).civGameData.skills.POINTS_DefenseLaws = Math.min(CFG.game.getCiv(nCivID).civGameData.skills.POINTS_DefenseLaws + 1, 30);
         var10000 = CFG.game.getCiv(nCivID).civGameData;
         var10000.fModifier_DefenseBonus = var10000.fModifier_DefenseBonus + CFG.game.getCiv(nCivID).civGameData.skills.POINTS_DefenseLaws * 10.0F / 100.0F;
      }
   }

   public static final void add_Budget(int nCivID) {
      if (CFG.game.getCiv(nCivID).civGameData.skills.getPointsLeftLaws(nCivID) > 0) {
         Save_Civ_GameData var10000 = CFG.game.getCiv(nCivID).civGameData;
         var10000.fWarWeariness = var10000.fWarWeariness - CFG.game.getCiv(nCivID).civGameData.skills.POINTS_Budget * -10.0F / 100.0F;
         CFG.game.getCiv(nCivID).civGameData.skills.POINTS_Budget = Math.min(CFG.game.getCiv(nCivID).civGameData.skills.POINTS_Budget + 1, 1000);
         var10000 = CFG.game.getCiv(nCivID).civGameData;
         var10000.fWarWeariness = var10000.fWarWeariness + CFG.game.getCiv(nCivID).civGameData.skills.POINTS_Budget * -10.0F / 100.0F;
      }
   }

   public static final void add_Vassals(int nCivID) {
      if (CFG.game.getCiv(nCivID).civGameData.skills.getPointsLeftLaws(nCivID) > 0) {
         Save_Civ_GameData var10000 = CFG.game.getCiv(nCivID).civGameData;
         var10000.fVassalLiberityDisere = var10000.fVassalLiberityDisere - CFG.game.getCiv(nCivID).civGameData.skills.POINTS_VASSALS * -100.0F / 100.0F;
         CFG.game.getCiv(nCivID).civGameData.skills.POINTS_VASSALS = Math.min(CFG.game.getCiv(nCivID).civGameData.skills.POINTS_VASSALS + 1, 1000);
         var10000 = CFG.game.getCiv(nCivID).civGameData;
         var10000.fVassalLiberityDisere = var10000.fVassalLiberityDisere + CFG.game.getCiv(nCivID).civGameData.skills.POINTS_VASSALS * -100.0F / 100.0F;
      }
   }

   public static final boolean canAdd_Colonization(int nCivID) {
      return CFG.game.getCiv(nCivID).civGameData.skills.POINTS_COLONIZATION < 15;
   }

   public static final void add_Colonization(int nCivID) {
      if (CFG.game.getCiv(nCivID).civGameData.skills.getPointsLeft(nCivID) > 0) {
         Save_Civ_GameData var10000 = CFG.game.getCiv(nCivID).civGameData;
         var10000.fModifier_ColonizationCost = var10000.fModifier_ColonizationCost
            - CFG.game.getCiv(nCivID).civGameData.skills.POINTS_COLONIZATION * 1.0F / 100.0F;
         CFG.game.getCiv(nCivID).civGameData.skills.POINTS_COLONIZATION = Math.min(CFG.game.getCiv(nCivID).civGameData.skills.POINTS_COLONIZATION + 1, 15);
         var10000 = CFG.game.getCiv(nCivID).civGameData;
         var10000.fModifier_ColonizationCost = var10000.fModifier_ColonizationCost
            + CFG.game.getCiv(nCivID).civGameData.skills.POINTS_COLONIZATION * 1.0F / 100.0F;
      }
   }
}
