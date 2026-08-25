package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

class Menu_PeaceTreaty_Provinces extends SliderMenu {
   protected Menu_PeaceTreaty_Provinces() {
      int tempW = CFG.CIV_INFO_MENU_WIDTH;
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      int tY = 0;
      menuElements.add(
         new Slider_FlagAction_Date(
            CFG.langManager.get("Truce"),
            CFG.PADDING,
            tY,
            tempW - CFG.PADDING * 2,
            CFG.TEXT_HEIGHT + CFG.PADDING * 2 + CFG.PADDING * 4,
            30,
            75,
            CFG.peaceTreatyData.peaceTreatyGameData.TRUCE_LENGTH,
            0.65F
         ) {
            @Override
            public String getDrawText() {
               return CFG.langManager.get("TurnsX", this.getCurrent());
            }

            @Override
            public int getSliderHeight() {
               return CFG.PADDING * 2;
            }

            @Override
            public void actionElement(int iID) {
               CFG.peaceTreatyData.peaceTreatyGameData.TRUCE_LENGTH = this.getCurrent();
            }

            @Override
            public Color getColorLEFT() {
               return new Color(0.06666667F, 0.24705882F, 0.5058824F, 0.75F);
            }
         }
      );
      tY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;

      for (int i = 0; i < CFG.peaceTreatyData.peaceTreatyGameData.lCivsData_Aggressors.size(); i++) {
         int var10006 = tempW - CFG.BUTTON_WIDTH;
         int var10007 = CFG.TEXT_HEIGHT + CFG.PADDING * 4;
         menuElements.add(
            new Button_PeaceTreaty_Demands_TakeAll(CFG.peaceTreatyData.peaceTreatyGameData.lCivsData_Aggressors.get(i).iCivID, 0, tY, var10006, var10007, true)
         );
         menuElements.add(
            new Button_PeaceTreaty_Demands_TakeAll_VicPoints(
               CFG.peaceTreatyData.peaceTreatyGameData.lCivsData_Aggressors.get(i).iCivID,
               CFG.peaceTreatyData.peaceTreatyGameData.lCivsData_Aggressors.get(i).getVictoryPointsTotal(),
               tempW - CFG.BUTTON_WIDTH,
               tY,
               CFG.BUTTON_WIDTH,
               CFG.TEXT_HEIGHT + CFG.PADDING * 4,
               true
            )
         );
         int var13;
         menuElements.add(
            new Button_PeaceTreaty_Demands_Vassalize(
               CFG.peaceTreatyData.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).iWillBecomeVassalOfCivID,
               CFG.peaceTreatyData.peaceTreatyGameData.lCivsData_Aggressors.get(i).iCivID,
               0,
               var13 = tY + menuElements.get(menuElements.size() - 1).getHeight(),
               tempW,
               CFG.TEXT_HEIGHT + CFG.PADDING * 5,
               true
            )
         );
         menuElements.add(
            new Button_PeaceTreaty_Demands_WarReparations(
               CFG.peaceTreatyData.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).iPaysWarReparationsToCivID,
               CFG.peaceTreatyData.peaceTreatyGameData.lCivsData_Aggressors.get(i).iCivID,
               0,
               tY = var13 + menuElements.get(menuElements.size() - 1).getHeight(),
               tempW,
               CFG.TEXT_HEIGHT + CFG.PADDING * 5,
               true
            )
         );
         tY += menuElements.get(menuElements.size() - 1).getHeight();

