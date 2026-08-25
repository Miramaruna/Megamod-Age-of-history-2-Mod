package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Events_GameData implements Serializable {
   public static final long serialVersionUID = 0L;
   public List<Event_GameData> lEvents = new ArrayList<>();
   public int iEventsSize = 0;

   Events_GameData() {
   }
}
