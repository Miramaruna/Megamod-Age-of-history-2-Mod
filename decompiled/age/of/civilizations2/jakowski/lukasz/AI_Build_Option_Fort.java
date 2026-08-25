package age.of.civilizations2.jakowski.lukasz;

public class AI_Build_Option_Fort extends AI_Build_Option {
   AI_Build_Option_Fort() {
   }

   @Override
   public float getScore(int nCivID) {
      return CFG.game.getCiv(nCivID).civGameData.civPersonality.BUILD_FORT
         * (1.0F - CFG.game.getCiv(nCivID).iNumOf_Forts / Math.max(CFG.game.getCiv(nCivID).getNumOfProvinces() * BuildingsManager.getFort_MaxLevel(), 1));
   }

   @Override
   public AI_Build getData(int nCivID) {
      return new AI_Build_Fort(nCivID, this.getMoney(nCivID));
   }
}
