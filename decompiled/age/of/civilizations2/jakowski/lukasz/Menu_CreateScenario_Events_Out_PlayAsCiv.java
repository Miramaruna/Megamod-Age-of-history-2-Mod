package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

public class Menu_CreateScenario_Events_Out_PlayAsCiv extends SliderMenu {
   protected Menu_CreateScenario_Events_Out_PlayAsCiv() {
      ArrayList var1 = new ArrayList();
      int var2 = CFG.PADDING;
      var1.add(new Button_Menu_LR_Line(null, -1, 0, var2, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT, true));
      var1.add(new Button_Menu(null, (int)(CFG.GUI_SCALE * 50.0F), 0, var2, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT, true));
      var2 += ((MenuElement)var1.get(var1.size() - 1)).getHeight() + CFG.PADDING;
      var1.add(new Button_Menu(null, (int)(CFG.GUI_SCALE * 50.0F), 0, var2, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT, true));
      ((MenuElement)var1.get(var1.size() - 1)).getHeight();
      var2 = CFG.PADDING;
      this.initMenuWithBackButton(
         new SliderMenuTitle(null, CFG.BUTTON_HEIGHT * 3 / 4, false, false),
         0,
         CFG.BUTTON_HEIGHT * 3 / 4,
         CFG.GAME_WIDTH,
         CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT * 3 / 4,
         var1
      );
      this.updateLanguage();
   }

   @Override
   protected final void actionElement(int var1) {
      switch (var1) {
         case 0:
            this.onBackPressed();
            break;
         case 1:
            CFG.eventsManager.eSelectCivAction = Event_SelectCivAction.OUT_SELECTCIV_PLAYASCIV_A;
            CFG.menuManager.setViewID(Menu.eCREATE_SCENARIO_EVENTS_SELECT_CIV);
            break;
         case 2:
            CFG.eventsManager.eSelectCivAction = Event_SelectCivAction.OUT_SELECTCIV_PLAYASCIV_B;
            CFG.menuManager.setViewID(Menu.eCREATE_SCENARIO_EVENTS_SELECT_CIV);
      }
   }

   @Override
   protected void draw(SpriteBatch var1, int var2, int var3, boolean var4) {
      super.beginClip(var1, var2, var3, var4);
      super.drawMenu(var1, var2, var3, var4);

      try {
         CFG.game
            .getCiv(
               CFG.eventsManager.lCreateScenario_Event.lDecisions.get(CFG.eventsManager.iCreateEvent_EditTriggerID)
                  .lOutcomes
                  .get(CFG.eventsManager.iCreateEvent_EditConditionID)
                  .getCivID()
            )
            .getFlag()
            .draw(
               var1,
               this.getMenuElement(1).getPosX() + this.getMenuElement(1).getTextPos() / 2 - CFG.CIV_FLAG_WIDTH / 2 + var2,
               -CFG.game
                     .getCiv(
                        CFG.eventsManager.lCreateScenario_Event.lDecisions.get(CFG.eventsManager.iCreateEvent_EditTriggerID)
                           .lOutcomes
                           .get(CFG.eventsManager.iCreateEvent_EditConditionID)
                           .getCivID()
                     )
                     .getFlag()
                     .getHeight()
                  + this.getMenuElement(1).getPosY()
                  + this.getMenuPosY()
                  + this.getMenuElement(1).getHeight() / 2
                  - CFG.CIV_FLAG_HEIGHT / 2
                  + var3,
               CFG.CIV_FLAG_WIDTH,
               CFG.CIV_FLAG_HEIGHT
            );
      } catch (IndexOutOfBoundsException var7) {
         ImageManager.getImage(Images.randomCivilizationFlag)
            .draw(
               var1,
               this.getMenuElement(1).getPosX() + this.getMenuElement(1).getTextPos() / 2 - CFG.CIV_FLAG_WIDTH / 2 + var2,
               this.getMenuElement(1).getPosY()
                  + this.getMenuPosY()
                  - ImageManager.getImage(Images.randomCivilizationFlag).getHeight()
                  + this.getMenuElement(1).getHeight() / 2
                  - CFG.CIV_FLAG_HEIGHT / 2
                  + var3,
               CFG.CIV_FLAG_WIDTH,
               CFG.CIV_FLAG_HEIGHT
            );
      }

      ImageManager.getImage(Images.flag_rect)
         .draw(
            var1,
            this.getMenuElement(1).getPosX() + this.getMenuElement(1).getTextPos() / 2 - CFG.CIV_FLAG_WIDTH / 2 + var2,
            this.getMenuElement(1).getPosY() + this.getMenuPosY() + this.getMenuElement(1).getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 + var3
         );

      try {
         CFG.game
            .getCiv(
               CFG.eventsManager.lCreateScenario_Event.lDecisions.get(CFG.eventsManager.iCreateEvent_EditTriggerID)
                  .lOutcomes
                  .get(CFG.eventsManager.iCreateEvent_EditConditionID)
                  .getCivID2()
            )
            .getFlag()
            .draw(
               var1,
               this.getMenuElement(2).getPosX() + this.getMenuElement(2).getTextPos() / 2 - CFG.CIV_FLAG_WIDTH / 2 + var2,
               -CFG.game
                     .getCiv(
                        CFG.eventsManager.lCreateScenario_Event.lDecisions.get(CFG.eventsManager.iCreateEvent_EditTriggerID)
                           .lOutcomes
                           .get(CFG.eventsManager.iCreateEvent_EditConditionID)
                           .getCivID2()
                     )
                     .getFlag()
                     .getHeight()
                  + this.getMenuElement(2).getPosY()
                  + this.getMenuPosY()
                  + this.getMenuElement(2).getHeight() / 2
                  - CFG.CIV_FLAG_HEIGHT / 2
                  + var3,
               CFG.CIV_FLAG_WIDTH,
               CFG.CIV_FLAG_HEIGHT
            );
      } catch (IndexOutOfBoundsException var6) {
         ImageManager.getImage(Images.randomCivilizationFlag)
            .draw(
               var1,
               this.getMenuElement(2).getPosX() + this.getMenuElement(2).getTextPos() / 2 - CFG.CIV_FLAG_WIDTH / 2 + var2,
               this.getMenuElement(2).getPosY()
                  + this.getMenuPosY()
                  - ImageManager.getImage(Images.randomCivilizationFlag).getHeight()
                  + this.getMenuElement(2).getHeight() / 2
                  - CFG.CIV_FLAG_HEIGHT / 2
                  + var3,
               CFG.CIV_FLAG_WIDTH,
               CFG.CIV_FLAG_HEIGHT
            );
      }

      ImageManager.getImage(Images.flag_rect)
         .draw(
            var1,
            this.getMenuElement(2).getPosX() + this.getMenuElement(2).getTextPos() / 2 - CFG.CIV_FLAG_WIDTH / 2 + var2,
            this.getMenuElement(2).getPosY() + this.getMenuPosY() + this.getMenuElement(2).getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 + var3
         );
      super.endClip(var1, var2, var3, var4);
   }

