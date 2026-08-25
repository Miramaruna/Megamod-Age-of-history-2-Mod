package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.io.Serializable;

public class Mountain implements Serializable {
   public static final long serialVersionUID = 0L;
   public String sName = null;
   public int iPosX;
   public int iPosY;
   public int iElevation;

   public Mountain(String sName, int iElevation, int nPosX, int nPosY) {
      this.sName = sName;
      this.iElevation = iElevation;
      this.iPosX = nPosX;
      this.iPosY = nPosY;
   }

   public final void draw(SpriteBatch oSB, int nProvinceID, float nScale) {
      this.draw(oSB, nProvinceID, nScale, new Color(1.0F, 1.0F, 1.0F, 0.85F), Images.mount);
   }

   public final void draw(SpriteBatch oSB, int nProvinceID, float nScale, int nImageID) {
      this.draw(oSB, nProvinceID, nScale, new Color(1.0F, 1.0F, 1.0F, 0.85F), nImageID);
   }

   public final void draw(SpriteBatch oSB, int nProvinceID, float nScale, Color nColor) {
      this.draw(oSB, nProvinceID, nScale, nColor, Images.mount);
   }

   public final void draw(SpriteBatch oSB, int nProvinceID, float nScale, Color nColor, int nImageID) {
      ImageManager.getImage(nImageID)
         .draw(
            oSB,
            (int)(
               (this.getPosX() * CFG.map.getMapBG().getMapScale() + CFG.game.getProvince(nProvinceID).getTranslateProvincePosX()) * nScale
                  - ImageManager.getImage(nImageID).getWidth() / 2
            ),
            (int)((this.getPosY() * CFG.map.getMapBG().getMapScale() + CFG.map.getMapCoordinates().getPosY()) * nScale)
               - ImageManager.getImage(nImageID).getHeight() / 2
         );
      CFG.drawText(
         oSB,
         this.getName(),
         (int)(
            (this.getPosX() * CFG.map.getMapBG().getMapScale() + CFG.game.getProvince(nProvinceID).getTranslateProvincePosX()) * nScale
               + ImageManager.getImage(nImageID).getWidth() / 2
               + 1.0F
         ),
         (int)(
            (this.getPosY() * CFG.map.getMapBG().getMapScale() + CFG.map.getMapCoordinates().getPosY()) * nScale
               - ImageManager.getImage(nImageID).getHeight() / 2
               + ImageManager.getImage(nImageID).getHeight() / 2
               - CFG.ARMY_HEIGHT / 4
               + 1.0F
         ),
         nColor
      );
      CFG.drawText(
         oSB,
         "" + this.iElevation + "m",
         (int)(
            (this.getPosX() * CFG.map.getMapBG().getMapScale() + CFG.game.getProvince(nProvinceID).getTranslateProvincePosX()) * nScale
               + ImageManager.getImage(nImageID).getWidth() / 2
               + 1.0F
         ),
         (int)(
            (this.getPosY() * CFG.map.getMapBG().getMapScale() + CFG.map.getMapCoordinates().getPosY()) * nScale
               - ImageManager.getImage(nImageID).getHeight() / 2
               + ImageManager.getImage(nImageID).getHeight() / 2
               + CFG.ARMY_HEIGHT / 4
               + 1.0F
               + CFG.PADDING
         ),
         CFG.COLOR_TEXT_MODIFIER_NEUTRAL
      );
   }

   public final void drawImage(SpriteBatch oSB, int nProvinceID, float nScale) {
      ImageManager.getImage(Images.mount)
         .draw(
            oSB,
            (int)(
               (this.getPosX() * CFG.map.getMapBG().getMapScale() + CFG.game.getProvince(nProvinceID).getTranslateProvincePosX()) * nScale
                  - ImageManager.getImage(Images.mount).getWidth() / 2
            ),
            (int)((this.getPosY() * CFG.map.getMapBG().getMapScale() + CFG.map.getMapCoordinates().getPosY()) * nScale)
               - ImageManager.getImage(Images.mount).getHeight() / 2
         );
   }

   public final String getName() {
      return this.sName;
   }

   public final void setName(String sName) {
      this.sName = sName;
   }

   public final int getPosX() {
      return this.iPosX;
   }

   public final void setPosX(int iPosX) {
      this.iPosX = iPosX;
   }

   public final int getPosY() {
      return this.iPosY;
   }

   public final void setPosY(int iPosY) {
      this.iPosY = iPosY;
   }

   public final int getElevation() {
      return this.iElevation;
   }

   public final void setElevation(int iElevation) {
      this.iElevation = iElevation;
   }
}
