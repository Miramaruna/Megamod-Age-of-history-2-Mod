package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.utils.ScissorStack;
import java.util.ArrayList;
import java.util.List;

public class Graph_Circle extends MenuElement {
   public static final int ANIMATION_TIME = 300;
   public long lTime = 0L;
   public List<Graph_CircleData> lData = new ArrayList<>();
   public int iDataSize;
   public boolean isDescriptionActive = true;
   public boolean hideAnimation = false;
   public boolean moveable = false;
   public int iButtonsPosY = 0;
   public int iExtraWidth = 0;
   public static final float TEXT_SCALE = 0.7F;

   public Graph_Circle(int iPosX, int iPosY, List<Integer> nValues, List<Integer> nCivIDs, MenuElement_Hover menuElementHover) {
      this.setPosX(iPosX);
      this.setPosY(iPosY);
      this.setWidth(CFG.graphCircleDraw.getWidth());
      this.setHeight(CFG.graphCircleDraw.getWidth());
      this.menuElementHover = menuElementHover;
      this.iDataSize = nValues.size();
      ArrayList<Integer> tempSortedValues = new ArrayList<>();
      ArrayList<Integer> tempSortedCivs = new ArrayList<>();

      while (nValues.size() > 0) {
         int nMinID = 0;

         for (int i = 1; i < nValues.size(); i++) {
            if (nValues.get(nMinID) < nValues.get(i)) {
               nMinID = i;
            }
         }

         tempSortedValues.add(nValues.get(nMinID));
         tempSortedCivs.add(nCivIDs.get(nMinID));
         nValues.remove(nMinID);
         nCivIDs.remove(nMinID);
      }

      int countValues = 0;

      for (int ix = 0; ix < this.iDataSize; ix++) {
         countValues += tempSortedValues.get(ix);
      }

      for (int var15 = 0; var15 < this.iDataSize; var15++) {
         this.lData.add(new Graph_CircleData(tempSortedCivs.get(var15), tempSortedValues.get(var15).intValue() * 100.0F / countValues));
      }

      this.updateMoveable();
      this.typeOfElement = MenuElement.TypeOfElement.GRAPH_CIRCLE;

      try {
         float nMaxWidth = 0.0F;

         for (int i2 = 0; i2 < this.iDataSize; i2++) {
            CFG.glyphLayout.setText(CFG.fontMain, "" + this.getPercentage(this.lData.get(i2).getPercentage(), 5) + "%");
            if (nMaxWidth < CFG.glyphLayout.width) {
               nMaxWidth = CFG.glyphLayout.width;
            }
         }

         this.iExtraWidth = (int)(CFG.CIV_FLAG_WIDTH * (CFG.TEXT_HEIGHT * 0.7F) / CFG.CIV_FLAG_HEIGHT) + CFG.PADDING * 4 + (int)(nMaxWidth * 0.7F);
      } catch (IndexOutOfBoundsException var12) {
         this.iExtraWidth = super.getWidth();
      } catch (IllegalArgumentException var13) {
         this.iExtraWidth = super.getWidth();
      }
   }

