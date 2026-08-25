package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

public class Menu_InGame_TradeRequest_SelectCiv extends SliderMenu {
   public static Menu_InGame_SelectProvinces.TypeOfAction typeOfAction = Menu_InGame_SelectProvinces.TypeOfAction.TRADE_LEFT_DECLAREWAR;

   public Menu_InGame_TradeRequest_SelectCiv() {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      menuElements.add(
         new Button_Game(null, -1, CFG.PADDING, CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING, CFG.BUTTON_WIDTH * 2, true) {
            @Override
            public Color getColor(boolean isActive) {
               return isActive
                  ? CFG.COLOR_BUTTON_GAME_TEXT_IMPORTANT_ACTIVE
                  : (
                     this.getClickable()
                        ? (this.getIsHovered() ? CFG.COLOR_BUTTON_GAME_TEXT_IMPORTANT_HOVER : CFG.COLOR_BUTTON_GAME_TEXT_IMPORTANT)
                        : CFG.COLOR_BUTTON_GAME_TEXT_NOT_CLICKABLE
                  );
            }
         }
      );
      menuElements.add(
         new Button_CivName(
            CFG.MANAGE_DIPLOMACY_CUSTOMIZE_ALLIANCE_ID,
            0,
            CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING * 3 - Math.max(CFG.TEXT_HEIGHT + CFG.PADDING * 4, CFG.CIV_FLAG_HEIGHT + CFG.PADDING * 2),
            CFG.CIV_INFO_MENU_WIDTH * 3 / 4,
            Math.max(CFG.TEXT_HEIGHT + CFG.PADDING * 4, CFG.CIV_FLAG_HEIGHT + CFG.PADDING * 2),
            true
         )
      );
      this.initMenu(null, 0, 0, CFG.GAME_WIDTH, CFG.GAME_HEIGHT, menuElements);
      this.updateLanguage();
   }

   @Override
   public void updateLanguage() {
      this.getMenuElement(0).setText(CFG.langManager.get("Back"));
   }

   @Override
   public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      CFG.drawEditorButtons_Bot_Edge_R(
         oSB,
         this.getMenuElement(0).getPosX() - CFG.PADDING + iTranslateX,
         this.getMenuPosY() + this.getMenuElement(0).getPosY() - CFG.PADDING + iTranslateY,
         this.getMenuElement(0).getPosX() + this.getMenuElement(0).getWidth() + CFG.PADDING,
         this.getMenuElement(0).getHeight() + CFG.PADDING * 2
      );
      super.draw(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
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
      CFG.brushTool = false;
      CFG.menuManager.setViewID(Menu.eINGAME);
      Game_Render_Province.updateDrawProvinces();
      CFG.viewsManager.setActiveViewID(CFG.game.getPlayer(CFG.PLAYER_TURNID).iACTIVE_VIEW_MODE);
   }
}
