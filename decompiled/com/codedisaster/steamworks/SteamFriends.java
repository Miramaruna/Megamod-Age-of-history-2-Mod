package com.codedisaster.steamworks;

import java.util.Collection;

public class SteamFriends extends SteamInterface {
   public SteamFriends(SteamFriendsCallback callback) {
      super(SteamAPI.getSteamFriendsPointer(), createCallback(new SteamFriendsCallbackAdapter(callback)));
   }

   public String getPersonaName() {
      return getPersonaName(this.pointer);
   }

   public SteamAPICall setPersonaName(String personaName) {
      return new SteamAPICall(setPersonaName(this.pointer, this.callback, personaName));
   }

   public SteamFriends.PersonaState getPersonaState() {
      return SteamFriends.PersonaState.byOrdinal(getPersonaState(this.pointer));
   }

   public int getFriendCount(SteamFriends.FriendFlags friendFlag) {
      return getFriendCount(this.pointer, friendFlag.bits);
   }

   public int getFriendCount(Collection<SteamFriends.FriendFlags> friendFlags) {
      return getFriendCount(this.pointer, SteamFriends.FriendFlags.asBits(friendFlags));
   }

   public SteamID getFriendByIndex(int friend, SteamFriends.FriendFlags friendFlag) {
      return new SteamID(getFriendByIndex(this.pointer, friend, friendFlag.bits));
   }

   public SteamID getFriendByIndex(int friend, Collection<SteamFriends.FriendFlags> friendFlags) {
      return new SteamID(getFriendByIndex(this.pointer, friend, SteamFriends.FriendFlags.asBits(friendFlags)));
   }

   public SteamFriends.FriendRelationship getFriendRelationship(SteamID steamIDFriend) {
      return SteamFriends.FriendRelationship.byOrdinal(getFriendRelationship(this.pointer, steamIDFriend.handle));
   }

   public SteamFriends.PersonaState getFriendPersonaState(SteamID steamIDFriend) {
      return SteamFriends.PersonaState.byOrdinal(getFriendPersonaState(this.pointer, steamIDFriend.handle));
   }

   public String getFriendPersonaName(SteamID steamIDFriend) {
      return getFriendPersonaName(this.pointer, steamIDFriend.handle);
   }

   public boolean getFriendGamePlayed(SteamID steamIDFriend, SteamFriends.FriendGameInfo friendGameInfo) {
      return getFriendGamePlayed(this.pointer, steamIDFriend.handle, friendGameInfo);
   }

   public void setInGameVoiceSpeaking(SteamID steamID, boolean speaking) {
      setInGameVoiceSpeaking(this.pointer, steamID.handle, speaking);
   }

   public int getSmallFriendAvatar(SteamID steamID) {
      return getSmallFriendAvatar(this.pointer, steamID.handle);
   }

   public int getMediumFriendAvatar(SteamID steamID) {
      return getMediumFriendAvatar(this.pointer, steamID.handle);
   }

   public int getLargeFriendAvatar(SteamID steamID) {
      return getLargeFriendAvatar(this.pointer, steamID.handle);
   }

   public boolean requestUserInformation(SteamID steamID, boolean requireNameOnly) {
      return requestUserInformation(this.pointer, steamID.handle, requireNameOnly);
   }

   public void activateGameOverlay(SteamFriends.OverlayDialog dialog) {
      activateGameOverlay(this.pointer, dialog.id);
   }

   public void activateGameOverlayToUser(SteamFriends.OverlayToUserDialog dialog, SteamID steamID) {
      activateGameOverlayToUser(this.pointer, dialog.id, steamID.handle);
   }

   public void activateGameOverlayToWebPage(String url) {
      activateGameOverlayToWebPage(this.pointer, url);
   }

   public void activateGameOverlayToStore(int appID, SteamFriends.OverlayToStoreFlag flag) {
      activateGameOverlayToStore(this.pointer, appID, flag.ordinal());
   }

   public void activateGameOverlayInviteDialog(SteamID steamIDLobby) {
      activateGameOverlayInviteDialog(this.pointer, steamIDLobby.handle);
   }

   public boolean setRichPresence(String key, String value) {
      return setRichPresence(this.pointer, key, value != null ? value : "");
   }

   public void clearRichPresence() {
      clearRichPresence(this.pointer);
   }

   public String getFriendRichPresence(SteamID steamIDFriend, String key) {
      return getFriendRichPresence(this.pointer, steamIDFriend.handle, key);
   }

   public int getFriendRichPresenceKeyCount(SteamID steamIDFriend) {
      return getFriendRichPresenceKeyCount(this.pointer, steamIDFriend.handle);
   }

   public String getFriendRichPresenceKeyByIndex(SteamID steamIDFriend, int index) {
      return getFriendRichPresenceKeyByIndex(this.pointer, steamIDFriend.handle, index);
   }

   public void requestFriendRichPresence(SteamID steamIDFriend) {
      requestFriendRichPresence(this.pointer, steamIDFriend.handle);
   }

   public boolean inviteUserToGame(SteamID steamIDFriend, String connectString) {
      return inviteUserToGame(this.pointer, steamIDFriend.handle, connectString);
   }

   private static native long createCallback(SteamFriendsCallbackAdapter var0);