   @Override
   public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
      this.drawGraph(
         oSB,
         iTranslateX,
         iTranslateY,
         isActive,
         scrollableY,
         this.getPosX(),
         this.getPosY(),
         this.getWidth_PercStrings(super.getWidth()),
         this.getHeight_Perc(),
         CFG.graphCircleDraw.getWidth()
      );
   }

   public final void drawGraph(
      SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY, int nPosX, int nPosY, int nWidth, int nHeight, int nWidth_LEFT
   ) {
      CFG.graphCircleDraw.draw(oSB, nPosX + iTranslateX, nPosY + iTranslateY, this.lData, isActive || this.getIsHovered());
      if (this.isDescriptionActive || this.hideAnimation) {
         Rectangle clipBounds = new Rectangle(nPosX + iTranslateX, CFG.GAME_HEIGHT - nPosY - iTranslateY, nWidth, -nHeight);
         oSB.flush();
         ScissorStack.pushScissors(clipBounds);
         CFG.fontMain.getData().setScale(0.7F);
         iTranslateY += this.iButtonsPosY;
         float tempFlagScale = CFG.TEXT_HEIGHT * 0.7F / CFG.CIV_FLAG_HEIGHT;

         for (int i = 0; i < this.iDataSize; i++) {
            try {
               oSB.setColor(
                  new Color(
                     CFG.game.getCiv(this.lData.get(i).getDataID()).getR() / 255.0F,
                     CFG.game.getCiv(this.lData.get(i).getDataID()).getG() / 255.0F,
                     CFG.game.getCiv(this.lData.get(i).getDataID()).getB() / 255.0F,
                     0.45F
                  )
               );
               ImageManager.getImage(Images.slider_gradient)
                  .draw(
                     oSB,
                     nPosX + nWidth_LEFT + CFG.PADDING + iTranslateX,
                     -ImageManager.getImage(Images.slider_gradient).getHeight()
                        + nPosY
                        + (int)(CFG.TEXT_HEIGHT * 0.7F + CFG.PADDING * 2) * i
                        + CFG.PADDING * i
                        + iTranslateY,
                     CFG.CIV_COLOR_WIDTH + nWidth - nWidth_LEFT,
                     (int)(CFG.TEXT_HEIGHT * 0.7F + CFG.PADDING * 2)
                  );
               oSB.setColor(
                  new Color(
                     CFG.game.getCiv(this.lData.get(i).getDataID()).getR() / 255.0F,
                     CFG.game.getCiv(this.lData.get(i).getDataID()).getG() / 255.0F,
                     CFG.game.getCiv(this.lData.get(i).getDataID()).getB() / 255.0F,
                     0.2F
                  )
               );
               ImageManager.getImage(Images.gradient)
                  .draw(
                     oSB,
                     nPosX + nWidth_LEFT + CFG.PADDING + iTranslateX,
                     -ImageManager.getImage(Images.gradient).getHeight()
                        + nPosY
                        + (int)(CFG.TEXT_HEIGHT * 0.7F + CFG.PADDING * 2) * i
                        + CFG.PADDING * i
                        + iTranslateY,
                     CFG.CIV_COLOR_WIDTH + nWidth - nWidth_LEFT,
                     (int)(CFG.TEXT_HEIGHT * 0.7F + CFG.PADDING * 2) / 4
                  );
               ImageManager.getImage(Images.gradient)
                  .draw(
                     oSB,
                     nPosX + nWidth_LEFT + CFG.PADDING + iTranslateX,
                     (int)(CFG.TEXT_HEIGHT * 0.7F + CFG.PADDING * 2)
                        - (int)(CFG.TEXT_HEIGHT * 0.7F + CFG.PADDING * 2) / 4
                        - ImageManager.getImage(Images.gradient).getHeight()
                        + nPosY
                        + (int)(CFG.TEXT_HEIGHT * 0.7F + CFG.PADDING * 2) * i
                        + CFG.PADDING * i
                        + iTranslateY,
                     CFG.CIV_COLOR_WIDTH + nWidth - nWidth_LEFT,
                     (int)(CFG.TEXT_HEIGHT * 0.7F + CFG.PADDING * 2) / 4,
                     false,
                     true
                  );
               oSB.setColor(Color.WHITE);
               CFG.game
                  .getCiv(this.lData.get(i).getDataID())
                  .getFlag()
                  .draw(
                     oSB,
                     nPosX + nWidth_LEFT + CFG.PADDING + CFG.PADDING + iTranslateX,
                     nPosY
                        + (int)(CFG.TEXT_HEIGHT * 0.7F + CFG.PADDING * 2) * i
                        + CFG.PADDING * i
                        + (int)(CFG.TEXT_HEIGHT * 0.7F + CFG.PADDING * 2) / 2
                        - (int)(CFG.TEXT_HEIGHT * 0.7F / 2.0F)
                        - CFG.game.getCiv(this.lData.get(i).getDataID()).getFlag().getHeight()
                        + iTranslateY,
                     (int)(CFG.CIV_FLAG_WIDTH * tempFlagScale),
                     (int)(CFG.CIV_FLAG_HEIGHT * tempFlagScale)
                  );
            } catch (IndexOutOfBoundsException var16) {
               oSB.setColor(
                  new Color(
                     CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getR(),
                     CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getG(),
                     CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getB(),
                     0.45F
                  )
               );
               ImageManager.getImage(Images.slider_gradient)
                  .draw(
                     oSB,
                     nPosX + nWidth_LEFT + CFG.PADDING + iTranslateX,
                     -ImageManager.getImage(Images.slider_gradient).getHeight()
                        + nPosY
                        + (int)(CFG.TEXT_HEIGHT * 0.7F + CFG.PADDING * 2) * i
                        + CFG.PADDING * i
                        + iTranslateY,
                     CFG.CIV_COLOR_WIDTH + nWidth - nWidth_LEFT,
                     (int)(CFG.TEXT_HEIGHT * 0.7F + CFG.PADDING * 2)
                  );
               oSB.setColor(
                  new Color(
                     CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getR(),
                     CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getG(),
                     CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getB(),
                     0.2F
                  )
               );
               ImageManager.getImage(Images.gradient)
                  .draw(
                     oSB,
                     nPosX + nWidth_LEFT + CFG.PADDING + iTranslateX,
                     -ImageManager.getImage(Images.gradient).getHeight()
                        + nPosY
                        + (int)(CFG.TEXT_HEIGHT * 0.7F + CFG.PADDING * 2) * i
                        + CFG.PADDING * i
                        + iTranslateY,
                     CFG.CIV_COLOR_WIDTH + nWidth - nWidth_LEFT,
                     (int)(CFG.TEXT_HEIGHT * 0.7F + CFG.PADDING * 2) / 4
                  );
               ImageManager.getImage(Images.gradient)
                  .draw(
                     oSB,
                     nPosX + nWidth_LEFT + CFG.PADDING + iTranslateX,
                     (int)(CFG.TEXT_HEIGHT * 0.7F + CFG.PADDING * 2)
                        - (int)(CFG.TEXT_HEIGHT * 0.7F + CFG.PADDING * 2) / 4
                        - ImageManager.getImage(Images.gradient).getHeight()
                        + nPosY
                        + (int)(CFG.TEXT_HEIGHT * 0.7F + CFG.PADDING * 2) * i
                        + CFG.PADDING * i
                        + iTranslateY,
                     CFG.CIV_COLOR_WIDTH + nWidth - nWidth_LEFT,
                     (int)(CFG.TEXT_HEIGHT * 0.7F + CFG.PADDING * 2) / 4,
                     false,
                     true
                  );
               oSB.setColor(Color.WHITE);
               ImageManager.getImage(Images.randomCivilizationFlag)
                  .draw(
                     oSB,
                     nPosX + nWidth_LEFT + CFG.PADDING + CFG.PADDING + iTranslateX,
                     nPosY
                        + (int)(CFG.TEXT_HEIGHT * 0.7F + CFG.PADDING * 2) * i
                        + CFG.PADDING * i
                        + (int)(CFG.TEXT_HEIGHT * 0.7F + CFG.PADDING * 2) / 2
                        - (int)(CFG.TEXT_HEIGHT * 0.7F / 2.0F)
                        - ImageManager.getImage(Images.randomCivilizationFlag).getHeight()
                        + iTranslateY,
                     (int)(CFG.CIV_FLAG_WIDTH * tempFlagScale),
                     (int)(CFG.CIV_FLAG_HEIGHT * tempFlagScale)
                  );
            }

            ImageManager.getImage(Images.flag_rect)
               .draw(
                  oSB,
                  nPosX + nWidth_LEFT + CFG.PADDING + CFG.PADDING + iTranslateX,
                  nPosY
                     + (int)(CFG.TEXT_HEIGHT * 0.7F + CFG.PADDING * 2) * i
                     + CFG.PADDING * i
                     + (int)(CFG.TEXT_HEIGHT * 0.7F + CFG.PADDING * 2) / 2
                     - (int)(CFG.TEXT_HEIGHT * 0.7F / 2.0F)
                     - CFG.CIV_FLAG_HEIGHT
                     + iTranslateY,
                  (int)(CFG.CIV_FLAG_WIDTH * tempFlagScale),
                  (int)(CFG.CIV_FLAG_HEIGHT * tempFlagScale)
               );
            CFG.drawTextWithShadow(
               oSB,
               "" + this.getPercentage(this.lData.get(i).getPercentage(), 5) + "%",
               (int)(CFG.CIV_FLAG_WIDTH * tempFlagScale) + CFG.PADDING + nPosX + nWidth_LEFT + CFG.PADDING + CFG.PADDING + iTranslateX,
               nPosY
                  + (int)(CFG.TEXT_HEIGHT * 0.7F + CFG.PADDING * 2) * i
                  + CFG.PADDING * i
                  + (int)(CFG.TEXT_HEIGHT * 0.7F + CFG.PADDING * 2) / 2
                  - (int)(CFG.TEXT_HEIGHT * 0.7F / 2.0F)
                  + iTranslateY,
               CFG.COLOR_TEXT_MODIFIER_NEUTRAL
            );
         }

         CFG.fontMain.getData().setScale(1.0F);

         try {
            oSB.flush();
            ScissorStack.popScissors();
         } catch (IllegalStateException var15) {
         }
      }
   }

   public final void updateMoveable() {
      if (this.getMaxHeight() > this.getHeight_Perc()) {
         this.moveable = true;
      } else {
         this.moveable = false;
         this.iButtonsPosY = 0;
      }
   }

   public final int getMaxHeight() {
      return (int)(CFG.TEXT_HEIGHT * 0.7F + CFG.PADDING * 2) * this.iDataSize + CFG.PADDING * (this.iDataSize - 1);
   }

   @Override
   public int getCurrent() {
      return this.iButtonsPosY;
   }

   @Override
   public void setCurrent(int nButtonsPosX) {
      if (nButtonsPosX > 0) {
         nButtonsPosX = 0;
         CFG.menuManager.setUpdateSliderMenuPosY(true);
      } else if (nButtonsPosX < -this.getMaxHeight() + this.getHeight_Perc()) {
         nButtonsPosX = -this.getMaxHeight() + this.getHeight_Perc();
         CFG.menuManager.setUpdateSliderMenuPosY(true);
      }

      if (this.iButtonsPosY != nButtonsPosX) {
         this.iButtonsPosY = nButtonsPosX;
         CFG.setRender_3(true);
      }
   }

   public int getHeight_Perc() {
      return CFG.graphCircleDraw.getWidth();
   }

   @Override
   public int getWidth() {
      return this.getWidth_PercStrings(super.getWidth());
   }

   public int getWidth_PercStrings(int nWidth) {
      if (this.isDescriptionActive) {
         if (this.lTime + 300L >= System.currentTimeMillis()) {
            CFG.setRender_3(true);
            return nWidth + (int)(this.iExtraWidth * ((float)(System.currentTimeMillis() - this.lTime) / 300.0F));
         } else {
            return nWidth + this.iExtraWidth;
         }
      } else if (this.hideAnimation) {
         if (this.lTime + 300L >= System.currentTimeMillis()) {
            CFG.setRender_3(true);
            return nWidth + this.iExtraWidth - (int)(this.iExtraWidth * ((float)(System.currentTimeMillis() - this.lTime) / 300.0F));
         } else {
            this.hideAnimation = false;
            return nWidth;
         }
      } else {
         return nWidth;
      }
   }

   @Override
   public boolean getMoveable() {
      return this.moveable;
   }

   public void setMoveable(boolean moveable) {
      this.moveable = moveable;
   }

   @Override
   public boolean getAnotherView() {
      return this.isDescriptionActive;
   }

   @Override
   public void setAnotherView(boolean inAnotherView) {
      this.isDescriptionActive = inAnotherView;
      if (this.getVisible()) {
         this.hideAnimation = !this.isDescriptionActive;
         this.lTime = System.currentTimeMillis();
      } else {
         this.lTime = 0L;
      }
   }

   @Override
   public void setVisible(boolean isVisible) {
      super.setVisible(isVisible);
   }

   public final String getPercentage(float nPerc, int nPrecision) {
      return ("" + nPerc).substring(0, Math.min(nPrecision, ("" + nPerc).length()));
   }

   @Override
   public int getSFX() {
      return SoundsManager.SOUND_CLICK2;
   }
}
