package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.List;

public class MenuElement {
   public MenuElement.TypeOfElement typeOfElement;
   public int iPosX;
   public int iPosY;
   public int iWidth;
   public int iHeight;
   public boolean isClickable = true;
   public boolean isVisible = true;
   public boolean isInView = false;
   public boolean isHovered = false;
   public MenuElement_Hover menuElementHover;

   public void buildElementHover() {
   }

   public void resetElementHover() {
      this.menuElementHover = null;
   }

   public void drawMenuElementHover2(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
      if (this.menuElementHover != null) {
         if (CFG.isAndroid()) {
            this.menuElementHover.drawAlwaysOver_Mobile(oSB, Touch.getMousePosX(), Touch.getMousePosY() - CFG.menuManager.getHover_ExtraPosY());
         } else {
            this.menuElementHover
               .draw(oSB, Touch.getMousePosX() + CFG.menuManager.getHover_ExtraPosX(), Touch.getMousePosY() + CFG.menuManager.getHover_ExtraPosY());
         }
      }
   }

   public boolean getMenuElement_Hover_IsNull() {
      return this.menuElementHover == null;
   }

   public void updateHover(int nPosX, int nPosY, int menuPosX, int menuPosY) {
   }

   public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY) {
   }

   public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
   }

   public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, int flagPixelID) {
   }

   public void setText(String sText) {
   }

   public boolean getCheckboxState() {
      return false;
   }

   public void setCheckboxState(boolean checkboxState) {
   }

   public void updateSlider(int nPosX) {
   }

   public void setCurrent(int nCurrent) {
   }

   public int getCurrent() {
      return 0;
   }

   public void setMin(int iMin) {
   }

   public void setMax(int iMax) {
   }

   public boolean getMoveable() {
      return false;
   }

   public boolean getAnotherView() {
      return false;
   }

   public void setAnotherView(boolean inAnotherView) {
   }

   public void setScrollPosY(int iScrollPosY) {
   }

   public void scrollTheMenu() {
   }

   public void srollByWheel(int nScoll) {
   }

   public boolean getIsScrollable() {
      return false;
   }

   public void addText(String sText, int extraHeight) {
   }

   public void setData(List<GraphData> nData) {
   }

   public void addData(GraphData nData) {
   }

   public void removeData(int iCivID) {
   }

   public void actionElement(int iID) {
   }

   public final MenuElement.TypeOfElement getTypeOfElement() {
      return this.typeOfElement;
   }

   public void setTypeOfButton(Button.TypeOfButton typeOfButton) {
   }

   public boolean getClickable() {
      return this.isClickable;
   }

   public final void setClickable(boolean isClickable) {
      this.isClickable = isClickable;
   }

   public boolean getVisible() {
      return this.isVisible;
   }

   public void setVisible(boolean isVisible) {
      this.isVisible = isVisible;
   }

   public int getPosX() {
      return this.iPosX;
   }

   public final void setPosX(int iPosX) {
      this.iPosX = iPosX;
   }

   public int getPosY() {
      return this.iPosY;
   }

   public final void setPosY(int iPosY) {
      this.iPosY = iPosY;
   }

   public int getWidth() {
      return this.iWidth;
   }

   public void setWidth(int iWidth) {
      this.iWidth = iWidth;
   }

   public int getHeight() {
      return this.iHeight;
   }

   public final void setHeight(int iHeight) {
      this.iHeight = iHeight;
   }

   public String getTextToDraw() {
      return "";
   }

   public String getText() {
      return "";
   }

   public int getTextWidth() {
      return 0;
   }

   public int getTextHeight() {
      return 0;
   }

   public int getTextPos() {
      return 0;
   }

   public final boolean getIsInView() {
      return this.isInView;
   }

   public final void setIsInView(boolean isInView) {
      this.isInView = isInView;
   }

   public final boolean getIsHovered() {
      return this.isHovered;
   }

   public void setIsHovered(boolean isHovered) {
      this.isHovered = isHovered;
   }

   public int getSFX() {
      return SoundsManager.SOUND_CLICK;
   }

   public static enum TypeOfElement {
      BUTTON,
      BUTTON_FLAG,
      BUTTON_TRANSPARENT,
      SLIDER,
      SLIDE,
      TEXT,
      TEXT_SLIDER,
      MINIMAP,
      MINIMAPINFO,
      FLAG_PIXEL,
      SPACE,
      DIPLOMACY_INFO,
      GRAPH,
      GRAPH_VERTICAL,
      GRAPH_CIRCLE;
   }
}
