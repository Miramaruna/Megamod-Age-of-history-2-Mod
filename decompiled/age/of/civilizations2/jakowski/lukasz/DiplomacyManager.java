package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.Gdx;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class DiplomacyManager {
   public static final int COST_OFFER_ALLIANCE = 20;
   public static final int COST_OFFER_IMPROVERELATIONS = 5;
   public static final int COST_OFFER_DECREASERELATIONS = 2;
   public static final int IMPROVERELATIONS_MAX_NUM_OF_TURS = 35;
   public static final int SUSPEND_DIPLOMATIC_RELATIONS_MAX = 50;
   public static final int SUSPEND_DIPLOMATIC_RELATIONS_MIN = 15;
   public static final int COST_OFFER_NONAGGRESSIONPACT = 8;
   public static final int COST_OFFER_DEFENSIVEPACT = 10;
   public static final int COST_OFFER_PROCLAIMINDEPENDENCE = 5;
   public static final int COST_OFFER_FORMUNION = 22;
   public static final int COST_ALLIANCE_LEAVE = 2;
   public static final int COST_OFFER_SUPPORTREBELS = 34;
   public static final int COST_OFFER_TRADEREQUEST = 10;
   public static final int COST_OFFER_LIBERATEAVASSAL = 2;
   public static final int COST_OFFER_VASSALIZATION = 16;
   public static final int COST_OFFER_MILITARYACCESS_ASK = 10;
   public static final int COST_OFFER_MILITARYACCESS_GIVE = 4;
   public static final int COST_OFFER_GIFT = 8;
   public static final int COST_CALL_TO_ARMS = 0;
   public static final int COST_WAR_PREPARATIONS = 0;
   public static final int COST_TAKE_LOAN = 6;
   public static final int COST_ABADON = 0;
   public static final int COST_ULTIMATUM = 24;
   public static final int COST_TRANSFER_CONTROL = 4;
   public static final int COST_INVEST_DEVLOPMENT = 8;
   public static final int INVEST_NUM_OF_TURNS_DEVLOPMENT = 4;
   public static final float COST_INVEST_ECONOMY_PER_GOLD_DEVELOPMENT = 1.075F;
   public static final int COST_INVEST = 12;
   public static final int INVEST_NUM_OF_TURNS = 4;
   public static final float COST_INVEST_ECONOMY_PER_GOLD = 3.5F;
   public static final float COST_INVEST_ECONOMY_PER_GOLD2 = 6.75F;
   public static final int COLONIZE_NEW_COLONY_BONUS = 92;
   public static final int COST_FESTIVAL = 8;
   public static final int BASE_COST_OF_FESTIVAL = 500;
   public static final int FESTIVAL_NUM_OF_TURNS = 7;
   public static final int COST_ASSIMILATE = 6;
   public static final int BASE_COST_OF_ASSIMILATE = 265;
   public static final int ASSIMILATE_NUM_OF_TURNS_MIN = 10;
   public static final int ASSIMILATE_NUM_OF_TURNS_MAX = 50;
   public static final int SUPPORT_REBELS_NUM_OF_TURNS_MAX = 35;
   public static final float SUPPORT_REBELS_ASSIMILATE_COST_MODIFIER = 1.6275F;
   public static final float SUPPORT_REBELS_ASSIMILATE_PERC = 0.845F;
   public static final float SUPPORT_REBELS_ASSIMILATE_PERC_EXTRA = 0.125F;
   public static final int COST_CIVILIZE = 10;
   public static final int DIPLOMAT_COST_ALLIANCE = 6;
   public static final int DIPLOMAT_COST_NONAGGRESSION = 2;
   public static final int DIPLOMAT_COST_GUARANTEE = 1;
   public static final int DIPLOMAT_COST_DEFENSIVE_PACT = 3;
   public static final int DIPLOMAT_COST_FRIENDLY_CIV = 3;
   public static final int DIPLOMAT_COST_MILITARYACCESS = 1;
   public static final int DIPLOMAT_COST_VASSAL = 1;
   public static final int GOLDEN_AGE_EVERY_X_TURNS = 30;
   public static final int CALL_TO_ARMS_RELATION_DENY = -15;
   public static final int CALL_TO_ARMS_RELATION_DENY_INSULT = -20;
   public static final int CALL_TO_ARMS_RELATION_ACCEPT = 10;
   public static int WAR_PREPARATIONS_REFUSE_OPINION_CHANGE = -10;
   public static final float GIFT_MAX_PERC_OF_TREASURY = 0.25F;
   public static final int GIFT_REMOVE_RECEIVED_GIFT_INFO_TURNS = 5;
   public static final int GIFT_REFUSE_OPINION_CHANGE = -8;
   public static final int ULTIMATUM_TRUCE_TURNS = 30;
   public static final int ULTIMATUM_REQUIRED_RELATIONS = -10;
   public static final int PEACETREATY_DEFAULT = 45;
   public static final int PEACETREATY_MIN_DURATION = 30;
   public static final int PEACETREATY_MAX_DURATION = 75;
   public static final int WAR_REPARATIONS_LENGTH = 12;
   public static int RELEASED_VASSAL_MIN_OPINION = 25;
   public static final int OUDATED_RELATIONS = 6;
   public static final int OUDATED_RELATIONS_MAX = 15;
   public static final int OUDATED_RELATIONS_MIN = -20;
   public static final int FRIENDLY_MIN_RELATION = 44;
   public static final int HATED_WAR = 85;
   public static final int HATED_MIN_RELATION = -25;
   public static final int HATED_INSULT = 20;
   public static final int INSULT_DECREASE_RELATIONS = 30;
   public static final int LOAN_MAX_NUM_OF_LOANS = 5;
   public static final int LOAN_MIN_DURATION = 5;
   public static final int LOAN_MAX_DURATION = 30;
   public static final float LOAN_MAX_VALUE_OF_INCOME = 0.6F;
   public static final float PLUNDER_INCOME_MULTIPLY = 1.45F;
   public static final float PLUNDER_INCOME_HIGH_REV_RISK_MODIFIER = 0.625F;

   DiplomacyManager() {
   }

   public static final float getLikelihoodScore(int iScore) {
      return (Math.min(Math.max(iScore, -100), 100) + 100) / 200.0F;
   }

   public static final void worldRecations(int iModifier, int iAgressorCivID, int iCivB) {
      for (int i = 1; i < CFG.game.getCivsSize(); i++) {
         if (CFG.game.getCiv(i).getNumOfProvinces() > 0 && i != iAgressorCivID && i != iCivB && !CFG.game.getCivsAtWar(i, iAgressorCivID)) {
            float tDistance = CFG.game_NextTurnUpdate
               .getDistanceFromAToB_PercOfMax(CFG.game.getCiv(i).getCapitalProvinceID(), CFG.game.getCiv(iCivB).getCapitalProvinceID());
            float out = -(tDistance < 0.375F ? iModifier / 20.0F * (1.0F - tDistance) : 0.0F)
               + iModifier * (-(CFG.game.getCivRelation_OfCivB(i, iCivB) + iModifier / 5) / 100.0F) * Math.max(1.0F - tDistance * 1.35F, 0.01F);
            CFG.game
               .setCivRelation_OfCivB(
                  i,
                  iAgressorCivID,
                  CFG.game.getCivRelation_OfCivB(i, iAgressorCivID) > -100.0F && CFG.game.getCivRelation_OfCivB(i, iAgressorCivID) + out <= -100.0F
                     ? -99.0F
                     : CFG.game.getCivRelation_OfCivB(i, iAgressorCivID) + out
               );
            CFG.game
               .setCivRelation_OfCivB(
                  iAgressorCivID,
                  i,
                  CFG.game.getCivRelation_OfCivB(iAgressorCivID, i) > -100.0F && CFG.game.getCivRelation_OfCivB(iAgressorCivID, i) + out <= -100.0F
                     ? -99.0F
                     : CFG.game.getCivRelation_OfCivB(iAgressorCivID, i) + out
               );
         }
      }
   }

   public static final void sendTransferControl(int iToCivID, int iFromCivID, int iProvinceID) {
      CFG.game.getCiv(iToCivID).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_TransferControl(iFromCivID, iProvinceID));
      CFG.game.getCiv(iFromCivID).setDiplomacyPoints(CFG.game.getCiv(iFromCivID).getDiplomacyPoints() - 0);
      if (!CFG.game.getCiv(iFromCivID).getControlledByPlayer()) {
         CFG.game.getCiv(iFromCivID).addSentMessages(new Civilization_SentMessages(iToCivID, Message_Type.TRANSFER_CONTROL));
      }
   }

   public static final void acceptTransferControl(int iCivID, int iFromCivID, int iValue) {
      if (CFG.game.getProvince(iValue).getCivID() == iFromCivID
         && CFG.game.getProvince(iValue).isOccupied()
         && (
            CFG.game.getCivsAreAllied(iCivID, iFromCivID)
               || CFG.game.getCiv(iCivID).getPuppetOfCivID() == iFromCivID
               || CFG.game.getCiv(iFromCivID).getPuppetOfCivID() == iCivID
               || CFG.game.getProvince(iValue).getTrueOwnerOfProvince() == iCivID
         )) {
         CFG.game
            .getCiv(iFromCivID)
            .getCivilization_Diplomacy_GameData()
            .messageBox
            .addMessage(new Message_TransferControl_Accepted(iCivID, iValue, iFromCivID));
         int oldOwnerArmy = CFG.game.getProvince(iValue).getArmyCivID(iFromCivID);
         int newOwnerArmy = CFG.game.getProvince(iValue).getArmyCivID(iCivID);
         if (oldOwnerArmy != 0) {
            CFG.game.getProvince(iValue).updateArmy(iFromCivID, 0);
         }

         if (newOwnerArmy != 0) {
            CFG.game.getProvince(iValue).updateArmy(iCivID, 0);
         }

         CFG.game.getProvince(iValue).setCivID(iCivID, false, true);
         if (oldOwnerArmy > 0) {
            CFG.game.getProvince(iValue).updateArmy(iFromCivID, oldOwnerArmy);
         }

         if (newOwnerArmy > 0) {
            CFG.game.getProvince(iValue).updateArmy(iCivID, newOwnerArmy);
         }
      }
   }

   public static final void declineTransferControl(int iCivID, int iFromCivID, int iValue) {
      if (CFG.game.getProvince(iValue).getCivID() == iFromCivID
         && CFG.game.getProvince(iValue).isOccupied()
         && (
            CFG.game.getCivsAreAllied(iCivID, iFromCivID)
               || CFG.game.getCiv(iCivID).getPuppetOfCivID() == iFromCivID
               || CFG.game.getCiv(iFromCivID).getPuppetOfCivID() == iCivID
         )) {
         CFG.game
            .getCiv(iFromCivID)
            .getCivilization_Diplomacy_GameData()
            .messageBox
            .addMessage(new Message_TransferControl_Refused(iCivID, iValue, iFromCivID));
      }
   }

   public static final float invest_DevelopmentByGold(int nProvinceID, int nMoney) {
      return nMoney
         / (CFG.game.getGameScenarios().getScenario_StartingPopulation() * 1.075F)
         * (0.375F + 0.625F * (CFG.gameAges.getAge_Economy_GrowthRate(Game_Calendar.CURRENT_AGEID) * 100.0F));
   }

   public static final int invest_MaxDevelopment_Gold(int nProvinceID, int nCivID) {
      return (int)Math.max(
         Math.min(
            Math.min(
                  CFG.game.getCiv(nCivID).getTechnologyLevel() + 0.01F - CFG.game.getProvince(nProvinceID).getDevelopmentLevel(),
                  Math.max(CFG.game.getProvince(nProvinceID).getDevelopmentLevel(), 0.1F) * 0.725F
               )
               * (
                  CFG.game.getGameScenarios().getScenario_StartingPopulation()
                     * 1.075F
                     * (0.375F + 1.2F * (CFG.gameAges.getAge_Economy_GrowthRate(Game_Calendar.CURRENT_AGEID) * 100.0F))
               ),
            (float)CFG.game.getCiv(nCivID).getMoney()
         ),
         0.0F
      );
   }

   public static final boolean investDevelopment(int nProvinceID, int nCivID, int nMoney) {
      if (CFG.game.getProvince(nProvinceID).getCivID() == nCivID && CFG.game.getCiv(nCivID).getMovePoints() >= 8) {
         if (CFG.game.getCiv(nCivID).getMoney() < nMoney) {
            nMoney = (int)CFG.game.getCiv(nCivID).getMoney();
         }

         float ecoPoints;
         if (nMoney > 0 && (ecoPoints = invest_DevelopmentByGold(nProvinceID, nMoney)) > 0.0F) {
            float ecoPointsPerTurn = Math.max(ecoPoints / 4.0F, 1.0E-5F);
            if (CFG.game.getCiv(nCivID).addInvest_Development(new CivInvest_Development(nProvinceID, 4, ecoPoints, ecoPointsPerTurn))) {
               CFG.game.getCiv(nCivID).setMovePoints(CFG.game.getCiv(nCivID).getMovePoints() - 8);
               CFG.game.getCiv(nCivID).setMoney(CFG.game.getCiv(nCivID).getMoney() - nMoney);
               return true;
            }
         }
      }

      return false;
   }

   public static final int invest_EconomyByGold(int nProvinceID, int nMoney) {
      return (int)(
         nMoney
            / 3.5F
            * (0.875F + 0.125F * Math.min(1.0F, CFG.game.getProvince(nProvinceID).getDevelopmentLevel() * 1.75F))
            * (0.375F + 0.625F * CFG.gameAges.getAge_Economy_GrowthRate(Game_Calendar.CURRENT_AGEID) * 10.0F)
      );
   }

   public static final int invest_MaxEconomy(int nProvinceID, int nCivID) {
      return (int)Math.min(
         CFG.game.getProvince(nProvinceID).getEconomy() * 0.375F, CFG.game.getProvince(nProvinceID).getPopulationData().getPopulation() * 0.26854F
      );
   }

   public static final int invest_MaxEconomy_Gold(int nProvinceID, int nCivID) {
      return Math.max(
         (int)Math.min(
            Math.min(CFG.game.getProvince(nProvinceID).getEconomy() * 0.325F, CFG.game.getProvince(nProvinceID).getPopulationData().getPopulation() * 0.665F)
               * (0.65F + 10.0F * CFG.game.getProvince(nProvinceID).getDevelopmentLevel())
               * 6.75F,
            (float)CFG.game.getCiv(nCivID).getMoney()
         ),
         0
      );
   }

   public static final int invest_MaxEconomy_Gold_Player(int nProvinceID, int nCivID) {
      return Math.max(
         (int)Math.min(
            Math.min(CFG.game.getProvince(nProvinceID).getEconomy() * 0.325F, CFG.game.getProvince(nProvinceID).getPopulationData().getPopulation() * 0.665F)
               * (0.65F + 0.35F * CFG.game.getProvince(nProvinceID).getDevelopmentLevel())
               * 6.75F,
            (float)CFG.game.getCiv(nCivID).getMoney()
         ),
         0
      );
   }

   public static final boolean invest(int nProvinceID, int nCivID, int nMoney) {
      if (CFG.game.getProvince(nProvinceID).getCivID() == nCivID && CFG.game.getCiv(nCivID).getMovePoints() >= 12) {
         if (CFG.game.getCiv(nCivID).getMoney() < nMoney) {
            nMoney = (int)CFG.game.getCiv(nCivID).getMoney();
         }

         int ecoPoints;
         if (nMoney > 0 && (ecoPoints = invest_EconomyByGold(nProvinceID, nMoney)) > 0) {
            int ecoPointsPerTurn = Math.max(ecoPoints / 10, 1);
            if (CFG.game.getCiv(nCivID).addInvest(new CivInvest(nProvinceID, 10, ecoPoints, ecoPointsPerTurn))) {
               CFG.game.getCiv(nCivID).setMovePoints(CFG.game.getCiv(nCivID).getMovePoints() - 12);
               CFG.game.getCiv(nCivID).setMoney(CFG.game.getCiv(nCivID).getMoney() - nMoney);
               return true;
            }
         }
      }

      return false;
   }

   public static final boolean canMoveToNaighbooringProvince(int nProvinceID, int nCivID) {
      return !Game_Calendar.ENABLE_COLONIZATION_NEUTRAL_PROVINCES
         || CFG.game.getProvince(nProvinceID).getSeaProvince()
         || CFG.game.getProvince(nProvinceID).getCivID() > 0
         || CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivID).getIdeologyID()).CAN_BECOME_CIVILIZED >= 0;
   }

   public static final int getColonizeCost(int nProvinceID, int nCivID) {
      return (int)(
         CFG.game.getGameScenarios().getScenario_StartingPopulation()
            * (
               CFG.gameAges.getAge(Game_Calendar.CURRENT_AGEID).COLONIZE_COST_GOLD_PERC
                  + 0.0845F * CFG.game.getProvince(nProvinceID).getGrowthRate_Population()
                  + 0.1325F
                     * (
                        CFG.game.getCiv(nCivID).getCapitalProvinceID() >= 0
                           ? 3.475F * CFG.game_NextTurnUpdate.getDistanceFromCapital_PercOfMax(CFG.game.getCiv(nCivID).getCapitalProvinceID(), nProvinceID)
                           : 1.0F
                     )
            )
            * getColonizeCost_OwnNeighboringProvincesModifier(nProvinceID, nCivID)
            * getColonizeCost_ContinentAndRegion_Modifier(nProvinceID, nCivID)
            * (1.0F - CFG.game.getCiv(nCivID).civGameData.fModifier_ColonizationCost)
            * (
               CFG.game.getCiv(nCivID).getTechnologyLevel() < Game_Calendar.COLONIZATION_TECH_LEVEL
                  ? 2.675F + (Game_Calendar.COLONIZATION_TECH_LEVEL - CFG.game.getCiv(nCivID).getTechnologyLevel()) * 8.25F
                  : 1.0F
            )
      );
   }

   public static final int getColonizeCost_AI(int nCivID) {
      return (int)(
         CFG.game.getGameScenarios().getScenario_StartingPopulation()
            * (CFG.gameAges.getAge(Game_Calendar.CURRENT_AGEID).COLONIZE_COST_GOLD_PERC + 0.021125F + 0.0795F)
            * (1.0F - CFG.game.getCiv(nCivID).civGameData.fModifier_ColonizationCost)
            * (
               CFG.game.getCiv(nCivID).getTechnologyLevel() < Game_Calendar.COLONIZATION_TECH_LEVEL
                  ? 2.675F + (Game_Calendar.COLONIZATION_TECH_LEVEL - CFG.game.getCiv(nCivID).getTechnologyLevel()) * 8.25F
                  : 1.0F
            )
      );
   }

   public static final float getColonizeCost_ContinentAndRegion_Modifier(int nProvinceID, int nCivID) {
      if (CFG.game.getCiv(nCivID).getCapitalProvinceID() >= 0) {
         if (CFG.game.getProvince(CFG.game.getCiv(nCivID).getCapitalProvinceID()).getContinent() == CFG.game.getProvince(nProvinceID).getContinent()) {
            if (CFG.game.getProvince(CFG.game.getCiv(nCivID).getCapitalProvinceID()).getRegion() == CFG.game.getProvince(nProvinceID).getRegion()) {
               return 0.815F;
            }

            return 0.865F;
         }

         if (CFG.game.getProvince(CFG.game.getCiv(nCivID).getCapitalProvinceID()).getRegion() == CFG.game.getProvince(nProvinceID).getRegion()) {
            return 0.915F;
         }
      }

      return 1.0F;
   }

   public static final float getColonizeCost_OwnNeighboringProvincesModifier(int nProvinceID, int nCivID) {
      int ownsNeighboringProvinces = 0;

      for (int i = 0; i < CFG.game.getProvince(nProvinceID).getNeighboringProvincesSize(); i++) {
         if (CFG.game.getProvince(CFG.game.getProvince(nProvinceID).getNeighboringProvinces(i)).getCivID() == nCivID) {
            ownsNeighboringProvinces++;
         }
      }

      return 1.0F - 0.4F * ownsNeighboringProvinces / Math.max(CFG.game.getProvince(nProvinceID).getNeighboringProvincesSize(), 1);
   }

   public static final int getColonizeCost_Movement(int nProvinceID, int nCivID) {
      return (int)Math.min(
         40.0F,
         CFG.gameAges.getAge(Game_Calendar.CURRENT_AGEID).COLONIZE_COST_MOVEMENT_POINTS
            + CFG.gameAges.getAge(Game_Calendar.CURRENT_AGEID).COLONIZE_COST_MOVEMENT_POINTS
               * (
                  CFG.game.getCiv(nCivID).getCapitalProvinceID() >= 0
                     ? 1.6275F * CFG.game_NextTurnUpdate.getDistanceFromCapital_PercOfMax(CFG.game.getCiv(nCivID).getCapitalProvinceID(), nProvinceID)
                     : 2.0F
               )
      );
   }

   public static final boolean colonizeWastelandProvince(int nProvinceID, int nCivID) {
      if (CFG.game.getProvince(nProvinceID).getWasteland() < 0 && CFG.game.getProvince(nProvinceID).getCivID() != 0) {
         return false;
      } else if (CFG.game.getCiv(nCivID).getMovePoints() < getColonizeCost_Movement(nProvinceID, nCivID)) {
         return false;
      } else if (CFG.game.getCiv(nCivID).getDiplomacyPoints() < CFG.gameAges.getAge(Game_Calendar.CURRENT_AGEID).COLONIZE_COST_DIPLOMACY_POINTS) {
         return false;
      } else if (CFG.game.getCiv(nCivID).getMoney() < getColonizeCost(nProvinceID, nCivID)) {
         return false;
      } else if (!CFG.gameAction.canColonizieWasteland_BorderOrArmy(nProvinceID, nCivID)) {
         return false;
      } else {
         boolean wasWasteland = CFG.game.getProvince(nProvinceID).getWasteland() >= 0;
         CFG.game.getCiv(nCivID).setMovePoints(CFG.game.getCiv(nCivID).getMovePoints() - getColonizeCost_Movement(nProvinceID, nCivID));
         CFG.game
            .getCiv(nCivID)
            .setDiplomacyPoints(CFG.game.getCiv(nCivID).getDiplomacyPoints() - CFG.gameAges.getAge(Game_Calendar.CURRENT_AGEID).COLONIZE_COST_DIPLOMACY_POINTS);
         CFG.game.getCiv(nCivID).setMoney(CFG.game.getCiv(nCivID).getMoney() - getColonizeCost(nProvinceID, nCivID));
         CFG.game.getProvince(nProvinceID).setWasteland(-1);
         CFG.game.getProvince(nProvinceID).resetArmies(0);
         CFG.game.getProvince(nProvinceID).setCivID(nCivID, false, true);
         int ranArmy = 5 + CFG.oR.nextInt(15);
         CFG.game.getProvince(nProvinceID).updateArmy(nCivID, ranArmy);
         CFG.game.getCiv(nCivID).setNumOfUnits(CFG.game.getCiv(nCivID).getNumOfUnits() + ranArmy);
         CFG.game.getProvince(nProvinceID).getCore().addNewCore(nCivID, Game_Calendar.TURN_ID);
         CFG.game.getProvince(nProvinceID).setHappiness(Math.max(CFG.game.getProvince(nProvinceID).getHappiness(), (62 + CFG.oR.nextInt(31)) / 100.0F));
         CFG.game
            .getProvince(nProvinceID)
            .setDevelopmentLevel(
               Math.max(
                  CFG.game.getProvince(nProvinceID).getDevelopmentLevel(),
                  CFG.game.getCiv(nCivID).getTechnologyLevel() * (0.125F + CFG.oR.nextInt(100) / 1000.0F)
               )
            );
         CFG.game.getProvince(nProvinceID).saveProvinceData.iNewColonyBonus = 92;
         if (wasWasteland) {
            CFG.game
               .getProvince(nProvinceID)
               .getPopulationData()
               .setPopulationOfCivID(
                  nCivID, Math.max(299 + CFG.oR.nextInt(460), CFG.game.getProvince(nProvinceID).getPopulationData().getPopulationOfCivID(nCivID))
               );
            CFG.game.getProvince(nProvinceID).setEconomy(Math.max(CFG.game.getProvince(nProvinceID).getEconomy(), 42 + CFG.oR.nextInt(76)));
            CFG.game.buildWastelandLevels();
         }

         for (int i = 0; i < CFG.game.getProvince(nProvinceID).getPopulationData().getNationalitiesSize(); i++) {
            if (CFG.game.getProvince(nProvinceID).getPopulationData().getCivID(i) == 0) {
               float randPop = 0.375F + CFG.oR.nextInt(35) / 100.0F;
               CFG.game
                  .getProvince(nProvinceID)
                  .getPopulationData()
                  .setPopulationOfCivID(
                     nCivID,
                     CFG.game.getProvince(nProvinceID).getPopulationData().getPopulationOfCivID(nCivID)
                        + (int)(CFG.game.getProvince(nProvinceID).getPopulationData().getPopulationID(i) * randPop)
                  );
               CFG.game
                  .getProvince(nProvinceID)
                  .getPopulationData()
                  .setPopulationOfCivID(
                     CFG.game.getProvince(nProvinceID).getPopulationData().getCivID(i),
                     CFG.game.getProvince(nProvinceID).getPopulationData().getPopulationID(i)
                        - (int)(CFG.game.getProvince(nProvinceID).getPopulationData().getPopulationID(i) * randPop)
                  );
               break;
            }
         }

         CFG.game.getCiv(nCivID).civGameData.lColonies_Founded.add(new Civilization_Colonies(nProvinceID));
         CFG.oAI.buildProvinceData(nProvinceID);
         if (CFG.game.getActiveProvinceID() == nProvinceID) {
            CFG.game.setActiveProvinceID(-1);
            CFG.game.setActiveProvinceID(nProvinceID);
         }

         try {
            CFG.historyManager.addHistoryLog(new HistoryLog_NewColony(nCivID, nProvinceID));
         } catch (NullPointerException var6) {
         } catch (IndexOutOfBoundsException var7) {
         }

         return true;
      }
   }

   public static final int festivalCost(int nProvinceID) {
      return 500
         + (int)(
            (CFG.game_NextTurnUpdate.getProvinceIncome_Taxation(nProvinceID) + CFG.game_NextTurnUpdate.getProvinceIncome_Production(nProvinceID))
               * (
                  0.6425F
                     + 0.1625F * CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getTechnologyLevel()
                     + 0.2F * CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getFestivalsSize()
               )
         );
   }

   public static final float festivalHappinessPerTurn(int nProvinceID) {
      return 0.0145F + 0.006F * (1.0F - CFG.game.getProvince(nProvinceID).getHappiness());
   }

   public static final float festivalHappinessPerTurn_NeighboringProvinces() {
      return 0.0045F;
   }

   public static final boolean addFestival(int nCivID, int nProvinceID) {
      if (nCivID == CFG.game.getProvince(nProvinceID).getCivID()
         && CFG.game.getCiv(nCivID).getMovePoints() >= 8
         && CFG.game.getCiv(nCivID).getMoney() >= festivalCost(nProvinceID)
         && CFG.game.getCiv(nCivID).addFestival(new CivFestival(nProvinceID, 7))) {
         CFG.game.getCiv(nCivID).setMovePoints(CFG.game.getCiv(nCivID).getMovePoints() - 8);
         CFG.game.getCiv(nCivID).setMoney(CFG.game.getCiv(nCivID).getMoney() - festivalCost(nProvinceID));
         return true;
      } else {
         return false;
      }
   }

   public static final int assimilateCost(int nProvinceID, int numOfTurns) {
      numOfTurns = Math.max(numOfTurns, 0);
      return (int)(
         (
               265
                  + (int)(
                     (
                           CFG.game_NextTurnUpdate.getProvinceIncome_Taxation(nProvinceID) * 0.775F
                              + CFG.game_NextTurnUpdate.getProvinceIncome_Production(nProvinceID) * 0.237F
                        )
                        * (
                           0.665F
                              + 0.412F * CFG.game.getProvince(nProvinceID).getDevelopmentLevel()
                              + 0.0825F * CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getAssimilatesSize()
                        )
                        * (
                           1.0F
                              + CFG.game_NextTurnUpdate
                                 .getDistanceFromAToB_PercOfMax(
                                    CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getCapitalProvinceID(), nProvinceID
                                 )
                        )
                        * (
                           1.625F
                              - (float)CFG.game.getProvince(nProvinceID).getPopulationData().getPopulationOfCivID(CFG.game.getProvince(nProvinceID).getCivID())
                                 / CFG.game.getProvince(nProvinceID).getPopulationData().getPopulation()
                        )
                  )
            )
            / 10.0F
            * numOfTurns
      );
   }

   public static final boolean addAssimilate(int nCivID, int nProvinceID, int numOfTurns) {
      numOfTurns = Math.max(numOfTurns, 0);
      if (nCivID == CFG.game.getProvince(nProvinceID).getCivID()
         && !CFG.game.getProvince(nProvinceID).isOccupied()
         && CFG.game.getCiv(nCivID).getDiplomacyPoints() >= 4
         && CFG.game.getCiv(nCivID).getMoney() >= assimilateCost(nProvinceID, numOfTurns)
         && CFG.game.getCiv(nCivID).addAssimilate(new CivFestival(nProvinceID, numOfTurns))) {
         CFG.game.getCiv(nCivID).setDiplomacyPoints(CFG.game.getCiv(nCivID).getDiplomacyPoints() - 4);
         CFG.game.getCiv(nCivID).setMoney(CFG.game.getCiv(nCivID).getMoney() - Math.abs(assimilateCost(nProvinceID, numOfTurns)));
         return true;
      } else {
         return false;
      }
   }

   public static final SupportRebels_Data supportRebels(int iOnCivID) {
      SupportRebels_Data outCivs = new SupportRebels_Data();

      for (int i = 0; i < CFG.game.getCiv(iOnCivID).getNumOfProvinces(); i++) {
         for (int j = 0; j < CFG.game.getProvince(CFG.game.getCiv(iOnCivID).getProvinceID(i)).getCore().getCivsSize(); j++) {
            if (CFG.game.getCiv(CFG.game.getProvince(CFG.game.getCiv(iOnCivID).getProvinceID(i)).getCore().getCivID(j)).getNumOfProvinces() <= 0) {
               boolean tAdd = true;

               for (int k = 0; k < outCivs.lMovements.size(); k++) {
                  if (outCivs.lMovements.get(k) == CFG.game.getProvince(CFG.game.getCiv(iOnCivID).getProvinceID(i)).getCore().getCivID(j)) {
                     tAdd = false;
                     outCivs.lPopulation
                        .set(
                           k,
                           outCivs.lPopulation.get(k)
                              + CFG.game
                                 .getProvince(CFG.game.getCiv(iOnCivID).getProvinceID(i))
                                 .getPopulationData()
                                 .getPopulationOfCivID(CFG.game.getProvince(CFG.game.getCiv(iOnCivID).getProvinceID(i)).getCore().getCivID(j))
                        );
                     outCivs.lUnrest
                        .set(
                           k, outCivs.lUnrest.get(k) + (int)(CFG.game.getProvince(CFG.game.getCiv(iOnCivID).getProvinceID(i)).getRevolutionaryRisk() * 100.0F)
                        );
                     outCivs.lProvinces.set(k, outCivs.lProvinces.get(k) + 1);
                     break;
                  }
               }

               if (tAdd) {
                  outCivs.lMovements.add(CFG.game.getProvince(CFG.game.getCiv(iOnCivID).getProvinceID(i)).getCore().getCivID(j));
                  outCivs.lPopulation
                     .add(
                        CFG.game
                           .getProvince(CFG.game.getCiv(iOnCivID).getProvinceID(i))
                           .getPopulationData()
                           .getPopulationOfCivID(CFG.game.getProvince(CFG.game.getCiv(iOnCivID).getProvinceID(i)).getCore().getCivID(j))
                     );
                  outCivs.lUnrest.add((int)(CFG.game.getProvince(CFG.game.getCiv(iOnCivID).getProvinceID(i)).getRevolutionaryRisk() * 100.0F));
                  outCivs.lProvinces.add(1);
               }
            }
         }
      }

      return outCivs;
   }

   public static final List<Integer> supportRebels_Provinces(int iOnCivID, int iRebelsID) {
      ArrayList<Integer> outProvinces = new ArrayList<>();

      for (int i = 0; i < CFG.game.getCiv(iOnCivID).getNumOfProvinces(); i++) {
         for (int j = 0; j < CFG.game.getProvince(CFG.game.getCiv(iOnCivID).getProvinceID(i)).getCore().getCivsSize(); j++) {
            if (CFG.game.getProvince(CFG.game.getCiv(iOnCivID).getProvinceID(i)).getCore().getCivID(j) == iRebelsID) {
               outProvinces.add(CFG.game.getCiv(iOnCivID).getProvinceID(i));
               break;
            }
         }
      }

      return outProvinces;
   }

   public static final float getSUPPORT_REBELS_ASSIMILATE_PERC(int iNumOfSupporters) {
      return iNumOfSupporters <= 1 ? 0.845F : 0.845F + 0.125F * Math.min(1.0F, (float)(iNumOfSupporters / 4));
   }

   public static final int supportRebels_MaxGold(List<Integer> nProvinces) {
      int out = 1;
      int iSize = nProvinces.size();

      for (int i = 0; i < iSize; i++) {
         out += (int)(assimilateCost(nProvinces.get(i), 100) * 1.6275F);
      }

      return out * 2;
   }

   public static final boolean supportRebels(int byCivID, int iOnCivID, int supportCivID, int nMoney) {
      if (CFG.game.getCiv(byCivID).getMoney() < nMoney) {
         nMoney = (int)CFG.game.getCiv(byCivID).getMoney();
      }

      if (nMoney <= 0) {
         return false;
      } else if (CFG.game.getCiv(byCivID).getDiplomacyPoints() < 34) {
         return false;
      } else {
         CFG.game.getCiv(byCivID).setDiplomacyPoints(CFG.game.getCiv(byCivID).getDiplomacyPoints() - 34);
         CFG.game.getCiv(byCivID).setMoney(CFG.game.getCiv(byCivID).getMoney() - nMoney);
         ArrayList<Integer> supportedProvinces = new ArrayList<>();
         ArrayList<Integer> supportedPopulation = new ArrayList<>();
         ArrayList<Integer> supportCostPerTurn = new ArrayList<>();
         int supportedPopulationTotal = 0;

         for (int i = 0; i < CFG.game.getCiv(iOnCivID).getNumOfProvinces(); i++) {
            if (CFG.game.getProvince(CFG.game.getCiv(iOnCivID).getProvinceID(i)).getCore().getHaveACore(supportCivID)) {
               supportedProvinces.add(CFG.game.getCiv(iOnCivID).getProvinceID(i));
               supportedPopulation.add(
                  CFG.game.getProvince(CFG.game.getCiv(iOnCivID).getProvinceID(i)).getPopulationData().getPopulationOfCivID(supportCivID) + 1
               );
               supportCostPerTurn.add((int)(assimilateCost(CFG.game.getCiv(iOnCivID).getProvinceID(i), 1) * 1.6275F));
               supportedPopulationTotal += CFG.game
                     .getProvince(CFG.game.getCiv(iOnCivID).getProvinceID(i))
                     .getPopulationData()
                     .getPopulationOfCivID(supportCivID)
                  + 1;
            }
         }

         try {
            CFG.game
               .getCiv(iOnCivID)
               .getCivilization_Diplomacy_GameData()
               .messageBox
               .addMessage(new Message_RebelsSupported(supportCivID, supportedProvinces.get(0)));
         } catch (IndexOutOfBoundsException var13) {
         }

         while (supportedProvinces.size() > 0 && nMoney > 0) {
            int nRandPop = CFG.oR.nextInt(supportedPopulationTotal);
            int currPop = 0;
            int bestSuppProvID = 0;

            for (int ix = 0; ix < supportedProvinces.size(); ix++) {
               if (nRandPop >= currPop && nRandPop <= currPop + supportedPopulation.get(ix)) {
                  bestSuppProvID = ix;
                  break;
               }
            }

            int numOfTunrs;
            if (!(Math.floor(nMoney / supportCostPerTurn.get(bestSuppProvID)) > 0.0)
               || (numOfTunrs = (int)Math.floor(nMoney / supportCostPerTurn.get(bestSuppProvID))) <= 1) {
               break;
            }

            if ((numOfTunrs = 1 + CFG.oR.nextInt(numOfTunrs)) > 60) {
               numOfTunrs = 59;
            }

            Province_SupportRebels_Help outHelp = CFG.game
               .getProvince(supportedProvinces.get(bestSuppProvID))
               .addSupportRebels(new Province_SupportRebels(byCivID, supportCivID, numOfTunrs));
            nMoney -= supportCostPerTurn.get(bestSuppProvID) * outHelp.iTurns;
            if (outHelp.max) {
               supportedPopulationTotal -= supportedPopulation.get(bestSuppProvID);
               supportedProvinces.remove(bestSuppProvID);
               supportedPopulation.remove(bestSuppProvID);
               supportCostPerTurn.remove(bestSuppProvID);
            }
         }

         supportedProvinces.clear();
         supportedPopulation.clear();
         supportedPopulationTotal = 0;

         for (int ixx = 0; ixx < CFG.game.getCiv(iOnCivID).getNumOfProvinces(); ixx++) {
            if (CFG.game.getProvince(CFG.game.getCiv(iOnCivID).getProvinceID(ixx)).getPopulationData().getPopulationOfCivID(supportCivID) > 0) {
               supportedProvinces.add(CFG.game.getCiv(iOnCivID).getProvinceID(ixx));
               supportedPopulation.add(
                  CFG.game.getProvince(CFG.game.getCiv(iOnCivID).getProvinceID(ixx)).getPopulationData().getPopulationOfCivID(supportCivID)
               );
               supportedPopulationTotal += supportedPopulation.get(supportedPopulation.size() - 1);
            }
         }

         float efficiency = nMoney / (supportedPopulationTotal * 100.5F * 7.0F);

         for (int ixxx = 0; ixxx < supportedProvinces.size(); ixxx++) {
            float tempPercOfPopulation = (float)supportedPopulation.get(ixxx).intValue()
               / CFG.game.getProvince(supportedProvinces.get(ixxx)).getPopulationData().getPopulation();
            CFG.game
               .getProvince(supportedProvinces.get(ixxx))
               .setRevolutionaryRisk(
                  CFG.gameAges.getAge_RevolutionaryRiskModifier(Game_Calendar.CURRENT_AGEID)
                        * CFG.game.getProvince(supportedProvinces.get(ixxx)).getRevolutionaryRisk()
                        * 20.0F
                     + 12.0F * efficiency * tempPercOfPopulation * (1.01F - CFG.game.getProvince(supportedProvinces.get(ixxx)).getHappiness())
               );
         }

         return true;
      }
   }

   public static final boolean civilizeCiv(int nCivID) {
      if (CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivID).getIdeologyID()).CAN_BECOME_CIVILIZED >= 0
         && CFG.game.getCiv(nCivID).getDiplomacyPoints() >= 10
         && CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivID).getIdeologyID()).CIVILIZE_TECH_LEVEL <= CFG.game.getCiv(nCivID).getTechnologyLevel()) {
         CFG.game.getCiv(nCivID).setIdeologyID(CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivID).getIdeologyID()).CAN_BECOME_CIVILIZED);
         CFG.game
            .getCiv(nCivID)
            .setCivTag(
               CFG.ideologiesManager.getRealTag(CFG.game.getCiv(nCivID).getCivTag())
                  + CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivID).getIdeologyID()).getExtraTag()
            );
         CFG.unionFlagsToGenerate_Manager.addFlagToLoad(nCivID);
         CFG.game.getCiv(nCivID).setDiplomacyPoints(CFG.game.getCiv(nCivID).getDiplomacyPoints() - 10);
         if (CFG.game.getPlayerID_ByCivID(nCivID) >= 0) {
            CFG.game.getPlayer(CFG.game.getPlayerID_ByCivID(nCivID)).loadPlayersFlag();
         }

         CFG.viewsManager.disableAllViews();

         for (int i = 0; i < CFG.game.getCiv(nCivID).getNumOfProvinces(); i++) {
            CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).setFromCivID(0);
         }

         CFG.palletManager.loadCivilizationStandardColor(nCivID);
         if (CFG.game.getCiv(nCivID).getNumOfNeighboringNeutralProvinces() > 0) {
            ArrayList<Integer> possibleProvinces = new ArrayList<>();

            for (int i = 0; i < CFG.game.getCiv(nCivID).getNumOfProvinces(); i++) {
               for (int j = 0; j < CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getNeighboringProvincesSize(); j++) {
                  possibleProvinces.add(CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getNeighboringProvinces(j));
               }
            }

            if (possibleProvinces.size() > 0) {
               CFG.game.getProvince(possibleProvinces.get(CFG.oR.nextInt(possibleProvinces.size()))).setCivID(nCivID, false);
            }
         }

         return true;
      } else {
         return false;
      }
   }

   public static void sendTechPointsMessages() {
      for (int i = 1; i < CFG.game.getCivsSize(); i++) {
         if (CFG.game.getCiv(i).getNumOfProvinces() > 0) {
            if (CFG.game.getCiv(i).civGameData.skills.getPointsLeft(i) > 0) {
               CFG.game.getCiv(i).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_TechPoints(i));
            }

            CFG.game.getCiv(i).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_OpenBudget(i));
         }
      }
   }

   public static void sendUncivilizedMessages() {
      for (int i = 1; i < CFG.game.getCivsSize(); i++) {
         if (CFG.game.getCiv(i).getControlledByPlayer()
            && CFG.game.getCiv(i).getNumOfProvinces() > 0
            && CFG.ideologiesManager.getIdeology(CFG.game.getCiv(i).getIdeologyID()).CAN_BECOME_CIVILIZED >= 0) {
            CFG.game.getCiv(i).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_Uncivilized(i));
         }
      }
   }

   public static void sendLowHappiness() {
      for (int i = 1; i < CFG.game.getCivsSize(); i++) {
         if (CFG.game.getCiv(i).getNumOfProvinces() >= 0) {
            if (CFG.game.getCiv(i).getHappiness() < 50) {
               CFG.game.getCiv(i).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_LowHappiness(i, 0));
            }

            if (CFG.game.getCiv(i).lProvincesWithLowStability.size() > 0) {
               boolean sendLowStability = false;

               for (int j = CFG.game.getCiv(i).lProvincesWithLowStability.size() - 1; j >= 0; j--) {
                  if (CFG.game.getProvince(CFG.game.getCiv(i).lProvincesWithLowStability.get(j)).getProvinceStability() < 75.0F) {
                     sendLowStability = true;
                     break;
                  }
               }

               if (sendLowStability) {
                  CFG.game.getCiv(i).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_LowStability(i, 0));
               }
            }
         }
      }
   }

   public static final int getCostOfCurrentDiplomaticActions(int nCivID) {
      int out = 0;
      if (CFG.game.getCiv(nCivID).getAllianceID() > 0 && CFG.game.getAlliance(CFG.game.getCiv(nCivID).getAllianceID()).getCivilizationsSize() > 1) {
         out += 6;
      }

      for (int i = 1; i < CFG.game.getCivsSize(); i++) {
         if (CFG.game.getCiv(i).getNumOfProvinces() > 0 && i != nCivID) {
            if (CFG.game.getCivNonAggressionPact(nCivID, i) > 0) {
               out += 2;
            }

            if (CFG.game.getGuarantee(nCivID, i) > 0) {
               out++;
            }

            if (CFG.game.getDefensivePact(nCivID, i) > 0) {
               out += 3;
            }

            if (CFG.game.getMilitaryAccess(nCivID, i) > 0) {
               out++;
            }

            out += 1 * CFG.game.getCiv(nCivID).civGameData.iVassalsSize;
            out += getCostOfFriendlyCivs(nCivID);
         }
      }

      return out;
   }

   public static final int getCostOfFriendlyCivs(int nCivID) {
      return 2 * CFG.game.getCiv(nCivID).getFriendlyCivsSize();
   }

   public static final int getCostOfCurrentDiplomaticActionsUpdate(int nCivID) {
      int out = 0;
      if (CFG.game.getCiv(nCivID).getAllianceID() > 0 && CFG.game.getAlliance(CFG.game.getCiv(nCivID).getAllianceID()).getCivilizationsSize() > 1) {
         out += 6;
      }

      return out;
   }

   public static final void updateGoldenAge() {
      for (int i = 1; i < CFG.game.getCivsSize(); i++) {
         if (CFG.game.getCiv(i).getNumOfProvinces() > 0) {
            CFG.game
               .getCiv(i)
               .setGoldenAge_Prosperity(
                  CFG.game.getCiv(i).getGoldenAge_Prosperity()
                     + (int)(
                        (CFG.game.getCiv(i).getSpendings_Goods() - CFG.ideologiesManager.getIdeology(CFG.game.getCiv(i).getIdeologyID()).getMin_Goods(i))
                           * 100.0F
                     )
                     + (int)(
                        (CFG.game.getCiv(i).getSpendings_Investments() - CFG.ideologiesManager.getIdeology(CFG.game.getCiv(i).getIdeologyID()).MIN_INVESTMENTS)
                           * 100.0F
                     )
               );
            CFG.game.getCiv(i).setGoldenAge_Science(CFG.game.getCiv(i).getGoldenAge_Science() + (int)(CFG.game.getCiv(i).getSpendings_Research() * 100.0F));
            CFG.game
               .getCiv(i)
               .setGoldenAge_Military(CFG.game.getCiv(i).getGoldenAge_Military() + CFG.game_NextTurnUpdate.getMilitarySpendings(i, CFG.game.getCiv(i).iBudget));
         }
      }

      if (Game_Calendar.TURN_ID % 30 != 10) {
         if (Game_Calendar.TURN_ID % 30 != 15) {
            if (Game_Calendar.TURN_ID % 30 == 20) {
               if (getNumOfCivsInTheGame() > 7) {
                  int nAverageScore = 0;
                  int nCivs = 0;

                  for (int i7 = 1; i7 < CFG.game.getCivsSize(); i7++) {
                     if (CFG.game.getCiv(i7).getNumOfProvinces() > 0) {
                        nAverageScore += CFG.game.getCiv(i7).getGoldenAge_Prosperity();
                        nCivs++;
                     }
                  }

                  float fAverage = (float)Math.ceil((float)nAverageScore / Math.max(nCivs, 1));
                  ArrayList<Integer> tCivs = new ArrayList<>();
                  int toRand = 0;

                  for (int i6 = 1; i6 < CFG.game.getCivsSize(); i6++) {
                     if (CFG.game.getCiv(i6).getNumOfProvinces() > 0 && CFG.game.getCiv(i6).getGoldenAge_Prosperity() >= fAverage) {
                        toRand += CFG.game.getCiv(i6).getGoldenAge_Prosperity();
                        tCivs.add(i6);
                     }
                  }

                  if (toRand > 0) {
                     toRand = CFG.oR.nextInt(toRand);
                     int counted = 0;

                     for (int var13 = 0; var13 < tCivs.size(); var13++) {
                        if (toRand >= counted && toRand < counted + CFG.game.getCiv(tCivs.get(var13)).getGoldenAge_Prosperity()) {
                           goldenAge_Prosperity(tCivs.get(var13));
                           CFG.game.getCiv(tCivs.get(var13)).setGoldenAge_Prosperity(0);
                           break;
                        }

                        counted += CFG.game.getCiv(tCivs.get(var13)).getGoldenAge_Prosperity();
                     }
                  }

                  tCivs.clear();

                  for (int var14 = 1; var14 < CFG.game.getCivsSize(); var14++) {
                     CFG.game.getCiv(var14).setGoldenAge_Prosperity((int)(CFG.game.getCiv(var14).getGoldenAge_Prosperity() * 0.15F));
                  }
               } else {
                  for (int var10 = 1; var10 < CFG.game.getCivsSize(); var10++) {
                     CFG.game.getCiv(var10).setGoldenAge_Prosperity((int)(CFG.game.getCiv(var10).getGoldenAge_Prosperity() * 0.1F));
                  }
               }
            }
         } else if (getNumOfCivsInTheGame() > 7) {
            int nAverageScore = 0;
            int nCivs = 0;

            for (int i5 = 1; i5 < CFG.game.getCivsSize(); i5++) {
               if (CFG.game.getCiv(i5).getNumOfProvinces() > 0) {
                  nAverageScore += CFG.game.getCiv(i5).getGoldenAge_Military();
                  nCivs++;
               }
            }

            float fAverage = (float)Math.ceil((float)nAverageScore / Math.max(nCivs, 1));
            ArrayList<Integer> tCivs = new ArrayList<>();
            int toRand = 0;

            for (int i4 = 1; i4 < CFG.game.getCivsSize(); i4++) {
               if (CFG.game.getCiv(i4).getNumOfProvinces() > 0 && CFG.game.getCiv(i4).getGoldenAge_Military() >= fAverage) {
                  toRand += CFG.game.getCiv(i4).getGoldenAge_Military();
                  tCivs.add(i4);
               }
            }

            if (toRand > 0) {
               toRand = CFG.oR.nextInt(toRand);
               int counted = 0;

               for (int var16 = 0; var16 < tCivs.size(); var16++) {
                  if (toRand >= counted && toRand < counted + CFG.game.getCiv(tCivs.get(var16)).getGoldenAge_Military()) {
                     goldenAge_Military(tCivs.get(var16));
                     CFG.game.getCiv(tCivs.get(var16)).setGoldenAge_Military(0);
                     break;
                  }

                  counted += CFG.game.getCiv(tCivs.get(var16)).getGoldenAge_Military();
               }
            }

            tCivs.clear();

            for (int var17 = 1; var17 < CFG.game.getCivsSize(); var17++) {
               CFG.game.getCiv(var17).setGoldenAge_Military((int)(CFG.game.getCiv(var17).getGoldenAge_Military() * 0.3F));
            }
         } else {
            for (int var11 = 1; var11 < CFG.game.getCivsSize(); var11++) {
               CFG.game.getCiv(var11).setGoldenAge_Military((int)(CFG.game.getCiv(var11).getGoldenAge_Military() * 0.15F));
            }
         }
      } else if (getNumOfCivsInTheGame() > 7) {
         int nAverageScore = 0;
         int nCivs = 0;

         for (int i3 = 1; i3 < CFG.game.getCivsSize(); i3++) {
            if (CFG.game.getCiv(i3).getNumOfProvinces() > 0) {
               nAverageScore += CFG.game.getCiv(i3).getGoldenAge_Science();
               nCivs++;
            }
         }

         float fAverage = (float)Math.ceil((float)nAverageScore / Math.max(nCivs, 1));
         ArrayList<Integer> tCivs = new ArrayList<>();
         int toRand = 0;

         for (int i2 = 1; i2 < CFG.game.getCivsSize(); i2++) {
            if (CFG.game.getCiv(i2).getNumOfProvinces() > 0 && CFG.game.getCiv(i2).getGoldenAge_Science() >= fAverage) {
               toRand += CFG.game.getCiv(i2).getGoldenAge_Science();
               tCivs.add(i2);
            }
         }

         if (toRand > 0) {
            toRand = CFG.oR.nextInt(toRand);
            int counted = 0;

            for (int var19 = 0; var19 < tCivs.size(); var19++) {
               if (toRand >= counted && toRand < counted + CFG.game.getCiv(tCivs.get(var19)).getGoldenAge_Science()) {
                  goldenAge_Science(tCivs.get(var19));
                  CFG.game.getCiv(tCivs.get(var19)).setGoldenAge_Science(0);
                  break;
               }

               counted += CFG.game.getCiv(tCivs.get(var19)).getGoldenAge_Science();
            }
         }

         tCivs.clear();

         for (int var20 = 1; var20 < CFG.game.getCivsSize(); var20++) {
            CFG.game.getCiv(var20).setGoldenAge_Science((int)(CFG.game.getCiv(var20).getGoldenAge_Science() * 0.3F));
         }
      } else {
         for (int var12 = 1; var12 < CFG.game.getCivsSize(); var12++) {
            CFG.game.getCiv(var12).setGoldenAge_Science((int)(CFG.game.getCiv(var12).getGoldenAge_Science() * 0.15F));
         }
      }
   }

   public static int getNumOfCivsInTheGame() {
      int nCivs = 0;

      for (int i = 1; i < CFG.game.getCivsSize(); i++) {
         if (CFG.game.getCiv(i).getNumOfProvinces() > 0) {
            nCivs++;
         }
      }

      return nCivs;
   }

   public static final void goldenAge_Prosperity(int nCivID) {
      Gdx.app.log("AoC", "PROSPERITY: " + CFG.game.getCiv(nCivID).getCivName());
      CivBonus_GameData nGodlenAge = new CivBonus_GameData();
      nGodlenAge.iTurnsLeft = ThreadLocalRandom.current().nextInt(8, 27);
      nGodlenAge.BONUS_TYPE = CivBonus_Type.GOLDEN_AGE_PROSPERITY;
      nGodlenAge.fModifier_PopGrowth = 0.1F + ThreadLocalRandom.current().nextInt(5, 30) / 100.0F;
      nGodlenAge.fModifier_EconomyGrowth = 0.08F + ThreadLocalRandom.current().nextInt(5, 30) / 100.0F;
      nGodlenAge.fModifier_IncomeTaxation = 0.06F + ThreadLocalRandom.current().nextInt(5, 30) / 100.0F;
      if (CFG.game.getCiv(nCivID).addNewBonus(nGodlenAge)) {
         CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_GoldenAge(nCivID, nGodlenAge.iTurnsLeft));
      }
   }

   public static final void goldenAge_Military(int nCivID) {
      Gdx.app.log("AoC", "MILITARY: " + CFG.game.getCiv(nCivID).getCivName());
      CivBonus_GameData nGodlenAge = new CivBonus_GameData();
      nGodlenAge.iTurnsLeft = ThreadLocalRandom.current().nextInt(8, 25);
      nGodlenAge.BONUS_TYPE = CivBonus_Type.GOLDEN_AGE_MILITARY;
      nGodlenAge.fModifier_AttackBonus = 0.08F + ThreadLocalRandom.current().nextInt(5, 30) / 100.0F;
      nGodlenAge.fModifier_MilitaryUpkeep = -0.14F - ThreadLocalRandom.current().nextInt(5, 30) / 100.0F;
      nGodlenAge.fModifier_MovementPoints = 0.06F + ThreadLocalRandom.current().nextInt(5, 30) / 100.0F;
      if (CFG.game.getCiv(nCivID).addNewBonus(nGodlenAge)) {
         CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_GoldenAgeMilitary(nCivID, nGodlenAge.iTurnsLeft));
      }
   }

   public static final void goldenAge_Science(int nCivID) {
      Gdx.app.log("AoC", "SCIENCE: " + CFG.game.getCiv(nCivID).getCivName());
      CivBonus_GameData nGodlenAge = new CivBonus_GameData();
      nGodlenAge.iTurnsLeft = ThreadLocalRandom.current().nextInt(8, 25);
      nGodlenAge.BONUS_TYPE = CivBonus_Type.GOLDEN_AGE_SCIENCE;
      nGodlenAge.fModifier_Research = 0.15F + ThreadLocalRandom.current().nextInt(5, 30) / 100.0F;
      nGodlenAge.fModifier_DefenseBonus = 0.1F + ThreadLocalRandom.current().nextInt(5, 30) / 100.0F;
      nGodlenAge.fModifier_IncomeProduction = 0.06F + ThreadLocalRandom.current().nextInt(5, 30) / 100.0F;
      if (CFG.game.getCiv(nCivID).addNewBonus(nGodlenAge)) {
         CFG.game.getCiv(nCivID).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_GoldenAgeScience(nCivID, nGodlenAge.iTurnsLeft));
      }
   }

   public static final void sendAllianceProposal(int iToCivID, int iFromCivID) {
      if (CFG.game.getCiv(iToCivID).getAllianceID() > 0 && CFG.game.getAlliance(CFG.game.getCiv(iToCivID).getAllianceID()).getCivilizationsSize() > 0) {
         CFG.game
            .getCiv(CFG.game.getAlliance(CFG.game.getCiv(iToCivID).getAllianceID()).getCivilization(0))
            .getCivilization_Diplomacy_GameData()
            .messageBox
            .addMessage(new Message(iFromCivID, 0));
      } else {
         CFG.game.getCiv(iToCivID).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message(iFromCivID, 0));
      }

      CFG.game.getCiv(iFromCivID).setDiplomacyPoints(CFG.game.getCiv(iFromCivID).getDiplomacyPoints() - 20);
      if (!CFG.game.getCiv(iFromCivID).getControlledByPlayer()) {
         CFG.game.getCiv(iFromCivID).addSentMessages(new Civilization_SentMessages(iToCivID, Message_Type.JOIN_ALLIANCE));
      }
   }

   public static final void sendDiplomaticAssociationProposal(int iToCivID, int iFromCivID) {
      CFG.game
         .getCiv(iFromCivID)
         .setMoney(
            (long)(
               CFG.game.getCiv(iFromCivID).getMoney()
                  - (
                        CFG.game.getCiv(iToCivID).getNumOfProvinces() * 10000 + CFG.game.getCiv(iToCivID).getNumOfUnits()
                           + CFG.game.getCiv(iToCivID).countPopulation()
                           + CFG.game.getCiv(iToCivID).countEconomy()
                     )
                     / 1.7
            )
         );
      CFG.game.getCiv(iFromCivID).setDiplomacyPoints(CFG.game.getCiv(iFromCivID).getDiplomacyPoints() - 100);
      int numOfProvs = CFG.game.getCiv(iToCivID).getNumOfProvinces();
      int[] provsID = new int[numOfProvs];

      for (int k = 0; k < numOfProvs; k++) {
         CFG.game.getProvince(CFG.game.getCiv(iToCivID).getProvinceID(k)).setTrueOwnerOfProvince(iFromCivID);
         provsID[k] = CFG.game.getCiv(iToCivID).getProvinceID(k);
      }

      for (int l = 0; l < numOfProvs; l++) {
         CFG.game
            .getProvince(provsID[l])
            .getPopulationData()
            .setPopulationOfCivID(
               iToCivID,
               (int)(
                  CFG.game.getProvince(provsID[l]).getPopulationData().getPopulationOfCivID(iToCivID)
                     - CFG.game.getProvince(provsID[l]).getPopulationData().getPopulationOfCivID(iToCivID) * 0.6
               )
            );
         CFG.game
            .getProvince(provsID[l])
            .getPopulationData()
            .setPopulationOfCivID(
               iFromCivID,
               (int)(
                  CFG.game.getProvince(provsID[l]).getPopulationData().getPopulationOfCivID(iToCivID)
                     + CFG.game.getProvince(provsID[l]).getPopulationData().getPopulationOfCivID(iToCivID) * 0.76
               )
            );
         CFG.game.getProvince(provsID[l]).setCivID(iFromCivID, false);
      }
   }

   public static final void declineAllianceProposal(int iCivID, int iFromCivID) {
      CFG.game.getCiv(iFromCivID).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_Alliance_Denied(iCivID));
   }

   public static final void acceptAllianceProposal(int iCivID, int iFromCivID) {
      if (CFG.game.getCiv(iCivID).getAllianceID() == 0 && CFG.game.getCiv(iFromCivID).getAllianceID() == 0) {
         CFG.game.addAlliance(CFG.getRandomAllianceName(0));
         int tempAllianceID = CFG.game.getAlliancesSize() - 1;
         if (CFG.game.getCiv(iCivID).getControlledByPlayer()) {
            CFG.game.getAlliance(tempAllianceID).addCivilization(iCivID);
            CFG.game.getAlliance(tempAllianceID).addCivilization(iFromCivID);
         } else if (CFG.game.getCiv(iFromCivID).getControlledByPlayer()) {
            CFG.game.getAlliance(tempAllianceID).addCivilization(iFromCivID);
            CFG.game.getAlliance(tempAllianceID).addCivilization(iCivID);
         } else {
            CFG.game.getAlliance(tempAllianceID).addCivilization(iCivID);
            CFG.game.getAlliance(tempAllianceID).addCivilization(iFromCivID);
         }

         CFG.game.getCiv(iCivID).setAllianceID(tempAllianceID);
         CFG.game.getCiv(iFromCivID).setAllianceID(tempAllianceID);
         CFG.historyManager.addHistoryLog(new HistoryLog_JoinAlliance(iCivID, tempAllianceID));
         CFG.historyManager.addHistoryLog(new HistoryLog_JoinAlliance(iFromCivID, tempAllianceID));
      } else if (CFG.game.getCiv(iFromCivID).getAllianceID() > 0 && CFG.game.getCiv(iCivID).getAllianceID() == 0) {
         CFG.game.getAlliance(CFG.game.getCiv(iFromCivID).getAllianceID()).addCivilization(iCivID);
         CFG.game.getCiv(iCivID).setAllianceID(CFG.game.getCiv(iFromCivID).getAllianceID());
         CFG.historyManager.addHistoryLog(new HistoryLog_JoinAlliance(iCivID, CFG.game.getCiv(iFromCivID).getAllianceID()));
      } else if (CFG.game.getCiv(iCivID).getAllianceID() > 0 && CFG.game.getCiv(iFromCivID).getAllianceID() == 0) {
         CFG.game.getAlliance(CFG.game.getCiv(iCivID).getAllianceID()).addCivilization(iFromCivID);
         CFG.game.getCiv(iFromCivID).setAllianceID(CFG.game.getCiv(iCivID).getAllianceID());
         CFG.historyManager.addHistoryLog(new HistoryLog_JoinAlliance(iFromCivID, CFG.game.getCiv(iCivID).getAllianceID()));
      } else {
         CFG.game.getAlliance(CFG.game.getCiv(iCivID).getAllianceID()).removeCivilization(iCivID);
         CFG.game.getAlliance(CFG.game.getCiv(iFromCivID).getAllianceID()).addCivilization(iCivID);
         CFG.game.getCiv(iCivID).setAllianceID(CFG.game.getCiv(iFromCivID).getAllianceID());
         CFG.game.getCiv(iCivID).setAllianceID(CFG.game.getCiv(iFromCivID).getAllianceID());
      }

      if (CFG.game.getCiv(iCivID).getControlledByPlayer()) {
         CFG.gameAction.buildFogOfWar(CFG.game.getPlayerID_ByCivID(iCivID));
         CFG.game.getPlayer(CFG.game.getPlayerID_ByCivID(iCivID)).buildMetProvincesAndCivs();
      }

      if (CFG.game.getCiv(iFromCivID).getControlledByPlayer()) {
         CFG.gameAction.buildFogOfWar(CFG.game.getPlayerID_ByCivID(iFromCivID));
         CFG.game.getPlayer(CFG.game.getPlayerID_ByCivID(iFromCivID)).buildMetProvincesAndCivs();
      }

      CFG.game.getCiv(iFromCivID).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_Alliance_Accepted(iCivID));
   }

   public static int getDiplomaticAssociation_Positive(int nCivA, int nCivB) {
      int out = 0;
      out = (int)(
         out
            + (
               CFG.game.getCivRelation_OfCivB(nCivB, nCivA) > 0.0F
                  ? CFG.game.getCivRelation_OfCivB(nCivB, nCivA) / 3.0F
                  : CFG.game.getCivRelation_OfCivB(nCivB, nCivA) * 2.0F
            )
      );
      if (CFG.game.getCivRelation_OfCivB(nCivB, nCivA) > 0.0F) {
         out = (int)(out + CFG.game.getCivRelation_OfCivB(nCivB, nCivA) / 3.0F);
      } else {
         out = (int)(out - CFG.game.getCivRelation_OfCivB(nCivB, nCivA) * 2.0F);
      }

      if (CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivA).getIdeologyID()).GOV_GROUP_ID
         == CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivB).getIdeologyID()).GOV_GROUP_ID) {
         if (CFG.game.getCiv(nCivA).getIdeologyID() == CFG.game.getCiv(nCivB).getIdeologyID()) {
            out += 15;
         }

         out += 5;
      }

      if (CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivA).getIdeologyID()).GOV_GROUP_ID
         != CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivB).getIdeologyID()).GOV_GROUP_ID) {
         out -= 13;
      }

      if (CFG.game.getCiv(nCivA).getIdeologyID() != CFG.game.getCiv(nCivB).getIdeologyID()) {
         out -= 30;
      }

      if (CFG.game.getCiv(nCivA).isAtWar()) {
         out -= 250;
      }

      if (getDiplomaticAssociation_CivStrength(nCivA, nCivB) > 0) {
         out += getDiplomaticAssociation_CivStrength(nCivA, nCivB);
      }

      if (getDiplomaticAssociation_CivStrength(nCivA, nCivB) < 0) {
         out -= getDiplomaticAssociation_CivStrength(nCivA, nCivB);
      }

      if (CFG.game.getCiv(nCivA).getCivilization_Diplomacy_GameData().isEmassyClosed(nCivB)
         || CFG.game.getCiv(nCivB).getCivilization_Diplomacy_GameData().isEmassyClosed(nCivA)) {
         out -= 1000;
      }

      int nNumOfCores = 0;

      for (int i = 0; i < CFG.game.getCiv(nCivA).getNumOfProvinces(); i++) {
         if (CFG.game.getProvince(CFG.game.getCiv(nCivA).getProvinceID(i)).getCore().getHaveACore(nCivB)) {
            nNumOfCores++;
         }
      }

      out -= nNumOfCores > 0 ? Math.min(15 + 5 * (nNumOfCores - 1), 50) : 0;
      out -= CFG.game.getCiv(nCivA).getPuppetOfCivID() != nCivA && CFG.game.getCiv(nCivA).getPuppetOfCivID() != nCivB ? 250 : 0;
      float minDistance = 1.0F;

      for (int ix = 0; ix < CFG.game.getCiv(nCivA).getNumOfProvinces(); ix++) {
         for (int j = 0; j < CFG.game.getCiv(nCivB).getNumOfProvinces(); j++) {
            minDistance = Math.min(
               minDistance,
               CFG.game_NextTurnUpdate.getDistanceFromAToB_PercOfMax(CFG.game.getCiv(nCivA).getProvinceID(ix), CFG.game.getCiv(nCivB).getProvinceID(j))
            );
         }
      }

      return out - (int)(CFG.gameAges.getAge_DistanceDiplomacy(Game_Calendar.CURRENT_AGEID) * 10.0F * minDistance);
   }

   public static int getDiplomaticAssociation_CivStrength(int nCivA, int nCivB) {
      return (int)(
         CFG.game.getCiv(nCivB).civGameData.civPersonality.RESPONSE_ALLIANCE_STRENTGH / 2.0F
            + CFG.game.getCiv(nCivB).civGameData.civPersonality.RESPONSE_ALLIANCE_STRENTGH
               / 2.0F
               * Math.min((float)CFG.game.getCiv(nCivA).getRankScore() / CFG.game.getCiv(nCivB).getRankScore(), 2.0F)
      );
   }

   public static int getAllianceProposal_Positive(int nCivA, int nCivB) {
      int out = 0;
      out += getAllianceProposal_Positive_Opinion(nCivA, nCivB);
      out += getAllianceProposal_Positive_Goverment(nCivA, nCivB);
      if (getAllianceProposale_CivStrength(nCivA, nCivB) > 0) {
         out += getAllianceProposale_CivStrength(nCivA, nCivB);
      }

      return out + getAllianceProposal_Positive_HRE(nCivA, nCivB);
   }

   public static int getAllianceProposal_Negative(int nCivA, int nCivB) {
      int out = 0;
      out += getAllianceProposal_Negative_Goverment(nCivA, nCivB);
      out += getAllianceProposal_Negative_HRE(nCivA, nCivB);
      out += getAllianceProposal_Negative_PowerfulAllies(nCivA, nCivB);
      out += getAllianceProposal_Negative_PowerfulAllies(nCivB, nCivA);
      out += getAllianceProposal_Negative_CivIsAtWar(nCivA);
      out += getAllianceProposal_Negative_EmbassyClosed(nCivA, nCivB);
      out += getAllianceProposal_Negative_HaveACore(nCivA, nCivB);
      out += getAllianceProposal_Negative_IsAVassal(nCivA, nCivB);
      out += getAllianceProposal_Negative_Distance(nCivA, nCivB) * 2;
      if (getAllianceProposale_CivStrength(nCivA, nCivB) < 0) {
         out += getAllianceProposale_CivStrength(nCivA, nCivB);
      }

      return out;
   }

   public static int getAllianceProposal_Positive_HRE(int nCivA, int nCivB) {
      return CFG.game.getCiv(nCivA).getIsPartOfHolyRomanEmpire() && CFG.game.getCiv(nCivB).getIsPartOfHolyRomanEmpire() ? 25 : 0;
   }

   public static int getAllianceProposal_Positive_Opinion(int nCivA, int nCivB) {
      return CFG.game.getCivRelation_OfCivB(nCivB, nCivA) - CFG.game.getCiv(nCivB).civGameData.civPersonality.RESPONSE_ALLIANCE_OPINION > 0.0F
         ? (int)((CFG.game.getCivRelation_OfCivB(nCivB, nCivA) - CFG.game.getCiv(nCivB).civGameData.civPersonality.RESPONSE_ALLIANCE_OPINION) / 1.94F)
         : 0;
   }

   public static int getAllianceProposal_Positive_Goverment(int nCivA, int nCivB) {
      if (CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivA).getIdeologyID()).GOV_GROUP_ID
         == CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivB).getIdeologyID()).GOV_GROUP_ID) {
         return CFG.game.getCiv(nCivA).getIdeologyID() == CFG.game.getCiv(nCivB).getIdeologyID() ? 6 : 2;
      } else {
         return 0;
      }
   }

   public static int getAllianceProposale_CivStrength(int nCivA, int nCivB) {
      return (int)(
         -CFG.game.getCiv(nCivB).civGameData.civPersonality.RESPONSE_ALLIANCE_STRENTGH / 2.0F
            + CFG.game.getCiv(nCivB).civGameData.civPersonality.RESPONSE_ALLIANCE_STRENTGH
               / 2.0F
               * Math.min((float)CFG.game.getCiv(nCivA).getRankScore() / CFG.game.getCiv(nCivB).getRankScore(), 2.0F)
      );
   }

   public static int getAllianceProposal_Negative_Opinion(int nCivA, int nCivB) {
      return CFG.game.getCivRelation_OfCivB(nCivB, nCivA) - CFG.game.getCiv(nCivB).civGameData.civPersonality.RESPONSE_ALLIANCE_OPINION < 0.0F
         ? (int)(
            (CFG.game.getCivRelation_OfCivB(nCivB, nCivA) - CFG.game.getCiv(nCivB).civGameData.civPersonality.RESPONSE_ALLIANCE_OPINION) / 2.0F
               - (CFG.game.getCivRelation_OfCivB(nCivB, nCivA) < 0.0F ? 5 : 0)
         )
         : 0;
   }

   public static int getAllianceProposal_Negative_HRE(int nCivA, int nCivB) {
      return (!CFG.game.getCiv(nCivA).getIsPartOfHolyRomanEmpire() || CFG.game.getCiv(nCivB).getIsPartOfHolyRomanEmpire())
            && (CFG.game.getCiv(nCivA).getIsPartOfHolyRomanEmpire() || !CFG.game.getCiv(nCivB).getIsPartOfHolyRomanEmpire())
         ? 0
         : -6;
   }

   public static int getAllianceProposal_Negative_Goverment(int nCivA, int nCivB) {
      if (CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivA).getIdeologyID()).GOV_GROUP_ID
         == CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivB).getIdeologyID()).GOV_GROUP_ID) {
         return 0;
      } else {
         return CFG.game.getCiv(nCivA).getIdeologyID() != CFG.game.getCiv(nCivB).getIdeologyID() ? -25 : 0;
      }
   }

   public static int getAllianceProposal_Negative_PowerfulAllies(int nCivA, int nCivB) {
      int out = 0;
      return 0;
   }

   public static int getAllianceProposal_Negative_CivIsAtWar(int nCivA) {
      return CFG.game.getCiv(nCivA).isAtWar() ? -250 : 0;
   }

   public static int getAllianceProposal_Negative_EmbassyClosed(int nCivA, int nCivB) {
      return !CFG.game.getCiv(nCivA).getCivilization_Diplomacy_GameData().isEmassyClosed(nCivB)
            && !CFG.game.getCiv(nCivB).getCivilization_Diplomacy_GameData().isEmassyClosed(nCivA)
         ? 0
         : -1000;
   }

   public static int getAllianceProposal_Negative_HaveACore(int nCivA, int nCivB) {
      int nNumOfCores = 0;

      for (int i = 0; i < CFG.game.getCiv(nCivA).getNumOfProvinces(); i++) {
         if (CFG.game.getProvince(CFG.game.getCiv(nCivA).getProvinceID(i)).getCore().getHaveACore(nCivB)) {
            nNumOfCores++;
         }
      }

      return nNumOfCores > 0 ? -Math.min(15 + 5 * (nNumOfCores - 1), 30) : 0;
   }

   public static int getAllianceProposal_Negative_IsAVassal(int nCivA, int nCivB) {
      return CFG.game.getCiv(nCivA).getPuppetOfCivID() != nCivA && CFG.game.getCiv(nCivA).getPuppetOfCivID() != nCivB ? -250 : 0;
   }

   public static int getAllianceProposal_Negative_Distance(int nCivA, int nCivB) {
      float minDistance = 1.0F;

      for (int i = 0; i < CFG.game.getCiv(nCivA).getNumOfProvinces(); i++) {
         for (int j = 0; j < CFG.game.getCiv(nCivB).getNumOfProvinces(); j++) {
            minDistance = Math.min(
               minDistance,
               CFG.game_NextTurnUpdate.getDistanceFromAToB_PercOfMax(CFG.game.getCiv(nCivA).getProvinceID(i), CFG.game.getCiv(nCivB).getProvinceID(j))
            );
         }
      }

      return (int)(-CFG.gameAges.getAge_DistanceDiplomacy(Game_Calendar.CURRENT_AGEID) * minDistance);
   }

   public static final void joinAWar(int iCivID, int iFromCivID, int iValue) {
      int tWarID = CFG.game.getWarID(iFromCivID, iValue);
      CFG.game.joinWar(iCivID, iValue, tWarID);
      if (CFG.game.getCivsAtWar(iCivID, iValue)) {
         CFG.game.getCiv(iFromCivID).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_AllyJoinedAWar(iCivID, iValue, iFromCivID));
         CFG.game.setCivRelation_OfCivB(iCivID, iFromCivID, CFG.game.getCivRelation_OfCivB(iCivID, iFromCivID) + 5.0F);
         CFG.game.setCivRelation_OfCivB(iFromCivID, iCivID, CFG.game.getCivRelation_OfCivB(iFromCivID, iCivID) + 5.0F);
      }
   }

   public static final List<Integer> callToArmsListOfCivs(int byCivID, int onCivID) {
      ArrayList<Integer> alliesToCall = new ArrayList<>();
      int tWarID = CFG.game.getWarID(byCivID, onCivID);
      if (CFG.game.getCiv(byCivID).getAllianceID() > 0) {
         for (int i = 0; i < CFG.game.getAlliance(CFG.game.getCiv(byCivID).getAllianceID()).getCivilizationsSize(); i++) {
            if (CFG.game.getCiv(CFG.game.getAlliance(CFG.game.getCiv(byCivID).getAllianceID()).getCivilization(i)).getNumOfProvinces() > 0
               && CFG.game.getAlliance(CFG.game.getCiv(byCivID).getAllianceID()).getCivilization(i) != byCivID
               && !CFG.game.getCivsAtWar(CFG.game.getAlliance(CFG.game.getCiv(byCivID).getAllianceID()).getCivilization(i), onCivID)) {
               alliesToCall.add(CFG.game.getAlliance(CFG.game.getCiv(byCivID).getAllianceID()).getCivilization(i));
            }
         }
      }

      for (int ix = 0; ix < CFG.game.getCiv(byCivID).civGameData.iVassalsSize; ix++) {
         if (!CFG.game.getWar(tWarID).getIsInDefenders(CFG.game.getCiv(byCivID).civGameData.lVassals.get(ix).iCivID)
            && !CFG.game.getWar(tWarID).getIsAggressor(CFG.game.getCiv(byCivID).civGameData.lVassals.get(ix).iCivID)
            && CFG.game.getCiv(CFG.game.getCiv(byCivID).civGameData.lVassals.get(ix).iCivID).getNumOfProvinces() > 0) {
            boolean wasAdded = false;

            for (int j = 0; j < alliesToCall.size(); j++) {
               if (alliesToCall.get(j) == CFG.game.getCiv(byCivID).civGameData.lVassals.get(ix).iCivID) {
                  wasAdded = true;
                  break;
               }
            }

            if (!wasAdded) {
               alliesToCall.add(CFG.game.getCiv(byCivID).civGameData.lVassals.get(ix).iCivID);
            }
         }
      }

      if (CFG.game.getCiv(byCivID).getCivID() != CFG.game.getCiv(byCivID).getPuppetOfCivID()
         && !CFG.game.getWar(tWarID).getIsInDefenders(CFG.game.getCiv(byCivID).getPuppetOfCivID())
         && !CFG.game.getWar(tWarID).getIsAggressor(CFG.game.getCiv(byCivID).getPuppetOfCivID())
         && CFG.game.getCiv(CFG.game.getCiv(byCivID).getPuppetOfCivID()).getNumOfProvinces() > 0) {
         boolean wasAdded = false;

         for (int jx = 0; jx < alliesToCall.size(); jx++) {
            if (alliesToCall.get(jx) == CFG.game.getCiv(byCivID).getPuppetOfCivID()) {
               wasAdded = true;
               break;
            }
         }

         if (!wasAdded) {
            alliesToCall.add(CFG.game.getCiv(byCivID).getPuppetOfCivID());
         }
      }

      return alliesToCall;
   }

   public static final void sendCallToArms(int iToCivID, int iFromCivID, int warAgainstCivID) {
      CFG.game.getCiv(iToCivID).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_CallToArms(iFromCivID, warAgainstCivID));
      CFG.game.getCiv(iFromCivID).setDiplomacyPoints(CFG.game.getCiv(iFromCivID).getDiplomacyPoints() - 20);
      if (!CFG.game.getCiv(iFromCivID).getControlledByPlayer()) {
         CFG.game.getCiv(iFromCivID).addSentMessages(new Civilization_SentMessages(iToCivID, Message_Type.WAR_DECLARED_ON_ALLY));
      }
   }

   public static final void acceptCallToArms(int iCivID, int iFromCivID, int iValue) {
      int tWarID = CFG.game.getWarID(iFromCivID, iValue);
      CFG.game.joinWar(iCivID, iValue, tWarID);
      if (CFG.game.getCivsAtWar(iCivID, iValue)) {
         CFG.game.getCiv(iFromCivID).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_CallToArms_Join(iCivID, iValue, iFromCivID));
         CFG.game.setCivRelation_OfCivB(iCivID, iFromCivID, CFG.game.getCivRelation_OfCivB(iCivID, iFromCivID) + 10.0F);
         CFG.game.setCivRelation_OfCivB(iFromCivID, iCivID, CFG.game.getCivRelation_OfCivB(iFromCivID, iCivID) + 10.0F);
      }
   }

   public static final void declineCallToArms(int iCivID, int iFromCivID, int iValue) {
      if (!CFG.game.getCivsAtWar(iCivID, iValue)) {
         CFG.game.getCiv(iFromCivID).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_CallToArms_Deny(iCivID, iValue, iFromCivID));
         CFG.game
            .setCivRelation_OfCivB(
               iCivID,
               iFromCivID,
               CFG.game.getCivRelation_OfCivB(iCivID, iFromCivID) > -100.0F && CFG.game.getCivRelation_OfCivB(iCivID, iFromCivID) + -15.0F <= -100.0F
                  ? -99.0F
                  : CFG.game.getCivRelation_OfCivB(iCivID, iFromCivID) + -15.0F
            );
         CFG.game
            .setCivRelation_OfCivB(
               iFromCivID,
               iCivID,
               CFG.game.getCivRelation_OfCivB(iFromCivID, iCivID) > -100.0F && CFG.game.getCivRelation_OfCivB(iFromCivID, iCivID) + -15.0F <= -100.0F
                  ? -99.0F
                  : CFG.game.getCivRelation_OfCivB(iFromCivID, iCivID) + -15.0F
            );
      }
   }

   public static final void callToArms_Denied_SendInsult(int iCivID, int iFromCivID, int iValue) {
      decreaseRelation(iCivID, iFromCivID, 15);
   }

   public static final void sendPrepareForWar(int iToCivID, int iFromCivID, int warAgainstCivID, int numOfTurns, int iLeaderCivID) {
      CFG.game
         .getCiv(iToCivID)
         .getCivilization_Diplomacy_GameData()
         .messageBox
         .addMessage(new Message_PrepareForWar(iFromCivID, warAgainstCivID, Game_Calendar.TURN_ID + numOfTurns, iLeaderCivID));
      CFG.game.getCiv(iFromCivID).setDiplomacyPoints(CFG.game.getCiv(iFromCivID).getDiplomacyPoints() - 0);
      if (!CFG.game.getCiv(iFromCivID).getControlledByPlayer()) {
         CFG.game.getCiv(iFromCivID).addSentMessages(new Civilization_SentMessages(iToCivID, Message_Type.PREPARE_FOR_WAR));
      }
   }

   public static final void sendCasusBelli(int iToCivID, int iFromCivID, int numOfTurns) {
      CFG.game.getCiv(iFromCivID).civGameData.civPlans.addNewCasusBelli(iToCivID, iFromCivID, numOfTurns);
      CFG.game.getCiv(iToCivID).civGameData.civPlans.addNewCasusBelli(iToCivID, iFromCivID, numOfTurns);
      CFG.game.getCiv(iToCivID).setDiplomacyPoints(CFG.game.getCiv(iFromCivID).getDiplomacyPoints() - 20);
      if (!CFG.game.getCiv(iFromCivID).getControlledByPlayer()) {
         CFG.game.getCiv(iFromCivID).addSentMessages(new Civilization_SentMessages(iToCivID, Message_Type.CASUSBELLI));
      }
   }

   public static final int CasusBelliTurns(int iToCivID, int iFromCivID) {
      return (int)Math.min(
         ThreadLocalRandom.current().nextInt(3, 8)
            + (CFG.game.getCiv(iFromCivID).getNumOfProvinces() * 0.1 + CFG.game.getCiv(iToCivID).getNumOfProvinces() * 0.85) / 15.0,
         30.0
      );
   }

   public static final void acceptPrepareForWar(int iLeaderCivID, int iCivID, int iFromCivID, int warAgainstCivID, int numOfTurns) {
      CFG.game.getCiv(iFromCivID).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_PrepareForWar_Accepted(iCivID, warAgainstCivID));
      CFG.game.getCiv(iFromCivID).civGameData.civPlans.addNewWarPreparations(iLeaderCivID, iFromCivID, warAgainstCivID, numOfTurns);
      CFG.game.getCiv(iCivID).civGameData.civPlans.addNewWarPreparations(iLeaderCivID, iCivID, warAgainstCivID, numOfTurns);
   }

   public static final void declinePrepareForWar(int iLeaderCivID, int iCivID, int iFromCivID, int warAgainstCivID, int numOfTurns) {
      CFG.game.getCiv(iFromCivID).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_PrepareForWar_Refused(iCivID, warAgainstCivID));
      CFG.game
         .setCivRelation_OfCivB(
            iCivID,
            iFromCivID,
            CFG.game.getCivRelation_OfCivB(iCivID, iFromCivID) > -100.0F
                  && CFG.game.getCivRelation_OfCivB(iCivID, iFromCivID) + WAR_PREPARATIONS_REFUSE_OPINION_CHANGE <= -100.0F
               ? -99.0F
               : CFG.game.getCivRelation_OfCivB(iCivID, iFromCivID) + WAR_PREPARATIONS_REFUSE_OPINION_CHANGE
         );
      CFG.game
         .setCivRelation_OfCivB(
            iFromCivID,
            iCivID,
            CFG.game.getCivRelation_OfCivB(iFromCivID, iCivID) > -100.0F
                  && CFG.game.getCivRelation_OfCivB(iFromCivID, iCivID) + WAR_PREPARATIONS_REFUSE_OPINION_CHANGE <= -100.0F
               ? -99.0F
               : CFG.game.getCivRelation_OfCivB(iFromCivID, iCivID) + WAR_PREPARATIONS_REFUSE_OPINION_CHANGE
         );
   }

   public static final void sendUnionProposal(int iToCivID, int iFromCivID) {
      CFG.game.getCiv(iToCivID).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_Union(iFromCivID, 0));
      CFG.game.getCiv(iFromCivID).setDiplomacyPoints(CFG.game.getCiv(iFromCivID).getDiplomacyPoints() - 22);
      if (!CFG.game.getCiv(iFromCivID).getControlledByPlayer()) {
         CFG.game.getCiv(iFromCivID).addSentMessages(new Civilization_SentMessages(iToCivID, Message_Type.UNION));
      }
   }

   public static final void acceptUnionProposal(int iCivID, int iFromCivID) {
      if (iCivID != iFromCivID && CFG.game.getCiv(iCivID).getNumOfProvinces() > 0 && CFG.game.getCiv(iFromCivID).getNumOfProvinces() > 0) {
         CFG.game.getCiv(iCivID).civGameData.numOfUnions++;
         CFG.game.getCiv(iFromCivID).civGameData.numOfUnions++;
         CFG.createUnion(iCivID, iFromCivID);
         CFG.game.getCiv(iFromCivID).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_Union_Accepted(iCivID, 0));
         CFG.game.getCiv(iCivID).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_Union_Accepted(iFromCivID, 0));
      }
   }

   public static final void declineUnionProposal(int iCivID, int iFromCivID) {
      CFG.game.getCiv(iFromCivID).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_Union_Refused(iCivID, 0));
   }

   public static final void sendNonAggressionProposal(int iToCivID, int iFromCivID, int iValue) {
      CFG.game.getCiv(iToCivID).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_NonAggressionPact(iFromCivID, iValue));
      CFG.game.getCiv(iFromCivID).setDiplomacyPoints(CFG.game.getCiv(iFromCivID).getDiplomacyPoints() - 8);
      if (!CFG.game.getCiv(iFromCivID).getControlledByPlayer()) {
         CFG.game.getCiv(iFromCivID).addSentMessages(new Civilization_SentMessages(iToCivID, Message_Type.NONAGGRESSIONPACT));
      }
   }

   public static final void acceptNonAggressionPact(int iCivID, int iFromCivID, int iValue) {
      CFG.game.setCivNonAggressionPact(iCivID, iFromCivID, iValue);
      CFG.game.getCiv(iFromCivID).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_NonAggressionPact_Accepted(iCivID));
      CFG.historyManager.addHistoryLog(new HistoryLog_SignedNonAggressionPact(iFromCivID, iCivID));
   }

   public static final void declineNonAggressionPact(int iCivID, int iFromCivID, int iValue) {
      CFG.game.getCiv(iFromCivID).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_NonAggressionPact_Denied(iCivID));
   }

   public static final void sendOfferVasalizationProposal(int iToCivID, int iFromCivID, int iValue) {
      CFG.game.getCiv(iToCivID).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_OfferVasalization(iFromCivID, iValue));
      CFG.game.getCiv(iFromCivID).setDiplomacyPoints(CFG.game.getCiv(iFromCivID).getDiplomacyPoints() - 16);
      if (!CFG.game.getCiv(iFromCivID).getControlledByPlayer()) {
         CFG.game.getCiv(iFromCivID).addSentMessages(new Civilization_SentMessages(iToCivID, Message_Type.OFFERVASALIZATION));
      }
   }

   public static final void acceptOfferVasalization(int iCivID, int iFromCivID, int iValue) {
      CFG.game.getCiv(iCivID).setPuppetOfCivID(iFromCivID);
      int tPlayerID;
      if (CFG.game.getCiv(iFromCivID).getControlledByPlayer() && CFG.FOG_OF_WAR > 0 && (tPlayerID = CFG.game.getPlayerID_ByCivID(iFromCivID)) >= 0) {
         for (int i = 0; i < CFG.game.getCiv(iCivID).getNumOfProvinces(); i++) {
            CFG.game.getProvince(CFG.game.getCiv(iCivID).getProvinceID(i)).updateFogOfWar(tPlayerID);
         }
      }

      CFG.game
         .updateCivilizationIdeology(
            iFromCivID,
            CFG.ideologiesManager.getRealTag(CFG.game.getCiv(iFromCivID).getCivTag())
               + CFG.ideologiesManager.getIdeology(CFG.game.getCiv(iCivID).getIdeologyID()).getExtraTag()
         );
      CFG.unionFlagsToGenerate_Manager.addFlagToLoad(iFromCivID);
      int r = CFG.game.getCiv(iFromCivID).getR();
      int g = CFG.game.getCiv(iFromCivID).getG();
      int b = CFG.game.getCiv(iFromCivID).getB();
      r = Math.max(0, (int)(r * 0.85));
      g = Math.max(0, (int)(g * 0.85));
      b = Math.max(0, (int)(b * 0.85));
      CFG.game.getCiv(iCivID).setR(r);
      CFG.game.getCiv(iCivID).setG(g);
      CFG.game.getCiv(iCivID).setB(b);
      if (CFG.game.getCiv(iCivID).getAllianceID() > 0) {
         CFG.game.getAlliance(CFG.game.getCiv(iCivID).getAllianceID()).removeCivilization(iCivID);
         CFG.game.getCiv(iCivID).setAllianceID(0);
      }

      CFG.game.getCiv(iFromCivID).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_Vassalization_Accepted(iCivID));
      CFG.historyManager.addHistoryLog(new HistoryLog_IsVassal(iFromCivID, iCivID));
   }

   public static final void declineOfferVasalization(int iCivID, int iFromCivID, int iValue) {
      CFG.game.getCiv(iFromCivID).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_Vassalization_Rejected(iCivID));
   }

   public static final void sendMilitaryAccess_AskProposal(int iToCivID, int iFromCivID, int iValue) {
      if (CFG.game.getCiv(iFromCivID).getDiplomacyPoints() >= 10) {
         CFG.game.getCiv(iToCivID).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_MilitaryAccess_Ask(iFromCivID, iValue));
         CFG.game.getCiv(iFromCivID).setDiplomacyPoints(CFG.game.getCiv(iFromCivID).getDiplomacyPoints() - 10);
         if (!CFG.game.getCiv(iFromCivID).getControlledByPlayer()) {
            CFG.game.getCiv(iFromCivID).addSentMessages(new Civilization_SentMessages(iToCivID, Message_Type.MILITARY_ACCESS_ASK));
         }
      }
   }

   public static final void acceptMilitaryAccess_Ask(int iCivID, int iFromCivID, int iValue) {
      CFG.game.setMilitaryAccess(iFromCivID, iCivID, iValue);
      if (CFG.game.getCivRelation_OfCivB(iCivID, iFromCivID) > 0.0F) {
         CFG.game
            .setCivRelation_OfCivB(
               iCivID,
               iFromCivID,
               CFG.game.getCivRelation_OfCivB(iCivID, iFromCivID) - Math.max(CFG.game.getCivRelation_OfCivB(iCivID, iFromCivID) / 9.325F, 1.127F)
            );
      }

      if (CFG.game.getCivRelation_OfCivB(iFromCivID, iCivID) > 0.0F) {
         CFG.game
            .setCivRelation_OfCivB(
               iFromCivID,
               iCivID,
               CFG.game.getCivRelation_OfCivB(iFromCivID, iCivID) - Math.max(CFG.game.getCivRelation_OfCivB(iFromCivID, iCivID) / 9.325F, 1.127F)
            );
      }

      CFG.game.getCiv(iFromCivID).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_MilitaryAccess_Ask_Accepted(iCivID));
      CFG.historyManager.addHistoryLog(new HistoryLog_HaveMilitartyAccess(iCivID, iFromCivID));
   }

   public static final void declineMilitaryAccess_Ask(int iCivID, int iFromCivID, int iValue) {
      CFG.game.getCiv(iFromCivID).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_MilitaryAccess_Ask_Denied(iCivID));
   }

   public static final void sendMilitaryAccess_GiveProposal(int iToCivID, int iFromCivID, int iValue) {
      CFG.game.getCiv(iToCivID).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_MilitaryAccess_Give(iFromCivID, iValue));
      CFG.game.getCiv(iFromCivID).setDiplomacyPoints(CFG.game.getCiv(iFromCivID).getDiplomacyPoints() - 4);
      if (!CFG.game.getCiv(iFromCivID).getControlledByPlayer()) {
         CFG.game.getCiv(iFromCivID).addSentMessages(new Civilization_SentMessages(iToCivID, Message_Type.MILITARY_ACCESS_GIVE));
      }
   }

   public static final void acceptMilitaryAccess_Give(int iCivID, int iFromCivID, int iValue) {
      CFG.game.setMilitaryAccess(iCivID, iFromCivID, iValue);
      CFG.historyManager.addHistoryLog(new HistoryLog_HaveMilitartyAccess(iFromCivID, iCivID));
   }

   public static final void declineMilitaryAccess_Give(int iCivID, int iFromCivID, int iValue) {
   }

   public static final void sendGuaranteeIndependence_AskProposal(int iToCivID, int iFromCivID, int iValue) {
      CFG.game.getCiv(iToCivID).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_Independence_Ask(iFromCivID, iValue));
      CFG.game.getCiv(iFromCivID).setDiplomacyPoints(CFG.game.getCiv(iFromCivID).getDiplomacyPoints() - 10);
      if (!CFG.game.getCiv(iFromCivID).getControlledByPlayer()) {
         CFG.game.getCiv(iFromCivID).addSentMessages(new Civilization_SentMessages(iToCivID, Message_Type.GUARANTEE_ASK));
      }
   }

   public static final void acceptGuaranteeIndependence_Ask(int iCivID, int iFromCivID, int iValue) {
      CFG.game.setGuarantee(iFromCivID, iCivID, iValue);
      CFG.game.getCiv(iFromCivID).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_Independence_Ask_Accepted(iCivID));

      try {
         CFG.historyManager.addHistoryLog(new HistoryLog_Guarantee(iCivID, iFromCivID));
      } catch (NullPointerException var4) {
      } catch (IndexOutOfBoundsException var5) {
      }
   }

   public static final void declineGuaranteeIndependence_Ask(int iCivID, int iFromCivID, int iValue) {
      CFG.game.getCiv(iFromCivID).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_Independence_Ask_Denied(iCivID));
   }

   public static final void sendDefensivePactProposal(int iToCivID, int iFromCivID, int iValue) {
      CFG.game.getCiv(iToCivID).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_DefensivePact(iFromCivID, iValue));
      CFG.game.getCiv(iFromCivID).setDiplomacyPoints(CFG.game.getCiv(iFromCivID).getDiplomacyPoints() - 10);
      if (!CFG.game.getCiv(iFromCivID).getControlledByPlayer()) {
         CFG.game.getCiv(iFromCivID).addSentMessages(new Civilization_SentMessages(iToCivID, Message_Type.DEFENSIVEPACT));
      }
   }

   public static final void acceptDefensivePact(int iCivID, int iFromCivID, int iValue) {
      CFG.game.setDefensivePact(iCivID, iFromCivID, iValue);
      CFG.game.getCiv(iFromCivID).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_DefensivePact_Accepted(iCivID));
      CFG.historyManager.addHistoryLog(new HistoryLog_SignedDefensivePact(iFromCivID, iCivID));
   }

   public static final void declineDefensivePact(int iCivID, int iFromCivID, int iValue) {
      CFG.game.getCiv(iFromCivID).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_DefensivePact_Denied(iCivID));
   }

   public static final void sendGift(int iToCivID, int iFromCivID, int iValue) {
      if ((float)CFG.game.getCiv(iFromCivID).getMoney() * 1.0F < iValue) {
         iValue = (int)Math.max(0.0F, (float)CFG.game.getCiv(iFromCivID).getMoney() * 1.0F);
      }

      if (iValue > 0) {
         CFG.game.getCiv(iToCivID).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_Gift(iFromCivID, iValue));
         CFG.game.getCiv(iFromCivID).setMoney(CFG.game.getCiv(iFromCivID).getMoney() - iValue);
         CFG.game.getCiv(iFromCivID).setDiplomacyPoints(CFG.game.getCiv(iFromCivID).getDiplomacyPoints() - 8);
         if (!CFG.game.getCiv(iFromCivID).getControlledByPlayer()) {
            CFG.game.getCiv(iFromCivID).addSentMessages(new Civilization_SentMessages(iToCivID, Message_Type.GIFT));
         }
      }
   }

   public static final void sendGiftMoneyAndManPower(int iToCivID, int iFromCivID, int iValue, int iManPower) {
      if ((float)CFG.game.getCiv(iFromCivID).getMoney() * 1.0F < iValue) {
         iValue = (int)Math.max(0.0F, (float)CFG.game.getCiv(iFromCivID).getMoney() * 1.0F);
      }

      if (iValue > 0 || iManPower > 0) {
         CFG.game.getCiv(iToCivID).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_Gift(iFromCivID, iValue));
         CFG.game.getCiv(iFromCivID).setMoney(CFG.game.getCiv(iFromCivID).getMoney() - iValue);
         CFG.game.getCiv(iFromCivID).setManPower(CFG.game.getCiv(iFromCivID).getManPower() - iManPower);
         CFG.game.getCiv(iToCivID).setManPower(CFG.game.getCiv(iToCivID).getManPower() + iManPower);
         CFG.game.getCiv(iFromCivID).setDiplomacyPoints(CFG.game.getCiv(iFromCivID).getDiplomacyPoints() - 8);
         if (!CFG.game.getCiv(iFromCivID).getControlledByPlayer()) {
            CFG.game.getCiv(iFromCivID).addSentMessages(new Civilization_SentMessages(iToCivID, Message_Type.GIFT));
         }
      }
   }

   public static final void AddUnitsSandbox(int iToCivID, int iFromCivID, int iValue) {
      if ((float)CFG.game.getCiv(iFromCivID).getMoney() * 1.0F < iValue) {
         iValue = (int)Math.max(0.0F, (float)CFG.game.getCiv(iFromCivID).getMoney() * 1.0F);
      }

      if (iValue > 0) {
         for (int i = 0; i < CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).getNumOfProvinces(); i++) {
            CFG.game.getProvince(CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).getProvinceID(i)).updateArmy(iValue);
         }
      }
   }

   public static final void sendGiftSandBox(int iToCivID, int iFromCivID, int iAttackBonus, int iDeferencesBonus) {
      CFG.game.getCiv(iToCivID).setModifier_AttackBonus(iAttackBonus / 100);
      CFG.game.getCiv(iToCivID).setModifier_DefenseBonus(iDeferencesBonus / 100);
   }

   public static final void sendGiftSandBox(int iToCivID, int iFromCivID, int iMoney, int iManPower, int iNuclBomb) {
      if ((float)CFG.game.getCiv(iFromCivID).getMoney() * 1.0F < iMoney) {
         iMoney = (int)Math.max(0.0F, (float)CFG.game.getCiv(iFromCivID).getMoney() * 1.0F);
      }

      if (iMoney > 0) {
         CFG.game.getCiv(iToCivID).setMoney(CFG.game.getCiv(iToCivID).getMoney() + iMoney * 5L);
         CFG.game.getCiv(iToCivID).setManPower(CFG.game.getCiv(iToCivID).getManPower() + iManPower);
         CFG.game.getCiv(iToCivID).setNuclearWeapons(CFG.game.getCiv(iToCivID).getNuclearWeapons() + iNuclBomb);
      }
   }

   public static final void acceptGift(int iCivID, int iFromCivID, int iValue) {
      if (iValue >= 0) {
         CFG.game.getCiv(iCivID).setMoney(CFG.game.getCiv(iCivID).getMoney() + iValue);
         CFG.game.getCiv(iFromCivID).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_Gift_Accepted(iCivID, iValue));
         CFG.game.getCiv(iCivID).civGameData.addGift_Received(iFromCivID);
      }
   }

   public static final void declineGift(int iCivID, int iFromCivID, int iValue) {
      CFG.game.getCiv(iFromCivID).setMoney(CFG.game.getCiv(iFromCivID).getMoney() + iValue);
      CFG.game
         .setCivRelation_OfCivB(
            iCivID,
            iFromCivID,
            CFG.game.getCivRelation_OfCivB(iCivID, iFromCivID) > -100.0F && CFG.game.getCivRelation_OfCivB(iCivID, iFromCivID) + -8.0F <= -100.0F
               ? -99.0F
               : CFG.game.getCivRelation_OfCivB(iCivID, iFromCivID) + -8.0F
         );
      CFG.game
         .setCivRelation_OfCivB(
            iFromCivID,
            iCivID,
            CFG.game.getCivRelation_OfCivB(iFromCivID, iCivID) > -100.0F && CFG.game.getCivRelation_OfCivB(iFromCivID, iCivID) + -8.0F <= -100.0F
               ? -99.0F
               : CFG.game.getCivRelation_OfCivB(iFromCivID, iCivID) + -8.0F
         );
      CFG.game.getCiv(iFromCivID).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_Gift_Refused(iCivID, iValue));
   }

   public static final boolean sendUltimatum(int iToCivID, int iFromCivID, Ultimatum_GameData nUltimatum, int nUnits) {
      Gdx.app.log("AoC", "ULTIMATUM: try send " + CFG.game.getCiv(iFromCivID).getCivName() + " -> " + CFG.game.getCiv(iToCivID).getCivName());
      if (CFG.game.getCiv(iToCivID).getPuppetOfCivID() == iToCivID || CFG.game.getCiv(iToCivID).getPuppetOfCivID() == iFromCivID) {
         if (CFG.game.getCiv(iFromCivID).getDiplomacyPoints() < 24) {
            Gdx.app.log("AoC", "ULTIMATUM: FAILED - not enough diplomacy points");
            return false;
         }

         Gdx.app.log("AoC", "ULTIMATUM: delivered to " + CFG.game.getCiv(iToCivID).getCivName());

         CFG.game.getCiv(iToCivID).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_Ultimatum(iFromCivID, nUltimatum, nUnits));
         CFG.game.getCiv(iFromCivID).setDiplomacyPoints(CFG.game.getCiv(iFromCivID).getDiplomacyPoints() - 24);

         if (!CFG.game.getCiv(iToCivID).getControlledByPlayer() && !CFG.SPECTATOR_MODE) {
            Gdx.app.log("AoC", "ULTIMATUM: requesting instant response");
            CFG.oAI.getAI_Style(CFG.game.getCiv(iToCivID).getAI_Style()).respondToMessages(iToCivID);
            Gdx.app.log("AoC", "ULTIMATUM: instant response processed");
         }
      }

      if (!CFG.game.getCiv(iFromCivID).getControlledByPlayer()) {
         CFG.game.getCiv(iFromCivID).addSentMessages(new Civilization_SentMessages(iToCivID, Message_Type.ULTIMATUM));
      }

      return true;
   }

   public static final void acceptUltimatum(int iToCivID, int iFromCivID, Ultimatum_GameData ultimatum) {
      if (CFG.game.getCiv(iToCivID).getControlledByPlayer()) {
         CFG.toast.setInView(CFG.langManager.get("Ult_Accepted"), CFG.COLOR_TEXT_MODIFIER_POSITIVE);
         CFG.toast.setTimeInView(4000);
      }

      if (CFG.game.getCiv(iFromCivID).getPuppetOfCivID() == iFromCivID || CFG.game.getCiv(iFromCivID).getPuppetOfCivID() == iToCivID) {
         CFG.game.getCiv(iFromCivID).setVassalLiberityDesire(CFG.game.getCiv(iFromCivID).getVassalLiberityDesire() * 1.25F + 18.0F + CFG.oR.nextInt(36));
         if (ultimatum.demandAnexation) {
            ArrayList<Integer> tempProvinces = new ArrayList<>();

            for (int i = 0; i < CFG.game.getCiv(iFromCivID).getNumOfProvinces(); i++) {
               tempProvinces.add(CFG.game.getCiv(iFromCivID).getProvinceID(i));
            }

            for (int var13 = 0; var13 < tempProvinces.size(); var13++) {
               if (CFG.game.getProvince(tempProvinces.get(var13)).getCivID() == iFromCivID
                  && CFG.game.getProvince(tempProvinces.get(var13)).getTrueOwnerOfProvince() == iFromCivID) {
                  int nArmyNewOwnerArmy = CFG.game.getProvince(tempProvinces.get(var13)).getArmyCivID(iToCivID);
                  CFG.game.getProvince(tempProvinces.get(var13)).updateArmy(0);
                  CFG.game.getProvince(tempProvinces.get(var13)).updateArmy(iToCivID, 0);
                  CFG.game.getProvince(tempProvinces.get(var13)).setTrueOwnerOfProvince(iToCivID);
                  CFG.game.getProvince(tempProvinces.get(var13)).setCivID(iToCivID, false);
                  CFG.game.getProvince(tempProvinces.get(var13)).updateArmy(iToCivID, nArmyNewOwnerArmy);

                  for (int j = CFG.game.getProvince(tempProvinces.get(var13)).getCivsSize() - 1; j >= 0; j--) {
                     if (CFG.game.getCiv(CFG.game.getProvince(tempProvinces.get(var13)).getCivID(j)).getPuppetOfCivID() != iToCivID
                        && CFG.game.getCiv(iToCivID).getPuppetOfCivID() != CFG.game.getProvince(tempProvinces.get(var13)).getCivID(j)
                        && (
                           CFG.game.getCiv(CFG.game.getProvince(tempProvinces.get(var13)).getCivID(j)).getAllianceID() <= 0
                              || CFG.game.getCiv(CFG.game.getProvince(tempProvinces.get(var13)).getCivID(j)).getAllianceID()
                                 != CFG.game.getCiv(iToCivID).getAllianceID()
                        )
                        && CFG.game.getMilitaryAccess(CFG.game.getProvince(tempProvinces.get(var13)).getCivID(j), iToCivID) <= 0) {
                        CFG.gameAction
                           .accessLost_MoveArmyToClosetsProvince(CFG.game.getProvince(tempProvinces.get(var13)).getCivID(j), tempProvinces.get(var13));
                     }
                  }
               }
            }

            if (CFG.game.getCiv(iFromCivID).getCapitalProvinceID() >= 0) {
               CFG.game.getProvince(CFG.game.getCiv(iFromCivID).getCapitalProvinceID()).setIsCapital(false);

               for (int var14 = 0; var14 < CFG.game.getProvince(CFG.game.getCiv(iFromCivID).getCapitalProvinceID()).getCitiesSize(); var14++) {
                  if (CFG.game.getProvince(CFG.game.getCiv(iFromCivID).getCapitalProvinceID()).getCity(var14).getCityLevel() == CFG.getEditorCityLevel(0)) {
                     CFG.game.getProvince(CFG.game.getCiv(iFromCivID).getCapitalProvinceID()).getCity(var14).setCityLevel(CFG.getEditorCityLevel(1));
                  }
               }
            }

            CFG.game.getCiv(iFromCivID).buildNumOfUnits();
            tempProvinces.clear();
            CFG.game.buildCivilizationsRegions_TextOver(iFromCivID);
            CFG.game.buildCivilizationsRegions_TextOver(iToCivID);
            CFG.game.getCiv(iFromCivID).setPuppetOfCivID(iFromCivID);
            CFG.historyManager.addHistoryLog(new HistoryLog_Annexation(iFromCivID, iToCivID));
         }

         if (ultimatum.demandVasalization) {
            int r = CFG.game.getCiv(iToCivID).getR();
            int g = CFG.game.getCiv(iToCivID).getG();
            int b = CFG.game.getCiv(iToCivID).getB();
            r = Math.max(0, (int)(r * 0.8));
            g = Math.max(0, (int)(g * 0.8));
            b = Math.max(0, (int)(b * 0.8));
            CFG.game.getCiv(iFromCivID).setR(r);
            CFG.game.getCiv(iFromCivID).setG(g);
            CFG.game.getCiv(iFromCivID).setB(b);
            if (CFG.game.getCiv(iFromCivID).getAllianceID() > 0) {
               CFG.game.getAlliance(CFG.game.getCiv(iFromCivID).getAllianceID()).removeCivilization(iFromCivID);
               CFG.game.getCiv(iFromCivID).setAllianceID(0);
            }

            CFG.game.getCiv(iFromCivID).setPuppetOfCivID(iToCivID);
            int tPlayerID;
            if (CFG.game.getCiv(iToCivID).getControlledByPlayer() && CFG.FOG_OF_WAR > 0 && (tPlayerID = CFG.game.getPlayerID_ByCivID(iToCivID)) >= 0) {
               for (int i = 0; i < CFG.game.getCiv(iFromCivID).getNumOfProvinces(); i++) {
                  CFG.game.getProvince(CFG.game.getCiv(iFromCivID).getProvinceID(i)).updateFogOfWar(tPlayerID);
               }
            }

            CFG.historyManager.addHistoryLog(new HistoryLog_IsVassal(iToCivID, iFromCivID));
         }

         if (ultimatum.demandChangeGoverment) {
            CFG.game
               .updateCivilizationIdeology(
                  iFromCivID,
                  CFG.ideologiesManager.getRealTag(CFG.game.getCiv(iFromCivID).getCivTag())
                     + CFG.ideologiesManager.getIdeology(CFG.game.getCiv(iToCivID).getIdeologyID()).getExtraTag()
               );
            CFG.unionFlagsToGenerate_Manager.addFlagToLoad(iFromCivID);
            CFG.historyManager.addHistoryLog(new HistoryLog_IsVassal(iToCivID, iFromCivID));
         }

         if (ultimatum.demandMilitaryAccess) {
            CFG.game.setMilitaryAccess(iToCivID, iFromCivID, Math.max(CFG.game.getMilitaryAccess(iToCivID, iFromCivID), 40));
            CFG.historyManager.addHistoryLog(new HistoryLog_HaveMilitartyAccess(iFromCivID, iToCivID));
         }

         if (ultimatum.demandLiberation.size() > 0) {
            for (int i2 = 0; i2 < ultimatum.demandLiberation.size(); i2++) {
               liberateAVassal(iFromCivID, ultimatum.demandLiberation.get(i2));
               CFG.game.setCivTruce(iFromCivID, ultimatum.demandLiberation.get(i2), 22);
            }
         }

         if (ultimatum.demandProvinces.size() > 0) {
            for (int i3 = 0; i3 < ultimatum.demandProvinces.size(); i3++) {
               if (CFG.game.getProvince(ultimatum.demandProvinces.get(i3)).getCivID() == iFromCivID
                  && CFG.game.getProvince(ultimatum.demandProvinces.get(i3)).getTrueOwnerOfProvince() == iFromCivID) {
                  ArrayList<Integer> tempCivs = new ArrayList<>();
                  ArrayList<Integer> tempArmies = new ArrayList<>();

                  for (int jx = 0; jx < CFG.game.getProvince(ultimatum.demandProvinces.get(i3)).getCivsSize(); jx++) {
                     tempCivs.add(CFG.game.getProvince(ultimatum.demandProvinces.get(i3)).getCivID(jx));
                     tempArmies.add(CFG.game.getProvince(ultimatum.demandProvinces.get(i3)).getArmy(jx));
                  }

                  int nArmyNewOwnerArmy = CFG.game.getProvince(ultimatum.demandProvinces.get(i3)).getArmyCivID(iToCivID);
                  int nOwnerArmy = CFG.game.getProvince(ultimatum.demandProvinces.get(i3)).getArmy(0);
                  int nOwnerCivID = CFG.game.getProvince(ultimatum.demandProvinces.get(i3)).getCivID();
                  CFG.game.getProvince(ultimatum.demandProvinces.get(i3)).updateArmy(0);
                  CFG.game.getProvince(ultimatum.demandProvinces.get(i3)).updateArmy(iToCivID, 0);
                  CFG.game.getProvince(ultimatum.demandProvinces.get(i3)).setTrueOwnerOfProvince(iToCivID);
                  CFG.game.getProvince(ultimatum.demandProvinces.get(i3)).setCivID(iToCivID, false);
                  if (!CFG.game.getProvince(ultimatum.demandProvinces.get(i3)).getIsCapital()) {
                     CFG.game.getProvince(ultimatum.demandProvinces.get(i3)).removeCapitalCityIcon();
                  }

                  CFG.game.getProvince(ultimatum.demandProvinces.get(i3)).updateArmy(iToCivID, nArmyNewOwnerArmy);
                  CFG.game.getProvince(ultimatum.demandProvinces.get(i3)).updateArmy(nOwnerCivID, nOwnerArmy);

                  for (int j2 = 0; j2 < tempCivs.size(); j2++) {
                     if (CFG.game.getCiv(tempCivs.get(j2)).getPuppetOfCivID() != iToCivID
                        && CFG.game.getCiv(iToCivID).getPuppetOfCivID() != tempCivs.get(j2)
                        && (
                           CFG.game.getCiv(tempCivs.get(j2)).getAllianceID() <= 0
                              || CFG.game.getCiv(tempCivs.get(j2)).getAllianceID() != CFG.game.getCiv(iToCivID).getAllianceID()
                        )
                        && CFG.game.getMilitaryAccess(tempCivs.get(j2), iToCivID) <= 0) {
                        CFG.gameAction.accessLost_MoveArmyToClosetsProvince(tempCivs.get(j2), ultimatum.demandProvinces.get(i3), tempArmies.get(j2));
                     }
                  }
               }
            }

            CFG.game.buildCivilizationsRegions_TextOver(iFromCivID);
            CFG.game.buildCivilizationsRegions_TextOver(iToCivID);
         }

         CFG.game.setCivTruce(iToCivID, iFromCivID, 30);
         CFG.game.getCiv(iToCivID).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_UltimatumAccepted(iFromCivID));
      }
   }

   public static final void refuseUltimatum(int iToCivID, int iFromCivID, Ultimatum_GameData ultimatum) {
      CFG.game.getCiv(iToCivID).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_UltimatumRefused(iFromCivID));
      if (CFG.game.getCiv(iToCivID).getControlledByPlayer()) {
         CFG.toast.setInView(CFG.langManager.get("Ult_Refused"), CFG.COLOR_TEXT_MODIFIER_NEGATIVE2);
         CFG.toast.setTimeInView(4000);
      }
   }

   public static final void refuseUltimatum_AcceptWar(int iFromCivID, int iToCivID) {
      sendCasusBelli(iToCivID, iFromCivID, CasusBelliTurns(iToCivID, iFromCivID));
   }

   public static final void vassalDeclareIndependence_War(int iFromCivID, int iToCivID) {
      sendCasusBelli(iToCivID, iFromCivID, CasusBelliTurns(iToCivID, iFromCivID));
   }

   public static final void vassalDeclareIndependence_Fine(int iFromCivID, int iToCivID) {
      CFG.game.acceptPeaceOffer(iFromCivID, iToCivID, 30);
   }

   public static final void sendPeaceTreaty(boolean toDefenders, int iFromCivID, PeaceTreaty_GameData peaceTreaty_GameData) {
      try {
         CFG.peaceTreatyData.preparePeaceTreatyToSend(iFromCivID);
         CFG.game.lPeaceTreaties.add(new PeaceTreaty_GameData_MessageData(peaceTreaty_GameData));
         String peaceTreatyTag = CFG.game.lPeaceTreaties.get(CFG.game.lPeaceTreaties.size() - 1).PEACE_TREATY_TAG;

         for (int i = 0; i < peaceTreaty_GameData.lCivsDemands_Defenders.size(); i++) {
            if (!peaceTreaty_GameData.lCivsDemands_Defenders.get(i).peaceTreatyAccepted) {
               CFG.game
                  .getCiv(peaceTreaty_GameData.lCivsDemands_Defenders.get(i).iCivID)
                  .getCivilization_Diplomacy_GameData()
                  .messageBox
                  .addMessage(new Message_PeaceTreaty(iFromCivID, peaceTreatyTag));
            }
         }

         for (int var6 = 0; var6 < peaceTreaty_GameData.lCivsDemands_Aggressors.size(); var6++) {
            if (!peaceTreaty_GameData.lCivsDemands_Aggressors.get(var6).peaceTreatyAccepted) {
               CFG.game
                  .getCiv(peaceTreaty_GameData.lCivsDemands_Aggressors.get(var6).iCivID)
                  .getCivilization_Diplomacy_GameData()
                  .messageBox
                  .addMessage(new Message_PeaceTreaty(iFromCivID, peaceTreatyTag));
            }
         }
      } catch (IndexOutOfBoundsException var5) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var5);
         }
      }
   }

   protected static final void acceptPeaceTreaty(int iCivID, String nTag) {
      int peaceID = CFG.game.getPeaceTreaty_GameDataID(nTag);
      boolean everyoneAccepted = true;

      for (int i = 0; i < CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.size(); i++) {
         if (iCivID == CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(i).iCivID) {
            CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(i).peaceTreatyAccepted = true;
         }

         if (!CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(i).peaceTreatyAccepted) {
            everyoneAccepted = false;
         }
      }

      for (int var33 = 0; var33 < CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.size(); var33++) {
         if (iCivID == CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(var33).iCivID) {
            CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(var33).peaceTreatyAccepted = true;
         }

         if (!CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(var33).peaceTreatyAccepted) {
            everyoneAccepted = false;
         }
      }

      if (everyoneAccepted) {
         try {
            for (int var34 = 0; var34 < CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.size(); var34++) {
               for (int j2 = 0; j2 < CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(var34).lDemands.size(); j2++) {
                  CFG.game
                     .getCiv(
                        CFG.game
                           .getProvince(CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(var34).lDemands.get(j2))
                           .getCivID()
                     )
                     .removePlunder_ProvinceID(CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(var34).lDemands.get(j2));
                  int nArmy0 = CFG.game
                     .getProvince(CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(var34).lDemands.get(j2))
                     .getArmy(0);
                  int nCiv0 = CFG.game
                     .getProvince(CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(var34).lDemands.get(j2))
                     .getCivID();
                  int nArmyNewOwner2 = CFG.game
                     .getProvince(CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(var34).lDemands.get(j2))
                     .getArmyCivID(CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(var34).iCivID);
                  int nCivNewOwner2 = CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(var34).iCivID;
                  CFG.game
                     .getProvince(CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(var34).lDemands.get(j2))
                     .updateArmy(0);
                  CFG.game
                     .getProvince(CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(var34).lDemands.get(j2))
                     .updateArmy(CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(var34).iCivID, 0);
                  if (CFG.game
                        .getProvince(CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(var34).lDemands.get(j2))
                        .getCivID()
                     == CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(var34).iCivID) {
                     CFG.timelapseManager
                        .addChange(
                           CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(var34).lDemands.get(j2),
                           CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(var34).iCivID,
                           false
                        );
                  }

                  CFG.game
                     .getProvince(CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(var34).lDemands.get(j2))
                     .setTrueOwnerOfProvince(CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(var34).iCivID);
                  CFG.game
                     .getProvince(CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(var34).lDemands.get(j2))
                     .setCivID(CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(var34).iCivID, false, true);
                  if (!CFG.game
                     .getProvince(CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(var34).lDemands.get(j2))
                     .getIsCapital()) {
                     CFG.game
                        .getProvince(CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(var34).lDemands.get(j2))
                        .removeCapitalCityIcon();
                  }

                  CFG.game
                     .getProvince(CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(var34).lDemands.get(j2))
                     .updateArmy(nCiv0, nArmy0);
                  CFG.game
                     .getProvince(CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(var34).lDemands.get(j2))
                     .updateArmy(nCivNewOwner2, nArmyNewOwner2);
               }

               for (int var59 = 0;
                  var59 < CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(var34).lWarReparationsFromCivsID.size();
                  var59++
               ) {
                  CFG.game
                     .getCiv(CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(var34).iCivID)
                     .addWarReparationsGets(
                        CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(var34).lWarReparationsFromCivsID.get(var59)
                     );
                  CFG.game
                     .getCiv(CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(var34).lWarReparationsFromCivsID.get(var59))
                     .addWarReparationsPay(CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(var34).iCivID);
               }
            }

            for (int var35 = 0; var35 < CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.size(); var35++) {
               for (int j2 = 0; j2 < CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(var35).lDemands.size(); j2++) {
                  CFG.game
                     .getCiv(
                        CFG.game
                           .getProvince(CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(var35).lDemands.get(j2))
                           .getCivID()
                     )
                     .removePlunder_ProvinceID(CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(var35).lDemands.get(j2));
                  int nArmy0x = CFG.game
                     .getProvince(CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(var35).lDemands.get(j2))
                     .getArmy(0);
                  int nCiv0x = CFG.game
                     .getProvince(CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(var35).lDemands.get(j2))
                     .getCivID();
                  int nArmyNewOwner2x = CFG.game
                     .getProvince(CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(var35).lDemands.get(j2))
                     .getArmyCivID(CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(var35).iCivID);
                  int nCivNewOwner2x = CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(var35).iCivID;
                  CFG.game
                     .getProvince(CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(var35).lDemands.get(j2))
                     .updateArmy(0);
                  CFG.game
                     .getProvince(CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(var35).lDemands.get(j2))
                     .updateArmy(CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(var35).iCivID, 0);
                  if (CFG.game
                        .getProvince(CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(var35).lDemands.get(j2))
                        .getCivID()
                     == CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(var35).iCivID) {
                     CFG.timelapseManager
                        .addChange(
                           CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(var35).lDemands.get(j2),
                           CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(var35).iCivID,
                           false
                        );
                  }

                  CFG.game
                     .getProvince(CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(var35).lDemands.get(j2))
                     .setTrueOwnerOfProvince(CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(var35).iCivID);
                  CFG.game
                     .getProvince(CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(var35).lDemands.get(j2))
                     .setCivID(CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(var35).iCivID, false, true);
                  if (!CFG.game
                     .getProvince(CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(var35).lDemands.get(j2))
                     .getIsCapital()) {
                     CFG.game
                        .getProvince(CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(var35).lDemands.get(j2))
                        .removeCapitalCityIcon();
                  }

                  CFG.game
                     .getProvince(CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(var35).lDemands.get(j2))
                     .updateArmy(nCiv0x, nArmy0x);
                  CFG.game
                     .getProvince(CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(var35).lDemands.get(j2))
                     .updateArmy(nCivNewOwner2x, nArmyNewOwner2x);
               }

               for (int var61 = 0;
                  var61 < CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(var35).lWarReparationsFromCivsID.size();
                  var61++
               ) {
                  CFG.game
                     .getCiv(CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(var35).iCivID)
                     .addWarReparationsGets(
                        CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(var35).lWarReparationsFromCivsID.get(var61)
                     );
                  CFG.game
                     .getCiv(CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(var35).lWarReparationsFromCivsID.get(var61))
                     .addWarReparationsPay(CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(var35).iCivID);
               }
            }

            try {
               for (int var36 = 0; var36 < CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.size(); var36++) {
                  for (int j2 = 0;
                     j2 < CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(var36).lWillVassalizeCivsID.size();
                     j2++
                  ) {
                     if (CFG.game
                           .getCiv(CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(var36).lWillVassalizeCivsID.get(j2))
                           .getAllianceID()
                        > 0) {
                        CFG.game
                           .getAlliance(
                              CFG.game
                                 .getCiv(
                                    CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(var36).lWillVassalizeCivsID.get(j2)
                                 )
                                 .getAllianceID()
                           )
                           .removeCivilization(
                              CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(var36).lWillVassalizeCivsID.get(j2)
                           );
                        CFG.game
                           .getCiv(CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(var36).lWillVassalizeCivsID.get(j2))
                           .setAllianceID(0);
                     }

                     CFG.game
                        .updateCivilizationIdeology(
                           CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(var36).lWillVassalizeCivsID.get(j2),
                           CFG.ideologiesManager
                                 .getRealTag(
                                    CFG.game
                                       .getCiv(
                                          CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(var36)
                                             .lWillVassalizeCivsID
                                             .get(j2)
                                       )
                                       .getCivTag()
                                 )
                              + CFG.ideologiesManager
                                 .getIdeology(
                                    CFG.game
                                       .getCiv(CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(var36).iCivID)
                                       .getIdeologyID()
                                 )
                                 .getExtraTag()
                        );
                     CFG.unionFlagsToGenerate_Manager
                        .addFlagToLoad(CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(var36).lWillVassalizeCivsID.get(j2));
                     int r = CFG.game.getCiv(CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(var36).iCivID).getR();
                     int g = CFG.game.getCiv(CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(var36).iCivID).getG();
                     int b = CFG.game.getCiv(CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(var36).iCivID).getB();
                     r = Math.max(0, (int)(r * 0.85));
                     g = Math.max(0, (int)(g * 0.85));
                     b = Math.max(0, (int)(b * 0.85));
                     CFG.game
                        .getCiv(CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(var36).lWillVassalizeCivsID.get(j2))
                        .setR(r);
                     CFG.game
                        .getCiv(CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(var36).lWillVassalizeCivsID.get(j2))
                        .setG(g);
                     CFG.game
                        .getCiv(CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(var36).lWillVassalizeCivsID.get(j2))
                        .setB(b);
                     CFG.game
                        .getCiv(CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(var36).lWillVassalizeCivsID.get(j2))
                        .setPuppetOfCivID(CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(var36).iCivID);
                     CFG.game
                        .setCivRelation_OfCivB(
                           CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(var36).lWillVassalizeCivsID.get(j2),
                           CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(var36).iCivID,
                           Math.max(
                              CFG.game
                                 .getCivRelation_OfCivB(
                                    CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(var36).lWillVassalizeCivsID.get(j2),
                                    CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(var36).iCivID
                                 ),
                              22.0F
                           )
                        );
                     CFG.game
                        .setCivRelation_OfCivB(
                           CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(var36).iCivID,
                           CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(var36).lWillVassalizeCivsID.get(j2),
                           Math.max(
                              CFG.game
                                 .getCivRelation_OfCivB(
                                    CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(var36).iCivID,
                                    CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(var36).lWillVassalizeCivsID.get(j2)
                                 ),
                              22.0F
                           )
                        );
                     CFG.historyManager
                        .addHistoryLog(
                           new HistoryLog_IsVassal(
                              CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(var36).iCivID,
                              CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(var36).lWillVassalizeCivsID.get(j2)
                           )
                        );
                  }
               }
            } catch (IndexOutOfBoundsException var27) {
               CFG.exceptionStack(var27);
            }

            try {
               for (int i4 = 0; i4 < CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.size(); i4++) {
                  for (int j2 = 0;
                     j2 < CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(i4).lWillVassalizeCivsID.size();
                     j2++
                  ) {
                     if (CFG.game
                           .getCiv(CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(i4).lWillVassalizeCivsID.get(j2))
                           .getAllianceID()
                        > 0) {
                        CFG.game
                           .getAlliance(
                              CFG.game
                                 .getCiv(CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(i4).lWillVassalizeCivsID.get(j2))
                                 .getAllianceID()
                           )
                           .removeCivilization(
                              CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(i4).lWillVassalizeCivsID.get(j2)
                           );
                        CFG.game
                           .getCiv(CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(i4).lWillVassalizeCivsID.get(j2))
                           .setAllianceID(0);
                     }

                     CFG.game
                        .updateCivilizationIdeology(
                           CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(i4).lWillVassalizeCivsID.get(j2),
                           CFG.ideologiesManager
                                 .getRealTag(
                                    CFG.game
                                       .getCiv(
                                          CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(i4)
                                             .lWillVassalizeCivsID
                                             .get(j2)
                                       )
                                       .getCivTag()
                                 )
                              + CFG.ideologiesManager
                                 .getIdeology(
                                    CFG.game
                                       .getCiv(CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(i4).iCivID)
                                       .getIdeologyID()
                                 )
                                 .getExtraTag()
                        );
                     CFG.unionFlagsToGenerate_Manager
                        .addFlagToLoad(CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(i4).lWillVassalizeCivsID.get(j2));
                     int r2 = CFG.game.getCiv(CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(i4).iCivID).getR();
                     int g2 = CFG.game.getCiv(CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(i4).iCivID).getG();
                     int b2 = CFG.game.getCiv(CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(i4).iCivID).getB();
                     r2 = Math.max(0, (int)(r2 * 0.8));
                     g2 = Math.max(0, (int)(g2 * 0.8));
                     b2 = Math.max(0, (int)(b2 * 0.8));
                     CFG.game
                        .getCiv(CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(i4).lWillVassalizeCivsID.get(j2))
                        .setR(r2);
                     CFG.game
                        .getCiv(CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(i4).lWillVassalizeCivsID.get(j2))
                        .setG(g2);
                     CFG.game
                        .getCiv(CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(i4).lWillVassalizeCivsID.get(j2))
                        .setB(b2);
                     CFG.game
                        .getCiv(CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(i4).lWillVassalizeCivsID.get(j2))
                        .setPuppetOfCivID(CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(i4).iCivID);
                     CFG.game
                        .setCivRelation_OfCivB(
                           CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(i4).lWillVassalizeCivsID.get(j2),
                           CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(i4).iCivID,
                           Math.max(
                              CFG.game
                                 .getCivRelation_OfCivB(
                                    CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(i4).lWillVassalizeCivsID.get(j2),
                                    CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(i4).iCivID
                                 ),
                              22.0F
                           )
                        );
                     CFG.game
                        .setCivRelation_OfCivB(
                           CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(i4).iCivID,
                           CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(i4).lWillVassalizeCivsID.get(j2),
                           Math.max(
                              CFG.game
                                 .getCivRelation_OfCivB(
                                    CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(i4).iCivID,
                                    CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(i4).lWillVassalizeCivsID.get(j2)
                                 ),
                              22.0F
                           )
                        );
                     CFG.historyManager
                        .addHistoryLog(
                           new HistoryLog_IsVassal(
                              CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(i4).iCivID,
                              CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(i4).lWillVassalizeCivsID.get(j2)
                           )
                        );
                  }
               }
            } catch (IndexOutOfBoundsException var28) {
               CFG.exceptionStack(var28);
            }

            for (int i3 = 0; i3 < CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.size(); i3++) {
               for (int j2 = 0;
                  j2 < CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(i3).lReleasableCivs_TakeControl.size();
                  j2++
               ) {
                  for (int k = 0; k < CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.size(); k++) {
                     if (CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(i3).lReleasableCivs_TakeControl.get(j2).iFromCivID
                        == CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(k).iCivID) {
                        for (int o = 0;
                           o < CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(k).lReleasableCivs.size();
                           o++
                        ) {
                           if (CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(k).lReleasableCivs.get(o).iCivID
                              == CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(i3).lReleasableCivs_TakeControl.get(j2).iVassalCivID
                              )
                            {
                              boolean zeroProvinces = CFG.game
                                    .getCiv(
                                       CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(i3)
                                             .lReleasableCivs_TakeControl
                                             .get(j2)
                                          .iVassalCivID
                                    )
                                    .getNumOfProvinces()
                                 == 0;

                              for (int u = 0;
                                 u
                                    < CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(k).lReleasableCivs.get(o)
                                       .lProvinces
                                       .size();
                                 u++
                              ) {
                                 int tempArmy0 = CFG.game
                                    .getProvince(
                                       CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(k).lReleasableCivs.get(o)
                                          .lProvinces
                                          .get(u)
                                    )
                                    .getArmy(0);
                                 int tempCiv0 = CFG.game
                                    .getProvince(
                                       CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(k).lReleasableCivs.get(o)
                                          .lProvinces
                                          .get(u)
                                    )
                                    .getCivID();
                                 int nArmyNewOwner = CFG.game
                                    .getProvince(
                                       CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(k).lReleasableCivs.get(o)
                                          .lProvinces
                                          .get(u)
                                    )
                                    .getArmyCivID(CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(i3).iCivID);
                                 int nCivNewOwner = CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(i3).iCivID;
                                 CFG.game
                                    .getProvince(
                                       CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(k).lReleasableCivs.get(o)
                                          .lProvinces
                                          .get(u)
                                    )
                                    .updateArmy(0);
                                 CFG.game
                                    .getProvince(
                                       CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(k).lReleasableCivs.get(o)
                                          .lProvinces
                                          .get(u)
                                    )
                                    .updateArmy(nCivNewOwner, 0);
                                 CFG.game
                                    .getProvince(
                                       CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(k).lReleasableCivs.get(o)
                                          .lProvinces
                                          .get(u)
                                    )
                                    .setTrueOwnerOfProvince(
                                       CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(i3)
                                             .lReleasableCivs_TakeControl
                                             .get(j2)
                                          .iVassalCivID
                                    );
                                 CFG.game
                                    .getProvince(
                                       CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(k).lReleasableCivs.get(o)
                                          .lProvinces
                                          .get(u)
                                    )
                                    .setCivID(
                                       CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(i3)
                                             .lReleasableCivs_TakeControl
                                             .get(j2)
                                          .iVassalCivID,
                                       false,
                                       true
                                    );
                                 CFG.game
                                    .getProvince(
                                       CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(k).lReleasableCivs.get(o)
                                          .lProvinces
                                          .get(u)
                                    )
                                    .updateArmy(tempCiv0, tempArmy0);
                                 CFG.game
                                    .getProvince(
                                       CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(k).lReleasableCivs.get(o)
                                          .lProvinces
                                          .get(u)
                                    )
                                    .updateArmy(nCivNewOwner, nArmyNewOwner);
                                 if (zeroProvinces) {
                                    CFG.game
                                       .getCiv(
                                          CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(i3)
                                                .lReleasableCivs_TakeControl
                                                .get(j2)
                                             .iVassalCivID
                                       )
                                       .setPuppetOfCivID(CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(i3).iCivID);
                                    CFG.historyManager
                                       .addHistoryLog(
                                          new HistoryLog_IsVassal(
                                             CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(i3).iCivID,
                                             CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(i3)
                                                   .lReleasableCivs_TakeControl
                                                   .get(j2)
                                                .iVassalCivID
                                          )
                                       );
                                    if (CFG.game
                                          .getCivRelation_OfCivB(
                                             CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(i3)
                                                   .lReleasableCivs_TakeControl
                                                   .get(j2)
                                                .iVassalCivID,
                                             CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(i3).iCivID
                                          )
                                       < RELEASED_VASSAL_MIN_OPINION) {
                                       CFG.game
                                          .setCivRelation_OfCivB(
                                             CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(i3)
                                                   .lReleasableCivs_TakeControl
                                                   .get(j2)
                                                .iVassalCivID,
                                             CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(i3).iCivID,
                                             RELEASED_VASSAL_MIN_OPINION
                                          );
                                    }

                                    if (CFG.game
                                          .getCivRelation_OfCivB(
                                             CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(i3).iCivID,
                                             CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(i3)
                                                   .lReleasableCivs_TakeControl
                                                   .get(j2)
                                                .iVassalCivID
                                          )
                                       < RELEASED_VASSAL_MIN_OPINION) {
                                       CFG.game
                                          .setCivRelation_OfCivB(
                                             CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(i3).iCivID,
                                             CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(i3)
                                                   .lReleasableCivs_TakeControl
                                                   .get(j2)
                                                .iVassalCivID,
                                             RELEASED_VASSAL_MIN_OPINION
                                          );
                                    }

                                    zeroProvinces = false;
                                 }

                                 for (int m = CFG.game
                                          .getProvince(
                                             CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(k).lReleasableCivs.get(o)
                                                .lProvinces
                                                .get(u)
                                          )
                                          .getCivsSize()
                                       - 1;
                                    m >= 0;
                                    m--
                                 ) {
                                    if (CFG.game
                                             .getProvince(
                                                CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(k).lReleasableCivs.get(o)
                                                   .lProvinces
                                                   .get(u)
                                             )
                                             .getCivID(m)
                                          != CFG.game
                                             .getProvince(
                                                CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(k).lReleasableCivs.get(o)
                                                   .lProvinces
                                                   .get(u)
                                             )
                                             .getCivID()
                                       && CFG.game
                                             .getCiv(
                                                CFG.game
                                                   .getProvince(
                                                      CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(k)
                                                            .lReleasableCivs
                                                            .get(o)
                                                         .lProvinces
                                                         .get(u)
                                                   )
                                                   .getCivID(m)
                                             )
                                             .getPuppetOfCivID()
                                          != CFG.game
                                             .getProvince(
                                                CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(k).lReleasableCivs.get(o)
                                                   .lProvinces
                                                   .get(u)
                                             )
                                             .getCivID()
                                       && CFG.game
                                             .getCiv(
                                                CFG.game
                                                   .getProvince(
                                                      CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(k)
                                                            .lReleasableCivs
                                                            .get(o)
                                                         .lProvinces
                                                         .get(u)
                                                   )
                                                   .getCivID()
                                             )
                                             .getPuppetOfCivID()
                                          != CFG.game
                                             .getProvince(
                                                CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(k).lReleasableCivs.get(o)
                                                   .lProvinces
                                                   .get(u)
                                             )
                                             .getCivID(m)
                                       && (
                                          CFG.game
                                                   .getCiv(
                                                      CFG.game
                                                         .getProvince(
                                                            CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(k)
                                                                  .lReleasableCivs
                                                                  .get(o)
                                                               .lProvinces
                                                               .get(u)
                                                         )
                                                         .getCivID(m)
                                                   )
                                                   .getAllianceID()
                                                <= 0
                                             || CFG.game
                                                   .getCiv(
                                                      CFG.game
                                                         .getProvince(
                                                            CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(k)
                                                                  .lReleasableCivs
                                                                  .get(o)
                                                               .lProvinces
                                                               .get(u)
                                                         )
                                                         .getCivID(m)
                                                   )
                                                   .getAllianceID()
                                                != CFG.game
                                                   .getCiv(
                                                      CFG.game
                                                         .getProvince(
                                                            CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(k)
                                                                  .lReleasableCivs
                                                                  .get(o)
                                                               .lProvinces
                                                               .get(u)
                                                         )
                                                         .getCivID()
                                                   )
                                                   .getAllianceID()
                                       )) {
                                       CFG.gameAction
                                          .accessLost_MoveArmyToClosetsProvince(
                                             CFG.game
                                                .getProvince(
                                                   CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(k)
                                                         .lReleasableCivs
                                                         .get(o)
                                                      .lProvinces
                                                      .get(u)
                                                )
                                                .getCivID(m),
                                             CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(k).lReleasableCivs.get(o)
                                                .lProvinces
                                                .get(u)
                                          );
                                    }
                                 }
                              }
                           }
                        }
                     }
                  }
               }
            }

            for (int var49 = 0; var49 < CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.size(); var49++) {
               for (int j2 = 0;
                  j2 < CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(var49).lReleasableCivs_TakeControl.size();
                  j2++
               ) {
                  for (int kx = 0; kx < CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.size(); kx++) {
                     if (CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(var49).lReleasableCivs_TakeControl.get(j2).iFromCivID
                        == CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(kx).iCivID) {
                        for (int ox = 0;
                           ox < CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(kx).lReleasableCivs.size();
                           ox++
                        ) {
                           if (CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(kx).lReleasableCivs.get(ox).iCivID
                              == CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(var49)
                                    .lReleasableCivs_TakeControl
                                    .get(j2)
                                 .iVassalCivID) {
                              boolean zeroProvinces = CFG.game
                                    .getCiv(
                                       CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(var49)
                                             .lReleasableCivs_TakeControl
                                             .get(j2)
                                          .iVassalCivID
                                    )
                                    .getNumOfProvinces()
                                 == 0;

                              for (int u = 0;
                                 u
                                    < CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(kx).lReleasableCivs.get(ox)
                                       .lProvinces
                                       .size();
                                 u++
                              ) {
                                 int tempArmy0 = CFG.game
                                    .getProvince(
                                       CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(kx).lReleasableCivs.get(ox)
                                          .lProvinces
                                          .get(u)
                                    )
                                    .getArmy(0);
                                 int tempCiv0 = CFG.game
                                    .getProvince(
                                       CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(kx).lReleasableCivs.get(ox)
                                          .lProvinces
                                          .get(u)
                                    )
                                    .getCivID();
                                 int nArmyNewOwner = CFG.game
                                    .getProvince(
                                       CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(kx).lReleasableCivs.get(ox)
                                          .lProvinces
                                          .get(u)
                                    )
                                    .getArmyCivID(CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(var49).iCivID);
                                 int nCivNewOwner = CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(var49).iCivID;
                                 CFG.game
                                    .getProvince(
                                       CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(kx).lReleasableCivs.get(ox)
                                          .lProvinces
                                          .get(u)
                                    )
                                    .updateArmy(0);
                                 CFG.game
                                    .getProvince(
                                       CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(kx).lReleasableCivs.get(ox)
                                          .lProvinces
                                          .get(u)
                                    )
                                    .updateArmy(nCivNewOwner, 0);
                                 CFG.game
                                    .getProvince(
                                       CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(kx).lReleasableCivs.get(ox)
                                          .lProvinces
                                          .get(u)
                                    )
                                    .setTrueOwnerOfProvince(
                                       CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(var49)
                                             .lReleasableCivs_TakeControl
                                             .get(j2)
                                          .iVassalCivID
                                    );
                                 CFG.game
                                    .getProvince(
                                       CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(kx).lReleasableCivs.get(ox)
                                          .lProvinces
                                          .get(u)
                                    )
                                    .setCivID(
                                       CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(var49)
                                             .lReleasableCivs_TakeControl
                                             .get(j2)
                                          .iVassalCivID,
                                       false,
                                       true
                                    );
                                 CFG.game
                                    .getProvince(
                                       CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(kx).lReleasableCivs.get(ox)
                                          .lProvinces
                                          .get(u)
                                    )
                                    .updateArmy(tempCiv0, tempArmy0);
                                 CFG.game
                                    .getProvince(
                                       CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(kx).lReleasableCivs.get(ox)
                                          .lProvinces
                                          .get(u)
                                    )
                                    .updateArmy(nCivNewOwner, nArmyNewOwner);
                                 if (zeroProvinces) {
                                    CFG.game
                                       .getCiv(
                                          CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(var49)
                                                .lReleasableCivs_TakeControl
                                                .get(j2)
                                             .iVassalCivID
                                       )
                                       .setPuppetOfCivID(CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(var49).iCivID);
                                    CFG.historyManager
                                       .addHistoryLog(
                                          new HistoryLog_IsVassal(
                                             CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(var49).iCivID,
                                             CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(var49)
                                                   .lReleasableCivs_TakeControl
                                                   .get(j2)
                                                .iVassalCivID
                                          )
                                       );
                                    if (CFG.game
                                          .getCivRelation_OfCivB(
                                             CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(var49)
                                                   .lReleasableCivs_TakeControl
                                                   .get(j2)
                                                .iVassalCivID,
                                             CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(var49).iCivID
                                          )
                                       < RELEASED_VASSAL_MIN_OPINION) {
                                       CFG.game
                                          .setCivRelation_OfCivB(
                                             CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(var49)
                                                   .lReleasableCivs_TakeControl
                                                   .get(j2)
                                                .iVassalCivID,
                                             CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(var49).iCivID,
                                             RELEASED_VASSAL_MIN_OPINION
                                          );
                                    }

                                    if (CFG.game
                                          .getCivRelation_OfCivB(
                                             CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(var49).iCivID,
                                             CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(var49)
                                                   .lReleasableCivs_TakeControl
                                                   .get(j2)
                                                .iVassalCivID
                                          )
                                       < RELEASED_VASSAL_MIN_OPINION) {
                                       CFG.game
                                          .setCivRelation_OfCivB(
                                             CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(var49).iCivID,
                                             CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(var49)
                                                   .lReleasableCivs_TakeControl
                                                   .get(j2)
                                                .iVassalCivID,
                                             RELEASED_VASSAL_MIN_OPINION
                                          );
                                    }

                                    zeroProvinces = false;
                                 }

                                 for (int mx = CFG.game
                                          .getProvince(
                                             CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(kx).lReleasableCivs.get(ox)
                                                .lProvinces
                                                .get(u)
                                          )
                                          .getCivsSize()
                                       - 1;
                                    mx >= 0;
                                    mx--
                                 ) {
                                    if (CFG.game
                                             .getProvince(
                                                CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(kx)
                                                      .lReleasableCivs
                                                      .get(ox)
                                                   .lProvinces
                                                   .get(u)
                                             )
                                             .getCivID(mx)
                                          != CFG.game
                                             .getProvince(
                                                CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(kx)
                                                      .lReleasableCivs
                                                      .get(ox)
                                                   .lProvinces
                                                   .get(u)
                                             )
                                             .getCivID()
                                       && CFG.game
                                             .getCiv(
                                                CFG.game
                                                   .getProvince(
                                                      CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(kx)
                                                            .lReleasableCivs
                                                            .get(ox)
                                                         .lProvinces
                                                         .get(u)
                                                   )
                                                   .getCivID(mx)
                                             )
                                             .getPuppetOfCivID()
                                          != CFG.game
                                             .getProvince(
                                                CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(kx)
                                                      .lReleasableCivs
                                                      .get(ox)
                                                   .lProvinces
                                                   .get(u)
                                             )
                                             .getCivID()
                                       && CFG.game
                                             .getCiv(
                                                CFG.game
                                                   .getProvince(
                                                      CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(kx)
                                                            .lReleasableCivs
                                                            .get(ox)
                                                         .lProvinces
                                                         .get(u)
                                                   )
                                                   .getCivID()
                                             )
                                             .getPuppetOfCivID()
                                          != CFG.game
                                             .getProvince(
                                                CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(kx)
                                                      .lReleasableCivs
                                                      .get(ox)
                                                   .lProvinces
                                                   .get(u)
                                             )
                                             .getCivID(mx)
                                       && (
                                          CFG.game
                                                   .getCiv(
                                                      CFG.game
                                                         .getProvince(
                                                            CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(kx)
                                                                  .lReleasableCivs
                                                                  .get(ox)
                                                               .lProvinces
                                                               .get(u)
                                                         )
                                                         .getCivID(mx)
                                                   )
                                                   .getAllianceID()
                                                <= 0
                                             || CFG.game
                                                   .getCiv(
                                                      CFG.game
                                                         .getProvince(
                                                            CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(kx)
                                                                  .lReleasableCivs
                                                                  .get(ox)
                                                               .lProvinces
                                                               .get(u)
                                                         )
                                                         .getCivID(mx)
                                                   )
                                                   .getAllianceID()
                                                != CFG.game
                                                   .getCiv(
                                                      CFG.game
                                                         .getProvince(
                                                            CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(kx)
                                                                  .lReleasableCivs
                                                                  .get(ox)
                                                               .lProvinces
                                                               .get(u)
                                                         )
                                                         .getCivID()
                                                   )
                                                   .getAllianceID()
                                       )) {
                                       CFG.gameAction
                                          .accessLost_MoveArmyToClosetsProvince(
                                             CFG.game
                                                .getProvince(
                                                   CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(kx)
                                                         .lReleasableCivs
                                                         .get(ox)
                                                      .lProvinces
                                                      .get(u)
                                                )
                                                .getCivID(mx),
                                             CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(kx).lReleasableCivs.get(ox)
                                                .lProvinces
                                                .get(u)
                                          );
                                    }
                                 }
                              }
                           }
                        }
                     }
                  }
               }
            }

            for (int var50 = 0; var50 < CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.size(); var50++) {
               for (int j2 = 0; j2 < CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.size(); j2++) {
                  if (CFG.game
                     .getCivsAtWar(
                        CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(var50).iCivID,
                        CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(j2).iCivID
                     )) {
                     CFG.game
                        .acceptPeaceOffer(
                           CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(var50).iCivID,
                           CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(j2).iCivID,
                           CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.TRUCE_LENGTH + 1
                        );
                     if (CFG.game
                              .getMilitaryAccess(
                                 CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(var50).iCivID,
                                 CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(j2).iCivID
                              )
                           <= 0
                        && CFG.game
                              .getCiv(CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(var50).iCivID)
                              .getPuppetOfCivID()
                           != CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(j2).iCivID
                        && CFG.game.getCiv(CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(j2).iCivID).getPuppetOfCivID()
                           != CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(var50).iCivID) {
                        CFG.gameAction
                           .accessLost_UpdateArmies(
                              CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(var50).iCivID,
                              CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(j2).iCivID
                           );
                     }

                     if (CFG.game
                              .getMilitaryAccess(
                                 CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(j2).iCivID,
                                 CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(var50).iCivID
                              )
                           <= 0
                        && CFG.game
                              .getCiv(CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(var50).iCivID)
                              .getPuppetOfCivID()
                           != CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(j2).iCivID
                        && CFG.game.getCiv(CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(j2).iCivID).getPuppetOfCivID()
                           != CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(var50).iCivID) {
                        CFG.gameAction
                           .accessLost_UpdateArmies(
                              CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(j2).iCivID,
                              CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(var50).iCivID
                           );
                     }
                  }
               }
            }

            for (int j = 0; j < CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.size(); j++) {
               if (CFG.game.getCiv(CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(j).iCivID).getNumOfProvinces() == 0) {
                  for (int i2 = 1; i2 < CFG.game.getCivsSize(); i2++) {
                     if (CFG.game.getCiv(i2).getPuppetOfCivID()
                        == CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(j).iCivID) {
                        CFG.game.getCiv(i2).setPuppetOfCivID(i2);
                     }
                  }

                  if (CFG.holyRomanEmpire_Manager
                     .getHRE()
                     .getIsElector(CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(j).iCivID)) {
                     CFG.holyRomanEmpire_Manager
                        .getHRE()
                        .removeElector(CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(j).iCivID);
                     CFG.holyRomanEmpire_Manager.getHRE().addStrongestPrinceAsElector();
                  }
               }
            }

            for (int var39 = 0; var39 < CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.size(); var39++) {
               if (CFG.game.getCiv(CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(var39).iCivID).getNumOfProvinces()
                  == 0) {
                  for (int i2x = 1; i2x < CFG.game.getCivsSize(); i2x++) {
                     if (CFG.game.getCiv(i2x).getPuppetOfCivID()
                        == CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(var39).iCivID) {
                        CFG.game.getCiv(i2x).setPuppetOfCivID(i2x);
                     }

                     if (CFG.holyRomanEmpire_Manager
                        .getHRE()
                        .getIsElector(CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(var39).iCivID)) {
                        CFG.holyRomanEmpire_Manager
                           .getHRE()
                           .removeElector(CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(var39).iCivID);
                        CFG.holyRomanEmpire_Manager.getHRE().addStrongestPrinceAsElector();
                     }
                  }
               }
            }

            for (int var51 = 0; var51 < CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsData_Defenders.size(); var51++) {
               CFG.game.buildCivilizationRegions(CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsData_Defenders.get(var51).iCivID);
            }

            for (int var52 = 0; var52 < CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsData_Aggressors.size(); var52++) {
               CFG.game.buildCivilizationRegions(CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsData_Aggressors.get(var52).iCivID);
            }


            try {
               for (int var53 = 0; var53 < CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsData_Defenders.size(); var53++) {
                  if (CFG.game.getCiv(CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsData_Defenders.get(var53).iCivID).getNumOfProvinces() == 0
                     )
                   {
                     for (int z = CFG.game
                              .getCiv(CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsData_Defenders.get(var53).iCivID)
                              .getArmyInAnotherProvinceSize()
                           - 1;
                        z >= 0;
                        z--
                     ) {
                        CFG.game
                           .getProvince(
                              CFG.game
                                 .getCiv(CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsData_Defenders.get(var53).iCivID)
                                 .getArmyInAnotherProvince(z)
                           )
                           .updateArmy(CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsData_Defenders.get(var53).iCivID, 0);
                     }

                     CFG.game.getCiv(CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsData_Defenders.get(var53).iCivID).setNumOfUnits(0);
                  }
               }

               for (int var54 = 0; var54 < CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsData_Aggressors.size(); var54++) {
                  if (CFG.game.getCiv(CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsData_Aggressors.get(var54).iCivID).getNumOfProvinces()
                     == 0) {
                     for (int z = CFG.game
                              .getCiv(CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsData_Aggressors.get(var54).iCivID)
                              .getArmyInAnotherProvinceSize()
                           - 1;
                        z >= 0;
                        z--
                     ) {
                        CFG.game
                           .getProvince(
                              CFG.game
                                 .getCiv(CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsData_Aggressors.get(var54).iCivID)
                                 .getArmyInAnotherProvince(z)
                           )
                           .updateArmy(CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsData_Aggressors.get(var54).iCivID, 0);
                     }

                     CFG.game.getCiv(CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsData_Aggressors.get(var54).iCivID).setNumOfUnits(0);
                  }
               }
            } catch (IndexOutOfBoundsException var29) {
            }

            int tWarID = -1;

            for (int i2x = 0; i2x < CFG.game.getWarsSize(); i2x++) {
               if (CFG.game.getWar(i2x).WAR_TAG.equals(CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.WAR_TAG)) {
                  tWarID = i2x;
                  break;
               }
            }

            try {
               if (tWarID >= 0) {
                  boolean everyoneAtPeace = true;

                  for (int i6 = 0; i6 < CFG.game.getWar(tWarID).getDefendersSize(); i6++) {
                     for (int j3 = 0; j3 < CFG.game.getWar(tWarID).getAggressorsSize(); j3++) {
                        if (CFG.game.getCivsAtWar(CFG.game.getWar(tWarID).getDefenderID(i6).getCivID(), CFG.game.getWar(tWarID).getAggressorID(j3).getCivID())) {
                           everyoneAtPeace = false;
                           i6 = CFG.game.getWar(tWarID).getDefendersSize();
                           break;
                        }
                     }
                  }

                  if (everyoneAtPeace) {
                     CFG.game.removeWarData(tWarID);
                  } else {
                     for (int var76 = CFG.game.getWar(tWarID).getDefendersSize() - 1; var76 >= 0; var76--) {
                        boolean isAtPeace = true;

                        for (int j4 = 0; j4 < CFG.game.getWar(tWarID).getAggressorsSize(); j4++) {
                           if (CFG.game
                              .getCivsAtWar(CFG.game.getWar(tWarID).getDefenderID(var76).getCivID(), CFG.game.getWar(tWarID).getAggressorID(j4).getCivID())) {
                              isAtPeace = false;
                              break;
                           }
                        }

                        if (isAtPeace) {
                           CFG.game.getWar(tWarID).removeDefender(CFG.game.getWar(tWarID).getDefenderID(var76).getCivID());
                        }
                     }

                     for (int var77 = CFG.game.getWar(tWarID).getAggressorsSize() - 1; var77 >= 0; var77--) {
                        boolean isAtPeace = true;

                        for (int j4x = 0; j4x < CFG.game.getWar(tWarID).getDefendersSize(); j4x++) {
                           if (CFG.game
                              .getCivsAtWar(CFG.game.getWar(tWarID).getDefenderID(j4x).getCivID(), CFG.game.getWar(tWarID).getAggressorID(var77).getCivID())) {
                              isAtPeace = false;
                              break;
                           }
                        }

                        if (isAtPeace) {
                           CFG.game.getWar(tWarID).removeAggressor(CFG.game.getWar(tWarID).getAggressorID(var77).getCivID());
                        }
                     }

                     if (CFG.game.getWar(tWarID).getDefendersSize() == 0 || CFG.game.getWar(tWarID).getAggressorsSize() == 0) {
                        CFG.game.removeWarData(tWarID);
                     }
                  }
               }
            } catch (IndexOutOfBoundsException var30) {
               if (CFG.LOGS) {
                  CFG.exceptionStack(var30);
               }
            }
         } catch (IndexOutOfBoundsException var31) {
            if (CFG.LOGS) {
               CFG.exceptionStack(var31);
            }
         } catch (NullPointerException var32) {
            if (CFG.LOGS) {
               CFG.exceptionStack(var32);
            }
         }

         CFG.game.lPeaceTreaties.remove(peaceID);
      }
   }

   protected static final void declinePeaceTreaty(int iCivID, String nTag) {
      int peaceID = CFG.game.getPeaceTreaty_GameDataID(nTag);

      for (int i = 0; i < CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.size(); i++) {
         if (CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(i).peaceTreatyAccepted) {
            CFG.game
               .getCiv(CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Aggressors.get(i).iCivID)
               .getCivilization_Diplomacy_GameData()
               .messageBox
               .addMessage(new Message_PeaceTreaty_Rejected(iCivID));
         }
      }

      for (int var4 = 0; var4 < CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.size(); var4++) {
         if (CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(var4).peaceTreatyAccepted) {
            CFG.game
               .getCiv(CFG.game.lPeaceTreaties.get(peaceID).peaceTreaty_GameData.lCivsDemands_Defenders.get(var4).iCivID)
               .getCivilization_Diplomacy_GameData()
               .messageBox
               .addMessage(new Message_PeaceTreaty_Rejected(iCivID));
         }
      }

      CFG.game.lPeaceTreaties.remove(peaceID);
   }

   public static final boolean sendTradeRequest(int iToCivID, int iFromCivID, TradeRequest_GameData tradeRequest) {
      if (CFG.game.getCiv(iFromCivID).getDiplomacyPoints() >= 10) {
         CFG.game.getCiv(iToCivID).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_TradeReuest(iFromCivID, tradeRequest));
         CFG.game.getCiv(iFromCivID).setDiplomacyPoints(CFG.game.getCiv(iFromCivID).getDiplomacyPoints() - 10);
         if (!CFG.game.getCiv(iFromCivID).getControlledByPlayer()) {
            CFG.game.getCiv(iFromCivID).addSentMessages(new Civilization_SentMessages(iToCivID, Message_Type.TRADE_REQUEST));
         }

         return true;
      } else {
         return false;
      }
   }

   public static final void acceptTradeRequest(int iCivID, int iFromCivID, TradeRequest_GameData tradeRequest) {
      if (tradeRequest.listLEFT.militaryAccess) {
         CFG.game.setMilitaryAccess(iCivID, iFromCivID, 40);
         CFG.historyManager.addHistoryLog(new HistoryLog_HaveMilitartyAccess(iFromCivID, iCivID));
      }

      if (tradeRequest.listRight.militaryAccess) {
         CFG.game.setMilitaryAccess(iFromCivID, iCivID, 40);
         CFG.historyManager.addHistoryLog(new HistoryLog_HaveMilitartyAccess(iCivID, iFromCivID));
      }

      if (tradeRequest.listLEFT.iGold > 0) {
         CFG.game.getCiv(iFromCivID).setMoney(CFG.game.getCiv(iFromCivID).getMoney() - tradeRequest.listLEFT.iGold);
         CFG.game.getCiv(iCivID).setMoney(CFG.game.getCiv(iCivID).getMoney() + tradeRequest.listLEFT.iGold);
      }

      if (tradeRequest.listLEFT.lProvinces.size() > 0) {
         for (int i = 0; i < tradeRequest.listLEFT.lProvinces.size(); i++) {
            if (CFG.game.getProvince(tradeRequest.listLEFT.lProvinces.get(i)).getCivID() == iFromCivID
               && CFG.game.getProvince(tradeRequest.listLEFT.lProvinces.get(i)).getTrueOwnerOfProvince() == iFromCivID) {
               int tempArmy0 = CFG.game.getProvince(tradeRequest.listLEFT.lProvinces.get(i)).getArmy(0);
               int tempCiv0 = CFG.game.getProvince(tradeRequest.listLEFT.lProvinces.get(i)).getCivID();
               int tempArmyNewOwner = CFG.game.getProvince(tradeRequest.listLEFT.lProvinces.get(i)).getArmyCivID(iCivID);
               CFG.game.getProvince(tradeRequest.listLEFT.lProvinces.get(i)).updateArmy(0);
               CFG.game.getProvince(tradeRequest.listLEFT.lProvinces.get(i)).setTrueOwnerOfProvince(iCivID);
               CFG.game.getProvince(tradeRequest.listLEFT.lProvinces.get(i)).setCivID(iCivID, false);
               CFG.game.getProvince(tradeRequest.listLEFT.lProvinces.get(i)).updateArmy(tempCiv0, tempArmy0);
               CFG.game.getProvince(tradeRequest.listLEFT.lProvinces.get(i)).updateArmy(iCivID, tempArmyNewOwner);
               ArrayList<Integer> tempCivsLostAccess = new ArrayList<>();

               for (int j = 0; j < CFG.game.getProvince(tradeRequest.listLEFT.lProvinces.get(i)).getCivsSize(); j++) {
                  tempCivsLostAccess.add(CFG.game.getProvince(tradeRequest.listLEFT.lProvinces.get(i)).getCivID(j));
               }

               for (int var9 = 0; var9 < tempCivsLostAccess.size(); var9++) {
                  if (CFG.game.getCiv(tempCivsLostAccess.get(var9)).getPuppetOfCivID() != iCivID
                     && CFG.game.getCiv(iCivID).getPuppetOfCivID() != tempCivsLostAccess.get(var9)
                     && (
                        CFG.game.getCiv(tempCivsLostAccess.get(var9)).getAllianceID() <= 0
                           || CFG.game.getCiv(tempCivsLostAccess.get(var9)).getAllianceID() != CFG.game.getCiv(iCivID).getAllianceID()
                     )
                     && CFG.game.getMilitaryAccess(tempCivsLostAccess.get(var9), iCivID) <= 0) {
                     CFG.gameAction.accessLost_MoveArmyToClosetsProvince(tempCivsLostAccess.get(var9), tradeRequest.listLEFT.lProvinces.get(i));
                  }
               }

               if (!CFG.game.getProvince(tradeRequest.listLEFT.lProvinces.get(i)).getIsCapital()) {
                  CFG.game.getProvince(tradeRequest.listLEFT.lProvinces.get(i)).removeCapitalCityIcon();
               }

               CFG.game.getProvince(tradeRequest.listLEFT.lProvinces.get(i)).getCore().removeCore(tradeRequest.iCivLEFT);
            }
         }

         CFG.game.buildCivilizationsRegions_TextOver(iFromCivID);
         CFG.game.buildCivilizationsRegions_TextOver(iCivID);
      }

      if (tradeRequest.listLEFT.iDeclarWarOnCivID > 0) {
         CFG.game.declareWar(iFromCivID, tradeRequest.listLEFT.iDeclarWarOnCivID, false);
      }

      if (tradeRequest.listLEFT.iFormCoalitionAgainst > 0) {
         CFG.game.declareWar(iFromCivID, tradeRequest.listLEFT.iFormCoalitionAgainst, false);
         CFG.game.declareWar(iCivID, tradeRequest.listLEFT.iFormCoalitionAgainst, false);
         CFG.game.setCivNonAggressionPact(iFromCivID, iCivID, 40);
         CFG.game.setMilitaryAccess(iFromCivID, iCivID, 40);
         CFG.game.setMilitaryAccess(iCivID, iFromCivID, 40);
         CFG.historyManager.addHistoryLog(new HistoryLog_HaveMilitartyAccess(iCivID, iFromCivID));
         CFG.historyManager.addHistoryLog(new HistoryLog_HaveMilitartyAccess(iFromCivID, iCivID));
      }

      if (tradeRequest.listLEFT.defensivePact) {
         CFG.game.setDefensivePact(iFromCivID, iCivID, 40);
         CFG.historyManager.addHistoryLog(new HistoryLog_SignedDefensivePact(iCivID, iFromCivID));
      }

      if (tradeRequest.listLEFT.nonAggressionPact) {
         CFG.game.setCivNonAggressionPact(iFromCivID, iCivID, 40);
         CFG.historyManager.addHistoryLog(new HistoryLog_SignedNonAggressionPact(iCivID, iFromCivID));
      }

      if (tradeRequest.listLEFT.proclaimIndependence) {
         CFG.game.setGuarantee(iFromCivID, iCivID, 100);
         CFG.historyManager.addHistoryLog(new HistoryLog_Guarantee(iFromCivID, iCivID));
      }

      if (tradeRequest.listRight.iGold > 0) {
         CFG.game.getCiv(iCivID).setMoney(CFG.game.getCiv(iCivID).getMoney() - tradeRequest.listRight.iGold);
         CFG.game.getCiv(iFromCivID).setMoney(CFG.game.getCiv(iFromCivID).getMoney() + tradeRequest.listRight.iGold);
      }

      if (tradeRequest.listRight.lProvinces.size() > 0) {
         for (int ix = 0; ix < tradeRequest.listRight.lProvinces.size(); ix++) {
            if (CFG.game.getProvince(tradeRequest.listRight.lProvinces.get(ix)).getCivID() == iCivID
               && CFG.game.getProvince(tradeRequest.listRight.lProvinces.get(ix)).getTrueOwnerOfProvince() == iCivID) {
               int tempArmy0 = CFG.game.getProvince(tradeRequest.listRight.lProvinces.get(ix)).getArmy(0);
               int tempCiv0 = CFG.game.getProvince(tradeRequest.listRight.lProvinces.get(ix)).getCivID();
               int tempArmyNewOwner = CFG.game.getProvince(tradeRequest.listRight.lProvinces.get(ix)).getArmyCivID(iCivID);
               CFG.game.getProvince(tradeRequest.listRight.lProvinces.get(ix)).updateArmy(0);
               CFG.game.getProvince(tradeRequest.listRight.lProvinces.get(ix)).setTrueOwnerOfProvince(iFromCivID);
               CFG.game.getProvince(tradeRequest.listRight.lProvinces.get(ix)).setCivID(iFromCivID, false);
               CFG.game.getProvince(tradeRequest.listRight.lProvinces.get(ix)).updateArmy(tempCiv0, tempArmy0);
               CFG.game.getProvince(tradeRequest.listRight.lProvinces.get(ix)).updateArmy(iCivID, tempArmyNewOwner);
               ArrayList<Integer> tempCivsLostAccess = new ArrayList<>();

               for (int j = 0; j < CFG.game.getProvince(tradeRequest.listRight.lProvinces.get(ix)).getCivsSize(); j++) {
                  tempCivsLostAccess.add(CFG.game.getProvince(tradeRequest.listRight.lProvinces.get(ix)).getCivID(j));
               }

               for (int var11 = 0; var11 < tempCivsLostAccess.size(); var11++) {
                  if (CFG.game.getCiv(tempCivsLostAccess.get(var11)).getPuppetOfCivID() != iFromCivID
                     && CFG.game.getCiv(iFromCivID).getPuppetOfCivID() != tempCivsLostAccess.get(var11)
                     && (
                        CFG.game.getCiv(tempCivsLostAccess.get(var11)).getAllianceID() <= 0
                           || CFG.game.getCiv(tempCivsLostAccess.get(var11)).getAllianceID() != CFG.game.getCiv(iFromCivID).getAllianceID()
                     )
                     && CFG.game.getMilitaryAccess(tempCivsLostAccess.get(var11), iFromCivID) <= 0) {
                     CFG.gameAction.accessLost_MoveArmyToClosetsProvince(tempCivsLostAccess.get(var11), tradeRequest.listRight.lProvinces.get(ix));
                  }
               }

               if (!CFG.game.getProvince(tradeRequest.listRight.lProvinces.get(ix)).getIsCapital()) {
                  CFG.game.getProvince(tradeRequest.listRight.lProvinces.get(ix)).removeCapitalCityIcon();
               }

               CFG.game.getProvince(tradeRequest.listRight.lProvinces.get(ix)).getCore().removeCore(tradeRequest.iCivRIGHT);
            }
         }

         CFG.game.buildCivilizationsRegions_TextOver(iFromCivID);
         CFG.game.buildCivilizationsRegions_TextOver(iCivID);
      }

      if (tradeRequest.listRight.iDeclarWarOnCivID > 0) {
         CFG.game.declareWar(iCivID, tradeRequest.listRight.iDeclarWarOnCivID, false);
      }

      if (tradeRequest.listRight.iFormCoalitionAgainst > 0) {
         CFG.game.declareWar(iFromCivID, tradeRequest.listRight.iFormCoalitionAgainst, false);
         CFG.game.declareWar(iCivID, tradeRequest.listRight.iFormCoalitionAgainst, false);
         CFG.game.setCivNonAggressionPact(iFromCivID, iCivID, 40);
         CFG.game.setMilitaryAccess(iFromCivID, iCivID, 40);
         CFG.game.setMilitaryAccess(iCivID, iFromCivID, 40);
         CFG.historyManager.addHistoryLog(new HistoryLog_HaveMilitartyAccess(iFromCivID, iCivID));
         CFG.historyManager.addHistoryLog(new HistoryLog_HaveMilitartyAccess(iCivID, iFromCivID));
      }

      if (tradeRequest.listRight.defensivePact) {
         CFG.game.setDefensivePact(iFromCivID, iCivID, 40);
         CFG.historyManager.addHistoryLog(new HistoryLog_SignedDefensivePact(iFromCivID, iCivID));
      }

      if (tradeRequest.listRight.nonAggressionPact) {
         CFG.game.setCivNonAggressionPact(iFromCivID, iCivID, 40);
         CFG.historyManager.addHistoryLog(new HistoryLog_SignedNonAggressionPact(iCivID, iFromCivID));
      }

      if (tradeRequest.listRight.proclaimIndependence) {
         CFG.game.setGuarantee(iCivID, iFromCivID, 100);
         CFG.historyManager.addHistoryLog(new HistoryLog_Guarantee(iFromCivID, iCivID));
      }

      CFG.game.getCiv(iFromCivID).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_TradeReuest_Accepted(iCivID));
   }

   public static final void declineTradeRequest(int iCivID, int iFromCivID, TradeRequest_GameData tradeRequest) {
      CFG.game.getCiv(iFromCivID).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_TradeReuest_Denied(iCivID));
   }

   public static String getTradeRequest_LikelihoodOfSuccess_Text() {
      return !CFG.game.getCiv(CFG.tradeRequest.iCivRIGHT).getControlledByPlayer() ? CFG.langManager.get("Medium") : CFG.langManager.get("NoData");
   }

   public static void runRelationsOutDated() {
      for (int i = Game_Calendar.TURN_ID % 6; i < CFG.game.getCivsSize(); i += 6) {
         if (CFG.game.getCiv(i).getNumOfProvinces() > 0) {
            for (int j = 1; j < CFG.game.getCivsSize(); j++) {
               if (CFG.game.getCiv(j).getNumOfProvinces() > 0) {
                  if (CFG.game.getCivRelation_OfCivB(i, j) > 15.0F) {
                     CFG.game.setCivRelation_OfCivB(i, j, CFG.game.getCivRelation_OfCivB(i, j) - 0.625F);
                  } else if (CFG.game.getCivRelation_OfCivB(i, j) < -20.0F) {
                     CFG.game.setCivRelation_OfCivB(i, j, CFG.game.getCivRelation_OfCivB(i, j) + 0.535F);
                  }
               }
            }
         }
      }
   }

   public static final void buildFriendlyCivs() {
      for (int i = 1; i < CFG.game.getCivsSize(); i++) {
         CFG.game.getCiv(i).clearFreidnlyCivs();
      }

      for (int var2 = 1; var2 < CFG.game.getCivsSize() - 1; var2++) {
         for (int j = var2 + 1; j < CFG.game.getCivsSize(); j++) {
            if (CFG.game.getCivRelation_OfCivB(var2, j) > 44.0F) {
               CFG.game.getCiv(var2).addFriendlyCiv(j);
            } else if (CFG.game.getCivRelation_OfCivB(var2, j) < -25.0F) {
               CFG.game.getCiv(var2).addHatedCiv(j);
            }

            if (CFG.game.getCivRelation_OfCivB(j, var2) > 44.0F) {
               CFG.game.getCiv(j).addFriendlyCiv(var2);
            } else if (CFG.game.getCivRelation_OfCivB(j, var2) < -25.0F) {
               CFG.game.getCiv(j).addHatedCiv(var2);
            }
         }
      }
   }

   public static final void updatePlayersFriendlyCivs() {
      if (!CFG.SPECTATOR_MODE) {
         try {
            for (int i = 0; i < CFG.game.getPlayersSize(); i++) {
               if (CFG.game.getCiv(CFG.game.getPlayer(i).getCivID()).getNumOfProvinces() > 0) {
                  for (int z = CFG.game.getCiv(CFG.game.getPlayer(i).getCivID()).getFriendlyCivsSize() - 1; z >= 0; z--) {
                     if (CFG.game
                           .getCivRelation_OfCivB(CFG.game.getCiv(CFG.game.getPlayer(i).getCivID()).getFriendlyCiv(z).iCivID, CFG.game.getPlayer(i).getCivID())
                        < 39.0F) {
                        CFG.game
                           .getCiv(CFG.game.getPlayer(i).getCivID())
                           .removeFriendlyCiv(CFG.game.getCiv(CFG.game.getPlayer(i).getCivID()).getFriendlyCiv(z).iCivID);
                     }
                  }
               }
            }
         } catch (IndexOutOfBoundsException var2) {
         }
      }
   }

   public static final void checkCivsHatedCivilizations_IfStillExsits() {
      if (Game_Calendar.TURN_ID % 9 == 0) {
         for (int i = 1 + Game_Calendar.TURN_ID % 2; i < CFG.game.getCivsSize(); i += 2) {
            if (CFG.game.getCiv(i).getNumOfProvinces() > 0) {
               for (int z = CFG.game.getCiv(i).getHatedCivsSize() - 1; z >= 0; z--) {
                  if (CFG.game.getCiv(CFG.game.getCiv(i).getHatedCiv(z).iCivID).getNumOfProvinces() == 0) {
                     CFG.game.getCiv(i).removeHatedCiv(CFG.game.getCiv(i).getHatedCiv(z).iCivID);
                  }
               }
            }
         }
      }
   }

   public static final void updateFriendlyCiv(int nCivA, int nCivB) {
      if (CFG.game.getCivRelation_OfCivB(nCivA, nCivB) > 44.0F) {
         if (CFG.game.getCiv(nCivB).addFriendlyCiv(nCivA)) {
            CFG.game.getCiv(nCivA).removeHatedCiv(nCivB);
         }
      } else if (CFG.game.getCivRelation_OfCivB(nCivA, nCivB) < -25.0F && CFG.game.getCiv(nCivA).addHatedCiv(nCivB)) {
         CFG.game.getCiv(nCivB).removeFriendlyCiv(nCivA);
      }

      if (CFG.game.getCivRelation_OfCivB(nCivB, nCivA) > 44.0F) {
         if (CFG.game.getCiv(nCivA).addFriendlyCiv(nCivB)) {
            CFG.game.getCiv(nCivB).removeHatedCiv(nCivA);
         }
      } else if (CFG.game.getCivRelation_OfCivB(nCivB, nCivA) < -25.0F && CFG.game.getCiv(nCivB).addHatedCiv(nCivA)) {
         CFG.game.getCiv(nCivA).removeFriendlyCiv(nCivB);
      }
   }

   public static boolean improveRelation(int iCivA, int iCivB) {
      if (CFG.game.getCiv(iCivA).getNumOfProvinces() == 0) {
         return false;
      } else if (CFG.game.getCiv(iCivB).getCivilization_Diplomacy_GameData().isEmassyClosed(iCivA)) {
         return false;
      } else if (CFG.game.getCiv(iCivA).getDiplomacyPoints() >= 5 && !CFG.game.getCivsAtWar(iCivA, iCivB)) {
         float out = getImproveRelation(iCivA, iCivB);
         float out2 = getImproveRelation(iCivB, iCivA) * 0.9175F;
         boolean updateFriendlyRelation = false;
         if (CFG.game.getCivRelation_OfCivB(iCivA, iCivB) < 44.0F) {
            updateFriendlyRelation = true;
         }

         CFG.game.setCivRelation_OfCivB(iCivA, iCivB, CFG.game.getCivRelation_OfCivB(iCivA, iCivB) + out2);
         CFG.game.setCivRelation_OfCivB(iCivB, iCivA, CFG.game.getCivRelation_OfCivB(iCivB, iCivA) + out);
         if (updateFriendlyRelation) {
            updateFriendlyCiv(iCivA, iCivB);
         }

         return true;
      } else {
         return false;
      }
   }

   public static float getImproveRelation(int iCivA, int iCivB) {
      float out = 0.8425F + CFG.oR.nextInt(121) / 100.0F;
      return 0.125F
         + out
            * (Math.min(CFG.game.getCivRelation_OfCivB(iCivB, iCivA) + 100.0F, 145.0F) / 200.0F)
            * Math.min(Math.max(0.325F, (float)CFG.game.getCiv(iCivA).getRankScore() / CFG.game.getCiv(iCivB).getRankScore()), 1.0F);
   }

   public static boolean decreaseRelation(int iCivA, int iCivB, int nNumOfTurns) {
      if (CFG.game.getCiv(iCivA).getDiplomacyPoints() < 2) {
         return false;
      } else {
         CFG.game.getCiv(iCivA).setDiplomacyPoints(CFG.game.getCiv(iCivA).getDiplomacyPoints() - 2);
         CFG.game.getCiv(iCivB).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_Relations_Insult(iCivA));
         CFG.game.getCiv(iCivA).getCivilization_Diplomacy_GameData().removeImproveRelations_WithCivID(iCivA, iCivB);
         CFG.game.getCiv(iCivB).getCivilization_Diplomacy_GameData().removeImproveRelations_WithCivID(iCivB, iCivA);
         CFG.game.getCiv(iCivA).getCivilization_Diplomacy_GameData().addEmbeassyClosed(new Civilization_ClosedEmbassy(iCivB, nNumOfTurns));
         CFG.game.getCiv(iCivB).getCivilization_Diplomacy_GameData().addEmbeassyClosed(new Civilization_ClosedEmbassy(iCivA, nNumOfTurns));
         float out = getDecreaseRelation(iCivA, iCivB);
         CFG.game
            .setCivRelation_OfCivB(
               iCivA,
               iCivB,
               CFG.game.getCivRelation_OfCivB(iCivA, iCivB) > -100.0F && CFG.game.getCivRelation_OfCivB(iCivA, iCivB) + out <= -100.0F
                  ? -99.0F
                  : CFG.game.getCivRelation_OfCivB(iCivA, iCivB) + out
            );
         float var4;
         CFG.game
            .setCivRelation_OfCivB(
               iCivB,
               iCivA,
               CFG.game.getCivRelation_OfCivB(iCivB, iCivA) > -100.0F && CFG.game.getCivRelation_OfCivB(iCivB, iCivA) + out <= -100.0F
                  ? -99.0F
                  : CFG.game.getCivRelation_OfCivB(iCivB, iCivA) + (var4 = out * 0.415F)
            );
         worldRecations((int)Math.min(30.0F, CFG.game.getCivRelation_OfCivB(iCivA, iCivB) + 100.0F) / 3, iCivA, iCivB);
         updateFriendlyCiv(iCivA, iCivB);
         return true;
      }
   }

   public static float getDecreaseRelation(int iCivA, int iCivB) {
      float out = -(26.25F + CFG.oR.nextInt(27) / 100.0F);
      return out * 0.4F + out * 0.725F * ((CFG.game.getCivRelation_OfCivB(iCivB, iCivA) + 100.0F) / 200.0F);
   }

   public static final void liberateAVassal(int iLord, int iVassal) {
      if (CFG.game.getCiv(iVassal).getPuppetOfCivID() == iLord) {
         CFG.game.getCiv(iVassal).setPuppetOfCivID(iVassal);
         if (CFG.game.getMilitaryAccess(iLord, iVassal) <= 0) {
            CFG.gameAction.accessLost_UpdateArmies(iVassal, iLord);
         }

         if (CFG.game.getMilitaryAccess(iVassal, iLord) <= 0) {
            CFG.gameAction.accessLost_UpdateArmies(iLord, iVassal);
         }

         if (CFG.FOG_OF_WAR > 0) {
            if (CFG.game.getPlayerID_ByCivID(iLord) >= 0) {
               CFG.gameAction.buildFogOfWar(CFG.game.getPlayerID_ByCivID(iLord));
            }

            if (CFG.game.getPlayerID_ByCivID(iVassal) >= 0) {
               CFG.gameAction.buildFogOfWar(CFG.game.getPlayerID_ByCivID(iVassal));
            }
         }

         CFG.palletManager.loadCivilizationStandardColor(iVassal);
         CFG.historyManager.addHistoryLog(new HistoryLog_IsNotVassal(iLord, iVassal));
         CFG.game.getCiv(iVassal).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_Liberation(iLord));
         if (!CFG.game.getCiv(iLord).getControlledByPlayer()) {
            CFG.game.getCiv(iLord).addSentMessages(new Civilization_SentMessages(iVassal, Message_Type.LIBERATION_OF_VASSAL));
         }
      }
   }

   public static final void declarationOfIndependeceByVassal(int iLord, int iVassal) {
      if (CFG.game.getCivTruce(iLord, iVassal) <= 0) {
         if (!CFG.NO_LIBERITY) {
            if (CFG.game.getCiv(iVassal).getPuppetOfCivID() == iLord) {
               CFG.game.getCiv(iVassal).setPuppetOfCivID(iVassal);
               CFG.game.getCiv(iVassal).setVassalLiberityDesire(0.0F);
               if (CFG.game.getMilitaryAccess(iLord, iVassal) <= 0) {
                  CFG.gameAction.accessLost_UpdateArmies(iVassal, iLord);
               }

               if (CFG.game.getMilitaryAccess(iVassal, iLord) <= 0) {
                  CFG.gameAction.accessLost_UpdateArmies(iLord, iVassal);
               }

               if (CFG.FOG_OF_WAR > 0) {
                  if (CFG.game.getPlayerID_ByCivID(iLord) >= 0) {
                     CFG.gameAction.buildFogOfWar(CFG.game.getPlayerID_ByCivID(iLord));
                  }

                  if (CFG.game.getPlayerID_ByCivID(iVassal) >= 0) {
                     CFG.gameAction.buildFogOfWar(CFG.game.getPlayerID_ByCivID(iVassal));
                  }
               }

               CFG.historyManager.addHistoryLog(new HistoryLog_IsNotVassal(iLord, iVassal));
               CFG.palletManager.loadCivilizationStandardColor(iVassal);
               CFG.game.getCiv(iVassal).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_Liberation(iLord));
               CFG.game.getCiv(iLord).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_DeclarationOfIndependence_ByVassal(iVassal));
               if (!CFG.game.getCiv(iLord).getControlledByPlayer()) {
                  CFG.game.getCiv(iLord).addSentMessages(new Civilization_SentMessages(iVassal, Message_Type.LIBERATION_OF_VASSAL));
               }
            }
         }
      }
   }

   public static final boolean canTakeMoreLoans(int nCivID) {
      return CFG.game.getCiv(nCivID).getLoansSize() < 5;
   }

   public static final void takeLoan(int iCivID, int iGold, int iDuration) {
      if (canTakeMoreLoans(iCivID) && iGold > 0 && iDuration >= 5 && iDuration <= 30) {
         if (!canTakeMoreLoans(iCivID)) {
            return;
         }

         if (iGold > takeLoan_MaxValue(iCivID)) {
            iGold = takeLoan_MaxValue(iCivID);
         }

         CFG.game.getCiv(iCivID).setMoney(CFG.game.getCiv(iCivID).getMoney() + iGold);
         CFG.game
            .getCiv(iCivID)
            .addLoan((int)Math.max(Math.ceil((iGold + iGold * takeLoan_InterestRate(iCivID, iGold, iDuration) / 100.0F) / iDuration), 1.0), iDuration);
         CFG.game.getCiv(iCivID).setMovePoints(CFG.game.getCiv(iCivID).getMovePoints() - 6);
      }
   }

   public static final int takeLoan_MinValue() {
      return 30;
   }

   public static final int takeLoan_MaxValue(int iCivID) {
      return (int)Math.max((CFG.game.getCiv(iCivID).iIncomeTaxation + CFG.game.getCiv(iCivID).iIncomeProduction) * 0.6F, 35.0F);
   }

   public static final float takeLoan_InterestRate(int iCivID, int iGold, int iDuration) {
      return iGold == 0
         ? 0.0F
         : 7.25F + CFG.game.getCiv(iCivID).getLoansSize() * 0.7F + (8.0F + CFG.game.getCiv(iCivID).getLoansSize() / 4.0F) * (iDuration - 5) / 25.0F;
   }

   public static final void repayLoan(int iCivID, int iLoanID) {
      try {
         CFG.game
            .getCiv(iCivID)
            .setMoney(
               CFG.game.getCiv(iCivID).getMoney() - CFG.game.getCiv(iCivID).getLoan(iLoanID).iTurnsLeft * CFG.game.getCiv(iCivID).getLoan(iLoanID).iGoldPerTurn
            );
         CFG.game.getCiv(iCivID).removeLoan(iLoanID);
      } catch (IndexOutOfBoundsException var3) {
      }
   }

   public static final float plunderEfficiency(int nCivID, int nProvinceID, int nArmy) {
      return Math.min(1.0F, nArmy / plunderEfficiency_RequiredMAX(nCivID, nProvinceID));
   }

   public static final float plunderEfficiency_RequiredMAX(int nCivID, int nProvinceID) {
      return CFG.game.getProvince(nProvinceID).getPopulationData().getPopulation()
         * (0.1375F - 0.035F * Math.min(CFG.game.getCiv(nCivID).getTechnologyLevel(), 1.0F));
   }

   public static final int plunderProvinceIncome(int nCivID, int nProvinceID, int nArmy) {
      return (int)(CFG.game_NextTurnUpdate.getProvinceIncome_Taxation(nProvinceID) + CFG.game_NextTurnUpdate.getProvinceIncome_Production(nProvinceID));
   }

   public static final int plunderTreasuryIncome(int nCivID, int nProvinceID, int nArmy) {
      return (int)(
         plunderProvinceIncome(nCivID, nProvinceID, nArmy)
            * 1.45F
            * plunderEfficiency(nCivID, nProvinceID, nArmy)
            * (1.0F - 0.625F * CFG.game.getProvince(nProvinceID).getRevolutionaryRisk())
      );
   }

   public static final float plunder_LossesEconomy_Perc(int nCivID, int nProvinceID, int nArmy) {
      return (0.0425F + CFG.oR.nextInt(525) / 10000.0F) * plunderEfficiency(nCivID, nProvinceID, nArmy);
   }

   public static final float plunder_LossesDevelopment_Perc(int nCivID, int nProvinceID, int nArmy) {
      return (0.0875F + CFG.oR.nextInt(625) / 10000.0F) * plunderEfficiency(nCivID, nProvinceID, nArmy);
   }

   public static final float plunder_Happiness(int nCivID, int nProvinceID, int nArmy) {
      return (0.05728F + CFG.oR.nextInt(426) / 10000.0F) * plunderEfficiency(nCivID, nProvinceID, nArmy);
   }

   public static final float plunder_RevolutionaryRisk(int nCivID, int nProvinceID, int nArmy) {
      return Math.max((0.011861F + CFG.oR.nextInt(268) / 10000.0F) * plunderEfficiency(nCivID, nProvinceID, nArmy), 0.034378F);
   }

   public static final int plunder_Population(int nCivID, int nProvinceID, int nArmy) {
      return (int)Math.min(nArmy * (0.04864F + CFG.oR.nextInt(412) / 10000.0F), CFG.game.getProvince(nProvinceID).getPopulationData().getPopulation() * 0.0786F);
   }

   public static final void plunderProvince(int iCivID, int nProvinceID, int nArmy) {
      if (CFG.game.getCiv(iCivID).getMovePoints() >= CFG.ideologiesManager.getIdeology(CFG.game.getCiv(iCivID).getIdeologyID()).COST_OF_PLUNDER) {
         if (nProvinceID >= 0 && CFG.game.getProvince(nProvinceID).isOccupied() && !CFG.game.getProvince(nProvinceID).getSeaProvince()) {
            int currPlunderArmy = 0;

            for (int i = 0; i < CFG.game.getCiv(iCivID).getMoveUnitsPlunderSize(); i++) {
               if (CFG.game.getCiv(iCivID).getMoveUnits_Plunder(i).getFromProvinceID() == nProvinceID) {
                  currPlunderArmy = CFG.game.getCiv(iCivID).getMoveUnits_Plunder(i).getNumOfUnits();
                  if (nArmy == 0) {
                     CFG.game.getCiv(iCivID).removePlunder(i);
                     CFG.game.getProvince(nProvinceID).updateArmy(iCivID, CFG.game.getProvince(nProvinceID).getArmyCivID(iCivID) + currPlunderArmy);
                     if (currPlunderArmy > 0) {
                        CFG.game
                           .getCiv(iCivID)
                           .setMovePoints(
                              CFG.game.getCiv(iCivID).getMovePoints()
                                 + CFG.ideologiesManager.getIdeology(CFG.game.getCiv(iCivID).getIdeologyID()).COST_OF_PLUNDER
                           );
                     }

                     return;
                  }
                  break;
               }
            }

            if (nArmy > CFG.game.getProvince(nProvinceID).getArmyCivID(iCivID) + currPlunderArmy) {
               nArmy = CFG.game.getProvince(nProvinceID).getArmyCivID(iCivID) + currPlunderArmy;
            }

            if (nArmy > 0) {
               CFG.game.getCiv(iCivID).newPlunder(nProvinceID, nArmy);
               CFG.game.getProvince(nProvinceID).updateArmy(iCivID, CFG.game.getProvince(nProvinceID).getArmyCivID(iCivID) + currPlunderArmy - nArmy);
               if (currPlunderArmy == 0) {
                  CFG.game
                     .getCiv(iCivID)
                     .setMovePoints(
                        CFG.game.getCiv(iCivID).getMovePoints() - CFG.ideologiesManager.getIdeology(CFG.game.getCiv(iCivID).getIdeologyID()).COST_OF_PLUNDER
                     );
               }
            }
         }
      }
   }

   public static final void plunder(int iCivID, int nProvinceID, int nArmy) {
      if (CFG.game.getProvince(nProvinceID).getTrueOwnerOfProvince() != iCivID) {
         int nTreasury = plunderTreasuryIncome(iCivID, nProvinceID, nArmy);
         float nHappiness = plunder_Happiness(iCivID, nProvinceID, nArmy);
         int nEconomy = (int)(
            4.0
               + Math.ceil(CFG.game.getProvince(nProvinceID).getEconomy() * plunder_LossesEconomy_Perc(iCivID, nProvinceID, nArmy))
                  * CFG.game.getCiv(iCivID).getModifier_GenocidePower()
         );
         float nDevelopment = CFG.game.getProvince(nProvinceID).getDevelopmentLevel() * plunder_LossesDevelopment_Perc(iCivID, nProvinceID, nArmy);
         float fRevolutionary = plunder_RevolutionaryRisk(iCivID, nProvinceID, nArmy);
         int nPopulation = (int)(plunder_Population(iCivID, nProvinceID, nArmy) * CFG.game.getCiv(iCivID).getModifier_GenocidePower());
         int tempPopulationBefore = CFG.game.getProvince(nProvinceID).getPopulationData().getPopulation();
         int tempEconomyBefore = CFG.game.getProvince(nProvinceID).getEconomy();
         CFG.game.getCiv(iCivID).setMoney(CFG.game.getCiv(iCivID).getMoney() + nTreasury);
         CFG.game.getCiv(iCivID).setFood(CFG.game.getCiv(iCivID).getFood() + nTreasury / 20);
         CFG.game.getProvince(nProvinceID).setEconomy(CFG.game.getProvince(nProvinceID).getEconomy() - nEconomy);
         CFG.game.getProvince(nProvinceID).setDevelopmentLevel(CFG.game.getProvince(nProvinceID).getDevelopmentLevel() - nDevelopment);
         CFG.game.getProvince(nProvinceID).setHappiness(CFG.game.getProvince(nProvinceID).getHappiness() - nHappiness);
         CFG.game
            .getProvince(nProvinceID)
            .setRevolutionaryRisk(
               CFG.game.getProvince(nProvinceID).getRevolutionaryRisk()
                  + CFG.gameAges.getAge_RevolutionaryRiskModifier(Game_Calendar.CURRENT_AGEID) * fRevolutionary
            );
         CFG.gameAction.updatePopulationLosses(nProvinceID, nPopulation);
         int tempWarID = CFG.game.getWarID(iCivID, CFG.game.getProvince(nProvinceID).getTrueOwnerOfProvince());
         if (tempWarID >= 0) {
            CFG.game
               .updateWarStatistics(
                  tempWarID,
                  iCivID,
                  CFG.game.getProvince(nProvinceID).getTrueOwnerOfProvince(),
                  Math.max(tempPopulationBefore - CFG.game.getProvince(nProvinceID).getPopulationData().getPopulation(), 0),
                  Math.max(tempEconomyBefore - CFG.game.getProvince(nProvinceID).getEconomy(), 0),
                  0,
                  0
               );
         }

         CFG.game
            .getCiv(iCivID)
            .getCivilization_Diplomacy_GameData()
            .messageBox
            .addMessage(new Message_Plunder(iCivID, nProvinceID, nTreasury, nEconomy, nDevelopment, nHappiness, nPopulation));
         if (CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getTrueOwnerOfProvince()).getControlledByPlayer()) {
            CFG.game
               .getCiv(CFG.game.getProvince(nProvinceID).getTrueOwnerOfProvince())
               .getCivilization_Diplomacy_GameData()
               .messageBox
               .addMessage(new Message_Plunder_Plundred(iCivID, nProvinceID, nEconomy, nDevelopment, nHappiness, nPopulation));
         }
      }
   }

   public static final void leaveAlliance(int nCivID) {
      if (CFG.game.getCiv(nCivID).getAllianceID() > 0 && CFG.game.getCiv(nCivID).getAllianceID() < CFG.game.getAlliancesSize()) {
         int allianceID = CFG.game.getCiv(nCivID).getAllianceID();
         CFG.game.getAlliance(allianceID).removeCivilization(nCivID);
         CFG.game.getCiv(nCivID).setAllianceID(0);
         if (CFG.game.getCiv(nCivID).getControlledByPlayer()) {
            int tPlayerID = CFG.game.getPlayerID_ByCivID(nCivID);

            for (int i = 0; i < CFG.game.getAlliance(allianceID).getCivilizationsSize(); i++) {
               int tPlayerID2;
               if (CFG.game.getCiv(CFG.game.getAlliance(allianceID).getCivilization(i)).getControlledByPlayer()
                  && (tPlayerID2 = CFG.game.getPlayerID_ByCivID(CFG.game.getAlliance(allianceID).getCivilization(i))) >= 0) {
                  for (int j2 = 0; j2 < CFG.game.getCiv(nCivID).getNumOfProvinces(); j2++) {
                     CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(j2)).updateFogOfWar(tPlayerID2);
                  }

                  for (int var11 = 0; var11 < CFG.game.getCiv(nCivID).civGameData.lVassals.size(); var11++) {
                     for (int k = 0; k < CFG.game.getCiv(CFG.game.getCiv(nCivID).civGameData.lVassals.get(var11).iCivID).getNumOfProvinces(); k++) {
                        CFG.game
                           .getProvince(CFG.game.getCiv(CFG.game.getCiv(nCivID).civGameData.lVassals.get(var11).iCivID).getProvinceID(var11))
                           .updateFogOfWar(tPlayerID2);
                     }
                  }
               }

               if (tPlayerID >= 0) {
                  for (int j = 0; j < CFG.game.getCiv(CFG.game.getAlliance(allianceID).getCivilization(i)).getNumOfProvinces(); j++) {
                     CFG.game.getProvince(CFG.game.getCiv(CFG.game.getAlliance(allianceID).getCivilization(i)).getProvinceID(j)).updateFogOfWar(tPlayerID);
                  }

                  for (int var10 = 0; var10 < CFG.game.getCiv(CFG.game.getAlliance(allianceID).getCivilization(i)).civGameData.lVassals.size(); var10++) {
                     for (int k = 0;
                        k
                           < CFG.game
                              .getCiv(CFG.game.getCiv(CFG.game.getAlliance(allianceID).getCivilization(i)).civGameData.lVassals.get(var10).iCivID)
                              .getNumOfProvinces();
                        k++
                     ) {
                        CFG.game
                           .getProvince(
                              CFG.game
                                 .getCiv(CFG.game.getCiv(CFG.game.getAlliance(allianceID).getCivilization(i)).civGameData.lVassals.get(var10).iCivID)
                                 .getProvinceID(var10)
                           )
                           .updateFogOfWar(tPlayerID);
                     }
                  }
               }
            }
         }

         for (int i = 0; i < CFG.game.getAlliance(allianceID).getCivilizationsSize(); i++) {
            int out = -10;
            CFG.game
               .setCivRelation_OfCivB(
                  nCivID,
                  CFG.game.getAlliance(allianceID).getCivilization(i),
                  CFG.game.getCivRelation_OfCivB(nCivID, CFG.game.getAlliance(allianceID).getCivilization(i)) > -100.0F
                        && CFG.game.getCivRelation_OfCivB(nCivID, CFG.game.getAlliance(allianceID).getCivilization(i)) + out <= -100.0F
                     ? -99.0F
                     : CFG.game.getCivRelation_OfCivB(nCivID, CFG.game.getAlliance(allianceID).getCivilization(i)) + out
               );
            CFG.game
               .setCivRelation_OfCivB(
                  CFG.game.getAlliance(allianceID).getCivilization(i),
                  nCivID,
                  CFG.game.getCivRelation_OfCivB(CFG.game.getAlliance(allianceID).getCivilization(i), nCivID) > -100.0F
                        && CFG.game.getCivRelation_OfCivB(CFG.game.getAlliance(allianceID).getCivilization(i), nCivID) + out <= -100.0F
                     ? -99.0F
                     : CFG.game.getCivRelation_OfCivB(CFG.game.getAlliance(allianceID).getCivilization(i), nCivID) + out
               );
            CFG.game
               .getCiv(CFG.game.getAlliance(allianceID).getCivilization(i))
               .getCivilization_Diplomacy_GameData()
               .messageBox
               .addMessage(new Message_LeftAlliance(nCivID, allianceID));
         }

         CFG.historyManager.addHistoryLog(new HistoryLog_LeavesAlliance(nCivID, allianceID));
      }
   }

   public static final void kickFromAlliance(int nCivID, int byCivID) {
      if (CFG.game.getCiv(nCivID).getAllianceID() > 0
         && CFG.game.getCiv(nCivID).getAllianceID() < CFG.game.getAlliancesSize()
         && CFG.game.getCiv(nCivID).getAllianceID() == CFG.game.getCiv(byCivID).getAllianceID()) {
         int allianceID = CFG.game.getCiv(nCivID).getAllianceID();
         CFG.game.getAlliance(allianceID).removeCivilization(nCivID);
         CFG.game.getCiv(nCivID).setAllianceID(0);

         for (int i = 0; i < CFG.game.getAlliance(allianceID).getCivilizationsSize(); i++) {
            if (CFG.game.getCiv(CFG.game.getAlliance(allianceID).getCivilization(i)).getControlledByPlayer()) {
               int tPlayerID;
               if (CFG.game.getAlliance(allianceID).getCivilization(i) == nCivID && (tPlayerID = CFG.game.getPlayerID_ByCivID(nCivID)) >= 0) {
                  for (int j = 0; j < CFG.game.getCiv(byCivID).getNumOfProvinces(); j++) {
                     CFG.game.getProvince(CFG.game.getCiv(byCivID).getProvinceID(j)).updateFogOfWar(tPlayerID);
                  }

                  for (int var10 = 0; var10 < CFG.game.getCiv(byCivID).civGameData.lVassals.size(); var10++) {
                     for (int k = 0; k < CFG.game.getCiv(CFG.game.getCiv(byCivID).civGameData.lVassals.get(var10).iCivID).getNumOfProvinces(); k++) {
                        CFG.game
                           .getProvince(CFG.game.getCiv(CFG.game.getCiv(byCivID).civGameData.lVassals.get(var10).iCivID).getProvinceID(var10))
                           .updateFogOfWar(tPlayerID);
                     }
                  }
               }

               if (CFG.game.getAlliance(allianceID).getCivilization(i) == nCivID && (tPlayerID = CFG.game.getPlayerID_ByCivID(nCivID)) >= 0) {
                  for (int j = 0; j < CFG.game.getCiv(nCivID).getNumOfProvinces(); j++) {
                     CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(j)).updateFogOfWar(tPlayerID);
                  }

                  for (int var12 = 0; var12 < CFG.game.getCiv(nCivID).civGameData.lVassals.size(); var12++) {
                     for (int k = 0; k < CFG.game.getCiv(CFG.game.getCiv(nCivID).civGameData.lVassals.get(var12).iCivID).getNumOfProvinces(); k++) {
                        CFG.game
                           .getProvince(CFG.game.getCiv(CFG.game.getCiv(nCivID).civGameData.lVassals.get(var12).iCivID).getProvinceID(var12))
                           .updateFogOfWar(tPlayerID);
                     }
                  }
               }
            }
         }

         for (int var15 = 0; var15 < CFG.game.getAlliance(allianceID).getCivilizationsSize(); var15++) {
            int tPlayerID2;
            if (CFG.game.getCiv(CFG.game.getAlliance(allianceID).getCivilization(var15)).getControlledByPlayer()
               && (tPlayerID2 = CFG.game.getPlayerID_ByCivID(CFG.game.getAlliance(allianceID).getCivilization(var15))) >= 0) {
               for (int j = 0; j < CFG.game.getCiv(nCivID).getNumOfProvinces(); j++) {
                  CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(j)).updateFogOfWar(tPlayerID2);
               }

               for (int var14 = 0; var14 < CFG.game.getCiv(nCivID).civGameData.lVassals.size(); var14++) {
                  for (int k = 0; k < CFG.game.getCiv(CFG.game.getCiv(nCivID).civGameData.lVassals.get(var14).iCivID).getNumOfProvinces(); k++) {
                     CFG.game
                        .getProvince(CFG.game.getCiv(CFG.game.getCiv(nCivID).civGameData.lVassals.get(var14).iCivID).getProvinceID(var14))
                        .updateFogOfWar(tPlayerID2);
                  }
               }
            }
         }

         int out = -25;
         CFG.game
            .setCivRelation_OfCivB(
               nCivID,
               byCivID,
               CFG.game.getCivRelation_OfCivB(nCivID, byCivID) > -100.0F && CFG.game.getCivRelation_OfCivB(nCivID, byCivID) + out <= -100.0F
                  ? -99.0F
                  : CFG.game.getCivRelation_OfCivB(nCivID, byCivID) + out
            );
         CFG.game
            .setCivRelation_OfCivB(
               byCivID,
               nCivID,
               CFG.game.getCivRelation_OfCivB(byCivID, nCivID) > -100.0F && CFG.game.getCivRelation_OfCivB(byCivID, nCivID) + out <= -100.0F
                  ? -99.0F
                  : CFG.game.getCivRelation_OfCivB(byCivID, nCivID) + out
            );

         for (int i2 = 0; i2 < CFG.game.getAlliance(allianceID).getCivilizationsSize(); i2++) {
            CFG.game
               .getCiv(CFG.game.getAlliance(allianceID).getCivilization(i2))
               .getCivilization_Diplomacy_GameData()
               .messageBox
               .addMessage(new Message_LeftAlliance(nCivID, allianceID));
         }

         CFG.historyManager.addHistoryLog(new HistoryLog_LeavesAlliance(nCivID, allianceID));
      }
   }

   public static final boolean changeGovernmentType(int nCivID, int toGovType) {
      if (CFG.game.getCiv(nCivID).getIdeologyID() == toGovType) {
         return false;
      } else if (CFG.game.getCiv(nCivID).getMoney() < Ideologies_Manager.getChangeGovernmentCost(nCivID)) {
         return false;
      } else if (CFG.game.getCiv(nCivID).getMovePoints() < 22) {
         return false;
      } else {
         CFG.game.getCiv(nCivID).setMoney(CFG.game.getCiv(nCivID).getMoney() - Ideologies_Manager.getChangeGovernmentCost(nCivID));
         CFG.game.getCiv(nCivID).setMovePoints(CFG.game.getCiv(nCivID).getMovePoints() - 22);
         CFG.game
            .updateCivilizationIdeology(
               nCivID, CFG.ideologiesManager.getRealTag(CFG.game.getCiv(nCivID).getCivTag()) + CFG.ideologiesManager.getIdeology(toGovType).getExtraTag()
            );
         if (CFG.isDesktop() && CFG.game.getCiv(nCivID).getControlledByPlayer()) {
         }

         for (int i = 0; i < CFG.game.getCiv(nCivID).getCivRegionsSize(); i++) {
            CFG.game.getCiv(nCivID).getCivRegion(i).buildScaleOfText();
         }

         return true;
      }
   }

   public static final void genocideProvince(int byCivID, int nProvinceID, int nArmy, ArrayList<Integer> lNations) {
      if (CFG.game.getCiv(byCivID).getMovePoints() >= CFG.ideologiesManager.getIdeology(CFG.game.getCiv(byCivID).getIdeologyID()).COST_OF_PLUNDER) {
         int currPlunderArmy = 0;

         for (int i = 0; i < CFG.game.getCiv(byCivID).getMoveUnitsGenocideSize(); i++) {
            if (CFG.game.getCiv(byCivID).getMoveUnits_Genocide(i).getFromProvinceID() == nProvinceID) {
               currPlunderArmy = CFG.game.getCiv(byCivID).getMoveUnits_Genocide(i).getNumOfUnits();
               if (nArmy == 0) {
                  CFG.game.getCiv(byCivID).removeGenocide(i);
                  CFG.game.getProvince(nProvinceID).updateArmy(byCivID, CFG.game.getProvince(nProvinceID).getArmyCivID(byCivID) + currPlunderArmy);
                  if (currPlunderArmy > 0) {
                     CFG.game
                        .getCiv(byCivID)
                        .setMovePoints(
                           CFG.game.getCiv(byCivID).getMovePoints()
                              + CFG.ideologiesManager.getIdeology(CFG.game.getCiv(byCivID).getIdeologyID()).COST_OF_PLUNDER
                        );
                  }

                  return;
               }
               break;
            }
         }

         if (nArmy > CFG.game.getProvince(nProvinceID).getArmyCivID(byCivID) + currPlunderArmy) {
            nArmy = CFG.game.getProvince(nProvinceID).getArmyCivID(byCivID) + currPlunderArmy;
         }

         if (nArmy > 0) {
            CFG.game.getCiv(byCivID).newGenocide(nProvinceID, nArmy, lNations);
            CFG.game.getProvince(nProvinceID).updateArmy(byCivID, CFG.game.getProvince(nProvinceID).getArmyCivID(byCivID) + currPlunderArmy - nArmy);
            if (currPlunderArmy == 0) {
               CFG.game
                  .getCiv(byCivID)
                  .setMovePoints(
                     CFG.game.getCiv(byCivID).getMovePoints() - CFG.ideologiesManager.getIdeology(CFG.game.getCiv(byCivID).getIdeologyID()).COST_OF_PLUNDER
                  );
            }
         }
      }
   }

   public static void genocide(int iCivID, int nProvinceID, int nArmy, ArrayList<Integer> lNations) {
      byte LEAKAGE_RISK = 16;
      ArrayList<Integer> deaths = new ArrayList<>();

      for (int j = 0; j < lNations.size(); j++) {
         int tempPopulationBefore = CFG.game.getProvince(nProvinceID).getPopulationData().getPopulationOfCivID(lNations.get(j));
         float fEfficiency = nArmy
            * 6.0F
            / CFG.game.getProvince(nProvinceID).getPopulationData().getPopulationOfCivID(lNations.get(j))
            * CFG.game.getCiv(iCivID).getModifier_GenocidePower();
         float fHappinessLosses = (float)CFG.game.getProvince(nProvinceID).getPopulationData().getPopulationOfCivID(lNations.get(j))
            / CFG.game.getProvince(nProvinceID).getPopulationData().getPopulation();
         if (nArmy > CFG.game.getProvince(nProvinceID).getPopulationData().getPopulationOfCivID(lNations.get(j))) {
            CFG.game.getProvince(nProvinceID).getPopulationData().setPopulationOfCivID(lNations.get(j), 0);
         } else {
            CFG.game
               .getProvince(nProvinceID)
               .getPopulationData()
               .setPopulationOfCivID(
                  lNations.get(j),
                  CFG.game.getProvince(nProvinceID).getPopulationData().getPopulationOfCivID(lNations.get(j))
                     - (int)Math.floor((double)CFG.game.getProvince(nProvinceID).getPopulationData().getPopulationOfCivID(lNations.get(j)) * fEfficiency)
               );
         }

         if (CFG.game.getCiv(iCivID).isAtWar() && CFG.game.getProvince(nProvinceID).getTrueOwnerOfProvince() == lNations.get(j)) {
            CFG.game
               .updateWarStatistics(
                  CFG.game.getWarID(iCivID, CFG.game.getProvince(nProvinceID).getTrueOwnerOfProvince()),
                  iCivID,
                  CFG.game.getProvince(nProvinceID).getTrueOwnerOfProvince(),
                  Math.max(tempPopulationBefore - CFG.game.getProvince(nProvinceID).getPopulationData().getPopulation(), 0),
                  0,
                  0,
                  0
               );
         }

         deaths.add(Math.max(tempPopulationBefore - CFG.game.getProvince(nProvinceID).getPopulationData().getPopulationOfCivID(j), 0));
         if (fHappinessLosses < 0.05F) {
            fHappinessLosses = 0.05F;
         }

         if (fHappinessLosses > 0.6F) {
            fHappinessLosses = 0.6F;
         }

         if (fHappinessLosses > CFG.game.getProvince(nProvinceID).getHappiness()) {
            fHappinessLosses = CFG.game.getProvince(nProvinceID).getHappiness() - 0.1F;
         }

         CFG.game.getProvince(nProvinceID).setHappiness(CFG.game.getProvince(nProvinceID).getHappiness() - fHappinessLosses);
         if (CFG.game.getCiv(lNations.get(j)).getCivID() != iCivID) {
            CFG.game
               .getCiv(lNations.get(j))
               .getCivilization_Diplomacy_GameData()
               .messageBox
               .addMessage(new Message_Genocide_ToVictim(iCivID, nProvinceID, lNations, deaths));
         }
      }

      if (ThreadLocalRandom.current().nextInt(0, 100) < LEAKAGE_RISK) {
         for (int i = 0; i < CFG.game.getCivsSize(); i++) {
            CFG.game.setCivRelation_OfCivB(i, iCivID, CFG.game.getCivRelation_OfCivB(i, iCivID) - ThreadLocalRandom.current().nextInt(0, 13));
         }
      }

      if (CFG.game.getCiv(iCivID).getControlledByPlayer()) {
         CFG.game.getCiv(iCivID).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_Genocide(iCivID, nProvinceID, lNations, deaths));
      }
   }

   public static final void addFestivalInAllProvince(int paramInt) {
      int i = CFG.game.getCiv(paramInt).lProvincesWithLowHappiness.size();
      if (i > 0) {
         while (true) {
            i--;
            if (i < 0 || !addFestival(paramInt, CFG.game.getCiv(paramInt).lProvincesWithLowHappiness.get(i))) {
               break;
            }

            i--;
         }
      }
   }

   public static int getNuclearAttackCost(int n, int n2) {
      return 50000 + getNuclearAttackCost2(n, n2) / 2;
   }

   public static final int getNuclearAttackCost2(int n, int n2) {
      float n3 = 15.0F;
      float n4 = CFG.game.getGameScenarios().getScenario_StartingPopulation();
      float colonize_COST_GOLD_PERC = CFG.gameAges.getAge(Game_Calendar.CURRENT_AGEID).COLONIZE_COST_GOLD_PERC;
      float growthRate_Population = CFG.game.getProvince(n).getGrowthRate_Population();
      float n5;
      if (CFG.game.getCiv(n2).getCapitalProvinceID() >= 0) {
         n5 = 20.0F * CFG.game_NextTurnUpdate.getDistanceFromCapital_PercOfMax(CFG.game.getCiv(n2).getCapitalProvinceID(), n);
      } else {
         n5 = 15.0F;
      }

      float colonizeCost_OwnNeighboringProvincesModifier = getColonizeCost_OwnNeighboringProvincesModifier(n, n2);
      float colonizeCost_ContinentAndRegion_Modifier = getColonizeCost_ContinentAndRegion_Modifier(n, n2);
      float fModifier_ColonizationCost = CFG.game.getCiv(n2).civGameData.fModifier_ColonizationCost;
      if (CFG.game.getCiv(n2).getTechnologyLevel() < Game_Calendar.COLONIZATION_TECH_LEVEL) {
         n3 = 2.675F + (Game_Calendar.COLONIZATION_TECH_LEVEL - CFG.game.getCiv(n2).getTechnologyLevel()) * 8.25F;
      }

      return (int)(
            (n5 * 0.1325F + (0.0845F * growthRate_Population + colonize_COST_GOLD_PERC))
               * n4
               * colonizeCost_OwnNeighboringProvincesModifier
               * colonizeCost_ContinentAndRegion_Modifier
               * (15.0F - fModifier_ColonizationCost)
               * n3
         )
         / 100;
   }
}
