package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;

public class Save_GameData_11 implements Serializable {
   public static final long serialVersionUID = 0L;
   public Events_GameData eventsGameData;

   public final void buildData() {
      this.eventsGameData = CFG.eventsManager.eventsGD;
   }
}
