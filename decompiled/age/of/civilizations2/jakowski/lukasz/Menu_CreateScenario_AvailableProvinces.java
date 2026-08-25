package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

public class Menu_CreateScenario_AvailableProvinces extends Menu_CreateScenario {
   public String selectMapOfAvailableProvinces;
   public int iStepWidth;
   public String selectMapOfAvailableProvinces2;
   public int iStepWidth2;
   public String sPlayableProvinces;
   public int iPlayableProvincesWidth;
   public String sWastelandProvinces;
   public int iWastelandProvincesWidth;

   public Menu_CreateScenario_AvailableProvinces() {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      menuElements.add(new Button_Game(null, -1, CFG.PADDING, CFG.PADDING, true) {
         @Override
         public void buildElementHover() {
            ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
            ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("SelectRegions"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
            this.menuElementHover = new MenuElement_Hover_v2(nElements);
         }
      });
      menuElements.add(new Button_Game(null, -1, CFG.GAME_WIDTH - CFG.BUTTON_WIDTH - CFG.PADDING, CFG.PADDING, true) {
         @Override
         public void buildElementHover() {
            ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
            ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("ManageCivilizations"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
            this.menuElementHover = new MenuElement_Hover_v2(nElements);
         }
      });
      menuElements.add(new Minimap(CFG.GAME_WIDTH - CFG.map.getMapBG().getMinimapWidth(), CFG.GAME_HEIGHT - CFG.map.getMapBG().getMinimapHeight()));
      menuElements.add(
         new Slide(
            CFG.GAME_WIDTH - CFG.PADDING - ImageManager.getImage(Images.slide_bg).getHeight() / 2 - ImageManager.getImage(Images.slide_bg).getHeight() * 2,
            CFG.GAME_HEIGHT
               - CFG.BUTTON_HEIGHT
               - CFG.PADDING * 2
               - CFG.PADDING
               - ImageManager.getImage(Images.slide_bg).getHeight() * 2
               - ImageManager.getImage(Images.slide_bg).getHeight() / 2,
            CFG.brushTool
         )
      );
      menuElements.add(
         new Button_Game_Checkbox(null, -1, CFG.PADDING, CFG.PADDING * 3 + CFG.BUTTON_HEIGHT, CFG.BUTTON_WIDTH * 2, true, CFG.bSetWasteland_AvailableProvinces) {
            @Override
            public boolean getCheckboxState() {
               return CFG.bSetWasteland_AvailableProvinces;
            }
         }
      );
      menuElements.add(
         new Button_Game_Checkbox(
            null, -1, CFG.PADDING * 2 + CFG.BUTTON_WIDTH * 2, CFG.BUTTON_HEIGHT + CFG.PADDING * 3, CFG.BUTTON_WIDTH * 2, true, CFG.brushTool
         ) {
            @Override
            public boolean getCheckboxState() {
               return CFG.brushTool;
            }
         }
      );
      menuElements.add(new Button_Game(null, -1, CFG.PADDING * 3 + CFG.BUTTON_WIDTH * 4, CFG.BUTTON_HEIGHT + CFG.PADDING * 3, false));
      menuElements.add(
         new Button_Game_Checkbox(
            null,
            -1,
            CFG.PADDING,
            CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING,
            CFG.BUTTON_WIDTH + CFG.BUTTON_WIDTH / 2,
            true,
            Game_Calendar.ENABLE_COLONIZATION
         ) {
            @Override
            public boolean getCheckboxState() {
               return Game_Calendar.ENABLE_COLONIZATION;
            }

            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               nData.add(
                  new MenuElement_Hover_v2_Element_Type_Text(
                     CFG.langManager.get("Enable") + "/" + CFG.langManager.get("Disable") + ": ", CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                  )
               );
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("ColonizationofWastelandProvinces") + "."));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
         }
      );
      menuElements.add(
         new Button_Game(
            null,
            -1,
            CFG.BUTTON_WIDTH + CFG.BUTTON_WIDTH / 2 + CFG.PADDING,
            CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING,
            CFG.BUTTON_WIDTH + CFG.BUTTON_WIDTH / 2,
            true
         )
      );
      this.initMenu(null, 0, 0, CFG.GAME_WIDTH, CFG.GAME_HEIGHT, menuElements);
      this.updateLanguage();
   }

