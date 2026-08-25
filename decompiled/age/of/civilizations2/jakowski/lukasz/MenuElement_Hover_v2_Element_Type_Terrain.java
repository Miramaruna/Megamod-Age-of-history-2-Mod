package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MenuElement_Hover_v2_Element_Type_Terrain implements MenuElement_Hover_v2_Element_Type {
   public int iTerrainID;
   public int offsetLeft = 0;
   public int offsetRight = 0;

   public MenuElement_Hover_v2_Element_Type_Terrain(int iTerrainID) {
      this.iTerrainID = iTerrainID;
      this.offsetLeft = 0;
      this.offsetRight = CFG.PADDING;
   }

   public MenuElement_Hover_v2_Element_Type_Terrain(int iTerrainID, int offsetLeft) {
      this.iTerrainID = iTerrainID;
      this.offsetLeft = offsetLeft;
      this.offsetRight = CFG.PADDING;
   }

   public MenuElement_Hover_v2_Element_Type_Terrain(int iTerrainID, int offsetLeft, int offsetRight) {
      this.iTerrainID = iTerrainID;
      this.offsetLeft = offsetLeft;
      this.offsetRight = offsetRight;
   }

   @Override
   public void draw(SpriteBatch oSB, int nPosX, int nPosY, float nAlpha) {
      oSB.setColor(new Color(1.0F, 1.0F, 1.0F, nAlpha));
      CFG.terrainTypesManager
         .getIcon(this.iTerrainID)
         .draw(
            oSB,
            nPosX + this.offsetLeft,
            nPosY
               + CFG.PADDING
               - CFG.terrainTypesManager.getIcon(this.iTerrainID).getHeight()
               + CFG.TEXT_HEIGHT / 2
               - (int)(CFG.terrainTypesManager.getIcon(this.iTerrainID).getHeight() * this.getImageScale() / 2.0F),
            (int)(CFG.terrainTypesManager.getIcon(this.iTerrainID).getWidth() * this.getImageScale()),
            (int)(CFG.terrainTypesManager.getIcon(this.iTerrainID).getHeight() * this.getImageScale())
         );
      ImageManager.getImage(Images.flag_rect)
         .draw(
            oSB,
            nPosX + this.offsetLeft,
            nPosY
               + CFG.PADDING
               - ImageManager.getImage(Images.flag_rect).getHeight()
               + CFG.TEXT_HEIGHT / 2
               - (int)(CFG.terrainTypesManager.getIcon(this.iTerrainID).getHeight() * this.getImageScale() / 2.0F),
            (int)(CFG.terrainTypesManager.getIcon(this.iTerrainID).getWidth() * this.getImageScale()),
            (int)(CFG.terrainTypesManager.getIcon(this.iTerrainID).getHeight() * this.getImageScale())
         );
      oSB.setColor(Color.WHITE);
   }

   @Override
   public int getWidth() {
      return this.offsetRight + this.offsetLeft + (int)(CFG.terrainTypesManager.getIcon(this.iTerrainID).getWidth() * this.getImageScale());
   }

   public final float getImageScale() {
      return (float)CFG.TEXT_HEIGHT / CFG.terrainTypesManager.getIcon(this.iTerrainID).getHeight();
   }
}
