package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Button_Diplomacy_Demand extends Button_Statistics {
   public static final float FONT_SCALE = 0.7F;
   public int iCivA;

   public Button_Diplomacy_Demand(String sText, int nCiv, int iPosX, int iPosY, int iWidth) {
      super(
         sText,
         0,
         iPosX,
         iPosY,
         iWidth,
         CFG.isAndroid() ? (int)Math.max(CFG.BUTTON_HEIGHT * 0.6F, (float)(CFG.TEXT_HEIGHT + CFG.PADDING * 6)) : CFG.TEXT_HEIGHT + CFG.PADDING * 4,
         false
      );
      this.iCivA = nCiv;
      this.row = !this.row;
   }

   @Override
   public Button.Checkbox buildCheckbox() {
      return new Button.Checkbox() {
         @Override
         public void drawCheckBox(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean scrollableY) {
         }
      };
   }

   @Override
   public void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
      oSB.setColor(new Color(CFG.COLOR_GRADIENT_TITLE_BLUE.r, CFG.COLOR_GRADIENT_TITLE_BLUE.g, CFG.COLOR_GRADIENT_TITLE_BLUE.b, 0.25F));
      ImageManager.getImage(Images.line_32_off1)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
            this.getWidth(),
            this.getHeight()
         );
      oSB.setColor(Color.WHITE);
      oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.55F));
      ImageManager.getImage(Images.gradient)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY,
            this.getWidth(),
            this.getHeight() * 3 / 5,
            false,
            false
         );
      oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.275F));
      ImageManager.getImage(Images.slider_gradient)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY,
            this.getWidth() / 4,
            this.getHeight(),
            false,
            false
         );
      ImageManager.getImage(Images.slider_gradient)
         .draw(
            oSB,
            this.getPosX() + this.getWidth() - this.getWidth() / 4 + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY,
            this.getWidth() / 4,
            this.getHeight(),
            true,
            false
         );
      super.drawButtonBG(oSB, iTranslateX, iTranslateY, isActive);
      oSB.setColor(new Color(CFG.COLOR_GRADIENT_TITLE_BLUE.r, CFG.COLOR_GRADIENT_TITLE_BLUE.g, CFG.COLOR_GRADIENT_TITLE_BLUE.b, 0.3F));
      ImageManager.getImage(Images.gradient)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY,
            this.getWidth(),
            CFG.PADDING,
            false,
            false
         );
      ImageManager.getImage(Images.gradient)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() + this.getHeight() - CFG.PADDING - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY,
            this.getWidth(),
            CFG.PADDING,
            false,
            true
         );
      oSB.setColor(new Color(CFG.COLOR_GRADIENT_TITLE_BLUE.r, CFG.COLOR_GRADIENT_TITLE_BLUE.g, CFG.COLOR_GRADIENT_TITLE_BLUE.b, 0.45F));
      ImageManager.getImage(Images.line_32_off1)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() + this.getHeight() - 1 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
            this.getWidth() - 4,
            1
         );
      oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.7F));
      ImageManager.getImage(Images.line_32_off1)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() + this.getHeight() - 2 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
            this.getWidth() - 4,
            1
         );
      oSB.setColor(Color.WHITE);
   }

   @Override
   public void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
      try {
         oSB.setColor(
            new Color(
               CFG.game.getCiv(this.iCivA).getR() / 255.0F,
               CFG.game.getCiv(this.iCivA).getG() / 255.0F,
               CFG.game.getCiv(this.iCivA).getB() / 255.0F,
               this.getCheckboxState() ? 1.0F : 0.55F
            )
         );
      } catch (IndexOutOfBoundsException var6) {
         oSB.setColor(
            new Color(
               CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_ALLIANCE.getR(),
               CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_ALLIANCE.getG(),
               CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_ALLIANCE.getB(),
               this.getCheckboxState() ? 1.0F : 0.55F
            )
         );
      }

      ImageManager.getImage(Images.pix255_255_255)
         .draw(
            oSB,
            this.getPosX() + CFG.PADDING * 2 + iTranslateX,
            this.getPosY()
               + this.getHeight() / 2
               - (int)(ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect)) / 2
               - ImageManager.getImage(Images.pix255_255_255).getHeight()
               + iTranslateY,
            2,
            (int)(ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect))
         );
      if (this.getCheckboxState()) {
         oSB.setColor(Color.WHITE);
      } else {
         oSB.setColor(new Color(1.0F, 1.0F, 1.0F, 0.55F));
      }

      CFG.game
         .getCiv(this.iCivA)
         .getFlag()
         .draw(
            oSB,
            this.getPosX() + CFG.PADDING * 2 + 2 + iTranslateX,
            this.getPosY()
               + this.getHeight() / 2
               - (int)(ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect)) / 2
               - CFG.game.getCiv(this.iCivA).getFlag().getHeight()
               + iTranslateY,
            (int)(ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(Images.flag_rect)),
            (int)(ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect))
         );
      ImageManager.getImage(Images.flag_rect)
         .draw(
            oSB,
            this.getPosX() + CFG.PADDING * 2 + 2 + iTranslateX,
            this.getPosY()
               + this.getHeight() / 2
               - (int)(ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect)) / 2
               - ImageManager.getImage(Images.flag_rect).getHeight()
               + iTranslateY,
            (int)(ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(Images.flag_rect)),
            (int)(ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect))
         );
      oSB.setColor(Color.WHITE);
      CFG.fontMain.getData().setScale(0.7F);
      CFG.drawTextWithShadow(
         oSB,
         this.getText(),
         this.getPosX() + CFG.PADDING * 3 + (int)(ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(Images.flag_rect)) + 2 + iTranslateX,
         this.getPosY() + this.getHeight() / 2 - (int)(CFG.TEXT_HEIGHT * 0.7F / 2.0F) + iTranslateY,
         this.getColor(isActive)
      );
      if (!this.getClickable()) {
         oSB.setColor(new Color(1.0F, 1.0F, 1.0F, 0.65F));
      }

      if (this.getCheckboxState()) {
         ImageManager.getImage(Images.icon_check_true)
            .draw(
               oSB,
               this.getPosX()
                  + this.getWidth()
                  - CFG.PADDING * 2
                  - (int)(ImageManager.getImage(Images.icon_check_true).getWidth() * this.getImageScale(Images.icon_check_true))
                  + iTranslateX,
               this.getPosY()
                  + this.getHeight() / 2
                  - (int)(ImageManager.getImage(Images.icon_check_true).getHeight() * this.getImageScale(Images.icon_check_true)) / 2
                  - ImageManager.getImage(Images.icon_check_true).getHeight()
                  + iTranslateY,
               (int)(ImageManager.getImage(Images.icon_check_true).getWidth() * this.getImageScale(Images.icon_check_true)),
               (int)(ImageManager.getImage(Images.icon_check_true).getHeight() * this.getImageScale(Images.icon_check_true))
            );
      } else {
         ImageManager.getImage(Images.icon_check_false)
            .draw(
               oSB,
               this.getPosX()
                  + this.getWidth()
                  - CFG.PADDING * 2
                  - (int)(ImageManager.getImage(Images.icon_check_false).getWidth() * this.getImageScale(Images.icon_check_false))
                  + iTranslateX,
               this.getPosY()
                  + this.getHeight() / 2
                  - (int)(ImageManager.getImage(Images.icon_check_false).getHeight() * this.getImageScale(Images.icon_check_false)) / 2
                  - ImageManager.getImage(Images.icon_check_false).getHeight()
                  + iTranslateY,
               (int)(ImageManager.getImage(Images.icon_check_false).getWidth() * this.getImageScale(Images.icon_check_false)),
               (int)(ImageManager.getImage(Images.icon_check_false).getHeight() * this.getImageScale(Images.icon_check_false))
            );
      }

      oSB.setColor(Color.WHITE);
      CFG.fontMain.getData().setScale(1.5F);
      oSB.setColor(Color.WHITE);
   }

   public final float getImageScale(int nImageID) {
      return (float)CFG.TEXT_HEIGHT / ImageManager.getImage(nImageID).getHeight() < 1.0F
         ? (float)CFG.TEXT_HEIGHT / ImageManager.getImage(nImageID).getHeight()
         : 1.0F;
   }

   @Override
   public Color getColor(boolean isActive) {
      return isActive
         ? CFG.COLOR_TEXT_OPTIONS_NS_ACTIVE
         : (
            this.getIsHovered()
               ? CFG.COLOR_TEXT_OPTIONS_NS_HOVER
               : (
                  this.getClickable()
                     ? (
                        this.getCheckboxState()
                           ? CFG.COLOR_TEXT_OPTIONS_NS
                           : new Color(CFG.COLOR_TEXT_OPTIONS_NS.r, CFG.COLOR_TEXT_OPTIONS_NS.g, CFG.COLOR_TEXT_OPTIONS_NS.b, 0.35F)
                     )
                     : new Color(CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.r, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.g, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.b, 0.35F)
               )
         );
   }

   @Override
   public int getSFX() {
      return SoundsManager.SOUND_CLICK2;
   }

   @Override
   public int getCurrent() {
      return this.iCivA;
   }
}
