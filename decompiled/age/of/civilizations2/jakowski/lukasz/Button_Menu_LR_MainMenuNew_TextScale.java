package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Button_Menu_LR_MainMenuNew_TextScale extends Button_Menu_LR_MainMenu {
   public Button_Menu_LR_MainMenuNew_TextScale(String sText, int iTextPositionX, int iPosX, int iPosY, int iWidth, int iHeight, boolean isClickable) {
      super(sText, iTextPositionX, iPosX, iPosY, iWidth, iHeight, isClickable);
   }

   public Button_Menu_LR_MainMenuNew_TextScale(
      String sText, int iTextPositionX, int iPosX, int iPosY, int iWidth, int iHeight, boolean isClickable, boolean checkboxState
   ) {
      super(sText, iTextPositionX, iPosX, iPosY, iWidth, iHeight, isClickable, checkboxState);
   }

   @Override
   public void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
      CFG.fontMain.getData().setScale(1.0F);
      if (isActive) {
         super.freePlayButton = false;
         CFG.drawText(
            oSB,
            this.getTextToDraw(),
            this.getPosX() + this.getWidth() / 2 - (int)(this.getTextWidth() * 0.9F / 2.0F) + iTranslateX,
            this.getPosY() + this.getHeight() / 2 - (int)(this.iTextHeight * 0.9F / 2.0F) + iTranslateY,
            CFG.COLOR_INGAME_GOLD_ACTIVE
         );
      } else {
         super.freePlayButton = true;
         CFG.drawTextWithShadow(
            oSB,
            this.getTextToDraw(),
            this.getPosX() + this.getWidth() / 2 - (int)(this.getTextWidth() * 0.9F / 2.0F) + iTranslateX,
            this.getPosY() + this.getHeight() / 2 - (int)(this.iTextHeight * 0.9F / 2.0F) + iTranslateY,
            CFG.COLOR_INGAME_GOLD_ACTIVE
         );
      }

      CFG.fontMain.getData().setScale(1.3F);
   }
}
