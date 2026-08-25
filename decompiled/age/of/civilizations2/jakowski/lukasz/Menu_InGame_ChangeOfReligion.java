package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.utils.ScissorStack;
import java.util.ArrayList;
import java.util.List;

public class Menu_InGame_ChangeOfReligion extends SliderMenu {
   public Menu_InGame_ChangeOfReligion() {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      int tempWidth = CFG.CIV_INFO_MENU_WIDTH * 2;
      int tY = CFG.PADDING;
      menuElements.add(new Button_Flag_JustFrame(CFG.PADDING, tY, true));
      tY += menuElements.get(menuElements.size() - 1).getHeight();
      int tempMenuPosY = ImageManager.getImage(Images.top_left2).getHeight() + CFG.PADDING * 2 + CFG.BUTTON_HEIGHT * 3 / 5;
      this.initMenu(
         new SliderMenuTitle(CFG.langManager.get("Religion"), CFG.BUTTON_HEIGHT * 3 / 5, true, true),
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

   public Menu_InGame_ChangeOfReligion(int nID) {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      int tempWidth = CFG.CIV_INFO_MENU_WIDTH * 2;
      int tY = 0;
      menuElements.add(
         new Button_Government(
            CFG.langManager.get("ChangeMainReligion"),
            "",
            CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getIdeologyID(),
            Ideologies_Manager.getChangeGovernmentCost(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()),
            22,
            2,
            tY,
            CFG.BUTTON_WIDTH * 2
         ) {
            @Override
            public int getWidth() {
               return Menu_InGame_ChangeOfReligion.this.getElementW() * 2;
            }
         }
      );
      tY += menuElements.get(menuElements.size() - 1).getHeight();
      List<Boolean> canChangeToIdeology = CFG.ideologiesManager.canChangeToIdeology(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
      tY += CFG.PADDING;
      int j = 0;

      for (int i = 0; i < CFG.ideologiesManager.getIdeologiesSize(); i++) {
         if (!CFG.ideologiesManager.getIdeology(i).REVOLUTIONARY && CFG.ideologiesManager.getIdeology(i).CAN_BECOME_CIVILIZED < 0) {
            menuElements.add(new Button_Diplomacy_ChangeReligion(j++, CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), i, 2, tY, CFG.BUTTON_WIDTH * 2) {
               @Override
               public int getWidth() {
                  return Menu_InGame_ChangeOfReligion.this.getElementW() * 2;
               }
            });
            tY += menuElements.get(menuElements.size() - 1).getHeight();
            menuElements.get(menuElements.size() - 1).setClickable(canChangeToIdeology.get(i));
         }
      }

      int var10;
      menuElements.add(new Button_FlagActionSliderStyle(CFG.langManager.get("Cancel"), -1, 2 + CFG.PADDING, var10 = tY + CFG.PADDING, CFG.BUTTON_WIDTH, true) {
         @Override
         public int getWidth() {
            return Menu_InGame_ChangeOfReligion.this.getElementW() - CFG.PADDING - CFG.PADDING / 2;
         }
      });
      menuElements.add(
         new Button_FlagActionSliderStyle(CFG.langManager.get("Confirm"), -1, 2, var10, CFG.BUTTON_WIDTH, true) {
            @Override
            public int getPosX() {
               return Menu_InGame_ChangeOfReligion.this.getElementW() + CFG.PADDING / 2;
            }

            @Override
            public int getWidth() {
               return Menu_InGame_ChangeOfReligion.this.getElementW() - CFG.PADDING - CFG.PADDING / 2;
            }

            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()));
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("ChangeMainReligion"), CFG.COLOR_TEXT_NUM_OF_PROVINCES));
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
               return CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMovePoints() >= 22
                  && CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoney()
                     >= Ideologies_Manager.getChangeGovernmentCost(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
                  && CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getTechnologyLevel() >= 0.4F;
            }

            @Override
            public int getSFX() {
               return SoundsManager.SOUND_BUILD;
            }
         }
      );
      int tempMenuPosY = ImageManager.getImage(Images.top_left2).getHeight() + CFG.PADDING * 2 + CFG.BUTTON_HEIGHT * 3 / 5;
      this.initMenu(
         new SliderMenuTitle(CFG.langManager.get("Religion"), CFG.BUTTON_HEIGHT * 3 / 5, true, true) {
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
               oSB.setColor(
                  new Color(
                     CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getR() / 255.0F,
                     CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getG() / 255.0F,
                     CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getB() / 255.0F,
                     0.165F
                  )
               );
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
               oSB.setColor(
                  new Color(
                     CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getR() / 255.0F,
                     CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getG() / 255.0F,
                     CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getB() / 255.0F,
                     0.375F
                  )
               );
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
                     Menu_InGame_ChangeOfReligion.this.getPosX() + CFG.PADDING * 2 + iTranslateX,
                     Menu_InGame_ChangeOfReligion.this.getPosY()
                        - this.getHeight() / 2
                        - CFG.CIV_FLAG_HEIGHT / 2
                        - CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getFlag().getHeight(),
                     CFG.CIV_FLAG_WIDTH,
                     CFG.CIV_FLAG_HEIGHT
                  );
               ImageManager.getImage(Images.flag_rect)
                  .draw(
                     oSB,
                     Menu_InGame_ChangeOfReligion.this.getPosX() + CFG.PADDING * 2 + iTranslateX,
                     Menu_InGame_ChangeOfReligion.this.getPosY() - this.getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2
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
      if (iID == this.getMenuElementsSize() - 1) {
         int toIdeology = -1;

         for (int i = 1; i < this.getMenuElementsSize() - 2; i++) {
            if (this.getMenuElement(i).getCheckboxState()) {
               toIdeology = this.getMenuElement(i).getCurrent();
               break;
            }
         }

         if (toIdeology >= 0) {
            if (DiplomacyManager.changeGovernmentType(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), toIdeology)) {
               ArrayList<String> tMess = new ArrayList<>();
               ArrayList<Color> tColor = new ArrayList<>();
               tMess.add(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCivName());
               tColor.add(CFG.ideologiesManager.getIdeology(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getIdeologyID()).getColor());
               tMess.add(CFG.ideologiesManager.getIdeology(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getIdeologyID()).getName());
               tColor.add(CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE);
               CFG.toast.setInView(tMess, tColor);
            }

            CFG.game.getPlayer(CFG.PLAYER_TURNID).loadPlayersFlag();
            CFG.setActiveCivInfo(CFG.getActiveCivInfo());
            CFG.updateActiveCivInfo_InGame();
            CFG.menuManager.updateInGame_TOP_All(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
            CFG.viewsManager.disableAllViews();

            for (int var7 = 0; var7 < CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getNumOfProvinces(); var7++) {
               CFG.game.getProvince(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getProvinceID(var7)).setFromCivID(0);
            }

            this.setVisible(false);
         }
      } else if (iID == this.getMenuElementsSize() - 2) {
         this.setVisible(false);
      } else {
         for (int ix = 0; ix < this.getMenuElementsSize(); ix++) {
            this.getMenuElement(ix).setCheckboxState(false);
         }

         this.getMenuElement(iID).setCheckboxState(!this.getMenuElement(iID).getCheckboxState());
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
