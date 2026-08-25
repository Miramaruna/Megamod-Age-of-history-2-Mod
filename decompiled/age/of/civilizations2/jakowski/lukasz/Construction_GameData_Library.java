package age.of.civilizations2.jakowski.lukasz;

public class Construction_GameData_Library extends Construction_GameData {
   public Construction_GameData_Library(int iProvinceID, int iNumOfTurnsLeft) {
      super(iProvinceID, iNumOfTurnsLeft);
      this.constructionType = ConstructionType.LIBRARY;
   }

   @Override
   public void onConstructed(int nCivID) {
      if (CFG.game.getProvince(this.iProvinceID).getCivID() == nCivID) {
         BuildingsManager.buildLibrary(this.iProvinceID, nCivID);
      }
   }
}
