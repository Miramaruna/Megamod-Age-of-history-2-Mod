package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class WastelandMap_GameData implements Serializable {
   public static final long serialVersionUID = 0L;
   public String sName = "";
   public List<Integer> lWastelandProvincesIDs = new ArrayList<>();

   public final void generateData() {
      if (this.lWastelandProvincesIDs != null) {
         this.lWastelandProvincesIDs.clear();
      } else {
         this.lWastelandProvincesIDs = new ArrayList<>();
      }

      for (int i = 0; i < CFG.game.getProvincesSize(); i++) {
         if (CFG.game.getProvince(i).getWasteland() >= 0) {
            this.lWastelandProvincesIDs.add(i);
         }
      }
   }

   public final String getName() {
      return this.sName;
   }

   public final void setName(String sName) {
      this.sName = sName;
   }

   public final int getWastelandProvincesSize() {
      return this.lWastelandProvincesIDs.size();
   }

   public final int getWastelandProvinceID(int i) {
      return this.lWastelandProvincesIDs.get(i);
   }
}
