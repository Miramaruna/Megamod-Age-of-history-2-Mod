package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;

public class TradeRoute_GameData implements Serializable {
   public static final long serialVersionUID = 0L;
   public String sFromTagID;
   public String sToTagID;
   public int iAgeFoundID;

   public final String getFromTagID() {
      return this.sFromTagID;
   }

   public final void setFromTagID(String sFromTagID) {
      this.sFromTagID = sFromTagID;
   }

   public final String getToTagID() {
      return this.sToTagID;
   }

   public final void setToTagID(String sToTagID) {
      this.sToTagID = sToTagID;
   }

   public final int getAgeFoundID() {
      return this.iAgeFoundID;
   }

   public final void setAgeFoundID(int iAgeFoundID) {
      this.iAgeFoundID = iAgeFoundID;
   }
}
