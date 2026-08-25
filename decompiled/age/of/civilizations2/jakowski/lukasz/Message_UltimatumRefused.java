package age.of.civilizations2.jakowski.lukasz;

import java.util.ArrayList;

public class Message_UltimatumRefused extends Message {
   public Message_UltimatumRefused(int fromCivID) {
      super(fromCivID, 0);
      this.messageType = Message_Type.ULTIMATUM_REFUSED;
      this.iNumOfTurnsLeft = 2;
   }

   @Override
   public void onAction(int iMessageID) {
      CFG.menuManager.rebuildInGame_Message_UltimatumRefused(this.iFromCivID, iMessageID, this.iValue);
   }

   @Override
   public void onAccept(int iCivID) {
      DiplomacyManager.refuseUltimatum_AcceptWar(iCivID, this.iFromCivID);
   }

   @Override
   public void onDecline(int iCivID) {
   }

   @Override
   public int getImageID() {
      return Images.diplo_rivals;
   }

   @Override
   public int getBGImageID() {
      return Images.messages_r;
   }

   @Override
   public MenuElement_Hover_v2 getHover() {
      ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
      ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
      nData.add(new MenuElement_Hover_v2_Element_Type_Flag(this.iFromCivID));
      nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.game.getCiv(this.iFromCivID).getCivName() + ": "));
      nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("RefusedToAcceptOurUltimatum"), CFG.COLOR_TEXT_MODIFIER_NEGATIVE2));
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
