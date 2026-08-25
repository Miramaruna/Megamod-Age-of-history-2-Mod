package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

public class Menu_CreateScenario_Settings_Top extends Menu_CreateScenario {
   public String sScenarioSettings;
   public int iStepWidth;

   public Menu_CreateScenario_Settings_Top() {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      menuElements.add(new Button_Game(null, -1, CFG.PADDING, CFG.PADDING, true) {
         @Override
         public void buildElementHover() {
            ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
            ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("AssignProvinces"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
            this.menuElementHover = new MenuElement_Hover_v2(nElements);
         }
      });
      menuElements.add(new Button_Game(null, -1, CFG.GAME_WIDTH - CFG.BUTTON_WIDTH - CFG.PADDING, CFG.PADDING, true) {
         @Override
         public void buildElementHover() {
            ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
            ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("SaveScenario"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
            this.menuElementHover = new MenuElement_Hover_v2(nElements);
         }
      });
      menuElements.add(new Minimap(CFG.GAME_WIDTH - CFG.map.getMapBG().getMinimapWidth(), CFG.GAME_HEIGHT - CFG.map.getMapBG().getMinimapHeight()));
      menuElements.remove(menuElements.size() - 1);
      this.initMenu(null, 0, 0, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT + CFG.PADDING * 2, menuElements);
      this.updateLanguage();
   }

   @Override
   public void updateLanguage() {
      super.updateLanguage();
      this.sScenarioSettings = CFG.langManager.get("ScenarioSettings");
      CFG.glyphLayout.setText(CFG.fontMain, this.sScenarioSettings);
      this.iStepWidth = (int)CFG.glyphLayout.width;
      this.getMenuElement(1).setText(CFG.langManager.get("Save"));
   }

   @Override
   public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      super.beginClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
      CFG.drawEditorTitle_Edge_LR(oSB, iTranslateX, this.getMenuPosY() + iTranslateY, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT + CFG.PADDING * 2);
      CFG.drawTextWithShadow(
         oSB,
         this.sScenarioSettings,
         CFG.GAME_WIDTH / 2 - this.iStepWidth / 2 + iTranslateX,
         CFG.BUTTON_HEIGHT / 2 - CFG.TEXT_HEIGHT / 2 + CFG.PADDING + this.getMenuPosY() + iTranslateY,
         Color.WHITE
      );
      super.drawMenu(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
      super.endClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
   }

   @Override
   public final void actionElement(int iID) {
      switch (iID) {
         case 1:
            CFG.menuManager.saveCreateScenarioSettings_Data();
            CFG.setDialogType(Dialog.SAVE_SCENARIO);
            return;
         default:
            super.actionElement(iID);
      }
   }

   @Override
   public void onBackPressed() {
      CFG.menuManager.saveCreateScenarioSettings_Data();
      CFG.lCreateScenario_UndoAssignProvincesCivID.clear();
      CFG.iCreateScenario_AssignProvinces_Civ = 0;
      CFG.menuManager.setViewID(Menu.eCREATE_SCENARIO_ASSIGN);
   }
}
