package age.of.civilizations2.jakowski.lukasz;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;

public class ConvertEvents {
   public static void main(String[] args) throws Exception {
      int fixed = 0;
      ArrayList<String> files = new ArrayList<>();

      for (String root : args) {
         Files.walkFileTree(
            Paths.get(root),
            new java.nio.file.SimpleFileVisitor<java.nio.file.Path>() {
               @Override
               public java.nio.file.FileVisitResult visitFile(java.nio.file.Path p, java.nio.file.attribute.BasicFileAttributes attrs) {
                  if (p.toString().endsWith("_E")) {
                     files.add(p.toString());
                  }

                  return java.nio.file.FileVisitResult.CONTINUE;
               }
            }
         );
      }

      System.out.println("FOUND _E FILES: " + files.size());

      for (String path : files) {
         byte[] raw;

         try {
            raw = Files.readAllBytes(Paths.get(path));
         } catch (Exception varErr) {
            System.out.println("SKIP (unreadable): " + path);
            continue;
         }
         Events_GameData egd;

         boolean tOk = true;

         try (ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(raw)) {
            @Override
            protected ObjectStreamClass readClassDescriptor() throws IOException, ClassNotFoundException {
               ObjectStreamClass read = super.readClassDescriptor();

               try {
                  Class<?> local = Class.forName(read.getName());
                  ObjectStreamClass localDesc = ObjectStreamClass.lookup(local);
                  if (localDesc != null && localDesc.getSerialVersionUID() != read.getSerialVersionUID()) {
                     return localDesc;
                  }
               } catch (ClassNotFoundException ignored) {
               }

               return read;
            }
         }) {
            egd = (Events_GameData)in.readUnshared();
         } catch (Exception varRead) {
            System.out.println("SKIP (incompatible): " + path + " :: " + varRead);
            continue;
         }

         egd.iEventsSize = egd.lEvents.size();

         try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(path))) {
            out.writeObject(egd);
         }

         System.out.println("CONVERTED: " + path + " -> " + egd.iEventsSize + " events");
         fixed++;
      }

      System.out.println("TOTAL FIXED: " + fixed);
   }
}
