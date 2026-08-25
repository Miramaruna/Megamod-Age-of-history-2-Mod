package age.of.civilizations2.jakowski.lukasz;

import java.util.ArrayList;

class Message_Bulit_Bunker extends Message {
   protected Message_Bulit_Bunker(int var1, int var2) {
      super(var1, var2);
      this.messageType = Message_Type.BUILT_BUNKER;
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
      var2.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("BuildingBuiltIn") + ": ", CFG.COLOR_TEXT_NUM_OF_PROVINCES));
      String var3;
      if (CFG.game.getProvince(this.iValue).getName().length() > 0) {
         var3 = CFG.game.getProvince(this.iValue).getName();
      } else {
         var3 = CFG.langManager.get("Province");
      }

      var2.add(new MenuElement_Hover_v2_Element_Type_Text(var3));
      var2.add(new MenuElement_Hover_v2_Element_Type_Flag(this.iFromCivID, CFG.PADDING, 0));
      var1.add(new MenuElement_Hover_v2_Element2(var2));
      var2.clear();
      var2.add(
         new MenuElement_Hover_v2_Element_Type_Text(
            CFG.langManager.get(BuildingsManager.getNuclearReactor_Name(CFG.game.getProvince(this.iValue).getLevelOfNuclearReactor())),
            CFG.COLOR_TEXT_NUM_OF_PROVINCES
         )
      );
      var2.add(new MenuElement_Hover_v2_Element_Type_Image(Images.bBunker, CFG.PADDING, 0));
      var1.add(new MenuElement_Hover_v2_Element2(var2));
      var2.clear();
      var2.add(new MenuElement_Hover_v2_Element_Type_Space());
      var1.add(new MenuElement_Hover_v2_Element2(var2));
      var2.clear();
      var2.add(new MenuElement_Hover_v2_Element_Type_Text(" - "));
      var2.add(
         new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("ReducesTheCostOfRecruitmentPerUnitByOneGold"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE)
      );
      var1.add(new MenuElement_Hover_v2_Element2(var2));
      var2.clear();
      var2.add(new MenuElement_Hover_v2_Element_Type_Space());
      var1.add(new MenuElement_Hover_v2_Element2(var2));
      var2.clear();
      var2.add(new MenuElement_Hover_v2_Element_Type_Image(Images.diplo_message));
      var2.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("MessageWillExpireIn") + ": "));
      var2.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("TurnsX", this.iNumOfTurnsLeft) + " ", CFG.COLOR_TEXT_MODIFIER_NEUTRAL2));
      var2.add(
         new MenuElement_Hover_v2_Element_Type_Text(
            "[" + Game_Calendar.getDate_ByTurnID(Game_Calendar.TURN_ID + this.iNumOfTurnsLeft) + "]", CFG.COLOR_TEXT_MODIFIER_NEUTRAL
         )
      );
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
      return Images.bBunker;
   }

   @Override
   protected void onAccept(int var1) {
      if (CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID() == var1) {
         ArrayList var2 = new ArrayList();
         ArrayList var3 = new ArrayList();
         StringBuilder var4 = new StringBuilder();
         String var5;
         if (CFG.game.getProvince(this.iValue).getName().length() > 0) {
            var5 = CFG.game.getProvince(this.iValue).getName() + ": ";
         } else {
            var5 = "";
         }

         var2.add(
            var4.append(var5)
               .append(CFG.langManager.get(BuildingsManager.getNuclearReactor_Name(CFG.game.getProvince(this.iValue).getLevelOfNuclearReactor())))
               .toString()
         );
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
         String var5;
         if (CFG.game.getProvince(this.iValue).getName().length() > 0) {
            var5 = CFG.game.getProvince(this.iValue).getName() + ": ";
         } else {
            var5 = "";
         }

         var2.add(
            var4.append(var5)
               .append(CFG.langManager.get(BuildingsManager.getNuclearReactor_Name(CFG.game.getProvince(this.iValue).getLevelOfNuclearReactor())))
               .toString()
         );
         var3.add(CFG.COLOR_TEXT_NUM_OF_PROVINCES);
         CFG.toast.setInView(var2, var3);
         CFG.toast.setTimeInView(6000);
      }
   }
}