   private static native String getPersonaName(long var0);

   private static native long setPersonaName(long var0, long var2, String var4);

   private static native int getPersonaState(long var0);

   private static native int getFriendCount(long var0, int var2);

   private static native long getFriendByIndex(long var0, int var2, int var3);

   private static native int getFriendRelationship(long var0, long var2);

   private static native int getFriendPersonaState(long var0, long var2);

   private static native String getFriendPersonaName(long var0, long var2);

   private static native boolean getFriendGamePlayed(long var0, long var2, SteamFriends.FriendGameInfo var4);

   private static native void setInGameVoiceSpeaking(long var0, long var2, boolean var4);

   private static native int getSmallFriendAvatar(long var0, long var2);

   private static native int getMediumFriendAvatar(long var0, long var2);

   private static native int getLargeFriendAvatar(long var0, long var2);

   private static native boolean requestUserInformation(long var0, long var2, boolean var4);

   private static native void activateGameOverlay(long var0, String var2);

   private static native void activateGameOverlayToUser(long var0, String var2, long var3);

   private static native void activateGameOverlayToWebPage(long var0, String var2);

   private static native void activateGameOverlayToStore(long var0, int var2, int var3);

   private static native void activateGameOverlayInviteDialog(long var0, long var2);

   private static native boolean setRichPresence(long var0, String var2, String var3);

   private static native void clearRichPresence(long var0);

   private static native String getFriendRichPresence(long var0, long var2, String var4);

   private static native int getFriendRichPresenceKeyCount(long var0, long var2);

   private static native String getFriendRichPresenceKeyByIndex(long var0, long var2, int var4);

   private static native void requestFriendRichPresence(long var0, long var2);

   private static native boolean inviteUserToGame(long var0, long var2, String var4);

   public static enum FriendFlags {
      None(0),
      Blocked(1),
      FriendshipRequested(2),
      Immediate(4),
      ClanMember(8),
      OnGameServer(16),
      RequestingFriendship(128),
      RequestingInfo(256),
      Ignored(512),
      IgnoredFriend(1024),
      ChatMember(4096),
      All(65535);

      private final int bits;

      private FriendFlags(int bits) {
         this.bits = bits;
      }

      static int asBits(Collection<SteamFriends.FriendFlags> friendFlags) {
         int bits = 0;

         for (SteamFriends.FriendFlags flags : friendFlags) {
            bits |= flags.bits;
         }

         return bits;
      }
   }

   public static class FriendGameInfo {
      private long gameID;
      private int gameIP;
      private short gamePort;
      private short queryPort;
      private long steamIDLobby;

      public long getGameID() {
         return this.gameID;
      }

      public int getGameIP() {
         return this.gameIP;
      }

      public short getGamePort() {
         return this.gamePort;
      }

      public short getQueryPort() {
         return this.queryPort;
      }

      public SteamID getSteamIDLobby() {
         return new SteamID(this.steamIDLobby);
      }
   }

   public static enum FriendRelationship {
      None,
      Blocked,
      Recipient,
      Friend,
      RequestInitiator,
      Ignored,
      IgnoredFriend,
      Suggested_DEPRECATED,
      Max;

      private static final SteamFriends.FriendRelationship[] values = values();

      static SteamFriends.FriendRelationship byOrdinal(int friendRelationship) {
         return values[friendRelationship];
      }
   }

   public static enum OverlayDialog {
      Friends("Friends"),
      Community("Community"),
      Players("Players"),
      Settings("Settings"),
      OfficialGameGroup("OfficialGameGroup"),
      Stats("Stats"),
      Achievements("Achievements");

      private final String id;

      private OverlayDialog(String id) {
         this.id = id;
      }
   }

   public static enum OverlayToStoreFlag {
      None,
      AddToCart,
      AddToCartAndShow;
   }

   public static enum OverlayToUserDialog {
      SteamID("steamid"),
      Chat("chat"),
      JoinTrade("jointrade"),
      Stats("stats"),
      Achievements("achievements"),
      FriendAdd("friendadd"),
      FriendRemove("friendremove"),
      FriendRequestAccept("friendrequestaccept"),
      FriendRequestIgnore("friendrequestignore");

      private final String id;

      private OverlayToUserDialog(String id) {
         this.id = id;
      }
   }

   public static enum PersonaChange {
      Name(1),
      Status(2),
      ComeOnline(4),
      GoneOffline(8),
      GamePlayed(16),
      GameServer(32),
      Avatar(64),
      JoinedSource(128),
      LeftSource(256),
      RelationshipChanged(512),
      NameFirstSet(1024),
      FacebookInfo(2048),
      Nickname(4096),
      SteamLevel(8192);

      private final int bits;

      private PersonaChange(int bits) {
         this.bits = bits;
      }

      static boolean isSet(SteamFriends.PersonaChange value, int bitMask) {
         return (value.bits & bitMask) == value.bits;
      }
   }

   public static enum PersonaState {
      Offline,
      Online,
      Busy,
      Away,
      Snooze,
      LookingToTrade,
      LookingToPlay;

      private static final SteamFriends.PersonaState[] values = values();

      static SteamFriends.PersonaState byOrdinal(int personaState) {
         return values[personaState];
      }
   }
}
