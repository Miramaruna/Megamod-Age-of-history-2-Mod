package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.Json;
import java.util.ArrayList;
import java.util.List;

public class Game_Mountains {
   Game_Mountains() {
   }

   public final List<Mountain> loadMountains() {
      ArrayList<Mountain> nMountains = new ArrayList<>();
      new Game_Mountains.Config();

      try {
         Game_Mountains.Config mountainsData = this.readMountains("mountains.json");

         for (Object e : mountainsData.mountains) {
            Game_Mountains.GameCity oMountainData = (Game_Mountains.GameCity)e;
            nMountains.add(new Mountain(oMountainData.Name, oMountainData.Elevation, oMountainData.x, oMountainData.y));
         }
      } catch (GdxRuntimeException var6) {
      }

      return nMountains;
   }

   public final Game_Mountains.Config readMountains(String nFileName) {
      FileHandle handle = Gdx.files.internal("map/" + CFG.map.getFile_ActiveMap_Path() + "cities/" + nFileName);
      String fileContent = handle.readString();
      Json json = new Json();
      json.setElementType(Game_Mountains.Config.class, "mountains", Game_Mountains.GameCity.class);
      return json.fromJson(Game_Mountains.Config.class, fileContent);
   }

   public static class Config {
      public ArrayList mountains;
      public String name;
   }

   public static class GameCity {
      public String Name;
      public int Elevation;
      public int x;
      public int y;
   }
}
