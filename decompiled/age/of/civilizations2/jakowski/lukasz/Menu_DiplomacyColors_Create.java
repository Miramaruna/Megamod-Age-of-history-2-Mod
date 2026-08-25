package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

public class Menu_DiplomacyColors_Create extends SliderMenu {
   public String sName;
   public int iNameWidth;

   public Menu_DiplomacyColors_Create() {
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
               return Menu_DiplomacyColors_Create.this.sName + ": " + super.getText();
            }

            @Override
            public int getTextWidth() {
               return super.getTextWidth() + Menu_DiplomacyColors_Create.this.iNameWidth;
            }

            @Override
            public void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
            }
         }
      );
      menuElements.add(new Button_Game(null, -1, CFG.GAME_WIDTH - CFG.BUTTON_WIDTH - CFG.PADDING, CFG.PADDING, true));
      this.initMenu(null, 0, 0, CFG.GAME_WIDTH, CFG.GAME_HEIGHT, menuElements);
      this.updateLanguage();
   }

   @Override
   public void updateLanguage() {
      this.sName = CFG.langManager.get("PackageName");
      CFG.glyphLayout.setText(CFG.fontMain, this.sName + ": ");
      this.iNameWidth = (int)CFG.glyphLayout.width;
      this.getMenuElement(0).setText(CFG.langManager.get("Back"));
      this.getMenuElement(1).setText(CFG.diplomacyColors_GameData.getName());
      this.getMenuElement(2).setText(CFG.langManager.get("Save"));
   }

   @Override
   public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      ImageManager.getImage(Images.editor_line)
         .draw2(
            oSB,
            iTranslateX,
            -ImageManager.getImage(Images.editor_line).getHeight() + iTranslateY,
            CFG.GAME_WIDTH,
            CFG.BUTTON_HEIGHT + CFG.PADDING * 2,
            false,
            true
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
            if (this.getMenuElement(1).getText().equals("")) {
               CFG.toast.setInView(this.sName);
               CFG.showKeyboard(1);
            } else {
               CFG.diplomacyColors_GameData.setName(this.getMenuElement(1).getText());
               CFG.game.saveDiplomacyColors();
               this.onBackPressed();
            }

            return;
      }
   }

   @Override
   public void onBackPressed() {
      CFG.menuManager.getColorPicker().setVisible(false, null);
      CFG.menuManager.setViewID(Menu.eGAME_EDITOR_DIPLOMACY_COLORS_PACKAGES);
      CFG.menuManager.setBackAnimation(true);
      Game_Render_Province.updateDrawProvinces();
   }
}
