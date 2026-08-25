package age.of.civilizations2.jakowski.lukasz;

import java.util.ArrayList;
import java.util.List;

public class Event_Outcome_JoinAlliance extends Event_Outcome {
   public String sName = "";
   public int iCivID = -1;
   public int iCivID2 = -1;

   Event_Outcome_JoinAlliance() {
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
   public String getText() {
      return this.sName;
   }

   @Override
   public void setText(String nText) {
      this.sName = nText;
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
         if (CFG.game.getCiv(this.getCivID()).getAllianceID() > 0 && CFG.game.getCiv(this.getCivID2()).getAllianceID() == 0) {
            CFG.game.getAlliance(CFG.game.getCiv(this.getCivID()).getAllianceID()).addCivilization(this.getCivID2());
            CFG.game.getCiv(this.getCivID2()).setAllianceID(CFG.game.getCiv(this.getCivID()).getAllianceID());
         } else if (CFG.game.getCiv(this.getCivID()).getAllianceID() == 0 && CFG.game.getCiv(this.getCivID2()).getAllianceID() > 0) {
            CFG.game.getAlliance(CFG.game.getCiv(this.getCivID2()).getAllianceID()).addCivilization(this.getCivID());
            CFG.game.getCiv(this.getCivID()).setAllianceID(CFG.game.getCiv(this.getCivID2()).getAllianceID());
         } else if (CFG.game.getCiv(this.getCivID()).getAllianceID() > 0 && CFG.game.getCiv(this.getCivID2()).getAllianceID() > 0) {
            CFG.game.getAlliance(CFG.game.getCiv(this.getCivID2()).getAllianceID()).removeCivilization(this.getCivID2());
            CFG.game.getAlliance(CFG.game.getCiv(this.getCivID()).getAllianceID()).addCivilization(this.getCivID2());
            CFG.game.getCiv(this.getCivID2()).setAllianceID(CFG.game.getCiv(this.getCivID()).getAllianceID());
         } else {
            CFG.game.addAlliance(CFG.getRandomAllianceName(0));
            CFG.game.getAlliance(CFG.game.getAlliancesSize() - 1).addCivilization(this.getCivID());
            CFG.game.getAlliance(CFG.game.getAlliancesSize() - 1).addCivilization(this.getCivID2());
            CFG.game.getCiv(this.getCivID()).setAllianceID(CFG.game.getAlliancesSize() - 1);
            CFG.game.getCiv(this.getCivID2()).setAllianceID(CFG.game.getAlliancesSize() - 1);
         }

         if (!this.sName.equals("")) {
            CFG.game.getAlliance(CFG.game.getCiv(this.getCivID()).getAllianceID()).setAllianceName(CFG.langManager.get(this.sName));
         }
      }
   }

   public boolean canMakeAction() {
      try {
         return this.getCivID() >= 0
            && this.getCivID() < CFG.game.getCivsSize()
            && this.getCivID2() >= 0
            && this.getCivID2() < CFG.game.getCivsSize()
            && this.getCivID() != this.getCivID2()
            && (int)CFG.game.getCivRelation_OfCivB(this.getCivID(), this.getCivID2()) != -100;
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
            tData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Alliance") + ":", CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
            if (!this.sName.equals("")) {
               tData.add(new MenuElement_Hover_v2_Element_Type_Text(" [" + this.sName + "]", CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
            }

            tData.add(new MenuElement_Hover_v2_Element_Type_Flag(this.getCivID(), CFG.PADDING, CFG.PADDING));
            tData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.game.getCiv(this.getCivID()).getCivName()));
            tData.add(new MenuElement_Hover_v2_Element_Type_Text(" - "));
            tData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.game.getCiv(this.getCivID2()).getCivName()));
            tData.add(new MenuElement_Hover_v2_Element_Type_Flag(this.getCivID2(), CFG.PADDING, CFG.PADDING));
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
         return CFG.langManager.get("JoinAlliance")
            + ": "
            + CFG.game.getCiv(this.getCivID()).getCivName()
            + ", "
            + CFG.game.getCiv(this.getCivID2()).getCivName();
      } catch (IndexOutOfBoundsException var2) {
         return CFG.langManager.get("JoinAlliance");
      }
   }

   @Override
   public final void editViewID() {
      CFG.menuManager.setViewID(Menu.eCREATE_SCENARIO_EVENTS_OUT_JOINALLIANCE);
   }
}
