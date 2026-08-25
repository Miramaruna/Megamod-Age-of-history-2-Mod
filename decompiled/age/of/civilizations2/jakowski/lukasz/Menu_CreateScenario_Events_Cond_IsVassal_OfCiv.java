package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

public class Menu_CreateScenario_Events_Cond_IsVassal_OfCiv extends SliderMenu {
   public Menu_CreateScenario_Events_Cond_IsVassal_OfCiv() {
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
         new Button_Menu(
            null,
            (int)(50.0F * CFG.GUI_SCALE),
            0,
            var3 = tY + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING,
            CFG.GAME_WIDTH,
            CFG.BUTTON_HEIGHT,
            true
         )
      );
      menuElements.add(
         new Button_Menu(
            null,
            (int)(50.0F * CFG.GUI_SCALE),
            0,
            tY = var3 + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING,
            CFG.GAME_WIDTH,
            CFG.BUTTON_HEIGHT,
            true
         )
      );
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
      this.getMenuElement(1).setText(CFG.langManager.get("AND"));
      this.getMenuElement(2).setText(CFG.langManager.get("NOT"));
      this.getMenuElement(3).setText(CFG.langManager.get("OR"));

      try {
         this.getMenuElement(4)
            .setText(
               CFG.eventsManager
                        .lCreateScenario_Event
                        .getTrigger(CFG.eventsManager.iCreateEvent_EditTriggerID)
                        .lConditions
                        .get(CFG.eventsManager.iCreateEvent_EditConditionID)
                        .getCivID()
                     > 0
                  ? CFG.langManager.get("Civilization")
                     + ": "
                     + CFG.game
                        .getCiv(
                           CFG.eventsManager
                              .lCreateScenario_Event
                              .getTrigger(CFG.eventsManager.iCreateEvent_EditTriggerID)
                              .lConditions
                              .get(CFG.eventsManager.iCreateEvent_EditConditionID)
                              .getCivID()
                        )
                        .getCivName()
                  : CFG.langManager.get("SelectCivilization")
            );
      } catch (IndexOutOfBoundsException var3) {
         this.getMenuElement(4).setText(CFG.langManager.get("SelectCivilization"));
      }

      try {
         this.getMenuElement(5)
            .setText(
               CFG.eventsManager
                        .lCreateScenario_Event
                        .getTrigger(CFG.eventsManager.iCreateEvent_EditTriggerID)
                        .lConditions
                        .get(CFG.eventsManager.iCreateEvent_EditConditionID)
                        .getCivID2()
                     > 0
                  ? CFG.langManager.get("Civilization")
                     + ": "
                     + CFG.game
                        .getCiv(
                           CFG.eventsManager
                              .lCreateScenario_Event
                              .getTrigger(CFG.eventsManager.iCreateEvent_EditTriggerID)
                              .lConditions
                              .get(CFG.eventsManager.iCreateEvent_EditConditionID)
                              .getCivID2()
                        )
                        .getCivName()
                  : CFG.langManager.get("SelectCivilization")
            );
      } catch (IndexOutOfBoundsException var2) {
         this.getMenuElement(5).setText(CFG.langManager.get("SelectCivilization"));
      }

