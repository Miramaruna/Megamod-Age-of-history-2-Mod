package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Button_ViewIcon extends Button {
   public Button_ViewIcon(int iPosX, int iPosY, boolean isClickable) {
      super.init("", -1, iPosX, iPosY, 25, 10, isClickable, true, false, false, null);
   }

   @Override
   public void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
   }

   @Override
   public void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
   }
}
