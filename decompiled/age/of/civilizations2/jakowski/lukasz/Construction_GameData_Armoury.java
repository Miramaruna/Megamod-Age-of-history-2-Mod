package age.of.civilizations2.jakowski.lukasz;

public class Construction_GameData_Armoury extends Construction_GameData {
   public Construction_GameData_Armoury(int iProvinceID, int iNumOfTurnsLeft) {
      super(iProvinceID, iNumOfTurnsLeft);
      this.constructionType = ConstructionType.ARMOURY;
   }

   @Override
   public void onConstructed(int nCivID) {
      if (CFG.game.getProvince(this.iProvinceID).getCivID() == nCivID) {
         BuildingsManager.buildArmoury(this.iProvinceID, nCivID);
      }
   }
}
