package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;

public class AI_WarPreparations implements Serializable {
   public static final long serialVersionUID = 0L;
   public int iLeaderCivID = 0;
   public int onCivID;
   public int iNumOfTurnsLeft = 0;
   public boolean declareWar = true;

   public AI_WarPreparations(int iLeaderCivID, int onCivID, boolean declareWar, int numOfTurns) {
      this.onCivID = onCivID;
      this.declareWar = declareWar;
      this.iLeaderCivID = iLeaderCivID;
      this.iNumOfTurnsLeft = numOfTurns;
   }
}
