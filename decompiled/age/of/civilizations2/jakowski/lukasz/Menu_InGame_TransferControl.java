package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.utils.ScissorStack;
import java.util.ArrayList;

public class Menu_InGame_TransferControl extends SliderMenu {
   public int iProvinceID = -1;

   public Menu_InGame_TransferControl() {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      int tempWidth = CFG.CIV_INFO_MENU_WIDTH * 2;
      int tY = CFG.PADDING;
      menuElements.add(new Button_Flag_JustFrame(CFG.PADDING, tY, true));
      tY += menuElements.get(menuElements.size() - 1).getHeight();
      int tempMenuPosY = ImageManager.getImage(Images.top_left2).getHeight() + CFG.PADDING * 2 + CFG.BUTTON_HEIGHT * 3 / 5;
      this.initMenu(
         new SliderMenuTitle(CFG.langManager.get("TransferControl"), CFG.BUTTON_HEIGHT * 3 / 5, true, true),
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

   public Menu_InGame_TransferControl(int nProvinceID) {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      this.iProvinceID = nProvinceID;
      int tempWidth = CFG.CIV_INFO_MENU_WIDTH * 2;
      int tY = 0;
      menuElements.add(
         new Button_Build_TransferControl(
            CFG.langManager.get("TransferControlOverProvince"),
            CFG.game.getProvince(this.iProvinceID).getName().length() > 0 ? CFG.game.getProvince(this.iProvinceID).getName() : CFG.langManager.get("Province"),
            Images.transfer_control,
            0,
            4,
            0,
            tY,
            CFG.BUTTON_WIDTH * 2
         ) {
            @Override
            public int getWidth() {
               return Menu_InGame_TransferControl.this.getElementW() * 2;
            }
         }
      );
      tY += menuElements.get(menuElements.size() - 1).getHeight();
      boolean canSend = false;
      ArrayList<Integer> alliesToTransfer = new ArrayList<>();
      int tWarID = CFG.game.getWarID(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), CFG.game.getProvince(this.iProvinceID).getTrueOwnerOfProvince());
      if (tWarID >= 0) {
         if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID() > 0) {
            for (int i = 0;
               i < CFG.game.getAlliance(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID()).getCivilizationsSize();
               i++
            ) {
               if (CFG.game
                        .getCiv(CFG.game.getAlliance(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID()).getCivilization(i))
                        .getNumOfProvinces()
                     > 0
                  && CFG.game.getAlliance(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID()).getCivilization(i)
                     != CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()
                  && (
                     CFG.game
                           .getWar(tWarID)
                           .getIsInDefenders(
                              CFG.game.getAlliance(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID()).getCivilization(i)
                           )
                        || CFG.game
                           .getWar(tWarID)
                           .getIsAggressor(
                              CFG.game.getAlliance(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID()).getCivilization(i)
                           )
                  )) {
                  alliesToTransfer.add(
                     CFG.game.getAlliance(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID()).getCivilization(i)
                  );
               }
            }
         }

         for (int ix = 1; ix < CFG.game.getCivsSize(); ix++) {
            if (ix != CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()
               && (
                  CFG.game.getCiv(ix).getPuppetOfCivID() == CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()
                     || CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getPuppetOfCivID() == ix
               )
               && CFG.game.getCiv(ix).getNumOfProvinces() > 0
               && (CFG.game.getWar(tWarID).getIsInDefenders(ix) || CFG.game.getWar(tWarID).getIsAggressor(ix))) {
               boolean wasAdded = false;

               for (int j = 0; j < alliesToTransfer.size(); j++) {
                  if (alliesToTransfer.get(j) == ix) {
                     wasAdded = true;
                     break;
                  }
               }

               if (!wasAdded) {
                  alliesToTransfer.add(ix);
               }
            }
         }

         if (alliesToTransfer.size() == 0) {
            menuElements.add(new Text_Scale(CFG.langManager.get("NoAllies"), -1, 2, tY, tempWidth - 4, CFG.BUTTON_HEIGHT * 3 / 4, 0.75F) {
               @Override
               public int getWidth() {
                  return Menu_InGame_TransferControl.this.getElementW() * 2;
               }
            });
            menuElements.get(menuElements.size() - 1).setClickable(false);
            tY += menuElements.get(menuElements.size() - 1).getHeight();
            canSend = false;
         } else {
            for (int var15 = 0; var15 < alliesToTransfer.size(); var15++) {
               menuElements.add(
                  new Buton_Diplomacy_CallAllies(
                     var15, alliesToTransfer.get(var15), CFG.game.getProvince(this.iProvinceID).getTrueOwnerOfProvince(), 2, tY, CFG.BUTTON_WIDTH * 2
                  ) {
                     @Override
                     public int getWidth() {
                        return Menu_InGame_TransferControl.this.getElementW() * 2;
                     }

                     @Override
                     public void buildElementHover() {
                        this.menuElementHover = null;
                     }
                  }
               );
               tY += menuElements.get(menuElements.size() - 1).getHeight();
            }

            canSend = true;
         }
      } else if (!CFG.game.getCivsAreAllied(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), CFG.game.getProvince(this.iProvinceID).getTrueOwnerOfProvince())) {
         menuElements.add(
            new Buton_Diplomacy_CallAllies(
               0,
               CFG.game.getProvince(this.iProvinceID).getTrueOwnerOfProvince(),
               CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(),
               2,
               tY,
               CFG.BUTTON_WIDTH * 2
            ) {
               @Override
               public int getWidth() {
                  return Menu_InGame_TransferControl.this.getElementW() * 2;
               }

               @Override
               public void buildElementHover() {
                  this.menuElementHover = null;
               }
            }
         );
         tY += menuElements.get(menuElements.size() - 1).getHeight();
         canSend = true;
      } else {
         menuElements.add(new Text_Scale(CFG.langManager.get("NoAllies"), -1, 2, tY, tempWidth - 4, CFG.BUTTON_HEIGHT * 3 / 4, 0.75F) {
            @Override
            public int getWidth() {
               return Menu_InGame_TransferControl.this.getElementW() * 2;
            }

            @Override
            public void buildElementHover() {
               this.menuElementHover = null;
            }
         });
         menuElements.get(menuElements.size() - 1).setClickable(false);
         tY += menuElements.get(menuElements.size() - 1).getHeight();
         canSend = false;
      }

