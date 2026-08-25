package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Button_Menu extends Button {
   public static long lTimeAnimation = 0L;
   public static int animationState = 0;
   public static final int ANIMATION_T = 750;

   public Button_Menu(String sText, int iTextPositionX, int iPosX, int iPosY, int iWidth, int iHeight, boolean isClickable) {
      super.init(sText, iTextPositionX, iPosX, iPosY, iWidth, iHeight, isClickable, true, false, false, null);
   }

   public Button_Menu(String sText, int iTextPositionX, int iPosX, int iPosY, int iWidth, int iHeight, boolean isClickable, boolean checkboxState) {
      super.init(sText, iTextPositionX, iPosX, iPosY, iWidth, iHeight, isClickable, true, true, checkboxState, null);
   }

   @Override
   public void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
      if (isActive) {
         ImageManager.getImage(Images.btnh_menu_h).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth());
      } else if (this.getIsHovered() && this.getClickable()) {
         oSB.setColor(CFG.COLOR_BUTTON_MENU_HOVER_BG);
         ImageManager.getImage(Images.btn_menu_h).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth());
         oSB.setColor(Color.WHITE);
      } else {
         ImageManager.getImage(Images.btn_menu_h).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth());
      }

      if (this.getClickable() && this.getIsHovered() && !isActive && animationState >= 0) {
         if (animationState == 0) {
            float drawPerc = Math.min(1.0F * (float)(System.currentTimeMillis() - lTimeAnimation) / 750.0F, 1.0F);
            oSB.setColor(getColorLine());
            ImageManager.getImage(Images.line_32_off1)
               .draw(
                  oSB,
                  this.getPosX() + CFG.PADDING + iTranslateX,
                  this.getPosY() + 1 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
                  (int)((this.getWidth() - CFG.PADDING * 2) * drawPerc),
                  1
               );
            ImageManager.getImage(Images.line_32_off1)
               .draw(
                  oSB,
                  this.getPosX() + CFG.PADDING + iTranslateX,
                  this.getPosY() + this.getHeight() - 2 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
                  (int)((this.getWidth() - CFG.PADDING * 2) * drawPerc),
                  1
               );
            if (lTimeAnimation < System.currentTimeMillis() - 750L) {
               animationState++;
               lTimeAnimation = System.currentTimeMillis();
            }
         } else {
            float drawPerc = Math.min(1.0F * (float)(System.currentTimeMillis() - lTimeAnimation) / 750.0F, 1.0F);
            oSB.setColor(getColorLine());
            ImageManager.getImage(Images.line_32_off1)
               .draw(
                  oSB,
                  this.getPosX() + CFG.PADDING + (int)((this.getWidth() - CFG.PADDING * 2) * drawPerc) + iTranslateX,
                  this.getPosY() + 1 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
                  this.getWidth() - CFG.PADDING * 2 - (int)((this.getWidth() - CFG.PADDING * 2) * drawPerc),
                  1
               );
            ImageManager.getImage(Images.line_32_off1)
               .draw(
                  oSB,
                  this.getPosX() + CFG.PADDING + (int)((this.getWidth() - CFG.PADDING * 2) * drawPerc) + iTranslateX,
                  this.getPosY() + this.getHeight() - 2 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
                  this.getWidth() - CFG.PADDING * 2 - (int)((this.getWidth() - CFG.PADDING * 2) * drawPerc),
                  1
               );
            if (lTimeAnimation < System.currentTimeMillis() - 750L) {
               animationState = 0;
               lTimeAnimation = System.currentTimeMillis();
            }
         }

         CFG.setRender_3(true);
         oSB.setColor(Color.WHITE);
      }
   }

   @Override
   public Color getColor(boolean isActive) {
      return isActive
         ? CFG.COLOR_BUTTON_MENU_TEXT_ACTIVE
         : (
            this.getClickable()
               ? (this.getIsHovered() ? CFG.COLOR_BUTTON_MENU_TEXT_HOVERED : CFG.COLOR_BUTTON_MENU_TEXT)
               : CFG.COLOR_BUTTON_MENU_TEXT_NOT_CLICKABLE
         );
   }

   @Override
   public void setIsHovered(boolean isHovered) {
      super.setIsHovered(isHovered);
      lTimeAnimation = System.currentTimeMillis();
      animationState = 0;
   }

   public static final Color getColorLine() {
      return new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.675F);
   }
}
