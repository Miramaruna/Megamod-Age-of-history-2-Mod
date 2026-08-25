package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.utils.ScissorStack;
import java.util.ArrayList;

public class Menu_InGame_ActionInfo_RegroupArmy extends SliderMenu {
   public Menu_InGame_ActionInfo_RegroupArmy() {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      menuElements.add(new Button_Game_Decline(CFG.PADDING, CFG.PADDING, true));
      menuElements.add(
         new Text_ActionInfo_Move(
            null, CFG.BUTTON_WIDTH + CFG.PADDING * 2, CFG.BUTTON_HEIGHT + CFG.PADDING * 2 - CFG.PADDING - (CFG.TEXT_HEIGHT + CFG.PADDING * 2)
         )
      );
      this.initMenu(
         null,
         0,
         CFG.GAME_HEIGHT - CFG.map.getMapBG().getMinimapHeight() - CFG.BUTTON_HEIGHT - CFG.PADDING * 2,
         CFG.GAME_WIDTH,
         CFG.BUTTON_HEIGHT + CFG.PADDING * 2,
         menuElements,
         true,
         false
      );
      this.updateLanguage();
      CFG.fMOVE_MENU_PERCENTAGE = 5.0F;
      CFG.lMOVE_MENU_TIME = System.currentTimeMillis();
   }

   @Override
   public void updateLanguage() {
      this.getMenuElement(1).setText(CFG.langManager.get("ChooseAProvince"));
   }

   @Override
   public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      if ((CFG.fMOVE_MENU_PERCENTAGE = CFG.fMOVE_MENU_PERCENTAGE + (float)(System.currentTimeMillis() - CFG.lMOVE_MENU_TIME) / 300.0F * 95.0F) > 100.0F) {
         CFG.fMOVE_MENU_PERCENTAGE = 100.0F;
      } else {
         CFG.setRender_3(true);
      }

      CFG.lMOVE_MENU_TIME = System.currentTimeMillis();
      Rectangle clipBounds = new Rectangle(
         this.getPosX() + iTranslateX, CFG.GAME_HEIGHT - this.getPosY() + 1 - iTranslateY, this.getWidth(), -this.getHeight() - 1
      );
      oSB.flush();
      ScissorStack.pushScissors(clipBounds);
      ImageManager.getImage(Images.bg_game_action)
         .draw2(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY()
               - ImageManager.getImage(Images.bg_game_action).getHeight()
               + (int)(this.getHeight() * (100.0F - CFG.fMOVE_MENU_PERCENTAGE) / 100.0F)
               - 1
               + iTranslateY,
            this.getMenuElement(0).getPosX() + this.getMenuElement(0).getWidth() + CFG.PADDING + 1,
            this.getHeight() + 1,
            true,
            false
         );
      super.draw(oSB, iTranslateX, (int)(this.getHeight() * (100.0F - CFG.fMOVE_MENU_PERCENTAGE) / 100.0F) + iTranslateY, sliderMenuIsActive);
   }

   @Override
   public void beginClip(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
   }

   @Override
   public final void actionElement(int iID) {
      switch (iID) {
         case 0:
            CFG.game.resetRegroupArmyData();
            CFG.game.checkProvinceActionMenu();
            break;
         case 1:
            CFG.toast.setInView(this.getMenuElement(1).getText());
      }
   }

   @Override
   public void onHovered() {
      CFG.menuManager.setOrderOfMenu_InGame_Recruit();
   }
}
