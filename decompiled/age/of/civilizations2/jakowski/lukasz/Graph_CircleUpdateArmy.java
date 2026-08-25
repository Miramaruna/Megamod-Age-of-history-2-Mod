package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;
import java.util.List;

public class Graph_CircleUpdateArmy extends MenuElement {
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

   public Graph_CircleUpdateArmy(int iPosX, int iPosY, List<Integer> nValues, List<Integer> nCivIDs, MenuElement_Hover menuElementHover) {
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
      CFG.graphCircleDraw.drawCircleUpgArmy(oSB, nPosX + iTranslateX, nPosY + iTranslateY, this.lData, isActive || this.getIsHovered());
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
