package age.of.civilizations2.jakowski.lukasz;

public class Construction_GameData_Tower extends Construction_GameData {
   public Construction_GameData_Tower(int iProvinceID, int iNumOfTurnsLeft) {
      super(iProvinceID, iNumOfTurnsLeft);
      this.constructionType = ConstructionType.TOWER;
   }

   @Override
   public void onConstructed(int nCivID) {
      if (CFG.game.getProvince(this.iProvinceID).getCivID() == nCivID) {
         BuildingsManager.buildTower(this.iProvinceID, nCivID);
      }
   }
}
