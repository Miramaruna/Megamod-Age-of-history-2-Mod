package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Save_GameData_4 implements Serializable {
   public static final long serialVersionUID = 0L;
   public List<Save_Provinces_GameData> lProvincesData = new ArrayList<>();

   public final void buildData() {
      for (int i = 0; i < CFG.game.getProvincesSize(); i++) {
         this.lProvincesData.add(CFG.game.getProvince(i).saveProvinceData);
      }
   }
}
