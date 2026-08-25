package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

public class Menu_CreateScenario_Preview extends SliderMenu {
   public Menu_CreateScenario_Preview() {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      menuElements.add(new Button_Game(null, -1, CFG.PADDING, CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING, CFG.BUTTON_WIDTH * 2, true));
      this.initMenu(null, 0, 0, CFG.GAME_WIDTH, CFG.GAME_HEIGHT, menuElements);
      this.updateLanguage();
   }

   @Override
   public void updateLanguage() {
      this.getMenuElement(0).setText(CFG.langManager.get("Back"));
   }

   @Override
   public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      CFG.drawEditorButtons_Bot_Edge_R(
         oSB,
         this.getMenuElement(0).getPosX() - CFG.PADDING + iTranslateX,
         this.getMenuElement(0).getPosY() - CFG.PADDING + iTranslateY,
         this.getMenuElement(0).getWidth() + CFG.PADDING * 2,
         this.getMenuElement(0).getHeight() + CFG.PADDING * 2
      );
      super.draw(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
   }

   @Override
   public final void actionElement(int iID) {
      switch (iID) {
         case 0:
            this.onBackPressed();
            return;
      }
   }

   @Override
   public void onBackPressed() {
      CFG.game.setActiveProvinceID(-1);
      CFG.menuManager.setViewID(Menu.eCREATE_SCENARIO_SETTINGS);
      CFG.menuManager.setBackAnimation(true);
   }
}
