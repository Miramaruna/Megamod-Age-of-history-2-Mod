package com.codedisaster.steamworks;

public class SteamMatchmakingServers extends SteamInterface {
   public SteamMatchmakingServers() {
      super(SteamAPI.getSteamMatchmakingServersPointer());
   }

   public SteamServerListRequest requestInternetServerList(
      int appID, SteamMatchmakingKeyValuePair[] filters, SteamMatchmakingServerListResponse requestServersResponse
   ) {
      return new SteamServerListRequest(requestInternetServerList(this.pointer, appID, filters, filters.length, requestServersResponse.callback));
   }

   public SteamServerListRequest requestLANServerList(int appID, SteamMatchmakingServerListResponse requestServersResponse) {
      return new SteamServerListRequest(requestLANServerList(this.pointer, appID, requestServersResponse.callback));
   }

   public SteamServerListRequest requestFriendsServerList(
      int appID, SteamMatchmakingKeyValuePair[] filters, SteamMatchmakingServerListResponse requestServersResponse
   ) {
      return new SteamServerListRequest(requestFriendsServerList(this.pointer, appID, filters, filters.length, requestServersResponse.callback));
   }

   public SteamServerListRequest requestFavoritesServerList(
      int appID, SteamMatchmakingKeyValuePair[] filters, SteamMatchmakingServerListResponse requestServersResponse
   ) {
      return new SteamServerListRequest(requestFavoritesServerList(this.pointer, appID, filters, filters.length, requestServersResponse.callback));
   }

   public SteamServerListRequest requestHistoryServerList(
      int appID, SteamMatchmakingKeyValuePair[] filters, SteamMatchmakingServerListResponse requestServersResponse
   ) {
      return new SteamServerListRequest(requestHistoryServerList(this.pointer, appID, filters, filters.length, requestServersResponse.callback));
   }

   public SteamServerListRequest requestSpectatorServerList(
      int appID, SteamMatchmakingKeyValuePair[] filters, SteamMatchmakingServerListResponse requestServersResponse
   ) {
      return new SteamServerListRequest(requestSpectatorServerList(this.pointer, appID, filters, filters.length, requestServersResponse.callback));
   }

   public void releaseRequest(SteamServerListRequest request) {
      releaseRequest(this.pointer, request.handle);
   }

   public boolean getServerDetails(SteamServerListRequest request, int server, SteamMatchmakingGameServerItem details) {
      return getServerDetails(this.pointer, request.handle, server, details);
   }

   public void cancelQuery(SteamServerListRequest request) {
      cancelQuery(this.pointer, request.handle);
   }

   public void refreshQuery(SteamServerListRequest request) {
      refreshQuery(this.pointer, request.handle);
   }

   public boolean isRefreshing(SteamServerListRequest request) {
      return isRefreshing(this.pointer, request.handle);
   }

   public int getServerCount(SteamServerListRequest request) {
      return getServerCount(this.pointer, request.handle);
   }

   public void refreshServer(SteamServerListRequest request, int server) {
      refreshServer(this.pointer, request.handle, server);
   }

   public SteamServerQuery pingServer(int ip, short port, SteamMatchmakingPingResponse requestServersResponse) {
      return new SteamServerQuery(pingServer(this.pointer, ip, port, requestServersResponse.callback));
   }

   public SteamServerQuery playerDetails(int ip, short port, SteamMatchmakingPlayersResponse requestServersResponse) {
      return new SteamServerQuery(playerDetails(this.pointer, ip, port, requestServersResponse.callback));
   }

   public SteamServerQuery serverRules(int ip, short port, SteamMatchmakingRulesResponse requestServersResponse) {
      return new SteamServerQuery(serverRules(this.pointer, ip, port, requestServersResponse.callback));
   }

   public void cancelServerQuery(SteamServerQuery serverQuery) {
      cancelServerQuery(this.pointer, serverQuery.handle);
   }

   private static native long requestInternetServerList(long var0, int var2, SteamMatchmakingKeyValuePair[] var3, int var4, long var5);

   private static native long requestLANServerList(long var0, int var2, long var3);

   private static native long requestFriendsServerList(long var0, int var2, SteamMatchmakingKeyValuePair[] var3, int var4, long var5);

   private static native long requestFavoritesServerList(long var0, int var2, SteamMatchmakingKeyValuePair[] var3, int var4, long var5);

   private static native long requestHistoryServerList(long var0, int var2, SteamMatchmakingKeyValuePair[] var3, int var4, long var5);

   private static native long requestSpectatorServerList(long var0, int var2, SteamMatchmakingKeyValuePair[] var3, int var4, long var5);

   private static native void releaseRequest(long var0, long var2);

   private static native boolean getServerDetails(long var0, long var2, int var4, SteamMatchmakingGameServerItem var5);

   private static native void cancelQuery(long var0, long var2);

   private static native void refreshQuery(long var0, long var2);

   private static native boolean isRefreshing(long var0, long var2);

   private static native int getServerCount(long var0, long var2);

   private static native void refreshServer(long var0, long var2, int var4);

   private static native int pingServer(long var0, int var2, short var3, long var4);

   private static native int playerDetails(long var0, int var2, short var3, long var4);

   private static native int serverRules(long var0, int var2, short var3, long var4);

   private static native void cancelServerQuery(long var0, int var2);
}
