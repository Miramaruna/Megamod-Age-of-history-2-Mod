package com.codedisaster.steamworks;

class SteamGameServerAPINative {
   static native boolean nativeInit(int var0, short var1, short var2, short var3, int var4, String var5);

   static native void nativeShutdown();

   static native void runCallbacks();

   static native boolean isSecure();

   static native long nativeGetSteamID();

   static native long getSteamGameServerPointer();

   static native long getSteamGameServerNetworkingPointer();

   static native long getSteamGameServerStatsPointer();

   static native long getSteamGameServerHTTPPointer();
}