         for (int j = 0; j < CFG.peaceTreatyData.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).lReleasableCivs.size(); j++) {
            menuElements.add(
               new Button_PeaceTreaty_Demands_ReleaseVassal(
                  CFG.peaceTreatyData.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).lReleasableCivs.get(j).iReleasesToCivID,
                  CFG.peaceTreatyData.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).lReleasableCivs.get(j).iCivID,
                  CFG.peaceTreatyData.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).iCivID,
                  CFG.peaceTreatyData.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).lReleasableCivs.get(j).getScoreValue(),
                  0,
                  tY,
                  tempW,
                  CFG.TEXT_HEIGHT + CFG.PADDING * 5,
                  true
               )
            );
            tY += menuElements.get(menuElements.size() - 1).getHeight();
         }

         if (CFG.peaceTreatyData.peaceTreatyGameData.lCivsData_Aggressors.get(i).showProvinces) {
            for (int var7 = 0; var7 < CFG.peaceTreatyData.peaceTreatyGameData.lCivsData_Aggressors.get(i).lProvincesLost.size(); var7++) {
               menuElements.add(
                  new Button_PeaceTreaty_Demands_Province(
                     CFG.peaceTreatyData.peaceTreatyGameData.lCivsData_Aggressors.get(i).lProvincesLost.get(var7),
                     0,
                     tY,
                     tempW - CFG.BUTTON_HEIGHT,
                     CFG.TEXT_HEIGHT + CFG.PADDING * 4,
                     true
                  )
               );
               menuElements.add(
                  new Button_PeaceTreaty_Demands_Localize(
                     CFG.peaceTreatyData.peaceTreatyGameData.lCivsData_Aggressors.get(i).lProvincesLost.get(var7),
                     tempW - CFG.BUTTON_HEIGHT,
                     tY,
                     CFG.BUTTON_HEIGHT,
                     CFG.TEXT_HEIGHT + CFG.PADDING * 4,
                     true
                  )
               );
               tY += menuElements.get(menuElements.size() - 1).getHeight();
            }
         }
      }

      for (int var10 = 0; var10 < CFG.peaceTreatyData.peaceTreatyGameData.lCivsData_Defenders.size(); var10++) {
         int var17 = tempW - CFG.BUTTON_WIDTH;
         int var18 = CFG.TEXT_HEIGHT + CFG.PADDING * 4;
         menuElements.add(
            new Button_PeaceTreaty_Demands_TakeAll(CFG.peaceTreatyData.peaceTreatyGameData.lCivsData_Defenders.get(var10).iCivID, 0, tY, var17, var18, true)
         );
         menuElements.add(
            new Button_PeaceTreaty_Demands_TakeAll_VicPoints(
               CFG.peaceTreatyData.peaceTreatyGameData.lCivsData_Defenders.get(var10).iCivID,
               CFG.peaceTreatyData.peaceTreatyGameData.lCivsData_Defenders.get(var10).getVictoryPointsTotal(),
               tempW - CFG.BUTTON_WIDTH,
               tY,
               CFG.BUTTON_WIDTH,
               CFG.TEXT_HEIGHT + CFG.PADDING * 4,
               true
            )
         );
         int var15;
         menuElements.add(
            new Button_PeaceTreaty_Demands_Vassalize(
               CFG.peaceTreatyData.peaceTreatyGameData.lCivsDemands_Defenders.get(var10).iWillBecomeVassalOfCivID,
               CFG.peaceTreatyData.peaceTreatyGameData.lCivsData_Defenders.get(var10).iCivID,
               0,
               var15 = tY + menuElements.get(menuElements.size() - 1).getHeight(),
               tempW,
               CFG.TEXT_HEIGHT + CFG.PADDING * 5,
               true
            )
         );
         menuElements.add(
            new Button_PeaceTreaty_Demands_WarReparations(
               CFG.peaceTreatyData.peaceTreatyGameData.lCivsDemands_Defenders.get(var10).iPaysWarReparationsToCivID,
               CFG.peaceTreatyData.peaceTreatyGameData.lCivsData_Defenders.get(var10).iCivID,
               0,
               tY = var15 + menuElements.get(menuElements.size() - 1).getHeight(),
               tempW,
               CFG.TEXT_HEIGHT + CFG.PADDING * 5,
               true
            )
         );
         tY += menuElements.get(menuElements.size() - 1).getHeight();

         for (int j = 0; j < CFG.peaceTreatyData.peaceTreatyGameData.lCivsDemands_Defenders.get(var10).lReleasableCivs.size(); j++) {
            menuElements.add(
               new Button_PeaceTreaty_Demands_ReleaseVassal(
                  CFG.peaceTreatyData.peaceTreatyGameData.lCivsDemands_Defenders.get(var10).lReleasableCivs.get(j).iReleasesToCivID,
                  CFG.peaceTreatyData.peaceTreatyGameData.lCivsDemands_Defenders.get(var10).lReleasableCivs.get(j).iCivID,
                  CFG.peaceTreatyData.peaceTreatyGameData.lCivsDemands_Defenders.get(var10).iCivID,
                  CFG.peaceTreatyData.peaceTreatyGameData.lCivsDemands_Defenders.get(var10).lReleasableCivs.get(j).getScoreValue(),
                  0,
                  tY,
                  tempW,
                  CFG.TEXT_HEIGHT + CFG.PADDING * 5,
                  true
               )
            );
            tY += menuElements.get(menuElements.size() - 1).getHeight();
         }

         if (CFG.peaceTreatyData.peaceTreatyGameData.lCivsData_Defenders.get(var10).showProvinces) {
            for (int var9 = 0; var9 < CFG.peaceTreatyData.peaceTreatyGameData.lCivsData_Defenders.get(var10).lProvincesLost.size(); var9++) {
               menuElements.add(
                  new Button_PeaceTreaty_Demands_Province(
                     CFG.peaceTreatyData.peaceTreatyGameData.lCivsData_Defenders.get(var10).lProvincesLost.get(var9),
                     0,
                     tY,
                     tempW - CFG.BUTTON_HEIGHT,
                     CFG.TEXT_HEIGHT + CFG.PADDING * 4,
                     true
                  )
               );
               menuElements.add(
                  new Button_PeaceTreaty_Demands_Localize(
                     CFG.peaceTreatyData.peaceTreatyGameData.lCivsData_Defenders.get(var10).lProvincesLost.get(var9),
                     tempW - CFG.BUTTON_HEIGHT,
                     tY,
                     CFG.BUTTON_HEIGHT,
                     CFG.TEXT_HEIGHT + CFG.PADDING * 4,
                     true
                  )
               );
               tY += menuElements.get(menuElements.size() - 1).getHeight();
            }
         }
      }

      for (int var11 = 0; var11 < menuElements.size(); var11++) {
         menuElements.get(var11).setCurrent(var11 % 4 / 2);
      }

      int tempPosY = Math.max(
         Math.max(CFG.BUTTON_HEIGHT * 4 / 5, Math.max(CFG.CIV_FLAG_HEIGHT + CFG.PADDING * 4, (CFG.TEXT_HEIGHT + CFG.PADDING) * 2 + CFG.PADDING))
            + CFG.PADDING * 2,
         CFG.BUTTON_HEIGHT + CFG.PADDING
      );
      this.initMenu(
         new SliderMenuTitle(null, CFG.BUTTON_HEIGHT * 3 / 4, false, false) {
            @Override
            public void draw(SpriteBatch oSB, int iTranslateX, int nPosX, int nPosY, int nWidth, boolean sliderMenuIsActive) {
               ImageManager.getImage(Images.dialog_title)
                  .draw2(
                     oSB,
                     nPosX - 2 + iTranslateX,
                     nPosY - this.getHeight() - ImageManager.getImage(Images.dialog_title).getHeight(),
                     nWidth + 2,
                     this.getHeight()
                  );
               oSB.setColor(new Color(0.23529412F, 0.3137255F, 0.4117647F, 0.165F));
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
               oSB.setColor(new Color(0.23529412F, 0.3137255F, 0.4117647F, 0.375F));
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
         CFG.GAME_WIDTH - tempW,
         tempPosY + CFG.BUTTON_HEIGHT * 3 / 4,
         tempW,
         Math.min(
            menuElements.size() > 0
               ? menuElements.get(menuElements.size() - 1).getPosY() + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING
               : CFG.PADDING,
            CFG.GAME_HEIGHT - (tempPosY + CFG.BUTTON_HEIGHT * 3 / 4) - CFG.BUTTON_HEIGHT
         ),
         menuElements,
         true,
         true
      );
      this.updateLanguage();
      this.getMenuElement(0).setCurrent(CFG.peaceTreatyData.peaceTreatyGameData.TRUCE_LENGTH);
   }

   @Override
   public void updateLanguage() {
      this.getTitle().setText(CFG.langManager.get("MakeDemands"));
   }

   @Override
   public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      if (Menu_Civilization_Info.lTime + 250L >= System.currentTimeMillis()) {
         iTranslateX += this.getWidth() - (int)(this.getWidth() * ((float)(System.currentTimeMillis() - Menu_Civilization_Info.lTime) / 250.0F));
         CFG.setRender_3(true);
      }

      ImageManager.getImage(Images.new_game_top_edge_line)
         .draw2(
            oSB,
            this.getPosX() - 2 + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.new_game_top_edge_line).getHeight() + iTranslateY,
            this.getWidth() + 4,
            this.getHeight(),
            false,
            true
         );
      super.draw(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
      oSB.setColor(CFG.COLOR_CREATE_NEW_GAME_BOX_PLAYERS);
      ImageManager.getImage(Images.pix255_255_255)
         .draw(
            oSB,
            this.getPosX() - 2 + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + this.getHeight(),
            this.getWidth() + 2
         );
      oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.4F));
      ImageManager.getImage(Images.line_32_off1)
         .draw(
            oSB,
            this.getPosX() - 2 + iTranslateX,
            this.getPosY()
               - ImageManager.getImage(Images.pix255_255_255).getHeight()
               - ImageManager.getImage(Images.line_32_off1).getHeight()
               + this.getHeight(),
            this.getWidth() + 2,
            1
         );
      oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.5F));
      ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() - 2 + iTranslateX, this.getPosY() + this.getHeight(), this.getWidth() + 2);
      oSB.setColor(Color.WHITE);
   }

   @Override
   public void drawScrollPos(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      if (sliderMenuIsActive) {
         super.drawScrollPos(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
      }
   }

   @Override
   public void actionElement(int iID) {
      this.getMenuElement(iID).actionElement(iID);
   }

   @Override
   public void actionClose() {
      this.setVisible(false);
      CFG.menuManager.hidePeaceTreatyProvinces();
   }

   @Override
   public void setVisible(boolean visible) {
      if (visible && !this.getVisible()) {
         Menu_Civilization_Info.lTime = System.currentTimeMillis();
      }

      super.setVisible(visible);
   }
}
