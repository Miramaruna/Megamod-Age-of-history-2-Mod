package com.codedisaster.steamworks;

import java.nio.ByteBuffer;

public class SteamNetworking extends SteamInterface {
   private final int[] tmpIntResult = new int[1];
   private final long[] tmpLongResult = new long[1];

   public SteamNetworking(SteamNetworkingCallback callback) {
      this(SteamAPI.getSteamNetworkingPointer(), createCallback(new SteamNetworkingCallbackAdapter(callback)));
   }

   SteamNetworking(long pointer, long callback) {
      super(pointer, callback);
   }

   public boolean sendP2PPacket(SteamID steamIDRemote, ByteBuffer data, SteamNetworking.P2PSend sendType, int channel) throws SteamException {
      if (!data.isDirect()) {
         throw new SteamException("Direct buffer required!");
      } else {
         return sendP2PPacket(this.pointer, steamIDRemote.handle, data, data.position(), data.remaining(), sendType.ordinal(), channel);
      }
   }

   public int isP2PPacketAvailable(int channel) {
      return isP2PPacketAvailable(this.pointer, this.tmpIntResult, channel) ? this.tmpIntResult[0] : 0;
   }

   public int readP2PPacket(SteamID steamIDRemote, ByteBuffer dest, int channel) throws SteamException {
      if (!dest.isDirect()) {
         throw new SteamException("Direct buffer required!");
      } else if (readP2PPacket(this.pointer, dest, dest.position(), dest.remaining(), this.tmpIntResult, this.tmpLongResult, channel)) {
         steamIDRemote.handle = this.tmpLongResult[0];
         return this.tmpIntResult[0];
      } else {
         return 0;
      }
   }

   public boolean acceptP2PSessionWithUser(SteamID steamIDRemote) {
      return acceptP2PSessionWithUser(this.pointer, steamIDRemote.handle);
   }

   public boolean closeP2PSessionWithUser(SteamID steamIDRemote) {
      return closeP2PSessionWithUser(this.pointer, steamIDRemote.handle);
   }

   public boolean closeP2PChannelWithUser(SteamID steamIDRemote, int channel) {
      return closeP2PChannelWithUser(this.pointer, steamIDRemote.handle, channel);
   }

   public boolean getP2PSessionState(SteamID steamIDRemote, SteamNetworking.P2PSessionState connectionState) {
      return getP2PSessionState(this.pointer, steamIDRemote.handle, connectionState);
   }

   public boolean allowP2PPacketRelay(boolean allow) {
      return allowP2PPacketRelay(this.pointer, allow);
   }

   private static native long createCallback(SteamNetworkingCallbackAdapter var0);

   private static native boolean sendP2PPacket(long var0, long var2, ByteBuffer var4, int var5, int var6, int var7, int var8);

   private static native boolean isP2PPacketAvailable(long var0, int[] var2, int var3);

   private static native boolean readP2PPacket(long var0, ByteBuffer var2, int var3, int var4, int[] var5, long[] var6, int var7);

   private static native boolean acceptP2PSessionWithUser(long var0, long var2);

   private static native boolean closeP2PSessionWithUser(long var0, long var2);

   private static native boolean closeP2PChannelWithUser(long var0, long var2, int var4);

   private static native boolean getP2PSessionState(long var0, long var2, SteamNetworking.P2PSessionState var4);

   private static native boolean allowP2PPacketRelay(long var0, boolean var2);

   public static enum P2PSend {
      Unreliable,
      UnreliableNoDelay,
      Reliable,
      ReliableWithBuffering;
   }

   public static enum P2PSessionError {
      None,
      NotRunningApp,
      NoRightsToApp,
      DestinationNotLoggedIn,
      Timeout;

      private static final SteamNetworking.P2PSessionError[] values = values();

      public static SteamNetworking.P2PSessionError byOrdinal(int sessionError) {
         return values[sessionError];
      }
   }

   public static class P2PSessionState {
      byte connectionActive;
      byte connecting;
      byte sessionError;
      byte usingRelay;
      int bytesQueuedForSend;
      int packetsQueuedForSend;
      int remoteIP;
      short remotePort;

      public boolean isConnectionActive() {
         return this.connectionActive != 0;
      }

      public boolean isConnecting() {
         return this.connecting != 0;
      }

      public SteamNetworking.P2PSessionError getLastSessionError() {
         return SteamNetworking.P2PSessionError.byOrdinal(this.sessionError);
      }

      public boolean isUsingRelay() {
         return this.usingRelay != 0;
      }

      public int getBytesQueuedForSend() {
         return this.bytesQueuedForSend;
      }

      public int getPacketsQueuedForSend() {
         return this.packetsQueuedForSend;
      }

      public int getRemoteIP() {
         return this.remoteIP;
      }

      public short getRemotePort() {
         return this.remotePort;
      }
   }
}
