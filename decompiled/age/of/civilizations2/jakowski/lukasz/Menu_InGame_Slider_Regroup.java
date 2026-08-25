package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.utils.ScissorStack;
import java.util.ArrayList;

public class Menu_InGame_Slider_Regroup extends SliderMenu {
   public Menu_InGame_Slider_Regroup() {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      menuElements.add(new Button_Game_Decline(CFG.PADDING, CFG.PADDING, true));
      menuElements.add(new Button_Game_Accept(CFG.GAME_WIDTH - CFG.PADDING - CFG.BUTTON_WIDTH, CFG.PADDING, true) {
         @Override
         public int getSFX() {
            return SoundsManager.SOUND_MOVE_REGROUP;
         }
      });
      menuElements.add(
         new Slider_LR_Perc(
            CFG.BUTTON_WIDTH + CFG.PADDING * 2, CFG.PADDING, CFG.GAME_WIDTH - CFG.BUTTON_WIDTH * 2 - CFG.PADDING * 4, CFG.BUTTON_HEIGHT, 0, 200, 100
         )
      );
      this.initMenu(
         null,
         0,
         CFG.GAME_HEIGHT - CFG.map.getMapBG().getMinimapHeight() - CFG.BUTTON_HEIGHT - CFG.PADDING * 2,
         CFG.GAME_WIDTH,
         CFG.BUTTON_HEIGHT + CFG.PADDING * 2,
         menuElements,
         false,
         false
      );
      this.updateLanguage();
      CFG.fMOVE_MENU_PERCENTAGE = 5.0F;
      CFG.lMOVE_MENU_TIME = System.currentTimeMillis();
   }

   @Override
   public void updateLanguage() {
   }

   @Override
   public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      if ((CFG.fMOVE_MENU_PERCENTAGE = CFG.fMOVE_MENU_PERCENTAGE + (float)(System.currentTimeMillis() - CFG.lMOVE_MENU_TIME) / 250.0F * 95.0F) > 100.0F) {
         CFG.fMOVE_MENU_PERCENTAGE = 100.0F;
      } else {
         CFG.setRender_3(true);
      }

      CFG.lMOVE_MENU_TIME = System.currentTimeMillis();
      Rectangle clipBounds = new Rectangle(this.getPosX() + iTranslateX, CFG.GAME_HEIGHT - this.getPosY() - iTranslateY, this.getWidth(), -this.getHeight());
      oSB.flush();
      ScissorStack.pushScissors(clipBounds);
      ImageManager.getImage(Images.bg_game_menu)
         .draw2(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY()
               - ImageManager.getImage(Images.bg_game_menu).getHeight()
               + (int)(this.getHeight() * (100.0F - CFG.fMOVE_MENU_PERCENTAGE) / 100.0F)
               + iTranslateY,
            this.getWidth(),
            this.getHeight()
         );
      oSB.setColor(CFG.COLOR_BG_GAME_MENU_SHADOW);
      ImageManager.getImage(Images.pix255_255_255)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() + this.getHeight() - 1 - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY,
            this.getWidth(),
            1
         );
      ImageManager.getImage(Images.line_32_off1)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() + this.getHeight() - 1 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
            this.getWidth(),
            1
         );
      oSB.setColor(Color.WHITE);
      super.draw(oSB, iTranslateX, (int)(this.getHeight() * (100.0F - CFG.fMOVE_MENU_PERCENTAGE) / 100.0F) + iTranslateY, sliderMenuIsActive);
   }

   @Override
   public void beginClip(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
   }

   @Override
   public void extraAction() {
      try {
         if (CFG.game.currentRegroupArmy.getRouteSize() == 1) {
            CFG.gameAction
               .moveArmy(
                  CFG.game.getActiveProvinceID(),
                  CFG.chosenProvinceID,
                  this.getMenuElement(2).getCurrent(),
                  CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(),
                  true,
                  true
               );
         } else if (CFG.gameAction
            .moveArmy(
               CFG.game.getActiveProvinceID(),
               CFG.game.currentRegroupArmy.getRoute(0),
               this.getMenuElement(2).getCurrent(),
               CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(),
               true,
               true
            )) {
            if (CFG.chosenProvinceID >= 0 && CFG.game.getProvince(CFG.chosenProvinceID).getCivID() > 0 && CFG.game.getCivsAtWar(
                  CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(),
                  CFG.game.getProvince(CFG.chosenProvinceID).getCivID()
               )) {
               VoiceManager.playAttack();
            } else {
               VoiceManager.playMove();
            }

            CFG.game.currentRegroupArmy.setFromProvinceID(CFG.game.currentRegroupArmy.getRoute(0));
            CFG.game.currentRegroupArmy.removeRoute(0);
            CFG.game.currentRegroupArmy.setNumOfUnits(this.getMenuElement(2).getCurrent());
            CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).addRegroupArmy(CFG.game.currentRegroupArmy);
         }

         try {
            if (this.getMenuElement(2).getCurrent() < 100
               && CFG.game.getProvince(CFG.chosenProvinceID).getCivID() > 0
               && CFG.game.getCivsAtWar(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), CFG.game.getProvince(CFG.chosenProvinceID).getCivID())) {
               CFG.toast
                  .setInView(
                     CFG.langManager.get("MinArmyRequiredToAttack") + ": " + 100 + " " + CFG.langManager.get("Units"), CFG.COLOR_TEXT_MODIFIER_NEGATIVE2
                  );
               CFG.toast.setTimeInView(4500);
            }
         } catch (IndexOutOfBoundsException var2) {
         }

         CFG.game.resetRegroupArmyData();
         CFG.game.checkProvinceActionMenu();
         CFG.menuManager.updateInGame_TOP_All(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
         CFG.game.getPlayer(CFG.PLAYER_TURNID).setNoOrders(false);
         if (RTS.isEnabled() && !RTS.PAUSE) {
            RTS.updateTimePast_AfterAction(0.75F);
         }
      } catch (IndexOutOfBoundsException var3) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var3);
         }
      }
   }

   @Override
   public final void actionElement(int iID) {
      switch (iID) {
         case 0:
            CFG.game.resetRegroupArmyData();
            CFG.game.checkProvinceActionMenu();
            if (RTS.isEnabled() && !RTS.PAUSE) {
               RTS.updateTimePast_AfterAction(0.5F);
            }
            break;
         case 1:
            this.extraAction();
            break;
         case 2:
            CFG.menuManager.updateInGame_ActionInfo_Regroup();
      }
   }

   @Override
   public void setVisible(boolean visible) {
      super.setVisible(visible);
      if (visible) {
         CFG.fMOVE_MENU_PERCENTAGE = 5.0F;
         CFG.lMOVE_MENU_TIME = System.currentTimeMillis();
      }
   }

   @Override
   public void onHovered() {
      CFG.menuManager.setOrderOfMenu_InGame_Recruit();
   }
}
