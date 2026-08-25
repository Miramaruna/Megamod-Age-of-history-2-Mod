package age.of.civilizations2.jakowski.lukasz;

import java.util.ArrayList;
import java.util.List;

public class Event_Outcome_PlayAsCiv extends Event_Outcome {
   protected int iCivID = -1;
   protected int iCivID2 = -1;

   Event_Outcome_PlayAsCiv() {
   }

   // $VF: Duplicated exception handlers to handle obfuscated exceptions
   protected boolean canMakeAction() {
      boolean var1 = false;
      boolean var2 = var1;

      try {
         if (this.getCivID() < 0) {
            return var2;
         }
      } catch (IndexOutOfBoundsException var11) {
         return var1;
      }

      var2 = var1;

      try {
         if (this.getCivID() >= CFG.game.getCivsSize()) {
            return var2;
         }
      } catch (IndexOutOfBoundsException var10) {
         return var1;
      }

      var2 = var1;

      try {
         if (this.getCivID2() < 0) {
            return var2;
         }
      } catch (IndexOutOfBoundsException var9) {
         return var1;
      }

      var2 = var1;

      try {
         if (this.getCivID2() >= CFG.game.getCivsSize()) {
            return var2;
         }
      } catch (IndexOutOfBoundsException var8) {
         return var1;
      }

      var2 = var1;

      try {
         if (this.getCivID() == this.getCivID2()) {
            return var2;
         }
      } catch (IndexOutOfBoundsException var7) {
         return var1;
      }

      var2 = var1;

      try {
         if (!CFG.game.getCiv(this.getCivID()).getControlledByPlayer()) {
            return var2;
         }
      } catch (IndexOutOfBoundsException var6) {
         return var1;
      }

      var2 = var1;

      int var3;
      try {
         if (CFG.game.getCiv(this.getCivID2()).getControlledByPlayer()) {
            return var2;
         }

         var3 = CFG.game.getCiv(this.getCivID2()).getNumOfProvinces();
      } catch (IndexOutOfBoundsException var5) {
         return var1;
      }

      var2 = var1;
      if (var3 > 0) {
         var2 = true;
      }

      return var2;
   }

   @Override
   protected final void editViewID() {
      CFG.menuManager.setViewID(Menu.eCREATE_SCENARIO_EVENTS_OUT_PLAYASCIV);
   }

   @Override
   protected int getCivID() {
      return this.iCivID;
   }

   @Override
   protected int getCivID2() {
      return this.iCivID2;
   }

   @Override
   protected String getConditionText() {
      String var1;
      try {
         StringBuilder var3 = new StringBuilder();
         var1 = var3.append("PlayAsCiv")
            .append(": ")
            .append(CFG.game.getCiv(this.getCivID()).getCivName())
            .append(", ")
            .append(CFG.game.getCiv(this.getCivID2()).getCivName())
            .toString();
      } catch (IndexOutOfBoundsException var2) {
         var1 = "PlayAsCiv";
      }

      return var1;
   }

   // $VF: Duplicated exception handlers to handle obfuscated exceptions
   @Override
   protected List<MenuElement_Hover_v2_Element2> getHoverText() {
      ArrayList var1;
      ArrayList var2;
      try {
         var1 = new ArrayList();
         var2 = new ArrayList();
      } catch (IndexOutOfBoundsException var9) {
         return new ArrayList<>();
      } catch (NullPointerException var10) {
         return new ArrayList<>();
      }

      ArrayList var3 = var1;

      try {
         if (!this.canMakeAction()) {
            return var3;
         }

         MenuElement_Hover_v2_Element_Type_Text var11 = new MenuElement_Hover_v2_Element_Type_Text("Play as civilization: ", CFG.COLOR_TEXT_MODIFIER_NEUTRAL2);
         var2.add(var11);
         int var4 = this.getCivID();
         if (CFG.game.getPlayerID_ByCivID(var4) != CFG.PLAYER_TURNID) {
            MenuElement_Hover_v2_Element_Type_Text var12 = new MenuElement_Hover_v2_Element_Type_Text(CFG.game.getCiv(this.getCivID()).getCivName());
            var2.add(var12);
            MenuElement_Hover_v2_Element_Type_Flag var13 = new MenuElement_Hover_v2_Element_Type_Flag(this.getCivID(), CFG.PADDING, 0);
            var2.add(var13);
            MenuElement_Hover_v2_Element_Type_Text var14 = new MenuElement_Hover_v2_Element_Type_Text(" -> ");
            var2.add(var14);
         }
      } catch (IndexOutOfBoundsException var7) {
         return new ArrayList<>();
      } catch (NullPointerException var8) {
         return new ArrayList<>();
      }

      try {
         MenuElement_Hover_v2_Element_Type_Text var15 = new MenuElement_Hover_v2_Element_Type_Text(CFG.game.getCiv(this.getCivID2()).getCivName());
         var2.add(var15);
         MenuElement_Hover_v2_Element_Type_Flag var16 = new MenuElement_Hover_v2_Element_Type_Flag(this.getCivID2(), CFG.PADDING, 0);
         var2.add(var16);
         MenuElement_Hover_v2_Element2 var17 = new MenuElement_Hover_v2_Element2(var2);
         var1.add(var17);
         var2.clear();
         return var1;
      } catch (IndexOutOfBoundsException var5) {
      } catch (NullPointerException var6) {
      }

      return new ArrayList<>();
   }

   @Override
   protected void outcomeAction() {
      if (this.canMakeAction()) {
         int var1 = this.getCivID();
         var1 = CFG.game.getPlayerID_ByCivID(var1);
         int var2 = this.getCivID2();
         CFG.gameAction.changePlayerCivID(var1, var2);
      }
   }

   @Override
   protected void setCivID(int var1) {
      this.iCivID = var1;
   }

   @Override
   protected void setCivID2(int var1) {
      this.iCivID2 = var1;
   }

   @Override
   protected boolean updateCivIDAfterRemove(int var1) {
      boolean var2 = false;
      boolean var3;
      if (this.iCivID == var1) {
         this.iCivID = -1;
         var3 = true;
      } else {
         var3 = var2;
         if (var1 < this.iCivID) {
            this.iCivID--;
            var3 = var2;
         }
      }

      if (this.iCivID2 == var1) {
         this.iCivID2 = -1;
         var2 = true;
      } else {
         var2 = var3;
         if (var1 < this.iCivID2) {
            this.iCivID2--;
            var2 = var3;
         }
      }

      return var2;
   }
}
