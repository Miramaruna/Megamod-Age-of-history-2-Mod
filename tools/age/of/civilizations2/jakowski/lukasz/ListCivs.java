package age.of.civilizations2.jakowski.lukasz;

import java.io.*;
import java.util.*;

public class ListCivs {
   public static void main(String[] args) throws Exception {
      try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(args[0]))) {
         Scenario_GameData s = (Scenario_GameData)in.readUnshared();
         TreeSet<String> missing = new TreeSet<>();
         StringBuilder sb = new StringBuilder();
         for (String tag : s.lCivsTags) {
            sb.append(tag).append(" ");
            File f = new File(args[1], tag);
            if (!f.exists()) missing.add(tag);
         }
         System.out.println("TAGS(" + s.lCivsTags.size() + "): " + sb);
         System.out.println("MISSING_IN_GAME_DIR(" + missing.size() + "): " + String.join(" ", missing));
      }
   }
}
