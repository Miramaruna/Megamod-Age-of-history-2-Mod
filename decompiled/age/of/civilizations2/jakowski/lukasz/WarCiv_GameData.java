package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;

public class WarCiv_GameData implements Serializable {
   public static final long serialVersionUID = 0L;
   public int iCivID = 0;
   public int iCasualties = 0;
   public int iCivilianDeaths = 0;
   public int iMilitaryDefendersPoints = 0;
   public static int iMilitaryAttackPoints = 0;
   public int iEconomicLosses = 0;
   public int iConqueredProvinces = 0;

   public WarCiv_GameData(int nCivID) {
      this.iCivID = nCivID;
   }

   public final int getCivID() {
      return this.iCivID;
   }

   public final void setCivID(int iCivID) {
      this.iCivID = iCivID;
   }

   public final int getCasualties() {
      return this.iCasualties;
   }

   public final void addCasualties(int nCasualties) {
      this.iCasualties += nCasualties;
   }

   public final int getCivilianDeaths() {
      return this.iCivilianDeaths;
   }

   public final void addMilitaryDefendersPoints(int iMilitaryDefendersPoints) {
      this.iMilitaryDefendersPoints += iMilitaryDefendersPoints;
   }

   public final void addMilitaryAttackPoints(int iMilitaryAttackPoints) {
      WarCiv_GameData.iMilitaryAttackPoints += iMilitaryAttackPoints;
   }

   public final int getMilitaryPoints() {
      return this.iMilitaryDefendersPoints + iMilitaryAttackPoints;
   }

   public final void addCivilianDeaths(int nCivilianDeaths) {
      this.iCivilianDeaths += nCivilianDeaths;
   }

   public final int getEconomicLosses() {
      return this.iEconomicLosses;
   }

   public final void addEconomicLosses(int nEconomicLosses) {
      this.iEconomicLosses += nEconomicLosses;
   }

   public final int getConqueredProvinces() {
      return this.iConqueredProvinces;
   }

   public final void setConqueredProvinces(int iConqueredProvinces) {
      this.iConqueredProvinces = iConqueredProvinces;
   }

   public final void addConqueredProvinces() {
      this.iConqueredProvinces++;
   }
}
