package age.of.civilizations2.jakowski.lukasz;

import java.util.ArrayList;

public class Menu_CreateScenario_Assign_Select extends SliderMenu {
   public Menu_CreateScenario_Assign_Select() {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      menuElements.add(new Button_Menu_LR_Line(null, -1, 0, 0, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT, true));
      this.initMenuWithBackButton(null, 0, CFG.BUTTON_HEIGHT * 3 / 4, CFG.GAME_WIDTH, CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT * 3 / 4, menuElements, true, false);
      this.updateLanguage();
   }

   @Override
   public void updateLanguage() {
      this.getMenuElement(0).setText(CFG.langManager.get("Back"));
   }

   @Override
   public final void actionElement(int iID) {
      this.onBackPressed();
   }

   @Override
   public void onBackPressed() {
      CFG.menuManager.setViewID(Menu.eCREATE_SCENARIO_ASSIGN);
   }

   @Override
   public void actionClose() {
      this.onBackPressed();
   }
}
