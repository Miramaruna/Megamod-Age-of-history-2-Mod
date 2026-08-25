package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MenuElement_Hover_v2_Element_Type_Flag implements MenuElement_Hover_v2_Element_Type {
   public int iCivID;
   public int offsetLeft = 0;
   public int offsetRight = 0;

   public MenuElement_Hover_v2_Element_Type_Flag(int iCivID) {
      this.iCivID = iCivID;
      this.offsetLeft = 0;
      this.offsetRight = CFG.PADDING;
   }

   public MenuElement_Hover_v2_Element_Type_Flag(int iCivID, int offsetLeft) {
      this.iCivID = iCivID;
      this.offsetLeft = offsetLeft;
      this.offsetRight = CFG.PADDING;
   }

   public MenuElement_Hover_v2_Element_Type_Flag(int iCivID, int offsetLeft, int offsetRight) {
      this.iCivID = iCivID;
      this.offsetLeft = offsetLeft;
      this.offsetRight = offsetRight;
   }

   @Override
   public void draw(SpriteBatch oSB, int nPosX, int nPosY, float nAlpha) {
      oSB.setColor(new Color(1.0F, 1.0F, 1.0F, nAlpha));

      try {
         if (this.iCivID >= 0) {
            CFG.game
               .getCiv(this.iCivID)
               .getFlag()
               .draw(
                  oSB,
                  nPosX + this.offsetLeft,
                  nPosY
                     + CFG.PADDING
                     - CFG.game.getCiv(this.iCivID).getFlag().getHeight()
                     + CFG.TEXT_HEIGHT / 2
                     - (int)(CFG.CIV_FLAG_HEIGHT * this.getImageScale() / 2.0F),
                  (int)(CFG.CIV_FLAG_WIDTH * this.getImageScale()),
                  (int)(CFG.CIV_FLAG_HEIGHT * this.getImageScale())
               );
         } else {
            ImageManager.getImage(Images.randomCivilizationFlag)
               .draw(
                  oSB,
                  nPosX + this.offsetLeft,
                  nPosY
                     + CFG.PADDING
                     - ImageManager.getImage(Images.randomCivilizationFlag).getHeight()
                     + CFG.TEXT_HEIGHT / 2
                     - (int)(CFG.CIV_FLAG_HEIGHT * this.getImageScale() / 2.0F),
                  (int)(CFG.CIV_FLAG_WIDTH * this.getImageScale()),
                  (int)(CFG.CIV_FLAG_HEIGHT * this.getImageScale())
               );
         }
      } catch (IndexOutOfBoundsException var6) {
         ImageManager.getImage(Images.randomCivilizationFlag)
            .draw(
               oSB,
               nPosX + this.offsetLeft,
               nPosY
                  + CFG.PADDING
                  - ImageManager.getImage(Images.randomCivilizationFlag).getHeight()
                  + CFG.TEXT_HEIGHT / 2
                  - (int)(CFG.CIV_FLAG_HEIGHT * this.getImageScale() / 2.0F),
               (int)(CFG.CIV_FLAG_WIDTH * this.getImageScale()),
               (int)(CFG.CIV_FLAG_HEIGHT * this.getImageScale())
            );
      }

      ImageManager.getImage(Images.flag_rect)
         .draw(
            oSB,
            nPosX + this.offsetLeft,
            nPosY
               + CFG.PADDING
               - ImageManager.getImage(Images.flag_rect).getHeight()
               + CFG.TEXT_HEIGHT / 2
               - (int)(CFG.CIV_FLAG_HEIGHT * this.getImageScale() / 2.0F),
            (int)(CFG.CIV_FLAG_WIDTH * this.getImageScale()),
            (int)(CFG.CIV_FLAG_HEIGHT * this.getImageScale())
         );
   }

   @Override
   public int getWidth() {
      return this.offsetRight + this.offsetLeft + (int)(CFG.CIV_FLAG_WIDTH * this.getImageScale());
   }

   public final float getImageScale() {
      return (float)CFG.TEXT_HEIGHT / CFG.CIV_FLAG_HEIGHT;
   }
}