   @Override
   public void updateLanguage() {
      super.updateLanguage();
      this.selectMapOfAvailableProvinces = CFG.langManager.get("CustomizeWasteland");
      CFG.glyphLayout.setText(CFG.fontMain, this.selectMapOfAvailableProvinces);
      this.iStepWidth = (int)CFG.glyphLayout.width;
      this.selectMapOfAvailableProvinces2 = CFG.langManager.get("SetWhichProvincesOfTheWorldAreWasteland") + ".";
      CFG.glyphLayout.setText(CFG.fontMain, this.selectMapOfAvailableProvinces2);
      this.iStepWidth2 = (int)CFG.glyphLayout.width;
      this.getMenuElement(4).setText(CFG.langManager.get("Wasteland"));
      this.getMenuElement(5).setText(CFG.langManager.get("Brush"));
      this.getMenuElement(6).setText(CFG.langManager.get("Undo"));
      this.getMenuElement(7).setText(CFG.langManager.get("ColonizationofWasteland"));
      this.getMenuElement(8).setText(CFG.langManager.get("Reverse"));
      this.updatedButtonsWidthFromToID(4, 6, CFG.PADDING, CFG.BUTTON_WIDTH * 2);
      this.updatedButtonsWidthFromToID(6, 7, this.getMenuElement(5).getPosX() + this.getMenuElement(5).getWidth() + CFG.PADDING, CFG.BUTTON_WIDTH);
      this.updatedButtonsWidthFromToID(7, 8, CFG.PADDING, CFG.BUTTON_WIDTH + CFG.BUTTON_WIDTH / 2);
      this.updatedButtonsWidthFromToID(8, 9, CFG.PADDING, CFG.BUTTON_WIDTH);
      this.getMenuElement(8).setPosX(this.getMenuElement(7).getPosX() + this.getMenuElement(7).getWidth() + CFG.PADDING);
      this.sPlayableProvinces = CFG.langManager.get("Playable");
      CFG.glyphLayout.setText(CFG.fontMain, this.sPlayableProvinces + ": ");
      this.iPlayableProvincesWidth = (int)CFG.glyphLayout.width;
      this.sWastelandProvinces = CFG.langManager.get("Wasteland");
      CFG.glyphLayout.setText(CFG.fontMain, this.sWastelandProvinces + ": ");
      this.iWastelandProvincesWidth = (int)CFG.glyphLayout.width;
      int tempX = CFG.GAME_WIDTH - this.getMenuElement(4).getWidth() - CFG.PADDING;
      this.getMenuElement(4).setPosX(tempX);
      tempX = tempX - this.getMenuElement(5).getWidth() - CFG.PADDING;
      this.getMenuElement(5).setPosX(tempX);
      tempX = tempX - this.getMenuElement(6).getWidth() - CFG.PADDING;
      this.getMenuElement(6).setPosX(tempX);
   }

