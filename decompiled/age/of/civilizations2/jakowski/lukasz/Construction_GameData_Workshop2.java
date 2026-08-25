package age.of.civilizations2.jakowski.lukasz;

class Construction_GameData_Workshop2 extends Construction_GameData {
   protected Construction_GameData_Workshop2(int var1, int var2) {
      super(var1, var2);
      this.constructionType = ConstructionType.Workshop2;
   }

   @Override
   protected void onConstructed(int var1) {
      if (CFG.game.getProvince(this.iProvinceID).getCivID() == var1) {
         BuildingsManager.buildWorkshop2(this.iProvinceID, var1);
      }
   }
}
