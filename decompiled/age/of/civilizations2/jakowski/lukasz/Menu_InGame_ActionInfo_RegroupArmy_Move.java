package age.of.civilizations2.jakowski.lukasz;

import java.util.ArrayList;

public class Menu_InGame_ActionInfo_RegroupArmy_Move extends SliderMenu {
   public Menu_InGame_ActionInfo_RegroupArmy_Move() {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      menuElements.add(
         new Text_ActionInfo_Move(
            "",
            0,
            CFG.GAME_HEIGHT - CFG.map.getMapBG().getMinimapHeight() - CFG.PADDING - (CFG.TEXT_HEIGHT + CFG.PADDING * 2) - CFG.BUTTON_HEIGHT - CFG.PADDING * 2
         )
      );

      try {
         if (CFG.game.currentRegroupArmy.getRouteSize() > 0
            && CFG.gameAction
               .getIsFreeMove(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), CFG.game.getActiveProvinceID(), CFG.game.currentRegroupArmy.getRoute(0))) {
            menuElements.add(
               new Text_ActionInfo_MovementCost_Right_Free(
                  "-0.0",
                  0,
                  CFG.GAME_HEIGHT
                     - CFG.map.getMapBG().getMinimapHeight()
                     - CFG.PADDING
                     - (CFG.TEXT_HEIGHT + CFG.PADDING * 2)
                     - CFG.BUTTON_HEIGHT
                     - CFG.PADDING * 2
               ) {
                  @Override
                  public void buildElementHover() {
                     ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
                     ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Cost") + ": "));
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text("0.0", CFG.COLOR_TEXT_FREE_MOVE));
                     nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_movement_points, CFG.PADDING, 0));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                     this.menuElementHover = new MenuElement_Hover_v2(nElements);
                  }
               }
            );
         } else {
            menuElements.add(
               new Text_ActionInfo_MovementCost_Right(
                  "-"
                     + CFG.gameAction
                           .costOfMoveArmy(
                              CFG.game.getActiveProvinceID(), CFG.game.currentRegroupArmy.getRoute(0), CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()
                           )
                        / 10.0F,
                  0,
                  CFG.GAME_HEIGHT
                     - CFG.map.getMapBG().getMinimapHeight()
                     - CFG.PADDING
                     - (CFG.TEXT_HEIGHT + CFG.PADDING * 2)
                     - CFG.BUTTON_HEIGHT
                     - CFG.PADDING * 2
               ) {
                  @Override
                  public void buildElementHover() {
                     ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
                     ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Cost") + ": "));
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           ""
                              + CFG.gameAction
                                    .costOfMoveArmy(
                                       CFG.game.getActiveProvinceID(),
                                       CFG.game.currentRegroupArmy.getRoute(0),
                                       CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()
                                    )
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
         }
      } catch (NullPointerException var3) {
         menuElements.add(
            new Text_ActionInfo_MovementCost_Right(
               "-"
                  + CFG.gameAction
                        .costOfMoveArmy(
                           CFG.game.getActiveProvinceID(), CFG.game.currentRegroupArmy.getRoute(0), CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()
                        )
                     / 10.0F,
               0,
               CFG.GAME_HEIGHT
                  - CFG.map.getMapBG().getMinimapHeight()
                  - CFG.PADDING
                  - (CFG.TEXT_HEIGHT + CFG.PADDING * 2)
                  - CFG.BUTTON_HEIGHT
                  - CFG.PADDING * 2
            ) {
               @Override
               public void buildElementHover() {
                  ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
                  ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
                  nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Cost") + ": "));
                  nData.add(
                     new MenuElement_Hover_v2_Element_Type_Text(
                        ""
                           + CFG.gameAction
                                 .costOfMoveArmy(
                                    CFG.game.getActiveProvinceID(), CFG.game.currentRegroupArmy.getRoute(0), CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()
                                 )
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
      }

      menuElements.add(
         new Text_ActionInfo_Turns(
            "",
            0,
            CFG.GAME_HEIGHT
               - CFG.map.getMapBG().getMinimapHeight()
               - CFG.PADDING
               - (CFG.TEXT_HEIGHT + CFG.PADDING * 2) * 2
               - CFG.PADDING
               - CFG.BUTTON_HEIGHT
               - CFG.PADDING * 2
         )
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
