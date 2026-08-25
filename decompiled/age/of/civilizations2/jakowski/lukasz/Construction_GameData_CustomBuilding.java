package age.of.civilizations2.jakowski.lukasz;

class Construction_GameData_CustomBuilding extends Construction_GameData {
   public Construction_GameData_CustomBuilding(int iProvinceID, int iNumOfTurnsLeft, int n) {
      super(iProvinceID, iNumOfTurnsLeft, n);
      this.constructionType = ConstructionType.CUSTOMBUILDING;
   }

   @Override
   public void onConstructed(int nCivID) {
      if (CFG.game.getProvince(this.iProvinceID).getCivID() == nCivID) {
         CustomBuildingsManager.buildBuilding(this.iProvinceID, nCivID, this.iN);
      }
   }
}
