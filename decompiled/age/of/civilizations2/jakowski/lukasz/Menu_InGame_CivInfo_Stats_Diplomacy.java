package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;
import java.util.List;

public class Menu_InGame_CivInfo_Stats_Diplomacy extends SliderMenu {
   public Menu_InGame_CivInfo_Stats_Diplomacy() {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      int tPosY = 0;
      ArrayList<Integer> tData = new ArrayList<>();
      if (CFG.getActiveCivInfo() > 0) {
         if (CFG.getActiveCivInfo() != CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) {
            menuElements.add(
               new Button_Diplomacy_Opinion(
                  CFG.getActiveCivInfo(),
                  (int)CFG.game.getCivRelation_OfCivB(CFG.getActiveCivInfo(), CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()),
                  0,
                  0,
                  tPosY,
                  CFG.CIV_INFO_MENU_WIDTH - 2 - (Button_Diplomacy.iDiploWidth + CFG.PADDING * 2),
                  CFG.TEXT_HEIGHT + CFG.PADDING * 4,
                  true
               ) {
                  @Override
                  public void actionElement(int iID) {
                     int nWarID;
                     if ((int)CFG.game.getCivRelation_OfCivB(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), CFG.getActiveCivInfo()) == -100
                        && (nWarID = CFG.game.getWarID(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), CFG.getActiveCivInfo())) >= 0
                        && nWarID < CFG.game.getWarsSize()) {
                        Menu_InGame_WarDetails.WAR_ID = nWarID;
                        CFG.menuManager.rebuildInGame_WarDetails();
                     }
                  }
               }
            );
            menuElements.add(
               new Button_Diplomacy_Wiki(
                  CFG.getActiveCivInfo(),
                  CFG.CIV_INFO_MENU_WIDTH - 2 - (Button_Diplomacy.iDiploWidth + CFG.PADDING * 2),
                  tPosY,
                  Button_Diplomacy.iDiploWidth + CFG.PADDING * 2,
                  CFG.TEXT_HEIGHT + CFG.PADDING * 4,
                  true
               ) {}
            );
            tPosY += menuElements.get(menuElements.size() - 1).getHeight();
         } else {
            menuElements.add(
               new Button_Diplomacy_Wiki_Civ(
                  CFG.getActiveCivInfo(),
                  (int)CFG.game.getCivRelation_OfCivB(CFG.getActiveCivInfo(), CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()),
                  0,
                  0,
                  tPosY,
                  CFG.CIV_INFO_MENU_WIDTH - 2,
                  CFG.TEXT_HEIGHT + CFG.PADDING * 4,
                  true
               ) {}
            );
            tPosY += menuElements.get(menuElements.size() - 1).getHeight();
         }

