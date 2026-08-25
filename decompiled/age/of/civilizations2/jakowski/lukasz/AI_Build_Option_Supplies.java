package age.of.civilizations2.jakowski.lukasz;

public class AI_Build_Option_Supplies extends AI_Build_Option {
   AI_Build_Option_Supplies() {
   }

   @Override
   public float getScore(int nCivID) {
      return CFG.game.getCiv(nCivID).civGameData.civPersonality.BUILD_SUPPLYLINE
         * (
            1.0F
               - CFG.game.getCiv(nCivID).iNumOf_SuppliesCamp / Math.max(CFG.game.getCiv(nCivID).getNumOfProvinces() * BuildingsManager.getSupply_MaxLevel(), 1)
         );
   }

   @Override
   public AI_Build getData(int nCivID) {
      return new AI_Build_Supplies(nCivID, this.getMoney(nCivID));
   }
}
