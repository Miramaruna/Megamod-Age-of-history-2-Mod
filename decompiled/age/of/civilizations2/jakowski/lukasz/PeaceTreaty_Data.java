package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.Gdx;
import java.util.ArrayList;
import java.util.List;

class PeaceTreaty_Data {
   protected PeaceTreaty_GameData peaceTreatyGameData = new PeaceTreaty_GameData();
   protected List<PeaceTreaty_DrawData> drawProvinceOwners = new ArrayList<>();
   protected List<Integer> provincesLeftToTake = new ArrayList<>();
   protected int iProvincesLeftToTakeSize = 0;
   protected boolean scoreCountDefenders = false;
   protected int iBrushCivID = -1;
   protected int iPlayerTurnID = 0;
   protected int iLastTakenID = -1;
   protected static final float VASSALIZE_COST = 0.4F;
   protected static final float WAR_REPARATIONS_COST = 0.1F;

   protected PeaceTreaty_Data() {
   }

   protected PeaceTreaty_Data(PeaceTreaty_GameData nPeaceTreaty) {
      this.peaceTreatyGameData = nPeaceTreaty;
      this.iBrushCivID = CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID();
      this.iPlayerTurnID = CFG.PLAYER_TURNID;
      this.prepareProvinceData(false);
      this.prepareDemansVassalsData();
   }

   protected PeaceTreaty_Data(int iWarID, boolean scoreCountDefenders) {
      ArrayList<Boolean> addDefender = new ArrayList<>();
      ArrayList<Boolean> addAggressor = new ArrayList<>();

      for (int i = 0; i < CFG.game.getWar(iWarID).getDefendersSize(); i++) {
         addDefender.add(true);
      }

      for (int var6 = 0; var6 < CFG.game.getWar(iWarID).getAggressorsSize(); var6++) {
         addAggressor.add(true);
      }

      this.initPeaceTreatyData(iWarID, addDefender, addAggressor, scoreCountDefenders);
   }

   protected PeaceTreaty_Data(int iWarID, List<Boolean> addDefender, List<Boolean> addAggressor, boolean scoreCountDefenders) {
      this.initPeaceTreatyData(iWarID, addDefender, addAggressor, scoreCountDefenders);
   }

