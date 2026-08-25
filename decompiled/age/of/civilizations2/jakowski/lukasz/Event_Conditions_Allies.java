package age.of.civilizations2.jakowski.lukasz;

public class Event_Conditions_Allies extends Event_Conditions {
   public int iCivID = -1;
   public int iCivID2 = -1;

   Event_Conditions_Allies() {
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
   public int getCivID2() {
      return this.iCivID2;
   }

   @Override
   public void setCivID2(int nCivID) {
      this.iCivID2 = nCivID;
   }

   @Override
   public boolean updateCivIDAfterRemove(int nRemovedCivID) {
      boolean out = false;
      if (this.iCivID == nRemovedCivID) {
         this.iCivID = -1;
         out = true;
      } else if (nRemovedCivID < this.iCivID) {
         this.iCivID--;
      }

      if (this.iCivID2 == nRemovedCivID) {
         this.iCivID2 = -1;
         out = true;
      } else if (nRemovedCivID < this.iCivID2) {
         this.iCivID2--;
      }

      return out;
   }

   @Override
   public boolean outCondition() {
      try {
         return CFG.game.getCiv(this.getCivID()).getAllianceID() > 0
            && CFG.game.getCiv(this.getCivID()).getAllianceID() == CFG.game.getCiv(this.getCivID2()).getAllianceID();
      } catch (IndexOutOfBoundsException var2) {
         return false;
      }
   }

   @Override
   public String getConditionText() {
      try {
         return CFG.langManager.get("Allies") + ": " + CFG.game.getCiv(this.getCivID()).getCivName() + " - " + CFG.game.getCiv(this.getCivID2()).getCivName();
      } catch (IndexOutOfBoundsException var2) {
         return CFG.langManager.get("Allies");
      }
   }

   @Override
   public final void editViewID() {
      CFG.menuManager.setViewID(Menu.eCREATE_SCENARIO_EVENTS_COND_ALLIES);
   }
}
