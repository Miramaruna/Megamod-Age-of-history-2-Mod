package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.Json;
import java.util.ArrayList;
import java.util.List;

public class PlagueManager {
   public static final int PLAGUE_PAUSE_FOR_X_TURNS = 38;
   public List<Plagues_GameData> lPlagues = new ArrayList<>();
   public int iPlaguesSize = 0;
   public List<Plague_GameData> lPlagues_INGAME = new ArrayList<>();
   public static final int OUTBREAK_RANDOM = 12500;
   public static final int OUTBREAK_MODIFY = 1000000;

   public final void runPlagues() {
      try {
         for (int i = this.lPlagues_INGAME.size() - 1; i >= 0; i--) {
            this.lPlagues_INGAME.get(i).runDisease();
         }

         try {
            for (int var8 = this.lPlagues_INGAME.size() - 1; var8 >= 0; var8--) {
               if (--this.lPlagues_INGAME.get(var8).iDurationTurnsLeft < 1 && this.lPlagues_INGAME.get(var8).lProvinces_Active.size() == 0) {
                  for (int k = var8 + 1; k < this.lPlagues_INGAME.size(); k++) {
                     for (int o = 0; o < this.lPlagues_INGAME.get(k).lProvinces_Active.size(); o++) {
                        if (CFG.game.getProvince(this.lPlagues_INGAME.get(k).lProvinces_Active.get(o)).saveProvinceData.provincePlague != null
                           && CFG.game.getProvince(this.lPlagues_INGAME.get(k).lProvinces_Active.get(o)).saveProvinceData.provincePlague.iPlagueID_InGame
                              == this.lPlagues_INGAME.get(k).getPlagueID_InGame()) {
                           CFG.game.getProvince(this.lPlagues_INGAME.get(k).lProvinces_Active.get(o)).saveProvinceData.provincePlague.iPlagueID_InGame--;
                        }
                     }

                     this.lPlagues_INGAME.get(k).setPlagueID_InGame(this.lPlagues_INGAME.get(k).getPlagueID_InGame() - 1);
                  }

                  this.lPlagues_INGAME.remove(var8);
               }
            }
         } catch (IndexOutOfBoundsException var4) {
            CFG.exceptionStack(var4);
         } catch (NullPointerException var5) {
            CFG.exceptionStack(var5);
         }

         for (int i2 = this.lPlagues_INGAME.size() - 1; i2 >= 0; i2--) {
            this.lPlagues_INGAME.get(i2).spreadDisease();
         }

         this.startDisease();
      } catch (IndexOutOfBoundsException var6) {
         CFG.exceptionStack(var6);
      } catch (NullPointerException var7) {
         CFG.exceptionStack(var7);
      }
   }

   public final void startDisease() {
      int tRandScore = CFG.oR.nextInt(12500);
      if (tRandScore < 12500.0F * CFG.gameAges.getAge_DiseaseChance(Game_Calendar.CURRENT_AGEID)) {
         ArrayList<Integer> tempIDsToSpawn = new ArrayList<>();
         int tScoreTotal = 0;

         for (int i = 0; i < this.iPlaguesSize; i++) {
            if (Game_Calendar.currentYear >= this.lPlagues.get(i).BeginningYear && Game_Calendar.currentYear <= this.lPlagues.get(i).EndYear) {
               tempIDsToSpawn.add(i);
               tScoreTotal = (int)(tScoreTotal + this.lPlagues.get(i).OUTBREAK_CHANCE * 1000000.0F);
            }
         }

         if (tempIDsToSpawn.size() > 0) {
            int spawnID = 0;
            if (tScoreTotal > 0) {
               int tCurrentScore = 0;

               for (int ix = tempIDsToSpawn.size() - 1; ix >= 0; ix--) {
                  tRandScore = CFG.oR.nextInt(tScoreTotal);
                  if ((tCurrentScore += (int)(this.lPlagues.get(tempIDsToSpawn.get(ix)).OUTBREAK_CHANCE * 1000000.0F)) > tRandScore) {
                     spawnID = ix;
                     break;
                  }
               }
            } else {
               spawnID = CFG.oR.nextInt(tempIDsToSpawn.size());
            }

            this.startDisease(tempIDsToSpawn.get(spawnID));
         }
      }
   }