   protected final void AI_UseVictoryPoints() {
      try {
         Gdx.app.log("AoC", "AI_UseVictoryPoints -> provincesLeftToTake.size: " + this.iProvincesLeftToTakeSize);
         if (this.iProvincesLeftToTakeSize > 0) {
            int iBestCivID = -1;
            int tBestPoints = -1;

            for (int i = 0; i < this.peaceTreatyGameData.lCivsDemands_Defenders.size(); i++) {
               if (this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).iVictoryPointsLeft > tBestPoints) {
                  iBestCivID = this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).iCivID;
                  tBestPoints = this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).iVictoryPointsLeft;
               } else if (this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).iVictoryPointsLeft == tBestPoints && CFG.oR.nextInt(100) < 50) {
                  iBestCivID = this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).iCivID;
                  tBestPoints = this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).iVictoryPointsLeft;
               }
            }

            for (int var5 = 0; var5 < this.peaceTreatyGameData.lCivsDemands_Aggressors.size(); var5++) {
               if (this.peaceTreatyGameData.lCivsDemands_Aggressors.get(var5).iVictoryPointsLeft > tBestPoints) {
                  iBestCivID = this.peaceTreatyGameData.lCivsDemands_Aggressors.get(var5).iCivID;
                  tBestPoints = this.peaceTreatyGameData.lCivsDemands_Aggressors.get(var5).iVictoryPointsLeft;
               } else if (this.peaceTreatyGameData.lCivsDemands_Aggressors.get(var5).iVictoryPointsLeft == tBestPoints && CFG.oR.nextInt(100) < 50) {
                  iBestCivID = this.peaceTreatyGameData.lCivsDemands_Aggressors.get(var5).iCivID;
                  tBestPoints = this.peaceTreatyGameData.lCivsDemands_Aggressors.get(var5).iVictoryPointsLeft;
               }
            }

            Gdx.app
               .log(
                  "AoC",
                  "AI_UseVictoryPoints -> iBestCivID: "
                     + iBestCivID
                     + (iBestCivID >= 0 ? ", " + CFG.game.getCiv(iBestCivID).getCivName() : "")
                     + ", tBestPoints: "
                     + tBestPoints
               );
            if (iBestCivID > 0 && tBestPoints > 0 && !CFG.game.getCiv(iBestCivID).getControlledByPlayer()) {
               Gdx.app.log("AoC", "AI_UseVictoryPoints -> AI TAKE PROVINCE");
               this.AI_UseVictoryPoints_CivID(iBestCivID, tBestPoints);
            }
         }
      } catch (StackOverflowError var4) {
      }
   }

   protected final void AI_UseVictoryPoints_CivID_TakeVassal(int nCivID, int pointsLeft, boolean clearPoints) {
      try {
         ArrayList<Integer> canVassalizeCivs = new ArrayList<>();
         boolean doneCheck = false;

         for (int i = 0; i < this.peaceTreatyGameData.lCivsDemands_Defenders.size(); i++) {
            if (this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).iCivID == nCivID) {
               for (int o = 0; o < this.peaceTreatyGameData.lCivsDemands_Aggressors.size(); o++) {
                  if (this.peaceTreatyGameData.lCivsDemands_Aggressors.get(o).iWillBecomeVassalOfCivID < 0
                     && this.getVassalization_Cost(this.peaceTreatyGameData.lCivsDemands_Aggressors.get(o).iCivID) <= pointsLeft) {
                     canVassalizeCivs.add(this.peaceTreatyGameData.lCivsDemands_Aggressors.get(o).iCivID);
                  }
               }

               doneCheck = true;
               break;
            }
         }

         if (!doneCheck) {
            for (int var10 = 0; var10 < this.peaceTreatyGameData.lCivsDemands_Aggressors.size(); var10++) {
               if (this.peaceTreatyGameData.lCivsDemands_Aggressors.get(var10).iCivID == nCivID) {
                  for (int ox = 0; ox < this.peaceTreatyGameData.lCivsDemands_Defenders.size(); ox++) {
                     if (this.peaceTreatyGameData.lCivsDemands_Defenders.get(ox).iWillBecomeVassalOfCivID < 0
                        && this.getVassalization_Cost(this.peaceTreatyGameData.lCivsDemands_Defenders.get(ox).iCivID) <= pointsLeft) {
                        canVassalizeCivs.add(this.peaceTreatyGameData.lCivsDemands_Defenders.get(ox).iCivID);
                     }
                  }

                  doneCheck = true;
                  break;
               }
            }
         }

         if (canVassalizeCivs.size() > 0) {
            this.takeVassalize(canVassalizeCivs.get(CFG.oR.nextInt(canVassalizeCivs.size())), nCivID, nCivID);
            this.AI_UseVictoryPoints();
            return;
         }

         if (clearPoints) {
            for (int var11 = 0; var11 < this.peaceTreatyGameData.lCivsDemands_Defenders.size(); var11++) {
               if (this.peaceTreatyGameData.lCivsDemands_Defenders.get(var11).iCivID == nCivID) {
                  this.peaceTreatyGameData.lCivsDemands_Defenders.get(var11).iVictoryPointsLeft = 0;
                  this.AI_UseVictoryPoints();
                  return;
               }
            }

            for (int var12 = 0; var12 < this.peaceTreatyGameData.lCivsDemands_Aggressors.size(); var12++) {
               if (this.peaceTreatyGameData.lCivsDemands_Aggressors.get(var12).iCivID == nCivID) {
                  this.peaceTreatyGameData.lCivsDemands_Aggressors.get(var12).iVictoryPointsLeft = 0;
                  this.AI_UseVictoryPoints();
                  return;
               }
            }
         }
      } catch (StackOverflowError var8) {
      }
   }

   public final void AI_UseVictoryPoints_CivID(int nCivID, int pointsLeft) {
      try {
         ArrayList<Float> lScores = new ArrayList<>();
         ArrayList<Boolean> lNeigh = new ArrayList<>();
         ArrayList<Integer> toTake = new ArrayList<>();
         boolean canTakeNieghProvince = false;
         float maxDistance = 1.0E-4F;

         for (int i2 = 0; i2 < this.iProvincesLeftToTakeSize; i2++) {
            if (pointsLeft >= this.drawProvinceOwners.get(this.provincesLeftToTake.get(i2)).iProvinceValue) {
               maxDistance = Math.max(
                  maxDistance, CFG.game_NextTurnUpdate.getDistanceFromCapital(CFG.game.getCiv(nCivID).getCapitalProvinceID(), this.provincesLeftToTake.get(i2))
               );
               if (CFG.game.getProvince(this.provincesLeftToTake.get(i2)).getTrueOwnerOfProvince() == nCivID) {
                  lScores.add(this.AI_UseVictoryPoints_CivID_Score(nCivID, this.provincesLeftToTake.get(i2), 550.0F));
                  lNeigh.add(true);
                  toTake.add(this.provincesLeftToTake.get(i2));
                  canTakeNieghProvince = true;
               } else {
                  boolean tempProvinceAdded = false;

                  for (int j = 0; j < CFG.game.getProvince(this.provincesLeftToTake.get(i2)).getNeighboringProvincesSize(); j++) {
                     if (this.drawProvinceOwners.get(CFG.game.getProvince(this.provincesLeftToTake.get(i2)).getNeighboringProvinces(j)).iCivID == nCivID) {
                        if (CFG.game.getProvince(this.provincesLeftToTake.get(i2)).getCore().getHaveACore(nCivID)) {
                           tempProvinceAdded = true;
                           lScores.add(this.AI_UseVictoryPoints_CivID_Score(nCivID, this.provincesLeftToTake.get(i2), 1000.0F));
                           lNeigh.add(true);
                           toTake.add(this.provincesLeftToTake.get(i2));
                           canTakeNieghProvince = true;
                        } else {
                           lScores.add(this.AI_UseVictoryPoints_CivID_Score(nCivID, this.provincesLeftToTake.get(i2), 100.25F));
                           lNeigh.add(true);
                           toTake.add(this.provincesLeftToTake.get(i2));
                           tempProvinceAdded = true;
                           canTakeNieghProvince = true;
                        }
                        break;
                     }
                  }

                  if (!tempProvinceAdded) {
                     if (CFG.game.getProvince(this.provincesLeftToTake.get(i2)).getCore().getHaveACore(nCivID)) {
                        lScores.add(this.AI_UseVictoryPoints_CivID_Score(nCivID, this.provincesLeftToTake.get(i2), 1000.75F));
                        lNeigh.add(true);
                        toTake.add(this.provincesLeftToTake.get(i2));
                        canTakeNieghProvince = true;
                     } else if (CFG.game.getProvince(this.provincesLeftToTake.get(i2)).getNeighboringSeaProvincesSize() > 0) {
                        lScores.add(this.AI_UseVictoryPoints_CivID_Score(nCivID, this.provincesLeftToTake.get(i2), 0.325F));
                        lNeigh.add(true);
                        toTake.add(this.provincesLeftToTake.get(i2));
                        canTakeNieghProvince = true;
                     } else {
                        lScores.add(this.AI_UseVictoryPoints_CivID_Score(nCivID, this.provincesLeftToTake.get(i2), 0.025F));
                        lNeigh.add(false);
                        toTake.add(this.provincesLeftToTake.get(i2));
                     }
                  }
               }
            }
         }

         if (lNeigh.size() == 0 || toTake.size() == 0) {
            Gdx.app.log("AoC", "AI_UseVictoryPoints -> AI TAKE PROVINCE -> lNeigh.size(): " + lNeigh.size());
            this.AI_UseVictoryPoints_CivID_TakeVassal(nCivID, pointsLeft, true);
            return;
         }

         if (!canTakeNieghProvince) {
            Gdx.app.log("AoC", "AI_UseVictoryPoints -> AI TAKE PROVINCE -> canTakeNieghProvince: " + canTakeNieghProvince);
            this.AI_UseVictoryPoints_CivID_TakeVassal(nCivID, pointsLeft, true);
            return;
         }

         if (CFG.oR.nextInt(100) >= CFG.AI_CREATING_VASSALS) {
            this.AI_UseVictoryPoints_CivID_TakeVassal(nCivID, pointsLeft, true);
         }

         int tBest = 0;

         for (int i = lScores.size() - 1; i > 0; i--) {
            lScores.set(
               i,
               lScores.get(i)
                  * (
                     0.8F
                        + 0.2F
                           * (
                              1.0F
                                 - CFG.game_NextTurnUpdate.getDistanceFromCapital(CFG.game.getCiv(nCivID).getCapitalProvinceID(), toTake.get(i)) / maxDistance
                           )
                  )
                  * (this.iLastTakenID == toTake.get(i) ? 0.05F : 1.0F)
            );
         }

         for (int var13 = lScores.size() - 1; var13 > 0; var13--) {
            if (lScores.get(tBest) < lScores.get(var13)) {
               tBest = var13;
            }
         }

         if (lNeigh.get(tBest)) {
            if (!this.takeProvince(toTake.get(tBest), nCivID, nCivID)) {
               for (int var14 = 0; var14 < this.peaceTreatyGameData.lCivsDemands_Defenders.size(); var14++) {
                  if (this.peaceTreatyGameData.lCivsDemands_Defenders.get(var14).iCivID == nCivID) {
                     this.peaceTreatyGameData.lCivsDemands_Defenders.get(var14).iVictoryPointsLeft = 0;
                     this.AI_UseVictoryPoints();
                     return;
                  }
               }

               for (int var15 = 0; var15 < this.peaceTreatyGameData.lCivsDemands_Aggressors.size(); var15++) {
                  if (this.peaceTreatyGameData.lCivsDemands_Aggressors.get(var15).iCivID == nCivID) {
                     this.peaceTreatyGameData.lCivsDemands_Aggressors.get(var15).iVictoryPointsLeft = 0;
                     this.AI_UseVictoryPoints();
                     return;
                  }
               }
            }
         } else if (toTake.size() == 1) {
            if (!this.takeProvince(toTake.get(tBest), nCivID, nCivID)) {
               for (int var16 = 0; var16 < this.peaceTreatyGameData.lCivsDemands_Defenders.size(); var16++) {
                  if (this.peaceTreatyGameData.lCivsDemands_Defenders.get(var16).iCivID == nCivID) {
                     this.peaceTreatyGameData.lCivsDemands_Defenders.get(var16).iVictoryPointsLeft = 0;
                     this.AI_UseVictoryPoints();
                     return;
                  }
               }

               for (int var17 = 0; var17 < this.peaceTreatyGameData.lCivsDemands_Aggressors.size(); var17++) {
                  if (this.peaceTreatyGameData.lCivsDemands_Aggressors.get(var17).iCivID == nCivID) {
                     this.peaceTreatyGameData.lCivsDemands_Aggressors.get(var17).iVictoryPointsLeft = 0;
                     this.AI_UseVictoryPoints();
                     return;
                  }
               }
            }
         } else {
            for (int var18 = 0; var18 < this.peaceTreatyGameData.lCivsDemands_Defenders.size(); var18++) {
               if (this.peaceTreatyGameData.lCivsDemands_Defenders.get(var18).iCivID == nCivID) {
                  this.peaceTreatyGameData.lCivsDemands_Defenders.get(var18).iVictoryPointsLeft = 0;
                  this.AI_UseVictoryPoints();
                  return;
               }
            }

            for (int var19 = 0; var19 < this.peaceTreatyGameData.lCivsDemands_Aggressors.size(); var19++) {
               if (this.peaceTreatyGameData.lCivsDemands_Aggressors.get(var19).iCivID == nCivID) {
                  this.peaceTreatyGameData.lCivsDemands_Aggressors.get(var19).iVictoryPointsLeft = 0;
                  this.AI_UseVictoryPoints();
                  return;
               }
            }
         }
      } catch (StackOverflowError var12) {
      }
   }

   protected final float AI_UseVictoryPoints_CivID_Score(int nCivID, int nProvinceID, float modifier) {
      int neigh_OwnProvinces = 0;
      int neigh_OtherCivsProvinces = 0;

      for (int i = 0; i < CFG.game.getProvince(nProvinceID).getNeighboringProvincesSize(); i++) {
         if (CFG.game.getProvince(CFG.game.getProvince(nProvinceID).getNeighboringProvinces(i)).getWasteland() < 0
            && CFG.game.getProvince(CFG.game.getProvince(nProvinceID).getNeighboringProvinces(i)).getCivID() > 0) {
            if (this.drawProvinceOwners.get(CFG.game.getProvince(nProvinceID).getNeighboringProvinces(i)).iCivID == nCivID) {
               neigh_OwnProvinces++;
            } else {
               neigh_OtherCivsProvinces++;
            }
         }
      }

      if (CFG.game.getProvince(nProvinceID).getNeighboringSeaProvincesSize() > 0) {
         neigh_OwnProvinces++;
      }

      neigh_OtherCivsProvinces = Math.max(1, neigh_OtherCivsProvinces);
      return modifier
         + (
            neigh_OwnProvinces * (modifier * 0.125F)
               + modifier * (neigh_OwnProvinces / (neigh_OwnProvinces + neigh_OtherCivsProvinces))
               + 0.125F * CFG.game.getProvince(nProvinceID).getPopulationData().getPopulation() / CFG.game.getGameScenarios().getScenario_StartingPopulation()
               + 0.05F * CFG.game.getProvince(nProvinceID).getEconomy() / CFG.game.getGameScenarios().getScenario_StartingEconomy()
               + 0.0075F * CFG.game.getProvince(nProvinceID).getDevelopmentLevel()
         );
   }

   protected final void addProvincesLeftToTake(int nProvinceID) {
      Gdx.app.log("AoC", "addProvincesLeftToTake: nCivID: , nProvinceID: " + CFG.game.getProvince(nProvinceID).getName());

      for (int i = 0; i < this.iProvincesLeftToTakeSize; i++) {
         if (this.provincesLeftToTake.get(i) == nProvinceID) {
            return;
         }
      }

      this.provincesLeftToTake.add(nProvinceID);
      this.iProvincesLeftToTakeSize = this.provincesLeftToTake.size();
   }

   protected final void removeProvincesLeftToTake(int nProvinceID) {
      Gdx.app.log("AoC", "removeProvincesLeftToTake: nCivID: , nProvinceID: " + CFG.game.getProvince(nProvinceID).getName());

      for (int i = 0; i < this.iProvincesLeftToTakeSize; i++) {
         if (this.provincesLeftToTake.get(i) == nProvinceID) {
            this.provincesLeftToTake.remove(i);
            this.iProvincesLeftToTakeSize = this.provincesLeftToTake.size();
            return;
         }
      }
   }

   private final void initPeaceTreatyData(int iWarID, List<Boolean> addDefender, List<Boolean> addAggressor, boolean scoreCountDefenders) {
      try {
         this.peaceTreatyGameData.iWarID = iWarID;
         this.peaceTreatyGameData.WAR_TAG = CFG.game.getWar(iWarID).WAR_TAG;
         this.scoreCountDefenders = scoreCountDefenders;

         for (int i = 0; i < CFG.game.getWar(iWarID).getDefendersSize(); i++) {
            if (addDefender.get(i)) {
               this.peaceTreatyGameData.lCivsData_Defenders.add(CFG.game.getWar(iWarID).getDefenders_ProvincesLost(i, addDefender, addAggressor));
               this.peaceTreatyGameData
                  .lCivsDemands_Defenders
                  .add(
                     new PeaceTreaty_Demands(
                        CFG.game.getWar(iWarID).getDefenderID(i).getCivID(),
                        CFG.game.getWar(iWarID).getWarScore_DefendersInProvinceValue_OnlyPositive(i, addDefender, addAggressor)
                     )
                  );
            }
         }

         for (int var7 = 0; var7 < CFG.game.getWar(iWarID).getAggressorsSize(); var7++) {
            if (addAggressor.get(var7)) {
               this.peaceTreatyGameData.lCivsData_Aggressors.add(CFG.game.getWar(iWarID).getAggressors_ProvincesLost(var7, addDefender, addAggressor));
               this.peaceTreatyGameData
                  .lCivsDemands_Aggressors
                  .add(
                     new PeaceTreaty_Demands(
                        CFG.game.getWar(iWarID).getAggressorID(var7).getCivID(),
                        CFG.game.getWar(iWarID).getWarScore_AggressorsInProvinceValue_OnlyPositive(var7, addDefender, addAggressor)
                     )
                  );
            }
         }

         this.iBrushCivID = CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID();
         this.iPlayerTurnID = CFG.PLAYER_TURNID;
         this.prepareProvinceData(true);
      } catch (IndexOutOfBoundsException var6) {
         CFG.exceptionStack(var6);
      }
   }

   protected final void prepareProvinceData(boolean buildProvincesLost) {
      this.drawProvinceOwners.clear();
      this.drawProvinceOwners = new ArrayList<>();
      ArrayList<Boolean> tempParticipants = new ArrayList<>();

      for (int i = 0; i < CFG.game.getCivsSize(); i++) {
         tempParticipants.add(false);
      }

      for (int var18 = 0; var18 < this.peaceTreatyGameData.lCivsData_Defenders.size(); var18++) {
         tempParticipants.set(this.peaceTreatyGameData.lCivsData_Defenders.get(var18).iCivID, true);
      }

      for (int var19 = 0; var19 < this.peaceTreatyGameData.lCivsData_Aggressors.size(); var19++) {
         tempParticipants.set(this.peaceTreatyGameData.lCivsData_Aggressors.get(var19).iCivID, true);
      }

      for (int var20 = 0; var20 < CFG.game.getProvincesSize(); var20++) {
         if (tempParticipants.get(CFG.game.getProvince(var20).getCivID())) {
            this.drawProvinceOwners.add(new PeaceTreaty_DrawData(CFG.game.getProvince(var20).getCivID(), CFG.game.getProvinceValue(var20), false));
         } else {
            this.drawProvinceOwners.add(new PeaceTreaty_DrawData(CFG.game.getProvince(var20).getCivID() * -1, CFG.game.getProvinceValue(var20), false));
         }
      }

      if (buildProvincesLost) {
         try {
            for (int var21 = this.peaceTreatyGameData.lCivsData_Defenders.size() - 1; var21 >= 0; var21--) {
               for (int j2 = this.peaceTreatyGameData.lCivsData_Defenders.get(var21).lProvincesLost.size() - 1; j2 >= 0; j2--) {
                  this.drawProvinceOwners.get(this.peaceTreatyGameData.lCivsData_Defenders.get(var21).lProvincesLost.get(j2)).isToTake = true;
                  this.drawProvinceOwners.get(this.peaceTreatyGameData.lCivsData_Defenders.get(var21).lProvincesLost.get(j2)).iCivID = CFG.game
                     .getProvince(this.peaceTreatyGameData.lCivsData_Defenders.get(var21).lProvincesLost.get(j2))
                     .getTrueOwnerOfProvince();
                  PeaceTreaty_Demands var10000 = this.peaceTreatyGameData.lCivsDemands_Defenders.get(var21);
                  var10000.iTotalNumOfVicotryPoints = var10000.iTotalNumOfVicotryPoints
                     + CFG.game.getProvinceValue(this.peaceTreatyGameData.lCivsData_Defenders.get(var21).lProvincesLost.get(j2));
               }

               for (int var65 = 0; var65 < CFG.game.getCiv(this.peaceTreatyGameData.lCivsDemands_Defenders.get(var21).iCivID).getNumOfProvinces(); var65++) {
                  PeaceTreaty_Demands var85 = this.peaceTreatyGameData.lCivsDemands_Defenders.get(var21);
                  var85.iTotalNumOfVicotryPoints = var85.iTotalNumOfVicotryPoints
                     + CFG.game.getProvinceValue(CFG.game.getCiv(this.peaceTreatyGameData.lCivsDemands_Defenders.get(var21).iCivID).getProvinceID(var65));
               }
            }
         } catch (IndexOutOfBoundsException var17) {
            CFG.exceptionStack(var17);
         }

         try {
            for (int i3 = this.peaceTreatyGameData.lCivsData_Aggressors.size() - 1; i3 >= 0; i3--) {
               for (int j2 = this.peaceTreatyGameData.lCivsData_Aggressors.get(i3).lProvincesLost.size() - 1; j2 >= 0; j2--) {
                  this.drawProvinceOwners.get(this.peaceTreatyGameData.lCivsData_Aggressors.get(i3).lProvincesLost.get(j2)).isToTake = true;
                  this.drawProvinceOwners.get(this.peaceTreatyGameData.lCivsData_Aggressors.get(i3).lProvincesLost.get(j2)).iCivID = CFG.game
                     .getProvince(this.peaceTreatyGameData.lCivsData_Aggressors.get(i3).lProvincesLost.get(j2))
                     .getTrueOwnerOfProvince();
                  PeaceTreaty_Demands var86 = this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i3);
                  var86.iTotalNumOfVicotryPoints = var86.iTotalNumOfVicotryPoints
                     + CFG.game.getProvinceValue(this.peaceTreatyGameData.lCivsData_Aggressors.get(i3).lProvincesLost.get(j2));
               }

               for (int var67 = 0; var67 < CFG.game.getCiv(this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i3).iCivID).getNumOfProvinces(); var67++) {
                  PeaceTreaty_Demands var87 = this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i3);
                  var87.iTotalNumOfVicotryPoints = var87.iTotalNumOfVicotryPoints
                     + CFG.game.getProvinceValue(CFG.game.getCiv(this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i3).iCivID).getProvinceID(var67));
               }
            }
         } catch (IndexOutOfBoundsException var16) {
            CFG.exceptionStack(var16);
         }

         for (int i2 = this.peaceTreatyGameData.lCivsDemands_Aggressors.size() - 1; i2 >= 0; i2--) {
            this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i2).lReleasableCivs = new ArrayList<>();

            for (int j2 = 0; j2 < CFG.game.getCiv(this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i2).iCivID).getNumOfProvinces(); j2++) {
               if (!CFG.game.getProvince(CFG.game.getCiv(this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i2).iCivID).getProvinceID(j2)).isOccupied()) {
                  for (int u = 0;
                     u
                        < CFG.game
                           .getProvince(CFG.game.getCiv(this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i2).iCivID).getProvinceID(j2))
                           .getCore()
                           .getCivsSize();
                     u++
                  ) {
                     if (CFG.game
                              .getProvince(CFG.game.getCiv(this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i2).iCivID).getProvinceID(j2))
                              .getCore()
                              .getCivID(u)
                           != CFG.game
                              .getProvince(CFG.game.getCiv(this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i2).iCivID).getProvinceID(j2))
                              .getCivID()
                        && CFG.game
                              .getCiv(
                                 CFG.game
                                    .getProvince(CFG.game.getCiv(this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i2).iCivID).getProvinceID(j2))
                                    .getCore()
                                    .getCivID(u)
                              )
                              .getNumOfProvinces()
                           == 0) {
                        boolean tAdd = true;

                        for (int k = this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i2).lReleasableCivs.size() - 1; k >= 0; k--) {
                           if (this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i2).lReleasableCivs.get(k).iCivID
                              == CFG.game
                                 .getProvince(CFG.game.getCiv(this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i2).iCivID).getProvinceID(j2))
                                 .getCore()
                                 .getCivID(u)) {
                              tAdd = false;
                              this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i2)
                                 .lReleasableCivs
                                 .get(k)
                                 .addProvince(CFG.game.getCiv(this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i2).iCivID).getProvinceID(j2));
                              break;
                           }
                        }

                        if (tAdd) {
                           this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i2)
                              .lReleasableCivs
                              .add(
                                 new PeaceTreaty_ReleaseableVassals(
                                    CFG.game
                                       .getProvince(CFG.game.getCiv(this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i2).iCivID).getProvinceID(j2))
                                       .getCore()
                                       .getCivID(u),
                                    CFG.game.getCiv(this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i2).iCivID).getProvinceID(j2)
                                 )
                              );
                        }
                     }
                  }
               }
            }

            for (int o = this.peaceTreatyGameData.lCivsData_Aggressors.get(i2).lProvincesLost.size() - 1; o >= 0; o--) {
               for (int j = 0;
                  j < CFG.game.getProvince(this.peaceTreatyGameData.lCivsData_Aggressors.get(i2).lProvincesLost.get(o)).getCore().getCivsSize();
                  j++
               ) {
                  if (CFG.game.getProvince(this.peaceTreatyGameData.lCivsData_Aggressors.get(i2).lProvincesLost.get(o)).getCore().getCivID(j)
                        != CFG.game.getProvince(this.peaceTreatyGameData.lCivsData_Aggressors.get(i2).lProvincesLost.get(o)).getCivID()
                     && CFG.game
                           .getCiv(CFG.game.getProvince(this.peaceTreatyGameData.lCivsData_Aggressors.get(i2).lProvincesLost.get(o)).getCore().getCivID(j))
                           .getNumOfProvinces()
                        == 0) {
                     boolean tAdd = true;

                     for (int kx = this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i2).lReleasableCivs.size() - 1; kx >= 0; kx--) {
                        if (this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i2).lReleasableCivs.get(kx).iCivID
                           == CFG.game.getProvince(this.peaceTreatyGameData.lCivsData_Aggressors.get(i2).lProvincesLost.get(o)).getCore().getCivID(j)) {
                           tAdd = false;
                           this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i2)
                              .lReleasableCivs
                              .get(kx)
                              .addProvince(this.peaceTreatyGameData.lCivsData_Aggressors.get(i2).lProvincesLost.get(o));
                           break;
                        }
                     }

                     if (tAdd) {
                        this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i2)
                           .lReleasableCivs
                           .add(
                              new PeaceTreaty_ReleaseableVassals(
                                 CFG.game.getProvince(this.peaceTreatyGameData.lCivsData_Aggressors.get(i2).lProvincesLost.get(o)).getCore().getCivID(j),
                                 this.peaceTreatyGameData.lCivsData_Aggressors.get(i2).lProvincesLost.get(o)
                              )
                           );
                     }
                  }
               }
            }
         }

         for (int var59 = this.peaceTreatyGameData.lCivsDemands_Defenders.size() - 1; var59 >= 0; var59--) {
            this.peaceTreatyGameData.lCivsDemands_Defenders.get(var59).lReleasableCivs = new ArrayList<>();

            for (int j2x = 0; j2x < CFG.game.getCiv(this.peaceTreatyGameData.lCivsDemands_Defenders.get(var59).iCivID).getNumOfProvinces(); j2x++) {
               if (!CFG.game.getProvince(CFG.game.getCiv(this.peaceTreatyGameData.lCivsDemands_Defenders.get(var59).iCivID).getProvinceID(j2x)).isOccupied()) {
                  for (int ux = 0;
                     ux
                        < CFG.game
                           .getProvince(CFG.game.getCiv(this.peaceTreatyGameData.lCivsDemands_Defenders.get(var59).iCivID).getProvinceID(j2x))
                           .getCore()
                           .getCivsSize();
                     ux++
                  ) {
                     if (CFG.game
                              .getProvince(CFG.game.getCiv(this.peaceTreatyGameData.lCivsDemands_Defenders.get(var59).iCivID).getProvinceID(j2x))
                              .getCore()
                              .getCivID(ux)
                           != CFG.game
                              .getProvince(CFG.game.getCiv(this.peaceTreatyGameData.lCivsDemands_Defenders.get(var59).iCivID).getProvinceID(j2x))
                              .getCivID()
                        && CFG.game
                              .getCiv(
                                 CFG.game
                                    .getProvince(CFG.game.getCiv(this.peaceTreatyGameData.lCivsDemands_Defenders.get(var59).iCivID).getProvinceID(j2x))
                                    .getCore()
                                    .getCivID(ux)
                              )
                              .getNumOfProvinces()
                           == 0) {
                        boolean tAdd = true;

                        for (int kxx = this.peaceTreatyGameData.lCivsDemands_Defenders.get(var59).lReleasableCivs.size() - 1; kxx >= 0; kxx--) {
                           if (this.peaceTreatyGameData.lCivsDemands_Defenders.get(var59).lReleasableCivs.get(kxx).iCivID
                              == CFG.game
                                 .getProvince(CFG.game.getCiv(this.peaceTreatyGameData.lCivsDemands_Defenders.get(var59).iCivID).getProvinceID(j2x))
                                 .getCore()
                                 .getCivID(ux)) {
                              tAdd = false;
                              this.peaceTreatyGameData.lCivsDemands_Defenders.get(var59)
                                 .lReleasableCivs
                                 .get(kxx)
                                 .addProvince(CFG.game.getCiv(this.peaceTreatyGameData.lCivsDemands_Defenders.get(var59).iCivID).getProvinceID(j2x));
                              break;
                           }
                        }

                        if (tAdd) {
                           this.peaceTreatyGameData.lCivsDemands_Defenders.get(var59)
                              .lReleasableCivs
                              .add(
                                 new PeaceTreaty_ReleaseableVassals(
                                    CFG.game
                                       .getProvince(CFG.game.getCiv(this.peaceTreatyGameData.lCivsDemands_Defenders.get(var59).iCivID).getProvinceID(j2x))
                                       .getCore()
                                       .getCivID(ux),
                                    CFG.game.getCiv(this.peaceTreatyGameData.lCivsDemands_Defenders.get(var59).iCivID).getProvinceID(j2x)
                                 )
                              );
                        }
                     }
                  }
               }
            }

            for (int o = this.peaceTreatyGameData.lCivsData_Defenders.get(var59).lProvincesLost.size() - 1; o >= 0; o--) {
               for (int jx = 0;
                  jx < CFG.game.getProvince(this.peaceTreatyGameData.lCivsData_Defenders.get(var59).lProvincesLost.get(o)).getCore().getCivsSize();
                  jx++
               ) {
                  if (CFG.game.getProvince(this.peaceTreatyGameData.lCivsData_Defenders.get(var59).lProvincesLost.get(o)).getCore().getCivID(jx)
                        != CFG.game.getProvince(this.peaceTreatyGameData.lCivsData_Defenders.get(var59).lProvincesLost.get(o)).getCivID()
                     && CFG.game
                           .getCiv(CFG.game.getProvince(this.peaceTreatyGameData.lCivsData_Defenders.get(var59).lProvincesLost.get(o)).getCore().getCivID(jx))
                           .getNumOfProvinces()
                        == 0) {
                     boolean tAdd = true;

                     for (int kxxx = this.peaceTreatyGameData.lCivsDemands_Defenders.get(var59).lReleasableCivs.size() - 1; kxxx >= 0; kxxx--) {
                        if (this.peaceTreatyGameData.lCivsDemands_Defenders.get(var59).lReleasableCivs.get(kxxx).iCivID
                           == CFG.game.getProvince(this.peaceTreatyGameData.lCivsData_Defenders.get(var59).lProvincesLost.get(o)).getCore().getCivID(jx)) {
                           tAdd = false;
                           this.peaceTreatyGameData.lCivsDemands_Defenders.get(var59)
                              .lReleasableCivs
                              .get(kxxx)
                              .addProvince(this.peaceTreatyGameData.lCivsData_Defenders.get(var59).lProvincesLost.get(o));
                           break;
                        }
                     }

                     if (tAdd) {
                        this.peaceTreatyGameData.lCivsDemands_Defenders.get(var59)
                           .lReleasableCivs
                           .add(
                              new PeaceTreaty_ReleaseableVassals(
                                 CFG.game.getProvince(this.peaceTreatyGameData.lCivsData_Defenders.get(var59).lProvincesLost.get(o)).getCore().getCivID(jx),
                                 this.peaceTreatyGameData.lCivsData_Defenders.get(var59).lProvincesLost.get(o)
                              )
                           );
                     }
                  }
               }
            }
         }

         for (int var60 = this.drawProvinceOwners.size() - 1; var60 >= 0; var60--) {
            if (this.drawProvinceOwners.get(var60).isToTake) {
               this.provincesLeftToTake.add(var60);
            }
         }

         this.iProvincesLeftToTakeSize = this.provincesLeftToTake.size();
      } else {
         try {
            ArrayList<Boolean> addDefender = new ArrayList<>();
            ArrayList<Boolean> addAggressor = new ArrayList<>();

            for (int i4 = 0; i4 < CFG.game.getWar(this.peaceTreatyGameData.iWarID).getDefendersSize(); i4++) {
               boolean addCiv = false;

               for (int jxx = this.peaceTreatyGameData.lCivsData_Defenders.size() - 1; jxx >= 0; jxx--) {
                  if (CFG.game.getWar(this.peaceTreatyGameData.iWarID).getDefenderID(i4).getCivID()
                     == this.peaceTreatyGameData.lCivsData_Defenders.get(jxx).iCivID) {
                     addCiv = true;
                     break;
                  }
               }

               addDefender.add(addCiv);
            }

            for (int var73 = 0; var73 < CFG.game.getWar(this.peaceTreatyGameData.iWarID).getAggressorsSize(); var73++) {
               boolean addCiv = false;

               for (int jxxx = this.peaceTreatyGameData.lCivsData_Aggressors.size() - 1; jxxx >= 0; jxxx--) {
                  if (CFG.game.getWar(this.peaceTreatyGameData.iWarID).getAggressorID(var73).getCivID()
                     == this.peaceTreatyGameData.lCivsData_Aggressors.get(jxxx).iCivID) {
                     addCiv = true;
                     break;
                  }
               }

               addAggressor.add(addCiv);
            }

            for (int var74 = this.peaceTreatyGameData.lCivsData_Defenders.size() - 1; var74 >= 0; var74--) {
               PeaceTreaty_Civs tempLost = CFG.game
                  .getWar(this.peaceTreatyGameData.iWarID)
                  .getDefenders_ProvincesLost(
                     CFG.game.getWar(this.peaceTreatyGameData.iWarID).getDefenderID_ByCivID(this.peaceTreatyGameData.lCivsData_Defenders.get(var74).iCivID),
                     addDefender,
                     addAggressor
                  );

               for (int jxxxx = tempLost.lProvincesLost.size() - 1; jxxxx >= 0; jxxxx--) {
                  boolean isAdded = false;

                  for (int k2 = this.peaceTreatyGameData.lCivsData_Defenders.get(var74).lProvincesLost.size() - 1; k2 >= 0; k2--) {
                     if (tempLost.lProvincesLost.get(jxxxx).equals(this.peaceTreatyGameData.lCivsData_Defenders.get(var74).lProvincesLost.get(k2))) {
                        isAdded = true;
                        break;
                     }
                  }

                  if (!isAdded) {
                     this.peaceTreatyGameData.lCivsData_Defenders.get(var74).lProvincesLost.add(tempLost.lProvincesLost.get(jxxxx));
                     this.makeDemand_Province(
                        tempLost.lProvincesLost.get(jxxxx),
                        this.peaceTreatyGameData.lCivsData_Defenders.get(var74).iCivID,
                        this.peaceTreatyGameData.lCivsData_Defenders.get(var74).iCivID,
                        true
                     );
                  }

                  tempLost.lProvincesLost.remove(jxxxx);
               }
            }

            for (int var75 = this.peaceTreatyGameData.lCivsData_Aggressors.size() - 1; var75 >= 0; var75--) {
               PeaceTreaty_Civs tempLost = CFG.game
                  .getWar(this.peaceTreatyGameData.iWarID)
                  .getAggressors_ProvincesLost(
                     CFG.game.getWar(this.peaceTreatyGameData.iWarID).getAggressorID_ByCivID(this.peaceTreatyGameData.lCivsData_Aggressors.get(var75).iCivID),
                     addDefender,
                     addAggressor
                  );

               for (int jxxxx = tempLost.lProvincesLost.size() - 1; jxxxx >= 0; jxxxx--) {
                  boolean isAdded = false;

                  for (int k2x = this.peaceTreatyGameData.lCivsData_Aggressors.get(var75).lProvincesLost.size() - 1; k2x >= 0; k2x--) {
                     if (tempLost.lProvincesLost.get(jxxxx).equals(this.peaceTreatyGameData.lCivsData_Aggressors.get(var75).lProvincesLost.get(k2x))) {
                        isAdded = true;
                        break;
                     }
                  }

                  if (!isAdded) {
                     this.peaceTreatyGameData.lCivsData_Aggressors.get(var75).lProvincesLost.add(tempLost.lProvincesLost.get(jxxxx));
                     this.makeDemand_Province(
                        tempLost.lProvincesLost.get(jxxxx),
                        this.peaceTreatyGameData.lCivsData_Aggressors.get(var75).iCivID,
                        this.peaceTreatyGameData.lCivsData_Aggressors.get(var75).iCivID,
                        true
                     );
                  }

                  tempLost.lProvincesLost.remove(jxxxx);
               }
            }

            for (int var76 = this.peaceTreatyGameData.lCivsData_Defenders.size() - 1; var76 >= 0; var76--) {
               if (!CFG.game.getWar(this.peaceTreatyGameData.iWarID).getIsAggressor(this.peaceTreatyGameData.lCivsData_Defenders.get(var76).iCivID)
                  && !CFG.game.getWar(this.peaceTreatyGameData.iWarID).getIsDefender(this.peaceTreatyGameData.lCivsData_Defenders.get(var76).iCivID)) {
                  this.peaceTreatyGameData.lCivsData_Defenders.remove(var76);
                  this.peaceTreatyGameData.lCivsDemands_Defenders.remove(var76);
               } else {
                  for (int j3 = this.peaceTreatyGameData.lCivsData_Defenders.get(var76).lProvincesLost.size() - 1; j3 >= 0; j3--) {
                     if (CFG.game.getProvince(this.peaceTreatyGameData.lCivsData_Defenders.get(var76).lProvincesLost.get(j3)).isOccupied()) {
                        if (!CFG.game
                              .getWar(this.peaceTreatyGameData.iWarID)
                              .getIsDefender(CFG.game.getProvince(this.peaceTreatyGameData.lCivsData_Defenders.get(var76).lProvincesLost.get(j3)).getCivID())
                           && !CFG.game
                              .getWar(this.peaceTreatyGameData.iWarID)
                              .getIsAggressor(CFG.game.getProvince(this.peaceTreatyGameData.lCivsData_Defenders.get(var76).lProvincesLost.get(j3)).getCivID())) {
                           boolean removed = false;

                           for (int kxxxx = this.peaceTreatyGameData.lCivsDemands_Aggressors.size() - 1; kxxxx >= 0; kxxxx--) {
                              for (int o = this.peaceTreatyGameData.lCivsDemands_Aggressors.get(kxxxx).lDemands.size() - 1; o >= 0; o--) {
                                 if (this.peaceTreatyGameData.lCivsDemands_Aggressors.get(kxxxx).lDemands.get(o)
                                    == this.peaceTreatyGameData.lCivsData_Defenders.get(var76).lProvincesLost.get(j3)) {
                                    this.peaceTreatyGameData.lCivsDemands_Aggressors.get(kxxxx).lDemands.remove(o);
                                    kxxxx = -1;
                                    removed = true;
                                    break;
                                 }
                              }
                           }

                           if (!removed) {
                              for (int var35 = this.peaceTreatyGameData.lCivsDemands_Defenders.size() - 1; var35 >= 0; var35--) {
                                 for (int ox = this.peaceTreatyGameData.lCivsDemands_Defenders.get(var35).lDemands.size() - 1; ox >= 0; ox--) {
                                    if (this.peaceTreatyGameData.lCivsDemands_Defenders.get(var35).lDemands.get(ox)
                                       == this.peaceTreatyGameData.lCivsData_Defenders.get(var76).lProvincesLost.get(j3)) {
                                       this.peaceTreatyGameData.lCivsDemands_Defenders.get(var35).lDemands.remove(ox);
                                       var35 = -1;
                                       removed = true;
                                       break;
                                    }
                                 }
                              }
                           }

                           this.peaceTreatyGameData.lCivsData_Defenders.get(var76).lProvincesLost.remove(j3);
                        } else {
                           this.drawProvinceOwners.get(this.peaceTreatyGameData.lCivsData_Defenders.get(var76).lProvincesLost.get(j3)).isToTake = true;
                           this.drawProvinceOwners.get(this.peaceTreatyGameData.lCivsData_Defenders.get(var76).lProvincesLost.get(j3)).iCivID = CFG.game
                              .getProvince(this.peaceTreatyGameData.lCivsData_Defenders.get(var76).lProvincesLost.get(j3))
                              .getTrueOwnerOfProvince();
                        }
                     } else {
                        boolean removed = false;

                        for (int kxxxx = this.peaceTreatyGameData.lCivsDemands_Aggressors.size() - 1; kxxxx >= 0; kxxxx--) {
                           for (int oxx = this.peaceTreatyGameData.lCivsDemands_Aggressors.get(kxxxx).lDemands.size() - 1; oxx >= 0; oxx--) {
                              if (this.peaceTreatyGameData.lCivsDemands_Aggressors.get(kxxxx).lDemands.get(oxx)
                                 == this.peaceTreatyGameData.lCivsData_Defenders.get(var76).lProvincesLost.get(j3)) {
                                 this.peaceTreatyGameData.lCivsDemands_Aggressors.get(kxxxx).lDemands.remove(oxx);
                                 kxxxx = -1;
                                 removed = true;
                                 break;
                              }
                           }
                        }

                        if (!removed) {
                           for (int var33 = this.peaceTreatyGameData.lCivsDemands_Defenders.size() - 1; var33 >= 0; var33--) {
                              for (int oxxx = this.peaceTreatyGameData.lCivsDemands_Defenders.get(var33).lDemands.size() - 1; oxxx >= 0; oxxx--) {
                                 if (this.peaceTreatyGameData.lCivsDemands_Defenders.get(var33).lDemands.get(oxxx)
                                    == this.peaceTreatyGameData.lCivsData_Defenders.get(var76).lProvincesLost.get(j3)) {
                                    this.peaceTreatyGameData.lCivsDemands_Defenders.get(var33).lDemands.remove(oxxx);
                                    var33 = -1;
                                    removed = true;
                                    break;
                                 }
                              }
                           }
                        }

                        this.peaceTreatyGameData.lCivsData_Defenders.get(var76).lProvincesLost.remove(j3);
                     }
                  }
               }
            }

            for (int var77 = this.peaceTreatyGameData.lCivsData_Aggressors.size() - 1; var77 >= 0; var77--) {
               if (!CFG.game.getWar(this.peaceTreatyGameData.iWarID).getIsAggressor(this.peaceTreatyGameData.lCivsData_Aggressors.get(var77).iCivID)
                  && !CFG.game.getWar(this.peaceTreatyGameData.iWarID).getIsDefender(this.peaceTreatyGameData.lCivsData_Aggressors.get(var77).iCivID)) {
                  this.peaceTreatyGameData.lCivsData_Aggressors.remove(var77);
                  this.peaceTreatyGameData.lCivsDemands_Aggressors.remove(var77);
               } else {
                  for (int j4 = this.peaceTreatyGameData.lCivsData_Aggressors.get(var77).lProvincesLost.size() - 1; j4 >= 0; j4--) {
                     if (CFG.game.getProvince(this.peaceTreatyGameData.lCivsData_Aggressors.get(var77).lProvincesLost.get(j4)).isOccupied()) {
                        if (!CFG.game
                              .getWar(this.peaceTreatyGameData.iWarID)
                              .getIsDefender(CFG.game.getProvince(this.peaceTreatyGameData.lCivsData_Aggressors.get(var77).lProvincesLost.get(j4)).getCivID())
                           && !CFG.game
                              .getWar(this.peaceTreatyGameData.iWarID)
                              .getIsAggressor(CFG.game.getProvince(this.peaceTreatyGameData.lCivsData_Aggressors.get(var77).lProvincesLost.get(j4)).getCivID())
                           )
                         {
                           boolean removed = false;

                           for (int kxxxx = this.peaceTreatyGameData.lCivsDemands_Defenders.size() - 1; kxxxx >= 0; kxxxx--) {
                              for (int oxxxx = this.peaceTreatyGameData.lCivsDemands_Defenders.get(kxxxx).lDemands.size() - 1; oxxxx >= 0; oxxxx--) {
                                 if (this.peaceTreatyGameData.lCivsDemands_Defenders.get(kxxxx).lDemands.get(oxxxx)
                                    == this.peaceTreatyGameData.lCivsData_Aggressors.get(var77).lProvincesLost.get(j4)) {
                                    this.peaceTreatyGameData.lCivsDemands_Defenders.get(kxxxx).lDemands.remove(oxxxx);
                                    kxxxx = -1;
                                    removed = true;
                                    break;
                                 }
                              }
                           }

                           if (!removed) {
                              for (int var39 = this.peaceTreatyGameData.lCivsDemands_Aggressors.size() - 1; var39 >= 0; var39--) {
                                 for (int oxxxxx = this.peaceTreatyGameData.lCivsDemands_Aggressors.get(var39).lDemands.size() - 1; oxxxxx >= 0; oxxxxx--) {
                                    if (this.peaceTreatyGameData.lCivsDemands_Aggressors.get(var39).lDemands.get(oxxxxx)
                                       == this.peaceTreatyGameData.lCivsData_Aggressors.get(var77).lProvincesLost.get(j4)) {
                                       this.peaceTreatyGameData.lCivsDemands_Aggressors.get(var39).lDemands.remove(oxxxxx);
                                       var39 = -1;
                                       removed = true;
                                       break;
                                    }
                                 }
                              }
                           }

                           this.peaceTreatyGameData.lCivsData_Aggressors.get(var77).lProvincesLost.remove(j4);
                        } else {
                           this.drawProvinceOwners.get(this.peaceTreatyGameData.lCivsData_Aggressors.get(var77).lProvincesLost.get(j4)).isToTake = true;
                           this.drawProvinceOwners.get(this.peaceTreatyGameData.lCivsData_Aggressors.get(var77).lProvincesLost.get(j4)).iCivID = CFG.game
                              .getProvince(this.peaceTreatyGameData.lCivsData_Aggressors.get(var77).lProvincesLost.get(j4))
                              .getTrueOwnerOfProvince();
                        }
                     } else {
                        boolean removed = false;

                        for (int kxxxx = this.peaceTreatyGameData.lCivsDemands_Defenders.size() - 1; kxxxx >= 0; kxxxx--) {
                           for (int oxxxxxx = this.peaceTreatyGameData.lCivsDemands_Defenders.get(kxxxx).lDemands.size() - 1; oxxxxxx >= 0; oxxxxxx--) {
                              if (this.peaceTreatyGameData.lCivsDemands_Defenders.get(kxxxx).lDemands.get(oxxxxxx)
                                 == this.peaceTreatyGameData.lCivsData_Aggressors.get(var77).lProvincesLost.get(j4)) {
                                 this.peaceTreatyGameData.lCivsDemands_Defenders.get(kxxxx).lDemands.remove(oxxxxxx);
                                 kxxxx = -1;
                                 removed = true;
                                 break;
                              }
                           }
                        }

                        if (!removed) {
                           for (int var37 = this.peaceTreatyGameData.lCivsDemands_Aggressors.size() - 1; var37 >= 0; var37--) {
                              for (int oxxxxxxx = this.peaceTreatyGameData.lCivsDemands_Aggressors.get(var37).lDemands.size() - 1; oxxxxxxx >= 0; oxxxxxxx--) {
                                 if (this.peaceTreatyGameData.lCivsDemands_Aggressors.get(var37).lDemands.get(oxxxxxxx)
                                    == this.peaceTreatyGameData.lCivsData_Aggressors.get(var77).lProvincesLost.get(j4)) {
                                    this.peaceTreatyGameData.lCivsDemands_Aggressors.get(var37).lDemands.remove(oxxxxxxx);
                                    var37 = -1;
                                    removed = true;
                                    break;
                                 }
                              }
                           }
                        }

                        this.peaceTreatyGameData.lCivsData_Aggressors.get(var77).lProvincesLost.remove(j4);
                     }
                  }
               }
            }

            for (int var78 = this.peaceTreatyGameData.lCivsDemands_Defenders.size() - 1; var78 >= 0; var78--) {
               for (int j5 = this.peaceTreatyGameData.lCivsDemands_Defenders.get(var78).lDemands.size() - 1; j5 >= 0; j5--) {
                  this.drawProvinceOwners.get(this.peaceTreatyGameData.lCivsDemands_Defenders.get(var78).lDemands.get(j5)).isTaken = this.peaceTreatyGameData
                        .lCivsDemands_Defenders
                        .get(var78)
                     .iCivID;
                  this.drawProvinceOwners.get(this.peaceTreatyGameData.lCivsDemands_Defenders.get(var78).lDemands.get(j5)).iCivID = this.peaceTreatyGameData
                        .lCivsDemands_Defenders
                        .get(var78)
                     .iCivID;
               }
            }

            for (int var79 = this.peaceTreatyGameData.lCivsDemands_Aggressors.size() - 1; var79 >= 0; var79--) {
               for (int j6 = this.peaceTreatyGameData.lCivsDemands_Aggressors.get(var79).lDemands.size() - 1; j6 >= 0; j6--) {
                  this.drawProvinceOwners.get(this.peaceTreatyGameData.lCivsDemands_Aggressors.get(var79).lDemands.get(j6)).isTaken = this.peaceTreatyGameData
                        .lCivsDemands_Aggressors
                        .get(var79)
                     .iCivID;
                  this.drawProvinceOwners.get(this.peaceTreatyGameData.lCivsDemands_Aggressors.get(var79).lDemands.get(j6)).iCivID = this.peaceTreatyGameData
                        .lCivsDemands_Aggressors
                        .get(var79)
                     .iCivID;
               }
            }
         } catch (IndexOutOfBoundsException var15) {
            CFG.exceptionStack(var15);
         }
      }
   }

   protected final void prepareDemansVassalsData() {
      for (int i = 0; i < this.peaceTreatyGameData.lCivsDemands_Defenders.size(); i++) {
         for (int j = 0; j < this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).lReleasableCivs_TakeControl.size(); j++) {
            for (int k = 0; k < this.peaceTreatyGameData.lCivsDemands_Aggressors.size(); k++) {
               if (this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).lReleasableCivs_TakeControl.get(j).iFromCivID
                  == this.peaceTreatyGameData.lCivsDemands_Aggressors.get(k).iCivID) {
                  for (int o = 0; o < this.peaceTreatyGameData.lCivsDemands_Aggressors.get(k).lReleasableCivs.size(); o++) {
                     if (this.peaceTreatyGameData.lCivsDemands_Aggressors.get(k).lReleasableCivs.get(o).iCivID
                        == this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).lReleasableCivs_TakeControl.get(j).iVassalCivID) {
                        for (int u = 0; u < this.peaceTreatyGameData.lCivsDemands_Aggressors.get(k).lReleasableCivs.get(o).lProvinces.size(); u++) {
                           this.drawProvinceOwners.get(this.peaceTreatyGameData.lCivsDemands_Aggressors.get(k).lReleasableCivs.get(o).lProvinces.get(u)).iCivID = this.peaceTreatyGameData
                                    .lCivsDemands_Defenders
                                    .get(i)
                                 .lReleasableCivs_TakeControl
                                 .get(j)
                              .iVassalCivID;
                        }
                     }
                  }
               }
            }
         }
      }

      for (int var10 = 0; var10 < this.peaceTreatyGameData.lCivsDemands_Aggressors.size(); var10++) {
         for (int j = 0; j < this.peaceTreatyGameData.lCivsDemands_Aggressors.get(var10).lReleasableCivs_TakeControl.size(); j++) {
            for (int kx = 0; kx < this.peaceTreatyGameData.lCivsDemands_Defenders.size(); kx++) {
               if (this.peaceTreatyGameData.lCivsDemands_Aggressors.get(var10).lReleasableCivs_TakeControl.get(j).iFromCivID
                  == this.peaceTreatyGameData.lCivsDemands_Defenders.get(kx).iCivID) {
                  for (int ox = 0; ox < this.peaceTreatyGameData.lCivsDemands_Defenders.get(kx).lReleasableCivs.size(); ox++) {
                     if (this.peaceTreatyGameData.lCivsDemands_Defenders.get(kx).lReleasableCivs.get(ox).iCivID
                        == this.peaceTreatyGameData.lCivsDemands_Aggressors.get(var10).lReleasableCivs_TakeControl.get(j).iVassalCivID) {
                        for (int u = 0; u < this.peaceTreatyGameData.lCivsDemands_Defenders.get(kx).lReleasableCivs.get(ox).lProvinces.size(); u++) {
                           this.drawProvinceOwners.get(this.peaceTreatyGameData.lCivsDemands_Defenders.get(kx).lReleasableCivs.get(ox).lProvinces.get(u)).iCivID = this.peaceTreatyGameData
                                    .lCivsDemands_Aggressors
                                    .get(var10)
                                 .lReleasableCivs_TakeControl
                                 .get(j)
                              .iVassalCivID;
                        }
                     }
                  }
               }
            }
         }
      }
   }

   protected final int takeReleaseVassal(int iFromCivID, int nReleaseCivID, int nCivID, int pointsUsedByCivID) {
      for (int i = 0; i < this.peaceTreatyGameData.lCivsDemands_Defenders.size(); i++) {
         if (this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).iCivID == iFromCivID) {
            for (int j = 0; j < this.peaceTreatyGameData.lCivsDemands_Aggressors.size(); j++) {
               if (this.peaceTreatyGameData.lCivsDemands_Aggressors.get(j).iCivID == nCivID) {
                  int nID = -1;

                  for (int o = 0; o < this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).lReleasableCivs.size(); o++) {
                     if (this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).lReleasableCivs.get(o).iCivID == nReleaseCivID) {
                        nID = o;
                        break;
                     }
                  }

                  if (nID >= 0) {
                     if (this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).lReleasableCivs.get(nID).iReleasesToCivID > 0) {
                        if (this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).lReleasableCivs.get(nID).iReleasesToCivID != nCivID) {
                           return this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).lReleasableCivs.get(nID).iReleasesToCivID;
                        }

                        this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).lReleasableCivs.get(nID).iReleasesToCivID = -1;
                        PeaceTreaty_Demands var27 = this.peaceTreatyGameData.lCivsDemands_Aggressors.get(j);
                        var27.iVictoryPointsLeft = var27.iVictoryPointsLeft
                           + this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).lReleasableCivs.get(nID).getScoreValue();
                        this.peaceTreatyGameData.lCivsDemands_Aggressors.get(j).removeReleaseVassal_TakeControl(iFromCivID, nReleaseCivID);

                        for (int k = 0; k < this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).lReleasableCivs.get(nID).lProvinces.size(); k++) {
                           if (!this.drawProvinceOwners.get(this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).lReleasableCivs.get(nID).lProvinces.get(k)).isToTake
                              )
                            {
                              this.drawProvinceOwners.get(this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).lReleasableCivs.get(nID).lProvinces.get(k)).iCivID = CFG.game
                                 .getProvince(this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).lReleasableCivs.get(nID).lProvinces.get(k))
                                 .getCivID();
                           } else {
                              if (this.drawProvinceOwners
                                       .get(this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).lReleasableCivs.get(nID).lProvinces.get(k))
                                    .isTaken
                                 > 0) {
                                 if (this.drawProvinceOwners
                                          .get(this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).lReleasableCivs.get(nID).lProvinces.get(k))
                                       .isTaken
                                    != this.peaceTreatyGameData.lCivsDemands_Aggressors.get(j).iCivID) {
                                    for (int u = 0; u < this.peaceTreatyGameData.lCivsDemands_Aggressors.size(); u++) {
                                       if (this.peaceTreatyGameData.lCivsDemands_Aggressors.get(u).iCivID
                                          == this.drawProvinceOwners
                                                .get(this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).lReleasableCivs.get(nID).lProvinces.get(k))
                                             .iCivID) {
                                          this.peaceTreatyGameData
                                             .lCivsDemands_Aggressors
                                             .get(u)
                                             .removeDemandOnProvince(
                                                this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).lReleasableCivs.get(nID).lProvinces.get(k)
                                             );
                                       }

                                       if (this.peaceTreatyGameData.lCivsDemands_Aggressors.get(u).iCivID
                                          == this.drawProvinceOwners
                                                .get(this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).lReleasableCivs.get(nID).lProvinces.get(k))
                                             .isTaken) {
                                          var27 = this.peaceTreatyGameData.lCivsDemands_Aggressors.get(u);
                                          var27.iVictoryPointsLeft = var27.iVictoryPointsLeft
                                             + this.drawProvinceOwners
                                                   .get(this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).lReleasableCivs.get(nID).lProvinces.get(k))
                                                .iProvinceValue;
                                       }
                                    }

                                    for (int var20 = 0; var20 < this.peaceTreatyGameData.lCivsDemands_Defenders.size(); var20++) {
                                       if (this.peaceTreatyGameData.lCivsDemands_Defenders.get(var20).iCivID
                                          == this.drawProvinceOwners
                                                .get(this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).lReleasableCivs.get(nID).lProvinces.get(k))
                                             .iCivID) {
                                          this.peaceTreatyGameData
                                             .lCivsDemands_Defenders
                                             .get(var20)
                                             .removeDemandOnProvince(
                                                this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).lReleasableCivs.get(nID).lProvinces.get(k)
                                             );
                                       }

                                       if (this.peaceTreatyGameData.lCivsDemands_Defenders.get(var20).iCivID
                                          == this.drawProvinceOwners
                                                .get(this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).lReleasableCivs.get(nID).lProvinces.get(k))
                                             .isTaken) {
                                          var27 = this.peaceTreatyGameData.lCivsDemands_Defenders.get(var20);
                                          var27.iVictoryPointsLeft = var27.iVictoryPointsLeft
                                             + this.drawProvinceOwners
                                                   .get(this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).lReleasableCivs.get(nID).lProvinces.get(k))
                                                .iProvinceValue;
                                       }
                                    }

                                    var27 = this.peaceTreatyGameData.lCivsDemands_Aggressors.get(j);
                                    var27.iVictoryPointsLeft = var27.iVictoryPointsLeft
                                       - this.drawProvinceOwners
                                             .get(this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).lReleasableCivs.get(nID).lProvinces.get(k))
                                          .iProvinceValue;
                                 }
                              } else {
                                 var27 = this.peaceTreatyGameData.lCivsDemands_Aggressors.get(j);
                                 var27.iVictoryPointsLeft = var27.iVictoryPointsLeft
                                    - this.drawProvinceOwners
                                          .get(this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).lReleasableCivs.get(nID).lProvinces.get(k))
                                       .iProvinceValue;
                              }

                              this.drawProvinceOwners.get(this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).lReleasableCivs.get(nID).lProvinces.get(k)).isTaken = -1;
                              this.drawProvinceOwners.get(this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).lReleasableCivs.get(nID).lProvinces.get(k)).iCivID = CFG.game
                                 .getProvince(this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).lReleasableCivs.get(nID).lProvinces.get(k))
                                 .getCivID();
                           }
                        }

                        return 0;
                     }

                     if (this.peaceTreatyGameData.lCivsDemands_Aggressors.get(j).iVictoryPointsLeft
                        < this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).lReleasableCivs.get(nID).getScoreValue()) {
                        return 0;
                     }

                     this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).lReleasableCivs.get(nID).iReleasesToCivID = nCivID;
                     PeaceTreaty_Demands var10000 = this.peaceTreatyGameData.lCivsDemands_Aggressors.get(j);
                     var10000.iVictoryPointsLeft = var10000.iVictoryPointsLeft
                        - this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).lReleasableCivs.get(nID).getScoreValue();
                     this.peaceTreatyGameData.lCivsDemands_Aggressors.get(j).addReleaseVassal_TakeControl(iFromCivID, nReleaseCivID);

                     for (int kx = 0; kx < this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).lReleasableCivs.get(nID).lProvinces.size(); kx++) {
                        if (this.drawProvinceOwners.get(this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).lReleasableCivs.get(nID).lProvinces.get(kx)).isToTake
                           )
                         {
                           if (this.drawProvinceOwners.get(this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).lReleasableCivs.get(nID).lProvinces.get(kx)).isTaken
                              > 0) {
                              for (int u = 0; u < this.peaceTreatyGameData.lCivsDemands_Aggressors.size(); u++) {
                                 if (this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).iCivID
                                    == this.drawProvinceOwners
                                          .get(this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).lReleasableCivs.get(nID).lProvinces.get(kx))
                                       .iCivID) {
                                    this.peaceTreatyGameData
                                       .lCivsDemands_Aggressors
                                       .get(i)
                                       .removeDemandOnProvince(
                                          this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).lReleasableCivs.get(nID).lProvinces.get(kx)
                                       );
                                 }

                                 if (this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).iCivID
                                    == this.drawProvinceOwners
                                          .get(this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).lReleasableCivs.get(nID).lProvinces.get(kx))
                                       .isTaken) {
                                    var10000 = this.peaceTreatyGameData.lCivsDemands_Aggressors.get(i);
                                    var10000.iVictoryPointsLeft = var10000.iVictoryPointsLeft
                                       + this.drawProvinceOwners
                                             .get(this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).lReleasableCivs.get(nID).lProvinces.get(kx))
                                          .iProvinceValue;
                                 }
                              }

                              for (int var18 = 0; var18 < this.peaceTreatyGameData.lCivsDemands_Defenders.size(); var18++) {
                                 if (this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).iCivID
                                    == this.drawProvinceOwners
                                          .get(this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).lReleasableCivs.get(nID).lProvinces.get(kx))
                                       .iCivID) {
                                    this.peaceTreatyGameData
                                       .lCivsDemands_Defenders
                                       .get(i)
                                       .removeDemandOnProvince(
                                          this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).lReleasableCivs.get(nID).lProvinces.get(kx)
                                       );
                                 }

                                 if (this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).iCivID
                                    == this.drawProvinceOwners
                                          .get(this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).lReleasableCivs.get(nID).lProvinces.get(kx))
                                       .isTaken) {
                                    var10000 = this.peaceTreatyGameData.lCivsDemands_Defenders.get(i);
                                    var10000.iVictoryPointsLeft = var10000.iVictoryPointsLeft
                                       + this.drawProvinceOwners
                                             .get(this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).lReleasableCivs.get(nID).lProvinces.get(kx))
                                          .iProvinceValue;
                                 }
                              }

                              this.drawProvinceOwners.get(this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).lReleasableCivs.get(nID).lProvinces.get(kx)).isTaken = -1;
                              this.drawProvinceOwners.get(this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).lReleasableCivs.get(nID).lProvinces.get(kx)).iCivID = CFG.game
                                 .getProvince(this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).lReleasableCivs.get(nID).lProvinces.get(kx))
                                 .getTrueOwnerOfProvince();
                           }

                           this.drawProvinceOwners.get(this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).lReleasableCivs.get(nID).lProvinces.get(kx)).isTaken = nCivID;
                        }

                        this.drawProvinceOwners.get(this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).lReleasableCivs.get(nID).lProvinces.get(kx)).iCivID = nReleaseCivID;
                     }

                     return nCivID;
                  }

                  i = this.peaceTreatyGameData.lCivsDemands_Defenders.size();
                  break;
               }
            }
         }
      }

      for (int var17 = 0; var17 < this.peaceTreatyGameData.lCivsDemands_Aggressors.size(); var17++) {
         if (this.peaceTreatyGameData.lCivsDemands_Aggressors.get(var17).iCivID == iFromCivID) {
            for (int jx = 0; jx < this.peaceTreatyGameData.lCivsDemands_Defenders.size(); jx++) {
               if (this.peaceTreatyGameData.lCivsDemands_Defenders.get(jx).iCivID == nCivID) {
                  int nID = -1;

                  for (int ox = 0; ox < this.peaceTreatyGameData.lCivsDemands_Aggressors.get(var17).lReleasableCivs.size(); ox++) {
                     if (this.peaceTreatyGameData.lCivsDemands_Aggressors.get(var17).lReleasableCivs.get(ox).iCivID == nReleaseCivID) {
                        nID = ox;
                        break;
                     }
                  }

                  if (nID >= 0) {
                     if (this.peaceTreatyGameData.lCivsDemands_Aggressors.get(var17).lReleasableCivs.get(nID).iReleasesToCivID > 0) {
                        if (this.peaceTreatyGameData.lCivsDemands_Aggressors.get(var17).lReleasableCivs.get(nID).iReleasesToCivID != nCivID) {
                           return this.peaceTreatyGameData.lCivsDemands_Aggressors.get(var17).lReleasableCivs.get(nID).iReleasesToCivID;
                        }

                        this.peaceTreatyGameData.lCivsDemands_Aggressors.get(var17).lReleasableCivs.get(nID).iReleasesToCivID = -1;
                        PeaceTreaty_Demands var35 = this.peaceTreatyGameData.lCivsDemands_Defenders.get(jx);
                        var35.iVictoryPointsLeft = var35.iVictoryPointsLeft
                           + this.peaceTreatyGameData.lCivsDemands_Aggressors.get(var17).lReleasableCivs.get(nID).getScoreValue();
                        this.peaceTreatyGameData.lCivsDemands_Defenders.get(jx).removeReleaseVassal_TakeControl(iFromCivID, nReleaseCivID);

                        for (int kx = 0; kx < this.peaceTreatyGameData.lCivsDemands_Aggressors.get(var17).lReleasableCivs.get(nID).lProvinces.size(); kx++) {
                           if (!this.drawProvinceOwners
                                 .get(this.peaceTreatyGameData.lCivsDemands_Aggressors.get(var17).lReleasableCivs.get(nID).lProvinces.get(kx))
                              .isToTake) {
                              this.drawProvinceOwners
                                    .get(this.peaceTreatyGameData.lCivsDemands_Aggressors.get(var17).lReleasableCivs.get(nID).lProvinces.get(kx))
                                 .iCivID = CFG.game
                                 .getProvince(this.peaceTreatyGameData.lCivsDemands_Aggressors.get(var17).lReleasableCivs.get(nID).lProvinces.get(kx))
                                 .getCivID();
                           } else {
                              if (this.drawProvinceOwners
                                       .get(this.peaceTreatyGameData.lCivsDemands_Aggressors.get(var17).lReleasableCivs.get(nID).lProvinces.get(kx))
                                    .isTaken
                                 > 0) {
                                 if (this.drawProvinceOwners
                                          .get(this.peaceTreatyGameData.lCivsDemands_Aggressors.get(var17).lReleasableCivs.get(nID).lProvinces.get(kx))
                                       .isTaken
                                    != this.peaceTreatyGameData.lCivsDemands_Defenders.get(jx).iCivID) {
                                    for (int u = 0; u < this.peaceTreatyGameData.lCivsDemands_Defenders.size(); u++) {
                                       if (this.peaceTreatyGameData.lCivsDemands_Defenders.get(u).iCivID
                                          == this.drawProvinceOwners
                                                .get(this.peaceTreatyGameData.lCivsDemands_Aggressors.get(var17).lReleasableCivs.get(nID).lProvinces.get(kx))
                                             .iCivID) {
                                          this.peaceTreatyGameData
                                             .lCivsDemands_Defenders
                                             .get(u)
                                             .removeDemandOnProvince(
                                                this.peaceTreatyGameData.lCivsDemands_Aggressors.get(var17).lReleasableCivs.get(nID).lProvinces.get(kx)
                                             );
                                       }

                                       if (this.peaceTreatyGameData.lCivsDemands_Defenders.get(u).iCivID
                                          == this.drawProvinceOwners
                                                .get(this.peaceTreatyGameData.lCivsDemands_Aggressors.get(var17).lReleasableCivs.get(nID).lProvinces.get(kx))
                                             .isTaken) {
                                          var35 = this.peaceTreatyGameData.lCivsDemands_Defenders.get(u);
                                          var35.iVictoryPointsLeft = var35.iVictoryPointsLeft
                                             + this.drawProvinceOwners
                                                   .get(this.peaceTreatyGameData.lCivsDemands_Aggressors.get(var17).lReleasableCivs.get(nID).lProvinces.get(kx))
                                                .iProvinceValue;
                                       }
                                    }

                                    for (int var24 = 0; var24 < this.peaceTreatyGameData.lCivsDemands_Aggressors.size(); var24++) {
                                       if (this.peaceTreatyGameData.lCivsDemands_Aggressors.get(var24).iCivID
                                          == this.drawProvinceOwners
                                                .get(this.peaceTreatyGameData.lCivsDemands_Aggressors.get(var17).lReleasableCivs.get(nID).lProvinces.get(kx))
                                             .iCivID) {
                                          this.peaceTreatyGameData
                                             .lCivsDemands_Aggressors
                                             .get(var24)
                                             .removeDemandOnProvince(
                                                this.peaceTreatyGameData.lCivsDemands_Aggressors.get(var17).lReleasableCivs.get(nID).lProvinces.get(kx)
                                             );
                                       }

                                       if (this.peaceTreatyGameData.lCivsDemands_Aggressors.get(var24).iCivID
                                          == this.drawProvinceOwners
                                                .get(this.peaceTreatyGameData.lCivsDemands_Aggressors.get(var17).lReleasableCivs.get(nID).lProvinces.get(kx))
                                             .isTaken) {
                                          var35 = this.peaceTreatyGameData.lCivsDemands_Aggressors.get(var24);
                                          var35.iVictoryPointsLeft = var35.iVictoryPointsLeft
                                             + this.drawProvinceOwners
                                                   .get(this.peaceTreatyGameData.lCivsDemands_Aggressors.get(var17).lReleasableCivs.get(nID).lProvinces.get(kx))
                                                .iProvinceValue;
                                       }
                                    }

                                    var35 = this.peaceTreatyGameData.lCivsDemands_Defenders.get(jx);
                                    var35.iVictoryPointsLeft = var35.iVictoryPointsLeft
                                       - this.drawProvinceOwners
                                             .get(this.peaceTreatyGameData.lCivsDemands_Aggressors.get(var17).lReleasableCivs.get(nID).lProvinces.get(kx))
                                          .iProvinceValue;
                                 }
                              } else {
                                 var35 = this.peaceTreatyGameData.lCivsDemands_Defenders.get(jx);
                                 var35.iVictoryPointsLeft = var35.iVictoryPointsLeft
                                    - this.drawProvinceOwners
                                          .get(this.peaceTreatyGameData.lCivsDemands_Aggressors.get(var17).lReleasableCivs.get(nID).lProvinces.get(kx))
                                       .iProvinceValue;
                              }

                              this.drawProvinceOwners
                                    .get(this.peaceTreatyGameData.lCivsDemands_Aggressors.get(var17).lReleasableCivs.get(nID).lProvinces.get(kx))
                                 .isTaken = -1;
                              this.drawProvinceOwners
                                    .get(this.peaceTreatyGameData.lCivsDemands_Aggressors.get(var17).lReleasableCivs.get(nID).lProvinces.get(kx))
                                 .iCivID = CFG.game
                                 .getProvince(this.peaceTreatyGameData.lCivsDemands_Aggressors.get(var17).lReleasableCivs.get(nID).lProvinces.get(kx))
                                 .getCivID();
                           }
                        }

                        return 0;
                     }

                     if (this.peaceTreatyGameData.lCivsDemands_Aggressors.get(var17).lReleasableCivs.get(nID).getScoreValue()
                        > this.peaceTreatyGameData.lCivsDemands_Defenders.get(jx).iVictoryPointsLeft) {
                        return 0;
                     }

                     this.peaceTreatyGameData.lCivsDemands_Aggressors.get(var17).lReleasableCivs.get(nID).iReleasesToCivID = nCivID;
                     PeaceTreaty_Demands var32 = this.peaceTreatyGameData.lCivsDemands_Defenders.get(jx);
                     var32.iVictoryPointsLeft = var32.iVictoryPointsLeft
                        - this.peaceTreatyGameData.lCivsDemands_Aggressors.get(var17).lReleasableCivs.get(nID).getScoreValue();
                     this.peaceTreatyGameData.lCivsDemands_Defenders.get(jx).addReleaseVassal_TakeControl(iFromCivID, nReleaseCivID);

                     for (int kxx = 0; kxx < this.peaceTreatyGameData.lCivsDemands_Aggressors.get(var17).lReleasableCivs.get(nID).lProvinces.size(); kxx++) {
                        if (this.drawProvinceOwners
                              .get(this.peaceTreatyGameData.lCivsDemands_Aggressors.get(var17).lReleasableCivs.get(nID).lProvinces.get(kxx))
                           .isToTake) {
                           if (this.drawProvinceOwners
                                    .get(this.peaceTreatyGameData.lCivsDemands_Aggressors.get(var17).lReleasableCivs.get(nID).lProvinces.get(kxx))
                                 .isTaken
                              > 0) {
                              for (int u = 0; u < this.peaceTreatyGameData.lCivsDemands_Defenders.size(); u++) {
                                 if (this.peaceTreatyGameData.lCivsDemands_Defenders.get(var17).iCivID
                                    == this.drawProvinceOwners
                                          .get(this.peaceTreatyGameData.lCivsDemands_Aggressors.get(var17).lReleasableCivs.get(nID).lProvinces.get(kxx))
                                       .iCivID) {
                                    this.peaceTreatyGameData
                                       .lCivsDemands_Defenders
                                       .get(var17)
                                       .removeDemandOnProvince(
                                          this.peaceTreatyGameData.lCivsDemands_Aggressors.get(var17).lReleasableCivs.get(nID).lProvinces.get(kxx)
                                       );
                                 }

                                 if (this.peaceTreatyGameData.lCivsDemands_Defenders.get(var17).iCivID
                                    == this.drawProvinceOwners
                                          .get(this.peaceTreatyGameData.lCivsDemands_Aggressors.get(var17).lReleasableCivs.get(nID).lProvinces.get(kxx))
                                       .isTaken) {
                                    var32 = this.peaceTreatyGameData.lCivsDemands_Defenders.get(var17);
                                    var32.iVictoryPointsLeft = var32.iVictoryPointsLeft
                                       + this.drawProvinceOwners
                                             .get(this.peaceTreatyGameData.lCivsDemands_Aggressors.get(var17).lReleasableCivs.get(nID).lProvinces.get(kxx))
                                          .iProvinceValue;
                                 }
                              }

                              for (int var22 = 0; var22 < this.peaceTreatyGameData.lCivsDemands_Aggressors.size(); var22++) {
                                 if (this.peaceTreatyGameData.lCivsDemands_Aggressors.get(var17).iCivID
                                    == this.drawProvinceOwners
                                          .get(this.peaceTreatyGameData.lCivsDemands_Aggressors.get(var17).lReleasableCivs.get(nID).lProvinces.get(kxx))
                                       .iCivID) {
                                    this.peaceTreatyGameData
                                       .lCivsDemands_Aggressors
                                       .get(var17)
                                       .removeDemandOnProvince(
                                          this.peaceTreatyGameData.lCivsDemands_Aggressors.get(var17).lReleasableCivs.get(nID).lProvinces.get(kxx)
                                       );
                                 }

                                 if (this.peaceTreatyGameData.lCivsDemands_Aggressors.get(var17).iCivID
                                    == this.drawProvinceOwners
                                          .get(this.peaceTreatyGameData.lCivsDemands_Aggressors.get(var17).lReleasableCivs.get(nID).lProvinces.get(kxx))
                                       .isTaken) {
                                    var32 = this.peaceTreatyGameData.lCivsDemands_Aggressors.get(var17);
                                    var32.iVictoryPointsLeft = var32.iVictoryPointsLeft
                                       + this.drawProvinceOwners
                                             .get(this.peaceTreatyGameData.lCivsDemands_Aggressors.get(var17).lReleasableCivs.get(nID).lProvinces.get(kxx))
                                          .iProvinceValue;
                                 }
                              }

                              this.drawProvinceOwners
                                    .get(this.peaceTreatyGameData.lCivsDemands_Aggressors.get(var17).lReleasableCivs.get(nID).lProvinces.get(kxx))
                                 .isTaken = -1;
                              this.drawProvinceOwners
                                    .get(this.peaceTreatyGameData.lCivsDemands_Aggressors.get(var17).lReleasableCivs.get(nID).lProvinces.get(kxx))
                                 .iCivID = CFG.game
                                 .getProvince(this.peaceTreatyGameData.lCivsDemands_Aggressors.get(var17).lReleasableCivs.get(nID).lProvinces.get(kxx))
                                 .getTrueOwnerOfProvince();
                           }

                           this.drawProvinceOwners
                                 .get(this.peaceTreatyGameData.lCivsDemands_Aggressors.get(var17).lReleasableCivs.get(nID).lProvinces.get(kxx))
                              .isTaken = nCivID;
                        }

                        this.drawProvinceOwners.get(this.peaceTreatyGameData.lCivsDemands_Aggressors.get(var17).lReleasableCivs.get(nID).lProvinces.get(kxx)).iCivID = nReleaseCivID;
                     }

                     return nCivID;
                  }

                  var17 = this.peaceTreatyGameData.lCivsDemands_Aggressors.size();
                  break;
               }
            }
         }
      }

      return pointsUsedByCivID;
   }

   protected final int getVassalization_Cost(int nCivID) {
      for (int i = 0; i < this.peaceTreatyGameData.lCivsDemands_Defenders.size(); i++) {
         if (this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).iCivID == nCivID) {
            return (int)Math.max(this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).iTotalNumOfVicotryPoints * 0.4F, 1.0F);
         }
      }

      for (int var3 = 0; var3 < this.peaceTreatyGameData.lCivsDemands_Aggressors.size(); var3++) {
         if (this.peaceTreatyGameData.lCivsDemands_Aggressors.get(var3).iCivID == nCivID) {
            return (int)Math.max(this.peaceTreatyGameData.lCivsDemands_Aggressors.get(var3).iTotalNumOfVicotryPoints * 0.4F, 1.0F);
         }
      }

      return 1;
   }

   protected final int takeVassalize(int nVasslizeCivID, int nCivID, int pointsUsedByCivID) {
      for (int i = 0; i < this.peaceTreatyGameData.lCivsDemands_Defenders.size(); i++) {
         if (this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).iCivID == nVasslizeCivID) {
            for (int j = 0; j < this.peaceTreatyGameData.lCivsDemands_Aggressors.size(); j++) {
               if (this.peaceTreatyGameData.lCivsDemands_Aggressors.get(j).iCivID == nCivID) {
                  if (this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).iWillBecomeVassalOfCivID > 0) {
                     if (this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).iWillBecomeVassalOfCivID == nCivID) {
                        this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).iWillBecomeVassalOfCivID = 0;
                        PeaceTreaty_Demands var11 = this.peaceTreatyGameData.lCivsDemands_Aggressors.get(j);
                        var11.iVictoryPointsLeft = var11.iVictoryPointsLeft + this.getVassalization_Cost(nVasslizeCivID);
                        this.peaceTreatyGameData.lCivsDemands_Aggressors.get(j).removeWillVassalizeCivID(nVasslizeCivID);
                        if (CFG.menuManager.getInGame_PeaceTreaty()) {
                           if (!CFG.game.getCiv(nCivID).getControlledByPlayer()) {
                              CFG.menuManager.rebuildInGame_PeaceTreaty_Provinces();
                           }

                           CFG.menuManager.rebuildInGame_PeaceTreaty_Scores();
                        }

                        return 0;
                     }

                     if (this.peaceTreatyGameData.lCivsDemands_Aggressors.get(j).iVictoryPointsLeft < this.getVassalization_Cost(nVasslizeCivID)) {
                        if (CFG.menuManager.getInGame_PeaceTreaty()) {
                           if (!CFG.game.getCiv(nCivID).getControlledByPlayer()) {
                              CFG.menuManager.rebuildInGame_PeaceTreaty_Provinces();
                           }

                           CFG.menuManager.rebuildInGame_PeaceTreaty_Scores();
                        }

                        return 0;
                     }

                     for (int k = 0; k < this.peaceTreatyGameData.lCivsDemands_Aggressors.size(); k++) {
                        if (this.peaceTreatyGameData.lCivsDemands_Aggressors.get(k).iCivID
                           == this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).iWillBecomeVassalOfCivID) {
                           this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).iWillBecomeVassalOfCivID = 0;
                           PeaceTreaty_Demands var10000 = this.peaceTreatyGameData.lCivsDemands_Aggressors.get(k);
                           var10000.iVictoryPointsLeft = var10000.iVictoryPointsLeft + this.getVassalization_Cost(nVasslizeCivID);
                           this.peaceTreatyGameData.lCivsDemands_Aggressors.get(k).removeWillVassalizeCivID(nVasslizeCivID);
                           break;
                        }
                     }
                  }

                  if (this.peaceTreatyGameData.lCivsDemands_Aggressors.get(j).iVictoryPointsLeft < this.getVassalization_Cost(nVasslizeCivID)) {
                     if (CFG.menuManager.getInGame_PeaceTreaty()) {
                        if (!CFG.game.getCiv(nCivID).getControlledByPlayer()) {
                           CFG.menuManager.rebuildInGame_PeaceTreaty_Provinces();
                        }

                        CFG.menuManager.rebuildInGame_PeaceTreaty_Scores();
                     }

                     return 0;
                  }

                  this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).iWillBecomeVassalOfCivID = nCivID;
                  PeaceTreaty_Demands var10 = this.peaceTreatyGameData.lCivsDemands_Aggressors.get(j);
                  var10.iVictoryPointsLeft = var10.iVictoryPointsLeft - this.getVassalization_Cost(nVasslizeCivID);
                  this.peaceTreatyGameData.lCivsDemands_Aggressors.get(j).addWillVassalizeCivID(nVasslizeCivID);
                  if (CFG.menuManager.getInGame_PeaceTreaty()) {
                     if (!CFG.game.getCiv(nCivID).getControlledByPlayer()) {
                        CFG.menuManager.rebuildInGame_PeaceTreaty_Provinces();
                     }

                     CFG.menuManager.rebuildInGame_PeaceTreaty_Scores();
                  }

                  return nCivID;
               }
            }
         }
      }

      for (int var8 = 0; var8 < this.peaceTreatyGameData.lCivsDemands_Aggressors.size(); var8++) {
         if (this.peaceTreatyGameData.lCivsDemands_Aggressors.get(var8).iCivID == nVasslizeCivID) {
            for (int jx = 0; jx < this.peaceTreatyGameData.lCivsDemands_Defenders.size(); jx++) {
               if (this.peaceTreatyGameData.lCivsDemands_Defenders.get(jx).iCivID == nCivID) {
                  if (this.peaceTreatyGameData.lCivsDemands_Aggressors.get(var8).iWillBecomeVassalOfCivID > 0) {
                     if (this.peaceTreatyGameData.lCivsDemands_Aggressors.get(var8).iWillBecomeVassalOfCivID == nCivID) {
                        this.peaceTreatyGameData.lCivsDemands_Aggressors.get(var8).iWillBecomeVassalOfCivID = 0;
                        PeaceTreaty_Demands var14 = this.peaceTreatyGameData.lCivsDemands_Defenders.get(jx);
                        var14.iVictoryPointsLeft = var14.iVictoryPointsLeft + this.getVassalization_Cost(nVasslizeCivID);
                        this.peaceTreatyGameData.lCivsDemands_Defenders.get(jx).removeWillVassalizeCivID(nVasslizeCivID);
                        if (CFG.menuManager.getInGame_PeaceTreaty()) {
                           if (!CFG.game.getCiv(nCivID).getControlledByPlayer()) {
                              CFG.menuManager.rebuildInGame_PeaceTreaty_Provinces();
                           }

                           CFG.menuManager.rebuildInGame_PeaceTreaty_Scores();
                        }

                        return 0;
                     }

                     if (this.peaceTreatyGameData.lCivsDemands_Defenders.get(jx).iVictoryPointsLeft < this.getVassalization_Cost(nVasslizeCivID)) {
                        if (CFG.menuManager.getInGame_PeaceTreaty()) {
                           if (!CFG.game.getCiv(nCivID).getControlledByPlayer()) {
                              CFG.menuManager.rebuildInGame_PeaceTreaty_Provinces();
                           }

                           CFG.menuManager.rebuildInGame_PeaceTreaty_Scores();
                        }

                        return 0;
                     }

                     for (int kx = 0; kx < this.peaceTreatyGameData.lCivsDemands_Defenders.size(); kx++) {
                        if (this.peaceTreatyGameData.lCivsDemands_Defenders.get(kx).iCivID
                           == this.peaceTreatyGameData.lCivsDemands_Aggressors.get(var8).iWillBecomeVassalOfCivID) {
                           this.peaceTreatyGameData.lCivsDemands_Aggressors.get(var8).iWillBecomeVassalOfCivID = 0;
                           PeaceTreaty_Demands var12 = this.peaceTreatyGameData.lCivsDemands_Defenders.get(kx);
                           var12.iVictoryPointsLeft = var12.iVictoryPointsLeft + this.getVassalization_Cost(nVasslizeCivID);
                           this.peaceTreatyGameData.lCivsDemands_Defenders.get(kx).removeWillVassalizeCivID(nVasslizeCivID);
                           break;
                        }
                     }
                  }

                  if (this.peaceTreatyGameData.lCivsDemands_Defenders.get(jx).iVictoryPointsLeft < this.getVassalization_Cost(nVasslizeCivID)) {
                     if (CFG.menuManager.getInGame_PeaceTreaty()) {
                        if (!CFG.game.getCiv(nCivID).getControlledByPlayer()) {
                           CFG.menuManager.rebuildInGame_PeaceTreaty_Provinces();
                        }

                        CFG.menuManager.rebuildInGame_PeaceTreaty_Scores();
                     }

                     return 0;
                  }

                  this.peaceTreatyGameData.lCivsDemands_Aggressors.get(var8).iWillBecomeVassalOfCivID = nCivID;
                  PeaceTreaty_Demands var13 = this.peaceTreatyGameData.lCivsDemands_Defenders.get(jx);
                  var13.iVictoryPointsLeft = var13.iVictoryPointsLeft - this.getVassalization_Cost(nVasslizeCivID);
                  this.peaceTreatyGameData.lCivsDemands_Defenders.get(jx).addWillVassalizeCivID(nVasslizeCivID);
                  if (CFG.menuManager.getInGame_PeaceTreaty()) {
                     if (!CFG.game.getCiv(nCivID).getControlledByPlayer()) {
                        CFG.menuManager.rebuildInGame_PeaceTreaty_Provinces();
                     }

                     CFG.menuManager.rebuildInGame_PeaceTreaty_Scores();
                  }

                  return nCivID;
               }
            }
         }
      }

      return pointsUsedByCivID;
   }

   protected final int getWarReparation_Cost(int nCivID) {
      for (int i = 0; i < this.peaceTreatyGameData.lCivsDemands_Defenders.size(); i++) {
         if (this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).iCivID == nCivID) {
            return (int)Math.max(this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).iTotalNumOfVicotryPoints * 0.1F, 1.0F);
         }
      }

      for (int var3 = 0; var3 < this.peaceTreatyGameData.lCivsDemands_Aggressors.size(); var3++) {
         if (this.peaceTreatyGameData.lCivsDemands_Aggressors.get(var3).iCivID == nCivID) {
            return (int)Math.max(this.peaceTreatyGameData.lCivsDemands_Aggressors.get(var3).iTotalNumOfVicotryPoints * 0.1F, 1.0F);
         }
      }

      return 1;
   }

   protected final int takeWarReparations(int nWarRepartionsFromCivID, int nCivID, int pointsUsedByCivID) {
      for (int i = 0; i < this.peaceTreatyGameData.lCivsDemands_Defenders.size(); i++) {
         if (this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).iCivID == nWarRepartionsFromCivID) {
            for (int j = 0; j < this.peaceTreatyGameData.lCivsDemands_Aggressors.size(); j++) {
               if (this.peaceTreatyGameData.lCivsDemands_Aggressors.get(j).iCivID == nCivID) {
                  if (this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).iPaysWarReparationsToCivID > 0) {
                     if (this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).iPaysWarReparationsToCivID == nCivID) {
                        this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).iPaysWarReparationsToCivID = 0;
                        PeaceTreaty_Demands var11 = this.peaceTreatyGameData.lCivsDemands_Aggressors.get(j);
                        var11.iVictoryPointsLeft = var11.iVictoryPointsLeft + this.getWarReparation_Cost(nWarRepartionsFromCivID);
                        this.peaceTreatyGameData.lCivsDemands_Aggressors.get(j).removeWarReparationsFromCivID(nWarRepartionsFromCivID);
                        return 0;
                     }

                     if (this.peaceTreatyGameData.lCivsDemands_Aggressors.get(j).iVictoryPointsLeft < this.getWarReparation_Cost(nWarRepartionsFromCivID)) {
                        return 0;
                     }

                     for (int k = 0; k < this.peaceTreatyGameData.lCivsDemands_Aggressors.size(); k++) {
                        if (this.peaceTreatyGameData.lCivsDemands_Aggressors.get(k).iCivID
                           == this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).iPaysWarReparationsToCivID) {
                           this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).iPaysWarReparationsToCivID = 0;
                           PeaceTreaty_Demands var10000 = this.peaceTreatyGameData.lCivsDemands_Aggressors.get(k);
                           var10000.iVictoryPointsLeft = var10000.iVictoryPointsLeft + this.getWarReparation_Cost(nWarRepartionsFromCivID);
                           this.peaceTreatyGameData.lCivsDemands_Aggressors.get(k).removeWarReparationsFromCivID(nWarRepartionsFromCivID);
                           break;
                        }
                     }
                  }

                  if (this.peaceTreatyGameData.lCivsDemands_Aggressors.get(j).iVictoryPointsLeft < this.getWarReparation_Cost(nWarRepartionsFromCivID)) {
                     return 0;
                  }

                  this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).iPaysWarReparationsToCivID = nCivID;
                  PeaceTreaty_Demands var10 = this.peaceTreatyGameData.lCivsDemands_Aggressors.get(j);
                  var10.iVictoryPointsLeft = var10.iVictoryPointsLeft - this.getWarReparation_Cost(nWarRepartionsFromCivID);
                  this.peaceTreatyGameData.lCivsDemands_Aggressors.get(j).addWarReparationsFromCivID(nWarRepartionsFromCivID);
                  return nCivID;
               }
            }
         }
      }

      for (int var8 = 0; var8 < this.peaceTreatyGameData.lCivsDemands_Aggressors.size(); var8++) {
         if (this.peaceTreatyGameData.lCivsDemands_Aggressors.get(var8).iCivID == nWarRepartionsFromCivID) {
            for (int jx = 0; jx < this.peaceTreatyGameData.lCivsDemands_Defenders.size(); jx++) {
               if (this.peaceTreatyGameData.lCivsDemands_Defenders.get(jx).iCivID == nCivID) {
                  if (this.peaceTreatyGameData.lCivsDemands_Aggressors.get(var8).iPaysWarReparationsToCivID > 0) {
                     if (this.peaceTreatyGameData.lCivsDemands_Aggressors.get(var8).iPaysWarReparationsToCivID == nCivID) {
                        this.peaceTreatyGameData.lCivsDemands_Aggressors.get(var8).iPaysWarReparationsToCivID = 0;
                        PeaceTreaty_Demands var14 = this.peaceTreatyGameData.lCivsDemands_Defenders.get(jx);
                        var14.iVictoryPointsLeft = var14.iVictoryPointsLeft + this.getWarReparation_Cost(nWarRepartionsFromCivID);
                        this.peaceTreatyGameData.lCivsDemands_Defenders.get(jx).removeWarReparationsFromCivID(nWarRepartionsFromCivID);
                        return 0;
                     }

                     if (this.peaceTreatyGameData.lCivsDemands_Defenders.get(jx).iVictoryPointsLeft < this.getWarReparation_Cost(nWarRepartionsFromCivID)) {
                        return 0;
                     }

                     for (int kx = 0; kx < this.peaceTreatyGameData.lCivsDemands_Defenders.size(); kx++) {
                        if (this.peaceTreatyGameData.lCivsDemands_Defenders.get(kx).iCivID
                           == this.peaceTreatyGameData.lCivsDemands_Aggressors.get(var8).iPaysWarReparationsToCivID) {
                           this.peaceTreatyGameData.lCivsDemands_Aggressors.get(var8).iPaysWarReparationsToCivID = 0;
                           PeaceTreaty_Demands var12 = this.peaceTreatyGameData.lCivsDemands_Defenders.get(kx);
                           var12.iVictoryPointsLeft = var12.iVictoryPointsLeft + this.getWarReparation_Cost(nWarRepartionsFromCivID);
                           this.peaceTreatyGameData.lCivsDemands_Defenders.get(kx).removeWarReparationsFromCivID(nWarRepartionsFromCivID);
                           break;
                        }
                     }
                  }

                  if (this.peaceTreatyGameData.lCivsDemands_Defenders.get(jx).iVictoryPointsLeft < this.getWarReparation_Cost(nWarRepartionsFromCivID)) {
                     return 0;
                  }

                  this.peaceTreatyGameData.lCivsDemands_Aggressors.get(var8).iPaysWarReparationsToCivID = nCivID;
                  PeaceTreaty_Demands var13 = this.peaceTreatyGameData.lCivsDemands_Defenders.get(jx);
                  var13.iVictoryPointsLeft = var13.iVictoryPointsLeft - this.getWarReparation_Cost(nWarRepartionsFromCivID);
                  this.peaceTreatyGameData.lCivsDemands_Defenders.get(jx).addWarReparationsFromCivID(nWarRepartionsFromCivID);
                  return nCivID;
               }
            }
         }
      }

      return pointsUsedByCivID;
   }

   protected final boolean takeProvince(int nProvinceID, int nCivID, int pointsUsedByCivID) {
      if (nProvinceID < 0) {
         return false;
      } else if (CFG.game.getProvince(nProvinceID).getSeaProvince()) {
         return false;
      } else if (!this.drawProvinceOwners.get(nProvinceID).isToTake) {
         return false;
      } else {
         this.iLastTakenID = nProvinceID;
         if (this.drawProvinceOwners.get(nProvinceID).isTaken > 0) {
            if (this.drawProvinceOwners.get(nProvinceID).iCivID == nCivID) {
               for (int i2 = 0; i2 < this.peaceTreatyGameData.lCivsDemands_Defenders.size(); i2++) {
                  if (this.peaceTreatyGameData.lCivsDemands_Defenders.get(i2).iCivID == this.drawProvinceOwners.get(nProvinceID).iCivID) {
                     this.peaceTreatyGameData.lCivsDemands_Defenders.get(i2).removeDemandOnProvince(nProvinceID);
                     this.addProvincesLeftToTake(nProvinceID);
                  }

                  if (this.peaceTreatyGameData.lCivsDemands_Defenders.get(i2).iCivID == this.drawProvinceOwners.get(nProvinceID).isTaken) {
                     PeaceTreaty_Demands var9 = this.peaceTreatyGameData.lCivsDemands_Defenders.get(i2);
                     var9.iVictoryPointsLeft = var9.iVictoryPointsLeft + this.drawProvinceOwners.get(nProvinceID).iProvinceValue;
                     if (CFG.menuManager.getInGame_PeaceTreaty()) {
                        CFG.menuManager.rebuildInGame_PeaceTreaty_Scores();
                     }
                  }
               }

               for (int var7 = 0; var7 < this.peaceTreatyGameData.lCivsDemands_Aggressors.size(); var7++) {
                  if (this.peaceTreatyGameData.lCivsDemands_Aggressors.get(var7).iCivID == this.drawProvinceOwners.get(nProvinceID).iCivID) {
                     this.peaceTreatyGameData.lCivsDemands_Aggressors.get(var7).removeDemandOnProvince(nProvinceID);
                     this.addProvincesLeftToTake(nProvinceID);
                  }

                  if (this.peaceTreatyGameData.lCivsDemands_Aggressors.get(var7).iCivID == this.drawProvinceOwners.get(nProvinceID).isTaken) {
                     PeaceTreaty_Demands var10 = this.peaceTreatyGameData.lCivsDemands_Aggressors.get(var7);
                     var10.iVictoryPointsLeft = var10.iVictoryPointsLeft + this.drawProvinceOwners.get(nProvinceID).iProvinceValue;
                     if (CFG.menuManager.getInGame_PeaceTreaty()) {
                        CFG.menuManager.rebuildInGame_PeaceTreaty_Scores();
                     }
                  }
               }

               this.drawProvinceOwners.get(nProvinceID).isTaken = -1;
               this.drawProvinceOwners.get(nProvinceID).iCivID = CFG.game.getProvince(nProvinceID).getTrueOwnerOfProvince();
               return false;
            } else {
               for (int i = 0; i < this.peaceTreatyGameData.lCivsDemands_Defenders.size(); i++) {
                  if (this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).iCivID == this.drawProvinceOwners.get(nProvinceID).iCivID) {
                     this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).removeDemandOnProvince(nProvinceID);
                     this.addProvincesLeftToTake(nProvinceID);
                  }

                  PeaceTreaty_Demands var10000 = this.peaceTreatyGameData.lCivsDemands_Defenders.get(i);
                  var10000.iVictoryPointsLeft = var10000.iVictoryPointsLeft + this.drawProvinceOwners.get(nProvinceID).iProvinceValue;
               }

               for (int var6 = 0; var6 < this.peaceTreatyGameData.lCivsDemands_Aggressors.size(); var6++) {
                  if (this.peaceTreatyGameData.lCivsDemands_Aggressors.get(var6).iCivID == this.drawProvinceOwners.get(nProvinceID).iCivID) {
                     this.peaceTreatyGameData.lCivsDemands_Aggressors.get(var6).removeDemandOnProvince(nProvinceID);
                     this.addProvincesLeftToTake(nProvinceID);
                  }

                  if (this.peaceTreatyGameData.lCivsDemands_Aggressors.get(var6).iCivID == this.drawProvinceOwners.get(nProvinceID).isTaken) {
                     PeaceTreaty_Demands var8 = this.peaceTreatyGameData.lCivsDemands_Aggressors.get(var6);
                     var8.iVictoryPointsLeft = var8.iVictoryPointsLeft + this.drawProvinceOwners.get(nProvinceID).iProvinceValue;
                  }
               }

               this.drawProvinceOwners.get(nProvinceID).isTaken = -1;
               if (this.makeDemand_Province(nProvinceID, nCivID, pointsUsedByCivID)) {
                  this.removeProvincesLeftToTake(nProvinceID);
                  CFG.game.setActiveProvinceID(-1);
                  this.AI_UseVictoryPoints();
                  return true;
               } else {
                  CFG.game.setActiveProvinceID(-1);
                  this.AI_UseVictoryPoints();
                  return false;
               }
            }
         } else if (this.makeDemand_Province(nProvinceID, nCivID, pointsUsedByCivID)) {
            this.removeProvincesLeftToTake(nProvinceID);
            CFG.game.setActiveProvinceID(-1);
            this.AI_UseVictoryPoints();
            return true;
         } else {
            CFG.game.setActiveProvinceID(-1);
            this.AI_UseVictoryPoints();
            return false;
         }
      }
   }

   protected final boolean makeDemand_Province(int nProvinceID, int nCivID, int pointsUsedByCivID) {
      return this.makeDemand_Province(nProvinceID, nCivID, pointsUsedByCivID, false);
   }

   protected final boolean makeDemand_Province(int nProvinceID, int nCivID, int pointsUsedByCivID, boolean free_ToTrueOwner) {
      Gdx.app
         .log("AoC", "makeDemand_Province: nCivID: " + CFG.game.getCiv(nCivID).getCivName() + ", nProvinceID: " + CFG.game.getProvince(nProvinceID).getName());
      if (nCivID != pointsUsedByCivID && CFG.game.getProvince(nProvinceID).getTrueOwnerOfProvince() == nCivID) {
         pointsUsedByCivID = nCivID;
      }

      if (!free_ToTrueOwner) {
         for (int i = 0; i < this.peaceTreatyGameData.lCivsDemands_Defenders.size(); i++) {
            if (this.drawProvinceOwners.get(nProvinceID).isTaken > 0) {
               if (this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).iCivID == pointsUsedByCivID) {
                  if (this.drawProvinceOwners.get(nProvinceID).isTaken == pointsUsedByCivID) {
                     break;
                  }

                  if (this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).iVictoryPointsLeft < this.drawProvinceOwners.get(nProvinceID).iProvinceValue) {
                     return false;
                  }
               }
            } else if (this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).iCivID == pointsUsedByCivID
               && this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).iVictoryPointsLeft < this.drawProvinceOwners.get(nProvinceID).iProvinceValue) {
               return false;
            }
         }

         for (int var6 = 0; var6 < this.peaceTreatyGameData.lCivsDemands_Aggressors.size(); var6++) {
            if (this.drawProvinceOwners.get(nProvinceID).isTaken > 0) {
               if (this.peaceTreatyGameData.lCivsDemands_Aggressors.get(var6).iCivID == pointsUsedByCivID) {
                  if (this.drawProvinceOwners.get(nProvinceID).isTaken == pointsUsedByCivID) {
                     break;
                  }

                  if (this.peaceTreatyGameData.lCivsDemands_Aggressors.get(var6).iVictoryPointsLeft < this.drawProvinceOwners.get(nProvinceID).iProvinceValue) {
                     return false;
                  }
               }
            } else if (this.peaceTreatyGameData.lCivsDemands_Aggressors.get(var6).iCivID == pointsUsedByCivID
               && this.peaceTreatyGameData.lCivsDemands_Aggressors.get(var6).iVictoryPointsLeft < this.drawProvinceOwners.get(nProvinceID).iProvinceValue) {
               return false;
            }
         }
      }

      for (int ix = 0; ix < this.peaceTreatyGameData.lCivsDemands_Defenders.size(); ix++) {
         if (this.peaceTreatyGameData.lCivsDemands_Defenders.get(ix).iCivID == nCivID) {
            this.peaceTreatyGameData.lCivsDemands_Defenders.get(ix).addDemandOnProvince(nProvinceID);
         }

         if (this.drawProvinceOwners.get(nProvinceID).isTaken > 0) {
            if (this.peaceTreatyGameData.lCivsDemands_Defenders.get(ix).iCivID == this.drawProvinceOwners.get(nProvinceID).iCivID) {
               this.peaceTreatyGameData.lCivsDemands_Defenders.get(ix).removeDemandOnProvince(nProvinceID);
            }
         } else if (this.peaceTreatyGameData.lCivsDemands_Defenders.get(ix).iCivID == pointsUsedByCivID) {
            PeaceTreaty_Demands var10000 = this.peaceTreatyGameData.lCivsDemands_Defenders.get(ix);
            var10000.iVictoryPointsLeft = var10000.iVictoryPointsLeft - this.drawProvinceOwners.get(nProvinceID).iProvinceValue;
         }
      }

      for (int var8 = 0; var8 < this.peaceTreatyGameData.lCivsDemands_Aggressors.size(); var8++) {
         if (this.peaceTreatyGameData.lCivsDemands_Aggressors.get(var8).iCivID == nCivID) {
            this.peaceTreatyGameData.lCivsDemands_Aggressors.get(var8).addDemandOnProvince(nProvinceID);
         }

         if (this.drawProvinceOwners.get(nProvinceID).isTaken > 0) {
            if (this.peaceTreatyGameData.lCivsDemands_Aggressors.get(var8).iCivID == this.drawProvinceOwners.get(nProvinceID).iCivID) {
               this.peaceTreatyGameData.lCivsDemands_Aggressors.get(var8).removeDemandOnProvince(nProvinceID);
            }
         } else if (this.peaceTreatyGameData.lCivsDemands_Aggressors.get(var8).iCivID == pointsUsedByCivID) {
            PeaceTreaty_Demands var9 = this.peaceTreatyGameData.lCivsDemands_Aggressors.get(var8);
            var9.iVictoryPointsLeft = var9.iVictoryPointsLeft - this.drawProvinceOwners.get(nProvinceID).iProvinceValue;
         }
      }

      this.drawProvinceOwners.get(nProvinceID).isTaken = pointsUsedByCivID;
      this.drawProvinceOwners.get(nProvinceID).iCivID = nCivID;
      if (CFG.menuManager.getInGame_PeaceTreaty()) {
         CFG.menuManager.rebuildInGame_PeaceTreaty_Scores();
      }

      return true;
   }

   protected final void preparePeaceTreatyToSend(int iFromCivID) {
      for (int i2 = 0; i2 < this.peaceTreatyGameData.lCivsData_Defenders.size(); i2++) {
         for (int j2 = 0; j2 < this.peaceTreatyGameData.lCivsData_Defenders.get(i2).lProvincesLost.size(); j2++) {
            if (this.drawProvinceOwners.get(this.peaceTreatyGameData.lCivsData_Defenders.get(i2).lProvincesLost.get(j2)).isTaken < 0) {
               this.makeDemand_Province(
                  this.peaceTreatyGameData.lCivsData_Defenders.get(i2).lProvincesLost.get(j2),
                  this.peaceTreatyGameData.lCivsData_Defenders.get(i2).iCivID,
                  this.peaceTreatyGameData.lCivsData_Defenders.get(i2).iCivID,
                  true
               );
            }
         }
      }

      for (int var26 = 0; var26 < this.peaceTreatyGameData.lCivsData_Aggressors.size(); var26++) {
         for (int j2x = 0; j2x < this.peaceTreatyGameData.lCivsData_Aggressors.get(var26).lProvincesLost.size(); j2x++) {
            if (this.drawProvinceOwners.get(this.peaceTreatyGameData.lCivsData_Aggressors.get(var26).lProvincesLost.get(j2x)).isTaken < 0) {
               this.makeDemand_Province(
                  this.peaceTreatyGameData.lCivsData_Aggressors.get(var26).lProvincesLost.get(j2x),
                  this.peaceTreatyGameData.lCivsData_Aggressors.get(var26).iCivID,
                  this.peaceTreatyGameData.lCivsData_Aggressors.get(var26).iCivID,
                  true
               );
            }
         }
      }

      boolean updateData = false;

      for (int i = 0; i < this.peaceTreatyGameData.lCivsDemands_Defenders.size(); i++) {
         if (!CFG.game.getCiv(this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).iCivID).getControlledByPlayer()) {
            for (int j = this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).lDemands.size() - 1; j >= 0; j--) {
               int numOfConnections_Own = 0;
               int numOfConnections_Enemies = 0;

               for (int k2 = 0;
                  k2 < CFG.game.getProvince(this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).lDemands.get(j)).getNeighboringProvincesSize();
                  k2++
               ) {
                  if (this.drawProvinceOwners
                           .get(CFG.game.getProvince(this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).lDemands.get(j)).getNeighboringProvinces(k2))
                        .iCivID
                     == this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).iCivID) {
                     numOfConnections_Own++;
                  } else if (this.drawProvinceOwners
                              .get(CFG.game.getProvince(this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).lDemands.get(j)).getNeighboringProvinces(k2))
                           .iCivID
                        < 0
                     || CFG.game
                        .getCivsAtWar(
                           this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).iCivID,
                           this.drawProvinceOwners
                                 .get(CFG.game.getProvince(this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).lDemands.get(j)).getNeighboringProvinces(k2))
                              .iCivID
                        )) {
                     numOfConnections_Enemies++;
                  }
               }

               if (numOfConnections_Own <= 0
                  && numOfConnections_Enemies > 0
                  && (
                     CFG.game.getProvince(this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).lDemands.get(j)).getNeighboringProvincesSize() > 2
                        || CFG.game.getProvince(this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).lDemands.get(j)).getNeighboringSeaProvincesSize() <= 0
                  )) {
                  this.drawProvinceOwners.get(this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).lDemands.get(j)).isTaken = -1;
                  this.drawProvinceOwners.get(this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).lDemands.get(j)).iCivID = CFG.game
                        .getProvince(i)
                        .getCivID()
                     * -1;
                  this.peaceTreatyGameData
                     .lCivsDemands_Defenders
                     .get(i)
                     .removeDemandOnProvince(this.peaceTreatyGameData.lCivsDemands_Defenders.get(i).lDemands.get(j));
                  updateData = true;
               }
            }
         }
      }

      for (int var20 = 0; var20 < this.peaceTreatyGameData.lCivsDemands_Aggressors.size(); var20++) {
         if (!CFG.game.getCiv(this.peaceTreatyGameData.lCivsDemands_Aggressors.get(var20).iCivID).getControlledByPlayer()) {
            for (int j = this.peaceTreatyGameData.lCivsDemands_Aggressors.get(var20).lDemands.size() - 1; j >= 0; j--) {
               int numOfConnections_Own = 0;
               int numOfConnections_Enemies = 0;

               for (int k2x = 0;
                  k2x < CFG.game.getProvince(this.peaceTreatyGameData.lCivsDemands_Aggressors.get(var20).lDemands.get(j)).getNeighboringProvincesSize();
                  k2x++
               ) {
                  if (this.drawProvinceOwners
                           .get(CFG.game.getProvince(this.peaceTreatyGameData.lCivsDemands_Aggressors.get(var20).lDemands.get(j)).getNeighboringProvinces(k2x))
                        .iCivID
                     == this.peaceTreatyGameData.lCivsDemands_Aggressors.get(var20).iCivID) {
                     numOfConnections_Own++;
                  } else if (this.drawProvinceOwners
                              .get(
                                 CFG.game.getProvince(this.peaceTreatyGameData.lCivsDemands_Aggressors.get(var20).lDemands.get(j)).getNeighboringProvinces(k2x)
                              )
                           .iCivID
                        < 0
                     || CFG.game
                        .getCivsAtWar(
                           this.peaceTreatyGameData.lCivsDemands_Aggressors.get(var20).iCivID,
                           this.drawProvinceOwners
                                 .get(
                                    CFG.game
                                       .getProvince(this.peaceTreatyGameData.lCivsDemands_Aggressors.get(var20).lDemands.get(j))
                                       .getNeighboringProvinces(k2x)
                                 )
                              .iCivID
                        )) {
                     numOfConnections_Enemies++;
                  }
               }

               if (numOfConnections_Own <= 0
                  && numOfConnections_Enemies > 0
                  && (
                     CFG.game.getProvince(this.peaceTreatyGameData.lCivsDemands_Aggressors.get(var20).lDemands.get(j)).getNeighboringProvincesSize() > 2
                        || CFG.game.getProvince(this.peaceTreatyGameData.lCivsDemands_Aggressors.get(var20).lDemands.get(j)).getNeighboringSeaProvincesSize()
                           <= 0
                  )) {
                  this.drawProvinceOwners.get(this.peaceTreatyGameData.lCivsDemands_Aggressors.get(var20).lDemands.get(j)).isTaken = -1;
                  this.drawProvinceOwners.get(this.peaceTreatyGameData.lCivsDemands_Aggressors.get(var20).lDemands.get(j)).iCivID = CFG.game
                        .getProvince(var20)
                        .getCivID()
                     * -1;
                  this.peaceTreatyGameData
                     .lCivsDemands_Aggressors
                     .get(var20)
                     .removeDemandOnProvince(this.peaceTreatyGameData.lCivsDemands_Aggressors.get(var20).lDemands.get(j));
                  updateData = true;
               }
            }
         }
      }

      if (updateData) {
         for (int var21 = 0; var21 < this.peaceTreatyGameData.lCivsData_Defenders.size(); var21++) {
            for (int j = 0; j < this.peaceTreatyGameData.lCivsData_Defenders.get(var21).lProvincesLost.size(); j++) {
               if (this.drawProvinceOwners.get(this.peaceTreatyGameData.lCivsData_Defenders.get(var21).lProvincesLost.get(j)).isTaken < 0) {
                  this.makeDemand_Province(
                     this.peaceTreatyGameData.lCivsData_Defenders.get(var21).lProvincesLost.get(j),
                     this.peaceTreatyGameData.lCivsData_Defenders.get(var21).iCivID,
                     this.peaceTreatyGameData.lCivsData_Defenders.get(var21).iCivID,
                     true
                  );
               }
            }
         }

         for (int var22 = 0; var22 < this.peaceTreatyGameData.lCivsData_Aggressors.size(); var22++) {
            for (int jx = 0; jx < this.peaceTreatyGameData.lCivsData_Aggressors.get(var22).lProvincesLost.size(); jx++) {
               if (this.drawProvinceOwners.get(this.peaceTreatyGameData.lCivsData_Aggressors.get(var22).lProvincesLost.get(jx)).isTaken < 0) {
                  this.makeDemand_Province(
                     this.peaceTreatyGameData.lCivsData_Aggressors.get(var22).lProvincesLost.get(jx),
                     this.peaceTreatyGameData.lCivsData_Aggressors.get(var22).iCivID,
                     this.peaceTreatyGameData.lCivsData_Aggressors.get(var22).iCivID,
                     true
                  );
               }
            }
         }
      }

      for (int var23 = 0; var23 < this.peaceTreatyGameData.lCivsDemands_Defenders.size(); var23++) {
         for (int jxx = 0; jxx < this.peaceTreatyGameData.lCivsDemands_Defenders.get(var23).lReleasableCivs.size(); jxx++) {
            for (int k = this.peaceTreatyGameData.lCivsDemands_Defenders.get(var23).lReleasableCivs.get(jxx).lProvinces.size() - 1; k >= 0; k--) {
               if (this.drawProvinceOwners.get(this.peaceTreatyGameData.lCivsDemands_Defenders.get(var23).lReleasableCivs.get(jxx).lProvinces.get(k)).iCivID
                  != this.peaceTreatyGameData.lCivsDemands_Defenders.get(var23).lReleasableCivs.get(jxx).iCivID) {
                  this.peaceTreatyGameData.lCivsDemands_Defenders.get(var23).lReleasableCivs.get(jxx).lProvinces.remove(k);
               }
            }
         }

         if (iFromCivID == this.peaceTreatyGameData.lCivsDemands_Defenders.get(var23).iCivID) {
            this.peaceTreatyGameData.lCivsDemands_Defenders.get(var23).peaceTreatyAccepted = true;
         }
      }

      for (int var24 = 0; var24 < this.peaceTreatyGameData.lCivsDemands_Aggressors.size(); var24++) {
         for (int jxx = 0; jxx < this.peaceTreatyGameData.lCivsDemands_Aggressors.get(var24).lReleasableCivs.size(); jxx++) {
            for (int kx = this.peaceTreatyGameData.lCivsDemands_Aggressors.get(var24).lReleasableCivs.get(jxx).lProvinces.size() - 1; kx >= 0; kx--) {
               if (this.drawProvinceOwners.get(this.peaceTreatyGameData.lCivsDemands_Aggressors.get(var24).lReleasableCivs.get(jxx).lProvinces.get(kx)).iCivID
                  != this.peaceTreatyGameData.lCivsDemands_Aggressors.get(var24).lReleasableCivs.get(jxx).iCivID) {
                  this.peaceTreatyGameData.lCivsDemands_Aggressors.get(var24).lReleasableCivs.get(jxx).lProvinces.remove(kx);
               }
            }
         }

         if (iFromCivID == this.peaceTreatyGameData.lCivsDemands_Aggressors.get(var24).iCivID) {
            this.peaceTreatyGameData.lCivsDemands_Aggressors.get(var24).peaceTreatyAccepted = true;
         }
      }
   }

   protected static int getProposal_Positive(boolean scoreCountDefenders) {
      return 0;
   }

   protected static int getProposal_Negative(boolean scoreCountDefenders) {
      return 0;
   }
}
