package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Slider_LR extends Slider {
   public Slider_LR(int iPosX, int iPosY, int iWidth, int iHeight, int iMin, int iMax, int iCurrent) {
      super.initSlider("", iPosX, iPosY, iWidth, iHeight, iMin, iMax, iCurrent);
   }

   public Slider_LR(String sText, int iPosX, int iPosY, int iWidth, int iHeight, int iMin, int iMax, int iCurrent) {
      super.initSlider(sText, iPosX, iPosY, iWidth, iHeight, iMin, iMax, iCurrent);
   }

   @Override
   public void drawSliderText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
      CFG.drawText(
         oSB,
         this.getDrawText(),
         this.getPosX() + CFG.PADDING * 2 + iTranslateX,
         this.getPosY() + this.getHeight() / 2 - this.getTextHeight() / 2 + iTranslateY,
         new Color(0.945F, 0.945F, 0.945F, 1.0F)
      );
      CFG.drawText(
         oSB,
         this.getDrawText(),
         this.getPosX() + this.getWidth() - CFG.PADDING * 2 - this.getTextWidth() + iTranslateX,
         this.getPosY() + this.getHeight() / 2 - this.getTextHeight() / 2 + iTranslateY,
         new Color(this.getColorLEFT().r * 1.85F, this.getColorLEFT().g * 1.85F, this.getColorLEFT().b * 2.4F, 1.0F)
      );
   }
}
