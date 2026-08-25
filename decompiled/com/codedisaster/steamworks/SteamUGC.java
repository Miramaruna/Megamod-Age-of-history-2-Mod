package com.codedisaster.steamworks;

import java.util.Collection;
import java.util.EnumSet;

public class SteamUGC extends SteamInterface {
   public SteamUGC(SteamUGCCallback callback) {
      super(SteamAPI.getSteamUGCPointer(), createCallback(new SteamUGCCallbackAdapter(callback)));
   }

   public SteamUGCQuery createQueryUserUGCRequest(
      int accountID,
      SteamUGC.UserUGCList listType,
      SteamUGC.MatchingUGCType matchingType,
      SteamUGC.UserUGCListSortOrder sortOrder,
      int creatorAppID,
      int consumerAppID,
      int page
   ) {
      return new SteamUGCQuery(
         createQueryUserUGCRequest(this.pointer, accountID, listType.ordinal(), matchingType.value, sortOrder.ordinal(), creatorAppID, consumerAppID, page)
      );
   }

   public SteamUGCQuery createQueryAllUGCRequest(
      SteamUGC.UGCQueryType queryType, SteamUGC.MatchingUGCType matchingType, int creatorAppID, int consumerAppID, int page
   ) {
      return new SteamUGCQuery(createQueryAllUGCRequest(this.pointer, queryType.ordinal(), matchingType.value, creatorAppID, consumerAppID, page));
   }

   public SteamUGCQuery createQueryUGCDetailsRequest(SteamPublishedFileID publishedFileID) {
      long[] fileIDs = new long[]{publishedFileID.handle};
      return new SteamUGCQuery(createQueryUGCDetailsRequest(this.pointer, fileIDs, 1));
   }

   public SteamUGCQuery createQueryUGCDetailsRequest(Collection<SteamPublishedFileID> publishedFileIDs) {
      int size = publishedFileIDs.size();
      long[] fileIDs = new long[size];
      int index = 0;

      for (SteamPublishedFileID fileID : publishedFileIDs) {
         fileIDs[index++] = fileID.handle;
      }

      return new SteamUGCQuery(createQueryUGCDetailsRequest(this.pointer, fileIDs, size));
   }

   public SteamAPICall sendQueryUGCRequest(SteamUGCQuery query) {
      return new SteamAPICall(sendQueryUGCRequest(this.pointer, this.callback, query.handle));
   }

   public boolean getQueryUGCResult(SteamUGCQuery query, int index, SteamUGCDetails details) {
      return getQueryUGCResult(this.pointer, query.handle, index, details);
   }

   public String getQueryUGCPreviewURL(SteamUGCQuery query, int index) {
      return getQueryUGCPreviewURL(this.pointer, query.handle, index);
   }

   public String getQueryUGCMetadata(SteamUGCQuery query, int index) {
      return getQueryUGCMetadata(this.pointer, query.handle, index);
   }

   public long getQueryUGCStatistic(SteamUGCQuery query, int index, SteamUGC.ItemStatistic statType) {
      return getQueryUGCStatistic(this.pointer, query.handle, index, statType.ordinal());
   }

   public int getQueryUGCNumAdditionalPreviews(SteamUGCQuery query, int index) {
      return getQueryUGCNumAdditionalPreviews(this.pointer, query.handle, index);
   }

   public boolean getQueryUGCAdditionalPreview(SteamUGCQuery query, int index, int previewIndex, SteamUGC.ItemAdditionalPreview previewInfo) {
      return getQueryUGCAdditionalPreview(this.pointer, query.handle, index, previewIndex, previewInfo);
   }

   public int getQueryUGCNumKeyValueTags(SteamUGCQuery query, int index) {
      return getQueryUGCNumKeyValueTags(this.pointer, query.handle, index);
   }

   public boolean getQueryUGCKeyValueTag(SteamUGCQuery query, int index, int keyValueTagIndex, String[] keyAndValue) {
      return getQueryUGCKeyValueTag(this.pointer, query.handle, index, keyValueTagIndex, keyAndValue);
   }

   public boolean releaseQueryUserUGCRequest(SteamUGCQuery query) {
      return releaseQueryUserUGCRequest(this.pointer, query.handle);
   }

