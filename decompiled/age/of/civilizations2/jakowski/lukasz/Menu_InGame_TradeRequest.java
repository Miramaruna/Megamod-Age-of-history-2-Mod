package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

public class Menu_InGame_TradeRequest extends SliderMenu {
   public static int iOnCivID = -1;

   public Menu_InGame_TradeRequest() {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      int tempWidth = CFG.CIV_INFO_MENU_WIDTH * 2;
      int tY = CFG.PADDING;
      menuElements.add(new Button_Flag_JustFrame(CFG.PADDING, tY, true));
      tY += menuElements.get(menuElements.size() - 1).getHeight();
      int tempMenuPosY = ImageManager.getImage(Images.top_left2).getHeight() + CFG.PADDING * 2 + CFG.BUTTON_HEIGHT * 3 / 5;
      this.initMenu(
         new SliderMenuTitle(CFG.langManager.get("TradeRequest"), CFG.BUTTON_HEIGHT * 3 / 5, true, true),
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

   public Menu_InGame_TradeRequest(int onCivID) {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      iOnCivID = onCivID;
      int tempWidth = CFG.CIV_INFO_MENU_WIDTH * 2;
      int tY = 0;
      menuElements.add(
         new Button_Diplomacy_LikelihoodOfSuccess(
            CFG.langManager.get("LikelihoodOfSuccess") + ": ", DiplomacyManager.getTradeRequest_LikelihoodOfSuccess_Text(), "1.0", 2, tY, CFG.BUTTON_WIDTH * 2
         ) {
            @Override
            public int getWidth() {
               return Menu_InGame_TradeRequest.this.getElementW() * 2 - 4;
            }
         }
      );
      tY += menuElements.get(menuElements.size() - 1).getHeight();
      if (CFG.tradeRequest.listLEFT.iGold > 0) {
         menuElements.add(
            new Slider_FlagAction_Clear(
               CFG.langManager.get("Gold"),
               CFG.PADDING * 2,
               tY,
               tempWidth - CFG.PADDING * 3 - CFG.BUTTON_WIDTH,
               CFG.TEXT_HEIGHT + CFG.PADDING * 2 + CFG.PADDING * 4,
               1,
               (int)CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoney(),
               CFG.tradeRequest.listLEFT.iGold,
               0.65F
            ) {
               @Override
               public int getWidth() {
                  return Menu_InGame_TradeRequest.this.getElementW() - CFG.PADDING * 4;
               }

               @Override
               public int getSliderHeight() {
                  return CFG.PADDING * 2;
               }

               @Override
               public Color getColorLEFT() {
                  return new Color(CFG.COLOR_INGAME_GOLD.r, CFG.COLOR_INGAME_GOLD.g, CFG.COLOR_INGAME_GOLD.b, 0.65F);
               }

               @Override
               public void actionElement(int iID) {
                  CFG.tradeRequest.listLEFT.iGold = this.getCurrent();
               }
            }
         );
         tY += menuElements.get(menuElements.size() - 1).getHeight();
      }

      if (CFG.tradeRequest.listLEFT.lProvinces.size() > 0) {
         menuElements.add(
            new Button_Diplomacy_MessageAlliance(
               Images.provinces,
               CFG.langManager.get("Provinces") + ": " + CFG.tradeRequest.listLEFT.lProvinces.size(),
               CFG.tradeRequest.iCivLEFT,
               2,
               tY,
               CFG.BUTTON_WIDTH * 2,
               CFG.TEXT_HEIGHT + CFG.PADDING * 2 + CFG.PADDING * 4
            ) {
               @Override
               public int getWidth() {
                  return Menu_InGame_TradeRequest.this.getElementW() - 2;
               }

               @Override
               public void buildElementHover() {
                  ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
                  ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();

                  for (int i = 0; i < CFG.tradeRequest.listLEFT.lProvinces.size(); i++) {
                     nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getProvince(CFG.tradeRequest.listLEFT.lProvinces.get(i)).getCivID()));
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           CFG.game.getProvince(CFG.tradeRequest.listLEFT.lProvinces.get(i)).getName(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                        )
                     );
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                  }

                  if (nElements.size() == 0) {
                     this.menuElementHover = null;
                  } else {
                     this.menuElementHover = new MenuElement_Hover_v2(nElements);
                  }
               }

               @Override
               public void actionElement(int iID) {
                  CFG.MANAGE_DIPLOMACY_CUSTOMIZE_ALLIANCE_ID = CFG.tradeRequest.iCivLEFT;
                  CFG.game.getPlayer(CFG.PLAYER_TURNID).iACTIVE_VIEW_MODE = CFG.viewsManager.getActiveViewID();
                  CFG.viewsManager.disableAllViews();
                  CFG.game.setActiveProvinceID(-1);
                  Menu_InGame_SelectProvinces.typeOfAction = Menu_InGame_SelectProvinces.TypeOfAction.TRADE_LEFT;
                  CFG.VIEW_SHOW_VALUES = false;
                  CFG.selectMode = true;
                  CFG.game.getSelectedProvinces().clearSelectedProvinces();

                  for (int i = 0; i < CFG.tradeRequest.listLEFT.lProvinces.size(); i++) {
                     CFG.game.getSelectedProvinces().addProvince(CFG.tradeRequest.listLEFT.lProvinces.get(i));
                  }

                  CFG.menuManager.setViewID(Menu.eINGAME_SELECT_PROVINCES);
                  Game_Render_Province.updateDrawProvinces();
               }
            }
         );
         tY += menuElements.get(menuElements.size() - 1).getHeight();
      }

