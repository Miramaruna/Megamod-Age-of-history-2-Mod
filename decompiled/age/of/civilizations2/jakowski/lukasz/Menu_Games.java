package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;
import java.util.Random;

public class Menu_Games extends SliderMenu {
   public Menu_Games() {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      int tempMenuWidth = Menu_Games_Title.getMenuWidth();
      int tY = 0;
      menuElements.add(
         new Text(null, -1, 0, tY, tempMenuWidth, CFG.BUTTON_HEIGHT * 3 / 4) {
            @Override
            public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
               CFG.drawRect_InfoBox_Right_Title(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight());
               CFG.fontMain.getData().setScale(0.8F);
               CFG.drawTextWithShadow(
                  oSB,
                  this.getText(),
                  this.getPosX() + this.getWidth() / 2 - (int)(this.getTextWidth() * 0.8F) / 2 + iTranslateX,
                  this.getPosY() + this.getHeight() / 2 - (int)(this.getTextHeight() * 0.8F) / 2 + iTranslateY,
                  CFG.COLOR_TEXT_CIV_INFO_TITLE
               );
               CFG.fontMain.getData().setScale(1.0F);
            }
         }
      );
      int var4;
      menuElements.add(
         new Button_Menu_Descripted(
            CFG.map.getMapAuthor(CFG.map.getActiveMapID()),
            CFG.langManager.get("MapType") + ": " + CFG.map.getMapName(CFG.map.getActiveMapID()),
            (int)(50.0F * CFG.GUI_SCALE),
            0,
            var4 = tY + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING,
            tempMenuWidth,
            CFG.BUTTON_HEIGHT,
            true
         ) {
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
               this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
         }
      );
      menuElements.add(
         new Button_Menu_LR_MainMenu_TextScale(
            null, -1, 0, tY = var4 + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING, tempMenuWidth, CFG.BUTTON_HEIGHT, true
         )
      );
      int var6;
      menuElements.add(
         new Button_Menu_LR_MainMenu_TextScale(
            null, -1, 0, var6 = tY + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING, tempMenuWidth, CFG.BUTTON_HEIGHT, true
         ) {
            @Override
            public boolean getClickable() {
               return SaveManager.gameCanBeContinued;
            }
         }
      );
      menuElements.add(
         new Button_Menu_LR_MainMenuNew_TextScale(
            null, -1, 0, tY = var6 + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING, tempMenuWidth, CFG.BUTTON_HEIGHT, true
         ) {
            @Override
            public void buildElementHover() {
               try {
                  ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
                  ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
                  nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("FreePlay"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                  nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_diplomacy_points, CFG.PADDING, 0));
                  nElements.add(new MenuElement_Hover_v2_Element2(nData));
                  nData.clear();
                  this.menuElementHover = new MenuElement_Hover_v2(nElements);
               } catch (IndexOutOfBoundsException var3) {
                  this.menuElementHover = null;
               }
            }

            @Override
            public int getSFX() {
               return SoundsManager.SOUND_RANDOM;
            }
         }
      );
      int var8;
      menuElements.add(
         new Button_Menu_LR_MainMenu_TextScale_Important(
            null, -1, 0, var8 = tY + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING, tempMenuWidth, CFG.BUTTON_HEIGHT, true
         ) {
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
         new Button_Menu_LR_MainMenu_TextScale(
            null, -1, 0, tY = var8 + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING, tempMenuWidth, CFG.BUTTON_HEIGHT, true
         ) {
            @Override
            public void buildElementHover() {
               try {
                  ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
                  ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
                  nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("RandomGame"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                  nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.dice, CFG.PADDING, 0));
                  nElements.add(new MenuElement_Hover_v2_Element2(nData));
                  nData.clear();
                  this.menuElementHover = new MenuElement_Hover_v2(nElements);
               } catch (IndexOutOfBoundsException var3) {
                  this.menuElementHover = null;
               }
            }

            @Override
            public int getSFX() {
               return SoundsManager.SOUND_RANDOM;
            }
         }
      );
      int var10;
      menuElements.add(
         new Button_Menu_LR_MainMenu_TextScale_Important(
            null, -1, 0, var10 = tY + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING, tempMenuWidth, CFG.BUTTON_HEIGHT, true
         ) {}
      );
      menuElements.add(
         new Button_Menu_LR_MainMenu_TextScale(
            null, -1, 0, tY = var10 + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING, tempMenuWidth, CFG.BUTTON_HEIGHT, true
         )
      );
      int var12;
      menuElements.add(
         new Button_Menu_LR_MainMenu_TextScale(
            null, -1, 0, var12 = tY + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING, tempMenuWidth, CFG.BUTTON_HEIGHT, false
         )
      );
      menuElements.add(
         new Button_Menu_LR_MainMenu_TextScale(
            null, -1, 0, tY = var12 + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING, tempMenuWidth, CFG.BUTTON_HEIGHT, false
         )
      );
      int var14;
      menuElements.add(
         new Button_Menu_LR_MainMenu_TextScale(
            null, -1, 0, var14 = tY + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING, tempMenuWidth, CFG.BUTTON_HEIGHT, false
         )
      );
      tY = var14 + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
      this.initMenu(null, CFG.GAME_WIDTH - tempMenuWidth, 0, tempMenuWidth, CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING, menuElements);
      this.updateLanguage();
      CFG.lCreateScenario_UndoAssignProvincesCivID = new ArrayList<>();
      CFG.lCreateScenario_UndoWastelandProvinces = new ArrayList<>();
   }

   @Override
   public void updateLanguage() {
      this.getMenuElement(0).setText(CFG.langManager.get("Games"));
      this.getMenuElement(2).setText(CFG.langManager.get("LoadGame"));
      this.getMenuElement(3).setText(CFG.langManager.get("ContinueGame"));
      this.getMenuElement(4).setText(CFG.langManager.get("FreePlay"));
      this.getMenuElement(5).setText(CFG.langManager.get("NewGame"));
      this.getMenuElement(6).setText(CFG.langManager.get("AgeofCivilizations"));
      this.getMenuElement(7).setText(CFG.langManager.get("Tutorial"));
      this.getMenuElement(8).setText(CFG.langManager.get("Achievements"));
      this.getMenuElement(9).setText(CFG.langManager.get("HallofFame"));
      this.getMenuElement(10).setText(CFG.langManager.get("Leaderboards"));
      this.getMenuElement(11).setText(CFG.langManager.get("Statistics"));
   }

   @Override
   public void drawScrollPos(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      if (sliderMenuIsActive) {
         super.drawScrollPos(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
      }
   }

   @Override
   public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      super.beginClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
      super.drawMenu(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
      CFG.map
         .getIcon(CFG.map.getActiveMapID())
         .draw(
            oSB,
            this.getPosX() + this.getMenuElement(1).getTextPos() / 2 - CFG.map.getIcon(CFG.map.getActiveMapID()).getWidth() / 2 + iTranslateX,
            this.getMenuElement(1).getPosY()
               + this.getMenuElement(1).getHeight() / 2
               - CFG.CIV_FLAG_HEIGHT / 2
               + this.getMenuPosY()
               - CFG.map.getIcon(CFG.map.getActiveMapID()).getHeight()
               + iTranslateY,
            CFG.CIV_FLAG_WIDTH,
            CFG.CIV_FLAG_HEIGHT
         );
      super.endClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
      CFG.setRender_3(true);
      if ((Map.GAME_CRASHED_LOADED_MIN_SCALE || CFG.map.getMapBG().getMapScale() <= 1) && CFG.map.getMapBG().getMapScale() == 1 && !CFG.toast.getInView()) {
         ArrayList<String> nMess = new ArrayList<>();
         ArrayList<Color> nCol = new ArrayList<>();
         nMess.add("Game crashed while loading");
         nCol.add(CFG.COLOR_TEXT_MODIFIER_NEGATIVE2);
         nMess.add("-- Loaded minimum scale of map --");
         nCol.add(CFG.COLOR_TEXT_MODIFIER_NEGATIVE2);
         nMess.add("Go to: Map: XX -> Earth: -> Scale X5");
         nCol.add(Color.WHITE);
         CFG.toast.setInView(nMess, nCol);
         CFG.toast.setTimeInView(6000);
      }
   }

   public static final void clickNewGame() {
      if (CFG.SPECTATOR_MODE) {
         CFG.SPECTATOR_MODE = false;
         CFG.FOG_OF_WAR = 2;
      }

      CFG.SANDBOX_MODE = false;
      CFG.RANDOM_PLACMENT = false;
      CFG.RANDOM_FILL = false;
      Game_Calendar.GAME_SPEED = 1.0F;
      Game_Calendar.AI_AGGRESSIVNESS = 0.5F;
      CFG.menuManager.setViewID(Menu.eCREATE_NEW_GAME);
      CFG.menuManager.setVisible_CreateNewGame_Options(false);
      CFG.menuManager.setVisible_CreateNewGame_CivInfo(true);
   }

   public static final void clickRandomGame() {
      CFG.randomGameManager = new RandomGame_Manager();
      Game_Calendar.GAME_SPEED = 1.0F;
      CFG.RANDOM_PLACMENT = false;
      CFG.RANDOM_FILL = false;
      CFG.TOTAL_WAR_MODE = false;
      Game_Calendar.currentYear = -4000 - CFG.oR.nextInt(1000);
      Game_Calendar.currentDay = 15;
      Game_Calendar.currentMonth = 5;
      Game_Calendar.CURRENT_AGEID = CFG.gameAges.getAgeOfYear(Game_Calendar.currentYear);
      Game_Calendar.ENABLE_COLONIZATION = false;
      Game_Calendar.ENABLE_COLONIZATION_NEUTRAL_PROVINCES = false;
      CFG.FOG_OF_WAR = 2;

      for (int i = 0; i < CFG.game.getProvincesSize(); i++) {
         if (!CFG.game.getProvince(i).getSeaProvince()) {
            CFG.game.getProvince(i).setWasteland(-1);
         }
      }

      Random oR = new Random();

      try {
         CFG.randomGameManager
            .setCivilizationsSize(
               Math.min(
                  (int)Math.max(Menu_RandomGame_Settings.getCivMax() * 0.1F, 2.0F) + oR.nextInt((int)(Menu_RandomGame_Settings.getCivMax() * 0.1F)),
                  Menu_RandomGame_Settings.getCivMax()
               )
            );
      } catch (IllegalArgumentException var2) {
         CFG.randomGameManager.setCivilizationsSize(3);
      }

      CFG.game.getGameScenarios().setScenario_StartingArmyInCapitals(75);
      CFG.randomGameManager.setNeutralArmy(30);
      CFG.game.getGameScenarios().setScenario_StartingPopulation(21000);
      CFG.game.getGameScenarios().setScenario_StartingEconomy(9750);
      CFG.game.getGameScenarios().setScenario_StartingMoney(50);
      CFG.menuManager.setViewID(Menu.eCREATE_RANDOM_GAME);
      CFG.menuManager.rebuildCreateRandomGame_Settings();
   }

   @Override
   public final void actionElement(int iID) {
      switch (iID) {
         case 1:
            CFG.backToMenu = Menu.eGAMES;
            CFG.menuManager.setViewID(Menu.eSELECT_MAP_TYPE);
            break;
         case 2:
            CFG.menuManager.setViewID(Menu.eLOADGAME);
            CFG.menuManager.setOrderOfMenu_LoadGame();
            break;
         case 3:
            CFG.menuManager.setViewID(Menu.eINGAME);
            CFG.menuManager.setVisible_InGame_Options(false);

            try {
               if (!CFG.SPECTATOR_MODE && CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCapitalProvinceID() >= 0) {
                  CFG.map.getMapCoordinates().centerToProvinceID(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCapitalProvinceID());
                  CFG.game.setActiveProvinceID(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCapitalProvinceID());
               }
            } catch (IndexOutOfBoundsException var3) {
            }
            break;
         case 4:
            CFG.MANPOWER_SYSTEM = true;
            clickNewGame();
            CFG.menuManager.setVisible_CreateNewGame_Options_Scenarios(true);
            CFG.FREEPLAY_MODE = true;
            break;
         case 5:
            if (SaveManager.gameCanBeContinued) {
               CFG.setDialogType(Dialog.ALL_NOT_SAVED_PROGRESS_WILL_BE_LOST);
            } else {
               CFG.FREEPLAY_MODE = false;
               CFG.MANPOWER_SYSTEM = true;
               clickNewGame();
            }
            break;
         case 6:
            if (SaveManager.gameCanBeContinued) {
               CFG.setDialogType(Dialog.ALL_NOT_SAVED_PROGRESS_WILL_BE_LOST2);
            } else {
               clickRandomGame();
            }
            break;
         case 7:
            CFG.setDialogType(Dialog.START_TUTORIAL);
            break;
         case 8:
            CFG.menuManager.setViewID(Menu.eACHIEVEMENTS);
      }
   }

   @Override
   public void onHovered() {
      CFG.menuManager.setOrderOfMenu_Games();
   }
}
