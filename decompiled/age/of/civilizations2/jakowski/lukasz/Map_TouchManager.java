package age.of.civilizations2.jakowski.lukasz;

public class Map_TouchManager {
   public boolean actionMap = false;
   public int iStartMovePosX;
   public int iStartMovePosY;
   public int actionDownPosX;
   public int actionDownPosY;
   public boolean updateStartMovePosX;
   public boolean updateStartMovePosY;
   public long lActionDownTime = 0L;
   public boolean enableScaling = false;
   public boolean actionBrushMove = false;
   public boolean actionBrush = false;
   public Map_TouchManager.ExtraAction map_ActionDown_ExtraAction;
   public Map_TouchManager.ExtraAction map_ActionMove_ExtraAction;
   public Map_TouchManager.ExtraAction map_ActionUp_SetActiveProvinceID_ExtraAction;
   public Map_TouchManager.ExtraAction map_ActionUp_ExtraAction;
   public Map_TouchManager.ReverseDirection reverseDirectionX;
   public Map_TouchManager.ReverseDirection reverseDirectionY;
   public Map_TouchManager.ReverseDirection2 reverseDirectionX2;
   public Map_TouchManager.ReverseDirection2 reverseDirectionY2;

   public Map_TouchManager() {
      this.buildReversePosX();
      this.buildReversePosY();
      this.buildReversePosX2();
      this.buildReversePosY2();
      this.update_ExtraAction();
   }

   public final void updateEnableScaling() {
      this.enableScaling = !CFG.menuManager.getInMainMenu()
         && !CFG.menuManager.getInAboutMenu()
         && !CFG.menuManager.getInInitMenu()
         && !CFG.menuManager.getInLoadMap();
   }

   public final void actionDown(int nPosX, int nPosY) {
      this.actionMap = true;
      this.actionBrush = false;
      this.actionBrushMove = false;
      if (CFG.map.getMapScroll().getScrollingTheMap()) {
         CFG.map.getMapScroll().stopScrollingTheMap();
      }

      this.iStartMovePosX = this.reverseDirectionX.getStartMovePos((int)(nPosX / CFG.map.getMapScale().getCurrentScale()));
      this.iStartMovePosY = this.reverseDirectionY.getStartMovePos((int)(nPosY / CFG.map.getMapScale().getCurrentScale()));
      this.actionDownPosX = nPosX;
      this.actionDownPosY = nPosY;
      this.map_ActionDown_ExtraAction.extraAction(nPosX, nPosY);
   }

   public final void actionMove(int nPosX, int nPosY) {
      if (CFG.brushTool) {
         this.actionDownPosX = nPosX;
         this.actionDownPosY = nPosY;
         this.actionUp_setActiveProvinceID(nPosX, nPosY);
      } else {
         this.actionMoveMap(nPosX, nPosY);
         CFG.map.getMapScroll().setScrollPos((int)(nPosX / CFG.map.getMapScale().getCurrentScale()), (int)(nPosY / CFG.map.getMapScale().getCurrentScale()));
      }
   }

   public final void actionMoveMap(int nPosX, int nPosY) {
      if (!CFG.map.getMapCoordinates().getDisableMovingMap()) {
         if (this.updateStartMovePosX) {
            this.iStartMovePosX = this.reverseDirectionX.getStartMovePos((int)(nPosX / CFG.map.getMapScale().getCurrentScale()));
            this.iStartMovePosY = this.reverseDirectionY.getStartMovePos((int)(nPosY / CFG.map.getMapScale().getCurrentScale()));
            this.updateStartMovePosX = false;
         }

         if (this.updateStartMovePosY) {
            this.iStartMovePosX = this.reverseDirectionX.getStartMovePos((int)(nPosX / CFG.map.getMapScale().getCurrentScale()));
            this.iStartMovePosY = this.reverseDirectionY.getStartMovePos((int)(nPosY / CFG.map.getMapScale().getCurrentScale()));
            this.updateStartMovePosY = false;
         }

         CFG.map.getMapCoordinates().setNewPosX(this.reverseDirectionX2.getNewPos(this.iStartMovePosX, (int)(nPosX / CFG.map.getMapScale().getCurrentScale())));
         CFG.map.getMapCoordinates().setNewPosY(this.reverseDirectionY2.getNewPos(this.iStartMovePosY, (int)(nPosY / CFG.map.getMapScale().getCurrentScale())));
      } else {
         this.map_ActionMove_ExtraAction.extraAction(nPosX, nPosY);
      }
   }

   public final void actionMove(int nPosX, int nPosY, int nPosX2, int nPosY2) {
      if (!CFG.map.getMapCoordinates().getDisableMovingMap() && this.enableScaling) {
         if (CFG.map.getMapScale().getStartScalePosY() <= 0) {
            CFG.map.getMapScale().startScaleTheMap(nPosX, nPosX2, nPosY, nPosY2);
         } else {
            CFG.map.getMapScale().scaleTheMap(nPosX, nPosX2, nPosY, nPosY2);
         }
      }
   }

   public final void actionUp(int nPosX, int nPosY) {
      this.actionUp_setActiveProvinceID(nPosX, nPosY);
      if (CFG.menuManager.getActiveMenuElementID() < 0 && this.enableScaling) {
         CFG.map.getMapScale().resetScaleOfMap(System.currentTimeMillis());
      }

      if (!CFG.map.getMapScale().getScaleMode() && !CFG.map.getMapCoordinates().getDisableMovingMap()) {
         CFG.map.getMapScroll().startScrollingTheMap();
      }

      this.map_ActionUp_ExtraAction.extraAction(nPosX, nPosY);
   }

   public final void actionUp_setActiveProvinceID(int nPosX, int nPosY) {
      if (!CFG.map.getMapScale().getScaleMode()
         && this.actionDownPosX + CFG.PADDING * CFG.DENSITY > nPosX
         && this.actionDownPosX - CFG.PADDING * CFG.DENSITY < nPosX
         && this.actionDownPosY + CFG.PADDING * CFG.DENSITY > nPosY
         && this.actionDownPosY - CFG.PADDING * CFG.DENSITY < nPosY) {
         CFG.game.setProvinceID((int)(nPosX / CFG.map.getMapScale().getCurrentScale()), (int)(nPosY / CFG.map.getMapScale().getCurrentScale()));
         if (!CFG.brushTool) {
            CFG.soundsManager.playSound(SoundsManager.SOUND_PROVINCE, SoundsManager.PERC_VOLUME_SELECT_PROVINCE);
         }

         this.map_ActionUp_SetActiveProvinceID_ExtraAction.extraAction(nPosX, nPosY);
      }
   }

   public final void buildReversePosX() {
      this.reverseDirectionX = CFG.reverseDirectionX ? new Map_TouchManager.ReverseDirection() {
         @Override
         public int getStartMovePos(int nPos) {
            return CFG.map.getMapCoordinates().getPosX() - nPos;
         }
      } : new Map_TouchManager.ReverseDirection() {
         @Override
         public int getStartMovePos(int nPos) {
            return CFG.map.getMapCoordinates().getPosX() + nPos;
         }
      };
   }

   public final void buildReversePosY() {
      this.reverseDirectionY = CFG.reverseDirectionY ? new Map_TouchManager.ReverseDirection() {
         @Override
         public int getStartMovePos(int nPos) {
            return CFG.map.getMapCoordinates().getPosY() - nPos;
         }
      } : new Map_TouchManager.ReverseDirection() {
         @Override
         public int getStartMovePos(int nPos) {
            return CFG.map.getMapCoordinates().getPosY() + nPos;
         }
      };
   }

   public final void buildReversePosX2() {
      this.reverseDirectionX2 = CFG.reverseDirectionX ? new Map_TouchManager.ReverseDirection2() {
         @Override
         public int getNewPos(int iStartMovePos, int nPos) {
            return iStartMovePos + nPos;
         }
      } : new Map_TouchManager.ReverseDirection2() {
         @Override
         public int getNewPos(int iStartMovePos, int nPos) {
            return iStartMovePos - nPos;
         }
      };
   }

   public final void buildReversePosY2() {
      this.reverseDirectionY2 = CFG.reverseDirectionY ? new Map_TouchManager.ReverseDirection2() {
         @Override
         public int getNewPos(int iStartMovePos, int nPos) {
            return iStartMovePos + nPos;
         }
      } : new Map_TouchManager.ReverseDirection2() {
         @Override
         public int getNewPos(int iStartMovePos, int nPos) {
            return iStartMovePos - nPos;
         }
      };
   }

