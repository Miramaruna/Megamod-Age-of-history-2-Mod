package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

public class Menu_InGame_FlagAction_Stats extends SliderMenu {
   public boolean allianceButton = false;

   public Menu_InGame_FlagAction_Stats() {
      int tempHeight = CFG.TEXT_HEIGHT + CFG.PADDING * 4;
      int tempWidth = CFG.GAME_WIDTH - CFG.PADDING * 4;
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      menuElements.add(new Button_Transparent(0, 0, CFG.PADDING, tempHeight, false));
      menuElements.add(
         new Text_FlagActionStats(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCivName(), CFG.PADDING, 0) {
            @Override
            public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
               super.draw(oSB, iTranslateX, iTranslateY, isActive, scrollableY);
               CFG.game
                  .getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
                  .getFlag()
                  .draw(
                     oSB,
                     this.getPosX() + iTranslateX,
                     this.getPosY()
                        + this.getHeight() / 2
                        - (int)(
                              ImageManager.getImage(Images.flag_rect).getHeight()
                                 * Menu_InGame_FlagAction_Stats.this.getImageScale(ImageManager.getImage(Images.flag_rect).getHeight())
                           )
                           / 2
                        - CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getFlag().getHeight()
                        + iTranslateY,
                     (int)(
                        ImageManager.getImage(Images.flag_rect).getWidth()
                           * Menu_InGame_FlagAction_Stats.this.getImageScale(ImageManager.getImage(Images.flag_rect).getHeight())
                     ),
                     (int)(
                        ImageManager.getImage(Images.flag_rect).getHeight()
                           * Menu_InGame_FlagAction_Stats.this.getImageScale(ImageManager.getImage(Images.flag_rect).getHeight())
                     )
                  );
               ImageManager.getImage(Images.flag_rect)
                  .draw(
                     oSB,
                     this.getPosX() + iTranslateX,
                     this.getPosY()
                        + this.getHeight() / 2
                        - (int)(
                              ImageManager.getImage(Images.flag_rect).getHeight()
                                 * Menu_InGame_FlagAction_Stats.this.getImageScale(ImageManager.getImage(Images.flag_rect).getHeight())
                           )
                           / 2
                        - ImageManager.getImage(Images.flag_rect).getHeight()
                        + iTranslateY,
                     (int)(
                        ImageManager.getImage(Images.flag_rect).getWidth()
                           * Menu_InGame_FlagAction_Stats.this.getImageScale(ImageManager.getImage(Images.flag_rect).getHeight())
                     ),
                     (int)(
                        ImageManager.getImage(Images.flag_rect).getHeight()
                           * Menu_InGame_FlagAction_Stats.this.getImageScale(ImageManager.getImage(Images.flag_rect).getHeight())
                     )
                  );
            }

            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               if (CFG.game.getProvince(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCapitalProvinceID()).getCitiesSize() > 0) {
                  nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()));
                  nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Capital") + ": "));
                  nData.add(
                     new MenuElement_Hover_v2_Element_Type_Text(
                        CFG.game.getProvince(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCapitalProvinceID()).getCity(0).getCityName(),
                        CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                     )
                  );
                  nElements.add(new MenuElement_Hover_v2_Element2(nData));
                  nData.clear();
                  if (CFG.isDesktop()) {
                     nData.add(new MenuElement_Hover_v2_Element_Type_Space());
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Shortcut") + ": "));
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text("HOME", CFG.COLOR_TEXT_NUM_OF_PROVINCES));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                  }

