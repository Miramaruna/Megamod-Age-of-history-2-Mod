package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Text_LeftSide_Stability extends Text_LeftSide {
   public int iCurrent = 0;

   public Text_LeftSide_Stability(String sText, int iPosX, int iPosY) {
      super(sText, iPosX, iPosY);
   }

   @Override
   public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
      ImageManager.getImage(Images.diplo_popstability)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY()
               + (
                     this.getHeight()
                        - (int)(
                           ImageManager.getImage(Images.diplo_popstability).getHeight()
                              * this.getImageScale(ImageManager.getImage(Images.diplo_popstability).getHeight())
                        )
                  )
                  / 2
               - ImageManager.getImage(Images.diplo_popstability).getHeight()
               + iTranslateY,
            (int)(
               ImageManager.getImage(Images.diplo_popstability).getWidth() * this.getImageScale(ImageManager.getImage(Images.diplo_popstability).getHeight())
            ),
            (int)(
               ImageManager.getImage(Images.diplo_popstability).getHeight() * this.getImageScale(ImageManager.getImage(Images.diplo_popstability).getHeight())
            )
         );
      CFG.fontMain.getData().setScale(0.7F);
      CFG.drawTextWithShadow(
         oSB,
         this.sText,
         this.getPosX()
            + (int)(
               ImageManager.getImage(Images.diplo_popstability).getWidth() * this.getImageScale(ImageManager.getImage(Images.diplo_popstability).getHeight())
            )
            + CFG.PADDING
            + iTranslateX,
         this.getPosY() + (int)(this.getHeight() - this.iTextHeight * 0.7F) / 2 + iTranslateY,
         this.getColor(isActive)
      );
      CFG.fontMain.getData().setScale(1.0F);
   }

   @Override
   public int getPosX() {
      return super.getPosX()
         - (int)(ImageManager.getImage(Images.diplo_popstability).getWidth() * this.getImageScale(ImageManager.getImage(Images.diplo_popstability).getHeight()))
         - CFG.PADDING;
   }

   @Override
   public int getWidth() {
      return (int)(this.getTextWidth() * 0.7F)
         + (int)(ImageManager.getImage(Images.diplo_popstability).getWidth() * this.getImageScale(ImageManager.getImage(Images.diplo_popstability).getHeight()))
         + CFG.PADDING;
   }

   public final float getImageScale(int nImageHeight) {
      return (float)this.getHeight() / nImageHeight < 1.0F ? (float)this.getHeight() / nImageHeight : 1.0F;
   }

   @Override
   public Color getColor(boolean isActive) {
      return isActive
         ? CFG.getColorStep(CFG.COLOR_TEXT_PROVINCE_STABILITY_MIN, CFG.COLOR_TEXT_PROVINCE_STABILITY_MAX, this.getCurrent(), 100, 1.0F)
         : (
            this.getClickable()
               ? (
                  this.getIsHovered()
                     ? CFG.getColorStep(CFG.COLOR_TEXT_PROVINCE_STABILITY_MIN, CFG.COLOR_TEXT_PROVINCE_STABILITY_MAX, this.getCurrent(), 100, 1.0F)
                     : CFG.getColorStep(CFG.COLOR_TEXT_PROVINCE_STABILITY_MIN, CFG.COLOR_TEXT_PROVINCE_STABILITY_MAX, this.getCurrent(), 100, 1.0F)
               )
               : new Color(0.78F, 0.78F, 0.78F, 0.7F)
         );
   }

   @Override
   public int getCurrent() {
      return this.iCurrent;
   }

   @Override
   public void setCurrent(int nCurrent) {
      this.iCurrent = nCurrent;
      this.setText("" + this.iCurrent + "%");
   }
}
