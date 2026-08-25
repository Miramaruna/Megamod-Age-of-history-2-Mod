package age.of.civilizations2.jakowski.lukasz;

import java.util.ArrayList;
import java.util.List;

public class MoveUnits_TurnData {
   public List<Move_Units> lMoveUnits = new ArrayList<>();
   public int iMoveUnitsSize = 0;
   public List<Integer> lCivID = new ArrayList<>();

   public MoveUnits_TurnData(int iCivID) {
   }

   protected final int getMoveUnitsFromProvinceSize() {
      ArrayList<Integer> arrayList = new ArrayList<>();

      label23:
      for (byte b = 0; b < this.iMoveUnitsSize; b++) {
         this.lMoveUnits.get(b).getFromProvinceID();

         for (byte b1 = 0; b1 < arrayList.size(); b1++) {
            int i = arrayList.get(b1);
            if (this.lMoveUnits.get(b).getFromProvinceID() == i) {
               continue label23;
            }
         }

         arrayList.add(this.lMoveUnits.get(b).getFromProvinceID());
      }

      return arrayList.size();
   }

   protected final int getWarFieldWidth() {
      int i = this.getMoveUnitsFromProvinceSize();
      int j = 0;
      if (i > 1) {
         j = 3000 * (i - 1);
      }

      i = this.lMoveUnits.get(0).getToProvinceID();
      i = CFG.game.getProvince(i).getTerrainTypeID();
      float f = CFG.terrainTypesManager.getMovementCost(i);
      return (int)((10000 + j + CFG.game.getGameScenarios().getScenario_StartingPopulation() / 10) / (1.0F + f));
   }

   public final int getMoveUnitsSize() {
      return this.iMoveUnitsSize;
   }

   public final void addMoveUnits(Move_Units nMoveUnits, int nCivID) {
      if (nMoveUnits.getMoveUnitsLine() == null) {
         nMoveUnits.buildMoveUnitsLine();
      }

      this.lMoveUnits.add(nMoveUnits);
      this.lCivID.add(nCivID);
      this.iMoveUnitsSize = this.lMoveUnits.size();
   }

   public final Move_Units getMoveUnits(int i) {
      return this.lMoveUnits.get(i);
   }

   public final int getMoveUnits_TotalNumOfUnits() {
      int out = 0;

      for (int i = 0; i < this.iMoveUnitsSize; i++) {
         out += this.lMoveUnits.get(i).getNumOfUnits();
      }

      return out;
   }

   public final int getCivID(int i) {
      return this.lCivID.get(i);
   }

   public final boolean isPlayerMoving() {
      for (int i = 0; i < this.iMoveUnitsSize; i++) {
         if (CFG.game.getCiv(this.lCivID.get(i)).getControlledByPlayer()) {
            return true;
         }
      }

      return false;
   }
}
