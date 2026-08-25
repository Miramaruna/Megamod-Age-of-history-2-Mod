package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MoveUnits_Line_Highlighted extends MoveUnits_Line {
   public static int MOVE_SRC_X2 = 0;
   public static int MOVE_WIDTH2 = 0;

   public MoveUnits_Line_Highlighted(int fromProvinceID, int toProvinceID) {
      super(fromProvinceID, toProvinceID);
      MOVE_WIDTH2 = this.getImageID().getWidth();
   }

   @Override
   public void updateColor(SpriteBatch oSB) {
      oSB.setColor(
         new Color(
            1.0F,
            1.0F,
            1.0F,
            (
                  (CFG.game.getProvince(this.getFromProvinceID()).getSeaProvince() ? 45 : 75)
                     + (
                        CFG.game.getProvinceAnimation_Active_Data().getBackAnimation()
                           ? 30 - CFG.game.getProvinceAnimation_Active_Data().getStepID()
                           : CFG.game.getProvinceAnimation_Active_Data().getStepID()
                     )
               )
               / 255.0F
         )
      );
   }

   @Override
   public boolean getFlipX() {
      return CFG.linesManager.highlightFlipX;
   }

   @Override
   public int getMoveSrcX() {
      return MOVE_SRC_X2;
   }

   @Override
   public Image getImageID() {
      return CFG.linesManager.highlightImage;
   }

   @Override
   public void updateMovingLine() {
      this.fMovingPercentage = this.fMovingPercentage + (float)(System.currentTimeMillis() - this.lMovingTime) / 350.0F * 0.9F;
   }
}
