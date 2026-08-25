package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MenuElement_Hover_v2_Element_Type_Image implements MenuElement_Hover_v2_Element_Type {
   public int iImageID;
   public int offsetLeft;
   public int offsetRight;

   public MenuElement_Hover_v2_Element_Type_Image(int iImageID) {
      this.iImageID = iImageID;
      this.offsetLeft = 0;
      this.offsetRight = CFG.PADDING;
   }

   public MenuElement_Hover_v2_Element_Type_Image(int iImageID, int offsetLeft) {
      this.iImageID = iImageID;
      this.offsetLeft = offsetLeft;
      this.offsetRight = CFG.PADDING;
   }

   public MenuElement_Hover_v2_Element_Type_Image(int iImageID, int offsetLeft, int offsetRight) {
      this.iImageID = iImageID;
      this.offsetLeft = offsetLeft;
      this.offsetRight = offsetRight;
   }

   @Override
   public void draw(SpriteBatch oSB, int nPosX, int nPosY, float nAlpha) {
      oSB.setColor(new Color(1.0F, 1.0F, 1.0F, nAlpha));
      ImageManager.getImage(this.iImageID)
         .draw(
            oSB,
            nPosX + this.offsetLeft,
            nPosY
               + CFG.PADDING
               - ImageManager.getImage(this.iImageID).getHeight()
               + CFG.TEXT_HEIGHT / 2
               - (int)(ImageManager.getImage(this.iImageID).getHeight() * this.getImageScale() / 2.0F),
            (int)(ImageManager.getImage(this.iImageID).getWidth() * this.getImageScale()),
            (int)(ImageManager.getImage(this.iImageID).getHeight() * this.getImageScale())
         );
   }

   @Override
   public int getWidth() {
      return this.offsetLeft + this.offsetRight + (int)(ImageManager.getImage(this.iImageID).getWidth() * this.getImageScale());
   }

   public final float getImageScale() {
      return CFG.TEXT_HEIGHT * 0.75F / ImageManager.getImage(this.iImageID).getHeight();
   }
}
