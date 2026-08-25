package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Province_Cores_GameData implements Serializable {
   public static final long serialVersionUID = 0L;
   public List<Province_Cores_Provinces_GameData> lProvinces = new ArrayList<>();

   Province_Cores_GameData() {
   }

   public final int getProvincesSize() {
      return this.lProvinces.size();
   }

   public final void addCore(int nProvinceID, int nCivID) {
      this.addCore(nProvinceID, nCivID, 100);
   }

   public final void addCore(int nProvinceID, int nCivID, int nPerc) {
      if (nCivID != 0) {
         int iSize = this.lProvinces.size();

         for (int i = 0; i < iSize; i++) {
            if (this.lProvinces.get(i).iProvinceID == nProvinceID) {
               this.lProvinces.get(i).addCore(nCivID, nPerc);
               return;
            }
         }

         this.lProvinces.add(new Province_Cores_Provinces_GameData(nProvinceID, nCivID, 100));
      }
   }

   public final void removeCore(int nProvinceID, int nCivID) {
      int iSize = this.lProvinces.size();

      for (int i = 0; i < iSize; i++) {
         if (this.lProvinces.get(i).iProvinceID == nProvinceID) {
            this.lProvinces.get(i).removeCore(nCivID);
            return;
         }
      }
   }

   public final void updatePercOfPopulation(int nProvinceID, int nCivID, int nPerc) {
      for (int i = 0; i < this.lProvinces.size(); i++) {
         if (this.lProvinces.get(i).iProvinceID == nProvinceID) {
            this.lProvinces.get(i).updateCorePercOfPopulation(nCivID, nPerc);
            return;
         }
      }

      this.addCore(nProvinceID, nCivID, nPerc);

      for (int var5 = 0; var5 < this.lProvinces.size(); var5++) {
         if (this.lProvinces.get(var5).iProvinceID == nProvinceID) {
            this.lProvinces.get(var5).updateCorePercOfPopulation(nCivID, nPerc);
            return;
         }
      }
   }

   public final void updateAfterRemove(int nRemovedCivID) {
      for (int i = 0; i < this.lProvinces.size(); i++) {
         if (CFG.game.getProvince(this.lProvinces.get(i).iProvinceID).getCivID() == 0) {
            this.lProvinces.remove(i--);
         } else {
            this.lProvinces.get(i).updateAfterRemove(nRemovedCivID);
            if (this.lProvinces.get(i).lCores.size() < 1) {
               this.lProvinces.remove(i--);
            }
         }
      }
   }

   public final float getPercOfPop(int nProvinceID, int nCivID) {
      int iSize = this.lProvinces.size();

      for (int i = 0; i < iSize; i++) {
         if (this.lProvinces.get(i).iProvinceID == nProvinceID) {
            return this.lProvinces.get(i).getPercOfPop(nCivID);
         }
      }

      return 1.0F;
   }

   public final void clearCoresData(int nProvinceID) {
      for (int i = 0; i < this.lProvinces.size(); i++) {
         if (this.lProvinces.get(i).iProvinceID == nProvinceID) {
            this.lProvinces.remove(i);
            return;
         }
      }
   }

   public final void clearUselessData() {
      for (int i = 0; i < this.lProvinces.size(); i++) {
         if (this.lProvinces.get(i).lCores.size() < 2) {
            this.lProvinces.remove(i--);
         }
      }
   }
}
