package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class VoiceManager {
   private static HashMap<String, Sound> tCache = new HashMap<>();
   private static long tLastPlay = 0L;
   private static HashMap<String, ArrayList<String>> tFiles = new HashMap<>();
   private static String tScannedLang = null;
   public static String LANGUAGE = "english";
   public static HashSet<String> AVAILABLE = new HashSet<>();
   public static final String[] LANG_ORDER = {
      "english", "russian", "german", "french", "spanish", "italian",
      "polish", "dutch", "japanese", "chinese", "danish", "norwegian", "swedish"
   };

   private static void scan() {
      if (tScannedLang != null && tScannedLang.equals(LANGUAGE)) return;
      if (AVAILABLE.isEmpty()) {
         FileHandle root = Gdx.files.local("sounds/vo");
         if (root.exists()) {
            for (FileHandle d : root.list()) {
               if (d.isDirectory()) AVAILABLE.add(d.name());
            }
         }
         if (!AVAILABLE.contains("english")) AVAILABLE.add("english");
         if (!AVAILABLE.contains(LANGUAGE)) LANGUAGE = AVAILABLE.iterator().next();
      }

      tSelectReset();
      FileHandle dir = Gdx.files.local("sounds/vo/" + LANGUAGE);

      if (!dir.exists()) return;

      for (FileHandle f : dir.list()) {
         String n = f.name();

         if (!n.endsWith(".ogg")) continue;

         if (n.startsWith("select_")) {
            getList("select").add(n);
         } else if (n.startsWith("attack_")) {
            getList("attack").add(n);
         } else if (n.startsWith("move_")) {
            getList("move").add(n);
         } else if (n.startsWith("cancel_")) {
            getList("cancel").add(n);
         } else if (n.startsWith("retreat_")) {
            getList("retreat").add(n);
         }
      }

      tScannedLang = LANGUAGE;
      Gdx.app.log("AoC", "VO SCAN [" + LANGUAGE + "]: select=" + size("select")
         + " attack=" + size("attack")
         + " move=" + size("move")
         + " cancel=" + size("cancel")
         + " retreat=" + size("retreat"));
   }

   private static void tSelectReset() {
      tFiles.clear();
      tCache.clear();
   }

   private static ArrayList<String> getList(String cat) {
      ArrayList<String> l = tFiles.get(cat);

      if (l == null) {
         l = new ArrayList<>();
         tFiles.put(cat, l);
      }

      return l;
   }

   private static int size(String cat) {
      ArrayList<String> l = tFiles.get(cat);
      return l == null ? 0 : l.size();
   }

   public static void playSelect() { scan(); playRandom("select"); }
   public static void playAttack() { scan(); playRandom("attack"); }
   public static void playMove() { scan(); playRandom("move"); }
   public static void playCancel() { scan(); playRandom("cancel"); }
   public static void playRetreat() { scan(); playRandom("retreat"); }

   public static void nextLanguage() {
      if (AVAILABLE.isEmpty()) {
         FileHandle root = Gdx.files.local("sounds/vo");

         if (root.exists()) {
            for (FileHandle d : root.list()) {
               if (d.isDirectory()) AVAILABLE.add(d.name());
            }
         }
      }

      if (!AVAILABLE.contains(LANGUAGE)) LANGUAGE = "english";

      ArrayList<String> langs = new ArrayList<>(AVAILABLE);
      java.util.Collections.sort(langs);

      if (langs.isEmpty()) return;

      int i = langs.indexOf(LANGUAGE);
      LANGUAGE = langs.get(i < 0 ? 0 : (i + 1) % langs.size());
      tScannedLang = null;
      scan();
   }

   private static void playRandom(String cat) {
      ArrayList<String> files = tFiles.get(cat);

      if (files == null || files.isEmpty()) return;

      long tNow = System.currentTimeMillis();

      if (tNow - tLastPlay < 400L && tLastPlay > 0) return;

      tLastPlay = tNow;

      try {
         String tFile = files.get(CFG.oR.nextInt(files.size()));
         Sound tSnd = tCache.get(tFile);

         if (tSnd == null) {
            tSnd = Gdx.audio.newSound(Gdx.files.local("sounds/vo/" + LANGUAGE + "/" + tFile));
            tCache.put(tFile, tSnd);
         }

         float tVol = CFG.soundsManager.getSoundsVolume() * CFG.soundsManager.getMasterVolume();
         tSnd.play(tVol);
      } catch (Exception var5) {
         Gdx.app.log("AoC", "VO ERROR: " + var5.getMessage());
      }
   }
}
