package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MenuElement_Hover_v2_Element_Type_Text implements MenuElement_Hover_v2_Element_Type {
   public String sText;
   public int iTextWidth;
   public Color oColor;

   public MenuElement_Hover_v2_Element_Type_Text(String sText) {
      this.init(sText, new Color(0.9843137F, 0.9843137F, 0.9843137F, 1.0F));
   }

   public MenuElement_Hover_v2_Element_Type_Text(String sText, Color nColor) {
      this.init(sText, nColor);
   }

   public final void init(String sText, Color oColor) {
      this.sText = sText;
      this.oColor = oColor;
      CFG.glyphLayout.setText(CFG.fontMain, sText);
      this.iTextWidth = (int)(CFG.glyphLayout.width * 0.75F);
   }

   @Override
   public void draw(SpriteBatch oSB, int nPosX, int nPosY, float nAlpha) {
      CFG.drawText(
         oSB,
         this.sText,
         nPosX,
         nPosY + CFG.PADDING + (int)((CFG.TEXT_HEIGHT - CFG.TEXT_HEIGHT * 0.75F) / 2.0F),
         new Color(this.oColor.r, this.oColor.g, this.oColor.b, nAlpha)
      );
   }

   @Override
   public int getWidth() {
      return this.iTextWidth;
   }
}
