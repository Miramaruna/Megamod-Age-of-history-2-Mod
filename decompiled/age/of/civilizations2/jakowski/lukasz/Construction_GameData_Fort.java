package age.of.civilizations2.jakowski.lukasz;

public class Construction_GameData_Fort extends Construction_GameData {
   public Construction_GameData_Fort(int iProvinceID, int iNumOfTurnsLeft) {
      super(iProvinceID, iNumOfTurnsLeft);
      this.constructionType = ConstructionType.FORT;
   }

   @Override
   public void onConstructed(int nCivID) {
      if (CFG.game.getProvince(this.iProvinceID).getCivID() == nCivID) {
         BuildingsManager.buildFort(this.iProvinceID, nCivID);
      }
   }
}
