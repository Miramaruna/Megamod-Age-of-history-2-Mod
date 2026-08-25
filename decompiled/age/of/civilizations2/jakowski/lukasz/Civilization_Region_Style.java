package age.of.civilizations2.jakowski.lukasz;

public class Civilization_Region_Style {
   Civilization_Region_Style() {
   }

   public void updatePB(int nProvinceID, int withProvinceID) {
      CFG.game.getProvince(nProvinceID).getProvinceBordersLandByLand(withProvinceID).updateDrawProvinceBorder_CivilizationRegion();
   }
}
