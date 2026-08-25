package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

public class Menu_InGame_ContinentPopulation extends SliderMenu {
   public static int iSort = 1;
   public static int CONTINENT_ID = 0;

   public Menu_InGame_ContinentPopulation(int tInit) {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      int tempWidth = CFG.CIV_INFO_MENU_WIDTH * 3;
      int tempMenuPosY = ImageManager.getImage(Images.top_flag_frame).getHeight() + CFG.PADDING * 4 + CFG.BUTTON_HEIGHT * 3 / 5 + CFG.PADDING * 2;
      this.initMenu(null, CFG.GAME_WIDTH / 2 - tempWidth / 2, tempMenuPosY, tempWidth, 5, menuElements, false, false);
   }

   public Menu_InGame_ContinentPopulation() {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      int tempWidth = CFG.CIV_INFO_MENU_WIDTH * 3;
      int tElemHeight = CFG.TEXT_HEIGHT + CFG.PADDING * 4;
      int tElemHeight2 = CFG.isAndroid() ? CFG.TEXT_HEIGHT + CFG.PADDING * 4 : CFG.TEXT_HEIGHT + CFG.PADDING * 4;
      menuElements.add(new Button_Statistics_Title(CFG.langManager.get("Civilization"), CFG.PADDING * 2, 2, 0, CFG.BUTTON_WIDTH * 2, tElemHeight) {
         @Override
         public int getWidth() {
            return Menu_InGame_ContinentPopulation.this.getElementW() * 2 + CFG.PADDING * 2 - 2;
         }

         @Override
         public Color getColor(boolean isActive) {
            return Menu_InGame_ContinentPopulation.iSort == 0 ? CFG.COLOR_TEXT_NUM_OF_PROVINCES : super.getColor(isActive);
         }

         @Override
         public void buildElementHover() {
            ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
            ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("SortBy") + ": "));
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(this.getText(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
            this.menuElementHover = new MenuElement_Hover_v2(nElements);
         }
      });
      menuElements.add(
         new Button_Statistics_Title(CFG.langManager.get("Population"), CFG.PADDING, CFG.PADDING * 2 + CFG.BUTTON_WIDTH * 2, 0, CFG.BUTTON_WIDTH, tElemHeight) {
            @Override
            public int getPosX() {
               return Menu_InGame_ContinentPopulation.this.getElementW() * 2 + CFG.PADDING * 2;
            }

            @Override
            public int getWidth() {
               return Menu_InGame_ContinentPopulation.this.getElementW();
            }

            @Override
            public Color getColor(boolean isActive) {
               return Menu_InGame_ContinentPopulation.iSort == 1 ? CFG.COLOR_TEXT_NUM_OF_PROVINCES : super.getColor(isActive);
            }

            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("SortBy") + ": "));
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(this.getText(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
         }
      );
      menuElements.add(
         new Button_Statistics_Title(CFG.langManager.get("Provinces"), CFG.PADDING, CFG.PADDING * 2 + CFG.BUTTON_WIDTH * 3, 0, CFG.BUTTON_WIDTH, tElemHeight) {
            @Override
            public int getPosX() {
               return Menu_InGame_ContinentPopulation.this.getElementW() * 3 + CFG.PADDING * 2;
            }

            @Override
            public int getWidth() {
               return Menu_InGame_ContinentPopulation.this.getElementW();
            }

            @Override
            public Color getColor(boolean isActive) {
               return Menu_InGame_ContinentPopulation.iSort == 2 ? CFG.COLOR_TEXT_NUM_OF_PROVINCES : super.getColor(isActive);
            }

            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("SortBy") + ": "));
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(this.getText(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
         }
      );
      menuElements.add(
         new Button_Statistics_Title(CFG.langManager.get("LargestCity"), CFG.PADDING, CFG.PADDING * 2 + CFG.BUTTON_WIDTH * 5, 0, CFG.BUTTON_WIDTH, tElemHeight) {
            @Override
            public int getPosX() {
               return Menu_InGame_ContinentPopulation.this.getElementW() * 4 + CFG.PADDING * 2;
            }

            @Override
            public int getWidth() {
               return Menu_InGame_ContinentPopulation.this.getW() - Menu_InGame_ContinentPopulation.this.getElementW() * 4 + CFG.PADDING * 2 - 2;
            }

            @Override
            public Color getColor(boolean isActive) {
               return Menu_InGame_ContinentPopulation.iSort == 5 ? CFG.COLOR_TEXT_NUM_OF_PROVINCES : super.getColor(isActive);
            }

            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("SortBy") + ": "));
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(this.getText(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
         }
      );
      int tPosY = CFG.PADDING + tElemHeight;
      ArrayList<Integer> tPopulation = new ArrayList<>();
      ArrayList<Integer> tCivilizations = new ArrayList<>();
      ArrayList<Integer> tProvinces = new ArrayList<>();
      ArrayList<Integer> tLargestCity = new ArrayList<>();

      for (int i3 = 0; i3 < CFG.game.getProvincesSize(); i3++) {
         if (CFG.game.getProvince(i3).getContinent() == CONTINENT_ID
            && CFG.game.getProvince(i3).getWasteland() < 0
            && !CFG.game.getProvince(i3).getSeaProvince()) {
            boolean tAdd2 = true;

            for (int j = 0; j < tCivilizations.size(); j++) {
               if (tCivilizations.get(j) == CFG.game.getProvince(i3).getCivID()) {
                  tAdd2 = false;
                  break;
               }
            }

            if (tAdd2) {
               tPopulation.add(0);
               tCivilizations.add(CFG.game.getProvince(i3).getCivID());
               tProvinces.add(0);
               tLargestCity.add(i3);
            }
         }
      }

      for (int var31 = 0; var31 < CFG.game.getProvincesSize(); var31++) {
         if (CFG.game.getProvince(var31).getWasteland() < 0
            && !CFG.game.getProvince(var31).getSeaProvince()
            && CFG.game.getProvince(var31).getContinent() == CONTINENT_ID) {
            int tCivID = 0;

            for (int jx = 0; jx < tCivilizations.size(); jx++) {
               if (tCivilizations.get(jx) == CFG.game.getProvince(var31).getCivID()) {
                  tCivID = jx;
               }
            }

            tPopulation.set(tCivID, tPopulation.get(tCivID) + CFG.game.getProvince(var31).getPopulationData().getPopulation());
            tProvinces.set(tCivID, tProvinces.get(tCivID) + 1);
            if (CFG.game.getProvince(tLargestCity.get(tCivID)).getPopulationData().getPopulation()
               < CFG.game.getProvince(var31).getPopulationData().getPopulation()) {
               tLargestCity.set(tCivID, var31);
            }
         }
      }

      int tTotalPop = 0;
      boolean tCivsTotal = false;
      int tProvincesTotal = 0;
      int tLargestCityTotal = -1;

      for (int i4 = 0; i4 < tCivilizations.size(); i4++) {
         tTotalPop += tPopulation.get(i4);
         tProvincesTotal += tProvinces.get(i4);
         if (tLargestCityTotal < 0) {
            if (tLargestCity.get(i4) >= 0) {
               tLargestCityTotal = tLargestCity.get(i4);
            }
         } else if (tLargestCity.get(i4) >= 0
            && CFG.game.getProvince(tLargestCityTotal).getPopulationData().getPopulation()
               < CFG.game.getProvince(tLargestCity.get(i4)).getPopulationData().getPopulation()) {
            tLargestCityTotal = tLargestCity.get(i4);
         }
      }

      menuElements.add(
         new Button_Statistics_Color(
            new Color(
               CFG.map.getMapContinents().getColor(CONTINENT_ID).r,
               CFG.map.getMapContinents().getColor(CONTINENT_ID).g,
               CFG.map.getMapContinents().getColor(CONTINENT_ID).b,
               0.95F
            ),
            "" + CFG.map.getMapContinents().getName(CONTINENT_ID),
            CFG.PADDING,
            CFG.PADDING * 2,
            tPosY,
            CFG.BUTTON_WIDTH * 2,
            tElemHeight2
         ) {
            @Override
            public int getWidth() {
               return Menu_InGame_ContinentPopulation.this.getElementW() * 2;
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
         }
      );
      menuElements.add(
         new Button_Statistics(
            CFG.getNumberWithSpaces("" + tTotalPop), CFG.PADDING, CFG.PADDING * 2 + CFG.BUTTON_WIDTH * 2, tPosY, CFG.BUTTON_WIDTH, tElemHeight2
         ) {
            @Override
            public int getPosX() {
               return Menu_InGame_ContinentPopulation.this.getElementW() * 2 + CFG.PADDING * 2;
            }

            @Override
            public int getWidth() {
               return Menu_InGame_ContinentPopulation.this.getElementW();
            }
         }
      );
      menuElements.add(
         new Button_Statistics(
            CFG.getNumberWithSpaces("" + tProvincesTotal), CFG.PADDING, CFG.PADDING * 2 + CFG.BUTTON_WIDTH * 4, tPosY, CFG.BUTTON_WIDTH, tElemHeight2
         ) {
            @Override
            public int getPosX() {
               return Menu_InGame_ContinentPopulation.this.getElementW() * 3 + CFG.PADDING * 2;
            }

            @Override
            public int getWidth() {
               return Menu_InGame_ContinentPopulation.this.getElementW();
            }
         }
      );
      menuElements.add(
         new Button_Statistics_Flag_Clip_ProvinceID(
            CFG.FOG_OF_WAR == 2 ? (CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetProvince(tLargestCityTotal) ? tLargestCityTotal : -1) : tLargestCityTotal,
            CFG.FOG_OF_WAR == 2
               ? (
                  CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetProvince(tLargestCityTotal)
                     ? (
                        CFG.game.getProvince(tLargestCityTotal).getCitiesSize() > 0
                           ? CFG.game.getProvince(tLargestCityTotal).getCity(0).getCityName()
                           : (
                              CFG.game.getProvince(tLargestCityTotal).getName().length() > 0
                                 ? CFG.game.getProvince(tLargestCityTotal).getName()
                                 : CFG.langManager.get("NoData")
                           )
                     )
                     : CFG.langManager.get("Undiscovered")
               )
               : (
                  CFG.game.getProvince(tLargestCityTotal).getCitiesSize() > 0
                     ? CFG.game.getProvince(tLargestCityTotal).getCity(0).getCityName()
                     : (
                        CFG.game.getProvince(tLargestCityTotal).getName().length() > 0
                           ? CFG.game.getProvince(tLargestCityTotal).getName()
                           : CFG.langManager.get("NoData")
                     )
               ),
            CFG.PADDING,
            CFG.PADDING * 2 + CFG.BUTTON_WIDTH * 5,
            tPosY,
            CFG.BUTTON_WIDTH,
            tElemHeight2
         ) {
            @Override
            public int getPosX() {
               return Menu_InGame_ContinentPopulation.this.getElementW() * 4 + CFG.PADDING * 2;
            }

            @Override
            public int getWidth() {
               return Menu_InGame_ContinentPopulation.this.getW() - Menu_InGame_ContinentPopulation.this.getElementW() * 4;
            }

            @Override
            public void buildElementHover() {
               try {
                  ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
                  ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
                  if (CFG.FOG_OF_WAR == 2) {
                     if (this.getCurrent() < 0) {
                        nData.add(new MenuElement_Hover_v2_Element_Type_Flag(-1));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(this.getText(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                     } else {
                        nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getProvince(this.getCurrent()).getCivID()));
                        nData.add(
                           new MenuElement_Hover_v2_Element_Type_Text(
                              CFG.game.getCiv(CFG.game.getProvince(this.getCurrent()).getCivID()).getCivName() + " - " + this.getText(),
                              CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                           )
                        );
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Population") + ": "));
                        nData.add(
                           new MenuElement_Hover_v2_Element_Type_Text(
                              CFG.getNumberWithSpaces("" + CFG.game.getProvince(this.getCurrent()).getPopulationData().getPopulation()),
                              CFG.COLOR_TEXT_POPULATION
                           )
                        );
                        nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.population, CFG.PADDING, 0));
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                     }
                  } else {
                     nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getProvince(this.getCurrent()).getCivID()));
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           CFG.game.getCiv(CFG.game.getProvince(this.getCurrent()).getCivID()).getCivName() + " - " + this.getText(),
                           CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                        )
                     );
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Population") + ": "));
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           CFG.getNumberWithSpaces("" + CFG.game.getProvince(this.getCurrent()).getPopulationData().getPopulation()), CFG.COLOR_TEXT_POPULATION
                        )
                     );
                     nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.population, CFG.PADDING, 0));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                  }

                  this.menuElementHover = new MenuElement_Hover_v2(nElements);
               } catch (IndexOutOfBoundsException var3) {
               }
            }
         }
      );
      tPosY += tElemHeight2;
      ArrayList tSorted = new ArrayList();
      ArrayList<Integer> tempIDs = new ArrayList<>();

      for (int i2 = 0; i2 < tCivilizations.size(); i2++) {
         tempIDs.add(i2);
      }

      if (iSort != 0) {
         if (iSort == 1) {
            while (tempIDs.size() > 0) {
               int tAdd = 0;

               for (int i = 1; i < tempIDs.size(); i++) {
                  if (tPopulation.get(tempIDs.get(tAdd)) < tPopulation.get(tempIDs.get(i))) {
                     tAdd = i;
                  }
               }

               tSorted.add(tempIDs.get(tAdd));
               tempIDs.remove(tAdd);
            }
         } else if (iSort == 2) {
            while (tempIDs.size() > 0) {
               int tAdd = 0;

               for (int ix = 1; ix < tempIDs.size(); ix++) {
                  if (tProvinces.get(tempIDs.get(tAdd)) < tProvinces.get(tempIDs.get(ix))) {
                     tAdd = ix;
                  }
               }

               tSorted.add(tempIDs.get(tAdd));
               tempIDs.remove(tAdd);
            }
         } else if (iSort == 3) {
            while (tempIDs.size() > 0) {
               int tAdd = 0;

               for (int ixx = 1; ixx < tempIDs.size(); ixx++) {
                  if (CFG.game.getProvince(tLargestCity.get(tempIDs.get(tAdd))).getPopulationData().getPopulation()
                     < CFG.game.getProvince(tLargestCity.get(tempIDs.get(ixx))).getPopulationData().getPopulation()) {
                     tAdd = ixx;
                  }
               }

               tSorted.add(tempIDs.get(tAdd));
               tempIDs.remove(tAdd);
            }
         }
      } else {
         while (tempIDs.size() > 0) {
            int tAdd = 0;

            for (int ixxx = 1; ixxx < tempIDs.size(); ixxx++) {
               if (CFG.compareAlphabetic_TwoString(
                  CFG.game.getCiv(tCivilizations.get(tempIDs.get(tAdd))).getCivName(), CFG.game.getCiv(tCivilizations.get(tempIDs.get(ixxx))).getCivName()
               )) {
                  tAdd = ixxx;
               }
            }

            tSorted.add(tempIDs.get(tAdd));
            tempIDs.remove(tAdd);
         }
      }

      for (int var29 = 0; var29 < tSorted.size(); var29++) {
         menuElements.add(
            new Button_Statistics_Flag_Clip(
               CFG.FOG_OF_WAR >= 2 && !CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetCivilization(tCivilizations.get((Integer)tSorted.get(var29)))
                  ? -1
                  : tCivilizations.get((Integer)tSorted.get(var29)),
               CFG.FOG_OF_WAR >= 2 && !CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetCivilization(tCivilizations.get((Integer)tSorted.get(var29)))
                  ? CFG.langManager.get("Undiscovered")
                  : CFG.game.getCiv(tCivilizations.get((Integer)tSorted.get(var29))).getCivName(),
               CFG.PADDING,
               CFG.PADDING * 2,
               tPosY,
               CFG.BUTTON_WIDTH * 2,
               tElemHeight2
            ) {
               @Override
               public int getWidth() {
                  return Menu_InGame_ContinentPopulation.this.getElementW() * 2;
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
            }
         );
         menuElements.add(
            new Button_Statistics(
               CFG.getNumberWithSpaces("" + tPopulation.get((Integer)tSorted.get(var29))),
               CFG.PADDING,
               CFG.PADDING * 2 + CFG.BUTTON_WIDTH * 2,
               tPosY,
               CFG.BUTTON_WIDTH,
               tElemHeight2
            ) {
               @Override
               public int getPosX() {
                  return Menu_InGame_ContinentPopulation.this.getElementW() * 2 + CFG.PADDING * 2;
               }

               @Override
               public int getWidth() {
                  return Menu_InGame_ContinentPopulation.this.getElementW();
               }
            }
         );
         menuElements.add(
            new Button_Statistics(
               CFG.getNumberWithSpaces("" + tProvinces.get((Integer)tSorted.get(var29))),
               CFG.PADDING,
               CFG.PADDING * 2 + CFG.BUTTON_WIDTH * 4,
               tPosY,
               CFG.BUTTON_WIDTH,
               tElemHeight2
            ) {
               @Override
               public int getPosX() {
                  return Menu_InGame_ContinentPopulation.this.getElementW() * 3 + CFG.PADDING * 2;
               }

               @Override
               public int getWidth() {
                  return Menu_InGame_ContinentPopulation.this.getElementW();
               }
            }
         );
         menuElements.add(
            new Button_Statistics_Flag_Clip_ProvinceID(
               CFG.FOG_OF_WAR == 2
                  ? (
                     CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetProvince(tLargestCity.get((Integer)tSorted.get(var29)))
                        ? tLargestCity.get((Integer)tSorted.get(var29))
                        : -1
                  )
                  : tLargestCity.get((Integer)tSorted.get(var29)),
               CFG.FOG_OF_WAR == 2
                  ? (
                     CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetProvince(tLargestCity.get((Integer)tSorted.get(var29)))
                        ? (
                           CFG.game.getProvince(tLargestCity.get((Integer)tSorted.get(var29))).getCitiesSize() > 0
                              ? CFG.game.getProvince(tLargestCity.get((Integer)tSorted.get(var29))).getCity(0).getCityName()
                              : (
                                 CFG.game.getProvince(tLargestCity.get((Integer)tSorted.get(var29))).getName().length() > 0
                                    ? CFG.game.getProvince(tLargestCity.get((Integer)tSorted.get(var29))).getName()
                                    : CFG.langManager.get("NoData")
                              )
                        )
                        : CFG.langManager.get("Undiscovered")
                  )
                  : (
                     CFG.game.getProvince(tLargestCity.get((Integer)tSorted.get(var29))).getCitiesSize() > 0
                        ? CFG.game.getProvince(tLargestCity.get((Integer)tSorted.get(var29))).getCity(0).getCityName()
                        : (
                           CFG.game.getProvince(tLargestCity.get((Integer)tSorted.get(var29))).getName().length() > 0
                              ? CFG.game.getProvince(tLargestCity.get((Integer)tSorted.get(var29))).getName()
                              : CFG.langManager.get("NoData")
                        )
                  ),
               CFG.PADDING,
               CFG.PADDING * 2 + CFG.BUTTON_WIDTH * 5,
               tPosY,
               CFG.BUTTON_WIDTH,
               tElemHeight2
            ) {
               @Override
               public int getPosX() {
                  return Menu_InGame_ContinentPopulation.this.getElementW() * 4 + CFG.PADDING * 2;
               }

               @Override
               public int getWidth() {
                  return Menu_InGame_ContinentPopulation.this.getW() - Menu_InGame_ContinentPopulation.this.getElementW() * 4;
               }

               @Override
               public void buildElementHover() {
                  try {
                     ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
                     ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
                     if (CFG.FOG_OF_WAR == 2) {
                        if (this.getCurrent() < 0) {
                           nData.add(new MenuElement_Hover_v2_Element_Type_Flag(-1));
                           nData.add(new MenuElement_Hover_v2_Element_Type_Text(this.getText(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                           nElements.add(new MenuElement_Hover_v2_Element2(nData));
                           nData.clear();
                        } else {
                           nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getProvince(this.getCurrent()).getCivID()));
                           nData.add(
                              new MenuElement_Hover_v2_Element_Type_Text(
                                 CFG.game.getCiv(CFG.game.getProvince(this.getCurrent()).getCivID()).getCivName() + " - " + this.getText(),
                                 CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                              )
                           );
                           nElements.add(new MenuElement_Hover_v2_Element2(nData));
                           nData.clear();
                           nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Population") + ": "));
                           nData.add(
                              new MenuElement_Hover_v2_Element_Type_Text(
                                 CFG.getNumberWithSpaces("" + CFG.game.getProvince(this.getCurrent()).getPopulationData().getPopulation()),
                                 CFG.COLOR_TEXT_POPULATION
                              )
                           );
                           nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.population, CFG.PADDING, 0));
                           nElements.add(new MenuElement_Hover_v2_Element2(nData));
                           nData.clear();
                        }
                     } else {
                        nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getProvince(this.getCurrent()).getCivID()));
                        nData.add(
                           new MenuElement_Hover_v2_Element_Type_Text(
                              CFG.game.getCiv(CFG.game.getProvince(this.getCurrent()).getCivID()).getCivName() + " - " + this.getText(),
                              CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                           )
                        );
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Population") + ": "));
                        nData.add(
                           new MenuElement_Hover_v2_Element_Type_Text(
                              CFG.getNumberWithSpaces("" + CFG.game.getProvince(this.getCurrent()).getPopulationData().getPopulation()),
                              CFG.COLOR_TEXT_POPULATION
                           )
                        );
                        nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.population, CFG.PADDING, 0));
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                     }

                     this.menuElementHover = new MenuElement_Hover_v2(nElements);
                  } catch (IndexOutOfBoundsException var3) {
                  }
               }
            }
         );
         tPosY += tElemHeight2;
      }

      int tempMenuPosY = ImageManager.getImage(Images.top_flag_frame).getHeight() + CFG.PADDING * 4 + CFG.BUTTON_HEIGHT * 3 / 5 + CFG.PADDING * 2;
      this.initMenu(
         new SliderMenuTitle(CFG.map.getMapContinents().getName(CONTINENT_ID), CFG.BUTTON_HEIGHT * 3 / 5, true, true) {
            @Override
            public void draw(SpriteBatch oSB, int iTranslateX, int nPosX, int nPosY, int nWidth, boolean sliderMenuIsActive) {
               ImageManager.getImage(Images.dialog_title)
                  .draw2(
                     oSB,
                     nPosX + iTranslateX,
                     nPosY - this.getHeight() - ImageManager.getImage(Images.dialog_title).getHeight(),
                     nWidth - ImageManager.getImage(Images.dialog_title).getWidth(),
                     this.getHeight()
                  );
               ImageManager.getImage(Images.dialog_title)
                  .draw2(
                     oSB,
                     nPosX + nWidth - ImageManager.getImage(Images.dialog_title).getWidth() + iTranslateX,
                     nPosY - this.getHeight() - ImageManager.getImage(Images.dialog_title).getHeight(),
                     ImageManager.getImage(Images.dialog_title).getWidth(),
                     this.getHeight(),
                     true,
                     false
                  );
               oSB.setColor(
                  new Color(
                     CFG.map.getMapContinents().getColor(Menu_InGame_ContinentPopulation.CONTINENT_ID).r,
                     CFG.map.getMapContinents().getColor(Menu_InGame_ContinentPopulation.CONTINENT_ID).g,
                     CFG.map.getMapContinents().getColor(Menu_InGame_ContinentPopulation.CONTINENT_ID).b,
                     0.165F
                  )
               );
               ImageManager.getImage(Images.line_32_off1)
                  .draw(
                     oSB,
                     nPosX + 2 + iTranslateX,
                     nPosY - this.getHeight() + 2 - ImageManager.getImage(Images.line_32_off1).getHeight(),
                     nWidth - 4,
                     this.getHeight() - 2,
                     false,
                     true
                  );
               oSB.setColor(
                  new Color(
                     CFG.map.getMapContinents().getColor(Menu_InGame_ContinentPopulation.CONTINENT_ID).r,
                     CFG.map.getMapContinents().getColor(Menu_InGame_ContinentPopulation.CONTINENT_ID).g,
                     CFG.map.getMapContinents().getColor(Menu_InGame_ContinentPopulation.CONTINENT_ID).b,
                     0.375F
                  )
               );
               ImageManager.getImage(Images.gradient)
                  .draw(
                     oSB,
                     nPosX + 2 + iTranslateX,
                     nPosY - this.getHeight() * 2 / 3 - ImageManager.getImage(Images.gradient).getHeight(),
                     nWidth - 4,
                     this.getHeight() * 2 / 3,
                     false,
                     true
                  );
               oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.5F));
               ImageManager.getImage(Images.gradient)
                  .draw(
                     oSB,
                     nPosX + 2 + iTranslateX,
                     nPosY - CFG.PADDING - ImageManager.getImage(Images.gradient).getHeight(),
                     nWidth - 4,
                     CFG.PADDING,
                     false,
                     true
                  );
               oSB.setColor(CFG.COLOR_NEW_GAME_EDGE_LINE);
               ImageManager.getImage(Images.pix255_255_255)
                  .draw(oSB, nPosX + 2 + iTranslateX, nPosY - 1 - ImageManager.getImage(Images.pix255_255_255).getHeight(), nWidth - 4, 1);
               oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.45F));
               ImageManager.getImage(Images.slider_gradient)
                  .draw(oSB, nPosX + 2 + iTranslateX, nPosY - 1 - ImageManager.getImage(Images.slider_gradient).getHeight(), (nWidth - 4) / 2, 1);
               ImageManager.getImage(Images.slider_gradient)
                  .draw(
                     oSB,
                     nPosX + 2 + (nWidth - 4) - (nWidth - 4) / 2 + iTranslateX,
                     nPosY - 1 - ImageManager.getImage(Images.slider_gradient).getHeight(),
                     (nWidth - 4) / 2,
                     1,
                     true,
                     false
                  );
               oSB.setColor(Color.WHITE);
               ImageManager.getImage(Images.population)
                  .draw(
                     oSB,
                     nPosX + (int)(nWidth - this.getTextWidth() * 0.8F) / 2 - CFG.PADDING - ImageManager.getImage(Images.population).getWidth() + iTranslateX,
                     2 + nPosY - this.getHeight() + this.getHeight() / 2 - ImageManager.getImage(Images.population).getHeight() / 2
                  );
               CFG.fontMain.getData().setScale(0.8F);
               CFG.drawText(
                  oSB,
                  this.getText(),
                  nPosX + (int)(nWidth - this.getTextWidth() * 0.8F) / 2 + iTranslateX,
                  2 + nPosY - this.getHeight() + (int)(this.getHeight() - this.getTextHeight() * 0.8F) / 2,
                  Color.WHITE
               );
               CFG.fontMain.getData().setScale(1.0F);
            }
         },
         CFG.GAME_WIDTH / 2 - tempWidth / 2,
         tempMenuPosY,
         tempWidth,
         menuElements.get(menuElements.size() - 1).getPosY() + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING + tempMenuPosY
               > CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING * 2
            ? Math.max(CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING * 2 - tempMenuPosY, tElemHeight2 * 6)
            : menuElements.get(menuElements.size() - 1).getPosY() + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING,
         menuElements,
         true,
         true
      );
      this.updateLanguage();

      for (int ixxxx = 0; ixxxx < this.getMenuElementsSize(); ixxxx++) {
         this.getMenuElement(ixxxx).setCurrent(ixxxx / 4 % 2);
      }
   }

   @Override
   public void updateLanguage() {
   }

   @Override
   public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      oSB.setColor(Color.WHITE);
      ImageManager.getImage(Images.new_game_top_edge)
         .draw2(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.new_game_top_edge).getHeight() + iTranslateY,
            this.getWidth() - ImageManager.getImage(Images.new_game_top_edge).getWidth(),
            this.getHeight(),
            false,
            true
         );
      ImageManager.getImage(Images.new_game_top_edge)
         .draw2(
            oSB,
            this.getPosX() + this.getWidth() - ImageManager.getImage(Images.new_game_top_edge).getWidth() + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.new_game_top_edge).getHeight() + iTranslateY,
            ImageManager.getImage(Images.new_game_top_edge).getWidth(),
            this.getHeight(),
            true,
            true
         );
      oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.45F));
      ImageManager.getImage(Images.gradient)
         .draw(
            oSB,
            this.getPosX() + 2 + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY,
            this.getWidth() - 4,
            this.getHeight() / 4
         );
      ImageManager.getImage(Images.pix255_255_255)
         .draw(
            oSB,
            this.getPosX() + 2 + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY,
            this.getWidth() - 4,
            1
         );
      oSB.setColor(Color.WHITE);
      this.beginClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
      this.drawMenu(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
      oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.65F));
      ImageManager.getImage(Images.line_32_off1)
         .draw(
            oSB,
            this.getPosX() + this.getMenuElement(0).getPosX() + iTranslateX,
            this.getMenuPosY()
               - 1
               + this.getMenuElement(0).getPosY()
               - ImageManager.getImage(Images.line_32_off1).getHeight()
               + this.getMenuElement(0).getHeight()
               + iTranslateY,
            this.getWidth() - 4,
            1
         );
      oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.45F));
      ImageManager.getImage(Images.pix255_255_255)
         .draw(
            oSB,
            this.getPosX() + this.getMenuElement(0).getPosX() + iTranslateX,
            this.getMenuPosY()
               + this.getMenuElement(0).getPosY()
               - ImageManager.getImage(Images.pix255_255_255).getHeight()
               + this.getMenuElement(0).getHeight()
               + iTranslateY,
            this.getWidth() - 4,
            1
         );
      oSB.setColor(Color.WHITE);
      this.endClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
   }

   @Override
   public void drawScrollPos(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      if (sliderMenuIsActive) {
         super.drawScrollPos(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
      }
   }

   @Override
   public final void actionElement(int iID) {
      switch (iID) {
         case 0:
            if (iSort != iID) {
               iSort = iID;
               CFG.menuManager.rebuildInGame_ContinentPopulation();
            }

            return;
         case 1:
            if (iSort != iID) {
               iSort = iID;
               CFG.menuManager.rebuildInGame_ContinentPopulation();
            }

            return;
         case 2:
            if (iSort != iID) {
               iSort = iID;
               CFG.menuManager.rebuildInGame_ContinentPopulation();
            }

            return;
         case 3:
            if (iSort != iID) {
               iSort = iID;
               CFG.menuManager.rebuildInGame_ContinentPopulation();
            }

            return;
         default:
            if (iID % 4 == 0) {
               if (iID / 4 == 1) {
                  CFG.menuManager.rebuildInGame_WorldPopulation();
               } else if (this.getMenuElement(iID).getCurrent() > 0) {
                  if (!CFG.menuManager.getVisible_InGame_CivInfo()) {
                     CFG.menuManager.setVisible_InGame_CivInfo(!CFG.menuManager.getVisible_InGame_CivInfo());
                     CFG.setActiveCivInfo(this.getMenuElement(iID).getCurrent());
                     CFG.updateActiveCivInfo_InGame();
                     CFG.toast.setInView(CFG.game.getCiv(this.getMenuElement(iID).getCurrent()).getCivName(), CFG.COLOR_TEXT_NUM_OF_PROVINCES);
                  } else {
                     CFG.setActiveCivInfo(this.getMenuElement(iID).getCurrent());
                     CFG.updateActiveCivInfo_InGame();
                     CFG.toast.setInView(CFG.game.getCiv(this.getMenuElement(iID).getCurrent()).getCivName(), CFG.COLOR_TEXT_NUM_OF_PROVINCES);
                  }
               }
            } else if (iID % 4 == 3) {
               try {
                  if (this.getMenuElement(iID).getCurrent() >= 0
                     && (CFG.FOG_OF_WAR != 2 || CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetProvince(this.getMenuElement(iID).getCurrent()))) {
                     CFG.game.setActiveProvinceID(this.getMenuElement(iID).getCurrent());
                     CFG.map.getMapCoordinates().centerToProvinceID(CFG.game.getActiveProvinceID());
                  }
               } catch (IndexOutOfBoundsException var3) {
               }
            }
      }
   }

   public final int getW() {
      return this.getWidth() - CFG.PADDING * 4;
   }

   public final int getElementW() {
      return this.getW() / 5;
   }
}
