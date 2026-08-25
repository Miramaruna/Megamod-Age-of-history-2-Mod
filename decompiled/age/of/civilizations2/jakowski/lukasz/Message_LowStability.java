package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import java.util.ArrayList;

public class Message_LowStability extends Message {
   public Message_LowStability(int fromCivID, int iValue) {
      super(fromCivID, iValue);
      this.messageType = Message_Type.LOW_STABILITY;
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
         ArrayList<String> lMess = new ArrayList<>();
         ArrayList<Color> lColors = new ArrayList<>();
         lMess.add(CFG.langManager.get("LowStability"));
         lColors.add(CFG.COLOR_TEXT_MODIFIER_NEGATIVE2);
         lMess.add(CFG.langManager.get("AssimilateTheProvincesToIncreaseStability"));
         lColors.add(CFG.COLOR_TEXT_MODIFIER_NEGATIVE2);
         CFG.toast.setInView(lMess, lColors);
         CFG.toast.setTimeInView(6000);
         if (CFG.viewsManager.getActiveViewID() != ViewsManager.VIEW_PROVINCE_STABILITY_MODE) {
            if (CFG.game.getCiv(this.iFromCivID).lProvincesWithLowStability.size() > 0) {
               CFG.game.setActiveProvinceID(CFG.game.getCiv(this.iFromCivID).lProvincesWithLowStability.get(0));
            }

            CFG.viewsManager.setActiveViewID(ViewsManager.VIEW_PROVINCE_STABILITY_MODE);
         }
      }
   }

   @Override
   public void onDecline(int iCivID) {
      if (CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID() == iCivID) {
         ArrayList<String> lMess = new ArrayList<>();
         ArrayList<Color> lColors = new ArrayList<>();
         lMess.add(CFG.langManager.get("LowStability"));
         lColors.add(CFG.COLOR_TEXT_MODIFIER_NEGATIVE2);
         lMess.add(CFG.langManager.get("AssimilateTheProvincesToIncreaseStability"));
         lColors.add(CFG.COLOR_TEXT_MODIFIER_NEGATIVE2);
         CFG.toast.setInView(lMess, lColors);
         CFG.toast.setTimeInView(6000);
         if (CFG.viewsManager.getActiveViewID() != ViewsManager.VIEW_PROVINCE_STABILITY_MODE) {
            if (CFG.game.getCiv(this.iFromCivID).lProvincesWithLowStability.size() > 0) {
               CFG.game.setActiveProvinceID(CFG.game.getCiv(this.iFromCivID).lProvincesWithLowStability.get(0));
            }

            CFG.viewsManager.setActiveViewID(ViewsManager.VIEW_PROVINCE_STABILITY_MODE);
         }
      }
   }

   @Override
   public int getImageID() {
      return Images.diplo_popstability;
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
      nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("LowStability"), CFG.COLOR_TEXT_NUM_OF_PROVINCES));
      nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.diplo_popstability, CFG.PADDING, CFG.PADDING));
      nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Provinces") + ": "));
      nData.add(
         new MenuElement_Hover_v2_Element_Type_Text("" + CFG.game.getCiv(this.iFromCivID).lProvincesWithLowStability.size(), CFG.COLOR_TEXT_MODIFIER_NEGATIVE2)
      );
      nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.provinces, CFG.PADDING, 0));
      nElements.add(new MenuElement_Hover_v2_Element2(nData));
      nData.clear();
      nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("AssimilateTheProvincesToIncreaseStability")));
      nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.diplo_popstability, CFG.PADDING, 0));
      nElements.add(new MenuElement_Hover_v2_Element2(nData));
      nData.clear();
      nData.add(
         new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("IncomeOfProvinceIsReducedDueToLowStability"), CFG.COLOR_TEXT_MODIFIER_NEGATIVE2)
      );
      nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_gold2, CFG.PADDING, 0));
      nElements.add(new MenuElement_Hover_v2_Element2(nData));
      nData.clear();
      nData.add(new MenuElement_Hover_v2_Element_Type_Space());
      nElements.add(new MenuElement_Hover_v2_Element2(nData));
      nData.clear();

      for (int i = 0; i < CFG.game.getCiv(this.iFromCivID).lProvincesWithLowStability.size() && i < 10; i++) {
         nData.add(new MenuElement_Hover_v2_Element_Type_Flag(this.iFromCivID));
         nData.add(
            new MenuElement_Hover_v2_Element_Type_Text(
               CFG.game.getProvince(CFG.game.getCiv(this.iFromCivID).lProvincesWithLowStability.get(i)).getName() + ": "
            )
         );
         nData.add(
            new MenuElement_Hover_v2_Element_Type_Text(
               "" + (int)(CFG.game.getProvince(CFG.game.getCiv(this.iFromCivID).lProvincesWithLowStability.get(i)).getProvinceStability() * 100.0F) + "%",
               CFG.COLOR_TEXT_PROVINCE_STABILITY_MIN
            )
         );
         nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.diplo_popstability, CFG.PADDING, 0));
         nElements.add(new MenuElement_Hover_v2_Element2(nData));
         nData.clear();
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
