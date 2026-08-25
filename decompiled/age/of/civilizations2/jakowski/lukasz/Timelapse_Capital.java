package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;

public class Timelapse_Capital implements Serializable {
   public static final long serialVersionUID = 0L;
   public int iSinceTurnID = 1;
   public int iProvinceID;

   public Timelapse_Capital(int iProvinceID, int iSinceTurnID) {
      this.iProvinceID = iProvinceID;
      this.iSinceTurnID = iSinceTurnID;
   }
}
