package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Province_Population implements Serializable {
   public static final long serialVersionUID = 0L;
   public int iPopulation = 0;
   public List<Province_Population_Nationalities> lNationalities = new ArrayList<>();
   public int iNationalitiesSize = 0;
   public List<Province_Population_SupportGovernment> lSupportGovernment = new ArrayList<>();
   public int iSupportGovernment = 0;

   Province_Population() {
   }

   public final void updatePopulationOfProvince() {
      for (int i = 0; i < this.iNationalitiesSize; i++) {
         this.iPopulation = this.iPopulation + this.lNationalities.get(i).getPopulation();
      }
   }

   public final int getPopulationID(int nID) {
      return this.lNationalities.get(nID).getPopulation();
   }

   public Province_Population_Nationalities getNational(int n) {
      return this.lNationalities.get(n);
   }

   public final int getPopulationOfCivID(int nCivID) {
      for (int i = 0; i < this.iNationalitiesSize; i++) {
         if (this.lNationalities.get(i).getCivID() == nCivID) {
            return this.lNationalities.get(i).getPopulation();
         }
      }

      return 0;
   }

   public final boolean setPopulationOfCivID(int nCivID, int nPopulation) {
      for (int i = 0; i < this.iNationalitiesSize; i++) {
         if (this.lNationalities.get(i).getCivID() == nCivID) {
            if (nPopulation <= 0) {
               if (this.lNationalities.size() > 1) {
                  this.iPopulation = this.iPopulation - this.lNationalities.get(i).getPopulation();
                  this.lNationalities.remove(i);
                  this.iNationalitiesSize = this.lNationalities.size();
                  return true;
               }

               this.lNationalities.get(i).setPopulaton(10);
               this.iPopulation = 10;
            } else {
               this.iPopulation = this.iPopulation - this.lNationalities.get(i).getPopulation();
               this.iPopulation += nPopulation;
               this.lNationalities.get(i).setPopulaton(nPopulation);
            }

            return false;
         }
      }

      if (nPopulation > 0) {
         this.lNationalities.add(new Province_Population_Nationalities(nCivID, nPopulation));
         this.iPopulation += nPopulation;
         this.iNationalitiesSize = this.lNationalities.size();
      }

      return false;
   }

   public final void clearData() {
      this.iPopulation = 0;
      this.lNationalities.clear();
      this.iNationalitiesSize = 0;
      this.iSupportGovernment = 0;
      this.lSupportGovernment.clear();
   }

   public final int getPopulation() {
      return this.iPopulation;
   }

   public final int getNationalitiesSize() {
      return this.iNationalitiesSize;
   }

   public final int getCivID(int i) {
      return this.lNationalities.get(i).getCivID();
   }

   public final void updateSupportGovernmentOfProvince() {
      for (int i = 0; i < this.iSupportGovernment; i++) {
         this.iPopulation = this.iPopulation + this.lSupportGovernment.get(i).getPopulation();
      }
   }

   public final int getSupportGovernmentSize() {
      return this.iSupportGovernment;
   }

   public final int getIdeologyID(int i) {
      return this.lSupportGovernment.get(i).getIdeologyID();
   }

   public final int getPopulationGovernmentID(int nID) {
      return this.lSupportGovernment.get(nID).getPopulation();
   }

   public Province_Population_SupportGovernment getSupportGovernment(int n) {
      return this.lSupportGovernment.get(n);
   }

   public final int getPopulationOfIdeologyID(int nCivID) {
      for (int i = 0; i < this.iSupportGovernment; i++) {
         if (this.lSupportGovernment.get(i).getIdeologyID() == nCivID) {
            return this.lSupportGovernment.get(i).getPopulation();
         }
      }

      return 0;
   }

   public final boolean setPopulationOfIdeologyID(int nIdeologyID, int nPopulation) {
      for (int i = 0; i < this.iSupportGovernment; i++) {
         if (this.lSupportGovernment.get(i).getIdeologyID() == nIdeologyID) {
            if (nPopulation <= 0) {
               if (this.lSupportGovernment.size() > 1) {
                  this.iPopulation = this.iPopulation - this.lSupportGovernment.get(i).getPopulation();
                  this.lSupportGovernment.remove(i);
                  this.iSupportGovernment = this.lSupportGovernment.size();
                  return true;
               }

               this.lSupportGovernment.get(i).setPopulaton(10);
               this.iPopulation = 10;
            } else {
               this.iPopulation = this.iPopulation - this.lSupportGovernment.get(i).getPopulation();
               this.iPopulation += nPopulation;
               this.lSupportGovernment.get(i).setPopulaton(nPopulation);
            }

            return false;
         }
      }

      if (nPopulation > 0) {
         this.lSupportGovernment.add(new Province_Population_SupportGovernment(nIdeologyID, nPopulation));
         this.iPopulation += nPopulation;
         this.iSupportGovernment = this.lSupportGovernment.size();
      }

      return false;
   }
}
