package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Button_Menu_CivilizationAndFlag extends Button_Menu {
   public int iCivID;

   public Button_Menu_CivilizationAndFlag(int iCivID, int iTextPositionX, int iPosX, int iPosY, int iWidth, int iHeight, boolean isClickable) {
      super(CFG.game.getCiv(iCivID).getCivName(), iTextPositionX, iPosX, iPosY, iWidth, iHeight, isClickable);
      this.iCivID = iCivID;
   }

   @Override
   public final void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
      super.drawButtonBG(oSB, iTranslateX, iTranslateY, isActive);
      CFG.game
         .getCiv(this.iCivID)
         .getFlag()
         .draw(
            oSB,
            this.getPosX() + this.getTextPos() / 2 - CFG.CIV_FLAG_WIDTH / 2 + iTranslateX,
            this.getPosY() + this.getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 - CFG.game.getCiv(this.iCivID).getFlag().getHeight() + iTranslateY,
            CFG.CIV_FLAG_WIDTH,
            CFG.CIV_FLAG_HEIGHT
         );
      ImageManager.getImage(Images.flag_rect)
         .draw(
            oSB,
            this.getPosX() + this.getTextPos() / 2 - CFG.CIV_FLAG_WIDTH / 2 + iTranslateX,
            this.getPosY() + this.getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 + iTranslateY
         );
   }

   @Override
   public int getCurrent() {
      return this.iCivID;
   }
}
