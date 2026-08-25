package com.codedisaster.steamworks;

public class SteamUserStats extends SteamInterface {
   public SteamUserStats(SteamUserStatsCallback callback) {
      super(SteamAPI.getSteamUserStatsPointer(), createCallback(new SteamUserStatsCallbackAdapter(callback)));
   }

   public boolean requestCurrentStats() {
      return requestCurrentStats(this.pointer);
   }

   public int getStatI(String name, int defaultValue) {
      int[] values = new int[1];
      return getStat(this.pointer, name, values) ? values[0] : defaultValue;
   }

   public boolean setStatI(String name, int value) {
      return setStat(this.pointer, name, value);
   }

   public float getStatF(String name, float defaultValue) {
      float[] values = new float[1];
      return getStat(this.pointer, name, values) ? values[0] : defaultValue;
   }

   public boolean setStatF(String name, float value) {
      return setStat(this.pointer, name, value);
   }

   public boolean isAchieved(String name, boolean defaultValue) {
      boolean[] achieved = new boolean[1];
      return getAchievement(this.pointer, name, achieved) ? achieved[0] : defaultValue;
   }

   public boolean setAchievement(String name) {
      return setAchievement(this.pointer, name);
   }

   public boolean clearAchievement(String name) {
      return clearAchievement(this.pointer, name);
   }

   public boolean storeStats() {
      return storeStats(this.pointer);
   }

   public boolean indicateAchievementProgress(String name, int curProgress, int maxProgress) {
      return indicateAchievementProgress(this.pointer, name, curProgress, maxProgress);
   }

   public int getNumAchievements() {
      return getNumAchievements(this.pointer);
   }

   public String getAchievementName(int index) {
      return getAchievementName(this.pointer, index);
   }

   public boolean resetAllStats(boolean achievementsToo) {
      return resetAllStats(this.pointer, achievementsToo);
   }

   public SteamAPICall findOrCreateLeaderboard(
      String leaderboardName, SteamUserStats.LeaderboardSortMethod leaderboardSortMethod, SteamUserStats.LeaderboardDisplayType leaderboardDisplayType
   ) {
      return new SteamAPICall(
         findOrCreateLeaderboard(this.pointer, this.callback, leaderboardName, leaderboardSortMethod.ordinal(), leaderboardDisplayType.ordinal())
      );
   }

   public SteamAPICall findLeaderboard(String leaderboardName) {
      return new SteamAPICall(findLeaderboard(this.pointer, this.callback, leaderboardName));
   }

   public String getLeaderboardName(SteamLeaderboardHandle leaderboard) {
      return getLeaderboardName(this.pointer, leaderboard.handle);
   }

   public int getLeaderboardEntryCount(SteamLeaderboardHandle leaderboard) {
      return getLeaderboardEntryCount(this.pointer, leaderboard.handle);
   }

   public SteamUserStats.LeaderboardSortMethod getLeaderboardSortMethod(SteamLeaderboardHandle leaderboard) {
      return SteamUserStats.LeaderboardSortMethod.values()[getLeaderboardSortMethod(this.pointer, leaderboard.handle)];
   }

   public SteamUserStats.LeaderboardDisplayType getLeaderboardDisplayType(SteamLeaderboardHandle leaderboard) {
      return SteamUserStats.LeaderboardDisplayType.values()[getLeaderboardDisplayType(this.pointer, leaderboard.handle)];
   }

   public SteamAPICall downloadLeaderboardEntries(
      SteamLeaderboardHandle leaderboard, SteamUserStats.LeaderboardDataRequest leaderboardDataRequest, int rangeStart, int rangeEnd
   ) {
      return new SteamAPICall(
         downloadLeaderboardEntries(this.pointer, this.callback, leaderboard.handle, leaderboardDataRequest.ordinal(), rangeStart, rangeEnd)
      );
   }

   public SteamAPICall downloadLeaderboardEntriesForUsers(SteamLeaderboardHandle leaderboard, SteamID[] users) {
      int count = users.length;
      long[] handles = new long[count];

      for (int i = 0; i < count; i++) {
         handles[i] = users[i].handle;
      }

      return new SteamAPICall(downloadLeaderboardEntriesForUsers(this.pointer, this.callback, leaderboard.handle, handles, count));
   }

