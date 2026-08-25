package age.of.civilizations2.jakowski.lukasz;

import java.util.ArrayList;

public class Menu_CreateScenario_Events_SelectIdeology extends SliderMenu {
   public Menu_CreateScenario_Events_SelectIdeology() {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      int tY = CFG.PADDING;
      menuElements.add(new Button_Menu_LR_Line(null, -1, 0, tY, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT, true));

      for (int i = 0; i < CFG.ideologiesManager.getIdeologiesSize(); i++) {
         menuElements.add(
            new Button_Menu(CFG.ideologiesManager.getIdeology(i).getName(), (int)(50.0F * CFG.GUI_SCALE), 0, tY, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT, true)
         );
         tY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
      }

      this.initMenuWithBackButton(
         new SliderMenuTitle(null, CFG.BUTTON_HEIGHT * 3 / 4, false, false),
         0,
         CFG.BUTTON_HEIGHT * 3 / 4,
         CFG.GAME_WIDTH,
         CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT * 3 / 4,
         menuElements
      );
      this.updateLanguage();
   }

   @Override
   public void updateLanguage() {
      this.getMenuElement(0).setText(CFG.langManager.get("Back"));
      this.getTitle().setText(CFG.langManager.get("Government"));
   }

   @Override
   public final void actionElement(int iID) {
      switch (iID) {
         case 0:
            this.onBackPressed();
            return;
         default:
            CFG.eventsManager.selectCivAction(iID - 1);
            this.onBackPressed();
      }
   }

   @Override
   public final void onBackPressed() {
      CFG.eventsManager.selectCivBack();
      CFG.menuManager.setBackAnimation(true);
   }
}
