package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

public class Menu_Continents_CreateNewContinent extends SliderMenu {
   public String sName;
   public int iNameWidth;

   public Menu_Continents_CreateNewContinent() {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      menuElements.add(new Button_Game(null, -1, CFG.PADDING, CFG.PADDING, true));
      menuElements.add(
         new Button_Menu(
            "", -1, CFG.BUTTON_WIDTH + CFG.PADDING * 2, 0, CFG.GAME_WIDTH - (CFG.BUTTON_WIDTH + CFG.PADDING * 2) * 2, CFG.BUTTON_HEIGHT + CFG.PADDING * 2, true
         ) {
            @Override
            public Color getColor(boolean isActive) {
               return isActive
                  ? new Color(0.82F, 0.82F, 0.82F, 1.0F)
                  : (this.getClickable() ? new Color(1.0F, 1.0F, 1.0F, 1.0F) : new Color(0.84F, 0.84F, 0.84F, 0.7F));
            }

            @Override
            public String getTextToDraw() {
               return Menu_Continents_CreateNewContinent.this.sName + ": " + super.getText();
            }

            @Override
            public int getTextWidth() {
               return super.getTextWidth() + Menu_Continents_CreateNewContinent.this.iNameWidth;
            }

            @Override
            public void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
            }
         }
      );
      menuElements.add(new Button_Game(null, -1, CFG.GAME_WIDTH - CFG.BUTTON_WIDTH - CFG.PADDING, CFG.PADDING, true));
      menuElements.add(
         new Button_Game(null, -1, CFG.PADDING, CFG.BUTTON_HEIGHT + CFG.PADDING * 3, CFG.BUTTON_WIDTH * 2) {
            @Override
            public void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
               super.drawText(oSB, iTranslateX, iTranslateY, isActive);
               oSB.setColor(new Color(CFG.editor_Continent_GameData.getR(), CFG.editor_Continent_GameData.getG(), CFG.editor_Continent_GameData.getB(), 1.0F));
               ImageManager.getImage(Images.pix255_255_255)
                  .draw(
                     oSB,
                     this.getPosX() + this.getWidth() / 2 - this.getTextWidth() / 2,
                     this.getPosY() + this.getHeight() / 2 + this.getTextHeight() / 2 + CFG.PADDING,
                     this.getTextWidth(),
                     CFG.CIV_COLOR_WIDTH
                  );
               oSB.setColor(Color.WHITE);
            }

            @Override
            public Color getColor(boolean isActive) {
               return isActive
                  ? new Color(CFG.editor_Continent_GameData.getR(), CFG.editor_Continent_GameData.getG(), CFG.editor_Continent_GameData.getB(), 1.0F)
                  : (this.getClickable() ? new Color(0.38F, 0.38F, 0.38F, 1.0F) : new Color(0.49F, 0.49F, 0.49F, 0.5F));
            }
         }
      );
      this.initMenu(null, 0, 0, CFG.GAME_WIDTH, CFG.GAME_HEIGHT, menuElements);
      this.updateLanguage();
   }

   @Override
   public void updateLanguage() {
      this.sName = CFG.langManager.get("ContinentName");
      CFG.glyphLayout.setText(CFG.fontMain, this.sName + ": ");
      this.iNameWidth = (int)CFG.glyphLayout.width;
      this.getMenuElement(0).setText(CFG.langManager.get("Back"));
      this.getMenuElement(1).setText(CFG.editor_Continent_GameData.getName());
      this.getMenuElement(2).setText(CFG.langManager.get("Save"));
      this.getMenuElement(3).setText(CFG.langManager.get("Color"));
   }

   @Override
   public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      CFG.drawEditorTitle_Edge_R(oSB, iTranslateX, iTranslateY, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT + CFG.PADDING * 2);
      CFG.drawEditorButtons_Top_Edge_R(
         oSB,
         iTranslateX,
         CFG.BUTTON_HEIGHT + CFG.PADDING * 2,
         this.getMenuElement(3).getWidth() + CFG.PADDING * 2,
         this.getMenuElement(3).getHeight() + CFG.PADDING * 2
      );
      super.draw(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
   }

   @Override
   public final void actionElement(int iID) {
      switch (iID) {
         case 0:
            this.onBackPressed();
            return;
         case 1:
            CFG.showKeyboard();
            return;
         case 2:
            if (this.getMenuElement(1).getText().length() > 0) {
               CFG.editor_Continent_GameData.setName(this.getMenuElement(1).getText());
               CFG.game.saveContinentPackagesData();
               this.onBackPressed();
            } else {
               CFG.showKeyboard(1);
               CFG.toast.setInView(this.sName);
               CFG.toast.setTimeInView(3000);
            }

            return;
         case 3:
            if (CFG.menuManager.getColorPicker().getVisible()) {
               CFG.menuManager.getColorPicker().setVisible(false, null);
            } else {
               CFG.menuManager.getColorPicker().setPosX(CFG.PADDING * 3);
               CFG.menuManager
                  .getColorPicker()
                  .setPosY(this.getMenuElement(3).getPosY() + this.getMenuElement(3).getHeight() + CFG.PADDING + CFG.menuManager.getColorPicker().getPosX());
               CFG.menuManager
                  .getColorPicker()
                  .setActiveRGBColor(CFG.editor_Continent_GameData.getR(), CFG.editor_Continent_GameData.getG(), CFG.editor_Continent_GameData.getB());
               CFG.menuManager.getColorPicker().setVisible(true, ColorPicker_AoC.PickerAction.MAP_EDITOR_CONTINENT_COLOR);
            }

            return;
      }
   }

   @Override
   public void onBackPressed() {
      CFG.menuManager.getColorPicker().setVisible(false, null);
      CFG.menuManager.setViewID(CFG.backToMenu);
      CFG.menuManager.setBackAnimation(true);
      Game_Render_Province.updateDrawProvinces();
   }
}
