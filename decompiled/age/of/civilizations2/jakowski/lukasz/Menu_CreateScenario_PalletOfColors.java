package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

public class Menu_CreateScenario_PalletOfColors extends Menu_CreateScenario {
   public String sTopText;
   public int iStepWidth;

   public Menu_CreateScenario_PalletOfColors() {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      menuElements.add(new Button_Game(null, -1, CFG.PADDING, CFG.PADDING, true));
      menuElements.add(new Button_Game(null, -1, CFG.GAME_WIDTH - CFG.BUTTON_WIDTH - CFG.PADDING, CFG.PADDING, true));
      menuElements.add(new Minimap(CFG.GAME_WIDTH - CFG.map.getMapBG().getMinimapWidth(), CFG.GAME_HEIGHT - CFG.map.getMapBG().getMinimapHeight()));
      this.initMenu(null, 0, 0, CFG.GAME_WIDTH, CFG.GAME_HEIGHT, menuElements);
      this.getMenuElement(2).setVisible(false);
      this.updateLanguage();
   }

   @Override
   public void updateLanguage() {
      this.sTopText = CFG.langManager.get("PalletCivColors");
      CFG.glyphLayout.setText(CFG.fontMain, this.sTopText);
      this.iStepWidth = (int)CFG.glyphLayout.width;
      super.updateLanguage();
      this.getMenuElement(1).setText(CFG.langManager.get("Save"));
   }

   @Override
   public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      CFG.drawEditorTitle_Edge_LR(oSB, iTranslateX, this.getMenuPosY() + iTranslateY, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT + CFG.PADDING * 2);
      CFG.drawTextWithShadow(
         oSB,
         this.sTopText,
         CFG.GAME_WIDTH / 2 - this.iStepWidth / 2 + iTranslateX,
         CFG.BUTTON_HEIGHT / 2 - CFG.TEXT_HEIGHT / 2 + CFG.PADDING + this.getMenuPosY() + iTranslateY,
         Color.WHITE
      );
      super.draw(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
   }

   @Override
   public final void actionElement(int iID) {
      switch (iID) {
         case 1:
            this.onBackPressed();
            return;
         default:
            super.actionElement(iID);
      }
   }

   @Override
   public void onBackPressed() {
      CFG.menuManager.setViewID(Menu.eCREATE_SCENARIO_SETTINGS);
   }
}
