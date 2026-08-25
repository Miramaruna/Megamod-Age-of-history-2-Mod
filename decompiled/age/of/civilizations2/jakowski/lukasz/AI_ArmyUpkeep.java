package age.of.civilizations2.jakowski.lukasz;

public class AI_ArmyUpkeep {
   public int iProvinceID;
   public int iCost;
   public float fScore = 0.0F;

   public AI_ArmyUpkeep(int nCivID, int nProvinceID) {
      this.iProvinceID = nProvinceID;
      this.iCost = (int)CFG.game_NextTurnUpdate.getMilitaryUpkeep(nProvinceID, nCivID);
   }
}
