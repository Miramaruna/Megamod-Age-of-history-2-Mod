package age.of.civilizations2.jakowski.lukasz;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomTurnOrder {
   public List<Integer> lRandomTurnOrder = new ArrayList<>();
   public int iRTOSize = 0;

   RandomTurnOrder() {
   }

   public final void buildRandomOrder() {
      this.lRandomTurnOrder.clear();
      ArrayList<Integer> tempIDs = new ArrayList<>();

      for (int i = 1; i < CFG.game.getCivsSize(); i++) {
         if (CFG.game.getCiv(i).getNumOfProvinces() > 0) {
            tempIDs.add(i);
         }
      }

      Random oR = new Random();

      while (tempIDs.size() > 0) {
         int tempID = oR.nextInt(tempIDs.size());
         this.lRandomTurnOrder.add(tempIDs.get(tempID));
         tempIDs.remove(tempID);
      }

      this.iRTOSize = this.lRandomTurnOrder.size();
   }

   public final int getRTO(int i) {
      return this.lRandomTurnOrder.get(i);
   }

   public final int getPositionInRTOOfCiv(int nCivID) {
      for (int i = 0; i < this.iRTOSize; i++) {
         if (nCivID == this.lRandomTurnOrder.get(i)) {
            return i + 1;
         }
      }

      return 0;
   }

   public final int getRTOSize() {
      return this.iRTOSize;
   }
}
