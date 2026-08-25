package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.utils.ScissorStack;
import java.util.ArrayList;

public class Menu_InGame_MoveCapital extends SliderMenu {
   public int toProvinceID = -1;

   public Menu_InGame_MoveCapital() {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      int tempWidth = CFG.CIV_INFO_MENU_WIDTH * 2;
      int tY = CFG.PADDING;
      menuElements.add(new Button_Flag_JustFrame(CFG.PADDING, tY, true));
      tY += menuElements.get(menuElements.size() - 1).getHeight();
      int tempMenuPosY = ImageManager.getImage(Images.top_left2).getHeight() + CFG.PADDING * 2 + CFG.BUTTON_HEIGHT * 3 / 5;
      this.initMenu(
         new SliderMenuTitle(CFG.langManager.get("Relations"), CFG.BUTTON_HEIGHT * 3 / 5, true, true),
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

   public Menu_InGame_MoveCapital(int toProvinceID) {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      this.toProvinceID = toProvinceID;
      int tempWidth = CFG.CIV_INFO_MENU_WIDTH * 2;
      int tY = 0;
      menuElements.add(
         new Button_Diplomacy_MoveCapital(
            CFG.game.getProvince(toProvinceID).getName().length() > 0 ? CFG.langManager.get("MoveCapitalTo") + ": " : CFG.langManager.get("MoveCapital"),
            CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(),
            toProvinceID,
            CFG.gameAction.moveCapital_Cost(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()),
            2,
            tY,
            CFG.BUTTON_WIDTH * 2
         ) {
            @Override
            public int getWidth() {
               return Menu_InGame_MoveCapital.this.getElementW() * 2;
            }
         }
      );
      tY += menuElements.get(menuElements.size() - 1).getHeight();
      int var7;
      menuElements.add(new Button_FlagActionSliderStyle(CFG.langManager.get("Cancel"), -1, 2 + CFG.PADDING, var7 = tY + CFG.PADDING, CFG.BUTTON_WIDTH, true) {
         @Override
         public int getWidth() {
            return Menu_InGame_MoveCapital.this.getElementW() - CFG.PADDING - CFG.PADDING / 2;
         }
      });
      menuElements.add(
         new Button_FlagActionSliderStyle(
            CFG.langManager.get("Confirm"), -1, 2, var7, CFG.BUTTON_WIDTH, CFG.gameAction.moveCapital_CanMove(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
         ) {
            @Override
            public int getPosX() {
               return Menu_InGame_MoveCapital.this.getElementW() + CFG.PADDING / 2;
            }

            @Override
            public int getWidth() {
               return Menu_InGame_MoveCapital.this.getElementW() - CFG.PADDING - CFG.PADDING / 2;
            }

            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               if (!CFG.gameAction.moveCapital_CanMove(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())) {
                  nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()));
                  nData.add(
                     new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("TheCapitalCityHasRecentlyBeenMoved"), CFG.COLOR_TEXT_MODIFIER_NEGATIVE2)
                  );
                  nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.city, CFG.PADDING, 0));
                  nElements.add(new MenuElement_Hover_v2_Element2(nData));
                  nData.clear();
                  nData.add(
                     new MenuElement_Hover_v2_Element_Type_Text(
                        Game_Calendar.getDate_ByTurnID(
                           Game_Calendar.TURN_ID
                              + Math.abs(
                                 Game_Calendar.TURN_ID - (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCapitalMoved_LastTurnID() + 50)
                              )
                        )
                     )
                  );
                  nData.add(
                     new MenuElement_Hover_v2_Element_Type_Text(
                        " ["
                           + CFG.langManager
                              .get(
                                 "TurnsX",
                                 Math.abs(
                                    Game_Calendar.TURN_ID
                                       - (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCapitalMoved_LastTurnID() + 50)
                                 )
                              )
                           + "]",
                        CFG.COLOR_TEXT_MODIFIER_NEUTRAL
                     )
                  );
                  nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.time, CFG.PADDING, 0));
                  nElements.add(new MenuElement_Hover_v2_Element2(nData));
                  nData.clear();
                  nData.add(new MenuElement_Hover_v2_Element_Type_Space());
                  nElements.add(new MenuElement_Hover_v2_Element2(nData));
                  nData.clear();
               }

               if (CFG.game.getProvince(Menu_InGame_MoveCapital.this.toProvinceID()).getName().length() > 0) {
                  nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()));
                  nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("MoveCapitalTo") + ": "));
                  nData.add(
                     new MenuElement_Hover_v2_Element_Type_Text(
                        CFG.game.getProvince(Menu_InGame_MoveCapital.this.toProvinceID()).getName(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                     )
                  );
                  nElements.add(new MenuElement_Hover_v2_Element2(nData));
                  nData.clear();
               } else {
                  nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()));
                  nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("MoveCapital"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                  nElements.add(new MenuElement_Hover_v2_Element2(nData));
                  nData.clear();
               }

               if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCapitalProvinceID() >= 0
                  && CFG.game.getProvince(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCapitalProvinceID()).getCivID()
                     != CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) {
                  nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()));
                  nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("TheCapitalCityIsLost"), CFG.COLOR_TEXT_MODIFIER_NEGATIVE2));
                  nElements.add(new MenuElement_Hover_v2_Element2(nData));
                  nData.clear();
               }

               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Cost") + ": "));
               nData.add(
                  new MenuElement_Hover_v2_Element_Type_Text(
                     "" + CFG.gameAction.moveCapital_Cost(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()), CFG.COLOR_INGAME_GOLD
                  )
               );
               nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_gold, CFG.PADDING, 0));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               nData.add(new MenuElement_Hover_v2_Element_Type_Space());
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCapitalProvinceID() >= 0) {
                  nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Happiness") + ": "));
                  nData.add(new MenuElement_Hover_v2_Element_Type_Text("-12", CFG.COLOR_TEXT_MODIFIER_NEGATIVE2));
                  nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.happiness2, CFG.PADDING, 0));
                  nData.add(
                     new MenuElement_Hover_v2_Element_Type_Text(
                        " " + CFG.game.getProvince(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCapitalProvinceID()).getName(),
                        CFG.COLOR_TEXT_MODIFIER_NEUTRAL
                     )
                  );
                  nElements.add(new MenuElement_Hover_v2_Element2(nData));
                  nData.clear();
               }

               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Happiness") + ": "));
               nData.add(new MenuElement_Hover_v2_Element_Type_Text("+2.5", CFG.COLOR_TEXT_MODIFIER_POSITIVE));
               nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.happiness, CFG.PADDING, 0));
               nData.add(
                  new MenuElement_Hover_v2_Element_Type_Text(
                     " " + CFG.game.getProvince(Menu_InGame_MoveCapital.this.toProvinceID()).getName(), CFG.COLOR_TEXT_MODIFIER_NEUTRAL
                  )
               );
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }

            @Override
            public void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
               ImageManager.getImage(Images.city)
                  .draw(
                     oSB,
                     this.getPosX()
                        + this.getWidth() / 2
                        - (int)((this.getTextWidth() * 0.8F + ImageManager.getImage(Images.city).getWidth() + CFG.PADDING) / 2.0F)
                        + iTranslateX,
                     this.getPosY() + this.getHeight() / 2 - ImageManager.getImage(Images.city).getHeight() / 2 + iTranslateY
                  );
               CFG.fontMain.getData().setScale(0.8F);
               CFG.drawText(
                  oSB,
                  this.getText(),
                  this.getPosX()
                     + (
                        this.getTextPos() < 0
                           ? this.getWidth() / 2
                              - (int)((this.getTextWidth() * 0.8F + ImageManager.getImage(Images.city).getWidth() + CFG.PADDING) / 2.0F)
                              + ImageManager.getImage(Images.city).getWidth()
                              + CFG.PADDING
                           : this.getTextPos()
                     )
                     + iTranslateX,
                  this.getPosY() + this.getHeight() / 2 - (int)(this.getTextHeight() * 0.8F / 2.0F) + iTranslateY,
                  this.getColor(isActive)
               );
               CFG.fontMain.getData().setScale(1.0F);
            }

            @Override
            public boolean getClickable() {
               return CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoney()
                     >= CFG.gameAction.moveCapital_Cost(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
                  && (
                     CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCapitalProvinceID() < 0
                        || CFG.game.getProvince(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCapitalProvinceID()).getCivID()
                           == CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()
                        || !CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).isAtWar()
                  );
            }

            @Override
            public int getSFX() {
               return SoundsManager.getSend();
            }
         }
      );
      int tempMenuPosY = ImageManager.getImage(Images.top_left2).getHeight() + CFG.PADDING * 2 + CFG.BUTTON_HEIGHT * 3 / 5;
      this.initMenu(
         new SliderMenuTitle(CFG.langManager.get("MoveCapital"), CFG.BUTTON_HEIGHT * 3 / 5, true, true) {
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
               oSB.setColor(new Color(0.13333334F, 0.45882353F, 0.7647059F, 0.165F));
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
               oSB.setColor(new Color(0.13333334F, 0.45882353F, 0.7647059F, 0.375F));
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
         if (CFG.gameAction.moveCapital(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), this.toProvinceID)) {
            CFG.menuManager.rebuildMenu_InGame_CapitalMoved(this.toProvinceID, CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
         }

         CFG.updateActiveCivInfo_InGame();
         CFG.menuManager.updateInGame_TOP_All(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
         this.setVisible(false);
      } else if (iID == this.getMenuElementsSize() - 2) {
         this.setVisible(false);
      } else {
         CFG.game.setActiveProvinceID(this.toProvinceID);
         CFG.map.getMapCoordinates().centerToProvinceID(CFG.game.getActiveProvinceID());
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

   public int toProvinceID() {
      return this.toProvinceID;
   }
}
