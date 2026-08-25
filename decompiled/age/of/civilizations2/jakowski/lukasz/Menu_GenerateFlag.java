package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

public class Menu_GenerateFlag extends SliderMenu {
   public Menu_GenerateFlag() {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      menuElements.add(new Button_Transparent(0, 0, CFG.GAME_WIDTH, CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING, true));
      this.initMenu(null, 0, 0, CFG.GAME_WIDTH, CFG.GAME_HEIGHT, menuElements);
   }

   @Override
   public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      CFG.flagManager.saveFlagTexture(oSB);
      CFG.toast.setInView(CFG.langManager.get("Saved"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE);
      this.onBackPressed();
   }

   @Override
   public void actionElement(int nMenuElementID) {
   }

   @Override
   public final void onBackPressed() {
      try {
         if (CFG.backToMenu == Menu.eINGAME_CREATE_VASSAL) {
            CFG.createVassal_Data.setCivTag(CFG.EDITOR_ACTIVE_GAMEDATA_TAG);
         }
      } catch (NullPointerException var2) {
      }

      CFG.menuManager.setViewIDWithoutAnimation(CFG.backToMenu);
      CFG.menuManager.setBackAnimation(true);
      CFG.map.getMapBG().updateWorldMap_Shaders();
      Game_Render_Province.updateDrawProvinces();
   }
}
