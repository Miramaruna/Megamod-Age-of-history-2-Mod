package age.of.civilizations2.jakowski.lukasz;

import java.util.ArrayList;

public class Menu_Download_Pallets extends SliderMenu {
   public Menu_Download_Pallets() {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      menuElements.add(new Button_Menu(null, -1, 0, CFG.PADDING, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT, true));
      menuElements.add(new Button_Menu(null, -1, 0, CFG.PADDING, CFG.BUTTON_WIDTH * 2, CFG.BUTTON_HEIGHT, true));
      menuElements.add(new Button_Menu_ReflectedBG(null, -1, CFG.GAME_WIDTH - CFG.BUTTON_WIDTH * 2, CFG.PADDING, CFG.BUTTON_WIDTH * 2, CFG.BUTTON_HEIGHT, true));
      menuElements.add(
         new Button_Menu_Classic(null, -1, CFG.BUTTON_WIDTH * 2, CFG.PADDING, (CFG.GAME_WIDTH - CFG.BUTTON_WIDTH * 4) / 2, CFG.BUTTON_HEIGHT, true)
      );
      menuElements.add(
         new Button_Menu_Classic(
            null,
            -1,
            CFG.BUTTON_WIDTH * 2 + (CFG.GAME_WIDTH - CFG.BUTTON_WIDTH * 4) / 2,
            CFG.PADDING,
            (CFG.GAME_WIDTH - CFG.BUTTON_WIDTH * 4) / 2,
            CFG.BUTTON_HEIGHT,
            true
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
   }

   @Override
   public void updateLanguage() {
      this.getMenuElement(0).setText(CFG.langManager.get("Back"));
      this.getMenuElement(1).setText("<<");
      this.getMenuElement(2).setText(">>");
      this.getMenuElement(3).setText("ByRep");
      this.getMenuElement(4).setText("ByNum");
      this.getTitle().setText(CFG.langManager.get("Download") + " - " + CFG.langManager.get("PalletsOfColors"));
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
      CFG.menuManager.setViewID(CFG.backToMenu);
      CFG.menuManager.setBackAnimation(true);
   }
}
