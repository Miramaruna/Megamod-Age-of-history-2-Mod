package age.of.civilizations2.jakowski.lukasz;

import java.util.ArrayList;
import java.util.List;

public class Event_Outcome_UpdatePopulation extends Event_Outcome {
   public int iCivID = -1;
   public List<Integer> lProvinces = new ArrayList<>();
   public int iValue = 0;

   Event_Outcome_UpdatePopulation() {
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
   public void outcomeAction() {
      if (this.getCivID() >= 0 && this.getCivID() < CFG.game.getCivsSize()) {
         for (int i = 0; i < this.getProvinces().size(); i++) {
            if (this.canMakeAction(i)) {
               if (this.getValue() < 0) {
                  float tPerc = (float)CFG.game.getProvince(this.getProvinces().get(i)).getPopulationData().getPopulation() / this.getValue() / 100.0F;

                  for (int j = 0; j < CFG.game.getProvince(this.getProvinces().get(i)).getPopulationData().getNationalitiesSize(); j++) {
                     CFG.game
                        .getProvince(this.getProvinces().get(i))
                        .getPopulationData()
                        .setPopulationOfCivID(
                           CFG.game.getProvince(this.getProvinces().get(i)).getPopulationData().getCivID(j),
                           CFG.game.getProvince(this.getProvinces().get(i)).getPopulationData().getPopulationID(j)
                              - (int)(CFG.game.getProvince(this.getProvinces().get(i)).getPopulationData().getPopulationID(j) * tPerc)
                        );
                  }
               } else {
                  CFG.game
                     .getProvince(this.getProvinces().get(i))
                     .getPopulationData()
                     .setPopulationOfCivID(
                        this.getCivID(),
                        CFG.game.getProvince(this.getProvinces().get(i)).getPopulationData().getPopulationOfCivID(this.getCivID()) + this.getValue()
                     );
               }
            }
         }
      }
   }

   public boolean canMakeAction(int i) {
      try {
         return !CFG.game.getProvince(this.getProvinces().get(i)).getSeaProvince() && CFG.game.getProvince(this.getProvinces().get(i)).getWasteland() < 0;
      } catch (IndexOutOfBoundsException var3) {
         return false;
      }
   }

   @Override
   public String getConditionText() {
      try {
         return CFG.langManager.get("UpdatePopulation") + ": " + CFG.game.getCiv(this.getCivID()).getCivName() + ", " + this.getValue();
      } catch (IndexOutOfBoundsException var2) {
         return CFG.langManager.get("UpdatePopulation");
      }
   }

   @Override
   public List<MenuElement_Hover_v2_Element2> getHoverText() {
      try {
         ArrayList<MenuElement_Hover_v2_Element2> tElements = new ArrayList<>();
         ArrayList<MenuElement_Hover_v2_Element_Type> tData = new ArrayList<>();

         for (int i = 0; i < this.getProvinces().size(); i++) {
            tData.add(new MenuElement_Hover_v2_Element_Type_Flag(this.getCivID()));
            tData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Population") + ": ", CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
            tData.add(
               new MenuElement_Hover_v2_Element_Type_Text(
                  (
                        CFG.game.getProvince(this.getProvinces().get(i)).getName().length() == 0
                           ? this.getProvinces().get(i)
                           : CFG.game.getProvince(this.getProvinces().get(i)).getName()
                     )
                     + ": "
               )
            );
            tData.add(
               new MenuElement_Hover_v2_Element_Type_Text(
                  (this.getValue() > 0 ? "+" : "") + CFG.getNumberWithSpaces("" + this.getValue()),
                  this.getValue() > 0
                     ? CFG.COLOR_TEXT_MODIFIER_POSITIVE
                     : (this.getValue() == 0 ? CFG.COLOR_TEXT_MODIFIER_NEUTRAL2 : CFG.COLOR_TEXT_MODIFIER_NEGATIVE2)
               )
            );
            tElements.add(new MenuElement_Hover_v2_Element2(tData));
            tData.clear();
         }

         return tElements;
      } catch (IndexOutOfBoundsException var4) {
      } catch (NullPointerException var5) {
      }

      return new ArrayList<>();
   }

   @Override
   public final void editViewID() {
      CFG.menuManager.setViewID(Menu.eCREATE_SCENARIO_EVENTS_OUT_UPDATEPOPULATION);
   }
}
