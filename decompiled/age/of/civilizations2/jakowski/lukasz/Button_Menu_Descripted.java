package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Button_Menu_Descripted extends Button_Menu {
   public String sDesc;

   public Button_Menu_Descripted(String sDesc, String sText, int iTextPositionX, int iPosX, int iPosY, int iWidth, int iHeight, boolean isClickable) {
      super(sText, iTextPositionX, iPosX, iPosY, iWidth, iHeight, isClickable);
      this.sDesc = sDesc;
   }

   public Button_Menu_Descripted(
      String sDesc, String sText, int iTextPositionX, int iPosX, int iPosY, int iWidth, int iHeight, boolean isClickable, boolean checkboxState
   ) {
      super(sText, iTextPositionX, iPosX, iPosY, iWidth, iHeight, isClickable, checkboxState);
      this.sDesc = sDesc;
   }

   @Override
   public void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
      CFG.fontMain.getData().setScale(0.9F);
      CFG.drawText(
         oSB,
         this.getText(),
         this.getPosX() + this.getTextPos() + iTranslateX,
         this.getPosY() + this.getHeight() / 2 - (int)(CFG.TEXT_HEIGHT * 0.9F + CFG.PADDING + CFG.TEXT_HEIGHT * 0.7F) / 2 + iTranslateY,
         this.getIsHovered() ? CFG.COLOR_TEXT_CNG_TOP_SCENARIO_NAME_HOVER : CFG.COLOR_TEXT_CNG_TOP_SCENARIO_NAME
      );
      CFG.fontMain.getData().setScale(0.7F);
      CFG.drawText(
         oSB,
         this.sDesc,
         this.getPosX() + this.getTextPos() + iTranslateX,
         this.getPosY()
            + this.getHeight() / 2
            - (int)(CFG.TEXT_HEIGHT * 0.9F + CFG.PADDING + CFG.TEXT_HEIGHT * 0.7F) / 2
            + CFG.PADDING
            + (int)(CFG.TEXT_HEIGHT * 0.9F)
            + iTranslateY,
         new Color(0.58F, 0.58F, 0.58F, 1.0F)
      );
      CFG.fontMain.getData().setScale(1.0F);
   }
}
