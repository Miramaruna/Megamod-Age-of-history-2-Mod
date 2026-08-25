package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

public class Menu_CreateScenario_Happiness extends Menu_CreateScenario {
   public String sTopText;
   public int iStepWidth;

   public Menu_CreateScenario_Happiness() {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      menuElements.add(new Button_Game(null, -1, CFG.PADDING, CFG.PADDING, true));
      menuElements.add(new Button_Game(null, -1, CFG.GAME_WIDTH - CFG.BUTTON_WIDTH - CFG.PADDING, CFG.PADDING, true));
      menuElements.add(new Minimap(CFG.GAME_WIDTH - CFG.map.getMapBG().getMinimapWidth(), CFG.GAME_HEIGHT - CFG.map.getMapBG().getMinimapHeight()));
      menuElements.add(new Button_Game("-", -1, CFG.PADDING, CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING, true));
      menuElements.add(
         new Slider(
            CFG.BUTTON_WIDTH + CFG.PADDING * 2,
            CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING,
            CFG.GAME_WIDTH - (CFG.BUTTON_WIDTH + CFG.PADDING * 2) * 2,
            CFG.BUTTON_HEIGHT,
            0,
            100,
            100
         ) {
            @Override
            public String getDrawText() {
               return CFG.game.getCiv(CFG.iCreateScenario_AssignProvinces_Civ).getCivName() + this.getText() + this.getCurrent() + "%";
            }

            @Override
            public Color getColorLEFT() {
               return new Color(0.17254902F, 0.3019608F, 0.019607844F, 1.0F);
            }

            @Override
            public int getTextWidth() {
               return super.getTextWidth() + CFG.CIV_FLAG_WIDTH + CFG.PADDING;
            }

            @Override
            public void drawSliderText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
               if (CFG.iCreateScenario_AssignProvinces_Civ >= 0) {
                  oSB.setColor(Color.WHITE);
                  CFG.game
                     .getCiv(CFG.iCreateScenario_AssignProvinces_Civ)
                     .getFlag()
                     .draw(
                        oSB,
                        this.getPosX() + this.getWidth() / 2 - this.getTextWidth() / 2 + iTranslateX,
                        this.getPosY() + this.getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 + iTranslateY
                     );
                  ImageManager.getImage(Images.flag_rect)
                     .draw(
                        oSB,
                        this.getPosX() + this.getWidth() / 2 - this.getTextWidth() / 2 + iTranslateX,
                        this.getPosY() + this.getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 + iTranslateY
                     );
               }

               CFG.drawTextWithShadow(
                  oSB,
                  this.getDrawText(),
                  this.getPosX() + this.getWidth() / 2 - this.getTextWidth() / 2 + CFG.CIV_FLAG_WIDTH + CFG.PADDING + iTranslateX,
                  this.getPosY() + this.getHeight() / 2 - this.getTextHeight() / 2 + iTranslateY,
                  new Color(0.945F, 0.945F, 0.945F, 1.0F)
               );
            }
         }
      );
      menuElements.add(new Button_Game("+", -1, CFG.GAME_WIDTH - CFG.BUTTON_WIDTH - CFG.PADDING, CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING, true));
      menuElements.add(
         new Button_Game_Checkbox(
            null, -1, CFG.GAME_WIDTH - CFG.PADDING - CFG.BUTTON_WIDTH * 2, CFG.BUTTON_HEIGHT + CFG.PADDING * 3, CFG.BUTTON_WIDTH * 2, true, true
         ) {
            @Override
            public boolean getCheckboxState() {
               return CFG.VIEW_SHOW_VALUES;
            }
         }
      );
      this.initMenu(null, 0, 0, CFG.GAME_WIDTH, CFG.GAME_HEIGHT, menuElements);
      this.getMenuElement(2).setVisible(false);
      this.updateLanguage();
   }

   @Override
   public void updateLanguage() {
      this.sTopText = CFG.langManager.get("Happiness");
      CFG.glyphLayout.setText(CFG.fontMain, this.sTopText);
      this.iStepWidth = (int)CFG.glyphLayout.width;
      this.getMenuElement(4).setText(": ");
      super.updateLanguage();
      this.getMenuElement(1).setText(CFG.langManager.get("Save"));
      this.getMenuElement(6).setText(CFG.langManager.get("ShowValues"));
   }

   @Override
   public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      CFG.drawEditorTitle_Edge_R_Reflected(oSB, iTranslateX, this.getMenuPosY() + iTranslateY, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT + CFG.PADDING * 2);
      CFG.drawTextWithShadow(
         oSB,
         this.sTopText,
         CFG.GAME_WIDTH / 2 - this.iStepWidth / 2 + iTranslateX,
         CFG.BUTTON_HEIGHT / 2 - CFG.TEXT_HEIGHT / 2 + CFG.PADDING + this.getMenuPosY() + iTranslateY,
         Color.WHITE
      );
      CFG.drawEditorButtons_Top_Edge_R_Reflected(
         oSB,
         this.getMenuElement(6).getPosX() - CFG.PADDING + iTranslateX,
         this.getMenuElement(6).getPosY() - CFG.PADDING + iTranslateY,
         this.getMenuElement(6).getWidth() + CFG.PADDING * 2,
         this.getMenuElement(6).getHeight() + CFG.PADDING * 2
      );
      CFG.drawEditorTitle_Bot_Edge_LR(
         oSB, iTranslateX, this.getMenuElement(3).getPosY() - CFG.PADDING, CFG.GAME_WIDTH, this.getMenuElement(3).getHeight() + CFG.PADDING * 2
      );
      super.draw(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
   }

   @Override
   public final void actionElement(int iID) {
      switch (iID) {
         case 1:
            this.onBackPressed();
            return;
         case 2:
         default:
            super.actionElement(iID);
            return;
         case 3:
            this.getMenuElement(iID + 1).setCurrent(this.getMenuElement(iID + 1).getCurrent() - 1);
            if (CFG.iCreateScenario_AssignProvinces_Civ > 0) {
               CFG.game.getCiv(CFG.iCreateScenario_AssignProvinces_Civ).setHappiness(this.getMenuElement(iID + 1).getCurrent());
               CFG.game
                  .getProvince(CFG.game.getCiv(CFG.iCreateScenario_AssignProvinces_Civ).getCapitalProvinceID())
                  .getArmy_Obj(0)
                  .updateArmyWidth(CFG.game.getCiv(CFG.iCreateScenario_AssignProvinces_Civ).getHappiness() + "%");
            }

            return;
         case 4:
            if (CFG.iCreateScenario_AssignProvinces_Civ > 0) {
               CFG.game.getCiv(CFG.iCreateScenario_AssignProvinces_Civ).setHappiness(this.getMenuElement(iID).getCurrent());
               CFG.game
                  .getProvince(CFG.game.getCiv(CFG.iCreateScenario_AssignProvinces_Civ).getCapitalProvinceID())
                  .getArmy_Obj(0)
                  .updateArmyWidth(CFG.game.getCiv(CFG.iCreateScenario_AssignProvinces_Civ).getHappiness() + "%");
            }

            return;
         case 5:
            this.getMenuElement(iID - 1).setCurrent(this.getMenuElement(iID - 1).getCurrent() + 1);
            if (CFG.iCreateScenario_AssignProvinces_Civ > 0) {
               CFG.game.getCiv(CFG.iCreateScenario_AssignProvinces_Civ).setHappiness(this.getMenuElement(iID - 1).getCurrent());
               CFG.game
                  .getProvince(CFG.game.getCiv(CFG.iCreateScenario_AssignProvinces_Civ).getCapitalProvinceID())
                  .getArmy_Obj(0)
                  .updateArmyWidth(CFG.game.getCiv(CFG.iCreateScenario_AssignProvinces_Civ).getHappiness() + "%");
            }

            return;
         case 6:
            CFG.VIEW_SHOW_VALUES = !CFG.VIEW_SHOW_VALUES;
            if (CFG.VIEW_SHOW_VALUES) {
               for (int i = 0; i < CFG.game.getProvincesSize(); i++) {
                  if (CFG.game.getProvince(i).getIsCapital()) {
                     CFG.game.getProvince(i).getArmy_Obj(0).updateArmyWidth(CFG.game.getCiv(CFG.game.getProvince(i).getCivID()).getHappiness() + "%");
                  }
               }
            }
      }
   }

   @Override
   public void onBackPressed() {
      CFG.menuManager.setViewID(Menu.eCREATE_SCENARIO_SETTINGS);
      if (CFG.iCreateScenario_AssignProvinces_Civ > 0) {
         CFG.game.disableDrawCivilizationRegions(CFG.iCreateScenario_AssignProvinces_Civ);
      }

      CFG.game.setActiveProvinceID(-1);
      Game_Render_Province.updateDrawProvinces();

      for (int i = 0; i < CFG.game.getProvincesSize(); i++) {
         if (CFG.game.getProvince(i).getIsCapital()) {
            CFG.game.getProvince(i).getArmy_Obj(0).updateArmyWidth_Just(i);
         }
      }
   }
}
