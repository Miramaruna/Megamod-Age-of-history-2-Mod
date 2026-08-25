package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;
import java.util.List;

public class Menu_InGame_ProvinceInfo extends SliderMenu {
   public static int iMaxWidth = 1;
   public Menu_InGame_ProvinceInfo.Box box;
   public String sTurn = "";
   public int iTurnWidth = 0;
   public static final float TURN_SCALE = 0.8F;
   public static List<Integer> lBuildingsImages = new ArrayList<>();
   public static int iBuildingsWidth = 0;

   public final void updateTurnWidth() {
   }

   public Menu_InGame_ProvinceInfo() {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      this.box = new Menu_InGame_ProvinceInfo.Box();
      this.box.iProvinceName = CFG.XXXXHDPI ? 7 : (CFG.XXXHDPI ? 6 : (CFG.XXHDPI ? 6 : (CFG.XHDPI ? 5 : 4)));
      CFG.glyphLayout.setText(CFG.fontMain, CFG.langManager.get("NextTurn"));
      int tempWidth = CFG.glyphLayout.width + CFG.PADDING * 4 > CFG.BUTTON_WIDTH ? (int)(CFG.glyphLayout.width + CFG.PADDING * 4) : CFG.BUTTON_WIDTH;
      menuElements.add(
         new Button_Game_NextTurn(null, -1, CFG.GAME_WIDTH - tempWidth - CFG.PADDING - CFG.map.getMapBG().getMinimapWidth(), CFG.PADDING, tempWidth, true) {
            @Override
            public void buildElementHover() {
               if (CFG.isDesktop()) {
                  ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
                  ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
                  nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Shortcut") + ": "));
                  nData.add(new MenuElement_Hover_v2_Element_Type_Text("SPACE", CFG.COLOR_TEXT_NUM_OF_PROVINCES));
                  nElements.add(new MenuElement_Hover_v2_Element2(nData));
                  nData.clear();
                  this.menuElementHover = new MenuElement_Hover_v2(nElements);
               } else {
                  this.menuElementHover = null;
               }
            }

            @Override
            public void drawMenuElementHover2(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
               if (this.menuElementHover != null) {
                  this.menuElementHover.drawAlwaysOver(oSB, Touch.getMousePosX(), CFG.GAME_HEIGHT - CFG.map.getMapBG().getMinimapHeight());
               }
            }

            @Override
            public boolean getClickable() {
               return super.getClickable()
                  && CFG.chosenProvinceID < 0
                  && !CFG.menuManager.getInGame_ProvinceRecruit_Visible()
                  && !CFG.menuManager.getInGame_ProvinceDisband_Visible()
                  && !CFG.menuManager.getInGame_ProvinceRecruitInstantly_Visible();
            }

            @Override
            public void setText(String sText) {
               super.setText(sText);
               Menu_InGame_ProvinceInfo.this.updateTurnWidth();
            }
         }
      );
      menuElements.add(
         new Text(
            "",
            CFG.PADDING,
            CFG.GAME_WIDTH - CFG.map.getMapBG().getMinimapWidth() * 2 - CFG.PADDING * 3 - tempWidth,
            CFG.BUTTON_HEIGHT + CFG.PADDING * 2 - (int)(CFG.TEXT_HEIGHT * 0.8F) - CFG.PADDING * 2,
            CFG.PADDING * 2,
            (int)(CFG.TEXT_HEIGHT * 0.8F) + CFG.PADDING * 2
         ) {
            @Override
            public int getPosX() {
               return Menu_InGame_ProvinceInfo.this.getMenuElement(0).getPosX() - CFG.PADDING * 2 - CFG.iProvinceNameWidth;
            }

            @Override
            public int getWidth() {
               return CFG.PADDING * 2 + CFG.iProvinceNameWidth;
            }

            @Override
            public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
               CFG.fontMain.getData().setScale(0.8F);
               CFG.drawText(oSB, this.getText(), this.getPosX() + iTranslateX, this.getPosY() + CFG.PADDING + iTranslateY, this.getColor(isActive));
               CFG.fontMain.getData().setScale(1.0F);
            }

            @Override
            public Color getColor(boolean isActive) {
               return isActive
                  ? new Color(0.56F, 0.56F, 0.56F, 1.0F)
                  : (
                     this.getClickable()
                        ? (this.getIsHovered() ? new Color(0.78F, 0.78F, 0.78F, 1.0F) : new Color(0.92F, 0.92F, 0.92F, 1.0F))
                        : new Color(0.78F, 0.78F, 0.78F, 0.7F)
                  );
            }

            @Override
            public void buildElementHover() {
               try {
                  ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
                  ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
                  if (CFG.ACTIVE_PROVINCE_INFO < 0 || CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getName().length() <= 0) {
                     this.menuElementHover = null;
                     return;
                  }

                  nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("ProvinceName") + ": "));
                  nData.add(
                     new MenuElement_Hover_v2_Element_Type_Text(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getName(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE)
                  );
                  if (CFG.FOG_OF_WAR == 2) {
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Flag(
                           CFG.getMetCiv(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getCivID())
                              ? CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getCivID()
                              : -1,
                           CFG.PADDING,
                           0
                        )
                     );
                  } else {
                     nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getCivID(), CFG.PADDING, 0));
                  }

