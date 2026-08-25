package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Button_BotBar extends Button {
   public float FONT_SCALE = 0.8F;
   public int iMinWidth = 0;

   public Button_BotBar(String sText, float FONT_SCALE, int iPosX, int iPosY, int iMinWidth, boolean isClickable, boolean isVisible) {
      super.init(sText, CFG.PADDING, iPosX, iPosY, iMinWidth, ImageManager.getImage(Images.bot_left).getHeight(), isClickable, isVisible, false, false, null);
      this.FONT_SCALE = FONT_SCALE;
      this.iMinWidth = iMinWidth;
   }

   @Override
   public void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
      ImageManager.getImage(Images.bot_left)
         .draw2(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.bot_left).getHeight() + iTranslateY,
            this.getWidth() + ImageManager.getImage(Images.bot_left).getWidth() / 2,
            this.getHeight(),
            true,
            true
         );
   }

   @Override
   public void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
      CFG.fontMain.getData().setScale(this.FONT_SCALE);
      CFG.drawTextWithShadow(
         oSB,
         this.getTextToDraw(),
         this.getPosX() + this.getTextPos() + iTranslateX,
         this.getPosY() + this.getHeight() / 2 - this.iTextHeight / 2 + iTranslateY,
         this.getColor(isActive)
      );
      CFG.fontMain.getData().setScale(1.0F);
   }

   @Override
   public Color getColor(boolean isActive) {
      return isActive
         ? new Color(0.56F, 0.56F, 0.56F, 1.0F)
         : (
            this.getClickable()
               ? (this.getIsHovered() ? new Color(0.68F, 0.68F, 0.68F, 1.0F) : new Color(0.82F, 0.82F, 0.82F, 1.0F))
               : new Color(0.78F, 0.78F, 0.78F, 0.7F)
         );
   }

   @Override
   public void setText(String sText) {
      this.sText = sText;
      this.setWidth(this.iMinWidth);

      try {
         CFG.glyphLayout.setText(CFG.fontMain, sText);
         this.iTextWidth = (int)(CFG.glyphLayout.width * this.FONT_SCALE);
         this.iTextHeight = (int)(CFG.glyphLayout.height * this.FONT_SCALE);
         if (super.getWidth() < this.iTextWidth + CFG.PADDING * 2) {
            this.setWidth(this.iTextWidth + CFG.PADDING * 2);
         }
      } catch (NullPointerException var3) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var3);
         }
      }
   }
}
