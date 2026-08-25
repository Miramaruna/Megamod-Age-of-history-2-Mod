package age.of.civilizations2.jakowski.lukasz;

import java.util.ArrayList;

public class Message_RelocateCapital extends Message {
   public Message_RelocateCapital(int fromCivID) {
      super(fromCivID, 0);
      this.messageType = Message_Type.CAPITAL_LOST_RELOCATE;
   }

   @Override
   public void onAction(int iMessageID) {
      if (CFG.game.getCiv(this.iFromCivID).getNumOfProvinces() > 0
         && (
            CFG.game.getCiv(this.iFromCivID).getCapitalProvinceID() < 0
               || CFG.game.getProvince(CFG.game.getCiv(this.iFromCivID).getCapitalProvinceID()).getCivID() != this.iFromCivID
         )) {
         CFG.toast.setInView(CFG.langManager.get("TheCapitalCityIsLost"), CFG.COLOR_TEXT_MODIFIER_NEGATIVE2);
         int iBest = -1;

         for (int i = 0; i < CFG.game.getCiv(this.iFromCivID).getNumOfProvinces(); i++) {
            if (!CFG.game.getProvince(CFG.game.getCiv(this.iFromCivID).getProvinceID(i)).isOccupied()) {
               if (iBest < 0) {
                  iBest = CFG.game.getCiv(this.iFromCivID).getProvinceID(i);
               } else if (CFG.game.getProvince(CFG.game.getCiv(this.iFromCivID).getProvinceID(i)).getPopulationData().getPopulation()
                  > CFG.game.getProvince(iBest).getPopulationData().getPopulation()) {
                  iBest = CFG.game.getCiv(this.iFromCivID).getProvinceID(i);
               }
            }
         }

         if (iBest >= 0) {
            CFG.menuManager.rebuildInGame_MoveCapital(iBest);
         }
      }

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
   }

   @Override
   public void onDecline(int iCivID) {
   }

   @Override
   public int getBGImageID() {
      return Images.messages_r;
   }

   @Override
   public int getImageID() {
      return Images.editor_city;
   }

   @Override
   public MenuElement_Hover_v2 getHover() {
      ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
      ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
      nData.add(new MenuElement_Hover_v2_Element_Type_Flag(this.iFromCivID));
      nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("TheCapitalCityIsLost"), CFG.COLOR_TEXT_MODIFIER_NEGATIVE2));
      nElements.add(new MenuElement_Hover_v2_Element2(nData));
      nData.clear();
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
