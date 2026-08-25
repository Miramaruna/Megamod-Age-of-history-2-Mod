package age.of.civilizations2.jakowski.lukasz;

public class AI_Build_Option_Invest extends AI_Build_Option {
   AI_Build_Option_Invest() {
   }

   @Override
   public float getScore(int nCivID) {
      return CFG.game.getCiv(nCivID).civGameData.civPersonality.BUILD_INVEST
         * (1.0F - (float)CFG.game.getCiv(nCivID).getInvestsSize() / CFG.game.getCiv(nCivID).getNumOfProvinces());
   }

   @Override
   public AI_Build getData(int nCivID) {
      return new AI_Build_Invest(nCivID, this.getMoney(nCivID));
   }
}
