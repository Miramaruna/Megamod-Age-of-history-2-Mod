package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;

public class Vassal_GameData implements Serializable {
   public static final long serialVersionUID = 0L;
   public int iCivID;
   public int iTribute;

   public Vassal_GameData(int iCivID) {
      this.iCivID = iCivID;
      this.setTribute(9);
   }

   public final void setTribute(int iTribute) {
      if (iTribute > 100) {
         iTribute = 100;
      } else if (iTribute < -100) {
         iTribute = -100;
      }

      this.iTribute = iTribute;
   }
}
