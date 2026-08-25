package age.of.civilizations2.jakowski.lukasz;

import java.util.ArrayList;

public class Menu_CreateScenario extends SliderMenu {
   public Menu_CreateScenario() {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      menuElements.add(new Button_Game(null, -1, CFG.PADDING, CFG.PADDING, true));
      menuElements.add(new Button_Game(null, -1, CFG.GAME_WIDTH - CFG.BUTTON_WIDTH - CFG.PADDING, CFG.PADDING, true));
      menuElements.add(new Minimap(CFG.GAME_WIDTH - CFG.map.getMapBG().getMinimapWidth(), CFG.GAME_HEIGHT - CFG.map.getMapBG().getMinimapHeight()));
      this.initMenu(null, 0, 0, CFG.GAME_WIDTH, CFG.GAME_HEIGHT, menuElements);
   }

   @Override
   public void updateLanguage() {
      this.getMenuElement(0).setText(CFG.langManager.get("Back"));
      this.getMenuElement(1).setText(CFG.langManager.get("Next"));
   }

   @Override
   public void actionElement(int iID) {
      switch (iID) {
         case 0:
            this.onBackPressed();
      }
   }

   @Override
   public void onBackPressed() {
      CFG.setDialogType(Dialog.EXIT_CREATOR);
   }
}
