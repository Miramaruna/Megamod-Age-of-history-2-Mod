package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

public class Menu_InGame_BuildingsConstructed extends SliderMenu {
   public static int iSort = 1;

   public Menu_InGame_BuildingsConstructed(int tInit) {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      int tempWidth = (int)(CFG.CIV_INFO_MENU_WIDTH * 1.5F);
      int tempMenuPosY = ImageManager.getImage(Images.top_flag_frame).getHeight()
         + CFG.PADDING * 4
         + CFG.BUTTON_HEIGHT * 3 / 5
         + CFG.PADDING * 2
         + CFG.BUTTON_HEIGHT * 3 / 4
         - CFG.PADDING * 2;
      if (tempWidth > CFG.GAME_WIDTH) {
         tempWidth = CFG.GAME_WIDTH - CFG.PADDING * 2;
      }

      this.initMenu(null, CFG.GAME_WIDTH * 3 / 4 - tempWidth * 3 / 5, tempMenuPosY, tempWidth, CFG.GAME_HEIGHT * 3 / 5, menuElements, false, false);
   }

   public Menu_InGame_BuildingsConstructed() {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      int tPosY = CFG.PADDING + CFG.TEXT_HEIGHT + CFG.PADDING * 4;
      int tempWidth = (int)(CFG.CIV_INFO_MENU_WIDTH * 1.5F);
      if (tempWidth > CFG.GAME_WIDTH) {
         tempWidth = CFG.GAME_WIDTH - CFG.PADDING * 2;
      }

      int tElemHeight = CFG.TEXT_HEIGHT + CFG.PADDING * 4;
      int tElemHeight2 = CFG.isAndroid() ? CFG.TEXT_HEIGHT + CFG.PADDING * 2 : CFG.TEXT_HEIGHT + CFG.PADDING * 2;
      menuElements.add(new Button_Statistics_Title(CFG.langManager.get("Civilizations"), CFG.PADDING * 2, 2, 0, CFG.BUTTON_WIDTH * 2, tElemHeight) {
         @Override
         public int getWidth() {
            return Menu_InGame_BuildingsConstructed.this.getElementW() * 2 + CFG.PADDING * 2 - 2;
         }

         @Override
         public Color getColor(boolean isActive) {
            return Menu_InGame_BuildingsConstructed.iSort == 0 ? CFG.COLOR_TEXT_NUM_OF_PROVINCES : super.getColor(isActive);
         }

         @Override
         public void buildElementHover() {
            ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
            ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("SortBy") + ": "));
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(this.getText(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
            this.menuElementHover = new MenuElement_Hover_v2(nElements);
         }
      });
      menuElements.add(
         new Button_Statistics_Title(
            CFG.langManager.get("ConstructedBuildings"), CFG.PADDING, CFG.PADDING * 2 + CFG.BUTTON_WIDTH * 5, 0, CFG.BUTTON_WIDTH, tElemHeight
         ) {
            @Override
            public int getPosX() {
               return Menu_InGame_BuildingsConstructed.this.getElementW() * 2 + CFG.PADDING * 2;
            }

            @Override
            public int getWidth() {
               return Menu_InGame_BuildingsConstructed.this.getW() - Menu_InGame_BuildingsConstructed.this.getElementW() * 2 + CFG.PADDING * 2 - 2;
            }

            @Override
            public Color getColor(boolean isActive) {
               return Menu_InGame_BuildingsConstructed.iSort == 1 ? CFG.COLOR_TEXT_NUM_OF_PROVINCES : super.getColor(isActive);
            }

            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("SortBy") + ": "));
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(this.getText(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
         }
      );
      ArrayList<Integer> tSorted = new ArrayList<>();
      if (iSort == 0) {
         for (int i = 1; i < CFG.game.getCivsSize(); i++) {
            tSorted.add(CFG.game.getSortedCivsAZ(i - 1));
         }
      } else if (iSort == 1) {
         ArrayList<Integer> tempIDS = new ArrayList<>();
         ArrayList<Integer> tempScore = new ArrayList<>();

         for (int i2 = 1; i2 < CFG.game.getCivsSize(); i2++) {
            tempIDS.add(CFG.game.getSortedCivsAZ(i2 - 1));
            tempScore.add(CFG.game.getCiv(CFG.game.getSortedCivsAZ(i2 - 1)).civGameData.iNumOfBuildingsConstructed);
         }

         int tAddID = 0;

         while (tempIDS.size() > 0) {
            tAddID = 0;

            for (int i3 = 1; i3 < tempIDS.size(); i3++) {
               if (tempScore.get(tAddID) < tempScore.get(i3)) {
                  tAddID = i3;
               }
            }

            tSorted.add(tempIDS.get(tAddID));
            tempIDS.remove(tAddID);
            tempScore.remove(tAddID);
         }
      }

      int iSize = tSorted.size();

      for (int i = 0; i < iSize; i++) {
         if (CFG.FOG_OF_WAR == 2) {
            if (CFG.getMetCiv(tSorted.get(i))) {
               menuElements.add(
                  new Button_Statistics_Flag_Clip(
                     tSorted.get(i),
                     "" + (i + 1) + ". " + CFG.game.getCiv(tSorted.get(i)).getCivName(),
                     CFG.PADDING,
                     CFG.PADDING * 2,
                     tPosY,
                     CFG.BUTTON_WIDTH * 2,
                     tElemHeight2
                  ) {
                     @Override
                     public int getWidth() {
                        return Menu_InGame_BuildingsConstructed.this.getElementW() * 2;
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
                     -1, "" + (i + 1) + ". " + CFG.langManager.get("Undiscovered"), CFG.PADDING, CFG.PADDING * 2, tPosY, CFG.BUTTON_WIDTH * 2, tElemHeight2
                  ) {
                     @Override
                     public int getWidth() {
                        return Menu_InGame_BuildingsConstructed.this.getElementW() * 2;
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
                  tSorted.get(i),
                  "" + (i + 1) + ". " + CFG.game.getCiv(tSorted.get(i)).getCivName(),
                  CFG.PADDING,
                  CFG.PADDING * 2,
                  tPosY,
                  CFG.BUTTON_WIDTH * 2,
                  tElemHeight2
               ) {
                  @Override
                  public int getWidth() {
                     return Menu_InGame_BuildingsConstructed.this.getElementW() * 2;
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
               CFG.getNumberWithSpaces("" + CFG.game.getCiv(tSorted.get(i)).civGameData.iNumOfBuildingsConstructed),
               CFG.PADDING,
               CFG.PADDING * 2 + CFG.BUTTON_WIDTH * 5,
               tPosY,
               CFG.BUTTON_WIDTH,
               tElemHeight2
            ) {
               @Override
               public int getPosX() {
                  return Menu_InGame_BuildingsConstructed.this.getElementW() * 2 + CFG.PADDING * 2;
               }

               @Override
               public int getWidth() {
                  return Menu_InGame_BuildingsConstructed.this.getW() - Menu_InGame_BuildingsConstructed.this.getElementW() * 2;
               }
            }
         );
         tPosY += tElemHeight2;
      }

      int tempMenuPosY = ImageManager.getImage(Images.top_flag_frame).getHeight() + CFG.PADDING * 4 + CFG.BUTTON_HEIGHT * 3 / 5 + CFG.PADDING * 2;
      this.initMenu(
         new SliderMenuTitle(null, CFG.BUTTON_HEIGHT * 3 / 5, true, true) {
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
               oSB.setColor(new Color(0.14901961F, 0.50980395F, 0.36078432F, 0.165F));
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
               oSB.setColor(new Color(0.14901961F, 0.50980395F, 0.36078432F, 0.375F));
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
               oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.425F));
               ImageManager.getImage(Images.slider_gradient)
                  .draw(
                     oSB,
                     nPosX + CFG.PADDING * 2 + iTranslateX,
                     nPosY + 1 - this.getHeight() / 2 - ImageManager.getImage(Images.slider_gradient).getHeight(),
                     (int)((nWidth - CFG.PADDING * 6 - this.getTextWidth() * 0.8F) / 2.0F),
                     1,
                     true,
                     false
                  );
               ImageManager.getImage(Images.slider_gradient)
                  .draw(
                     oSB,
                     nPosX + nWidth / 2 + CFG.PADDING + (int)(this.getTextWidth() * 0.8F / 2.0F) + iTranslateX,
                     nPosY + 1 - this.getHeight() / 2 - ImageManager.getImage(Images.slider_gradient).getHeight(),
                     (int)((nWidth - CFG.PADDING * 6 - this.getTextWidth() * 0.8F) / 2.0F),
                     1
                  );
               oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.325F));
               ImageManager.getImage(Images.slider_gradient)
                  .draw(
                     oSB,
                     nPosX + CFG.PADDING * 2 + iTranslateX,
                     nPosY + 2 - this.getHeight() / 2 - ImageManager.getImage(Images.slider_gradient).getHeight(),
                     (int)((nWidth - CFG.PADDING * 6 - this.getTextWidth() * 0.8F) / 2.0F),
                     1,
                     true,
                     false
                  );
               ImageManager.getImage(Images.slider_gradient)
                  .draw(
                     oSB,
                     nPosX + nWidth / 2 + CFG.PADDING + (int)(this.getTextWidth() * 0.8F / 2.0F) + iTranslateX,
                     nPosY + 2 - this.getHeight() / 2 - ImageManager.getImage(Images.slider_gradient).getHeight(),
                     (int)((nWidth - CFG.PADDING * 6 - this.getTextWidth() * 0.8F) / 2.0F),
                     1
                  );
               ImageManager.getImage(Images.slider_gradient)
                  .draw(
                     oSB,
                     nPosX + CFG.PADDING * 2 + iTranslateX,
                     nPosY - this.getHeight() / 2 - ImageManager.getImage(Images.slider_gradient).getHeight(),
                     (int)((nWidth - CFG.PADDING * 6 - this.getTextWidth() * 0.8F) / 2.0F),
                     1,
                     true,
                     false
                  );
               ImageManager.getImage(Images.slider_gradient)
                  .draw(
                     oSB,
                     nPosX + nWidth / 2 + CFG.PADDING + (int)(this.getTextWidth() * 0.8F / 2.0F) + iTranslateX,
                     nPosY - this.getHeight() / 2 - ImageManager.getImage(Images.slider_gradient).getHeight(),
                     (int)((nWidth - CFG.PADDING * 6 - this.getTextWidth() * 0.8F) / 2.0F),
                     1
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
            ? CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING * 2 - tempMenuPosY
            : menuElements.get(menuElements.size() - 1).getPosY() + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING,
         menuElements,
         true,
         true
      );
      this.updateLanguage();

      for (int i4 = 0; i4 < this.getMenuElementsSize(); i4++) {
         this.getMenuElement(i4).setCurrent(i4 / 2 % 2);
      }
   }

   @Override
   public void updateLanguage() {
      try {
         this.getTitle().setText(CFG.langManager.get("ConstructedBuildings"));
      } catch (NullPointerException var2) {
      }
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
      switch (iID) {
         case 0:
            if (iSort != iID) {
               iSort = iID;
               CFG.menuManager.rebuildInGame_BuildingsConstrcuted();
            }

            return;
         case 1:
            if (iSort != iID) {
               iSort = iID;
               CFG.menuManager.rebuildInGame_BuildingsConstrcuted();
            }

            return;
         default:
            if (iID % 2 == 0) {
               if (this.getMenuElement(iID).getCurrent() > 0) {
                  CFG.map.getMapCoordinates().centerToProvinceID(CFG.game.getCiv(this.getMenuElement(iID).getCurrent()).getCapitalProvinceID());
               } else {
                  CFG.toast.setInView(CFG.langManager.get("UndiscoveredCivilization"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE);
               }
            }
      }
   }

   public final int getW() {
      return this.getWidth() - CFG.PADDING * 4;
   }

   public final int getElementW() {
      return this.getW() / 3;
   }
}
