package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Slider_BG extends Slider {
   public Slider_BG(int iPosX, int iPosY, int iWidth, int iHeight, int iMin, int iMax, int iCurrent) {
      this.initSlider("", iPosX, iPosY, iWidth, iHeight, iMin, iMax, iCurrent);
   }

   public Slider_BG(String sText, int iPosX, int iPosY, int iWidth, int iHeight, int iMin, int iMax, int iCurrent) {
      this.initSlider(sText, iPosX, iPosY, iWidth, iHeight, iMin, iMax, iCurrent);
   }

   @Override
   public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
      ImageManager.getImage(Images.btn_menu_h).draw(oSB, iTranslateX, this.getPosY() - CFG.PADDING + iTranslateY, CFG.GAME_WIDTH);
      super.draw(oSB, iTranslateX, iTranslateY, isActive, scrollableY);
   }
}
