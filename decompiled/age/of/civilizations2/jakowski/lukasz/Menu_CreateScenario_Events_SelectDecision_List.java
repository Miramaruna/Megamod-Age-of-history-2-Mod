package age.of.civilizations2.jakowski.lukasz;

import java.util.ArrayList;

public class Menu_CreateScenario_Events_SelectDecision_List extends SliderMenu {
   public Menu_CreateScenario_Events_SelectDecision_List() {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      new ArrayList();
      new ArrayList();
      int nPosY = 0;

      for (int i = 0; i < CFG.eventsManager.getEventsSize(); i++) {
         for (int j = 0; j < CFG.eventsManager.getEvent(i).lDecisions.size(); j++) {
            CFG.eventsManager.iCreateEvent_Day = CFG.eventsManager.getEvent(i).getEventDate_Since().iEventDay;
            CFG.eventsManager.iCreateEvent_Month = CFG.eventsManager.getEvent(i).getEventDate_Since().iEventMonth;
            CFG.eventsManager.iCreateEvent_Year = CFG.eventsManager.getEvent(i).getEventDate_Since().iEventYear;
            menuElements.add(
               new Button_Menu_Descripted(
                  CFG.eventsManager.iCreateEvent_Year == 9999999 ? CFG.langManager.get("NoDate") : Game_Calendar.getCurrentDate_CreateEvent(),
                  CFG.eventsManager.getEvent(i).lDecisions.get(j).sTitle
                     + " - ["
                     + CFG.eventsManager.getEvent(i).getEventName()
                     + ", "
                     + (
                        CFG.eventsManager.getEvent(i).getCivID() >= 0 && CFG.eventsManager.getEvent(i).getCivID() < CFG.game.getCivsSize()
                           ? CFG.game.getCiv(CFG.eventsManager.getEvent(i).getCivID()).getCivName()
                           : CFG.langManager.get("AnyCivilization")
                     )
                     + "]",
                  (int)(50.0F * CFG.GUI_SCALE),
                  0,
                  CFG.BUTTON_HEIGHT * nPosY + CFG.PADDING * (nPosY + 1),
                  CFG.GAME_WIDTH,
                  CFG.BUTTON_HEIGHT,
                  i != CFG.eventsManager.iCreateEvent_EditEventID
               )
            );
            nPosY++;
         }
      }

      this.initMenu(
         null,
         0,
         CFG.BUTTON_HEIGHT * 3 / 4,
         CFG.GAME_WIDTH,
         CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT * 3 / 4 - CFG.BUTTON_HEIGHT - CFG.PADDING,
         menuElements,
         true,
         false
      );
      this.updateLanguage();
   }

   @Override
   public void updateLanguage() {
   }

   @Override
   public final void actionElement(int iID) {
      int tNum = 0;

      for (int i = 0; i < CFG.eventsManager.getEventsSize(); i++) {
         for (int j = 0; j < CFG.eventsManager.getEvent(i).lDecisions.size(); j++) {
            if (tNum++ == iID) {
               CFG.eventsManager
                  .lCreateScenario_Event
                  .getTrigger(CFG.eventsManager.iCreateEvent_EditTriggerID)
                  .lConditions
                  .get(CFG.eventsManager.iCreateEvent_EditConditionID)
                  .setText(CFG.eventsManager.getEvent(i).getEventTag() + "_" + j);
               CFG.eventsManager.selectCivBack();
               return;
            }
         }
      }

      CFG.eventsManager.selectCivBack();
   }
}
