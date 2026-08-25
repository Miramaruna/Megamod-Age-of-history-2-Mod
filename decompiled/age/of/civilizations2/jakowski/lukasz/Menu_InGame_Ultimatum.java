package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.utils.ScissorStack;
import java.util.ArrayList;

public class Menu_InGame_Ultimatum extends SliderMenu {
   public int iOnCivID = -1;

   public Menu_InGame_Ultimatum() {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      int tempWidth = CFG.CIV_INFO_MENU_WIDTH * 2;
      int tY = CFG.PADDING;
      menuElements.add(new Button_Flag_JustFrame(CFG.PADDING, tY, true));
      tY += menuElements.get(menuElements.size() - 1).getHeight();
      int tempMenuPosY = ImageManager.getImage(Images.top_left2).getHeight() + CFG.PADDING * 2 + CFG.BUTTON_HEIGHT * 3 / 5;
      this.initMenu(
         new SliderMenuTitle(CFG.langManager.get("Ultimatum"), CFG.BUTTON_HEIGHT * 3 / 5, true, true),
         CFG.GAME_WIDTH / 2 - tempWidth / 2,
         tempMenuPosY,
         tempWidth,
         menuElements.get(menuElements.size() - 1).getPosY() + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING + tempMenuPosY
               > CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING * 2
            ? Math.max(CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING * 2 - tempMenuPosY, (CFG.TEXT_HEIGHT + CFG.PADDING * 2) * 6)
            : menuElements.get(menuElements.size() - 1).getPosY() + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING,
         menuElements,
         false,
         true
      );
      this.updateLanguage();
   }

   public Menu_InGame_Ultimatum(final int onCivID) {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      this.iOnCivID = onCivID;
      int tempWidth = CFG.CIV_INFO_MENU_WIDTH * 2;
      int tY = 0;
      menuElements.add(new Button_Diplomacy_War_Cost(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), CFG.getActiveCivInfo(), 2, tY, CFG.BUTTON_WIDTH * 2) {
         @Override
         public int getWidth() {
            return Menu_InGame_Ultimatum.this.getElementW() * 2;
         }
      });
      int var6;
      menuElements.add(
         new Button_Diplomacy_Demand(
            CFG.langManager.get("DemandAnnexation"),
            CFG.getActiveCivInfo(),
            2,
            var6 = tY + menuElements.get(menuElements.size() - 1).getHeight(),
            CFG.BUTTON_WIDTH * 2
         ) {
            @Override
            public int getWidth() {
               return Menu_InGame_Ultimatum.this.getElementW() * 2;
            }

            @Override
            public void actionElement(int iID) {
               CFG.ultimatum.demandAnexation = !CFG.ultimatum.demandAnexation;
               CFG.ultimatum.demandProvinces.clear();
               if (CFG.ultimatum.demandAnexation) {
                  CFG.ultimatum.demandVasalization = false;
               }
            }

            @Override
            public boolean getCheckboxState() {
               return CFG.ultimatum.demandAnexation;
            }

            @Override
            public boolean getClickable() {
               return true;
            }

            @Override
            public void buildElementHover() {
               this.menuElementHover = null;
            }
         }
      );
      tY = var6 + menuElements.get(menuElements.size() - 1).getHeight();
      if (CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID() != CFG.game.getCiv(onCivID).getPuppetOfCivID()) {
         menuElements.add(new Button_Diplomacy_Demand(CFG.langManager.get("DemandVassalization"), CFG.getActiveCivInfo(), 2, tY, CFG.BUTTON_WIDTH * 2) {
            @Override
            public int getWidth() {
               return Menu_InGame_Ultimatum.this.getElementW() * 2;
            }

            @Override
            public void actionElement(int iID) {
               CFG.ultimatum.demandVasalization = !CFG.ultimatum.demandVasalization;
               CFG.ultimatum.demandProvinces.clear();
               if (CFG.ultimatum.demandVasalization) {
                  CFG.ultimatum.demandAnexation = false;
               }
            }

            @Override
            public boolean getCheckboxState() {
               return CFG.ultimatum.demandVasalization;
            }
         });
         tY += menuElements.get(menuElements.size() - 1).getHeight();
      }

