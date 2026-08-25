package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Slider_BG_ZeroInMiddle extends Slider {
   public Slider_BG_ZeroInMiddle(int iPosX, int iPosY, int iWidth, int iHeight, int iMin, int iMax, int iCurrent) {
      super.initSlider("", iPosX, iPosY, iWidth, iHeight, iMin, iMax, iCurrent);
   }

   @Override
   public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
      ImageManager.getImage(Images.btn_menu_1_h).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - CFG.PADDING + iTranslateY, this.getWidth());
      if (this.getCurrent() == 0) {
         oSB.setColor(0.97F, 0.97F, 0.97F, this.getClickable() ? 0.68F : 0.5F);
         ImageManager.getImage(Images.pix255_255_255)
            .draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - 1 + iTranslateY, this.getWidth(), this.getHeight());
      } else if (this.getCurrent() > 0) {
         oSB.setColor(0.97F, 0.97F, 0.97F, 0.68F);
         ImageManager.getImage(Images.pix255_255_255)
            .draw(
               oSB, this.getPosX() + this.iCurrentPosX + iTranslateX, this.getPosY() - 1 + iTranslateY, this.getWidth() - this.iCurrentPosX, this.getHeight()
            );
         oSB.setColor(new Color(this.getColorRIGHT().r, this.getColorRIGHT().g, this.getColorRIGHT().b, 0.68F));
         ImageManager.getImage(Images.pix255_255_255)
            .draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - 1 + iTranslateY, this.iCurrentPosX, this.getHeight());
         ImageManager.getImage(Images.slider_gradient)
            .draw(
               oSB,
               this.getPosX() + iTranslateX,
               this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY,
               this.iCurrentPosX,
               this.getHeight()
            );
      } else {
         oSB.setColor(0.97F, 0.97F, 0.97F, 0.68F);
         ImageManager.getImage(Images.pix255_255_255)
            .draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - 1 + iTranslateY, this.iCurrentPosX, this.getHeight());
         oSB.setColor(new Color(this.getColorLEFT().r, this.getColorLEFT().g, this.getColorLEFT().b, 0.68F));
         ImageManager.getImage(Images.pix255_255_255)
            .draw(
               oSB, this.getPosX() + this.iCurrentPosX + iTranslateX, this.getPosY() - 1 + iTranslateY, this.getWidth() - this.iCurrentPosX, this.getHeight()
            );
         ImageManager.getImage(Images.slider_gradient)
            .draw(
               oSB,
               this.getPosX() + this.iCurrentPosX + iTranslateX,
               this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY,
               this.getWidth() - this.iCurrentPosX,
               this.getHeight(),
               true,
               false
            );
      }

      this.drawSliderText(oSB, iTranslateX, iTranslateY, isActive, scrollableY);
      oSB.setColor(new Color(CFG.COLOR_SLIDER_BORDER.r, CFG.COLOR_SLIDER_BORDER.g, CFG.COLOR_SLIDER_BORDER.b, 0.68F));
      ImageManager.getImage(Images.pix255_255_255)
         .draw(oSB, this.getPosX() + this.iCurrentPosX - 1 + iTranslateX, this.getPosY() - 1 + iTranslateY, 1, this.getHeight());
      oSB.setColor(Color.WHITE);
      this.drawSliderBorder(oSB, iTranslateX, iTranslateY, isActive, scrollableY);
   }

   @Override
   public Color getColorLEFT() {
      return CFG.COLOR_TEXT_MODIFIER_NEGATIVE;
   }

   @Override
   public Color getColorRIGHT() {
      return CFG.COLOR_TEXT_MODIFIER_POSITIVE;
   }
}
