package age.of.civilizations2.jakowski.lukasz;

import com.codedisaster.steamworks.SteamAuth;
import com.codedisaster.steamworks.SteamID;
import com.codedisaster.steamworks.SteamResult;
import com.codedisaster.steamworks.SteamUserCallback;

class Steam_Game$1 implements SteamUserCallback {
   final Steam_Game this$0;

   Steam_Game$1(Steam_Game var1) {
      this.this$0 = var1;
   }

   @Override
   public void onEncryptedAppTicket(SteamResult var1) {
   }

   @Override
   public void onMicroTxnAuthorization(int var1, long var2, boolean var4) {
   }

   @Override
   public void onValidateAuthTicket(SteamID var1, SteamAuth.AuthSessionResponse var2, SteamID var3) {
   }
}
