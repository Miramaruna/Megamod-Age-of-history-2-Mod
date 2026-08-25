package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.GdxRuntimeException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Menu_CreateScenario_WastelandMap_List extends SliderMenu {
   public Menu_CreateScenario_WastelandMap_List() {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      menuElements.add(
         new Button_Game_TextTwoLines(
            null, CFG.langManager.get("Provinces") + ": " + CFG.game.countLandProvinces(), -1, CFG.PADDING, CFG.PADDING, CFG.BUTTON_WIDTH, true
         ) {
            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(this.getText(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("NumberOfProvinces") + ": "));
               nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + CFG.game.countLandProvinces(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
         }
      );

      try {
         String[] tagsSPLITED = null;
         if (!CFG.isDesktop()) {
            FileHandle tempFileT = Gdx.files.internal("map/" + CFG.map.getFile_ActiveMap_Path() + "data/wasteland_maps/Age_of_Civilizations");
            String tempT = tempFileT.readString();
            tagsSPLITED = tempT.split(";");
         } else {
            List<String> tempFiles = CFG.getFileNames("map/" + CFG.map.getFile_ActiveMap_Path() + "data/wasteland_maps/");
            int iSize = tempFiles.size();

            for (int i2 = 0; i2 < iSize; i2++) {
               if (tempFiles.get(i2).equals("Age_of_Civilizations")) {
                  tempFiles.remove(i2);
                  break;
               }
            }

            tagsSPLITED = new String[tempFiles.size()];
            iSize = tempFiles.size();

            for (int var12 = 0; var12 < iSize; var12++) {
               tagsSPLITED[var12] = tempFiles.get(var12);
            }
         }

         int tempLandProvinces = 0;

         for (int i = 0; i < CFG.game.getProvincesSize(); i++) {
            if (!CFG.game.getProvince(i).getSeaProvince()) {
               tempLandProvinces++;
            }
         }

         for (int var10 = 0; var10 < tagsSPLITED.length; var10++) {
            FileHandle fileData = Gdx.files.internal("map/" + CFG.map.getFile_ActiveMap_Path() + "data/wasteland_maps/" + tagsSPLITED[var10]);

            try {
               WastelandMap_GameData tempGameData = (WastelandMap_GameData)CFG.deserialize(fileData.readBytes());
               menuElements.add(
                  new Button_Game_TextTwoLines(
                     CFG.langManager.get(tempGameData.getName()),
                     CFG.langManager.get("Provinces") + ": " + (tempLandProvinces - tempGameData.getWastelandProvincesSize()),
                     -1,
                     CFG.PADDING * (var10 + 2),
                     CFG.PADDING,
                     CFG.BUTTON_WIDTH,
                     true
                  )
               );
            } catch (ClassNotFoundException var7) {
            } catch (IOException var8) {
            }
         }
      } catch (GdxRuntimeException var9) {
      }

      this.initMenu(
         null,
         0,
         CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING * 2,
         CFG.GAME_WIDTH - CFG.map.getMapBG().getMinimapWidth(),
         CFG.BUTTON_HEIGHT + CFG.PADDING * 2,
         menuElements,
         true,
         false
      );
      this.updateLanguage();
      this.updatedButtonsWidth(CFG.PADDING, CFG.BUTTON_WIDTH);
   }

   @Override
   public void updateLanguage() {
      this.getMenuElement(0).setText(CFG.langManager.get("World"));
   }

   @Override
   public void updateMenuElements_IsInView() {
      super.updateMenuElements_IsInView_X();
   }

   @Override
   public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      ImageManager.getImage(Images.editor_line)
         .draw2(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.editor_line).getHeight() + iTranslateY,
            this.getWidth(),
            CFG.BUTTON_HEIGHT + CFG.PADDING * 2
         );
      oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.55F));
      ImageManager.getImage(Images.slider_gradient)
         .draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY, this.getWidth(), 1);
      oSB.setColor(new Color(0.137F, 0.141F, 0.145F, 1.0F));
      ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, 1, this.getHeight());
      oSB.setColor(Color.WHITE);
      super.draw(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
   }

   @Override
   public final void actionElement(int iID) {
      for (int i = 0; i < CFG.menuManager.getCreateScenario_WastelandContinents().getMenuElementsSize(); i++) {
         CFG.menuManager.getCreateScenario_WastelandContinents().getMenuElement(i).setCheckboxState(true);
      }

      CFG.toast.setInView(this.getMenuElement(iID).getText());
      if (iID == 0) {
         for (int var11 = 0; var11 < CFG.game.getProvincesSize(); var11++) {
            CFG.game.getProvince(var11).setWasteland(-1);
         }
      } else {
         for (int var12 = 0; var12 < CFG.game.getProvincesSize(); var12++) {
            if (!CFG.game.getProvince(var12).getSeaProvince()) {
               CFG.game.getProvince(var12).setWasteland(-1);
            }
         }

         String[] tagsSPLITED = null;
         if (!CFG.isDesktop()) {
            FileHandle tempFileT = Gdx.files.internal("map/" + CFG.map.getFile_ActiveMap_Path() + "data/wasteland_maps/Age_of_Civilizations");
            String tempT = tempFileT.readString();
            tagsSPLITED = tempT.split(";");
         } else {
            List<String> tempFiles = CFG.getFileNames("map/" + CFG.map.getFile_ActiveMap_Path() + "data/wasteland_maps/");
            int iSize = tempFiles.size();

            for (int i2 = 0; i2 < iSize; i2++) {
               if (tempFiles.get(i2).equals("Age_of_Civilizations")) {
                  tempFiles.remove(i2);
                  break;
               }
            }

            tagsSPLITED = new String[tempFiles.size()];
            iSize = tempFiles.size();

            for (int var14 = 0; var14 < iSize; var14++) {
               tagsSPLITED[var14] = tempFiles.get(var14);
            }
         }

         try {
            FileHandle fileData = Gdx.files.internal("map/" + CFG.map.getFile_ActiveMap_Path() + "data/wasteland_maps/" + tagsSPLITED[iID - 1]);
            WastelandMap_GameData tempGameData = (WastelandMap_GameData)CFG.deserialize(fileData.readBytes());
            int iSize = tempGameData.getWastelandProvincesSize();

            for (int i3 = 0; i3 < iSize; i3++) {
               CFG.game.getProvince(tempGameData.getWastelandProvinceID(i3)).setWasteland(0);
            }
         } catch (ClassNotFoundException var8) {
         } catch (IOException var9) {
         } catch (IndexOutOfBoundsException var10) {
         }

         CFG.game.buildWastelandLevels();
      }
   }
}