         if (CFG.getActiveCivInfo() == CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID() && !CFG.SPECTATOR_MODE) {
            for (int i3 = 0;
               i3 < CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCivilization_Diplomacy_GameData().getImproveRelationsSize();
               i3++
            ) {
               int var10009 = CFG.CIV_INFO_MENU_WIDTH - 2;
               menuElements.add(
                  new Button_Diplomacy_ImprovingRelations(
                     CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCivilization_Diplomacy_GameData().getImproveRelation(i3).iWithCivID,
                     CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCivilization_Diplomacy_GameData().getImproveRelation(i3).iNumOfTurns,
                     0,
                     0,
                     tPosY,
                     var10009,
                     CFG.TEXT_HEIGHT + CFG.PADDING * 4,
                     true
                  ) {
                     @Override
                     public void actionElement(int iID) {
                        if (!CFG.SPECTATOR_MODE) {
                           CFG.game
                              .getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
                              .getCivilization_Diplomacy_GameData()
                              .messageBox
                              .addMessage(
                                 new Message_Relations_Increase_Ended(
                                    CFG.game
                                       .getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
                                       .getCivilization_Diplomacy_GameData()
                                       .getImproveRelation(iID - 1)
                                       .iWithCivID
                                 )
                              );
                           CFG.game
                              .getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
                              .getCivilization_Diplomacy_GameData()
                              .removeImproveRelations(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), iID - 1);
                           CFG.updateActiveCivInfo_InGame();
                           CFG.menuManager.updateInGame_TOP_All(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
                           CFG.toast.setInView(CFG.langManager.get("Removed"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE);
                           CFG.menuManager.rebuildInGame_Messages();
                        }
                     }
                  }
               );
               tPosY += menuElements.get(menuElements.size() - 1).getHeight();
            }
         }

         if (!CFG.game.getCiv(CFG.getActiveCivInfo()).getControlledByPlayer()
            && CFG.game.getCiv(CFG.getActiveCivInfo()).getPuppetOfCivID() != CFG.getActiveCivInfo()) {
            menuElements.add(
               new Button_Diplomacy_LiberityDesire(
                  CFG.game.getCiv(CFG.getActiveCivInfo()).getPuppetOfCivID(),
                  (int)CFG.game.getCiv(CFG.getActiveCivInfo()).getVassalLiberityDesire(),
                  0,
                  0,
                  tPosY,
                  CFG.CIV_INFO_MENU_WIDTH - 2,
                  CFG.TEXT_HEIGHT + CFG.PADDING * 4,
                  true
               ) {
                  @Override
                  public void actionElement(int iID) {
                     if (CFG.game.getCiv(this.getCurrent()).getPuppetOfCivID() == CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) {
                        if (CFG.menuManager.getVisibleInGame_Tribute()) {
                           CFG.menuManager.setVisibleInGame_Tribute(false);
                        } else {
                           CFG.menuManager.rebuildInGame_Tribute();
                        }
                     } else {
                        CFG.toast
                           .setInView(
                              CFG.langManager.get("Lord") + ": " + CFG.game.getCiv(CFG.game.getCiv(this.getCurrent()).getPuppetOfCivID()).getCivName(),
                              CFG.COLOR_TEXT_NUM_OF_PROVINCES
                           );
                     }
                  }
               }
            );
            tPosY += menuElements.get(menuElements.size() - 1).getHeight();
         }

         tData.clear();

         for (int i3 = 1; i3 < CFG.game.getCivsSize(); i3++) {
            if (i3 != CFG.getActiveCivInfo()
               && (int)CFG.game.getCivRelation_OfCivB(CFG.getActiveCivInfo(), i3) == -100
               && CFG.game.getCiv(i3).getNumOfProvinces() > 0) {
               if (CFG.FOG_OF_WAR >= 2 && !CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetCivilization(i3)) {
                  tData.add(-1);
               } else {
                  tData.add(i3);
               }
            }
         }

         if (tData.size() > 0) {
            menuElements.add(
               new Button_Diplomacy_InGameWar(Images.diplo_war, tData, 0, tPosY, CFG.CIV_INFO_MENU_WIDTH - 2) {
                  @Override
                  public void actionElement(int iID) {
                     ArrayList<Integer> tWars = new ArrayList<>();

                     for (int i = 1; i < CFG.game.getCivsSize(); i++) {
                        if (i != CFG.getActiveCivInfo()
                           && (int)CFG.game.getCivRelation_OfCivB(CFG.getActiveCivInfo(), i) == -100
                           && CFG.game.getCiv(i).getNumOfProvinces() > 0) {
                           int tWarID = CFG.game.getWarID(i, CFG.getActiveCivInfo());
                           boolean added = false;

                           for (int j = 0; j < tWars.size(); j++) {
                              if (tWars.get(j) == tWarID) {
                                 added = true;
                                 break;
                              }
                           }

                           if (!added) {
                              tWars.add(tWarID);
                           }
                        }
                     }

                     if (tWars.size() > 0) {
                        if (CFG.menuManager.getVisibleInGame_WarDetails()) {
                           int nWarID = 0;

                           for (int ix = 0; ix < tWars.size(); ix++) {
                              if (Menu_InGame_WarDetails.WAR_ID == tWars.get(ix)) {
                                 nWarID = ix + 1;
                                 break;
                              }
                           }

                           if (nWarID >= tWars.size()) {
                              CFG.menuManager.setVisibleInGame_WarDetails(false);
                           } else {
                              Menu_InGame_WarDetails.WAR_ID = tWars.get(nWarID);
                              CFG.menuManager.rebuildInGame_WarDetails();
                           }
                        } else {
                           Menu_InGame_WarDetails.WAR_ID = tWars.get(0);
                           CFG.menuManager.rebuildInGame_WarDetails();
                        }
                     } else {
                        CFG.menuManager.setVisibleInGame_WarDetails(false);
                     }
                  }
               }
            );
            tPosY += menuElements.get(menuElements.size() - 1).getHeight();
         }

         if (CFG.game.getCiv(CFG.getActiveCivInfo()).getAllianceID() > 0) {
            tData.clear();

            for (int var31 = 0; var31 < CFG.game.getAlliance(CFG.game.getCiv(CFG.getActiveCivInfo()).getAllianceID()).getCivilizationsSize(); var31++) {
               if (CFG.game.getAlliance(CFG.game.getCiv(CFG.getActiveCivInfo()).getAllianceID()).getCivilization(var31) != CFG.getActiveCivInfo()) {
                  if (CFG.FOG_OF_WAR >= 2
                     && !CFG.game
                        .getPlayer(CFG.PLAYER_TURNID)
                        .getMetCivilization(CFG.game.getAlliance(CFG.game.getCiv(CFG.getActiveCivInfo()).getAllianceID()).getCivilization(var31))) {
                     tData.add(-1);
                  } else {
                     tData.add(CFG.game.getAlliance(CFG.game.getCiv(CFG.getActiveCivInfo()).getAllianceID()).getCivilization(var31));
                  }
               }
            }

            if (tData.size() > 0) {
               menuElements.add(
                  new Button_Diplomacy_InGame(Images.diplo_alliance, tData, 0, tPosY, CFG.CIV_INFO_MENU_WIDTH - 2) {
                     @Override
                     public void actionElement(int iID) {
                        try {
                           if (CFG.menuManager.getVisible_InGame_Alliance()
                              && Menu_InGame_Alliance.ALLIANCE_ID == CFG.game.getCiv(CFG.getActiveCivInfo()).getAllianceID()) {
                              CFG.menuManager.setVisible_InGame_Alliance(false);
                           } else {
                              CFG.menuManager.rebuildInGame_Alliance(CFG.game.getCiv(CFG.getActiveCivInfo()).getAllianceID());
                           }
                        } catch (IndexOutOfBoundsException var3) {
                        }
                     }
                  }
               );
               tPosY += menuElements.get(menuElements.size() - 1).getHeight();
            }
         }

         tData.clear();

         for (int var32 = 1; var32 < CFG.game.getCivsSize(); var32++) {
            if (var32 != CFG.getActiveCivInfo()
               && CFG.game.getCiv(var32).getNumOfProvinces() > 0
               && CFG.game.getCiv(var32).getPuppetOfCivID() == CFG.getActiveCivInfo()) {
               if (CFG.FOG_OF_WAR >= 2 && !CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetCivilization(var32)) {
                  tData.add(-1);
               } else {
                  tData.add(var32);
               }
            }
         }

         if (tData.size() > 0) {
            menuElements.add(new Button_Diplomacy_InGame(Images.diplo_vassal, tData, 0, tPosY, CFG.CIV_INFO_MENU_WIDTH - 2));
            tPosY += menuElements.get(menuElements.size() - 1).getHeight();
         }

         tData.clear();

         for (int var33 = 1; var33 < CFG.game.getCivsSize(); var33++) {
            if (var33 != CFG.getActiveCivInfo() && CFG.game.getCiv(var33).getNumOfProvinces() > 0 && CFG.game.getCivTruce(var33, CFG.getActiveCivInfo()) > 0) {
               if (CFG.FOG_OF_WAR >= 2 && !CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetCivilization(var33)) {
                  tData.add(-1);
               } else {
                  tData.add(var33);
               }
            }
         }

         if (tData.size() > 0) {
            menuElements.add(new Button_Diplomacy_InGame(Images.diplo_truce, tData, 0, tPosY, CFG.CIV_INFO_MENU_WIDTH - 2));
            tPosY += menuElements.get(menuElements.size() - 1).getHeight();
         }

         ArrayList<Integer> tempOpinions = new ArrayList<>();
         ArrayList tempSortedIDs = new ArrayList();

         for (int i2 = 1; i2 < CFG.game.getCivsSize(); i2++) {
            if (CFG.game.getCiv(i2).getNumOfProvinces() > 0 && CFG.getActiveCivInfo() != i2) {
               tempOpinions.add(i2);
            }
         }

         while (tempOpinions.size() > 0) {
            int highestID = 0;

            for (int i = 1; i < tempOpinions.size(); i++) {
               if (CFG.game.getCivRelation_OfCivB(CFG.getActiveCivInfo(), tempOpinions.get(highestID))
                  > CFG.game.getCivRelation_OfCivB(CFG.getActiveCivInfo(), tempOpinions.get(i))) {
                  highestID = i;
               }
            }

            tempSortedIDs.add(tempOpinions.get(highestID));
            tempOpinions.remove(highestID);
         }

         tData.clear();
         int var27 = tempSortedIDs.size() - 1;

         for (int j = 0; var27 >= 0 && j < 6 && !(CFG.game.getCivRelation_OfCivB(CFG.getActiveCivInfo(), (Integer)tempSortedIDs.get(var27)) < 25.0F); j++) {
            if (CFG.FOG_OF_WAR >= 2 && !CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetCivilization((Integer)tempSortedIDs.get(var27))) {
               tData.add(-1);
            } else {
               tData.add((Integer)tempSortedIDs.get(var27));
            }

            var27--;
         }

         if (tData.size() > 0) {
            menuElements.add(new Button_Diplomacy_InGame(Images.diplo_heart, tData, 0, tPosY, CFG.CIV_INFO_MENU_WIDTH - 2));
            tPosY += menuElements.get(menuElements.size() - 1).getHeight();
         }

         tData.clear();

         for (int var28 = 0; var28 < CFG.game.getCiv(CFG.getActiveCivInfo()).getHatedCivsSize(); var28++) {
            tData.add(CFG.game.getCiv(CFG.getActiveCivInfo()).getHatedCiv(var28).iCivID);
         }

         for (int var29 = 0;
            var29 < 10 && var29 < tempSortedIDs.size() && !(CFG.game.getCivRelation_OfCivB(CFG.getActiveCivInfo(), (Integer)tempSortedIDs.get(var29)) > -25.0F);
            var29++
         ) {
            boolean addCiv = true;

            for (int z = tData.size() - 1; z >= 0; z--) {
               if (tData.get(z).equals(tempSortedIDs.get(var29))) {
                  addCiv = false;
                  break;
               }
            }

            if (addCiv) {
               if (CFG.FOG_OF_WAR >= 2 && !CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetCivilization((Integer)tempSortedIDs.get(var29))) {
                  tData.add(-1);
               } else {
                  tData.add((Integer)tempSortedIDs.get(var29));
               }
            }
         }

         if (tData.size() > 0) {
            menuElements.add(new Button_Diplomacy_InGame(Images.diplo_rivals, tData, 0, tPosY, CFG.CIV_INFO_MENU_WIDTH - 2));
            tPosY += menuElements.get(menuElements.size() - 1).getHeight();
         }

         try {
            tData.clear();
            if (CFG.game.getCiv(CFG.getActiveCivInfo()).getNumOfProvinces() > 0) {
               for (int j = 0; j < CFG.game.getCiv(CFG.getActiveCivInfo()).getWarReparationsPaysSize(); j++) {
                  if (CFG.FOG_OF_WAR >= 2
                     && !CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetCivilization(CFG.game.getCiv(CFG.getActiveCivInfo()).getWarReparationsPays(j).iFromCivID)) {
                     tData.add(-1);
                  } else {
                     tData.add(CFG.game.getCiv(CFG.getActiveCivInfo()).getWarReparationsPays(j).iFromCivID);
                  }
               }
            }

            if (tData.size() > 0) {
               menuElements.add(new Button_Diplomacy_InGame(Images.top_gold2, tData, 0, tPosY, CFG.CIV_INFO_MENU_WIDTH - 2));
               tPosY += menuElements.get(menuElements.size() - 1).getHeight();
            }
         } catch (IndexOutOfBoundsException var11) {
         }

         tData.clear();

         for (int ix = 1; ix < CFG.game.getCivsSize(); ix++) {
            if (ix != CFG.getActiveCivInfo() && CFG.game.getCiv(ix).getNumOfProvinces() > 0 && CFG.game.getDefensivePact(ix, CFG.getActiveCivInfo()) > 0) {
               if (CFG.FOG_OF_WAR >= 2 && !CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetCivilization(ix)) {
                  tData.add(-1);
               } else {
                  tData.add(ix);
               }
            }
         }

         if (tData.size() > 0) {
            menuElements.add(new Button_Diplomacy_InGame(Images.diplo_defensive_pact, tData, 0, tPosY, CFG.CIV_INFO_MENU_WIDTH - 2));
            tPosY += menuElements.get(menuElements.size() - 1).getHeight();
         }

         tData.clear();

         for (int var15 = 1; var15 < CFG.game.getCivsSize(); var15++) {
            if (var15 != CFG.getActiveCivInfo()
               && CFG.game.getCiv(var15).getNumOfProvinces() > 0
               && CFG.game.getCivNonAggressionPact(var15, CFG.getActiveCivInfo()) > 0) {
               if (CFG.FOG_OF_WAR >= 2 && !CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetCivilization(var15)) {
                  tData.add(-1);
               } else {
                  tData.add(var15);
               }
            }
         }

         if (tData.size() > 0) {
            menuElements.add(new Button_Diplomacy_InGame(Images.diplo_non_aggression, tData, 0, tPosY, CFG.CIV_INFO_MENU_WIDTH - 2));
            tPosY += menuElements.get(menuElements.size() - 1).getHeight();
         }

         tData.clear();

         for (int var16 = 1; var16 < CFG.game.getCivsSize(); var16++) {
            if (var16 != CFG.getActiveCivInfo() && CFG.game.getCiv(var16).getNumOfProvinces() > 0 && CFG.game.getGuarantee(var16, CFG.getActiveCivInfo()) > 0) {
               if (CFG.FOG_OF_WAR >= 2 && !CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetCivilization(var16)) {
                  tData.add(-1);
               } else {
                  tData.add(var16);
               }
            }
         }

         if (tData.size() > 0) {
            menuElements.add(new Button_Diplomacy_InGame(Images.diplo_guarantee_has, tData, 0, tPosY, CFG.CIV_INFO_MENU_WIDTH - 2));
            tPosY += menuElements.get(menuElements.size() - 1).getHeight();
         }

         tData.clear();

         for (int var17 = 1; var17 < CFG.game.getCivsSize(); var17++) {
            if (var17 != CFG.getActiveCivInfo() && CFG.game.getCiv(var17).getNumOfProvinces() > 0 && CFG.game.getGuarantee(CFG.getActiveCivInfo(), var17) > 0) {
               if (CFG.FOG_OF_WAR >= 2 && !CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetCivilization(var17)) {
                  tData.add(-1);
               } else {
                  tData.add(var17);
               }
            }
         }

         if (tData.size() > 0) {
            menuElements.add(new Button_Diplomacy_InGame(Images.diplo_guarantee_gives, tData, 0, tPosY, CFG.CIV_INFO_MENU_WIDTH - 2));
            tPosY += menuElements.get(menuElements.size() - 1).getHeight();
         }

         tData.clear();

         for (int var18 = 1; var18 < CFG.game.getCivsSize(); var18++) {
            if (var18 != CFG.getActiveCivInfo()
               && CFG.game.getCiv(var18).getNumOfProvinces() > 0
               && CFG.game.getMilitaryAccess(var18, CFG.getActiveCivInfo()) > 0) {
               if (CFG.FOG_OF_WAR >= 2 && !CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetCivilization(var18)) {
                  tData.add(-1);
               } else {
                  tData.add(var18);
               }
            }
         }

         if (tData.size() > 0) {
            menuElements.add(new Button_Diplomacy_InGame(Images.diplo_access_has, tData, 0, tPosY, CFG.CIV_INFO_MENU_WIDTH - 2));
            tPosY += menuElements.get(menuElements.size() - 1).getHeight();
         }

         tData.clear();

         for (int var19 = 1; var19 < CFG.game.getCivsSize(); var19++) {
            if (var19 != CFG.getActiveCivInfo()
               && CFG.game.getCiv(var19).getNumOfProvinces() > 0
               && CFG.game.getMilitaryAccess(CFG.getActiveCivInfo(), var19) > 0) {
               if (CFG.FOG_OF_WAR >= 2 && !CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetCivilization(var19)) {
                  tData.add(-1);
               } else {
                  tData.add(var19);
               }
            }
         }

         if (tData.size() > 0) {
            menuElements.add(new Button_Diplomacy_InGame(Images.diplo_access_gives, tData, 0, tPosY, CFG.CIV_INFO_MENU_WIDTH - 2));
            tPosY += menuElements.get(menuElements.size() - 1).getHeight();
         }

         tData.clear();

         for (int var20 = 0; var20 < CFG.game.getCiv(CFG.getActiveCivInfo()).getCivilization_Diplomacy_GameData().getEmbassyClosedSize(); var20++) {
            if (var20 != CFG.getActiveCivInfo()
               && CFG.game
                     .getCiv(CFG.game.getCiv(CFG.getActiveCivInfo()).getCivilization_Diplomacy_GameData().getEmbassyClosed(var20).iCivID)
                     .getNumOfProvinces()
                  > 0
               && CFG.game.getCiv(CFG.getActiveCivInfo()).getCivilization_Diplomacy_GameData().getEmbassyClosed(var20).iNumOfTurns > 0) {
               if (CFG.FOG_OF_WAR >= 2
                  && !CFG.game
                     .getPlayer(CFG.PLAYER_TURNID)
                     .getMetCivilization(CFG.game.getCiv(CFG.getActiveCivInfo()).getCivilization_Diplomacy_GameData().getEmbassyClosed(var20).iCivID)) {
                  tData.add(-1);
               } else {
                  tData.add(CFG.game.getCiv(CFG.getActiveCivInfo()).getCivilization_Diplomacy_GameData().getEmbassyClosed(var20).iCivID);
               }
            }
         }

         if (tData.size() > 0) {
            menuElements.add(new Button_Diplomacy_InGame(Images.diplo_relations_dec, tData, 0, tPosY, CFG.CIV_INFO_MENU_WIDTH - 2));
            tPosY += menuElements.get(menuElements.size() - 1).getHeight();
         }

         if (CFG.getActiveCivInfo() != CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID() || CFG.SPECTATOR_MODE) {
            tData.clear();

            for (int var21 = 0; var21 < CFG.game.getCiv(CFG.getActiveCivInfo()).getCivilization_Diplomacy_GameData().getImproveRelationsSize(); var21++) {
               if (CFG.FOG_OF_WAR >= 2
                  && !CFG.game
                     .getPlayer(CFG.PLAYER_TURNID)
                     .getMetCivilization(CFG.game.getCiv(CFG.getActiveCivInfo()).getCivilization_Diplomacy_GameData().getImproveRelation(var21).iWithCivID)) {
                  tData.add(-1);
               } else {
                  tData.add(CFG.game.getCiv(CFG.getActiveCivInfo()).getCivilization_Diplomacy_GameData().getImproveRelation(var21).iWithCivID);
               }
            }

            if (tData.size() > 0) {
               menuElements.add(new Button_Diplomacy_InGame(Images.diplo_relations_inc, tData, 0, tPosY, CFG.CIV_INFO_MENU_WIDTH - 2));
               tPosY += menuElements.get(menuElements.size() - 1).getHeight();
            }
         }

         tData.clear();

         for (int k = 1; k < CFG.game.getCivsSize(); k++) {
            if (CFG.game.getCiv(k).getNumOfProvinces() > 0) {
               for (int var22 = 0; var22 < CFG.game.getCiv(k).getCivilization_Diplomacy_GameData().getImproveRelationsSize(); var22++) {
                  if (CFG.getActiveCivInfo() == CFG.game.getCiv(k).getCivilization_Diplomacy_GameData().getImproveRelation(var22).iWithCivID) {
                     if (CFG.FOG_OF_WAR >= 2 && !CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetCivilization(k)) {
                        tData.add(-1);
                        break;
                     }

                     tData.add(k);
                     break;
                  }
               }
            }
         }

         if (tData.size() > 0) {
            menuElements.add(new Button_Diplomacy_InGame(Images.diplo_relations, tData, 0, tPosY, CFG.CIV_INFO_MENU_WIDTH - 2));
            tPosY += menuElements.get(menuElements.size() - 1).getHeight();
         }

         if (CFG.game.getCiv(CFG.getActiveCivInfo()).getIsPartOfHolyRomanEmpire()) {
            tData.clear();

            for (int var23 = 0; var23 < CFG.game.getCivsSize(); var23++) {
               if (CFG.game.getCiv(var23).getNumOfProvinces() > 0 && var23 != CFG.getActiveCivInfo() && CFG.game.getCiv(var23).getIsPartOfHolyRomanEmpire()) {
                  if (CFG.FOG_OF_WAR >= 2 && !CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetCivilization(var23)) {
                     tData.add(-1);
                  } else {
                     tData.add(var23);
                  }
               }
            }

            if (tData.size() > 0) {
               menuElements.add(new Button_Diplomacy_InGame(Images.hre_icon, tData, 0, tPosY, CFG.CIV_INFO_MENU_WIDTH - 2) {
                  @Override
                  public void actionElement(int iID) {
                     if (CFG.menuManager.getVisibleInGame_HRE()) {
                        CFG.menuManager.setVisible_InGame_HRE(false);
                     } else {
                        CFG.menuManager.rebuildInGame_HRE();
                     }
                  }
               });
               tPosY += menuElements.get(menuElements.size() - 1).getHeight();
            }
         }

         tData.clear();

         for (int var24 = CFG.game.getCiv(CFG.getActiveCivInfo()).civGameData.lGifts_Received.size() - 1; var24 >= 0; var24--) {
            if (var24 != CFG.getActiveCivInfo()) {
               if (CFG.FOG_OF_WAR >= 2
                  && !CFG.game
                     .getPlayer(CFG.PLAYER_TURNID)
                     .getMetCivilization(CFG.game.getCiv(CFG.getActiveCivInfo()).civGameData.lGifts_Received.get(var24).iFromCivID)) {
                  tData.add(-1);
               } else {
                  tData.add(CFG.game.getCiv(CFG.getActiveCivInfo()).civGameData.lGifts_Received.get(var24).iFromCivID);
               }
            }
         }

         if (tData.size() > 0) {
            menuElements.add(new Button_Diplomacy_InGame(Images.diplo_gift, tData, 0, tPosY, CFG.CIV_INFO_MENU_WIDTH - 2));
            tPosY += menuElements.get(menuElements.size() - 1).getHeight();
         }

         tData.clear();

         for (int var25 = CFG.game.getCiv(CFG.getActiveCivInfo()).civGameData.lLoansTaken.size() - 1; var25 >= 0; var25--) {
            tData.add(CFG.getActiveCivInfo());
         }

         if (tData.size() > 0) {
            menuElements.add(new Button_Diplomacy_InGame(Images.diplo_loan, tData, 0, tPosY, CFG.CIV_INFO_MENU_WIDTH - 2));
            tPosY += menuElements.get(menuElements.size() - 1).getHeight();
         }
      }

