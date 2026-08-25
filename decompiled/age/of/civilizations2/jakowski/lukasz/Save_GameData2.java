package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;

public class Save_GameData2 implements Serializable {
   public static final long serialVersionUID = 0L;
   public float AI_AGGRESSIVNESS;

   Save_GameData2() {
   }

   public final void buildData() {
      this.AI_AGGRESSIVNESS = Game_Calendar.AI_AGGRESSIVNESS;
   }
}
