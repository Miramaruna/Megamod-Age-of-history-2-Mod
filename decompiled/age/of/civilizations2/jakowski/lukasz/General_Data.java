package age.of.civilizations2.jakowski.lukasz;

import java.util.ArrayList;
import java.util.List;

public class General_Data {
   public int iCivID;
   public String sName;
   public int iLevel = 1;
   public List<Integer> lProvinces = new ArrayList<>();

   public General_Data(int nCivID, String nName) {
      this.iCivID = nCivID;
      this.sName = nName;
   }

   public final float getBonus() {
      return 0.15F + 0.05F * (float)(this.iLevel - 1);
   }
}
