package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;
import java.util.List;

public class Save_Player_GameData implements Serializable {
   public static final long serialVersionUID = 0L;
   public int iCivID = -1;
   public List<Boolean> metProvince;
   public List<Boolean> metCivilization;
   public boolean lostNextTurn = false;

   Save_Player_GameData() {
   }
}
