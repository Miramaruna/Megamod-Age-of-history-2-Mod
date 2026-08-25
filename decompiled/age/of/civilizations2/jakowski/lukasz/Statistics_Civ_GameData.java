package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;

public class Statistics_Civ_GameData implements Serializable {
   public static final long serialVersionUID = 0L;
   public String sTag = "";
   public int iGamesWon = 0;
   public int iConqueredProvinces = 0;
   public int iTurns = 0;
   public int iRecruitedArmy = 0;
   public int iLargestArmy = 0;
   public int iLargestPopulation = 0;
   public int iBiggestEconomy = 0;
   public int iBuiltForts = 0;
   public int iBuiltTowers = 0;
   public int iBuiltPorts = 0;
   public int iBuiltLibraries = 0;
   public int iBuiltSupplies = 0;
   public int iBuiltArmories = 0;
   public int iBuiltFarms = 0;
   public int iBuiltWorkshops = 0;

   public Statistics_Civ_GameData(String nTag) {
      this.sTag = nTag;
   }

   public final int getConqueredProvinces() {
      return this.iConqueredProvinces;
   }

   public final void setConqueredProvinces(int iConqueredProvinces) {
      this.iConqueredProvinces = iConqueredProvinces;
   }

   public final int getTurns() {
      return this.iTurns;
   }

   public final void setTurns(int iTurns) {
      this.iTurns = iTurns;
   }

   public final int getRecruitedArmy() {
      return this.iRecruitedArmy;
   }

   public final void setRecruitedArmy(int iRecruitedArmy) {
      this.iRecruitedArmy = iRecruitedArmy;
   }

   public final int getGamesWon() {
      return this.iGamesWon;
   }

   public final void setGamesWon(int iGamesWon) {
      this.iGamesWon = iGamesWon;
   }

   public final int getBiggestEconomy() {
      return this.iBiggestEconomy;
   }

   public final void setBiggestEconomy(int iBiggestEconomy) {
      this.iBiggestEconomy = iBiggestEconomy;
   }

   public final int getLargestPopulation() {
      return this.iLargestPopulation;
   }

   public final void setLargestPopulation(int iLargestPopulation) {
      this.iLargestPopulation = iLargestPopulation;
   }

   public final int getLargestArmy() {
      return this.iLargestArmy;
   }

   public final void setLargestArmy(int iLargestArmy) {
      this.iLargestArmy = iLargestArmy;
   }

   public final int getiBuiltArmories() {
      return this.iBuiltArmories;
   }

   public final void setiBuiltArmories(int iBuiltArmories) {
      this.iBuiltArmories = iBuiltArmories;
   }

   public final int getiBuiltFarms() {
      return this.iBuiltFarms;
   }

   public final void setiBuiltFarms(int iBuiltFarms) {
      this.iBuiltFarms = iBuiltFarms;
   }

   public final int getiBuiltWorkshops() {
      return this.iBuiltWorkshops;
   }

   public final void setiBuiltWorkshops(int iBuiltWorkshops) {
      this.iBuiltWorkshops = iBuiltWorkshops;
   }

   public final int getiBuiltSupplies() {
      return this.iBuiltSupplies;
   }

   public final void setiBuiltSupplies(int iBuiltSupplies) {
      this.iBuiltSupplies = iBuiltSupplies;
   }

   public final int getiBuiltPorts() {
      return this.iBuiltPorts;
   }

   public final void setiBuiltPorts(int iBuiltPorts) {
      this.iBuiltPorts = iBuiltPorts;
   }

   public final int getiBuiltTowers() {
      return this.iBuiltTowers;
   }

   public final void setiBuiltTowers(int iBuiltTowers) {
      this.iBuiltTowers = iBuiltTowers;
   }

   public final int getiBuiltForts() {
      return this.iBuiltForts;
   }

   public final void setiBuiltForts(int iBuiltForts) {
      this.iBuiltForts = iBuiltForts;
   }

   public final int getiBuiltLibraries() {
      return this.iBuiltLibraries;
   }

   public final void setiBuiltLibraries(int iBuiltLibraries) {
      this.iBuiltLibraries = iBuiltLibraries;
   }
}
