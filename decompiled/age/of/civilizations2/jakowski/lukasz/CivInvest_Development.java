package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;

public class CivInvest_Development implements Serializable {
   public static final long serialVersionUID = 0L;
   public int iProvinceID;
   public int iTurnsLeft;
   public float iDevelopemntLeft;
   public float iDevelopemntPerTurn;

   public CivInvest_Development(int iProvinceID, int iTurnsLeft, float iDevelopemntLeft, float iDevelopemntPerTurn) {
      this.iProvinceID = iProvinceID;
      this.iTurnsLeft = iTurnsLeft;
      this.iDevelopemntLeft = iDevelopemntLeft;
      this.iDevelopemntPerTurn = iDevelopemntPerTurn;
   }
}
