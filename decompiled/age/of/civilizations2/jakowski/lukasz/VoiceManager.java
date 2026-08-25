package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import java.util.ArrayList;
import java.util.HashMap;

public class VoiceManager {
   private static HashMap<String, Sound> tCache = new HashMap<>();
   private static long tLastPlay = 0L;

   private static final int[] SELECT = new int[]{
      1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20,
      21, 22, 23, 24, 25, 26, 27, 28
   };
   private static final int[] ATTACK = new int[]{34, 35, 36, 37, 38, 39, 44, 48, 49, 50};
   private static final int[] MOVE = new int[]{
      41, 42, 43, 58, 59, 60, 61, 62, 63, 64, 65, 67, 68, 69, 70, 71
   };
   private static final int[] CANCEL = new int[]{75, 77};

   public static final void playSelect() {
      Gdx.app.log("AoC", "VO: playSelect called");
      playRandom(SELECT);
   }

   public static final void playAttack() {
      Gdx.app.log("AoC", "VO: playAttack called");
      playRandom(ATTACK);
   }

   public static final void playMove() {
      Gdx.app.log("AoC", "VO: playMove called");
      playRandom(MOVE);
   }

   public static final void playCancel() {
      playRandom(CANCEL);
   }

   private static void playRandom(int[] nums) {
      long tNow = System.currentTimeMillis();
      if (tNow - tLastPlay < 400L && tLastPlay > 0) {
         return;
      }

      tLastPlay = tNow;

      try {
         int tNum = nums[CFG.oR.nextInt(nums.length)];
         String tKey = "vo_" + tNum;
         Sound tSnd = tCache.get(tKey);

         if (tSnd == null) {
            String tName = "sounds/vo/SovietExtracted_" + String.format("%03d", tNum) + ".mp3";
            com.badlogic.gdx.files.FileHandle tFH = Gdx.files.internal(tName);
            Gdx.app.log("AoC", "VO: loading " + tName + " exists=" + tFH.exists());
            tSnd = Gdx.audio.newSound(tFH);
            tCache.put(tKey, tSnd);
            Gdx.app.log("AoC", "VO: loaded OK");
         }

         float tVol = CFG.soundsManager.getSoundsVolume() * CFG.soundsManager.getMasterVolume();
         Gdx.app.log("AoC", "VO PLAY: num=" + tNum + " vol=" + tVol);
         tSnd.play(tVol);
      } catch (Exception var5) {
         Gdx.app.log("AoC", "VO ERROR: " + var5);
         var5.printStackTrace();
      }
   }
}
