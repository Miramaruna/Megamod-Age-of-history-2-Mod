package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import java.util.ArrayList;

class Message_Producted_NuclearWeapon extends Message {
   protected Message_Producted_NuclearWeapon(int var1, int var2) {
      super(var1, var2);
      this.messageType = Message_Type.BUILT_NUCLEARREACTOR;
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
      var2.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_nuclear_weapons, CFG.PADDING, 0));
      var2.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("CreatedNewNuclearWeapon"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
      var1.add(new MenuElement_Hover_v2_Element2(var2));
      var2.clear();
      return new MenuElement_Hover_v2(var1);
   }

   @Override
   protected int getImageID() {
      return Images.top_nuclear_weapons;
   }

   @Override
   protected void onAccept(int var1) {
      if (CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID() == var1) {
         CFG.toast.setInView(CFG.langManager.get("CreatedNuclearWeapon"), Color.RED);
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
         CFG.toast.setInView(CFG.langManager.get("CreatedNuclearWeapon"), Color.RED);
         CFG.toast.setTimeInView(6000);
      }
   }
}
