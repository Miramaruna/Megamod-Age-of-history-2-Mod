package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

public class Menu_InGame_CreateAVassal_Info extends SliderMenu {
   public static boolean hideAnimation = true;

   public Menu_InGame_CreateAVassal_Info() {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      menuElements.add(
         new Text_Scrollable(
            null,
            ImageManager.getImage(Images.top_flag_frame).getWidth() + CFG.PADDING * 4,
            CFG.PADDING * 3,
            CFG.CIV_INFO_MENU_WIDTH - ImageManager.getImage(Images.top_flag_frame).getWidth() - CFG.PADDING * 5,
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
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("SelectVassal"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }

            @Override
            public void draw_Element(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
               super.draw_Element(
                  oSB,
                  iTranslateX
                     + CFG.PADDING
                     + (int)(
                        ImageManager.getImage(Images.flag_rect).getWidth()
                           * Menu_InGame_CreateAVassal_Info.this.getImageScale(ImageManager.getImage(Images.flag_rect).getHeight())
                     ),
                  iTranslateY,
                  isActive,
                  scrollableY
               );

               try {
                  CFG.createVassal_Data
                     .getFlagOfCiv()
                     .draw(
                        oSB,
                        this.getPosX() + this.getCurrent() + iTranslateX,
                        this.getPosY()
                           - CFG.createVassal_Data.getFlagOfCiv().getHeight()
                           + (
                                 this.getHeight()
                                    - (int)(
                                       ImageManager.getImage(Images.flag_rect).getHeight()
                                          * Menu_InGame_CreateAVassal_Info.this.getImageScale(ImageManager.getImage(Images.flag_rect).getHeight())
                                    )
                              )
                              / 2
                           + iTranslateY,
                        (int)(
                           ImageManager.getImage(Images.flag_rect).getWidth()
                              * Menu_InGame_CreateAVassal_Info.this.getImageScale(ImageManager.getImage(Images.flag_rect).getHeight())
                        ),
                        (int)(
                           ImageManager.getImage(Images.flag_rect).getHeight()
                              * Menu_InGame_CreateAVassal_Info.this.getImageScale(ImageManager.getImage(Images.flag_rect).getHeight())
                        )
                     );
               } catch (NullPointerException var7) {
                  ImageManager.getImage(Images.randomCivilizationFlag)
                     .draw(
                        oSB,
                        this.getPosX() + this.getCurrent() + iTranslateX,
                        this.getPosY()
                           - ImageManager.getImage(Images.randomCivilizationFlag).getHeight()
                           + (
                                 this.getHeight()
                                    - (int)(
                                       ImageManager.getImage(Images.flag_rect).getHeight()
                                          * Menu_InGame_CreateAVassal_Info.this.getImageScale(ImageManager.getImage(Images.flag_rect).getHeight())
                                    )
                              )
                              / 2
                           + iTranslateY,
                        (int)(
                           ImageManager.getImage(Images.flag_rect).getWidth()
                              * Menu_InGame_CreateAVassal_Info.this.getImageScale(ImageManager.getImage(Images.flag_rect).getHeight())
                        ),
                        (int)(
                           ImageManager.getImage(Images.flag_rect).getHeight()
                              * Menu_InGame_CreateAVassal_Info.this.getImageScale(ImageManager.getImage(Images.flag_rect).getHeight())
                        )
                     );
               }

               ImageManager.getImage(Images.flag_rect)
                  .draw(
                     oSB,
                     this.getPosX() + this.getCurrent() + iTranslateX,
                     this.getPosY()
                        - ImageManager.getImage(Images.flag_rect).getHeight()
                        + (int)(
                              this.getHeight()
                                 - ImageManager.getImage(Images.flag_rect).getHeight()
                                    * Menu_InGame_CreateAVassal_Info.this.getImageScale(ImageManager.getImage(Images.flag_rect).getHeight())
                           )
                           / 2
                        + iTranslateY,
                     (int)(
                        ImageManager.getImage(Images.flag_rect).getWidth()
                           * Menu_InGame_CreateAVassal_Info.this.getImageScale(ImageManager.getImage(Images.flag_rect).getHeight())
                     ),
                     (int)(
                        ImageManager.getImage(Images.flag_rect).getHeight()
                           * Menu_InGame_CreateAVassal_Info.this.getImageScale(ImageManager.getImage(Images.flag_rect).getHeight())
                     )
                  );
            }

            @Override
            public int getTextWidth() {
               try {
                  return super.getTextWidth()
                     + CFG.PADDING
                     + (int)(
                        ImageManager.getImage(Images.flag_rect).getWidth()
                           * Menu_InGame_CreateAVassal_Info.this.getImageScale(ImageManager.getImage(Images.flag_rect).getHeight())
                     );
               } catch (IndexOutOfBoundsException var2) {
                  return super.getTextWidth();
               }
            }
         }
      );
      menuElements.add(
         new Button_FlagFrame(CFG.PADDING * 2, CFG.PADDING * 2, true) {
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
            public Image getFlag() {
               try {
                  return CFG.createVassal_Data.getFlagOfCivH() != null
                     ? CFG.createVassal_Data.getFlagOfCivH()
                     : ImageManager.getImage(Images.randomCivilizationFlag);
               } catch (NullPointerException var2) {
                  return ImageManager.getImage(Images.randomCivilizationFlag);
               }
            }
         }
      );
      menuElements.add(
         new Text(null, ImageManager.getImage(Images.top_flag_frame).getWidth() + CFG.PADDING * 4, CFG.PADDING * 4 + CFG.TEXT_HEIGHT) {
            int iCurrent = 0;

            @Override
            public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
               CFG.fontMain.getData().setScale(0.8F);
               CFG.drawTextWithShadow(
                  oSB,
                  this.sText,
                  this.getPosX() + iTranslateX,
                  this.getPosY() + (int)(this.getHeight() - this.getTextHeight() * 0.8F) / 2 + iTranslateY,
                  this.getColor(isActive)
               );
               CFG.drawTextWithShadow(
                  oSB,
                  "" + this.getCurrent(),
                  this.getPosX() + (int)(this.getTextWidth() * 0.8F) + iTranslateX,
                  this.getPosY() + (int)(this.getHeight() - this.getTextHeight() * 0.8F) / 2 + iTranslateY,
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
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Provinces") + ": "));
               nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + CFG.game.getSelectedProvinces().getProvincesSize(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }

            @Override
            public int getCurrent() {
               return this.iCurrent;
            }

            @Override
            public void setCurrent(int nCurrent) {
               this.iCurrent = nCurrent;
            }
         }
      );
      this.initMenu(
         new SliderMenuTitle("", 0, false, false),
         0,
         CFG.BUTTON_HEIGHT + CFG.PADDING * 3,
         CFG.CIV_INFO_MENU_WIDTH,
         ImageManager.getImage(Images.top_flag_frame).getHeight() + CFG.PADDING * 4,
         menuElements,
         true,
         true
      );
      this.updateLanguage();
   }

   @Override
   public void updateLanguage() {
      try {
         this.getMenuElement(0)
            .setText(CFG.createVassal_Data.sCivTag == null ? CFG.langManager.get("SelectCivilization") : CFG.langManager.getCiv(CFG.createVassal_Data.sCivTag));
      } catch (IndexOutOfBoundsException var2) {
         this.getMenuElement(0).setText(CFG.langManager.get("SelectCivilization"));
      } catch (NullPointerException var3) {
         this.getMenuElement(0).setText(CFG.langManager.get("SelectCivilization"));
      }

      this.getMenuElement(2).setText(CFG.langManager.get("Provinces") + ": ");
      this.getMenuElement(2).setCurrent(CFG.game.getSelectedProvinces().getProvincesSize());
      this.getMenuElement(0)
         .setPosY(
            this.getMenuElement(1).getPosY()
               + this.getMenuElement(1).getHeight() / 2
               - (int)((CFG.TEXT_HEIGHT + CFG.TEXT_HEIGHT * 0.8F + CFG.PADDING * 2) / 2.0F)
         );
      this.getMenuElement(2).setPosY(this.getMenuElement(0).getPosY() + CFG.TEXT_HEIGHT + CFG.PADDING);
   }

   @Override
   public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      ImageManager.getImage(Images.new_game_top_edge)
         .draw2(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.new_game_top_edge).getHeight() + iTranslateY,
            this.getWidth(),
            this.getHeight(),
            true,
            false
         );
      oSB.setColor(new Color(CFG.COLOR_GRADIENT_LIGHTER_DARK_BLUE.r, CFG.COLOR_GRADIENT_LIGHTER_DARK_BLUE.g, CFG.COLOR_GRADIENT_LIGHTER_DARK_BLUE.b, 1.0F));
      ImageManager.getImage(Images.gradient)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
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
            this.getPosX() + iTranslateX,
            this.getMenuPosY() + this.getHeight() - ImageManager.getImage(Images.pix255_255_255).getHeight() - 2 + iTranslateY,
            this.getWidth() - 2,
            1
         );
      oSB.setColor(CFG.COLOR_NEW_GAME_EDGE_LINE);
      ImageManager.getImage(Images.pix255_255_255)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getMenuPosY() + this.getHeight() - ImageManager.getImage(Images.pix255_255_255).getHeight() - 1 + iTranslateY,
            this.getWidth() - 2,
            1
         );
      oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.65F));
      ImageManager.getImage(Images.line_32_off1)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getMenuPosY() + this.getHeight() - ImageManager.getImage(Images.line_32_off1).getHeight() - 1 + iTranslateY,
            this.getWidth() - 2,
            1
         );
      oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.8F));
      ImageManager.getImage(Images.slider_gradient)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getMenuPosY() + this.getHeight() - ImageManager.getImage(Images.slider_gradient).getHeight() - 1 + iTranslateY,
            this.getWidth() / 4,
            1
         );
      ImageManager.getImage(Images.slider_gradient)
         .draw(
            oSB,
            this.getPosX() + this.getWidth() - 2 - this.getWidth() / 4 + iTranslateX,
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
   public void actionElement(int iID) {
      CFG.menuManager.setOrderOfMenu_InGame_CreateAVassal_Info();
      switch (iID) {
         case 0:
         case 2:
            CFG.menuManager.setVisible_InGame_CreateVassal_Civs(!CFG.menuManager.getVisible_InGame_CreateVassal_Civs());
            break;
         case 1:
            CFG.menuManager.getColorPicker().setPosX(CFG.GAME_WIDTH - CFG.menuManager.getColorPicker().getWidth() - CFG.PADDING * 4);
            CFG.menuManager.getColorPicker().setPosY(this.getPosY() + CFG.PADDING * 2);
            CFG.menuManager.getColorPicker().setVisible(!CFG.menuManager.getColorPicker().getVisible(), ColorPicker_AoC.PickerAction.CREATE_VASSAL_COLOR);
            if (CFG.menuManager.getColorPicker().getVisible()) {
               CFG.viewsManager.disableAllViews();
            }
      }
   }
}
