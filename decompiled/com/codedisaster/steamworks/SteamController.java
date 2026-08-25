package com.codedisaster.steamworks;

public class SteamController extends SteamInterface {
   public static final int STEAM_CONTROLLER_MAX_COUNT = 16;
   public static final int STEAM_CONTROLLER_MAX_ANALOG_ACTIONS = 16;
   public static final int STEAM_CONTROLLER_MAX_DIGITAL_ACTIONS = 128;
   public static final int STEAM_CONTROLLER_MAX_ORIGINS = 8;
   public static final long STEAM_CONTROLLER_HANDLE_ALL_CONTROLLERS = -1L;
   public static final float STEAM_CONTROLLER_MIN_ANALOG_ACTION_DATA = -1.0F;
   public static final float STEAM_CONTROLLER_MAX_ANALOG_ACTION_DATA = 1.0F;
   private long[] controllerHandles = new long[16];
   private int[] actionOrigins = new int[8];

   public SteamController() {
      super(SteamAPI.getSteamControllerPointer());
   }

   public boolean init() {
      return init(this.pointer);
   }

   public boolean shutdown() {
      return shutdown(this.pointer);
   }

   public void runFrame() {
      runFrame(this.pointer);
   }

   public int getConnectedControllers(SteamControllerHandle[] handlesOut) {
      if (handlesOut.length < 16) {
         throw new IllegalArgumentException("Array size must be at least STEAM_CONTROLLER_MAX_COUNT");
      } else {
         int count = getConnectedControllers(this.pointer, this.controllerHandles);

         for (int i = 0; i < count; i++) {
            handlesOut[i] = new SteamControllerHandle(this.controllerHandles[i]);
         }

         return count;
      }
   }

   public boolean showBindingPanel(SteamControllerHandle controller) {
      return showBindingPanel(this.pointer, controller.handle);
   }

   public SteamControllerActionSetHandle getActionSetHandle(String actionSetName) {
      return new SteamControllerActionSetHandle(getActionSetHandle(this.pointer, actionSetName));
   }

   public void activateActionSet(SteamControllerHandle controller, SteamControllerActionSetHandle actionSet) {
      activateActionSet(this.pointer, controller.handle, actionSet.handle);
   }

   public SteamControllerActionSetHandle getCurrentActionSet(SteamControllerHandle controller) {
      return new SteamControllerActionSetHandle(getCurrentActionSet(this.pointer, controller.handle));
   }

   public SteamControllerDigitalActionHandle getDigitalActionHandle(String actionName) {
      return new SteamControllerDigitalActionHandle(getDigitalActionHandle(this.pointer, actionName));
   }

   public void getDigitalActionData(
      SteamControllerHandle controller, SteamControllerDigitalActionHandle digitalAction, SteamControllerDigitalActionData digitalActionData
   ) {
      getDigitalActionData(this.pointer, controller.handle, digitalAction.handle, digitalActionData);
   }

   public int getDigitalActionOrigins(
      SteamControllerHandle controller,
      SteamControllerActionSetHandle actionSet,
      SteamControllerDigitalActionHandle digitalAction,
      SteamController.ActionOrigin[] originsOut
   ) {
      if (originsOut.length < 8) {
         throw new IllegalArgumentException("Array size must be at least STEAM_CONTROLLER_MAX_ORIGINS");
      } else {
         int count = getDigitalActionOrigins(this.pointer, controller.handle, actionSet.handle, digitalAction.handle, this.actionOrigins);

         for (int i = 0; i < count; i++) {
            originsOut[i] = SteamController.ActionOrigin.byOrdinal(this.actionOrigins[i]);
         }

         return count;
      }
   }

   public SteamControllerAnalogActionHandle getAnalogActionHandle(String actionName) {
      return new SteamControllerAnalogActionHandle(getAnalogActionHandle(this.pointer, actionName));
   }

   public void getAnalogActionData(
      SteamControllerHandle controller, SteamControllerAnalogActionHandle analogAction, SteamControllerAnalogActionData analoglActionData
   ) {
      getAnalogActionData(this.pointer, controller.handle, analogAction.handle, analoglActionData);
   }

   public int getAnalogActionOrigins(
      SteamControllerHandle controller,
      SteamControllerActionSetHandle actionSet,
      SteamControllerAnalogActionHandle analogAction,
      SteamController.ActionOrigin[] originsOut
   ) {
      if (originsOut.length < 8) {
         throw new IllegalArgumentException("Array size must be at least STEAM_CONTROLLER_MAX_ORIGINS");
      } else {
         int count = getAnalogActionOrigins(this.pointer, controller.handle, actionSet.handle, analogAction.handle, this.actionOrigins);

         for (int i = 0; i < count; i++) {
            originsOut[i] = SteamController.ActionOrigin.byOrdinal(this.actionOrigins[i]);
         }

         return count;
      }
   }

