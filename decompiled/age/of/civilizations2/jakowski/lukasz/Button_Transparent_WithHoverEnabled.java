package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Button_Transparent_WithHoverEnabled extends Button {
   public Button_Transparent_WithHoverEnabled(int iPosX, int iPosY, int iWidth, int iHeight, boolean isClickable) {
      super.init("", 0, iPosX, iPosY, iWidth, iHeight, isClickable, true, false, false, null);
   }

   public Button_Transparent_WithHoverEnabled(int iTextPos, int iPosX, int iPosY, int iWidth, int iHeight, boolean isClickable) {
      super.init("", iTextPos, iPosX, iPosY, iWidth, iHeight, isClickable, true, false, false, null);
   }

   @Override
   public void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
   }

   @Override
   public void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
   }
}
