package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

public class Menu_InGame_FlagAction_Bot_Right_Right extends SliderMenu {
   public static int iViewMode = 1;
   public static final int iViewsSize = 3;
   public static int iSort = 1;
   public static long lTime = 0L;

   public static final String getViewName() {
      switch (iViewMode) {
         case 0:
            return CFG.langManager.get("Provinces");
         case 1:
            return CFG.langManager.get("Population");
         case 2:
            return CFG.langManager.get("TechnologyLevel");
         case 3:
            return CFG.langManager.get("RankScore");
         default:
            return CFG.langManager.get("Provinces");
      }
   }

   public Menu_InGame_FlagAction_Bot_Right_Right() {
      int tempHeight = 0;
      int tempWidth = 0;
      if (CFG.isAndroid() && CFG.LANDSCAPE) {
         tempHeight = CFG.GAME_HEIGHT
            - (
               ImageManager.getImage(Images.top_left).getHeight()
                  + CFG.PADDING * 2
                  + ImageManager.getImage(Images.top_flag_frame).getHeight()
                  + CFG.PADDING * 4
                  + CFG.TEXT_HEIGHT
                  + CFG.PADDING * 4
            )
            - CFG.PADDING * 2
            - CFG.BUTTON_HEIGHT / 2;
         tempWidth = CFG.GAME_WIDTH - CFG.GAME_WIDTH * 2 / 5 - CFG.PADDING * 2;
      } else {
         tempHeight = CFG.GAME_HEIGHT
            - (
               ImageManager.getImage(Images.top_left).getHeight()
                  + CFG.PADDING * 2
                  + ImageManager.getImage(Images.top_flag_frame).getHeight()
                  + CFG.PADDING * 4
                  + CFG.TEXT_HEIGHT
                  + CFG.PADDING * 4
            )
            - CFG.map.getMapBG().getMinimapHeight()
            - CFG.PADDING * 2
            - CFG.BUTTON_HEIGHT / 2;
         tempWidth = CFG.GAME_WIDTH - CFG.GAME_WIDTH * 2 / 5 - CFG.PADDING * 2;
      }

      int tY = 0;
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      menuElements.add(
         new Button_Statistics_Title("<", -1, 1, tY, (int)Math.ceil((tempWidth - tempWidth * 7 / 10 - 3) * 0.2F), CFG.TEXT_HEIGHT + CFG.PADDING * 6) {
            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Previous"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }

            @Override
            public void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
               super.drawButtonBG(oSB, iTranslateX, iTranslateY, isActive);
               oSB.setColor(CFG.COLOR_NEW_GAME_EDGE_LINE);
               ImageManager.getImage(Images.line_32_vertical)
                  .draw(
                     oSB,
                     this.getPosX() + this.getWidth() - 1 + iTranslateX,
                     this.getPosY() - ImageManager.getImage(Images.line_32_vertical).getHeight() + iTranslateY,
                     1,
                     this.getHeight()
                  );
               oSB.setColor(Color.WHITE);
            }

            @Override
            public void actionElement(int iID) {
               if (--Menu_InGame_FlagAction_Bot_Right_Right.iViewMode < 0) {
                  Menu_InGame_FlagAction_Bot_Right_Right.iViewMode = 3;
               }

               CFG.menuManager.rebuildInGame_FlagActionRightBoth();
            }
         }
      );
      menuElements.add(
         new Button_Statistics_Title(
            getViewName(),
            -1,
            1 + (int)Math.ceil((tempWidth - tempWidth * 7 / 10 - 3) * 0.2F),
            tY,
            (int)(tempWidth - tempWidth * 7 / 10 - 3 - Math.ceil((tempWidth - tempWidth * 7 / 10 - 3) * 0.2F) * 2.0),
            CFG.TEXT_HEIGHT + CFG.PADDING * 6
         ) {
            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()));
               nData.add(
                  new MenuElement_Hover_v2_Element_Type_Text(
                     CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCivName(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                  )
               );
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }

