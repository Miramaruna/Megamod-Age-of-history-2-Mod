package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Civilization_ServiceRibbon_GameData implements Serializable {
   public static final long serialVersionUID = 0L;
   public String sSRTAG;
   public List<Color_GameData> lColors = new ArrayList<>();

   Civilization_ServiceRibbon_GameData() {
   }

   public final String getSRTAG() {
      return this.sSRTAG;
   }

   public final void setSRTAG(String sSRTAG) {
      this.sSRTAG = sSRTAG;
   }

   public final List<Color_GameData> getColors() {
      return this.lColors;
   }

   public final Color_GameData getColor(int i) {
      return this.lColors.get(i);
   }
}
