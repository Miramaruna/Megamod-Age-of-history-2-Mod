package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Button_Diplomacy_War_Cost extends Button_Diplomacy_War {
   public String sDiploCost = "2.4";
   public int iDiploCostWidth = 0;

   public Button_Diplomacy_War_Cost(int nAggressor, int nDefender, int iPosX, int iPosY, int iWidth) {
      super(nAggressor, nDefender, iPosX, iPosY, iWidth);
      CFG.glyphLayout.setText(CFG.fontMain, this.sDiploCost);
      this.iDiploCostWidth = (int)(CFG.glyphLayout.width * 0.7F);
   }

   @Override
   public void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
      super.drawText(oSB, iTranslateX, iTranslateY, isActive);
      CFG.fontMain.getData().setScale(0.7F);
      ImageManager.getImage(Images.top_diplomacy_points)
         .draw(
            oSB,
            this.getPosX()
               + this.getWidth()
               - CFG.PADDING * 2
               - (int)(ImageManager.getImage(Images.top_diplomacy_points).getWidth() * this.getImageScale(Images.top_diplomacy_points))
               + iTranslateX,
            this.getPosY()
               + this.getHeight() / 2
               - (int)(ImageManager.getImage(Images.top_diplomacy_points).getHeight() * this.getImageScale(Images.top_diplomacy_points)) / 2
               - ImageManager.getImage(Images.top_diplomacy_points).getHeight()
               + iTranslateY,
            (int)(ImageManager.getImage(Images.top_diplomacy_points).getWidth() * this.getImageScale(Images.top_diplomacy_points)),
            (int)(ImageManager.getImage(Images.top_diplomacy_points).getHeight() * this.getImageScale(Images.top_diplomacy_points))
         );
      CFG.drawTextWithShadow(
         oSB,
         this.sDiploCost,
         this.getPosX()
            + this.getWidth()
            - CFG.PADDING * 3
            - this.iDiploCostWidth
            - (int)(ImageManager.getImage(Images.top_diplomacy_points).getWidth() * this.getImageScale(Images.top_diplomacy_points))
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
