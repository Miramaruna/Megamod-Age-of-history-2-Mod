package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MenuElement_Hover_v2_Element_Type_Ideology implements MenuElement_Hover_v2_Element_Type {
   public int iIdeologyID;
   public int offsetLeft = 0;
   public int offsetRight = 0;

   public MenuElement_Hover_v2_Element_Type_Ideology(int iIdeologyID) {
      this.iIdeologyID = iIdeologyID;
      this.offsetLeft = 0;
      this.offsetRight = CFG.PADDING;
   }

   public MenuElement_Hover_v2_Element_Type_Ideology(int iIdeologyID, int offsetLeft) {
      this.iIdeologyID = iIdeologyID;
      this.offsetLeft = offsetLeft;
      this.offsetRight = CFG.PADDING;
   }

   public MenuElement_Hover_v2_Element_Type_Ideology(int iIdeologyID, int offsetLeft, int offsetRight) {
      this.iIdeologyID = iIdeologyID;
      this.offsetLeft = offsetLeft;
      this.offsetRight = offsetRight;
   }

   @Override
   public void draw(SpriteBatch oSB, int nPosX, int nPosY, float nAlpha) {
      oSB.setColor(new Color(1.0F, 1.0F, 1.0F, nAlpha));
      CFG.ideologiesManager
         .getIdeology(this.iIdeologyID)
         .getCrownImageScaled()
         .draw(
            oSB,
            nPosX + this.offsetLeft,
            nPosY
               + CFG.PADDING
               - CFG.ideologiesManager.getIdeology(this.iIdeologyID).getCrownImageScaled().getHeight()
               + CFG.TEXT_HEIGHT / 2
               - (int)(CFG.ideologiesManager.getIdeology(this.iIdeologyID).getCrownImageScaled().getHeight() * this.getImageScale() / 2.0F),
            (int)(CFG.ideologiesManager.getIdeology(this.iIdeologyID).getCrownImageScaled().getWidth() * this.getImageScale()),
            (int)(CFG.ideologiesManager.getIdeology(this.iIdeologyID).getCrownImageScaled().getHeight() * this.getImageScale())
         );
   }

   @Override
   public int getWidth() {
      return this.offsetRight
         + this.offsetLeft
         + (int)(CFG.ideologiesManager.getIdeology(this.iIdeologyID).getCrownImageScaled().getWidth() * this.getImageScale());
   }

   public final float getImageScale() {
      return (float)CFG.TEXT_HEIGHT / CFG.ideologiesManager.getIdeology(this.iIdeologyID).getCrownImageScaled().getHeight();
   }
}
