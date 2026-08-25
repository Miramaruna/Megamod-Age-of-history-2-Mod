package com.codedisaster.steamworks;

import java.nio.ByteBuffer;

class SteamGameServerNative {
   static native long createCallback(Object var0);

   static native void setProduct(long var0, String var2);

   static native void setGameDescription(long var0, String var2);

   static native void setModDir(long var0, String var2);

   static native void setDedicatedServer(long var0, boolean var2);

   static native void logOn(long var0, String var2);

   static native void logOnAnonymous(long var0);

   static native void logOff(long var0);

   static native boolean isLoggedOn(long var0);

   static native boolean isSecure(long var0);

   static native long getSteamID(long var0);

   static native boolean wasRestartRequested(long var0);

   static native void setMaxPlayerCount(long var0, int var2);

   static native void setBotPlayerCount(long var0, int var2);

   static native void setServerName(long var0, String var2);

   static native void setMapName(long var0, String var2);

   static native void setPasswordProtected(long var0, boolean var2);

   static native void setSpectatorPort(long var0, short var2);

   static native void setSpectatorServerName(long var0, String var2);

   static native void clearAllKeyValues(long var0);

   static native void setKeyValue(long var0, String var2, String var3);

   static native void setGameTags(long var0, String var2);

   static native void setGameData(long var0, String var2);

   static native void setRegion(long var0, String var2);

   static native boolean sendUserConnectAndAuthenticate(long var0, int var2, ByteBuffer var3, int var4, int var5, long[] var6);

   static native long createUnauthenticatedUserConnection(long var0);

   static native void sendUserDisconnect(long var0, long var2);

   static native boolean updateUserData(long var0, long var2, String var4, int var5);

   static native int getAuthSessionTicket(long var0, ByteBuffer var2, int var3, int var4, int[] var5);

   static native int beginAuthSession(long var0, ByteBuffer var2, int var3, int var4, long var5);

   static native void endAuthSession(long var0, long var2);

   static native void cancelAuthTicket(long var0, int var2);

   static native int userHasLicenseForApp(long var0, long var2, int var4);

   static native boolean requestUserGroupStatus(long var0, long var2, long var4);

   static native int getPublicIP(long var0);

   static native boolean handleIncomingPacket(long var0, ByteBuffer var2, int var3, int var4, int var5, short var6);

   static native int getNextOutgoingPacket(long var0, ByteBuffer var2, int var3, int var4, int[] var5, short[] var6);

   static native void enableHeartbeats(long var0, boolean var2);

   static native void setHeartbeatInterval(long var0, int var2);

   static native void forceHeartbeat(long var0);

   static native long associateWithClan(long var0, long var2);

   static native long computeNewPlayerCompatibility(long var0, long var2);
}
