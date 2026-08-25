package com.codedisaster.steamworks;

import java.nio.ByteBuffer;

public class SteamRemoteStorage extends SteamInterface {
   public SteamRemoteStorage(SteamRemoteStorageCallback callback) {
      super(SteamAPI.getSteamRemoteStoragePointer(), createCallback(new SteamRemoteStorageCallbackAdapter(callback)));
   }

   public boolean fileWrite(String file, ByteBuffer data) throws SteamException {
      if (!data.isDirect()) {
         throw new SteamException("Direct buffer required!");
      } else {
         return fileWrite(this.pointer, file, data, data.position(), data.remaining());
      }
   }

   public boolean fileRead(String file, ByteBuffer buffer) throws SteamException {
      if (!buffer.isDirect()) {
         throw new SteamException("Direct buffer required!");
      } else {
         return fileRead(this.pointer, file, buffer, buffer.position(), buffer.remaining());
      }
   }

   public SteamAPICall fileWriteAsync(String file, ByteBuffer data) throws SteamException {
      if (!data.isDirect()) {
         throw new SteamException("Direct buffer required!");
      } else {
         return new SteamAPICall(fileWriteAsync(this.pointer, this.callback, file, data, data.position(), data.remaining()));
      }
   }

   public SteamAPICall fileReadAsync(String file, int offset, int toRead) {
      return new SteamAPICall(fileReadAsync(this.pointer, this.callback, file, offset, toRead));
   }

   public boolean fileReadAsyncComplete(SteamAPICall readCall, ByteBuffer buffer, int toRead) {
      return fileReadAsyncComplete(this.pointer, readCall.handle, buffer, buffer.position(), toRead);
   }

   public boolean fileForget(String file) {
      return fileForget(this.pointer, file);
   }

   public boolean fileDelete(String file) {
      return fileDelete(this.pointer, file);
   }

   public SteamAPICall fileShare(String file) {
      return new SteamAPICall(fileShare(this.pointer, this.callback, file));
   }

   public boolean setSyncPlatforms(String file, SteamRemoteStorage.RemoteStoragePlatform remoteStoragePlatform) {
      return setSyncPlatforms(this.pointer, file, remoteStoragePlatform.mask);
   }

   public SteamUGCFileWriteStreamHandle fileWriteStreamOpen(String name) {
      return new SteamUGCFileWriteStreamHandle(fileWriteStreamOpen(this.pointer, name));
   }

   public boolean fileWriteStreamWriteChunk(SteamUGCFileWriteStreamHandle stream, ByteBuffer data) {
      return fileWriteStreamWriteChunk(this.pointer, stream.handle, data, data.position(), data.remaining());
   }

   public boolean fileWriteStreamClose(SteamUGCFileWriteStreamHandle stream) {
      return fileWriteStreamClose(this.pointer, stream.handle);
   }

   public boolean fileWriteStreamCancel(SteamUGCFileWriteStreamHandle stream) {
      return fileWriteStreamCancel(this.pointer, stream.handle);
   }

   public boolean fileExists(String file) {
      return fileExists(this.pointer, file);
   }

   public boolean filePersisted(String file) {
      return filePersisted(this.pointer, file);
   }

   public int getFileSize(String file) {
      return getFileSize(this.pointer, file);
   }

   public long getFileTimestamp(String file) {
      return getFileTimestamp(this.pointer, file);
   }

   public SteamRemoteStorage.RemoteStoragePlatform[] getSyncPlatforms(String file) {
      int mask = getSyncPlatforms(this.pointer, file);
      return SteamRemoteStorage.RemoteStoragePlatform.byMask(mask);
   }

   public int getFileCount() {
      return getFileCount(this.pointer);
   }

   public String getFileNameAndSize(int index, int[] sizes) {
      return getFileNameAndSize(this.pointer, index, sizes);
   }

   public boolean getQuota(long[] totalBytes, long[] availableBytes) {
      return getQuota(this.pointer, totalBytes, availableBytes);
   }

   public boolean isCloudEnabledForAccount() {
      return isCloudEnabledForAccount(this.pointer);
   }

   public boolean isCloudEnabledForApp() {
      return isCloudEnabledForApp(this.pointer);
   }