      if (CFG.tradeRequest.listLEFT.iDeclarWarOnCivID > 0) {
         menuElements.add(
            new Button_Diplomacy_MessageAlliance(
               Images.diplo_war,
               CFG.langManager.get("DeclareWar") + ": " + CFG.game.getCiv(CFG.tradeRequest.listLEFT.iDeclarWarOnCivID).getCivName(),
               CFG.tradeRequest.listLEFT.iDeclarWarOnCivID,
               2,
               tY,
               CFG.BUTTON_WIDTH * 2,
               CFG.TEXT_HEIGHT + CFG.PADDING * 2 + CFG.PADDING * 4
            ) {
               @Override
               public int getWidth() {
                  return Menu_InGame_TradeRequest.this.getElementW() - 2;
               }
            }
         );
         tY += menuElements.get(menuElements.size() - 1).getHeight();
      }

      if (CFG.tradeRequest.listLEFT.iFormCoalitionAgainst > 0) {
         menuElements.add(
            new Button_Diplomacy_MessageAlliance(
               Images.diplo_war_preparations,
               CFG.langManager.get("FormACoalitionAgainst") + ": " + CFG.game.getCiv(CFG.tradeRequest.listLEFT.iFormCoalitionAgainst).getCivName(),
               CFG.tradeRequest.listLEFT.iFormCoalitionAgainst,
               2,
               tY,
               CFG.BUTTON_WIDTH * 2,
               CFG.TEXT_HEIGHT + CFG.PADDING * 2 + CFG.PADDING * 4
            ) {
               @Override
               public int getWidth() {
                  return Menu_InGame_TradeRequest.this.getElementW() - 2;
               }
            }
         );
         tY += menuElements.get(menuElements.size() - 1).getHeight();
      }

      if (CFG.tradeRequest.listLEFT.defensivePact) {
         menuElements.add(
            new Button_Statistics(
               CFG.langManager.get("DefensivePact"), CFG.PADDING * 2, 2, tY, CFG.BUTTON_WIDTH * 2, CFG.TEXT_HEIGHT + CFG.PADDING * 2 + CFG.PADDING * 4
            ) {
               @Override
               public int getWidth() {
                  return Menu_InGame_TradeRequest.this.getElementW() - 2;
               }
            }
         );
         tY += menuElements.get(menuElements.size() - 1).getHeight();
      }

      if (CFG.tradeRequest.listLEFT.nonAggressionPact) {
         menuElements.add(
            new Button_Statistics(
               CFG.langManager.get("NonAggressionPact"), CFG.PADDING * 2, 2, tY, CFG.BUTTON_WIDTH * 2, CFG.TEXT_HEIGHT + CFG.PADDING * 2 + CFG.PADDING * 4
            ) {
               @Override
               public int getWidth() {
                  return Menu_InGame_TradeRequest.this.getElementW() - 2;
               }
            }
         );
         tY += menuElements.get(menuElements.size() - 1).getHeight();
      }

