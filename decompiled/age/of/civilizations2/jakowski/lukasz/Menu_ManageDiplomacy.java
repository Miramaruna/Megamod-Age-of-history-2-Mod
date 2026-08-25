package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

public class Menu_ManageDiplomacy extends SliderMenu {
   public Menu_ManageDiplomacy() {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      menuElements.add(
         new Button_Game(
            null, -1, CFG.GAME_WIDTH - CFG.BUTTON_WIDTH * 2 - CFG.PADDING, CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING, CFG.BUTTON_WIDTH * 2, true
         ) {
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
      this.initMenu(null, 0, 0, CFG.GAME_WIDTH, CFG.GAME_HEIGHT, menuElements);
      this.updateLanguage();
   }

   @Override
   public void updateLanguage() {
      this.getMenuElement(0).setText(CFG.langManager.get("Back"));
      CFG.sAtWar = CFG.langManager.get("AtWar");
   }

   @Override
   public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      ImageManager.getImage(Images.editor_line)
         .draw2(
            oSB,
            this.getMenuElement(0).getPosX() - CFG.PADDING + iTranslateX,
            this.getMenuElement(0).getPosY() - CFG.PADDING - ImageManager.getImage(Images.editor_line).getHeight() + iTranslateY,
            this.getMenuElement(0).getWidth() + CFG.PADDING * 2,
            this.getMenuElement(0).getHeight() + CFG.PADDING * 2
         );
      super.draw(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
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
      CFG.game.checkAlliances();
      CFG.game.setActiveProvinceID(-1);
      CFG.map.getMapCoordinates().setDisableMovingMap(false);
      CFG.menuManager.setViewID(CFG.backToMenu);
      CFG.menuManager.setBackAnimation(true);
      CFG.menuManager.getColorPicker().setVisible(false, null);
      Game_Render_Province.updateDrawProvinces();
      CFG.map.getMapTouchManager().update_ExtraAction();
   }
}
