package age.of.civilizations2.jakowski.lukasz;

public class CivArmyMission_RegroupAfterRecruitment extends CivArmyMission {
   public int iTurnsRequiredToMoveToFrontLine = 1;

   public CivArmyMission_RegroupAfterRecruitment(int nCivID, int fromProvinceID, int toProvinceID, int iArmy) {
      this.iArmy = iArmy;
      this.iProvinceID = fromProvinceID;
      this.toProvinceID = toProvinceID;
      this.MISSION_ID = -1;
      RegroupArmy_Data tryRegroupArmy = new RegroupArmy_Data(nCivID, this.iProvinceID, toProvinceID);
      this.iTurnsRequiredToMoveToFrontLine = tryRegroupArmy.getRouteSize();
      this.MISSION_TYPE = CivArmyMission_Type.REGRUOP_AFTER_RECRUIT;
      this.TURN_ID = Game_Calendar.TURN_ID;
      this.iObsolate = 15;
   }

   @Override
   public boolean canMakeAction(int nCivID, int iTurnsLeft) {
      return Game_Calendar.TURN_ID != this.TURN_ID;
   }

   @Override
   public boolean action(int nCivID) {
      RegroupArmy_Data tryRegroupArmy;
      if (this.iProvinceID == this.toProvinceID || (tryRegroupArmy = new RegroupArmy_Data(nCivID, this.iProvinceID, this.toProvinceID)).getRouteSize() <= 0) {
         return true;
      } else if (tryRegroupArmy.getRouteSize() == 1) {
         return CFG.gameAction.moveArmy(this.iProvinceID, this.toProvinceID, this.iArmy, nCivID, true, false);
      } else if (CFG.gameAction.moveArmy(this.iProvinceID, tryRegroupArmy.getRoute(0), this.iArmy, nCivID, true, false)) {
         tryRegroupArmy.setFromProvinceID(tryRegroupArmy.getRoute(0));
         tryRegroupArmy.removeRoute(0);
         tryRegroupArmy.setNumOfUnits(this.iArmy);
         CFG.game.getCiv(nCivID).addRegroupArmy(tryRegroupArmy);
         return true;
      } else {
         return false;
      }
   }
}