   public void setCloudEnabledForApp(boolean enabled) {
      setCloudEnabledForApp(this.pointer, enabled);
   }

   public SteamAPICall ugcDownload(SteamUGCHandle fileHandle, int priority) {
      return new SteamAPICall(ugcDownload(this.pointer, this.callback, fileHandle.handle, priority));
   }

   public boolean getUGCDownloadProgress(SteamUGCHandle fileHandle, int[] bytesDownloaded, int[] bytesExpected) {
      return getUGCDownloadProgress(this.pointer, fileHandle.handle, bytesDownloaded, bytesExpected);
   }

   public int ugcRead(SteamUGCHandle fileHandle, ByteBuffer buffer, int dataToRead, int offset, SteamRemoteStorage.UGCReadAction action) {
      return ugcRead(this.pointer, fileHandle.handle, buffer, buffer.position(), dataToRead, offset, action.ordinal());
   }

   public int getCachedUGCCount() {
      return getCachedUGCCount(this.pointer);
   }

   public SteamUGCHandle getCachedUGCHandle(int cachedContent) {
      return new SteamUGCHandle(getCachedUGCHandle(this.pointer, cachedContent));
   }

   public SteamAPICall publishWorkshopFile(
      String file,
      String previewFile,
      int consumerAppID,
      String title,
      String description,
      SteamRemoteStorage.PublishedFileVisibility visibility,
      String[] tags,
      SteamRemoteStorage.WorkshopFileType workshopFileType
   ) {
      return new SteamAPICall(
         publishWorkshopFile(
            this.pointer,
            this.callback,
            file,
            previewFile,
            consumerAppID,
            title,
            description,
            visibility.ordinal(),
            tags,
            tags != null ? tags.length : 0,
            workshopFileType.ordinal()
         )
      );
   }

   public SteamPublishedFileUpdateHandle createPublishedFileUpdateRequest(SteamPublishedFileID publishedFileID) {
      return new SteamPublishedFileUpdateHandle(createPublishedFileUpdateRequest(this.pointer, publishedFileID.handle));
   }

   public boolean updatePublishedFileFile(SteamPublishedFileUpdateHandle updateHandle, String file) {
      return updatePublishedFileFile(this.pointer, updateHandle.handle, file);
   }

   public boolean updatePublishedFilePreviewFile(SteamPublishedFileUpdateHandle updateHandle, String previewFile) {
      return updatePublishedFilePreviewFile(this.pointer, updateHandle.handle, previewFile);
   }

   public boolean updatePublishedFileTitle(SteamPublishedFileUpdateHandle updateHandle, String title) {
      return updatePublishedFileTitle(this.pointer, updateHandle.handle, title);
   }

   public boolean updatePublishedFileDescription(SteamPublishedFileUpdateHandle updateHandle, String description) {
      return updatePublishedFileDescription(this.pointer, updateHandle.handle, description);
   }

   public boolean updatePublishedFileVisibility(SteamPublishedFileUpdateHandle updateHandle, SteamRemoteStorage.PublishedFileVisibility visibility) {
      return updatePublishedFileVisibility(this.pointer, updateHandle.handle, visibility.ordinal());
   }

   public boolean updatePublishedFileTags(SteamPublishedFileUpdateHandle updateHandle, String[] tags) {
      return updatePublishedFileTags(this.pointer, updateHandle.handle, tags, tags != null ? tags.length : 0);
   }

   public SteamAPICall commitPublishedFileUpdate(SteamPublishedFileUpdateHandle updateHandle) {
      return new SteamAPICall(commitPublishedFileUpdate(this.pointer, this.callback, updateHandle.handle));
   }

   private static native long createCallback(SteamRemoteStorageCallbackAdapter var0);

   private static native boolean fileWrite(long var0, String var2, ByteBuffer var3, int var4, int var5);

   private static native boolean fileRead(long var0, String var2, ByteBuffer var3, int var4, int var5);

   private static native long fileWriteAsync(long var0, long var2, String var4, ByteBuffer var5, int var6, int var7);

   private static native long fileReadAsync(long var0, long var2, String var4, int var5, int var6);

