package age.of.civilizations2.jakowski.lukasz;

import com.codedisaster.steamworks.SteamFriends;
import com.codedisaster.steamworks.SteamFriendsCallback;
import com.codedisaster.steamworks.SteamID;
import com.codedisaster.steamworks.SteamResult;

class Steam_Game$5 implements SteamFriendsCallback {
   final Steam_Game this$0;

   Steam_Game$5(Steam_Game var1) {
      this.this$0 = var1;
   }

   @Override
   public void onAvatarImageLoaded(SteamID var1, int var2, int var3, int var4) {
   }

   @Override
   public void onFriendRichPresenceUpdate(SteamID var1, int var2) {
   }

   @Override
   public void onGameLobbyJoinRequested(SteamID var1, SteamID var2) {
   }

   @Override
   public void onGameOverlayActivated(boolean var1) {
   }

   @Override
   public void onGameRichPresenceJoinRequested(SteamID var1, String var2) {
   }

   @Override
   public void onGameServerChangeRequested(String var1, String var2) {
   }

   @Override
   public void onPersonaStateChange(SteamID var1, SteamFriends.PersonaChange var2) {
   }

   @Override
   public void onSetPersonaNameResponse(boolean var1, boolean var2, SteamResult var3) {
   }
}
