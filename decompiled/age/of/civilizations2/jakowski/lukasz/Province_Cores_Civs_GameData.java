package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;

public class Province_Cores_Civs_GameData implements Serializable {
   public static final long serialVersionUID = 0L;
   public int iCivID;
   public float fPercPop;

   public Province_Cores_Civs_GameData(int nCivID, int nPerc) {
      this.iCivID = nCivID;
      this.fPercPop = nPerc / 100.0F;
   }

   public final void setPerc(float nPerc) {
      this.fPercPop = nPerc;
      if (this.fPercPop < 0.01F) {
         this.fPercPop = 0.01F;
      } else if (this.fPercPop > 1.0F) {
         this.fPercPop = 1.0F;
      }
   }
}
