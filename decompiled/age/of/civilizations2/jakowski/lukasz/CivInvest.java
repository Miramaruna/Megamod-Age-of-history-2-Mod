package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;

public class CivInvest implements Serializable {
   public static final long serialVersionUID = 0L;
   public int iProvinceID;
   public int iTurnsLeft;
   public int iEconomyLeft;
   public int iEconomyPerTurn;

   public CivInvest(int iProvinceID, int iTurnsLeft, int iEconomyLeft, int iEconomyPerTurn) {
      this.iProvinceID = iProvinceID;
      this.iTurnsLeft = iTurnsLeft;
      this.iEconomyLeft = iEconomyLeft;
      this.iEconomyPerTurn = iEconomyPerTurn;
   }
}
