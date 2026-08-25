package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;

public class Province_Population_Nationalities implements Serializable {
   public static final long serialVersionUID = 0L;
   public int iCivID;
   public int iPopulation;

   public Province_Population_Nationalities(int iCivID, int iPopulation) {
      this.iCivID = iCivID;
      this.iPopulation = iPopulation;
   }

   public final int getCivID() {
      return this.iCivID;
   }

   public final int getPopulation() {
      return this.iPopulation;
   }

   public final void setPopulaton(int iPopulation) {
      this.iPopulation = iPopulation;
   }
}
