package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.utils.ScissorStack;
import java.util.ArrayList;

public class Menu_InGame_Slider_RecruitInstantly extends SliderMenu {
   public Menu_InGame_Slider_RecruitInstantly() {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      menuElements.add(new Button_Game_Decline(CFG.PADDING, CFG.PADDING, true));
      menuElements.add(
         new Button_Game_Accept(CFG.GAME_WIDTH - CFG.PADDING - CFG.BUTTON_WIDTH, CFG.PADDING, true) {
            @Override
            public int getSFX() {
               return SoundsManager.SOUND_RECRUIT;
            }

            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("RecruitArmyInstantly"), CFG.COLOR_TEXT_NUM_OF_PROVINCES));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("CostOfRecruitingWillBeDoubled"), CFG.COLOR_TEXT_MODIFIER_NEGATIVE2));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }

            @Override
            public void drawMenuElementHover2(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
               if (this.menuElementHover != null) {
                  this.menuElementHover
                     .drawAlwaysOver(oSB, Touch.getMousePosX(), CFG.GAME_HEIGHT - CFG.map.getMapBG().getMinimapHeight() - CFG.BUTTON_HEIGHT - CFG.PADDING * 2);
               }
            }
         }
      );
      menuElements.add(
         new Slider_LR_Perc(
            CFG.BUTTON_WIDTH + CFG.PADDING * 2, CFG.PADDING, CFG.GAME_WIDTH - CFG.BUTTON_WIDTH * 2 - CFG.PADDING * 4, CFG.BUTTON_HEIGHT, 0, 200, 100
         ) {
            @Override
            public Color getColorLEFT() {
               return CFG.COLOR_SLIDER_LEFT_INSTANTLY;
            }
         }
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
      try {
         CFG.fMOVE_MENU_PERCENTAGE = CFG.fMOVE_MENU_PERCENTAGE + (float)(System.currentTimeMillis() - CFG.lMOVE_MENU_TIME) / 250.0F * 95.0F;
         if (CFG.fMOVE_MENU_PERCENTAGE > 100.0F) {
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
      } catch (IndexOutOfBoundsException var6) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var6);
         }
      }
   }

   @Override
   public void beginClip(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
   }

   @Override
   public void extraAction() {
      CFG.menuManager.setVisible_InGame_ProvinceRecruitInstantly(false);
      CFG.gameAction
         .recruitArmyInstantly(CFG.game.getActiveProvinceID(), this.getMenuElement(2).getCurrent(), CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
      CFG.game.checkProvinceActionMenu();
      CFG.game.autoBuildChooseProvinceMode(true);
      CFG.menuManager.updateInGame_TOP_All(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
      CFG.gameAction.updateInGame_ProvinceInfo();
      if (CFG.menuManager.getVisibleInGame_CensusOfProvince()) {
         CFG.menuManager.rebuildInGame_CensusOfProvince(CFG.game.getActiveProvinceID());
      }

      if (CFG.viewsManager.getActiveViewID() == ViewsManager.VIEW_ARMY_MODE) {
         CFG.updateMAX_Army();
      }

      CFG.game.getPlayer(CFG.PLAYER_TURNID).setNoOrders(false);
      Menu_InGame.updateOverBudget();
      if (RTS.isEnabled() && !RTS.PAUSE) {
         RTS.updateTimePast_AfterAction(1.0F);
      }

      CFG.menuManager.resetHoverActive();
   }

   @Override
   public final void actionElement(int iID) {
      switch (iID) {
         case 0:
            CFG.menuManager.setVisible_InGame_ProvinceRecruitInstantly(false);
            CFG.game.checkProvinceActionMenu();
            if (RTS.isEnabled() && !RTS.PAUSE) {
               RTS.updateTimePast_AfterAction(0.5F);
            }
            break;
         case 1:
            this.extraAction();
            break;
         case 2:
            CFG.menuManager.updateInGame_ActionInfo_RecruitInstantly();
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
