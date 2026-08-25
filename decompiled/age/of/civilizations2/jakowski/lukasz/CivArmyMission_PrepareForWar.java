package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.Gdx;

public class CivArmyMission_PrepareForWar extends CivArmyMission {
   public int iTurnsRequiredToMoveToFrontLine = 1;

   public CivArmyMission_PrepareForWar(int nCivID, int fromProvinceID, int toProvinceID, int iArmy, int MISSION_ID) {
      this.iArmy = iArmy;
      this.iProvinceID = fromProvinceID;
      this.toProvinceID = toProvinceID;
      this.MISSION_ID = MISSION_ID;
      RegroupArmy_Data tryRegroupArmy = new RegroupArmy_Data(nCivID, this.iProvinceID, toProvinceID);
      this.iTurnsRequiredToMoveToFrontLine = tryRegroupArmy.getRouteSize();
      Gdx.app.log("AoC", "CivArmyMission_PrepareForWar -> INIT: " + CFG.game.getCiv(nCivID).getCivName() + ", LEFT: " + this.iTurnsRequiredToMoveToFrontLine);
      RegroupArmy_Data var7 = null;
   }

   @Override
   public boolean canMakeAction(int nCivID, int iTurnsLeft) {
      return true;
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
