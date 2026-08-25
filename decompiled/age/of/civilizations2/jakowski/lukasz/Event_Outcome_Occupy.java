package age.of.civilizations2.jakowski.lukasz;

import java.util.ArrayList;
import java.util.List;

public class Event_Outcome_Occupy extends Event_Outcome {
   public int iCivID = -1;
   public int iCivID_ControlledBy = -1;
   public List<Integer> lProvinces = new ArrayList<>();

   Event_Outcome_Occupy() {
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
      return this.iCivID_ControlledBy;
   }

   @Override
   public void setCivID2(int nCivID) {
      this.iCivID_ControlledBy = nCivID;
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
      boolean out = false;
      if (this.iCivID == nRemovedCivID) {
         this.iCivID = -1;
         out = true;
      } else if (nRemovedCivID < this.iCivID) {
         this.iCivID--;
      }

      if (this.iCivID_ControlledBy == nRemovedCivID) {
         this.iCivID_ControlledBy = -1;
         out = true;
      } else if (nRemovedCivID < this.iCivID_ControlledBy) {
         this.iCivID--;
      }

      return out;
   }

   @Override
   public void outcomeAction() {
      if (this.getCivID() >= 0 && this.getCivID() < CFG.game.getCivsSize()) {
         for (int i = 0; i < this.lProvinces.size(); i++) {
            try {
               if (this.canMakeAction(i)) {
                  CFG.game.getProvince(this.lProvinces.get(i)).setCivID(this.getCivID(), false);
               }
            } catch (IndexOutOfBoundsException var3) {
            }
         }
      }

      CFG.gameAction.updateCivsHappiness(this.getCivID());
      if (this.getCivID2() > 0) {
         CFG.gameAction.updateCivsHappiness(this.getCivID2());
      }

      if (CFG.game.getCiv(this.getCivID()).getCapitalProvinceID() < 0
         || CFG.game.getProvince(CFG.game.getCiv(this.getCivID()).getCapitalProvinceID()).getCivID() != this.getCivID()) {
         CFG.game.moveCapitalToTheLargestCity(this.getCivID());
      }

      CFG.game.buildCivilizationRegions(this.getCivID());
      if (this.getCivID2() > 0) {
         CFG.game.buildCivilizationRegions(this.getCivID2());
      }
   }

   public boolean canMakeAction(int i) {
      try {
         return !CFG.game.getProvince(this.getProvinces().get(i)).getSeaProvince()
            && CFG.game.getProvince(this.getProvinces().get(i)).getWasteland() < 0
            && (CFG.game.getProvince(this.getProvinces().get(i)).getCivID() == this.getCivID2() || this.getCivID2() < 0)
            && this.getCivID() != this.getCivID2();
      } catch (IndexOutOfBoundsException var3) {
         return false;
      }
   }

   @Override
   public List<MenuElement_Hover_v2_Element2> getHoverText() {
      ArrayList<MenuElement_Hover_v2_Element2> tElements = new ArrayList<>();
      ArrayList<MenuElement_Hover_v2_Element_Type> tData = new ArrayList<>();

      for (int i = 0; i < this.getProvinces().size(); i++) {
         if (this.canMakeAction(i)) {
            tData.add(new MenuElement_Hover_v2_Element_Type_Flag(this.getCivID()));
            tData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Occupy") + ": ", CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
            tData.add(
               new MenuElement_Hover_v2_Element_Type_Text(
                  ""
                     + (
                        CFG.game.getProvince(this.getProvinces().get(i)).getName().length() == 0
                           ? this.getProvinces().get(i)
                           : CFG.game.getProvince(this.getProvinces().get(i)).getName()
                     )
               )
            );
            tData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getProvince(this.getProvinces().get(i)).getCivID(), CFG.PADDING, 0));
            tElements.add(new MenuElement_Hover_v2_Element2(tData));
            tData.clear();
         }
      }

      return tElements;
   }

   @Override
   public String getConditionText() {
      try {
         return CFG.langManager.get("Occupy") + ": " + CFG.game.getCiv(this.getCivID()).getCivName();
      } catch (IndexOutOfBoundsException var2) {
         return CFG.langManager.get("Occupy");
      }
   }

   @Override
   public final void editViewID() {
      CFG.menuManager.setViewID(Menu.eCREATE_SCENARIO_EVENTS_OUT_OCCUPY);
   }
}