   public boolean addRequiredTag(SteamUGCQuery query, String tagName) {
      return addRequiredTag(this.pointer, query.handle, tagName);
   }

   public boolean addExcludedTag(SteamUGCQuery query, String tagName) {
      return addExcludedTag(this.pointer, query.handle, tagName);
   }

   public boolean setReturnOnlyIDs(SteamUGCQuery query, boolean returnOnlyIDs) {
      return setReturnOnlyIDs(this.pointer, query.handle, returnOnlyIDs);
   }

   public boolean setReturnKeyValueTags(SteamUGCQuery query, boolean returnKeyValueTags) {
      return setReturnKeyValueTags(this.pointer, query.handle, returnKeyValueTags);
   }

   public boolean setReturnLongDescription(SteamUGCQuery query, boolean returnLongDescription) {
      return setReturnLongDescription(this.pointer, query.handle, returnLongDescription);
   }

   public boolean setReturnMetadata(SteamUGCQuery query, boolean returnMetadata) {
      return setReturnMetadata(this.pointer, query.handle, returnMetadata);
   }

   public boolean setReturnChildren(SteamUGCQuery query, boolean returnChildren) {
      return setReturnChildren(this.pointer, query.handle, returnChildren);
   }

   public boolean setReturnAdditionalPreviews(SteamUGCQuery query, boolean returnAdditionalPreviews) {
      return setReturnAdditionalPreviews(this.pointer, query.handle, returnAdditionalPreviews);
   }

   public boolean setReturnTotalOnly(SteamUGCQuery query, boolean returnTotalOnly) {
      return setReturnTotalOnly(this.pointer, query.handle, returnTotalOnly);
   }

   public boolean setReturnPlaytimeStats(SteamUGCQuery query, int days) {
      return setReturnPlaytimeStats(this.pointer, query.handle, days);
   }

   public boolean setLanguage(SteamUGCQuery query, String language) {
      return setLanguage(this.pointer, query.handle, language);
   }

   public boolean setAllowCachedResponse(SteamUGCQuery query, int maxAgeSeconds) {
      return setAllowCachedResponse(this.pointer, query.handle, maxAgeSeconds);
   }

   public boolean setCloudFileNameFilter(SteamUGCQuery query, String matchCloudFileName) {
      return setCloudFileNameFilter(this.pointer, query.handle, matchCloudFileName);
   }

   public boolean setMatchAnyTag(SteamUGCQuery query, boolean matchAnyTag) {
      return setMatchAnyTag(this.pointer, query.handle, matchAnyTag);
   }

   public boolean setSearchText(SteamUGCQuery query, String searchText) {
      return setSearchText(this.pointer, query.handle, searchText);
   }

   public boolean setRankedByTrendDays(SteamUGCQuery query, int days) {
      return setRankedByTrendDays(this.pointer, query.handle, days);
   }

   public boolean addRequiredKeyValueTag(SteamUGCQuery query, String key, String value) {
      return addRequiredKeyValueTag(this.pointer, query.handle, key, value);
   }

   @Deprecated
   public SteamAPICall requestUGCDetails(SteamPublishedFileID publishedFileID, int maxAgeSeconds) {
      return new SteamAPICall(requestUGCDetails(this.pointer, this.callback, publishedFileID.handle, maxAgeSeconds));
   }

   public SteamAPICall createItem(int consumerAppID, SteamRemoteStorage.WorkshopFileType fileType) {
      return new SteamAPICall(createItem(this.pointer, this.callback, consumerAppID, fileType.ordinal()));
   }

   public SteamUGCUpdateHandle startItemUpdate(int consumerAppID, SteamPublishedFileID publishedFileID) {
      return new SteamUGCUpdateHandle(startItemUpdate(this.pointer, consumerAppID, publishedFileID.handle));
   }

   public boolean setItemTitle(SteamUGCUpdateHandle update, String title) {
      return setItemTitle(this.pointer, update.handle, title);
   }

   public boolean setItemDescription(SteamUGCUpdateHandle update, String description) {
      return setItemDescription(this.pointer, update.handle, description);
   }

