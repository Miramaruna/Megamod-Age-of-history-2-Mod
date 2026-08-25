package com.codedisaster.steamworks;

class SteamGameServerStatsNative {
   static native long createCallback(Object var0);

   static native long requestUserStats(long var0, long var2);

   static native boolean getUserStat(long var0, long var2, String var4, int[] var5);

   static native boolean getUserStat(long var0, long var2, String var4, float[] var5);

   static native boolean getUserAchievement(long var0, long var2, String var4, boolean[] var5);

   static native boolean setUserStat(long var0, long var2, String var4, int var5);

   static native boolean setUserStat(long var0, long var2, String var4, float var5);

   static native boolean updateUserAvgRateStat(long var0, long var2, String var4, float var5, double var6);

   static native boolean setUserAchievement(long var0, long var2, String var4);

   static native boolean clearUserAchievement(long var0, long var2, String var4);

   static native long storeUserStats(long var0, long var2);
}
