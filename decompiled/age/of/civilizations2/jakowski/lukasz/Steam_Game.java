package age.of.civilizations2.jakowski.lukasz;

import com.codedisaster.steamworks.SteamApps;
import com.codedisaster.steamworks.SteamFriends;
import com.codedisaster.steamworks.SteamLeaderboardHandle;
import com.codedisaster.steamworks.SteamRemoteStorage;
import com.codedisaster.steamworks.SteamUGC;
import com.codedisaster.steamworks.SteamUser;
import com.codedisaster.steamworks.SteamUserStats;
import com.codedisaster.steamworks.SteamUtils;

public class Steam_Game {
   public SteamUser user;
   public SteamUserStats userStats;
   public SteamRemoteStorage remoteStorage;
   public SteamUGC ugc;
   public SteamUtils utils;
   public SteamApps apps;
   public SteamFriends friends;
   public int S_VASS = 0;
   public int S_UNIO = 0;
   public int S_ALLI = 0;
   public static int iScore = -1;
   public SteamLeaderboardHandle currentLeaderboard = null;

   public final void uploadVassals() {
   }

   public final void uploadUnions() {
   }

   public final void uploadAlliance() {
   }

   public final void uploadScore() {
   }

   public final void checkGovermentAchievement(int toIdeologyID) {
   }

   public final void checkFormableAchievement(int nCivID) {
   }

   public final void uploadScore_OnSave() {
   }

   public final void checkAchievement() {
   }

   public Steam_Game() {
      if (CFG.isDesktop()) {
      }
   }
}
