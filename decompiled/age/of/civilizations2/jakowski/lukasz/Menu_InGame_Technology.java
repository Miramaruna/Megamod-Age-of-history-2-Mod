package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.utils.ScissorStack;
import java.util.ArrayList;

public class Menu_InGame_Technology extends SliderMenu {
   public int iCivID;
   public long lTime = 0L;

   public Menu_InGame_Technology() {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      int tempWidth = CFG.CIV_INFO_MENU_WIDTH * 2;
      int tY = CFG.PADDING;
      menuElements.add(new Button_Flag_JustFrame(CFG.PADDING, tY, true));
      tY += menuElements.get(menuElements.size() - 1).getHeight();
      int tempMenuPosY = ImageManager.getImage(Images.top_left2).getHeight() + CFG.PADDING * 2 + CFG.BUTTON_HEIGHT * 3 / 5;
      this.initMenu(
         new SliderMenuTitle(CFG.langManager.get("TechnologyPoints"), CFG.BUTTON_HEIGHT * 3 / 5, true, true),
         CFG.GAME_WIDTH / 2 - tempWidth * 3 / 8,
         tempMenuPosY,
         tempWidth,
         menuElements.get(menuElements.size() - 1).getPosY() + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING + tempMenuPosY
               > CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING * 2
            ? Math.max(CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING * 2 - tempMenuPosY, (CFG.TEXT_HEIGHT + CFG.PADDING * 2) * 6)
            : menuElements.get(menuElements.size() - 1).getPosY() + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING,
         menuElements,
         false,
         true
      );
      this.updateLanguage();
   }