   @Override
   public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      CFG.drawEditorTitle_Edge_R_Reflected(oSB, iTranslateX, this.getMenuPosY() + iTranslateY, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT + CFG.PADDING * 2);
      CFG.drawEditorButtons_Top_Edge_R_Reflected(
         oSB,
         this.getMenuElement(6).getPosX() - CFG.PADDING + iTranslateX,
         this.getMenuPosY() + CFG.BUTTON_HEIGHT + CFG.PADDING * 2 + iTranslateY,
         CFG.GAME_WIDTH - (this.getMenuElement(6).getPosX() - CFG.PADDING),
         CFG.BUTTON_HEIGHT + CFG.PADDING * 2
      );
      CFG.drawEditorButtons_Bot_Edge_R(
         oSB,
         iTranslateX,
         this.getMenuPosY() + this.getMenuElement(7).getPosY() - CFG.PADDING + iTranslateY,
         this.getMenuElement(8).getPosX() + this.getMenuElement(8).getWidth() + CFG.PADDING,
         this.getMenuElement(7).getHeight() + CFG.PADDING * 2
      );
      CFG.drawTextWithShadow(
         oSB,
         this.selectMapOfAvailableProvinces,
         CFG.GAME_WIDTH / 2 - this.iStepWidth / 2 + iTranslateX,
         CFG.PADDING + CFG.BUTTON_HEIGHT / 2 - CFG.TEXT_HEIGHT - CFG.PADDING / 2 + this.getMenuPosY() + iTranslateY,
         new Color(CFG.COLOR_TEXT_CNG_TOP_SCENARIO_NAME.r, CFG.COLOR_TEXT_CNG_TOP_SCENARIO_NAME.g, CFG.COLOR_TEXT_CNG_TOP_SCENARIO_NAME.b, 0.95F)
      );
      CFG.fontMain.getData().setScale(0.8F);
      CFG.drawTextWithShadow(
         oSB,
         this.selectMapOfAvailableProvinces2,
         CFG.GAME_WIDTH / 2 - (int)(this.iStepWidth2 * 0.8F / 2.0F) + iTranslateX,
         CFG.PADDING + CFG.BUTTON_HEIGHT / 2 + CFG.PADDING + this.getMenuPosY() + iTranslateY,
         new Color(CFG.COLOR_TEXT_CNG_TOP_SCENARIO_INFO.r, CFG.COLOR_TEXT_CNG_TOP_SCENARIO_INFO.g, CFG.COLOR_TEXT_CNG_TOP_SCENARIO_INFO.b, 0.75F)
      );
      CFG.fontMain.getData().setScale(1.0F);
      oSB.setColor(new Color(0.06F, 0.06F, 0.06F, 1.0F));
      CFG.fontMain.getData().setScale(0.9F);
      ImageManager.getImage(Images.civ_name_bg)
         .draw2(
            oSB,
            CFG.GAME_WIDTH
               - (CFG.PADDING * 2 + CFG.CIV_NAME_BG_EXTRA_WIDTH + (int)(this.iPlayableProvincesWidth * 0.9F) + CFG.iNumOfAvailableProvincesWidth)
               + iTranslateX,
            CFG.GAME_HEIGHT
               - CFG.map.getMapBG().getMinimapHeight()
               - CFG.PADDING * 2
               - CFG.TEXT_HEIGHT * 2
               - CFG.CIV_NAME_BG_EXTRA_HEIGHT * 4
               - ImageManager.getImage(Images.civ_name_bg).getHeight()
               + iTranslateY,
            CFG.PADDING * 2 + CFG.CIV_NAME_BG_EXTRA_WIDTH + (int)(this.iPlayableProvincesWidth * 0.9F) + CFG.iNumOfAvailableProvincesWidth,
            CFG.TEXT_HEIGHT + CFG.CIV_NAME_BG_EXTRA_HEIGHT * 2 - ImageManager.getImage(Images.civ_name_bg).getHeight()
         );
      ImageManager.getImage(Images.civ_name_bg)
         .draw(
            oSB,
            CFG.GAME_WIDTH
               - (CFG.PADDING * 2 + CFG.CIV_NAME_BG_EXTRA_WIDTH + (int)(this.iPlayableProvincesWidth * 0.9F) + CFG.iNumOfAvailableProvincesWidth)
               + iTranslateX,
            CFG.GAME_HEIGHT
               - CFG.map.getMapBG().getMinimapHeight()
               - CFG.PADDING * 2
               - CFG.TEXT_HEIGHT
               - CFG.CIV_NAME_BG_EXTRA_HEIGHT * 2
               - ImageManager.getImage(Images.civ_name_bg).getHeight()
               + iTranslateY,
            CFG.PADDING * 2 + CFG.CIV_NAME_BG_EXTRA_WIDTH + (int)(this.iPlayableProvincesWidth * 0.9F) + CFG.iNumOfAvailableProvincesWidth,
            false,
            true
         );
      CFG.drawTextWithShadow(
         oSB,
         this.sPlayableProvinces + ": " + CFG.iNumOfAvailableProvinces,
         CFG.GAME_WIDTH - (int)(this.iPlayableProvincesWidth * 0.9F) - CFG.iNumOfAvailableProvincesWidth - CFG.PADDING + iTranslateX,
         CFG.GAME_HEIGHT - CFG.map.getMapBG().getMinimapHeight() - CFG.PADDING * 2 - CFG.TEXT_HEIGHT * 2 - CFG.CIV_NAME_BG_EXTRA_HEIGHT * 3 + iTranslateY,
         new Color(CFG.COLOR_TEXT_CNG_TOP_SCENARIO_NAME.r, CFG.COLOR_TEXT_CNG_TOP_SCENARIO_NAME.g, CFG.COLOR_TEXT_CNG_TOP_SCENARIO_NAME.b, 0.95F)
      );
      ImageManager.getImage(Images.civ_name_bg)
         .draw2(
            oSB,
            CFG.GAME_WIDTH
               - (CFG.PADDING * 2 + CFG.CIV_NAME_BG_EXTRA_WIDTH + (int)(this.iWastelandProvincesWidth * 0.9F) + CFG.iNumOfWastelandProvincesWidth)
               + iTranslateX,
            CFG.GAME_HEIGHT
               - CFG.map.getMapBG().getMinimapHeight()
               - CFG.PADDING
               - CFG.TEXT_HEIGHT
               - CFG.CIV_NAME_BG_EXTRA_HEIGHT * 2
               - ImageManager.getImage(Images.civ_name_bg).getHeight()
               + iTranslateY,
            CFG.PADDING * 2 + CFG.CIV_NAME_BG_EXTRA_WIDTH + (int)(this.iWastelandProvincesWidth * 0.9F) + CFG.iNumOfWastelandProvincesWidth,
            CFG.TEXT_HEIGHT + CFG.CIV_NAME_BG_EXTRA_HEIGHT * 2 - ImageManager.getImage(Images.civ_name_bg).getHeight()
         );
      ImageManager.getImage(Images.civ_name_bg)
         .draw(
            oSB,
            CFG.GAME_WIDTH
               - (CFG.PADDING * 2 + CFG.CIV_NAME_BG_EXTRA_WIDTH + (int)(this.iWastelandProvincesWidth * 0.9F) + CFG.iNumOfWastelandProvincesWidth)
               + iTranslateX,
            CFG.GAME_HEIGHT - CFG.map.getMapBG().getMinimapHeight() - CFG.PADDING - ImageManager.getImage(Images.civ_name_bg).getHeight() + iTranslateY,
            CFG.PADDING * 2 + CFG.CIV_NAME_BG_EXTRA_WIDTH + (int)(this.iWastelandProvincesWidth * 0.9F) + CFG.iNumOfWastelandProvincesWidth,
            false,
            true
         );
      CFG.drawTextWithShadow(
         oSB,
         this.sWastelandProvinces + ": " + CFG.iNumOfWastelandProvinces,
         CFG.GAME_WIDTH - (int)(this.iWastelandProvincesWidth * 0.9F) - CFG.iNumOfWastelandProvincesWidth - CFG.PADDING + iTranslateX,
         CFG.GAME_HEIGHT - CFG.map.getMapBG().getMinimapHeight() - CFG.PADDING - CFG.TEXT_HEIGHT - CFG.CIV_NAME_BG_EXTRA_HEIGHT + iTranslateY,
         new Color(CFG.COLOR_TEXT_CNG_TOP_SCENARIO_NAME.r, CFG.COLOR_TEXT_CNG_TOP_SCENARIO_NAME.g, CFG.COLOR_TEXT_CNG_TOP_SCENARIO_NAME.b, 0.95F)
      );
      CFG.fontMain.getData().setScale(1.0F);
      oSB.setColor(Color.WHITE);
      super.draw(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
   }

