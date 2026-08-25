package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.GdxRuntimeException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TimelapseManager {
   public Timelapse_GameData timelapseGameData = new Timelapse_GameData();
   public Timelapse_Owners_GameData timelapseOwnersGameData = new Timelapse_Owners_GameData();
   public Timelapse_TurnChanges_GameData timelapseTurnChanges = new Timelapse_TurnChanges_GameData();
   public Timelapse_Stats_GameData timelapseStatsGD = new Timelapse_Stats_GameData();
   public static int SOURCE = 0;
   public static final int[] TIME_REQUIRED_TO_ACTION = new int[]{1, 2000, 1500, 1000, 750, 500, 250};
   public static final int MAX_SPEED = 6;
   public static int SPEED = 1;
   public static boolean PAUSE = true;
   public static long TIME_PAST = 0L;
   public static long TIME_LAST_UPDATE = 0L;
   public List<Integer> timelineOwners = new ArrayList<>();
   public List<Boolean> timelineOwners_IsOccupied = new ArrayList<>();
   public List<Integer> timelineOwners_Capitals = new ArrayList<>();
   public Timelapse_TurnChanges_GameData timelineOwners_Changes = new Timelapse_TurnChanges_GameData();
   public int iTimelineTurnID = 0;

   TimelapseManager() {
   }

   public final void updateTime() {
      try {
         TIME_PAST = TIME_PAST + (System.currentTimeMillis() - TIME_LAST_UPDATE);
         TIME_LAST_UPDATE = System.currentTimeMillis();
         if (this.timePasted()) {
            TIME_PAST = 0L;
            this.loadNextTurn();
         }
      } catch (NullPointerException var2) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var2);
         }
      } catch (IndexOutOfBoundsException var3) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var3);
         }
      }
   }

   public final boolean timePasted() {
      return TIME_PAST > this.getRequiredTime();
   }

   public final int getRequiredTime() {
      return TIME_REQUIRED_TO_ACTION[SPEED];
   }

   public final float getTimePerc() {
      return Math.min((float)TIME_PAST / this.getRequiredTime(), 1.0F);
   }

   public final void resetTime() {
      TIME_PAST = 0L;
      TIME_LAST_UPDATE = System.currentTimeMillis();
   }

   public void pauseUnpause() {
      PAUSE = !PAUSE;
      if (!PAUSE) {
         if (this.iTimelineTurnID >= this.timelineOwners_Changes.lTurnChanges.size() - 1) {
            this.buildTimeline();
            PAUSE = false;
            if (CFG.menuManager.getInGame_Timeline()) {
               CFG.menuManager.getInGame_Timeline_UpdateLanguage();
            } else if (CFG.menuManager.getInVictory()) {
               CFG.menuManager.getInGame_Victory_UpdateLanguage();
            }
         }

         TIME_LAST_UPDATE = System.currentTimeMillis();
      }
   }

   public void updateSpeed(int nDiff) {
      float tempTimePastPerc = this.getTimePerc();
      if ((SPEED += nDiff) < 1) {
         SPEED = 1;
      } else if (SPEED > 6) {
         SPEED = 6;
      }

      TIME_PAST = (long)(TIME_REQUIRED_TO_ACTION[SPEED] * tempTimePastPerc);
   }

   public final void buildTimeline() {
      this.clearTimeline();
      this.iTimelineTurnID = 0;
      this.resetTime();
      PAUSE = true;
      if (CFG.FOG_OF_WAR == 2) {
         for (int i = 0; i < this.timelapseOwnersGameData.lProvinceOwnersAtBeginning.size(); i++) {
            if (CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetProvince(i)) {
               this.timelineOwners.add(this.timelapseOwnersGameData.lProvinceOwnersAtBeginning.get(i));
            } else {
               this.timelineOwners.add(0);
            }

            this.timelineOwners_IsOccupied.add(false);
         }

         for (int var14 = 0; var14 < this.timelapseGameData.lCivsCapitals.size(); var14++) {
            int tempCapital = this.timelapseGameData.lCivsCapitals.get(var14).getCapitalID(this.iTimelineTurnID + 1);
            if (tempCapital >= 0 && this.timelineOwners.get(tempCapital) == var14 + 1 && CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetCivilization(var14 + 1)) {
               this.timelineOwners_Capitals.add(tempCapital);
            } else {
               this.timelineOwners_Capitals.add(-1);
            }
         }
      } else {
         for (int i = 0; i < this.timelapseOwnersGameData.lProvinceOwnersAtBeginning.size(); i++) {
            this.timelineOwners.add(this.timelapseOwnersGameData.lProvinceOwnersAtBeginning.get(i));
            this.timelineOwners_IsOccupied.add(false);
         }

         for (int var16 = 0; var16 < this.timelapseGameData.lCivsCapitals.size(); var16++) {
            int tempCapital = this.timelapseGameData.lCivsCapitals.get(var16).getCapitalID(this.iTimelineTurnID + 1);
            if (tempCapital >= 0 && this.timelineOwners.get(tempCapital) == var16 + 1) {
               this.timelineOwners_Capitals.add(tempCapital);
            } else {
               this.timelineOwners_Capitals.add(-1);
            }
         }
      }

      if (SaveManager.saveTag != null) {
         int turnSavesID = 0;

         try {
            FileHandle fileReadData3 = null;
            fileReadData3 = CFG.readLocalFiles()
               ? Gdx.files.local("saves/games/" + CFG.map.getFile_ActiveMap_Path() + SaveManager.saveTag + "/TS/TURN/Age_of_Civilizations")
               : Gdx.files.internal("saves/games/" + CFG.map.getFile_ActiveMap_Path() + SaveManager.saveTag + "/TS/TURN/Age_of_Civilizations");
            String tRead = fileReadData3.readString();
            turnSavesID = Integer.parseInt(tRead) + 1;

            for (int i2 = 0; i2 < turnSavesID; i2++) {
               FileHandle fileReadData4 = null;
               fileReadData4 = CFG.readLocalFiles()
                  ? Gdx.files.local("saves/games/" + CFG.map.getFile_ActiveMap_Path() + SaveManager.saveTag + "/TS/TURN/" + SaveManager.saveTag + "_C_" + i2)
                  : Gdx.files
                     .internal("saves/games/" + CFG.map.getFile_ActiveMap_Path() + SaveManager.saveTag + "/TS/TURN/" + SaveManager.saveTag + "_C_" + i2);
               Timelapse_TurnChanges_GameData tempChangesData = (Timelapse_TurnChanges_GameData)CFG.deserialize(fileReadData4.readBytes());

               for (int j = 0; j < tempChangesData.lTurnChanges.size(); j++) {
                  this.timelineOwners_Changes.lTurnChanges.add(tempChangesData.lTurnChanges.get(j));
               }
            }
         } catch (GdxRuntimeException var10) {
            CFG.exceptionStack(var10);
         } catch (ClassNotFoundException var11) {
            CFG.exceptionStack(var11);
         } catch (IOException var12) {
            CFG.exceptionStack(var12);
         }
      }

      for (int j = 0; j < this.timelapseTurnChanges.lTurnChanges.size(); j++) {
         this.timelineOwners_Changes.lTurnChanges.add(this.timelapseTurnChanges.lTurnChanges.get(j));
      }
   }

   public final void clearTimeline() {
      this.timelineOwners.clear();
      this.timelineOwners_IsOccupied.clear();
      this.timelineOwners_Capitals.clear();
      this.timelineOwners_Changes.lTurnChanges.clear();
   }

   public final void clearTimeline_Statistics() {
      this.timelapseStatsGD.lProvinces.clear();
      this.timelapseStatsGD.lPopulation.clear();
      this.timelapseStatsGD.lRank.clear();
      this.timelapseStatsGD.lTechnologyLevel.clear();
      this.timelapseStatsGD.lPlayers_Income.clear();
      this.timelapseStatsGD.lPlayers_Balance.clear();
      this.timelapseStatsGD.lPlayers_MilitarySpendings.clear();
      this.timelapseStatsGD.lHistory = new ArrayList<>();
   }

   public final void loadNextTurn() {
      if (this.iTimelineTurnID < this.timelineOwners_Changes.lTurnChanges.size() - 1) {
         this.timelineOwners_Capitals.clear();
         if (CFG.FOG_OF_WAR == 2) {
            for (int i = 0; i < this.timelineOwners_Changes.lTurnChanges.get(this.iTimelineTurnID).size(); i++) {
               if (CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetProvince(this.timelineOwners_Changes.lTurnChanges.get(this.iTimelineTurnID).get(i).iProvinceID)) {
                  this.timelineOwners
                     .set(
                        this.timelineOwners_Changes.lTurnChanges.get(this.iTimelineTurnID).get(i).iProvinceID,
                        this.timelineOwners_Changes.lTurnChanges.get(this.iTimelineTurnID).get(i).iToCivID
                     );
                  this.timelineOwners_IsOccupied
                     .set(
                        this.timelineOwners_Changes.lTurnChanges.get(this.iTimelineTurnID).get(i).iProvinceID,
                        this.timelineOwners_Changes.lTurnChanges.get(this.iTimelineTurnID).get(i).isOccupied
                     );
               }
            }

            for (int var5 = 0; var5 < this.timelapseGameData.lCivsCapitals.size(); var5++) {
               int tempCapital = this.timelapseGameData.lCivsCapitals.get(var5).getCapitalID(this.iTimelineTurnID + 1);
               if (tempCapital >= 0 && this.timelineOwners.get(tempCapital) == var5 + 1 && CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetCivilization(var5 + 1)) {
                  this.timelineOwners_Capitals.add(tempCapital);
               } else {
                  this.timelineOwners_Capitals.add(-1);
               }
            }
         } else {
            for (int ix = 0; ix < this.timelineOwners_Changes.lTurnChanges.get(this.iTimelineTurnID).size(); ix++) {
               this.timelineOwners
                  .set(
                     this.timelineOwners_Changes.lTurnChanges.get(this.iTimelineTurnID).get(ix).iProvinceID,
                     this.timelineOwners_Changes.lTurnChanges.get(this.iTimelineTurnID).get(ix).iToCivID
                  );
               this.timelineOwners_IsOccupied
                  .set(
                     this.timelineOwners_Changes.lTurnChanges.get(this.iTimelineTurnID).get(ix).iProvinceID,
                     this.timelineOwners_Changes.lTurnChanges.get(this.iTimelineTurnID).get(ix).isOccupied
                  );
            }

            for (int var3 = 0; var3 < this.timelapseGameData.lCivsCapitals.size(); var3++) {
               int tempCapital = this.timelapseGameData.lCivsCapitals.get(var3).getCapitalID(this.iTimelineTurnID + 1);
               if (tempCapital >= 0 && this.timelineOwners.get(tempCapital) == var3 + 1) {
                  this.timelineOwners_Capitals.add(tempCapital);
               } else {
                  this.timelineOwners_Capitals.add(-1);
               }
            }
         }

         this.iTimelineTurnID++;
         if (CFG.menuManager.getInGame_Timeline()) {
            CFG.menuManager.getInGame_Timeline_UpdateLanguage();
         } else if (CFG.menuManager.getInVictory()) {
            CFG.menuManager.getInGame_Victory_UpdateLanguage();
            CFG.map.getMapCoordinates().centerToCivilizationBox_Timeline(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), true);
         }
      } else {
         PAUSE = true;
      }
   }

   public final void newGame() {
      this.timelapseOwnersGameData.lProvinceOwnersAtBeginning.clear();
      this.timelapseGameData.lCivsCapitals.clear();
      this.timelapseTurnChanges.lTurnChanges.clear();
      this.clearTimeline();
      this.clearTimeline_Statistics();

      for (int i = 0; i < CFG.game.getProvincesSize(); i++) {
         if (CFG.game.getProvince(i).getSeaProvince()) {
            this.timelapseOwnersGameData.lProvinceOwnersAtBeginning.add(0);
         } else if (CFG.game.getProvince(i).getWasteland() >= 0) {
            this.timelapseOwnersGameData.lProvinceOwnersAtBeginning.add(-1);
         } else {
            this.timelapseOwnersGameData.lProvinceOwnersAtBeginning.add(CFG.game.getProvince(i).getCivID());
         }
      }

      for (int var7 = 1; var7 < CFG.game.getCivsSize(); var7++) {
         this.timelapseGameData.lCivsCapitals.add(new Timelapse_Capitals(CFG.game.getCiv(var7).getCapitalProvinceID(), 1));
      }

      this.timelapseTurnChanges.lTurnChanges.add(new ArrayList<>());
      ArrayList<Integer> tempProvinces = new ArrayList<>();
      ArrayList<Integer> tempPopulation = new ArrayList<>();
      ArrayList<Integer> tempRank = new ArrayList<>();
      ArrayList<Integer> tempTechnology = new ArrayList<>();

      for (int i2 = 0; i2 < CFG.game.getCivsSize(); i2++) {
         tempProvinces.add(CFG.game.getCiv(i2).getNumOfProvinces());
         tempPopulation.add((int)(CFG.game.getCiv(i2).countPopulation() + CFG.game.getCiv(i2).getNumOfUnits()));
         tempRank.add(CFG.game.getCiv(i2).getRankScore());
         tempTechnology.add((int)(CFG.game.getCiv(i2).getTechnologyLevel() * 100.0F));
      }

      this.timelapseStatsGD.addProvinces(tempProvinces);
      this.timelapseStatsGD.addPopulation(tempPopulation);
      this.timelapseStatsGD.addRank(tempRank);
      this.timelapseStatsGD.addTechLevel(tempTechnology);
   }

   public final void newTurn() {
      this.timelapseTurnChanges.lTurnChanges.add(new ArrayList<>());
      this.updateTurnStatistics();
   }

   public final void addChange(int nProvinceID, int toCivID, boolean isOccupied) {
      int iSize = this.timelapseTurnChanges.lTurnChanges.size() - 1;

      for (int i = 0; i < this.timelapseTurnChanges.lTurnChanges.get(iSize).size(); i++) {
         if (this.timelapseTurnChanges.lTurnChanges.get(iSize).get(i).iProvinceID == nProvinceID) {
            this.timelapseTurnChanges.lTurnChanges.get(iSize).get(i).iToCivID = toCivID;
            this.timelapseTurnChanges.lTurnChanges.get(iSize).get(i).isOccupied = isOccupied;
            return;
         }
      }

      this.timelapseTurnChanges.lTurnChanges.get(iSize).add(new Timelapse_TurnChanges(nProvinceID, toCivID, isOccupied));
   }

   public final void updateTurnStatistics() {
      try {
         for (int i = 1; i < CFG.game.getCivsSize(); i++) {
            this.timelapseGameData.lCivsCapitals.get(i - 1).updateCapital(CFG.game.getCiv(i).getCapitalProvinceID(), Game_Calendar.TURN_ID);
         }
      } catch (IndexOutOfBoundsException var9) {
         for (int i = this.timelapseGameData.lCivsCapitals.size() + 1; i < CFG.game.getCivsSize(); i++) {
            this.timelapseGameData.lCivsCapitals.add(new Timelapse_Capitals(CFG.game.getCiv(i).getCapitalProvinceID(), Game_Calendar.TURN_ID));
         }
      }

      ArrayList<Integer> tempProvinces = new ArrayList<>();
      ArrayList<Integer> tempPopulation = new ArrayList<>();
      ArrayList<Integer> tempRank = new ArrayList<>();
      ArrayList<Integer> tempTechnology = new ArrayList<>();

      for (int i = 0; i < CFG.game.getCivsSize(); i++) {
         tempProvinces.add(CFG.game.getCiv(i).getNumOfProvinces());
         tempPopulation.add((int)(CFG.game.getCiv(i).countPopulation() + CFG.game.getCiv(i).getNumOfUnits()));
         tempRank.add(CFG.game.getCiv(i).getRankScore());
         tempTechnology.add((int)(CFG.game.getCiv(i).getTechnologyLevel() * 100.0F));
      }

      this.timelapseStatsGD.addProvinces(tempProvinces);
      this.timelapseStatsGD.addPopulation(tempPopulation);
      this.timelapseStatsGD.addRank(tempRank);
      this.timelapseStatsGD.addTechLevel(tempTechnology);
      ArrayList<Integer> tempIncome = new ArrayList<>();
      ArrayList<Integer> tempBalance = new ArrayList<>();
      ArrayList<Integer> tempMilitarySpendings = new ArrayList<>();

      for (int i = 0; i < CFG.game.getPlayersSize(); i++) {
         tempIncome.add(CFG.game.getCiv(CFG.game.getPlayer(i).getCivID()).iIncomeTaxation + CFG.game.getCiv(CFG.game.getPlayer(i).getCivID()).iIncomeProduction);
         tempMilitarySpendings.add((int)CFG.game_NextTurnUpdate.getMilitaryUpkeep_Total(CFG.game.getPlayer(i).getCivID()));
         tempBalance.add(
            (int)CFG.game_NextTurnUpdate.getIncome(CFG.game.getPlayer(i).getCivID())
               - (int)CFG.game_NextTurnUpdate.getExpenses(CFG.game.getPlayer(i).getCivID())
         );
      }

      this.timelapseStatsGD.lPlayers_Income.add(tempIncome);
      this.timelapseStatsGD.lPlayers_Balance.add(tempBalance);
      this.timelapseStatsGD.lPlayers_MilitarySpendings.add(tempMilitarySpendings);
      if (this.timelapseStatsGD.lPlayers_Income.size() > CFG.settingsManager.GRAPH_DATA_LIMIT_PLAYER_DATA) {
         this.timelapseStatsGD.lPlayers_Income.remove(0);
      }

      if (this.timelapseStatsGD.lPlayers_Balance.size() > CFG.settingsManager.GRAPH_DATA_LIMIT_PLAYER_DATA) {
         this.timelapseStatsGD.lPlayers_Balance.remove(0);
      }

      if (this.timelapseStatsGD.lPlayers_MilitarySpendings.size() > CFG.settingsManager.GRAPH_DATA_LIMIT_PLAYER_DATA) {
         this.timelapseStatsGD.lPlayers_MilitarySpendings.remove(0);
      }
   }

   public final int getNumOfProvinces(int nCivID) {
      int out = 0;

      try {
         for (int i = this.timelineOwners.size() - 1; i >= 0; i--) {
            if (this.timelineOwners.get(i) == nCivID) {
               out++;
            }
         }
      } catch (IndexOutOfBoundsException var4) {
      } catch (NullPointerException var5) {
      }

      return out;
   }

   public final int getPlayerIncome(int iPlayerID, int iTurnID) {
      try {
         return this.timelapseStatsGD.lPlayers_Income.get(iTurnID).get(iPlayerID);
      } catch (IndexOutOfBoundsException var6) {
         try {
            if (iTurnID >= this.timelapseStatsGD.lPlayers_Income.size()) {
               return this.timelapseStatsGD.lPlayers_Income.get(this.timelapseStatsGD.lPlayers_Income.size() - 1).get(iPlayerID);
            }
         } catch (IndexOutOfBoundsException var5) {
         }

         return 0;
      }
   }
}