   public boolean setItemUpdateLanguage(SteamUGCUpdateHandle update, String language) {
      return setItemUpdateLanguage(this.pointer, update.handle, language);
   }

   public boolean setItemMetadata(SteamUGCUpdateHandle update, String metaData) {
      return setItemMetadata(this.pointer, update.handle, metaData);
   }

   public boolean setItemVisibility(SteamUGCUpdateHandle update, SteamRemoteStorage.PublishedFileVisibility visibility) {
      return setItemVisibility(this.pointer, update.handle, visibility.ordinal());
   }

   public boolean setItemTags(SteamUGCUpdateHandle update, String[] tags) {
      return setItemTags(this.pointer, update.handle, tags, tags.length);
   }

   public boolean setItemContent(SteamUGCUpdateHandle update, String contentFolder) {
      return setItemContent(this.pointer, update.handle, contentFolder);
   }

   public boolean setItemPreview(SteamUGCUpdateHandle update, String previewFile) {
      return setItemPreview(this.pointer, update.handle, previewFile);
   }

   public boolean removeItemKeyValueTags(SteamUGCUpdateHandle update, String key) {
      return removeItemKeyValueTags(this.pointer, update.handle, key);
   }

   public boolean addItemKeyValueTag(SteamUGCUpdateHandle update, String key, String value) {
      return addItemKeyValueTag(this.pointer, update.handle, key, value);
   }

   public SteamAPICall submitItemUpdate(SteamUGCUpdateHandle update, String changeNote) {
      return new SteamAPICall(submitItemUpdate(this.pointer, this.callback, update.handle, changeNote));
   }

   public SteamUGC.ItemUpdateStatus getItemUpdateProgress(SteamUGCUpdateHandle update, SteamUGC.ItemUpdateInfo updateInfo) {
      long[] values = new long[2];
      SteamUGC.ItemUpdateStatus status = SteamUGC.ItemUpdateStatus.byOrdinal(getItemUpdateProgress(this.pointer, update.handle, values));
      updateInfo.bytesProcessed = values[0];
      updateInfo.bytesTotal = values[1];
      return status;
   }

   public SteamAPICall setUserItemVote(SteamPublishedFileID publishedFileID, boolean voteUp) {
      return new SteamAPICall(setUserItemVote(this.pointer, this.callback, publishedFileID.handle, voteUp));
   }

   public SteamAPICall getUserItemVote(SteamPublishedFileID publishedFileID) {
      return new SteamAPICall(getUserItemVote(this.pointer, this.callback, publishedFileID.handle));
   }

   public SteamAPICall addItemToFavorites(int appID, SteamPublishedFileID publishedFileID) {
      return new SteamAPICall(addItemToFavorites(this.pointer, this.callback, appID, publishedFileID.handle));
   }

   public SteamAPICall removeItemFromFavorites(int appID, SteamPublishedFileID publishedFileID) {
      return new SteamAPICall(removeItemFromFavorites(this.pointer, this.callback, appID, publishedFileID.handle));
   }

   public SteamAPICall subscribeItem(SteamPublishedFileID publishedFileID) {
      return new SteamAPICall(subscribeItem(this.pointer, this.callback, publishedFileID.handle));
   }

   public SteamAPICall unsubscribeItem(SteamPublishedFileID publishedFileID) {
      return new SteamAPICall(unsubscribeItem(this.pointer, this.callback, publishedFileID.handle));
   }

   public int getNumSubscribedItems() {
      return getNumSubscribedItems(this.pointer);
   }

   public int getSubscribedItems(SteamPublishedFileID[] publishedFileIds) {
      long[] ids = new long[publishedFileIds.length];
      int nb = getSubscribedItems(this.pointer, ids, publishedFileIds.length);

      for (int i = 0; i < nb; i++) {
         publishedFileIds[i] = new SteamPublishedFileID(ids[i]);
      }

      return nb;
   }

   public Collection<SteamUGC.ItemState> getItemState(SteamPublishedFileID publishedFileID) {
      return SteamUGC.ItemState.fromBits(getItemState(this.pointer, publishedFileID.handle));
   }

   public boolean getItemInstallInfo(SteamPublishedFileID publishedFileID, SteamUGC.ItemInstallInfo installInfo) {
      return getItemInstallInfo(this.pointer, publishedFileID.handle, installInfo);
   }

