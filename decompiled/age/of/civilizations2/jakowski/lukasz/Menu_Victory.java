package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

public class Menu_Victory extends SliderMenu {
   public static boolean VICTORIOUS = false;
   public static final int ANIMATION_TIME = 1000;
   public long lTime = 0L;
   public static final int ANIMATION_TIME_TOP = 725;
   public long lTimeTOP = 0L;
   public static final int ANIMATION_TIME_TOP2 = 2750;
   public long lTimeTOP2 = 0L;
   public boolean backAnimation = false;
   public boolean hideTop = false;
   public String sTopText;
   public int iTopTextWidth = 0;
   public final float FONT_DATE_SCALE = 0.7F;
   public String sTopTextDate;
   public int iTopTextDateWidth = 0;
   public Color topColorBG;

   public Menu_Victory(boolean VICTORIOUS) {
      Menu_Victory.VICTORIOUS = VICTORIOUS;
      this.sTopText = VICTORIOUS ? CFG.langManager.get("Victory") : CFG.langManager.get("Defeat");
      CFG.glyphLayout.setText(CFG.fontMain, this.sTopText);
      this.iTopTextWidth = (int)CFG.glyphLayout.width;
      this.sTopTextDate = Game_Calendar.getDate_ByTurnID(1) + " - " + Game_Calendar.getCurrentDate();
      CFG.glyphLayout.setText(CFG.fontMain, this.sTopTextDate);
      this.iTopTextDateWidth = (int)(CFG.glyphLayout.width * 0.7F);
      this.topColorBG = VICTORIOUS ? Color.WHITE : CFG.COLOR_TEXT_MODIFIER_NEGATIVE2;
      this.backAnimation = false;
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      menuElements.add(
         new Text(null, 0, 0, CFG.PADDING, ImageManager.getImage(Images.top_left2).getHeight()) {
            @Override
            public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
               CFG.fontMain.getData().setScale(0.8F);
               CFG.drawTextWithShadow(
                  oSB,
                  this.sText,
                  this.getPosX() + this.getWidth() / 2 - (int)(this.getTextWidth() * 0.8F) / 2 + iTranslateX,
                  this.getPosY() + this.getHeight() / 2 - (int)(this.getTextHeight() * 0.8F / 2.0F) + 1 + iTranslateY,
                  this.getColor(isActive)
               );
               CFG.fontMain.getData().setScale(1.0F);
            }

            @Override
            public Color getColor(boolean isActive) {
               return isActive
                  ? CFG.COLOR_BUTTON_GAME_TEXT_IMPORTANT_ACTIVE
                  : (
                     this.getClickable()
                        ? (this.getIsHovered() ? CFG.COLOR_BUTTON_GAME_TEXT_IMPORTANT_HOVER : CFG.COLOR_BUTTON_GAME_TEXT_IMPORTANT)
                        : CFG.COLOR_BUTTON_GAME_TEXT_NOT_CLICKABLE
                  );
            }

            @Override
            public int getPosY() {
               return CFG.GAME_HEIGHT - this.getHeight();
            }

            @Override
            public int getWidth() {
               return Math.max(CFG.BUTTON_WIDTH + CFG.BUTTON_WIDTH / 2, (int)(this.getTextWidth() * 0.8F) + CFG.PADDING * 4);
            }

            @Override
            public int getSFX() {
               return SoundsManager.SOUND_CLICK2;
            }
         }
      );
      menuElements.add(
         new Text("Date", 0, 0, CFG.PADDING, ImageManager.getImage(Images.top_left2).getHeight()) {
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
                  - ImageManager.getImage(Images.top_left2).getHeight()
                  - CFG.PADDING
                  - Math.max(CFG.BUTTON_WIDTH + CFG.BUTTON_WIDTH / 2, (int)(this.getTextWidth() * 0.8F) + CFG.PADDING * 4);
            }

            @Override
            public int getWidth() {
               return Math.max(CFG.BUTTON_WIDTH + CFG.BUTTON_WIDTH / 2, (int)(this.getTextWidth() * 0.8F) + CFG.PADDING * 4);
            }

            @Override
            public int getHeight() {
               return (int)(CFG.TEXT_HEIGHT * 0.8F) + CFG.PADDING;
            }

            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               if (TimelapseManager.PAUSE) {
                  nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("ClickToUnpause"), CFG.COLOR_TEXT_NUM_OF_PROVINCES));
                  nElements.add(new MenuElement_Hover_v2_Element2(nData));
                  nData.clear();
               } else {
                  nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("ClickToPause"), CFG.COLOR_TEXT_NUM_OF_PROVINCES));
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

      for (int i = 0; i < 60 && !(CFG.TEXT_HEIGHT * Menu_InGame.fTurnScale <= tempTurnH); i++) {
         Menu_InGame.fTurnScale -= 0.01F;
      }

      menuElements.add(
         new Text("Turn", 0, 0, CFG.PADDING * 2 + (int)(CFG.TEXT_HEIGHT * 0.8F), ImageManager.getImage(Images.top_left2).getHeight()) {
            @Override
            public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
               CFG.fontMain.getData().setScale(Menu_InGame.fTurnScale);
               CFG.drawTextWithShadow(
                  oSB,
                  this.getText(),
                  this.getPosX() + (int)((this.getWidth() - this.getTextWidth() * Menu_InGame.fTurnScale) / 2.0F) + iTranslateX,
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
               return Menu_Victory.this.getMenuElement(1).getPosX();
            }

            @Override
            public int getWidth() {
               return Menu_Victory.this.getMenuElement(1).getWidth();
            }

            @Override
            public int getHeight() {
               return (int)(CFG.TEXT_HEIGHT * Menu_InGame.fTurnScale);
            }

            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               if (TimelapseManager.PAUSE) {
                  nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("ClickToUnpause"), CFG.COLOR_TEXT_NUM_OF_PROVINCES));
                  nElements.add(new MenuElement_Hover_v2_Element2(nData));
                  nData.clear();
               } else {
                  nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("ClickToPause"), CFG.COLOR_TEXT_NUM_OF_PROVINCES));
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
      menuElements.add(
         new Button_Speed(
            "-", -1, 0, 0, ImageManager.getImage(Images.top_left2).getHeight() + CFG.PADDING, ImageManager.getImage(Images.top_left2).getHeight() - 2, true
         ) {
            @Override
            public int getPosX() {
               return Menu_Victory.this.getMenuElement(1).getPosX() - this.getWidth();
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
            public void drawMenuElementHover2(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
               if (this.menuElementHover != null) {
                  this.menuElementHover.drawAlwaysBelow(oSB, Touch.getMousePosX(), ImageManager.getImage(Images.top_left2).getHeight());
               }
            }
         }
      );
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
         }
      );
      menuElements.add(
         new Button_VictoryStats(
            CFG.langManager.get("Income") + ": ",
            CFG.getNumberWithSpaces("75148"),
            CFG.COLOR_INGAME_GOLD,
            Images.top_gold,
            0,
            CFG.GAME_HEIGHT
               - menuElements.get(0).getHeight()
               - CFG.PADDING * 2
               - Math.max(CFG.TEXT_HEIGHT + CFG.PADDING * 4, CFG.CIV_FLAG_HEIGHT + CFG.PADDING * 2),
            CFG.CIV_INFO_MENU_WIDTH * 3 / 4,
            Math.max(CFG.TEXT_HEIGHT + CFG.PADDING * 4, CFG.CIV_FLAG_HEIGHT + CFG.PADDING * 2),
            true
         )
      );
      menuElements.add(
         new Button_VictoryStats(
            CFG.langManager.get("Provinces") + ": ",
            "4",
            CFG.COLOR_TEXT_NUM_OF_PROVINCES,
            Images.provinces,
            0,
            menuElements.get(menuElements.size() - 1).getPosY() - Math.max(CFG.TEXT_HEIGHT + CFG.PADDING * 4, CFG.CIV_FLAG_HEIGHT + CFG.PADDING * 2),
            CFG.CIV_INFO_MENU_WIDTH * 3 / 4,
            Math.max(CFG.TEXT_HEIGHT + CFG.PADDING * 4, CFG.CIV_FLAG_HEIGHT + CFG.PADDING * 2),
            true
         )
      );
      this.initMenu(null, 0, 0, CFG.GAME_WIDTH, CFG.GAME_HEIGHT, menuElements);
      this.lTime = System.currentTimeMillis();
      this.lTimeTOP = System.currentTimeMillis();
      this.lTimeTOP2 = 0L;
      TimelapseManager.SPEED = 6;
      CFG.timelapseManager.pauseUnpause();
      CFG.map.getMapCoordinates().centerToCivilizationBox_Timeline(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), false);
      this.updateLanguage();
      this.getMenuElement(5).setCurrent(0);
      this.getMenuElement(6).setCurrent(1);
   }

   @Override
   public void updateLanguage() {
      this.getMenuElement(1).setText(Game_Calendar.getDate_ByTurnID(CFG.timelapseManager.iTimelineTurnID + 1));
      this.getMenuElement(2).setText(CFG.langManager.get("Turn") + ": " + (CFG.timelapseManager.iTimelineTurnID + 1));
      this.getMenuElement(0).setText(CFG.langManager.get("Continue"));
      this.updateTurnData();
   }

   public void updateTurnData() {
      this.getMenuElement(5)
         .setText(CFG.getNumberWithSpaces("" + CFG.timelapseManager.getPlayerIncome(CFG.PLAYER_TURNID, CFG.timelapseManager.iTimelineTurnID)));
      this.getMenuElement(6).setText(CFG.getNumberWithSpaces("" + CFG.timelapseManager.getNumOfProvinces(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())));
   }

   @Override
   public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      CFG.setRender_3(true);
      if (!TimelapseManager.PAUSE) {
         CFG.timelapseManager.updateTime();
         this.updateTurnData();
      }

      if (this.lTime + 1000L >= System.currentTimeMillis()) {
         oSB.setColor(
            new Color(
               CFG.COLOR_GRADIENT_DARK_BLUE.r,
               CFG.COLOR_GRADIENT_DARK_BLUE.g,
               CFG.COLOR_GRADIENT_DARK_BLUE.b,
               0.05F + 0.325F * ((float)(System.currentTimeMillis() - this.lTime) / 1000.0F)
            )
         );
      } else {
         oSB.setColor(new Color(CFG.COLOR_GRADIENT_DARK_BLUE.r, CFG.COLOR_GRADIENT_DARK_BLUE.g, CFG.COLOR_GRADIENT_DARK_BLUE.b, 0.375F));
      }

      ImageManager.getImage(Images.gradient)
         .draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.gradient).getHeight(), this.getWidth(), this.getTopHeight());
      ImageManager.getImage(Images.gradient)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() + this.getHeight() - this.getTopHeight() / 4 - ImageManager.getImage(Images.gradient).getHeight(),
            this.getWidth(),
            this.getTopHeight() / 4,
            false,
            true
         );
      if (this.lTime + 1000L >= System.currentTimeMillis()) {
         oSB.setColor(
            new Color(
               CFG.COLOR_GRADIENT_DARK_BLUE.r,
               CFG.COLOR_GRADIENT_DARK_BLUE.g,
               CFG.COLOR_GRADIENT_DARK_BLUE.b,
               0.05F + 0.075F * ((float)(System.currentTimeMillis() - this.lTime) / 1000.0F)
            )
         );
         CFG.setRender_3(true);
      } else {
         oSB.setColor(new Color(CFG.COLOR_GRADIENT_DARK_BLUE.r, CFG.COLOR_GRADIENT_DARK_BLUE.g, CFG.COLOR_GRADIENT_DARK_BLUE.b, 0.125F));
      }

      ImageManager.getImage(Images.slider_gradient)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight(),
            this.getTopHeight() / 2,
            this.getHeight()
         );
      ImageManager.getImage(Images.slider_gradient)
         .draw(
            oSB,
            this.getPosX() + this.getWidth() - this.getTopHeight() / 4 + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight(),
            this.getTopHeight() / 4,
            this.getHeight(),
            true,
            false
         );
      oSB.setColor(new Color(1.0F, 1.0F, 1.0F, 0.125F));
      ImageManager.getImage(Images.gameLogo)
         .draw(
            oSB,
            this.getPosX() + this.getWidth() - CFG.PADDING - ImageManager.getImage(Images.gameLogo).getWidth() + iTranslateX,
            this.getPosY() + this.getHeight() - CFG.PADDING - ImageManager.getImage(Images.gameLogo).getHeight()
         );
      oSB.setColor(Color.WHITE);
      ImageManager.getImage(Images.top_left2_sha)
         .draw2(
            oSB,
            this.getMenuElement(3).getPosX() - ImageManager.getImage(Images.top_left2_sha).getWidth() / 2 - CFG.PADDING + iTranslateX,
            -ImageManager.getImage(Images.top_left2_sha).getHeight() + iTranslateY,
            ImageManager.getImage(Images.top_left2_sha).getWidth() / 2 + CFG.PADDING + (CFG.GAME_WIDTH - this.getMenuElement(3).getPosX()),
            ImageManager.getImage(Images.top_left2_sha).getHeight()
         );
      ImageManager.getImage(Images.top_left2)
         .draw2(
            oSB,
            this.getMenuElement(3).getPosX() - ImageManager.getImage(Images.top_left2).getWidth() / 2 - CFG.PADDING + iTranslateX,
            -ImageManager.getImage(Images.top_left2).getHeight() + iTranslateY,
            ImageManager.getImage(Images.top_left2).getWidth() / 2 + CFG.PADDING + (CFG.GAME_WIDTH - this.getMenuElement(3).getPosX()),
            ImageManager.getImage(Images.top_left2).getHeight()
         );
      draw_Time(
         oSB,
         this.getMenuElement(1).getPosX() + iTranslateX,
         0,
         this.getMenuElement(1).getWidth(),
         ImageManager.getImage(Images.top_left2).getHeight() - 2 - CFG.PADDING
      );
      int tSpeedWidth = (this.getMenuElement(1).getWidth() - CFG.PADDING * 5) / 6;
      int tX = (this.getMenuElement(1).getWidth() - tSpeedWidth * 6 - CFG.PADDING * 5) / 2;

      for (int i = 0; i < TimelapseManager.SPEED; i++) {
         Menu_InGame.draw_Speed(
            oSB,
            tX + this.getMenuElement(4).getPosX() + (tSpeedWidth + CFG.PADDING) * i + iTranslateX,
            ImageManager.getImage(Images.top_left2).getHeight() - 2 - CFG.PADDING,
            tSpeedWidth,
            CFG.PADDING
         );
      }

      oSB.setColor(Color.WHITE);
      ImageManager.getImage(Images.top_left2_sha)
         .draw2(
            oSB,
            this.getMenuElement(0).getPosX() + iTranslateX,
            this.getMenuElement(0).getPosY() - ImageManager.getImage(Images.top_left2_sha).getHeight() + iTranslateY,
            ImageManager.getImage(Images.top_left2_sha).getWidth() / 2 + CFG.PADDING + this.getMenuElement(0).getWidth(),
            ImageManager.getImage(Images.top_left2_sha).getHeight(),
            true,
            true
         );
      if (this.getMenuElement(0).getIsHovered()) {
         ImageManager.getImage(Images.top_left3)
            .draw2(
               oSB,
               this.getMenuElement(0).getPosX() + iTranslateX,
               this.getMenuElement(0).getPosY() - ImageManager.getImage(Images.top_left3).getHeight() + iTranslateY,
               ImageManager.getImage(Images.top_left3).getWidth() / 2 + CFG.PADDING + this.getMenuElement(0).getWidth(),
               ImageManager.getImage(Images.top_left3).getHeight(),
               true,
               true
            );
      } else {
         ImageManager.getImage(Images.top_left2)
            .draw2(
               oSB,
               this.getMenuElement(0).getPosX() + iTranslateX,
               this.getMenuElement(0).getPosY() - ImageManager.getImage(Images.top_left2).getHeight() + iTranslateY,
               ImageManager.getImage(Images.top_left2).getWidth() / 2 + CFG.PADDING + this.getMenuElement(0).getWidth(),
               ImageManager.getImage(Images.top_left2).getHeight(),
               true,
               true
            );
      }

      super.draw(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
      int extraY = 0;
      if (this.backAnimation && this.lTimeTOP2 + 2750L >= System.currentTimeMillis()) {
         this.lTimeTOP = System.currentTimeMillis();
      } else if (this.lTimeTOP + 725L >= System.currentTimeMillis()) {
         extraY = this.backAnimation
            ? (int)(-this.getVictoryPosMax() * ((float)(System.currentTimeMillis() - this.lTimeTOP) / 725.0F))
            : (int)(-this.getVictoryPosMax() + this.getVictoryPosMax() * ((float)(System.currentTimeMillis() - this.lTimeTOP) / 725.0F));
      } else if (!this.backAnimation) {
         this.backAnimation = true;
         this.lTimeTOP = System.currentTimeMillis();
         this.lTimeTOP2 = System.currentTimeMillis();
      } else {
         this.hideTop = true;
      }

      if (!this.hideTop) {
         this.drawVictory(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() + CFG.BUTTON_HEIGHT / 4 + extraY + iTranslateY,
            sliderMenuIsActive,
            CFG.TEXT_HEIGHT + CFG.PADDING * 6
         );
      }
   }

   public int getVictoryPosMax() {
      return CFG.BUTTON_HEIGHT / 4 + CFG.TEXT_HEIGHT + CFG.PADDING * 6 + CFG.PADDING * 2 + 0;
   }

   public static final void draw_Time(SpriteBatch oSB, int nPosX, int nPosY, int nWidth, int nHeight) {
      oSB.setColor(new Color(CFG.COLOR_GRADIENT_TITLE_BLUE.r, CFG.COLOR_GRADIENT_TITLE_BLUE.g, CFG.COLOR_GRADIENT_TITLE_BLUE.b, 1.0F));
      ImageManager.getImage(Images.patt2).draw2(oSB, nPosX, nPosY - ImageManager.getImage(Images.patt2).getHeight(), nWidth, nHeight);
      ImageManager.getImage(Images.patt2).draw2(oSB, nPosX, nPosY - ImageManager.getImage(Images.patt2).getHeight(), nWidth, nHeight);
      oSB.setColor(new Color(1.0F, 1.0F, 1.0F, 1.0F));
      ImageManager.getImage(Images.patt2)
         .draw2(
            oSB,
            nPosX,
            nPosY - ImageManager.getImage(Images.patt2).getHeight(),
            (int)(nWidth * CFG.timelapseManager.getTimePerc()),
            nHeight,
            0,
            TimelapseManager.SOURCE
         );
      oSB.setColor(new Color(1.0F, 1.0F, 1.0F, 0.75F));
      ImageManager.getImage(Images.patt2)
         .draw2(
            oSB,
            nPosX,
            nPosY - ImageManager.getImage(Images.patt2).getHeight(),
            (int)(nWidth * CFG.timelapseManager.getTimePerc()),
            nHeight,
            0,
            TimelapseManager.SOURCE
         );
      if (!TimelapseManager.PAUSE) {
         TimelapseManager.SOURCE--;
      }

      oSB.setColor(new Color(CFG.COLOR_GRADIENT_TITLE_BLUE.r, CFG.COLOR_GRADIENT_TITLE_BLUE.g, CFG.COLOR_GRADIENT_TITLE_BLUE.b, 0.4F));
      ImageManager.getImage(Images.gradient).draw(oSB, nPosX, nPosY - ImageManager.getImage(Images.gradient).getHeight(), nWidth, nHeight, false, true);
      oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.45F));
      ImageManager.getImage(Images.gradient).draw(oSB, nPosX, nPosY - ImageManager.getImage(Images.gradient).getHeight(), nWidth, CFG.PADDING);
      oSB.setColor(Color.WHITE);
   }

   public final void drawVictory(SpriteBatch oSB, int nX, int nY, boolean sliderMenuIsActive, int titleH) {
      oSB.setColor(new Color(CFG.COLOR_GRADIENT_DARK_BLUE));
      ImageManager.getImage(Images.line_32_off1).draw(oSB, nX, nY - ImageManager.getImage(Images.line_32_off1).getHeight(), this.getWidth(), titleH);
      oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.625F));
      ImageManager.getImage(Images.line_32_off1).draw(oSB, nX, nY - 1 - ImageManager.getImage(Images.line_32_off1).getHeight(), this.getWidth(), 1);
      ImageManager.getImage(Images.line_32_off1).draw(oSB, nX, nY + titleH - ImageManager.getImage(Images.line_32_off1).getHeight(), this.getWidth(), 1);
      oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME));
      ImageManager.getImage(Images.line_32_off1).draw(oSB, nX, nY - ImageManager.getImage(Images.line_32_off1).getHeight(), this.getWidth(), 1);
      ImageManager.getImage(Images.line_32_off1).draw(oSB, nX, nY + titleH - 1 - ImageManager.getImage(Images.line_32_off1).getHeight(), this.getWidth(), 1);
      oSB.setColor(Color.WHITE);
      CFG.drawTextWithShadow(oSB, this.sTopText, nX + this.getWidth() / 2 - this.iTopTextWidth / 2, nY + titleH / 2 - CFG.TEXT_HEIGHT / 2, this.topColorBG);
      CFG.fontMain.getData().setScale(0.7F);
      CFG.drawText(
         oSB,
         this.sTopTextDate,
         nX + this.getWidth() / 2 - this.iTopTextDateWidth / 2,
         nY + titleH + CFG.PADDING + CFG.PADDING / 2,
         new Color(CFG.COLOR_TEXT_CNG_TOP_SCENARIO_NAME.r, CFG.COLOR_TEXT_CNG_TOP_SCENARIO_NAME.g, CFG.COLOR_TEXT_CNG_TOP_SCENARIO_NAME.b, 0.425F)
      );
      CFG.fontMain.getData().setScale(1.0F);
   }

   public int getTopHeight() {
      return CFG.BUTTON_HEIGHT + CFG.PADDING * 4;
   }

   public static final void clickBack() {
      RTS.resetTime();
      RTS.PAUSE = true;
      CFG.menuManager.setViewIDWithoutAnimation(Menu.eINGAME);
      CFG.map.getMapScroll().stopScrollingTheMap();
      CFG.map.getMapBG().updateWorldMap_Shaders();
   }

   @Override
   public final void actionElement(int iID) {
      switch (iID) {
         case 0:
            if (!TimelapseManager.PAUSE) {
               CFG.timelapseManager.pauseUnpause();
            }

            CFG.setDialogType(Dialog.CONTINUE_AFTER_END_GAME);
         default:
            return;
         case 1:
         case 2:
            CFG.timelapseManager.pauseUnpause();
            CFG.map.getMapCoordinates().centerToCivilizationBox_Timeline(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), false);
            return;
         case 3:
            CFG.timelapseManager.updateSpeed(-1);
            return;
         case 4:
            CFG.timelapseManager.updateSpeed(1);
      }
   }

   @Override
   public final void onBackPressed() {
   }

   @Override
   public void onMenuPressed() {
   }
}