   private static native boolean fileReadAsyncComplete(long var0, long var2, ByteBuffer var4, long var5, int var7);

   private static native boolean fileForget(long var0, String var2);

   private static native boolean fileDelete(long var0, String var2);

   private static native long fileShare(long var0, long var2, String var4);

   private static native boolean setSyncPlatforms(long var0, String var2, int var3);

   private static native long fileWriteStreamOpen(long var0, String var2);

   private static native boolean fileWriteStreamWriteChunk(long var0, long var2, ByteBuffer var4, int var5, int var6);

   private static native boolean fileWriteStreamClose(long var0, long var2);

   private static native boolean fileWriteStreamCancel(long var0, long var2);

   private static native boolean fileExists(long var0, String var2);

   private static native boolean filePersisted(long var0, String var2);

   private static native int getFileSize(long var0, String var2);

   private static native long getFileTimestamp(long var0, String var2);

   private static native int getSyncPlatforms(long var0, String var2);

   private static native int getFileCount(long var0);

   private static native String getFileNameAndSize(long var0, int var2, int[] var3);

   private static native boolean getQuota(long var0, long[] var2, long[] var3);

   private static native boolean isCloudEnabledForAccount(long var0);

   private static native boolean isCloudEnabledForApp(long var0);

   private static native void setCloudEnabledForApp(long var0, boolean var2);

   private static native long ugcDownload(long var0, long var2, long var4, int var6);

   private static native boolean getUGCDownloadProgress(long var0, long var2, int[] var4, int[] var5);

   private static native int ugcRead(long var0, long var2, ByteBuffer var4, int var5, int var6, int var7, int var8);

   private static native int getCachedUGCCount(long var0);

   private static native long getCachedUGCHandle(long var0, int var2);

   private static native long publishWorkshopFile(
      long var0, long var2, String var4, String var5, int var6, String var7, String var8, int var9, String[] var10, int var11, int var12
   );

   private static native long createPublishedFileUpdateRequest(long var0, long var2);

   private static native boolean updatePublishedFileFile(long var0, long var2, String var4);

   private static native boolean updatePublishedFilePreviewFile(long var0, long var2, String var4);

   private static native boolean updatePublishedFileTitle(long var0, long var2, String var4);

   private static native boolean updatePublishedFileDescription(long var0, long var2, String var4);

   private static native boolean updatePublishedFileVisibility(long var0, long var2, int var4);

   private static native boolean updatePublishedFileTags(long var0, long var2, String[] var4, int var5);

   private static native long commitPublishedFileUpdate(long var0, long var2, long var4);

   public static enum PublishedFileVisibility {
      Public,
      FriendsOnly,
      Private;
   }

   public static enum RemoteStoragePlatform {
      None(0),
      Windows(1),
      OSX(2),
      PS3(4),
      Linux(8),
      Reserved2(16),
      All(-1);

      private final int mask;
      private static final SteamRemoteStorage.RemoteStoragePlatform[] values = values();

      private RemoteStoragePlatform(int mask) {
         this.mask = mask;
      }

      static SteamRemoteStorage.RemoteStoragePlatform[] byMask(int mask) {
         int bits = Integer.bitCount(mask);
         SteamRemoteStorage.RemoteStoragePlatform[] result = new SteamRemoteStorage.RemoteStoragePlatform[bits];
         int idx = 0;

         for (SteamRemoteStorage.RemoteStoragePlatform value : values) {
            if ((value.mask & mask) != 0) {
               result[idx++] = value;
            }
         }

         return result;
      }
   }

   public static enum UGCReadAction {
      ContinueReadingUntilFinished,
      ContinueReading,
      Close;
   }

   public static enum WorkshopFileType {
      Community,
      Microtransaction,
      Collection,
      Art,
      Video,
      Screenshot,
      Game,
      Software,
      Concept,
      WebGuide,
      IntegratedGuide,
      Merch,
      ControllerBinding,
      SteamworksAccessInvite,
      SteamVideo,
      GameManagedItem;

      private static final SteamRemoteStorage.WorkshopFileType[] values = values();

      static SteamRemoteStorage.WorkshopFileType byOrdinal(int ordinal) {
         return values[ordinal];
      }
   }
}
