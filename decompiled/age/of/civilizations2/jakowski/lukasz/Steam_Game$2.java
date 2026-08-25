package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.codedisaster.steamworks.SteamID;
import com.codedisaster.steamworks.SteamLeaderboardEntriesHandle;
import com.codedisaster.steamworks.SteamLeaderboardEntry;
import com.codedisaster.steamworks.SteamLeaderboardHandle;
import com.codedisaster.steamworks.SteamResult;
import com.codedisaster.steamworks.SteamUserStats;
import com.codedisaster.steamworks.SteamUserStatsCallback;

class Steam_Game$2 implements SteamUserStatsCallback {
   final Steam_Game this$0;

   Steam_Game$2(Steam_Game var1) {
      this.this$0 = var1;
   }

   @Override
   public void onGlobalStatsReceived(long var1, SteamResult var3) {
      Gdx.app.log("AoC", "onGlobalStatsReceived");
   }

   @Override
   public void onLeaderboardFindResult(SteamLeaderboardHandle var1, boolean var2) {
      Application var3 = Gdx.app;
      StringBuilder var4 = new StringBuilder().append("Leaderboard find result: handle=").append(var1.toString()).append(", found=");
      String var5;
      if (var2) {
         var5 = "yes";
      } else {
         var5 = "no";
      }

      var3.log("AoC", var4.append(var5).toString());
      if (var2) {
         Gdx.app
            .log(
               "AoC",
               "Leaderboard: name=" + this.this$0.userStats.getLeaderboardName(var1) + ", entries=" + this.this$0.userStats.getLeaderboardEntryCount(var1)
            );
         this.this$0.currentLeaderboard = var1;
         this.this$0.userStats.downloadLeaderboardEntriesForUsers(this.this$0.currentLeaderboard, new SteamID[]{this.this$0.user.getSteamID()});
      } else {
         this.this$0.currentLeaderboard = var1;
         this.this$0.userStats.uploadLeaderboardScore(this.this$0.currentLeaderboard, SteamUserStats.LeaderboardUploadScoreMethod.KeepBest, 1, null);
      }
   }

   @Override
   public void onLeaderboardScoreUploaded(boolean var1, SteamLeaderboardHandle var2, int var3, boolean var4, int var5, int var6) {
      Application var7 = Gdx.app;
      StringBuilder var8 = new StringBuilder().append("Leaderboard score uploaded: ");
      String var9;
      if (var1) {
         var9 = "yes";
      } else {
         var9 = "no";
      }

      StringBuilder var11 = var8.append(var9).append(", handle=").append(var2.toString()).append(", score=").append(var3).append(", changed=");
      String var10;
      if (var4) {
         var10 = "yes";
      } else {
         var10 = "no";
      }

      var7.log("AoC", var11.append(var10).append(", globalRankNew=").append(var5).append(", globalRankPrevious=").append(var6).toString());
      this.this$0.userStats.storeStats();
   }

   @Override
   public void onLeaderboardScoresDownloaded(SteamLeaderboardHandle var1, SteamLeaderboardEntriesHandle var2, int var3) {
      Gdx.app.log("AoC", "Leaderboard scores downloaded: handle=" + var1.toString() + ", entries=" + var2.toString() + ", count=" + var3);
      if (var3 == 0) {
         this.this$0.userStats.uploadLeaderboardScore(this.this$0.currentLeaderboard, SteamUserStats.LeaderboardUploadScoreMethod.KeepBest, 1, null);
      } else {
         for (int var4 = 0; var4 < var3; var4++) {
            SteamLeaderboardEntry var5 = new SteamLeaderboardEntry();
            if (this.this$0.userStats.getDownloadedLeaderboardEntry(var2, var4, var5, null)) {
               Gdx.app
                  .log(
                     "AoC",
                     "Leaderboard entry #"
                        + var4
                        + ": steamIDUser="
                        + var5.getSteamIDUser().getAccountID()
                        + ", globalRank="
                        + var5.getGlobalRank()
                        + ", score="
                        + var5.getScore()
                  );
               Steam_Game.iScore = Math.max(Steam_Game.iScore + 1, var5.getScore() + 1);
               this.this$0
                  .userStats
                  .uploadLeaderboardScore(this.this$0.currentLeaderboard, SteamUserStats.LeaderboardUploadScoreMethod.KeepBest, Steam_Game.iScore, null);
            }
         }
      }
   }

   @Override
   public void onUserAchievementStored(long var1, boolean var3, String var4, int var5, int var6) {
      Gdx.app.log("AoC", "onUserAchievementStored");
   }

   @Override
   public void onUserStatsReceived(long var1, SteamID var3, SteamResult var4) {
      Gdx.app.log("AoC", "User stats received: gameId=" + var1 + ", userId=" + var3 + ", result=" + var4.toString());
      int var5 = this.this$0.userStats.getNumAchievements();
      Gdx.app.log("AoC", "Num of achievements: " + var5);

      for (int var6 = 0; var6 < var5; var6++) {
         String var9 = this.this$0.userStats.getAchievementName(var6);
         boolean var7 = this.this$0.userStats.isAchieved(var9, false);
         Application var11 = Gdx.app;
         StringBuilder var8 = new StringBuilder().append("# ").append(var6).append(" : name=").append(var9).append(", achieved=");
         String var10;
         if (var7) {
            var10 = "yes";
         } else {
            var10 = "no";
         }

         var11.log("AoC", var8.append(var10).toString());
      }

      this.this$0.S_VASS = this.this$0.userStats.getStatI("S_VASS", 0);
      Gdx.app.log("AoC", "S_VASS: " + this.this$0.S_VASS);
      this.this$0.S_UNIO = this.this$0.userStats.getStatI("S_UNIO", 0);
      Gdx.app.log("AoC", "S_UNIO: " + this.this$0.S_UNIO);
      this.this$0.S_ALLI = this.this$0.userStats.getStatI("S_ALLI", 0);
      Gdx.app.log("AoC", "S_ALLI: " + this.this$0.S_ALLI);
   }

   @Override
   public void onUserStatsStored(long var1, SteamResult var3) {
      Gdx.app.log("AoC", "onUserStatsStored");
   }

   @Override
   public void onUserStatsUnloaded(SteamID var1) {
      Gdx.app.log("AoC", "onUserStatsUnloaded");
   }
}