                  nElements.add(new MenuElement_Hover_v2_Element2(nData));
                  nData.clear();
                  nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Continent") + ": "));
                  nData.add(
                     new MenuElement_Hover_v2_Element_Type_Text(
                        CFG.map.getMapContinents().getName(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getContinent()),
                        CFG.map.getMapContinents().getColor(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getContinent())
                     )
                  );
                  nElements.add(new MenuElement_Hover_v2_Element2(nData));
                  nData.clear();
                  if (CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getMountainsSize() > 0) {
                     for (int i = 0; i < CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getMountainsSize(); i++) {
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Mountain") + ": "));
                        nData.add(
                           new MenuElement_Hover_v2_Element_Type_Text(
                              CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getMountain(i).getName() + " ", CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                           )
                        );
                        nData.add(
                           new MenuElement_Hover_v2_Element_Type_Text(
                              ""
                                 + CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getMountain(i).getElevation()
                                 + "m / "
                                 + CFG.getMetersToFeet(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getMountain(i).getElevation())
                                 + "ft",
                              CFG.COLOR_TEXT_MODIFIER_NEUTRAL
                           )
                        );
                        nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.mount, CFG.PADDING, 0));
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                     }
                  }

                  if (CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getLevelOfFort() > 0
                     || CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getLevelOfWatchTower() > 0
                     || CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getLevelOfPort() > 0
                     || CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getLevelOfLibrary() > 0
                     || CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getLevelOfFarm() > 0
                     || CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getLevelOfWorkshop() > 0
                     || CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getLevelOfArmoury() > 0
                     || CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getLevelOfSupply() > 0) {
                     nData.add(new MenuElement_Hover_v2_Element_Type_Space());
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                  }

                  if (CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getLevelOfFort() > 0) {
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           CFG.langManager.get(BuildingsManager.getFort_Name(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getLevelOfFort())),
                           CFG.COLOR_TEXT_NUM_OF_PROVINCES
                        )
                     );
                     nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.b_fort, CFG.PADDING, CFG.PADDING));
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Level") + ": ", CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           "" + CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getLevelOfFort(), CFG.COLOR_TEXT_NUM_OF_PROVINCES
                        )
                     );
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(" - "));
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           CFG.langManager.get("HidesTheArmyFromTheSightOfViewOfWatchTower"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                        )
                     );
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(" - "));
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("DefenseBonus") + ": "));
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           "+" + BuildingsManager.getFort_DefenseBonus(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getLevelOfFort()) + "%",
                           CFG.COLOR_TEXT_MODIFIER_POSITIVE
                        )
                     );
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                  }

                  if (CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getLevelOfWatchTower() > 0) {
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           CFG.langManager.get(BuildingsManager.getTower_Name(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getLevelOfWatchTower())),
                           CFG.COLOR_TEXT_NUM_OF_PROVINCES
                        )
                     );
                     nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.b_tower, CFG.PADDING, CFG.PADDING));
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Level") + ": ", CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           "" + CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getLevelOfWatchTower(), CFG.COLOR_TEXT_NUM_OF_PROVINCES
                        )
                     );
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(" - "));
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           CFG.langManager.get("AllowsToSeeTheArmyInNeighboringProvinces"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                        )
                     );
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(" - "));
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("DefenseBonus") + ": "));
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           "+" + BuildingsManager.getTower_DefenseBonus(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getLevelOfWatchTower()) + "%",
                           CFG.COLOR_TEXT_MODIFIER_POSITIVE
                        )
                     );
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                  }

                  if (CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getLevelOfPort() > 0) {
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           CFG.langManager.get(BuildingsManager.getPort_Name(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getLevelOfPort())),
                           CFG.COLOR_TEXT_NUM_OF_PROVINCES
                        )
                     );
                     nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.b_port, CFG.PADDING, CFG.PADDING));
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Level") + ": ", CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           "" + CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getLevelOfPort(), CFG.COLOR_TEXT_NUM_OF_PROVINCES
                        )
                     );
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(" - "));
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("AllowsYourArmyGoToTheSea"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                     nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.icon_move_sea, CFG.PADDING, 0));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(" - "));
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("IncomeProduction") + ": "));
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           "+"
                              + (int)(BuildingsManager.getPort_IncomeProduction(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getLevelOfPort()) * 100.0F)
                              + "%",
                           CFG.COLOR_TEXT_MODIFIER_POSITIVE
                        )
                     );
                     nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.economy, CFG.PADDING, 0));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                  }

                  if (CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getLevelOfLibrary() > 0) {
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           CFG.langManager.get(BuildingsManager.getLibrary_Name(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getLevelOfLibrary())),
                           CFG.COLOR_TEXT_NUM_OF_PROVINCES
                        )
                     );
                     nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.b_library, CFG.PADDING, CFG.PADDING));
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Level") + ": ", CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           "" + CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getLevelOfLibrary(), CFG.COLOR_TEXT_NUM_OF_PROVINCES
                        )
                     );
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(" - "));
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("+1"), CFG.COLOR_TEXT_RESEARCH));
                     nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.research, CFG.PADDING, CFG.PADDING));
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           CFG.langManager
                              .get(
                                 "ResearchPerTurnForEveryXPeopleInProvince",
                                 BuildingsManager.getLibrary_ResearchPerPopulation(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getLevelOfLibrary())
                              ),
                           CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                        )
                     );
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                  }

                  if (CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getLevelOfFarm() > 0) {
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           CFG.langManager.get(BuildingsManager.getFarm_Name(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getLevelOfFarm())),
                           CFG.COLOR_TEXT_NUM_OF_PROVINCES
                        )
                     );
                     nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.b_farm, CFG.PADDING, 0));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(" - "));
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("GrowthRate") + ": "));
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           "+"
                              + (int)(BuildingsManager.getFarm_GrowthRateBonus(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getLevelOfFarm()) * 100.0F)
                              + "%",
                           CFG.COLOR_TEXT_MODIFIER_POSITIVE
                        )
                     );
                     nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.population_growth, CFG.PADDING, 0));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                  }

                  if (CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getLevelOfWorkshop() > 0) {
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           CFG.langManager.get(BuildingsManager.getWorkshop_Name(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getLevelOfWorkshop())),
                           CFG.COLOR_TEXT_NUM_OF_PROVINCES
                        )
                     );
                     nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.b_workshop, CFG.PADDING, CFG.PADDING));
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Level") + ": ", CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           "" + CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getLevelOfWorkshop(), CFG.COLOR_TEXT_NUM_OF_PROVINCES
                        )
                     );
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(" - "));
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("IncomeProduction") + ": "));
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           "+"
                              + (int)(
                                 BuildingsManager.getWorkshop_IncomeProduction(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getLevelOfWorkshop()) * 100.0F
                              )
                              + "%",
                           CFG.COLOR_TEXT_MODIFIER_POSITIVE
                        )
                     );
                     nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.population_growth, CFG.PADDING, 0));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                  }

                  if (CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getLevelOfArmoury() > 0) {
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           CFG.langManager.get(BuildingsManager.getArmoury_Name(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getLevelOfArmoury())),
                           CFG.COLOR_TEXT_NUM_OF_PROVINCES
                        )
                     );
                     nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.b_armoury, CFG.PADDING, CFG.PADDING));
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Level") + ": ", CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           "" + CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getLevelOfArmoury(), CFG.COLOR_TEXT_NUM_OF_PROVINCES
                        )
                     );
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(" - "));
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           CFG.langManager.get("ReducesTheCostOfRecruitmentPerUnitByOneGold"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                        )
                     );
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                  }

                  if (CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getLevelOfSupply() > 0) {
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           CFG.langManager.get(BuildingsManager.getSupply_Name(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getLevelOfSupply())),
                           CFG.COLOR_TEXT_NUM_OF_PROVINCES
                        )
                     );
                     nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.b_supply, CFG.PADDING, CFG.PADDING));
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Level") + ": ", CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           "" + CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getLevelOfSupply(), CFG.COLOR_TEXT_NUM_OF_PROVINCES
                        )
                     );
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(" - "));
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("MilitaryUpkeep") + ": "));
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           "-" + (int)(BuildingsManager.getSupply_Bonus(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getLevelOfSupply()) * 100.0F) + "%",
                           CFG.COLOR_TEXT_MODIFIER_POSITIVE
                        )
                     );
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                  }

                  this.menuElementHover = new MenuElement_Hover_v2(nElements);
               } catch (IndexOutOfBoundsException var4) {
               }
            }

            @Override
            public void drawMenuElementHover2(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
               if (this.menuElementHover != null) {
                  this.menuElementHover.drawAlwaysOver(oSB, Touch.getMousePosX(), CFG.GAME_HEIGHT - CFG.map.getMapBG().getMinimapHeight());
               }
            }
         }
      );
      menuElements.add(
         new Button_BotBar_Flag("", 0.85F, 1, 1, 1, true, true) {
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
            public void setText(String sText) {
               if (sText.length() > (CFG.isAndroid() ? 20 : 30)) {
                  sText = sText.substring(0, Math.min(CFG.isAndroid() ? 20 : 30, sText.length() - 1));
               }

               super.setText(sText);
            }

            @Override
            public void buildElementHover() {
               if (this.getCurrent() > 0) {
                  try {
                     ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
                     ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
                     if (CFG.ACTIVE_PROVINCE_INFO >= 0) {
                        nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getCivID()));
                     }

                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           CFG.langManager.get("OpenCloseCivilizationInformationsView") + ".", CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                        )
                     );
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                     if (CFG.ACTIVE_PROVINCE_INFO >= 0) {
                        nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getCivID()));
                        nData.add(
                           new MenuElement_Hover_v2_Element_Type_Text(CFG.game.getCiv(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getCivID()).getCivName())
                        );
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                     }

                     if (CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).isOccupied()) {
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("RightfulOwner") + ": "));
                        nData.add(
                           new MenuElement_Hover_v2_Element_Type_Text(
                              CFG.game.getCiv(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getTrueOwnerOfProvince()).getCivName(),
                              CFG.COLOR_TEXT_NUM_OF_PROVINCES
                           )
                        );
                        nData.add(
                           new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getTrueOwnerOfProvince(), CFG.PADDING, 0)
                        );
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("OccupiedBy") + ": "));
                        nData.add(
                           new MenuElement_Hover_v2_Element_Type_Text(
                              CFG.game.getCiv(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getCivID()).getCivName(), CFG.COLOR_TEXT_NUM_OF_PROVINCES
                           )
                        );
                        nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getCivID(), CFG.PADDING, 0));
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                     }

                     if (CFG.isDesktop()) {
                        nData.add(new MenuElement_Hover_v2_Element_Type_Space());
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Shortcut") + ": "));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text("F2, TAB", CFG.COLOR_TEXT_NUM_OF_PROVINCES));
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                     }

                     this.menuElementHover = new MenuElement_Hover_v2(nElements);
                  } catch (IndexOutOfBoundsException var7) {
                     this.menuElementHover = null;
                  }
               } else if (this.getCurrent() == 0) {
                  try {
                     ArrayList<MenuElement_Hover_v2_Element2> nElementsx = new ArrayList<>();
                     ArrayList<MenuElement_Hover_v2_Element_Type> nDatax = new ArrayList<>();
                     if (CFG.ACTIVE_PROVINCE_INFO >= 0) {
                        nDatax.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getCivID()));
                     }

                     nDatax.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           CFG.langManager.get("OpenCloseCivilizationInformationsView") + ".", CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                        )
                     );
                     nElementsx.add(new MenuElement_Hover_v2_Element2(nDatax));
                     nDatax.clear();
                     if (CFG.isDesktop()) {
                        nDatax.add(new MenuElement_Hover_v2_Element_Type_Space());
                        nElementsx.add(new MenuElement_Hover_v2_Element2(nDatax));
                        nDatax.clear();
                        nDatax.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Shortcut") + ": "));
                        nDatax.add(new MenuElement_Hover_v2_Element_Type_Text("F2, TAB", CFG.COLOR_TEXT_NUM_OF_PROVINCES));
                        nElementsx.add(new MenuElement_Hover_v2_Element2(nDatax));
                        nDatax.clear();
                     }

                     this.menuElementHover = new MenuElement_Hover_v2(nElementsx);
                  } catch (IndexOutOfBoundsException var6) {
                     this.menuElementHover = null;
                  }
               } else if (this.getCurrent() == -1) {
                  try {
                     ArrayList<MenuElement_Hover_v2_Element2> nElementsxx = new ArrayList<>();
                     ArrayList<MenuElement_Hover_v2_Element_Type> nDataxx = new ArrayList<>();
                     nDataxx.add(new MenuElement_Hover_v2_Element_Type_Terrain(0));
                     nDataxx.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Sea"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                     nElementsxx.add(new MenuElement_Hover_v2_Element2(nDataxx));
                     nDataxx.clear();
                     this.menuElementHover = new MenuElement_Hover_v2(nElementsxx);
                  } catch (IndexOutOfBoundsException var5) {
                     this.menuElementHover = null;
                  }
               } else if (this.getCurrent() == -2) {
                  try {
                     ArrayList<MenuElement_Hover_v2_Element2> nElementsxx = new ArrayList<>();
                     ArrayList<MenuElement_Hover_v2_Element_Type> nDataxx = new ArrayList<>();
                     nDataxx.add(new MenuElement_Hover_v2_Element_Type_Image(Images.randomCivilizationFlag));
                     nDataxx.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Wasteland"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                     nElementsxx.add(new MenuElement_Hover_v2_Element2(nDataxx));
                     nDataxx.clear();
                     this.menuElementHover = new MenuElement_Hover_v2(nElementsxx);
                  } catch (IndexOutOfBoundsException var4) {
                     this.menuElementHover = null;
                  }
               } else if (this.getCurrent() == -3) {
                  try {
                     ArrayList<MenuElement_Hover_v2_Element2> nElementsxx = new ArrayList<>();
                     ArrayList<MenuElement_Hover_v2_Element_Type> nDataxx = new ArrayList<>();
                     nDataxx.add(new MenuElement_Hover_v2_Element_Type_Image(Images.randomCivilizationFlag));
                     nDataxx.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("UndiscoveredProvince"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                     nElementsxx.add(new MenuElement_Hover_v2_Element2(nDataxx));
                     nDataxx.clear();
                     this.menuElementHover = new MenuElement_Hover_v2(nElementsxx);
                  } catch (IndexOutOfBoundsException var3) {
                     this.menuElementHover = null;
                  }
               }
            }

            @Override
            public void drawMenuElementHover2(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
               if (this.menuElementHover != null) {
                  this.menuElementHover.drawAlwaysOver(oSB, Touch.getMousePosX(), CFG.GAME_HEIGHT - CFG.map.getMapBG().getMinimapHeight());
               }
            }

            @Override
            public int getSFX() {
               return SoundsManager.SOUND_CLICK2;
            }
         }
      );
      menuElements.add(
         new Button_BotBar_Cores(CFG.langManager.get("Cores") + ":", 0.8F, 1, 1, 1, true, true) {
            @Override
            public Color getColor(boolean isActive) {
               return isActive
                  ? CFG.COLOR_TEXT_OPTIONS_NS_ACTIVE
                  : (
                     this.getClickable()
                        ? (this.getIsHovered() ? CFG.COLOR_TEXT_OPTIONS_NS_HOVER : CFG.COLOR_TEXT_OPTIONS_NS)
                        : CFG.COLOR_BUTTON_MENU_TEXT_NOT_CLICKABLE
                  );
            }

            @Override
            public void buildElementHover() {
               try {
                  ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
                  ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
                  nData.add(
                     new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("CoreIsALegitimatePartOfCivilization"), CFG.COLOR_TEXT_NUM_OF_PROVINCES)
                  );
                  nElements.add(new MenuElement_Hover_v2_Element2(nData));
                  nData.clear();
                  nData.add(new MenuElement_Hover_v2_Element_Type_Space());
                  nElements.add(new MenuElement_Hover_v2_Element2(nData));
                  nData.clear();
                  nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Cores") + ":", CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                  if (CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getCore().getCivsSize() > 0) {
                     for (int i = 0; i < CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getCore().getCivsSize(); i++) {
                        nData.add(
                           new MenuElement_Hover_v2_Element_Type_Flag(
                              CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getCore().getCivID(i), i == 0 ? CFG.PADDING : 0, 0
                           )
                        );
                     }
                  } else {
                     nData.add(new MenuElement_Hover_v2_Element_Type_Flag(-1, CFG.PADDING, 0));
                  }

                  nElements.add(new MenuElement_Hover_v2_Element2(nData));
                  nData.clear();
                  if (CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getCore().getCivsSize() <= 0) {
                     if (CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getCore().getOwnership_CivsSize() > 0) {
                        for (int i = 0; i < CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getCore().getOwnership_CivsSize(); i++) {
                           if (!CFG.game
                              .getProvince(CFG.ACTIVE_PROVINCE_INFO)
                              .getCore()
                              .getHaveACore(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getCore().getOwnership_CivID(i))) {
                              nData.add(
                                 new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getCore().getOwnership_CivID(i))
                              );
                              nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("CoreConstruction") + ": "));
                              nData.add(
                                 new MenuElement_Hover_v2_Element_Type_Text(
                                    ""
                                       + Math.min(
                                          (int)(
                                             (float)CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getCore().getOwnership_NumOfTurns(i)
                                                / CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getCore().getNumOfTurnsOwnershipToGetACore()
                                                * 100.0F
                                          ),
                                          99
                                       )
                                       + "%",
                                    CFG.COLOR_TEXT_NUM_OF_PROVINCES
                                 )
                              );
                              nData.add(
                                 new MenuElement_Hover_v2_Element_Type_Text(
                                    " "
                                       + Game_Calendar.getDate_ByTurnID(
                                          Game_Calendar.TURN_ID
                                             + Math.max(
                                                1,
                                                CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getCore().getNumOfTurnsOwnershipToGetACore()
                                                   - CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getCore().getOwnership_NumOfTurns(i)
                                             )
                                       ),
                                    CFG.COLOR_TEXT_MODIFIER_NEUTRAL
                                 )
                              );
                              nData.add(
                                 new MenuElement_Hover_v2_Element_Type_Text(
                                    " ["
                                       + CFG.langManager
                                          .get(
                                             "TurnsX",
                                             Math.max(
                                                1,
                                                CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getCore().getNumOfTurnsOwnershipToGetACore()
                                                   - CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getCore().getOwnership_NumOfTurns(i)
                                             )
                                          )
                                       + "]",
                                    CFG.COLOR_TEXT_RANK_HOVER
                                 )
                              );
                              nElements.add(new MenuElement_Hover_v2_Element2(nData));
                              nData.clear();
                           }
                        }
                     }
                  } else {
                     for (int ix = 0; ix < CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getCore().getCivsSize(); ix++) {
                        nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getCore().getCivID(ix)));
                        nData.add(
                           new MenuElement_Hover_v2_Element_Type_Text(
                              CFG.game.getCiv(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getCore().getCivID(ix)).getCivName() + ", ",
                              CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                           )
                        );
                        nData.add(
                           new MenuElement_Hover_v2_Element_Type_Text(
                              Game_Calendar.getDate_ByTurnID(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getCore().getSinceTurnID(ix)),
                              CFG.COLOR_TEXT_MODIFIER_NEUTRAL
                           )
                        );
                        nData.add(
                           new MenuElement_Hover_v2_Element_Type_Text(
                              " [" + CFG.langManager.get("Turn") + ": " + CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getCore().getSinceTurnID(ix) + "]",
                              CFG.COLOR_TEXT_RANK_HOVER
                           )
                        );
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                     }

                     for (int var6 = 0; var6 < CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getCore().getOwnership_CivsSize(); var6++) {
                        if (!CFG.game
                           .getProvince(CFG.ACTIVE_PROVINCE_INFO)
                           .getCore()
                           .getHaveACore(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getCore().getOwnership_CivID(var6))) {
                           nData.add(
                              new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getCore().getOwnership_CivID(var6))
                           );
                           nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("CoreConstruction") + ": "));
                           nData.add(
                              new MenuElement_Hover_v2_Element_Type_Text(
                                 ""
                                    + Math.min(
                                       (int)(
                                          (float)CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getCore().getOwnership_NumOfTurns(var6)
                                             / CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getCore().getNumOfTurnsOwnershipToGetACore()
                                             * 100.0F
                                       ),
                                       99
                                    )
                                    + "%",
                                 CFG.COLOR_TEXT_NUM_OF_PROVINCES
                              )
                           );
                           nData.add(
                              new MenuElement_Hover_v2_Element_Type_Text(
                                 " "
                                    + Game_Calendar.getDate_ByTurnID(
                                       Game_Calendar.TURN_ID
                                          + Math.max(
                                             1,
                                             CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getCore().getNumOfTurnsOwnershipToGetACore()
                                                - CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getCore().getOwnership_NumOfTurns(var6)
                                          )
                                    ),
                                 CFG.COLOR_TEXT_MODIFIER_NEUTRAL
                              )
                           );
                           nData.add(
                              new MenuElement_Hover_v2_Element_Type_Text(
                                 " ["
                                    + CFG.langManager
                                       .get(
                                          "TurnsX",
                                          Math.max(
                                             1,
                                             CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getCore().getNumOfTurnsOwnershipToGetACore()
                                                - CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getCore().getOwnership_NumOfTurns(var6)
                                          )
                                       )
                                    + "]",
                                 CFG.COLOR_TEXT_RANK_HOVER
                              )
                           );
                           nElements.add(new MenuElement_Hover_v2_Element2(nData));
                           nData.clear();
                        }
                     }
                  }

                  this.menuElementHover = new MenuElement_Hover_v2(nElements);
               } catch (IndexOutOfBoundsException var4) {
                  this.menuElementHover = null;
               }
            }

            @Override
            public void drawMenuElementHover2(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
               if (this.menuElementHover != null) {
                  this.menuElementHover.drawAlwaysOver(oSB, Touch.getMousePosX(), CFG.GAME_HEIGHT - CFG.map.getMapBG().getMinimapHeight());
               }
            }
         }
      );
      int tierHeight = (int)Math.floor((CFG.map.getMapBG().getMinimapHeight() - menuElements.get(2).getHeight() - 2 - CFG.PADDING * 3) / 2);
      menuElements.add(
         new Button_Stats_Image_Population(
            "", 0.85F, CFG.PADDING, menuElements.get(2).getPosY() + menuElements.get(2).getHeight() + CFG.PADDING, 1, tierHeight, true, true
         ) {
            @Override
            public Color getColor(boolean isActive) {
               return isActive
                  ? CFG.COLOR_TEXT_POPULATION_ACTIVE
                  : (
                     this.getClickable()
                        ? (this.getIsHovered() ? CFG.COLOR_TEXT_POPULATION_HOVER : CFG.COLOR_TEXT_POPULATION)
                        : new Color(0.78F, 0.78F, 0.78F, 0.7F)
                  );
            }

            @Override
            public void buildElementHover() {
               try {
                  ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
                  ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
                  if (CFG.ACTIVE_PROVINCE_INFO < 0) {
                     this.menuElementHover = null;
                     return;
                  }

                  if (CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getName().length() > 0) {
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getName(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE)
                     );
                     nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getCivID(), CFG.PADDING, 0));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                  }

                  nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Population") + ": "));
                  nData.add(
                     new MenuElement_Hover_v2_Element_Type_Text(
                        CFG.getNumberWithSpaces("" + CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getPopulationData().getPopulation()),
                        CFG.COLOR_TEXT_POPULATION
                     )
                  );
                  if (CFG.game.showTurnChangesInformation(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getCivID())) {
                     nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.population, CFG.PADDING, CFG.PADDING));
                     if (CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).saveProvinceData.turnChange_Population > 0) {
                        nData.add(
                           new MenuElement_Hover_v2_Element_Type_Text(
                              "+" + CFG.getNumberWithSpaces("" + CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).saveProvinceData.turnChange_Population),
                              CFG.COLOR_TEXT_MODIFIER_POSITIVE
                           )
                        );
                     } else if (CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).saveProvinceData.turnChange_Population < 0) {
                        nData.add(
                           new MenuElement_Hover_v2_Element_Type_Text(
                              CFG.getNumberWithSpaces("" + CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).saveProvinceData.turnChange_Population),
                              CFG.COLOR_TEXT_MODIFIER_NEGATIVE2
                           )
                        );
                     } else {
                        nData.add(
                           new MenuElement_Hover_v2_Element_Type_Text(
                              "+" + CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).saveProvinceData.turnChange_Population, CFG.COLOR_TEXT_MODIFIER_NEUTRAL
                           )
                        );
                     }

                     nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.time, CFG.PADDING, 0));
                  } else {
                     nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.population, CFG.PADDING, 0));
                  }

                  nElements.add(new MenuElement_Hover_v2_Element2(nData));
                  nData.clear();
                  ArrayList<Integer> tSortedCivs = new ArrayList<>();
                  ArrayList<Integer> tSortedPop = new ArrayList<>();

                  for (int i = 0; i < CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getPopulationData().getNationalitiesSize(); i++) {
                     tSortedCivs.add(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getPopulationData().getCivID(i));
                     tSortedPop.add(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getPopulationData().getPopulationID(i));
                  }

                  for (int var10 = 0; var10 < tSortedCivs.size() - 1; var10++) {
                     for (int j = var10 + 1; j < tSortedCivs.size(); j++) {
                        if (tSortedPop.get(var10) < tSortedPop.get(j)) {
                           int tempD = tSortedCivs.get(var10);
                           tSortedCivs.set(var10, tSortedCivs.get(j));
                           tSortedCivs.set(j, tempD);
                           tempD = tSortedPop.get(var10);
                           tSortedPop.set(var10, tSortedPop.get(j));
                           tSortedPop.set(j, tempD);
                        }
                     }
                  }

                  if (CFG.FOG_OF_WAR == 2) {
                     for (int var12 = 0; var12 < tSortedCivs.size(); var12++) {
                        nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.getMetCiv(tSortedCivs.get(var12)) ? tSortedCivs.get(var12) : -(var12 + 1)));
                        nData.add(
                           new MenuElement_Hover_v2_Element_Type_Text("" + CFG.getNumberWithSpaces("" + tSortedPop.get(var12)), CFG.COLOR_TEXT_POPULATION)
                        );
                        nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.population, CFG.PADDING, CFG.PADDING));
                        nData.add(
                           new MenuElement_Hover_v2_Element_Type_Text(
                              "["
                                 + CFG.getPercentage(
                                    tSortedPop.get(var12), CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getPopulationData().getPopulation(), 5
                                 )
                                 + "%]",
                              CFG.COLOR_TEXT_MODIFIER_NEUTRAL
                           )
                        );
                        nData.add(
                           new MenuElement_Hover_v2_Element_Type_Text(
                              " "
                                 + (
                                    CFG.getMetCiv(tSortedCivs.get(var12))
                                       ? CFG.game.getCiv(tSortedCivs.get(var12)).getCivName()
                                       : CFG.langManager.get("Undiscovered")
                                 ),
                              CFG.COLOR_TEXT_RANK_HOVER
                           )
                        );
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                     }
                  } else {
                     for (int var11 = 0; var11 < tSortedCivs.size(); var11++) {
                        nData.add(new MenuElement_Hover_v2_Element_Type_Flag(tSortedCivs.get(var11)));
                        nData.add(
                           new MenuElement_Hover_v2_Element_Type_Text("" + CFG.getNumberWithSpaces("" + tSortedPop.get(var11)), CFG.COLOR_TEXT_POPULATION)
                        );
                        nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.population, CFG.PADDING, CFG.PADDING));
                        nData.add(
                           new MenuElement_Hover_v2_Element_Type_Text(
                              "["
                                 + CFG.getPercentage(
                                    tSortedPop.get(var11), CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getPopulationData().getPopulation(), 5
                                 )
                                 + "%]",
                              CFG.COLOR_TEXT_MODIFIER_NEUTRAL
                           )
                        );
                        nData.add(
                           new MenuElement_Hover_v2_Element_Type_Text(" " + CFG.game.getCiv(tSortedCivs.get(var11)).getCivName(), CFG.COLOR_TEXT_RANK_HOVER)
                        );
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                     }
                  }

                  try {
                     nData.add(new MenuElement_Hover_v2_Element_Type_Space());
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("RecruitablePopulation") + ": "));
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           ""
                              + CFG.getNumberWithSpaces(
                                 "" + CFG.gameAction.getRecruitableArmy(CFG.ACTIVE_PROVINCE_INFO, CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
                              ),
                           CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                        )
                     );
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           " ["
                              + CFG.getPercentage(
                                 CFG.gameAction.getRecruitableArmy(CFG.ACTIVE_PROVINCE_INFO, CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()),
                                 CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getPopulationData().getPopulation(),
                                 5
                              )
                              + "%]",
                           CFG.COLOR_TEXT_MODIFIER_NEUTRAL
                        )
                     );
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                  } catch (IndexOutOfBoundsException var8) {
                     nElements.remove(nElements.size() - 1);
                  }

                  this.menuElementHover = new MenuElement_Hover_v2(nElements);
               } catch (IndexOutOfBoundsException var9) {
                  this.menuElementHover = null;
               }
            }

            @Override
            public void drawMenuElementHover2(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
               if (this.menuElementHover != null) {
                  this.menuElementHover.drawAlwaysOver(oSB, Touch.getMousePosX(), CFG.GAME_HEIGHT - CFG.map.getMapBG().getMinimapHeight());
               }
            }
         }
      );
      ArrayList<Integer> nData = new ArrayList<>();
      ArrayList<Integer> nCivs = new ArrayList<>();
      nData.add(1);
      nCivs.add(0);
      menuElements.add(
         new Graph_Circle(
            CFG.GAME_WIDTH
               - CFG.map.getMapBG().getMinimapWidth()
               - menuElements.get(0).getWidth()
               - CFG.PADDING * 2
               - CFG.terrainTypesManager.getIcon(0).getWidth()
               - CFG.PADDING * 2
               - CFG.PADDING,
            CFG.PADDING,
            nData,
            nCivs,
            null
         ) {
            @Override
            public int getPosX() {
               return super.getPosX() - this.getWidth();
            }

            @Override
            public void drawMenuElementHover2(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
               if (this.menuElementHover != null) {
                  this.menuElementHover.drawAlwaysOver(oSB, Touch.getMousePosX(), CFG.GAME_HEIGHT - CFG.map.getMapBG().getMinimapHeight());
               }
            }
         }
      );
      menuElements.get(menuElements.size() - 1).setAnotherView(false);
      menuElements.add(
         new Button_Transparent_WithHoverEnabled(
            CFG.GAME_WIDTH - CFG.map.getMapBG().getMinimapWidth() * 2,
            0,
            CFG.terrainTypesManager.getIcon(0).getWidth() + CFG.PADDING * 2,
            CFG.terrainTypesManager.getIcon(0).getHeight() + CFG.PADDING * 2,
            true
         ) {
            @Override
            public int getPosX() {
               return Menu_InGame_ProvinceInfo.this.getMenuElement(0).getPosX() - CFG.PADDING * 2 - this.getWidth();
            }

            @Override
            public void buildElementHover() {
               try {
                  if (CFG.FOG_OF_WAR == 2 && !CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetProvince(CFG.ACTIVE_PROVINCE_INFO)) {
                     ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
                     ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
                     nData.add(new MenuElement_Hover_v2_Element_Type_Flag(-1));
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Undiscovered"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                     this.menuElementHover = new MenuElement_Hover_v2(nElements);
                  } else {
                     this.menuElementHover = CFG.game
                        .getHover_TerrainTypeInfo(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getTerrainTypeID(), CFG.ACTIVE_PROVINCE_INFO);
                  }
               } catch (IndexOutOfBoundsException var3) {
                  this.menuElementHover = null;
               }
            }

            @Override
            public void drawMenuElementHover2(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
               if (this.menuElementHover != null) {
                  this.menuElementHover.drawAlwaysOver(oSB, Touch.getMousePosX(), CFG.GAME_HEIGHT - CFG.map.getMapBG().getMinimapHeight());
               }
            }
         }
      );
      menuElements.add(
         new Button_Stats_Image(
            Images.economy, "", 0.85F, CFG.PADDING, menuElements.get(4).getPosY() + menuElements.get(4).getHeight() + CFG.PADDING, 1, tierHeight, true, true
         ) {
            @Override
            public Color getColor(boolean isActive) {
               return isActive
                  ? CFG.COLOR_TEXT_ECONOMY_ACTIVE
                  : (this.getClickable() ? (this.getIsHovered() ? CFG.COLOR_TEXT_ECONOMY_HOVER : CFG.COLOR_TEXT_ECONOMY) : new Color(0.78F, 0.78F, 0.78F, 0.7F));
            }

            @Override
            public void buildElementHover() {
               try {
                  ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
                  ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
                  if (CFG.ACTIVE_PROVINCE_INFO < 0) {
                     this.menuElementHover = null;
                     return;
                  }

                  nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Economy") + ": "));
                  nData.add(
                     new MenuElement_Hover_v2_Element_Type_Text(
                        CFG.getNumberWithSpaces("" + CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getEconomy()), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                     )
                  );
                  if (CFG.game.showTurnChangesInformation(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getCivID())) {
                     nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.economy, CFG.PADDING, CFG.PADDING));
                     if (CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).saveProvinceData.turnChange_Economy > 0) {
                        nData.add(
                           new MenuElement_Hover_v2_Element_Type_Text(
                              "+" + CFG.getNumberWithSpaces("" + CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).saveProvinceData.turnChange_Economy),
                              CFG.COLOR_TEXT_MODIFIER_POSITIVE
                           )
                        );
                     } else if (CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).saveProvinceData.turnChange_Economy < 0) {
                        nData.add(
                           new MenuElement_Hover_v2_Element_Type_Text(
                              CFG.getNumberWithSpaces("" + CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).saveProvinceData.turnChange_Economy),
                              CFG.COLOR_TEXT_MODIFIER_NEGATIVE2
                           )
                        );
                     } else {
                        nData.add(
                           new MenuElement_Hover_v2_Element_Type_Text(
                              "+" + CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).saveProvinceData.turnChange_Economy, CFG.COLOR_TEXT_MODIFIER_NEUTRAL
                           )
                        );
                     }

                     nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.time, CFG.PADDING, 0));
                  } else {
                     nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.economy, CFG.PADDING, 0));
                  }

                  nElements.add(new MenuElement_Hover_v2_Element2(nData));
                  nData.clear();
                  nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("DevelopmentLevel") + ": "));
                  nData.add(
                     new MenuElement_Hover_v2_Element_Type_Text(
                        "" + (int)(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getDevelopmentLevel() * 100.0F) / 100.0F + " / 1.0",
                        CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                     )
                  );
                  nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.development, CFG.PADDING, 0));
                  nElements.add(new MenuElement_Hover_v2_Element2(nData));
                  nData.clear();
                  this.menuElementHover = new MenuElement_Hover_v2(nElements);
               } catch (IndexOutOfBoundsException var3) {
                  this.menuElementHover = null;
               }
            }

            @Override
            public void drawMenuElementHover2(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
               if (this.menuElementHover != null) {
                  this.menuElementHover.drawAlwaysOver(oSB, Touch.getMousePosX(), CFG.GAME_HEIGHT - CFG.map.getMapBG().getMinimapHeight());
               }
            }
         }
      );
      menuElements.add(
         new Button_BotBar_ProvinceValue("0", 0.8F, 1, 1, 1, true, true) {
            @Override
            public Color getColor(boolean isActive) {
               return isActive
                  ? CFG.COLOR_TEXT_PROVINCE_VALUE_ACTIVE
                  : (
                     this.getClickable()
                        ? (this.getIsHovered() ? CFG.COLOR_TEXT_PROVINCE_VALUE_HOVER : CFG.COLOR_TEXT_PROVINCE_VALUE)
                        : new Color(0.78F, 0.78F, 0.78F, 0.7F)
                  );
            }

            @Override
            public void buildElementHover() {
               try {
                  ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
                  ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
                  if (CFG.ACTIVE_PROVINCE_INFO < 0) {
                     this.menuElementHover = null;
                     return;
                  }

                  nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("ProvinceValue") + ": ", CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                  nData.add(new MenuElement_Hover_v2_Element_Type_Text(this.getText(), CFG.COLOR_TEXT_PROVINCE_VALUE));
                  nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.victoryPoints, CFG.PADDING, 0));
                  nElements.add(new MenuElement_Hover_v2_Element2(nData));
                  nData.clear();
                  nData.add(new MenuElement_Hover_v2_Element_Type_Space());
                  nElements.add(new MenuElement_Hover_v2_Element2(nData));
                  nData.clear();
                  nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("BaseProvinceValue") + ": "));
                  nData.add(new MenuElement_Hover_v2_Element_Type_Text("+1", CFG.COLOR_TEXT_MODIFIER_POSITIVE));
                  nElements.add(new MenuElement_Hover_v2_Element2(nData));
                  nData.clear();
                  if (CFG.game.getProvinceValue_Capital(CFG.ACTIVE_PROVINCE_INFO) > 0) {
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Capital") + ": "));
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           "+" + CFG.game.getProvinceValue_Capital(CFG.ACTIVE_PROVINCE_INFO), CFG.COLOR_TEXT_MODIFIER_POSITIVE
                        )
                     );
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                  }

                  if (CFG.game.getProvinceValue_PopulationGrowthRate(CFG.ACTIVE_PROVINCE_INFO) > 0) {
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("GrowthRate") + ": "));
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           "+" + CFG.game.getProvinceValue_PopulationGrowthRate(CFG.ACTIVE_PROVINCE_INFO), CFG.COLOR_TEXT_MODIFIER_POSITIVE
                        )
                     );
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                  }

                  if (CFG.game.getProvinceValue_DevelopmentLevel(CFG.ACTIVE_PROVINCE_INFO) > 0) {
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("DevelopmentLevel") + ": "));
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           "+" + CFG.game.getProvinceValue_DevelopmentLevel(CFG.ACTIVE_PROVINCE_INFO), CFG.COLOR_TEXT_MODIFIER_POSITIVE
                        )
                     );
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                  }

                  if (CFG.game.getProvinceValue_Terrain(CFG.ACTIVE_PROVINCE_INFO) > 0) {
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           CFG.terrainTypesManager.getName(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getTerrainTypeID()) + ": "
                        )
                     );
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           "+" + CFG.game.getProvinceValue_Terrain(CFG.ACTIVE_PROVINCE_INFO), CFG.COLOR_TEXT_MODIFIER_POSITIVE
                        )
                     );
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                  }

                  this.menuElementHover = new MenuElement_Hover_v2(nElements);
               } catch (IndexOutOfBoundsException var3) {
                  this.menuElementHover = null;
               }
            }

            @Override
            public void drawMenuElementHover2(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
               if (this.menuElementHover != null) {
                  this.menuElementHover.drawAlwaysOver(oSB, Touch.getMousePosX(), CFG.GAME_HEIGHT - CFG.map.getMapBG().getMinimapHeight());
               }
            }
         }
      );
      menuElements.add(
         new Button_Stats_Image(Images.population_growth, "", 0.8F, CFG.PADDING, menuElements.get(4).getPosY(), 1, tierHeight, true, true) {
            int iCurrent = 0;

            @Override
            public Color getColor(boolean isActive) {
               return isActive
                  ? CFG.COLOR_TEXT_POPULATION_ACTIVE
                  : (
                     this.getClickable()
                        ? (
                           this.getIsHovered()
                              ? CFG.COLOR_TEXT_POPULATION_HOVER
                              : CFG.getColorStep(
                                 CFG.COLOR_TEXT_POPULATION_GROWTHRATE_MIN, CFG.COLOR_TEXT_POPULATION_GROWTHRATE_MAX, this.getCurrent(), 100, 1.0F
                              )
                        )
                        : new Color(0.78F, 0.78F, 0.78F, 0.7F)
                  );
            }

            @Override
            public int getCurrent() {
               return this.iCurrent;
            }

            @Override
            public void setCurrent(int nCurrent) {
               this.iCurrent = nCurrent;
            }

            @Override
            public void buildElementHover() {
               try {
                  ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
                  ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
                  nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("ProvinceGrowthRate") + ": "));
                  nData.add(
                     new MenuElement_Hover_v2_Element_Type_Text(
                        "" + (int)(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getGrowthRate_Population() * 100.0F) + "%", CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                     )
                  );
                  nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.population_growth, CFG.PADDING, 0));
                  nElements.add(new MenuElement_Hover_v2_Element2(nData));
                  nData.clear();
                  if (BuildingsManager.getFarm_GrowthRateBonus(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getLevelOfFarm()) > 0.0F) {
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Farm") + ": "));
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           "+"
                              + (int)(BuildingsManager.getFarm_GrowthRateBonus(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getLevelOfFarm()) * 100.0F)
                              + "%",
                           CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                        )
                     );
                     nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.b_farm, CFG.PADDING, 0));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                  }

                  if (CFG.terrainTypesManager.getPopulationGrowth(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getTerrainTypeID()) > 0.0F) {
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Color(
                           CFG.terrainTypesManager.getColor(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getTerrainTypeID()), 0, 0
                        )
                     );
                     nData.add(new MenuElement_Hover_v2_Element_Type_Terrain(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getTerrainTypeID(), 0, CFG.PADDING));
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           CFG.terrainTypesManager.getName(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getTerrainTypeID()) + ": "
                        )
                     );
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           "+"
                              + (int)(CFG.terrainTypesManager.getPopulationGrowth(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getTerrainTypeID()) * 100.0F)
                              + "%",
                           CFG.COLOR_TEXT_MODIFIER_POSITIVE
                        )
                     );
                     nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.population_growth, CFG.PADDING, 0));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                  } else if (CFG.terrainTypesManager.getPopulationGrowth(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getTerrainTypeID()) < 0.0F) {
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Color(
                           CFG.terrainTypesManager.getColor(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getTerrainTypeID()), 0, 0
                        )
                     );
                     nData.add(new MenuElement_Hover_v2_Element_Type_Terrain(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getTerrainTypeID(), 0, CFG.PADDING));
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           CFG.terrainTypesManager.getName(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getTerrainTypeID()) + ": "
                        )
                     );
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           ""
                              + (int)(CFG.terrainTypesManager.getPopulationGrowth(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getTerrainTypeID()) * 100.0F)
                              + "%",
                           CFG.COLOR_TEXT_MODIFIER_NEGATIVE2
                        )
                     );
                     nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.population_growth, CFG.PADDING, 0));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                  }

                  if (CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getGrowthRate_NewColony() > 0.0F) {
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("NewColony") + ": "));
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           "+" + (int)(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getGrowthRate_NewColony() * 100.0F) + "%",
                           CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                        )
                     );
                     nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.city, CFG.PADDING, CFG.PADDING));
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           "[" + CFG.langManager.get("TurnsX", CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).saveProvinceData.iNewColonyBonus) + "]",
                           CFG.COLOR_TEXT_MODIFIER_NEUTRAL
                        )
                     );
                     nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.time, CFG.PADDING, 0));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                  }

                  this.menuElementHover = new MenuElement_Hover_v2(nElements);
               } catch (IndexOutOfBoundsException var3) {
                  this.menuElementHover = null;
               }
            }

            @Override
            public void drawMenuElementHover2(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
               if (this.menuElementHover != null) {
                  this.menuElementHover.drawAlwaysOver(oSB, Touch.getMousePosX(), CFG.GAME_HEIGHT - CFG.map.getMapBG().getMinimapHeight());
               }
            }
         }
      );
      menuElements.add(
         new Button_Stats_Image(Images.development, "", 0.8F, CFG.PADDING, menuElements.get(7).getPosY(), 1, tierHeight, true, true) {
            int iCurrent = 0;

            @Override
            public Color getColor(boolean isActive) {
               return isActive
                  ? CFG.COLOR_TEXT_POPULATION_ACTIVE
                  : (
                     this.getClickable()
                        ? (this.getIsHovered() ? CFG.COLOR_TEXT_POPULATION_HOVER : CFG.COLOR_TEXT_MODIFIER_NEUTRAL2)
                        : new Color(0.78F, 0.78F, 0.78F, 0.7F)
                  );
            }

            @Override
            public int getCurrent() {
               return this.iCurrent;
            }

            @Override
            public void setCurrent(int nCurrent) {
               this.iCurrent = nCurrent;
            }

            @Override
            public void buildElementHover() {
               try {
                  ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
                  ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
                  nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("DevelopmentLevel") + ": "));
                  nData.add(
                     new MenuElement_Hover_v2_Element_Type_Text(
                        "" + CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getDevelopmentLevel(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                     )
                  );
                  if (CFG.game.showTurnChangesInformation(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getCivID())) {
                     nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.development, CFG.PADDING, CFG.PADDING));
                     if (CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).saveProvinceData.turnChange_Development > 0.0F) {
                        nData.add(
                           new MenuElement_Hover_v2_Element_Type_Text(
                              "+"
                                 + String.format("%.6f", CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).saveProvinceData.turnChange_Development)
                                    .replace(',', '.'),
                              CFG.COLOR_TEXT_MODIFIER_POSITIVE
                           )
                        );
                     } else if (CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).saveProvinceData.turnChange_Development < 0.0F) {
                        nData.add(
                           new MenuElement_Hover_v2_Element_Type_Text(
                              ""
                                 + String.format("%.6f", CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).saveProvinceData.turnChange_Development)
                                    .replace(',', '.'),
                              CFG.COLOR_TEXT_MODIFIER_NEGATIVE2
                           )
                        );
                     } else {
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text("+0", CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
                     }

                     nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.time, CFG.PADDING, 0));
                  } else {
                     nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.development, CFG.PADDING, 0));
                  }

                  nElements.add(new MenuElement_Hover_v2_Element2(nData));
                  nData.clear();
                  nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getCivID()));
                  nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("TechnologyLevel") + ": "));
                  nData.add(
                     new MenuElement_Hover_v2_Element_Type_Text(
                        ""
                           + (int)(CFG.game.getCiv(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getCivID()).getTechnologyLevel() * 100.0F) / 100.0F
                           + " / "
                           + 2.0F,
                        CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                     )
                  );
                  nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.technology, CFG.PADDING, 0));
                  nElements.add(new MenuElement_Hover_v2_Element2(nData));
                  nData.clear();
                  this.menuElementHover = new MenuElement_Hover_v2(nElements);
               } catch (IndexOutOfBoundsException var3) {
                  this.menuElementHover = null;
               }
            }

            @Override
            public void drawMenuElementHover2(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
               if (this.menuElementHover != null) {
                  this.menuElementHover.drawAlwaysOver(oSB, Touch.getMousePosX(), CFG.GAME_HEIGHT - CFG.map.getMapBG().getMinimapHeight());
               }
            }
         }
      );
      menuElements.add(
         new Button_Stats_Image(Images.happiness, "", 0.8F, CFG.PADDING, menuElements.get(9).getPosY(), 1, tierHeight, true, true) {
            int iCurrent = 0;

            @Override
            public Color getColor(boolean isActive) {
               return isActive
                  ? CFG.COLOR_TEXT_HAPPINESS_ACTIVE
                  : (
                     this.getClickable()
                        ? (
                           this.getIsHovered()
                              ? CFG.COLOR_TEXT_HAPPINESS_HOVER
                              : CFG.getColorStep(CFG.COLOR_TEXT_HAPPINESS_MIN, CFG.COLOR_TEXT_HAPPINESS_MAX, this.getCurrent(), 100, 1.0F)
                        )
                        : new Color(0.78F, 0.78F, 0.78F, 0.7F)
                  );
            }

            @Override
            public void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
               ImageManager.getImage(CFG.getHappinesImage(this.getCurrent()))
                  .draw(
                     oSB,
                     this.getPosX() + this.getTextPos() + iTranslateX,
                     this.getPosY()
                        + this.getHeight() / 2
                        - ImageManager.getImage(Images.happiness).getHeight()
                        - ImageManager.getImage(Images.happiness).getHeight() / 2
                        + iTranslateY,
                     ImageManager.getImage(Images.happiness).getWidth(),
                     ImageManager.getImage(Images.happiness).getHeight()
                  );
               CFG.fontMain.getData().setScale(this.FONT_SCALE);
               CFG.drawTextWithShadow(
                  oSB,
                  this.getTextToDraw(),
                  this.getPosX() + this.getTextPos() + CFG.PADDING + ImageManager.getImage(Images.happiness).getWidth() * 1 + iTranslateX,
                  this.getPosY() + this.getHeight() / 2 - this.iTextHeight / 2 + iTranslateY,
                  this.getColor(isActive)
               );
               CFG.fontMain.getData().setScale(1.0F);
            }

            @Override
            public int getCurrent() {
               return this.iCurrent;
            }

            @Override
            public void setCurrent(int nCurrent) {
               this.iCurrent = nCurrent;
            }

            @Override
            public void buildElementHover() {
               try {
                  ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
                  ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
                  nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Happiness") + ": "));
                  nData.add(
                     new MenuElement_Hover_v2_Element_Type_Text(
                        "" + (int)(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getHappiness() * 100.0F) + "%", CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                     )
                  );
                  if (CFG.game.showTurnChangesInformation(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getCivID())) {
                     nData.add(new MenuElement_Hover_v2_Element_Type_Image(CFG.getHappinesImage(this.getCurrent()), CFG.PADDING, CFG.PADDING));
                     if (CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).saveProvinceData.turnChange_Happiness > 0.0F) {
                        nData.add(
                           new MenuElement_Hover_v2_Element_Type_Text(
                              "+"
                                 + String.format("%.4f", CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).saveProvinceData.turnChange_Happiness * 100.0F)
                                    .replace(',', '.')
                                 + "%",
                              CFG.COLOR_TEXT_MODIFIER_POSITIVE
                           )
                        );
                     } else if (CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).saveProvinceData.turnChange_Happiness < 0.0F) {
                        nData.add(
                           new MenuElement_Hover_v2_Element_Type_Text(
                              ""
                                 + String.format("%.4f", CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).saveProvinceData.turnChange_Happiness * 100.0F)
                                    .replace(',', '.')
                                 + "%",
                              CFG.COLOR_TEXT_MODIFIER_NEGATIVE2
                           )
                        );
                     } else {
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text("+0%", CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
                     }

                     nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.time, CFG.PADDING, 0));
                  } else {
                     nData.add(new MenuElement_Hover_v2_Element_Type_Image(CFG.getHappinesImage(this.getCurrent()), CFG.PADDING, 0));
                  }

                  nElements.add(new MenuElement_Hover_v2_Element2(nData));
                  nData.clear();
                  this.menuElementHover = new MenuElement_Hover_v2(nElements);
               } catch (IndexOutOfBoundsException var3) {
                  this.menuElementHover = null;
               }
            }

            @Override
            public void drawMenuElementHover2(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
               if (this.menuElementHover != null) {
                  this.menuElementHover.drawAlwaysOver(oSB, Touch.getMousePosX(), CFG.GAME_HEIGHT - CFG.map.getMapBG().getMinimapHeight());
               }
            }
         }
      );
      menuElements.add(
         new Button_BotBar_NotSupplied("", 0.8F, 1, 1, 1, true, true) {
            @Override
            public Color getColor(boolean isActive) {
               return isActive
                  ? CFG.COLOR_TEXT_PROVINCE_VALUE_ACTIVE
                  : (
                     this.getClickable()
                        ? (this.getIsHovered() ? CFG.COLOR_TEXT_PROVINCE_VALUE_HOVER : CFG.COLOR_TEXT_PROVINCE_VALUE)
                        : new Color(0.78F, 0.78F, 0.78F, 0.7F)
                  );
            }

            @Override
            public void buildElementHover() {
               try {
                  ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
                  ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
                  nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Festival"), CFG.COLOR_TEXT_NUM_OF_PROVINCES));
                  nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getCivID(), CFG.PADDING, 0));
                  nElements.add(new MenuElement_Hover_v2_Element2(nData));
                  nData.clear();
                  nData.add(
                     new MenuElement_Hover_v2_Element_Type_Text(
                        Game_Calendar.getDate_ByTurnID(
                           Game_Calendar.TURN_ID
                              - 7
                              + CFG.game
                                 .getCiv(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getCivID())
                                 .isFestivalOrganized_TurnsLeft(CFG.ACTIVE_PROVINCE_INFO)
                        )
                     )
                  );
                  nData.add(new MenuElement_Hover_v2_Element_Type_Text(" - ", CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
                  nData.add(
                     new MenuElement_Hover_v2_Element_Type_Text(
                        Game_Calendar.getDate_ByTurnID(
                           Game_Calendar.TURN_ID
                              + CFG.game
                                 .getCiv(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getCivID())
                                 .isFestivalOrganized_TurnsLeft(CFG.ACTIVE_PROVINCE_INFO)
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
                                    .getCiv(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getCivID())
                                    .isFestivalOrganized_TurnsLeft(CFG.ACTIVE_PROVINCE_INFO)
                              )
                           + "]",
                        CFG.COLOR_TEXT_MODIFIER_NEUTRAL
                     )
                  );
                  nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.time, CFG.PADDING, 0));
                  nElements.add(new MenuElement_Hover_v2_Element2(nData));
                  nData.clear();
                  nData.add(new MenuElement_Hover_v2_Element_Type_Space());
                  nElements.add(new MenuElement_Hover_v2_Element2(nData));
                  nData.clear();
                  nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Happiness") + ": "));
                  nData.add(
                     new MenuElement_Hover_v2_Element_Type_Text(
                        "+" + (int)(DiplomacyManager.festivalHappinessPerTurn(CFG.ACTIVE_PROVINCE_INFO) * 10000.0F) / 100.0F, CFG.COLOR_TEXT_MODIFIER_POSITIVE
                     )
                  );
                  nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.happiness, CFG.PADDING, CFG.PADDING));
                  nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("PerTurn"), CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
                  nElements.add(new MenuElement_Hover_v2_Element2(nData));
                  nData.clear();
                  nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("NeighboringProvinces") + ": "));
                  nData.add(
                     new MenuElement_Hover_v2_Element_Type_Text(
                        "+" + (int)(DiplomacyManager.festivalHappinessPerTurn_NeighboringProvinces() * 10000.0F) / 100.0F, CFG.COLOR_TEXT_MODIFIER_POSITIVE
                     )
                  );
                  nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.happiness, CFG.PADDING, CFG.PADDING));
                  nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("PerTurn"), CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
                  nElements.add(new MenuElement_Hover_v2_Element2(nData));
                  nData.clear();
                  this.menuElementHover = new MenuElement_Hover_v2(nElements);
               } catch (IndexOutOfBoundsException var3) {
                  this.menuElementHover = null;
               }
            }

            @Override
            public void drawMenuElementHover2(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
               if (this.menuElementHover != null) {
                  this.menuElementHover.drawAlwaysOver(oSB, Touch.getMousePosX(), CFG.GAME_HEIGHT - CFG.map.getMapBG().getMinimapHeight());
               }
            }
         }
      );
      menuElements.add(
         new Button_Stats_Image(Images.diplo_popstability, "", 0.8F, CFG.PADDING, menuElements.get(7).getPosY(), 1, tierHeight, true, true) {
            int iCurrent = 0;

            @Override
            public Color getColor(boolean isActive) {
               return isActive
                  ? CFG.COLOR_TEXT_HAPPINESS_ACTIVE
                  : (
                     this.getClickable()
                        ? (
                           this.getIsHovered()
                              ? CFG.COLOR_TEXT_HAPPINESS_HOVER
                              : CFG.getColorStep(CFG.COLOR_TEXT_PROVINCE_STABILITY_MIN, CFG.COLOR_TEXT_PROVINCE_STABILITY_MAX, this.getCurrent(), 100, 1.0F)
                        )
                        : new Color(0.78F, 0.78F, 0.78F, 0.7F)
                  );
            }

            @Override
            public int getCurrent() {
               return this.iCurrent;
            }

            @Override
            public void setCurrent(int nCurrent) {
               this.iCurrent = nCurrent;
            }

            @Override
            public void buildElementHover() {
               this.menuElementHover = Menu_InGame_ProvinceInfo.getStabilityHoverOfProvince(CFG.ACTIVE_PROVINCE_INFO);
            }

            @Override
            public void drawMenuElementHover2(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
               if (this.menuElementHover != null) {
                  this.menuElementHover.drawAlwaysOver(oSB, Touch.getMousePosX(), CFG.GAME_HEIGHT - CFG.map.getMapBG().getMinimapHeight());
               }
            }
         }
      );
      menuElements.add(
         new Text(
            "",
            CFG.PADDING,
            CFG.GAME_WIDTH - CFG.map.getMapBG().getMinimapWidth() * 2 - CFG.PADDING * 3 - tempWidth,
            CFG.BUTTON_HEIGHT + CFG.PADDING * 2 - (int)(CFG.TEXT_HEIGHT * 0.8F) - CFG.PADDING * 2,
            CFG.PADDING * 2,
            (int)(CFG.TEXT_HEIGHT * 0.8F) + CFG.PADDING * 2
         ) {
            @Override
            public int getPosX() {
               return Menu_InGame_ProvinceInfo.this.getMenuElement(0).getPosX() - CFG.PADDING - CFG.iProvinceNameWidth - this.getWidth();
            }

            @Override
            public int getWidth() {
               return Menu_InGame_ProvinceInfo.iBuildingsWidth + CFG.PADDING * 2;
            }

            @Override
            public boolean getVisible() {
               return super.getVisible() && Menu_InGame_ProvinceInfo.iBuildingsWidth > 0;
            }

            @Override
            public void setVisible(boolean isVisible) {
               super.setVisible(isVisible);
               if (!isVisible) {
                  Menu_InGame_ProvinceInfo.lBuildingsImages.clear();
                  Menu_InGame_ProvinceInfo.iBuildingsWidth = 0;
               }
            }

            @Override
            public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
               if (isActive) {
                  oSB.setColor(1.0F, 1.0F, 1.0F, 0.65F);
               } else if (this.getIsHovered()) {
                  oSB.setColor(1.0F, 1.0F, 1.0F, 0.75F);
               }

               int iExtraX = 0;

               for (int i = 0; i < Menu_InGame_ProvinceInfo.lBuildingsImages.size(); i++) {
                  ImageManager.getImage(Menu_InGame_ProvinceInfo.lBuildingsImages.get(i))
                     .draw(
                        oSB,
                        this.getPosX() + CFG.PADDING + iExtraX + iTranslateX,
                        this.getPosY() + CFG.PADDING - ImageManager.getImage(Menu_InGame_ProvinceInfo.lBuildingsImages.get(i)).getHeight() + iTranslateY,
                        (int)(
                           ImageManager.getImage(Menu_InGame_ProvinceInfo.lBuildingsImages.get(i)).getWidth()
                              * Menu_InGame_ProvinceInfo.getImageScale(Menu_InGame_ProvinceInfo.lBuildingsImages.get(i))
                        ),
                        (int)(
                           ImageManager.getImage(Menu_InGame_ProvinceInfo.lBuildingsImages.get(i)).getHeight()
                              * Menu_InGame_ProvinceInfo.getImageScale(Menu_InGame_ProvinceInfo.lBuildingsImages.get(i))
                        )
                     );
                  iExtraX += CFG.PADDING
                     + (int)(
                        ImageManager.getImage(Menu_InGame_ProvinceInfo.lBuildingsImages.get(i)).getWidth()
                           * Menu_InGame_ProvinceInfo.getImageScale(Menu_InGame_ProvinceInfo.lBuildingsImages.get(i))
                     );
               }

               oSB.setColor(Color.WHITE);
            }

            @Override
            public Color getColor(boolean isActive) {
               return isActive
                  ? new Color(0.56F, 0.56F, 0.56F, 1.0F)
                  : (
                     this.getClickable()
                        ? (this.getIsHovered() ? new Color(0.78F, 0.78F, 0.78F, 1.0F) : new Color(0.92F, 0.92F, 0.92F, 1.0F))
                        : new Color(0.78F, 0.78F, 0.78F, 0.7F)
                  );
            }

            @Override
            public void buildElementHover() {
               try {
                  ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
                  ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
                  if (Menu_InGame_ProvinceInfo.iBuildingsWidth <= 0) {
                     this.menuElementHover = null;
                     return;
                  }

                  if (CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getLevelOfFort() > 0) {
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           CFG.langManager.get(BuildingsManager.getFort_Name(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getLevelOfFort())),
                           CFG.COLOR_TEXT_NUM_OF_PROVINCES
                        )
                     );
                     nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.b_fort, CFG.PADDING, CFG.PADDING));
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Level") + ": ", CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           "" + CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getLevelOfFort(), CFG.COLOR_TEXT_NUM_OF_PROVINCES
                        )
                     );
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(" - "));
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           CFG.langManager.get("HidesTheArmyFromTheSightOfViewOfWatchTower"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                        )
                     );
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(" - "));
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("DefenseBonus") + ": "));
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           "+" + BuildingsManager.getFort_DefenseBonus(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getLevelOfFort()) + "%",
                           CFG.COLOR_TEXT_MODIFIER_POSITIVE
                        )
                     );
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                  }

                  if (CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getLevelOfWatchTower() > 0) {
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           CFG.langManager.get(BuildingsManager.getTower_Name(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getLevelOfWatchTower())),
                           CFG.COLOR_TEXT_NUM_OF_PROVINCES
                        )
                     );
                     nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.b_tower, CFG.PADDING, CFG.PADDING));
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Level") + ": ", CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           "" + CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getLevelOfWatchTower(), CFG.COLOR_TEXT_NUM_OF_PROVINCES
                        )
                     );
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(" - "));
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           CFG.langManager.get("AllowsToSeeTheArmyInNeighboringProvinces"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                        )
                     );
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(" - "));
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("DefenseBonus") + ": "));
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           "+" + BuildingsManager.getTower_DefenseBonus(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getLevelOfWatchTower()) + "%",
                           CFG.COLOR_TEXT_MODIFIER_POSITIVE
                        )
                     );
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                  }

                  if (CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getLevelOfPort() > 0) {
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           CFG.langManager.get(BuildingsManager.getPort_Name(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getLevelOfPort())),
                           CFG.COLOR_TEXT_NUM_OF_PROVINCES
                        )
                     );
                     nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.b_port, CFG.PADDING, CFG.PADDING));
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Level") + ": ", CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           "" + CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getLevelOfPort(), CFG.COLOR_TEXT_NUM_OF_PROVINCES
                        )
                     );
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(" - "));
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("AllowsYourArmyGoToTheSea"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                     nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.icon_move_sea, CFG.PADDING, 0));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(" - "));
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("IncomeProduction") + ": "));
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           "+"
                              + (int)(BuildingsManager.getPort_IncomeProduction(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getLevelOfPort()) * 100.0F)
                              + "%",
                           CFG.COLOR_TEXT_MODIFIER_POSITIVE
                        )
                     );
                     nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.economy, CFG.PADDING, 0));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                  }

                  if (CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getLevelOfLibrary() > 0) {
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           CFG.langManager.get(BuildingsManager.getLibrary_Name(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getLevelOfLibrary())),
                           CFG.COLOR_TEXT_NUM_OF_PROVINCES
                        )
                     );
                     nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.b_library, CFG.PADDING, CFG.PADDING));
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Level") + ": ", CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           "" + CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getLevelOfLibrary(), CFG.COLOR_TEXT_NUM_OF_PROVINCES
                        )
                     );
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(" - "));
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("+1"), CFG.COLOR_TEXT_RESEARCH));
                     nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.research, CFG.PADDING, CFG.PADDING));
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           CFG.langManager
                              .get(
                                 "ResearchPerTurnForEveryXPeopleInProvince",
                                 BuildingsManager.getLibrary_ResearchPerPopulation(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getLevelOfLibrary())
                              ),
                           CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                        )
                     );
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                  }

                  if (CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getLevelOfFarm() > 0) {
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           CFG.langManager.get(BuildingsManager.getFarm_Name(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getLevelOfFarm())),
                           CFG.COLOR_TEXT_NUM_OF_PROVINCES
                        )
                     );
                     nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.b_farm, CFG.PADDING, CFG.PADDING));
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Level") + ": ", CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           "" + CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getLevelOfFarm(), CFG.COLOR_TEXT_NUM_OF_PROVINCES
                        )
                     );
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(" - "));
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("GrowthRate") + ": "));
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           "+"
                              + (int)(BuildingsManager.getFarm_GrowthRateBonus(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getLevelOfFarm()) * 100.0F)
                              + "%",
                           CFG.COLOR_TEXT_MODIFIER_POSITIVE
                        )
                     );
                     nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.population_growth, CFG.PADDING, 0));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                  }

                  if (CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getLevelOfWorkshop() > 0) {
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           CFG.langManager.get(BuildingsManager.getWorkshop_Name(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getLevelOfWorkshop())),
                           CFG.COLOR_TEXT_NUM_OF_PROVINCES
                        )
                     );
                     nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.b_workshop, CFG.PADDING, CFG.PADDING));
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Level") + ": ", CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           "" + CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getLevelOfWorkshop(), CFG.COLOR_TEXT_NUM_OF_PROVINCES
                        )
                     );
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(" - "));
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("IncomeProduction") + ": "));
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           "+"
                              + (int)(
                                 BuildingsManager.getWorkshop_IncomeProduction(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getLevelOfWorkshop()) * 100.0F
                              )
                              + "%",
                           CFG.COLOR_TEXT_MODIFIER_POSITIVE
                        )
                     );
                     nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.population_growth, CFG.PADDING, 0));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                  }

                  if (CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getLevelOfArmoury() > 0) {
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           CFG.langManager.get(BuildingsManager.getArmoury_Name(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getLevelOfArmoury())),
                           CFG.COLOR_TEXT_NUM_OF_PROVINCES
                        )
                     );
                     nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.b_armoury, CFG.PADDING, CFG.PADDING));
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Level") + ": ", CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           "" + CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getLevelOfArmoury(), CFG.COLOR_TEXT_NUM_OF_PROVINCES
                        )
                     );
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(" - "));
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           CFG.langManager.get("ReducesTheCostOfRecruitmentPerUnitByOneGold"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                        )
                     );
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                  }

                  if (CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getLevelOfSupply() > 0) {
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           CFG.langManager.get(BuildingsManager.getSupply_Name(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getLevelOfSupply())),
                           CFG.COLOR_TEXT_NUM_OF_PROVINCES
                        )
                     );
                     nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.b_supply, CFG.PADDING, CFG.PADDING));
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Level") + ": ", CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           "" + CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getLevelOfSupply(), CFG.COLOR_TEXT_NUM_OF_PROVINCES
                        )
                     );
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(" - "));
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("MilitaryUpkeep") + ": "));
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           "-" + (int)(BuildingsManager.getSupply_Bonus(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getLevelOfSupply()) * 100.0F) + "%",
                           CFG.COLOR_TEXT_MODIFIER_POSITIVE
                        )
                     );
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                  }

                  this.menuElementHover = new MenuElement_Hover_v2(nElements);
               } catch (IndexOutOfBoundsException var3) {
               }
            }

            @Override
            public void drawMenuElementHover2(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
               if (this.menuElementHover != null) {
                  this.menuElementHover.drawAlwaysOver(oSB, Touch.getMousePosX(), CFG.GAME_HEIGHT - CFG.map.getMapBG().getMinimapHeight());
               }
            }
         }
      );
      menuElements.add(
         new Button_Stats_Image(Images.diplo_revolution, "", 0.8F, CFG.PADDING, menuElements.get(7).getPosY(), 1, tierHeight, true, true) {
            int iCurrent = 0;

            @Override
            public Color getColor(boolean isActive) {
               return isActive
                  ? CFG.COLOR_TEXT_HAPPINESS_ACTIVE
                  : (
                     this.getClickable()
                        ? (
                           this.getIsHovered()
                              ? CFG.COLOR_TEXT_HAPPINESS_HOVER
                              : CFG.getColorStep(CFG.COLOR_TEXT_REVOLUTION_MIN, CFG.COLOR_TEXT_REVOLUTION_MAX, this.getCurrent(), 100, 1.0F)
                        )
                        : new Color(0.78F, 0.78F, 0.78F, 0.7F)
                  );
            }

            @Override
            public int getCurrent() {
               return this.iCurrent;
            }

            @Override
            public void setCurrent(int nCurrent) {
               this.iCurrent = nCurrent;
            }

            @Override
            public void buildElementHover() {
               try {
                  ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
                  ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
                  nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("RevolutionaryRisk") + ": "));
                  nData.add(
                     new MenuElement_Hover_v2_Element_Type_Text(
                        "" + (int)(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getRevolutionaryRisk() * 100.0F) + "%", CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                     )
                  );
                  if (!CFG.game.showTurnChangesInformation(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getCivID())
                     && !CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).civSupportsRebels(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())) {
                     nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.diplo_revolution, CFG.PADDING, 0));
                  } else {
                     nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.diplo_revolution, CFG.PADDING, CFG.PADDING));
                     if (CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).saveProvinceData.turnChange_RevRisk > 0.0F) {
                        nData.add(
                           new MenuElement_Hover_v2_Element_Type_Text(
                              "+"
                                 + String.format("%.2f", CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).saveProvinceData.turnChange_RevRisk * 100.0F)
                                    .replace(',', '.')
                                 + "%",
                              CFG.COLOR_TEXT_MODIFIER_NEGATIVE2
                           )
                        );
                     } else if (CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).saveProvinceData.turnChange_RevRisk < 0.0F) {
                        nData.add(
                           new MenuElement_Hover_v2_Element_Type_Text(
                              ""
                                 + String.format("%.2f", CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).saveProvinceData.turnChange_RevRisk * 100.0F)
                                    .replace(',', '.')
                                 + "%",
                              CFG.COLOR_TEXT_MODIFIER_POSITIVE
                           )
                        );
                     } else {
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text("+0%", CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
                     }

                     nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.time, CFG.PADDING, 0));
                  }

                  nElements.add(new MenuElement_Hover_v2_Element2(nData));
                  nData.clear();
                  if (CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).saveProvinceData.iSupportRebelsSize > 0) {
                     ArrayList<Integer> lCivs = new ArrayList<>();
                     ArrayList<Integer> lCivsTurnsLeft = new ArrayList<>();
                     ArrayList lSupportedByCivs = new ArrayList();
                     int iCivsSize = 0;

                     for (int i = 0; i < CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).saveProvinceData.iSupportRebelsSize; i++) {
                        boolean wasAdded = false;
                        int tAddID = CFG.game
                              .getPlayer(CFG.PLAYER_TURNID)
                              .getMetCivilization(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).saveProvinceData.lSupportRebels.get(i).iRebelsCivID)
                           ? CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).saveProvinceData.lSupportRebels.get(i).iRebelsCivID
                           : CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).saveProvinceData.lSupportRebels.get(i).iRebelsCivID * -1;

                        for (int j = lCivs.size() - 1; j >= 0; j--) {
                           if (lCivs.get(j) == tAddID) {
                              wasAdded = true;
                              lCivsTurnsLeft.set(
                                 j,
                                 Math.max(
                                    lCivsTurnsLeft.get(j), CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).saveProvinceData.lSupportRebels.get(i).iTurnsLeft
                                 )
                              );
                              ((List)lSupportedByCivs.get(j))
                                 .add(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).saveProvinceData.lSupportRebels.get(i).iByCivID);
                              break;
                           }
                        }

                        if (!wasAdded) {
                           lCivs.add(tAddID);
                           lCivsTurnsLeft.add(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).saveProvinceData.lSupportRebels.get(i).iTurnsLeft);
                           lSupportedByCivs.add(new ArrayList());
                           ((List)lSupportedByCivs.get(lSupportedByCivs.size() - 1))
                              .add(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).saveProvinceData.lSupportRebels.get(i).iByCivID);
                           if (lCivs.size() >= 4) {
                              break;
                           }
                        }
                     }

                     iCivsSize = lCivs.size();
                     nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getCivID()));
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getName(), CFG.COLOR_TEXT_NUM_OF_PROVINCES)
                     );
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(" - " + CFG.langManager.get("SupportRebels"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                     nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.diplo_revolution, CFG.PADDING, 0));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                     nData.add(new MenuElement_Hover_v2_Element_Type_Space());
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();

                     for (int var12 = 0; var12 < iCivsSize; var12++) {
                        nData.add(new MenuElement_Hover_v2_Element_Type_Flag(lCivs.get(var12)));
                        nData.add(
                           new MenuElement_Hover_v2_Element_Type_Text(
                              lCivs.get(var12) > 0 ? CFG.game.getCiv(lCivs.get(var12)).getCivName() : CFG.langManager.get("Undiscovered"),
                              CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                           )
                        );

                        for (int k = 0; k < ((List)lSupportedByCivs.get(var12)).size() && k < 10; k++) {
                           nData.add(
                              new MenuElement_Hover_v2_Element_Type_Flag(
                                 !CFG.SPECTATOR_MODE
                                       && !CFG.game
                                          .isAlly(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), (Integer)((List)lSupportedByCivs.get(var12)).get(k))
                                    ? -(Integer)((List)lSupportedByCivs.get(var12)).get(k)
                                    : (Integer)((List)lSupportedByCivs.get(var12)).get(k),
                                 k == 0 ? CFG.PADDING : 0,
                                 0
                              )
                           );
                        }

                        nData.add(
                           new MenuElement_Hover_v2_Element_Type_Text(
                              " " + Game_Calendar.getDate_ByTurnID(Game_Calendar.TURN_ID + lCivsTurnsLeft.get(var12)), CFG.COLOR_TEXT_MODIFIER_NEUTRAL
                           )
                        );
                        nData.add(
                           new MenuElement_Hover_v2_Element_Type_Text(
                              " [" + CFG.langManager.get("TurnsX", lCivsTurnsLeft.get(var12)) + "]", CFG.COLOR_TEXT_RANK_HOVER
                           )
                        );
                        nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.time, CFG.PADDING, 0));
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                     }
                  }

                  this.menuElementHover = new MenuElement_Hover_v2(nElements);
               } catch (IndexOutOfBoundsException var11) {
                  this.menuElementHover = null;
               }
            }

            @Override
            public void drawMenuElementHover2(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
               if (this.menuElementHover != null) {
                  this.menuElementHover.drawAlwaysOver(oSB, Touch.getMousePosX(), CFG.GAME_HEIGHT - CFG.map.getMapBG().getMinimapHeight());
               }
            }
         }
      );
      menuElements.add(
         new Button_BotBar_Assimilate("", 0.8F, 1, 1, 1, true, true) {
            @Override
            public void buildElementHover() {
               try {
                  ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
                  ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
                  nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Assimilate"), CFG.COLOR_TEXT_NUM_OF_PROVINCES));
                  nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getCivID(), CFG.PADDING, 0));
                  nElements.add(new MenuElement_Hover_v2_Element2(nData));
                  nData.clear();
                  nData.add(
                     new MenuElement_Hover_v2_Element_Type_Text(
                        Game_Calendar.getDate_ByTurnID(
                           Game_Calendar.TURN_ID
                              + CFG.game
                                 .getCiv(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getCivID())
                                 .isAssimialateOrganized_TurnsLeft(CFG.ACTIVE_PROVINCE_INFO)
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
                                    .getCiv(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getCivID())
                                    .isAssimialateOrganized_TurnsLeft(CFG.ACTIVE_PROVINCE_INFO)
                              )
                           + "]",
                        CFG.COLOR_TEXT_MODIFIER_NEUTRAL
                     )
                  );
                  nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.time, CFG.PADDING, 0));
                  nElements.add(new MenuElement_Hover_v2_Element2(nData));
                  nData.clear();
                  nData.add(new MenuElement_Hover_v2_Element_Type_Space());
                  nElements.add(new MenuElement_Hover_v2_Element2(nData));
                  nData.clear();
                  nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("PromoteOurTraditionsAndCulturesInThisProvince")));
                  nElements.add(new MenuElement_Hover_v2_Element2(nData));
                  nData.clear();
                  nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("APercentageOfTheLocalsWillConvertToOurNationality")));
                  nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getCivID(), CFG.PADDING, 0));
                  nElements.add(new MenuElement_Hover_v2_Element2(nData));
                  nData.clear();
                  nData.add(
                     new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("ProvinceStabilityWillBeIncreased"), CFG.COLOR_TEXT_MODIFIER_POSITIVE)
                  );
                  nElements.add(new MenuElement_Hover_v2_Element2(nData));
                  nData.clear();
                  this.menuElementHover = new MenuElement_Hover_v2(nElements);
               } catch (IndexOutOfBoundsException var3) {
                  this.menuElementHover = null;
               }
            }

            @Override
            public void drawMenuElementHover2(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
               if (this.menuElementHover != null) {
                  this.menuElementHover.drawAlwaysOver(oSB, Touch.getMousePosX(), CFG.GAME_HEIGHT - CFG.map.getMapBG().getMinimapHeight());
               }
            }
         }
      );
      menuElements.add(
         new Button_BotBar_Invest("", 0.8F, 1, 1, 1, true, true) {
            @Override
            public void buildElementHover() {
               try {
                  ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
                  ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
                  nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getCivID()));
                  nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Invest"), CFG.COLOR_TEXT_NUM_OF_PROVINCES));
                  nElements.add(new MenuElement_Hover_v2_Element2(nData));
                  nData.clear();
                  nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Economy") + ": "));
                  nData.add(
                     new MenuElement_Hover_v2_Element_Type_Text(
                        "+"
                           + CFG.getNumberWithSpaces(
                              ""
                                 + CFG.game
                                    .getCiv(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getCivID())
                                    .isInvestOrganized_EconomyLeft(CFG.ACTIVE_PROVINCE_INFO)
                           ),
                        CFG.COLOR_TEXT_ECONOMY
                     )
                  );
                  nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.economy, CFG.PADDING, 0));
                  nElements.add(new MenuElement_Hover_v2_Element2(nData));
                  nData.clear();
                  nData.add(
                     new MenuElement_Hover_v2_Element_Type_Text(
                        Game_Calendar.getDate_ByTurnID(
                           Game_Calendar.TURN_ID
                              - 4
                              + CFG.game
                                 .getCiv(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getCivID())
                                 .isInvestOrganized_TurnsLeft(CFG.ACTIVE_PROVINCE_INFO)
                        )
                     )
                  );
                  nData.add(new MenuElement_Hover_v2_Element_Type_Text(" - ", CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
                  nData.add(
                     new MenuElement_Hover_v2_Element_Type_Text(
                        Game_Calendar.getDate_ByTurnID(
                           Game_Calendar.TURN_ID
                              + CFG.game
                                 .getCiv(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getCivID())
                                 .isInvestOrganized_TurnsLeft(CFG.ACTIVE_PROVINCE_INFO)
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
                                    .getCiv(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getCivID())
                                    .isInvestOrganized_TurnsLeft(CFG.ACTIVE_PROVINCE_INFO)
                              )
                           + "]",
                        CFG.COLOR_TEXT_MODIFIER_NEUTRAL
                     )
                  );
                  nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.time, CFG.PADDING, 0));
                  nElements.add(new MenuElement_Hover_v2_Element2(nData));
                  nData.clear();
                  this.menuElementHover = new MenuElement_Hover_v2(nElements);
               } catch (IndexOutOfBoundsException var3) {
                  this.menuElementHover = null;
               }
            }

            @Override
            public void drawMenuElementHover2(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
               if (this.menuElementHover != null) {
                  this.menuElementHover.drawAlwaysOver(oSB, Touch.getMousePosX(), CFG.GAME_HEIGHT - CFG.map.getMapBG().getMinimapHeight());
               }
            }
         }
      );
      menuElements.add(
         new Button_BotBar_InvestDevelopment("", 0.8F, 1, 1, 1, true, true) {
            @Override
            public void buildElementHover() {
               try {
                  ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
                  ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
                  nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getCivID()));
                  nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Invest"), CFG.COLOR_TEXT_NUM_OF_PROVINCES));
                  nElements.add(new MenuElement_Hover_v2_Element2(nData));
                  nData.clear();
                  nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Development") + ": ", CFG.COLOR_TEXT_NUM_OF_PROVINCES));
                  nData.add(
                     new MenuElement_Hover_v2_Element_Type_Text(
                        "+"
                           + String.format(
                                 "%.4f",
                                 CFG.game
                                    .getCiv(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getCivID())
                                    .isInvestOrganized_EconomyLeft_Development(CFG.ACTIVE_PROVINCE_INFO)
                              )
                              .replace(',', '.'),
                        CFG.COLOR_TEXT_MODIFIER_NEUTRAL2
                     )
                  );
                  nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.development, CFG.PADDING, 0));
                  nElements.add(new MenuElement_Hover_v2_Element2(nData));
                  nData.clear();
                  nData.add(
                     new MenuElement_Hover_v2_Element_Type_Text(
                        Game_Calendar.getDate_ByTurnID(
                           Game_Calendar.TURN_ID
                              - 4
                              + CFG.game
                                 .getCiv(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getCivID())
                                 .isInvestOrganized_TurnsLeft_Development(CFG.ACTIVE_PROVINCE_INFO)
                        )
                     )
                  );
                  nData.add(new MenuElement_Hover_v2_Element_Type_Text(" - ", CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
                  nData.add(
                     new MenuElement_Hover_v2_Element_Type_Text(
                        Game_Calendar.getDate_ByTurnID(
                           Game_Calendar.TURN_ID
                              + CFG.game
                                 .getCiv(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getCivID())
                                 .isInvestOrganized_TurnsLeft_Development(CFG.ACTIVE_PROVINCE_INFO)
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
                                    .getCiv(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getCivID())
                                    .isInvestOrganized_TurnsLeft_Development(CFG.ACTIVE_PROVINCE_INFO)
                              )
                           + "]",
                        CFG.COLOR_TEXT_MODIFIER_NEUTRAL
                     )
                  );
                  nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.time, CFG.PADDING, 0));
                  nElements.add(new MenuElement_Hover_v2_Element2(nData));
                  nData.clear();
                  this.menuElementHover = new MenuElement_Hover_v2(nElements);
               } catch (IndexOutOfBoundsException var3) {
                  this.menuElementHover = null;
               }
            }

            @Override
            public void drawMenuElementHover2(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
               if (this.menuElementHover != null) {
                  this.menuElementHover.drawAlwaysOver(oSB, Touch.getMousePosX(), CFG.GAME_HEIGHT - CFG.map.getMapBG().getMinimapHeight());
               }
            }
         }
      );
      menuElements.add(
         new Button_BotBar_ISNotSupplied("", 0.8F, 1, 1, 1, true, true) {
            @Override
            public void buildElementHover() {
               try {
                  ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
                  ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
                  nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getCivID()));
                  nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("ProvinceIsNotSupplied"), CFG.COLOR_TEXT_MODIFIER_NEGATIVE2));
                  nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.difficulty_hell, CFG.PADDING, CFG.PADDING));
                  nData.add(
                     new MenuElement_Hover_v2_Element_Type_Text(
                        "" + CFG.langManager.get("TurnsX", CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getIsNotSuppliedForXTurns()),
                        CFG.COLOR_TEXT_MODIFIER_NEUTRAL
                     )
                  );
                  nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.time, CFG.PADDING, 0));
                  nElements.add(new MenuElement_Hover_v2_Element2(nData));
                  nData.clear();
                  nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("DefenseBonus") + ": "));
                  nData.add(new MenuElement_Hover_v2_Element_Type_Text("-10% " + CFG.langManager.get("PerTurn"), CFG.COLOR_TEXT_MODIFIER_NEGATIVE2));
                  nElements.add(new MenuElement_Hover_v2_Element2(nData));
                  nData.clear();
                  this.menuElementHover = new MenuElement_Hover_v2(nElements);
               } catch (IndexOutOfBoundsException var3) {
                  this.menuElementHover = null;
               }
            }

            @Override
            public void drawMenuElementHover2(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
               if (this.menuElementHover != null) {
                  this.menuElementHover.drawAlwaysOver(oSB, Touch.getMousePosX(), CFG.GAME_HEIGHT - CFG.map.getMapBG().getMinimapHeight());
               }
            }
         }
      );
      menuElements.add(
         new Button_BotBar_DefensivePosition("", 0.8F, 1, 1, 1, true, true) {
            @Override
            public void buildElementHover() {
               try {
                  ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
                  ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
                  nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getCivID()));
                  nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("DefensivePosition") + ": "));
                  nData.add(
                     new MenuElement_Hover_v2_Element_Type_Text(
                        "" + CFG.langManager.get("TurnsX", CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getDefensivePosition()),
                        CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                     )
                  );
                  nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.time, CFG.PADDING, 0));
                  nElements.add(new MenuElement_Hover_v2_Element2(nData));
                  nData.clear();
                  nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("MilitaryUpkeep") + ": "));
                  nData.add(
                     new MenuElement_Hover_v2_Element_Type_Text(
                        "-" + Math.ceil((int)(CFG.game_NextTurnUpdate.getMilitaryUpkeep_DefensivePosition(CFG.ACTIVE_PROVINCE_INFO) * 1000.0F)) / 10.0 + "%",
                        CFG.COLOR_TEXT_MODIFIER_POSITIVE
                     )
                  );
                  nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.diplo_army, CFG.PADDING, 0));
                  nElements.add(new MenuElement_Hover_v2_Element2(nData));
                  nData.clear();
                  this.menuElementHover = new MenuElement_Hover_v2(nElements);
               } catch (IndexOutOfBoundsException var3) {
                  this.menuElementHover = null;
               }
            }

            @Override
            public void drawMenuElementHover2(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
               if (this.menuElementHover != null) {
                  this.menuElementHover.drawAlwaysOver(oSB, Touch.getMousePosX(), CFG.GAME_HEIGHT - CFG.map.getMapBG().getMinimapHeight());
               }
            }
         }
      );
      menuElements.add(
         new Button_BotBar_Disease("", 0.8F, 1, 1, 1, true, true) {
            @Override
            public void buildElementHover() {
               try {
                  ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
                  ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
                  nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Name") + ": "));
                  nData.add(
                     new MenuElement_Hover_v2_Element_Type_Text(
                        CFG.plagueManager
                           .getPlague_InGame(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).saveProvinceData.provincePlague.iPlagueID_InGame)
                           .getPlagueName(),
                        CFG.COLOR_TEXT_MODIFIER_NEGATIVE2
                     )
                  );
                  nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.disease, CFG.PADDING, CFG.PADDING));
                  nElements.add(new MenuElement_Hover_v2_Element2(nData));
                  nData.clear();
                  nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Deaths") + ": "));
                  nData.add(
                     new MenuElement_Hover_v2_Element_Type_Text(
                        CFG.getNumberWithSpaces("" + CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).saveProvinceData.provincePlague.iDeaths),
                        CFG.COLOR_TEXT_MODIFIER_NEGATIVE2
                     )
                  );
                  nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.population, CFG.PADDING, 0));
                  nElements.add(new MenuElement_Hover_v2_Element2(nData));
                  nData.clear();
                  nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("OutbreakOfDisease") + ": "));
                  nData.add(
                     new MenuElement_Hover_v2_Element_Type_Text(
                        CFG.game
                              .getPlayer(CFG.PLAYER_TURNID)
                              .getMetProvince(
                                 CFG.plagueManager
                                    .getPlague_InGame(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).saveProvinceData.provincePlague.iPlagueID_InGame)
                                    .getOutbreakProvinceID()
                              )
                           ? CFG.game
                              .getProvince(
                                 CFG.plagueManager
                                    .getPlague_InGame(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).saveProvinceData.provincePlague.iPlagueID_InGame)
                                    .getOutbreakProvinceID()
                              )
                              .getName()
                           : CFG.langManager.get("Undiscovered"),
                        CFG.COLOR_TEXT_NUM_OF_PROVINCES
                     )
                  );
                  nData.add(
                     new MenuElement_Hover_v2_Element_Type_Flag(
                        CFG.game
                              .getPlayer(CFG.PLAYER_TURNID)
                              .getMetProvince(
                                 CFG.plagueManager
                                    .getPlague_InGame(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).saveProvinceData.provincePlague.iPlagueID_InGame)
                                    .getOutbreakProvinceID()
                              )
                           ? CFG.game
                              .getProvince(
                                 CFG.plagueManager
                                    .getPlague_InGame(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).saveProvinceData.provincePlague.iPlagueID_InGame)
                                    .getOutbreakProvinceID()
                              )
                              .getCivID()
                           : -1,
                        CFG.PADDING,
                        0
                     )
                  );
                  nElements.add(new MenuElement_Hover_v2_Element2(nData));
                  nData.clear();
                  nData.add(new MenuElement_Hover_v2_Element_Type_Space());
                  nElements.add(new MenuElement_Hover_v2_Element2(nData));
                  nData.clear();
                  nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getCivID()));
                  nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Disease"), CFG.COLOR_TEXT_MODIFIER_NEGATIVE2));
                  nData.add(new MenuElement_Hover_v2_Element_Type_Text(" - ", Color.WHITE));
                  nData.add(
                     new MenuElement_Hover_v2_Element_Type_Text(
                        "" + Game_Calendar.getDate_ByTurnID(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).saveProvinceData.provincePlague.iSinceTurnID),
                        CFG.COLOR_TEXT_MODIFIER_NEUTRAL
                     )
                  );
                  nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.time, CFG.PADDING, 0));
                  nElements.add(new MenuElement_Hover_v2_Element2(nData));
                  nData.clear();
                  this.menuElementHover = new MenuElement_Hover_v2(nElements);
               } catch (IndexOutOfBoundsException var3) {
                  this.menuElementHover = null;
               } catch (NullPointerException var4) {
                  this.menuElementHover = null;
               }
            }

            @Override
            public void drawMenuElementHover2(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
               if (this.menuElementHover != null) {
                  this.menuElementHover.drawAlwaysOver(oSB, Touch.getMousePosX(), CFG.GAME_HEIGHT - CFG.map.getMapBG().getMinimapHeight());
               }
            }
         }
      );
      menuElements.add(
         new Button_BotBar_NewColony("", 0.8F, 1, 1, 1, true, true) {
            @Override
            public void buildElementHover() {
               try {
                  ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
                  ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
                  nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getCivID()));
                  nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("NewColony")));
                  if (CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getName().length() > 0) {
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(": "));
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getName(), CFG.COLOR_TEXT_NUM_OF_PROVINCES)
                     );
                  }

                  nElements.add(new MenuElement_Hover_v2_Element2(nData));
                  nData.clear();
                  nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("GrowthRate") + ": "));
                  nData.add(
                     new MenuElement_Hover_v2_Element_Type_Text(
                        "+" + (int)(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getGrowthRate_NewColony() * 100.0F) + "%", CFG.COLOR_TEXT_MODIFIER_POSITIVE
                     )
                  );
                  nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.population_growth, CFG.PADDING, CFG.PADDING));
                  nData.add(
                     new MenuElement_Hover_v2_Element_Type_Text(
                        Game_Calendar.getDate_ByTurnID(Game_Calendar.TURN_ID + CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).saveProvinceData.iNewColonyBonus)
                           + " "
                     )
                  );
                  nData.add(
                     new MenuElement_Hover_v2_Element_Type_Text(
                        "[" + CFG.langManager.get("TurnsX", CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).saveProvinceData.iNewColonyBonus) + "]",
                        CFG.COLOR_TEXT_MODIFIER_NEUTRAL
                     )
                  );
                  nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.time, CFG.PADDING, 0));
                  nElements.add(new MenuElement_Hover_v2_Element2(nData));
                  nData.clear();
                  this.menuElementHover = new MenuElement_Hover_v2(nElements);
               } catch (IndexOutOfBoundsException var3) {
                  this.menuElementHover = null;
               } catch (NullPointerException var4) {
                  this.menuElementHover = null;
               }
            }

            @Override
            public void drawMenuElementHover2(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
               if (this.menuElementHover != null) {
                  this.menuElementHover.drawAlwaysOver(oSB, Touch.getMousePosX(), CFG.GAME_HEIGHT - CFG.map.getMapBG().getMinimapHeight());
               }
            }
         }
      );
      menuElements.add(new Button_BotBar_SuppRebels("", 0.8F, 1, 1, 1, true, true) {
         @Override
         public void drawMenuElementHover2(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
            if (this.menuElementHover != null) {
               this.menuElementHover.drawAlwaysOver(oSB, Touch.getMousePosX(), CFG.GAME_HEIGHT - CFG.map.getMapBG().getMinimapHeight());
            }
         }
      });
      menuElements.add(
         new Text(
            "0",
            0,
            CFG.GAME_WIDTH,
            CFG.terrainTypesManager.getIcon(0).getHeight() + CFG.PADDING * 3,
            CFG.PADDING * 2,
            (int)(CFG.TEXT_HEIGHT * 0.8F) + CFG.PADDING * 2
         ) {
            @Override
            public int getPosX() {
               return Menu_InGame_ProvinceInfo.this.getMenuElement(0).getPosX() - CFG.PADDING - this.getWidth();
            }

            @Override
            public int getWidth() {
               return CFG.terrainTypesManager.getIcon(0).getWidth() + CFG.PADDING * 2;
            }

            @Override
            public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
               CFG.fontMain.getData().setScale(0.8F);
               CFG.drawText(
                  oSB,
                  this.getText(),
                  this.getPosX() + (int)(this.getWidth() / 2.0F - this.getTextWidth() * 0.8F / 2.0F) + iTranslateX,
                  this.getPosY() + (int)(this.getHeight() / 2 - CFG.TEXT_HEIGHT * 0.8F / 2.0F) + iTranslateY,
                  this.getColor(isActive)
               );
               CFG.fontMain.getData().setScale(1.0F);
            }

            @Override
            public Color getColor(boolean isActive) {
               return isActive
                  ? CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                  : (
                     this.getClickable()
                        ? (this.getIsHovered() ? CFG.COLOR_BUTTON_GAME_TEXT_HOVERED : CFG.COLOR_BUTTON_GAME_TEXT)
                        : CFG.COLOR_BUTTON_GAME_TEXT_NOT_CLICKABLE
                  );
            }

            @Override
            public void buildElementHover() {
               try {
                  ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
                  ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
                  nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("ProvinceName") + ": "));
                  nData.add(
                     new MenuElement_Hover_v2_Element_Type_Text(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getName(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE)
                  );
                  if (CFG.FOG_OF_WAR == 2) {
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Flag(
                           CFG.getMetCiv(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getCivID())
                              ? CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getCivID()
                              : -1,
                           CFG.PADDING,
                           0
                        )
                     );
                  } else {
                     nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getCivID(), CFG.PADDING, 0));
                  }

                  nElements.add(new MenuElement_Hover_v2_Element2(nData));
                  nData.clear();
                  nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Continent") + ": "));
                  nData.add(
                     new MenuElement_Hover_v2_Element_Type_Text(
                        CFG.map.getMapContinents().getName(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getContinent()),
                        CFG.map.getMapContinents().getColor(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getContinent())
                     )
                  );
                  nElements.add(new MenuElement_Hover_v2_Element2(nData));
                  nData.clear();
                  if (CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getMountainsSize() > 0) {
                     for (int i = 0; i < CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getMountainsSize(); i++) {
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Mountain") + ": "));
                        nData.add(
                           new MenuElement_Hover_v2_Element_Type_Text(
                              CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getMountain(i).getName() + " ", CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                           )
                        );
                        nData.add(
                           new MenuElement_Hover_v2_Element_Type_Text(
                              ""
                                 + CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getMountain(i).getElevation()
                                 + "m / "
                                 + CFG.getMetersToFeet(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getMountain(i).getElevation())
                                 + "ft",
                              CFG.COLOR_TEXT_MODIFIER_NEUTRAL
                           )
                        );
                        nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.mount, CFG.PADDING, 0));
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                     }
                  }

                  nData.add(new MenuElement_Hover_v2_Element_Type_Space());
                  nElements.add(new MenuElement_Hover_v2_Element2(nData));
                  nData.clear();

                  for (int i = 0; i < CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getNeighboringProvincesSize(); i++) {
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Terrain(
                           CFG.game.getProvince(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getNeighboringProvinces(i)).getTerrainTypeID()
                        )
                     );
                     if (CFG.game.getProvince(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getNeighboringProvinces(i)).getName().length() == 0
                        && CFG.game.getProvince(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getNeighboringProvinces(i)).getSeaProvince()) {
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Sea")));
                     } else {
                        nData.add(
                           new MenuElement_Hover_v2_Element_Type_Text(
                              CFG.game.getProvince(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getNeighboringProvinces(i)).getName()
                           )
                        );
                     }

                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Flag(
                           CFG.game.getProvince(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getNeighboringProvinces(i)).getCivID(), CFG.PADDING, 0
                        )
                     );
                     if (CFG.game.getProvince(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getNeighboringProvinces(i)).getSeaProvince()) {
                        nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.icon_move_sea, CFG.PADDING, 0));
                     } else if (CFG.game
                        .isAlly(
                           CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(),
                           CFG.game.getProvince(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getNeighboringProvinces(i)).getCivID()
                        )) {
                        nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.icon_move_attack, CFG.PADDING, 0));
                     } else {
                        nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.icon_move_ally, CFG.PADDING, 0));
                     }

                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                  }

                  for (int var6 = 0; var6 < CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getNeighboringSeaProvincesSize(); var6++) {
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Terrain(
                           CFG.game.getProvince(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getNeighboringSeaProvinces(var6)).getTerrainTypeID()
                        )
                     );
                     if (CFG.game.getProvince(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getNeighboringSeaProvinces(var6)).getName().length() == 0) {
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Sea")));
                     } else {
                        nData.add(
                           new MenuElement_Hover_v2_Element_Type_Text(
                              CFG.game.getProvince(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getNeighboringSeaProvinces(var6)).getName()
                           )
                        );
                     }

                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Flag(
                           CFG.game.getProvince(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getNeighboringSeaProvinces(var6)).getCivID(), CFG.PADDING, 0
                        )
                     );
                     nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.icon_move_sea, CFG.PADDING, 0));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                  }

                  this.menuElementHover = new MenuElement_Hover_v2(nElements);
               } catch (IndexOutOfBoundsException var4) {
               }
            }

            @Override
            public void drawMenuElementHover2(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
               if (this.menuElementHover != null) {
                  this.menuElementHover.drawAlwaysOver(oSB, Touch.getMousePosX(), CFG.GAME_HEIGHT - CFG.map.getMapBG().getMinimapHeight());
               }
            }
         }
      );
      this.initMenu(
         null,
         CFG.map.getMapBG().getMinimapWidth(),
         CFG.GAME_HEIGHT - CFG.map.getMapBG().getMinimapHeight(),
         CFG.GAME_WIDTH - CFG.map.getMapBG().getMinimapWidth(),
         CFG.BUTTON_HEIGHT + CFG.PADDING * 2,
         menuElements,
         true,
         false
      );
      this.updateLanguage();
   }

   @Override
   public void updateLanguage() {
      this.getMenuElement(0).setText(CFG.langManager.get("NextTurn"));
      this.updateButtonWidth(0, CFG.PADDING, CFG.BUTTON_WIDTH);
      this.getMenuElement(0).setPosX(CFG.GAME_WIDTH - this.getMenuElement(0).getWidth() - CFG.PADDING - CFG.map.getMapBG().getMinimapWidth());
      this.getMenuElement(3).setText(CFG.langManager.get("Cores") + ":");
   }

   public static final MenuElement_Hover_v2 getStabilityHoverOfProvince(int nProvinceID) {
      try {
         ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
         ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
         nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("ProvinceStability") + ": "));
         nData.add(
            new MenuElement_Hover_v2_Element_Type_Text(
               "" + (int)(CFG.game.getProvince(nProvinceID).getProvinceStability() * 100.0F) + "%", CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
            )
         );
         if (CFG.game.showTurnChangesInformation(CFG.game.getProvince(nProvinceID).getCivID())) {
            nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.diplo_popstability, CFG.PADDING, CFG.PADDING));
            if (CFG.game.getProvince(nProvinceID).saveProvinceData.turnChange_Stability > 0.0F) {
               nData.add(
                  new MenuElement_Hover_v2_Element_Type_Text(
                     "+" + String.format("%.3f", CFG.game.getProvince(nProvinceID).saveProvinceData.turnChange_Stability * 100.0F).replace(',', '.') + "%",
                     CFG.COLOR_TEXT_MODIFIER_POSITIVE
                  )
               );
            } else if (CFG.game.getProvince(nProvinceID).saveProvinceData.turnChange_Stability < 0.0F) {
               nData.add(
                  new MenuElement_Hover_v2_Element_Type_Text(
                     "" + String.format("%.3f", CFG.game.getProvince(nProvinceID).saveProvinceData.turnChange_Stability * 100.0F).replace(',', '.') + "%",
                     CFG.COLOR_TEXT_MODIFIER_NEGATIVE2
                  )
               );
            } else {
               nData.add(new MenuElement_Hover_v2_Element_Type_Text("+0%", CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
            }

            nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.time, CFG.PADDING, 0));
         } else {
            nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.diplo_popstability, CFG.PADDING, 0));
         }

         nElements.add(new MenuElement_Hover_v2_Element2(nData));
         nData.clear();
         nData.add(new MenuElement_Hover_v2_Element_Type_Space());
         nElements.add(new MenuElement_Hover_v2_Element2(nData));
         nData.clear();
         int ownnerPopulation = 0;
         int largestGroup = 0;
         int notOwnerPopulaiton = 0;

         for (int i = 0; i < CFG.game.getProvince(nProvinceID).getPopulationData().getNationalitiesSize(); i++) {
            if (CFG.game.getProvince(nProvinceID).getPopulationData().getCivID(i) == CFG.game.getProvince(nProvinceID).getCivID()) {
               ownnerPopulation = CFG.game.getProvince(nProvinceID).getPopulationData().getPopulationID(i);
            } else {
               notOwnerPopulaiton += CFG.game.getProvince(nProvinceID).getPopulationData().getPopulationID(i);
            }

            if (CFG.game.getProvince(nProvinceID).getPopulationData().getPopulationID(i) > largestGroup) {
               largestGroup = CFG.game.getProvince(nProvinceID).getPopulationData().getPopulationID(i);
            }
         }

         nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + CFG.getNumberWithSpaces("" + ownnerPopulation), CFG.COLOR_TEXT_POPULATION));
         nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getProvince(nProvinceID).getCivID(), CFG.PADDING, 0));
         nData.add(new MenuElement_Hover_v2_Element_Type_Text(" / ", CFG.COLOR_TEXT_POPULATION));
         nData.add(
            new MenuElement_Hover_v2_Element_Type_Text("" + CFG.getNumberWithSpaces("" + (ownnerPopulation + notOwnerPopulaiton)), CFG.COLOR_TEXT_POPULATION)
         );
         nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.population, CFG.PADDING, CFG.PADDING));
         nData.add(
            new MenuElement_Hover_v2_Element_Type_Text(
               "[" + CFG.getPercentage_Max100(ownnerPopulation, ownnerPopulation + notOwnerPopulaiton, 4) + "%]", CFG.COLOR_TEXT_MODIFIER_NEUTRAL
            )
         );
         nElements.add(new MenuElement_Hover_v2_Element2(nData));
         nData.clear();
         if (CFG.SPECTATOR_MODE || CFG.game.isAlly(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), CFG.game.getProvince(nProvinceID).getCivID())) {
            if (ownnerPopulation < notOwnerPopulaiton) {
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("OurPopulationIsAMinority"), CFG.COLOR_TEXT_MODIFIER_NEGATIVE2));
               nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getProvince(nProvinceID).getCivID(), CFG.PADDING, 0));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
            }

            if (CFG.game.getProvince(nProvinceID).getCivID() == CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()
               && CFG.game.getProvince(nProvinceID).getProvinceStability() < 1.0F) {
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("AssimilateTheProvincesToIncreaseStability")));
               nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.diplo_popstability, CFG.PADDING, 0));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
            }

            nData.add(new MenuElement_Hover_v2_Element_Type_Space());
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
            float tempScore = Math.min(CFG.game.getProvince(nProvinceID).updateStability_Score_Population() * 100.0F, 100.0F);
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Population") + ": "));
            nData.add(
               new MenuElement_Hover_v2_Element_Type_Text(
                  "+" + (tempScore != 0.0F ? String.format("%.1f", tempScore).replace(',', '.') : (int)tempScore) + "%",
                  tempScore > 0.0F ? CFG.COLOR_TEXT_MODIFIER_POSITIVE : CFG.COLOR_TEXT_MODIFIER_NEUTRAL
               )
            );
            nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.diplo_popstability, CFG.PADDING, 0));
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
            tempScore = Math.min(CFG.game.getProvince(nProvinceID).updateStability_Score_Core() * 100.0F, 100.0F);
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Core") + ": "));
            nData.add(
               new MenuElement_Hover_v2_Element_Type_Text(
                  "+" + (tempScore != 0.0F ? String.format("%.1f", tempScore).replace(',', '.') : (int)tempScore) + "%",
                  tempScore > 0.0F ? CFG.COLOR_TEXT_MODIFIER_POSITIVE : CFG.COLOR_TEXT_MODIFIER_NEUTRAL
               )
            );
            nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.diplo_popstability, CFG.PADDING, 0));
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
            tempScore = Math.min(CFG.game.getProvince(nProvinceID).updateStability_Score_Army() * 100.0F, 100.0F);
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Army") + ": "));
            nData.add(
               new MenuElement_Hover_v2_Element_Type_Text(
                  "+" + (tempScore != 0.0F ? String.format("%.1f", tempScore).replace(',', '.') : (int)tempScore) + "%",
                  tempScore > 0.0F ? CFG.COLOR_TEXT_MODIFIER_POSITIVE : CFG.COLOR_TEXT_MODIFIER_NEUTRAL
               )
            );
            nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.diplo_popstability, CFG.PADDING, 0));
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
            tempScore = Math.min(CFG.game.getProvince(nProvinceID).updateStability_Score_RevRisk() * 100.0F, 100.0F);
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("RevolutionaryRisk") + ": "));
            nData.add(
               new MenuElement_Hover_v2_Element_Type_Text(
                  "-" + (tempScore != 0.0F ? String.format("%.1f", tempScore).replace(',', '.') : (int)tempScore) + "%",
                  tempScore > 0.0F ? CFG.COLOR_TEXT_MODIFIER_NEGATIVE2 : CFG.COLOR_TEXT_MODIFIER_NEUTRAL
               )
            );
            nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.diplo_popstability, CFG.PADDING, 0));
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
            tempScore = Math.min(CFG.game.getProvince(nProvinceID).updateStability_Score_Happiness(), 100.0F);
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Happiness") + ": "));
            nData.add(
               new MenuElement_Hover_v2_Element_Type_Text(
                  (tempScore >= 0.0F ? "+" : "") + (tempScore != 0.0F ? String.format("%.1f", tempScore).replace(',', '.') : (int)tempScore) + "%",
                  tempScore > 0.0F
                     ? CFG.COLOR_TEXT_MODIFIER_POSITIVE
                     : (tempScore < 0.0F ? CFG.COLOR_TEXT_MODIFIER_NEGATIVE2 : CFG.COLOR_TEXT_MODIFIER_NEUTRAL)
               )
            );
            nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.diplo_popstability, CFG.PADDING, 0));
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
            tempScore = Math.min(CFG.game.getProvince(nProvinceID).updateStability_Score_Occupied(), 100.0F);
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Occupied") + ": "));
            nData.add(
               new MenuElement_Hover_v2_Element_Type_Text(
                  "+" + (tempScore != 0.0F ? String.format("%.1f", tempScore).replace(',', '.') : (int)tempScore) + "%",
                  tempScore > 0.0F ? CFG.COLOR_TEXT_MODIFIER_POSITIVE : CFG.COLOR_TEXT_MODIFIER_NEUTRAL
               )
            );
            nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.diplo_popstability, CFG.PADDING, 0));
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
            tempScore = Math.min(CFG.game.getProvince(nProvinceID).updateStability_Score_Disease() * 100.0F, 100.0F);
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Disease") + ": "));
            nData.add(
               new MenuElement_Hover_v2_Element_Type_Text(
                  "-" + (tempScore != 0.0F ? String.format("%.1f", tempScore).replace(',', '.') : (int)tempScore) + "%",
                  tempScore > 0.0F ? CFG.COLOR_TEXT_MODIFIER_NEGATIVE2 : CFG.COLOR_TEXT_MODIFIER_NEUTRAL
               )
            );
            nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.diplo_popstability, CFG.PADDING, 0));
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
         }

         return new MenuElement_Hover_v2(nElements);
      } catch (IndexOutOfBoundsException var7) {
         return null;
      }
   }

   public static final void updateBuildingsList(int nProvinceID) {
      lBuildingsImages.clear();
      if (CFG.game.getProvince(nProvinceID).getLevelOfFort() > 0) {
         lBuildingsImages.add(Images.b_fort);
      }

      if (CFG.game.getProvince(nProvinceID).getLevelOfWatchTower() > 0) {
         lBuildingsImages.add(Images.b_tower);
      }

      if (CFG.game.getProvince(nProvinceID).getLevelOfPort() > 0) {
         lBuildingsImages.add(Images.b_port);
      }

      if (CFG.game.getProvince(nProvinceID).getLevelOfLibrary() > 0) {
         lBuildingsImages.add(Images.b_library);
      }

      if (CFG.game.getProvince(nProvinceID).getLevelOfFarm() > 0) {
         lBuildingsImages.add(Images.b_farm);
      }

      if (CFG.game.getProvince(nProvinceID).getLevelOfWorkshop() > 0) {
         lBuildingsImages.add(Images.b_workshop);
      }

      if (CFG.game.getProvince(nProvinceID).getLevelOfArmoury() > 0) {
         lBuildingsImages.add(Images.b_armoury);
      }

      if (CFG.game.getProvince(nProvinceID).getLevelOfSupply() > 0) {
         lBuildingsImages.add(Images.b_supply);
      }

      iBuildingsWidth = 0;

      for (int i = 0; i < lBuildingsImages.size(); i++) {
         iBuildingsWidth = iBuildingsWidth
            + (int)(ImageManager.getImage(lBuildingsImages.get(i)).getWidth() * getImageScale(lBuildingsImages.get(i)))
            + CFG.PADDING;
      }

      if (lBuildingsImages.size() > 0) {
         iBuildingsWidth = iBuildingsWidth + CFG.PADDING * 2;
      }
   }

   @Override
   public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      int activeProvinceInfo = CFG.chosenProvinceID >= 0 ? CFG.chosenProvinceID : CFG.game.getActiveProvinceID();
      if (iMaxWidth != 0) {
         if (iMaxWidth > 0) {
            ImageManager.getImage(Images.bg_game_menu)
               .draw2(
                  oSB,
                  this.getPosX() + 1 + iTranslateX,
                  this.getPosY() - ImageManager.getImage(Images.bg_game_menu).getHeight() + iTranslateY,
                  this.getWidth(),
                  this.getHeight()
               );
            if (activeProvinceInfo >= 0 && CFG.game.getProvince(activeProvinceInfo).isOccupied()) {
               oSB.setColor(new Color(1.0F, 1.0F, 1.0F, 0.075F));
               ImageManager.getImage(Images.patt2)
                  .draw2(
                     oSB,
                     this.getPosX() + 1 + iTranslateX,
                     this.getPosY() + 1 - ImageManager.getImage(Images.patt2).getHeight() + iTranslateY,
                     iMaxWidth,
                     this.getHeight() - 1
                  );
               oSB.setColor(Color.WHITE);
            }

            oSB.setColor(new Color(CFG.COLOR_GRADIENT_DARK_BLUE.r, CFG.COLOR_GRADIENT_DARK_BLUE.g, CFG.COLOR_GRADIENT_DARK_BLUE.b, 0.55F));
            ImageManager.getImage(Images.slider_gradient)
               .draw(
                  oSB,
                  this.getPosX() + 1 + iTranslateX,
                  this.getPosY() + 1 - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY,
                  Math.min(this.getMenuElement(9).getPosX() + this.getMenuElement(9).getWidth() + CFG.PADDING * 2, iMaxWidth),
                  this.getHeight() - 1
               );
            oSB.setColor(new Color(0.012F, 0.024F, 0.072F, 0.2F));
            ImageManager.getImage(Images.gradient)
               .draw(
                  oSB,
                  this.getPosX() + 1 + iTranslateX,
                  this.getPosY() + this.getHeight() - CFG.PADDING - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY,
                  iMaxWidth,
                  CFG.PADDING,
                  false,
                  true
               );
            oSB.setColor(Color.WHITE);
            if (this.getMenuElement(5).getVisible()) {
               ImageManager.getImage(Images.bg_game_menu_r)
                  .draw2(
                     oSB,
                     this.getPosX() + this.getMenuElement(5).getPosX() - CFG.PADDING * 2 + iTranslateX,
                     this.getPosY() - 1 - ImageManager.getImage(Images.bg_game_menu_r).getHeight() + iTranslateY,
                     CFG.GAME_WIDTH - (this.getPosX() + this.getMenuElement(5).getPosX() - CFG.PADDING * 2),
                     this.getHeight() + 1,
                     false,
                     false
                  );
            }
         } else {
            ImageManager.getImage(Images.bg_game_menu)
               .draw2(
                  oSB,
                  this.getPosX() + 1 + iTranslateX,
                  this.getPosY() - ImageManager.getImage(Images.bg_game_menu).getHeight() + iTranslateY,
                  this.getWidth() - 1,
                  this.getHeight()
               );
            if (activeProvinceInfo >= 0 && CFG.game.getProvince(activeProvinceInfo).isOccupied()) {
               oSB.setColor(new Color(1.0F, 1.0F, 1.0F, 0.1F));
               ImageManager.getImage(Images.patt2)
                  .draw2(
                     oSB,
                     this.getPosX() + 1 + iTranslateX,
                     this.getPosY() + 1 - ImageManager.getImage(Images.patt2).getHeight() + iTranslateY,
                     this.getWidth() - 1,
                     this.getHeight() - 1
                  );
               oSB.setColor(Color.WHITE);
            }

            oSB.setColor(new Color(CFG.COLOR_GRADIENT_DARK_BLUE.r, CFG.COLOR_GRADIENT_DARK_BLUE.g, CFG.COLOR_GRADIENT_DARK_BLUE.b, 0.55F));
            ImageManager.getImage(Images.slider_gradient)
               .draw(
                  oSB,
                  this.getPosX() + 1 + iTranslateX,
                  this.getPosY() + 1 - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY,
                  this.getMenuElement(9).getPosX() + this.getMenuElement(9).getWidth() + CFG.PADDING * 2,
                  this.getHeight() - 1
               );
            oSB.setColor(new Color(0.012F, 0.024F, 0.072F, 0.2F));
            ImageManager.getImage(Images.gradient)
               .draw(
                  oSB,
                  this.getPosX() + 1 + iTranslateX,
                  this.getPosY() + this.getHeight() - CFG.PADDING - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY,
                  this.getWidth() - 1,
                  CFG.PADDING,
                  false,
                  true
               );
            oSB.setColor(Color.WHITE);
         }
      }

      if (CFG.game.getActiveProvinceID() >= 0) {
         ImageManager.getImage(Images.bot_icons_bg)
            .draw2(
               oSB,
               CFG.GAME_WIDTH
                  - CFG.PADDING * 2
                  - this.getMenuElement(0).getWidth()
                  - CFG.PADDING * 2
                  - CFG.terrainTypesManager.getIcon(CFG.game.getProvince(activeProvinceInfo).getTerrainTypeID()).getWidth()
                  + iTranslateX,
               this.getPosY() - ImageManager.getImage(Images.bot_icons_bg).getHeight() + iTranslateY,
               CFG.PADDING * 2 + CFG.terrainTypesManager.getIcon(CFG.game.getProvince(activeProvinceInfo).getTerrainTypeID()).getWidth(),
               this.getHeight()
            );
         ImageManager.getImage(Images.bot_end_left)
            .draw2(
               oSB,
               CFG.GAME_WIDTH - CFG.PADDING * 2 - 1 - this.getMenuElement(0).getWidth() + iTranslateX,
               this.getPosY() - ImageManager.getImage(Images.bot_end_left).getHeight() + iTranslateY,
               this.getMenuElement(0).getWidth() + CFG.PADDING * 2 + 1,
               this.getHeight()
            );
         if (CFG.FOG_OF_WAR == 2 && !CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetProvince(activeProvinceInfo)) {
            ImageManager.getImage(Images.randomCivilizationFlag)
               .draw(
                  oSB,
                  CFG.GAME_WIDTH
                     - CFG.PADDING * 2
                     - this.getMenuElement(0).getWidth()
                     - CFG.PADDING
                     - CFG.terrainTypesManager.getIcon(CFG.game.getProvince(activeProvinceInfo).getTerrainTypeID()).getWidth()
                     + iTranslateX,
                  this.getPosY() + CFG.PADDING - ImageManager.getImage(Images.randomCivilizationFlag).getHeight() + iTranslateY,
                  CFG.CIV_FLAG_WIDTH,
                  CFG.CIV_FLAG_HEIGHT
               );
            ImageManager.getImage(Images.flag_rect)
               .draw(
                  oSB,
                  CFG.GAME_WIDTH
                     - CFG.PADDING * 2
                     - this.getMenuElement(0).getWidth()
                     - CFG.PADDING
                     - CFG.terrainTypesManager.getIcon(CFG.game.getProvince(activeProvinceInfo).getTerrainTypeID()).getWidth()
                     + iTranslateX,
                  this.getPosY() + CFG.PADDING + iTranslateY
               );
         } else {
            CFG.terrainTypesManager
               .getIcon(CFG.game.getProvince(activeProvinceInfo).getTerrainTypeID())
               .draw(
                  oSB,
                  CFG.GAME_WIDTH
                     - CFG.PADDING * 2
                     - this.getMenuElement(0).getWidth()
                     - CFG.PADDING
                     - CFG.terrainTypesManager.getIcon(CFG.game.getProvince(activeProvinceInfo).getTerrainTypeID()).getWidth()
                     + iTranslateX,
                  this.getPosY() + CFG.PADDING + iTranslateY
               );
         }

         if (this.getMenuElement(14).getVisible()) {
            ImageManager.getImage(Images.bot_prov_name_left)
               .draw2(
                  oSB,
                  CFG.GAME_WIDTH
                     - this.getMenuElement(14).getWidth()
                     - CFG.PADDING
                     - this.getMenuElement(0).getWidth()
                     - CFG.iProvinceNameWidth
                     - CFG.PADDING * 2
                     + iTranslateX,
                  CFG.GAME_HEIGHT
                     - (int)(CFG.TEXT_HEIGHT * 0.8F)
                     - CFG.PADDING * 2
                     - ImageManager.getImage(Images.bot_prov_name_left).getHeight()
                     + iTranslateY,
                  this.getMenuElement(14).getWidth(),
                  (int)(CFG.TEXT_HEIGHT * 0.8F) + CFG.PADDING * 2 + ImageManager.getImage(Images.bot_prov_name_left).getHeight()
               );
         }

         if (this.getMenuElement(1).getVisible()) {
            ImageManager.getImage(Images.bot_prov_name)
               .draw2(
                  oSB,
                  CFG.GAME_WIDTH - CFG.PADDING * 2 - this.getMenuElement(0).getWidth() - ImageManager.getImage(Images.bot_prov_name).getWidth() + iTranslateX,
                  CFG.GAME_HEIGHT
                     - ((int)(CFG.TEXT_HEIGHT * 0.8F) + CFG.PADDING * 2 + this.box.iProvinceName)
                     - ImageManager.getImage(Images.bot_prov_name).getHeight()
                     + iTranslateY,
                  ImageManager.getImage(Images.bot_prov_name).getWidth(),
                  (int)(CFG.TEXT_HEIGHT * 0.8F) + CFG.PADDING * 2 + this.box.iProvinceName
               );
            ImageManager.getImage(Images.bot_prov_name_left)
               .draw2(
                  oSB,
                  CFG.GAME_WIDTH - CFG.PADDING * 2 - this.getMenuElement(0).getWidth() - CFG.iProvinceNameWidth - CFG.PADDING * 2 + iTranslateX,
                  CFG.GAME_HEIGHT
                     - (int)(CFG.TEXT_HEIGHT * 0.8F)
                     - CFG.PADDING * 2
                     - ImageManager.getImage(Images.bot_prov_name_left).getHeight()
                     + iTranslateY,
                  CFG.iProvinceNameWidth + CFG.PADDING * 2 - ImageManager.getImage(Images.bot_prov_name).getWidth(),
                  (int)(CFG.TEXT_HEIGHT * 0.8F) + CFG.PADDING * 2 + ImageManager.getImage(Images.bot_prov_name_left).getHeight()
               );
         } else {
            ImageManager.getImage(Images.bot_end_left)
               .draw2(
                  oSB,
                  CFG.GAME_WIDTH - CFG.PADDING * 2 - 1 - this.getMenuElement(0).getWidth() + iTranslateX,
                  this.getPosY() - ImageManager.getImage(Images.bot_end_left).getHeight() + iTranslateY,
                  this.getMenuElement(0).getWidth() + CFG.PADDING * 2 + 1,
                  this.getHeight()
               );
         }
      } else {
         ImageManager.getImage(Images.bot_end_left)
            .draw2(
               oSB,
               CFG.GAME_WIDTH - CFG.PADDING * 2 - 1 - this.getMenuElement(0).getWidth() + iTranslateX,
               this.getPosY() - ImageManager.getImage(Images.bot_end_left).getHeight() + iTranslateY,
               this.getMenuElement(0).getWidth() + CFG.PADDING * 2 + 1,
               this.getHeight()
            );
      }

      super.draw(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
      if (iMaxWidth >= 0) {
         oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.7F));
         ImageManager.getImage(Images.line_32_off1)
            .draw(
               oSB, iTranslateX, CFG.GAME_HEIGHT - CFG.map.getMapBG().getMinimapHeight() - 1 + iTranslateY, CFG.map.getMapBG().getMinimapWidth() + iMaxWidth, 1
            );
      } else {
         oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.7F));
         ImageManager.getImage(Images.line_32_off1)
            .draw(oSB, iTranslateX, CFG.GAME_HEIGHT - CFG.map.getMapBG().getMinimapHeight() - 1 + iTranslateY, CFG.GAME_WIDTH, 1);
      }

      oSB.setColor(Color.WHITE);
   }

   public static final float getImageScale(int nImage) {
      return CFG.TEXT_HEIGHT * 0.8F / ImageManager.getImage(nImage).getHeight();
   }

   public static final void clickEndTurn() {
      if (!CFG.tutorialManager.IN_TUTORIAL || !CFG.tutorialManager.tutStep.action(Tutorial_ActionType.NEXT_TURN)) {
         if (CFG.gameAction.getActiveTurnState() == Game_Action.TurnStates.INPUT_ORDERS) {
            if (CFG.settingsManager.CONFIRM_NO_ORDERS && CFG.game.getPlayer(CFG.PLAYER_TURNID).getNoOrders()) {
               CFG.setDialogType(Dialog.NO_ORDERS);
            } else if (CFG.settingsManager.CONFIRM_END_TURN) {
               CFG.setDialogType(Dialog.CONFIRM_END_TURN);
            } else {
               CFG.gameAction.tryToTakeNexTurn();
            }
         } else {
            CFG.gameAction.tryToTakeNexTurn();
         }

         if (RTS.isEnabled() && !RTS.PAUSE) {
            RTS.resetTime();
         }
      }
   }

   @Override
   public final void actionElement(int iID) {
      switch (iID) {
         case 0:
            if (CFG.gameAction.getActiveTurnState() == Game_Action.TurnStates.INPUT_ORDERS) {
               if (!RTS.PAUSE) {
                  RTS.PAUSED_BY_NEXT_TURN = true;
               }

               RTS.PAUSE = true;
               RTS.resetTime();
            }

            clickEndTurn();
            break;
         case 1:
            Keyboard.changeCivilizationNameMode = -1;
            Keyboard.changeProvinceNameMode = CFG.game.getActiveProvinceID();
            Keyboard.changeCityNameIDToo = -1;

            for (int c = 0; c < CFG.game.getProvince(Keyboard.changeProvinceNameMode).getCitiesSize(); c++) {
               if (CFG.game.getProvince(Keyboard.changeProvinceNameMode).getCity(c).getCityName().equals(this.getMenuElement(iID).getText())) {
                  Keyboard.changeCityNameIDToo = c;
                  break;
               }
            }

            CFG.updateKeyboard_Actions();
            CFG.showKeyboard();
            break;
         case 2:
            if (CFG.gameAction.getActiveTurnState() == Game_Action.TurnStates.INPUT_ORDERS
               && CFG.game.getActiveProvinceID() >= 0
               && !CFG.menuManager.getVisible_InGame_FlagAction()) {
               CFG.menuManager.setVisible_InGame_CivInfo(!CFG.menuManager.getVisible_InGame_CivInfo());
            }
            break;
         case 3:
            if (CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetProvince(CFG.ACTIVE_PROVINCE_INFO)) {
               if (CFG.viewsManager.getActiveViewID() != ViewsManager.VIEW_CORES_MODE) {
                  CFG.viewsManager.setActiveViewID(ViewsManager.VIEW_CORES_MODE);
                  CFG.toast.setInView(CFG.langManager.get("Cores"), CFG.COLOR_TEXT_NUM_OF_PROVINCES);
                  CFG.toast.setTimeInView(1500);

                  try {
                     if (CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getCivID() != CFG.getActiveCivInfo()) {
                        CFG.setActiveCivInfo(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getCivID());
                     }
                  } catch (IndexOutOfBoundsException var6) {
                     CFG.exceptionStack(var6);
                  }

                  return;
               }

               if (CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getCore().getCivsSize() > 1) {
                  int currID = 0;

                  for (int i = 0; i < CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getCore().getCivsSize(); i++) {
                     if (CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getCore().getCivID(i) == CFG.getActiveCivInfo()) {
                        currID = i;
                        break;
                     }
                  }

                  boolean disableView = false;
                  if (++currID >= CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getCore().getCivsSize()) {
                     currID = 0;
                     disableView = true;
                  }

                  if (!CFG.menuManager.getVisible_InGame_CivInfo()) {
                     CFG.menuManager.setVisible_InGame_CivInfo(!CFG.menuManager.getVisible_InGame_CivInfo());
                  }

                  CFG.setActiveCivInfo(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getCore().getCivID(currID));
                  CFG.updateActiveCivInfo_InGame();
                  if (disableView) {
                     CFG.viewsManager.setActiveViewID(ViewsManager.VIEW_CORES_MODE);
                     if (CFG.viewsManager.getActiveViewID() == ViewsManager.VIEW_CORES_MODE) {
                        CFG.toast.setInView(CFG.langManager.get("Cores"), CFG.COLOR_TEXT_NUM_OF_PROVINCES);
                        CFG.toast.setTimeInView(1500);
                     }

                     CFG.menuManager.setVisible_InGame_CivInfo(!CFG.menuManager.getVisible_InGame_CivInfo());
                  } else if (CFG.viewsManager.getActiveViewID() != ViewsManager.VIEW_CORES_MODE) {
                     CFG.viewsManager.setActiveViewID(ViewsManager.VIEW_CORES_MODE);
                     CFG.toast.setInView(CFG.langManager.get("Cores"), CFG.COLOR_TEXT_NUM_OF_PROVINCES);
                     CFG.toast.setTimeInView(1500);
                  }
               } else {
                  CFG.viewsManager.setActiveViewID(ViewsManager.VIEW_CORES_MODE);
                  if (CFG.viewsManager.getActiveViewID() == ViewsManager.VIEW_CORES_MODE) {
                     CFG.toast.setInView(CFG.langManager.get("Cores"), CFG.COLOR_TEXT_NUM_OF_PROVINCES);
                     CFG.toast.setTimeInView(1500);

                     try {
                        if (CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getCivID() != CFG.getActiveCivInfo()) {
                           CFG.setActiveCivInfo(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getCivID());
                        }
                     } catch (IndexOutOfBoundsException var8) {
                        CFG.exceptionStack(var8);
                     }
                  }
               }
            } else {
               CFG.viewsManager.setActiveViewID(ViewsManager.VIEW_CORES_MODE);
               if (CFG.viewsManager.getActiveViewID() == ViewsManager.VIEW_CORES_MODE) {
                  CFG.toast.setInView(CFG.langManager.get("Cores"), CFG.COLOR_TEXT_NUM_OF_PROVINCES);
                  CFG.toast.setTimeInView(1500);

                  try {
                     if (CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getCivID() != CFG.getActiveCivInfo()) {
                        CFG.setActiveCivInfo(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).getCivID());
                     }
                  } catch (IndexOutOfBoundsException var7) {
                     CFG.exceptionStack(var7);
                  }
               }
            }
            break;
         case 4:
            CFG.viewsManager.setActiveViewID(ViewsManager.VIEW_POPULATION_MODE);
            if (CFG.viewsManager.getActiveViewID() == ViewsManager.VIEW_POPULATION_MODE) {
               CFG.toast.setInView(CFG.langManager.get("Population"), CFG.COLOR_TEXT_NUM_OF_PROVINCES);
            }
         case 5:
         case 18:
         case 22:
         default:
            break;
         case 6:
            CFG.viewsManager.setActiveViewID(ViewsManager.VIEW_TERRAIN_TYPE_MODE);
            if (CFG.viewsManager.getActiveViewID() == ViewsManager.VIEW_TERRAIN_TYPE_MODE) {
               CFG.toast.setInView(CFG.langManager.get("TerrainType"), CFG.COLOR_TEXT_NUM_OF_PROVINCES);
            }

            return;
         case 7:
         case 17:
            CFG.viewsManager.setActiveViewID(ViewsManager.VIEW_ECONOMY_MODE);
            if (CFG.viewsManager.getActiveViewID() == ViewsManager.VIEW_ECONOMY_MODE) {
               CFG.toast.setInView(CFG.langManager.get("Economy"), CFG.COLOR_TEXT_NUM_OF_PROVINCES);
            }

            return;
         case 8:
            CFG.viewsManager.setActiveViewID(ViewsManager.VIEW_PROVINCE_VALUE_MODE);
            if (CFG.viewsManager.getActiveViewID() == ViewsManager.VIEW_PROVINCE_VALUE_MODE) {
               CFG.toast.setInView(CFG.langManager.get("ProvinceValue"), CFG.COLOR_TEXT_NUM_OF_PROVINCES);
            }

            return;
         case 9:
            CFG.viewsManager.setActiveViewID(ViewsManager.VIEW_GROWTH_RATE_MODE);
            if (CFG.viewsManager.getActiveViewID() == ViewsManager.VIEW_GROWTH_RATE_MODE) {
               CFG.toast.setInView(CFG.langManager.get("GrowthRate"), CFG.COLOR_TEXT_NUM_OF_PROVINCES);
            }

            return;
         case 10:
            CFG.viewsManager.setActiveViewID(ViewsManager.VIEW_DEVELOPMENT_MODE);
            if (CFG.viewsManager.getActiveViewID() == ViewsManager.VIEW_DEVELOPMENT_MODE) {
               CFG.toast.setInView(CFG.langManager.get("Development"), CFG.COLOR_TEXT_NUM_OF_PROVINCES);
            }

            return;
         case 11:
         case 12:
            CFG.viewsManager.setActiveViewID(ViewsManager.VIEW_HAPPINESS_MODE);
            if (CFG.viewsManager.getActiveViewID() == ViewsManager.VIEW_HAPPINESS_MODE) {
               CFG.toast.setInView(CFG.langManager.get("Happiness"), CFG.COLOR_TEXT_NUM_OF_PROVINCES);
            }

            return;
         case 13:
         case 16:
            CFG.viewsManager.setActiveViewID(ViewsManager.VIEW_PROVINCE_STABILITY_MODE);
            if (CFG.viewsManager.getActiveViewID() == ViewsManager.VIEW_PROVINCE_STABILITY_MODE) {
               ArrayList<String> lMess = new ArrayList<>();
               ArrayList<Color> lColors = new ArrayList<>();
               lMess.add(CFG.langManager.get("ProvinceStability"));
               lColors.add(CFG.COLOR_TEXT_NUM_OF_PROVINCES);
               CFG.toast.setInView(lMess, lColors);
            }

            return;
         case 14:
            CFG.viewsManager.setActiveViewID(ViewsManager.VIEW_BUILDINGS_MODE);
            if (CFG.viewsManager.getActiveViewID() == ViewsManager.VIEW_BUILDINGS_MODE) {
               CFG.toast.setInView(CFG.langManager.get("Buildings"), CFG.COLOR_TEXT_NUM_OF_PROVINCES);
            }

            return;
         case 15:
         case 23:
            CFG.viewsManager.setActiveViewID(ViewsManager.VIEW_REVOLUTION_MODE);
            if (CFG.viewsManager.getActiveViewID() == ViewsManager.VIEW_REVOLUTION_MODE) {
               CFG.toast.setInView(CFG.langManager.get("RevolutionaryRisk"), CFG.COLOR_TEXT_NUM_OF_PROVINCES);
            }

            return;
         case 19:
            CFG.viewsManager.setActiveViewID(ViewsManager.VIEW_SUPPLIES_MODE);
            if (CFG.viewsManager.getActiveViewID() == ViewsManager.VIEW_SUPPLIES_MODE) {
               CFG.toast.setInView(CFG.langManager.get("Supplies"), CFG.COLOR_TEXT_NUM_OF_PROVINCES);
            }

            return;
         case 20:
            CFG.toast
               .setInView(
                  CFG.langManager.get("MilitaryUpkeep")
                     + ": -"
                     + Math.ceil((int)(CFG.game_NextTurnUpdate.getMilitaryUpkeep_DefensivePosition(CFG.ACTIVE_PROVINCE_INFO) * 1000.0F)) / 10.0
                     + "%",
                  CFG.COLOR_TEXT_MODIFIER_POSITIVE
               );
            return;
         case 21:
            CFG.viewsManager.setActiveViewID(ViewsManager.VIEW_DISEASES_MODE);
            if (CFG.viewsManager.getActiveViewID() == ViewsManager.VIEW_DISEASES_MODE) {
               try {
                  CFG.toast
                     .setInView(
                        CFG.plagueManager
                           .getPlague_InGame(CFG.game.getProvince(CFG.ACTIVE_PROVINCE_INFO).saveProvinceData.provincePlague.iPlagueID_InGame)
                           .getPlagueName(),
                        CFG.COLOR_TEXT_MODIFIER_NEGATIVE2
                     );
               } catch (IndexOutOfBoundsException var4) {
               } catch (NullPointerException var5) {
               }
            }

            return;
      }
   }

   @Override
   public void onHovered() {
      CFG.menuManager.setOrderOfMenu_InGame();
   }

   public class Box {
      public int iProvinceName;
   }
}
