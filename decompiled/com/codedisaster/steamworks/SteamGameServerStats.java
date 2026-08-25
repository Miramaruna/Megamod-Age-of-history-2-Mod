package com.codedisaster.steamworks;

public class SteamGameServerStats extends SteamInterface {
   public SteamGameServerStats(SteamGameServerStatsCallback callback) {
      super(
         SteamGameServerAPINative.getSteamGameServerStatsPointer(),
         SteamGameServerStatsNative.createCallback(new SteamGameServerStatsCallbackAdapter(callback))
      );
   }

   public SteamAPICall requestUserStats(SteamID steamIDUser) {
      return new SteamAPICall(SteamGameServerStatsNative.requestUserStats(this.pointer, steamIDUser.handle));
   }

   public int getUserStatI(SteamID steamIDUser, String name, int defaultValue) {
      int[] values = new int[1];
      return SteamGameServerStatsNative.getUserStat(this.pointer, steamIDUser.handle, name, values) ? values[0] : defaultValue;
   }

   public float getUserStatF(SteamID steamIDUser, String name, float defaultValue) {
      float[] values = new float[1];
      return SteamGameServerStatsNative.getUserStat(this.pointer, steamIDUser.handle, name, values) ? values[0] : defaultValue;
   }

   public boolean getUserAchievement(SteamID steamIDUser, String name, boolean defaultValue) {
      boolean[] achieved = new boolean[1];
      return SteamGameServerStatsNative.getUserAchievement(this.pointer, steamIDUser.handle, name, achieved) ? achieved[0] : defaultValue;
   }

   public boolean setUserStatI(SteamID steamIDUser, String name, int value) {
      return SteamGameServerStatsNative.setUserStat(this.pointer, steamIDUser.handle, name, value);
   }

   public boolean setUserStatF(SteamID steamIDUser, String name, float value) {
      return SteamGameServerStatsNative.setUserStat(this.pointer, steamIDUser.handle, name, value);
   }

   public boolean updateUserAvgRateStat(SteamID steamIDUser, String name, float countThisSession, double sessionLength) {
      return SteamGameServerStatsNative.updateUserAvgRateStat(this.pointer, steamIDUser.handle, name, countThisSession, sessionLength);
   }

   public boolean setUserAchievement(SteamID steamIDUser, String name) {
      return SteamGameServerStatsNative.setUserAchievement(this.pointer, steamIDUser.handle, name);
   }

   public boolean clearUserAchievement(SteamID steamIDUser, String name) {
      return SteamGameServerStatsNative.clearUserAchievement(this.pointer, steamIDUser.handle, name);
   }

   public SteamAPICall storeUserStats(SteamID steamIDUser) {
      return new SteamAPICall(SteamGameServerStatsNative.storeUserStats(this.pointer, steamIDUser.handle));
   }
}