      this.getTitle().setText(CFG.langManager.get("IsAVassalOfCiv"));
   }

   @Override
   public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      super.beginClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
      super.drawMenu(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
      int tempButtonID = 4;

      try {
         CFG.game
            .getCiv(
               CFG.eventsManager
                  .lCreateScenario_Event
                  .getTrigger(CFG.eventsManager.iCreateEvent_EditTriggerID)
                  .lConditions
                  .get(CFG.eventsManager.iCreateEvent_EditConditionID)
                  .getCivID()
            )
            .getFlag()
            .draw(
               oSB,
               this.getMenuElement(tempButtonID).getPosX() + this.getMenuElement(tempButtonID).getTextPos() / 2 - CFG.CIV_FLAG_WIDTH / 2 + iTranslateX,
               -CFG.game
                     .getCiv(
                        CFG.eventsManager
                           .lCreateScenario_Event
                           .getTrigger(CFG.eventsManager.iCreateEvent_EditTriggerID)
                           .lConditions
                           .get(CFG.eventsManager.iCreateEvent_EditConditionID)
                           .getCivID()
                     )
                     .getFlag()
                     .getHeight()
                  + this.getMenuElement(tempButtonID).getPosY()
                  + this.getMenuPosY()
                  + this.getMenuElement(tempButtonID).getHeight() / 2
                  - CFG.CIV_FLAG_HEIGHT / 2
                  + iTranslateY,
               CFG.CIV_FLAG_WIDTH,
               CFG.CIV_FLAG_HEIGHT
            );
      } catch (IndexOutOfBoundsException var8) {
         ImageManager.getImage(Images.randomCivilizationFlag)
            .draw(
               oSB,
               this.getMenuElement(tempButtonID).getPosX() + this.getMenuElement(tempButtonID).getTextPos() / 2 - CFG.CIV_FLAG_WIDTH / 2 + iTranslateX,
               this.getMenuElement(tempButtonID).getPosY()
                  - ImageManager.getImage(Images.randomCivilizationFlag).getHeight()
                  + this.getMenuPosY()
                  + this.getMenuElement(tempButtonID).getHeight() / 2
                  - CFG.CIV_FLAG_HEIGHT / 2
                  + iTranslateY,
               CFG.CIV_FLAG_WIDTH,
               CFG.CIV_FLAG_HEIGHT
            );
      }

      ImageManager.getImage(Images.flag_rect)
         .draw(
            oSB,
            this.getMenuElement(tempButtonID).getPosX() + this.getMenuElement(tempButtonID).getTextPos() / 2 - CFG.CIV_FLAG_WIDTH / 2 + iTranslateX,
            this.getMenuElement(tempButtonID).getPosY()
               + this.getMenuPosY()
               + this.getMenuElement(tempButtonID).getHeight() / 2
               - CFG.CIV_FLAG_HEIGHT / 2
               + iTranslateY
         );
      int var9 = 5;

      try {
         CFG.game
            .getCiv(
               CFG.eventsManager
                  .lCreateScenario_Event
                  .getTrigger(CFG.eventsManager.iCreateEvent_EditTriggerID)
                  .lConditions
                  .get(CFG.eventsManager.iCreateEvent_EditConditionID)
                  .getCivID2()
            )
            .getFlag()
            .draw(
               oSB,
               this.getMenuElement(var9).getPosX() + this.getMenuElement(var9).getTextPos() / 2 - CFG.CIV_FLAG_WIDTH / 2 + iTranslateX,
               -CFG.game
                     .getCiv(
                        CFG.eventsManager
                           .lCreateScenario_Event
                           .getTrigger(CFG.eventsManager.iCreateEvent_EditTriggerID)
                           .lConditions
                           .get(CFG.eventsManager.iCreateEvent_EditConditionID)
                           .getCivID2()
                     )
                     .getFlag()
                     .getHeight()
                  + this.getMenuElement(var9).getPosY()
                  + this.getMenuPosY()
                  + this.getMenuElement(var9).getHeight() / 2
                  - CFG.CIV_FLAG_HEIGHT / 2
                  + iTranslateY,
               CFG.CIV_FLAG_WIDTH,
               CFG.CIV_FLAG_HEIGHT
            );
      } catch (IndexOutOfBoundsException var7) {
         ImageManager.getImage(Images.randomCivilizationFlag)
            .draw(
               oSB,
               this.getMenuElement(var9).getPosX() + this.getMenuElement(var9).getTextPos() / 2 - CFG.CIV_FLAG_WIDTH / 2 + iTranslateX,
               this.getMenuElement(var9).getPosY()
                  - ImageManager.getImage(Images.randomCivilizationFlag).getHeight()
                  + this.getMenuPosY()
                  + this.getMenuElement(var9).getHeight() / 2
                  - CFG.CIV_FLAG_HEIGHT / 2
                  + iTranslateY,
               CFG.CIV_FLAG_WIDTH,
               CFG.CIV_FLAG_HEIGHT
            );
      }

      ImageManager.getImage(Images.flag_rect)
         .draw(
            oSB,
            this.getMenuElement(var9).getPosX() + this.getMenuElement(var9).getTextPos() / 2 - CFG.CIV_FLAG_WIDTH / 2 + iTranslateX,
            this.getMenuElement(var9).getPosY() + this.getMenuPosY() + this.getMenuElement(var9).getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 + iTranslateY
         );
      super.endClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
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
            CFG.eventsManager.eSelectCivAction = Event_SelectCivAction.SELECT_CIV_ISVASSALOFCIV;
            CFG.menuManager.setViewID(Menu.eCREATE_SCENARIO_EVENTS_SELECT_CIV);
            break;
         case 5:
            CFG.eventsManager.eSelectCivAction = Event_SelectCivAction.SELECT_CIV_ISVASSALOFCIV2;
            CFG.menuManager.setViewID(Menu.eCREATE_SCENARIO_EVENTS_SELECT_CIV);
      }
   }

   @Override
   public final void onBackPressed() {
      CFG.menuManager.setViewID(Menu.eCREATE_SCENARIO_EVENTS_TRIGGER);
      CFG.menuManager.setBackAnimation(true);
   }
}
