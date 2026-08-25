package age.of.civilizations2.jakowski.lukasz;

import java.util.ArrayList;

public class Menu_Help extends SliderMenu {
   public Menu_Help() {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      menuElements.add(new Button_Menu_LR_Line(null, -1, 0, 0, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT, true));
      menuElements.add(
         new Button_Menu_LR_Line(
            null, -1, 0, CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT * 2 - CFG.PADDING - CFG.BUTTON_HEIGHT * 3 / 4, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT, true
         )
      );
      menuElements.add(
         new TextSlider(
            CFG.PADDING, CFG.PADDING, CFG.GAME_WIDTH - CFG.PADDING * 2, CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT * 2 - CFG.PADDING * 4 - CFG.BUTTON_HEIGHT * 3 / 4
         )
      );
      this.initMenuWithBackButton(
         new SliderMenuTitle(null, CFG.BUTTON_HEIGHT * 3 / 4, false, false),
         0,
         CFG.BUTTON_HEIGHT * 3 / 4,
         CFG.GAME_WIDTH,
         CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT * 3 / 4,
         menuElements
      );
      this.updateLanguage();
      this.getMenuElement(2).addText(CFG.langManager.get("WelcomeToTheTutorial"), CFG.PADDING);
      this.getMenuElement(2).addText(CFG.langManager.get("t0"), CFG.PADDING / 2);
      this.getMenuElement(2).addText(CFG.langManager.get("t1"), CFG.PADDING / 2);
      this.getMenuElement(2).addText(CFG.langManager.get("t2"), CFG.TEXT_HEIGHT / 2 + CFG.PADDING);
      this.getMenuElement(2).addText(CFG.langManager.get("t7"), CFG.PADDING / 2);
      this.getMenuElement(2).addText(CFG.langManager.get("t8"), CFG.TEXT_HEIGHT / 2 + CFG.PADDING);
      this.getMenuElement(2).addText(CFG.langManager.get("t4"), CFG.PADDING / 2);
      this.getMenuElement(2).addText(CFG.langManager.get("t5"), CFG.PADDING / 2);
      this.getMenuElement(2).addText(CFG.langManager.get("t6"), CFG.PADDING / 2);
      this.getMenuElement(2).addText(CFG.langManager.get("t6a"), CFG.TEXT_HEIGHT / 2 + CFG.PADDING);
      this.getMenuElement(2).addText(CFG.langManager.get("h5"), CFG.TEXT_HEIGHT / 2 + CFG.PADDING);
      this.getMenuElement(2).addText(CFG.langManager.get("h6"), CFG.TEXT_HEIGHT / 2 + CFG.PADDING);
      this.getMenuElement(2).addText(CFG.langManager.get("h7"), CFG.TEXT_HEIGHT / 2 + CFG.PADDING);
      this.getMenuElement(2).addText(CFG.langManager.get("h8"), CFG.TEXT_HEIGHT / 2 + CFG.PADDING);
      this.getMenuElement(2).addText(CFG.langManager.get("h9"), CFG.TEXT_HEIGHT / 2 + CFG.PADDING);
      this.getMenuElement(2).addText(CFG.langManager.get("h10"), CFG.TEXT_HEIGHT / 2 + CFG.PADDING);
      this.getMenuElement(2).addText(CFG.langManager.get("h11"), 0);
      this.getMenuElement(2).addText(CFG.langManager.get("h12"), 0);
      this.getMenuElement(2).addText(CFG.langManager.get("h13"), 0);
      this.getMenuElement(2).addText(CFG.langManager.get("h14"), 0);
      this.getMenuElement(2).addText(CFG.langManager.get("h15"), 0);
      this.getMenuElement(2).addText(CFG.langManager.get("h16"), 0);
   }

   @Override
   public void updateLanguage() {
      this.getMenuElement(0).setText(CFG.langManager.get("Back"));
      this.getMenuElement(1).setText(CFG.langManager.get("Tutorial"));
      this.getTitle().setText(CFG.langManager.get("Help"));
   }

   @Override
   public final void actionElement(int iID) {
      switch (iID) {
         case 0:
            this.onBackPressed();
            break;
         case 1:
            CFG.setDialogType(Dialog.START_TUTORIAL);
      }
   }

   @Override
   public final void onBackPressed() {
      CFG.menuManager.setViewID(Menu.eMAINMENU);
      CFG.menuManager.setBackAnimation(true);
   }
}
