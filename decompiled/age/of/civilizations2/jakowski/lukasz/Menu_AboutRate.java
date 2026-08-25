package age.of.civilizations2.jakowski.lukasz;

import java.util.ArrayList;

public class Menu_AboutRate extends SliderMenu {
   public Menu_AboutRate() {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      menuElements.add(new Button_Menu_LR_Line(null, -1, 0, 0, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT, true) {
         @Override
         public void actionElement(int iID) {
            if (CFG.isDesktop()) {
               CFG.GO_TO_LINK = "http://www.AgeofCivilizationsGame.com";
               CFG.setDialogType(Dialog.GO_TO_LINK);
            }

            CFG.menuManager.setViewID(Menu.eMAINMENU);
            CFG.menuManager.setBackAnimation(true);
         }
      });
      this.initMenu(null, 0, CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT + 1, menuElements);
      this.updateLanguage();
   }

   @Override
   public void updateLanguage() {
      this.getMenuElement(0).setText(CFG.isDesktop() ? "www.AgeofCivilizationsGame.com" : CFG.langManager.get("Rate") + " Age of Civilizations II");
   }

   @Override
   public final void actionElement(int iID) {
      this.getMenuElement(iID).actionElement(iID);
   }
}
