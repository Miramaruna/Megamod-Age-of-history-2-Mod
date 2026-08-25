package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.utils.ScissorStack;
import java.util.ArrayList;
import java.util.List;

public class SliderMenu {
   public List<MenuElement> menuElements = new ArrayList<>();
   public int iMenuElementsSize;
   public int iPosX;
   public int iPosY;
   public int iWidth;
   public int iHeight;
   public boolean visible = true;
   public boolean closeable = false;
   public SliderMenuTitle sliderMenuTitle = null;
   public int iMenuPosX;
   public int iNewMenuPositionX;
   public int iMaxSliderPositionX;
   public boolean scrollableX = false;
   public int iMenuPosY;
   public int iNewMenuPositionY;
   public int iMaxSliderPositionY;
   public boolean scrollableY = false;
   public boolean scrollModeY = false;
   public int iScrollPosY = -1;
   public int iScrollPosY2 = -1;
   public float fScrollNewMenuPosY = 0.0F;
   public boolean scrollModeX = false;
   public int iScrollPosX = -1;
   public int iScrollPosX2 = -1;
   public float fScrollNewMenuPosX = 0.0F;

   public final void initMenu(SliderMenuTitle sliderMenuTitle, int iPosX, int iPosY, int iWidth, int iHeight, List<MenuElement> menuElements) {
      this.initMenu(sliderMenuTitle, iPosX, iPosY, iWidth, iHeight, menuElements, true, false, false);
   }

   public final void initMenu(
      SliderMenuTitle sliderMenuTitle, int iPosX, int iPosY, int iWidth, int iHeight, List<MenuElement> menuElements, boolean visible, boolean closeable
   ) {
      this.initMenu(sliderMenuTitle, iPosX, iPosY, iWidth, iHeight, menuElements, visible, false, closeable);
   }

   public final void initMenuWithBackButton(SliderMenuTitle sliderMenuTitle, int iPosX, int iPosY, int iWidth, int iHeight, List<MenuElement> menuElements) {
      this.initMenu(sliderMenuTitle, iPosX, iPosY, iWidth, iHeight, menuElements, true, true, false);
   }

   public final void initMenuWithBackButton(
      SliderMenuTitle sliderMenuTitle, int iPosX, int iPosY, int iWidth, int iHeight, List<MenuElement> menuElements, boolean closeable
   ) {
      this.initMenu(sliderMenuTitle, iPosX, iPosY, iWidth, iHeight, menuElements, true, true, closeable);
   }

   public final void initMenuWithBackButton(
      SliderMenuTitle sliderMenuTitle, int iPosX, int iPosY, int iWidth, int iHeight, List<MenuElement> menuElements, boolean visible, boolean closeable
   ) {
      this.initMenu(sliderMenuTitle, iPosX, iPosY, iWidth, iHeight, menuElements, visible, true, closeable);
   }

   public final void initMenu(
      SliderMenuTitle sliderMenuTitle,
      int iPosX,
      int iPosY,
      int iWidth,
      int iHeight,
      List<MenuElement> menuElements,
      boolean visible,
      boolean backButton,
      boolean closeable
   ) {
      this.iMenuPosX = this.iNewMenuPositionX = iPosX;
      this.iPosX = this.iNewMenuPositionX;
      this.iMenuPosY = this.iNewMenuPositionY = iPosY;
      this.iPosY = this.iNewMenuPositionY;
      this.iWidth = iWidth;
      this.iHeight = iHeight;
      this.closeable = closeable;
      this.visible = visible;
      this.sliderMenuTitle = sliderMenuTitle;
      this.iMenuElementsSize = menuElements.size();
      if (backButton) {
         int tempMaxY = 0;

         for (int i = 0; i < this.iMenuElementsSize; i++) {
            if (menuElements.get(i).getPosY() + menuElements.get(i).getHeight() > tempMaxY) {
               tempMaxY = menuElements.get(i).getPosY() + menuElements.get(i).getHeight();
            }
         }

         menuElements.get(0).setPosY(tempMaxY + CFG.PADDING);
         if (tempMaxY > iHeight - CFG.PADDING - menuElements.get(0).getHeight()) {
            menuElements.get(0).setPosY(tempMaxY + CFG.PADDING);
         } else {
            menuElements.get(0).setPosY(iHeight - menuElements.get(0).getHeight());
         }
      }

      this.menuElements = menuElements;
      this.updateScrollable();
      this.updateMenuElements_IsInView();
   }