                  this.menuElementHover = new MenuElement_Hover_v2(nElements);
               } else if (CFG.game.getProvince(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCapitalProvinceID()).getName().length() > 0
                  )
                {
                  nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()));
                  nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Capital") + ": "));
                  nData.add(
                     new MenuElement_Hover_v2_Element_Type_Text(
                        CFG.game.getProvince(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCapitalProvinceID()).getName(),
                        CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                     )
                  );
                  nElements.add(new MenuElement_Hover_v2_Element2(nData));
                  nData.clear();
                  if (CFG.isDesktop()) {
                     nData.add(new MenuElement_Hover_v2_Element_Type_Space());
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Shortcut") + ": "));
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text("HOME", CFG.COLOR_TEXT_NUM_OF_PROVINCES));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                  }

                  this.menuElementHover = new MenuElement_Hover_v2(nElements);
               } else {
                  this.menuElementHover = null;
               }
            }

            @Override
            public int getWidth() {
               return super.getWidth() + this.getTextPos();
            }

            @Override
            public int getTextPos() {
               return CFG.PADDING
                  + (int)(
                     ImageManager.getImage(Images.flag_rect).getWidth()
                        * Menu_InGame_FlagAction_Stats.this.getImageScale(ImageManager.getImage(Images.flag_rect).getHeight())
                  );
            }

            @Override
            public Color getColor(boolean isActive) {
               return isActive
                  ? CFG.COLOR_TEXT_OPTIONS_LEFT_NS_ACTIVE
                  : (
                     this.getClickable()
                        ? (this.getIsHovered() ? CFG.COLOR_TEXT_OPTIONS_LEFT_NS_HOVER : CFG.COLOR_TEXT_OPTIONS_LEFT_NS)
                        : CFG.COLOR_BUTTON_MENU_TEXT_NOT_CLICKABLE
                  );
            }

            @Override
            public void actionElement(int iID) {
               if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCapitalProvinceID() >= 0) {
                  CFG.game.setActiveProvinceID(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCapitalProvinceID());
                  if (CFG.game.getProvince(CFG.game.getActiveProvinceID()).getName().length() > 0) {
                     CFG.toast.setInView(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getName(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE);
                  }

                  CFG.map.getMapCoordinates().centerToProvinceID(CFG.game.getActiveProvinceID());
                  CFG.viewsManager.disableAllViews();
               }
            }
         }
      );
      if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID() > 0) {
         this.allianceButton = true;
         menuElements.add(
            new Text_FlagActionStats(
               "" + CFG.game.getAlliance(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID()).getAllianceName(), CFG.PADDING, 0
            ) {
               @Override
               public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
                  super.draw(oSB, iTranslateX, iTranslateY, isActive, scrollableY);
                  ImageManager.getImage(Images.diplo_alliance)
                     .draw(
                        oSB,
                        this.getPosX() + iTranslateX,
                        this.getPosY()
                           + this.getHeight() / 2
                           - (int)(
                                 ImageManager.getImage(Images.diplo_alliance).getHeight()
                                    * Menu_InGame_FlagAction_Stats.this.getImageScale(ImageManager.getImage(Images.diplo_alliance).getHeight())
                              )
                              / 2
                           - ImageManager.getImage(Images.diplo_alliance).getHeight()
                           + iTranslateY,
                        (int)(
                           ImageManager.getImage(Images.diplo_alliance).getWidth()
                              * Menu_InGame_FlagAction_Stats.this.getImageScale(ImageManager.getImage(Images.diplo_alliance).getHeight())
                        ),
                        (int)(
                           ImageManager.getImage(Images.diplo_alliance).getHeight()
                              * Menu_InGame_FlagAction_Stats.this.getImageScale(ImageManager.getImage(Images.diplo_alliance).getHeight())
                        )
                     );
               }

               @Override
               public int getWidth() {
                  return super.getWidth() + this.getTextPos();
               }

               @Override
               public int getTextPos() {
                  return CFG.PADDING
                     + (int)(
                        ImageManager.getImage(Images.diplo_alliance).getWidth()
                           * Menu_InGame_FlagAction_Stats.this.getImageScale(ImageManager.getImage(Images.diplo_alliance).getHeight())
                     );
               }

               @Override
               public void buildElementHover() {
                  try {
                     ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
                     ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();

                     for (int i = 0;
                        i < CFG.game.getAlliance(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID()).getCivilizationsSize();
                        i++
                     ) {
                        nData.add(
                           new MenuElement_Hover_v2_Element_Type_Flag(
                              CFG.game.getAlliance(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID()).getCivilization(i)
                           )
                        );
                        nData.add(
                           new MenuElement_Hover_v2_Element_Type_Text(
                              CFG.game
                                 .getCiv(
                                    CFG.game.getAlliance(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID()).getCivilization(i)
                                 )
                                 .getCivName(),
                              CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                           )
                        );
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                     }

                     this.menuElementHover = new MenuElement_Hover_v2(nElements);
                     return;
                  } catch (IndexOutOfBoundsException var4) {
                     if (CFG.LOGS) {
                        CFG.exceptionStack(var4);
                     }
                  } catch (NullPointerException var5) {
                     if (CFG.LOGS) {
                        CFG.exceptionStack(var5);
                     }
                  }

                  this.menuElementHover = null;
               }
            }
         );
      }

      menuElements.add(
         new Text_FlagActionStats(
            CFG.langManager.get("Provinces") + ": ",
            "" + CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getNumOfProvinces(),
            CFG.COLOR_TEXT_NUM_OF_PROVINCES,
            CFG.PADDING,
            0
         ) {
            @Override
            public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
               super.draw(oSB, iTranslateX, iTranslateY, isActive, scrollableY);
               ImageManager.getImage(Images.provinces)
                  .draw(
                     oSB,
                     this.getPosX() + iTranslateX,
                     this.getPosY()
                        + this.getHeight() / 2
                        - (int)(
                              ImageManager.getImage(Images.provinces).getHeight()
                                 * Menu_InGame_FlagAction_Stats.this.getImageScale(ImageManager.getImage(Images.provinces).getHeight())
                           )
                           / 2
                        - ImageManager.getImage(Images.provinces).getHeight()
                        + iTranslateY,
                     (int)(
                        ImageManager.getImage(Images.provinces).getWidth()
                           * Menu_InGame_FlagAction_Stats.this.getImageScale(ImageManager.getImage(Images.provinces).getHeight())
                     ),
                     (int)(
                        ImageManager.getImage(Images.provinces).getHeight()
                           * Menu_InGame_FlagAction_Stats.this.getImageScale(ImageManager.getImage(Images.provinces).getHeight())
                     )
                  );
            }

            @Override
            public int getWidth() {
               return super.getWidth() + this.getTextPos();
            }

            @Override
            public int getTextPos() {
               return CFG.PADDING
                  + (int)(
                     ImageManager.getImage(Images.provinces).getWidth()
                        * Menu_InGame_FlagAction_Stats.this.getImageScale(ImageManager.getImage(Images.provinces).getHeight())
                  );
            }

            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               ArrayList<Integer> tempTerrainTypes = new ArrayList<>();
               ArrayList<Integer> numOfProvinccesByTerrain = new ArrayList<>();

               for (int i = 0; i < CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getNumOfProvinces(); i++) {
                  boolean add = true;

                  for (int j = 0; j < tempTerrainTypes.size(); j++) {
                     if (CFG.game.getProvince(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getProvinceID(i)).getTerrainTypeID()
                        == tempTerrainTypes.get(j)) {
                        add = false;
                        numOfProvinccesByTerrain.set(j, numOfProvinccesByTerrain.get(j) + 1);
                        break;
                     }
                  }

                  if (add) {
                     tempTerrainTypes.add(
                        CFG.game.getProvince(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getProvinceID(i)).getTerrainTypeID()
                     );
                     numOfProvinccesByTerrain.add(1);
                  }
               }

               int iSize = tempTerrainTypes.size();

               for (int var10 = 0; var10 < iSize - 1; var10++) {
                  for (int jx = var10 + 1; jx < iSize; jx++) {
                     if (numOfProvinccesByTerrain.get(var10) < numOfProvinccesByTerrain.get(jx)) {
                        int tempD = tempTerrainTypes.get(var10);
                        tempTerrainTypes.set(var10, tempTerrainTypes.get(jx));
                        tempTerrainTypes.set(jx, tempD);
                        tempD = numOfProvinccesByTerrain.get(var10);
                        numOfProvinccesByTerrain.set(var10, numOfProvinccesByTerrain.get(jx));
                        numOfProvinccesByTerrain.set(jx, tempD);
                     }
                  }
               }

               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Provinces") + ": "));
               nData.add(
                  new MenuElement_Hover_v2_Element_Type_Text(
                     "" + CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getNumOfProvinces(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                  )
               );
               nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), CFG.PADDING, 0));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();

               for (int var11 = 0; var11 < tempTerrainTypes.size(); var11++) {
                  nData.add(
                     new MenuElement_Hover_v2_Element_Type_Color(
                        new Color(
                           CFG.terrainTypesManager.getColor(tempTerrainTypes.get(var11)).r,
                           CFG.terrainTypesManager.getColor(tempTerrainTypes.get(var11)).g,
                           CFG.terrainTypesManager.getColor(tempTerrainTypes.get(var11)).b,
                           1.0F
                        )
                     )
                  );
                  nData.add(new MenuElement_Hover_v2_Element_Type_Terrain(tempTerrainTypes.get(var11)));
                  nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.terrainTypesManager.getName(tempTerrainTypes.get(var11)) + ": "));
                  nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + numOfProvinccesByTerrain.get(var11), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                  nElements.add(new MenuElement_Hover_v2_Element2(nData));
                  nData.clear();
               }

               this.menuElementHover = nElements.size() > 0 ? new MenuElement_Hover_v2(nElements) : null;
            }
         }
      );
      menuElements.add(
         new Text_FlagActionStats(
            CFG.langManager.get("Population") + ": ",
            CFG.getNumberWithSpaces("" + CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).countPopulation()),
            CFG.COLOR_TEXT_POPULATION,
            CFG.PADDING,
            0
         ) {
            @Override
            public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
               super.draw(oSB, iTranslateX, iTranslateY, isActive, scrollableY);
               ImageManager.getImage(Images.population)
                  .draw(
                     oSB,
                     this.getPosX() + iTranslateX,
                     this.getPosY()
                        + this.getHeight() / 2
                        - (int)(
                              ImageManager.getImage(Images.population).getHeight()
                                 * Menu_InGame_FlagAction_Stats.this.getImageScale(ImageManager.getImage(Images.population).getHeight())
                           )
                           / 2
                        - ImageManager.getImage(Images.population).getHeight()
                        + iTranslateY,
                     (int)(
                        ImageManager.getImage(Images.population).getWidth()
                           * Menu_InGame_FlagAction_Stats.this.getImageScale(ImageManager.getImage(Images.population).getHeight())
                     ),
                     (int)(
                        ImageManager.getImage(Images.population).getHeight()
                           * Menu_InGame_FlagAction_Stats.this.getImageScale(ImageManager.getImage(Images.population).getHeight())
                     )
                  );
            }

            @Override
            public int getWidth() {
               return super.getWidth() + this.getTextPos();
            }

            @Override
            public int getTextPos() {
               return CFG.PADDING
                  + (int)(
                     ImageManager.getImage(Images.population).getWidth()
                        * Menu_InGame_FlagAction_Stats.this.getImageScale(ImageManager.getImage(Images.population).getHeight())
                  );
            }

            @Override
            public void buildElementHover() {
               this.menuElementHover = CFG.game.getHover_PopulationOfCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
            }
         }
      );
      menuElements.add(
         new Text_FlagActionStats(
            CFG.langManager.get("Army") + ": ",
            CFG.getNumberWithSpaces("" + CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getNumOfUnits()),
            CFG.COLOR_TEXT_MODIFIER_NEUTRAL,
            CFG.PADDING,
            0
         ) {
            @Override
            public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
               super.draw(oSB, iTranslateX, iTranslateY, isActive, scrollableY);
               ImageManager.getImage(Images.diplo_army)
                  .draw(
                     oSB,
                     this.getPosX() + iTranslateX,
                     this.getPosY()
                        + this.getHeight() / 2
                        - (int)(
                              ImageManager.getImage(Images.diplo_army).getHeight()
                                 * Menu_InGame_FlagAction_Stats.this.getImageScale(ImageManager.getImage(Images.diplo_army).getHeight())
                           )
                           / 2
                        - ImageManager.getImage(Images.diplo_army).getHeight()
                        + iTranslateY,
                     (int)(
                        ImageManager.getImage(Images.diplo_army).getWidth()
                           * Menu_InGame_FlagAction_Stats.this.getImageScale(ImageManager.getImage(Images.diplo_army).getHeight())
                     ),
                     (int)(
                        ImageManager.getImage(Images.diplo_army).getHeight()
                           * Menu_InGame_FlagAction_Stats.this.getImageScale(ImageManager.getImage(Images.diplo_army).getHeight())
                     )
                  );
            }

            @Override
            public int getWidth() {
               return super.getWidth() + this.getTextPos();
            }

            @Override
            public int getTextPos() {
               return CFG.PADDING
                  + (int)(
                     ImageManager.getImage(Images.diplo_army).getWidth()
                        * Menu_InGame_FlagAction_Stats.this.getImageScale(ImageManager.getImage(Images.diplo_army).getHeight())
                  );
            }

            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Army") + ": "));
               nData.add(
                  new MenuElement_Hover_v2_Element_Type_Text(
                     "" + CFG.getNumberWithSpaces("" + CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getNumOfUnits()),
                     CFG.COLOR_TEXT_NUM_OF_PROVINCES
                  )
               );
               nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), CFG.PADDING, 0));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               int nUpkeep = (int)CFG.game_NextTurnUpdate.getMilitaryUpkeep_Total(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("MilitaryUpkeep") + ": "));
               nData.add(
                  new MenuElement_Hover_v2_Element_Type_Text("" + nUpkeep, nUpkeep == 0 ? CFG.COLOR_TEXT_MODIFIER_NEUTRAL : CFG.COLOR_TEXT_MODIFIER_NEGATIVE2)
               );
               nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_gold, CFG.PADDING, CFG.PADDING));
               nData.add(
                  new MenuElement_Hover_v2_Element_Type_Text(
                     "" + (int)((float)nUpkeep / CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getNumOfUnits() * 100.0F) / 100.0F,
                     CFG.COLOR_INGAME_GOLD
                  )
               );
               nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_gold, CFG.PADDING, CFG.PADDING));
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("PerUnit")));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()));
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("WarWeariness") + ": "));
               nData.add(
                  new MenuElement_Hover_v2_Element_Type_Text(
                     "" + (int)(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getWarWeariness() * 10000.0F) / 100.0F + "%",
                     CFG.COLOR_TEXT_MODIFIER_NEUTRAL2
                  )
               );
               nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.diplo_weariness, CFG.PADDING, 0));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("MilitaryUpkeepH1"), CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
         }
      );
      menuElements.add(
         new Text_FlagActionStats(
            CFG.langManager.get("Economy") + ": ",
            CFG.getNumberWithSpaces("" + CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).countEconomy()),
            CFG.COLOR_TEXT_ECONOMY,
            CFG.PADDING,
            0
         ) {
            @Override
            public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
               super.draw(oSB, iTranslateX, iTranslateY, isActive, scrollableY);
               ImageManager.getImage(Images.economy)
                  .draw(
                     oSB,
                     this.getPosX() + iTranslateX,
                     this.getPosY()
                        + this.getHeight() / 2
                        - (int)(
                              ImageManager.getImage(Images.economy).getHeight()
                                 * Menu_InGame_FlagAction_Stats.this.getImageScale(ImageManager.getImage(Images.economy).getHeight())
                           )
                           / 2
                        - ImageManager.getImage(Images.economy).getHeight()
                        + iTranslateY,
                     (int)(
                        ImageManager.getImage(Images.economy).getWidth()
                           * Menu_InGame_FlagAction_Stats.this.getImageScale(ImageManager.getImage(Images.economy).getHeight())
                     ),
                     (int)(
                        ImageManager.getImage(Images.economy).getHeight()
                           * Menu_InGame_FlagAction_Stats.this.getImageScale(ImageManager.getImage(Images.economy).getHeight())
                     )
                  );
            }

            @Override
            public int getWidth() {
               return super.getWidth() + this.getTextPos();
            }

            @Override
            public int getTextPos() {
               return CFG.PADDING
                  + (int)(
                     ImageManager.getImage(Images.economy).getWidth()
                        * Menu_InGame_FlagAction_Stats.this.getImageScale(ImageManager.getImage(Images.economy).getHeight())
                  );
            }

            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()));
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Unemployment") + ": "));
               nData.add(
                  new MenuElement_Hover_v2_Element_Type_Text(
                     ""
                        + CFG.getNumberWithSpaces("" + CFG.game_NextTurnUpdate.getUnemploymentPopulation(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()))
                        + " ",
                     CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                  )
               );
               nData.add(
                  new MenuElement_Hover_v2_Element_Type_Text(
                     "["
                        + CFG.getPercentage(
                           (float)CFG.game_NextTurnUpdate.getUnemploymentPopulation(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()),
                           (float)CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).countPopulation(),
                           4
                        )
                        + "%]",
                     CFG.COLOR_TEXT_MODIFIER_NEUTRAL
                  )
               );
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
         }
      );
      menuElements.add(
         new Text_FlagActionStats(
            CFG.langManager.get("Technology") + ": ",
            "" + (int)(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getTechnologyLevel() * 100.0F) / 100.0F,
            CFG.COLOR_TEXT_TECHNOLOGY,
            CFG.PADDING,
            0
         ) {
            @Override
            public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
               super.draw(oSB, iTranslateX, iTranslateY, isActive, scrollableY);
               ImageManager.getImage(Images.technology)
                  .draw(
                     oSB,
                     this.getPosX() + iTranslateX,
                     this.getPosY()
                        + this.getHeight() / 2
                        - (int)(
                              ImageManager.getImage(Images.technology).getHeight()
                                 * Menu_InGame_FlagAction_Stats.this.getImageScale(ImageManager.getImage(Images.technology).getHeight())
                           )
                           / 2
                        - ImageManager.getImage(Images.technology).getHeight()
                        + iTranslateY,
                     (int)(
                        ImageManager.getImage(Images.technology).getWidth()
                           * Menu_InGame_FlagAction_Stats.this.getImageScale(ImageManager.getImage(Images.technology).getHeight())
                     ),
                     (int)(
                        ImageManager.getImage(Images.technology).getHeight()
                           * Menu_InGame_FlagAction_Stats.this.getImageScale(ImageManager.getImage(Images.technology).getHeight())
                     )
                  );
            }

            @Override
            public int getWidth() {
               return super.getWidth() + this.getTextPos();
            }

            @Override
            public int getTextPos() {
               return CFG.PADDING
                  + (int)(
                     ImageManager.getImage(Images.technology).getWidth()
                        * Menu_InGame_FlagAction_Stats.this.getImageScale(ImageManager.getImage(Images.technology).getHeight())
                  );
            }

            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()));
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Technology") + ": "));
               nData.add(
                  new MenuElement_Hover_v2_Element_Type_Text(
                     "" + (int)(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getTechnologyLevel() * 100.0F) / 100.0F + "/" + 2.0F,
                     CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                  )
               );
               nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.technology, CFG.PADDING, 0));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Tech1"), CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Tech2"), CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
               nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.research, CFG.PADDING, 0));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Tech3"), CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Tech4"), CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
         }
      );
      menuElements.add(
         new Text_FlagActionStats(
            CFG.langManager.get("AverageDevelopment")
               + ": "
               + CFG.game.countAvarageDevelopmentLevel(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
               + " ["
               + (int)(
                  CFG.game.countAvarageDevelopmentLevel_Float(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
                     / CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getTechnologyLevel()
                     * 100.0F
               )
               + "%]",
            CFG.PADDING,
            0
         ) {
            @Override
            public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
               super.draw(oSB, iTranslateX, iTranslateY, isActive, scrollableY);
               ImageManager.getImage(Images.development)
                  .draw(
                     oSB,
                     this.getPosX() + iTranslateX,
                     this.getPosY()
                        + this.getHeight() / 2
                        - (int)(
                              ImageManager.getImage(Images.development).getHeight()
                                 * Menu_InGame_FlagAction_Stats.this.getImageScale(ImageManager.getImage(Images.development).getHeight())
                           )
                           / 2
                        - ImageManager.getImage(Images.development).getHeight()
                        + iTranslateY,
                     (int)(
                        ImageManager.getImage(Images.development).getWidth()
                           * Menu_InGame_FlagAction_Stats.this.getImageScale(ImageManager.getImage(Images.development).getHeight())
                     ),
                     (int)(
                        ImageManager.getImage(Images.development).getHeight()
                           * Menu_InGame_FlagAction_Stats.this.getImageScale(ImageManager.getImage(Images.development).getHeight())
                     )
                  );
            }

            @Override
            public int getWidth() {
               return super.getWidth() + this.getTextPos();
            }

            @Override
            public int getTextPos() {
               return CFG.PADDING
                  + (int)(
                     ImageManager.getImage(Images.development).getWidth()
                        * Menu_InGame_FlagAction_Stats.this.getImageScale(ImageManager.getImage(Images.development).getHeight())
                  );
            }

            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()));
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("AverageDevelopment") + ": "));
               nData.add(
                  new MenuElement_Hover_v2_Element_Type_Text(
                     ""
                        + CFG.game.countAvarageDevelopmentLevel(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
                        + "/"
                        + (int)(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getTechnologyLevel() * 100.0F) / 100.0F,
                     CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                  )
               );
               nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.development, CFG.PADDING, 0));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Tech4"), CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Tech5"), CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
         }
      );
      menuElements.add(
         new Text_FlagActionStats(
            CFG.langManager.get("Inflation") + ": ",
            "" + (int)(CFG.game_NextTurnUpdate.getInflationPerc(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) * 10000.0F) / 100.0F + "%",
            CFG.game_NextTurnUpdate.getInflationPerc(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) > 0.0F
               ? CFG.COLOR_TEXT_MODIFIER_NEGATIVE2
               : CFG.COLOR_TEXT_MODIFIER_POSITIVE,
            CFG.PADDING,
            0
         ) {
            @Override
            public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
               super.draw(oSB, iTranslateX, iTranslateY, isActive, scrollableY);
               ImageManager.getImage(Images.development_down)
                  .draw(
                     oSB,
                     this.getPosX() + iTranslateX,
                     this.getPosY()
                        + this.getHeight() / 2
                        - (int)(
                              ImageManager.getImage(Images.development_down).getHeight()
                                 * Menu_InGame_FlagAction_Stats.this.getImageScale(ImageManager.getImage(Images.development_down).getHeight())
                           )
                           / 2
                        - ImageManager.getImage(Images.development_down).getHeight()
                        + iTranslateY,
                     (int)(
                        ImageManager.getImage(Images.development_down).getWidth()
                           * Menu_InGame_FlagAction_Stats.this.getImageScale(ImageManager.getImage(Images.development_down).getHeight())
                     ),
                     (int)(
                        ImageManager.getImage(Images.development_down).getHeight()
                           * Menu_InGame_FlagAction_Stats.this.getImageScale(ImageManager.getImage(Images.development_down).getHeight())
                     )
                  );
            }

            @Override
            public int getWidth() {
               return super.getWidth() + this.getTextPos();
            }

            @Override
            public int getTextPos() {
               return CFG.PADDING
                  + (int)(
                     ImageManager.getImage(Images.development_down).getWidth()
                        * Menu_InGame_FlagAction_Stats.this.getImageScale(ImageManager.getImage(Images.development_down).getHeight())
                  );
            }

            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()));
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Inflation") + ": "));
               nData.add(
                  new MenuElement_Hover_v2_Element_Type_Text(
                     "" + CFG.getNumberWithSpaces("" + (int)CFG.game_NextTurnUpdate.getInflation(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())),
                     (int)CFG.game_NextTurnUpdate.getInflation(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) > 0
                        ? CFG.COLOR_TEXT_MODIFIER_NEGATIVE2
                        : CFG.COLOR_TEXT_MODIFIER_NEUTRAL2
                  )
               );
               nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_gold, CFG.PADDING, CFG.PADDING));
               nData.add(
                  new MenuElement_Hover_v2_Element_Type_Text(
                     "[" + (int)(CFG.game_NextTurnUpdate.getInflationPerc(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) * 10000.0F) / 100.0F + "%]",
                     CFG.COLOR_TEXT_MODIFIER_NEUTRAL
                  )
               );
               nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.development_down, CFG.PADDING, 0));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("InflationH1"), CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("InflationH2"), CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
         }
      );
      menuElements.add(
         new Text_FlagActionStats(
            CFG.langManager.get("WarWeariness") + ": ",
            "" + (int)(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getWarWeariness() * 10000.0F) / 100.0F + "%",
            CFG.COLOR_TEXT_MODIFIER_NEUTRAL2,
            CFG.PADDING,
            0
         ) {
            @Override
            public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
               super.draw(oSB, iTranslateX, iTranslateY, isActive, scrollableY);
               ImageManager.getImage(Images.diplo_weariness)
                  .draw(
                     oSB,
                     this.getPosX() + iTranslateX,
                     this.getPosY()
                        + this.getHeight() / 2
                        - (int)(
                              ImageManager.getImage(Images.diplo_weariness).getHeight()
                                 * Menu_InGame_FlagAction_Stats.this.getImageScale(ImageManager.getImage(Images.diplo_weariness).getHeight())
                           )
                           / 2
                        - ImageManager.getImage(Images.diplo_weariness).getHeight()
                        + iTranslateY,
                     (int)(
                        ImageManager.getImage(Images.diplo_weariness).getWidth()
                           * Menu_InGame_FlagAction_Stats.this.getImageScale(ImageManager.getImage(Images.diplo_weariness).getHeight())
                     ),
                     (int)(
                        ImageManager.getImage(Images.diplo_weariness).getHeight()
                           * Menu_InGame_FlagAction_Stats.this.getImageScale(ImageManager.getImage(Images.diplo_weariness).getHeight())
                     )
                  );
            }

            @Override
            public int getWidth() {
               return super.getWidth() + this.getTextPos();
            }

            @Override
            public int getTextPos() {
               return CFG.PADDING
                  + (int)(
                     ImageManager.getImage(Images.diplo_weariness).getWidth()
                        * Menu_InGame_FlagAction_Stats.this.getImageScale(ImageManager.getImage(Images.diplo_weariness).getHeight())
                  );
            }

            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()));
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("WarWeariness") + ": "));
               nData.add(
                  new MenuElement_Hover_v2_Element_Type_Text(
                     "" + (int)(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getWarWeariness() * 10000.0F) / 100.0F + "%",
                     CFG.COLOR_TEXT_MODIFIER_NEUTRAL2
                  )
               );
               nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.diplo_weariness, CFG.PADDING, 0));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("WarWearinessH1"), CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("WarWearinessH2"), CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("WarWearinessH3"), CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
         }
      );
      menuElements.add(
         new Text_FlagActionStats(
            CFG.langManager.get("Hunger") + ": ",
            "" + (int)(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getHunger() * 10000.0F) / 100.0F + "%",
            CFG.COLOR_TEXT_REVOLUTION_MAX,
            CFG.PADDING,
            0
         ) {
            @Override
            public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
               super.draw(oSB, iTranslateX, iTranslateY, isActive, scrollableY);
               ImageManager.getImage(Images.b_farm)
                  .draw(
                     oSB,
                     this.getPosX() + iTranslateX,
                     this.getPosY()
                        + this.getHeight() / 2
                        - (int)(
                              ImageManager.getImage(Images.b_farm).getHeight()
                                 * Menu_InGame_FlagAction_Stats.this.getImageScale(ImageManager.getImage(Images.b_farm).getHeight())
                           )
                           / 2
                        - ImageManager.getImage(Images.b_farm).getHeight()
                        + iTranslateY,
                     (int)(
                        ImageManager.getImage(Images.b_farm).getWidth()
                           * Menu_InGame_FlagAction_Stats.this.getImageScale(ImageManager.getImage(Images.b_farm).getHeight())
                     ),
                     (int)(
                        ImageManager.getImage(Images.b_farm).getHeight()
                           * Menu_InGame_FlagAction_Stats.this.getImageScale(ImageManager.getImage(Images.b_farm).getHeight())
                     )
                  );
            }

            @Override
            public int getWidth() {
               return super.getWidth() + this.getTextPos();
            }

            @Override
            public int getTextPos() {
               return CFG.PADDING
                  + (int)(
                     ImageManager.getImage(Images.diplo_weariness).getWidth()
                        * Menu_InGame_FlagAction_Stats.this.getImageScale(ImageManager.getImage(Images.b_farm).getHeight())
                  );
            }

            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()));
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Hunger") + ": "));
               nData.add(
                  new MenuElement_Hover_v2_Element_Type_Text(
                     "" + (int)(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getHunger() * 10000.0F) / 100.0F + "%",
                     CFG.COLOR_TEXT_MODIFIER_NEUTRAL2
                  )
               );
               nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.b_farm, CFG.PADDING, 0));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Hunger1"), CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Hunger2"), CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
         }
      );
      menuElements.add(
         new Text_FlagActionStats(
            CFG.langManager.get("Happiness") + ": ",
            "" + CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getHappiness() + "%",
            CFG.getColorStep(
               CFG.COLOR_TEXT_HAPPINESS_MIN,
               CFG.COLOR_TEXT_HAPPINESS_MAX,
               CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getHappiness(),
               100,
               1.0F
            ),
            CFG.PADDING,
            0
         ) {
            @Override
            public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
               super.draw(oSB, iTranslateX, iTranslateY, isActive, scrollableY);

               try {
                  ImageManager.getImage(CFG.getHappinesImage(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getHappiness()))
                     .draw(
                        oSB,
                        this.getPosX() + iTranslateX,
                        this.getPosY()
                           + this.getHeight() / 2
                           - (int)(
                                 ImageManager.getImage(Images.happiness).getHeight()
                                    * Menu_InGame_FlagAction_Stats.this.getImageScale(ImageManager.getImage(Images.happiness).getHeight())
                              )
                              / 2
                           - ImageManager.getImage(Images.happiness).getHeight()
                           + iTranslateY,
                        (int)(
                           ImageManager.getImage(Images.happiness).getWidth()
                              * Menu_InGame_FlagAction_Stats.this.getImageScale(ImageManager.getImage(Images.happiness).getHeight())
                        ),
                        (int)(
                           ImageManager.getImage(Images.happiness).getHeight()
                              * Menu_InGame_FlagAction_Stats.this.getImageScale(ImageManager.getImage(Images.happiness).getHeight())
                        )
                     );
               } catch (IndexOutOfBoundsException var7) {
                  ImageManager.getImage(Images.happiness)
                     .draw(
                        oSB,
                        this.getPosX() + iTranslateX,
                        this.getPosY()
                           + this.getHeight() / 2
                           - (int)(
                                 ImageManager.getImage(Images.happiness).getHeight()
                                    * Menu_InGame_FlagAction_Stats.this.getImageScale(ImageManager.getImage(Images.happiness).getHeight())
                              )
                              / 2
                           - ImageManager.getImage(Images.happiness).getHeight()
                           + iTranslateY,
                        (int)(
                           ImageManager.getImage(Images.happiness).getWidth()
                              * Menu_InGame_FlagAction_Stats.this.getImageScale(ImageManager.getImage(Images.happiness).getHeight())
                        ),
                        (int)(
                           ImageManager.getImage(Images.happiness).getHeight()
                              * Menu_InGame_FlagAction_Stats.this.getImageScale(ImageManager.getImage(Images.happiness).getHeight())
                        )
                     );
               }
            }

            @Override
            public int getWidth() {
               return super.getWidth() + this.getTextPos();
            }

            @Override
            public int getTextPos() {
               return CFG.PADDING
                  + (int)(
                     ImageManager.getImage(Images.happiness).getWidth()
                        * Menu_InGame_FlagAction_Stats.this.getImageScale(ImageManager.getImage(Images.happiness).getHeight())
                  );
            }

            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()));
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Happiness") + ": "));
               nData.add(
                  new MenuElement_Hover_v2_Element_Type_Text(
                     "" + CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getHappiness() + "%", CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                  )
               );
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
         }
      );
      menuElements.add(
         new Text_FlagActionStats(
            CFG.langManager.get("Stability") + ": ",
            "" + (int)(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getStability() * 100.0F) + "%",
            CFG.getColorStep(
               CFG.COLOR_TEXT_PROVINCE_STABILITY_MIN,
               CFG.COLOR_TEXT_PROVINCE_STABILITY_MAX,
               (int)(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getStability() * 100.0F),
               100,
               1.0F
            ),
            CFG.PADDING,
            0
         ) {
            @Override
            public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
               super.draw(oSB, iTranslateX, iTranslateY, isActive, scrollableY);
               ImageManager.getImage(Images.diplo_popstability)
                  .draw(
                     oSB,
                     this.getPosX() + iTranslateX,
                     this.getPosY()
                        + this.getHeight() / 2
                        - (int)(
                              ImageManager.getImage(Images.diplo_popstability).getHeight()
                                 * Menu_InGame_FlagAction_Stats.this.getImageScale(ImageManager.getImage(Images.diplo_popstability).getHeight())
                           )
                           / 2
                        - ImageManager.getImage(Images.diplo_popstability).getHeight()
                        + iTranslateY,
                     (int)(
                        ImageManager.getImage(Images.diplo_popstability).getWidth()
                           * Menu_InGame_FlagAction_Stats.this.getImageScale(ImageManager.getImage(Images.diplo_popstability).getHeight())
                     ),
                     (int)(
                        ImageManager.getImage(Images.diplo_popstability).getHeight()
                           * Menu_InGame_FlagAction_Stats.this.getImageScale(ImageManager.getImage(Images.diplo_popstability).getHeight())
                     )
                  );
            }

            @Override
            public int getWidth() {
               return super.getWidth() + this.getTextPos();
            }

            @Override
            public int getTextPos() {
               return CFG.PADDING
                  + (int)(
                     ImageManager.getImage(Images.diplo_popstability).getWidth()
                        * Menu_InGame_FlagAction_Stats.this.getImageScale(ImageManager.getImage(Images.diplo_popstability).getHeight())
                  );
            }

            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()));
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Stability") + ": "));
               nData.add(
                  new MenuElement_Hover_v2_Element_Type_Text(
                     "" + (int)(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getStability() * 100.0F) + "%",
                     CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                  )
               );
               nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.diplo_popstability, CFG.PADDING, 0));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
         }
      );
      menuElements.add(
         new Text_FlagActionStats(
            CFG.langManager.get("CivRank") + ": ",
            "" + CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getRankPosition(),
            CFG.COLOR_TEXT_NUM_OF_PROVINCES,
            CFG.PADDING,
            0
         ) {
            @Override
            public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
               super.draw(oSB, iTranslateX, iTranslateY, isActive, scrollableY);
               ImageManager.getImage(Images.rank)
                  .draw(
                     oSB,
                     this.getPosX() + iTranslateX,
                     this.getPosY()
                        + this.getHeight() / 2
                        - (int)(
                              ImageManager.getImage(Images.rank).getHeight()
                                 * Menu_InGame_FlagAction_Stats.this.getImageScale(ImageManager.getImage(Images.rank).getHeight())
                           )
                           / 2
                        - ImageManager.getImage(Images.rank).getHeight()
                        + iTranslateY,
                     (int)(
                        ImageManager.getImage(Images.rank).getWidth()
                           * Menu_InGame_FlagAction_Stats.this.getImageScale(ImageManager.getImage(Images.rank).getHeight())
                     ),
                     (int)(
                        ImageManager.getImage(Images.rank).getHeight()
                           * Menu_InGame_FlagAction_Stats.this.getImageScale(ImageManager.getImage(Images.rank).getHeight())
                     )
                  );
            }

            @Override
            public int getWidth() {
               return super.getWidth() + this.getTextPos();
            }

            @Override
            public int getTextPos() {
               return CFG.PADDING
                  + (int)(
                     ImageManager.getImage(Images.rank).getWidth()
                        * Menu_InGame_FlagAction_Stats.this.getImageScale(ImageManager.getImage(Images.rank).getHeight())
                  );
            }

            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()));
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("CivRank") + ": "));
               nData.add(
                  new MenuElement_Hover_v2_Element_Type_Text(
                     "" + CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getRankPosition() + "/" + (CFG.game.getCivsSize() - 1),
                     CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                  )
               );
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
         }
      );
      menuElements.add(
         new Text_FlagActionStats(
            CFG.langManager.get("Government") + ": ",
            "" + CFG.ideologiesManager.getIdeology(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getIdeologyID()).getName(),
            new Color(
               CFG.ideologiesManager.getIdeology(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getIdeologyID()).getColor().r,
               CFG.ideologiesManager.getIdeology(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getIdeologyID()).getColor().g,
               CFG.ideologiesManager.getIdeology(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getIdeologyID()).getColor().b,
               1.0F
            ),
            CFG.PADDING,
            0
         ) {
            @Override
            public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
               super.draw(oSB, iTranslateX, iTranslateY, isActive, scrollableY);
               CFG.ideologiesManager
                  .getIdeology(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getIdeologyID())
                  .getCrownImageScaled()
                  .draw(
                     oSB,
                     this.getPosX() + iTranslateX,
                     this.getPosY()
                        + this.getHeight() / 2
                        - (int)(
                              CFG.ideologiesManager
                                    .getIdeology(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getIdeologyID())
                                    .getCrownImageScaled()
                                    .getHeight()
                                 * Menu_InGame_FlagAction_Stats.this.getImageScale(
                                    CFG.ideologiesManager
                                       .getIdeology(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getIdeologyID())
                                       .getCrownImageScaled()
                                       .getHeight()
                                 )
                           )
                           / 2
                        - CFG.ideologiesManager
                           .getIdeology(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getIdeologyID())
                           .getCrownImageScaled()
                           .getHeight()
                        + iTranslateY,
                     (int)(
                        CFG.ideologiesManager
                              .getIdeology(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getIdeologyID())
                              .getCrownImageScaled()
                              .getWidth()
                           * Menu_InGame_FlagAction_Stats.this.getImageScale(
                              CFG.ideologiesManager
                                 .getIdeology(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getIdeologyID())
                                 .getCrownImageScaled()
                                 .getHeight()
                           )
                     ),
                     (int)(
                        CFG.ideologiesManager
                              .getIdeology(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getIdeologyID())
                              .getCrownImageScaled()
                              .getHeight()
                           * Menu_InGame_FlagAction_Stats.this.getImageScale(
                              CFG.ideologiesManager
                                 .getIdeology(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getIdeologyID())
                                 .getCrownImageScaled()
                                 .getHeight()
                           )
                     )
                  );
            }

            @Override
            public int getWidth() {
               return super.getWidth() + this.getTextPos();
            }

            @Override
            public int getTextPos() {
               return CFG.PADDING
                  + (int)(
                     CFG.ideologiesManager
                           .getIdeology(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getIdeologyID())
                           .getCrownImageScaled()
                           .getWidth()
                        * Menu_InGame_FlagAction_Stats.this.getImageScale(
                           CFG.ideologiesManager
                              .getIdeology(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getIdeologyID())
                              .getCrownImageScaled()
                              .getHeight()
                        )
                  );
            }

            @Override
            public void buildElementHover() {
               this.menuElementHover = CFG.ideologiesManager.getIdeologyHover(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
            }
         }
      );
      menuElements.add(
         new Text_FlagActionStats(
            CFG.langManager.get("Difficulty") + ": " + CFG.getDifficultyName(CFG.DIFFICULTY), "", CFG.COLOR_TEXT_MODIFIER_NEUTRAL, CFG.PADDING, 0
         ) {
            @Override
            public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
               super.draw(oSB, iTranslateX, iTranslateY, isActive, scrollableY);
               if (isActive) {
                  oSB.setColor(new Color(1.0F, 1.0F, 1.0F, 0.7F));
               } else {
                  oSB.setColor(new Color(1.0F, 1.0F, 1.0F, 0.8F));
               }

               ImageManager.getImage(Images.editor_map)
                  .draw(
                     oSB,
                     this.getPosX() + iTranslateX,
                     this.getPosY()
                        + this.getHeight() / 2
                        - (int)(
                              ImageManager.getImage(Images.editor_map).getHeight()
                                 * Menu_InGame_FlagAction_Stats.this.getImageScale(ImageManager.getImage(Images.editor_map).getHeight())
                           )
                           / 2
                        - ImageManager.getImage(Images.editor_map).getHeight()
                        + iTranslateY,
                     (int)(
                        ImageManager.getImage(Images.editor_map).getWidth()
                           * Menu_InGame_FlagAction_Stats.this.getImageScale(ImageManager.getImage(Images.editor_map).getHeight())
                     ),
                     (int)(
                        ImageManager.getImage(Images.editor_map).getHeight()
                           * Menu_InGame_FlagAction_Stats.this.getImageScale(ImageManager.getImage(Images.editor_map).getHeight())
                     )
                  );
               oSB.setColor(Color.WHITE);
            }

            @Override
            public int getWidth() {
               return super.getWidth() + this.getTextPos();
            }

            @Override
            public int getTextPos() {
               return CFG.PADDING
                  + (int)(
                     ImageManager.getImage(Images.editor_map).getWidth()
                        * Menu_InGame_FlagAction_Stats.this.getImageScale(ImageManager.getImage(Images.editor_map).getHeight())
                  );
            }

            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()));
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Difficulty") + ": "));
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.getDifficultyName(CFG.DIFFICULTY), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }

            @Override
            public void actionElement(int iID) {
            }
         }
      );
      menuElements.add(
         new Text_FlagActionStats(CFG.langManager.get("Wiki"), "", CFG.COLOR_TEXT_MODIFIER_NEUTRAL, CFG.PADDING, 0) {
            @Override
            public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
               super.draw(oSB, iTranslateX, iTranslateY, isActive, scrollableY);
               if (isActive) {
                  oSB.setColor(new Color(1.0F, 1.0F, 1.0F, 0.7F));
               } else {
                  oSB.setColor(new Color(1.0F, 1.0F, 1.0F, 0.8F));
               }

               ImageManager.getImage(Images.wikipedia)
                  .draw(
                     oSB,
                     this.getPosX() + iTranslateX,
                     this.getPosY()
                        + this.getHeight() / 2
                        - (int)(
                              ImageManager.getImage(Images.wikipedia).getHeight()
                                 * Menu_InGame_FlagAction_Stats.this.getImageScale(ImageManager.getImage(Images.wikipedia).getHeight())
                           )
                           / 2
                        - ImageManager.getImage(Images.wikipedia).getHeight()
                        + iTranslateY,
                     (int)(
                        ImageManager.getImage(Images.wikipedia).getWidth()
                           * Menu_InGame_FlagAction_Stats.this.getImageScale(ImageManager.getImage(Images.wikipedia).getHeight())
                     ),
                     (int)(
                        ImageManager.getImage(Images.wikipedia).getHeight()
                           * Menu_InGame_FlagAction_Stats.this.getImageScale(ImageManager.getImage(Images.wikipedia).getHeight())
                     )
                  );
               oSB.setColor(Color.WHITE);
            }

            @Override
            public int getWidth() {
               return super.getWidth() + this.getTextPos();
            }

            @Override
            public int getTextPos() {
               return CFG.PADDING
                  + (int)(
                     ImageManager.getImage(Images.wikipedia).getWidth()
                        * Menu_InGame_FlagAction_Stats.this.getImageScale(ImageManager.getImage(Images.wikipedia).getHeight())
                  );
            }

            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()));
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Wiki") + ": "));
               nData.add(
                  new MenuElement_Hover_v2_Element_Type_Text(
                     CFG.getWikiInormationsLink_Clear(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCivTag()),
                     CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                  )
               );
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }

            @Override
            public void actionElement(int iID) {
               CFG.EDITOR_ACTIVE_GAMEDATA_TAG = CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCivTag();
               CFG.setDialogType(Dialog.GO_TO_WIKI);
            }
         }
      );
      menuElements.add(new Button_Transparent(0, 0, CFG.PADDING, tempHeight, false));
      int tElementsWidth = 0;

      for (int i = 0; i < menuElements.size(); i++) {
         tElementsWidth += menuElements.get(i).getWidth();
      }

      int tStartX = 0;
      int var7;
      tStartX = (var7 = tElementsWidth + CFG.PADDING * 2 * (menuElements.size() - 1)) > tempWidth ? 0 : (tempWidth - var7) / 2;

      for (int i = 0; i < menuElements.size(); i++) {
         menuElements.get(i).setPosX(tStartX);
         tStartX += menuElements.get(i).getWidth() + CFG.PADDING * 2;
      }

      menuElements.add(new Button_Transparent(0, 0, tempWidth - 4, tempHeight, true));
      this.initMenu(
         null,
         CFG.PADDING * 2 + 2,
         ImageManager.getImage(Images.top_left).getHeight() + CFG.PADDING * 2 + ImageManager.getImage(Images.top_flag_frame).getHeight() + CFG.PADDING * 4,
         tempWidth - 4,
         tempHeight,
         menuElements,
         false,
         false
      );
   }

   public final float getImageScale(int nImageHeight) {
      return (CFG.TEXT_HEIGHT + CFG.PADDING * 2) * 0.7F / nImageHeight < 1.0F ? (CFG.TEXT_HEIGHT + CFG.PADDING * 2) * 0.7F / nImageHeight : 1.0F;
   }

   @Override
   public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      oSB.setColor(Color.WHITE);
      ImageManager.getImage(Images.new_game_top_edge_line)
         .draw2(
            oSB,
            this.getPosX() - 2 + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.new_game_top_edge_line).getHeight() + iTranslateY,
            this.getWidth() + 4 - ImageManager.getImage(Images.new_game_top_edge_line).getWidth(),
            this.getHeight(),
            false,
            true
         );
      ImageManager.getImage(Images.new_game_top_edge_line)
         .draw2(
            oSB,
            this.getPosX() + this.getWidth() + 2 - ImageManager.getImage(Images.new_game_top_edge_line).getWidth() + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.new_game_top_edge_line).getHeight() + iTranslateY,
            ImageManager.getImage(Images.new_game_top_edge_line).getWidth(),
            this.getHeight(),
            true,
            true
         );
      oSB.setColor(new Color(CFG.COLOR_GRADIENT_TITLE_BLUE.r, CFG.COLOR_GRADIENT_TITLE_BLUE.g, CFG.COLOR_GRADIENT_TITLE_BLUE.b, 0.275F));
      ImageManager.getImage(Images.gradient)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY,
            this.getWidth(),
            this.getHeight() / 3
         );
      oSB.setColor(new Color(0.025F, 0.025F, 0.025F, 0.225F));
      ImageManager.getImage(Images.slider_gradient)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY,
            this.getWidth() / 8,
            this.getHeight()
         );
      ImageManager.getImage(Images.slider_gradient)
         .draw(
            oSB,
            this.getPosX() + this.getWidth() - this.getWidth() / 8 + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY,
            this.getWidth() / 8,
            this.getHeight(),
            true,
            false
         );
      oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.9F));
      ImageManager.getImage(Images.gradient)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY,
            this.getWidth(),
            this.getHeight() * 4 / 5
         );
      oSB.setColor(new Color(0.01F, 0.02F, 0.04F, 0.65F));
      ImageManager.getImage(Images.gradient)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() + this.getHeight() - CFG.PADDING - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY,
            this.getWidth(),
            CFG.PADDING,
            false,
            true
         );
      oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.85F));
      ImageManager.getImage(Images.pix255_255_255)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() + this.getHeight() - 2 - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY,
            this.getWidth(),
            1
         );
      ImageManager.getImage(Images.pix255_255_255)
         .draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY, this.getWidth(), 1);
      oSB.setColor(new Color(CFG.COLOR_NEW_GAME_EDGE_LINE));
      ImageManager.getImage(Images.pix255_255_255)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() + this.getHeight() - 1 - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY,
            this.getWidth(),
            1
         );
      oSB.setColor(new Color(0.025F, 0.025F, 0.025F, 0.8F));
      ImageManager.getImage(Images.slider_gradient)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() + this.getHeight() - 1 - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY,
            this.getWidth() / 4,
            1
         );
      ImageManager.getImage(Images.slider_gradient)
         .draw(
            oSB,
            this.getPosX() + this.getWidth() - this.getWidth() / 4 + iTranslateX,
            this.getPosY() + this.getHeight() - 1 - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY,
            this.getWidth() / 4,
            1,
            true,
            false
         );
      oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.65F));
      ImageManager.getImage(Images.slider_gradient)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() + this.getHeight() - 1 - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY,
            this.getWidth() / 10,
            1
         );
      ImageManager.getImage(Images.slider_gradient)
         .draw(
            oSB,
            this.getPosX() + this.getWidth() - this.getWidth() / 10 + iTranslateX,
            this.getPosY() + this.getHeight() - 1 - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY,
            this.getWidth() / 10,
            1,
            true,
            false
         );
      oSB.setColor(Color.WHITE);
      super.draw(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
      oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.65F));
      ImageManager.getImage(Images.slider_gradient)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY,
            CFG.PADDING,
            this.getHeight()
         );
      ImageManager.getImage(Images.slider_gradient)
         .draw(
            oSB,
            this.getPosX() + this.getWidth() - CFG.PADDING + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY,
            CFG.PADDING,
            this.getHeight(),
            true,
            false
         );
      oSB.setColor(Color.WHITE);
   }

   @Override
   public void onHovered() {
      CFG.menuManager.setOrderOfMenu_InGame_FlagAction();
   }

   @Override
   public void actionElement(int iID) {
      this.getMenuElement(iID).actionElement(iID);
   }
}
