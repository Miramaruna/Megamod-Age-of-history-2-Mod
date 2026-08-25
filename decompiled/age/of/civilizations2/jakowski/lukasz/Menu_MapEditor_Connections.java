package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

public class Menu_MapEditor_Connections extends SliderMenu {
   public Menu_MapEditor_Connections() {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      menuElements.add(new Button_Game(null, -1, CFG.PADDING, CFG.GAME_HEIGHT - CFG.PADDING - CFG.BUTTON_HEIGHT, CFG.BUTTON_WIDTH * 2));
      menuElements.add(
         new Button_Game(null, -1, CFG.PADDING * 2 + CFG.BUTTON_WIDTH * 2, CFG.GAME_HEIGHT - CFG.PADDING - CFG.BUTTON_HEIGHT, CFG.BUTTON_WIDTH * 2)
      );
      menuElements.add(
         new Button_Game_Checkbox(
            null, -1, CFG.PADDING * 3 + CFG.BUTTON_WIDTH * 3, CFG.GAME_HEIGHT - CFG.PADDING - CFG.BUTTON_HEIGHT, CFG.BUTTON_WIDTH, true, CFG.VIEW_SHOW_VALUES
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
      this.getMenuElement(0).setText(CFG.langManager.get("Save"));
      this.getMenuElement(1).setText(CFG.langManager.get("UpdateProvinceData"));
      this.getMenuElement(2).setText(CFG.langManager.get("Lines"));
      this.updatedButtonsWidthFromToID(1, 3, CFG.BUTTON_WIDTH * 2 + CFG.PADDING * 2, CFG.BUTTON_WIDTH);
   }

   @Override
   public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      super.beginClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
      CFG.drawEditorButtons_Bot_Edge_R(
         oSB,
         iTranslateX,
         this.getMenuElement(0).getPosY() - CFG.PADDING + iTranslateY,
         this.getMenuElement(this.getMenuElementsSize() - 1).getPosX() + this.getMenuElement(this.getMenuElementsSize() - 1).getWidth() + CFG.PADDING,
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
            CFG.editorManager.resetInUseEditors();
            CFG.menuManager.setViewID(Menu.eMAP_EDITOR_UPDATE_PROVINCE_DATA);
            return;
         case 2:
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

      for (int i = 0; i < CFG.game.getProvincesSize(); i++) {
         CFG.game.getProvince(i).getArmy_Obj(0).updateArmyWidth_Just(i);
      }
   }
}
