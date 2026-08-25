package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import java.util.ArrayList;
import java.util.List;

public class Editor_Regions extends Editor {
   public static int activeRegion = 0;
   public static List<UndoOptimizationRegions> lUndo;

   public static final void addUndo(int nProvinceID) {
      if (nProvinceID >= 0) {
         if (lUndo.size() > 0) {
            if (lUndo.get(lUndo.size() - 1).iProvinceID != nProvinceID && activeRegion != CFG.game.getRegionID(nProvinceID)) {
               if (lUndo.size() > 50) {
                  lUndo.remove(0);
                  lUndo.add(new UndoOptimizationRegions(nProvinceID, CFG.game.getRegionID(nProvinceID)));
               } else {
                  lUndo.add(new UndoOptimizationRegions(nProvinceID, CFG.game.getRegionID(nProvinceID)));
               }
            }
         } else if (activeRegion != CFG.game.getRegionID(nProvinceID)) {
            lUndo.add(new UndoOptimizationRegions(nProvinceID, CFG.game.getRegionID(nProvinceID)));
         }
      }
   }

   public static void popUndo() {
      if (lUndo.size() > 0) {
         CFG.game.setActiveProvinceID(lUndo.get(lUndo.size() - 1).iProvinceID);
         activeRegion = lUndo.get(lUndo.size() - 1).iRegionID;
         actionUpdateRegionID(false);
         if (!CFG.game.getProvince(CFG.game.getActiveProvinceID()).getDrawProvince()) {
            CFG.map.getMapCoordinates().centerToProvinceID(CFG.game.getActiveProvinceID());
         }

         lUndo.remove(lUndo.size() - 1);
      }
   }

   public Editor_Regions() {
      lUndo = new ArrayList<>();
   }

   @Override
   public void keyDown(int keycode) {
      if (Gdx.input.isKeyPressed(21)) {
         if (--activeRegion < 0) {
            activeRegion = CFG.game.getRegions().size();
         }
      } else if (Gdx.input.isKeyPressed(22) && ++activeRegion > CFG.game.getRegions().size()) {
         activeRegion = 0;
      }

      if ((Gdx.input.isKeyPressed(20) || Gdx.input.isKeyPressed(19)) && CFG.game.getActiveProvinceID() >= 0) {
         activeRegion = CFG.game.getRegionID(CFG.game.getActiveProvinceID());
      }

      if (Gdx.input.isKeyPressed(62)) {
         actionUpdateRegionID(true);
      }

      if (Gdx.input.isKeyPressed(66)) {
         saveRegions();
      }
   }

   public static final void actionUpdateRegionID(boolean addUndo) {
      if (CFG.game.getActiveProvinceID() >= 0) {
         if (addUndo) {
            addUndo(CFG.game.getActiveProvinceID());
         }

         for (int i = 0; i < CFG.game.getRegions().size(); i++) {
            for (int j = 0; j < CFG.game.getRegions().get(i).getProvincesSize(); j++) {
               if (CFG.game.getRegions().get(i).getProvince(j) == CFG.game.getActiveProvinceID()) {
                  CFG.game.getRegions().get(i).removeProvince(j);
                  CFG.game.getRegions().get(i).buildRegionBounds();
               }
            }
         }

         if (activeRegion >= CFG.game.getRegions().size()) {
            Menu_GameEditor_Regions.lColors.add(CFG.getRandomColor());
            CFG.game.getRegions().add(new Region());
            CFG.game.getRegions().get(CFG.game.getRegions().size() - 1).addProvince(CFG.game.getActiveProvinceID());
            CFG.game.getRegions().get(CFG.game.getRegions().size() - 1).buildRegionBounds();
            CFG.game.updateRegionsSize();
         } else {
            CFG.game.getRegions().get(activeRegion).addProvince(CFG.game.getActiveProvinceID());
            CFG.game.getRegions().get(activeRegion).buildRegionBounds();
         }

         if (Menu_MapEditor_OptimizationRegions.showValues) {
            CFG.game.getProvince(CFG.game.getActiveProvinceID()).getArmy_Obj(0).updateArmyWidth(CFG.game.getRegionID(CFG.game.getActiveProvinceID()));
         }

         CFG.setRender_3(true);
      }
   }

   public static final void saveRegions() {
      FileHandle fileSave = Gdx.files.local("map/" + CFG.map.getFile_ActiveMap_Path() + "data/regions");
      String sLine = "";

      for (int i = 0; i < CFG.game.getRegions().size(); i++) {
         if (CFG.game.getRegions().get(i).getProvincesSize() == 0) {
            Menu_GameEditor_Regions.lColors.remove(i);
            CFG.game.getRegions().remove(i--);
         }
      }

      for (int var4 = 0; var4 < CFG.game.getRegions().size(); var4++) {
         for (int j = 0; j < CFG.game.getRegions().get(var4).getProvincesSize(); j++) {
            sLine = sLine + CFG.game.getRegions().get(var4).getProvince(j) + ";";
         }

         if (var4 != CFG.game.getRegions().size() - 1) {
            sLine = sLine + "\n";
         }
      }

      fileSave.writeString(sLine, false);
      CFG.game.loadRegions();
   }

   @Override
   public String toString() {
      return "SET TO REGION ID: "
         + activeRegion
         + (
            CFG.game.getActiveProvinceID() >= 0
               ? "\n\nACTIVE PROVINCE REGION ID: "
                  + CFG.game.getRegionID(CFG.game.getActiveProvinceID())
                  + "\nNUMBER OF PROVINCES: "
                  + CFG.game.getRegions().get(CFG.game.getRegionID(CFG.game.getActiveProvinceID())).getProvincesSize()
                  + "\nWIDTH: "
                  + (
                     CFG.game.getRegions().get(CFG.game.getRegionID(CFG.game.getActiveProvinceID())).getMaxX()
                        - CFG.game.getRegions().get(CFG.game.getRegionID(CFG.game.getActiveProvinceID())).getMinX()
                  )
                  + " ["
                  + (int)(
                     (
                           CFG.game.getRegions().get(CFG.game.getRegionID(CFG.game.getActiveProvinceID())).getMaxX()
                              - CFG.game.getRegions().get(CFG.game.getRegionID(CFG.game.getActiveProvinceID())).getMinX()
                        )
                        * 100.0F
                        / CFG.map.getMapBG().getWidth()
                  )
                  + "%]\nHEIGHT:"
                  + (
                     CFG.game.getRegions().get(CFG.game.getRegionID(CFG.game.getActiveProvinceID())).getMaxY()
                        - CFG.game.getRegions().get(CFG.game.getRegionID(CFG.game.getActiveProvinceID())).getMinY()
                  )
                  + " ["
                  + (int)(
                     (
                           CFG.game.getRegions().get(CFG.game.getRegionID(CFG.game.getActiveProvinceID())).getMaxY()
                              - CFG.game.getRegions().get(CFG.game.getRegionID(CFG.game.getActiveProvinceID())).getMinY()
                        )
                        * 100.0F
                        / CFG.map.getMapBG().getHeight()
                  )
                  + "%]"
               : ""
         );
   }
}
