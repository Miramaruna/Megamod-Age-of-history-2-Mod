package age.of.civilizations2.jakowski.lukasz;

import java.io.*;

public class FixE {
   public static void main(String[] a) throws Exception {
      byte[] b = java.nio.file.Files.readAllBytes(java.nio.file.Paths.get(a[0]));
      Events_GameData egd;

      try (ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(b))) {
         egd = (Events_GameData)in.readUnshared();
      }

      egd.iEventsSize = egd.lEvents.size();

      try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(a[0]))) {
         out.writeObject(egd);
      }

      System.out.println("iEventsSize fixed -> " + egd.iEventsSize);
   }
}
