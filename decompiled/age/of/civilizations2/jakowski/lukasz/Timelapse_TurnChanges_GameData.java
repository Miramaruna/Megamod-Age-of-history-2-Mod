package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Timelapse_TurnChanges_GameData implements Serializable {
   public static final long serialVersionUID = 0L;
   public List<List<Timelapse_TurnChanges>> lTurnChanges = new ArrayList<>();

   Timelapse_TurnChanges_GameData() {
   }
}
