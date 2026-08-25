package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Move_Units {
   public int iFromProvinceID;
   public int iToProvinceID;
   public int iNumOfUnits;
   public int iNumOfUnitsWidth;
   public MoveUnits_Line moveUnitsLine = null;

   public Move_Units(int iFromProvinceID, int iToProvinceID, int iNumOfUnits, boolean buildLane) {
      this.iFromProvinceID = iFromProvinceID;
      this.iToProvinceID = iToProvinceID;
      this.iNumOfUnits = iNumOfUnits;
      this.buildMoveUnitsLine();
   }

   public Move_Units(int iFromProvinceID, int iToProvinceID, int iNumOfUnits, boolean buildLane, boolean migrateLine) {
      this.iFromProvinceID = iFromProvinceID;
      this.iToProvinceID = iToProvinceID;
      this.iNumOfUnits = iNumOfUnits;
      if (buildLane) {
         this.buildMoveUnitsLine_Migrate();
      }
   }

   public final void draw(SpriteBatch oSB, float nScale) {
      this.moveUnitsLine.drawLine(oSB, nScale);
   }

   public final void draw2(SpriteBatch oSB, float nScale) {
      this.moveUnitsLine.drawLine2(oSB, nScale);
   }

   public final int getNumOfUnits() {
      return this.iNumOfUnits;
   }

   public final void setNumOfUnits(int iNumOfUnits) {
      try {
         this.iNumOfUnits = iNumOfUnits;
         if (this.moveUnitsLine != null) {
            this.moveUnitsLine.lMovingTime = System.currentTimeMillis();
            this.moveUnitsLine.fMovingPercentage = 0.1F;
         }

         CFG.glyphLayout.setText(CFG.fontArmy, "" + iNumOfUnits);
         this.iNumOfUnitsWidth = (int)CFG.glyphLayout.width;
      } catch (NullPointerException var3) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var3);
         }
      }
   }

   public final int getFromProvinceID() {
      return this.iFromProvinceID;
   }

   public final int getToProvinceID() {
      return this.iToProvinceID;
   }

   public final MoveUnits_Line getMoveUnitsLine() {
      return this.moveUnitsLine;
   }

   public final int getUnitsWidth() {
      return this.iNumOfUnitsWidth;
   }

   public final void buildMoveUnitsLine() {
      try {
         this.moveUnitsLine = new MoveUnits_Line(this.iFromProvinceID, this.iToProvinceID);
         CFG.glyphLayout.setText(CFG.fontArmy, "" + this.iNumOfUnits);
         this.iNumOfUnitsWidth = (int)CFG.glyphLayout.width;
      } catch (IndexOutOfBoundsException var2) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var2);
         }
      } catch (NullPointerException var3) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var3);
         }
      }
   }

   public final void buildMoveUnitsLine_Migrate() {
      try {
         this.moveUnitsLine = new MoveUnits_Line_Migrate(this.iFromProvinceID, this.iToProvinceID);
         CFG.glyphLayout.setText(CFG.fontArmy, "" + this.iNumOfUnits);
         this.iNumOfUnitsWidth = (int)CFG.glyphLayout.width;
      } catch (IndexOutOfBoundsException var2) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var2);
         }
      }
   }
}
