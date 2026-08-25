package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CasusBelli implements Serializable {
   public static final long serialVersionUID = 0L;
   public boolean CasusBelliIf = false;
   public List<Integer> demandProvinces = new ArrayList<>();
   public List<Integer> demandLiberation = new ArrayList<>();
   public int numOfUntis = 0;

   CasusBelli() {
   }

   public boolean isLiberationDemanded(int nCivID) {
      for (int i = 0; i < this.demandLiberation.size(); i++) {
         if (this.demandLiberation.get(i) == nCivID) {
            return true;
         }
      }

      return false;
   }

   public void updateLiberationDemand(int nCivID) {
      for (int i = 0; i < this.demandLiberation.size(); i++) {
         if (this.demandLiberation.get(i) == nCivID) {
            this.demandLiberation.remove(i);
            return;
         }
      }

      this.demandLiberation.add(nCivID);
   }

   public final boolean canBeSend() {
      return this.CasusBelliIf || this.demandProvinces.size() > 0 || this.demandLiberation.size() > 0;
   }
}
