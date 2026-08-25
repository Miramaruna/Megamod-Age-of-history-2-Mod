package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.utils.ScissorStack;
import java.util.ArrayList;

public class Menu_InGame_Airbase extends SliderMenu {
   public int iProvinceID = -1;

   public Menu_InGame_Airbase() {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      int tempWidth = CFG.CIV_INFO_MENU_WIDTH * 2;
      int tY = CFG.PADDING;
      menuElements.add(new Button_Flag_JustFrame(CFG.PADDING, tY, true));
      tY += menuElements.get(menuElements.size() - 1).getHeight();
      int tempMenuPosY = ImageManager.getImage(Images.top_left2).getHeight() + CFG.PADDING * 2 + CFG.BUTTON_HEIGHT * 3 / 5;
      this.initMenu(
         new SliderMenuTitle(CFG.langManager.get("Airbase"), CFG.BUTTON_HEIGHT * 3 / 5, true, true),
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

   public Menu_InGame_Airbase(final int nProvinceID) {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      this.iProvinceID = nProvinceID;
      int tempWidth = CFG.CIV_INFO_MENU_WIDTH * 2;
      int tY = 0;
      int tempValue = CFG.game.getProvince(nProvinceID).getAirbase().getFighters();
      int var7;
      menuElements.add(
         new Text_Economy_Value(
            "" + tempValue,
            CFG.game.getProvince(nProvinceID).getAirbase().getFightersInvested() + " " + CFG.langManager.get("Fighters"),
            CFG.PADDING,
            var7 = tY + CFG.PADDING,
            tempWidth - CFG.PADDING * 2,
            CFG.TEXT_HEIGHT + CFG.PADDING * 2
         ) {
            @Override
            public int getWidth() {
               return Menu_InGame_Airbase.this.getWidth() - CFG.PADDING * 2;
            }

            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               new ArrayList();
               this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
         }
      );
      menuElements.get(menuElements.size() - 1).setMax(tempValue);
      tempValue = CFG.game.getProvince(nProvinceID).getAirbase().getBombers();
      menuElements.add(
         new Text_Economy_Value(
            "" + tempValue,
            CFG.langManager.get("Bombers"),
            CFG.PADDING,
            tY = var7 + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING,
            tempWidth - CFG.PADDING * 2,
            CFG.TEXT_HEIGHT + CFG.PADDING * 2
         ) {
            @Override
            public int getWidth() {
               return Menu_InGame_Airbase.this.getWidth() - CFG.PADDING * 2;
            }

            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               new ArrayList();
               this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
         }
      );
      menuElements.get(menuElements.size() - 1).setMax(tempValue);
      tempValue = CFG.game.getProvince(nProvinceID).getAirbase().getHelicopters();
      int var9;
      menuElements.add(
         new Text_Economy_Value(
            "" + tempValue,
            CFG.langManager.get("Helicopters"),
            CFG.PADDING,
            var9 = tY + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING,
            tempWidth - CFG.PADDING * 2,
            CFG.TEXT_HEIGHT + CFG.PADDING * 2
         ) {
            @Override
            public int getWidth() {
               return Menu_InGame_Airbase.this.getWidth() - CFG.PADDING * 2;
            }

            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               new ArrayList();
               this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
         }
      );
      menuElements.get(menuElements.size() - 1).setMax(tempValue);
      menuElements.add(
         new Text_Economy_Value(
            CFG.langManager.get("Choose Region!"),
            CFG.langManager.get("Region") + ": " + CFG.map.getMapRegions().getName(CFG.game.getProvince(nProvinceID).getAirbase().getRegion()),
            CFG.PADDING,
            tY = var9 + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING,
            tempWidth - CFG.PADDING * 2,
            CFG.TEXT_HEIGHT + CFG.PADDING * 2
         ) {
            @Override
            public void actionElement(int iID) {
               Menu_InGame_Airbase.this.hideAllViews();
               CFG.lastAirbaseProvince = nProvinceID;
               CFG.regionChoosing = true;
            }

            @Override
            public int getWidth() {
               return Menu_InGame_Airbase.this.getWidth() - CFG.PADDING * 2;
            }

            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               new ArrayList();
               this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
         }
      );
      menuElements.get(menuElements.size() - 1).setMax(100);
      int var11;
      menuElements.add(
         new Slider_FlagAction(
            CFG.langManager.get("Fighters"),
            CFG.PADDING * 3,
            var11 = tY + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING,
            tempWidth - CFG.PADDING * 3 * 2,
            CFG.TEXT_HEIGHT + CFG.PADDING * 2 + CFG.PADDING * 5,
            0,
            100,
            (int)(CFG.game.getProvince(nProvinceID).getAirbase().getFightersInvest() * 100.0F)
         ) {
            @Override
            public void actionElement(int iID) {
               super.actionElement(iID);
               CFG.game.getProvince(nProvinceID).getAirbase().setFightersInvest(this.getCurrent() / 100.0F);
               Menu_InGame.updateOverBudget();
            }

            @Override
            public int getWidth() {
               return Menu_InGame_Airbase.this.getWidth() - CFG.PADDING * 3 * 2;
            }

            @Override
            public String getDrawText() {
               return this.getCurrent() + "%";
            }

            @Override
            public Color getColorLEFT() {
               return Color.LIGHT_GRAY;
            }

            @Override
            public Color getColor(boolean isActive) {
               return super.getColor(isActive);
            }

            @Override
            public boolean getClickable() {
               return CFG.SPECTATOR_MODE ? false : super.getClickable();
            }
         }
      );
      menuElements.add(
         new Slider_FlagAction(
            CFG.langManager.get("Bombers"),
            CFG.PADDING * 3,
            tY = var11 + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING,
            tempWidth - CFG.PADDING * 6,
            CFG.TEXT_HEIGHT + CFG.PADDING * 2 + CFG.PADDING * 5,
            0,
            100,
            0
         ) {
            @Override
            public void actionElement(int iID) {
               super.actionElement(iID);
               CFG.game.getProvince(nProvinceID).getAirbase().setBombersInvest(this.getCurrent() / 100.0F);
               Menu_InGame.updateOverBudget();
            }

            @Override
            public int getWidth() {
               return Menu_InGame_Airbase.this.getWidth() - CFG.PADDING * 3 * 2;
            }

            @Override
            public String getDrawText() {
               return this.getCurrent() + "%";
            }

            @Override
            public Color getColorLEFT() {
               return Color.LIGHT_GRAY;
            }

            @Override
            public Color getColor(boolean isActive) {
               return super.getColor(isActive);
            }

            @Override
            public boolean getClickable() {
               return CFG.SPECTATOR_MODE ? false : super.getClickable();
            }
         }
      );
      int var13;
      menuElements.add(
         new Slider_FlagAction(
            CFG.langManager.get("Helicopters"),
            CFG.PADDING * 3,
            var13 = tY + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING,
            tempWidth - CFG.PADDING * 6,
            CFG.TEXT_HEIGHT + CFG.PADDING * 2 + CFG.PADDING * 5,
            0,
            100,
            0
         ) {
            @Override
            public void actionElement(int iID) {
               super.actionElement(iID);
               CFG.game.getProvince(nProvinceID).getAirbase().setHelicoptersInvest(this.getCurrent() / 100.0F);
               Menu_InGame.updateOverBudget();
            }

            @Override
            public int getWidth() {
               return Menu_InGame_Airbase.this.getWidth() - CFG.PADDING * 3 * 2;
            }

            @Override
            public String getDrawText() {
               return this.getCurrent() + "%";
            }

            @Override
            public Color getColorLEFT() {
               return Color.LIGHT_GRAY;
            }

            @Override
            public Color getColor(boolean isActive) {
               return super.getColor(isActive);
            }

            @Override
            public boolean getClickable() {
               return CFG.SPECTATOR_MODE ? false : super.getClickable();
            }
         }
      );
      menuElements.add(
         new Button_FlagActionSliderStyle(
            CFG.langManager.get("Cancel"), -1, 2 + CFG.PADDING, tY = var13 + menuElements.get(menuElements.size() - 1).getHeight(), CFG.BUTTON_WIDTH, true
         ) {
            @Override
            public void actionElement(int iID) {
               Menu_InGame_Airbase.this.setVisible(false);
            }

            @Override
            public int getPosY() {
               return Menu_InGame_Airbase.this.getHeight() - this.getHeight() - CFG.PADDING;
            }

            @Override
            public int getWidth() {
               return Menu_InGame_Airbase.this.getWidth() - CFG.PADDING * 2;
            }
         }
      );
      int tempMenuPosY = ImageManager.getImage(Images.top_left2).getHeight() + CFG.PADDING * 2 + CFG.BUTTON_HEIGHT * 3 / 5;
      this.initMenu(
         new SliderMenuTitle(CFG.langManager.get("Airbase"), CFG.BUTTON_HEIGHT * 3 / 5, true, true) {
            @Override
            public void draw(SpriteBatch oSB, int iTranslateX, int nPosX, int nPosY, int nWidth, boolean sliderMenuIsActive) {
               ImageManager.getImage(Images.dialog_title)
                  .draw2(
                     oSB,
                     nPosX - 2 + iTranslateX,
                     nPosY - this.getHeight() - ImageManager.getImage(Images.dialog_title).getHeight(),
                     nWidth + 4 - ImageManager.getImage(Images.dialog_title).getWidth(),
                     this.getHeight()
                  );
               ImageManager.getImage(Images.dialog_title)
                  .draw2(
                     oSB,
                     nPosX + nWidth + 2 - ImageManager.getImage(Images.dialog_title).getWidth() + iTranslateX,
                     nPosY - this.getHeight() - ImageManager.getImage(Images.dialog_title).getHeight(),
                     ImageManager.getImage(Images.dialog_title).getWidth(),
                     this.getHeight(),
                     true,
                     false
                  );
               oSB.setColor(new Color(0.7137255F, 0.20392157F, 0.03529412F, 0.165F));
               ImageManager.getImage(Images.line_32_off1)
                  .draw(
                     oSB,
                     nPosX + iTranslateX,
                     nPosY - this.getHeight() + 2 - ImageManager.getImage(Images.line_32_off1).getHeight(),
                     nWidth,
                     this.getHeight() - 2,
                     false,
                     true
                  );
               oSB.setColor(new Color(0.7137255F, 0.20392157F, 0.03529412F, 0.375F));
               ImageManager.getImage(Images.gradient)
                  .draw(
                     oSB,
                     nPosX + iTranslateX,
                     nPosY - this.getHeight() * 2 / 3 - ImageManager.getImage(Images.gradient).getHeight(),
                     nWidth,
                     this.getHeight() * 2 / 3,
                     false,
                     true
                  );
               oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.65F));
               ImageManager.getImage(Images.gradient)
                  .draw(oSB, nPosX + iTranslateX, nPosY - CFG.PADDING - ImageManager.getImage(Images.gradient).getHeight(), nWidth, CFG.PADDING, false, true);
               oSB.setColor(CFG.COLOR_NEW_GAME_EDGE_LINE);
               ImageManager.getImage(Images.pix255_255_255)
                  .draw(oSB, nPosX + iTranslateX, nPosY - 1 - ImageManager.getImage(Images.pix255_255_255).getHeight(), nWidth, 1);
               oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.55F));
               ImageManager.getImage(Images.line_32_off1)
                  .draw(oSB, nPosX + iTranslateX, nPosY - 2 - ImageManager.getImage(Images.line_32_off1).getHeight(), nWidth, 1);
               oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.8F));
               ImageManager.getImage(Images.line_32_off1)
                  .draw(oSB, nPosX + iTranslateX, nPosY - 1 - ImageManager.getImage(Images.line_32_off1).getHeight(), nWidth, 1);
               oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.45F));
               ImageManager.getImage(Images.slider_gradient)
                  .draw(oSB, nPosX + iTranslateX, nPosY - 1 - ImageManager.getImage(Images.slider_gradient).getHeight(), nWidth / 2, 1);
               ImageManager.getImage(Images.slider_gradient)
                  .draw(
                     oSB,
                     nPosX + nWidth - nWidth / 2 + iTranslateX,
                     nPosY - 1 - ImageManager.getImage(Images.slider_gradient).getHeight(),
                     nWidth / 2,
                     1,
                     true,
                     false
                  );
               oSB.setColor(Color.WHITE);
               CFG.game
                  .getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
                  .getFlag()
                  .draw(
                     oSB,
                     Menu_InGame_Airbase.this.getPosX() + CFG.PADDING * 2 + iTranslateX,
                     Menu_InGame_Airbase.this.getPosY()
                        - this.getHeight() / 2
                        - CFG.CIV_FLAG_HEIGHT / 2
                        - CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getFlag().getHeight(),
                     CFG.CIV_FLAG_WIDTH,
                     CFG.CIV_FLAG_HEIGHT
                  );
               ImageManager.getImage(Images.flag_rect)
                  .draw(
                     oSB,
                     Menu_InGame_Airbase.this.getPosX() + CFG.PADDING * 2 + iTranslateX,
                     Menu_InGame_Airbase.this.getPosY() - this.getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2
                  );
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
            : tY + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING,
         menuElements,
         true,
         true
      );
      this.updateLanguage();
      Menu_InGame_OfferAlliance.lTime = System.currentTimeMillis();
      this.getMenuElement(4).setCurrent((int)(CFG.game.getProvince(nProvinceID).getAirbase().getFightersInvest() * 100.0F));
      this.getMenuElement(5).setCurrent((int)(CFG.game.getProvince(nProvinceID).getAirbase().getBombersInvest() * 100.0F));
      this.getMenuElement(6).setCurrent((int)(CFG.game.getProvince(nProvinceID).getAirbase().getHelicoptersInvest() * 100.0F));
   }

   public final void hideAllViews() {
      this.hideAllProvinceActionViews();
      CFG.menuManager.updateInGameRTO(false);
      if (CFG.menuManager.getColorPicker().getVisible()) {
         CFG.menuManager.getColorPicker().setVisible(false, null);
      }
   }

   public final void hideAllProvinceActionViews() {
      CFG.menuManager.setVisible_InGame_ActionInfo(false);
      CFG.menuManager.setVisible_InGame_ProvinceAction(false);
      CFG.menuManager.setVisible_InGame_ProvinceMoveUnits(false);
      CFG.menuManager.setVisible_InGame_ProvinceRecruit(false);
      CFG.menuManager.setVisible_InGame_ProvinceRecruitInstantly(false);
      CFG.menuManager.setVisible_InGame_ProvinceRegroupArmy(false);
      CFG.menuManager.setVisible_InGame_ProvinceDisband(false);
      CFG.menuManager.setVisible_InGame_ProvinceAction_Colonize(false);
      CFG.menuManager.setVisible_InGame_ProvinceAction_Colonize_TechRequired(false);
      CFG.menuManager.setVisible_InGame_View_HideViews();
   }

   @Override
   public void updateLanguage() {
   }

   @Override
   public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      if (Menu_InGame_OfferAlliance.lTime + 200L >= System.currentTimeMillis()) {
         Rectangle clipBounds = new Rectangle(
            this.getPosX() - 2,
            CFG.GAME_HEIGHT - this.getPosY(),
            this.getWidth() + 4,
            -((int)((this.getHeight() + CFG.PADDING) * ((float)(System.currentTimeMillis() - Menu_InGame_OfferAlliance.lTime) / 200.0F)))
         );
         oSB.flush();
         ScissorStack.pushScissors(clipBounds);
         oSB.setColor(Color.WHITE);
         ImageManager.getImage(Images.new_game_top_edge)
            .draw2(
               oSB,
               this.getPosX() - 2 + iTranslateX,
               this.getPosY() - ImageManager.getImage(Images.new_game_top_edge).getHeight() + iTranslateY,
               this.getWidth() - ImageManager.getImage(Images.new_game_top_edge).getWidth() + 4,
               this.getHeight() + CFG.PADDING,
               false,
               true
            );
         ImageManager.getImage(Images.new_game_top_edge)
            .draw2(
               oSB,
               this.getPosX() + 2 + this.getWidth() - ImageManager.getImage(Images.new_game_top_edge).getWidth() + iTranslateX,
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
               this.getPosX() + 2 + iTranslateX,
               this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY,
               this.getWidth() - 4,
               this.getHeight() / 4
            );
         ImageManager.getImage(Images.pix255_255_255)
            .draw(
               oSB,
               this.getPosX() + 2 + iTranslateX,
               this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY,
               this.getWidth() - 4,
               1
            );
         oSB.setColor(Color.WHITE);
         this.drawMenu(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
         oSB.setColor(Color.WHITE);
         CFG.setRender_3(true);
         this.endClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
      } else {
         oSB.setColor(Color.WHITE);
         ImageManager.getImage(Images.new_game_top_edge)
            .draw2(
               oSB,
               this.getPosX() - 2 + iTranslateX,
               this.getPosY() - ImageManager.getImage(Images.new_game_top_edge).getHeight() + iTranslateY,
               this.getWidth() - ImageManager.getImage(Images.new_game_top_edge).getWidth() + 4,
               this.getHeight() + CFG.PADDING,
               false,
               true
            );
         ImageManager.getImage(Images.new_game_top_edge)
            .draw2(
               oSB,
               this.getPosX() + 2 + this.getWidth() - ImageManager.getImage(Images.new_game_top_edge).getWidth() + iTranslateX,
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
               this.getPosX() + 2 + iTranslateX,
               this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY,
               this.getWidth() - 4,
               this.getHeight() / 4
            );
         ImageManager.getImage(Images.pix255_255_255)
            .draw(
               oSB,
               this.getPosX() + 2 + iTranslateX,
               this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY,
               this.getWidth() - 4,
               1
            );
         oSB.setColor(Color.WHITE);
         this.beginClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
         this.drawMenu(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
         oSB.setColor(Color.WHITE);
         this.endClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
      }
   }

   @Override
   public void drawScrollPos(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      if (sliderMenuIsActive) {
         super.drawScrollPos(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
      }
   }

   @Override
   public final void actionElement(int iID) {
      this.getMenuElement(iID).actionElement(iID);
   }

   public final int getW() {
      return this.getWidth() - 4;
   }

   public final int getElementW() {
      return this.getW() / 2;
   }

   @Override
   public void setVisible(boolean visible) {
      super.setVisible(visible);
      if (!visible) {
         for (int i = 0; i < this.getMenuElementsSize(); i++) {
            this.getMenuElement(i).setVisible(false);
         }
      }
   }
}
