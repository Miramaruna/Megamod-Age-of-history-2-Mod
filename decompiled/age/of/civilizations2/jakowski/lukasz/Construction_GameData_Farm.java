package age.of.civilizations2.jakowski.lukasz;

public class Construction_GameData_Farm extends Construction_GameData {
   public Construction_GameData_Farm(int iProvinceID, int iNumOfTurnsLeft) {
      super(iProvinceID, iNumOfTurnsLeft);
      this.constructionType = ConstructionType.FARM;
   }

   @Override
   public void onConstructed(int nCivID) {
      if (CFG.game.getProvince(this.iProvinceID).getCivID() == nCivID) {
         BuildingsManager.buildFarm(this.iProvinceID, nCivID);
      }
   }
}
