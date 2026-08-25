package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Graph_Vertical_Data_Value {
   public int iValue;
   public int iHeight;
   public static final float ALPHA = 0.35F;
   public static final float ALPHA_GRADIENT = 0.7F;
   public static final float ALPHA_GRADIENT2 = 0.2F;
   public static final Color COLOR_VALUE_BORDER = new Color(0.9F, 0.9F, 0.9F, 0.1F);
   public int iColorDataID = 0;

   public Graph_Vertical_Data_Value(int iValue, int iColorDataID) {
      this.iValue = iValue;
      this.iColorDataID = iColorDataID;
   }

   public void draw(SpriteBatch oSB, int nPosX, int nPosY, int nWidth, int nHeight, Color nColor) {
      oSB.setColor(new Color(nColor.r, nColor.g, nColor.b, 0.35F));
      ImageManager.getImage(Images.pix255_255_255).draw(oSB, nPosX, nPosY - nHeight - this.iHeight, nWidth, this.iHeight);
      oSB.setColor(new Color(nColor.r, nColor.g, nColor.b, 0.7F));
      ImageManager.getImage(Images.gradient)
         .draw(oSB, nPosX, nPosY - nHeight - this.iHeight - ImageManager.getImage(Images.gradient).getHeight(), nWidth, this.iHeight, false, true);
   }

   public void draw(SpriteBatch oSB, int nPosX, int nPosY, int nWidth, int nHeight, int nAnimationHeight, Color nColor) {
      oSB.setColor(new Color(nColor.r, nColor.g, nColor.b, 0.35F));
      ImageManager.getImage(Images.pix255_255_255).draw(oSB, nPosX, nPosY - nHeight - nAnimationHeight, nWidth, nAnimationHeight);
      oSB.setColor(new Color(nColor.r, nColor.g, nColor.b, 0.7F));
      ImageManager.getImage(Images.gradient)
         .draw(oSB, nPosX, nPosY - nHeight - nAnimationHeight - ImageManager.getImage(Images.gradient).getHeight(), nWidth, nAnimationHeight, false, true);
   }

   public final int getValue() {
      return this.iValue;
   }

   public final int getHeight() {
      return this.iHeight;
   }

   public final void setHeight(int iHeight) {
      this.iHeight = iHeight;
      if (this.iHeight < 1) {
         this.iHeight = 1;
      }
   }

   public final int getDataTypeID() {
      return this.iColorDataID;
   }
}