   @Override
   public final void actionElement(int iID) {
      switch (iID) {
         case 1:
            int nPlayableProvinces = 0;
            int i = 0;

            for (; i < CFG.game.getProvincesSize(); i++) {
               if (!CFG.game.getProvince(i).getSeaProvince() && CFG.game.getProvince(i).getWasteland() < 0) {
                  nPlayableProvinces++;
               }
            }

            if (nPlayableProvinces < 2) {
               CFG.toast.setInView(CFG.langManager.get("Error") + " - " + CFG.langManager.get("PlayableProvinces") + ": " + nPlayableProvinces);
            } else {
               CFG.brushTool = false;
               CFG.menuManager.setViewID(CFG.goToMenu);
               if (CFG.goToMenu != Menu.eCREATE_RANDOM_GAME) {
                  for (int var6 = 0; var6 < CFG.game.getProvincesSize(); var6++) {
                     if (!CFG.game.getProvince(var6).getSeaProvince()
                        && CFG.game.getProvince(var6).getWasteland() >= 0
                        && CFG.game.getProvince(var6).getCivID() > 0) {
                        CFG.game.getProvince(var6).setCivID(0, false, false);
                     }
                  }

                  for (int var7 = 1; var7 < CFG.game.getCivsSize(); var7++) {
                     if (CFG.game.getProvince(CFG.game.getCiv(var7).getCapitalProvinceID()).getWasteland() >= 0) {
                        boolean foundAnotherCapital = false;

                        for (int j = 0; j < CFG.game.getCiv(var7).getNumOfProvinces(); j++) {
                           if (CFG.game.getProvince(CFG.game.getCiv(var7).getProvinceID(j)).getWasteland() < 0) {
                              CFG.game.getCiv(var7).setCapitalProvinceID(CFG.game.getCiv(var7).getProvinceID(j));
                              foundAnotherCapital = true;
                              break;
                           }
                        }

                        if (!foundAnotherCapital) {
                           CFG.game.createScenarioRemoveCivilization(var7);
                        }
                     }
                  }

                  CFG.game.buildWastelandLevels();
                  CFG.updateCreateScenario_Civilizations();
                  CFG.map.getMapBG().disposeMinimapOfCivilizations();
               } else {
                  CFG.game.buildWastelandLevels();
               }
            }

            return;
         case 2:
            CFG.map
               .getMapCoordinates()
               .centerToMinimapClick(
                  Touch.getMousePosX() - this.getMenuElement(iID).getPosX() - this.getPosX(),
                  Touch.getMousePosY() - this.getMenuElement(iID).getPosY() - this.getMenuPosY()
               );
         case 3:
         default:
            super.actionElement(iID);
            return;
         case 4:
            CFG.bSetWasteland_AvailableProvinces = !CFG.bSetWasteland_AvailableProvinces;
            this.getMenuElement(iID).setCheckboxState(CFG.bSetWasteland_AvailableProvinces);
            return;
         case 5:
            CFG.brushTool = !CFG.brushTool;
            this.getMenuElement(iID).setCheckboxState(CFG.brushTool);
            this.getMenuElement(3).setVisible(CFG.brushTool);
            return;
         case 6:
            if (CFG.lCreateScenario_UndoWastelandProvinces.size() > 0) {
               CFG.game
                  .getProvince(CFG.lCreateScenario_UndoWastelandProvinces.get(CFG.lCreateScenario_UndoWastelandProvinces.size() - 1))
                  .setWasteland(
                     CFG.game.getProvince(CFG.lCreateScenario_UndoWastelandProvinces.get(CFG.lCreateScenario_UndoWastelandProvinces.size() - 1)).getWasteland()
                           >= 0
                        ? -1
                        : 0
                  );
               CFG.game.setActiveProvinceID(CFG.lCreateScenario_UndoWastelandProvinces.get(CFG.lCreateScenario_UndoWastelandProvinces.size() - 1));
               if (!CFG.game.getProvince(CFG.game.getActiveProvinceID()).getDrawProvince()) {
                  CFG.map.getMapCoordinates().centerToProvinceID(CFG.game.getActiveProvinceID());
               }

               CFG.removeUndoWastelandProvince();
               CFG.updateNumOfAvailableProvinces();
            }

            return;
         case 7:
            Game_Calendar.ENABLE_COLONIZATION = !Game_Calendar.ENABLE_COLONIZATION;
            if (Game_Calendar.ENABLE_COLONIZATION) {
               CFG.toast.setInView(CFG.langManager.get("Colonization") + " - " + CFG.langManager.get("Enabled"));
            } else {
               CFG.toast.setInView(CFG.langManager.get("Colonization") + " - " + CFG.langManager.get("Disabled"));
            }

            return;
         case 8:
            CFG.setDialogType(Dialog.REVERSE_WASTELAND);
      }
   }

   @Override
   public void onBackPressed() {
      CFG.game.setActiveProvinceID(-1);
      CFG.brushTool = false;
      CFG.menuManager.setViewID(CFG.backToMenu);
   }
}
