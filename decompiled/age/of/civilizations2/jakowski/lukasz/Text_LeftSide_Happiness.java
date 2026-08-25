package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Text_LeftSide_Happiness extends Text_LeftSide {
   public int iCurrent = 0;

   public Text_LeftSide_Happiness(String sText, int iPosX, int iPosY) {
      super(sText, iPosX, iPosY);
   }

   @Override
   public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
      ImageManager.getImage(CFG.getHappinesImage(this.getCurrent()))
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY()
               + (
                     this.getHeight()
                        - (int)(ImageManager.getImage(Images.happiness).getHeight() * this.getImageScale(ImageManager.getImage(Images.happiness).getHeight()))
                  )
                  / 2
               - ImageManager.getImage(Images.happiness).getHeight()
               + iTranslateY,
            (int)(ImageManager.getImage(Images.happiness).getWidth() * this.getImageScale(ImageManager.getImage(Images.happiness).getHeight())),
            (int)(ImageManager.getImage(Images.happiness).getHeight() * this.getImageScale(ImageManager.getImage(Images.happiness).getHeight()))
         );
      CFG.fontMain.getData().setScale(0.7F);
      CFG.drawTextWithShadow(
         oSB,
         this.sText,
         this.getPosX()
            + (int)(ImageManager.getImage(Images.happiness).getWidth() * this.getImageScale(ImageManager.getImage(Images.happiness).getHeight()))
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
         - (int)(ImageManager.getImage(Images.happiness).getWidth() * this.getImageScale(ImageManager.getImage(Images.happiness).getHeight()))
         - CFG.PADDING;
   }

   @Override
   public int getWidth() {
      return (int)(this.getTextWidth() * 0.7F)
         + (int)(ImageManager.getImage(Images.happiness).getWidth() * this.getImageScale(ImageManager.getImage(Images.happiness).getHeight()))
         + CFG.PADDING;
   }

   public final float getImageScale(int nImageHeight) {
      return (float)this.getHeight() / nImageHeight < 1.0F ? (float)this.getHeight() / nImageHeight : 1.0F;
   }

   @Override
   public Color getColor(boolean isActive) {
      return isActive
         ? CFG.COLOR_TEXT_HAPPINESS_ACTIVE
         : (
            this.getClickable()
               ? (
                  this.getIsHovered()
                     ? CFG.COLOR_TEXT_HAPPINESS_HOVER
                     : CFG.getColorStep(CFG.COLOR_TEXT_HAPPINESS_MIN, CFG.COLOR_TEXT_HAPPINESS_MAX, this.getCurrent(), 100, 1.0F)
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
