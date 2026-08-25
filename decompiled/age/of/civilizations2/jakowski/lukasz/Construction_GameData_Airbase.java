package age.of.civilizations2.jakowski.lukasz;

public class Construction_GameData_Airbase extends Construction_GameData {
   public Construction_GameData_Airbase(int iProvinceID, int iNumOfTurnsLeft) {
      super(iProvinceID, iNumOfTurnsLeft);
      this.constructionType = ConstructionType.AIRBASE;
   }

   @Override
   public void onConstructed(int nCivID) {
      if (CFG.game.getProvince(this.iProvinceID).getCivID() == nCivID) {
         BuildingsManager.buildAirbase(this.iProvinceID, nCivID);
      }
   }
}
