package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.Gdx;

public class RegroupArmy_Data_ToTheFront extends RegroupArmy_Data {
   public RegroupArmy_Data_ToTheFront(int nCivID, int fromProvinceID, int toProvinceID) {
      super(nCivID, fromProvinceID, toProvinceID);
   }

   @Override
   public boolean continueMovingArmy(int nCivID) {
      if (CFG.game.getProvince(this.getFromProvinceID()).getBordersWithEnemy()) {
         Gdx.app
            .log(
               "AoC",
               "continueMovingArmy -> ToTheFront -> "
                  + CFG.game.getCiv(nCivID).getCivName()
                  + " -> 0000: ARMY:"
                  + this.getNumOfUnits()
                  + " -> "
                  + CFG.game.getProvince(this.getFromProvinceID()).getName()
            );
         return false;
      } else {
         return super.continueMovingArmy(nCivID);
      }
   }
}
