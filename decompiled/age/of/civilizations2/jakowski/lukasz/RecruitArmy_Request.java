package age.of.civilizations2.jakowski.lukasz;

public class RecruitArmy_Request {
   public int iArmy;
   public int iProvinceID;
   public int iArmyWidth = 0;

   public RecruitArmy_Request(int iProvinceID, int iArmy) {
      this.iProvinceID = iProvinceID;
      this.setArmy(iArmy);
   }

   public final int getArmy() {
      return this.iArmy;
   }

   public final void setArmy(int iArmy) {
      this.iArmy = iArmy;

      try {
         CFG.glyphLayout.setText(CFG.fontArmy, "" + iArmy);
         this.iArmyWidth = (int)CFG.glyphLayout.width;
      } catch (NullPointerException var3) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var3);
         }
      }
   }

   public final int getProvinceID() {
      return this.iProvinceID;
   }

   public final void setProvinceID(int iProvinceID) {
      this.iProvinceID = iProvinceID;
   }

   public final int getArmyWidth() {
      return this.iArmyWidth;
   }
}
