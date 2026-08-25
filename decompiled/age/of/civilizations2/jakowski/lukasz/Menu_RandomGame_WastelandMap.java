package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.GdxRuntimeException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Menu_RandomGame_WastelandMap extends SliderMenu {
   public static final int ANIMATION_TIME = 250;
   public static long lTime = 0L;

   public Menu_RandomGame_WastelandMap() {
      int tempW = CFG.CIV_INFO_MENU_WIDTH;
      int tempMaxH = CFG.GAME_HEIGHT
         - (ImageManager.getImage(Images.top_left).getHeight() + CFG.PADDING * 2 + CFG.BUTTON_HEIGHT * 3 / 4)
         - (CFG.BUTTON_HEIGHT + CFG.PADDING * 2)
         - CFG.PADDING;
      int tempElemH = CFG.BUTTON_HEIGHT * 3 / 4;
      int tY = 0;
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      menuElements.add(new Button_CNG_Options(null, -1, 0, tY, tempW, tempElemH, true));
      int var14;
      menuElements.add(
         new Button_CNG_Options_Text2(
            CFG.map.getMapName(CFG.map.getActiveMapID()),
            CFG.langManager.get("Provinces") + ": " + CFG.game.countLandProvinces(),
            CFG.PADDING * 2,
            0,
            var14 = tY + menuElements.get(menuElements.size() - 1).getHeight(),
            tempW,
            tempElemH,
            true
         )
      );
      tY = var14 + menuElements.get(menuElements.size() - 1).getHeight();

      for (int i = 1; i < CFG.map.getMapContinents().getContinentsSize(); i++) {
         menuElements.add(
            new Button_CNG_Options_Text2(
               CFG.map.getMapContinents().getName(i),
               CFG.langManager.get("Provinces") + ": " + CFG.game.countContinentProvinces(i),
               CFG.PADDING * 2,
               0,
               tY,
               tempW,
               tempElemH,
               true
            )
         );
         tY += menuElements.get(menuElements.size() - 1).getHeight();
      }

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

            for (int var20 = 0; var20 < iSize; var20++) {
               tagsSPLITED[var20] = tempFiles.get(var20);
            }
         }

         int tempLandProvinces = 0;

         for (int i = 0; i < CFG.game.getProvincesSize(); i++) {
            if (!CFG.game.getProvince(i).getSeaProvince()) {
               tempLandProvinces++;
            }
         }

         for (int var17 = 0; var17 < tagsSPLITED.length; var17++) {
            FileHandle fileData = Gdx.files.internal("map/" + CFG.map.getFile_ActiveMap_Path() + "data/wasteland_maps/" + tagsSPLITED[var17]);

            try {
               WastelandMap_GameData tempGameData = (WastelandMap_GameData)CFG.deserialize(fileData.readBytes());
               menuElements.add(
                  new Button_CNG_Options_Text2(
                     CFG.langManager.get(tempGameData.getName()),
                     CFG.langManager.get("Provinces") + ": " + (tempLandProvinces - tempGameData.getWastelandProvincesSize()),
                     CFG.PADDING * 2,
                     0,
                     tY,
                     tempW,
                     tempElemH,
                     true
                  )
               );
               tY += menuElements.get(menuElements.size() - 1).getHeight();
            } catch (ClassNotFoundException var11) {
            } catch (IOException var12) {
            }
         }
      } catch (GdxRuntimeException var13) {
      }

      menuElements.add(
         new Button_CNG_Options(
            null, -1, 0, menuElements.get(menuElements.size() - 1).getPosY() + menuElements.get(menuElements.size() - 1).getHeight(), tempW, tempElemH, true
         )
      );

      for (int ix = 1; ix < menuElements.size() - 1; ix++) {
         menuElements.get(ix).setCurrent(ix);
      }

      this.initMenu(
         new SliderMenuTitle(null, CFG.BUTTON_HEIGHT * 3 / 4, false, false) {
            @Override
            public void draw(SpriteBatch oSB, int iTranslateX, int nPosX, int nPosY, int nWidth, boolean sliderMenuIsActive) {
               ImageManager.getImage(Images.new_game_top_edge_title)
                  .draw2(
                     oSB,
                     Menu_RandomGame_WastelandMap.this.getPosX() + iTranslateX,
                     Menu_RandomGame_WastelandMap.this.getPosY() - ImageManager.getImage(Images.new_game_top_edge_title).getHeight() - this.getHeight(),
                     Menu_RandomGame_WastelandMap.this.getWidth() + 2,
                     this.getHeight(),
                     true,
                     false
                  );
               oSB.setColor(new Color(0.011F, 0.014F, 0.019F, 0.25F));
               ImageManager.getImage(Images.gradient)
                  .draw(
                     oSB,
                     Menu_RandomGame_WastelandMap.this.getPosX() + iTranslateX,
                     Menu_RandomGame_WastelandMap.this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() - this.getHeight() * 3 / 4,
                     Menu_RandomGame_WastelandMap.this.getWidth(),
                     this.getHeight() * 3 / 4,
                     false,
                     true
                  );
               oSB.setColor(new Color(0.451F, 0.329F, 0.11F, 1.0F));
               ImageManager.getImage(Images.pix255_255_255)
                  .draw(
                     oSB,
                     Menu_RandomGame_WastelandMap.this.getPosX() + iTranslateX,
                     Menu_RandomGame_WastelandMap.this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight(),
                     Menu_RandomGame_WastelandMap.this.getWidth()
                  );
               oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.4F));
               ImageManager.getImage(Images.line_32_off1)
                  .draw(
                     oSB,
                     Menu_RandomGame_WastelandMap.this.getPosX() + iTranslateX,
                     Menu_RandomGame_WastelandMap.this.getPosY()
                        - ImageManager.getImage(Images.pix255_255_255).getHeight()
                        - ImageManager.getImage(Images.line_32_off1).getHeight(),
                     Menu_RandomGame_WastelandMap.this.getWidth(),
                     1
                  );
               oSB.setColor(Color.WHITE);
               CFG.fontMain.getData().setScale(0.8F);
               CFG.drawText(
                  oSB,
                  this.getText(),
                  nPosX + nWidth / 2 - (int)(this.getTextWidth() * 0.8F / 2.0F) + iTranslateX,
                  nPosY - this.getHeight() + this.getHeight() / 2 + 1 - (int)(this.getTextHeight() * 0.8F / 2.0F),
                  CFG.COLOR_TEXT_OPTIONS_LEFT_NS
               );
               CFG.fontMain.getData().setScale(1.0F);
            }
         },
         0,
         ImageManager.getImage(Images.top_left).getHeight() + CFG.PADDING * 2 + CFG.BUTTON_HEIGHT * 3 / 4,
         tempW,
         tempMaxH < menuElements.get(menuElements.size() - 1).getPosY() + menuElements.get(menuElements.size() - 1).getHeight()
            ? tempMaxH
            : menuElements.get(menuElements.size() - 1).getPosY() + menuElements.get(menuElements.size() - 1).getHeight(),
         menuElements
      );
      this.setVisible(false);
      this.updateLanguage();
   }

   @Override
   public void updateLanguage() {
      this.getMenuElement(0).setText(CFG.langManager.get("CustomizeWasteland"));
      this.getMenuElement(this.getMenuElementsSize() - 1).setText(CFG.langManager.get("Back"));
      this.getTitle().setText(CFG.langManager.get("Maps"));
   }

   @Override
   public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      if (lTime + 250L >= System.currentTimeMillis()) {
         iTranslateX += -this.getWidth() + (int)(this.getWidth() * ((float)(System.currentTimeMillis() - lTime) / 250.0F));
         CFG.setRender_3(true);
      }

      ImageManager.getImage(Images.new_game_top_edge_line)
         .draw2(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.new_game_top_edge_line).getHeight() + iTranslateY,
            this.getWidth() + 2,
            this.getHeight(),
            true,
            true
         );
      super.draw(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
      oSB.setColor(CFG.COLOR_CREATE_NEW_GAME_BOX_PLAYERS);
      ImageManager.getImage(Images.pix255_255_255)
         .draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + this.getHeight(), this.getWidth());
      oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.4F));
      ImageManager.getImage(Images.line_32_off1)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY()
               - ImageManager.getImage(Images.pix255_255_255).getHeight()
               - ImageManager.getImage(Images.line_32_off1).getHeight()
               + this.getHeight(),
            this.getWidth(),
            1
         );
      oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.5F));
      ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight(), this.getWidth() + 2);
      oSB.setColor(Color.WHITE);
   }

   @Override
   public void drawScrollPos(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      if ((sliderMenuIsActive || this.getScrollModeY()) && !CFG.menuManager.getSliderMode()) {
         super.drawScrollPos(oSB, iTranslateX - 2, iTranslateY, sliderMenuIsActive);
      }
   }

   @Override
   public void actionElement(int iID) {
      if (iID == 0) {
         CFG.lCreateScenario_UndoWastelandProvinces = new ArrayList<>();
         CFG.backToMenu = Menu.eCREATE_RANDOM_GAME;
         CFG.goToMenu = Menu.eCREATE_RANDOM_GAME;
         CFG.menuManager.setViewID(Menu.eCREATE_SCENARIO_AVAILABLE_PROVINCES);
         CFG.map.getMapCoordinates().centerToRandomMapPosition();
      } else if (iID == 1) {
         for (int i = 0; i < CFG.game.getProvincesSize(); i++) {
            if (!CFG.game.getProvince(i).getSeaProvince()) {
               CFG.game.getProvince(i).setWasteland(-1);
            }
         }

         ArrayList<String> tMess = new ArrayList<>();
         ArrayList<Color> tColor = new ArrayList<>();
         tMess.add(this.getMenuElement(iID).getText());
         tColor.add(Color.WHITE);
         tMess.add(CFG.langManager.get("Provinces") + ": " + CFG.game.countLandProvinces());
         tColor.add(CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE);
         CFG.toast.setInView(tMess, tColor);
      } else if (iID == this.getMenuElementsSize() - 1) {
         CFG.randomGameManager.checkCapitals();
         CFG.menuManager.setVisible_CreateRandomGame_Options(true);
         CFG.map.getMapCoordinates().centerToRandomMapPosition();
      } else if (iID <= CFG.map.getMapContinents().getContinentsSize()) {
         for (int ix = 0; ix < CFG.game.getProvincesSize(); ix++) {
            if (!CFG.game.getProvince(ix).getSeaProvince()) {
               if (CFG.game.getProvince(ix).getContinent() == iID - 1) {
                  CFG.game.getProvince(ix).setWasteland(-1);
               } else {
                  CFG.game.getProvince(ix).setWasteland(0);
               }
            }
         }

         CFG.game.buildWastelandLevels();
         ArrayList<String> tMess = new ArrayList<>();
         ArrayList<Color> tColor = new ArrayList<>();
         tMess.add(this.getMenuElement(iID).getText());
         tColor.add(Color.WHITE);
         tMess.add(CFG.langManager.get("Provinces") + ": " + CFG.game.countContinentProvinces(iID - 1));
         tColor.add(CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE);
         CFG.toast.setInView(tMess, tColor);
      } else {
         for (int ixx = 0; ixx < CFG.game.getProvincesSize(); ixx++) {
            if (!CFG.game.getProvince(ixx).getSeaProvince()) {
               CFG.game.getProvince(ixx).setWasteland(-1);
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

            for (int ixxx = 0; ixxx < iSize; ixxx++) {
               if (tempFiles.get(ixxx).equals("Age_of_Civilizations")) {
                  tempFiles.remove(ixxx);
                  break;
               }
            }

            tagsSPLITED = new String[tempFiles.size()];
            iSize = tempFiles.size();

            for (int var18 = 0; var18 < iSize; var18++) {
               tagsSPLITED[var18] = tempFiles.get(var18);
            }
         }

         try {
            FileHandle fileData = Gdx.files
               .internal(
                  "map/" + CFG.map.getFile_ActiveMap_Path() + "data/wasteland_maps/" + tagsSPLITED[iID - 1 - CFG.map.getMapContinents().getContinentsSize()]
               );
            WastelandMap_GameData tempGameData = (WastelandMap_GameData)CFG.deserialize(fileData.readBytes());
            int iSize = tempGameData.getWastelandProvincesSize();

            for (int ixxxx = 0; ixxxx < iSize; ixxxx++) {
               CFG.game.getProvince(tempGameData.getWastelandProvinceID(ixxxx)).setWasteland(0);
            }
         } catch (ClassNotFoundException var7) {
         } catch (IOException var8) {
         } catch (IndexOutOfBoundsException var9) {
         }

         CFG.game.buildWastelandLevels();
         ArrayList<String> tMess = new ArrayList<>();
         ArrayList<Color> tColor = new ArrayList<>();
         tMess.add(this.getMenuElement(iID).getText());
         tColor.add(Color.WHITE);
         tMess.add(CFG.langManager.get("Provinces") + ": " + CFG.game.countLandProvinces_NotWasteland());
         tColor.add(CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE);
         CFG.toast.setInView(tMess, tColor);
      }
   }

   @Override
   public void setVisible(boolean visible) {
      super.setVisible(visible);
      lTime = System.currentTimeMillis();
   }
}
