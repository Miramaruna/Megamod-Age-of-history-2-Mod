package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.utils.ScissorStack;

public class Button_Statistics_Color extends Button_Statistics {
   public Color oColor;

   public Button_Statistics_Color(Color oColor, String sText, int iTextPosX, int iPosX, int iPosY, int iWidth, int iHeight) {
      super(sText, iTextPosX, iPosX, iPosY, iWidth, iHeight);
      this.oColor = oColor;
   }

   @Override
   public void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
      Rectangle clipBounds = new Rectangle(this.getPosX() + iTranslateX, CFG.GAME_HEIGHT - this.getPosY() - iTranslateY, this.getWidth(), -this.getHeight());
      oSB.flush();
      ScissorStack.pushScissors(clipBounds);
      oSB.setColor(this.oColor);
      ImageManager.getImage(Images.pix255_255_255)
         .draw(
            oSB,
            this.getPosX() + this.getTextPos() + iTranslateX,
            this.getPosY()
               + this.getHeight() / 2
               - (int)(this.iTextHeight * 0.7F / 2.0F)
               - ImageManager.getImage(Images.pix255_255_255).getHeight()
               + iTranslateY,
            2,
            (int)(this.iTextHeight * 0.7F)
         );
      oSB.setColor(Color.WHITE);
      super.drawText(oSB, 2 + CFG.PADDING + iTranslateX, iTranslateY, isActive);

      try {
         oSB.flush();
         ScissorStack.popScissors();
      } catch (IllegalStateException var7) {
      }
   }
}