   public Menu_InGame_Technology(int nCivID) {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      int tempWidth = CFG.CIV_INFO_MENU_WIDTH * 2;
      int tY = 0;
      this.iCivID = nCivID;
      menuElements.add(new Button_Technology(nCivID, 2, tY, CFG.BUTTON_WIDTH * 2) {
         @Override
         public int getWidth() {
            return Menu_InGame_Technology.this.getElementW() * 2;
         }
      });
      int var7;
      menuElements.add(new Button_Icon(Images.population_growth, 0, var7 = tY + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING));
      menuElements.add(
         new Slider_FlagAction_Clear_Tech(
            1.5F,
            CFG.langManager.get("PopulationGrowthModifier"),
            Button_Diplomacy.iDiploWidth + CFG.PADDING,
            var7,
            tempWidth - CFG.PADDING * 3 - CFG.BUTTON_WIDTH,
            CFG.TEXT_HEIGHT + CFG.PADDING * 6,
            0,
            120,
            CFG.game.getCiv(this.iCivID).civGameData.skills.POINTS_POP_GROWTH
         ) {
            @Override
            public int getWidth() {
               return Math.max(Menu_InGame_Technology.this.getElementW() * 2 - CFG.BUTTON_WIDTH * 3 / 4 - CFG.PADDING * 4 - Button_Diplomacy.iDiploWidth, 0);
            }

            @Override
            public Color getColorLEFT() {
               return new Color(CFG.COLOR_TEXT_POPULATION.r, CFG.COLOR_TEXT_POPULATION.g, CFG.COLOR_TEXT_POPULATION.b, 1.0F);
            }

            @Override
            public void actionElement(int iID) {
            }
         }
      );
      menuElements.add(
         new Button_FlagActionSliderStyle(
            "+", -1, tempWidth - CFG.BUTTON_WIDTH * 3 / 4 - CFG.PADDING, var7, CFG.BUTTON_WIDTH * 3 / 4, CFG.TEXT_HEIGHT + CFG.PADDING * 6, true
         ) {
            @Override
            public int getPosX() {
               return Menu_InGame_Technology.this.getElementW() * 2 - this.getWidth() - CFG.PADDING;
            }

            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("AddPoint"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
               nData.add(new MenuElement_Hover_v2_Element_Type_Flag(Menu_InGame_Technology.this.iCivID, CFG.PADDING, 0));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("PopulationGrowthModifier") + ": "));
               nData.add(new MenuElement_Hover_v2_Element_Type_Text("+1.5%", CFG.COLOR_TEXT_MODIFIER_POSITIVE));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }

            @Override
            public void actionElement(int iID) {
               SkillsManager.add_PopGrowth(Menu_InGame_Technology.this.iCivID);
               Menu_InGame_Technology.this.getMenuElement(0)
                  .setMin(CFG.game.getCiv(Menu_InGame_Technology.this.iCivID).civGameData.skills.getPointsLeft(Menu_InGame_Technology.this.iCivID));
               Menu_InGame_Technology.this.getMenuElement(iID - 1)
                  .setCurrent(CFG.game.getCiv(Menu_InGame_Technology.this.iCivID).civGameData.skills.POINTS_POP_GROWTH);
            }

            @Override
            public int getSFX() {
               return SoundsManager.SOUND_CLICK2;
            }
         }
      );
      menuElements.add(new Button_Icon(Images.economy, 0, tY = var7 + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING));
      menuElements.add(
         new Slider_FlagAction_Clear_Tech(
            1.5F,
            CFG.langManager.get("EconomyGrowthModifier"),
            CFG.PADDING + Button_Diplomacy.iDiploWidth,
            tY,
            tempWidth - CFG.PADDING * 3 - CFG.BUTTON_WIDTH,
            CFG.TEXT_HEIGHT + CFG.PADDING * 6,
            0,
            125,
            CFG.game.getCiv(this.iCivID).civGameData.skills.POINTS_ECONOMY_GROWTH
         ) {
            @Override
            public int getWidth() {
               return Math.max(Menu_InGame_Technology.this.getElementW() * 2 - CFG.BUTTON_WIDTH * 3 / 4 - CFG.PADDING * 4 - Button_Diplomacy.iDiploWidth, 0);
            }

            @Override
            public Color getColorLEFT() {
               return new Color(CFG.COLOR_TEXT_ECONOMY.r, CFG.COLOR_TEXT_ECONOMY.g, CFG.COLOR_TEXT_ECONOMY.b, 1.0F);
            }

            @Override
            public void actionElement(int iID) {
            }
         }
      );
      menuElements.add(
         new Button_FlagActionSliderStyle(
            "+", -1, tempWidth - CFG.BUTTON_WIDTH * 3 / 4 - CFG.PADDING, tY, CFG.BUTTON_WIDTH * 3 / 4, CFG.TEXT_HEIGHT + CFG.PADDING * 6, true
         ) {
            @Override
            public int getPosX() {
               return Menu_InGame_Technology.this.getElementW() * 2 - this.getWidth() - CFG.PADDING;
            }

            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("AddPoint"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
               nData.add(new MenuElement_Hover_v2_Element_Type_Flag(Menu_InGame_Technology.this.iCivID, CFG.PADDING, 0));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("EconomyGrowthModifier") + ": "));
               nData.add(new MenuElement_Hover_v2_Element_Type_Text("+1.5%", CFG.COLOR_TEXT_MODIFIER_POSITIVE));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }

            @Override
            public void actionElement(int iID) {
               SkillsManager.add_EcoGrowth(Menu_InGame_Technology.this.iCivID);
               Menu_InGame_Technology.this.getMenuElement(0)
                  .setMin(CFG.game.getCiv(Menu_InGame_Technology.this.iCivID).civGameData.skills.getPointsLeft(Menu_InGame_Technology.this.iCivID));
               Menu_InGame_Technology.this.getMenuElement(iID - 1)
                  .setCurrent(CFG.game.getCiv(Menu_InGame_Technology.this.iCivID).civGameData.skills.POINTS_ECONOMY_GROWTH);
            }

            @Override
            public int getSFX() {
               return SoundsManager.SOUND_CLICK2;
            }
         }
      );
      int var9;
      menuElements.add(new Button_Icon(Images.top_gold, 0, var9 = tY + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING));
      menuElements.add(
         new Slider_FlagAction_Clear_Tech(
            0.8F,
            CFG.langManager.get("IncomeTaxation"),
            CFG.PADDING + Button_Diplomacy.iDiploWidth,
            var9,
            tempWidth - CFG.PADDING * 3 - CFG.BUTTON_WIDTH,
            CFG.TEXT_HEIGHT + CFG.PADDING * 6,
            0,
            100,
            CFG.game.getCiv(this.iCivID).civGameData.skills.POINTS_INCOME_TAXATION
         ) {
            @Override
            public int getWidth() {
               return Math.max(Menu_InGame_Technology.this.getElementW() * 2 - CFG.BUTTON_WIDTH * 3 / 4 - CFG.PADDING * 4 - Button_Diplomacy.iDiploWidth, 0);
            }

            @Override
            public Color getColorLEFT() {
               return new Color(CFG.COLOR_INGAME_GOLD.r, CFG.COLOR_INGAME_GOLD.g, CFG.COLOR_INGAME_GOLD.b, 1.0F);
            }

            @Override
            public void actionElement(int iID) {
            }
         }
      );
      menuElements.add(
         new Button_FlagActionSliderStyle(
            "+",
            -1,
            tempWidth - CFG.BUTTON_WIDTH * 3 / 4 - CFG.PADDING,
            var9,
            CFG.BUTTON_WIDTH * 3 / 4,
            CFG.TEXT_HEIGHT + CFG.PADDING * 2 + CFG.PADDING * 4,
            true
         ) {
            @Override
            public int getPosX() {
               return Menu_InGame_Technology.this.getElementW() * 2 - this.getWidth() - CFG.PADDING;
            }

            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("AddPoint"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
               nData.add(new MenuElement_Hover_v2_Element_Type_Flag(Menu_InGame_Technology.this.iCivID, CFG.PADDING, 0));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("IncomeTaxation") + ": "));
               nData.add(new MenuElement_Hover_v2_Element_Type_Text("+0.8%", CFG.COLOR_TEXT_MODIFIER_POSITIVE));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }

            @Override
            public void actionElement(int iID) {
               SkillsManager.add_IncomeTaxation(Menu_InGame_Technology.this.iCivID);
               Menu_InGame_Technology.this.getMenuElement(0)
                  .setMin(CFG.game.getCiv(Menu_InGame_Technology.this.iCivID).civGameData.skills.getPointsLeft(Menu_InGame_Technology.this.iCivID));
               Menu_InGame_Technology.this.getMenuElement(iID - 1)
                  .setCurrent(CFG.game.getCiv(Menu_InGame_Technology.this.iCivID).civGameData.skills.POINTS_INCOME_TAXATION);
               Menu_InGame_Technology.this.rebuildBudgetView();
               Menu_InGame.updateOverBudget();
               if (CFG.viewsManager.getActiveViewID() == ViewsManager.VIEW_INCOME_MODE && CFG.menuManager.getVisible_InGame_View_Stats()) {
                  CFG.menuManager.setVisible_InGame_ViewIncome(true);
               }
            }

            @Override
            public int getSFX() {
               return SoundsManager.SOUND_CLICK2;
            }
         }
      );
      menuElements.add(new Button_Icon(Images.development, 0, tY = var9 + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING));
      menuElements.add(
         new Slider_FlagAction_Clear_Tech(
            0.8F,
            CFG.langManager.get("IncomeProduction"),
            CFG.PADDING + Button_Diplomacy.iDiploWidth,
            tY,
            tempWidth - CFG.PADDING * 3 - CFG.BUTTON_WIDTH,
            CFG.TEXT_HEIGHT + CFG.PADDING * 6,
            0,
            100,
            CFG.game.getCiv(this.iCivID).civGameData.skills.POINTS_INCOME_PRODUCTION
         ) {
            @Override
            public int getWidth() {
               return Math.max(Menu_InGame_Technology.this.getElementW() * 2 - CFG.BUTTON_WIDTH * 3 / 4 - CFG.PADDING * 4 - Button_Diplomacy.iDiploWidth, 0);
            }

            @Override
            public Color getColorLEFT() {
               return new Color(CFG.COLOR_INGAME_GOLD_ACTIVE.r, CFG.COLOR_INGAME_GOLD_ACTIVE.g, CFG.COLOR_INGAME_GOLD_ACTIVE.b, 0.65F);
            }

            @Override
            public void actionElement(int iID) {
            }
         }
      );
      menuElements.add(
         new Button_FlagActionSliderStyle(
            "+",
            -1,
            tempWidth - CFG.BUTTON_WIDTH * 3 / 4 - CFG.PADDING,
            tY,
            CFG.BUTTON_WIDTH * 3 / 4,
            CFG.TEXT_HEIGHT + CFG.PADDING * 2 + CFG.PADDING * 4,
            true
         ) {
            @Override
            public int getPosX() {
               return Menu_InGame_Technology.this.getElementW() * 2 - this.getWidth() - CFG.PADDING;
            }

            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("AddPoint"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
               nData.add(new MenuElement_Hover_v2_Element_Type_Flag(Menu_InGame_Technology.this.iCivID, CFG.PADDING, 0));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("IncomeProduction") + ": "));
               nData.add(new MenuElement_Hover_v2_Element_Type_Text("+0.8%", CFG.COLOR_TEXT_MODIFIER_POSITIVE));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }

            @Override
            public void actionElement(int iID) {
               SkillsManager.add_IncomeProduction(Menu_InGame_Technology.this.iCivID);
               Menu_InGame_Technology.this.getMenuElement(0)
                  .setMin(CFG.game.getCiv(Menu_InGame_Technology.this.iCivID).civGameData.skills.getPointsLeft(Menu_InGame_Technology.this.iCivID));
               Menu_InGame_Technology.this.getMenuElement(iID - 1)
                  .setCurrent(CFG.game.getCiv(Menu_InGame_Technology.this.iCivID).civGameData.skills.POINTS_INCOME_PRODUCTION);
               Menu_InGame_Technology.this.rebuildBudgetView();
               Menu_InGame.updateOverBudget();
               if (CFG.viewsManager.getActiveViewID() == ViewsManager.VIEW_INCOME_MODE && CFG.menuManager.getVisible_InGame_View_Stats()) {
                  CFG.menuManager.setVisible_InGame_ViewIncome(true);
               }
            }

            @Override
            public int getSFX() {
               return SoundsManager.SOUND_CLICK2;
            }
         }
      );
      int var11;
      menuElements.add(new Button_Icon(Images.top_gold2, 0, var11 = tY + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING));
      menuElements.add(
         new Slider_FlagAction_Clear_Adm(
            0.75F,
            CFG.langManager.get("Administration"),
            CFG.PADDING + Button_Diplomacy.iDiploWidth,
            var11,
            tempWidth - CFG.PADDING * 3 - CFG.BUTTON_WIDTH,
            CFG.TEXT_HEIGHT + CFG.PADDING * 6,
            0,
            80,
            CFG.game.getCiv(this.iCivID).civGameData.skills.POINTS_ADMINISTRATION
         ) {
            @Override
            public int getWidth() {
               return Math.max(Menu_InGame_Technology.this.getElementW() * 2 - CFG.BUTTON_WIDTH * 3 / 4 - CFG.PADDING * 4 - Button_Diplomacy.iDiploWidth, 0);
            }

            @Override
            public Color getColorLEFT() {
               return new Color(
                  CFG.COLOR_TEXT_MODIFIER_NEGATIVE_ACTTIVE.r, CFG.COLOR_TEXT_MODIFIER_NEGATIVE_ACTTIVE.g, CFG.COLOR_TEXT_MODIFIER_NEGATIVE_ACTTIVE.b, 0.65F
               );
            }

            @Override
            public void actionElement(int iID) {
            }
         }
      );
      menuElements.add(
         new Button_FlagActionSliderStyle(
            "+",
            -1,
            tempWidth - CFG.BUTTON_WIDTH * 3 / 4 - CFG.PADDING,
            var11,
            CFG.BUTTON_WIDTH * 3 / 4,
            CFG.TEXT_HEIGHT + CFG.PADDING * 2 + CFG.PADDING * 4,
            true
         ) {
            @Override
            public int getPosX() {
               return Menu_InGame_Technology.this.getElementW() * 2 - this.getWidth() - CFG.PADDING;
            }

            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("AddPoint"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
               nData.add(new MenuElement_Hover_v2_Element_Type_Flag(Menu_InGame_Technology.this.iCivID, CFG.PADDING, 0));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Administration") + ": "));
               nData.add(new MenuElement_Hover_v2_Element_Type_Text("-0.75%", CFG.COLOR_TEXT_MODIFIER_POSITIVE));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }

            @Override
            public void actionElement(int iID) {
               SkillsManager.add_Administration(Menu_InGame_Technology.this.iCivID);
               Menu_InGame_Technology.this.getMenuElement(0)
                  .setMin(CFG.game.getCiv(Menu_InGame_Technology.this.iCivID).civGameData.skills.getPointsLeft(Menu_InGame_Technology.this.iCivID));
               Menu_InGame_Technology.this.getMenuElement(iID - 1)
                  .setCurrent(CFG.game.getCiv(Menu_InGame_Technology.this.iCivID).civGameData.skills.POINTS_ADMINISTRATION);
               Menu_InGame_Technology.this.rebuildBudgetView();
               Menu_InGame.updateOverBudget();
               if (CFG.viewsManager.getActiveViewID() == ViewsManager.VIEW_INCOME_MODE && CFG.menuManager.getVisible_InGame_View_Stats()) {
                  CFG.menuManager.setVisible_InGame_ViewIncome(true);
               }
            }

            @Override
            public int getSFX() {
               return SoundsManager.SOUND_CLICK2;
            }
         }
      );
      menuElements.add(new Button_Icon(Images.diplo_army, 0, tY = var11 + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING));
      menuElements.add(
         new Slider_FlagAction_Clear_Adm(
            0.35F,
            CFG.langManager.get("MilitaryUpkeep"),
            CFG.PADDING + Button_Diplomacy.iDiploWidth,
            tY,
            tempWidth - CFG.PADDING * 3 - CFG.BUTTON_WIDTH,
            CFG.TEXT_HEIGHT + CFG.PADDING * 6,
            0,
            30,
            CFG.game.getCiv(this.iCivID).civGameData.skills.POINTS_MILITARY_UPKEEP
         ) {
            @Override
            public int getWidth() {
               return Math.max(Menu_InGame_Technology.this.getElementW() * 2 - CFG.BUTTON_WIDTH * 3 / 4 - CFG.PADDING * 4 - Button_Diplomacy.iDiploWidth, 0);
            }

            @Override
            public Color getColorLEFT() {
               return new Color(CFG.COLOR_ARMY_TEXT.r, CFG.COLOR_ARMY_TEXT.g, CFG.COLOR_ARMY_TEXT.b, 0.65F);
            }

            @Override
            public void actionElement(int iID) {
            }
         }
      );
      menuElements.add(
         new Button_FlagActionSliderStyle(
            "+",
            -1,
            tempWidth - CFG.BUTTON_WIDTH * 3 / 4 - CFG.PADDING,
            tY,
            CFG.BUTTON_WIDTH * 3 / 4,
            CFG.TEXT_HEIGHT + CFG.PADDING * 2 + CFG.PADDING * 4,
            true
         ) {
            @Override
            public int getPosX() {
               return Menu_InGame_Technology.this.getElementW() * 2 - this.getWidth() - CFG.PADDING;
            }

            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("AddPoint"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
               nData.add(new MenuElement_Hover_v2_Element_Type_Flag(Menu_InGame_Technology.this.iCivID, CFG.PADDING, 0));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("MilitaryUpkeep") + ": "));
               nData.add(new MenuElement_Hover_v2_Element_Type_Text("-0.35%", CFG.COLOR_TEXT_MODIFIER_POSITIVE));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }

            @Override
            public void actionElement(int iID) {
               SkillsManager.add_MilitaryUpkeep(Menu_InGame_Technology.this.iCivID);
               Menu_InGame_Technology.this.getMenuElement(0)
                  .setMin(CFG.game.getCiv(Menu_InGame_Technology.this.iCivID).civGameData.skills.getPointsLeft(Menu_InGame_Technology.this.iCivID));
               Menu_InGame_Technology.this.getMenuElement(iID - 1)
                  .setCurrent(CFG.game.getCiv(Menu_InGame_Technology.this.iCivID).civGameData.skills.POINTS_MILITARY_UPKEEP);
               Menu_InGame_Technology.this.rebuildBudgetView();
               Menu_InGame.updateOverBudget();
            }

            @Override
            public int getSFX() {
               return SoundsManager.SOUND_CLICK2;
            }
         }
      );
      int var13;
      menuElements.add(new Button_Icon(Images.provinces, 0, var13 = tY + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING));
      menuElements.add(
         new Slider_FlagAction_Clear_Adm(
            1.0F,
            CFG.langManager.get("ColonizationCost"),
            CFG.PADDING + Button_Diplomacy.iDiploWidth,
            var13,
            tempWidth - CFG.PADDING * 3 - CFG.BUTTON_WIDTH,
            CFG.TEXT_HEIGHT + CFG.PADDING * 6,
            0,
            15,
            CFG.game.getCiv(this.iCivID).civGameData.skills.POINTS_COLONIZATION
         ) {
            @Override
            public int getWidth() {
               return Math.max(Menu_InGame_Technology.this.getElementW() * 2 - CFG.BUTTON_WIDTH * 3 / 4 - CFG.PADDING * 4 - Button_Diplomacy.iDiploWidth, 0);
            }

            @Override
            public Color getColorLEFT() {
               return new Color(
                  CFG.COLOR_GRADIENT_TITLE_BLUE_LIGHT_ALLIANCE.r,
                  CFG.COLOR_GRADIENT_TITLE_BLUE_LIGHT_ALLIANCE.g,
                  CFG.COLOR_GRADIENT_TITLE_BLUE_LIGHT_ALLIANCE.b,
                  0.65F
               );
            }

            @Override
            public void actionElement(int iID) {
            }
         }
      );
      menuElements.add(
         new Button_FlagActionSliderStyle(
            "+",
            -1,
            tempWidth - CFG.BUTTON_WIDTH * 3 / 4 - CFG.PADDING,
            var13,
            CFG.BUTTON_WIDTH * 3 / 4,
            CFG.TEXT_HEIGHT + CFG.PADDING * 2 + CFG.PADDING * 4,
            true
         ) {
            @Override
            public int getPosX() {
               return Menu_InGame_Technology.this.getElementW() * 2 - this.getWidth() - CFG.PADDING;
            }

            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("AddPoint"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
               nData.add(new MenuElement_Hover_v2_Element_Type_Flag(Menu_InGame_Technology.this.iCivID, CFG.PADDING, 0));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("ColonizationCost") + ": "));
               nData.add(new MenuElement_Hover_v2_Element_Type_Text("-1.0%", CFG.COLOR_TEXT_MODIFIER_POSITIVE));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }

            @Override
            public void actionElement(int iID) {
               SkillsManager.add_Colonization(Menu_InGame_Technology.this.iCivID);
               Menu_InGame_Technology.this.getMenuElement(0)
                  .setMin(CFG.game.getCiv(Menu_InGame_Technology.this.iCivID).civGameData.skills.getPointsLeft(Menu_InGame_Technology.this.iCivID));
               Menu_InGame_Technology.this.getMenuElement(iID - 1)
                  .setCurrent(CFG.game.getCiv(Menu_InGame_Technology.this.iCivID).civGameData.skills.POINTS_COLONIZATION);
            }

            @Override
            public int getSFX() {
               return SoundsManager.SOUND_CLICK2;
            }
         }
      );
      menuElements.add(new Button_Icon(Images.research, 0, tY = var13 + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING));
      menuElements.add(
         new Slider_FlagAction_Clear_Tech(
            1.75F,
            CFG.langManager.get("Research"),
            CFG.PADDING + Button_Diplomacy.iDiploWidth,
            tY,
            tempWidth - CFG.PADDING * 3 - CFG.BUTTON_WIDTH,
            CFG.TEXT_HEIGHT + CFG.PADDING * 6,
            0,
            100,
            CFG.game.getCiv(this.iCivID).civGameData.skills.POINTS_RESEARCH
         ) {
            @Override
            public int getWidth() {
               return Math.max(Menu_InGame_Technology.this.getElementW() * 2 - CFG.BUTTON_WIDTH * 3 / 4 - CFG.PADDING * 4 - Button_Diplomacy.iDiploWidth, 0);
            }

            @Override
            public Color getColorLEFT() {
               return new Color(CFG.COLOR_TEXT_RESEARCH.r, CFG.COLOR_TEXT_RESEARCH.g, CFG.COLOR_TEXT_RESEARCH.b, 1.0F);
            }

            @Override
            public void actionElement(int iID) {
            }
         }
      );
      menuElements.add(
         new Button_FlagActionSliderStyle(
            "+",
            -1,
            tempWidth - CFG.BUTTON_WIDTH * 3 / 4 - CFG.PADDING,
            tY,
            CFG.BUTTON_WIDTH * 3 / 4,
            CFG.TEXT_HEIGHT + CFG.PADDING * 2 + CFG.PADDING * 4,
            true
         ) {
            @Override
            public int getPosX() {
               return Menu_InGame_Technology.this.getElementW() * 2 - this.getWidth() - CFG.PADDING;
            }

            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("AddPoint"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
               nData.add(new MenuElement_Hover_v2_Element_Type_Flag(Menu_InGame_Technology.this.iCivID, CFG.PADDING, 0));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Research") + ": "));
               nData.add(new MenuElement_Hover_v2_Element_Type_Text("+1.75%", CFG.COLOR_TEXT_MODIFIER_POSITIVE));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }

            @Override
            public void actionElement(int iID) {
               SkillsManager.add_Research(Menu_InGame_Technology.this.iCivID);
               Menu_InGame_Technology.this.getMenuElement(0)
                  .setMin(CFG.game.getCiv(Menu_InGame_Technology.this.iCivID).civGameData.skills.getPointsLeft(Menu_InGame_Technology.this.iCivID));
               Menu_InGame_Technology.this.getMenuElement(iID - 1)
                  .setCurrent(CFG.game.getCiv(Menu_InGame_Technology.this.iCivID).civGameData.skills.POINTS_RESEARCH);
               Menu_InGame_Technology.this.rebuildBudgetView();
               Menu_InGame.updateOverBudget();
            }

            @Override
            public int getSFX() {
               return SoundsManager.SOUND_CLICK2;
            }
         }
      );
      int var15;
      menuElements.add(new Button_Icon(Images.b_library, 0, var15 = tY + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING));
      menuElements.add(
         new Slider_FlagAction_Clear_Tech(
            2.25F,
            CFG.langManager.get("Education"),
            CFG.PADDING + Button_Diplomacy.iDiploWidth,
            var15,
            tempWidth - CFG.PADDING * 3 - CFG.BUTTON_WIDTH,
            CFG.TEXT_HEIGHT + CFG.PADDING * 6,
            0,
            25,
            CFG.game.getCiv(this.iCivID).civGameData.skills.POINTS_Education
         ) {
            @Override
            public int getWidth() {
               return Math.max(Menu_InGame_Technology.this.getElementW() * 2 - CFG.BUTTON_WIDTH * 3 / 4 - CFG.PADDING * 4 - Button_Diplomacy.iDiploWidth, 0);
            }

            @Override
            public Color getColorLEFT() {
               return new Color(CFG.COLOR_INGAME_DIPLOMACY_POINTS.r, CFG.COLOR_INGAME_DIPLOMACY_POINTS.g, CFG.COLOR_INGAME_DIPLOMACY_POINTS.b, 1.0F);
            }

            @Override
            public void actionElement(int iID) {
            }
         }
      );
      menuElements.add(
         new Button_FlagActionSliderStyle(
            "+",
            -1,
            tempWidth - CFG.BUTTON_WIDTH * 3 / 4 - CFG.PADDING,
            var15,
            CFG.BUTTON_WIDTH * 3 / 4,
            CFG.TEXT_HEIGHT + CFG.PADDING * 2 + CFG.PADDING * 4,
            true
         ) {
            @Override
            public int getPosX() {
               return Menu_InGame_Technology.this.getElementW() * 2 - this.getWidth() - CFG.PADDING;
            }

            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("AddPoint"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
               nData.add(new MenuElement_Hover_v2_Element_Type_Flag(Menu_InGame_Technology.this.iCivID, CFG.PADDING, 0));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Research") + ": "));
               nData.add(new MenuElement_Hover_v2_Element_Type_Text("+2.25%", CFG.COLOR_TEXT_MODIFIER_POSITIVE));
               nData.add(new MenuElement_Hover_v2_Element_Type_Flag(Menu_InGame_Technology.this.iCivID, CFG.PADDING, 0));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }

            @Override
            public void actionElement(int iID) {
               SkillsManager.add_Education(Menu_InGame_Technology.this.iCivID);
               Menu_InGame_Technology.this.getMenuElement(0)
                  .setMin(CFG.game.getCiv(Menu_InGame_Technology.this.iCivID).civGameData.skills.getPointsLeft(Menu_InGame_Technology.this.iCivID));
               Menu_InGame_Technology.this.getMenuElement(iID - 1)
                  .setCurrent(CFG.game.getCiv(Menu_InGame_Technology.this.iCivID).civGameData.skills.POINTS_Education);
               Menu_InGame_Technology.this.rebuildBudgetView();
               Menu_InGame.updateOverBudget();
            }

            @Override
            public int getSFX() {
               return SoundsManager.SOUND_CLICK2;
            }
         }
      );
      tY = var15 + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
      int var17;
      menuElements.add(new Button_FlagActionSliderStyle(CFG.langManager.get("Close"), -1, CFG.PADDING, var17 = tY + CFG.PADDING, CFG.BUTTON_WIDTH, true) {
         @Override
         public int getWidth() {
            return Menu_InGame_Technology.this.getElementW() * 2 - CFG.PADDING * 2;
         }
      });
      int tempMenuPosY = ImageManager.getImage(Images.top_left2).getHeight() + CFG.PADDING * 2 + CFG.BUTTON_HEIGHT * 3 / 5;
      this.initMenu(
         new SliderMenuTitle(CFG.langManager.get("TechnologyPoints"), CFG.BUTTON_HEIGHT * 3 / 5, true, true) {
            @Override
            public void draw(SpriteBatch oSB, int iTranslateX, int nPosX, int nPosY, int nWidth, boolean sliderMenuIsActive) {
               ImageManager.getImage(Images.dialog_title)
                  .draw2(
                     oSB,
                     nPosX - 2 + iTranslateX,
                     nPosY - this.getHeight() - ImageManager.getImage(Images.dialog_title).getHeight(),
                     nWidth + 4 - ImageManager.getImage(Images.dialog_title).getWidth(),
                     this.getHeight()
                  );
               ImageManager.getImage(Images.dialog_title)
                  .draw2(
                     oSB,
                     nPosX + nWidth + 2 - ImageManager.getImage(Images.dialog_title).getWidth() + iTranslateX,
                     nPosY - this.getHeight() - ImageManager.getImage(Images.dialog_title).getHeight(),
                     ImageManager.getImage(Images.dialog_title).getWidth(),
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
               oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.65F));
               ImageManager.getImage(Images.gradient)
                  .draw(oSB, nPosX + iTranslateX, nPosY - CFG.PADDING - ImageManager.getImage(Images.gradient).getHeight(), nWidth, CFG.PADDING, false, true);
               oSB.setColor(CFG.COLOR_NEW_GAME_EDGE_LINE);
               ImageManager.getImage(Images.pix255_255_255)
                  .draw(oSB, nPosX + iTranslateX, nPosY - 1 - ImageManager.getImage(Images.pix255_255_255).getHeight(), nWidth, 1);
               oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.55F));
               ImageManager.getImage(Images.line_32_off1)
                  .draw(oSB, nPosX + iTranslateX, nPosY - 2 - ImageManager.getImage(Images.line_32_off1).getHeight(), nWidth, 1);
               oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.8F));
               ImageManager.getImage(Images.line_32_off1)
                  .draw(oSB, nPosX + iTranslateX, nPosY - 1 - ImageManager.getImage(Images.line_32_off1).getHeight(), nWidth, 1);
               oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.45F));
               ImageManager.getImage(Images.slider_gradient)
                  .draw(oSB, nPosX + iTranslateX, nPosY - 1 - ImageManager.getImage(Images.slider_gradient).getHeight(), nWidth / 2, 1);
               ImageManager.getImage(Images.slider_gradient)
                  .draw(
                     oSB,
                     nPosX + nWidth - nWidth / 2 + iTranslateX,
                     nPosY - 1 - ImageManager.getImage(Images.slider_gradient).getHeight(),
                     nWidth / 2,
                     1,
                     true,
                     false
                  );
               oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.425F));
               ImageManager.getImage(Images.slider_gradient)
                  .draw(
                     oSB,
                     nPosX + CFG.PADDING * 2 + iTranslateX,
                     nPosY + 1 - this.getHeight() / 2 - ImageManager.getImage(Images.slider_gradient).getHeight(),
                     (int)((nWidth - CFG.PADDING * 6 - this.getTextWidth() * 0.8F) / 2.0F),
                     1,
                     true,
                     false
                  );
               ImageManager.getImage(Images.slider_gradient)
                  .draw(
                     oSB,
                     nPosX + nWidth / 2 + CFG.PADDING + (int)(this.getTextWidth() * 0.8F / 2.0F) + iTranslateX,
                     nPosY + 1 - this.getHeight() / 2 - ImageManager.getImage(Images.slider_gradient).getHeight(),
                     (int)((nWidth - CFG.PADDING * 6 - this.getTextWidth() * 0.8F) / 2.0F),
                     1
                  );
               oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.325F));
               ImageManager.getImage(Images.slider_gradient)
                  .draw(
                     oSB,
                     nPosX + CFG.PADDING * 2 + iTranslateX,
                     nPosY + 2 - this.getHeight() / 2 - ImageManager.getImage(Images.slider_gradient).getHeight(),
                     (int)((nWidth - CFG.PADDING * 6 - this.getTextWidth() * 0.8F) / 2.0F),
                     1,
                     true,
                     false
                  );
               ImageManager.getImage(Images.slider_gradient)
                  .draw(
                     oSB,
                     nPosX + nWidth / 2 + CFG.PADDING + (int)(this.getTextWidth() * 0.8F / 2.0F) + iTranslateX,
                     nPosY + 2 - this.getHeight() / 2 - ImageManager.getImage(Images.slider_gradient).getHeight(),
                     (int)((nWidth - CFG.PADDING * 6 - this.getTextWidth() * 0.8F) / 2.0F),
                     1
                  );
               ImageManager.getImage(Images.slider_gradient)
                  .draw(
                     oSB,
                     nPosX + CFG.PADDING * 2 + iTranslateX,
                     nPosY - this.getHeight() / 2 - ImageManager.getImage(Images.slider_gradient).getHeight(),
                     (int)((nWidth - CFG.PADDING * 6 - this.getTextWidth() * 0.8F) / 2.0F),
                     1,
                     true,
                     false
                  );
               ImageManager.getImage(Images.slider_gradient)
                  .draw(
                     oSB,
                     nPosX + nWidth / 2 + CFG.PADDING + (int)(this.getTextWidth() * 0.8F / 2.0F) + iTranslateX,
                     nPosY - this.getHeight() / 2 - ImageManager.getImage(Images.slider_gradient).getHeight(),
                     (int)((nWidth - CFG.PADDING * 6 - this.getTextWidth() * 0.8F) / 2.0F),
                     1
                  );
               oSB.setColor(Color.WHITE);
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
            ? Math.max(CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING * 2 - tempMenuPosY, (CFG.TEXT_HEIGHT + CFG.PADDING * 2) * 6)
            : menuElements.get(menuElements.size() - 1).getPosY() + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING,
         menuElements,
         true,
         true
      );
      this.updateLanguage();
      this.lTime = System.currentTimeMillis();

      for (int i = 0; i < this.getMenuElementsSize(); i++) {
         this.getMenuElement(i).setCurrent(this.getMenuElement(i).getCurrent());
      }
   }

   @Override
   public void updateLanguage() {
   }

   @Override
   public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      if (this.lTime + 200L >= System.currentTimeMillis()) {
         Rectangle clipBounds = new Rectangle(
            this.getPosX() - 2,
            CFG.GAME_HEIGHT - this.getPosY(),
            this.getWidth() + 4,
            -((int)((this.getHeight() + CFG.PADDING) * ((float)(System.currentTimeMillis() - this.lTime) / 200.0F)))
         );
         oSB.flush();
         ScissorStack.pushScissors(clipBounds);
         oSB.setColor(Color.WHITE);
         ImageManager.getImage(Images.new_game_top_edge)
            .draw2(
               oSB,
               this.getPosX() - 2 + iTranslateX,
               this.getPosY() - ImageManager.getImage(Images.new_game_top_edge).getHeight() + iTranslateY,
               this.getWidth() - ImageManager.getImage(Images.new_game_top_edge).getWidth() + 4,
               this.getHeight() + CFG.PADDING,
               false,
               true
            );
         ImageManager.getImage(Images.new_game_top_edge)
            .draw2(
               oSB,
               this.getPosX() + 2 + this.getWidth() - ImageManager.getImage(Images.new_game_top_edge).getWidth() + iTranslateX,
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
         this.drawMenu(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
         oSB.setColor(Color.WHITE);
         CFG.setRender_3(true);
         this.endClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
      } else {
         oSB.setColor(Color.WHITE);
         ImageManager.getImage(Images.new_game_top_edge)
            .draw2(
               oSB,
               this.getPosX() - 2 + iTranslateX,
               this.getPosY() - ImageManager.getImage(Images.new_game_top_edge).getHeight() + iTranslateY,
               this.getWidth() - ImageManager.getImage(Images.new_game_top_edge).getWidth() + 4,
               this.getHeight() + CFG.PADDING,
               false,
               true
            );
         ImageManager.getImage(Images.new_game_top_edge)
            .draw2(
               oSB,
               this.getPosX() + 2 + this.getWidth() - ImageManager.getImage(Images.new_game_top_edge).getWidth() + iTranslateX,
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
   public final void actionElement(int iID) {
      if (iID == this.getMenuElementsSize() - 1) {
         this.setVisible(false);
      } else {
         this.getMenuElement(iID).actionElement(iID);
      }
   }

   public final int getW() {
      return this.getWidth() - 4;
   }

   public final int getElementW() {
      return this.getW() / 2;
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

   public void rebuildBudgetView() {
      if (CFG.menuManager.getVisible_InGame_Budget()) {
         CFG.menuManager.setVisible_InGame_Budget(true);
         Menu_InGame_FlagAction_Bot.lTime = 1L;
      } else if (CFG.menuManager.getVisible_InGame_FlagAction()) {
         CFG.menuManager.rebuildInGame_FlagActionLeft();
      } else {
         CFG.game_NextTurnUpdate.getBalance_UpdateBudget_Prepare(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
      }

      CFG.menuManager.setOrderOfTechPoints();
   }
}
