package age.of.civilizations2.jakowski.lukasz;

class AI_Build_Option_NuclearReactor extends AI_Build_Option {
   @Override
   protected AI_Build getData(int var1) {
      return new AI_Build_NuclearReactor(var1, this.getMoney(var1));
   }

   @Override
   protected float getScore(int var1) {
      return CFG.game.getCiv(var1).civGameData.civPersonality.BUILD_NUCLEAR_REACTOR
         * (
            1.0F
               - CFG.game.getCiv(var1).iNumOf_NuclearReactors
                  / Math.max(CFG.game.getCiv(var1).getNumOfProvinces() * BuildingsManager.getNuclearReactor_MaxLevel(), 1)
         );
   }
}
