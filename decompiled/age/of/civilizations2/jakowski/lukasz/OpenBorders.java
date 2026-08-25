package age.of.civilizations2.jakowski.lukasz;

public class OpenBorders {
   public int iToCivID;
   public int iNumOfTurns;

   public OpenBorders(int iToCivID, int iNumOfTurns) {
      this.iToCivID = iToCivID;
      this.iNumOfTurns = iNumOfTurns;
   }

   public final int getToCivID() {
      return this.iToCivID;
   }

   public final void setToCivID(int iToCivID) {
      this.iToCivID = iToCivID;
   }

   public final int getNumOfTurns() {
      return this.iNumOfTurns;
   }

   public final void setNumOfTurns(int iNumOfTurns) {
      this.iNumOfTurns = iNumOfTurns;
   }
}
