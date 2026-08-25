package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Province_Cores_Provinces_GameData implements Serializable {
   public static final long serialVersionUID = 0L;
   public int iProvinceID;
   public List<Province_Cores_Civs_GameData> lCores = new ArrayList<>();

   public Province_Cores_Provinces_GameData(int nProvinceID, int nCivID, int nPerc) {
      this.iProvinceID = nProvinceID;
      this.addCore(nCivID, nPerc);
   }

   public final void addCore(int nCivID, int nPerc) {
      for (int i = 0; i < this.lCores.size(); i++) {
         if (this.lCores.get(i).iCivID == nCivID) {
            return;
         }
      }

      this.lCores.add(new Province_Cores_Civs_GameData(nCivID, Math.min(nPerc, 100 - this.lCores.size())));
      this.updateCorePercOfPopulation(nCivID, nPerc);
   }

   public final void updateCorePercOfPopulation(int nCivID, int nPerc) {
      if (this.lCores.size() > 1) {
         for (int i = 0; i < this.lCores.size(); i++) {
            if (this.lCores.get(i).iCivID == nCivID) {
               this.lCores.get(i).setPerc(nPerc / 100.0F);
               break;
            }
         }

         float tempPercAll = 0.0F;

         for (int ix = 0; ix < this.lCores.size(); ix++) {
            tempPercAll += this.lCores.get(ix).fPercPop;
         }

         if (tempPercAll > 1.0F) {
            float tempTotal = 0.0F;
            float tempCivTotal = 0.0F;

            for (int i2 = 0; i2 < this.lCores.size(); i2++) {
               if (this.lCores.get(i2).iCivID != nCivID) {
                  tempTotal += this.lCores.get(i2).fPercPop;
               } else {
                  this.lCores.get(i2).setPerc(Math.min(nPerc, 100 - this.lCores.size()) / 100.0F);
                  tempCivTotal = this.lCores.get(i2).fPercPop;
               }
            }

            float tDiff = 1.0F - Math.min(1.0F, tempCivTotal);

            for (int ix = 0; ix < this.lCores.size(); ix++) {
               if (this.lCores.get(ix).iCivID != nCivID) {
                  this.lCores.get(ix).setPerc(tDiff * this.lCores.get(ix).fPercPop / tempTotal);
               }
            }

            tempTotal = 0.0F;

            for (int var10 = 0; var10 < this.lCores.size(); var10++) {
               tempTotal += this.lCores.get(var10).fPercPop;
            }

            if ((tempTotal = 1.0F - tempTotal) > 0.0F) {
               this.lCores.get(this.lCores.size() - 1).setPerc(this.lCores.get(this.lCores.size() - 1).fPercPop + tempTotal);
            }
         }
      } else if (this.lCores.size() > 0) {
         if (nPerc > 100) {
            this.lCores.get(0).setPerc(1.0F);
         } else if (nPerc < 1) {
            this.lCores.get(0).setPerc(0.01F);
         } else {
            this.lCores.get(0).setPerc(nPerc / 100.0F);
         }
      }
   }

   public final void removeCore(int nCivID) {
      for (int i = 0; i < this.lCores.size(); i++) {
         if (this.lCores.get(i).iCivID == nCivID) {
            this.lCores.remove(i);
            return;
         }
      }
   }

   public final float getPercOfPop(int nCivID) {
      for (int i = 0; i < this.lCores.size(); i++) {
         if (this.lCores.get(i).iCivID == nCivID) {
            return this.lCores.get(i).fPercPop;
         }
      }

      return 0.0F;
   }

   public final void updateAfterRemove(int nRemovedCivID) {
      for (int i = 0; i < this.lCores.size(); i++) {
         if (this.lCores.get(i).iCivID > nRemovedCivID) {
            this.lCores.get(i).iCivID--;
         } else if (this.lCores.get(i).iCivID == nRemovedCivID) {
            this.lCores.remove(i--);
         }
      }
   }
}
