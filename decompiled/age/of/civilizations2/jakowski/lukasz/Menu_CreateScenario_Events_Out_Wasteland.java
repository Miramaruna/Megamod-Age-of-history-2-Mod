package age.of.civilizations2.jakowski.lukasz;

import java.util.ArrayList;

public class Menu_CreateScenario_Events_Out_Wasteland extends SliderMenu {
   public Menu_CreateScenario_Events_Out_Wasteland() {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      int tY = CFG.PADDING;
      menuElements.add(new Button_Menu_LR_Line(null, -1, 0, tY, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT, true));
      menuElements.add(new Button_Menu(null, (int)(50.0F * CFG.GUI_SCALE), 0, tY, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT, true));
      int var3;
      menuElements.add(
         new Slider_BG(
            0,
            var3 = tY + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING * 2,
            CFG.GAME_WIDTH,
            CFG.BUTTON_HEIGHT - CFG.PADDING * 2,
            0,
            20,
            CFG.eventsManager.lCreateScenario_Event.lDecisions.get(CFG.eventsManager.iCreateEvent_EditTriggerID)
                  .lOutcomes
                  .get(CFG.eventsManager.iCreateEvent_EditConditionID)
                  .getValue()
               * 10
         ) {
            @Override
            public String getDrawText() {
               return this.getCurrent() / 10 == 0 ? CFG.langManager.get("WontBeAWastelandAnymore") : CFG.langManager.get("WillTurnIntoAWasteland");
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
      this.getMenuElement(1)
         .setText(
            CFG.langManager.get("SelectProvinces")
               + ": "
               + CFG.eventsManager.lCreateScenario_Event.lDecisions.get(CFG.eventsManager.iCreateEvent_EditTriggerID)
                  .lOutcomes
                  .get(CFG.eventsManager.iCreateEvent_EditConditionID)
                  .getProvinces()
                  .size()
         );
      this.getTitle().setText(CFG.langManager.get("UpdateWastelandProvinces"));
   }

   @Override
   public final void actionElement(int iID) {
      switch (iID) {
         case 0:
            this.onBackPressed();
            break;
         case 1:
            CFG.game.getSelectedProvinces().clearSelectedProvinces();

            for (int i = 0;
               i
                  < CFG.eventsManager.lCreateScenario_Event.lDecisions.get(CFG.eventsManager.iCreateEvent_EditTriggerID)
                     .lOutcomes
                     .get(CFG.eventsManager.iCreateEvent_EditConditionID)
                     .getProvinces()
                     .size();
               i++
            ) {
               CFG.game
                  .getSelectedProvinces()
                  .addProvince(
                     CFG.eventsManager.lCreateScenario_Event.lDecisions.get(CFG.eventsManager.iCreateEvent_EditTriggerID)
                        .lOutcomes
                        .get(CFG.eventsManager.iCreateEvent_EditConditionID)
                        .getProvinces()
                        .get(i)
                  );
            }

            CFG.eventsManager.eSelectCivAction = Event_SelectCivAction.OUT_SELECTPROVICNES_WASTELAND;
            CFG.menuManager.setViewID(Menu.eCREATE_SCENARIO_EVENTS_SELECT_PROVINCES);
            break;
         case 2:
            CFG.eventsManager.lCreateScenario_Event.lDecisions.get(CFG.eventsManager.iCreateEvent_EditTriggerID)
               .lOutcomes
               .get(CFG.eventsManager.iCreateEvent_EditConditionID)
               .setValue(this.getMenuElement(iID).getCurrent() / 10);
      }
   }

   @Override
   public final void onBackPressed() {
      CFG.menuManager.setViewID(Menu.eCREATE_SCENARIO_EVENTS_DECISION);
      CFG.menuManager.setBackAnimation(true);
   }
}
