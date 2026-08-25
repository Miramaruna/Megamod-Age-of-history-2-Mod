package age.of.civilizations2.jakowski.lukasz;

import java.util.ArrayList;

public class Event_Conditions_NumOfNeighbors extends Event_Conditions {
   public int iCivID = -1;
   public int iValue = 0;

   Event_Conditions_NumOfNeighbors() {
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
         ArrayList<Integer> lNeigh = new ArrayList<>();

         for (int i = 0; i < CFG.game.getCiv(this.getCivID()).getNumOfProvinces(); i++) {
            for (int j = 0; j < CFG.game.getProvince(CFG.game.getCiv(this.getCivID()).getProvinceID(i)).getNeighboringProvincesSize(); j++) {
               if (CFG.game.getProvince(CFG.game.getProvince(CFG.game.getCiv(this.getCivID()).getProvinceID(i)).getNeighboringProvinces(j)).getCivID() > 0
                  && CFG.game.getProvince(CFG.game.getProvince(CFG.game.getCiv(this.getCivID()).getProvinceID(i)).getNeighboringProvinces(j)).getCivID()
                     != this.getCivID()) {
                  boolean tAdd = true;

                  for (int k = 0; k < lNeigh.size(); k++) {
                     if (lNeigh.get(k)
                        == CFG.game.getProvince(CFG.game.getProvince(CFG.game.getCiv(this.getCivID()).getProvinceID(i)).getNeighboringProvinces(j)).getCivID()) {
                        tAdd = false;
                        break;
                     }
                  }

                  if (tAdd) {
                     lNeigh.add(
                        CFG.game.getProvince(CFG.game.getProvince(CFG.game.getCiv(this.getCivID()).getProvinceID(i)).getNeighboringProvinces(j)).getCivID()
                     );
                  }
               }
            }
         }

         return lNeigh.size() >= this.getValue();
      } catch (IndexOutOfBoundsException var6) {
         return false;
      }
   }

   @Override
   public String getConditionText() {
      try {
         return CFG.langManager.get("NumberOfNeighbors") + " >= " + this.getValue() + ", " + CFG.game.getCiv(this.getCivID()).getCivName();
      } catch (IndexOutOfBoundsException var2) {
         return CFG.langManager.get("NumberOfNeighbors");
      }
   }

   @Override
   public final void editViewID() {
      CFG.menuManager.setViewID(Menu.eCREATE_SCENARIO_EVENTS_COND_NUMOFNEIGHBORS);
   }
}
