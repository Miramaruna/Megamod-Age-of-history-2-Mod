package age.of.civilizations2.jakowski.lukasz;

import java.util.ArrayList;

class Message_Bulit_Workshop2 extends Message {
   protected Message_Bulit_Workshop2(int var1, int var2) {
      super(var1, var2);
      this.messageType = Message_Type.BUILT_Workshop2;
      this.iNumOfTurnsLeft = 1;
   }

   @Override
   protected int getBGImageID() {
      return Images.messages_g;
   }

   @Override
   protected MenuElement_Hover_v2 getHover() {
      ArrayList var1 = new ArrayList();
      ArrayList var2 = new ArrayList();
      StringBuilder var3 = new StringBuilder();
      var3.append(CFG.langManager.get("BuildingBuiltIn"));
      var3.append(": ");
      var2.add(new MenuElement_Hover_v2_Element_Type_Text(var3.toString(), CFG.COLOR_TEXT_NUM_OF_PROVINCES));
      String var4;
      if (CFG.game.getProvince(this.iValue).getName().length() > 0) {
         var4 = CFG.game.getProvince(this.iValue).getName();
      } else {
         var4 = CFG.langManager.get("Province");
      }

      var2.add(new MenuElement_Hover_v2_Element_Type_Text(var4));
      var2.add(new MenuElement_Hover_v2_Element_Type_Flag(this.iFromCivID, CFG.PADDING, 0));
      var1.add(new MenuElement_Hover_v2_Element2(var2));
      var2.clear();
      var2.add(
         new MenuElement_Hover_v2_Element_Type_Text(
            CFG.langManager.get(BuildingsManager.getWorkshop2_Name(CFG.game.getProvince(this.iValue).getLevelOfWorkshop2())), CFG.COLOR_TEXT_NUM_OF_PROVINCES
         )
      );
      var2.add(new MenuElement_Hover_v2_Element_Type_Image(Images.b_Workshop, CFG.PADDING, 0));
      var1.add(new MenuElement_Hover_v2_Element2(var2));
      var2.clear();
      var2.add(new MenuElement_Hover_v2_Element_Type_Space());
      var1.add(new MenuElement_Hover_v2_Element2(var2));
      var2.clear();
      var2.add(new MenuElement_Hover_v2_Element_Type_Text(" - "));
      var3 = new StringBuilder();
      var3.append(CFG.langManager.get("IncomeProduction"));
      var3.append(": ");
      var2.add(new MenuElement_Hover_v2_Element_Type_Text(var3.toString()));
      var3 = new StringBuilder();
      var3.append("+");
      var3.append((int)(BuildingsManager.getWorkshop2_IncomeProduction(CFG.game.getProvince(this.iValue).getLevelOfWorkshop2()) * 100.0F));
      var3.append("%");
      var2.add(new MenuElement_Hover_v2_Element_Type_Text(var3.toString(), CFG.COLOR_TEXT_MODIFIER_POSITIVE));
      var1.add(new MenuElement_Hover_v2_Element2(var2));
      var2.clear();
      var2.add(new MenuElement_Hover_v2_Element_Type_Space());
      var1.add(new MenuElement_Hover_v2_Element2(var2));
      var2.clear();
      var2.add(new MenuElement_Hover_v2_Element_Type_Image(Images.diplo_message));
      var3 = new StringBuilder();
      var3.append(CFG.langManager.get("MessageWillExpireIn"));
      var3.append(": ");
      var2.add(new MenuElement_Hover_v2_Element_Type_Text(var3.toString()));
      var3 = new StringBuilder();
      var3.append(CFG.langManager.get("TurnsX", this.iNumOfTurnsLeft));
      var3.append(" ");
      var2.add(new MenuElement_Hover_v2_Element_Type_Text(var3.toString(), CFG.COLOR_TEXT_MODIFIER_NEUTRAL2));
      var3 = new StringBuilder();
      var3.append("[");
      var3.append(Game_Calendar.getDate_ByTurnID(Game_Calendar.TURN_ID + this.iNumOfTurnsLeft));
      var3.append("]");
      var2.add(new MenuElement_Hover_v2_Element_Type_Text(var3.toString(), CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
      var1.add(new MenuElement_Hover_v2_Element2(var2));
      var2.clear();
      if (CFG.game.getCiv(this.iFromCivID).civGameData.leaderData != null) {
         var2.add(
            new MenuElement_Hover_v2_Element_Type_Text(CFG.game.getCiv(this.iFromCivID).civGameData.leaderData.getName(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE)
         );
         var2.add(new MenuElement_Hover_v2_Element_Type_Flag(this.iFromCivID, CFG.PADDING, 0));
         var1.add(new MenuElement_Hover_v2_Element2(var2));
         var2.clear();
      }

      return new MenuElement_Hover_v2(var1);
   }

   @Override
   protected int getImageID() {
      return Images.b_Workshop;
   }

   @Override
   protected void onAccept(int var1) {
      if (CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID() == var1) {
         ArrayList var2 = new ArrayList();
         ArrayList var3 = new ArrayList();
         StringBuilder var4 = new StringBuilder();
         String var6;
         if (CFG.game.getProvince(this.iValue).getName().length() > 0) {
            StringBuilder var5 = new StringBuilder();
            var5.append(CFG.game.getProvince(this.iValue).getName());
            var5.append(": ");
            var6 = var5.toString();
         } else {
            var6 = "";
         }

         var4.append(var6);
         var4.append(CFG.langManager.get(BuildingsManager.getWorkshop2_Name(CFG.game.getProvince(this.iValue).getLevelOfWorkshop2())));
         var2.add(var4.toString());
         var3.add(CFG.COLOR_TEXT_NUM_OF_PROVINCES);
         CFG.toast.setInView(var2, var3);
         CFG.toast.setTimeInView(6000);
      }
   }

   @Override
   protected void onAction(int var1) {
      CFG.game.setActiveProvinceID(this.iValue);
      CFG.map.getMapCoordinates().centerToProvinceID(CFG.game.getActiveProvinceID());
      CFG.game
         .getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
         .getCivilization_Diplomacy_GameData()
         .messageBox
         .getMessage(var1)
         .onDecline(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
      CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCivilization_Diplomacy_GameData().messageBox.removeMessage(var1);
      CFG.menuManager.rebuildInGame_Messages();
   }

   @Override
   protected void onDecline(int var1) {
      if (CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID() == var1) {
         ArrayList var2 = new ArrayList();
         ArrayList var3 = new ArrayList();
         StringBuilder var4 = new StringBuilder();
         String var6;
         if (CFG.game.getProvince(this.iValue).getName().length() > 0) {
            StringBuilder var5 = new StringBuilder();
            var5.append(CFG.game.getProvince(this.iValue).getName());
            var5.append(": ");
            var6 = var5.toString();
         } else {
            var6 = "";
         }

         var4.append(var6);
         var4.append(CFG.langManager.get(BuildingsManager.getWorkshop2_Name(CFG.game.getProvince(this.iValue).getLevelOfWorkshop2())));
         var2.add(var4.toString());
         var3.add(CFG.COLOR_TEXT_NUM_OF_PROVINCES);
         CFG.toast.setInView(var2, var3);
         CFG.toast.setTimeInView(6000);
      }
   }
}
