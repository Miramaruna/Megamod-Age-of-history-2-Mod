package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.GdxRuntimeException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Menu_MapEditor_WastelandMaps extends SliderMenu {
   public Menu_MapEditor_WastelandMaps() {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      menuElements.add(new Button_Menu(null, -1, 0, CFG.PADDING, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT, true));
      menuElements.add(new Button_Menu_LR_Line(null, -1, 0, CFG.PADDING, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT, true));

      try {
         String[] tagsSPLITED = null;
         if (!CFG.isDesktop()) {
            FileHandle tempFileT = Gdx.files.internal("map/" + CFG.map.getFile_ActiveMap_Path() + "data/wasteland_maps/Age_of_Civilizations");
            String tempT = tempFileT.readString();
            tagsSPLITED = tempT.split(";");
         } else {
            List<String> tempFiles = CFG.getFileNames("map/" + CFG.map.getFile_ActiveMap_Path() + "data/wasteland_maps/");
            int iSize = tempFiles.size();

            for (int i = 0; i < iSize; i++) {
               if (tempFiles.get(i).equals("Age_of_Civilizations")) {
                  tempFiles.remove(i);
                  break;
               }
            }

            tagsSPLITED = new String[tempFiles.size()];
            iSize = tempFiles.size();

            for (int var10 = 0; var10 < iSize; var10++) {
               tagsSPLITED[var10] = tempFiles.get(var10);
            }
         }

         for (int ix = 0; ix < tagsSPLITED.length; ix++) {
            FileHandle fileData = Gdx.files.internal("map/" + CFG.map.getFile_ActiveMap_Path() + "data/wasteland_maps/" + tagsSPLITED[ix]);

            try {
               WastelandMap_GameData tempGameData = (WastelandMap_GameData)CFG.deserialize(fileData.readBytes());
               menuElements.add(
                  new Button_Menu(
                     tempGameData.getName() + ": " + tempGameData.getWastelandProvincesSize() + " " + CFG.langManager.get("Provinces"),
                     (int)(50.0F * CFG.GUI_SCALE),
                     0,
                     CFG.BUTTON_HEIGHT * (ix + 1) + CFG.PADDING * (ix + 2),
                     CFG.GAME_WIDTH,
                     CFG.BUTTON_HEIGHT,
                     true
                  ) {
                     @Override
                     public void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                        CFG.map
                           .getIcon(CFG.map.getActiveMapID())
                           .draw(
                              oSB,
                              this.getPosX() + this.getTextPos() / 2 - CFG.map.getIcon(CFG.map.getActiveMapID()).getWidth() / 2 + iTranslateX,
                              this.getPosY() + this.getHeight() / 2 - CFG.map.getIcon(CFG.map.getActiveMapID()).getHeight() / 2 + iTranslateY
                           );
                        super.drawText(oSB, iTranslateX, iTranslateY, isActive);
                     }
                  }
               );
            } catch (ClassNotFoundException var6) {
            } catch (IOException var7) {
            }
         }
      } catch (GdxRuntimeException var8) {
      }

      this.initMenuWithBackButton(
         new SliderMenuTitle(null, CFG.BUTTON_HEIGHT * 3 / 4, false, false),
         0,
         CFG.BUTTON_HEIGHT * 3 / 4,
         CFG.GAME_WIDTH,
         CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT * 3 / 4,
         menuElements
      );
      this.updateLanguage();
   }

   @Override
   public void updateLanguage() {
      this.getMenuElement(0).setText(CFG.langManager.get("Back"));
      this.getMenuElement(1).setText(CFG.langManager.get("AddNewWastelandMap"));
      this.getTitle().setText(CFG.langManager.get("WastelandMapsEditor"));
   }

   @Override
   public final void actionElement(int iID) {
      CFG.lCreateScenario_UndoWastelandProvinces = new ArrayList<>();
      switch (iID) {
         case 0:
            this.onBackPressed();
            return;
         case 1:
            CFG.RELOAD_SCENARIO = true;

            for (int i = 0; i < CFG.game.getProvincesSize(); i++) {
               if (!CFG.game.getProvince(i).getSeaProvince()) {
                  CFG.game.getProvince(i).setWasteland(-1);
               }
            }

            CFG.brushTool = false;
            CFG.bSetWasteland_AvailableProvinces = true;
            CFG.EDITOR_ACTIVE_GAMEDATA_TAG = System.currentTimeMillis() + CFG.extraRandomTag();
            CFG.CREATE_SCENARIO_NAME = "";
            CFG.menuManager.setViewID(Menu.eMAP_EDITOR_WASTELAND_MAPS_EDIT);
            return;
         default:
            CFG.RELOAD_SCENARIO = true;
            int i = 0;

            for (; i < CFG.game.getProvincesSize(); i++) {
               if (!CFG.game.getProvince(i).getSeaProvince()) {
                  CFG.game.getProvince(i).setWasteland(-1);
               }
            }

            CFG.brushTool = false;
            CFG.bSetWasteland_AvailableProvinces = true;
            String[] tagsSPLITED = null;
            String[] var12;
            if (CFG.isDesktop()) {
               List<String> tempFiles = CFG.getFileNames("map/" + CFG.map.getFile_ActiveMap_Path() + "data/wasteland_maps/");
               int iSize = tempFiles.size();

               for (int ix = 0; ix < iSize; ix++) {
                  if (tempFiles.get(ix).equals("Age_of_Civilizations")) {
                     tempFiles.remove(ix);
                     break;
                  }
               }

               var12 = new String[tempFiles.size()];
               iSize = tempFiles.size();

               for (int var14 = 0; var14 < iSize; var14++) {
                  var12[var14] = tempFiles.get(var14);
               }
            } else {
               FileHandle tempFileT = Gdx.files.internal("map/" + CFG.map.getFile_ActiveMap_Path() + "data/wasteland_maps/Age_of_Civilizations");
               String tempT = tempFileT.readString();
               var12 = tempT.split(";");
            }

            CFG.EDITOR_ACTIVE_GAMEDATA_TAG = var12[iID - 2];
            CFG.CREATE_SCENARIO_NAME = "";
            FileHandle fileData = Gdx.files.internal("map/" + CFG.map.getFile_ActiveMap_Path() + "data/wasteland_maps/" + CFG.EDITOR_ACTIVE_GAMEDATA_TAG);

            try {
               WastelandMap_GameData tempGameData = (WastelandMap_GameData)CFG.deserialize(fileData.readBytes());
               int iSize = tempGameData.getWastelandProvincesSize();

               for (int ixx = 0; ixx < iSize; ixx++) {
                  try {
                     if (!CFG.game.getProvince(tempGameData.getWastelandProvinceID(ixx)).getSeaProvince()) {
                        CFG.game.getProvince(tempGameData.getWastelandProvinceID(ixx)).setWasteland(0);
                     }
                  } catch (IndexOutOfBoundsException var8) {
                  }
               }

               CFG.CREATE_SCENARIO_NAME = tempGameData.getName();
            } catch (ClassNotFoundException var9) {
            } catch (IOException var10) {
            }

            CFG.game.buildWastelandLevels();
            CFG.menuManager.setViewID(Menu.eMAP_EDITOR_WASTELAND_MAPS_EDIT);
      }
   }

   @Override
   public void onBackPressed() {
      CFG.menuManager.setViewID(Menu.eMAP_EDITOR_EDIT);
      CFG.menuManager.setBackAnimation(true);

      for (int i = 0; i < CFG.game.getProvincesSize(); i++) {
         CFG.game.getProvince(i).setWasteland(-1);
      }

      CFG.lCreateScenario_UndoWastelandProvinces = null;
   }
}