      int var12;
      menuElements.add(new Button_FlagActionSliderStyle(CFG.langManager.get("Cancel"), -1, 2 + CFG.PADDING, var12 = tY + CFG.PADDING, CFG.BUTTON_WIDTH, true) {
         @Override
         public int getWidth() {
            return Menu_InGame_TransferControl.this.getElementW() - CFG.PADDING - CFG.PADDING / 2;
         }
      });
      menuElements.add(
         new Button_FlagActionSliderStyle(CFG.langManager.get("Confirm"), -1, 2, var12, CFG.BUTTON_WIDTH, canSend) {
            @Override
            public int getPosX() {
               return Menu_InGame_TransferControl.this.getElementW() + CFG.PADDING / 2;
            }

            @Override
            public int getWidth() {
               return Menu_InGame_TransferControl.this.getElementW() - CFG.PADDING - CFG.PADDING / 2;
            }

            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("TransferControlOverProvince"), CFG.COLOR_TEXT_NUM_OF_PROVINCES));
               nData.add(
                  new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getProvince(Menu_InGame_TransferControl.this.iProvinceID).getCivID(), CFG.PADDING, 0)
               );
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("DiplomacyPoints") + ": ", CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
               nData.add(new MenuElement_Hover_v2_Element_Type_Text("-0.4", CFG.COLOR_INGAME_DIPLOMACY_POINTS));
               nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_diplomacy_points, CFG.PADDING, 0));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }

            @Override
            public void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
               CFG.fontMain.getData().setScale(0.8F);
               CFG.drawText(
                  oSB,
                  this.getText(),
                  this.getPosX() + this.getWidth() / 2 - (int)(this.getTextWidth() * 0.8F / 2.0F) + iTranslateX,
                  this.getPosY() + this.getHeight() / 2 - (int)(this.getTextHeight() * 0.8F / 2.0F) + iTranslateY,
                  this.getColor(isActive)
               );
               CFG.fontMain.getData().setScale(1.0F);
            }

            @Override
            public boolean getClickable() {
               return CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getDiplomacyPoints() >= 4 && super.getClickable();
            }

            @Override
            public int getSFX() {
               return SoundsManager.getSend();
            }
         }
      );
      int tempMenuPosY = ImageManager.getImage(Images.top_left2).getHeight() + CFG.PADDING * 2 + CFG.BUTTON_HEIGHT * 3 / 5;
      this.initMenu(
         new SliderMenuTitle(CFG.langManager.get("TransferControl"), CFG.BUTTON_HEIGHT * 3 / 5, true, true) {
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
               oSB.setColor(new Color(0.09803922F, 0.27450982F, 0.5686275F, 0.165F));
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
               oSB.setColor(new Color(0.09803922F, 0.27450982F, 0.5686275F, 0.375F));
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
                     Menu_InGame_TransferControl.this.getPosX() + CFG.PADDING * 2 + iTranslateX,
                     Menu_InGame_TransferControl.this.getPosY()
                        - this.getHeight() / 2
                        - CFG.CIV_FLAG_HEIGHT / 2
                        - CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getFlag().getHeight(),
                     CFG.CIV_FLAG_WIDTH,
                     CFG.CIV_FLAG_HEIGHT
                  );
               ImageManager.getImage(Images.flag_rect)
                  .draw(
                     oSB,
                     Menu_InGame_TransferControl.this.getPosX() + CFG.PADDING * 2 + iTranslateX,
                     Menu_InGame_TransferControl.this.getPosY() - this.getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2
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
            : menuElements.get(menuElements.size() - 1).getPosY() + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING,
         menuElements,
         true,
         true
      );
      this.updateLanguage();
      Menu_InGame_OfferAlliance.lTime = System.currentTimeMillis();
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
      if (iID == 0) {
         CFG.game.setActiveProvinceID(this.iProvinceID);
         CFG.map.getMapCoordinates().centerToProvinceID(CFG.game.getActiveProvinceID());
      } else {
         if (iID == this.getMenuElementsSize() - 1) {
            int toCivID = -1;

            for (int i = 1; i < this.getMenuElementsSize() - 2; i++) {
               if (this.getMenuElement(i).getCurrent() > 0 && this.getMenuElement(i).getCheckboxState()) {
                  toCivID = this.getMenuElement(i).getCurrent();
                  break;
               }
            }

            if (toCivID > 0) {
               DiplomacyManager.sendTransferControl(toCivID, CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), this.iProvinceID);
               CFG.menuManager.updateInGame_TOP_All(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
               CFG.toast.setInView(CFG.langManager.get("Sent") + "!", CFG.COLOR_TEXT_MODIFIER_POSITIVE);
               CFG.toast.setTimeInView(4500);
               this.setVisible(false);
            } else {
               CFG.toast.setInView(CFG.langManager.get("SelectCivilization"), CFG.COLOR_TEXT_MODIFIER_NEGATIVE2);
               CFG.toast.setTimeInView(4500);
            }

            return;
         }

         if (iID == this.getMenuElementsSize() - 2) {
            this.setVisible(false);
            return;
         }

         for (int ix = 1; ix < this.getMenuElementsSize(); ix++) {
            this.getMenuElement(ix).setCheckboxState(false);
         }

         this.getMenuElement(iID).setCheckboxState(true);
      }
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
