package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Union_GameData implements Serializable {
   public static final long serialVersionUID = 0L;
   public String lCreateCivTag = "";
   public List<String> lCivsTags = new ArrayList<>();

   Union_GameData() {
   }
}
