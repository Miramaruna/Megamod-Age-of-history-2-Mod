package age.of.civilizations2.jakowski.lukasz;

public class Construction_GameData_Supply extends Construction_GameData {
   public Construction_GameData_Supply(int iProvinceID, int iNumOfTurnsLeft) {
      super(iProvinceID, iNumOfTurnsLeft);
      this.constructionType = ConstructionType.SUPPLY;
   }

   @Override
   public void onConstructed(int nCivID) {
      if (CFG.game.getProvince(this.iProvinceID).getCivID() == nCivID) {
         BuildingsManager.buildSupply(this.iProvinceID, nCivID);
      }
   }
}