      if (CFG.tradeRequest.listLEFT.proclaimIndependence) {
         menuElements.add(
            new Button_Statistics(
               CFG.langManager.get("ProclaimIndependence"), CFG.PADDING * 2, 2, tY, CFG.BUTTON_WIDTH * 2, CFG.TEXT_HEIGHT + CFG.PADDING * 2 + CFG.PADDING * 4
            ) {
               @Override
               public int getWidth() {
                  return Menu_InGame_TradeRequest.this.getElementW() - 2;
               }
            }
         );
         tY += menuElements.get(menuElements.size() - 1).getHeight();
      }

      if (CFG.tradeRequest.listLEFT.militaryAccess) {
         menuElements.add(
            new Button_Statistics(
               CFG.langManager.get("MilitaryAccess"), CFG.PADDING * 2, 2, tY, CFG.BUTTON_WIDTH * 2, CFG.TEXT_HEIGHT + CFG.PADDING * 2 + CFG.PADDING * 4
            ) {
               @Override
               public int getWidth() {
                  return Menu_InGame_TradeRequest.this.getElementW() - 2;
               }
            }
         );
         tY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
      }

      tY = menuElements.get(0).getPosY() + menuElements.get(0).getHeight();
      if (CFG.tradeRequest.listRight.iGold > 0) {
         menuElements.add(
            new Slider_FlagAction_Clear(
               CFG.langManager.get("Gold"),
               CFG.PADDING,
               tY,
               tempWidth - CFG.PADDING * 3 - CFG.BUTTON_WIDTH,
               CFG.TEXT_HEIGHT + CFG.PADDING * 2 + CFG.PADDING * 4,
               1,
               100000,
               CFG.tradeRequest.listRight.iGold,
               0.65F
            ) {
               @Override
               public int getWidth() {
                  return Menu_InGame_TradeRequest.this.getElementW() - CFG.PADDING * 4;
               }

               @Override
               public int getPosX() {
                  return Menu_InGame_TradeRequest.this.getElementW() + CFG.PADDING * 2;
               }

               @Override
               public int getSliderHeight() {
                  return CFG.PADDING * 2;
               }

               @Override
               public Color getColorLEFT() {
                  return new Color(CFG.COLOR_INGAME_GOLD.r, CFG.COLOR_INGAME_GOLD.g, CFG.COLOR_INGAME_GOLD.b, 0.65F);
               }

               @Override
               public void actionElement(int iID) {
                  CFG.tradeRequest.listRight.iGold = this.getCurrent();
               }
            }
         );
         tY += menuElements.get(menuElements.size() - 1).getHeight();
      }

      if (CFG.tradeRequest.listRight.lProvinces.size() > 0) {
         menuElements.add(
            new Button_Diplomacy_MessageAlliance(
               Images.provinces,
               CFG.langManager.get("Provinces") + ": " + CFG.tradeRequest.listRight.lProvinces.size(),
               CFG.tradeRequest.iCivRIGHT,
               2,
               tY,
               CFG.BUTTON_WIDTH * 2,
               CFG.TEXT_HEIGHT + CFG.PADDING * 2 + CFG.PADDING * 4
            ) {
               @Override
               public int getPosX() {
                  return Menu_InGame_TradeRequest.this.getElementW() - 2;
               }

               @Override
               public int getWidth() {
                  return Menu_InGame_TradeRequest.this.getElementW();
               }

               @Override
               public void buildElementHover() {
                  ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
                  ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();

                  for (int i = 0; i < CFG.tradeRequest.listRight.lProvinces.size(); i++) {
                     nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getProvince(CFG.tradeRequest.listRight.lProvinces.get(i)).getCivID()));
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           CFG.game.getProvince(CFG.tradeRequest.listRight.lProvinces.get(i)).getName(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                        )
                     );
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                  }

                  if (nElements.size() == 0) {
                     this.menuElementHover = null;
                  } else {
                     this.menuElementHover = new MenuElement_Hover_v2(nElements);
                  }
               }

