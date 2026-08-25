package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.Gdx;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Game_NextTurnUpdate {
   public static int INFLATION_PEAK_VALUE = 100;
   public static final float INFLATION_STARTS_AT = 0.235F;
   public static final float INFLATION_MODIFIER = 18.12746F;
   public static float LEAGUE_BUDGET = 1.0F;
   public static final float TAXES_INFLUENCE_POP = 0.3F;
   public static final float TAXES_INFLUENCE_PRODUCTION = 0.175F;
   public static final int PERCENTAGE_OF_INCOME_FOR_LORD_DEFAULT = 9;
   public static final int PERCENTAGE_OF_INCOME_FOR_LORD_MAX = 20;
   public static final int PERCENTAGE_OF_INCOME_FOR_LORD_MIN = 0;
   public static final float PERCENTAGE_OF_INCOME_FOR_WAR_REPARATIONS = 0.08F;
   public static final float EMPLOYEMENT_PER_ECONOMY = 1.025F;
   public static final float EMPLOYEMENT_PER_ECONOMY_OLD = 1.775F;
   public static final float DEFENSIVE_POSITION_MILITARY_UPKEEP_PER_TUR = 0.008F;
   public static final int BUDGET_MAX = 200;
   public static float LEAGUE_MANPOWER_PERTURN = 1.0F;
   public static float AVERAGE_MANPOWER_PERTURN = 250.0F;

   Game_NextTurnUpdate() {
   }

   public final void updatePlayableProvinces() {
      CFG.oAI.PLAYABLE_PROVINCES = 0;

      for (int i = 0; i < CFG.game.getProvincesSize(); i++) {
         if (!CFG.game.getProvince(i).getSeaProvince() && CFG.game.getProvince(i).getWasteland() < 0) {
            CFG.oAI.PLAYABLE_PROVINCES++;
         }
      }

      CFG.oAI.NUM_OF_CIVS_IN_THE_GAME = 0;

      for (int var2 = 1; var2 < CFG.game.getCivsSize(); var2++) {
         if (CFG.game.getCiv(var2).getNumOfProvinces() > 0) {
            CFG.oAI.NUM_OF_CIVS_IN_THE_GAME++;
         }
      }

      CFG.oAI.NUM_OF_CIVS_IN_THE_GAME = Math.max(1, CFG.oAI.NUM_OF_CIVS_IN_THE_GAME);
      CFG.oAI.updateMinRivals();
   }

   public final void updateCivAssimilaty(int n) {
      CFG.game.getCiv(n).lProvincesWithMoreAssimilate.clear();
      if (CFG.game.getCiv(n).getNumOfProvinces() > 0) {
         ArrayList<Integer> arrayList = new ArrayList<>();
         int n3 = CFG.game.getCiv(n).getNumOfProvinces();

         for (int n2 = 0; n2 <= n3 - 1; n2++) {
            arrayList.add(CFG.game.getCiv(n).getProvinceID(n2));
         }

         while (arrayList.size() > 0) {
            int n4 = 0;
            n3 = 0;

            for (int var8 = 0; var8 < arrayList.size(); var8++) {
               int n5 = arrayList.get(var8);
               int n6 = CFG.game.getProvince(n5).getRecruitableArmyPoints();
               n5 = n4;
               if (n6 > n4) {
                  n5 = n6;
                  n3 = var8;
               }

               n4 = n5;
            }

            Object e = arrayList.get(n3);
            CFG.game.getCiv(n).lProvincesWithMoreAssimilate.add((Integer)e);
            arrayList.remove(n3);
         }
      }
   }

   protected final void updateCivRecruitableArmy(int civId) {
      Civilization civ = CFG.game.getCiv(civId);
      civ.lProvincesWithMoreRecruitableArmy.clear();
      int numOfProvinces = civ.getNumOfProvinces();
      if (numOfProvinces > 0) {
         List<Integer> provinceIDs = new ArrayList<>();

         for (int i = 0; i < numOfProvinces; i++) {
            provinceIDs.add(civ.getProvinceID(i));
         }

         while (!provinceIDs.isEmpty()) {
            int maxRecruitableArmyPoints = Integer.MIN_VALUE;
            int bestProvinceIndex = -1;

            for (int i = 0; i < provinceIDs.size(); i++) {
               int provinceID = provinceIDs.get(i);
               int recruitableArmyPoints = CFG.game.getProvince(provinceID).getArmySize();
               if (recruitableArmyPoints > maxRecruitableArmyPoints) {
                  maxRecruitableArmyPoints = recruitableArmyPoints;
                  bestProvinceIndex = i;
               }
            }

            if (bestProvinceIndex != -1) {
               Integer bestProvinceID = provinceIDs.get(bestProvinceIndex);
               civ.lProvincesWithMoreRecruitableArmy.add(bestProvinceID);
               provinceIDs.remove(bestProvinceIndex);
            }
         }
      }
   }

   public final void updateInflationPeakValue() {
      INFLATION_PEAK_VALUE = 1;

      for (int i = 1; i < CFG.game.getCivsSize(); i++) {
         if (CFG.game.getCiv(i).getNumOfProvinces() > 0) {
            INFLATION_PEAK_VALUE = Math.max(INFLATION_PEAK_VALUE, CFG.game.getCiv(i).iIncomeTaxation + CFG.game.getCiv(i).iIncomeProduction);
            LEAGUE_BUDGET = (int)Math.max(
               LEAGUE_BUDGET, (float)(CFG.game.getCiv(i).iIncomeTaxation + CFG.game.getCiv(i).iIncomeProduction - CFG.game.getCiv(i).iAdministrationCosts)
            );
         }
      }

      float f2 = 0.0F;
      float f1 = 0.0F;
      int b = 1;
      int j = 0;
      int var8 = 0;

      while (b < CFG.game.getCivsSize()) {
         float f3 = f2;
         float f4 = f1;
         j = var8;
         if (CFG.game.getCiv(b).getNumOfProvinces() > 0) {
            f3 = CFG.game.getCiv(b).getManPowerIncreasing();
            f4 = f1 + f3;
            f3 = Math.max(f2, f3);
            j = var8 + 1;
         }

         b++;
         f2 = f3;
         f1 = f4;
         var8 = j;
      }

      LEAGUE_MANPOWER_PERTURN = f2;
      AVERAGE_MANPOWER_PERTURN = f1 / var8;
      LEAGUE_BUDGET = (int)(LEAGUE_BUDGET * 0.9F);

      for (int var9 = 1; var9 < CFG.game.getCivsSize(); var9++) {
         if (CFG.game.getCiv(var9).getNumOfProvinces() > 0) {
            CFG.game.getCiv(var9).iLeague = Math.min(
               (int)(
                  Math.max(CFG.game.getCiv(var9).iIncomeTaxation + CFG.game.getCiv(var9).iIncomeProduction - CFG.game.getCiv(var9).iAdministrationCosts, 0)
                     / LEAGUE_BUDGET
                     * 10.0F
               ),
               10
            );
         }
      }

      for (int var10 = 0; var10 < CFG.game.getPlayersSize(); var10++) {
         if (this.getInflationPerc(CFG.game.getPlayer(var10).getCivID()) > 0.0049F) {
            CFG.game
               .getCiv(CFG.game.getPlayer(var10).getCivID())
               .getCivilization_Diplomacy_GameData()
               .messageBox
               .addMessage(new Message_HighInflation(CFG.game.getPlayer(var10).getCivID(), 0));
         }
      }
   }

   public final void updateCivs_Money() {
      Gdx.app.log("AoC", "updateCivs_Money 0000");

      for (int i = 1; i < CFG.game.getCivsSize(); i++) {
         this.getBalance_UpdateBudget_Prepare(i);
      }

      Gdx.app.log("AoC", "updateCivs_Money 11111");

      for (int var2 = 1; var2 < CFG.game.getCivsSize(); var2++) {
         if (CFG.game.getCiv(var2).getNumOfProvinces() > 0) {
            CFG.game.getCiv(var2).setMoney(CFG.game.getCiv(var2).getMoney() + this.getBalance(var2));
            CFG.game.getCiv(var2).updateLoansNextTurn();
         }
      }

      Gdx.app.log("AoC", "updateCivs_Money END");
   }

   public final void updateCivs_ManPower() {
      for (int b = 1; b < CFG.game.getCivsSize(); b++) {
         if (CFG.game.getCiv(b).getNumOfProvinces() > 0) {
            CFG.game.getCiv(b).updateManPowerIncreasing();
         }
      }
   }

   public final void updateCivs_Food() {
   }

   public final int getProductionFood(int nCivID) {
      int movementPointsModifier = (int)CFG.gameAges.getAge_MovementPointsModifier(Game_Calendar.CURRENT_AGEID);
      int farms = CFG.game.getCiv(nCivID).iNumOf_Farms;
      int provinces = CFG.game.getCiv(nCivID).getNumOfProvinces();
      int technologyLevel = (int)CFG.game.getCiv(nCivID).getTechnologyLevel();
      int happiness = CFG.game.getCiv(nCivID).getHappiness();
      long population = CFG.game.getCiv(nCivID).countPopulation();
      int foodProduction = (movementPointsModifier * 500 + farms * 3 + provinces / 2) * technologyLevel * 100 * happiness;
      return (int)(foodProduction - population / 10L);
   }

   public final void updateProvinceStability() {
      for (int i = 1; i < CFG.game.getCivsSize(); i++) {
         CFG.game.getCiv(i).lProvincesWithLowStability.clear();
         CFG.game.getCiv(i).fStability = 0.0F;
      }

      for (int var3 = 0; var3 < CFG.game.getProvincesSize(); var3++) {
         if (!CFG.game.getProvince(var3).getSeaProvince() && CFG.game.getProvince(var3).getWasteland() < 0) {
            CFG.game.getProvince(var3).updateProvinceStability();
            if (CFG.game.getProvince(var3).getCivID() > 0) {
               Civilization var10000 = CFG.game.getCiv(CFG.game.getProvince(var3).getCivID());
               var10000.fStability = var10000.fStability + CFG.game.getProvince(var3).getProvinceStability();
            }
         }
      }

      for (int var4 = 1; var4 < CFG.game.getCivsSize(); var4++) {
         for (int j = CFG.game.getCiv(var4).lProvincesWithLowStability.size() - 1; j >= 0; j--) {
            if (CFG.game.getCiv(var4).isAssimilateOrganized(CFG.game.getCiv(var4).lProvincesWithLowStability.get(j))) {
               CFG.game.getCiv(var4).lProvincesWithLowStability.remove(j);
            }
         }

         CFG.game.getCiv(var4).setStability(CFG.game.getCiv(var4).fStability / CFG.game.getCiv(var4).getNumOfProvinces());
      }
   }

   public final int getBalance(int nCivID) {
      return (int)(this.getIncome(nCivID) - this.getExpenses(nCivID));
   }

   public final int getAdministration_Capital(int nCivID) {
      return CFG.game.getCiv(nCivID).getCapitalProvinceID() < 0
         ? (CFG.game.getCiv(nCivID).getNumOfProvinces() > 0 ? CFG.game.getCiv(nCivID).getProvinceID(0) : 0)
         : CFG.game.getCiv(nCivID).getCapitalProvinceID();
   }

   public final void getBalance_UpdateBudget_Prepare(int nCivID) {
      CFG.game.getCiv(nCivID).iIncomeTaxation = 0;
      CFG.game.getCiv(nCivID).iIncomeProduction = 0;
      CFG.game.getCiv(nCivID).iAdministrationCosts = 0;
      int nCapital = this.getAdministration_Capital(nCivID);
      float incomeModifer = this.taxIncome_Modifier(nCivID);

      for (int i = 0; i < CFG.game.getCiv(nCivID).getNumOfProvinces(); i++) {
         CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).iIncome_Taxation = this.getProvinceIncome_Taxation(
            CFG.game.getCiv(nCivID).getProvinceID(i), nCivID, incomeModifer
         );
         CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).iIncome_Production = this.getProvinceIncome_Production(
            CFG.game.getCiv(nCivID).getProvinceID(i), nCivID, incomeModifer
         );
         CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).iAdministrationCost = Math.min(
            CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).iIncome_Taxation
               + CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).iIncome_Production,
            this.getProvinceAdministration(CFG.game.getCiv(nCivID).getProvinceID(i), nCapital)
         );
         CFG.game.getCiv(nCivID).iIncomeTaxation = (int)(
            CFG.game.getCiv(nCivID).iIncomeTaxation + CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).iIncome_Taxation
         );
         CFG.game.getCiv(nCivID).iIncomeProduction = (int)(
            CFG.game.getCiv(nCivID).iIncomeProduction + CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).iIncome_Production
         );
         CFG.game.getCiv(nCivID).iAdministrationCosts = (int)(
            CFG.game.getCiv(nCivID).iAdministrationCosts + CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).iAdministrationCost
         );
         if (CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getBalance_LastTurn() < 0) {
            CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).saveProvinceData.iNumOfTurnsWithBalanceOnMinus++;
         } else {
            CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).saveProvinceData.iNumOfTurnsWithBalanceOnMinus = 0;
         }
      }

      CFG.game.getCiv(nCivID).iBudget = (int)(this.getIncome(nCivID) - CFG.game.getCiv(nCivID).iAdministrationCosts);
   }

   public final float getHappinessChange_ByTaxation(int paramInt) {
      float f1 = CFG.ideologiesManager.getIdeology(CFG.game.getCiv(paramInt).getIdeologyID()).ACCEPTABLE_TAXATION;
      float f2 = CFG.ideologiesManager.getIdeology(CFG.game.getCiv(paramInt).getIdeologyID()).ACCEPTABLE_TAXATION
         * CFG.game.getCiv(paramInt).getTechnologyLevel()
         / 21.73F;
      float f3 = CFG.game.getCiv(paramInt).getTaxationLevel();
      float f4;
      if (CFG.game.getCiv(paramInt).getTaxationLevel() > CFG.ideologiesManager.getIdeology(CFG.game.getCiv(paramInt).getIdeologyID()).ACCEPTABLE_TAXATION) {
         f4 = 1.45F;
      } else {
         f4 = 0.9F;
      }

      return ((f1 + f2) * 100.0F - f3 * 100.0F) * f4 * 0.034F + 0.1F;
   }

   public final float getHappinessChange_ByTaxation_Occupied(int paramInt) {
      float f1 = CFG.ideologiesManager.getIdeology(CFG.game.getCiv(paramInt).getIdeologyID()).ACCEPTABLE_TAXATION;
      float f2 = CFG.ideologiesManager.getIdeology(CFG.game.getCiv(paramInt).getIdeologyID()).ACCEPTABLE_TAXATION
         * CFG.game.getCiv(paramInt).getTechnologyLevel()
         / 21.73F;
      float f3 = CFG.game.getCiv(paramInt).getTaxationLevel();
      float f4;
      if (CFG.game.getCiv(paramInt).getTaxationLevel() > CFG.ideologiesManager.getIdeology(CFG.game.getCiv(paramInt).getIdeologyID()).ACCEPTABLE_TAXATION) {
         f4 = 1.45F;
      } else {
         f4 = 1.0F;
      }

      return ((f1 + f2) * 100.0F - f3 * 100.0F) * f4 * 0.02675F + 0.034F;
   }

   public float taxIncome_Modifier(int nCivID) {
      if (CFG.game.getCiv(nCivID).getControlledByPlayer()) {
         switch (CFG.DIFFICULTY) {
            case 0:
               return 1.5F;
            case 1:
            default:
               return 1.025F;
            case 2:
               return 1.35F;
            case 3:
               return 0.95F;
            case 4:
               return 0.9F;
         }
      } else {
         switch (CFG.DIFFICULTY) {
            case 0:
               return 1.15F;
            case 1:
            default:
               return 1.0F;
            case 2:
               return 1.35F;
            case 3:
               return 2.5F;
            case 4:
               return 6.5F;
         }
      }
   }

   public final int getMilitarySpendings(int nCivID, int iBudget) {
      return Math.max(0, (int)(this.getMilitaryUpkeep_Total(nCivID) / iBudget * 100.0F));
   }

   public final float getIncome(int nCivID) {
      float tempTotal = 0.0F;
      tempTotal += CFG.game.getCiv(nCivID).iIncomeTaxation;
      tempTotal += CFG.game.getCiv(nCivID).iIncomeProduction;
      tempTotal += this.getIncome_FromVassalsOfCiv(nCivID);
      tempTotal += this.getIncome_Debuff_IsVassal(nCivID);
      tempTotal += this.getIncome_Buff_WarReparations(nCivID);
      return (int)(tempTotal + this.getIncome_Debuff_WarReparations(nCivID));
   }

   public final float getIncome_TaxesLevel(int nCivID) {
      return this.getIncome_TaxesLevel_Taxation(nCivID) + this.getIncome_TaxesLevel_Production(nCivID);
   }

   public final float getIncome_TaxesLevel_Taxation(int nCivID) {
      float tempTotal = 0.0F;

      for (int i = 0; i < CFG.game.getCiv(nCivID).getNumOfProvinces(); i++) {
         tempTotal += this.getProvinceIncome_Taxation(CFG.game.getCiv(nCivID).getProvinceID(i));
      }

      return tempTotal;
   }

   public final float getIncome_TaxesLevel_Production(int nCivID) {
      float tempTotal = 0.0F;

      for (int i = 0; i < CFG.game.getCiv(nCivID).getNumOfProvinces(); i++) {
         tempTotal += this.getProvinceIncome_Production(CFG.game.getCiv(nCivID).getProvinceID(i));
      }

      return tempTotal;
   }

   public final float getIncome_Debuff_IsVassal(int nCivID) {
      return CFG.game.getCiv(nCivID).getPuppetOfCivID() != nCivID ? -this.getIncome_Vassals(CFG.game.getCiv(nCivID).getPuppetOfCivID(), nCivID) : 0.0F;
   }

   public final float getIncome_FromVassalsOfCiv(int nCivID) {
      float tempTotal = 0.0F;

      for (int i = CFG.game.getCiv(nCivID).civGameData.lVassals.size() - 1; i >= 0; i--) {
         tempTotal += this.getIncome_Vassals(nCivID, CFG.game.getCiv(nCivID).civGameData.lVassals.get(i).iCivID);
      }

      return tempTotal;
   }

   public final float getIncome_Debuff_WarReparations(int nCivID) {
      float tempTotal = 0.0F;

      for (int i = CFG.game.getCiv(nCivID).getWarReparationsPaysSize() - 1; i >= 0; i--) {
         tempTotal -= this.getWarReparationsMoney(nCivID);
      }

      return tempTotal;
   }

   public final float getIncome_Buff_WarReparations(int nCivID) {
      float tempTotal = 0.0F;

      for (int i = CFG.game.getCiv(nCivID).getWarReparationsGetsSize() - 1; i >= 0; i--) {
         tempTotal += this.getWarReparationsMoney(CFG.game.getCiv(nCivID).getWarReparationsGets(i).iFromCivID);
      }

      return tempTotal;
   }

   public final float getIncome_Vassals(int nForCivID, int nIsVassal) {
      return CFG.game.getCiv(nIsVassal).getPuppetOfCivID() == nForCivID ? this.getVassalizationMoney(nIsVassal) : 0.0F;
   }

   public final float getManPowerIncome_Vassals(int nForCivID, int nIsVassal) {
      return CFG.game.getCiv(nIsVassal).getPuppetOfCivID() == nForCivID
         ? this.getManPowerIncomeMoney(nIsVassal) * (CFG.game.getCiv(CFG.game.getCiv(nIsVassal).getPuppetOfCivID()).getVassal_Tribute(nIsVassal) / 100.0F)
         : 0.0F;
   }

   public final float getManPowerIncomeMoney(int nVassalID) {
      return CFG.game.getCiv(nVassalID).getManPower_ThisTurn()
         * (CFG.game.getCiv(CFG.game.getCiv(nVassalID).getPuppetOfCivID()).getVassal_Tribute(nVassalID) / 100.0F);
   }

   public final float getVassalizationMoney(int nVassalID) {
      return CFG.game.getCiv(nVassalID).iIncomeTaxation
         * (CFG.game.getCiv(CFG.game.getCiv(nVassalID).getPuppetOfCivID()).getVassal_Tribute(nVassalID) / 100.0F);
   }

   public final float getWarReparationsMoney(int nCivID) {
      return CFG.game.getCiv(nCivID).iIncomeTaxation * 0.2F;
   }

   public final float getProvinceIncomeAndExpenses_Total(int nProvinceID) {
      return this.getProvinceIncome_Taxation(nProvinceID)
         + this.getProvinceIncome_Production(nProvinceID)
         - (
            CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getCapitalProvinceID() >= 0
               ? this.getProvinceAdministration(nProvinceID, CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getCapitalProvinceID())
               : 0.0F
         );
   }

   public final float getProvinceIncome_Taxation(int nProvinceID) {
      return this.getProvinceIncome_Taxation(
         nProvinceID, CFG.game.getProvince(nProvinceID).getCivID(), this.taxIncome_Modifier(CFG.game.getProvince(nProvinceID).getCivID())
      );
   }

   public final float getProvinceIncome_Taxation(int nProvinceID, int nCivID, float incomeModifer) {
      float tTaxOut = CFG.game.getProvince(nProvinceID).isOccupied()
         ? this.getProvinceAdministration(nProvinceID, CFG.game_NextTurnUpdate.getAdministration_Capital(nCivID))
         : (float)(
               Math.pow(
                     this.getProvince_EmploymentPopulation(nProvinceID)
                        * (
                           CFG.gameAges.getAge_IncomeTaxation_Base(Game_Calendar.CURRENT_AGEID)
                              + CFG.gameAges.getAge_IncomeTaxation_PerTechnology(Game_Calendar.CURRENT_AGEID)
                                 * CFG.game.getCiv(nCivID).getTechnologyLevel()
                                 * 21.923813F
                        ),
                     0.8386
                  )
                  + Math.pow(
                     this.getProvince_UnemploymentPopulation(nProvinceID)
                        * (
                           CFG.gameAges.getAge_IncomeTaxation_Base(Game_Calendar.CURRENT_AGEID)
                              + CFG.gameAges.getAge_IncomeTaxation_PerTechnology(Game_Calendar.CURRENT_AGEID)
                                 * CFG.game.getCiv(nCivID).getTechnologyLevel()
                                 * 21.923813F
                        ),
                     0.7936
                  )
            )
            * CFG.gameAges.getAge_TreasuryModifier(Game_Calendar.CURRENT_AGEID)
            * (0.675F + 0.325F * CFG.game.getProvince(nProvinceID).getProvinceStability())
            * (
               CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivID).getIdeologyID()).INCOME_TAXATION
                  + CFG.game.getCiv(nCivID).getModifier_IncomeTaxation()
                  + (CFG.game.getProvince(nProvinceID).getIsCapital() ? 0.1F : 0.0F)
                  + -0.16584F
                  + 0.3674786F * CFG.game.getProvince(nProvinceID).getHappiness()
            )
            * (0.7F + 0.3F * CFG.game.getCiv(nCivID).getTaxationLevel())
            * incomeModifer
            * Game_Calendar.GAME_SPEED;
      if (AI_Assistant.MINORITY_TAX != 1 && nCivID == CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) {
         Province_Population tPopData = CFG.game.getProvince(nProvinceID).getPopulationData();
         if (tPopData.getPopulation() > 0 && tPopData.getPopulationOfCivID(nCivID) < tPopData.getPopulation()) {
            float tOwnerShare = (float)tPopData.getPopulationOfCivID(nCivID) / (float)tPopData.getPopulation();
            float tMinorityFactor = AI_Assistant.MINORITY_TAX == 0 ? 0.75F : 1.4F;
            tTaxOut *= tOwnerShare + (1.0F - tOwnerShare) * tMinorityFactor;
         }
      }

      return tTaxOut;
   }

   public final int getProvince_EmploymentPopulation(int nProvinceID) {
      return (int)Math.min(
         (float)CFG.game.getProvince(nProvinceID).getPopulationData().getPopulation(),
         CFG.game.getProvince(nProvinceID).getEconomy()
            * (
               1.775F
                  + 0.1725F * CFG.game.getProvince(nProvinceID).getDevelopmentLevel()
                  + 0.0925F * CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getTechnologyLevel()
            )
      );
   }

   public final int getProvince_UnemploymentPopulation(int nProvinceID) {
      return Math.max(CFG.game.getProvince(nProvinceID).getPopulationData().getPopulation() - this.getProvince_EmploymentPopulation(nProvinceID), 0);
   }

   public final int getEmploymentPopulation(int nCivID) {
      int out = 0;

      for (int i = 0; i < CFG.game.getCiv(nCivID).getNumOfProvinces(); i++) {
         out += this.getProvince_EmploymentPopulation(CFG.game.getCiv(nCivID).getProvinceID(i));
      }

      return out;
   }

   public final int getUnemploymentPopulation(int nCivID) {
      int out = 0;

      for (int i = 0; i < CFG.game.getCiv(nCivID).getNumOfProvinces(); i++) {
         out += this.getProvince_UnemploymentPopulation(CFG.game.getCiv(nCivID).getProvinceID(i));
      }

      return out;
   }

   public final float getProvinceIncome_Production(int nProvinceID) {
      return this.getProvinceIncome_Production(
         nProvinceID, CFG.game.getProvince(nProvinceID).getCivID(), this.taxIncome_Modifier(CFG.game.getProvince(nProvinceID).getCivID())
      );
   }

   public final float getProvinceIncome_Production(int nProvinceID, int nCivID, float incomeModifer) {
      return CFG.game.getProvince(nProvinceID).isOccupied()
         ? (int)Math.min(
               CFG.game.getProvince(nProvinceID).getPopulationData().getPopulation()
                  * (
                     1.025F
                        + 0.1725F * CFG.game.getProvince(nProvinceID).getDevelopmentLevel()
                        + 0.0425F * CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getTechnologyLevel()
                  ),
               (float)CFG.game.getProvince(nProvinceID).getEconomy()
            )
            * (
               CFG.gameAges.getAge_IncomeProduction_Base(Game_Calendar.CURRENT_AGEID)
                  + CFG.gameAges.getAge_IncomeProduction_PerDevelopment(Game_Calendar.CURRENT_AGEID) * CFG.game.getProvince(nProvinceID).getDevelopmentLevel()
            )
            * (
               0.0685F
                  + 0.575F * CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getTechnologyLevel()
                  + 0.8625F * CFG.game.getProvince(nProvinceID).getDevelopmentLevel()
            )
            * (0.425F + 0.575F * CFG.game.getProvince(nProvinceID).getProvinceStability())
            * CFG.gameAges.getAge_TreasuryModifier_Production(Game_Calendar.CURRENT_AGEID)
            * (
               CFG.ideologiesManager.getIdeology(CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getIdeologyID()).INCOME_PRODUCTION
                  + BuildingsManager.getPort_IncomeProduction(CFG.game.getProvince(nProvinceID).getLevelOfPort())
                  + CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getModifier_IncomeProduction()
                  + (CFG.game.getProvince(nProvinceID).getIsCapital() ? 0.2F : 0.0F)
                  + BuildingsManager.getWorkshop_IncomeProduction(CFG.game.getProvince(nProvinceID).getLevelOfWorkshop())
            )
            * (0.825F + 0.175F * CFG.game.getCiv(nCivID).getTaxationLevel())
            * incomeModifer
            * Game_Calendar.GAME_SPEED
            * 0.1F
         : (int)Math.min(
               CFG.game.getProvince(nProvinceID).getPopulationData().getPopulation()
                  * (
                     1.025F
                        + 0.1725F * CFG.game.getProvince(nProvinceID).getDevelopmentLevel()
                        + 0.0425F * CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getTechnologyLevel()
                  ),
               (float)CFG.game.getProvince(nProvinceID).getEconomy()
            )
            * (
               CFG.gameAges.getAge_IncomeProduction_Base(Game_Calendar.CURRENT_AGEID)
                  + CFG.gameAges.getAge_IncomeProduction_PerDevelopment(Game_Calendar.CURRENT_AGEID) * CFG.game.getProvince(nProvinceID).getDevelopmentLevel()
            )
            * (
               0.0685F
                  + 0.575F * CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getTechnologyLevel()
                  + 0.8625F * CFG.game.getProvince(nProvinceID).getDevelopmentLevel()
            )
            * (0.425F + 0.575F * CFG.game.getProvince(nProvinceID).getProvinceStability())
            * CFG.gameAges.getAge_TreasuryModifier_Production(Game_Calendar.CURRENT_AGEID)
            * (
               CFG.ideologiesManager.getIdeology(CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getIdeologyID()).INCOME_PRODUCTION
                  + BuildingsManager.getPort_IncomeProduction(CFG.game.getProvince(nProvinceID).getLevelOfPort())
                  + CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getModifier_IncomeProduction()
                  + (CFG.game.getProvince(nProvinceID).getIsCapital() ? 0.2F : 0.0F)
                  + BuildingsManager.getWorkshop_IncomeProduction(CFG.game.getProvince(nProvinceID).getLevelOfWorkshop())
            )
            * (0.825F + 0.175F * CFG.game.getCiv(nCivID).getTaxationLevel())
            * incomeModifer
            * Game_Calendar.GAME_SPEED;
   }

   public final float getExpenses(int nCivID) {
      float tempTotal = 0.0F;
      tempTotal += CFG.game.getCiv(nCivID).iAdministrationCosts;
      tempTotal += this.getMilitaryUpkeep_Total(nCivID);
      tempTotal += this.getInvestments_Total(nCivID, CFG.game.getCiv(nCivID).iBudget);
      tempTotal += this.getGoodsSpendings(nCivID, CFG.game.getCiv(nCivID).iBudget);
      tempTotal += this.getInterestCost(nCivID);
      tempTotal += this.getInflation(nCivID);
      tempTotal += this.getAviationExpense(nCivID);
      return (int)Math.ceil(tempTotal + CFG.game.getCiv(nCivID).getLoans_GoldTotalPerTurn());
   }

   public int getAviationExpense(int civID) {
      float value = 0.0F;

      for (int i = 0; i < CFG.game.getCiv(civID).getNumOfProvinces(); i++) {
         if (CFG.game.getProvince(CFG.game.getCiv(civID).getProvinceID(i)).getLevelOfAirbase() > 0) {
            value += this.getPlaneCost(CFG.game.getCiv(civID).getProvinceID(i))
               / 10.0F
               * CFG.game.getProvince(CFG.game.getCiv(civID).getProvinceID(i)).getAirbase().getFightersInvest();
            value += this.getBomberCost(CFG.game.getCiv(civID).getProvinceID(i))
               / 20.0F
               * CFG.game.getProvince(CFG.game.getCiv(civID).getProvinceID(i)).getAirbase().getBombersInvest();
            value += this.getHelicopterCost(CFG.game.getCiv(civID).getProvinceID(i))
               / 15.0F
               * CFG.game.getProvince(CFG.game.getCiv(civID).getProvinceID(i)).getAirbase().getHelicoptersInvest();
         }
      }

      return (int)value;
   }

   public final float getExpenses_Budget(int nCivID) {
      float tempTotal = 0.0F;
      return (int)Math.ceil(tempTotal + CFG.game.getCiv(nCivID).iAdministrationCosts);
   }

   public void updateCivs_Planes() {
      for (int i = 0; i < CFG.game.getProvincesSize(); i++) {
         if (CFG.game.getProvince(i).getLevelOfAirbase() > 0 && CFG.game.getProvince(i).getAirbase() != null) {
            float value = this.getPlaneCost(i) / 10.0F * CFG.game.getProvince(i).getAirbase().getFightersInvest();
            if (this.getPlaneCost(i) > CFG.game.getProvince(i).getAirbase().getFightersInvested()) {
               CFG.game.getProvince(i).getAirbase().setFightersInvested(CFG.game.getProvince(i).getAirbase().getFightersInvested() + value);
            }

            if (CFG.game.getProvince(i).getAirbase().getFightersInvested() + value >= this.getPlaneCost(i)) {
               CFG.game.getProvince(i).getAirbase().setFighters(CFG.game.getProvince(i).getAirbase().getFighters() + ThreadLocalRandom.current().nextInt(2, 8));
               CFG.game
                  .getProvince(i)
                  .getAirbase()
                  .setFightersInvested(CFG.game.getProvince(i).getAirbase().getFightersInvested() + value - this.getPlaneCost(i));
            }

            value = this.getBomberCost(i) / 20.0F * CFG.game.getProvince(i).getAirbase().getBombersInvest();
            if (this.getBomberCost(i) > CFG.game.getProvince(i).getAirbase().getBombersInvested()) {
               CFG.game.getProvince(i).getAirbase().setBombersInvested(CFG.game.getProvince(i).getAirbase().getBombersInvested() + value);
            }

            if (CFG.game.getProvince(i).getAirbase().getBombersInvested() + value >= this.getBomberCost(i)) {
               CFG.game.getProvince(i).getAirbase().setBombers(CFG.game.getProvince(i).getAirbase().getBombers() + ThreadLocalRandom.current().nextInt(2, 8));
               CFG.game
                  .getProvince(i)
                  .getAirbase()
                  .setBombersInvested(CFG.game.getProvince(i).getAirbase().getBombersInvested() + value - this.getBomberCost(i));
            }

            value = this.getHelicopterCost(i) / 15.0F * CFG.game.getProvince(i).getAirbase().getHelicoptersInvest();
            if (this.getHelicopterCost(i) > CFG.game.getProvince(i).getAirbase().getHelicoptersInvested()) {
               CFG.game.getProvince(i).getAirbase().setHelicoptersInvested(CFG.game.getProvince(i).getAirbase().getHelicoptersInvested() + value);
            }

            if (CFG.game.getProvince(i).getAirbase().getHelicoptersInvested() + value >= this.getHelicopterCost(i)) {
               CFG.game
                  .getProvince(i)
                  .getAirbase()
                  .setHelicopters(CFG.game.getProvince(i).getAirbase().getHelicopters() + ThreadLocalRandom.current().nextInt(2, 8));
               CFG.game
                  .getProvince(i)
                  .getAirbase()
                  .setHelicoptersInvested(CFG.game.getProvince(i).getAirbase().getHelicoptersInvested() + value - this.getHelicopterCost(i));
            }
         }
      }
   }

   public final float getInflation(int nCivID) {
      if (CFG.game.getCiv(nCivID).getMoney() < 0L) {
         return 0.0F;
      } else {
         try {
            return (float)CFG.game.getCiv(nCivID).getMoney()
                     / (
                        (INFLATION_PEAK_VALUE * 1.1275F + (CFG.game.getCiv(nCivID).iIncomeTaxation + CFG.game.getCiv(nCivID).iIncomeProduction) * 0.4F)
                           * 18.12746F
                     )
                  > 0.235F
               ? 1.0F
                  + (float)CFG.game.getCiv(nCivID).getMoney()
                     * ((float)CFG.game.getCiv(nCivID).getMoney() / (INFLATION_PEAK_VALUE * 18.12746F) - 0.235F)
                     * 0.0679248F
               : 0.0F;
         } catch (ArithmeticException var3) {
            return 0.0F;
         }
      }
   }

   public final float getInflationPerc(int nCivID) {
      return Math.max(this.getInflation(nCivID) / (float)CFG.game.getCiv(nCivID).getMoney(), 0.0F);
   }

   public final float getInterestCost(int nCivID) {
      return CFG.game.getCiv(nCivID).getMoney() < 0L
         ? Math.min((float)Math.abs(CFG.game.getCiv(nCivID).getMoney()) * 0.01274F, Math.abs(CFG.game.getCiv(nCivID).iBudget * 0.075F))
         : 0.0F;
   }

   public final float getAdministrationCost_Update(int nCivID) {
      float tempTotal = 0.0F;

      try {
         for (int i = 0; i < CFG.game.getCiv(nCivID).getNumOfProvinces(); i++) {
            tempTotal += this.getProvinceAdministration(CFG.game.getCiv(nCivID).getProvinceID(i), CFG.game_NextTurnUpdate.getAdministration_Capital(nCivID));
         }
      } catch (IndexOutOfBoundsException var4) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var4);
         }
      }

      return tempTotal;
   }

   public final float getProvinceAdministration(int nProvinceID, int nCapital) {
      return (float)Math.pow(
            CFG.game.getProvince(nProvinceID).getEconomy()
                  * Math.min(
                     1.0F, (float)CFG.game.getProvince(nProvinceID).getEconomy() / CFG.game.getProvince(nProvinceID).getPopulationData().getPopulation()
                  )
                  * 0.003248F
               + CFG.game.getProvince(nProvinceID).getPopulationData().getPopulation()
                  * (0.0024F + 7.25E-4F * CFG.game.getProvince(nProvinceID).getDevelopmentLevel()),
            0.93478
         )
         * (
            1.0F
               + (
                     this.getDistanceFromCapital_PercOfMax(nCapital, nProvinceID)
                           / (1.5275F + CFG.game.getProvince(nProvinceID).getProvinceStability() / 8.0F)
                           * CFG.gameAges.getAge_AdministrationCost_Distance(Game_Calendar.CURRENT_AGEID)
                        + 0.13468F
                        - 0.13468F * CFG.game.getProvince(nProvinceID).getHappiness()
                  )
                  * CFG.ideologiesManager.getIdeology(CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getIdeologyID()).ADMINISTRATION_COST_DISTANCE
         )
         * (
            0.9325F
               + 0.0715F * CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getTaxationLevel()
               + 0.0325F * (1.0F - CFG.game.getProvince(nProvinceID).getProvinceStability())
         )
         * CFG.gameAges.getAge_TreasuryModifier_Administration(Game_Calendar.CURRENT_AGEID)
         * (
            CFG.ideologiesManager.getIdeology(CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getIdeologyID()).ADMINISTRATION_COST
               + CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getModifier_Administation()
         )
         * (
            nProvinceID == nCapital
               ? CFG.ideologiesManager.getIdeology(CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getIdeologyID()).ADMINISTRATION_COST_CAPITAL
               : 1.0F
         )
         * Game_Calendar.GAME_SPEED;
   }

   public final float getDistanceFromCapital(int nCapital, int toProvinceID) {
      try {
         return CFG.map.getMapWorldMap(CFG.map.getActiveMapID())
            ? Math.min(
               Math.min(
                  (float)Math.sqrt(
                     Math.pow(
                           CFG.game.getProvince(toProvinceID).getCenterX_Real()
                              + CFG.map.getMapBG().getWidth_Real()
                              - CFG.game.getProvince(nCapital).getCenterX_Real(),
                           2.0
                        )
                        + Math.pow(CFG.game.getProvince(toProvinceID).getCenterY_Real() - CFG.game.getProvince(nCapital).getCenterY_Real(), 2.0)
                  ),
                  (float)Math.sqrt(
                     Math.pow(
                           CFG.game.getProvince(toProvinceID).getCenterX_Real()
                              - (CFG.game.getProvince(nCapital).getCenterX_Real() + CFG.map.getMapBG().getWidth_Real()),
                           2.0
                        )
                        + Math.pow(CFG.game.getProvince(toProvinceID).getCenterY_Real() - CFG.game.getProvince(nCapital).getCenterY_Real(), 2.0)
                  )
               ),
               (float)Math.sqrt(
                  Math.pow(CFG.game.getProvince(toProvinceID).getCenterX_Real() - CFG.game.getProvince(nCapital).getCenterX_Real(), 2.0)
                     + Math.pow(CFG.game.getProvince(toProvinceID).getCenterY_Real() - CFG.game.getProvince(nCapital).getCenterY_Real(), 2.0)
               )
            )
            : (float)Math.sqrt(
               Math.pow(CFG.game.getProvince(toProvinceID).getCenterX_Real() - CFG.game.getProvince(nCapital).getCenterX_Real(), 2.0)
                  + Math.pow(CFG.game.getProvince(toProvinceID).getCenterY_Real() - CFG.game.getProvince(nCapital).getCenterY_Real(), 2.0)
            );
      } catch (IndexOutOfBoundsException var4) {
         return CFG.map.getMapBG().getMaxDistance();
      }
   }

   public final float getDistanceFromCapital_PercOfMax(int nCapital, int toProvinceID) {
      return this.getDistanceFromCapital(nCapital, toProvinceID) / CFG.map.getMapBG().getMaxDistance();
   }

   public final float getDistanceFromAToB_PercOfMax(int nProvinceA, int nProvinceB) {
      return this.getDistanceFromCapital(nProvinceA, nProvinceB) / CFG.map.getMapBG().getMaxDistance();
   }

   public final float getMilitaryUpkeep_Total(int nCivID) {
      float tempTotal = 0.0F;

      for (int i = 0; i < CFG.game.getCiv(nCivID).getNumOfProvinces(); i++) {
         tempTotal += this.getMilitaryUpkeep(CFG.game.getCiv(nCivID).getProvinceID(i), nCivID);
      }

      for (int var4 = 0; var4 < CFG.game.getCiv(nCivID).getArmyInAnotherProvinceSize(); var4++) {
         tempTotal += this.getMilitaryUpkeep(CFG.game.getCiv(nCivID).getArmyInAnotherProvince(var4), nCivID);
      }

      for (int var5 = 0; var5 < CFG.game.getCiv(nCivID).getMoveUnitsSize(); var5++) {
         tempTotal += this.getMilitaryUpkeep(
            CFG.game.getCiv(nCivID).getMoveUnits(var5).getFromProvinceID(), CFG.game.getCiv(nCivID).getMoveUnits(var5).getNumOfUnits(), nCivID
         );
      }

      for (int var6 = 0; var6 < CFG.game.getCiv(nCivID).getMoveUnitsPlunderSize(); var6++) {
         tempTotal += this.getMilitaryUpkeep(
            CFG.game.getCiv(nCivID).getMoveUnits_Plunder(var6).getFromProvinceID(), CFG.game.getCiv(nCivID).getMoveUnits_Plunder(var6).getNumOfUnits(), nCivID
         );
      }

      for (int var7 = 0; var7 < CFG.game.getCiv(nCivID).getMoveUnitsGenocideSize(); var7++) {
         tempTotal += this.getMilitaryUpkeep(
            CFG.game.getCiv(nCivID).getMoveUnits_Genocide(var7).getFromProvinceID(),
            CFG.game.getCiv(nCivID).getMoveUnits_Genocide(var7).getNumOfUnits(),
            nCivID
         );
      }

      return (int)Math.ceil(tempTotal);
   }

   public final float getMilitaryUpkeep(int nProvinceID, int nCivID) {
      return this.getMilitaryUpkeep(nProvinceID, CFG.game.getProvince(nProvinceID).getArmyCivID(nCivID), nCivID);
   }

   public final float getMilitaryUpkeep_WithAllRecruitmentsInProcess(int nProvinceID, int nArmy, int nCivID) {
      int out = 0;

      for (int i = 0; i < CFG.game.getCiv(nCivID).getRecruitArmySize(); i++) {
         if (CFG.game.getCiv(nCivID).getRecruitArmy(i).getProvinceID() != nProvinceID) {
            out = (int)(
               out
                  + this.getMilitaryUpkeep(
                     CFG.game.getCiv(nCivID).getRecruitArmy(i).getProvinceID(), CFG.game.getCiv(nCivID).getRecruitArmy(i).getArmy(), nCivID
                  )
            );
         }
      }

      return out + this.getMilitaryUpkeep(nProvinceID, nArmy, nCivID);
   }

   public final float getMilitaryUpkeep_WithAllRecruitmentsInProcess_Disband(int nProvinceID, int nArmy, int nCivID) {
      int out = 0;

      for (int i = 0; i < CFG.game.getCiv(nCivID).getRecruitArmySize(); i++) {
         if (CFG.game.getCiv(nCivID).getRecruitArmy(i).getProvinceID() != nProvinceID) {
            out = (int)(
               out
                  + this.getMilitaryUpkeep(
                     CFG.game.getCiv(nCivID).getRecruitArmy(i).getProvinceID(), CFG.game.getCiv(nCivID).getRecruitArmy(i).getArmy(), nCivID
                  )
            );
         }
      }

      return out - this.getMilitaryUpkeep(nProvinceID, nArmy, nCivID);
   }

   protected final float getMilitaryUpkeep(int nProvinceID, int nArmy, int nCivID) {
      return (float)Math.pow(
            nArmy * CFG.gameAges.getAge_MilitaryUpkeep(Game_Calendar.CURRENT_AGEID),
            1.03F - 0.1275F * CFG.game.getProvince(nProvinceID).getDevelopmentLevel() - 0.10479F * CFG.game.getCiv(nCivID).getTechnologyLevel()
         )
         * (1.0F + CFG.terrainTypesManager.getMilitaryUpkeep(CFG.game.getProvince(nProvinceID).getTerrainTypeID()))
         * CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivID).getIdeologyID()).MILITARY_UPKEEP
         * CFG.gameAges.getAge_TreasuryModifier_MilitaryUpkeep(Game_Calendar.CURRENT_AGEID)
         * (
            1.0F
               + (float)CFG.game.getCiv(nCivID).getNumOfProvinces() / CFG.game.getProvincesSize() * 0.425F
               + CFG.game.getCiv(nCivID).getWarWeariness()
               + CFG.game.getCiv(nCivID).getModifier_MilitaryUpkeep()
               - BuildingsManager.getSupply_Bonus(CFG.game.getProvince(nProvinceID).getLevelOfSupply())
         )
         * Game_Calendar.GAME_SPEED
         * (1.0F - this.getMilitaryUpkeep_DefensivePosition(nProvinceID));
   }

   public final float getMilitaryDefen(int nProvinceID) {
      return 1.0F - this.getMilitaryUpkeep_DefensivePosition(nProvinceID);
   }

   protected final float getMilitaryUpkeep_WithoutDefensivePosition(int nProvinceID, int nArmy, int nCivID) {
      return (float)Math.pow(
            nArmy * CFG.gameAges.getAge_MilitaryUpkeep(Game_Calendar.CURRENT_AGEID),
            1.03F - 0.1275F * CFG.game.getProvince(nProvinceID).getDevelopmentLevel() - 0.10479F * CFG.game.getCiv(nCivID).getTechnologyLevel()
         )
         * (1.0F + CFG.terrainTypesManager.getMilitaryUpkeep(CFG.game.getProvince(nProvinceID).getTerrainTypeID()))
         * CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivID).getIdeologyID()).MILITARY_UPKEEP
         * CFG.gameAges.getAge_TreasuryModifier_MilitaryUpkeep(Game_Calendar.CURRENT_AGEID)
         * (
            1.0F
               + (float)CFG.game.getCiv(nCivID).getNumOfProvinces() / CFG.game.getProvincesSize() * 0.425F
               + CFG.game.getCiv(nCivID).getWarWeariness()
               + CFG.game.getCiv(nCivID).getModifier_MilitaryUpkeep()
               - BuildingsManager.getSupply_Bonus(CFG.game.getProvince(nProvinceID).getLevelOfSupply())
         )
         * Game_Calendar.GAME_SPEED;
   }

   public final float getMilitaryUpkeep_DefensivePosition(int nProvinceID) {
      return 0.008F * CFG.game.getProvince(nProvinceID).getDefensivePosition();
   }

   public final float getInvestments_Total(int nCivID, int iBudget) {
      return this.getResearchSpendings(nCivID, iBudget) + this.getInvestmentsSpendings(nCivID, iBudget);
   }

   public final float getResearchSpendings(int nCivID, int iBudget) {
      return iBudget * CFG.game.getCiv(nCivID).getSpendings_Research();
   }

   public final float getGoodsSpendings(int nCivID, int iBudget) {
      return iBudget * CFG.game.getCiv(nCivID).getSpendings_Goods();
   }

   public final float getInvestmentsSpendings(int nCivID, int iBudget) {
      return iBudget * CFG.game.getCiv(nCivID).getSpendings_Investments();
   }

   public final void updateSpendingsOfCiv(int nCivID, int iBudget) {
      if (CFG.game.getCiv(nCivID).getCapitalProvinceID() >= 0 && CFG.game.getCiv(nCivID).getNumOfProvinces() > 0) {
         if (CFG.game.getCiv(nCivID).getMoney() < -500L) {
            CFG.game.getCiv(nCivID).setSpendings_Research(0.0F);
         }

         if (iBudget <= 0) {
            CFG.game.getCiv(nCivID).setSpendings_Goods(0.0F);
            CFG.game.getCiv(nCivID).setSpendings_Research(0.0F);
            CFG.game.getCiv(nCivID).setSpendings_Investments(0.0F);
         }

         int tempMilitary;
         if ((tempMilitary = this.getMilitarySpendings(nCivID, iBudget)) + (int)(CFG.game.getCiv(nCivID).getSpendings_Goods() * 100.0F) > 200) {
            CFG.game.getCiv(nCivID).setSpendings_Goods((200 - tempMilitary) / 100.0F);
         }

         if ((tempMilitary = tempMilitary + (int)(CFG.game.getCiv(nCivID).getSpendings_Goods() * 100.0F))
               + (int)(CFG.game.getCiv(nCivID).getSpendings_Research() * 100.0F)
               + (int)(CFG.game.getCiv(nCivID).getSpendings_Investments() * 100.0F)
            > 200) {
            if (tempMilitary > 200) {
               CFG.game.getCiv(nCivID).setSpendings_Research(0.0F);
               CFG.game.getCiv(nCivID).setSpendings_Investments(0.0F);
               return;
            }

            int overBudget = (int)(CFG.game.getCiv(nCivID).getSpendings_Research() * 100.0F)
               + (int)(CFG.game.getCiv(nCivID).getSpendings_Investments() * 100.0F)
               + tempMilitary
               - 200;
            int tempBef = (int)(CFG.game.getCiv(nCivID).getSpendings_Research() * 100.0F);
            CFG.game.getCiv(nCivID).setSpendings_Research(CFG.game.getCiv(nCivID).getSpendings_Research() - overBudget / 2.0F / 100.0F);
            overBudget -= (int)(tempBef - CFG.game.getCiv(nCivID).getSpendings_Research() * 100.0F);
            if (overBudget < CFG.game.getCiv(nCivID).getSpendings_Investments() * 100.0F) {
               CFG.game.getCiv(nCivID).setSpendings_Investments(CFG.game.getCiv(nCivID).getSpendings_Investments() - overBudget / 100.0F);
            } else {
               CFG.game.getCiv(nCivID).setSpendings_Investments(0.0F);
               int var8;
               CFG.game
                  .getCiv(nCivID)
                  .setSpendings_Research(
                     CFG.game.getCiv(nCivID).getSpendings_Research()
                        - (var8 = overBudget - (int)(CFG.game.getCiv(nCivID).getSpendings_Investments() * 100.0F)) / 100.0F
                  );
            }
         }
      }
   }

   public final void updateCities() {
      for (int i = 1; i < CFG.game.getCivsSize(); i++) {
         this.updateCities(i);
      }
   }

   public final void updateCities(int nCivID) {
      int tempNumOfCities = (int)Math.ceil(CFG.game.getCiv(nCivID).getNumOfProvinces() * CFG.settingsManager.PERCETANGE_OF_CITIES_ON_MAP / 100.0F);
      int tMaxPopulation = 1;
      ArrayList<Integer> tempProvinces = new ArrayList<>();

      for (int i = 0; i < CFG.game.getCiv(nCivID).getNumOfProvinces(); i++) {
         tempProvinces.add(CFG.game.getCiv(nCivID).getProvinceID(i));
         CFG.game
            .getProvince(CFG.game.getCiv(nCivID).getProvinceID(i))
            .setDrawCities(
               CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getLevelOfPort() > 0
                  || CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getLevelOfArmoury() > 0
            );
         if (!CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).isOccupied()
            && tMaxPopulation < CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getPopulationData().getPopulation()) {
            tMaxPopulation = CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getPopulationData().getPopulation();
         }
      }

      for (int var11 = 0; var11 < CFG.game.getCiv(nCivID).getNumOfProvinces(); var11++) {
         for (int j = 0; j < CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(var11)).getCitiesSize(); j++) {
            if (CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(var11)).getCity(j).getCityLevel() != CFG.getEditorCityLevel(0)) {
               CFG.game
                  .getProvince(CFG.game.getCiv(nCivID).getProvinceID(var11))
                  .getCity(j)
                  .setCityLevel(
                     this.getLevelOfCity(
                        tMaxPopulation, CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(var11)).getPopulationData().getPopulation(), j
                     )
                  );
            }
         }
      }

      for (int jx = 0; jx < tempNumOfCities; jx++) {
         int largestProvinceID = 0;
         int largestPopulation = CFG.game.getProvince(tempProvinces.get(largestProvinceID)).getPopulationData().getPopulation();
         int iSize = tempProvinces.size();

         for (int i2 = 1; i2 < iSize; i2++) {
            if (largestPopulation < CFG.game.getProvince(tempProvinces.get(i2)).getPopulationData().getPopulation()) {
               largestProvinceID = i2;
               largestPopulation = CFG.game.getProvince(tempProvinces.get(i2)).getPopulationData().getPopulation();
            }
         }

         CFG.game.getProvince(tempProvinces.get(largestProvinceID)).setDrawCities(true);
         tempProvinces.remove(largestProvinceID);
      }

      if (CFG.game.getCiv(nCivID).getCapitalProvinceID() >= 0) {
         CFG.game.getProvince(CFG.game.getCiv(nCivID).getCapitalProvinceID()).setDrawCities(true);
      }

      tempProvinces.clear();
   }

   public int getLevelOfCity(int nMaxPopulation, int nPopulation, int nCityID) {
      float nScore = (float)nPopulation / nMaxPopulation;
      int out = 4;
      out = nScore >= 0.765F ? 1 : (nScore >= 0.575F ? 2 : (nScore >= 0.325F ? 3 : 4));
      return CFG.getEditorCityLevel(out);
   }

   public final void buildLevelsOfCities() {
      for (int i = 0; i < CFG.game.getProvincesSize(); i++) {
         for (int j = 0; j < CFG.game.getProvince(i).getCitiesSize(); j++) {
            CFG.game.getProvince(i).getCity(j).setCityLevel(CFG.getEditorCityLevel(4));
         }
      }

      for (int var3 = 1; var3 < CFG.game.getCivsSize(); var3++) {
         this.buildLevelsOfCities(var3);
      }
   }

   public final void buildLevelsOfCities(int nCivID) {
      int tMaxPop = 0;

      for (int i = 0; i < CFG.game.getCiv(nCivID).getNumOfProvinces(); i++) {
         if (tMaxPop < CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getPopulationData().getPopulation()) {
            tMaxPop = CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getPopulationData().getPopulation();
         }
      }

      for (int var7 = 0; var7 < CFG.game.getCiv(nCivID).getNumOfProvinces(); var7++) {
         for (int j = 0; j < CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(var7)).getCitiesSize(); j++) {
            CFG.game
               .getProvince(CFG.game.getCiv(nCivID).getProvinceID(var7))
               .getCity(j)
               .setCityLevel(
                  CFG.getCityLevel_Population(tMaxPop, CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(var7)).getPopulationData().getPopulation(), j)
               );
         }
      }

      try {
         CFG.game.getProvince(CFG.game.getCiv(nCivID).getCapitalProvinceID()).getCity(0).setCityLevel(CFG.getEditorCityLevel(0));
      } catch (IndexOutOfBoundsException var5) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var5);
         }
      } catch (NullPointerException var6) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var6);
         }
      }
   }

   private float getPlaneCost(int province) {
      return 100000.0F + 3500.0F * CFG.game.getCiv(CFG.game.getProvince(province).getCivID()).getTechnologyLevel() + 320.0F * Game_Calendar.TURN_ID;
   }

   private float getBomberCost(int province) {
      return 180000.0F + 8000.0F * CFG.game.getCiv(CFG.game.getProvince(province).getCivID()).getTechnologyLevel() + 320.0F * Game_Calendar.TURN_ID;
   }

   private float getHelicopterCost(int province) {
      return 120000.0F + 5400.0F * CFG.game.getCiv(CFG.game.getProvince(province).getCivID()).getTechnologyLevel() + 320.0F * Game_Calendar.TURN_ID;
   }
}
