package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Unions_GameData implements Serializable {
   public static final long serialVersionUID = 0L;
   public List<Union_GameData> lUnions = new ArrayList<>();

   Unions_GameData() {
   }
}
