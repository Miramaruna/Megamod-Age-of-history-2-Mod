package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

public class Menu_VicotryConditions extends SliderMenu {
   public Menu_VicotryConditions() {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      int tempMenuWidth = Menu_Games_Title.getMenuWidth();
      int tY = 0;
      menuElements.add(new Button_Menu_LR_Line(null, -1, 0, 0, tempMenuWidth, CFG.BUTTON_HEIGHT, true) {
         @Override
         public void actionElement(int iID) {
            Menu_VicotryConditions.this.onBackPressed();
         }
      });
      menuElements.add(
         new Text(null, -1, 0, tY, tempMenuWidth, CFG.BUTTON_HEIGHT * 3 / 4) {
            @Override
            public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
               CFG.drawRect_InfoBox_Right_Title(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight());
               CFG.fontMain.getData().setScale(0.8F);
               CFG.drawTextWithShadow(
                  oSB,
                  this.getText(),
                  this.getPosX() + this.getWidth() / 2 - (int)(this.getTextWidth() * 0.8F) / 2 + iTranslateX,
                  this.getPosY() + this.getHeight() / 2 - (int)(this.getTextHeight() * 0.8F) / 2 + iTranslateY,
                  CFG.COLOR_TEXT_CIV_INFO_TITLE
               );
               CFG.fontMain.getData().setScale(1.0F);
            }
         }
      );
      int var4;
      menuElements.add(
         new Button_Menu_Descripted(
            CFG.langManager.get("AnnihilateAllOfYourEnemies"),
            CFG.langManager.get("Domination"),
            (int)(50.0F * CFG.GUI_SCALE),
            0,
            var4 = tY + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING,
            tempMenuWidth,
            CFG.BUTTON_HEIGHT,
            true,
            true
         )
      );
      menuElements.add(
         new Slider(
            "",
            CFG.PADDING,
            tY = var4 + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING * 2,
            tempMenuWidth - CFG.PADDING * 2,
            CFG.BUTTON_HEIGHT - CFG.PADDING * 2,
            2,
            100,
            VicotryManager.VICTORY_CONTROL_PROVINCES_PERC
         ) {
            @Override
            public void drawSliderText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
               CFG.fontMain.getData().setScale(0.8F);
               CFG.drawTextWithShadow(
                  oSB,
                  this.getDrawText(),
                  this.getPosX() + this.getWidth() / 2 - (int)(this.getTextWidth() * 0.8F / 2.0F) + iTranslateX,
                  this.getPosY() + this.getHeight() / 2 - (int)(this.getTextHeight() * 0.8F / 2.0F) + iTranslateY,
                  new Color(0.945F, 0.945F, 0.945F, 1.0F)
               );
               CFG.fontMain.getData().setScale(1.0F);
            }

            @Override
            public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
               ImageManager.getImage(Images.btn_menu_1_h)
                  .draw(oSB, this.getPosX() - CFG.PADDING + iTranslateX, this.getPosY() - CFG.PADDING + iTranslateY, this.getWidth() + CFG.PADDING * 2);
               super.draw(oSB, iTranslateX, iTranslateY, isActive, scrollableY);
            }

            @Override
            public String getDrawText() {
               return super.getText() + ": " + this.getCurrent() + "%";
            }

            @Override
            public Color getColorLEFT() {
               return CFG.COLOR_POPULATION[CFG.COLOR_POPULATION.length - 1];
            }

