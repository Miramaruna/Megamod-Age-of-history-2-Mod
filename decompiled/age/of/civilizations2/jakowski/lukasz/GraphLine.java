package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GraphLine {
   public int iPosY;
   public int iWidth;
   public float fAngle;

   public GraphLine(int fromPosX, int fromPosY, int toPosX, int toPosY) {
      this.iPosY = fromPosY;
      this.iWidth = (int)Math.ceil(Math.sqrt((toPosX - fromPosX) * (toPosX - fromPosX) + (fromPosY - toPosY) * (fromPosY - toPosY)));
      this.fAngle = (float)(Math.atan2(fromPosY - toPosY, -fromPosX + toPosX) * 180.0 / Math.PI);
   }

   public void draw(SpriteBatch oSB, int nPosX, int nPosY, int i) {
      ImageManager.getImage(Images.pix255_255_255).draw(oSB, nPosX, nPosY + this.iPosY, this.iWidth, 1, this.fAngle);
   }

   public final int getPosY() {
      return this.iPosY;
   }

   public final int getWidth() {
      return this.iWidth;
   }

   public final void setWidth(int iWidth) {
      this.iWidth = iWidth;
   }
}
