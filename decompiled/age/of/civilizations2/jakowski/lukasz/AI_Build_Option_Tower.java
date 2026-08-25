package age.of.civilizations2.jakowski.lukasz;

public class AI_Build_Option_Tower extends AI_Build_Option {
   AI_Build_Option_Tower() {
   }

   @Override
   public float getScore(int nCivID) {
      return CFG.game.getCiv(nCivID).civGameData.civPersonality.BUILD_TOWER
         * (1.0F - CFG.game.getCiv(nCivID).iNumOf_Towers / Math.max(CFG.game.getCiv(nCivID).getNumOfProvinces() * BuildingsManager.getTower_MaxLevel(), 1));
   }

   @Override
   public AI_Build getData(int nCivID) {
      return new AI_Build_Tower(nCivID, this.getMoney(nCivID));
   }
}
