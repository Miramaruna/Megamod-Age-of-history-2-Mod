package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

public class Menu_CreateScenario_Events_Trigger extends Menu_CreateScenario {
   public String assignProvinces;
   public int iStepWidth;

   public Menu_CreateScenario_Events_Trigger() {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      menuElements.add(new Button_Game(null, -1, CFG.PADDING, CFG.PADDING, true));
      menuElements.add(new Button_Game(null, -1, CFG.GAME_WIDTH - CFG.BUTTON_WIDTH - CFG.PADDING, CFG.PADDING, true));
      int tY = CFG.BUTTON_HEIGHT + CFG.PADDING * 3;
      menuElements.add(new Button_Menu_LR_Line(null, -1, 0, tY, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT, true));
      tY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;

      for (int i = 0; i < CFG.eventsManager.lCreateScenario_Event.getTrigger(CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.size(); i++) {
         menuElements.add(
            new Button_Menu(
               CFG.eventsManager.lCreateScenario_Event.getTrigger(CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.get(i).getConditionText(),
               (int)(50.0F * CFG.GUI_SCALE),
               0,
               tY,
               CFG.GAME_WIDTH - CFG.BUTTON_WIDTH * 2,
               CFG.BUTTON_HEIGHT,
               true
            )
         );
         menuElements.add(
            new Button_Menu_LR_Line(
               CFG.eventsManager
                  .getEventTypeText(
                     CFG.eventsManager.lCreateScenario_Event.getTrigger(CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.get(i).conditionType
                  ),
               -1,
               CFG.GAME_WIDTH - CFG.BUTTON_WIDTH * 2,
               tY,
               CFG.BUTTON_WIDTH,
               CFG.BUTTON_HEIGHT,
               true
            ) {
               @Override
               public void buildElementHover() {
                  ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
                  ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
                  nData.add(new MenuElement_Hover_v2_Element_Type_Text(this.getText(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                  nElements.add(new MenuElement_Hover_v2_Element2(nData));
                  nData.clear();
                  this.menuElementHover = new MenuElement_Hover_v2(nElements);
               }
            }
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
      this.assignProvinces = CFG.langManager.get("Conditions");
      CFG.glyphLayout.setText(CFG.fontMain, this.assignProvinces);
      this.iStepWidth = (int)CFG.glyphLayout.width;
      this.getMenuElement(2).setText(CFG.langManager.get("AddNewCondition"));
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

   @Override
   public final void actionElement(int iID) {
      switch (iID) {
         case 0:
         case 1:
            this.onBackPressed();
            return;
         case 2:
            CFG.menuManager.setViewID(Menu.eCREATE_SCENARIO_EVENTS_ADD_NEW_CONDITION);
            return;
         default:
            iID -= 3;
            if (iID % 3 == 0) {
               CFG.eventsManager.iCreateEvent_EditConditionID = iID / 3;
               CFG.eventsManager
                  .lCreateScenario_Event
                  .getTrigger(CFG.eventsManager.iCreateEvent_EditTriggerID)
                  .lConditions
                  .get(CFG.eventsManager.iCreateEvent_EditConditionID)
                  .editViewID();
            } else if (iID % 3 == 1) {
               CFG.eventsManager.lCreateScenario_Event.getTrigger(CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.get(iID / 3).conditionType = CFG.eventsManager
                           .lCreateScenario_Event
                           .getTrigger(CFG.eventsManager.iCreateEvent_EditTriggerID)
                           .lConditions
                           .get(iID / 3)
                        .conditionType
                     == Event_Type.AND
                  ? Event_Type.NOT
                  : (
                     CFG.eventsManager.lCreateScenario_Event.getTrigger(CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.get(iID / 3).conditionType
                           == Event_Type.NOT
                        ? Event_Type.OR
                        : Event_Type.AND
                  );
               this.getMenuElement(iID + 3)
                  .setText(
                     CFG.eventsManager
                        .getEventTypeText(
                           CFG.eventsManager.lCreateScenario_Event.getTrigger(CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.get(iID / 3).conditionType
                        )
                  );
               CFG.toast.setInView(this.getMenuElement(iID + 3).getText(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE);
            } else {
               CFG.eventsManager.lCreateScenario_Event.getTrigger(CFG.eventsManager.iCreateEvent_EditTriggerID).lConditions.remove(iID / 3);
               CFG.menuManager.setViewID(Menu.eCREATE_SCENARIO_EVENTS_TRIGGER);
            }
      }
   }

   @Override
   public void onBackPressed() {
      CFG.eventsManager.lCreateScenario_Event.checkTriggers();
      CFG.menuManager.setViewID(Menu.eCREATE_SCENARIO_EVENTS);
      CFG.menuManager.setVisibleCreateScenario_Events_Edit(true);
      CFG.menuManager.setBackAnimation(true);
   }
}
