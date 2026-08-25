package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;
import java.util.List;

public class Menu_InGame_ActionInfo_Move extends SliderMenu {
   public Menu_InGame_ActionInfo_Move() {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      menuElements.add(
         new Text_ActionInfo_Move(
            "",
            0,
            CFG.GAME_HEIGHT - CFG.map.getMapBG().getMinimapHeight() - CFG.PADDING - (CFG.TEXT_HEIGHT + CFG.PADDING * 2) - CFG.BUTTON_HEIGHT - CFG.PADDING * 2
         )
      );
      if (CFG.gameAction.getIsFreeMove(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), CFG.game.getActiveProvinceID(), CFG.chosenProvinceID)) {
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
                  + CFG.gameAction.costOfMoveArmy(CFG.game.getActiveProvinceID(), CFG.chosenProvinceID, CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
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
                                 .costOfMoveArmy(CFG.game.getActiveProvinceID(), CFG.chosenProvinceID, CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
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

      int tRes = CFG.gameAction
         .moveArmyModifiers_Attackers(CFG.game.getActiveProvinceID(), CFG.chosenProvinceID, CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
      if (tRes != 0) {
         menuElements.add(
            new Text_ActionInfo_ArmyBonus(
               CFG.langManager.get("Attackers") + ": ",
               "" + (tRes > 0 ? "+" : "") + tRes + "%",
               0,
               CFG.GAME_HEIGHT
                  - CFG.map.getMapBG().getMinimapHeight()
                  - CFG.PADDING
                  - (CFG.TEXT_HEIGHT + CFG.PADDING * 2)
                  - CFG.BUTTON_HEIGHT
                  - CFG.PADDING * 2
                  - (CFG.TEXT_HEIGHT + CFG.PADDING * 2)
                  - CFG.PADDING
            ) {
               @Override
               public void buildElementHover() {
                  try {
                     new ArrayList();
                     new ArrayList();
                     List<MenuElement_Hover_v2_Element2> ex = CFG.gameAction
                        .getMoveArmyModifiers_Attackers_Hover(
                           CFG.game.getActiveProvinceID(), CFG.chosenProvinceID, CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()
                        );
                     this.menuElementHover = new MenuElement_Hover_v2(ex);
                  } catch (IndexOutOfBoundsException var3) {
                     this.menuElementHover = null;
                  }
               }

               @Override
               public void drawMenuElementHover2(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                  if (this.menuElementHover != null) {
                     this.menuElementHover.drawAlwaysOver(oSB, Touch.getMousePosX(), this.getPosY());
                  }
               }
            }
         );
      }

      if (tRes != 0) {
         menuElements.add(
            new Text_ActionInfo_Right_ArmyBonues(
               CFG.langManager.get("Defenders") + ": ",
               "" + (tRes > 0 ? "+" : "") + tRes + "%",
               0,
               CFG.GAME_HEIGHT
                  - CFG.map.getMapBG().getMinimapHeight()
                  - CFG.PADDING
                  - (CFG.TEXT_HEIGHT + CFG.PADDING * 2)
                  - CFG.BUTTON_HEIGHT
                  - CFG.PADDING * 2
                  - (CFG.TEXT_HEIGHT + CFG.PADDING * 2)
                  - CFG.PADDING
            ) {
               boolean isPositive = true;

               @Override
               public void buildElementHover() {
                  try {
                     new ArrayList();
                     new ArrayList();
                     List<MenuElement_Hover_v2_Element2> ex = CFG.gameAction
                        .getMoveArmyModifiers_Defenders_Hover(CFG.game.getActiveProvinceID(), CFG.chosenProvinceID);
                     this.menuElementHover = new MenuElement_Hover_v2(ex);
                  } catch (IndexOutOfBoundsException var3) {
                     this.menuElementHover = null;
                  }
               }

               @Override
               public void drawMenuElementHover2(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                  if (this.menuElementHover != null) {
                     this.menuElementHover.drawAlwaysOver(oSB, Touch.getMousePosX(), this.getPosY());
                  }
               }

               @Override
               public Color getColorValue() {
                  return this.isPositive ? super.getColorValue() : CFG.COLOR_TEXT_MODIFIER_NEGATIVE2;
               }

               @Override
               public void setCurrent(int nCurrent) {
                  this.isPositive = nCurrent >= 0;
               }
            }
         );
         menuElements.get(menuElements.size() - 1).setCurrent(tRes);
      }

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
