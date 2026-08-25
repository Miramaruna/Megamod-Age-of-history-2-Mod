package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Plague_GameData implements Serializable {
   public static final long serialVersionUID = 0L;
   public String sName;
   public int iPlagueID_InGame = 0;
   public List<Integer> lProvinces = new ArrayList<>();
   public int iProvincesSize = 0;
   public List<Integer> lProvinces_Active = new ArrayList<>();
   public float fDeathRate = 0.0F;
   public int iDurationTurnsLeft = 0;
   public int iDeaths = 0;
   public int iDurationTurnsLeft_BEGINNING = 0;
   public float fR;
   public float fG;
   public float fB;
   public float EXPANSION_MODIFIER;
   public float EXPANSION_SCORE;

   public Plague_GameData(
      int outbreakProvince,
      String sName,
      float fR,
      float fG,
      float fB,
      int nPlagueID_InGame,
      float fDeathRate,
      int iDurationTurnsLeft,
      float EXPANSION_MODIFIER
   ) {
      this.sName = sName;
      this.iPlagueID_InGame = nPlagueID_InGame;
      this.fR = fR;
      this.fG = fG;
      this.fB = fB;
      this.fDeathRate = fDeathRate;
      this.iDurationTurnsLeft = iDurationTurnsLeft;
      this.iDurationTurnsLeft_BEGINNING = iDurationTurnsLeft;
      this.EXPANSION_MODIFIER = EXPANSION_MODIFIER;
      this.addProvince(outbreakProvince);
   }

   public final void runDisease() {
      for (int i = this.lProvinces_Active.size() - 1; i >= 0; i--) {
         if (CFG.game.getProvince(this.lProvinces_Active.get(i)).saveProvinceData.provincePlague != null
            && CFG.game.getProvince(this.lProvinces_Active.get(i)).saveProvinceData.provincePlague.iPlagueID_InGame == this.getPlagueID_InGame()) {
            int nPopBefore = CFG.game.getProvince(this.lProvinces_Active.get(i)).getPopulationData().getPopulation();
            int nDeaths = (int)Math.ceil(
               nPopBefore
                  * (
                     this.fDeathRate
                        * (1.0F + CFG.game.getGameScenarios().getScenario_DiseasesDeathRate_Modifier())
                        * (0.225F + 0.325F * this.getDurationPercLEFT() + 0.55F * CFG.oR.nextInt(100) / 100.0F)
                  )
            );

            for (int k = CFG.game.getProvince(this.lProvinces_Active.get(i)).getPopulationData().getNationalitiesSize() - 1; k >= 0; k--) {
               CFG.game
                  .getProvince(this.lProvinces_Active.get(i))
                  .getPopulationData()
                  .setPopulationOfCivID(
                     CFG.game.getProvince(this.lProvinces_Active.get(i)).getPopulationData().getCivID(k),
                     (int)(
                        CFG.game.getProvince(this.lProvinces_Active.get(i)).getPopulationData().getPopulationID(k)
                           - Math.floor(
                              nDeaths * ((float)CFG.game.getProvince(this.lProvinces_Active.get(i)).getPopulationData().getPopulationID(k) / nPopBefore)
                           )
                     )
                  );
            }

            PlagueProvince_GameData var10000 = CFG.game.getProvince(this.lProvinces_Active.get(i)).saveProvinceData.provincePlague;
            int var5;
            var10000.iDeaths = var10000.iDeaths + (var5 = nPopBefore - CFG.game.getProvince(this.lProvinces_Active.get(i)).getPopulationData().getPopulation());
            CFG.game.getProvince(this.lProvinces_Active.get(i)).saveProvinceData.iPlaguesDeaths += var5;
            this.iDeaths += var5;
            var10000 = CFG.game.getProvince(this.lProvinces_Active.get(i)).saveProvinceData.provincePlague;
            var10000.iDurationTurnsLeft = var10000.iDurationTurnsLeft
               - (0.875F - 0.065F * CFG.game.getProvince(this.lProvinces_Active.get(i)).getGrowthRate_Population_WithFarm() + CFG.oR.nextInt(825) / 1000.0F);
            if (CFG.game.getProvince(this.lProvinces_Active.get(i)).saveProvinceData.provincePlague.iDurationTurnsLeft <= 0.0F) {
               CFG.game.getProvince(this.lProvinces_Active.get(i)).saveProvinceData.iLastPlagueTurnID = Game_Calendar.TURN_ID;
               CFG.game.getProvince(this.lProvinces_Active.get(i)).saveProvinceData.provincePlague = null;
               this.lProvinces_Active.remove(i);
            }
         }
      }

      this.fDeathRate = this.fDeathRate
         * (1.0F + CFG.game.getGameScenarios().getScenario_DiseasesDeathRate_Modifier())
         * (0.965F - CFG.oR.nextInt(875) / 10000.0F);
   }

   public final void spreadDisease() {
      if (this.iDurationTurnsLeft > 0 && this.lProvinces_Active.size() > 0) {
         if ((float)this.lProvinces.size() / CFG.game.getProvincesSize() > 0.35F) {
            return;
         }

         this.EXPANSION_SCORE = this.EXPANSION_SCORE
            + this.lProvinces_Active.size() * 0.425F * this.EXPANSION_MODIFIER * (0.1F + 0.9F * this.getDurationPercLEFT());
         this.EXPANSION_MODIFIER = this.EXPANSION_MODIFIER * (0.925F - CFG.oR.nextInt(17850) / 100000.0F);
         int nRand;
         if (this.EXPANSION_SCORE >= 1.0F && (nRand = CFG.oR.nextInt((int)this.EXPANSION_SCORE)) > 0) {
            this.EXPANSION_SCORE -= nRand;
            this.spreadDisease(nRand);
         }
      }
   }

   public final void spreadDisease(int nNumOfProvinces) {
      try {
         nNumOfProvinces = (int)Math.min((float)nNumOfProvinces, Math.max(CFG.game.getProvincesSize() * 0.01425F, 16.0F));
         ArrayList<Integer> tPossibleSpreadProvinces = new ArrayList<>();
         ArrayList<Integer> tPossibleSpreadProvinces_Scores = new ArrayList<>();

         for (int i = 0; i < this.lProvinces_Active.size(); i++) {
            if (CFG.game.getProvince(this.lProvinces_Active.get(i)).getSeaProvince()) {
               for (int k = 0; k < CFG.game.getProvince(this.lProvinces_Active.get(i)).getNeighboringProvincesSize(); k++) {
                  if (CFG.game.getProvince(CFG.game.getProvince(this.lProvinces_Active.get(i)).getNeighboringProvinces(k)).saveProvinceData.provincePlague
                        == null
                     && Game_Calendar.TURN_ID
                           - CFG.game.getProvince(CFG.game.getProvince(this.lProvinces_Active.get(i)).getNeighboringProvinces(k)).saveProvinceData.iLastPlagueTurnID
                        > 38) {
                     tPossibleSpreadProvinces.add(CFG.game.getProvince(this.lProvinces_Active.get(i)).getNeighboringProvinces(k));
                  }
               }
            } else {
               for (int kx = 0; kx < CFG.game.getProvince(this.lProvinces_Active.get(i)).getNeighboringProvincesSize(); kx++) {
                  if (CFG.game.getProvince(CFG.game.getProvince(this.lProvinces_Active.get(i)).getNeighboringProvinces(kx)).getWasteland() < 0
                     && CFG.game.getProvince(CFG.game.getProvince(this.lProvinces_Active.get(i)).getNeighboringProvinces(kx)).saveProvinceData.provincePlague
                        == null
                     && Game_Calendar.TURN_ID
                           - CFG.game.getProvince(CFG.game.getProvince(this.lProvinces_Active.get(i)).getNeighboringProvinces(kx)).saveProvinceData.iLastPlagueTurnID
                        > 38) {
                     tPossibleSpreadProvinces.add(CFG.game.getProvince(this.lProvinces_Active.get(i)).getNeighboringProvinces(kx));
                  }
               }

               if (CFG.game.getProvince(this.lProvinces_Active.get(i)).getLevelOfPort() > 0
                  || CFG.game.getProvince(this.lProvinces_Active.get(i)).getNeighboringProvincesSize() < 2) {
                  for (int var12 = 0; var12 < CFG.game.getProvince(this.lProvinces_Active.get(i)).getNeighboringSeaProvincesSize(); var12++) {
                     if (CFG.game.getProvince(CFG.game.getProvince(this.lProvinces_Active.get(i)).getNeighboringSeaProvinces(var12)).getWasteland() < 0
                        && CFG.game.getProvince(CFG.game.getProvince(this.lProvinces_Active.get(i)).getNeighboringSeaProvinces(var12)).saveProvinceData.provincePlague
                           == null
                        && Game_Calendar.TURN_ID
                              - CFG.game.getProvince(CFG.game.getProvince(this.lProvinces_Active.get(i)).getNeighboringSeaProvinces(var12)).saveProvinceData.iLastPlagueTurnID
                           > 38) {
                        tPossibleSpreadProvinces.add(CFG.game.getProvince(this.lProvinces_Active.get(i)).getNeighboringSeaProvinces(var12));
                     }
                  }
               }
            }
         }

         if (tPossibleSpreadProvinces.size() > 0) {
            int tTotalScore = 0;

            for (int ix = tPossibleSpreadProvinces.size() - 1; ix >= 0; ix--) {
               int tempScore = this.getSpreadScore(tPossibleSpreadProvinces.get(ix)) * 3 + 1;
               tPossibleSpreadProvinces_Scores.add(tempScore);
               tTotalScore += tempScore;
            }

            if (tTotalScore > 0) {
               while (tPossibleSpreadProvinces_Scores.size() > 0 && nNumOfProvinces > 0) {
                  int tRandScore = CFG.oR.nextInt(tTotalScore);
                  int tCurrentScore = 0;

                  for (int ix = 0; ix < tPossibleSpreadProvinces_Scores.size(); ix++) {
                     if ((tCurrentScore += tPossibleSpreadProvinces_Scores.get(ix)) > tRandScore) {
                        this.addProvince(tPossibleSpreadProvinces.get(ix));
                        tTotalScore -= tPossibleSpreadProvinces_Scores.get(ix);
                        tPossibleSpreadProvinces_Scores.remove(ix);
                        tPossibleSpreadProvinces.remove(ix);
                        nNumOfProvinces--;
                        break;
                     }
                  }
               }

               if (nNumOfProvinces > 0) {
                  this.spreadDisease(nNumOfProvinces);
               }
            }
         }
      } catch (IndexOutOfBoundsException var8) {
         CFG.exceptionStack(var8);
      } catch (IllegalArgumentException var9) {
         CFG.exceptionStack(var9);
      }
   }

   public final int getSpreadScore(int nProvinceID) {
      int tempScore = 0;

      for (int k = 0; k < CFG.game.getProvince(nProvinceID).getNeighboringProvincesSize(); k++) {
         if (CFG.game.getProvince(CFG.game.getProvince(nProvinceID).getNeighboringProvinces(k)).saveProvinceData.provincePlague == null) {
            tempScore += CFG.game.getProvince(CFG.game.getProvince(nProvinceID).getNeighboringProvinces(k)).getSeaProvince() ? 1 : 2;
         }
      }

      for (int var4 = 0; var4 < CFG.game.getProvince(nProvinceID).getNeighboringSeaProvincesSize(); var4++) {
         if (CFG.game.getProvince(CFG.game.getProvince(nProvinceID).getNeighboringSeaProvinces(var4)).saveProvinceData.provincePlague == null) {
            tempScore += CFG.game.getProvince(CFG.game.getProvince(nProvinceID).getNeighboringSeaProvinces(var4)).getSeaProvince() ? 1 : 2;
         }
      }

      return tempScore;
   }

   public final void addProvince(int nProvinceID) {
      for (int i = 0; i < this.iProvincesSize; i++) {
         if (this.lProvinces.get(i) == nProvinceID) {
            return;
         }
      }

      CFG.game.getProvince(nProvinceID).saveProvinceData.iLastPlagueTurnID = Game_Calendar.TURN_ID;
      if (CFG.game.getProvince(nProvinceID).saveProvinceData.provincePlague == null) {
         CFG.game.getProvince(nProvinceID).saveProvinceData.provincePlague = new PlagueProvince_GameData(
            this.iPlagueID_InGame, Game_Calendar.TURN_ID, this.iDurationTurnsLeft * (0.625F + CFG.oR.nextInt(6000) / 10000.0F), 0
         );
         if (CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getControlledByPlayer()) {
            CFG.game
               .getCiv(CFG.game.getProvince(nProvinceID).getCivID())
               .getCivilization_Diplomacy_GameData()
               .messageBox
               .addMessage(new Message_Disease(CFG.game.getProvince(nProvinceID).getCivID(), nProvinceID));
         }

         CFG.game.getProvince(nProvinceID).saveProvinceData.iNumOfPlaguesTotal++;
         this.lProvinces.add(nProvinceID);
         this.lProvinces_Active.add(nProvinceID);
         this.iProvincesSize = this.lProvinces.size();
      }
   }

   public final String getPlagueName() {
      try {
         return this.sName;
      } catch (IndexOutOfBoundsException var2) {
         return CFG.langManager.get("Plague");
      }
   }

   public final void setPlagueID_InGame(int iPlagueID_InGame) {
      this.iPlagueID_InGame = iPlagueID_InGame;
   }

   public final int getPlagueID_InGame() {
      return this.iPlagueID_InGame;
   }

   public final float getDurationPercLEFT() {
      return (float)this.iDurationTurnsLeft / this.iDurationTurnsLeft_BEGINNING;
   }

   public final float getDurationPercLEFT(int nNumOfTurns) {
      return (float)nNumOfTurns / this.iDurationTurnsLeft_BEGINNING;
   }

   public final int getOutbreakProvinceID() {
      try {
         return this.lProvinces.get(0);
      } catch (IndexOutOfBoundsException var2) {
         return -1;
      }
   }

   public final int getDeaths() {
      return this.iDeaths;
   }

   public final int getNumOfProvinces_Total() {
      return this.lProvinces.size();
   }

   public final int getNumOfProvinces_Active() {
      return this.lProvinces_Active.size();
   }
}
