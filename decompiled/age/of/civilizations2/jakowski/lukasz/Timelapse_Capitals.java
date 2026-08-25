package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Timelapse_Capitals implements Serializable {
   public static final long serialVersionUID = 0L;
   public List<Timelapse_Capital> lCapitals = new ArrayList<>();

   public Timelapse_Capitals(int iProvinceID, int iSinceTurnID) {
      this.lCapitals.add(new Timelapse_Capital(iProvinceID, iSinceTurnID));
   }

   public void updateCapital(int iProvinceID, int iSinceTurnID) {
      try {
         if (this.lCapitals.get(this.lCapitals.size() - 1).iProvinceID != iProvinceID) {
            this.lCapitals.add(new Timelapse_Capital(iProvinceID, iSinceTurnID));
         }
      } catch (IndexOutOfBoundsException var4) {
         this.lCapitals.add(new Timelapse_Capital(iProvinceID, iSinceTurnID));
      }
   }

   public int getCapitalID(int iTurnID) {
      for (int i = 0; i < this.lCapitals.size() - 1; i++) {
         if (this.lCapitals.get(i).iSinceTurnID <= iTurnID && this.lCapitals.get(i + 1).iSinceTurnID > iTurnID) {
            return this.lCapitals.get(i).iProvinceID;
         }
      }

      return this.lCapitals.get(this.lCapitals.size() - 1).iProvinceID;
   }
}
