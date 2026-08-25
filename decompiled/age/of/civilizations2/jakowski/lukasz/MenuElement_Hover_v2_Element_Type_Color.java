package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MenuElement_Hover_v2_Element_Type_Color implements MenuElement_Hover_v2_Element_Type {
   public Color oColor;
   public int offsetLeft = 0;
   public int offsetRight = 0;

   public MenuElement_Hover_v2_Element_Type_Color(Color oColor) {
      this.oColor = oColor;
      this.offsetLeft = 0;
      this.offsetRight = 0;
   }

   public MenuElement_Hover_v2_Element_Type_Color(Color oColor, int offsetLeft) {
      this.oColor = oColor;
      this.offsetLeft = offsetLeft;
      this.offsetRight = CFG.PADDING;
   }

   public MenuElement_Hover_v2_Element_Type_Color(Color oColor, int offsetLeft, int offsetRight) {
      this.oColor = oColor;
      this.offsetLeft = offsetLeft;
      this.offsetRight = offsetRight;
   }

   @Override
   public void draw(SpriteBatch oSB, int nPosX, int nPosY, float nAlpha) {
      oSB.setColor(new Color(this.oColor.r, this.oColor.g, this.oColor.b, nAlpha));
      ImageManager.getImage(Images.pix255_255_255)
         .draw(
            oSB,
            nPosX + this.offsetLeft,
            nPosY
               + CFG.PADDING
               - ImageManager.getImage(Images.pix255_255_255).getHeight()
               + CFG.TEXT_HEIGHT / 2
               - (int)(CFG.CIV_FLAG_HEIGHT * this.getImageScale() / 2.0F),
            2,
            (int)(CFG.CIV_FLAG_HEIGHT * this.getImageScale())
         );
      oSB.setColor(Color.WHITE);
   }

   @Override
   public int getWidth() {
      return this.offsetRight + this.offsetLeft + 2;
   }

   public final float getImageScale() {
      return (float)CFG.TEXT_HEIGHT / CFG.CIV_FLAG_HEIGHT;
   }
}