      menuElements.add(new Button_Transparent(0, 0, CFG.CIV_INFO_MENU_WIDTH, (CFG.CIV_FLAG_HEIGHT + CFG.PADDING * 2) * 3, true));
      this.initMenu(
         new SliderMenuTitle(null, CFG.TEXT_HEIGHT + CFG.PADDING * 2, false, false) {
            @Override
            public void draw(SpriteBatch oSB, int iTranslateX, int nPosX, int nPosY, int nWidth, boolean sliderMenuIsActive) {
               ImageManager.getImage(Images.new_game_top_edge_line)
                  .draw2(
                     oSB,
                     Menu_InGame_CivInfo_Stats_Diplomacy.this.getPosX() + iTranslateX,
                     Menu_InGame_CivInfo_Stats_Diplomacy.this.getPosY() - ImageManager.getImage(Images.new_game_top_edge_line).getHeight() - this.getHeight(),
                     Menu_InGame_CivInfo_Stats_Diplomacy.this.getWidth(),
                     this.getHeight(),
                     true,
                     false
                  );
               CFG.drawRect_InfoBox_Left_Title(
                  oSB,
                  Menu_InGame_CivInfo_Stats_Diplomacy.this.getPosX() + iTranslateX,
                  Menu_InGame_CivInfo_Stats_Diplomacy.this.getPosY() - this.getHeight(),
                  Menu_InGame_CivInfo_Stats_Diplomacy.this.getWidth() - 2,
                  this.getHeight()
               );
               CFG.fontMain.getData().setScale(0.7F);
               CFG.drawTextWithShadow(
                  oSB,
                  this.getText(),
                  nPosX + nWidth / 2 - (int)(this.getTextWidth() * 0.7F) / 2 + iTranslateX,
                  nPosY - this.getHeight() + this.getHeight() / 2 - (int)(this.getTextHeight() * 0.7F) / 2,
                  CFG.COLOR_TEXT_CIV_INFO_TITLE
               );
               CFG.fontMain.getData().setScale(1.0F);
            }
         },
         0,
         ImageManager.getImage(Images.new_game_top).getHeight()
            + CFG.PADDING * 4
            + (int)(CFG.TEXT_HEIGHT * 0.6F)
            + ImageManager.getImage(Images.top_flag_frame).getHeight()
            + CFG.PADDING * 4,
         CFG.CIV_INFO_MENU_WIDTH,
         (CFG.CIV_FLAG_HEIGHT + CFG.PADDING * 2) * (CFG.isDesktop() ? 4 : 3),
         menuElements,
         false,
         false
      );
      this.updateLanguage();

