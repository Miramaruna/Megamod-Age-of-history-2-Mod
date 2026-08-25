package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import java.util.ArrayList;

public class Menu_InGame_ActionInfo_Disband extends SliderMenu {
   public Menu_InGame_ActionInfo_Disband() {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      menuElements.add(
         new Text_ActionInfo_Move(
            "",
            0,
            CFG.GAME_HEIGHT - CFG.map.getMapBG().getMinimapHeight() - CFG.PADDING - (CFG.TEXT_HEIGHT + CFG.PADDING * 2) - CFG.BUTTON_HEIGHT - CFG.PADDING * 2
         ) {
            @Override
            public Color getColor(boolean isActive) {
               return isActive
                  ? CFG.COLOR_TEXT_MODIFIER_NEGATIVE
                  : (this.getIsHovered() ? CFG.COLOR_TEXT_MODIFIER_NEGATIVE : CFG.COLOR_TEXT_MODIFIER_NEGATIVE2);
            }
         }
      );
      menuElements.add(
         new Text_ActionInfo_MovementCost_Right(
            "-" + CFG.ideologiesManager.getIdeology(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getIdeologyID()).COST_OF_DISBAND / 10.0F,
            0,
            CFG.GAME_HEIGHT - CFG.map.getMapBG().getMinimapHeight() - CFG.PADDING - (CFG.TEXT_HEIGHT + CFG.PADDING * 2) - CFG.BUTTON_HEIGHT - CFG.PADDING * 2
         ) {
            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Cost") + ": "));
               nData.add(
                  new MenuElement_Hover_v2_Element_Type_Text(
                     ""
                        + CFG.ideologiesManager.getIdeology(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getIdeologyID()).COST_OF_DISBAND
                           / 10.0F,
                     CFG.COLOR_INGAME_MOVEMENT
                  )
               );
               nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_movement_points, CFG.PADDING, 0));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
         }
      );
      menuElements.add(
         new Text_ActionInfo_Cost_Right_Balance_Disband(
            CFG.langManager.get("Balance") + ": ",
            0,
            CFG.GAME_HEIGHT
               - CFG.map.getMapBG().getMinimapHeight()
               - CFG.PADDING
               - (CFG.TEXT_HEIGHT + CFG.PADDING * 2) * 2
               - CFG.PADDING
               - CFG.BUTTON_HEIGHT
               - CFG.PADDING * 2
         ) {}
      );
      this.initMenu(null, 0, 0, CFG.GAME_WIDTH, CFG.GAME_HEIGHT, menuElements, false, false);
   }

   @Override
   public final void actionElement(int iID) {
      switch (iID) {
         case 1:
            CFG.toast.setInView(CFG.langManager.get("MovementPoints") + ": " + this.getMenuElement(1).getText(), CFG.COLOR_INGAME_MOVEMENT_ZERO);
         case 0:
      }
   }

   @Override
   public void onHovered() {
      CFG.menuManager.setOrderOfMenu_InGame_Recruit();
   }
}
