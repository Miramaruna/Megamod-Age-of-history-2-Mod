package age.of.civilizations2.jakowski.lukasz;

import java.util.ArrayList;

public class Move_Units_Genocide {
   public int iFromProvinceID;
   public int iNumOfUnits;
   public int iNumOfUnitsWidth;
   public ArrayList<Integer> lNations;

   public Move_Units_Genocide(int iFromProvinceID, int iNumOfUnits, ArrayList<Integer> lNations) {
      this.iFromProvinceID = iFromProvinceID;
      this.setNumOfUnits(iNumOfUnits);
      this.lNations = lNations;
   }

   public final ArrayList<Integer> getNations() {
      return this.lNations;
   }

   public final int getFromProvinceID() {
      return this.iFromProvinceID;
   }

   public final int getNumOfUnits() {
      return this.iNumOfUnits;
   }

   public final void setNumOfUnits(int iNumOfUnits) {
      this.iNumOfUnits = iNumOfUnits;
      CFG.glyphLayout.setText(CFG.fontArmy, "" + iNumOfUnits);
      this.iNumOfUnitsWidth = (int)CFG.glyphLayout.width;
   }

   public final int getUnitsWidth() {
      return this.iNumOfUnitsWidth;
   }
}
