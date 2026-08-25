package age.of.civilizations2.jakowski.lukasz;

import java.util.ArrayList;

public class Menu_CreateScenario_Events_Out_TriggerAnotherEvent extends SliderMenu {
   public Menu_CreateScenario_Events_Out_TriggerAnotherEvent() {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      int tY = CFG.PADDING;
      menuElements.add(new Button_Menu_LR_Line(null, -1, 0, tY, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT, true));
      menuElements.add(new Button_Menu(null, (int)(50.0F * CFG.GUI_SCALE), 0, tY, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT, true));
      tY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
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
      this.getMenuElement(1)
         .setText(
            CFG.langManager.get("Event")
               + ": "
               + CFG.eventsManager.lCreateScenario_Event.lDecisions.get(CFG.eventsManager.iCreateEvent_EditTriggerID)
                  .lOutcomes
                  .get(CFG.eventsManager.iCreateEvent_EditConditionID)
                  .getText()
         );
      this.getTitle().setText(CFG.langManager.get("TriggerAnotherEvent"));
   }

   @Override
   public final void actionElement(int iID) {
      switch (iID) {
         case 0:
            this.onBackPressed();
            break;
         case 1:
            CFG.eventsManager.eSelectCivAction = Event_SelectCivAction.OUT_SELECTEVENT;
            CFG.menuManager.setViewID(Menu.eCREATE_SCENARIO_EVENTS_OUT_SELECT_EVENT);
      }
   }

   @Override
   public final void onBackPressed() {
      CFG.menuManager.setViewID(Menu.eCREATE_SCENARIO_EVENTS_DECISION);
      CFG.menuManager.setBackAnimation(true);
   }
}
