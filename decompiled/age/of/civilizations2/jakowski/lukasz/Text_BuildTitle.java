package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Text_BuildTitle extends Text_EconomyTitle {
   public static final float FONT_SIZE = 0.7F;

   public Text_BuildTitle(String sText, int iTextPositionX, int iPosX, int iPosY, int iWidth, int iHeight) {
      super(sText, iTextPositionX, iPosX, iPosY, iWidth, iHeight);
   }

   @Override
   public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
      CFG.drawRect_InfoBox_Left_Title(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight());
      oSB.setColor(Color.WHITE);
      CFG.fontMain.getData().setScale(0.7F);
      CFG.drawTextWithShadow(
         oSB,
         this.sText,
         this.getPosX() + (int)((this.getWidth() - this.getTextWidth() * 0.7F) / 2.0F) + iTranslateX,
         this.getPosY() + (int)((this.getHeight() - this.iTextHeight * 0.7F) / 2.0F) + iTranslateY,
         this.getColor(isActive)
      );
      CFG.fontMain.getData().setScale(1.0F);
   }

   @Override
   public Color getColor(boolean isActive) {
      return isActive
         ? CFG.COLOR_TEXT_OPTIONS_NS_HOVER
         : (this.getClickable() ? (this.getIsHovered() ? CFG.COLOR_TEXT_OPTIONS_NS : Color.WHITE) : new Color(0.78F, 0.78F, 0.78F, 0.7F));
   }
}
