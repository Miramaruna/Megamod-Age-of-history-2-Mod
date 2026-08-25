package age.of.civilizations2.jakowski.lukasz;

public class Map_Scroll {
   public static final float SCROLL_SLOW = 0.97F;
   public boolean scrollingTheMap = false;
   public int iScrollPosX;
   public int iScrollPosY;
   public int iScrollPosX2 = -1;
   public int iScrollPosY2 = -1;
   public float fScrollNewPosX;
   public float fScrollNewPosY;
   public long moveMapTime = 0L;
   public boolean moveMapDirection = false;
   public int iStepID = 0;
   public int iScrollEvent_PosX;
   public int iScrollEvent_PosY;
   public boolean scrollEvent = false;
   public int iPlayerID = 0;
   public boolean enableBackgroundAnimation = false;
   public Map_Scroll.BackgroundAnimation backgroundAnimation = new Map_Scroll.BackgroundAnimation() {
      @Override
      public void updateBackgroundAnimation() {
      }
   };
   public Map_Scroll.ReverseDirection reverseDirectionX = null;
   public Map_Scroll.ReverseDirection reverseDirectionY = null;

   public final void updateEnableBackroundAnimation() {
      boolean bl = this.enableBackgroundAnimation = !CFG.menuManager.getInGameView()
         && !CFG.menuManager.getInSelectCiv()
         && !CFG.menuManager.getInCreateScenario_Civilizations()
         && !CFG.menuManager.getInCreateScenario_Assign()
         && !CFG.menuManager.getInCreateScenario_Assign_Select()
         && !CFG.menuManager.getInCreateScenario_Civilizations_Select()
         && !CFG.menuManager.getInCreateScenario_WastelandMap()
         && !CFG.menuManager.getInCreateScenario_Available_Provinces()
         && !CFG.menuManager.getInCreateScenario_SetUpArmy()
         && !CFG.menuManager.getInCreateScenario_TechnologyLevels()
         && !CFG.menuManager.getInCreateScenario_Preview()
         && !CFG.menuManager.getInCreateScenario_PalletOfColors()
         && !CFG.menuManager.getInCreateScenario_StartingMoney()
         && !CFG.menuManager.getInCreateScenario_Happiness()
         && !CFG.menuManager.getInMainMenu()
         && !CFG.menuManager.getInAboutMenu()
         && !CFG.menuManager.getInStartGameMenu()
         && !CFG.menuManager.getInEndGameMenu()
         && !CFG.menuManager.getInCreateNewGame()
         && !CFG.menuManager.getInManageDiplomacy()
         && !CFG.menuManager.getInLoadMap()
         && !CFG.menuManager.getInSelectMapType()
         && !CFG.menuManager.getInCustomizeAlliance()
         && !CFG.menuManager.getInSelectAvailableCivilizations()
         && !CFG.menuManager.getInCreateCivilization()
         && !CFG.menuManager.getInCreateCity()
         && !CFG.menuManager.getInGame_PeaceTreaty()
         && !CFG.menuManager.getInGame_PeaceTreaty_Response()
         && !CFG.menuManager.getInMapEditor_Create_NewContinent()
         && !CFG.menuManager.getInGameEditor_Create_DiplomacyPackage()
         && !CFG.menuManager.getInGameEditor_TerrainAdd()
         && !CFG.menuManager.getInGameEditor_ReligionAdd()
         && !CFG.menuManager.getInChooseScenario()
         && !CFG.menuManager.getInSettingsProvince()
         && !CFG.menuManager.getInMapEditor_Terrain()
         && !CFG.menuManager.getInMapEditor_Continents()
         && !CFG.menuManager.getInMapEditor_GrowthRate()
         && !CFG.menuManager.getInMapEditor_ArmyPosition()
         && !CFG.menuManager.getInMapEditor_TradeZones()
         && !CFG.menuManager.getInMapEditor_TradeZones_Edit()
         && !CFG.menuManager.getInMapEditor_WastelandMaps_Edit()
         && !CFG.menuManager.getInMapEditor_ArmySeaBoxes()
         && !CFG.menuManager.getInMapEditor_ArmySeaBoxes_Edit()
         && !CFG.menuManager.getInMapEditor_ArmySeaBoxes_Add()
         && !CFG.menuManager.getInMapEditor_Connections()
         && !CFG.menuManager.getInMapEditor_ProvinceBackground()
         && !CFG.menuManager.getInMapEditor_SeaProvinces()
         && !CFG.menuManager.getInMapEditor_PortPosition()
         && !CFG.menuManager.getInGame_Timeline()
         && !CFG.menuManager.getInGame_ScreenShot()
         && !CFG.menuManager.getInMapEditor_Create_NewRegion()
         && !CFG.menuManager.getInMapEditor_Regions()
         && !CFG.menuManager.getInDownloadPallets()
         && !CFG.menuManager.getInSelectLanguage()
         && !CFG.menuManager.getInMapEditor_LoadSuggestedOwners()
         && !CFG.menuManager.getInMapEditor_LoadPreDefinedBorders()
         && !CFG.menuManager.getInCreateScenario_Cores()
         && !CFG.menuManager.getInPalletOfCivsColorsEdit()
         && !CFG.menuManager.getInCreateScenario_Events_SelectProvinces()
         && !CFG.menuManager.getInGameEditor_Regions()
         && !CFG.menuManager.getInNextPlayerTurn()
         && !CFG.menuManager.getInVictory()
         && !CFG.menuManager.getInGame_CivlizationView()
         && !CFG.menuManager.getInPrintAMap()
         && !CFG.menuManager.getInRandomGame()
         && !CFG.menuManager.getInRandomGame_Civilizations_Select()
         && !CFG.menuManager.getCreateScenario_ScenarioAge()
         && !CFG.menuManager.getInCreateScenario_HolyRomanEmpire()
         && !CFG.menuManager.getInMapEditor_FormableCivs_Edit()
         && !CFG.menuManager.getInGame_CreateAVassal()
         && !CFG.menuManager.getInGame_SelectProvinces()
         && !CFG.menuManager.getInGame_ShowProvinces()
         && !CFG.menuManager.getInGame_TradeSelectCiv()
         && !CFG.menuManager.getInMapEditor_FormableCivs_SelectFormable()
         && !CFG.menuManager.getInMapEditor_FormableCivs_SelectClaimant()
         && !CFG.menuManager.getInGame_Formable_Civ_Provinces()
         && !CFG.menuManager.getInGame_FormAnimation();
      this.backgroundAnimation = this.enableBackgroundAnimation ? (CFG.menuManager.getInNewGamePlayers() ? new Map_Scroll.BackgroundAnimation() {
         @Override
         public void updateBackgroundAnimation() {
            if (!CFG.map.getMapTouchManager().getActionMap() && CFG.game.getPlayersSize() > 1 && CFG.menuManager.getInNewGamePlayers()) {
               try {
                  if (CFG.game.getPlayer(Map_Scroll.this.iPlayerID).getCivID() < 0) {
                     Map_Scroll.this.iPlayerID++;
                     if (Map_Scroll.this.iPlayerID >= CFG.game.getPlayersSize()) {
                        Map_Scroll.this.iPlayerID = 0;
                     }

                     return;
                  }
               } catch (IndexOutOfBoundsException var3) {
                  Map_Scroll.this.iPlayerID = 0;
                  return;
               }

               if (Map_Scroll.this.moveMapTime <= System.currentTimeMillis() - 2500L) {
                  try {
                     Map_Scroll.this.setScrollEvent(CFG.game.getCiv(CFG.game.getPlayer(Map_Scroll.this.iPlayerID).getCivID()).getCapitalProvinceID());
                     Map_Scroll.this.iPlayerID++;
                  } catch (IndexOutOfBoundsException var2) {
                     Map_Scroll.this.iPlayerID = 0;
                     Map_Scroll.this.setScrollEvent(CFG.game.getCiv(CFG.game.getPlayer(Map_Scroll.this.iPlayerID).getCivID()).getCapitalProvinceID());
                  }

                  if (Map_Scroll.this.iPlayerID >= CFG.game.getPlayersSize()) {
                     Map_Scroll.this.iPlayerID = 0;
                  }
               }
            }
         }
      } : new Map_Scroll.BackgroundAnimation() {
         @Override
         public void updateBackgroundAnimation() {
            if (!CFG.map.getMapTouchManager().getActionMap() && Map_Scroll.this.moveMapTime <= System.currentTimeMillis() - 85L) {
               CFG.map.getMapCoordinates().setNewPosX(CFG.map.getMapCoordinates().getPosX() + (int)((Map_Scroll.this.moveMapDirection ? 1 : -1) * CFG.DENSITY));
               Map_Scroll.this.moveMapTime = System.currentTimeMillis();
            }
         }
      }) : new Map_Scroll.BackgroundAnimation() {
         @Override
         public void updateBackgroundAnimation() {
         }
      };
   }

