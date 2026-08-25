package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

public class Menu_SelectCivilization extends SliderMenu {
   public Menu_SelectCivilization() {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      menuElements.add(new Minimap(0, CFG.GAME_HEIGHT - CFG.map.getMapBG().getMinimapHeight()));
      menuElements.add(new Button_Game(null, -1, CFG.GAME_WIDTH - CFG.BUTTON_WIDTH - CFG.PADDING, CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING, true));
      menuElements.add(
         new Button_Game(
            null,
            -1,
            CFG.GAME_WIDTH - CFG.BUTTON_WIDTH - CFG.PADDING - CFG.BUTTON_WIDTH - CFG.PADDING,
            CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING,
            CFG.BUTTON_WIDTH * 2,
            true
         )
      );
      this.initMenu(new SliderMenuTitle(null, CFG.BUTTON_HEIGHT * 3 / 4, false, false), 0, 0, CFG.GAME_WIDTH, CFG.GAME_HEIGHT, menuElements);
      this.updateLanguage();
   }

   @Override
   public void updateLanguage() {
      this.getMenuElement(1).setText(CFG.langManager.get("Back"));
      this.getMenuElement(2).setText(CFG.langManager.get("RandomCivilization"));
      this.getTitle().setText(CFG.langManager.get("SelectCivilization"));
      this.updateButtonWidth(2, 0, CFG.BUTTON_WIDTH);
      this.getMenuElement(2).setPosX(this.getMenuElement(1).getPosX() - this.getMenuElement(2).getWidth() - CFG.PADDING);
   }

   @Override
   public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      ImageManager.getImage(Images.editor_line)
         .draw2(
            oSB,
            CFG.map.getMapBG().getMinimapWidth() + iTranslateX,
            CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING * 2 - ImageManager.getImage(Images.editor_line).getHeight() + iTranslateY,
            CFG.GAME_WIDTH - CFG.map.getMapBG().getMinimapWidth(),
            CFG.BUTTON_HEIGHT + CFG.PADDING * 2
         );
      super.draw(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
   }

   @Override
   public void drawTitle(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive, int nPosY) {
      super.drawTitle(oSB, iTranslateX, iTranslateY, sliderMenuIsActive, this.getTitle().getHeight());
   }

   @Override
   public final void actionElement(int iID) {
      switch (iID) {
         case 0:
            CFG.map
               .getMapCoordinates()
               .centerToProvinceID(CFG.game.getCiv(CFG.game.getPlayer(CFG.iSelectCivilizationPlayerID).getCivID()).getCapitalProvinceID());
            break;
         case 1:
            CFG.menuManager.setViewID(Menu.eNEWGAME_PLAYERS);
            break;
         case 2:
            if (CFG.game.getPlayer(CFG.iSelectCivilizationPlayerID).getCivID() > 0) {
               CFG.game.disableDrawCivilizationRegions(CFG.game.getPlayer(CFG.iSelectCivilizationPlayerID).getCivID());
            }

            CFG.game.getPlayer(CFG.iSelectCivilizationPlayerID).setCivID(-1);
            CFG.menuManager.setViewID(Menu.eNEWGAME_PLAYERS);
            CFG.game.setActiveProvinceID(-1);
      }
   }

   @Override
   public final void onBackPressed() {
      CFG.menuManager.setViewID(Menu.eNEWGAME_PLAYERS);
   }
}
