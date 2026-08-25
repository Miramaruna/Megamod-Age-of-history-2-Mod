package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Scenario_GameData_Province2 implements Serializable {
   public static final long serialVersionUID = 0L;
   public List<Integer> lProvinceOwners = null;

   Scenario_GameData_Province2() {
   }

   public final void buildProvinceOwners() {
      this.lProvinceOwners = new ArrayList<>();

      for (int i = 0; i < CFG.game.getProvincesSize(); i++) {
         this.lProvinceOwners.add(CFG.game.getProvince(i).getCivID());
      }
   }

   public final List<Integer> getProvinceOwners() {
      return this.lProvinceOwners;
   }
}