               @Override
               public void actionElement(int iID) {
                  CFG.MANAGE_DIPLOMACY_CUSTOMIZE_ALLIANCE_ID = CFG.tradeRequest.iCivRIGHT;
                  CFG.game.getPlayer(CFG.PLAYER_TURNID).iACTIVE_VIEW_MODE = CFG.viewsManager.getActiveViewID();
                  CFG.viewsManager.disableAllViews();
                  CFG.game.setActiveProvinceID(-1);
                  Menu_InGame_SelectProvinces.typeOfAction = Menu_InGame_SelectProvinces.TypeOfAction.TRADE_RIGHT;
                  CFG.VIEW_SHOW_VALUES = false;
                  CFG.selectMode = true;
                  CFG.game.getSelectedProvinces().clearSelectedProvinces();

                  for (int i = 0; i < CFG.tradeRequest.listRight.lProvinces.size(); i++) {
                     CFG.game.getSelectedProvinces().addProvince(CFG.tradeRequest.listRight.lProvinces.get(i));
                  }

                  CFG.menuManager.setViewID(Menu.eINGAME_SELECT_PROVINCES);
                  Game_Render_Province.updateDrawProvinces();
               }
            }
         );
         tY += menuElements.get(menuElements.size() - 1).getHeight();
      }

      if (CFG.tradeRequest.listRight.iDeclarWarOnCivID > 0) {
         menuElements.add(
            new Button_Diplomacy_MessageAlliance(
               Images.diplo_war,
               CFG.langManager.get("DeclareWar") + ": " + CFG.game.getCiv(CFG.tradeRequest.listRight.iDeclarWarOnCivID).getCivName(),
               CFG.tradeRequest.listRight.iDeclarWarOnCivID,
               2,
               tY,
               CFG.BUTTON_WIDTH * 2,
               CFG.TEXT_HEIGHT + CFG.PADDING * 2 + CFG.PADDING * 4
            ) {
               @Override
               public int getPosX() {
                  return Menu_InGame_TradeRequest.this.getElementW() - 2;
               }

               @Override
               public int getWidth() {
                  return Menu_InGame_TradeRequest.this.getElementW();
               }
            }
         );
         tY += menuElements.get(menuElements.size() - 1).getHeight();
      }

      if (CFG.tradeRequest.listRight.iFormCoalitionAgainst > 0) {
         menuElements.add(
            new Button_Diplomacy_MessageAlliance(
               Images.diplo_war_preparations,
               CFG.langManager.get("FormACoalitionAgainst") + ": " + CFG.game.getCiv(CFG.tradeRequest.listRight.iFormCoalitionAgainst).getCivName(),
               CFG.tradeRequest.listRight.iFormCoalitionAgainst,
               2,
               tY,
               CFG.BUTTON_WIDTH * 2,
               CFG.TEXT_HEIGHT + CFG.PADDING * 2 + CFG.PADDING * 4
            ) {
               @Override
               public int getPosX() {
                  return Menu_InGame_TradeRequest.this.getElementW() - 2;
               }

               @Override
               public int getWidth() {
                  return Menu_InGame_TradeRequest.this.getElementW();
               }
            }
         );
         tY += menuElements.get(menuElements.size() - 1).getHeight();
      }

      if (CFG.tradeRequest.listRight.defensivePact) {
         menuElements.add(
            new Button_Statistics(
               CFG.langManager.get("DefensivePact"), CFG.PADDING * 2, 2, tY, CFG.BUTTON_WIDTH * 2, CFG.TEXT_HEIGHT + CFG.PADDING * 2 + CFG.PADDING * 4
            ) {
               @Override
               public int getPosX() {
                  return Menu_InGame_TradeRequest.this.getElementW() - 2;
               }

               @Override
               public int getWidth() {
                  return Menu_InGame_TradeRequest.this.getElementW();
               }
            }
         );
         tY += menuElements.get(menuElements.size() - 1).getHeight();
      }

      if (CFG.tradeRequest.listRight.nonAggressionPact) {
         menuElements.add(
            new Button_Statistics(
               CFG.langManager.get("NonAggressionPact"), CFG.PADDING * 2, 2, tY, CFG.BUTTON_WIDTH * 2, CFG.TEXT_HEIGHT + CFG.PADDING * 2 + CFG.PADDING * 4
            ) {
               @Override
               public int getPosX() {
                  return Menu_InGame_TradeRequest.this.getElementW() - 2;
               }

               @Override
               public int getWidth() {
                  return Menu_InGame_TradeRequest.this.getElementW();
               }
            }
         );
         tY += menuElements.get(menuElements.size() - 1).getHeight();
      }

      if (CFG.tradeRequest.listRight.proclaimIndependence) {
         menuElements.add(
            new Button_Statistics(
               CFG.langManager.get("ProclaimIndependence"), CFG.PADDING * 2, 2, tY, CFG.BUTTON_WIDTH * 2, CFG.TEXT_HEIGHT + CFG.PADDING * 2 + CFG.PADDING * 4
            ) {
               @Override
               public int getPosX() {
                  return Menu_InGame_TradeRequest.this.getElementW() - 2;
               }

               @Override
               public int getWidth() {
                  return Menu_InGame_TradeRequest.this.getElementW();
               }
            }
         );
         tY += menuElements.get(menuElements.size() - 1).getHeight();
      }

      if (CFG.tradeRequest.listRight.militaryAccess) {
         menuElements.add(
            new Button_Statistics(
               CFG.langManager.get("MilitaryAccess"), CFG.PADDING * 2, 2, tY, CFG.BUTTON_WIDTH * 2, CFG.TEXT_HEIGHT + CFG.PADDING * 2 + CFG.PADDING * 4
            ) {
               @Override
               public int getPosX() {
                  return Menu_InGame_TradeRequest.this.getElementW() - 2;
               }

               @Override
               public int getWidth() {
                  return Menu_InGame_TradeRequest.this.getElementW();
               }
            }
         );
         tY += menuElements.get(menuElements.size() - 1).getHeight();
      }

      for (int i = 0; i < menuElements.size(); i++) {
         if (menuElements.get(i).getPosY() + menuElements.get(i).getHeight() > tY) {
            tY = menuElements.get(i).getPosY() + menuElements.get(i).getHeight();
         }
      }

      int var10;
      menuElements.add(
         new Button_FlagActionSliderStyle(CFG.langManager.get("Cancel"), -1, CFG.PADDING, var10 = tY + CFG.PADDING, CFG.BUTTON_WIDTH, true) {
            @Override
            public int getWidth() {
               return Menu_InGame_TradeRequest.this.getElementW() - CFG.PADDING - CFG.PADDING / 2;
            }

            @Override
            public int getPosY() {
               return Menu_InGame_TradeRequest.this.getH() - this.getHeight() - CFG.PADDING > super.getPosY()
                  ? Menu_InGame_TradeRequest.this.getH() - this.getHeight() - CFG.PADDING
                  : super.getPosY();
            }
         }
      );
      menuElements.add(
         new Button_FlagActionSliderStyle(CFG.langManager.get("SendProposal"), -1, 0, var10, CFG.BUTTON_WIDTH, true) {
            @Override
            public int getPosX() {
               return Menu_InGame_TradeRequest.this.getElementW() + CFG.PADDING / 2;
            }

            @Override
            public int getWidth() {
               return Menu_InGame_TradeRequest.this.getElementW() - CFG.PADDING - CFG.PADDING / 2;
            }

            @Override
            public int getPosY() {
               return Menu_InGame_TradeRequest.this.getH() - this.getHeight() - CFG.PADDING > super.getPosY()
                  ? Menu_InGame_TradeRequest.this.getH() - this.getHeight() - CFG.PADDING
                  : super.getPosY();
            }

            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("SendProposal") + ":", CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
               nData.add(new MenuElement_Hover_v2_Element_Type_Flag(Menu_InGame_TradeRequest.iOnCivID, CFG.PADDING, CFG.PADDING));
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.game.getCiv(Menu_InGame_TradeRequest.iOnCivID).getCivName()));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("DiplomacyPoints") + ": ", CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
               nData.add(new MenuElement_Hover_v2_Element_Type_Text("-1.0", CFG.COLOR_TEXT_MODIFIER_NEGATIVE2));
               nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_diplomacy_points, CFG.PADDING, 0));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }

            @Override
            public void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
               ImageManager.getImage(Images.diplo_trade)
                  .draw(
                     oSB,
                     this.getPosX()
                        + this.getWidth() / 2
                        - (int)((this.getTextWidth() * 0.8F + ImageManager.getImage(Images.diplo_trade).getWidth() + CFG.PADDING) / 2.0F)
                        + iTranslateX,
                     this.getPosY() + this.getHeight() / 2 - ImageManager.getImage(Images.diplo_trade).getHeight() / 2 + iTranslateY
                  );
               CFG.fontMain.getData().setScale(0.8F);
               CFG.drawText(
                  oSB,
                  this.getText(),
                  this.getPosX()
                     + (
                        this.getTextPos() < 0
                           ? this.getWidth() / 2
                              - (int)((this.getTextWidth() * 0.8F + ImageManager.getImage(Images.diplo_trade).getWidth() + CFG.PADDING) / 2.0F)
                              + ImageManager.getImage(Images.diplo_trade).getWidth()
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
               return CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getDiplomacyPoints() >= 10 && CFG.tradeRequest.canBeSend();
            }

            @Override
            public int getSFX() {
               return SoundsManager.getSend();
            }
         }
      );
      int tempMenuPosY = ImageManager.getImage(Images.top_left2).getHeight() + CFG.PADDING * 2 + CFG.BUTTON_HEIGHT * 3 / 5;
      this.initMenu(
         new SliderMenuTitle(CFG.langManager.get("TradeRequest"), CFG.BUTTON_HEIGHT * 3 / 5, true, true) {
            @Override
            public void draw(SpriteBatch oSB, int iTranslateX, int nPosX, int nPosY, int nWidth, boolean sliderMenuIsActive) {
               ImageManager.getImage(Images.dialog_title)
                  .draw2(
                     oSB,
                     nPosX + iTranslateX,
                     nPosY - this.getHeight() - ImageManager.getImage(Images.dialog_title).getHeight(),
                     nWidth - ImageManager.getImage(Images.dialog_title).getWidth(),
                     this.getHeight()
                  );
               ImageManager.getImage(Images.dialog_title)
                  .draw2(
                     oSB,
                     nPosX + nWidth - ImageManager.getImage(Images.dialog_title).getWidth() + iTranslateX,
                     nPosY - this.getHeight() - ImageManager.getImage(Images.dialog_title).getHeight(),
                     ImageManager.getImage(Images.dialog_title).getWidth(),
                     this.getHeight(),
                     true,
                     false
                  );
               oSB.setColor(new Color(0.32156864F, 0.7294118F, 0.39607844F, 0.165F));
               ImageManager.getImage(Images.line_32_off1)
                  .draw(
                     oSB,
                     nPosX + 2 + iTranslateX,
                     nPosY - this.getHeight() - ImageManager.getImage(Images.line_32_off1).getHeight(),
                     nWidth - 4,
                     this.getHeight() - 2,
                     false,
                     true
                  );
               oSB.setColor(new Color(0.32156864F, 0.7294118F, 0.39607844F, 0.375F));
               ImageManager.getImage(Images.gradient)
                  .draw(
                     oSB,
                     nPosX + 2 + iTranslateX,
                     nPosY - this.getHeight() * 2 / 3 - ImageManager.getImage(Images.gradient).getHeight(),
                     nWidth - 4,
                     this.getHeight() * 2 / 3,
                     false,
                     true
                  );
               oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.65F));
               ImageManager.getImage(Images.gradient)
                  .draw(
                     oSB,
                     nPosX + 2 + iTranslateX,
                     nPosY - CFG.PADDING - ImageManager.getImage(Images.gradient).getHeight(),
                     nWidth - 4,
                     CFG.PADDING,
                     false,
                     true
                  );
               oSB.setColor(CFG.COLOR_NEW_GAME_EDGE_LINE);
               ImageManager.getImage(Images.pix255_255_255)
                  .draw(oSB, nPosX + 2 + iTranslateX, nPosY - 1 - ImageManager.getImage(Images.pix255_255_255).getHeight(), nWidth - 4, 1);
               oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.55F));
               ImageManager.getImage(Images.line_32_off1)
                  .draw(oSB, nPosX + 2 + iTranslateX, nPosY - 2 - ImageManager.getImage(Images.line_32_off1).getHeight(), nWidth - 4, 1);
               oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.8F));
               ImageManager.getImage(Images.line_32_off1)
                  .draw(oSB, nPosX + 2 + iTranslateX, nPosY - 1 - ImageManager.getImage(Images.line_32_off1).getHeight(), nWidth - 4, 1);
               oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.45F));
               ImageManager.getImage(Images.slider_gradient)
                  .draw(oSB, nPosX + 2 + iTranslateX, nPosY - 1 - ImageManager.getImage(Images.slider_gradient).getHeight(), (nWidth - 4) / 2, 1);
               ImageManager.getImage(Images.slider_gradient)
                  .draw(
                     oSB,
                     nPosX + (nWidth - 2) - (nWidth - 4) / 2 + iTranslateX,
                     nPosY - 1 - ImageManager.getImage(Images.slider_gradient).getHeight(),
                     (nWidth - 4) / 2,
                     1,
                     true,
                     false
                  );
               oSB.setColor(Color.WHITE);

               try {
                  CFG.game
                     .getCiv(CFG.tradeRequest.iCivLEFT)
                     .getFlag()
                     .draw(
                        oSB,
                        Menu_InGame_TradeRequest.this.getPosX() + CFG.PADDING * 2 + iTranslateX,
                        Menu_InGame_TradeRequest.this.getPosY()
                           - this.getHeight() / 2
                           - ImageManager.getImage(Images.flag_rect).getHeight() / 2
                           - CFG.game.getCiv(CFG.tradeRequest.iCivLEFT).getFlag().getHeight(),
                        ImageManager.getImage(Images.flag_rect).getWidth(),
                        ImageManager.getImage(Images.flag_rect).getHeight()
                     );
                  ImageManager.getImage(Images.flag_rect)
                     .draw(
                        oSB,
                        Menu_InGame_TradeRequest.this.getPosX() + CFG.PADDING * 2 + iTranslateX,
                        Menu_InGame_TradeRequest.this.getPosY()
                           - this.getHeight() / 2
                           - ImageManager.getImage(Images.flag_rect).getHeight() / 2
                           - ImageManager.getImage(Images.flag_rect).getHeight(),
                        ImageManager.getImage(Images.flag_rect).getWidth(),
                        ImageManager.getImage(Images.flag_rect).getHeight()
                     );
                  CFG.game
                     .getCiv(CFG.tradeRequest.iCivRIGHT)
                     .getFlag()
                     .draw(
                        oSB,
                        Menu_InGame_TradeRequest.this.getPosX()
                           + Menu_InGame_TradeRequest.this.getWidth()
                           - ImageManager.getImage(Images.flag_rect).getWidth()
                           - CFG.PADDING * 2
                           + iTranslateX,
                        Menu_InGame_TradeRequest.this.getPosY()
                           - this.getHeight() / 2
                           - ImageManager.getImage(Images.flag_rect).getHeight() / 2
                           - CFG.game.getCiv(CFG.tradeRequest.iCivLEFT).getFlag().getHeight(),
                        ImageManager.getImage(Images.flag_rect).getWidth(),
                        ImageManager.getImage(Images.flag_rect).getHeight()
                     );
                  ImageManager.getImage(Images.flag_rect)
                     .draw(
                        oSB,
                        Menu_InGame_TradeRequest.this.getPosX()
                           + Menu_InGame_TradeRequest.this.getWidth()
                           - ImageManager.getImage(Images.flag_rect).getWidth()
                           - CFG.PADDING * 2
                           + iTranslateX,
                        Menu_InGame_TradeRequest.this.getPosY()
                           - this.getHeight() / 2
                           - ImageManager.getImage(Images.flag_rect).getHeight() / 2
                           - ImageManager.getImage(Images.flag_rect).getHeight(),
                        ImageManager.getImage(Images.flag_rect).getWidth(),
                        ImageManager.getImage(Images.flag_rect).getHeight()
                     );
               } catch (IndexOutOfBoundsException var8) {
                  Menu_InGame_TradeRequest.this.setVisible(false);
               }

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
         false
      );
      this.updateLanguage();

      for (int ix = 0; ix < this.getMenuElementsSize(); ix++) {
         if (this.getMenuElement(ix).getCurrent() > 0) {
            this.getMenuElement(ix).setCurrent(this.getMenuElement(ix).getCurrent());
         }
      }

      Menu_InGame_OfferAlliance.lTime = System.currentTimeMillis();
   }

   @Override
   public void updateLanguage() {
   }

   @Override
   public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      oSB.setColor(Color.WHITE);
      ImageManager.getImage(Images.new_game_top_edge)
         .draw2(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.new_game_top_edge).getHeight() + iTranslateY,
            this.getWidth() - ImageManager.getImage(Images.new_game_top_edge).getWidth(),
            this.getHeight() + CFG.PADDING,
            false,
            true
         );
      ImageManager.getImage(Images.new_game_top_edge)
         .draw2(
            oSB,
            this.getPosX() + this.getWidth() - ImageManager.getImage(Images.new_game_top_edge).getWidth() + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.new_game_top_edge).getHeight() + iTranslateY,
            ImageManager.getImage(Images.new_game_top_edge).getWidth(),
            this.getHeight() + CFG.PADDING,
            true,
            true
         );
      oSB.setColor(new Color(CFG.COLOR_GRADIENT_TITLE_BLUE.r, CFG.COLOR_GRADIENT_TITLE_BLUE.g, CFG.COLOR_GRADIENT_TITLE_BLUE.b, 0.75F));
      ImageManager.getImage(Images.line_32_vertical)
         .draw(
            oSB,
            this.getPosX() + this.getWidth() / 2 + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.line_32_vertical).getHeight() + iTranslateY,
            1,
            this.getHeight() - 2
         );
      oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.45F));
      ImageManager.getImage(Images.gradient)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY,
            this.getWidth(),
            this.getHeight() / 4
         );
      ImageManager.getImage(Images.pix255_255_255)
         .draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY, this.getWidth(), 1);
      oSB.setColor(Color.WHITE);
      super.draw(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
   }

   @Override
   public void drawScrollPos(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      if (sliderMenuIsActive) {
         super.drawScrollPos(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
      }
   }

   @Override
   public void onHovered() {
      CFG.menuManager.setOrderOfMenu_InGame_TradeRequest();
   }

   @Override
   public final void actionElement(int iID) {
      if (iID == this.getMenuElementsSize() - 1) {
         DiplomacyManager.sendTradeRequest(iOnCivID, CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), CFG.tradeRequest);
         CFG.menuManager.updateInGame_TOP_All(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
         CFG.toast.setInView(CFG.langManager.get("Sent") + "!", CFG.COLOR_TEXT_MODIFIER_POSITIVE);
         CFG.toast.setTimeInView(4500);
         this.setVisible(false);
      } else if (iID == this.getMenuElementsSize() - 2) {
         this.setVisible(false);
      } else if (iID != 0) {
         this.getMenuElement(iID).actionElement(iID);
      }
   }

   public final int getW() {
      return this.getWidth();
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

         CFG.menuManager.getInGame_SendMessage_TradeLEFT().setVisible(false);
         CFG.menuManager.getInGame_SendMessage_TradeRIGHT().setVisible(false);
      }
   }

   @Override
   public void setPosX(int iPosX) {
      super.setPosX(iPosX);

      try {
         CFG.menuManager.getInGame_SendMessage_TradeLEFT().setPosX(this.getPosX() - CFG.menuManager.getInGame_SendMessage_TradeLEFT().getWidth());
         CFG.menuManager.getInGame_SendMessage_TradeRIGHT().setPosX(this.getPosX() + this.getWidth());
      } catch (NullPointerException var3) {
      }
   }

   @Override
   public void setPosY(int iPosY) {
      super.setPosY(iPosY);

      try {
         CFG.menuManager.getInGame_SendMessage_TradeLEFT().setPosY(this.getPosY());
         CFG.menuManager.getInGame_SendMessage_TradeRIGHT().setPosY(this.getPosY());
      } catch (NullPointerException var3) {
      }
   }

   @Override
   public boolean setWidth(int iWidth) {
      boolean out = super.setWidth(iWidth);

      try {
         CFG.menuManager.getInGame_SendMessage_TradeRIGHT().setPosX(this.getPosX() + this.getWidth());
      } catch (NullPointerException var4) {
      }

      return out;
   }

   @Override
   public void setHeight(int iHeight) {
      super.setHeight(iHeight);

      try {
         CFG.menuManager.getInGame_SendMessage_TradeLEFT().setHeight(this.getHeight());
         CFG.menuManager.getInGame_SendMessage_TradeRIGHT().setHeight(this.getHeight());
         CFG.menuManager.getInGame_SendMessage_TradeLEFT().updateScrollable();
         CFG.menuManager.getInGame_SendMessage_TradeRIGHT().updateScrollable();
         CFG.menuManager.getInGame_SendMessage_TradeLEFT().updateMenuElements_IsInView();
         CFG.menuManager.getInGame_SendMessage_TradeRIGHT().updateMenuElements_IsInView();
      } catch (NullPointerException var3) {
      }
   }

   public final int getH() {
      return this.getHeight();
   }
}
