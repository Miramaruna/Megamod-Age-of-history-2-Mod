package age.of.civilizations2.jakowski.lukasz;

import com.codedisaster.steamworks.SteamAPICall;
import com.codedisaster.steamworks.SteamPublishedFileID;
import com.codedisaster.steamworks.SteamRemoteStorageCallback;
import com.codedisaster.steamworks.SteamResult;
import com.codedisaster.steamworks.SteamUGCHandle;

class Steam_Game$3 implements SteamRemoteStorageCallback {
   final Steam_Game this$0;

   Steam_Game$3(Steam_Game var1) {
      this.this$0 = var1;
   }

   @Override
   public void onDownloadUGCResult(SteamUGCHandle var1, SteamResult var2) {
   }

   @Override
   public void onFileReadAsyncComplete(SteamAPICall var1, SteamResult var2, int var3, int var4) {
   }

   @Override
   public void onFileShareResult(SteamUGCHandle var1, String var2, SteamResult var3) {
   }

   @Override
   public void onFileWriteAsyncComplete(SteamResult var1) {
   }

   @Override
   public void onPublishFileResult(SteamPublishedFileID var1, boolean var2, SteamResult var3) {
   }

   @Override
   public void onPublishedFileDeleted(SteamPublishedFileID var1, int var2) {
   }

   @Override
   public void onPublishedFileSubscribed(SteamPublishedFileID var1, int var2) {
   }

   @Override
   public void onPublishedFileUnsubscribed(SteamPublishedFileID var1, int var2) {
   }

   @Override
   public void onUpdatePublishedFileResult(SteamPublishedFileID var1, boolean var2, SteamResult var3) {
   }
}
