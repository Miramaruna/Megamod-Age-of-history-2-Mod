package age.of.civilizations2.jakowski.lukasz;

public class AI_Build_Option_Armoury extends AI_Build_Option {
   AI_Build_Option_Armoury() {
   }

   @Override
   public float getScore(int nCivID) {
      return CFG.game.getCiv(nCivID).civGameData.civPersonality.BUILD_ARMOURY
         * (1.0F - CFG.game.getCiv(nCivID).iNumOf_Armories / Math.max(CFG.game.getCiv(nCivID).getNumOfProvinces() * BuildingsManager.getArmoury_MaxLevel(), 1));
   }

   @Override
   public AI_Build getData(int nCivID) {
      return new AI_Build_Armoury(nCivID, this.getMoney(nCivID));
   }
}
