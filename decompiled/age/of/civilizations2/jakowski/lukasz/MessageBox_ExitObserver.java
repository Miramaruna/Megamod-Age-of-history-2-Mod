package age.of.civilizations2.jakowski.lukasz;

import java.util.ArrayList;

public class MessageBox_ExitObserver extends Message {
   public MessageBox_ExitObserver(int fromCivID) {
      super(fromCivID, 0);
      this.messageType = Message_Type.EXITOBSERVER;
      this.iNumOfTurnsLeft = 1;
   }

   @Override
   public void onAction(int iMessageID) {
      if (CFG.game.getActiveProvinceID() >= 0
         && !CFG.game.getProvince(CFG.game.getActiveProvinceID()).getSeaProvince()
         && CFG.game.getProvince(CFG.game.getActiveProvinceID()).getWasteland() < 0
         && CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID() > 0
         && !CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).getControlledByPlayer()
         && CFG.SPECTATOR_MODE) {
         CFG.SPECTATOR_MODE = false;
         if (CFG.game.getPlayersSize() == 1) {
            CFG.game.removePlayer(0);
         }
      }

      CFG.toast.setInView(CFG.langManager.get("Выйти из режима наблюдателя"), CFG.COLOR_TEXT_MODIFIER_NEGATIVE2);
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
   public int getImageID() {
      return Images.editor_game;
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
      nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Выйти из режима наблюдателя"), CFG.COLOR_TEXT_NUM_OF_PROVINCES));
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
