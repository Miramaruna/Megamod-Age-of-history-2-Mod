package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Button_Menu_LR_MainMenu extends Button_Menu {
   public boolean freePlayButton;

   public Button_Menu_LR_MainMenu(String sText, int iTextPositionX, int iPosX, int iPosY, int iWidth, int iHeight, boolean isClickable) {
      super(sText, iTextPositionX, iPosX, iPosY, iWidth, iHeight, isClickable);
   }

   public Button_Menu_LR_MainMenu(String sText, int iTextPositionX, int iPosX, int iPosY, int iWidth, int iHeight, boolean isClickable, boolean checkboxState) {
      super(sText, iTextPositionX, iPosX, iPosY, iWidth, iHeight, isClickable, checkboxState);
   }

   @Override
   public void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
      oSB.setColor(1.0F, 1.0F, 1.0F, 0.55F);
      if (!this.getClickable()) {
         oSB.setColor(new Color(1.0F, 1.0F, 1.0F, 0.4F));
         ImageManager.getImage(Images.btn_menu_h)
            .draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth() - ImageManager.getImage(Images.btn_menu_h).getWidth());
         ImageManager.getImage(Images.btn_menu_h)
            .draw(oSB, this.getPosX() + this.getWidth() - ImageManager.getImage(Images.btn_menu_h).getWidth() + iTranslateX, this.getPosY() + iTranslateY, true);
      } else if (isActive) {
         ImageManager.getImage(Images.btnh_menu_h)
            .draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth() - ImageManager.getImage(Images.btnh_menu_h).getWidth());
         ImageManager.getImage(Images.btnh_menu_h)
            .draw(
               oSB, this.getPosX() + this.getWidth() - ImageManager.getImage(Images.btnh_menu_h).getWidth() + iTranslateX, this.getPosY() + iTranslateY, true
            );
      } else if (this.getIsHovered() && this.getClickable()) {
         oSB.setColor(new Color(1.0F, 1.0F, 1.0F, 0.485F));
         ImageManager.getImage(Images.btn_menu_h)
            .draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth() - ImageManager.getImage(Images.btn_menu_h).getWidth());
         ImageManager.getImage(Images.btn_menu_h)
            .draw(oSB, this.getPosX() + this.getWidth() - ImageManager.getImage(Images.btn_menu_h).getWidth() + iTranslateX, this.getPosY() + iTranslateY, true);
      } else {
         ImageManager.getImage(Images.btn_menu_h)
            .draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth() - ImageManager.getImage(Images.btn_menu_h).getWidth());
         ImageManager.getImage(Images.btn_menu_h)
            .draw(oSB, this.getPosX() + this.getWidth() - ImageManager.getImage(Images.btn_menu_h).getWidth() + iTranslateX, this.getPosY() + iTranslateY, true);
      }

      if (this.getClickable() && this.getIsHovered() && !isActive && animationState >= 0 || this.freePlayButton) {
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
}
