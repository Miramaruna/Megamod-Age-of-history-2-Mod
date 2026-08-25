package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MenuElement_Hover_v2_Element_Type_Space implements MenuElement_Hover_v2_Element_Type {
   public static final String sText = "-----";
   public int iTextWidth;

   public MenuElement_Hover_v2_Element_Type_Space() {
      CFG.glyphLayout.setText(CFG.fontMain, "-----");
      this.iTextWidth = (int)(CFG.glyphLayout.width * 0.75F);
   }

   @Override
   public void draw(SpriteBatch oSB, int nPosX, int nPosY, float nAlpha) {
      CFG.drawText(oSB, "-----", nPosX, nPosY + CFG.PADDING + (int)((CFG.TEXT_HEIGHT - CFG.TEXT_HEIGHT * 0.75F) / 2.0F), new Color(0.85F, 0.85F, 0.85F, nAlpha));
   }

   @Override
   public int getWidth() {
      return this.iTextWidth;
   }
}
