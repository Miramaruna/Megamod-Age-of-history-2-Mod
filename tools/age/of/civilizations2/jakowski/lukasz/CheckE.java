package age.of.civilizations2.jakowski.lukasz;

import java.io.*;

public class CheckE {
   public static void main(String[] a) throws Exception {
      byte[] b = java.nio.file.Files.readAllBytes(java.nio.file.Paths.get(a[0]));

      try (ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(b))) {
         Events_GameData egd = (Events_GameData)in.readUnshared();
         System.out.println("readUnshared OK, events=" + egd.iEventsSize + ", list=" + egd.lEvents.size());
         for (Event_GameData e : egd.lEvents) {
            System.out.println(" - " + e.sEventName
               + " | textLen=" + e.event_PopUp.sText.length()
               + " | text=" + (e.event_PopUp.sText.length() > 40 ? e.event_PopUp.sText.substring(0, 40) : e.event_PopUp.sText));
         }
      }
   }
}
