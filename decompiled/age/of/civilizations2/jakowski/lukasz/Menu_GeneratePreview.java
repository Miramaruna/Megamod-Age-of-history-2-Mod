package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

public class Menu_GeneratePreview extends SliderMenu {
   public Menu_GeneratePreview() {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      menuElements.add(new Button_Transparent(0, 0, CFG.GAME_WIDTH, CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING, true));
      this.initMenu(null, 0, 0, CFG.GAME_WIDTH, CFG.GAME_HEIGHT, menuElements);
   }

   @Override
   public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      super.draw(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
      CFG.map.getMapBG().saveScenarioMinimapPreviewTexture(oSB);
      CFG.toast.setInView(CFG.langManager.get("Saved"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE);
      this.onBackPressed();
   }

   @Override
   public void actionElement(int nMenuElementID) {
      this.onBackPressed();
   }

   @Override
   public final void onBackPressed() {
      CFG.menuManager.setViewIDWithoutAnimation(CFG.backToMenu);
      CFG.menuManager.setBackAnimation(true);
   }
}
