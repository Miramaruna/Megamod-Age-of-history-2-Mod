package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Timelapse_GameData implements Serializable {
   public static final long serialVersionUID = 0L;
   public List<Timelapse_Capitals> lCivsCapitals = new ArrayList<>();

   Timelapse_GameData() {
   }
}
