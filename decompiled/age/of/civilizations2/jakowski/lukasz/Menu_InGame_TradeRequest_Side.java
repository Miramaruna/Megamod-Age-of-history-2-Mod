package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

public class Menu_InGame_TradeRequest_Side extends SliderMenu {
   public boolean left = false;
   public int iOnCivID = -1;

   public Menu_InGame_TradeRequest_Side() {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      int tempWidth = CFG.CIV_INFO_MENU_WIDTH * 3 / 4;
      int tY = CFG.PADDING;
      menuElements.add(new Button_Flag_JustFrame(CFG.PADDING, tY, true));
      tY += menuElements.get(menuElements.size() - 1).getHeight();
      int tempMenuPosY = ImageManager.getImage(Images.top_left2).getHeight() + CFG.PADDING * 2 + CFG.BUTTON_HEIGHT * 3 / 5;
      this.initMenu(
         new SliderMenuTitle(CFG.langManager.get("TradeRequest"), CFG.BUTTON_HEIGHT * 3 / 5, true, true),
         CFG.GAME_WIDTH / 2 - tempWidth / 2,
         tempMenuPosY,
         tempWidth,
         menuElements.get(menuElements.size() - 1).getPosY() + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING + tempMenuPosY
               > CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING * 2
            ? Math.max(CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING * 2 - tempMenuPosY, (CFG.TEXT_HEIGHT + CFG.PADDING * 2) * 6)
            : menuElements.get(menuElements.size() - 1).getPosY() + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING,
         menuElements,
         false,
         true
      );
      this.updateLanguage();
   }

