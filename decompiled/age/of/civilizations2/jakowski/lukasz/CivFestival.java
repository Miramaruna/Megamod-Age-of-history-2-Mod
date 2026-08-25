package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;

public class CivFestival implements Serializable {
   public static final long serialVersionUID = 0L;
   public int iProvinceID;
   public int iTurnsLeft;

   public CivFestival(int iProvinceID, int iTurnsLeft) {
      this.iProvinceID = iProvinceID;
      this.iTurnsLeft = iTurnsLeft;
   }
}
