package age.of.civilizations2.jakowski.lukasz;

import java.util.ArrayList;

public class Menu_Editor_Translations extends SliderMenu {
   public Menu_Editor_Translations() {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      menuElements.add(new Button_Menu_LR_Line(null, -1, 0, 0, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT, true));
      menuElements.add(new Button_Menu_LR_Line(null, -1, 0, CFG.PADDING, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT, true));
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
      this.getMenuElement(1).setText(CFG.langManager.get("CreateTranslation"));
      this.getTitle().setText(CFG.langManager.get("TranslationEditor"));
   }

   @Override
   public final void actionElement(int iID) {
      switch (iID) {
         case 0:
            this.onBackPressed();
            return;
         case 1:
            CFG.menuManager.setViewID(Menu.eCREATE_TRANSLATION);
            return;
      }
   }

   @Override
   public void onBackPressed() {
      CFG.menuManager.setViewID(Menu.eGAME_EDITOR);
      CFG.menuManager.setBackAnimation(true);
   }
}