      if (CFG.ideologiesManager.getIdeology(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getIdeologyID())
         != CFG.ideologiesManager.getIdeology(CFG.game.getCiv(this.iOnCivID).getIdeologyID())) {
         menuElements.add(new Button_Diplomacy_Demand(CFG.langManager.get("DemandChangeGovernment"), CFG.getActiveCivInfo(), 2, tY, CFG.BUTTON_WIDTH * 2) {
            @Override
            public int getWidth() {
               return Menu_InGame_Ultimatum.this.getElementW() * 2;
            }

            @Override
            public void actionElement(int iID) {
               CFG.ultimatum.demandChangeGoverment = !CFG.ultimatum.demandChangeGoverment;
               CFG.ultimatum.demandProvinces.clear();
               if (CFG.ultimatum.demandChangeGoverment) {
                  CFG.ultimatum.demandAnexation = false;
               }
            }

            @Override
            public boolean getCheckboxState() {
               return CFG.ultimatum.demandChangeGoverment;
            }
         });
         tY += menuElements.get(menuElements.size() - 1).getHeight();
      }

      menuElements.add(new Button_Diplomacy_Demand(CFG.langManager.get("DemandProvinces") + ": ", CFG.getActiveCivInfo(), 2, tY, CFG.BUTTON_WIDTH * 2) {
         @Override
         public int getWidth() {
            return Menu_InGame_Ultimatum.this.getElementW() * 2;
         }

         @Override
         public void actionElement(int iID) {
            CFG.ultimatum.demandAnexation = false;
            CFG.ultimatum.demandVasalization = false;
            if (CFG.ultimatum.demandProvinces.size() == 0) {
               CFG.MANAGE_DIPLOMACY_CUSTOMIZE_ALLIANCE_ID = onCivID;
               CFG.game.getPlayer(CFG.PLAYER_TURNID).iACTIVE_VIEW_MODE = CFG.viewsManager.getActiveViewID();
               CFG.viewsManager.disableAllViews();
               CFG.game.setActiveProvinceID(-1);
               Menu_InGame_SelectProvinces.typeOfAction = Menu_InGame_SelectProvinces.TypeOfAction.ULTIMATUM;
               CFG.VIEW_SHOW_VALUES = false;
               CFG.selectMode = true;
               CFG.game.getSelectedProvinces().clearSelectedProvinces();
               CFG.menuManager.setViewID(Menu.eINGAME_SELECT_PROVINCES);
               Game_Render_Province.updateDrawProvinces();
            } else {
               CFG.MANAGE_DIPLOMACY_CUSTOMIZE_ALLIANCE_ID = onCivID;
               CFG.game.getPlayer(CFG.PLAYER_TURNID).iACTIVE_VIEW_MODE = CFG.viewsManager.getActiveViewID();
               CFG.viewsManager.disableAllViews();
               CFG.game.setActiveProvinceID(-1);
               Menu_InGame_SelectProvinces.typeOfAction = Menu_InGame_SelectProvinces.TypeOfAction.ULTIMATUM;
               CFG.VIEW_SHOW_VALUES = false;
               CFG.selectMode = true;
               CFG.game.getSelectedProvinces().clearSelectedProvinces();

               for (int i = 0; i < CFG.ultimatum.demandProvinces.size(); i++) {
                  CFG.game.getSelectedProvinces().addProvince(CFG.ultimatum.demandProvinces.get(i));
               }

               CFG.menuManager.setViewID(Menu.eINGAME_SELECT_PROVINCES);
               Game_Render_Province.updateDrawProvinces();
            }
         }

         @Override
         public String getText() {
            return super.getText() + CFG.ultimatum.demandProvinces.size();
         }

         @Override
         public boolean getCheckboxState() {
            return CFG.ultimatum.demandProvinces.size() > 0;
         }

         @Override
         public void buildElementHover() {
            ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
            ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
            if (CFG.ultimatum.demandProvinces.size() > 0) {
               nData.add(new MenuElement_Hover_v2_Element_Type_Flag(onCivID));
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("DemandProvinces"), CFG.COLOR_TEXT_NUM_OF_PROVINCES));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
            }

            for (int i = 0; i < CFG.ultimatum.demandProvinces.size(); i++) {
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(" - ", CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.game.getProvince(CFG.ultimatum.demandProvinces.get(i)).getName()));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
            }

