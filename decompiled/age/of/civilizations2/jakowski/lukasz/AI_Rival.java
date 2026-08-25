package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;

public class AI_Rival implements Serializable {
   public static final long serialVersionUID = 0L;
   public int iCivID;
   public int iUntilTurnID;

   public AI_Rival(int iCivID, int iUntilTurnID) {
      this.iCivID = iCivID;
      this.iUntilTurnID = iUntilTurnID;
   }
}
