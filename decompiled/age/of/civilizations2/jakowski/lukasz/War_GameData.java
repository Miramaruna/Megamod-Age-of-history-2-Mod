package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class War_GameData implements Serializable {
   public static final long serialVersionUID = 0L;
   public List<WarCiv_GameData> lAggressors = new ArrayList<>();
   public List<WarCiv_GameData> lDefenders = new ArrayList<>();
   public int iWarTurnID = 1;
   public int iLastFight_InTunrs = 0;
   public int iLastTurn_ConqueredProvince = 0;
   public boolean wasAnyAttack = false;
   public String WAR_TAG;
   public static final float WAR_SCORE_MODIFIER = 0.7F;
   public static final float WAR_SCORE_MODIFIER2 = 0.2F;

   public War_GameData(int nAggressor, int nDefender) {
      this.addAggressor(nAggressor);
      this.addDefender(nDefender);
      this.iWarTurnID = Game_Calendar.TURN_ID;
      this.WAR_TAG = CFG.game.getCiv(nAggressor).getCivTag() + CFG.game.getCiv(nDefender).getCivTag() + CFG.extraRandomTag() + this.iWarTurnID;
   }

   public final void addAggressor(int nCivID) {
      for (int i = 0; i < this.getAggressorsSize(); i++) {
         if (this.lAggressors.get(i).getCivID() == nCivID) {
            return;
         }
      }

      this.lAggressors.add(new WarCiv_GameData(nCivID));

      for (int var3 = 0; var3 < this.getDefendersSize(); var3++) {
         if (!CFG.game.getCivsAtWar(nCivID, this.getDefenderID(var3).getCivID())) {
            CFG.game.setCivRelation_OfCivB(nCivID, this.getDefenderID(var3).getCivID(), -100.0F);
            CFG.game.setCivRelation_OfCivB(this.getDefenderID(var3).getCivID(), nCivID, -100.0F);
         }
      }

      this.iLastFight_InTunrs = 0;
      this.iLastTurn_ConqueredProvince = Game_Calendar.TURN_ID;
   }

   public final void removeAggressor(int nCivID) {
      for (int i = 0; i < this.getAggressorsSize(); i++) {
         if (this.lAggressors.get(i).getCivID() == nCivID) {
            this.lAggressors.remove(i);
            return;
         }
      }
   }

   public final void addDefender(int nCivID) {
      for (int i = 0; i < this.getDefendersSize(); i++) {
         if (this.lDefenders.get(i).getCivID() == nCivID) {
            return;
         }
      }

      this.lDefenders.add(new WarCiv_GameData(nCivID));

      for (int var3 = 0; var3 < this.getAggressorsSize(); var3++) {
         if (!CFG.game.getCivsAtWar(nCivID, this.getAggressorID(var3).getCivID())) {
            CFG.game.setCivRelation_OfCivB(nCivID, this.getAggressorID(var3).getCivID(), -100.0F);
            CFG.game.setCivRelation_OfCivB(this.getAggressorID(var3).getCivID(), nCivID, -100.0F);
         }
      }

      this.iLastFight_InTunrs = 0;
      this.iLastTurn_ConqueredProvince = Game_Calendar.TURN_ID;
   }

   public final void removeDefender(int nCivID) {
      for (int i = 0; i < this.getDefendersSize(); i++) {
         if (this.lDefenders.get(i).getCivID() == nCivID) {
            this.lDefenders.remove(i);
            return;
         }
      }
   }

   public final void updateAfterUnion(int nCivA, int nCivB) {
      if (this.getIsAggressor(nCivA) && this.getIsAggressor(nCivB)) {
         int nID2 = this.getAggressorID_ByCivID(nCivA);
         int nID22 = this.getAggressorID_ByCivID(nCivB);
         if (nID2 >= 0 && nID22 >= 0) {
            this.getAggressorID(nID2).addCivilianDeaths(this.getAggressorID(nID22).getCivilianDeaths());
            this.getAggressorID(nID2).addCasualties(this.getAggressorID(nID22).getCasualties());
            this.getAggressorID(nID2).addEconomicLosses(this.getAggressorID(nID22).getEconomicLosses());
            this.removeAggressor(nCivB);
         }
      } else if (this.getIsDefender(nCivA) && this.getIsDefender(nCivB)) {
         int nID3 = this.getDefenderID_ByCivID(nCivA);
         int nID2 = this.getDefenderID_ByCivID(nCivB);
         if (nID3 >= 0 && nID2 >= 0) {
            this.getDefenderID(nID3).addCivilianDeaths(this.getDefenderID(nID2).getCivilianDeaths());
            this.getDefenderID(nID3).addCasualties(this.getDefenderID(nID2).getCasualties());
            this.getDefenderID(nID3).addEconomicLosses(this.getDefenderID(nID2).getEconomicLosses());
            this.removeDefender(nCivB);
         }
      } else if (this.getIsAggressor(nCivB) && !this.getIsDefender(nCivA)) {
         int nID4 = this.getAggressorID_ByCivID(nCivB);
         if (nID4 >= 0) {
            this.getAggressorID(nID4).setCivID(nCivA);
         }
      } else {
         int nID;
         if (this.getIsDefender(nCivB) && !this.getIsAggressor(nCivA) && (nID = this.getDefenderID_ByCivID(nCivB)) >= 0) {
            this.getDefenderID(nID).setCivID(nCivA);
         }
      }
   }

   public final boolean getIsAggressor(int nCivID) {
      for (int i = 0; i < this.getAggressorsSize(); i++) {
         if (this.getAggressorID(i).getCivID() == nCivID) {
            return true;
         }
      }

      return false;
   }

   public final boolean getIsDefender(int nCivID) {
      for (int i = 0; i < this.getDefendersSize(); i++) {
         if (this.getDefenderID(i).getCivID() == nCivID) {
            return true;
         }
      }

      return false;
   }

   public final int getWarScore() {
      int tempNumOfProvincesInWar_Aggrersors = 0;
      int tempNumOfProvincesInWar_Defenders = 0;
      int tempControledEnemyProvinces_ByAggrersors = 0;
      int tempControledEnemyProvinces_ByDefenders = 0;

      for (int i = 0; i < this.getAggressorsSize(); i++) {
         for (int j = 0; j < CFG.game.getCiv(this.getAggressorID(i).getCivID()).getNumOfProvinces(); j++) {
            if (this.getAggressorID(i).getCivID()
               == CFG.game.getProvince(CFG.game.getCiv(this.getAggressorID(i).getCivID()).getProvinceID(j)).getTrueOwnerOfProvince()) {
               tempNumOfProvincesInWar_Aggrersors += CFG.game.getProvinceValue(CFG.game.getCiv(this.getAggressorID(i).getCivID()).getProvinceID(j));
            } else {
               for (int k = 0; k < this.getDefendersSize(); k++) {
                  if (this.getDefenderID(k).getCivID()
                     == CFG.game.getProvince(CFG.game.getCiv(this.getAggressorID(i).getCivID()).getProvinceID(j)).getTrueOwnerOfProvince()) {
                     tempControledEnemyProvinces_ByAggrersors += CFG.game.getProvinceValue(CFG.game.getCiv(this.getAggressorID(i).getCivID()).getProvinceID(j));
                     break;
                  }
               }
            }
         }
      }

      for (int var15 = 0; var15 < this.getDefendersSize(); var15++) {
         for (int jx = 0; jx < CFG.game.getCiv(this.getDefenderID(var15).getCivID()).getNumOfProvinces(); jx++) {
            if (this.getDefenderID(var15).getCivID()
               == CFG.game.getProvince(CFG.game.getCiv(this.getDefenderID(var15).getCivID()).getProvinceID(jx)).getTrueOwnerOfProvince()) {
               tempNumOfProvincesInWar_Defenders += CFG.game.getProvinceValue(CFG.game.getCiv(this.getDefenderID(var15).getCivID()).getProvinceID(jx));
            } else {
               for (int kx = 0; kx < this.getAggressorsSize(); kx++) {
                  if (this.getAggressorID(kx).getCivID()
                     == CFG.game.getProvince(CFG.game.getCiv(this.getDefenderID(var15).getCivID()).getProvinceID(jx)).getTrueOwnerOfProvince()) {
                     tempControledEnemyProvinces_ByDefenders += CFG.game
                        .getProvinceValue(CFG.game.getCiv(this.getDefenderID(var15).getCivID()).getProvinceID(jx));
                     break;
                  }
               }
            }
         }
      }

      int tempAggressorsPerc = 0;
      int tempDefendersPerc = 0;

      try {
         tempAggressorsPerc = (int)(
            (float)tempControledEnemyProvinces_ByAggrersors
               / (tempNumOfProvincesInWar_Defenders + tempControledEnemyProvinces_ByDefenders + tempControledEnemyProvinces_ByAggrersors)
               * 100.0F
         );
      } catch (ArithmeticException var12) {
         tempAggressorsPerc = 0;
      }

      try {
         tempDefendersPerc = (int)(
            (float)tempControledEnemyProvinces_ByDefenders
               / (tempNumOfProvincesInWar_Aggrersors + tempControledEnemyProvinces_ByAggrersors + tempControledEnemyProvinces_ByDefenders)
               * 100.0F
         );
      } catch (ArithmeticException var11) {
         tempDefendersPerc = 0;
      }

      return -tempAggressorsPerc + tempDefendersPerc;
   }

   public final int getWarScore_PeaceTreaty() {
      int tempNumOfProvincesInWar_Aggrersors = 0;
      int tempNumOfProvincesInWar_Defenders = 0;
      int tempControledEnemyProvinces_ByAggrersors = 0;
      int tempControledEnemyProvinces_ByDefenders = 0;

      for (int i = 0; i < CFG.peaceTreatyData.peaceTreatyGameData.lCivsData_Aggressors.size(); i++) {
         for (int j = 0; j < CFG.game.getCiv(CFG.peaceTreatyData.peaceTreatyGameData.lCivsData_Aggressors.get(i).iCivID).getNumOfProvinces(); j++) {
            if (CFG.peaceTreatyData.peaceTreatyGameData.lCivsData_Aggressors.get(i).iCivID
               == CFG.game
                  .getProvince(CFG.game.getCiv(CFG.peaceTreatyData.peaceTreatyGameData.lCivsData_Aggressors.get(i).iCivID).getProvinceID(j))
                  .getTrueOwnerOfProvince()) {
               tempNumOfProvincesInWar_Aggrersors += CFG.game
                  .getProvinceValue(CFG.game.getCiv(CFG.peaceTreatyData.peaceTreatyGameData.lCivsData_Aggressors.get(i).iCivID).getProvinceID(j));
            } else {
               for (int k = 0; k < CFG.peaceTreatyData.peaceTreatyGameData.lCivsData_Defenders.size(); k++) {
                  if (CFG.peaceTreatyData.peaceTreatyGameData.lCivsData_Defenders.get(k).iCivID
                     == CFG.game
                        .getProvince(CFG.game.getCiv(CFG.peaceTreatyData.peaceTreatyGameData.lCivsData_Aggressors.get(i).iCivID).getProvinceID(j))
                        .getTrueOwnerOfProvince()) {
                     tempControledEnemyProvinces_ByAggrersors += CFG.game
                        .getProvinceValue(CFG.game.getCiv(CFG.peaceTreatyData.peaceTreatyGameData.lCivsData_Aggressors.get(i).iCivID).getProvinceID(j));
                     break;
                  }
               }
            }
         }
      }

      for (int var15 = 0; var15 < CFG.peaceTreatyData.peaceTreatyGameData.lCivsData_Defenders.size(); var15++) {
         for (int jx = 0; jx < CFG.game.getCiv(CFG.peaceTreatyData.peaceTreatyGameData.lCivsData_Defenders.get(var15).iCivID).getNumOfProvinces(); jx++) {
            if (CFG.peaceTreatyData.peaceTreatyGameData.lCivsData_Defenders.get(var15).iCivID
               == CFG.game
                  .getProvince(CFG.game.getCiv(CFG.peaceTreatyData.peaceTreatyGameData.lCivsData_Defenders.get(var15).iCivID).getProvinceID(jx))
                  .getTrueOwnerOfProvince()) {
               tempNumOfProvincesInWar_Defenders += CFG.game
                  .getProvinceValue(CFG.game.getCiv(CFG.peaceTreatyData.peaceTreatyGameData.lCivsData_Defenders.get(var15).iCivID).getProvinceID(jx));
            } else {
               for (int kx = 0; kx < CFG.peaceTreatyData.peaceTreatyGameData.lCivsData_Aggressors.size(); kx++) {
                  if (CFG.peaceTreatyData.peaceTreatyGameData.lCivsData_Aggressors.get(kx).iCivID
                     == CFG.game
                        .getProvince(CFG.game.getCiv(CFG.peaceTreatyData.peaceTreatyGameData.lCivsData_Defenders.get(var15).iCivID).getProvinceID(jx))
                        .getTrueOwnerOfProvince()) {
                     tempControledEnemyProvinces_ByDefenders += CFG.game
                        .getProvinceValue(CFG.game.getCiv(CFG.peaceTreatyData.peaceTreatyGameData.lCivsData_Defenders.get(var15).iCivID).getProvinceID(jx));
                     break;
                  }
               }
            }
         }
      }

      int tempAggressorsPerc = 0;
      int tempDefendersPerc = 0;

      try {
         tempAggressorsPerc = (int)(
            (float)tempControledEnemyProvinces_ByAggrersors
               / (tempNumOfProvincesInWar_Defenders + tempControledEnemyProvinces_ByDefenders + tempControledEnemyProvinces_ByAggrersors)
               * 100.0F
         );
      } catch (ArithmeticException var12) {
         tempAggressorsPerc = 0;
      }

      try {
         tempDefendersPerc = (int)(
            (float)tempControledEnemyProvinces_ByDefenders
               / (tempNumOfProvincesInWar_Aggrersors + tempControledEnemyProvinces_ByAggrersors + tempControledEnemyProvinces_ByDefenders)
               * 100.0F
         );
      } catch (ArithmeticException var11) {
         tempDefendersPerc = 0;
      }

      return -tempAggressorsPerc + tempDefendersPerc;
   }

   public final int getWarScore_DefendersInProvinceValue() {
      int outScore = 0;

      for (int i = 0; i < this.getAggressorsSize(); i++) {
         for (int j = 0; j < CFG.game.getCiv(this.getAggressorID(i).getCivID()).getNumOfProvinces(); j++) {
            if (this.getAggressorID(i).getCivID()
               != CFG.game.getProvince(CFG.game.getCiv(this.getAggressorID(i).getCivID()).getProvinceID(j)).getTrueOwnerOfProvince()) {
               for (int k = 0; k < this.getDefendersSize(); k++) {
                  if (this.getDefenderID(k).getCivID()
                     == CFG.game.getProvince(CFG.game.getCiv(this.getAggressorID(i).getCivID()).getProvinceID(j)).getTrueOwnerOfProvince()) {
                     outScore += CFG.game.getProvinceValue(CFG.game.getCiv(this.getAggressorID(i).getCivID()).getProvinceID(j));
                     break;
                  }
               }
            }
         }
      }

      for (int var7 = 0; var7 < this.getDefendersSize(); var7++) {
         for (int jx = 0; jx < CFG.game.getCiv(this.getDefenderID(var7).getCivID()).getNumOfProvinces(); jx++) {
            if (this.getDefenderID(var7).getCivID()
               != CFG.game.getProvince(CFG.game.getCiv(this.getDefenderID(var7).getCivID()).getProvinceID(jx)).getTrueOwnerOfProvince()) {
               for (int kx = 0; kx < this.getAggressorsSize(); kx++) {
                  if (this.getAggressorID(kx).getCivID()
                     == CFG.game.getProvince(CFG.game.getCiv(this.getDefenderID(var7).getCivID()).getProvinceID(jx)).getTrueOwnerOfProvince()) {
                     outScore -= CFG.game.getProvinceValue(CFG.game.getCiv(this.getDefenderID(var7).getCivID()).getProvinceID(jx));
                     break;
                  }
               }
            }
         }
      }

      return outScore;
   }

   public final int getWarScore_DefendersInProvinceValue(int id) {
      int outScore = 0;

      for (int i = 0; i < this.getAggressorsSize(); i++) {
         for (int j = 0; j < CFG.game.getCiv(this.getAggressorID(i).getCivID()).getNumOfProvinces(); j++) {
            if (this.getAggressorID(i).getCivID()
                  != CFG.game.getProvince(CFG.game.getCiv(this.getAggressorID(i).getCivID()).getProvinceID(j)).getTrueOwnerOfProvince()
               && this.getDefenderID(id).getCivID()
                  == CFG.game.getProvince(CFG.game.getCiv(this.getAggressorID(i).getCivID()).getProvinceID(j)).getTrueOwnerOfProvince()) {
               outScore -= CFG.game.getProvinceValue(CFG.game.getCiv(this.getAggressorID(i).getCivID()).getProvinceID(j));
            }
         }
      }

      for (int jx = 0; jx < CFG.game.getCiv(this.getDefenderID(id).getCivID()).getNumOfProvinces(); jx++) {
         if (this.getDefenderID(id).getCivID()
            != CFG.game.getProvince(CFG.game.getCiv(this.getDefenderID(id).getCivID()).getProvinceID(jx)).getTrueOwnerOfProvince()) {
            for (int k = 0; k < this.getAggressorsSize(); k++) {
               if (this.getAggressorID(k).getCivID()
                  == CFG.game.getProvince(CFG.game.getCiv(this.getDefenderID(id).getCivID()).getProvinceID(jx)).getTrueOwnerOfProvince()) {
                  outScore += CFG.game.getProvinceValue(CFG.game.getCiv(this.getDefenderID(id).getCivID()).getProvinceID(jx));
                  break;
               }
            }
         }
      }

      return outScore;
   }

   public final int getWarScore_DefendersInProvinceValue_OnlyPositive(int id, List<Boolean> addDefender, List<Boolean> addAggressor) {
      int outScore = 0;
      int iMinScore = 0;
      ArrayList<War_Points> nPoints = new ArrayList<>();

      for (int k = 0; k < this.getAggressorsSize(); k++) {
         nPoints.add(new War_Points(this.getAggressorID(k).getCivID()));
      }

      for (int j = 0; j < CFG.game.getCiv(this.getDefenderID(id).getCivID()).getNumOfProvinces(); j++) {
         if (this.getDefenderID(id).getCivID()
            != CFG.game.getProvince(CFG.game.getCiv(this.getDefenderID(id).getCivID()).getProvinceID(j)).getTrueOwnerOfProvince()) {
            for (int k = 0; k < this.getAggressorsSize(); k++) {
               if (addAggressor.get(k)
                  && this.getAggressorID(k).getCivID()
                     == CFG.game.getProvince(CFG.game.getCiv(this.getDefenderID(id).getCivID()).getProvinceID(j)).getTrueOwnerOfProvince()) {
                  int nValue = CFG.game.getProvinceValue(CFG.game.getCiv(this.getDefenderID(id).getCivID()).getProvinceID(j));
                  if (nValue > iMinScore) {
                     iMinScore = nValue;
                  }

                  nPoints.get(k).addPoints(nValue);
                  break;
               }
            }
         }
      }

      int defenderNumOfTrueProvinces = 0;

      for (int i = 0; i < CFG.game.getCiv(this.getDefenderID(id).getCivID()).getNumOfProvinces(); i++) {
         if (CFG.game.getProvince(CFG.game.getCiv(this.getDefenderID(id).getCivID()).getProvinceID(i)).getTrueOwnerOfProvince()
            == this.getDefenderID(id).getCivID()) {
            defenderNumOfTrueProvinces++;
         }
      }

      for (int var12 = nPoints.size() - 1; var12 >= 0; var12--) {
         if (!(defenderNumOfTrueProvinces >= nPoints.get(var12).getNumOfProvincesTotal() * 2.5F)
            && nPoints.get(var12).getNumOfProvincesTotal() > 2
            && nPoints.get(var12).iNumOfLostProvinces > 2) {
            float fModifer = 1.0F;

            try {
               fModifer = nPoints.get(var12).getNumOfProvincesTotal() == 3
                  ? 0.7F
                     + 0.3F
                        * (
                           1.0F
                              - Math.min(
                                 (float)this.getAggressorID(var12).getConqueredProvinces() / Math.max(this.getDefenderID(id).getConqueredProvinces(), 1), 1.0F
                              )
                        )
                  : (
                     defenderNumOfTrueProvinces < nPoints.get(var12).getNumOfProvincesTotal()
                        ? 0.2F
                           + 0.1F * (nPoints.get(var12).iNumOfLostProvinces / nPoints.get(var12).getNumOfProvincesTotal())
                           + 0.2F * (1.0F - (float)defenderNumOfTrueProvinces / nPoints.get(var12).getNumOfProvincesTotal())
                           + 0.35F
                              * (
                                 1.0F
                                    - Math.min(
                                       (float)this.getAggressorID(var12).getConqueredProvinces() / Math.max(this.getDefenderID(id).getConqueredProvinces(), 1),
                                       1.0F
                                    )
                              )
                        : 0.2F
                           + 0.1F * (nPoints.get(var12).iNumOfLostProvinces / nPoints.get(var12).getNumOfProvincesTotal())
                           + 0.35F
                              * (
                                 1.0F
                                    - Math.min(
                                       (float)this.getAggressorID(var12).getConqueredProvinces() / Math.max(this.getDefenderID(id).getConqueredProvinces(), 1),
                                       1.0F
                                    )
                              )
                  );
            } catch (IllegalArgumentException var11) {
               fModifer = 0.7F;
               CFG.exceptionStack(var11);
            }

            outScore += (int)Math.max(Math.ceil(nPoints.get(var12).iPoints * fModifer), (double)nPoints.get(var12).iMinScore);
         } else {
            outScore += nPoints.get(var12).iPoints;
         }
      }

      return Math.max(outScore, iMinScore);
   }

   public final int getWarScore_AggressorsInProvinceValue() {
      int outScore = 0;

      for (int i = 0; i < this.getAggressorsSize(); i++) {
         for (int j = 0; j < CFG.game.getCiv(this.getAggressorID(i).getCivID()).getNumOfProvinces(); j++) {
            if (this.getAggressorID(i).getCivID()
               != CFG.game.getProvince(CFG.game.getCiv(this.getAggressorID(i).getCivID()).getProvinceID(j)).getTrueOwnerOfProvince()) {
               for (int k = 0; k < this.getDefendersSize(); k++) {
                  if (this.getDefenderID(k).getCivID()
                     == CFG.game.getProvince(CFG.game.getCiv(this.getAggressorID(i).getCivID()).getProvinceID(j)).getTrueOwnerOfProvince()) {
                     outScore -= CFG.game.getProvinceValue(CFG.game.getCiv(this.getAggressorID(i).getCivID()).getProvinceID(j));
                     break;
                  }
               }
            }
         }
      }

      for (int var7 = 0; var7 < this.getDefendersSize(); var7++) {
         for (int jx = 0; jx < CFG.game.getCiv(this.getDefenderID(var7).getCivID()).getNumOfProvinces(); jx++) {
            if (this.getDefenderID(var7).getCivID()
               != CFG.game.getProvince(CFG.game.getCiv(this.getDefenderID(var7).getCivID()).getProvinceID(jx)).getTrueOwnerOfProvince()) {
               for (int kx = 0; kx < this.getAggressorsSize(); kx++) {
                  if (this.getAggressorID(kx).getCivID()
                     == CFG.game.getProvince(CFG.game.getCiv(this.getDefenderID(var7).getCivID()).getProvinceID(jx)).getTrueOwnerOfProvince()) {
                     outScore += CFG.game.getProvinceValue(CFG.game.getCiv(this.getDefenderID(var7).getCivID()).getProvinceID(jx));
                     break;
                  }
               }
            }
         }
      }

      return outScore;
   }

   public final int getWarScore_AggressorsInProvinceValue(int id) {
      int outScore = 0;

      for (int j = 0; j < CFG.game.getCiv(this.getAggressorID(id).getCivID()).getNumOfProvinces(); j++) {
         if (this.getAggressorID(id).getCivID()
            != CFG.game.getProvince(CFG.game.getCiv(this.getAggressorID(id).getCivID()).getProvinceID(j)).getTrueOwnerOfProvince()) {
            for (int k = 0; k < this.getDefendersSize(); k++) {
               if (this.getDefenderID(k).getCivID()
                  == CFG.game.getProvince(CFG.game.getCiv(this.getAggressorID(id).getCivID()).getProvinceID(j)).getTrueOwnerOfProvince()) {
                  outScore += CFG.game.getProvinceValue(CFG.game.getCiv(this.getAggressorID(id).getCivID()).getProvinceID(j));
                  break;
               }
            }
         }
      }

      for (int i = 0; i < this.getDefendersSize(); i++) {
         for (int jx = 0; jx < CFG.game.getCiv(this.getDefenderID(i).getCivID()).getNumOfProvinces(); jx++) {
            if (this.getDefenderID(i).getCivID()
                  != CFG.game.getProvince(CFG.game.getCiv(this.getDefenderID(i).getCivID()).getProvinceID(jx)).getTrueOwnerOfProvince()
               && this.getAggressorID(id).getCivID()
                  == CFG.game.getProvince(CFG.game.getCiv(this.getDefenderID(i).getCivID()).getProvinceID(jx)).getTrueOwnerOfProvince()) {
               outScore -= CFG.game.getProvinceValue(CFG.game.getCiv(this.getDefenderID(i).getCivID()).getProvinceID(jx));
            }
         }
      }

      return outScore;
   }

   public final int getWarScore_AggressorsInProvinceValue_OnlyPositive(int id, List<Boolean> addDefender, List<Boolean> addAggressor) {
      int outScore = 0;
      int iMinScore = 0;
      ArrayList<War_Points> nPoints = new ArrayList<>();

      for (int k = 0; k < this.getDefendersSize(); k++) {
         nPoints.add(new War_Points(this.getDefenderID(k).getCivID()));
      }

      for (int j = 0; j < CFG.game.getCiv(this.getAggressorID(id).getCivID()).getNumOfProvinces(); j++) {
         if (this.getAggressorID(id).getCivID()
            != CFG.game.getProvince(CFG.game.getCiv(this.getAggressorID(id).getCivID()).getProvinceID(j)).getTrueOwnerOfProvince()) {
            for (int k = 0; k < this.getDefendersSize(); k++) {
               if (addDefender.get(k)
                  && this.getDefenderID(k).getCivID()
                     == CFG.game.getProvince(CFG.game.getCiv(this.getAggressorID(id).getCivID()).getProvinceID(j)).getTrueOwnerOfProvince()) {
                  int nValue = CFG.game.getProvinceValue(CFG.game.getCiv(this.getAggressorID(id).getCivID()).getProvinceID(j));
                  if (nValue > iMinScore) {
                     iMinScore = nValue;
                  }

                  nPoints.get(k).addPoints(nValue);
                  break;
               }
            }
         }
      }

      int defenderNumOfTrueProvinces = 0;

      for (int i = 0; i < CFG.game.getCiv(this.getAggressorID(id).getCivID()).getNumOfProvinces(); i++) {
         if (CFG.game.getProvince(CFG.game.getCiv(this.getAggressorID(id).getCivID()).getProvinceID(i)).getTrueOwnerOfProvince()
            == this.getAggressorID(id).getCivID()) {
            defenderNumOfTrueProvinces++;
         }
      }

      for (int var12 = nPoints.size() - 1; var12 >= 0; var12--) {
         if (!(defenderNumOfTrueProvinces >= nPoints.get(var12).getNumOfProvincesTotal() * 2.5F)
            && nPoints.get(var12).getNumOfProvincesTotal() > 2
            && nPoints.get(var12).iNumOfLostProvinces > 2) {
            float fModifer = 1.0F;

            try {
               fModifer = nPoints.get(var12).getNumOfProvincesTotal() == 3
                  ? 0.7F
                     + 0.3F
                        * (
                           1.0F
                              - Math.min(
                                 (float)this.getDefenderID(var12).getConqueredProvinces() / Math.max(this.getAggressorID(id).getConqueredProvinces(), 1), 1.0F
                              )
                        )
                  : (
                     defenderNumOfTrueProvinces < nPoints.get(var12).getNumOfProvincesTotal()
                        ? 0.2F
                           + 0.1F * (nPoints.get(var12).iNumOfLostProvinces / nPoints.get(var12).getNumOfProvincesTotal())
                           + 0.2F * (1.0F - (float)defenderNumOfTrueProvinces / nPoints.get(var12).getNumOfProvincesTotal())
                           + 0.35F
                              * (
                                 1.0F
                                    - Math.min(
                                       (float)this.getDefenderID(var12).getConqueredProvinces() / Math.max(this.getAggressorID(id).getConqueredProvinces(), 1),
                                       1.0F
                                    )
                              )
                        : 0.2F
                           + 0.1F * (nPoints.get(var12).iNumOfLostProvinces / nPoints.get(var12).getNumOfProvincesTotal())
                           + 0.35F
                              * (
                                 1.0F
                                    - Math.min(
                                       (float)this.getDefenderID(var12).getConqueredProvinces() / Math.max(this.getAggressorID(id).getConqueredProvinces(), 1),
                                       1.0F
                                    )
                              )
                  );
            } catch (IllegalArgumentException var11) {
               fModifer = 0.7F;
               CFG.exceptionStack(var11);
            }

            outScore += (int)Math.max(Math.ceil(nPoints.get(var12).iPoints * fModifer), (double)nPoints.get(var12).iMinScore);
         } else {
            outScore += nPoints.get(var12).iPoints;
         }
      }

      return Math.max(outScore, iMinScore);
   }

   public final PeaceTreaty_Civs getDefenders_ProvincesLost(int id, List<Boolean> addDefender, List<Boolean> addAggressor) {
      PeaceTreaty_Civs outPC = new PeaceTreaty_Civs(this.getDefenderID(id).getCivID());

      for (int i = 0; i < this.getAggressorsSize(); i++) {
         if (addAggressor.get(i)) {
            for (int j = 0; j < CFG.game.getCiv(this.getAggressorID(i).getCivID()).getNumOfProvinces(); j++) {
               if (this.getAggressorID(i).getCivID()
                     != CFG.game.getProvince(CFG.game.getCiv(this.getAggressorID(i).getCivID()).getProvinceID(j)).getTrueOwnerOfProvince()
                  && this.getDefenderID(id).getCivID()
                     == CFG.game.getProvince(CFG.game.getCiv(this.getAggressorID(i).getCivID()).getProvinceID(j)).getTrueOwnerOfProvince()) {
                  outPC.lProvincesLost.add(CFG.game.getCiv(this.getAggressorID(i).getCivID()).getProvinceID(j));
               }
            }
         }
      }

      return outPC;
   }

   public final PeaceTreaty_Civs getAggressors_ProvincesLost(int id, List<Boolean> addDefender, List<Boolean> addAggressor) {
      PeaceTreaty_Civs outPC = new PeaceTreaty_Civs(this.getAggressorID(id).getCivID());

      for (int i = 0; i < this.getDefendersSize(); i++) {
         if (addDefender.get(i)) {
            for (int j = 0; j < CFG.game.getCiv(this.getDefenderID(i).getCivID()).getNumOfProvinces(); j++) {
               if (this.getDefenderID(i).getCivID()
                     != CFG.game.getProvince(CFG.game.getCiv(this.getDefenderID(i).getCivID()).getProvinceID(j)).getTrueOwnerOfProvince()
                  && this.getAggressorID(id).getCivID()
                     == CFG.game.getProvince(CFG.game.getCiv(this.getDefenderID(i).getCivID()).getProvinceID(j)).getTrueOwnerOfProvince()) {
                  outPC.lProvincesLost.add(CFG.game.getCiv(this.getDefenderID(i).getCivID()).getProvinceID(j));
               }
            }
         }
      }

      return outPC;
   }

   public final int getProvinces_Aggressor_Own(int i) {
      int out = 0;

      for (int j = 0; j < CFG.game.getCiv(this.getAggressorID(i).getCivID()).getNumOfProvinces(); j++) {
         if (this.getAggressorID(i).getCivID()
            == CFG.game.getProvince(CFG.game.getCiv(this.getAggressorID(i).getCivID()).getProvinceID(j)).getTrueOwnerOfProvince()) {
            out++;
         }
      }

      for (int k = 0; k < this.getDefendersSize(); k++) {
         for (int jx = 0; jx < CFG.game.getCiv(this.getDefenderID(k).getCivID()).getNumOfProvinces(); jx++) {
            if (this.getAggressorID(i).getCivID()
               == CFG.game.getProvince(CFG.game.getCiv(this.getDefenderID(k).getCivID()).getProvinceID(jx)).getTrueOwnerOfProvince()) {
               out++;
            }
         }
      }

      return out;
   }

   public final int getProvinces_Aggressor_OwnTotal(int i) {
      int out = 0;

      for (int j = 0; j < CFG.game.getCiv(this.getAggressorID(i).getCivID()).getNumOfProvinces(); j++) {
         if (this.getAggressorID(i).getCivID()
            == CFG.game.getProvince(CFG.game.getCiv(this.getAggressorID(i).getCivID()).getProvinceID(j)).getTrueOwnerOfProvince()) {
            out++;
         } else {
            for (int k = 0; k < this.getDefendersSize(); k++) {
               if (this.getDefenderID(k).getCivID()
                  == CFG.game.getProvince(CFG.game.getCiv(this.getAggressorID(i).getCivID()).getProvinceID(j)).getTrueOwnerOfProvince()) {
                  out++;
                  break;
               }
            }
         }
      }

      return out;
   }

   public final int getProvinces_Defender_Own(int i) {
      int out = 0;

      for (int j = 0; j < CFG.game.getCiv(this.getDefenderID(i).getCivID()).getNumOfProvinces(); j++) {
         if (this.getDefenderID(i).getCivID()
            == CFG.game.getProvince(CFG.game.getCiv(this.getDefenderID(i).getCivID()).getProvinceID(j)).getTrueOwnerOfProvince()) {
            out++;
         }
      }

      for (int k = 0; k < this.getAggressorsSize(); k++) {
         for (int jx = 0; jx < CFG.game.getCiv(this.getAggressorID(k).getCivID()).getNumOfProvinces(); jx++) {
            if (this.getDefenderID(i).getCivID()
               == CFG.game.getProvince(CFG.game.getCiv(this.getAggressorID(k).getCivID()).getProvinceID(jx)).getTrueOwnerOfProvince()) {
               out++;
            }
         }
      }

      return out;
   }

   public final int getProvinces_Defender_OwnTotal(int i) {
      int out = 0;

      for (int j = 0; j < CFG.game.getCiv(this.getDefenderID(i).getCivID()).getNumOfProvinces(); j++) {
         if (this.getDefenderID(i).getCivID()
            == CFG.game.getProvince(CFG.game.getCiv(this.getDefenderID(i).getCivID()).getProvinceID(j)).getTrueOwnerOfProvince()) {
            out++;
         } else {
            for (int k = 0; k < this.getAggressorsSize(); k++) {
               if (this.getAggressorID(k).getCivID()
                  == CFG.game.getProvince(CFG.game.getCiv(this.getDefenderID(i).getCivID()).getProvinceID(j)).getTrueOwnerOfProvince()) {
                  out++;
                  break;
               }
            }
         }
      }

      return out;
   }

   public final WarCiv_GameData getAggressorID(int i) {
      return this.lAggressors.get(i);
   }

   public final int getAggressorID_ByCivID(int nCivID) {
      for (int i = 0; i < this.getAggressorsSize(); i++) {
         if (this.getAggressorID(i).getCivID() == nCivID) {
            return i;
         }
      }

      return -1;
   }

   public final boolean getIsInAggressors(int nCivID) {
      for (int i = 0; i < this.getAggressorsSize(); i++) {
         if (this.getAggressorID(i).getCivID() == nCivID) {
            return true;
         }
      }

      return false;
   }

   public final int getAggressorsSize() {
      return this.lAggressors.size();
   }

   public final WarCiv_GameData getDefenderID(int i) {
      return this.lDefenders.get(i);
   }

   public final int getDefenderID_ByCivID(int nCivID) {
      for (int i = 0; i < this.getDefendersSize(); i++) {
         if (this.getDefenderID(i).getCivID() == nCivID) {
            return i;
         }
      }

      return -1;
   }

   public final boolean getIsInDefenders(int nCivID) {
      for (int i = 0; i < this.getDefendersSize(); i++) {
         if (this.getDefenderID(i).getCivID() == nCivID) {
            return true;
         }
      }

      return false;
   }

   public final int getDefendersSize() {
      return this.lDefenders.size();
   }

   public final int getParticipation_DefenderID(int nID) {
      int out = 0;

      for (int i = 0; i < this.getDefendersSize(); i++) {
         out += this.getDefenderID(i).getCasualties();
      }

      return out == 0
         ? 100
         : (int)(
            nID == 0
               ? Math.ceil((float)this.getDefenderID(nID).getCasualties() / out * 100.0F)
               : Math.floor((float)this.getDefenderID(nID).getCasualties() / out * 100.0F)
         );
   }

   public final int getParticipation_AggressorID(int nID) {
      int out = 0;

      for (int i = 0; i < this.getAggressorsSize(); i++) {
         out += this.getAggressorID(i).getCasualties();
      }

      return out == 0
         ? 100
         : (int)(
            nID == 0
               ? Math.ceil((float)this.getAggressorID(nID).getCasualties() / out * 100.0F)
               : Math.floor((float)this.getAggressorID(nID).getCasualties() / out * 100.0F)
         );
   }

   public final void addConqueredProvinces(int iCivID) {
      this.iLastTurn_ConqueredProvince = Game_Calendar.TURN_ID;

      for (int i = 0; i < this.getDefendersSize(); i++) {
         if (this.getDefenderID(i).getCivID() == iCivID) {
            this.getDefenderID(i).addConqueredProvinces();
            return;
         }
      }

      for (int var3 = 0; var3 < this.getAggressorsSize(); var3++) {
         if (this.getAggressorID(var3).getCivID() == iCivID) {
            this.getAggressorID(var3).addConqueredProvinces();
            return;
         }
      }
   }

   public final void addCasualties(int iCivID, int iCasualties) {
      this.iLastFight_InTunrs = 0;
      this.wasAnyAttack = true;

      for (int i = 0; i < this.getDefendersSize(); i++) {
         if (this.getDefenderID(i).getCivID() == iCivID) {
            this.getDefenderID(i).addCasualties(iCasualties);
            return;
         }
      }

      for (int var4 = 0; var4 < this.getAggressorsSize(); var4++) {
         if (this.getAggressorID(var4).getCivID() == iCivID) {
            this.getAggressorID(var4).addCasualties(iCasualties);
            return;
         }
      }
   }

   public final void addCivilianEconomicLosses(int iCivID, int iCivilianDeaths, int iEconomicLosses) {
      this.iLastFight_InTunrs = 0;

      for (int i = 0; i < this.getDefendersSize(); i++) {
         if (this.getDefenderID(i).getCivID() == iCivID) {
            this.getDefenderID(i).addCivilianDeaths(iCivilianDeaths);
            this.getDefenderID(i).addEconomicLosses(iEconomicLosses);
            return;
         }
      }

      for (int var5 = 0; var5 < this.getAggressorsSize(); var5++) {
         if (this.getAggressorID(var5).getCivID() == iCivID) {
            this.getAggressorID(var5).addCivilianDeaths(iCivilianDeaths);
            this.getAggressorID(var5).addEconomicLosses(iEconomicLosses);
            return;
         }
      }
   }

   public final int getCasualties_Defenders() {
      int out = 0;

      for (int i = 0; i < this.getDefendersSize(); i++) {
         out += this.getDefenderID(i).getCasualties();
         out += this.getDefenderID(i).getCivilianDeaths();
      }

      return out;
   }

   public final int getCasualties_Aggressors() {
      int out = 0;

      for (int i = 0; i < this.getAggressorsSize(); i++) {
         out += this.getAggressorID(i).getCasualties();
         out += this.getAggressorID(i).getCivilianDeaths();
      }

      return out;
   }

   public final int getWarTurnID() {
      return this.iWarTurnID;
   }

   public final void setWarTurnID(int iWarTurnID) {
      this.iWarTurnID = iWarTurnID;
   }
}