   public boolean getItemDownloadInfo(SteamPublishedFileID publishedFileID, SteamUGC.ItemDownloadInfo downloadInfo) {
      long[] values = new long[2];
      if (getItemDownloadInfo(this.pointer, publishedFileID.handle, values)) {
         downloadInfo.bytesDownloaded = values[0];
         downloadInfo.bytesTotal = values[1];
         return true;
      } else {
         return false;
      }
   }

   public SteamAPICall deleteItem(SteamPublishedFileID publishedFileID) {
      return new SteamAPICall(deleteItem(this.pointer, this.callback, publishedFileID.handle));
   }

   public boolean downloadItem(SteamPublishedFileID publishedFileID, boolean highPriority) {
      return downloadItem(this.pointer, publishedFileID.handle, highPriority);
   }

   public boolean initWorkshopForGameServer(int workshopDepotID, String folder) {
      return initWorkshopForGameServer(this.pointer, workshopDepotID, folder);
   }

   public void suspendDownloads(boolean suspend) {
      suspendDownloads(this.pointer, suspend);
   }

   public SteamAPICall startPlaytimeTracking(SteamPublishedFileID[] publishedFileIDs) {
      long[] ids = new long[publishedFileIDs.length];

      for (int i = 0; i < ids.length; i++) {
         ids[i] = publishedFileIDs[i].handle;
      }

      return new SteamAPICall(startPlaytimeTracking(this.pointer, this.callback, ids, ids.length));
   }

   public SteamAPICall stopPlaytimeTracking(SteamPublishedFileID[] publishedFileIDs) {
      long[] ids = new long[publishedFileIDs.length];

      for (int i = 0; i < ids.length; i++) {
         ids[i] = publishedFileIDs[i].handle;
      }

      return new SteamAPICall(stopPlaytimeTracking(this.pointer, this.callback, ids, ids.length));
   }

   public SteamAPICall stopPlaytimeTrackingForAllItems() {
      return new SteamAPICall(stopPlaytimeTrackingForAllItems(this.pointer, this.callback));
   }

   private static native long createCallback(SteamUGCCallbackAdapter var0);

   private static native long createQueryUserUGCRequest(long var0, int var2, int var3, int var4, int var5, int var6, int var7, int var8);

   private static native long createQueryAllUGCRequest(long var0, int var2, int var3, int var4, int var5, int var6);

   private static native long createQueryUGCDetailsRequest(long var0, long[] var2, int var3);

   private static native long sendQueryUGCRequest(long var0, long var2, long var4);

   private static native boolean getQueryUGCResult(long var0, long var2, int var4, SteamUGCDetails var5);

   private static native String getQueryUGCPreviewURL(long var0, long var2, int var4);

   private static native String getQueryUGCMetadata(long var0, long var2, int var4);

   private static native long getQueryUGCStatistic(long var0, long var2, int var4, int var5);

   private static native int getQueryUGCNumAdditionalPreviews(long var0, long var2, int var4);

   private static native boolean getQueryUGCAdditionalPreview(long var0, long var2, int var4, int var5, SteamUGC.ItemAdditionalPreview var6);

   private static native int getQueryUGCNumKeyValueTags(long var0, long var2, int var4);

   private static native boolean getQueryUGCKeyValueTag(long var0, long var2, int var4, int var5, String[] var6);

   private static native boolean releaseQueryUserUGCRequest(long var0, long var2);

   private static native boolean addRequiredTag(long var0, long var2, String var4);

   private static native boolean addExcludedTag(long var0, long var2, String var4);

   private static native boolean setReturnOnlyIDs(long var0, long var2, boolean var4);

   private static native boolean setReturnKeyValueTags(long var0, long var2, boolean var4);

   private static native boolean setReturnLongDescription(long var0, long var2, boolean var4);

   private static native boolean setReturnMetadata(long var0, long var2, boolean var4);

   private static native boolean setReturnChildren(long var0, long var2, boolean var4);

   private static native boolean setReturnAdditionalPreviews(long var0, long var2, boolean var4);

   private static native boolean setReturnTotalOnly(long var0, long var2, boolean var4);