   public final void startDisease(int nID) {
      int nOutbreakProvinces = this.lPlagues.get(nID).OUTBREAK_PROVINCES;
      if (this.lPlagues.get(nID).OUTBREAK_PROVINCES_EXTRA > 0) {
         nOutbreakProvinces += CFG.oR.nextInt(this.lPlagues.get(nID).OUTBREAK_PROVINCES_EXTRA);
      }

      ArrayList<Integer> lPossibleProvinces = new ArrayList<>();

      for (int i = 0; i < CFG.game.getProvincesSize(); i++) {
         if (CFG.game.getProvince(i).getWasteland() < 0
            && !CFG.game.getProvince(i).getSeaProvince()
            && CFG.game.getProvince(i).getCivID() > 0
            && CFG.game.getProvince(i).saveProvinceData.provincePlague == null
            && Game_Calendar.TURN_ID - CFG.game.getProvince(i).saveProvinceData.iLastPlagueTurnID > 38) {
            lPossibleProvinces.add(i);
         }
      }

      if (lPossibleProvinces.size() > 0) {
         ArrayList lSpreadPropositions = new ArrayList();
         int nToCheck = 8 + (int)(10.0F * Math.min(this.lPlagues.get(nID).DEATH_RATE_MIN, 1.0F));

         while (lPossibleProvinces.size() > 0 && nToCheck-- > 0) {
            int tRandID = CFG.oR.nextInt(lPossibleProvinces.size());
            lSpreadPropositions.add(lPossibleProvinces.get(tRandID));
            lPossibleProvinces.remove(tRandID);
         }

         lPossibleProvinces.clear();
         if (lSpreadPropositions.size() > 0) {
            ArrayList<Float> lSpreadPropositions_Score = new ArrayList<>();
            int tMaxPopulation = 0;
            int tMaxEconomy = 0;
            float tMaxDevelopemnt = 0.0F;
            float tMaxHappiness = 0.0F;

            for (int ix = lSpreadPropositions.size() - 1; ix >= 0; ix--) {
               if (CFG.game.getProvince((Integer)lSpreadPropositions.get(ix)).getPopulationData().getPopulation() > tMaxPopulation) {
                  tMaxPopulation = CFG.game.getProvince((Integer)lSpreadPropositions.get(ix)).getPopulationData().getPopulation();
               }

               if (CFG.game.getProvince((Integer)lSpreadPropositions.get(ix)).getEconomy() > tMaxEconomy) {
                  tMaxEconomy = CFG.game.getProvince((Integer)lSpreadPropositions.get(ix)).getEconomy();
               }

               if (CFG.game.getProvince((Integer)lSpreadPropositions.get(ix)).getDevelopmentLevel() > tMaxDevelopemnt) {
                  tMaxDevelopemnt = CFG.game.getProvince((Integer)lSpreadPropositions.get(ix)).getDevelopmentLevel();
               }

               if (CFG.game.getProvince((Integer)lSpreadPropositions.get(ix)).getHappiness() > tMaxHappiness) {
                  tMaxHappiness = CFG.game.getProvince((Integer)lSpreadPropositions.get(ix)).getHappiness();
               }
            }

            for (int var20 = lSpreadPropositions.size() - 1; var20 >= 0; var20--) {
               lSpreadPropositions_Score.add(
                  this.lPlagues.get(nID).OUTBREAK_SCORE_POPULATION
                        * CFG.game.getProvince((Integer)lSpreadPropositions.get(var20)).getPopulationData().getPopulation()
                        / tMaxPopulation
                     + this.lPlagues.get(nID).OUTBREAK_SCORE_ECONOMY * CFG.game.getProvince((Integer)lSpreadPropositions.get(var20)).getEconomy() / tMaxEconomy
                     + (
                        this.lPlagues.get(nID).OUTBREAK_SCORE_DEVELOPMENT_LOW
                           - this.lPlagues.get(nID).OUTBREAK_SCORE_DEVELOPMENT_LOW
                              * CFG.game.getProvince((Integer)lSpreadPropositions.get(var20)).getDevelopmentLevel()
                              / tMaxDevelopemnt
                     )
                     + this.lPlagues.get(nID).OUTBREAK_SCORE_DEVELOPMENT
                        * CFG.game.getProvince((Integer)lSpreadPropositions.get(var20)).getDevelopmentLevel()
                        / tMaxDevelopemnt
                     + (
                        this.lPlagues.get(nID).OUTBREAK_SCORE_HAPPINESS_LOW
                           - this.lPlagues.get(nID).OUTBREAK_SCORE_HAPPINESS_LOW
                              * CFG.game.getProvince((Integer)lSpreadPropositions.get(var20)).getHappiness()
                              / tMaxHappiness
                     )
                     + this.lPlagues.get(nID).OUTBREAK_SCORE_HAPPINESS
                        * CFG.game.getProvince((Integer)lSpreadPropositions.get(var20)).getHappiness()
                        / tMaxHappiness
               );
            }

            int tBestID = 0;

            for (int i2 = lSpreadPropositions_Score.size() - 1; i2 > 0; i2--) {
               if (lSpreadPropositions_Score.get(tBestID) < lSpreadPropositions_Score.get(i2)) {
                  tBestID = i2;
               }
            }

            int nPlagueID_InGame = this.lPlagues_INGAME.size();
            this.lPlagues_INGAME
               .add(
                  new Plague_GameData(
                     (Integer)lSpreadPropositions.get(tBestID),
                     this.lPlagues.get(nID).getName(),
                     this.lPlagues.get(nID).fR,
                     this.lPlagues.get(nID).fG,
                     this.lPlagues.get(nID).fB,
                     nPlagueID_InGame,
                     this.lPlagues.get(nID).DEATH_RATE_MIN + CFG.oR.nextInt((int)(this.lPlagues.get(nID).DEATH_RATE_EXTRA * 100000.0F + 1.0F)) / 100000.0F,
                     this.lPlagues.get(nID).DURATION_TURNS_MIN
                        + (this.lPlagues.get(nID).DURATION_TURNS_EXTRA > 0 ? CFG.oR.nextInt(this.lPlagues.get(nID).DURATION_TURNS_EXTRA) : 0),
                     this.lPlagues.get(nID).EXPANSION_MODIFIER
                        + CFG.oR.nextInt((int)(this.lPlagues.get(nID).EXPANSION_MODIFIER_EXTRA * 100000.0F + 1.0F)) / 100000.0F
                  )
               );

            try {
               CFG.historyManager.addHistoryLog(new HistoryLog_Disease((Integer)lSpreadPropositions.get(tBestID)));
            } catch (NullPointerException var15) {
            } catch (IndexOutOfBoundsException var16) {
            }

            lSpreadPropositions.clear();
            lSpreadPropositions_Score.clear();
            if (--nOutbreakProvinces > 0) {
               this.lPlagues_INGAME.get(nPlagueID_InGame).spreadDisease(nOutbreakProvinces);
            }
         }
      }
   }

