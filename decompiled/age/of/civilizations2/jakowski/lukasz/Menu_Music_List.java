package age.of.civilizations2.jakowski.lukasz;

import java.util.ArrayList;

class Menu_Music_List extends SliderMenu {
   protected Menu_Music_List() {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      menuElements.add(new Button_Menu_LR_Line(null, -1, 0, 0, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT, true));
      menuElements.add(new Button_Menu_LR_Line(CFG.langManager.get("ChooseMusic"), -1, 0, 0, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT, false));

      try {
         for (String str : CFG.soundsManager.getlTitles()) {
            menuElements.add(
               new Button_Menu(
                  str.replace(".ogg", ""),
                  (int)(50.0F * CFG.GUI_SCALE),
                  0,
                  menuElements.get(menuElements.size() - 1).getPosY() + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING,
                  CFG.GAME_WIDTH,
                  CFG.BUTTON_HEIGHT,
                  true
               )
            );
         }
      } catch (Exception var4) {
         var4.printStackTrace();
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
      this.getTitle().setText(CFG.langManager.get("MusicList"));
   }

   @Override
   public final void actionElement(int iID) {
      switch (iID) {
         case 0:
            if (CFG.menuManager.getPMMLMenu() == Menu.eSETTINGS) {
               CFG.menuManager.setViewID(Menu.eSETTINGS);
            } else {
               CFG.menuManager.setViewID(Menu.eINGAME);
            }

            return;
         default:
            CFG.soundsManager.loadMusic(iID - 2);
      }
   }
}
