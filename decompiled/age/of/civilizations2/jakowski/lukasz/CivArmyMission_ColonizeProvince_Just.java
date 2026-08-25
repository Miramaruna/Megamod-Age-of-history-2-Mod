package age.of.civilizations2.jakowski.lukasz;

public class CivArmyMission_ColonizeProvince_Just extends CivArmyMission {
   public int iCivID;
   public int iColonizeProvinceID;

   public CivArmyMission_ColonizeProvince_Just(int nCivID, int colonizeProvinceID) {
      this.toProvinceID = colonizeProvinceID;
      this.iColonizeProvinceID = colonizeProvinceID;
      this.MISSION_ID = -1;
      this.iCivID = nCivID;
      this.MISSION_TYPE = CivArmyMission_Type.COLONIZE_PROVINCE;
      this.TURN_ID = Game_Calendar.TURN_ID;
      this.iObsolate = (int)Math.max(CFG.game.getProvincesSize() * 0.01F, 30.0F);
      this.iArmy = 0;
      this.generateColonizeData();
   }

   public final boolean generateColonizeData() {
      this.iProvinceID = -1;
      if (CFG.game.getCiv(this.iCivID).iBudget < 0) {
         this.iObsolate = 0;
         CFG.game.getCiv(this.iCivID).civGameData.iLockTreasury = 1;
         return false;
      } else {
         for (int i = 0; i < CFG.game.getProvince(this.iColonizeProvinceID).getNeighboringProvincesSize(); i++) {
            if (this.iCivID == CFG.game.getProvince(CFG.game.getProvince(this.iColonizeProvinceID).getNeighboringProvinces(i)).getCivID()) {
               this.iProvinceID = CFG.game.getProvince(this.iColonizeProvinceID).getNeighboringProvinces(i);
               break;
            }
         }

         this.lockTreasury();
         if (this.iProvinceID < 0) {
            this.iObsolate = 0;
            CFG.game.getCiv(this.iCivID).civGameData.iLockTreasury = 1;
            return false;
         } else {
            return true;
         }
      }
   }

   @Override
   public boolean canMakeAction(int nCivID, int iTurnsLeft) {
      return true;
   }

   @Override
   public boolean action(int nCivID) {
      if (CFG.game.getProvince(this.iColonizeProvinceID).getCivID() != 0) {
         CFG.game.getCiv(this.iCivID).civGameData.iLockTreasury = 1;
         return true;
      } else if (DiplomacyManager.colonizeWastelandProvince(this.iColonizeProvinceID, this.iCivID)) {
         CFG.game.getCiv(this.iCivID).civGameData.iLockTreasury = 1;
         CFG.game.getCiv(this.iCivID).buildCivPersonality_Colonization();
         return true;
      } else {
         this.lockTreasury();
         return false;
      }
   }

   public final void lockTreasury() {
      int colonizeCost = (int)(DiplomacyManager.getColonizeCost(this.iColonizeProvinceID, this.iCivID) * 1.05F);
      CFG.game.getCiv(this.iCivID).civGameData.iLockTreasury = Math.max(CFG.game.getCiv(this.iCivID).civGameData.iLockTreasury, colonizeCost);
      if (CFG.game.getCiv(this.iCivID).iBudget > 0) {
         this.iObsolate = CFG.game.getCiv(this.iCivID).getMoney() > 0L && CFG.game.getCiv(this.iCivID).getMoney() < colonizeCost
            ? Math.max(this.iObsolate, Math.max(2, (int)Math.ceil((float)CFG.game.getCiv(this.iCivID).getMoney() / colonizeCost)))
            : Math.max(2, this.iObsolate);
      }
   }
}
