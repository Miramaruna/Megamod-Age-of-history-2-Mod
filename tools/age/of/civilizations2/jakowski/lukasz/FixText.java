package age.of.civilizations2.jakowski.lukasz;

import java.io.*;
import java.nio.file.*;

public class FixText {
   public static void main(String[] a) throws Exception {
      byte[] b = Files.readAllBytes(Paths.get(a[0]));
      Events_GameData egd;

      try (ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(b))) {
         egd = (Events_GameData)in.readUnshared();
      }

      int tFixed = 0;

      for (Event_GameData e : egd.lEvents) {
         if (e.event_PopUp.sText.contains("\n")) {
            e.event_PopUp.sText = e.event_PopUp.sText.replace("\n", " ");
            tFixed++;
         }
      }

      try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(a[0]))) {
         out.writeObject(egd);
      }

      System.out.println("FIXED NEWLINES IN: " + tFixed);
   }
}
