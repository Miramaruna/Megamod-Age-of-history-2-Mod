package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;

public class Province_Population_SupportGovernment implements Serializable {
   public static final long serialVersionUID = 0L;
   public int iIdeologyID;
   public int iPopulation;

   public Province_Population_SupportGovernment(int iIdeologyID, int iPopulation) {
      this.iIdeologyID = iIdeologyID;
      this.iPopulation = iPopulation;
   }

   public final int getIdeologyID() {
      return this.iIdeologyID;
   }

   public final int getPopulation() {
      return this.iPopulation;
   }

   public final void setPopulaton(int iPopulation) {
      this.iPopulation = iPopulation;
   }
}
