package age.of.civilizations2.jakowski.lukasz;

public class Construction_GameData_Workshop extends Construction_GameData {
   public Construction_GameData_Workshop(int iProvinceID, int iNumOfTurnsLeft) {
      super(iProvinceID, iNumOfTurnsLeft);
      this.constructionType = ConstructionType.WORKSHOP;
   }

   @Override
   public void onConstructed(int nCivID) {
      if (CFG.game.getProvince(this.iProvinceID).getCivID() == nCivID) {
         BuildingsManager.buildWorkshop(this.iProvinceID, nCivID);
      }
   }
}
