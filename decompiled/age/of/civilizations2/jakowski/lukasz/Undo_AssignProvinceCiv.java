package age.of.civilizations2.jakowski.lukasz;

public class Undo_AssignProvinceCiv {
   public int iProvinceID;
   public int iCivID;

   public Undo_AssignProvinceCiv(int iProvinceID, int iCivID) {
      this.iProvinceID = iProvinceID;
      this.iCivID = iCivID;
   }

   public final int getProvinceID() {
      return this.iProvinceID;
   }

   public final int getCivID() {
      return this.iCivID;
   }
}