   public Map_Scroll() {
      this.buildReverseDirectionX();
      this.buildReverseDirectionY();
   }

   public final void update() {
      if (this.scrollEvent) {
         if (this.iStepID < 14) {
            CFG.map.getMapCoordinates().setNewPosX(CFG.map.getMapCoordinates().getPosX() - (int)changeAnimationPos(this.iStepID, this.iScrollEvent_PosX));
            CFG.map.getMapCoordinates().setNewPosY(CFG.map.getMapCoordinates().getPosY() - (int)changeAnimationPos(this.iStepID++, this.iScrollEvent_PosY));
            if (this.iStepID == 14) {
               this.moveMapTime = System.currentTimeMillis();
               this.scrollEvent = false;
            }
         }
      } else if (this.scrollingTheMap && !CFG.map.getMapCoordinates().getDisableMovingMap()) {
         if (!(Math.abs(this.fScrollNewPosX) > 1.0F) && !(Math.abs(this.fScrollNewPosY) > 1.0F)) {
            this.stopScrollingTheMap();
         } else {
            if (Math.abs(this.fScrollNewPosX) > 1.0F) {
               CFG.map.getMapCoordinates().setNewPosX(this.reverseDirectionX.getNewPos((int)this.fScrollNewPosX));
               this.fScrollNewPosX *= 0.97F;
            }

            if (Math.abs(this.fScrollNewPosY) > 1.0F) {
               CFG.map.getMapCoordinates().setNewPosY(this.reverseDirectionY.getNewPos((int)this.fScrollNewPosY));
               this.fScrollNewPosY *= 0.97F;
            }
         }
      } else {
         this.backgroundAnimation.updateBackgroundAnimation();
      }
   }

