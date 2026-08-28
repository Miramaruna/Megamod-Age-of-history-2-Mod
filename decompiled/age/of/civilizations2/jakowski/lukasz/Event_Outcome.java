package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Event_Outcome implements Serializable {
   public static final long serialVersionUID = 0L;

   public LeaderOfCiv_GameData getLeader() {
      return null;
   }

   public void setLeader(LeaderOfCiv_GameData nLeader) {
   }

   public String getSound() {
      return null;
   }

   public void setPathToSound(String s) {
   }

   public int getProvId() {
      return -1;
   }

   public void setProvId(int i) {
   }

   public String getNewName() {
      return "";
   }

   public void setNewName(String s) {
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

   public String getText() {
      return "";
   }

   public void setText(String nText) {
   }

   public boolean updateCivIDAfterRemove(int nRemovedCivID) {
      return false;
   }

   public void outcomeAction() {
   }

   public List<MenuElement_Hover_v2_Element2> getHoverText() {
      return new ArrayList<>();
   }

   public String getConditionText() {
      return "";
   }

   public void editViewID() {
      CFG.menuManager.setViewID(Menu.eCREATE_SCENARIO_EVENTS_OUT_CHANGE_OWNER);
   }
}
