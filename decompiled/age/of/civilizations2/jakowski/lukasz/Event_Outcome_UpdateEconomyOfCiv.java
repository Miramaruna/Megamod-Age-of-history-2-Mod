package age.of.civilizations2.jakowski.lukasz;

import java.util.ArrayList;
import java.util.List;

public class Event_Outcome_UpdateEconomyOfCiv extends Event_Outcome {
   public int iCivID = -1;
   public int iValue = 0;

   Event_Outcome_UpdateEconomyOfCiv() {
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
         for (int i = 0; i < CFG.game.getCiv(this.getCivID()).getNumOfProvinces(); i++) {
            CFG.game
               .getProvince(CFG.game.getCiv(this.getCivID()).getProvinceID(i))
               .setEconomy(
                  CFG.game.getProvince(CFG.game.getCiv(this.getCivID()).getProvinceID(i)).getEconomy()
                     + (int)(CFG.game.getProvince(CFG.game.getCiv(this.getCivID()).getProvinceID(i)).getEconomy() * (this.getValue() / 100.0F))
               );
         }
      }
   }

   public boolean canMakeAction() {
      try {
         return this.getCivID() > 0 && this.getCivID() < CFG.game.getCivsSize() && CFG.game.getCiv(this.getCivID()).getNumOfProvinces() > 0;
      } catch (IndexOutOfBoundsException var2) {
         return false;
      }
   }

   @Override
   public String getConditionText() {
      try {
         return CFG.langManager.get("UpdateEconomyOfCiv") + ": " + CFG.game.getCiv(this.getCivID()).getCivName() + ", " + this.getValue() + "%";
      } catch (IndexOutOfBoundsException var2) {
         return CFG.langManager.get("UpdateEconomyOfCiv");
      }
   }

   @Override
   public List<MenuElement_Hover_v2_Element2> getHoverText() {
      try {
         ArrayList<MenuElement_Hover_v2_Element2> tElements = new ArrayList<>();
         ArrayList<MenuElement_Hover_v2_Element_Type> tData = new ArrayList<>();
         if (this.canMakeAction()) {
            tData.add(new MenuElement_Hover_v2_Element_Type_Flag(this.getCivID()));
            tData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Economy") + ": ", CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
            tData.add(
               new MenuElement_Hover_v2_Element_Type_Text(
                  (this.getValue() > 0 ? "+" : "") + CFG.getNumberWithSpaces("" + this.getValue()) + "%",
                  this.getValue() > 0
                     ? CFG.COLOR_TEXT_MODIFIER_POSITIVE
                     : (this.getValue() == 0 ? CFG.COLOR_TEXT_MODIFIER_NEUTRAL2 : CFG.COLOR_TEXT_MODIFIER_NEGATIVE2)
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
   public final void editViewID() {
      CFG.menuManager.setViewID(Menu.eCREATE_SCENARIO_EVENTS_OUT_UPDATEECONOMY_OFCIV);
   }
}