   public void stopAnalogActionMomentum(SteamControllerHandle controller, SteamControllerAnalogActionHandle analogAction) {
      stopAnalogActionMomentum(this.pointer, controller.handle, analogAction.handle);
   }

   public void triggerHapticPulse(SteamControllerHandle controller, SteamController.Pad targetPad, int durationMicroSec) {
      triggerHapticPulse(this.pointer, controller.handle, targetPad.ordinal(), durationMicroSec);
   }

   public void triggerRepeatedHapticPulse(
      SteamControllerHandle controller, SteamController.Pad targetPad, int durationMicroSec, int offMicroSec, int repeat, int flags
   ) {
      triggerRepeatedHapticPulse(this.pointer, controller.handle, targetPad.ordinal(), durationMicroSec, offMicroSec, repeat, flags);
   }

   public void triggerVibration(SteamControllerHandle controller, short leftSpeed, short rightSpeed) {
      triggerVibration(this.pointer, controller.handle, leftSpeed, rightSpeed);
   }

   public void setLEDColor(SteamControllerHandle controller, int colorR, int colorG, int colorB, SteamController.LEDFlag flags) {
      setLEDColor(this.pointer, controller.handle, (byte)(colorR & 0xFF), (byte)(colorG & 0xFF), (byte)(colorB & 0xFF), flags.ordinal());
   }

   public int getGamepadIndexForController(SteamControllerHandle controller) {
      return getGamepadIndexForController(this.pointer, controller.handle);
   }

   public SteamControllerHandle getControllerForGamepadIndex(int index) {
      return new SteamControllerHandle(getControllerForGamepadIndex(this.pointer, index));
   }

   public void getMotionData(SteamControllerHandle controller, SteamControllerMotionData motionData) {
      getMotionData(this.pointer, controller.handle, motionData.data);
   }

   public boolean showDigitalActionOrigins(
      SteamControllerHandle controller, SteamControllerDigitalActionHandle digitalActionHandle, float scale, float xPosition, float yPosition
   ) {
      return showDigitalActionOrigins(this.pointer, controller.handle, digitalActionHandle.handle, scale, xPosition, yPosition);
   }

   public boolean showAnalogActionOrigins(
      SteamControllerHandle controller, SteamControllerAnalogActionHandle analogActionHandle, float scale, float xPosition, float yPosition
   ) {
      return showAnalogActionOrigins(this.pointer, controller.handle, analogActionHandle.handle, scale, xPosition, yPosition);
   }

   public String getStringForActionOrigin(SteamController.ActionOrigin origin) {
      return getStringForActionOrigin(this.pointer, origin.ordinal());
   }

   public String getGlyphForActionOrigin(SteamController.ActionOrigin origin) {
      return getGlyphForActionOrigin(this.pointer, origin.ordinal());
   }

   public SteamController.InputType getInputTypeForHandle(SteamControllerHandle controller) {
      return SteamController.InputType.byOrdinal(getInputTypeForHandle(this.pointer, controller.handle));
   }

   private static native boolean init(long var0);

   private static native boolean shutdown(long var0);

   private static native void runFrame(long var0);

   private static native int getConnectedControllers(long var0, long[] var2);

   private static native boolean showBindingPanel(long var0, long var2);

   private static native long getActionSetHandle(long var0, String var2);

   private static native void activateActionSet(long var0, long var2, long var4);

   private static native long getCurrentActionSet(long var0, long var2);

   private static native long getDigitalActionHandle(long var0, String var2);

   private static native void getDigitalActionData(long var0, long var2, long var4, SteamControllerDigitalActionData var6);

   private static native int getDigitalActionOrigins(long var0, long var2, long var4, long var6, int[] var8);

   private static native long getAnalogActionHandle(long var0, String var2);

   private static native void getAnalogActionData(long var0, long var2, long var4, SteamControllerAnalogActionData var6);

   private static native int getAnalogActionOrigins(long var0, long var2, long var4, long var6, int[] var8);

   private static native void stopAnalogActionMomentum(long var0, long var2, long var4);

   private static native void triggerHapticPulse(long var0, long var2, int var4, int var5);

   private static native void triggerRepeatedHapticPulse(long var0, long var2, int var4, int var5, int var6, int var7, int var8);

   private static native void triggerVibration(long var0, long var2, short var4, short var5);

   private static native void setLEDColor(long var0, long var2, byte var4, byte var5, byte var6, int var7);

   private static native int getGamepadIndexForController(long var0, long var2);

   private static native long getControllerForGamepadIndex(long var0, int var2);

   private static native void getMotionData(long var0, long var2, float[] var4);