   public PlagueManager() {
      this.loadPlagues();
   }

   public final void loadPlagues() {
      this.lPlagues = new ArrayList<>();

      try {
         FileHandle fileList = Gdx.files.internal("game/Diseases.json");
         String fileContent = fileList.readString();
         Json json = new Json();
         json.setElementType(PlagueManager.ConfigDiseasesData.class, "Disease", PlagueManager.Data_Diseases.class);
         new PlagueManager.ConfigDiseasesData();
         PlagueManager.ConfigDiseasesData data = json.fromJson(PlagueManager.ConfigDiseasesData.class, fileContent);

         for (Object e : data.Disease) {
            PlagueManager.Data_Diseases tempData = (PlagueManager.Data_Diseases)e;
            this.lPlagues
               .add(
                  new Plagues_GameData(
                     tempData.Name,
                     tempData.BeginningYear,
                     tempData.EndYear,
                     tempData.DURATION_TURNS_MIN,
                     tempData.DURATION_TURNS_EXTRA,
                     tempData.DEATH_RATE_MIN,
                     tempData.DEATH_RATE_EXTRA,
                     tempData.EXPANSION_MODIFIER,
                     tempData.EXPANSION_MODIFIER_EXTRA,
                     tempData.R,
                     tempData.G,
                     tempData.B,
                     tempData.OUTBREAK_CHANCE,
                     tempData.OUTBREAK_PROVINCES,
                     tempData.OUTBREAK_PROVINCES_EXTRA,
                     tempData.OUTBREAK_SCORE_POPULATION,
                     tempData.OUTBREAK_SCORE_ECONOMY,
                     tempData.OUTBREAK_SCORE_DEVELOPMENT,
                     tempData.OUTBREAK_SCORE_HAPPINESS,
                     tempData.OUTBREAK_SCORE_DEVELOPMENT_LOW,
                     tempData.OUTBREAK_SCORE_HAPPINESS_LOW
                  )
               );
         }
      } catch (GdxRuntimeException var8) {
         CFG.exceptionStack(var8);
      }

      this.iPlaguesSize = this.lPlagues.size();
   }

