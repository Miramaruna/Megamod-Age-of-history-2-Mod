package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MinimapInfo extends MenuElement {
   public float scale;

   public MinimapInfo(int nPosX, int nPosY, int nWidth) {
      this.typeOfElement = MenuElement.TypeOfElement.MINIMAPINFO;
      this.setPosX(nPosX);
      this.setPosY(nPosY);
      this.scale = (float)nWidth / CFG.map.getMapBG().getWidth();
      this.setWidth(nWidth);
      this.setHeight((int)(CFG.map.getMapBG().getHeight() * this.scale));
   }

   @Override
   public final void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
      oSB.end();
      AoCGame.viewport
         .setWorldSize(
            CFG.GAME_WIDTH * ((float)CFG.map.getMapBG().getWidth() / this.getWidth()),
            CFG.GAME_HEIGHT * ((float)CFG.map.getMapBG().getHeight() / this.getHeight())
         );
      AoCGame.viewport.apply();
      AoCGame.camera
         .setToOrtho(
            true,
            CFG.GAME_WIDTH * ((float)CFG.map.getMapBG().getWidth() / this.getWidth()),
            -(CFG.GAME_HEIGHT * ((float)CFG.map.getMapBG().getHeight() / this.getHeight()))
         );
      oSB.setProjectionMatrix(AoCGame.camera.combined);
      oSB.begin();
      CFG.map
         .getMapBG()
         .drawMap(
            oSB,
            (int)((this.getPosX() + iTranslateX) * ((float)CFG.map.getMapBG().getWidth() / this.getWidth())),
            (int)((this.getPosY() + iTranslateY) * ((float)CFG.map.getMapBG().getHeight() / this.getHeight()))
         );
      CFG.game
         .drawProvinces(
            oSB,
            (int)((this.getPosX() + iTranslateX) * ((float)CFG.map.getMapBG().getWidth() / this.getWidth())),
            (int)((this.getPosY() + iTranslateY) * ((float)CFG.map.getMapBG().getHeight() / this.getHeight())),
            1.0F,
            255
         );
      oSB.end();
      AoCGame.camera.setToOrtho(false, CFG.GAME_WIDTH, -CFG.GAME_HEIGHT);
      AoCGame.viewport.setWorldSize(CFG.GAME_WIDTH, CFG.GAME_HEIGHT);
      AoCGame.viewport.apply();
      oSB.setProjectionMatrix(AoCGame.camera.combined);
      oSB.begin();
   }
}
