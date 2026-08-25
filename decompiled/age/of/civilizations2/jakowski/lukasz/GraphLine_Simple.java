package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GraphLine_Simple extends GraphLine {
   public int iHeight;

   public GraphLine_Simple(int fromPosX, int fromPosY, int toPosX, int toPosY) {
      super(fromPosX, fromPosY, toPosX, toPosY);
      this.setWidth(toPosX - fromPosX);
      this.iHeight = toPosY - fromPosY;
   }

   @Override
   public final void draw(SpriteBatch oSB, int nPosX, int nPosY, int i) {
      ImageManager.getImage(Images.pix255_255_255).draw(oSB, nPosX - i, nPosY + this.getPosY(), this.getWidth());
      ImageManager.getImage(Images.pix255_255_255).draw(oSB, nPosX - i + this.getWidth(), nPosY + this.getPosY(), 1, this.iHeight);
   }
}
