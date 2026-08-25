package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

public class Menu_CreateScenario_PalletOfColors_List extends SliderMenu {
   public Menu_CreateScenario_PalletOfColors_List() {
      int tempW = CFG.CIV_INFO_MENU_WIDTH;
      int tempMaxH = CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING * 3 - CFG.BUTTON_HEIGHT * 3 / 4;
      int tempElemH = CFG.BUTTON_HEIGHT * 3 / 4;
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      menuElements.add(
         new Button_CNG_Options("", -1, 0, 0, tempW, tempElemH, true) {
            @Override
            public void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
               CFG.palletManager
                  .drawSampleColors_Standard(
                     oSB,
                     this.getPosX() + CFG.PADDING * 2 + iTranslateX,
                     this.getPosY() + CFG.PADDING * 2 + iTranslateY,
                     this.getWidth() - CFG.PADDING * 4,
                     this.getHeight() - CFG.PADDING * 4,
                     0,
                     isActive || CFG.palletManager.getActivePalletID() == 0
                  );
               super.drawText(oSB, iTranslateX, iTranslateY, isActive);
            }
         }
      );

      for (int i = 0; i < CFG.palletManager.getNumOfPallets(); i++) {
         menuElements.add(
            new Button_CNG_Options("" + CFG.palletManager.getNumOfColorsInPallet(i), -1, 0, tempElemH * (i + 1), tempW, tempElemH, true) {
               int iCurrent = 0;

               @Override
               public void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                  CFG.palletManager
                     .drawSampleColors(
                        oSB,
                        this.getPosX() + CFG.PADDING * 2 + iTranslateX,
                        this.getPosY() + CFG.PADDING * 2 + iTranslateY,
                        this.getWidth() - CFG.PADDING * 4,
                        this.getHeight() - CFG.PADDING * 4,
                        this.getCurrent(),
                        isActive || CFG.palletManager.getActivePalletID() == this.getCurrent() + 1
                     );
                  oSB.setColor(CFG.COLOR_GRADIENT_DARK_BLUE.r, CFG.COLOR_GRADIENT_DARK_BLUE.g, CFG.COLOR_GRADIENT_DARK_BLUE.b, 0.9F);
                  ImageManager.getImage(Images.line_32_off1)
                     .draw(
                        oSB,
                        this.getPosX() + this.getWidth() / 2 - this.getTextWidth() / 2 - CFG.PADDING * 2 + iTranslateX,
                        this.getPosY()
                           + this.getHeight() / 2
                           - this.getTextHeight() / 2
                           - CFG.CIV_COLOR_WIDTH
                           - ImageManager.getImage(Images.line_32_off1).getHeight()
                           + iTranslateY,
                        this.getTextWidth() + CFG.PADDING * 4,
                        this.getTextHeight() + CFG.CIV_COLOR_WIDTH * 2
                     );
                  oSB.setColor(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 1.0F);
                  ImageManager.getImage(Images.line_32_off1)
                     .draw(
                        oSB,
                        this.getPosX() + this.getWidth() / 2 - this.getTextWidth() / 2 - CFG.PADDING * 2 + iTranslateX,
                        1
                           + this.getPosY()
                           + this.getHeight() / 2
                           - this.getTextHeight() / 2
                           - CFG.CIV_COLOR_WIDTH
                           - ImageManager.getImage(Images.line_32_off1).getHeight()
                           + iTranslateY,
                        this.getTextWidth() + CFG.PADDING * 4,
                        1
                     );
                  ImageManager.getImage(Images.line_32_off1)
                     .draw(
                        oSB,
                        this.getPosX() + this.getWidth() / 2 - this.getTextWidth() / 2 - CFG.PADDING * 2 + iTranslateX,
                        this.getTextHeight()
                           + CFG.CIV_COLOR_WIDTH * 2
                           - 2
                           + this.getPosY()
                           + this.getHeight() / 2
                           - this.getTextHeight() / 2
                           - CFG.CIV_COLOR_WIDTH
                           - ImageManager.getImage(Images.line_32_off1).getHeight()
                           + iTranslateY,
                        this.getTextWidth() + CFG.PADDING * 4,
                        1
                     );
                  oSB.setColor(Color.WHITE);
                  super.drawText(oSB, iTranslateX, iTranslateY, isActive);
               }

               @Override
               public void setCurrent(int nCurrent) {
                  this.iCurrent = nCurrent;
               }

               @Override
               public int getCurrent() {
                  return this.iCurrent;
               }

               @Override
               public void buildElementHover() {
                  ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
                  ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
                  nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("PalletCivColors"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                  nElements.add(new MenuElement_Hover_v2_Element2(nData));
                  nData.clear();
                  nData.add(new MenuElement_Hover_v2_Element_Type_Space());
                  nElements.add(new MenuElement_Hover_v2_Element2(nData));
                  nData.clear();
                  nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("NumberOfColors") + ": "));
                  nData.add(new MenuElement_Hover_v2_Element_Type_Text(this.getText(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                  nElements.add(new MenuElement_Hover_v2_Element2(nData));
                  nData.clear();
                  this.menuElementHover = new MenuElement_Hover_v2(nElements);
               }
            }
         );
         menuElements.get(menuElements.size() - 1).setCurrent(i);
      }

      this.initMenu(
         new SliderMenuTitle(null, CFG.BUTTON_HEIGHT * 3 / 4, true, false) {
            @Override
            public void draw(SpriteBatch oSB, int iTranslateX, int nPosX, int nPosY, int nWidth, boolean sliderMenuIsActive) {
               ImageManager.getImage(Images.new_game_top_edge_title)
                  .draw2(
                     oSB,
                     Menu_CreateScenario_PalletOfColors_List.this.getPosX() - 2 + iTranslateX,
                     Menu_CreateScenario_PalletOfColors_List.this.getPosY()
                        - ImageManager.getImage(Images.new_game_top_edge_title).getHeight()
                        - this.getHeight(),
                     Menu_CreateScenario_PalletOfColors_List.this.getWidth() + 2,
                     this.getHeight(),
                     false,
                     false
                  );
               oSB.setColor(new Color(0.011F, 0.014F, 0.019F, 0.25F));
               ImageManager.getImage(Images.gradient)
                  .draw(
                     oSB,
                     Menu_CreateScenario_PalletOfColors_List.this.getPosX() + iTranslateX,
                     Menu_CreateScenario_PalletOfColors_List.this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() - this.getHeight() * 3 / 4,
                     Menu_CreateScenario_PalletOfColors_List.this.getWidth(),
                     this.getHeight() * 3 / 4,
                     false,
                     true
                  );
               oSB.setColor(new Color(0.451F, 0.329F, 0.11F, 1.0F));
               ImageManager.getImage(Images.pix255_255_255)
                  .draw(
                     oSB,
                     Menu_CreateScenario_PalletOfColors_List.this.getPosX() + iTranslateX,
                     Menu_CreateScenario_PalletOfColors_List.this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight(),
                     Menu_CreateScenario_PalletOfColors_List.this.getWidth()
                  );
               oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.4F));
               ImageManager.getImage(Images.line_32_off1)
                  .draw(
                     oSB,
                     Menu_CreateScenario_PalletOfColors_List.this.getPosX() + iTranslateX,
                     Menu_CreateScenario_PalletOfColors_List.this.getPosY()
                        - ImageManager.getImage(Images.pix255_255_255).getHeight()
                        - ImageManager.getImage(Images.line_32_off1).getHeight(),
                     Menu_CreateScenario_PalletOfColors_List.this.getWidth(),
                     1
                  );
               oSB.setColor(Color.WHITE);
               CFG.fontMain.getData().setScale(0.9F);
               CFG.drawText(
                  oSB,
                  this.getText(),
                  nPosX + nWidth / 2 - (int)(this.getTextWidth() * 0.9F / 2.0F) + iTranslateX,
                  nPosY - this.getHeight() + this.getHeight() / 2 + 1 - (int)(this.getTextHeight() * 0.9F / 2.0F),
                  new Color(0.92941177F, 0.99607843F, 1.0F, 0.75F)
               );
               CFG.fontMain.getData().setScale(1.0F);
            }
         },
         CFG.GAME_WIDTH - tempW,
         CFG.BUTTON_HEIGHT + CFG.PADDING * 2 + CFG.PADDING + CFG.BUTTON_HEIGHT * 3 / 4,
         tempW,
         tempMaxH < tempElemH * menuElements.size() ? tempMaxH : tempElemH * menuElements.size(),
         menuElements
      );
      this.updateLanguage();
   }

   @Override
   public void updateLanguage() {
      this.getMenuElement(0).setText(CFG.langManager.get("DefaultColors"));
      this.getTitle().setText(CFG.langManager.get("PalletsOfColors"));
   }

   @Override
   public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      ImageManager.getImage(Images.new_game_top_edge_line)
         .draw2(
            oSB,
            this.getPosX() - 2 + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.new_game_top_edge_line).getHeight() + iTranslateY,
            this.getWidth() + 2,
            this.getHeight(),
            false,
            true
         );
      super.draw(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
      oSB.setColor(CFG.COLOR_CREATE_NEW_GAME_BOX_PLAYERS);
      ImageManager.getImage(Images.pix255_255_255)
         .draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + this.getHeight(), this.getWidth());
      oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.4F));
      ImageManager.getImage(Images.line_32_off1)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY()
               - ImageManager.getImage(Images.pix255_255_255).getHeight()
               - ImageManager.getImage(Images.line_32_off1).getHeight()
               + this.getHeight(),
            this.getWidth(),
            1
         );
      oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.5F));
      ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() - 2 + iTranslateX, this.getPosY() + this.getHeight(), this.getWidth() + 2);
      oSB.setColor(Color.WHITE);
   }

   @Override
   public void drawScrollPos(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      if ((sliderMenuIsActive || this.getScrollModeY()) && !CFG.menuManager.getSliderMode()) {
         super.drawScrollPos(oSB, iTranslateX - 2, iTranslateY, sliderMenuIsActive);
      }
   }

   @Override
   public void actionElement(int iID) {
      switch (iID) {
         case 0:
            CFG.palletManager.setActivePalletID(0);
            CFG.palletManager.loadCivilizationStandardColors();
            CFG.toast.setInView(CFG.langManager.get("Done"));
            return;
         default:
            CFG.palletManager.setActivePalletID(iID);
            CFG.palletManager.loadCivilizationsPaletteOfColors(iID);
            CFG.toast.setInView(CFG.langManager.get("Done"));
      }
   }
}
