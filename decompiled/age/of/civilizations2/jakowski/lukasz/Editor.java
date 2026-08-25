package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Editor {
   public boolean inUse = false;

   Editor() {
   }

   public void keyDown(int keycode) {
   }

   public void keyUp(int keycode) {
   }

   public void touchDown(int screenX, int screenY, int pointer, int button) {
   }

   public void touchDragged(int screenX, int screenY, int pointer) {
   }

   public void touchUp(int screenX, int screenY, int pointer, int button) {
   }

   public void draw(SpriteBatch oSB) {
      CFG.fontMain.getData().setScale(0.9F);
      CFG.glyphLayout.setText(CFG.fontMain, this.toString());
      oSB.setColor(0.08F, 0.012F, 0.038F, 0.95F);
      ImageManager.getImage(Images.slider_gradient)
         .draw(
            oSB,
            0,
            -ImageManager.getImage(Images.slider_gradient).getHeight(),
            (int)CFG.glyphLayout.width + CFG.PADDING * 6,
            (int)CFG.glyphLayout.height + CFG.PADDING * 4
         );
      oSB.setColor(CFG.COLOR_FLAG_FRAME);
      ImageManager.getImage(Images.slider_gradient)
         .draw(oSB, 0, -ImageManager.getImage(Images.slider_gradient).getHeight() + 1, (int)CFG.glyphLayout.width + CFG.PADDING * 6, 1);
      ImageManager.getImage(Images.slider_gradient)
         .draw(
            oSB,
            0,
            -ImageManager.getImage(Images.slider_gradient).getHeight() + (int)CFG.glyphLayout.height + CFG.PADDING * 4 - 2,
            (int)CFG.glyphLayout.width + CFG.PADDING * 6,
            1
         );
      oSB.setColor(Color.WHITE);
      CFG.drawTextWithShadow(oSB, this.toString(), CFG.PADDING, CFG.PADDING * 2, Color.WHITE);
      CFG.fontMain.getData().setScale(1.0F);
   }

   public final boolean getInUse() {
      return this.inUse;
   }

   public void setInUse(boolean inUse) {
      this.inUse = inUse;
   }

   @Override
   public String toString() {
      return "EDITOR";
   }
}
