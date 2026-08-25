package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Button_New_Game_Players_Special extends Button_New_Game_Players {
   public Button_New_Game_Players_Special(String sText, int iTextPositionX, int iPosX, int iPosY, int iWidth, boolean isClickable) {
      super(sText, iTextPositionX, iPosX, iPosY, iWidth, isClickable);
   }

   public Button_New_Game_Players_Special(String sText, int iTextPositionX, int iPosX, int iPosY, int iWidth, int iHeight, boolean isClickable) {
      super(sText, iTextPositionX, iPosX, iPosY, iWidth, iHeight, isClickable);
   }

   @Override
   public void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
      if (isActive) {
         CFG.fontMain.getData().setScale(0.8F);
         CFG.drawText(
            oSB,
            this.getTextToDraw(),
            this.getPosX() + (this.getTextPos() < 0 ? this.getWidth() / 2 - (int)(this.getTextWidth() * 0.8F / 2.0F) : this.getTextPos()) + iTranslateX,
            this.getPosY() + this.getHeight() / 2 - (int)(CFG.TEXT_HEIGHT * 0.8F / 2.0F) + iTranslateY,
            this.getColor(isActive)
         );
         CFG.fontMain.getData().setScale(1.0F);
      } else {
         CFG.fontMain.getData().setScale(0.8F);
         CFG.drawText(
            oSB,
            this.getTextToDraw(),
            this.getPosX() + (this.getTextPos() < 0 ? this.getWidth() / 2 - (int)(this.getTextWidth() * 0.8F / 2.0F) : this.getTextPos()) + iTranslateX,
            this.getPosY() + this.getHeight() / 2 - (int)(CFG.TEXT_HEIGHT * 0.8F / 2.0F) + iTranslateY,
            this.getColor(isActive)
         );
         CFG.fontMain.getData().setScale(1.0F);
      }
   }
}
