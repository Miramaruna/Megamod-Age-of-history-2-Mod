package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

public class Plusic {
   public static ArrayList<PlusicMod> mods = new ArrayList<>();
   public static ArrayList<Building> customBuildings = new ArrayList<>();
   private static PlusicModLoader modLoader = new PlusicModLoader();
   private static ArrayList<PlusicEventListener> listeners = new ArrayList<>();

   public static void created() {
      for (int i = 0; i < mods.size(); i++) {
         mods.get(i).create();
      }
   }

   public static void dispose() {
      for (int i = 0; i < mods.size(); i++) {
         mods.get(i).dispose();
      }
   }

   public static void render(SpriteBatch mod) {
      for (int i = 0; i < mods.size(); i++) {
         mod.setColor(Color.WHITE);
         mods.get(i).render(mod);
      }
   }

   public static void registerBuilding(CustomBuilding building) {
      CustomBuildingsManager.addBuilding(building);
   }

   public static void registerListener(PlusicEventListener listener) {
      listeners.add(listener);
   }

   public static void initPlusic() {
      File dir = new File("mods");
      if (!dir.exists()) {
         dir.mkdirs();
      }

      List<File> lst = new ArrayList<>();

      for (File file : dir.listFiles()) {
         if (file.isFile() && file.getName().endsWith(".jar")) {
            lst.add(file);
         }
      }

      for (int i = 0; i < lst.size(); i++) {
         new File("mods/" + lst.get(i).getName());
         String mainClass = "";

         try {
            URL[] urls = new URL[]{new URL("jar:file:" + lst.get(i) + "!/")};
            URLClassLoader cl = URLClassLoader.newInstance(urls);
            BufferedReader bfr = new BufferedReader(new InputStreamReader(cl.getResourceAsStream("modinfo.plus")));
            String line = "";
            String name = "Unknown";
            String author = "Unknown";
            String version = "1.0";

            while ((line = bfr.readLine()) != null) {
               if (line.split(": ")[0].equals("MainClass")) {
                  mainClass = line.split(": ")[1];
               }

               if (line.split(": ")[0].equals("Name")) {
                  name = line.split(": ")[1];
               }

               if (line.split(": ")[0].equals("Author")) {
                  author = line.split(": ")[1];
               }

               if (line.split(": ")[0].equals("Version")) {
                  version = line.split(": ")[1];
               }
            }

            PlusicMod mod = (PlusicMod)cl.loadClass(mainClass).newInstance();
            mod.setName(name);
            mod.setAuthor(author);
            mod.setVersion(version);
            mods.add(mod);
            System.out.println("loaded!");
         } catch (IOException var13) {
            throw new RuntimeException(var13);
         } catch (ClassNotFoundException var14) {
            throw new RuntimeException(var14);
         } catch (InstantiationException var15) {
            throw new RuntimeException(var15);
         } catch (IllegalAccessException var16) {
            throw new RuntimeException(var16);
         }
      }

      mods.add(modLoader);
   }

   public static void menuChanged(Menu eMenu) {
      for (int i = 0; i < listeners.size(); i++) {
         listeners.get(i).changedMenu(eMenu);
      }
   }

   public static boolean touchUp(int screenX, int screenY, int pointer, int button) {
      boolean handled = false;

      for (int i = 0; i < listeners.size(); i++) {
         if (listeners.get(i).touchUp(screenX, screenY, pointer, button)) {
            handled = true;
         }
      }

      return handled;
   }

   public static boolean touchDown(int screenX, int screenY, int pointer, int button) {
      boolean handled = false;

      for (int i = 0; i < listeners.size(); i++) {
         if (listeners.get(i).touchDown(screenX, screenY, pointer, button)) {
            handled = true;
         }
      }

      return handled;
   }

   public static ArrayList<CustomBuilding> regBuildings(ArrayList<CustomBuilding> buildings) {
      for (int i = 0; i < listeners.size(); i++) {
         listeners.get(i).registerBuildings(buildings);
      }

      return buildings;
   }

   public static boolean touchDragged(int screenX, int screenY, int pointer) {
      boolean handled = false;

      for (int i = 0; i < listeners.size(); i++) {
         if (listeners.get(i).touchDragged(screenX, screenY, pointer)) {
            handled = true;
         }
      }

      return handled;
   }

   public static boolean keyUp(int keycode) {
      boolean handled = false;

      for (int i = 0; i < listeners.size(); i++) {
         if (listeners.get(i).keyUp(keycode)) {
            handled = true;
         }
      }

      return handled;
   }

   public static boolean keyDown(int keycode) {
      boolean handled = false;

      for (int i = 0; i < listeners.size(); i++) {
         if (listeners.get(i).keyDown(keycode)) {
            handled = true;
         }
      }

      return handled;
   }
}