   public final Plagues_GameData getPlague(int nID) {
      return this.lPlagues.get(nID);
   }

   public final Color getPlagueColor(int nID, float nAlpha) {
      return new Color(this.lPlagues.get(nID).fR, this.lPlagues.get(nID).fG, this.lPlagues.get(nID).fB, nAlpha);
   }

   public final Plague_GameData getPlague_InGame(int nID) {
      return this.lPlagues_INGAME.get(nID);
   }

   public final Color getPlagueColor_InGame(int nProvinceID, int nID, float nAlpha) {
      return new Color(
         this.lPlagues_INGAME.get(nID).fR,
         this.lPlagues_INGAME.get(nID).fG,
         this.lPlagues_INGAME.get(nID).fB,
         nAlpha
            * (
               0.625F
                  + 0.375F
                     * this.lPlagues_INGAME
                        .get(nID)
                        .getDurationPercLEFT((int)CFG.game.getProvince(nProvinceID).saveProvinceData.provincePlague.iDurationTurnsLeft)
            )
      );
   }

   public final Color getPlagueColor_InGame(int nID, float nAlpha) {
      return new Color(this.lPlagues_INGAME.get(nID).fR, this.lPlagues_INGAME.get(nID).fG, this.lPlagues_INGAME.get(nID).fB, nAlpha);
   }

   public final int getPlaguesSize() {
      return this.iPlaguesSize;
   }

   public static class ConfigDiseasesData {
      public String Age_of_Civilizations;
      public ArrayList Disease;
   }

   public static class Data_Diseases {
      public String Name;
      public int BeginningYear;
      public int EndYear;
      public float OUTBREAK_CHANCE;
      public int OUTBREAK_PROVINCES;
      public int OUTBREAK_PROVINCES_EXTRA;
      public float OUTBREAK_SCORE_POPULATION;
      public float OUTBREAK_SCORE_ECONOMY;
      public float OUTBREAK_SCORE_DEVELOPMENT;
      public float OUTBREAK_SCORE_HAPPINESS;
      public float OUTBREAK_SCORE_DEVELOPMENT_LOW;
      public float OUTBREAK_SCORE_HAPPINESS_LOW;
      public int DURATION_TURNS_MIN;
      public int DURATION_TURNS_EXTRA;
      public float DEATH_RATE_MIN;
      public float DEATH_RATE_EXTRA;
      public float EXPANSION_MODIFIER;
      public float EXPANSION_MODIFIER_EXTRA;
      public int R;
      public int G;
      public int B;
   }
}