            @Override
            public void actionElement(int iID) {
               VicotryManager.VICTORY_CONTROL_PROVINCES_PERC = this.getCurrent();
            }
         }
      );
      int var6;
      menuElements.add(
         new Slider(
            "",
            CFG.PADDING,
            var6 = tY + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING * 2,
            tempMenuWidth - CFG.PADDING * 2,
            CFG.BUTTON_HEIGHT - CFG.PADDING * 2,
            0,
            100,
            VicotryManager.VICTORY_LIMIT_OF_TURNS / 10
         ) {
            @Override
            public void drawSliderText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
               CFG.fontMain.getData().setScale(0.8F);
               CFG.drawTextWithShadow(
                  oSB,
                  this.getDrawText(),
                  this.getPosX() + this.getWidth() / 2 - (int)(this.getTextWidth() * 0.8F / 2.0F) + iTranslateX,
                  this.getPosY() + this.getHeight() / 2 - (int)(this.getTextHeight() * 0.8F / 2.0F) + iTranslateY,
                  new Color(0.945F, 0.945F, 0.945F, 1.0F)
               );
               CFG.fontMain.getData().setScale(1.0F);
            }

            @Override
            public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
               ImageManager.getImage(Images.btn_menu_1_h)
                  .draw(oSB, this.getPosX() - CFG.PADDING + iTranslateX, this.getPosY() - CFG.PADDING + iTranslateY, this.getWidth() + CFG.PADDING * 2);
               super.draw(oSB, iTranslateX, iTranslateY, isActive, scrollableY);
            }

            @Override
            public String getDrawText() {
               return super.getText() + (this.getCurrent() == 0 ? CFG.langManager.get("NoThanks") : CFG.langManager.get("TurnsX", this.getCurrent() * 10));
            }

            @Override
            public void actionElement(int iID) {
               VicotryManager.VICTORY_LIMIT_OF_TURNS = this.getCurrent() * 10;
            }
         }
      );
      menuElements.add(
         new Slider(
            "",
            CFG.PADDING,
            tY = var6 + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING * 2,
            tempMenuWidth - CFG.PADDING * 2,
            CFG.BUTTON_HEIGHT - CFG.PADDING * 2,
            0,
            200,
            (int)(VicotryManager.VICTORY_TECHNOLOGY * 100.0F)
         ) {
            @Override
            public void drawSliderText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
               CFG.fontMain.getData().setScale(0.8F);
               CFG.drawTextWithShadow(
                  oSB,
                  this.getDrawText(),
                  this.getPosX() + this.getWidth() / 2 - (int)(this.getTextWidth() * 0.8F / 2.0F) + iTranslateX,
                  this.getPosY() + this.getHeight() / 2 - (int)(this.getTextHeight() * 0.8F / 2.0F) + iTranslateY,
                  new Color(0.945F, 0.945F, 0.945F, 1.0F)
               );
               CFG.fontMain.getData().setScale(1.0F);
            }

            @Override
            public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
               ImageManager.getImage(Images.btn_menu_1_h)
                  .draw(oSB, this.getPosX() - CFG.PADDING + iTranslateX, this.getPosY() - CFG.PADDING + iTranslateY, this.getWidth() + CFG.PADDING * 2);
               super.draw(oSB, iTranslateX, iTranslateY, isActive, scrollableY);
            }

            @Override
            public String getDrawText() {
               return super.getText() + (this.getCurrent() == 0 ? CFG.langManager.get("NoThanks") : "" + this.getCurrent() / 100.0F);
            }

            @Override
            public void actionElement(int iID) {
               VicotryManager.VICTORY_TECHNOLOGY = this.getCurrent() / 100.0F;
            }
         }
      );
      tY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING * 2;
      this.initMenuWithBackButton(null, CFG.GAME_WIDTH - tempMenuWidth, 0, tempMenuWidth, CFG.GAME_HEIGHT, menuElements);
      this.updateLanguage();
   }

   @Override
   public void updateLanguage() {
      this.getMenuElement(0).setText(CFG.langManager.get("Back"));
      this.getMenuElement(1).setText(CFG.langManager.get("VictoryConditions"));
      this.getMenuElement(3).setText(CFG.langManager.get("ControlProvinces"));
      this.getMenuElement(4).setText(CFG.langManager.get("TurnsLimit") + ": ");
      this.getMenuElement(5).setText(CFG.langManager.get("TechnologyLevel") + ": ");
   }

   @Override
   public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.6F));
      ImageManager.getImage(Images.gradient)
         .draw(oSB, iTranslateX, -ImageManager.getImage(Images.gradient).getHeight() + iTranslateY, CFG.GAME_WIDTH, CFG.PADDING * 3);
      ImageManager.getImage(Images.gradient)
         .draw(
            oSB,
            iTranslateX,
            CFG.GAME_HEIGHT - ImageManager.getImage(Images.gradient).getHeight() - CFG.PADDING * 3 + iTranslateY,
            CFG.GAME_WIDTH,
            CFG.PADDING * 3,
            false,
            true
         );
      oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.1F));
      ImageManager.getImage(Images.patt2).draw(oSB, iTranslateX, -ImageManager.getImage(Images.patt2).getHeight(), CFG.GAME_WIDTH, CFG.GAME_HEIGHT, 0.0F, 0);
      oSB.setColor(1.0F, 1.0F, 1.0F, 1.0F);
      ImageManager.getImage(Images.gameLogo)
         .draw(oSB, CFG.PADDING * 2 + iTranslateX, CFG.GAME_HEIGHT - CFG.PADDING * 2 - ImageManager.getImage(Images.gameLogo).getHeight() + iTranslateY);
      oSB.setColor(1.0F, 1.0F, 1.0F, 0.85F);
      ImageManager.getImage(Images.new_game_top_edge_line)
         .draw2(
            oSB,
            this.getPosX() - 2 + iTranslateX,
            -ImageManager.getImage(Images.new_game_top_edge_line).getHeight() + iTranslateY,
            this.getWidth() + 2,
            CFG.GAME_HEIGHT
         );
      oSB.setColor(CFG.COLOR_GRADIENT_TITLE_BLUE.r, CFG.COLOR_GRADIENT_TITLE_BLUE.g, CFG.COLOR_GRADIENT_TITLE_BLUE.b, 0.275F);
      ImageManager.getImage(Images.line_32_off1)
         .draw(oSB, this.getPosX() + iTranslateX, -ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth(), CFG.GAME_HEIGHT);
      oSB.setColor(Color.WHITE);
      super.beginClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
      super.drawMenu(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
      if (this.getMenuElement(2).getIsInView()) {
         CFG.map
            .getIcon(CFG.map.getActiveMapID())
            .draw(
               oSB,
               this.getPosX() + this.getMenuElement(2).getTextPos() / 2 - CFG.map.getIcon(CFG.map.getActiveMapID()).getWidth() / 2 + iTranslateX,
               this.getMenuElement(2).getPosY()
                  + this.getMenuElement(2).getHeight() / 2
                  - CFG.CIV_FLAG_HEIGHT / 2
                  + this.getMenuPosY()
                  - CFG.map.getIcon(CFG.map.getActiveMapID()).getHeight()
                  + iTranslateY,
               CFG.CIV_FLAG_WIDTH,
               CFG.CIV_FLAG_HEIGHT
            );
      }

      super.endClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
   }

   @Override
   public final void actionElement(int iID) {
      this.getMenuElement(iID).actionElement(iID);
   }

   @Override
   public final void onBackPressed() {
      CFG.menuManager.setViewID(CFG.backToMenu);
      CFG.menuManager.setBackAnimation(true);
   }
}
