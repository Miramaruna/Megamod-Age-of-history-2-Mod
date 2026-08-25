package age.of.civilizations2.jakowski.lukasz;

import java.util.ArrayList;
import java.util.List;

public class Event_Outcome_DeclareWar extends Event_Outcome {
   public int iCivID = -1;
   public int iCivID2 = -1;

   Event_Outcome_DeclareWar() {
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
      return this.iCivID2;
   }

   @Override
   public void setCivID2(int nCivID) {
      this.iCivID2 = nCivID;
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

      if (this.iCivID2 == nRemovedCivID) {
         this.iCivID2 = -1;
         out = true;
      } else if (nRemovedCivID < this.iCivID2) {
         this.iCivID2--;
      }

      return out;
   }

   @Override
   public void outcomeAction() {
      if (this.canMakeAction()) {
         CFG.game.declareWar(this.getCivID(), this.getCivID2(), true);
      }
   }

   public boolean canMakeAction() {
      try {
         return this.getCivID() >= 0
            && this.getCivID() < CFG.game.getCivsSize()
            && this.getCivID2() >= 0
            && this.getCivID2() < CFG.game.getCivsSize()
            && this.getCivID() != this.getCivID2()
            && (int)CFG.game.getCivRelation_OfCivB(this.getCivID(), this.getCivID2()) != -100
            && CFG.game.getCiv(this.getCivID()).getNumOfProvinces() > 0
            && CFG.game.getCiv(this.getCivID2()).getNumOfProvinces() > 0;
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
            tData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("War") + ": ", CFG.COLOR_TEXT_MODIFIER_NEGATIVE2));
            tData.add(new MenuElement_Hover_v2_Element_Type_Flag(this.getCivID(), 0, CFG.PADDING));
            tData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.game.getCiv(this.getCivID()).getCivName()));
            tData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.diplo_rivals, CFG.PADDING, CFG.PADDING));
            tData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.game.getCiv(this.getCivID2()).getCivName()));
            tData.add(new MenuElement_Hover_v2_Element_Type_Flag(this.getCivID2(), CFG.PADDING, 0));
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
         return CFG.langManager.get("DeclareWar")
            + ": "
            + CFG.game.getCiv(this.getCivID()).getCivName()
            + ", "
            + CFG.game.getCiv(this.getCivID2()).getCivName();
      } catch (IndexOutOfBoundsException var2) {
         return CFG.langManager.get("DeclareWar");
      }
   }

   @Override
   public final void editViewID() {
      CFG.menuManager.setViewID(Menu.eCREATE_SCENARIO_EVENTS_OUT_DECLAREWAR);
   }
}
