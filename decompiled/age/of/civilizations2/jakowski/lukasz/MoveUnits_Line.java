package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MoveUnits_Line {
   public int fromProvinceID;
   public int toProvinceID;
   public static int MOVE_SRC_X = 0;
   public static int MOVE_WIDTH = 0;
   public int iWidth;
   public float fAngle;
   public int offsetX = 0;
   public int offsetY = 0;
   public long lMovingTime = 0L;
   public float fMovingPercentage = 0.0F;

   public MoveUnits_Line() {
   }

   public MoveUnits_Line(int fromProvinceID, int toProvinceID) {
      this.fromProvinceID = fromProvinceID;
      this.toProvinceID = toProvinceID;
      if (!CFG.game.getProvince(fromProvinceID).getDrawProvince()) {
         CFG.game.updateDrawProvince(fromProvinceID);
      }

      if (!CFG.game.getProvince(toProvinceID).getDrawProvince()) {
         CFG.game.updateDrawProvince(toProvinceID);
      }

      this.iWidth = (int)Math.ceil(
         Math.sqrt(
            (
                     CFG.game.getProvince(toProvinceID).getCenterX()
                        + CFG.game.getProvince(toProvinceID).getShiftX()
                        + CFG.game.getProvince(toProvinceID).getTranslateProvincePosX()
                        - (
                           CFG.game.getProvince(fromProvinceID).getCenterX()
                              + CFG.game.getProvince(fromProvinceID).getShiftX()
                              + CFG.game.getProvince(fromProvinceID).getTranslateProvincePosX()
                        )
                  )
                  * (
                     CFG.game.getProvince(toProvinceID).getCenterX()
                        + CFG.game.getProvince(toProvinceID).getShiftX()
                        + CFG.game.getProvince(toProvinceID).getTranslateProvincePosX()
                        - (
                           CFG.game.getProvince(fromProvinceID).getCenterX()
                              + CFG.game.getProvince(fromProvinceID).getShiftX()
                              + CFG.game.getProvince(fromProvinceID).getTranslateProvincePosX()
                        )
                  )
               + (
                     CFG.game.getProvince(fromProvinceID).getCenterY()
                        + CFG.game.getProvince(fromProvinceID).getShiftY()
                        - (CFG.game.getProvince(toProvinceID).getCenterY() + CFG.game.getProvince(toProvinceID).getShiftY())
                  )
                  * (
                     CFG.game.getProvince(fromProvinceID).getCenterY()
                        + CFG.game.getProvince(fromProvinceID).getShiftY()
                        - (CFG.game.getProvince(toProvinceID).getCenterY() + CFG.game.getProvince(toProvinceID).getShiftY())
                  )
         )
      );
      this.fAngle = (float)(
         Math.atan2(
               CFG.game.getProvince(fromProvinceID).getCenterY()
                  + CFG.game.getProvince(fromProvinceID).getShiftY()
                  - (CFG.game.getProvince(toProvinceID).getCenterY() + CFG.game.getProvince(toProvinceID).getShiftY()),
               -(
                     CFG.game.getProvince(fromProvinceID).getCenterX()
                        + CFG.game.getProvince(fromProvinceID).getShiftX()
                        + CFG.game.getProvince(fromProvinceID).getTranslateProvincePosX()
                  )
                  + CFG.game.getProvince(toProvinceID).getCenterX()
                  + CFG.game.getProvince(toProvinceID).getShiftX()
                  + CFG.game.getProvince(toProvinceID).getTranslateProvincePosX()
            )
            * 180.0
            / Math.PI
      );
      float tempAngle = this.fAngle > 90.0F ? 90.0F - this.fAngle % 90.0F : (this.fAngle < -90.0F ? -(90.0F + this.fAngle % 90.0F) : this.fAngle);
      this.offsetX = -((int)(this.getImageID().getHeight() / 2.0F * (tempAngle / 90.0F)));
      this.offsetY = -((int)(this.getImageID().getHeight() / 2.0F * ((90.0F - Math.abs(this.fAngle)) / 90.0F)));
      this.lMovingTime = System.currentTimeMillis();
      this.fMovingPercentage = 0.1F;
      MOVE_WIDTH = CFG.linesManager.moveLandImage.getWidth();
   }

   public void updateColor(SpriteBatch oSB) {
      oSB.setColor(Color.WHITE);
   }

   public void updateMovingLine() {
      this.fMovingPercentage = this.fMovingPercentage + (float)(System.currentTimeMillis() - this.lMovingTime) / 1000.0F * 0.9F;
   }

   public void drawLine(SpriteBatch oSB, float nScale) {
      this.updateColor(oSB);
      this.drawLine2(oSB, nScale);
   }

   public void drawLine2(SpriteBatch oSB, float nScale) {
      this.updateMovingLine();
      this.lMovingTime = System.currentTimeMillis();
      if (this.fMovingPercentage >= 1.0F) {
         this.fMovingPercentage = 1.0F;
      } else {
         CFG.setRender_3(true);
      }

      this.getImageID()
         .draw(
            oSB,
            (int)(
                  (
                        CFG.game.getProvince(this.fromProvinceID).getCenterX()
                           + CFG.game.getProvince(this.fromProvinceID).getShiftX()
                           + CFG.game.getProvince(this.fromProvinceID).getTranslateProvincePosX()
                     )
                     * nScale
               )
               + this.offsetX,
            (int)(
                  (
                        CFG.game.getProvince(this.fromProvinceID).getCenterY()
                           + CFG.game.getProvince(this.fromProvinceID).getShiftY()
                           + CFG.map.getMapCoordinates().getPosY()
                     )
                     * nScale
               )
               + this.offsetY,
            (int)(this.iWidth * this.fMovingPercentage * nScale),
            this.getImageID().getHeight(),
            this.fAngle,
            this.getMoveSrcX(),
            this.getFlipX()
         );
      oSB.setColor(Color.WHITE);
   }

   public int getMoveSrcX() {
      return MOVE_SRC_X;
   }

   public boolean getFlipX() {
      return CFG.linesManager.moveLandFlipX;
   }

   public Image getImageID() {
      return CFG.linesManager.moveLandImage;
   }

   public final int getFromProvinceID() {
      return this.fromProvinceID;
   }

   public final void setFromProvinceID(int fromProvinceID) {
      this.fromProvinceID = fromProvinceID;
   }

   public final int getToProvinceID() {
      return this.toProvinceID;
   }

   public final void setToProvinceID(int toProvinceID) {
      this.toProvinceID = toProvinceID;
   }

   public final int getWidth() {
      return this.iWidth;
   }

   public final void setWidth(int iWidth) {
      this.iWidth = iWidth;
   }

   public final float getAngle() {
      return this.fAngle;
   }

   public final void setAngle(float fAngle) {
      this.fAngle = fAngle;
   }

   public final float getMovingPercentage() {
      return this.fMovingPercentage;
   }

   public final void updateMoveTime() {
      this.lMovingTime = System.currentTimeMillis();
      this.fMovingPercentage = 0.1F;
   }
}
