package age.of.civilizations2.jakowski.lukasz;

public class AI_Build_Option_Workshop extends AI_Build_Option {
   AI_Build_Option_Workshop() {
   }

   @Override
   public float getScore(int nCivID) {
      return CFG.game.getCiv(nCivID).civGameData.civPersonality.BUILD_WORKSHOP
         * (
            1.0F
               - CFG.game.getCiv(nCivID).iNumOf_Workshops / Math.max(CFG.game.getCiv(nCivID).getNumOfProvinces() * BuildingsManager.getWorkshop_MaxLevel(), 1)
         );
   }

   @Override
   public AI_Build getData(int nCivID) {
      return new AI_Build_Workshop(nCivID, this.getMoney(nCivID));
   }
}
