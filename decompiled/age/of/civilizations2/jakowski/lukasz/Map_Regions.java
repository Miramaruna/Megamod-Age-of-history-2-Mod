package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Map_Regions {
   public List<String> lName;
   public List<Color> lColor;
   public int iRegionsSize;

   public Map_Regions(String nTag) {
      this.loadRegions(nTag);
   }

   public final void loadRegions(String nTag) {
      this.lName = new ArrayList<>();
      this.lColor = new ArrayList<>();

      try {
         FileHandle file = Gdx.files.internal("map/data/regions/packges/" + nTag);
         Package_RegionsData tempPackageRegionGameData = (Package_RegionsData)CFG.deserialize(file.readBytes());

         for (int i = 0; i < tempPackageRegionGameData.getRegionsTagsSize(); i++) {
            try {
               FileHandle fileRegion = Gdx.files.internal("map/data/regions/packges_data/" + tempPackageRegionGameData.getRegionTag(i));
               Region_GameData tempregionGameData = (Region_GameData)CFG.deserialize(fileRegion.readBytes());
               this.lName.add(CFG.langManager.get(tempregionGameData.getName()));
               this.lColor.add(new Color(tempregionGameData.getR(), tempregionGameData.getG(), tempregionGameData.getB(), 0.45F));
            } catch (ClassNotFoundException var7) {
            } catch (IOException var8) {
            }
         }
      } catch (ClassNotFoundException var9) {
      } catch (IOException var10) {
      }

      this.iRegionsSize = this.lName.size();
   }

   public final String getName(int i) {
      return this.lName.get(i);
   }

   public final Color getColor(int i) {
      return this.lColor.get(i);
   }

   public final int getRegionsSize() {
      return this.iRegionsSize;
   }
}
