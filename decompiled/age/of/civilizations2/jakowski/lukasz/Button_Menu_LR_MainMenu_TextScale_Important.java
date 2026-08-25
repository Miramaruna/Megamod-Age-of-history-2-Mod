package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Button_Menu_LR_MainMenu_TextScale_Important extends Button_Menu_LR_MainMenu {
   public Button_Menu_LR_MainMenu_TextScale_Important(String sText, int iTextPositionX, int iPosX, int iPosY, int iWidth, int iHeight, boolean isClickable) {
      super(sText, iTextPositionX, iPosX, iPosY, iWidth, iHeight, isClickable);
   }

   public Button_Menu_LR_MainMenu_TextScale_Important(
      String sText, int iTextPositionX, int iPosX, int iPosY, int iWidth, int iHeight, boolean isClickable, boolean checkboxState
   ) {
      super(sText, iTextPositionX, iPosX, iPosY, iWidth, iHeight, isClickable, checkboxState);
   }

   @Override
   public void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
      CFG.fontMain.getData().setScale(0.9F);
      if (isActive) {
         CFG.drawText(
            oSB,
            this.getTextToDraw(),
            this.getPosX() + this.getWidth() / 2 - (int)(this.getTextWidth() * 0.9F / 2.0F) + iTranslateX,
            this.getPosY() + this.getHeight() / 2 - (int)(this.iTextHeight * 0.9F / 2.0F) + iTranslateY,
            this.getColor(isActive)
         );
      } else {
         CFG.drawTextWithShadow(
            oSB,
            this.getTextToDraw(),
            this.getPosX() + this.getWidth() / 2 - (int)(this.getTextWidth() * 0.9F / 2.0F) + iTranslateX,
            this.getPosY() + this.getHeight() / 2 - (int)(this.iTextHeight * 0.9F / 2.0F) + iTranslateY,
            this.getColor(isActive)
         );
      }

      CFG.fontMain.getData().setScale(1.0F);
      oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, isActive ? 0.0F : (this.getIsHovered() ? 0.3F : 0.25F)));
      ImageManager.getImage(Images.slider_gradient)
         .draw(
            oSB,
            this.getPosX() + CFG.PADDING * 2 + iTranslateX,
            this.getPosY() + this.getHeight() / 2 - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY,
            (this.getWidth() - CFG.PADDING * 8) / 2 - (int)(this.getTextWidth() * 0.9F) / 2,
            1,
            true,
            false
         );
      ImageManager.getImage(Images.slider_gradient)
         .draw(
            oSB,
            this.getPosX() + this.getWidth() / 2 + (int)(this.getTextWidth() * 0.9F) / 2 + CFG.PADDING * 2 + iTranslateX,
            this.getPosY() + this.getHeight() / 2 - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY,
            (this.getWidth() - CFG.PADDING * 8) / 2 - (int)(this.getTextWidth() * 0.9F) / 2,
            1,
            false,
            false
         );
      oSB.setColor(new Color(0.0F, 0.0F, 0.0F, isActive ? 0.0F : (this.getIsHovered() ? 0.3F : 0.2F)));
      ImageManager.getImage(Images.slider_gradient)
         .draw(
            oSB,
            this.getPosX() + CFG.PADDING * 2 + iTranslateX,
            this.getPosY() - 1 + this.getHeight() / 2 - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY,
            (this.getWidth() - CFG.PADDING * 8) / 2 - (int)(this.getTextWidth() * 0.9F) / 2,
            1,
            true,
            false
         );
      ImageManager.getImage(Images.slider_gradient)
         .draw(
            oSB,
            this.getPosX() + this.getWidth() / 2 + (int)(this.getTextWidth() * 0.9F) / 2 + CFG.PADDING * 2 + iTranslateX,
            this.getPosY() - 1 + this.getHeight() / 2 - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY,
            (this.getWidth() - CFG.PADDING * 8) / 2 - (int)(this.getTextWidth() * 0.9F) / 2,
            1,
            false,
            false
         );
      ImageManager.getImage(Images.slider_gradient)
         .draw(
            oSB,
            this.getPosX() + CFG.PADDING * 2 + iTranslateX,
            this.getPosY() + 1 + this.getHeight() / 2 - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY,
            (this.getWidth() - CFG.PADDING * 8) / 2 - (int)(this.getTextWidth() * 0.9F) / 2,
            1,
            true,
            false
         );
      ImageManager.getImage(Images.slider_gradient)
         .draw(
            oSB,
            this.getPosX() + this.getWidth() / 2 + (int)(this.getTextWidth() * 0.9F) / 2 + CFG.PADDING * 2 + iTranslateX,
            this.getPosY() + 1 + this.getHeight() / 2 - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY,
            (this.getWidth() - CFG.PADDING * 8) / 2 - (int)(this.getTextWidth() * 0.9F) / 2,
            1,
            false,
            false
         );
      oSB.setColor(Color.WHITE);
   }
}
