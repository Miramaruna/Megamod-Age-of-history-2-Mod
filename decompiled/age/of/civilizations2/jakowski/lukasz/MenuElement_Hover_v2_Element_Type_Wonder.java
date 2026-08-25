package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MenuElement_Hover_v2_Element_Type_Wonder implements MenuElement_Hover_v2_Element_Type {
   public int iProvinceID;
   public int iWonderID;
   public int offsetLeft;
   public int offsetRight;

   public MenuElement_Hover_v2_Element_Type_Wonder(int nProvinceID) {
      this.iProvinceID = nProvinceID;
      this.offsetLeft = 0;
      this.offsetRight = CFG.PADDING;
   }

   public MenuElement_Hover_v2_Element_Type_Wonder(int nProvinceID, int offsetLeft) {
      this.iProvinceID = nProvinceID;
      this.offsetLeft = offsetLeft;
      this.offsetRight = CFG.PADDING;
   }

   public MenuElement_Hover_v2_Element_Type_Wonder(int nProvinceID, int iWonderID, int offsetLeft, int offsetRight) {
      this.iProvinceID = nProvinceID;
      this.iWonderID = iWonderID;
      this.offsetLeft = offsetLeft;
      this.offsetRight = offsetRight;
   }

   @Override
   public void draw(SpriteBatch oSB, int nPosX, int nPosY, float nAlpha) {
      try {
         oSB.setColor(new Color(1.0F, 1.0F, 1.0F, nAlpha));
         CFG.game
            .getProvince(this.iProvinceID)
            .getWonder(this.iWonderID)
            .nImage
            .draw(
               oSB,
               nPosX + this.offsetLeft,
               nPosY
                  + CFG.PADDING
                  - CFG.game.getProvince(this.iProvinceID).getWonder(this.iWonderID).nImage.getHeight()
                  + CFG.TEXT_HEIGHT / 2
                  - (int)(CFG.game.getProvince(this.iProvinceID).getWonder(this.iWonderID).nImage.getHeight() * this.getImageScale() / 2.0F),
               (int)(CFG.game.getProvince(this.iProvinceID).getWonder(this.iWonderID).nImage.getWidth() * this.getImageScale()),
               (int)(CFG.game.getProvince(this.iProvinceID).getWonder(this.iWonderID).nImage.getHeight() * this.getImageScale())
            );
      } catch (IndexOutOfBoundsException var6) {
      } catch (NullPointerException var7) {
      }
   }

   @Override
   public int getWidth() {
      try {
         return this.offsetLeft
            + this.offsetRight
            + (int)(CFG.game.getProvince(this.iProvinceID).getWonder(this.iWonderID).nImage.getWidth() * this.getImageScale());
      } catch (IndexOutOfBoundsException var2) {
         return this.offsetLeft + this.offsetRight;
      } catch (NullPointerException var3) {
         return this.offsetLeft + this.offsetRight;
      }
   }

   public final float getImageScale() {
      try {
         return CFG.TEXT_HEIGHT * 0.75F / CFG.game.getProvince(this.iProvinceID).getWonder(this.iWonderID).nImage.getHeight();
      } catch (IndexOutOfBoundsException var2) {
         return CFG.TEXT_HEIGHT * 0.75F;
      } catch (NullPointerException var3) {
         return CFG.TEXT_HEIGHT * 0.75F;
      }
   }
}
