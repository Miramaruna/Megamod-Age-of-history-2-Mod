package age.of.civilizations2.jakowski.lukasz;

import java.util.ArrayList;
import java.util.List;

public class Event_Outcome_ChangeIdeology extends Event_Outcome {
   public int iCivID = -1;
   public int iValue = -1;

   Event_Outcome_ChangeIdeology() {
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
   public int getValue() {
      return this.iValue;
   }

   @Override
   public void setValue(int nValue) {
      this.iValue = nValue;
   }

   @Override
   public void outcomeAction() {
      if (this.getValue() < 0) {
         this.iValue = CFG.oR.nextInt(CFG.ideologiesManager.getIdeologiesSize());
      }

      if (this.getCivID() >= 0 && this.getCivID() < CFG.game.getCivsSize()) {
         try {
            CFG.game.getCiv(this.getCivID()).setIdeologyID(this.getValue());
            CFG.game
               .getCiv(this.getCivID())
               .setCivTag(
                  CFG.ideologiesManager.getRealTag(CFG.game.getCiv(this.getCivID()).getCivTag())
                     + CFG.ideologiesManager.getIdeology(CFG.game.getCiv(this.getCivID()).getIdeologyID()).getExtraTag()
               );
            CFG.game.getCiv(this.getCivID()).loadFlag();

            for (int i = 0; i < CFG.game.getPlayersSize(); i++) {
               if (CFG.game.getPlayer(i).getCivID() == this.getCivID()) {
                  CFG.game.getPlayer(i).loadPlayersFlag();
                  break;
               }
            }

            CFG.setActiveCivInfo(CFG.getActiveCivInfo());
         } catch (IndexOutOfBoundsException var2) {
         }
      }
   }

   @Override
   public String getConditionText() {
      try {
         return CFG.langManager.get("ChangeIdeology")
            + ": "
            + CFG.game.getCiv(this.getCivID()).getCivName()
            + " -> "
            + CFG.ideologiesManager.getIdeology(this.getValue()).getName();
      } catch (IndexOutOfBoundsException var2) {
         return CFG.langManager.get("ChangeIdeology");
      }
   }

   public boolean canMakeAction() {
      return false;
   }

   @Override
   public List<MenuElement_Hover_v2_Element2> getHoverText() {
      try {
         ArrayList<MenuElement_Hover_v2_Element2> tElements = new ArrayList<>();
         ArrayList<MenuElement_Hover_v2_Element_Type> tData = new ArrayList<>();
         tData.add(new MenuElement_Hover_v2_Element_Type_Flag(this.getCivID()));
         tData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("ChangeIdeology") + ": ", CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
         tData.add(
            new MenuElement_Hover_v2_Element_Type_Text(
               CFG.ideologiesManager.getIdeology(this.getValue()).getName(), CFG.ideologiesManager.getIdeology(this.getValue()).getColor()
            )
         );
         tData.add(new MenuElement_Hover_v2_Element_Type_Ideology(this.getValue(), CFG.PADDING, 0));
         tElements.add(new MenuElement_Hover_v2_Element2(tData));
         tData.clear();
         return tElements;
      } catch (IndexOutOfBoundsException var3) {
      } catch (NullPointerException var4) {
      }

      return new ArrayList<>();
   }

   @Override
   public final void editViewID() {
      CFG.menuManager.setViewID(Menu.eCREATE_SCENARIO_EVENTS_OUT_CHANGEIDEOLOGY);
   }
}
