package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Button_Statistics_Gold extends Button_Statistics {
   public String sGold;

   public Button_Statistics_Gold(String sText, String sGold, int iTextPosX, int iPosX, int iPosY, int iWidth, int iHeight) {
      super(sText, iTextPosX, iPosX, iPosY, iWidth, iHeight);
      this.sGold = sGold;
   }

   @Override
   public void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
      CFG.fontMain.getData().setScale(0.7F);
      CFG.drawTextWithShadow(
         oSB,
         this.getTextToDraw(),
         this.getPosX() + this.textPosition.getTextPosition() + iTranslateX,
         this.getPosY() + this.getHeight() / 2 - (int)(this.iTextHeight * 0.7F / 2.0F) + iTranslateY,
         this.getColor(isActive)
      );
      CFG.drawTextWithShadow(
         oSB,
         this.sGold,
         this.getPosX() + this.textPosition.getTextPosition() + (int)(this.getTextWidth() * 0.7F) + iTranslateX,
         this.getPosY() + this.getHeight() / 2 - (int)(this.iTextHeight * 0.7F / 2.0F) + iTranslateY,
         CFG.COLOR_INGAME_GOLD
      );
      CFG.fontMain.getData().setScale(1.0F);
   }
}