   public final void updateScrollable() {
      this.iMaxSliderPositionX = 0;
      this.iMaxSliderPositionY = 0;

      for (int i = 0; i < this.iMenuElementsSize; i++) {
         if (this.menuElements.get(i).getPosY() + this.menuElements.get(i).getHeight() > this.iMaxSliderPositionY) {
            this.iMaxSliderPositionY = this.menuElements.get(i).getPosY() + this.menuElements.get(i).getHeight();
         }

         if (this.menuElements.get(i).getPosX() + this.menuElements.get(i).getWidth() > this.iMaxSliderPositionX) {
            this.iMaxSliderPositionX = this.menuElements.get(i).getPosX() + this.menuElements.get(i).getWidth();
         }
      }

      this.scrollableX = this.iMaxSliderPositionX > this.getWidth();
      boolean bl = this.scrollableY = this.iMaxSliderPositionY > this.iHeight;
      if (this.scrollableY) {
         this.updateMenuPosY(this.iPosY);
      }

      if (this.scrollableX) {
         this.updateMenuPosX(this.iPosX);
      }
   }

   public void updateLanguage() {
   }

   public void update() {
      if (this.scrollModeY) {
         if (Math.abs(this.fScrollNewMenuPosY) > 1.0F) {
            this.updateMenuPosY(this.iMenuPosY + (int)this.fScrollNewMenuPosY);
            this.fScrollNewMenuPosY *= 0.97F;
         } else {
            this.scrollModeY = false;
         }

         CFG.setRender_3(true);
      }

      if (this.scrollModeX) {
         if (Math.abs(this.fScrollNewMenuPosX) > 1.0F) {
            this.updateMenuPosX(this.iMenuPosX + (int)this.fScrollNewMenuPosX);
            this.fScrollNewMenuPosX *= 0.97F;
         } else {
            this.scrollModeX = false;
         }

         CFG.setRender_3(true);
      }

      if (this.scrollableX && this.iNewMenuPositionX != this.iMenuPosX) {
         this.iMenuPosX = this.iNewMenuPositionX;
         this.updateMenuElements_IsInView();
         CFG.setRender_3(true);
      }

      if (this.iNewMenuPositionY != this.iMenuPosY) {
         this.iMenuPosY = this.iNewMenuPositionY;
         this.updateMenuElements_IsInView();
         CFG.setRender_3(true);
      }
   }

   public void extraAction() {
   }

   public void draw(SpriteBatch oSB, int iTranslateX, boolean sliderMenuIsActive) {
      this.draw(oSB, iTranslateX, 0, sliderMenuIsActive);
   }

   public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      this.beginClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
      this.drawMenu(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
      this.endClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
   }

   public void beginClip(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      this.drawBackgroundMode(oSB, sliderMenuIsActive);
      Rectangle clipBounds = new Rectangle(this.getPosX() + iTranslateX, CFG.GAME_HEIGHT - this.getPosY() - iTranslateY, this.getWidth(), -this.getHeight());
      oSB.flush();
      ScissorStack.pushScissors(clipBounds);
   }

