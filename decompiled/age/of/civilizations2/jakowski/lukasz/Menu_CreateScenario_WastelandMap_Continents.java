package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

public class Menu_CreateScenario_WastelandMap_Continents extends SliderMenu {
   public Menu_CreateScenario_WastelandMap_Continents() {
      ArrayList lSortedIDs = new ArrayList();
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      ArrayList<Integer> tempIDs = new ArrayList<>();

      for (int i = 0; i < CFG.map.getMapContinents().getContinentsSize(); i++) {
         if (i != CFG.map.getMapContinents().getOceanContinentID()) {
            menuElements.add(
               new Button_Game_Checkbox(null, -1, CFG.PADDING, CFG.PADDING, CFG.BUTTON_WIDTH, true, true) {
                  int iCurrent = 0;

                  @Override
                  public int getCurrent() {
                     return this.iCurrent;
                  }

                  @Override
                  public void setCurrent(int nCurrent) {
                     this.iCurrent = nCurrent;
                  }

                  @Override
                  public void buildElementHover() {
                     ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
                     ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(CFG.map.getMapContinents().getName(this.getCurrent()), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE)
                     );
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("NumberOfProvinces") + ": "));
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text("" + CFG.game.countContinentProvinces(this.getCurrent()), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE)
                     );
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                     this.menuElementHover = new MenuElement_Hover_v2(nElements);
                  }
               }
            );
            tempIDs.add(i);
         }
      }

      while (tempIDs.size() > 0) {
         int nMinID = 0;

         for (int i2 = 1; i2 < tempIDs.size(); i2++) {
            if (CFG.compareAlphabetic_TwoString(CFG.map.getMapContinents().getName(tempIDs.get(nMinID)), CFG.map.getMapContinents().getName(tempIDs.get(i2)))) {
               nMinID = i2;
            }
         }

         lSortedIDs.add(tempIDs.get(nMinID));
         tempIDs.remove(nMinID);
      }

      for (int var7 = 0; var7 < menuElements.size(); var7++) {
         menuElements.get(var7).setText(CFG.map.getMapContinents().getName((Integer)lSortedIDs.get(var7)));
         menuElements.get(var7).setCurrent((Integer)lSortedIDs.get(var7));
      }

      this.initMenu(
         null, 0, CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT * 2 - CFG.PADDING * 4, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT + CFG.PADDING * 2, menuElements, true, false
      );
      this.updateLanguage();
   }

   @Override
   public void updateLanguage() {
      this.updatedButtonsWidth(CFG.PADDING, CFG.BUTTON_WIDTH);
   }

   @Override
   public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      ImageManager.getImage(Images.bg_game_action)
         .draw2(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() - 1 - ImageManager.getImage(Images.bg_game_action).getHeight() + iTranslateY,
            this.getMenuElement(this.getMenuElementsSize() - 1).getPosX() + this.getMenuElement(this.getMenuElementsSize() - 1).getWidth() + CFG.PADDING + 1,
            this.getHeight() + 1,
            true,
            false
         );
      super.draw(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
   }

   @Override
   public final void actionElement(int iID) {
      int numOfWastelandProvinces = 0;
      int numOfNormalProvinces = 0;
      int chosenContinent = this.getMenuElement(iID).getCurrent();

      for (int i = 0; i < CFG.game.getProvincesSize(); i++) {
         if (CFG.game.getProvince(i).getContinent() == chosenContinent) {
            if (CFG.game.getProvince(i).getWasteland() >= 0) {
               numOfWastelandProvinces++;
            } else {
               numOfNormalProvinces++;
            }
         }
      }

      this.getMenuElement(iID).setCheckboxState(numOfWastelandProvinces > numOfNormalProvinces);

      for (int var6 = 0; var6 < CFG.game.getProvincesSize(); var6++) {
         if (CFG.game.getProvince(var6).getContinent() == chosenContinent) {
            CFG.game.getProvince(var6).setWasteland(numOfWastelandProvinces < numOfNormalProvinces ? 0 : -1);
         }
      }

      CFG.game.buildWastelandLevels();
      CFG.toast.setInView(this.getMenuElement(iID).getText());
   }
}
