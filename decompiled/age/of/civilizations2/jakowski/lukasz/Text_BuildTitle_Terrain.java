package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Text_BuildTitle_Terrain extends Text_BuildTitle {
   public int iTerrainID;

   public Text_BuildTitle_Terrain(String sText, int iTextPositionX, int iPosX, int iPosY, int iWidth, int iHeight, int iTerrainID) {
      super(sText, iTextPositionX, iPosX, iPosY, iWidth, iHeight);
      this.iTerrainID = iTerrainID;
   }

   @Override
   public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
      super.draw(oSB, iTranslateX, iTranslateY, isActive, scrollableY);
      CFG.terrainTypesManager
         .getIcon(this.iTerrainID)
         .draw(
            oSB,
            this.getPosX()
               + this.getWidth()
               - CFG.PADDING * 2
               - (int)(CFG.terrainTypesManager.getIcon(this.iTerrainID).getWidth() * this.getImageScale())
               + iTranslateX,
            this.getPosY()
               + this.getHeight() / 2
               - CFG.terrainTypesManager.getIcon(this.iTerrainID).getHeight()
               - (int)(CFG.terrainTypesManager.getIcon(this.iTerrainID).getHeight() * this.getImageScale()) / 2
               + iTranslateY,
            (int)(CFG.terrainTypesManager.getIcon(this.iTerrainID).getWidth() * this.getImageScale()),
            (int)(CFG.terrainTypesManager.getIcon(this.iTerrainID).getHeight() * this.getImageScale())
         );
   }

   public float getImageScale() {
      return CFG.TEXT_HEIGHT * 1.0F / CFG.terrainTypesManager.getIcon(this.iTerrainID).getHeight();
   }
}
