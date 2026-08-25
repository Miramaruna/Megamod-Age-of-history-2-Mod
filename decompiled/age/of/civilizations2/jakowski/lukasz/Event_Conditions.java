package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Event_Conditions implements Serializable {
   public static final long serialVersionUID = 0L;
   public Event_Type conditionType = Event_Type.AND;

   Event_Conditions() {
   }

   public int getCivID() {
      return -1;
   }

   public void setCivID(int nCivID) {
   }

   public int getCivID2() {
      return -1;
   }

   public void setCivID2(int nCivID) {
   }

   public int getValue() {
      return -1;
   }

   public void setValue(int nValue) {
   }

   public List<Integer> getProvinces() {
      return new ArrayList<>();
   }

   public void setProvinces(List<Integer> nProvinces) {
   }

   public boolean updateCivIDAfterRemove(int nRemovedCivID) {
      return false;
   }

   public String getText() {
      return "";
   }

   public void setText(String nText) {
   }

   public boolean outCondition() {
      return false;
   }

   public String getConditionText() {
      return "";
   }

   public void editViewID() {
      CFG.menuManager.setViewID(Menu.eCREATE_SCENARIO_EVENTS_COND_CIVEXIST);
   }
}
