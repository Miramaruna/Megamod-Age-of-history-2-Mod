package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.utils.ScissorStack;

public class Minimap extends MenuElement {
   public int iWindowPosX;
   public int iWindowPosY;
   public int iWidnowHeight;

   public Minimap(int nPosX, int nPosY) {
      this.typeOfElement = MenuElement.TypeOfElement.MINIMAP;
      this.setPosX(nPosX);
      this.setPosY(nPosY);
      this.setWidth(CFG.map.getMapBG().getMinimapWidth());
      this.setHeight(CFG.map.getMapBG().getMinimapHeight());
   }

   @Override
   public final void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
      oSB.setColor(Color.BLACK);
      ImageManager.getImage(Images.pix255_255_255)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY,
            CFG.map.getMapBG().getMinimapOfCivilizationsWidth(),
            CFG.map.getMapBG().getMinimapOfCivilizationsHeight()
         );
      oSB.setColor(Color.WHITE);
      CFG.map.getMapBG().drawMinimapTexture(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY);
      if (this.getIsHovered()) {
         oSB.setColor(new Color(1.0F, 1.0F, 1.0F, 0.025F));
         ImageManager.getImage(Images.pix255_255_255)
            .draw(
               oSB,
               this.getPosX() + iTranslateX,
               this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY,
               CFG.map.getMapBG().getMinimapOfCivilizationsWidth(),
               CFG.map.getMapBG().getMinimapOfCivilizationsHeight()
            );
         oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.45F));
         ImageManager.getImage(Images.gradient)
            .draw(
               oSB,
               this.getPosX() + iTranslateX,
               this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY,
               CFG.map.getMapBG().getMinimapOfCivilizationsWidth(),
               CFG.map.getMapBG().getMinimapOfCivilizationsHeight() / 7
            );
         ImageManager.getImage(Images.gradient)
            .draw(
               oSB,
               this.getPosX() + iTranslateX,
               this.getPosY()
                  - ImageManager.getImage(Images.gradient).getHeight()
                  + CFG.map.getMapBG().getMinimapOfCivilizationsHeight()
                  - CFG.map.getMapBG().getMinimapOfCivilizationsHeight() / 7
                  + iTranslateY,
               CFG.map.getMapBG().getMinimapOfCivilizationsWidth(),
               CFG.map.getMapBG().getMinimapOfCivilizationsHeight() / 7,
               false,
               true
            );
         oSB.setColor(Color.WHITE);
      }

      CFG.map
         .getMapBG()
         .getMinimap()
         .draw2(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() - CFG.map.getMapBG().getMinimap().getHeight() + iTranslateY,
            CFG.map.getMapBG().getMinimapWidth() - CFG.map.getMapBG().getMinimap().getWidth(),
            CFG.map.getMapBG().getMinimapHeight() - CFG.map.getMapBG().getMinimap().getHeight()
         );
      CFG.map
         .getMapBG()
         .getMinimap()
         .draw2(
            oSB,
            this.getPosX() + CFG.map.getMapBG().getMinimapWidth() - CFG.map.getMapBG().getMinimap().getWidth() + iTranslateX,
            this.getPosY() - CFG.map.getMapBG().getMinimap().getHeight() + iTranslateY,
            CFG.map.getMapBG().getMinimap().getWidth(),
            CFG.map.getMapBG().getMinimapHeight() - CFG.map.getMapBG().getMinimap().getHeight(),
            true,
            false
         );
      CFG.map
         .getMapBG()
         .getMinimap()
         .draw2(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() + CFG.map.getMapBG().getMinimapHeight() - CFG.map.getMapBG().getMinimap().getHeight() * 2 + iTranslateY,
            CFG.map.getMapBG().getMinimapWidth() - CFG.map.getMapBG().getMinimap().getWidth(),
            CFG.map.getMapBG().getMinimap().getHeight(),
            false,
            true
         );
      CFG.map
         .getMapBG()
         .getMinimap()
         .draw2(
            oSB,
            this.getPosX() + CFG.map.getMapBG().getMinimapWidth() - CFG.map.getMapBG().getMinimap().getWidth() + iTranslateX,
            this.getPosY() + CFG.map.getMapBG().getMinimapHeight() - CFG.map.getMapBG().getMinimap().getHeight() * 2 + iTranslateY,
            CFG.map.getMapBG().getMinimap().getWidth(),
            CFG.map.getMapBG().getMinimap().getHeight(),
            true,
            true
         );
      oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.3F));
      if (CFG.map.getMapBG().fMinimapScaled_Scale != 1.0F) {
         Rectangle clipBounds = new Rectangle(this.getPosX() + iTranslateX, CFG.GAME_HEIGHT - this.getPosY() - iTranslateY, this.getWidth(), -this.getHeight());
         oSB.flush();
         ScissorStack.pushScissors(clipBounds);
         this.iWindowPosX = (int)(
            -(CFG.map.getMapCoordinates().getPosX() + CFG.map.getMapBG().iMinimapScaled_PosX) / CFG.map.getMapScale().getMinimapScaled_ScaleX()
               - (
                  CFG.map.getMapBG().minimapBelowZero
                        && -CFG.map.getMapCoordinates().getPosX()
                           > CFG.map.getMapBG().getWidth() - CFG.map.getMapBG().getWidth() / CFG.map.getMapBG().fMinimapScaled_Scale
                     ? CFG.map.getMapBG().getWidth() / CFG.map.getMapScale().getMinimapScaled_ScaleX()
                     : 0.0F
               )
         );
         this.iWindowPosY = (int)(
            -(CFG.map.getMapCoordinates().getPosY() + CFG.map.getMapBG().iMinimapScaled_PosY) / CFG.map.getMapScale().getMinimapScaled_ScaleY()
         );
         this.iWidnowHeight = (int)(
            this.iWindowPosY + CFG.GAME_HEIGHT / CFG.map.getMapScale().getMinimapScaled_ScaleY() / CFG.map.getMapScale().getCurrentScale()
                  > this.getHeight() - 2
               ? this.getHeight() - 2 - this.iWindowPosY
               : CFG.GAME_HEIGHT / CFG.map.getMapScale().getMinimapScaled_ScaleY() / CFG.map.getMapScale().getCurrentScale()
         );
         CFG.drawRect(
            oSB,
            this.getPosX() + this.iWindowPosX - 1 + iTranslateX,
            1 + this.getPosY() + this.iWindowPosY - 1 + iTranslateY,
            2
               + (int)(
                  CFG.GAME_WIDTH / CFG.map.getMapScale().getMinimapScaled_ScaleX() / CFG.map.getMapScale().getCurrentScale() + this.iWindowPosX
                        > this.getWidth()
                     ? this.getWidth() - this.iWindowPosX
                     : CFG.GAME_WIDTH / CFG.map.getMapScale().getMinimapScaled_ScaleX() / CFG.map.getMapScale().getCurrentScale()
               ),
            this.iWidnowHeight + 2
         );
         oSB.setColor(new Color(1.0F, 1.0F, 1.0F, 0.8F));
         CFG.drawRect(
            oSB,
            this.getPosX() + this.iWindowPosX + iTranslateX,
            1 + this.getPosY() + this.iWindowPosY + iTranslateY,
            (int)(
               CFG.GAME_WIDTH / CFG.map.getMapScale().getMinimapScaled_ScaleX() / CFG.map.getMapScale().getCurrentScale() + this.iWindowPosX > this.getWidth()
                  ? this.getWidth() - this.iWindowPosX
                  : CFG.GAME_WIDTH / CFG.map.getMapScale().getMinimapScaled_ScaleX() / CFG.map.getMapScale().getCurrentScale()
            ),
            this.iWidnowHeight
         );

         try {
            oSB.flush();
            ScissorStack.popScissors();
         } catch (IllegalStateException var8) {
         }
      } else {
         this.iWindowPosX = (int)(-CFG.map.getMapCoordinates().getPosX() / CFG.map.getMapScale().getMinimapScaleX());
         this.iWindowPosY = (int)(
            -CFG.map.getMapCoordinates().getPosY() / CFG.map.getMapScale().getMinimapScaleY() < 0.0F
               ? 0.0F
               : -CFG.map.getMapCoordinates().getPosY() / CFG.map.getMapScale().getMinimapScaleY()
         );
         this.iWidnowHeight = (int)(
            this.iWindowPosY + CFG.GAME_HEIGHT / CFG.map.getMapScale().getMinimapScaleY() / CFG.map.getMapScale().getCurrentScale() > this.getHeight() - 2
               ? this.getHeight() - 2 - this.iWindowPosY
               : CFG.GAME_HEIGHT / CFG.map.getMapScale().getMinimapScaleY() / CFG.map.getMapScale().getCurrentScale()
         );
         CFG.drawRect(
            oSB,
            this.getPosX() + this.iWindowPosX - 1 + iTranslateX,
            1 + this.getPosY() + this.iWindowPosY - 1 + iTranslateY,
            2
               + (int)(
                  CFG.GAME_WIDTH / CFG.map.getMapScale().getMinimapScaleX() / CFG.map.getMapScale().getCurrentScale() + this.iWindowPosX > this.getWidth()
                     ? this.getWidth() - this.iWindowPosX
                     : CFG.GAME_WIDTH / CFG.map.getMapScale().getMinimapScaleX() / CFG.map.getMapScale().getCurrentScale()
               ),
            this.iWidnowHeight + 2
         );
         oSB.setColor(new Color(1.0F, 1.0F, 1.0F, 1.0F));
         CFG.drawRect(
            oSB,
            this.getPosX() + this.iWindowPosX + iTranslateX,
            1 + this.getPosY() + this.iWindowPosY + iTranslateY,
            (int)(
               CFG.GAME_WIDTH / CFG.map.getMapScale().getMinimapScaleX() / CFG.map.getMapScale().getCurrentScale() + this.iWindowPosX > this.getWidth()
                  ? this.getWidth() - this.iWindowPosX
                  : CFG.GAME_WIDTH / CFG.map.getMapScale().getMinimapScaleX() / CFG.map.getMapScale().getCurrentScale()
            ),
            this.iWidnowHeight
         );
         if (CFG.map.getMapCoordinates().getSecondSideOfMap()) {
            oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.3F));
            CFG.drawRect(
               oSB,
               this.getPosX() + iTranslateX,
               this.getPosY() + 1 + this.iWindowPosY + iTranslateY,
               (int)Math.abs(
                  CFG.GAME_WIDTH / CFG.map.getMapScale().getCurrentScale() / CFG.map.getMapScale().getMinimapScaleX()
                     - (CFG.map.getMapBG().getWidth() + CFG.map.getMapCoordinates().getPosX()) / CFG.map.getMapScale().getMinimapScaleX()
               ),
               this.iWidnowHeight
            );
            oSB.setColor(new Color(1.0F, 1.0F, 1.0F, 1.0F));
            CFG.drawRect(
               oSB,
               this.getPosX() + iTranslateX,
               this.getPosY() + 1 + this.iWindowPosY + iTranslateY,
               (int)Math.abs(
                  CFG.GAME_WIDTH / CFG.map.getMapScale().getCurrentScale() / CFG.map.getMapScale().getMinimapScaleX()
                     - (CFG.map.getMapBG().getWidth() + CFG.map.getMapCoordinates().getPosX()) / CFG.map.getMapScale().getMinimapScaleX()
               ),
               this.iWidnowHeight
            );
         }
      }

      oSB.setColor(CFG.COLOR_MINIMAP_BORDER);
      CFG.drawRect(oSB, this.getPosX() + iTranslateX, this.getPosY() - 1 + iTranslateY, this.getWidth(), this.getHeight());
      oSB.setColor(Color.WHITE);
   }

   @Override
   public int getWidth() {
      return CFG.map.getMapBG().getMinimapWidth();
   }

   @Override
   public int getHeight() {
      return CFG.map.getMapBG().getMinimapHeight();
   }
}
