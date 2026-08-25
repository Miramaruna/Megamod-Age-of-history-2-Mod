package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;

public class PlagueProvince_GameData implements Serializable {
   public static final long serialVersionUID = 0L;
   public int iPlagueID_InGame = 0;
   public int iSinceTurnID = 0;
   public int iDeaths = 0;
   public float iDurationTurnsLeft = 0.0F;

   public PlagueProvince_GameData(int iPlagueID_InGame, int iSinceTurnID, float iDurationTurnsLeft, int iDeaths) {
      this.iPlagueID_InGame = iPlagueID_InGame;
      this.iSinceTurnID = iSinceTurnID;
      this.iDurationTurnsLeft = iDurationTurnsLeft;
      this.iDeaths = iDeaths;
   }
}
