package age.of.civilizations2.jakowski.lukasz;

import java.util.ArrayList;

public class Menu_CustomizeAlliance extends SliderMenu {
   public Menu_CustomizeAlliance() {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      menuElements.add(new Button_Menu_LR_Line(null, -1, 0, CFG.PADDING, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT, true));
      this.initMenu(null, 0, CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT + CFG.PADDING, menuElements);
      this.updateLanguage();
   }

   @Override
   public void updateLanguage() {
      this.getMenuElement(0).setText(CFG.langManager.get("Save"));
   }

   @Override
   public final void actionElement(int iID) {
      switch (iID) {
         case 0:
            this.onBackPressed();
      }
   }

   @Override
   public final void onBackPressed() {
      CFG.menuManager.getColorPicker().setVisible(false, null);
      CFG.game.checkAlliances();
      CFG.menuManager.rebuildManageDiplomacy_Alliances();
      CFG.MANAGE_DIPLOMACY_CUSTOMIZE_ALLIANCE_ID = 0;
      CFG.menuManager.setViewID(Menu.eMANAGE_DIPLOMACY);
      CFG.menuManager.setBackAnimation(true);
   }
}
