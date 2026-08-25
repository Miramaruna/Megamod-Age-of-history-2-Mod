package age.of.civilizations2.jakowski.lukasz;

public class AI_Build_Option_Library extends AI_Build_Option {
   AI_Build_Option_Library() {
   }

   @Override
   public float getScore(int nCivID) {
      return CFG.game.getCiv(nCivID).civGameData.civPersonality.BUILD_LIBRARY
         * (1.0F - CFG.game.getCiv(nCivID).iNumOf_Libraries / Math.max(CFG.game.getCiv(nCivID).getNumOfProvinces() * BuildingsManager.getLibrary_MaxLevel(), 1));
   }

   @Override
   public AI_Build getData(int nCivID) {
      return new AI_Build_Library(nCivID, this.getMoney(nCivID));
   }
}