   private static native boolean showDigitalActionOrigins(long var0, long var2, long var4, float var6, float var7, float var8);

   private static native boolean showAnalogActionOrigins(long var0, long var2, long var4, float var6, float var7, float var8);

   private static native String getStringForActionOrigin(long var0, int var2);

   private static native String getGlyphForActionOrigin(long var0, int var2);

   private static native int getInputTypeForHandle(long var0, long var2);

   public static enum ActionOrigin {
      None,
      A,
      B,
      X,
      Y,
      LeftBumper,
      RightBumper,
      LeftGrip,
      RightGrip,
      Start,
      Back,
      LeftPad_Touch,
      LeftPad_Swipe,
      LeftPad_Click,
      LeftPad_DPadNorth,
      LeftPad_DPadSouth,
      LeftPad_DPadWest,
      LeftPad_DPadEast,
      RightPad_Touch,
      RightPad_Swipe,
      RightPad_Click,
      RightPad_DPadNorth,
      RightPad_DPadSouth,
      RightPad_DPadWest,
      RightPad_DPadEast,
      LeftTrigger_Pull,
      LeftTrigger_Click,
      RightTrigger_Pull,
      RightTrigger_Click,
      LeftStick_Move,
      LeftStick_Click,
      LeftStick_DPadNorth,
      LeftStick_DPadSouth,
      LeftStick_DPadWest,
      LeftStick_DPadEast,
      Gyro_Move,
      Gyro_Pitch,
      Gyro_Yaw,
      Gyro_Roll,
      PS4_X,
      PS4_Circle,
      PS4_Triangle,
      PS4_Square,
      PS4_LeftBumper,
      PS4_RightBumper,
      PS4_Options,
      PS4_Share,
      PS4_LeftPad_Touch,
      PS4_LeftPad_Swipe,
      PS4_LeftPad_Click,
      PS4_LeftPad_DPadNorth,
      PS4_LeftPad_DPadSouth,
      PS4_LeftPad_DPadWest,
      PS4_LeftPad_DPadEast,
      PS4_RightPad_Touch,
      PS4_RightPad_Swipe,
      PS4_RightPad_Click,
      PS4_RightPad_DPadNorth,
      PS4_RightPad_DPadSouth,
      PS4_RightPad_DPadWest,
      PS4_RightPad_DPadEast,
      PS4_CenterPad_Touch,
      PS4_CenterPad_Swipe,
      PS4_CenterPad_Click,
      PS4_CenterPad_DPadNorth,
      PS4_CenterPad_DPadSouth,
      PS4_CenterPad_DPadWest,
      PS4_CenterPad_DPadEast,
      PS4_LeftTrigger_Pull,
      PS4_LeftTrigger_Click,
      PS4_RightTrigger_Pull,
      PS4_RightTrigger_Click,
      PS4_LeftStick_Move,
      PS4_LeftStick_Click,
      PS4_LeftStick_DPadNorth,
      PS4_LeftStick_DPadSouth,
      PS4_LeftStick_DPadWest,
      PS4_LeftStick_DPadEast,
      PS4_RightStick_Move,
      PS4_RightStick_Click,
      PS4_RightStick_DPadNorth,
      PS4_RightStick_DPadSouth,
      PS4_RightStick_DPadWest,
      PS4_RightStick_DPadEast,
      PS4_DPad_North,
      PS4_DPad_South,
      PS4_DPad_West,
      PS4_DPad_East,
      PS4_Gyro_Move,
      PS4_Gyro_Pitch,
      PS4_Gyro_Yaw,
      PS4_Gyro_Roll,
      XBoxOne_A,
      XBoxOne_B,
      XBoxOne_X,
      XBoxOne_Y,
      XBoxOne_LeftBumper,
      XBoxOne_RightBumper,
      XBoxOne_Menu,
      XBoxOne_View,
      XBoxOne_LeftTrigger_Pull,
      XBoxOne_LeftTrigger_Click,
      XBoxOne_RightTrigger_Pull,
      XBoxOne_RightTrigger_Click,
      XBoxOne_LeftStick_Move,
      XBoxOne_LeftStick_Click,
      XBoxOne_LeftStick_DPadNorth,
      XBoxOne_LeftStick_DPadSouth,
      XBoxOne_LeftStick_DPadWest,
      XBoxOne_LeftStick_DPadEast,
      XBoxOne_RightStick_Move,
      XBoxOne_RightStick_Click,
      XBoxOne_RightStick_DPadNorth,
      XBoxOne_RightStick_DPadSouth,
      XBoxOne_RightStick_DPadWest,
      XBoxOne_RightStick_DPadEast,
      XBoxOne_DPad_North,
      XBoxOne_DPad_South,
      XBoxOne_DPad_West,
      XBoxOne_DPad_East,
      XBox360_A,
      XBox360_B,
      XBox360_X,
      XBox360_Y,
      XBox360_LeftBumper,
      XBox360_RightBumper,
      XBox360_Start,
      XBox360_Back,
      XBox360_LeftTrigger_Pull,
      XBox360_LeftTrigger_Click,
      XBox360_RightTrigger_Pull,
      XBox360_RightTrigger_Click,
      XBox360_LeftStick_Move,
      XBox360_LeftStick_Click,
      XBox360_LeftStick_DPadNorth,
      XBox360_LeftStick_DPadSouth,
      XBox360_LeftStick_DPadWest,
      XBox360_LeftStick_DPadEast,
      XBox360_RightStick_Move,
      XBox360_RightStick_Click,
      XBox360_RightStick_DPadNorth,
      XBox360_RightStick_DPadSouth,
      XBox360_RightStick_DPadWest,
      XBox360_RightStick_DPadEast,
      XBox360_DPad_North,
      XBox360_DPad_South,
      XBox360_DPad_West,
      XBox360_DPad_East,
      SteamV2_A,
      SteamV2_B,
      SteamV2_X,
      SteamV2_Y,
      SteamV2_LeftBumper,
      SteamV2_RightBumper,
      SteamV2_LeftGrip,
      SteamV2_RightGrip,
      SteamV2_LeftGrip_Upper,
      SteamV2_RightGrip_Upper,
      SteamV2_LeftBumper_Pressure,
      SteamV2_RightBumper_Pressure,
      SteamV2_LeftGrip_Pressure,
      SteamV2_RightGrip_Pressure,
      SteamV2_LeftGrip_Upper_Pressure,
      SteamV2_RightGrip_Upper_Pressure,
      SteamV2_Start,
      SteamV2_Back,
      SteamV2_LeftPad_Touch,
      SteamV2_LeftPad_Swipe,
      SteamV2_LeftPad_Click,
      SteamV2_LeftPad_Pressure,
      SteamV2_LeftPad_DPadNorth,
      SteamV2_LeftPad_DPadSouth,
      SteamV2_LeftPad_DPadWest,
      SteamV2_LeftPad_DPadEast,
      SteamV2_RightPad_Touch,
      SteamV2_RightPad_Swipe,
      SteamV2_RightPad_Click,
      SteamV2_RightPad_Pressure,
      SteamV2_RightPad_DPadNorth,
      SteamV2_RightPad_DPadSouth,
      SteamV2_RightPad_DPadWest,
      SteamV2_RightPad_DPadEast,
      SteamV2_LeftTrigger_Pull,
      SteamV2_LeftTrigger_Click,
      SteamV2_RightTrigger_Pull,
      SteamV2_RightTrigger_Click,
      SteamV2_LeftStick_Move,
      SteamV2_LeftStick_Click,
      SteamV2_LeftStick_DPadNorth,
      SteamV2_LeftStick_DPadSouth,
      SteamV2_LeftStick_DPadWest,
      SteamV2_LeftStick_DPadEast,
      SteamV2_Gyro_Move,
      SteamV2_Gyro_Pitch,
      SteamV2_Gyro_Yaw,
      SteamV2_Gyro_Roll;