   private static native boolean setReturnPlaytimeStats(long var0, long var2, int var4);

   private static native boolean setLanguage(long var0, long var2, String var4);

   private static native boolean setAllowCachedResponse(long var0, long var2, int var4);

   private static native boolean setCloudFileNameFilter(long var0, long var2, String var4);

   private static native boolean setMatchAnyTag(long var0, long var2, boolean var4);

   private static native boolean setSearchText(long var0, long var2, String var4);

   private static native boolean setRankedByTrendDays(long var0, long var2, int var4);

   private static native boolean addRequiredKeyValueTag(long var0, long var2, String var4, String var5);

   private static native long requestUGCDetails(long var0, long var2, long var4, int var6);

   private static native long createItem(long var0, long var2, int var4, int var5);

   private static native long startItemUpdate(long var0, int var2, long var3);

   private static native boolean setItemTitle(long var0, long var2, String var4);

   private static native boolean setItemDescription(long var0, long var2, String var4);

   private static native boolean setItemUpdateLanguage(long var0, long var2, String var4);

   private static native boolean setItemMetadata(long var0, long var2, String var4);

   private static native boolean setItemVisibility(long var0, long var2, int var4);

   private static native boolean setItemTags(long var0, long var2, String[] var4, int var5);

   private static native boolean setItemContent(long var0, long var2, String var4);

   private static native boolean setItemPreview(long var0, long var2, String var4);

   private static native boolean removeItemKeyValueTags(long var0, long var2, String var4);

   private static native boolean addItemKeyValueTag(long var0, long var2, String var4, String var5);

   private static native long submitItemUpdate(long var0, long var2, long var4, String var6);

   private static native int getItemUpdateProgress(long var0, long var2, long[] var4);

   private static native long setUserItemVote(long var0, long var2, long var4, boolean var6);

   private static native long getUserItemVote(long var0, long var2, long var4);

   private static native long addItemToFavorites(long var0, long var2, int var4, long var5);

   private static native long removeItemFromFavorites(long var0, long var2, int var4, long var5);

   private static native long subscribeItem(long var0, long var2, long var4);

   private static native long unsubscribeItem(long var0, long var2, long var4);

   private static native int getNumSubscribedItems(long var0);

   private static native int getSubscribedItems(long var0, long[] var2, int var3);

   private static native int getItemState(long var0, long var2);

   private static native boolean getItemInstallInfo(long var0, long var2, SteamUGC.ItemInstallInfo var4);

   private static native boolean getItemDownloadInfo(long var0, long var2, long[] var4);

   private static native long deleteItem(long var0, long var2, long var4);

   private static native boolean downloadItem(long var0, long var2, boolean var4);

   private static native boolean initWorkshopForGameServer(long var0, int var2, String var3);

   private static native void suspendDownloads(long var0, boolean var2);

   private static native long startPlaytimeTracking(long var0, long var2, long[] var4, int var5);

   private static native long stopPlaytimeTracking(long var0, long var2, long[] var4, int var5);

   private static native long stopPlaytimeTrackingForAllItems(long var0, long var2);

   public static class ItemAdditionalPreview {
      private String urlOrVideoID;
      private String originalFileName;
      private int previewType;

      public String getUrlOrVideoID() {
         return this.urlOrVideoID;
      }

      public String getOriginalFileName() {
         return this.originalFileName;
      }

      public SteamUGC.ItemPreviewType getPreviewType() {
         return SteamUGC.ItemPreviewType.byValue(this.previewType);
      }
   }

   public static class ItemDownloadInfo {
      long bytesDownloaded;
      long bytesTotal;

      public long getBytesDownloaded() {
         return this.bytesDownloaded;
      }

      public long getBytesTotal() {
         return this.bytesTotal;
      }
   }

   public static class ItemInstallInfo {
      private String folder;
      private int sizeOnDisk;

      public String getFolder() {
         return this.folder;
      }

      public int getSizeOnDisk() {
         return this.sizeOnDisk;
      }
   }

   public static enum ItemPreviewType {
      Image(0),
      YouTubeVideo(1),
      Sketchfab(2),
      EnvironmentMap_HorizontalCross(3),
      EnvironmentMap_LatLong(4),
      ReservedMax(255),
      UnknownPreviewType_NotImplementedByAPI(-1);

