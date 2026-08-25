package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.Gdx;
import java.util.ArrayList;
import java.util.List;

public class AI {
   public boolean doneLoadingOrders = false;
   public int iLoadingTurnActionsOfCivID = 0;
   public List<AI_Style> lAI_Styles = new ArrayList<>();
   public int NUM_OF_CIVS_IN_THE_GAME = 0;
   public int PLAYABLE_PROVINCES = 1;
   public int MIN_NUM_OF_RIVALS = 1;
   public List<List<AI_Frontline>> lFrontLines = new ArrayList<>();
   public List<Integer> lNeutralProvincesWithSeaAccess = new ArrayList<>();
   public int iNeutralProvincesWithSeaAccessSize = 0;
   public List<Integer> lWastelandProvincesWithSeaAccess = new ArrayList<>();
   public static int REBUILD_PERSONALITY = 87;
   public static final int STATUS_QUO_TURNS = 39;
   public static final int STATUS_QUO_TURNS_NO_ONE_ATTACKED = 19;
   public static final int STATUS_QUO_NO_PROGRESS = 49;
   public static final int STATUS_QUO_TOO_LONG = 299;
   public int iNumOfColonizedProvinces = 0;
   public final int DANGER_EXTRA_AT_WAR = 450;
   public AI.Expand expandNeutral;

   public AI() {
      this.updateExpand();
      this.lAI_Styles.add(new AI_Style());
      this.lAI_Styles.add(new AI_Style_Communism());
      this.lAI_Styles.add(new AI_Style_Horde());
      this.lAI_Styles.add(new AI_Style_Fascism());
      this.lAI_Styles.add(new AI_Style_CityState());
      this.lAI_Styles.add(new AI_Style_Tribal());
      this.lAI_Styles.add(new AI_Style_Rebels());
      this.build_RebuildPersonality();
   }

   public final int getAIStyle_ByTag(String nTag) {
      for (int i = 0; i < this.lAI_Styles.size(); i++) {
         if (this.lAI_Styles.get(i).TAG.equals(nTag)) {
            return i;
         }
      }

      return 0;
   }