      for (int ixx = 0; ixx < this.getMenuElementsSize(); ixx++) {
         this.getMenuElement(ixx).setMax(ixx % 2);
      }
   }

   @Override
   public void updateLanguage() {
      this.getTitle().setText(CFG.langManager.get("Diplomacy"));
   }

   @Override
   public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      if (Menu_InGame_CivInfo.lTime + 175L >= System.currentTimeMillis()) {
         int var5;
         int var6;
         iTranslateX = Menu_InGame_CivInfo.hideAnimation
            ? (var5 = iTranslateX - (int)(this.getWidth() * ((float)(System.currentTimeMillis() - Menu_InGame_CivInfo.lTime) / 175.0F)))
            : (var6 = iTranslateX + -this.getWidth() + (int)(this.getWidth() * ((float)(System.currentTimeMillis() - Menu_InGame_CivInfo.lTime) / 175.0F)));
         CFG.setRender_3(true);
      } else if (Menu_InGame_CivInfo.hideAnimation) {
         super.setVisible(false);
         return;
      }

      ImageManager.getImage(Images.new_game_top_edge_line)
         .draw2(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.new_game_top_edge_line).getHeight() + iTranslateY,
            this.getWidth(),
            this.getHeight() + 2,
            true,
            false
         );
      this.beginClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
      oSB.setColor(Color.WHITE);
      this.drawMenu(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
      this.endClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
   }

   @Override
   public void drawScrollPos(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      if (sliderMenuIsActive) {
         super.drawScrollPos(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
      }
   }

   @Override
   public void onHovered() {
      CFG.menuManager.setOrderOfMenu_InGame_CivInfo();
   }

   @Override
   public void actionElement(int iID) {
      this.getMenuElement(iID).actionElement(iID);
   }

   @Override
   public void setVisible(boolean visible) {
      if (visible) {
         super.setVisible(visible);
      }
   }
}
