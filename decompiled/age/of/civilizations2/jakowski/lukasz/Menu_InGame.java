package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

public class Menu_InGame extends SliderMenu {
   public static float fTurnScale = 0.8F;
   public static boolean BUDGET_OVER = false;
   public static int iTopBalance = 0;
   public static final int TIME_REQUIRED_TO_CONTINUE = 6;
   public static long TIME_CONTINUE;
   public static final float FONT_SIZE_BALANCE = 0.85F;

   public static final void updateOverBudget() {
      iTopBalance = CFG.game_NextTurnUpdate.getBalance(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
      BUDGET_OVER = iTopBalance < 0;
      CFG.menuManager.getInGame().getMenuElement(1).setCurrent(iTopBalance);
   }

   public Menu_InGame() {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      if (CFG.XXXXHDPI) {
         CFG.topBox.iFlagX = 10;
         CFG.topBox.iFlagY = 11;
         CFG.topBox.iCircleShift = -36;
      } else if (CFG.XXXHDPI) {
         CFG.topBox.iFlagX = 14;
         CFG.topBox.iFlagY = 12;
         CFG.topBox.iCircleShift = -24;
      } else if (CFG.XXHDPI) {
         CFG.topBox.iFlagX = 14;
         CFG.topBox.iFlagY = 12;
         CFG.topBox.iCircleShift = -24;
      } else if (CFG.XHDPI) {
         CFG.topBox.iFlagX = 9;
         CFG.topBox.iFlagY = 9;
         CFG.topBox.iCircleShift = -22;
      } else {
         CFG.topBox.iFlagX = 7;
         CFG.topBox.iFlagY = 7;
         CFG.topBox.iCircleShift = -18;
         CFG.topBox.leftExtraViewPadding = 10;
      }

      menuElements.add(new Minimap(0, 0) {
         @Override
         public int getPosY() {
            return CFG.GAME_HEIGHT - CFG.map.getMapBG().getMinimapHeight();
         }
      });
      menuElements.add(
         new Text(
            "0",
            0,
            CFG.topBox.iFlagX * 2 + ImageManager.getImage(Images.top_flag_frame).getWidth() + CFG.PADDING,
            0,
            ImageManager.getImage(Images.top_left2).getHeight()
         ) {
            String sBalance = "";
            int iBalanceWidth = 0;
            Color cBalance = Color.WHITE;

            @Override
            public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
               if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoney() < 0L) {
                  oSB.setColor(new Color(1.0F, 1.0F, 1.0F, 0.9F));
                  ImageManager.getImage(Images.top_gold2)
                     .draw(
                        oSB,
                        this.getPosX() + iTranslateX,
                        this.getPosY() + this.getHeight() / 2 - ImageManager.getImage(Images.top_gold2).getHeight() / 2 + iTranslateY
                     );
                  oSB.setColor(Color.WHITE);
               } else {
                  ImageManager.getImage(Images.top_gold)
                     .draw(
                        oSB,
                        this.getPosX() + iTranslateX,
                        this.getPosY() + this.getHeight() / 2 - ImageManager.getImage(Images.top_gold).getHeight() / 2 + iTranslateY
                     );
               }

               if (Menu_InGame.BUDGET_OVER) {
                  oSB.setColor(new Color(1.0F, 1.0F, 1.0F, 0.9F));
                  ImageManager.getImage(Images.top_gold2)
                     .draw(
                        oSB,
                        this.getPosX()
                           + this.getTextWidth()
                           + ImageManager.getImage(Images.top_gold).getWidth()
                           + this.textPosition.getTextPosition()
                           + CFG.PADDING * 2
                           + iTranslateX,
                        this.getPosY() + this.getHeight() / 2 - ImageManager.getImage(Images.top_gold2).getHeight() / 2 + iTranslateY
                     );
                  oSB.setColor(Color.WHITE);
                  ImageManager.getImage(Images.ar_down)
                     .draw(
                        oSB,
                        this.getPosX()
                           + this.getTextWidth()
                           + ImageManager.getImage(Images.top_gold).getWidth()
                           + this.textPosition.getTextPosition()
                           + CFG.PADDING * 2
                           + ImageManager.getImage(Images.top_gold2).getWidth()
                           - ImageManager.getImage(Images.ar_down).getWidth()
                           + CFG.PADDING / 4
                           + iTranslateX,
                        this.getPosY()
                           + this.getHeight() / 2
                           - ImageManager.getImage(Images.top_gold2).getHeight() / 2
                           + ImageManager.getImage(Images.top_gold2).getHeight()
                           - ImageManager.getImage(Images.ar_down).getHeight()
                           + CFG.PADDING / 4
                           + iTranslateY
                     );
               } else {
                  ImageManager.getImage(Images.top_gold)
                     .draw(
                        oSB,
                        this.getPosX()
                           + this.getTextWidth()
                           + ImageManager.getImage(Images.top_gold).getWidth()
                           + this.textPosition.getTextPosition()
                           + CFG.PADDING * 2
                           + iTranslateX,
                        this.getPosY() + this.getHeight() / 2 - ImageManager.getImage(Images.top_gold).getHeight() / 2 + iTranslateY
                     );
                  ImageManager.getImage(Images.ar_up)
                     .draw(
                        oSB,
                        this.getPosX()
                           + this.getTextWidth()
                           + ImageManager.getImage(Images.top_gold).getWidth()
                           + this.textPosition.getTextPosition()
                           + CFG.PADDING * 2
                           + ImageManager.getImage(Images.top_gold).getWidth()
                           - ImageManager.getImage(Images.ar_up).getWidth()
                           + CFG.PADDING / 4
                           + iTranslateX,
                        this.getPosY()
                           + this.getHeight() / 2
                           - ImageManager.getImage(Images.top_gold).getHeight() / 2
                           + ImageManager.getImage(Images.top_gold).getHeight()
                           - ImageManager.getImage(Images.ar_up).getHeight()
                           + CFG.PADDING / 4
                           + iTranslateY
                     );
               }

               int var6;
               super.draw(oSB, var6 = iTranslateX + ImageManager.getImage(Images.top_gold).getWidth() + CFG.PADDING, iTranslateY, isActive, scrollableY);
               CFG.fontMain.getData().setScale(0.85F);
               CFG.drawTextWithShadow(
                  oSB,
                  this.sBalance,
                  this.getPosX()
                     + this.textPosition.getTextPosition()
                     + CFG.PADDING
                     + ImageManager.getImage(Images.top_gold).getWidth()
                     + this.getTextWidth()
                     + CFG.PADDING
                     + var6,
                  this.getPosY() + (int)((this.getHeight() - CFG.TEXT_HEIGHT * 0.85F) / 2.0F) + iTranslateY,
                  this.cBalance
               );
               CFG.fontMain.getData().setScale(1.0F);
            }

            @Override
            public void setCurrent(int nCurrent) {
               try {
                  this.sBalance = (nCurrent > 0 ? "+" : "") + CFG.getNumberWithSpaces("" + nCurrent);
                  this.cBalance = nCurrent > 0
                     ? CFG.COLOR_TEXT_MODIFIER_POSITIVE
                     : (nCurrent == 0 ? CFG.COLOR_TEXT_MODIFIER_NEUTRAL : CFG.COLOR_TEXT_MODIFIER_NEGATIVE_HOVER);
                  CFG.glyphLayout.setText(CFG.fontMain, this.sBalance);
                  this.iBalanceWidth = (int)(CFG.glyphLayout.width * 0.85F);
               } catch (NullPointerException var3) {
                  this.sBalance = "";
                  this.iBalanceWidth = 0;
                  CFG.exceptionStack(var3);
               }
            }

            @Override
            public int getWidth() {
               return super.getWidth()
                  + ImageManager.getImage(Images.top_gold).getWidth()
                  + CFG.PADDING
                  + ImageManager.getImage(Images.top_gold).getWidth()
                  + CFG.PADDING
                  + this.iBalanceWidth
                  + CFG.PADDING;
            }

            @Override
            public Color getColor(boolean isActive) {
               return CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoney() <= 0L
                  ? CFG.COLOR_TEXT_MODIFIER_NEGATIVE2
                  : (isActive ? CFG.COLOR_INGAME_GOLD_ACTIVE : (this.getIsHovered() ? CFG.COLOR_INGAME_GOLD_HOVER : CFG.COLOR_INGAME_GOLD));
            }

            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Treasury") + ": ", CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
               nData.add(
                  new MenuElement_Hover_v2_Element_Type_Text(
                     "" + CFG.getNumberWithSpaces("" + CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoney()), CFG.COLOR_INGAME_GOLD
                  )
               );
               nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_gold, CFG.PADDING, 0));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               if ((int)CFG.game_NextTurnUpdate.getInflation(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) > 0) {
                  nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Inflation") + ": "));
                  nData.add(
                     new MenuElement_Hover_v2_Element_Type_Text(
                        "" + CFG.getNumberWithSpaces("" + (int)CFG.game_NextTurnUpdate.getInflation(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())),
                        (int)CFG.game_NextTurnUpdate.getInflation(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) > 0
                           ? CFG.COLOR_TEXT_MODIFIER_NEGATIVE2
                           : CFG.COLOR_TEXT_MODIFIER_NEUTRAL2
                     )
                  );
                  nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_gold, CFG.PADDING, CFG.PADDING));
                  nData.add(
                     new MenuElement_Hover_v2_Element_Type_Text(
                        "[" + (int)(CFG.game_NextTurnUpdate.getInflationPerc(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) * 10000.0F) / 100.0F + "%]",
                        CFG.COLOR_TEXT_MODIFIER_NEUTRAL
                     )
                  );
                  nElements.add(new MenuElement_Hover_v2_Element2(nData));
                  nData.clear();
               }

               nData.add(new MenuElement_Hover_v2_Element_Type_Space());
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               int tempValue;
               int tempBalance = tempValue = (int)CFG.game_NextTurnUpdate.getIncome(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Income") + ": "));
               nData.add(
                  new MenuElement_Hover_v2_Element_Type_Text(
                     "" + CFG.getNumberWithSpaces("" + tempValue), tempValue > 0 ? CFG.COLOR_TEXT_MODIFIER_POSITIVE : CFG.COLOR_TEXT_MODIFIER_NEUTRAL
                  )
               );
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               tempValue = (int)CFG.game_NextTurnUpdate.getExpenses(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Expenses") + ": "));
               nData.add(
                  new MenuElement_Hover_v2_Element_Type_Text(
                     "" + CFG.getNumberWithSpaces("" + tempValue), tempValue > 0 ? CFG.COLOR_TEXT_MODIFIER_NEGATIVE2 : CFG.COLOR_TEXT_MODIFIER_NEUTRAL
                  )
               );
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Balance") + ": "));
               int var6;
               nData.add(
                  new MenuElement_Hover_v2_Element_Type_Text(
                     ((var6 = tempBalance - tempValue) > 0 ? "+" : "") + CFG.getNumberWithSpaces("" + var6),
                     var6 > 0 ? CFG.COLOR_TEXT_MODIFIER_POSITIVE : (var6 < 0 ? CFG.COLOR_TEXT_MODIFIER_NEGATIVE2 : CFG.COLOR_TEXT_MODIFIER_NEUTRAL)
                  )
               );
               nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_gold, CFG.PADDING, 0));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }

            @Override
            public void drawMenuElementHover2(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
               if (this.menuElementHover != null) {
                  this.menuElementHover.drawAlwaysBelow(oSB, Touch.getMousePosX(), ImageManager.getImage(Images.top_left2).getHeight());
               }
            }

            @Override
            public int getSFX() {
               return CFG.menuManager.getVisible_InGame_Budget() ? SoundsManager.SOUND_CLICK2 : SoundsManager.SOUND_GOLD;
            }
         }
      );
      menuElements.add(new Text("", 0, 0));
      menuElements.add(new Text("", 0, 0));
      menuElements.add(new Text("", 0, 0));
      menuElements.add(new Text("", 0, 0));
      menuElements.add(
         new Button_Rank(
            "1",
            CFG.topBox.iFlagX + ImageManager.getImage(Images.top_flag_frame).getWidth() + CFG.topBox.iCircleShift,
            CFG.topBox.iFlagY
               + ImageManager.getImage(Images.top_flag_frame).getHeight()
               + ImageManager.getImage(Images.top_civ_color).getHeight()
               + CFG.topBox.iCircleShift
         ) {
            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("CivRank") + ": "));
               nData.add(
                  new MenuElement_Hover_v2_Element_Type_Text(
                     "" + CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getRankPosition() + "/" + CFG.game.getCivsSize(),
                     CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                  )
               );
               nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.rank, CFG.PADDING, 0));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               if (CFG.isDesktop()) {
                  nData.add(new MenuElement_Hover_v2_Element_Type_Space());
                  nElements.add(new MenuElement_Hover_v2_Element2(nData));
                  nData.clear();
                  nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Shortcut") + ": "));
                  nData.add(new MenuElement_Hover_v2_Element_Type_Text("F9", CFG.COLOR_TEXT_NUM_OF_PROVINCES));
                  nElements.add(new MenuElement_Hover_v2_Element2(nData));
                  nData.clear();
               }

               this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }

            @Override
            public void drawMenuElementHover2(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
               if (this.menuElementHover != null) {
                  this.menuElementHover.drawAlwaysBelow(oSB, Touch.getMousePosX(), ImageManager.getImage(Images.top_left).getHeight());
               }
            }
         }
      );
      menuElements.add(
         new Button_Flag_JustFrame(CFG.topBox.iFlagX, CFG.topBox.iFlagY, true) {
            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()));
               nData.add(
                  new MenuElement_Hover_v2_Element_Type_Text(
                     CFG.menuManager.getVisible_InGame_FlagAction()
                        ? CFG.langManager.get("CloseCivilizationView")
                        : CFG.langManager.get("OpenCivilizationView"),
                     CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                  )
               );
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("CivRank") + ": "));
               nData.add(
                  new MenuElement_Hover_v2_Element_Type_Text(
                     "" + CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getRankPosition() + "/" + CFG.game.getCivsSize(),
                     CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                  )
               );
               nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.rank, CFG.PADDING, 0));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               if (CFG.isDesktop()) {
                  nData.add(new MenuElement_Hover_v2_Element_Type_Space());
                  nElements.add(new MenuElement_Hover_v2_Element2(nData));
                  nData.clear();
                  nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Shortcut") + ": "));
                  nData.add(new MenuElement_Hover_v2_Element_Type_Text("F1", CFG.COLOR_TEXT_NUM_OF_PROVINCES));
                  nElements.add(new MenuElement_Hover_v2_Element2(nData));
                  nData.clear();
               }

               this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }

            @Override
            public void drawMenuElementHover2(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
               if (this.menuElementHover != null) {
                  this.menuElementHover.drawAlwaysBelow(oSB, Touch.getMousePosX(), ImageManager.getImage(Images.top_left).getHeight());
               }
            }
         }
      );
      menuElements.add(
         new Text(null, -1, 0, 0, ImageManager.getImage(Images.top_left2).getHeight()) {
            @Override
            public int getPosX() {
               return Menu_InGame.this.getMenuElement(10).getVisible()
                  ? Menu_InGame.this.getMenuElement(10).getPosX() + Menu_InGame.this.getMenuElement(10).getWidth()
                  : Menu_InGame.this.getMenuElement(CFG.FREEPLAY_MODE ? 32 : 16).getPosX()
                     + Menu_InGame.this.getMenuElement(CFG.FREEPLAY_MODE ? 32 : 16).getWidth();
            }

            @Override
            public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
               CFG.fontMain.getData().setScale(0.6F);
               CFG.drawText(
                  oSB,
                  this.sText,
                  this.getPosX() + CFG.PADDING + ImageManager.getImage(Images.top_left2).getWidth() + iTranslateX,
                  this.getPosY() + (int)(this.getHeight() - this.getTextHeight() * 0.6F) / 2 + iTranslateY,
                  this.getColor(isActive)
               );
               CFG.fontMain.getData().setScale(1.0F);
            }

            @Override
            public int getWidth() {
               return (int)(this.getTextWidth() * 0.6F) + CFG.PADDING * 2 + ImageManager.getImage(Images.top_left2).getWidth();
            }

            @Override
            public int getSFX() {
               return CFG.viewsManager.getActiveViewID() == ViewsManager.VIEW_DIPLOMACY_MODE ? super.getSFX() : SoundsManager.SOUND_DIPLOMACY;
            }

            @Override
            public void buildElementHover() {
               if (CFG.isDesktop()) {
                  ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
                  ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
                  nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Shortcut") + ": "));
                  nData.add(new MenuElement_Hover_v2_Element_Type_Text("F3", CFG.COLOR_TEXT_NUM_OF_PROVINCES));
                  nElements.add(new MenuElement_Hover_v2_Element2(nData));
                  nData.clear();
                  this.menuElementHover = new MenuElement_Hover_v2(nElements);
               } else {
                  this.menuElementHover = null;
               }
            }

            @Override
            public void drawMenuElementHover2(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
               if (this.menuElementHover != null) {
                  this.menuElementHover.drawAlwaysBelow(oSB, Touch.getMousePosX(), ImageManager.getImage(Images.top_left2).getHeight());
               }
            }
         }
      );
      menuElements.add(
         new Text(null, -1, 0, 0, ImageManager.getImage(Images.top_left2).getHeight()) {
            @Override
            public int getPosX() {
               return Menu_InGame.this.getMenuElement(8).getPosX() + Menu_InGame.this.getMenuElement(8).getWidth();
            }

            @Override
            public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
               CFG.fontMain.getData().setScale(0.6F);
               CFG.drawText(
                  oSB,
                  this.sText,
                  this.getPosX() + CFG.PADDING + ImageManager.getImage(Images.top_left2).getWidth() + iTranslateX,
                  this.getPosY() + (int)(this.getHeight() - this.getTextHeight() * 0.6F) / 2 + iTranslateY,
                  this.getColor(isActive)
               );
               CFG.fontMain.getData().setScale(1.0F);
            }

            @Override
            public int getWidth() {
               return (int)(this.getTextWidth() * 0.6F) + CFG.PADDING * 2 + CFG.PADDING + ImageManager.getImage(Images.top_left2).getWidth();
            }

            @Override
            public int getSFX() {
               return SoundsManager.SOUND_CLICK2;
            }

            @Override
            public void buildElementHover() {
               if (CFG.isDesktop()) {
                  ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
                  ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
                  nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Shortcut") + ": "));
                  nData.add(new MenuElement_Hover_v2_Element_Type_Text("F4", CFG.COLOR_TEXT_NUM_OF_PROVINCES));
                  nElements.add(new MenuElement_Hover_v2_Element2(nData));
                  nData.clear();
                  this.menuElementHover = new MenuElement_Hover_v2(nElements);
               } else {
                  this.menuElementHover = null;
               }
            }

            @Override
            public void drawMenuElementHover2(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
               if (this.menuElementHover != null) {
                  this.menuElementHover.drawAlwaysBelow(oSB, Touch.getMousePosX(), ImageManager.getImage(Images.top_left2).getHeight());
               }
            }
         }
      );
      menuElements.add(
         new Text(
            "",
            0,
            CFG.topBox.iFlagX * 2 + ImageManager.getImage(Images.top_flag_frame).getWidth() + CFG.PADDING,
            0,
            ImageManager.getImage(Images.top_left2).getHeight()
         ) {
            @Override
            public Color getColor(boolean isActive) {
               return isActive ? CFG.COLOR_TEXT_OPTIONS_NS_HOVER : (this.getIsHovered() ? CFG.COLOR_TEXT_OPTIONS_NS : Color.WHITE);
            }

            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()));
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(this.getText(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Player") + ": "));
               nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + (CFG.PLAYER_TURNID + 1), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }

            @Override
            public void drawMenuElementHover2(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
               if (this.menuElementHover != null) {
                  this.menuElementHover.drawAlwaysBelow(oSB, Touch.getMousePosX(), ImageManager.getImage(Images.top_left2).getHeight());
               }
            }
         }
      );
      menuElements.get(menuElements.size() - 1).setVisible(false);
      menuElements.add(new Text("", 0, 0));
      menuElements.add(
         new Button_Speed_Right(
            "+", -1, 0, 0, ImageManager.getImage(Images.top_left2).getHeight() + CFG.PADDING, ImageManager.getImage(Images.top_left2).getHeight() - 2, true
         ) {
            @Override
            public int getPosX() {
               return CFG.GAME_WIDTH - this.getWidth();
            }

            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("IncreaseSpeed"), CFG.COLOR_TEXT_NUM_OF_PROVINCES));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               if (CFG.isDesktop()) {
                  nData.add(new MenuElement_Hover_v2_Element_Type_Space());
                  nElements.add(new MenuElement_Hover_v2_Element2(nData));
                  nData.clear();
                  nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Shortcut") + ": "));
                  nData.add(new MenuElement_Hover_v2_Element_Type_Text("+", CFG.COLOR_TEXT_NUM_OF_PROVINCES));
                  nElements.add(new MenuElement_Hover_v2_Element2(nData));
                  nData.clear();
               }

               this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }

            @Override
            public void drawMenuElementHover2(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
               if (this.menuElementHover != null) {
                  this.menuElementHover.drawAlwaysBelow(oSB, Touch.getMousePosX(), ImageManager.getImage(Images.top_left2).getHeight());
               }
            }

            @Override
            public boolean getVisible() {
               return RTS.isEnabled();
            }
         }
      );
      menuElements.add(new Text("", 0, 0));
      menuElements.add(new Text("", 0, 0));
      menuElements.add(new Text("", 0, 0));
      menuElements.add(new Text("", 0, 0));
      menuElements.add(new Text("", 0, 0));
      menuElements.add(new Text("", 0, 0));
      menuElements.add(new Text("", 0, 0));
      menuElements.add(new Text("", 0, 0));
      menuElements.add(new Text("", 0, 0));
      menuElements.add(new Text("", 0, 0));
      menuElements.add(new Text("", 0, 0));
      menuElements.add(new Text("", 0, 0));
      menuElements.add(new Text("", 0, 0));
      menuElements.add(new Text("", 0, 0));
      menuElements.add(new Text("", 0, 0));
      menuElements.add(new Text("", 0, 0));
      menuElements.add(new Text("", 0, 0));
      menuElements.add(new Text("", 0, 0));
      menuElements.add(new Text("", 0, 0));
      menuElements.add(new Text("", 0, 0));
      this.initMenu(null, 0, 0, CFG.GAME_WIDTH, CFG.GAME_HEIGHT, menuElements);
      this.setMenuElement(
         2,
         new Text("3.2", 0, 0, 0, ImageManager.getImage(Images.top_left2).getHeight()) {
            @Override
            public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
               ImageManager.getImage(Images.top_movement_points)
                  .draw(
                     oSB,
                     this.getPosX() + iTranslateX,
                     this.getPosY() + this.getHeight() / 2 - ImageManager.getImage(Images.top_movement_points).getHeight() / 2 + iTranslateY
                  );
               int var6;
               super.draw(
                  oSB, var6 = iTranslateX + ImageManager.getImage(Images.top_movement_points).getWidth() + CFG.PADDING, iTranslateY, isActive, scrollableY
               );
            }

            @Override
            public int getPosX() {
               return CFG.menuManager.getInGame().getMenuElement(1).getPosX() + CFG.menuManager.getInGame().getMenuElement(1).getWidth() + CFG.PADDING;
            }

            @Override
            public int getWidth() {
               return super.getWidth() + ImageManager.getImage(Images.top_movement_points).getWidth() + CFG.PADDING;
            }

            @Override
            public Color getColor(boolean isActive) {
               return CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMovePoints()
                     < CFG.ideologiesManager.getIdeology(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getIdeologyID()).COST_OF_MOVE
                  ? CFG.COLOR_TEXT_MODIFIER_NEGATIVE2
                  : (isActive ? CFG.COLOR_INGAME_MOVEMENT_ACTIVE : (this.getIsHovered() ? CFG.COLOR_INGAME_MOVEMENT_HOVER : CFG.COLOR_INGAME_MOVEMENT));
            }

            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("MovementPoints") + ": ", CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
               nData.add(
                  new MenuElement_Hover_v2_Element_Type_Text(
                     "" + CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMovePoints() / 10.0F, CFG.COLOR_INGAME_MOVEMENT
                  )
               );
               nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_movement_points, CFG.PADDING, 0));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               nData.add(new MenuElement_Hover_v2_Element_Type_Space());
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("BaseValue") + ": "));
               nData.add(
                  new MenuElement_Hover_v2_Element_Type_Text(
                     "+" + CFG.gameAction.getMovementPoints_BaseValue(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) / 10.0F,
                     CFG.COLOR_TEXT_MODIFIER_POSITIVE
                  )
               );
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("CivilizationSize") + ": "));
               nData.add(
                  new MenuElement_Hover_v2_Element_Type_Text(
                     ""
                        + (CFG.gameAction.getMovementPoints_FromCivSize(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) == 0 ? "" : "+")
                        + CFG.gameAction.getMovementPoints_FromCivSize(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) / 10.0F,
                     CFG.gameAction.getMovementPoints_FromCivSize(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) == 0
                        ? CFG.COLOR_TEXT_MODIFIER_NEUTRAL
                        : CFG.COLOR_TEXT_MODIFIER_POSITIVE
                  )
               );
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Technology") + ": "));
               nData.add(
                  new MenuElement_Hover_v2_Element_Type_Text(
                     ""
                        + (CFG.gameAction.getMovementPoints_FromTechnology(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) == 0 ? "" : "+")
                        + CFG.gameAction.getMovementPoints_FromTechnology(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) / 10.0F,
                     CFG.gameAction.getMovementPoints_FromTechnology(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) == 0
                        ? CFG.COLOR_TEXT_MODIFIER_NEUTRAL
                        : CFG.COLOR_TEXT_MODIFIER_POSITIVE
                  )
               );
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }

            @Override
            public void drawMenuElementHover2(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
               if (this.menuElementHover != null) {
                  this.menuElementHover.drawAlwaysBelow(oSB, Touch.getMousePosX(), ImageManager.getImage(Images.top_left2).getHeight());
               }
            }

            @Override
            public int getSFX() {
               return SoundsManager.SOUND_MOVE_ARMY;
            }
         }
      );
      this.setMenuElement(
         13,
         new Text("ERROR", 0, 0, 0, ImageManager.getImage(Images.top_left2).getHeight()) {
            @Override
            public void draw(SpriteBatch param1SpriteBatch, int param1Int1, int param1Int2, boolean param1Boolean1, boolean param1Boolean2) {
               if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getNuclearWeapons() > 0) {
                  ImageManager.getImage(Images.nuclear_icon)
                     .draw(
                        param1SpriteBatch,
                        this.getPosX() + param1Int1,
                        this.getPosY() + this.getHeight() / 2 - ImageManager.getImage(Images.nuclear_icon).getHeight() / 2 + param1Int2
                     );
                  super.draw(
                     param1SpriteBatch,
                     param1Int1 + ImageManager.getImage(Images.nuclear_icon).getWidth() + CFG.PADDING,
                     param1Int2,
                     param1Boolean1,
                     param1Boolean2
                  );
               }
            }

            @Override
            public int getPosX() {
               try {
                  return CFG.menuManager.getInGame().getMenuElement(3).getPosX() + CFG.menuManager.getInGame().getMenuElement(3).getWidth() + CFG.PADDING;
               } catch (NullPointerException var2) {
                  return 0;
               }
            }

            @Override
            public int getWidth() {
               return CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getNuclearWeapons() > 0
                  ? super.getWidth() + ImageManager.getImage(Images.nuclear_icon).getWidth() + CFG.PADDING
                  : 0;
            }

            @Override
            public Color getColor(boolean isActive) {
               return Color.CORAL;
            }

            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               nData.add(
                  new MenuElement_Hover_v2_Element_Type_Text(
                     CFG.langManager.get("Warheads") + ": " + CFG.game.getCiv(CFG.PLAYER_TURNID).getNuclearWeapons(), CFG.COLOR_TEXT_MODIFIER_NEUTRAL
                  )
               );
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }

            @Override
            public int getSFX() {
               return CFG.viewsManager.getActiveViewID() == ViewsManager.VIEW_DIPLOMACY_MODE ? super.getSFX() : SoundsManager.SOUND_DIPLOMACY;
            }

            @Override
            public void drawMenuElementHover2(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
               if (this.menuElementHover != null) {
                  this.menuElementHover.drawAlwaysBelow(oSB, Touch.getMousePosX(), ImageManager.getImage(Images.top_left2).getHeight());
               }
            }
         }
      );
      this.setMenuElement(
         14,
         new Text("0", 0, 0, 0, ImageManager.getImage(Images.top_left2).getHeight()) {
            @Override
            public void draw(SpriteBatch param1SpriteBatch, int param1Int1, int param1Int2, boolean param1Boolean1, boolean param1Boolean2) {
               ImageManager.getImage(Images.militaryxp)
                  .draw(
                     param1SpriteBatch,
                     this.getPosX() + param1Int1,
                     this.getPosY() + this.getHeight() / 2 - ImageManager.getImage(Images.militaryxp).getHeight() / 2 + param1Int2
                  );
               super.draw(
                  param1SpriteBatch, param1Int1 + ImageManager.getImage(Images.militaryxp).getWidth() + CFG.PADDING, param1Int2, param1Boolean1, param1Boolean2
               );
            }

            @Override
            public int getPosX() {
               try {
                  return CFG.menuManager.getInGame().getMenuElement(13).getPosX() + CFG.menuManager.getInGame().getMenuElement(13).getWidth() + CFG.PADDING;
               } catch (NullPointerException var2) {
                  return 0;
               }
            }

            @Override
            public int getWidth() {
               return super.getWidth() + ImageManager.getImage(Images.militaryxp).getWidth() + CFG.PADDING;
            }

            @Override
            public Color getColor(boolean isActive) {
               return isActive
                  ? CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                  : (this.getIsHovered() ? CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE : CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE);
            }

            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("MilitaryExperience") + ":", CFG.COLOR_TEXT_MODIFIER_POSITIVE));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }

            @Override
            public int getSFX() {
               return CFG.viewsManager.getActiveViewID() == ViewsManager.VIEW_DIPLOMACY_MODE ? super.getSFX() : SoundsManager.SOUND_DIPLOMACY;
            }

            @Override
            public void drawMenuElementHover2(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
               if (this.menuElementHover != null) {
                  this.menuElementHover.drawAlwaysBelow(oSB, Touch.getMousePosX(), ImageManager.getImage(Images.top_left2).getHeight());
               }
            }
         }
      );
      this.setMenuElement(
         15,
         new Text("0", 0, 0, 0, ImageManager.getImage(Images.top_left2).getHeight()) {
            @Override
            public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
               if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).isAtWar()) {
                  ImageManager.getImage(Images.diplo_war)
                     .draw(
                        oSB,
                        this.getPosX() + iTranslateX,
                        this.getPosY() + this.getHeight() / 2 - ImageManager.getImage(Images.diplo_war).getHeight() / 2 + iTranslateY
                     );
                  int var6;
                  super.draw(oSB, var6 = iTranslateX + ImageManager.getImage(Images.diplo_war).getWidth() + CFG.PADDING, iTranslateY, isActive, scrollableY);
               }
            }

            @Override
            public int getPosX() {
               try {
                  return CFG.menuManager.getInGame().getMenuElement(14).getPosX() + CFG.menuManager.getInGame().getMenuElement(14).getWidth() + CFG.PADDING;
               } catch (NullPointerException var2) {
                  return 0;
               }
            }

            @Override
            public int getWidth() {
               return CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).isAtWar()
                  ? super.getWidth() + ImageManager.getImage(Images.diplo_war).getWidth() + CFG.PADDING
                  : 0;
            }

            @Override
            public Color getColor(boolean isActive) {
               return CFG.COLOR_INGAME_DIPLOMACY_POINTS;
            }

            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("TheSurrenderOfThisCountry") + ": ", CFG.COLOR_INGAME_MOVEMENT_ACTIVE));
               nData.add(
                  new MenuElement_Hover_v2_Element_Type_Text(
                     " " + CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCapitulationPoints() + "%", CFG.COLOR_INGAME_DIPLOMACY_POINTS
                  )
               );
               nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.diplo_war, CFG.PADDING, 0));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }

            @Override
            public int getSFX() {
               return CFG.viewsManager.getActiveViewID() == ViewsManager.VIEW_DIPLOMACY_MODE ? super.getSFX() : SoundsManager.SOUND_DIPLOMACY;
            }

            @Override
            public void drawMenuElementHover2(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
               if (this.menuElementHover != null) {
                  this.menuElementHover.drawAlwaysBelow(oSB, Touch.getMousePosX(), ImageManager.getImage(Images.top_left2).getHeight());
               }
            }
         }
      );
      this.setMenuElement(
         16,
         new Text("0", 0, 0, 0, ImageManager.getImage(Images.top_left2).getHeight()) {
            @Override
            public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
               if (CFG.MANPOWER_SYSTEM) {
                  ImageManager.getImage(Images.editor_leaders)
                     .draw(
                        oSB,
                        this.getPosX() + iTranslateX,
                        this.getPosY() + this.getHeight() / 2 - ImageManager.getImage(Images.editor_leaders).getHeight() / 2 + iTranslateY
                     );
                  int var6;
                  super.draw(
                     oSB, var6 = iTranslateX + ImageManager.getImage(Images.editor_leaders).getWidth() + CFG.PADDING, iTranslateY, isActive, scrollableY
                  );
               }
            }

            @Override
            public int getPosX() {
               try {
                  return CFG.menuManager.getInGame().getMenuElement(15).getPosX() + CFG.menuManager.getInGame().getMenuElement(15).getWidth() + CFG.PADDING;
               } catch (NullPointerException var2) {
                  return 0;
               }
            }

            @Override
            public int getWidth() {
               return CFG.MANPOWER_SYSTEM ? super.getWidth() + ImageManager.getImage(Images.editor_leaders).getWidth() + CFG.PADDING : 0;
            }

            @Override
            public Color getColor(boolean isActive) {
               return CFG.COLOR_FORT_1;
            }

            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("ManPower") + ": ", CFG.COLOR_FORT_1));
               nData.add(
                  new MenuElement_Hover_v2_Element_Type_Text(
                     " " + CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getManPower(), CFG.COLOR_INGAME_DIPLOMACY_POINTS
                  )
               );
               nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.editor_leaders, CFG.PADDING, 0));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("ManPowerThisTurn") + ": ", CFG.COLOR_FORT_1));
               nData.add(
                  new MenuElement_Hover_v2_Element_Type_Text(
                     " " + CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getManPower_ThisTurn(), CFG.COLOR_INGAME_DIPLOMACY_POINTS
                  )
               );
               nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.editor_leaders, CFG.PADDING, 0));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("ManPowerIncreasing") + ": ", CFG.COLOR_FORT_1));
               nData.add(
                  new MenuElement_Hover_v2_Element_Type_Text(
                     " " + CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getManPowerIncreasing(), CFG.COLOR_INGAME_DIPLOMACY_POINTS
                  )
               );
               nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.editor_leaders, CFG.PADDING, 0));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("ManPowerThis") + ": ", CFG.COLOR_FORT_1));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("ManPowerText1"), CFG.COLOR_INGAME_DIPLOMACY_POINTS));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("ManPowerText2"), CFG.COLOR_INGAME_DIPLOMACY_POINTS));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("ManPowerText3"), CFG.COLOR_INGAME_DIPLOMACY_POINTS));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }

            @Override
            public int getSFX() {
               return CFG.viewsManager.getActiveViewID() == ViewsManager.VIEW_DIPLOMACY_MODE ? super.getSFX() : SoundsManager.SOUND_DIPLOMACY;
            }

            @Override
            public void drawMenuElementHover2(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
               if (this.menuElementHover != null) {
                  this.menuElementHover.drawAlwaysBelow(oSB, Touch.getMousePosX(), ImageManager.getImage(Images.top_left2).getHeight());
               }
            }
         }
      );
      this.setMenuElement(
         17,
         new Text("+", -1, 0, 0, ImageManager.getImage(Images.top_left2).getHeight()) {
            @Override
            public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
               CFG.fontMain.getData().setScale(0.6F);
               CFG.drawText(
                  oSB,
                  this.sText,
                  this.getPosX() + CFG.PADDING * 2 + ImageManager.getImage(Images.top_left2).getWidth() / 2 + iTranslateX,
                  this.getPosY() + (int)(this.getHeight() - this.getTextHeight() * 0.6F) / 2 + iTranslateY,
                  this.getColor(isActive)
               );
               CFG.fontMain.getData().setScale(1.0F);
            }

            @Override
            public int getPosX() {
               try {
                  return CFG.menuManager.getInGame().getMenuElement(9).getPosX() + CFG.menuManager.getInGame().getMenuElement(9).getWidth() + CFG.PADDING;
               } catch (NullPointerException var2) {
                  return 0;
               }
            }

            @Override
            public int getWidth() {
               return (int)(this.getTextWidth() * 0.6F) + CFG.PADDING * 2 + CFG.PADDING + ImageManager.getImage(Images.top_left2).getWidth();
            }

            @Override
            public Color getColor(boolean isActive) {
               return Color.WHITE;
            }

            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Surrender") + ": ", CFG.COLOR_CITY_NAME));
               nData.add(
                  new MenuElement_Hover_v2_Element_Type_Text(
                     " " + CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCapitulationPoints() + "%", CFG.COLOR_INGAME_DIPLOMACY_POINTS
                  )
               );
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Army") + ": ", CFG.COLOR_FORT_1));
               nData.add(
                  new MenuElement_Hover_v2_Element_Type_Text(
                     " " + CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getNumOfUnits(), CFG.COLOR_INGAME_DIPLOMACY_POINTS
                  )
               );
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("WarWeariness") + ": ", CFG.COLOR_INGAME_MOVEMENT_ACTIVE));
               nData.add(
                  new MenuElement_Hover_v2_Element_Type_Text(
                     " " + (int)CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getWarWeariness() * 100 + "%",
                     CFG.COLOR_INGAME_DIPLOMACY_POINTS
                  )
               );
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("CivScore") + ": ", CFG.COLOR_INGAME_GOLD_ACTIVE));
               nData.add(
                  new MenuElement_Hover_v2_Element_Type_Text(
                     " " + CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getRankScore(), CFG.COLOR_INGAME_DIPLOMACY_POINTS
                  )
               );
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("ConstructedBuildings") + ": ", CFG.COLOR_TEXT_PROVINCE_STABILITY_MIN));
               nData.add(
                  new MenuElement_Hover_v2_Element_Type_Text(
                     " " + CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).civGameData.iNumOfBuildingsConstructed,
                     CFG.COLOR_INGAME_DIPLOMACY_POINTS
                  )
               );
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Population") + ": ", CFG.COLOR_TEXT_POPULATION));
               int turnPopChange = 0;

               for (int i2 = 0; i2 < CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getNumOfProvinces(); i2++) {
                  turnPopChange += CFG.game.getProvince(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getProvinceID(i2)).saveProvinceData.turnChange_Population;
               }

               if (turnPopChange > 0) {
                  nData.add(new MenuElement_Hover_v2_Element_Type_Text("+" + CFG.getNumberWithSpaces("" + turnPopChange), CFG.COLOR_INGAME_DIPLOMACY_POINTS));
               } else if (turnPopChange < 0) {
                  nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.getNumberWithSpaces("" + turnPopChange), CFG.COLOR_INGAME_DIPLOMACY_POINTS));
               } else {
                  nData.add(new MenuElement_Hover_v2_Element_Type_Text("+" + turnPopChange, CFG.COLOR_INGAME_DIPLOMACY_POINTS));
               }

               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Economy") + ": ", CFG.COLOR_FORT_1));
               int turnEcoChange = 0;

               for (int i2 = 0; i2 < CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getNumOfProvinces(); i2++) {
                  turnEcoChange += CFG.game.getProvince(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getProvinceID(i2)).saveProvinceData.turnChange_Economy;
               }

               if (turnEcoChange > 0) {
                  nData.add(new MenuElement_Hover_v2_Element_Type_Text("+" + CFG.getNumberWithSpaces("" + turnEcoChange), CFG.COLOR_INGAME_DIPLOMACY_POINTS));
               } else if (turnEcoChange < 0) {
                  nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.getNumberWithSpaces("" + turnEcoChange), CFG.COLOR_INGAME_DIPLOMACY_POINTS));
               } else {
                  nData.add(new MenuElement_Hover_v2_Element_Type_Text("+" + turnEcoChange, CFG.COLOR_INGAME_DIPLOMACY_POINTS));
               }

               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("AverageDevelopmentLevel") + ": ", CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
               nData.add(
                  new MenuElement_Hover_v2_Element_Type_Text(
                     "" + CFG.game.countAvarageDevelopmentLevel(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()), CFG.COLOR_TEXT_MODIFIER_NEUTRAL
                  )
               );
               nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.development, CFG.PADDING, CFG.PADDING));
               nData.add(
                  new MenuElement_Hover_v2_Element_Type_Text(
                     "["
                        + (int)(
                           CFG.game.countAvarageDevelopmentLevel_Float(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
                              / CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getTechnologyLevel()
                              * 100.0F
                        )
                        + "%",
                     CFG.COLOR_TEXT_MODIFIER_NEUTRAL
                  )
               );
               nData.add(new MenuElement_Hover_v2_Element_Type_Text("]", CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }

            @Override
            public int getSFX() {
               return CFG.viewsManager.getActiveViewID() == ViewsManager.VIEW_DIPLOMACY_MODE ? super.getSFX() : SoundsManager.SOUND_DIPLOMACY;
            }

            @Override
            public void drawMenuElementHover2(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
               if (this.menuElementHover != null) {
                  this.menuElementHover.drawAlwaysBelow(oSB, Touch.getMousePosX(), ImageManager.getImage(Images.top_left2).getHeight());
               }
            }
         }
      );
      this.setMenuElement(
         32,
         new Text("", 0, 0, 0, ImageManager.getImage(Images.top_left2).getHeight()) {
            @Override
            public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
               if (CFG.FREEPLAY_MODE) {
                  CFG.fontMain.getData().setScale(0.6F);
                  CFG.drawText(
                     oSB,
                     this.sText,
                     this.getPosX() + CFG.PADDING + ImageManager.getImage(Images.top_left2).getWidth() + iTranslateX,
                     this.getPosY() + (int)(this.getHeight() - this.getTextHeight() * 0.6F) / 2 + iTranslateY,
                     this.getColor(isActive)
                  );
                  CFG.fontMain.getData().setScale(1.0F);
               }
            }

            @Override
            public int getPosX() {
               try {
                  return CFG.menuManager.getInGame().getMenuElement(16).getPosX() + CFG.menuManager.getInGame().getMenuElement(16).getWidth() + CFG.PADDING;
               } catch (NullPointerException var2) {
                  return 0;
               }
            }

            @Override
            public int getWidth() {
               return (int)(this.getTextWidth() * 0.6F) + CFG.PADDING * 2 + CFG.PADDING + ImageManager.getImage(Images.top_left2).getWidth();
            }

            @Override
            public Color getColor(boolean isActive) {
               return Color.YELLOW;
            }

            @Override
            public void buildElementHover() {
            }

            @Override
            public int getSFX() {
               return CFG.viewsManager.getActiveViewID() == ViewsManager.VIEW_DIPLOMACY_MODE ? super.getSFX() : SoundsManager.SOUND_DIPLOMACY;
            }

            @Override
            public void drawMenuElementHover2(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
               if (this.menuElementHover != null) {
                  this.menuElementHover.drawAlwaysBelow(oSB, Touch.getMousePosX(), ImageManager.getImage(Images.top_left2).getHeight());
               }
            }
         }
      );
      this.setMenuElement(
         3,
         new Text("1.4", 0, 0, 0, ImageManager.getImage(Images.top_left2).getHeight()) {
            @Override
            public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
               ImageManager.getImage(Images.top_diplomacy_points)
                  .draw(
                     oSB,
                     this.getPosX() + iTranslateX,
                     this.getPosY() + this.getHeight() / 2 - ImageManager.getImage(Images.top_diplomacy_points).getHeight() / 2 + iTranslateY
                  );
               int var6;
               super.draw(
                  oSB, var6 = iTranslateX + ImageManager.getImage(Images.top_diplomacy_points).getWidth() + CFG.PADDING, iTranslateY, isActive, scrollableY
               );
            }

            @Override
            public int getPosX() {
               try {
                  return CFG.menuManager.getInGame().getMenuElement(2).getPosX() + CFG.menuManager.getInGame().getMenuElement(2).getWidth() + CFG.PADDING;
               } catch (NullPointerException var2) {
                  return 0;
               }
            }

            @Override
            public int getWidth() {
               return super.getWidth() + ImageManager.getImage(Images.top_diplomacy_points).getWidth() + CFG.PADDING;
            }

            @Override
            public Color getColor(boolean isActive) {
               return isActive
                  ? CFG.COLOR_INGAME_DIPLOMACY_POINTS_ACTIVE
                  : (this.getIsHovered() ? CFG.COLOR_INGAME_DIPLOMACY_POINTS_HOVER : CFG.COLOR_INGAME_DIPLOMACY_POINTS);
            }

            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("DiplomacyPoints") + ": ", CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
               nData.add(
                  new MenuElement_Hover_v2_Element_Type_Text(
                     "" + CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getDiplomacyPoints() / 10.0F, CFG.COLOR_INGAME_DIPLOMACY_POINTS
                  )
               );
               nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_diplomacy_points, CFG.PADDING, 0));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               int perTurn = CFG.gameAction.getUpdateCivsDiplomacyPoints(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("TurnIncrease") + ": "));
               nData.add(
                  new MenuElement_Hover_v2_Element_Type_Text(
                     "+" + Math.max(perTurn, 0) / 10.0F, perTurn > 0 ? CFG.COLOR_TEXT_MODIFIER_POSITIVE : CFG.COLOR_TEXT_MODIFIER_NEUTRAL2
                  )
               );
               nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_diplomacy_points, CFG.PADDING, 0));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               nData.add(new MenuElement_Hover_v2_Element_Type_Space());
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("BaseValue") + ": "));
               nData.add(
                  new MenuElement_Hover_v2_Element_Type_Text(
                     "+" + CFG.gameAction.getDiplomacyPoints_BaseValue(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) / 10.0F,
                     CFG.COLOR_TEXT_MODIFIER_POSITIVE
                  )
               );
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Rank") + ": "));
               nData.add(
                  new MenuElement_Hover_v2_Element_Type_Text(
                     ""
                        + (CFG.gameAction.getDiplomacyPoints_FromRank(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) == 0 ? "" : "+")
                        + CFG.gameAction.getDiplomacyPoints_FromRank(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) / 10.0F,
                     CFG.gameAction.getDiplomacyPoints_FromRank(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) == 0
                        ? CFG.COLOR_TEXT_MODIFIER_NEUTRAL
                        : CFG.COLOR_TEXT_MODIFIER_POSITIVE
                  )
               );
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Technology") + ": "));
               nData.add(
                  new MenuElement_Hover_v2_Element_Type_Text(
                     ""
                        + (CFG.gameAction.getDiplomacyPoints_FromTechnology(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) == 0 ? "" : "+")
                        + CFG.gameAction.getDiplomacyPoints_FromTechnology(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) / 10.0F,
                     CFG.gameAction.getDiplomacyPoints_FromTechnology(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) == 0
                        ? CFG.COLOR_TEXT_MODIFIER_NEUTRAL
                        : CFG.COLOR_TEXT_MODIFIER_POSITIVE
                  )
               );
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Enemies") + ": "));
               nData.add(
                  new MenuElement_Hover_v2_Element_Type_Text(
                     "" + CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getHatedCivsSize(),
                     CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getHatedCivsSize() > 0
                        ? CFG.COLOR_TEXT_MODIFIER_POSITIVE
                        : CFG.COLOR_TEXT_MODIFIER_NEGATIVE2
                  )
               );
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(" / "));
               nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + CFG.oAI.MIN_NUM_OF_RIVALS, CFG.COLOR_TEXT_NUM_OF_PROVINCES));
               nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.diplo_rivals, CFG.PADDING, CFG.PADDING));
               nData.add(
                  new MenuElement_Hover_v2_Element_Type_Text(
                     ""
                        + (CFG.gameAction.getDiplomacyPoints_FromEnemies(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) <= 0 ? "" : "+")
                        + CFG.gameAction.getDiplomacyPoints_FromEnemies(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) / 10.0F,
                     CFG.gameAction.getDiplomacyPoints_FromEnemies(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) == 0
                        ? CFG.COLOR_TEXT_MODIFIER_NEUTRAL
                        : (
                           CFG.gameAction.getDiplomacyPoints_FromEnemies(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) < 0
                              ? CFG.COLOR_TEXT_MODIFIER_NEGATIVE2
                              : CFG.COLOR_TEXT_MODIFIER_POSITIVE
                        )
                  )
               );
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               if (DiplomacyManager.getCostOfCurrentDiplomaticActions(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) > 0) {
                  nData.add(new MenuElement_Hover_v2_Element_Type_Space());
                  nElements.add(new MenuElement_Hover_v2_Element2(nData));
                  nData.clear();
               }

               if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID() > 0
                  && CFG.game.getAlliance(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID()).getCivilizationsSize() > 1) {
                  for (int j = 0;
                     j < CFG.game.getAlliance(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID()).getCivilizationsSize();
                     j++
                  ) {
                     if (CFG.game.getAlliance(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID()).getCivilization(j)
                        != CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) {
                        nData.add(
                           new MenuElement_Hover_v2_Element_Type_Color(
                              new Color(
                                 CFG.game
                                       .getCiv(
                                          CFG.game
                                             .getAlliance(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID())
                                             .getCivilization(j)
                                       )
                                       .getR()
                                    / 255.0F,
                                 CFG.game
                                       .getCiv(
                                          CFG.game
                                             .getAlliance(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID())
                                             .getCivilization(j)
                                       )
                                       .getG()
                                    / 255,
                                 CFG.game
                                       .getCiv(
                                          CFG.game
                                             .getAlliance(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID())
                                             .getCivilization(j)
                                       )
                                       .getB()
                                    / 255.0F,
                                 1.0F
                              )
                           )
                        );
                        nData.add(
                           new MenuElement_Hover_v2_Element_Type_Flag(
                              CFG.game.getAlliance(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID()).getCivilization(j)
                           )
                        );
                        break;
                     }
                  }

                  nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Alliance")));
                  nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.diplo_alliance, CFG.PADDING, CFG.PADDING));
                  nData.add(new MenuElement_Hover_v2_Element_Type_Text("-0.6", CFG.COLOR_TEXT_MODIFIER_NEGATIVE2));
                  nElements.add(new MenuElement_Hover_v2_Element2(nData));
                  nData.clear();
               }

               for (int i = 0; i < CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getFriendlyCivsSize(); i++) {
                  if (CFG.game.getCiv(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getFriendlyCiv(i).iCivID).getNumOfProvinces() > 0) {
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Color(
                           new Color(
                              CFG.game.getCiv(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getFriendlyCiv(i).iCivID).getR() / 255.0F,
                              CFG.game.getCiv(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getFriendlyCiv(i).iCivID).getG() / 255,
                              CFG.game.getCiv(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getFriendlyCiv(i).iCivID).getB() / 255.0F,
                              1.0F
                           )
                        )
                     );
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getFriendlyCiv(i).iCivID)
                     );
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("FriendlyCivilizations")));
                     nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.diplo_heart, CFG.PADDING, CFG.PADDING));
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text("-0.3", CFG.COLOR_TEXT_MODIFIER_NEGATIVE2));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                  }
               }

               if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).civGameData.iVassalsSize > 0) {
                  nData.add(
                     new MenuElement_Hover_v2_Element_Type_Color(
                        new Color(
                           CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getR() / 255.0F,
                           CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getG() / 255,
                           CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getB() / 255.0F,
                           1.0F
                        )
                     )
                  );
                  nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()));
                  nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Vassals")));
                  nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.diplo_vassal, CFG.PADDING, CFG.PADDING));
                  nData.add(
                     new MenuElement_Hover_v2_Element_Type_Text(
                        "-" + 1 * CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).civGameData.iVassalsSize / 10.0F,
                        CFG.COLOR_TEXT_MODIFIER_NEGATIVE2
                     )
                  );
                  nElements.add(new MenuElement_Hover_v2_Element2(nData));
                  nData.clear();
               }

               for (int var6 = 1; var6 < CFG.game.getCivsSize(); var6++) {
                  if (CFG.game.getCiv(var6).getNumOfProvinces() > 0 && var6 != CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) {
                     if (CFG.game.getCivNonAggressionPact(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), var6) > 0) {
                        nData.add(
                           new MenuElement_Hover_v2_Element_Type_Color(
                              new Color(CFG.game.getCiv(var6).getR() / 255.0F, CFG.game.getCiv(var6).getG() / 255, CFG.game.getCiv(var6).getB() / 255.0F, 1.0F)
                           )
                        );
                        nData.add(new MenuElement_Hover_v2_Element_Type_Flag(var6));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("NonAggressionPact")));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.diplo_non_aggression, CFG.PADDING, CFG.PADDING));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text("-0.2", CFG.COLOR_TEXT_MODIFIER_NEGATIVE2));
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                     }

                     if (CFG.game.getGuarantee(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), var6) > 0) {
                        nData.add(
                           new MenuElement_Hover_v2_Element_Type_Color(
                              new Color(CFG.game.getCiv(var6).getR() / 255.0F, CFG.game.getCiv(var6).getG() / 255, CFG.game.getCiv(var6).getB() / 255.0F, 1.0F)
                           )
                        );
                        nData.add(new MenuElement_Hover_v2_Element_Type_Flag(var6));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("GuaranteeIndependence")));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.diplo_guarantee_gives, CFG.PADDING, CFG.PADDING));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text("-0.1", CFG.COLOR_TEXT_MODIFIER_NEGATIVE2));
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                     }

                     if (CFG.game.getDefensivePact(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), var6) > 0) {
                        nData.add(
                           new MenuElement_Hover_v2_Element_Type_Color(
                              new Color(CFG.game.getCiv(var6).getR() / 255.0F, CFG.game.getCiv(var6).getG() / 255, CFG.game.getCiv(var6).getB() / 255.0F, 1.0F)
                           )
                        );
                        nData.add(new MenuElement_Hover_v2_Element_Type_Flag(var6));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("DefensivePact")));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.diplo_defensive_pact, CFG.PADDING, CFG.PADDING));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text("-0.3", CFG.COLOR_TEXT_MODIFIER_NEGATIVE2));
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                     }

                     if (CFG.game.getMilitaryAccess(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), var6) > 0) {
                        nData.add(
                           new MenuElement_Hover_v2_Element_Type_Color(
                              new Color(CFG.game.getCiv(var6).getR() / 255.0F, CFG.game.getCiv(var6).getG() / 255, CFG.game.getCiv(var6).getB() / 255.0F, 1.0F)
                           )
                        );
                        nData.add(new MenuElement_Hover_v2_Element_Type_Flag(var6));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("MilitaryAccess")));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.diplo_access_has, CFG.PADDING, CFG.PADDING));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text("-0.1", CFG.COLOR_TEXT_MODIFIER_NEGATIVE2));
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                     }
                  }
               }

               if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCivilization_Diplomacy_GameData().getImproveRelationsSize() > 0) {
                  nData.add(new MenuElement_Hover_v2_Element_Type_Space());
                  nElements.add(new MenuElement_Hover_v2_Element2(nData));
                  nData.clear();
                  nData.add(
                     new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("WeAreImprovingOurRelationsWith") + ": ", CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE)
                  );
                  nElements.add(new MenuElement_Hover_v2_Element2(nData));
                  nData.clear();

                  for (int var7 = 0;
                     var7 < CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCivilization_Diplomacy_GameData().getImproveRelationsSize();
                     var7++
                  ) {
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Color(
                           new Color(
                              CFG.game
                                    .getCiv(
                                       CFG.game
                                          .getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
                                          .getCivilization_Diplomacy_GameData()
                                          .getImproveRelation(var7)
                                          .iWithCivID
                                    )
                                    .getR()
                                 / 255.0F,
                              CFG.game
                                    .getCiv(
                                       CFG.game
                                          .getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
                                          .getCivilization_Diplomacy_GameData()
                                          .getImproveRelation(var7)
                                          .iWithCivID
                                    )
                                    .getG()
                                 / 255,
                              CFG.game
                                    .getCiv(
                                       CFG.game
                                          .getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
                                          .getCivilization_Diplomacy_GameData()
                                          .getImproveRelation(var7)
                                          .iWithCivID
                                    )
                                    .getB()
                                 / 255.0F,
                              1.0F
                           )
                        )
                     );
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Flag(
                           CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCivilization_Diplomacy_GameData().getImproveRelation(var7).iWithCivID
                        )
                     );
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           CFG.game
                              .getCiv(
                                 CFG.game
                                    .getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
                                    .getCivilization_Diplomacy_GameData()
                                    .getImproveRelation(var7)
                                    .iWithCivID
                              )
                              .getCivName()
                        )
                     );
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           " +"
                              + (int)(
                                    DiplomacyManager.getImproveRelation(
                                          CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(),
                                          CFG.game
                                             .getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
                                             .getCivilization_Diplomacy_GameData()
                                             .getImproveRelation(var7)
                                             .iWithCivID
                                       )
                                       * 100.0F
                                 )
                                 / 100.0F,
                           CFG.COLOR_TEXT_MODIFIER_POSITIVE
                        )
                     );
                     nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.diplo_relations_inc, CFG.PADDING, CFG.PADDING));
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text("-0.5", CFG.COLOR_TEXT_MODIFIER_NEGATIVE2));
                     nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_diplomacy_points, CFG.PADDING, CFG.PADDING));
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("PerTurn")));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                  }
               }

               this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }

            @Override
            public int getSFX() {
               return CFG.viewsManager.getActiveViewID() == ViewsManager.VIEW_DIPLOMACY_MODE ? super.getSFX() : SoundsManager.SOUND_DIPLOMACY;
            }

            @Override
            public void drawMenuElementHover2(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
               if (this.menuElementHover != null) {
                  this.menuElementHover.drawAlwaysBelow(oSB, Touch.getMousePosX(), ImageManager.getImage(Images.top_left2).getHeight());
               }
            }
         }
      );
      this.setMenuElement(
         4,
         new Text(null, 0, 0, CFG.PADDING, ImageManager.getImage(Images.top_left2).getHeight()) {
            @Override
            public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
               CFG.fontMain.getData().setScale(0.8F);
               CFG.drawTextWithShadow(
                  oSB,
                  this.sText,
                  this.getPosX() + this.getWidth() / 2 - (int)(this.getTextWidth() * 0.8F) / 2 + iTranslateX,
                  this.getPosY() + iTranslateY,
                  this.getColor(isActive)
               );
               CFG.fontMain.getData().setScale(1.0F);
            }

            @Override
            public Color getColor(boolean isActive) {
               return isActive
                  ? CFG.COLOR_TEXT_CIV_NAME_ACTIVE
                  : (
                     this.getClickable()
                        ? (this.getIsHovered() ? CFG.COLOR_TEXT_CIV_NAME_HOVERED : CFG.COLOR_TEXT_CIV_NAME)
                        : new Color(0.78F, 0.78F, 0.78F, 0.7F)
                  );
            }

            @Override
            public int getPosX() {
               return CFG.GAME_WIDTH
                  + (
                     RTS.isEnabled()
                        ? -ImageManager.getImage(Images.top_left2).getHeight()
                           - CFG.PADDING
                           - Math.max(CFG.BUTTON_WIDTH + CFG.BUTTON_WIDTH / 2, (int)(this.getTextWidth() * 0.8F) + CFG.PADDING * 4)
                        : -((int)(this.getTextWidth() * 0.8F)) - CFG.PADDING
                  );
            }

            @Override
            public int getWidth() {
               return RTS.isEnabled()
                  ? Math.max(CFG.BUTTON_WIDTH + CFG.BUTTON_WIDTH / 2, (int)(this.getTextWidth() * 0.8F) + CFG.PADDING * 4)
                  : (int)(this.getTextWidth() * 0.8F);
            }

            @Override
            public int getHeight() {
               return (int)(CFG.TEXT_HEIGHT * 0.8F) + CFG.PADDING;
            }

            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               if (RTS.isEnabled()) {
                  if (RTS.PAUSE) {
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("ClickToUnpauseTheGame"), CFG.COLOR_TEXT_NUM_OF_PROVINCES));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                     nData.add(new MenuElement_Hover_v2_Element_Type_Space());
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                  } else {
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("ClickToPauseTheGame"), CFG.COLOR_TEXT_NUM_OF_PROVINCES));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                     nData.add(new MenuElement_Hover_v2_Element_Type_Space());
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                  }

                  nData.add(new MenuElement_Hover_v2_Element_Type_Text(Game_Calendar.getCurrentDate()));
                  nData.add(
                     new MenuElement_Hover_v2_Element_Type_Text(
                        " - " + CFG.gameAges.getAge(Game_Calendar.CURRENT_AGEID).getName(), CFG.COLOR_TEXT_MODIFIER_NEUTRAL
                     )
                  );
                  nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.time, CFG.PADDING, 0));
                  nElements.add(new MenuElement_Hover_v2_Element2(nData));
                  nData.clear();
                  if (Game_Calendar.TURN_ID != 1) {
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("PlayingTime") + ": "));
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(Game_Calendar.getNumOfDates_ByTurnID(1), CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                  }

                  if (CFG.isDesktop()) {
                     nData.add(new MenuElement_Hover_v2_Element_Type_Space());
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Shortcut") + ": "));
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text("ENTER", CFG.COLOR_TEXT_NUM_OF_PROVINCES));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                  }
               } else {
                  nData.add(new MenuElement_Hover_v2_Element_Type_Text(Game_Calendar.getCurrentDate()));
                  nData.add(
                     new MenuElement_Hover_v2_Element_Type_Text(
                        " - " + CFG.gameAges.getAge(Game_Calendar.CURRENT_AGEID).getName(), CFG.COLOR_TEXT_MODIFIER_NEUTRAL
                     )
                  );
                  nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.time, CFG.PADDING, 0));
                  nElements.add(new MenuElement_Hover_v2_Element2(nData));
                  nData.clear();
                  nData.add(new MenuElement_Hover_v2_Element_Type_Text(Game_Calendar.getCurrentDate()));
                  nData.add(new MenuElement_Hover_v2_Element_Type_Text(" - " + CFG.langManager.get("Turn") + ": "));
                  nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.getNumberWithSpaces("" + Game_Calendar.TURN_ID), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                  nElements.add(new MenuElement_Hover_v2_Element2(nData));
                  nData.clear();
                  if (Game_Calendar.TURN_ID != 1) {
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("PlayingTime") + ": "));
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(Game_Calendar.getNumOfDates_ByTurnID(1), CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                  }

                  if (CFG.isDesktop()) {
                     nData.add(new MenuElement_Hover_v2_Element_Type_Space());
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Shortcut") + ": "));
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text("F8", CFG.COLOR_TEXT_NUM_OF_PROVINCES));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                  }
               }

               this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }

            @Override
            public void drawMenuElementHover2(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
               if (this.menuElementHover != null) {
                  this.menuElementHover.drawAlwaysBelow(oSB, Touch.getMousePosX(), ImageManager.getImage(Images.top_left2).getHeight());
               }
            }

            @Override
            public int getSFX() {
               return SoundsManager.SOUND_CLICK2;
            }
         }
      );
      int tempTurnH = ImageManager.getImage(Images.top_left2).getHeight() - CFG.PADDING * 3 - (int)(CFG.TEXT_HEIGHT * 0.8F);

      for (int i = 0; i < 60 && !(CFG.TEXT_HEIGHT * fTurnScale <= tempTurnH); i++) {
         fTurnScale -= 0.01F;
      }

      this.setMenuElement(
         5,
         new Text(null, 0, 0, CFG.PADDING * 2 + (int)(CFG.TEXT_HEIGHT * 0.8F), ImageManager.getImage(Images.top_left2).getHeight()) {
            @Override
            public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
               CFG.fontMain.getData().setScale(Menu_InGame.fTurnScale);
               CFG.drawTextWithShadow(
                  oSB,
                  this.sText,
                  this.getPosX()
                     + (
                        RTS.isEnabled()
                           ? (int)((this.getWidth() - this.getTextWidth() * Menu_InGame.fTurnScale) / 2.0F)
                           : (int)(this.getWidth() - this.getTextWidth() * Menu_InGame.fTurnScale)
                     )
                     + iTranslateX,
                  this.getPosY() + iTranslateY,
                  this.getColor(isActive)
               );
               CFG.fontMain.getData().setScale(1.0F);
            }

            @Override
            public Color getColor(boolean isActive) {
               return isActive
                  ? CFG.COLOR_TEXT_RANK_ACTIVE
                  : (this.getClickable() ? (this.getIsHovered() ? CFG.COLOR_TEXT_RANK_HOVER : CFG.COLOR_TEXT_RANK) : new Color(0.78F, 0.78F, 0.78F, 0.7F));
            }

            @Override
            public int getPosX() {
               return Menu_InGame.this.getMenuElement(4).getPosX();
            }

            @Override
            public int getWidth() {
               return Menu_InGame.this.getMenuElement(4).getWidth();
            }

            @Override
            public int getHeight() {
               return (int)(CFG.TEXT_HEIGHT * Menu_InGame.fTurnScale);
            }

            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               if (RTS.isEnabled()) {
                  if (RTS.PAUSE) {
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("ClickToUnpauseTheGame"), CFG.COLOR_TEXT_NUM_OF_PROVINCES));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                     nData.add(new MenuElement_Hover_v2_Element_Type_Space());
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                  } else {
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("ClickToPauseTheGame"), CFG.COLOR_TEXT_NUM_OF_PROVINCES));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                     nData.add(new MenuElement_Hover_v2_Element_Type_Space());
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                  }

                  nData.add(new MenuElement_Hover_v2_Element_Type_Text(Game_Calendar.getCurrentDate()));
                  nData.add(
                     new MenuElement_Hover_v2_Element_Type_Text(
                        " - " + CFG.gameAges.getAge(Game_Calendar.CURRENT_AGEID).getName(), CFG.COLOR_TEXT_MODIFIER_NEUTRAL
                     )
                  );
                  nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.time, CFG.PADDING, 0));
                  nElements.add(new MenuElement_Hover_v2_Element2(nData));
                  nData.clear();
                  if (Game_Calendar.TURN_ID != 1) {
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("PlayingTime") + ": "));
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(Game_Calendar.getNumOfDates_ByTurnID(1), CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                  }

                  if (CFG.isDesktop()) {
                     nData.add(new MenuElement_Hover_v2_Element_Type_Space());
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Shortcut") + ": "));
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text("ENTER", CFG.COLOR_TEXT_NUM_OF_PROVINCES));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                  }
               } else {
                  nData.add(new MenuElement_Hover_v2_Element_Type_Text(Game_Calendar.getCurrentDate()));
                  nData.add(
                     new MenuElement_Hover_v2_Element_Type_Text(
                        " - " + CFG.gameAges.getAge(Game_Calendar.CURRENT_AGEID).getName(), CFG.COLOR_TEXT_MODIFIER_NEUTRAL
                     )
                  );
                  nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.time, CFG.PADDING, 0));
                  nElements.add(new MenuElement_Hover_v2_Element2(nData));
                  nData.clear();
                  if (Game_Calendar.TURN_ID != 1) {
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("PlayingTime") + ": "));
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(Game_Calendar.getNumOfDates_ByTurnID(1), CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                  }

                  if (CFG.isDesktop()) {
                     nData.add(new MenuElement_Hover_v2_Element_Type_Space());
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Shortcut") + ": "));
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text("F8", CFG.COLOR_TEXT_NUM_OF_PROVINCES));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                  }
               }

               this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }

            @Override
            public int getSFX() {
               return SoundsManager.SOUND_CLICK2;
            }

            @Override
            public void drawMenuElementHover2(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
               if (this.menuElementHover != null) {
                  this.menuElementHover.drawAlwaysBelow(oSB, Touch.getMousePosX(), ImageManager.getImage(Images.top_left2).getHeight());
               }
            }
         }
      );
      this.setMenuElement(
         11,
         new Button_Speed(
            "-", -1, 0, 0, ImageManager.getImage(Images.top_left2).getHeight() + CFG.PADDING, ImageManager.getImage(Images.top_left2).getHeight() - 2, true
         ) {
            @Override
            public int getPosX() {
               return Menu_InGame.this.getMenuElement(4).getPosX() - this.getWidth();
            }

            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("DecreaseSpeed"), CFG.COLOR_TEXT_NUM_OF_PROVINCES));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               if (CFG.isDesktop()) {
                  nData.add(new MenuElement_Hover_v2_Element_Type_Space());
                  nElements.add(new MenuElement_Hover_v2_Element2(nData));
                  nData.clear();
                  nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Shortcut") + ": "));
                  nData.add(new MenuElement_Hover_v2_Element_Type_Text("-", CFG.COLOR_TEXT_NUM_OF_PROVINCES));
                  nElements.add(new MenuElement_Hover_v2_Element2(nData));
                  nData.clear();
               }

               this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }

            @Override
            public boolean getVisible() {
               return RTS.isEnabled();
            }

            @Override
            public void drawMenuElementHover2(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
               if (this.menuElementHover != null) {
                  this.menuElementHover.drawAlwaysBelow(oSB, Touch.getMousePosX(), ImageManager.getImage(Images.top_left2).getHeight());
               }
            }
         }
      );
      this.updateLanguage();
      this.updateMenuElements_IsInView();
   }

   @Override
   public void updateLanguage() {
      this.getMenuElement(4).setText(Game_Calendar.getCurrentDate());
      this.getMenuElement(5).setText(CFG.langManager.get("Turn") + ": " + Game_Calendar.TURN_ID);
      this.getMenuElement(8).setText(CFG.langManager.get("Diplomacy"));
      this.getMenuElement(9).setText(CFG.langManager.get("MapModes"));
      this.getMenuElement(32).setText(CFG.langManager.get("Sandbox"));
   }

   public static final void draw_Time(SpriteBatch oSB, int nPosX, int nPosY, int nWidth, int nHeight) {
      oSB.setColor(new Color(CFG.COLOR_GRADIENT_TITLE_BLUE.r, CFG.COLOR_GRADIENT_TITLE_BLUE.g, CFG.COLOR_GRADIENT_TITLE_BLUE.b, 1.0F));
      ImageManager.getImage(Images.patt2).draw2(oSB, nPosX, nPosY - ImageManager.getImage(Images.patt2).getHeight(), nWidth, nHeight);
      ImageManager.getImage(Images.patt2).draw2(oSB, nPosX, nPosY - ImageManager.getImage(Images.patt2).getHeight(), nWidth, nHeight);
      oSB.setColor(new Color(1.0F, 1.0F, 1.0F, 1.0F));
      ImageManager.getImage(Images.patt2)
         .draw2(oSB, nPosX, nPosY - ImageManager.getImage(Images.patt2).getHeight(), (int)(nWidth * RTS.getTimePerc()), nHeight, 0, RTS.SOURCE);
      oSB.setColor(new Color(1.0F, 1.0F, 1.0F, 0.75F));
      ImageManager.getImage(Images.patt2)
         .draw2(oSB, nPosX, nPosY - ImageManager.getImage(Images.patt2).getHeight(), (int)(nWidth * RTS.getTimePerc()), nHeight, 0, RTS.SOURCE);
      if (!RTS.PAUSE) {
         RTS.SOURCE--;
      }

      oSB.setColor(new Color(CFG.COLOR_GRADIENT_TITLE_BLUE.r, CFG.COLOR_GRADIENT_TITLE_BLUE.g, CFG.COLOR_GRADIENT_TITLE_BLUE.b, 0.4F));
      ImageManager.getImage(Images.gradient).draw(oSB, nPosX, nPosY - ImageManager.getImage(Images.gradient).getHeight(), nWidth, nHeight, false, true);
      oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.45F));
      ImageManager.getImage(Images.gradient).draw(oSB, nPosX, nPosY - ImageManager.getImage(Images.gradient).getHeight(), nWidth, CFG.PADDING);
      oSB.setColor(Color.WHITE);
   }

   public static final void draw_Speed(SpriteBatch oSB, int nPosX, int nPosY, int nWidth, int nHeight) {
      oSB.setColor(new Color(CFG.COLOR_GRADIENT_TITLE_BLUE.r, CFG.COLOR_GRADIENT_TITLE_BLUE.g, CFG.COLOR_GRADIENT_TITLE_BLUE.b, 0.75F));
      ImageManager.getImage(Images.pix255_255_255).draw(oSB, nPosX, nPosY - ImageManager.getImage(Images.pix255_255_255).getHeight(), nWidth, nHeight);
      oSB.setColor(new Color(1.0F, 1.0F, 1.0F, 0.075F));
      ImageManager.getImage(Images.gradient)
         .draw(oSB, nPosX, nPosY + nHeight - nHeight / 2 - ImageManager.getImage(Images.gradient).getHeight(), nWidth, nHeight / 2, false, true);
      ImageManager.getImage(Images.gradient).draw(oSB, nPosX, nPosY - ImageManager.getImage(Images.gradient).getHeight(), nWidth, nHeight / 2, false, false);
      ImageManager.getImage(Images.slider_gradient).draw(oSB, nPosX, nPosY - ImageManager.getImage(Images.slider_gradient).getHeight(), CFG.PADDING, nHeight);
      ImageManager.getImage(Images.slider_gradient)
         .draw(oSB, nPosX + nWidth - CFG.PADDING, nPosY - ImageManager.getImage(Images.slider_gradient).getHeight(), CFG.PADDING, nHeight, true, false);
      oSB.setColor(Color.WHITE);
   }

   @Override
   public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      if (CFG.FREEPLAY_MODE) {
         if (CFG.gameAction.getActiveTurnState() == Game_Action.TurnStates.LOADING_NEXT_TURN && TIME_CONTINUE > 0L) {
            CFG.setRender_3(true);
            if (TIME_CONTINUE < System.currentTimeMillis() - 6L) {
               TIME_CONTINUE = -1L;
               Menu_InGame_ProvinceInfo.clickEndTurn();
            }
         }

         int nElemWidthID = this.getMenuElement(10).getVisible() ? 10 : 32;
         ImageManager.getImage(Images.top_left)
            .draw2(
               oSB,
               iTranslateX,
               iTranslateY - ImageManager.getImage(Images.top_left).getHeight(),
               this.getMenuElement(nElemWidthID).getPosX() + this.getMenuElement(nElemWidthID).getWidth(),
               ImageManager.getImage(Images.top_left).getHeight()
            );
         ImageManager.getImage(Images.top_left2_sha)
            .draw2(
               oSB,
               this.getMenuElement(16).getPosX() + this.getMenuElement(16).getWidth() + iTranslateX,
               -ImageManager.getImage(Images.top_left2_sha).getHeight() + iTranslateY,
               this.getMenuElement(32).getWidth()
                  + this.getMenuElement(8).getWidth()
                  + this.getMenuElement(9).getWidth()
                  + ImageManager.getImage(Images.top_left2_sha).getWidth()
                  - CFG.PADDING,
               ImageManager.getImage(Images.top_left2_sha).getHeight(),
               true,
               false
            );
         ImageManager.getImage(this.getMenuElement(32).getIsHovered() ? Images.top_left3 : Images.top_left2)
            .draw2(
               oSB,
               this.getMenuElement(16).getPosX() + this.getMenuElement(16).getWidth() + iTranslateX,
               -ImageManager.getImage(Images.top_left3).getHeight() + iTranslateY,
               this.getMenuElement(32).getWidth() + CFG.PADDING + ImageManager.getImage(Images.top_left2).getWidth(),
               ImageManager.getImage(Images.top_left3).getHeight(),
               true,
               false
            );
         ImageManager.getImage(this.getMenuElement(17).getIsHovered() ? Images.top_left2 : Images.top_left3)
            .draw2(
               oSB,
               this.getMenuElement(9).getPosX() + this.getMenuElement(9).getWidth() + iTranslateX,
               -ImageManager.getImage(Images.top_left3).getHeight() + iTranslateY,
               this.getMenuElement(17).getWidth() - CFG.PADDING + ImageManager.getImage(Images.top_left2).getWidth(),
               ImageManager.getImage(Images.top_left3).getHeight(),
               true,
               false
            );
         ImageManager.getImage(this.getMenuElement(9).getIsHovered() ? Images.top_left2 : Images.top_left3)
            .draw2(
               oSB,
               this.getMenuElement(8).getPosX() + this.getMenuElement(8).getWidth() + iTranslateX,
               -ImageManager.getImage(Images.top_left3).getHeight() + iTranslateY,
               this.getMenuElement(9).getWidth() - CFG.PADDING + ImageManager.getImage(Images.top_left2).getWidth(),
               ImageManager.getImage(Images.top_left3).getHeight(),
               true,
               false
            );
         ImageManager.getImage(this.getMenuElement(8).getIsHovered() ? Images.top_left3 : Images.top_left2)
            .draw2(
               oSB,
               this.getMenuElement(32).getPosX() + this.getMenuElement(32).getWidth() + iTranslateX,
               -ImageManager.getImage(Images.top_left3).getHeight() + iTranslateY,
               this.getMenuElement(8).getWidth() - CFG.PADDING + ImageManager.getImage(Images.top_left2).getWidth(),
               ImageManager.getImage(Images.top_left3).getHeight(),
               true,
               false
            );
         ImageManager.getImage(Images.top_left2)
            .draw(oSB, this.getMenuElement(16).getPosX() + this.getMenuElement(16).getWidth() + iTranslateX, iTranslateY, true, false);
         ImageManager.getImage(Images.top_left2)
            .draw(oSB, this.getMenuElement(32).getPosX() + this.getMenuElement(32).getWidth() + iTranslateX, iTranslateY, true, false);
         ImageManager.getImage(Images.top_left_extra).draw(oSB, iTranslateX, ImageManager.getImage(Images.top_left).getHeight() + iTranslateY);
         ImageManager.getImage(Images.top_left2_sha)
            .draw2(
               oSB,
               (RTS.isEnabled() ? this.getMenuElement(11).getPosX() : this.getMenuElement(4).getPosX())
                  - ImageManager.getImage(Images.top_left2_sha).getWidth() / 2
                  - CFG.PADDING
                  + iTranslateX,
               -ImageManager.getImage(Images.top_left2_sha).getHeight() + iTranslateY,
               ImageManager.getImage(Images.top_left2_sha).getWidth() / 2
                  + CFG.PADDING
                  + (CFG.GAME_WIDTH - (RTS.isEnabled() ? this.getMenuElement(11).getPosX() : this.getMenuElement(4).getPosX())),
               ImageManager.getImage(Images.top_left2_sha).getHeight()
            );
         ImageManager.getImage(Images.top_left2)
            .draw2(
               oSB,
               (RTS.isEnabled() ? this.getMenuElement(11).getPosX() : this.getMenuElement(4).getPosX())
                  - ImageManager.getImage(Images.top_left2).getWidth() / 2
                  - CFG.PADDING
                  + iTranslateX,
               -ImageManager.getImage(Images.top_left2).getHeight() + iTranslateY,
               ImageManager.getImage(Images.top_left2).getWidth() / 2
                  + CFG.PADDING
                  + (CFG.GAME_WIDTH - (RTS.isEnabled() ? this.getMenuElement(11).getPosX() : this.getMenuElement(4).getPosX())),
               ImageManager.getImage(Images.top_left2).getHeight()
            );
         if (RTS.isEnabled()) {
            draw_Time(
               oSB,
               this.getMenuElement(4).getPosX() + iTranslateX,
               0,
               this.getMenuElement(4).getWidth(),
               ImageManager.getImage(Images.top_left2).getHeight() - 2 - CFG.PADDING
            );
            int tSpeedWidth = (this.getMenuElement(4).getWidth() - CFG.PADDING * 5) / 6;
            int tX = (this.getMenuElement(4).getWidth() - tSpeedWidth * 6 - CFG.PADDING * 5) / 2;

            for (int i = 0; i < RTS.SPEED; i++) {
               draw_Speed(
                  oSB,
                  tX + this.getMenuElement(4).getPosX() + (tSpeedWidth + CFG.PADDING) * i + iTranslateX,
                  ImageManager.getImage(Images.top_left2).getHeight() - 2 - CFG.PADDING,
                  tSpeedWidth,
                  CFG.PADDING
               );
            }
         }

         CFG.game
            .getPlayer(CFG.PLAYER_TURNID)
            .getFlag()
            .draw(
               oSB,
               CFG.topBox.iFlagX + iTranslateX,
               CFG.topBox.iFlagY - CFG.game.getPlayer(CFG.PLAYER_TURNID).getFlag().getHeight() + this.getMenuPosY() + iTranslateY,
               ImageManager.getImage(Images.top_flag_frame).getWidth(),
               ImageManager.getImage(Images.top_flag_frame).getHeight()
            );
         oSB.setColor(
            new Color(
               CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getR() / 255.0F,
               CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getG() / 255.0F,
               CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getB() / 255.0F,
               1.0F
            )
         );
         ImageManager.getImage(Images.top_civ_color_shader)
            .draw(oSB, CFG.topBox.iFlagX + iTranslateX, ImageManager.getImage(Images.top_flag_frame).getHeight() + CFG.topBox.iFlagY + iTranslateY);
         oSB.setColor(Color.WHITE);
         ImageManager.getImage(Images.top_civ_color)
            .draw(oSB, CFG.topBox.iFlagX + iTranslateX, ImageManager.getImage(Images.top_flag_frame).getHeight() + CFG.topBox.iFlagY + iTranslateY);
         super.draw(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
         oSB.setColor(CFG.COLOR_BG_GAME_MENU_SHADOW);
         if (Menu_InGame_ProvinceInfo.iMaxWidth >= 0) {
            ImageManager.getImage(Images.pix255_255_255)
               .draw(
                  oSB,
                  iTranslateX,
                  CFG.GAME_HEIGHT - CFG.map.getMapBG().getMinimapHeight() - 1 + iTranslateY,
                  CFG.map.getMapBG().getMinimapWidth() + Menu_InGame_ProvinceInfo.iMaxWidth + 1
               );
            ImageManager.getImage(Images.line_32_off1)
               .draw(
                  oSB,
                  iTranslateX,
                  CFG.GAME_HEIGHT - CFG.map.getMapBG().getMinimapHeight() - ImageManager.getImage(Images.line_32_off1).getHeight() - 1 + iTranslateY,
                  CFG.map.getMapBG().getMinimapWidth() + Menu_InGame_ProvinceInfo.iMaxWidth + 1,
                  1
               );
         } else {
            ImageManager.getImage(Images.pix255_255_255)
               .draw(oSB, iTranslateX, CFG.GAME_HEIGHT - CFG.map.getMapBG().getMinimapHeight() - 1 + iTranslateY, CFG.GAME_WIDTH);
            ImageManager.getImage(Images.line_32_off1)
               .draw(
                  oSB,
                  iTranslateX,
                  CFG.GAME_HEIGHT - CFG.map.getMapBG().getMinimapHeight() - ImageManager.getImage(Images.line_32_off1).getHeight() - 1 + iTranslateY,
                  CFG.GAME_WIDTH,
                  1
               );
         }

         oSB.setColor(Color.WHITE);
      } else {
         if (CFG.gameAction.getActiveTurnState() == Game_Action.TurnStates.LOADING_NEXT_TURN && TIME_CONTINUE > 0L) {
            CFG.setRender_3(true);
            if (TIME_CONTINUE < System.currentTimeMillis() - 6L) {
               TIME_CONTINUE = -1L;
               Menu_InGame_ProvinceInfo.clickEndTurn();
            }
         }

         int nElemWidthIDx = this.getMenuElement(10).getVisible() ? 10 : 16;
         ImageManager.getImage(Images.top_left)
            .draw2(
               oSB,
               iTranslateX,
               iTranslateY - ImageManager.getImage(Images.top_left).getHeight(),
               this.getMenuElement(nElemWidthIDx).getPosX() + this.getMenuElement(nElemWidthIDx).getWidth(),
               ImageManager.getImage(Images.top_left).getHeight()
            );
         ImageManager.getImage(Images.top_left2_sha)
            .draw2(
               oSB,
               this.getMenuElement(nElemWidthIDx).getPosX() + this.getMenuElement(nElemWidthIDx).getWidth() + iTranslateX,
               -ImageManager.getImage(Images.top_left2_sha).getHeight() + iTranslateY,
               this.getMenuElement(8).getWidth() + this.getMenuElement(9).getWidth() + ImageManager.getImage(Images.top_left2_sha).getWidth() - CFG.PADDING,
               ImageManager.getImage(Images.top_left2_sha).getHeight(),
               true,
               false
            );
         ImageManager.getImage(this.getMenuElement(17).getIsHovered() ? Images.top_left2 : Images.top_left3)
            .draw2(
               oSB,
               this.getMenuElement(9).getPosX() + this.getMenuElement(9).getWidth() + iTranslateX,
               -ImageManager.getImage(Images.top_left3).getHeight() + iTranslateY,
               this.getMenuElement(17).getWidth() - CFG.PADDING + ImageManager.getImage(Images.top_left2).getWidth(),
               ImageManager.getImage(Images.top_left3).getHeight(),
               true,
               false
            );
         ImageManager.getImage(this.getMenuElement(9).getIsHovered() ? Images.top_left2 : Images.top_left3)
            .draw2(
               oSB,
               this.getMenuElement(8).getPosX() + this.getMenuElement(8).getWidth() + iTranslateX,
               -ImageManager.getImage(Images.top_left3).getHeight() + iTranslateY,
               this.getMenuElement(9).getWidth() - CFG.PADDING + ImageManager.getImage(Images.top_left2).getWidth(),
               ImageManager.getImage(Images.top_left3).getHeight(),
               true,
               false
            );
         ImageManager.getImage(this.getMenuElement(8).getIsHovered() ? Images.top_left3 : Images.top_left2)
            .draw2(
               oSB,
               this.getMenuElement(nElemWidthIDx).getPosX() + this.getMenuElement(nElemWidthIDx).getWidth() + iTranslateX,
               -ImageManager.getImage(Images.top_left3).getHeight() + iTranslateY,
               this.getMenuElement(8).getWidth() - CFG.PADDING + ImageManager.getImage(Images.top_left2).getWidth(),
               ImageManager.getImage(Images.top_left3).getHeight(),
               true,
               false
            );
         ImageManager.getImage(Images.top_left2)
            .draw(oSB, this.getMenuElement(nElemWidthIDx).getPosX() + this.getMenuElement(nElemWidthIDx).getWidth() + iTranslateX, iTranslateY, true, false);
         ImageManager.getImage(Images.top_left_extra).draw(oSB, iTranslateX, ImageManager.getImage(Images.top_left).getHeight() + iTranslateY);
         ImageManager.getImage(Images.top_left2_sha)
            .draw2(
               oSB,
               (RTS.isEnabled() ? this.getMenuElement(11).getPosX() : this.getMenuElement(4).getPosX())
                  - ImageManager.getImage(Images.top_left2_sha).getWidth() / 2
                  - CFG.PADDING
                  + iTranslateX,
               -ImageManager.getImage(Images.top_left2_sha).getHeight() + iTranslateY,
               ImageManager.getImage(Images.top_left2_sha).getWidth() / 2
                  + CFG.PADDING
                  + (CFG.GAME_WIDTH - (RTS.isEnabled() ? this.getMenuElement(11).getPosX() : this.getMenuElement(4).getPosX())),
               ImageManager.getImage(Images.top_left2_sha).getHeight()
            );
         ImageManager.getImage(Images.top_left2)
            .draw2(
               oSB,
               (RTS.isEnabled() ? this.getMenuElement(11).getPosX() : this.getMenuElement(4).getPosX())
                  - ImageManager.getImage(Images.top_left2).getWidth() / 2
                  - CFG.PADDING
                  + iTranslateX,
               -ImageManager.getImage(Images.top_left2).getHeight() + iTranslateY,
               ImageManager.getImage(Images.top_left2).getWidth() / 2
                  + CFG.PADDING
                  + (CFG.GAME_WIDTH - (RTS.isEnabled() ? this.getMenuElement(11).getPosX() : this.getMenuElement(4).getPosX())),
               ImageManager.getImage(Images.top_left2).getHeight()
            );
         if (RTS.isEnabled()) {
            draw_Time(
               oSB,
               this.getMenuElement(4).getPosX() + iTranslateX,
               0,
               this.getMenuElement(4).getWidth(),
               ImageManager.getImage(Images.top_left2).getHeight() - 2 - CFG.PADDING
            );
            int tSpeedWidth = (this.getMenuElement(4).getWidth() - CFG.PADDING * 5) / 6;
            int tX = (this.getMenuElement(4).getWidth() - tSpeedWidth * 6 - CFG.PADDING * 5) / 2;

            for (int i = 0; i < RTS.SPEED; i++) {
               draw_Speed(
                  oSB,
                  tX + this.getMenuElement(4).getPosX() + (tSpeedWidth + CFG.PADDING) * i + iTranslateX,
                  ImageManager.getImage(Images.top_left2).getHeight() - 2 - CFG.PADDING,
                  tSpeedWidth,
                  CFG.PADDING
               );
            }
         }

         CFG.game
            .getPlayer(CFG.PLAYER_TURNID)
            .getFlag()
            .draw(
               oSB,
               CFG.topBox.iFlagX + iTranslateX,
               CFG.topBox.iFlagY - CFG.game.getPlayer(CFG.PLAYER_TURNID).getFlag().getHeight() + this.getMenuPosY() + iTranslateY,
               ImageManager.getImage(Images.top_flag_frame).getWidth(),
               ImageManager.getImage(Images.top_flag_frame).getHeight()
            );
         oSB.setColor(
            new Color(
               CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getR() / 255.0F,
               CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getG() / 255.0F,
               CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getB() / 255.0F,
               1.0F
            )
         );
         ImageManager.getImage(Images.top_civ_color_shader)
            .draw(oSB, CFG.topBox.iFlagX + iTranslateX, ImageManager.getImage(Images.top_flag_frame).getHeight() + CFG.topBox.iFlagY + iTranslateY);
         oSB.setColor(Color.WHITE);
         ImageManager.getImage(Images.top_civ_color)
            .draw(oSB, CFG.topBox.iFlagX + iTranslateX, ImageManager.getImage(Images.top_flag_frame).getHeight() + CFG.topBox.iFlagY + iTranslateY);
         super.draw(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
         oSB.setColor(CFG.COLOR_BG_GAME_MENU_SHADOW);
         if (Menu_InGame_ProvinceInfo.iMaxWidth >= 0) {
            ImageManager.getImage(Images.pix255_255_255)
               .draw(
                  oSB,
                  iTranslateX,
                  CFG.GAME_HEIGHT - CFG.map.getMapBG().getMinimapHeight() - 1 + iTranslateY,
                  CFG.map.getMapBG().getMinimapWidth() + Menu_InGame_ProvinceInfo.iMaxWidth + 1
               );
            ImageManager.getImage(Images.line_32_off1)
               .draw(
                  oSB,
                  iTranslateX,
                  CFG.GAME_HEIGHT - CFG.map.getMapBG().getMinimapHeight() - ImageManager.getImage(Images.line_32_off1).getHeight() - 1 + iTranslateY,
                  CFG.map.getMapBG().getMinimapWidth() + Menu_InGame_ProvinceInfo.iMaxWidth + 1,
                  1
               );
         } else {
            ImageManager.getImage(Images.pix255_255_255)
               .draw(oSB, iTranslateX, CFG.GAME_HEIGHT - CFG.map.getMapBG().getMinimapHeight() - 1 + iTranslateY, CFG.GAME_WIDTH);
            ImageManager.getImage(Images.line_32_off1)
               .draw(
                  oSB,
                  iTranslateX,
                  CFG.GAME_HEIGHT - CFG.map.getMapBG().getMinimapHeight() - ImageManager.getImage(Images.line_32_off1).getHeight() - 1 + iTranslateY,
                  CFG.GAME_WIDTH,
                  1
               );
         }

         oSB.setColor(Color.WHITE);
      }
   }

   public static final void clickFlagAction() {
      if (CFG.gameAction.getActiveTurnState() == Game_Action.TurnStates.INPUT_ORDERS) {
         if (!CFG.menuManager.getVisible_InGame_FlagAction()) {
            CFG.game_NextTurnUpdate
               .updateSpendingsOfCiv(
                  CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).iBudget
               );
            if (RTS.isEnabled() && !RTS.PAUSE) {
               RTS.updateTimePast_AfterAction(0.4F);
            }

            if (CFG.menuManager.getVisible_InGame_CivInfo()) {
               CFG.menuManager.setVisible_InGame_CivInfo(!CFG.menuManager.getVisible_InGame_CivInfo());
            }

            int reloadGraph = Menu_InGame_GraphManager.iActiveGraphID;
            Menu_InGame_GraphManager.iActiveGraphID = -1;
            Menu_InGame_GraphManager.setActiveGraphID(reloadGraph);
         }

         CFG.menuManager.setVisible_InGame_FlagAction(!CFG.menuManager.getVisible_InGame_FlagAction());
         if (CFG.menuManager.getVisible_InGame_FlagAction()) {
            CFG.gameAction.hideAllViews();
            if (CFG.chooseProvinceMode) {
               CFG.game.resetChooseProvinceData();
            }

            if (CFG.regroupArmyMode) {
               CFG.game.resetRegroupArmyData();
            }
         } else {
            if (CFG.viewsManager.getActiveViewID() >= 0) {
               CFG.viewsManager.getActiveView().enableViewAction();
            }

            CFG.game.checkProvinceActionMenu();
         }
      }
   }

   @Override
   public final void actionElement(int iID) {
      switch (iID) {
         case 0:
            CFG.map
               .getMapCoordinates()
               .centerToMinimapClick(
                  Touch.getMousePosX() - this.getMenuElement(iID).getPosX() - this.getPosX(),
                  Touch.getMousePosY() - this.getMenuElement(iID).getPosY() - this.getMenuPosY()
               );
            break;
         case 1:
            if (CFG.gameAction.getActiveTurnState() == Game_Action.TurnStates.INPUT_ORDERS) {
               int tBalance = CFG.game_NextTurnUpdate.getBalance(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
               CFG.toast
                  .setInView(
                     CFG.langManager.get("Balance") + ": " + (tBalance > 0 ? "+" : "") + CFG.getNumberWithSpaces("" + tBalance),
                     tBalance > 0 ? CFG.COLOR_TEXT_MODIFIER_POSITIVE : (tBalance == 0 ? CFG.COLOR_TEXT_MODIFIER_NEUTRAL : CFG.COLOR_TEXT_MODIFIER_NEGATIVE2)
                  );
               CFG.toast.setTimeInView(1500);
               CFG.menuManager.setVisible_InGame_Budget(!CFG.menuManager.getVisible_InGame_Budget());
               CFG.menuManager.resetHoverActive();
            } else {
               int tBalance = CFG.game_NextTurnUpdate.getBalance(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
               CFG.toast
                  .setInView(
                     CFG.langManager.get("Balance") + ": " + (tBalance > 0 ? "+" : "") + CFG.getNumberWithSpaces("" + tBalance),
                     tBalance > 0 ? CFG.COLOR_TEXT_MODIFIER_POSITIVE : (tBalance == 0 ? CFG.COLOR_TEXT_MODIFIER_NEUTRAL : CFG.COLOR_TEXT_MODIFIER_NEGATIVE2)
                  );
               CFG.toast.setTimeInView(1500);
            }
            break;
         case 2:
            CFG.toast.setInView(CFG.langManager.get("MovementPoints") + ": " + this.getMenuElement(iID).getText(), CFG.COLOR_INGAME_MOVEMENT);
            break;
         case 3:
         case 8:
            CFG.viewsManager.setActiveViewID(ViewsManager.VIEW_DIPLOMACY_MODE);
            if (CFG.viewsManager.getActiveViewID() == ViewsManager.VIEW_DIPLOMACY_MODE) {
               if (CFG.menuManager.getVisible_InGame_FlagAction()) {
                  CFG.menuManager.setVisible_InGame_FlagAction(false);
               }

               if (CFG.menuManager.getInGame_Budget().getVisible()) {
                  CFG.menuManager.getInGame_Budget().setVisible(false);
               }

               CFG.menuManager.setVisible_InGame_CivInfo(true);
               CFG.viewsManager.getActiveView().updateActiveCivInfo_ExtraAction(CFG.getActiveCivInfo());
            } else {
               CFG.menuManager.setVisible_InGame_CivInfo(false);
            }
            break;
         case 4:
         case 5:
            if (RTS.isEnabled()) {
               if (!RTS.PAUSE) {
                  RTS.updateTimePast_AfterAction(0.75F);
               }

               RTS.pauseUnpause();
            } else {
               if (CFG.menuManager.getVisibleInGame_History()) {
                  CFG.menuManager.setVisibleInGame_History(false);
               } else {
                  CFG.menuManager.rebuildInGame_History();
               }

               ArrayList<String> tempMess = new ArrayList<>();
               ArrayList<Color> tempColor = new ArrayList<>();
               tempMess.add(CFG.gameAges.getAge(Game_Calendar.CURRENT_AGEID).getName());
               tempColor.add(CFG.COLOR_TEXT_RANK);
               tempMess.add(Game_Calendar.getCurrentDate());
               tempColor.add(CFG.COLOR_TEXT_CIV_NAME);
               CFG.toast.setInView(tempMess, tempColor);
            }
            break;
         case 6:
            if (CFG.menuManager.getVisibleInGame_Rank()) {
               CFG.menuManager.setVisibleInGame_Rank(false);
            } else {
               CFG.menuManager.rebuildInGame_Rank();
            }
            break;
         case 7:
            clickFlagAction();
            if (CFG.gameAction.getActiveTurnState() == Game_Action.TurnStates.INPUT_ORDERS) {
               this.getMenuElement(iID).buildElementHover();
            }
            break;
         case 9:
            CFG.menuManager.setVisible_InGame_MapModes(!CFG.menuManager.getInGame_MapModes().getVisible());
            if (CFG.menuManager.getInGame_MapModes().getPosX() < 0) {
               if (CFG.isAndroid()) {
                  CFG.glyphLayout.setText(CFG.fontMain, "+100% ");
                  int tempMaxTextW = (int)(CFG.glyphLayout.width * 0.7F);
                  int tMenuWidth = ImageManager.getImage(Images.diplo_war).getWidth() / 2
                     + CFG.PADDING
                     + CFG.CIV_FLAG_WIDTH
                     + CFG.PADDING
                     + tempMaxTextW
                     + CFG.PADDING;
                  CFG.menuManager
                     .getInGame_MapModes()
                     .setPosX_Force(CFG.GAME_WIDTH - CFG.menuManager.getInGame_MapModes().getWidth() - CFG.PADDING - tMenuWidth);
                  CFG.menuManager
                     .getInGame_MapModes()
                     .setPosY(
                        CFG.menuManager.getInGame_MapModes().getTitle().getHeight()
                           + this.getMenuElement(iID).getPosY()
                           + this.getMenuElement(iID).getHeight()
                           + CFG.PADDING
                     );
                  if (CFG.menuManager.getInGame_MapModes().getPosX() + CFG.menuManager.getInGame_MapModes().getWidth() > CFG.GAME_WIDTH - CFG.PADDING) {
                     CFG.menuManager.getInGame_MapModes().setPosX_Force(CFG.GAME_WIDTH - CFG.PADDING - CFG.menuManager.getInGame_MapModes().getWidth());
                  }
               } else {
                  CFG.menuManager
                     .getInGame_MapModes()
                     .setPosX_Force(
                        this.getMenuElement(iID).getPosX() + this.getMenuElement(iID).getWidth() / 2 - CFG.menuManager.getInGame_MapModes().getWidth() / 2
                     );
                  CFG.menuManager
                     .getInGame_MapModes()
                     .setPosY(
                        CFG.menuManager.getInGame_MapModes().getTitle().getHeight()
                           + this.getMenuElement(iID).getPosY()
                           + this.getMenuElement(iID).getHeight()
                           + CFG.PADDING
                     );
                  if (CFG.menuManager.getInGame_MapModes().getPosX() + CFG.menuManager.getInGame_MapModes().getWidth() > CFG.GAME_WIDTH - CFG.PADDING) {
                     CFG.menuManager.getInGame_MapModes().setPosX_Force(CFG.GAME_WIDTH - CFG.PADDING - CFG.menuManager.getInGame_MapModes().getWidth());
                  }
               }
            }
            break;
         case 10:
            CFG.map.getMapCoordinates().centerToProvinceID(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCapitalProvinceID());
            break;
         case 11:
            RTS.updateSpeed(-1);
            break;
         case 12:
            RTS.updateSpeed(1);
            break;
         case 13:
            CFG.toast.setInView(CFG.langManager.get("NuclearWeapons") + ": " + this.getMenuElement(iID).getText(), Color.ORANGE);
            break;
         case 14:
            CFG.toast.setInView(CFG.langManager.get("MilXP") + ": " + this.getMenuElement(iID).getText(), CFG.COLOR_TEXT_MODIFIER_POSITIVE_ACTIVE);
            if (CFG.menuManager.getVisibleInGame_UpgradingArmy()) {
               CFG.menuManager.setVisibleInGame_UpgradingArmy(false);
            } else {
               CFG.menuManager.rebuildInGame_UpgradingArmy();
            }
            break;
         case 15:
            CFG.toast.setInView(CFG.langManager.get("Surrender") + ": " + this.getMenuElement(iID).getText(), CFG.COLOR_TEXT_MODIFIER_POSITIVE_ACTIVE);
            break;
         case 16:
            CFG.toast.setInView(CFG.langManager.get("ManPower") + ": " + this.getMenuElement(iID).getText(), CFG.COLOR_FORT_2);
            if (CFG.menuManager.getVisibleInGame_UpgradingArmy()) {
               CFG.menuManager.setVisibleInGame_UpgradingArmy(false);
            } else {
               CFG.menuManager.rebuildInGame_UpgradingArmy();
            }
         case 17:
         case 18:
         case 19:
         case 20:
         case 21:
         case 22:
         case 23:
         case 24:
         case 25:
         case 26:
         case 27:
         case 28:
         case 29:
         case 30:
         case 31:
         default:
            break;
         case 32:
            if (!CFG.menuManager.getVisible_InGame_SandBoxMenu()) {
               if (CFG.menuManager.getVisible_InGame_FlagAction()) {
                  CFG.menuManager.setVisible_InGame_FlagAction(false);
               }

               if (CFG.menuManager.getInGame_Budget().getVisible()) {
                  CFG.menuManager.getInGame_Budget().setVisible(false);
               }

               CFG.menuManager.setVisible_InGame_SandBoxMenu(true, true);
            } else {
               CFG.menuManager.setVisible_InGame_SandBoxMenu(false, false);
            }
      }
   }

   @Override
   public final void onBackPressed() {
      CFG.menuManager.setVisible_InGame_Options(true);
   }

   @Override
   public void onHovered() {
      CFG.menuManager.setOrderOfMenu_InGame();
   }
}
