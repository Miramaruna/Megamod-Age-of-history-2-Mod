package age.of.civilizations2.jakowski.lukasz;

import java.util.ArrayList;

public class Menu_Leader_Edit_SelectCiv extends SliderMenu {
   public Menu_Leader_Edit_SelectCiv() {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      menuElements.add(new Button_Menu_LR_Line(null, -1, 0, 0, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT, true));
      this.initMenuWithBackButton(
         new SliderMenuTitle(null, CFG.BUTTON_HEIGHT * 3 / 4, false, false),
         0,
         CFG.BUTTON_HEIGHT * 3 / 4,
         CFG.GAME_WIDTH,
         CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT * 3 / 4,
         menuElements,
         true,
         true
      );
      this.updateLanguage();
   }

   @Override
   public void updateLanguage() {
      this.getMenuElement(0).setText(CFG.langManager.get("Back"));
      this.getTitle().setText(CFG.langManager.get("SelectCivilization"));
   }

   @Override
   public final void actionElement(int iID) {
      this.onBackPressed();
   }

   @Override
   public void onBackPressed() {
      CFG.menuManager.setViewID(Menu.eGAME_LEADERS_EDIT);
      CFG.menuManager.clearLeaders__SelectCiv();
   }

   @Override
   public void actionClose() {
      this.onBackPressed();
   }
}
