package age.of.civilizations2.jakowski.lukasz;

public class Construction_GameData_Shaft extends Construction_GameData {
   protected Construction_GameData_Shaft(int var1, int var2) {
      super(var1, var2);
      this.constructionType = ConstructionType.SHAFT;
   }

   @Override
   protected void onConstructed(int var1) {
      if (CFG.game.getProvince(this.iProvinceID).getCivID() == var1) {
         Shaft.buildShaft(this.iProvinceID, var1);
      }
   }
}
