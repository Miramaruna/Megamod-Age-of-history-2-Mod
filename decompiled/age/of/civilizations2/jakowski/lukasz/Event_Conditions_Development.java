package age.of.civilizations2.jakowski.lukasz;

import java.util.ArrayList;
import java.util.List;

public class Event_Conditions_Development extends Event_Conditions {
   public List<Integer> lProvinces = new ArrayList<>();
   public int iValue = 0;

   Event_Conditions_Development() {
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
         for (int i = 0; i < this.getProvinces().size(); i++) {
            if (CFG.game.getProvince(this.getProvinces().get(i)).getDevelopmentLevel() < this.getValue() / 100.0F) {
               return false;
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
         return CFG.langManager.get("DevelopmentLevel") + " >= " + this.getValue() / 100.0F;
      } catch (IndexOutOfBoundsException var2) {
         return CFG.langManager.get("DevelopmentLevel");
      }
   }

   @Override
   public final void editViewID() {
      CFG.menuManager.setViewID(Menu.eCREATE_SCENARIO_EVENTS_COND_DEVELOPMENT);
   }
}
