package age.of.civilizations2.jakowski.lukasz;

public class War_Points {
   public int iCivID;
   public int iPoints = 0;
   public int iNumOfLostProvinces = 0;
   public int iMinScore = 0;

   public War_Points(int iCivID) {
      this.iCivID = iCivID;
   }

   public void addPoints(int nP) {
      this.iPoints += nP;
      if (nP > this.iMinScore) {
         this.iMinScore = nP;
      }

      this.iNumOfLostProvinces++;
   }

   public final int getNumOfProvincesTotal() {
      return this.iNumOfLostProvinces + CFG.game.getCiv(this.iCivID).getNumOfProvinces();
   }
}
