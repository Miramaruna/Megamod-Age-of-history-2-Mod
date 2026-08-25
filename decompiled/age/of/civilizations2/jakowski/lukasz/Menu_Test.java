package age.of.civilizations2.jakowski.lukasz;

import java.util.ArrayList;

public class Menu_Test extends SliderMenu {
   public Menu_Test() {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      menuElements.add(new Button_Menu("Back", -1, 0, 0, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT, true));
      menuElements.add(new Slider_LR(0, CFG.PADDING, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT, 0, 7777, 4444));

      for (int i = 1; i < 5; i++) {
         menuElements.add(new Button_Menu("TEST" + i, -1, 0, CFG.BUTTON_HEIGHT * i + CFG.PADDING * (i + 1), CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT, true));
      }

      this.initMenuWithBackButton(
         new SliderMenuTitle("TEST MENU", CFG.BUTTON_HEIGHT * 3 / 4, true, true),
         CFG.BUTTON_HEIGHT,
         CFG.GAME_HEIGHT / 2,
         CFG.GAME_WIDTH / 2,
         CFG.BUTTON_HEIGHT * 3,
         menuElements,
         true
      );
   }

   @Override
   public void actionElement(int iID) {
      switch (iID) {
         case 0:
            this.onBackPressed();
            break;
         case 2:
            CFG.showKeyboard();
      }
   }

   @Override
   public void onBackPressed() {
      CFG.menuManager.setViewID(Menu.eMAINMENU);
      CFG.menuManager.setBackAnimation(true);
   }
}
