package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

public class Menu_InGame_RTO2 extends SliderMenu {
   public static final int ANIMATION_TIME = 250;
   public static long lTime = 0L;
   public String sLoading;
   public static final int TIME_REQUIRED_TO_CONTINUE = 30;
   public static long TIME_CONTINUE;

   public Menu_InGame_RTO2() {
      int tempRowH = CFG.TEXT_HEIGHT + CFG.PADDING * 2 > CFG.CIV_FLAG_HEIGHT + CFG.PADDING * 2
         ? CFG.TEXT_HEIGHT + CFG.PADDING * 2
         : CFG.CIV_FLAG_HEIGHT + CFG.PADDING * 2;
      int tempW = CFG.CIV_INFO_MENU_WIDTH * 4 / 5;
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      if (CFG.settingsManager.showOrderOfMovesView) {
         if (CFG.FOG_OF_WAR == 2) {
            for (int i = 0; i < CFG.game.getRTO().getRTOSize(); i++) {
               if (CFG.game.getCiv(CFG.game.getRTO().getRTO(i)).getControlledByPlayer()) {
                  menuElements.add(new Button_RTO_Player(i + 1, CFG.game.getRTO().getRTO(i), 0, tempRowH * i, tempW, tempRowH, true));
               } else if (i % 2 == 0) {
                  menuElements.add(
                     new Button_RTO(
                        i + 1,
                        CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetCivilization(CFG.game.getRTO().getRTO(i)) ? CFG.game.getRTO().getRTO(i) : -1,
                        0,
                        tempRowH * i,
                        tempW,
                        tempRowH,
                        true
                     )
                  );
               } else {
                  menuElements.add(
                     new Button_RTO2(
                        i + 1,
                        CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetCivilization(CFG.game.getRTO().getRTO(i)) ? CFG.game.getRTO().getRTO(i) : -1,
                        0,
                        tempRowH * i,
                        tempW,
                        tempRowH,
                        true
                     )
                  );
               }
            }
         } else {
            for (int ix = 0; ix < CFG.game.getRTO().getRTOSize(); ix++) {
               if (CFG.game.getCiv(CFG.game.getRTO().getRTO(ix)).getControlledByPlayer()) {
                  menuElements.add(new Button_RTO_Player(ix + 1, CFG.game.getRTO().getRTO(ix), 0, tempRowH * ix, tempW, tempRowH, true));
               } else if (ix % 2 == 0) {
                  menuElements.add(new Button_RTO(ix + 1, CFG.game.getRTO().getRTO(ix), 0, tempRowH * ix, tempW, tempRowH, true));
               } else {
                  menuElements.add(new Button_RTO2(ix + 1, CFG.game.getRTO().getRTO(ix), 0, tempRowH * ix, tempW, tempRowH, true));
               }
            }
         }
      } else {
         int ixx = 0;
         if (ixx < CFG.game.getRTO().getRTOSize()) {
            if (CFG.game.getCiv(CFG.game.getRTO().getRTO(ixx)).getControlledByPlayer()) {
               menuElements.add(new Button_RTO_Player(ixx + 1, CFG.game.getRTO().getRTO(ixx), 0, tempRowH * ixx, tempW, tempRowH, true));
            } else if (ixx % 2 == 0) {
               menuElements.add(new Button_RTO(ixx + 1, CFG.game.getRTO().getRTO(ixx), 0, tempRowH * ixx, tempW, tempRowH, true));
            } else {
               menuElements.add(new Button_RTO2(ixx + 1, CFG.game.getRTO().getRTO(ixx), 0, tempRowH * ixx, tempW, tempRowH, true));
            }
         }
      }

      this.initMenu(
         new SliderMenuTitle(null, CFG.BUTTON_HEIGHT * 3 / 4, false, false) {
            @Override
            public void draw(SpriteBatch oSB, int iTranslateX, int nPosX, int nPosY, int nWidth, boolean sliderMenuIsActive) {
               ImageManager.getImage(Images.new_game_top_edge_title)
                  .draw2(
                     oSB,
                     Menu_InGame_RTO2.this.getPosX() + iTranslateX,
                     Menu_InGame_RTO2.this.getPosY() - ImageManager.getImage(Images.new_game_top_edge_title).getHeight() - this.getHeight(),
                     Menu_InGame_RTO2.this.getWidth(),
                     this.getHeight()
                  );
               oSB.setColor(new Color(0.011F, 0.014F, 0.019F, 0.8F));
               ImageManager.getImage(Images.gradient)
                  .draw(
                     oSB,
                     Menu_InGame_RTO2.this.getPosX() + 2 + iTranslateX,
                     Menu_InGame_RTO2.this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() - this.getHeight() * 3 / 4,
                     Menu_InGame_RTO2.this.getWidth(),
                     this.getHeight() * 3 / 4,
                     false,
                     true
                  );
               oSB.setColor(new Color(0.451F, 0.329F, 0.11F, 1.0F));
               ImageManager.getImage(Images.pix255_255_255)
                  .draw(
                     oSB,
                     Menu_InGame_RTO2.this.getPosX() + 2 + iTranslateX,
                     Menu_InGame_RTO2.this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight(),
                     Menu_InGame_RTO2.this.getWidth() - 2
                  );
               oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.4F));
               ImageManager.getImage(Images.line_32_off1)
                  .draw(
                     oSB,
                     Menu_InGame_RTO2.this.getPosX() + 2 + iTranslateX,
                     Menu_InGame_RTO2.this.getPosY()
                        - ImageManager.getImage(Images.pix255_255_255).getHeight()
                        - ImageManager.getImage(Images.line_32_off1).getHeight(),
                     Menu_InGame_RTO2.this.getWidth() - 2,
                     1
                  );
               oSB.setColor(Color.WHITE);
               CFG.fontMain.getData().setScale(0.8F);
               CFG.drawText(
                  oSB,
                  this.getText(),
                  nPosX + nWidth / 2 - (int)(this.getTextWidth() * 0.8F / 2.0F) + iTranslateX,
                  nPosY - this.getHeight() + this.getHeight() / 2 + 1 - (int)(this.getTextHeight() * 0.8F / 2.0F),
                  CFG.COLOR_TEXT_OPTIONS_LEFT_NS
               );
               CFG.fontMain.getData().setScale(1.0F);
            }
         },
         CFG.GAME_WIDTH - tempW,
         ImageManager.getImage(Images.top_left).getHeight() + CFG.PADDING * 2 + CFG.BUTTON_HEIGHT * 3 / 4,
         tempW,
         CFG.GAME_HEIGHT * 4 / 5
                  - (ImageManager.getImage(Images.top_left).getHeight() + CFG.PADDING * 2 + CFG.BUTTON_HEIGHT * 3 / 4)
                  - CFG.map.getMapBG().getMinimapHeight()
                  - CFG.PADDING * 2
                  - (CFG.TEXT_HEIGHT + CFG.PADDING * 4)
                  - (CFG.BUTTON_HEIGHT * 3 / 4 + CFG.PADDING * 2)
               < (CFG.TEXT_HEIGHT + CFG.PADDING * 2) * 8
            ? CFG.GAME_HEIGHT
               - (ImageManager.getImage(Images.top_left).getHeight() + CFG.PADDING * 2 + CFG.BUTTON_HEIGHT * 3 / 4)
               - CFG.map.getMapBG().getMinimapHeight()
               - CFG.PADDING * 2
               - (CFG.TEXT_HEIGHT + CFG.PADDING * 4)
               - (CFG.BUTTON_HEIGHT * 3 / 4 + CFG.PADDING * 2)
            : CFG.GAME_HEIGHT * 4 / 5
               - (ImageManager.getImage(Images.top_left).getHeight() + CFG.PADDING * 2 + CFG.BUTTON_HEIGHT * 3 / 4)
               - CFG.map.getMapBG().getMinimapHeight()
               - CFG.PADDING * 2
               - (CFG.TEXT_HEIGHT + CFG.PADDING * 4)
               - (CFG.BUTTON_HEIGHT * 3 / 4 + CFG.PADDING * 2),
         menuElements,
         false,
         false
      );
      this.updateLanguage();
   }

   @Override
   public void updateLanguage() {
      this.getTitle().setText(CFG.langManager.get("OrderOfMoves"));
      this.sLoading = CFG.langManager.get("Loading") + ": ";
   }

   @Override
   public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      if (CFG.settingsManager.showOrderOfMovesView) {
         if (lTime + 250L >= System.currentTimeMillis()) {
            iTranslateX += this.getWidth() - (int)(this.getWidth() * ((float)(System.currentTimeMillis() - lTime) / 250.0F));
            CFG.setRender_3(true);
         }

         ImageManager.getImage(Images.new_game_top_edge_line)
            .draw2(
               oSB,
               this.getPosX() + iTranslateX,
               this.getPosY() - ImageManager.getImage(Images.new_game_top_edge_line).getHeight() + iTranslateY,
               this.getWidth(),
               this.getHeight(),
               false,
               true
            );
         super.draw(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
         if (!CFG.oAI.doneLoadingOrders) {
            oSB.setColor(new Color(CFG.COLOR_GRADIENT_DARK_BLUE.r, CFG.COLOR_GRADIENT_DARK_BLUE.g, CFG.COLOR_GRADIENT_DARK_BLUE.b, 0.9F));
            ImageManager.getImage(Images.slider_gradient)
               .draw(
                  oSB,
                  0,
                  this.getLoadPosY() - ImageManager.getImage(Images.line_32_off1).getHeight(),
                  CFG.map.getMapBG().getMinimapWidth(),
                  this.getLoadHeight(),
                  false,
                  false
               );
            oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.35F));
            ImageManager.getImage(Images.slider_gradient)
               .draw(
                  oSB,
                  0,
                  this.getLoadPosY() - ImageManager.getImage(Images.slider_gradient).getHeight(),
                  CFG.map.getMapBG().getMinimapWidth() / 2,
                  this.getLoadHeight(),
                  false,
                  false
               );
            oSB.setColor(CFG.COLOR_CREATE_NEW_GAME_BOX_PLAYERS);
            ImageManager.getImage(Images.slider_gradient)
               .draw(
                  oSB, 0, this.getLoadPosY() - ImageManager.getImage(Images.slider_gradient).getHeight(), CFG.map.getMapBG().getMinimapWidth(), 1, false, false
               );
            ImageManager.getImage(Images.slider_gradient)
               .draw(
                  oSB,
                  0,
                  this.getLoadPosY() + this.getLoadHeight() - 1 - ImageManager.getImage(Images.slider_gradient).getHeight(),
                  CFG.map.getMapBG().getMinimapWidth(),
                  1,
                  false,
                  false
               );
            oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.65F));
            ImageManager.getImage(Images.slider_gradient)
               .draw(
                  oSB,
                  0,
                  this.getLoadPosY() + 1 - ImageManager.getImage(Images.slider_gradient).getHeight(),
                  CFG.map.getMapBG().getMinimapWidth(),
                  1,
                  false,
                  false
               );
            ImageManager.getImage(Images.slider_gradient)
               .draw(
                  oSB,
                  0,
                  this.getLoadPosY() + this.getLoadHeight() - 2 - ImageManager.getImage(Images.slider_gradient).getHeight(),
                  CFG.map.getMapBG().getMinimapWidth(),
                  1,
                  false,
                  false
               );
            oSB.setColor(Color.WHITE);
            CFG.game
               .getCiv(CFG.oAI.iLoadingTurnActionsOfCivID)
               .getFlag()
               .draw(
                  oSB,
                  CFG.PADDING * 2,
                  this.getLoadPosY()
                     + this.getLoadHeight() / 2
                     - CFG.CIV_FLAG_HEIGHT / 2
                     - CFG.game.getCiv(CFG.oAI.iLoadingTurnActionsOfCivID).getFlag().getHeight()
                     + iTranslateY,
                  CFG.CIV_FLAG_WIDTH,
                  CFG.CIV_FLAG_HEIGHT
               );
            ImageManager.getImage(Images.flag_rect)
               .draw(oSB, CFG.PADDING * 2, this.getLoadPosY() + this.getLoadHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 + iTranslateY);
            CFG.fontMain.getData().setScale(0.7F);
            CFG.drawTextWithShadow(
               oSB,
               this.sLoading + (int)((float)CFG.oAI.iLoadingTurnActionsOfCivID / (CFG.game.getCivsSize() - 1) * 100.0F) + "%",
               CFG.PADDING * 4 + CFG.CIV_FLAG_WIDTH,
               this.getLoadPosY() + (int)((this.getLoadHeight() - CFG.TEXT_HEIGHT * 0.7F) / 2.0F),
               CFG.COLOR_TEXT_NUM_OF_PROVINCES
            );
            CFG.fontMain.getData().setScale(1.0F);
         } else if (TIME_CONTINUE > 0L) {
            CFG.setRender_3(true);
            if (TIME_CONTINUE < System.currentTimeMillis() - 30L) {
               Menu_InGame_ProvinceInfo.clickEndTurn();
            }
         }
      } else if (!CFG.oAI.doneLoadingOrders) {
         oSB.setColor(new Color(CFG.COLOR_GRADIENT_DARK_BLUE.r, CFG.COLOR_GRADIENT_DARK_BLUE.g, CFG.COLOR_GRADIENT_DARK_BLUE.b, 0.9F));
         ImageManager.getImage(Images.slider_gradient)
            .draw(
               oSB,
               0,
               this.getLoadPosY() - ImageManager.getImage(Images.line_32_off1).getHeight(),
               CFG.map.getMapBG().getMinimapWidth(),
               this.getLoadHeight(),
               false,
               false
            );
         oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.35F));
         ImageManager.getImage(Images.slider_gradient)
            .draw(
               oSB,
               0,
               this.getLoadPosY() - ImageManager.getImage(Images.slider_gradient).getHeight(),
               CFG.map.getMapBG().getMinimapWidth() / 2,
               this.getLoadHeight(),
               false,
               false
            );
         oSB.setColor(CFG.COLOR_CREATE_NEW_GAME_BOX_PLAYERS);
         ImageManager.getImage(Images.slider_gradient)
            .draw(oSB, 0, this.getLoadPosY() - ImageManager.getImage(Images.slider_gradient).getHeight(), CFG.map.getMapBG().getMinimapWidth(), 1, false, false);
         ImageManager.getImage(Images.slider_gradient)
            .draw(
               oSB,
               0,
               this.getLoadPosY() + this.getLoadHeight() - 1 - ImageManager.getImage(Images.slider_gradient).getHeight(),
               CFG.map.getMapBG().getMinimapWidth(),
               1,
               false,
               false
            );
         oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.65F));
         ImageManager.getImage(Images.slider_gradient)
            .draw(
               oSB,
               0,
               this.getLoadPosY() + 1 - ImageManager.getImage(Images.slider_gradient).getHeight(),
               CFG.map.getMapBG().getMinimapWidth(),
               1,
               false,
               false
            );
         ImageManager.getImage(Images.slider_gradient)
            .draw(
               oSB,
               0,
               this.getLoadPosY() + this.getLoadHeight() - 2 - ImageManager.getImage(Images.slider_gradient).getHeight(),
               CFG.map.getMapBG().getMinimapWidth(),
               1,
               false,
               false
            );
         oSB.setColor(Color.WHITE);
         CFG.game
            .getCiv(CFG.oAI.iLoadingTurnActionsOfCivID)
            .getFlag()
            .draw(
               oSB,
               CFG.PADDING * 2,
               this.getLoadPosY()
                  + this.getLoadHeight() / 2
                  - CFG.CIV_FLAG_HEIGHT / 2
                  - CFG.game.getCiv(CFG.oAI.iLoadingTurnActionsOfCivID).getFlag().getHeight()
                  + iTranslateY,
               CFG.CIV_FLAG_WIDTH,
               CFG.CIV_FLAG_HEIGHT
            );
         ImageManager.getImage(Images.flag_rect)
            .draw(oSB, CFG.PADDING * 2, this.getLoadPosY() + this.getLoadHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 + iTranslateY);
         CFG.fontMain.getData().setScale(0.7F);
         CFG.drawTextWithShadow(
            oSB,
            this.sLoading + (int)((float)CFG.oAI.iLoadingTurnActionsOfCivID / (CFG.game.getCivsSize() - 1) * 100.0F) + "%",
            CFG.PADDING * 4 + CFG.CIV_FLAG_WIDTH,
            this.getLoadPosY() + (int)((this.getLoadHeight() - CFG.TEXT_HEIGHT * 0.7F) / 2.0F),
            CFG.COLOR_TEXT_NUM_OF_PROVINCES
         );
         CFG.fontMain.getData().setScale(1.0F);
         CFG.setRender_3(true);
      } else if (TIME_CONTINUE > 0L) {
         CFG.setRender_3(true);
         if (TIME_CONTINUE < System.currentTimeMillis() - 30L) {
            Menu_InGame_ProvinceInfo.clickEndTurn();
         }
      }
   }

   public final int getLoadPosY() {
      return CFG.GAME_HEIGHT - CFG.map.getMapBG().getMinimapHeight() - CFG.PADDING - this.getLoadHeight();
   }

   public final int getLoadHeight() {
      return CFG.CIV_FLAG_HEIGHT + CFG.PADDING * 4;
   }

   @Override
   public void drawScrollPos(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      if (sliderMenuIsActive || this.getScrollModeY()) {
         super.drawScrollPos(oSB, iTranslateX - 2, iTranslateY, sliderMenuIsActive);
      }
   }

   @Override
   public void actionElement(int nID) {
      if (CFG.FOG_OF_WAR == 2 && !CFG.getMetCiv_AllPlayers(CFG.game.getRTO().getRTO(nID))) {
         CFG.toast.setInView(CFG.langManager.get("UndiscoveredCivilization"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE);
      } else {
         if (CFG.FOG_OF_WAR != 2 || CFG.getMetProvince(CFG.game.getCiv(CFG.game.getRTO().getRTO(nID)).getCapitalProvinceID())) {
            CFG.map.getMapCoordinates().centerToProvinceID(CFG.game.getCiv(CFG.game.getRTO().getRTO(nID)).getCapitalProvinceID());
         }

         CFG.toast.setInView(CFG.game.getCiv(CFG.game.getRTO().getRTO(nID)).getCivName(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE);
      }
   }

   @Override
   public void setVisible(boolean visible) {
      super.setVisible(visible);
      lTime = System.currentTimeMillis();
      TIME_CONTINUE = -1L;
      if (!visible) {
         CFG.menuManager.setVisibleInGame_RTOBot(visible);
      }
   }
}
