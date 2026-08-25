package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.Gdx;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Turn_NewTurn extends Thread {
   public static long tempTime;
   public static long tempTimeTotal;
   public static float ageRiskModifier = 1.0F;
   public static float ageDevMod = 1.0F;
   public static List<PopulationGrowth> tempCivs = new ArrayList<>();
   public static List<Float> happinessChange_ByTaxation = new ArrayList<>();
   public static List<Float> happinessChange_ByTaxation_Occupied = new ArrayList<>();
   public static List<Float> goodsUpdate = new ArrayList<>();
   public static List<Float> devUpdate = new ArrayList<>();
   public static List<Float> ecoUpdate = new ArrayList<>();

   Turn_NewTurn() {
   }

   @Override
   public void run() {
      Gdx.app.log("Turn_NewTurn", "Turn_NewTurn...");
      WorldNews.saveCivSnapshot();
      doAction();
      WorldNews.checkCapitulations();

      if (!WorldNews.CAPITULATIONS.isEmpty() && !CFG.SPECTATOR_MODE) {
         WorldNews.prepare();
         WorldNews.PENDING = true;
         Gdx.app.log("AoC", "WORLDNEWS: capitulations detected");
      }
      Gdx.app.log("Turn_NewTurn", "Turn_NewTurn END");
   }

   public static final void checkOccupiedProvincesIfAreAtWar() {
      if (Game_Calendar.TURN_ID % 10 == 0) {
         for (int i = 0; i < CFG.game.getProvincesSize(); i++) {
            if (!CFG.game.getProvince(i).getSeaProvince()
               && CFG.game.getProvince(i).getWasteland() < 0
               && CFG.game.getProvince(i).isOccupied()
               && CFG.game.getCiv(CFG.game.getProvince(i).getCivID()).getIdeologyID() != CFG.ideologiesManager.REBELS_ID
               && !CFG.game.getCivsAtWar(CFG.game.getProvince(i).getCivID(), CFG.game.getProvince(i).getTrueOwnerOfProvince())) {
               CFG.game
                  .getCiv(CFG.game.getProvince(i).getCivID())
                  .getCivilization_Diplomacy_GameData()
                  .messageBox
                  .addMessage(new Message_ProvincesOccupiedNotAtWar_LostControl(CFG.game.getProvince(i).getTrueOwnerOfProvince(), i));
               int tempArmy0 = CFG.game.getProvince(i).getArmy(0);
               int tempCiv0 = CFG.game.getProvince(i).getCivID();
               int tempArmyNewOwner = CFG.game.getProvince(i).getArmyCivID(CFG.game.getProvince(i).getTrueOwnerOfProvince());
               CFG.game.getProvince(i).updateArmy(0);
               CFG.game.getProvince(i).setCivID(CFG.game.getProvince(i).getTrueOwnerOfProvince(), false);
               CFG.game.getProvince(i).updateArmy(tempCiv0, tempArmy0);
               CFG.game.getProvince(i).updateArmy(CFG.game.getProvince(i).getTrueOwnerOfProvince(), tempArmyNewOwner);
               ArrayList<Integer> tempCivsLostAccess = new ArrayList<>();

               for (int j = 0; j < CFG.game.getProvince(i).getCivsSize(); j++) {
                  tempCivsLostAccess.add(CFG.game.getProvince(i).getCivID(j));
               }

               for (int var6 = 0; var6 < tempCivsLostAccess.size(); var6++) {
                  if (CFG.game.getCiv(tempCivsLostAccess.get(var6)).getPuppetOfCivID() != CFG.game.getProvince(i).getTrueOwnerOfProvince()
                     && CFG.game.getCiv(CFG.game.getProvince(i).getTrueOwnerOfProvince()).getPuppetOfCivID() != tempCivsLostAccess.get(var6)
                     && (
                        CFG.game.getCiv(tempCivsLostAccess.get(var6)).getAllianceID() <= 0
                           || CFG.game.getCiv(tempCivsLostAccess.get(var6)).getAllianceID()
                              != CFG.game.getCiv(CFG.game.getProvince(i).getTrueOwnerOfProvince()).getAllianceID()
                     )
                     && CFG.game.getMilitaryAccess(tempCivsLostAccess.get(var6), CFG.game.getProvince(i).getTrueOwnerOfProvince()) <= 0) {
                     CFG.gameAction.accessLost_MoveArmyToClosetsProvince(tempCivsLostAccess.get(var6), i);
                  }
               }
            }
         }
      }
   }

   public static final void doAction() {
      if (Game_Calendar.TURN_ID % 10 == 0) {
         CFG.game.build_Leaders();
      }

      try {
         tempTime = System.currentTimeMillis();
         tempTimeTotal = System.currentTimeMillis();
         if (Menu_InGame_DiplomaticAssociation.TurnsForJoinСountryPassed > 0) {
            Menu_InGame_DiplomaticAssociation.TurnsForJoinСountryPassed--;
         }

         CFG.game_NextTurnUpdate.updateCivs_ManPower();
         CFG.game_NextTurnUpdate.updateCivs_Money();
         Gdx.app.log("AoC", "STA41MON: " + (System.currentTimeMillis() - tempTime));
         Commands.addMessage("STA41MON: " + (System.currentTimeMillis() - tempTime));
         tempTime = System.currentTimeMillis();

         for (int i = 1; i < CFG.game.getCivsSize(); i++) {
            CFG.game.getCiv(i).runFestivals();
            CFG.game.getCiv(i).runInvests_Development();
            CFG.game.getCiv(i).runInvests();
            CFG.game.getCiv(i).runAssimilates();
            CFG.game.getCiv(i).runWarReparations();
            if (CFG.game.getCiv(i).iNumOfProvinces < 1 && !CFG.game.getCiv(i).isAtWar()) {
               if (CFG.game.getCiv(CFG.game.getCiv(i).iCivID).getAllianceID() > 0) {
                  CFG.game.getAlliance(CFG.game.getCiv(CFG.game.getCiv(i).iCivID).getAllianceID()).removeCivilization(CFG.game.getCiv(i).iCivID);
                  CFG.game.getCiv(CFG.game.getCiv(i).iCivID).setAllianceID(0);
               }

               if (CFG.game.getCiv(i).getIsPupet()) {
                  DiplomacyManager.declarationOfIndependeceByVassal(CFG.game.getCiv(i).getPuppetOfCivID(), CFG.game.getCiv(i).iCivID);
               }
            }
         }

         Gdx.app.log("AoC", "STA1: " + (System.currentTimeMillis() - tempTime));
         Commands.addMessage("STA1: " + (System.currentTimeMillis() - tempTime));
         tempTime = System.currentTimeMillis();
         if (CFG.DISEASES) {
            CFG.plagueManager.runPlagues();
         }

         checkOccupiedProvincesIfAreAtWar();
         Gdx.app.log("AoC", "PLAGUES: " + (System.currentTimeMillis() - tempTime));
         Commands.addMessage("PLAGUES: " + (System.currentTimeMillis() - tempTime));
         tempTime = System.currentTimeMillis();
         Gdx.app.log("AoC", "STA1: " + (System.currentTimeMillis() - tempTime));
         Commands.addMessage("STA1: " + (System.currentTimeMillis() - tempTime));
         tempTime = System.currentTimeMillis();
         updateGameData();

         for (int var34 = 1; var34 < CFG.game.getCivsSize(); var34++) {
            CFG.game.getCiv(var34).getCivilization_Diplomacy_GameData().messageBox.updateNextTurn(var34);
            if (CFG.game.getCiv(var34).isAtWar()) {
               float lostProvincesRatio = (float)CFG.game.getCiv(var34).getNumOfLostProvinces() / CFG.game.getCiv(var34).getNumOfTrueProvinces() * 100.0F;
               CFG.game
                  .getCiv(var34)
                  .setCapitulationPoints(
                     (int)(
                        (100 - CFG.game.getCiv(var34).getHappiness()) / 1.3
                           + (int)lostProvincesRatio
                           + (int)CFG.game.getCiv(var34).getWarWeariness() / 2
                           + (CFG.game.getCiv(var34).getStability() > 90.0F ? -6 : 8)
                           + (CFG.game.getCiv(var34).getNuclearWeapons() > 3 ? -8 : 5)
                           + (CFG.game.getCiv(var34).getMoney() < 0L ? 10 : 0)
                           + (CFG.game.getCiv(var34).getNumOfUnits() < CFG.game.getCiv(var34).countPopulation() * 0.035 ? 15 : 0)
                           + (CFG.game.getCiv(var34).getRankPosition() <= 10 ? -10 : 0)
                           + (CFG.game.getProvince(CFG.game.getCiv(var34).getCapitalProvinceID()).isOccupied() ? 30 : 0)
                     )
                  );
               if (CFG.game.getCiv(var34).getCapitulationPoints() < 0) {
                  CFG.game.getCiv(var34).setCapitulationPoints(0);
               }
            } else {
               CFG.game.getCiv(var34).setCapitulationPoints(0);
            }
         }

         Gdx.app.log("AoC", "STA3: " + (System.currentTimeMillis() - tempTime));
         Commands.addMessage("STA3: " + (System.currentTimeMillis() - tempTime));
         tempTime = System.currentTimeMillis();
         CFG.gameAction.updateCivsMovementPoints();
         Gdx.app.log("AoC", "STA41MOVE: " + (System.currentTimeMillis() - tempTime));
         Commands.addMessage("STA41MOVE: " + (System.currentTimeMillis() - tempTime));
         tempTime = System.currentTimeMillis();
         CFG.gameAction.updateCivsDiplomacyPoints();
         Gdx.app.log("AoC", "STA41: " + (System.currentTimeMillis() - tempTime));
         Commands.addMessage("STA41: " + (System.currentTimeMillis() - tempTime));
         tempTime = System.currentTimeMillis();

         for (int var35 = 1; var35 < CFG.game.getCivsSize(); var35++) {
            for (int j = CFG.game.getCiv(var35).lOpt_DefensivePact.size() - 1; j >= 0; j--) {
               CFG.game.getCiv(var35).setDiplomacyPoints(CFG.game.getCiv(var35).getDiplomacyPoints() - 3);
               CFG.game
                  .getCiv(CFG.game.getCiv(var35).lOpt_DefensivePact.get(j) + var35 + 1)
                  .setDiplomacyPoints(CFG.game.getCiv(CFG.game.getCiv(var35).lOpt_DefensivePact.get(j) + var35 + 1).getDiplomacyPoints() - 3);
               if (CFG.game.getDefensivePact(var35, CFG.game.getCiv(var35).lOpt_DefensivePact.get(j) + var35 + 1) == 1) {
                  CFG.game
                     .getCiv(var35)
                     .getCivilization_Diplomacy_GameData()
                     .messageBox
                     .addMessage(new Message_DefensivePact_Expired(CFG.game.getCiv(var35).lOpt_DefensivePact.get(j) + var35 + 1));
                  CFG.game
                     .getCiv(CFG.game.getCiv(var35).lOpt_DefensivePact.get(j) + var35 + 1)
                     .getCivilization_Diplomacy_GameData()
                     .messageBox
                     .addMessage(new Message_DefensivePact_Expired(var35));
               }

               CFG.game
                  .setDefensivePact(
                     var35,
                     CFG.game.getCiv(var35).lOpt_DefensivePact.get(j) + var35 + 1,
                     CFG.game.getDefensivePact(var35, CFG.game.getCiv(var35).lOpt_DefensivePact.get(j) + var35 + 1) - 1
                  );
            }

            for (int var29 = CFG.game.getCiv(var35).lOpt_NonAggressionPact.size() - 1; var29 >= 0; var29--) {
               CFG.game.getCiv(var35).setDiplomacyPoints(CFG.game.getCiv(var35).getDiplomacyPoints() - 2);
               CFG.game
                  .getCiv(CFG.game.getCiv(var35).lOpt_NonAggressionPact.get(var29) + var35 + 1)
                  .setDiplomacyPoints(CFG.game.getCiv(CFG.game.getCiv(var35).lOpt_NonAggressionPact.get(var29) + var35 + 1).getDiplomacyPoints() - 2);
               if (CFG.game.getCivNonAggressionPact(var35, CFG.game.getCiv(var35).lOpt_NonAggressionPact.get(var29) + var35 + 1) == 1) {
                  CFG.game
                     .getCiv(var35)
                     .getCivilization_Diplomacy_GameData()
                     .messageBox
                     .addMessage(new Message_NonAggressionPact_Expired(CFG.game.getCiv(var35).lOpt_NonAggressionPact.get(var29) + var35 + 1));
                  CFG.game
                     .getCiv(CFG.game.getCiv(var35).lOpt_NonAggressionPact.get(var29) + var35 + 1)
                     .getCivilization_Diplomacy_GameData()
                     .messageBox
                     .addMessage(new Message_NonAggressionPact_Expired(var35));
               }

               CFG.game
                  .setCivNonAggressionPact(
                     var35,
                     CFG.game.getCiv(var35).lOpt_NonAggressionPact.get(var29) + var35 + 1,
                     CFG.game.getCivNonAggressionPact(var35, CFG.game.getCiv(var35).lOpt_NonAggressionPact.get(var29) + var35 + 1) - 1
                  );
            }

            for (int var30 = CFG.game.getCiv(var35).lOpt_Guarantee.size() - 1; var30 >= 0; var30--) {
               CFG.game.getCiv(var35).setDiplomacyPoints(CFG.game.getCiv(var35).getDiplomacyPoints() - 1);
               CFG.game
                  .getCiv(CFG.game.getCiv(var35).lOpt_Guarantee.get(var30) + 1)
                  .setDiplomacyPoints(CFG.game.getCiv(CFG.game.getCiv(var35).lOpt_Guarantee.get(var30) + 1).getDiplomacyPoints() - 1);
               if (CFG.game.getGuarantee(var35, CFG.game.getCiv(var35).lOpt_Guarantee.get(var30) + 1) == 1) {
                  CFG.game
                     .getCiv(var35)
                     .getCivilization_Diplomacy_GameData()
                     .messageBox
                     .addMessage(new Message_IndependenceFrom_Expired(CFG.game.getCiv(var35).lOpt_Guarantee.get(var30) + 1));
                  CFG.game
                     .getCiv(CFG.game.getCiv(var35).lOpt_Guarantee.get(var30) + 1)
                     .getCivilization_Diplomacy_GameData()
                     .messageBox
                     .addMessage(new Message_Independence_Expired(var35));
               }

               CFG.game
                  .setGuarantee(
                     var35,
                     CFG.game.getCiv(var35).lOpt_Guarantee.get(var30) + 1,
                     CFG.game.getGuarantee(var35, CFG.game.getCiv(var35).lOpt_Guarantee.get(var30) + 1) - 1
                  );
            }

            for (int var31 = CFG.game.getCiv(var35).lOpt_MilitirayAccess.size() - 1; var31 >= 0; var31--) {
               CFG.game.getCiv(var35).setDiplomacyPoints(CFG.game.getCiv(var35).getDiplomacyPoints() - 1);
               CFG.game
                  .getCiv(CFG.game.getCiv(var35).lOpt_MilitirayAccess.get(var31) + 1)
                  .setDiplomacyPoints(CFG.game.getCiv(CFG.game.getCiv(var35).lOpt_MilitirayAccess.get(var31) + 1).getDiplomacyPoints() - 1);
               if (CFG.game.getMilitaryAccess(var35, CFG.game.getCiv(var35).lOpt_MilitirayAccess.get(var31) + 1) == 1) {
                  CFG.game
                     .getCiv(var35)
                     .getCivilization_Diplomacy_GameData()
                     .messageBox
                     .addMessage(new Message_MilitaryAccess_Expired(CFG.game.getCiv(var35).lOpt_MilitirayAccess.get(var31) + 1));
                  CFG.gameAction.accessLost_UpdateArmies(CFG.game.getCiv(var35).lOpt_MilitirayAccess.get(var31) + 1, var35);
               } else if (CFG.game.getMilitaryAccess(var35, CFG.game.getCiv(var35).lOpt_MilitirayAccess.get(var31) + 1) < 4) {
                  CFG.game
                     .getCiv(var35)
                     .getCivilization_Diplomacy_GameData()
                     .messageBox
                     .addMessage(
                        new Message_MilitaryAccess_ExpireSoon(
                           CFG.game.getCiv(var35).lOpt_MilitirayAccess.get(var31) + 1,
                           CFG.game.getMilitaryAccess(var35, CFG.game.getCiv(var35).lOpt_MilitirayAccess.get(var31) + 1) - 1
                        )
                     );
               }

               CFG.game
                  .setMilitaryAccess(
                     var35,
                     CFG.game.getCiv(var35).lOpt_MilitirayAccess.get(var31) + 1,
                     CFG.game.getMilitaryAccess(var35, CFG.game.getCiv(var35).lOpt_MilitirayAccess.get(var31) + 1) - 1
                  );
            }

            for (int var32 = CFG.game.getCiv(var35).lOpt_Truce.size() - 1; var32 >= 0; var32--) {
               if (CFG.game.getCivTruce(var35, CFG.game.getCiv(var35).lOpt_Truce.get(var32) + var35 + 1) == 1
                  && CFG.game.getCiv(var35).getNumOfProvinces() > 0
                  && CFG.game.getCiv(CFG.game.getCiv(var35).lOpt_Truce.get(var32) + var35 + 1).getNumOfProvinces() > 0) {
                  CFG.game
                     .getCiv(var35)
                     .getCivilization_Diplomacy_GameData()
                     .messageBox
                     .addMessage(new Message_Truce_Expired(CFG.game.getCiv(var35).lOpt_Truce.get(var32) + var35 + 1));
                  CFG.game
                     .getCiv(CFG.game.getCiv(var35).lOpt_Truce.get(var32) + var35 + 1)
                     .getCivilization_Diplomacy_GameData()
                     .messageBox
                     .addMessage(new Message_Truce_Expired(var35));
               }

               CFG.game
                  .setCivTruce(
                     var35,
                     CFG.game.getCiv(var35).lOpt_Truce.get(var32) + var35 + 1,
                     CFG.game.getCivTruce(var35, CFG.game.getCiv(var35).lOpt_Truce.get(var32) + var35 + 1) - 1
                  );
            }
         }

         Gdx.app.log("AoC", "STA45DIPLOM: " + (System.currentTimeMillis() - tempTime));
         Commands.addMessage("STA45DIPLOM: " + (System.currentTimeMillis() - tempTime));
         tempTime = System.currentTimeMillis();
         CFG.gameAction.updateCivsHappiness();
         CFG.game_NextTurnUpdate.updateProvinceStability();
         CFG.game_NextTurnUpdate.updateInflationPeakValue();
         Game_Calendar.updateDateNextTurn();
         Gdx.app.log("AoC", "STA45NUCLE: " + (System.currentTimeMillis() - tempTime));
         Commands.addMessage("STA45NUCLE: " + (System.currentTimeMillis() - tempTime));
         if (CFG.SANDBOX_MODE || CFG.FREEPLAY_MODE || Game_Calendar.getYear() > 1600) {
            for (int c = 0; c < CFG.game.getCivsSize(); c++) {
               int nuclearReactorCount = CFG.game.getCiv(c).getNuclearReactorsNumber();
               CFG.game
                  .getCiv(c)
                  .setNuclearProgress(
                     CFG.game.getCiv(c).getNuclearProgress()
                        + (0.05F * nuclearReactorCount + CFG.game.getCiv(c).getTechnologyLevel() / 100.0F)
                           * (CFG.game.getCiv(c).getCivID() == CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID() && AI_Assistant.NUCLEAR_DOCTRINE == 2
                              ? 1.25F
                              : 1.0F)
                  );
               if (CFG.game.getCiv(c).getNuclearProgress() > 10.0F + 10.0F * (CFG.game.getCiv(c).getTechnologyLevel() / 200.0F)) {
                  CFG.game.getCiv(c).setNuclearProgress(0.0F);
                  CFG.game.getCiv(c).setNuclearWeapons(CFG.game.getCiv(c).getNuclearWeapons() + 1);
               }
            }
         }

         for (int p = 0; p < CFG.game.getProvincesSize(); p++) {
            CFG.game.getProvince(p).setDrawNuclearExplosion(false);

            for (int np = 0; np < CFG.game.getProvince(p).getNeighboringProvincesSize(); np++) {
               if (CFG.game.getProvince(CFG.game.getProvince(p).getNeighboringProvinces(np)).getZiverts() < CFG.game.getProvince(p).getZiverts()) {
                  float sievert = CFG.game.getProvince(p).getZiverts() / CFG.game.getProvince(p).getNeighboringProvincesSize() / 3.0F;
                  CFG.game
                     .getProvince(CFG.game.getProvince(p).getNeighboringProvinces(np))
                     .setZiverts(CFG.game.getProvince(CFG.game.getProvince(p).getNeighboringProvinces(np)).getZiverts() + sievert);
                  CFG.game.getProvince(p).setZiverts(CFG.game.getProvince(p).getZiverts() - sievert);
               }
            }

            if (CFG.game.getProvince(p).getZiverts() > 0.0F) {
               CFG.game.getProvince(p).setZiverts(CFG.game.getProvince(p).getZiverts() - 8.0E-5F);
            }
         }

         Gdx.app.log("AoC", "STA42: " + (System.currentTimeMillis() - tempTime));
         Commands.addMessage("STA42: " + (System.currentTimeMillis() - tempTime));
         tempTime = System.currentTimeMillis();

         for (int var36 = 1; var36 < CFG.game.getCivsSize(); var36++) {
            CFG.game.getCiv(var36).runConstruction();
            if (CFG.game.getCiv(var36).getMoney() < -500L) {
               CFG.game.getCiv(var36).setSpendings_Research(0.0F);
            }

            if (CFG.game.getCiv(var36).getNumOfProvinces() > 0) {
               if (CFG.game.getCiv(var36).getSpendings_Goods() < CFG.ideologiesManager.getIdeology(CFG.game.getCiv(var36).getIdeologyID()).getMin_Goods(var36)) {
                  CFG.game
                     .getCiv(var36)
                     .getCivilization_Diplomacy_GameData()
                     .messageBox
                     .addMessage(
                        new Message_GoodsLow(
                           var36, (int)(CFG.ideologiesManager.getIdeology(CFG.game.getCiv(var36).getIdeologyID()).getMin_Goods(var36) * 100.0F)
                        )
                     );
               }

               if (CFG.game.getCiv(var36).getSpendings_Investments()
                  < CFG.ideologiesManager.getIdeology(CFG.game.getCiv(var36).getIdeologyID()).MIN_INVESTMENTS) {
                  CFG.game
                     .getCiv(var36)
                     .getCivilization_Diplomacy_GameData()
                     .messageBox
                     .addMessage(
                        new Message_InvestmentsLow(
                           var36, (int)(CFG.ideologiesManager.getIdeology(CFG.game.getCiv(var36).getIdeologyID()).MIN_INVESTMENTS * 100.0F)
                        )
                     );
               }

               if (CFG.game.getCiv(var36).civGameData.skills.getPointsLeft(var36) > 0) {
                  CFG.game.getCiv(var36).getCivilization_Diplomacy_GameData().messageBox.addMessage(new Message_TechPoints(var36));
               }
            }
         }

         for (int var37 = 1; var37 < CFG.game.getCivsSize(); var37++) {
            if (CFG.game.getCiv(var37).getNumOfProvinces() > 0) {
               if (!CFG.game.getCiv(var37).isAtWar()) {
                  CFG.game.getCiv(var37);
                  float f = CFG.game.getCiv(var37).getWarWeariness();
                  CFG.game.getCiv(var37).setWarWeariness(f - 0.01F);
               } else {
                  CFG.game.getCiv(var37);
                  if (CFG.game.getCiv(var37).getControlledByPlayer()) {
                     Iterator<Integer> iterator = CFG.game.recruitInProvinces.iterator();

                     while (iterator.hasNext()) {
                        int provinceIndex = iterator.next();
                        if (CFG.game.getProvince(provinceIndex).getCivID() != CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) {
                           iterator.remove();
                        }
                     }

                     Iterator<Integer> iBuildings = CFG.game.buildInProvinces.iterator();

                     while (iBuildings.hasNext()) {
                        int provinceIndex = iBuildings.next();
                        if (CFG.game.getProvince(provinceIndex).getCivID() != CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) {
                           iBuildings.remove();
                        }
                     }

                     Iterator<Integer> itroopDist = CFG.game.TroopDistributionInProvinces.iterator();

                     while (itroopDist.hasNext()) {
                        int provinceIndex = itroopDist.next();
                        if (CFG.game.getProvince(provinceIndex).getCivID() != CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) {
                           itroopDist.remove();
                        }
                     }

                     CFG.game.getSelectedProvinces().clearSelectedProvinces();
                  }

                  float f1 = CFG.game.getCiv(var37).getWarWeariness();
                  float f2 = Math.min(1.5F, 5.443202F * Game_Calendar.GAME_SPEED);
                  CFG.game.getCiv(var37).setWarWeariness(f1 + 0.01F * f2);
               }
            }
         }

         Gdx.app.log("AoC", "STA43: " + (System.currentTimeMillis() - tempTime));
         Commands.addMessage("STA43: " + (System.currentTimeMillis() - tempTime));
         tempTime = System.currentTimeMillis();

         for (int var38 = 1; var38 < CFG.game.getCivsSize(); var38++) {
            if (CFG.game.getCiv(var38).getNumOfProvinces() > 0) {
               if (CFG.SPECTATOR_MODE) {
                  CFG.game.getCiv(var38).getCivilization_Diplomacy_GameData().messageBox.addMessage(new MessageBox_ExitObserver(var38));
               }

               for (int j = CFG.game.getCiv(var38).lProvincesWithLowStability.size() - 1; j >= 0; j--) {
                  if (CFG.game.getProvince(CFG.game.getCiv(var38).lProvincesWithLowStability.get(j)).getProvinceStability()
                        < Game_Action.RISE_REVOLT_RISK_STABILITY
                     && !CFG.game.getProvince(CFG.game.getCiv(var38).lProvincesWithLowStability.get(j)).isOccupied()
                     && CFG.game.getProvince(CFG.game.getCiv(var38).lProvincesWithLowStability.get(j)).getRevolutionaryRisk() < 0.55F) {
                     CFG.game
                        .getProvince(CFG.game.getCiv(var38).lProvincesWithLowStability.get(j))
                        .setRevolutionaryRisk(
                           CFG.game.getProvince(CFG.game.getCiv(var38).lProvincesWithLowStability.get(j)).getRevolutionaryRisk()
                              + ageRiskModifier
                                 * (
                                    Game_Action.RISE_REVOLT_RISK_STABILITY
                                       - CFG.game.getProvince(CFG.game.getCiv(var38).lProvincesWithLowStability.get(j)).getProvinceStability()
                                 )
                                 * 0.0155F
                        );
                  }
               }
            }
         }

         Gdx.app.log("AoC", "STASTABILITY: " + (System.currentTimeMillis() - tempTime));
         Commands.addMessage("STASTABILITY: " + (System.currentTimeMillis() - tempTime));
         tempTime = System.currentTimeMillis();
         if (!CFG.SPECTATOR_MODE) {
            for (int var39 = 0; var39 < CFG.game.getPlayersSize(); var39++) {
               try {
                  if (CFG.game.getCiv(CFG.game.getPlayer(var39).getCivID()).getNumOfProvinces() > 0) {
                     CFG.game.getPlayer(var39).statistics_Civ_GameData.setTurns(CFG.game.getPlayer(var39).statistics_Civ_GameData.getTurns() + 1);
                     if (CFG.game.getCiv(CFG.game.getPlayer(var39).getCivID()).isMoveAtWarPlayer > 0) {
                        CFG.oAI
                           .getAI_Style(CFG.game.getCiv(CFG.game.getPlayer(var39).getCivID()).getAI_Style())
                           .moveAtWarPlayer(CFG.game.getPlayer(var39).getCivID());
                     }

                     if (CFG.game.getCiv(CFG.game.getPlayer(var39).getCivID()).isAssimilateProvincesPlayer > 0) {
                        CFG.oAI.getAI_Style(CFG.game.getCiv(CFG.game.getPlayer(var39).getCivID()).getAI_Style());
                        AI_Style.AllassimilateProvinces(CFG.game.getPlayer(var39).getCivID());
                     }
                  }
               } catch (NullPointerException var25) {
                  CFG.game.getPlayer(var39).tryLoadStats();
               }
            }
         }

                  if (CFG.game.getPlayersSize() > 0) {
            int tFSCiv = CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID();
            int tFSBuilt = 0;

            for (int i = AI_Assistant.FORT_STRIP_PROVINCES.size() - 1; i >= 0; i--) {
               int tProvID = AI_Assistant.FORT_STRIP_PROVINCES.get(i);
               if (tProvID < 0
                  || tProvID >= CFG.game.getProvincesSize()
                  || CFG.game.getProvince(tProvID).getCivID() != tFSCiv
                  || CFG.game.getProvince(tProvID).isOccupied()) {
                  AI_Assistant.FORT_STRIP_PROVINCES.remove(i);
                  continue;
               }

               if (tFSBuilt < 8 && !CFG.game.getProvince(tProvID).getSeaProvince()) {
                  if (BuildingsManager.canBuildFort(tProvID)) {
                     if (BuildingsManager.constructFort(tProvID, tFSCiv)) {
                        tFSBuilt++;
                        Gdx.app.log("AoC", "FortStrip: FORT -> province " + tProvID);
                     }
                  } else if (BuildingsManager.canBuildTower(tProvID)) {
                     if (BuildingsManager.constructTower(tProvID, tFSCiv)) {
                        tFSBuilt++;
                        Gdx.app.log("AoC", "FortStrip: TOWER -> province " + tProvID);
                     }
                  }
               }
            }
         }

         AI_Assistant.PARTISAN_HOTSPOTS.clear();

         for (int pi = 0; pi < CFG.game.getProvincesSize(); pi++) {
            Province tProvP = CFG.game.getProvince(pi);
            if (tProvP.isOccupied()) {
               int tRealOwner = tProvP.getTrueOwnerOfProvince();
               int tOccupier = tProvP.getCivID();
               if (tRealOwner > 0
                     && tRealOwner != tOccupier
                     && tOccupier == CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()
                     && tProvP.getPopulationData().getPopulation() > 0) {
                  float tForeignShare = 1.0F
                     - (float)tProvP.getPopulationData().getPopulationOfCivID(tRealOwner) / (float)tProvP.getPopulationData().getPopulation();
                  if (tForeignShare > 0.35F) {
                     AI_Assistant.PARTISAN_HOTSPOTS.add(pi);
                     float tChance = 0.01F + 0.05F * tForeignShare - 0.02F * (float)tProvP.getLevelOfFort();
                     if (tChance > 0.005F && CFG.oR.nextInt(1000) < (int)(tChance * 1000.0F)) {
                        int tGarrison = tProvP.getArmyCivID(tOccupier);
                        if (tGarrison > 100) {
                           int tLosses = Math.max(50, (int)((float)tGarrison * (0.05F + (float)CFG.oR.nextInt(10) / 100.0F)));
                           tProvP.updateArmy(tOccupier, tGarrison - tLosses);
                           tProvP.setRevolutionaryRisk(Math.min(1.0F, tProvP.getRevolutionaryRisk() + 0.05F));
                           Gdx.app.log("AoC", "PARTISANS: province " + pi + ", occupier lost " + tLosses);
                           if (tOccupier == CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) {
                              CFG.toast.setInView(
                                 CFG.langManager.get("Partisans_Attack") + "!", CFG.COLOR_TEXT_MODIFIER_NEGATIVE2
                              );
                              CFG.toast.setTimeInView(3000);
                           }
                        }
                     }
                  }
               }
            }
         }

Gdx.app.log("AoC", "ERR: 0000, fontMain" + CFG.fontMain.getData().scaleY);

         for (int var40 = 1; var40 < CFG.game.getCivsSize(); var40++) {
            CFG.game.getCiv(var40).updateBonuses();
            CFG.game.getCiv(var40).civGameData.updateGift_Received();
         }

         Gdx.app.log("AoC", "ERR: 1111, fontMain" + CFG.fontMain.getData().scaleY);
         DiplomacyManager.updateGoldenAge();
         Gdx.app.log("AoC", "ERR: 222, fontMain" + CFG.fontMain.getData().scaleY);
         DiplomacyManager.sendUncivilizedMessages();
         Gdx.app.log("AoC", "ERR: 3333, fontMain" + CFG.fontMain.getData().scaleY);
         DiplomacyManager.sendLowHappiness();
         Gdx.app.log("AoC", "ERR: 444, fontMain" + CFG.fontMain.getData().scaleY);
         if (!CFG.SPECTATOR_MODE) {
            for (int var41 = 0; var41 < CFG.game.getPlayersSize(); var41++) {
               if (CFG.game.getCiv(CFG.game.getPlayer(var41).getCivID()).getNumOfProvinces() > 0) {
                  if (!CFG.SANDBOX_MODE || !CFG.SPECTATOR_MODE) {
                     CFG.game.getCiv(CFG.game.getPlayer(var41).getCivID()).resetTechnologyLevelFlag();
                  }

                  for (int j2 = CFG.game.getCiv(CFG.game.getPlayer(var41).getCivID()).civGameData.civPlans.iWarPreparationsSize - 1; j2 >= 0; j2--) {
                     if (--CFG.game.getCiv(CFG.game.getPlayer(var41).getCivID()).civGameData.civPlans.warPreparations.get(j2).iNumOfTurnsLeft <= 0) {
                        int tOnCivID = CFG.game.getCiv(CFG.game.getPlayer(var41).getCivID()).civGameData.civPlans.warPreparations.get(j2).onCivID;
                        CFG.game
                           .declareWar(
                              CFG.game.getPlayer(var41).getCivID(),
                              CFG.game.getCiv(CFG.game.getPlayer(var41).getCivID()).civGameData.civPlans.warPreparations.get(j2).onCivID,
                              false
                           );
                        CFG.game
                           .getCiv(CFG.game.getPlayer(var41).getCivID())
                           .civGameData
                           .civilization_Diplomacy_GameData
                           .messageBox
                           .addMessage(new Message_War(tOnCivID, CFG.game.getPlayer(var41).getCivID()));

                        try {
                           CFG.game.getCiv(CFG.game.getPlayer(var41).getCivID()).civGameData.civPlans.warPreparations.remove(j2);
                           CFG.game.getCiv(CFG.game.getPlayer(var41).getCivID()).civGameData.civPlans.iWarPreparationsSize = CFG.game
                              .getCiv(CFG.game.getPlayer(var41).getCivID())
                              .civGameData
                              .civPlans
                              .warPreparations
                              .size();
                        } catch (IndexOutOfBoundsException var24) {
                        }
                     }
                  }

                  for (int j2x = CFG.game.getCiv(CFG.game.getPlayer(var41).getCivID()).civGameData.civPlans.iCasusBelliSize - 1; j2x >= 0; j2x--) {
                     if (--CFG.game.getCiv(CFG.game.getPlayer(var41).getCivID()).civGameData.civPlans.casusBelli.get(j2x).iNumOfTurnsLeft <= 0) {
                        int tOnCivID = CFG.game.getCiv(CFG.game.getPlayer(var41).getCivID()).civGameData.civPlans.casusBelli.get(j2x).iLeaderCivID;
                        if (tOnCivID == CFG.game.getPlayer(var41).getCivID()) {
                           tOnCivID = CFG.game.getCiv(CFG.game.getPlayer(var41).getCivID()).civGameData.civPlans.casusBelli.get(j2x).onCivID;
                        }

                        CFG.game
                           .declareWar(
                              CFG.game.getPlayer(var41).getCivID(),
                              CFG.game.getCiv(CFG.game.getPlayer(var41).getCivID()).civGameData.civPlans.casusBelli.get(j2x).onCivID,
                              false
                           );
                        CFG.game
                           .getCiv(CFG.game.getPlayer(var41).getCivID())
                           .civGameData
                           .civilization_Diplomacy_GameData
                           .messageBox
                           .addMessage(new Message_War(tOnCivID, CFG.game.getPlayer(var41).getCivID()));

                        try {
                           CFG.game.getCiv(CFG.game.getPlayer(var41).getCivID()).civGameData.civPlans.casusBelli.remove(j2x);
                           CFG.game.getCiv(CFG.game.getPlayer(var41).getCivID()).civGameData.civPlans.iCasusBelliSize = CFG.game
                              .getCiv(CFG.game.getPlayer(var41).getCivID())
                              .civGameData
                              .civPlans
                              .casusBelli
                              .size();
                        } catch (IndexOutOfBoundsException var23) {
                        }
                     }
                  }

                  if (CFG.game.getCiv(CFG.game.getPlayer(var41).getCivID()).getCapitalProvinceID() < 0
                     || CFG.game.getProvince(CFG.game.getCiv(CFG.game.getPlayer(var41).getCivID()).getCapitalProvinceID()).getCivID()
                           != CFG.game.getPlayer(var41).getCivID()
                        && !CFG.game.getProvince(CFG.game.getCiv(CFG.game.getPlayer(var41).getCivID()).getCapitalProvinceID()).isOccupied()) {
                     CFG.game
                        .getCiv(CFG.game.getPlayer(var41).getCivID())
                        .civGameData
                        .civilization_Diplomacy_GameData
                        .messageBox
                        .addMessage(new Message_RelocateCapital(CFG.game.getPlayer(var41).getCivID()));
                  }
               }
            }
         }

         Gdx.app.log("AoC", "ERR: 5555, fontMain" + CFG.fontMain.getData().scaleY);
         CFG.gameAction.updateHRE_Elections();
         Gdx.app.log("AoC", "ERR: 666, fontMain" + CFG.fontMain.getData().scaleY);
         if (CFG.SANDBOX_MODE && !CFG.SPECTATOR_MODE) {
            CFG.gameNewGame.sandboxMode();
         }

         for (int var42 = 1; var42 < CFG.game.getCivsSize(); var42++) {
            CFG.game.getCiv(var42).getCivilization_Diplomacy_GameData().updateEmbassyClosed();
            CFG.game.getCiv(var42).getCivilization_Diplomacy_GameData().runImproveRelations(var42);
         }

         if (Game_Calendar.TURN_ID % 4 == 0) {
            CFG.gameAction.updateRelations();
         }

         DiplomacyManager.checkCivsHatedCivilizations_IfStillExsits();
         DiplomacyManager.updatePlayersFriendlyCivs();

         for (int var43 = 0; var43 < CFG.game.getWarsSize(); var43++) {
            CFG.game.getWar(var43).iLastFight_InTunrs++;
         }

         Gdx.app.log("AoC-", "STA TOTAL: " + (System.currentTimeMillis() - tempTimeTotal));
         Commands.addMessage("STA TOTAL: " + (System.currentTimeMillis() - tempTimeTotal));
         Gdx.app.log("AoC", "STA4BFSAVE: " + (System.currentTimeMillis() - tempTime));
         Commands.addMessage("STA4BFSAVE: " + (System.currentTimeMillis() - tempTime));
         tempTime = System.currentTimeMillis();
         SaveManager.trySaveGame();
         Gdx.app.log("AoC", "SAVE: " + (System.currentTimeMillis() - tempTime));
         Commands.addMessage("SAVE: " + (System.currentTimeMillis() - tempTime));
         tempTime = System.currentTimeMillis();

         for (int var44 = 0; var44 < CFG.game.getPlayersSize(); var44++) {
            CFG.game.getPlayer(CFG.PLAYER_TURNID).setNoOrders(true);
         }

         CFG.gameAction.moveRegroupArmy();

         for (int var45 = 1; var45 < CFG.game.getCivsSize(); var45++) {
            if (CFG.game.getCiv(var45).getUpdateRegions()) {
               try {
                  CFG.game.getCiv(var45).setUpdateRegions(false);
                  CFG.game.buildCivilizationRegions(var45);
               } catch (IndexOutOfBoundsException var19) {
               } catch (NullPointerException var20) {
               } catch (ArithmeticException var21) {
               } catch (StackOverflowError var22) {
               }
            }
         }

         CFG.gameAction.updateIsSupplied();
         Gdx.app.log("AoC", "STA5: " + (System.currentTimeMillis() - tempTime));
         Commands.addMessage("STA5: " + (System.currentTimeMillis() - tempTime));
         tempTime = System.currentTimeMillis();
         CFG.eventsManager.checkEvents();

         if (!CFG.SPECTATOR_MODE && CFG.game.getPlayersSize() > 0) {
            WorldNews.prepare();
            boolean tHasNews = WorldNews.FRESH_WARS_COUNT > 0 || !WorldNews.CAPITULATIONS.isEmpty();
            
            if (tHasNews) {
               WorldNews.PENDING = true;
               Gdx.app.log("AoC", "WORLDNEWS: pending, wars=" + WorldNews.FRESH_WARS_COUNT);
            }
         }
         Game_Calendar.TURNS_SINCE_LAST_WAR++;
         CFG.gameAction.buildRank_Score();
         if (!CFG.SPECTATOR_MODE) {
            for (int var46 = 0; var46 < CFG.game.getPlayersSize(); var46++) {
               try {
                  CFG.game.getPlayer(var46).iTurnPopulation = CFG.game.getCiv(CFG.game.getPlayer(var46).getCivID()).countPopulation_WithoutOccupied()
                     - CFG.game.getPlayer(var46).iTurnPopulation;
                  CFG.game.getPlayer(var46).iTurnEconomy = CFG.game.getCiv(CFG.game.getPlayer(var46).getCivID()).countEconomy_WithoutOccupied()
                     - CFG.game.getPlayer(var46).iTurnEconomy;
               } catch (IndexOutOfBoundsException var18) {
                  CFG.game.getPlayer(var46).iTurnPopulation = 0L;
                  CFG.game.getPlayer(var46).iTurnEconomy = 0;
               }
            }
         }

         CFG.historyManager.addNewTurn();
         CFG.timelapseManager.newTurn();
         if (Game_Calendar.TURN_ID % (CFG.isDesktop() ? 6 : 12) == 0) {
            CFG.game_NextTurnUpdate.updateCities();
            Gdx.app.log("AoC", "CITIES UPDATE: " + (System.currentTimeMillis() - tempTime));
            Commands.addMessage("CITIES UPDATE: " + (System.currentTimeMillis() - tempTime));
         }

         Gdx.app.log("AoC", "STA6: " + (System.currentTimeMillis() - tempTime));
         Commands.addMessage("STA6: " + (System.currentTimeMillis() - tempTime));
         updateGameData_TurnChanges();
         CFG.gameAction.checkGameEnd();
      } catch (IllegalArgumentException var26) {
         CFG.exceptionStack(var26);
      } catch (Exception var27) {
         CFG.exceptionStack(var27);
      } finally {
         CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(0).setClickable(true);
         Menu_InGame.TIME_CONTINUE = System.currentTimeMillis();
         CFG.setRender_3(true);
      }
   }

   public static final void updateGameData_TurnChanges() {
      if (CFG.isDesktop()) {
         for (int i = 0; i < CFG.game.getProvincesSize(); i++) {
            CFG.game.getProvince(i).saveProvinceData.turnChange_Population = CFG.game.getProvince(i).getPopulationData().getPopulation()
               - CFG.game.getProvince(i).saveProvinceData.turnChange_Population;
            CFG.game.getProvince(i).saveProvinceData.turnChange_Economy = CFG.game.getProvince(i).getEconomy()
               - CFG.game.getProvince(i).saveProvinceData.turnChange_Economy;
            CFG.game.getProvince(i).saveProvinceData.turnChange_Development = CFG.game.getProvince(i).getDevelopmentLevel()
               - CFG.game.getProvince(i).saveProvinceData.turnChange_Development;
            CFG.game.getProvince(i).saveProvinceData.turnChange_Happiness = CFG.game.getProvince(i).getHappiness()
               - CFG.game.getProvince(i).saveProvinceData.turnChange_Happiness;
            CFG.game.getProvince(i).saveProvinceData.turnChange_Stability = CFG.game.getProvince(i).getProvinceStability()
               - CFG.game.getProvince(i).saveProvinceData.turnChange_Stability;
            CFG.game.getProvince(i).saveProvinceData.turnChange_RevRisk = CFG.game.getProvince(i).getRevolutionaryRisk()
               - CFG.game.getProvince(i).saveProvinceData.turnChange_RevRisk;
         }

         Gdx.app.log("AoC", "STA7PROVINCESS: " + (System.currentTimeMillis() - tempTime));
         Commands.addMessage("STA7PROVINCESS: " + (System.currentTimeMillis() - tempTime));
      }
   }

   public static final void updateGameData() {
      if (CFG.isDesktop()) {
         tempCivs = new ArrayList<>();
         happinessChange_ByTaxation = new ArrayList<>();
         happinessChange_ByTaxation_Occupied = new ArrayList<>();
         goodsUpdate = new ArrayList<>();
         devUpdate = new ArrayList<>();
         ecoUpdate = new ArrayList<>();
         ageRiskModifier = CFG.gameAges.getAge_RevolutionaryRiskModifier(Game_Calendar.CURRENT_AGEID);
         ageDevMod = CFG.gameAges.getAge_DevelopmentLevel_Increase(Game_Calendar.CURRENT_AGEID);

         for (int i = 1; i < CFG.game.getCivsSize(); i++) {
            if (CFG.game.getCiv(i).getNumOfProvinces() > 0) {
               happinessChange_ByTaxation.add(CFG.game_NextTurnUpdate.getHappinessChange_ByTaxation(i));
               happinessChange_ByTaxation_Occupied.add(CFG.game_NextTurnUpdate.getHappinessChange_ByTaxation_Occupied(i));
               goodsUpdate.add(
                  CFG.game.getCiv(i).getSpendings_Goods() < CFG.ideologiesManager.getIdeology(CFG.game.getCiv(i).getIdeologyID()).getMin_Goods(i)
                     ? -0.0192864F
                        * (
                           (CFG.ideologiesManager.getIdeology(CFG.game.getCiv(i).getIdeologyID()).getMin_Goods(i) - CFG.game.getCiv(i).getSpendings_Goods())
                              / CFG.ideologiesManager.getIdeology(CFG.game.getCiv(i).getIdeologyID()).getMin_Goods(i)
                        )
                     : (
                           -CFG.ideologiesManager.getIdeology(CFG.game.getCiv(i).getIdeologyID()).getMin_Goods(i)
                              + 0.013845F
                              + CFG.game.getCiv(i).getSpendings_Goods()
                        )
                        * CFG.gameAges.getAge_Population_GrowthRate(Game_Calendar.CURRENT_AGEID)
               );
               devUpdate.add(
                  CFG.game.getCiv(i).getSpendings_Investments() < CFG.ideologiesManager.getIdeology(CFG.game.getCiv(i).getIdeologyID()).MIN_INVESTMENTS
                     ? -0.642864F
                        * (
                           (
                                 CFG.ideologiesManager.getIdeology(CFG.game.getCiv(i).getIdeologyID()).MIN_INVESTMENTS
                                    - CFG.game.getCiv(i).getSpendings_Investments()
                              )
                              / CFG.ideologiesManager.getIdeology(CFG.game.getCiv(i).getIdeologyID()).MIN_INVESTMENTS
                        )
                     : -CFG.ideologiesManager.getIdeology(CFG.game.getCiv(i).getIdeologyID()).MIN_INVESTMENTS
                        + 0.01F
                        + CFG.game.getCiv(i).getSpendings_Investments()
               );
               ecoUpdate.add(
                  CFG.game.getCiv(i).getSpendings_Investments() < CFG.ideologiesManager.getIdeology(CFG.game.getCiv(i).getIdeologyID()).MIN_INVESTMENTS
                     ? -0.0192864F
                        * (
                           (
                                 CFG.ideologiesManager.getIdeology(CFG.game.getCiv(i).getIdeologyID()).MIN_INVESTMENTS
                                    - CFG.game.getCiv(i).getSpendings_Investments()
                              )
                              / CFG.ideologiesManager.getIdeology(CFG.game.getCiv(i).getIdeologyID()).MIN_INVESTMENTS
                        )
                     : (
                           -CFG.ideologiesManager.getIdeology(CFG.game.getCiv(i).getIdeologyID()).MIN_INVESTMENTS
                              + 0.01F
                              + CFG.game.getCiv(i).getSpendings_Investments()
                        )
                        * CFG.gameAges.getAge_Economy_GrowthRate(Game_Calendar.CURRENT_AGEID)
               );
            } else {
               happinessChange_ByTaxation.add(1.0F);
               happinessChange_ByTaxation_Occupied.add(1.0F);
               goodsUpdate.add(1.0F);
               devUpdate.add(1.0F);
               ecoUpdate.add(1.0F);
            }

            CFG.game.getCiv(i).civGameData.civAggresionLevel = Math.max(0.0F, CFG.game.getCiv(i).civGameData.civAggresionLevel - 8.0E-4F);
         }

         float modifiedStartingPop = CFG.game.getGameScenarios().getScenario_StartingPopulation() * 0.1875F;
         float modifiedStartingEco = CFG.game.getGameScenarios().getScenario_StartingEconomy() * 0.0925F;
         Gdx.app.log("AoC", "STA2_PREP: " + (System.currentTimeMillis() - tempTime));
         Commands.addMessage("STA2_PREP: " + (System.currentTimeMillis() - tempTime));
         tempTime = System.currentTimeMillis();

         for (int i = 0; i < CFG.game.getProvincesSize(); i++) {
            if (!CFG.game.getProvince(i).getSeaProvince() && CFG.game.getProvince(i).getWasteland() < 0) {
               if (CFG.game.getProvince(i).getCivID() <= 0) {
                  if (CFG.oR.nextInt(50) > 38) {
                     CFG.game.getProvince(i).updateArmy(0, CFG.game.getProvince(i).getArmyCivID(0) - 4 + CFG.oR.nextInt(11));
                  }
               } else {
                  int n = CFG.game.getProvince(i).getCivID();
                  if (CFG.game.getProvince(i).getZiverts() > 8.0F) {
                     for (int k = CFG.game.getProvince(i).getPopulationData().getNationalitiesSize() - 1; k >= 0; k--) {
                        CFG.game
                           .getProvince(i)
                           .getPopulationData()
                           .setPopulationOfCivID(
                              CFG.game.getProvince(i).getPopulationData().getCivID(k),
                              (int)(CFG.game.getProvince(i).getPopulationData().getPopulationID(k) - CFG.game.getProvince(i).getZiverts() * 2.5)
                           );
                     }

                     CFG.game.getProvince(i).setHappiness(CFG.game.getProvince(i).getHappiness() - 0.01F);
                  }

                  if (CFG.game.getProvince(i).getTrueOwnerOfProvince() == CFG.game.getProvince(i).getCivID()
                     && !CFG.ideologiesManager.getIdeology(CFG.game.getCiv(CFG.game.getProvince(i).getCivID()).getIdeologyID()).REVOLUTIONARY) {
                     CFG.game.getProvince(i).getCore().increaseOwnership(CFG.game.getProvince(i).getCivID(), i);
                  }

                  if (CFG.game.getProvince(i).getDevelopmentLevel() < 1.0F) {
                     if (CFG.game.getProvince(i).getCivID() == CFG.game.getProvince(i).getTrueOwnerOfProvince()) {
                        float tempDevelopmentChange = ageDevMod
                           * devUpdate.get(CFG.game.getProvince(i).getCivID() - 1)
                           * Math.min(CFG.game.getProvince(i).getGrowthRate_Population_WithFarm_WithTerrain() * 0.45F, 0.3705F);
                        CFG.game.getProvince(i).setDevelopmentLevel(CFG.game.getProvince(i).getDevelopmentLevel() + tempDevelopmentChange);
                     } else {
                        CFG.game.getProvince(i).setDevelopmentLevel(CFG.game.getProvince(i).getDevelopmentLevel() - CFG.oR.nextInt(275) / 100000.0F);
                     }
                  }

                  float tempPopGrowth;
                  if ((
                        tempPopGrowth = CFG.game.getProvince(i).getPopulationData().getPopulation()
                           * (
                              0.2F
                                 + (
                                    CFG.ideologiesManager
                                             .getIdeology(CFG.game.getCiv(CFG.game.getProvince(i).getCivID()).getIdeologyID())
                                             .getMin_Goods(CFG.game.getProvince(i).getCivID())
                                          < CFG.game.getCiv(CFG.game.getProvince(i).getCivID()).getSpendings_Goods()
                                       ? CFG.oR.nextInt(50) / 100.0F
                                       : 0.5F
                                 )
                           )
                           * goodsUpdate.get(CFG.game.getProvince(i).getCivID() - 1)
                           * (
                              (
                                    0.01F
                                       + CFG.game.getProvince(i).getGrowthRate_Population_WithFarm_WithTerrain()
                                       + CFG.game.getCiv(CFG.game.getProvince(i).getCivID()).getModifier_PopGrowth()
                                 )
                                 * 0.485F
                           )
                           * (
                              1.0F
                                 + CFG.game.getProvince(i).getDevelopmentLevel() / 63.3468F
                                 + CFG.game.getGameScenarios().getScenario_PopulationGrowthRate_Modifier()
                           )
                           * Game_Calendar.GAME_SPEED
                     )
                     > 0.0F) {
                     if (CFG.game.getProvince(i).getPopulationData().getPopulation() < modifiedStartingPop * CFG.game.getProvince(i).getGrowthRate_Population()
                        )
                      {
                        tempPopGrowth += CFG.game.getGameScenarios().getScenario_StartingPopulation()
                           * (
                              0.00725F
                                 * (
                                    1.0F
                                       - (float)CFG.game.getProvince(i).getPopulationData().getPopulation()
                                          / CFG.game.getGameScenarios().getScenario_StartingPopulation()
                                 )
                           )
                           * CFG.game.getProvince(i).getGrowthRate_Population()
                           * Math.min(CFG.game.getProvince(i).getDevelopmentLevel() * 2.7469234F, 1.0F);
                     }

                     if ((
                           tempPopGrowth = 1.0F
                              + tempPopGrowth
                                 * Math.max(
                                    0.0865F,
                                    1.0F
                                       - 0.4F
                                          * CFG.game.getProvince(i).getPopulationData().getPopulation()
                                          / (CFG.game.getGameScenarios().getScenario_StartingPopulation() * 1.825F)
                                 )
                        )
                        > 0.0F) {
                        tempPopGrowth = tempPopGrowth * 0.1F
                           + CFG.oR.nextInt(Math.max((int)(tempPopGrowth * 1.0F * 100.0F), 1)) / 100.0F
                           - CFG.oR.nextInt(Math.max((int)(tempPopGrowth * 0.325F * 100.0F), 1)) / 100.0F;
                     }
                  }

                  if (CFG.game.getProvince(i).getCivID() == CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()
                     && AI_Assistant.MIGRATION_POLICY == 0) {
                     tempPopGrowth *= 1.3F;
                  }

                  if ((int)tempPopGrowth != 0) {
                     if (tempPopGrowth > -10.0F && tempPopGrowth < 16.0F) {
                        CFG.game
                           .getProvince(i)
                           .getPopulationData()
                           .setPopulationOfCivID(
                              CFG.game.getProvince(i).getCivID(),
                              CFG.game.getProvince(i).getPopulationData().getPopulationOfCivID(CFG.game.getProvince(i).getCivID()) + (int)tempPopGrowth
                           );
                     } else {
                        tempCivs.clear();
                        tempCivs.add(new PopulationGrowth(CFG.game.getProvince(i).getCivID(), 8.13F * CFG.game.getProvince(i).getProvinceStability()));
                        if (CFG.game.getProvince(i).getCivID() != CFG.game.getCiv(CFG.game.getProvince(i).getCivID()).getPuppetOfCivID()) {
                           tempCivs.add(new PopulationGrowth(CFG.game.getCiv(CFG.game.getProvince(i).getCivID()).getPuppetOfCivID(), 5.4378F));
                        }

                        if (CFG.game.getProvince(i).isOccupied()) {
                           tempCivs.add(new PopulationGrowth(CFG.game.getProvince(i).getTrueOwnerOfProvince(), 6.241138F));
                        }

                        for (int j3 = 0; j3 < CFG.game.getProvince(i).getCore().getCivsSize(); j3++) {
                           tempCivs.add(new PopulationGrowth(CFG.game.getProvince(i).getCore().getCivID(j3), 6.7861F));
                        }

                        int tempPop = CFG.game.getProvince(i).getPopulationData().getPopulation();

                        for (int j2 = 0; j2 < CFG.game.getProvince(i).getPopulationData().getNationalitiesSize(); j2++) {
                           tempCivs.add(
                              new PopulationGrowth(
                                 CFG.game.getProvince(i).getPopulationData().getCivID(j2),
                                 (float)CFG.game.getProvince(i).getPopulationData().getPopulationID(j2) / tempPop * 100.0F
                              )
                           );
                        }

                        for (int var27 = 0; var27 < CFG.game.getProvince(i).getNeighboringProvincesSize(); var27++) {
                           if (CFG.game.getProvince(CFG.game.getProvince(i).getNeighboringProvinces(var27)).getCivID() > 0) {
                              tempCivs.add(new PopulationGrowth(CFG.game.getProvince(CFG.game.getProvince(i).getNeighboringProvinces(var27)).getCivID(), 2.0F));
                           }
                        }

                        if (CFG.game.getProvince(i).getCivID() == CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()
                           && AI_Assistant.MIGRATION_POLICY == 2) {
                           int tOwnerCivID = CFG.game.getProvince(i).getCivID();

                           for (int j = tempCivs.size() - 1; j >= 0; j--) {
                              if (tempCivs.get(j).iCivID != tOwnerCivID) {
                                 tempCivs.remove(j);
                              }
                           }
                        }

                        float tempTotalPoints = 0.0F;

                        for (int j = tempCivs.size() - 1; j >= 0; j--) {
                           tempTotalPoints += tempCivs.get(j).fPerc;
                        }

                        for (int var22 = tempCivs.size() - 1; var22 >= 0; var22--) {
                           tempCivs.get(var22).fPerc /= tempTotalPoints;
                           CFG.game
                              .getProvince(i)
                              .getPopulationData()
                              .setPopulationOfCivID(
                                 tempCivs.get(var22).iCivID,
                                 CFG.game.getProvince(i).getPopulationData().getPopulationOfCivID(tempCivs.get(var22).iCivID)
                                    + (int)(tempPopGrowth * tempCivs.get(var22).fPerc)
                              );
                        }

                        tempCivs.clear();
                     }
                  }

                  Province_Population tAutoAbsorbPopData = CFG.game.getProvince(i).getPopulationData();

                  for (int j = tAutoAbsorbPopData.getNationalitiesSize() - 1; j >= 0; j--) {
                     if (tAutoAbsorbPopData.getCivID(j) != CFG.game.getProvince(i).getCivID()
                        && !CFG.game.getProvince(i).isOccupied()) {
                        int tForeignPop = tAutoAbsorbPopData.getPopulationID(j);
                        if (tForeignPop <= 50 || tForeignPop <= (int)((float)tAutoAbsorbPopData.getPopulation() * 0.005F)) {
                           tAutoAbsorbPopData.setPopulationOfCivID(
                              CFG.game.getProvince(i).getCivID(),
                              tAutoAbsorbPopData.getPopulationOfCivID(CFG.game.getProvince(i).getCivID()) + tForeignPop
                           );
                           tAutoAbsorbPopData.setPopulationOfCivID(tAutoAbsorbPopData.getCivID(j), 0);
                        }
                     }
                  }

                  if (CFG.game.getCiv(CFG.game.getProvince(i).getCivID()).getSpendings_Goods()
                     < CFG.ideologiesManager
                        .getIdeology(CFG.game.getCiv(CFG.game.getProvince(i).getCivID()).getIdeologyID())
                        .getMin_Goods(CFG.game.getProvince(i).getCivID())) {
                     float tempHapp = -0.01225F
                        * (
                           (
                                 CFG.ideologiesManager
                                       .getIdeology(CFG.game.getCiv(CFG.game.getProvince(i).getCivID()).getIdeologyID())
                                       .getMin_Goods(CFG.game.getProvince(i).getCivID())
                                    - CFG.game.getCiv(CFG.game.getProvince(i).getCivID()).getSpendings_Goods()
                              )
                              / CFG.ideologiesManager
                                 .getIdeology(CFG.game.getCiv(CFG.game.getProvince(i).getCivID()).getIdeologyID())
                                 .getMin_Goods(CFG.game.getProvince(i).getCivID())
                        )
                        * (0.01F + CFG.game.getProvince(i).getDevelopmentLevel() * 1.25F + CFG.game.getProvince(i).getGrowthRate_Population_WithFarm() * 0.135F);
                     if (tempHapp > 0.0F) {
                        tempHapp *= 1.0F - 0.625F * CFG.game.getCiv(CFG.game.getProvince(i).getCivID()).civGameData.fWarWeariness;
                     }

                     CFG.game.getProvince(i).setHappiness(CFG.game.getProvince(i).getHappiness() + tempHapp);
                     float tempEcoPop = CFG.game.getProvince(i).getEconomy()
                        * -0.00625F
                        * (
                           (
                                 CFG.ideologiesManager
                                       .getIdeology(CFG.game.getCiv(CFG.game.getProvince(i).getCivID()).getIdeologyID())
                                       .getMin_Goods(CFG.game.getProvince(i).getCivID())
                                    - CFG.game.getCiv(CFG.game.getProvince(i).getCivID()).getSpendings_Goods()
                              )
                              / CFG.ideologiesManager
                                 .getIdeology(CFG.game.getCiv(CFG.game.getProvince(i).getCivID()).getIdeologyID())
                                 .getMin_Goods(CFG.game.getProvince(i).getCivID())
                        )
                        * (
                           0.01F
                              + CFG.game.getProvince(i).getDevelopmentLevel() * 0.5475F
                              + CFG.game.getProvince(i).getGrowthRate_Population_WithFarm_WithTerrain() * 0.195F
                        );
                     CFG.game.getProvince(i).setEconomy((int)(CFG.game.getProvince(i).getEconomy() + tempEcoPop));
                  }

                  float tempEco;
                  if ((
                        tempEco = Math.max(
                              (float)CFG.game.getProvince(i).getEconomy(),
                              CFG.game.getGameScenarios().getScenario_StartingPopulation() * 0.0825F * CFG.game.getProvince(i).getGrowthRate_Population()
                           )
                           * ecoUpdate.get(CFG.game.getProvince(i).getCivID() - 1)
                           * (
                              0.65F
                                 + 0.275F
                                    * (CFG.game.getProvince(i).getDevelopmentLevel() / CFG.game.getCiv(CFG.game.getProvince(i).getCivID()).getTechnologyLevel())
                                 + CFG.game.getProvince(i).getGrowthRate_Population_WithFarm_WithTerrain() * 0.075F
                           )
                           * (
                              1.0F
                                 + CFG.game.getCiv(CFG.game.getProvince(i).getCivID()).getModifier_EconomyGrowth()
                                 + CFG.game.getGameScenarios().getScenario_EconomyGrowthRate_Modifier()
                           )
                           * Game_Calendar.GAME_SPEED
                     )
                     > 0.0F) {
                     if (CFG.game.getProvince(i).getEconomy() < modifiedStartingEco * CFG.game.getProvince(i).getGrowthRate_Population()) {
                        tempEco += CFG.game.getGameScenarios().getScenario_StartingEconomy()
                           * (
                              0.00425F
                                 * (
                                    1.0F
                                       - (float)CFG.game.getProvince(i).getPopulationData().getPopulation()
                                          / CFG.game.getGameScenarios().getScenario_StartingEconomy()
                                 )
                           )
                           * CFG.game.getProvince(i).getGrowthRate_Population_WithFarm_WithTerrain()
                           * 0.7548F
                           * Math.min(CFG.game.getProvince(i).getDevelopmentLevel() * 3.165134F, 1.0F);
                     }

                     if ((
                           tempEco *= Math.max(
                              0.086486F,
                              1.0F - 0.4F * CFG.game.getProvince(i).getEconomy() / (CFG.game.getGameScenarios().getScenario_StartingEconomy() * 1.4681F)
                           )
                        )
                        > 0.0F) {
                        tempEco = tempEco * 0.1F
                           + CFG.oR.nextInt(Math.max((int)(tempEco * 1.0F * 100.0F), 1)) / 100.0F
                           - CFG.oR.nextInt(Math.max((int)(tempEco * 0.25F * 100.0F), 1)) / 100.0F;
                     }
                  }

                  CFG.game.getProvince(i).setEconomy((int)(CFG.game.getProvince(i).getEconomy() + tempEco));
                  if (CFG.game.getProvince(i).getCivID() == CFG.game.getProvince(i).getTrueOwnerOfProvince()) {
                     if (happinessChange_ByTaxation.get(CFG.game.getProvince(i).getCivID() - 1) > 0.0F) {
                        CFG.game
                           .getProvince(i)
                           .setHappiness(
                              CFG.game.getProvince(i).getHappiness()
                                 + CFG.oR.nextInt((int)(Math.max(happinessChange_ByTaxation.get(CFG.game.getProvince(i).getCivID() - 1), 0.01F) * 100.0F))
                                    / 10000.0F
                           );
                     } else {
                        CFG.game
                           .getProvince(i)
                           .setHappiness(
                              CFG.game.getProvince(i).getHappiness()
                                 + (
                                       happinessChange_ByTaxation.get(CFG.game.getProvince(i).getCivID() - 1)
                                          + happinessChange_ByTaxation.get(CFG.game.getProvince(i).getCivID() - 1)
                                             * (0.2F - 0.2F * CFG.game.getProvince(i).getProvinceStability())
                                    )
                                    / 100.0F
                           );
                     }
                  } else if (happinessChange_ByTaxation_Occupied.get(CFG.game.getProvince(i).getCivID() - 1) > 0.0F) {
                     CFG.game
                        .getProvince(i)
                        .setHappiness(
                           CFG.game.getProvince(i).getHappiness()
                              + CFG.oR.nextInt(Math.max(1, (int)(happinessChange_ByTaxation_Occupied.get(CFG.game.getProvince(i).getCivID() - 1) * 100.0F)))
                                 / 10000.0F
                        );
                  } else {
                     CFG.game
                        .getProvince(i)
                        .setHappiness(
                           CFG.game.getProvince(i).getHappiness() + happinessChange_ByTaxation_Occupied.get(CFG.game.getProvince(i).getCivID() - 1) / 100.0F
                        );
                  }

                  if (CFG.ideologiesManager.getIdeology(CFG.game.getCiv(CFG.game.getProvince(i).getCivID()).getIdeologyID()).REVOLUTIONARY) {
                     CFG.game.getProvince(i).setRevolutionaryRisk(0.0F);
                  } else {
                     float fRisk = CFG.game.getProvince(i).getRevolutionaryRisk();
                     if (fRisk > 0.0075F) {
                        fRisk -= Math.min(fRisk / 10.0F, 0.012437F) * (1.0F - CFG.game.getCiv(CFG.game.getProvince(i).getCivID()).getWarWeariness());
                     }

                     if (CFG.game.getProvince(i).getHappiness() < Game_Action.RISE_REVOLT_RISK_HAPPINESS) {
                        float nModifier = CFG.game.getCiv(CFG.game.getProvince(i).getCivID()).getMoney() < -1000L
                           ? 1.0F
                           : Math.min(
                              0.0725F
                                 + CFG.game.getCiv(CFG.game.getProvince(i).getCivID()).getTaxationLevel()
                                    / CFG.ideologiesManager.getIdeology(CFG.game.getCiv(CFG.game.getProvince(i).getCivID()).getIdeologyID()).ACCEPTABLE_TAXATION,
                              1.0F
                           );
                        fRisk += nModifier * ageRiskModifier * (Game_Action.RISE_REVOLT_RISK_HAPPINESS - CFG.game.getProvince(i).getHappiness()) / 14.0F;
                     }

                     CFG.game.getProvince(i).setRevolutionaryRisk(fRisk);
                  }

                  CFG.game.getProvince(i).runSupportRebels();
                  CFG.game.getProvince(i).updateNewColony();
               }
            }
         }

         tempCivs.clear();
         tempCivs = null;
         happinessChange_ByTaxation.clear();
         happinessChange_ByTaxation_Occupied.clear();
         goodsUpdate.clear();
         devUpdate.clear();
         ecoUpdate.clear();
         Gdx.app.log("AoC", "STA2: " + (System.currentTimeMillis() - tempTime));
         Commands.addMessage("STA2: " + (System.currentTimeMillis() - tempTime));
         tempTime = System.currentTimeMillis();
      } else {
         tempCivs = new ArrayList<>();
         happinessChange_ByTaxation = new ArrayList<>();
         happinessChange_ByTaxation_Occupied = new ArrayList<>();
         goodsUpdate = new ArrayList<>();
         devUpdate = new ArrayList<>();
         ecoUpdate = new ArrayList<>();
         ageRiskModifier = CFG.gameAges.getAge_RevolutionaryRiskModifier(Game_Calendar.CURRENT_AGEID);
         ageDevMod = CFG.gameAges.getAge_DevelopmentLevel_Increase(Game_Calendar.CURRENT_AGEID);

         for (int ix = 1; ix < CFG.game.getCivsSize(); ix++) {
            if (CFG.game.getCiv(ix).getNumOfProvinces() > 0) {
               happinessChange_ByTaxation.add(CFG.game_NextTurnUpdate.getHappinessChange_ByTaxation(ix));
               happinessChange_ByTaxation_Occupied.add(CFG.game_NextTurnUpdate.getHappinessChange_ByTaxation_Occupied(ix));
               goodsUpdate.add(
                  CFG.game.getCiv(ix).getSpendings_Goods() < CFG.ideologiesManager.getIdeology(CFG.game.getCiv(ix).getIdeologyID()).getMin_Goods(ix)
                     ? -0.0192864F
                        * (
                           (CFG.ideologiesManager.getIdeology(CFG.game.getCiv(ix).getIdeologyID()).getMin_Goods(ix) - CFG.game.getCiv(ix).getSpendings_Goods())
                              / CFG.ideologiesManager.getIdeology(CFG.game.getCiv(ix).getIdeologyID()).getMin_Goods(ix)
                        )
                     : (
                           -CFG.ideologiesManager.getIdeology(CFG.game.getCiv(ix).getIdeologyID()).getMin_Goods(ix)
                              + 0.013845F
                              + CFG.game.getCiv(ix).getSpendings_Goods()
                        )
                        * CFG.gameAges.getAge_Population_GrowthRate(Game_Calendar.CURRENT_AGEID)
               );
               devUpdate.add(
                  CFG.game.getCiv(ix).getSpendings_Investments() < CFG.ideologiesManager.getIdeology(CFG.game.getCiv(ix).getIdeologyID()).MIN_INVESTMENTS
                     ? -0.642864F
                        * (
                           (
                                 CFG.ideologiesManager.getIdeology(CFG.game.getCiv(ix).getIdeologyID()).MIN_INVESTMENTS
                                    - CFG.game.getCiv(ix).getSpendings_Investments()
                              )
                              / CFG.ideologiesManager.getIdeology(CFG.game.getCiv(ix).getIdeologyID()).MIN_INVESTMENTS
                        )
                     : -CFG.ideologiesManager.getIdeology(CFG.game.getCiv(ix).getIdeologyID()).MIN_INVESTMENTS
                        + 0.01F
                        + CFG.game.getCiv(ix).getSpendings_Investments()
               );
               ecoUpdate.add(
                  CFG.game.getCiv(ix).getSpendings_Investments() < CFG.ideologiesManager.getIdeology(CFG.game.getCiv(ix).getIdeologyID()).MIN_INVESTMENTS
                     ? -0.0192864F
                        * (
                           (
                                 CFG.ideologiesManager.getIdeology(CFG.game.getCiv(ix).getIdeologyID()).MIN_INVESTMENTS
                                    - CFG.game.getCiv(ix).getSpendings_Investments()
                              )
                              / CFG.ideologiesManager.getIdeology(CFG.game.getCiv(ix).getIdeologyID()).MIN_INVESTMENTS
                        )
                     : (
                           -CFG.ideologiesManager.getIdeology(CFG.game.getCiv(ix).getIdeologyID()).MIN_INVESTMENTS
                              + 0.01F
                              + CFG.game.getCiv(ix).getSpendings_Investments()
                        )
                        * CFG.gameAges.getAge_Economy_GrowthRate(Game_Calendar.CURRENT_AGEID)
               );
            } else {
               happinessChange_ByTaxation.add(1.0F);
               happinessChange_ByTaxation_Occupied.add(1.0F);
               goodsUpdate.add(1.0F);
               devUpdate.add(1.0F);
               ecoUpdate.add(1.0F);
            }

            CFG.game.getCiv(ix).civGameData.civAggresionLevel = Math.max(0.0F, CFG.game.getCiv(ix).civGameData.civAggresionLevel - 8.0E-4F);
         }

         float modifiedStartingPop = CFG.game.getGameScenarios().getScenario_StartingPopulation() * 0.1875F;
         float modifiedStartingEco = CFG.game.getGameScenarios().getScenario_StartingEconomy() * 0.0925F;
         Gdx.app.log("AoC", "STA2_PREP: " + (System.currentTimeMillis() - tempTime));
         Commands.addMessage("STA2_PREP: " + (System.currentTimeMillis() - tempTime));
         tempTime = System.currentTimeMillis();

         for (int ix = 0; ix < CFG.game.getProvincesSize(); ix++) {
            if (!CFG.game.getProvince(ix).getSeaProvince() && CFG.game.getProvince(ix).getWasteland() < 0) {
               if (CFG.game.getProvince(ix).getCivID() > 0) {
                  if (CFG.game.getProvince(ix).getTrueOwnerOfProvince() == CFG.game.getProvince(ix).getCivID()
                     && !CFG.ideologiesManager.getIdeology(CFG.game.getCiv(CFG.game.getProvince(ix).getCivID()).getIdeologyID()).REVOLUTIONARY) {
                     CFG.game.getProvince(ix).getCore().increaseOwnership(CFG.game.getProvince(ix).getCivID(), ix);
                  }

                  if (CFG.game.getProvince(ix).getDevelopmentLevel() < 1.0F) {
                     if (CFG.game.getProvince(ix).getCivID() == CFG.game.getProvince(ix).getTrueOwnerOfProvince()) {
                        float tempDevelopmentChange = ageDevMod
                           * devUpdate.get(CFG.game.getProvince(ix).getCivID() - 1)
                           * Math.min(CFG.game.getProvince(ix).getGrowthRate_Population_WithFarm_WithTerrain() * 0.45F, 0.3705F);
                        CFG.game.getProvince(ix).setDevelopmentLevel(CFG.game.getProvince(ix).getDevelopmentLevel() + tempDevelopmentChange);
                     } else {
                        CFG.game.getProvince(ix).setDevelopmentLevel(CFG.game.getProvince(ix).getDevelopmentLevel() - CFG.oR.nextInt(275) / 100000.0F);
                     }
                  }

                  float tempPopGrowthx;
                  if ((
                        tempPopGrowthx = CFG.game.getProvince(ix).getPopulationData().getPopulation()
                           * (
                              0.2F
                                 + (
                                    CFG.ideologiesManager
                                             .getIdeology(CFG.game.getCiv(CFG.game.getProvince(ix).getCivID()).getIdeologyID())
                                             .getMin_Goods(CFG.game.getProvince(ix).getCivID())
                                          < CFG.game.getCiv(CFG.game.getProvince(ix).getCivID()).getSpendings_Goods()
                                       ? CFG.oR.nextInt(50) / 100.0F
                                       : 0.5F
                                 )
                           )
                           * goodsUpdate.get(CFG.game.getProvince(ix).getCivID() - 1)
                           * (
                              (
                                    0.01F
                                       + CFG.game.getProvince(ix).getGrowthRate_Population_WithFarm_WithTerrain()
                                       + CFG.game.getCiv(CFG.game.getProvince(ix).getCivID()).getModifier_PopGrowth()
                                 )
                                 * 0.485F
                           )
                           * (
                              1.0F
                                 + CFG.game.getProvince(ix).getDevelopmentLevel() / 63.3468F
                                 + CFG.game.getGameScenarios().getScenario_PopulationGrowthRate_Modifier()
                           )
                           * Game_Calendar.GAME_SPEED
                     )
                     > 0.0F) {
                     if (CFG.game.getProvince(ix).getPopulationData().getPopulation()
                        < modifiedStartingPop * CFG.game.getProvince(ix).getGrowthRate_Population()) {
                        tempPopGrowthx += CFG.game.getGameScenarios().getScenario_StartingPopulation()
                           * (
                              0.00725F
                                 * (
                                    1.0F
                                       - (float)CFG.game.getProvince(ix).getPopulationData().getPopulation()
                                          / CFG.game.getGameScenarios().getScenario_StartingPopulation()
                                 )
                           )
                           * CFG.game.getProvince(ix).getGrowthRate_Population()
                           * Math.min(CFG.game.getProvince(ix).getDevelopmentLevel() * 2.7469234F, 1.0F);
                     }

                     if ((
                           tempPopGrowthx = 1.0F
                              + tempPopGrowthx
                                 * Math.max(
                                    0.0865F,
                                    1.0F
                                       - 0.4F
                                          * CFG.game.getProvince(ix).getPopulationData().getPopulation()
                                          / (CFG.game.getGameScenarios().getScenario_StartingPopulation() * 1.825F)
                                 )
                        )
                        > 0.0F) {
                        tempPopGrowthx = tempPopGrowthx * 0.1F
                           + CFG.oR.nextInt(Math.max((int)(tempPopGrowthx * 1.0F * 100.0F), 1)) / 100.0F
                           - CFG.oR.nextInt(Math.max((int)(tempPopGrowthx * 0.325F * 100.0F), 1)) / 100.0F;
                     }
                  }

                  if ((int)tempPopGrowthx != 0) {
                     CFG.game
                        .getProvince(ix)
                        .getPopulationData()
                        .setPopulationOfCivID(
                           CFG.game.getProvince(ix).getCivID(),
                           CFG.game.getProvince(ix).getPopulationData().getPopulationOfCivID(CFG.game.getProvince(ix).getCivID()) + (int)tempPopGrowthx
                        );
                  }

                  if (CFG.game.getCiv(CFG.game.getProvince(ix).getCivID()).getSpendings_Goods()
                     < CFG.ideologiesManager
                        .getIdeology(CFG.game.getCiv(CFG.game.getProvince(ix).getCivID()).getIdeologyID())
                        .getMin_Goods(CFG.game.getProvince(ix).getCivID())) {
                     float tempHapp = -0.01225F
                        * (
                           (
                                 CFG.ideologiesManager
                                       .getIdeology(CFG.game.getCiv(CFG.game.getProvince(ix).getCivID()).getIdeologyID())
                                       .getMin_Goods(CFG.game.getProvince(ix).getCivID())
                                    - CFG.game.getCiv(CFG.game.getProvince(ix).getCivID()).getSpendings_Goods()
                              )
                              / CFG.ideologiesManager
                                 .getIdeology(CFG.game.getCiv(CFG.game.getProvince(ix).getCivID()).getIdeologyID())
                                 .getMin_Goods(CFG.game.getProvince(ix).getCivID())
                        )
                        * (
                           0.01F
                              + CFG.game.getProvince(ix).getDevelopmentLevel() * 1.25F
                              + CFG.game.getProvince(ix).getGrowthRate_Population_WithFarm() * 0.135F
                        );
                     if (tempHapp > 0.0F) {
                        tempHapp *= 1.0F - 0.625F * CFG.game.getCiv(CFG.game.getProvince(ix).getCivID()).civGameData.fWarWeariness;
                     }

                     CFG.game.getProvince(ix).setHappiness(CFG.game.getProvince(ix).getHappiness() + tempHapp);
                     float tempEcoPop = CFG.game.getProvince(ix).getEconomy()
                        * -0.00625F
                        * (
                           (
                                 CFG.ideologiesManager
                                       .getIdeology(CFG.game.getCiv(CFG.game.getProvince(ix).getCivID()).getIdeologyID())
                                       .getMin_Goods(CFG.game.getProvince(ix).getCivID())
                                    - CFG.game.getCiv(CFG.game.getProvince(ix).getCivID()).getSpendings_Goods()
                              )
                              / CFG.ideologiesManager
                                 .getIdeology(CFG.game.getCiv(CFG.game.getProvince(ix).getCivID()).getIdeologyID())
                                 .getMin_Goods(CFG.game.getProvince(ix).getCivID())
                        )
                        * (
                           0.01F
                              + CFG.game.getProvince(ix).getDevelopmentLevel() * 0.5475F
                              + CFG.game.getProvince(ix).getGrowthRate_Population_WithFarm_WithTerrain() * 0.195F
                        );
                     CFG.game.getProvince(ix).setEconomy((int)(CFG.game.getProvince(ix).getEconomy() + tempEcoPop));
                  }

                  float tempEcox;
                  if ((
                        tempEcox = Math.max(
                              (float)CFG.game.getProvince(ix).getEconomy(),
                              CFG.game.getGameScenarios().getScenario_StartingPopulation() * 0.0825F * CFG.game.getProvince(ix).getGrowthRate_Population()
                           )
                           * ecoUpdate.get(CFG.game.getProvince(ix).getCivID() - 1)
                           * (
                              0.65F
                                 + 0.275F
                                    * (
                                       CFG.game.getProvince(ix).getDevelopmentLevel()
                                          / CFG.game.getCiv(CFG.game.getProvince(ix).getCivID()).getTechnologyLevel()
                                    )
                                 + CFG.game.getProvince(ix).getGrowthRate_Population_WithFarm_WithTerrain() * 0.075F
                           )
                           * (
                              1.0F
                                 + CFG.game.getCiv(CFG.game.getProvince(ix).getCivID()).getModifier_EconomyGrowth()
                                 + CFG.game.getGameScenarios().getScenario_EconomyGrowthRate_Modifier()
                           )
                           * Game_Calendar.GAME_SPEED
                     )
                     > 0.0F) {
                     if (CFG.game.getProvince(ix).getEconomy() < modifiedStartingEco * CFG.game.getProvince(ix).getGrowthRate_Population()) {
                        tempEcox += CFG.game.getGameScenarios().getScenario_StartingEconomy()
                           * (
                              0.00425F
                                 * (
                                    1.0F
                                       - (float)CFG.game.getProvince(ix).getPopulationData().getPopulation()
                                          / CFG.game.getGameScenarios().getScenario_StartingEconomy()
                                 )
                           )
                           * CFG.game.getProvince(ix).getGrowthRate_Population_WithFarm_WithTerrain()
                           * 0.7548F
                           * Math.min(CFG.game.getProvince(ix).getDevelopmentLevel() * 3.165134F, 1.0F);
                     }

                     if ((
                           tempEcox *= Math.max(
                              0.086486F,
                              1.0F - 0.4F * CFG.game.getProvince(ix).getEconomy() / (CFG.game.getGameScenarios().getScenario_StartingEconomy() * 1.4681F)
                           )
                        )
                        > 0.0F) {
                        tempEcox = tempEcox * 0.1F
                           + CFG.oR.nextInt(Math.max((int)(tempEcox * 1.0F * 100.0F), 1)) / 100.0F
                           - CFG.oR.nextInt(Math.max((int)(tempEcox * 0.25F * 100.0F), 1)) / 100.0F;
                     }
                  }

                  CFG.game.getProvince(ix).setEconomy((int)(CFG.game.getProvince(ix).getEconomy() + tempEcox));
                  if (CFG.game.getProvince(ix).getCivID() == CFG.game.getProvince(ix).getTrueOwnerOfProvince()) {
                     if (happinessChange_ByTaxation.get(CFG.game.getProvince(ix).getCivID() - 1) > 0.0F) {
                        CFG.game
                           .getProvince(ix)
                           .setHappiness(
                              CFG.game.getProvince(ix).getHappiness()
                                 + CFG.oR.nextInt((int)(Math.max(happinessChange_ByTaxation.get(CFG.game.getProvince(ix).getCivID() - 1), 0.01F) * 100.0F))
                                    / 10000.0F
                           );
                     } else {
                        CFG.game
                           .getProvince(ix)
                           .setHappiness(
                              CFG.game.getProvince(ix).getHappiness()
                                 + (
                                       happinessChange_ByTaxation.get(CFG.game.getProvince(ix).getCivID() - 1)
                                          + happinessChange_ByTaxation.get(CFG.game.getProvince(ix).getCivID() - 1)
                                             * (0.2F - 0.2F * CFG.game.getProvince(ix).getProvinceStability())
                                    )
                                    / 100.0F
                           );
                     }
                  } else if (happinessChange_ByTaxation_Occupied.get(CFG.game.getProvince(ix).getCivID() - 1) > 0.0F) {
                     CFG.game
                        .getProvince(ix)
                        .setHappiness(
                           CFG.game.getProvince(ix).getHappiness()
                              + CFG.oR.nextInt(Math.max(1, (int)(happinessChange_ByTaxation_Occupied.get(CFG.game.getProvince(ix).getCivID() - 1) * 100.0F)))
                                 / 10000.0F
                        );
                  } else {
                     CFG.game
                        .getProvince(ix)
                        .setHappiness(
                           CFG.game.getProvince(ix).getHappiness() + happinessChange_ByTaxation_Occupied.get(CFG.game.getProvince(ix).getCivID() - 1) / 100.0F
                        );
                  }

                  if (CFG.ideologiesManager.getIdeology(CFG.game.getCiv(CFG.game.getProvince(ix).getCivID()).getIdeologyID()).REVOLUTIONARY) {
                     CFG.game.getProvince(ix).setRevolutionaryRisk(0.0F);
                  } else {
                     float fRiskx = CFG.game.getProvince(ix).getRevolutionaryRisk();
                     if (fRiskx > 0.0075F) {
                        fRiskx -= Math.min(fRiskx / 10.0F, 0.012437F) * (1.0F - CFG.game.getCiv(CFG.game.getProvince(ix).getCivID()).getWarWeariness());
                     }

                     if (CFG.game.getProvince(ix).getHappiness() < Game_Action.RISE_REVOLT_RISK_HAPPINESS) {
                        float nModifier = CFG.game.getCiv(CFG.game.getProvince(ix).getCivID()).getMoney() < -1000L
                           ? 1.0F
                           : Math.min(
                              0.0725F
                                 + CFG.game.getCiv(CFG.game.getProvince(ix).getCivID()).getTaxationLevel()
                                    / CFG.ideologiesManager.getIdeology(CFG.game.getCiv(CFG.game.getProvince(ix).getCivID()).getIdeologyID()).ACCEPTABLE_TAXATION,
                              1.0F
                           );
                        fRiskx += nModifier * ageRiskModifier * (Game_Action.RISE_REVOLT_RISK_HAPPINESS - CFG.game.getProvince(ix).getHappiness()) / 14.0F;
                     }

                     CFG.game.getProvince(ix).setRevolutionaryRisk(fRiskx);
                  }

                  CFG.game.getProvince(ix).runSupportRebels();
                  CFG.game.getProvince(ix).updateNewColony();
               } else if (CFG.oR.nextInt(50) > 38) {
                  CFG.game.getProvince(ix).updateArmy(0, CFG.game.getProvince(ix).getArmyCivID(0) - 4 + CFG.oR.nextInt(11));
               }
            }
         }

         tempCivs.clear();
         tempCivs = null;
         happinessChange_ByTaxation.clear();
         happinessChange_ByTaxation_Occupied.clear();
         goodsUpdate.clear();
         devUpdate.clear();
         ecoUpdate.clear();
         Gdx.app.log("AoC", "STA2: " + (System.currentTimeMillis() - tempTime));
         Commands.addMessage("STA2: " + (System.currentTimeMillis() - tempTime));
         tempTime = System.currentTimeMillis();
      }
   }
}