            if (nElements.size() == 0) {
               this.menuElementHover = null;
            } else {
               this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
         }
      });
      int var8;
      menuElements.add(
         new Button_Diplomacy_Demand(
            CFG.langManager.get("DemandMilitaryAccess"),
            CFG.getActiveCivInfo(),
            2,
            var8 = tY + menuElements.get(menuElements.size() - 1).getHeight(),
            CFG.BUTTON_WIDTH * 2
         ) {
            @Override
            public int getWidth() {
               return Menu_InGame_Ultimatum.this.getElementW() * 2;
            }

            @Override
            public void actionElement(int iID) {
               CFG.ultimatum.demandMilitaryAccess = !CFG.ultimatum.demandMilitaryAccess;
            }

            @Override
            public boolean getCheckboxState() {
               return CFG.ultimatum.demandMilitaryAccess;
            }
         }
      );
      tY = var8 + menuElements.get(menuElements.size() - 1).getHeight();

      for (int i = 0; i < CFG.game.getCiv(onCivID).civGameData.lVassals.size(); i++) {
         menuElements.add(
            new Button_Diplomacy_Demand(
               CFG.langManager.get("DemandLiberationOfVassal")
                  + ": "
                  + CFG.game.getCiv(CFG.game.getCiv(onCivID).civGameData.lVassals.get(i).iCivID).getCivName(),
               CFG.game.getCiv(onCivID).civGameData.lVassals.get(i).iCivID,
               2,
               tY,
               CFG.BUTTON_WIDTH * 2
            ) {
               @Override
               public int getWidth() {
                  return Menu_InGame_Ultimatum.this.getElementW() * 2;
               }

               @Override
               public void actionElement(int iID) {
                  CFG.ultimatum.updateLiberationDemand(this.getCurrent());
               }

               @Override
               public boolean getCheckboxState() {
                  return CFG.ultimatum.isLiberationDemanded(this.getCurrent());
               }
            }
         );
         tY += menuElements.get(menuElements.size() - 1).getHeight();
      }

      CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).buildNumOfUnits();
      menuElements.add(
         new Slider_CrossTheBorder(
            CFG.langManager.get("OrXUnitsWillInvade", 0),
            CFG.PADDING * 2,
            menuElements.get(menuElements.size() - 1).getPosY() + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING,
            tempWidth - CFG.PADDING * 3 - CFG.BUTTON_WIDTH,
            CFG.TEXT_HEIGHT + CFG.PADDING * 2 + CFG.PADDING * 4,
            0,
            CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getNumOfUnits(),
            0,
            0.65F
         ) {
            @Override
            public int getWidth() {
               return Menu_InGame_Ultimatum.this.getElementW() * 2 - CFG.PADDING * 4;
            }

            @Override
            public int getSliderHeight() {
               return CFG.PADDING * 2;
            }

            @Override
            public Color getColorLEFT() {
               return new Color(
                  CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_AT_WAR.getR(),
                  CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_AT_WAR.getG(),
                  CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_AT_WAR.getB(),
                  0.65F
               );
            }
         }
      );
      tY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
      int var11;
      menuElements.add(new Button_FlagActionSliderStyle(CFG.langManager.get("Cancel"), -1, 2 + CFG.PADDING, var11 = tY + CFG.PADDING, CFG.BUTTON_WIDTH, true) {
         @Override
         public int getWidth() {
            return Menu_InGame_Ultimatum.this.getElementW() - CFG.PADDING - CFG.PADDING / 2;
         }
      });
      menuElements.add(
         new Button_FlagActionSliderStyle(CFG.langManager.get("SendUltimatum"), -1, 2, var11, CFG.BUTTON_WIDTH, true) {
            @Override
            public int getPosX() {
               return Menu_InGame_Ultimatum.this.getElementW() + CFG.PADDING / 2;
            }

            @Override
            public int getWidth() {
               return Menu_InGame_Ultimatum.this.getElementW() - CFG.PADDING - CFG.PADDING / 2;
            }

            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               if (Game_Calendar.TURN_ID > Game_Calendar.PeaceAfterGameStarts) {
                  nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("SendUltimatum") + ":", CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                  nData.add(new MenuElement_Hover_v2_Element_Type_Flag(Menu_InGame_Ultimatum.this.iOnCivID, CFG.PADDING, CFG.PADDING));
                  nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.game.getCiv(Menu_InGame_Ultimatum.this.iOnCivID).getCivName()));
                  nElements.add(new MenuElement_Hover_v2_Element2(nData));
                  nData.clear();
                  if (CFG.ultimatum.canBeSend()) {
                     nData.add(new MenuElement_Hover_v2_Element_Type_Space());
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                     if (CFG.ultimatum.demandAnexation) {
                        nData.add(new MenuElement_Hover_v2_Element_Type_Flag(onCivID));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("DemandAnnexation"), CFG.COLOR_TEXT_NUM_OF_PROVINCES));
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                     }

                     if (CFG.ultimatum.demandVasalization) {
                        nData.add(new MenuElement_Hover_v2_Element_Type_Flag(onCivID));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("DemandVassalization"), CFG.COLOR_TEXT_NUM_OF_PROVINCES));
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                     }

                     if (CFG.ultimatum.demandProvinces.size() > 0) {
                        nData.add(new MenuElement_Hover_v2_Element_Type_Flag(onCivID));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("DemandProvinces"), CFG.COLOR_TEXT_NUM_OF_PROVINCES));
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                     }

                     for (int i = 0; i < CFG.ultimatum.demandProvinces.size(); i++) {
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(" - ", CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.game.getProvince(CFG.ultimatum.demandProvinces.get(i)).getName()));
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                     }

                     nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()));
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           CFG.langManager
                              .get(
                                 "OrXUnitsWillInvade",
                                 Menu_InGame_Ultimatum.this.getMenuElement(Menu_InGame_Ultimatum.this.getMenuElementsSize() - 3).getCurrent()
                              ),
                           CFG.COLOR_TEXT_MODIFIER_NEGATIVE2
                        )
                     );
                     nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.diplo_army, CFG.PADDING, 0));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                  }
               } else {
                  nData.add(
                     new MenuElement_Hover_v2_Element_Type_Text(
                        CFG.langManager.get("AWarCantBeDeclaredInFirstXTurns", Game_Calendar.PeaceAfterGameStarts) + ".", CFG.COLOR_TEXT_MODIFIER_NEGATIVE2
                     )
                  );
                  nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.diplo_war, CFG.PADDING, 0));
                  nElements.add(new MenuElement_Hover_v2_Element2(nData));
                  nData.clear();
               }

               this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }

            @Override
            public void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
               ImageManager.getImage(Images.diplo_rivals)
                  .draw(
                     oSB,
                     this.getPosX()
                        + this.getWidth() / 2
                        - (int)((this.getTextWidth() * 0.8F + ImageManager.getImage(Images.diplo_rivals).getWidth() + CFG.PADDING) / 2.0F)
                        + iTranslateX,
                     this.getPosY() + this.getHeight() / 2 - ImageManager.getImage(Images.diplo_rivals).getHeight() / 2 + iTranslateY
                  );
               CFG.fontMain.getData().setScale(0.8F);
               CFG.drawText(
                  oSB,
                  this.getText(),
                  this.getPosX()
                     + (
                        this.getTextPos() < 0
                           ? this.getWidth() / 2
                              - (int)((this.getTextWidth() * 0.8F + ImageManager.getImage(Images.diplo_rivals).getWidth() + CFG.PADDING) / 2.0F)
                              + ImageManager.getImage(Images.diplo_rivals).getWidth()
                              + CFG.PADDING
                           : this.getTextPos()
                     )
                     + iTranslateX,
                  this.getPosY() + this.getHeight() / 2 - (int)(this.getTextHeight() * 0.8F / 2.0F) + iTranslateY,
                  this.getColor(isActive)
               );
               CFG.fontMain.getData().setScale(1.0F);
            }

            @Override
            public boolean getClickable() {
               return CFG.ultimatum.canBeSend()
                  && Menu_InGame_Ultimatum.this.getMenuElement(Menu_InGame_Ultimatum.this.getMenuElementsSize() - 3).getCurrent() > 0
                  && CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getDiplomacyPoints() >= 24;
            }

            @Override
            public int getSFX() {
               return this.getClickable() ? SoundsManager.SOUND_WAR : super.getSFX();
            }
         }
      );
      int tempMenuPosY = ImageManager.getImage(Images.top_left2).getHeight() + CFG.PADDING * 2 + CFG.BUTTON_HEIGHT * 3 / 5;
      this.initMenu(
         new SliderMenuTitle(CFG.langManager.get("Ultimatum"), CFG.BUTTON_HEIGHT * 3 / 5, true, true) {
            @Override
            public void draw(SpriteBatch oSB, int iTranslateX, int nPosX, int nPosY, int nWidth, boolean sliderMenuIsActive) {
               ImageManager.getImage(Images.dialog_title)
                  .draw2(
                     oSB,
                     nPosX - 2 + iTranslateX,
                     nPosY - this.getHeight() - ImageManager.getImage(Images.dialog_title).getHeight(),
                     nWidth + 4 - ImageManager.getImage(Images.dialog_title).getWidth(),
                     this.getHeight()
                  );
               ImageManager.getImage(Images.dialog_title)
                  .draw2(
                     oSB,
                     nPosX + nWidth + 2 - ImageManager.getImage(Images.dialog_title).getWidth() + iTranslateX,
                     nPosY - this.getHeight() - ImageManager.getImage(Images.dialog_title).getHeight(),
                     ImageManager.getImage(Images.dialog_title).getWidth(),
                     this.getHeight(),
                     true,
                     false
                  );
               oSB.setColor(
                  new Color(
                     CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_AT_WAR.getR(),
                     CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_AT_WAR.getG(),
                     CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_AT_WAR.getB(),
                     0.165F
                  )
               );
               ImageManager.getImage(Images.line_32_off1)
                  .draw(
                     oSB,
                     nPosX + iTranslateX,
                     nPosY - this.getHeight() + 2 - ImageManager.getImage(Images.line_32_off1).getHeight(),
                     nWidth,
                     this.getHeight() - 2,
                     false,
                     true
                  );
               oSB.setColor(
                  new Color(
                     CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_AT_WAR.getR(),
                     CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_AT_WAR.getG(),
                     CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_AT_WAR.getB(),
                     0.375F
                  )
               );
               ImageManager.getImage(Images.gradient)
                  .draw(
                     oSB,
                     nPosX + iTranslateX,
                     nPosY - this.getHeight() * 2 / 3 - ImageManager.getImage(Images.gradient).getHeight(),
                     nWidth,
                     this.getHeight() * 2 / 3,
                     false,
                     true
                  );
               oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.65F));
               ImageManager.getImage(Images.gradient)
                  .draw(oSB, nPosX + iTranslateX, nPosY - CFG.PADDING - ImageManager.getImage(Images.gradient).getHeight(), nWidth, CFG.PADDING, false, true);
               oSB.setColor(CFG.COLOR_NEW_GAME_EDGE_LINE);
               ImageManager.getImage(Images.pix255_255_255)
                  .draw(oSB, nPosX + iTranslateX, nPosY - 1 - ImageManager.getImage(Images.pix255_255_255).getHeight(), nWidth, 1);
               oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.55F));
               ImageManager.getImage(Images.line_32_off1)
                  .draw(oSB, nPosX + iTranslateX, nPosY - 2 - ImageManager.getImage(Images.line_32_off1).getHeight(), nWidth, 1);
               oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.8F));
               ImageManager.getImage(Images.line_32_off1)
                  .draw(oSB, nPosX + iTranslateX, nPosY - 1 - ImageManager.getImage(Images.line_32_off1).getHeight(), nWidth, 1);
               oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.45F));
               ImageManager.getImage(Images.slider_gradient)
                  .draw(oSB, nPosX + iTranslateX, nPosY - 1 - ImageManager.getImage(Images.slider_gradient).getHeight(), nWidth / 2, 1);
               ImageManager.getImage(Images.slider_gradient)
                  .draw(
                     oSB,
                     nPosX + nWidth - nWidth / 2 + iTranslateX,
                     nPosY - 1 - ImageManager.getImage(Images.slider_gradient).getHeight(),
                     nWidth / 2,
                     1,
                     true,
                     false
                  );
               oSB.setColor(Color.WHITE);
               CFG.fontMain.getData().setScale(0.8F);
               CFG.drawText(
                  oSB,
                  this.getText(),
                  nPosX + (int)(nWidth - this.getTextWidth() * 0.8F) / 2 + iTranslateX,
                  2 + nPosY - this.getHeight() + (int)(this.getHeight() - this.getTextHeight() * 0.8F) / 2,
                  Color.WHITE
               );
               CFG.fontMain.getData().setScale(1.0F);
            }
         },
         CFG.GAME_WIDTH / 2 - tempWidth / 2,
         tempMenuPosY,
         tempWidth,
         menuElements.get(menuElements.size() - 1).getPosY() + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING + tempMenuPosY
               > CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING * 2
            ? Math.max(CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING * 2 - tempMenuPosY, (CFG.TEXT_HEIGHT + CFG.PADDING * 2) * 6)
            : menuElements.get(menuElements.size() - 1).getPosY() + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING,
         menuElements,
         true,
         true
      );
      this.updateLanguage();
      Menu_InGame_OfferAlliance.lTime = System.currentTimeMillis();
   }

   @Override
   public void updateLanguage() {
   }

   @Override
   public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      if (Menu_InGame_OfferAlliance.lTime + 200L >= System.currentTimeMillis()) {
         Rectangle clipBounds = new Rectangle(
            this.getPosX() - 2,
            CFG.GAME_HEIGHT - this.getPosY(),
            this.getWidth() + 4,
            -((int)((this.getHeight() + CFG.PADDING) * ((float)(System.currentTimeMillis() - Menu_InGame_OfferAlliance.lTime) / 200.0F)))
         );
         oSB.flush();
         ScissorStack.pushScissors(clipBounds);
         oSB.setColor(Color.WHITE);
         ImageManager.getImage(Images.new_game_top_edge)
            .draw2(
               oSB,
               this.getPosX() - 2 + iTranslateX,
               this.getPosY() - ImageManager.getImage(Images.new_game_top_edge).getHeight() + iTranslateY,
               this.getWidth() - ImageManager.getImage(Images.new_game_top_edge).getWidth() + 4,
               this.getHeight() + CFG.PADDING,
               false,
               true
            );
         ImageManager.getImage(Images.new_game_top_edge)
            .draw2(
               oSB,
               this.getPosX() + 2 + this.getWidth() - ImageManager.getImage(Images.new_game_top_edge).getWidth() + iTranslateX,
               this.getPosY() - ImageManager.getImage(Images.new_game_top_edge).getHeight() + iTranslateY,
               ImageManager.getImage(Images.new_game_top_edge).getWidth(),
               this.getHeight() + CFG.PADDING,
               true,
               true
            );
         oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.45F));
         ImageManager.getImage(Images.gradient)
            .draw(
               oSB,
               this.getPosX() + 2 + iTranslateX,
               this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY,
               this.getWidth() - 4,
               this.getHeight() / 4
            );
         ImageManager.getImage(Images.pix255_255_255)
            .draw(
               oSB,
               this.getPosX() + 2 + iTranslateX,
               this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY,
               this.getWidth() - 4,
               1
            );
         oSB.setColor(Color.WHITE);
         this.drawMenu(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
         oSB.setColor(Color.WHITE);
         CFG.setRender_3(true);
         this.endClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
      } else {
         oSB.setColor(Color.WHITE);
         ImageManager.getImage(Images.new_game_top_edge)
            .draw2(
               oSB,
               this.getPosX() - 2 + iTranslateX,
               this.getPosY() - ImageManager.getImage(Images.new_game_top_edge).getHeight() + iTranslateY,
               this.getWidth() - ImageManager.getImage(Images.new_game_top_edge).getWidth() + 4,
               this.getHeight() + CFG.PADDING,
               false,
               true
            );
         ImageManager.getImage(Images.new_game_top_edge)
            .draw2(
               oSB,
               this.getPosX() + 2 + this.getWidth() - ImageManager.getImage(Images.new_game_top_edge).getWidth() + iTranslateX,
               this.getPosY() - ImageManager.getImage(Images.new_game_top_edge).getHeight() + iTranslateY,
               ImageManager.getImage(Images.new_game_top_edge).getWidth(),
               this.getHeight() + CFG.PADDING,
               true,
               true
            );
         oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.45F));
         ImageManager.getImage(Images.gradient)
            .draw(
               oSB,
               this.getPosX() + 2 + iTranslateX,
               this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY,
               this.getWidth() - 4,
               this.getHeight() / 4
            );
         ImageManager.getImage(Images.pix255_255_255)
            .draw(
               oSB,
               this.getPosX() + 2 + iTranslateX,
               this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY,
               this.getWidth() - 4,
               1
            );
         oSB.setColor(Color.WHITE);
         this.beginClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
         this.drawMenu(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
         oSB.setColor(Color.WHITE);
         this.endClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
      }
   }

   @Override
   public void drawScrollPos(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      if (sliderMenuIsActive) {
         super.drawScrollPos(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
      }
   }

   @Override
   public final void actionElement(int iID) {
      if (iID == this.getMenuElementsSize() - 1) {
         if (DiplomacyManager.sendUltimatum(
            this.iOnCivID, CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), CFG.ultimatum, this.getMenuElement(this.getMenuElementsSize() - 3).getCurrent()
         )) {
            CFG.menuManager.updateInGame_TOP_All(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
            CFG.toast.setInView(CFG.langManager.get("Sent") + "!", CFG.COLOR_TEXT_MODIFIER_POSITIVE);
            CFG.toast.setTimeInView(4500);
         }

         this.setVisible(false);
      } else if (iID == this.getMenuElementsSize() - 2) {
         this.setVisible(false);
      } else {
         this.getMenuElement(iID).actionElement(iID);
      }
   }

   public final int getW() {
      return this.getWidth() - 4;
   }

   public final int getElementW() {
      return this.getW() / 2;
   }

   @Override
   public void setVisible(boolean visible) {
      super.setVisible(visible);
      if (!visible) {
         for (int i = 0; i < this.getMenuElementsSize(); i++) {
            this.getMenuElement(i).setVisible(false);
         }
      }
   }
}
