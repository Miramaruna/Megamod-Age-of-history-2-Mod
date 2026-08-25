package age.of.civilizations2.jakowski.lukasz;

import java.util.ArrayList;
import java.util.List;

public class Event_Conditions_HaveArmy extends Event_Conditions {
   public int iCivID = -1;
   public List<Integer> lProvinces = new ArrayList<>();
   public int iPercentage = 100;

   Event_Conditions_HaveArmy() {
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
      return this.iPercentage;
   }

   @Override
   public void setValue(int nValue) {
      this.iPercentage = nValue;
   }

   @Override
   public List<Integer> getProvinces() {
      return this.lProvinces;
   }

   @Override
   public void setProvinces(List<Integer> nProvinces) {
      this.lProvinces.clear();

      for (int i = 0; i < nProvinces.size(); i++) {
         this.lProvinces.add(nProvinces.get(i));
      }
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
      int numOut = 0;

      try {
         for (int i = 0; i < this.lProvinces.size(); i++) {
            if (CFG.game.getProvince(this.lProvinces.get(i)).getArmyCivID(this.getCivID()) > 0) {
               numOut++;
            }
         }
      } catch (IndexOutOfBoundsException var3) {
         return false;
      }

      return !((float)numOut / this.lProvinces.size() > this.getValue() / 100.0F);
   }

   @Override
   public String getConditionText() {
      try {
         return CFG.langManager.get("HaveArmy") + ": " + CFG.game.getCiv(this.getCivID()).getCivName();
      } catch (IndexOutOfBoundsException var2) {
         return CFG.langManager.get("HaveArmy");
      }
   }

   @Override
   public final void editViewID() {
      CFG.menuManager.setViewID(Menu.eCREATE_SCENARIO_EVENTS_COND_HAVEARMY);
   }
}
