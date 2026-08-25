package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

public class Menu_PeaceTreaty_Response_Demands_Show extends SliderMenu {
   protected Menu_PeaceTreaty_Response_Demands_Show() {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      menuElements.add(new Button_ShowMenu(0, 0, CFG.BUTTON_WIDTH * 3 / 5, CFG.BUTTON_WIDTH * 3 / 5, true));
      int tempPosY = Math.max(
         Math.max(CFG.BUTTON_HEIGHT * 4 / 5, Math.max(CFG.CIV_FLAG_HEIGHT + CFG.PADDING * 4, (CFG.TEXT_HEIGHT + CFG.PADDING) * 2 + CFG.PADDING))
            + CFG.PADDING * 2,
         CFG.BUTTON_HEIGHT + CFG.PADDING
      );
      this.initMenu(
         null, CFG.GAME_WIDTH - CFG.BUTTON_WIDTH * 3 / 5, tempPosY, CFG.BUTTON_WIDTH * 3 / 5, CFG.BUTTON_WIDTH * 3 / 5 + 1, menuElements, false, false
      );
      this.updateLanguage();
   }

   @Override
   public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      if (Menu_Civilization_Info.lTime + 250L >= System.currentTimeMillis()) {
         iTranslateX += this.getWidth() - (int)(this.getWidth() * ((float)(System.currentTimeMillis() - Menu_Civilization_Info.lTime) / 250.0F));
         CFG.setRender_3(true);
      }

      super.draw(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
   }

   @Override
   public void actionElement(int iID) {
      switch (iID) {
         case 0:
            CFG.menuManager.setVisible_InGamePeaceTreaty_ResponseProvinces(true);
      }
   }

   @Override
   public void setVisible(boolean visible) {
      if (visible && !this.getVisible()) {
         Menu_Civilization_Info.lTime = System.currentTimeMillis();
      }

      super.setVisible(visible);
   }
}