   @Override
   protected final void onBackPressed() {
      CFG.menuManager.setViewID(Menu.eCREATE_SCENARIO_EVENTS_DECISION);
      CFG.menuManager.setBackAnimation(true);
   }

   // $VF: Duplicated exception handlers to handle obfuscated exceptions
   @Override
   protected void updateLanguage() {
      this.getMenuElement(0).setText(CFG.langManager.get("Save"));

      label51: {
         MenuElement var1;
         String var2;
         label55: {
            try {
               var1 = this.getMenuElement(1);
               if (CFG.eventsManager.lCreateScenario_Event.lDecisions.get(CFG.eventsManager.iCreateEvent_EditTriggerID)
                     .lOutcomes
                     .get(CFG.eventsManager.iCreateEvent_EditConditionID)
                     .getCivID()
                  >= 0) {
                  StringBuilder var10 = new StringBuilder();
                  var2 = var10.append(CFG.langManager.get("Civilization"))
                     .append(": ")
                     .append(
                        CFG.game
                           .getCiv(
                              CFG.eventsManager.lCreateScenario_Event.lDecisions.get(CFG.eventsManager.iCreateEvent_EditTriggerID)
                                 .lOutcomes
                                 .get(CFG.eventsManager.iCreateEvent_EditConditionID)
                                 .getCivID()
                           )
                           .getCivName()
                     )
                     .toString();
                  break label55;
               }
            } catch (IndexOutOfBoundsException var8) {
               this.getMenuElement(1).setText(CFG.langManager.get("SelectCivilization"));
               break label51;
            }

            try {
               var2 = CFG.langManager.get("SelectCivilization");
            } catch (IndexOutOfBoundsException var7) {
               this.getMenuElement(1).setText(CFG.langManager.get("SelectCivilization"));
               break label51;
            }
         }

         try {
            var1.setText(var2);
         } catch (IndexOutOfBoundsException var4) {
            this.getMenuElement(1).setText(CFG.langManager.get("SelectCivilization"));
         }
      }

      label40: {
         MenuElement var9;
         String var11;
         label56: {
            try {
               var9 = this.getMenuElement(2);
               if (CFG.eventsManager.lCreateScenario_Event.lDecisions.get(CFG.eventsManager.iCreateEvent_EditTriggerID)
                     .lOutcomes
                     .get(CFG.eventsManager.iCreateEvent_EditConditionID)
                     .getCivID2()
                  >= 0) {
                  StringBuilder var12 = new StringBuilder();
                  var11 = var12.append(CFG.langManager.get("Civilization"))
                     .append(": ")
                     .append(
                        CFG.game
                           .getCiv(
                              CFG.eventsManager.lCreateScenario_Event.lDecisions.get(CFG.eventsManager.iCreateEvent_EditTriggerID)
                                 .lOutcomes
                                 .get(CFG.eventsManager.iCreateEvent_EditConditionID)
                                 .getCivID2()
                           )
                           .getCivName()
                     )
                     .toString();
                  break label56;
               }
            } catch (IndexOutOfBoundsException var6) {
               this.getMenuElement(2).setText(CFG.langManager.get("SelectCivilization"));
               break label40;
            }

            try {
               var11 = CFG.langManager.get("SelectCivilization");
            } catch (IndexOutOfBoundsException var5) {
               this.getMenuElement(2).setText(CFG.langManager.get("SelectCivilization"));
               break label40;
            }
         }

         try {
            var9.setText(var11);
         } catch (IndexOutOfBoundsException var3) {
            this.getMenuElement(2).setText(CFG.langManager.get("SelectCivilization"));
         }
      }

      this.getTitle().setText(CFG.langManager.get("PlayAsCiv"));
   }
}
