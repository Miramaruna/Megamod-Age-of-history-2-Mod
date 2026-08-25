package age.of.civilizations2.jakowski.lukasz;

import java.util.ArrayList;

public class Menu_InGame_FormAnimation extends SliderMenu {
   public Menu_InGame_FormAnimation() {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      menuElements.add(new Button_Transparent(0, 0, CFG.GAME_WIDTH, CFG.GAME_HEIGHT, true));
      this.initMenuWithBackButton(null, 0, 0, CFG.GAME_WIDTH, CFG.GAME_HEIGHT, menuElements);
      this.updateLanguage();
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
      CFG.menuManager.setViewIDWithoutAnimation(Menu.eINGAME);
      CFG.menuManager.setVisible_InGame_CivInfo(false);
      CFG.map.getMapBG().updateWorldMap_Shaders();
      CFG.game.setActiveProvinceID(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCapitalProvinceID());
   }
}
