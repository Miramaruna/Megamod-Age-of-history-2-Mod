package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Save_GameData implements Serializable {
   public static final long serialVersionUID = 0L;
   public List<Save_Civ_GameData> lCivsData = new ArrayList<>();
   public List<Save_CivDiplo_GameData> lCivsDiploData = new ArrayList<>();
   public List<Save_Provinces_GameData> lProvincesData = new ArrayList<>();
   public List<Save_Player_GameData> lPlayers = new ArrayList<>();
   public List<Alliance> lAlliances = new ArrayList<>();
   public List<War_GameData> lWars = new ArrayList<>();
   public List<PeaceTreaty_GameData_MessageData> lPeaceTreaties = new ArrayList<>();
   public List<Plague_GameData> lPlagues_INGAME = new ArrayList<>();
   public HolyRomanEmpire_GameData holyRomanEmpire_GameData;
   public Events_GameData eventsGameData;
   public int iTurnID;
   public int TURNS_SINCE_LAST_WAR;
   public int iDay;
   public int iMonth;
   public int iYear;
   public int DIFFICULTY;
   public float GAME_SPEED;
   public int FOG_OF_WAR;
   public boolean SPECTATOR_MODE;
   public boolean SANDBOX_MODE;
   public boolean FREEPLAY_MODE;
   public boolean DISEASES;
   public float LOSSESINWAR;
   public int SURRENDERLIMIT;
   public float POWERREBELS;
   public boolean MANPOWER;
   public float fTroopBreakthroughChance;
   public boolean NO_LIBERITY;
   public int AI_CREATING_VASSALS;
   public int AI_CREATING_ALLIANCE;
   public boolean ENABLE_COLONIZATION;
   public boolean ENABLE_COLONIZATION_NEUTRAL_PROVINCES = false;
   public float COLONIZATION_TECH_LEVEL = 0.8F;
   public int STARTING_POPULATION;
   public int STARTING_ECONOMY;
   public float POPULATION_GROWTH_RATE_MODIFIER;
   public float ECONOMY_GROWTH_RATE_MODIFIER;
   public float DISEASES_DEATH_REATE_MODIFIER;
   public int VICTORY_CONTROL_PROVINCES_PERC;
   public int VICTORY_LIMIT_OF_TURNS;
   public float VICTORY_TECHNOLOGY;
   public String sActiveScenarioTag = "";

   Save_GameData() {
   }

   public final void buildData() {
      this.iTurnID = Game_Calendar.TURN_ID;
      this.TURNS_SINCE_LAST_WAR = Game_Calendar.TURNS_SINCE_LAST_WAR;
      this.iDay = Game_Calendar.currentDay;
      this.iMonth = Game_Calendar.currentMonth;
      this.iYear = Game_Calendar.currentYear;
      this.GAME_SPEED = Game_Calendar.GAME_SPEED;
      this.SANDBOX_MODE = CFG.SANDBOX_MODE;
      this.FREEPLAY_MODE = CFG.FREEPLAY_MODE;
      this.LOSSESINWAR = Game_Calendar.LOSSESINWAR;
      this.SURRENDERLIMIT = Game_Calendar.SURRENDERLIMIT;
      this.POWERREBELS = Game_Calendar.POWERREBELS;
      this.MANPOWER = CFG.MANPOWER_SYSTEM;
      this.DISEASES = CFG.DISEASES;
      this.fTroopBreakthroughChance = Game_Action.fTroopBreakthroughChance;
      this.NO_LIBERITY = CFG.NO_LIBERITY;
      this.AI_CREATING_VASSALS = CFG.AI_CREATING_VASSALS;
      this.AI_CREATING_ALLIANCE = CFG.AI_CREATING_ALLIANCE;
      this.ENABLE_COLONIZATION = Game_Calendar.ENABLE_COLONIZATION;
      this.ENABLE_COLONIZATION_NEUTRAL_PROVINCES = Game_Calendar.ENABLE_COLONIZATION_NEUTRAL_PROVINCES;
      this.COLONIZATION_TECH_LEVEL = Game_Calendar.COLONIZATION_TECH_LEVEL;
      this.STARTING_POPULATION = CFG.game.getGameScenarios().getScenario_StartingPopulation();
      this.STARTING_ECONOMY = CFG.game.getGameScenarios().getScenario_StartingEconomy();
      this.POPULATION_GROWTH_RATE_MODIFIER = CFG.game.getGameScenarios().getScenario_PopulationGrowthRate_Modifier();
      this.ECONOMY_GROWTH_RATE_MODIFIER = CFG.game.getGameScenarios().getScenario_EconomyGrowthRate_Modifier();
      this.DISEASES_DEATH_REATE_MODIFIER = CFG.game.getGameScenarios().getScenario_DiseasesDeathRate_Modifier();
      this.VICTORY_CONTROL_PROVINCES_PERC = VicotryManager.VICTORY_CONTROL_PROVINCES_PERC;
      this.VICTORY_LIMIT_OF_TURNS = VicotryManager.VICTORY_LIMIT_OF_TURNS;
      this.VICTORY_TECHNOLOGY = VicotryManager.VICTORY_TECHNOLOGY;
      this.FOG_OF_WAR = CFG.FOG_OF_WAR;
      this.SPECTATOR_MODE = CFG.SPECTATOR_MODE;
      this.DIFFICULTY = CFG.DIFFICULTY;

      for (int i = 1; i < CFG.game.getCivsSize(); i++) {
         this.lCivsData.add(CFG.game.getCiv(i).civGameData);
         this.lCivsDiploData.add(new Save_CivDiplo_GameData(i));
      }

      for (int var4 = 0; var4 < CFG.game.getProvincesSize(); var4++) {
         this.lProvincesData.add(CFG.game.getProvince(var4).saveProvinceData);
      }

      for (int var5 = 0; var5 < CFG.game.getPlayersSize(); var5++) {
         this.lPlayers.add(CFG.game.getPlayer(var5).savePlayer);
      }

      for (int var6 = 0; var6 < CFG.game.getAlliancesSize(); var6++) {
         this.lAlliances.add(CFG.game.getAlliance(var6));
      }

      for (int var7 = 0; var7 < CFG.game.getWarsSize(); var7++) {
         this.lWars.add(CFG.game.getWar(var7));
      }

      for (int var8 = 0; var8 < CFG.game.lPeaceTreaties.size(); var8++) {
         this.lPeaceTreaties.add(CFG.game.lPeaceTreaties.get(var8));
      }

      for (int var9 = 0; var9 < CFG.plagueManager.lPlagues_INGAME.size(); var9++) {
         this.lPlagues_INGAME.add(CFG.plagueManager.lPlagues_INGAME.get(var9));
      }

      this.holyRomanEmpire_GameData = CFG.holyRomanEmpire_Manager.getHRE();
      this.eventsGameData = CFG.eventsManager.eventsGD;
      if (this.sActiveScenarioTag.length() <= 0) {
         if (CFG.game.getGameScenarios().sActiveScenarioTag.length() <= 0) {
            try {
               CFG.game.getGameScenarios().sActiveScenarioTag = CFG.game.getGameScenarios().getScenarioTag(CFG.game.getScenarioID());
            } catch (IndexOutOfBoundsException var3) {
               if (CFG.LOGS) {
                  CFG.exceptionStack(var3);
               }
            }
         }

         this.sActiveScenarioTag = CFG.game.getGameScenarios().sActiveScenarioTag;
      }
   }
}
