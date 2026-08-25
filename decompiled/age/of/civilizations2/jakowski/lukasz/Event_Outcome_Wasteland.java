package age.of.civilizations2.jakowski.lukasz;

import java.util.ArrayList;
import java.util.List;

public class Event_Outcome_Wasteland extends Event_Outcome {
   public List<Integer> lProvinces = new ArrayList<>();
   public int iValue = 0;

   Event_Outcome_Wasteland() {
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
   public void outcomeAction() {
      for (int i = 0; i < this.getProvinces().size(); i++) {
         if (this.canMakeAction(i)) {
            if (this.getValue() == 0) {
               CFG.game.getProvince(this.getProvinces().get(i)).setWasteland(-1);
               CFG.game.getProvince(this.getProvinces().get(i)).setCivID(0, false);
               CFG.game.getProvince(this.getProvinces().get(i)).getPopulationData().setPopulationOfCivID(0, 92);
               CFG.game.getProvince(this.getProvinces().get(i)).setEconomy(19);
               CFG.game.buildWastelandLevels();
               if (CFG.game.getActiveProvinceID() == this.getProvinces().get(i)) {
                  CFG.game.setActiveProvinceID(-1);
                  CFG.game.setActiveProvinceID(this.getProvinces().get(i));
               }
            } else {
               CFG.game.getProvince(this.getProvinces().get(i)).setWasteland(0);
               CFG.game.getProvince(this.getProvinces().get(i)).setCivID(0, false);
               CFG.game.getProvince(this.getProvinces().get(i)).getPopulationData().setPopulationOfCivID(0, 92);
               CFG.game.getProvince(this.getProvinces().get(i)).setEconomy(19);
               CFG.game.buildWastelandLevels();
               if (CFG.game.getActiveProvinceID() == this.getProvinces().get(i)) {
                  CFG.game.setActiveProvinceID(-1);
                  CFG.game.setActiveProvinceID(this.getProvinces().get(i));
               }
            }
         }
      }

      CFG.game.buildWastelandLevels();
   }

   public boolean canMakeAction(int i) {
      try {
         return !CFG.game.getProvince(this.getProvinces().get(i)).getSeaProvince();
      } catch (IndexOutOfBoundsException var3) {
         return false;
      }
   }

   @Override
   public String getConditionText() {
      try {
         return CFG.langManager.get("UpdateWastelandProvinces")
            + ": "
            + CFG.langManager.get("Provinces")
            + ": "
            + this.getProvinces().size()
            + ", "
            + (this.getValue() == 0 ? CFG.langManager.get("WontBeAWastelandAnymore") : CFG.langManager.get("WillTurnIntoAWasteland"));
      } catch (IndexOutOfBoundsException var2) {
         return CFG.langManager.get("UpdateWastelandProvinces");
      }
   }

   @Override
   public List<MenuElement_Hover_v2_Element2> getHoverText() {
      try {
         ArrayList<MenuElement_Hover_v2_Element2> tElements = new ArrayList<>();
         ArrayList<MenuElement_Hover_v2_Element_Type> tData = new ArrayList<>();

         for (int i = 0; i < this.getProvinces().size(); i++) {
            if (this.canMakeAction(i)) {
               tData.add(
                  new MenuElement_Hover_v2_Element_Type_Text(
                     ""
                        + (
                           CFG.game.getProvince(this.getProvinces().get(i)).getName().length() == 0
                              ? this.getProvinces().get(i)
                              : CFG.game.getProvince(this.getProvinces().get(i)).getName()
                        )
                        + ": "
                  )
               );
               if (this.getValue() == 0) {
                  tData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("WontBeAWastelandAnymore"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
               } else {
                  tData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("WillTurnIntoAWasteland"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
               }

               tElements.add(new MenuElement_Hover_v2_Element2(tData));
               tData.clear();
            }
         }

         return tElements;
      } catch (IndexOutOfBoundsException var4) {
      } catch (NullPointerException var5) {
      }

      return new ArrayList<>();
   }

   @Override
   public final void editViewID() {
      CFG.menuManager.setViewID(Menu.eCREATE_SCENARIO_EVENTS_OUT_WASTELAND);
   }
}