            @Override
            public void actionElement(int iID) {
               CFG.menuManager.rebuildInGame_FlagActionRightLeft();
            }
         }
      );
      menuElements.add(
         new Button_Statistics_Title(
            ">",
            -1,
            1 + (int)(tempWidth - tempWidth * 7 / 10 - 3 - Math.ceil((tempWidth - tempWidth * 7 / 10 - 3) * 0.2F)),
            tY,
            (int)Math.ceil((tempWidth - tempWidth * 7 / 10 - 3) * 0.2F),
            CFG.TEXT_HEIGHT + CFG.PADDING * 6
         ) {
            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Next"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }

            @Override
            public void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
               super.drawButtonBG(oSB, iTranslateX, iTranslateY, isActive);
               oSB.setColor(CFG.COLOR_NEW_GAME_EDGE_LINE);
               ImageManager.getImage(Images.line_32_vertical)
                  .draw(
                     oSB,
                     this.getPosX() + iTranslateX,
                     this.getPosY() - ImageManager.getImage(Images.line_32_vertical).getHeight() + iTranslateY,
                     1,
                     this.getHeight()
                  );
               oSB.setColor(Color.WHITE);
            }

            @Override
            public void actionElement(int iID) {
               if (++Menu_InGame_FlagAction_Bot_Right_Right.iViewMode > 3) {
                  Menu_InGame_FlagAction_Bot_Right_Right.iViewMode = 0;
               }

               CFG.menuManager.rebuildInGame_FlagActionRightBoth();
            }
         }
      );
      int var23;
      menuElements.add(
         new Button_Statistics_Title(
            CFG.langManager.get("Name"),
            CFG.PADDING * 2,
            1,
            var23 = tY + menuElements.get(menuElements.size() - 1).getHeight(),
            (tempWidth - tempWidth * 7 / 10) * 3 / 5,
            CFG.TEXT_HEIGHT + CFG.PADDING * 4
         ) {
            @Override
            public Color getColor(boolean isActive) {
               return Menu_InGame_FlagAction_Bot_Right_Right.iSort == 0 ? CFG.COLOR_TEXT_NUM_OF_PROVINCES : super.getColor(isActive);
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

            @Override
            public void actionElement(int iID) {
               if (Menu_InGame_FlagAction_Bot_Right_Right.iSort != 0) {
                  Menu_InGame_FlagAction_Bot_Right_Right.iSort = 0;
                  CFG.menuManager.rebuildInGame_FlagActionRightRight();
               }
            }
         }
      );
      menuElements.add(
         new Button_Statistics_Title(
            getViewName(),
            CFG.PADDING * 2,
            (tempWidth - tempWidth * 7 / 10) * 3 / 5 + 1,
            var23,
            tempWidth - tempWidth * 7 / 10 - (tempWidth - tempWidth * 7 / 10) * 3 / 5 - 3,
            CFG.TEXT_HEIGHT + CFG.PADDING * 4
         ) {
            @Override
            public Color getColor(boolean isActive) {
               return Menu_InGame_FlagAction_Bot_Right_Right.iSort == 1 ? CFG.COLOR_TEXT_NUM_OF_PROVINCES : super.getColor(isActive);
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

            @Override
            public void actionElement(int iID) {
               if (Menu_InGame_FlagAction_Bot_Right_Right.iSort != 1) {
                  Menu_InGame_FlagAction_Bot_Right_Right.iSort = 1;
                  CFG.menuManager.rebuildInGame_FlagActionRightRight();
               }
            }
         }
      );
      tY = var23 + menuElements.get(menuElements.size() - 1).getHeight();
      if (iViewMode == 0) {
         ArrayList<Integer> tSorted = new ArrayList<>();
         if (iSort == 0) {
            for (int i = 0; i < CFG.game.getSortedCivsSize(); i++) {
               tSorted.add(CFG.game.getSortedCivsAZ(i));
            }
         } else {
            ArrayList<Integer> tempCivs = new ArrayList<>();

            for (int i2 = 1; i2 < CFG.game.getCivsSize(); i2++) {
               if (CFG.game.getCiv(i2).getNumOfProvinces() > 0) {
                  tempCivs.add(i2);
               }
            }

            while (tempCivs.size() > 0) {
               int tBest = 0;

               for (int j = 1; j < tempCivs.size(); j++) {
                  if (CFG.game.getCiv(tempCivs.get(j)).getNumOfProvinces() > CFG.game.getCiv(tempCivs.get(tBest)).getNumOfProvinces()) {
                     tBest = j;
                  }
               }

               tSorted.add(tempCivs.get(tBest));
               tempCivs.remove(tBest);
            }
         }

         for (int i = 0; i < tSorted.size(); i++) {
            menuElements.add(
               new Button_Statistics_Flag_Clip(
                  CFG.FOG_OF_WAR >= 2 && !CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetCivilization(tSorted.get(i)) ? -1 : tSorted.get(i),
                  CFG.FOG_OF_WAR >= 2 && !CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetCivilization(tSorted.get(i))
                     ? "" + (i + 1) + ". " + CFG.langManager.get("Undiscovered")
                     : "" + (i + 1) + ". " + CFG.game.getCiv(tSorted.get(i)).getCivName(),
                  CFG.PADDING,
                  1,
                  tY,
                  (tempWidth - tempWidth * 7 / 10) * 3 / 5,
                  CFG.TEXT_HEIGHT + CFG.PADDING * 2
               ) {
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
                     if (this.getCurrent() >= 0) {
                        CFG.menuManager.menuInGame_FlagActionBotRightLeft_LoadData(this.getCurrent());
                     }
                  }

                  @Override
                  public void buildElementHover() {
                     if (this.getCurrent() >= 0) {
                        ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
                        ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
                        nData.add(new MenuElement_Hover_v2_Element_Type_Flag(this.getCurrent()));
                        nData.add(
                           new MenuElement_Hover_v2_Element_Type_Text(CFG.game.getCiv(this.getCurrent()).getCivName(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE)
                        );
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                        this.menuElementHover = new MenuElement_Hover_v2(nElements);
                     } else {
                        MenuElement_Hover_v2.resetAnimation_2();
                        this.menuElementHover = null;
                     }
                  }
               }
            );
            menuElements.add(
               new Button_Statistics(
                  "" + CFG.game.getCiv(tSorted.get(i)).getNumOfProvinces(),
                  CFG.PADDING,
                  (tempWidth - tempWidth * 7 / 10) * 3 / 5 + 1,
                  tY,
                  tempWidth - tempWidth * 7 / 10 - (tempWidth - tempWidth * 7 / 10) * 3 / 5 - 3,
                  CFG.TEXT_HEIGHT + CFG.PADDING * 2
               ) {}
            );
            tY += menuElements.get(menuElements.size() - 1).getHeight();
         }
      } else if (iViewMode == 1) {
         ArrayList<Integer> tSorted = new ArrayList<>();
         if (iSort == 0) {
            for (int i3 = 0; i3 < CFG.game.getSortedCivsSize(); i3++) {
               tSorted.add(CFG.game.getSortedCivsAZ(i3));
            }
         } else {
            ArrayList<Integer> tempCivs = new ArrayList<>();
            ArrayList<Integer> tempCivsPop = new ArrayList<>();

            for (int i4 = 1; i4 < CFG.game.getCivsSize(); i4++) {
               if (CFG.game.getCiv(i4).getNumOfProvinces() > 0) {
                  tempCivs.add(i4);
                  tempCivsPop.add((int)CFG.game.getCiv(i4).countPopulation());
               }
            }

            while (tempCivs.size() > 0) {
               int tBest = 0;

               for (int j2 = 1; j2 < tempCivs.size(); j2++) {
                  if (tempCivsPop.get(j2) > tempCivsPop.get(tBest)) {
                     tBest = j2;
                  }
               }

               tSorted.add(tempCivs.get(tBest));
               tempCivs.remove(tBest);
               tempCivsPop.remove(tBest);
            }
         }

         for (int i = 0; i < tSorted.size(); i++) {
            menuElements.add(
               new Button_Statistics_Flag_Clip(
                  CFG.FOG_OF_WAR >= 2 && !CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetCivilization(tSorted.get(i)) ? -1 : tSorted.get(i),
                  CFG.FOG_OF_WAR >= 2 && !CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetCivilization(tSorted.get(i))
                     ? "" + (i + 1) + ". " + CFG.langManager.get("Undiscovered")
                     : "" + (i + 1) + ". " + CFG.game.getCiv(tSorted.get(i)).getCivName(),
                  CFG.PADDING,
                  1,
                  tY,
                  (tempWidth - tempWidth * 7 / 10) * 3 / 5,
                  CFG.TEXT_HEIGHT + CFG.PADDING * 2
               ) {
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
                     if (this.getCurrent() >= 0) {
                        CFG.menuManager.menuInGame_FlagActionBotRightLeft_LoadData(this.getCurrent());
                     }
                  }

                  @Override
                  public void buildElementHover() {
                     if (this.getCurrent() >= 0) {
                        ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
                        ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
                        nData.add(new MenuElement_Hover_v2_Element_Type_Flag(this.getCurrent()));
                        nData.add(
                           new MenuElement_Hover_v2_Element_Type_Text(CFG.game.getCiv(this.getCurrent()).getCivName(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE)
                        );
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                        this.menuElementHover = new MenuElement_Hover_v2(nElements);
                     } else {
                        MenuElement_Hover_v2.resetAnimation_2();
                        this.menuElementHover = null;
                     }
                  }
               }
            );
            menuElements.add(
               new Button_Statistics(
                  "" + CFG.getNumberWithSpaces("" + CFG.game.getCiv(tSorted.get(i)).countPopulation()),
                  CFG.PADDING,
                  (tempWidth - tempWidth * 7 / 10) * 3 / 5 + 1,
                  tY,
                  tempWidth - tempWidth * 7 / 10 - (tempWidth - tempWidth * 7 / 10) * 3 / 5 - 3,
                  CFG.TEXT_HEIGHT + CFG.PADDING * 2
               ) {}
            );
            tY += menuElements.get(menuElements.size() - 1).getHeight();
         }
      } else if (iViewMode == 2) {
         ArrayList<Integer> tSorted = new ArrayList<>();
         if (iSort == 0) {
            for (int i5 = 0; i5 < CFG.game.getSortedCivsSize(); i5++) {
               tSorted.add(CFG.game.getSortedCivsAZ(i5));
            }
         } else {
            ArrayList<Integer> tempCivs = new ArrayList<>();

            for (int i6 = 1; i6 < CFG.game.getCivsSize(); i6++) {
               if (CFG.game.getCiv(i6).getTechnologyLevel() > 0.0F) {
                  tempCivs.add(i6);
               }
            }

            while (tempCivs.size() > 0) {
               int tBest = 0;

               for (int jx = 1; jx < tempCivs.size(); jx++) {
                  if (CFG.game.getCiv(tempCivs.get(jx)).getTechnologyLevel() > CFG.game.getCiv(tempCivs.get(tBest)).getTechnologyLevel()) {
                     tBest = jx;
                  }
               }

               tSorted.add(tempCivs.get(tBest));
               tempCivs.remove(tBest);
            }
         }

         for (int i = 0; i < tSorted.size(); i++) {
            menuElements.add(
               new Button_Statistics_Flag_Clip(
                  CFG.FOG_OF_WAR >= 2 && !CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetCivilization(tSorted.get(i)) ? -1 : tSorted.get(i),
                  CFG.FOG_OF_WAR >= 2 && !CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetCivilization(tSorted.get(i))
                     ? "" + (i + 1) + ". " + CFG.langManager.get("Undiscovered")
                     : "" + (i + 1) + ". " + CFG.game.getCiv(tSorted.get(i)).getCivName(),
                  CFG.PADDING,
                  1,
                  tY,
                  (tempWidth - tempWidth * 7 / 10) * 3 / 5,
                  CFG.TEXT_HEIGHT + CFG.PADDING * 2
               ) {
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
                     if (this.getCurrent() >= 0) {
                        CFG.menuManager.menuInGame_FlagActionBotRightLeft_LoadData(this.getCurrent());
                     }
                  }

                  @Override
                  public void buildElementHover() {
                     if (this.getCurrent() >= 0) {
                        ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
                        ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
                        nData.add(new MenuElement_Hover_v2_Element_Type_Flag(this.getCurrent()));
                        nData.add(
                           new MenuElement_Hover_v2_Element_Type_Text(CFG.game.getCiv(this.getCurrent()).getCivName(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE)
                        );
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                        this.menuElementHover = new MenuElement_Hover_v2(nElements);
                     } else {
                        MenuElement_Hover_v2.resetAnimation_2();
                        this.menuElementHover = null;
                     }
                  }
               }
            );
            menuElements.add(
               new Button_Statistics(
                  "" + (int)(CFG.game.getCiv(tSorted.get(i)).getTechnologyLevel() * 100.0F) / 100.0F,
                  CFG.PADDING,
                  (tempWidth - tempWidth * 7 / 10) * 3 / 5 + 1,
                  tY,
                  tempWidth - tempWidth * 7 / 10 - (tempWidth - tempWidth * 7 / 10) * 3 / 5 - 3,
                  CFG.TEXT_HEIGHT + CFG.PADDING * 2
               ) {}
            );
            tY += menuElements.get(menuElements.size() - 1).getHeight();
         }
      } else if (iViewMode == 3) {
         ArrayList<Integer> tSorted = new ArrayList<>();
         if (iSort == 0) {
            for (int i7 = 0; i7 < CFG.game.getSortedCivsSize(); i7++) {
               tSorted.add(CFG.game.getSortedCivsAZ(i7));
            }
         } else {
            ArrayList<Integer> tempCivs = new ArrayList<>();

            for (int i8 = 1; i8 < CFG.game.getCivsSize(); i8++) {
               if (CFG.game.getCiv(i8).getRankScore() > 0) {
                  tempCivs.add(i8);
               }
            }

            while (tempCivs.size() > 0) {
               int tBest = 0;

               for (int jxx = 1; jxx < tempCivs.size(); jxx++) {
                  if (CFG.game.getCiv(tempCivs.get(jxx)).getRankScore() > CFG.game.getCiv(tempCivs.get(tBest)).getRankScore()) {
                     tBest = jxx;
                  }
               }

               tSorted.add(tempCivs.get(tBest));
               tempCivs.remove(tBest);
            }
         }

         for (int i = 0; i < tSorted.size(); i++) {
            menuElements.add(
               new Button_Statistics_Flag_Clip(
                  CFG.FOG_OF_WAR >= 2 && !CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetCivilization(tSorted.get(i)) ? -1 : tSorted.get(i),
                  CFG.FOG_OF_WAR >= 2 && !CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetCivilization(tSorted.get(i))
                     ? "" + (i + 1) + ". " + CFG.langManager.get("Undiscovered")
                     : "" + (i + 1) + ". " + CFG.game.getCiv(tSorted.get(i)).getCivName(),
                  CFG.PADDING,
                  1,
                  tY,
                  (tempWidth - tempWidth * 7 / 10) * 3 / 5,
                  CFG.TEXT_HEIGHT + CFG.PADDING * 2
               ) {
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
                     if (this.getCurrent() >= 0) {
                        CFG.menuManager.menuInGame_FlagActionBotRightLeft_LoadData(this.getCurrent());
                     }
                  }

                  @Override
                  public void buildElementHover() {
                     if (this.getCurrent() >= 0) {
                        ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
                        ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
                        nData.add(new MenuElement_Hover_v2_Element_Type_Flag(this.getCurrent()));
                        nData.add(
                           new MenuElement_Hover_v2_Element_Type_Text(CFG.game.getCiv(this.getCurrent()).getCivName(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE)
                        );
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                        this.menuElementHover = new MenuElement_Hover_v2(nElements);
                     } else {
                        MenuElement_Hover_v2.resetAnimation_2();
                        this.menuElementHover = null;
                     }
                  }
               }
            );
            menuElements.add(
               new Button_Statistics(
                  "" + CFG.game.getCiv(tSorted.get(i)).getRankScore(),
                  CFG.PADDING,
                  (tempWidth - tempWidth * 7 / 10) * 3 / 5 + 1,
                  tY,
                  tempWidth - tempWidth * 7 / 10 - (tempWidth - tempWidth * 7 / 10) * 3 / 5 - 3,
                  CFG.TEXT_HEIGHT + CFG.PADDING * 2
               ) {}
            );
            tY += menuElements.get(menuElements.size() - 1).getHeight();
         }
      }

      menuElements.add(
         new Button_Transparent(
            0,
            0,
            tempWidth - tempWidth * 7 / 10,
            tempHeight - tempHeight / 2 - 2 < menuElements.get(menuElements.size() - 1).getPosY() + menuElements.get(menuElements.size() - 1).getHeight()
               ? menuElements.get(menuElements.size() - 1).getPosY() + menuElements.get(menuElements.size() - 1).getHeight()
               : tempHeight - tempHeight / 2 - 2,
            true
         )
      );
      this.initMenu(
         null,
         CFG.GAME_WIDTH - CFG.GAME_WIDTH * 3 / 5 + tempWidth * 7 / 10,
         tempHeight / 2
            + ImageManager.getImage(Images.top_left).getHeight()
            + CFG.PADDING * 2
            + ImageManager.getImage(Images.top_flag_frame).getHeight()
            + CFG.PADDING * 4
            + CFG.TEXT_HEIGHT
            + CFG.PADDING * 4
            + CFG.BUTTON_HEIGHT / 2,
         tempWidth - tempWidth * 7 / 10,
         tempHeight - tempHeight / 2 - 2,
         menuElements,
         false,
         false
      );

      for (int i9 = 3; i9 < menuElements.size(); i9++) {
         this.getMenuElement(i9).setCurrent(((i9 - 3) / 2 + 1) % 2);
      }
   }

   @Override
   public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      oSB.setColor(Color.WHITE);
      ImageManager.getImage(Images.new_game_top_edge)
         .draw2(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.new_game_top_edge).getHeight() + iTranslateY,
            this.getWidth(),
            this.getHeight() + 2,
            true,
            true
         );
      oSB.setColor(new Color(0.025F, 0.025F, 0.025F, 0.25F));
      ImageManager.getImage(Images.line_32_off1)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
            this.getWidth(),
            this.getHeight() + 2
         );
      oSB.setColor(new Color(0.025F, 0.025F, 0.025F, 0.75F));
      ImageManager.getImage(Images.gradient)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY,
            this.getWidth() - 2,
            CFG.BUTTON_HEIGHT / 4
         );
      ImageManager.getImage(Images.slider_gradient)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY,
            CFG.BUTTON_HEIGHT / 4,
            this.getHeight()
         );
      oSB.setColor(new Color(CFG.COLOR_NEW_GAME_EDGE_LINE));
      ImageManager.getImage(Images.pix255_255_255)
         .draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY, 1, this.getHeight());
      oSB.setColor(Color.WHITE);
      super.draw(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
   }

   @Override
   public void drawScrollPos(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      if (sliderMenuIsActive) {
         super.drawScrollPos(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
      }
   }

   @Override
   public void onHovered() {
      CFG.menuManager.setOrderOfMenu_InGame_FlagAction();
   }

   @Override
   public void actionElement(int iID) {
      this.getMenuElement(iID).actionElement(iID);
   }

   @Override
   public void setVisible(boolean visible) {
      super.setVisible(visible);
      lTime = System.currentTimeMillis();
   }

   @Override
   public boolean getVisible() {
      return CFG.isAndroid() && !CFG.LANDSCAPE ? false : super.getVisible();
   }
}
