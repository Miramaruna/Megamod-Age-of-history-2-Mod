package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;
import java.util.List;

public class TextSlider_Line {
   public List<String> lText = new ArrayList<>();
   public int iHeight;
   public TextSlider_Line.Align align;

   public TextSlider_Line(String sText, int nWidth, int extraHeight, TextSlider_Line.Align nAlign, float nFONT_SCALE) {
      this.align = nAlign;
      String[] tempLine = sText.split(" ");
      int currentW = 0;
      int iSize = tempLine.length;
      int last = 0;

      for (int i = 0; i < iSize; i++) {
         CFG.glyphLayout.setText(CFG.fontMain, tempLine[i] + " ");
         if ((currentW += (int)(CFG.glyphLayout.width * nFONT_SCALE)) >= nWidth || i == iSize - 1 && currentW < nWidth) {
            String addLine = "";

            for (int j = last; j < (i == iSize - 1 && currentW < nWidth ? iSize : i); j++) {
               addLine = addLine + tempLine[j] + " ";
            }

            this.lText.add(addLine);
            last = i;
            if (currentW >= nWidth && i == iSize - 1) {
               this.lText.add(tempLine[i]);
            }

            currentW = (int)(CFG.glyphLayout.width * nFONT_SCALE);
         }
      }

      this.iHeight = (int)(this.lText.size() * (CFG.TEXT_HEIGHT * nFONT_SCALE + CFG.PADDING) + extraHeight);
   }

   public void draw(SpriteBatch oSB, int nPosX, int nPosY, int nWidth, Color oColor) {
      int iSize = this.lText.size();

      for (int i = 0; i < iSize; i++) {
         CFG.drawText(oSB, this.lText.get(i), nPosX, nPosY + (CFG.TEXT_HEIGHT + CFG.PADDING) * i, oColor);
      }
   }

   public final void setHeight(int iHeight) {
      this.iHeight = iHeight;
   }

   public final int getHeight() {
      return this.iHeight;
   }

   public static enum Align {
      LEFT,
      CENTER,
      RIGHT;
   }
}