   public static final float changeAnimationPos(int animationStepID, int nWidth) {
      switch (animationStepID) {
         case 0:
         case 1:
         case 12:
         case 13:
            return nWidth * 2.5F / 100.0F;
         case 2:
         case 3:
         case 10:
         case 11:
            return nWidth * 5.0F / 100.0F;
         case 4:
         case 5:
         case 8:
         case 9:
            return nWidth * 10.0F / 100.0F;
         case 6:
         case 7:
            return nWidth * 15.0F / 100.0F;
         default:
            return 0.0F;
      }
   }

   public final void startScrollingTheMap() {
      if (this.iScrollPosX2 >= 0 || this.iScrollPosY2 >= 0) {
         float f = Math.abs(this.iScrollPosX - this.iScrollPosX2);
         float f2 = CFG.isDesktop() ? CFG.PADDING * 1.5F : 4.0F;
         if (f > f2 * CFG.DENSITY) {
            this.fScrollNewPosX = (this.iScrollPosX - this.iScrollPosX2) * 1.25F * (CFG.reverseDirectionX ? 1 : -1);
            this.scrollingTheMap = true;
         }

         float f3 = Math.abs(this.iScrollPosY - this.iScrollPosY2);
         float f4 = CFG.isDesktop() ? CFG.PADDING * 1.5F : 4.0F;
         if (f3 > f4 * CFG.DENSITY) {
            this.fScrollNewPosY = (this.iScrollPosY - this.iScrollPosY2) * 1.25F * (CFG.reverseDirectionY ? 1 : -1);
            this.scrollingTheMap = true;
         }
      }

      if (this.iScrollPosX != this.iScrollPosX2) {
         this.updateMoveMapDirection(this.iScrollPosX > this.iScrollPosX2);
      }

      this.resetScrollInfo();
   }

