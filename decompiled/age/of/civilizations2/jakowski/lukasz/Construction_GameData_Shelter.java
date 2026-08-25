package age.of.civilizations2.jakowski.lukasz;

public class Construction_GameData_Shelter extends Construction_GameData {
   public Construction_GameData_Shelter(int iProvinceID, int iNumOfTurnsLeft) {
      super(iProvinceID, iNumOfTurnsLeft);
      this.constructionType = ConstructionType.SHELTER;
   }

   @Override
   public void onConstructed(int nCivID) {
      if (CFG.game.getProvince(this.iProvinceID).getCivID() == nCivID) {
         BuildingsManager.buildShelter(this.iProvinceID, nCivID);
      }
   }
}