   public final void update_ExtraAction() {
      this.map_ActionUp_SetActiveProvinceID_ExtraAction = null;
      this.map_ActionUp_SetActiveProvinceID_ExtraAction = CFG.menuManager.getInSelectCiv()
         ? new Map_TouchManager.ExtraAction() {
            @Override
            public void extraAction(int nPosX, int nPosY) {
               if (CFG.game.getActiveProvinceID() >= 0 && CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID() > 0) {
                  for (int i = 0; i < CFG.game.getPlayersSize(); i++) {
                     if (CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID() == CFG.game.getPlayer(i).getCivID()) {
                        return;
                     }
                  }

                  CFG.setDialogType(Dialog.SELECT_CIVILIZATION);
               }
            }
         }
         : (
            CFG.menuManager.getInGameView()
               ? (
                  CFG.gameAction.getActiveTurnState() != Game_Action.TurnStates.LOAD_AI_RTO
                        && CFG.gameAction.getActiveTurnState() != Game_Action.TurnStates.LOADING_NEXT_TURN
                     ? new Map_TouchManager.ExtraAction() {
                        @Override
                        public void extraAction(int nPosX, int nPosY) {
                           if (CFG.SPECTATOR_MODE || CFG.FREEPLAY_MODE && CFG.viewsManager.getActiveViewID() != ViewsManager.VIEW_DIPLOMACY_MODE) {
                              if (CFG.game.getActiveProvinceID() >= 0
                                 && CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID() != CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()
                                 && CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID() > 0) {
                                 CFG.game.getPlayer(CFG.PLAYER_TURNID).setCivID(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID());
                                 CFG.game.getPlayer(CFG.PLAYER_TURNID).loadPlayersFlag();
                                 CFG.setActiveCivInfo(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID());
                                 CFG.updateActiveCivInfo_InGame();
                                 CFG.menuManager.updateInGame_TOP_All(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
                                 CFG.menuManager.setVisible_Menu_InGame_CurrentWars(true);
                                 CFG.menuManager.rebuildInGame_Messages();
                                 if (CFG.menuManager.getVisible_InGame_Budget()) {
                                    CFG.menuManager.setVisible_InGame_Budget(true);
                                 }

                                 if (CFG.menuManager.getVisible_InGame_FlagAction() && !CFG.menuManager.getVisible_InGame_FlagAction_Console()) {
                                    CFG.menuManager.setVisible_InGame_FlagAction(true);
                                 }

                                 if (CFG.menuManager.getVisibleInGame_VictoryConditions()) {
                                    CFG.menuManager.rebuildInGame_VictoryConditions();
                                 }

                                 if (CFG.viewsManager.getActiveViewID() == ViewsManager.VIEW_DIPLOMACY_MODE) {
                                    CFG.game.disableDrawCivilizationRegions_Active();
                                    CFG.game.enableDrawCivilizationRegions_ActiveProvince();
                                 } else if (CFG.viewsManager.getActiveViewID() == ViewsManager.VIEW_INCOME_MODE
                                    && CFG.menuManager.getVisible_InGame_View_Stats()) {
                                    CFG.menuManager.setVisible_InGame_ViewIncome(true);
                                 }
                              }
                           } else if (CFG.game.getActiveProvinceID() >= 0) {
                              CFG.game.autoBuildChooseProvinceMode(false);
                              int nCivID = CFG.getActiveCivInfo_BasedOnActiveProvinceID(CFG.game.getActiveProvinceID());
                              if (nCivID > 0 && CFG.getActiveCivInfo() != nCivID) {
                                 if (CFG.viewsManager.getActiveViewID() >= 0) {
                                    CFG.viewsManager.getActiveView().updateActiveCivInfo_ExtraAction(nCivID);
                                 }

                                 if (CFG.menuManager.getInGame_CivInfo().getVisible()) {
                                    CFG.setActiveCivInfo(nCivID);
                                    CFG.updateActiveCivInfo_InGame();
                                    if (CFG.viewsManager.getActiveViewID() >= 0) {
                                       CFG.viewsManager.getActiveView().setActiveProvinceAction();
                                    }
                                 }
                              }

                              if (RTS.isEnabled() && !RTS.PAUSE) {
                                 RTS.updateTimePast_AfterAction(0.5F);
                              }
                           }
                        }
                     }
                     : new Map_TouchManager.ExtraAction() {
                        @Override
                        public void extraAction(int nPosX, int nPosY) {
                           CFG.game.setActiveProvinceID(-1);
                        }
                     }
               )
               : (
                  !CFG.menuManager.getInGame_Timeline() && !CFG.menuManager.getInVictory()
                     ? (
                        !CFG.menuManager.getInGame_Formable_Civ_Provinces() && !CFG.menuManager.getInGame_FormAnimation()
                           ? (
                              CFG.menuManager.getInCreateNewGame()
                                 ? new Map_TouchManager.ExtraAction() {
                                    @Override
                                    public void extraAction(int nPosX, int nPosY) {
                                       try {
                                          if (CFG.game.getActiveProvinceID() >= 0
                                             && CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID() > 0
                                             && CFG.getActiveCivInfo() != CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()) {
                                             if (!CFG.game.getCiv(CFG.getActiveCivInfo()).getControlledByPlayer()) {
                                                CFG.game.disableDrawCivilizationRegions(CFG.getActiveCivInfo());
                                             }

                                             CFG.setActiveCivInfo(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID());
                                             CFG.updateActiveCivInfo_CreateNewGame();
                                             CFG.game.enableDrawCivilizationRegions(CFG.getActiveCivInfo(), 1);
                                          }
                                       } catch (IndexOutOfBoundsException var4) {
                                          if (CFG.LOGS) {
                                             CFG.exceptionStack(var4);
                                          }
                                       } catch (NullPointerException var5) {
                                          if (CFG.LOGS) {
                                             CFG.exceptionStack(var5);
                                          }
                                       }
                                    }
                                 }
                                 : (
                                    CFG.menuManager.getInSelectAvailableCivilizations()
                                       ? new Map_TouchManager.ExtraAction() {
                                          @Override
                                          public void extraAction(int nPosX, int nPosY) {
                                             if (CFG.game.getActiveProvinceID() >= 0
                                                && !CFG.game.getProvince(CFG.game.getActiveProvinceID()).getSeaProvince()
                                                && CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID() > 0) {
                                                CFG.menuManager.getSelectAvailableCivilizations().getMenuElement(3).setVisible(true);
                                                CFG.menuManager.getSelectAvailableCivilizations().getMenuElement(3).setClickable(true);
                                                CFG.menuManager
                                                   .getSelectAvailableCivilizations()
                                                   .getMenuElement(3)
                                                   .setCheckboxState(
                                                      CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).getIsAvailable()
                                                   );
                                                if (CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).getIsAvailable()) {
                                                   CFG.menuManager
                                                      .getSelectAvailableCivilizations()
                                                      .getMenuElement(3)
                                                      .setText(
                                                         CFG.langManager.get("Disable")
                                                            + " - "
                                                            + CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).getCivName()
                                                      );
                                                   if (CFG.game.getAvailableCivilizations() < 3) {
                                                      CFG.menuManager.getSelectAvailableCivilizations().getMenuElement(3).setClickable(false);
                                                   }
                                                } else {
                                                   CFG.menuManager
                                                      .getSelectAvailableCivilizations()
                                                      .getMenuElement(3)
                                                      .setText(
                                                         CFG.langManager.get("Enable")
                                                            + " - "
                                                            + CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).getCivName()
                                                      );
                                                }

                                                CFG.menuManager.getSelectAvailableCivilizations().updateButtonWidth(3, CFG.PADDING, CFG.BUTTON_WIDTH * 2);
                                             } else {
                                                CFG.menuManager.getSelectAvailableCivilizations().getMenuElement(3).setVisible(false);
                                                CFG.menuManager.getSelectAvailableCivilizations().getMenuElement(3).setClickable(false);
                                             }
                                          }
                                       }
                                       : (
                                          CFG.menuManager.getInGame_PeaceTreaty()
                                             ? new Map_TouchManager.ExtraAction() {
                                                @Override
                                                public void extraAction(int nPosX, int nPosY) {
                                                   if (Menu_PeaceTreaty_Response.DRAW_TREATY_PROVINCES) {
                                                      CFG.peaceTreatyData
                                                         .takeProvince(
                                                            CFG.game.getActiveProvinceID(),
                                                            CFG.peaceTreatyData.iBrushCivID,
                                                            CFG.game.getCiv(CFG.peaceTreatyData.iBrushCivID).getControlledByPlayer()
                                                               ? CFG.peaceTreatyData.iBrushCivID
                                                               : CFG.game.getPlayer(CFG.peaceTreatyData.iPlayerTurnID).getCivID()
                                                         );
                                                   }
                                                }
                                             }
                                             : (
                                                CFG.menuManager.getInGame_PeaceTreaty_Response()
                                                   ? new Map_TouchManager.ExtraAction() {
                                                      @Override
                                                      public void extraAction(int nPosX, int nPosY) {
                                                         CFG.game.setActiveProvinceID(-1);
                                                      }
                                                   }
                                                   : (
                                                      CFG.menuManager.getInCreateScenario_Civilizations()
                                                         ? new Map_TouchManager.ExtraAction() {
                                                            @Override
                                                            public void extraAction(int nPosX, int nPosY) {
                                                               CFG.updateCreateScenario_Civilizations();
                                                            }
                                                         }
                                                         : (
                                                            CFG.menuManager.getInCreateScenario_Civilizations_Select()
                                                               ? new Map_TouchManager.ExtraAction() {
                                                                  @Override
                                                                  public void extraAction(int nPosX, int nPosY) {
                                                                     CFG.game.setActiveProvinceID(CFG.iCreateScenario_ActiveProvinceID);
                                                                  }
                                                               }
                                                               : (
                                                                  CFG.menuManager.getInCreateScenario_TechnologyLevels()
                                                                     ? new Map_TouchManager.ExtraAction() {
                                                                        @Override
                                                                        public void extraAction(int nPosX, int nPosY) {
                                                                           if (CFG.game.getActiveProvinceID() >= 0) {
                                                                              if (CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID() != 0) {
                                                                                 if (CFG.iCreateScenario_AssignProvinces_Civ > 0) {
                                                                                    CFG.game
                                                                                       .disableDrawCivilizationRegions(CFG.iCreateScenario_AssignProvinces_Civ);
                                                                                 }

                                                                                 CFG.iCreateScenario_AssignProvinces_Civ = CFG.game
                                                                                    .getProvince(CFG.game.getActiveProvinceID())
                                                                                    .getCivID();
                                                                                 CFG.menuManager
                                                                                    .set_CreateScenario_TechnologyLevels_Slider(
                                                                                       (int)(
                                                                                          CFG.game
                                                                                                .getCiv(CFG.iCreateScenario_AssignProvinces_Civ)
                                                                                                .getTechnologyLevel()
                                                                                             * 100.0F
                                                                                       )
                                                                                    );
                                                                                 if (CFG.iCreateScenario_AssignProvinces_Civ > 0) {
                                                                                    CFG.game
                                                                                       .enableDrawCivilizationRegions(
                                                                                          CFG.iCreateScenario_AssignProvinces_Civ, 0
                                                                                       );
                                                                                 }
                                                                              } else {
                                                                                 CFG.menuManager.set_CreateScenario_TechnologyLevels_SliderCivs();
                                                                              }
                                                                           }
                                                                        }
                                                                     }
                                                                     : (
                                                                        CFG.menuManager.getInCreateScenario_Happiness()
                                                                           ? new Map_TouchManager.ExtraAction() {
                                                                              @Override
                                                                              public void extraAction(int nPosX, int nPosY) {
                                                                                 if (CFG.game.getActiveProvinceID() >= 0
                                                                                    && CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID() != 0) {
                                                                                    if (CFG.iCreateScenario_AssignProvinces_Civ > 0) {
                                                                                       CFG.game
                                                                                          .disableDrawCivilizationRegions(
                                                                                             CFG.iCreateScenario_AssignProvinces_Civ
                                                                                          );
                                                                                    }

                                                                                    CFG.iCreateScenario_AssignProvinces_Civ = CFG.game
                                                                                       .getProvince(CFG.game.getActiveProvinceID())
                                                                                       .getCivID();
                                                                                    CFG.menuManager
                                                                                       .set_CreateScenario_Happiness_Slider(
                                                                                          CFG.game
                                                                                             .getCiv(CFG.iCreateScenario_AssignProvinces_Civ)
                                                                                             .getHappiness()
                                                                                       );
                                                                                    if (CFG.iCreateScenario_AssignProvinces_Civ > 0) {
                                                                                       CFG.game
                                                                                          .enableDrawCivilizationRegions(
                                                                                             CFG.iCreateScenario_AssignProvinces_Civ, 0
                                                                                          );
                                                                                    }
                                                                                 }
                                                                              }
                                                                           }
                                                                           : (
                                                                              CFG.menuManager.getInCreateScenario_StartingMoney()
                                                                                 ? new Map_TouchManager.ExtraAction() {
                                                                                    @Override
                                                                                    public void extraAction(int nPosX, int nPosY) {
                                                                                       if (CFG.game.getActiveProvinceID() >= 0
                                                                                          && CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()
                                                                                             != 0) {
                                                                                          if (CFG.iCreateScenario_AssignProvinces_Civ > 0) {
                                                                                             CFG.game
                                                                                                .disableDrawCivilizationRegions(
                                                                                                   CFG.iCreateScenario_AssignProvinces_Civ
                                                                                                );
                                                                                          }

                                                                                          CFG.menuManager
                                                                                             .set_CreateScenario_StartingMoney_Slider(
                                                                                                (int)(
                                                                                                   CFG.game
                                                                                                            .getCiv(
                                                                                                               CFG.iCreateScenario_AssignProvinces_Civ = CFG.game
                                                                                                                  .getProvince(CFG.game.getActiveProvinceID())
                                                                                                                  .getCivID()
                                                                                                            )
                                                                                                            .getMoney()
                                                                                                         == -999999L
                                                                                                      ? CFG.game.getGameScenarios().getScenario_StartingMoney()
                                                                                                      : CFG.game
                                                                                                         .getCiv(CFG.iCreateScenario_AssignProvinces_Civ)
                                                                                                         .getMoney()
                                                                                                )
                                                                                             );
                                                                                          if (CFG.iCreateScenario_AssignProvinces_Civ > 0) {
                                                                                             CFG.game
                                                                                                .enableDrawCivilizationRegions(
                                                                                                   CFG.iCreateScenario_AssignProvinces_Civ, 0
                                                                                                );
                                                                                          }
                                                                                       }
                                                                                    }
                                                                                 }
                                                                                 : (
                                                                                    CFG.menuManager.getInCreateScenario_Available_Provinces()
                                                                                       ? new Map_TouchManager.ExtraAction() {
                                                                                          @Override
                                                                                          public void extraAction(int nPosX, int nPosY) {
                                                                                             if (CFG.game.getActiveProvinceID() >= 0) {
                                                                                                if (CFG.game
                                                                                                   .getProvince(CFG.game.getActiveProvinceID())
                                                                                                   .getSeaProvince()) {
                                                                                                   if (!Map_TouchManager.this.actionBrush) {
                                                                                                      Map_TouchManager.this.actionMoveMap(nPosX, nPosY);
                                                                                                      Map_TouchManager.this.actionBrushMove = true;
                                                                                                   }
                                                                                                } else if (!Map_TouchManager.this.actionBrushMove) {
                                                                                                   if (!CFG.bSetWasteland_AvailableProvinces
                                                                                                      || CFG.game
                                                                                                            .getProvince(CFG.game.getActiveProvinceID())
                                                                                                            .getWasteland()
                                                                                                         < 0) {
                                                                                                      CFG.game
                                                                                                         .setWasteland(
                                                                                                            CFG.game.getActiveProvinceID(),
                                                                                                            CFG.bSetWasteland_AvailableProvinces
                                                                                                         );
                                                                                                   }

                                                                                                   CFG.updateNumOfAvailableProvinces();
                                                                                                   Map_TouchManager.this.actionBrush = true;
                                                                                                } else {
                                                                                                   Map_TouchManager.this.actionMoveMap(nPosX, nPosY);
                                                                                                }
                                                                                             }
                                                                                          }
                                                                                       }
                                                                                       : (
                                                                                          CFG.menuManager.getInMapEditor_WastelandMaps_Edit()
                                                                                             ? new Map_TouchManager.ExtraAction() {
                                                                                                @Override
                                                                                                public void extraAction(int nPosX, int nPosY) {
                                                                                                   if (CFG.game.getActiveProvinceID() >= 0) {
                                                                                                      if (CFG.game
                                                                                                         .getProvince(CFG.game.getActiveProvinceID())
                                                                                                         .getSeaProvince()) {
                                                                                                         if (!Map_TouchManager.this.actionBrush) {
                                                                                                            Map_TouchManager.this.actionMoveMap(nPosX, nPosY);
                                                                                                            Map_TouchManager.this.actionBrushMove = true;
                                                                                                         }
                                                                                                      } else if (!Map_TouchManager.this.actionBrushMove) {
                                                                                                         if (CFG.bSetWasteland_AvailableProvinces) {
                                                                                                            if (CFG.game
                                                                                                                  .getProvince(CFG.game.getActiveProvinceID())
                                                                                                                  .getWasteland()
                                                                                                               < 0) {
                                                                                                               CFG.game
                                                                                                                  .setWasteland(
                                                                                                                     CFG.game.getActiveProvinceID(),
                                                                                                                     CFG.bSetWasteland_AvailableProvinces
                                                                                                                  );
                                                                                                            }
                                                                                                         } else {
                                                                                                            CFG.game
                                                                                                               .setWasteland(
                                                                                                                  CFG.game.getActiveProvinceID(),
                                                                                                                  CFG.bSetWasteland_AvailableProvinces
                                                                                                               );
                                                                                                         }

                                                                                                         CFG.updateNumOfAvailableProvinces();
                                                                                                         Map_TouchManager.this.actionBrush = true;
                                                                                                      } else {
                                                                                                         Map_TouchManager.this.actionMoveMap(nPosX, nPosY);
                                                                                                      }
                                                                                                   }
                                                                                                }
                                                                                             }
                                                                                             : (
                                                                                                CFG.menuManager.getInMapEditor_ArmySeaBoxes_Add()
                                                                                                   ? new Map_TouchManager.ExtraAction() {
                                                                                                      @Override
                                                                                                      public void extraAction(int nPosX, int nPosY) {
                                                                                                         if (CFG.game.getActiveProvinceID() >= 0) {
                                                                                                            if (Menu_MapEditor_ArmySeaBoxes_Add.oFirstPoint
                                                                                                                  .getPosY()
                                                                                                               < 0) {
                                                                                                               Menu_MapEditor_ArmySeaBoxes_Add.oFirstPoint
                                                                                                                  .setPosX(
                                                                                                                     -CFG.map.getMapCoordinates().getPosX()
                                                                                                                        + (int)(
                                                                                                                           nPosX
                                                                                                                              / CFG.map
                                                                                                                                 .getMapScale()
                                                                                                                                 .getCurrentScale()
                                                                                                                        )
                                                                                                                  );
                                                                                                               Menu_MapEditor_ArmySeaBoxes_Add.oFirstPoint
                                                                                                                  .setPosY(
                                                                                                                     -CFG.map.getMapCoordinates().getPosY()
                                                                                                                        + (int)(
                                                                                                                           nPosY
                                                                                                                              / CFG.map
                                                                                                                                 .getMapScale()
                                                                                                                                 .getCurrentScale()
                                                                                                                        )
                                                                                                                  );
                                                                                                            } else if (Menu_MapEditor_ArmySeaBoxes_Add.oSecondPoint
                                                                                                                  .getPosY()
                                                                                                               < 0) {
                                                                                                               Menu_MapEditor_ArmySeaBoxes_Add.oSecondPoint
                                                                                                                  .setPosX(
                                                                                                                     -CFG.map.getMapCoordinates().getPosX()
                                                                                                                        + (int)(
                                                                                                                           nPosX
                                                                                                                              / CFG.map
                                                                                                                                 .getMapScale()
                                                                                                                                 .getCurrentScale()
                                                                                                                        )
                                                                                                                  );
                                                                                                               Menu_MapEditor_ArmySeaBoxes_Add.oSecondPoint
                                                                                                                  .setPosY(
                                                                                                                     -CFG.map.getMapCoordinates().getPosY()
                                                                                                                        + (int)(
                                                                                                                           nPosY
                                                                                                                              / CFG.map
                                                                                                                                 .getMapScale()
                                                                                                                                 .getCurrentScale()
                                                                                                                        )
                                                                                                                  );
                                                                                                            } else {
                                                                                                               int tempPosX = -CFG.map
                                                                                                                     .getMapCoordinates()
                                                                                                                     .getPosX()
                                                                                                                  + (int)(
                                                                                                                     nPosX
                                                                                                                        / CFG.map
                                                                                                                           .getMapScale()
                                                                                                                           .getCurrentScale()
                                                                                                                  );
                                                                                                               int tempPosY = -CFG.map
                                                                                                                     .getMapCoordinates()
                                                                                                                     .getPosY()
                                                                                                                  + (int)(
                                                                                                                     nPosY
                                                                                                                        / CFG.map
                                                                                                                           .getMapScale()
                                                                                                                           .getCurrentScale()
                                                                                                                  );
                                                                                                               int tempWidthFirst = (int)Math.ceil(
                                                                                                                  Math.sqrt(
                                                                                                                     (
                                                                                                                              Menu_MapEditor_ArmySeaBoxes_Add.oFirstPoint
                                                                                                                                    .getPosX()
                                                                                                                                 - tempPosX
                                                                                                                           )
                                                                                                                           * (
                                                                                                                              Menu_MapEditor_ArmySeaBoxes_Add.oFirstPoint
                                                                                                                                    .getPosX()
                                                                                                                                 - tempPosX
                                                                                                                           )
                                                                                                                        + (
                                                                                                                              tempPosY
                                                                                                                                 - Menu_MapEditor_ArmySeaBoxes_Add.oFirstPoint
                                                                                                                                    .getPosY()
                                                                                                                           )
                                                                                                                           * (
                                                                                                                              tempPosY
                                                                                                                                 - Menu_MapEditor_ArmySeaBoxes_Add.oFirstPoint
                                                                                                                                    .getPosY()
                                                                                                                           )
                                                                                                                  )
                                                                                                               );
                                                                                                               if (tempWidthFirst
                                                                                                                  < (int)Math.ceil(
                                                                                                                     Math.sqrt(
                                                                                                                        (
                                                                                                                                 Menu_MapEditor_ArmySeaBoxes_Add.oSecondPoint
                                                                                                                                       .getPosX()
                                                                                                                                    - tempPosX
                                                                                                                              )
                                                                                                                              * (
                                                                                                                                 Menu_MapEditor_ArmySeaBoxes_Add.oSecondPoint
                                                                                                                                       .getPosX()
                                                                                                                                    - tempPosX
                                                                                                                              )
                                                                                                                           + (
                                                                                                                                 tempPosY
                                                                                                                                    - Menu_MapEditor_ArmySeaBoxes_Add.oSecondPoint
                                                                                                                                       .getPosY()
                                                                                                                              )
                                                                                                                              * (
                                                                                                                                 tempPosY
                                                                                                                                    - Menu_MapEditor_ArmySeaBoxes_Add.oSecondPoint
                                                                                                                                       .getPosY()
                                                                                                                              )
                                                                                                                     )
                                                                                                                  )) {
                                                                                                                  Menu_MapEditor_ArmySeaBoxes_Add.oFirstPoint
                                                                                                                     .setPosX(
                                                                                                                        -CFG.map.getMapCoordinates().getPosX()
                                                                                                                           + (int)(
                                                                                                                              nPosX
                                                                                                                                 / CFG.map
                                                                                                                                    .getMapScale()
                                                                                                                                    .getCurrentScale()
                                                                                                                           )
                                                                                                                     );
                                                                                                                  Menu_MapEditor_ArmySeaBoxes_Add.oFirstPoint
                                                                                                                     .setPosY(
                                                                                                                        -CFG.map.getMapCoordinates().getPosY()
                                                                                                                           + (int)(
                                                                                                                              nPosY
                                                                                                                                 / CFG.map
                                                                                                                                    .getMapScale()
                                                                                                                                    .getCurrentScale()
                                                                                                                           )
                                                                                                                     );
                                                                                                               } else {
                                                                                                                  Menu_MapEditor_ArmySeaBoxes_Add.oSecondPoint
                                                                                                                     .setPosX(
                                                                                                                        -CFG.map.getMapCoordinates().getPosX()
                                                                                                                           + (int)(
                                                                                                                              nPosX
                                                                                                                                 / CFG.map
                                                                                                                                    .getMapScale()
                                                                                                                                    .getCurrentScale()
                                                                                                                           )
                                                                                                                     );
                                                                                                                  Menu_MapEditor_ArmySeaBoxes_Add.oSecondPoint
                                                                                                                     .setPosY(
                                                                                                                        -CFG.map.getMapCoordinates().getPosY()
                                                                                                                           + (int)(
                                                                                                                              nPosY
                                                                                                                                 / CFG.map
                                                                                                                                    .getMapScale()
                                                                                                                                    .getCurrentScale()
                                                                                                                           )
                                                                                                                     );
                                                                                                               }
                                                                                                            }

                                                                                                            if (CFG.game.getActiveProvinceID()
                                                                                                               != CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1) {
                                                                                                               CFG.game
                                                                                                                  .setActiveProvinceID(
                                                                                                                     CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1
                                                                                                                  );
                                                                                                            }
                                                                                                         }
                                                                                                      }
                                                                                                   }
                                                                                                   : (
                                                                                                      CFG.menuManager.getInCreateScenario_Assign()
                                                                                                         ? new Map_TouchManager.ExtraAction() {
                                                                                                            @Override
                                                                                                            public void extraAction(int nPosX, int nPosY) {
                                                                                                               if (CFG.game.getActiveProvinceID() >= 0) {
                                                                                                                  if (CFG.game
                                                                                                                     .getProvince(
                                                                                                                        CFG.game.getActiveProvinceID()
                                                                                                                     )
                                                                                                                     .getSeaProvince()) {
                                                                                                                     if (CFG.brushTool
                                                                                                                        && !Map_TouchManager.this.actionBrush) {
                                                                                                                        Map_TouchManager.this.actionMoveMap(
                                                                                                                           nPosX, nPosY
                                                                                                                        );
                                                                                                                        Map_TouchManager.this.actionBrushMove = true;
                                                                                                                     }

                                                                                                                     return;
                                                                                                                  }

                                                                                                                  if (CFG.brushTool) {
                                                                                                                     if (Map_TouchManager.this.actionBrushMove) {
                                                                                                                        Map_TouchManager.this.actionMoveMap(
                                                                                                                           nPosX, nPosY
                                                                                                                        );
                                                                                                                        return;
                                                                                                                     }

                                                                                                                     Map_TouchManager.this.actionBrush = true;
                                                                                                                  }

                                                                                                                  if (CFG.iCreateScenario_AssignProvinces_Civ
                                                                                                                     >= 0) {
                                                                                                                     for (int i = 1;
                                                                                                                        i < CFG.game.getCivsSize();
                                                                                                                        i++
                                                                                                                     ) {
                                                                                                                        if (CFG.game
                                                                                                                              .getCiv(i)
                                                                                                                              .getCapitalProvinceID()
                                                                                                                           == CFG.game.getActiveProvinceID()) {
                                                                                                                           if (!CFG.brushTool
                                                                                                                              && CFG.game
                                                                                                                                    .getProvince(
                                                                                                                                       CFG.game
                                                                                                                                          .getActiveProvinceID()
                                                                                                                                    )
                                                                                                                                    .getCivID()
                                                                                                                                 != CFG.iCreateScenario_AssignProvinces_Civ
                                                                                                                              )
                                                                                                                            {
                                                                                                                              CFG.setDialogType(
                                                                                                                                 Dialog.CREATE_SCENARIO_ASSIGN_CIVILIZATION
                                                                                                                              );
                                                                                                                           }

                                                                                                                           return;
                                                                                                                        }
                                                                                                                     }

                                                                                                                     if (CFG.game
                                                                                                                              .getProvince(
                                                                                                                                 CFG.game.getActiveProvinceID()
                                                                                                                              )
                                                                                                                              .getCivID()
                                                                                                                           != CFG.iCreateScenario_AssignProvinces_Civ
                                                                                                                        && CFG.game
                                                                                                                              .getProvince(
                                                                                                                                 CFG.game.getActiveProvinceID()
                                                                                                                              )
                                                                                                                              .getWasteland()
                                                                                                                           < 0) {
                                                                                                                        CFG.addUndoAssignProvinces(
                                                                                                                           CFG.game.getActiveProvinceID(),
                                                                                                                           CFG.game
                                                                                                                              .getProvince(
                                                                                                                                 CFG.game.getActiveProvinceID()
                                                                                                                              )
                                                                                                                              .getCivID()
                                                                                                                        );
                                                                                                                        CFG.game
                                                                                                                           .getProvince(
                                                                                                                              CFG.game.getActiveProvinceID()
                                                                                                                           )
                                                                                                                           .setCivID(
                                                                                                                              CFG.iCreateScenario_AssignProvinces_Civ,
                                                                                                                              false,
                                                                                                                              false
                                                                                                                           );
                                                                                                                        CFG.game
                                                                                                                           .getProvince(
                                                                                                                              CFG.game.getActiveProvinceID()
                                                                                                                           )
                                                                                                                           .resetArmies(-1);
                                                                                                                        CFG.game
                                                                                                                           .getProvince(
                                                                                                                              CFG.game.getActiveProvinceID()
                                                                                                                           )
                                                                                                                           .buildProvinceCore();
                                                                                                                        CFG.game
                                                                                                                           .setActiveProvinceID(
                                                                                                                              CFG.game.getActiveProvinceID()
                                                                                                                           );
                                                                                                                     }
                                                                                                                  } else {
                                                                                                                     for (int ix = 1;
                                                                                                                        ix < CFG.game.getCivsSize();
                                                                                                                        ix++
                                                                                                                     ) {
                                                                                                                        if (CFG.game
                                                                                                                              .getCiv(ix)
                                                                                                                              .getCapitalProvinceID()
                                                                                                                           == CFG.game.getActiveProvinceID()) {
                                                                                                                           if (CFG.game
                                                                                                                                 .getProvince(
                                                                                                                                    CFG.game
                                                                                                                                       .getActiveProvinceID()
                                                                                                                                 )
                                                                                                                                 .getCivID()
                                                                                                                              != CFG.iCreateScenario_AssignProvinces_Civ
                                                                                                                              )
                                                                                                                            {
                                                                                                                              CFG.setDialogType(
                                                                                                                                 Dialog.CREATE_SCENARIO_ASSIGN_CIVILIZATION
                                                                                                                              );
                                                                                                                           }

                                                                                                                           return;
                                                                                                                        }
                                                                                                                     }
                                                                                                                  }
                                                                                                               }
                                                                                                            }
                                                                                                         }
                                                                                                         : (
                                                                                                            CFG.menuManager.getInCreateScenario_SetUpArmy()
                                                                                                               ? new Map_TouchManager.ExtraAction() {
                                                                                                                  @Override
                                                                                                                  public void extraAction(int nPosX, int nPosY) {
                                                                                                                     if (CFG.game.getActiveProvinceID() >= 0) {
                                                                                                                        if (CFG.game
                                                                                                                              .getProvince(
                                                                                                                                 CFG.game.getActiveProvinceID()
                                                                                                                              )
                                                                                                                              .getSeaProvince()
                                                                                                                           || CFG.game
                                                                                                                                 .getProvince(
                                                                                                                                    CFG.game
                                                                                                                                       .getActiveProvinceID()
                                                                                                                                 )
                                                                                                                                 .getWasteland()
                                                                                                                              >= 0) {
                                                                                                                           if (CFG.brushTool) {
                                                                                                                              if (!Map_TouchManager.this.actionBrush
                                                                                                                                 )
                                                                                                                               {
                                                                                                                                 Map_TouchManager.this.actionMoveMap(
                                                                                                                                    nPosX, nPosY
                                                                                                                                 );
                                                                                                                                 Map_TouchManager.this.actionBrushMove = true;
                                                                                                                              }
                                                                                                                           } else if (CFG.selectMode
                                                                                                                              && CFG.game
                                                                                                                                 .getProvince(
                                                                                                                                    CFG.game
                                                                                                                                       .getActiveProvinceID()
                                                                                                                                 )
                                                                                                                                 .getSeaProvince()) {
                                                                                                                              CFG.game
                                                                                                                                 .getSelectedProvinces()
                                                                                                                                 .clearSelectedProvinces();
                                                                                                                              CFG.menuManager
                                                                                                                                 .rebuildCreateScenario_SetUpArmies_Sliders();
                                                                                                                              if (CFG.menuManager
                                                                                                                                 .getVisible_CreateScenario_SetUpArmies_Civs()
                                                                                                                                 )
                                                                                                                               {
                                                                                                                                 CFG.menuManager
                                                                                                                                    .rebuildCreateScenario_SetUpArmies_Civs();
                                                                                                                              }
                                                                                                                           }

                                                                                                                           return;
                                                                                                                        }

                                                                                                                        if (CFG.brushTool) {
                                                                                                                           if (Map_TouchManager.this.actionBrushMove
                                                                                                                              )
                                                                                                                            {
                                                                                                                              Map_TouchManager.this.actionMoveMap(
                                                                                                                                 nPosX, nPosY
                                                                                                                              );
                                                                                                                              return;
                                                                                                                           }

                                                                                                                           Map_TouchManager.this.actionBrush = true;
                                                                                                                        }

                                                                                                                        if (CFG.selectMode) {
                                                                                                                           if (CFG.brushTool) {
                                                                                                                              CFG.game
                                                                                                                                 .getSelectedProvinces()
                                                                                                                                 .addProvince(
                                                                                                                                    CFG.game
                                                                                                                                       .getActiveProvinceID()
                                                                                                                                 );
                                                                                                                           } else {
                                                                                                                              CFG.game
                                                                                                                                 .getSelectedProvinces()
                                                                                                                                 .clearSelectedProvinces();
                                                                                                                              CFG.game
                                                                                                                                 .getSelectedProvinces()
                                                                                                                                 .addProvince(
                                                                                                                                    CFG.game
                                                                                                                                       .getActiveProvinceID()
                                                                                                                                 );
                                                                                                                           }
                                                                                                                        } else {
                                                                                                                           CFG.game
                                                                                                                              .getSelectedProvinces()
                                                                                                                              .removeProvince(
                                                                                                                                 CFG.game.getActiveProvinceID()
                                                                                                                              );
                                                                                                                        }

                                                                                                                        CFG.menuManager
                                                                                                                           .rebuildCreateScenario_SetUpArmies_Sliders();
                                                                                                                        if (CFG.menuManager
                                                                                                                           .getVisible_CreateScenario_SetUpArmies_Civs()
                                                                                                                           )
                                                                                                                         {
                                                                                                                           CFG.menuManager
                                                                                                                              .rebuildCreateScenario_SetUpArmies_Civs();
                                                                                                                        }
                                                                                                                     }
                                                                                                                  }
                                                                                                               }
                                                                                                               : (
                                                                                                                  CFG.menuManager
                                                                                                                        .getInCreateScenario_Events_SelectProvinces()
                                                                                                                     ? new Map_TouchManager.ExtraAction() {
                                                                                                                        @Override
                                                                                                                        public void extraAction(
                                                                                                                           int nPosX, int nPosY
                                                                                                                        ) {
                                                                                                                           if (CFG.game.getActiveProvinceID()
                                                                                                                              >= 0) {
                                                                                                                              if (CFG.game
                                                                                                                                 .getProvince(
                                                                                                                                    CFG.game
                                                                                                                                       .getActiveProvinceID()
                                                                                                                                 )
                                                                                                                                 .getSeaProvince()) {
                                                                                                                                 if (CFG.brushTool) {
                                                                                                                                    if (!Map_TouchManager.this.actionBrush
                                                                                                                                       )
                                                                                                                                     {
                                                                                                                                       Map_TouchManager.this.actionMoveMap(
                                                                                                                                          nPosX, nPosY
                                                                                                                                       );
                                                                                                                                       Map_TouchManager.this.actionBrushMove = true;
                                                                                                                                    }
                                                                                                                                 } else if (CFG.selectMode
                                                                                                                                    && CFG.game
                                                                                                                                       .getProvince(
                                                                                                                                          CFG.game
                                                                                                                                             .getActiveProvinceID()
                                                                                                                                       )
                                                                                                                                       .getSeaProvince()) {
                                                                                                                                    CFG.game
                                                                                                                                       .getSelectedProvinces()
                                                                                                                                       .clearSelectedProvinces();
                                                                                                                                 }

                                                                                                                                 return;
                                                                                                                              }

                                                                                                                              if (CFG.brushTool) {
                                                                                                                                 if (Map_TouchManager.this.actionBrushMove
                                                                                                                                    )
                                                                                                                                  {
                                                                                                                                    Map_TouchManager.this.actionMoveMap(
                                                                                                                                       nPosX, nPosY
                                                                                                                                    );
                                                                                                                                    return;
                                                                                                                                 }

                                                                                                                                 Map_TouchManager.this.actionBrush = true;
                                                                                                                              }

                                                                                                                              if (CFG.selectMode) {
                                                                                                                                 if (CFG.brushTool) {
                                                                                                                                    CFG.game
                                                                                                                                       .getSelectedProvinces()
                                                                                                                                       .addProvince(
                                                                                                                                          CFG.game
                                                                                                                                             .getActiveProvinceID()
                                                                                                                                       );
                                                                                                                                 } else {
                                                                                                                                    CFG.game
                                                                                                                                       .getSelectedProvinces()
                                                                                                                                       .clearSelectedProvinces();
                                                                                                                                    CFG.game
                                                                                                                                       .getSelectedProvinces()
                                                                                                                                       .addProvince(
                                                                                                                                          CFG.game
                                                                                                                                             .getActiveProvinceID()
                                                                                                                                       );
                                                                                                                                 }
                                                                                                                              } else {
                                                                                                                                 CFG.game
                                                                                                                                    .getSelectedProvinces()
                                                                                                                                    .removeProvince(
                                                                                                                                       CFG.game
                                                                                                                                          .getActiveProvinceID()
                                                                                                                                    );
                                                                                                                              }
                                                                                                                           }
                                                                                                                        }
                                                                                                                     }
                                                                                                                     : (
                                                                                                                        CFG.menuManager
                                                                                                                              .getInCreateScenario_Cores()
                                                                                                                           ? new Map_TouchManager.ExtraAction() {
                                                                                                                              @Override
                                                                                                                              public void extraAction(
                                                                                                                                 int nPosX, int nPosY
                                                                                                                              ) {
                                                                                                                                 if (CFG.game
                                                                                                                                       .getActiveProvinceID()
                                                                                                                                    >= 0) {
                                                                                                                                    if (CFG.game
                                                                                                                                          .getProvince(
                                                                                                                                             CFG.game
                                                                                                                                                .getActiveProvinceID()
                                                                                                                                          )
                                                                                                                                          .getSeaProvince()
                                                                                                                                       || CFG.game
                                                                                                                                             .getProvince(
                                                                                                                                                CFG.game
                                                                                                                                                   .getActiveProvinceID()
                                                                                                                                             )
                                                                                                                                             .getWasteland()
                                                                                                                                          >= 0) {
                                                                                                                                       if (CFG.brushTool) {
                                                                                                                                          if (!Map_TouchManager.this.actionBrush
                                                                                                                                             )
                                                                                                                                           {
                                                                                                                                             Map_TouchManager.this.actionMoveMap(
                                                                                                                                                nPosX, nPosY
                                                                                                                                             );
                                                                                                                                             Map_TouchManager.this.actionBrushMove = true;
                                                                                                                                          }
                                                                                                                                       } else if (CFG.selectMode
                                                                                                                                          && CFG.game
                                                                                                                                             .getProvince(
                                                                                                                                                CFG.game
                                                                                                                                                   .getActiveProvinceID()
                                                                                                                                             )
                                                                                                                                             .getSeaProvince()) {
                                                                                                                                          CFG.game
                                                                                                                                             .getSelectedProvinces()
                                                                                                                                             .clearSelectedProvinces();
                                                                                                                                       }

                                                                                                                                       CFG.menuManager
                                                                                                                                          .rebuildCreateScenario_Cores_SetUp();
                                                                                                                                       return;
                                                                                                                                    }

                                                                                                                                    if (CFG.brushTool) {
                                                                                                                                       if (Map_TouchManager.this.actionBrushMove
                                                                                                                                          )
                                                                                                                                        {
                                                                                                                                          Map_TouchManager.this.actionMoveMap(
                                                                                                                                             nPosX, nPosY
                                                                                                                                          );
                                                                                                                                          return;
                                                                                                                                       }

                                                                                                                                       Map_TouchManager.this.actionBrush = true;
                                                                                                                                    }

                                                                                                                                    if (CFG.selectMode) {
                                                                                                                                       if (CFG.brushTool) {
                                                                                                                                          CFG.game
                                                                                                                                             .getSelectedProvinces()
                                                                                                                                             .addProvince(
                                                                                                                                                CFG.game
                                                                                                                                                   .getActiveProvinceID()
                                                                                                                                             );
                                                                                                                                       } else {
                                                                                                                                          CFG.game
                                                                                                                                             .getSelectedProvinces()
                                                                                                                                             .clearSelectedProvinces();
                                                                                                                                          CFG.game
                                                                                                                                             .getSelectedProvinces()
                                                                                                                                             .addProvince(
                                                                                                                                                CFG.game
                                                                                                                                                   .getActiveProvinceID()
                                                                                                                                             );
                                                                                                                                       }
                                                                                                                                    } else {
                                                                                                                                       CFG.game
                                                                                                                                          .getSelectedProvinces()
                                                                                                                                          .removeProvince(
                                                                                                                                             CFG.game
                                                                                                                                                .getActiveProvinceID()
                                                                                                                                          );
                                                                                                                                    }

                                                                                                                                    CFG.menuManager
                                                                                                                                       .rebuildCreateScenario_Cores_SetUp();
                                                                                                                                 }
                                                                                                                              }
                                                                                                                           }
                                                                                                                           : (
                                                                                                                              CFG.menuManager
                                                                                                                                    .getInMapEditor_FormableCivs_Edit()
                                                                                                                                 ? new Map_TouchManager.ExtraAction(
                                                                                                                                    
                                                                                                                                 ) {
                                                                                                                                    @Override
                                                                                                                                    public void extraAction(
                                                                                                                                       int nPosX, int nPosY
                                                                                                                                    ) {
                                                                                                                                       if (CFG.game
                                                                                                                                             .getActiveProvinceID()
                                                                                                                                          >= 0) {
                                                                                                                                          if (CFG.game
                                                                                                                                             .getProvince(
                                                                                                                                                CFG.game
                                                                                                                                                   .getActiveProvinceID()
                                                                                                                                             )
                                                                                                                                             .getSeaProvince()) {
                                                                                                                                             if (CFG.brushTool
                                                                                                                                                && !Map_TouchManager.this.actionBrush
                                                                                                                                                )
                                                                                                                                              {
                                                                                                                                                Map_TouchManager.this.actionMoveMap(
                                                                                                                                                   nPosX, nPosY
                                                                                                                                                );
                                                                                                                                                Map_TouchManager.this.actionBrushMove = true;
                                                                                                                                             }

                                                                                                                                             return;
                                                                                                                                          }

                                                                                                                                          if (CFG.brushTool) {
                                                                                                                                             if (Map_TouchManager.this.actionBrushMove
                                                                                                                                                )
                                                                                                                                              {
                                                                                                                                                Map_TouchManager.this.actionMoveMap(
                                                                                                                                                   nPosX, nPosY
                                                                                                                                                );
                                                                                                                                                return;
                                                                                                                                             }

                                                                                                                                             Map_TouchManager.this.actionBrush = true;
                                                                                                                                          }

                                                                                                                                          if (CFG.selectMode) {
                                                                                                                                             if (CFG.brushTool) {
                                                                                                                                                CFG.game
                                                                                                                                                   .getSelectedProvinces()
                                                                                                                                                   .addProvince(
                                                                                                                                                      CFG.game
                                                                                                                                                         .getActiveProvinceID()
                                                                                                                                                   );
                                                                                                                                             } else {
                                                                                                                                                CFG.game
                                                                                                                                                   .getSelectedProvinces()
                                                                                                                                                   .addProvince(
                                                                                                                                                      CFG.game
                                                                                                                                                         .getActiveProvinceID()
                                                                                                                                                   );
                                                                                                                                             }
                                                                                                                                          } else {
                                                                                                                                             CFG.game
                                                                                                                                                .getSelectedProvinces()
                                                                                                                                                .removeProvince(
                                                                                                                                                   CFG.game
                                                                                                                                                      .getActiveProvinceID()
                                                                                                                                                );
                                                                                                                                          }
                                                                                                                                       }
                                                                                                                                    }
                                                                                                                                 }
                                                                                                                                 : (
                                                                                                                                    CFG.menuManager
                                                                                                                                          .getInCreateScenario_HolyRomanEmpire()
                                                                                                                                       ? new Map_TouchManager.ExtraAction(
                                                                                                                                          
                                                                                                                                       ) {
                                                                                                                                          @Override
                                                                                                                                          public void extraAction(
                                                                                                                                             int nPosX,
                                                                                                                                             int nPosY
                                                                                                                                          ) {
                                                                                                                                             if (CFG.game
                                                                                                                                                   .getActiveProvinceID()
                                                                                                                                                >= 0) {
                                                                                                                                                if (CFG.game
                                                                                                                                                   .getProvince(
                                                                                                                                                      CFG.game
                                                                                                                                                         .getActiveProvinceID()
                                                                                                                                                   )
                                                                                                                                                   .getSeaProvince()
                                                                                                                                                   )
                                                                                                                                                 {
                                                                                                                                                   if (CFG.brushTool
                                                                                                                                                      && !Map_TouchManager.this.actionBrush
                                                                                                                                                      )
                                                                                                                                                    {
                                                                                                                                                      Map_TouchManager.this.actionMoveMap(
                                                                                                                                                         nPosX,
                                                                                                                                                         nPosY
                                                                                                                                                      );
                                                                                                                                                      Map_TouchManager.this.actionBrushMove = true;
                                                                                                                                                   }

                                                                                                                                                   return;
                                                                                                                                                }

                                                                                                                                                if (CFG.brushTool
                                                                                                                                                   )
                                                                                                                                                 {
                                                                                                                                                   if (Map_TouchManager.this.actionBrushMove
                                                                                                                                                      )
                                                                                                                                                    {
                                                                                                                                                      Map_TouchManager.this.actionMoveMap(
                                                                                                                                                         nPosX,
                                                                                                                                                         nPosY
                                                                                                                                                      );
                                                                                                                                                      return;
                                                                                                                                                   }

                                                                                                                                                   Map_TouchManager.this.actionBrush = true;
                                                                                                                                                }

                                                                                                                                                if (CFG.selectMode
                                                                                                                                                   )
                                                                                                                                                 {
                                                                                                                                                   if (CFG.game
                                                                                                                                                         .getSelectedProvinces()
                                                                                                                                                         .addProvince(
                                                                                                                                                            CFG.game
                                                                                                                                                               .getActiveProvinceID()
                                                                                                                                                         )
                                                                                                                                                      && CFG.holyRomanEmpire_Manager
                                                                                                                                                         .addProvince(
                                                                                                                                                            CFG.game
                                                                                                                                                               .getActiveProvinceID()
                                                                                                                                                         )) {
                                                                                                                                                      CFG.menuManager
                                                                                                                                                         .rebuildCreateScenario_HolyRomanEmpire_Princes();
                                                                                                                                                   }
                                                                                                                                                } else if (CFG.game
                                                                                                                                                      .getSelectedProvinces()
                                                                                                                                                      .removeProvince(
                                                                                                                                                         CFG.game
                                                                                                                                                            .getActiveProvinceID()
                                                                                                                                                      )
                                                                                                                                                   && CFG.holyRomanEmpire_Manager
                                                                                                                                                      .removeProvince(
                                                                                                                                                         CFG.game
                                                                                                                                                            .getActiveProvinceID()
                                                                                                                                                      )) {
                                                                                                                                                   CFG.menuManager
                                                                                                                                                      .rebuildCreateScenario_HolyRomanEmpire_Princes();
                                                                                                                                                }
                                                                                                                                             }
                                                                                                                                          }
                                                                                                                                       }
                                                                                                                                       : (
                                                                                                                                          CFG.menuManager
                                                                                                                                                .getInGame_CreateAVassal()
                                                                                                                                             ? new Map_TouchManager.ExtraAction(
                                                                                                                                                
                                                                                                                                             ) {
                                                                                                                                                @Override
                                                                                                                                                public void extraAction(
                                                                                                                                                   int nPosX,
                                                                                                                                                   int nPosY
                                                                                                                                                ) {
                                                                                                                                                   if (CFG.game
                                                                                                                                                         .getActiveProvinceID()
                                                                                                                                                      >= 0) {
                                                                                                                                                      if (CFG.game
                                                                                                                                                            .getProvince(
                                                                                                                                                               CFG.game
                                                                                                                                                                  .getActiveProvinceID()
                                                                                                                                                            )
                                                                                                                                                            .getSeaProvince()
                                                                                                                                                         || !CFG.game
                                                                                                                                                            .getSelectedProvinces()
                                                                                                                                                            .canBeReleasedAsVassal(
                                                                                                                                                               CFG.game
                                                                                                                                                                  .getPlayer(
                                                                                                                                                                     CFG.PLAYER_TURNID
                                                                                                                                                                  )
                                                                                                                                                                  .getCivID(),
                                                                                                                                                               CFG.game
                                                                                                                                                                  .getActiveProvinceID()
                                                                                                                                                            )) {
                                                                                                                                                         if (CFG.brushTool
                                                                                                                                                            && !Map_TouchManager.this.actionBrush
                                                                                                                                                            )
                                                                                                                                                          {
                                                                                                                                                            Map_TouchManager.this.actionMoveMap(
                                                                                                                                                               nPosX,
                                                                                                                                                               nPosY
                                                                                                                                                            );
                                                                                                                                                            Map_TouchManager.this.actionBrushMove = true;
                                                                                                                                                         }

                                                                                                                                                         return;
                                                                                                                                                      }

                                                                                                                                                      if (CFG.brushTool
                                                                                                                                                         )
                                                                                                                                                       {
                                                                                                                                                         if (Map_TouchManager.this.actionBrushMove
                                                                                                                                                            )
                                                                                                                                                          {
                                                                                                                                                            Map_TouchManager.this.actionMoveMap(
                                                                                                                                                               nPosX,
                                                                                                                                                               nPosY
                                                                                                                                                            );
                                                                                                                                                            return;
                                                                                                                                                         }

                                                                                                                                                         Map_TouchManager.this.actionBrush = true;
                                                                                                                                                      }

                                                                                                                                                      if (CFG.selectMode
                                                                                                                                                         )
                                                                                                                                                       {
                                                                                                                                                         if (CFG.game
                                                                                                                                                            .getSelectedProvinces()
                                                                                                                                                            .canBeReleasedAsVassal(
                                                                                                                                                               CFG.game
                                                                                                                                                                  .getPlayer(
                                                                                                                                                                     CFG.PLAYER_TURNID
                                                                                                                                                                  )
                                                                                                                                                                  .getCivID(),
                                                                                                                                                               CFG.game
                                                                                                                                                                  .getActiveProvinceID()
                                                                                                                                                            )) {
                                                                                                                                                            if (CFG.brushTool
                                                                                                                                                               )
                                                                                                                                                             {
                                                                                                                                                               CFG.game
                                                                                                                                                                  .getSelectedProvinces()
                                                                                                                                                                  .addProvince(
                                                                                                                                                                     CFG.game
                                                                                                                                                                        .getActiveProvinceID()
                                                                                                                                                                  );
                                                                                                                                                               CFG.updateCreateAVassal_CivInfo();
                                                                                                                                                            } else {
                                                                                                                                                               CFG.game
                                                                                                                                                                  .getSelectedProvinces()
                                                                                                                                                                  .addProvince(
                                                                                                                                                                     CFG.game
                                                                                                                                                                        .getActiveProvinceID()
                                                                                                                                                                  );
                                                                                                                                                               CFG.updateCreateAVassal_CivInfo();
                                                                                                                                                            }
                                                                                                                                                         }
                                                                                                                                                      } else {
                                                                                                                                                         CFG.game
                                                                                                                                                            .getSelectedProvinces()
                                                                                                                                                            .removeProvince(
                                                                                                                                                               CFG.game
                                                                                                                                                                  .getActiveProvinceID()
                                                                                                                                                            );
                                                                                                                                                         boolean resetCapital = true;

                                                                                                                                                         for (int i = 0;
                                                                                                                                                            i
                                                                                                                                                               < CFG.game
                                                                                                                                                                  .getSelectedProvinces()
                                                                                                                                                                  .getProvincesSize();
                                                                                                                                                            i++
                                                                                                                                                         ) {
                                                                                                                                                            if (CFG.createVassal_Data
                                                                                                                                                                  .iCapitalProvinceID
                                                                                                                                                               == CFG.game
                                                                                                                                                                  .getSelectedProvinces()
                                                                                                                                                                  .getProvince(
                                                                                                                                                                     i
                                                                                                                                                                  )
                                                                                                                                                               )
                                                                                                                                                             {
                                                                                                                                                               resetCapital = false;
                                                                                                                                                               break;
                                                                                                                                                            }
                                                                                                                                                         }

                                                                                                                                                         if (resetCapital
                                                                                                                                                            )
                                                                                                                                                          {
                                                                                                                                                            CFG.createVassal_Data
                                                                                                                                                               .iCapitalProvinceID = -1;
                                                                                                                                                         }

                                                                                                                                                         CFG.updateCreateAVassal_CivInfo();
                                                                                                                                                      }
                                                                                                                                                   }
                                                                                                                                                }
                                                                                                                                             }
                                                                                                                                             : (
                                                                                                                                                CFG.menuManager
                                                                                                                                                      .getInGame_SelectProvinces()
                                                                                                                                                   ? new Map_TouchManager.ExtraAction(
                                                                                                                                                      
                                                                                                                                                   ) {
                                                                                                                                                      @Override
                                                                                                                                                      public void extraAction(
                                                                                                                                                         int nPosX,
                                                                                                                                                         int nPosY
                                                                                                                                                      ) {
                           if (Menu_InGame_SelectProvinces.typeOfAction == Menu_InGame_SelectProvinces.TypeOfAction.GENERAL_ASSIGN
                              && GeneralsManager.SELECTED_GENERAL >= 0) {
                              if (CFG.game.getActiveProvinceID() >= 0) {
                                 int tGProvID = CFG.game.getActiveProvinceID();
                                 General_Data tSelGen = GeneralsManager.getGeneral(GeneralsManager.SELECTED_GENERAL);
                                 if (tSelGen != null
                                    && CFG.game.getProvince(tGProvID).getCivID() == CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()
                                    && !CFG.game.getProvince(tGProvID).isOccupied()) {
                                    if (tSelGen.lProvinces.contains(tGProvID)) {
                                       tSelGen.lProvinces.remove((Integer)tGProvID);
                                       CFG.game.getSelectedProvinces().removeProvince(tGProvID);
                                    } else {
                                       tSelGen.lProvinces.add(tGProvID);
                                       CFG.game.getSelectedProvinces().addProvince(tGProvID);
                                    }

                                    CFG.toast.setInView(
                                       CFG.game.getProvince(tGProvID).getName() + " ⚔" + tSelGen.lProvinces.size(), CFG.COLOR_TEXT_NUM_OF_PROVINCES
                                    );
                                    CFG.toast.setTimeInView(1500);
                                    Game_Render_Province.updateDrawProvinces();
                                 }
                              }

                              return;
                           }

                           if (Menu_InGame_SelectProvinces.typeOfAction == Menu_InGame_SelectProvinces.TypeOfAction.GARRISON_SELECT) {
                              if (CFG.game.getActiveProvinceID() >= 0) {
                                 int tGProvID = CFG.game.getActiveProvinceID();
                                 if (CFG.game.getProvince(tGProvID).getCivID() == CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()
                                    && !CFG.game.getProvince(tGProvID).isOccupied()) {
                                    if (AI_Assistant.GARRISON_PROVINCES.contains(tGProvID)) {
                                       AI_Assistant.GARRISON_PROVINCES.remove((Integer)tGProvID);
                                       CFG.game.getSelectedProvinces().removeProvince(tGProvID);
                                    } else {
                                       AI_Assistant.GARRISON_PROVINCES.add(tGProvID);
                                       CFG.game.getSelectedProvinces().addProvince(tGProvID);
                                    }

                                    CFG.toast.setInView(
                                       CFG.langManager.get("Garrison") + ": " + AI_Assistant.GARRISON_PROVINCES.size(),
                                       CFG.COLOR_TEXT_NUM_OF_PROVINCES
                                    );
                                    CFG.toast.setTimeInView(1500);
                                 }
                              }

                              return;
                           }

                           if (Menu_InGame_SelectProvinces.typeOfAction == Menu_InGame_SelectProvinces.TypeOfAction.FORT_BORDER) {
                              if (CFG.game.getActiveProvinceID() >= 0) {
                                 int tFSProvID = CFG.game.getActiveProvinceID();
                                 if (CFG.game.getProvince(tFSProvID).getCivID() == CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()
                                    && !CFG.game.getProvince(tFSProvID).isOccupied()) {
                                    if (AI_Assistant.FORT_STRIP_PROVINCES.contains(tFSProvID)) {
                                       AI_Assistant.FORT_STRIP_PROVINCES.remove((Integer)tFSProvID);
                                       CFG.game.getSelectedProvinces().removeProvince(tFSProvID);
                                    } else {
                                       AI_Assistant.FORT_STRIP_PROVINCES.add(tFSProvID);
                                       CFG.game.getSelectedProvinces().addProvince(tFSProvID);
                                    }

                                    CFG.toast.setInView(
                                       CFG.langManager.get("FortStrip") + ": " + AI_Assistant.FORT_STRIP_PROVINCES.size(),
                                       CFG.COLOR_TEXT_NUM_OF_PROVINCES
                                    );
                                    CFG.toast.setTimeInView(1500);
                                 }
                              }

                              return;
                           }

                           if (Menu_InGame_SelectProvinces.typeOfAction == Menu_InGame_SelectProvinces.TypeOfAction.ARMY_PRIORITY) {
                              if (CFG.game.getActiveProvinceID() >= 0) {
                                 int tAPCivID = CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID();
                                 if (tAPCivID > 0 && tAPCivID != CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) {
                                    if (AI_Assistant.PRIORITY_COUNTRIES.contains(tAPCivID)) {
                                       AI_Assistant.PRIORITY_COUNTRIES.remove((Integer)tAPCivID);

                                       for (int tp = 0; tp < CFG.game.getCiv(tAPCivID).getNumOfProvinces(); tp++) {
                                          CFG.game.getSelectedProvinces().removeProvince(CFG.game.getCiv(tAPCivID).getProvinceID(tp));
                                       }
                                    } else {
                                       AI_Assistant.PRIORITY_COUNTRIES.add(tAPCivID);

                                       for (int tp = 0; tp < CFG.game.getCiv(tAPCivID).getNumOfProvinces(); tp++) {
                                          CFG.game.getSelectedProvinces().addProvince(CFG.game.getCiv(tAPCivID).getProvinceID(tp));
                                       }
                                    }

                                    CFG.toast.setInView(CFG.game.getCiv(tAPCivID).getCivName(), CFG.COLOR_TEXT_NUM_OF_PROVINCES);
                                    CFG.toast.setTimeInView(2000);
                                    Game_Render_Province.updateDrawProvinces();
                                 }
                              }

                              return;
                           }

                                                                                                                                                         if (CFG.game
                                                                                                                                                               .getActiveProvinceID()
                                                                                                                                                            >= 0
                                                                                                                                                            )
                                                                                                                                                          {
                                                                                                                                                            if (CFG.game
                                                                                                                                                                  .getProvince(
                                                                                                                                                                     CFG.game
                                                                                                                                                                        .getActiveProvinceID()
                                                                                                                                                                  )
                                                                                                                                                                  .getSeaProvince()
                                                                                                                                                               || CFG.FOG_OF_WAR
                                                                                                                                                                     == 2
                                                                                                                                                                  && !CFG.game
                                                                                                                                                                     .getPlayer(
                                                                                                                                                                        CFG.PLAYER_TURNID
                                                                                                                                                                     )
                                                                                                                                                                     .getMetProvince(
                                                                                                                                                                        CFG.game
                                                                                                                                                                           .getActiveProvinceID()
                                                                                                                                                                     )
                                                                                                                                                               || CFG.game
                                                                                                                                                                     .getProvince(
                                                                                                                                                                        CFG.game
                                                                                                                                                                           .getActiveProvinceID()
                                                                                                                                                                     )
                                                                                                                                                                     .getCivID()
                                                                                                                                                                  != CFG.MANAGE_DIPLOMACY_CUSTOMIZE_ALLIANCE_ID
                                                                                                                                                               || CFG.game
                                                                                                                                                                     .getProvince(
                                                                                                                                                                        CFG.game
                                                                                                                                                                           .getActiveProvinceID()
                                                                                                                                                                     )
                                                                                                                                                                     .getTrueOwnerOfProvince()
                                                                                                                                                                  != CFG.MANAGE_DIPLOMACY_CUSTOMIZE_ALLIANCE_ID
                                                                                                                                                               )
                                                                                                                                                             {
                                                                                                                                                               if (CFG.brushTool
                                                                                                                                                                  && !Map_TouchManager.this.actionBrush
                                                                                                                                                                  )
                                                                                                                                                                {
                                                                                                                                                                  Map_TouchManager.this.actionMoveMap(
                                                                                                                                                                     nPosX,
                                                                                                                                                                     nPosY
                                                                                                                                                                  );
                                                                                                                                                                  Map_TouchManager.this.actionBrushMove = true;
                                                                                                                                                               }

                                                                                                                                                               return;
                                                                                                                                                            }

                                                                                                                                                            if (CFG.brushTool
                                                                                                                                                               )
                                                                                                                                                             {
                                                                                                                                                               if (Map_TouchManager.this.actionBrushMove
                                                                                                                                                                  )
                                                                                                                                                                {
                                                                                                                                                                  Map_TouchManager.this.actionMoveMap(
                                                                                                                                                                     nPosX,
                                                                                                                                                                     nPosY
                                                                                                                                                                  );
                                                                                                                                                                  return;
                                                                                                                                                               }

                                                                                                                                                               Map_TouchManager.this.actionBrush = true;
                                                                                                                                                            }

                                                                                                                                                            if (CFG.selectMode
                                                                                                                                                               )
                                                                                                                                                             {
                                                                                                                                                               if (CFG.game
                                                                                                                                                                        .getProvince(
                                                                                                                                                                           CFG.game
                                                                                                                                                                              .getActiveProvinceID()
                                                                                                                                                                        )
                                                                                                                                                                        .getCivID()
                                                                                                                                                                     == CFG.MANAGE_DIPLOMACY_CUSTOMIZE_ALLIANCE_ID
                                                                                                                                                                  && CFG.game
                                                                                                                                                                        .getProvince(
                                                                                                                                                                           CFG.game
                                                                                                                                                                              .getActiveProvinceID()
                                                                                                                                                                        )
                                                                                                                                                                        .getCivID()
                                                                                                                                                                     == CFG.game
                                                                                                                                                                        .getProvince(
                                                                                                                                                                           CFG.game
                                                                                                                                                                              .getActiveProvinceID()
                                                                                                                                                                        )
                                                                                                                                                                        .getTrueOwnerOfProvince()
                                                                                                                                                                  )
                                                                                                                                                                {
                                                                                                                                                                  if (CFG.brushTool
                                                                                                                                                                     )
                                                                                                                                                                   {
                                                                                                                                                                     CFG.game
                                                                                                                                                                        .getSelectedProvinces()
                                                                                                                                                                        .addProvince(
                                                                                                                                                                           CFG.game
                                                                                                                                                                              .getActiveProvinceID()
                                                                                                                                                                        );
                                                                                                                                                                  } else {
                                                                                                                                                                     CFG.game
                                                                                                                                                                        .getSelectedProvinces()
                                                                                                                                                                        .addProvince(
                                                                                                                                                                           CFG.game
                                                                                                                                                                              .getActiveProvinceID()
                                                                                                                                                                        );
                                                                                                                                                                  }
                                                                                                                                                               }
                                                                                                                                                            } else {
                                                                                                                                                               CFG.game
                                                                                                                                                                  .getSelectedProvinces()
                                                                                                                                                                  .removeProvince(
                                                                                                                                                                     CFG.game
                                                                                                                                                                        .getActiveProvinceID()
                                                                                                                                                                  );
                                                                                                                                                            }
                                                                                                                                                         }
                                                                                                                                                      }
                                                                                                                                                   }
                                                                                                                                                   : (
                                                                                                                                                      CFG.menuManager
                                                                                                                                                            .getInGame_TradeSelectCiv()
                                                                                                                                                         ? new Map_TouchManager.ExtraAction(
                                                                                                                                                            
                                                                                                                                                         ) {
                                                                                                                                                            @Override
                                                                                                                                                            public void extraAction(
                                                                                                                                                               int nPosX,
                                                                                                                                                               int nPosY
                                                                                                                                                            ) {
                                                                                                                                                               if (CFG.game
                                                                                                                                                                        .getActiveProvinceID()
                                                                                                                                                                     >= 0
                                                                                                                                                                  && CFG.game
                                                                                                                                                                        .getProvince(
                                                                                                                                                                           CFG.game
                                                                                                                                                                              .getActiveProvinceID()
                                                                                                                                                                        )
                                                                                                                                                                        .getCivID()
                                                                                                                                                                     != CFG.tradeRequest
                                                                                                                                                                        .iCivLEFT
                                                                                                                                                                  && CFG.game
                                                                                                                                                                        .getProvince(
                                                                                                                                                                           CFG.game
                                                                                                                                                                              .getActiveProvinceID()
                                                                                                                                                                        )
                                                                                                                                                                        .getCivID()
                                                                                                                                                                     != CFG.tradeRequest
                                                                                                                                                                        .iCivRIGHT
                                                                                                                                                                  && !CFG.game
                                                                                                                                                                     .getProvince(
                                                                                                                                                                        CFG.game
                                                                                                                                                                           .getActiveProvinceID()
                                                                                                                                                                     )
                                                                                                                                                                     .getSeaProvince()
                                                                                                                                                                  && CFG.game
                                                                                                                                                                        .getProvince(
                                                                                                                                                                           CFG.game
                                                                                                                                                                              .getActiveProvinceID()
                                                                                                                                                                        )
                                                                                                                                                                        .getWasteland()
                                                                                                                                                                     < 0
                                                                                                                                                                  && CFG.game
                                                                                                                                                                        .getProvince(
                                                                                                                                                                           CFG.game
                                                                                                                                                                              .getActiveProvinceID()
                                                                                                                                                                        )
                                                                                                                                                                        .getCivID()
                                                                                                                                                                     > 0
                                                                                                                                                                  && (
                                                                                                                                                                     CFG.FOG_OF_WAR
                                                                                                                                                                           != 2
                                                                                                                                                                        || CFG.game
                                                                                                                                                                           .getPlayer(
                                                                                                                                                                              CFG.PLAYER_TURNID
                                                                                                                                                                           )
                                                                                                                                                                           .getMetProvince(
                                                                                                                                                                              CFG.game
                                                                                                                                                                                 .getActiveProvinceID()
                                                                                                                                                                           )
                                                                                                                                                                  )
                                                                                                                                                                  )
                                                                                                                                                                {
                                                                                                                                                                  CFG.setDialogType(
                                                                                                                                                                     Dialog.TRADE_REQUEST_SELECT_CIV
                                                                                                                                                                  );
                                                                                                                                                               }
                                                                                                                                                            }
                                                                                                                                                         }
                                                                                                                                                         : (
                                                                                                                                                            CFG.menuManager
                                                                                                                                                                  .getInManageDiplomacy()
                                                                                                                                                               ? (
                                                                                                                                                                  CFG.menuManager
                                                                                                                                                                        .getInManageDiplomacy_Relations_Interactive()
                                                                                                                                                                     ? new Map_TouchManager.ExtraAction(
                                                                                                                                                                        
                                                                                                                                                                     ) {
                                                                                                                                                                        @Override
                                                                                                                                                                        public void extraAction(
                                                                                                                                                                           int nPosX,
                                                                                                                                                                           int nPosY
                                                                                                                                                                        ) {
                                                                                                                                                                           if (CFG.game
                                                                                                                                                                                    .getActiveProvinceID()
                                                                                                                                                                                 >= 0
                                                                                                                                                                              && CFG.game
                                                                                                                                                                                    .getProvince(
                                                                                                                                                                                       CFG.game
                                                                                                                                                                                          .getActiveProvinceID()
                                                                                                                                                                                    )
                                                                                                                                                                                    .getCivID()
                                                                                                                                                                                 > 0
                                                                                                                                                                              )
                                                                                                                                                                            {
                                                                                                                                                                              if (CFG.game
                                                                                                                                                                                    .getProvince(
                                                                                                                                                                                       CFG.game
                                                                                                                                                                                          .getActiveProvinceID()
                                                                                                                                                                                    )
                                                                                                                                                                                    .getCivID()
                                                                                                                                                                                 != CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID
                                                                                                                                                                                 )
                                                                                                                                                                               {
                                                                                                                                                                                 CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID2 = CFG.game
                                                                                                                                                                                    .getProvince(
                                                                                                                                                                                       CFG.game
                                                                                                                                                                                          .getActiveProvinceID()
                                                                                                                                                                                    )
                                                                                                                                                                                    .getCivID();
                                                                                                                                                                                 CFG.menuManager
                                                                                                                                                                                    .getManageDiplomacy_Relations_Interactive()
                                                                                                                                                                                    .getMenuElement(
                                                                                                                                                                                       1
                                                                                                                                                                                    )
                                                                                                                                                                                    .setClickable(
                                                                                                                                                                                       true
                                                                                                                                                                                    );
                                                                                                                                                                                 CFG.menuManager
                                                                                                                                                                                    .getManageDiplomacy_Relations_Interactive()
                                                                                                                                                                                    .getMenuElement(
                                                                                                                                                                                       2
                                                                                                                                                                                    )
                                                                                                                                                                                    .setClickable(
                                                                                                                                                                                       true
                                                                                                                                                                                    );
                                                                                                                                                                                 CFG.menuManager
                                                                                                                                                                                    .getManageDiplomacy_Relations_Interactive()
                                                                                                                                                                                    .getMenuElement(
                                                                                                                                                                                       3
                                                                                                                                                                                    )
                                                                                                                                                                                    .setClickable(
                                                                                                                                                                                       true
                                                                                                                                                                                    );
                                                                                                                                                                                 CFG.menuManager
                                                                                                                                                                                    .getManageDiplomacy_Relations_Interactive()
                                                                                                                                                                                    .getMenuElement(
                                                                                                                                                                                       2
                                                                                                                                                                                    )
                                                                                                                                                                                    .setCurrent(
                                                                                                                                                                                       (int)CFG.game
                                                                                                                                                                                          .getCivRelation_OfCivB(
                                                                                                                                                                                             CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID,
                                                                                                                                                                                             CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID2
                                                                                                                                                                                          )
                                                                                                                                                                                    );
                                                                                                                                                                                 CFG.menuManager
                                                                                                                                                                                    .getManageDiplomacy_Relations_Interactive()
                                                                                                                                                                                    .getMenuElement(
                                                                                                                                                                                       4
                                                                                                                                                                                    )
                                                                                                                                                                                    .setClickable(
                                                                                                                                                                                       true
                                                                                                                                                                                    );
                                                                                                                                                                                 CFG.menuManager
                                                                                                                                                                                    .getManageDiplomacy_Relations_Interactive()
                                                                                                                                                                                    .getMenuElement(
                                                                                                                                                                                       5
                                                                                                                                                                                    )
                                                                                                                                                                                    .setClickable(
                                                                                                                                                                                       true
                                                                                                                                                                                    );
                                                                                                                                                                                 CFG.menuManager
                                                                                                                                                                                    .getManageDiplomacy_Relations_Interactive()
                                                                                                                                                                                    .getMenuElement(
                                                                                                                                                                                       6
                                                                                                                                                                                    )
                                                                                                                                                                                    .setClickable(
                                                                                                                                                                                       true
                                                                                                                                                                                    );
                                                                                                                                                                                 CFG.menuManager
                                                                                                                                                                                    .getManageDiplomacy_Relations_Interactive()
                                                                                                                                                                                    .getMenuElement(
                                                                                                                                                                                       5
                                                                                                                                                                                    )
                                                                                                                                                                                    .setCurrent(
                                                                                                                                                                                       (int)CFG.game
                                                                                                                                                                                          .getCivRelation_OfCivB(
                                                                                                                                                                                             CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID2,
                                                                                                                                                                                             CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID
                                                                                                                                                                                          )
                                                                                                                                                                                    );
                                                                                                                                                                              } else {
                                                                                                                                                                                 CFG.menuManager
                                                                                                                                                                                    .getManageDiplomacy_Relations_Interactive()
                                                                                                                                                                                    .getMenuElement(
                                                                                                                                                                                       1
                                                                                                                                                                                    )
                                                                                                                                                                                    .setClickable(
                                                                                                                                                                                       false
                                                                                                                                                                                    );
                                                                                                                                                                                 CFG.menuManager
                                                                                                                                                                                    .getManageDiplomacy_Relations_Interactive()
                                                                                                                                                                                    .getMenuElement(
                                                                                                                                                                                       2
                                                                                                                                                                                    )
                                                                                                                                                                                    .setClickable(
                                                                                                                                                                                       false
                                                                                                                                                                                    );
                                                                                                                                                                                 CFG.menuManager
                                                                                                                                                                                    .getManageDiplomacy_Relations_Interactive()
                                                                                                                                                                                    .getMenuElement(
                                                                                                                                                                                       3
                                                                                                                                                                                    )
                                                                                                                                                                                    .setClickable(
                                                                                                                                                                                       false
                                                                                                                                                                                    );
                                                                                                                                                                                 CFG.menuManager
                                                                                                                                                                                    .getManageDiplomacy_Relations_Interactive()
                                                                                                                                                                                    .getMenuElement(
                                                                                                                                                                                       2
                                                                                                                                                                                    )
                                                                                                                                                                                    .setCurrent(
                                                                                                                                                                                       0
                                                                                                                                                                                    );
                                                                                                                                                                                 CFG.menuManager
                                                                                                                                                                                    .getManageDiplomacy_Relations_Interactive()
                                                                                                                                                                                    .getMenuElement(
                                                                                                                                                                                       4
                                                                                                                                                                                    )
                                                                                                                                                                                    .setClickable(
                                                                                                                                                                                       false
                                                                                                                                                                                    );
                                                                                                                                                                                 CFG.menuManager
                                                                                                                                                                                    .getManageDiplomacy_Relations_Interactive()
                                                                                                                                                                                    .getMenuElement(
                                                                                                                                                                                       5
                                                                                                                                                                                    )
                                                                                                                                                                                    .setClickable(
                                                                                                                                                                                       false
                                                                                                                                                                                    );
                                                                                                                                                                                 CFG.menuManager
                                                                                                                                                                                    .getManageDiplomacy_Relations_Interactive()
                                                                                                                                                                                    .getMenuElement(
                                                                                                                                                                                       6
                                                                                                                                                                                    )
                                                                                                                                                                                    .setClickable(
                                                                                                                                                                                       false
                                                                                                                                                                                    );
                                                                                                                                                                                 CFG.menuManager
                                                                                                                                                                                    .getManageDiplomacy_Relations_Interactive()
                                                                                                                                                                                    .getMenuElement(
                                                                                                                                                                                       5
                                                                                                                                                                                    )
                                                                                                                                                                                    .setCurrent(
                                                                                                                                                                                       0
                                                                                                                                                                                    );
                                                                                                                                                                                 CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID2 = 0;
                                                                                                                                                                              }
                                                                                                                                                                           }
                                                                                                                                                                        }
                                                                                                                                                                     }
                                                                                                                                                                     : (
                                                                                                                                                                        CFG.menuManager
                                                                                                                                                                              .getInManageDiplomacy_Pacts3()
                                                                                                                                                                           ? new Map_TouchManager.ExtraAction(
                                                                                                                                                                              
                                                                                                                                                                           ) {
                                                                                                                                                                              @Override
                                                                                                                                                                              public void extraAction(
                                                                                                                                                                                 int nPosX,
                                                                                                                                                                                 int nPosY
                                                                                                                                                                              ) {
                                                                                                                                                                                 if (CFG.game
                                                                                                                                                                                          .getProvince(
                                                                                                                                                                                             CFG.game
                                                                                                                                                                                                .getActiveProvinceID()
                                                                                                                                                                                          )
                                                                                                                                                                                          .getCivID()
                                                                                                                                                                                       > 0
                                                                                                                                                                                    && CFG.MANAGE_DIPLOMACY_CUSTOMIZE_ALLIANCE_ID
                                                                                                                                                                                       != CFG.game
                                                                                                                                                                                          .getProvince(
                                                                                                                                                                                             CFG.game
                                                                                                                                                                                                .getActiveProvinceID()
                                                                                                                                                                                          )
                                                                                                                                                                                          .getCivID()
                                                                                                                                                                                    )
                                                                                                                                                                                  {
                                                                                                                                                                                    CFG.MANAGE_DIPLOMACY_CUSTOMIZE_ALLIANCE_ID = CFG.game
                                                                                                                                                                                       .getProvince(
                                                                                                                                                                                          CFG.game
                                                                                                                                                                                             .getActiveProvinceID()
                                                                                                                                                                                       )
                                                                                                                                                                                       .getCivID();
                                                                                                                                                                                    CFG.menuManager
                                                                                                                                                                                       .rebuildManageDiplomacy_Pacts_List();
                                                                                                                                                                                 }
                                                                                                                                                                              }
                                                                                                                                                                           }
                                                                                                                                                                           : (
                                                                                                                                                                              CFG.menuManager
                                                                                                                                                                                    .getInManageDiplomacy_Truces()
                                                                                                                                                                                 ? new Map_TouchManager.ExtraAction(
                                                                                                                                                                                    
                                                                                                                                                                                 ) {
                                                                                                                                                                                    @Override
                                                                                                                                                                                    public void extraAction(
                                                                                                                                                                                       int nPosX,
                                                                                                                                                                                       int nPosY
                                                                                                                                                                                    ) {
                                                                                                                                                                                       if (CFG.game
                                                                                                                                                                                                .getProvince(
                                                                                                                                                                                                   CFG.game
                                                                                                                                                                                                      .getActiveProvinceID()
                                                                                                                                                                                                )
                                                                                                                                                                                                .getCivID()
                                                                                                                                                                                             > 0
                                                                                                                                                                                          && CFG.MANAGE_DIPLOMACY_CUSTOMIZE_ALLIANCE_ID
                                                                                                                                                                                             != CFG.game
                                                                                                                                                                                                .getProvince(
                                                                                                                                                                                                   CFG.game
                                                                                                                                                                                                      .getActiveProvinceID()
                                                                                                                                                                                                )
                                                                                                                                                                                                .getCivID()
                                                                                                                                                                                          )
                                                                                                                                                                                        {
                                                                                                                                                                                          CFG.MANAGE_DIPLOMACY_CUSTOMIZE_ALLIANCE_ID = CFG.game
                                                                                                                                                                                             .getProvince(
                                                                                                                                                                                                CFG.game
                                                                                                                                                                                                   .getActiveProvinceID()
                                                                                                                                                                                             )
                                                                                                                                                                                             .getCivID();
                                                                                                                                                                                          CFG.menuManager
                                                                                                                                                                                             .rebuildManageDiplomacy_Trcues_List();
                                                                                                                                                                                       }
                                                                                                                                                                                    }
                                                                                                                                                                                 }
                                                                                                                                                                                 : (
                                                                                                                                                                                    CFG.menuManager
                                                                                                                                                                                          .getInManageDiplomacy_Guarantee()
                                                                                                                                                                                       ? new Map_TouchManager.ExtraAction(
                                                                                                                                                                                          
                                                                                                                                                                                       ) {
                                                                                                                                                                                          @Override
                                                                                                                                                                                          public void extraAction(
                                                                                                                                                                                             int nPosX,
                                                                                                                                                                                             int nPosY
                                                                                                                                                                                          ) {
                                                                                                                                                                                             if (CFG.game
                                                                                                                                                                                                      .getProvince(
                                                                                                                                                                                                         CFG.game
                                                                                                                                                                                                            .getActiveProvinceID()
                                                                                                                                                                                                      )
                                                                                                                                                                                                      .getCivID()
                                                                                                                                                                                                   > 0
                                                                                                                                                                                                && CFG.MANAGE_DIPLOMACY_CUSTOMIZE_ALLIANCE_ID
                                                                                                                                                                                                   != CFG.game
                                                                                                                                                                                                      .getProvince(
                                                                                                                                                                                                         CFG.game
                                                                                                                                                                                                            .getActiveProvinceID()
                                                                                                                                                                                                      )
                                                                                                                                                                                                      .getCivID()
                                                                                                                                                                                                )
                                                                                                                                                                                              {
                                                                                                                                                                                                CFG.MANAGE_DIPLOMACY_CUSTOMIZE_ALLIANCE_ID = CFG.game
                                                                                                                                                                                                   .getProvince(
                                                                                                                                                                                                      CFG.game
                                                                                                                                                                                                         .getActiveProvinceID()
                                                                                                                                                                                                   )
                                                                                                                                                                                                   .getCivID();
                                                                                                                                                                                                CFG.menuManager
                                                                                                                                                                                                   .rebuildManageDiplomacy_Guarantee_List();
                                                                                                                                                                                             }
                                                                                                                                                                                          }
                                                                                                                                                                                       }
                                                                                                                                                                                       : (
                                                                                                                                                                                          CFG.menuManager
                                                                                                                                                                                                .getInManageDiplomacy_DefensivePact()
                                                                                                                                                                                             ? new Map_TouchManager.ExtraAction(
                                                                                                                                                                                                
                                                                                                                                                                                             ) {
                                                                                                                                                                                                @Override
                                                                                                                                                                                                public void extraAction(
                                                                                                                                                                                                   int nPosX,
                                                                                                                                                                                                   int nPosY
                                                                                                                                                                                                ) {
                                                                                                                                                                                                   if (CFG.game
                                                                                                                                                                                                            .getProvince(
                                                                                                                                                                                                               CFG.game
                                                                                                                                                                                                                  .getActiveProvinceID()
                                                                                                                                                                                                            )
                                                                                                                                                                                                            .getCivID()
                                                                                                                                                                                                         > 0
                                                                                                                                                                                                      && CFG.MANAGE_DIPLOMACY_CUSTOMIZE_ALLIANCE_ID
                                                                                                                                                                                                         != CFG.game
                                                                                                                                                                                                            .getProvince(
                                                                                                                                                                                                               CFG.game
                                                                                                                                                                                                                  .getActiveProvinceID()
                                                                                                                                                                                                            )
                                                                                                                                                                                                            .getCivID()
                                                                                                                                                                                                      )
                                                                                                                                                                                                    {
                                                                                                                                                                                                      CFG.MANAGE_DIPLOMACY_CUSTOMIZE_ALLIANCE_ID = CFG.game
                                                                                                                                                                                                         .getProvince(
                                                                                                                                                                                                            CFG.game
                                                                                                                                                                                                               .getActiveProvinceID()
                                                                                                                                                                                                         )
                                                                                                                                                                                                         .getCivID();
                                                                                                                                                                                                      CFG.menuManager
                                                                                                                                                                                                         .rebuildManageDiplomacy_DefensivePacts_List();
                                                                                                                                                                                                   }
                                                                                                                                                                                                }
                                                                                                                                                                                             }
                                                                                                                                                                                             : (
                                                                                                                                                                                                CFG.menuManager
                                                                                                                                                                                                      .getInManageDiplomacy_MilitaryAccess()
                                                                                                                                                                                                   ? new Map_TouchManager.ExtraAction(
                                                                                                                                                                                                      
                                                                                                                                                                                                   ) {
                                                                                                                                                                                                      @Override
                                                                                                                                                                                                      public void extraAction(
                                                                                                                                                                                                         int nPosX,
                                                                                                                                                                                                         int nPosY
                                                                                                                                                                                                      ) {
                                                                                                                                                                                                         if (CFG.game
                                                                                                                                                                                                                  .getProvince(
                                                                                                                                                                                                                     CFG.game
                                                                                                                                                                                                                        .getActiveProvinceID()
                                                                                                                                                                                                                  )
                                                                                                                                                                                                                  .getCivID()
                                                                                                                                                                                                               > 0
                                                                                                                                                                                                            && CFG.MANAGE_DIPLOMACY_CUSTOMIZE_ALLIANCE_ID
                                                                                                                                                                                                               != CFG.game
                                                                                                                                                                                                                  .getProvince(
                                                                                                                                                                                                                     CFG.game
                                                                                                                                                                                                                        .getActiveProvinceID()
                                                                                                                                                                                                                  )
                                                                                                                                                                                                                  .getCivID()
                                                                                                                                                                                                            )
                                                                                                                                                                                                          {
                                                                                                                                                                                                            CFG.MANAGE_DIPLOMACY_CUSTOMIZE_ALLIANCE_ID = CFG.game
                                                                                                                                                                                                               .getProvince(
                                                                                                                                                                                                                  CFG.game
                                                                                                                                                                                                                     .getActiveProvinceID()
                                                                                                                                                                                                               )
                                                                                                                                                                                                               .getCivID();
                                                                                                                                                                                                            CFG.menuManager
                                                                                                                                                                                                               .rebuildManageDiplomacy_MilitaryAccess_List();
                                                                                                                                                                                                         }
                                                                                                                                                                                                      }
                                                                                                                                                                                                   }
                                                                                                                                                                                                   : (
                                                                                                                                                                                                      CFG.menuManager
                                                                                                                                                                                                            .getInManageDiplomacy_Vassals()
                                                                                                                                                                                                         ? new Map_TouchManager.ExtraAction(
                                                                                                                                                                                                            
                                                                                                                                                                                                         ) {
                                                                                                                                                                                                            @Override
                                                                                                                                                                                                            public void extraAction(
                                                                                                                                                                                                               int nPosX,
                                                                                                                                                                                                               int nPosY
                                                                                                                                                                                                            ) {
                                                                                                                                                                                                               if (CFG.game
                                                                                                                                                                                                                        .getProvince(
                                                                                                                                                                                                                           CFG.game
                                                                                                                                                                                                                              .getActiveProvinceID()
                                                                                                                                                                                                                        )
                                                                                                                                                                                                                        .getCivID()
                                                                                                                                                                                                                     > 0
                                                                                                                                                                                                                  && CFG.MANAGE_DIPLOMACY_CUSTOMIZE_ALLIANCE_ID
                                                                                                                                                                                                                     != CFG.game
                                                                                                                                                                                                                        .getProvince(
                                                                                                                                                                                                                           CFG.game
                                                                                                                                                                                                                              .getActiveProvinceID()
                                                                                                                                                                                                                        )
                                                                                                                                                                                                                        .getCivID()
                                                                                                                                                                                                                  )
                                                                                                                                                                                                                {
                                                                                                                                                                                                                  CFG.MANAGE_DIPLOMACY_CUSTOMIZE_ALLIANCE_ID = CFG.game
                                                                                                                                                                                                                     .getProvince(
                                                                                                                                                                                                                        CFG.game
                                                                                                                                                                                                                           .getActiveProvinceID()
                                                                                                                                                                                                                     )
                                                                                                                                                                                                                     .getCivID();
                                                                                                                                                                                                                  CFG.menuManager
                                                                                                                                                                                                                     .rebuildManageDiplomacy_Vassals_List();
                                                                                                                                                                                                               }
                                                                                                                                                                                                            }
                                                                                                                                                                                                         }
                                                                                                                                                                                                         : new Map_TouchManager.ExtraAction(
                                                                                                                                                                                                            
                                                                                                                                                                                                         ) {
                                                                                                                                                                                                            @Override
                                                                                                                                                                                                            public void extraAction(
                                                                                                                                                                                                               int nPosX,
                                                                                                                                                                                                               int nPosY
                                                                                                                                                                                                            ) {
                                                                                                                                                                                                            }
                                                                                                                                                                                                         }
                                                                                                                                                                                                   )
                                                                                                                                                                                             )
                                                                                                                                                                                       )
                                                                                                                                                                                 )
                                                                                                                                                                           )
                                                                                                                                                                     )
                                                                                                                                                               )
                                                                                                                                                               : (
                                                                                                                                                                  CFG.menuManager
                                                                                                                                                                        .getInCreateCity()
                                                                                                                                                                     ? new Map_TouchManager.ExtraAction(
                                                                                                                                                                        
                                                                                                                                                                     ) {
                                                                                                                                                                        @Override
                                                                                                                                                                        public void extraAction(
                                                                                                                                                                           int nPosX,
                                                                                                                                                                           int nPosY
                                                                                                                                                                        ) {
                                                                                                                                                                           if (CFG.game
                                                                                                                                                                                 .getActiveProvinceID()
                                                                                                                                                                              >= 0
                                                                                                                                                                              )
                                                                                                                                                                            {
                                                                                                                                                                              CFG.editorCity
                                                                                                                                                                                 .setPosX(
                                                                                                                                                                                    (
                                                                                                                                                                                          (int)(
                                                                                                                                                                                                nPosX
                                                                                                                                                                                                   / CFG.map
                                                                                                                                                                                                      .getMapScale()
                                                                                                                                                                                                      .getCurrentScale()
                                                                                                                                                                                             )
                                                                                                                                                                                             - CFG.map
                                                                                                                                                                                                .getMapCoordinates()
                                                                                                                                                                                                .getPosX()
                                                                                                                                                                                       )
                                                                                                                                                                                       / CFG.map
                                                                                                                                                                                          .getMapBG()
                                                                                                                                                                                          .getMapScale()
                                                                                                                                                                                 );
                                                                                                                                                                              CFG.editorCity
                                                                                                                                                                                 .setPosY(
                                                                                                                                                                                    (
                                                                                                                                                                                          (int)(
                                                                                                                                                                                                nPosY
                                                                                                                                                                                                   / CFG.map
                                                                                                                                                                                                      .getMapScale()
                                                                                                                                                                                                      .getCurrentScale()
                                                                                                                                                                                             )
                                                                                                                                                                                             - CFG.map
                                                                                                                                                                                                .getMapCoordinates()
                                                                                                                                                                                                .getPosY()
                                                                                                                                                                                       )
                                                                                                                                                                                       / CFG.map
                                                                                                                                                                                          .getMapBG()
                                                                                                                                                                                          .getMapScale()
                                                                                                                                                                                 );
                                                                                                                                                                              if (CFG.editorCity
                                                                                                                                                                                    .getPosX()
                                                                                                                                                                                 > CFG.map
                                                                                                                                                                                       .getMapBG()
                                                                                                                                                                                       .getWidth()
                                                                                                                                                                                    / CFG.map
                                                                                                                                                                                       .getMapBG()
                                                                                                                                                                                       .getMapScale()
                                                                                                                                                                                 )
                                                                                                                                                                               {
                                                                                                                                                                                 CFG.editorCity
                                                                                                                                                                                    .setPosX(
                                                                                                                                                                                       CFG.editorCity
                                                                                                                                                                                             .getPosX()
                                                                                                                                                                                          % (
                                                                                                                                                                                             CFG.map
                                                                                                                                                                                                   .getMapBG()
                                                                                                                                                                                                   .getWidth()
                                                                                                                                                                                                / CFG.map
                                                                                                                                                                                                   .getMapBG()
                                                                                                                                                                                                   .getMapScale()
                                                                                                                                                                                          )
                                                                                                                                                                                    );
                                                                                                                                                                              }

                                                                                                                                                                              CFG.menuManager
                                                                                                                                                                                 .getCreateCity_UpdateSaveButton();
                                                                                                                                                                           }
                                                                                                                                                                        }
                                                                                                                                                                     }
                                                                                                                                                                     : (
                                                                                                                                                                        CFG.menuManager
                                                                                                                                                                              .getInMapEditor_Terrain()
                                                                                                                                                                           ? new Map_TouchManager.ExtraAction(
                                                                                                                                                                              
                                                                                                                                                                           ) {
                                                                                                                                                                              @Override
                                                                                                                                                                              public void extraAction(
                                                                                                                                                                                 int nPosX,
                                                                                                                                                                                 int nPosY
                                                                                                                                                                              ) {
                                                                                                                                                                                 if (CFG.game
                                                                                                                                                                                       .getActiveProvinceID()
                                                                                                                                                                                    >= 0
                                                                                                                                                                                    )
                                                                                                                                                                                  {
                                                                                                                                                                                    if (CFG.game
                                                                                                                                                                                       .getProvince(
                                                                                                                                                                                          CFG.game
                                                                                                                                                                                             .getActiveProvinceID()
                                                                                                                                                                                       )
                                                                                                                                                                                       .getSeaProvince()
                                                                                                                                                                                       )
                                                                                                                                                                                     {
                                                                                                                                                                                       if (!Map_TouchManager.this.actionBrush
                                                                                                                                                                                          )
                                                                                                                                                                                        {
                                                                                                                                                                                          Map_TouchManager.this.actionMoveMap(
                                                                                                                                                                                             nPosX,
                                                                                                                                                                                             nPosY
                                                                                                                                                                                          );
                                                                                                                                                                                          Map_TouchManager.this.actionBrushMove = true;
                                                                                                                                                                                       }
                                                                                                                                                                                    } else if (!Map_TouchManager.this.actionBrushMove
                                                                                                                                                                                       )
                                                                                                                                                                                     {
                                                                                                                                                                                       Editor_TerrainType.actionSave(
                                                                                                                                                                                          true
                                                                                                                                                                                       );
                                                                                                                                                                                       Map_TouchManager.this.actionBrush = true;
                                                                                                                                                                                    } else {
                                                                                                                                                                                       Map_TouchManager.this.actionMoveMap(
                                                                                                                                                                                          nPosX,
                                                                                                                                                                                          nPosY
                                                                                                                                                                                       );
                                                                                                                                                                                    }
                                                                                                                                                                                 }
                                                                                                                                                                              }
                                                                                                                                                                           }
                                                                                                                                                                           : (
                                                                                                                                                                              CFG.menuManager
                                                                                                                                                                                    .getInMapEditor_GrowthRate()
                                                                                                                                                                                 ? new Map_TouchManager.ExtraAction(
                                                                                                                                                                                    
                                                                                                                                                                                 ) {
                                                                                                                                                                                    @Override
                                                                                                                                                                                    public void extraAction(
                                                                                                                                                                                       int nPosX,
                                                                                                                                                                                       int nPosY
                                                                                                                                                                                    ) {
                                                                                                                                                                                       if (CFG.game
                                                                                                                                                                                             .getActiveProvinceID()
                                                                                                                                                                                          >= 0
                                                                                                                                                                                          )
                                                                                                                                                                                        {
                                                                                                                                                                                          if (CFG.game
                                                                                                                                                                                             .getProvince(
                                                                                                                                                                                                CFG.game
                                                                                                                                                                                                   .getActiveProvinceID()
                                                                                                                                                                                             )
                                                                                                                                                                                             .getSeaProvince()
                                                                                                                                                                                             )
                                                                                                                                                                                           {
                                                                                                                                                                                             if (!Map_TouchManager.this.actionBrush
                                                                                                                                                                                                )
                                                                                                                                                                                              {
                                                                                                                                                                                                Map_TouchManager.this.actionMoveMap(
                                                                                                                                                                                                   nPosX,
                                                                                                                                                                                                   nPosY
                                                                                                                                                                                                );
                                                                                                                                                                                                Map_TouchManager.this.actionBrushMove = true;
                                                                                                                                                                                             }
                                                                                                                                                                                          } else if (!Map_TouchManager.this.actionBrushMove
                                                                                                                                                                                             )
                                                                                                                                                                                           {
                                                                                                                                                                                             Editor_GrowthRate.actionSave(
                                                                                                                                                                                                true
                                                                                                                                                                                             );
                                                                                                                                                                                             Map_TouchManager.this.actionBrush = true;
                                                                                                                                                                                          } else {
                                                                                                                                                                                             Map_TouchManager.this.actionMoveMap(
                                                                                                                                                                                                nPosX,
                                                                                                                                                                                                nPosY
                                                                                                                                                                                             );
                                                                                                                                                                                          }
                                                                                                                                                                                       }
                                                                                                                                                                                    }
                                                                                                                                                                                 }
                                                                                                                                                                                 : (
                                                                                                                                                                                    CFG.menuManager
                                                                                                                                                                                          .getInMapEditor_Continents()
                                                                                                                                                                                       ? new Map_TouchManager.ExtraAction(
                                                                                                                                                                                          
                                                                                                                                                                                       ) {
                                                                                                                                                                                          @Override
                                                                                                                                                                                          public void extraAction(
                                                                                                                                                                                             int nPosX,
                                                                                                                                                                                             int nPosY
                                                                                                                                                                                          ) {
                                                                                                                                                                                             if (CFG.game
                                                                                                                                                                                                   .getActiveProvinceID()
                                                                                                                                                                                                >= 0
                                                                                                                                                                                                )
                                                                                                                                                                                              {
                                                                                                                                                                                                if (!Map_TouchManager.this.actionBrushMove
                                                                                                                                                                                                   )
                                                                                                                                                                                                 {
                                                                                                                                                                                                   Editor_Continents.actionSave(
                                                                                                                                                                                                      true
                                                                                                                                                                                                   );
                                                                                                                                                                                                   Map_TouchManager.this.actionBrush = true;
                                                                                                                                                                                                } else {
                                                                                                                                                                                                   Map_TouchManager.this.actionMoveMap(
                                                                                                                                                                                                      nPosX,
                                                                                                                                                                                                      nPosY
                                                                                                                                                                                                   );
                                                                                                                                                                                                }
                                                                                                                                                                                             }
                                                                                                                                                                                          }
                                                                                                                                                                                       }
                                                                                                                                                                                       : (
                                                                                                                                                                                          CFG.menuManager
                                                                                                                                                                                                .getInMapEditor_Regions()
                                                                                                                                                                                             ? new Map_TouchManager.ExtraAction(
                                                                                                                                                                                                
                                                                                                                                                                                             ) {
                                                                                                                                                                                                @Override
                                                                                                                                                                                                public void extraAction(
                                                                                                                                                                                                   int nPosX,
                                                                                                                                                                                                   int nPosY
                                                                                                                                                                                                ) {
                                                                                                                                                                                                   if (CFG.game
                                                                                                                                                                                                         .getActiveProvinceID()
                                                                                                                                                                                                      >= 0
                                                                                                                                                                                                      )
                                                                                                                                                                                                    {
                                                                                                                                                                                                      if (CFG.game
                                                                                                                                                                                                         .getProvince(
                                                                                                                                                                                                            CFG.game
                                                                                                                                                                                                               .getActiveProvinceID()
                                                                                                                                                                                                         )
                                                                                                                                                                                                         .getSeaProvince()
                                                                                                                                                                                                         )
                                                                                                                                                                                                       {
                                                                                                                                                                                                         if (!Map_TouchManager.this.actionBrush
                                                                                                                                                                                                            )
                                                                                                                                                                                                          {
                                                                                                                                                                                                            Map_TouchManager.this.actionMoveMap(
                                                                                                                                                                                                               nPosX,
                                                                                                                                                                                                               nPosY
                                                                                                                                                                                                            );
                                                                                                                                                                                                            Map_TouchManager.this.actionBrushMove = true;
                                                                                                                                                                                                         }
                                                                                                                                                                                                      } else if (!Map_TouchManager.this.actionBrushMove
                                                                                                                                                                                                         )
                                                                                                                                                                                                       {
                                                                                                                                                                                                         Editor_MapRegions.actionSave(
                                                                                                                                                                                                            true
                                                                                                                                                                                                         );
                                                                                                                                                                                                         Map_TouchManager.this.actionBrush = true;
                                                                                                                                                                                                      } else {
                                                                                                                                                                                                         Map_TouchManager.this.actionMoveMap(
                                                                                                                                                                                                            nPosX,
                                                                                                                                                                                                            nPosY
                                                                                                                                                                                                         );
                                                                                                                                                                                                      }
                                                                                                                                                                                                   }
                                                                                                                                                                                                }
                                                                                                                                                                                             }
                                                                                                                                                                                             : (
                                                                                                                                                                                                CFG.menuManager
                                                                                                                                                                                                      .getInGameEditor_Regions()
                                                                                                                                                                                                   ? new Map_TouchManager.ExtraAction(
                                                                                                                                                                                                      
                                                                                                                                                                                                   ) {
                                                                                                                                                                                                      @Override
                                                                                                                                                                                                      public void extraAction(
                                                                                                                                                                                                         int nPosX,
                                                                                                                                                                                                         int nPosY
                                                                                                                                                                                                      ) {
                                                                                                                                                                                                         if (CFG.game
                                                                                                                                                                                                               .getActiveProvinceID()
                                                                                                                                                                                                            >= 0
                                                                                                                                                                                                            )
                                                                                                                                                                                                          {
                                                                                                                                                                                                            if (!Map_TouchManager.this.actionBrushMove
                                                                                                                                                                                                               )
                                                                                                                                                                                                             {
                                                                                                                                                                                                               Editor_Regions.actionUpdateRegionID(
                                                                                                                                                                                                                  true
                                                                                                                                                                                                               );
                                                                                                                                                                                                               Map_TouchManager.this.actionBrush = true;
                                                                                                                                                                                                            } else {
                                                                                                                                                                                                               Map_TouchManager.this.actionMoveMap(
                                                                                                                                                                                                                  nPosX,
                                                                                                                                                                                                                  nPosY
                                                                                                                                                                                                               );
                                                                                                                                                                                                            }
                                                                                                                                                                                                         }
                                                                                                                                                                                                      }
                                                                                                                                                                                                   }
                                                                                                                                                                                                   : new Map_TouchManager.ExtraAction(
                                                                                                                                                                                                      
                                                                                                                                                                                                   ) {
                                                                                                                                                                                                      @Override
                                                                                                                                                                                                      public void extraAction(
                                                                                                                                                                                                         int nPosX,
                                                                                                                                                                                                         int nPosY
                                                                                                                                                                                                      ) {
                                                                                                                                                                                                      }
                                                                                                                                                                                                   }
                                                                                                                                                                                             )
                                                                                                                                                                                       )
                                                                                                                                                                                 )
                                                                                                                                                                           )
                                                                                                                                                                     )
                                                                                                                                                               )
                                                                                                                                                         )
                                                                                                                                                   )
                                                                                                                                             )
                                                                                                                                       )
                                                                                                                                 )
                                                                                                                           )
                                                                                                                     )
                                                                                                               )
                                                                                                         )
                                                                                                   )
                                                                                             )
                                                                                       )
                                                                                 )
                                                                           )
                                                                     )
                                                               )
                                                         )
                                                   )
                                             )
                                       )
                                 )
                           )
                           : new Map_TouchManager.ExtraAction() {
                              @Override
                              public void extraAction(int nPosX, int nPosY) {
                                 CFG.game.setActiveProvinceID(-1);
                              }
                           }
                     )
                     : new Map_TouchManager.ExtraAction() {
                        @Override
                        public void extraAction(int nPosX, int nPosY) {
                           if (CFG.game.getActiveProvinceID() >= 0) {
                              if (CFG.game.getProvince(CFG.game.getActiveProvinceID()).getSeaProvince()) {
                                 CFG.game.setActiveProvinceID(-1);
                              } else if (CFG.timelapseManager.timelineOwners.get(CFG.game.getActiveProvinceID()) > 0) {
                                 CFG.toast
                                    .setInView(
                                       CFG.game.getCiv(CFG.timelapseManager.timelineOwners.get(CFG.game.getActiveProvinceID())).getCivName(),
                                       CFG.COLOR_TEXT_NUM_OF_PROVINCES
                                    );
                                 CFG.toast.setTimeInView(1500);
                              }
                           }
                        }
                     }
               )
         );
      this.map_ActionDown_ExtraAction = null;
      this.map_ActionDown_ExtraAction = CFG.menuManager.getInManageDiplomacy()
         ? new Map_TouchManager.ExtraAction() {
            @Override
            public void extraAction(int nPosX, int nPosY) {
               if ((
                     CFG.menuManager.getManageDiplomacy_Alliances().getVisible()
                        || CFG.menuManager.getInManageDiplomacy_Relations_Interactive()
                        || CFG.menuManager.getInManageDiplomacy_Pacts3()
                        || CFG.menuManager.getInManageDiplomacy_Truces()
                        || CFG.menuManager.getInManageDiplomacy_MilitaryAccess()
                        || CFG.menuManager.getInManageDiplomacy_DefensivePact()
                        || CFG.menuManager.getInManageDiplomacy_Guarantee()
                        || CFG.menuManager.getInManageDiplomacy_Vassals()
                  )
                  && CFG.game.getActiveProvinceID() >= 0
                  && CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID() != 0) {
                  int tempOldActiveProvinceID = CFG.game.getActiveProvinceID();
                  CFG.game.setProvinceID((int)(nPosX / CFG.map.getMapScale().getCurrentScale()), (int)(nPosY / CFG.map.getMapScale().getCurrentScale()));
                  if (CFG.game.getActiveProvinceID() == tempOldActiveProvinceID) {
                     CFG.map.getMapCoordinates().setDisableMovingMap(true);
                     CFG.menuManager.getDrawCivilization().setVisible(true);
                     CFG.menuManager.getDrawCivilization().setCivID(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID());
                     CFG.menuManager.getDrawCivilization().setPosX(nPosX);
                     CFG.menuManager.getDrawCivilization().setPosY(nPosY);
                  } else {
                     CFG.game.setActiveProvinceID(tempOldActiveProvinceID);
                  }
               }
            }
         }
         : new Map_TouchManager.ExtraAction() {
            @Override
            public void extraAction(int nPosX, int nPosY) {
            }
         };
      this.map_ActionMove_ExtraAction = null;
      this.map_ActionMove_ExtraAction = CFG.menuManager.getInManageDiplomacy() ? new Map_TouchManager.ExtraAction() {
         @Override
         public void extraAction(int nPosX, int nPosY) {
            CFG.menuManager.getDrawCivilization().setPosX(nPosX);
            CFG.menuManager.getDrawCivilization().setPosY(nPosY);
         }
      } : new Map_TouchManager.ExtraAction() {
         @Override
         public void extraAction(int nPosX, int nPosY) {
         }
      };
      this.map_ActionUp_ExtraAction = null;
      this.map_ActionUp_ExtraAction = CFG.menuManager.getInManageDiplomacy_Vassals()
         ? new Map_TouchManager.ExtraAction() {
            @Override
            public void extraAction(int nPosX, int nPosY) {
               if (CFG.map.getMapCoordinates().getDisableMovingMap()) {
                  if (CFG.game.getActiveProvinceID() >= 0 && CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID() > 0) {
                     for (int i = 0; i < CFG.menuManager.getManageDiplomacy_Vassals().getMenuElementsSize() - 1; i++) {
                        if (nPosX
                              >= CFG.menuManager.getManageDiplomacy_Vassals().getMenuElement(i).getPosX()
                                 + CFG.menuManager.getManageDiplomacy_Vassals().getMenuPosX()
                           && nPosX
                              <= CFG.menuManager.getManageDiplomacy_Vassals().getMenuElement(i).getPosX()
                                 + CFG.menuManager.getManageDiplomacy_Vassals().getMenuPosX()
                                 + CFG.menuManager.getManageDiplomacy_Vassals().getMenuElement(i).getWidth()
                           && nPosY
                              >= CFG.menuManager.getManageDiplomacy_Vassals().getMenuElement(i).getPosY()
                                 + CFG.menuManager.getManageDiplomacy_Vassals().getMenuPosY()
                           && nPosY
                              <= CFG.menuManager.getManageDiplomacy_Vassals().getMenuElement(i).getPosY()
                                 + CFG.menuManager.getManageDiplomacy_Vassals().getMenuElement(i).getHeight()
                                 + CFG.menuManager.getManageDiplomacy_Vassals().getMenuPosY()) {
                           if (i == 0) {
                              if (CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV2 != CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()) {
                                 CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1 = CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID();
                              } else {
                                 int tempID = CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1;
                                 CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1 = CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV2;
                                 CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV2 = tempID;
                              }
                           } else if (CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1 != CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()) {
                              CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV2 = CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID();
                           } else {
                              int tempID = CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1;
                              CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1 = CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV2;
                              CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV2 = tempID;
                           }

                           if (CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1 > 0 && CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV2 > 0) {
                              CFG.menuManager
                                 .getManageDiplomacy_Vassals()
                                 .getMenuElement(CFG.menuManager.getManageDiplomacy_Vassals().getMenuElementsSize() - 1)
                                 .setClickable(true);
                           }

                           if (CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1 > 0) {
                              CFG.menuManager
                                 .getManageDiplomacy_Vassals()
                                 .getMenuElement(0)
                                 .setText(CFG.game.getCiv(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getCivName());
                           } else {
                              CFG.menuManager.getManageDiplomacy_Vassals().getMenuElement(0).setText("");
                           }

                           if (CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV2 > 0) {
                              CFG.menuManager
                                 .getManageDiplomacy_Vassals()
                                 .getMenuElement(1)
                                 .setText(CFG.game.getCiv(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV2).getCivName());
                           } else {
                              CFG.menuManager.getManageDiplomacy_Vassals().getMenuElement(1).setText("");
                           }
                           break;
                        }
                     }
                  }

                  CFG.game.setActiveProvinceID(-1);
                  CFG.menuManager.getDrawCivilization().setVisible(false);
                  CFG.MANAGE_DIPLOMACY_DRAW_HELP_LINE = false;
               }

               CFG.map.getMapCoordinates().setDisableMovingMap(false);
            }
         }
         : (
            CFG.menuManager.getInManageDiplomacy()
               ? new Map_TouchManager.ExtraAction() {
                  @Override
                  public void extraAction(int nPosX, int nPosY) {
                     int i;
                     label528: {
                        if (CFG.menuManager.getManageDiplomacy_Alliances().getVisible() && CFG.map.getMapCoordinates().getDisableMovingMap()) {
                           if (CFG.game.getActiveProvinceID() >= 0 && CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID() > 0) {
                              i = 0;

                              while (true) {
                                 if (i < CFG.menuManager.getManageDiplomacy_Alliances().getMenuElementsSize()) {
                                    if (nPosX
                                          < CFG.menuManager.getManageDiplomacy_Alliances().getMenuElement(i).getPosX()
                                             + CFG.menuManager.getManageDiplomacy_Alliances().getMenuPosX()
                                       || nPosX
                                          > CFG.menuManager.getManageDiplomacy_Alliances().getMenuElement(i).getPosX()
                                             + CFG.menuManager.getManageDiplomacy_Alliances().getMenuPosX()
                                             + CFG.menuManager.getManageDiplomacy_Alliances().getMenuElement(i).getWidth()
                                       || nPosY
                                          < CFG.menuManager.getManageDiplomacy_Alliances().getMenuElement(i).getPosY()
                                             + CFG.menuManager.getManageDiplomacy_Alliances().getMenuPosY()
                                       || nPosY
                                          > CFG.menuManager.getManageDiplomacy_Alliances().getMenuElement(i).getPosY()
                                             + CFG.menuManager.getManageDiplomacy_Alliances().getMenuElement(i).getHeight()
                                             + CFG.menuManager.getManageDiplomacy_Alliances().getMenuPosY()) {
                                       i++;
                                       continue;
                                    }

                                    if (i == 0) {
                                       CFG.game.addAlliance("");
                                       CFG.game
                                          .getAlliance(CFG.game.getAlliancesSize() - 1)
                                          .addCivilization(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID());
                                       if (CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).getAllianceID() != 0) {
                                          CFG.game
                                             .getAlliance(CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).getAllianceID())
                                             .removeCivilization(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID());
                                       }

                                       CFG.game
                                          .getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID())
                                          .setAllianceID(CFG.game.getAlliancesSize() - 1);
                                       CFG.MANAGE_DIPLOMACY_CUSTOMIZE_ALLIANCE_ID = CFG.game.getAlliancesSize() - 1;
                                       CFG.menuManager.setViewID(Menu.eCUSTOMIZE_ALLIANCE);
                                       CFG.game.disableDrawCivilizationRegions_ActiveProvince();
                                       CFG.menuManager.getDrawCivilization().setVisible(false);
                                       CFG.game.setActiveProvinceID(-1);
                                       CFG.MANAGE_DIPLOMACY_DRAW_HELP_LINE = false;
                                       return;
                                    }

                                    if (CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).getAllianceID() == 0) {
                                       break label528;
                                    }

                                    if (i != CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).getAllianceID()) {
                                       CFG.game
                                          .getAlliance(CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).getAllianceID())
                                          .removeCivilization(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID());
                                       break label528;
                                    }
                                 }

                                 i = CFG.game.getActiveProvinceID();
                                 CFG.game
                                    .setProvinceID(
                                       (int)(nPosX / CFG.map.getMapScale().getCurrentScale()), (int)(nPosY / CFG.map.getMapScale().getCurrentScale())
                                    );
                                 if (CFG.game.getActiveProvinceID() >= 0
                                    && CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID() > 0
                                    && CFG.game.getProvince(i).getCivID() != CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()) {
                                    if (CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).getAllianceID() > 0) {
                                       if (CFG.game.getCiv(CFG.game.getProvince(i).getCivID()).getAllianceID() != 0) {
                                          CFG.game
                                             .getAlliance(CFG.game.getCiv(CFG.game.getProvince(i).getCivID()).getAllianceID())
                                             .removeCivilization(CFG.game.getProvince(i).getCivID());
                                       }

                                       CFG.game
                                          .getCiv(CFG.game.getProvince(i).getCivID())
                                          .setAllianceID(CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).getAllianceID());
                                       CFG.game
                                          .getAlliance(CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).getAllianceID())
                                          .addCivilization(CFG.game.getProvince(i).getCivID());
                                       CFG.game.checkAlliances();
                                       CFG.menuManager.rebuildManageDiplomacy_Alliances();
                                    } else {
                                       CFG.game.addAlliance("");
                                       CFG.game
                                          .getAlliance(CFG.game.getAlliancesSize() - 1)
                                          .addCivilization(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID());
                                       CFG.game.getAlliance(CFG.game.getAlliancesSize() - 1).addCivilization(CFG.game.getProvince(i).getCivID());
                                       if (CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).getAllianceID() != 0) {
                                          CFG.game
                                             .getAlliance(CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).getAllianceID())
                                             .removeCivilization(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID());
                                       }

                                       if (CFG.game.getCiv(CFG.game.getProvince(i).getCivID()).getAllianceID() != 0) {
                                          CFG.game
                                             .getAlliance(CFG.game.getCiv(CFG.game.getProvince(i).getCivID()).getAllianceID())
                                             .removeCivilization(CFG.game.getProvince(i).getCivID());
                                       }

                                       CFG.game
                                          .getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID())
                                          .setAllianceID(CFG.game.getAlliancesSize() - 1);
                                       CFG.game.getCiv(CFG.game.getProvince(i).getCivID()).setAllianceID(CFG.game.getAlliancesSize() - 1);
                                       CFG.game.checkAlliances();
                                       CFG.menuManager.rebuildManageDiplomacy_Alliances();
                                       CFG.game.disableDrawCivilizationRegions_ActiveProvince();
                                    }
                                 }

                                 CFG.menuManager.getDrawCivilization().setVisible(false);
                                 CFG.game.setActiveProvinceID(-1);
                                 CFG.MANAGE_DIPLOMACY_DRAW_HELP_LINE = false;
                                 break;
                              }
                           }
                        } else if (CFG.menuManager.getInManageDiplomacy_Pacts3() && CFG.map.getMapCoordinates().getDisableMovingMap()) {
                           if (CFG.game.getActiveProvinceID() >= 0 && CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID() > 0) {
                              for (int ix = 0; ix < CFG.menuManager.getManageDiplomacy_Pacts3().getMenuElementsSize() - 1; ix++) {
                                 if (nPosX
                                       >= CFG.menuManager.getManageDiplomacy_Pacts3().getMenuElement(ix).getPosX()
                                          + CFG.menuManager.getManageDiplomacy_Pacts3().getMenuPosX()
                                    && nPosX
                                       <= CFG.menuManager.getManageDiplomacy_Pacts3().getMenuElement(ix).getPosX()
                                          + CFG.menuManager.getManageDiplomacy_Pacts3().getMenuPosX()
                                          + CFG.menuManager.getManageDiplomacy_Pacts3().getMenuElement(ix).getWidth()
                                    && nPosY
                                       >= CFG.menuManager.getManageDiplomacy_Pacts3().getMenuElement(ix).getPosY()
                                          + CFG.menuManager.getManageDiplomacy_Pacts3().getMenuPosY()
                                    && nPosY
                                       <= CFG.menuManager.getManageDiplomacy_Pacts3().getMenuElement(ix).getPosY()
                                          + CFG.menuManager.getManageDiplomacy_Pacts3().getMenuElement(ix).getHeight()
                                          + CFG.menuManager.getManageDiplomacy_Pacts3().getMenuPosY()) {
                                    if (ix == 0) {
                                       if (CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV2 == CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()) {
                                          return;
                                       }

                                       CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1 = CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID();
                                    } else {
                                       if (CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1 == CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()) {
                                          return;
                                       }

                                       CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV2 = CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID();
                                    }

                                    if (CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1 > 0 && CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV2 > 0) {
                                       CFG.menuManager
                                          .getManageDiplomacy_Pacts3()
                                          .getMenuElement(CFG.menuManager.getManageDiplomacy_Pacts3().getMenuElementsSize() - 1)
                                          .setClickable(true);
                                    }

                                    CFG.menuManager
                                       .getManageDiplomacy_Pacts3()
                                       .getMenuElement(ix)
                                       .setText(CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).getCivName());
                                    CFG.game.setActiveProvinceID(-1);
                                    CFG.menuManager.getDrawCivilization().setVisible(false);
                                    CFG.MANAGE_DIPLOMACY_DRAW_HELP_LINE = false;
                                    CFG.map.getMapCoordinates().setDisableMovingMap(false);
                                    return;
                                 }
                              }
                           }

                           i = CFG.game.getActiveProvinceID();
                           CFG.game
                              .setProvinceID((int)(nPosX / CFG.map.getMapScale().getCurrentScale()), (int)(nPosY / CFG.map.getMapScale().getCurrentScale()));
                           if (CFG.game.getActiveProvinceID() >= 0 && CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID() > 0) {
                              if (CFG.game.getProvince(i).getCivID() != CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()) {
                                 if (CFG.game
                                       .getCivNonAggressionPact(
                                          CFG.game.getProvince(i).getCivID(), CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()
                                       )
                                    == 0) {
                                    CFG.game
                                       .setCivNonAggressionPact(
                                          CFG.game.getProvince(i).getCivID(), CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID(), 5
                                       );
                                    CFG.game.setActiveProvinceID(i);
                                    if (CFG.game.getActiveProvinceID() >= 0 && CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID() > 0) {
                                       CFG.MANAGE_DIPLOMACY_CUSTOMIZE_ALLIANCE_ID = CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID();
                                    }

                                    CFG.menuManager.rebuildManageDiplomacy_Pacts3();
                                    CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1 = -1;
                                    CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV2 = -1;
                                 } else {
                                    CFG.game.setActiveProvinceID(i);
                                 }
                              } else {
                                 CFG.game.setActiveProvinceID(i);
                              }
                           } else {
                              CFG.game.setActiveProvinceID(i);
                           }
                        } else if (CFG.menuManager.getInManageDiplomacy_Truces() && CFG.map.getMapCoordinates().getDisableMovingMap()) {
                           if (CFG.game.getActiveProvinceID() >= 0 && CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID() > 0) {
                              for (int ixx = 0; ixx < CFG.menuManager.getManageDiplomacy_Truces().getMenuElementsSize() - 1; ixx++) {
                                 if (nPosX
                                       >= CFG.menuManager.getManageDiplomacy_Truces().getMenuElement(ixx).getPosX()
                                          + CFG.menuManager.getManageDiplomacy_Truces().getMenuPosX()
                                    && nPosX
                                       <= CFG.menuManager.getManageDiplomacy_Truces().getMenuElement(ixx).getPosX()
                                          + CFG.menuManager.getManageDiplomacy_Truces().getMenuPosX()
                                          + CFG.menuManager.getManageDiplomacy_Truces().getMenuElement(ixx).getWidth()
                                    && nPosY
                                       >= CFG.menuManager.getManageDiplomacy_Truces().getMenuElement(ixx).getPosY()
                                          + CFG.menuManager.getManageDiplomacy_Truces().getMenuPosY()
                                    && nPosY
                                       <= CFG.menuManager.getManageDiplomacy_Truces().getMenuElement(ixx).getPosY()
                                          + CFG.menuManager.getManageDiplomacy_Truces().getMenuElement(ixx).getHeight()
                                          + CFG.menuManager.getManageDiplomacy_Truces().getMenuPosY()) {
                                    if (ixx == 0) {
                                       if (CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV2 == CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()) {
                                          return;
                                       }

                                       CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1 = CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID();
                                    } else {
                                       if (CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1 == CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()) {
                                          return;
                                       }

                                       CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV2 = CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID();
                                    }

                                    if (CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1 > 0 && CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV2 > 0) {
                                       CFG.menuManager
                                          .getManageDiplomacy_Truces()
                                          .getMenuElement(CFG.menuManager.getManageDiplomacy_Truces().getMenuElementsSize() - 1)
                                          .setClickable(true);
                                    }

                                    CFG.menuManager
                                       .getManageDiplomacy_Truces()
                                       .getMenuElement(ixx)
                                       .setText(CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).getCivName());
                                    CFG.game.setActiveProvinceID(-1);
                                    CFG.menuManager.getDrawCivilization().setVisible(false);
                                    CFG.MANAGE_DIPLOMACY_DRAW_HELP_LINE = false;
                                    CFG.map.getMapCoordinates().setDisableMovingMap(false);
                                    return;
                                 }
                              }
                           }

                           i = CFG.game.getActiveProvinceID();
                           CFG.game
                              .setProvinceID((int)(nPosX / CFG.map.getMapScale().getCurrentScale()), (int)(nPosY / CFG.map.getMapScale().getCurrentScale()));
                           if (CFG.game.getActiveProvinceID() >= 0 && CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID() > 0) {
                              if (CFG.game.getProvince(i).getCivID() != CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()) {
                                 if (CFG.game.getCivTruce(CFG.game.getProvince(i).getCivID(), CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID())
                                    == 0) {
                                    CFG.game
                                       .setCivTruce(CFG.game.getProvince(i).getCivID(), CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID(), 5);
                                    CFG.game.setActiveProvinceID(i);
                                    if (CFG.game.getActiveProvinceID() >= 0 && CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID() > 0) {
                                       CFG.MANAGE_DIPLOMACY_CUSTOMIZE_ALLIANCE_ID = CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID();
                                    }

                                    CFG.menuManager.rebuildManageDiplomacy_Truces();
                                    CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1 = -1;
                                    CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV2 = -1;
                                 } else {
                                    CFG.game.setActiveProvinceID(i);
                                 }
                              } else {
                                 CFG.game.setActiveProvinceID(i);
                              }
                           } else {
                              CFG.game.setActiveProvinceID(i);
                           }
                        } else if (CFG.menuManager.getInManageDiplomacy_MilitaryAccess() && CFG.map.getMapCoordinates().getDisableMovingMap()) {
                           if (CFG.game.getActiveProvinceID() >= 0 && CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID() > 0) {
                              for (int ixxx = 0; ixxx < CFG.menuManager.getManageDiplomacy_MilitaryAccess().getMenuElementsSize() - 1; ixxx++) {
                                 if (nPosX
                                       >= CFG.menuManager.getManageDiplomacy_MilitaryAccess().getMenuElement(ixxx).getPosX()
                                          + CFG.menuManager.getManageDiplomacy_MilitaryAccess().getMenuPosX()
                                    && nPosX
                                       <= CFG.menuManager.getManageDiplomacy_MilitaryAccess().getMenuElement(ixxx).getPosX()
                                          + CFG.menuManager.getManageDiplomacy_MilitaryAccess().getMenuPosX()
                                          + CFG.menuManager.getManageDiplomacy_MilitaryAccess().getMenuElement(ixxx).getWidth()
                                    && nPosY
                                       >= CFG.menuManager.getManageDiplomacy_MilitaryAccess().getMenuElement(ixxx).getPosY()
                                          + CFG.menuManager.getManageDiplomacy_MilitaryAccess().getMenuPosY()
                                    && nPosY
                                       <= CFG.menuManager.getManageDiplomacy_MilitaryAccess().getMenuElement(ixxx).getPosY()
                                          + CFG.menuManager.getManageDiplomacy_MilitaryAccess().getMenuElement(ixxx).getHeight()
                                          + CFG.menuManager.getManageDiplomacy_MilitaryAccess().getMenuPosY()) {
                                    if (ixxx == 0) {
                                       if (CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV2 == CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()) {
                                          return;
                                       }

                                       CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1 = CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID();
                                    } else {
                                       if (CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1 == CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()) {
                                          return;
                                       }

                                       CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV2 = CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID();
                                    }

                                    if (CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1 > 0 && CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV2 > 0) {
                                       CFG.menuManager
                                          .getManageDiplomacy_MilitaryAccess()
                                          .getMenuElement(CFG.menuManager.getManageDiplomacy_MilitaryAccess().getMenuElementsSize() - 1)
                                          .setClickable(true);
                                    }

                                    CFG.menuManager
                                       .getManageDiplomacy_MilitaryAccess()
                                       .getMenuElement(ixxx)
                                       .setText(CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).getCivName());
                                    CFG.game.setActiveProvinceID(-1);
                                    CFG.menuManager.getDrawCivilization().setVisible(false);
                                    CFG.MANAGE_DIPLOMACY_DRAW_HELP_LINE = false;
                                    CFG.map.getMapCoordinates().setDisableMovingMap(false);
                                    return;
                                 }
                              }
                           }

                           i = CFG.game.getActiveProvinceID();
                           CFG.game
                              .setProvinceID((int)(nPosX / CFG.map.getMapScale().getCurrentScale()), (int)(nPosY / CFG.map.getMapScale().getCurrentScale()));
                           if (CFG.game.getActiveProvinceID() >= 0 && CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID() > 0) {
                              if (CFG.game.getProvince(i).getCivID() != CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()) {
                                 if (CFG.game
                                       .getMilitaryAccess(CFG.game.getProvince(i).getCivID(), CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID())
                                    == 0) {
                                    CFG.game
                                       .setMilitaryAccess(
                                          CFG.game.getProvince(i).getCivID(), CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID(), 5
                                       );
                                    CFG.game.setActiveProvinceID(i);
                                    if (CFG.game.getActiveProvinceID() >= 0 && CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID() > 0) {
                                       CFG.MANAGE_DIPLOMACY_CUSTOMIZE_ALLIANCE_ID = CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID();
                                    }

                                    CFG.menuManager.rebuildManageDiplomacy_MilitaryAccess();
                                    CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1 = -1;
                                    CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV2 = -1;
                                 } else {
                                    CFG.game.setActiveProvinceID(i);
                                 }
                              } else {
                                 CFG.game.setActiveProvinceID(i);
                              }
                           } else {
                              CFG.game.setActiveProvinceID(i);
                           }
                        } else if (CFG.menuManager.getInManageDiplomacy_Guarantee() && CFG.map.getMapCoordinates().getDisableMovingMap()) {
                           if (CFG.game.getActiveProvinceID() >= 0 && CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID() > 0) {
                              for (int ixxxx = 0; ixxxx < CFG.menuManager.getManageDiplomacy_Guarantee().getMenuElementsSize() - 1; ixxxx++) {
                                 if (nPosX
                                       >= CFG.menuManager.getManageDiplomacy_Guarantee().getMenuElement(ixxxx).getPosX()
                                          + CFG.menuManager.getManageDiplomacy_Guarantee().getMenuPosX()
                                    && nPosX
                                       <= CFG.menuManager.getManageDiplomacy_Guarantee().getMenuElement(ixxxx).getPosX()
                                          + CFG.menuManager.getManageDiplomacy_Guarantee().getMenuPosX()
                                          + CFG.menuManager.getManageDiplomacy_Guarantee().getMenuElement(ixxxx).getWidth()
                                    && nPosY
                                       >= CFG.menuManager.getManageDiplomacy_Guarantee().getMenuElement(ixxxx).getPosY()
                                          + CFG.menuManager.getManageDiplomacy_Guarantee().getMenuPosY()
                                    && nPosY
                                       <= CFG.menuManager.getManageDiplomacy_Guarantee().getMenuElement(ixxxx).getPosY()
                                          + CFG.menuManager.getManageDiplomacy_Guarantee().getMenuElement(ixxxx).getHeight()
                                          + CFG.menuManager.getManageDiplomacy_Guarantee().getMenuPosY()) {
                                    if (ixxxx == 0) {
                                       if (CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV2 == CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()) {
                                          return;
                                       }

                                       CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1 = CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID();
                                    } else {
                                       if (CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1 == CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()) {
                                          return;
                                       }

                                       CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV2 = CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID();
                                    }

                                    if (CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1 > 0 && CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV2 > 0) {
                                       CFG.menuManager
                                          .getManageDiplomacy_Guarantee()
                                          .getMenuElement(CFG.menuManager.getManageDiplomacy_Guarantee().getMenuElementsSize() - 1)
                                          .setClickable(true);
                                    }

                                    CFG.menuManager
                                       .getManageDiplomacy_Guarantee()
                                       .getMenuElement(ixxxx)
                                       .setText(CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).getCivName());
                                    CFG.game.setActiveProvinceID(-1);
                                    CFG.menuManager.getDrawCivilization().setVisible(false);
                                    CFG.MANAGE_DIPLOMACY_DRAW_HELP_LINE = false;
                                    CFG.map.getMapCoordinates().setDisableMovingMap(false);
                                    return;
                                 }
                              }
                           }

                           i = CFG.game.getActiveProvinceID();
                           CFG.game
                              .setProvinceID((int)(nPosX / CFG.map.getMapScale().getCurrentScale()), (int)(nPosY / CFG.map.getMapScale().getCurrentScale()));
                           if (CFG.game.getActiveProvinceID() >= 0 && CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID() > 0) {
                              if (CFG.game.getProvince(i).getCivID() != CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()) {
                                 if (CFG.game.getGuarantee(CFG.game.getProvince(i).getCivID(), CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID())
                                    == 0) {
                                    CFG.game
                                       .setGuarantee(CFG.game.getProvince(i).getCivID(), CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID(), 5);
                                    CFG.game.setActiveProvinceID(i);
                                    if (CFG.game.getActiveProvinceID() >= 0 && CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID() > 0) {
                                       CFG.MANAGE_DIPLOMACY_CUSTOMIZE_ALLIANCE_ID = CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID();
                                    }

                                    CFG.menuManager.rebuildManageDiplomacy_Guarantee();
                                    CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1 = -1;
                                    CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV2 = -1;
                                 } else {
                                    CFG.game.setActiveProvinceID(i);
                                 }
                              } else {
                                 CFG.game.setActiveProvinceID(i);
                              }
                           } else {
                              CFG.game.setActiveProvinceID(i);
                           }
                        } else if (CFG.menuManager.getInManageDiplomacy_DefensivePact() && CFG.map.getMapCoordinates().getDisableMovingMap()) {
                           if (CFG.game.getActiveProvinceID() >= 0 && CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID() > 0) {
                              for (int ixxxxx = 0; ixxxxx < CFG.menuManager.getManageDiplomacy_Defensive().getMenuElementsSize() - 1; ixxxxx++) {
                                 if (nPosX
                                       >= CFG.menuManager.getManageDiplomacy_Defensive().getMenuElement(ixxxxx).getPosX()
                                          + CFG.menuManager.getManageDiplomacy_Defensive().getMenuPosX()
                                    && nPosX
                                       <= CFG.menuManager.getManageDiplomacy_Defensive().getMenuElement(ixxxxx).getPosX()
                                          + CFG.menuManager.getManageDiplomacy_Defensive().getMenuPosX()
                                          + CFG.menuManager.getManageDiplomacy_Defensive().getMenuElement(ixxxxx).getWidth()
                                    && nPosY
                                       >= CFG.menuManager.getManageDiplomacy_Defensive().getMenuElement(ixxxxx).getPosY()
                                          + CFG.menuManager.getManageDiplomacy_Defensive().getMenuPosY()
                                    && nPosY
                                       <= CFG.menuManager.getManageDiplomacy_Defensive().getMenuElement(ixxxxx).getPosY()
                                          + CFG.menuManager.getManageDiplomacy_Defensive().getMenuElement(ixxxxx).getHeight()
                                          + CFG.menuManager.getManageDiplomacy_Defensive().getMenuPosY()) {
                                    if (ixxxxx == 0) {
                                       if (CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV2 == CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()) {
                                          return;
                                       }

                                       CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1 = CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID();
                                    } else {
                                       if (CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1 == CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()) {
                                          return;
                                       }

                                       CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV2 = CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID();
                                    }

                                    if (CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1 > 0 && CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV2 > 0) {
                                       CFG.menuManager
                                          .getManageDiplomacy_Defensive()
                                          .getMenuElement(CFG.menuManager.getManageDiplomacy_Defensive().getMenuElementsSize() - 1)
                                          .setClickable(true);
                                    }

                                    CFG.menuManager
                                       .getManageDiplomacy_Defensive()
                                       .getMenuElement(ixxxxx)
                                       .setText(CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).getCivName());
                                    CFG.game.setActiveProvinceID(-1);
                                    CFG.menuManager.getDrawCivilization().setVisible(false);
                                    CFG.MANAGE_DIPLOMACY_DRAW_HELP_LINE = false;
                                    CFG.map.getMapCoordinates().setDisableMovingMap(false);
                                    return;
                                 }
                              }
                           }

                           i = CFG.game.getActiveProvinceID();
                           CFG.game
                              .setProvinceID((int)(nPosX / CFG.map.getMapScale().getCurrentScale()), (int)(nPosY / CFG.map.getMapScale().getCurrentScale()));
                           if (CFG.game.getActiveProvinceID() >= 0 && CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID() > 0) {
                              if (CFG.game.getProvince(i).getCivID() != CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()) {
                                 if (CFG.game
                                       .getDefensivePact(CFG.game.getProvince(i).getCivID(), CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID())
                                    == 0) {
                                    CFG.game
                                       .setDefensivePact(CFG.game.getProvince(i).getCivID(), CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID(), 5);
                                    CFG.game.setActiveProvinceID(i);
                                    if (CFG.game.getActiveProvinceID() >= 0 && CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID() > 0) {
                                       CFG.MANAGE_DIPLOMACY_CUSTOMIZE_ALLIANCE_ID = CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID();
                                    }

                                    CFG.menuManager.rebuildManageDiplomacy_Defensive();
                                    CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1 = -1;
                                    CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV2 = -1;
                                 } else {
                                    CFG.game.setActiveProvinceID(i);
                                 }
                              } else {
                                 CFG.game.setActiveProvinceID(i);
                              }
                           } else {
                              CFG.game.setActiveProvinceID(i);
                           }
                        } else if (CFG.menuManager.getInManageDiplomacy_Relations_Interactive() && CFG.map.getMapCoordinates().getDisableMovingMap()) {
                           if (CFG.game.getActiveProvinceID() >= 0 && CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID() > 0) {
                              for (int ixxxxxx = 0; ixxxxxx < 1; ixxxxxx++) {
                                 if (nPosX
                                       >= CFG.menuManager.getManageDiplomacy_Relations_Interactive().getMenuElement(ixxxxxx).getPosX()
                                          + CFG.menuManager.getManageDiplomacy_Relations_Interactive().getMenuPosX()
                                    && nPosX
                                       <= CFG.menuManager.getManageDiplomacy_Relations_Interactive().getMenuElement(ixxxxxx).getPosX()
                                          + CFG.menuManager.getManageDiplomacy_Relations_Interactive().getMenuPosX()
                                          + CFG.menuManager.getManageDiplomacy_Relations_Interactive().getMenuElement(ixxxxxx).getWidth()
                                    && nPosY
                                       >= CFG.menuManager.getManageDiplomacy_Relations_Interactive().getMenuElement(ixxxxxx).getPosY()
                                          + CFG.menuManager.getManageDiplomacy_Relations_Interactive().getMenuPosY()
                                    && nPosY
                                       <= CFG.menuManager.getManageDiplomacy_Relations_Interactive().getMenuElement(ixxxxxx).getPosY()
                                          + CFG.menuManager.getManageDiplomacy_Relations_Interactive().getMenuElement(ixxxxxx).getHeight()
                                          + CFG.menuManager.getManageDiplomacy_Relations_Interactive().getMenuPosY()) {
                                    if (CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID != CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()) {
                                       CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID = CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID();
                                       CFG.toast
                                          .setInView(
                                             CFG.langManager.get("CustomizeRelations")
                                                + ": "
                                                + CFG.game.getCiv(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID).getCivName()
                                          );
                                       CFG.toast.setTimeInView(3000);
                                       if (CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID == CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID2) {
                                          CFG.menuManager.getManageDiplomacy_Relations_Interactive().getMenuElement(1).setClickable(false);
                                          CFG.menuManager.getManageDiplomacy_Relations_Interactive().getMenuElement(2).setClickable(false);
                                          CFG.menuManager.getManageDiplomacy_Relations_Interactive().getMenuElement(3).setClickable(false);
                                          CFG.menuManager.getManageDiplomacy_Relations_Interactive().getMenuElement(2).setCurrent(0);
                                          CFG.menuManager.getManageDiplomacy_Relations_Interactive().getMenuElement(4).setClickable(false);
                                          CFG.menuManager.getManageDiplomacy_Relations_Interactive().getMenuElement(5).setClickable(false);
                                          CFG.menuManager.getManageDiplomacy_Relations_Interactive().getMenuElement(6).setClickable(false);
                                          CFG.menuManager.getManageDiplomacy_Relations_Interactive().getMenuElement(5).setCurrent(0);
                                          CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID2 = 0;
                                       }
                                    }

                                    CFG.menuManager
                                       .getManageDiplomacy_Relations_Interactive()
                                       .getMenuElement(ixxxxxx)
                                       .setText(
                                          CFG.langManager.get("CustomizeRelations")
                                             + " ["
                                             + CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).getCivName()
                                             + "]"
                                       );
                                    break;
                                 }
                              }
                           }

                           CFG.menuManager.getDrawCivilization().setVisible(false);
                           CFG.game.setActiveProvinceID(-1);
                        }

                        CFG.menuManager.getDrawCivilization().setVisible(false);
                        CFG.map.getMapCoordinates().setDisableMovingMap(false);
                        return;
                     }

                     CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).setAllianceID(i);
                     CFG.game.getAlliance(i).addCivilization(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID());
                     CFG.game.checkAlliances();
                     CFG.menuManager.rebuildManageDiplomacy_Alliances();
                     CFG.menuManager.getDrawCivilization().setVisible(false);
                     CFG.game.setActiveProvinceID(-1);
                     CFG.map.getMapCoordinates().setDisableMovingMap(false);
                     CFG.MANAGE_DIPLOMACY_DRAW_HELP_LINE = false;
                  }
               }
               : (CFG.menuManager.getInCreateScenario_Assign() ? new Map_TouchManager.ExtraAction() {
                  @Override
                  public void extraAction(int nPosX, int nPosY) {
                     if (CFG.brushTool) {
                        for (int i = 1; i < CFG.game.getCivsSize(); i++) {
                           if (CFG.game.getCiv(i).getCapitalProvinceID() == CFG.game.getActiveProvinceID()) {
                              if (CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID() != CFG.iCreateScenario_AssignProvinces_Civ) {
                                 CFG.setDialogType(Dialog.CREATE_SCENARIO_ASSIGN_CIVILIZATION);
                              }

                              return;
                           }
                        }
                     }
                  }
               } : new Map_TouchManager.ExtraAction() {
                  @Override
                  public void extraAction(int nPosX, int nPosY) {
                  }
               })
         );
   }

   public final void setUpdateStartMovePosX(boolean updateStartMovePosX) {
      this.updateStartMovePosX = updateStartMovePosX;
   }

   public final void setUpdateStartMovePosY(boolean updateStartMovePosY) {
      this.updateStartMovePosY = updateStartMovePosY;
   }

   public final boolean getActionMap() {
      return this.actionMap;
   }

   public final void setActionMap(boolean actionMap) {
      this.actionMap = actionMap;
   }

   public final long getActionDownTime() {
      return this.lActionDownTime;
   }

   public final void setActionDownTime(long lActionDownTime) {
      this.lActionDownTime = lActionDownTime;
   }

   public interface ExtraAction {
      void extraAction(int var1, int var2);
   }

   public interface ReverseDirection {
      int getStartMovePos(int var1);
   }

   public interface ReverseDirection2 {
      int getNewPos(int var1, int var2);
   }
}
