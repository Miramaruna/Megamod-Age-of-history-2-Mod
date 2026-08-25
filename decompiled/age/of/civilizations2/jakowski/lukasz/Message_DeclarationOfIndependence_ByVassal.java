package age.of.civilizations2.jakowski.lukasz;

import java.util.ArrayList;

public class Message_DeclarationOfIndependence_ByVassal extends Message {
   public Message_DeclarationOfIndependence_ByVassal(int byCivID) {
      super(byCivID, 0);
      this.messageType = Message_Type.DECLARATION_OF_INDEPENDENCE_BYVASSAl;
      this.iNumOfTurnsLeft = 3;
      this.requestsResponse = true;
      this.willPauseTheGame = true;
   }

   @Override
   public void onAction(int iMessageID) {
      CFG.menuManager.rebuildInGame_Message_DeclarationOfIndependence_ByVassal(this.iFromCivID, iMessageID, this.iValue);
   }

   @Override
   public void onAccept(int iCivID) {
      DiplomacyManager.vassalDeclareIndependence_War(iCivID, this.iFromCivID);
   }

   @Override
   public void onDecline(int iCivID) {
      CFG.game.whitePeace_ReturnProvincesToRightfulOwners(iCivID, this.iFromCivID);
      DiplomacyManager.vassalDeclareIndependence_Fine(iCivID, this.iFromCivID);
   }

   @Override
   public int getImageID() {
      return Images.top_diplomacy_points;
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
      nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("DeclarationOfIndependence") + ": "));
      nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.game.getCiv(this.iFromCivID).getCivName(), CFG.COLOR_TEXT_NUM_OF_PROVINCES));
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
