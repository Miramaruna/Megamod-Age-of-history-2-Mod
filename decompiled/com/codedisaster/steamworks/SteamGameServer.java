package com.codedisaster.steamworks;

import java.nio.Buffer;
import java.nio.ByteBuffer;

public class SteamGameServer extends SteamInterface {
   public SteamGameServer(SteamGameServerCallback callback) {
      super(SteamGameServerAPINative.getSteamGameServerPointer(), SteamGameServerNative.createCallback(new SteamGameServerCallbackAdapter(callback)));
   }

   public void setProduct(String product) {
      SteamGameServerNative.setProduct(this.pointer, product);
   }

   public void setGameDescription(String gameDescription) {
      SteamGameServerNative.setGameDescription(this.pointer, gameDescription);
   }

   public void setModDir(String modDir) {
      SteamGameServerNative.setModDir(this.pointer, modDir);
   }

   public void setDedicatedServer(boolean dedicated) {
      SteamGameServerNative.setDedicatedServer(this.pointer, dedicated);
   }

   public void logOn(String token) {
      SteamGameServerNative.logOn(this.pointer, token);
   }

   public void logOnAnonymous() {
      SteamGameServerNative.logOnAnonymous(this.pointer);
   }

   public void logOff() {
      SteamGameServerNative.logOff(this.pointer);
   }

   public boolean isLoggedOn() {
      return SteamGameServerNative.isLoggedOn(this.pointer);
   }

   public boolean isSecure() {
      return SteamGameServerNative.isSecure(this.pointer);
   }

   public SteamID getSteamID() {
      return new SteamID(SteamGameServerNative.getSteamID(this.pointer));
   }

   public boolean wasRestartRequested() {
      return SteamGameServerNative.wasRestartRequested(this.pointer);
   }

   public void setMaxPlayerCount(int playersMax) {
      SteamGameServerNative.setMaxPlayerCount(this.pointer, playersMax);
   }

   public void setBotPlayerCount(int botplayers) {
      SteamGameServerNative.setBotPlayerCount(this.pointer, botplayers);
   }

   public void setServerName(String serverName) {
      SteamGameServerNative.setServerName(this.pointer, serverName);
   }

   public void setMapName(String mapName) {
      SteamGameServerNative.setMapName(this.pointer, mapName);
   }

   public void setPasswordProtected(boolean passwordProtected) {
      SteamGameServerNative.setPasswordProtected(this.pointer, passwordProtected);
   }

   public void setSpectatorPort(short spectatorPort) {
      SteamGameServerNative.setSpectatorPort(this.pointer, spectatorPort);
   }

   public void setSpectatorServerName(String spectatorServerName) {
      SteamGameServerNative.setSpectatorServerName(this.pointer, spectatorServerName);
   }

   public void clearAllKeyValues() {
      SteamGameServerNative.clearAllKeyValues(this.pointer);
   }

   public void setKeyValue(String key, String value) {
      SteamGameServerNative.setKeyValue(this.pointer, key, value);
   }

   public void setGameTags(String gameTags) {
      SteamGameServerNative.setGameTags(this.pointer, gameTags);
   }

   public void setGameData(String gameData) {
      SteamGameServerNative.setGameData(this.pointer, gameData);
   }

   public void setRegion(String region) {
      SteamGameServerNative.setRegion(this.pointer, region);
   }

   public boolean sendUserConnectAndAuthenticate(int clientIP, ByteBuffer authBlob, SteamID steamIDUser) {
      long[] ids = new long[1];
      if (SteamGameServerNative.sendUserConnectAndAuthenticate(this.pointer, clientIP, authBlob, authBlob.position(), authBlob.remaining(), ids)) {
         steamIDUser.handle = ids[0];
         return true;
      } else {
         return false;
      }
   }

   public SteamID createUnauthenticatedUserConnection() {
      return new SteamID(SteamGameServerNative.createUnauthenticatedUserConnection(this.pointer));
   }

   public void sendUserDisconnect(SteamID steamIDUser) {
      SteamGameServerNative.sendUserDisconnect(this.pointer, steamIDUser.handle);
   }

