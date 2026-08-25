package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

public class Menu_MapEditor_ProvinceBG extends SliderMenu {
   public Menu_MapEditor_ProvinceBG() {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      menuElements.add(new Button_Game(null, -1, CFG.PADDING, CFG.GAME_HEIGHT - CFG.PADDING - CFG.BUTTON_HEIGHT, CFG.BUTTON_WIDTH * 2));
      menuElements.add(
         new Button_Game_Checkbox(
            "BORDERS",
            -1,
            CFG.PADDING * 2 + CFG.BUTTON_WIDTH * 2,
            CFG.GAME_HEIGHT - CFG.PADDING - CFG.BUTTON_HEIGHT,
            CFG.BUTTON_WIDTH,
            true,
            CFG.VIEW_SHOW_VALUES
         ) {
            @Override
            public boolean getCheckboxState() {
               return CFG.VIEW_SHOW_VALUES;
            }
         }
      );
      this.initMenu(null, 0, 0, CFG.GAME_WIDTH, CFG.GAME_HEIGHT, menuElements);
      this.updateLanguage();
   }

   @Override
   public void updateLanguage() {
      this.getMenuElement(0).setText(CFG.langManager.get("Back"));
   }

   @Override
   public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      super.beginClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
      CFG.drawEditorButtons_Bot_Edge_R(
         oSB,
         iTranslateX,
         this.getMenuElement(0).getPosY() - CFG.PADDING + iTranslateY,
         CFG.BUTTON_WIDTH * 3 + CFG.PADDING * 3,
         CFG.BUTTON_HEIGHT + CFG.PADDING * 2
      );
      super.drawMenu(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
      super.endClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
   }

   @Override
   public final void actionElement(int iID) {
      switch (iID) {
         case 0:
            this.onBackPressed();
            return;
         case 1:
            CFG.VIEW_SHOW_VALUES = !CFG.VIEW_SHOW_VALUES;
            return;
      }
   }

   @Override
   public void onBackPressed() {
      CFG.menuManager.setViewID(Menu.eMAP_EDITOR_EDIT);
      CFG.menuManager.setBackAnimation(true);
      CFG.editorManager.resetInUseEditors();
      Game_Render_Province.updateDrawProvinces();
      CFG.map.getMapCoordinates().setDisableMovingMap(false);
   }
}
