package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Map_Continents {
   public List<String> lName;
   public List<Color> lColor;
   public int iContinentsSize;
   public static final String OCEAN_CONTINENT_TAG = "1486419009922xximucak";
   public int iOceanContinentID;

   public Map_Continents(String nTag) {
      this.loadContinents(nTag);
   }

   public final void loadContinents(String nTag) {
      this.lName = new ArrayList<>();
      this.lColor = new ArrayList<>();

      try {
         FileHandle file = Gdx.files.internal("map/data/continents/packges/" + nTag);
         Package_ContinentsData tempPackageContinentGameData = (Package_ContinentsData)CFG.deserialize(file.readBytes());

         for (int i = 0; i < tempPackageContinentGameData.getContinentsTagsSize(); i++) {
            try {
               FileHandle fileContinent = Gdx.files.internal("map/data/continents/packges_data/" + tempPackageContinentGameData.getContinentTag(i));
               Continent_GameData tempContinentGameData = (Continent_GameData)CFG.deserialize(fileContinent.readBytes());
               this.lName.add(CFG.langManager.get(tempContinentGameData.getName()));
               this.lColor.add(new Color(tempContinentGameData.getR(), tempContinentGameData.getG(), tempContinentGameData.getB(), 0.7F));
               if (tempPackageContinentGameData.getContinentTag(i).equals("1486419009922xximucak")) {
                  this.iOceanContinentID = i;
               }
            } catch (ClassNotFoundException var7) {
            } catch (IOException var8) {
            }
         }
      } catch (ClassNotFoundException var9) {
      } catch (IOException var10) {
      }

      this.iContinentsSize = this.lName.size();
   }

   public final String getName(int i) {
      return this.lName.get(i);
   }

   public final Color getColor(int i) {
      return this.lColor.get(i);
   }

   public final int getContinentsSize() {
      return this.iContinentsSize;
   }

   public final int getOceanContinentID() {
      return this.iOceanContinentID;
   }
}
