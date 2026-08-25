package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

public class Menu_Settings_Province extends SliderMenu {
   public String sScale;

   public Menu_Settings_Province() {
      int tempW = CFG.CIV_INFO_MENU_WIDTH + CFG.CIV_INFO_MENU_WIDTH * 3 / 4;
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      int tPosY = CFG.PADDING;
      menuElements.add(new Button_NewGameStyle(null, -1, CFG.PADDING, tPosY, tempW - CFG.PADDING * 2, (int)(CFG.BUTTON_HEIGHT * 0.6F), true));
      int var4;
      menuElements.add(
         new Button_NewGameStyle_Left(
            "-",
            -1,
            CFG.PADDING,
            var4 = tPosY + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING,
            CFG.BUTTON_HEIGHT,
            (int)(CFG.BUTTON_HEIGHT * 0.6F),
            true
         )
      );
      menuElements.add(
         new Button_NewGameStyle_Middle(
            null, -1, CFG.PADDING + CFG.BUTTON_HEIGHT, var4, tempW - CFG.PADDING * 2 - CFG.BUTTON_HEIGHT * 2, (int)(CFG.BUTTON_HEIGHT * 0.6F), true
         )
      );
      menuElements.add(
         new Button_NewGameStyle_Right(
            "+",
            -1,
            CFG.PADDING + CFG.BUTTON_HEIGHT + (tempW - CFG.PADDING * 2 - CFG.BUTTON_HEIGHT * 2),
            var4,
            CFG.BUTTON_HEIGHT,
            (int)(CFG.BUTTON_HEIGHT * 0.6F),
            true
         )
      );
      menuElements.add(
         new Slider_FlagAction_Clear(
            "",
            CFG.PADDING * 2,
            tPosY = var4 + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING * 2,
            tempW - CFG.PADDING * 4,
            (int)(CFG.BUTTON_HEIGHT * 0.8F),
            25,
            255,
            CFG.settingsManager.PROVINCE_ALPHA
         )
      );
      int var6;
      menuElements.add(
         new Slider_FlagAction_Clear(
            "",
            CFG.PADDING * 2,
            var6 = tPosY + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING * 2,
            tempW - CFG.PADDING * 4,
            (int)(CFG.BUTTON_HEIGHT * 0.8F),
            100,
            400,
            (int)(CFG.settingsManager.STOP_SCALING_ARMY * 100.0F)
         ) {
            @Override
            public String getDrawText() {
               return "" + this.getCurrent() / 100.0F;
            }
         }
      );
      menuElements.add(
         new Text_BudgetTitle(
            "", -1, 2, tPosY = var6 + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING, tempW - 2, CFG.TEXT_HEIGHT + CFG.PADDING * 4
         ) {
            @Override
            public Color getColor(boolean isActive) {
               return isActive
                  ? CFG.COLOR_TEXT_OPTIONS_NS_HOVER
                  : (this.getClickable() ? (this.getIsHovered() ? CFG.COLOR_TEXT_OPTIONS_NS : Color.WHITE) : new Color(0.78F, 0.78F, 0.78F, 0.7F));
            }
         }
      );
      int var8;
      menuElements.add(
         new Slider_FlagAction_Clear(
            "",
            CFG.PADDING * 2,
            var8 = tPosY + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING * 2,
            tempW - CFG.PADDING * 4,
            (int)(CFG.BUTTON_HEIGHT * 0.8F),
            0,
            600,
            (int)(CFG.settingsManager.BORDER_WIDTH * 100.0F)
         ) {
            @Override
            public String getDrawText() {
               return "" + this.getCurrent() / 100.0F;
            }
         }
      );
      menuElements.add(
         new Slider_FlagAction_Clear(
            "",
            CFG.PADDING * 2,
            tPosY = var8 + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING * 2,
            tempW - CFG.PADDING * 4,
            (int)(CFG.BUTTON_HEIGHT * 0.8F),
            0,
            600,
            (int)(CFG.settingsManager.BORDER_HEIGHT * 100.0F)
         ) {
            @Override
            public String getDrawText() {
               return "" + this.getCurrent() / 100.0F;
            }
         }
      );
      int var10;
      menuElements.add(
         new Button_NewGameStyle(
            null,
            -1,
            CFG.PADDING,
            var10 = tPosY + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING * 2,
            tempW - CFG.PADDING * 2,
            true,
            CFG.settingsManager.ENABLE_INNER_BORDERS
         ) {
            @Override
            public boolean getCheckboxState() {
               return CFG.settingsManager.ENABLE_INNER_BORDERS;
            }
         }
      );
      menuElements.add(
         new Text_BudgetTitle(
            "", -1, 2, tPosY = var10 + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING, tempW - 2, CFG.TEXT_HEIGHT + CFG.PADDING * 4
         ) {
            @Override
            public Color getColor(boolean isActive) {
               return isActive
                  ? CFG.COLOR_TEXT_OPTIONS_NS_HOVER
                  : (this.getClickable() ? (this.getIsHovered() ? CFG.COLOR_TEXT_OPTIONS_NS : Color.WHITE) : new Color(0.78F, 0.78F, 0.78F, 0.7F));
            }
         }
      );
      int var12;
      menuElements.add(
         new Slider_FlagAction_Clear(
            "",
            CFG.PADDING * 2,
            var12 = tPosY + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING,
            tempW - CFG.PADDING * 4,
            (int)(CFG.BUTTON_HEIGHT * 0.8F),
            0,
            100,
            CFG.settingsManager.PERCETANGE_OF_CITIES_ON_MAP
         ) {
            @Override
            public String getDrawText() {
               return super.getDrawText() + "%";
            }
         }
      );
      menuElements.add(
         new Slider_FlagAction_Clear(
            "",
            CFG.PADDING * 2,
            tPosY = var12 + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING * 2,
            tempW - CFG.PADDING * 4,
            (int)(CFG.BUTTON_HEIGHT * 0.8F),
            10,
            200,
            (int)(CFG.settingsManager.CITIES_FONT_SCALE * 100.0F)
         ) {
            @Override
            public String getDrawText() {
               return super.getDrawText() + "%";
            }
         }
      );
      int var14;
      menuElements.add(
         new Text_BudgetTitle(
            "", -1, 2, var14 = tPosY + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING * 2, tempW - 2, CFG.TEXT_HEIGHT + CFG.PADDING * 4
         ) {
            @Override
            public Color getColor(boolean isActive) {
               return isActive
                  ? CFG.COLOR_TEXT_OPTIONS_NS_HOVER
                  : (this.getClickable() ? (this.getIsHovered() ? CFG.COLOR_TEXT_OPTIONS_NS : Color.WHITE) : new Color(0.78F, 0.78F, 0.78F, 0.7F));
            }
         }
      );
      menuElements.add(
         new Button_NewGameStyle(
            null,
            -1,
            CFG.PADDING,
            tPosY = var14 + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING,
            tempW - CFG.PADDING * 2,
            true,
            CFG.settingsManager.DRAW_CIVILIZATIONS_NAMES_OVER_PRPOVINCES_IN_GAME
         ) {
            @Override
            public boolean getCheckboxState() {
               return CFG.settingsManager.DRAW_CIVILIZATIONS_NAMES_OVER_PRPOVINCES_IN_GAME;
            }
         }
      );
      int var16;
      menuElements.add(
         new Button_NewGameStyle_Left(
            "-",
            -1,
            CFG.PADDING,
            var16 = tPosY + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING,
            CFG.BUTTON_HEIGHT,
            (int)(CFG.BUTTON_HEIGHT * 0.6F),
            true
         )
      );
      menuElements.add(
         new Button_NewGameStyle_Middle(
            null, -1, CFG.PADDING + CFG.BUTTON_HEIGHT, var16, tempW - CFG.PADDING * 2 - CFG.BUTTON_HEIGHT * 2, (int)(CFG.BUTTON_HEIGHT * 0.6F), true
         )
      );
      menuElements.add(
         new Button_NewGameStyle_Right(
            "+",
            -1,
            CFG.PADDING + CFG.BUTTON_HEIGHT + (tempW - CFG.PADDING * 2 - CFG.BUTTON_HEIGHT * 2),
            var16,
            CFG.BUTTON_HEIGHT,
            (int)(CFG.BUTTON_HEIGHT * 0.6F),
            true
         )
      );
      menuElements.add(
         new Slider_FlagAction_Clear(
            "",
            CFG.PADDING * 2,
            tPosY = var16 + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING * 2,
            tempW - CFG.PADDING * 4,
            (int)(CFG.BUTTON_HEIGHT * 0.8F),
            0,
            100,
            (int)(CFG.settingsManager.CIV_NAMES_MIN_SCALE_OF_FONT * 100.0F)
         ) {
            @Override
            public String getDrawText() {
               return super.getDrawText() + "%";
            }
         }
      );
      int var18;
      menuElements.add(
         new Button_NewGameStyle_Clear(
            "",
            -1,
            CFG.PADDING,
            var18 = tPosY + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING * 2,
            tempW - CFG.PADDING * 2,
            (int)(CFG.BUTTON_HEIGHT * 0.6F),
            true
         ) {
            int iCurrent;

            @Override
            public void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
               super.drawText(oSB, iTranslateX, iTranslateY, isActive);
               oSB.setColor(
                  CFG.settingsManager.civNamesFontColor.getR(),
                  CFG.settingsManager.civNamesFontColor.getG(),
                  CFG.settingsManager.civNamesFontColor.getB(),
                  1.0F
               );
               ImageManager.getImage(Images.slider_gradient)
                  .draw(
                     oSB,
                     this.getPosX() + this.getWidth() / 2 - (int)(this.getTextWidth() * 0.8F / 2.0F) + iTranslateX,
                     this.getPosY() + Menu_Settings_Province.this.getMenuPosY() + this.getHeight() / 2 + this.getTextHeight() / 2 + CFG.CIV_COLOR_WIDTH,
                     CFG.PADDING,
                     CFG.CIV_COLOR_WIDTH,
                     true,
                     false
                  );
               ImageManager.getImage(Images.pix255_255_255)
                  .draw(
                     oSB,
                     this.getPosX() + this.getWidth() / 2 - (int)(this.getTextWidth() * 0.8F / 2.0F) + CFG.PADDING + iTranslateX,
                     this.getPosY() + Menu_Settings_Province.this.getMenuPosY() + this.getHeight() / 2 + this.getTextHeight() / 2 + CFG.CIV_COLOR_WIDTH,
                     (int)(this.getTextWidth() * 0.8F) - CFG.PADDING * 2,
                     CFG.CIV_COLOR_WIDTH
                  );
               ImageManager.getImage(Images.slider_gradient)
                  .draw(
                     oSB,
                     this.getPosX()
                        + this.getWidth() / 2
                        - (int)(this.getTextWidth() * 0.8F / 2.0F)
                        + (int)(this.getTextWidth() * 0.8F)
                        - CFG.PADDING
                        + iTranslateX,
                     this.getPosY() + Menu_Settings_Province.this.getMenuPosY() + this.getHeight() / 2 + this.getTextHeight() / 2 + CFG.CIV_COLOR_WIDTH,
                     CFG.PADDING,
                     CFG.CIV_COLOR_WIDTH
                  );
               oSB.setColor(Color.WHITE);
            }

            @Override
            public void setCurrent(int nCurrent) {
               this.iCurrent = nCurrent;
            }
         }
      );
      menuElements.add(
         new Slider_FlagAction_Clear(
            "",
            CFG.PADDING * 2,
            tPosY = var18 + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING,
            tempW - CFG.PADDING * 4,
            (int)(CFG.BUTTON_HEIGHT * 0.8F),
            0,
            100,
            (int)(CFG.settingsManager.civNamesFontColor_ALPHA * 100.0F)
         ) {
            @Override
            public String getDrawText() {
               return super.getDrawText() + "%";
            }
         }
      );
      int var20;
      menuElements.add(
         new Button_NewGameStyle_Clear(
            "",
            -1,
            CFG.PADDING,
            var20 = tPosY + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING * 2,
            tempW - CFG.PADDING * 2,
            (int)(CFG.BUTTON_HEIGHT * 0.6F),
            true
         ) {
            int iCurrent;

            @Override
            public void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
               super.drawText(oSB, iTranslateX, iTranslateY, isActive);
               oSB.setColor(
                  CFG.settingsManager.civNamesFontColorBorder.getR(),
                  CFG.settingsManager.civNamesFontColorBorder.getG(),
                  CFG.settingsManager.civNamesFontColorBorder.getB(),
                  1.0F
               );
               ImageManager.getImage(Images.slider_gradient)
                  .draw(
                     oSB,
                     this.getPosX() + this.getWidth() / 2 - (int)(this.getTextWidth() * 0.8F / 2.0F) + iTranslateX,
                     this.getPosY() + Menu_Settings_Province.this.getMenuPosY() + this.getHeight() / 2 + this.getTextHeight() / 2 + CFG.CIV_COLOR_WIDTH,
                     CFG.PADDING,
                     CFG.CIV_COLOR_WIDTH,
                     true,
                     false
                  );
               ImageManager.getImage(Images.pix255_255_255)
                  .draw(
                     oSB,
                     this.getPosX() + this.getWidth() / 2 - (int)(this.getTextWidth() * 0.8F / 2.0F) + CFG.PADDING + iTranslateX,
                     this.getPosY() + Menu_Settings_Province.this.getMenuPosY() + this.getHeight() / 2 + this.getTextHeight() / 2 + CFG.CIV_COLOR_WIDTH,
                     (int)(this.getTextWidth() * 0.8F) - CFG.PADDING * 2,
                     CFG.CIV_COLOR_WIDTH
                  );
               ImageManager.getImage(Images.slider_gradient)
                  .draw(
                     oSB,
                     this.getPosX()
                        + this.getWidth() / 2
                        - (int)(this.getTextWidth() * 0.8F / 2.0F)
                        + (int)(this.getTextWidth() * 0.8F)
                        - CFG.PADDING
                        + iTranslateX,
                     this.getPosY() + Menu_Settings_Province.this.getMenuPosY() + this.getHeight() / 2 + this.getTextHeight() / 2 + CFG.CIV_COLOR_WIDTH,
                     CFG.PADDING,
                     CFG.CIV_COLOR_WIDTH
                  );
               oSB.setColor(Color.WHITE);
            }

            @Override
            public void setCurrent(int nCurrent) {
               this.iCurrent = nCurrent;
            }
         }
      );
      menuElements.add(
         new Slider_FlagAction_Clear(
            "",
            CFG.PADDING * 2,
            tPosY = var20 + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING,
            tempW - CFG.PADDING * 4,
            (int)(CFG.BUTTON_HEIGHT * 0.8F),
            0,
            100,
            (int)(CFG.settingsManager.civNamesFontColorBorder_ALPHA * 100.0F)
         ) {
            @Override
            public String getDrawText() {
               return super.getDrawText() + "%";
            }
         }
      );
      int var22;
      menuElements.add(
         new Slider_FlagAction_Clear(
            "",
            CFG.PADDING * 2,
            var22 = tPosY + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING * 2,
            tempW - CFG.PADDING * 4,
            (int)(CFG.BUTTON_HEIGHT * 0.8F),
            0,
            (int)(CFG.settingsManager.FONT_BORDER_SIZE * 0.4F),
            CFG.settingsManager.FONT_BORDER_WIDTH_OF_BORDER
         ) {
            @Override
            public String getDrawText() {
               return super.getDrawText() + "px";
            }
         }
      );
      menuElements.add(
         new Slider_FlagAction_Clear(
            "",
            CFG.PADDING * 2,
            tPosY = var22 + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING * 2,
            tempW - CFG.PADDING * 4,
            (int)(CFG.BUTTON_HEIGHT * 0.8F),
            0,
            5000,
            CFG.settingsManager.CIVILIZATIONS_NAMES_INTERVAL
         ) {
            @Override
            public String getDrawText() {
               return super.getDrawText() + "ms";
            }
         }
      );
      int var24;
      menuElements.add(
         new Text_BudgetTitle(
            "", -1, 2, var24 = tPosY + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING * 2, tempW - 2, CFG.TEXT_HEIGHT + CFG.PADDING * 4
         ) {
            @Override
            public Color getColor(boolean isActive) {
               return isActive
                  ? CFG.COLOR_TEXT_OPTIONS_NS_HOVER
                  : (this.getClickable() ? (this.getIsHovered() ? CFG.COLOR_TEXT_OPTIONS_NS : Color.WHITE) : new Color(0.78F, 0.78F, 0.78F, 0.7F));
            }
         }
      );
      menuElements.add(
         new Button_NewGameStyle_Clear(
            "",
            -1,
            CFG.PADDING,
            tPosY = var24 + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING,
            tempW - CFG.PADDING * 2,
            (int)(CFG.BUTTON_HEIGHT * 0.6F),
            true
         ) {
            int iCurrent;

            @Override
            public void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
               super.drawText(oSB, iTranslateX, iTranslateY, isActive);
               oSB.setColor(
                  CFG.settingsManager.civNamesFontColor.getR(),
                  CFG.settingsManager.civNamesFontColor.getG(),
                  CFG.settingsManager.civNamesFontColor.getB(),
                  1.0F
               );
               ImageManager.getImage(Images.slider_gradient)
                  .draw(
                     oSB,
                     this.getPosX() + this.getWidth() / 2 - (int)(this.getTextWidth() * 0.8F / 2.0F) + iTranslateX,
                     this.getPosY() + Menu_Settings_Province.this.getMenuPosY() + this.getHeight() / 2 + this.getTextHeight() / 2 + CFG.CIV_COLOR_WIDTH,
                     CFG.PADDING,
                     CFG.CIV_COLOR_WIDTH,
                     true,
                     false
                  );
               ImageManager.getImage(Images.pix255_255_255)
                  .draw(
                     oSB,
                     this.getPosX() + this.getWidth() / 2 - (int)(this.getTextWidth() * 0.8F / 2.0F) + CFG.PADDING + iTranslateX,
                     this.getPosY() + Menu_Settings_Province.this.getMenuPosY() + this.getHeight() / 2 + this.getTextHeight() / 2 + CFG.CIV_COLOR_WIDTH,
                     (int)(this.getTextWidth() * 0.8F) - CFG.PADDING * 2,
                     CFG.CIV_COLOR_WIDTH
                  );
               ImageManager.getImage(Images.slider_gradient)
                  .draw(
                     oSB,
                     this.getPosX()
                        + this.getWidth() / 2
                        - (int)(this.getTextWidth() * 0.8F / 2.0F)
                        + (int)(this.getTextWidth() * 0.8F)
                        - CFG.PADDING
                        + iTranslateX,
                     this.getPosY() + Menu_Settings_Province.this.getMenuPosY() + this.getHeight() / 2 + this.getTextHeight() / 2 + CFG.CIV_COLOR_WIDTH,
                     CFG.PADDING,
                     CFG.CIV_COLOR_WIDTH
                  );
               oSB.setColor(Color.WHITE);
            }

            @Override
            public void setCurrent(int nCurrent) {
               this.iCurrent = nCurrent;
            }
         }
      );
      int var26;
      menuElements.add(
         new Slider_FlagAction_Clear(
            "",
            CFG.PADDING * 2,
            var26 = tPosY + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING,
            tempW - CFG.PADDING * 4,
            (int)(CFG.BUTTON_HEIGHT * 0.8F),
            0,
            255,
            (int)(CFG.settingsManager.PROVINCE_ALPHA_WASTELAND * 255.0F)
         ) {
            @Override
            public String getDrawText() {
               return super.getDrawText() + "";
            }
         }
      );
      menuElements.add(
         new Text_BudgetTitle(
            "", -1, 2, tPosY = var26 + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING * 2, tempW - 2, CFG.TEXT_HEIGHT + CFG.PADDING * 4
         ) {
            @Override
            public Color getColor(boolean isActive) {
               return isActive
                  ? CFG.COLOR_TEXT_OPTIONS_NS_HOVER
                  : (this.getClickable() ? (this.getIsHovered() ? CFG.COLOR_TEXT_OPTIONS_NS : Color.WHITE) : new Color(0.78F, 0.78F, 0.78F, 0.7F));
            }
         }
      );
      int var28;
      menuElements.add(
         new Button_NewGameStyle_Clear(
            "",
            -1,
            CFG.PADDING,
            var28 = tPosY + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING,
            tempW - CFG.PADDING * 2,
            (int)(CFG.BUTTON_HEIGHT * 0.6F),
            true
         ) {
            int iCurrent;

            @Override
            public void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
               super.drawText(oSB, iTranslateX, iTranslateY, isActive);
               oSB.setColor(
                  CFG.settingsManager.civNamesFontColorBorder.getR(),
                  CFG.settingsManager.civNamesFontColorBorder.getG(),
                  CFG.settingsManager.civNamesFontColorBorder.getB(),
                  1.0F
               );
               ImageManager.getImage(Images.slider_gradient)
                  .draw(
                     oSB,
                     this.getPosX() + this.getWidth() / 2 - (int)(this.getTextWidth() * 0.8F / 2.0F) + iTranslateX,
                     this.getPosY() + Menu_Settings_Province.this.getMenuPosY() + this.getHeight() / 2 + this.getTextHeight() / 2 + CFG.CIV_COLOR_WIDTH,
                     CFG.PADDING,
                     CFG.CIV_COLOR_WIDTH,
                     true,
                     false
                  );
               ImageManager.getImage(Images.pix255_255_255)
                  .draw(
                     oSB,
                     this.getPosX() + this.getWidth() / 2 - (int)(this.getTextWidth() * 0.8F / 2.0F) + CFG.PADDING + iTranslateX,
                     this.getPosY() + Menu_Settings_Province.this.getMenuPosY() + this.getHeight() / 2 + this.getTextHeight() / 2 + CFG.CIV_COLOR_WIDTH,
                     (int)(this.getTextWidth() * 0.8F) - CFG.PADDING * 2,
                     CFG.CIV_COLOR_WIDTH
                  );
               ImageManager.getImage(Images.slider_gradient)
                  .draw(
                     oSB,
                     this.getPosX()
                        + this.getWidth() / 2
                        - (int)(this.getTextWidth() * 0.8F / 2.0F)
                        + (int)(this.getTextWidth() * 0.8F)
                        - CFG.PADDING
                        + iTranslateX,
                     this.getPosY() + Menu_Settings_Province.this.getMenuPosY() + this.getHeight() / 2 + this.getTextHeight() / 2 + CFG.CIV_COLOR_WIDTH,
                     CFG.PADDING,
                     CFG.CIV_COLOR_WIDTH
                  );
               oSB.setColor(Color.WHITE);
            }

            @Override
            public void setCurrent(int nCurrent) {
               this.iCurrent = nCurrent;
            }
         }
      );
      menuElements.add(
         new Slider_FlagAction_Clear(
            "",
            CFG.PADDING * 2,
            tPosY = var28 + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING,
            tempW - CFG.PADDING * 4,
            (int)(CFG.BUTTON_HEIGHT * 0.8F),
            0,
            255,
            (int)(CFG.settingsManager.COLOR_PROVINCE_DISCOVERY_ALPHA * 255.0F)
         ) {
            @Override
            public String getDrawText() {
               return super.getDrawText() + "";
            }
         }
      );
      int var30;
      menuElements.add(
         new Button_NewGameStyle_Left(
            "<<",
            -1,
            CFG.PADDING,
            var30 = tPosY + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING * 2,
            CFG.BUTTON_HEIGHT,
            (int)(CFG.BUTTON_HEIGHT * 0.6F),
            true
         )
      );
      menuElements.add(
         new Button_NewGameStyle_Middle(
            "", -1, CFG.PADDING + CFG.BUTTON_HEIGHT, var30, tempW - CFG.PADDING * 2 - CFG.BUTTON_HEIGHT * 2, (int)(CFG.BUTTON_HEIGHT * 0.6F), true
         ) {
            @Override
            public void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
               oSB.setColor(Color.WHITE);
               CFG.linesManager
                  .moveLandImage
                  .draw2(
                     oSB,
                     this.getPosX() + CFG.PADDING * 2 + iTranslateX,
                     this.getPosY()
                        + this.getHeight() / 2
                        - CFG.linesManager.moveLandImage.getHeight() / 2
                        - CFG.linesManager.moveLandImage.getHeight()
                        + iTranslateY,
                     this.getWidth() - CFG.PADDING * 4,
                     CFG.linesManager.moveLandImage.getHeight()
                  );
            }
         }
      );
      menuElements.add(
         new Button_NewGameStyle_Right(
            ">>",
            -1,
            CFG.PADDING + CFG.BUTTON_HEIGHT + (tempW - CFG.PADDING * 2 - CFG.BUTTON_HEIGHT * 2),
            var30,
            CFG.BUTTON_HEIGHT,
            (int)(CFG.BUTTON_HEIGHT * 0.6F),
            true
         )
      );
      menuElements.add(
         new Button_NewGameStyle_Left(
            "<<",
            -1,
            CFG.PADDING,
            tPosY = var30 + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING * 2,
            CFG.BUTTON_HEIGHT,
            (int)(CFG.BUTTON_HEIGHT * 0.6F),
            true
         )
      );
      menuElements.add(
         new Button_NewGameStyle_Middle(
            "", -1, CFG.PADDING + CFG.BUTTON_HEIGHT, tPosY, tempW - CFG.PADDING * 2 - CFG.BUTTON_HEIGHT * 2, (int)(CFG.BUTTON_HEIGHT * 0.6F), true
         ) {
            @Override
            public void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
               oSB.setColor(Color.WHITE);
               CFG.linesManager
                  .highlightImage
                  .draw2(
                     oSB,
                     this.getPosX() + CFG.PADDING * 2 + iTranslateX,
                     this.getPosY()
                        + this.getHeight() / 2
                        - CFG.linesManager.highlightImage.getHeight() / 2
                        - CFG.linesManager.highlightImage.getHeight()
                        + iTranslateY,
                     this.getWidth() - CFG.PADDING * 4,
                     CFG.linesManager.highlightImage.getHeight()
                  );
            }
         }
      );
      menuElements.add(
         new Button_NewGameStyle_Right(
            ">>",
            -1,
            CFG.PADDING + CFG.BUTTON_HEIGHT + (tempW - CFG.PADDING * 2 - CFG.BUTTON_HEIGHT * 2),
            tPosY,
            CFG.BUTTON_HEIGHT,
            (int)(CFG.BUTTON_HEIGHT * 0.6F),
            true
         )
      );
      int var32;
      menuElements.add(
         new Text_BudgetTitle(
            "", -1, 2, var32 = tPosY + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING * 2, tempW - 2, CFG.TEXT_HEIGHT + CFG.PADDING * 4
         ) {
            @Override
            public Color getColor(boolean isActive) {
               return isActive
                  ? CFG.COLOR_TEXT_OPTIONS_NS_HOVER
                  : (this.getClickable() ? (this.getIsHovered() ? CFG.COLOR_TEXT_OPTIONS_NS : Color.WHITE) : new Color(0.78F, 0.78F, 0.78F, 0.7F));
            }
         }
      );
      menuElements.add(
         new Slider_FlagAction_Clear(
            "",
            CFG.PADDING * 2,
            tPosY = var32 + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING,
            tempW - CFG.PADDING * 4,
            (int)(CFG.BUTTON_HEIGHT * 0.8F),
            25,
            255,
            CFG.settingsManager.OCCUPIED_PROVINCE_ALPHA
         ) {
            @Override
            public String getDrawText() {
               return "" + (int)(this.getCurrent() / 255.0F * 100.0F) + "%";
            }
         }
      );
      int var34;
      menuElements.add(
         new Slider_FlagAction_Clear(
            "",
            CFG.PADDING * 2,
            var34 = tPosY + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING * 2,
            tempW - CFG.PADDING * 4,
            (int)(CFG.BUTTON_HEIGHT * 0.8F),
            1,
            100,
            (int)(CFG.settingsManager.OCCUPIED_STRIPES_SIZE * 10.0F)
         ) {
            @Override
            public String getDrawText() {
               return "" + this.getCurrent() / 10.0F;
            }
         }
      );
      menuElements.add(
         new Button_NewGameStyle(
            null,
            -1,
            CFG.PADDING,
            tPosY = var34 + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING * 2,
            tempW - CFG.PADDING * 2,
            (int)(CFG.BUTTON_HEIGHT * 0.6F),
            true
         )
      );
      int var36;
      this.initMenu(
         new SliderMenuTitle(null, CFG.BUTTON_HEIGHT * 3 / 5, false, false) {
            @Override
            public void draw(SpriteBatch oSB, int iTranslateX, int nPosX, int nPosY, int nWidth, boolean sliderMenuIsActive) {
               ImageManager.getImage(Images.dialog_title)
                  .draw2(
                     oSB,
                     nPosX - 2 + iTranslateX,
                     nPosY - this.getHeight() - ImageManager.getImage(Images.dialog_title).getHeight(),
                     nWidth + 4,
                     this.getHeight()
                  );
               oSB.setColor(new Color(0.003921569F, 0.32941177F, 0.50980395F, 0.165F));
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
               oSB.setColor(new Color(0.003921569F, 0.32941177F, 0.50980395F, 0.375F));
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
         CFG.GAME_WIDTH - tempW,
         CFG.PADDING + CFG.BUTTON_HEIGHT * 3 / 4,
         tempW,
         Math.min(
            var36 = tPosY + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING, CFG.GAME_HEIGHT - (CFG.PADDING + CFG.BUTTON_HEIGHT * 3 / 4)
         ),
         menuElements
      );
      this.updateLanguage();
   }

   @Override
   public void updateLanguage() {
      this.getMenuElement(0).setText(CFG.langManager.get("Back"));
      this.getMenuElement(2).setText(CFG.langManager.get("FontSizeofArmy") + ": " + CFG.settingsManager.FONT_ARMY_SIZE);
      this.getMenuElement(4).setText(CFG.langManager.get("ProvinceAlpha"));
      this.getMenuElement(5).setText(CFG.langManager.get("Scale"));
      this.getMenuElement(6).setText(CFG.langManager.get("Borders"));
      this.getMenuElement(7).setText(CFG.langManager.get("BorderWidth"));
      this.getMenuElement(8).setText(CFG.langManager.get("BorderHeight"));
      this.getMenuElement(9).setText(CFG.langManager.get("InnerBorders"));
      this.getMenuElement(10).setText(CFG.langManager.get("Cities"));
      this.getMenuElement(11).setText(CFG.langManager.get("NumberOfCities"));
      this.getMenuElement(12).setText(CFG.langManager.get("ScaleOfCitiesNames"));
      this.getMenuElement(13).setText(CFG.langManager.get("CivilizationsNames"));
      this.getMenuElement(14).setText(CFG.langManager.get("NamesOfCivilizationsOverProvinces"));
      this.getMenuElement(16).setText(CFG.langManager.get("FontSize") + ": " + CFG.settingsManager.FONT_BORDER_SIZE);
      this.getMenuElement(18).setText(CFG.langManager.get("MinScaleofCivilizationsNames"));
      this.getMenuElement(19).setText(CFG.langManager.get("Color"));
      this.getMenuElement(20).setText(CFG.langManager.get("Alpha"));
      this.getMenuElement(21).setText(CFG.langManager.get("BorderColor"));
      this.getMenuElement(22).setText(CFG.langManager.get("Alpha"));
      this.getMenuElement(23).setText(CFG.langManager.get("Width"));
      this.getMenuElement(24).setText(CFG.langManager.get("AnimationTime"));
      this.getMenuElement(25).setText(CFG.langManager.get("Wasteland"));
      this.getMenuElement(26).setText(CFG.langManager.get("Color"));
      this.getMenuElement(27).setText(CFG.langManager.get("Alpha"));
      this.getMenuElement(28).setText(CFG.langManager.get("Fogofwar"));
      this.getMenuElement(29).setText(CFG.langManager.get("Color"));
      this.getMenuElement(30).setText(CFG.langManager.get("Alpha"));
      this.getMenuElement(37).setText(CFG.langManager.get("OccupiedProvinces"));
      this.getMenuElement(38).setText(CFG.langManager.get("Alpha"));
      this.getMenuElement(39).setText(CFG.langManager.get("Scale"));
      this.getMenuElement(40).setText(CFG.langManager.get("Defaults"));
      this.getMenuElement(4).setCurrent(CFG.settingsManager.PROVINCE_ALPHA);
      this.getMenuElement(5).setCurrent((int)(CFG.settingsManager.STOP_SCALING_ARMY * 100.0F));
      this.getMenuElement(11).setCurrent(CFG.settingsManager.PERCETANGE_OF_CITIES_ON_MAP);
      this.getMenuElement(12).setCurrent((int)(CFG.settingsManager.CITIES_FONT_SCALE * 100.0F));
      this.getMenuElement(18).setCurrent((int)(CFG.settingsManager.CIV_NAMES_MIN_SCALE_OF_FONT * 100.0F));
      this.getMenuElement(20).setCurrent((int)(CFG.settingsManager.civNamesFontColor_ALPHA * 100.0F));
      this.getMenuElement(22).setCurrent((int)(CFG.settingsManager.civNamesFontColorBorder_ALPHA * 100.0F));
      this.getMenuElement(23).setCurrent(CFG.settingsManager.FONT_BORDER_WIDTH_OF_BORDER);
      this.getMenuElement(24).setCurrent(CFG.settingsManager.CIVILIZATIONS_NAMES_INTERVAL);
      this.getMenuElement(27).setCurrent((int)(CFG.settingsManager.PROVINCE_ALPHA_WASTELAND * 255.0F));
      this.getMenuElement(30).setCurrent((int)(CFG.settingsManager.COLOR_PROVINCE_DISCOVERY_ALPHA * 255.0F));
      this.getMenuElement(38).setCurrent(CFG.settingsManager.PROVINCE_ALPHA);
      this.getMenuElement(39).setCurrent((int)(CFG.settingsManager.OCCUPIED_STRIPES_SIZE * 10.0F));
      this.getTitle().setText(CFG.langManager.get("ProvinceSettings"));
      this.sScale = CFG.langManager.get("Scale") + ": ";
   }

   @Override
   public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.65F));
      ImageManager.getImage(Images.gradient)
         .draw(oSB, iTranslateX, -ImageManager.getImage(Images.gradient).getHeight() + iTranslateY, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT * 3 / 4);
      oSB.setColor(Color.WHITE);
      ImageManager.getImage(Images.new_game_top_edge_line)
         .draw2(
            oSB,
            this.getPosX() - 2 + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.new_game_top_edge_line).getHeight() + iTranslateY,
            this.getWidth() + 2,
            this.getHeight(),
            false,
            true
         );
      CFG.fontBorder.getData().setScale(1.0F);
      CFG.drawTextBorder(oSB, "Age of Civilizations II", CFG.PADDING * 2 + iTranslateX, CFG.PADDING * 2, Color.WHITE);
      CFG.drawTextWithShadow(
         oSB,
         this.sScale + CFG.map.getMapScale().getCurrentScale(),
         CFG.PADDING + iTranslateX,
         CFG.GAME_HEIGHT - CFG.PADDING - CFG.TEXT_HEIGHT,
         CFG.COLOR_TEXT_MODIFIER_NEUTRAL
      );
      super.draw(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
      oSB.setColor(CFG.COLOR_CREATE_NEW_GAME_BOX_PLAYERS);
      ImageManager.getImage(Images.pix255_255_255)
         .draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + this.getHeight(), this.getWidth());
      oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.4F));
      ImageManager.getImage(Images.line_32_off1)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY()
               - ImageManager.getImage(Images.pix255_255_255).getHeight()
               - ImageManager.getImage(Images.line_32_off1).getHeight()
               + this.getHeight(),
            this.getWidth(),
            1
         );
      oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.5F));
      ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() - 2 + iTranslateX, this.getPosY() + this.getHeight(), this.getWidth() + 2);
      oSB.setColor(Color.WHITE);
   }

   public final void updateArmyWidth() {
      for (int i = 0; i < CFG.game.getProvincesSize(); i++) {
         for (int j = 0; j < CFG.game.getProvince(i).getCivsSize(); j++) {
            CFG.game.getProvince(i).getArmy_Obj(j).updateArmyWidth_Just(i);
         }
      }

      for (int var5 = 1; var5 < CFG.game.getCivsSize(); var5++) {
         for (int j = 0; j < CFG.game.getCiv(var5).getRecruitArmySize(); j++) {
            CFG.game.getCiv(var5).getRecruitArmy(j).setArmy(CFG.game.getCiv(var5).getRecruitArmy(j).getArmy());
         }

         for (int var4 = 0; var4 < CFG.game.getCiv(var5).getMoveUnitsPlunderSize(); var4++) {
            CFG.game.getCiv(var5).getMoveUnits_Plunder(var4).setNumOfUnits(CFG.game.getCiv(var5).getMoveUnits_Plunder(var4).getNumOfUnits());
         }
      }
   }

   @Override
   public void actionElement(int iID) {
      switch (iID) {
         case 0:
            this.onBackPressed();
            break;
         case 1:
            CFG.settingsManager.FONT_ARMY_SIZE--;
            if (CFG.settingsManager.FONT_ARMY_SIZE < 12) {
               CFG.settingsManager.FONT_ARMY_SIZE = 12;
            }

            CFG.loadFontArmy();
            if (SaveManager.gameCanBeContinued) {
               this.updateArmyWidth();
            } else {
               for (int i = 0; i < CFG.game.getProvincesSize(); i++) {
                  CFG.game.getProvince(i).getArmy_Obj(0).updateArmyWidth(i);
               }
            }

            Menu_InitGame.loadArmyBGImages();
            this.updateLanguage();
            break;
         case 2:
            CFG.toast.setInView(this.getMenuElement(iID).getText(), CFG.COLOR_TEXT_NUM_OF_PROVINCES);
            break;
         case 3:
            CFG.settingsManager.FONT_ARMY_SIZE++;
            if (CFG.settingsManager.FONT_ARMY_SIZE > 128) {
               CFG.settingsManager.FONT_ARMY_SIZE = 128;
            }

            CFG.loadFontArmy();
            if (SaveManager.gameCanBeContinued) {
               this.updateArmyWidth();
            } else {
               for (int i = 0; i < CFG.game.getProvincesSize(); i++) {
                  CFG.game.getProvince(i).getArmy_Obj(0).updateArmyWidth(i);
               }
            }

            Menu_InitGame.loadArmyBGImages();
            this.updateLanguage();
            break;
         case 4:
            CFG.settingsManager.PROVINCE_ALPHA = this.getMenuElement(iID).getCurrent();
            break;
         case 5:
            CFG.settingsManager.STOP_SCALING_ARMY = this.getMenuElement(iID).getCurrent() / 100.0F;
         case 6:
         case 10:
         case 13:
         case 16:
         case 25:
         case 28:
         case 32:
         case 35:
         case 37:
         default:
            break;
         case 7:
            CFG.settingsManager.BORDER_WIDTH = this.getMenuElement(iID).getCurrent() / 100.0F;
            break;
         case 8:
            CFG.settingsManager.BORDER_HEIGHT = this.getMenuElement(iID).getCurrent() / 100.0F;
            break;
         case 9:
            CFG.settingsManager.ENABLE_INNER_BORDERS = !CFG.settingsManager.ENABLE_INNER_BORDERS;

            for (int i = 0; i < CFG.game.getProvincesSize(); i++) {
               CFG.game.getProvince(i).updateProvinceBorder();
            }
            break;
         case 11:
            CFG.settingsManager.PERCETANGE_OF_CITIES_ON_MAP = this.getMenuElement(iID).getCurrent();

            for (int i = 1; i < CFG.game.getCivsSize(); i++) {
               CFG.game_NextTurnUpdate.updateCities(i);
            }
            break;
         case 12:
            CFG.settingsManager.CITIES_FONT_SCALE = this.getMenuElement(iID).getCurrent() / 100.0F;

            for (int i = 0; i < CFG.game.getProvincesSize(); i++) {
               for (int j = 0; j < CFG.game.getProvince(i).getCitiesSize(); j++) {
                  CFG.game.getProvince(i).getCity(j).updateCityNameWidth();
               }
            }
            break;
         case 14:
            CFG.settingsManager.DRAW_CIVILIZATIONS_NAMES_OVER_PRPOVINCES_IN_GAME = !CFG.settingsManager.DRAW_CIVILIZATIONS_NAMES_OVER_PRPOVINCES_IN_GAME;
            Game_Render.updateRenderer_CivNames();
            break;
         case 15:
            CFG.settingsManager.FONT_BORDER_SIZE--;
            if (CFG.settingsManager.FONT_BORDER_SIZE < 8) {
               CFG.settingsManager.FONT_BORDER_SIZE = 8;
            }

            CFG.loadFontBorder();

            for (int i = 0; i < CFG.game.getCivsSize(); i++) {
               for (int j = 0; j < CFG.game.getCiv(i).getCivRegionsSize(); j++) {
                  CFG.game.getCiv(i).getCivRegion(j).buildScaleOfText();
               }
            }

            this.updateLanguage();
            break;
         case 17:
            CFG.settingsManager.FONT_BORDER_SIZE++;
            if (CFG.settingsManager.FONT_BORDER_SIZE > 256) {
               CFG.settingsManager.FONT_BORDER_SIZE = 256;
            }

            CFG.loadFontBorder();

            for (int i = 0; i < CFG.game.getCivsSize(); i++) {
               for (int j = 0; j < CFG.game.getCiv(i).getCivRegionsSize(); j++) {
                  CFG.game.getCiv(i).getCivRegion(j).buildScaleOfText();
               }
            }

            this.updateLanguage();
            break;
         case 18:
            CFG.settingsManager.CIV_NAMES_MIN_SCALE_OF_FONT = this.getMenuElement(iID).getCurrent() / 100.0F;
            break;
         case 19:
            CFG.menuManager
               .getColorPicker()
               .setActiveRGBColor(
                  CFG.settingsManager.civNamesFontColor.getR(), CFG.settingsManager.civNamesFontColor.getG(), CFG.settingsManager.civNamesFontColor.getB()
               );
            CFG.menuManager.getColorPicker().setVisible(true, ColorPicker_AoC.PickerAction.CIV_NAMES_OVER_PROVINCES);
            break;
         case 20:
            CFG.settingsManager.civNamesFontColor_ALPHA = this.getMenuElement(iID).getCurrent() / 100.0F;
            CFG.loadFontBorder();
            break;
         case 21:
            CFG.menuManager
               .getColorPicker()
               .setActiveRGBColor(
                  CFG.settingsManager.civNamesFontColorBorder.getR(),
                  CFG.settingsManager.civNamesFontColorBorder.getG(),
                  CFG.settingsManager.civNamesFontColorBorder.getB()
               );
            CFG.menuManager.getColorPicker().setVisible(true, ColorPicker_AoC.PickerAction.CIV_NAMES_OVER_PROVINCES_BORDER);
            break;
         case 22:
            CFG.settingsManager.civNamesFontColorBorder_ALPHA = this.getMenuElement(iID).getCurrent() / 100.0F;
            CFG.loadFontBorder();
            break;
         case 23:
            CFG.settingsManager.FONT_BORDER_WIDTH_OF_BORDER = this.getMenuElement(iID).getCurrent();
            CFG.loadFontBorder();
            break;
         case 24:
            CFG.settingsManager.CIVILIZATIONS_NAMES_INTERVAL = this.getMenuElement(iID).getCurrent();
            break;
         case 26:
            CFG.menuManager
               .getColorPicker()
               .setActiveRGBColor(
                  CFG.settingsManager.COLOR_PROVINCE_BG_WASTELAND.getR(),
                  CFG.settingsManager.COLOR_PROVINCE_BG_WASTELAND.getG(),
                  CFG.settingsManager.COLOR_PROVINCE_BG_WASTELAND.getB()
               );
            CFG.menuManager.getColorPicker().setVisible(true, ColorPicker_AoC.PickerAction.PROVINCE_SETTINGS_WASTELAND_COLOR);
            break;
         case 27:
            CFG.settingsManager.PROVINCE_ALPHA_WASTELAND = this.getMenuElement(iID).getCurrent() / 255.0F;
            break;
         case 29:
            CFG.menuManager
               .getColorPicker()
               .setActiveRGBColor(
                  CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getR(),
                  CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getG(),
                  CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getB()
               );
            CFG.menuManager.getColorPicker().setVisible(true, ColorPicker_AoC.PickerAction.PROVINCE_SETTINGS_DISCOVERY_COLOR);
            break;
         case 30:
            CFG.settingsManager.COLOR_PROVINCE_DISCOVERY_ALPHA = this.getMenuElement(iID).getCurrent() / 255.0F;
            break;
         case 31:
            CFG.linesManager.moveLandTAG = CFG.settingsManager.sMoveLine = CFG.linesManager.loadNext(CFG.linesManager.moveLandTAG, false);
            CFG.linesManager.loadMoveLand();
            break;
         case 33:
            CFG.linesManager.moveLandTAG = CFG.settingsManager.sMoveLine = CFG.linesManager.loadNext(CFG.linesManager.moveLandTAG, true);
            CFG.linesManager.loadMoveLand();
            break;
         case 34:
            CFG.linesManager.highlightTAG = CFG.settingsManager.sHighlightLine = CFG.linesManager.loadNext(CFG.linesManager.highlightTAG, false);
            CFG.linesManager.loadHighlight();
            break;
         case 36:
            CFG.linesManager.highlightTAG = CFG.settingsManager.sHighlightLine = CFG.linesManager.loadNext(CFG.linesManager.highlightTAG, true);
            CFG.linesManager.loadHighlight();
            break;
         case 38:
            CFG.settingsManager.OCCUPIED_PROVINCE_ALPHA = this.getMenuElement(iID).getCurrent();
            break;
         case 39:
            CFG.settingsManager.OCCUPIED_STRIPES_SIZE = this.getMenuElement(iID).getCurrent() / 10.0F;
            break;
         case 40:
            SettingsManager tempS = new SettingsManager();
            CFG.settingsManager.PROVINCE_ALPHA = tempS.PROVINCE_ALPHA;
            CFG.settingsManager.DRAW_CIVILIZATIONS_NAMES_OVER_PRPOVINCES_IN_GAME = tempS.DRAW_CIVILIZATIONS_NAMES_OVER_PRPOVINCES_IN_GAME;
            CFG.settingsManager.OCCUPIED_PROVINCE_ALPHA = tempS.OCCUPIED_PROVINCE_ALPHA;
            CFG.settingsManager.OCCUPIED_STRIPES_SIZE = tempS.OCCUPIED_STRIPES_SIZE;
            CFG.settingsManager.FONT_ARMY_SIZE = tempS.FONT_ARMY_SIZE;
            AoCGame.updateArmyFontSize();
            CFG.loadFontArmy();

            for (int i = 0; i < CFG.game.getProvincesSize(); i++) {
               CFG.game.getProvince(i).getArmy_Obj(0).updateArmyWidth(i);
            }

            CFG.settingsManager.PERCETANGE_OF_CITIES_ON_MAP = tempS.PERCETANGE_OF_CITIES_ON_MAP;
            CFG.settingsManager.STOP_SCALING_ARMY = tempS.STOP_SCALING_ARMY;

            for (int var5 = 1; var5 < CFG.game.getCivsSize(); var5++) {
               CFG.game_NextTurnUpdate.updateCities(var5);
            }

            CFG.settingsManager.updateCitiesFontScale();

            for (int var6 = 0; var6 < CFG.game.getProvincesSize(); var6++) {
               for (int j = 0; j < CFG.game.getProvince(var6).getCitiesSize(); j++) {
                  CFG.game.getProvince(var6).getCity(j).updateCityNameWidth();
               }
            }

            CFG.settingsManager.FONT_BORDER_WIDTH_OF_BORDER = tempS.FONT_BORDER_WIDTH_OF_BORDER;
            CFG.settingsManager.ENABLE_INNER_BORDERS = tempS.ENABLE_INNER_BORDERS;

            for (int var7 = 0; var7 < CFG.game.getProvincesSize(); var7++) {
               CFG.game.getProvince(var7).updateProvinceBorder();
            }

            CFG.settingsManager.civNamesFontColor.setR(tempS.civNamesFontColor.getR());
            CFG.settingsManager.civNamesFontColor.setG(tempS.civNamesFontColor.getG());
            CFG.settingsManager.civNamesFontColor.setB(tempS.civNamesFontColor.getB());
            CFG.settingsManager.civNamesFontColor_ALPHA = tempS.civNamesFontColor_ALPHA;
            CFG.settingsManager.civNamesFontColorBorder.setR(tempS.civNamesFontColorBorder.getR());
            CFG.settingsManager.civNamesFontColorBorder.setG(tempS.civNamesFontColorBorder.getG());
            CFG.settingsManager.civNamesFontColorBorder.setB(tempS.civNamesFontColorBorder.getB());
            CFG.settingsManager.civNamesFontColorBorder_ALPHA = tempS.civNamesFontColorBorder_ALPHA;
            CFG.settingsManager.CIV_NAMES_MIN_SCALE_OF_FONT = tempS.CIV_NAMES_MIN_SCALE_OF_FONT;
            CFG.settingsManager.CIVILIZATIONS_NAMES_INTERVAL = tempS.CIVILIZATIONS_NAMES_INTERVAL;
            CFG.settingsManager.COLOR_PROVINCE_BG_WASTELAND.setR(tempS.COLOR_PROVINCE_BG_WASTELAND.getR());
            CFG.settingsManager.COLOR_PROVINCE_BG_WASTELAND.setG(tempS.COLOR_PROVINCE_BG_WASTELAND.getG());
            CFG.settingsManager.COLOR_PROVINCE_BG_WASTELAND.setB(tempS.COLOR_PROVINCE_BG_WASTELAND.getB());
            CFG.settingsManager.PROVINCE_ALPHA_WASTELAND = tempS.PROVINCE_ALPHA_WASTELAND;
            CFG.settingsManager.COLOR_PROVINCE_DISCOVERY = tempS.COLOR_PROVINCE_DISCOVERY;
            CFG.settingsManager.COLOR_PROVINCE_DISCOVERY_ALPHA = tempS.COLOR_PROVINCE_DISCOVERY_ALPHA;
            CFG.settingsManager.sMoveLine = tempS.sMoveLine;
            CFG.linesManager.loadMoveLand();
            CFG.settingsManager.sHighlightLine = tempS.sHighlightLine;
            CFG.linesManager.loadHighlight();
            CFG.loadFontBorder();
            Game_Render.updateRenderer_CivNames();
            this.updateLanguage();
      }

      CFG.saveSettings();
   }

   @Override
   public void onBackPressed() {
      CFG.menuManager.getColorPicker().setVisible(false, null);
      CFG.menuManager.setViewID(Menu.eSETTINGS);
      CFG.menuManager.setBackAnimation(true);
      this.updateArmyWidth();
   }
}
