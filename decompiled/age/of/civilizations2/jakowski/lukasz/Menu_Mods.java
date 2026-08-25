package age.of.civilizations2.jakowski.lukasz;

import java.util.ArrayList;

public class Menu_Mods extends SliderMenu {
   public Menu_Mods() {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      int tY = CFG.PADDING;

      for (int i = 0; i < Plusic.mods.size(); i++) {
         menuElements.add(
            new Button_Menu(
               "[" + Plusic.mods.get(i).getVersion() + "] " + Plusic.mods.get(i).getName() + " by " + Plusic.mods.get(i).getAuthor(),
               (int)(50.0F * CFG.GUI_SCALE),
               0,
               tY,
               CFG.GAME_WIDTH,
               CFG.BUTTON_HEIGHT,
               true
            )
         );
         tY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
      }

      this.initMenu(
         null, 0, CFG.BUTTON_HEIGHT * 3 / 4, CFG.GAME_WIDTH, CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT * 3 / 4 - CFG.BUTTON_HEIGHT - CFG.PADDING, menuElements
      );
      this.updateLanguage();
   }

   @Override
   public void updateLanguage() {
   }

   @Override
   public final void actionElement(int iID) {
   }
}