   public Menu_InGame_TradeRequest_Side(int onCivID, final boolean left) {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      this.iOnCivID = onCivID;
      this.left = left;
      int tempWidth = CFG.CIV_INFO_MENU_WIDTH * 3 / 4;
      int tY = 0;
      menuElements.add(
         new Button_Statistics(CFG.langManager.get("Gold"), CFG.PADDING * 2, 2, tY, CFG.BUTTON_WIDTH * 2, (int)(CFG.BUTTON_HEIGHT * 0.6F), false) {
            @Override
            public int getWidth() {
               return Menu_InGame_TradeRequest_Side.this.getElementW();
            }

            @Override
            public boolean getCheckboxState() {
               return left ? CFG.tradeRequest.listLEFT.iGold > 0 : CFG.tradeRequest.listRight.iGold > 0;
            }
         }
      );
      tY += menuElements.get(menuElements.size() - 1).getHeight();
      if (left) {
         menuElements.get(menuElements.size() - 1).setClickable(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoney() > 0L);
      }

      menuElements.add(
         new Button_Statistics(CFG.langManager.get("Provinces"), CFG.PADDING * 2, 2, tY, CFG.BUTTON_WIDTH * 2, (int)(CFG.BUTTON_HEIGHT * 0.6F), false) {
            @Override
            public int getWidth() {
               return Menu_InGame_TradeRequest_Side.this.getElementW();
            }

            @Override
            public boolean getCheckboxState() {
               return left ? CFG.tradeRequest.listLEFT.lProvinces.size() > 0 : CFG.tradeRequest.listRight.lProvinces.size() > 0;
            }
         }
      );
      int var9;
      menuElements.add(
         new Button_Statistics(
            CFG.langManager.get("DeclareWar"),
            CFG.PADDING * 2,
            2,
            var9 = tY + menuElements.get(menuElements.size() - 1).getHeight(),
            CFG.BUTTON_WIDTH * 2,
            (int)(CFG.BUTTON_HEIGHT * 0.6F),
            false
         ) {
            @Override
            public int getWidth() {
               return Menu_InGame_TradeRequest_Side.this.getElementW();
            }

            @Override
            public boolean getCheckboxState() {
               return left ? CFG.tradeRequest.listLEFT.iDeclarWarOnCivID > 0 : CFG.tradeRequest.listRight.iDeclarWarOnCivID > 0;
            }
         }
      );
      menuElements.add(
         new Button_Statistics(
            CFG.langManager.get("FormACoalitionAgainst"),
            CFG.PADDING * 2,
            2,
            tY = var9 + menuElements.get(menuElements.size() - 1).getHeight(),
            CFG.BUTTON_WIDTH * 2,
            (int)(CFG.BUTTON_HEIGHT * 0.6F),
            false
         ) {
            @Override
            public int getWidth() {
               return Menu_InGame_TradeRequest_Side.this.getElementW();
            }

            @Override
            public boolean getCheckboxState() {
               return CFG.tradeRequest.listLEFT.iFormCoalitionAgainst > 0 || CFG.tradeRequest.listRight.iFormCoalitionAgainst > 0;
            }
         }
      );
      int var11;
      menuElements.add(
         new Button_Statistics(
            CFG.langManager.get("DefensivePact"),
            CFG.PADDING * 2,
            2,
            var11 = tY + menuElements.get(menuElements.size() - 1).getHeight(),
            CFG.BUTTON_WIDTH * 2,
            (int)(CFG.BUTTON_HEIGHT * 0.6F),
            false
         ) {
            @Override
            public int getWidth() {
               return Menu_InGame_TradeRequest_Side.this.getElementW();
            }

            @Override
            public boolean getCheckboxState() {
               return left ? CFG.tradeRequest.listLEFT.defensivePact : CFG.tradeRequest.listRight.defensivePact;
            }
         }
      );
      menuElements.add(
         new Button_Statistics(
            CFG.langManager.get("NonAggressionPact"),
            CFG.PADDING * 2,
            2,
            tY = var11 + menuElements.get(menuElements.size() - 1).getHeight(),
            CFG.BUTTON_WIDTH * 2,
            (int)(CFG.BUTTON_HEIGHT * 0.6F),
            false
         ) {
            @Override
            public int getWidth() {
               return Menu_InGame_TradeRequest_Side.this.getElementW();
            }

            @Override
            public boolean getCheckboxState() {
               return left ? CFG.tradeRequest.listLEFT.nonAggressionPact : CFG.tradeRequest.listRight.nonAggressionPact;
            }
         }
      );
      int var13;
      menuElements.add(
         new Button_Statistics(
            CFG.langManager.get("ProclaimIndependence"),
            CFG.PADDING * 2,
            2,
            var13 = tY + menuElements.get(menuElements.size() - 1).getHeight(),
            CFG.BUTTON_WIDTH * 2,
            (int)(CFG.BUTTON_HEIGHT * 0.6F),
            false
         ) {
            @Override
            public int getWidth() {
               return Menu_InGame_TradeRequest_Side.this.getElementW();
            }

            @Override
            public boolean getCheckboxState() {
               return left ? CFG.tradeRequest.listLEFT.proclaimIndependence : CFG.tradeRequest.listRight.proclaimIndependence;
            }
         }
      );
      menuElements.add(
         new Button_Statistics(
            CFG.langManager.get("MilitaryAccess"),
            CFG.PADDING * 2,
            2,
            tY = var13 + menuElements.get(menuElements.size() - 1).getHeight(),
            CFG.BUTTON_WIDTH * 2,
            (int)(CFG.BUTTON_HEIGHT * 0.6F),
            false
         ) {
            @Override
            public int getWidth() {
               return Menu_InGame_TradeRequest_Side.this.getElementW();
            }

            @Override
            public boolean getCheckboxState() {
               return left ? CFG.tradeRequest.listLEFT.militaryAccess : CFG.tradeRequest.listRight.militaryAccess;
            }
         }
      );
      tY += menuElements.get(menuElements.size() - 1).getHeight();
      int tempMenuPosY = ImageManager.getImage(Images.top_left2).getHeight() + CFG.PADDING * 2 + CFG.BUTTON_HEIGHT * 3 / 5;
      this.initMenu(
         new SliderMenuTitle(CFG.game.getCiv(onCivID).getCivName(), CFG.BUTTON_HEIGHT * 3 / 5, false, false) {
            @Override
            public void draw(SpriteBatch oSB, int iTranslateX, int nPosX, int nPosY, int nWidth, boolean sliderMenuIsActive) {
               ImageManager.getImage(Images.dialog_title)
                  .draw2(
                     oSB,
                     nPosX + iTranslateX,
                     nPosY - this.getHeight() - ImageManager.getImage(Images.dialog_title).getHeight(),
                     nWidth - ImageManager.getImage(Images.dialog_title).getWidth(),
                     this.getHeight()
                  );
               ImageManager.getImage(Images.dialog_title)
                  .draw2(
                     oSB,
                     nPosX + nWidth - ImageManager.getImage(Images.dialog_title).getWidth() + iTranslateX,
                     nPosY - this.getHeight() - ImageManager.getImage(Images.dialog_title).getHeight(),
                     ImageManager.getImage(Images.dialog_title).getWidth(),
                     this.getHeight(),
                     true,
                     false
                  );
               oSB.setColor(
                  new Color(
                     CFG.game.getCiv(Menu_InGame_TradeRequest_Side.this.iOnCivID).getR() / 255.0F,
                     CFG.game.getCiv(Menu_InGame_TradeRequest_Side.this.iOnCivID).getG() / 255.0F,
                     CFG.game.getCiv(Menu_InGame_TradeRequest_Side.this.iOnCivID).getB() / 255.0F,
                     0.165F
                  )
               );
               ImageManager.getImage(Images.line_32_off1)
                  .draw(
                     oSB,
                     nPosX + 2 + iTranslateX,
                     nPosY - this.getHeight() - ImageManager.getImage(Images.line_32_off1).getHeight(),
                     nWidth - 4,
                     this.getHeight() - 2,
                     false,
                     true
                  );
               oSB.setColor(
                  new Color(
                     CFG.game.getCiv(Menu_InGame_TradeRequest_Side.this.iOnCivID).getR() / 255.0F,
                     CFG.game.getCiv(Menu_InGame_TradeRequest_Side.this.iOnCivID).getG() / 255.0F,
                     CFG.game.getCiv(Menu_InGame_TradeRequest_Side.this.iOnCivID).getB() / 255.0F,
                     0.375F
                  )
               );
               ImageManager.getImage(Images.gradient)
                  .draw(
                     oSB,
                     nPosX + 2 + iTranslateX,
                     nPosY - this.getHeight() * 2 / 3 - ImageManager.getImage(Images.gradient).getHeight(),
                     nWidth - 4,
                     this.getHeight() * 2 / 3,
                     false,
                     true
                  );
               oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.65F));
               ImageManager.getImage(Images.gradient)
                  .draw(
                     oSB,
                     nPosX + 2 + iTranslateX,
                     nPosY - CFG.PADDING - ImageManager.getImage(Images.gradient).getHeight(),
                     nWidth - 4,
                     CFG.PADDING,
                     false,
                     true
                  );
               oSB.setColor(CFG.COLOR_NEW_GAME_EDGE_LINE);
               ImageManager.getImage(Images.pix255_255_255)
                  .draw(oSB, nPosX + 2 + iTranslateX, nPosY - 1 - ImageManager.getImage(Images.pix255_255_255).getHeight(), nWidth - 4, 1);
               oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.55F));
               ImageManager.getImage(Images.line_32_off1)
                  .draw(oSB, nPosX + 2 + iTranslateX, nPosY - 2 - ImageManager.getImage(Images.line_32_off1).getHeight(), nWidth - 4, 1);
               oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.8F));
               ImageManager.getImage(Images.line_32_off1)
                  .draw(oSB, nPosX + 2 + iTranslateX, nPosY - 1 - ImageManager.getImage(Images.line_32_off1).getHeight(), nWidth - 4, 1);
               oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.45F));
               ImageManager.getImage(Images.slider_gradient)
                  .draw(oSB, nPosX + 2 + iTranslateX, nPosY - 1 - ImageManager.getImage(Images.slider_gradient).getHeight(), (nWidth - 4) / 2, 1);
               ImageManager.getImage(Images.slider_gradient)
                  .draw(
                     oSB,
                     nPosX + (nWidth - 2) - (nWidth - 4) / 2 + iTranslateX,
                     nPosY - 1 - ImageManager.getImage(Images.slider_gradient).getHeight(),
                     (nWidth - 4) / 2,
                     1,
                     true,
                     false
                  );
               oSB.setColor(Color.WHITE);
               CFG.fontMain.getData().setScale(0.8F);
               CFG.drawText(
                  oSB,
                  this.getText(),
                  nPosX + (int)(nWidth - this.getTextWidth() * 0.8F) / 2 + iTranslateX,
                  2 + nPosY - this.getHeight() + (int)(this.getHeight() - this.getTextHeight() * 0.8F) / 2,
                  Color.WHITE
               );
               CFG.fontMain.getData().setScale(1.0F);
            }
         },
         CFG.GAME_WIDTH / 2 - tempWidth / 2,
         tempMenuPosY,
         tempWidth,
         menuElements.get(menuElements.size() - 1).getPosY() + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING + tempMenuPosY
               > CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING * 2
            ? Math.max(CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING * 2 - tempMenuPosY, (CFG.TEXT_HEIGHT + CFG.PADDING * 2) * 6)
            : menuElements.get(menuElements.size() - 1).getPosY() + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING,
         menuElements,
         true,
         false
      );
      this.updateLanguage();

      for (int i = 0; i < this.getMenuElementsSize(); i++) {
         this.getMenuElement(i).setCurrent(i % 2);
      }
   }

   @Override
   public void updateLanguage() {
   }

   @Override
   public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      oSB.setColor(Color.WHITE);
      ImageManager.getImage(Images.new_game_top_edge)
         .draw2(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.new_game_top_edge).getHeight() + iTranslateY,
            this.getWidth() - ImageManager.getImage(Images.new_game_top_edge).getWidth(),
            this.getHeight() + CFG.PADDING,
            false,
            true
         );
      ImageManager.getImage(Images.new_game_top_edge)
         .draw2(
            oSB,
            this.getPosX() + this.getWidth() - ImageManager.getImage(Images.new_game_top_edge).getWidth() + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.new_game_top_edge).getHeight() + iTranslateY,
            ImageManager.getImage(Images.new_game_top_edge).getWidth(),
            this.getHeight() + CFG.PADDING,
            true,
            true
         );
      oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.45F));
      ImageManager.getImage(Images.gradient)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY,
            this.getWidth(),
            this.getHeight() / 4
         );
      ImageManager.getImage(Images.pix255_255_255)
         .draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY, this.getWidth(), 1);
      oSB.setColor(Color.WHITE);
      this.beginClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
      this.drawMenu(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
      oSB.setColor(Color.WHITE);
      this.endClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
   }

   @Override
   public void drawScrollPos(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      if (sliderMenuIsActive) {
         super.drawScrollPos(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
      }
   }

   @Override
   public void onHovered() {
      CFG.menuManager.setOrderOfMenu_InGame_TradeRequest();
   }

   @Override
   public final void actionElement(int iID) {
      int tempPosY = this.getMenuPosY();
      switch (iID) {
         case 0:
            if (this.left) {
               if (CFG.tradeRequest.listLEFT.iGold > 0) {
                  CFG.tradeRequest.listLEFT.iGold = 0;
               } else {
                  CFG.tradeRequest.listLEFT.iGold = 100;
                  if (CFG.tradeRequest.listLEFT.iGold > CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoney()) {
                     CFG.tradeRequest.listLEFT.iGold = (int)CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoney();
                  }
               }
            } else {
               CFG.tradeRequest.listRight.iGold = CFG.tradeRequest.listRight.iGold > 0 ? 0 : 100;
            }

            CFG.menuManager.rebuildInGame_TradeRequest_Just();
            this.setMenuPosY(tempPosY);
            return;
         case 1:
            if (this.left) {
               if (CFG.tradeRequest.listLEFT.lProvinces.size() == 0) {
                  CFG.MANAGE_DIPLOMACY_CUSTOMIZE_ALLIANCE_ID = CFG.tradeRequest.iCivLEFT;
                  CFG.game.getPlayer(CFG.PLAYER_TURNID).iACTIVE_VIEW_MODE = CFG.viewsManager.getActiveViewID();
                  CFG.viewsManager.disableAllViews();
                  CFG.game.setActiveProvinceID(-1);
                  Menu_InGame_SelectProvinces.typeOfAction = Menu_InGame_SelectProvinces.TypeOfAction.TRADE_LEFT;
                  CFG.VIEW_SHOW_VALUES = false;
                  CFG.selectMode = true;
                  CFG.game.getSelectedProvinces().clearSelectedProvinces();
                  CFG.menuManager.setViewID(Menu.eINGAME_SELECT_PROVINCES);
                  Game_Render_Province.updateDrawProvinces();
               } else {
                  CFG.tradeRequest.listLEFT.lProvinces.clear();
                  CFG.menuManager.rebuildInGame_TradeRequest_Just();
               }
            } else if (CFG.tradeRequest.listRight.lProvinces.size() == 0) {
               CFG.MANAGE_DIPLOMACY_CUSTOMIZE_ALLIANCE_ID = CFG.tradeRequest.iCivRIGHT;
               CFG.game.getPlayer(CFG.PLAYER_TURNID).iACTIVE_VIEW_MODE = CFG.viewsManager.getActiveViewID();
               CFG.viewsManager.disableAllViews();
               CFG.game.setActiveProvinceID(-1);
               Menu_InGame_SelectProvinces.typeOfAction = Menu_InGame_SelectProvinces.TypeOfAction.TRADE_RIGHT;
               CFG.VIEW_SHOW_VALUES = false;
               CFG.selectMode = true;
               CFG.game.getSelectedProvinces().clearSelectedProvinces();
               CFG.menuManager.setViewID(Menu.eINGAME_SELECT_PROVINCES);
               Game_Render_Province.updateDrawProvinces();
            } else {
               CFG.tradeRequest.listRight.lProvinces.clear();
               CFG.menuManager.rebuildInGame_TradeRequest_Just();
            }

            this.setMenuPosY(tempPosY);
            return;
         case 2:
            if (this.left) {
               if (CFG.tradeRequest.listLEFT.iDeclarWarOnCivID <= 0) {
                  CFG.game.getPlayer(CFG.PLAYER_TURNID).iACTIVE_VIEW_MODE = CFG.viewsManager.getActiveViewID();
                  CFG.viewsManager.disableAllViews();
                  CFG.game.setActiveProvinceID(-1);
                  Menu_InGame_TradeRequest_SelectCiv.typeOfAction = Menu_InGame_SelectProvinces.TypeOfAction.TRADE_LEFT_DECLAREWAR;
                  CFG.menuManager.setViewID(Menu.eINGAME_TRADE_SELECT_CIV);
                  CFG.toast.setInView(CFG.langManager.get("SelectProvince"), CFG.COLOR_TEXT_NUM_OF_PROVINCES);
                  Game_Render_Province.updateDrawProvinces();
               } else {
                  CFG.tradeRequest.listLEFT.iDeclarWarOnCivID = -1;
                  CFG.menuManager.rebuildInGame_TradeRequest_Just();
               }
            } else if (CFG.tradeRequest.listRight.iDeclarWarOnCivID <= 0) {
               CFG.game.getPlayer(CFG.PLAYER_TURNID).iACTIVE_VIEW_MODE = CFG.viewsManager.getActiveViewID();
               CFG.viewsManager.disableAllViews();
               CFG.game.setActiveProvinceID(-1);
               Menu_InGame_TradeRequest_SelectCiv.typeOfAction = Menu_InGame_SelectProvinces.TypeOfAction.TRADE_RIGHT_DECLAREWAR;
               CFG.menuManager.setViewID(Menu.eINGAME_TRADE_SELECT_CIV);
               CFG.toast.setInView(CFG.langManager.get("SelectProvince"), CFG.COLOR_TEXT_NUM_OF_PROVINCES);
               Game_Render_Province.updateDrawProvinces();
            } else {
               CFG.tradeRequest.listRight.iDeclarWarOnCivID = -1;
               CFG.menuManager.rebuildInGame_TradeRequest_Just();
            }

            this.setMenuPosY(tempPosY);
            return;
         case 3:
            if (this.left) {
               if (CFG.tradeRequest.listLEFT.iFormCoalitionAgainst <= 0) {
                  CFG.game.getPlayer(CFG.PLAYER_TURNID).iACTIVE_VIEW_MODE = CFG.viewsManager.getActiveViewID();
                  CFG.viewsManager.disableAllViews();
                  CFG.game.setActiveProvinceID(-1);
                  Menu_InGame_TradeRequest_SelectCiv.typeOfAction = Menu_InGame_SelectProvinces.TypeOfAction.TRADE_LEFT_COALITION;
                  CFG.menuManager.setViewID(Menu.eINGAME_TRADE_SELECT_CIV);
                  CFG.toast.setInView(CFG.langManager.get("SelectProvince"), CFG.COLOR_TEXT_NUM_OF_PROVINCES);
                  Game_Render_Province.updateDrawProvinces();
               } else {
                  CFG.tradeRequest.listLEFT.iFormCoalitionAgainst = -1;
                  CFG.menuManager.rebuildInGame_TradeRequest_Just();
               }
            } else if (CFG.tradeRequest.listRight.iFormCoalitionAgainst <= 0) {
               CFG.game.getPlayer(CFG.PLAYER_TURNID).iACTIVE_VIEW_MODE = CFG.viewsManager.getActiveViewID();
               CFG.viewsManager.disableAllViews();
               CFG.game.setActiveProvinceID(-1);
               Menu_InGame_TradeRequest_SelectCiv.typeOfAction = Menu_InGame_SelectProvinces.TypeOfAction.TRADE_RIGHT_COALITION;
               CFG.menuManager.setViewID(Menu.eINGAME_TRADE_SELECT_CIV);
               CFG.toast.setInView(CFG.langManager.get("SelectProvince"), CFG.COLOR_TEXT_NUM_OF_PROVINCES);
               Game_Render_Province.updateDrawProvinces();
            } else {
               CFG.tradeRequest.listRight.iFormCoalitionAgainst = -1;
               CFG.menuManager.rebuildInGame_TradeRequest_Just();
            }

            this.setMenuPosY(tempPosY);
            return;
         case 4:
            CFG.tradeRequest.listLEFT.defensivePact = !CFG.tradeRequest.listLEFT.defensivePact;
            CFG.tradeRequest.listRight.defensivePact = !CFG.tradeRequest.listRight.defensivePact;
            CFG.menuManager.rebuildInGame_TradeRequest_Just();
            this.setMenuPosY(tempPosY);
            return;
         case 5:
            CFG.tradeRequest.listLEFT.nonAggressionPact = !CFG.tradeRequest.listLEFT.nonAggressionPact;
            CFG.tradeRequest.listRight.nonAggressionPact = !CFG.tradeRequest.listRight.nonAggressionPact;
            CFG.menuManager.rebuildInGame_TradeRequest_Just();
            this.setMenuPosY(tempPosY);
            return;
         case 6:
            if (this.left) {
               CFG.tradeRequest.listLEFT.proclaimIndependence = !CFG.tradeRequest.listLEFT.proclaimIndependence;
            } else {
               CFG.tradeRequest.listRight.proclaimIndependence = !CFG.tradeRequest.listRight.proclaimIndependence;
            }

            CFG.menuManager.rebuildInGame_TradeRequest_Just();
            this.setMenuPosY(tempPosY);
            return;
         case 7:
            if (this.left) {
               CFG.tradeRequest.listLEFT.militaryAccess = !CFG.tradeRequest.listLEFT.militaryAccess;
            } else {
               CFG.tradeRequest.listRight.militaryAccess = !CFG.tradeRequest.listRight.militaryAccess;
            }

            CFG.menuManager.rebuildInGame_TradeRequest_Just();
            this.setMenuPosY(tempPosY);
            return;
      }
   }

   public final int getW() {
      return this.getWidth();
   }

   public final int getElementW() {
      return this.getW() - 4;
   }
}
