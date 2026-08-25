package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

public class Menu_CreateScenario_WastelandMap extends Menu_CreateScenario {
   public String selectMapOfAvailableProvinces;
   public int iStepWidth;
   public String selectMapOfAvailableProvinces2;
   public int iStepWidth2;

   public Menu_CreateScenario_WastelandMap() {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      menuElements.add(new Button_Game(null, -1, CFG.PADDING, CFG.PADDING, true) {
         @Override
         public void buildElementHover() {
            ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
            ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("ExitScenarioEditor"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
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
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("CustomizeWasteland"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
            this.menuElementHover = new MenuElement_Hover_v2(nElements);
         }
      });
      menuElements.add(new Minimap(CFG.GAME_WIDTH - CFG.map.getMapBG().getMinimapWidth(), CFG.GAME_HEIGHT - CFG.map.getMapBG().getMinimapHeight()));
      this.initMenu(null, 0, 0, CFG.GAME_WIDTH, CFG.GAME_HEIGHT, menuElements);
      this.updateLanguage();
   }

   @Override
   public void updateLanguage() {
      super.updateLanguage();
      this.selectMapOfAvailableProvinces = CFG.langManager.get("SelectRegions");
      CFG.glyphLayout.setText(CFG.fontMain, this.selectMapOfAvailableProvinces);
      this.iStepWidth = (int)CFG.glyphLayout.width;
      this.selectMapOfAvailableProvinces2 = CFG.langManager.get("SetWhichRegionsOfTheWorldAreWasteland") + ".";
      CFG.glyphLayout.setText(CFG.fontMain, this.selectMapOfAvailableProvinces2);
      this.iStepWidth2 = (int)CFG.glyphLayout.width;
   }

   @Override
   public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      CFG.drawEditorTitle_Edge_LR(oSB, iTranslateX, iTranslateY, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT + CFG.PADDING * 2);
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
      oSB.setColor(CFG.COLOR_BG_GAME_MENU_SHADOW);
      ImageManager.getImage(Images.pix255_255_255)
         .draw(oSB, iTranslateX, CFG.GAME_HEIGHT - CFG.map.getMapBG().getMinimapHeight() - 1 + iTranslateY, CFG.GAME_WIDTH);
      oSB.setColor(Color.WHITE);
      super.draw(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
   }

   @Override
   public final void actionElement(int iID) {
      switch (iID) {
         case 1:
            if (CFG.game.getActiveProvinceID() >= 0 && !CFG.game.getProvince(CFG.game.getActiveProvinceID()).getSeaProvince()) {
               CFG.map.getMapScale().setCurrentScale(Map_Scale.STANDARD_SCALE);
               CFG.map.getMapCoordinates().centerToProvinceID(CFG.game.getActiveProvinceID());
            } else {
               CFG.game.setActiveProvinceID(-1);
               CFG.map.getMapCoordinates().centerToRandomMapPosition();
            }

            CFG.brushTool = false;
            CFG.updateNumOfAvailableProvinces();
            CFG.lCreateScenario_UndoWastelandProvinces.clear();
            CFG.backToMenu = Menu.eCREATE_SCENARIO_WASTELAND;
            CFG.goToMenu = Menu.eCREATE_SCENARIO_CIVILIZATIONS;
            CFG.menuManager.setViewID(Menu.eCREATE_SCENARIO_AVAILABLE_PROVINCES);
            CFG.map.getMapBG().disposeMinimapOfCivilizations();
            return;
         case 2:
            CFG.map
               .getMapCoordinates()
               .centerToMinimapClick(
                  Touch.getMousePosX() - this.getMenuElement(iID).getPosX() - this.getPosX(),
                  Touch.getMousePosY() - this.getMenuElement(iID).getPosY() - this.getMenuPosY()
               );
         default:
            super.actionElement(iID);
      }
   }
}
