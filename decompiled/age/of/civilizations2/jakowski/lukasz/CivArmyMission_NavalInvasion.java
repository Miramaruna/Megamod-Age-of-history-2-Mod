package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.Gdx;
import java.util.ArrayList;
import java.util.List;

public class CivArmyMission_NavalInvasion extends CivArmyMission {
   public int iCivID;
   public int moveArmyInNextXTurns = 1;
   public int numOfUnitsMoved = 0;

   public CivArmyMission_NavalInvasion(int nCivID, int fromProvinceID, int toProvinceID) {
      this.iProvinceID = fromProvinceID;
      this.toProvinceID = toProvinceID;
      this.MISSION_ID = -1;
      this.iCivID = nCivID;
      this.MISSION_TYPE = CivArmyMission_Type.NAVAL_INVASION;
      this.TURN_ID = Game_Calendar.TURN_ID;
      this.iObsolate = 10;
      this.iArmy = (int)(CFG.game.getProvince(fromProvinceID).getArmyCivID(nCivID) * (0.685F + CFG.oR.nextInt(25) / 100.0F));
   }

   @Override
   public boolean canMakeAction(int nCivID, int iTurnsLeft) {
      return true;
   }

   @Override
   public boolean action(int nCivID) {
      if (!CFG.game.getCivsAtWar(nCivID, CFG.game.getProvince(this.toProvinceID).getCivID())) {
         if (!CFG.game.getCivsAreAllied(nCivID, CFG.game.getProvince(this.toProvinceID).getCivID())) {
            this.iObsolate = 0;
            return true;
         }

         if (!CFG.game
            .getCiv(CFG.game.getProvince(this.toProvinceID).getCivID())
            .getCivRegion(CFG.game.getProvince(this.toProvinceID).getCivRegionID())
            .checkRegionBordersWithEnemy(nCivID)) {
            this.iObsolate = 0;
            return true;
         }
      }

      if (CFG.game.getProvince(this.iProvinceID).getBordersWithEnemy()) {
         this.iObsolate = 0;
         return true;
      } else if (CFG.game
         .getCiv(CFG.game.getProvince(this.iProvinceID).getCivID())
         .getCivRegion(CFG.game.getProvince(this.iProvinceID).getCivRegionID())
         .checkRegionBordersWithEnemy(nCivID)) {
         this.iObsolate = 0;
         return true;
      } else if (CFG.game.getCiv(nCivID).getMovePoints() < CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivID).getIdeologyID()).COST_OF_MOVE * 2.5F) {
         return false;
      } else {
         Gdx.app.log("AoC", "NavalInvasion -> " + CFG.game.getCiv(nCivID).getCivName());
         if (CFG.game.getProvince(this.iProvinceID).getLevelOfPort() <= 0 || this.action_BuildPort(this.iProvinceID, this.toProvinceID)) {
            Gdx.app.log("AoC", "NavalInvasion -> 000, moveArmyInNextXTurns: " + this.moveArmyInNextXTurns);
            if (CFG.game.getProvince(this.iProvinceID).getArmyCivID(nCivID) > 0 && this.moveArmyInNextXTurns-- <= 0) {
               RegroupArmy_Data_AtWar tryRegroupArmy = new RegroupArmy_Data_AtWar(nCivID, this.iProvinceID, this.toProvinceID);
               if (tryRegroupArmy.getRouteSize() <= 0) {
                  this.iObsolate = 0;
                  return true;
               }

               int tArmyToRecruit_PRE = CFG.game.getProvince(this.iProvinceID).getArmyCivID(nCivID);
               int enemyArmy = 0;
               if (CFG.game.getCivsAtWar(nCivID, CFG.game.getProvince(this.toProvinceID).getCivID())) {
                  for (int i = 0;
                     i
                        < CFG.game
                           .getCiv(CFG.game.getProvince(this.toProvinceID).getCivID())
                           .getCivRegion(CFG.game.getProvince(this.toProvinceID).getCivRegionID())
                           .getProvincesSize();
                     i++
                  ) {
                     enemyArmy += CFG.game
                        .getProvinceArmy(
                           CFG.game
                              .getCiv(CFG.game.getProvince(this.toProvinceID).getCivID())
                              .getCivRegion(CFG.game.getProvince(this.toProvinceID).getCivRegionID())
                              .getProvince(i)
                        );
                  }
               } else if (!CFG.game
                  .getCiv(CFG.game.getProvince(this.toProvinceID).getCivID())
                  .getCivRegion(CFG.game.getProvince(this.toProvinceID).getCivRegionID())
                  .checkRegionBordersWithEnemy(nCivID)) {
                  this.iObsolate = 0;
                  return true;
               }

               Gdx.app.log("AoC", "NavalInvasion -> enemyArmy: " + enemyArmy);
               if (enemyArmy * 0.825F > CFG.game.getCiv(nCivID).getNumOfUnits()) {
                  this.moveArmyInNextXTurns = Math.max(this.moveArmyInNextXTurns, 3);
                  Gdx.app.log("AoC", "NavalInvasion -> enemyArmy: RETURN 000");
                  return false;
               }

               if (enemyArmy * 0.915F > tArmyToRecruit_PRE + this.numOfUnitsMoved) {
                  this.moveArmyInNextXTurns = Math.max(this.moveArmyInNextXTurns, 3);
                  Gdx.app.log("AoC", "NavalInvasion -> enemyArmy: RETURN 1111");
                  return false;
               }

               if (tArmyToRecruit_PRE > enemyArmy * 1.175F) {
                  if ((float)CFG.game.getCiv(CFG.game.getProvince(this.toProvinceID).getCivID()).getMoney()
                        + Math.max(0.0F, CFG.game.getCiv(CFG.game.getProvince(this.toProvinceID).getCivID()).iBudget * 1.5F)
                     > 0.0F) {
                     if (tArmyToRecruit_PRE > (float)(enemyArmy + CFG.game.getCiv(CFG.game.getProvince(this.toProvinceID).getCivID()).getMoney() / 5L) * 1.175F
                        )
                      {
                        tArmyToRecruit_PRE = (int)Math.min(
                           (double)tArmyToRecruit_PRE,
                           5 + CFG.oR.nextInt(10)
                              + Math.ceil(
                                 (
                                       enemyArmy
                                          + (
                                                (float)CFG.game.getCiv(CFG.game.getProvince(this.toProvinceID).getCivID()).getMoney()
                                                   + Math.max(0.0F, CFG.game.getCiv(CFG.game.getProvince(this.toProvinceID).getCivID()).iBudget * 1.5F)
                                             )
                                             / 5.0F
                                    )
                                    * 1.175F
                                    * (1.745F + CFG.oR.nextInt(925) / 1000.0F)
                              )
                        );
                        Gdx.app.log("AoC", "NavalInvasion -> enemyArmy: UPDATE 000");
                     }
                  } else {
                     tArmyToRecruit_PRE = (int)Math.min(
                        (double)tArmyToRecruit_PRE, 5 + CFG.oR.nextInt(10) + Math.ceil(enemyArmy * (1.745F + CFG.oR.nextInt(925) / 1000.0F))
                     );
                     Gdx.app.log("AoC", "NavalInvasion -> enemyArmy: UPDATE 111");
                  }
               }

               if (tArmyToRecruit_PRE > CFG.game.getCiv(nCivID).getNumOfUnits() * (0.525F + CFG.oR.nextInt(150) / 1000.0F)) {
                  tArmyToRecruit_PRE = (int)((CFG.game.getCiv(nCivID).getNumOfUnits() - this.numOfUnitsMoved) * (0.525F + CFG.oR.nextInt(150) / 1000.0F));
                  if (tArmyToRecruit_PRE <= 0) {
                     this.moveArmyInNextXTurns = 6 + tryRegroupArmy.getRouteSize();
                     this.iArmy = 0;
                     return false;
                  }
               } else if (tArmyToRecruit_PRE + this.numOfUnitsMoved > CFG.game.getCiv(nCivID).getNumOfUnits() * 0.525F) {
                  this.moveArmyInNextXTurns = 5 + tryRegroupArmy.getRouteSize();
                  this.iArmy = 0;
                  return false;
               }

               if (CFG.game.getCiv(nCivID).getBordersWithEnemy() == 0
                  && CFG.game.getProvince(this.iProvinceID).getCivID() == nCivID
                  && CFG.game.getProvince(this.toProvinceID).getCivID() != nCivID) {
                  tArmyToRecruit_PRE = (int)Math.ceil(tArmyToRecruit_PRE * (0.46F + CFG.oR.nextInt(33) / 100.0F));
               }

               Gdx.app.log("AoC", "NavalInvasion -> enemyArmy: tArmyToRecruit_PRE: " + tArmyToRecruit_PRE);
               if (tryRegroupArmy.getRouteSize() == 1) {
                  if (CFG.gameAction.moveArmy(this.iProvinceID, this.toProvinceID, tArmyToRecruit_PRE, nCivID, true, false)) {
                     this.moveArmyInNextXTurns = 3;
                     this.numOfUnitsMoved += tArmyToRecruit_PRE;
                     CFG.game.getCiv(nCivID).civGameData.iNextPossibleNavalInvastionTurnID = Game_Calendar.TURN_ID
                        + 4
                        + tryRegroupArmy.getRouteSize()
                        + CFG.oR.nextInt(14);
                     this.iObsolate = 0;
                     return true;
                  }

                  this.moveArmyInNextXTurns = 1;
               } else if (CFG.gameAction.moveArmy(this.iProvinceID, tryRegroupArmy.getRoute(0), tArmyToRecruit_PRE, nCivID, true, false)) {
                  tryRegroupArmy.setFromProvinceID(tryRegroupArmy.getRoute(0));
                  tryRegroupArmy.removeRoute(0);
                  tryRegroupArmy.setNumOfUnits(tArmyToRecruit_PRE);
                  CFG.game.getCiv(nCivID).addRegroupArmy(tryRegroupArmy);
                  this.moveArmyInNextXTurns = 3;
                  this.numOfUnitsMoved += tArmyToRecruit_PRE;
                  CFG.game.getCiv(nCivID).civGameData.iNextPossibleNavalInvastionTurnID = Game_Calendar.TURN_ID
                     + 4
                     + tryRegroupArmy.getRouteSize()
                     + CFG.oR.nextInt(14);
                  this.iObsolate = 0;
                  return true;
               }
            }

            Gdx.app.log("AoC", "NavalInvasion -> 111");
            this.action_RecruitArmy(nCivID);
            Gdx.app.log("AoC", "NavalInvasion -> 222");
            List<AI_NeighProvinces> listOfPossibleProvinces;
            if (CFG.game.getCiv(nCivID).getMovePoints()
                  >= CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivID).getIdeologyID()).COST_OF_RECRUIT
                     + CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivID).getIdeologyID()).COST_OF_MOVE_OWN_PROVINCE
               && this.iObsolate > 1
               && (listOfPossibleProvinces = CFG.oAI
                        .getAllNeighboringProvincesInRange_Regroup_ForNavalInvasion(
                           this.iProvinceID, nCivID, this.iObsolate, new ArrayList<>(), new ArrayList<>()
                        ))
                     .size()
                  > 0) {
               while (
                  CFG.game.getCiv(nCivID).getMovePoints()
                        >= CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivID).getIdeologyID()).COST_OF_RECRUIT
                           + CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivID).getIdeologyID()).COST_OF_MOVE_OWN_PROVINCE
                     && listOfPossibleProvinces.size() > 0
               ) {
                  int iBestID = 0;

                  for (int i = listOfPossibleProvinces.size() - 1; i > 0; i--) {
                     if (CFG.game.getProvince(listOfPossibleProvinces.get(i).iProvinceID).getArmyCivID(nCivID)
                        > CFG.game.getProvince(listOfPossibleProvinces.get(iBestID).iProvinceID).getArmyCivID(nCivID)) {
                        iBestID = i;
                     }
                  }

                  int tArmyToRecruit_PREx = CFG.game.getProvince(listOfPossibleProvinces.get(iBestID).iProvinceID).getArmyCivID(nCivID)
                     - CFG.game.getCiv(nCivID).civGameData.civPlans.haveMission_Army(listOfPossibleProvinces.get(iBestID).iProvinceID);
                  RegroupArmy_Data tryRegroupArmyx;
                  if (tArmyToRecruit_PREx > 0
                     && (tryRegroupArmyx = new RegroupArmy_Data(nCivID, listOfPossibleProvinces.get(iBestID).iProvinceID, this.iProvinceID)).getRouteSize() > 0
                     )
                   {
                     if (tryRegroupArmyx.getRouteSize() == 1) {
                        if (!CFG.gameAction
                           .moveArmy(listOfPossibleProvinces.get(iBestID).iProvinceID, this.iProvinceID, tArmyToRecruit_PREx, nCivID, true, false)) {
                        }
                     } else if (CFG.gameAction
                        .moveArmy(listOfPossibleProvinces.get(iBestID).iProvinceID, tryRegroupArmyx.getRoute(0), tArmyToRecruit_PREx, nCivID, true, false)) {
                        tryRegroupArmyx.setFromProvinceID(tryRegroupArmyx.getRoute(0));
                        tryRegroupArmyx.removeRoute(0);
                        tryRegroupArmyx.setNumOfUnits(tArmyToRecruit_PREx);
                        CFG.game.getCiv(nCivID).addRegroupArmy(tryRegroupArmyx);
                     }
                  }

                  listOfPossibleProvinces.remove(iBestID);
               }
            }

            Gdx.app.log("AoC", "NavalInvasion -> 333");
         }

         this.iArmy = (int)(CFG.game.getProvince(this.iProvinceID).getArmyCivID(nCivID) * (0.685F + CFG.oR.nextInt(25) / 100.0F));
         return false;
      }
   }

   public final boolean action_RecruitArmy(int nCivID) {
      Gdx.app.log("AoC", "NavalInvasion -> action_RecruitArmy");
      if (CFG.ideologiesManager.getIdeology(CFG.game.getCiv(this.iCivID).getIdeologyID()).COST_OF_RECRUIT <= CFG.game.getCiv(this.iCivID).getMovePoints()) {
         ArrayList<AI_ProvinceInfo_War> nProvinces = new ArrayList<>();
         nProvinces.add(new AI_ProvinceInfo_War(this.iProvinceID, 100, true));
         CFG.oAI.getAI_Style(CFG.game.getCiv(nCivID).getAI_Style()).moveAtWar_Recruit(nCivID, nProvinces, new ArrayList<>(), true);
      }

      Gdx.app.log("AoC", "NavalInvasion -> action_RecruitArmy END");
      return true;
   }

   public final boolean action_BuildPort(int nFromProvinceID, int toProvinceID) {
      if (CFG.game.getProvince(nFromProvinceID).getLevelOfPort() == 0) {
         if (CFG.game.getProvince(nFromProvinceID).getCivID() == this.iCivID) {
            if (CFG.game.getCiv(this.iCivID).isInConstruction(nFromProvinceID, ConstructionType.PORT) == 0) {
               if (BuildingsManager.constructPort(nFromProvinceID, this.iCivID)) {
                  this.iObsolate = 10;
                  return false;
               } else {
                  this.lockTreasury_Port(nFromProvinceID);
                  return false;
               }
            } else {
               return false;
            }
         } else {
            this.iObsolate = 0;
            return false;
         }
      } else {
         return true;
      }
   }

   public final void lockTreasury_Port(int nProvinceID) {
      int colonizeCost = (int)(BuildingsManager.getPort_BuildCost(CFG.game.getProvince(nProvinceID).getLevelOfPort() + 1, nProvinceID) * 1.05F);
      CFG.game.getCiv(this.iCivID).civGameData.iLockTreasury = Math.max(CFG.game.getCiv(this.iCivID).civGameData.iLockTreasury, colonizeCost);
      if (CFG.game.getCiv(this.iCivID).iBudget > 0 && CFG.game.getCiv(this.iCivID).getMoney() > 0L && CFG.game.getCiv(this.iCivID).getMoney() < colonizeCost) {
      }
   }
}
