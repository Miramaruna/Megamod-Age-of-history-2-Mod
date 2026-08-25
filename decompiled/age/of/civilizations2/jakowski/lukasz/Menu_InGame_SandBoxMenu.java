package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

public class Menu_InGame_SandBoxMenu extends SliderMenu {
   public static final int ANIMATION_TIME = 175;
   public static long lTime = 0L;
   private static String str;
   public static boolean hideAnimation = true;

   public Menu_InGame_SandBoxMenu() {
      int tempW = CFG.CIV_INFO_MENU_WIDTH;
      int tPosY = 0;
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      menuElements.add(new Text_BuildTitle(CFG.langManager.get("GeneralSettings"), -1, 0, tPosY, tempW, CFG.TEXT_HEIGHT + CFG.PADDING * 4) {});
      int var6;
      menuElements.add(
         new Button_Build(
            CFG.langManager.get("WorldSettings"),
            Images.editor_map,
            0,
            0,
            0,
            var6 = tPosY + menuElements.get(menuElements.size() - 1).getHeight(),
            tempW,
            true,
            false,
            0,
            0.0F
         ) {
            @Override
            public void actionElement(int iID) {
               CFG.menuManager.rebuildInGame_SettingsWorld(CFG.getActiveCivInfo());
            }

            @Override
            public int getSFX() {
               try {
                  return CFG.game.getProvince(BuildingsManager.iBuildInProvinceID).getTrueOwnerOfProvince() != CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()
                     ? super.getSFX()
                     : SoundsManager.SOUND_RECRUIT;
               } catch (IndexOutOfBoundsException var2) {
                  return super.getSFX();
               }
            }
         }
      );
      menuElements.get(menuElements.size() - 1).setHeight(CFG.BUTTON_HEIGHT * 3 / 5);
      menuElements.add(
         new Button_Build(
            CFG.langManager.get("SettingCiv"),
            Images.editor_civ,
            0,
            0,
            0,
            tPosY = var6 + menuElements.get(menuElements.size() - 1).getHeight(),
            tempW,
            true,
            false,
            0,
            0.0F
         ) {
            @Override
            public void actionElement(int iID) {
               CFG.menuManager.rebuildInGame_SettingsCiv(CFG.getActiveCivInfo());
            }

            @Override
            public int getSFX() {
               try {
                  return CFG.game.getProvince(BuildingsManager.iBuildInProvinceID).getTrueOwnerOfProvince() != CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()
                     ? super.getSFX()
                     : SoundsManager.SOUND_RECRUIT;
               } catch (IndexOutOfBoundsException var2) {
                  return super.getSFX();
               }
            }
         }
      );
      menuElements.get(menuElements.size() - 1).setHeight(CFG.BUTTON_HEIGHT * 3 / 5);
      tPosY += menuElements.get(menuElements.size() - 1).getHeight();
      menuElements.add(new Text_BuildTitle(CFG.langManager.get("Civilization"), -1, 0, tPosY, tempW, CFG.TEXT_HEIGHT + CFG.PADDING * 4) {});
      str = CFG.game.getCiv(CFG.getActiveCivInfo()).disabledAI ? "enabledAI" : "disableAI";
      int var9;
      menuElements.add(
         new Button_Build(
            CFG.langManager.get(str), Images.dice, 0, 0, 0, var9 = tPosY + menuElements.get(menuElements.size() - 1).getHeight(), tempW, true, false, 0, 0.0F
         ) {
            @Override
            public void actionElement(int iID) {
               CFG.menuManager.rebuildInGame_DisabledAI(CFG.getActiveCivInfo());
            }

            @Override
            public int getSFX() {
               try {
                  return CFG.game.getProvince(BuildingsManager.iBuildInProvinceID).getTrueOwnerOfProvince() != CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()
                     ? super.getSFX()
                     : SoundsManager.SOUND_RECRUIT;
               } catch (IndexOutOfBoundsException var2) {
                  return super.getSFX();
               }
            }
         }
      );
      menuElements.get(menuElements.size() - 1).setHeight(CFG.BUTTON_HEIGHT * 3 / 5);
      menuElements.add(
         new Button_Build(
            CFG.langManager.get("Die"),
            Images.skull,
            0,
            0,
            0,
            tPosY = var9 + menuElements.get(menuElements.size() - 1).getHeight(),
            tempW,
            true,
            false,
            0,
            0.0F
         ) {
            @Override
            public void actionElement(int iID) {
               CFG.menuManager.rebuildInGame_Die(CFG.getActiveCivInfo());
            }

            @Override
            public int getSFX() {
               try {
                  return CFG.game.getProvince(BuildingsManager.iBuildInProvinceID).getTrueOwnerOfProvince() != CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()
                     ? super.getSFX()
                     : SoundsManager.SOUND_RECRUIT;
               } catch (IndexOutOfBoundsException var2) {
                  return super.getSFX();
               }
            }
         }
      );
      menuElements.get(menuElements.size() - 1).setHeight(CFG.BUTTON_HEIGHT * 3 / 5);
      int var11;
      menuElements.add(
         new Button_Build(
            CFG.langManager.get("SendGift"),
            Images.diplo_gift,
            0,
            0,
            0,
            var11 = tPosY + menuElements.get(menuElements.size() - 1).getHeight(),
            tempW,
            true,
            false,
            0,
            0.0F
         ) {
            @Override
            public void actionElement(int iID) {
               CFG.menuManager.rebuildInGame_SendGiftSandbox(CFG.getActiveCivInfo());
            }

            @Override
            public int getSFX() {
               try {
                  return CFG.game.getProvince(BuildingsManager.iBuildInProvinceID).getTrueOwnerOfProvince() != CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()
                     ? super.getSFX()
                     : SoundsManager.SOUND_RECRUIT;
               } catch (IndexOutOfBoundsException var2) {
                  return super.getSFX();
               }
            }
         }
      );
      menuElements.get(menuElements.size() - 1).setHeight(CFG.BUTTON_HEIGHT * 3 / 5);
      menuElements.add(
         new Button_Build(
            CFG.langManager.get("causeRevolution"),
            Images.diplo_revolution,
            0,
            0,
            0,
            tPosY = var11 + menuElements.get(menuElements.size() - 1).getHeight(),
            tempW,
            true,
            false,
            0,
            0.0F
         ) {
            @Override
            public void actionElement(int iID) {
               CFG.menuManager.rebuildInGame_RevSandBox(CFG.getActiveCivInfo());
            }

            @Override
            public int getSFX() {
               try {
                  return CFG.game.getProvince(BuildingsManager.iBuildInProvinceID).getTrueOwnerOfProvince() != CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()
                     ? super.getSFX()
                     : SoundsManager.SOUND_RECRUIT;
               } catch (IndexOutOfBoundsException var2) {
                  return super.getSFX();
               }
            }
         }
      );
      menuElements.get(menuElements.size() - 1).setHeight(CFG.BUTTON_HEIGHT * 3 / 5);
      int var13;
      menuElements.add(
         new Button_Build(
            CFG.langManager.get("BuildAll"),
            Images.b_workshop,
            0,
            0,
            0,
            var13 = tPosY + menuElements.get(menuElements.size() - 1).getHeight(),
            tempW,
            true,
            false,
            0,
            0.0F
         ) {
            @Override
            public void actionElement(int iID) {
               CFG.menuManager.rebuildInGame_BuildAllSandBox(CFG.getActiveCivInfo());
            }

            @Override
            public int getSFX() {
               try {
                  return CFG.game.getProvince(BuildingsManager.iBuildInProvinceID).getTrueOwnerOfProvince() != CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()
                     ? super.getSFX()
                     : SoundsManager.SOUND_RECRUIT;
               } catch (IndexOutOfBoundsException var2) {
                  return super.getSFX();
               }
            }
         }
      );
      menuElements.get(menuElements.size() - 1).setHeight(CFG.BUTTON_HEIGHT * 3 / 5);
      menuElements.add(
         new Button_Build(
            CFG.langManager.get("Technology"),
            Images.technology,
            0,
            0,
            0,
            tPosY = var13 + menuElements.get(menuElements.size() - 1).getHeight(),
            tempW,
            true,
            false,
            0,
            0.0F
         ) {
            @Override
            public void actionElement(int iID) {
               if (CFG.menuManager.getVisibleInGame_Technology()) {
                  CFG.menuManager.setVisibleInGame_Technology(false);
               } else {
                  CFG.menuManager.rebuildInGame_Technology(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
               }
            }

            @Override
            public int getSFX() {
               try {
                  return CFG.game.getProvince(BuildingsManager.iBuildInProvinceID).getTrueOwnerOfProvince() != CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()
                     ? super.getSFX()
                     : SoundsManager.SOUND_RECRUIT;
               } catch (IndexOutOfBoundsException var2) {
                  return super.getSFX();
               }
            }
         }
      );
      menuElements.get(menuElements.size() - 1).setHeight(CFG.BUTTON_HEIGHT * 3 / 5);
      if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID() > 0) {
         menuElements.add(
            new Button_Build(
               CFG.langManager.get("LeaveAlliance"),
               Images.diplo_alliance,
               0,
               0,
               0,
               tPosY += menuElements.get(menuElements.size() - 1).getHeight(),
               tempW,
               true,
               false,
               0,
               0.0F
            ) {
               @Override
               public void actionElement(int iID) {
                  CFG.menuManager.rebuildInGame_LeaveAllinace(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
               }

               @Override
               public int getSFX() {
                  try {
                     return CFG.game.getProvince(BuildingsManager.iBuildInProvinceID).getTrueOwnerOfProvince()
                           != CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()
                        ? super.getSFX()
                        : SoundsManager.SOUND_RECRUIT;
                  } catch (IndexOutOfBoundsException var2) {
                     return super.getSFX();
                  }
               }
            }
         );
         menuElements.get(menuElements.size() - 1).setHeight(CFG.BUTTON_HEIGHT * 3 / 5);
      }

      if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getPuppetOfCivID() != CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) {
         menuElements.add(
            new Button_Build(
               CFG.langManager.get("DeclarationOfIndependence"),
               Images.diplo_vassal,
               0,
               0,
               0,
               tPosY += menuElements.get(menuElements.size() - 1).getHeight(),
               tempW,
               true,
               false,
               0,
               0.0F
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

               @Override
               public int getSFX() {
                  try {
                     return CFG.game.getProvince(BuildingsManager.iBuildInProvinceID).getTrueOwnerOfProvince()
                           != CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()
                        ? super.getSFX()
                        : SoundsManager.SOUND_RECRUIT;
                  } catch (IndexOutOfBoundsException var2) {
                     return super.getSFX();
                  }
               }
            }
         );
         menuElements.get(menuElements.size() - 1).setHeight(CFG.BUTTON_HEIGHT * 3 / 5);
      }

      int var15;
      menuElements.add(
         new Button_Build(
            CFG.langManager.get("ReleaseAVassal"),
            Images.top_diplomacy_points,
            0,
            0,
            0,
            var15 = tPosY + menuElements.get(menuElements.size() - 1).getHeight(),
            tempW,
            true,
            false,
            0,
            0.0F
         ) {
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

            @Override
            public int getSFX() {
               try {
                  return CFG.game.getProvince(BuildingsManager.iBuildInProvinceID).getTrueOwnerOfProvince() != CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()
                     ? super.getSFX()
                     : SoundsManager.SOUND_RECRUIT;
               } catch (IndexOutOfBoundsException var2) {
                  return super.getSFX();
               }
            }
         }
      );
      menuElements.get(menuElements.size() - 1).setHeight(CFG.BUTTON_HEIGHT * 3 / 5);
      menuElements.add(
         new Button_Build(
            CFG.langManager.get("MoveCapital"),
            Images.editor_city,
            0,
            0,
            0,
            tPosY = var15 + menuElements.get(menuElements.size() - 1).getHeight(),
            tempW,
            true,
            false,
            0,
            0.0F
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

            @Override
            public int getSFX() {
               try {
                  return CFG.game.getProvince(BuildingsManager.iBuildInProvinceID).getTrueOwnerOfProvince() != CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()
                     ? super.getSFX()
                     : SoundsManager.SOUND_RECRUIT;
               } catch (IndexOutOfBoundsException var2) {
                  return super.getSFX();
               }
            }
         }
      );
      menuElements.get(menuElements.size() - 1).setHeight(CFG.BUTTON_HEIGHT * 3 / 5);
      int var17;
      menuElements.add(
         new Button_Build(
            CFG.langManager.get("ChangeTypeOfGovernment"),
            Images.diplo_union,
            0,
            0,
            0,
            var17 = tPosY + menuElements.get(menuElements.size() - 1).getHeight(),
            tempW,
            true,
            false,
            0,
            0.0F
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

            @Override
            public int getSFX() {
               try {
                  return CFG.game.getProvince(BuildingsManager.iBuildInProvinceID).getTrueOwnerOfProvince() != CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()
                     ? super.getSFX()
                     : SoundsManager.SOUND_RECRUIT;
               } catch (IndexOutOfBoundsException var2) {
                  return super.getSFX();
               }
            }
         }
      );
      menuElements.get(menuElements.size() - 1).setHeight(CFG.BUTTON_HEIGHT * 3 / 5);
      tPosY = var17 + menuElements.get(menuElements.size() - 1).getHeight();
      menuElements.add(
         new Text_BuildTitle(CFG.langManager.get("Army"), -1, 0, tPosY, tempW, CFG.TEXT_HEIGHT + CFG.PADDING * 4) {
            @Override
            public Color getColor(boolean isActive) {
               return isActive
                  ? CFG.COLOR_TEXT_OPTIONS_NS_HOVER
                  : (this.getClickable() ? (this.getIsHovered() ? CFG.COLOR_TEXT_OPTIONS_NS : Color.WHITE) : new Color(0.78F, 0.78F, 0.78F, 0.7F));
            }
         }
      );
      int var19;
      menuElements.add(
         new Button_Build(
            CFG.langManager.get("DeletArmy"),
            Images.diplo_army,
            0,
            0,
            0,
            var19 = tPosY + menuElements.get(menuElements.size() - 1).getHeight(),
            tempW,
            true,
            false,
            0,
            0.0F
         ) {
            @Override
            public void actionElement(int iID) {
               CFG.menuManager.rebuildInGame_DeclareWarSandBox(CFG.getActiveCivInfo());
            }

            @Override
            public int getSFX() {
               try {
                  return CFG.game.getProvince(BuildingsManager.iBuildInProvinceID).getTrueOwnerOfProvince() != CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()
                     ? super.getSFX()
                     : SoundsManager.SOUND_RECRUIT;
               } catch (IndexOutOfBoundsException var2) {
                  return super.getSFX();
               }
            }
         }
      );
      menuElements.get(menuElements.size() - 1).setHeight(CFG.BUTTON_HEIGHT * 3 / 5);
      menuElements.add(
         new Button_Build(
            CFG.langManager.get("EditArmy"),
            Images.b_armoury,
            0,
            0,
            0,
            tPosY = var19 + menuElements.get(menuElements.size() - 1).getHeight(),
            tempW,
            true,
            false,
            0,
            0.0F
         ) {
            @Override
            public void actionElement(int iID) {
               CFG.menuManager.rebuildInGame_EditArmySandBox(CFG.getActiveCivInfo());
            }

            @Override
            public int getSFX() {
               try {
                  return CFG.game.getProvince(BuildingsManager.iBuildInProvinceID).getTrueOwnerOfProvince() != CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()
                     ? super.getSFX()
                     : SoundsManager.SOUND_RECRUIT;
               } catch (IndexOutOfBoundsException var2) {
                  return super.getSFX();
               }
            }
         }
      );
      menuElements.get(menuElements.size() - 1).setHeight(CFG.BUTTON_HEIGHT * 3 / 5);
      int var21;
      menuElements.add(
         new Button_Build(
            CFG.langManager.get("AddUnits"),
            Images.editor_leaders,
            0,
            0,
            0,
            var21 = tPosY + menuElements.get(menuElements.size() - 1).getHeight(),
            tempW,
            true,
            false,
            0,
            0.0F
         ) {
            @Override
            public void actionElement(int iID) {
               CFG.menuManager.rebuildInGame_AddUnitsSandBox(CFG.getActiveCivInfo());
            }

            @Override
            public int getSFX() {
               try {
                  return CFG.game.getProvince(BuildingsManager.iBuildInProvinceID).getTrueOwnerOfProvince() != CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()
                     ? super.getSFX()
                     : SoundsManager.SOUND_RECRUIT;
               } catch (IndexOutOfBoundsException var2) {
                  return super.getSFX();
               }
            }
         }
      );
      menuElements.get(menuElements.size() - 1).setHeight(CFG.BUTTON_HEIGHT * 3 / 5);
      menuElements.add(
         new Text_BuildTitle(
            CFG.langManager.get("Province"),
            -1,
            0,
            tPosY = var21 + menuElements.get(menuElements.size() - 1).getHeight(),
            tempW,
            CFG.TEXT_HEIGHT + CFG.PADDING * 4
         ) {
            @Override
            public Color getColor(boolean isActive) {
               return isActive
                  ? CFG.COLOR_TEXT_OPTIONS_NS_HOVER
                  : (this.getClickable() ? (this.getIsHovered() ? CFG.COLOR_TEXT_OPTIONS_NS : Color.WHITE) : new Color(0.78F, 0.78F, 0.78F, 0.7F));
            }
         }
      );
      tPosY += menuElements.get(menuElements.size() - 1).getHeight();

      try {
         menuElements.add(
            new Button_Build(
               CFG.langManager.get("Abandon"),
               Images.provinces,
               0,
               0,
               0,
               tPosY,
               tempW,
               BuildingsManager.iBuildInProvinceID != CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCapitalProvinceID()
                  && !CFG.game.getProvince(BuildingsManager.iBuildInProvinceID).isOccupied(),
               false,
               0,
               0.0F
            ) {
               @Override
               public void actionElement(int iID) {
                  if (BuildingsManager.iBuildInProvinceID >= 0) {
                     CFG.menuManager.rebuildInGame_Abadon(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), BuildingsManager.iBuildInProvinceID);
                  }
               }
            }
         );
         menuElements.get(menuElements.size() - 1).setHeight(CFG.BUTTON_HEIGHT * 3 / 5);
         tPosY += menuElements.get(menuElements.size() - 1).getHeight();
      } catch (IndexOutOfBoundsException var5) {
         CFG.exceptionStack(var5);
      }

