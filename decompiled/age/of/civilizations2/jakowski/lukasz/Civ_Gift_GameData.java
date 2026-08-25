package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;

public class Civ_Gift_GameData implements Serializable {
   public static final long serialVersionUID = 0L;
   public int iFromCivID;
   public int iTurnID;

   public Civ_Gift_GameData(int iFromCivID, int iTurnID) {
      this.iFromCivID = iFromCivID;
      this.iTurnID = iTurnID;
   }
}
