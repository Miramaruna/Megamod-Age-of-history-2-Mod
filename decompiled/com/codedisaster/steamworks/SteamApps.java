package com.codedisaster.steamworks;

public class SteamApps extends SteamInterface {
   public SteamApps() {
      super(SteamAPI.getSteamAppsPointer());
   }

   public boolean isSubscribed() {
      return isSubscribed(this.pointer);
   }

   public boolean isLowViolence() {
      return isLowViolence(this.pointer);
   }

   public boolean isCybercafe() {
      return isCybercafe(this.pointer);
   }

   public boolean isVACBanned() {
      return isVACBanned(this.pointer);
   }

   public String getCurrentGameLanguage() {
      return getCurrentGameLanguage(this.pointer);
   }

   public String getAvailableGameLanguages() {
      return getAvailableGameLanguages(this.pointer);
   }

   public boolean isSubscribedApp(int appID) {
      return isSubscribedApp(this.pointer, appID);
   }

   public boolean isDlcInstalled(int appID) {
      return isDlcInstalled(this.pointer, appID);
   }

   public int getEarliestPurchaseUnixTime(int appID) {
      return getEarliestPurchaseUnixTime(this.pointer, appID);
   }

   public boolean isSubscribedFromFreeWeekend() {
      return isSubscribedFromFreeWeekend(this.pointer);
   }

   public int getDLCCount() {
      return getDLCCount(this.pointer);
   }

   public void installDLC(int appID) {
      installDLC(this.pointer, appID);
   }

   public void uninstallDLC(int appID) {
      uninstallDLC(this.pointer, appID);
   }

   public SteamID getAppOwner() {
      return new SteamID(getAppOwner(this.pointer));
   }

   public int getAppBuildId() {
      return getAppBuildId(this.pointer);
   }

   private static native boolean isSubscribed(long var0);

   private static native boolean isLowViolence(long var0);

   private static native boolean isCybercafe(long var0);

   private static native boolean isVACBanned(long var0);

   private static native String getCurrentGameLanguage(long var0);

   private static native String getAvailableGameLanguages(long var0);

   private static native boolean isSubscribedApp(long var0, int var2);

   private static native boolean isDlcInstalled(long var0, int var2);

   private static native int getEarliestPurchaseUnixTime(long var0, int var2);

   private static native boolean isSubscribedFromFreeWeekend(long var0);

   private static native int getDLCCount(long var0);

   private static native void installDLC(long var0, int var2);

   private static native void uninstallDLC(long var0, int var2);

   private static native long getAppOwner(long var0);

   private static native int getAppBuildId(long var0);
}
