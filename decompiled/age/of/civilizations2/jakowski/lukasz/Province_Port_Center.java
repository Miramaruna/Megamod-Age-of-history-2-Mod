package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Province_Port_Center {
   public int iShiftX;
   public int iShiftY;

   public Province_Port_Center() {
      this.iShiftX = 0;
      this.iShiftY = 0;
   }

   public Province_Port_Center(int iShiftX, int iShiftY) {
      this.iShiftX = iShiftX;
      this.iShiftY = iShiftY;
   }

   public void draw(SpriteBatch oSB, int nPosX, int nPosY, float nScale) {
      ImageManager.getImage(Images.port_ico)
         .draw(
            oSB,
            nPosX + (int)(this.iShiftX * nScale) - ImageManager.getImage(Images.port_ico).getWidth() / 2,
            nPosY + (int)(this.iShiftY * nScale) - ImageManager.getImage(Images.port_ico).getHeight() / 2
         );
   }

   public final int getShiftX() {
      return this.iShiftX;
   }

   public final int getShiftY() {
      return this.iShiftY;
   }
}
