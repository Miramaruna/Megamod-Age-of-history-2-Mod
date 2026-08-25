package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import java.util.ArrayList;
import java.util.List;

class Game_CustomBuildings {
   public final List<age.of.civilizations2.jakowski.lukasz.CustomBuilding> loadCustomBuildings() {
      return new ArrayList<>();
   }

   private final Game_CustomBuildings.Config readCustomBuildings() {
      FileHandle handle = Gdx.files.internal("files/Buildings.json");
      String fileContent = handle.readString();
      Json json = new Json();
      json.setElementType(Game_CustomBuildings.Config.class, "CustomBuildings", Game_CustomBuildings.CustomBuilding.class);
      return json.fromJson(Game_CustomBuildings.Config.class, fileContent);
   }

   public static class Config {
      private ArrayList CustomBuildings;
      private String name;
   }

   public static class CustomBuilding {
      String[] Names;
      float[] Build_Cost;
      int[] Build_Movement_Cost;
      int[] Defense_Bonus;
      float[] Tech_Level;
      int[] Construction;
      String[] Image;
      int[] Turn_PopGrowth;
      int[] Turn_GoldIncome;
      int[] Turn_Soldiers;
      int[] Turn_MovementPoints;
      int[] Turn_Economy;
   }
}
