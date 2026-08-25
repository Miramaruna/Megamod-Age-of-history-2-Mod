package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

public class Menu_Civilization_Info extends SliderMenu {
   public static final int ANIMATION_TIME = 250;
   public static long lTime = 0L;

   public Menu_Civilization_Info() {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      menuElements.add(
         new Button_Rank(
            "1",
            CFG.PADDING * 2 + ImageManager.getImage(Images.top_flag_frame).getWidth() - ImageManager.getImage(Images.top_circle).getWidth() * 4 / 5,
            CFG.PADDING * 2 + ImageManager.getImage(Images.top_flag_frame).getHeight() - ImageManager.getImage(Images.top_circle).getHeight() * 6 / 7
         ) {
            @Override
            public void buildElementHover() {
               this.menuElementHover = CFG.game.getHover_RankOfCiv(CFG.getActiveCivInfo());
            }
         }
      );
      menuElements.add(
         new Text_Scrollable(
            null,
            ImageManager.getImage(Images.top_flag_frame).getWidth() + CFG.PADDING * 4,
            CFG.PADDING * 3,
            CFG.CIV_INFO_MENU_WIDTH - ImageManager.getImage(Images.top_flag_frame).getWidth() - CFG.PADDING * 4,
            CFG.COLOR_TEXT_CIV_NAME
         ) {
            @Override
            public Color getColor(boolean isActive) {
               return isActive
                  ? CFG.COLOR_TEXT_CIV_NAME_ACTIVE
                  : (
                     this.getClickable()
                        ? (this.getIsHovered() ? CFG.COLOR_TEXT_CIV_NAME_HOVERED : CFG.COLOR_TEXT_CIV_NAME)
                        : new Color(0.78F, 0.78F, 0.78F, 0.7F)
                  );
            }

            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               if (CFG.game.getCiv(CFG.getActiveCivInfo()).getCivID() != CFG.game.getCiv(CFG.getActiveCivInfo()).getPuppetOfCivID()) {
                  nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Lord") + ": "));
                  nData.add(
                     new MenuElement_Hover_v2_Element_Type_Text(
                        CFG.game.getCiv(CFG.game.getCiv(CFG.getActiveCivInfo()).getPuppetOfCivID()).getCivName(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                     )
                  );
                  nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getCiv(CFG.getActiveCivInfo()).getPuppetOfCivID(), CFG.PADDING, 0));
                  nData.add(
                     new MenuElement_Hover_v2_Element_Type_Ideology(
                        CFG.game.getCiv(CFG.game.getCiv(CFG.getActiveCivInfo()).getPuppetOfCivID()).getIdeologyID(), CFG.PADDING, 0
                     )
                  );
                  nElements.add(new MenuElement_Hover_v2_Element2(nData));
                  nData.clear();
                  nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Vassal") + ": "));
                  nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.game.getCiv(CFG.getActiveCivInfo()).getCivName(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                  nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.getActiveCivInfo(), CFG.PADDING, 0));
                  nData.add(new MenuElement_Hover_v2_Element_Type_Ideology_Vassal(CFG.game.getCiv(CFG.getActiveCivInfo()).getIdeologyID(), CFG.PADDING, 0));
                  nElements.add(new MenuElement_Hover_v2_Element2(nData));
                  nData.clear();
               } else {
                  nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.getActiveCivInfo()));
                  nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.game.getCiv(CFG.getActiveCivInfo()).getCivName()));
                  nElements.add(new MenuElement_Hover_v2_Element2(nData));
                  nData.clear();
               }

               this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }

            @Override
            public void draw_Element(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
               if (CFG.game.getCiv(CFG.getActiveCivInfo()).getCivID() != CFG.game.getCiv(CFG.getActiveCivInfo()).getPuppetOfCivID()) {
                  super.draw_Element(
                     oSB,
                     iTranslateX
                        + CFG.PADDING
                        + (int)(
                           CFG.game.getCiv(CFG.game.getCiv(CFG.getActiveCivInfo()).getPuppetOfCivID()).getFlag().getWidth()
                              * Menu_Civilization_Info.this.getImageScale(
                                 CFG.game.getCiv(CFG.game.getCiv(CFG.getActiveCivInfo()).getPuppetOfCivID()).getFlag().getHeight()
                              )
                        ),
                     iTranslateY,
                     isActive,
                     scrollableY
                  );
                  CFG.game
                     .getCiv(CFG.game.getCiv(CFG.getActiveCivInfo()).getPuppetOfCivID())
                     .getFlag()
                     .draw(
                        oSB,
                        this.getPosX() + this.getCurrent() + iTranslateX,
                        this.getPosY()
                           - CFG.game.getCiv(CFG.game.getCiv(CFG.getActiveCivInfo()).getPuppetOfCivID()).getFlag().getHeight()
                           + (int)(
                                 this.getHeight()
                                    - CFG.game.getCiv(CFG.game.getCiv(CFG.getActiveCivInfo()).getPuppetOfCivID()).getFlag().getHeight()
                                       * Menu_Civilization_Info.this.getImageScale(
                                          CFG.game.getCiv(CFG.game.getCiv(CFG.getActiveCivInfo()).getPuppetOfCivID()).getFlag().getHeight()
                                       )
                              )
                              / 2
                           + iTranslateY,
                        (int)(
                           CFG.game.getCiv(CFG.game.getCiv(CFG.getActiveCivInfo()).getPuppetOfCivID()).getFlag().getWidth()
                              * Menu_Civilization_Info.this.getImageScale(
                                 CFG.game.getCiv(CFG.game.getCiv(CFG.getActiveCivInfo()).getPuppetOfCivID()).getFlag().getHeight()
                              )
                        ),
                        (int)(
                           CFG.game.getCiv(CFG.game.getCiv(CFG.getActiveCivInfo()).getPuppetOfCivID()).getFlag().getHeight()
                              * Menu_Civilization_Info.this.getImageScale(
                                 CFG.game.getCiv(CFG.game.getCiv(CFG.getActiveCivInfo()).getPuppetOfCivID()).getFlag().getHeight()
                              )
                        )
                     );
                  ImageManager.getImage(Images.flag_rect)
                     .draw(
                        oSB,
                        this.getPosX() + this.getCurrent() + iTranslateX,
                        this.getPosY()
                           - ImageManager.getImage(Images.flag_rect).getHeight()
                           + (int)(
                                 this.getHeight()
                                    - CFG.game.getCiv(CFG.game.getCiv(CFG.getActiveCivInfo()).getPuppetOfCivID()).getFlag().getHeight()
                                       * Menu_Civilization_Info.this.getImageScale(
                                          CFG.game.getCiv(CFG.game.getCiv(CFG.getActiveCivInfo()).getPuppetOfCivID()).getFlag().getHeight()
                                       )
                              )
                              / 2
                           + iTranslateY,
                        (int)(
                           CFG.game.getCiv(CFG.game.getCiv(CFG.getActiveCivInfo()).getPuppetOfCivID()).getFlag().getWidth()
                              * Menu_Civilization_Info.this.getImageScale(
                                 CFG.game.getCiv(CFG.game.getCiv(CFG.getActiveCivInfo()).getPuppetOfCivID()).getFlag().getHeight()
                              )
                        ),
                        (int)(
                           CFG.game.getCiv(CFG.game.getCiv(CFG.getActiveCivInfo()).getPuppetOfCivID()).getFlag().getHeight()
                              * Menu_Civilization_Info.this.getImageScale(
                                 CFG.game.getCiv(CFG.game.getCiv(CFG.getActiveCivInfo()).getPuppetOfCivID()).getFlag().getHeight()
                              )
                        )
                     );
               } else {
                  super.draw_Element(oSB, iTranslateX, iTranslateY, isActive, scrollableY);
               }
            }

            @Override
            public int getTextWidth() {
               try {
                  return CFG.game.getCiv(CFG.getActiveCivInfo()).getCivID() != CFG.game.getCiv(CFG.getActiveCivInfo()).getPuppetOfCivID()
                     ? super.getTextWidth()
                        + CFG.PADDING
                        + (int)(
                           CFG.game.getCiv(CFG.game.getCiv(CFG.getActiveCivInfo()).getPuppetOfCivID()).getFlag().getWidth()
                              * Menu_Civilization_Info.this.getImageScale(
                                 CFG.game.getCiv(CFG.game.getCiv(CFG.getActiveCivInfo()).getPuppetOfCivID()).getFlag().getHeight()
                              )
                        )
                     : super.getTextWidth();
               } catch (IndexOutOfBoundsException var2) {
                  return super.getTextWidth();
               }
            }
         }
      );
      menuElements.add(new Button_FlagFrame(CFG.PADDING * 2, CFG.PADDING * 2, true) {
         @Override
         public void buildElementHover() {
            ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
            ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("ShowHideColorPicker"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
            nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.pickeIcon, CFG.PADDING, 0));
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
            this.menuElementHover = new MenuElement_Hover_v2(nElements);
         }

         @Override
         public int getSFX() {
            return SoundsManager.SOUND_CLICK2;
         }
      });
      menuElements.add(
         new Text(null, ImageManager.getImage(Images.top_flag_frame).getWidth() + CFG.PADDING * 4, CFG.PADDING * 4 + CFG.TEXT_HEIGHT) {
            int iCurrent = 0;

            @Override
            public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
               CFG.fontMain.getData().setScale(0.8F);
               CFG.drawTextWithShadow(oSB, this.sText, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getColor(isActive));
               CFG.drawTextWithShadow(
                  oSB,
                  "" + this.getCurrent(),
                  this.getPosX() + (int)(this.getTextWidth() * 0.8F) + iTranslateX,
                  this.getPosY() + iTranslateY,
                  CFG.COLOR_TEXT_NUM_OF_PROVINCES
               );
               CFG.fontMain.getData().setScale(1.0F);
            }

            @Override
            public Color getColor(boolean isActive) {
               return isActive
                  ? CFG.COLOR_TEXT_RANK_ACTIVE
                  : (this.getClickable() ? (this.getIsHovered() ? CFG.COLOR_TEXT_RANK_HOVER : CFG.COLOR_TEXT_RANK) : new Color(0.78F, 0.78F, 0.78F, 0.7F));
            }

            @Override
            public void buildElementHover() {
               this.menuElementHover = CFG.game.getHover_ProvincesOfCiv(CFG.getActiveCivInfo());
            }

            @Override
            public int getCurrent() {
               return this.iCurrent;
            }

            @Override
            public void setCurrent(int nCurrent) {
               this.iCurrent = nCurrent;
            }

            @Override
            public int getWidth() {
               return CFG.CIV_INFO_MENU_WIDTH - ImageManager.getImage(Images.top_flag_frame).getWidth() - CFG.PADDING * 4;
            }
         }
      );
      menuElements.add(
         new Text("", ImageManager.getImage(Images.top_flag_frame).getWidth() + CFG.PADDING * 4, CFG.PADDING * 4 + CFG.TEXT_HEIGHT) {
            @Override
            public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
               CFG.fontMain.getData().setScale(0.8F);
               CFG.drawTextWithShadow(oSB, this.getText(), this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getColor(isActive));
               CFG.fontMain.getData().setScale(1.0F);
            }

            @Override
            public Color getColor(boolean isActive) {
               return isActive
                  ? CFG.COLOR_TEXT_RANK_ACTIVE
                  : (this.getClickable() ? (this.getIsHovered() ? CFG.COLOR_TEXT_RANK_HOVER : CFG.COLOR_TEXT_RANK) : new Color(0.78F, 0.78F, 0.78F, 0.7F));
            }

            @Override
            public void buildElementHover() {
               this.menuElementHover = CFG.game.getHover_LeaderOfCiv(CFG.getActiveCivInfo());
            }

            @Override
            public int getWidth() {
               return CFG.CIV_INFO_MENU_WIDTH - ImageManager.getImage(Images.top_flag_frame).getWidth() - CFG.PADDING * 4;
            }
         }
      );
      this.initMenu(
         new SliderMenuTitle("", 0, false, false),
         CFG.GAME_WIDTH - CFG.CIV_INFO_MENU_WIDTH,
         CFG.isAndroid() && !CFG.LANDSCAPE
            ? ImageManager.getImage(Images.new_game_top).getHeight() + CFG.PADDING * 4 + (int)(CFG.TEXT_HEIGHT * 0.6F)
            : ImageManager.getImage(Images.top_left2).getHeight() + CFG.PADDING * 2,
         CFG.CIV_INFO_MENU_WIDTH,
         ImageManager.getImage(Images.top_flag_frame).getHeight() + CFG.PADDING * 4,
         menuElements,
         false,
         true
      );
      this.updateLanguage();
   }

   @Override
   public void updateLanguage() {
      this.getMenuElement(0).setText("" + CFG.game.getCiv(CFG.getActiveCivInfo()).getRankPosition());
      this.getMenuElement(1).setText(CFG.getActiveCivInfo() > 0 ? CFG.game.getCiv(CFG.getActiveCivInfo()).getCivName() : "");
      this.getMenuElement(3).setText(CFG.langManager.get("Provinces") + ": ");
   }

   @Override
   public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      if (lTime + 250L >= System.currentTimeMillis()) {
         iTranslateX += this.getWidth() - (int)(this.getWidth() * ((float)(System.currentTimeMillis() - lTime) / 250.0F));
         CFG.setRender_3(true);
      }

      ImageManager.getImage(Images.new_game_top_edge)
         .draw2(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.new_game_top_edge).getHeight() + iTranslateY,
            this.getWidth(),
            this.getHeight()
         );
      oSB.setColor(new Color(CFG.COLOR_GRADIENT_TITLE_BLUE.r, CFG.COLOR_GRADIENT_TITLE_BLUE.g, CFG.COLOR_GRADIENT_TITLE_BLUE.b, 0.25F));
      ImageManager.getImage(Images.line_32_off1)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() + 1 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
            this.getWidth(),
            this.getHeight() - 1
         );
      oSB.setColor(Color.WHITE);
      oSB.setColor(new Color(CFG.COLOR_GRADIENT_LIGHTER_DARK_BLUE.r, CFG.COLOR_GRADIENT_LIGHTER_DARK_BLUE.g, CFG.COLOR_GRADIENT_LIGHTER_DARK_BLUE.b, 1.0F));
      ImageManager.getImage(Images.gradient)
         .draw(
            oSB,
            this.getPosX() + 2 + iTranslateX,
            this.getMenuPosY() - ImageManager.getImage(Images.gradient).getHeight() + this.getHeight() - this.getHeight() / 2 + iTranslateY,
            this.getWidth() - 2,
            this.getHeight() / 2,
            false,
            true
         );
      oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.65F));
      ImageManager.getImage(Images.pix255_255_255)
         .draw(
            oSB,
            this.getPosX() + 2 + iTranslateX,
            this.getMenuPosY() + this.getHeight() - ImageManager.getImage(Images.pix255_255_255).getHeight() - 2 + iTranslateY,
            this.getWidth(),
            1
         );
      oSB.setColor(CFG.COLOR_NEW_GAME_EDGE_LINE);
      ImageManager.getImage(Images.pix255_255_255)
         .draw(
            oSB,
            this.getPosX() + 2 + iTranslateX,
            this.getMenuPosY() + this.getHeight() - ImageManager.getImage(Images.pix255_255_255).getHeight() - 1 + iTranslateY,
            this.getWidth(),
            1
         );
      oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.65F));
      ImageManager.getImage(Images.line_32_off1)
         .draw(
            oSB,
            this.getPosX() + 2 + iTranslateX,
            this.getMenuPosY() + this.getHeight() - ImageManager.getImage(Images.line_32_off1).getHeight() - 1 + iTranslateY,
            this.getWidth(),
            1,
            true,
            false
         );
      oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.8F));
      ImageManager.getImage(Images.slider_gradient)
         .draw(
            oSB,
            this.getPosX() + 2 + iTranslateX,
            this.getMenuPosY() + this.getHeight() - ImageManager.getImage(Images.slider_gradient).getHeight() - 1 + iTranslateY,
            this.getWidth() / 4,
            1
         );
      ImageManager.getImage(Images.slider_gradient)
         .draw(
            oSB,
            this.getPosX() + this.getWidth() - this.getWidth() / 4 + iTranslateX,
            this.getMenuPosY() + this.getHeight() - ImageManager.getImage(Images.slider_gradient).getHeight() - 1 + iTranslateY,
            this.getWidth() / 4,
            1,
            true,
            false
         );
      oSB.setColor(Color.WHITE);
      super.draw(oSB, iTranslateX, 1 + iTranslateY, sliderMenuIsActive);
   }

   @Override
   public void drawCloseButton(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      this.getCloseButtonImage(sliderMenuIsActive)
         .draw(
            oSB,
            this.getPosX() + this.getWidth() - ImageManager.getImage(Images.btn_close).getWidth() * 3 / 5 + iTranslateX,
            this.getPosY() - this.getTitle().getHeight() - ImageManager.getImage(Images.btn_close).getHeight() + iTranslateY,
            ImageManager.getImage(Images.btn_close).getWidth() * 3 / 5,
            ImageManager.getImage(Images.btn_close).getHeight() * 3 / 5
         );
   }

   public final float getImageScale(int nImageHeight) {
      return (float)CFG.TEXT_HEIGHT / nImageHeight < 1.0F ? (float)CFG.TEXT_HEIGHT / nImageHeight : 1.0F;
   }

   @Override
   public void onHovered() {
      CFG.menuManager.setOrderOfMenu_CreateNewGame_CivInfo();
   }

   @Override
   public void actionElement(int iID) {
      switch (iID) {
         case 1:
         case 3:
            Game_Render.drawCivNamesInCreateNewGame = !Game_Render.drawCivNamesInCreateNewGame;
            break;
         case 2:
            CFG.menuManager.getColorPicker().setPosX(CFG.PADDING * 4);
            CFG.menuManager.getColorPicker().setPosY(this.getPosY());
            CFG.menuManager.getColorPicker().setVisible(!CFG.menuManager.getColorPicker().getVisible(), ColorPicker_AoC.PickerAction.ACTIVE_CIVILIZATION_COLOR);
            if (CFG.menuManager.getColorPicker().getVisible()) {
               CFG.viewsManager.disableAllViews();
            }
            break;
         case 4:
            if (CFG.game.getCiv(CFG.getActiveCivInfo()).civGameData.leaderData.getWiki().length() > 0) {
               CFG.EDITOR_ACTIVE_GAMEDATA_TAG = CFG.game.getCiv(CFG.getActiveCivInfo()).civGameData.leaderData.getWiki();
               CFG.setDialogType(Dialog.GO_TO_WIKI_SCENARIO);
            }
      }
   }

   @Override
   public void actionClose() {
      this.setVisible(false);
      CFG.menuManager.hideCivilizations_Info_Players();
   }

   @Override
   public void setVisible(boolean visible) {
      if (visible && !this.getVisible()) {
         lTime = System.currentTimeMillis();
      }

      super.setVisible(visible);
   }
}