      this.initMenu(
         new SliderMenuTitle(null, CFG.BUTTON_HEIGHT * 3 / 5, false, false) {
            @Override
            public void draw(SpriteBatch oSB, int iTranslateX, int nPosX, int nPosY, int nWidth, boolean sliderMenuIsActive) {
               ImageManager.getImage(Images.new_game_top_edge_title)
                  .draw2(
                     oSB,
                     Menu_InGame_SandBoxMenu.this.getPosX() + iTranslateX,
                     Menu_InGame_SandBoxMenu.this.getPosY() - ImageManager.getImage(Images.new_game_top_edge_title).getHeight() - this.getHeight(),
                     Menu_InGame_SandBoxMenu.this.getWidth() + 2,
                     this.getHeight(),
                     true,
                     false
                  );
               oSB.setColor(
                  new Color(
                     CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getR() / 255.0F,
                     CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getG() / 255.0F,
                     CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getB() / 255.0F,
                     0.165F
                  )
               );
               ImageManager.getImage(Images.line_32_off1)
                  .draw(
                     oSB,
                     nPosX + iTranslateX,
                     nPosY - this.getHeight() + 2 - ImageManager.getImage(Images.line_32_off1).getHeight(),
                     nWidth,
                     this.getHeight() - 2,
                     false,
                     true
                  );
               oSB.setColor(
                  new Color(
                     CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getR() / 255.0F,
                     CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getG() / 255.0F,
                     CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getB() / 255.0F,
                     0.375F
                  )
               );
               ImageManager.getImage(Images.gradient)
                  .draw(
                     oSB,
                     nPosX + iTranslateX,
                     nPosY - this.getHeight() * 2 / 3 - ImageManager.getImage(Images.gradient).getHeight(),
                     nWidth,
                     this.getHeight() * 2 / 3,
                     false,
                     true
                  );
               oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.6F));
               ImageManager.getImage(Images.line_32_off1)
                  .draw(
                     oSB,
                     Menu_InGame_SandBoxMenu.this.getPosX() + iTranslateX,
                     Menu_InGame_SandBoxMenu.this.getPosY()
                        - ImageManager.getImage(Images.pix255_255_255).getHeight()
                        - ImageManager.getImage(Images.line_32_off1).getHeight(),
                     Menu_InGame_SandBoxMenu.this.getWidth(),
                     1
                  );
               oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.8F));
               ImageManager.getImage(Images.slider_gradient)
                  .draw(
                     oSB,
                     Menu_InGame_SandBoxMenu.this.getPosX() + iTranslateX,
                     Menu_InGame_SandBoxMenu.this.getPosY()
                        - ImageManager.getImage(Images.pix255_255_255).getHeight()
                        - ImageManager.getImage(Images.slider_gradient).getHeight(),
                     Menu_InGame_SandBoxMenu.this.getWidth() / 4,
                     1
                  );
               ImageManager.getImage(Images.slider_gradient)
                  .draw(
                     oSB,
                     Menu_InGame_SandBoxMenu.this.getPosX()
                        + Menu_InGame_SandBoxMenu.this.getWidth()
                        - Menu_InGame_SandBoxMenu.this.getWidth() / 4
                        + iTranslateX,
                     Menu_InGame_SandBoxMenu.this.getPosY()
                        - ImageManager.getImage(Images.pix255_255_255).getHeight()
                        - ImageManager.getImage(Images.slider_gradient).getHeight(),
                     Menu_InGame_SandBoxMenu.this.getWidth() / 4,
                     1,
                     true,
                     false
                  );
               oSB.setColor(Color.WHITE);
               CFG.game
                  .getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
                  .getFlag()
                  .draw(
                     oSB,
                     Menu_InGame_SandBoxMenu.this.getPosX() + CFG.PADDING * 2 + iTranslateX,
                     Menu_InGame_SandBoxMenu.this.getPosY()
                        - this.getHeight() / 2
                        - CFG.CIV_FLAG_HEIGHT / 2
                        - CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getFlag().getHeight(),
                     CFG.CIV_FLAG_WIDTH,
                     CFG.CIV_FLAG_HEIGHT
                  );
               ImageManager.getImage(Images.flag_rect)
                  .draw(
                     oSB,
                     Menu_InGame_SandBoxMenu.this.getPosX() + CFG.PADDING * 2 + iTranslateX,
                     Menu_InGame_SandBoxMenu.this.getPosY() - this.getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2
                  );
               CFG.fontMain.getData().setScale(0.8F);
               CFG.drawText(
                  oSB,
                  this.getText(),
                  nPosX + nWidth / 2 - (int)(this.getTextWidth() * 0.8F / 2.0F) + iTranslateX,
                  nPosY - this.getHeight() + this.getHeight() / 2 + 1 - (int)(this.getTextHeight() * 0.8F / 2.0F),
                  Color.WHITE
               );
               CFG.fontMain.getData().setScale(1.0F);
            }
         },
         0,
         ImageManager.getImage(Images.top_left).getHeight() + CFG.PADDING * 3 + CFG.BUTTON_HEIGHT * 3 / 5,
         tempW,
         Math.min(
            tPosY,
            CFG.GAME_HEIGHT
               - (ImageManager.getImage(Images.top_left).getHeight() + CFG.PADDING * 3 + CFG.BUTTON_HEIGHT * 3 / 4 + (CFG.PADDING * 2 + CFG.BUTTON_HEIGHT) * 2)
         ),
         menuElements,
         false,
         true
      );
      if (BuildingsManager.iBuildInProvinceID < 0) {
         this.setVisible(false);
      }

      this.updateLanguage();
   }

   @Override
   public void updateLanguage() {
      this.getTitle().setText(CFG.langManager.get("Sandbox"));
   }

   @Override
   public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      if (lTime + 175L >= System.currentTimeMillis()) {
         int var5;
         int var6;
         iTranslateX = hideAnimation
            ? (var5 = iTranslateX - (int)(this.getWidth() * ((float)(System.currentTimeMillis() - lTime) / 175.0F)))
            : (var6 = iTranslateX + -this.getWidth() + (int)(this.getWidth() * ((float)(System.currentTimeMillis() - lTime) / 175.0F)));
         CFG.setRender_3(true);
      } else if (hideAnimation) {
         super.setVisible(false);
         return;
      }

      ImageManager.getImage(Images.new_game_top_edge_line)
         .draw2(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.new_game_top_edge_line).getHeight() + iTranslateY,
            this.getWidth() + 2,
            this.getHeight() + CFG.PADDING,
            true,
            true
         );
      oSB.setColor(new Color(0.09803922F, 0.05882353F, 0.37254903F, 0.25F));
      ImageManager.getImage(Images.gradient)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY,
            this.getWidth(),
            CFG.PADDING * 4
         );
      oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.65F));
      ImageManager.getImage(Images.gradient)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY,
            this.getWidth(),
            CFG.PADDING * 2
         );
      oSB.setColor(Color.WHITE);
      super.draw(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
      oSB.setColor(CFG.COLOR_CREATE_NEW_GAME_BOX_PLAYERS);
      ImageManager.getImage(Images.pix255_255_255)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + this.getHeight() + CFG.PADDING,
            this.getWidth()
         );
      oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.4F));
      ImageManager.getImage(Images.slider_gradient)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY()
               - ImageManager.getImage(Images.pix255_255_255).getHeight()
               - ImageManager.getImage(Images.slider_gradient).getHeight()
               + this.getHeight()
               + CFG.PADDING,
            this.getWidth(),
            1
         );
      oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.5F));
      ImageManager.getImage(Images.pix255_255_255)
         .draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() + CFG.PADDING, this.getWidth() + 2);
      oSB.setColor(Color.WHITE);
   }

   @Override
   public void drawScrollPos(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      if (sliderMenuIsActive) {
         super.drawScrollPos(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
      }
   }

   @Override
   public void actionElement(int iID) {
      if (!CFG.SPECTATOR_MODE) {
         this.getMenuElement(iID).actionElement(iID);
      }
   }

   @Override
   public void setVisible(boolean visible) {
      if (visible) {
         super.setVisible(visible);
         this.setHideAnimation(false);
      } else {
         this.setHideAnimation(true);
      }
   }

   public final void setHideAnimation(boolean nHideAnimation) {
      if (nHideAnimation != hideAnimation) {
         lTime = lTime > System.currentTimeMillis() - 175L
            ? System.currentTimeMillis() - (175L - (System.currentTimeMillis() - lTime))
            : System.currentTimeMillis();
         CFG.setRender_3(true);
      }

      hideAnimation = nHideAnimation;
   }
}
