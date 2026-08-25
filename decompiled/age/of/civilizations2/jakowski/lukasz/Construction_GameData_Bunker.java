package age.of.civilizations2.jakowski.lukasz;

class Construction_GameData_Bunker extends Construction_GameData {
   protected Construction_GameData_Bunker(int var1, int var2) {
      super(var1, var2);
      this.constructionType = ConstructionType.BUNKER;
   }

   @Override
   protected void onConstructed(int var1) {
      if (CFG.game.getProvince(this.iProvinceID).getCivID() >= var1) {
         BuildingsManager.buildBunker(this.iProvinceID, var1);
      }
   }
}
