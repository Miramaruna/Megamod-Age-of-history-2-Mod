package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

public class Menu_SelectAvailableCivilizations extends SliderMenu {
   public String selectAvailableCivilizations;
   public int iTitleWidth;

   public Menu_SelectAvailableCivilizations() {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      menuElements.add(new Button_Game(null, -1, CFG.PADDING, CFG.PADDING, true));
      menuElements.add(new Button_Game(null, -1, CFG.GAME_WIDTH - CFG.BUTTON_WIDTH * 2 - CFG.PADDING, CFG.PADDING, CFG.BUTTON_WIDTH * 2, true));
      menuElements.add(new Minimap(CFG.GAME_WIDTH - CFG.map.getMapBG().getMinimapWidth(), CFG.GAME_HEIGHT - CFG.map.getMapBG().getMinimapHeight()));
      menuElements.add(new Button_Game_Checkbox(null, -1, CFG.PADDING, CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING, CFG.BUTTON_WIDTH * 2, false, false));
      this.initMenu(null, 0, 0, CFG.GAME_WIDTH, CFG.GAME_HEIGHT, menuElements);
      this.updateLanguage();
   }

   @Override
   public void updateLanguage() {
      super.updateLanguage();
      this.getMenuElement(0).setText(CFG.langManager.get("Back"));
      this.getMenuElement(1).setText(CFG.langManager.get("Save"));
      this.getMenuElement(3).setText(CFG.langManager.get("Disable"));
      this.selectAvailableCivilizations = CFG.langManager.get("SelectAvailableCivilizations");
      CFG.glyphLayout.setText(CFG.fontMain, this.selectAvailableCivilizations + " [XX]");
      this.iTitleWidth = (int)CFG.glyphLayout.width;
   }

   @Override
   public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      CFG.drawEditorTitle_Edge_LR(oSB, iTranslateX, this.getMenuPosY() + iTranslateY, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT + CFG.PADDING * 2);
      CFG.drawTextWithShadow(
         oSB,
         this.selectAvailableCivilizations + " [" + CFG.game.getAvailableCivilizations() + "]",
         CFG.GAME_WIDTH / 2 - this.iTitleWidth / 2 + iTranslateX,
         CFG.BUTTON_HEIGHT / 2 - CFG.TEXT_HEIGHT / 2 + CFG.PADDING + this.getMenuPosY() + iTranslateY,
         Color.WHITE
      );
      if (this.getMenuElement(3).getVisible()) {
         CFG.drawEditorButtons_Bot_Edge_R(
            oSB,
            iTranslateX,
            this.getMenuElement(3).getPosY() - CFG.PADDING,
            this.getMenuElement(3).getPosX() + this.getMenuElement(3).getWidth() + CFG.PADDING,
            CFG.BUTTON_HEIGHT + CFG.PADDING * 2
         );
      }

      super.draw(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
   }

   @Override
   public final void actionElement(int iID) {
      switch (iID) {
         case 0:
            this.onBackPressed();
            return;
         case 1:
            if (CFG.game.getActiveProvinceID() >= 0) {
               CFG.game.disableDrawCivilizationRegions(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID());
            }

            CFG.game.setActiveProvinceID(-1);
            CFG.game.disableNonPlayableCivilizations();
            CFG.game.checkPlayersCivilizations();
            CFG.menuManager.setViewID(Menu.eCREATE_NEW_GAME);
            return;
         case 2:
            CFG.map
               .getMapCoordinates()
               .centerToMinimapClick(
                  Touch.getMousePosX() - this.getMenuElement(iID).getPosX() - this.getPosX(),
                  Touch.getMousePosY() - this.getMenuElement(iID).getPosY() - this.getMenuPosY()
               );
         default:
            super.actionElement(iID);
            return;
         case 3:
            if (CFG.game.getActiveProvinceID() >= 0 && CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID() > 0) {
               CFG.game
                  .getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID())
                  .setIsAvailable(!CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).getIsAvailable());
               CFG.game.updateNumOfAvailableCivilizations();
               this.getMenuElement(iID).setCheckboxState(CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).getIsAvailable());
               if (CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).getIsAvailable()) {
                  this.getMenuElement(iID)
                     .setText(
                        CFG.langManager.get("Disable") + " - " + CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).getCivName()
                     );
                  CFG.game.enableDrawCivilizationRegions(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID(), 0);
               } else {
                  this.getMenuElement(iID)
                     .setText(
                        CFG.langManager.get("Enable") + " - " + CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).getCivName()
                     );
                  CFG.game.disableDrawCivilizationRegions(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID());
                  CFG.game.setActiveProvinceID(CFG.game.getActiveProvinceID());
               }

               this.updateButtonWidth(iID, CFG.PADDING, CFG.BUTTON_WIDTH * 2);
            }
      }
   }

   @Override
   public void onBackPressed() {
      for (int i = 1; i < CFG.game.getCivsSize(); i++) {
         CFG.game.getCiv(i).setIsAvailable(true);
      }

      if (CFG.game.getActiveProvinceID() >= 0) {
         CFG.game.disableDrawCivilizationRegions(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID());
      }

      CFG.game.setActiveProvinceID(-1);
      CFG.menuManager.setViewID(Menu.eCHOOSE_SCENARIO);
   }
}
