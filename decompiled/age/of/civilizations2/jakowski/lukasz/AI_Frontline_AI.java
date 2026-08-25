package age.of.civilizations2.jakowski.lukasz;

import java.util.ArrayList;
import java.util.List;

public class AI_Frontline_AI {
   public List<AI_Frontline> lFrontLines = new ArrayList<>();
   public List<Boolean> lFrontLines_OwnFront = new ArrayList<>();
   public int iFrontLinesSize = 0;
   public int iWithCivID = 0;

   public AI_Frontline_AI(int iWithCivID, AI_Frontline nFront, boolean ownFront) {
      this.iWithCivID = iWithCivID;
      this.lFrontLines.add(nFront);
      this.lFrontLines_OwnFront.add(ownFront);
      this.iFrontLinesSize = this.lFrontLines.size();
   }

   public final void addFrontLine(AI_Frontline nFront, boolean ownFront) {
      this.lFrontLines.add(nFront);
      this.lFrontLines_OwnFront.add(ownFront);
      this.iFrontLinesSize = this.lFrontLines.size();
   }

   public final AI_Frontline getFrontLine(int id) {
      return this.lFrontLines.get(id);
   }

   public final int getFrontLinesSize() {
      return this.iFrontLinesSize;
   }

   public final int getWithCivID() {
      return this.iWithCivID;
   }

   public boolean ownFront(int i) {
      return this.lFrontLines_OwnFront.get(i);
   }
}
