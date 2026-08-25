package age.of.civilizations2.jakowski.lukasz;

import java.util.ArrayList;
import java.util.List;

public class Event_Outcome_LiberateVassal extends Event_Outcome {
   public int iCivID = -1;

   Event_Outcome_LiberateVassal() {
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
   public void outcomeAction() {
      if (this.canMakeAction()) {
         CFG.game.getCiv(this.getCivID()).setPuppetOfCivID(this.getCivID());
      }
   }

   public boolean canMakeAction() {
      try {
         return this.getCivID() >= 0 && this.getCivID() < CFG.game.getCivsSize() && CFG.game.getCiv(this.getCivID()).getPuppetOfCivID() != this.getCivID();
      } catch (IndexOutOfBoundsException var2) {
         return false;
      }
   }

   @Override
   public List<MenuElement_Hover_v2_Element2> getHoverText() {
      try {
         ArrayList<MenuElement_Hover_v2_Element2> tElements = new ArrayList<>();
         ArrayList<MenuElement_Hover_v2_Element_Type> tData = new ArrayList<>();
         if (this.canMakeAction()) {
            tData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Independence") + ":", CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
            tData.add(new MenuElement_Hover_v2_Element_Type_Flag(this.getCivID(), CFG.PADDING, CFG.PADDING));
            tData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.game.getCiv(this.getCivID()).getCivName()));
            tElements.add(new MenuElement_Hover_v2_Element2(tData));
            tData.clear();
         }

         return tElements;
      } catch (IndexOutOfBoundsException var3) {
      } catch (NullPointerException var4) {
      }

      return new ArrayList<>();
   }

   @Override
   public String getConditionText() {
      try {
         return CFG.langManager.get("LiberateAVassal") + ": " + CFG.game.getCiv(this.getCivID()).getCivName();
      } catch (IndexOutOfBoundsException var2) {
         return CFG.langManager.get("LiberateAVassal");
      }
   }

   @Override
   public final void editViewID() {
      CFG.menuManager.setViewID(Menu.eCREATE_SCENARIO_EVENTS_OUT_LIBERATEVASSAL);
   }
}
