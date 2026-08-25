package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

public class Menu_InGame_ProvincePopulation extends SliderMenu {
   public Menu_InGame_ProvincePopulation() {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      ArrayList<Integer> nData = new ArrayList<>();
      ArrayList<Integer> nCivs = new ArrayList<>();

      for (int i = 1; i < 5; i++) {
         nData.add(CFG.game.getCiv(i).getNumOfProvinces());
         nCivs.add(i);
      }

      menuElements.add(new Graph_Circle(CFG.PADDING + 2, CFG.PADDING + 2, nData, nCivs, null));
      this.initMenu(
         null,
         CFG.GAME_WIDTH - CFG.graphCircleDraw.getWidth() - 2 - CFG.PADDING * 2,
         CFG.GAME_HEIGHT - 2 - CFG.map.getMapBG().getMinimapHeight() - CFG.graphCircleDraw.getWidth() - CFG.PADDING * 2,
         CFG.graphCircleDraw.getWidth() + CFG.PADDING * 2 + 2,
         CFG.graphCircleDraw.getWidth() + CFG.PADDING * 2 + 2,
         menuElements,
         true,
         false
      );
      this.updateLanguage();
   }

   @Override
   public void updateLanguage() {
   }

   @Override
   public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      oSB.setColor(Color.WHITE);
      ImageManager.getImage(Images.bg_game_action)
         .draw2(
            oSB,
            this.getPosX() + iTranslateX,
            -ImageManager.getImage(Images.bg_game_action).getHeight() + this.getMenuPosY() + iTranslateY,
            this.getWidth(),
            this.getHeight(),
            false,
            false
         );
      super.draw(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
   }

   @Override
   public final void actionElement(int iID) {
      switch (iID) {
      }
   }

   @Override
   public int getPosX() {
      return CFG.GAME_WIDTH - this.getMenuElement(0).getWidth() - CFG.PADDING * 2 - 2;
   }

   @Override
   public int getMenuPosX() {
      return CFG.GAME_WIDTH - this.getMenuElement(0).getWidth() - CFG.PADDING * 2 - 2;
   }

   @Override
   public int getWidth() {
      return this.getMenuElement(0).getWidth() + CFG.PADDING * 2 + 2;
   }
}
