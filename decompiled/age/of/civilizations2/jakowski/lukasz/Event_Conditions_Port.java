package age.of.civilizations2.jakowski.lukasz;

import java.util.ArrayList;
import java.util.List;

public class Event_Conditions_Port extends Event_Conditions {
   public List<Integer> lProvinces = new ArrayList<>();
   public int iValue = 0;

   Event_Conditions_Port() {
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
   public boolean outCondition() {
      try {
         if (this.getValue() == 0) {
            for (int i = 0; i < this.getProvinces().size(); i++) {
               if (CFG.game.getProvince(this.getProvinces().get(i)).getLevelOfPort() <= 0) {
                  return false;
               }
            }
         } else {
            for (int ix = 0; ix < this.getProvinces().size(); ix++) {
               if (CFG.game.getProvince(this.getProvinces().get(ix)).getLevelOfPort() > 0) {
                  return false;
               }
            }
         }

         return true;
      } catch (IndexOutOfBoundsException var2) {
         return false;
      }
   }

   @Override
   public String getConditionText() {
      try {
         return CFG.langManager.get("Port") + ": " + (this.getValue() == 0);
      } catch (IndexOutOfBoundsException var2) {
         return CFG.langManager.get("Port");
      }
   }

   @Override
   public final void editViewID() {
      CFG.menuManager.setViewID(Menu.eCREATE_SCENARIO_EVENTS_COND_PORT);
   }
}
