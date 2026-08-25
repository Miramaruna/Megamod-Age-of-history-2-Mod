package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Button_FlagActionSliderStyle_Animated extends Button_FlagActionSliderStyle {
   public static long lTimeAnimation = 0L;
   public static int animationState = 0;
   public static final int ANIMATION_T = 750;

   public Button_FlagActionSliderStyle_Animated(String sText, int iTextPositionX, int iPosX, int iPosY, int iWidth, boolean isClickable) {
      super(sText, iTextPositionX, iPosX, iPosY, iWidth, isClickable);
   }

   public Button_FlagActionSliderStyle_Animated(String sText, int iTextPositionX, int iPosX, int iPosY, int iWidth, int iHeight, boolean isClickable) {
      super(sText, iTextPositionX, iPosX, iPosY, iWidth, iHeight, isClickable);
   }

   @Override
   public void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
      super.drawButtonBG(oSB, iTranslateX, iTranslateY, isActive);
      if (this.getClickable() && !isActive && animationState >= 0) {
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

   public static final Color getColorLine() {
      return new Color(CFG.COLOR_GRADIENT_TITLE_BLUE.r, CFG.COLOR_GRADIENT_TITLE_BLUE.g, CFG.COLOR_GRADIENT_TITLE_BLUE.b, 0.975F);
   }
}
