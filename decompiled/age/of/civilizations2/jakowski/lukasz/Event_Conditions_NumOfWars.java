package age.of.civilizations2.jakowski.lukasz;

public class Event_Conditions_NumOfWars extends Event_Conditions {
   public int iCivID = -1;
   public int iValue = 0;

   Event_Conditions_NumOfWars() {
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
         int tNum = 0;

         for (int i = 0; i < CFG.game.getCivsSize(); i++) {
            if (i != this.getCivID() && (int)CFG.game.getCivRelation_OfCivB(this.getCivID(), i) == -100) {
               tNum++;
            }
         }

         return tNum >= this.getValue();
      } catch (IndexOutOfBoundsException var3) {
         return false;
      }
   }

   @Override
   public String getConditionText() {
      try {
         return CFG.langManager.get("NumberOfWars") + " >= " + this.getValue() + ", " + CFG.game.getCiv(this.getCivID()).getCivName();
      } catch (IndexOutOfBoundsException var2) {
         return CFG.langManager.get("NumberOfWars");
      }
   }

   @Override
   public final void editViewID() {
      CFG.menuManager.setViewID(Menu.eCREATE_SCENARIO_EVENTS_COND_NUMOFWARS);
   }
}