      private static final SteamController.ActionOrigin[] values = values();

      static SteamController.ActionOrigin byOrdinal(int ordinal) {
         return values[ordinal];
      }
   }

   public static enum InputType {
      Unknown,
      SteamController,
      XBox360Controller,
      XBoxOneController,
      GenericXInput,
      PS4Controller;

      private static final SteamController.InputType[] values = values();

      static SteamController.InputType byOrdinal(int ordinal) {
         return values[ordinal];
      }
   }

   public static enum LEDFlag {
      SetColor,
      RestoreUserDefault;
   }

   public static enum Pad {
      Left,
      Right;
   }

   public static enum Source {
      None,
      LeftTrackpad,
      RightTrackpad,
      Joystick,
      ABXY,
      Switch,
      LeftTrigger,
      RightTrigger,
      Gyro,
      CenterTrackpad,
      RightJoystick,
      DPad,
      Key,
      Mouse;
   }

   public static enum SourceMode {
      None,
      Dpad,
      Buttons,
      FourButtons,
      AbsoluteMouse,
      RelativeMouse,
      JoystickMove,
      JoystickMouse,
      JoystickCamera,
      ScrollWheel,
      Trigger,
      TouchMenu,
      MouseJoystick,
      MouseRegion,
      RadialMenu,
      SingleButton,
      Switches;

      private static final SteamController.SourceMode[] values = values();

      static SteamController.SourceMode byOrdinal(int ordinal) {
         return values[ordinal];
      }
   }
}
