package age.of.civilizations2.jakowski.lukasz;

import java.util.ArrayList;

public class Message_ProvincesNotSupplied extends Message {
   public Message_ProvincesNotSupplied(int fromCivID, int iValue) {
      super(fromCivID, iValue);
      this.messageType = Message_Type.PROVINCES_NOT_SUPPLIED;
      this.iNumOfTurnsLeft = 1;
   }

   @Override
   public void onAction(int iMessageID) {
      CFG.game
         .getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
         .getCivilization_Diplomacy_GameData()
         .messageBox
         .getMessage(iMessageID)
         .onDecline(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
      CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCivilization_Diplomacy_GameData().messageBox.removeMessage(iMessageID);
      CFG.menuManager.rebuildInGame_Messages();
   }

   @Override
   public void onAccept(int iCivID) {
      if (CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID() == iCivID) {
         CFG.toast.setInView(CFG.langManager.get("ProvinceIsNotSupplied"), CFG.COLOR_TEXT_MODIFIER_NEGATIVE2);
         CFG.toast.setTimeInView(4500);
      }
   }

   @Override
   public void onDecline(int iCivID) {
      if (CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID() == iCivID) {
         CFG.toast.setInView(CFG.langManager.get("ProvinceIsNotSupplied"), CFG.COLOR_TEXT_MODIFIER_NEGATIVE2);
         CFG.toast.setTimeInView(4500);
      }
   }

   @Override
   public int getImageID() {
      return Images.difficulty_hell;
   }

   @Override
   public int getBGImageID() {
      return Images.messages;
   }

   @Override
   public MenuElement_Hover_v2 getHover() {
      ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
      ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
      nData.add(new MenuElement_Hover_v2_Element_Type_Flag(this.iFromCivID));
      nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("ProvinceIsNotSupplied"), CFG.COLOR_TEXT_MODIFIER_NEGATIVE2));
      nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.difficulty_hell, CFG.PADDING, 0));
      nElements.add(new MenuElement_Hover_v2_Element2(nData));
      nData.clear();

      for (int i = 0; i < CFG.game.getCiv(this.iFromCivID).getNumOfProvinces(); i++) {
         if (!CFG.game.getProvince(CFG.game.getCiv(this.iFromCivID).getProvinceID(i)).getIsSupplied()
            && CFG.game.getProvince(CFG.game.getCiv(this.iFromCivID).getProvinceID(i)).getIsNotSuppliedForXTurns() > 0) {
            nData.add(
               new MenuElement_Hover_v2_Element_Type_Text(
                  CFG.game.getProvince(CFG.game.getCiv(this.iFromCivID).getProvinceID(i)).getName().length() > 0
                     ? CFG.game.getProvince(CFG.game.getCiv(this.iFromCivID).getProvinceID(i)).getName()
                     : CFG.langManager.get("Province"),
                  CFG.COLOR_TEXT_MODIFIER_NEGATIVE2
               )
            );
            nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.difficulty_hell, CFG.PADDING, CFG.PADDING));
            nData.add(
               new MenuElement_Hover_v2_Element_Type_Text(
                  CFG.langManager.get("TurnsX", CFG.game.getProvince(CFG.game.getCiv(this.iFromCivID).getProvinceID(i)).getIsNotSuppliedForXTurns()),
                  CFG.COLOR_TEXT_MODIFIER_NEUTRAL
               )
            );
            nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.time, CFG.PADDING, 0));
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
         }
      }

      nData.add(new MenuElement_Hover_v2_Element_Type_Space());
      nElements.add(new MenuElement_Hover_v2_Element2(nData));
      nData.clear();
      nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.diplo_message));
      nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("MessageWillExpireIn") + ": "));
      nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("TurnsX", this.iNumOfTurnsLeft) + " ", CFG.COLOR_TEXT_MODIFIER_NEUTRAL2));
      nData.add(
         new MenuElement_Hover_v2_Element_Type_Text(
            "[" + Game_Calendar.getDate_ByTurnID(Game_Calendar.TURN_ID + this.iNumOfTurnsLeft) + "]", CFG.COLOR_TEXT_MODIFIER_NEUTRAL
         )
      );
      nElements.add(new MenuElement_Hover_v2_Element2(nData));
      nData.clear();
      if (CFG.game.getCiv(this.iFromCivID).civGameData.leaderData != null) {
         nData.add(
            new MenuElement_Hover_v2_Element_Type_Text(CFG.game.getCiv(this.iFromCivID).civGameData.leaderData.getName(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE)
         );
         nData.add(new MenuElement_Hover_v2_Element_Type_Flag(this.iFromCivID, CFG.PADDING, 0));
         nElements.add(new MenuElement_Hover_v2_Element2(nData));
         nData.clear();
      }

      return new MenuElement_Hover_v2(nElements);
   }
}
