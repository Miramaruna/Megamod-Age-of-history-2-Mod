package age.of.civilizations2.jakowski.lukasz;

public class RegroupArmy_Data_AtPeace extends RegroupArmy_Data {
   public RegroupArmy_Data_AtPeace(int nCivID, int fromProvinceID, int toProvinceID) {
      super(nCivID, fromProvinceID, toProvinceID);
   }

   @Override
   public boolean continueMovingArmy(int nCivID) {
      return CFG.game.getCiv(nCivID).isAtWar() ? false : super.continueMovingArmy(nCivID);
   }
}
