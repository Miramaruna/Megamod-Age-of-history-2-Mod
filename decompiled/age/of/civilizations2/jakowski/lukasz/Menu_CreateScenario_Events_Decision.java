package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

public class Menu_CreateScenario_Events_Decision extends Menu_CreateScenario {
   public String assignProvinces;
   public int iStepWidth;
   public String sTitle;

   public Menu_CreateScenario_Events_Decision() {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      menuElements.add(new Button_Game(null, -1, CFG.PADDING, CFG.PADDING, true));
      menuElements.add(new Button_Game(null, -1, CFG.GAME_WIDTH - CFG.BUTTON_WIDTH - CFG.PADDING, CFG.PADDING, true));
      int tY = CFG.BUTTON_HEIGHT + CFG.PADDING * 3;
      menuElements.add(new Button_Menu_LR_Line(null, (int)(50.0F * CFG.GUI_SCALE), 0, tY, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT, true) {
         @Override
         public String getTextToDraw() {
            return Menu_CreateScenario_Events_Decision.this.sTitle + super.getTextToDraw();
         }
      });
      int var4;
      menuElements.add(
         new Button_Menu_LR_Line(
            null, -1, 0, var4 = tY + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT, true
         )
      );
      menuElements.add(
         new Slider_BG(
            0,
            tY = var4 + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING * 2,
            CFG.GAME_WIDTH,
            CFG.BUTTON_HEIGHT - CFG.PADDING * 2,
            0,
            100,
            CFG.eventsManager.lCreateScenario_Event.lDecisions.get(CFG.eventsManager.iCreateEvent_EditTriggerID).iAIChance
         ) {
            @Override
            public String getDrawText() {
               return super.getDrawText() + "%";
            }
         }
      );
      tY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING * 2;

      for (int i = 0; i < CFG.eventsManager.lCreateScenario_Event.lDecisions.get(CFG.eventsManager.iCreateEvent_EditTriggerID).lOutcomes.size(); i++) {
         menuElements.add(
            new Button_Menu(
               CFG.eventsManager.lCreateScenario_Event.lDecisions.get(CFG.eventsManager.iCreateEvent_EditTriggerID).lOutcomes.get(i).getConditionText(),
               (int)(50.0F * CFG.GUI_SCALE),
               0,
               tY,
               CFG.GAME_WIDTH - CFG.BUTTON_WIDTH,
               CFG.BUTTON_HEIGHT,
               true
            )
         );
         menuElements.add(new Button_Menu_Remove(CFG.GAME_WIDTH - CFG.BUTTON_WIDTH, tY, CFG.BUTTON_WIDTH, CFG.BUTTON_HEIGHT, true) {
            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Delete"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
         });
         tY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
      }

      this.initMenu(null, 0, 0, CFG.GAME_WIDTH, CFG.GAME_HEIGHT, menuElements);
      this.updateLanguage();
   }

   @Override
   public void updateLanguage() {
      this.getMenuElement(0).setText(CFG.langManager.get("Back"));
      this.getMenuElement(1).setText(CFG.langManager.get("Save"));
      this.assignProvinces = CFG.langManager.get("Decision");
      CFG.glyphLayout.setText(CFG.fontMain, this.assignProvinces);
      this.iStepWidth = (int)CFG.glyphLayout.width;
      this.sTitle = CFG.langManager.get("Title") + ": ";
      this.getMenuElement(2).setText(CFG.eventsManager.lCreateScenario_Event.lDecisions.get(CFG.eventsManager.iCreateEvent_EditTriggerID).sTitle);
      this.getMenuElement(3).setText(CFG.langManager.get("AddNewOutcome"));
      this.getMenuElement(4).setText(CFG.langManager.get("AIChance") + ": ");
   }

   @Override
   public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      CFG.drawEditorTitle_Edge_R_Reflected(oSB, iTranslateX, this.getMenuPosY() + iTranslateY, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT + CFG.PADDING * 2);
      CFG.drawTextWithShadow(
         oSB,
         this.assignProvinces,
         CFG.GAME_WIDTH / 2 - (this.iStepWidth + CFG.CIV_FLAG_WIDTH + CFG.PADDING) / 2 + CFG.PADDING + CFG.CIV_FLAG_WIDTH + iTranslateX,
         CFG.PADDING + CFG.BUTTON_HEIGHT / 2 - CFG.TEXT_HEIGHT / 2 + this.getMenuPosY() + iTranslateY,
         new Color(CFG.COLOR_TEXT_CNG_TOP_SCENARIO_NAME.r, CFG.COLOR_TEXT_CNG_TOP_SCENARIO_NAME.g, CFG.COLOR_TEXT_CNG_TOP_SCENARIO_NAME.b, 0.95F)
      );
      super.draw(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
   }

   public final void saveData() {
      CFG.eventsManager.lCreateScenario_Event.lDecisions.get(CFG.eventsManager.iCreateEvent_EditTriggerID).sTitle = this.getMenuElement(2).getText();
   }

   @Override
   public final void actionElement(int iID) {
      switch (iID) {
         case 0:
         case 1:
            this.saveData();
            this.onBackPressed();
            return;
         case 2:
            CFG.showKeyboard();
            return;
         case 3:
            this.saveData();
            CFG.menuManager.setViewID(Menu.eCREATE_SCENARIO_EVENTS_ADD_NEW_OUTCOME);
            return;
         case 4:
            CFG.eventsManager.lCreateScenario_Event.lDecisions.get(CFG.eventsManager.iCreateEvent_EditTriggerID).iAIChance = this.getMenuElement(iID)
               .getCurrent();
            return;
         default:
            iID -= 5;
            if (iID % 2 == 0) {
               CFG.eventsManager.iCreateEvent_EditConditionID = iID / 2;
               CFG.eventsManager.lCreateScenario_Event.lDecisions.get(CFG.eventsManager.iCreateEvent_EditTriggerID)
                  .lOutcomes
                  .get(CFG.eventsManager.iCreateEvent_EditConditionID)
                  .editViewID();
            } else {
               CFG.eventsManager.iCreateEvent_EditConditionID = iID / 2;
               CFG.eventsManager.lCreateScenario_Event.lDecisions.get(CFG.eventsManager.iCreateEvent_EditTriggerID)
                  .lOutcomes
                  .remove(CFG.eventsManager.iCreateEvent_EditConditionID);
               CFG.menuManager.setViewID(Menu.eCREATE_SCENARIO_EVENTS_DECISION);
            }
      }
   }

   @Override
   public void onBackPressed() {
      CFG.eventsManager.lCreateScenario_Event.checkDecisions();
      CFG.menuManager.setViewID(Menu.eCREATE_SCENARIO_EVENTS);
      CFG.menuManager.setVisibleCreateScenario_Events_Edit(true);
      CFG.menuManager.setBackAnimation(true);
   }
}
