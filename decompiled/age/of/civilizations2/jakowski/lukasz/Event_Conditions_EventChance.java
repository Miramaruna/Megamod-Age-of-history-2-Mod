package age.of.civilizations2.jakowski.lukasz;

public class Event_Conditions_EventChance extends Event_Conditions {
   public int iValue = 40;

   Event_Conditions_EventChance() {
   }

   @Override
   public int getValue() {
      return this.iValue;
   }

   @Override
   public void setValue(int iValue) {
      this.iValue = iValue;
   }

   @Override
   public boolean outCondition() {
      return CFG.oR.nextInt(100) <= this.getValue();
   }

   @Override
   public String getConditionText() {
      try {
         return CFG.langManager.get("EventChance") + ": " + this.getValue() + "%";
      } catch (IndexOutOfBoundsException var2) {
         return CFG.langManager.get("EventChance");
      }
   }

   @Override
   public final void editViewID() {
      CFG.menuManager.setViewID(Menu.eCREATE_SCENARIO_EVENTS_COND_EVENTCHANCE);
   }
}
