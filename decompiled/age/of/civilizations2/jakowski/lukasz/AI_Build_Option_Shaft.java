package age.of.civilizations2.jakowski.lukasz;

class AI_Build_Option_Shaft extends AI_Build_Option {
   @Override
   protected AI_Build getData(int var1) {
      return new AI_Build_Shaft(var1, this.getMoney(var1));
   }

   @Override
   protected float getScore(int var1) {
      return CFG.game.getCiv(var1).civGameData.civPersonality.BUILD_SHAFT
         * (1.0F - CFG.game.getCiv(var1).iNumOf_Shafts / Math.max(CFG.game.getCiv(var1).getNumOfProvinces() * Shaft.getShaft_MaxLevel(), 1));
   }
}
