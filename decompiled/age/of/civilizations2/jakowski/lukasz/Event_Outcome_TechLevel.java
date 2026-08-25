package age.of.civilizations2.jakowski.lukasz;

import java.util.ArrayList;
import java.util.List;

public class Event_Outcome_TechLevel extends Event_Outcome {
   public int iCivID = -1;
   public int iValue = 0;

   Event_Outcome_TechLevel() {
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
   public void outcomeAction() {
      if (this.canMakeAction()) {
         CFG.game.getCiv(this.getCivID()).setTechnologyLevel(CFG.game.getCiv(this.getCivID()).getTechnologyLevel() + this.getValue() / 100.0F);
      }
   }

   public boolean canMakeAction() {
      try {
         return this.getValue() != 0 && this.getCivID() >= 0 && this.getCivID() < CFG.game.getCivsSize();
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
            tData.add(new MenuElement_Hover_v2_Element_Type_Flag(this.getCivID()));
            tData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("TechnologyLevel") + ": "));
            tData.add(
               new MenuElement_Hover_v2_Element_Type_Text(
                  (this.getValue() > 0 ? "+" : "") + this.getValue() / 100.0F + " ",
                  this.getValue() > 0 ? CFG.COLOR_TEXT_MODIFIER_POSITIVE : CFG.COLOR_TEXT_MODIFIER_NEGATIVE2
               )
            );
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
         return CFG.langManager.get("UpdateTechnologyLevel")
            + ": "
            + CFG.game.getCiv(this.getCivID()).getCivName()
            + ", "
            + (this.getValue() > 0 ? "+" : "")
            + this.getValue() / 100.0F;
      } catch (IndexOutOfBoundsException var2) {
         return CFG.langManager.get("UpdateTechnologyLevel");
      }
   }

   @Override
   public final void editViewID() {
      CFG.menuManager.setViewID(Menu.eCREATE_SCENARIO_EVENTS_OUT_TECHLEVEL);
   }
}