   public final AI_Style getAI_Style(int i) {
      try {
         return this.lAI_Styles.get(i);
      } catch (IndexOutOfBoundsException var3) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var3);
         }

         return this.lAI_Styles.get(0);
      }
   }

   public final void resetNeutralProvincesWithSeaAccess() {
      this.lNeutralProvincesWithSeaAccess.clear();
      this.iNeutralProvincesWithSeaAccessSize = 0;
   }

   public final void addNeutralProvincesWithSeaAccess(int nProvinceID) {
      this.lNeutralProvincesWithSeaAccess.add(nProvinceID);
   }

   public final void resetWastelandProvincesWithSeaAccess() {
      this.lWastelandProvincesWithSeaAccess.clear();
   }

   public final void addWastelandProvincesWithSeaAccess(int nProvinceID) {
      this.lWastelandProvincesWithSeaAccess.add(nProvinceID);
   }

   public final void build_RebuildPersonality() {
      REBUILD_PERSONALITY = 79 + CFG.oR.nextInt(20);
   }

   public final void checkCurrentWars_LookingForPeace() {
      try {
         for (int i = 0; i < CFG.game.getWarsSize(); i++) {
            for (int j = 0; j < CFG.game.getWar(i).getDefendersSize(); j++) {
               if (CFG.game.getCiv(CFG.game.getWar(i).getDefenderID(j).getCivID()).getNumOfProvinces() == 0
                  && !CFG.ideologiesManager.getIdeology(CFG.game.getCiv(CFG.game.getWar(i).getDefenderID(j).getCivID()).getIdeologyID()).REVOLUTIONARY) {
                  for (int k = 0; k < CFG.game.getWar(i).getAggressorsSize(); k++) {
                     if (!CFG.ideologiesManager.getIdeology(CFG.game.getCiv(CFG.game.getWar(i).getAggressorID(k).getCivID()).getIdeologyID()).REVOLUTIONARY) {
                        CFG.game
                           .getCiv(CFG.game.getWar(i).getAggressorID(k).getCivID())
                           .getCivilization_Diplomacy_GameData()
                           .messageBox
                           .addMessage(new Message_WeCanSignPeace(CFG.game.getWar(i).getDefenderID(j).getCivID()));
                     }
                  }
               }
            }

            for (int var8 = 0; var8 < CFG.game.getWar(i).getAggressorsSize(); var8++) {
               if (CFG.game.getCiv(CFG.game.getWar(i).getAggressorID(var8).getCivID()).getNumOfProvinces() == 0
                  && !CFG.ideologiesManager.getIdeology(CFG.game.getCiv(CFG.game.getWar(i).getAggressorID(var8).getCivID()).getIdeologyID()).REVOLUTIONARY) {
                  for (int kx = 0; kx < CFG.game.getWar(i).getDefendersSize(); kx++) {
                     if (!CFG.ideologiesManager.getIdeology(CFG.game.getCiv(CFG.game.getWar(i).getDefenderID(kx).getCivID()).getIdeologyID()).REVOLUTIONARY) {
                        CFG.game
                           .getCiv(CFG.game.getWar(i).getDefenderID(kx).getCivID())
                           .getCivilization_Diplomacy_GameData()
                           .messageBox
                           .addMessage(new Message_WeCanSignPeace(CFG.game.getWar(i).getAggressorID(var8).getCivID()));
                     }
                  }
               }
            }

            if (CFG.game.getWar(i).iLastFight_InTunrs > (CFG.game.getWar(i).wasAnyAttack ? 39 : 19)
               || CFG.game.getWar(i).iLastTurn_ConqueredProvince < Game_Calendar.TURN_ID - 49
               || CFG.game.getWar(i).getWarTurnID() < Game_Calendar.TURN_ID - (299 + CFG.game.getCivsSize())) {
               for (int var9 = 0; var9 < CFG.game.getWar(i).getAggressorsSize(); var9++) {
                  if (!CFG.ideologiesManager.getIdeology(CFG.game.getCiv(CFG.game.getWar(i).getAggressorID(var9).getCivID()).getIdeologyID()).REVOLUTIONARY) {
                     for (int kxx = 0; kxx < CFG.game.getWar(i).getDefendersSize(); kxx++) {
                        if (!CFG.ideologiesManager.getIdeology(CFG.game.getCiv(CFG.game.getWar(i).getDefenderID(kxx).getCivID()).getIdeologyID()).REVOLUTIONARY
                           )
                         {
                           CFG.game
                              .getCiv(CFG.game.getWar(i).getDefenderID(kxx).getCivID())
                              .getCivilization_Diplomacy_GameData()
                              .messageBox
                              .addMessage(new Message_WeCanSignPeace(CFG.game.getWar(i).getAggressorID(var9).getCivID()));
                        }
                     }
                  }
               }
            }
         }
      } catch (IndexOutOfBoundsException var4) {
         CFG.exceptionStack(var4);
      } catch (NullPointerException var5) {
         CFG.exceptionStack(var5);
      }
   }

   public final void turnOrders() {
      this.doneLoadingOrders = false;

      try {
         for (int i = 1; i < CFG.game.getCivsSize(); i++) {
            if (!CFG.game.getCiv(i).getControlledByPlayer()) {
               if (Game_Calendar.TURN_ID % REBUILD_PERSONALITY == i % REBUILD_PERSONALITY && CFG.oR.nextInt(100) > 54) {
                  CFG.game.getCiv(i).buildCivPersonality();
               } else if (Game_Calendar.TURN_ID % CFG.game.getCiv(i).civGameData.civPersonality.REBUILD_PERSONALITY_MORE_OFTEN
                     == i % CFG.game.getCiv(i).civGameData.civPersonality.REBUILD_PERSONALITY_MORE_OFTEN
                  && CFG.oR.nextInt(100) > 28) {
                  CFG.game.getCiv(i).buildCivPersonality_MoreOften();
               }
            }
         }

         this.checkCurrentWars_LookingForPeace();

         for (int var12 = 1; var12 < CFG.game.getCivsSize(); var12++) {
            try {
               if (!CFG.game.getCiv(var12).getControlledByPlayer()
                  || AI_Assistant.ENABLED && var12 == CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) {
                  if (CFG.game.getCiv(var12).getNumOfProvinces() > 0) {
                     this.iLoadingTurnActionsOfCivID = var12;
                     CFG.setRender_3(true);
                     this.lAI_Styles.get(CFG.game.getCiv(var12).getAI_Style()).turnOrdersEssential(var12);
                     this.lAI_Styles.get(CFG.game.getCiv(var12).getAI_Style()).turnOrders(var12);
                  } else {
                     this.lAI_Styles.get(CFG.game.getCiv(var12).getAI_Style()).respondToMessages(var12);
                  }
               }
            } catch (IndexOutOfBoundsException var8) {
               CFG.exceptionStack(var8);
            } catch (ArithmeticException var9) {
               CFG.exceptionStack(var9);
            } catch (NullPointerException var10) {
               CFG.exceptionStack(var10);
            }
         }

         for (int var13 = Game_Calendar.TURN_ID % 6; var13 < CFG.game.getCivsSize(); var13 += 6) {
            if (!CFG.game.getCiv(var13).getControlledByPlayer()) {
               this.lAI_Styles.get(CFG.game.getCiv(var13).getAI_Style()).manageVassalsTribute(var13);
            }
         }
      } finally {
         this.doneLoadingOrders = true;
      }
   }

   public final void updateMinRivals() {
      this.MIN_NUM_OF_RIVALS = (int)Math.min(3.0, Math.ceil((CFG.oAI.NUM_OF_CIVS_IN_THE_GAME - 1) / 2.0F));
   }

   public final void buildAIData() {
      this.resetNeutralProvincesWithSeaAccess();
      this.resetWastelandProvincesWithSeaAccess();
      this.iNumOfColonizedProvinces = 0;
      this.NUM_OF_CIVS_IN_THE_GAME = 0;

      for (int i = 1; i < CFG.game.getCivsSize(); i++) {
         CFG.game.getCiv(i).setSeaAccess(0);
         CFG.game.getCiv(i).clearSeaAccess_Provinces();
         CFG.game.getCiv(i).clearSeaAccess_PortProvinces();
         CFG.game.getCiv(i).setBordersWithEnemy(0);
         CFG.game.getCiv(i).setIsAtWar(false);
         CFG.game.getCiv(i).setCanExpandOnContinent(false);
         CFG.game.getCiv(i).setNumOfNeighboringNeutralProvinces(0);
         CFG.game.getCiv(i).lArmiesPosition.clear();
         CFG.game.getCiv(i).iArmiesPositionSize = 0;
         CFG.game.getCiv(i).lBorderWithCivs.clear();
         CFG.game.getCiv(i).iBorderWithCivsSize = 0;
         CFG.game.getCiv(i).iAveragePopulation = 1;
         CFG.game.getCiv(i).lBordersWithNeutralProvincesID.clear();
         CFG.game.getCiv(i).lBordersWithWastelandProvincesID.clear();
         CFG.game.getCiv(i).civGameData.civPlans.updateObsolateMissions();
         CFG.game.countAvarageDevelopmentLevel_Float(i);
         CFG.game.getCiv(i).lProvincesWithHighRevRisk.clear();
         CFG.game.getCiv(i).isAtWarWithCivs.clear();
         CFG.game.getCiv(i).iNumOf_Forts = 0;
         CFG.game.getCiv(i).iNumOf_Towers = 0;
         CFG.game.getCiv(i).iNumOf_Ports = 0;
         CFG.game.getCiv(i).iNumOf_Farms = 0;
         CFG.game.getCiv(i).iNumOf_Farms_ProvincesPossibleToBuild = 0;
         CFG.game.getCiv(i).iNumOf_Workshops = 0;
         CFG.game.getCiv(i).iNumOf_Libraries = 0;
         CFG.game.getCiv(i).iNumOf_Armories = 0;
         CFG.game.getCiv(i).iNumOf_SuppliesCamp = 0;
         this.iNumOfColonizedProvinces = this.iNumOfColonizedProvinces + CFG.game.getCiv(i).civGameData.lColonies_Founded.size();
      }

      ViewsManager.updateMaxPopulation();
      ViewsManager.updateMaxEconomy();

      for (int var13 = 1; var13 < CFG.game.getCivsSize() - 1; var13++) {
         for (int j = var13 + 1; j < CFG.game.getCivsSize(); j++) {
            if (CFG.game.getCivsAtWar(var13, j)) {
               CFG.game.getCiv(var13).setIsAtWar(true);
               CFG.game.getCiv(j).setIsAtWar(true);
               CFG.game.getCiv(var13).isAtWarWithCivs.add(j);
               CFG.game.getCiv(j).isAtWarWithCivs.add(var13);
            }
         }

         for (int var9 = 0; var9 < CFG.game.getCiv(var13).getCivRegionsSize(); var9++) {
            CFG.game.getCiv(var13).getCivRegion(var9).iAveragePotential = 0;
         }
      }

      for (int var14 = 0; var14 < CFG.game.getCivsSize(); var14++) {
         if (CFG.game.getCiv(var14).getNumOfProvinces() > 0) {
            this.NUM_OF_CIVS_IN_THE_GAME++;
            if (CFG.game.getCiv(var14).isAtWar()) {
               CFG.game.getCiv(var14).civGameData.iNumOfTurnsAtWar++;
            } else {
               CFG.game.getCiv(var14).civGameData.iNumOfTurnsAtWar -= 2;
               if (CFG.game.getCiv(var14).civGameData.iNumOfTurnsAtWar < 0) {
                  CFG.game.getCiv(var14).civGameData.iNumOfTurnsAtWar = 0;
               }
            }
         }
      }

      this.updateMinRivals();
      this.PLAYABLE_PROVINCES = 0;

      for (int var15 = 0; var15 < CFG.game.getProvincesSize(); var15++) {
         if (!CFG.game.getProvince(var15).getSeaProvince()) {
            if (CFG.game.getProvince(var15).getWasteland() >= 0) {
               if (Game_Calendar.getColonizationOfWastelandIsEnabled()) {
                  for (int jx = 0; jx < CFG.game.getProvince(var15).getNeighboringSeaProvincesSize(); jx++) {
                     if (CFG.game.getProvince(CFG.game.getProvince(var15).getNeighboringSeaProvinces(jx)).getLevelOfPort() == -2) {
                        this.addWastelandProvincesWithSeaAccess(var15);
                        break;
                     }
                  }
               }
            } else {
               this.buildProvinceData(var15);
               this.PLAYABLE_PROVINCES++;
            }
         }
      }

      for (int var16 = 1; var16 < CFG.game.getCivsSize(); var16++) {
         for (int jxx = 0; jxx < CFG.game.getCiv(var16).getCivRegionsSize(); jxx++) {
            if (CFG.game.getCiv(var16).getCivRegion(jxx).getProvincesSize() > 0) {
               Civilization_Region var26 = CFG.game.getCiv(var16).getCivRegion(jxx);
               var26.iAveragePotential = var26.iAveragePotential / CFG.game.getCiv(var16).getCivRegion(jxx).getProvincesSize();
            }
         }

         CFG.game.getCiv(var16).iArmiesPositionSize = CFG.game.getCiv(var16).lArmiesPosition.size();
         Civilization var27 = CFG.game.getCiv(var16);
         int var35;
         if (CFG.game.getCiv(var16).getNumOfProvinces() > 0) {
            Civilization var10001 = CFG.game.getCiv(var16);
            var35 = var10001.iAveragePopulation = var10001.iAveragePopulation / CFG.game.getCiv(var16).getNumOfProvinces();
         } else {
            var35 = 1;
         }

         var27.iAveragePopulation = var35;

         for (int var12 = 0; var12 < CFG.game.getCiv(var16).getConstructionsSize(); var12++) {
            if (CFG.game.getCiv(var16).getConstruction(var12).constructionType == ConstructionType.FARM) {
               CFG.game.getCiv(var16).iNumOf_Farms++;
            } else if (CFG.game.getCiv(var16).getConstruction(var12).constructionType == ConstructionType.ARMOURY) {
               CFG.game.getCiv(var16).iNumOf_Armories++;
            } else if (CFG.game.getCiv(var16).getConstruction(var12).constructionType == ConstructionType.TOWER) {
               CFG.game.getCiv(var16).iNumOf_Towers++;
            } else if (CFG.game.getCiv(var16).getConstruction(var12).constructionType == ConstructionType.LIBRARY) {
               CFG.game.getCiv(var16).iNumOf_Libraries++;
            } else if (CFG.game.getCiv(var16).getConstruction(var12).constructionType == ConstructionType.PORT) {
               CFG.game.getCiv(var16).iNumOf_Ports++;
            } else if (CFG.game.getCiv(var16).getConstruction(var12).constructionType == ConstructionType.FORT) {
               CFG.game.getCiv(var16).iNumOf_Forts++;
            } else if (CFG.game.getCiv(var16).getConstruction(var12).constructionType == ConstructionType.SUPPLY) {
               CFG.game.getCiv(var16).iNumOf_SuppliesCamp++;
            }
         }
      }

      this.lFrontLines.clear();

      for (int var17 = 1; var17 < CFG.game.getCivsSize(); var17++) {
         ArrayList<AI_Frontline> nFrontline = new ArrayList<>();
         if (CFG.game.getCiv(var17).getNumOfProvinces() > 0) {
            for (int j2 = 0; j2 < CFG.game.getCiv(var17).getCivRegionsSize(); j2++) {
               for (int k = 0; k < CFG.game.getCiv(var17).getCivRegion(j2).getProvincesSize(); k++) {
                  if (CFG.game.getProvince(CFG.game.getCiv(var17).getCivRegion(j2).getProvince(k)).getDangerLevel() > 0) {
                     for (int u = 0; u < CFG.game.getProvince(CFG.game.getCiv(var17).getCivRegion(j2).getProvince(k)).getNeighboringProvincesSize(); u++) {
                        if (CFG.game
                                 .getProvince(CFG.game.getProvince(CFG.game.getCiv(var17).getCivRegion(j2).getProvince(k)).getNeighboringProvinces(u))
                                 .getCivID()
                              > 0
                           && CFG.game
                                 .getProvince(CFG.game.getProvince(CFG.game.getCiv(var17).getCivRegion(j2).getProvince(k)).getNeighboringProvinces(u))
                                 .getCivID()
                              != var17
                           && !CFG.game
                              .getCivsAreAllied(
                                 var17,
                                 CFG.game
                                    .getProvince(CFG.game.getProvince(CFG.game.getCiv(var17).getCivRegion(j2).getProvince(k)).getNeighboringProvinces(u))
                                    .getCivID()
                              )
                           && CFG.game.getCiv(var17).getPuppetOfCivID()
                              != CFG.game
                                 .getProvince(CFG.game.getProvince(CFG.game.getCiv(var17).getCivRegion(j2).getProvince(k)).getNeighboringProvinces(u))
                                 .getCivID()
                           && CFG.game
                                 .getCiv(
                                    CFG.game
                                       .getProvince(CFG.game.getProvince(CFG.game.getCiv(var17).getCivRegion(j2).getProvince(k)).getNeighboringProvinces(u))
                                       .getCivID()
                                 )
                                 .getPuppetOfCivID()
                              != var17
                           && CFG.game
                                 .getCiv(
                                    CFG.game
                                       .getProvince(CFG.game.getProvince(CFG.game.getCiv(var17).getCivRegion(j2).getProvince(k)).getNeighboringProvinces(u))
                                       .getCivID()
                                 )
                                 .getPuppetOfCivID()
                              != CFG.game.getCiv(var17).getPuppetOfCivID()) {
                           boolean addNew = true;

                           for (int o = 0; o < nFrontline.size(); o++) {
                              if (nFrontline.get(o).iRegionID == j2
                                 && nFrontline.get(o).iWithCivID
                                    == CFG.game
                                       .getProvince(CFG.game.getProvince(CFG.game.getCiv(var17).getCivRegion(j2).getProvince(k)).getNeighboringProvinces(u))
                                       .getCivID()) {
                                 addNew = false;
                                 nFrontline.get(o).lProvinces.add(CFG.game.getCiv(var17).getCivRegion(j2).getProvince(k));
                                 if (CFG.game.getProvince(CFG.game.getCiv(var17).getCivRegion(j2).getProvince(k)).getBordersWithEnemy()) {
                                    nFrontline.get(o).bordersWithEnemy = true;
                                 }
                                 break;
                              }
                           }

                           if (addNew) {
                              nFrontline.add(
                                 new AI_Frontline(
                                    CFG.game.getCiv(var17).getCivRegion(j2).getProvince(k),
                                    j2,
                                    CFG.game
                                       .getProvince(CFG.game.getProvince(CFG.game.getCiv(var17).getCivRegion(j2).getProvince(k)).getNeighboringProvinces(u))
                                       .getCivID(),
                                    CFG.game.getProvince(CFG.game.getCiv(var17).getCivRegion(j2).getProvince(k)).getBordersWithEnemy()
                                 )
                              );
                           }
                        }
                     }
                  }
               }
            }
         }

         this.lFrontLines.add(nFrontline);
      }

      for (int var18 = 1; var18 < CFG.game.getCivsSize(); var18++) {
         if (CFG.game.getCiv(var18).getNumOfProvinces() > 0) {
            for (int j3 = 0; j3 < CFG.game.getCiv(var18).civGameData.civPlans.iWarPreparationsSize; j3++) {
               for (int f = 0; f < this.lFrontLines.get(var18 - 1).size(); f++) {
                  if (this.lFrontLines.get(var18 - 1).get(f).iWithCivID == CFG.game.getCiv(var18).civGameData.civPlans.warPreparations.get(j3).onCivID) {
                     for (int e = 0; e < this.lFrontLines.get(var18 - 1).get(f).lProvinces.size(); e++) {
                        CFG.game
                           .getProvince(this.lFrontLines.get(var18 - 1).get(f).lProvinces.get(e))
                           .addDangerLevel(
                              (int)(450.0F * (0.675F + 0.325F / CFG.game.getCiv(var18).civGameData.civPlans.warPreparations.get(j3).iNumOfTurnsLeft))
                           );
                     }
                  }
               }
            }

            for (int j3 = 0; j3 < CFG.game.getCiv(var18).civGameData.civPlans.iCasusBelliSize; j3++) {
               for (int fx = 0; fx < this.lFrontLines.get(var18 - 1).size(); fx++) {
                  if (this.lFrontLines.get(var18 - 1).get(fx).iWithCivID == CFG.game.getCiv(var18).civGameData.civPlans.casusBelli.get(j3).onCivID) {
                     for (int e = 0; e < this.lFrontLines.get(var18 - 1).get(fx).lProvinces.size(); e++) {
                        CFG.game
                           .getProvince(this.lFrontLines.get(var18 - 1).get(fx).lProvinces.get(e))
                           .addDangerLevel((int)(450.0F * (0.675F + 0.325F / CFG.game.getCiv(var18).civGameData.civPlans.casusBelli.get(j3).iNumOfTurnsLeft)));
                     }
                  }
               }
            }
         }
      }

      this.iNeutralProvincesWithSeaAccessSize = this.lNeutralProvincesWithSeaAccess.size();
      Gdx.app.log("AI", "--------- TURN: " + Game_Calendar.TURN_ID + " ---------");
      CFG.setRender_3(true);
   }

   public final void buildProvinceData(int i) {
      CFG.game.getProvince(i).setBordersWithEnemy(false);
      CFG.game.getProvince(i).setDangerLevel(0);
      CFG.game.getProvince(i).setPotential(245);
      CFG.game.getProvince(i).setNumOfNeighboringNeutralProvinces(0);
      CFG.game.getProvince(i).was = false;
      CFG.game.getProvince(i).buildRecruitableArmyPoints();
      if (CFG.game.getProvince(i).getRevolutionaryRisk() > 0.56F) {
         CFG.game.getCiv(CFG.game.getProvince(i).getCivID()).lProvincesWithHighRevRisk.add(i);
      }

      if (CFG.game.getProvince(i).getCivID() > 0) {
         Civilization var10000 = CFG.game.getCiv(CFG.game.getProvince(i).getCivID());
         var10000.iNumOf_Forts = var10000.iNumOf_Forts + CFG.game.getProvince(i).getLevelOfFort();
         var10000 = CFG.game.getCiv(CFG.game.getProvince(i).getCivID());
         var10000.iNumOf_Towers = var10000.iNumOf_Towers + CFG.game.getProvince(i).getLevelOfWatchTower();
         if (CFG.terrainTypesManager.getPopulationGrowth(CFG.game.getProvince(i).getTerrainTypeID()) >= 0.0F) {
            var10000 = CFG.game.getCiv(CFG.game.getProvince(i).getCivID());
            var10000.iNumOf_Farms = var10000.iNumOf_Farms + CFG.game.getProvince(i).getLevelOfFarm();
            CFG.game.getCiv(CFG.game.getProvince(i).getCivID()).iNumOf_Farms_ProvincesPossibleToBuild++;
         }

         var10000 = CFG.game.getCiv(CFG.game.getProvince(i).getCivID());
         var10000.iNumOf_Workshops = var10000.iNumOf_Workshops + CFG.game.getProvince(i).getLevelOfWorkshop();
         var10000 = CFG.game.getCiv(CFG.game.getProvince(i).getCivID());
         var10000.iNumOf_Libraries = var10000.iNumOf_Libraries + CFG.game.getProvince(i).getLevelOfLibrary();
         var10000 = CFG.game.getCiv(CFG.game.getProvince(i).getCivID());
         var10000.iNumOf_Armories = var10000.iNumOf_Armories + CFG.game.getProvince(i).getLevelOfArmoury();
         var10000 = CFG.game.getCiv(CFG.game.getProvince(i).getCivID());
         var10000.iNumOf_SuppliesCamp = var10000.iNumOf_SuppliesCamp + CFG.game.getProvince(i).getLevelOfSupply();
         if (CFG.game.getProvince(i).getLevelOfPort() > 0) {
            var10000 = CFG.game.getCiv(CFG.game.getProvince(i).getCivID());
            var10000.iNumOf_Ports = var10000.iNumOf_Ports + CFG.game.getProvince(i).getLevelOfPort();
            CFG.game.getCiv(CFG.game.getProvince(i).getCivID()).addSeaAccess_PortProvinces(i);
         }

         if (CFG.game.getProvince(i).getNeighboringSeaProvincesSize() > 0) {
            CFG.game.getCiv(CFG.game.getProvince(i).getCivID()).addSeaAccess_Provinces(i);
         }

         for (int k = 0; k < CFG.game.getProvince(i).getCivsSize(); k++) {
            if (CFG.game.getProvince(i).getArmy(k) > 0) {
               CFG.game.getCiv(CFG.game.getProvince(i).getCivID(k)).lArmiesPosition.add(i);
            }
         }

         for (int j = 0; j < CFG.game.getProvince(i).getNeighboringProvincesSize(); j++) {
            if (CFG.game.getProvince(CFG.game.getProvince(i).getNeighboringProvinces(j)).getCivID() > 0) {
               CFG.game
                  .getCiv(CFG.game.getProvince(i).getCivID())
                  .addBordersWithCivID(CFG.game.getProvince(CFG.game.getProvince(i).getNeighboringProvinces(j)).getCivID());
            } else if (CFG.game.getProvince(CFG.game.getProvince(i).getNeighboringProvinces(j)).getWasteland() >= 0) {
               CFG.game.getCiv(CFG.game.getProvince(i).getCivID()).lBordersWithWastelandProvincesID.add(CFG.game.getProvince(i).getNeighboringProvinces(j));
            } else if (CFG.game.getProvince(CFG.game.getProvince(i).getNeighboringProvinces(j)).getCivID() == 0) {
               CFG.game.getCiv(CFG.game.getProvince(i).getCivID()).lBordersWithNeutralProvincesID.add(CFG.game.getProvince(i).getNeighboringProvinces(j));
            }
         }
      } else if (CFG.game.getProvince(i).getSeaProvince()) {
         for (int kx = 1; kx < CFG.game.getProvince(i).getCivsSize(); kx++) {
            if (CFG.game.getProvince(i).getArmy(kx) > 0) {
               CFG.game.getCiv(CFG.game.getProvince(i).getCivID(kx)).lArmiesPosition.add(i);
            }
         }
      }

      if (CFG.game.getProvince(i).getWasAttacked() > 0) {
         CFG.game
            .getProvince(i)
            .addDangerLevel(
               (int)(
                  CFG.game.getProvince(i).getIsCapital()
                     ? 45.0F
                     : 10.0F
                        * (
                           (100.0F - (float)(35 * CFG.game.getProvince(i).getArmy(0)) / CFG.game.getCiv(CFG.game.getProvince(i).getCivID()).getNumOfUnits())
                              / 100.0F
                        )
               )
            );
         CFG.game.getProvince(i).setArmyWasRecruited(0);
         CFG.game.getProvince(i).setWasAttacked(CFG.game.getProvince(i).getWasAttacked() - 1);
      }

      CFG.game.getProvince(i).addPotential(CFG.game.getProvince(i).getNeighboringProvincesSize());
      CFG.game.getProvince(i).addPotential(CFG.game.getProvince(i).getNeighboringSeaProvincesSize());
      CFG.game.getProvince(i).addPotential((int)((float)(235 * CFG.game.getProvince(i).getPopulationData().getPopulation()) / ViewsManager.POPULATION_MAX));
      CFG.game.getProvince(i).addPotential((int)(185.0F * CFG.game.getProvince(i).getGrowthRate_Population_WithFarm()));
      CFG.game.getProvince(i).addPotential((int)((float)(175 * CFG.game.getProvince(i).getEconomy()) / ViewsManager.ECONOMY_MAX));
      CFG.game.getProvince(i).addPotential((int)(115.0F * CFG.game.getProvince(i).getDevelopmentLevel()));
      CFG.game.getProvince(i).addDangerLevel((int)CFG.game.getProvince(i).getRevolutionaryRisk());
      if (CFG.game.getProvince(i).getCivID() == 0) {
         CFG.game
            .getProvince(i)
            .addPotential(
               225
                  + (int)(
                     (375.0F + 275.0F * (0.5F + 0.1F * CFG.game.getProvince(i).getNeighboringProvincesSize()))
                        * CFG.game.getProvince(i).getGrowthRate_Population_WithFarm()
                  )
            );

         for (int jx = 0; jx < CFG.game.getProvince(i).getNeighboringSeaProvincesSize(); jx++) {
            if (CFG.game.getProvince(CFG.game.getProvince(i).getNeighboringSeaProvinces(jx)).getLevelOfPort() == -2) {
               this.addNeutralProvincesWithSeaAccess(i);
               break;
            }
         }
      } else {
         Civilization var23 = CFG.game.getCiv(CFG.game.getProvince(i).getCivID());
         var23.iAveragePopulation = var23.iAveragePopulation + CFG.game.getProvince(i).getPopulationData().getPopulation();
         if (CFG.game.getProvince(i).getLevelOfWatchTower() > 0) {
            CFG.game.getProvince(i).addPotential(4 * CFG.game.getProvince(i).getLevelOfWatchTower() * CFG.game.getProvince(i).getNeighboringProvincesSize());
         }

         CFG.game.getProvince(i).addPotential(6 * CFG.game.getProvince(i).getLevelOfPort() * CFG.game.getProvince(i).getNeighboringProvincesSize());
         CFG.game.getProvince(i).addPotential(5 * CFG.game.getProvince(i).getLevelOfFort());
         CFG.game.getProvince(i).addPotential(3 * CFG.game.getProvince(i).getLevelOfFarm());
         CFG.game.getProvince(i).addPotential(4 * CFG.game.getProvince(i).getLevelOfWorkshop());
         if (CFG.game.getProvince(i).getNeighboringSeaProvincesSize() > 0) {
            CFG.game.getCiv(CFG.game.getProvince(i).getCivID()).setSeaAccess(CFG.game.getCiv(CFG.game.getProvince(i).getCivID()).getSeaAccess() + 1);
         }

         int nNeighbooringOwnProvinces = 0;

         for (int j2 = 0; j2 < CFG.game.getProvince(i).getNeighboringProvincesSize(); j2++) {
            if (CFG.game.getProvince(CFG.game.getProvince(i).getNeighboringProvinces(j2)).getCivID() > 0) {
               if (CFG.game.getProvince(i).getCivID() != CFG.game.getProvince(CFG.game.getProvince(i).getNeighboringProvinces(j2)).getCivID()
                  && CFG.game.getCiv(CFG.game.getProvince(i).getCivID()).getPuppetOfCivID()
                     != CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvince(i).getNeighboringProvinces(j2)).getCivID()).getPuppetOfCivID()) {
                  if (CFG.game
                     .getCivsAtWar(CFG.game.getProvince(i).getCivID(), CFG.game.getProvince(CFG.game.getProvince(i).getNeighboringProvinces(j2)).getCivID())) {
                     CFG.game.getProvince(i).setBordersWithEnemy(true);
                     CFG.game
                        .getProvince(i)
                        .addDangerLevel(
                           (int)(
                              (CFG.game.getProvince(i).getIsCapital() ? 64 : 24)
                                 * (CFG.game.getProvince(i).getWasAttacked() > 0 ? 1.775F : 1.0F)
                                 * (CFG.game.getProvince(CFG.game.getProvince(i).getNeighboringProvinces(j2)).getWasConquered() + 1)
                           )
                        );
                  }

                  if (!CFG.game
                        .getCivsAreAllied(
                           CFG.game.getProvince(i).getCivID(), CFG.game.getProvince(CFG.game.getProvince(i).getNeighboringProvinces(j2)).getCivID()
                        )
                     && CFG.game.getCiv(CFG.game.getProvince(i).getCivID()).getPuppetOfCivID()
                        != CFG.game.getCiv(CFG.game.getProvince(CFG.game.getProvince(i).getNeighboringProvinces(j2)).getCivID()).getPuppetOfCivID()
                     && CFG.game
                           .getDefensivePact(
                              CFG.game.getProvince(i).getCivID(), CFG.game.getProvince(CFG.game.getProvince(i).getNeighboringProvinces(j2)).getCivID()
                           )
                        == 0
                     && CFG.game
                           .getGuarantee(
                              CFG.game.getProvince(i).getCivID(), CFG.game.getProvince(CFG.game.getProvince(i).getNeighboringProvinces(j2)).getCivID()
                           )
                        == 0
                     && CFG.game
                           .getCivNonAggressionPact(
                              CFG.game.getProvince(i).getCivID(), CFG.game.getProvince(CFG.game.getProvince(i).getNeighboringProvinces(j2)).getCivID()
                           )
                        == 0
                     && CFG.game
                           .getCivTruce(
                              CFG.game.getProvince(i).getCivID(), CFG.game.getProvince(CFG.game.getProvince(i).getNeighboringProvinces(j2)).getCivID()
                           )
                        < 4) {
                     CFG.game.getCiv(CFG.game.getProvince(i).getCivID()).setCanExpandOnContinent(true);
                     CFG.game.getProvince(i).addDangerLevel(CFG.game.getProvince(i).getIsCapital() ? 14 : 6);
                     CFG.game
                        .getProvince(i)
                        .addDangerLevel(
                           (int)(
                              (CFG.game.getProvince(i).getIsCapital() ? 48.75F : 33.45F)
                                 * (
                                    CFG.game
                                          .getCivsAtWar(
                                             CFG.game.getProvince(i).getCivID(),
                                             CFG.game.getProvince(CFG.game.getProvince(i).getNeighboringProvinces(j2)).getCivID()
                                          )
                                       ? 4.875F * (CFG.game.getProvince(CFG.game.getProvince(i).getNeighboringProvinces(j2)).getWasConquered() + 1)
                                       : Math.max(
                                          0.75F,
                                          1.55F
                                             - CFG.game
                                                   .getCivRelation_OfCivB(
                                                      CFG.game.getProvince(i).getCivID(),
                                                      CFG.game.getProvince(CFG.game.getProvince(i).getNeighboringProvinces(j2)).getCivID()
                                                   )
                                                / 25.0F
                                       )
                                 )
                                 * (
                                    0.625F
                                       + Math.min(
                                          1.42F,
                                          (float)CFG.game
                                                .getCiv(CFG.game.getProvince(CFG.game.getProvince(i).getNeighboringProvinces(j2)).getCivID())
                                                .getNumOfProvinces()
                                             / CFG.game.getCiv(CFG.game.getProvince(i).getCivID()).getNumOfProvinces()
                                             / CFG.game.getProvince(i).getNeighboringProvincesSize()
                                       )
                                 )
                           )
                        );
                  }

                  CFG.game
                     .getProvince(i)
                     .addPotential(
                        -(
                           (int)(
                              CFG.game.getCiv(CFG.game.getProvince(i).getCivID()).civGameData.civPersonality.POTENTIAL_POPULATION
                                 * 0.85F
                                 * CFG.game.getProvince(CFG.game.getProvince(i).getNeighboringProvinces(j2)).getPopulationData().getPopulation()
                                 / ViewsManager.POPULATION_MAX
                           )
                        )
                     );
                  CFG.game
                     .getProvince(i)
                     .addPotential(
                        -(
                           (int)(
                              CFG.game.getCiv(CFG.game.getProvince(i).getCivID()).civGameData.civPersonality.POTENTIAL_ECONOMY
                                 * 0.85F
                                 * CFG.game.getProvince(CFG.game.getProvince(i).getNeighboringProvinces(j2)).getEconomy()
                                 / ViewsManager.ECONOMY_MAX
                           )
                        )
                     );
                  CFG.game.getProvince(i).addPotential(-24);
               } else {
                  CFG.game.getProvince(i).addPotential(24);
                  nNeighbooringOwnProvinces++;
               }
            } else {
               CFG.game.getProvince(i).setNumOfNeighboringNeutralProvinces(CFG.game.getProvince(i).getNumOfNeighboringNeutralProvinces() + 1);
               CFG.game
                  .getProvince(i)
                  .addPotential(5 + (int)(4.0F + 46.0F * CFG.game.getProvince(CFG.game.getProvince(i).getNeighboringProvinces(j2)).getGrowthRate_Population()));
            }

            CFG.game
               .getProvince(i)
               .addPotential(
                  (int)(
                     CFG.game.getCiv(CFG.game.getProvince(i).getCivID()).civGameData.civPersonality.POTENTIAL_POPULATION
                        * CFG.game.getProvince(CFG.game.getProvince(i).getNeighboringProvinces(j2)).getPopulationData().getPopulation()
                        / ViewsManager.POPULATION_MAX
                  )
               );
            CFG.game
               .getProvince(i)
               .addPotential(
                  (int)(
                     CFG.game.getCiv(CFG.game.getProvince(i).getCivID()).civGameData.civPersonality.POTENTIAL_ECONOMY
                        * CFG.game.getProvince(CFG.game.getProvince(i).getNeighboringProvinces(j2)).getEconomy()
                        / ViewsManager.ECONOMY_MAX
                  )
               );
         }

         if (nNeighbooringOwnProvinces > 0) {
            CFG.game
               .getProvince(i)
               .setDangerLevel(
                  (int)(
                     CFG.game.getProvince(i).getDangerLevel()
                        + CFG.game.getCiv(CFG.game.getProvince(i).getCivID()).civGameData.civPersonality.DANGER_EXTRA_PER_OWN_PROVINCE
                           * nNeighbooringOwnProvinces
                           * CFG.game.getProvince(i).getDangerLevel()
                  )
               );
         }

         if (CFG.game.getProvince(i).getBordersWithEnemy()) {
            CFG.game.getProvince(i).addDangerLevel(450);
         }

         if (CFG.game.getProvince(i).getIsCapital()) {
            CFG.game.getProvince(i).addPotential(25);
            if (CFG.game.getProvince(i).getNeighboringSeaProvincesSize() > 0) {
               CFG.game.getProvince(i).addDangerLevel(125 + 25 * CFG.game.getProvince(i).getNeighboringSeaProvincesSize());
            }
         }
      }

      for (int jxx = 0; jxx < CFG.game.getProvince(i).getNeighboringSeaProvincesSize(); jxx++) {
         for (int k2 = 1; k2 < CFG.game.getProvince(CFG.game.getProvince(i).getNeighboringSeaProvinces(jxx)).getCivsSize(); k2++) {
            if (CFG.game
               .getCivsAtWar(CFG.game.getProvince(i).getCivID(), CFG.game.getProvince(CFG.game.getProvince(i).getNeighboringSeaProvinces(jxx)).getCivID(k2))) {
               CFG.game
                  .getProvince(i)
                  .addDangerLevel(
                     (int)(
                        (CFG.game.getProvince(i).getIsCapital() ? 28.75F : 14.87F)
                           * Math.min(
                              1.0F
                                 * CFG.game.getProvince(CFG.game.getProvince(i).getNeighboringSeaProvinces(jxx)).getArmy(k2)
                                 / Math.max((float)CFG.game.getProvince(i).getArmy(0), 1.0F),
                              2.0F
                           )
                     )
                  );
            } else if (CFG.game
                  .getCivRelation_OfCivB(
                     CFG.game.getProvince(i).getCivID(), CFG.game.getProvince(CFG.game.getProvince(i).getNeighboringSeaProvinces(jxx)).getCivID(k2)
                  )
               < 0.0F) {
               CFG.game
                  .getProvince(i)
                  .addDangerLevel(
                     (int)(
                        (CFG.game.getProvince(i).getIsCapital() ? 8.75F : 4.87F)
                           * Math.min(
                              1.0F
                                 * CFG.game.getProvince(CFG.game.getProvince(i).getNeighboringSeaProvinces(jxx)).getArmy(k2)
                                 / Math.max((float)CFG.game.getProvince(i).getArmy(0), 1.0F),
                              2.0F
                           )
                           * (
                              -CFG.game
                                    .getCivRelation_OfCivB(
                                       CFG.game.getProvince(i).getCivID(),
                                       CFG.game.getProvince(CFG.game.getProvince(i).getNeighboringSeaProvinces(jxx)).getCivID(k2)
                                    )
                                 / 100.0F
                           )
                     )
                  );
            }
         }
      }

      try {
         if (CFG.game.getProvince(i).getArmy(0) > 0) {
            CFG.game
               .getProvince(i)
               .setDangerLevel_WithArmy(
                  (int)Math.ceil(
                     CFG.game.getProvince(i).getDangerLevel()
                        * (
                           1.0F
                              - CFG.game.getCiv(CFG.game.getProvince(i).getCivID()).civGameData.civPersonality.DANGER_PERC_OF_UNITS
                                 * CFG.game.getProvince(i).getArmy(0)
                                 / CFG.game.getCiv(CFG.game.getProvince(i).getCivID()).getNumOfUnits()
                        )
                  )
               );
         } else {
            CFG.game.getProvince(i).setDangerLevel_WithArmy(CFG.game.getProvince(i).getDangerLevel());
         }
      } catch (IllegalArgumentException var10) {
         CFG.game.getProvince(i).setDangerLevel_WithArmy(CFG.game.getProvince(i).getDangerLevel());
         if (CFG.LOGS) {
            CFG.exceptionStack(var10);
         }
      }

      if (CFG.game.getProvince(i).getLevelOfFort() > 0) {
         CFG.game.getProvince(i).setPotential((int)Math.ceil(CFG.game.getProvince(i).getPotential() * 0.9566F));
      }

      if (CFG.game.getProvince(i).getCivID() > 0) {
         try {
            Civilization_Region var24 = CFG.game.getCiv(CFG.game.getProvince(i).getCivID()).getCivRegion(CFG.game.getProvince(i).getCivRegionID());
            var24.iAveragePotential = var24.iAveragePotential + CFG.game.getProvince(i).getPotential();
         } catch (IndexOutOfBoundsException var8) {
            if (CFG.LOGS) {
               CFG.exceptionStack(var8);
            }
         } catch (NullPointerException var9) {
            if (CFG.LOGS) {
               CFG.exceptionStack(var9);
            }
         }
      }

      try {
         if (CFG.game.getProvince(i).getCivID() > 0
            && CFG.game.getCiv(CFG.game.getProvince(i).getCivID()).getCivRegion(CFG.game.getProvince(i).getCivRegionID()).isKeyRegion) {
            CFG.game
               .getProvince(i)
               .setDangerLevel(
                  (int)(
                     CFG.game.getProvince(i).getDangerLevel()
                        * CFG.game.getCiv(CFG.game.getProvince(i).getCivID()).civGameData.civPersonality.DANGER_EXTRA_KEY_REGION
                  )
               );
         }
      } catch (IndexOutOfBoundsException var6) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var6);
         }
      } catch (NullPointerException var7) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var7);
         }
      }

      if (CFG.game.getProvince(i).getNeighbooringProvinceOfCivWasLost() > 0) {
         CFG.game
            .getProvince(i)
            .addDangerLevel((int)(CFG.game.getProvince(i).getDangerLevel() * 0.15F * CFG.game.getProvince(i).getNeighbooringProvinceOfCivWasLost()));
      }

      if (CFG.game.getProvince(i).getArmyWasRecruited() > 0) {
         CFG.game.getProvince(i).setArmyWasRecruited(CFG.game.getProvince(i).getArmyWasRecruited() - 1);
      }

      if (CFG.game.getProvince(i).getBordersWithEnemy()) {
         CFG.game.getCiv(CFG.game.getProvince(i).getCivID()).setBordersWithEnemy(CFG.game.getCiv(CFG.game.getProvince(i).getCivID()).getBordersWithEnemy() + 1);
      }

      if (CFG.game.getProvince(i).getNumOfNeighboringNeutralProvinces() > 0) {
         CFG.game
            .getCiv(CFG.game.getProvince(i).getCivID())
            .setNumOfNeighboringNeutralProvinces(
               CFG.game.getCiv(CFG.game.getProvince(i).getCivID()).getNumOfNeighboringNeutralProvinces()
                  + CFG.game.getProvince(i).getNumOfNeighboringNeutralProvinces()
            );
      }

      CFG.game.getProvince(i).setWasConquered((byte)(CFG.game.getProvince(i).getWasConquered() - 1));
      CFG.game.getProvince(i).setNeighbooringProvinceOfCivWasLost((byte)(CFG.game.getProvince(i).getNeighbooringProvinceOfCivWasLost() - 1));
   }

   public final List<AI_NeighProvinces_Army> getAllNeighboringProvincesInRange_WithArmyToRegroup(
      int nProvinceID,
      int nCivID,
      int iRange,
      boolean onlyTrueOwner,
      boolean dontBreakIfNotFoundRecentlyProvince,
      List<AI_NeighProvinces_Army> out,
      List<Integer> was,
      int nRequiredArmy
   ) {
      ArrayList<Integer> recentlyAdded = new ArrayList<>();
      recentlyAdded.add(nProvinceID);
      was.add(nProvinceID);
      CFG.game.getProvince(nProvinceID).was = true;
      ArrayList currProvinces = new ArrayList();
      int nIteration_Distance = 0;
      int nArmyCollected = 0;

      while (iRange-- > 0 && (dontBreakIfNotFoundRecentlyProvince || recentlyAdded.size() > 0)) {
         currProvinces.clear();
         nIteration_Distance++;

         for (int a = recentlyAdded.size() - 1; a >= 0; a--) {
            boolean wasntAdded = true;

            for (int j = currProvinces.size() - 1; j >= 0; j--) {
               if (currProvinces.get(j) == recentlyAdded.get(a)) {
                  wasntAdded = false;
                  break;
               }
            }

            if (wasntAdded) {
               currProvinces.add(recentlyAdded.get(a));
            }
         }

         recentlyAdded.clear();

         for (int var18 = currProvinces.size() - 1; var18 >= 0; var18--) {
            for (int i = 0; i < CFG.game.getProvince((Integer)currProvinces.get(var18)).getNeighboringProvincesSize(); i++) {
               if (!CFG.game.getProvince(CFG.game.getProvince((Integer)currProvinces.get(var18)).getNeighboringProvinces(i)).was) {
                  was.add(CFG.game.getProvince((Integer)currProvinces.get(var18)).getNeighboringProvinces(i));
                  CFG.game.getProvince(CFG.game.getProvince((Integer)currProvinces.get(var18)).getNeighboringProvinces(i)).was = true;
                  if (CFG.game.getProvince(CFG.game.getProvince((Integer)currProvinces.get(var18)).getNeighboringProvinces(i)).getCivID() == nCivID
                     && (
                        !onlyTrueOwner
                           || CFG.game.getProvince(CFG.game.getProvince((Integer)currProvinces.get(var18)).getNeighboringProvinces(i)).getCivID()
                              == CFG.game
                                 .getProvince(CFG.game.getProvince((Integer)currProvinces.get(var18)).getNeighboringProvinces(i))
                                 .getTrueOwnerOfProvince()
                     )) {
                     if (CFG.game.getProvince(CFG.game.getProvince((Integer)currProvinces.get(var18)).getNeighboringProvinces(i)).getArmyCivID(nCivID)
                           - CFG.game
                              .getCiv(nCivID)
                              .civGameData
                              .civPlans
                              .haveMission_Army(CFG.game.getProvince((Integer)currProvinces.get(var18)).getNeighboringProvinces(i))
                        > 0) {
                        int tArmy = CFG.game
                              .getProvince(CFG.game.getProvince((Integer)currProvinces.get(var18)).getNeighboringProvinces(i))
                              .getArmyCivID(nCivID)
                           - CFG.game
                              .getCiv(nCivID)
                              .civGameData
                              .civPlans
                              .haveMission_Army(CFG.game.getProvince((Integer)currProvinces.get(var18)).getNeighboringProvinces(i));
                        nArmyCollected += tArmy;
                        out.add(
                           new AI_NeighProvinces_Army(
                              CFG.game.getProvince((Integer)currProvinces.get(var18)).getNeighboringProvinces(i), nIteration_Distance, tArmy
                           )
                        );
                     }

                     recentlyAdded.add(CFG.game.getProvince((Integer)currProvinces.get(var18)).getNeighboringProvinces(i));
                  }
               }
            }
         }

         if (nArmyCollected < nRequiredArmy) {
         }
      }

      for (int jx = was.size() - 1; jx >= 0; jx--) {
         CFG.game.getProvince(was.get(jx)).was = false;
      }

      recentlyAdded.clear();
      ArrayList<Integer> var17 = null;
      was.clear();
      List<Integer> var16 = null;
      return out;
   }

   public final List<AI_NeighProvinces> getAllNeighboringProvincesInRange_RecruitAtWAr(
      int nProvinceID,
      int nCivID,
      int iRange,
      boolean onlyTrueOwner,
      boolean dontBreakIfNotFoundRecentlyProvince,
      List<AI_NeighProvinces> out,
      List<Integer> was
   ) {
      ArrayList<Integer> recentlyAdded = new ArrayList<>();
      recentlyAdded.add(nProvinceID);
      was.add(nProvinceID);
      CFG.game.getProvince(nProvinceID).was = true;
      ArrayList currProvinces = new ArrayList();
      int nIteration_Distance = 0;
      int iFirstFoundRange = -1;

      while ((nIteration_Distance < iRange || out.size() == 0) && recentlyAdded.size() > 0) {
         currProvinces.clear();
         nIteration_Distance++;

         for (int a = recentlyAdded.size() - 1; a >= 0; a--) {
            boolean wasntAdded = true;

            for (int j = currProvinces.size() - 1; j >= 0; j--) {
               if (currProvinces.get(j) == recentlyAdded.get(a)) {
                  wasntAdded = false;
                  break;
               }
            }

            if (wasntAdded) {
               currProvinces.add(recentlyAdded.get(a));
            }
         }

         recentlyAdded.clear();

         for (int var17 = currProvinces.size() - 1; var17 >= 0; var17--) {
            for (int i = 0; i < CFG.game.getProvince((Integer)currProvinces.get(var17)).getNeighboringProvincesSize(); i++) {
               if (!CFG.game.getProvince(CFG.game.getProvince((Integer)currProvinces.get(var17)).getNeighboringProvinces(i)).was) {
                  was.add(CFG.game.getProvince((Integer)currProvinces.get(var17)).getNeighboringProvinces(i));
                  CFG.game.getProvince(CFG.game.getProvince((Integer)currProvinces.get(var17)).getNeighboringProvinces(i)).was = true;
                  if (CFG.game
                        .isAlly(nCivID, CFG.game.getProvince(CFG.game.getProvince((Integer)currProvinces.get(var17)).getNeighboringProvinces(i)).getCivID())
                     || CFG.game
                           .getMilitaryAccess(
                              nCivID, CFG.game.getProvince(CFG.game.getProvince((Integer)currProvinces.get(var17)).getNeighboringProvinces(i)).getCivID()
                           )
                        > 0) {
                     if (!CFG.game.getProvince(CFG.game.getProvince((Integer)currProvinces.get(var17)).getNeighboringProvinces(i)).isOccupied()
                        && nCivID == CFG.game.getProvince(CFG.game.getProvince((Integer)currProvinces.get(var17)).getNeighboringProvinces(i)).getCivID()
                        && CFG.game
                              .getCiv(nCivID)
                              .isRecruitingArmyInProvinceID(CFG.game.getProvince((Integer)currProvinces.get(var17)).getNeighboringProvinces(i))
                           < 0) {
                        out.add(new AI_NeighProvinces(CFG.game.getProvince((Integer)currProvinces.get(var17)).getNeighboringProvinces(i), nIteration_Distance));
                        if (iFirstFoundRange < 0) {
                           iFirstFoundRange = nIteration_Distance;
                           iRange += 4;
                        }
                     }

                     recentlyAdded.add(CFG.game.getProvince((Integer)currProvinces.get(var17)).getNeighboringProvinces(i));
                  }
               }
            }
         }

         if (iFirstFoundRange > 0 && iFirstFoundRange + 8 >= nIteration_Distance) {
         }
      }

      for (int jx = was.size() - 1; jx >= 0; jx--) {
         CFG.game.getProvince(was.get(jx)).was = false;
      }

      recentlyAdded.clear();
      ArrayList<Integer> var16 = null;
      was.clear();
      List<Integer> var15 = null;
      return out;
   }

   public final List<AI_NeighProvinces> getAllNeighboringProvincesInRange_Recruit(
      int nProvinceID,
      int nCivID,
      int iRange,
      boolean onlyTrueOwner,
      boolean dontBreakIfNotFoundRecentlyProvince,
      List<AI_NeighProvinces> out,
      List<Integer> was
   ) {
      ArrayList<Integer> recentlyAdded = new ArrayList<>();
      recentlyAdded.add(nProvinceID);
      was.add(nProvinceID);
      CFG.game.getProvince(nProvinceID).was = true;
      ArrayList currProvinces = new ArrayList();
      int nIteration_Distance = 0;

      while (iRange-- > 0 && (dontBreakIfNotFoundRecentlyProvince || recentlyAdded.size() > 0)) {
         currProvinces.clear();
         nIteration_Distance++;

         for (int a = recentlyAdded.size() - 1; a >= 0; a--) {
            boolean wasntAdded = true;

            for (int j = currProvinces.size() - 1; j >= 0; j--) {
               if (currProvinces.get(j) == recentlyAdded.get(a)) {
                  wasntAdded = false;
                  break;
               }
            }

            if (wasntAdded) {
               currProvinces.add(recentlyAdded.get(a));
            }
         }

         recentlyAdded.clear();

         for (int var16 = currProvinces.size() - 1; var16 >= 0; var16--) {
            for (int i = 0; i < CFG.game.getProvince((Integer)currProvinces.get(var16)).getNeighboringProvincesSize(); i++) {
               if (!CFG.game.getProvince(CFG.game.getProvince((Integer)currProvinces.get(var16)).getNeighboringProvinces(i)).was) {
                  was.add(CFG.game.getProvince((Integer)currProvinces.get(var16)).getNeighboringProvinces(i));
                  CFG.game.getProvince(CFG.game.getProvince((Integer)currProvinces.get(var16)).getNeighboringProvinces(i)).was = true;
                  if (CFG.game.getProvince(CFG.game.getProvince((Integer)currProvinces.get(var16)).getNeighboringProvinces(i)).getCivID() == nCivID) {
                     if (!CFG.game.getProvince(CFG.game.getProvince((Integer)currProvinces.get(var16)).getNeighboringProvinces(i)).isOccupied()
                        && CFG.game
                              .getCiv(nCivID)
                              .isRecruitingArmyInProvinceID(CFG.game.getProvince((Integer)currProvinces.get(var16)).getNeighboringProvinces(i))
                           < 0) {
                        out.add(new AI_NeighProvinces(CFG.game.getProvince((Integer)currProvinces.get(var16)).getNeighboringProvinces(i), nIteration_Distance));
                     }

                     recentlyAdded.add(CFG.game.getProvince((Integer)currProvinces.get(var16)).getNeighboringProvinces(i));
                  }
               }
            }
         }
      }

      for (int jx = was.size() - 1; jx >= 0; jx--) {
         CFG.game.getProvince(was.get(jx)).was = false;
      }

      recentlyAdded.clear();
      ArrayList<Integer> var15 = null;
      was.clear();
      List<Integer> var14 = null;
      return out;
   }

   public final List<AI_NeighProvinces> getAllNeighboringProvincesInRange_Clear(
      int nProvinceID,
      int nCivID,
      int iRange,
      boolean onlyTrueOwner,
      boolean dontBreakIfNotFoundRecentlyProvince,
      List<AI_NeighProvinces> out,
      List<Integer> was
   ) {
      ArrayList<Integer> recentlyAdded = new ArrayList<>();
      recentlyAdded.add(nProvinceID);
      was.add(nProvinceID);
      CFG.game.getProvince(nProvinceID).was = true;
      ArrayList currProvinces = new ArrayList();
      int nIteration_Distance = 0;

      while (iRange-- > 0 && (dontBreakIfNotFoundRecentlyProvince || recentlyAdded.size() > 0)) {
         currProvinces.clear();
         nIteration_Distance++;

         for (int a = recentlyAdded.size() - 1; a >= 0; a--) {
            boolean wasntAdded = true;

            for (int j = currProvinces.size() - 1; j >= 0; j--) {
               if (currProvinces.get(j) == recentlyAdded.get(a)) {
                  wasntAdded = false;
                  break;
               }
            }

            if (wasntAdded) {
               currProvinces.add(recentlyAdded.get(a));
            }
         }

         recentlyAdded.clear();

         for (int var16 = currProvinces.size() - 1; var16 >= 0; var16--) {
            for (int i = 0; i < CFG.game.getProvince((Integer)currProvinces.get(var16)).getNeighboringProvincesSize(); i++) {
               if (!CFG.game.getProvince(CFG.game.getProvince((Integer)currProvinces.get(var16)).getNeighboringProvinces(i)).was) {
                  was.add(CFG.game.getProvince((Integer)currProvinces.get(var16)).getNeighboringProvinces(i));
                  CFG.game.getProvince(CFG.game.getProvince((Integer)currProvinces.get(var16)).getNeighboringProvinces(i)).was = true;
                  if (CFG.game.getProvince(CFG.game.getProvince((Integer)currProvinces.get(var16)).getNeighboringProvinces(i)).getCivID() == nCivID
                     && (
                        !onlyTrueOwner
                           || CFG.game.getProvince(CFG.game.getProvince((Integer)currProvinces.get(var16)).getNeighboringProvinces(i)).getCivID()
                              == CFG.game
                                 .getProvince(CFG.game.getProvince((Integer)currProvinces.get(var16)).getNeighboringProvinces(i))
                                 .getTrueOwnerOfProvince()
                     )) {
                     out.add(new AI_NeighProvinces(CFG.game.getProvince((Integer)currProvinces.get(var16)).getNeighboringProvinces(i), nIteration_Distance));
                     recentlyAdded.add(CFG.game.getProvince((Integer)currProvinces.get(var16)).getNeighboringProvinces(i));
                  }
               }
            }
         }
      }

      for (int jx = was.size() - 1; jx >= 0; jx--) {
         CFG.game.getProvince(was.get(jx)).was = false;
      }

      recentlyAdded.clear();
      ArrayList<Integer> var15 = null;
      was.clear();
      List<Integer> var14 = null;
      return out;
   }

   public final List<AI_NeighProvinces> getAllNeighboringProvincesInRange_OnlyOwn_Clear(
      int nProvinceID,
      int nCivID,
      int iRange,
      boolean onlyTrueOwner,
      boolean dontBreakIfNotFoundRecentlyProvince,
      List<AI_NeighProvinces> out,
      List<Integer> was
   ) {
      ArrayList<Integer> recentlyAdded = new ArrayList<>();
      recentlyAdded.add(nProvinceID);
      was.add(nProvinceID);
      CFG.game.getProvince(nProvinceID).was = true;
      ArrayList currProvinces = new ArrayList();
      int nIteration_Distance = 0;
      int iFirstFoundRange = -1;

      while (iRange-- > 0) {
         currProvinces.clear();
         nIteration_Distance++;

         for (int a = recentlyAdded.size() - 1; a >= 0; a--) {
            boolean wasntAdded = true;

            for (int j = currProvinces.size() - 1; j >= 0; j--) {
               if (currProvinces.get(j) == recentlyAdded.get(a)) {
                  wasntAdded = false;
                  break;
               }
            }

            if (wasntAdded) {
               currProvinces.add(recentlyAdded.get(a));
            }
         }

         recentlyAdded.clear();

         for (int var17 = currProvinces.size() - 1; var17 >= 0; var17--) {
            for (int i = 0; i < CFG.game.getProvince((Integer)currProvinces.get(var17)).getNeighboringProvincesSize(); i++) {
               if (!CFG.game.getProvince(CFG.game.getProvince((Integer)currProvinces.get(var17)).getNeighboringProvinces(i)).was) {
                  was.add(CFG.game.getProvince((Integer)currProvinces.get(var17)).getNeighboringProvinces(i));
                  CFG.game.getProvince(CFG.game.getProvince((Integer)currProvinces.get(var17)).getNeighboringProvinces(i)).was = true;
                  if (CFG.game.getProvince(CFG.game.getProvince((Integer)currProvinces.get(var17)).getNeighboringProvinces(i)).getCivID() == nCivID) {
                     out.add(new AI_NeighProvinces(CFG.game.getProvince((Integer)currProvinces.get(var17)).getNeighboringProvinces(i), nIteration_Distance));
                     iFirstFoundRange = nIteration_Distance;
                  }

                  recentlyAdded.add(CFG.game.getProvince((Integer)currProvinces.get(var17)).getNeighboringProvinces(i));
               }
            }
         }

         if (iFirstFoundRange > 0 && iFirstFoundRange + 4 >= nIteration_Distance) {
         }
      }

      for (int jx = was.size() - 1; jx >= 0; jx--) {
         CFG.game.getProvince(was.get(jx)).was = false;
      }

      recentlyAdded.clear();
      ArrayList<Integer> var16 = null;
      was.clear();
      List<Integer> var15 = null;
      return out;
   }

   public final int getLoadingTurnActionsOfCivID() {
      return this.iLoadingTurnActionsOfCivID;
   }

   public final void setLoadingTurnActionsOfCivID(int iLoadingTurnActionsOfCivID) {
      this.iLoadingTurnActionsOfCivID = iLoadingTurnActionsOfCivID;
   }

   public final void updateExpand() {
      this.expandNeutral = !Game_Calendar.ENABLE_COLONIZATION_NEUTRAL_PROVINCES ? new AI.Expand() {
         @Override
         public boolean expandToNeutralProvinces(int nCivID) {
            return AI.this.expandToNeutralProvinces_Out(nCivID, true);
         }
      } : new AI.Expand() {
         @Override
         public boolean expandToNeutralProvinces(int nCivID) {
            return false;
         }
      };
   }

   public final void expandToNeutralProvinces_Run(int nCivID) {
      for (int k = CFG.game.getCiv(nCivID).civGameData.civPlans.lArmiesMissions.size() - 1; k >= 0; k--) {
         if (CFG.game.getCiv(nCivID).civGameData.civPlans.lArmiesMissions.get(k).MISSION_TYPE == CivArmyMission_Type.EXPAND_NETURAL_PROVINCE
            && CFG.game.getCiv(nCivID).civGameData.civPlans.lArmiesMissions.get(k).action(nCivID)) {
            CFG.game.getCiv(nCivID).civGameData.civPlans.lArmiesMissions.get(k).onRemove();
            CFG.game.getCiv(nCivID).civGameData.civPlans.lArmiesMissions.remove(k);
         }
      }
   }

   public final boolean expandToNeutralProvinces_Out(int nCivID, boolean maybeGoToTheSea) {
      try {
         if (CFG.game.getCiv(nCivID).getBordersWithEnemy() == 0) {
            Gdx.app.log("AoC", "expandToNeutralProvinces_Out -> " + CFG.game.getCiv(nCivID).getCivName());
            this.expandToNeutralProvinces_Run(nCivID);
            Gdx.app.log("AoC", "expandToNeutralProvinces_Out -> movepoints" + CFG.game.getCiv(nCivID).getMovePoints() / 10.0F);
            if (CFG.game.getCiv(nCivID).getMovePoints() < CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivID).getIdeologyID()).COST_OF_MOVE) {
               return false;
            }

            if (CFG.game.getCiv(nCivID).lBordersWithNeutralProvincesID.size() > 0
               && (
                  this.iNeutralProvincesWithSeaAccessSize <= 0
                     || !maybeGoToTheSea
                     || CFG.oR.nextInt(100) >= 5
                     || CFG.game.getCiv(nCivID).getMoney() <= BuildingsManager.getPort_BuildCost(1, CFG.game.getCiv(nCivID).getProvinceID(0))
               )) {
               int recruitableArmyMax = (int)(CFG.game.getCiv(nCivID).getMoney() / 5L);
               ArrayList<AI.NeutralProvinces> possibleProvinces = new ArrayList<>();

               for (int i = CFG.game.getCiv(nCivID).lBordersWithNeutralProvincesID.size() - 1; i >= 0; i--) {
                  if (CFG.game.getProvince(CFG.game.getCiv(nCivID).lBordersWithNeutralProvincesID.get(i)).getArmy(0) + 2
                     < recruitableArmyMax + CFG.game.getCiv(nCivID).getNumOfUnits()) {
                     possibleProvinces.add(new AI.NeutralProvinces(CFG.game.getCiv(nCivID).lBordersWithNeutralProvincesID.get(i), nCivID));
                  }
               }

               Gdx.app.log("AoC", "EXPAND -> 000 possibleProvinces.size: " + possibleProvinces.size());
               if (possibleProvinces.size() > 0) {
                  ArrayList sorted = new ArrayList();
                  ArrayList<Integer> tempIDs = new ArrayList<>();
                  int iSize = possibleProvinces.size();

                  for (int i2 = 0; i2 < iSize; i2++) {
                     tempIDs.add(i2);
                  }

                  while (tempIDs.size() > 0) {
                     int tBest = 0;

                     for (int i3 = tempIDs.size() - 1; i3 > 0; i3--) {
                        if (possibleProvinces.get(tempIDs.get(tBest)).iScore < possibleProvinces.get(tempIDs.get(i3)).iScore) {
                           tBest = i3;
                        }
                     }

                     sorted.add(tempIDs.get(tBest));
                     tempIDs.remove(tBest);
                  }

                  iSize = sorted.size();

                  for (int var33 = 0; var33 < iSize; var33++) {
                     ArrayList<Integer> possibleFrom = new ArrayList<>();

                     for (int k = 0; k < CFG.game.getProvince(possibleProvinces.get((Integer)sorted.get(var33)).iProvinceID).getNeighboringProvincesSize(); k++) {
                        if (CFG.game
                              .getProvince(CFG.game.getProvince(possibleProvinces.get((Integer)sorted.get(var33)).iProvinceID).getNeighboringProvinces(k))
                              .getCivID()
                           == nCivID) {
                           possibleFrom.add(CFG.game.getProvince(possibleProvinces.get((Integer)sorted.get(var33)).iProvinceID).getNeighboringProvinces(k));
                        }
                     }

                     ArrayList canMoveImmediately = new ArrayList();

                     for (int kx = possibleFrom.size() - 1; kx >= 0; kx--) {
                        if (CFG.game.getProvince(possibleFrom.get(kx)).getArmyCivID(nCivID)
                              - CFG.game.getCiv(nCivID).civGameData.civPlans.haveMission_Army(possibleFrom.get(kx))
                           > CFG.game.getProvince(possibleProvinces.get((Integer)sorted.get(var33)).iProvinceID).getArmy(0)) {
                           canMoveImmediately.add(possibleFrom.get(kx));
                        }
                     }

                     Gdx.app.log("AoC", "EXPAND -> 000 canMoveImmediately.size: " + canMoveImmediately.size());
                     if (canMoveImmediately.size() <= 0) {
                        Gdx.app.log("AoC", "EXPAND -> 000 ADDMISION: TOPROVINCEID: " + possibleProvinces.get((Integer)sorted.get(var33)).iProvinceID);
                        if (!CFG.game
                           .getCiv(nCivID)
                           .civGameData
                           .civPlans
                           .addNewArmyMission(
                              possibleProvinces.get((Integer)sorted.get(var33)).iProvinceID,
                              new CivArmyMission_ExpandNeutralProvince(nCivID, possibleProvinces.get((Integer)sorted.get(var33)).iProvinceID)
                           )) {
                        }
                     } else {
                        int randID = CFG.oR.nextInt(canMoveImmediately.size());
                        int numOfNeutral = 0;

                        for (int kxx = 0; kxx < CFG.game.getProvince((Integer)canMoveImmediately.get(randID)).getNeighboringProvincesSize(); kxx++) {
                           if (CFG.game.getProvince(CFG.game.getProvince((Integer)canMoveImmediately.get(randID)).getNeighboringProvinces(kxx)).getCivID() == 0
                              && !CFG.game
                                 .getCiv(nCivID)
                                 .isMovingUnitsToProvinceID(CFG.game.getProvince((Integer)canMoveImmediately.get(randID)).getNeighboringProvinces(kxx))) {
                              numOfNeutral++;
                           }
                        }

                        int tArmyToMove = CFG.game.getProvince((Integer)canMoveImmediately.get(randID)).getArmyCivID(nCivID);
                        if (numOfNeutral > 1) {
                           tArmyToMove = CFG.game.getProvince(possibleProvinces.get((Integer)sorted.get(var33)).iProvinceID).getArmy(0) + 5 + CFG.oR.nextInt(5);
                        }

                        Gdx.app.log("AoC", "EXPAND -> 000 movearmy: TOPROVINCEID: " + possibleProvinces.get((Integer)sorted.get(var33)).iProvinceID);
                        if (!CFG.gameAction
                           .moveArmy(
                              (Integer)canMoveImmediately.get(randID),
                              possibleProvinces.get((Integer)sorted.get(var33)).iProvinceID,
                              tArmyToMove,
                              nCivID,
                              true,
                              false
                           )) {
                           break;
                        }
                     }
                  }
               } else {
                  for (int var26 = CFG.game.getCiv(nCivID).lBordersWithNeutralProvincesID.size() - 1; var26 >= 0; var26--) {
                     possibleProvinces.add(new AI.NeutralProvinces(CFG.game.getCiv(nCivID).lBordersWithNeutralProvincesID.get(var26), nCivID));
                  }

                  int tBest = 0;

                  for (int i4 = possibleProvinces.size() - 1; i4 > 0; i4--) {
                     if (possibleProvinces.get(tBest).iScore < possibleProvinces.get(i4).iScore) {
                        tBest = i4;
                     }
                  }

                  CFG.game
                     .getCiv(nCivID)
                     .civGameData
                     .civPlans
                     .addNewArmyMission(
                        possibleProvinces.get(tBest).iProvinceID, new CivArmyMission_ExpandNeutralProvince(nCivID, possibleProvinces.get(tBest).iProvinceID)
                     );
               }
            } else if (maybeGoToTheSea) {
               Gdx.app.log("AoC", "iNeutralProvincesWithSeaAccessSize: " + this.iNeutralProvincesWithSeaAccessSize);
               maybeGoToTheSea = false;
               if (this.iNeutralProvincesWithSeaAccessSize > 0) {
                  ArrayList<AI.NeutralProvinces> possibleTo = new ArrayList<>();
                  ArrayList<Integer> possibleTo_MoveFrom = new ArrayList<>();
                  Gdx.app.log("AoC", "EXPAND EXTRA -> begin");

                  for (int i5 = 0; i5 < CFG.game.getCiv(nCivID).getCivRegionsSize(); i5++) {
                     if (CFG.game.getCiv(nCivID).getCivRegion(i5).getSeaAccess()) {
                        for (int j = 0; j < CFG.game.getCiv(nCivID).getCivRegion(i5).getProvincesSize(); j++) {
                           if (CFG.game.getProvince(CFG.game.getCiv(nCivID).getCivRegion(i5).getProvince(j)).getLevelOfPort() > 0) {
                              for (int kxxx = 0;
                                 kxxx < CFG.game.getProvince(CFG.game.getCiv(nCivID).getCivRegion(i5).getProvince(j)).getNeighboringSeaProvincesSize();
                                 kxxx++
                              ) {
                                 for (int o = 0;
                                    o
                                       < CFG.game
                                          .getProvince(
                                             CFG.game.getProvince(CFG.game.getCiv(nCivID).getCivRegion(i5).getProvince(j)).getNeighboringSeaProvinces(kxxx)
                                          )
                                          .getNeighboringProvincesSize();
                                    o++
                                 ) {
                                    if (!CFG.game
                                          .getProvince(
                                             CFG.game
                                                .getProvince(
                                                   CFG.game
                                                      .getProvince(CFG.game.getCiv(nCivID).getCivRegion(i5).getProvince(j))
                                                      .getNeighboringSeaProvinces(kxxx)
                                                )
                                                .getNeighboringProvinces(o)
                                          )
                                          .getSeaProvince()
                                       && CFG.game
                                             .getProvince(
                                                CFG.game
                                                   .getProvince(
                                                      CFG.game
                                                         .getProvince(CFG.game.getCiv(nCivID).getCivRegion(i5).getProvince(j))
                                                         .getNeighboringSeaProvinces(kxxx)
                                                   )
                                                   .getNeighboringProvinces(o)
                                             )
                                             .getWasteland()
                                          < 0
                                       && CFG.game
                                             .getProvince(
                                                CFG.game
                                                   .getProvince(
                                                      CFG.game
                                                         .getProvince(CFG.game.getCiv(nCivID).getCivRegion(i5).getProvince(j))
                                                         .getNeighboringSeaProvinces(kxxx)
                                                   )
                                                   .getNeighboringProvinces(o)
                                             )
                                             .getCivID()
                                          == 0) {
                                       possibleTo.add(
                                          new AI.NeutralProvinces(
                                             CFG.game
                                                .getProvince(
                                                   CFG.game
                                                      .getProvince(CFG.game.getCiv(nCivID).getCivRegion(i5).getProvince(j))
                                                      .getNeighboringSeaProvinces(kxxx)
                                                )
                                                .getNeighboringProvinces(o),
                                             nCivID
                                          )
                                       );
                                       possibleTo_MoveFrom.add(CFG.game.getCiv(nCivID).getCivRegion(i5).getProvince(j));
                                    }
                                 }
                              }
                           }
                        }
                     }
                  }

                  for (int var41 = 0; var41 < CFG.game.getCiv(nCivID).getCivRegionsSize(); var41++) {
                     if (CFG.game.getCiv(nCivID).getCivRegion(var41).getSeaAccess()) {
                        for (int jx = 0; jx < CFG.game.getCiv(nCivID).getCivRegion(var41).getProvincesSize(); jx++) {
                           if (CFG.game.getProvince(CFG.game.getCiv(nCivID).getCivRegion(var41).getProvince(jx)).getLevelOfPort() > 0) {
                              for (int kxxx = 0;
                                 kxxx < CFG.game.getProvince(CFG.game.getCiv(nCivID).getCivRegion(var41).getProvince(jx)).getNeighboringSeaProvincesSize();
                                 kxxx++
                              ) {
                                 for (int ox = 0;
                                    ox
                                       < CFG.game
                                          .getProvince(
                                             CFG.game.getProvince(CFG.game.getCiv(nCivID).getCivRegion(var41).getProvince(jx)).getNeighboringSeaProvinces(kxxx)
                                          )
                                          .getNeighboringProvincesSize();
                                    ox++
                                 ) {
                                    if (CFG.game
                                       .getProvince(
                                          CFG.game
                                             .getProvince(
                                                CFG.game
                                                   .getProvince(CFG.game.getCiv(nCivID).getCivRegion(var41).getProvince(jx))
                                                   .getNeighboringSeaProvinces(kxxx)
                                             )
                                             .getNeighboringProvinces(ox)
                                       )
                                       .getSeaProvince()) {
                                       for (int z = 0;
                                          z
                                             < CFG.game
                                                .getProvince(
                                                   CFG.game
                                                      .getProvince(
                                                         CFG.game
                                                            .getProvince(CFG.game.getCiv(nCivID).getCivRegion(var41).getProvince(jx))
                                                            .getNeighboringSeaProvinces(kxxx)
                                                      )
                                                      .getNeighboringProvinces(ox)
                                                )
                                                .getNeighboringProvincesSize();
                                          z++
                                       ) {
                                          if (!CFG.game
                                                .getProvince(
                                                   CFG.game
                                                      .getProvince(
                                                         CFG.game
                                                            .getProvince(
                                                               CFG.game
                                                                  .getProvince(CFG.game.getCiv(nCivID).getCivRegion(var41).getProvince(jx))
                                                                  .getNeighboringSeaProvinces(kxxx)
                                                            )
                                                            .getNeighboringProvinces(ox)
                                                      )
                                                      .getNeighboringProvinces(z)
                                                )
                                                .getSeaProvince()
                                             && CFG.game
                                                   .getProvince(
                                                      CFG.game
                                                         .getProvince(
                                                            CFG.game
                                                               .getProvince(
                                                                  CFG.game
                                                                     .getProvince(CFG.game.getCiv(nCivID).getCivRegion(var41).getProvince(jx))
                                                                     .getNeighboringSeaProvinces(kxxx)
                                                               )
                                                               .getNeighboringProvinces(ox)
                                                         )
                                                         .getNeighboringProvinces(z)
                                                   )
                                                   .getWasteland()
                                                < 0
                                             && CFG.game
                                                   .getProvince(
                                                      CFG.game
                                                         .getProvince(
                                                            CFG.game
                                                               .getProvince(
                                                                  CFG.game
                                                                     .getProvince(CFG.game.getCiv(nCivID).getCivRegion(var41).getProvince(jx))
                                                                     .getNeighboringSeaProvinces(kxxx)
                                                               )
                                                               .getNeighboringProvinces(ox)
                                                         )
                                                         .getNeighboringProvinces(z)
                                                   )
                                                   .getCivID()
                                                == 0) {
                                             possibleTo.add(
                                                new AI.NeutralProvinces(
                                                   CFG.game
                                                      .getProvince(
                                                         CFG.game
                                                            .getProvince(
                                                               CFG.game
                                                                  .getProvince(CFG.game.getCiv(nCivID).getCivRegion(var41).getProvince(jx))
                                                                  .getNeighboringSeaProvinces(kxxx)
                                                            )
                                                            .getNeighboringProvinces(ox)
                                                      )
                                                      .getNeighboringProvinces(z),
                                                   nCivID
                                                )
                                             );
                                             possibleTo_MoveFrom.add(CFG.game.getCiv(nCivID).getCivRegion(var41).getProvince(jx));
                                          }
                                       }
                                    }
                                 }
                              }
                           }
                        }
                     }
                  }

                  Gdx.app.log("AoC", "EXPAND EXTRA -> 11 -> possibleTo.size: " + possibleTo.size());
                  if (possibleTo.size() > 0) {
                     int tBest = 0;

                     for (int ix = possibleTo.size() - 1; ix > 0; ix--) {
                        if (possibleTo.get(tBest).iScore < possibleTo.get(ix).iScore) {
                           tBest = ix;
                        }
                     }

                     int neutralArmy = CFG.game.getProvince(possibleTo.get(tBest).iProvinceID).getArmy(0)
                        + 6
                        - CFG.game.getCiv(nCivID).isMovingUnitsToProvinceID_Num(possibleTo.get(tBest).iProvinceID)
                        - CFG.game.getCiv(nCivID).civGameData.civPlans.haveMission_Army(possibleTo.get(tBest).iProvinceID);
                     if (neutralArmy >= 0) {
                        if (CFG.game.getProvince(possibleTo_MoveFrom.get(tBest)).getArmyCivID(nCivID) > neutralArmy) {
                           RegroupArmy_Data tryRegroupArmy = new RegroupArmy_Data(nCivID, possibleTo_MoveFrom.get(tBest), possibleTo.get(tBest).iProvinceID);
                           if (tryRegroupArmy.getRouteSize() > 0
                              && CFG.gameAction.moveArmy(possibleTo_MoveFrom.get(tBest), tryRegroupArmy.getRoute(0), neutralArmy, nCivID, true, false)) {
                              if (tryRegroupArmy.getRouteSize() > 1) {
                                 CFG.game
                                    .getCiv(nCivID)
                                    .civGameData
                                    .civPlans
                                    .lArmiesMissions
                                    .add(
                                       new CivArmyMission_ExpandNeutral_Check(
                                          nCivID, tryRegroupArmy.getRoute(0), possibleTo.get(tBest).iProvinceID, neutralArmy
                                       )
                                    );
                              }

                              return false;
                           }
                        } else {
                           int tArmyToRecruit = neutralArmy - CFG.game.getProvince(possibleTo_MoveFrom.get(tBest)).getArmyCivID(nCivID);
                           CFG.game.getCiv(nCivID).recruitArmy_AI(possibleTo_MoveFrom.get(tBest), tArmyToRecruit);
                           int tempArmy = CFG.game.getCiv(nCivID).getRecruitArmy_BasedOnProvinceID(possibleTo_MoveFrom.get(tBest))
                              + CFG.game.getProvince(possibleTo_MoveFrom.get(tBest)).getArmyCivID(nCivID);
                           if (tempArmy > 0) {
                              CFG.game
                                 .getCiv(nCivID)
                                 .civGameData
                                 .civPlans
                                 .lArmiesMissions
                                 .add(
                                    new CivArmyMission_ExpandNeutral_Check(nCivID, possibleTo_MoveFrom.get(tBest), possibleTo.get(tBest).iProvinceID, tempArmy)
                                 );
                           }
                        }
                     }
                  } else {
                     possibleTo_MoveFrom.clear();
                     possibleTo.clear();

                     for (int zx = 0; zx < CFG.game.getCiv(nCivID).getCivRegionsSize(); zx++) {
                        if (CFG.game.getCiv(nCivID).getCivRegion(zx).getSeaAccess()) {
                           for (int jxx = 0; jxx < CFG.game.getCiv(nCivID).getCivRegion(zx).getProvincesSize(); jxx++) {
                              if (CFG.game.getProvince(CFG.game.getCiv(nCivID).getCivRegion(zx).getProvince(jxx)).getLevelOfPort() >= 0) {
                                 ArrayList<Integer> recentlyAdded = new ArrayList<>();
                                 ArrayList<Integer> was = new ArrayList<>();

                                 for (int k2 = 0;
                                    k2 < CFG.game.getProvince(CFG.game.getCiv(nCivID).getCivRegion(zx).getProvince(jxx)).getNeighboringSeaProvincesSize();
                                    k2++
                                 ) {
                                    recentlyAdded.add(
                                       CFG.game.getProvince(CFG.game.getCiv(nCivID).getCivRegion(zx).getProvince(jxx)).getNeighboringSeaProvinces(k2)
                                    );
                                    was.add(CFG.game.getProvince(CFG.game.getCiv(nCivID).getCivRegion(zx).getProvince(jxx)).getNeighboringSeaProvinces(k2));
                                    CFG.game
                                       .getProvince(
                                          CFG.game.getProvince(CFG.game.getCiv(nCivID).getCivRegion(zx).getProvince(jxx)).getNeighboringSeaProvinces(k2)
                                       )
                                       .was = true;
                                 }

                                 ArrayList currProvinces = new ArrayList();
                                 int nIteration_Distance = 0;
                                 boolean foundProvince = false;

                                 while (
                                    nIteration_Distance < CFG.game.getCiv(nCivID).civGameData.iExpandNeutralProvinces_RangeCheck && recentlyAdded.size() > 0
                                 ) {
                                    currProvinces.clear();
                                    nIteration_Distance++;

                                    for (int a = recentlyAdded.size() - 1; a >= 0; a--) {
                                       boolean wasntAdded = true;

                                       for (int p = currProvinces.size() - 1; p >= 0; p--) {
                                          if (currProvinces.get(p) == recentlyAdded.get(a)) {
                                             wasntAdded = false;
                                             break;
                                          }
                                       }

                                       if (wasntAdded) {
                                          currProvinces.add(recentlyAdded.get(a));
                                       }
                                    }

                                    recentlyAdded.clear();

                                    for (int var64 = currProvinces.size() - 1; var64 >= 0; var64--) {
                                       for (int i6 = 0; i6 < CFG.game.getProvince((Integer)currProvinces.get(var64)).getNeighboringProvincesSize(); i6++) {
                                          if (!CFG.game.getProvince(CFG.game.getProvince((Integer)currProvinces.get(var64)).getNeighboringProvinces(i6)).was) {
                                             was.add(CFG.game.getProvince((Integer)currProvinces.get(var64)).getNeighboringProvinces(i6));
                                             CFG.game.getProvince(CFG.game.getProvince((Integer)currProvinces.get(var64)).getNeighboringProvinces(i6)).was = true;
                                             if (CFG.game
                                                .getProvince(CFG.game.getProvince((Integer)currProvinces.get(var64)).getNeighboringProvinces(i6))
                                                .getSeaProvince()) {
                                                recentlyAdded.add(CFG.game.getProvince((Integer)currProvinces.get(var64)).getNeighboringProvinces(i6));
                                             } else if (CFG.game
                                                      .getProvince(CFG.game.getProvince((Integer)currProvinces.get(var64)).getNeighboringProvinces(i6))
                                                      .getCivID()
                                                   == 0
                                                && CFG.game
                                                      .getProvince(CFG.game.getProvince((Integer)currProvinces.get(var64)).getNeighboringProvinces(i6))
                                                      .getWasteland()
                                                   < 0) {
                                                possibleTo.add(
                                                   new AI.NeutralProvinces(
                                                      CFG.game.getProvince((Integer)currProvinces.get(var64)).getNeighboringProvinces(i6), nCivID
                                                   )
                                                );
                                                possibleTo_MoveFrom.add(CFG.game.getCiv(nCivID).getCivRegion(zx).getProvince(jxx));
                                                foundProvince = true;
                                                recentlyAdded.add(CFG.game.getProvince((Integer)currProvinces.get(var64)).getNeighboringProvinces(i6));
                                             }
                                          }
                                       }
                                    }

                                    if (!foundProvince) {
                                    }
                                 }

                                 for (int px = was.size() - 1; px >= 0; px--) {
                                    CFG.game.getProvince(was.get(px)).was = false;
                                 }

                                 recentlyAdded.clear();
                                 ArrayList<Integer> var57 = null;
                                 was.clear();
                                 Object var60 = null;
                              }
                           }
                        }
                     }

                     Gdx.app.log("AoC", "EXPAND EXTRA -> 22 -> possibleTo.size: " + possibleTo.size());
                     if (possibleTo.size() == 0) {
                        CFG.game.getCiv(nCivID).civGameData.iExpandNeutralProvinces_RangeCheck = Math.max(
                           CFG.game.getCiv(nCivID).civGameData.iExpandNeutralProvinces_RangeCheck + 1, CFG.game.getProvincesSize() / 15
                        );
                        this.expandToNeutralProvinces_Out(nCivID, false);
                     } else {
                        int tBest = 0;

                        for (int ixx = possibleTo.size() - 1; ixx > 0; ixx--) {
                           if (possibleTo.get(tBest).iScore < possibleTo.get(ixx).iScore) {
                              tBest = ixx;
                           }
                        }

                        Gdx.app.log("AoC", "EXPAND EXTRA -> 22 -> ACTION 0000 -> ProvinceID_TO: " + possibleTo.get(tBest).iProvinceID);
                        Gdx.app.log("AoC", "EXPAND EXTRA -> 22 -> ACTION 0000 -> ProvinceID_FROM: " + possibleTo_MoveFrom.get(tBest));
                        int neutralArmy = CFG.game.getProvince(possibleTo.get(tBest).iProvinceID).getArmy(0)
                           + 10
                           - CFG.game.getCiv(nCivID).isMovingUnitsToProvinceID_Num(possibleTo.get(tBest).iProvinceID)
                           - CFG.game.getCiv(nCivID).civGameData.civPlans.haveMission_Army(possibleTo.get(tBest).iProvinceID);
                        if (neutralArmy >= 0) {
                           Gdx.app.log("AoC", "EXPAND EXTRA -> 22 -> ACTION 1111");
                           if (CFG.game.getProvince(possibleTo_MoveFrom.get(tBest)).getArmyCivID(nCivID) > neutralArmy) {
                              Gdx.app.log("AoC", "EXPAND EXTRA -> 22 -> ACTION 2222");
                              RegroupArmy_Data_PortToBuild tryRegroupArmy = new RegroupArmy_Data_PortToBuild(
                                 nCivID, possibleTo_MoveFrom.get(tBest), possibleTo.get(tBest).iProvinceID
                              );
                              if (tryRegroupArmy.getRouteSize() > 0
                                 && CFG.gameAction.moveArmy(possibleTo_MoveFrom.get(tBest), tryRegroupArmy.getRoute(0), neutralArmy, nCivID, true, false)) {
                                 if (tryRegroupArmy.getRouteSize() > 1) {
                                    CFG.game
                                       .getCiv(nCivID)
                                       .civGameData
                                       .civPlans
                                       .lArmiesMissions
                                       .add(
                                          new CivArmyMission_Expand_BuildPort(
                                             nCivID, tryRegroupArmy.getRoute(0), possibleTo.get(tBest).iProvinceID, neutralArmy
                                          )
                                       );
                                 }

                                 return false;
                              }
                           } else {
                              Gdx.app.log("AoC", "EXPAND EXTRA -> 22 -> ACTION 3333");
                              int tArmyToRecruit = neutralArmy - CFG.game.getProvince(possibleTo_MoveFrom.get(tBest)).getArmyCivID(nCivID);
                              Gdx.app.log("AoC", "EXPAND EXTRA -> 22 -> ACTION tArmyToRecruit: " + tArmyToRecruit);
                              CFG.game.getCiv(nCivID).recruitArmy_AI(possibleTo_MoveFrom.get(tBest), tArmyToRecruit);
                              int tempArmy = CFG.game.getCiv(nCivID).getRecruitArmy_BasedOnProvinceID(possibleTo_MoveFrom.get(tBest))
                                 + CFG.game.getProvince(possibleTo_MoveFrom.get(tBest)).getArmyCivID(nCivID);
                              Gdx.app.log("AoC", "EXPAND EXTRA -> 22 -> ACTION tempArmy: " + tempArmy);
                              if (tempArmy > 0) {
                                 Gdx.app.log("AoC", "EXPAND EXTRA -> 22 -> ACTION ADDMISION");
                                 CFG.game
                                    .getCiv(nCivID)
                                    .civGameData
                                    .civPlans
                                    .lArmiesMissions
                                    .add(
                                       new CivArmyMission_Expand_BuildPort(nCivID, possibleTo_MoveFrom.get(tBest), possibleTo.get(tBest).iProvinceID, tempArmy)
                                    );
                              }
                           }
                        }
                     }
                  }
               }
            }
         }
      } catch (IndexOutOfBoundsException var21) {
         CFG.exceptionStack(var21);
      } catch (NullPointerException var22) {
         CFG.exceptionStack(var22);
      }

      return false;
   }

   public final List<AI_NeighProvinces> getAllNeighboringProvincesInRange_Regroup_ForNavalInvasion(
      int nProvinceID, int nCivID, int iRange, List<AI_NeighProvinces> out, List<Integer> was
   ) {
      ArrayList<Integer> recentlyAdded = new ArrayList<>();
      recentlyAdded.add(nProvinceID);
      was.add(nProvinceID);
      CFG.game.getProvince(nProvinceID).was = true;
      ArrayList currProvinces = new ArrayList();
      int nIteration_Distance = 0;
      int iFirstFoundRange = -1;

      while ((nIteration_Distance < iRange || out.size() == 0) && recentlyAdded.size() > 0) {
         currProvinces.clear();
         nIteration_Distance++;

         for (int a = recentlyAdded.size() - 1; a >= 0; a--) {
            boolean wasntAdded = true;

            for (int j = currProvinces.size() - 1; j >= 0; j--) {
               if (currProvinces.get(j) == recentlyAdded.get(a)) {
                  wasntAdded = false;
                  break;
               }
            }

            if (wasntAdded) {
               currProvinces.add(recentlyAdded.get(a));
            }
         }

         recentlyAdded.clear();

         for (int var15 = currProvinces.size() - 1; var15 >= 0; var15--) {
            for (int i = 0; i < CFG.game.getProvince((Integer)currProvinces.get(var15)).getNeighboringProvincesSize(); i++) {
               if (!CFG.game.getProvince(CFG.game.getProvince((Integer)currProvinces.get(var15)).getNeighboringProvinces(i)).was) {
                  was.add(CFG.game.getProvince((Integer)currProvinces.get(var15)).getNeighboringProvinces(i));
                  CFG.game.getProvince(CFG.game.getProvince((Integer)currProvinces.get(var15)).getNeighboringProvinces(i)).was = true;
                  if (CFG.game
                     .isAlly(nCivID, CFG.game.getProvince(CFG.game.getProvince((Integer)currProvinces.get(var15)).getNeighboringProvinces(i)).getCivID())) {
                     if (CFG.game.getProvince(CFG.game.getProvince((Integer)currProvinces.get(var15)).getNeighboringProvinces(i)).getArmyCivID(nCivID) > 0) {
                        out.add(new AI_NeighProvinces(CFG.game.getProvince((Integer)currProvinces.get(var15)).getNeighboringProvinces(i), nIteration_Distance));
                        if (iFirstFoundRange < 0) {
                           iFirstFoundRange = nIteration_Distance;
                        }
                     }

                     recentlyAdded.add(CFG.game.getProvince((Integer)currProvinces.get(var15)).getNeighboringProvinces(i));
                  }
               }
            }
         }

         if (iFirstFoundRange > 0 && iFirstFoundRange + 2 >= nIteration_Distance) {
         }
      }

      for (int jx = was.size() - 1; jx >= 0; jx--) {
         CFG.game.getProvince(was.get(jx)).was = false;
      }

      recentlyAdded.clear();
      ArrayList<Integer> var14 = null;
      was.clear();
      List<Integer> var13 = null;
      return out;
   }

   public final boolean prepareForWar_BordersWithEnemy(int nCivID, int nProvinceID) {
      if (CFG.game.getProvince(nProvinceID).getBordersWithEnemy()) {
         for (int z = 0; z < CFG.game.getProvince(nProvinceID).getNeighboringProvincesSize(); z++) {
            if (CFG.game.getCivsAtWar(nCivID, CFG.game.getProvince(CFG.game.getProvince(nProvinceID).getNeighboringProvinces(z)).getCivID())) {
               return true;
            }
         }
      }

      return this.prepareForWar_BordersWithEnemy_Just(nCivID, nProvinceID);
   }

   public final boolean prepareForWar_BordersWithEnemy_Just(int nCivID, int nProvinceID) {
      for (int u = 0; u < CFG.game.getCiv(nCivID).civGameData.civPlans.iWarPreparationsSize; u++) {
         for (int k = 0; k < CFG.game.getProvince(nProvinceID).getNeighboringProvincesSize(); k++) {
            if (CFG.game.getProvince(CFG.game.getProvince(nProvinceID).getNeighboringProvinces(k)).getCivID()
               == CFG.game.getCiv(nCivID).civGameData.civPlans.warPreparations.get(u).onCivID) {
               return true;
            }
         }
      }

      for (int u = 0; u < CFG.game.getCiv(nCivID).civGameData.civPlans.iCasusBelliSize; u++) {
         for (int kx = 0; kx < CFG.game.getProvince(nProvinceID).getNeighboringProvincesSize(); kx++) {
            if (CFG.game.getProvince(CFG.game.getProvince(nProvinceID).getNeighboringProvinces(kx)).getCivID()
               == CFG.game.getCiv(nCivID).civGameData.civPlans.casusBelli.get(u).onCivID) {
               return true;
            }
         }
      }

      return false;
   }

   interface Expand {
      boolean expandToNeutralProvinces(int var1);
   }

   public class NeutralProvinces {
      public int iProvinceID;
      public float iScore;

      public NeutralProvinces(int nProvinceID, int nCivID) {
         this.iProvinceID = nProvinceID;
         this.buildScore(nCivID);
      }

      public final void buildScore(int nCivID) {
         int neighboring_NeutralProvinces = 0;
         int neighboring_CivProvinces = 0;
         int neighboring_OtherCivProvinces = 0;

         for (int i = 0; i < CFG.game.getProvince(this.iProvinceID).getNeighboringProvincesSize(); i++) {
            if (CFG.game.getProvince(CFG.game.getProvince(this.iProvinceID).getNeighboringProvinces(i)).getWasteland() < 0) {
               if (CFG.game.getProvince(CFG.game.getProvince(this.iProvinceID).getNeighboringProvinces(i)).getCivID() == nCivID) {
                  this.iScore = CFG.game.getProvince(this.iProvinceID).getNeighboringProvinces(i) == CFG.game.getCiv(nCivID).getCapitalProvinceID()
                     ? (this.iScore = this.iScore + CFG.game.getCiv(nCivID).civGameData.civPersonality.NEUTRAL_EXPAND_CAPITAL)
                     : (this.iScore = this.iScore + CFG.game.getCiv(nCivID).civGameData.civPersonality.NEUTRAL_EXPAND_OWN_PROVINCE);
                  neighboring_CivProvinces++;
               } else if (CFG.game.getProvince(CFG.game.getProvince(this.iProvinceID).getNeighboringProvinces(i)).getCivID() == 0) {
                  neighboring_NeutralProvinces++;
                  this.iScore = this.iScore + CFG.game.getCiv(nCivID).civGameData.civPersonality.NEUTRAL_EXPAND_MORE_NEUTRAL;
               } else {
                  neighboring_OtherCivProvinces++;
                  this.iScore = this.iScore + CFG.game.getCiv(nCivID).civGameData.civPersonality.NEUTRAL_EXPAND_OTHER_CIV;
               }
            }
         }

         this.iScore = this.iScore
            + CFG.game.getCiv(nCivID).civGameData.civPersonality.NEUTRAL_EXPAND_GROWTH_RATE * CFG.game.getProvince(this.iProvinceID).getGrowthRate_Population();
         if (CFG.game.getProvince(this.iProvinceID).getNeighboringSeaProvincesSize() > 0) {
            this.iScore = this.iScore
               + (
                  CFG.game.getCiv(nCivID).civGameData.civPersonality.NEUTRAL_EXPAND_SEA_ACCESS
                     + CFG.game.getCiv(nCivID).civGameData.civPersonality.NEUTRAL_EXPAND_SEA_ACCESS_EXTRA
                        * CFG.game.getProvince(this.iProvinceID).getNeighboringSeaProvincesSize()
               );
         }

         this.iScore = this.iScore
            + CFG.game.getCiv(nCivID).civGameData.civPersonality.NEUTRAL_EXPAND_NEIGHBORING_PROVINCES
               * (neighboring_CivProvinces + neighboring_NeutralProvinces + neighboring_OtherCivProvinces);
         this.iScore = this.iScore
            + (int)(
               CFG.game.getCiv(nCivID).civGameData.civPersonality.NEUTRAL_EXPAND_NEIGHBORING_PROVINCES_POTENITAL
                  * (neighboring_NeutralProvinces + neighboring_CivProvinces)
                  / (neighboring_CivProvinces + neighboring_NeutralProvinces + neighboring_OtherCivProvinces)
            );
         if (neighboring_NeutralProvinces == 0 && CFG.game.getProvince(this.iProvinceID).getNeighboringProvincesSize() > 0) {
            this.iScore = this.iScore + CFG.game.getCiv(nCivID).civGameData.civPersonality.NEUTRAL_EXPAND_LAST_PROVINCE;
         } else if (neighboring_CivProvinces <= 1) {
            this.iScore *= 0.725F;
         }
      }
   }
}
