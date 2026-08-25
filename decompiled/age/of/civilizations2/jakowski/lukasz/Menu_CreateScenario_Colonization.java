package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

public class Menu_CreateScenario_Colonization extends SliderMenu {
   public Menu_CreateScenario_Colonization() {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      int tY = CFG.PADDING;
      menuElements.add(
         new Button_Menu(null, (int)(50.0F * CFG.GUI_SCALE), 0, tY, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT, true, Game_Calendar.ENABLE_COLONIZATION) {
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
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("ColonizationofWastelandProvinces") + "."));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }

            @Override
            public void actionElement(int iID) {
               Game_Calendar.ENABLE_COLONIZATION = !Game_Calendar.ENABLE_COLONIZATION;
            }
         }
      );
      int var3;
      menuElements.add(
         new Button_Menu(
            null,
            (int)(50.0F * CFG.GUI_SCALE),
            0,
            var3 = tY + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING,
            CFG.GAME_WIDTH,
            CFG.BUTTON_HEIGHT,
            true,
            Game_Calendar.ENABLE_COLONIZATION_NEUTRAL_PROVINCES
         ) {
            @Override
            public boolean getCheckboxState() {
               return Game_Calendar.ENABLE_COLONIZATION_NEUTRAL_PROVINCES;
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
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("ColonizationofNeutralProvinces") + "."));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }

            @Override
            public void actionElement(int iID) {
               Game_Calendar.ENABLE_COLONIZATION_NEUTRAL_PROVINCES = !Game_Calendar.ENABLE_COLONIZATION_NEUTRAL_PROVINCES;
               Menu_CreateScenario_Colonization.this.updateLanguage();
            }
         }
      );
      menuElements.add(
         new Slider(
            null,
            CFG.BUTTON_WIDTH / 2,
            tY = var3 + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING * 2,
            CFG.GAME_WIDTH - CFG.BUTTON_WIDTH,
            CFG.BUTTON_HEIGHT - CFG.PADDING * 2,
            0,
            100,
            (int)(Game_Calendar.COLONIZATION_TECH_LEVEL * 100.0F)
         ) {
            @Override
            public String getDrawText() {
               return super.getText() + this.getCurrent() / 100.0F;
            }

            @Override
            public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
               ImageManager.getImage(Images.btn_menu_1_h)
                  .draw(
                     oSB, this.getPosX() - CFG.BUTTON_WIDTH / 2 + iTranslateX, this.getPosY() - CFG.PADDING + iTranslateY, this.getWidth() + CFG.BUTTON_WIDTH
                  );
               super.draw(oSB, iTranslateX, iTranslateY, isActive, scrollableY);
            }

            @Override
            public void setCurrent(int nCurrent) {
               Game_Calendar.COLONIZATION_TECH_LEVEL = this.getCurrent() / 100.0F;
               super.setCurrent(nCurrent);
            }

            @Override
            public void actionElement(int iID) {
               Game_Calendar.COLONIZATION_TECH_LEVEL = this.getCurrent() / 100.0F;
            }
         }
      );
      this.initMenu(
         null, 0, CFG.BUTTON_HEIGHT * 3 / 4, CFG.GAME_WIDTH, CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT * 3 / 4 - CFG.BUTTON_HEIGHT - CFG.PADDING, menuElements
      );
      this.updateLanguage();
      CFG.lCreateScenario_UndoAssignProvincesCivID = new ArrayList<>();
      CFG.lCreateScenario_UndoWastelandProvinces = new ArrayList<>();
   }

   @Override
   public void updateLanguage() {
      this.getMenuElement(0).setText(CFG.langManager.get("ColonizationofWastelandProvinces"));
      this.getMenuElement(1)
         .setText(
            CFG.langManager.get("NeutralProvinces")
               + ": "
               + (Game_Calendar.ENABLE_COLONIZATION_NEUTRAL_PROVINCES ? CFG.langManager.get("Colonization") : CFG.langManager.get("Conquering"))
         );
      this.getMenuElement(2).setText(CFG.langManager.get("RequiredTechnologyLevel") + ": ");
   }

   @Override
   public final void actionElement(int iID) {
      this.getMenuElement(iID).actionElement(iID);
   }
}