   public final void stopScrollingTheMap() {
      this.scrollingTheMap = false;
      this.resetScrollInfo();
      this.scrollEvent = false;
   }

   public final void updateMoveMapDirection(boolean moveMapDirection) {
      this.moveMapDirection = moveMapDirection;
      this.moveMapTime = 0L;
   }

   public final void resetScrollInfo() {
      this.iScrollPosY2 = -1;
      this.iScrollPosX2 = -1;
      this.iScrollPosY = -1;
      this.iScrollPosX = -1;
   }

   public final void setScrollEvent(int nProvinceID) {
      this.setScrollEvent_Pos(
         (int)(
            CFG.map.getMapCoordinates().getPosX() + CFG.game.getProvince(nProvinceID).getCenterX()
               - CFG.GAME_WIDTH / CFG.map.getMapScale().getCurrentScale() / 2.0F
         ),
         (int)(
            CFG.map.getMapCoordinates().getPosY() + CFG.game.getProvince(nProvinceID).getCenterY()
               - CFG.GAME_HEIGHT / CFG.map.getMapScale().getCurrentScale() / 2.0F
         )
      );
   }

   public final void setScrollEvent_ToPosition(int nPosX, int nPosY) {
      this.setScrollEvent_Pos(
         (int)(CFG.map.getMapCoordinates().getPosX() + nPosX - CFG.GAME_WIDTH / CFG.map.getMapScale().getCurrentScale() / 2.0F),
         (int)(CFG.map.getMapCoordinates().getPosY() + nPosY - CFG.GAME_HEIGHT / CFG.map.getMapScale().getCurrentScale() / 2.0F)
      );
   }

   public final void setScrollEvent_Pos(int nPosX, int nPosY) {
      if (!this.scrollEvent) {
         this.scrollEvent = true;
         this.iStepID = 0;
         this.iScrollEvent_PosX = nPosX;
         this.iScrollEvent_PosY = nPosY;
         this.moveMapTime = System.currentTimeMillis() + 208L;
      }
   }

   public final void buildReverseDirectionX() {
      this.reverseDirectionX = CFG.reverseDirectionX ? new Map_Scroll.ReverseDirection() {
         @Override
         public int getNewPos(int nPosX) {
            return CFG.map.getMapCoordinates().getNewPosX() + nPosX;
         }
      } : new Map_Scroll.ReverseDirection() {
         @Override
         public int getNewPos(int nPosX) {
            return CFG.map.getMapCoordinates().getNewPosX() - nPosX;
         }
      };
   }

   public final void buildReverseDirectionY() {
      this.reverseDirectionY = CFG.reverseDirectionY ? new Map_Scroll.ReverseDirection() {
         @Override
         public int getNewPos(int nPosY) {
            return CFG.map.getMapCoordinates().getNewPosY() + nPosY;
         }
      } : new Map_Scroll.ReverseDirection() {
         @Override
         public int getNewPos(int nPosY) {
            return CFG.map.getMapCoordinates().getNewPosY() - nPosY;
         }
      };
   }

   public final void setScrollPos(int nPosX, int nPosY) {
      this.iScrollPosX2 = this.iScrollPosX;
      this.iScrollPosY2 = this.iScrollPosY;
      this.iScrollPosX = nPosX;
      this.iScrollPosY = nPosY;
   }

   public boolean getScrollingTheMap() {
      return this.scrollingTheMap;
   }

   interface BackgroundAnimation {
      void updateBackgroundAnimation();
   }

   public interface ReverseDirection {
      int getNewPos(int var1);
   }
}
