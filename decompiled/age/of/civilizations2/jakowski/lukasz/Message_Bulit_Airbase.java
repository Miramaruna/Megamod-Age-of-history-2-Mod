package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import java.util.ArrayList;

public class Message_Bulit_Airbase extends Message {
   public Message_Bulit_Airbase(int fromCivID, int iValue) {
      super(fromCivID, iValue);
      this.messageType = Message_Type.BUILT_AIRBASE;
      this.iNumOfTurnsLeft = 1;
   }

   @Override
   public void onAction(int iMessageID) {
      CFG.game.setActiveProvinceID(this.iValue);
      CFG.map.getMapCoordinates().centerToProvinceID(CFG.game.getActiveProvinceID());
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
         ArrayList<String> lMess = new ArrayList<>();
         ArrayList<Color> lColors = new ArrayList<>();
         lMess.add(
            (CFG.game.getProvince(this.iValue).getName().length() > 0 ? CFG.game.getProvince(this.iValue).getName() + ": " : "")
               + CFG.langManager.get(BuildingsManager.getArmoury_Name(CFG.game.getProvince(this.iValue).getLevelOfArmoury()))
         );
         lColors.add(CFG.COLOR_TEXT_NUM_OF_PROVINCES);
         CFG.toast.setInView(lMess, lColors);
         CFG.toast.setTimeInView(6000);
      }
   }

   @Override
   public void onDecline(int iCivID) {
      if (CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID() == iCivID) {
         ArrayList<String> lMess = new ArrayList<>();
         ArrayList<Color> lColors = new ArrayList<>();
         lMess.add(
            (CFG.game.getProvince(this.iValue).getName().length() > 0 ? CFG.game.getProvince(this.iValue).getName() + ": " : "")
               + CFG.langManager.get(BuildingsManager.getArmoury_Name(CFG.game.getProvince(this.iValue).getLevelOfArmoury()))
         );
         lColors.add(CFG.COLOR_TEXT_NUM_OF_PROVINCES);
         CFG.toast.setInView(lMess, lColors);
         CFG.toast.setTimeInView(6000);
      }
   }

   @Override
   public int getImageID() {
      return Images.b_airbase;
   }

   @Override
   public int getBGImageID() {
      return Images.messages_g;
   }

   @Override
   public MenuElement_Hover_v2 getHover() {
      ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
      ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
      nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("BuildingBuiltIn") + ": ", CFG.COLOR_TEXT_NUM_OF_PROVINCES));
      nData.add(
         new MenuElement_Hover_v2_Element_Type_Text(
            CFG.game.getProvince(this.iValue).getName().length() > 0 ? CFG.game.getProvince(this.iValue).getName() : CFG.langManager.get("Province")
         )
      );
      nData.add(new MenuElement_Hover_v2_Element_Type_Flag(this.iFromCivID, CFG.PADDING, 0));
      nElements.add(new MenuElement_Hover_v2_Element2(nData));
      nData.clear();
      nData.add(
         new MenuElement_Hover_v2_Element_Type_Text(
            CFG.langManager.get(BuildingsManager.getArmoury_Name(CFG.game.getProvince(this.iValue).getLevelOfArmoury())), CFG.COLOR_TEXT_NUM_OF_PROVINCES
         )
      );
      nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.b_airbase, CFG.PADDING, 0));
      nElements.add(new MenuElement_Hover_v2_Element2(nData));
      nData.clear();
      nData.add(new MenuElement_Hover_v2_Element_Type_Space());
      nElements.add(new MenuElement_Hover_v2_Element2(nData));
      nData.clear();
      nData.add(new MenuElement_Hover_v2_Element_Type_Text(" - "));
      nData.add(
         new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("ReducesTheCostOfRecruitmentPerUnitByOneGold"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE)
      );
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
