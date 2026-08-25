package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MoveUnits_Line_Migrate extends MoveUnits_Line {
   public static int MOVE_SRC_X2 = 0;
   public static int MOVE_WIDTH2 = 0;

   public MoveUnits_Line_Migrate(int fromProvinceID, int toProvinceID) {
      super(fromProvinceID, toProvinceID);
      MOVE_WIDTH2 = this.getImageID().getWidth();
   }

   @Override
   public void updateColor(SpriteBatch oSB) {
      oSB.setColor(
         new Color(
            CFG.game.getCiv(CFG.game.getProvince(this.getFromProvinceID()).getCivID()).getR() / 255,
            CFG.game.getCiv(CFG.game.getProvince(this.getFromProvinceID()).getCivID()).getG() / 255.0F,
            CFG.game.getCiv(CFG.game.getProvince(this.getFromProvinceID()).getCivID()).getB() / 255.0F,
            1.0F
         )
      );
   }

   @Override
   public boolean getFlipX() {
      return CFG.linesManager.migrateFlipX;
   }

   @Override
   public int getMoveSrcX() {
      return MOVE_SRC_X2;
   }

   @Override
   public Image getImageID() {
      return CFG.linesManager.migrateImage;
   }

   @Override
   public void updateMovingLine() {
      this.fMovingPercentage = this.fMovingPercentage + (float)(System.currentTimeMillis() - this.lMovingTime) / 350.0F * 0.9F;
   }
}