   public boolean updateUserData(SteamID steamIDUser, String playerName, int score) {
      return SteamGameServerNative.updateUserData(this.pointer, steamIDUser.handle, playerName, score);
   }

   public SteamAuthTicket getAuthSessionTicket(ByteBuffer authTicket, int[] sizeInBytes) throws SteamException {
      if (!authTicket.isDirect()) {
         throw new SteamException("Direct buffer required!");
      } else {
         int ticket = SteamGameServerNative.getAuthSessionTicket(this.pointer, authTicket, authTicket.position(), authTicket.remaining(), sizeInBytes);
         if (ticket != 0L) {
            ((Buffer)authTicket).limit(authTicket.position() + sizeInBytes[0]);
         }

         return new SteamAuthTicket(ticket);
      }
   }

   public SteamAuth.BeginAuthSessionResult beginAuthSession(ByteBuffer authTicket, SteamID steamID) throws SteamException {
      if (!authTicket.isDirect()) {
         throw new SteamException("Direct buffer required!");
      } else {
         int result = SteamGameServerNative.beginAuthSession(this.pointer, authTicket, authTicket.position(), authTicket.remaining(), steamID.handle);
         return SteamAuth.BeginAuthSessionResult.byOrdinal(result);
      }
   }

   public void endAuthSession(SteamID steamID) {
      SteamGameServerNative.endAuthSession(this.pointer, steamID.handle);
   }

   public void cancelAuthTicket(SteamAuthTicket authTicket) {
      SteamGameServerNative.cancelAuthTicket(this.pointer, (int)authTicket.handle);
   }

   public SteamAuth.UserHasLicenseForAppResult userHasLicenseForApp(SteamID steamID, int appID) {
      return SteamAuth.UserHasLicenseForAppResult.byOrdinal(SteamGameServerNative.userHasLicenseForApp(this.pointer, steamID.handle, appID));
   }

   public boolean requestUserGroupStatus(SteamID steamIDUser, SteamID steamIDGroup) {
      return SteamGameServerNative.requestUserGroupStatus(this.pointer, steamIDUser.handle, steamIDGroup.handle);
   }

   public int getPublicIP() {
      return SteamGameServerNative.getPublicIP(this.pointer);
   }

   public boolean handleIncomingPacket(ByteBuffer data, int srcIP, short srcPort) {
      return SteamGameServerNative.handleIncomingPacket(this.pointer, data, data.position(), data.remaining(), srcIP, srcPort);
   }

   public int getNextOutgoingPacket(ByteBuffer out, int[] netAdr, short[] port) {
      return SteamGameServerNative.getNextOutgoingPacket(this.pointer, out, out.position(), out.remaining(), netAdr, port);
   }

   public void enableHeartbeats(boolean active) {
      SteamGameServerNative.enableHeartbeats(this.pointer, active);
   }

   public void setHeartbeatInterval(int heartbeatInterval) {
      SteamGameServerNative.setHeartbeatInterval(this.pointer, heartbeatInterval);
   }

   public void forceHeartbeat() {
      SteamGameServerNative.forceHeartbeat(this.pointer);
   }

   public SteamAPICall associateWithClan(SteamID steamIDClan) {
      return new SteamAPICall(SteamGameServerNative.associateWithClan(this.pointer, steamIDClan.handle));
   }

   public SteamAPICall computeNewPlayerCompatibility(SteamID steamIDNewPlayer) {
      return new SteamAPICall(SteamGameServerNative.computeNewPlayerCompatibility(this.pointer, steamIDNewPlayer.handle));
   }

   public static enum DenyReason {
      Invalid,
      InvalidVersion,
      Generic,
      NotLoggedOn,
      NoLicense,
      Cheater,
      LoggedInElseWhere,
      UnknownText,
      IncompatibleAnticheat,
      MemoryCorruption,
      IncompatibleSoftware,
      SteamConnectionLost,
      SteamConnectionError,
      SteamResponseTimedOut,
      SteamValidationStalled,
      SteamOwnerLeftGuestUser;

      private static final SteamGameServer.DenyReason[] values = values();

      static SteamGameServer.DenyReason byOrdinal(int denyReason) {
         return values[denyReason];
      }
   }
}