   public final void drawMenu(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      this.drawMenuElements(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
      this.drawScrollPos(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
   }

   public final void endClip(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      try {
         oSB.flush();
         ScissorStack.popScissors();
      } catch (IllegalStateException var6) {
      }

      this.drawTitle(oSB, iTranslateX, iTranslateY, sliderMenuIsActive, this.getPosY());
      if (this.getCloseable()) {
         this.drawCloseButton(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
      }
   }

   public final void drawHover(SpriteBatch oSB, int iTranslateX, int iTranslateY, int nMenuElementID) {
      try {
         this.getMenuElement(nMenuElementID)
            .drawMenuElementHover2(
               oSB,
               this.getMenuPosX() + iTranslateX,
               this.getMenuPosY() + iTranslateY,
               this.getMenuElementIsActive(true, CFG.menuManager.getActiveMenuElementID())
            );
      } catch (IndexOutOfBoundsException var6) {
      }
   }

   public void drawScrollPos(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      try {
         if (this.scrollableY && this.getHeight() < this.iMaxSliderPositionY) {
            oSB.setColor(new Color(0.22F, 0.22F, 0.3F, 1.0F));
            ImageManager.getImage(Images.scroll_posiotion)
               .draw2(
                  oSB,
                  this.getPosX() + this.getWidth() - CFG.PADDING * 2 + 1 + iTranslateX,
                  this.getPosY() - ImageManager.getImage(Images.scroll_posiotion).getHeight() + iTranslateY,
                  ImageManager.getImage(Images.scroll_posiotion).getWidth(),
                  this.getHeight() - ImageManager.getImage(Images.scroll_posiotion).getHeight()
               );
            ImageManager.getImage(Images.scroll_posiotion)
               .draw(
                  oSB,
                  this.getPosX() + this.getWidth() - CFG.PADDING * 2 + 1 + iTranslateX,
                  this.getPosY() + this.getHeight() - ImageManager.getImage(Images.scroll_posiotion).getHeight() + iTranslateY,
                  false,
                  true
               );
            if (CFG.menuManager.getSliderMenuMode()) {
               oSB.setColor(new Color(0.0F, 0.0F, 0.08F, 1.0F));
            } else {
               oSB.setColor(new Color(0.098F, 0.098F, 0.16F, 1.0F));
            }

            ImageManager.getImage(Images.scroll_posiotion_active)
               .draw2(
                  oSB,
                  this.getPosX() + this.getWidth() - CFG.PADDING * 2 + iTranslateX + 1,
                  this.getPosY()
                     + (this.getHeight() - 100 * this.getHeight() / this.iMaxSliderPositionY * this.getHeight() / 100)
                        * (this.getPosY() - this.getMenuPosY())
                        / (this.iMaxSliderPositionY - this.getHeight())
                     - ImageManager.getImage(Images.scroll_posiotion_active).getHeight()
                     + iTranslateY,
                  CFG.PADDING * 2 - 2,
                  this.getHeight() * 100 / this.iMaxSliderPositionY * this.getHeight() / 100
                     - ImageManager.getImage(Images.scroll_posiotion_active).getHeight()
               );
            ImageManager.getImage(Images.scroll_posiotion_active)
               .draw(
                  oSB,
                  this.getPosX() + this.getWidth() - CFG.PADDING * 2 + iTranslateX + 1,
                  this.getPosY()
                     + (this.getHeight() - 100 * this.getHeight() / this.iMaxSliderPositionY * this.getHeight() / 100)
                        * (this.getPosY() - this.getMenuPosY())
                        / (this.iMaxSliderPositionY - this.getHeight())
                     + this.getHeight() * 100 / this.iMaxSliderPositionY * this.getHeight() / 100
                     - ImageManager.getImage(Images.scroll_posiotion_active).getHeight()
                     + iTranslateY,
                  false,
                  true
               );
            oSB.setColor(Color.WHITE);
         }
      } catch (ArithmeticException var6) {
      }
   }

   public final void drawMenuElements(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      for (int i = this.iMenuElementsSize - 1; i >= 0; i--) {
         if (this.menuElements.get(i).getVisible() && this.menuElements.get(i).getIsInView()) {
            this.menuElements
               .get(i)
               .draw(
                  oSB, this.getMenuPosX() + iTranslateX, this.getMenuPosY() + iTranslateY, this.getMenuElementIsActive(sliderMenuIsActive, i), this.scrollableY
               );
         }
      }
   }

   public void updateMenuElements_IsInView() {
      for (int i = 0; i < this.iMenuElementsSize; i++) {
         this.menuElements.get(i).setIsInView(this.getMenuElementIsInView(i));
      }
   }

   public void updateMenuElements_IsInView_X() {
      for (int i = 0; i < this.iMenuElementsSize; i++) {
         this.menuElements.get(i).setIsInView(this.getMenuElementIsInView_X(i));
      }
   }

   public void updateMenuElements_IsInView_XY() {
      for (int i = 0; i < this.iMenuElementsSize; i++) {
         this.menuElements.get(i).setIsInView(this.getMenuElementIsInView(i) && this.getMenuElementIsInView_X(i));
      }
   }

   public final boolean getMenuElementIsInView(int i) {
      return this.menuElements.get(i).getPosY() + this.getMenuPosY() > this.getPosY()
            && this.menuElements.get(i).getPosY() + this.getMenuPosY() < this.getPosY() + this.getHeight()
         || this.menuElements.get(i).getPosY() + this.menuElements.get(i).getHeight() + this.getMenuPosY() > this.getPosY()
            && this.menuElements.get(i).getPosY() + this.menuElements.get(i).getHeight() + this.getMenuPosY() < this.getPosY() + this.getHeight();
   }

   public final boolean getMenuElementIsInView_X(int i) {
      return this.menuElements.get(i).getPosX() + this.getMenuPosX() >= this.getPosX()
            && this.menuElements.get(i).getPosX() + this.getMenuPosX() <= this.getPosX() + this.getWidth()
         || this.menuElements.get(i).getPosX() + this.menuElements.get(i).getWidth() + this.getMenuPosX() >= this.getPosX()
            && this.menuElements.get(i).getPosX() + this.menuElements.get(i).getWidth() + this.getMenuPosX() <= this.getPosX() + this.getWidth();
   }

   public boolean getMenuElementIsActive(boolean sliderMenuIsActive, int i) {
      return sliderMenuIsActive ? i == CFG.menuManager.getActiveMenuElementID() : false;
   }

   public void drawTitle(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive, int nPosY) {
      if (this.sliderMenuTitle != null) {
         this.sliderMenuTitle.draw(oSB, iTranslateX, this.getPosX(), nPosY + iTranslateY, this.getWidth(), sliderMenuIsActive);
      }

      if (sliderMenuIsActive) {
         if (CFG.menuManager.getSliderMenuResizeMode()) {
            this.drawMenuBorder(oSB);
            this.drawMenuResizeRect(oSB);
         } else if (CFG.menuManager.getSliderMenuTitleMode()) {
            this.drawMenuBorder(oSB);
         }
      }
   }

   public final void drawMenuBorder(SpriteBatch oSB) {
      oSB.setColor(0.196F, 0.196F, 0.196F, 1.0F);
      ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX(), this.getPosY(), 1, this.getHeight());
      ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() + this.getWidth() - 1, this.getPosY(), 1, this.getHeight());
      ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX(), this.getPosY(), this.getWidth(), -1);
      ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX(), this.getPosY() + this.getHeight() - 1, this.getWidth(), -1);
      oSB.setColor(Color.WHITE);
   }

   public final void drawMenuResizeRect(SpriteBatch oSB) {
      oSB.setColor(0.196F, 0.196F, 0.196F, 0.95F);
      if (CFG.menuManager.getSliderMenuResizeLEFT()) {
         ImageManager.getImage(Images.pix255_255_255)
            .draw(oSB, this.getPosX(), this.getPosY() + this.getHeight() - 1 - CFG.PADDING * 6, CFG.PADDING * 6, CFG.PADDING * 6);
         oSB.setColor(new Color(1.0F, 1.0F, 1.0F, 0.35F));
         ImageManager.getImage(Images.pickerEdge)
            .draw(
               oSB,
               this.getPosX(),
               this.getPosY() + this.getHeight() - ImageManager.getImage(Images.pickerEdge).getHeight() * 2,
               ImageManager.getImage(Images.pickerEdge).getWidth(),
               ImageManager.getImage(Images.pickerEdge).getHeight(),
               true,
               false
            );
      } else {
         ImageManager.getImage(Images.pix255_255_255)
            .draw(
               oSB,
               this.getPosX() + this.getWidth() - 1 - CFG.PADDING * 6,
               this.getPosY() + this.getHeight() - 1 - CFG.PADDING * 6,
               CFG.PADDING * 6,
               CFG.PADDING * 6
            );
         oSB.setColor(new Color(1.0F, 1.0F, 1.0F, 0.35F));
         ImageManager.getImage(Images.pickerEdge)
            .draw(
               oSB,
               this.getPosX() + this.getWidth() - ImageManager.getImage(Images.pickerEdge).getWidth(),
               this.getPosY() + this.getHeight() - ImageManager.getImage(Images.pickerEdge).getHeight() * 2,
               ImageManager.getImage(Images.pickerEdge).getWidth(),
               ImageManager.getImage(Images.pickerEdge).getHeight(),
               false,
               false
            );
      }

      oSB.setColor(Color.WHITE);
   }

   public final void drawBackgroundMode(SpriteBatch oSB, boolean sliderMenuIsActive) {
      if (sliderMenuIsActive && (CFG.menuManager.getSliderMenuResizeMode() || CFG.menuManager.getSliderMenuTitleMode())) {
         oSB.setColor(new Color(0.1F, 0.1F, 0.1F, 0.5F));
         ImageManager.getImage(Images.patt).draw2(oSB, 0, -ImageManager.getImage(Images.patt).getHeight(), CFG.GAME_WIDTH, CFG.GAME_HEIGHT);
         oSB.setColor(Color.WHITE);
      }
   }

   public void drawCloseButton(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      this.getCloseButtonImage(sliderMenuIsActive)
         .draw(
            oSB,
            this.getPosX() + this.getWidth() - ImageManager.getImage(Images.btn_close).getWidth() + iTranslateX,
            this.getPosY() - this.sliderMenuTitle.getHeight() + iTranslateY
         );
   }

   public final Image getCloseButtonImage(boolean sliderMenuIsActive) {
      return CFG.menuManager.getSliderMenuCloseMode() && sliderMenuIsActive
         ? ImageManager.getImage(Images.btnh_close)
         : ImageManager.getImage(Images.btn_close);
   }

   public void actionElement(int nMenuElementID) {
   }

   public void onBackPressed() {
   }

   public void onMenuPressed() {
   }

   public void actionClose() {
      this.setVisible(false);
   }

   public void onHovered() {
   }

   public final void updateMenuPosX(int nMenuPosX) {
      try {
         if (nMenuPosX > this.getPosX()) {
            this.iNewMenuPositionX = this.getPosX();
            CFG.menuManager.setUpdateSliderMenuPosX(true);
         } else if (nMenuPosX < this.getWidth() + this.getPosX() - this.iMaxSliderPositionX) {
            this.iNewMenuPositionX = this.getWidth() + this.getPosX() - this.iMaxSliderPositionX;
            CFG.menuManager.setUpdateSliderMenuPosX(true);
         } else {
            this.iNewMenuPositionX = nMenuPosX;
         }
      } catch (NullPointerException var3) {
      }
   }

   public final void updateMenuPosY(int nMenuPosY) {
      try {
         if (nMenuPosY > this.getPosY()) {
            this.iNewMenuPositionY = this.getPosY();
            CFG.menuManager.setUpdateSliderMenuPosY(true);
            this.scrollModeY = false;
         } else if (nMenuPosY < this.getHeight() + this.getPosY() - this.iMaxSliderPositionY) {
            this.iNewMenuPositionY = this.getHeight() + this.getPosY() - this.iMaxSliderPositionY;
            CFG.menuManager.setUpdateSliderMenuPosY(true);
            this.scrollModeY = false;
         } else {
            this.iNewMenuPositionY = nMenuPosY;
         }
      } catch (NullPointerException var3) {
      }
   }

   public final void scrollTheMenu() {
      if (this.scrollableY && this.iScrollPosY > 0 && this.iScrollPosY2 > 0 && Math.abs(this.iScrollPosY - this.iScrollPosY2) > 3.0F * CFG.DENSITY) {
         this.fScrollNewMenuPosY = (this.iScrollPosY - this.iScrollPosY2) * 1.45F;
         this.scrollModeY = true;
      }

      if (this.scrollableX && this.iScrollPosX > 0 && this.iScrollPosX2 > 0 && Math.abs(this.iScrollPosX - this.iScrollPosX2) > 3) {
         this.fScrollNewMenuPosX = (this.iScrollPosX - this.iScrollPosX2) * 1.45F;
         this.scrollModeX = true;
      }

      this.resetScrollINFO();
   }

   public final void resetScrollINFO() {
      this.iScrollPosX2 = -1;
      this.iScrollPosX = -1;
      this.iScrollPosY2 = -1;
      this.iScrollPosY = -1;
   }

   public final void stopScrolling() {
      this.resetScrollINFO();
      this.scrollModeX = false;
      this.scrollModeY = false;
   }

   public final void updatedButtonsWidth(int iStartPosX, int iMinWidth) {
      for (int i = 0; i < this.getMenuElementsSize(); i++) {
         iStartPosX += this.updateButtonWidth(i, iStartPosX, iMinWidth) + CFG.PADDING;
      }

      this.updateScrollable();
   }

   public final void updatedButtonsWidth_Padding(int iStartPosX, int iMinWidth, int iPadding) {
      for (int i = 0; i < this.getMenuElementsSize(); i++) {
         iStartPosX += this.updateButtonWidth(i, iStartPosX, iMinWidth) + iPadding;
      }

      this.updateScrollable();
   }

   public final void updatedButtonsWidthFromToID(int iStartButtonID, int iEndButtonID, int iStartPosX, int iMinWidth) {
      for (int i = iStartButtonID; i < iEndButtonID; i++) {
         iStartPosX += this.updateButtonWidth(i, iStartPosX, iMinWidth) + CFG.PADDING;
      }

      this.updateScrollable();
   }

   public final int updateButtonWidth(int iButtonID, int iStartPosX, int iMinWidth) {
      if (this.getMenuElement(iButtonID).getTextWidth() + CFG.PADDING * 4 > iMinWidth) {
         this.getMenuElement(iButtonID).setWidth(this.getMenuElement(iButtonID).getTextWidth() + CFG.PADDING * 4);
      } else {
         this.getMenuElement(iButtonID).setWidth(iMinWidth);
      }

      this.getMenuElement(iButtonID).setPosX(iStartPosX);
      this.updateScrollable();
      return this.getMenuElement(iButtonID).getWidth();
   }

   public final int getMenuElementsSize() {
      return this.iMenuElementsSize;
   }

   public final MenuElement getMenuElement(int iID) {
      return this.menuElements.get(iID);
   }

   public final void setMenuElement(int iID, MenuElement nMenuElement) {
      this.menuElements.set(iID, null);
      this.menuElements.set(iID, nMenuElement);
   }

   public int getPosX() {
      return this.iPosX;
   }

   public void setPosX(int iPosX) {
      this.iPosX = iPosX;
      this.iMenuPosX = iPosX;
      this.updateMenuPosX(this.iMenuPosX);
   }

   public final void setPosX_Force(int iPosX) {
      this.iPosX = iPosX;
      this.iMenuPosX = iPosX;
      this.iNewMenuPositionX = iPosX;
      CFG.menuManager.setUpdateSliderMenuPosX(false);
   }

   public int getPosY() {
      return this.iPosY;
   }

   public void setPosY(int iPosY) {
      this.iPosY = iPosY;
      this.iMenuPosY = iPosY;
      this.updateMenuPosY(this.iMenuPosY);
   }

   public int getWidth() {
      return this.iWidth;
   }

   public boolean setWidth(int iWidth) {
      if (iWidth < CFG.GAME_WIDTH) {
         if (iWidth >= this.getMinWidth()) {
            this.iWidth = iWidth;
            return true;
         } else {
            this.iWidth = this.getMinWidth();
            return false;
         }
      } else {
         this.iWidth = CFG.GAME_WIDTH;
         return true;
      }
   }

   public final int getMinWidth() {
      try {
         return CFG.PADDING * 2;
      } catch (NullPointerException var2) {
         return CFG.PADDING * 2;
      }
   }

   public int getHeight() {
      return this.iHeight;
   }

   public void setHeight(int iHeight) {
      this.iHeight = iHeight;
      if (iHeight < this.getMinHeight()) {
         this.iHeight = this.getMinHeight();
      }

      if (iHeight + this.getPosY() + (this.sliderMenuTitle != null ? this.sliderMenuTitle.getHeight() : 0) >= CFG.GAME_HEIGHT) {
         this.iHeight = CFG.GAME_HEIGHT - (this.getPosY() + (this.sliderMenuTitle != null ? this.sliderMenuTitle.getHeight() : 0));
      }

      this.updateScrollable();
   }

   public final int getMinHeight() {
      return CFG.PADDING + CFG.BUTTON_HEIGHT;
   }

   public final SliderMenuTitle getTitle() {
      return this.sliderMenuTitle;
   }

   public final boolean getScrollableY() {
      return this.scrollableY;
   }

   public final void setMenuPosY(int iMenuPosY) {
      this.updateMenuPosY(iMenuPosY);
   }

   public int getMenuPosY() {
      return this.iMenuPosY;
   }

   public final int getNewMenuPosY() {
      return this.iNewMenuPositionY;
   }

   public final int getNewMenuPosX() {
      return this.iNewMenuPositionX;
   }

   public final boolean getScrollableX() {
      return this.scrollableX;
   }

   public final void setMenuPosX(int iMenuPosX) {
      this.updateMenuPosX(iMenuPosX);
   }

   public int getMenuPosX() {
      return this.iMenuPosX;
   }

   public boolean getVisible() {
      return this.visible;
   }

   public void setVisible(boolean visible) {
      this.visible = visible;
   }

   public final boolean getCloseable() {
      return this.closeable;
   }

   public final boolean getMoveable() {
      return this.sliderMenuTitle == null ? false : this.sliderMenuTitle.getMoveable();
   }

   public final boolean getResizable() {
      return this.sliderMenuTitle == null ? false : this.sliderMenuTitle.getResizable();
   }

   public final void setScrollPosY(int iScrollPosY) {
      this.iScrollPosY2 = this.iScrollPosY;
      this.iScrollPosY = iScrollPosY;
   }

   public final int getScrollPosY() {
      return this.iScrollPosY;
   }

   public final void setScrollPosX(int iScrollPosX) {
      this.iScrollPosX2 = this.iScrollPosX;
      this.iScrollPosX = iScrollPosX;
   }

   public final boolean getScrollModeY() {
      return this.scrollModeY;
   }
}
