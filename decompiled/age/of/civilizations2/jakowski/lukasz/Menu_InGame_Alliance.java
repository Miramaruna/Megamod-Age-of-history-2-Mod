package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.utils.ScissorStack;
import java.util.ArrayList;

public class Menu_InGame_Alliance extends SliderMenu {
   public static final int ANIMATION_TIME = 175;
   public long lTime = 0L;
   public static int ALLIANCE_ID = 0;
   public static int sortBy = 0;
   public String sFormDate = "";
   public int iFormDateWidth;
   public static final float FONT_SCALE = 0.55F;

   public Menu_InGame_Alliance() {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      int tempWidth = CFG.CIV_INFO_MENU_WIDTH * 2 + CFG.CIV_INFO_MENU_WIDTH / 4;
      int tempHeight = CFG.BUTTON_HEIGHT + CFG.BUTTON_HEIGHT * 3 / 4;
      int tElemHeight = CFG.TEXT_HEIGHT + CFG.PADDING * 4;
      int tElemHeight2 = CFG.isAndroid() ? CFG.TEXT_HEIGHT + CFG.PADDING * 4 : CFG.TEXT_HEIGHT + CFG.PADDING * 4;
      this.sFormDate = Game_Calendar.getDate_ByTurnID(CFG.game.getAlliance(ALLIANCE_ID).getFormationTurnID());
      CFG.glyphLayout.setText(CFG.fontMain, this.sFormDate);
      this.iFormDateWidth = (int)(CFG.glyphLayout.width * 0.55F);
      menuElements.add(new Button_Statistics_Title(CFG.langManager.get("Members"), CFG.PADDING * 2, 2, 0, CFG.BUTTON_WIDTH * 2, tElemHeight) {
         @Override
         public int getWidth() {
            return Menu_InGame_Alliance.this.getElementW() * 2 + CFG.PADDING * 2 - 2;
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
            return Menu_InGame_Alliance.sortBy == 0 ? CFG.COLOR_TEXT_NUM_OF_PROVINCES : super.getColor(isActive);
         }
      });
      menuElements.add(
         new Button_Statistics_Title(CFG.langManager.get("Provinces"), CFG.PADDING, CFG.PADDING * 2 + CFG.BUTTON_WIDTH * 2, 0, CFG.BUTTON_WIDTH, tElemHeight) {
            @Override
            public int getPosX() {
               return Menu_InGame_Alliance.this.getElementW() * 2 + CFG.PADDING * 2;
            }

            @Override
            public int getWidth() {
               return Menu_InGame_Alliance.this.getElementW();
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
               return Menu_InGame_Alliance.sortBy == 1 ? CFG.COLOR_TEXT_NUM_OF_PROVINCES : super.getColor(isActive);
            }
         }
      );
      menuElements.add(
         new Button_Statistics_Title(CFG.langManager.get("Population"), CFG.PADDING, CFG.PADDING * 2 + CFG.BUTTON_WIDTH * 3, 0, CFG.BUTTON_WIDTH, tElemHeight) {
            @Override
            public int getPosX() {
               return Menu_InGame_Alliance.this.getElementW() * 3 + CFG.PADDING * 2;
            }

            @Override
            public int getWidth() {
               return Menu_InGame_Alliance.this.getElementW();
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
               return Menu_InGame_Alliance.sortBy == 2 ? CFG.COLOR_TEXT_NUM_OF_PROVINCES : super.getColor(isActive);
            }
         }
      );
      menuElements.add(
         new Button_Statistics_Title(CFG.langManager.get("Economy"), CFG.PADDING, CFG.PADDING * 2 + CFG.BUTTON_WIDTH * 4, 0, CFG.BUTTON_WIDTH, tElemHeight) {
            @Override
            public int getPosX() {
               return Menu_InGame_Alliance.this.getElementW() * 4 + CFG.PADDING * 2;
            }

            @Override
            public int getWidth() {
               return Menu_InGame_Alliance.this.getElementW();
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
               return Menu_InGame_Alliance.sortBy == 3 ? CFG.COLOR_TEXT_NUM_OF_PROVINCES : super.getColor(isActive);
            }
         }
      );
      menuElements.add(
         new Button_Statistics_Title(CFG.langManager.get("Technology"), CFG.PADDING, CFG.PADDING * 2 + CFG.BUTTON_WIDTH * 5, 0, CFG.BUTTON_WIDTH, tElemHeight) {
            @Override
            public int getPosX() {
               return Menu_InGame_Alliance.this.getElementW() * 5 + CFG.PADDING * 2;
            }

            @Override
            public int getWidth() {
               return Menu_InGame_Alliance.this.getW() - Menu_InGame_Alliance.this.getElementW() * 5 + CFG.PADDING * 2 - 2;
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
               return Menu_InGame_Alliance.sortBy == 4 ? CFG.COLOR_TEXT_NUM_OF_PROVINCES : super.getColor(isActive);
            }
         }
      );
      int tPosY = CFG.PADDING + tElemHeight;
      ArrayList tCivsSorted = new ArrayList();
      if (sortBy == 1) {
         ArrayList<Integer> tempL = new ArrayList<>();

         for (int i3 = 0; i3 < CFG.game.getAlliance(ALLIANCE_ID).getCivilizationsSize(); i3++) {
            tempL.add(CFG.game.getAlliance(ALLIANCE_ID).getCivilization(i3));
         }

         while (tempL.size() > 0) {
            int tAddID = 0;

            for (int i2 = 1; i2 < tempL.size(); i2++) {
               if (CFG.game.getCiv(tempL.get(tAddID)).getNumOfProvinces() < CFG.game.getCiv(tempL.get(i2)).getNumOfProvinces()) {
                  tAddID = i2;
               }
            }

            tCivsSorted.add(tempL.get(tAddID));
            tempL.remove(tAddID);
         }
      } else if (sortBy == 2) {
         ArrayList<Integer> tempL = new ArrayList<>();

         for (int i3 = 0; i3 < CFG.game.getAlliance(ALLIANCE_ID).getCivilizationsSize(); i3++) {
            tempL.add(CFG.game.getAlliance(ALLIANCE_ID).getCivilization(i3));
         }

         while (tempL.size() > 0) {
            int tAddID = 0;

            for (int i2x = 1; i2x < tempL.size(); i2x++) {
               if (CFG.game.getCiv(tempL.get(tAddID)).countPopulation() < CFG.game.getCiv(tempL.get(i2x)).countPopulation()) {
                  tAddID = i2x;
               }
            }

            tCivsSorted.add(tempL.get(tAddID));
            tempL.remove(tAddID);
         }
      } else if (sortBy == 3) {
         ArrayList<Integer> tempL = new ArrayList<>();

         for (int i3 = 0; i3 < CFG.game.getAlliance(ALLIANCE_ID).getCivilizationsSize(); i3++) {
            tempL.add(CFG.game.getAlliance(ALLIANCE_ID).getCivilization(i3));
         }

         while (tempL.size() > 0) {
            int tAddID = 0;

            for (int i2xx = 1; i2xx < tempL.size(); i2xx++) {
               if (CFG.game.getCiv(tempL.get(tAddID)).countEconomy() < CFG.game.getCiv(tempL.get(i2xx)).countEconomy()) {
                  tAddID = i2xx;
               }
            }

            tCivsSorted.add(tempL.get(tAddID));
            tempL.remove(tAddID);
         }
      } else if (sortBy == 4) {
         ArrayList<Integer> tempL = new ArrayList<>();

         for (int i3 = 0; i3 < CFG.game.getAlliance(ALLIANCE_ID).getCivilizationsSize(); i3++) {
            tempL.add(CFG.game.getAlliance(ALLIANCE_ID).getCivilization(i3));
         }

         while (tempL.size() > 0) {
            int tAddID = 0;

            for (int i2xxx = 1; i2xxx < tempL.size(); i2xxx++) {
               if (CFG.game.getCiv(tempL.get(tAddID)).getTechnologyLevel() < CFG.game.getCiv(tempL.get(i2xxx)).getTechnologyLevel()) {
                  tAddID = i2xxx;
               }
            }

            tCivsSorted.add(tempL.get(tAddID));
            tempL.remove(tAddID);
         }
      } else {
         for (int i4 = 0; i4 < CFG.game.getAlliance(ALLIANCE_ID).getCivilizationsSize(); i4++) {
            tCivsSorted.add(CFG.game.getAlliance(ALLIANCE_ID).getCivilization(i4));
         }
      }

      for (int i5 = 0; i5 < tCivsSorted.size(); i5++) {
         menuElements.add(
            new Button_Statistics_Flag_Clip(
               CFG.FOG_OF_WAR == 2 && !CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetCivilization((Integer)tCivsSorted.get(i5))
                  ? -1
                  : (Integer)tCivsSorted.get(i5),
               CFG.FOG_OF_WAR == 2 && !CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetCivilization((Integer)tCivsSorted.get(i5))
                  ? CFG.langManager.get("Undiscovered")
                  : CFG.game.getCiv((Integer)tCivsSorted.get(i5)).getCivName(),
               CFG.PADDING,
               CFG.PADDING * 2,
               tPosY,
               CFG.BUTTON_WIDTH * 2,
               tElemHeight2
            ) {
               @Override
               public int getWidth() {
                  return Menu_InGame_Alliance.this.getElementW() * 2;
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
               CFG.getNumberWithSpaces("" + CFG.game.getCiv((Integer)tCivsSorted.get(i5)).getNumOfProvinces()),
               CFG.PADDING,
               CFG.PADDING * 2 + CFG.BUTTON_WIDTH * 2,
               tPosY,
               CFG.BUTTON_WIDTH,
               tElemHeight2
            ) {
               @Override
               public int getPosX() {
                  return Menu_InGame_Alliance.this.getElementW() * 2 + CFG.PADDING * 2;
               }

               @Override
               public int getWidth() {
                  return Menu_InGame_Alliance.this.getElementW();
               }
            }
         );
         menuElements.add(
            new Button_Statistics(
               CFG.getNumberWithSpaces("" + CFG.game.getCiv((Integer)tCivsSorted.get(i5)).countPopulation()),
               CFG.PADDING,
               CFG.PADDING * 2 + CFG.BUTTON_WIDTH * 3,
               tPosY,
               CFG.BUTTON_WIDTH,
               tElemHeight2
            ) {
               @Override
               public int getPosX() {
                  return Menu_InGame_Alliance.this.getElementW() * 3 + CFG.PADDING * 2;
               }

               @Override
               public int getWidth() {
                  return Menu_InGame_Alliance.this.getElementW();
               }
            }
         );
         menuElements.add(
            new Button_Statistics(
               CFG.getNumberWithSpaces("" + CFG.game.getCiv((Integer)tCivsSorted.get(i5)).countEconomy()),
               CFG.PADDING,
               CFG.PADDING * 2 + CFG.BUTTON_WIDTH * 4,
               tPosY,
               CFG.BUTTON_WIDTH,
               tElemHeight2
            ) {
               @Override
               public int getPosX() {
                  return Menu_InGame_Alliance.this.getElementW() * 4 + CFG.PADDING * 2;
               }

               @Override
               public int getWidth() {
                  return Menu_InGame_Alliance.this.getElementW();
               }
            }
         );
         menuElements.add(
            new Button_Statistics(
               "" + (int)(CFG.game.getCiv((Integer)tCivsSorted.get(i5)).getTechnologyLevel() * 100.0F) / 100.0F,
               CFG.PADDING,
               CFG.PADDING * 2 + CFG.BUTTON_WIDTH * 5,
               tPosY,
               CFG.BUTTON_WIDTH,
               tElemHeight2
            ) {
               @Override
               public int getPosX() {
                  return Menu_InGame_Alliance.this.getElementW() * 5 + CFG.PADDING * 2;
               }

               @Override
               public int getWidth() {
                  return Menu_InGame_Alliance.this.getW() - Menu_InGame_Alliance.this.getElementW() * 5;
               }
            }
         );
         tPosY += tElemHeight2;
      }

      int tempTotalProvinces = 0;
      int tempTotalPopulation = 0;
      int tempTotalEconomy = 0;
      float tempTotalArmy = 0.0F;

      for (int i = 0; i < tCivsSorted.size(); i++) {
         tempTotalProvinces += CFG.game.getCiv((Integer)tCivsSorted.get(i)).getNumOfProvinces();
         tempTotalPopulation = (int)(tempTotalPopulation + CFG.game.getCiv((Integer)tCivsSorted.get(i)).countPopulation());
         tempTotalEconomy += CFG.game.getCiv((Integer)tCivsSorted.get(i)).countEconomy();
         tempTotalArmy += CFG.game.getCiv((Integer)tCivsSorted.get(i)).getTechnologyLevel();
      }

      menuElements.add(
         new Button_Statistics_Total(CFG.langManager.get("Total") + ":", CFG.PADDING, CFG.PADDING * 2, tPosY, CFG.BUTTON_WIDTH * 2, tElemHeight2) {
            @Override
            public int getWidth() {
               return Menu_InGame_Alliance.this.getElementW() * 2;
            }

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
            public void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
               CFG.fontMain.getData().setScale(0.6F);
               CFG.drawTextWithShadow(
                  oSB,
                  this.getTextToDraw(),
                  this.getPosX() + this.getWidth() - CFG.PADDING * 2 - (int)(this.iTextWidth * 0.6F) + iTranslateX,
                  this.getPosY() + this.getHeight() / 2 - (int)(this.iTextHeight * 0.6F / 2.0F) + iTranslateY,
                  this.getColor(isActive)
               );
               CFG.fontMain.getData().setScale(1.0F);
            }
         }
      );
      menuElements.add(
         new Button_Statistics_Total(
            CFG.getNumberWithSpaces("" + tempTotalProvinces), CFG.PADDING, CFG.PADDING * 2 + CFG.BUTTON_WIDTH * 2, tPosY, CFG.BUTTON_WIDTH, tElemHeight2
         ) {
            @Override
            public int getPosX() {
               return Menu_InGame_Alliance.this.getElementW() * 2 + CFG.PADDING * 2;
            }

            @Override
            public int getWidth() {
               return Menu_InGame_Alliance.this.getElementW();
            }
         }
      );
      menuElements.add(
         new Button_Statistics_Total(
            CFG.getNumberWithSpaces("" + tempTotalPopulation), CFG.PADDING, CFG.PADDING * 2 + CFG.BUTTON_WIDTH * 3, tPosY, CFG.BUTTON_WIDTH, tElemHeight2
         ) {
            @Override
            public int getPosX() {
               return Menu_InGame_Alliance.this.getElementW() * 3 + CFG.PADDING * 2;
            }

            @Override
            public int getWidth() {
               return Menu_InGame_Alliance.this.getElementW();
            }
         }
      );
      menuElements.add(
         new Button_Statistics_Total(
            CFG.getNumberWithSpaces("" + tempTotalEconomy), CFG.PADDING, CFG.PADDING * 2 + CFG.BUTTON_WIDTH * 4, tPosY, CFG.BUTTON_WIDTH, tElemHeight2
         ) {
            @Override
            public int getPosX() {
               return Menu_InGame_Alliance.this.getElementW() * 4 + CFG.PADDING * 2;
            }

            @Override
            public int getWidth() {
               return Menu_InGame_Alliance.this.getElementW();
            }
         }
      );
      menuElements.add(
         new Button_Statistics_Total(
            "" + (int)(tempTotalArmy / tCivsSorted.size() * 100.0F) / 100.0F,
            CFG.PADDING,
            CFG.PADDING * 2 + CFG.BUTTON_WIDTH * 5,
            tPosY,
            CFG.BUTTON_WIDTH,
            tElemHeight2
         ) {
            @Override
            public int getPosX() {
               return Menu_InGame_Alliance.this.getElementW() * 5 + CFG.PADDING * 2;
            }

            @Override
            public int getWidth() {
               return Menu_InGame_Alliance.this.getW() - Menu_InGame_Alliance.this.getElementW() * 5;
            }
         }
      );
      int var26;
      menuElements.add(
         new Button_FlagActionSliderStyle(
            CFG.langManager.get("ChangeName"),
            -1,
            CFG.PADDING * 2,
            var26 = tPosY + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING * 2,
            CFG.BUTTON_WIDTH,
            true
         ) {
            @Override
            public int getWidth() {
               return (Menu_InGame_Alliance.this.getW() - CFG.PADDING * 2) / 2;
            }

            @Override
            public Color getColor(boolean isActive) {
               return this.getClickable()
                  ? (
                     1 == CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).civGameData.LevelMilitarySpending
                        ? CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                        : (this.getIsHovered() ? CFG.COLOR_TEXT_CIV_INFO_HOVER : CFG.COLOR_TEXT_CIV_INFO)
                  )
                  : new Color(CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.r, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.g, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.b, 0.65F);
            }
         }
      );
      menuElements.add(
         new Button_FlagActionSliderStyle(CFG.langManager.get("ChangeColor"), -1, CFG.PADDING, var26, CFG.BUTTON_WIDTH, true) {
            @Override
            public int getWidth() {
               return (Menu_InGame_Alliance.this.getW() - CFG.PADDING * 2) / 2;
            }

            @Override
            public int getPosX() {
               return CFG.PADDING * 2 + Menu_InGame_Alliance.this.getW() / 2;
            }

            @Override
            public Color getColor(boolean isActive) {
               return this.getClickable()
                  ? (
                     1 == CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).civGameData.LevelMilitarySpending
                        ? CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                        : (this.getIsHovered() ? CFG.COLOR_TEXT_CIV_INFO_HOVER : CFG.COLOR_TEXT_CIV_INFO)
                  )
                  : new Color(CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.r, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.g, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.b, 0.65F);
            }
         }
      );
      menuElements.add(
         new Text_EconomyTitle(
            CFG.langManager.get(""), -1, 0, tPosY = var26 + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING * 2, 0, CFG.PADDING / 2
         )
      );
      this.initMenu(
         new SliderMenuTitle(
            CFG.FOG_OF_WAR == 2 && !CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetAlliance(ALLIANCE_ID)
               ? CFG.langManager.get("Undiscovered")
               : CFG.game.getAlliance(ALLIANCE_ID).getAllianceName(),
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
               oSB.setColor(
                  new Color(
                     CFG.game.getAlliance(Menu_InGame_Alliance.ALLIANCE_ID).getColorOfAlliance().getR(),
                     CFG.game.getAlliance(Menu_InGame_Alliance.ALLIANCE_ID).getColorOfAlliance().getG(),
                     CFG.game.getAlliance(Menu_InGame_Alliance.ALLIANCE_ID).getColorOfAlliance().getB(),
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
                     CFG.game.getAlliance(Menu_InGame_Alliance.ALLIANCE_ID).getColorOfAlliance().getR(),
                     CFG.game.getAlliance(Menu_InGame_Alliance.ALLIANCE_ID).getColorOfAlliance().getG(),
                     CFG.game.getAlliance(Menu_InGame_Alliance.ALLIANCE_ID).getColorOfAlliance().getB(),
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
               ImageManager.getImage(Images.time)
                  .draw(
                     oSB,
                     nPosX
                        + nWidth
                        - CFG.PADDING
                        - 2
                        - (int)(ImageManager.getImage(Images.time).getWidth() * Menu_InGame_Alliance.this.getImageScale2(Images.time))
                        + iTranslateX,
                     nPosY
                        - CFG.PADDING
                        - (int)(ImageManager.getImage(Images.time).getHeight() * Menu_InGame_Alliance.this.getImageScale2(Images.time))
                        - ImageManager.getImage(Images.time).getHeight(),
                     (int)(ImageManager.getImage(Images.time).getWidth() * Menu_InGame_Alliance.this.getImageScale2(Images.time)),
                     (int)(ImageManager.getImage(Images.time).getHeight() * Menu_InGame_Alliance.this.getImageScale2(Images.time))
                  );
               CFG.fontMain.getData().setScale(0.55F);
               CFG.drawText(
                  oSB,
                  Menu_InGame_Alliance.this.sFormDate,
                  nPosX
                     + nWidth
                     - Menu_InGame_Alliance.this.iFormDateWidth
                     - CFG.PADDING * 2
                     - (int)(ImageManager.getImage(Images.time).getWidth() * Menu_InGame_Alliance.this.getImageScale2(Images.time))
                     - 2
                     + iTranslateX,
                  nPosY - CFG.PADDING - (int)(CFG.TEXT_HEIGHT * 0.55F),
                  CFG.COLOR_TEXT_MODIFIER_NEUTRAL
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

      for (int var16 = 0; var16 < this.getMenuElementsSize(); var16++) {
         this.getMenuElement(var16).setCurrent(var16 / 5 % 2);
      }
   }

   @Override
   public void updateLanguage() {
   }

   public final float getImageScale2(int nImageID) {
      return CFG.TEXT_HEIGHT * 0.55F / ImageManager.getImage(nImageID).getHeight() < 1.0F
         ? CFG.TEXT_HEIGHT * 0.55F / ImageManager.getImage(nImageID).getHeight()
         : 1.0F;
   }

   @Override
   public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      if (this.lTime + 175L >= System.currentTimeMillis()) {
         Rectangle clipBounds = new Rectangle(
            this.getPosX(),
            CFG.GAME_HEIGHT - this.getPosY(),
            this.getWidth(),
            -((int)(this.getHeight() * ((float)(System.currentTimeMillis() - this.lTime) / 175.0F)))
         );
         oSB.flush();
         ScissorStack.pushScissors(clipBounds);
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

         try {
            oSB.flush();
            ScissorStack.popScissors();
         } catch (IllegalStateException var7) {
         }

         this.endClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
         CFG.setRender_3(true);
      } else {
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
      if (iID == this.getMenuElementsSize() - 3) {
         Keyboard.menuElementAlTitle = this.getTitle();
         Keyboard.allianceID = ALLIANCE_ID;
         CFG.showKeyboard_RenameAlliance();
      } else if (iID == this.getMenuElementsSize() - 2) {
         CFG.menuManager.getColorPicker().setVisible(true, ColorPicker_AoC.PickerAction.CUSTOMIZE_ALLIANCE_COLOR);
      } else {
         switch (iID) {
            case 0:
               if (sortBy != iID) {
                  sortBy = iID;
                  CFG.menuManager.rebuildInGame_Alliance(ALLIANCE_ID);
               }

               return;
            case 1:
               if (sortBy != iID) {
                  sortBy = iID;
                  CFG.menuManager.rebuildInGame_Alliance(ALLIANCE_ID);
               }

               return;
            case 2:
               if (sortBy != iID) {
                  sortBy = iID;
                  CFG.menuManager.rebuildInGame_Alliance(ALLIANCE_ID);
               }

               return;
            case 3:
               if (sortBy != iID) {
                  sortBy = iID;
                  CFG.menuManager.rebuildInGame_Alliance(ALLIANCE_ID);
               }

               return;
            case 4:
               if (sortBy != iID) {
                  sortBy = iID;
                  CFG.menuManager.rebuildInGame_Alliance(ALLIANCE_ID);
               }

               return;
            default:
               if (iID % 5 == 0 && iID < this.getMenuElementsSize() - 5) {
                  try {
                     if (this.getMenuElement(iID).getCurrent() >= 0) {
                        CFG.setActiveCivInfo(this.getMenuElement(iID).getCurrent());
                        CFG.game.setActiveProvinceID(CFG.game.getCiv(CFG.getActiveCivInfo()).getCapitalProvinceID());
                        CFG.map.getMapCoordinates().centerToProvinceID(CFG.game.getActiveProvinceID());
                        CFG.updateActiveCivInfo_InGame();
                     }
                  } catch (IndexOutOfBoundsException var3) {
                  }
               }
         }
      }
   }

   public final int getW() {
      return this.getWidth() - CFG.PADDING * 4;
   }

   public final int getElementW() {
      return this.getW() / 6;
   }

   @Override
   public void setVisible(boolean visible) {
      super.setVisible(visible);
      this.lTime = System.currentTimeMillis();
   }
}
