package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Text_Scale extends Text {
   public float fScale = 1.0F;

   public Text_Scale(String sText, int iPosX, int iPosY, float nScale) {
      super(sText, iPosX, iPosY);
      this.fScale = nScale;
   }

   public Text_Scale(String sText, int iTextPositionX, int iPosX, int iPosY, float nScale) {
      super(sText, iTextPositionX, iPosX, iPosY, (int)(CFG.TEXT_HEIGHT * nScale));
      this.fScale = nScale;
   }

   public Text_Scale(String sText, int iTextPositionX, int iPosX, int iPosY, int iHeight, float nScale) {
      super(sText, iTextPositionX, iPosX, iPosY, iHeight);
      this.fScale = nScale;
   }

   public Text_Scale(String sText, int iTextPositionX, int iPosX, int iPosY, int iWidth, int iHeight, float nScale) {
      super(sText, iTextPositionX, iPosX, iPosY, iWidth, iHeight);
      this.fScale = nScale;
   }

   @Override
   public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
      CFG.fontMain.getData().setScale(this.fScale);
      CFG.drawTextWithShadow(
         oSB,
         this.sText,
         this.getPosX() + (int)(this.iTextPositionX != 0 ? (this.getWidth() - this.getTextWidth() * this.fScale) / 2.0F : 0.0F) + iTranslateX,
         this.getPosY() + (int)((this.getHeight() - this.iTextHeight * this.fScale) / 2.0F) + iTranslateY,
         this.getColor(isActive)
      );
      CFG.fontMain.getData().setScale(1.0F);
   }
}
