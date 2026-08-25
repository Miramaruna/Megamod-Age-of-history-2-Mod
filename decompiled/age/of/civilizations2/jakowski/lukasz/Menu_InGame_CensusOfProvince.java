package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

public class Menu_InGame_CensusOfProvince extends SliderMenu {
   public static int PROVINCE_ID = 0;

   public Menu_InGame_CensusOfProvince(int nProvinceID) {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      int tempWidth = CFG.CIV_INFO_MENU_WIDTH * 4 / 5;
      int tempHeight = CFG.BUTTON_HEIGHT + CFG.BUTTON_HEIGHT * 3 / 4;
      PROVINCE_ID = nProvinceID;
      menuElements.add(
         new Button_Statistics_Title(CFG.langManager.get("EthnicGroups"), CFG.PADDING * 2, 2, 0, CFG.BUTTON_WIDTH * 2, CFG.TEXT_HEIGHT + CFG.PADDING * 4) {
            @Override
            public int getWidth() {
               return Menu_InGame_CensusOfProvince.this.getElementW() * 2 + CFG.PADDING * 2 - 2;
            }

            @Override
            public Color getColor(boolean isActive) {
               return CFG.COLOR_TEXT_NUM_OF_PROVINCES;
            }

            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Nationalities"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
         }
      );
      menuElements.add(
         new Button_Statistics_Title(
            CFG.langManager.get("Population"), CFG.PADDING, CFG.PADDING * 2 + CFG.BUTTON_WIDTH * 2, 0, CFG.BUTTON_WIDTH, CFG.TEXT_HEIGHT + CFG.PADDING * 4
         ) {
            @Override
            public int getPosX() {
               return Menu_InGame_CensusOfProvince.this.getElementW() * 2 + CFG.PADDING * 2;
            }

            @Override
            public int getWidth() {
               return Menu_InGame_CensusOfProvince.this.getW() - Menu_InGame_CensusOfProvince.this.getElementW() * 2 + CFG.PADDING * 2 - 2;
            }

            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("DemographyOfProvince"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
         }
      );
      ArrayList tSorted = new ArrayList();
      ArrayList<Integer> tempIDs = new ArrayList<>();

      for (int i2 = 0; i2 < CFG.game.getProvince(PROVINCE_ID).getPopulationData().getNationalitiesSize(); i2++) {
         tempIDs.add(i2);
      }

      while (tempIDs.size() > 0) {
         int tAdd = 0;

         for (int i = 1; i < tempIDs.size(); i++) {
            if (CFG.game.getProvince(PROVINCE_ID).getPopulationData().getPopulationID(tempIDs.get(tAdd))
               < CFG.game.getProvince(PROVINCE_ID).getPopulationData().getPopulationID(tempIDs.get(i))) {
               tAdd = i;
            }
         }

         tSorted.add(tempIDs.get(tAdd));
         tempIDs.remove(tAdd);
      }

      int tPosY = CFG.PADDING + CFG.TEXT_HEIGHT + CFG.PADDING * 4;

      for (int ix = 0; ix < tSorted.size(); ix++) {
         if (CFG.FOG_OF_WAR == 2) {
            if (CFG.getMetCiv(CFG.game.getProvince(PROVINCE_ID).getPopulationData().getCivID((Integer)tSorted.get(ix)))) {
               menuElements.add(
                  new Button_Statistics_Flag_Clip(
                     CFG.game.getProvince(PROVINCE_ID).getPopulationData().getCivID((Integer)tSorted.get(ix)),
                     CFG.game.getCiv(CFG.game.getProvince(PROVINCE_ID).getPopulationData().getCivID((Integer)tSorted.get(ix))).getCivName(),
                     CFG.PADDING * 2,
                     2,
                     tPosY,
                     CFG.BUTTON_WIDTH * 2,
                     CFG.TEXT_HEIGHT + CFG.PADDING * 2
                  ) {
                     @Override
                     public int getWidth() {
                        return Menu_InGame_CensusOfProvince.this.getElementW() * 2 + CFG.PADDING * 2 - 2;
                     }

                     @Override
                     public Color getColor(boolean isActive) {
                        return isActive
                           ? CFG.COLOR_TEXT_OPTIONS_LEFT_NS_ACTIVE
                           : (
                              this.getClickable()
                                 ? (this.getIsHovered() ? CFG.COLOR_TEXT_OPTIONS_LEFT_NS_HOVER : CFG.COLOR_TEXT_OPTIONS_LEFT_NS)
                                 : CFG.COLOR_BUTTON_MENU_TEXT_NOT_CLICKABLE
                           );
                     }
                  }
               );
            } else {
               menuElements.add(
                  new Button_Statistics_Flag_Clip(
                     -1, CFG.langManager.get("Undiscovered"), CFG.PADDING * 2, 2, tPosY, CFG.BUTTON_WIDTH * 2, CFG.TEXT_HEIGHT + CFG.PADDING * 2
                  ) {
                     @Override
                     public int getWidth() {
                        return Menu_InGame_CensusOfProvince.this.getElementW() * 2 + CFG.PADDING * 2 - 2;
                     }

                     @Override
                     public Color getColor(boolean isActive) {
                        return isActive
                           ? CFG.COLOR_TEXT_OPTIONS_LEFT_NS_ACTIVE
                           : (
                              this.getClickable()
                                 ? (this.getIsHovered() ? CFG.COLOR_TEXT_OPTIONS_LEFT_NS_HOVER : CFG.COLOR_TEXT_OPTIONS_LEFT_NS)
                                 : CFG.COLOR_BUTTON_MENU_TEXT_NOT_CLICKABLE
                           );
                     }
                  }
               );
            }
         } else {
            menuElements.add(
               new Button_Statistics_Flag_Clip(
                  CFG.game.getProvince(PROVINCE_ID).getPopulationData().getCivID((Integer)tSorted.get(ix)),
                  CFG.game.getCiv(CFG.game.getProvince(PROVINCE_ID).getPopulationData().getCivID((Integer)tSorted.get(ix))).getCivName(),
                  CFG.PADDING * 2,
                  2,
                  tPosY,
                  CFG.BUTTON_WIDTH * 2,
                  CFG.TEXT_HEIGHT + CFG.PADDING * 2
               ) {
                  @Override
                  public int getWidth() {
                     return Menu_InGame_CensusOfProvince.this.getElementW() * 2 + CFG.PADDING * 2 - 2;
                  }

                  @Override
                  public Color getColor(boolean isActive) {
                     return isActive
                        ? CFG.COLOR_TEXT_OPTIONS_LEFT_NS_ACTIVE
                        : (
                           this.getClickable()
                              ? (this.getIsHovered() ? CFG.COLOR_TEXT_OPTIONS_LEFT_NS_HOVER : CFG.COLOR_TEXT_OPTIONS_LEFT_NS)
                              : CFG.COLOR_BUTTON_MENU_TEXT_NOT_CLICKABLE
                        );
                  }
               }
            );
         }

         menuElements.add(
            new Button_Statistics(
               CFG.getNumberWithSpaces("" + CFG.game.getProvince(PROVINCE_ID).getPopulationData().getPopulationID((Integer)tSorted.get(ix))),
               CFG.PADDING,
               CFG.PADDING * 2 + CFG.BUTTON_WIDTH * 2,
               tPosY,
               CFG.BUTTON_WIDTH,
               CFG.TEXT_HEIGHT + CFG.PADDING * 2
            ) {
               @Override
               public int getPosX() {
                  return Menu_InGame_CensusOfProvince.this.getElementW() * 2 + CFG.PADDING * 2;
               }

               @Override
               public int getWidth() {
                  return Menu_InGame_CensusOfProvince.this.getW() - Menu_InGame_CensusOfProvince.this.getElementW() * 2 + CFG.PADDING * 2 - 2;
               }
            }
         );
         tPosY += CFG.TEXT_HEIGHT + CFG.PADDING * 2;
      }

      if (tSorted.size() > 1) {
         menuElements.add(
            new Button_Statistics_Total(
               CFG.langManager.get("Total") + ":", CFG.PADDING, CFG.PADDING * 2, tPosY, CFG.BUTTON_WIDTH * 2, CFG.TEXT_HEIGHT + CFG.PADDING * 2
            ) {
               @Override
               public int getWidth() {
                  return Menu_InGame_CensusOfProvince.this.getElementW() * 2;
               }

               @Override
               public Color getColor(boolean isActive) {
                  return isActive
                     ? CFG.COLOR_TEXT_OPTIONS_NS_ACTIVE
                     : (
                        this.getClickable()
                           ? (this.getIsHovered() ? CFG.COLOR_TEXT_OPTIONS_NS_HOVER : CFG.COLOR_TEXT_OPTIONS_NS)
                           : CFG.COLOR_BUTTON_MENU_TEXT_NOT_CLICKABLE
                     );
               }

               @Override
               public void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                  CFG.fontMain.getData().setScale(0.6F);
                  CFG.drawTextWithShadow(
                     oSB,
                     this.getTextToDraw(),
                     this.getPosX() + this.getWidth() - CFG.PADDING * 2 - (int)(this.iTextWidth * 0.6F) + iTranslateX,
                     this.getPosY() + this.getHeight() / 2 - (int)(this.iTextHeight * 0.6F / 2.0F) + iTranslateY,
                     this.getColor(isActive)
                  );
                  CFG.fontMain.getData().setScale(1.0F);
               }
            }
         );
         menuElements.add(
            new Button_Statistics(
               CFG.getNumberWithSpaces("" + CFG.game.getProvince(PROVINCE_ID).getPopulationData().getPopulation()),
               CFG.PADDING,
               CFG.PADDING * 2 + CFG.BUTTON_WIDTH * 2,
               tPosY,
               CFG.BUTTON_WIDTH,
               CFG.TEXT_HEIGHT + CFG.PADDING * 2
            ) {
               @Override
               public int getPosX() {
                  return Menu_InGame_CensusOfProvince.this.getElementW() * 2 + CFG.PADDING * 2;
               }

               @Override
               public int getWidth() {
                  return Menu_InGame_CensusOfProvince.this.getW() - Menu_InGame_CensusOfProvince.this.getElementW() * 2 + CFG.PADDING * 2 - 2;
               }

               @Override
               public Color getColor(boolean isActive) {
                  return CFG.COLOR_TEXT_POPULATION;
               }
            }
         );
      }

      this.initMenu(
         new SliderMenuTitle(
            CFG.game.getProvince(PROVINCE_ID).getName().length() > 0 ? CFG.game.getProvince(PROVINCE_ID).getName() : CFG.langManager.get("Demography"),
            CFG.TEXT_HEIGHT + CFG.PADDING * 4,
            true,
            true
         ) {
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
               oSB.setColor(new Color(0.1764706F, 0.22352941F, 0.45882353F, 0.225F));
               ImageManager.getImage(Images.line_32_off1)
                  .draw(
                     oSB,
                     nPosX + 2 + iTranslateX,
                     nPosY - this.getHeight() + 2 - ImageManager.getImage(Images.line_32_off1).getHeight(),
                     nWidth - 4,
                     this.getHeight() - 2,
                     false,
                     true
                  );
               oSB.setColor(new Color(0.1764706F, 0.22352941F, 0.45882353F, 0.375F));
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
               oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.5F));
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
               oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.45F));
               ImageManager.getImage(Images.slider_gradient)
                  .draw(oSB, nPosX + 2 + iTranslateX, nPosY - 1 - ImageManager.getImage(Images.slider_gradient).getHeight(), (nWidth - 4) / 2, 1);
               ImageManager.getImage(Images.slider_gradient)
                  .draw(
                     oSB,
                     nPosX + 2 + (nWidth - 4) - (nWidth - 4) / 2 + iTranslateX,
                     nPosY - 1 - ImageManager.getImage(Images.slider_gradient).getHeight(),
                     (nWidth - 4) / 2,
                     1,
                     true,
                     false
                  );
               oSB.setColor(Color.WHITE);
               ImageManager.getImage(Images.population)
                  .draw(
                     oSB,
                     nPosX
                        + (int)(nWidth - this.getTextWidth() * 0.7F) / 2
                        - CFG.PADDING
                        - (int)(ImageManager.getImage(Images.population).getWidth() * Menu_InGame_CensusOfProvince.this.getImageScale())
                        + iTranslateX,
                     2
                        + nPosY
                        - this.getHeight()
                        + this.getHeight() / 2
                        - ImageManager.getImage(Images.population).getHeight()
                        - (int)(ImageManager.getImage(Images.population).getHeight() * Menu_InGame_CensusOfProvince.this.getImageScale()) / 2,
                     (int)(ImageManager.getImage(Images.population).getWidth() * Menu_InGame_CensusOfProvince.this.getImageScale()),
                     (int)(ImageManager.getImage(Images.population).getHeight() * Menu_InGame_CensusOfProvince.this.getImageScale())
                  );
               CFG.fontMain.getData().setScale(0.7F);
               CFG.drawText(
                  oSB,
                  this.getText(),
                  nPosX + (int)(nWidth - this.getTextWidth() * 0.7F) / 2 + iTranslateX,
                  2 + nPosY - this.getHeight() + (int)(this.getHeight() - this.getTextHeight() * 0.7F) / 2,
                  Color.WHITE
               );
               CFG.fontMain.getData().setScale(1.0F);
            }
         },
         CFG.CIV_INFO_MENU_WIDTH + CFG.PADDING,
         ImageManager.getImage(Images.top_flag_frame).getHeight() + CFG.PADDING * 3 + CFG.TEXT_HEIGHT + CFG.PADDING * 4,
         tempWidth,
         tempHeight,
         menuElements,
         false,
         true
      );
      this.updateLanguage();

      for (int var10 = 0; var10 < this.getMenuElementsSize(); var10++) {
         this.getMenuElement(var10).setCurrent(var10 / 2 % 2);
      }
   }

   public final float getImageScale() {
      return (float)CFG.TEXT_HEIGHT / CFG.CIV_FLAG_HEIGHT;
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
            this.getHeight() + 2,
            false,
            true
         );
      ImageManager.getImage(Images.new_game_top_edge)
         .draw2(
            oSB,
            this.getPosX() + this.getWidth() - ImageManager.getImage(Images.new_game_top_edge).getWidth() + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.new_game_top_edge).getHeight() + iTranslateY,
            ImageManager.getImage(Images.new_game_top_edge).getWidth(),
            this.getHeight() + 2,
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
      oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.65F));
      ImageManager.getImage(Images.line_32_off1)
         .draw(
            oSB,
            this.getPosX() + this.getMenuElement(0).getPosX() + iTranslateX,
            this.getMenuPosY()
               - 1
               + this.getMenuElement(0).getPosY()
               - ImageManager.getImage(Images.line_32_off1).getHeight()
               + this.getMenuElement(0).getHeight()
               + iTranslateY,
            this.getWidth() - 4,
            1
         );
      oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.45F));
      ImageManager.getImage(Images.pix255_255_255)
         .draw(
            oSB,
            this.getPosX() + this.getMenuElement(0).getPosX() + iTranslateX,
            this.getMenuPosY()
               + this.getMenuElement(0).getPosY()
               - ImageManager.getImage(Images.pix255_255_255).getHeight()
               + this.getMenuElement(0).getHeight()
               + iTranslateY,
            this.getWidth() - 4,
            1
         );
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
   public final void actionElement(int iID) {
   }

   public final int getW() {
      return this.getWidth() - CFG.PADDING * 4;
   }

   public final int getElementW() {
      return this.getW() / 3;
   }
}
