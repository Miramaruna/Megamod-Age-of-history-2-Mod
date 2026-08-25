package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

public class Menu_CreateScenario_Events extends Menu_CreateScenario {
   public String assignProvinces;
   public int iStepWidth;
   public String assignProvinces2;
   public int iStepWidth2;

   public Menu_CreateScenario_Events() {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      menuElements.add(new Button_Game(null, -1, CFG.PADDING, CFG.PADDING, true) {
         @Override
         public void buildElementHover() {
            ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
            ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("ScenarioSettings"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
            this.menuElementHover = new MenuElement_Hover_v2(nElements);
         }

         @Override
         public boolean getClickable() {
            return !CFG.menuManager.getVisibleCreateScenario_Events_Edit();
         }
      });
      menuElements.add(new Button_Game(null, -1, CFG.GAME_WIDTH - CFG.BUTTON_WIDTH - CFG.PADDING, CFG.PADDING, true) {
         @Override
         public void buildElementHover() {
            ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
            ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("ScenarioSettings"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
            this.menuElementHover = new MenuElement_Hover_v2(nElements);
         }

         @Override
         public boolean getClickable() {
            return !CFG.menuManager.getVisibleCreateScenario_Events_Edit();
         }
      });
      this.initMenu(null, 0, 0, CFG.GAME_WIDTH, CFG.GAME_HEIGHT, menuElements);
      this.updateLanguage();
   }

   @Override
   public void updateLanguage() {
      this.getMenuElement(0).setText(CFG.langManager.get("Back"));
      this.getMenuElement(1).setText(CFG.langManager.get("Save"));
      this.assignProvinces = CFG.langManager.get("SetEvents");
      CFG.glyphLayout.setText(CFG.fontMain, this.assignProvinces);
      this.iStepWidth = (int)CFG.glyphLayout.width;
      this.assignProvinces2 = CFG.gameAges.getAge(CFG.CREATE_SCENARIO_AGE).getName()
         + " - "
         + Game_Calendar.currentDay
         + " "
         + Game_Calendar.getMonthName(Game_Calendar.currentMonth)
         + " "
         + CFG.gameAges.getYear(Game_Calendar.currentYear)
         + "";
      CFG.glyphLayout.setText(CFG.fontMain, this.assignProvinces2);
      this.iStepWidth2 = (int)CFG.glyphLayout.width;
   }

   @Override
   public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      CFG.drawEditorTitle_Edge_R_Reflected(oSB, iTranslateX, this.getMenuPosY() + iTranslateY, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT + CFG.PADDING * 2);
      CFG.drawTextWithShadow(
         oSB,
         this.assignProvinces,
         CFG.GAME_WIDTH / 2 - (this.iStepWidth + CFG.CIV_FLAG_WIDTH + CFG.PADDING) / 2 + CFG.PADDING + CFG.CIV_FLAG_WIDTH + iTranslateX,
         CFG.PADDING + CFG.BUTTON_HEIGHT / 2 - CFG.TEXT_HEIGHT - CFG.PADDING / 2 + this.getMenuPosY() + iTranslateY,
         new Color(CFG.COLOR_TEXT_CNG_TOP_SCENARIO_NAME.r, CFG.COLOR_TEXT_CNG_TOP_SCENARIO_NAME.g, CFG.COLOR_TEXT_CNG_TOP_SCENARIO_NAME.b, 0.95F)
      );
      CFG.fontMain.getData().setScale(0.8F);
      CFG.drawTextWithShadow(
         oSB,
         this.assignProvinces2,
         CFG.GAME_WIDTH / 2 - (int)(this.iStepWidth2 * 0.8F / 2.0F) + iTranslateX,
         CFG.PADDING + CFG.BUTTON_HEIGHT / 2 + CFG.PADDING + this.getMenuPosY() + iTranslateY,
         new Color(CFG.COLOR_TEXT_CNG_TOP_SCENARIO_INFO.r, CFG.COLOR_TEXT_CNG_TOP_SCENARIO_INFO.g, CFG.COLOR_TEXT_CNG_TOP_SCENARIO_INFO.b, 0.75F)
      );
      CFG.fontMain.getData().setScale(1.0F);
      super.draw(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
   }

   @Override
   public final void actionElement(int iID) {
      switch (iID) {
         case 0:
         case 1:
            this.onBackPressed();
            return;
         default:
            super.actionElement(iID);
      }
   }

   @Override
   public void onBackPressed() {
      if (CFG.menuManager.getVisibleCreateScenario_Events_Edit()) {
         CFG.setDialogType(Dialog.CREATE_SCENARIO_EVENTS_EDIT_BACK);
      } else {
         CFG.eventsManager.sortEventsByDate();
         CFG.menuManager.setViewID(Menu.eCREATE_SCENARIO_SETTINGS);
      }
   }
}
