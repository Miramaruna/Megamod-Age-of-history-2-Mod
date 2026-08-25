package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

public class Menu_InGame_CivInfo_Stats_Decisions extends SliderMenu {
   public Menu_InGame_CivInfo_Stats_Decisions() {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      int tempW = CFG.CIV_INFO_MENU_WIDTH;
      int tempElemH = CFG.isAndroid() ? Math.max(CFG.TEXT_HEIGHT + CFG.PADDING * 4, (int)(CFG.BUTTON_HEIGHT * 0.6F)) : CFG.TEXT_HEIGHT + CFG.PADDING * 4;
      int tY = CFG.PADDING;

      for (int i = 0; i < CFG.game.getCiv(CFG.getActiveCivInfo()).getTagsCanFormSize(); i++) {
         menuElements.add(
            new Button_Diplomacy_FormCivilization(
               CFG.game.getCiv(CFG.getActiveCivInfo()).getTagsCanForm(i),
               0,
               tY,
               tempW - 2,
               true,
               CFG.canFormACiv(CFG.getActiveCivInfo(), CFG.game.getCiv(CFG.getActiveCivInfo()).getTagsCanForm(i), true)
            ) {
               @Override
               public void actionElement(int iID) {
                  CFG.game.getPlayer(CFG.PLAYER_TURNID).iACTIVE_VIEW_MODE = CFG.viewsManager.getActiveViewID();
                  CFG.game.getPlayer(CFG.PLAYER_TURNID).iBefore_ActiveProvince = CFG.game.getActiveProvinceID();
                  CFG.viewsManager.disableAllViews();
                  CFG.game.resetChooseProvinceData();
                  CFG.game.resetRegroupArmyData();
                  CFG.game.setActiveProvinceID(-1);
                  CFG.game.resetChooseProvinceData_Immediately();
                  CFG.gameAction.hideAllProvinceActionViews();
                  CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID = CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID();
                  CFG.loadFormableCiv_GameData(CFG.game.getCiv(CFG.getActiveCivInfo()).getTagsCanForm(iID));
                  CFG.menuManager.setViewID(Menu.eINGAME_FORMABLE_CIV_PROVINCES);
                  CFG.map.getMapBG().updateWorldMap_Shaders();
               }
            }
         );
         tY += menuElements.get(menuElements.size() - 1).getHeight();
      }

      if (CFG.ideologiesManager.getIdeology(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getIdeologyID()).CAN_BECOME_CIVILIZED >= 0) {
         menuElements.add(
            new Button_Diplomacy_Civilize(
               CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(),
               0,
               tY,
               tempW - 2,
               true,
               CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getTechnologyLevel()
                  >= CFG.ideologiesManager.getIdeology(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getIdeologyID()).CIVILIZE_TECH_LEVEL
            ) {
               @Override
               public void actionElement(int iID) {
                  CFG.menuManager.rebuildInGame_Civilize(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
                  CFG.viewsManager.setActiveViewID(ViewsManager.VIEW_IDEOLOGIES_MODE);
               }
            }
         );
         tY += menuElements.get(menuElements.size() - 1).getHeight();
      }

      menuElements.add(
         new Button_Diplomacy_Action_Tech(Images.technology, CFG.langManager.get("Technology"), 0, 0, tY, CFG.CIV_INFO_MENU_WIDTH - 2, tempElemH, true) {
            @Override
            public void actionElement(int iID) {
               if (CFG.menuManager.getVisibleInGame_Technology()) {
                  CFG.menuManager.setVisibleInGame_Technology(false);
               } else {
                  CFG.menuManager.rebuildInGame_Technology(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
               }
            }

            @Override
            public void buildElementHover() {
               try {
                  ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
                  ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
                  int pointsLeft = CFG.game
                     .getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
                     .civGameData
                     .skills
                     .getPointsLeft(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
                  nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()));
                  nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("TechnologyPoints") + ": ", CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                  nData.add(
                     new MenuElement_Hover_v2_Element_Type_Text(
                        "" + pointsLeft, pointsLeft > 0 ? CFG.COLOR_TEXT_MODIFIER_POSITIVE : CFG.COLOR_TEXT_MODIFIER_NEUTRAL
                     )
                  );
                  nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.technology, CFG.PADDING, 0));
                  nElements.add(new MenuElement_Hover_v2_Element2(nData));
                  nData.clear();
                  this.menuElementHover = new MenuElement_Hover_v2(nElements);
               } catch (IndexOutOfBoundsException var4) {
                  this.menuElementHover = null;
               }
            }
         }
      );
      tY += menuElements.get(menuElements.size() - 1).getHeight();
      if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID() > 0) {
         menuElements.add(
            new Button_Diplomacy_Action(Images.diplo_alliance, CFG.langManager.get("LeaveAlliance"), 0, 0, tY, CFG.CIV_INFO_MENU_WIDTH - 2, tempElemH, true) {
               @Override
               public void actionElement(int iID) {
                  CFG.menuManager.rebuildInGame_LeaveAllinace(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
               }
            }
         );
         tY += menuElements.get(menuElements.size() - 1).getHeight();
      }

      String str = CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).isAssimilateProvincesPlayer <= 0 ? "StartAssimilation" : "assimilatNo";
      menuElements.add(
         new Button_Diplomacy_Action(
            Images.diplo_popstability,
            CFG.langManager.get(str),
            0,
            0,
            tY,
            CFG.CIV_INFO_MENU_WIDTH - 2,
            tempElemH,
            CFG.game
                  .getCivTruce(
                     CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getPuppetOfCivID()
                  )
               == 0
         ) {
            @Override
            public void actionElement(int iID) {
               CFG.menuManager.rebuildInGame_AutoAssimilation(CFG.getActiveCivInfo());
            }

            @Override
            public void buildElementHover() {
            }
         }
      );
      tY += menuElements.get(menuElements.size() - 1).getHeight();
      String strAssistant = AI_Assistant.ENABLED ? "AIAssistant_On" : "AIAssistant_Off";
      menuElements.add(
         new Button_Diplomacy_Action(
            Images.pix255_255_255,
            CFG.langManager.get(strAssistant),
            0,
            0,
            tY,
            CFG.CIV_INFO_MENU_WIDTH - 2,
            tempElemH,
            true
         ) {
            @Override
            public void actionElement(int iID) {
               AI_Assistant.ENABLED = !AI_Assistant.ENABLED;
               CFG.toast.setInView(CFG.langManager.get(AI_Assistant.ENABLED ? "AIAssistant_On" : "AIAssistant_Off"), CFG.COLOR_TEXT_MODIFIER_POSITIVE);
               CFG.toast.setTimeInView(3000);
               CFG.menuManager.rebuildInGame_Civ_Info_Decisions();
            }

            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("AIAssistant_Info")));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
         }
      );
      tY += menuElements.get(menuElements.size() - 1).getHeight();
      if (AI_Assistant.ENABLED) {
         menuElements.add(
            new Button_Diplomacy_Action(
               Images.pix255_255_255,
               CFG.langManager.get("AI_Settings_Title"),
               0,
               0,
               tY,
               CFG.CIV_INFO_MENU_WIDTH - 2,
               tempElemH,
               true
            ) {
               @Override
               public void actionElement(int iID) {
                  CFG.menuManager.rebuildInGame_AIAssistantSettings();
               }

               @Override
               public void buildElementHover() {
                  ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
                  ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
                  nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("AI_Settings_Info")));
                  nElements.add(new MenuElement_Hover_v2_Element2(nData));
                  this.menuElementHover = new MenuElement_Hover_v2(nElements);
               }
            }
         );
         tY += menuElements.get(menuElements.size() - 1).getHeight();
      }
      menuElements.add(
         new Button_Diplomacy_Action(
            Images.diplo_festival,
            CFG.langManager.get("HaveNationalCelebration"),
            0,
            0,
            tY,
            CFG.CIV_INFO_MENU_WIDTH - 2,
            tempElemH,
            CFG.game
                  .getCivTruce(
                     CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getPuppetOfCivID()
                  )
               == 0
         ) {
            @Override
            public void actionElement(int iID) {
               CFG.menuManager.rebuildInGame_FestivalInAllProvince(CFG.getActiveCivInfo());
            }

            @Override
            public void buildElementHover() {
            }
         }
      );
      tY += menuElements.get(menuElements.size() - 1).getHeight();
      menuElements.add(
         new Button_Diplomacy_Action(Images.diplo_vassal, CFG.langManager.get("Vassals"), 0, 0, tY, CFG.CIV_INFO_MENU_WIDTH - 2, tempElemH, true) {
            @Override
            public void actionElement(int iID) {
               if (CFG.menuManager.getVisibleInGame_Tribute()) {
                  CFG.menuManager.setVisibleInGame_Tribute(false);
               } else {
                  CFG.menuManager.rebuildInGame_Tribute();
               }
            }

            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Vassals"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
               nData.add(
                  new MenuElement_Hover_v2_Element_Type_Ideology_Vassal(
                     CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getIdeologyID(), CFG.PADDING, 0
                  )
               );
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();

               for (int i = CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID() - 1; i > 0; i--) {
                  if (CFG.game.getCiv(i).getNumOfProvinces() > 0 && CFG.game.getCiv(i).getPuppetOfCivID() == CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) {
                     nData.add(new MenuElement_Hover_v2_Element_Type_Flag(i));
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + CFG.game.getCiv(i).getCivName() + ": "));
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           "" + (int)CFG.game_NextTurnUpdate.getIncome_Vassals(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), i),
                           CFG.COLOR_TEXT_MODIFIER_POSITIVE
                        )
                     );
                     nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_gold, CFG.PADDING, 0));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                  }
               }

               for (int var4 = CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID() + 1; var4 < CFG.game.getCivsSize(); var4++) {
                  if (CFG.game.getCiv(var4).getNumOfProvinces() > 0
                     && CFG.game.getCiv(var4).getPuppetOfCivID() == CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) {
                     nData.add(new MenuElement_Hover_v2_Element_Type_Flag(var4));
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + CFG.game.getCiv(var4).getCivName() + ": "));
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           "" + (int)CFG.game_NextTurnUpdate.getIncome_Vassals(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), var4),
                           CFG.COLOR_TEXT_MODIFIER_POSITIVE
                        )
                     );
                     nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_gold, CFG.PADDING, 0));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                  }
               }

               if (nElements.size() <= 1) {
                  nElements.clear();
                  nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()));
                  nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("NoVassals"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                  nData.add(
                     new MenuElement_Hover_v2_Element_Type_Ideology_Vassal(
                        CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getIdeologyID(), CFG.PADDING, 0
                     )
                  );
                  nElements.add(new MenuElement_Hover_v2_Element2(nData));
                  nData.clear();
               }

               this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
         }
      );
      tY += menuElements.get(menuElements.size() - 1).getHeight();
      if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getPuppetOfCivID() != CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) {
         menuElements.add(
            new Button_Diplomacy_Action(
               Images.diplo_vassal,
               CFG.langManager.get("DeclarationOfIndependence"),
               0,
               0,
               tY,
               CFG.CIV_INFO_MENU_WIDTH - 2,
               tempElemH,
               CFG.game
                     .getCivTruce(
                        CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getPuppetOfCivID()
                     )
                  == 0
            ) {
               @Override
               public void actionElement(int iID) {
                  CFG.menuManager.rebuildInGame_DeclarationOfIndependence(CFG.getActiveCivInfo());
               }

               @Override
               public void buildElementHover() {
                  try {
                     ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
                     ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
                     if (CFG.game
                           .getCivTruce(
                              CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(),
                              CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getPuppetOfCivID()
                           )
                        > 0) {
                        nData.add(
                           new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("WeHaveATruceUntil") + ": ", CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE)
                        );
                        nData.add(
                           new MenuElement_Hover_v2_Element_Type_Text(
                              Game_Calendar.getDate_ByTurnID(
                                 Game_Calendar.TURN_ID
                                    + CFG.game
                                       .getCivTruce(
                                          CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(),
                                          CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getPuppetOfCivID()
                                       )
                              )
                           )
                        );
                        nData.add(
                           new MenuElement_Hover_v2_Element_Type_Text(
                              " ["
                                 + CFG.langManager
                                    .get(
                                       "TurnsX",
                                       CFG.game
                                          .getCivTruce(
                                             CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(),
                                             CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getPuppetOfCivID()
                                          )
                                    )
                                 + "]",
                              CFG.COLOR_TEXT_OPTIONS_NS_HOVER
                           )
                        );
                        nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.diplo_truce, CFG.PADDING, 0));
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                        this.menuElementHover = new MenuElement_Hover_v2(nElements);
                     } else {
                        this.menuElementHover = null;
                     }
                  } catch (IndexOutOfBoundsException var3) {
                     this.menuElementHover = null;
                  }
               }
            }
         );
         tY += menuElements.get(menuElements.size() - 1).getHeight();
      }

      menuElements.add(
         new Button_Diplomacy_Action(Images.top_diplomacy_points, CFG.langManager.get("ReleaseAVassal"), 0, 0, tY, CFG.CIV_INFO_MENU_WIDTH - 2, tempElemH, true) {
            @Override
            public void actionElement(int iID) {
               CFG.game.getPlayer(CFG.PLAYER_TURNID).iACTIVE_VIEW_MODE = CFG.viewsManager.getActiveViewID();
               CFG.viewsManager.disableAllViews();
               CFG.game.resetChooseProvinceData();
               CFG.game.resetRegroupArmyData();
               CFG.game.setActiveProvinceID(-1);
               CFG.game.resetChooseProvinceData_Immediately();
               CFG.gameAction.hideAllProvinceActionViews();
               CFG.game.getSelectedProvinces().clearSelectedProvinces();
               CFG.createVassal_Data = new CreateVassal_Data();
               CFG.selectMode = true;
               CFG.brushTool = false;
               CFG.VIEW_SHOW_VALUES = false;
               CFG.menuManager.setViewID(Menu.eINGAME_CREATE_VASSAL);
               Game_Render_Province.updateDrawProvinces();
               CFG.map.getMapBG().updateWorldMap_Shaders();
            }
         }
      );
      int var12;
      menuElements.add(
         new Button_Diplomacy_Action(
            Images.editor_city,
            CFG.langManager.get("MoveCapital"),
            0,
            0,
            var12 = tY + menuElements.get(menuElements.size() - 1).getHeight(),
            CFG.CIV_INFO_MENU_WIDTH - 2,
            tempElemH,
            CFG.gameAction.moveCapital_CanMove(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
         ) {
            @Override
            public void buildElementHover() {
               if (CFG.game.getActiveProvinceID() >= 0 && !CFG.game.getProvince(CFG.game.getActiveProvinceID()).getSeaProvince()) {
                  ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
                  ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
                  if (!CFG.gameAction.moveCapital_CanMove(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())) {
                     nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()));
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("TheCapitalCityHasRecentlyBeenMoved"), CFG.COLOR_TEXT_MODIFIER_NEGATIVE2)
                     );
                     nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.city, CFG.PADDING, 0));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           Game_Calendar.getDate_ByTurnID(
                              Game_Calendar.TURN_ID
                                 + Math.abs(
                                    Game_Calendar.TURN_ID
                                       - (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCapitalMoved_LastTurnID() + 50)
                                 )
                           )
                        )
                     );
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           " ["
                              + CFG.langManager
                                 .get(
                                    "TurnsX",
                                    Math.abs(
                                       Game_Calendar.TURN_ID
                                          - (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCapitalMoved_LastTurnID() + 50)
                                    )
                                 )
                              + "]",
                           CFG.COLOR_TEXT_MODIFIER_NEUTRAL
                        )
                     );
                     nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.time, CFG.PADDING, 0));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                  } else if (CFG.game.getProvince(CFG.game.getActiveProvinceID()).getTrueOwnerOfProvince() == CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) {
                     if (CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID() == CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) {
                        if (CFG.game.getProvince(CFG.game.getActiveProvinceID()).getName().length() > 0) {
                           nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()));
                           nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("MoveCapitalTo") + ": "));
                           nData.add(
                              new MenuElement_Hover_v2_Element_Type_Text(
                                 CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCitiesSize() > 0
                                    ? CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCity(0).getCityName()
                                    : CFG.game.getProvince(CFG.game.getActiveProvinceID()).getName(),
                                 CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                              )
                           );
                           nElements.add(new MenuElement_Hover_v2_Element2(nData));
                           nData.clear();
                        }
                     } else {
                        nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("SelectProvince"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                     }

                     if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCapitalProvinceID() >= 0
                        && CFG.game.getProvince(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCapitalProvinceID()).getCivID()
                           != CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()
                        && CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).isAtWar()) {
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("TheCapitalCityIsLost"), CFG.COLOR_TEXT_MODIFIER_NEGATIVE2));
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                     } else {
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Cost") + ": "));
                        nData.add(
                           new MenuElement_Hover_v2_Element_Type_Text(
                              "" + CFG.gameAction.moveCapital_Cost(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()), CFG.COLOR_INGAME_GOLD
                           )
                        );
                        nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_gold, CFG.PADDING, 0));
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                     }
                  } else {
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("OccupiedProvince"), CFG.COLOR_TEXT_MODIFIER_NEGATIVE2));
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Flag(
                           CFG.game.getProvince(CFG.game.getActiveProvinceID()).getTrueOwnerOfProvince(), CFG.PADDING, 0
                        )
                     );
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                     if (CFG.game.getProvince(CFG.game.getActiveProvinceID()).getName().length() > 0) {
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getName()));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID(), CFG.PADDING, 0));
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                     }
                  }

                  this.menuElementHover = new MenuElement_Hover_v2(nElements);
               } else {
                  ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
                  ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
                  nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()));
                  nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("SelectProvince"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                  nElements.add(new MenuElement_Hover_v2_Element2(nData));
                  nData.clear();
                  if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCapitalProvinceID() >= 0
                     && CFG.game.getProvince(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCapitalProvinceID()).getCivID()
                        != CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()
                     && CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).isAtWar()) {
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("TheCapitalCityIsLost"), CFG.COLOR_TEXT_MODIFIER_NEGATIVE2));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                  }

                  this.menuElementHover = new MenuElement_Hover_v2(nElements);
               }
            }

            @Override
            public boolean getClickable() {
               return super.getClickable()
                  && CFG.game.getActiveProvinceID() >= 0
                  && CFG.game.getProvince(CFG.game.getActiveProvinceID()).getTrueOwnerOfProvince() == CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()
                  && CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID() == CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()
                  && CFG.game.getActiveProvinceID() != CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCapitalProvinceID()
                  && (
                     CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCapitalProvinceID() < 0
                        || CFG.game.getProvince(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCapitalProvinceID()).getCivID()
                           == CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()
                        || !CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).isAtWar()
                  );
            }

            @Override
            public void actionElement(int iID) {
               CFG.menuManager.rebuildInGame_MoveCapital(CFG.game.getActiveProvinceID());
            }
         }
      );
      menuElements.add(
         new Button_Diplomacy_Action_Goverment(
            CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getIdeologyID(),
            CFG.langManager.get("ChangeTypeOfGovernment"),
            0,
            0,
            tY = var12 + menuElements.get(menuElements.size() - 1).getHeight(),
            CFG.CIV_INFO_MENU_WIDTH - 2,
            tempElemH,
            true
         ) {
            @Override
            public void actionElement(int iID) {
               CFG.menuManager.rebuildInGame_ChangeGovernment();
            }

            @Override
            public void buildElementHover() {
               try {
                  ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
                  ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
                  int pointsLeft = CFG.game
                     .getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
                     .civGameData
                     .skills
                     .getPointsLeft(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
                  nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()));
                  nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("ChangeTypeOfGovernment"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                  nData.add(
                     new MenuElement_Hover_v2_Element_Type_Ideology(
                        CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getIdeologyID(), CFG.PADDING, 0
                     )
                  );
                  nElements.add(new MenuElement_Hover_v2_Element2(nData));
                  nData.clear();
                  nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Cost") + ": "));
                  nData.add(
                     new MenuElement_Hover_v2_Element_Type_Text(
                        CFG.getNumberWithSpaces("" + Ideologies_Manager.getChangeGovernmentCost(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())),
                        CFG.COLOR_INGAME_GOLD
                     )
                  );
                  nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_gold, CFG.PADDING, 0));
                  nElements.add(new MenuElement_Hover_v2_Element2(nData));
                  nData.clear();
                  nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("MovementPoints") + ": "));
                  nData.add(new MenuElement_Hover_v2_Element_Type_Text("2.2", CFG.COLOR_INGAME_MOVEMENT));
                  nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_movement_points, CFG.PADDING, 0));
                  nElements.add(new MenuElement_Hover_v2_Element2(nData));
                  nData.clear();
                  this.menuElementHover = new MenuElement_Hover_v2(nElements);
               } catch (IndexOutOfBoundsException var4) {
                  this.menuElementHover = null;
               }
            }
         }
      );
      int var14;
      menuElements.add(
         new Button_Diplomacy_Action(
            Images.diplo_loan,
            CFG.langManager.get("TakeLoan"),
            0,
            0,
            var14 = tY + menuElements.get(menuElements.size() - 1).getHeight(),
            CFG.CIV_INFO_MENU_WIDTH - 2,
            tempElemH,
            true
         ) {
            @Override
            public void actionElement(int iID) {
               CFG.menuManager.rebuildInGame_TakeLoan(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
            }

            @Override
            public void buildElementHover() {
               try {
                  ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
                  ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
                  nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), 0, CFG.PADDING));
                  nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("TakeLoan"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                  nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.diplo_loan, CFG.PADDING, 0));
                  nElements.add(new MenuElement_Hover_v2_Element2(nData));
                  nData.clear();
                  nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("MovementPoints") + ": "));
                  nData.add(new MenuElement_Hover_v2_Element_Type_Text("-0.6", CFG.COLOR_TEXT_MODIFIER_NEGATIVE2));
                  nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_movement_points, CFG.PADDING, 0));
                  nElements.add(new MenuElement_Hover_v2_Element2(nData));
                  nData.clear();
                  this.menuElementHover = new MenuElement_Hover_v2(nElements);
               } catch (IndexOutOfBoundsException var3) {
                  this.menuElementHover = null;
               }
            }
         }
      );
      menuElements.add(
         new Button_Diplomacy_Action(
            Images.diplo_loan2,
            CFG.langManager.get("RepayLoans"),
            0,
            0,
            tY = var14 + menuElements.get(menuElements.size() - 1).getHeight(),
            CFG.CIV_INFO_MENU_WIDTH - 2,
            tempElemH,
            true
         ) {
            @Override
            public void actionElement(int iID) {
               CFG.menuManager.rebuildInGame_Loans(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
            }

            @Override
            public void buildElementHover() {
               try {
                  ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
                  ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
                  int pointsLeft = CFG.game
                     .getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
                     .civGameData
                     .skills
                     .getPointsLeft(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
                  nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()));
                  nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Loans") + ": ", CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                  nData.add(
                     new MenuElement_Hover_v2_Element_Type_Text(
                        "" + CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getLoansSize(), CFG.COLOR_TEXT_MODIFIER_NEUTRAL
                     )
                  );
                  nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.diplo_loan2, CFG.PADDING, 0));
                  nElements.add(new MenuElement_Hover_v2_Element2(nData));
                  nData.clear();
                  this.menuElementHover = new MenuElement_Hover_v2(nElements);
               } catch (IndexOutOfBoundsException var4) {
                  this.menuElementHover = null;
               }
            }
         }
      );
      if (CFG.settingsManager.EXPERIMENTAL_GAMEPLAY) {
         int var16;
         menuElements.add(
            new Button_Diplomacy_Action(
               Images.pix255_255_255,
               CFG.langManager.get("GovernmentPolicy"),
               0,
               0,
               var16 = tY + menuElements.get(menuElements.size() - 1).getHeight(),
               CFG.CIV_INFO_MENU_WIDTH - 2,
               (int)(tempElemH * 1.2),
               true
            ) {
               @Override
               public void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                  CFG.fontMain.getData().setScale(0.8F);
                  CFG.drawTextWithShadow(
                     oSB,
                     this.sText,
                     this.getPosX() + (int)((this.getWidth() - this.getTextWidth() * 0.8F) / 2.0F) + iTranslateX,
                     this.getPosY() + (int)((this.getHeight() - this.iTextHeight * 0.8F) / 2.0F) + iTranslateY,
                     this.getColor(isActive)
                  );
               }

               @Override
               public void actionElement(int iID) {
                  CFG.menuManager.setVisible_InGame_Politics(!CFG.menuManager.getVisible_InGame_Politics());
               }

               @Override
               public void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                  oSB.setColor(new Color(CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.r, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.g, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.b, 0.075F));
                  ImageManager.getImage(Images.pix255_255_255)
                     .draw(
                        oSB,
                        this.getPosX() + iTranslateX,
                        this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY,
                        this.getWidth(),
                        this.getHeight()
                     );
                  oSB.setColor(new Color(0.627451F, 0.09803922F, 0.078431375F, 0.225F));
                  ImageManager.getImage(Images.pix255_255_255)
                     .draw(
                        oSB,
                        this.getPosX() + iTranslateX,
                        this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY,
                        this.getWidth(),
                        CFG.TEXT_HEIGHT + CFG.PADDING * 4
                     );
                  oSB.setColor(new Color(0.627451F, 0.09803922F, 0.078431375F, !this.getIsHovered() && !isActive ? 0.125F : 0.155F));
                  ImageManager.getImage(Images.gradient)
                     .draw(
                        oSB,
                        this.getPosX() + iTranslateX,
                        this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY,
                        this.getWidth(),
                        CFG.TEXT_HEIGHT + CFG.PADDING,
                        false,
                        true
                     );
                  oSB.setColor(new Color(0.627451F, 0.09803922F, 0.078431375F, 0.125F));
                  oSB.setColor(CFG.COLOR_NEW_GAME_EDGE_LINE);
                  ImageManager.getImage(Images.pix255_255_255)
                     .draw(
                        oSB,
                        this.getPosX() + iTranslateX,
                        this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY,
                        this.getWidth(),
                        1
                     );
                  oSB.setColor(new Color(0.627451F, 0.09803922F, 0.078431375F, 0.625F));
                  ImageManager.getImage(Images.gradient)
                     .draw(
                        oSB,
                        this.getPosX() + iTranslateX,
                        this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY,
                        this.getWidth(),
                        CFG.PADDING,
                        false,
                        true
                     );
                  oSB.setColor(CFG.COLOR_NEW_GAME_EDGE_LINE);
                  ImageManager.getImage(Images.pix255_255_255)
                     .draw(
                        oSB,
                        this.getPosX() + iTranslateX,
                        this.getPosY() + CFG.TEXT_HEIGHT + CFG.PADDING * 4 - 1 - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY,
                        this.getWidth(),
                        1
                     );
                  oSB.setColor(new Color(0.627451F, 0.09803922F, 0.078431375F, 0.525F));
                  ImageManager.getImage(Images.line_32_off1)
                     .draw(
                        oSB,
                        this.getPosX() + iTranslateX,
                        this.getPosY() + CFG.TEXT_HEIGHT + CFG.PADDING * 4 - 2 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
                        this.getWidth(),
                        1
                     );
                  oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.8F));
                  ImageManager.getImage(Images.line_32_off1)
                     .draw(
                        oSB,
                        this.getPosX() + iTranslateX,
                        this.getPosY() + CFG.TEXT_HEIGHT + CFG.PADDING * 4 - 1 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
                        this.getWidth(),
                        1
                     );
                  oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.45F));
                  ImageManager.getImage(Images.slider_gradient)
                     .draw(
                        oSB,
                        this.getPosX() + iTranslateX,
                        this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY,
                        this.getWidth() / 2,
                        1
                     );
                  ImageManager.getImage(Images.slider_gradient)
                     .draw(
                        oSB,
                        this.getPosX() + this.getWidth() - this.getWidth() / 2 + iTranslateX,
                        this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY,
                        this.getWidth() / 2,
                        1,
                        true,
                        false
                     );
                  oSB.setColor(CFG.COLOR_NEW_GAME_EDGE_LINE);
                  ImageManager.getImage(Images.pix255_255_255)
                     .draw(
                        oSB,
                        this.getPosX() + iTranslateX,
                        this.getPosY() + this.getHeight() - 1 - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY,
                        this.getWidth(),
                        1
                     );
               }
            }
         );
         int var17;
         menuElements.add(
            new Button_Diplomacy_Action(
               Images.diplo_non_aggression,
               CFG.langManager.get("ChangeGovernmentPolicy"),
               0,
               0,
               var17 = var16 + menuElements.get(menuElements.size() - 1).getHeight(),
               CFG.CIV_INFO_MENU_WIDTH - 2,
               tempElemH,
               true
            ) {
               @Override
               public void actionElement(int iID) {
                  CFG.menuManager.setVisible_InGame_Politics(!CFG.menuManager.getVisible_InGame_Politics());
               }
            }
         );
         menuElements.add(
            new Button_Diplomacy_Action(
               Images.editor_leaders,
               CFG.langManager.get("ConscriptionLaw"),
               0,
               0,
               tY = var17 + menuElements.get(menuElements.size() - 1).getHeight(),
               CFG.CIV_INFO_MENU_WIDTH - 2,
               tempElemH,
               true
            ) {
               @Override
               public void actionElement(int iID) {
                  CFG.menuManager.rebuildInGame_ConscriptionLaw(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
               }

               @Override
               public void buildElementHover() {
                  try {
                     ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
                     ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
                     int pointsLeft = CFG.game
                        .getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
                        .civGameData
                        .skills
                        .getPointsLeft(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
                     nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()));
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           CFG.langManager.get("RecruitablePopulation")
                              + ": "
                              + CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getModifier_RecruitablePopulation() * 100.0F
                              + "%",
                           CFG.COLOR_TEXT_MODIFIER_NEUTRAL
                        )
                     );
                     nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.editor_leaders, CFG.PADDING, 0));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                     this.menuElementHover = new MenuElement_Hover_v2(nElements);
                  } catch (IndexOutOfBoundsException var4) {
                     this.menuElementHover = null;
                  }
               }
            }
         );
      }

      tY += menuElements.get(menuElements.size() - 1).getHeight();
      this.initMenu(
         new SliderMenuTitle(null, CFG.TEXT_HEIGHT * 2 + CFG.PADDING * 2, false, false) {
            @Override
            public void draw(SpriteBatch oSB, int iTranslateX, int nPosX, int nPosY, int nWidth, boolean sliderMenuIsActive) {
               ImageManager.getImage(Images.new_game_top_edge_line)
                  .draw2(
                     oSB,
                     Menu_InGame_CivInfo_Stats_Decisions.this.getPosX() + iTranslateX,
                     Menu_InGame_CivInfo_Stats_Decisions.this.getPosY() - ImageManager.getImage(Images.new_game_top_edge_line).getHeight() - this.getHeight(),
                     Menu_InGame_CivInfo_Stats_Decisions.this.getWidth(),
                     this.getHeight(),
                     true,
                     false
                  );
               CFG.drawRect_InfoBox_Left_Title(
                  oSB,
                  Menu_InGame_CivInfo_Stats_Decisions.this.getPosX() + iTranslateX,
                  Menu_InGame_CivInfo_Stats_Decisions.this.getPosY() - this.getHeight(),
                  Menu_InGame_CivInfo_Stats_Decisions.this.getWidth() - 2,
                  this.getHeight()
               );
               oSB.setColor(new Color(CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.r, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.g, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.b, 0.075F));
               ImageManager.getImage(Images.pix255_255_255)
                  .draw(
                     oSB,
                     Menu_InGame_CivInfo_Stats_Decisions.this.getPosX() + iTranslateX,
                     Menu_InGame_CivInfo_Stats_Decisions.this.getPosY() - this.getHeight() - ImageManager.getImage(Images.pix255_255_255).getHeight(),
                     nWidth,
                     this.getHeight()
                  );
               oSB.setColor(new Color(CFG.COLOR_FORT_2.r, CFG.COLOR_FORT_2.g, CFG.COLOR_FORT_2.b, 0.185F));
               ImageManager.getImage(Images.pix255_255_255)
                  .draw(
                     oSB,
                     nPosX + iTranslateX,
                     Menu_InGame_CivInfo_Stats_Decisions.this.getPosY() - this.getHeight() - ImageManager.getImage(Images.pix255_255_255).getHeight(),
                     nWidth,
                     CFG.TEXT_HEIGHT + CFG.PADDING * 4
                  );
               oSB.setColor(new Color(CFG.COLOR_FORT_2.r, CFG.COLOR_FORT_2.g, CFG.COLOR_FORT_2.b, 0.115F));
               ImageManager.getImage(Images.gradient)
                  .draw(
                     oSB,
                     nPosX + iTranslateX,
                     Menu_InGame_CivInfo_Stats_Decisions.this.getPosY() - this.getHeight() - ImageManager.getImage(Images.gradient).getHeight(),
                     nWidth,
                     CFG.TEXT_HEIGHT + CFG.PADDING * 4,
                     false,
                     true
                  );
               oSB.setColor(new Color(CFG.COLOR_FORT_2.r, CFG.COLOR_FORT_2.g, CFG.COLOR_FORT_2.b, 0.09F));
               oSB.setColor(CFG.COLOR_NEW_GAME_EDGE_LINE);
               ImageManager.getImage(Images.pix255_255_255)
                  .draw(
                     oSB,
                     nPosX + iTranslateX,
                     Menu_InGame_CivInfo_Stats_Decisions.this.getPosY() - this.getHeight() - ImageManager.getImage(Images.pix255_255_255).getHeight(),
                     nWidth,
                     1
                  );
               oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.3F));
               ImageManager.getImage(Images.gradient)
                  .draw(
                     oSB,
                     nPosX + iTranslateX,
                     Menu_InGame_CivInfo_Stats_Decisions.this.getPosY() - this.getHeight() - ImageManager.getImage(Images.gradient).getHeight(),
                     nWidth,
                     CFG.PADDING,
                     false,
                     true
                  );
               oSB.setColor(CFG.COLOR_NEW_GAME_EDGE_LINE);
               ImageManager.getImage(Images.pix255_255_255)
                  .draw(
                     oSB,
                     nPosX + iTranslateX,
                     Menu_InGame_CivInfo_Stats_Decisions.this.getPosY()
                        - this.getHeight()
                        + CFG.TEXT_HEIGHT
                        + CFG.PADDING * 4
                        - 1
                        - ImageManager.getImage(Images.pix255_255_255).getHeight(),
                     nWidth,
                     1
                  );
               oSB.setColor(new Color(CFG.COLOR_FORT_2.r, CFG.COLOR_FORT_2.g, CFG.COLOR_FORT_2.b, 0.4F));
               ImageManager.getImage(Images.line_32_off1)
                  .draw(
                     oSB,
                     nPosX + iTranslateX,
                     Menu_InGame_CivInfo_Stats_Decisions.this.getPosY()
                        - this.getHeight()
                        + CFG.TEXT_HEIGHT
                        + CFG.PADDING * 4
                        - 2
                        - ImageManager.getImage(Images.line_32_off1).getHeight(),
                     nWidth,
                     1
                  );
               oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.8F));
               ImageManager.getImage(Images.line_32_off1)
                  .draw(
                     oSB,
                     nPosX + iTranslateX,
                     Menu_InGame_CivInfo_Stats_Decisions.this.getPosY()
                        - this.getHeight()
                        + CFG.TEXT_HEIGHT
                        + CFG.PADDING * 4
                        - 1
                        - ImageManager.getImage(Images.line_32_off1).getHeight(),
                     nWidth,
                     1
                  );
               oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.33F));
               ImageManager.getImage(Images.slider_gradient)
                  .draw(
                     oSB,
                     nPosX + iTranslateX,
                     Menu_InGame_CivInfo_Stats_Decisions.this.getPosY() - this.getHeight() - ImageManager.getImage(Images.slider_gradient).getHeight(),
                     nWidth / 2,
                     1
                  );
               ImageManager.getImage(Images.slider_gradient)
                  .draw(
                     oSB,
                     nPosX + nWidth - nWidth / 2 + iTranslateX,
                     Menu_InGame_CivInfo_Stats_Decisions.this.getPosY() - this.getHeight() - ImageManager.getImage(Images.slider_gradient).getHeight(),
                     nWidth / 2,
                     1,
                     true,
                     false
                  );
               oSB.setColor(CFG.COLOR_NEW_GAME_EDGE_LINE);
               ImageManager.getImage(Images.pix255_255_255)
                  .draw(
                     oSB,
                     nPosX + iTranslateX,
                     Menu_InGame_CivInfo_Stats_Decisions.this.getPosY()
                        - this.getHeight()
                        + this.getHeight()
                        - 1
                        - ImageManager.getImage(Images.pix255_255_255).getHeight(),
                     nWidth,
                     1
                  );
               CFG.fontMain.getData().setScale(0.8F);
               CFG.drawTextWithShadow(
                  oSB,
                  this.getText(),
                  nPosX + nWidth / 2 - (int)(this.getTextWidth() * 0.8F) / 2 + iTranslateX,
                  nPosY - this.getHeight() + this.getHeight() / 2 - (int)(this.getTextHeight() * 0.8F) / 2,
                  new Color(1.0F, 1.0F, 1.0F, 0.8F)
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
         tempW,
         (CFG.CIV_FLAG_HEIGHT + CFG.PADDING * 2) * 6,
         menuElements,
         false,
         false
      );
      this.updateLanguage();

      for (int var7 = 0; var7 < this.getMenuElementsSize(); var7++) {
         this.getMenuElement(var7).setCurrent(var7 % 2);
      }
   }

   @Override
   public void updateLanguage() {
      this.getTitle().setText(CFG.langManager.get("Decisions"));
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
      oSB.setColor(CFG.COLOR_CREATE_NEW_GAME_BOX_PLAYERS);
      ImageManager.getImage(Images.pix255_255_255)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + this.getHeight() + 1,
            this.getWidth() - 2,
            1
         );
      oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.4F));
      ImageManager.getImage(Images.loading_rect_edge)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.loading_rect_edge).getHeight() + 1 + this.getHeight(),
            this.getWidth() - 2,
            1
         );
      oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.5F));
      ImageManager.getImage(Images.pix255_255_255)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + 2 + this.getHeight(),
            this.getWidth(),
            1
         );
      oSB.setColor(Color.WHITE);
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
      if (!CFG.SPECTATOR_MODE && CFG.gameAction.getActiveTurnState() == Game_Action.TurnStates.INPUT_ORDERS) {
         this.getMenuElement(iID).actionElement(iID);
      }
   }

   @Override
   public void setVisible(boolean visible) {
      if (visible) {
         super.setVisible(visible);
      }
   }

   @Override
   public void actionClose() {
      super.setVisible(false);

      for (int i = 0; i < this.getMenuElementsSize(); i++) {
         this.getMenuElement(i).setVisible(false);
      }
   }

   @Override
   public void setPosY(int iPosY) {
      super.setPosY(iPosY);
      this.setHeight(this.iMaxSliderPositionY);
      if (this.getPosY() + this.getHeight() > CFG.GAME_HEIGHT) {
         this.setHeight(Math.max(CFG.GAME_HEIGHT - this.getPosY(), CFG.BUTTON_HEIGHT / 2));
      }

      int tempElemH = CFG.isAndroid() ? Math.max(CFG.TEXT_HEIGHT + CFG.PADDING * 4, (int)(CFG.BUTTON_HEIGHT * 0.6F)) : CFG.TEXT_HEIGHT + CFG.PADDING * 4;
      this.setHeight(Math.min(this.getHeight(), tempElemH * 8));
      this.updateMenuElements_IsInView();
   }
}
