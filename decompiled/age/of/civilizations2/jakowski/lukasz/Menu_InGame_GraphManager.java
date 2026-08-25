package age.of.civilizations2.jakowski.lukasz;

import java.util.ArrayList;
import java.util.List;

public class Menu_InGame_GraphManager {
   public static int iActiveGraphID = 0;

   Menu_InGame_GraphManager() {
   }

   public static final void setActiveGraphID(int nID) {
      if (iActiveGraphID != nID) {
         iActiveGraphID = nID;
      }

      if (iActiveGraphID == 0) {
         ArrayList<Graph_Vertical_Data> tempData = new ArrayList<>();

         for (int i = 1; i < CFG.game.getCivsSize(); i++) {
            if (CFG.FOG_OF_WAR < 2 || CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetCivilization(i)) {
               tempData.add(new Graph_Vertical_Data(i));
            }
         }

         updateGraph(
            new Graph_Vertical(
               Graph_Vertical_Data_Type.NUM_OF_PROVINCES_BY_CONTINENT,
               CFG.langManager.get("Civilizations"),
               CFG.langManager.get("Provinces"),
               CFG.menuManager.getInGame_FlagActionGraph().getMenuElement(0).getPosX(),
               CFG.menuManager.getInGame_FlagActionGraph().getMenuElement(0).getPosY(),
               CFG.menuManager.getInGame_FlagActionGraph().getMenuElement(0).getWidth(),
               CFG.menuManager.getInGame_FlagActionGraph().getMenuElement(0).getHeight(),
               true,
               tempData
            )
         );
      } else if (iActiveGraphID == 1) {
         ArrayList<Graph_Vertical_Data> tempL = new ArrayList<>();

         for (int ix = 1; ix < CFG.game.getCivsSize(); ix++) {
            if (CFG.FOG_OF_WAR < 2 || CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetCivilization(ix)) {
               tempL.add(new Graph_Vertical_Data(ix));
            }
         }

         updateGraph(
            new Graph_Vertical(
               Graph_Vertical_Data_Type.POPULATION_OF_CIVILIZATIONS,
               CFG.langManager.get("Civilizations"),
               CFG.langManager.get("Population"),
               CFG.menuManager.getInGame_FlagActionGraph().getMenuElement(0).getPosX(),
               CFG.menuManager.getInGame_FlagActionGraph().getMenuElement(0).getPosY(),
               CFG.menuManager.getInGame_FlagActionGraph().getMenuElement(0).getWidth(),
               CFG.menuManager.getInGame_FlagActionGraph().getMenuElement(0).getHeight(),
               true,
               tempL
            )
         );
      } else if (iActiveGraphID == 10) {
         ArrayList<Graph_Vertical_Data> tempL = new ArrayList<>();

         for (int ixx = 1; ixx < CFG.game.getCivsSize(); ixx++) {
            if (CFG.FOG_OF_WAR < 2 || CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetCivilization(ixx)) {
               tempL.add(new Graph_Vertical_Data(ixx));
            }
         }

         updateGraph(
            new Graph_Vertical(
               Graph_Vertical_Data_Type.CONQUERED_PROVINCES,
               CFG.langManager.get("Civilizations"),
               CFG.langManager.get("ConqueredProvinces"),
               CFG.menuManager.getInGame_FlagActionGraph().getMenuElement(0).getPosX(),
               CFG.menuManager.getInGame_FlagActionGraph().getMenuElement(0).getPosY(),
               CFG.menuManager.getInGame_FlagActionGraph().getMenuElement(0).getWidth(),
               CFG.menuManager.getInGame_FlagActionGraph().getMenuElement(0).getHeight(),
               true,
               tempL
            )
         );
      } else if (iActiveGraphID == 11) {
         ArrayList<Graph_Vertical_Data> tempL = new ArrayList<>();

         for (int ixxx = 1; ixxx < CFG.game.getCivsSize(); ixxx++) {
            if (CFG.FOG_OF_WAR < 2 || CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetCivilization(ixxx)) {
               tempL.add(new Graph_Vertical_Data(ixxx));
            }
         }

         updateGraph(
            new Graph_Vertical(
               Graph_Vertical_Data_Type.CONSTRUCTED_BUILDINGS,
               CFG.langManager.get("Civilizations"),
               CFG.langManager.get("ConstructedBuildings"),
               CFG.menuManager.getInGame_FlagActionGraph().getMenuElement(0).getPosX(),
               CFG.menuManager.getInGame_FlagActionGraph().getMenuElement(0).getPosY(),
               CFG.menuManager.getInGame_FlagActionGraph().getMenuElement(0).getWidth(),
               CFG.menuManager.getInGame_FlagActionGraph().getMenuElement(0).getHeight(),
               true,
               tempL
            )
         );
      } else if (iActiveGraphID == 13) {
         ArrayList<Graph_Vertical_Data> tempL = new ArrayList<>();

         for (int ixxxx = 1; ixxxx < CFG.game.getCivsSize(); ixxxx++) {
            if (CFG.FOG_OF_WAR < 2 || CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetCivilization(ixxxx)) {
               tempL.add(new Graph_Vertical_Data(ixxxx));
            }
         }

         updateGraph(
            new Graph_Vertical(
               Graph_Vertical_Data_Type.ECONOMY_OF_CIVILIZATIONS,
               CFG.langManager.get("Civilizations"),
               CFG.langManager.get("Economy"),
               CFG.menuManager.getInGame_FlagActionGraph().getMenuElement(0).getPosX(),
               CFG.menuManager.getInGame_FlagActionGraph().getMenuElement(0).getPosY(),
               CFG.menuManager.getInGame_FlagActionGraph().getMenuElement(0).getWidth(),
               CFG.menuManager.getInGame_FlagActionGraph().getMenuElement(0).getHeight(),
               true,
               tempL
            )
         );
      } else if (iActiveGraphID == 2) {
         ArrayList<Graph_Vertical_Data> tempL = new ArrayList<>();

         for (int ixxxxx = 0; ixxxxx < CFG.game.getCivsSize(); ixxxxx++) {
            if (CFG.FOG_OF_WAR < 2 || CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetCivilization(ixxxxx)) {
               tempL.add(new Graph_Vertical_Data(ixxxxx));
            }
         }

         updateGraph(
            new Graph_Vertical(
               Graph_Vertical_Data_Type.POPULATION_OF_CIVILIZATION_BY_NATIONALITIES,
               CFG.langManager.get("EthnicGroups"),
               CFG.langManager.get("EthnicGroups"),
               CFG.menuManager.getInGame_FlagActionGraph().getMenuElement(0).getPosX(),
               CFG.menuManager.getInGame_FlagActionGraph().getMenuElement(0).getPosY(),
               CFG.menuManager.getInGame_FlagActionGraph().getMenuElement(0).getWidth(),
               CFG.menuManager.getInGame_FlagActionGraph().getMenuElement(0).getHeight(),
               true,
               tempL
            )
         );
      } else if (iActiveGraphID == 3) {
         ArrayList<Graph_Vertical_Data> tempL = new ArrayList<>();

         for (int ixxxxxx = 1; ixxxxxx < CFG.game.getCivsSize(); ixxxxxx++) {
            if (CFG.FOG_OF_WAR < 2 || CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetCivilization(ixxxxxx)) {
               tempL.add(new Graph_Vertical_Data(ixxxxxx));
            }
         }

         updateGraph(
            new Graph_Vertical(
               Graph_Vertical_Data_Type.TECHNOLOGY_LEVELS,
               CFG.langManager.get("Technology"),
               CFG.langManager.get("Technology"),
               CFG.menuManager.getInGame_FlagActionGraph().getMenuElement(0).getPosX(),
               CFG.menuManager.getInGame_FlagActionGraph().getMenuElement(0).getPosY(),
               CFG.menuManager.getInGame_FlagActionGraph().getMenuElement(0).getWidth(),
               CFG.menuManager.getInGame_FlagActionGraph().getMenuElement(0).getHeight(),
               true,
               tempL
            )
         );
      } else if (iActiveGraphID == 100) {
         ArrayList<Integer> tempCivs = new ArrayList<>();
         tempCivs.add(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
         updateGraph(
            new Graph(
               CFG.langManager.get("Turn"),
               CFG.langManager.get("Income"),
               CFG.menuManager.getInGame_FlagActionGraph().getMenuElement(0).getPosX(),
               CFG.menuManager.getInGame_FlagActionGraph().getMenuElement(0).getPosY(),
               CFG.menuManager.getInGame_FlagActionGraph().getMenuElement(0).getWidth(),
               CFG.menuManager.getInGame_FlagActionGraph().getMenuElement(0).getHeight(),
               true,
               tempCivs,
               1
            ) {
               @Override
               public void loadData(int i) {
                  try {
                     if (Menu_InGame_GraphManager.iActiveGraphID == 100) {
                        int nStartTurnID = -1;
                        int jSize = CFG.timelapseManager.timelapseStatsGD.lPlayers_Income.size();

                        for (int j = 0; j < jSize; j++) {
                           if (CFG.timelapseManager.timelapseStatsGD.lPlayers_Income.get(j).size() > CFG.PLAYER_TURNID) {
                              nStartTurnID = j;
                              break;
                           }
                        }

                        ArrayList<Integer> tempPoints = new ArrayList<>();
                        if (nStartTurnID >= 0) {
                           int jSize2 = CFG.timelapseManager.timelapseStatsGD.lPlayers_Income.size();

                           for (int jx = nStartTurnID; jx < jSize2; jx++) {
                              tempPoints.add(CFG.timelapseManager.timelapseStatsGD.lPlayers_Income.get(jx).get(CFG.PLAYER_TURNID));
                           }
                        }

                        if (tempPoints.size() > 0) {
                           this.lData.set(i, new GraphData(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), tempPoints, nStartTurnID));
                           this.lData.get(i).setDrawData(true);
                           this.updateMoveable();
                           this.buildGraph();
                        }
                     }
                  } catch (IndexOutOfBoundsException var7) {
                     if (CFG.LOGS) {
                        CFG.exceptionStack(var7);
                     }
                  }
               }
            }
         );
      } else if (iActiveGraphID == 111) {
         ArrayList<Integer> tempCivs = new ArrayList<>();
         tempCivs.add(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
         updateGraph(
            new Graph(
               CFG.langManager.get("Turn"),
               CFG.langManager.get("Balance"),
               CFG.menuManager.getInGame_FlagActionGraph().getMenuElement(0).getPosX(),
               CFG.menuManager.getInGame_FlagActionGraph().getMenuElement(0).getPosY(),
               CFG.menuManager.getInGame_FlagActionGraph().getMenuElement(0).getWidth(),
               CFG.menuManager.getInGame_FlagActionGraph().getMenuElement(0).getHeight(),
               true,
               tempCivs,
               1
            ) {
               @Override
               public void loadData(int i) {
                  try {
                     if (Menu_InGame_GraphManager.iActiveGraphID == 111) {
                        int nStartTurnID = -1;
                        int jSize = CFG.timelapseManager.timelapseStatsGD.lPlayers_Balance.size();

                        for (int j = 0; j < jSize; j++) {
                           if (CFG.timelapseManager.timelapseStatsGD.lPlayers_Balance.get(j).size() > CFG.PLAYER_TURNID) {
                              nStartTurnID = j;
                              break;
                           }
                        }

                        ArrayList<Integer> tempPoints = new ArrayList<>();
                        if (nStartTurnID >= 0) {
                           int jSize2 = CFG.timelapseManager.timelapseStatsGD.lPlayers_Balance.size();

                           for (int jx = nStartTurnID; jx < jSize2; jx++) {
                              tempPoints.add(CFG.timelapseManager.timelapseStatsGD.lPlayers_Balance.get(jx).get(CFG.PLAYER_TURNID));
                           }
                        }

                        if (tempPoints.size() > 0) {
                           this.lData.set(i, new GraphData(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), tempPoints, nStartTurnID));
                           this.lData.get(i).setDrawData(true);
                           this.updateMoveable();
                           this.buildGraph();
                        }
                     }
                  } catch (IndexOutOfBoundsException var7) {
                     if (CFG.LOGS) {
                        CFG.exceptionStack(var7);
                     }
                  }
               }
            }
         );
      } else if (iActiveGraphID == 102) {
         ArrayList<Integer> tempCivs = new ArrayList<>();
         tempCivs.add(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
         updateGraph(
            new Graph(
               CFG.langManager.get("Turn"),
               CFG.langManager.get("MilitaryUpkeep"),
               CFG.menuManager.getInGame_FlagActionGraph().getMenuElement(0).getPosX(),
               CFG.menuManager.getInGame_FlagActionGraph().getMenuElement(0).getPosY(),
               CFG.menuManager.getInGame_FlagActionGraph().getMenuElement(0).getWidth(),
               CFG.menuManager.getInGame_FlagActionGraph().getMenuElement(0).getHeight(),
               true,
               tempCivs,
               1
            ) {
               @Override
               public void loadData(int i) {
                  try {
                     if (Menu_InGame_GraphManager.iActiveGraphID == 102) {
                        int nStartTurnID = -1;
                        int jSize = CFG.timelapseManager.timelapseStatsGD.lPlayers_MilitarySpendings.size();

                        for (int j = 0; j < jSize; j++) {
                           if (CFG.timelapseManager.timelapseStatsGD.lPlayers_MilitarySpendings.get(j).size() > CFG.PLAYER_TURNID) {
                              nStartTurnID = j;
                              break;
                           }
                        }

                        ArrayList<Integer> tempPoints = new ArrayList<>();
                        if (nStartTurnID >= 0) {
                           int jSize2 = CFG.timelapseManager.timelapseStatsGD.lPlayers_MilitarySpendings.size();

                           for (int jx = nStartTurnID; jx < jSize2; jx++) {
                              tempPoints.add(CFG.timelapseManager.timelapseStatsGD.lPlayers_MilitarySpendings.get(jx).get(CFG.PLAYER_TURNID));
                           }
                        }

                        if (tempPoints.size() > 0) {
                           this.lData.set(i, new GraphData(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), tempPoints, nStartTurnID));
                           this.lData.get(i).setDrawData(true);
                           this.updateMoveable();
                           this.buildGraph();
                        }
                     }
                  } catch (IndexOutOfBoundsException var7) {
                  }
               }
            }
         );
      } else if (iActiveGraphID == 106) {
         ArrayList<Integer> tempCivs = new ArrayList<>();
         tempCivs.add(0);
         tempCivs.add(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
         updateGraph(
            new Graph(
               CFG.langManager.get("Turn"),
               CFG.langManager.get("WorldsPopulation"),
               CFG.menuManager.getInGame_FlagActionGraph().getMenuElement(0).getPosX(),
               CFG.menuManager.getInGame_FlagActionGraph().getMenuElement(0).getPosY(),
               CFG.menuManager.getInGame_FlagActionGraph().getMenuElement(0).getWidth(),
               CFG.menuManager.getInGame_FlagActionGraph().getMenuElement(0).getHeight(),
               true,
               tempCivs,
               2
            ) {
               @Override
               public void loadData(int i) {
                  try {
                     if (Menu_InGame_GraphManager.iActiveGraphID == 106) {
                        if (i == 0) {
                           ArrayList<Integer> tempPoints = new ArrayList<>();
                           int jSize = CFG.timelapseManager.timelapseStatsGD.lPopulation.size();

                           for (int j = 0; j < jSize; j++) {
                              int tempTurnPop = 0;

                              for (int k = 0; k < CFG.timelapseManager.timelapseStatsGD.lPopulation.get(j).size(); k++) {
                                 tempTurnPop += CFG.timelapseManager.timelapseStatsGD.lPopulation.get(j).get(k);
                              }

                              tempPoints.add(tempTurnPop);
                           }

                           if (tempPoints.size() > 0) {
                              this.lData.set(i, new GraphData(0, tempPoints, 0));
                              this.lData.get(i).setDrawData(true);
                              this.updateMoveable();
                              this.buildGraph();
                           }
                        } else {
                           int nStartTurnID = -1;
                           int jSize = CFG.timelapseManager.timelapseStatsGD.lPopulation.size();

                           for (int j = 0; j < jSize; j++) {
                              if (CFG.timelapseManager.timelapseStatsGD.lPopulation.get(j).size() > this.lData.get(i).getCivID()) {
                                 nStartTurnID = j;
                                 break;
                              }
                           }

                           ArrayList<Integer> tempPoints = new ArrayList<>();
                           if (nStartTurnID >= 0) {
                              int jSize2 = CFG.timelapseManager.timelapseStatsGD.lPopulation.size();

                              for (int jx = nStartTurnID; jx < jSize2; jx++) {
                                 tempPoints.add(CFG.timelapseManager.timelapseStatsGD.lPopulation.get(jx).get(this.lData.get(i).getCivID()));
                              }
                           }

                           if (tempPoints.size() > 0) {
                              this.lData.set(i, new GraphData(this.lData.get(i).getCivID(), tempPoints, nStartTurnID));
                              this.lData.get(i).setDrawData(true);
                              this.updateMoveable();
                              this.buildGraph();
                           }
                        }
                     }
                  } catch (IndexOutOfBoundsException var7) {
                  }
               }
            }
         );
      }
   }

   public static final void updateGraph(MenuElement tElem) {
      CFG.menuManager.getInGame_FlagActionGraph().setMenuElement(0, tElem);
      CFG.menuManager.getInGame_FlagActionGraph().updateMenuElements_IsInView();
   }
}
