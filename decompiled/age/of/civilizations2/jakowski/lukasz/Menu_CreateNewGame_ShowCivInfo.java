package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

public class Menu_CreateNewGame_ShowCivInfo extends SliderMenu {
   public Menu_CreateNewGame_ShowCivInfo() {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      menuElements.add(new Button_ShowMenu(0, 0, CFG.BUTTON_WIDTH * 3 / 5, CFG.BUTTON_WIDTH * 3 / 5, true));
      this.initMenu(
         null,
         CFG.GAME_WIDTH - CFG.BUTTON_WIDTH * 3 / 5,
         ImageManager.getImage(Images.new_game_top).getHeight() + CFG.PADDING * 4 + (int)(CFG.TEXT_HEIGHT * 0.6F),
         CFG.BUTTON_WIDTH * 3 / 5,
         CFG.BUTTON_WIDTH * 3 / 5 + 1,
         menuElements,
         false,
         false
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
            CFG.menuManager.setVisible_CreateNewGame_CivInfo(true);
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
