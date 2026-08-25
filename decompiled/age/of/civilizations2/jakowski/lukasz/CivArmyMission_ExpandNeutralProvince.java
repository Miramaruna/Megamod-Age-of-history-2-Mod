package age.of.civilizations2.jakowski.lukasz;

import java.util.ArrayList;
import java.util.List;

public class CivArmyMission_ExpandNeutralProvince extends CivArmyMission {
   public int iCivID;
   public int iConquerProvinceID;
   public int iRegroupArmyPlace = -1;
   public int iRangeOfRegroup = 3;

   public CivArmyMission_ExpandNeutralProvince(int nCivID, int conquerProvinceID) {
      this.toProvinceID = conquerProvinceID;
      this.iConquerProvinceID = conquerProvinceID;
      this.MISSION_ID = -1;
      this.iCivID = nCivID;
      this.MISSION_TYPE = CivArmyMission_Type.EXPAND_NETURAL_PROVINCE;
      this.TURN_ID = Game_Calendar.TURN_ID;
      this.iObsolate = 4;
      this.iArmy = 0;
      this.action(nCivID);
   }

   @Override
   public boolean action(int nCivID) {
      ArrayList<Integer> possibleFrom = new ArrayList<>();
      if (CFG.game.getProvince(this.iConquerProvinceID).getCivID() != 0) {
         this.iObsolate = -1;
         return true;
      } else {
         for (int i3 = 0; i3 < CFG.game.getProvince(this.iConquerProvinceID).getNeighboringProvincesSize(); i3++) {
            if (CFG.game.getProvince(CFG.game.getProvince(this.iConquerProvinceID).getNeighboringProvinces(i3)).getCivID() == this.iCivID) {
               possibleFrom.add(CFG.game.getProvince(this.iConquerProvinceID).getNeighboringProvinces(i3));
            }
         }

         if (possibleFrom.size() == 0) {
            this.iObsolate = -1;
            return true;
         } else {
            if (CFG.game.getProvince(this.iConquerProvinceID).getArmy(0)
               > CFG.game.getCiv(this.iCivID).getNumOfUnits() + CFG.game.getCiv(this.iCivID).getMoney() / 5L) {
               CFG.game.getCiv(this.iCivID).civGameData.iLockTreasury = (
                     CFG.game.getProvince(this.iConquerProvinceID).getArmy(0) + 5 - CFG.game.getCiv(this.iCivID).getNumOfUnits()
                  )
                  * 5;
               this.iObsolate++;
            }

            ArrayList canMoveImmediately = new ArrayList();

            for (int i4 = possibleFrom.size() - 1; i4 >= 0; i4--) {
               if (CFG.game.getProvince(possibleFrom.get(i4)).getArmyCivID(this.iCivID)
                     - CFG.game.getCiv(this.iCivID).civGameData.civPlans.haveMission_Army(possibleFrom.get(i4))
                  > CFG.game.getProvince(this.iConquerProvinceID).getArmy(0)) {
                  canMoveImmediately.add(possibleFrom.get(i4));
               }
            }

            if (canMoveImmediately.size() > 0) {
               int randID = CFG.oR.nextInt(canMoveImmediately.size());
               int numOfNeutral = 0;

               for (int k = 0; k < CFG.game.getProvince((Integer)canMoveImmediately.get(randID)).getNeighboringProvincesSize(); k++) {
                  if (CFG.game.getProvince(CFG.game.getProvince((Integer)canMoveImmediately.get(randID)).getNeighboringProvinces(k)).getCivID() == 0) {
                     numOfNeutral++;
                  }
               }

               int tArmyToMove = CFG.game.getProvince((Integer)canMoveImmediately.get(randID)).getArmyCivID(this.iCivID);
               if (numOfNeutral > 1) {
                  tArmyToMove = CFG.game.getProvince(this.iConquerProvinceID).getArmy(0) + 5 + CFG.oR.nextInt(5);
               }

               if (!CFG.gameAction.moveArmy((Integer)canMoveImmediately.get(randID), this.iConquerProvinceID, tArmyToMove, this.iCivID, true, false)) {
                  return false;
               } else {
                  this.iProvinceID = (Integer)canMoveImmediately.get(randID);
                  this.iObsolate = -1;
                  return true;
               }
            } else {
               canMoveImmediately.clear();
               int nArmiesInNeighbooringProvinces = 0;

               for (int i5 = possibleFrom.size() - 1; i5 >= 0; i5--) {
                  if (CFG.game.getProvince(possibleFrom.get(i5)).getArmyCivID(this.iCivID)
                        - CFG.game.getCiv(this.iCivID).civGameData.civPlans.haveMission_Army(possibleFrom.get(i5))
                     > 0) {
                     canMoveImmediately.add(possibleFrom.get(i5));
                     nArmiesInNeighbooringProvinces += CFG.game.getProvince(possibleFrom.get(i5)).getArmyCivID(this.iCivID)
                        - CFG.game.getCiv(this.iCivID).civGameData.civPlans.haveMission_Army(possibleFrom.get(i5));
                  }
               }

               if (CFG.game.getProvince(this.iConquerProvinceID).getArmy(0) + 4 < nArmiesInNeighbooringProvinces) {
                  ArrayList sortedByArmy = new ArrayList();

                  while (canMoveImmediately.size() > 0) {
                     int tBest = 0;

                     for (int i2 = canMoveImmediately.size() - 1; i2 > 0; i2--) {
                        if (CFG.game.getProvince((Integer)canMoveImmediately.get(tBest)).getArmyCivID(this.iCivID)
                              - CFG.game.getCiv(this.iCivID).civGameData.civPlans.haveMission_Army((Integer)canMoveImmediately.get(tBest))
                           < CFG.game.getProvince((Integer)canMoveImmediately.get(i2)).getArmyCivID(this.iCivID)
                              - CFG.game.getCiv(this.iCivID).civGameData.civPlans.haveMission_Army((Integer)canMoveImmediately.get(i2))) {
                           tBest = i2;
                        }
                     }

                     sortedByArmy.add(canMoveImmediately.get(tBest));
                     canMoveImmediately.remove(tBest);
                  }

                  for (int i6 = 0; i6 < sortedByArmy.size(); i6++) {
                     if (!CFG.gameAction
                        .moveArmy(
                           (Integer)sortedByArmy.get(i6),
                           this.iConquerProvinceID,
                           CFG.game.getProvince((Integer)sortedByArmy.get(i6)).getArmyCivID(this.iCivID)
                              - CFG.game.getCiv(this.iCivID).civGameData.civPlans.haveMission_Army((Integer)sortedByArmy.get(i6)),
                           nCivID,
                           true,
                           false
                        )) {
                        return false;
                     }
                  }
               }

               if (this.iRegroupArmyPlace < 0) {
                  this.iProvinceID = this.iRegroupArmyPlace = possibleFrom.get(0);
                  this.iArmy = CFG.game.getProvince(this.iRegroupArmyPlace).getArmyCivID(this.iCivID);
               } else if (CFG.game.getProvince(this.iRegroupArmyPlace).getCivID() != nCivID) {
                  this.iProvinceID = this.iRegroupArmyPlace = possibleFrom.get(0);
                  this.iArmy = CFG.game.getProvince(this.iRegroupArmyPlace).getArmyCivID(this.iCivID);
               } else {
                  if (CFG.game.getProvince(this.iRegroupArmyPlace).getArmyCivID(this.iCivID) > 2) {
                     CFG.gameAction
                        .moveArmy(
                           this.iRegroupArmyPlace,
                           this.iConquerProvinceID,
                           CFG.game.getProvince(this.iRegroupArmyPlace).getArmyCivID(this.iCivID),
                           this.iCivID,
                           true,
                           false
                        );
                  }

                  this.iProvinceID = this.iRegroupArmyPlace;
                  this.iArmy = CFG.game.getProvince(this.iRegroupArmyPlace).getArmyCivID(this.iCivID);
               }

               int requiredArmy = CFG.game.getProvince(this.iConquerProvinceID).getArmy(0)
                  - CFG.game.getCiv(nCivID).isMovingUnitsToProvinceID_Num(this.iConquerProvinceID)
                  - CFG.game.getCiv(nCivID).isRegoupingArmy_ToProvinceID(this.iRegroupArmyPlace);
               if (CFG.game.getCiv(nCivID).getNumOfUnits() > requiredArmy) {
                  if (requiredArmy <= 0) {
                     return false;
                  }

                  List<AI_NeighProvinces_Army> closestArmy = CFG.oAI
                     .getAllNeighboringProvincesInRange_WithArmyToRegroup(
                        this.iRegroupArmyPlace, nCivID, this.iRangeOfRegroup, true, false, new ArrayList<>(), new ArrayList<>(), requiredArmy
                     );
                  int nClosestArmy_Num = 0;

                  for (int i = closestArmy.size() - 1; i >= 0; i--) {
                     nClosestArmy_Num += closestArmy.get(i).iArmy;
                  }

                  if (nClosestArmy_Num <= requiredArmy) {
                     if (CFG.game.getCiv(nCivID).getMovePoints() < CFG.ideologiesManager.getIdeology(CFG.game.getCiv(nCivID).getIdeologyID()).COST_OF_RECRUIT) {
                        return false;
                     }

                     List<AI_NeighProvinces> listOfPossibleProvincesToRecruit = CFG.oAI
                        .getAllNeighboringProvincesInRange_Recruit(this.iRegroupArmyPlace, nCivID, 3, true, false, new ArrayList<>(), new ArrayList<>());
                     if (this.iRegroupArmyPlace >= 0
                        && !CFG.game.getProvince(this.iRegroupArmyPlace).isOccupied()
                        && CFG.game.getProvince(this.iRegroupArmyPlace).getCivID() == nCivID) {
                        listOfPossibleProvincesToRecruit.add(new AI_NeighProvinces(this.iRegroupArmyPlace, 1));
                     }

                     if (listOfPossibleProvincesToRecruit.size() <= 0) {
                        return false;
                     }

                     int tempRand = 0;
                     int tBest = 0;
                     int tBestArmy = CFG.gameAction.getRecruitableArmy(listOfPossibleProvincesToRecruit.get(tBest).iProvinceID);

                     for (int kx = 1; kx < listOfPossibleProvincesToRecruit.size(); kx++) {
                        if (tBestArmy < CFG.gameAction.getRecruitableArmy(listOfPossibleProvincesToRecruit.get(kx).iProvinceID)) {
                           tBest = kx;
                           tBestArmy = CFG.gameAction.getRecruitableArmy(listOfPossibleProvincesToRecruit.get(kx).iProvinceID);
                        }
                     }

                     int tArmyToRecruit = Math.min(
                        requiredArmy,
                        Math.min(
                           CFG.gameAction.getRecruitableArmy(listOfPossibleProvincesToRecruit.get(tBest).iProvinceID),
                           (int)(
                              CFG.game.getCiv(nCivID).getMoney()
                                 / (CFG.game.getProvince(listOfPossibleProvincesToRecruit.get(tBest).iProvinceID).getLevelOfArmoury() > 0 ? 4 : 5)
                           )
                        )
                     );
                     CFG.game.getCiv(nCivID).recruitArmy_AI(listOfPossibleProvincesToRecruit.get(tBest).iProvinceID, tArmyToRecruit);
                     int tempArmy = CFG.game.getCiv(nCivID).getRecruitArmy_BasedOnProvinceID(listOfPossibleProvincesToRecruit.get(tBest).iProvinceID);
                     if (tempArmy <= 0) {
                        return false;
                     }

                     CFG.game
                        .getCiv(nCivID)
                        .civGameData
                        .civPlans
                        .lArmiesMissions
                        .add(
                           new CivArmyMission_RegroupAfterRecruitment(
                              nCivID, listOfPossibleProvincesToRecruit.get(tBest).iProvinceID, this.iRegroupArmyPlace, tempArmy
                           )
                        );
                     return false;
                  }

                  for (int var16 = closestArmy.size() - 1; var16 >= 0; var16--) {
                     boolean alreadyNeighboors = false;

                     for (int j = 0; j < CFG.game.getProvince(closestArmy.get(var16).iProvinceID).getNeighboringProvincesSize(); j++) {
                        if (this.iConquerProvinceID == CFG.game.getProvince(closestArmy.get(var16).iProvinceID).getNeighboringProvinces(j)) {
                           alreadyNeighboors = true;
                           break;
                        }
                     }

                     RegroupArmy_Data tryRegroupArmy;
                     if (!alreadyNeighboors
                        && (tryRegroupArmy = new RegroupArmy_Data(nCivID, closestArmy.get(var16).iProvinceID, this.iRegroupArmyPlace)).getRouteSize() > 0) {
                        if (tryRegroupArmy.getRouteSize() == 1) {
                           if (!CFG.gameAction
                              .moveArmy(closestArmy.get(var16).iProvinceID, this.iRegroupArmyPlace, closestArmy.get(var16).iArmy, nCivID, true, false)) {
                              return false;
                           }

                           requiredArmy -= closestArmy.get(var16).iArmy;
                        } else {
                           if (!CFG.gameAction
                              .moveArmy(closestArmy.get(var16).iProvinceID, tryRegroupArmy.getRoute(0), closestArmy.get(var16).iArmy, nCivID, true, false)) {
                              return false;
                           }

                           tryRegroupArmy.setFromProvinceID(tryRegroupArmy.getRoute(0));
                           tryRegroupArmy.removeRoute(0);
                           tryRegroupArmy.setNumOfUnits(closestArmy.get(var16).iArmy);
                           CFG.game.getCiv(nCivID).addRegroupArmy(tryRegroupArmy);
                           requiredArmy -= closestArmy.get(var16).iArmy;
                        }
                     }

                     if (requiredArmy < 0) {
                        return false;
                     }
                  }
               }

               if (requiredArmy > 0) {
                  int recrutiableUnits_Treasury = (int)(CFG.game.getCiv(nCivID).getMoney() / 5L);
                  if (recrutiableUnits_Treasury > requiredArmy) {
                     canMoveImmediately.clear();

                     for (int i2x = possibleFrom.size() - 1; i2x >= 0; i2x--) {
                        if (CFG.gameAction.getRecruitableArmy(possibleFrom.get(i2x), nCivID) > requiredArmy) {
                           canMoveImmediately.add(possibleFrom.get(i2x));
                        }
                     }

                     if (canMoveImmediately.size() != 0) {
                        int tRand = CFG.oR.nextInt(canMoveImmediately.size());
                        CFG.game.getCiv(nCivID).recruitArmy_AI((Integer)canMoveImmediately.get(tRand), requiredArmy + 5 + CFG.oR.nextInt(5));
                        return false;
                     }

                     int tBest = 0;

                     for (int i = possibleFrom.size() - 1; i > 0; i--) {
                        if (CFG.gameAction.getRecruitableArmy(possibleFrom.get(i), nCivID) > CFG.gameAction.getRecruitableArmy(possibleFrom.get(tBest), nCivID)
                           )
                         {
                           tBest = i;
                        }
                     }

                     CFG.game.getCiv(nCivID).recruitArmy_AI(possibleFrom.get(tBest), requiredArmy + 5 + CFG.oR.nextInt(5));
                  } else {
                     this.iRangeOfRegroup = 6;
                  }
               }

               for (int i7 = 0; i7 < possibleFrom.size(); i7++) {
                  if (!CFG.gameAction
                     .moveArmy(
                        possibleFrom.get(i7),
                        this.iConquerProvinceID,
                        CFG.game.getProvince(possibleFrom.get(i7)).getArmyCivID(this.iCivID),
                        this.iCivID,
                        true,
                        false
                     )) {
                     return false;
                  }
               }

               return false;
            }
         }
      }
   }

   @Override
   public void onRemove() {
      CFG.game.getCiv(this.iCivID).civGameData.iLockTreasury = -1;
   }
}
