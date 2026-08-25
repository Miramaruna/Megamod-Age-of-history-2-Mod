package age.of.civilizations2.jakowski.lukasz;

import com.codedisaster.steamworks.SteamPublishedFileID;
import com.codedisaster.steamworks.SteamResult;
import com.codedisaster.steamworks.SteamUGCCallback;
import com.codedisaster.steamworks.SteamUGCDetails;
import com.codedisaster.steamworks.SteamUGCQuery;

class Steam_Game$4 implements SteamUGCCallback {
   final Steam_Game this$0;

   Steam_Game$4(Steam_Game var1) {
      this.this$0 = var1;
   }

   @Override
   public void onCreateItem(SteamPublishedFileID var1, boolean var2, SteamResult var3) {
   }

   @Override
   public void onDeleteItem(SteamPublishedFileID var1, SteamResult var2) {
   }

   @Override
   public void onDownloadItemResult(int var1, SteamPublishedFileID var2, SteamResult var3) {
   }

   @Override
   public void onGetUserItemVote(SteamPublishedFileID var1, boolean var2, boolean var3, boolean var4, SteamResult var5) {
   }

   @Override
   public void onRequestUGCDetails(SteamUGCDetails var1, SteamResult var2) {
   }

   @Override
   public void onSetUserItemVote(SteamPublishedFileID var1, boolean var2, SteamResult var3) {
   }

   @Override
   public void onStartPlaytimeTracking(SteamResult var1) {
   }

   @Override
   public void onStopPlaytimeTracking(SteamResult var1) {
   }

   @Override
   public void onStopPlaytimeTrackingForAllItems(SteamResult var1) {
   }

   @Override
   public void onSubmitItemUpdate(SteamPublishedFileID var1, boolean var2, SteamResult var3) {
   }

   @Override
   public void onSubscribeItem(SteamPublishedFileID var1, SteamResult var2) {
   }

   @Override
   public void onUGCQueryCompleted(SteamUGCQuery var1, int var2, int var3, boolean var4, SteamResult var5) {
   }

   @Override
   public void onUnsubscribeItem(SteamPublishedFileID var1, SteamResult var2) {
   }

   @Override
   public void onUserFavoriteItemsListChanged(SteamPublishedFileID var1, boolean var2, SteamResult var3) {
   }
}