      private final int value;
      private static final SteamUGC.ItemPreviewType[] values = values();

      private ItemPreviewType(int value) {
         this.value = value;
      }

      static SteamUGC.ItemPreviewType byValue(int value) {
         for (SteamUGC.ItemPreviewType type : values) {
            if (type.value == value) {
               return type;
            }
         }

         return UnknownPreviewType_NotImplementedByAPI;
      }
   }

   public static enum ItemState {
      None(0),
      Subscribed(1),
      LegacyItem(2),
      Installed(4),
      NeedsUpdate(8),
      Downloading(16),
      DownloadPending(32);

      private final int bits;
      private static final SteamUGC.ItemState[] values = values();

      private ItemState(int bits) {
         this.bits = bits;
      }

      static Collection<SteamUGC.ItemState> fromBits(int bits) {
         EnumSet<SteamUGC.ItemState> itemStates = EnumSet.noneOf(SteamUGC.ItemState.class);

         for (SteamUGC.ItemState itemState : values) {
            if ((bits & itemState.bits) == itemState.bits) {
               itemStates.add(itemState);
            }
         }

         return itemStates;
      }
   }

   public static enum ItemStatistic {
      NumSubscriptions,
      NumFavorites,
      NumFollowers,
      NumUniqueSubscriptions,
      NumUniqueFavorites,
      NumUniqueFollowers,
      NumUniqueWebsiteViews,
      ReportScore,
      NumSecondsPlayed,
      NumPlaytimeSessions,
      NumComments,
      NumSecondsPlayedDuringTimePeriod,
      NumPlaytimeSessionsDuringTimePeriod;
   }

   public static class ItemUpdateInfo {
      long bytesProcessed;
      long bytesTotal;

      public long getBytesProcessed() {
         return this.bytesProcessed;
      }

      public long getBytesTotal() {
         return this.bytesTotal;
      }
   }

   public static enum ItemUpdateStatus {
      Invalid,
      PreparingConfig,
      PreparingContent,
      UploadingContent,
      UploadingPreviewFile,
      CommittingChanges;

      private static final SteamUGC.ItemUpdateStatus[] values = values();

      static SteamUGC.ItemUpdateStatus byOrdinal(int value) {
         return values[value];
      }
   }

   public static enum MatchingUGCType {
      Items(0),
      ItemsMtx(1),
      ItemsReadyToUse(2),
      Collections(3),
      Artwork(4),
      Videos(5),
      Screenshots(6),
      AllGuides(7),
      WebGuides(8),
      IntegratedGuides(9),
      UsableInGame(10),
      ControllerBindings(11),
      GameManagedItems(12),
      All(-1);

      private final int value;

      private MatchingUGCType(int value) {
         this.value = value;
      }
   }

   public static enum UGCQueryType {
      RankedByVote,
      RankedByPublicationDate,
      AcceptedForGameRankedByAcceptanceDate,
      RankedByTrend,
      FavoritedByFriendsRankedByPublicationDate,
      CreatedByFriendsRankedByPublicationDate,
      RankedByNumTimesReported,
      CreatedByFollowedUsersRankedByPublicationDate,
      NotYetRated,
      RankedByTotalVotesAsc,
      RankedByVotesUp,
      RankedByTextSearch,
      RankedByTotalUniqueSubscriptions,
      RankedByPlaytimeTrend,
      RankedByTotalPlaytime,
      RankedByAveragePlaytimeTrend,
      RankedByLifetimeAveragePlaytime,
      RankedByPlaytimeSessionsTrend,
      RankedByLifetimePlaytimeSessions;
   }

   public static enum UserUGCList {
      Published,
      VotedOn,
      VotedUp,
      VotedDown,
      WillVoteLater,
      Favorited,
      Subscribed,
      UsedOrPlayed,
      Followed;
   }

   public static enum UserUGCListSortOrder {
      CreationOrderDesc,
      CreationOrderAsc,
      TitleAsc,
      LastUpdatedDesc,
      SubscriptionDateDesc,
      VoteScoreDesc,
      ForModeration;
   }
}
