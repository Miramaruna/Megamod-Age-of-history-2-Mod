package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.PixmapIO;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.utils.ScissorStack;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.ScreenUtils;
import java.util.ArrayList;

public class Menu_Printamap extends SliderMenu {
   public int iMapPosX = 0;
   public int iMapPosY = 0;
   public int id = 0;

   public Menu_Printamap() {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      this.initMenu(null, 0, 0, CFG.GAME_WIDTH, CFG.GAME_HEIGHT, menuElements);
   }

   @Override
   public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      super.draw(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
      CFG.map.getMapCoordinates().setNewPosX(this.iMapPosX);
      CFG.map.getMapCoordinates().setNewPosY(this.iMapPosY);
      this.iMapPosX = this.iMapPosX - CFG.GAME_WIDTH;
      if (-this.iMapPosX >= CFG.map.getMapBG().getWidth()) {
         this.iMapPosX = 0;
         this.iMapPosY = this.iMapPosY - CFG.GAME_HEIGHT;
         if (-this.iMapPosY >= CFG.map.getMapBG().getHeight()) {
            this.onBackPressed();
            CFG.toast.setInView(CFG.langManager.get("Saved"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE);
         }
      }

      this.saveScenarioMinimapPreviewTexture(oSB);
      CFG.setRender_3(true);
   }

   @Override
   public void actionElement(int nMenuElementID) {
   }

   @Override
   public final void onBackPressed() {
      CFG.menuManager.setViewIDWithoutAnimation(CFG.backToMenu);
      CFG.menuManager.setBackAnimation(true);
   }

   public final void saveScenarioMinimapPreviewTexture(SpriteBatch oSB) {
      try {
         oSB.flush();
         ScissorStack.popScissors();
      } catch (IllegalStateException var5) {
      }

      Image tempMinimapPrerivew = new Image(
         new Texture(ScreenUtils.getFrameBufferPixmap(0, CFG.GAME_HEIGHT - CFG.GAME_HEIGHT, CFG.GAME_WIDTH, CFG.GAME_HEIGHT))
      );

      try {
         tempMinimapPrerivew.getTexture().getTextureData().prepare();
      } catch (GdxRuntimeException var4) {
      }

      PixmapIO.writePNG(
         Gdx.files.local("map/" + CFG.map.getFile_ActiveMap_Path() + "PRINT/map" + this.id++ + ".png"),
         tempMinimapPrerivew.getTexture().getTextureData().consumePixmap()
      );
      CFG.setRender_3(true);
      tempMinimapPrerivew.getTexture().dispose();
      tempMinimapPrerivew = null;
   }
}
