package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class FormableCivs_GameData implements Serializable {
   public static final long serialVersionUID = 0L;
   public String sFormableCivTag = null;
   public List<String> sClaimants = new ArrayList<>();
   public List<Integer> lProvinces = new ArrayList<>();
   public int iCapitalProvinceID = -1;

   FormableCivs_GameData() {
   }

   public final void addClaimant(String nTag) {
      if (this.sFormableCivTag == null || !this.sFormableCivTag.equals(nTag)) {
         for (int i = 0; i < this.sClaimants.size(); i++) {
            if (this.sClaimants.get(i).equals(nTag)) {
               return;
            }
         }

         this.sClaimants.add(nTag);
      }
   }

   public final void removeClaimant(int i) {
      this.sClaimants.remove(i);
   }

   public final String getClaimant(int i) {
      return this.sClaimants.get(i);
   }

   public final int getClaimantsSize() {
      return this.sClaimants.size();
   }

   public final void addProvince(int nProvince) {
      this.lProvinces.add(nProvince);
   }

   public final int getProvinceID(int i) {
      return this.lProvinces.get(i);
   }

   public final int getProvincesSize() {
      return this.lProvinces.size();
   }

   public final void clearProvinces() {
      this.lProvinces.clear();
   }

   public final void setCapitalProvinceID(int iCapitalProvinceID) {
      this.iCapitalProvinceID = iCapitalProvinceID;
   }

   public final int getCapitalProvinceID() {
      return this.iCapitalProvinceID;
   }

   public final String getFormableCivTag() {
      return this.sFormableCivTag;
   }

   public final void setFormableCivTag(String sFormableCivTag) {
      this.sFormableCivTag = sFormableCivTag;
   }
}
