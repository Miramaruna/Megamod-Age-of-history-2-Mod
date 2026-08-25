package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

public class Menu_CreateScenario_SetUpArmy_Options extends SliderMenu {
   public Menu_CreateScenario_SetUpArmy_Options() {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      menuElements.add(new Button_Game(null, -1, CFG.PADDING, CFG.PADDING, CFG.BUTTON_WIDTH * 2, true));
      menuElements.add(new Button_Game(null, -1, CFG.BUTTON_WIDTH * 2 + CFG.PADDING * 2, CFG.PADDING, CFG.BUTTON_WIDTH, true));
      menuElements.add(new Button_Game(null, -1, CFG.BUTTON_WIDTH * 3 + CFG.PADDING * 3, CFG.PADDING, CFG.BUTTON_WIDTH, true));
      menuElements.add(new Button_Game(null, -1, CFG.BUTTON_WIDTH * 4 + CFG.PADDING * 4, CFG.PADDING, CFG.BUTTON_WIDTH, true));
      this.initMenu(
         null,
         0,
         CFG.GAME_HEIGHT - CFG.map.getMapBG().getMinimapHeight(),
         CFG.GAME_WIDTH - CFG.map.getMapBG().getMinimapWidth(),
         CFG.map.getMapBG().getMinimapHeight(),
         menuElements
      );
      this.updateLanguage();
   }

   @Override
   public void updateLanguage() {
      this.getMenuElement(0).setText(CFG.langManager.get("AddArmy"));
      this.getMenuElement(1).setText(CFG.langManager.get("Max") + ": -10000");
      this.getMenuElement(2).setText(CFG.langManager.get("Max") + ": +10000");
      this.getMenuElement(3).setText(CFG.langManager.get("NeutralArmy"));
      this.updatedButtonsWidth(CFG.PADDING, CFG.BUTTON_WIDTH);
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
      oSB.setColor(CFG.COLOR_MINIMAP_BORDER);
      ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, 1, this.getHeight());
      oSB.setColor(Color.WHITE);
      super.draw(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
   }

   @Override
   public void actionElement(int iID) {
      switch (iID) {
         case 0:
            if (CFG.menuManager.getVisible_CreateScenario_SetUpArmies_Civs()) {
               CFG.menuManager.setVisible_CreateScenario_SetUpArmies_Civs(false);
            } else {
               CFG.menuManager.rebuildCreateScenario_SetUpArmies_Civs();
            }
         default:
            return;
         case 1:
            if ((CFG.MANAGE_DIPLOMACY_CUSTOMIZE_ALLIANCE_ID -= 10000) < 10000) {
               CFG.MANAGE_DIPLOMACY_CUSTOMIZE_ALLIANCE_ID = 10000;
            }

            ArrayList<String> tMess2 = new ArrayList<>();
            ArrayList<Color> tColor2 = new ArrayList<>();
            tMess2.add(CFG.langManager.get("Max"));
            tColor2.add(Color.WHITE);
            tMess2.add(CFG.getNumberWithSpaces("" + CFG.MANAGE_DIPLOMACY_CUSTOMIZE_ALLIANCE_ID));
            tColor2.add(CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE);
            CFG.toast.setInView(tMess2, tColor2);
            CFG.menuManager.rebuildCreateScenario_SetUpArmies_Sliders();
            return;
         case 2:
            if ((CFG.MANAGE_DIPLOMACY_CUSTOMIZE_ALLIANCE_ID += 10000) > 1000000) {
               CFG.MANAGE_DIPLOMACY_CUSTOMIZE_ALLIANCE_ID = 1000000;
            }

            ArrayList<String> tMess = new ArrayList<>();
            ArrayList<Color> tColor = new ArrayList<>();
            tMess.add(CFG.langManager.get("Max"));
            tColor.add(Color.WHITE);
            tMess.add(CFG.getNumberWithSpaces("" + CFG.MANAGE_DIPLOMACY_CUSTOMIZE_ALLIANCE_ID));
            tColor.add(CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE);
            CFG.toast.setInView(tMess, tColor);
            CFG.menuManager.rebuildCreateScenario_SetUpArmies_Sliders();
            return;
         case 3:
            if (CFG.menuManager.getVisible_CreateScenario_SetUpArmies_Neutral()) {
               CFG.menuManager.setVisible_CreateScenario_SetUpArmies_Neutral(false);
            } else {
               CFG.menuManager.rebuildCreateScenario_SetUpArmies_Neutral();
            }
      }
   }
}
