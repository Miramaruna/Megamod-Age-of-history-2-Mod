package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class CasusBelliButton extends Button_Diplomacy_War {
   public String sTurn;
   public int iDiploCostWidth = 0;

   public CasusBelliButton(int nAggressor, int nDefender, int iPosX, int iPosY, int iWidth, int Turns) {
      super(nAggressor, nDefender, iPosX, iPosY, iWidth);
      this.sTurn = Turns + " Ходов";
      CFG.glyphLayout.setText(CFG.fontMain, this.sTurn);
      this.iDiploCostWidth = (int)(CFG.glyphLayout.width * 0.7F);
   }

   @Override
   public void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
      super.drawText(oSB, iTranslateX, iTranslateY, isActive);
      CFG.fontMain.getData().setScale(0.7F);
      ImageManager.getImage(Images.diplo_war)
         .draw(
            oSB,
            this.getPosX()
               + this.getWidth()
               - CFG.PADDING * 2
               - (int)(ImageManager.getImage(Images.diplo_war).getWidth() * this.getImageScale(Images.diplo_war))
               + iTranslateX,
            this.getPosY()
               + this.getHeight() / 2
               - (int)(ImageManager.getImage(Images.diplo_war).getHeight() * this.getImageScale(Images.diplo_war)) / 2
               - ImageManager.getImage(Images.diplo_war).getHeight()
               + iTranslateY,
            (int)(ImageManager.getImage(Images.diplo_war).getWidth() * this.getImageScale(Images.diplo_war)),
            (int)(ImageManager.getImage(Images.diplo_war).getHeight() * this.getImageScale(Images.diplo_war))
         );
      CFG.drawTextWithShadow(
         oSB,
         this.sTurn,
         this.getPosX()
            + this.getWidth()
            - CFG.PADDING * 3
            - this.iDiploCostWidth
            - (int)(ImageManager.getImage(Images.diplo_war).getWidth() * this.getImageScale(Images.diplo_war))
            + iTranslateX,
         this.getPosY() + this.getHeight() / 2 - (int)(CFG.TEXT_HEIGHT * 0.7F / 2.0F) + iTranslateY,
         CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getDiplomacyPoints() >= 20 ? Color.WHITE : CFG.COLOR_TEXT_MODIFIER_NEGATIVE2
      );
      CFG.fontMain.getData().setScale(1.0F);
   }

   @Override
   public final float getImageScale(int nImageID) {
      return (float)CFG.TEXT_HEIGHT / ImageManager.getImage(nImageID).getHeight() < 1.0F
         ? (float)CFG.TEXT_HEIGHT / ImageManager.getImage(nImageID).getHeight()
         : 1.0F;
   }
}
