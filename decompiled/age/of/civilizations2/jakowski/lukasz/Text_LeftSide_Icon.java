package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Text_LeftSide_Icon extends Text_LeftSide {
   public int iImageID;

   public Text_LeftSide_Icon(String sText, int iPosX, int iPosY, int iImageID) {
      super(sText, iPosX, iPosY);
      this.iImageID = iImageID;
   }

   @Override
   public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
      ImageManager.getImage(this.iImageID)
         .draw(
            oSB,
            this.getPosX()
               - CFG.PADDING
               - (int)(ImageManager.getImage(this.iImageID).getWidth() * this.getImageScale(ImageManager.getImage(this.iImageID).getHeight()))
               + iTranslateX,
            this.getPosY()
               + (
                     this.getHeight()
                        - (int)(ImageManager.getImage(this.iImageID).getHeight() * this.getImageScale(ImageManager.getImage(this.iImageID).getHeight()))
                  )
                  / 2
               - ImageManager.getImage(this.iImageID).getHeight()
               + iTranslateY,
            (int)(ImageManager.getImage(this.iImageID).getWidth() * this.getImageScale(ImageManager.getImage(this.iImageID).getHeight())),
            (int)(ImageManager.getImage(this.iImageID).getHeight() * this.getImageScale(ImageManager.getImage(this.iImageID).getHeight()))
         );
      super.draw(oSB, iTranslateX, iTranslateY, isActive, scrollableY);
   }

   public final float getImageScale(int nImageHeight) {
      return (float)this.getHeight() / nImageHeight < 1.0F ? (float)this.getHeight() / nImageHeight : 1.0F;
   }
}
