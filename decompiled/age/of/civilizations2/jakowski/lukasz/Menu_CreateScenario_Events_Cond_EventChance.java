package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import java.util.ArrayList;

public class Menu_CreateScenario_Events_Cond_EventChance extends SliderMenu {
   public Menu_CreateScenario_Events_Cond_EventChance() {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      int tY = CFG.PADDING;
      menuElements.add(new Button_Menu_LR_Line(null, -1, 0, tY, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT, true));
      menuElements.add(
         new Button_Menu_LR_Line(null, -1, 0, tY, CFG.GAME_WIDTH / 3, CFG.BUTTON_HEIGHT, true) {
            @Override
            public Color getColor(boolean isActive) {
               return CFG.eventsManager
                           .lCreateScenario_Event
                           .getTrigger(CFG.eventsManager.iCreateEvent_EditTriggerID)
                           .lConditions
                           .get(CFG.eventsManager.iCreateEvent_EditConditionID)
                        .conditionType
                     == Event_Type.AND
                  ? CFG.COLOR_TEXT_MODIFIER_POSITIVE
                  : super.getColor(isActive);
            }
         }
      );
      menuElements.add(
         new Button_Menu_LR_Line(null, -1, CFG.GAME_WIDTH / 3, tY, CFG.GAME_WIDTH / 3, CFG.BUTTON_HEIGHT, true) {
            @Override
            public Color getColor(boolean isActive) {
               return CFG.eventsManager
                           .lCreateScenario_Event
                           .getTrigger(CFG.eventsManager.iCreateEvent_EditTriggerID)
                           .lConditions
                           .get(CFG.eventsManager.iCreateEvent_EditConditionID)
                        .conditionType
                     == Event_Type.NOT
                  ? CFG.COLOR_TEXT_MODIFIER_POSITIVE
                  : super.getColor(isActive);
            }
         }
      );
      menuElements.add(
         new Button_Menu_LR_Line(null, -1, CFG.GAME_WIDTH - CFG.GAME_WIDTH / 3, tY, CFG.GAME_WIDTH - CFG.GAME_WIDTH / 3 * 2, CFG.BUTTON_HEIGHT, true) {
            @Override
            public Color getColor(boolean isActive) {
               return CFG.eventsManager
                           .lCreateScenario_Event
                           .getTrigger(CFG.eventsManager.iCreateEvent_EditTriggerID)
                           .lConditions
                           .get(CFG.eventsManager.iCreateEvent_EditConditionID)
                        .conditionType
                     == Event_Type.OR
                  ? CFG.COLOR_TEXT_MODIFIER_POSITIVE
                  : super.getColor(isActive);
            }
         }
      );
      int var3;
      menuElements.add(
         new Slider_BG(
            0,
            var3 = tY + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING * 2,
            CFG.GAME_WIDTH,
            CFG.BUTTON_HEIGHT - CFG.PADDING * 2,
            0,
            100,
            CFG.eventsManager
               .lCreateScenario_Event
               .getTrigger(CFG.eventsManager.iCreateEvent_EditTriggerID)
               .lConditions
               .get(CFG.eventsManager.iCreateEvent_EditConditionID)
               .getValue()
         ) {
            @Override
            public String getDrawText() {
               return this.getText() + ": " + this.getCurrent() + "%";
            }
         }
      );
      tY = var3 + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
      this.initMenuWithBackButton(
         new SliderMenuTitle(null, CFG.BUTTON_HEIGHT * 3 / 4, false, false),
         0,
         CFG.BUTTON_HEIGHT * 3 / 4,
         CFG.GAME_WIDTH,
         CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT * 3 / 4,
         menuElements
      );
      this.updateLanguage();
   }

   @Override
   public void updateLanguage() {
      this.getMenuElement(0).setText(CFG.langManager.get("Save"));
      this.getMenuElement(1).setText(CFG.langManager.get("AND"));
      this.getMenuElement(2).setText(CFG.langManager.get("NOT"));
      this.getMenuElement(3).setText(CFG.langManager.get("OR"));
      this.getMenuElement(4).setText(CFG.langManager.get("EventChance"));
      this.getTitle().setText(CFG.langManager.get("EventChance"));
   }

   @Override
   public final void actionElement(int iID) {
      switch (iID) {
         case 0:
            this.onBackPressed();
            break;
         case 1:
            CFG.eventsManager
                  .lCreateScenario_Event
                  .getTrigger(CFG.eventsManager.iCreateEvent_EditTriggerID)
                  .lConditions
                  .get(CFG.eventsManager.iCreateEvent_EditConditionID)
               .conditionType = Event_Type.AND;
            CFG.toast.setInView(this.getMenuElement(iID).getText(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE);
            break;
         case 2:
            CFG.eventsManager
                  .lCreateScenario_Event
                  .getTrigger(CFG.eventsManager.iCreateEvent_EditTriggerID)
                  .lConditions
                  .get(CFG.eventsManager.iCreateEvent_EditConditionID)
               .conditionType = Event_Type.NOT;
            CFG.toast.setInView(this.getMenuElement(iID).getText(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE);
            break;
         case 3:
            CFG.eventsManager
                  .lCreateScenario_Event
                  .getTrigger(CFG.eventsManager.iCreateEvent_EditTriggerID)
                  .lConditions
                  .get(CFG.eventsManager.iCreateEvent_EditConditionID)
               .conditionType = Event_Type.OR;
            CFG.toast.setInView(this.getMenuElement(iID).getText(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE);
            break;
         case 4:
            CFG.eventsManager
               .lCreateScenario_Event
               .getTrigger(CFG.eventsManager.iCreateEvent_EditTriggerID)
               .lConditions
               .get(CFG.eventsManager.iCreateEvent_EditConditionID)
               .setValue(this.getMenuElement(iID).getCurrent());
      }
   }

   @Override
   public final void onBackPressed() {
      CFG.menuManager.setViewID(Menu.eCREATE_SCENARIO_EVENTS_TRIGGER);
      CFG.menuManager.setBackAnimation(true);
   }
}
