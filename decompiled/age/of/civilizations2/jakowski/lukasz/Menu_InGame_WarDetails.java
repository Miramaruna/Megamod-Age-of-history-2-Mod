package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

public class Menu_InGame_WarDetails extends SliderMenu {
   public static int WAR_ID = 0;
   public static int iSort = 0;
   public String sDefender;
   public String sWarDate;
   public int iWarDateWidth;
   public static final float FONT_SCALE = 0.55F;

   public Menu_InGame_WarDetails(int tInit) {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      int tempWidth = CFG.CIV_INFO_MENU_WIDTH * 3;
      int tempMenuPosY = ImageManager.getImage(Images.top_flag_frame).getHeight()
         + CFG.PADDING * 4
         + CFG.BUTTON_HEIGHT * 3 / 5
         + CFG.PADDING * 2
         + CFG.BUTTON_HEIGHT / 2;
      this.initMenu(null, CFG.GAME_WIDTH / 2 - tempWidth / 2, tempMenuPosY, tempWidth, 5, menuElements, false, false);
   }

   public Menu_InGame_WarDetails() {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      int tempWidth = CFG.CIV_INFO_MENU_WIDTH * 3;
      if (WAR_ID >= CFG.game.getWarsSize()) {
         WAR_ID = 0;
      }

      this.sWarDate = Game_Calendar.getNumOfDates_ByTurnID(CFG.game.getWar(WAR_ID).getWarTurnID());
      CFG.glyphLayout.setText(CFG.fontMain, this.sWarDate);
      this.iWarDateWidth = (int)(CFG.glyphLayout.width * 0.55F);
      menuElements.add(
         new Button_Statistics_WarDetails_WarResult(
            CFG.game.getWar(WAR_ID).getAggressorID(0).getCivID(), CFG.game.getWar(WAR_ID).getDefenderID(0).getCivID(), WAR_ID, 2, 0, tempWidth - 4
         ) {
            @Override
            public int getWidth() {
               return Menu_InGame_WarDetails.this.getW();
            }
         }
      );
      int tY = menuElements.get(menuElements.size() - 1).getHeight();
      menuElements.add(
         new Button_Statistics_Title(CFG.langManager.get("Aggressors"), CFG.PADDING * 2, 2, tY, CFG.BUTTON_WIDTH * 2, CFG.TEXT_HEIGHT + CFG.PADDING * 2) {
            @Override
            public int getWidth() {
               return Menu_InGame_WarDetails.this.getElementW() * 4;
            }

            @Override
            public Color getColor(boolean isActive) {
               return Menu_InGame_WarDetails.iSort == 0 ? CFG.COLOR_TEXT_NUM_OF_PROVINCES : super.getColor(isActive);
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
               if (Menu_InGame_WarDetails.iSort != 0) {
                  Menu_InGame_WarDetails.iSort = 0;
                  CFG.menuManager.rebuildInGame_WarDetails();
               }
            }
         }
      );
      menuElements.add(
         new Button_Statistics_Title(
            CFG.langManager.get("Casualties"), -1, CFG.PADDING * 2 + CFG.BUTTON_WIDTH * 2, tY, CFG.BUTTON_WIDTH, CFG.TEXT_HEIGHT + CFG.PADDING * 2
         ) {
            @Override
            public int getPosX() {
               return Menu_InGame_WarDetails.this.getElementW() * 4 + 2;
            }

            @Override
            public int getWidth() {
               return Menu_InGame_WarDetails.this.getElementW() + 2;
            }

            @Override
            public Color getColor(boolean isActive) {
               return Menu_InGame_WarDetails.iSort == 1 ? CFG.COLOR_TEXT_NUM_OF_PROVINCES : super.getColor(isActive);
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
               if (Menu_InGame_WarDetails.iSort != 1) {
                  Menu_InGame_WarDetails.iSort = 1;
                  CFG.menuManager.rebuildInGame_WarDetails();
               }
            }
         }
      );
      menuElements.add(
         new Button_Statistics_Title(
            CFG.langManager.get("Casualties"), -1, CFG.PADDING * 2 + CFG.BUTTON_WIDTH * 2, tY, CFG.BUTTON_WIDTH, CFG.TEXT_HEIGHT + CFG.PADDING * 2
         ) {
            @Override
            public int getPosX() {
               return Menu_InGame_WarDetails.this.getElementW() * 5 + 4;
            }

            @Override
            public int getWidth() {
               return Menu_InGame_WarDetails.this.getElementW() + 2;
            }

            @Override
            public Color getColor(boolean isActive) {
               return Menu_InGame_WarDetails.iSort == 1 ? CFG.COLOR_TEXT_NUM_OF_PROVINCES : super.getColor(isActive);
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
               if (Menu_InGame_WarDetails.iSort != 1) {
                  Menu_InGame_WarDetails.iSort = 1;
                  CFG.menuManager.rebuildInGame_WarDetails();
               }
            }
         }
      );
      menuElements.add(
         new Button_Statistics_Title_Right(
            CFG.langManager.get("Defenders"), CFG.PADDING, CFG.PADDING * 2 + CFG.BUTTON_WIDTH * 5, tY, CFG.BUTTON_WIDTH, CFG.TEXT_HEIGHT + CFG.PADDING * 2
         ) {
            @Override
            public int getPosX() {
               return Menu_InGame_WarDetails.this.getElementW() * 6 + 6;
            }

            @Override
            public int getWidth() {
               return Menu_InGame_WarDetails.this.getW() - Menu_InGame_WarDetails.this.getElementW() * 6 - 4;
            }

            @Override
            public Color getColor(boolean isActive) {
               return Menu_InGame_WarDetails.iSort == 0 ? CFG.COLOR_TEXT_NUM_OF_PROVINCES : super.getColor(isActive);
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
               if (Menu_InGame_WarDetails.iSort != 0) {
                  Menu_InGame_WarDetails.iSort = 0;
                  CFG.menuManager.rebuildInGame_WarDetails();
               }
            }
         }
      );
      tY += menuElements.get(menuElements.size() - 1).getHeight();

      for (int i3 = 0; i3 < CFG.game.getWar(WAR_ID).getAggressorsSize(); i3++) {
         menuElements.add(
            new Button_Statistics_War_Casualties(
               CFG.game.getWar(WAR_ID).getAggressorID(i3).getCasualties() + CFG.game.getWar(WAR_ID).getAggressorID(i3).getCivilianDeaths(),
               -1,
               tY,
               CFG.BUTTON_WIDTH * 2
            ) {
               @Override
               public int getPosX() {
                  return Menu_InGame_WarDetails.this.getElementW() * 4 + 2;
               }

               @Override
               public int getWidth() {
                  return Menu_InGame_WarDetails.this.getElementW() + 2;
               }
            }
         );
         menuElements.add(
            new Button_Statistics_WarDetails(
               CFG.game.getWar(WAR_ID).getAggressorID(i3).getCivID(),
               CFG.game.getWar(WAR_ID).getAggressorID(i3).getCivilianDeaths(),
               CFG.game.getWar(WAR_ID).getAggressorID(i3).getEconomicLosses(),
               CFG.game.getCiv(CFG.game.getWar(WAR_ID).getAggressorID(i3).getCivID()).getCapitulationPoints(),
               CFG.game.getWar(WAR_ID).getProvinces_Aggressor_OwnTotal(i3),
               CFG.game.getWar(WAR_ID).getProvinces_Aggressor_Own(i3),
               2,
               tY,
               CFG.BUTTON_WIDTH * 2,
               !CFG.SPECTATOR_MODE && CFG.game.getWar(WAR_ID).getIsDefender(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
            ) {
               @Override
               public int getWidth() {
                  return Menu_InGame_WarDetails.this.getElementW() * 4;
               }
            }
         );
         tY += menuElements.get(menuElements.size() - 1).getHeight();
      }

      tY = menuElements.get(1).getPosY() + menuElements.get(1).getHeight();

      for (int var23 = 0; var23 < CFG.game.getWar(WAR_ID).getDefendersSize(); var23++) {
         menuElements.add(
            new Button_Statistics_War_Casualties_Right(
               CFG.game.getWar(WAR_ID).getDefenderID(var23).getCasualties() + CFG.game.getWar(WAR_ID).getDefenderID(var23).getCivilianDeaths(),
               -1,
               tY,
               CFG.BUTTON_WIDTH * 2
            ) {
               @Override
               public int getPosX() {
                  return Menu_InGame_WarDetails.this.getElementW() * 5 + 4;
               }

               @Override
               public int getWidth() {
                  return Menu_InGame_WarDetails.this.getElementW() + 2;
               }
            }
         );
         menuElements.add(
            new Button_Statistics_WarDetails_Right(
               CFG.game.getWar(WAR_ID).getDefenderID(var23).getCivID(),
               CFG.game.getWar(WAR_ID).getDefenderID(var23).getCivilianDeaths(),
               CFG.game.getWar(WAR_ID).getDefenderID(var23).getEconomicLosses(),
               CFG.game.getCiv(CFG.game.getWar(WAR_ID).getDefenderID(var23).getCivID()).getCapitulationPoints(),
               CFG.game.getWar(WAR_ID).getProvinces_Defender_OwnTotal(var23),
               CFG.game.getWar(WAR_ID).getProvinces_Defender_Own(var23),
               CFG.PADDING * 2,
               tY,
               CFG.BUTTON_WIDTH * 2,
               !CFG.SPECTATOR_MODE && CFG.game.getWar(WAR_ID).getIsAggressor(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
            ) {
               @Override
               public int getPosX() {
                  return Menu_InGame_WarDetails.this.getElementW() * 6 + 6;
               }

               @Override
               public int getWidth() {
                  return Menu_InGame_WarDetails.this.getW() - Menu_InGame_WarDetails.this.getElementW() * 6 - 4;
               }
            }
         );
         tY += menuElements.get(menuElements.size() - 1).getHeight();
      }

      this.sDefender = CFG.FOG_OF_WAR == 2
         ? (
            CFG.game.getCiv(CFG.game.getWar(WAR_ID).getDefenderID(0).getCivID()).getAllianceID() > 0
               ? (
                  CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetAlliance(CFG.game.getCiv(CFG.game.getWar(WAR_ID).getDefenderID(0).getCivID()).getAllianceID())
                     ? CFG.game.getAlliance(CFG.game.getCiv(CFG.game.getWar(WAR_ID).getDefenderID(0).getCivID()).getAllianceID()).getAllianceName()
                     : CFG.langManager.get("Undiscovered")
               )
               : (
                  CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetCivilization(CFG.game.getWar(WAR_ID).getDefenderID(0).getCivID())
                     ? CFG.game.getCiv(CFG.game.getWar(WAR_ID).getDefenderID(0).getCivID()).getCivName()
                     : CFG.langManager.get("Undiscovered")
               )
         )
         : (
            CFG.game.getCiv(CFG.game.getWar(WAR_ID).getDefenderID(0).getCivID()).getAllianceID() > 0
               ? CFG.game.getAlliance(CFG.game.getCiv(CFG.game.getWar(WAR_ID).getDefenderID(0).getCivID()).getAllianceID()).getAllianceName()
               : CFG.game.getCiv(CFG.game.getWar(WAR_ID).getDefenderID(0).getCivID()).getCivName()
         );
      int tempMaxY = 0;
      int iSize = menuElements.size();

      for (int i4 = 0; i4 < iSize; i4++) {
         if (menuElements.get(i4).getPosY() + menuElements.get(i4).getHeight() > tempMaxY) {
            tempMaxY = menuElements.get(i4).getPosY() + menuElements.get(i4).getHeight();
         }
      }

      boolean addAlliesNotInWar = false;
      if (CFG.game.getCiv(CFG.game.getWar(WAR_ID).getAggressorID(0).getCivID()).getAllianceID() > 0) {
         for (int i2 = 0;
            i2 < CFG.game.getAlliance(CFG.game.getCiv(CFG.game.getWar(WAR_ID).getAggressorID(0).getCivID()).getAllianceID()).getCivilizationsSize();
            i2++
         ) {
            if (!CFG.game
               .getWar(WAR_ID)
               .getIsInAggressors(
                  CFG.game.getAlliance(CFG.game.getCiv(CFG.game.getWar(WAR_ID).getAggressorID(0).getCivID()).getAllianceID()).getCivilization(i2)
               )) {
               addAlliesNotInWar = true;
               break;
            }
         }
      }

      if (!addAlliesNotInWar && CFG.game.getCiv(CFG.game.getWar(WAR_ID).getDefenderID(0).getCivID()).getAllianceID() > 0) {
         for (int i2x = 0;
            i2x < CFG.game.getAlliance(CFG.game.getCiv(CFG.game.getWar(WAR_ID).getDefenderID(0).getCivID()).getAllianceID()).getCivilizationsSize();
            i2x++
         ) {
            if (!CFG.game
               .getWar(WAR_ID)
               .getIsInDefenders(
                  CFG.game.getAlliance(CFG.game.getCiv(CFG.game.getWar(WAR_ID).getDefenderID(0).getCivID()).getAllianceID()).getCivilization(i2x)
               )) {
               addAlliesNotInWar = true;
               break;
            }
         }
      }

      if (!addAlliesNotInWar) {
         for (int i2xx = 0; i2xx < CFG.game.getWar(WAR_ID).getAggressorsSize(); i2xx++) {
            if (CFG.game.getCiv(CFG.game.getWar(WAR_ID).getAggressorID(i2xx).getCivID()).getNumOfProvinces() > 0) {
               for (int j = 1; j < CFG.game.getCivsSize(); j++) {
                  if (j != CFG.game.getWar(WAR_ID).getAggressorID(i2xx).getCivID()
                     && (
                        CFG.game.getCiv(j).getPuppetOfCivID() == CFG.game.getWar(WAR_ID).getAggressorID(i2xx).getCivID()
                           || CFG.game.getCiv(CFG.game.getWar(WAR_ID).getAggressorID(i2xx).getCivID()).getPuppetOfCivID() == j
                     )
                     && !CFG.game.getWar(WAR_ID).getIsAggressor(j)
                     && !CFG.game.getCivsAreAllied(j, CFG.game.getWar(WAR_ID).getAggressorID(i2xx).getCivID())) {
                     addAlliesNotInWar = true;
                     break;
                  }
               }
            }
         }
      }

      if (!addAlliesNotInWar) {
         for (int i2xxx = 0; i2xxx < CFG.game.getWar(WAR_ID).getDefendersSize(); i2xxx++) {
            if (CFG.game.getCiv(CFG.game.getWar(WAR_ID).getDefenderID(i2xxx).getCivID()).getNumOfProvinces() > 0) {
               for (int jx = 1; jx < CFG.game.getCivsSize(); jx++) {
                  if (jx != CFG.game.getWar(WAR_ID).getDefenderID(i2xxx).getCivID()
                     && (
                        CFG.game.getCiv(jx).getPuppetOfCivID() == CFG.game.getWar(WAR_ID).getDefenderID(i2xxx).getCivID()
                           || CFG.game.getCiv(CFG.game.getWar(WAR_ID).getDefenderID(i2xxx).getCivID()).getPuppetOfCivID() == jx
                     )
                     && !CFG.game.getWar(WAR_ID).getIsDefender(jx)
                     && !CFG.game.getCivsAreAllied(jx, CFG.game.getWar(WAR_ID).getDefenderID(i2xxx).getCivID())) {
                     addAlliesNotInWar = true;
                     break;
                  }
               }
            }
         }
      }

      if (addAlliesNotInWar) {
         tY = tempMaxY + CFG.PADDING * 2;
         menuElements.add(
            new Text_AlliesNotInWar(CFG.langManager.get("AlliesNotInWar"), -1, CFG.PADDING, tY, tempWidth - CFG.PADDING * 2, CFG.TEXT_HEIGHT + CFG.PADDING * 3) {
               @Override
               public int getPosX() {
                  return 2;
               }

               @Override
               public int getWidth() {
                  return Menu_InGame_WarDetails.this.getW();
               }
            }
         );
         int var27;
         tempMaxY = var27 = tY + menuElements.get(menuElements.size() - 1).getHeight();
         int tempAdded = 0;
         if (CFG.game.getCiv(CFG.game.getWar(WAR_ID).getAggressorID(0).getCivID()).getAllianceID() > 0) {
            for (int i = 0;
               i < CFG.game.getAlliance(CFG.game.getCiv(CFG.game.getWar(WAR_ID).getAggressorID(0).getCivID()).getAllianceID()).getCivilizationsSize();
               i++
            ) {
               if (!CFG.game
                  .getWar(WAR_ID)
                  .getIsInAggressors(
                     CFG.game.getAlliance(CFG.game.getCiv(CFG.game.getWar(WAR_ID).getAggressorID(0).getCivID()).getAllianceID()).getCivilization(i)
                  )) {
                  menuElements.add(
                     new Button_Statistics_CallAlly(
                        CFG.FOG_OF_WAR == 2
                              && !CFG.game
                                 .getPlayer(CFG.PLAYER_TURNID)
                                 .getMetCivilization(
                                    CFG.game
                                       .getAlliance(CFG.game.getCiv(CFG.game.getWar(WAR_ID).getAggressorID(0).getCivID()).getAllianceID())
                                       .getCivilization(i)
                                 )
                           ? -1
                           : CFG.game.getAlliance(CFG.game.getCiv(CFG.game.getWar(WAR_ID).getAggressorID(0).getCivID()).getAllianceID()).getCivilization(i),
                        2,
                        var27,
                        CFG.BUTTON_WIDTH * 2,
                        CFG.game.getWar(WAR_ID).getIsDefender(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
                     ) {
                        @Override
                        public int getWidth() {
                           return Menu_InGame_WarDetails.this.getElementW() * 5 + 2;
                        }

                        @Override
                        public void actionElement(int iID) {
                           if (Menu_InGame_WarDetails.WAR_ID >= 0 && Menu_InGame_WarDetails.WAR_ID < CFG.game.getWarsSize()) {
                              if (this.getCurrent() == CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) {
                                 CFG.menuManager
                                    .rebuildInGame_JoinAWar(
                                       CFG.game.getWar(Menu_InGame_WarDetails.WAR_ID).getAggressorID(0).getCivID(),
                                       CFG.game.getWar(Menu_InGame_WarDetails.WAR_ID).getDefenderID(0).getCivID()
                                    );
                              } else if (CFG.game.getWar(Menu_InGame_WarDetails.WAR_ID).getIsAggressor(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())) {
                                 CFG.menuManager.rebuildInGame_CallToArms(CFG.game.getWar(Menu_InGame_WarDetails.WAR_ID).getDefenderID(0).getCivID());
                              } else if (CFG.game.getWar(Menu_InGame_WarDetails.WAR_ID).getIsDefender(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())) {
                                 CFG.menuManager.rebuildInGame_DeclareWar(this.getCurrent());
                              }
                           }
                        }
                     }
                  );
                  menuElements.get(menuElements.size() - 1).setCurrent(tempAdded++ % 2);
                  menuElements.get(menuElements.size() - 1)
                     .setClickable(
                        CFG.game.getWar(WAR_ID).getIsAggressor(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
                           || CFG.game.getAlliance(CFG.game.getCiv(CFG.game.getWar(WAR_ID).getAggressorID(0).getCivID()).getAllianceID()).getCivilization(i)
                              == CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()
                           || CFG.game.getWar(WAR_ID).getIsDefender(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
                     );
                  var27 += menuElements.get(menuElements.size() - 1).getHeight();
               }
            }
         }

         for (int ix = 0; ix < CFG.game.getWar(WAR_ID).getAggressorsSize(); ix++) {
            if (CFG.game.getCiv(CFG.game.getWar(WAR_ID).getAggressorID(ix).getCivID()).getNumOfProvinces() > 0) {
               for (int j2 = 1; j2 < CFG.game.getCivsSize(); j2++) {
                  if (j2 != CFG.game.getWar(WAR_ID).getAggressorID(ix).getCivID()
                     && CFG.game.getCiv(j2).getPuppetOfCivID() == CFG.game.getWar(WAR_ID).getAggressorID(ix).getCivID()
                     && !CFG.game.getCivsAreAllied(j2, CFG.game.getWar(WAR_ID).getAggressorID(ix).getCivID())
                     && !CFG.game.getWar(WAR_ID).getIsInAggressors(j2)) {
                     menuElements.add(
                        new Button_Statistics_CallAlly(
                           CFG.FOG_OF_WAR == 2 && !CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetCivilization(j2) ? -1 : j2,
                           2,
                           var27,
                           CFG.BUTTON_WIDTH * 2,
                           CFG.game.getWar(WAR_ID).getIsDefender(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
                        ) {
                           @Override
                           public int getWidth() {
                              return Menu_InGame_WarDetails.this.getElementW() * 5 + 2;
                           }

                           @Override
                           public void actionElement(int iID) {
                              if (Menu_InGame_WarDetails.WAR_ID >= 0 && Menu_InGame_WarDetails.WAR_ID < CFG.game.getWarsSize()) {
                                 if (this.getCurrent() == CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) {
                                    CFG.menuManager
                                       .rebuildInGame_JoinAWar(
                                          CFG.game.getWar(Menu_InGame_WarDetails.WAR_ID).getAggressorID(0).getCivID(),
                                          CFG.game.getWar(Menu_InGame_WarDetails.WAR_ID).getDefenderID(0).getCivID()
                                       );
                                 } else if (CFG.game.getWar(Menu_InGame_WarDetails.WAR_ID).getIsAggressor(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())) {
                                    CFG.menuManager.rebuildInGame_CallToArms(CFG.game.getWar(Menu_InGame_WarDetails.WAR_ID).getDefenderID(0).getCivID());
                                 } else if (CFG.game.getWar(Menu_InGame_WarDetails.WAR_ID).getIsDefender(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())) {
                                    CFG.menuManager.rebuildInGame_DeclareWar(this.getCurrent());
                                 }
                              }
                           }
                        }
                     );
                     menuElements.get(menuElements.size() - 1).setCurrent(tempAdded++ % 2);
                     menuElements.get(menuElements.size() - 1)
                        .setClickable(
                           CFG.game.getWar(WAR_ID).getIsAggressor(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
                              || j2 == CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()
                              || CFG.game.getWar(WAR_ID).getIsDefender(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
                        );
                     var27 += menuElements.get(menuElements.size() - 1).getHeight();
                  }
               }
            }
         }

         tempAdded = 0;
         tY = tempMaxY;
         if (CFG.game.getCiv(CFG.game.getWar(WAR_ID).getDefenderID(0).getCivID()).getAllianceID() > 0) {
            for (int var14 = 0;
               var14 < CFG.game.getAlliance(CFG.game.getCiv(CFG.game.getWar(WAR_ID).getDefenderID(0).getCivID()).getAllianceID()).getCivilizationsSize();
               var14++
            ) {
               if (!CFG.game
                  .getWar(WAR_ID)
                  .getIsInDefenders(
                     CFG.game.getAlliance(CFG.game.getCiv(CFG.game.getWar(WAR_ID).getDefenderID(0).getCivID()).getAllianceID()).getCivilization(var14)
                  )) {
                  menuElements.add(
                     new Button_Statistics_CallAlly_Right(
                        CFG.FOG_OF_WAR == 2
                              && !CFG.game
                                 .getPlayer(CFG.PLAYER_TURNID)
                                 .getMetCivilization(
                                    CFG.game
                                       .getAlliance(CFG.game.getCiv(CFG.game.getWar(WAR_ID).getDefenderID(0).getCivID()).getAllianceID())
                                       .getCivilization(var14)
                                 )
                           ? -1
                           : CFG.game.getAlliance(CFG.game.getCiv(CFG.game.getWar(WAR_ID).getDefenderID(0).getCivID()).getAllianceID()).getCivilization(var14),
                        2,
                        tY,
                        CFG.BUTTON_WIDTH * 2,
                        CFG.game.getWar(WAR_ID).getIsAggressor(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
                     ) {
                        @Override
                        public int getPosX() {
                           return Menu_InGame_WarDetails.this.getElementW() * 5 + 4;
                        }

                        @Override
                        public int getWidth() {
                           return Menu_InGame_WarDetails.this.getW()
                              - Menu_InGame_WarDetails.this.getElementW() * 6
                              - 4
                              + Menu_InGame_WarDetails.this.getElementW()
                              + 2;
                        }

                        @Override
                        public void actionElement(int iID) {
                           if (Menu_InGame_WarDetails.WAR_ID >= 0 && Menu_InGame_WarDetails.WAR_ID < CFG.game.getWarsSize()) {
                              if (this.getCurrent() == CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) {
                                 CFG.menuManager
                                    .rebuildInGame_JoinAWar(
                                       CFG.game.getWar(Menu_InGame_WarDetails.WAR_ID).getDefenderID(0).getCivID(),
                                       CFG.game.getWar(Menu_InGame_WarDetails.WAR_ID).getAggressorID(0).getCivID()
                                    );
                              } else if (CFG.game.getWar(Menu_InGame_WarDetails.WAR_ID).getIsAggressor(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())) {
                                 CFG.menuManager.rebuildInGame_DeclareWar(this.getCurrent());
                              } else if (CFG.game.getWar(Menu_InGame_WarDetails.WAR_ID).getIsDefender(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())) {
                                 CFG.menuManager.rebuildInGame_CallToArms(CFG.game.getWar(Menu_InGame_WarDetails.WAR_ID).getAggressorID(0).getCivID());
                              }
                           }
                        }
                     }
                  );
                  menuElements.get(menuElements.size() - 1).setCurrent(tempAdded++ % 2);
                  menuElements.get(menuElements.size() - 1)
                     .setClickable(
                        CFG.game.getWar(WAR_ID).getIsDefender(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
                           || CFG.game.getAlliance(CFG.game.getCiv(CFG.game.getWar(WAR_ID).getDefenderID(0).getCivID()).getAllianceID()).getCivilization(var14)
                              == CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()
                           || CFG.game.getWar(WAR_ID).getIsAggressor(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
                     );
                  tY += menuElements.get(menuElements.size() - 1).getHeight();
               }
            }
         }

         for (int var15 = 0; var15 < CFG.game.getWar(WAR_ID).getDefendersSize(); var15++) {
            if (CFG.game.getCiv(CFG.game.getWar(WAR_ID).getDefenderID(var15).getCivID()).getNumOfProvinces() > 0) {
               for (int j2x = 1; j2x < CFG.game.getCivsSize(); j2x++) {
                  if (j2x != CFG.game.getWar(WAR_ID).getDefenderID(var15).getCivID()
                     && CFG.game.getCiv(j2x).getPuppetOfCivID() == CFG.game.getWar(WAR_ID).getDefenderID(var15).getCivID()
                     && !CFG.game.getCivsAreAllied(j2x, CFG.game.getWar(WAR_ID).getDefenderID(var15).getCivID())
                     && !CFG.game.getWar(WAR_ID).getIsInDefenders(j2x)) {
                     menuElements.add(
                        new Button_Statistics_CallAlly_Right(
                           CFG.FOG_OF_WAR == 2 && !CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetCivilization(j2x) ? -1 : j2x,
                           2,
                           tY,
                           CFG.BUTTON_WIDTH * 2,
                           CFG.game.getWar(WAR_ID).getIsAggressor(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
                        ) {
                           @Override
                           public int getPosX() {
                              return Menu_InGame_WarDetails.this.getElementW() * 5 + 4;
                           }

                           @Override
                           public int getWidth() {
                              return Menu_InGame_WarDetails.this.getW()
                                 - Menu_InGame_WarDetails.this.getElementW() * 6
                                 - 4
                                 + Menu_InGame_WarDetails.this.getElementW()
                                 + 2;
                           }

                           @Override
                           public void actionElement(int iID) {
                              if (Menu_InGame_WarDetails.WAR_ID >= 0 && Menu_InGame_WarDetails.WAR_ID < CFG.game.getWarsSize()) {
                                 if (this.getCurrent() == CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) {
                                    CFG.menuManager
                                       .rebuildInGame_JoinAWar(
                                          CFG.game.getWar(Menu_InGame_WarDetails.WAR_ID).getDefenderID(0).getCivID(),
                                          CFG.game.getWar(Menu_InGame_WarDetails.WAR_ID).getAggressorID(0).getCivID()
                                       );
                                 } else if (CFG.game.getWar(Menu_InGame_WarDetails.WAR_ID).getIsAggressor(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())) {
                                    CFG.menuManager.rebuildInGame_DeclareWar(this.getCurrent());
                                 } else if (CFG.game.getWar(Menu_InGame_WarDetails.WAR_ID).getIsDefender(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())) {
                                    CFG.menuManager.rebuildInGame_CallToArms(CFG.game.getWar(Menu_InGame_WarDetails.WAR_ID).getAggressorID(0).getCivID());
                                 }
                              }
                           }
                        }
                     );
                     menuElements.get(menuElements.size() - 1).setCurrent(tempAdded++ % 2);
                     menuElements.get(menuElements.size() - 1)
                        .setClickable(
                           CFG.game.getWar(WAR_ID).getIsDefender(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
                              || j2x == CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()
                              || CFG.game.getWar(WAR_ID).getIsAggressor(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
                        );
                     tY += menuElements.get(menuElements.size() - 1).getHeight();
                  }
               }
            }
         }
      }

      tempMaxY = 0;
      int iSize2 = menuElements.size();

      for (int i2xxxx = 0; i2xxxx < iSize2; i2xxxx++) {
         if (menuElements.get(i2xxxx).getPosY() + menuElements.get(i2xxxx).getHeight() > tempMaxY) {
            tempMaxY = menuElements.get(i2xxxx).getPosY() + menuElements.get(i2xxxx).getHeight();
         }
      }

      if (CFG.game.getWar(WAR_ID).getIsAggressor(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
         || CFG.game.getWar(WAR_ID).getIsDefender(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())) {
         int var31;
         menuElements.add(
            new Button_FlagActionSliderStyle_Animated(CFG.langManager.get("PeaceNegotiations"), -1, 2, var31 = tempMaxY + CFG.PADDING, CFG.BUTTON_WIDTH, true) {
               @Override
               public int getPosX() {
                  return 2 + CFG.PADDING;
               }

               @Override
               public int getWidth() {
                  return Menu_InGame_WarDetails.this.getW() - CFG.PADDING * 2;
               }

               @Override
               public void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                  ImageManager.getImage(Images.diplo_truce)
                     .draw(
                        oSB,
                        this.getPosX()
                           + this.getWidth() / 2
                           - CFG.PADDING
                           - (int)(ImageManager.getImage(Images.diplo_truce).getWidth() * Menu_InGame_WarDetails.this.getImageScale3(Images.diplo_truce)) / 2
                           - (int)(this.getTextWidth() * 0.8F / 2.0F)
                           + iTranslateX,
                        this.getPosY()
                           + this.getHeight() / 2
                           - (int)(ImageManager.getImage(Images.diplo_truce).getHeight() * Menu_InGame_WarDetails.this.getImageScale3(Images.diplo_truce)) / 2
                           - ImageManager.getImage(Images.diplo_truce).getHeight()
                           + iTranslateY,
                        (int)(ImageManager.getImage(Images.diplo_truce).getWidth() * Menu_InGame_WarDetails.this.getImageScale3(Images.diplo_truce)),
                        (int)(ImageManager.getImage(Images.diplo_truce).getHeight() * Menu_InGame_WarDetails.this.getImageScale3(Images.diplo_truce))
                     );
                  CFG.fontMain.getData().setScale(0.8F);
                  CFG.drawText(
                     oSB,
                     this.getText(),
                     this.getPosX()
                        + (
                           this.getTextPos() < 0
                              ? this.getWidth() / 2
                                 - (int)(
                                    (
                                          this.getTextWidth() * 0.8F
                                             + (int)(
                                                ImageManager.getImage(Images.diplo_truce).getWidth()
                                                   * Menu_InGame_WarDetails.this.getImageScale3(Images.diplo_truce)
                                             )
                                             + CFG.PADDING
                                       )
                                       / 2.0F
                                 )
                                 + (int)(ImageManager.getImage(Images.diplo_truce).getWidth() * Menu_InGame_WarDetails.this.getImageScale3(Images.diplo_truce))
                                 + CFG.PADDING
                              : this.getTextPos()
                        )
                        + iTranslateX,
                     this.getPosY() + this.getHeight() / 2 - (int)(this.getTextHeight() * 0.8F / 2.0F) + iTranslateY,
                     this.getColor(isActive)
                  );
                  CFG.fontMain.getData().setScale(1.0F);
               }

               @Override
               public void actionElement(int iID) {
                  if (!CFG.SPECTATOR_MODE) {
                     CFG.game.getPlayer(CFG.PLAYER_TURNID).iBefore_ActiveProvince = CFG.game.getActiveProvinceID();
                     CFG.game.getPlayer(CFG.PLAYER_TURNID).iACTIVE_VIEW_MODE = CFG.viewsManager.getActiveViewID();
                     CFG.viewsManager.disableAllViews();
                     Menu_PeaceTreaty.WAR_ID = Menu_InGame_WarDetails.WAR_ID;
                     CFG.peaceTreatyData = new PeaceTreaty_Data(
                        Menu_PeaceTreaty.WAR_ID,
                        CFG.game.getWar(Menu_InGame_WarDetails.WAR_ID).getIsAggressor(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
                     );
                     CFG.game.resetChooseProvinceData_Immediately();
                     CFG.game.resetRegroupArmyData();
                     CFG.menuManager.setViewID(Menu.eINGAME_PEACE_TREATY);
                  }
               }

               @Override
               public int getSFX() {
                  return SoundsManager.SOUND_CLICK2;
               }

               @Override
               public void buildElementHover() {
                  ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
                  ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
                  nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("PeaceNegotiations"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));

                  for (int i = 0; i < CFG.game.getWar(Menu_InGame_WarDetails.WAR_ID).getAggressorsSize() && i < 5; i++) {
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Flag(
                           CFG.game.getWar(Menu_InGame_WarDetails.WAR_ID).getAggressorID(i).getCivID(), i == 0 ? CFG.PADDING : 0, 0
                        )
                     );
                  }

                  nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.diplo_truce, CFG.PADDING, 0));

                  for (int var4 = 0; var4 < CFG.game.getWar(Menu_InGame_WarDetails.WAR_ID).getDefendersSize() && var4 < 5; var4++) {
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Flag(
                           CFG.game.getWar(Menu_InGame_WarDetails.WAR_ID).getDefenderID(var4).getCivID(), var4 == 0 ? CFG.PADDING : 0, 0
                        )
                     );
                  }

                  nElements.add(new MenuElement_Hover_v2_Element2(nData));
                  nData.clear();
                  this.menuElementHover = new MenuElement_Hover_v2(nElements);
               }
            }
         );
         tempMaxY = var31 + menuElements.get(menuElements.size() - 1).getHeight();
      }

      menuElements.add(new Button_Transparent(0, 0, tempWidth, tempMaxY, true) {
         @Override
         public int getPosX() {
            return 2;
         }

         @Override
         public int getWidth() {
            return Menu_InGame_WarDetails.this.getW();
         }
      });
      int tempMenuPosY = ImageManager.getImage(Images.top_flag_frame).getHeight()
         + CFG.PADDING * 4
         + CFG.BUTTON_HEIGHT * 3 / 5
         + CFG.PADDING * 2
         + CFG.BUTTON_HEIGHT / 2;
      this.initMenu(
         new SliderMenuTitle(
            CFG.FOG_OF_WAR == 2
               ? (
                  CFG.game.getCiv(CFG.game.getWar(WAR_ID).getAggressorID(0).getCivID()).getAllianceID() > 0
                     ? (
                        CFG.game
                              .getPlayer(CFG.PLAYER_TURNID)
                              .getMetAlliance(CFG.game.getCiv(CFG.game.getWar(WAR_ID).getAggressorID(0).getCivID()).getAllianceID())
                           ? CFG.game.getAlliance(CFG.game.getCiv(CFG.game.getWar(WAR_ID).getAggressorID(0).getCivID()).getAllianceID()).getAllianceName()
                           : CFG.langManager.get("Undiscovered")
                     )
                     : (
                        CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetCivilization(CFG.game.getWar(WAR_ID).getAggressorID(0).getCivID())
                           ? CFG.game.getCiv(CFG.game.getWar(WAR_ID).getAggressorID(0).getCivID()).getCivName()
                           : CFG.langManager.get("Undiscovered")
                     )
               )
               : (
                  CFG.game.getCiv(CFG.game.getWar(WAR_ID).getAggressorID(0).getCivID()).getAllianceID() > 0
                     ? CFG.game.getAlliance(CFG.game.getCiv(CFG.game.getWar(WAR_ID).getAggressorID(0).getCivID()).getAllianceID()).getAllianceName()
                     : CFG.game.getCiv(CFG.game.getWar(WAR_ID).getAggressorID(0).getCivID()).getCivName()
               ),
            CFG.BUTTON_HEIGHT * 3 / 5,
            true,
            true
         ) {
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
               oSB.setColor(new Color(0.5411765F, 0.050980393F, 0.050980393F, 0.165F));
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
               oSB.setColor(new Color(0.5411765F, 0.050980393F, 0.050980393F, 0.375F));
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
               ImageManager.getImage(Images.diplo_rivals)
                  .draw(
                     oSB,
                     nPosX + nWidth / 2 - ImageManager.getImage(Images.diplo_rivals).getWidth() / 2 + iTranslateX,
                     2 + nPosY - this.getHeight() + this.getHeight() / 2 - ImageManager.getImage(Images.diplo_rivals).getHeight() / 2
                  );
               CFG.fontMain.getData().setScale(0.7F);
               CFG.drawText(
                  oSB,
                  this.getText(),
                  nPosX
                     + nWidth / 2
                     - (int)(this.getTextWidth() * 0.7F)
                     - ImageManager.getImage(Images.diplo_rivals).getWidth() / 2
                     - CFG.PADDING
                     + iTranslateX,
                  2 + nPosY - this.getHeight() + (int)(this.getHeight() - this.getTextHeight() * 0.7F) / 2,
                  Color.WHITE
               );
               CFG.drawText(
                  oSB,
                  Menu_InGame_WarDetails.this.sDefender,
                  nPosX + nWidth / 2 + ImageManager.getImage(Images.diplo_rivals).getWidth() / 2 + CFG.PADDING + iTranslateX,
                  2 + nPosY - this.getHeight() + (int)(this.getHeight() - this.getTextHeight() * 0.7F) / 2,
                  Color.WHITE
               );
               ImageManager.getImage(Images.time)
                  .draw(
                     oSB,
                     nPosX
                        + nWidth
                        - CFG.PADDING
                        - 2
                        - (int)(ImageManager.getImage(Images.time).getWidth() * Menu_InGame_WarDetails.this.getImageScale2(Images.time))
                        + iTranslateX,
                     nPosY
                        - CFG.PADDING
                        - (int)(ImageManager.getImage(Images.time).getHeight() * Menu_InGame_WarDetails.this.getImageScale2(Images.time))
                        - ImageManager.getImage(Images.time).getHeight(),
                     (int)(ImageManager.getImage(Images.time).getWidth() * Menu_InGame_WarDetails.this.getImageScale2(Images.time)),
                     (int)(ImageManager.getImage(Images.time).getHeight() * Menu_InGame_WarDetails.this.getImageScale2(Images.time))
                  );
               CFG.fontMain.getData().setScale(0.55F);
               CFG.drawText(
                  oSB,
                  Menu_InGame_WarDetails.this.sWarDate,
                  nPosX
                     + nWidth
                     - Menu_InGame_WarDetails.this.iWarDateWidth
                     - CFG.PADDING * 2
                     - (int)(ImageManager.getImage(Images.time).getWidth() * Menu_InGame_WarDetails.this.getImageScale2(Images.time))
                     - 2
                     + iTranslateX,
                  nPosY - CFG.PADDING - (int)(CFG.TEXT_HEIGHT * 0.55F),
                  CFG.COLOR_TEXT_MODIFIER_NEUTRAL
               );
               CFG.fontMain.getData().setScale(1.0F);
            }
         },
         CFG.GAME_WIDTH / 2 - tempWidth * 3 / 4,
         tempMenuPosY,
         tempWidth,
         menuElements.get(menuElements.size() - 1).getPosY() + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING + tempMenuPosY
               > CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING * 2
            ? Math.max(CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING * 2 - tempMenuPosY, (CFG.TEXT_HEIGHT + CFG.PADDING * 2) * 6)
            : menuElements.get(menuElements.size() - 1).getPosY() + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING,
         menuElements,
         true,
         true
      );
      this.updateLanguage();

      for (int ixx = 0; ixx < this.getMenuElementsSize() && ixx < CFG.game.getWar(WAR_ID).getAggressorsSize() * 2; ixx++) {
         this.getMenuElement(ixx).setCurrent(ixx / 2 % 2);
      }

      for (int var17 = 4 + CFG.game.getWar(WAR_ID).getAggressorsSize() * 2;
         var17 < this.getMenuElementsSize() && var17 < 4 + CFG.game.getWar(WAR_ID).getAggressorsSize() * 2 + CFG.game.getWar(WAR_ID).getDefendersSize();
         var17++
      ) {
         this.getMenuElement(var17).setCurrent((var17 / 2 + (CFG.game.getWar(WAR_ID).getAggressorsSize() + 1) % 2) % 2);
      }
   }

   @Override
   public void updateLanguage() {
   }

   public final float getImageScale3(int nImageID) {
      return CFG.TEXT_HEIGHT * 1.0F / ImageManager.getImage(nImageID).getHeight() < 1.0F
         ? CFG.TEXT_HEIGHT * 1.0F / ImageManager.getImage(nImageID).getHeight()
         : 1.0F;
   }

   public final float getImageScale2(int nImageID) {
      return CFG.TEXT_HEIGHT * 0.55F / ImageManager.getImage(nImageID).getHeight() < 1.0F
         ? CFG.TEXT_HEIGHT * 0.55F / ImageManager.getImage(nImageID).getHeight()
         : 1.0F;
   }

   public final void clickFlag(int iID) {
      try {
         CFG.toast.setInView(CFG.game.getCiv(this.getMenuElement(iID).getCurrent()).getCivName(), CFG.COLOR_TEXT_NUM_OF_PROVINCES);
         if (CFG.FOG_OF_WAR == 2) {
            if (CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetCivilization(this.getMenuElement(iID).getCurrent())
               && CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetProvince(CFG.game.getCiv(this.getMenuElement(iID).getCurrent()).getCapitalProvinceID())) {
               CFG.game.setActiveProvinceID(CFG.game.getCiv(this.getMenuElement(iID).getCurrent()).getCapitalProvinceID());
               CFG.map.getMapCoordinates().centerToProvinceID(CFG.game.getActiveProvinceID());
            }
         } else {
            CFG.game.setActiveProvinceID(CFG.game.getCiv(this.getMenuElement(iID).getCurrent()).getCapitalProvinceID());
            CFG.map.getMapCoordinates().centerToProvinceID(CFG.game.getActiveProvinceID());
         }

         if (CFG.viewsManager.getActiveViewID() == ViewsManager.VIEW_DIPLOMACY_MODE) {
            CFG.game.disableDrawCivilizationRegions_Active();
            CFG.game.enableDrawCivilizationRegions_ActiveProvince();
         }
      } catch (IndexOutOfBoundsException var3) {
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
            this.getWidth() - ImageManager.getImage(Images.new_game_top_edge).getWidth(),
            this.getHeight() + CFG.PADDING,
            false,
            true
         );
      ImageManager.getImage(Images.new_game_top_edge)
         .draw2(
            oSB,
            this.getPosX() + this.getWidth() - ImageManager.getImage(Images.new_game_top_edge).getWidth() + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.new_game_top_edge).getHeight() + iTranslateY,
            ImageManager.getImage(Images.new_game_top_edge).getWidth(),
            this.getHeight() + CFG.PADDING,
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
   public void drawCloseButton(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      this.getCloseButtonImage(sliderMenuIsActive)
         .draw(
            oSB,
            this.getPosX() + this.getWidth() - ImageManager.getImage(Images.btn_close).getWidth() * 3 / 5 + iTranslateX,
            this.getPosY() - this.getTitle().getHeight() - ImageManager.getImage(Images.btn_close).getHeight() + iTranslateY,
            ImageManager.getImage(Images.btn_close).getWidth() * 3 / 5,
            ImageManager.getImage(Images.btn_close).getHeight() * 3 / 5
         );
   }

   @Override
   public final void actionElement(int iID) {
      if (iID != this.getMenuElementsSize() - 1) {
         this.getMenuElement(iID).actionElement(iID);
      }
   }

   public final int getW() {
      return this.getWidth() - 4;
   }

   public final int getElementW() {
      return this.getW() / 10;
   }

   @Override
   public void setVisible(boolean visible) {
      super.setVisible(visible);
      if (!visible) {
         for (int i = 0; i < this.getMenuElementsSize(); i++) {
            this.getMenuElement(i).setVisible(false);
         }
      }
   }
}
