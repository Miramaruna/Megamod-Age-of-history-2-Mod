package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Button_Menu_Active extends Button_Menu {
   public Button_Menu_Active(String sText, int iTextPositionX, int iPosX, int iPosY, int iWidth, int iHeight, boolean isClickable) {
      super(sText, iTextPositionX, iPosX, iPosY, iWidth, iHeight, isClickable);
   }

   @Override
   public void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
      if (isActive) {
         CFG.drawTextWithShadow(
            oSB,
            this.getTextToDraw(),
            this.getPosX() + (this.getTextPos() < 0 ? this.getWidth() / 2 - this.getTextWidth() / 2 : this.getTextPos()) + iTranslateX,
            this.getPosY() + this.getHeight() / 2 - this.getTextHeight() / 2 + iTranslateY,
            this.getColor(isActive)
         );
      } else {
         CFG.drawText(
            oSB,
            this.getTextToDraw(),
            this.getPosX() + (this.getTextPos() < 0 ? this.getWidth() / 2 - this.getTextWidth() / 2 : this.getTextPos()) + iTranslateX,
            this.getPosY() + this.getHeight() / 2 - this.getTextHeight() / 2 + iTranslateY,
            this.getColor(isActive)
         );
      }
   }

   @Override
   public void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
      if (isActive) {
         ImageManager.getImage(Images.btn_menu_1_h).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth());
      } else if (this.getIsHovered() && this.getClickable()) {
         oSB.setColor(CFG.COLOR_BUTTON_MENU_HOVER_BG);
         ImageManager.getImage(Images.btnh_menu_1_h).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth());
         oSB.setColor(Color.WHITE);
      } else {
         ImageManager.getImage(Images.btnh_menu_1_h).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth());
      }
   }

   @Override
   public final Color getColor(boolean isActive) {
      return isActive ? (this.getClickable() ? new Color(0.82F, 0.82F, 0.82F, 1.0F) : new Color(0.78F, 0.78F, 0.78F, 0.7F)) : new Color(0.1F, 0.1F, 0.1F, 1.0F);
   }
}