   public boolean getDownloadedLeaderboardEntry(SteamLeaderboardEntriesHandle leaderboardEntries, int index, SteamLeaderboardEntry entry, int[] details) {
      return details == null
         ? getDownloadedLeaderboardEntry(this.pointer, leaderboardEntries.handle, index, entry)
         : getDownloadedLeaderboardEntry(this.pointer, leaderboardEntries.handle, index, entry, details, details.length);
   }

   public SteamAPICall uploadLeaderboardScore(
      SteamLeaderboardHandle leaderboard, SteamUserStats.LeaderboardUploadScoreMethod method, int score, int[] scoreDetails
   ) {
      return new SteamAPICall(
         scoreDetails == null
            ? uploadLeaderboardScore(this.pointer, this.callback, leaderboard.handle, method.ordinal(), score)
            : uploadLeaderboardScore(this.pointer, this.callback, leaderboard.handle, method.ordinal(), score, scoreDetails, scoreDetails.length)
      );
   }

   public SteamAPICall requestGlobalStats(int historyDays) {
      return new SteamAPICall(requestGlobalStats(this.pointer, this.callback, historyDays));
   }

   public long getGlobalStat(String name, long defaultValue) {
      long[] values = new long[1];
      return getGlobalStat(this.pointer, name, values) ? values[0] : defaultValue;
   }

   public double getGlobalStat(String name, double defaultValue) {
      double[] values = new double[1];
      return getGlobalStat(this.pointer, name, values) ? values[0] : defaultValue;
   }

   public int getGlobalStatHistory(String name, long[] data) {
      return getGlobalStatHistory(this.pointer, name, data, data.length);
   }

   public int getGlobalStatHistory(String name, double[] data) {
      return getGlobalStatHistory(this.pointer, name, data, data.length);
   }

   private static native long createCallback(SteamUserStatsCallbackAdapter var0);

   private static native boolean requestCurrentStats(long var0);

   private static native boolean getStat(long var0, String var2, int[] var3);

   private static native boolean setStat(long var0, String var2, int var3);

   private static native boolean getStat(long var0, String var2, float[] var3);

   private static native boolean setStat(long var0, String var2, float var3);

   private static native boolean getAchievement(long var0, String var2, boolean[] var3);

   private static native boolean setAchievement(long var0, String var2);

   private static native boolean clearAchievement(long var0, String var2);

   private static native boolean storeStats(long var0);

   private static native boolean indicateAchievementProgress(long var0, String var2, int var3, int var4);

   private static native int getNumAchievements(long var0);

   private static native String getAchievementName(long var0, int var2);

   private static native boolean resetAllStats(long var0, boolean var2);

   private static native long findOrCreateLeaderboard(long var0, long var2, String var4, int var5, int var6);

   private static native long findLeaderboard(long var0, long var2, String var4);

   private static native String getLeaderboardName(long var0, long var2);

   private static native int getLeaderboardEntryCount(long var0, long var2);

   private static native int getLeaderboardSortMethod(long var0, long var2);

   private static native int getLeaderboardDisplayType(long var0, long var2);

   private static native long downloadLeaderboardEntries(long var0, long var2, long var4, int var6, int var7, int var8);

   private static native long downloadLeaderboardEntriesForUsers(long var0, long var2, long var4, long[] var6, int var7);

   private static native boolean getDownloadedLeaderboardEntry(long var0, long var2, int var4, SteamLeaderboardEntry var5, int[] var6, int var7);

   private static native boolean getDownloadedLeaderboardEntry(long var0, long var2, int var4, SteamLeaderboardEntry var5);

   private static native long uploadLeaderboardScore(long var0, long var2, long var4, int var6, int var7, int[] var8, int var9);

   private static native long uploadLeaderboardScore(long var0, long var2, long var4, int var6, int var7);

   private static native long requestGlobalStats(long var0, long var2, int var4);

   private static native boolean getGlobalStat(long var0, String var2, long[] var3);

   private static native boolean getGlobalStat(long var0, String var2, double[] var3);

   private static native int getGlobalStatHistory(long var0, String var2, long[] var3, int var4);

   private static native int getGlobalStatHistory(long var0, String var2, double[] var3, int var4);

   public static enum LeaderboardDataRequest {
      Global,
      GlobalAroundUser,
      Friends,
      Users;
   }

   public static enum LeaderboardDisplayType {
      None,
      Numeric,
      TimeSeconds,
      TimeMilliSeconds;
   }

   public static enum LeaderboardSortMethod {
      None,
      Ascending,
      Descending;
   }

   public static enum LeaderboardUploadScoreMethod {
      None,
      KeepBest,
      ForceUpdate;
   }
}
