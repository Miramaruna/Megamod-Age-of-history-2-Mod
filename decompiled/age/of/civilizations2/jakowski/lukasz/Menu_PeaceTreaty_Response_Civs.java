package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

class Menu_PeaceTreaty_Response_Civs extends SliderMenu {
   protected final float FONT_SCALE = 0.8F;

   protected Menu_PeaceTreaty_Response_Civs() {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      int tMenuWidth = CFG.CIV_INFO_MENU_WIDTH * 2 / 5;
      int tElementH = Math.max(CFG.TEXT_HEIGHT + CFG.PADDING * 4, CFG.CIV_FLAG_HEIGHT + CFG.PADDING * 4);
      int tPosY = 0;

      for (int i = 0; i < CFG.peaceTreatyData.peaceTreatyGameData.lCivsDemands_Defenders.size(); i++) {
         menuElements.add(
            new Text_PeaceTreaty_Result(
               CFG.peaceTreatyData.peaceTreatyGameData.lCivsDemands_Defenders.get(i).iCivID,
               CFG.peaceTreatyData.peaceTreatyGameData.lCivsDemands_Defenders.get(i).peaceTreatyAccepted,
               0,
               tPosY,
               tMenuWidth - 2
            )
         );
         tPosY += menuElements.get(menuElements.size() - 1).getHeight();
      }

      for (int var8 = 0; var8 < CFG.peaceTreatyData.peaceTreatyGameData.lCivsDemands_Aggressors.size(); var8++) {
         menuElements.add(
            new Text_PeaceTreaty_Result(
               CFG.peaceTreatyData.peaceTreatyGameData.lCivsDemands_Aggressors.get(var8).iCivID,
               CFG.peaceTreatyData.peaceTreatyGameData.lCivsDemands_Aggressors.get(var8).peaceTreatyAccepted,
               0,
               tPosY,
               tMenuWidth - 2
            )
         );
         tPosY += menuElements.get(menuElements.size() - 1).getHeight();
      }

      int tempPosY = Math.max(
         Math.max(CFG.BUTTON_HEIGHT * 4 / 5, Math.max(CFG.CIV_FLAG_HEIGHT + CFG.PADDING * 4, (CFG.TEXT_HEIGHT + CFG.PADDING) * 2 + CFG.PADDING))
            + CFG.PADDING * 2,
         CFG.BUTTON_HEIGHT + CFG.PADDING
      );
      this.initMenu(
         new SliderMenuTitle(null, CFG.BUTTON_HEIGHT / 2, false, false) {
            @Override
            public void draw(SpriteBatch oSB, int iTranslateX, int nPosX, int nPosY, int nWidth, boolean sliderMenuIsActive) {
               oSB.setColor(new Color(CFG.COLOR_GRADIENT_TITLE_BLUE.r, CFG.COLOR_GRADIENT_TITLE_BLUE.g, CFG.COLOR_GRADIENT_TITLE_BLUE.b, 1.0F));
               ImageManager.getImage(Images.slider_gradient)
                  .draw(
                     oSB,
                     nPosX + iTranslateX,
                     nPosY - this.getHeight() - ImageManager.getImage(Images.slider_gradient).getHeight(),
                     nWidth,
                     this.getHeight(),
                     false,
                     false
                  );
               oSB.setColor(new Color(CFG.COLOR_GRADIENT_TITLE_BLUE.r, CFG.COLOR_GRADIENT_TITLE_BLUE.g, CFG.COLOR_GRADIENT_TITLE_BLUE.b, 0.4F));
               ImageManager.getImage(Images.slider_gradient)
                  .draw(
                     oSB,
                     nPosX + iTranslateX,
                     nPosY - this.getHeight() - ImageManager.getImage(Images.slider_gradient).getHeight(),
                     nWidth,
                     this.getHeight(),
                     false,
                     false
                  );
               oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.35F));
               ImageManager.getImage(Images.slider_gradient)
                  .draw(
                     oSB,
                     nPosX + iTranslateX,
                     nPosY - this.getHeight() - ImageManager.getImage(Images.slider_gradient).getHeight(),
                     nWidth / 2,
                     this.getHeight(),
                     false,
                     false
                  );
               oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.65F));
               ImageManager.getImage(Images.slider_gradient)
                  .draw(
                     oSB,
                     nPosX + iTranslateX,
                     nPosY + 1 - this.getHeight() - ImageManager.getImage(Images.slider_gradient).getHeight(),
                     nWidth,
                     1,
                     false,
                     false
                  );
               oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.7F));
               ImageManager.getImage(Images.slider_gradient)
                  .draw(
                     oSB,
                     nPosX + iTranslateX,
                     nPosY + 1 - this.getHeight() - ImageManager.getImage(Images.slider_gradient).getHeight(),
                     nWidth,
                     1,
                     false,
                     false
                  );
               oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.65F));
               ImageManager.getImage(Images.slider_gradient)
                  .draw(
                     oSB,
                     nPosX + iTranslateX,
                     nPosY + 2 - this.getHeight() - ImageManager.getImage(Images.slider_gradient).getHeight(),
                     nWidth,
                     1,
                     false,
                     false
                  );
               ImageManager.getImage(Images.slider_gradient)
                  .draw(oSB, nPosX + iTranslateX, nPosY - 1 - ImageManager.getImage(Images.slider_gradient).getHeight(), nWidth, 1, false, false);
               oSB.setColor(Color.WHITE);
               CFG.fontMain.getData().setScale(0.8F);
               CFG.drawText(
                  oSB,
                  this.getText(),
                  nPosX + nWidth / 2 - (int)(this.getTextWidth() * 0.8F) / 2 + iTranslateX,
                  2 + nPosY - this.getHeight() + (int)(this.getHeight() - this.getTextHeight() * 0.8F) / 2,
                  Color.WHITE
               );
               CFG.fontMain.getData().setScale(1.0F);
            }
         },
         0,
         tempPosY + CFG.BUTTON_HEIGHT / 2,
         tMenuWidth,
         Math.min(tElementH * 6, menuElements.get(menuElements.size() - 1).getPosY() + menuElements.get(menuElements.size() - 1).getHeight()),
         menuElements,
         true,
         false
      );

      for (int i2 = 0; i2 < this.getMenuElementsSize(); i2++) {
         this.getMenuElement(i2).setCurrent(i2 % 2);
      }

      this.updateLanguage();
   }

   @Override
   public void updateLanguage() {
      this.getTitle().setText(CFG.langManager.get("Civilizations"));
   }

   @Override
   public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      super.draw(oSB, iTranslateX, 1 + iTranslateY, sliderMenuIsActive);
      oSB.setColor(Color.WHITE);
   }

   @Override
   public void drawScrollPos(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      if (sliderMenuIsActive) {
         super.drawScrollPos(oSB, iTranslateX + CFG.PADDING, iTranslateY, sliderMenuIsActive);
      }
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

   @Override
   public void actionElement(int iID) {
      this.getMenuElement(iID).actionElement(iID);
   }
}
