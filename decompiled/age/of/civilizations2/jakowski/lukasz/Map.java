package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.Json;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Map {
   public int iActiveMapID = 0;
   public List<String> sMAP_TAGS;
   public List<String> sMAP_LANG_KEY;
   public List<Integer> iMAP_NUM_OF_PROVINCES;
   public List<Integer> iMAP_SCALE;
   public List<Integer> iMAP_DEFAULT_SCALE;
   public List<String> sMAP_BACKGROUND_NAME;
   public List<String> sMAP_CONTINENTS_PACKAGE_TAG;
   public List<String> sMAP_REGIONS_PACKAGE_TAG;
   public List<String> sMAP_AUTHOR;
   public List<Boolean> MAP_WORLD_MAP;
   public List<String> MAP_SCENARIO;
   public List<String> sMAP_WIKI;
   public List<Image> iMAP_ICON;
   public Map_BG mapBG = null;
   public Map_Coordinates mapCoordinates = null;
   public Map_TouchManager mapTouchManager = null;
   public Map_Scroll mapScroll = null;
   public Map_Scale mapScale = null;
   public Map_Continents mapContinents = null;
   public Map_Regions mapRegions = null;
   public int iNumOfBasins = 0;
   public static boolean GAME_CRASHED_LOADED_MIN_SCALE = false;

   public Map() {
      new Map.Config();
      Json json = new Json();
      json.setElementType(Map.Config.class, "Map", Map.Maps.class);
      Map.Config data = json.fromJson(Map.Config.class, Gdx.files.internal("map/Age_of_Civilizations.json").reader("UTF8"));
      this.sMAP_TAGS = new ArrayList<>();

      for (Object e : data.Map) {
         Map.Maps tempMapFolder = (Map.Maps)e;
         this.sMAP_TAGS.add(tempMapFolder.Folder);
      }

      this.sMAP_LANG_KEY = new ArrayList<>();
      this.iMAP_NUM_OF_PROVINCES = new ArrayList<>();
      this.iMAP_SCALE = new ArrayList<>();
      this.iMAP_DEFAULT_SCALE = new ArrayList<>();
      this.sMAP_BACKGROUND_NAME = new ArrayList<>();
      this.sMAP_AUTHOR = new ArrayList<>();
      this.sMAP_WIKI = new ArrayList<>();
      this.sMAP_CONTINENTS_PACKAGE_TAG = new ArrayList<>();
      this.sMAP_REGIONS_PACKAGE_TAG = new ArrayList<>();
      this.iMAP_ICON = new ArrayList<>();
      this.MAP_WORLD_MAP = new ArrayList<>();
      this.MAP_SCENARIO = new ArrayList<>();
      int iSize = this.sMAP_TAGS.size();

      for (int i = 0; i < iSize; i++) {
         new Map.Config();
         json.setElementType(Map.Config.class, "Map", Map.MapInformations.class);
         data = json.fromJson(Map.Config.class, Gdx.files.internal("map/" + this.sMAP_TAGS.get(i) + "/config.json").reader("UTF8"));
         Iterator iterator = data.Map.iterator();
         if (iterator.hasNext()) {
            Object e = iterator.next();
            Map.MapInformations tempMapFolder = (Map.MapInformations)e;
            this.sMAP_LANG_KEY.add(tempMapFolder.MapName);
            this.sMAP_AUTHOR.add(tempMapFolder.Author);
            this.sMAP_BACKGROUND_NAME.add(tempMapFolder.BackgroundName);
            this.sMAP_CONTINENTS_PACKAGE_TAG.add(tempMapFolder.ContinentsPackage);
            this.sMAP_REGIONS_PACKAGE_TAG.add(tempMapFolder.RegionsPackage);
            this.iMAP_NUM_OF_PROVINCES.add(tempMapFolder.NumberOfProvinces);
            this.iMAP_SCALE
               .add(
                  tempMapFolder.MapScale
                     + (
                        CFG.isDesktop()
                           ? (CFG.XHDPI ? 1 : 0)
                           : (CFG.isAndroid() ? (!CFG.XXXXHDPI && !CFG.XXXHDPI && !CFG.XXHDPI ? (CFG.XHDPI ? 1 : 0) : 2) : 0)
                     )
               );
            this.iMAP_DEFAULT_SCALE.add(tempMapFolder.MapScale);
            this.MAP_WORLD_MAP.add(tempMapFolder.WorldMap);
            this.MAP_SCENARIO.add(tempMapFolder.Scenario);
            this.sMAP_WIKI.add(tempMapFolder.Wiki);
         }

         this.iMAP_ICON.add(new Image(new Texture(Gdx.files.internal("map/" + this.sMAP_TAGS.get(i) + "/ico.png"))));
      }

      this.mapBG = new Map_BG();
      this.mapCoordinates = new Map_Coordinates();
      this.mapTouchManager = new Map_TouchManager();
      this.mapScroll = new Map_Scroll();
      this.mapScale = new Map_Scale();
   }

   public final void loadSettings_ActiveMap() {
      try {
         try {
            FileHandle file = Gdx.files.local("settings_map");
            SaveActiveMap_GameData tempActiveMapData = (SaveActiveMap_GameData)CFG.deserialize(file.readBytes());
            if (tempActiveMapData.iActiveMapID >= 0 && tempActiveMapData.iActiveMapID < this.getNumOfMaps()) {
               CFG.map.setMapScale(tempActiveMapData.iActiveMapID, tempActiveMapData.iActiveMapScale);
               CFG.map.setActiveMapID(tempActiveMapData.iActiveMapID);
            }
         } catch (GdxRuntimeException var8) {
            FileHandle file2 = Gdx.files.internal("settings_map");
            SaveActiveMap_GameData tempActiveMapData2 = (SaveActiveMap_GameData)CFG.deserialize(file2.readBytes());
            if (tempActiveMapData2.iActiveMapID >= 0 && tempActiveMapData2.iActiveMapID < this.getNumOfMaps()) {
               CFG.map.setMapScale(tempActiveMapData2.iActiveMapID, tempActiveMapData2.iActiveMapScale);
               CFG.map.setActiveMapID(tempActiveMapData2.iActiveMapID);
            }
         }
      } catch (GdxRuntimeException var9) {
      } catch (ClassNotFoundException var10) {
      } catch (IOException var11) {
      }

      try {
         try {
            FileHandle filex = Gdx.files.local("status");
            SaveActiveMap_Status_GameData tempActiveMapData3 = (SaveActiveMap_Status_GameData)CFG.deserialize(filex.readBytes());
            this.load_MinScale();
         } catch (GdxRuntimeException var4) {
            FileHandle file2 = Gdx.files.internal("status");
            SaveActiveMap_Status_GameData tempActiveMapData4 = (SaveActiveMap_Status_GameData)CFG.deserialize(file2.readBytes());
            this.load_MinScale();
         }
      } catch (GdxRuntimeException var5) {
      } catch (ClassNotFoundException var6) {
      } catch (IOException var7) {
      }
   }

   public final void load_MinScale() {
      FileHandle tempFileT = Gdx.files.internal("map/" + CFG.map.getFile_Map_Path(this.getActiveMapID()) + "data/scales/provinces/Age_of_Civilizations");
      String tempT = tempFileT.readString();
      String[] tagsSPLITED = tempT.split(";");
      CFG.map.setMapScale(this.getActiveMapID(), Integer.parseInt(tagsSPLITED[0]));
      this.load_DeleteStatusFile();
      GAME_CRASHED_LOADED_MIN_SCALE = true;
   }

   public final void load_DeleteStatusFile() {
      try {
         Gdx.files.local("status").delete();
      } catch (GdxRuntimeException var4) {
         try {
            Gdx.files.internal("status").delete();
         } catch (GdxRuntimeException var3) {
         }
      }
   }

   public final void initMapContinents() {
      this.mapContinents = new Map_Continents(this.sMAP_CONTINENTS_PACKAGE_TAG.get(this.getActiveMapID()));
   }

   public final void initMapRegions() {
      this.mapRegions = new Map_Regions(this.sMAP_REGIONS_PACKAGE_TAG.get(this.getActiveMapID()));
   }

   public final void update() {
      this.mapScale.update();
      this.mapScroll.update();
      this.mapCoordinates.update();
   }

   public final void drawMap(SpriteBatch oSB) {
      if (this.mapBG.requestToDisposeMinimap) {
         this.mapBG.disposeMinimapOfCivilizations_Real();
      }

      this.mapBG.drawMinimapTexture_Generate(oSB);
      this.mapBG.drawMap(oSB, this.mapCoordinates.getPosX(), this.mapCoordinates.getPosY());
      this.mapBG.drawMapBorder(oSB, this.mapCoordinates.getPosX(), this.mapCoordinates.getPosY());
   }

   public final String getFile_ActiveMap_Path() {
      return this.sMAP_TAGS.get(this.iActiveMapID) + "/";
   }

   public final String getFile_Map_Path(int nMapID) {
      return this.sMAP_TAGS.get(nMapID) + "/";
   }

   public final String getMapName_Just(int i) {
      return CFG.langManager.get(this.getMapLangKey(i));
   }

   public final String getMapName(int i) {
      return CFG.langManager.get(this.getMapLangKey(i)) + " | " + this.getMapNumOfProvinces(i) + " " + CFG.langManager.get("Provinces");
   }

   public final void updateWorldMap() {
      this.mapBG.updateWorldMap();
      this.mapCoordinates.updateWorldMap();
   }

   public final Map_BG getMapBG() {
      return this.mapBG;
   }

   public final Map_Coordinates getMapCoordinates() {
      return this.mapCoordinates;
   }

   public final Map_TouchManager getMapTouchManager() {
      return this.mapTouchManager;
   }

   public final Map_Scroll getMapScroll() {
      return this.mapScroll;
   }

   public final Map_Scale getMapScale() {
      return this.mapScale;
   }

   public final Map_Continents getMapContinents() {
      return this.mapContinents;
   }

   public final Map_Regions getMapRegions() {
      return this.mapRegions;
   }

   public final int getActiveMapID() {
      return this.iActiveMapID;
   }

   public final void setActiveMapID(int iActiveMapID) {
      if (this.iActiveMapID != iActiveMapID) {
         this.iActiveMapID = iActiveMapID;
         this.updateWorldMap();
      }
   }

   public final String getMapLangKey(int i) {
      return this.sMAP_LANG_KEY.get(i);
   }

   public final int getMapNumOfProvinces(int i) {
      return this.iMAP_NUM_OF_PROVINCES.get(i);
   }

   public final int getNumOfMaps() {
      return this.sMAP_TAGS.size();
   }

   public final Image getIcon(int i) {
      return this.iMAP_ICON.get(i);
   }

   public final String getMapBackgroundName(int i) {
      return this.sMAP_BACKGROUND_NAME.get(i);
   }

   public final String getMapAuthor(int i) {
      return this.sMAP_AUTHOR.get(i);
   }

   public final String getMapWiki(int i) {
      return this.sMAP_WIKI.get(i);
   }

   public final int setMapScale(int i, int nMapScale) {
      return this.iMAP_SCALE.set(i, nMapScale);
   }

   public final int getMapScale(int i) {
      return this.iMAP_SCALE.get(i);
   }

   public final int getMapDefaultScale(int i) {
      return this.iMAP_DEFAULT_SCALE.get(i);
   }

   public final String getMapContinentsPackageTag(int i) {
      return this.sMAP_CONTINENTS_PACKAGE_TAG.get(i);
   }

   public final String getMapRegionsPackageTag(int i) {
      return this.sMAP_REGIONS_PACKAGE_TAG.get(i);
   }

   public final boolean getMapWorldMap(int i) {
      return this.MAP_WORLD_MAP.get(i);
   }

   public final String getMapDefaultScenario(int i) {
      return this.MAP_SCENARIO.get(i);
   }

   public static class Config {
      public String Age_of_Civilizations;
      public ArrayList Map;

      public void setMapData(ArrayList nMap) {
         this.Map = nMap;
      }
   }

   public static class MapInformations {
      public String MapName;
      public String Author;
      public String BackgroundName;
      public String ContinentsPackage;
      public String RegionsPackage;
      public int NumberOfProvinces;
      public int MapScale;
      public boolean WorldMap;
      public String Scenario;
      public String Wiki;
   }

   public static class Maps {
      public String Folder;
   }

   public static class Mapsrr {
      public String Folder;
   }
}
