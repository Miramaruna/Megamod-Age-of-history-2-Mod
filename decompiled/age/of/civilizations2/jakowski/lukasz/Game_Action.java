package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.GdxRuntimeException;
import java.util.ArrayList;
import java.util.List;

public class Game_Action {
   public Game_Action.TurnStates activeTurnAction = Game_Action.TurnStates.INPUT_ORDERS;
   public MoveUnits_TurnData currentMoveUnits = null;
   public int iPlayerAttack_ShowArmyInProvinceID = -1;
   public boolean SHOW_REPORT = false;
   public static float fTroopBreakthroughChance = 0.3F;
   public int MDP;
   public int MAP;
   public Turn_Actions turnActions;
   public Turn_NewTurn turnNewTurn;
   public boolean updatePosOfMap_NewTurn = false;
   public static final float POINTS_PER_ENEMY = 6.0F;
   public static float RISE_REVOLT_RISK_HAPPINESS = 0.56F;
   public static float RISE_REVOLT_RISK_STABILITY = 0.62F;
   public int eRTO_START = 0;
   public int eRTO_START2 = 0;
   public int eRTO_START3 = 0;
   public static final int MAX_RELATION = 30;
   public static final int MIN_RELATION = -20;
   public static final float MAX_RELATION_DIFF = 0.295F;
   public static final float MIN_RELATION_DIFF = 0.0145F;
   public static final float UPRISE_MIN = 0.16F;
   public static final float UPRISE_IGNITE = 0.64F;
   public static final int SPAWN_REVOLUTIONARY_ARMY_MIN = 10;
   public static final int SPAWN_REVOLUTIONARY_ARMY_RANDOM = 50;
   public int diceAggressors;
   public int diceDefenders;
   public int diceAggressorsCivID;
   public int diceDefendersCivID;
   public static final float DICE_ROLL_BONUS = 2.5F;
   public static final int TECHNOLGY_LEVEL_BONUS_ARMY = 18;
   public static final float DEFENSE_BONUS_LOSS_PER_TURN_FOR_NOT_SUPPLIED_PROVINCE = 0.1F;
   public static final int NOT_SUPPLIED_PROVINCE_STRAVE_START_NUM_OF_TURNS = 2;
   public static final float NOT_SUPPLIED_PROVINCE_STRAVE__PERC_PER_TURN = 0.04F;
   public static final int NOT_SUPPLIED_PROVINCE_LOOSE_CONTROL = 10;
   public static boolean gameEnded = false;
   public static final float RECRUITABLE_ARMY_OWN_POP = 0.175F;
   public static final float RECRUITABLE_ARMY_OTHER_POP_ALLIANCE = 0.125F;
   public static final float RECRUITABLE_ARMY_NEUTRAL_POP = 0.0675F;
   public static final float RECRUITABLE_ARMY_OTHER_POP = 0.00725F;
   public static final float RECRUITABLE_ARMY_OTHER_POP_ATWAR = 0.0025F;
   public static final float RECRUIT_HAPPINESS_CHANGE = 0.1375F;
   public static final float RECRUIT_ECONOMY_CHANGE = 0.575F;
   public static final float RECRUIT_DEVELOPMENT_CHANGE = 0.1625F;
   public static final float DISBAND_PERC_POP = 0.05F;
   public static final float MOVE_CAPITAL_HAPPINESS_CHANGE_OLD = 0.12168F;
   public static final float MOVE_CAPITAL_HAPPINESS_CHANGE_NEW = 0.025F;
   public static final int MOVE_CAPITAL_LOCK_MOVING_FOR_X_TURNS = 50;
   public static final float BASE_COST_OF_MOVE_CAPITAL_PERC = 0.1925F;
   public static final float BASE_COST_OF_MOVE_CAPITAL_POP_OF_CAPITAL_PERC = 0.125F;

   Game_Action() {
   }

   public final void tryToTakeNexTurn() {
      if (CFG.menuManager.getVisibleInGame_Event()) {
         CFG.menuManager.centerInGame_Event();
      } else {
         if (!CFG.SPECTATOR_MODE && this.activeTurnAction == Game_Action.TurnStates.INPUT_ORDERS) {
            for (int i = 0;
               i < CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCivilization_Diplomacy_GameData().messageBox.getMessagesSize();
               i++
            ) {
               if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).requestsResponse
                  && CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).iNumOfTurnsLeft
                     <= 1) {
                  this.checkMessages_PauseRTS();
                  CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).onAction(i);
                  CFG.toast.setInView(CFG.langManager.get("TheMessageRequiresAResponse"), CFG.COLOR_TEXT_NUM_OF_PROVINCES);
                  CFG.toast.setTimeInView(3000);
                  return;
               }
            }

            this.checkMessages_PauseRTS();
         }

         this.nextTurn();
      }
   }

   public final void checkMessages_PauseRTS() {
      if (!CFG.SPECTATOR_MODE) {
         for (int i = 0;
            i < CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCivilization_Diplomacy_GameData().messageBox.getMessagesSize();
            i++
         ) {
            if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).willPauseTheGame
               )
             {
               CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).willPauseTheGame = false;
               if (!RTS.PAUSE) {
                  RTS.pauseUnpause();
                  return;
               }
            }
         }
      }
   }

   public final void nextTurn() {
      this.resetTurnData();
      this.hideAllViews();
      switch (this.activeTurnAction) {
         case INPUT_ORDERS:
            CFG.game.resetLastActiveProvince();
            if (CFG.game.getPlayersSize() == 1) {
               this.updatePlayerData();
               this.endOfInputOrders();
            } else {
               this.inputOrders();
            }

            return;
         case LOAD_AI_RTO:
            CFG.menuManager.updateInGameRTO(false);
            this.turnMoves();
            Gdx.app.log("AoC", "GA, LOAD_AI_RTO -> AI end");
            return;
         case TURN_ACTIONS:
            this.turnMoves();
            return;
         case LOADING_NEXT_TURN:
            this.startNewTurn_End();
            return;
         case START_NEXT_TURN:
      }
   }

   public final void endOfInputOrders() {
      CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(0).setClickable(false);
      this.activeTurnAction = Game_Action.TurnStates.LOAD_AI_RTO;
      if (this.getNumOfPlayersInGame() > 1) {
         CFG.menuManager.updateInGame_TOP_All_NextTurnActions(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
      }

      this.eRTO_START = 0;
      this.eRTO_START2 = 0;
      this.eRTO_START3 = 0;
      CFG.game.getRTO().buildRandomOrder();
      CFG.menuManager.updateInGameRTO(true);
      if (!CFG.isDesktop()) {
         Turn_Actions.runRevolts();
      }

      this.turnActions = new Turn_Actions();
      this.turnActions.start();
   }

   public final void startNewTurn() {
      Menu_InGame.TIME_CONTINUE = -1L;
      CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(0).setClickable(false);
      if (CFG.isAndroid()) {
         Turn_NewTurn.doAction();
         this.startNewTurn_End();
      } else {
         this.activeTurnAction = Game_Action.TurnStates.LOADING_NEXT_TURN;
         this.turnNewTurn = new Turn_NewTurn();
         this.turnNewTurn.start();
      }
   }

   public final void startNewTurn_End() {
      CFG.PLAYER_TURNID = 0;
      CFG.gameAction.loadActivePlayerData();
      this.updatePosOfMap_NewTurn = false;
      CFG.menuManager.updateInGame_TOP_All(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
      CFG.map.getMapBG().disposeMinimapOfCivilizations();
      CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(0).setText(CFG.langManager.get("NextTurn"));
      CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(0).setClickable(true);
      CFG.gameAction.setActiveTurnState(Game_Action.TurnStates.INPUT_ORDERS);
      Game_Render.updateDrawMoveUnits();
      CFG.game.updateDrawMoveUnitsArmy();
      Menu_InGame_Messages.START_ANIMATION = true;
      if (Game_Calendar.TURN_ID % 100 == 92 && !CFG.SPECTATOR_MODE) {
         CFG.soundsManager.playSound(SoundsManager.SOUND_CROW);
      }

      Game_Render.updateRenderer();
      CFG.game.checkProvinceActionMenu();
   }

   public final void updatePlayerData() {
      CFG.game.getPlayer(CFG.PLAYER_TURNID).iBefore_PosX = CFG.map.getMapCoordinates().getPosX();
      CFG.game.getPlayer(CFG.PLAYER_TURNID).iBefore_PosY = CFG.map.getMapCoordinates().getPosY();
      CFG.game.getPlayer(CFG.PLAYER_TURNID).fBefore_Scale = CFG.map.getMapScale().getCurrentScale();
      CFG.game.getPlayer(CFG.PLAYER_TURNID).iACTIVE_VIEW_MODE = CFG.viewsManager.getActiveViewID();
      CFG.game.getPlayer(CFG.PLAYER_TURNID).visible_CivInfo = CFG.menuManager.getVisible_InGame_CivInfo() ? CFG.getActiveCivInfo() : -1;
      CFG.game.getPlayer(CFG.PLAYER_TURNID).visible_Outliner = CFG.menuManager.getVisible_Menu_InGame_Outliner();
      CFG.game.getPlayer(CFG.PLAYER_TURNID).visible_CensusOfProvince = CFG.menuManager.getVisibleInGame_CensusOfProvince()
         ? Menu_InGame_CensusOfProvince.PROVINCE_ID
         : -1;
      CFG.game.getPlayer(CFG.PLAYER_TURNID).visible_Wars = CFG.menuManager.getVisibleInGame_Wars();
      CFG.game.getPlayer(CFG.PLAYER_TURNID).visible_WarStats = CFG.menuManager.getVisibleInGame_WarDetails() ? Menu_InGame_WarDetails.WAR_ID : -1;
      CFG.game.getPlayer(CFG.PLAYER_TURNID).visible_Alliances = CFG.menuManager.getVisibleInGame_MilitaryAlliances();
      CFG.game.getPlayer(CFG.PLAYER_TURNID).visible_Alliance = CFG.menuManager.getVisible_InGame_Alliance() ? Menu_InGame_Alliance.ALLIANCE_ID : -1;
      CFG.game.getPlayer(CFG.PLAYER_TURNID).visible_Rank = CFG.menuManager.getVisibleInGame_Rank();
      CFG.game.getPlayer(CFG.PLAYER_TURNID).visible_ConqueredProvinces = CFG.menuManager.getVisibleInGame_ConquredProvinces();
      CFG.game.getPlayer(CFG.PLAYER_TURNID).visible_VictoryConditions = CFG.menuManager.getVisibleInGame_VictoryConditions();
      CFG.game.getPlayer(CFG.PLAYER_TURNID).visible_UpgradingArmy = CFG.menuManager.getVisibleInGame_UpgradingArmy();
      CFG.game.getPlayer(CFG.PLAYER_TURNID).visible_BuildingsConstructed = CFG.menuManager.getVisibleInGame_BuildingsConstructed();
      CFG.game.getPlayer(CFG.PLAYER_TURNID).visible_RecruitedArmy = CFG.menuManager.getVisibleInGame_RecruitedArmy();
      CFG.game.getPlayer(CFG.PLAYER_TURNID).visible_Tribute = CFG.menuManager.getVisibleInGame_Tribute();
      CFG.game.getPlayer(CFG.PLAYER_TURNID).visible_Technology = CFG.menuManager.getVisibleInGame_Technology();
      CFG.game.getPlayer(CFG.PLAYER_TURNID).visible_Army = CFG.menuManager.getVisibleInGame_Army();
      CFG.game.getPlayer(CFG.PLAYER_TURNID).visible_WorldPop = CFG.menuManager.getVisibleInGame_WorldPopulation();
      CFG.game.getPlayer(CFG.PLAYER_TURNID).visible_MapModes = CFG.menuManager.getVisible_InGame_MapModes();
      CFG.game.getPlayer(CFG.PLAYER_TURNID).visible_History = CFG.menuManager.getVisibleInGame_History();
      CFG.game.getPlayer(CFG.PLAYER_TURNID).visible_BuildingsMore = CFG.menuManager.getInGame_ProvinceBuild_Visible();
      CFG.game.getPlayer(CFG.PLAYER_TURNID).visible_HRE = CFG.menuManager.getVisibleInGame_HRE();
      this.hideExtraViews();
   }

   public final void hideExtraViews() {
      try {
         CFG.menuManager.setVisible_InGame_CivInfo(false);
         CFG.menuManager.setVisible_InGame_FlagAction(false);
         CFG.menuManager.setVisibleInGame_WarDetails(false);
         CFG.menuManager.setVisibleInGame_Wars(false);
         CFG.menuManager.setVisibleInGame_CensusOfProvince(false);
         CFG.menuManager.setVisibleInGame_Rank(false);
         CFG.menuManager.setVisibleInGame_MilitaryAlliances(false);
         CFG.menuManager.setVisible_InGame_Alliance(false);
         CFG.menuManager.setVisible_Menu_InGame_Outliner(false);
         CFG.menuManager.setVisibleInGame_WorldPopulation(false);
         CFG.menuManager.setVisible_InGame_MapModes(false);
         CFG.menuManager.setVisibleInGame_Playlist(false);
         CFG.menuManager.setVisibleInGame_WarPreparations(false);
         CFG.menuManager.setVisibleInGame_ConquredProvinces(false);
         CFG.menuManager.setVisibleInGame_VictoryConditions(false);
         CFG.menuManager.setVisibleInGame_BuildingsConstructed(false);
         CFG.menuManager.setVisibleInGame_RecruitedArmy(false);
         CFG.menuManager.setVisibleInGame_Tribute(false);
         CFG.menuManager.setVisibleInGame_Technology(false);
         CFG.menuManager.setVisibleInGame_Wonders(false);
         CFG.menuManager.setVisibleInGame_SendMessage(false);
         CFG.menuManager.setVisibleInGame_Plunder(false);
         CFG.menuManager.setVisibleInGame_MessageView(false);
         CFG.menuManager.setVisible_Menu_InGame_War(false);
         CFG.menuManager.setVisible_Menu_InGame_CapitalMoved(false);
         CFG.menuManager.setVisible_Menu_InGame_VassalReleased(false);
         CFG.menuManager.setVisible_Menu_InGame_CityHaveBeenFounded(false);
         CFG.menuManager.setVisible_Menu_InGame_AllianceInfo(false);
         CFG.menuManager.setVisible_InGame_Budget(false);
         CFG.menuManager.setVisible_InGame_Politics(false);
         CFG.menuManager.setVisible_Menu_InGame_CurrentWars(false);
         CFG.menuManager.setVisible_InGame_HRE(false);
         CFG.menuManager.setVisible_InGame_HRE_VoteFor(false);
         CFG.menuManager.setVisible_Menu_InGame_Graph(false);
         CFG.menuManager.setVisibleInGame_History(false);
         CFG.menuManager.setVisibleInGame_Genocide(false);
      } catch (IndexOutOfBoundsException var2) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var2);
         }
      }
   }

   public final void inputOrders() {
      this.updatePlayerData();
      if (CFG.PLAYER_TURNID == CFG.game.getPlayersSize() - 1) {
         this.endOfInputOrders();
      } else {
         CFG.PLAYER_TURNID++;
         this.updatePosOfMap_NewTurn = true;
         this.loadActivePlayerData();
         if (CFG.FOG_OF_WAR == 2) {
            CFG.map.getMapBG().disposeMinimapOfCivilizations();
         }
      }
   }

   public final void updateIsSupplied() {
      try {
         for (int i2 = 1; i2 < CFG.game.getCivsSize(); i2++) {
            if (CFG.game.getCiv(i2).getNumOfProvinces() > 0) {
               for (int j = 0; j < CFG.game.getCiv(i2).getCivRegionsSize(); j++) {
                  if (!CFG.game.getCiv(i2).getCivRegion(j).getHaveNotOccupiedProvince()) {
                     try {
                        for (int k = 0; k < CFG.game.getCiv(i2).getCivRegion(j).getProvincesSize(); k++) {
                           for (int o = 0; o < CFG.game.getProvince(CFG.game.getCiv(i2).getCivRegion(j).getProvince(k)).getNeighboringProvincesSize(); o++) {
                              if (CFG.game
                                    .getProvince(CFG.game.getProvince(CFG.game.getCiv(i2).getCivRegion(j).getProvince(k)).getNeighboringProvinces(o))
                                    .getWasteland()
                                 < 0) {
                                 if (CFG.game
                                       .getProvince(CFG.game.getProvince(CFG.game.getCiv(i2).getCivRegion(j).getProvince(k)).getNeighboringProvinces(o))
                                       .getCivID()
                                    == 0) {
                                    CFG.game.getCiv(i2).getCivRegion(j).setIsSupplied(true);
                                    k = CFG.game.getCiv(i2).getCivRegion(j).getProvincesSize();
                                    break;
                                 }

                                 if (CFG.game
                                          .getProvince(CFG.game.getProvince(CFG.game.getCiv(i2).getCivRegion(j).getProvince(k)).getNeighboringProvinces(o))
                                          .getCivID()
                                       != i2
                                    && CFG.game
                                       .getCiv(
                                          CFG.game
                                             .getProvince(CFG.game.getProvince(CFG.game.getCiv(i2).getCivRegion(j).getProvince(k)).getNeighboringProvinces(o))
                                             .getCivID()
                                       )
                                       .getCivRegion(
                                          CFG.game
                                             .getProvince(CFG.game.getProvince(CFG.game.getCiv(i2).getCivRegion(j).getProvince(k)).getNeighboringProvinces(o))
                                             .getCivRegionID()
                                       )
                                       .getHaveNotOccupiedProvince()
                                    && (
                                       CFG.game
                                                .getCiv(
                                                   CFG.game
                                                      .getProvince(
                                                         CFG.game.getProvince(CFG.game.getCiv(i2).getCivRegion(j).getProvince(k)).getNeighboringProvinces(o)
                                                      )
                                                      .getCivID()
                                                )
                                                .getPuppetOfCivID()
                                             == i2
                                          || CFG.game.getCiv(i2).getPuppetOfCivID()
                                             == CFG.game
                                                .getProvince(
                                                   CFG.game.getProvince(CFG.game.getCiv(i2).getCivRegion(j).getProvince(k)).getNeighboringProvinces(o)
                                                )
                                                .getCivID()
                                          || CFG.game.getCiv(i2).getAllianceID() > 0
                                             && CFG.game.getCiv(i2).getAllianceID()
                                                == CFG.game
                                                   .getCiv(
                                                      CFG.game
                                                         .getProvince(
                                                            CFG.game.getProvince(CFG.game.getCiv(i2).getCivRegion(j).getProvince(k)).getNeighboringProvinces(o)
                                                         )
                                                         .getCivID()
                                                   )
                                                   .getAllianceID()
                                          || CFG.game
                                                .getMilitaryAccess(
                                                   i2,
                                                   CFG.game
                                                      .getProvince(
                                                         CFG.game.getProvince(CFG.game.getCiv(i2).getCivRegion(j).getProvince(k)).getNeighboringProvinces(o)
                                                      )
                                                      .getCivID()
                                                )
                                             > 0
                                    )) {
                                    CFG.game.getCiv(i2).getCivRegion(j).setIsSupplied(true);
                                    k = CFG.game.getCiv(i2).getCivRegion(j).getProvincesSize();
                                    break;
                                 }
                              }
                           }
                        }
                     } catch (IndexOutOfBoundsException var5) {
                        CFG.exceptionStack(var5);
                     }
                  }
               }
            }
         }
      } catch (IndexOutOfBoundsException var6) {
      }

      for (int i2x = 0; i2x < CFG.game.getProvincesSize(); i2x++) {
         if (!CFG.game.getProvince(i2x).getSeaProvince() && CFG.game.getProvince(i2x).getWasteland() < 0 && CFG.game.getProvince(i2x).getCivID() > 0) {
            CFG.game.getProvince(i2x).updateIsNotSuppliedForXTurns();
            CFG.game.getProvince(i2x).updateDefensivePosition();
         }
      }

      this.updateIsSupplied_Twice();
   }

   public final void updateIsSupplied_Twice() {
      try {
         for (int i2 = 1; i2 < CFG.game.getCivsSize(); i2++) {
            if (CFG.game.getCiv(i2).getNumOfProvinces() > 0) {
               for (int j = 0; j < CFG.game.getCiv(i2).getCivRegionsSize(); j++) {
                  if (!CFG.game
                     .getCiv(i2)
                     .getCivRegion(j)
                     .setIsSupplied(CFG.game.getCiv(i2).getCivRegion(j).getSeaAccess() || CFG.game.getCiv(i2).getCivRegion(j).getHaveNotOccupiedProvince())) {
                     try {
                        for (int k = 0; k < CFG.game.getCiv(i2).getCivRegion(j).getProvincesSize(); k++) {
                           for (int o = 0; o < CFG.game.getProvince(CFG.game.getCiv(i2).getCivRegion(j).getProvince(k)).getNeighboringProvincesSize(); o++) {
                              if (CFG.game
                                    .getProvince(CFG.game.getProvince(CFG.game.getCiv(i2).getCivRegion(j).getProvince(k)).getNeighboringProvinces(o))
                                    .getWasteland()
                                 < 0) {
                                 if (CFG.game
                                       .getProvince(CFG.game.getProvince(CFG.game.getCiv(i2).getCivRegion(j).getProvince(k)).getNeighboringProvinces(o))
                                       .getCivID()
                                    == 0) {
                                    CFG.game.getCiv(i2).getCivRegion(j).setIsSupplied(true);
                                    k = CFG.game.getCiv(i2).getCivRegion(j).getProvincesSize();
                                    break;
                                 }

                                 if (CFG.game
                                          .getProvince(CFG.game.getProvince(CFG.game.getCiv(i2).getCivRegion(j).getProvince(k)).getNeighboringProvinces(o))
                                          .getCivID()
                                       != i2
                                    && CFG.game
                                       .getCiv(
                                          CFG.game
                                             .getProvince(CFG.game.getProvince(CFG.game.getCiv(i2).getCivRegion(j).getProvince(k)).getNeighboringProvinces(o))
                                             .getCivID()
                                       )
                                       .getCivRegion(
                                          CFG.game
                                             .getProvince(CFG.game.getProvince(CFG.game.getCiv(i2).getCivRegion(j).getProvince(k)).getNeighboringProvinces(o))
                                             .getCivRegionID()
                                       )
                                       .getHaveNotOccupiedProvince()
                                    && (
                                       CFG.game
                                                .getCiv(
                                                   CFG.game
                                                      .getProvince(
                                                         CFG.game.getProvince(CFG.game.getCiv(i2).getCivRegion(j).getProvince(k)).getNeighboringProvinces(o)
                                                      )
                                                      .getCivID()
                                                )
                                                .getPuppetOfCivID()
                                             == i2
                                          || CFG.game.getCiv(i2).getPuppetOfCivID()
                                             == CFG.game
                                                .getProvince(
                                                   CFG.game.getProvince(CFG.game.getCiv(i2).getCivRegion(j).getProvince(k)).getNeighboringProvinces(o)
                                                )
                                                .getCivID()
                                          || CFG.game.getCiv(i2).getAllianceID() > 0
                                             && CFG.game.getCiv(i2).getAllianceID()
                                                == CFG.game
                                                   .getCiv(
                                                      CFG.game
                                                         .getProvince(
                                                            CFG.game.getProvince(CFG.game.getCiv(i2).getCivRegion(j).getProvince(k)).getNeighboringProvinces(o)
                                                         )
                                                         .getCivID()
                                                   )
                                                   .getAllianceID()
                                          || CFG.game
                                                .getMilitaryAccess(
                                                   i2,
                                                   CFG.game
                                                      .getProvince(
                                                         CFG.game.getProvince(CFG.game.getCiv(i2).getCivRegion(j).getProvince(k)).getNeighboringProvinces(o)
                                                      )
                                                      .getCivID()
                                                )
                                             > 0
                                    )) {
                                    CFG.game.getCiv(i2).getCivRegion(j).setIsSupplied(true);
                                    k = CFG.game.getCiv(i2).getCivRegion(j).getProvincesSize();
                                    break;
                                 }
                              }
                           }
                        }
                     } catch (IndexOutOfBoundsException var5) {
                        CFG.exceptionStack(var5);
                     }
                  }
               }
            }
         }
      } catch (IndexOutOfBoundsException var6) {
      }

      for (int i2x = 0; i2x < CFG.game.getProvincesSize(); i2x++) {
         if (!CFG.game.getProvince(i2x).getSeaProvince() && CFG.game.getProvince(i2x).getWasteland() < 0 && CFG.game.getProvince(i2x).getCivID() > 0) {
            CFG.game.getProvince(i2x).updateIsNotSuppliedForXTurns_Twice();
         }
      }
   }

   public final void updateCivsHappiness() {
      for (int i = 1; i < CFG.game.getCivsSize(); i++) {
         this.updateCivsHappiness(i);
      }
   }

   public final void updateCivsHappiness(int nCivID) {
      CFG.game.getCiv(nCivID).setHappiness((int)(this.getCivHappiness(nCivID) * 100.0F));
   }

   public final float getCivHappiness(int nCivID) {
      float tHappiness = 0.0F;
      CFG.game.getCiv(nCivID).lProvincesWithLowHappiness.clear();

      for (int i = 0; i < CFG.game.getCiv(nCivID).getNumOfProvinces(); i++) {
         tHappiness += CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getHappiness();
         if (CFG.game.getCiv(nCivID).civGameData.civPersonality.MIN_PROVINCE_HAPPINESS_RUN_FESTIVAL
            > CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getHappiness()) {
            CFG.game.getCiv(nCivID).lProvincesWithLowHappiness.add(CFG.game.getCiv(nCivID).getProvinceID(i));
         }
      }

      for (int var4 = CFG.game.getCiv(nCivID).lProvincesWithLowHappiness.size() - 1; var4 >= 0; var4--) {
         if (CFG.game.getCiv(nCivID).isFestivalOrganized(CFG.game.getCiv(nCivID).lProvincesWithLowHappiness.get(var4))) {
            CFG.game.getCiv(nCivID).lProvincesWithLowHappiness.remove(var4);
         }
      }

      return tHappiness / CFG.game.getCiv(nCivID).getNumOfProvinces();
   }

   public final void updateCivsMovementPoints() {
      for (int i = 1; i < CFG.game.getCivsSize(); i++) {
         this.updateCivsMovementPoints(i);
      }
   }

   public final void updateCivsMovementPoints(int nCivID) {
      CFG.game
         .getCiv(nCivID)
         .setMovePoints(this.getMovementPoints_BaseValue(nCivID) + this.getMovementPoints_FromCivSize(nCivID) + this.getMovementPoints_FromTechnology(nCivID));
   }

   public final int getMovementPoints_BaseValue(int nCivID) {
      return 6
         + (int)(
            CFG.gameAges.getAge_StartingMovementPoints(Game_Calendar.CURRENT_AGEID)
               * this.modifierMovementPoints_CivID(nCivID)
               * (1.0F + CFG.game.getCiv(nCivID).getModifier_MovementPoints())
         );
   }

   public final int getMovementPoints_FromCivSize(int nCivID) {
      return (int)(
         CFG.game.getCiv(nCivID).getNumOfProvinces()
            * CFG.gameAges.getAge_MovementPointsModifier(Game_Calendar.CURRENT_AGEID)
            * Math.min(CFG.game.getCiv(nCivID).getTechnologyLevel() * 1.213854F, 1.0F)
            * this.modifierMovementPoints_CivID(nCivID)
            * (1.0F + CFG.game.getCiv(nCivID).getModifier_MovementPoints())
      );
   }

   public final int getMovementPoints_FromTechnology(int nCivID) {
      return (int)(
         CFG.gameAges.getAge_StartingMovementPoints(Game_Calendar.CURRENT_AGEID)
            * CFG.game.getCiv(nCivID).getTechnologyLevel()
            * 2.143798F
            * (1.0F + CFG.game.getCiv(nCivID).getModifier_MovementPoints())
      );
   }

   public final void updateCivsDiplomacyPoints_StartTheGame() {
      for (int i = 1; i < CFG.game.getCivsSize(); i++) {
         this.updateCivsDiplomacyPoints(i);
         CFG.game.getCiv(i).setDiplomacyPoints((int)Math.max(CFG.game.getCiv(i).getDiplomacyPoints() * 2.65F, 22.0F));
      }
   }

   public final void updateCivsDiplomacyPoints() {
      for (int i = 1; i < CFG.game.getCivsSize(); i++) {
         this.updateCivsDiplomacyPoints(i);
      }
   }

   public final void updateCivsDiplomacyPoints(int nCivID) {
      CFG.game.getCiv(nCivID).setDiplomacyPoints(CFG.game.getCiv(nCivID).getDiplomacyPoints() + this.getUpdateCivsDiplomacyPoints(nCivID));
   }

   public final int getUpdateCivsDiplomacyPoints(int nCivID) {
      return Math.max(
         this.getDiplomacyPoints_BaseValue(nCivID)
            + this.getDiplomacyPoints_FromEnemies(nCivID)
            + this.getDiplomacyPoints_FromRank(nCivID)
            + this.getDiplomacyPoints_FromTechnology(nCivID)
            - DiplomacyManager.getCostOfCurrentDiplomaticActionsUpdate(nCivID),
         0
      );
   }

   public final int getDiplomacyPoints_BaseValue(int nCivID) {
      return 1 + (int)(CFG.gameAges.getAge_StartingDiplomacyPoints(Game_Calendar.CURRENT_AGEID) * this.modifierMovementPoints_CivID(nCivID) * 0.375F);
   }

   public final int getDiplomacyPoints_FromTechnology(int nCivID) {
      return (int)(CFG.gameAges.getAge_StartingDiplomacyPoints(Game_Calendar.CURRENT_AGEID) * CFG.game.getCiv(nCivID).getTechnologyLevel() * 2.75F);
   }

   public final int getDiplomacyPoints_FromRank(int nCivID) {
      return (int)(
         CFG.gameAges.getAge_StartingDiplomacyPoints(Game_Calendar.CURRENT_AGEID)
            * (1.0F - (float)CFG.game.getCiv(nCivID).getRankPosition() / CFG.game.getCivsSize())
            * 0.775F
      );
   }

   public final int getDiplomacyPoints_FromEnemies(int nCivID) {
      return (int)(-6.0F + Math.min(CFG.oAI.MIN_NUM_OF_RIVALS, CFG.game.getCiv(nCivID).getHatedCivsSize()) * 6.0F);
   }

   public float modifierMovementPoints_CivID(int nCivID) {
      if (CFG.game.getCiv(nCivID).getControlledByPlayer()) {
         switch (CFG.DIFFICULTY) {
            case 0:
               return 1.35F;
            case 1:
               return 1.15F;
            case 2:
               return 0.95F;
            case 3:
               return 0.85F;
         }
      }

      switch (CFG.DIFFICULTY) {
         case 0:
            return 0.8F;
         case 1:
            return 0.95F;
         case 2:
            return 1.2F;
         case 3:
            return 1.45F;
         default:
            return 1.0F;
      }
   }

   private final void rollDices() {
      this.diceAggressors = CFG.oR.nextInt(725) % 6 + 1;
      this.diceDefenders = CFG.oR.nextInt(600) % 6 + 1;
   }

   protected final void turnMoves() {
      if (this.currentMoveUnits != null && this.currentMoveUnits.getMoveUnitsSize() > 0) {
         this.turnMoves_MoveCurrentArmy();
      } else {
         if (CFG.menuManager.getInGame_Report_Visible()) {
            CFG.menuManager.setInGame_Report_Visible(false);
         }

         for (int e = this.eRTO_START2; e < CFG.game.getRTO().getRTOSize(); this.eRTO_START2++) {
            this.turnMoves_UpdatePlayersFogOfWar(CFG.game.getRTO().getRTO(e));

            for (int i = 0; i < CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getMoveUnitsSize(); i++) {
               if (CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getMoveUnits(i).getNumOfUnits() > 39
                  && CFG.game.getProvince(CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getMoveUnits(i).getToProvinceID()).getCivID() > 0
                  && !CFG.game.getProvince(CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getMoveUnits(i).getFromProvinceID()).isOccupied()
                  && CFG.game.getProvince(CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getMoveUnits(i).getToProvinceID()).isOccupied()
                  && CFG.game
                     .getCivsAtWar(
                        CFG.game.getRTO().getRTO(e),
                        CFG.game.getProvince(CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getMoveUnits(i).getToProvinceID()).getCivID()
                     )) {
                  if (CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getMoveUnits(i).getNumOfUnits()
                     > CFG.game
                        .getProvince(CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getMoveUnits(i).getFromProvinceID())
                        .getArmyCivID(CFG.game.getRTO().getRTO(e))) {
                     CFG.game
                        .getCiv(CFG.game.getRTO().getRTO(e))
                        .getMoveUnits(i)
                        .setNumOfUnits(
                           CFG.game
                              .getProvince(CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getMoveUnits(i).getFromProvinceID())
                              .getArmyCivID(CFG.game.getRTO().getRTO(e))
                        );
                  }

                  if (CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getMoveUnits(i).getNumOfUnits() <= 0) {
                     CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).removeMove(i--);
                  } else {
                     this.currentMoveUnits = new MoveUnits_TurnData(CFG.game.getRTO().getRTO(e));
                     this.currentMoveUnits.addMoveUnits(CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getMoveUnits(i), CFG.game.getRTO().getRTO(e));
                     CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).removeMove(i--);
                     if (!CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getSeaProvince()) {
                        for (int k2 = i + 1; k2 < CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getMoveUnitsSize(); k2++) {
                           if (this.currentMoveUnits.getMoveUnits(0).getToProvinceID()
                              == CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getMoveUnits(k2).getToProvinceID()) {
                              if (CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getMoveUnits(k2).getNumOfUnits()
                                 > CFG.game
                                    .getProvince(CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getMoveUnits(k2).getFromProvinceID())
                                    .getArmyCivID(CFG.game.getRTO().getRTO(e))) {
                                 CFG.game
                                    .getCiv(CFG.game.getRTO().getRTO(e))
                                    .getMoveUnits(k2)
                                    .setNumOfUnits(
                                       CFG.game
                                          .getProvince(CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getMoveUnits(k2).getFromProvinceID())
                                          .getArmyCivID(CFG.game.getRTO().getRTO(e))
                                    );
                              }

                              if (CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getMoveUnits(k2).getNumOfUnits() > 0) {
                                 this.currentMoveUnits.addMoveUnits(CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getMoveUnits(k2), CFG.game.getRTO().getRTO(e));
                                 CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).removeMove(k2--);
                              }
                           }
                        }

                        if (CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getAllianceID() > 0) {
                           for (int a = 0; a < CFG.game.getAlliance(CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getAllianceID()).getCivilizationsSize(); a++) {
                              if (CFG.game.getAlliance(CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getAllianceID()).getCivilization(a)
                                 != CFG.game.getRTO().getRTO(e)) {
                                 for (int k = 0;
                                    k
                                       < CFG.game
                                          .getCiv(CFG.game.getAlliance(CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getAllianceID()).getCivilization(a))
                                          .getMoveUnitsSize();
                                    k++
                                 ) {
                                    if (this.currentMoveUnits.getMoveUnits(0).getToProvinceID()
                                       == CFG.game
                                          .getCiv(CFG.game.getAlliance(CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getAllianceID()).getCivilization(a))
                                          .getMoveUnits(k)
                                          .getToProvinceID()) {
                                       if (CFG.game
                                             .getCiv(CFG.game.getAlliance(CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getAllianceID()).getCivilization(a))
                                             .getMoveUnits(k)
                                             .getNumOfUnits()
                                          > CFG.game
                                             .getProvince(
                                                CFG.game
                                                   .getCiv(
                                                      CFG.game.getAlliance(CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getAllianceID()).getCivilization(a)
                                                   )
                                                   .getMoveUnits(k)
                                                   .getFromProvinceID()
                                             )
                                             .getArmyCivID(
                                                CFG.game.getAlliance(CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getAllianceID()).getCivilization(a)
                                             )) {
                                          CFG.game
                                             .getCiv(CFG.game.getAlliance(CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getAllianceID()).getCivilization(a))
                                             .getMoveUnits(k)
                                             .setNumOfUnits(
                                                CFG.game
                                                   .getProvince(
                                                      CFG.game
                                                         .getCiv(
                                                            CFG.game
                                                               .getAlliance(CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getAllianceID())
                                                               .getCivilization(a)
                                                         )
                                                         .getMoveUnits(k)
                                                         .getFromProvinceID()
                                                   )
                                                   .getArmyCivID(
                                                      CFG.game.getAlliance(CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getAllianceID()).getCivilization(a)
                                                   )
                                             );
                                       }

                                       if (CFG.game
                                             .getCiv(CFG.game.getAlliance(CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getAllianceID()).getCivilization(a))
                                             .getMoveUnits(k)
                                             .getNumOfUnits()
                                          > 0) {
                                          this.currentMoveUnits
                                             .addMoveUnits(
                                                CFG.game
                                                   .getCiv(
                                                      CFG.game.getAlliance(CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getAllianceID()).getCivilization(a)
                                                   )
                                                   .getMoveUnits(k),
                                                CFG.game.getAlliance(CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getAllianceID()).getCivilization(a)
                                             );
                                          CFG.game
                                             .getCiv(CFG.game.getAlliance(CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getAllianceID()).getCivilization(a))
                                             .removeMove(k--);
                                       }
                                    }
                                 }
                              }
                           }
                        }

                        for (int ax = 1; ax < CFG.game.getCivsSize(); ax++) {
                           if (ax != CFG.game.getRTO().getRTO(e)
                              && (
                                 CFG.game.getCiv(ax).getPuppetOfCivID() == CFG.game.getRTO().getRTO(e)
                                    || ax == CFG.game.getCiv(CFG.game.getRTO().getRTO(e)).getPuppetOfCivID()
                              )) {
                              for (int kx = 0; kx < CFG.game.getCiv(ax).getMoveUnitsSize(); kx++) {
                                 if (this.currentMoveUnits.getMoveUnits(0).getToProvinceID() == CFG.game.getCiv(ax).getMoveUnits(kx).getToProvinceID()) {
                                    if (CFG.game.getCiv(ax).getMoveUnits(kx).getNumOfUnits()
                                       > CFG.game.getProvince(CFG.game.getCiv(ax).getMoveUnits(kx).getFromProvinceID()).getArmyCivID(ax)) {
                                       CFG.game
                                          .getCiv(ax)
                                          .getMoveUnits(kx)
                                          .setNumOfUnits(CFG.game.getProvince(CFG.game.getCiv(ax).getMoveUnits(kx).getFromProvinceID()).getArmyCivID(ax));
                                    }

                                    if (CFG.game.getCiv(ax).getMoveUnits(kx).getNumOfUnits() > 0) {
                                       this.currentMoveUnits.addMoveUnits(CFG.game.getCiv(ax).getMoveUnits(kx), ax);
                                       CFG.game.getCiv(ax).removeMove(kx--);
                                    }
                                 }
                              }
                           }
                        }
                     }

                     int attackingArmy = 0;

                     for (int o = 0; o < this.currentMoveUnits.getMoveUnitsSize(); o++) {
                        attackingArmy += this.currentMoveUnits.getMoveUnits(o).getNumOfUnits();
                     }

                     Gdx.app.log("AoC", "attackingArmy: " + attackingArmy);
                     Gdx.app.log("AoC", "MIN_ARMY_TO_ATTACK: 10");
                     Gdx.app
                        .log(
                           "AoC",
                           "atWar: "
                              + CFG.game
                                 .getCivsAtWar(
                                    CFG.game.getRTO().getRTO(e), CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID()
                                 )
                        );
                     if (attackingArmy < 100
                        && CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID() > 0
                        && CFG.game
                           .getCivsAtWar(CFG.game.getRTO().getRTO(e), CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID())
                        )
                      {
                        Gdx.app.log("AoC", "attackingArmy: remove");
                        this.currentMoveUnits = null;
                     } else {
                        for (int var10 = 0; var10 < this.currentMoveUnits.getMoveUnitsSize(); var10++) {
                           this.currentMoveUnits.getMoveUnits(var10).getMoveUnitsLine().updateMoveTime();
                        }

                        this.rollDices();
                        if ((
                              CFG.SHOW_ALL_MOVES
                                 || CFG.game.getCiv(this.currentMoveUnits.getCivID(0)).getControlledByPlayer()
                                 || CFG.game
                                    .getCiv(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID())
                                    .getControlledByPlayer()
                           )
                           && CFG.settingsManager.SHOW_BATTLE_RESULTS) {
                           if (!CFG.SHOW_ONLY_COMBAT_MOVES) {
                              CFG.map.getMapCoordinates().centerToProvinceID(this.currentMoveUnits.getMoveUnits(0).getToProvinceID());
                              if (CFG.viewsManager.getActiveViewID() >= 0) {
                                 CFG.viewsManager.disableAllViews();
                              }

                              return;
                           }

                           if ((
                                 CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getSeaProvince()
                                       && CFG.game
                                          .getSeaProvinceAttack(this.currentMoveUnits.getCivID(0), this.currentMoveUnits.getMoveUnits(0).getToProvinceID())
                                    || !CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getSeaProvince()
                                       && this.turnMoves_IsACombatMove(
                                          this.currentMoveUnits.getCivID(0), this.currentMoveUnits.getMoveUnits(0).getToProvinceID()
                                       )
                              )
                              && (!RTS.isEnabled() || !RTS.PAUSE && RTS.showReport() || RTS.PAUSE)) {
                              this.SHOW_REPORT = true;
                              this.iPlayerAttack_ShowArmyInProvinceID = this.currentMoveUnits.getMoveUnits(0).getToProvinceID();
                              CFG.game.getPlayer(CFG.PLAYER_TURNID).setFogOfWar(this.currentMoveUnits.getMoveUnits(0).getToProvinceID(), true);
                              CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).updateDrawArmy();
                              this.diceDefendersCivID = CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID();
                              this.diceAggressorsCivID = CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getFromProvinceID()).getCivID();
                              CFG.menuManager
                                 .setVisible_InGame_Dices(!CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getSeaProvince());
                              CFG.map.getMapCoordinates().centerToProvinceID(this.currentMoveUnits.getMoveUnits(0).getToProvinceID());
                              if (CFG.viewsManager.getActiveViewID() >= 0) {
                                 CFG.viewsManager.disableAllViews();
                              }

                              return;
                           }

                           this.turnMoves_MoveCurrentArmy();
                        } else {
                           this.turnMoves_MoveCurrentArmy();
                        }
                     }
                  }
               }
            }

            e++;
         }

         for (int var29 = this.eRTO_START; var29 < CFG.game.getRTO().getRTOSize(); this.eRTO_START++) {
            this.turnMoves_UpdatePlayersFogOfWar(CFG.game.getRTO().getRTO(var29));

            for (int ix = 0; ix < CFG.game.getCiv(CFG.game.getRTO().getRTO(var29)).getMoveUnitsSize(); ix++) {
               if (CFG.game.getProvince(CFG.game.getCiv(CFG.game.getRTO().getRTO(var29)).getMoveUnits(ix).getToProvinceID()).getCivID() == 0
                  || CFG.game
                     .getCivsAtWar(
                        CFG.game.getRTO().getRTO(var29),
                        CFG.game.getProvince(CFG.game.getCiv(CFG.game.getRTO().getRTO(var29)).getMoveUnits(ix).getToProvinceID()).getCivID()
                     )
                  || CFG.game
                        .getMilitaryAccess(
                           CFG.game.getRTO().getRTO(var29),
                           CFG.game.getProvince(CFG.game.getCiv(CFG.game.getRTO().getRTO(var29)).getMoveUnits(ix).getToProvinceID()).getCivID()
                        )
                     > 0
                  || CFG.game.getProvince(CFG.game.getCiv(CFG.game.getRTO().getRTO(var29)).getMoveUnits(ix).getToProvinceID()).getCivID()
                     == CFG.game.getRTO().getRTO(var29)
                  || CFG.game
                        .getCiv(CFG.game.getProvince(CFG.game.getCiv(CFG.game.getRTO().getRTO(var29)).getMoveUnits(ix).getToProvinceID()).getCivID())
                        .getPuppetOfCivID()
                     == CFG.game.getRTO().getRTO(var29)
                  || CFG.game.getCiv(CFG.game.getRTO().getRTO(var29)).getPuppetOfCivID()
                     == CFG.game.getProvince(CFG.game.getCiv(CFG.game.getRTO().getRTO(var29)).getMoveUnits(ix).getToProvinceID()).getCivID()
                  || CFG.game.getCiv(CFG.game.getRTO().getRTO(var29)).getAllianceID() > 0
                     && CFG.game.getCiv(CFG.game.getRTO().getRTO(var29)).getAllianceID()
                        == CFG.game
                           .getCiv(CFG.game.getProvince(CFG.game.getCiv(CFG.game.getRTO().getRTO(var29)).getMoveUnits(ix).getToProvinceID()).getCivID())
                           .getAllianceID()) {
                  if (CFG.game.getCiv(CFG.game.getRTO().getRTO(var29)).getMoveUnits(ix).getNumOfUnits()
                     > CFG.game
                        .getProvince(CFG.game.getCiv(CFG.game.getRTO().getRTO(var29)).getMoveUnits(ix).getFromProvinceID())
                        .getArmyCivID(CFG.game.getRTO().getRTO(var29))) {
                     CFG.game
                        .getCiv(CFG.game.getRTO().getRTO(var29))
                        .getMoveUnits(ix)
                        .setNumOfUnits(
                           CFG.game
                              .getProvince(CFG.game.getCiv(CFG.game.getRTO().getRTO(var29)).getMoveUnits(ix).getFromProvinceID())
                              .getArmyCivID(CFG.game.getRTO().getRTO(var29))
                        );
                  }

                  if (CFG.game.getCiv(CFG.game.getRTO().getRTO(var29)).getMoveUnits(ix).getNumOfUnits() <= 0) {
                     CFG.game.getCiv(CFG.game.getRTO().getRTO(var29)).removeMove(ix--);
                  } else {
                     this.currentMoveUnits = new MoveUnits_TurnData(CFG.game.getRTO().getRTO(var29));
                     this.currentMoveUnits.addMoveUnits(CFG.game.getCiv(CFG.game.getRTO().getRTO(var29)).getMoveUnits(ix), CFG.game.getRTO().getRTO(var29));
                     CFG.game.getCiv(CFG.game.getRTO().getRTO(var29)).removeMove(ix--);
                     if (!CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getSeaProvince()) {
                        for (int k2x = ix + 1; k2x < CFG.game.getCiv(CFG.game.getRTO().getRTO(var29)).getMoveUnitsSize(); k2x++) {
                           if (this.currentMoveUnits.getMoveUnits(0).getToProvinceID()
                              == CFG.game.getCiv(CFG.game.getRTO().getRTO(var29)).getMoveUnits(k2x).getToProvinceID()) {
                              if (CFG.game.getCiv(CFG.game.getRTO().getRTO(var29)).getMoveUnits(k2x).getNumOfUnits()
                                 > CFG.game
                                    .getProvince(CFG.game.getCiv(CFG.game.getRTO().getRTO(var29)).getMoveUnits(k2x).getFromProvinceID())
                                    .getArmyCivID(CFG.game.getRTO().getRTO(var29))) {
                                 CFG.game
                                    .getCiv(CFG.game.getRTO().getRTO(var29))
                                    .getMoveUnits(k2x)
                                    .setNumOfUnits(
                                       CFG.game
                                          .getProvince(CFG.game.getCiv(CFG.game.getRTO().getRTO(var29)).getMoveUnits(k2x).getFromProvinceID())
                                          .getArmyCivID(CFG.game.getRTO().getRTO(var29))
                                    );
                              }

                              if (CFG.game.getCiv(CFG.game.getRTO().getRTO(var29)).getMoveUnits(k2x).getNumOfUnits() > 0) {
                                 this.currentMoveUnits
                                    .addMoveUnits(CFG.game.getCiv(CFG.game.getRTO().getRTO(var29)).getMoveUnits(k2x), CFG.game.getRTO().getRTO(var29));
                                 CFG.game.getCiv(CFG.game.getRTO().getRTO(var29)).removeMove(k2x--);
                              }
                           }
                        }

                        if (CFG.game.getCiv(CFG.game.getRTO().getRTO(var29)).getAllianceID() > 0) {
                           for (int axx = 0;
                              axx < CFG.game.getAlliance(CFG.game.getCiv(CFG.game.getRTO().getRTO(var29)).getAllianceID()).getCivilizationsSize();
                              axx++
                           ) {
                              if (CFG.game.getAlliance(CFG.game.getCiv(CFG.game.getRTO().getRTO(var29)).getAllianceID()).getCivilization(axx)
                                 != CFG.game.getRTO().getRTO(var29)) {
                                 for (int kxx = 0;
                                    kxx
                                       < CFG.game
                                          .getCiv(CFG.game.getAlliance(CFG.game.getCiv(CFG.game.getRTO().getRTO(var29)).getAllianceID()).getCivilization(axx))
                                          .getMoveUnitsSize();
                                    kxx++
                                 ) {
                                    if (this.currentMoveUnits.getMoveUnits(0).getToProvinceID()
                                       == CFG.game
                                          .getCiv(CFG.game.getAlliance(CFG.game.getCiv(CFG.game.getRTO().getRTO(var29)).getAllianceID()).getCivilization(axx))
                                          .getMoveUnits(kxx)
                                          .getToProvinceID()) {
                                       if (CFG.game
                                             .getCiv(
                                                CFG.game.getAlliance(CFG.game.getCiv(CFG.game.getRTO().getRTO(var29)).getAllianceID()).getCivilization(axx)
                                             )
                                             .getMoveUnits(kxx)
                                             .getNumOfUnits()
                                          > CFG.game
                                             .getProvince(
                                                CFG.game
                                                   .getCiv(
                                                      CFG.game
                                                         .getAlliance(CFG.game.getCiv(CFG.game.getRTO().getRTO(var29)).getAllianceID())
                                                         .getCivilization(axx)
                                                   )
                                                   .getMoveUnits(kxx)
                                                   .getFromProvinceID()
                                             )
                                             .getArmyCivID(
                                                CFG.game.getAlliance(CFG.game.getCiv(CFG.game.getRTO().getRTO(var29)).getAllianceID()).getCivilization(axx)
                                             )) {
                                          CFG.game
                                             .getCiv(
                                                CFG.game.getAlliance(CFG.game.getCiv(CFG.game.getRTO().getRTO(var29)).getAllianceID()).getCivilization(axx)
                                             )
                                             .getMoveUnits(kxx)
                                             .setNumOfUnits(
                                                CFG.game
                                                   .getProvince(
                                                      CFG.game
                                                         .getCiv(
                                                            CFG.game
                                                               .getAlliance(CFG.game.getCiv(CFG.game.getRTO().getRTO(var29)).getAllianceID())
                                                               .getCivilization(axx)
                                                         )
                                                         .getMoveUnits(kxx)
                                                         .getFromProvinceID()
                                                   )
                                                   .getArmyCivID(
                                                      CFG.game
                                                         .getAlliance(CFG.game.getCiv(CFG.game.getRTO().getRTO(var29)).getAllianceID())
                                                         .getCivilization(axx)
                                                   )
                                             );
                                       }

                                       if (CFG.game
                                             .getCiv(
                                                CFG.game.getAlliance(CFG.game.getCiv(CFG.game.getRTO().getRTO(var29)).getAllianceID()).getCivilization(axx)
                                             )
                                             .getMoveUnits(kxx)
                                             .getNumOfUnits()
                                          > 0) {
                                          this.currentMoveUnits
                                             .addMoveUnits(
                                                CFG.game
                                                   .getCiv(
                                                      CFG.game
                                                         .getAlliance(CFG.game.getCiv(CFG.game.getRTO().getRTO(var29)).getAllianceID())
                                                         .getCivilization(axx)
                                                   )
                                                   .getMoveUnits(kxx),
                                                CFG.game.getAlliance(CFG.game.getCiv(CFG.game.getRTO().getRTO(var29)).getAllianceID()).getCivilization(axx)
                                             );
                                          CFG.game
                                             .getCiv(
                                                CFG.game.getAlliance(CFG.game.getCiv(CFG.game.getRTO().getRTO(var29)).getAllianceID()).getCivilization(axx)
                                             )
                                             .removeMove(kxx--);
                                       }
                                    }
                                 }
                              }
                           }
                        }

                        for (int axxx = 1; axxx < CFG.game.getCivsSize(); axxx++) {
                           if (axxx != CFG.game.getRTO().getRTO(var29)
                              && (
                                 CFG.game.getCiv(axxx).getPuppetOfCivID() == CFG.game.getRTO().getRTO(var29)
                                    || axxx == CFG.game.getCiv(CFG.game.getRTO().getRTO(var29)).getPuppetOfCivID()
                              )) {
                              for (int kxxx = 0; kxxx < CFG.game.getCiv(axxx).getMoveUnitsSize(); kxxx++) {
                                 if (this.currentMoveUnits.getMoveUnits(0).getToProvinceID() == CFG.game.getCiv(axxx).getMoveUnits(kxxx).getToProvinceID()) {
                                    if (CFG.game.getCiv(axxx).getMoveUnits(kxxx).getNumOfUnits()
                                       > CFG.game.getProvince(CFG.game.getCiv(axxx).getMoveUnits(kxxx).getFromProvinceID()).getArmyCivID(axxx)) {
                                       CFG.game
                                          .getCiv(axxx)
                                          .getMoveUnits(kxxx)
                                          .setNumOfUnits(CFG.game.getProvince(CFG.game.getCiv(axxx).getMoveUnits(kxxx).getFromProvinceID()).getArmyCivID(axxx));
                                    }

                                    if (CFG.game.getCiv(axxx).getMoveUnits(kxxx).getNumOfUnits() > 0) {
                                       this.currentMoveUnits.addMoveUnits(CFG.game.getCiv(axxx).getMoveUnits(kxxx), axxx);
                                       CFG.game.getCiv(axxx).removeMove(kxxx--);
                                    }
                                 }
                              }
                           }
                        }
                     }

                     int attackingArmy = 0;

                     for (int o = 0; o < this.currentMoveUnits.getMoveUnitsSize(); o++) {
                        attackingArmy += this.currentMoveUnits.getMoveUnits(o).getNumOfUnits();
                     }

                     Gdx.app.log("AoC", "attackingArmy: " + attackingArmy);
                     Gdx.app.log("AoC", "MIN_ARMY_TO_ATTACK: 10");
                     Gdx.app
                        .log(
                           "AoC",
                           "atWar: "
                              + CFG.game
                                 .getCivsAtWar(
                                    CFG.game.getRTO().getRTO(var29), CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID()
                                 )
                        );
                     if (attackingArmy < 100
                        && CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID() > 0
                        && CFG.game
                           .getCivsAtWar(
                              CFG.game.getRTO().getRTO(var29), CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID()
                           )) {
                        Gdx.app.log("AoC", "attackingArmy: remove");
                        this.currentMoveUnits = null;
                     } else {
                        for (int var12 = 0; var12 < this.currentMoveUnits.getMoveUnitsSize(); var12++) {
                           this.currentMoveUnits.getMoveUnits(var12).getMoveUnitsLine().updateMoveTime();
                        }

                        this.rollDices();
                        if ((
                              CFG.SHOW_ALL_MOVES
                                 || this.currentMoveUnits.isPlayerMoving()
                                 || CFG.game
                                    .getCiv(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID())
                                    .getControlledByPlayer()
                           )
                           && CFG.settingsManager.SHOW_BATTLE_RESULTS) {
                           if (!CFG.SHOW_ONLY_COMBAT_MOVES) {
                              CFG.map.getMapCoordinates().centerToProvinceID(this.currentMoveUnits.getMoveUnits(0).getToProvinceID());
                              if (CFG.viewsManager.getActiveViewID() >= 0) {
                                 CFG.viewsManager.disableAllViews();
                              }

                              return;
                           }

                           if ((
                                 CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getSeaProvince()
                                       && CFG.game
                                          .getSeaProvinceAttack(this.currentMoveUnits.getCivID(0), this.currentMoveUnits.getMoveUnits(0).getToProvinceID())
                                    || !CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getSeaProvince()
                                       && this.turnMoves_IsACombatMove(
                                          this.currentMoveUnits.getCivID(0), this.currentMoveUnits.getMoveUnits(0).getToProvinceID()
                                       )
                              )
                              && (!RTS.isEnabled() || !RTS.PAUSE && RTS.showReport() || RTS.PAUSE)) {
                              this.SHOW_REPORT = true;
                              this.iPlayerAttack_ShowArmyInProvinceID = this.currentMoveUnits.getMoveUnits(0).getToProvinceID();
                              CFG.game.getPlayer(CFG.PLAYER_TURNID).setFogOfWar(this.currentMoveUnits.getMoveUnits(0).getToProvinceID(), true);
                              CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).updateDrawArmy();
                              this.diceDefendersCivID = CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID();
                              this.diceAggressorsCivID = CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getFromProvinceID()).getCivID();
                              CFG.menuManager
                                 .setVisible_InGame_Dices(!CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getSeaProvince());
                              CFG.map.getMapCoordinates().centerToProvinceID(this.currentMoveUnits.getMoveUnits(0).getToProvinceID());
                              if (CFG.viewsManager.getActiveViewID() >= 0) {
                                 CFG.viewsManager.disableAllViews();
                              }

                              return;
                           }

                           this.turnMoves_MoveCurrentArmy();
                        } else {
                           this.turnMoves_MoveCurrentArmy();
                        }
                     }
                  }
               } else {
                  CFG.game.getCiv(CFG.game.getRTO().getRTO(var29)).removeMove(ix--);
               }
            }

            for (int var23 = 0; var23 < CFG.game.getCiv(CFG.game.getRTO().getRTO(var29)).getMoveUnitsPlunderSize(); var23++) {
               if (CFG.game.getCiv(CFG.game.getRTO().getRTO(var29)).getMoveUnits_Plunder(var23).getNumOfUnits()
                  > CFG.game
                     .getProvince(CFG.game.getCiv(CFG.game.getRTO().getRTO(var29)).getMoveUnits_Plunder(var23).getFromProvinceID())
                     .getArmyCivID(CFG.game.getRTO().getRTO(var29))) {
                  CFG.game
                     .getCiv(CFG.game.getRTO().getRTO(var29))
                     .getMoveUnits_Plunder(var23)
                     .setNumOfUnits(
                        CFG.game
                           .getProvince(CFG.game.getCiv(CFG.game.getRTO().getRTO(var29)).getMoveUnits_Plunder(var23).getFromProvinceID())
                           .getArmyCivID(CFG.game.getRTO().getRTO(var29))
                     );
               }

               if (CFG.game.getCiv(CFG.game.getRTO().getRTO(var29)).getMoveUnits_Plunder(var23).getNumOfUnits() > 0) {
                  DiplomacyManager.plunder(
                     CFG.game.getRTO().getRTO(var29),
                     CFG.game.getCiv(CFG.game.getRTO().getRTO(var29)).getMoveUnits_Plunder(var23).getFromProvinceID(),
                     CFG.game.getCiv(CFG.game.getRTO().getRTO(var29)).getMoveUnits_Plunder(var23).getNumOfUnits()
                  );
               }

               CFG.game.getCiv(CFG.game.getRTO().getRTO(var29)).removePlunder(var23--);
            }

            for (int var25 = 0; var25 < CFG.game.getCiv(CFG.game.getRTO().getRTO(var29)).getMoveUnitsGenocideSize(); var25++) {
               if (CFG.game.getCiv(CFG.game.getRTO().getRTO(var29)).getMoveUnits_Genocide(var25).getNumOfUnits()
                  > CFG.game
                     .getProvince(CFG.game.getCiv(CFG.game.getRTO().getRTO(var29)).getMoveUnits_Genocide(var25).getFromProvinceID())
                     .getArmyCivID(CFG.game.getRTO().getRTO(var29))) {
                  CFG.game
                     .getCiv(CFG.game.getRTO().getRTO(var29))
                     .getMoveUnits_Genocide(var25)
                     .setNumOfUnits(
                        CFG.game
                           .getProvince(CFG.game.getCiv(CFG.game.getRTO().getRTO(var29)).getMoveUnits_Genocide(var25).getFromProvinceID())
                           .getArmyCivID(CFG.game.getRTO().getRTO(var29))
                     );
               }

               if (CFG.game.getCiv(CFG.game.getRTO().getRTO(var29)).getMoveUnits_Genocide(var25).getNumOfUnits() > 0) {
                  DiplomacyManager.genocide(
                     CFG.game.getRTO().getRTO(var29),
                     CFG.game.getCiv(CFG.game.getRTO().getRTO(var29)).getMoveUnits_Genocide(var25).getFromProvinceID(),
                     CFG.game.getCiv(CFG.game.getRTO().getRTO(var29)).getMoveUnits_Genocide(var25).getNumOfUnits(),
                     CFG.game.getCiv(CFG.game.getRTO().getRTO(var29)).getMoveUnits_Genocide(var25).getNations()
                  );
               }

               CFG.game.getCiv(CFG.game.getRTO().getRTO(var29)).removeGenocide(var25--);
            }

            for (int var27 = 0; var27 < CFG.game.getCiv(CFG.game.getRTO().getRTO(var29)).getMigrateSize(); var27++) {
               this.migrateFromTo(
                  CFG.game.getRTO().getRTO(var29),
                  CFG.game.getCiv(CFG.game.getRTO().getRTO(var29)).getMigrate(var27).getFromProvinceID(),
                  CFG.game.getCiv(CFG.game.getRTO().getRTO(var29)).getMigrate(var27).getToProvinceID()
               );
               CFG.game.getCiv(CFG.game.getRTO().getRTO(var29)).removeMigrate(var27--);
            }

            var29++;
         }

         CFG.PROVINCE_BORDER_ANIMATION_TIME.clear();
         this.currentMoveUnits = null;
         this.diceDefenders = 1;
         this.diceAggressors = 1;
         Game_Calendar.TURN_ID++;
         CFG.gameAction.updateInGame_Date();

         try {
            for (int i2 = 1; i2 < CFG.game.getCivsSize(); i2++) {
               CFG.game.getCiv(i2).clearMoveUnits();
               CFG.game.getCiv(i2).clearMoveUnits_Plunder();
            }
         } catch (IndexOutOfBoundsException var9) {
         }

         this.startNewTurn();
      }
   }

   public final void updateRelations() {
      ArrayList<Integer> tempCivs = new ArrayList<>();

      for (int i = 1; i < CFG.game.getCivsSize(); i++) {
         if (CFG.game.getCiv(i).getNumOfProvinces() > 0) {
            tempCivs.add(i);
         }
      }

      int iSize = tempCivs.size();

      for (int var5 = 0; var5 < iSize - 1; var5++) {
         for (int j = var5 + 1; j < iSize; j++) {
            if (CFG.game.getCivRelation_OfCivB(var5, j) > 30.0F) {
               CFG.game.setCivRelation_OfCivB(var5, j, CFG.game.getCivRelation_OfCivB(var5, j) - 0.295F);
            } else if (CFG.game.getCivRelation_OfCivB(var5, j) < -20.0F && !CFG.game.getCivsAtWar(var5, j)) {
               CFG.game.setCivRelation_OfCivB(var5, j, CFG.game.getCivRelation_OfCivB(var5, j) + 0.0145F);
            }

            if (CFG.game.getCivRelation_OfCivB(j, var5) > 30.0F) {
               CFG.game.setCivRelation_OfCivB(j, var5, CFG.game.getCivRelation_OfCivB(j, var5) - 0.295F);
            } else if (CFG.game.getCivRelation_OfCivB(j, var5) < -20.0F && !CFG.game.getCivsAtWar(j, var5)) {
               CFG.game.setCivRelation_OfCivB(j, var5, CFG.game.getCivRelation_OfCivB(j, var5) + 0.045F);
            }
         }
      }

      tempCivs.clear();
      ArrayList<Integer> var6 = null;
   }

   public final boolean isEmperorInTheGame() {
      try {
         return CFG.game.getCiv(CFG.holyRomanEmpire_Manager.getHRE().getEmperor()).getNumOfProvinces() > 0
            && CFG.game.getCiv(CFG.holyRomanEmpire_Manager.getHRE().getEmperor()).getPuppetOfCivID() == CFG.holyRomanEmpire_Manager.getHRE().getEmperor();
      } catch (IndexOutOfBoundsException var2) {
      } catch (NullPointerException var3) {
      }

      return true;
   }

   public final void updateHRE_Elections() {
      try {
         CFG.holyRomanEmpire_Manager.getHRE().setNextElectionsIn(CFG.holyRomanEmpire_Manager.getHRE().getNextElectionsIn() - 1);
         if (CFG.holyRomanEmpire_Manager.getHRE().getNextElectionsIn() > 0 && this.isEmperorInTheGame()) {
            if (CFG.holyRomanEmpire_Manager.getHRE().getNextElectionsIn() == 1) {
               for (int i = 0; i < CFG.holyRomanEmpire_Manager.getHRE().getElectorsSize(); i++) {
                  if (CFG.game
                     .getCiv(CFG.holyRomanEmpire_Manager.getHRE().getPrince(CFG.holyRomanEmpire_Manager.getHRE().getElector(i)))
                     .getControlledByPlayer()) {
                     CFG.game
                        .getCiv(CFG.holyRomanEmpire_Manager.getHRE().getPrince(CFG.holyRomanEmpire_Manager.getHRE().getElector(i)))
                        .getCivilization_Diplomacy_GameData()
                        .messageBox
                        .addMessage(new Message_HRE_ElectionsInNextTurn(CFG.holyRomanEmpire_Manager.getHRE().getEmperor()));
                  }
               }

               this.updateHRE_VotesFor();
            } else if (Game_Calendar.TURN_ID % 6 == 0) {
               this.updateHRE_VotesFor();
            }
         } else {
            ArrayList<Integer> lNumOfVotes = new ArrayList<>();

            for (int i2 = 0; i2 < CFG.holyRomanEmpire_Manager.getHRE().getPrincesSize(); i2++) {
               lNumOfVotes.add(0);
            }

            for (int var15 = 0; var15 < CFG.holyRomanEmpire_Manager.getHRE().getElectorsSize(); var15++) {
               for (int j = 0; j < CFG.holyRomanEmpire_Manager.getHRE().getPrincesSize(); j++) {
                  if (CFG.holyRomanEmpire_Manager.getHRE().getPrince(j) == CFG.holyRomanEmpire_Manager.getHRE().lVotesFor.get(var15)) {
                     lNumOfVotes.set(j, lNumOfVotes.get(j) + 1);
                     break;
                  }
               }
            }

            int maxVotes = 0;

            for (int i3 = 0; i3 < lNumOfVotes.size(); i3++) {
               if (lNumOfVotes.get(i3) > maxVotes) {
                  maxVotes = lNumOfVotes.get(i3);
               }
            }

            ArrayList<Integer> nCivsWithMaxVotes = new ArrayList<>();

            for (int ix = 0; ix < lNumOfVotes.size(); ix++) {
               if (lNumOfVotes.get(ix) == maxVotes) {
                  nCivsWithMaxVotes.add(ix);
               }
            }

            if (nCivsWithMaxVotes.size() > 0) {
               boolean newEmperorID = false;
               int oldEmperorID = CFG.holyRomanEmpire_Manager.getHRE().getEmperor();
               boolean wasElector = false;
               if (nCivsWithMaxVotes.size() == 1) {
                  wasElector = CFG.holyRomanEmpire_Manager.getHRE().getIsElector(CFG.holyRomanEmpire_Manager.getHRE().getPrince(nCivsWithMaxVotes.get(0)));
                  CFG.holyRomanEmpire_Manager.getHRE().setEmperor(CFG.holyRomanEmpire_Manager.getHRE().getPrince(nCivsWithMaxVotes.get(0)));
               } else {
                  boolean emperorVoted = false;

                  for (int i4 = 0; i4 < nCivsWithMaxVotes.size(); i4++) {
                     if (CFG.holyRomanEmpire_Manager.getHRE().getEmperor() == CFG.holyRomanEmpire_Manager.getHRE().getPrince(nCivsWithMaxVotes.get(i4))) {
                        emperorVoted = true;
                        break;
                     }
                  }

                  if (!emperorVoted) {
                     int tBest = 0;

                     for (int i5 = tBest + 1; i5 < nCivsWithMaxVotes.size(); i5++) {
                        if (CFG.game.getCiv(CFG.holyRomanEmpire_Manager.getHRE().getPrince(nCivsWithMaxVotes.get(tBest))).countPopulation()
                           < CFG.game.getCiv(CFG.holyRomanEmpire_Manager.getHRE().getPrince(nCivsWithMaxVotes.get(i5))).countPopulation()) {
                           tBest = i5;
                        }
                     }

                     wasElector = CFG.holyRomanEmpire_Manager
                        .getHRE()
                        .getIsElector(CFG.holyRomanEmpire_Manager.getHRE().getPrince(nCivsWithMaxVotes.get(tBest)));
                     CFG.holyRomanEmpire_Manager.getHRE().setEmperor(CFG.holyRomanEmpire_Manager.getHRE().getPrince(nCivsWithMaxVotes.get(tBest)));
                  }
               }

               if (CFG.holyRomanEmpire_Manager.getHRE().getEmperor() != oldEmperorID && wasElector) {
                  CFG.holyRomanEmpire_Manager.getHRE().addElector(oldEmperorID);
               }
            }

            for (int var13 = 0; var13 < CFG.holyRomanEmpire_Manager.getHRE().getPrincesSize(); var13++) {
               if (CFG.game.getCiv(CFG.holyRomanEmpire_Manager.getHRE().getPrince(var13)).getControlledByPlayer()) {
                  CFG.game
                     .getCiv(CFG.holyRomanEmpire_Manager.getHRE().getPrince(var13))
                     .getCivilization_Diplomacy_GameData()
                     .messageBox
                     .addMessage(new Message_HRE_Elections_NewEmperor(CFG.holyRomanEmpire_Manager.getHRE().getEmperor()));
               }
            }

            CFG.holyRomanEmpire_Manager.getHRE().randomNextElections();
            this.updateHRE_VotesFor();
         }
      } catch (IndexOutOfBoundsException var12) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var12);
         }
      }
   }

   public final void updateHRE_VotesFor() {
      boolean rebuildVotes = CFG.holyRomanEmpire_Manager.getHRE().lVotesFor == null
         || CFG.holyRomanEmpire_Manager.getHRE().lVotesFor.size() != CFG.holyRomanEmpire_Manager.getHRE().getElectorsSize();

      for (int i = CFG.holyRomanEmpire_Manager.getHRE().getElectorsSize() - 1; i >= 0; i--) {
         if (CFG.game.getCiv(CFG.holyRomanEmpire_Manager.getHRE().getPrince(CFG.holyRomanEmpire_Manager.getHRE().getElector(i))).getNumOfProvinces() == 0) {
            CFG.holyRomanEmpire_Manager
               .getHRE()
               .removeElector(CFG.holyRomanEmpire_Manager.getHRE().getPrince(CFG.holyRomanEmpire_Manager.getHRE().getElector(i)));
            CFG.holyRomanEmpire_Manager.getHRE().addStrongestPrinceAsElector();
            rebuildVotes = true;
         }
      }

      if (rebuildVotes) {
         CFG.holyRomanEmpire_Manager.getHRE().buildVotesFor();
      }

      int nMaxProvinces = 1;
      int nMaxScore = 1;

      for (int j = 0; j < CFG.holyRomanEmpire_Manager.getHRE().getPrincesSize(); j++) {
         if (nMaxProvinces < CFG.game.getCiv(CFG.holyRomanEmpire_Manager.getHRE().getPrince(j)).getNumOfProvinces()) {
            nMaxProvinces = CFG.game.getCiv(CFG.holyRomanEmpire_Manager.getHRE().getPrince(j)).getNumOfProvinces();
         }

         if (nMaxScore < CFG.game.getCiv(CFG.holyRomanEmpire_Manager.getHRE().getPrince(j)).getRankScore()) {
            nMaxScore = CFG.game.getCiv(CFG.holyRomanEmpire_Manager.getHRE().getPrince(j)).getRankScore();
         }
      }

      try {
         for (int ix = 0; ix < CFG.holyRomanEmpire_Manager.getHRE().getElectorsSize(); ix++) {
            if (!CFG.game.getCiv(CFG.holyRomanEmpire_Manager.getHRE().getPrince(CFG.holyRomanEmpire_Manager.getHRE().getElector(ix))).getControlledByPlayer()) {
               ArrayList<Float> tempScores = new ArrayList<>();

               for (int j = 0; j < CFG.holyRomanEmpire_Manager.getHRE().getPrincesSize(); j++) {
                  float nScore = 0.0F;
                  float var13;
                  float var14;
                  nScore = CFG.holyRomanEmpire_Manager.getHRE().getPrince(j)
                        == CFG.holyRomanEmpire_Manager.getHRE().getPrince(CFG.holyRomanEmpire_Manager.getHRE().getElector(ix))
                     ? (var13 = nScore + 16.0F)
                     : (
                        var14 = nScore
                           + 10.0F
                              * CFG.game
                                 .getCivRelation_OfCivB(
                                    CFG.holyRomanEmpire_Manager.getHRE().getPrince(CFG.holyRomanEmpire_Manager.getHRE().getElector(ix)),
                                    CFG.holyRomanEmpire_Manager.getHRE().getPrince(j)
                                 )
                              / 100.0F
                     );
                  nScore += CFG.game.getCiv(CFG.holyRomanEmpire_Manager.getHRE().getPrince(j)).civGameData.civPersonality.HRE_VOTE_FOR_PROVINCES
                     * CFG.game.getCiv(CFG.holyRomanEmpire_Manager.getHRE().getPrince(j)).getNumOfProvinces()
                     / nMaxProvinces
                     * (
                        0.4F
                           + 0.6F
                              * CFG.game
                                 .getCivRelation_OfCivB(
                                    CFG.holyRomanEmpire_Manager.getHRE().getPrince(CFG.holyRomanEmpire_Manager.getHRE().getElector(ix)),
                                    CFG.holyRomanEmpire_Manager.getHRE().getPrince(j)
                                 )
                              / 100.0F
                     );
                  nScore += CFG.game.getCiv(CFG.holyRomanEmpire_Manager.getHRE().getPrince(j)).civGameData.civPersonality.HRE_VOTE_FOR_RANK
                     * CFG.game.getCiv(CFG.holyRomanEmpire_Manager.getHRE().getPrince(j)).getRankScore()
                     / nMaxScore
                     * (
                        0.5F
                           + 0.55F
                              * CFG.game
                                 .getCivRelation_OfCivB(
                                    CFG.holyRomanEmpire_Manager.getHRE().getPrince(CFG.holyRomanEmpire_Manager.getHRE().getElector(ix)),
                                    CFG.holyRomanEmpire_Manager.getHRE().getPrince(j)
                                 )
                              / 100.0F
                     );
                  if (CFG.game.getCiv(CFG.holyRomanEmpire_Manager.getHRE().getPrince(j)).getPuppetOfCivID()
                     != CFG.holyRomanEmpire_Manager.getHRE().getPrince(j)) {
                     nScore = -500.0F;
                  }

                  if (CFG.game.getCiv(CFG.holyRomanEmpire_Manager.getHRE().getPrince(j)).getNumOfProvinces() <= 0) {
                     nScore = -10000.0F;
                  }

                  tempScores.add(nScore);
               }

               if (tempScores.size() > 0) {
                  int tBestID = 0;

                  for (int j = tBestID + 1; j < tempScores.size(); j++) {
                     if (tempScores.get(tBestID) < tempScores.get(j)) {
                        tBestID = j;
                     }
                  }

                  CFG.holyRomanEmpire_Manager.getHRE().lVotesFor.set(ix, CFG.holyRomanEmpire_Manager.getHRE().getPrince(tBestID));
               }

               tempScores.clear();
            }
         }
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

   public final void revoltDeclareIndependence() {
      for (int i = 1; i < CFG.game.getCivsSize(); i++) {
         if (CFG.game.getCiv(i).getNumOfProvinces() > 0
            && CFG.ideologiesManager.getIdeology(CFG.game.getCiv(i).getIdeologyID()).REVOLUTIONARY
            && Game_Calendar.TURN_ID - CFG.game.getCiv(i).civGameData.iRevolt_SinceTurn >= 10 + CFG.oR.nextInt(10)
            && (
               Game_Calendar.TURN_ID - CFG.game.getCiv(i).civGameData.iRevolt_LastTurnLostProvince > 2
                  || Game_Calendar.TURN_ID - CFG.game.getCiv(i).civGameData.iRevolt_SinceTurn > 49
            )) {
            this.rebels_DeclareIndependence(i);
         }
      }
   }

   public final void rebels_DeclareIndependence(int nCivID) {
      ArrayList<Integer> tempPossibleCivs = new ArrayList<>();
      ArrayList<Integer> tempPopulation = new ArrayList<>();

      for (int i2 = 0; i2 < CFG.game.getCiv(nCivID).getNumOfProvinces(); i2++) {
         for (int j = 0; j < CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i2)).getPopulationData().getNationalitiesSize(); j++) {
            if (CFG.game.getCiv(CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i2)).getPopulationData().getCivID(j)).getNumOfProvinces() == 0
               && CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i2)).getPopulationData().getCivID(j)
                  != CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i2)).getTrueOwnerOfProvince()) {
               boolean wasAdded = false;

               for (int o = 0; o < tempPossibleCivs.size(); o++) {
                  if (tempPossibleCivs.get(o) == CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i2)).getPopulationData().getCivID(j)) {
                     wasAdded = true;
                     tempPopulation.set(
                        o, tempPopulation.get(o) + CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i2)).getPopulationData().getPopulationID(j)
                     );
                     break;
                  }
               }

               if (!wasAdded) {
                  tempPossibleCivs.add(CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i2)).getPopulationData().getCivID(j));
                  tempPopulation.add(CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i2)).getPopulationData().getPopulationID(j));
               }
            }
         }
      }

      ArrayList<String> possibleNewCivsByTags = new ArrayList<>();
      ArrayList<Integer> possibleNewCivsByTags_Capitals = new ArrayList<>();
      if (tempPossibleCivs.size() == 0 || CFG.oR.nextInt(100) < 33) {
         for (int i = 0; i < CFG.game.getCiv(nCivID).getNumOfProvinces(); i++) {
            try {
               FileHandle file = Gdx.files.internal("map/" + CFG.map.getFile_ActiveMap_Path() + "suggested_owners/" + CFG.game.getCiv(nCivID).getProvinceID(i));
               String sOwners = file.readString();
               String[] sRes = sOwners.split(";");

               for (int jx = 0; jx < sRes.length; jx += 2) {
                  boolean canBeAdded = true;
                  int tempIdeologyID = CFG.ideologiesManager.getIdeologyID(sRes[jx]);
                  if (CFG.ideologiesManager.getIdeology(tempIdeologyID).REVOLUTIONARY
                     || CFG.ideologiesManager.getIdeology(tempIdeologyID).AVAILABLE_SINCE_AGE_ID <= Game_Calendar.CURRENT_AGEID) {
                     String realTag = CFG.ideologiesManager.getRealTag(sRes[jx]);

                     try {
                        if (Game_Calendar.currentYear < Integer.parseInt(sRes[jx + 1])) {
                           canBeAdded = false;
                        }
                     } catch (NumberFormatException var15) {
                        CFG.exceptionStack(var15);
                     } catch (IndexOutOfBoundsException var16) {
                        CFG.exceptionStack(var16);
                     }

                     if (canBeAdded) {
                        for (int k = 0; k < CFG.game.getCivsSize(); k++) {
                           if (CFG.ideologiesManager.getRealTag(CFG.game.getCiv(k).getCivTag()).equals(realTag)) {
                              canBeAdded = false;
                              break;
                           }
                        }
                     }

                     if (canBeAdded) {
                        for (int kx = 0; kx < possibleNewCivsByTags.size(); kx++) {
                           if (possibleNewCivsByTags.get(kx).equals(sRes[jx])) {
                              canBeAdded = false;
                              break;
                           }
                        }

                        if (canBeAdded) {
                           possibleNewCivsByTags.add(sRes[jx]);
                           possibleNewCivsByTags_Capitals.add(CFG.game.getCiv(nCivID).getProvinceID(i));
                        }
                     }
                  }
               }
            } catch (GdxRuntimeException var20) {
            }
         }
      }

      try {
         if (tempPossibleCivs.size() > 0 || possibleNewCivsByTags.size() > 0) {
            if (CFG.game.getCiv(nCivID).getCapitalProvinceID() >= 0) {
               CFG.game.getProvince(CFG.game.getCiv(nCivID).getCapitalProvinceID()).setIsCapital(false);

               for (int i = 0; i < CFG.game.getProvince(CFG.game.getCiv(nCivID).getCapitalProvinceID()).getCitiesSize(); i++) {
                  if (CFG.game.getProvince(CFG.game.getCiv(nCivID).getCapitalProvinceID()).getCity(i).getCityLevel() == CFG.getEditorCityLevel(0)) {
                     CFG.game.getProvince(CFG.game.getCiv(nCivID).getCapitalProvinceID()).getCity(i).setCityLevel(CFG.getEditorCityLevel(1));
                  }
               }
            }

            int declareCivID = -1;
            ArrayList<Integer> joinProvinces = new ArrayList<>();

            for (int i3 = 0; i3 < CFG.game.getCiv(nCivID).getNumOfProvinces(); i3++) {
               joinProvinces.add(CFG.game.getCiv(nCivID).getProvinceID(i3));
            }

            if (possibleNewCivsByTags.size() > 0) {
               int randCiv = CFG.oR.nextInt(possibleNewCivsByTags.size());
               String newTag = possibleNewCivsByTags.get(randCiv);
               float nTech = CFG.game.getCiv(CFG.game.getProvince(possibleNewCivsByTags_Capitals.get(randCiv)).getTrueOwnerOfProvince()).getTechnologyLevel();
               CFG.game.createScenarioAddCivilization(newTag, possibleNewCivsByTags_Capitals.get(randCiv), false, false, true);

               for (int i4 = CFG.game.getCivsSize() - 1; i4 >= 0; i4--) {
                  if (CFG.game.getCiv(i4).getCivTag().equals(newTag)) {
                     declareCivID = i4;
                     CFG.game.getCiv(i4).setTechnologyLevel(nTech * (0.625F + CFG.oR.nextInt(375) / 1000.0F));
                     break;
                  }
               }

               if (declareCivID < 0) {
                  declareCivID = tempPossibleCivs.get(0);
               }

               int nPop = 0;

               try {
                  CFG.game
                     .getProvince(CFG.game.getCiv(declareCivID).getCoreCapitalProvinceID())
                     .setHappiness(Math.max(CFG.game.getProvince(CFG.game.getCiv(declareCivID).getCapitalProvinceID()).getHappiness(), 0.75F));
                  CFG.game.getProvince(CFG.game.getCiv(declareCivID).getCoreCapitalProvinceID()).setRevolutionaryRisk(0.0F);

                  for (int i5 = CFG.game.getProvince(CFG.game.getCiv(declareCivID).getCapitalProvinceID()).getPopulationData().getNationalitiesSize() - 1;
                     i5 >= 0;
                     i5--
                  ) {
                     int nDiff = (int)Math.ceil(
                        CFG.game.getProvince(CFG.game.getCiv(declareCivID).getCapitalProvinceID()).getPopulationData().getPopulationID(i5)
                           * (0.625F + CFG.oR.nextInt(325) / 1000.0F)
                     );
                     CFG.game
                        .getProvince(CFG.game.getCiv(declareCivID).getCapitalProvinceID())
                        .getPopulationData()
                        .setPopulationOfCivID(
                           CFG.game.getProvince(CFG.game.getCiv(declareCivID).getCapitalProvinceID()).getPopulationData().getCivID(i5),
                           CFG.game.getProvince(CFG.game.getCiv(declareCivID).getCapitalProvinceID()).getPopulationData().getPopulationID(i5) - nDiff
                        );
                     nPop += nDiff;
                  }
               } catch (IndexOutOfBoundsException var17) {
               }

               CFG.game.getProvince(CFG.game.getCiv(declareCivID).getCapitalProvinceID()).getPopulationData().setPopulationOfCivID(declareCivID, nPop);
               this.rebels_DeclareIndependence_Civ(nCivID, declareCivID, joinProvinces, true);
            } else if (tempPossibleCivs.size() <= 1) {
               declareCivID = tempPossibleCivs.get(0);
               this.rebels_DeclareIndependence_Civ(nCivID, declareCivID, joinProvinces, false);
            } else if (CFG.oR.nextInt(100) < 85) {
               int tHighestPop = 0;

               for (int i6 = tHighestPop + 1; i6 < tempPopulation.size(); i6++) {
                  if (tempPopulation.get(tHighestPop) < tempPopulation.get(i6)) {
                     tHighestPop = i6;
                  }
               }

               declareCivID = tempPossibleCivs.get(tHighestPop);
               this.rebels_DeclareIndependence_Civ(nCivID, declareCivID, joinProvinces, false);
            } else {
               declareCivID = tempPossibleCivs.get(CFG.oR.nextInt(tempPossibleCivs.size()));
               this.rebels_DeclareIndependence_Civ(nCivID, declareCivID, joinProvinces, false);
            }
         }
      } catch (IndexOutOfBoundsException var18) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var18);
         }
      } catch (NullPointerException var19) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var19);
         }
      }
   }

   public final void rebels_DeclareIndependence_Civ(int nCivID, int declareCivID, List<Integer> joinProvinces, boolean newCivilization) {
      try {
         boolean updateCapital = true;
         if (CFG.game.getCiv(declareCivID).getCapitalProvinceID() >= 0) {
            for (int i = 0; i < joinProvinces.size(); i++) {
               if (joinProvinces.get(i) == CFG.game.getCiv(declareCivID).getCapitalProvinceID()) {
                  updateCapital = false;
                  break;
               }
            }
         }

         if (updateCapital) {
            int newCapital = 0;

            for (int i2 = 1; i2 < joinProvinces.size(); i2++) {
               if (CFG.game.getProvince(joinProvinces.get(i2)).getPopulationData().getPopulationOfCivID(declareCivID)
                  > CFG.game.getProvince(joinProvinces.get(newCapital)).getPopulationData().getPopulationOfCivID(declareCivID)) {
                  newCapital = i2;
               }
            }

            CFG.game.getCiv(declareCivID).setCapitalProvinceID(joinProvinces.get(newCapital));
         }

         for (int ix = 0; ix < CFG.game.getCivsSize(); ix++) {
            if (ix != nCivID && CFG.game.getCivsAtWar(ix, nCivID)) {
               CFG.game.whitePeace(nCivID, ix);
               CFG.game
                  .getCiv(ix)
                  .civGameData
                  .civilization_Diplomacy_GameData
                  .messageBox
                  .addMessage(new Message_DeclarationOfIndependence(declareCivID, CFG.game.getCiv(declareCivID).getCapitalProvinceID()));
            }
         }

         if (joinProvinces.size() > 1) {
            for (int var15 = joinProvinces.size() - 1; var15 >= 0; var15--) {
               boolean removeNotConnected = joinProvinces.get(var15) != CFG.game.getCiv(declareCivID).getCapitalProvinceID();
               if (removeNotConnected) {
                  for (int j2 = 0; j2 < CFG.game.getProvince(joinProvinces.get(var15)).getNeighboringProvincesSize(); j2++) {
                     if (CFG.game.getProvince(CFG.game.getProvince(joinProvinces.get(var15)).getNeighboringProvinces(j2)).getCivID()
                        == CFG.game.getProvince(joinProvinces.get(var15)).getCivID()) {
                        removeNotConnected = false;
                        break;
                     }
                  }
               }

               if (removeNotConnected) {
                  CFG.game.getProvince(joinProvinces.get(var15)).setCivID(CFG.game.getProvince(joinProvinces.get(var15)).getTrueOwnerOfProvince(), false);
                  CFG.game
                     .getProvince(joinProvinces.get(var15))
                     .setRevolutionaryRisk(CFG.game.getProvince(joinProvinces.get(var15)).getRevolutionaryRisk() * 0.15F);
                  joinProvinces.remove(var15);
               }
            }
         }

         for (int var16 = 0; var16 < joinProvinces.size(); var16++) {
            CFG.game.getProvince(joinProvinces.get(var16)).setTrueOwnerOfProvince(declareCivID);
            if (CFG.game.getProvince(joinProvinces.get(var16)).getCivID() != declareCivID) {
               CFG.game.getProvince(joinProvinces.get(var16)).setCivID(declareCivID, false, true);
               CFG.game.getProvince(joinProvinces.get(var16)).setRevolutionaryRisk(0.0F);
               if (CFG.game.getProvince(joinProvinces.get(var16)).getHappiness() < 0.7F) {
                  CFG.game.getProvince(joinProvinces.get(var16)).setHappiness(0.7F + CFG.oR.nextInt(20) / 100.0F);
               } else {
                  CFG.game.getProvince(joinProvinces.get(var16)).setHappiness(CFG.game.getProvince(joinProvinces.get(var16)).getHappiness() * 1.1775F);
               }
            }

            if (newCivilization) {
               int nPop = 0;

               try {
                  for (int j3 = CFG.game.getProvince(joinProvinces.get(var16)).getPopulationData().getNationalitiesSize() - 1; var16 >= 0; j3--) {
                     int nDiff = (int)Math.ceil(
                        CFG.game.getProvince(joinProvinces.get(var16)).getPopulationData().getPopulationID(j3) * (0.325F + CFG.oR.nextInt(350) / 1000.0F)
                     );
                     CFG.game
                        .getProvince(joinProvinces.get(var16))
                        .getPopulationData()
                        .setPopulationOfCivID(
                           CFG.game.getProvince(joinProvinces.get(var16)).getPopulationData().getCivID(j3),
                           CFG.game.getProvince(joinProvinces.get(var16)).getPopulationData().getPopulationID(j3) - nDiff
                        );
                     nPop += nDiff;
                  }
               } catch (IndexOutOfBoundsException var11) {
               }

               CFG.game.getProvince(joinProvinces.get(var16)).getPopulationData().setPopulationOfCivID(declareCivID, nPop);
            }

            CFG.game.getProvince(joinProvinces.get(var16)).saveProvinceData.iNumOfRevolutions = 0;
         }

         if (CFG.game.getCiv(declareCivID).getCapitalProvinceID() >= 0) {
            CFG.game.getProvince(CFG.game.getCiv(declareCivID).getCapitalProvinceID()).setIsCapital(true);
            if (CFG.game.getProvince(CFG.game.getCiv(declareCivID).getCapitalProvinceID()).getCitiesSize() > 0) {
               CFG.game.getProvince(CFG.game.getCiv(declareCivID).getCapitalProvinceID()).getCity(0).setCityLevel(CFG.getEditorCityLevel(0));
            }
         }

         for (int var17 = 0; var17 < CFG.game.getCiv(declareCivID).getNumOfProvinces(); var17++) {
            CFG.game.getProvince(CFG.game.getCiv(declareCivID).getProvinceID(var17)).updateDrawArmy();
            if (!CFG.game.getProvince(CFG.game.getCiv(declareCivID).getProvinceID(var17)).getCore().getHaveACore(declareCivID)) {
               CFG.game.getProvince(CFG.game.getCiv(declareCivID).getProvinceID(var17)).getCore().addNewCore(declareCivID, Game_Calendar.TURN_ID);
            }

            for (int j = CFG.game.getProvince(CFG.game.getCiv(declareCivID).getProvinceID(var17)).getPopulationData().getNationalitiesSize() - 1; j >= 0; j--) {
               int tempPop = CFG.game.getProvince(CFG.game.getCiv(declareCivID).getProvinceID(var17)).getPopulationData().getPopulationOfCivID(j);
               int tempPopCiv = CFG.game.getProvince(CFG.game.getCiv(declareCivID).getProvinceID(var17)).getPopulationData().getCivID(j);
               int tRand = (int)Math.floor(0.0625F + CFG.oR.nextInt(63) / 100.0F * tempPop);
               if (tRand > 0) {
                  CFG.game
                     .getProvince(CFG.game.getCiv(declareCivID).getProvinceID(var17))
                     .getPopulationData()
                     .setPopulationOfCivID(tempPopCiv, tempPop - tRand);
                  CFG.game
                     .getProvince(CFG.game.getCiv(declareCivID).getProvinceID(var17))
                     .getPopulationData()
                     .setPopulationOfCivID(
                        declareCivID,
                        tempPop
                           - CFG.game.getProvince(CFG.game.getCiv(declareCivID).getProvinceID(var17)).getPopulationData().getPopulationOfCivID(declareCivID)
                           + tRand
                     );
               }
            }
         }

         if (CFG.FOG_OF_WAR == 2) {
            for (int var18 = 0; var18 < CFG.game.getPlayersSize(); var18++) {
               for (int jx = 0; jx < CFG.game.getCiv(declareCivID).getNumOfProvinces(); jx++) {
                  if (CFG.game.getPlayer(var18).getMetProvince(CFG.game.getCiv(declareCivID).getProvinceID(jx))) {
                     CFG.game.getPlayer(var18).setMetCivilization(declareCivID, true);
                     break;
                  }
               }
            }
         }

         CFG.game.getCiv(declareCivID).buildNumOfUnits();
         CFG.game.getCiv(declareCivID).setMoney(Math.max(50L, CFG.game.getCiv(declareCivID).getMoney()));
      } catch (IndexOutOfBoundsException var12) {
      }
   }

   public final boolean canAnyCivUprise(int nProvinceID) {
      for (int i = 0; i < CFG.game.getProvince(nProvinceID).getCore().getCivsSize(); i++) {
         if (CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCore().getCivID(i)).getNumOfProvinces() == 0
            && CFG.game.getProvince(nProvinceID).getCore().getCivID(i) != CFG.game.getProvince(nProvinceID).getTrueOwnerOfProvince()) {
            return true;
         }
      }

      return false;
   }

   public final void startUprising() {
      Gdx.app.log("AoC", "GA -> startUprising: BEGIN");
      ArrayList<Integer> tempPossibleUprising = new ArrayList<>();
      ArrayList<Integer> tempPossibleUprising_CheckSuggest = new ArrayList<>();
      ArrayList<Integer> overMin = new ArrayList<>();
      int numOfTrueOwnerProvinces = 0;

      for (int i = 1 + Game_Calendar.TURN_ID % 3; i < CFG.game.getCivsSize(); i += 3) {
         if (CFG.game.getCiv(i).getNumOfProvinces() > 0
            && CFG.ideologiesManager.getIdeology(CFG.game.getCiv(i).getIdeologyID()).CAN_BECOME_CIVILIZED < 0
            && !CFG.ideologiesManager.getIdeology(CFG.game.getCiv(i).getIdeologyID()).REVOLUTIONARY) {
            tempPossibleUprising.clear();
            tempPossibleUprising_CheckSuggest.clear();
            overMin.clear();
            numOfTrueOwnerProvinces = 0;

            for (int j = 0; j < CFG.game.getCiv(i).getNumOfProvinces(); j++) {
               if (CFG.game.getProvince(CFG.game.getCiv(i).getProvinceID(j)).getCivID()
                  == CFG.game.getProvince(CFG.game.getCiv(i).getProvinceID(j)).getTrueOwnerOfProvince()) {
                  numOfTrueOwnerProvinces++;
                  if (CFG.game.getProvince(CFG.game.getCiv(i).getProvinceID(j)).getRevolutionaryRisk() > 0.16F
                     && !CFG.game.getProvince(CFG.game.getCiv(i).getProvinceID(j)).getIsCapital()) {
                     if (this.getModifiedRevolutionsRisk(CFG.game.getCiv(i).getProvinceID(j)) > 0.64F * (0.4F + 0.6F * CFG.game.getCiv(i).getStability())
                        && CFG.oR.nextInt((int)(this.getModifiedRevolutionsRisk(CFG.game.getCiv(i).getProvinceID(j)) * 100.0F)) > 40) {
                        if (this.canAnyCivUprise(CFG.game.getCiv(i).getProvinceID(j))) {
                           tempPossibleUprising.add(CFG.game.getCiv(i).getProvinceID(j));
                        } else {
                           tempPossibleUprising_CheckSuggest.add(CFG.game.getCiv(i).getProvinceID(j));
                        }
                     }

                     overMin.add(CFG.game.getCiv(i).getProvinceID(j));
                  }
               }
            }

            if (tempPossibleUprising.size() == 0 && tempPossibleUprising_CheckSuggest.size() > 0) {
               for (int var20 = tempPossibleUprising_CheckSuggest.size() - 1; var20 >= 0; var20--) {
                  try {
                     FileHandle file = Gdx.files
                        .internal("map/" + CFG.map.getFile_ActiveMap_Path() + "suggested_owners/" + tempPossibleUprising_CheckSuggest.get(var20));
                     String sOwners = file.readString();
                     String[] sRes = sOwners.split(";");

                     for (int k = 0; k < sRes.length; k += 2) {
                        boolean canBeAdded = true;
                        int tempIdeologyID = CFG.ideologiesManager.getIdeologyID(sRes[k]);
                        if (!CFG.ideologiesManager.getIdeology(tempIdeologyID).REVOLUTIONARY
                           && CFG.ideologiesManager.getIdeology(tempIdeologyID).AVAILABLE_SINCE_AGE_ID <= Game_Calendar.CURRENT_AGEID) {
                           String realTag = CFG.ideologiesManager.getRealTag(sRes[k]);

                           for (int o = 0; o < CFG.game.getCivsSize(); o++) {
                              if (CFG.ideologiesManager.getRealTag(CFG.game.getCiv(o).getCivTag()).equals(realTag)) {
                                 canBeAdded = false;
                                 break;
                              }
                           }

                           if (canBeAdded) {
                              tempPossibleUprising.add(tempPossibleUprising_CheckSuggest.get(var20));
                              break;
                           }
                        }
                     }
                  } catch (GdxRuntimeException var15) {
                  }
               }
            }

            if (tempPossibleUprising.size() > 0 || overMin.size() > 0) {
               this.spawnRevolution(i, tempPossibleUprising, overMin, numOfTrueOwnerProvinces);
            }
         }
      }

      tempPossibleUprising.clear();
      ArrayList<Integer> var16 = null;
      tempPossibleUprising_CheckSuggest.clear();
      ArrayList<Integer> var17 = null;
      overMin.clear();
      ArrayList<Integer> var18 = null;
   }

   public final float getModifiedRevolutionsRisk(int nProvinceID) {
      return (
            CFG.game.getProvince(nProvinceID).getRevolutionaryRisk() * (1.0F + CFG.game.getProvince(nProvinceID).getCore().getCivsSize() / 10.0F)
               - (float)CFG.game.getProvinceArmy(nProvinceID) / CFG.game.getProvince(nProvinceID).getPopulationData().getPopulation() * 50.0F
         )
         * Game_Calendar.POWERREBELS;
   }

   public final void spawnRevolution(int nCivID, List<Integer> nProvinces, List<Integer> nOverMin, int numOfTrueOwnerProvinces) {
      Gdx.app.log("AoC", "GA -> spawnRevolution: BEGIN: " + CFG.game.getCiv(nCivID).getCivName());
      ArrayList<Integer> tempSorted = new ArrayList<>();

      while (nProvinces.size() > 0) {
         int tBest = 0;

         for (int i2 = nProvinces.size() - 1; i2 > 0; i2--) {
            if (CFG.game.getProvince(nProvinces.get(i2)).getRevolutionaryRisk() > CFG.game.getProvince(nProvinces.get(tBest)).getRevolutionaryRisk()) {
               tBest = i2;
            }
         }

         tempSorted.add(nProvinces.get(tBest));
         nProvinces.remove(tBest);
      }

      Gdx.app.log("AoC", "GA -> spawnRevolution: 000");
      if (numOfTrueOwnerProvinces * 0.63F < nOverMin.size() && CFG.oR.nextInt(1000) < 47) {
         Gdx.app.log("AoC", "GA -> spawnRevolution: 111");
         ArrayList<Integer> possibleIdeologies = new ArrayList<>();
         ArrayList<Integer> possibleCivsExisting = new ArrayList<>();

         for (int i = 0; i < CFG.ideologiesManager.getIdeologiesSize(); i++) {
            if (CFG.ideologiesManager.getIdeology(i).CAN_BECOME_CIVILIZED < 0
               && !CFG.ideologiesManager.getIdeology(i).REVOLUTIONARY
               && Game_Calendar.CURRENT_AGEID >= CFG.ideologiesManager.getIdeology(i).AVAILABLE_SINCE_AGE_ID) {
               String tempTag = CFG.ideologiesManager.getRealTag(CFG.game.getCiv(nCivID).getCivTag()) + CFG.ideologiesManager.getIdeology(i).getExtraTag();
               boolean isInTheGame = CFG.game.getCiv(nCivID).getCivTag().equals(tempTag);
               if (!isInTheGame) {
                  for (int j = 0; j < CFG.game.getCivsSize(); j++) {
                     if (CFG.game.getCiv(j).getCivTag().equals(tempTag)) {
                        if (CFG.game.getCiv(j).getNumOfProvinces() > 0) {
                           isInTheGame = true;
                        } else {
                           possibleCivsExisting.add(j);
                        }
                        break;
                     }
                  }

                  if (!isInTheGame) {
                     possibleIdeologies.add(i);
                  }
               }
            }
         }

         Gdx.app.log("AoC", "GA -> spawnRevolution: 222");
         if (possibleIdeologies.size() > 0 || possibleCivsExisting.size() > 0) {
            Gdx.app.log("AoC", "GA -> spawnRevolution: 333");
            ArrayList allProvincesSorted = new ArrayList();

            for (int i3 = tempSorted.size() - 1; i3 >= 0; i3--) {
               allProvincesSorted.add(tempSorted.get(i3));
            }

            for (int var38 = nOverMin.size() - 1; var38 >= 0; var38--) {
               boolean wasAdded = false;

               for (int jx = 0; jx < allProvincesSorted.size(); jx++) {
                  if (allProvincesSorted.get(jx) == nOverMin.get(var38)) {
                     wasAdded = true;
                     break;
                  }
               }

               if (!wasAdded) {
                  allProvincesSorted.add(nOverMin.get(var38));
               }
            }

            Gdx.app.log("AoC", "GA -> spawnRevolution: 444");
            ArrayList<Integer> revoltProvinces = new ArrayList<>();
            int numOfTrueProvinces = 0;

            for (int i4 = 0; i4 < CFG.game.getCiv(nCivID).getNumOfProvinces(); i4++) {
               if (CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i4)).getCivID()
                  == CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i4)).getTrueOwnerOfProvince()) {
                  numOfTrueProvinces++;
               }
            }

            Gdx.app.log("AoC", "GA -> spawnRevolution: 555");
            int numOfRevoltProvincesMax = (int)(numOfTrueProvinces * (0.3F + CFG.oR.nextInt(25) / 100.0F));
            if (numOfRevoltProvincesMax > 0 && allProvincesSorted.size() > 0) {
               Gdx.app.log("AoC", "GA -> spawnRevolution: 666");
               int igniteProvince = (Integer)allProvincesSorted.get(CFG.oR.nextInt(allProvincesSorted.size()));
               revoltProvinces.add(igniteProvince);
               Gdx.app.log("AoC", "GA -> spawnRevolution: 777");
               if (numOfRevoltProvincesMax > revoltProvinces.size()) {
                  Gdx.app.log("AoC", "GA -> spawnRevolution: 888");

                  for (int j2 = 0; j2 < CFG.game.getProvince(igniteProvince).getNeighboringProvincesSize(); j2++) {
                     if (CFG.game.getProvince(CFG.game.getProvince(igniteProvince).getNeighboringProvinces(j2)).getCivID() == nCivID
                        && CFG.game.getProvince(CFG.game.getProvince(igniteProvince).getNeighboringProvinces(j2)).getCivID()
                           == CFG.game.getProvince(CFG.game.getProvince(igniteProvince).getNeighboringProvinces(j2)).getTrueOwnerOfProvince()
                        && !CFG.game.getProvince(CFG.game.getProvince(igniteProvince).getNeighboringProvinces(j2)).getIsCapital()
                        && CFG.game.getProvince(CFG.game.getProvince(igniteProvince).getNeighboringProvinces(j2)).getRevolutionaryRisk() > 0.16F) {
                        revoltProvinces.add(CFG.game.getProvince(igniteProvince).getNeighboringProvinces(j2));
                        if (numOfRevoltProvincesMax <= revoltProvinces.size()) {
                           break;
                        }
                     }
                  }

                  Gdx.app.log("AoC", "GA -> spawnRevolution: 999");
                  if (numOfRevoltProvincesMax > revoltProvinces.size()) {
                     Gdx.app.log("AoC", "GA -> spawnRevolution: 10");

                     for (int i5 = allProvincesSorted.size() - 1; i5 >= 0; i5--) {
                        for (int j3 = revoltProvinces.size() - 1; j3 >= 0; j3--) {
                           if (allProvincesSorted.get(i5) == revoltProvinces.get(j3)) {
                              allProvincesSorted.remove(i5);
                              break;
                           }
                        }
                     }

                     Gdx.app.log("AoC", "GA -> spawnRevolution: 11");

                     while (numOfRevoltProvincesMax > revoltProvinces.size() && allProvincesSorted.size() > 0) {
                        int counter = 0;
                        int nRand = 0;

                        while (true) {
                           if (counter++ < 8) {
                              nRand = CFG.oR.nextInt(allProvincesSorted.size());
                              boolean endRand = false;

                              for (int o = revoltProvinces.size() - 1; o >= 0; o--) {
                                 for (int p = 0; p < CFG.game.getProvince((Integer)allProvincesSorted.get(nRand)).getNeighboringProvincesSize(); p++) {
                                    if (CFG.game.getProvince((Integer)allProvincesSorted.get(nRand)).getNeighboringProvinces(p) == revoltProvinces.get(o)) {
                                       endRand = true;
                                       o = -1;
                                       break;
                                    }
                                 }
                              }

                              if (!endRand) {
                                 continue;
                              }
                           }

                           revoltProvinces.add((Integer)allProvincesSorted.get(nRand));
                           allProvincesSorted.remove(nRand);
                           break;
                        }
                     }

                     Gdx.app.log("AoC", "GA -> spawnRevolution: 12");
                  }
               }

               Gdx.app.log("AoC", "GA -> spawnRevolution: 13");
               boolean spawnedCivWithDifferentGovernment = false;
               if (revoltProvinces.size() > 0) {
                  Gdx.app.log("AoC", "GA -> spawnRevolution: 14");
                  String nRevTag = "";
                  ArrayList<Province_Army> tempArmies = new ArrayList<>();
                  ArrayList tempArmiesProvinces = new ArrayList();
                  if (possibleCivsExisting.size() > 0 && (CFG.oR.nextInt(10) < 5 || possibleIdeologies.size() == 0)) {
                     Gdx.app.log("AoC", "GA -> spawnRevolution: 15");
                     int randCiv = CFG.oR.nextInt(possibleCivsExisting.size());
                     nRevTag = CFG.game.getCiv(possibleCivsExisting.get(randCiv)).getCivTag();
                     CFG.game.getCiv(possibleCivsExisting.get(randCiv)).setCapitalProvinceID(revoltProvinces.get(0));
                     if (CFG.game.getProvince(revoltProvinces.get(0)).getArmy(0) > 0) {
                        tempArmies.add(new Province_Army(nCivID, CFG.game.getProvince(revoltProvinces.get(0)).getArmy(0), revoltProvinces.get(0)));
                        tempArmiesProvinces.add(revoltProvinces.get(0));
                     }

                     Gdx.app.log("AoC", "GA -> spawnRevolution: 16");
                  } else if (possibleIdeologies.size() > 0) {
                     Gdx.app.log("AoC", "GA -> spawnRevolution: 17");
                     nRevTag = CFG.ideologiesManager.getRealTag(CFG.game.getCiv(nCivID).getCivTag())
                        + CFG.ideologiesManager.getIdeology(possibleIdeologies.get(CFG.oR.nextInt(possibleIdeologies.size()))).getExtraTag();
                     if (CFG.game.getProvince(revoltProvinces.get(0)).getArmy(0) > 0) {
                        tempArmies.add(new Province_Army(nCivID, CFG.game.getProvince(revoltProvinces.get(0)).getArmy(0), revoltProvinces.get(0)));
                        tempArmiesProvinces.add(revoltProvinces.get(0));
                     }

                     CFG.game.createScenarioAddCivilization(nRevTag, revoltProvinces.get(0), false, false, true);
                     spawnedCivWithDifferentGovernment = true;
                     Gdx.app.log("AoC", "GA -> spawnRevolution: 18");
                  }

                  Gdx.app.log("AoC", "GA -> spawnRevolution: 19");
                  int nRebelsCivID = -1;

                  for (int i6 = CFG.game.getCivsSize() - 1; i6 > 0; i6--) {
                     if (CFG.game.getCiv(i6).getCivTag().equals(nRevTag)) {
                        nRebelsCivID = i6;
                        break;
                     }
                  }

                  Gdx.app.log("AoC", "GA -> spawnRevolution: 20");
                  if (nRebelsCivID > 0) {
                     Gdx.app.log("AoC", "GA -> spawnRevolution: 21");

                     for (int var58 = 0; var58 < revoltProvinces.size(); var58++) {
                        if (CFG.game.getProvince(revoltProvinces.get(var58)).getCivID() != nRebelsCivID) {
                           if (CFG.game.getProvince(revoltProvinces.get(var58)).getArmy(0) > 0) {
                              tempArmies.add(new Province_Army(nCivID, CFG.game.getProvince(revoltProvinces.get(var58)).getArmy(0), revoltProvinces.get(var58)));
                              tempArmiesProvinces.add(revoltProvinces.get(var58));
                           }

                           if (spawnedCivWithDifferentGovernment) {
                              CFG.game.getProvince(revoltProvinces.get(var58)).setTrueOwnerOfProvince(nRebelsCivID);
                              CFG.game.getProvince(revoltProvinces.get(var58)).setCivID(nRebelsCivID, true);
                           } else {
                              CFG.game.getProvince(revoltProvinces.get(var58)).setCivID(nRebelsCivID, true);
                              CFG.game.getProvince(revoltProvinces.get(var58)).setTrueOwnerOfProvince(nRebelsCivID);
                           }

                           this.updateProvinceAfterRevolution(revoltProvinces.get(var58));
                           this.spawnRevolutionaryArmy(revoltProvinces.get(var58), nCivID, nRebelsCivID);
                        }
                     }

                     Gdx.app.log("AoC", "GA -> spawnRevolution: 22");

                     for (int var59 = 0; var59 < tempArmies.size(); var59++) {
                        CFG.game
                           .getProvince((Integer)tempArmiesProvinces.get(var59))
                           .updateArmy(tempArmies.get(var59).getCivID(), tempArmies.get(var59).getArmy());
                        CFG.game
                           .getCiv(tempArmies.get(var59).getCivID())
                           .newMove((Integer)tempArmiesProvinces.get(var59), (Integer)tempArmiesProvinces.get(var59), tempArmies.get(var59).getArmy(), true);

                        for (int a = CFG.game.getProvince((Integer)tempArmiesProvinces.get(var59)).getCivsSize() - 1; a >= 0; a--) {
                           if (CFG.game.getProvince((Integer)tempArmiesProvinces.get(var59)).getCivID(a) != nCivID
                              && CFG.game.getProvince((Integer)tempArmiesProvinces.get(var59)).getCivID(a) != nRebelsCivID) {
                              this.accessLost_MoveArmyToClosetsProvince(
                                 CFG.game.getProvince((Integer)tempArmiesProvinces.get(var59)).getCivID(a), (Integer)tempArmiesProvinces.get(var59)
                              );
                           }
                        }
                     }

                     CFG.game.getCiv(nCivID).setNumOfUnits(0);
                     CFG.game.getCiv(nCivID).buildNumOfUnits();
                     CFG.game.getCiv(nRebelsCivID).setNumOfUnits(0);
                     CFG.game.getCiv(nRebelsCivID).buildNumOfUnits();
                     Color nColor = CFG.getRandomColor();
                     CFG.game.getCiv(nRebelsCivID).setR((int)(nColor.r * 255.0F));
                     CFG.game.getCiv(nRebelsCivID).setG((int)(nColor.g * 255.0F));
                     CFG.game.getCiv(nRebelsCivID).setB((int)(nColor.b * 255.0F));
                     CFG.game.getCiv(nRebelsCivID).setMoney(Math.max(CFG.game.getCiv(nRebelsCivID).getMoney(), 50L));
                     CFG.game.getCiv(nRebelsCivID).setTechnologyLevel(CFG.game.getCiv(nCivID).getTechnologyLevel() * (0.845F + CFG.oR.nextInt(125) / 1000.0F));
                     if (CFG.game.getCiv(nCivID).getCivID() != CFG.game.getCiv(nCivID).getPuppetOfCivID()) {
                        CFG.game.getCiv(nRebelsCivID).setPuppetOfCivID(CFG.game.getCiv(nCivID).getPuppetOfCivID());
                     }

                     try {
                        for (int px = 0; px < CFG.game.getPlayersSize(); px++) {
                           if (!CFG.game.getPlayer(px).getMetCivilization(nRebelsCivID)) {
                              for (int o = 0; o < CFG.game.getCiv(nRebelsCivID).getNumOfProvinces(); o++) {
                                 if (CFG.game.getPlayer(px).getMetProvince(CFG.game.getCiv(nRebelsCivID).getProvinceID(o))) {
                                    CFG.game.getPlayer(px).setMetCivilization(nRebelsCivID, true);
                                    break;
                                 }
                              }
                           }
                        }
                     } catch (IndexOutOfBoundsException var27) {
                        CFG.exceptionStack(var27);
                     }

                     Gdx.app.log("AoC", "GA -> spawnRevolution: 23");
                     CFG.game
                        .getCiv(nCivID)
                        .civGameData
                        .civilization_Diplomacy_GameData
                        .messageBox
                        .addMessage(new Message_Revolt(nRebelsCivID, revoltProvinces.get(0)));
                     CFG.game.declareWar(nRebelsCivID, nCivID, true);
                     CFG.game.getCiv(nRebelsCivID).isAtCivilWar = true;
                     CFG.game.getCiv(nCivID).isAtCivilWar = true;
                     CFG.game.getCiv(nCivID).civGameData.iNumOfRevolutions++;
                     Gdx.app.log("AoC", "GA -> spawnRevolution: 24");
                  }

                  for (int var60 = 0; var60 < CFG.game.getCiv(nCivID).getNumOfProvinces(); var60++) {
                     CFG.game
                        .getProvince(CFG.game.getCiv(nCivID).getProvinceID(var60))
                        .setRevolutionaryRisk(
                           CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(var60)).getRevolutionaryRisk() * (0.7F + CFG.oR.nextInt(300) / 1000.0F)
                        );
                  }

                  for (int var61 = 0; var61 < CFG.game.getCiv(nRebelsCivID).getNumOfProvinces(); var61++) {
                     CFG.game.getProvince(CFG.game.getCiv(nRebelsCivID).getProvinceID(var61)).setRevolutionaryRisk(0.0F);
                     CFG.game
                        .getProvince(CFG.game.getCiv(nRebelsCivID).getProvinceID(var61))
                        .setHappiness(
                           Math.max(
                              0.66F + CFG.oR.nextInt(24) / 100.0F, CFG.game.getProvince(CFG.game.getCiv(nRebelsCivID).getProvinceID(var61)).getHappiness()
                           )
                        );
                  }

                  Gdx.app.log("AoC", "GA -> spawnRevolution: 25");
                  if (spawnedCivWithDifferentGovernment) {
                     for (int var62 = 0; var62 < CFG.game.getCiv(nRebelsCivID).getNumOfProvinces(); var62++) {
                        if (!CFG.game.getProvince(CFG.game.getCiv(nRebelsCivID).getProvinceID(var62)).getCore().getHaveACore(nRebelsCivID)) {
                           CFG.game.getProvince(CFG.game.getCiv(nRebelsCivID).getProvinceID(var62)).getCore().addNewCore(nRebelsCivID, Game_Calendar.TURN_ID);
                        }

                        int popOfNativeCiv;
                        if ((
                              popOfNativeCiv = CFG.game
                                 .getProvince(CFG.game.getCiv(nRebelsCivID).getProvinceID(var62))
                                 .getPopulationData()
                                 .getPopulationOfCivID(nCivID)
                           )
                           > 0) {
                           float randPerc = CFG.oR.nextInt(625) / 1000.0F;
                           CFG.game
                              .getProvince(CFG.game.getCiv(nRebelsCivID).getProvinceID(var62))
                              .getPopulationData()
                              .setPopulationOfCivID(nCivID, (int)(popOfNativeCiv * randPerc));
                           CFG.game
                              .getProvince(CFG.game.getCiv(nRebelsCivID).getProvinceID(var62))
                              .getPopulationData()
                              .setPopulationOfCivID(nRebelsCivID, (int)(popOfNativeCiv * (1.0F - randPerc)));
                        }
                     }
                  }

                  Gdx.app.log("AoC", "GA -> spawnRevolution: 26");

                  for (int var63 = 0; var63 < CFG.game.getCiv(nRebelsCivID).getNumOfProvinces(); var63++) {
                     CFG.game.getProvince(CFG.game.getCiv(nRebelsCivID).getProvinceID(var63)).updateDrawArmy();
                  }

                  Gdx.app.log("AoC", "GA -> spawnRevolution: 27");

                  for (int var64 = 0; var64 < CFG.game.getCiv(nCivID).getNumOfProvinces(); var64++) {
                     if (CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(var64)).getCivID()
                        == CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(var64)).getTrueOwnerOfProvince()) {
                        CFG.game
                           .getProvince(CFG.game.getCiv(nCivID).getProvinceID(var64))
                           .setRevolutionaryRisk(CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(var64)).getRevolutionaryRisk() * 0.645F);
                        CFG.game
                           .getProvince(CFG.game.getCiv(nCivID).getProvinceID(var64))
                           .setHappiness((CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(var64)).getHappiness() + 0.08F) * 1.124F);
                        if (CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(var64)).getHappiness() < 0.32F) {
                           CFG.game
                              .getProvince(CFG.game.getCiv(nCivID).getProvinceID(var64))
                              .setHappiness(0.32F + CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(var64)).getHappiness() * 0.1F);
                        }
                     } else {
                        CFG.game
                           .getProvince(CFG.game.getCiv(nCivID).getProvinceID(var64))
                           .setRevolutionaryRisk(CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(var64)).getRevolutionaryRisk() * 0.4638F);
                     }
                  }

                  Gdx.app.log("AoC", "GA -> spawnRevolution: 28");
                  if (CFG.game.getCiv(nRebelsCivID).getCapitalProvinceID() >= 0) {
                     CFG.game.getProvince(CFG.game.getCiv(nRebelsCivID).getCapitalProvinceID()).setIsCapital(true);
                     boolean updateCapitalLevel = true;

                     for (int i7 = 0; i7 < CFG.game.getProvince(CFG.game.getCiv(nRebelsCivID).getCapitalProvinceID()).getCitiesSize(); i7++) {
                        if (CFG.game.getProvince(CFG.game.getCiv(nRebelsCivID).getCapitalProvinceID()).getCity(i7).getCityLevel() == CFG.getEditorCityLevel(0)) {
                           updateCapitalLevel = false;
                           break;
                        }
                     }

                     if (updateCapitalLevel && CFG.game.getProvince(CFG.game.getCiv(nRebelsCivID).getCapitalProvinceID()).getCitiesSize() > 0) {
                        CFG.game.getProvince(CFG.game.getCiv(nRebelsCivID).getCapitalProvinceID()).getCity(0).setCityLevel(CFG.getEditorCityLevel(0));
                     }
                  }

                  Gdx.app.log("AoC", "GA -> spawnRevolution: 29");
                  if (CFG.FOG_OF_WAR == 2) {
                     for (int var65 = 0; var65 < CFG.game.getPlayersSize(); var65++) {
                        for (int j4 = 0; j4 < CFG.game.getCiv(nRebelsCivID).getNumOfProvinces(); j4++) {
                           if (CFG.game.getPlayer(var65).getMetProvince(CFG.game.getCiv(nRebelsCivID).getProvinceID(j4))) {
                              CFG.game.getPlayer(var65).setMetCivilization(nRebelsCivID, true);
                              break;
                           }
                        }
                     }
                  }

                  Gdx.app.log("AoC", "GA -> spawnRevolution: 30");
                  return;
               }
            }
         }
      }

      Gdx.app.log("AoC", "GA -> spawnRevolution: SECOND 0000");
      String nRevTagx = CFG.ideologiesManager.getRealTag(CFG.game.getCiv(nCivID).getCivTag())
         + CFG.ideologiesManager.getIdeology(CFG.ideologiesManager.REBELS_ID).getExtraTag();
      int revoltCivID = -1;

      for (int ix = 1; ix < CFG.game.getCivsSize(); ix++) {
         if (CFG.game.getCiv(ix).getIdeologyID() == CFG.ideologiesManager.REBELS_ID && this.getSpawnRebels_CivRebelsTag(ix).equals(nRevTagx)) {
            if (CFG.game.getCiv(ix).getNumOfProvinces() == 0) {
               revoltCivID = ix;
            } else {
               if (CFG.game.getCiv(ix).getNumOfProvinces() > 1
                  && CFG.oR.nextInt(1500) % 100
                     < Math.min(50, 20 + 10 * (Game_Calendar.TURN_ID - CFG.game.getCiv(ix).civGameData.iRevolt_LastTurnLostProvince))
                  && CFG.game.getCiv(ix).getNumOfProvinces() < CFG.game.getCiv(nCivID).getNumOfProvinces() - 1) {
                  int theBestProvinceID = -1;
                  int theBestConnections = 0;

                  for (int jxx = 0; jxx < tempSorted.size(); jxx++) {
                     int currentConnections = 0;
                     int ownProvincesConnection = 0;

                     for (int k = 0; k < CFG.game.getProvince(tempSorted.get(jxx)).getNeighboringProvincesSize(); k++) {
                        if (CFG.game.getProvince(CFG.game.getProvince(tempSorted.get(jxx)).getNeighboringProvinces(k)).getCivID() == ix) {
                           currentConnections++;
                        } else if (CFG.game.getProvince(CFG.game.getProvince(tempSorted.get(jxx)).getNeighboringProvinces(k)).getCivID()
                           == CFG.game.getProvince(tempSorted.get(jxx)).getCivID()) {
                           ownProvincesConnection++;
                        }
                     }

                     if (currentConnections > 0) {
                        if (ownProvincesConnection == 0) {
                           currentConnections += 2;
                        } else if (ownProvincesConnection == 1) {
                           currentConnections++;
                        }
                     }

                     if (currentConnections > theBestConnections
                        || currentConnections > 0 && currentConnections == theBestConnections && CFG.oR.nextInt(150) % 2 == 1) {
                        theBestProvinceID = tempSorted.get(jxx);
                        theBestConnections = currentConnections;
                     }
                  }

                  if (theBestProvinceID < 0) {
                     for (int var30 = 0; var30 < nOverMin.size(); var30++) {
                        int currentConnections = 0;
                        int ownProvincesConnection = 0;

                        for (int kx = 0; kx < CFG.game.getProvince(nOverMin.get(var30)).getNeighboringProvincesSize(); kx++) {
                           if (CFG.game.getProvince(CFG.game.getProvince(nOverMin.get(var30)).getNeighboringProvinces(kx)).getCivID() == ix) {
                              currentConnections++;
                           } else if (CFG.game.getProvince(CFG.game.getProvince(nOverMin.get(var30)).getNeighboringProvinces(kx)).getCivID()
                              == CFG.game.getProvince(nOverMin.get(var30)).getCivID()) {
                              ownProvincesConnection++;
                           }
                        }

                        if (currentConnections > 0) {
                           if (ownProvincesConnection == 0) {
                              currentConnections += 2;
                           } else if (ownProvincesConnection == 1) {
                              currentConnections++;
                           }
                        }

                        if (currentConnections > theBestConnections
                           || currentConnections > 0 && currentConnections == theBestConnections && CFG.oR.nextInt(150) % 2 == 1) {
                           theBestProvinceID = nOverMin.get(var30);
                           theBestConnections = currentConnections;
                        }
                     }
                  }

                  if (theBestProvinceID >= 0) {
                     for (int z = tempSorted.size() - 1; z >= 0; z--) {
                        if (tempSorted.get(z) == theBestProvinceID) {
                           tempSorted.remove(z);
                           break;
                        }
                     }

                     int nArmy0 = CFG.game.getProvince(theBestProvinceID).getArmy(0);
                     CFG.game.getProvince(theBestProvinceID).setCivID(ix, false, true);
                     this.updateProvinceAfterRevolution(theBestProvinceID);
                     this.spawnRevolutionaryArmy(theBestProvinceID, nCivID, ix);
                     if (nArmy0 > 0) {
                        CFG.game.getProvince(theBestProvinceID).updateArmy(nCivID, nArmy0);
                        CFG.game.getCiv(nCivID).newMove(theBestProvinceID, theBestProvinceID, nArmy0, true);

                        for (int ax = CFG.game.getProvince(theBestProvinceID).getCivsSize() - 1; ax >= 0; ax--) {
                           if (CFG.game.getProvince(theBestProvinceID).getCivID(ax) != nCivID && CFG.game.getProvince(theBestProvinceID).getCivID(ax) != ix) {
                              this.accessLost_MoveArmyToClosetsProvince(CFG.game.getProvince(theBestProvinceID).getCivID(ax), theBestProvinceID);
                           }
                        }
                     }

                     CFG.game.getCiv(nCivID).civGameData.civilization_Diplomacy_GameData.messageBox.addMessage(new Message_Revolt(ix, theBestProvinceID));
                  }
               }

               if (tempSorted.size() == 0) {
                  return;
               }
            }
         }
      }

      if (tempSorted.size() != 0) {
         if (revoltCivID <= 0) {
            for (int var32 = 1; var32 < CFG.game.getCivsSize(); var32++) {
               if (CFG.game.getCiv(var32).getIdeologyID() == CFG.ideologiesManager.REBELS_ID && CFG.game.getCiv(var32).getNumOfProvinces() == 0) {
                  revoltCivID = var32;
               }
            }
         }

         try {
            this.spawnRevolutionInProvinceID(nCivID, revoltCivID, tempSorted.get(0), tempSorted, nOverMin);
         } catch (IndexOutOfBoundsException var25) {
            CFG.exceptionStack(var25);
         } catch (StackOverflowError var26) {
            CFG.exceptionStack(var26);
         }
      }
   }

   public final String getSpawnRebels_CivRebelsTag(int nCivID) {
      return CFG.game.getCiv(nCivID).getCivTag().lastIndexOf(95) > 0
         ? CFG.game.getCiv(nCivID).getCivTag().substring(0, CFG.game.getCiv(nCivID).getCivTag().lastIndexOf(95) + 2)
         : CFG.game.getCiv(nCivID).getCivTag();
   }

   public final int getSpawnRebels_CivRebelsTag_GetID(int nCivID) {
      if (CFG.game.getCiv(nCivID).getCivTag().lastIndexOf(95) > 0) {
         try {
            return Integer.parseInt(CFG.game.getCiv(nCivID).getCivTag().substring(CFG.game.getCiv(nCivID).getCivTag().lastIndexOf(95) + 2));
         } catch (NumberFormatException var3) {
            CFG.exceptionStack(var3);
         }
      }

      return 0;
   }

   public final void updateMetCivilization(int nProvinceID) {
      try {
         if (CFG.FOG_OF_WAR == 2) {
            for (int i = 0; i < CFG.game.getPlayersSize(); i++) {
               if (CFG.game.getPlayer(i).getMetProvince(nProvinceID)) {
                  CFG.game.getPlayer(i).setMetCivilization(CFG.game.getProvince(nProvinceID).getCivID(), true);
               }
            }
         }
      } catch (IndexOutOfBoundsException var3) {
      } catch (NullPointerException var4) {
      }
   }

   public final void spawnRevolutionInProvinceID(int nCivID, int nRebelsCivID, int nProvinceID, List<Integer> nProvinces, List<Integer> nOverMin) {
      String nRevTag = CFG.ideologiesManager.getRealTag(CFG.game.getCiv(nCivID).getCivTag())
         + CFG.ideologiesManager.getIdeology(CFG.ideologiesManager.REBELS_ID).getExtraTag();
      int nLastID = -1;

      for (int i3 = 1; i3 < CFG.game.getCivsSize(); i3++) {
         int tID;
         if (this.getSpawnRebels_CivRebelsTag(i3).equals(nRevTag) && (tID = this.getSpawnRebels_CivRebelsTag_GetID(i3)) >= nLastID) {
            nLastID = tID + 1;
         }
      }

      if (nLastID >= 0) {
         nRevTag = CFG.ideologiesManager.getRealTag(CFG.game.getCiv(nCivID).getCivTag())
            + CFG.ideologiesManager.getIdeology(CFG.ideologiesManager.REBELS_ID).getExtraTag()
            + nLastID;
      }

      ArrayList<Province_Army> tempArmies = new ArrayList<>();
      ArrayList<Integer> tempArmiesProvinces = new ArrayList<>();
      if (CFG.game.getProvince(nProvinceID).getArmy(0) > 0) {
         tempArmies.add(new Province_Army(nCivID, CFG.game.getProvince(nProvinceID).getArmy(0), nProvinceID));
         tempArmiesProvinces.add(nProvinceID);
         CFG.game.getProvince(nProvinceID).updateArmy(0);
      }

      if (nRebelsCivID <= 0) {
         CFG.game.createScenarioAddCivilization(nRevTag, nProvinceID, false, false, true);

         for (int i2 = CFG.game.getCivsSize() - 1; i2 > 0; i2--) {
            if (CFG.game.getCiv(i2).getIdeologyID() == CFG.ideologiesManager.REBELS_ID && CFG.game.getCiv(i2).getCivTag().equals(nRevTag)) {
               nRebelsCivID = i2;
               break;
            }
         }

         this.spawnRevolution_UpdateCivData(nCivID, nRebelsCivID, nRevTag);
      } else if (!CFG.game.getCiv(nRebelsCivID).getCivTag().equals(nRevTag)) {
         this.spawnRevolution_UpdateCivData(nCivID, nRebelsCivID, nRevTag);
      } else {
         this.spawnRevolution_UpdateCivData(nCivID, nRebelsCivID, nRevTag);
      }

      CFG.game.getCiv(nRebelsCivID).civGameData.iRevolt_SinceTurn = Game_Calendar.TURN_ID;
      CFG.game.getCiv(nRebelsCivID).civGameData.iRevolt_LastTurnLostProvince = Game_Calendar.TURN_ID;
      CFG.game.getCiv(nRebelsCivID).setCapitalProvinceID(nProvinceID);
      CFG.game.getProvince(nProvinceID).setIsCapital(true);
      if (CFG.game.getProvince(CFG.game.getCiv(nRebelsCivID).getCapitalProvinceID()).getCitiesSize() > 0) {
         for (int i2x = 0; i2x < CFG.game.getProvince(CFG.game.getCiv(nRebelsCivID).getCapitalProvinceID()).getCitiesSize(); i2x++) {
            if (CFG.game.getProvince(CFG.game.getCiv(nRebelsCivID).getCapitalProvinceID()).getCity(i2x).getCityLevel() == CFG.getEditorCityLevel(0)) {
               CFG.game.getProvince(CFG.game.getCiv(nRebelsCivID).getCapitalProvinceID()).getCity(i2x).setCityLevel(CFG.getEditorCityLevel(1));
            }
         }

         CFG.game.getProvince(CFG.game.getCiv(nRebelsCivID).getCapitalProvinceID()).getCity(0).setCityLevel(CFG.getEditorCityLevel(0));
      }

      CFG.game.getProvince(nProvinceID).setCivID(nRebelsCivID, true);
      CFG.game.getProvince(nProvinceID).setTrueOwnerOfProvince(nCivID);
      this.updateProvinceAfterRevolution(nProvinceID);
      CFG.game.getProvince(nProvinceID).updateArmy(nRebelsCivID, 0);
      CFG.game.getCiv(nRebelsCivID).setNumOfUnits(0);
      this.spawnRevolutionaryArmy(nProvinceID, nCivID, nRebelsCivID);
      CFG.game.getCiv(nCivID).civGameData.civilization_Diplomacy_GameData.messageBox.addMessage(new Message_Revolt(nRebelsCivID, nProvinceID));
      int mainCivProvinces = 0;

      for (int i4 = 0; i4 < CFG.game.getCiv(nCivID).getNumOfProvinces(); i4++) {
         if (CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i4)).getCivID()
            == CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i4)).getTrueOwnerOfProvince()) {
            mainCivProvinces++;
         }
      }

      int revelsMaxPercOfProvinces = (int)Math.ceil(mainCivProvinces * (0.12F + CFG.oR.nextInt(15) / 100.0F));
      ArrayList<Integer> tempRevCivsIDs = new ArrayList<>();

      for (int i5 = 0; i5 < CFG.game.getProvince(nProvinceID).getCore().getCivsSize(); i5++) {
         if (CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCore().getCivID(i5)).getNumOfProvinces() == 0) {
            tempRevCivsIDs.add(CFG.game.getProvince(nProvinceID).getCore().getCivID(i5));
         }
      }

      ArrayList<Integer> joinProvinces = new ArrayList<>();

      for (int j2 = 0; j2 < CFG.game.getProvince(nProvinceID).getNeighboringProvincesSize(); j2++) {
         if (!CFG.game.getProvince(CFG.game.getProvince(nProvinceID).getNeighboringProvinces(j2)).getIsCapital()
            && CFG.game.getProvince(CFG.game.getProvince(nProvinceID).getNeighboringProvinces(j2)).getCivID() == nCivID
            && this.getModifiedRevolutionsRisk(CFG.game.getProvince(nProvinceID).getNeighboringProvinces(j2)) > 0.16F) {
            joinProvinces.add(CFG.game.getProvince(nProvinceID).getNeighboringProvinces(j2));
         }
      }

      if (revelsMaxPercOfProvinces <= joinProvinces.size() + 1) {
         for (int i6 = joinProvinces.size() - 1; i6 >= 0; i6--) {
            boolean bRemove = true;

            for (int j = 0; j < tempRevCivsIDs.size(); j++) {
               if (CFG.game.getProvince(joinProvinces.get(i6)).getCore().getHaveACore(tempRevCivsIDs.get(j))) {
                  bRemove = false;
               }
            }

            if (bRemove) {
               joinProvinces.remove(i6);
               if (revelsMaxPercOfProvinces >= joinProvinces.size() + 1) {
                  break;
               }
            }
         }

         if (revelsMaxPercOfProvinces <= joinProvinces.size() + 1) {
            while (joinProvinces.size() > 0 && revelsMaxPercOfProvinces <= joinProvinces.size() + 1) {
               joinProvinces.remove(CFG.oR.nextInt(joinProvinces.size()));
            }
         }
      } else {
         ArrayList<Integer> tempPossibleToAdd = new ArrayList<>();

         for (int i7 = 0; i7 < joinProvinces.size(); i7++) {
            for (int jx = 0; jx < CFG.game.getProvince(joinProvinces.get(i7)).getNeighboringProvincesSize(); jx++) {
               for (int k = 0; k < tempRevCivsIDs.size(); k++) {
                  if (CFG.game.getProvince(CFG.game.getProvince(joinProvinces.get(i7)).getNeighboringProvinces(jx)).getCivID() == nCivID
                     && CFG.game
                        .getProvince(CFG.game.getProvince(joinProvinces.get(i7)).getNeighboringProvinces(jx))
                        .getCore()
                        .getHaveACore(tempRevCivsIDs.get(k))) {
                     boolean canBeAdded = CFG.game.getProvince(joinProvinces.get(i7)).getNeighboringProvinces(jx) != nProvinceID;
                     if (canBeAdded) {
                        for (int o = 0; o < joinProvinces.size(); o++) {
                           if (CFG.game.getProvince(joinProvinces.get(i7)).getNeighboringProvinces(jx) == joinProvinces.get(o)) {
                              canBeAdded = false;
                              break;
                           }
                        }

                        if (canBeAdded) {
                           for (int var37 = 0; var37 < tempPossibleToAdd.size(); var37++) {
                              if (tempPossibleToAdd.get(var37) == CFG.game.getProvince(joinProvinces.get(i7)).getNeighboringProvinces(jx)) {
                                 canBeAdded = false;
                                 break;
                              }
                           }

                           if (canBeAdded) {
                              tempPossibleToAdd.add(CFG.game.getProvince(joinProvinces.get(i7)).getNeighboringProvinces(jx));
                           }
                        }
                     }
                  }
               }
            }
         }

         ArrayList sortedPossibleToAdd = new ArrayList();

         while (tempPossibleToAdd.size() > 0) {
            int tBest = 0;

            for (int i8 = 1; i8 < tempPossibleToAdd.size(); i8++) {
               if (CFG.game.getProvince(tempPossibleToAdd.get(i8)).getPopulationData().getPopulation()
                     * CFG.game.getProvince(tempPossibleToAdd.get(i8)).getRevolutionaryRisk()
                  > CFG.game.getProvince(tempPossibleToAdd.get(tBest)).getPopulationData().getPopulation()
                     * CFG.game.getProvince(tempPossibleToAdd.get(tBest)).getRevolutionaryRisk()) {
                  tBest = i8;
               }
            }

            sortedPossibleToAdd.add(tempPossibleToAdd.get(tBest));
            tempPossibleToAdd.remove(tBest);
         }

         for (int i9 = 0; i9 < sortedPossibleToAdd.size() && revelsMaxPercOfProvinces > joinProvinces.size() + 1; i9++) {
            joinProvinces.add((Integer)sortedPossibleToAdd.get(i9));
         }
      }

      for (int i = 0; i < joinProvinces.size(); i++) {
         if (CFG.game.getProvince(joinProvinces.get(i)).getCivID() != nRebelsCivID) {
            if (CFG.game.getProvince(joinProvinces.get(i)).getArmy(0) > 0) {
               tempArmies.add(new Province_Army(nCivID, CFG.game.getProvince(joinProvinces.get(i)).getArmy(0), joinProvinces.get(i)));
               tempArmiesProvinces.add(joinProvinces.get(i));
               CFG.game.getProvince(joinProvinces.get(i)).updateArmy(0);
            }

            CFG.game.getProvince(joinProvinces.get(i)).setCivID(nRebelsCivID, true);
            this.spawnRevolutionaryArmy(joinProvinces.get(i), nCivID, nRebelsCivID);
            this.updateProvinceAfterRevolution(joinProvinces.get(i));
         }
      }

      CFG.game.getCiv(nRebelsCivID).buildCivPersonality();

      for (int var23 = 0; var23 < tempArmies.size(); var23++) {
         CFG.game.getProvince(tempArmiesProvinces.get(var23)).updateArmy(tempArmies.get(var23).getCivID(), tempArmies.get(var23).getArmy());
         CFG.game
            .getCiv(tempArmies.get(var23).getCivID())
            .newMove(tempArmiesProvinces.get(var23), tempArmiesProvinces.get(var23), tempArmies.get(var23).getArmy(), true);

         for (int a = CFG.game.getProvince(tempArmiesProvinces.get(var23)).getCivsSize() - 1; a >= 0; a--) {
            if (CFG.game.getProvince(tempArmiesProvinces.get(var23)).getCivID(a) != nCivID
               && CFG.game.getProvince(tempArmiesProvinces.get(var23)).getCivID(a) != nRebelsCivID) {
               this.accessLost_MoveArmyToClosetsProvince(CFG.game.getProvince(tempArmiesProvinces.get(var23)).getCivID(a), tempArmiesProvinces.get(var23));
            }
         }
      }
   }

   public final void spawnRevolution_UpdateCivData(int nCivID, int nRebelsCivID, String nRevTag) {
      CFG.game.getCiv(nRebelsCivID).setCivTag(nRevTag);
      Color nColor = CFG.getRandomColor();
      CFG.game.getCiv(nRebelsCivID).setR((int)(nColor.r * 255.0F));
      CFG.game.getCiv(nRebelsCivID).setG((int)(nColor.g * 255.0F));
      CFG.game.getCiv(nRebelsCivID).setB((int)(nColor.b * 255.0F));
      if (CFG.game.getCiv(nRebelsCivID).getMoney() < 100L) {
         CFG.game.getCiv(nRebelsCivID).setMoney(100L);
      }

      CFG.game.getCiv(nRebelsCivID).setCivName(CFG.langManager.get("Rebels"));
      CFG.game.getCiv(nRebelsCivID).setTechnologyLevel(CFG.game.getCiv(nCivID).getTechnologyLevel() * (0.575F + CFG.oR.nextInt(25) / 100.0F));
      CFG.game.declareWar(nRebelsCivID, nCivID, true);
   }

   public final void spawnRevolutionaryArmy(int nProvinceID, int nCivID, int nRebelsCivID) {
      int revolutionaryPop = 10 + CFG.oR.nextInt(50);

      for (int i = 0; i < CFG.game.getProvince(nProvinceID).getPopulationData().getNationalitiesSize(); i++) {
         if (CFG.game.getProvince(nProvinceID).getPopulationData().getCivID(i) == nCivID) {
            revolutionaryPop += (int)(CFG.game.getProvince(nProvinceID).getPopulationData().getPopulationID(i) * 0.00125F);
         } else if (CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getPopulationData().getCivID(i)).getNumOfProvinces() == 0) {
            revolutionaryPop += (int)(CFG.game.getProvince(nProvinceID).getPopulationData().getPopulationID(i) * (0.0125F + CFG.oR.nextInt(35) / 1000.0F));
         } else if (CFG.game.getCivsAtWar(CFG.game.getProvince(nProvinceID).getPopulationData().getCivID(i), nCivID)) {
            revolutionaryPop += (int)(CFG.game.getProvince(nProvinceID).getPopulationData().getPopulationID(i) * 0.0145F);
         } else {
            revolutionaryPop += (int)(CFG.game.getProvince(nProvinceID).getPopulationData().getPopulationID(i) * 8.5E-4F);
         }
      }

      int nArmy = (int)(
         revolutionaryPop
            * Math.min(
               0.35F
                  + 0.5F * CFG.game.getProvince(nProvinceID).saveProvinceData.iNumOfRevolutions
                  + 0.095F * CFG.game.getCiv(nCivID).civGameData.iNumOfRevolutions
                  + CFG.oR.nextInt(400) / 1000.0F,
               10.0F
            )
            * Game_Calendar.POWERREBELS
            * 2.0F
      );
      CFG.game.getProvince(nProvinceID).updateArmy(nRebelsCivID, nArmy);
      CFG.game.getCiv(nRebelsCivID).setNumOfUnits(CFG.game.getCiv(nRebelsCivID).getNumOfUnits() + nArmy);
      CFG.game.getProvince(nProvinceID).saveProvinceData.iNumOfRevolutions++;
   }

   public final int getSpawnRevolutionaryArmy_MAX(int nProvinceID, int nCivID) {
      int revolutionaryPop = 10 + CFG.oR.nextInt(50);

      for (int i = 0; i < CFG.game.getProvince(nProvinceID).getPopulationData().getNationalitiesSize(); i++) {
         if (CFG.game.getProvince(nProvinceID).getPopulationData().getCivID(i) == nCivID) {
            revolutionaryPop += (int)(CFG.game.getProvince(nProvinceID).getPopulationData().getPopulationID(i) * 0.00125F);
         } else if (CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getPopulationData().getCivID(i)).getNumOfProvinces() == 0) {
            revolutionaryPop += (int)(CFG.game.getProvince(nProvinceID).getPopulationData().getPopulationID(i) * 0.0475F);
         } else if (CFG.game.getCivsAtWar(CFG.game.getProvince(nProvinceID).getPopulationData().getCivID(i), nCivID)) {
            revolutionaryPop += (int)(CFG.game.getProvince(nProvinceID).getPopulationData().getPopulationID(i) * 0.0145F);
         } else {
            revolutionaryPop += (int)(CFG.game.getProvince(nProvinceID).getPopulationData().getPopulationID(i) * 8.5E-4F);
         }
      }

      return (int)(revolutionaryPop * 0.17199999F) + 2;
   }

   public final void updateProvinceAfterRevolution(int nProvinceID) {
      CFG.game
         .getProvince(nProvinceID)
         .setRevolutionaryRisk(CFG.game.getProvince(nProvinceID).getRevolutionaryRisk() * (0.42241F + CFG.oR.nextInt(400) / 1000.0F));
      CFG.game.getProvince(nProvinceID).setHappiness(CFG.game.getProvince(nProvinceID).getHappiness() * (1.075F + CFG.oR.nextInt(52) / 100.0F));
      CFG.game.getProvince(nProvinceID).setEconomy((int)(CFG.game.getProvince(nProvinceID).getEconomy() * (0.98244F - CFG.oR.nextInt(78) / 1000.0F)));
      CFG.game
         .getProvince(nProvinceID)
         .setDevelopmentLevel(CFG.game.getProvince(nProvinceID).getDevelopmentLevel() * (0.93244F - CFG.oR.nextInt(184) / 1000.0F));
      if (CFG.game.getProvince(nProvinceID).getLevelOfLibrary() > 0 && CFG.oR.nextInt(100) < 64) {
         CFG.game.getProvince(nProvinceID).setLevelOfLibrary(0);
      }

      this.updateMetCivilization(nProvinceID);
   }

   public final void moveRegroupArmy() {
      for (int i = 1; i < CFG.game.getCivsSize(); i++) {
         CFG.game.getCiv(i).moveRegroupArmy();
      }
   }

   public final void migrateFromTo(int nCivID, int fromProvinceID, int toProvinceID) {
      try {
         if (CFG.game.getProvince(fromProvinceID).getCivID() == nCivID && Game.uncivilizedCanMigrate(toProvinceID, nCivID)) {
            ArrayList<Integer> tCivs = new ArrayList<>();
            ArrayList<Integer> tArmies = new ArrayList<>();

            for (int j2 = 0; j2 < CFG.game.getProvince(fromProvinceID).getCivsSize(); j2++) {
               tCivs.add(CFG.game.getProvince(fromProvinceID).getCivID(j2));
               tArmies.add(CFG.game.getProvince(fromProvinceID).getArmy(j2));
            }

            int minus_A = CFG.game.getProvince(fromProvinceID).saveProvinceData.iNumOfTurnsWithBalanceOnMinus;
            CivFestival tFestival = CFG.game.getCiv(nCivID).isFestivalOrganized_GET(fromProvinceID);
            if (tFestival != null) {
               CFG.game.getCiv(nCivID).removeFestival_ProvinceID(fromProvinceID);
            }

            CivFestival tAssimilate;
            if ((tAssimilate = CFG.game.getCiv(nCivID).isAssimilateOrganized_GET(fromProvinceID)) != null) {
               CFG.game.getCiv(nCivID).removeAssimilate_ProvinceID(fromProvinceID);
            }

            CivInvest tInvest;
            if ((tInvest = CFG.game.getCiv(nCivID).isInvestOrganized_GET(fromProvinceID)) != null) {
               CFG.game.getCiv(nCivID).removeInvest_ProvinceID(fromProvinceID);
            }

            int tNeutral = CFG.game.getProvince(toProvinceID).getArmy(0);

            for (int j = CFG.game.getProvince(fromProvinceID).getCivsSize() - 1; j >= 0; j--) {
               CFG.game.getProvince(fromProvinceID).updateArmy(CFG.game.getProvince(fromProvinceID).getCivID(j), 0);
            }

            for (int var20 = CFG.game.getProvince(toProvinceID).getCivsSize() - 1; var20 >= 0; var20--) {
               CFG.game.getProvince(toProvinceID).updateArmy(CFG.game.getProvince(toProvinceID).getCivID(var20), 0);
            }

            CFG.game.getProvince(fromProvinceID).setTrueOwnerOfProvince(nCivID);
            CFG.game.getProvince(toProvinceID).setCivID(nCivID, false);
            CFG.game.getProvince(fromProvinceID).setTrueOwnerOfProvince(0);
            CFG.game.getProvince(fromProvinceID).setCivID(0, false);
            CFG.game.getProvince(toProvinceID).saveProvinceData.iNumOfTurnsWithBalanceOnMinus = minus_A;
            if (CFG.game.getCiv(nCivID).getCapitalProvinceID() == fromProvinceID) {
               CFG.game.getProvince(toProvinceID).setIsCapital(true);
               CFG.game.getProvince(fromProvinceID).setIsCapital(false);
               CFG.game.getCiv(nCivID).setCapitalProvinceID(toProvinceID);

               try {
                  CFG.game.getProvince(fromProvinceID).getCity(0).setCityLevel(CFG.getEditorCityLevel(3));
               } catch (IndexOutOfBoundsException var17) {
               }

               try {
                  CFG.game.getProvince(toProvinceID).getCity(0).setCityLevel(CFG.getEditorCityLevel(0));
               } catch (IndexOutOfBoundsException var16) {
               }
            }

            CFG.game.getProvince(fromProvinceID).setDrawCities(false);
            CFG.game.getProvince(toProvinceID).setDrawCities(true);
            Province_Population tempD = CFG.game.getProvince(toProvinceID).getPopulationData();
            CFG.game.getProvince(toProvinceID).setPopulationData(CFG.game.getProvince(fromProvinceID).getPopulationData());
            CFG.game.getProvince(fromProvinceID).setPopulationData(tempD);
            int tData = CFG.game.getProvince(toProvinceID).getEconomy();
            CFG.game.getProvince(toProvinceID).setEconomy(CFG.game.getProvince(fromProvinceID).getEconomy());
            CFG.game.getProvince(fromProvinceID).setEconomy(tData);
            float fData = CFG.game.getProvince(toProvinceID).getHappiness();
            CFG.game.getProvince(toProvinceID).setHappiness(CFG.game.getProvince(fromProvinceID).getHappiness());
            CFG.game.getProvince(fromProvinceID).setHappiness(fData);
            fData = CFG.game.getProvince(toProvinceID).getDevelopmentLevel();
            CFG.game.getProvince(toProvinceID).setDevelopmentLevel(CFG.game.getProvince(fromProvinceID).getDevelopmentLevel());
            CFG.game.getProvince(fromProvinceID).setDevelopmentLevel(fData);
            if (tFestival != null) {
               tFestival.iProvinceID = toProvinceID;
               CFG.game.getCiv(nCivID).addFestival(tFestival);
            }

            if (tAssimilate != null) {
               tAssimilate.iProvinceID = toProvinceID;
               CFG.game.getCiv(nCivID).addAssimilate(tAssimilate);
            }

            if (tInvest != null) {
               tInvest.iProvinceID = toProvinceID;
               CFG.game.getCiv(nCivID).addInvest(tInvest);
            }

            for (int j4 = 0; j4 < tCivs.size(); j4++) {
               CFG.game.getProvince(toProvinceID).updateArmy(tCivs.get(j4), tArmies.get(j4));
            }

            CFG.game.getProvince(fromProvinceID).updateArmy(0, tNeutral);
            CFG.game.getProvince(toProvinceID).iIncome_Taxation = CFG.game.getProvince(fromProvinceID).iIncome_Taxation;
            CFG.game.getProvince(toProvinceID).iIncome_Production = CFG.game.getProvince(fromProvinceID).iIncome_Production;
            CFG.game.getProvince(toProvinceID).iAdministrationCost = CFG.game.getProvince(fromProvinceID).iAdministrationCost;
            CFG.game.getProvince(fromProvinceID).getCore().resetOwnership(nCivID);
            CFG.game.getProvince(toProvinceID).getCore().resetOwnership(nCivID);
            CFG.game.getProvince(fromProvinceID).updateDrawArmy();
            CFG.game.getProvince(toProvinceID).updateDrawArmy();
            TechnologyManager.updateCivs_ResearchProgress_Migrate(nCivID, toProvinceID);
         }
      } catch (IndexOutOfBoundsException var18) {
         CFG.exceptionStack(var18);
      } catch (NullPointerException var19) {
         CFG.exceptionStack(var19);
      }
   }

   public final boolean turnMoves_IsACombatMove(int nCivID, int toProvinceID) {
      if (nCivID == CFG.game.getProvince(toProvinceID).getCivID()
         || nCivID == CFG.game.getCiv(CFG.game.getProvince(toProvinceID).getCivID()).getPuppetOfCivID()
         || CFG.game.getProvince(toProvinceID).getCivID() == CFG.game.getCiv(nCivID).getPuppetOfCivID()
         || CFG.game.getMilitaryAccess(nCivID, CFG.game.getProvince(toProvinceID).getCivID()) != 0
         || CFG.game.getCiv(nCivID).getAllianceID() > 0
            && CFG.game.getCiv(nCivID).getAllianceID() == CFG.game.getCiv(CFG.game.getProvince(toProvinceID).getCivID()).getAllianceID()) {
         for (int i = CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivsSize() - 1; i > 0; i--) {
            if (CFG.game
               .getCivsAtWar(this.currentMoveUnits.getCivID(0), CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(i))) {
               return true;
            }
         }

         return false;
      } else {
         return true;
      }
   }

   public final void turnMoves_UpdatePlayersFogOfWar(int nCivID) {
      if (CFG.game.getCiv(nCivID).getControlledByPlayer() && CFG.PLAYER_TURNID != CFG.game.getPlayerID_ByCivID(nCivID)) {
         CFG.PLAYER_TURNID = CFG.game.getPlayerID_ByCivID(nCivID);
         if (CFG.FOG_OF_WAR > 0) {
            for (int i = 0; i < CFG.game.getProvincesSize(); i++) {
               CFG.game.getProvince(i).updateDrawArmy();
            }
         }

         if (this.getNumOfPlayersInGame() > 1) {
            CFG.menuManager.updateInGame_TOP_All_NextTurnActions(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
         }
      }
   }

   private final void turnMoves_MoveCurrentArmy() {
      CFG.menuManager.setVisible_InGame_Dices(false);
      if (this.currentMoveUnits.getCivID(0) != CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID()
         && this.currentMoveUnits.getCivID(0)
            != CFG.game.getCiv(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID()).getPuppetOfCivID()
         && CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID()
            != CFG.game.getCiv(this.currentMoveUnits.getCivID(0)).getPuppetOfCivID()
         && !CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getSeaProvince()) {
         if (CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID() > 0
            && (
               CFG.game
                     .getCivsInAlliance(
                        this.currentMoveUnits.getCivID(0), CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID()
                     )
                  || CFG.game
                        .getMilitaryAccess(
                           this.currentMoveUnits.getCivID(0), CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID()
                        )
                     > 0
            )) {
            for (int i = CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivsSize() - 1; i > 0; i--) {
               int losses;
               if (CFG.game
                     .getCivsAtWar(this.currentMoveUnits.getCivID(0), CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(i))
                  && (
                        losses = Math.min(
                           CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getArmy(i),
                           this.currentMoveUnits.getMoveUnits_TotalNumOfUnits()
                        )
                     )
                     > 0) {
                  int tWarID = CFG.game
                     .getWarID(this.currentMoveUnits.getCivID(0), CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(i));
                  int tempArmy = Math.min(losses, CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getArmy(i));
                  if (tWarID >= 0) {
                     CFG.game.getWar(tWarID).addCasualties(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(i), tempArmy);
                  }

                  CFG.game
                     .getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID())
                     .updateArmy(
                        CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(i),
                        CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getArmy(i) - losses
                     );

                  for (int j = 0; j < this.currentMoveUnits.getMoveUnitsSize(); j++) {
                     if (this.currentMoveUnits.getMoveUnits(j).getNumOfUnits() > 0) {
                        tempArmy = Math.min(this.currentMoveUnits.getMoveUnits(j).getNumOfUnits(), losses);
                        if (tWarID >= 0) {
                           CFG.game.getWar(tWarID).addCasualties(this.currentMoveUnits.getCivID(j), tempArmy);
                        }

                        this.currentMoveUnits.getMoveUnits(j).setNumOfUnits(Math.max(this.currentMoveUnits.getMoveUnits(j).getNumOfUnits() - losses, 0));
                        CFG.game
                           .getProvince(this.currentMoveUnits.getMoveUnits(j).getFromProvinceID())
                           .updateArmy(
                              this.currentMoveUnits.getCivID(j),
                              CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(j).getFromProvinceID()).getArmyCivID(this.currentMoveUnits.getCivID(j))
                                 - losses
                           );
                        if ((losses -= tempArmy) <= 0) {
                           break;
                        }
                     }
                  }
               }
            }

            this.turnMoves_MoveCurrentArmy_JustMove();
         } else {
            try {
               Gdx.app.log("AoC", "ATTACK: 111");
               if (CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID() != 0
                  && !CFG.game
                     .getCivsAtWar(this.currentMoveUnits.getCivID(0), CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID())
                  )
                {
                  int tNumOfCivs = 1;

                  for (int c = 1; c < this.currentMoveUnits.getMoveUnitsSize(); c++) {
                     if (this.currentMoveUnits.getCivID(0) != this.currentMoveUnits.getCivID(c)) {
                        tNumOfCivs++;
                        break;
                     }
                  }

                  if (tNumOfCivs == 1) {
                     this.turnMoves_MoveCurrentArmy_JustMove();
                     this.currentMoveUnits = null;
                     return;
                  }

                  if (!CFG.game
                     .isAlly(this.currentMoveUnits.getCivID(0), CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID())) {
                     CFG.game
                        .declareWar(
                           this.currentMoveUnits.getCivID(0), CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(), false
                        );
                  }
               }

               if (this.SHOW_REPORT) {
                  CFG.reportData = new Report_Data();
                  CFG.reportData.iBattleOfProvinceID = this.currentMoveUnits.getMoveUnits(0).getToProvinceID();
               }

               Gdx.app.log("AoC", "ATTACK: 222");
               int tempNumOfUnits = 0;

               for (int ix = 0; ix < this.currentMoveUnits.getMoveUnitsSize(); ix++) {
                  tempNumOfUnits += this.currentMoveUnits.getMoveUnits(ix).getNumOfUnits();
               }

               float f = 1.0F;
               if (this.currentMoveUnits.getWarFieldWidth() <= tempNumOfUnits) {
                  f = this.currentMoveUnits.getWarFieldWidth() / tempNumOfUnits;
               }

               int tempPopulationBefore = CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getPopulationData().getPopulation();
               int tempEconomyBefore = CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getEconomy();
               if (CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID() == 0) {
                  this.updatePopulationLosses(
                     this.currentMoveUnits.getMoveUnits(0).getToProvinceID(),
                     (int)Math.min(
                        tempNumOfUnits * 0.0375F,
                        CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getPopulationData().getPopulation() * 0.0025F
                     )
                  );
                  CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).updateProvinceEconomyLosses(tempNumOfUnits, 0.0575F);
               } else {
                  CFG.game
                     .getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID())
                     .updateProvincePopulationLosses(
                        tempNumOfUnits,
                        0.0565F * CFG.game.getCiv(this.currentMoveUnits.getCivID(0)).getTechnologyLevel() / 3.0F * Game_Calendar.LOSSESINWAR * 7.0F
                     );
                  CFG.game
                     .getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID())
                     .updateProvinceEconomyLosses(tempNumOfUnits, 0.0575F * Game_Calendar.LOSSESINWAR);
               }

               Gdx.app.log("AoC", "ATTACK: 333");
               if (this.SHOW_REPORT) {
                  CFG.reportData.iPopulationLosses = tempPopulationBefore
                     - CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getPopulationData().getPopulation();
                  CFG.reportData.iEconomyLosses = tempEconomyBefore
                     - CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getEconomy();
                  this.MDP = (int)Math.min(tempNumOfUnits * 0.05 / 150.0, 2.0);
                  float techMMap = 0.0205F + 0.0055F * CFG.game.getCiv(this.currentMoveUnits.getCivID(0)).getTechnologyLevel();
                  this.MAP = (int)Math.min(
                     (tempNumOfUnits * 0.0205F + 0.0055F * CFG.game.getCiv(this.currentMoveUnits.getCivID(0)).getTechnologyLevel()) / 200.0F, 2.0F
                  );
                  CFG.reportData.iMilitaryDefendersPoints = this.MDP;
                  CFG.reportData.iMilitaryAttackPoints = this.MAP;
               }

               CFG.game.getCiv(this.currentMoveUnits.getCivID(0)).setFood(this.currentMoveUnits.getWarFieldWidth());
               int tempWarID = CFG.game
                  .getWarID(this.currentMoveUnits.getCivID(0), CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID());
               CFG.game
                  .updateWarStatistics(
                     tempWarID,
                     this.currentMoveUnits.getCivID(0),
                     CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(),
                     tempPopulationBefore - CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getPopulationData().getPopulation(),
                     tempEconomyBefore - CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getEconomy(),
                     (int)Math.min(tempNumOfUnits * 0.05 / 150.0, 2.0),
                     (int)Math.min(
                        (tempNumOfUnits * 0.0205F + 0.0055F * CFG.game.getCiv(this.currentMoveUnits.getCivID(0)).getTechnologyLevel()) / 200.0F, 2.0F
                     )
                  );
               Gdx.app.log("AoC", "ATTACK: 444");
               if (this.turnMoves_MoveCurrentArmy_AttackResult(this.currentMoveUnits.getMoveUnits(0).getToProvinceID(), tempNumOfUnits)) {
                  Gdx.app.log("AoC", "WON: 111");
                  int attackersArmy = tempNumOfUnits;
                  int defendersArmy = this.turnMoves_MoveCurrentArmy_Attack_NumOfDefeningUnits(this.currentMoveUnits.getMoveUnits(0).getToProvinceID());
                  CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).setWasAttacked(2);
                  float f1 = 3.0F;
                  if (this.currentMoveUnits.getWarFieldWidth() <= defendersArmy) {
                     f1 = this.currentMoveUnits.getWarFieldWidth() / defendersArmy;
                  }

                  Gdx.app.log("AoC", "WONBEFORE: attackersArmy: " + tempNumOfUnits);
                  Gdx.app.log("AoC", "WONBEFORE: defendersArmy: " + defendersArmy);
                  float f3 = (int)Math.ceil(Math.min(tempNumOfUnits, this.currentMoveUnits.getWarFieldWidth())) + 1;
                  f3 = Math.min(Math.max((int)((Math.min(defendersArmy, this.currentMoveUnits.getWarFieldWidth()) + 1) / f3 * 100.0F), 25), 100) / 100.0F;
                  defendersArmy = Math.max(
                     (int)Math.ceil(
                           Math.min(defendersArmy, this.currentMoveUnits.getWarFieldWidth())
                              * 3.0F
                              / this.turnMoves_MoveCurrentArmy_Attack_DefensiveModifiers(this.currentMoveUnits.getMoveUnits(0).getToProvinceID())
                              * this.turnMoves_MoveCurrentArmy_Attack_OffensiveModifiers(this.currentMoveUnits.getMoveUnits(0).getToProvinceID())
                              * f3
                        )
                        / 10,
                     0
                  );
                  Gdx.app.log("AoC", "WON: attackersArmy: " + tempNumOfUnits);
                  Gdx.app.log("AoC", "WON: defendersArmy: " + defendersArmy);
                  if (!CFG.game
                     .getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID())
                     .getArmyCanRetreat(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(0))) {
                     CFG.game
                        .updateWarStatistics_Casualties(
                           tempWarID,
                           this.currentMoveUnits.getCivID(0),
                           CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(0),
                           CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getArmy(0)
                        );
                  } else {
                     CFG.game
                        .updateWarStatistics_Casualties(
                           tempWarID,
                           this.currentMoveUnits.getCivID(0),
                           CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(0),
                           (int)(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getArmy(0) * 0.03 * f1)
                        );
                  }

                  Gdx.app.log("AoC", "WON: 222");
                  if (this.SHOW_REPORT) {
                     CFG.reportData.attackersWon = true;
                     CFG.reportData.lDefenders_IDs.add(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(0));
                     CFG.reportData.lDefenders_Armies.add(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getArmy(0));
                     if (!CFG.game
                        .getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID())
                        .getArmyCanRetreat(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(0))) {
                        CFG.reportData.lDefenders_ArmiesLost.add(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getArmy(0));
                     } else {
                        CFG.reportData
                           .lDefenders_ArmiesLost
                           .add((int)(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getArmy(0) * 0.03 * f1));
                     }

                     CFG.soundsManager.playSound(CFG.soundsManager.playMoveArmy());
                  }

                  CFG.game
                     .getCiv(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(0))
                     .setNumOfUnits(
                        (int)(
                           CFG.game.getCiv(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(0)).getNumOfUnits()
                              - CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getArmy(0) * 0.03
                        )
                     );
                  this.retreate(0);
                  CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).updateArmy(0);
                  this.destroyBuildingInBattle(this.currentMoveUnits.getMoveUnits(0).getToProvinceID());
                  Gdx.app.log("AoC", "WON: 333");
                  int i3 = 1;

                  for (int iBreak = 0;
                     i3 < CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivsSize() && iBreak < 50;
                     iBreak++
                  ) {
                     if ((int)CFG.game
                              .getCivRelation_OfCivB(
                                 this.currentMoveUnits.getCivID(0), CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(i3)
                              )
                           != -100
                        && !CFG.game
                           .isAlly(
                              this.currentMoveUnits.getCivID(0), CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(i3)
                           )) {
                        CFG.game
                           .declareWar(
                              this.currentMoveUnits.getCivID(0),
                              CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(i3),
                              false
                           );
                     }

                     CFG.game
                        .updateWarStatistics_Casualties(
                           tempWarID,
                           this.currentMoveUnits.getCivID(0),
                           CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(i3),
                           CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getArmy(i3) / 10
                        );
                     if (this.SHOW_REPORT
                        && (
                           CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getArmy(i3) > 0
                              || CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivsSize() == 1
                        )) {
                        CFG.reportData.lDefenders_IDs.add(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(i3));
                        CFG.reportData.lDefenders_Armies.add(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getArmy(i3));
                        if (!CFG.game
                           .getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID())
                           .getArmyCanRetreat(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(0))) {
                           CFG.reportData
                              .lDefenders_ArmiesLost
                              .add((int)(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getArmy(i3) * 0.03));
                        } else {
                           CFG.reportData
                              .lDefenders_ArmiesLost
                              .add((int)(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getArmy(i3) * f1) / 10);
                        }
                     }

                     CFG.game
                        .getCiv(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(i3))
                        .setNumOfUnits(
                           (
                                 CFG.game.getCiv(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(i3)).getNumOfUnits()
                                    - CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getArmy(i3)
                              )
                              / 10
                        );
                     this.retreate(i3);
                     CFG.game
                        .getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID())
                        .updateArmy(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(i3), 0);
                  }

                  Gdx.app.log("AoC", "WON: 444");
                  ArrayList<Integer> tempAttackersCivID = new ArrayList<>();
                  ArrayList<Integer> tempAttackersArmy = new ArrayList<>();

                  for (int i2 = 0; i2 < this.currentMoveUnits.getMoveUnitsSize(); i2++) {
                     boolean tempAdd = true;

                     for (int jx = 0; jx < tempAttackersCivID.size(); jx++) {
                        if (tempAttackersCivID.get(jx) == this.currentMoveUnits.getCivID(i2)) {
                           tempAdd = false;
                           tempAttackersArmy.set(jx, tempAttackersArmy.get(jx) + this.currentMoveUnits.getMoveUnits(i2).getNumOfUnits());
                           break;
                        }
                     }

                     if (tempAdd) {
                        tempAttackersCivID.add(this.currentMoveUnits.getCivID(i2));
                        tempAttackersArmy.add(this.currentMoveUnits.getMoveUnits(i2).getNumOfUnits());
                     }

                     if (!this.turnMoves_MoveCurrentArmy_AttackResult(this.currentMoveUnits.getMoveUnits(0).getToProvinceID(), tempNumOfUnits)) {
                        CFG.game
                           .getProvince(this.currentMoveUnits.getMoveUnits(i2).getFromProvinceID())
                           .updateArmy(
                              this.currentMoveUnits.getCivID(i2),
                              CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(i2).getFromProvinceID()).getArmyCivID(this.currentMoveUnits.getCivID(i2))
                                 - Math.max((int)(this.currentMoveUnits.getMoveUnits(i2).getNumOfUnits() * f) / 10, 1)
                           );
                     } else {
                        CFG.game
                           .getProvince(this.currentMoveUnits.getMoveUnits(i2).getFromProvinceID())
                           .updateArmy(
                              this.currentMoveUnits.getCivID(i2),
                              CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(i2).getFromProvinceID()).getArmyCivID(this.currentMoveUnits.getCivID(i2))
                                 - this.currentMoveUnits.getMoveUnits(i2).getNumOfUnits()
                           );
                     }
                  }

                  Gdx.app.log("AoC", "WON: 555");
                  if (tempAttackersCivID.size() > 1) {
                     Gdx.app.log("AoC", "WON: 666A");
                     int iSize = tempAttackersCivID.size();

                     for (int var56 = 0; var56 < iSize - 1; var56++) {
                        int tempBiggestArmyID = var56;

                        for (int jxx = var56 + 1; jxx < iSize; jxx++) {
                           if (tempAttackersArmy.get(tempBiggestArmyID) < tempAttackersArmy.get(jxx)) {
                              tempBiggestArmyID = jxx;
                           }
                        }

                        if (tempBiggestArmyID != var56) {
                           int tempC = tempAttackersCivID.get(var56);
                           int tempA = tempAttackersArmy.get(var56);
                           tempAttackersCivID.set(var56, tempAttackersCivID.get(tempBiggestArmyID));
                           tempAttackersArmy.set(var56, tempAttackersArmy.get(tempBiggestArmyID));
                           tempAttackersCivID.set(tempBiggestArmyID, tempC);
                           tempAttackersArmy.set(tempBiggestArmyID, tempA);
                        }
                     }

                     CFG.game
                        .updateWarStatistics_Casualties(
                           tempWarID,
                           CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(0),
                           tempAttackersCivID.get(0),
                           tempAttackersArmy.get(0)
                              - (int)Math.ceil(
                                 (float)tempAttackersArmy.get(0).intValue() / tempNumOfUnits * (tempNumOfUnits - defendersArmy * this.getFrontLength())
                              )
                        );
                     if (this.SHOW_REPORT) {
                        CFG.reportData.lAttackers_IDs.add(tempAttackersCivID.get(0));
                        CFG.reportData.lAttackers_Armies.add(tempAttackersArmy.get(0));
                        CFG.reportData
                           .lAttackers_Armies_Lost
                           .add(
                              tempAttackersArmy.get(0)
                                 - (int)Math.ceil(
                                    (float)tempAttackersArmy.get(0).intValue() / tempNumOfUnits * (tempNumOfUnits - defendersArmy * this.getFrontLength())
                                 )
                           );
                     }

                     CFG.game
                        .getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID())
                        .updateArmy(
                           (int)Math.ceil(
                              (float)tempAttackersArmy.get(0).intValue() / tempNumOfUnits * (tempNumOfUnits - defendersArmy * this.getFrontLength())
                           )
                        );
                     CFG.game
                        .getCiv(tempAttackersCivID.get(0))
                        .setNumOfUnits(
                           CFG.game.getCiv(tempAttackersCivID.get(0)).getNumOfUnits()
                              - Math.min(
                                 tempAttackersArmy.get(0),
                                 tempAttackersArmy.get(0)
                                    - (int)Math.ceil(
                                       (float)tempAttackersArmy.get(0).intValue() / tempNumOfUnits * (tempNumOfUnits - defendersArmy * this.getFrontLength())
                                    )
                              )
                        );

                     for (int var57 = 1; var57 < tempAttackersCivID.size(); var57++) {
                        if ((int)CFG.game
                                 .getCivRelation_OfCivB(
                                    tempAttackersCivID.get(var57), CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(0)
                                 )
                              != -100
                           && !CFG.game
                              .isAlly(tempAttackersCivID.get(var57), CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(0))
                           )
                         {
                           CFG.game
                              .declareWar(
                                 tempAttackersCivID.get(var57),
                                 CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(0),
                                 false
                              );
                        }

                        CFG.game
                           .updateWarStatistics_Casualties(
                              tempWarID,
                              CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(0),
                              tempAttackersCivID.get(var57),
                              tempAttackersArmy.get(var57)
                                 - (int)Math.floor(
                                    (float)tempAttackersArmy.get(var57).intValue() / tempNumOfUnits * (attackersArmy - defendersArmy * this.getFrontLength())
                                 )
                           );
                        if (this.SHOW_REPORT) {
                           CFG.reportData.lAttackers_IDs.add(tempAttackersCivID.get(var57));
                           CFG.reportData.lAttackers_Armies.add(tempAttackersArmy.get(var57));
                           CFG.reportData
                              .lAttackers_Armies_Lost
                              .add(
                                 tempAttackersArmy.get(var57)
                                    - (int)Math.floor(
                                       (float)tempAttackersArmy.get(var57).intValue()
                                          / tempNumOfUnits
                                          * (attackersArmy - defendersArmy * this.getFrontLength())
                                    )
                              );
                        }

                        CFG.game
                           .getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID())
                           .updateArmy(
                              tempAttackersCivID.get(var57),
                              (int)Math.floor(
                                 (float)tempAttackersArmy.get(var57).intValue() / tempNumOfUnits * (attackersArmy - defendersArmy * this.getFrontLength())
                              )
                           );
                        CFG.game
                           .getCiv(tempAttackersCivID.get(var57))
                           .setNumOfUnits(
                              CFG.game.getCiv(tempAttackersCivID.get(var57)).getNumOfUnits()
                                 - Math.min(
                                    tempAttackersArmy.get(var57),
                                    tempAttackersArmy.get(var57)
                                       - (int)Math.floor(
                                          (float)tempAttackersArmy.get(var57).intValue()
                                             / tempNumOfUnits
                                             * (attackersArmy - defendersArmy * this.getFrontLength())
                                       )
                                 )
                           );
                     }

                     if (CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getTrueOwnerOfProvince() > 0
                        && CFG.ideologiesManager
                           .getIdeology(
                              CFG.game.getCiv(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID()).getIdeologyID()
                           )
                           .REVOLUTIONARY) {
                        if (CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getTrueOwnerOfProvince()
                              != this.currentMoveUnits.getCivID(0)
                           && !CFG.game
                              .getCivsAtWar(
                                 CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getTrueOwnerOfProvince(),
                                 this.currentMoveUnits.getCivID(0)
                              )) {
                           int tArmy = CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getArmy(0);
                           int tArmyTrueOwner = CFG.game
                                 .getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID())
                                 .getArmyCivID(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getTrueOwnerOfProvince())
                              / 10;
                           int tTrueOwner = CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getTrueOwnerOfProvince();
                           CFG.game
                              .updateWarStatistics_ConqueredProvinces(
                                 tempWarID,
                                 CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getTrueOwnerOfProvince(),
                                 CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(0)
                              );
                           CFG.game
                              .getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID())
                              .setCivID(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getTrueOwnerOfProvince(), true);
                           CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).updateArmy(0);
                           CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).updateArmy(tTrueOwner, tArmyTrueOwner);
                           CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).updateArmy(this.currentMoveUnits.getCivID(0), tArmy);
                           if (this.currentMoveUnits.getMoveUnits(0).getToProvinceID() == CFG.game.getActiveProvinceID()) {
                              this.updateInGame_ProvinceInfo();
                           }
                        } else {
                           CFG.game
                              .updateWarStatistics_ConqueredProvinces(
                                 tempWarID,
                                 CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getTrueOwnerOfProvince(),
                                 CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(0)
                              );
                           CFG.game
                              .getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID())
                              .setCivID(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getTrueOwnerOfProvince(), true);
                           if (this.currentMoveUnits.getMoveUnits(0).getToProvinceID() == CFG.game.getActiveProvinceID()) {
                              this.updateInGame_ProvinceInfo();
                           }
                        }
                     } else if (CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getTrueOwnerOfProvince() >= 1
                        && CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getTrueOwnerOfProvince() != tempAttackersCivID.get(0)) {
                        if (!CFG.game
                              .getCivsAtWar(
                                 CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getTrueOwnerOfProvince(),
                                 CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID()
                              )
                           || !CFG.game
                                 .getCivsAreAllied(
                                    tempAttackersCivID.get(0),
                                    CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getTrueOwnerOfProvince()
                                 )
                              && CFG.game
                                    .getCiv(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getTrueOwnerOfProvince())
                                    .getPuppetOfCivID()
                                 != tempAttackersCivID.get(0)
                              && !CFG.game
                                 .getCivsAreAllied(
                                    CFG.game
                                       .getCiv(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getTrueOwnerOfProvince())
                                       .getPuppetOfCivID(),
                                    tempAttackersCivID.get(0)
                                 )
                              && CFG.game.getCiv(tempAttackersCivID.get(0)).getPuppetOfCivID()
                                 != CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getTrueOwnerOfProvince()
                              && !CFG.game
                                 .getCivsAreAllied(
                                    CFG.game.getCiv(tempAttackersCivID.get(0)).getPuppetOfCivID(),
                                    CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getTrueOwnerOfProvince()
                                 )) {
                           boolean ownerChanged = false;

                           for (int ix = 0; ix < CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCore().getCivsSize(); ix++) {
                              if (CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCore().getCivID(ix)
                                 == tempAttackersCivID.get(0)) {
                                 CFG.game
                                    .updateWarStatistics_ConqueredProvinces(
                                       tempWarID,
                                       tempAttackersCivID.get(0),
                                       CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(0)
                                    );
                                 CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).setCivID(tempAttackersCivID.get(0), true);
                                 ownerChanged = true;
                                 if (this.currentMoveUnits.getMoveUnits(0).getToProvinceID() == CFG.game.getActiveProvinceID()) {
                                    this.updateInGame_ProvinceInfo();
                                 }
                                 break;
                              }
                           }

                           if (!ownerChanged) {
                              for (int var53 = 0;
                                 var53 < CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCore().getCivsSize();
                                 var53++
                              ) {
                                 if (CFG.game
                                       .getCivsAtWar(
                                          CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCore().getCivID(var53),
                                          CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID()
                                       )
                                    && (
                                       CFG.game
                                             .getCivsAreAllied(
                                                tempAttackersCivID.get(0),
                                                CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCore().getCivID(var53)
                                             )
                                          || CFG.game
                                                .getCiv(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCore().getCivID(var53))
                                                .getPuppetOfCivID()
                                             == tempAttackersCivID.get(0)
                                          || CFG.game
                                             .getCivsAreAllied(
                                                CFG.game
                                                   .getCiv(
                                                      CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCore().getCivID(var53)
                                                   )
                                                   .getPuppetOfCivID(),
                                                tempAttackersCivID.get(0)
                                             )
                                          || CFG.game.getCiv(tempAttackersCivID.get(0)).getPuppetOfCivID()
                                             == CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCore().getCivID(var53)
                                          || CFG.game
                                             .getCivsAreAllied(
                                                CFG.game.getCiv(tempAttackersCivID.get(0)).getPuppetOfCivID(),
                                                CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCore().getCivID(var53)
                                             )
                                    )) {
                                    int tArmy2 = CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getArmy(0);
                                    int tArmyTrue = CFG.game
                                          .getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID())
                                          .getArmyCivID(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getTrueOwnerOfProvince())
                                       / 10;
                                    CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).updateArmy(0);
                                    CFG.game
                                       .getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID())
                                       .updateArmy(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getTrueOwnerOfProvince(), 0);
                                    CFG.game
                                       .updateWarStatistics_ConqueredProvinces(
                                          tempWarID,
                                          CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCore().getCivID(var53),
                                          CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(0)
                                       );
                                    CFG.game
                                       .getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID())
                                       .setCivID(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCore().getCivID(var53), true);
                                    CFG.game
                                       .getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID())
                                       .updateArmy(
                                          CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getTrueOwnerOfProvince(), tArmyTrue
                                       );
                                    CFG.game
                                       .getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID())
                                       .updateArmy(this.currentMoveUnits.getCivID(0), tArmy2);
                                    ownerChanged = true;
                                    if (this.currentMoveUnits.getMoveUnits(0).getToProvinceID() == CFG.game.getActiveProvinceID()) {
                                       this.updateInGame_ProvinceInfo();
                                    }
                                    break;
                                 }
                              }
                           }

                           if (!ownerChanged) {
                              CFG.game
                                 .updateWarStatistics_ConqueredProvinces(
                                    tempWarID,
                                    tempAttackersCivID.get(0),
                                    CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(0)
                                 );
                              CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).setCivID(tempAttackersCivID.get(0), true);
                              if (this.currentMoveUnits.getMoveUnits(0).getToProvinceID() == CFG.game.getActiveProvinceID()) {
                                 this.updateInGame_ProvinceInfo();
                              }
                           }
                        } else {
                           int tArmy = CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getArmy(0);
                           int tArmyTrue = CFG.game
                                 .getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID())
                                 .getArmyCivID(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getTrueOwnerOfProvince())
                              / 10;
                           CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).updateArmy(0);
                           CFG.game
                              .getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID())
                              .updateArmy(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getTrueOwnerOfProvince(), 0);
                           CFG.game
                              .updateWarStatistics_ConqueredProvinces(
                                 tempWarID,
                                 CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getTrueOwnerOfProvince(),
                                 CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(0)
                              );
                           CFG.game
                              .getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID())
                              .setCivID(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getTrueOwnerOfProvince(), true);
                           CFG.game
                              .getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID())
                              .updateArmy(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getTrueOwnerOfProvince(), tArmyTrue);
                           CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).updateArmy(this.currentMoveUnits.getCivID(0), tArmy);
                           if (this.currentMoveUnits.getMoveUnits(0).getToProvinceID() == CFG.game.getActiveProvinceID()) {
                              this.updateInGame_ProvinceInfo();
                           }
                        }
                     } else {
                        CFG.game
                           .updateWarStatistics_ConqueredProvinces(
                              tempWarID, tempAttackersCivID.get(0), CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(0)
                           );
                        CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).setCivID(tempAttackersCivID.get(0), true);
                        if (this.currentMoveUnits.getMoveUnits(0).getToProvinceID() == CFG.game.getActiveProvinceID()) {
                           this.updateInGame_ProvinceInfo();
                        }
                     }

                     Gdx.app.log("AoC", "WON: 777A END");
                  } else {
                     Gdx.app.log("AoC", "WON: 666B");
                     int tempDefendersArmyLeft = (int)(defendersArmy * this.getFrontLength());

                     for (int ixx = 0; ixx < this.currentMoveUnits.getMoveUnitsSize(); ixx++) {
                        CFG.game
                           .updateWarStatistics_Casualties(
                              tempWarID,
                              CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(0),
                              this.currentMoveUnits.getCivID(ixx),
                              this.currentMoveUnits.getMoveUnits(ixx).getNumOfUnits() > tempDefendersArmyLeft
                                 ? tempDefendersArmyLeft
                                 : this.currentMoveUnits.getMoveUnits(ixx).getNumOfUnits()
                           );
                        if ((tempDefendersArmyLeft -= this.currentMoveUnits.getMoveUnits(ixx).getNumOfUnits()) < 0) {
                           tempDefendersArmyLeft = 0;
                        }
                     }

                     if (this.SHOW_REPORT) {
                        tempDefendersArmyLeft = (int)(defendersArmy * this.getFrontLength());

                        for (int var51 = 0; var51 < this.currentMoveUnits.getMoveUnitsSize(); var51++) {
                           CFG.reportData.lAttackers_IDs.add(this.currentMoveUnits.getCivID(var51));
                           CFG.reportData.lAttackers_Armies.add(this.currentMoveUnits.getMoveUnits(var51).getNumOfUnits());
                           CFG.reportData
                              .lAttackers_Armies_Lost
                              .add(
                                 this.currentMoveUnits.getMoveUnits(var51).getNumOfUnits() > tempDefendersArmyLeft
                                    ? tempDefendersArmyLeft
                                    : this.currentMoveUnits.getMoveUnits(var51).getNumOfUnits()
                              );
                           if ((tempDefendersArmyLeft -= this.currentMoveUnits.getMoveUnits(var51).getNumOfUnits()) < 0) {
                              tempDefendersArmyLeft = 0;
                           }
                        }
                     }

                     Gdx.app.log("AoC", "WON: 777B");
                     Gdx.app.log("AoC", "WON: 777B: attackersArmy: " + tempNumOfUnits);
                     Gdx.app.log("AoC", "WON: 777B: defendersArmy: " + defendersArmy);
                     Gdx.app.log("AoC", "WON: 777B: FROM ARMY: " + CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getFromProvinceID()).getArmy(0));
                     Gdx.app.log("AoC", "WON: 777B: TO ARMY: " + CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getArmy(0));
                     CFG.game
                        .getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID())
                        .updateArmy((int)(tempNumOfUnits - defendersArmy * this.getFrontLength()));
                     CFG.game
                        .getCiv(this.currentMoveUnits.getCivID(0))
                        .setNumOfUnits((int)(CFG.game.getCiv(this.currentMoveUnits.getCivID(0)).getNumOfUnits() - defendersArmy * this.getFrontLength()));
                     Gdx.app.log("AoC", "WON: 777C");
                     Gdx.app.log("AoC", "WON: 777B: FROM ARMY: " + CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getFromProvinceID()).getArmy(0));
                     Gdx.app.log("AoC", "WON: 777B: TO ARMY: " + CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getArmy(0));
                     if (CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getTrueOwnerOfProvince() > 0
                        && CFG.ideologiesManager
                           .getIdeology(
                              CFG.game.getCiv(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID()).getIdeologyID()
                           )
                           .REVOLUTIONARY) {
                        if (CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getTrueOwnerOfProvince()
                              != this.currentMoveUnits.getCivID(0)
                           && !CFG.game
                              .getCivsAtWar(
                                 CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getTrueOwnerOfProvince(),
                                 this.currentMoveUnits.getCivID(0)
                              )) {
                           int tArmy = CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getArmy(0);
                           int tArmyTrueOwner = CFG.game
                                 .getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID())
                                 .getArmyCivID(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getTrueOwnerOfProvince())
                              / 10;
                           CFG.game
                              .updateWarStatistics_ConqueredProvinces(
                                 tempWarID,
                                 CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getTrueOwnerOfProvince(),
                                 CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(0)
                              );
                           CFG.game
                              .getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID())
                              .setCivID(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getTrueOwnerOfProvince(), true);
                           CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).updateArmy(tArmyTrueOwner);
                           CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).updateArmy(this.currentMoveUnits.getCivID(0), tArmy);
                           if (this.currentMoveUnits.getMoveUnits(0).getToProvinceID() == CFG.game.getActiveProvinceID()) {
                              this.updateInGame_ProvinceInfo();
                           }
                        } else {
                           CFG.game
                              .updateWarStatistics_ConqueredProvinces(
                                 tempWarID,
                                 CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getTrueOwnerOfProvince(),
                                 CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(0)
                              );
                           CFG.game
                              .getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID())
                              .setCivID(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getTrueOwnerOfProvince(), true);
                           if (this.currentMoveUnits.getMoveUnits(0).getToProvinceID() == CFG.game.getActiveProvinceID()) {
                              this.updateInGame_ProvinceInfo();
                           }
                        }
                     } else if (CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getTrueOwnerOfProvince() >= 1
                        && CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getTrueOwnerOfProvince()
                           != this.currentMoveUnits.getCivID(0)) {
                        if (!CFG.game
                              .getCivsAtWar(
                                 CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getTrueOwnerOfProvince(),
                                 CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID()
                              )
                           || !CFG.game
                                 .getCivsAreAllied(
                                    this.currentMoveUnits.getCivID(0),
                                    CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getTrueOwnerOfProvince()
                                 )
                              && CFG.game
                                    .getCiv(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getTrueOwnerOfProvince())
                                    .getPuppetOfCivID()
                                 != this.currentMoveUnits.getCivID(0)
                              && !CFG.game
                                 .getCivsAreAllied(
                                    CFG.game
                                       .getCiv(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getTrueOwnerOfProvince())
                                       .getPuppetOfCivID(),
                                    this.currentMoveUnits.getCivID(0)
                                 )
                              && CFG.game.getCiv(this.currentMoveUnits.getCivID(0)).getPuppetOfCivID()
                                 != CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getTrueOwnerOfProvince()
                              && !CFG.game
                                 .getCivsAreAllied(
                                    CFG.game.getCiv(this.currentMoveUnits.getCivID(0)).getPuppetOfCivID(),
                                    CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getTrueOwnerOfProvince()
                                 )) {
                           boolean ownerChanged = false;

                           for (int i4 = 0; i4 < CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCore().getCivsSize(); i4++) {
                              if (CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCore().getCivID(i4)
                                 == this.currentMoveUnits.getCivID(0)) {
                                 CFG.game
                                    .updateWarStatistics_ConqueredProvinces(
                                       tempWarID,
                                       this.currentMoveUnits.getCivID(0),
                                       CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(0)
                                    );
                                 CFG.game
                                    .getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID())
                                    .setCivID(this.currentMoveUnits.getCivID(0), true);
                                 ownerChanged = true;
                                 if (this.currentMoveUnits.getMoveUnits(0).getToProvinceID() == CFG.game.getActiveProvinceID()) {
                                    this.updateInGame_ProvinceInfo();
                                 }
                                 break;
                              }
                           }

                           if (!ownerChanged) {
                              for (int var81 = 0;
                                 var81 < CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCore().getCivsSize();
                                 var81++
                              ) {
                                 if (CFG.game
                                       .getCivsAtWar(
                                          CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCore().getCivID(var81),
                                          CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID()
                                       )
                                    && (
                                       CFG.game
                                             .getCivsAreAllied(
                                                this.currentMoveUnits.getCivID(0),
                                                CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCore().getCivID(var81)
                                             )
                                          || CFG.game
                                                .getCiv(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCore().getCivID(var81))
                                                .getPuppetOfCivID()
                                             == this.currentMoveUnits.getCivID(0)
                                          || CFG.game
                                             .getCivsAreAllied(
                                                CFG.game
                                                   .getCiv(
                                                      CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCore().getCivID(var81)
                                                   )
                                                   .getPuppetOfCivID(),
                                                this.currentMoveUnits.getCivID(0)
                                             )
                                          || CFG.game.getCiv(this.currentMoveUnits.getCivID(0)).getPuppetOfCivID()
                                             == CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCore().getCivID(var81)
                                          || CFG.game
                                             .getCivsAreAllied(
                                                CFG.game.getCiv(this.currentMoveUnits.getCivID(0)).getPuppetOfCivID(),
                                                CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCore().getCivID(var81)
                                             )
                                    )) {
                                    int tArmy3 = CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getArmy(0);
                                    CFG.game
                                       .updateWarStatistics_ConqueredProvinces(
                                          tempWarID,
                                          CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCore().getCivID(var81),
                                          CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(0)
                                       );
                                    CFG.game
                                       .getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID())
                                       .setCivID(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCore().getCivID(var81), true);
                                    CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).updateArmy(0);
                                    CFG.game
                                       .getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID())
                                       .updateArmy(this.currentMoveUnits.getCivID(0), tArmy3);
                                    ownerChanged = true;
                                    if (this.currentMoveUnits.getMoveUnits(0).getToProvinceID() == CFG.game.getActiveProvinceID()) {
                                       this.updateInGame_ProvinceInfo();
                                    }
                                    break;
                                 }
                              }
                           }

                           if (!ownerChanged) {
                              CFG.game
                                 .updateWarStatistics_ConqueredProvinces(
                                    tempWarID,
                                    this.currentMoveUnits.getCivID(0),
                                    CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(0)
                                 );
                              CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).setCivID(this.currentMoveUnits.getCivID(0), true);
                              if (this.currentMoveUnits.getMoveUnits(0).getToProvinceID() == CFG.game.getActiveProvinceID()) {
                                 this.updateInGame_ProvinceInfo();
                              }
                           }

                           if (CFG.game
                                 .getCiv(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getTrueOwnerOfProvince())
                                 .getCapitulationPoints()
                              >= Game_Calendar.SURRENDERLIMIT) {
                              while (
                                 CFG.game
                                       .getCiv(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getTrueOwnerOfProvince())
                                       .getNumOfProvinces()
                                    != 0
                              ) {
                                 CFG.game
                                    .getProvince(
                                       CFG.game
                                          .getCiv(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getTrueOwnerOfProvince())
                                          .getProvinceID(0)
                                    )
                                    .updateArmy(0);
                                 CFG.game
                                    .getProvince(
                                       CFG.game
                                          .getCiv(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getTrueOwnerOfProvince())
                                          .getProvinceID(0)
                                    )
                                    .setCivID(this.currentMoveUnits.getCivID(0), true);
                              }
                           }
                        } else {
                           int tArmy = CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getArmy(0);
                           CFG.game
                              .updateWarStatistics_ConqueredProvinces(
                                 tempWarID,
                                 CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getTrueOwnerOfProvince(),
                                 CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(0)
                              );
                           CFG.game
                              .getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID())
                              .setCivID(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getTrueOwnerOfProvince(), true);
                           CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).updateArmy(0);
                           CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).updateArmy(this.currentMoveUnits.getCivID(0), tArmy);
                           if (this.currentMoveUnits.getMoveUnits(0).getToProvinceID() == CFG.game.getActiveProvinceID()) {
                              this.updateInGame_ProvinceInfo();
                           }
                        }
                     } else {
                        CFG.game
                           .updateWarStatistics_ConqueredProvinces(
                              tempWarID,
                              this.currentMoveUnits.getCivID(0),
                              CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(0)
                           );
                        CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).setCivID(this.currentMoveUnits.getCivID(0), true);
                        if (this.currentMoveUnits.getMoveUnits(0).getToProvinceID() == CFG.game.getActiveProvinceID()) {
                           this.updateInGame_ProvinceInfo();
                        }
                     }

                     Gdx.app.log("AoC", "WON: 777B END");
                  }
               } else {
                  Gdx.app.log("AoC", "LOSS: 111");
                  if (this.SHOW_REPORT) {
                     CFG.reportData.attackersWon = false;
                  }

                  CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).setWasAttacked(2);
                  int defendersArmyx = this.turnMoves_MoveCurrentArmy_Attack_NumOfDefeningUnits(this.currentMoveUnits.getMoveUnits(0).getToProvinceID());
                  int numOfDefenders = this.turnMoves_MoveCurrentArmy_Attack_NumOfDefenders(this.currentMoveUnits.getMoveUnits(0).getToProvinceID());
                  if (this.currentMoveUnits.getWarFieldWidth() <= defendersArmyx) {
                     float var60 = this.currentMoveUnits.getWarFieldWidth() / defendersArmyx;
                  }

                  float f2 = Math.min(
                        this.currentMoveUnits.getWarFieldWidth(),
                        this.turnMoves_MoveCurrentArmy_Attack_NumOfDefeningUnits(this.currentMoveUnits.getMoveUnits(0).getToProvinceID())
                     )
                     + 1;
                  f2 = Math.min(Math.max((int)((Math.min(this.currentMoveUnits.getWarFieldWidth(), tempNumOfUnits) + 1) / f2 * 100.0F), 100), 100) / 100.0F;
                  int var49 = Math.max(
                     (int)Math.ceil(
                           Math.min(this.currentMoveUnits.getWarFieldWidth(), tempNumOfUnits)
                              * 3.0F
                              / this.turnMoves_MoveCurrentArmy_Attack_OffensiveModifiers(this.currentMoveUnits.getMoveUnits(0).getToProvinceID())
                              * this.turnMoves_MoveCurrentArmy_Attack_DefensiveModifiers(this.currentMoveUnits.getMoveUnits(0).getToProvinceID())
                              * f2
                        )
                        / 10,
                     0
                  );

                  for (int ixxx = 0; ixxx < this.currentMoveUnits.getMoveUnitsSize(); ixxx++) {
                     CFG.game
                        .updateWarStatistics_Casualties(
                           tempWarID,
                           CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(0),
                           this.currentMoveUnits.getCivID(ixxx),
                           this.currentMoveUnits.getMoveUnits(ixxx).getNumOfUnits() / 10
                        );
                     if (this.SHOW_REPORT) {
                        CFG.reportData.lAttackers_IDs.add(this.currentMoveUnits.getCivID(ixxx));
                        CFG.reportData.lAttackers_Armies.add(this.currentMoveUnits.getMoveUnits(ixxx).getNumOfUnits());
                        CFG.reportData.lAttackers_Armies_Lost.add(this.currentMoveUnits.getMoveUnits(ixxx).getNumOfUnits() / 10);
                     }

                     if (!this.turnMoves_MoveCurrentArmy_AttackResult(this.currentMoveUnits.getMoveUnits(0).getToProvinceID(), tempNumOfUnits)) {
                        CFG.game
                           .getProvince(this.currentMoveUnits.getMoveUnits(ixxx).getFromProvinceID())
                           .updateArmy(
                              this.currentMoveUnits.getCivID(ixxx),
                              CFG.game
                                    .getProvince(this.currentMoveUnits.getMoveUnits(ixxx).getFromProvinceID())
                                    .getArmyCivID(this.currentMoveUnits.getCivID(ixxx))
                                 - Math.max((int)(this.currentMoveUnits.getMoveUnits(ixxx).getNumOfUnits() * f) / 10, 1)
                           );
                     } else {
                        CFG.game
                           .getProvince(this.currentMoveUnits.getMoveUnits(ixxx).getFromProvinceID())
                           .updateArmy(
                              this.currentMoveUnits.getCivID(ixxx),
                              (int)(
                                 CFG.game
                                       .getProvince(this.currentMoveUnits.getMoveUnits(ixxx).getFromProvinceID())
                                       .getArmyCivID(this.currentMoveUnits.getCivID(ixxx))
                                    - this.currentMoveUnits.getMoveUnits(ixxx).getNumOfUnits() * this.getFrontLength()
                              )
                           );
                     }

                     CFG.game
                        .getCiv(this.currentMoveUnits.getCivID(ixxx))
                        .setNumOfUnits(
                           CFG.game.getCiv(this.currentMoveUnits.getCivID(ixxx)).getNumOfUnits()
                              - this.currentMoveUnits.getMoveUnits(ixxx).getNumOfUnits() / 10
                        );
                  }

                  Gdx.app.log("AoC", "LOSS: 222");
                  if (numOfDefenders <= 1) {
                     Gdx.app.log("AoC", "LOSS: 333B");
                     CFG.game
                        .updateWarStatistics_Casualties(
                           tempWarID,
                           this.currentMoveUnits.getCivID(0),
                           CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(),
                           var49
                        );
                     if (this.SHOW_REPORT) {
                        CFG.reportData.lDefenders_IDs.add(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID());
                        CFG.reportData.lDefenders_Armies.add(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getArmy(0));
                        CFG.reportData.lDefenders_ArmiesLost.add(var49 / 10);
                     }

                     CFG.game
                        .getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID())
                        .updateArmy(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getArmy(0) - var49 / 10);
                     CFG.game
                        .getCiv(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID())
                        .setNumOfUnits(
                           CFG.game.getCiv(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID()).getNumOfUnits()
                              - var49 / 10
                        );
                     Gdx.app.log("AoC", "LOSS: 333B END");
                  } else {
                     Gdx.app.log("AoC", "LOSS: 333A");
                     Gdx.app.log("AoC", "defendersArmy: " + defendersArmyx);
                     Gdx.app.log("AoC", "attackersArmy: " + var49);
                     CFG.game
                        .updateWarStatistics_Casualties(
                           tempWarID,
                           this.currentMoveUnits.getCivID(0),
                           CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(0),
                           (int)Math.ceil(
                              (float)CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getArmy(0)
                                 / defendersArmyx
                                 * var49
                                 * this.getFrontLength()
                           )
                        );
                     if (this.SHOW_REPORT) {
                        CFG.reportData.lDefenders_IDs.add(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(0));
                        CFG.reportData.lDefenders_Armies.add(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getArmy(0));
                        CFG.reportData
                           .lDefenders_ArmiesLost
                           .add(
                              (int)Math.ceil(
                                    (float)CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getArmy(0) / defendersArmyx * var49
                                 )
                                 / 10
                           );
                     }

                     Gdx.app.log("AoC", "LOSS: 333A - 111");
                     CFG.game
                        .getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID())
                        .updateArmy(
                           (int)(
                              CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getArmy(0)
                                 - Math.ceil(
                                       (float)CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getArmy(0) / defendersArmyx * var49
                                    )
                                    / 10.0
                           )
                        );
                     CFG.game
                        .getCiv(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(0))
                        .setNumOfUnits(
                           CFG.game.getCiv(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(0)).getNumOfUnits()
                              - (int)Math.ceil(
                                 (float)CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getArmy(0)
                                    / defendersArmyx
                                    * var49
                                    * this.getFrontLength()
                              )
                        );
                     Gdx.app.log("AoC", "LOSS: 333A - 222");
                     ArrayList<Integer> tempIDs = new ArrayList<>();
                     ArrayList<Integer> tempArmies = new ArrayList<>();
                     ArrayList<Integer> tempArmies_Lost = new ArrayList<>();

                     for (int ixxx = 1; ixxx < CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivsSize(); ixxx++) {
                        if (this.turnMoves_MoveCurrentArmy_Attack_NumOfDefeningUnits_IsDefender(
                           this.currentMoveUnits.getMoveUnits(0).getToProvinceID(),
                           CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(ixxx)
                        )) {
                           if ((int)CFG.game
                                    .getCivRelation_OfCivB(
                                       this.currentMoveUnits.getCivID(0),
                                       CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(ixxx)
                                    )
                                 != -100
                              && !CFG.game
                                 .isAlly(
                                    this.currentMoveUnits.getCivID(0),
                                    CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(ixxx)
                                 )) {
                              CFG.game
                                 .declareWar(
                                    this.currentMoveUnits.getCivID(0),
                                    CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(ixxx),
                                    false
                                 );
                           }

                           CFG.game
                              .updateWarStatistics_Casualties(
                                 tempWarID,
                                 this.currentMoveUnits.getCivID(0),
                                 CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(ixxx),
                                 (int)Math.floor(
                                    (float)CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getArmy(ixxx)
                                       / defendersArmyx
                                       * var49
                                       * this.getFrontLength()
                                 )
                              );
                           if (this.SHOW_REPORT) {
                              CFG.reportData.lDefenders_IDs.add(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(ixxx));
                              CFG.reportData.lDefenders_Armies.add(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getArmy(ixxx));
                              CFG.reportData
                                 .lDefenders_ArmiesLost
                                 .add(
                                    (int)Math.floor(
                                       (float)CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getArmy(ixxx)
                                          / defendersArmyx
                                          * var49
                                          / 10.0F
                                    )
                                 );
                           }

                           tempIDs.add(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(ixxx));
                           tempArmies.add(
                              (int)(
                                 CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getArmy(ixxx)
                                    - Math.floor(
                                       (float)CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getArmy(ixxx)
                                          / defendersArmyx
                                          * var49
                                          * this.getFrontLength()
                                    )
                              )
                           );
                           tempArmies_Lost.add(
                              (int)Math.floor(
                                 (float)CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getArmy(ixxx)
                                    / defendersArmyx
                                    * var49
                                    / 10.0F
                              )
                           );
                        }
                     }

                     for (int var66 = 0; var66 < tempIDs.size(); var66++) {
                        CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).updateArmy(tempIDs.get(var66), tempArmies.get(var66));
                        CFG.game.getCiv(tempIDs.get(var66)).setNumOfUnits(CFG.game.getCiv(tempIDs.get(var66)).getNumOfUnits() - tempArmies_Lost.get(var66));
                     }

                     for (int var67 = 1; var67 < CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivsSize(); var67++) {
                        if (this.turnMoves_MoveCurrentArmy_Attack_NumOfDefeningUnits_IsDefender(
                           this.currentMoveUnits.getMoveUnits(0).getToProvinceID(),
                           CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(var67)
                        )) {
                           if ((int)CFG.game
                                    .getCivRelation_OfCivB(
                                       this.currentMoveUnits.getCivID(0),
                                       CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(var67)
                                    )
                                 != -100
                              && !CFG.game
                                 .isAlly(
                                    this.currentMoveUnits.getCivID(0),
                                    CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(var67)
                                 )) {
                              CFG.game
                                 .declareWar(
                                    this.currentMoveUnits.getCivID(0),
                                    CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(var67),
                                    false
                                 );
                           }

                           CFG.game
                              .updateWarStatistics_Casualties(
                                 tempWarID,
                                 this.currentMoveUnits.getCivID(0),
                                 CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(var67),
                                 (int)Math.floor(
                                    (float)CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getArmy(var67)
                                       / defendersArmyx
                                       * var49
                                       * this.getFrontLength()
                                 )
                              );
                           if (this.SHOW_REPORT) {
                              CFG.reportData.lDefenders_IDs.add(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(var67));
                              CFG.reportData
                                 .lDefenders_Armies
                                 .add(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getArmy(var67));
                              CFG.reportData
                                 .lDefenders_ArmiesLost
                                 .add(
                                    (int)Math.floor(
                                          (float)CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getArmy(var67)
                                             / defendersArmyx
                                             * var49
                                       )
                                       / 10
                                 );
                           }

                           CFG.game
                              .getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID())
                              .updateArmy(
                                 CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(var67),
                                 (int)(
                                    CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getArmy(var67)
                                       - Math.floor(
                                          (float)CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getArmy(var67)
                                             / defendersArmyx
                                             * var49
                                             * this.getFrontLength()
                                       )
                                 )
                              );
                           CFG.game
                              .getCiv(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(var67))
                              .setNumOfUnits(
                                 CFG.game.getCiv(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(var67)).getNumOfUnits()
                                    - (int)Math.floor(
                                       (float)CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getArmy(var67)
                                          / defendersArmyx
                                          * var49
                                          * this.getFrontLength()
                                    )
                              );
                           if (CFG.game
                                 .getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID())
                                 .getArmyCivID(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(var67))
                              == 0) {
                              var67--;
                              Gdx.app.log("AoC", "LOSS: 333A - 222-----");
                           }
                        }
                     }

                     Gdx.app.log("AoC", "LOSS: 333A END");
                  }
               }

               if (this.SHOW_REPORT && CFG.settingsManager.SHOW_BATTLE_RESULTS) {
                  CFG.menuManager.rebuildInGame_Report();
                  this.SHOW_REPORT = false;
               }

               if (this.iPlayerAttack_ShowArmyInProvinceID >= 0 && this.iPlayerAttack_ShowArmyInProvinceID < CFG.game.getProvincesSize()) {
                  CFG.game.getProvince(this.iPlayerAttack_ShowArmyInProvinceID).updateFogOfWar(CFG.PLAYER_TURNID);
               }
            } catch (IndexOutOfBoundsException var22) {
               CFG.exceptionStack(var22);
            } catch (NullPointerException var23) {
               CFG.exceptionStack(var23);
            }
         }
      } else if (CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getSeaProvince()
         && CFG.game.getSeaProvinceAttack(this.currentMoveUnits.getCivID(0), this.currentMoveUnits.getMoveUnits(0).getToProvinceID())) {
         Gdx.app.log("AoC", "SEA ATTACK");

         try {
            Gdx.app.log("AoC", "ATTACK: 111");
            if (this.SHOW_REPORT) {
               CFG.reportData = new Report_Data();
               CFG.reportData.iBattleOfProvinceID = this.currentMoveUnits.getMoveUnits(0).getToProvinceID();
            }

            Gdx.app.log("AoC", "ATTACK: 222");
            int tempNumOfUnits = 0;

            for (int ixxxx = 0; ixxxx < this.currentMoveUnits.getMoveUnitsSize(); ixxxx++) {
               tempNumOfUnits += this.currentMoveUnits.getMoveUnits(ixxxx).getNumOfUnits();
            }

            Gdx.app.log("AoC", "ATTACK: 333");
            if (this.SHOW_REPORT) {
               CFG.reportData.iPopulationLosses = 0;
               CFG.reportData.iEconomyLosses = 0;
            }

            int tempWarIDx = CFG.game
               .getWarID(this.currentMoveUnits.getCivID(0), CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID());
            Gdx.app.log("AoC", "ATTACK: 444");
            if (this.turnMoves_MoveCurrentArmy_AttackResult_SEA(
               this.currentMoveUnits.getMoveUnits(0).getToProvinceID(), tempNumOfUnits, this.currentMoveUnits.getCivID(0)
            )) {
               int defendersArmyxx = this.turnMoves_MoveCurrentArmy_Attack_NumOfDefeningUnits_SEA(
                  this.currentMoveUnits.getMoveUnits(0).getToProvinceID(), this.currentMoveUnits.getCivID(0)
               );
               int attackersArmyx = this.currentMoveUnits.getMoveUnits(0).getNumOfUnits();
               if (this.SHOW_REPORT) {
                  CFG.reportData.attackersWon = true;
                  CFG.soundsManager.playSound(CFG.soundsManager.playMoveArmy());
               }

               for (int ixxxx = CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivsSize() - 1; ixxxx >= 1; ixxxx--) {
                  if (CFG.game
                     .getCivsAtWar(
                        this.currentMoveUnits.getCivID(0), CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(ixxxx)
                     )) {
                     if (this.SHOW_REPORT) {
                        tempWarIDx = CFG.game
                           .getWarID(
                              this.currentMoveUnits.getCivID(0), CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(ixxxx)
                           );
                        if (tempWarIDx >= 0) {
                           CFG.game
                              .updateWarStatistics_Casualties(
                                 tempWarIDx,
                                 this.currentMoveUnits.getCivID(0),
                                 CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(ixxxx),
                                 CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getArmy(ixxxx)
                              );
                           CFG.game
                              .updateWarStatistics_Casualties(
                                 tempWarIDx,
                                 CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(ixxxx),
                                 this.currentMoveUnits.getCivID(0),
                                 CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getArmy(ixxxx)
                              );
                        }

                        CFG.reportData.lDefenders_IDs.add(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(ixxxx));
                        CFG.reportData.lDefenders_Armies.add(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getArmy(ixxxx));
                        CFG.reportData.lDefenders_ArmiesLost.add(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getArmy(ixxxx));
                     }

                     CFG.game
                        .getCiv(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(ixxxx))
                        .setNumOfUnits(
                           CFG.game.getCiv(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(ixxxx)).getNumOfUnits()
                              - CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getArmy(ixxxx) / 10
                        );
                     CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).removeArmy_ID(ixxxx);
                  }
               }

               if (this.SHOW_REPORT) {
                  int var43 = defendersArmyxx;

                  for (int ixxxxx = 0; ixxxxx < this.currentMoveUnits.getMoveUnitsSize(); ixxxxx++) {
                     CFG.reportData.lAttackers_IDs.add(this.currentMoveUnits.getCivID(ixxxxx));
                     CFG.reportData.lAttackers_Armies.add(this.currentMoveUnits.getMoveUnits(ixxxxx).getNumOfUnits());
                     CFG.reportData
                        .lAttackers_Armies_Lost
                        .add(
                           this.currentMoveUnits.getMoveUnits(ixxxxx).getNumOfUnits() > var43
                              ? var43
                              : this.currentMoveUnits.getMoveUnits(ixxxxx).getNumOfUnits()
                        );
                     if ((var43 -= this.currentMoveUnits.getMoveUnits(ixxxxx).getNumOfUnits()) < 0) {
                        var43 = 0;
                     }
                  }
               }

               CFG.game
                  .getProvince(this.currentMoveUnits.getMoveUnits(0).getFromProvinceID())
                  .updateArmy(
                     this.currentMoveUnits.getCivID(0),
                     CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getFromProvinceID()).getArmyCivID(this.currentMoveUnits.getCivID(0))
                        - this.currentMoveUnits.getMoveUnits(0).getNumOfUnits()
                  );
               CFG.game
                  .getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID())
                  .updateArmy(this.currentMoveUnits.getCivID(0), attackersArmyx - defendersArmyxx);
               CFG.game
                  .getCiv(this.currentMoveUnits.getCivID(0))
                  .setNumOfUnits(CFG.game.getCiv(this.currentMoveUnits.getCivID(0)).getNumOfUnits() - defendersArmyxx);
            } else {
               int defendersArmyxxx = this.turnMoves_MoveCurrentArmy_Attack_NumOfDefeningUnits_SEA(
                  this.currentMoveUnits.getMoveUnits(0).getToProvinceID(), this.currentMoveUnits.getCivID(0)
               );
               int attackersArmyxx = this.currentMoveUnits.getMoveUnits(0).getNumOfUnits();
               if (this.SHOW_REPORT) {
                  CFG.reportData.attackersWon = false;
                  CFG.soundsManager.playSound(CFG.soundsManager.playMoveArmy());
               }

               int tempDefendersArmyLeft = attackersArmyxx;
               boolean firstCeil = true;

               for (int ixxxxxx = CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivsSize() - 1; ixxxxxx >= 1; ixxxxxx--) {
                  if (CFG.game
                     .getCivsAtWar(
                        this.currentMoveUnits.getCivID(0), CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(ixxxxxx)
                     )) {
                     float tempCurrentLosses = attackersArmyxx
                        * ((float)CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getArmy(ixxxxxx) / defendersArmyxxx);
                     int currentLosses = (int)(firstCeil ? Math.ceil(tempCurrentLosses) : Math.floor(tempCurrentLosses));
                     firstCeil = false;
                     if (this.SHOW_REPORT) {
                        tempWarIDx = CFG.game
                           .getWarID(
                              this.currentMoveUnits.getCivID(0),
                              CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(ixxxxxx)
                           );
                        if (tempWarIDx >= 0) {
                           CFG.game
                              .updateWarStatistics_Casualties(
                                 tempWarIDx,
                                 this.currentMoveUnits.getCivID(0),
                                 CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(ixxxxxx),
                                 currentLosses
                              );
                           CFG.game
                              .updateWarStatistics_Casualties(
                                 tempWarIDx,
                                 CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(ixxxxxx),
                                 this.currentMoveUnits.getCivID(0),
                                 currentLosses
                              );
                        }

                        CFG.reportData.lDefenders_IDs.add(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(ixxxxxx));
                        CFG.reportData.lDefenders_Armies.add(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getArmy(ixxxxxx));
                        CFG.reportData.lDefenders_ArmiesLost.add(currentLosses);
                     }

                     CFG.game
                        .getCiv(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(ixxxxxx))
                        .setNumOfUnits(
                           CFG.game.getCiv(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(ixxxxxx)).getNumOfUnits()
                              - currentLosses
                        );
                     CFG.game
                        .getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID())
                        .updateArmy(
                           CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(ixxxxxx),
                           CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getArmy(ixxxxxx) - currentLosses
                        );
                     if ((tempDefendersArmyLeft -= currentLosses) < 0) {
                        tempDefendersArmyLeft = 0;
                     }
                  }
               }

               if (this.SHOW_REPORT) {
                  tempDefendersArmyLeft = defendersArmyxxx;

                  for (int var34 = 0; var34 < this.currentMoveUnits.getMoveUnitsSize(); var34++) {
                     CFG.reportData.lAttackers_IDs.add(this.currentMoveUnits.getCivID(var34));
                     CFG.reportData.lAttackers_Armies.add(this.currentMoveUnits.getMoveUnits(var34).getNumOfUnits());
                     CFG.reportData.lAttackers_Armies_Lost.add(this.currentMoveUnits.getMoveUnits(var34).getNumOfUnits());
                     if ((tempDefendersArmyLeft -= this.currentMoveUnits.getMoveUnits(var34).getNumOfUnits()) < 0) {
                        tempDefendersArmyLeft = 0;
                     }
                  }
               }

               CFG.game
                  .getProvince(this.currentMoveUnits.getMoveUnits(0).getFromProvinceID())
                  .updateArmy(
                     this.currentMoveUnits.getCivID(0),
                     CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getFromProvinceID()).getArmyCivID(this.currentMoveUnits.getCivID(0))
                        - this.currentMoveUnits.getMoveUnits(0).getNumOfUnits()
                  );
               CFG.game
                  .getCiv(this.currentMoveUnits.getCivID(0))
                  .setNumOfUnits(CFG.game.getCiv(this.currentMoveUnits.getCivID(0)).getNumOfUnits() - attackersArmyxx);
            }

            if (this.SHOW_REPORT && CFG.settingsManager.SHOW_BATTLE_RESULTS) {
               CFG.menuManager.rebuildInGame_Report();
               this.SHOW_REPORT = false;
            }

            if (this.iPlayerAttack_ShowArmyInProvinceID >= 0 && this.iPlayerAttack_ShowArmyInProvinceID < CFG.game.getProvincesSize()) {
               CFG.game.getProvince(this.iPlayerAttack_ShowArmyInProvinceID).updateFogOfWar(CFG.PLAYER_TURNID);
            }
         } catch (IndexOutOfBoundsException var20) {
            CFG.exceptionStack(var20);
         } catch (NullPointerException var21) {
            CFG.exceptionStack(var21);
         }
      } else {
         this.turnMoves_MoveCurrentArmy_JustMove();
      }

      this.currentMoveUnits = null;
   }

   private final boolean turnMoves_MoveCurrentArmy_AttackResult(int toProvinceID, int numOfAttackers) {
      int numOfDefenders = this.turnMoves_MoveCurrentArmy_Attack_NumOfDefeningUnits(toProvinceID);
      float fDefensiveArmyModifiers = 1.0F;
      float fOffensiveArmyModifiers = 1.0F;
      fDefensiveArmyModifiers += this.turnMoves_MoveCurrentArmy_Attack_OffensiveModifiers(toProvinceID);
      fOffensiveArmyModifiers += this.turnMoves_MoveCurrentArmy_Attack_DefensiveModifiers(toProvinceID);
      if (fDefensiveArmyModifiers < 0.001F) {
         fDefensiveArmyModifiers = 0.001F;
      }

      if (fOffensiveArmyModifiers < 0.001F) {
         fOffensiveArmyModifiers = 0.001F;
      }

      return numOfAttackers * fOffensiveArmyModifiers * this.getFrontLength() > numOfDefenders * fDefensiveArmyModifiers;
   }

   public float getFrontLength() {
      return fTroopBreakthroughChance;
   }

   public final float turnMoves_MoveCurrentArmy_Attack_Breakthrough() {
      float fBreakthrough = 1.0F;
      int i = 0;

      while (i < this.currentMoveUnits.getMoveUnitsSize()) {
         i++;
      }

      return fBreakthrough;
   }

   public void retreate(int n) {
      ArrayList<Integer> lProvincesToRetreat = new ArrayList<>();

      for (int j = 0; j < CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getNeighboringProvincesSize(); j++) {
         int tProvince = this.currentMoveUnits.getMoveUnits(0).getToProvinceID();
         int tNeighborProvince = CFG.game
            .getProvince(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getNeighboringProvinces(j))
            .getProvinceID();
         if (CFG.game.getProvince(tProvince).getCivID(n) == CFG.game.getProvince(tNeighborProvince).getCivID()
            || CFG.game.isAlly(CFG.game.getProvince(tProvince).getCivID(n), CFG.game.getProvince(tNeighborProvince).getCivID())
            || CFG.game.getMilitaryAccess(CFG.game.getProvince(tProvince).getCivID(n), CFG.game.getProvince(tNeighborProvince).getCivID()) > 0
            || CFG.game.getCiv(CFG.game.getProvince(tProvince).getCivID(n)).getPuppetOfCivID() == CFG.game.getProvince(tNeighborProvince).getCivID()
            || CFG.game.getCiv(CFG.game.getProvince(tNeighborProvince).getCivID()).getPuppetOfCivID() == CFG.game.getProvince(tProvince).getCivID(n)) {
            lProvincesToRetreat.add(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getNeighboringProvinces(j));
         }
      }

      if (CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getArmy(n) > 100) {
         for (int i = 0; i < lProvincesToRetreat.size(); i++) {
            try {
               try {
                  CFG.game
                     .getProvince(lProvincesToRetreat.get(i))
                     .updateArmy(
                        CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(n),
                        CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getArmy(n) / lProvincesToRetreat.size()
                           + CFG.oR
                              .nextInt(
                                 (int)Math.ceil(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getArmy(n) * -0.02F),
                                 (int)Math.ceil(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getArmy(n) * 0.02F)
                              )
                           + CFG.game.getProvince(lProvincesToRetreat.get(i)).getArmy(n)
                     );
               } catch (IndexOutOfBoundsException var6) {
                  CFG.game
                     .getProvince(lProvincesToRetreat.get(i))
                     .updateArmy(
                        CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(n),
                        CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getArmy(n) / lProvincesToRetreat.size()
                           + CFG.oR
                              .nextInt(
                                 (int)Math.ceil(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getArmy(n) * -0.02F),
                                 (int)Math.ceil(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getArmy(n) * 0.02F)
                              )
                     );
               }
            } catch (IllegalArgumentException var7) {
            }
         }
      }

      lProvincesToRetreat.clear();
   }

   protected void destroyBuildingInBattle(int n) {
      int n2 = CFG.game.getProvince(n).getLevelOfFort();
      if (n2 > 0 && CFG.oR.nextInt(100) < 50) {
         CFG.game.getProvince(n).setLevelOfFort(n2 - 1);
      }

      if ((n2 = CFG.game.getProvince(n).getLevelOfWatchTower()) > 0 && CFG.oR.nextInt(100) < 50) {
         CFG.game.getProvince(n).setLevelOfWatchTower(n2 - 1);
      }

      if ((n2 = CFG.game.getProvince(n).getLevelOfSupply()) > 0 && CFG.oR.nextInt(100) < 20) {
         CFG.game.getProvince(n).setLevelOfSupply(n2 - 1);
      }

      if ((n2 = CFG.game.getProvince(n).getLevelOfPort()) > 0 && CFG.oR.nextInt(100) < 20) {
         CFG.game.getProvince(n).setLevelOfPort(n2 - 1);
      }

      if ((n2 = CFG.game.getProvince(n).getLevelOfLibrary()) > 0 && CFG.oR.nextInt(100) < 20) {
         CFG.game.getProvince(n).setLevelOfLibrary(n2 - 1);
      }

      if ((n2 = CFG.game.getProvince(n).getLevelOfArmoury()) > 0 && CFG.oR.nextInt(100) < 20) {
         CFG.game.getProvince(n).setLevelOfArmoury(n2 - 1);
      }

      if ((n2 = CFG.game.getProvince(n).getLevelOfFarm()) > 0 && CFG.oR.nextInt(100) < 20) {
         CFG.game.getProvince(n).setLevelOfFarm(n2 - 1);
      }

      if ((n2 = CFG.game.getProvince(n).getLevelOfWorkshop()) > 0 && CFG.oR.nextInt(100) < 20) {
         CFG.game.getProvince(n).setLevelOfWorkshop(n2 - 1);
      }
   }

   public float getDamageModifare(int nCivID, boolean isDefender) {
      float damageModifare = 0.0F;
      float minDamage = 0.05F;
      if (CFG.game.getCiv(nCivID).getTechnologyLevel() < 0.2F) {
         damageModifare = 0.05F;
      } else if (CFG.game.getCiv(nCivID).getTechnologyLevel() <= 0.45F) {
         damageModifare += 0.1F;
      } else if (CFG.game.getCiv(nCivID).getTechnologyLevel() > 0.45F && CFG.game.getCiv(nCivID).getTechnologyLevel() <= 0.7F) {
         damageModifare += 0.15F;
      } else if (CFG.game.getCiv(nCivID).getTechnologyLevel() > 0.7F && CFG.game.getCiv(nCivID).getTechnologyLevel() <= 1.0F) {
         damageModifare += 0.2F;
      } else if (CFG.game.getCiv(nCivID).getTechnologyLevel() > 1.0F && CFG.game.getCiv(nCivID).getTechnologyLevel() <= 1.5F) {
         damageModifare += 0.25F;
      } else {
         damageModifare += 0.3F;
      }

      if (!isDefender) {
         damageModifare -= CFG.terrainTypesManager.getDefense(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getTerrainTypeID());
         damageModifare -= BuildingsManager.getFort_DefenseBonus(CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getLevelOfFort())
            / 100.0F;
         damageModifare -= BuildingsManager.getTower_DefenseBonus(
               CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getLevelOfWatchTower()
            )
            / 100.0F;
      }

      if (!isDefender && CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getIsNotSuppliedForXTurns() >= 1
         || isDefender && !CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getFromProvinceID()).getIsSupplied()) {
         damageModifare += 5.25F;
      }

      if ((!isDefender || CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getIsSupplied())
         && (isDefender || CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getFromProvinceID()).getIsSupplied())) {
         int tempOtherCivID = isDefender
            ? CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(0).getToProvinceID()).getCivID(0)
            : this.currentMoveUnits.getCivID(0);
         damageModifare += (CFG.game.getCiv(nCivID).getModifier_AttackBonus() - CFG.game.getCiv(tempOtherCivID).getModifier_DefenseBonus()) / 5.0F;
         damageModifare += GeneralsManager.getAttackBonus(nCivID, this.currentMoveUnits.getMoveUnits(0).getFromProvinceID()) / 5.0F;
         return damageModifare < 0.05F ? minDamage : damageModifare;
      } else {
         return minDamage;
      }
   }

   public final boolean turnMoves_MoveCurrentArmy_AttackResult_SEA(int toProvinceID, int numOfAttackers, int attackersCivID) {
      int numOfDefenders = this.turnMoves_MoveCurrentArmy_Attack_NumOfDefeningUnits_SEA(toProvinceID, attackersCivID);
      return numOfAttackers > numOfDefenders;
   }

   public final int turnMoves_MoveCurrentArmy_Attack_NumOfDefeningUnits(int toProvinceID) {
      int numOfDefenders = CFG.game.getProvince(toProvinceID).getArmy(0);

      for (int i = 1; i < CFG.game.getProvince(toProvinceID).getCivsSize(); i++) {
         if (this.turnMoves_MoveCurrentArmy_Attack_NumOfDefeningUnits_IsDefender(toProvinceID, CFG.game.getProvince(toProvinceID).getCivID(i))) {
            numOfDefenders += CFG.game.getProvince(toProvinceID).getArmy(i);
         }
      }

      return numOfDefenders;
   }

   public final int turnMoves_MoveCurrentArmy_Attack_NumOfDefeningUnits_SEA(int toProvinceID, int attackersCivID) {
      int numOfDefenders = 0;

      for (int i = 1; i < CFG.game.getProvince(toProvinceID).getCivsSize(); i++) {
         if (CFG.game.getCivsAtWar(CFG.game.getProvince(toProvinceID).getCivID(i), attackersCivID)) {
            numOfDefenders += CFG.game.getProvince(toProvinceID).getArmy(i);
         }
      }

      return numOfDefenders;
   }

   public final int turnMoves_MoveCurrentArmy_Attack_NumOfDefenders(int toProvinceID) {
      int numOfDefenders = 1;

      for (int i = 1; i < CFG.game.getProvince(toProvinceID).getCivsSize(); i++) {
         if (this.turnMoves_MoveCurrentArmy_Attack_NumOfDefeningUnits_IsDefender(toProvinceID, CFG.game.getProvince(toProvinceID).getCivID(i))) {
            numOfDefenders++;
         }
      }

      return numOfDefenders;
   }

   public final boolean turnMoves_MoveCurrentArmy_Attack_NumOfDefeningUnits_IsDefender(int toProvinceID, int nCivID) {
      return CFG.game.getCiv(CFG.game.getProvince(toProvinceID).getCivID(0)).getAllianceID() > 0
         && CFG.game.getCiv(CFG.game.getProvince(toProvinceID).getCivID(0)).getAllianceID() == CFG.game.getCiv(nCivID).getAllianceID();
   }

   public final float getDefenseBonusFromTechnology(int nCivID) {
      return nCivID <= 0 ? 0.0F : Math.min(CFG.game.getCiv(nCivID).getTechnologyLevel() * 50.0F * 1.0F, 1000.0F);
   }

   public final int moveArmyModifiers_Defenders(int fromProvinceID, int toProvinceID) {
      try {
         if (CFG.game.getProvince(fromProvinceID).getCivID() != CFG.game.getProvince(toProvinceID).getCivID()
            && CFG.game.getProvince(fromProvinceID).getCivID() != CFG.game.getCiv(CFG.game.getProvince(toProvinceID).getCivID()).getPuppetOfCivID()
            && CFG.game.getCiv(CFG.game.getProvince(fromProvinceID).getCivID()).getPuppetOfCivID() != CFG.game.getProvince(toProvinceID).getCivID()
            && CFG.game.getMilitaryAccess(CFG.game.getProvince(fromProvinceID).getCivID(), CFG.game.getProvince(toProvinceID).getCivID()) <= 0
            && (
               CFG.game.getCiv(CFG.game.getProvince(fromProvinceID).getCivID()).getAllianceID() <= 0
                  || CFG.game.getCiv(CFG.game.getProvince(fromProvinceID).getCivID()).getAllianceID()
                     != CFG.game.getCiv(CFG.game.getProvince(toProvinceID).getCivID()).getAllianceID()
            )) {
            float fOut = CFG.ideologiesManager.getIdeology(CFG.game.getCiv(CFG.game.getProvince(toProvinceID).getCivID()).getIdeologyID()).DEFENSE_BONUS
               / 100.0F;
            fOut += (int)this.getDefenseBonusFromTechnology(CFG.game.getProvince(toProvinceID).getCivID()) / 100.0F;
            if (CFG.game.getProvince(toProvinceID).getIsCapital()) {
               fOut += 0.3F;
            }

            fOut += BuildingsManager.getFort_DefenseBonus(CFG.game.getProvince(toProvinceID).getLevelOfFort()) / 100.0F;
            fOut += BuildingsManager.getTower_DefenseBonus(CFG.game.getProvince(toProvinceID).getLevelOfWatchTower()) / 100.0F;
            fOut += CFG.terrainTypesManager.getDefense(CFG.game.getProvince(toProvinceID).getTerrainTypeID());
            fOut += CFG.game.getCiv(CFG.game.getProvince(toProvinceID).getCivID()).getModifier_DefenseBonus();
            if (CFG.game.getProvince(toProvinceID).getIsNotSuppliedForXTurns() > 0) {
               fOut -= this.getDefenseBonusLossPerTurnForNotSuppliedProvince(toProvinceID);
            }

            return (int)(fOut * 100.0F);
         } else {
            return 0;
         }
      } catch (IndexOutOfBoundsException var4) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var4);
         }

         return 0;
      }
   }

   public float modifierManPower_CivID(int paramInt) {
      float f = 1.45F;
      if (CFG.game.getCiv(paramInt).getControlledByPlayer()) {
         switch (CFG.DIFFICULTY) {
            case 0:
            case 1:
               float var6 = 1.625F;
            case 2:
               float var7 = 1.3F;
            case 3:
               float var8 = 1.25F;
               break;
            default:
               float f1 = f;
               switch (CFG.DIFFICULTY) {
                  case 0:
                     f1 = 1.15F;
                  case 1:
                     f1 = 1.25F;
                  case 2:
                     break;
                  default:
                     f1 = 1.3F;
                  case 3:
                     return f1;
               }
         }

         float var9 = 1.3F;
      }

      return f;
   }

   public final List<MenuElement_Hover_v2_Element2> getMoveArmyModifiers_Defenders_Hover(int fromProvinceID, int toProvinceID) {
      ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
      ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();

      try {
         if (CFG.game.getProvince(fromProvinceID).getCivID() != CFG.game.getProvince(toProvinceID).getCivID()
            && CFG.game.getProvince(fromProvinceID).getCivID() != CFG.game.getCiv(CFG.game.getProvince(toProvinceID).getCivID()).getPuppetOfCivID()
            && CFG.game.getCiv(CFG.game.getProvince(fromProvinceID).getCivID()).getPuppetOfCivID() != CFG.game.getProvince(toProvinceID).getCivID()
            && CFG.game.getMilitaryAccess(CFG.game.getProvince(fromProvinceID).getCivID(), CFG.game.getProvince(toProvinceID).getCivID()) <= 0
            && (
               CFG.game.getCiv(CFG.game.getProvince(fromProvinceID).getCivID()).getAllianceID() <= 0
                  || CFG.game.getCiv(CFG.game.getProvince(fromProvinceID).getCivID()).getAllianceID()
                     != CFG.game.getCiv(CFG.game.getProvince(toProvinceID).getCivID()).getAllianceID()
            )) {
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("BaseValue") + ": "));
            nData.add(
               new MenuElement_Hover_v2_Element_Type_Text(
                  "+" + CFG.ideologiesManager.getIdeology(CFG.game.getCiv(CFG.game.getProvince(toProvinceID).getCivID()).getIdeologyID()).DEFENSE_BONUS + "%",
                  CFG.COLOR_TEXT_MODIFIER_POSITIVE
               )
            );
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
            if (CFG.game.getProvince(toProvinceID).getIsCapital()) {
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("DefenseOfTheCapital") + ": "));
               nData.add(new MenuElement_Hover_v2_Element_Type_Text("+30%", CFG.COLOR_TEXT_MODIFIER_POSITIVE));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
            }

            int fTech;
            if ((fTech = (int)this.getDefenseBonusFromTechnology(CFG.game.getProvince(toProvinceID).getCivID())) > 0) {
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Technology") + ": "));
               nData.add(new MenuElement_Hover_v2_Element_Type_Text("+" + fTech + "%", CFG.COLOR_TEXT_MODIFIER_POSITIVE));
               nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getProvince(toProvinceID).getCivID(), CFG.PADDING, 0));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
            }

            if (BuildingsManager.getFort_DefenseBonus(CFG.game.getProvince(toProvinceID).getLevelOfFort()) > 0) {
               nData.add(
                  new MenuElement_Hover_v2_Element_Type_Text(
                     CFG.langManager.get(BuildingsManager.getFort_Name(CFG.game.getProvince(toProvinceID).getLevelOfFort())) + ": "
                  )
               );
               nData.add(
                  new MenuElement_Hover_v2_Element_Type_Text(
                     "+" + BuildingsManager.getFort_DefenseBonus(CFG.game.getProvince(toProvinceID).getLevelOfFort()) + "%", CFG.COLOR_TEXT_MODIFIER_POSITIVE
                  )
               );
               nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.b_fort, CFG.PADDING, 0));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
            }

            if (BuildingsManager.getTower_DefenseBonus(CFG.game.getProvince(toProvinceID).getLevelOfWatchTower()) > 0) {
               nData.add(
                  new MenuElement_Hover_v2_Element_Type_Text(
                     CFG.langManager.get(BuildingsManager.getTower_Name(CFG.game.getProvince(toProvinceID).getLevelOfWatchTower())) + ": "
                  )
               );
               nData.add(
                  new MenuElement_Hover_v2_Element_Type_Text(
                     "+" + BuildingsManager.getTower_DefenseBonus(CFG.game.getProvince(toProvinceID).getLevelOfWatchTower()) + "%",
                     CFG.COLOR_TEXT_MODIFIER_POSITIVE
                  )
               );
               nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.b_tower, CFG.PADDING, 0));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
            }

            if (CFG.game.getCiv(CFG.game.getProvince(toProvinceID).getCivID()).getModifier_DefenseBonus() != 0.0F) {
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Bonus") + ": "));
               nData.add(
                  new MenuElement_Hover_v2_Element_Type_Text(
                     (CFG.game.getCiv(CFG.game.getProvince(toProvinceID).getCivID()).getModifier_DefenseBonus() > 0.0F ? "+" : "")
                        + (int)(CFG.game.getCiv(CFG.game.getProvince(toProvinceID).getCivID()).getModifier_DefenseBonus() * 100.0F)
                        + "%",
                     CFG.game.getCiv(CFG.game.getProvince(toProvinceID).getCivID()).getModifier_DefenseBonus() > 0.0F
                        ? CFG.COLOR_TEXT_MODIFIER_POSITIVE
                        : CFG.COLOR_TEXT_MODIFIER_NEGATIVE2
                  )
               );
               nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getProvince(toProvinceID).getCivID(), CFG.PADDING, 0));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
            }

            if (CFG.terrainTypesManager.getDefense(CFG.game.getProvince(toProvinceID).getTerrainTypeID()) != 0.0F) {
               nData.add(
                  new MenuElement_Hover_v2_Element_Type_Text(CFG.terrainTypesManager.getName(CFG.game.getProvince(toProvinceID).getTerrainTypeID()) + ": ")
               );
               nData.add(
                  new MenuElement_Hover_v2_Element_Type_Text(
                     (CFG.terrainTypesManager.getDefense(CFG.game.getProvince(toProvinceID).getTerrainTypeID()) > 0.0F ? "+" : "")
                        + (int)(CFG.terrainTypesManager.getDefense(CFG.game.getProvince(toProvinceID).getTerrainTypeID()) * 100.0F)
                        + "%",
                     CFG.terrainTypesManager.getDefense(CFG.game.getProvince(toProvinceID).getTerrainTypeID()) > 0.0F
                        ? CFG.COLOR_TEXT_MODIFIER_POSITIVE
                        : CFG.COLOR_TEXT_MODIFIER_NEGATIVE2
                  )
               );
               nData.add(new MenuElement_Hover_v2_Element_Type_Terrain(CFG.game.getProvince(toProvinceID).getTerrainTypeID(), CFG.PADDING, 0));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
            }

            if (CFG.game.getProvince(toProvinceID).getIsNotSuppliedForXTurns() > 0) {
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("ProvinceIsNotSupplied") + ": "));
               nData.add(
                  new MenuElement_Hover_v2_Element_Type_Text(
                     "-" + (int)(this.getDefenseBonusLossPerTurnForNotSuppliedProvince(toProvinceID) * 100.0F) + "%", CFG.COLOR_TEXT_MODIFIER_NEGATIVE2
                  )
               );
               nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.difficulty_hell, CFG.PADDING, 0));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
            }
         }
      } catch (IndexOutOfBoundsException var6) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var6);
         }
      }

      return nElements;
   }

   public final float getAttackersBonusFromTechnology(int nCivID) {
      return nCivID <= 0 ? 0.0F : Math.min(CFG.game.getCiv(nCivID).getTechnologyLevel() * 50.0F * 1.0F, 1000.0F);
   }

   public final int moveArmyModifiers_Attackers(int fromProvinceID, int toProvinceID, int iCivID) {
      try {
         if (CFG.game.getProvince(fromProvinceID).getCivID() != CFG.game.getProvince(toProvinceID).getCivID()
            && CFG.game.getProvince(fromProvinceID).getCivID() != CFG.game.getCiv(CFG.game.getProvince(toProvinceID).getCivID()).getPuppetOfCivID()
            && CFG.game.getCiv(CFG.game.getProvince(fromProvinceID).getCivID()).getPuppetOfCivID() != CFG.game.getProvince(toProvinceID).getCivID()
            && CFG.game.getMilitaryAccess(CFG.game.getProvince(fromProvinceID).getCivID(), CFG.game.getProvince(toProvinceID).getCivID()) <= 0
            && (
               CFG.game.getCiv(CFG.game.getProvince(fromProvinceID).getCivID()).getAllianceID() <= 0
                  || CFG.game.getCiv(CFG.game.getProvince(fromProvinceID).getCivID()).getAllianceID()
                     != CFG.game.getCiv(CFG.game.getProvince(toProvinceID).getCivID()).getAllianceID()
            )) {
            float fOut = 0.0F;
            if (CFG.game.getProvince(fromProvinceID).getIsCapital()) {
               fOut += 0.45F;
            }

            if (CFG.game.getProvince(fromProvinceID).getIsNotSuppliedForXTurns() > 0) {
               fOut += this.getDefenseBonusLossPerTurnForNotSuppliedProvince(fromProvinceID);
            }

            fOut += this.getAttackersBonusFromTechnology(iCivID) / 100.0F;
            return (int)((fOut + CFG.game.getCiv(iCivID).getModifier_AttackBonus()) * 100.0F);
         } else {
            return 0;
         }
      } catch (IndexOutOfBoundsException var5) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var5);
         }

         return 0;
      }
   }

   public final List<MenuElement_Hover_v2_Element2> getMoveArmyModifiers_Attackers_Hover(int fromProvinceID, int toProvinceID, int iCivID) {
      ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
      ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();

      try {
         if (CFG.game.getProvince(fromProvinceID).getCivID() != CFG.game.getProvince(toProvinceID).getCivID()
            && CFG.game.getProvince(fromProvinceID).getCivID() != CFG.game.getCiv(CFG.game.getProvince(toProvinceID).getCivID()).getPuppetOfCivID()
            && CFG.game.getCiv(CFG.game.getProvince(fromProvinceID).getCivID()).getPuppetOfCivID() != CFG.game.getProvince(toProvinceID).getCivID()
            && CFG.game.getMilitaryAccess(CFG.game.getProvince(fromProvinceID).getCivID(), CFG.game.getProvince(toProvinceID).getCivID()) <= 0
            && (
               CFG.game.getCiv(CFG.game.getProvince(fromProvinceID).getCivID()).getAllianceID() <= 0
                  || CFG.game.getCiv(CFG.game.getProvince(fromProvinceID).getCivID()).getAllianceID()
                     != CFG.game.getCiv(CFG.game.getProvince(toProvinceID).getCivID()).getAllianceID()
            )) {
            if (CFG.game.getProvince(fromProvinceID).getIsCapital()) {
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("AttackFromCapital") + ": "));
               nData.add(new MenuElement_Hover_v2_Element_Type_Text("+45%", CFG.COLOR_TEXT_MODIFIER_POSITIVE));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
            }

            int fTech;
            if ((fTech = (int)this.getAttackersBonusFromTechnology(iCivID)) > 0) {
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Technology") + ": "));
               nData.add(new MenuElement_Hover_v2_Element_Type_Text("+" + fTech + "%", CFG.COLOR_TEXT_MODIFIER_POSITIVE));
               nData.add(new MenuElement_Hover_v2_Element_Type_Flag(iCivID, CFG.PADDING, 0));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
            }

            if (CFG.game.getCiv(iCivID).getModifier_AttackBonus() != 0.0F) {
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Bonus") + ": "));
               nData.add(
                  new MenuElement_Hover_v2_Element_Type_Text(
                     (CFG.game.getCiv(iCivID).getModifier_AttackBonus() > 0.0F ? "+" : "")
                        + (int)(CFG.game.getCiv(iCivID).getModifier_AttackBonus() * 100.0F)
                        + "%",
                     CFG.game.getCiv(iCivID).getModifier_AttackBonus() > 0.0F ? CFG.COLOR_TEXT_MODIFIER_POSITIVE : CFG.COLOR_TEXT_MODIFIER_NEGATIVE2
                  )
               );
               nData.add(new MenuElement_Hover_v2_Element_Type_Flag(iCivID, CFG.PADDING, 0));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
            }
         }
      } catch (IndexOutOfBoundsException var7) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var7);
         }
      }

      return nElements;
   }

   protected final float diceRollBonus(boolean defenders) {
      int tDifference = defenders ? this.diceDefenders - this.diceAggressors : this.diceAggressors - this.diceDefenders;
      return tDifference > 0 ? 0.0F * tDifference : 0.0F;
   }

   public final float turnMoves_MoveCurrentArmy_Attack_OffensiveModifiers(int toProvinceID) {
      float fOffensiveArmyModifiers = CFG.ideologiesManager.getIdeology(CFG.game.getCiv(CFG.game.getProvince(toProvinceID).getCivID()).getIdeologyID()).DEFENSE_BONUS
         / 100.0F;
      if (CFG.game.getProvince(toProvinceID).getIsCapital()) {
         fOffensiveArmyModifiers += 0.3F;
      }

      fOffensiveArmyModifiers += BuildingsManager.getFort_DefenseBonus(CFG.game.getProvince(toProvinceID).getLevelOfFort()) / 100.0F;
      fOffensiveArmyModifiers += this.diceRollBonus(true) / 100.0F;
      fOffensiveArmyModifiers += BuildingsManager.getTower_DefenseBonus(CFG.game.getProvince(toProvinceID).getLevelOfWatchTower()) / 100.0F;
      if (CFG.terrainTypesManager.getDefense(CFG.game.getProvince(toProvinceID).getTerrainTypeID()) > 0.0F) {
         fOffensiveArmyModifiers += CFG.terrainTypesManager.getDefense(CFG.game.getProvince(toProvinceID).getTerrainTypeID());
      }

      fOffensiveArmyModifiers += this.getDefenseBonusFromTechnology(CFG.game.getProvince(toProvinceID).getCivID()) / 100.0F;
      return fOffensiveArmyModifiers + CFG.game.getCiv(CFG.game.getProvince(toProvinceID).getCivID()).getModifier_DefenseBonus();
   }

   protected final float getDefenseBonusLossPerTurnForNotSuppliedProvince(int toProvinceID) {
      return Math.min(6.0F * CFG.game.getProvince(toProvinceID).getIsNotSuppliedForXTurns(), 60.85F);
   }

   public final float turnMoves_MoveCurrentArmy_Attack_DefensiveModifiers(int toProvinceID) {
      float fDefensiveArmyModifiers = 0.0F;
      if (CFG.terrainTypesManager.getDefense(CFG.game.getProvince(toProvinceID).getTerrainTypeID()) < 0.0F) {
         fDefensiveArmyModifiers += CFG.terrainTypesManager.getDefense(CFG.game.getProvince(toProvinceID).getTerrainTypeID());
      }

      if (CFG.game.getProvince(toProvinceID).getIsNotSuppliedForXTurns() > 0) {
         fDefensiveArmyModifiers += this.getDefenseBonusLossPerTurnForNotSuppliedProvince(toProvinceID);
      }

      fDefensiveArmyModifiers += this.diceRollBonus(false) / 100.0F;

      for (int i2 = 0; i2 < this.currentMoveUnits.getMoveUnitsSize(); i2++) {
         if (CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(i2).getFromProvinceID()).getIsCapital()) {
            fDefensiveArmyModifiers += 0.45F;
            break;
         }
      }

      float fBest = 0.0F;

      for (int i = 0; i < this.currentMoveUnits.getMoveUnitsSize(); i++) {
         if (CFG.game.getCiv(this.currentMoveUnits.getCivID(i)).getModifier_AttackBonus() > fBest) {
            fBest = CFG.game.getCiv(this.currentMoveUnits.getCivID(i)).getModifier_AttackBonus();
         }
      }

      fDefensiveArmyModifiers += fBest;
      fBest = 0.0F;

      for (int var5 = 0; var5 < this.currentMoveUnits.getMoveUnitsSize(); var5++) {
         if (this.getAttackersBonusFromTechnology(this.currentMoveUnits.getCivID(var5)) / 100.0F > fBest) {
            fBest = this.getAttackersBonusFromTechnology(this.currentMoveUnits.getCivID(var5)) / 100.0F;
         }
      }

      float var8;
      return var8 = fDefensiveArmyModifiers + fBest;
   }

   public final void turnMoves_MoveCurrentArmy_JustMove() {
      for (int i = 0; i < this.currentMoveUnits.getMoveUnitsSize(); i++) {
         CFG.game
            .getProvince(this.currentMoveUnits.getMoveUnits(i).getFromProvinceID())
            .updateArmy(
               this.currentMoveUnits.getCivID(i),
               CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(i).getFromProvinceID()).getArmyCivID(this.currentMoveUnits.getCivID(i))
                  - this.currentMoveUnits.getMoveUnits(i).getNumOfUnits()
            );
         CFG.game
            .getProvince(this.currentMoveUnits.getMoveUnits(i).getToProvinceID())
            .updateArmy(
               this.currentMoveUnits.getCivID(i),
               CFG.game.getProvince(this.currentMoveUnits.getMoveUnits(i).getToProvinceID()).getArmyCivID(this.currentMoveUnits.getCivID(i))
                  + this.currentMoveUnits.getMoveUnits(i).getNumOfUnits()
            );
      }
   }

   public final void loadActivePlayerData() {
      Gdx.app.log("AoC", "loadActivePlayerData: 00000");
      if (CFG.FOG_OF_WAR > 0) {
         if (CFG.FOG_OF_WAR == 2) {
            for (int i = 0; i < CFG.game.getProvincesSize(); i++) {
               CFG.game.getProvince(i).updateProvinceBorder();
            }

            Game_Render.updateDrawCivRegionNames_FogOfWar();
         }

         for (int i = 0; i < CFG.game.getProvincesSize(); i++) {
            CFG.game.getProvince(i).updateDrawArmy();
         }
      }

      Gdx.app.log("AoC", "loadActivePlayerData: 1111");
      CFG.menuManager.rebuildInGame_Messages();
      Gdx.app.log("AoC", "loadActivePlayerData: 222");
      CFG.menuManager.setVisible_Menu_InGame_CurrentWars(true);
      Gdx.app.log("AoC", "loadActivePlayerData: 333");
      CFG.game.buildMoveUnits_JustDraw_AnotherArmies();
      Gdx.app.log("AoC", "loadActivePlayerData: 444");

      try {
         if (!CFG.SPECTATOR_MODE
            && CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getNumOfProvinces() == 0
            && this.showDefeatView(CFG.PLAYER_TURNID)
            && !gameEnded) {
            CFG.menuManager.setViewID(Menu.eDEFEAT);
            CFG.map.getMapBG().updateWorldMap_Shaders();
            CFG.toast.setInView(CFG.langManager.get("Defeat"), CFG.COLOR_TEXT_MODIFIER_NEGATIVE2);
            gameEnded = true;
         } else if (CFG.settingsManager.CONFIRM_NEXT_PLAYER_TURN) {
            CFG.menuManager.updateInGame_TOP_All(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
            if ((!RTS.isEnabled() || RTS.PAUSE) && !CFG.SPECTATOR_MODE && this.showNextPlayerTurnView_NextTurn()) {
               CFG.menuManager.setViewIDWithoutAnimation(Menu.eNEXT_PLAYER_TURN);
               CFG.game.enableDrawCivilizationRegions(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), 0);
               CFG.map.getMapBG().updateWorldMap_Shaders();
            } else {
               Menu_NextPlayerTurn.clickEnd();
            }

            Menu_InGame_Messages.START_ANIMATION = true;
         }
      } catch (IndexOutOfBoundsException var2) {
         Menu_NextPlayerTurn.clickEnd();
      } catch (NullPointerException var3) {
         Menu_NextPlayerTurn.clickEnd();
      } catch (StackOverflowError var4) {
         Menu_NextPlayerTurn.clickEnd();
      } catch (ArithmeticException var5) {
         Menu_NextPlayerTurn.clickEnd();
      }

      Gdx.app.log("AoC", "loadActivePlayerData: END");
   }

   public final void checkGameEnd() {
      if (!CFG.SPECTATOR_MODE && !gameEnded) {
         for (int i = 0; i < CFG.game.getPlayersSize(); i++) {
            int numOfProvinces = CFG.game.getCiv(CFG.game.getPlayer(i).getCivID()).getNumOfProvinces();
            Gdx.app.log("AoC", "checkGameEnd: numOfProvinces1: " + numOfProvinces);

            for (int z = 0; z < CFG.game.getCiv(CFG.game.getPlayer(i).getCivID()).civGameData.iVassalsSize; z++) {
               numOfProvinces += CFG.game.getCiv(CFG.game.getCiv(CFG.game.getPlayer(i).getCivID()).civGameData.lVassals.get(z).iCivID).getNumOfProvinces();
            }

            Gdx.app.log("AoC", "checkGameEnd: numOfProvinces2: " + numOfProvinces);
            if (CFG.game.getCiv(CFG.game.getPlayer(i).getCivID()).getAllianceID() > 0) {
               for (int var4 = 0; var4 < CFG.game.getAlliance(CFG.game.getCiv(CFG.game.getPlayer(i).getCivID()).getAllianceID()).getCivilizationsSize(); var4++) {
                  if (CFG.game.getAlliance(CFG.game.getCiv(CFG.game.getPlayer(i).getCivID()).getAllianceID()).getCivilization(var4)
                        != CFG.game.getPlayer(i).getCivID()
                     && CFG.game
                           .getCiv(CFG.game.getAlliance(CFG.game.getCiv(CFG.game.getPlayer(i).getCivID()).getAllianceID()).getCivilization(var4))
                           .getPuppetOfCivID()
                        != CFG.game.getPlayer(i).getCivID()) {
                     numOfProvinces += CFG.game
                        .getCiv(CFG.game.getAlliance(CFG.game.getCiv(CFG.game.getPlayer(i).getCivID()).getAllianceID()).getCivilization(var4))
                        .getNumOfProvinces();
                  }
               }
            }

            Gdx.app.log("AoC", "checkGameEnd: numOfProvinces3: " + numOfProvinces);
            Gdx.app.log("AoC", "checkGameEnd: VIC CFG.oAI.PLAYABLE_PROVINCES: " + CFG.oAI.PLAYABLE_PROVINCES);
            Gdx.app.log("AoC", "checkGameEnd: CFG.oAI.NUM_OF_CIVS_IN_THE_GAME: " + CFG.oAI.NUM_OF_CIVS_IN_THE_GAME);
            if (VicotryManager.VICTORY_LIMIT_OF_TURNS != 0 && VicotryManager.VICTORY_LIMIT_OF_TURNS < Game_Calendar.TURN_ID) {
               Gdx.app.log("AoC", "checkGameEnd: VIC 0000");
               CFG.menuManager.setViewID(Menu.eVICTORY);
               CFG.map.getMapBG().updateWorldMap_Shaders();
               CFG.toast.setInView("TurnsLimit", CFG.COLOR_TEXT_MODIFIER_POSITIVE);
               CFG.toast.setTimeInView(4500);
               gameEnded = true;
            } else if (CFG.oAI.PLAYABLE_PROVINCES > numOfProvinces
               && !(CFG.oAI.PLAYABLE_PROVINCES <= numOfProvinces * (VicotryManager.VICTORY_CONTROL_PROVINCES_PERC / 100.0F))
               && CFG.oAI.NUM_OF_CIVS_IN_THE_GAME >= 2) {
               Gdx.app.log("AoC", "checkGameEnd: VIC 2222");
               if (VicotryManager.VICTORY_TECHNOLOGY > 0.0F) {
                  for (int var5 = 1; var5 < CFG.game.getCivsSize(); var5++) {
                     Gdx.app.log("AoC", "checkGameEnd: VIC 222: CIV: " + CFG.game.getCiv(var5).getCivName());
                     Gdx.app.log("AoC", "checkGameEnd: VIC 222: CFG.game.getCiv(z).getTechnologyLevel(): " + CFG.game.getCiv(var5).getTechnologyLevel());
                     Gdx.app.log("AoC", "checkGameEnd: VIC 222:  VicotryManager.VICTORY_TECHNOLOGY: " + VicotryManager.VICTORY_TECHNOLOGY);
                     if (CFG.game.getCiv(var5).getNumOfProvinces() > 0 && CFG.game.getCiv(var5).getTechnologyLevel() >= VicotryManager.VICTORY_TECHNOLOGY) {
                        if (CFG.game.getCiv(var5).getControlledByPlayer()) {
                           CFG.menuManager.setViewID(Menu.eVICTORY);
                           CFG.map.getMapBG().updateWorldMap_Shaders();
                           CFG.toast.setInView("Technology: " + VicotryManager.VICTORY_TECHNOLOGY, CFG.COLOR_TEXT_MODIFIER_POSITIVE);
                           CFG.toast.setTimeInView(4500);
                           gameEnded = true;
                        } else {
                           CFG.menuManager.setViewID(Menu.eDEFEAT);
                           CFG.map.getMapBG().updateWorldMap_Shaders();
                           CFG.toast.setInView("Technology: " + VicotryManager.VICTORY_TECHNOLOGY, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2);
                           CFG.toast.setTimeInView(4500);
                           gameEnded = true;
                        }
                     }
                  }
               }
            } else {
               Gdx.app.log("AoC", "checkGameEnd: VIC 1111");
               CFG.menuManager.setViewID(Menu.eVICTORY);
               CFG.map.getMapBG().updateWorldMap_Shaders();
               gameEnded = true;
            }
         }
      }
   }

   public final boolean showDefeatView(int nPlayerID) {
      for (int i = 0; i < CFG.game.getProvincesSize(); i++) {
         if (!CFG.game.getProvince(i).getSeaProvince()
            && CFG.game.getProvince(i).getWasteland() < 0
            && CFG.game.getProvince(i).getTrueOwnerOfProvince() == CFG.game.getPlayer(nPlayerID).getCivID()) {
            return false;
         }
      }

      if (!CFG.game.getPlayer(nPlayerID).savePlayer.lostNextTurn) {
         CFG.game.getPlayer(nPlayerID).savePlayer.lostNextTurn = true;
         return false;
      } else {
         return true;
      }
   }

   public final boolean showNextPlayerTurnView() {
      return CFG.settingsManager.showNextPlayerView || SaveManager.gameWillBeSavedInThisTurn() || this.getNumOfPlayersInGame() > 1;
   }

   public final boolean showNextPlayerTurnView_NextTurn() {
      return CFG.settingsManager.showNextPlayerView || SaveManager.forceShowNextPlayerTurnView || this.getNumOfPlayersInGame() > 1;
   }

   public int getNumOfPlayersInGame() {
      int out = 0;

      for (int i = 0; i < CFG.game.getPlayersSize(); i++) {
         if (CFG.game.getCiv(CFG.game.getPlayer(i).getCivID()).getNumOfProvinces() > 0) {
            out++;
         }
      }

      return out;
   }

   public final void buildFogOfWar(int nPlayerID) {
      try {
         for (int i = 0; i < CFG.game.getProvincesSize(); i++) {
            CFG.game.getPlayer(nPlayerID).setFogOfWar(i, false);
         }

         this.buildFogOfWar_CivID(nPlayerID, CFG.game.getPlayer(nPlayerID).getCivID());
         if (CFG.game.getCiv(CFG.game.getPlayer(nPlayerID).getCivID()).getAllianceID() > 0) {
            for (int var4 = 0;
               var4 < CFG.game.getAlliance(CFG.game.getCiv(CFG.game.getPlayer(nPlayerID).getCivID()).getAllianceID()).getCivilizationsSize();
               var4++
            ) {
               if (CFG.game.getAlliance(CFG.game.getCiv(CFG.game.getPlayer(nPlayerID).getCivID()).getAllianceID()).getCivilization(var4)
                  != CFG.game.getPlayer(nPlayerID).getCivID()) {
                  this.buildFogOfWar_CivID(
                     nPlayerID, CFG.game.getAlliance(CFG.game.getCiv(CFG.game.getPlayer(nPlayerID).getCivID()).getAllianceID()).getCivilization(var4)
                  );
               }
            }
         }

         for (int var5 = 1; var5 < CFG.game.getCivsSize(); var5++) {
            if (var5 != CFG.game.getPlayer(nPlayerID).getCivID() && CFG.game.getCiv(var5).getPuppetOfCivID() == CFG.game.getPlayer(nPlayerID).getCivID()) {
               this.buildFogOfWar_CivID(nPlayerID, var5);
            }
         }

         for (int var6 = 1; var6 < CFG.game.getCivsSize(); var6++) {
            if (var6 != CFG.game.getPlayer(nPlayerID).getCivID() && CFG.game.getCiv(CFG.game.getPlayer(nPlayerID).getCivID()).getPuppetOfCivID() == var6) {
               this.buildFogOfWar_CivID(nPlayerID, var6);
            }
         }
      } catch (IndexOutOfBoundsException var3) {
         CFG.exceptionStack(var3);
      }
   }

   public final void buildFogOfWar_CivID(int nPlayerID, int nCivID) {
      for (int i = 0; i < CFG.game.getCiv(nCivID).getNumOfProvinces(); i++) {
         CFG.game.getPlayer(nPlayerID).setFogOfWar(CFG.game.getCiv(nCivID).getProvinceID(i), true);

         for (int j = 0; j < CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getNeighboringSeaProvincesSize(); j++) {
            CFG.game.getPlayer(nPlayerID).setFogOfWar(CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getNeighboringSeaProvinces(j), true);
         }

         this.buildFogOfWar_WatchTower(nPlayerID, CFG.game.getCiv(nCivID).getProvinceID(i));
      }

      for (int var6 = 0; var6 < CFG.game.getCiv(nCivID).getArmyInAnotherProvinceSize(); var6++) {
         CFG.game.getPlayer(nPlayerID).setFogOfWar(CFG.game.getCiv(nCivID).getArmyInAnotherProvince(var6), true);
         if (CFG.game.getProvince(CFG.game.getCiv(nCivID).getArmyInAnotherProvince(var6)).getSeaProvince()) {
            for (int j = 0; j < CFG.game.getProvince(CFG.game.getCiv(nCivID).getArmyInAnotherProvince(var6)).getNeighboringProvincesSize(); j++) {
               if (CFG.game
                  .getProvince(CFG.game.getProvince(CFG.game.getCiv(nCivID).getArmyInAnotherProvince(var6)).getNeighboringProvinces(j))
                  .getSeaProvince()) {
                  CFG.game
                     .getPlayer(nPlayerID)
                     .setFogOfWar(CFG.game.getProvince(CFG.game.getCiv(nCivID).getArmyInAnotherProvince(var6)).getNeighboringProvinces(j), true);
               }
            }
         }
      }
   }

   public final void buildFogOfWar_WatchTower(int nPlayerID, int nProvinceID) {
      if (CFG.game.getProvince(nProvinceID).getLevelOfWatchTower() > 0) {
         if (CFG.game.getProvince(nProvinceID).getLevelOfWatchTower() == 1) {
            for (int j = 0; j < CFG.game.getProvince(nProvinceID).getNeighboringProvincesSize(); j++) {
               if (CFG.game.getProvince(CFG.game.getProvince(nProvinceID).getNeighboringProvinces(j)).getLevelOfFort() < 1) {
                  CFG.game.getPlayer(nPlayerID).setFogOfWar(CFG.game.getProvince(nProvinceID).getNeighboringProvinces(j), true);
               }
            }
         } else {
            for (int jx = 0; jx < CFG.game.getProvince(nProvinceID).getNeighboringProvincesSize(); jx++) {
               if (CFG.game.getProvince(CFG.game.getProvince(nProvinceID).getNeighboringProvinces(jx)).getLevelOfFort() < 1) {
                  CFG.game.getPlayer(nPlayerID).setFogOfWar(CFG.game.getProvince(nProvinceID).getNeighboringProvinces(jx), true);

                  for (int k = 0; k < CFG.game.getProvince(CFG.game.getProvince(nProvinceID).getNeighboringProvinces(jx)).getNeighboringProvincesSize(); k++) {
                     if (CFG.game
                           .getProvince(CFG.game.getProvince(CFG.game.getProvince(nProvinceID).getNeighboringProvinces(jx)).getNeighboringProvinces(k))
                           .getLevelOfFort()
                        < 1) {
                        CFG.game
                           .getPlayer(nPlayerID)
                           .setFogOfWar(CFG.game.getProvince(CFG.game.getProvince(nProvinceID).getNeighboringProvinces(jx)).getNeighboringProvinces(k), true);
                     }
                  }
               }
            }
         }
      }
   }

   public final boolean hasArmyInProvince(int nProvinceID, int nCivID) {
      for (int i = 0; i < CFG.game.getProvince(nProvinceID).getCivsSize(); i++) {
         if (CFG.game.getProvince(nProvinceID).getCivID(i) == nCivID) {
            return CFG.game.getProvince(nProvinceID).getArmy(i) > 0;
         }
      }

      return false;
   }

   public final boolean hasArmyInProvince_AllianceID(int nProvinceID, int nAllianceID) {
      if (nAllianceID == 0) {
         return false;
      } else {
         for (int i = 0; i < CFG.game.getProvince(nProvinceID).getCivsSize(); i++) {
            if (CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID(i)).getAllianceID() == nAllianceID) {
               return true;
            }
         }

         return false;
      }
   }

   public final boolean isMovingArmyFromProvince(int nProvinceID) {
      return this.isMovingArmyFromProvince(nProvinceID, CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
   }

   public final boolean isMovingArmyFromProvince(int nProvinceID, int nCivID) {
      for (int i = 0; i < CFG.game.getCiv(nCivID).getMoveUnitsSize(); i++) {
         if (CFG.game.getCiv(nCivID).getMoveUnits(i).getFromProvinceID() == nProvinceID) {
            return true;
         }
      }

      return false;
   }

   public final boolean controlsArmyInProvince(int nProvinceID) {
      return this.controlsArmyInProvince(nProvinceID, CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
   }

   public final boolean controlsArmyInProvince(int nProvinceID, int nCivID) {
      for (int i = 0; i < CFG.game.getProvince(nProvinceID).getCivsSize(); i++) {
         if (CFG.game.getProvince(nProvinceID).getCivID(i) == nCivID && CFG.game.getProvince(nProvinceID).getArmy(i) > 0) {
            CFG.activeCivilizationArmyID = i;
            return true;
         }
      }

      CFG.activeCivilizationArmyID = 0;
      return false;
   }

   public final boolean canColonizieWasteland_Tech(int nProvinceID, int nCivID) {
      return !Game_Calendar.getColonizationOfWastelandIsEnabled() ? false : Game_Calendar.getCanColonize_TechLevel(nCivID);
   }

   public final boolean canColonizieNeutral_Tech(int nProvinceID, int nCivID) {
      return Game_Calendar.getCanColonize_TechLevel(nCivID);
   }

   public final boolean canColonizieWasteland_BorderOrArmy(int nProvinceID, int nCivID) {
      for (int i = 0; i < CFG.game.getProvince(nProvinceID).getNeighboringProvincesSize(); i++) {
         if (CFG.game.getProvince(CFG.game.getProvince(nProvinceID).getNeighboringProvinces(i)).getWasteland() < 0) {
            if (CFG.game.getProvince(CFG.game.getProvince(nProvinceID).getNeighboringProvinces(i)).getCivID() == nCivID) {
               return true;
            }

            for (int j = 0; j < CFG.game.getProvince(CFG.game.getProvince(nProvinceID).getNeighboringProvinces(i)).getCivsSize(); j++) {
               if (CFG.game.getProvince(CFG.game.getProvince(nProvinceID).getNeighboringProvinces(i)).getCivID(j) == nCivID) {
                  return true;
               }
            }
         }
      }

      for (int var6 = 0; var6 < CFG.game.getProvince(nProvinceID).getNeighboringSeaProvincesSize(); var6++) {
         for (int jx = 1; jx < CFG.game.getProvince(CFG.game.getProvince(nProvinceID).getNeighboringSeaProvinces(var6)).getCivsSize(); jx++) {
            if (CFG.game.getProvince(CFG.game.getProvince(nProvinceID).getNeighboringSeaProvinces(var6)).getCivID(jx) == nCivID) {
               return true;
            }
         }
      }

      return false;
   }

   public final void resetTurnData() {
      if (Game_Action.TurnStates.INPUT_ORDERS == this.activeTurnAction) {
         CFG.game.getPlayer(CFG.PLAYER_TURNID).iBefore_ActiveProvince = CFG.game.getActiveProvinceID();
      }

      if (CFG.chooseProvinceMode) {
         CFG.game.resetChooseProvinceData();
      }

      if (CFG.regroupArmyMode) {
         CFG.game.resetRegroupArmyData();
      }
   }

   public final void hideAllProvinceActionViews() {
      CFG.menuManager.setVisible_InGame_ActionInfo(false);
      CFG.menuManager.setVisible_InGame_ProvinceAction(false);
      CFG.menuManager.setVisible_InGame_ProvinceMoveUnits(false);
      CFG.menuManager.setVisible_InGame_ProvinceRecruit(false);
      CFG.menuManager.setVisible_InGame_ProvinceRecruitInstantly(false);
      CFG.menuManager.setVisible_InGame_ProvinceRegroupArmy(false);
      CFG.menuManager.setVisible_InGame_ProvinceDisband(false);
      CFG.menuManager.setVisible_InGame_ProvinceAction_Colonize(false);
      CFG.menuManager.setVisible_InGame_ProvinceAction_Colonize_TechRequired(false);
   }

   public final void hideAllViews() {
      this.hideAllProvinceActionViews();
      CFG.menuManager.updateInGameRTO(false);
      if (CFG.menuManager.getColorPicker().getVisible()) {
         CFG.menuManager.getColorPicker().setVisible(false, null);
      }
   }

   public final boolean canMigrate_MovementPoints(int iCivID) {
      return CFG.game.getCiv(iCivID).getMovePoints() >= CFG.ideologiesManager.getIdeology(CFG.game.getCiv(iCivID).getIdeologyID()).COST_OF_MOVE;
   }

   public final boolean migrateToProvince(int fromProvinceID, int toProvinceID, int iCivID, boolean buildLine) {
      if (!this.canMigrate_MovementPoints(iCivID)) {
         return false;
      } else if (!Game.uncivilizedCanMigrate_FromProvince(fromProvinceID, iCivID)) {
         return false;
      } else if (CFG.game.getCiv(iCivID).migratesFromProvinceID(fromProvinceID)) {
         return false;
      } else {
         CFG.game.getCiv(iCivID).newMigrate(fromProvinceID, toProvinceID, buildLine);
         CFG.game
            .getCiv(iCivID)
            .setMovePoints(CFG.game.getCiv(iCivID).getMovePoints() - CFG.ideologiesManager.getIdeology(CFG.game.getCiv(iCivID).getIdeologyID()).COST_OF_MOVE);
         return true;
      }
   }

   public final boolean moveArmy(int fromProvinceID, int toProvinceID, int nNumOfUnits, int iCivID, boolean regroupMode, boolean buildLine) {
      try {
         if (AI_Assistant.ENABLED
            && !AI_Assistant.GARRISON_PROVINCES.isEmpty()
            && AI_Assistant.GARRISON_PROVINCES.contains(fromProvinceID)
            && iCivID == CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()
            && nNumOfUnits > 0
            && this.activeTurnAction != Game_Action.TurnStates.INPUT_ORDERS
            && CFG.game.getProvince(fromProvinceID).getArmyCivID(iCivID) - nNumOfUnits < 1000) {
            Gdx.app.log("AoC", "Garrison: BLOCKED drain from " + fromProvinceID);
            return false;
         }

         if (!regroupMode && nNumOfUnits > 0 && !CFG.game.getProvince(toProvinceID).getSeaProvince()) {
            boolean tAdjacent = false;

            for (int tn = 0; tn < CFG.game.getProvince(fromProvinceID).getNeighboringProvincesSize(); tn++) {
               if (CFG.game.getProvince(fromProvinceID).getNeighboringProvinces(tn) == toProvinceID) {
                  tAdjacent = true;
                  break;
               }
            }

            if (!tAdjacent) {
               RegroupArmy_Data tRouteData = new RegroupArmy_Data(iCivID, fromProvinceID, toProvinceID);
               if (tRouteData.getRouteSize() > 1
                  && tRouteData.getRoute(tRouteData.getRouteSize() - 1) == toProvinceID
                  && CFG.game.getProvince(tRouteData.getRoute(0)).getWasteland() < 0) {
                  int tPrevHop = fromProvinceID;

                  for (int tk = 0; tk < tRouteData.getRouteSize(); tk++) {
                     int tNextHop = tRouteData.getRoute(tk);
                     if (!this.moveArmy(tPrevHop, tNextHop, nNumOfUnits, iCivID, regroupMode, buildLine)) {
                        return tk > 0;
                     }

                     tPrevHop = tNextHop;
                  }

                  return true;
               }
            }
         }

         if (nNumOfUnits == 0) {
            for (int i = 0; i < CFG.game.getCiv(iCivID).getMoveUnitsSize(); i++) {
               if (CFG.game.getCiv(iCivID).getMoveUnits(i).getFromProvinceID() == fromProvinceID
                  && CFG.game.getCiv(iCivID).getMoveUnits(i).getToProvinceID() == toProvinceID) {
                  CFG.game
                     .getProvince(fromProvinceID)
                     .updateArmy(iCivID, CFG.game.getProvince(fromProvinceID).getArmyCivID(iCivID) + CFG.game.getCiv(iCivID).getMoveUnits(i).getNumOfUnits());
                  CFG.game.getCiv(iCivID).removeMove(i);

                  for (int j = 0; j < CFG.game.getCiv(iCivID).getRegroupArmySize(); j++) {
                     if (CFG.game.getCiv(iCivID).getRegroupArmy(j).getFromProvinceID() == toProvinceID) {
                        CFG.game.getCiv(iCivID).removeRegroupArmy(j--);
                     }
                  }

                  CFG.game.getCiv(iCivID).setMovePoints(CFG.game.getCiv(iCivID).getMovePoints() + this.costOfMoveArmy(fromProvinceID, toProvinceID, iCivID));
                  return false;
               }
            }

            return false;
         } else {
            for (int ix = 0; ix < CFG.game.getCiv(iCivID).getMoveUnitsSize(); ix++) {
               if (CFG.game.getCiv(iCivID).getMoveUnits(ix).getFromProvinceID() == fromProvinceID
                  && CFG.game.getCiv(iCivID).getMoveUnits(ix).getToProvinceID() == toProvinceID) {
                  if (regroupMode) {
                     if (CFG.game.getProvince(fromProvinceID).getArmyCivID(iCivID) < nNumOfUnits) {
                        nNumOfUnits = CFG.game.getProvince(fromProvinceID).getArmyCivID(iCivID);
                     }

                     CFG.game.getProvince(fromProvinceID).updateArmy(iCivID, CFG.game.getProvince(fromProvinceID).getArmyCivID(iCivID) - nNumOfUnits);
                     CFG.game.getCiv(iCivID).getMoveUnits(ix).setNumOfUnits(CFG.game.getCiv(iCivID).getMoveUnits(ix).getNumOfUnits() + nNumOfUnits);
                  } else {
                     CFG.game
                        .getProvince(fromProvinceID)
                        .updateArmy(
                           iCivID,
                           CFG.game.getProvince(fromProvinceID).getArmyCivID(iCivID) - (nNumOfUnits - CFG.game.getCiv(iCivID).getMoveUnits(ix).getNumOfUnits())
                        );
                     CFG.game.getCiv(iCivID).getMoveUnits(ix).setNumOfUnits(nNumOfUnits);
                  }

                  return true;
               }
            }

            if (CFG.game.getCiv(iCivID).getMovePoints() < this.costOfMoveArmy(fromProvinceID, toProvinceID, iCivID)) {
               return false;
            } else if (!CFG.game.getProvince(fromProvinceID).getSeaProvince()
               && CFG.game.getProvince(toProvinceID).getSeaProvince()
               && CFG.game.getProvince(fromProvinceID).getLevelOfPort() < 1) {
               return false;
            } else {
               if (nNumOfUnits > CFG.game.getProvince(fromProvinceID).getArmyCivID(iCivID)) {
                  nNumOfUnits = CFG.game.getProvince(fromProvinceID).getArmyCivID(iCivID);
               }

               if (nNumOfUnits <= 0) {
                  return false;
               } else {
                  CFG.game.getCiv(iCivID).setMovePoints(CFG.game.getCiv(iCivID).getMovePoints() - this.costOfMoveArmy(fromProvinceID, toProvinceID, iCivID));
                  CFG.game.getCiv(iCivID).newMove(fromProvinceID, toProvinceID, nNumOfUnits, buildLine);
                  CFG.game.getProvince(fromProvinceID).updateArmy(iCivID, CFG.game.getProvince(fromProvinceID).getArmyCivID(iCivID) - nNumOfUnits);
                  return true;
               }
            }
         }
      } catch (IndexOutOfBoundsException var9) {
         CFG.exceptionStack(var9);
         return false;
      }
   }

   public final int costOfMoveArmy(int fromProvinceID, int toProvinceID, int nCivID) {
      try {
         if (CFG.game.getProvince(fromProvinceID).getCivID() > 0
            && CFG.game.getProvince(toProvinceID).getCivID() > 0
            && CFG.game.getProvince(fromProvinceID).getCivID() == CFG.game.getProvince(toProvinceID).getCivID()
            && CFG.game.getProvince(fromProvinceID).getCivID() == nCivID) {
            return CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivID).getIdeologyID()).COST_OF_MOVE_OWN_PROVINCE;
         } else if (CFG.game.getProvince(fromProvinceID).getSeaProvince()) {
            return CFG.game.getProvince(toProvinceID).getSeaProvince()
               ? (int)(CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivID).getIdeologyID()).COST_OF_MOVE * 1.5F)
               : CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivID).getIdeologyID()).COST_OF_MOVE * 2;
         } else {
            for (int i = 0; i < CFG.game.getCiv(nCivID).getMoveUnitsSize(); i++) {
               if (CFG.game.getCiv(nCivID).getMoveUnits(i).getToProvinceID() == toProvinceID) {
                  return CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivID).getIdeologyID()).COST_OF_MOVE_SAME_PROVINCE;
               }
            }

            return CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivID).getIdeologyID()).COST_OF_MOVE;
         }
      } catch (IndexOutOfBoundsException var5) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var5);
         }

         return CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivID).getIdeologyID()).COST_OF_MOVE;
      }
   }

   public final boolean getIsFreeMove(int iCivID, int fromProvinceID, int toProvinceID) {
      for (int i = 0; i < CFG.game.getCiv(iCivID).getMoveUnitsSize(); i++) {
         if (CFG.game.getCiv(iCivID).getMoveUnits(i).getFromProvinceID() == fromProvinceID
            && CFG.game.getCiv(iCivID).getMoveUnits(i).getToProvinceID() == toProvinceID) {
            return true;
         }
      }

      return false;
   }

   public final void updatePopulationLosses(int nProvinceID, int iLosses) {
      int nRecuritedPop = CFG.game.getProvince(nProvinceID).getPopulationData().getPopulation();

      for (int i = 0; i < CFG.game.getProvince(nProvinceID).getPopulationData().getNationalitiesSize(); i++) {
         if (CFG.game.getProvince(nProvinceID).getPopulationData().getCivID(i) == 0) {
            if (CFG.game
               .getProvince(nProvinceID)
               .getPopulationData()
               .setPopulationOfCivID(
                  CFG.game.getProvince(nProvinceID).getPopulationData().getCivID(i),
                  (int)(
                     CFG.game.getProvince(nProvinceID).getPopulationData().getPopulationID(i)
                        - Math.floor(iLosses * ((float)CFG.game.getProvince(nProvinceID).getPopulationData().getPopulationID(i) / nRecuritedPop))
                  )
               )) {
               i--;
            }
         } else if (CFG.game.getProvince(nProvinceID).getCivID() == CFG.game.getProvince(nProvinceID).getPopulationData().getCivID(i)) {
            if (CFG.game
               .getProvince(nProvinceID)
               .getPopulationData()
               .setPopulationOfCivID(
                  CFG.game.getProvince(nProvinceID).getPopulationData().getCivID(i),
                  (int)(
                     CFG.game.getProvince(nProvinceID).getPopulationData().getPopulationID(i)
                        - Math.ceil(iLosses * ((float)CFG.game.getProvince(nProvinceID).getPopulationData().getPopulationID(i) / nRecuritedPop))
                  )
               )) {
               i--;
            }
         } else if ((int)CFG.game
               .getCivRelation_OfCivB(CFG.game.getProvince(nProvinceID).getCivID(), CFG.game.getProvince(nProvinceID).getPopulationData().getCivID(i))
            == -100) {
            if (CFG.game
               .getProvince(nProvinceID)
               .getPopulationData()
               .setPopulationOfCivID(
                  CFG.game.getProvince(nProvinceID).getPopulationData().getCivID(i),
                  (int)(
                     CFG.game.getProvince(nProvinceID).getPopulationData().getPopulationID(i)
                        - Math.floor(iLosses * ((float)CFG.game.getProvince(nProvinceID).getPopulationData().getPopulationID(i) / nRecuritedPop))
                  )
               )) {
               i--;
            }
         } else if (CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getAllianceID() > 0
            && CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getAllianceID()
               == CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getPopulationData().getCivID(i)).getAllianceID()) {
            if (CFG.game
               .getProvince(nProvinceID)
               .getPopulationData()
               .setPopulationOfCivID(
                  CFG.game.getProvince(nProvinceID).getPopulationData().getCivID(i),
                  (int)(
                     CFG.game.getProvince(nProvinceID).getPopulationData().getPopulationID(i)
                        - Math.floor(iLosses * ((float)CFG.game.getProvince(nProvinceID).getPopulationData().getPopulationID(i) / nRecuritedPop))
                  )
               )) {
               i--;
            }
         } else if (CFG.game
            .getProvince(nProvinceID)
            .getPopulationData()
            .setPopulationOfCivID(
               CFG.game.getProvince(nProvinceID).getPopulationData().getCivID(i),
               (int)(
                  CFG.game.getProvince(nProvinceID).getPopulationData().getPopulationID(i)
                     - Math.floor(iLosses * ((float)CFG.game.getProvince(nProvinceID).getPopulationData().getPopulationID(i) / nRecuritedPop))
               )
            )) {
            i--;
         }
      }

      if ((nRecuritedPop = nRecuritedPop - CFG.game.getProvince(nProvinceID).getPopulationData().getPopulation()) < iLosses) {
         nRecuritedPop = iLosses - nRecuritedPop;
         int tPop = 0;

         for (int var6 = 0; var6 < CFG.game.getProvince(nProvinceID).getPopulationData().getNationalitiesSize(); var6++) {
            tPop = Math.min(nRecuritedPop, CFG.game.getProvince(nProvinceID).getPopulationData().getPopulationID(var6));
            if (CFG.game
               .getProvince(nProvinceID)
               .getPopulationData()
               .setPopulationOfCivID(
                  CFG.game.getProvince(nProvinceID).getPopulationData().getCivID(var6),
                  CFG.game.getProvince(nProvinceID).getPopulationData().getPopulationID(var6)
                     - Math.min(nRecuritedPop, CFG.game.getProvince(nProvinceID).getPopulationData().getPopulationID(var6))
               )) {
               var6--;
            }

            if ((nRecuritedPop -= tPop) <= 0) {
               break;
            }
         }
      }
   }

   public final void recruitArmyInstantly(int nProvinceID, int nNumOfUnits, int nCivID) {
      if (CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getMovePoints()
         >= CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivID).getIdeologyID()).COST_OF_RECRUIT) {
         if (nNumOfUnits >= CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getMoney() / CFG.getCostOfRecruitArmyMoney_Instantly(nProvinceID)) {
            nNumOfUnits = (int)CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getMoney() / CFG.getCostOfRecruitArmyMoney_Instantly(nProvinceID);
         }

         if (nNumOfUnits >= this.getRecruitableArmy(nProvinceID)) {
            nNumOfUnits = this.getRecruitableArmy(nProvinceID);
         }

         if (nNumOfUnits > 0) {
            CFG.game
               .getCiv(nCivID)
               .setMovePoints(
                  CFG.game.getCiv(nCivID).getMovePoints() - CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivID).getIdeologyID()).COST_OF_RECRUIT
               );
            CFG.game.getCiv(nCivID).setMoney(CFG.game.getCiv(nCivID).getMoney() - (long)nNumOfUnits * CFG.getCostOfRecruitArmyMoney_Instantly(nProvinceID));
            this.recruitArmy(nProvinceID, nNumOfUnits, nCivID);
         }
      }
   }

   public final void recruitArmy(int nProvinceID, int nNumOfUnits, int nCivID) {
      if (nNumOfUnits >= this.getRecruitableArmy(nProvinceID)) {
         nNumOfUnits = this.getRecruitableArmy(nProvinceID);
      }

      if (nNumOfUnits > 0) {
         int tempProvincePopulation = CFG.game.getProvince(nProvinceID).getPopulationData().getPopulation();
         CFG.game
            .getProvince(nProvinceID)
            .setHappiness(CFG.game.getProvince(nProvinceID).getHappiness() - 0.3F * ((float)nNumOfUnits / tempProvincePopulation));
         CFG.game
            .getProvince(nProvinceID)
            .setEconomy(
               (int)(
                  CFG.game.getProvince(nProvinceID).getEconomy()
                     - CFG.game.getProvince(nProvinceID).getEconomy()
                        * (CFG.game.getProvince(nProvinceID).getIsCapital() ? 0.2875F : 0.575F + CFG.oR.nextInt(175) / 1000.0F)
                        * ((float)nNumOfUnits / tempProvincePopulation)
               )
            );
         CFG.game
            .getProvince(nProvinceID)
            .setDevelopmentLevel(
               CFG.game.getProvince(nProvinceID).getDevelopmentLevel()
                  - CFG.game.getProvince(nProvinceID).getDevelopmentLevel()
                     * (CFG.game.getProvince(nProvinceID).getIsCapital() ? 0.08125F : 0.1625F + CFG.oR.nextInt(125) / 1000.0F)
                     * ((float)nNumOfUnits / tempProvincePopulation)
            );
         CFG.game.getProvince(nProvinceID).updateArmy(CFG.game.getProvince(nProvinceID).getArmy(0) + Math.max(nNumOfUnits, 0));
         Save_Civ_GameData var10000 = CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).civGameData;
         var10000.iRecruitedArmy = var10000.iRecruitedArmy + Math.max(nNumOfUnits, 0);
         if (CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getControlledByPlayer()) {
            int nPlayerID = CFG.game.getPlayerID_ByCivID(CFG.game.getProvince(nProvinceID).getCivID());

            try {
               CFG.game
                  .getPlayer(nPlayerID)
                  .statistics_Civ_GameData
                  .setRecruitedArmy(CFG.game.getPlayer(nPlayerID).statistics_Civ_GameData.getRecruitedArmy() + Math.max(nNumOfUnits, 0));
            } catch (IndexOutOfBoundsException var8) {
               CFG.exceptionStack(var8);
            } catch (NullPointerException var9) {
               CFG.exceptionStack(var9);
            }
         }

         CFG.game
            .getCiv(CFG.game.getProvince(nProvinceID).getCivID())
            .setNumOfUnits(CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getNumOfUnits() + nNumOfUnits);
         int nRecuritedPop = tempProvincePopulation;

         for (int i = 0; i < CFG.game.getProvince(nProvinceID).getPopulationData().getNationalitiesSize(); i++) {
            if (CFG.game.getProvince(nProvinceID).getPopulationData().getCivID(i) == 0) {
               if (CFG.game
                  .getProvince(nProvinceID)
                  .getPopulationData()
                  .setPopulationOfCivID(
                     CFG.game.getProvince(nProvinceID).getPopulationData().getCivID(i),
                     (int)(
                        CFG.game.getProvince(nProvinceID).getPopulationData().getPopulationID(i)
                           - Math.floor(nNumOfUnits * ((float)CFG.game.getProvince(nProvinceID).getPopulationData().getPopulationID(i) / nRecuritedPop))
                     )
                  )) {
                  i--;
                  Gdx.app.log("GameAction", "recruit--1");
               }
            } else if (CFG.game.getProvince(nProvinceID).getCivID() == CFG.game.getProvince(nProvinceID).getPopulationData().getCivID(i)) {
               if (CFG.game
                  .getProvince(nProvinceID)
                  .getPopulationData()
                  .setPopulationOfCivID(
                     CFG.game.getProvince(nProvinceID).getPopulationData().getCivID(i),
                     (int)(
                        CFG.game.getProvince(nProvinceID).getPopulationData().getPopulationID(i)
                           - Math.ceil(nNumOfUnits * ((float)CFG.game.getProvince(nProvinceID).getPopulationData().getPopulationID(i) / nRecuritedPop))
                     )
                  )) {
                  i--;
                  Gdx.app.log("GameAction", "recruit--2");
               }
            } else if ((int)CFG.game
                  .getCivRelation_OfCivB(CFG.game.getProvince(nProvinceID).getCivID(), CFG.game.getProvince(nProvinceID).getPopulationData().getCivID(i))
               == -100) {
               if (CFG.game
                  .getProvince(nProvinceID)
                  .getPopulationData()
                  .setPopulationOfCivID(
                     CFG.game.getProvince(nProvinceID).getPopulationData().getCivID(i),
                     (int)(
                        CFG.game.getProvince(nProvinceID).getPopulationData().getPopulationID(i)
                           - Math.floor(nNumOfUnits * ((float)CFG.game.getProvince(nProvinceID).getPopulationData().getPopulationID(i) / nRecuritedPop))
                     )
                  )) {
                  i--;
                  Gdx.app.log("GameAction", "recruit--3");
               }
            } else if (CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getAllianceID() > 0
               && CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getAllianceID()
                  == CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getPopulationData().getCivID(i)).getAllianceID()) {
               if (CFG.game
                  .getProvince(nProvinceID)
                  .getPopulationData()
                  .setPopulationOfCivID(
                     CFG.game.getProvince(nProvinceID).getPopulationData().getCivID(i),
                     (int)(
                        CFG.game.getProvince(nProvinceID).getPopulationData().getPopulationID(i)
                           - Math.floor(nNumOfUnits * ((float)CFG.game.getProvince(nProvinceID).getPopulationData().getPopulationID(i) / nRecuritedPop))
                     )
                  )) {
                  i--;
                  Gdx.app.log("GameAction", "recruit--4");
               }
            } else if (CFG.game
               .getProvince(nProvinceID)
               .getPopulationData()
               .setPopulationOfCivID(
                  CFG.game.getProvince(nProvinceID).getPopulationData().getCivID(i),
                  (int)(
                     CFG.game.getProvince(nProvinceID).getPopulationData().getPopulationID(i)
                        - Math.floor(nNumOfUnits * ((float)CFG.game.getProvince(nProvinceID).getPopulationData().getPopulationID(i) / nRecuritedPop))
                  )
               )) {
               i--;
               Gdx.app.log("GameAction", "recruit--5");
            }
         }

         if ((nRecuritedPop = nRecuritedPop - CFG.game.getProvince(nProvinceID).getPopulationData().getPopulation()) < nNumOfUnits) {
            nRecuritedPop = nNumOfUnits - nRecuritedPop;
            int tPop = 0;

            for (int var10 = 0; var10 < CFG.game.getProvince(nProvinceID).getPopulationData().getNationalitiesSize(); var10++) {
               tPop = Math.min(nRecuritedPop, CFG.game.getProvince(nProvinceID).getPopulationData().getPopulationID(var10));
               if (CFG.game
                  .getProvince(nProvinceID)
                  .getPopulationData()
                  .setPopulationOfCivID(
                     CFG.game.getProvince(nProvinceID).getPopulationData().getCivID(var10),
                     CFG.game.getProvince(nProvinceID).getPopulationData().getPopulationID(var10)
                        - Math.min(nRecuritedPop, CFG.game.getProvince(nProvinceID).getPopulationData().getPopulationID(var10))
                  )) {
                  var10--;
               }

               if ((nRecuritedPop -= tPop) <= 0) {
                  break;
               }
            }
         }
      }
   }

   public final int getRecruitableArmy(int nProvinceID) {
      return this.getRecruitableArmy(nProvinceID, CFG.game.getProvince(nProvinceID).getCivID());
   }

   public final int getRecruitableArmy(int nProvinceID, int nCivID) {
      int nOut = 0;

      for (int i = 0; i < CFG.game.getProvince(nProvinceID).getPopulationData().getNationalitiesSize(); i++) {
         nOut = CFG.game.getProvince(nProvinceID).getPopulationData().getCivID(i) == 0
            ? (int)(nOut + CFG.game.getProvince(nProvinceID).getPopulationData().getPopulationID(i) * 0.0675F)
            : (
               nCivID == CFG.game.getProvince(nProvinceID).getPopulationData().getCivID(i)
                  ? (int)(nOut + CFG.game.getProvince(nProvinceID).getPopulationData().getPopulationID(i) * 0.175F)
                  : (
                     (int)CFG.game.getCivRelation_OfCivB(nCivID, CFG.game.getProvince(nProvinceID).getPopulationData().getCivID(i)) == -100
                        ? (int)(nOut + CFG.game.getProvince(nProvinceID).getPopulationData().getPopulationID(i) * 0.0025F)
                        : (
                           CFG.game.getCiv(nCivID).getAllianceID() > 0
                                 && CFG.game.getCiv(nCivID).getAllianceID()
                                    == CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getPopulationData().getCivID(i)).getAllianceID()
                              ? (int)(nOut + CFG.game.getProvince(nProvinceID).getPopulationData().getPopulationID(i) * 0.125F)
                              : (int)(nOut + CFG.game.getProvince(nProvinceID).getPopulationData().getPopulationID(i) * 0.00725F)
                        )
                  )
            );
      }

      return nOut;
   }

   public final void updateRecruitSlider() {
      try {
         int tMaxRecruit = 0;
         tMaxRecruit = (int)CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).getMoney()
            / CFG.getCostOfRecruitArmyMoney(CFG.game.getActiveProvinceID());
         if (tMaxRecruit < 0) {
            tMaxRecruit = 0;
         } else if (tMaxRecruit > this.getRecruitableArmy(CFG.game.getActiveProvinceID())) {
            tMaxRecruit = this.getRecruitableArmy(CFG.game.getActiveProvinceID());
         }

         int isRecruiting = CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).isRecruitingArmyInProvinceID(CFG.game.getActiveProvinceID());
         if (isRecruiting >= 0) {
            if ((tMaxRecruit = tMaxRecruit + CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getRecruitArmy(isRecruiting).getArmy())
               > this.getRecruitableArmy(CFG.game.getActiveProvinceID())) {
               tMaxRecruit = this.getRecruitableArmy(CFG.game.getActiveProvinceID());
            }

            CFG.menuManager.getInGame_ProvinceRecruit_Slider().setMax(tMaxRecruit);
            CFG.menuManager
               .getInGame_ProvinceRecruit_Slider()
               .setCurrent(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getRecruitArmy(isRecruiting).getArmy());
         } else {
            CFG.menuManager.getInGame_ProvinceRecruit_Slider().setMax(tMaxRecruit);
            CFG.menuManager.getInGame_ProvinceRecruit_Slider().setCurrent(tMaxRecruit);
         }
      } catch (IndexOutOfBoundsException var3) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var3);
         }

         CFG.menuManager.getInGame_ProvinceRecruit_Slider().setMax(0);
         CFG.menuManager.getInGame_ProvinceRecruit_Slider().setCurrent(0);
      }
   }

   public final void updateRecruitSlider_Instantly() {
      try {
         int tMaxRecruit = 0;
         tMaxRecruit = (int)CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).getMoney()
            / CFG.getCostOfRecruitArmyMoney_Instantly(CFG.game.getActiveProvinceID());
         if (tMaxRecruit < 0) {
            tMaxRecruit = 0;
         } else if (tMaxRecruit > this.getRecruitableArmy(CFG.game.getActiveProvinceID())) {
            tMaxRecruit = this.getRecruitableArmy(CFG.game.getActiveProvinceID());
         }

         CFG.menuManager.getInGame_ProvinceRecruitInstantly_Slider().setMax(tMaxRecruit);
         CFG.menuManager.getInGame_ProvinceRecruitInstantly_Slider().setCurrent(tMaxRecruit);
      } catch (IndexOutOfBoundsException var2) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var2);
         }

         CFG.menuManager.getInGame_ProvinceRecruitInstantly_Slider().setMax(0);
         CFG.menuManager.getInGame_ProvinceRecruitInstantly_Slider().setCurrent(0);
      }
   }

   public final void disbandArmy(int paramInt1, int paramInt2, int paramInt3) {
      if (paramInt2 >= 0) {
         int i = paramInt2;
         if (paramInt2 > CFG.game.getProvince(paramInt1).getArmyCivID(paramInt3)) {
            i = CFG.game.getProvince(paramInt1).getArmyCivID(paramInt3);
         }

         if (i > 0
            && CFG.game.getCiv(paramInt3).getMovePoints() >= CFG.ideologiesManager.getIdeology(CFG.game.getCiv(paramInt3).getIdeologyID()).COST_OF_DISBAND) {
            CFG.game
               .getCiv(paramInt3)
               .setMovePoints(
                  CFG.game.getCiv(paramInt3).getMovePoints() - CFG.ideologiesManager.getIdeology(CFG.game.getCiv(paramInt3).getIdeologyID()).COST_OF_DISBAND
               );
            int j = Math.min(CFG.game.getProvince(paramInt1).getArmyCivID(paramInt3), i);
            if (j > 0) {
               CFG.game.getCiv(paramInt3).setNumOfUnits(CFG.game.getCiv(paramInt3).getNumOfUnits() - j);
               CFG.game.getProvince(paramInt1).updateArmy(paramInt3, CFG.game.getProvince(paramInt1).getArmyCivID(paramInt3) - j);
               i = CFG.game.getCiv(paramInt3).getManPower();
               paramInt2 = j / 5;
               CFG.game.getCiv(paramInt3).setManPower(paramInt2 + i);
               paramInt2 = 1;
               int k = 0;

               while (k < CFG.game.getProvince(paramInt1).getNeighboringProvincesSize()) {
                  i = paramInt2;
                  if (CFG.game.getProvince(CFG.game.getProvince(paramInt1).getNeighboringProvinces(k)).getCivID() == paramInt3) {
                     i = paramInt2 + 1;
                  }

                  k++;
                  paramInt2 = i;
               }

               k = (int)Math.ceil(j * 1.0F);
               i = j - k;
               CFG.game
                  .getProvince(paramInt1)
                  .getPopulationData()
                  .setPopulationOfCivID(
                     paramInt3,
                     CFG.game.getProvince(paramInt1).getPopulationData().getPopulationOfCivID(paramInt3) + Math.min((int)Math.ceil(k / paramInt2), 100)
                  );
               k -= (int)Math.ceil(k / paramInt2);
               j = paramInt2 - 1;
               paramInt2 = i;
               if (j > 0) {
                  for (int var12 = 0; var12 < CFG.game.getProvince(paramInt1).getNeighboringProvincesSize(); var12++) {
                     if (CFG.game.getProvince(CFG.game.getProvince(paramInt1).getNeighboringProvinces(var12)).getCivID() == paramInt3) {
                        CFG.game
                           .getProvince(paramInt1)
                           .getPopulationData()
                           .setPopulationOfCivID(
                              CFG.game.getProvince(CFG.game.getProvince(paramInt1).getNeighboringProvinces(var12)).getCivID(),
                              CFG.game
                                    .getProvince(paramInt1)
                                    .getPopulationData()
                                    .getPopulationOfCivID(CFG.game.getProvince(CFG.game.getProvince(paramInt1).getNeighboringProvinces(var12)).getCivID())
                                 + Math.min(k / j, 100)
                           );
                     }
                  }

                  paramInt2 = i + k - k / j * j;
               }

               if (CFG.game.getCiv(paramInt3).getNumOfProvinces() > 0) {
                  paramInt1 = (int)Math.floor(paramInt2 / CFG.game.getCiv(paramInt3).getNumOfProvinces());
                  CFG.game
                     .getProvince(CFG.game.getCiv(paramInt3).getProvinceID(0))
                     .getPopulationData()
                     .setPopulationOfCivID(
                        paramInt3,
                        CFG.game.getProvince(CFG.game.getCiv(paramInt3).getProvinceID(0)).getPopulationData().getPopulationOfCivID(paramInt3)
                           + Math.min((int)Math.ceil(paramInt2 / CFG.game.getCiv(paramInt3).getNumOfProvinces()), 100)
                     );

                  for (int var13 = 1; var13 < CFG.game.getCiv(paramInt3).getNumOfProvinces(); var13++) {
                     Province_Population province_Population = CFG.game.getProvince(CFG.game.getCiv(paramInt3).getProvinceID(var13)).getPopulationData();
                     i = CFG.game.getProvince(CFG.game.getCiv(paramInt3).getProvinceID(var13)).getPopulationData().getPopulationOfCivID(paramInt3);
                     paramInt1 = Math.min(paramInt1, 100);
                     province_Population.setPopulationOfCivID(paramInt3, i + paramInt1);
                  }

                  return;
               }

               CFG.game
                  .getProvince(paramInt1)
                  .getPopulationData()
                  .setPopulationOfCivID(
                     paramInt3, CFG.game.getProvince(paramInt1).getPopulationData().getPopulationOfCivID(paramInt3) + Math.min(paramInt2, 100)
                  );
            }
         }
      }
   }

   public final void updateDisbandSlider() {
      CFG.menuManager
         .getInGame_ProvinceDisband_Slider()
         .setMax(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getArmyCivID(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()));
      CFG.menuManager
         .getInGame_ProvinceDisband_Slider()
         .setCurrent(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getArmyCivID(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()));
   }

   public final void updateInGame_Date() {
      CFG.menuManager.getInGame().getMenuElement(4).setWidth(1);
      CFG.menuManager.getInGame().getMenuElement(4).setText(Game_Calendar.getCurrentDate());
      CFG.menuManager.getInGame().getMenuElement(5).setWidth(1);
      CFG.menuManager.getInGame().getMenuElement(5).setText(CFG.langManager.get("Turn") + ": " + Game_Calendar.TURN_ID);
   }

   public final void updateInGame_ProvinceInfo() {
      try {
         CFG.ACTIVE_PROVINCE_INFO = CFG.chosenProvinceID >= 0 ? CFG.chosenProvinceID : CFG.game.getActiveProvinceID();
         if (CFG.ACTIVE_PROVINCE_INFO < 0) {
            Menu_InGame_ProvinceInfo.iMaxWidth = 0;
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(1).setVisible(false);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(2).setVisible(false);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(3).setVisible(false);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(4).setVisible(false);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(5).setVisible(false);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(6).setVisible(false);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(7).setVisible(false);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(8).setVisible(false);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(9).setVisible(false);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(10).setVisible(false);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(11).setVisible(false);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(12).setVisible(false);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(13).setVisible(false);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(14).setVisible(false);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(15).setVisible(false);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(16).setVisible(false);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(17).setVisible(false);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(18).setVisible(false);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(19).setVisible(false);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(20).setVisible(false);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(21).setVisible(false);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(22).setVisible(false);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(23).setVisible(false);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(24).setVisible(false);
            return;
         }

         if (CFG.FOG_OF_WAR == 2 && !CFG.getMetProvince(CFG.ACTIVE_PROVINCE_INFO)) {
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(2).setVisible(true);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(2).setText(CFG.langManager.get("Undiscovered"));
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(2).setCurrent(-3);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(6).setVisible(true);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(1).setVisible(false);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(3).setVisible(false);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(8).setVisible(false);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(4).setVisible(false);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(5).setVisible(false);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(7).setVisible(false);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(9).setVisible(false);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(10).setVisible(false);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(11).setVisible(false);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(12).setVisible(false);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(13).setVisible(false);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(14).setVisible(false);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(15).setVisible(false);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(16).setVisible(false);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(17).setVisible(false);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(18).setVisible(false);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(19).setVisible(false);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(20).setVisible(false);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(21).setVisible(false);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(22).setVisible(false);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(23).setVisible(false);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(24).setVisible(false);
         } else if (CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getWasteland() >= 0) {
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(2).setVisible(true);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(2).setText(CFG.langManager.get("Wasteland"));
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(2).setCurrent(-2);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(6).setVisible(true);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(1).setVisible(false);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(3).setVisible(false);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(8).setVisible(false);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(4).setVisible(false);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(5).setVisible(false);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(7).setVisible(false);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(9).setVisible(false);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(10).setVisible(false);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(11).setVisible(false);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(12).setVisible(false);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(13).setVisible(false);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(14).setVisible(false);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(15).setVisible(false);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(16).setVisible(false);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(17).setVisible(false);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(18).setVisible(false);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(19).setVisible(false);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(20).setVisible(false);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(21).setVisible(false);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(22).setVisible(false);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(23).setVisible(false);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(24).setVisible(true);
            CFG.menuManager
               .getInGame_ProvinceInfo()
               .getMenuElement(24)
               .setText(
                  String.valueOf(
                     CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getNeighboringProvincesSize()
                        + CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getNeighboringSeaProvincesSize()
                  )
               );
         } else if (CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getSeaProvince()) {
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(2).setVisible(true);
            CFG.menuManager
               .getInGame_ProvinceInfo()
               .getMenuElement(2)
               .setText(
                  CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getName().length() > 0
                     ? CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getName()
                     : CFG.langManager.get("Sea")
               );
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(2).setCurrent(-1);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(6).setVisible(true);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(1).setVisible(false);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(3).setVisible(false);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(8).setVisible(false);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(4).setVisible(false);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(5).setVisible(false);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(7).setVisible(false);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(9).setVisible(false);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(10).setVisible(false);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(11).setVisible(false);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(12).setVisible(false);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(13).setVisible(false);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(14).setVisible(false);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(15).setVisible(false);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(16).setVisible(false);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(17).setVisible(false);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(18).setVisible(false);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(19).setVisible(false);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(20).setVisible(false);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(21).setVisible(false);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(22).setVisible(false);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(23).setVisible(false);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(24).setVisible(true);
            CFG.menuManager
               .getInGame_ProvinceInfo()
               .getMenuElement(24)
               .setText(
                  String.valueOf(
                     CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getNeighboringProvincesSize()
                        + CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getNeighboringSeaProvincesSize()
                  )
               );
         } else {
            if (CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getName().length() > 0) {
               CFG.game.updateProvinceNameWidth(CFG.ACTIVE_PROVINCE_INFO);
               CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(1).setVisible(true);
               CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(1).setText(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getName());
            } else {
               CFG.game.updateProvinceNameWidth("Fokus");
               CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(1).setVisible(true);
               CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(1).setText(CFG.langManager.get("Fokus"));
            }

            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(2).setVisible(true);
            CFG.menuManager
               .getInGame_ProvinceInfo()
               .getMenuElement(2)
               .setText(CFG.game.getCiv(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getCivID()).getCivName());
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(2).setCurrent(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getCivID());
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(3).setVisible(true);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(3).setCurrent(CFG.ACTIVE_PROVINCE_INFO);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(8).setVisible(true);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(8).setText(String.valueOf(CFG.game.getProvinceValue(CFG.ACTIVE_PROVINCE_INFO)));
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(4).setVisible(true);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(4).setCurrent(CFG.ACTIVE_PROVINCE_INFO);
            CFG.menuManager
               .getInGame_ProvinceInfo()
               .getMenuElement(4)
               .setText(String.valueOf(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getPopulationData().getPopulation()));
            CFG.menuManager.updateInGame_ProvinceInfoGraph(CFG.ACTIVE_PROVINCE_INFO);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(6).setVisible(true);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(9).setVisible(true);
            CFG.menuManager
               .getInGame_ProvinceInfo()
               .getMenuElement(9)
               .setCurrent((int)(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getGrowthRate_Population_WithFarm_WithTerrain() * 100.0F));
            CFG.menuManager
               .getInGame_ProvinceInfo()
               .getMenuElement(9)
               .setText((int)(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getGrowthRate_Population_WithFarm_WithTerrain() * 100.0F) + "%");
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(7).setVisible(true);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(7).setText(String.valueOf(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getEconomy()));
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(10).setVisible(true);
            CFG.menuManager
               .getInGame_ProvinceInfo()
               .getMenuElement(10)
               .setCurrent((int)(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getDevelopmentLevel() * 100.0F));
            CFG.menuManager
               .getInGame_ProvinceInfo()
               .getMenuElement(10)
               .setText(String.valueOf((int)(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getDevelopmentLevel() * 100.0F) / 100.0F));
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(11).setVisible(true);
            CFG.menuManager
               .getInGame_ProvinceInfo()
               .getMenuElement(11)
               .setCurrent((int)(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getHappiness() * 100.0F));
            CFG.menuManager
               .getInGame_ProvinceInfo()
               .getMenuElement(11)
               .setText((int)(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getHappiness() * 100.0F) + "%");
            CFG.menuManager
               .getInGame_ProvinceInfo()
               .getMenuElement(12)
               .setVisible(CFG.game.getCiv(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getCivID()).isFestivalOrganized(CFG.ACTIVE_PROVINCE_INFO));
            if (CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(12).getVisible()) {
               CFG.menuManager
                  .getInGame_ProvinceInfo()
                  .getMenuElement(12)
                  .setText(
                     String.valueOf(
                        CFG.game.getCiv(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getCivID()).isFestivalOrganized_TurnsLeft(CFG.ACTIVE_PROVINCE_INFO)
                     )
                  );
            }

            if (CFG.menuManager.getVisibleInGame_CensusOfProvince()) {
               CFG.menuManager.rebuildInGame_CensusOfProvince(CFG.ACTIVE_PROVINCE_INFO);
            }

            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(13).setVisible(true);
            CFG.menuManager
               .getInGame_ProvinceInfo()
               .getMenuElement(13)
               .setCurrent((int)(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getProvinceStability() * 100.0F));
            CFG.menuManager
               .getInGame_ProvinceInfo()
               .getMenuElement(13)
               .setText((int)(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getProvinceStability() * 100.0F) + "%");
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(14).setVisible(true);
            Menu_InGame_ProvinceInfo.updateBuildingsList(CFG.ACTIVE_PROVINCE_INFO);
            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(15).setVisible(true);
            CFG.menuManager
               .getInGame_ProvinceInfo()
               .getMenuElement(15)
               .setCurrent((int)(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getRevolutionaryRisk() * 100.0F));
            CFG.menuManager
               .getInGame_ProvinceInfo()
               .getMenuElement(15)
               .setText((int)(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getRevolutionaryRisk() * 100.0F) + "%");
            CFG.menuManager
               .getInGame_ProvinceInfo()
               .getMenuElement(16)
               .setVisible(CFG.game.getCiv(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getCivID()).isAssimilateOrganized(CFG.ACTIVE_PROVINCE_INFO));
            if (CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(16).getVisible()) {
               CFG.menuManager
                  .getInGame_ProvinceInfo()
                  .getMenuElement(16)
                  .setText(
                     String.valueOf(
                        CFG.game.getCiv(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getCivID()).isAssimialateOrganized_TurnsLeft(CFG.ACTIVE_PROVINCE_INFO)
                     )
                  );
            }

            CFG.menuManager
               .getInGame_ProvinceInfo()
               .getMenuElement(17)
               .setVisible(CFG.game.getCiv(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getCivID()).isInvestOrganized(CFG.ACTIVE_PROVINCE_INFO));
            if (CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(17).getVisible()) {
               CFG.menuManager
                  .getInGame_ProvinceInfo()
                  .getMenuElement(17)
                  .setText(
                     String.valueOf(
                        CFG.game.getCiv(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getCivID()).isInvestOrganized_TurnsLeft(CFG.ACTIVE_PROVINCE_INFO)
                     )
                  );
            }

            CFG.menuManager
               .getInGame_ProvinceInfo()
               .getMenuElement(18)
               .setVisible(CFG.game.getCiv(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getCivID()).isInvestOrganized_Development(CFG.ACTIVE_PROVINCE_INFO));
            if (CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(18).getVisible()) {
               CFG.menuManager
                  .getInGame_ProvinceInfo()
                  .getMenuElement(18)
                  .setText(
                     String.valueOf(
                        CFG.game
                           .getCiv(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getCivID())
                           .isInvestOrganized_TurnsLeft_Development(CFG.ACTIVE_PROVINCE_INFO)
                     )
                  );
            }

            CFG.menuManager
               .getInGame_ProvinceInfo()
               .getMenuElement(19)
               .setVisible(
                  !CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getIsSupplied()
                     && CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getIsNotSuppliedForXTurns() > 0
               );
            if (CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(19).getVisible()) {
               CFG.menuManager
                  .getInGame_ProvinceInfo()
                  .getMenuElement(19)
                  .setText(String.valueOf(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getIsNotSuppliedForXTurns()));
            }

            if (!CFG.SPECTATOR_MODE && CFG.FOG_OF_WAR != 0 && !CFG.game.getPlayer(CFG.PLAYER_TURNID).getFogOfWar(CFG.ACTIVE_PROVINCE_INFO)) {
               CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(20).setVisible(false);
            } else {
               CFG.menuManager
                  .getInGame_ProvinceInfo()
                  .getMenuElement(20)
                  .setVisible(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getDefensivePosition() > 0);
               if (CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(20).getVisible()) {
                  CFG.menuManager
                     .getInGame_ProvinceInfo()
                     .getMenuElement(20)
                     .setText(String.valueOf(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getDefensivePosition()));
               }
            }

            CFG.menuManager
               .getInGame_ProvinceInfo()
               .getMenuElement(21)
               .setVisible(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).saveProvinceData.provincePlague != null);
            if (CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(21).getVisible()) {
               CFG.menuManager
                  .getInGame_ProvinceInfo()
                  .getMenuElement(21)
                  .setText(String.valueOf(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).saveProvinceData.provincePlague.iDeaths));
            }

            CFG.menuManager
               .getInGame_ProvinceInfo()
               .getMenuElement(22)
               .setVisible(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).saveProvinceData.iNewColonyBonus > 0);
            if (CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(22).getVisible()) {
               CFG.menuManager
                  .getInGame_ProvinceInfo()
                  .getMenuElement(22)
                  .setText(String.valueOf(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).saveProvinceData.iNewColonyBonus));
            }

            CFG.menuManager
               .getInGame_ProvinceInfo()
               .getMenuElement(23)
               .setVisible(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).saveProvinceData.iSupportRebelsSize > 0);
            if (CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(23).getVisible()) {
               CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(23).setCurrent(CFG.ACTIVE_PROVINCE_INFO);
            }

            CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(24).setVisible(true);
            CFG.menuManager
               .getInGame_ProvinceInfo()
               .getMenuElement(24)
               .setText(
                  String.valueOf(
                     CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getNeighboringProvincesSize()
                        + CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getNeighboringSeaProvincesSize()
                  )
               );
            if (!CFG.SPECTATOR_MODE && Game_Calendar.ENABLE_COLONIZATION_NEUTRAL_PROVINCES && CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getCivID() == 0) {
               CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(1).setVisible(false);
               CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(4).setVisible(false);
               CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(7).setVisible(false);
               CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(8).setVisible(false);
               CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(9).setVisible(false);
               CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(10).setVisible(false);
               CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(11).setVisible(false);
               CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(12).setVisible(false);
               CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(13).setVisible(false);
               CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(14).setVisible(false);
               CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(15).setVisible(false);
               CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(16).setVisible(false);
               CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(17).setVisible(false);
               CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(18).setVisible(false);
               CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(19).setVisible(false);
               CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(20).setVisible(false);
               CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(21).setVisible(false);
               CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(22).setVisible(false);
               CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(23).setVisible(false);
            }
         }
      } catch (IndexOutOfBoundsException var2) {
         CFG.exceptionStack(var2);
      }

      this.updateInGame_ProvinceInfo_PosX();
   }

   public final void updateInGame_ProvinceInfo_PosX() {
      try {
         CFG.menuManager
            .getInGame_ProvinceInfo()
            .getMenuElement(3)
            .setPosX(
               CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(2).getPosX()
                  + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(2).getWidth()
                  - ImageManager.getImage(Images.bot_left).getWidth() / 2
            );
         CFG.menuManager
            .getInGame_ProvinceInfo()
            .getMenuElement(8)
            .setPosX(
               CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(3).getPosX()
                  + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(3).getWidth()
                  - ImageManager.getImage(Images.bot_left).getWidth() / 2
            );
         CFG.menuManager
            .getInGame_ProvinceInfo()
            .getMenuElement(9)
            .setPosX(
               CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(4).getPosX()
                  + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(4).getWidth()
                  + CFG.PADDING
            );
         CFG.menuManager
            .getInGame_ProvinceInfo()
            .getMenuElement(10)
            .setPosX(
               CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(7).getPosX()
                  + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(7).getWidth()
                  + CFG.PADDING
            );
         CFG.menuManager
            .getInGame_ProvinceInfo()
            .getMenuElement(11)
            .setPosX(
               CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(9).getPosX()
                  + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(9).getWidth()
                  + CFG.PADDING
            );
         CFG.menuManager
            .getInGame_ProvinceInfo()
            .getMenuElement(12)
            .setPosX(
               CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(8).getPosX()
                  + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(8).getWidth()
                  - ImageManager.getImage(Images.bot_left).getWidth() / 2
            );
         CFG.menuManager
            .getInGame_ProvinceInfo()
            .getMenuElement(13)
            .setPosX(
               CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(10).getPosX()
                  + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(10).getWidth()
                  + CFG.PADDING
            );
         CFG.menuManager
            .getInGame_ProvinceInfo()
            .getMenuElement(15)
            .setPosX(
               CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(13).getPosX()
                  + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(13).getWidth()
                  + CFG.PADDING
            );
         CFG.menuManager
            .getInGame_ProvinceInfo()
            .getMenuElement(16)
            .setPosX(
               (
                     CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(12).getVisible()
                        ? CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(12).getPosX()
                           + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(12).getWidth()
                        : CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(8).getPosX()
                           + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(8).getWidth()
                  )
                  - ImageManager.getImage(Images.bot_left).getWidth() / 2
            );
         CFG.menuManager
            .getInGame_ProvinceInfo()
            .getMenuElement(17)
            .setPosX(
               (
                     CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(16).getVisible()
                        ? CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(16).getPosX()
                           + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(16).getWidth()
                        : (
                           CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(12).getVisible()
                              ? CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(12).getPosX()
                                 + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(12).getWidth()
                              : CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(8).getPosX()
                                 + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(8).getWidth()
                        )
                  )
                  - ImageManager.getImage(Images.bot_left).getWidth() / 2
            );
         CFG.menuManager
            .getInGame_ProvinceInfo()
            .getMenuElement(18)
            .setPosX(
               (
                     CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(17).getVisible()
                        ? CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(17).getPosX()
                           + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(17).getWidth()
                        : (
                           CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(16).getVisible()
                              ? CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(16).getPosX()
                                 + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(16).getWidth()
                              : (
                                 CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(12).getVisible()
                                    ? CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(12).getPosX()
                                       + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(12).getWidth()
                                    : CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(8).getPosX()
                                       + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(8).getWidth()
                              )
                        )
                  )
                  - ImageManager.getImage(Images.bot_left).getWidth() / 2
            );
         CFG.menuManager
            .getInGame_ProvinceInfo()
            .getMenuElement(19)
            .setPosX(
               (
                     CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(18).getVisible()
                        ? CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(18).getPosX()
                           + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(18).getWidth()
                        : (
                           CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(17).getVisible()
                              ? CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(17).getPosX()
                                 + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(17).getWidth()
                              : (
                                 CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(16).getVisible()
                                    ? CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(16).getPosX()
                                       + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(16).getWidth()
                                    : (
                                       CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(12).getVisible()
                                          ? CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(12).getPosX()
                                             + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(12).getWidth()
                                          : CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(8).getPosX()
                                             + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(8).getWidth()
                                    )
                              )
                        )
                  )
                  - ImageManager.getImage(Images.bot_left).getWidth() / 2
            );
         CFG.menuManager
            .getInGame_ProvinceInfo()
            .getMenuElement(20)
            .setPosX(
               (
                     CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(19).getVisible()
                        ? CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(19).getPosX()
                           + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(19).getWidth()
                        : (
                           CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(18).getVisible()
                              ? CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(18).getPosX()
                                 + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(18).getWidth()
                              : (
                                 CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(17).getVisible()
                                    ? CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(17).getPosX()
                                       + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(17).getWidth()
                                    : (
                                       CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(16).getVisible()
                                          ? CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(16).getPosX()
                                             + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(16).getWidth()
                                          : (
                                             CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(12).getVisible()
                                                ? CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(12).getPosX()
                                                   + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(12).getWidth()
                                                : CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(8).getPosX()
                                                   + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(8).getWidth()
                                          )
                                    )
                              )
                        )
                  )
                  - ImageManager.getImage(Images.bot_left).getWidth() / 2
            );
         CFG.menuManager
            .getInGame_ProvinceInfo()
            .getMenuElement(21)
            .setPosX(
               (
                     CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(20).getVisible()
                        ? CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(20).getPosX()
                           + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(20).getWidth()
                        : (
                           CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(19).getVisible()
                              ? CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(19).getPosX()
                                 + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(19).getWidth()
                              : (
                                 CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(18).getVisible()
                                    ? CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(18).getPosX()
                                       + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(18).getWidth()
                                    : (
                                       CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(17).getVisible()
                                          ? CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(17).getPosX()
                                             + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(17).getWidth()
                                          : (
                                             CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(16).getVisible()
                                                ? CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(16).getPosX()
                                                   + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(16).getWidth()
                                                : (
                                                   CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(12).getVisible()
                                                      ? CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(12).getPosX()
                                                         + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(12).getWidth()
                                                      : CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(8).getPosX()
                                                         + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(8).getWidth()
                                                )
                                          )
                                    )
                              )
                        )
                  )
                  - ImageManager.getImage(Images.bot_left).getWidth() / 2
            );
         CFG.menuManager
            .getInGame_ProvinceInfo()
            .getMenuElement(22)
            .setPosX(
               (
                     CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(21).getVisible()
                        ? CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(21).getPosX()
                           + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(21).getWidth()
                        : (
                           CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(20).getVisible()
                              ? CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(20).getPosX()
                                 + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(20).getWidth()
                              : (
                                 CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(19).getVisible()
                                    ? CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(19).getPosX()
                                       + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(19).getWidth()
                                    : (
                                       CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(18).getVisible()
                                          ? CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(18).getPosX()
                                             + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(18).getWidth()
                                          : (
                                             CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(17).getVisible()
                                                ? CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(17).getPosX()
                                                   + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(17).getWidth()
                                                : (
                                                   CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(16).getVisible()
                                                      ? CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(16).getPosX()
                                                         + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(16).getWidth()
                                                      : (
                                                         CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(12).getVisible()
                                                            ? CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(12).getPosX()
                                                               + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(12).getWidth()
                                                            : CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(8).getPosX()
                                                               + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(8).getWidth()
                                                      )
                                                )
                                          )
                                    )
                              )
                        )
                  )
                  - ImageManager.getImage(Images.bot_left).getWidth() / 2
            );
         CFG.menuManager
            .getInGame_ProvinceInfo()
            .getMenuElement(23)
            .setPosX(
               (
                     CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(22).getVisible()
                        ? CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(22).getPosX()
                           + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(22).getWidth()
                        : (
                           CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(21).getVisible()
                              ? CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(21).getPosX()
                                 + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(21).getWidth()
                              : (
                                 CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(20).getVisible()
                                    ? CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(20).getPosX()
                                       + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(20).getWidth()
                                    : (
                                       CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(19).getVisible()
                                          ? CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(19).getPosX()
                                             + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(19).getWidth()
                                          : (
                                             CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(18).getVisible()
                                                ? CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(18).getPosX()
                                                   + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(18).getWidth()
                                                : (
                                                   CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(17).getVisible()
                                                      ? CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(17).getPosX()
                                                         + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(17).getWidth()
                                                      : (
                                                         CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(16).getVisible()
                                                            ? CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(16).getPosX()
                                                               + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(16).getWidth()
                                                            : (
                                                               CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(12).getVisible()
                                                                  ? CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(12).getPosX()
                                                                     + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(12).getWidth()
                                                                  : CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(8).getPosX()
                                                                     + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(8).getWidth()
                                                            )
                                                      )
                                                )
                                          )
                                    )
                              )
                        )
                  )
                  - ImageManager.getImage(Images.bot_left).getWidth() / 2
            );
         Menu_InGame_ProvinceInfo.iMaxWidth = 1;
         if (CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(2).getVisible()) {
            Menu_InGame_ProvinceInfo.iMaxWidth = Math.max(
               CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(2).getPosX() + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(2).getWidth(),
               Menu_InGame_ProvinceInfo.iMaxWidth
            );
         }

         if (CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(3).getVisible()) {
            Menu_InGame_ProvinceInfo.iMaxWidth = Math.max(
               CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(3).getPosX() + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(3).getWidth(),
               Menu_InGame_ProvinceInfo.iMaxWidth
            );
         }

         if (CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(8).getVisible()) {
            Menu_InGame_ProvinceInfo.iMaxWidth = Math.max(
               CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(8).getPosX() + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(8).getWidth(),
               Menu_InGame_ProvinceInfo.iMaxWidth
            );
         }

         if (CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(9).getVisible()) {
            Menu_InGame_ProvinceInfo.iMaxWidth = Math.max(
               CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(9).getPosX() + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(9).getWidth(),
               Menu_InGame_ProvinceInfo.iMaxWidth
            );
         }

         if (CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(10).getVisible()) {
            Menu_InGame_ProvinceInfo.iMaxWidth = Math.max(
               CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(10).getPosX() + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(10).getWidth(),
               Menu_InGame_ProvinceInfo.iMaxWidth
            );
         }

         if (CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(11).getVisible()) {
            Menu_InGame_ProvinceInfo.iMaxWidth = Math.max(
               CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(11).getPosX() + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(11).getWidth(),
               Menu_InGame_ProvinceInfo.iMaxWidth
            );
         }

         if (CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(12).getVisible()) {
            Menu_InGame_ProvinceInfo.iMaxWidth = Math.max(
               CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(12).getPosX() + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(12).getWidth(),
               Menu_InGame_ProvinceInfo.iMaxWidth
            );
         }

         if (CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(13).getVisible()) {
            Menu_InGame_ProvinceInfo.iMaxWidth = Math.max(
               CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(13).getPosX() + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(13).getWidth(),
               Menu_InGame_ProvinceInfo.iMaxWidth
            );
         }

         if (CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(15).getVisible()) {
            Menu_InGame_ProvinceInfo.iMaxWidth = Math.max(
               CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(15).getPosX() + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(15).getWidth(),
               Menu_InGame_ProvinceInfo.iMaxWidth
            );
         }

         if (CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(16).getVisible()) {
            Menu_InGame_ProvinceInfo.iMaxWidth = Math.max(
               CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(16).getPosX() + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(16).getWidth(),
               Menu_InGame_ProvinceInfo.iMaxWidth
            );
         }

         if (CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(17).getVisible()) {
            Menu_InGame_ProvinceInfo.iMaxWidth = Math.max(
               CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(17).getPosX() + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(17).getWidth(),
               Menu_InGame_ProvinceInfo.iMaxWidth
            );
         }

         if (CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(18).getVisible()) {
            Menu_InGame_ProvinceInfo.iMaxWidth = Math.max(
               CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(18).getPosX() + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(18).getWidth(),
               Menu_InGame_ProvinceInfo.iMaxWidth
            );
         }

         if (CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(19).getVisible()) {
            Menu_InGame_ProvinceInfo.iMaxWidth = Math.max(
               CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(19).getPosX() + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(19).getWidth(),
               Menu_InGame_ProvinceInfo.iMaxWidth
            );
         }

         if (CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(20).getVisible()) {
            Menu_InGame_ProvinceInfo.iMaxWidth = Math.max(
               CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(20).getPosX() + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(20).getWidth(),
               Menu_InGame_ProvinceInfo.iMaxWidth
            );
         }

         if (CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(21).getVisible()) {
            Menu_InGame_ProvinceInfo.iMaxWidth = Math.max(
               CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(21).getPosX() + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(21).getWidth(),
               Menu_InGame_ProvinceInfo.iMaxWidth
            );
         }

         if (CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(22).getVisible()) {
            Menu_InGame_ProvinceInfo.iMaxWidth = Math.max(
               CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(22).getPosX() + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(22).getWidth(),
               Menu_InGame_ProvinceInfo.iMaxWidth
            );
         }

         if (CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(23).getVisible()) {
            Menu_InGame_ProvinceInfo.iMaxWidth = Math.max(
               CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(23).getPosX() + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(23).getWidth(),
               Menu_InGame_ProvinceInfo.iMaxWidth
            );
         }

         if ((Menu_InGame_ProvinceInfo.iMaxWidth = Menu_InGame_ProvinceInfo.iMaxWidth + CFG.PADDING * 2)
               + CFG.GAME_WIDTH
               - CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(5).getPosX()
               + CFG.PADDING * 2
            >= CFG.GAME_WIDTH * 0.8F) {
            Menu_InGame_ProvinceInfo.iMaxWidth = -1;
         }
      } catch (IndexOutOfBoundsException var2) {
      } catch (NullPointerException var3) {
      }
   }

   public final void buildRank_Score() {
      for (int i = 1; i < CFG.game.getCivsSize(); i++) {
         this.buildRank_Score(i);
      }

      this.buildRank_Positions();
   }

   public final void buildRank_Score(int nCivID) {
      CFG.game
         .getCiv(nCivID)
         .setRankScore(this.buildRank_Score_Population(nCivID) + this.buildRank_Score_Economy(nCivID) + this.buildRank_Score_Prestige(nCivID));
   }

   public final void buildRank_Positions() {
      ArrayList<Integer> tCivIDs = new ArrayList<>();
      if (CFG.game.getSortedCivsSize() > 0) {
         if (CFG.game.getSortedCivsSize() != CFG.game.getCivsSize() - 1) {
            CFG.game.sortCivilizationsAZ();
         }

         for (int i = 1; i < CFG.game.getCivsSize(); i++) {
            tCivIDs.add(CFG.game.getSortedCivsAZ(i - 1));
         }
      } else {
         for (int i = 1; i < CFG.game.getCivsSize(); i++) {
            tCivIDs.add(i);
         }
      }

      int tRank = 1;
      int tAddID = 0;

      while (tCivIDs.size() > 0) {
         tAddID = 0;

         for (int i2 = tCivIDs.size() - 1; i2 > 0; i2--) {
            if (CFG.game.getCiv(tCivIDs.get(tAddID)).getRankScore() < CFG.game.getCiv(tCivIDs.get(i2)).getRankScore()) {
               tAddID = i2;
            }
         }

         CFG.game.getCiv(tCivIDs.get(tAddID)).setRankPosition(tRank++);
         tCivIDs.remove(tAddID);
      }
   }

   public final int buildRank_Score_Population(int nCivID) {
      float nScore = 0.0F;
      float nTech = Math.min(1.0F, CFG.game.getCiv(nCivID).getTechnologyLevel());

      for (int i = 0; i < CFG.game.getCiv(nCivID).getNumOfProvinces(); i++) {
         for (int j = 0; j < CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getPopulationData().getNationalitiesSize(); j++) {
            nScore += CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getPopulationData().getPopulationID(j)
               / (CFG.game.getGameScenarios().getScenario_StartingPopulation() / 2.65F)
               * (CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getPopulationData().getCivID(j) == nCivID ? 1.0F : 0.275F)
               * (0.6F + 0.4F * CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getProvinceStability())
               * (0.625F + 0.375F * nTech)
               * (CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).isOccupied() ? 0.15F : 1.0F);
         }
      }

      return (int)Math.ceil(nScore);
   }

   public final int buildRank_Score_Economy(int nCivID) {
      float nScore = 0.0F;

      for (int i = 0; i < CFG.game.getCiv(nCivID).getNumOfProvinces(); i++) {
         nScore += CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getEconomy()
            / (CFG.game.getGameScenarios().getScenario_StartingEconomy() / 16.25F)
            * (0.425F + 0.675F * CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getDevelopmentLevel())
            * (0.275F + 0.725F * CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getProvinceStability());
      }

      return (int)Math.ceil(nScore);
   }

   public final int buildRank_Score_Prestige(int nCivID) {
      float nScore = 0.0F;
      float nTech = Math.min(1.0F, CFG.game.getCiv(nCivID).getTechnologyLevel());
      if (CFG.game.getCiv(nCivID).getNumOfProvinces() > 0) {
         for (int i = 0; i < CFG.game.getCiv(nCivID).getNumOfProvinces(); i++) {
            nScore += 2.25F
               * (0.125F + 0.875F * CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getGrowthRate_Population_WithFarm())
               * (0.785F + 0.215F * nTech)
               * (CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getCore().getHaveACore(nCivID) ? 1.0F : 0.475F)
               * (0.375F + 0.625F * CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getDevelopmentLevel());
         }

         nScore += 17.5F * CFG.game.getCiv(nCivID).getTechnologyLevel();
      }

      return (int)Math.ceil(nScore);
   }

   public final boolean moveCapital(int nCivID, int toProvinceID) {
      if (nCivID < 1 || toProvinceID < 0) {
         return false;
      } else if (!this.moveCapital_CanMove(nCivID)) {
         return false;
      } else if ((
            CFG.game.getCiv(nCivID).getCapitalProvinceID() < 0
               || CFG.game.getProvince(CFG.game.getCiv(nCivID).getCapitalProvinceID()).getCivID() == nCivID
               || !CFG.game.getCiv(nCivID).isAtWar()
               || !CFG.game.getCivsAtWar(CFG.game.getProvince(CFG.game.getCiv(nCivID).getCapitalProvinceID()).getCivID(), nCivID)
         )
         && CFG.game.getProvince(toProvinceID).getTrueOwnerOfProvince() == nCivID
         && CFG.game.getProvince(toProvinceID).getCivID() == nCivID
         && CFG.game.getCiv(nCivID).getCapitalProvinceID() != toProvinceID
         && CFG.game.getCiv(nCivID).getMoney() >= this.moveCapital_Cost(nCivID)) {
         CFG.game.getCiv(nCivID).setMoney(CFG.game.getCiv(nCivID).getMoney() - this.moveCapital_Cost(nCivID));
         CFG.game.getCiv(nCivID).setCapitalMoved_LastTurnID(Game_Calendar.TURN_ID);
         int tempOld = CFG.game.getCiv(nCivID).getCapitalProvinceID();
         CFG.game.getCiv(nCivID).setCapitalProvinceID(toProvinceID);
         CFG.game.getProvince(CFG.game.getCiv(nCivID).getCapitalProvinceID()).setIsCapital(false);
         if (tempOld >= 0) {
            CFG.game.getProvince(tempOld).setIsCapital(false);
            CFG.game.getProvince(tempOld).updateDrawArmy();
            CFG.game
               .getProvince(tempOld)
               .setHappiness(CFG.game.getProvince(tempOld).getHappiness() - CFG.game.getProvince(tempOld).getHappiness() * 0.35168F - 0.35168F);

            try {
               CFG.game.getProvince(tempOld).getCity(0).setCityLevel(CFG.getEditorCityLevel(1));
            } catch (IndexOutOfBoundsException var6) {
            }
         }

         CFG.game.getProvince(CFG.game.getCiv(nCivID).getCapitalProvinceID()).setIsCapital(true);
         CFG.game.getProvince(CFG.game.getCiv(nCivID).getCapitalProvinceID()).updateDrawArmy();
         CFG.gameAction.updateIsSupplied();
         CFG.game
            .getProvince(CFG.game.getCiv(nCivID).getCapitalProvinceID())
            .setHappiness(CFG.game.getProvince(CFG.game.getCiv(nCivID).getCapitalProvinceID()).getHappiness() + 0.15F);

         try {
            CFG.game.getProvince(CFG.game.getCiv(nCivID).getCapitalProvinceID()).getCity(0).setCityLevel(CFG.getEditorCityLevel(0));
         } catch (IndexOutOfBoundsException var5) {
         }

         CFG.game.getProvince(CFG.game.getCiv(nCivID).getCapitalProvinceID()).setDrawCities(true);
         return true;
      } else {
         return false;
      }
   }

   public final boolean moveCapital_CanMove(int nCivID) {
      return CFG.game.getCiv(nCivID).getCapitalProvinceID() >= 0
            && (
               CFG.game.getProvince(CFG.game.getCiv(nCivID).getCapitalProvinceID()).getCivID() == nCivID
                  || CFG.game.getProvince(CFG.game.getCiv(nCivID).getCapitalProvinceID()).isOccupied()
                     && CFG.game.getCivsAtWar(nCivID, CFG.game.getProvince(CFG.game.getCiv(nCivID).getCapitalProvinceID()).getCivID())
            )
         ? CFG.game.getCiv(nCivID).getCapitalMoved_LastTurnID() <= Game_Calendar.TURN_ID - 50
         : true;
   }

   public final int moveCapital_Cost(int nCivID) {
      return CFG.game.getCiv(nCivID).getCapitalProvinceID() >= 0
            && (
               CFG.game.getProvince(CFG.game.getCiv(nCivID).getCapitalProvinceID()).getCivID() == nCivID
                  || CFG.game.getProvince(CFG.game.getCiv(nCivID).getCapitalProvinceID()).isOccupied()
                     && CFG.game.getCivsAtWar(nCivID, CFG.game.getProvince(CFG.game.getCiv(nCivID).getCapitalProvinceID()).getCivID())
            )
         ? 1
            + (int)(
               CFG.game.getGameScenarios().getScenario_StartingPopulation() * 0.1925F
                  + CFG.game.getProvince(CFG.game.getCiv(nCivID).getCapitalProvinceID()).getPopulationData().getPopulation() * 0.125F
                  + (
                        CFG.game_NextTurnUpdate.getProvinceIncome_Taxation(CFG.game.getCiv(nCivID).getCapitalProvinceID())
                           + CFG.game_NextTurnUpdate.getProvinceIncome_Production(CFG.game.getCiv(nCivID).getCapitalProvinceID())
                     )
                     * (2.1348F + 1.86584F * CFG.game.getCiv(nCivID).getTechnologyLevel())
            )
         : 25;
   }

   public final boolean abadonProvince(int nProvinceID, int nCivID) {
      if (CFG.game.getProvince(nProvinceID).getCivID() == nCivID
         && CFG.game.getCiv(nCivID).getCapitalProvinceID() != nProvinceID
         && !CFG.game.getProvince(nProvinceID).isOccupied()
         && CFG.game.getCiv(nCivID).getNumOfProvinces() > 1) {
         for (int i = 0; i < CFG.game.getCiv(nCivID).getMoveUnitsSize(); i++) {
            if (CFG.game.getCiv(nCivID).getMoveUnits(i).getFromProvinceID() == nProvinceID) {
               CFG.game.getCiv(nCivID).removeMove(i--);
            }
         }

         for (int var6 = 0; var6 < CFG.game.getCiv(nCivID).getMoveUnitsPlunderSize(); var6++) {
            if (CFG.game.getCiv(nCivID).getMoveUnits_Plunder(var6).getFromProvinceID() == nProvinceID) {
               CFG.game.getCiv(nCivID).removePlunder(var6--);
            }
         }

         for (int var7 = 0; var7 < CFG.game.getCiv(nCivID).getMoveUnitsGenocideSize(); var7++) {
            if (CFG.game.getCiv(nCivID).getMoveUnits_Genocide(var7).getFromProvinceID() == nProvinceID) {
               CFG.game.getCiv(nCivID).removeGenocide(var7--);
            }
         }

         for (int var8 = 0; var8 < CFG.game.getCiv(nCivID).getMigrateSize(); var8++) {
            if (CFG.game.getCiv(nCivID).getMigrate(var8).getFromProvinceID() == nProvinceID) {
               CFG.game.getCiv(nCivID).removeMigrate(var8--);
            }
         }

         for (int var9 = 0; var9 < CFG.game.getCiv(nCivID).getRecruitArmySize(); var9++) {
            if (CFG.game.getCiv(nCivID).getRecruitArmy(var9).getProvinceID() == nProvinceID) {
               CFG.game.getCiv(nCivID).removeRecruitArmy(var9--);
            }
         }

         for (int var10 = CFG.game.getProvince(nProvinceID).getPopulationData().getNationalitiesSize() - 1; var10 >= 0; var10--) {
            CFG.game
               .getProvince(nProvinceID)
               .getPopulationData()
               .setPopulationOfCivID(
                  CFG.game.getProvince(nProvinceID).getPopulationData().getCivID(var10),
                  (int)(
                     CFG.game
                           .getProvince(nProvinceID)
                           .getPopulationData()
                           .getPopulationOfCivID(CFG.game.getProvince(nProvinceID).getPopulationData().getCivID(var10))
                        * (0.05F + CFG.oR.nextInt(20) / 100.0F)
                  )
               );
         }

         CFG.game.getProvince(nProvinceID).setEconomy((int)(CFG.game.getProvince(nProvinceID).getEconomy() * (0.025F + CFG.oR.nextInt(15) / 100.0F)));
         CFG.game
            .getProvince(nProvinceID)
            .setDevelopmentLevel((int)(CFG.game.getProvince(nProvinceID).getDevelopmentLevel() * (0.045F + CFG.oR.nextInt(20) / 100.0F)));
         CFG.game.getProvince(nProvinceID).setTrueOwnerOfProvince(0);
         CFG.game.getProvince(nProvinceID).setCivID(0, false);

         try {
            CFG.game
               .getProvince(nProvinceID)
               .resetArmies(
                  CFG.oR.nextInt(CFG.game.getGameScenarios().getScenario_NeutralArmy() / 2) + CFG.game.getGameScenarios().getScenario_NeutralArmy() / 2
               );
            CFG.game.getProvince(nProvinceID).updateDrawArmy();
         } catch (IllegalArgumentException var5) {
         }

         return true;
      } else {
         return false;
      }
   }

   public final void accessLost_UpdateArmies(int inCivID, int nCivID) {
      ArrayList<Integer> tempProvincesToMove = new ArrayList<>();

      for (int i = 0; i < CFG.game.getCiv(nCivID).getArmyInAnotherProvinceSize(); i++) {
         if (CFG.game.getProvince(CFG.game.getCiv(nCivID).getArmyInAnotherProvince(i)).getCivID() == inCivID) {
            tempProvincesToMove.add(CFG.game.getCiv(nCivID).getArmyInAnotherProvince(i));
         }
      }

      for (int var5 = CFG.game.getCiv(nCivID).getMoveUnitsSize() - 1; var5 >= 0; var5--) {
         if (CFG.game.getProvince(CFG.game.getCiv(nCivID).getMoveUnits(var5).getFromProvinceID()).getCivID() == inCivID) {
            tempProvincesToMove.add(CFG.game.getCiv(nCivID).getMoveUnits(var5).getFromProvinceID());
            this.moveArmy(
               CFG.game.getCiv(nCivID).getMoveUnits(var5).getFromProvinceID(),
               CFG.game.getCiv(nCivID).getMoveUnits(var5).getToProvinceID(),
               0,
               nCivID,
               false,
               false
            );
         }
      }

      for (int var6 = 0; var6 < tempProvincesToMove.size(); var6++) {
         this.accessLost_MoveArmyToClosetsProvince(nCivID, tempProvincesToMove.get(var6));
      }
   }

   public final void accessLost_MoveArmyToClosetsProvince(int nCivID, int nProvinceID) {
      this.accessLost_MoveArmyToClosetsProvince(nCivID, nProvinceID, CFG.game.getProvince(nProvinceID).getArmyCivID(nCivID));
   }

   public final void accessLost_MoveArmyToClosetsProvince(int nCivID, int nProvinceID, int nArmy) {
      if (nArmy > 0) {
         if (CFG.game.getCiv(nCivID).getNumOfProvinces() > 0) {
            try {
               int toProvinceID = CFG.game.getCiv(nCivID).getProvinceID(0);
               float fMinDistance = CFG.game_NextTurnUpdate.getDistanceFromAToB_PercOfMax(nProvinceID, toProvinceID);
               float tempDistance = 0.0F;

               for (int i = 1; i < CFG.game.getCiv(nCivID).getNumOfProvinces(); i++) {
                  tempDistance = CFG.game_NextTurnUpdate.getDistanceFromAToB_PercOfMax(nProvinceID, CFG.game.getCiv(nCivID).getProvinceID(i));
                  if (fMinDistance > tempDistance) {
                     toProvinceID = CFG.game.getCiv(nCivID).getProvinceID(i);
                     fMinDistance = tempDistance;
                  }
               }

               CFG.game.getProvince(nProvinceID).updateArmy(nCivID, 0);
               CFG.game.getProvince(toProvinceID).updateArmy(nCivID, CFG.game.getProvince(toProvinceID).getArmyCivID(nCivID) + nArmy);
            } catch (IndexOutOfBoundsException var8) {
               if (CFG.LOGS) {
                  CFG.exceptionStack(var8);
               }

               CFG.game.getCiv(nCivID).setNumOfUnits(CFG.game.getCiv(nCivID).getNumOfUnits() - nArmy);
               CFG.game.getProvince(nProvinceID).updateArmy(nCivID, 0);
            }
         } else {
            CFG.game.getCiv(nCivID).setNumOfUnits(CFG.game.getCiv(nCivID).getNumOfUnits() - nArmy);
            CFG.game.getProvince(nProvinceID).updateArmy(nCivID, 0);
         }
      }
   }

   public final Game_Action.TurnStates getActiveTurnState() {
      return this.activeTurnAction;
   }

   public final void setActiveTurnState(Game_Action.TurnStates nState) {
      this.activeTurnAction = nState;
   }

   public final MoveUnits_TurnData getCurrentMoveunits() {
      return this.currentMoveUnits;
   }

   public final void resetCurrentMoveUnits() {
      this.currentMoveUnits = null;
   }

   static enum TurnStates {
      INPUT_ORDERS,
      LOAD_AI_RTO,
      TURN_ACTIONS,
      LOADING_NEXT_TURN,
      START_NEXT_TURN,
      SAVE_THE_GAME,
      RESULTS_STANDINGS,
      END_OF_THE_GAME;
   }
}
