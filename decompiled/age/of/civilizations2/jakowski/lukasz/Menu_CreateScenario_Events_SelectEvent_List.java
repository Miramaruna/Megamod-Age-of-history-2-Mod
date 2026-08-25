package age.of.civilizations2.jakowski.lukasz;

import java.util.ArrayList;

public class Menu_CreateScenario_Events_SelectEvent_List extends SliderMenu {
   public Menu_CreateScenario_Events_SelectEvent_List() {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      new ArrayList();
      new ArrayList();
      int nPosY = 0;

      for (int i = 0; i < CFG.eventsManager.getEventsSize(); i++) {
         menuElements.add(
            new Button_Menu(
               CFG.eventsManager.getEvent(i).getEventName()
                  + (
                     CFG.eventsManager.getEvent(i).getCivID() >= 0 && CFG.eventsManager.getEvent(i).getCivID() < CFG.game.getCivsSize()
                        ? ", " + CFG.game.getCiv(CFG.eventsManager.getEvent(i).getCivID()).getCivName()
                        : ""
                  )
                  + " ["
                  + CFG.eventsManager.getEvent(i).getEventTag()
                  + "]",
               50,
               0,
               CFG.BUTTON_HEIGHT * nPosY + CFG.PADDING * (nPosY + 1),
               CFG.GAME_WIDTH,
               CFG.BUTTON_HEIGHT,
               i != CFG.eventsManager.iCreateEvent_EditEventID
            )
         );
         nPosY++;
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
      CFG.eventsManager.lCreateScenario_Event.lDecisions.get(CFG.eventsManager.iCreateEvent_EditTriggerID)
         .lOutcomes
         .get(CFG.eventsManager.iCreateEvent_EditConditionID)
         .setText(CFG.eventsManager.getEvent(iID).getEventTag());
      CFG.eventsManager.selectCivBack();
   }
}
