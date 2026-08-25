package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Timelapse_Stats_GameData implements Serializable {
   public static final long serialVersionUID = 0L;
   public List<List<Integer>> lProvinces = new ArrayList<>();
   public List<List<Integer>> lPopulation = new ArrayList<>();
   public List<List<Integer>> lRank = new ArrayList<>();
   public List<List<Integer>> lTechnologyLevel = new ArrayList<>();
   public List<List<Integer>> lPlayers_Income = new ArrayList<>();
   public List<List<Integer>> lPlayers_MilitarySpendings = new ArrayList<>();
   public List<List<Integer>> lPlayers_Balance = new ArrayList<>();
   public List<List<HistoryLog>> lHistory = new ArrayList<>();

   Timelapse_Stats_GameData() {
   }

   public final void addProvinces(List<Integer> tData) {
      this.lProvinces.add(tData);
      if (CFG.settingsManager.GRAPH_DATA_LIMIT_PROVINCES > 0 && this.lProvinces.size() > CFG.settingsManager.GRAPH_DATA_LIMIT_PROVINCES) {
         this.lProvinces.remove(0);
      }
   }

   public final void addPopulation(List<Integer> tData) {
      this.lPopulation.add(tData);
      if (CFG.settingsManager.GRAPH_DATA_LIMIT_POPULATION > 0 && this.lPopulation.size() > CFG.settingsManager.GRAPH_DATA_LIMIT_POPULATION) {
         this.lPopulation.remove(0);
      }
   }

   public final void addRank(List<Integer> tData) {
      this.lRank.add(tData);
      if (CFG.settingsManager.GRAPH_DATA_LIMIT_RANK > 0 && this.lRank.size() > CFG.settingsManager.GRAPH_DATA_LIMIT_RANK) {
         this.lRank.remove(0);
      }
   }

   public final void addTechLevel(List<Integer> tData) {
      this.lTechnologyLevel.add(tData);
      if (CFG.settingsManager.GRAPH_DATA_LIMIT_TECH_LEVEL > 0 && this.lTechnologyLevel.size() > CFG.settingsManager.GRAPH_DATA_LIMIT_TECH_LEVEL) {
         this.lTechnologyLevel.remove(0);
      }
   }
}
