package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;

public class AI_Influence implements Serializable {
   public static final long serialVersionUID = 0L;
   public int iCivID;
   public int iMinRelation;
   public int iUntilTurnID;

   public AI_Influence(int iCivID, int iMinRelation, int iUntilTurnID) {
      this.iCivID = iCivID;
      this.iMinRelation = iMinRelation;
      this.iUntilTurnID = iUntilTurnID;
   }
}
