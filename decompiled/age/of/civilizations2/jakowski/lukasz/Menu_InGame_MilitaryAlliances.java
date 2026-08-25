package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;
import java.util.List;

public class Menu_InGame_MilitaryAlliances extends SliderMenu {
   public static int sortBy = 1;
   public List<Integer> lSorted = new ArrayList<>();

   public Menu_InGame_MilitaryAlliances() {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      int tempWidth = CFG.CIV_INFO_MENU_WIDTH * 2 + CFG.CIV_INFO_MENU_WIDTH / 2;
      int tempHeight = CFG.BUTTON_HEIGHT + CFG.BUTTON_HEIGHT * 3 / 4;
      int tElemHeight = CFG.TEXT_HEIGHT + CFG.PADDING * 4;
      int tElemHeight2 = CFG.isAndroid() ? CFG.TEXT_HEIGHT + CFG.PADDING * 4 : CFG.TEXT_HEIGHT + CFG.PADDING * 4;
      menuElements.add(new Button_Statistics_Title(CFG.langManager.get("Name"), CFG.PADDING * 2, 2, 0, CFG.BUTTON_WIDTH * 2, tElemHeight) {
         @Override
         public int getWidth() {
            return Menu_InGame_MilitaryAlliances.this.getElementW() * 2 + CFG.PADDING * 2 - 2;
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
         public Color getColor(boolean isActive) {
            return Menu_InGame_MilitaryAlliances.sortBy == 0 ? CFG.COLOR_TEXT_NUM_OF_PROVINCES : super.getColor(isActive);
         }
      });
      menuElements.add(
         new Button_Statistics_Title(CFG.langManager.get("Members"), CFG.PADDING, CFG.PADDING * 2 + CFG.BUTTON_WIDTH * 2, 0, CFG.BUTTON_WIDTH, tElemHeight) {
            @Override
            public int getPosX() {
               return Menu_InGame_MilitaryAlliances.this.getElementW() * 2 + CFG.PADDING * 2;
            }

            @Override
            public int getWidth() {
               return Menu_InGame_MilitaryAlliances.this.getElementW();
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
            public Color getColor(boolean isActive) {
               return Menu_InGame_MilitaryAlliances.sortBy == 1 ? CFG.COLOR_TEXT_NUM_OF_PROVINCES : super.getColor(isActive);
            }
         }
      );
      menuElements.add(
         new Button_Statistics_Title(CFG.langManager.get("Provinces"), CFG.PADDING, CFG.PADDING * 2 + CFG.BUTTON_WIDTH * 3, 0, CFG.BUTTON_WIDTH, tElemHeight) {
            @Override
            public int getPosX() {
               return Menu_InGame_MilitaryAlliances.this.getElementW() * 3 + CFG.PADDING * 2;
            }

            @Override
            public int getWidth() {
               return Menu_InGame_MilitaryAlliances.this.getElementW();
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
            public Color getColor(boolean isActive) {
               return Menu_InGame_MilitaryAlliances.sortBy == 2 ? CFG.COLOR_TEXT_NUM_OF_PROVINCES : super.getColor(isActive);
            }
         }
      );
      menuElements.add(
         new Button_Statistics_Title(CFG.langManager.get("Population"), CFG.PADDING, CFG.PADDING * 2 + CFG.BUTTON_WIDTH * 4, 0, CFG.BUTTON_WIDTH, tElemHeight) {
            @Override
            public int getPosX() {
               return Menu_InGame_MilitaryAlliances.this.getElementW() * 4 + CFG.PADDING * 2;
            }

            @Override
            public int getWidth() {
               return Menu_InGame_MilitaryAlliances.this.getElementW();
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
            public Color getColor(boolean isActive) {
               return Menu_InGame_MilitaryAlliances.sortBy == 3 ? CFG.COLOR_TEXT_NUM_OF_PROVINCES : super.getColor(isActive);
            }
         }
      );
      menuElements.add(
         new Button_Statistics_Title(CFG.langManager.get("Headquarters"), CFG.PADDING, CFG.PADDING * 2 + CFG.BUTTON_WIDTH * 4, 0, CFG.BUTTON_WIDTH, tElemHeight) {
            @Override
            public int getPosX() {
               return Menu_InGame_MilitaryAlliances.this.getElementW() * 5 + CFG.PADDING * 2;
            }

            @Override
            public int getWidth() {
               return Menu_InGame_MilitaryAlliances.this.getElementW();
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
            public Color getColor(boolean isActive) {
               return Menu_InGame_MilitaryAlliances.sortBy == 4 ? CFG.COLOR_TEXT_NUM_OF_PROVINCES : super.getColor(isActive);
            }
         }
      );
      menuElements.add(
         new Button_Statistics_Title(CFG.langManager.get("Formation"), CFG.PADDING, CFG.PADDING * 2 + CFG.BUTTON_WIDTH * 5, 0, CFG.BUTTON_WIDTH, tElemHeight) {
            @Override
            public int getPosX() {
               return Menu_InGame_MilitaryAlliances.this.getElementW() * 6 + CFG.PADDING * 2;
            }

            @Override
            public int getWidth() {
               return Menu_InGame_MilitaryAlliances.this.getW() - Menu_InGame_MilitaryAlliances.this.getElementW() * 6 + CFG.PADDING * 2 - 2;
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
            public Color getColor(boolean isActive) {
               return Menu_InGame_MilitaryAlliances.sortBy == 5 ? CFG.COLOR_TEXT_NUM_OF_PROVINCES : super.getColor(isActive);
            }
         }
      );
      int tPosY = CFG.PADDING + tElemHeight;
      ArrayList<Integer> tProvinces = new ArrayList<>();
      ArrayList<Integer> tPopulation = new ArrayList<>();

      for (int i3 = 1; i3 < CFG.game.getAlliancesSize(); i3++) {
         tProvinces.add(CFG.game.getAlliance(i3).countProvinces());
         tPopulation.add(CFG.game.getAlliance(i3).countPopulation());
      }

      ArrayList<Integer> tempIDs = new ArrayList<>();

      for (int i2 = 1; i2 < CFG.game.getAlliancesSize(); i2++) {
         tempIDs.add(i2);
      }

      if (sortBy != 0) {
         if (sortBy == 1) {
            while (tempIDs.size() > 0) {
               int tAdd = 0;

               for (int i = 1; i < tempIDs.size(); i++) {
                  if (CFG.game.getAlliance(tempIDs.get(tAdd)).getCivilizationsSize() < CFG.game.getAlliance(tempIDs.get(i)).getCivilizationsSize()) {
                     tAdd = i;
                  }
               }

               this.lSorted.add(tempIDs.get(tAdd));
               tempIDs.remove(tAdd);
            }
         } else if (sortBy == 2) {
            while (tempIDs.size() > 0) {
               int tAdd = 0;

               for (int ix = 1; ix < tempIDs.size(); ix++) {
                  if (tProvinces.get(tempIDs.get(tAdd) - 1) < tProvinces.get(tempIDs.get(ix) - 1)) {
                     tAdd = ix;
                  }
               }

               this.lSorted.add(tempIDs.get(tAdd));
               tempIDs.remove(tAdd);
            }
         } else if (sortBy == 3) {
            while (tempIDs.size() > 0) {
               int tAdd = 0;

               for (int ixx = 1; ixx < tempIDs.size(); ixx++) {
                  if (tPopulation.get(tempIDs.get(tAdd) - 1) < tPopulation.get(tempIDs.get(ixx) - 1)) {
                     tAdd = ixx;
                  }
               }

               this.lSorted.add(tempIDs.get(tAdd));
               tempIDs.remove(tAdd);
            }
         } else if (sortBy == 4) {
            while (tempIDs.size() > 0) {
               int tAdd = 0;

               for (int ixxx = 1; ixxx < tempIDs.size(); ixxx++) {
                  if (CFG.compareAlphabetic_TwoString(
                     CFG.game.getProvince(CFG.game.getCiv(CFG.game.getAlliance(tempIDs.get(tAdd)).getCivilization(0)).getCapitalProvinceID()).getCitiesSize()
                           > 0
                        ? CFG.game
                           .getProvince(CFG.game.getCiv(CFG.game.getAlliance(tempIDs.get(tAdd)).getCivilization(0)).getCapitalProvinceID())
                           .getCity(0)
                           .getCityName()
                        : (
                           CFG.game
                                    .getProvince(CFG.game.getCiv(CFG.game.getAlliance(tempIDs.get(tAdd)).getCivilization(0)).getCapitalProvinceID())
                                    .getName()
                                    .length()
                                 > 0
                              ? CFG.game
                                 .getProvince(CFG.game.getCiv(CFG.game.getAlliance(tempIDs.get(tAdd)).getCivilization(0)).getCapitalProvinceID())
                                 .getName()
                              : CFG.game.getCiv(CFG.game.getAlliance(tempIDs.get(tAdd)).getCivilization(0)).getCivName()
                        ),
                     CFG.game.getProvince(CFG.game.getCiv(CFG.game.getAlliance(tempIDs.get(ixxx)).getCivilization(0)).getCapitalProvinceID()).getCitiesSize()
                           > 0
                        ? CFG.game
                           .getProvince(CFG.game.getCiv(CFG.game.getAlliance(tempIDs.get(ixxx)).getCivilization(0)).getCapitalProvinceID())
                           .getCity(0)
                           .getCityName()
                        : (
                           CFG.game
                                    .getProvince(CFG.game.getCiv(CFG.game.getAlliance(tempIDs.get(ixxx)).getCivilization(0)).getCapitalProvinceID())
                                    .getName()
                                    .length()
                                 > 0
                              ? CFG.game
                                 .getProvince(CFG.game.getCiv(CFG.game.getAlliance(tempIDs.get(ixxx)).getCivilization(0)).getCapitalProvinceID())
                                 .getName()
                              : CFG.game.getCiv(CFG.game.getAlliance(tempIDs.get(ixxx)).getCivilization(0)).getCivName()
                        )
                  )) {
                     tAdd = ixxx;
                  }
               }

               this.lSorted.add(tempIDs.get(tAdd));
               tempIDs.remove(tAdd);
            }
         } else if (sortBy == 5) {
            while (tempIDs.size() > 0) {
               int tAdd = 0;

               for (int ixxxx = 1; ixxxx < tempIDs.size(); ixxxx++) {
                  if (CFG.game.getAlliance(tempIDs.get(tAdd)).getFormationTurnID() < CFG.game.getAlliance(tempIDs.get(ixxxx)).getFormationTurnID()) {
                     tAdd = ixxxx;
                  }
               }

               this.lSorted.add(tempIDs.get(tAdd));
               tempIDs.remove(tAdd);
            }
         }
      } else {
         while (tempIDs.size() > 0) {
            int tAdd = 0;

            for (int ixxxxx = 1; ixxxxx < tempIDs.size(); ixxxxx++) {
               if (CFG.compareAlphabetic_TwoString(
                  CFG.game.getAlliance(tempIDs.get(tAdd)).getAllianceName(), CFG.game.getAlliance(tempIDs.get(ixxxxx)).getAllianceName()
               )) {
                  tAdd = ixxxxx;
               }
            }

            this.lSorted.add(tempIDs.get(tAdd));
            tempIDs.remove(tAdd);
         }
      }

      for (int var23 = 0; var23 < this.lSorted.size(); var23++) {
         menuElements.add(
            new Button_Statistics_Alliance_Clip(
               CFG.FOG_OF_WAR == 2 && !CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetAlliance(this.lSorted.get(var23)) ? -1 : this.lSorted.get(var23),
               CFG.FOG_OF_WAR == 2
                  ? (
                     CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetAlliance(this.lSorted.get(var23))
                        ? CFG.game.getAlliance(this.lSorted.get(var23)).getAllianceName()
                        : CFG.langManager.get("Undiscovered")
                  )
                  : CFG.game.getAlliance(this.lSorted.get(var23)).getAllianceName(),
               CFG.PADDING,
               CFG.PADDING * 2,
               tPosY,
               CFG.BUTTON_WIDTH * 2,
               tElemHeight2
            ) {
               @Override
               public int getWidth() {
                  return Menu_InGame_MilitaryAlliances.this.getElementW() * 2;
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
               public int getSFX() {
                  return SoundsManager.SOUND_CLICK2;
               }
            }
         );
         menuElements.add(
            new Button_Statistics(
               "" + CFG.game.getAlliance(this.lSorted.get(var23)).getCivilizationsSize(),
               CFG.PADDING,
               CFG.PADDING * 2 + CFG.BUTTON_WIDTH * 3,
               tPosY,
               CFG.BUTTON_WIDTH,
               tElemHeight2
            ) {
               @Override
               public int getPosX() {
                  return Menu_InGame_MilitaryAlliances.this.getElementW() * 2 + CFG.PADDING * 2;
               }

               @Override
               public int getWidth() {
                  return Menu_InGame_MilitaryAlliances.this.getElementW();
               }
            }
         );
         menuElements.add(
            new Button_Statistics(
               CFG.getNumberWithSpaces("" + tProvinces.get(this.lSorted.get(var23) - 1)),
               CFG.PADDING,
               CFG.PADDING * 2 + CFG.BUTTON_WIDTH * 4,
               tPosY,
               CFG.BUTTON_WIDTH,
               tElemHeight2
            ) {
               @Override
               public int getPosX() {
                  return Menu_InGame_MilitaryAlliances.this.getElementW() * 3 + CFG.PADDING * 2;
               }

               @Override
               public int getWidth() {
                  return Menu_InGame_MilitaryAlliances.this.getElementW();
               }
            }
         );
         menuElements.add(
            new Button_Statistics(
               CFG.getNumberWithSpaces("" + tPopulation.get(this.lSorted.get(var23) - 1)),
               CFG.PADDING,
               CFG.PADDING * 2 + CFG.BUTTON_WIDTH * 4,
               tPosY,
               CFG.BUTTON_WIDTH,
               tElemHeight2
            ) {
               @Override
               public int getPosX() {
                  return Menu_InGame_MilitaryAlliances.this.getElementW() * 4 + CFG.PADDING * 2;
               }

               @Override
               public int getWidth() {
                  return Menu_InGame_MilitaryAlliances.this.getElementW();
               }
            }
         );
         menuElements.add(
            new Button_Statistics_Flag_Clip(
               CFG.FOG_OF_WAR == 2
                  ? (
                     CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetCivilization(CFG.game.getAlliance(this.lSorted.get(var23)).getCivilization(0))
                        ? CFG.game.getAlliance(this.lSorted.get(var23)).getCivilization(0)
                        : -1
                  )
                  : CFG.game.getAlliance(this.lSorted.get(var23)).getCivilization(0),
               CFG.FOG_OF_WAR == 2
                  ? (
                     CFG.game
                           .getPlayer(CFG.PLAYER_TURNID)
                           .getMetProvince(CFG.game.getCiv(CFG.game.getAlliance(this.lSorted.get(var23)).getCivilization(0)).getCapitalProvinceID())
                        ? (
                           CFG.game
                                    .getProvince(CFG.game.getCiv(CFG.game.getAlliance(this.lSorted.get(var23)).getCivilization(0)).getCapitalProvinceID())
                                    .getCitiesSize()
                                 > 0
                              ? CFG.game
                                 .getProvince(CFG.game.getCiv(CFG.game.getAlliance(this.lSorted.get(var23)).getCivilization(0)).getCapitalProvinceID())
                                 .getCity(0)
                                 .getCityName()
                              : (
                                 CFG.game
                                          .getProvince(CFG.game.getCiv(CFG.game.getAlliance(this.lSorted.get(var23)).getCivilization(0)).getCapitalProvinceID())
                                          .getName()
                                          .length()
                                       > 0
                                    ? CFG.game
                                       .getProvince(CFG.game.getCiv(CFG.game.getAlliance(this.lSorted.get(var23)).getCivilization(0)).getCapitalProvinceID())
                                       .getName()
                                    : CFG.game.getCiv(CFG.game.getAlliance(this.lSorted.get(var23)).getCivilization(0)).getCivName()
                              )
                        )
                        : CFG.langManager.get("Undiscovered")
                  )
                  : (
                     CFG.game
                              .getProvince(CFG.game.getCiv(CFG.game.getAlliance(this.lSorted.get(var23)).getCivilization(0)).getCapitalProvinceID())
                              .getCitiesSize()
                           > 0
                        ? CFG.game
                           .getProvince(CFG.game.getCiv(CFG.game.getAlliance(this.lSorted.get(var23)).getCivilization(0)).getCapitalProvinceID())
                           .getCity(0)
                           .getCityName()
                        : (
                           CFG.game
                                    .getProvince(CFG.game.getCiv(CFG.game.getAlliance(this.lSorted.get(var23)).getCivilization(0)).getCapitalProvinceID())
                                    .getName()
                                    .length()
                                 > 0
                              ? CFG.game
                                 .getProvince(CFG.game.getCiv(CFG.game.getAlliance(this.lSorted.get(var23)).getCivilization(0)).getCapitalProvinceID())
                                 .getName()
                              : CFG.game.getCiv(CFG.game.getAlliance(this.lSorted.get(var23)).getCivilization(0)).getCivName()
                        )
                  ),
               CFG.PADDING,
               CFG.PADDING * 2 + CFG.BUTTON_WIDTH * 2,
               tPosY,
               CFG.BUTTON_WIDTH,
               tElemHeight2
            ) {
               @Override
               public int getPosX() {
                  return Menu_InGame_MilitaryAlliances.this.getElementW() * 5 + CFG.PADDING * 2;
               }

               @Override
               public int getWidth() {
                  return Menu_InGame_MilitaryAlliances.this.getElementW();
               }

               @Override
               public void buildElementHover() {
                  try {
                     ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
                     ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
                     nData.add(new MenuElement_Hover_v2_Element_Type_Flag(this.getCurrent()));
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.game.getCiv(this.getCurrent()).getCivName(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                     this.menuElementHover = new MenuElement_Hover_v2(nElements);
                  } catch (IndexOutOfBoundsException var3) {
                  }
               }
            }
         );
         menuElements.add(
            new Button_Statistics_Clip(
               "" + Game_Calendar.getDate_ByTurnID(CFG.game.getAlliance(this.lSorted.get(var23)).getFormationTurnID()),
               CFG.PADDING,
               CFG.PADDING * 2 + CFG.BUTTON_WIDTH * 5,
               tPosY,
               CFG.BUTTON_WIDTH,
               tElemHeight2
            ) {
               @Override
               public int getPosX() {
                  return Menu_InGame_MilitaryAlliances.this.getElementW() * 6 + CFG.PADDING * 2;
               }

               @Override
               public int getWidth() {
                  return Menu_InGame_MilitaryAlliances.this.getW() - Menu_InGame_MilitaryAlliances.this.getElementW() * 6;
               }

               @Override
               public void buildElementHover() {
                  try {
                     ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
                     ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Formation") + ": "));
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(this.getText(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                     this.menuElementHover = new MenuElement_Hover_v2(nElements);
                  } catch (IndexOutOfBoundsException var3) {
                  }
               }
            }
         );
         tPosY += tElemHeight2;
      }

      this.initMenu(
         new SliderMenuTitle(CFG.langManager.get("MilitaryAlliances"), CFG.BUTTON_HEIGHT * 3 / 5, true, true) {
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
               oSB.setColor(new Color(0.003921569F, 0.12941177F, 0.4117647F, 0.225F));
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
               oSB.setColor(new Color(0.003921569F, 0.12941177F, 0.4117647F, 0.375F));
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
               ImageManager.getImage(Images.diplo_alliance)
                  .draw(
                     oSB,
                     nPosX
                        + (int)(nWidth - this.getTextWidth() * 0.8F) / 2
                        - CFG.PADDING
                        - ImageManager.getImage(Images.diplo_alliance).getWidth()
                        + iTranslateX,
                     2 + nPosY - this.getHeight() + this.getHeight() / 2 - ImageManager.getImage(Images.diplo_alliance).getHeight() / 2
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
         CFG.CIV_INFO_MENU_WIDTH + CFG.PADDING * 2,
         ImageManager.getImage(Images.top_flag_frame).getHeight() + CFG.PADDING * 4 + CFG.BUTTON_HEIGHT * 3 / 5,
         tempWidth,
         tempHeight,
         menuElements,
         false,
         true
      );
      this.updateLanguage();

      for (int var24 = 0; var24 < this.getMenuElementsSize(); var24++) {
         this.getMenuElement(var24).setCurrent(var24 / 6 % 2);
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
            if (sortBy != iID) {
               sortBy = iID;
               CFG.menuManager.rebuildInGame_MilitaryAlliances();
            }

            return;
         case 1:
            if (sortBy != iID) {
               sortBy = iID;
               CFG.menuManager.rebuildInGame_MilitaryAlliances();
            }

            return;
         case 2:
            if (sortBy != iID) {
               sortBy = iID;
               CFG.menuManager.rebuildInGame_MilitaryAlliances();
            }

            return;
         case 3:
            if (sortBy != iID) {
               sortBy = iID;
               CFG.menuManager.rebuildInGame_MilitaryAlliances();
            }

            return;
         case 4:
            if (sortBy != iID) {
               sortBy = iID;
               CFG.menuManager.rebuildInGame_MilitaryAlliances();
            }

            return;
         case 5:
            if (sortBy != iID) {
               sortBy = iID;
               CFG.menuManager.rebuildInGame_MilitaryAlliances();
            }

            return;
         default:
            if (iID % 6 == 0 || iID % 6 == 1) {
               CFG.menuManager.rebuildInGame_Alliance(this.lSorted.get(iID / 6 - 1));
            } else if (iID % 6 == 4
               && (
                  CFG.FOG_OF_WAR != 2
                     || CFG.game
                        .getPlayer(CFG.PLAYER_TURNID)
                        .getMetProvince(CFG.game.getCiv(CFG.game.getAlliance(this.lSorted.get(iID / 6 - 1)).getCivilization(0)).getCapitalProvinceID())
               )) {
               CFG.game.setActiveProvinceID(CFG.game.getCiv(CFG.game.getAlliance(this.lSorted.get(iID / 6 - 1)).getCivilization(0)).getCapitalProvinceID());
               CFG.map.getMapCoordinates().centerToProvinceID(CFG.game.getActiveProvinceID());
            }
      }
   }

   public final int getW() {
      return this.getWidth() - CFG.PADDING * 4;
   }

   public final int getElementW() {
      return this.getW() / 7;
   }
}
