package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

public class Menu_CreateNewGame_Options_v2 extends SliderMenu {
   public static final int ANIMATION_TIME = 175;
   public static long lTime = 0L;
   public static boolean hideAnimation = true;

   public Menu_CreateNewGame_Options_v2() {
      int tempW = CFG.CIV_INFO_MENU_WIDTH;
      int tempMaxH = CFG.GAME_HEIGHT
         - (ImageManager.getImage(Images.top_left).getHeight() + CFG.PADDING * 2 + CFG.BUTTON_HEIGHT * 3 / 4)
         - (CFG.BUTTON_HEIGHT + CFG.PADDING * 2)
         - CFG.PADDING;
      int tempElemH = CFG.BUTTON_HEIGHT * 3 / 4;
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      menuElements.add(
         new Button_CNG_Options(null, CFG.PADDING * 2 + CFG.map.getIcon(CFG.map.getActiveMapID()).getWidth(), 0, 0, tempW, tempElemH, true) {
            @Override
            public void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
               oSB.setColor(Color.WHITE);
               CFG.map
                  .getIcon(CFG.map.getActiveMapID())
                  .draw(
                     oSB,
                     this.getPosX() + CFG.PADDING + iTranslateX,
                     this.getPosY()
                        + Menu_CreateNewGame_Options_v2.this.getMenuPosY()
                        + this.getHeight() / 2
                        - CFG.map.getIcon(CFG.map.getActiveMapID()).getHeight() / 2
                  );
               super.drawText(oSB, iTranslateX, iTranslateY, isActive);
            }

            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.map.getMapName_Just(CFG.map.getActiveMapID()), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               nData.add(new MenuElement_Hover_v2_Element_Type_Space());
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Provinces") + ": "));
               nData.add(
                  new MenuElement_Hover_v2_Element_Type_Text("" + CFG.map.getMapNumOfProvinces(CFG.map.getActiveMapID()), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE)
               );
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("LandProvinces") + ": "));
               nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + CFG.game.countLandProvinces(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("SeaProvinces") + ": "));
               nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + CFG.game.countSeaProvinces(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("AverageGrowthRateOfProvinces") + ": "));
               nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + CFG.game.countAvarageGrowthRate() + "%", CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
         }
      );
      menuElements.add(new Slider_BG_CNG("", CFG.PADDING * 2, tempElemH + CFG.PADDING, tempW - CFG.PADDING * 4, tempElemH - CFG.PADDING * 2, 0, 25, 0) {
         @Override
         public String getDrawText() {
            return this.getText() + ": " + (int)((1.0F + this.getCurrent() * 0.1F) * 100.0F) + "%";
         }

         @Override
         public void buildElementHover() {
            ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
            ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("DefaultScaleOfMap") + ".", CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
            this.menuElementHover = new MenuElement_Hover_v2(nElements);
         }
      });
      menuElements.add(
         new Button_CNG_Options(null, CFG.PADDING * 2, 0, tempElemH * 2, tempW, tempElemH, true) {
            @Override
            public void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
               super.drawText(oSB, iTranslateX, iTranslateY, isActive);

               for (int i = 0; i < CFG.game.getPlayersSize(); i++) {
                  if (CFG.game.getPlayer(i).getCivID() > 0) {
                     CFG.game
                        .getCiv(CFG.game.getPlayer(i).getCivID())
                        .getFlag()
                        .draw(
                           oSB,
                           this.getTextPos() + (int)(this.getTextWidth() * 0.8F) + CFG.PADDING + CFG.CIV_FLAG_WIDTH * i + CFG.PADDING * i + iTranslateX,
                           this.getPosY()
                              + this.getHeight() / 2
                              - CFG.CIV_FLAG_HEIGHT / 2
                              - CFG.game.getCiv(CFG.game.getPlayer(i).getCivID()).getFlag().getHeight()
                              + iTranslateY,
                           CFG.CIV_FLAG_WIDTH,
                           CFG.CIV_FLAG_HEIGHT
                        );
                     ImageManager.getImage(Images.flag_rect)
                        .draw(
                           oSB,
                           this.getTextPos() + (int)(this.getTextWidth() * 0.8F) + CFG.PADDING + CFG.CIV_FLAG_WIDTH * i + CFG.PADDING * i + iTranslateX,
                           this.getPosY() + this.getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 + iTranslateY
                        );
                     oSB.setColor(
                        new Color(
                           CFG.game.getCiv(CFG.game.getPlayer(i).getCivID()).getR() / 255.0F,
                           CFG.game.getCiv(CFG.game.getPlayer(i).getCivID()).getG() / 255.0F,
                           CFG.game.getCiv(CFG.game.getPlayer(i).getCivID()).getB() / 255.0F,
                           1.0F
                        )
                     );
                     ImageManager.getImage(Images.pix255_255_255)
                        .draw(
                           oSB,
                           this.getTextPos() + (int)(this.getTextWidth() * 0.8F) + CFG.PADDING + CFG.CIV_FLAG_WIDTH * i + CFG.PADDING * i + iTranslateX,
                           this.getPosY() + this.getHeight() + iTranslateY - 2 - (int)(CFG.CIV_COLOR_WIDTH * CFG.GUI_SCALE),
                           CFG.CIV_FLAG_WIDTH,
                           (int)(CFG.CIV_COLOR_WIDTH * CFG.GUI_SCALE)
                        );
                  } else {
                     ImageManager.getImage(Images.randomCivilizationFlag)
                        .draw(
                           oSB,
                           this.getTextPos() + (int)(this.getTextWidth() * 0.8F) + CFG.PADDING + CFG.CIV_FLAG_WIDTH * i + CFG.PADDING * i + iTranslateX,
                           this.getPosY()
                              + this.getHeight() / 2
                              - CFG.CIV_FLAG_HEIGHT / 2
                              - ImageManager.getImage(Images.randomCivilizationFlag).getHeight()
                              + iTranslateY,
                           CFG.CIV_FLAG_WIDTH,
                           CFG.CIV_FLAG_HEIGHT
                        );
                     ImageManager.getImage(Images.flag_rect)
                        .draw(
                           oSB,
                           this.getTextPos() + (int)(this.getTextWidth() * 0.8F) + CFG.PADDING + CFG.CIV_FLAG_WIDTH * i + CFG.PADDING * i + iTranslateX,
                           this.getPosY() + this.getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 + iTranslateY
                        );
                     oSB.setColor(CFG.RANDOM_CIVILIZATION_COLOR);
                     ImageManager.getImage(Images.pix255_255_255)
                        .draw(
                           oSB,
                           this.getTextPos() + (int)(this.getTextWidth() * 0.8F) + CFG.PADDING + CFG.CIV_FLAG_WIDTH * i + CFG.PADDING * i + iTranslateX,
                           this.getPosY() + this.getHeight() + iTranslateY - 2 - (int)(CFG.CIV_COLOR_WIDTH * CFG.GUI_SCALE),
                           CFG.CIV_FLAG_WIDTH,
                           (int)(CFG.CIV_COLOR_WIDTH * CFG.GUI_SCALE)
                        );
                  }

                  oSB.setColor(Color.WHITE);
               }
            }

            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Players") + ": ", CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
               nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + CFG.game.getPlayersSize()));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               nData.add(new MenuElement_Hover_v2_Element_Type_Space());
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();

               for (int i = 0; i < CFG.game.getPlayersSize(); i++) {
                  nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getPlayer(i).getCivID()));
                  nData.add(
                     new MenuElement_Hover_v2_Element_Type_Text(
                        CFG.game.getPlayer(i).getCivID() > 0
                           ? CFG.game.getCiv(CFG.game.getPlayer(i).getCivID()).getCivName()
                           : CFG.langManager.get("RandomCivilization")
                     )
                  );
                  nElements.add(new MenuElement_Hover_v2_Element2(nData));
                  nData.clear();
               }

               this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
         }
      );
      menuElements.add(
         new Button_CNG_Options2(null, CFG.PADDING * 2, 0, tempElemH * 3, tempW, tempElemH, true) {
            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               nData.add(
                  new MenuElement_Hover_v2_Element_Type_Text(
                     CFG.langManager.get(CFG.game.getGameScenarios().getScenarioName(CFG.game.getScenarioID())), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                  )
               );
               nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.time, CFG.PADDING, CFG.PADDING));
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(Game_Calendar.getCurrentDate(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               nData.add(new MenuElement_Hover_v2_Element_Type_Space());
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               nData.add(
                  new MenuElement_Hover_v2_Element_Type_Text(
                     CFG.gameAges.getAge(CFG.game.getGameScenarios().getScenarioAge(CFG.game.getScenarioID())).getName()
                  )
               );
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Civilizations") + ": "));
               nData.add(
                  new MenuElement_Hover_v2_Element_Type_Text(
                     "" + CFG.game.getGameScenarios().getNumOfCivs(CFG.game.getScenarioID()), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                  )
               );
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Author") + ": "));
               nData.add(
                  new MenuElement_Hover_v2_Element_Type_Text(
                     "" + CFG.game.getGameScenarios().getScenarioAuthor(CFG.game.getScenarioID()), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                  )
               );
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
         }
      );
      menuElements.add(
         new Button_CNG_Options2(null, CFG.PADDING * 2, 0, tempElemH * 4, tempW, tempElemH, true) {
            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("VictoryConditions") + ": ", CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Domination")));
               nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.diplo_war, CFG.PADDING, 0));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               nData.add(new MenuElement_Hover_v2_Element_Type_Space());
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("ControlProvinces") + ": "));
               nData.add(
                  new MenuElement_Hover_v2_Element_Type_Text("" + VicotryManager.VICTORY_CONTROL_PROVINCES_PERC + "%", CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE)
               );
               nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.provinces, CFG.PADDING, 0));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("TurnsLimit") + ": "));
               nData.add(
                  new MenuElement_Hover_v2_Element_Type_Text(
                     ""
                        + (
                           VicotryManager.VICTORY_LIMIT_OF_TURNS == 0
                              ? CFG.langManager.get("NoThanks")
                              : CFG.langManager.get("TurnsX", VicotryManager.VICTORY_LIMIT_OF_TURNS)
                        ),
                     CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                  )
               );
               nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.time, CFG.PADDING, 0));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Technology") + ": "));
               nData.add(
                  new MenuElement_Hover_v2_Element_Type_Text(
                     ""
                        + (
                           VicotryManager.VICTORY_TECHNOLOGY == 0.0F
                              ? CFG.langManager.get("Disabled")
                              : (int)(VicotryManager.VICTORY_TECHNOLOGY * 100.0F) / 100.0F
                        ),
                     CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                  )
               );
               nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.technology, CFG.PADDING, 0));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
         }
      );
      menuElements.add(
         new Slider_BG_CNG("", CFG.PADDING * 2, tempElemH * 5 + CFG.PADDING, tempW - CFG.PADDING * 4, tempElemH - CFG.PADDING * 2, 0, 9, CFG.DIFFICULTY * 2 + 1) {
            @Override
            public String getDrawText() {
               return this.getText();
            }

            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("DifficultyLevel") + ": ", CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.getDifficultyName(CFG.DIFFICULTY)));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               nData.add(new MenuElement_Hover_v2_Element_Type_Space());
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Beginner")));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Normal")));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Hard")));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Extreme")));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Legendary")));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
         }
      );
      menuElements.add(
         new Slider_BG_CNG("", CFG.PADDING * 2, tempElemH * 6 + CFG.PADDING, tempW - CFG.PADDING * 4, tempElemH - CFG.PADDING * 2, 0, 5, CFG.FOG_OF_WAR * 2 + 1) {
            @Override
            public String getDrawText() {
               return this.getText();
            }

            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Fogofwar") + ": ", CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.getFogOfWarName(CFG.FOG_OF_WAR)));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               nData.add(new MenuElement_Hover_v2_Element_Type_Space());
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Off") + ": ", CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("TheWholeMapAndSoldiersAreVisibleAtAllTimes") + "."));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Classic") + ": ", CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
               nData.add(
                  new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("ProvinceOwnershipIsKnownButSoldiersCanOnlyBeSeenInAdjacentProvinces") + ".")
               );
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Discovery") + ": ", CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
               nData.add(
                  new MenuElement_Hover_v2_Element_Type_Text(
                     CFG.langManager.get("TheWorldIsCoveredByFogCivilizationsMustBeDiscoveredBeforeTheyCanBeInteractedWith") + "."
                  )
               );
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
         }
      );
      menuElements.add(
         new Button_CNG_Options(null, CFG.PADDING * 2, 0, tempElemH * 21, tempW, tempElemH, true) {
            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               nData.add(
                  new MenuElement_Hover_v2_Element_Type_Text(
                     CFG.langManager.get("ChangeDiplomaticRelationsBetweenCivilizations") + ".", CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                  )
               );
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
         }
      );
      menuElements.add(
         new Button_CNG_Options2(null, CFG.PADDING * 2, 0, tempElemH * 22, tempW, tempElemH, true, CFG.FILL_THE_MAP) {
            @Override
            public boolean getCheckboxState() {
               return CFG.FILL_THE_MAP;
            }

            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               nData.add(
                  new MenuElement_Hover_v2_Element_Type_Text(
                     CFG.langManager.get("IfDisabledAllCivilizationsStartWithOnlyTheirCapitalProvince") + ".", CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                  )
               );
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
         }
      );
      menuElements.add(
         new Button_CNG_Options(null, CFG.PADDING * 2, 0, tempElemH * 24, tempW, tempElemH, true, CFG.RANDOM_PLACMENT) {
            @Override
            public boolean getCheckboxState() {
               return CFG.RANDOM_PLACMENT;
            }

            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               nData.add(
                  new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("PlacesCapitalsInRandomProvinces") + ".", CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE)
               );
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
         }
      );
      menuElements.add(
         new Button_CNG_Options2(null, CFG.PADDING * 2, 0, tempElemH * 25, tempW, tempElemH, true, CFG.RANDOM_FILL) {
            @Override
            public boolean getCheckboxState() {
               return CFG.RANDOM_FILL;
            }

            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               nData.add(
                  new MenuElement_Hover_v2_Element_Type_Text(
                     CFG.langManager.get("RandomnlyFillsTheWorldWithDifferentCivilizations") + ".", CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                  )
               );
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
         }
      );
      menuElements.add(
         new Button_CNG_Options(null, CFG.PADDING * 2, 0, tempElemH * 26, tempW, tempElemH, true) {
            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               nData.add(
                  new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("SwapCivilizationsToRandomPlaces") + ".", CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE)
               );
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
         }
      );
      menuElements.add(new Button_CNG_Options2(null, CFG.PADDING * 2, 0, tempElemH * 27, tempW, tempElemH, true, CFG.SANDBOX_MODE) {
         @Override
         public boolean getCheckboxState() {
            return CFG.SANDBOX_MODE;
         }
      });
      menuElements.add(
         new Slider_BG_CNG("", CFG.PADDING * 2, tempElemH * 28 + CFG.PADDING, tempW - CFG.PADDING * 4, tempElemH - CFG.PADDING * 2, 45, 500, CFG.SANDBOX_TECH) {
            @Override
            public String getDrawText() {
               return this.getText() + this.getCurrent();
            }

            @Override
            public Color getColorLEFT() {
               return new Color(CFG.COLOR_TEXT_RANK.r, CFG.COLOR_TEXT_RANK.g, CFG.COLOR_TEXT_RANK.b, 0.55F);
            }
         }
      );
      menuElements.add(
         new Button_CNG_Options(null, CFG.PADDING * 2, 0, tempElemH * 31, tempW, tempElemH, true, CFG.TOTAL_WAR_MODE) {
            @Override
            public boolean getCheckboxState() {
               return CFG.TOTAL_WAR_MODE;
            }

            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               nData.add(
                  new MenuElement_Hover_v2_Element_Type_Text(
                     CFG.langManager.get("NoneoftheseCivilizationshasthewordforPeaceintheirlanguage") + ".", CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                  )
               );
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
         }
      );
      menuElements.add(
         new Button_CNG_Options2("", CFG.PADDING * 2, 0, tempElemH * 23, tempW, tempElemH, true) {
            @Override
            public void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
               if (CFG.palletManager.getActivePalletID() == 0) {
                  CFG.palletManager
                     .drawSampleColors_Standard(
                        oSB,
                        this.getPosX() + CFG.PADDING * 2 + iTranslateX,
                        this.getPosY() + CFG.PADDING * 2 + iTranslateY,
                        this.getWidth() - CFG.PADDING * 4,
                        this.getHeight() - CFG.PADDING * 4,
                        0,
                        isActive
                     );
               } else {
                  CFG.palletManager
                     .drawSampleColors(
                        oSB,
                        this.getPosX() + CFG.PADDING * 2 + iTranslateX,
                        this.getPosY() + CFG.PADDING * 2 + iTranslateY,
                        this.getWidth() - CFG.PADDING * 4,
                        this.getHeight() - CFG.PADDING * 4,
                        CFG.palletManager.getActivePalletID() - 1,
                        isActive
                     );
               }
            }

            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               nData.add(
                  new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("SetsOfTheColorsForCivilizations") + ".", CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE)
               );
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
         }
      );
      menuElements.add(
         new Button_CNG_Options2(null, CFG.PADDING * 2, 0, tempElemH * 29, tempW, tempElemH, true, CFG.SPECTATOR_MODE) {
            @Override
            public boolean getCheckboxState() {
               return CFG.SPECTATOR_MODE;
            }

            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               nData.add(
                  new MenuElement_Hover_v2_Element_Type_Text(
                     CFG.langManager.get("ObserveCivilizationsAndTheirStruggleForSupremacy") + ".", CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                  )
               );
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
         }
      );
      menuElements.add(
         new Button_CNG_Options(null, CFG.PADDING * 2, 0, tempElemH * 30, tempW, tempElemH, true, Game_Calendar.ENABLE_COLONIZATION) {
            @Override
            public boolean getCheckboxState() {
               return Game_Calendar.ENABLE_COLONIZATION;
            }

            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               nData.add(
                  new MenuElement_Hover_v2_Element_Type_Text(
                     CFG.langManager.get("Enable") + "/" + CFG.langManager.get("Disable") + ": ", CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                  )
               );
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(" " + CFG.langManager.get("ColonizationofWastelandProvinces") + "."));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
         }
      );
      menuElements.add(
         new Button_CNG_Options(null, CFG.PADDING * 2, 0, tempElemH * 32, tempW, tempElemH, true, Game_Calendar.ENABLE_COLONIZATION_NEUTRAL_PROVINCES) {
            @Override
            public boolean getCheckboxState() {
               return Game_Calendar.ENABLE_COLONIZATION_NEUTRAL_PROVINCES;
            }

            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(this.getText(), CFG.COLOR_TEXT_NUM_OF_PROVINCES));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               nData.add(
                  new MenuElement_Hover_v2_Element_Type_Text(
                     CFG.langManager.get("Enable") + "/" + CFG.langManager.get("Disable") + ": ", CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                  )
               );
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("ColonizationofNeutralProvinces") + "."));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
         }
      );
      menuElements.add(
         new Slider_BG_CNG(
            "",
            CFG.PADDING * 2,
            tempElemH * 7 + CFG.PADDING,
            tempW - CFG.PADDING * 4,
            tempElemH - CFG.PADDING * 2,
            (int)(Game_Calendar.GAME_SPEED_MIN * 10.0F),
            (int)(Game_Calendar.GAME_SPEED_MAX * 10.0F),
            (int)(Game_Calendar.GAME_SPEED * 10.0F)
         ) {
            @Override
            public String getDrawText() {
               return this.getText() + this.getCurrent() * 10 + "%";
            }
         }
      );
      menuElements.add(new Button_CNG_Options(null, CFG.PADDING * 2, 0, tempElemH * 8, tempW, tempElemH, true) {});
      menuElements.add(
         new Slider_BG_CNG(
            "",
            CFG.PADDING * 2,
            tempElemH * 9 + CFG.PADDING,
            tempW - CFG.PADDING * 4,
            tempElemH - CFG.PADDING * 2,
            0,
            1000,
            (int)(Game_Calendar.AI_AGGRESSIVNESS * 100.0F)
         ) {
            @Override
            public String getDrawText() {
               return this.getText() + this.getCurrent() + "%";
            }

            @Override
            public Color getColorLEFT() {
               return new Color(CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.r, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.g, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.b, 0.65F);
            }
         }
      );
      menuElements.add(
         new Slider_BG_CNG(
            "", CFG.PADDING * 2, tempElemH * 10 + CFG.PADDING, tempW - CFG.PADDING * 4, tempElemH - CFG.PADDING * 2, 0, 100, 100 - CFG.AI_CREATING_VASSALS
         ) {
            @Override
            public String getDrawText() {
               return this.getText() + this.getCurrent() + "%";
            }

            @Override
            public Color getColorLEFT() {
               return new Color(CFG.COLOR_PORT_1.r, CFG.COLOR_PORT_1.g, CFG.COLOR_PORT_1.b, 0.35F);
            }
         }
      );
      menuElements.add(
         new Slider_BG_CNG(
            "", CFG.PADDING * 2, tempElemH * 11 + CFG.PADDING, tempW - CFG.PADDING * 4, tempElemH - CFG.PADDING * 2, 0, 100, CFG.AI_CREATING_ALLIANCE
         ) {
            @Override
            public String getDrawText() {
               return this.getText() + this.getCurrent() + "%";
            }

            @Override
            public Color getColorLEFT() {
               return new Color(CFG.COLOR_TEXT_POPULATION.r, CFG.COLOR_TEXT_POPULATION.g, CFG.COLOR_TEXT_POPULATION.b, 0.35F);
            }
         }
      );
      menuElements.add(new Button_CNG_Options(null, CFG.PADDING * 2, 0, tempElemH * 12, tempW, tempElemH, true) {});
      menuElements.add(new Slider_BG_CNG("", CFG.PADDING * 2, tempElemH * 13 + CFG.PADDING, tempW - CFG.PADDING * 4, tempElemH - CFG.PADDING * 2, 0, 500, 5) {
         @Override
         public String getDrawText() {
            return this.getText() + this.getCurrent() + " " + CFG.langManager.get("Turn");
         }
      });
      menuElements.add(
         new Slider_BG_CNG(
            "", CFG.PADDING * 2, tempElemH * 14 + CFG.PADDING, tempW - CFG.PADDING * 4, tempElemH - CFG.PADDING * 2, 0, 200, Game_Calendar.SURRENDERLIMIT
         ) {
            @Override
            public String getDrawText() {
               return this.getText() + this.getCurrent() + "%";
            }

            @Override
            public Color getColorLEFT() {
               return new Color(CFG.COLOR_TEXT_RANK.r, CFG.COLOR_TEXT_RANK.g, CFG.COLOR_TEXT_RANK.b, 0.55F);
            }
         }
      );
      menuElements.add(
         new Slider_BG_CNG(
            "",
            CFG.PADDING * 2,
            tempElemH * 15 + CFG.PADDING,
            tempW - CFG.PADDING * 4,
            tempElemH - CFG.PADDING * 2,
            1,
            100,
            (int)(CFG.gameAction.getFrontLength() * 100.0F)
         ) {
            @Override
            public String getDrawText() {
               return this.getText() + this.getCurrent() + "%";
            }

            @Override
            public Color getColorLEFT() {
               return new Color(0.44F, 0.44F, 0.44F, 0.35F);
            }
         }
      );
      menuElements.add(
         new Slider_BG_CNG(
            "",
            CFG.PADDING * 2,
            tempElemH * 16 + CFG.PADDING,
            tempW - CFG.PADDING * 4,
            tempElemH - CFG.PADDING * 2,
            0,
            1000,
            (int)(Game_Calendar.LOSSESINWAR * 100.0F)
         ) {
            @Override
            public String getDrawText() {
               return this.getText() + this.getCurrent() + "%";
            }

            @Override
            public Color getColorLEFT() {
               return new Color(0.20392157F, 0.383F, 0.26666668F, 1.0F);
            }
         }
      );
      menuElements.add(
         new Slider_BG_CNG(
            "",
            CFG.PADDING * 2,
            tempElemH * 17 + CFG.PADDING,
            tempW - CFG.PADDING * 4,
            tempElemH - CFG.PADDING * 2,
            0,
            500,
            (int)(Game_Calendar.POWERREBELS * 100.0F)
         ) {
            @Override
            public String getDrawText() {
               return this.getText() + this.getCurrent() + "%";
            }

            @Override
            public Color getColorLEFT() {
               return new Color(0.5905882F, 0.58882356F, 0.12941177F, 1.0F);
            }
         }
      );
      menuElements.add(new Button_CNG_Options2(null, CFG.PADDING * 2, 0, tempElemH * 18, tempW, tempElemH, true, CFG.MANPOWER_SYSTEM) {
         @Override
         public boolean getCheckboxState() {
            return CFG.MANPOWER_SYSTEM;
         }
      });
      menuElements.add(new Button_CNG_Options2(null, CFG.PADDING * 2, 0, tempElemH * 19, tempW, tempElemH, true, CFG.DISEASES) {
         @Override
         public boolean getCheckboxState() {
            return CFG.DISEASES;
         }
      });
      menuElements.add(new Button_CNG_Options2(null, CFG.PADDING * 2, 0, tempElemH * 20, tempW, tempElemH, true, CFG.DesireForIndependenceVassals) {
         @Override
         public boolean getCheckboxState() {
            return CFG.DesireForIndependenceVassals;
         }
      });
      this.initMenu(
         new SliderMenuTitle(null, CFG.BUTTON_HEIGHT * 3 / 4, false, false) {
            @Override
            public void draw(SpriteBatch oSB, int iTranslateX, int nPosX, int nPosY, int nWidth, boolean sliderMenuIsActive) {
               ImageManager.getImage(Images.new_game_top_edge_title)
                  .draw2(
                     oSB,
                     Menu_CreateNewGame_Options_v2.this.getPosX() + iTranslateX,
                     Menu_CreateNewGame_Options_v2.this.getPosY() - ImageManager.getImage(Images.new_game_top_edge_title).getHeight() - this.getHeight(),
                     Menu_CreateNewGame_Options_v2.this.getWidth() + 2,
                     this.getHeight(),
                     true,
                     false
                  );
               oSB.setColor(new Color(0.011F, 0.014F, 0.019F, 0.25F));
               ImageManager.getImage(Images.gradient)
                  .draw(
                     oSB,
                     Menu_CreateNewGame_Options_v2.this.getPosX() + iTranslateX,
                     Menu_CreateNewGame_Options_v2.this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() - this.getHeight() * 3 / 4,
                     Menu_CreateNewGame_Options_v2.this.getWidth(),
                     this.getHeight() * 3 / 4,
                     false,
                     true
                  );
               oSB.setColor(new Color(0.451F, 0.329F, 0.11F, 1.0F));
               ImageManager.getImage(Images.pix255_255_255)
                  .draw(
                     oSB,
                     Menu_CreateNewGame_Options_v2.this.getPosX() + iTranslateX,
                     Menu_CreateNewGame_Options_v2.this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight(),
                     Menu_CreateNewGame_Options_v2.this.getWidth()
                  );
               oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.4F));
               ImageManager.getImage(Images.line_32_off1)
                  .draw(
                     oSB,
                     Menu_CreateNewGame_Options_v2.this.getPosX() + iTranslateX,
                     Menu_CreateNewGame_Options_v2.this.getPosY()
                        - ImageManager.getImage(Images.pix255_255_255).getHeight()
                        - ImageManager.getImage(Images.line_32_off1).getHeight(),
                     Menu_CreateNewGame_Options_v2.this.getWidth(),
                     1
                  );
               oSB.setColor(Color.WHITE);
               CFG.fontMain.getData().setScale(0.8F);
               CFG.drawText(
                  oSB,
                  this.getText(),
                  nPosX + nWidth / 2 - (int)(this.getTextWidth() * 0.8F / 2.0F) + iTranslateX,
                  nPosY - this.getHeight() + this.getHeight() / 2 + 1 - (int)(this.getTextHeight() * 0.8F / 2.0F),
                  CFG.COLOR_TEXT_OPTIONS_LEFT_NS
               );
               CFG.fontMain.getData().setScale(1.0F);
            }
         },
         0,
         ImageManager.getImage(Images.top_left).getHeight() + CFG.PADDING * 2 + CFG.BUTTON_HEIGHT * 3 / 4,
         tempW,
         tempMaxH < tempElemH * menuElements.size() ? tempMaxH : tempElemH * menuElements.size(),
         menuElements
      );
      this.setVisible(false);
      this.updateLanguage();
   }

   @Override
   public void updateLanguage() {
      this.getTitle().setText(CFG.langManager.get("Options"));
      this.getMenuElement(0).setText(CFG.langManager.get("MapType") + ": " + CFG.map.getMapName(CFG.map.getActiveMapID()));
      this.getMenuElement(1).setText(CFG.langManager.get("ScaleOfMap"));
      this.getMenuElement(2).setText(CFG.langManager.get("Editor") + ":");
      this.getMenuElement(3)
         .setText(
            CFG.langManager.get(CFG.game.getGameScenarios().getScenarioName(CFG.game.getScenarioID()))
               + " | "
               + CFG.game.getGameScenarios().getNumOfCivs(CFG.game.getScenarioID())
               + " "
               + CFG.langManager.get("Civilizations")
         );
      this.getMenuElement(4).setText(CFG.langManager.get("VictoryConditions") + ": " + CFG.langManager.get("Domination"));
      this.getMenuElement(5).setText(CFG.langManager.get("Difficulty") + ": " + CFG.getDifficultyName(CFG.DIFFICULTY));
      this.getMenuElement(6).setText(CFG.langManager.get("Fogofwar") + ": " + CFG.getFogOfWarName(CFG.FOG_OF_WAR));
      this.getMenuElement(7).setText(CFG.langManager.get("ManageDiplomacy"));
      this.getMenuElement(8).setText(CFG.langManager.get("FillTheMap"));
      this.getMenuElement(9).setText(CFG.langManager.get("RandomPlacement"));
      this.getMenuElement(10).setText(CFG.langManager.get("RandomFill"));
      this.getMenuElement(11).setText(CFG.langManager.get("ShuffleCivilizations"));
      this.getMenuElement(12).setText(CFG.langManager.get("SandboxMode"));
      this.getMenuElement(13).setText(CFG.langManager.get("SandboxTech") + ": ");
      this.getMenuElement(14).setText(CFG.langManager.get("EternalWar"));
      this.getMenuElement(16).setText(CFG.langManager.get("SpectatorMode"));
      this.getMenuElement(17).setText(CFG.langManager.get("ColonizationofWastelandProvinces"));
      this.getMenuElement(18)
         .setText(
            CFG.langManager.get("NeutralProvinces")
               + ": "
               + (Game_Calendar.ENABLE_COLONIZATION_NEUTRAL_PROVINCES ? CFG.langManager.get("Colonization") : CFG.langManager.get("Conquering"))
         );
      this.getMenuElement(19).setText(CFG.langManager.get("GameSpeed") + ": ");
      this.getMenuElement(20).setText(CFG.langManager.get("AISettings"));
      this.getMenuElement(21).setText(CFG.langManager.get("AIAggressiveness") + ": ");
      this.getMenuElement(22).setText(CFG.langManager.get("AICreatingVassals") + ": ");
      this.getMenuElement(23).setText(CFG.langManager.get("AICreatingAlliance") + ": ");
      this.getMenuElement(24).setText(CFG.langManager.get("WarSettings"));
      this.getMenuElement(25).setText(CFG.langManager.get("PeaceAfterGameStarts") + ": ");
      this.getMenuElement(26).setText(CFG.langManager.get("SurrenderLimit") + ": ");
      this.getMenuElement(27).setText(CFG.langManager.get("TroopBreakthroughChance") + ": ");
      this.getMenuElement(28).setText(CFG.langManager.get("LossesInWars") + ": ");
      this.getMenuElement(29).setText(CFG.langManager.get("PowerRebels") + ": ");
      this.getMenuElement(30).setText(CFG.langManager.get("ManpowerSystem"));
      this.getMenuElement(31).setText(CFG.langManager.get("Diseases"));
      this.getMenuElement(32).setText(CFG.langManager.get("DesireForIndependenceVassals"));
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
            this.getHeight(),
            true,
            true
         );
      super.draw(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
      oSB.setColor(CFG.COLOR_CREATE_NEW_GAME_BOX_PLAYERS);
      ImageManager.getImage(Images.pix255_255_255)
         .draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + this.getHeight(), this.getWidth());
      oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.4F));
      ImageManager.getImage(Images.line_32_off1)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY()
               - ImageManager.getImage(Images.pix255_255_255).getHeight()
               - ImageManager.getImage(Images.line_32_off1).getHeight()
               + this.getHeight(),
            this.getWidth(),
            1
         );
      oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.5F));
      ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight(), this.getWidth() + 2);
      oSB.setColor(Color.WHITE);
   }

   @Override
   public void drawScrollPos(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      if ((sliderMenuIsActive || this.getScrollModeY()) && !CFG.menuManager.getSliderMode()) {
         super.drawScrollPos(oSB, iTranslateX - 2, iTranslateY, sliderMenuIsActive);
      }
   }

   public static final void clickFillTheMap() {
      CFG.viewsManager.disableAllViews();
      CFG.FILL_THE_MAP = !CFG.FILL_THE_MAP;
      CFG.game.disableDrawCivlizationsRegions_Players();
      if (CFG.FILL_THE_MAP) {
         CFG.game.getGameScenarios().enableFillTheMap();
         CFG.game.setActiveProvinceID(CFG.game.getActiveProvinceID());
      } else {
         CFG.game.getGameScenarios().disableFillTheMap();

         try {
            if (CFG.getActiveCivInfo() > 0) {
               CFG.game.setActiveProvinceID(CFG.game.getCiv(CFG.getActiveCivInfo()).getCapitalProvinceID());
            } else {
               CFG.game.setActiveProvinceID(CFG.game.getActiveProvinceID());
            }
         } catch (IndexOutOfBoundsException var1) {
         }
      }

      CFG.game.enableDrawCivlizationsRegions_Players();
      CFG.setActiveCivInfo(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID());
      CFG.updateActiveCivInfo_CreateNewGame();
   }

   @Override
   public void actionElement(int iID) {
      switch (iID) {
         case 0:
            CFG.backToMenu = Menu.eCREATE_NEW_GAME;
            CFG.menuManager.setViewID(Menu.eSELECT_MAP_TYPE);
            break;
         case 1:
            Map_Scale.STANDARD_SCALE = 1.0F + this.getMenuElement(iID).getCurrent() * 0.1F;
            CFG.map.getMapScale().setCurrentScale(Map_Scale.STANDARD_SCALE);
            CFG.map.getMapScale().setScaleBeforeReset(Map_Scale.STANDARD_SCALE >= 3.0F ? 2.0F : (Map_Scale.STANDARD_SCALE > 1.0F ? 1.0F : 0.5F));
            break;
         case 2:
            CFG.menuManager.setViewID(Menu.eCREATE_SCENARIO_SETTINGS);
            break;
         case 3:
            CFG.menuManager.setVisible_CreateNewGame_Options_Scenarios(true);
            break;
         case 4:
            CFG.backToMenu = Menu.eCREATE_NEW_GAME;
            CFG.menuManager.setViewID(Menu.eVICTORY_CONDITIONS);
            break;
         case 5:
            if (CFG.DIFFICULTY != this.getMenuElement(iID).getCurrent() / 2) {
               CFG.DIFFICULTY = this.getMenuElement(iID).getCurrent() / 2;
               this.getMenuElement(iID).setText(CFG.langManager.get("Difficulty") + ": " + CFG.getDifficultyName(CFG.DIFFICULTY));
            }
            break;
         case 6:
            if (CFG.FOG_OF_WAR != this.getMenuElement(iID).getCurrent() / 2) {
               CFG.FOG_OF_WAR = this.getMenuElement(iID).getCurrent() / 2;
               this.getMenuElement(iID).setText(CFG.langManager.get("Fogofwar") + ": " + CFG.getFogOfWarName(CFG.FOG_OF_WAR));
            }
            break;
         case 7:
            CFG.game.setActiveProvinceID(-1);
            CFG.menuManager.rebuildManageDiplomacy_Alliances();
            CFG.game.disableDrawCivlizationsRegions_Players();
            CFG.chosen_AlphabetCharachter = null;
            CFG.resetManageDiplomacyIDs();
            CFG.backToMenu = Menu.eCREATE_NEW_GAME;
            CFG.menuManager.setViewID(Menu.eMANAGE_DIPLOMACY);
            Game_Render_Province.updateDrawProvinces();
            CFG.map.getMapTouchManager().update_ExtraAction();
            break;
         case 8:
            clickFillTheMap();
            break;
         case 9:
            CFG.RANDOM_PLACMENT = !CFG.RANDOM_PLACMENT;
            this.getMenuElement(iID).setCheckboxState(CFG.RANDOM_PLACMENT);
            break;
         case 10:
            CFG.RANDOM_FILL = !CFG.RANDOM_FILL;
            this.getMenuElement(iID).setCheckboxState(CFG.RANDOM_FILL);
            break;
         case 11:
            CFG.setDialogType(Dialog.SHUFFLE_CIVILIZATIONS);
            break;
         case 12:
            CFG.SANDBOX_MODE = !CFG.SANDBOX_MODE;
            this.getMenuElement(iID).setCheckboxState(CFG.SANDBOX_MODE);
            break;
         case 13:
            CFG.SANDBOX_TECH = this.getMenuElement(iID).getCurrent();
            CFG.toast.setInView(this.getMenuElement(iID).getText());
            break;
         case 14:
            CFG.TOTAL_WAR_MODE = !CFG.TOTAL_WAR_MODE;
            this.getMenuElement(iID).setCheckboxState(CFG.TOTAL_WAR_MODE);
            if (CFG.TOTAL_WAR_MODE) {
               CFG.toast.setInView(CFG.langManager.get("TotalWar") + " - " + CFG.langManager.get("Enabled"));
            } else {
               CFG.toast.setInView(CFG.langManager.get("TotalWar") + " - " + CFG.langManager.get("Disabled"));
            }
            break;
         case 15:
            CFG.menuManager.setVisible_CreateNewGame_Options_Pallets(true);
            break;
         case 16:
            CFG.SPECTATOR_MODE = !CFG.SPECTATOR_MODE;
            this.getMenuElement(iID).setCheckboxState(CFG.SPECTATOR_MODE);
            break;
         case 17:
            Game_Calendar.ENABLE_COLONIZATION = !Game_Calendar.ENABLE_COLONIZATION;
            if (Game_Calendar.ENABLE_COLONIZATION) {
               CFG.toast.setInView(CFG.langManager.get("Colonization") + " - " + CFG.langManager.get("Enabled"));
            } else {
               CFG.toast.setInView(CFG.langManager.get("Colonization") + " - " + CFG.langManager.get("Disabled"));
            }
            break;
         case 18:
            Game_Calendar.ENABLE_COLONIZATION_NEUTRAL_PROVINCES = !Game_Calendar.ENABLE_COLONIZATION_NEUTRAL_PROVINCES;
            this.updateLanguage();
            CFG.toast.setInView(this.getMenuElement(iID).getText());
            break;
         case 19:
            Game_Calendar.GAME_SPEED = this.getMenuElement(iID).getCurrent() / 10.0F;
            CFG.toast.setInView(this.getMenuElement(iID).getText());
         case 20:
         case 24:
         default:
            break;
         case 21:
            Game_Calendar.AI_AGGRESSIVNESS = this.getMenuElement(iID).getCurrent() / 100.0F;
            CFG.toast.setInView(this.getMenuElement(iID).getText());
            break;
         case 22:
            CFG.AI_CREATING_VASSALS = 100 - this.getMenuElement(iID).getCurrent();
            CFG.toast.setInView(this.getMenuElement(iID).getText());
            break;
         case 23:
            CFG.AI_CREATING_ALLIANCE = this.getMenuElement(iID).getCurrent();
            CFG.toast.setInView(this.getMenuElement(iID).getText());
            break;
         case 25:
            Game_Calendar.PeaceAfterGameStarts = this.getMenuElement(iID).getCurrent();
            CFG.toast.setInView(this.getMenuElement(iID).getText());
            break;
         case 26:
            Game_Calendar.SURRENDERLIMIT = this.getMenuElement(iID).getCurrent();
            CFG.toast.setInView(this.getMenuElement(iID).getText());
            break;
         case 27:
            Game_Action.fTroopBreakthroughChance = this.getMenuElement(iID).getCurrent() / 100.0F;
            CFG.toast.setInView(this.getMenuElement(iID).getText());
            break;
         case 28:
            Game_Calendar.LOSSESINWAR = this.getMenuElement(iID).getCurrent() / 100.0F;
            CFG.toast.setInView(this.getMenuElement(iID).getText());
            break;
         case 29:
            Game_Calendar.POWERREBELS = this.getMenuElement(iID).getCurrent() / 100.0F;
            CFG.toast.setInView(this.getMenuElement(iID).getText());
            break;
         case 30:
            CFG.MANPOWER_SYSTEM = !CFG.MANPOWER_SYSTEM;
            this.getMenuElement(iID).setCheckboxState(CFG.MANPOWER_SYSTEM);
            break;
         case 31:
            CFG.DISEASES = !CFG.DISEASES;
            this.getMenuElement(iID).setCheckboxState(CFG.DISEASES);
            break;
         case 32:
            CFG.DesireForIndependenceVassals = !CFG.DesireForIndependenceVassals;
            this.getMenuElement(iID).setCheckboxState(CFG.DesireForIndependenceVassals);
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
