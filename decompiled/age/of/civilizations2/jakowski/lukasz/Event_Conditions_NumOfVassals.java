package age.of.civilizations2.jakowski.lukasz;

public class Event_Conditions_NumOfVassals extends Event_Conditions {
   public int iCivID = -1;
   public int iValue = 0;

   Event_Conditions_NumOfVassals() {
   }

   @Override
   public int getCivID() {
      return this.iCivID;
   }

   @Override
   public void setCivID(int nCivID) {
      this.iCivID = nCivID;
   }

   @Override
   public int getValue() {
      return this.iValue;
   }

   @Override
   public void setValue(int nValue) {
      this.iValue = nValue;
   }

   @Override
   public boolean updateCivIDAfterRemove(int nRemovedCivID) {
      if (this.iCivID == nRemovedCivID) {
         this.iCivID = -1;
         return true;
      } else {
         if (nRemovedCivID < this.iCivID) {
            this.iCivID--;
         }

         return false;
      }
   }

   @Override
   public boolean outCondition() {
      try {
         int tNumOfVassals = 0;

         for (int i = 0; i < CFG.game.getCivsSize(); i++) {
            if (CFG.game.getCiv(i).getPuppetOfCivID() == this.getCivID() && i != this.getCivID()) {
               tNumOfVassals++;
            }
         }

         return tNumOfVassals >= this.getValue();
      } catch (IndexOutOfBoundsException var3) {
         return false;
      }
   }

   @Override
   public String getConditionText() {
      try {
         return CFG.langManager.get("NumberOfVassals") + " >= " + this.getValue() + ", " + CFG.game.getCiv(this.getCivID()).getCivName();
      } catch (IndexOutOfBoundsException var2) {
         return CFG.langManager.get("NumberOfVassals");
      }
   }

   @Override
   public final void editViewID() {
      CFG.menuManager.setViewID(Menu.eCREATE_SCENARIO_EVENTS_COND_NUMOFVASSALS);
   }
}
