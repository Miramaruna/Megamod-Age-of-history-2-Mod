package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import java.util.ArrayList;

public class Menu_InGame_ActionInfo_NoMovementPoints extends SliderMenu {
   public Menu_InGame_ActionInfo_NoMovementPoints() {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      menuElements.add(
         new Text_ActionInfo(
            null,
            0,
            CFG.GAME_HEIGHT - CFG.map.getMapBG().getMinimapHeight() - CFG.PADDING - (CFG.TEXT_HEIGHT + CFG.PADDING * 2) - CFG.BUTTON_HEIGHT - CFG.PADDING * 2
         ) {
            @Override
            public Color getColor(boolean isActive) {
               return isActive
                  ? CFG.COLOR_INGAME_MOVEMENT_ZERO_ACTIVE
                  : (this.getIsHovered() ? CFG.COLOR_INGAME_MOVEMENT_ZERO_HOVER : CFG.COLOR_INGAME_MOVEMENT_ZERO);
            }
         }
      );
      this.initMenu(null, 0, 0, CFG.GAME_WIDTH, CFG.GAME_HEIGHT, menuElements, false, false);
      this.updateLanguage();
   }

   @Override
   public void updateLanguage() {
      this.getMenuElement(0).setText(CFG.langManager.get("NoMovementPoints") + ".");
   }

   @Override
   public final void actionElement(int iID) {
      switch (iID) {
         case 0:
            this.setVisible(false);
      }
   }

   @Override
   public void onHovered() {
      CFG.menuManager.setOrderOfMenu_InGame_Recruit();
   }
}
