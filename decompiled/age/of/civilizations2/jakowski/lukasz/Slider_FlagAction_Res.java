package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

class Slider_FlagAction_Res extends Slider {
   protected static final Color bgColor = new Color(0.0F, 0.0F, 0.0F, 0.3F);
   protected float FONT_SCALE = 0.8F;

   protected Slider_FlagAction_Res(String var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8) {
      this.initSlider(var1, var2, var3, var4, var5, var6, var7, var8);
   }

   protected Slider_FlagAction_Res(String var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, float var9) {
      this.initSlider(var1, var2, var3, var4, var5, var6, var7, var8);
      this.FONT_SCALE = var9;
   }

   @Override
   protected void draw(SpriteBatch var1, int var2, int var3, boolean var4, boolean var5) {
      this.drawSliderBG_UpdateAnimation();
      var1.setColor(new Color(CFG.COLOR_GRADIENT_TITLE_BLUE.r, CFG.COLOR_GRADIENT_TITLE_BLUE.g, CFG.COLOR_GRADIENT_TITLE_BLUE.b, 0.25F));
      Image var6 = ImageManager.getImage(Images.pix255_255_255);
      int var7 = this.getPosX();
      int var8 = CFG.PADDING;
      int var9 = this.getPosY();
      int var10 = ImageManager.getImage(Images.pix255_255_255).getHeight();
      int var11 = this.getWidth();
      var6.draw(var1, var7 - var8 + var2, var9 - var10 + var3, CFG.PADDING * 2 + var11, this.getHeight());
      var1.setColor(new Color(CFG.COLOR_GRADIENT_TITLE_BLUE.r, CFG.COLOR_GRADIENT_TITLE_BLUE.g, CFG.COLOR_GRADIENT_TITLE_BLUE.b, 0.8F));
      var6 = ImageManager.getImage(Images.patt);
      var8 = this.getPosX();
      var9 = CFG.PADDING;
      var7 = this.getPosY();
      var10 = ImageManager.getImage(Images.patt).getHeight();
      var11 = this.getWidth();
      var6.draw2(var1, var8 - var9 + var2, var7 - var10 + var3, CFG.PADDING * 2 + var11, this.getHeight());
      var1.setColor(new Color(0.0F, 0.0F, 0.0F, 0.55F));
      var6 = ImageManager.getImage(Images.gradient);
      var7 = this.getPosX();
      var9 = CFG.PADDING;
      var10 = this.getPosY();
      var8 = ImageManager.getImage(Images.gradient).getHeight();
      var11 = this.getWidth();
      var6.draw(var1, var7 - var9 + var2, var10 - var8 + var3, CFG.PADDING * 2 + var11, this.getHeight() * 3 / 5, false, false);
      var1.setColor(new Color(0.0F, 0.0F, 0.0F, 0.275F));
      ImageManager.getImage(Images.slider_gradient)
         .draw(
            var1,
            this.getPosX() - CFG.PADDING + var2,
            this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + var3,
            this.getWidth() / 4,
            this.getHeight(),
            false,
            false
         );
      ImageManager.getImage(Images.slider_gradient)
         .draw(
            var1,
            this.getPosX() - CFG.PADDING + this.getWidth() + CFG.PADDING * 2 - this.getWidth() / 4 + var2,
            this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + var3,
            this.getWidth() / 4,
            this.getHeight(),
            true,
            false
         );
      var1.setColor(new Color(CFG.COLOR_GRADIENT_TITLE_BLUE.r, CFG.COLOR_GRADIENT_TITLE_BLUE.g, CFG.COLOR_GRADIENT_TITLE_BLUE.b, 0.65F));
      var6 = ImageManager.getImage(Images.line_32_off1);
      var8 = this.getPosX();
      var9 = CFG.PADDING;
      var7 = this.getPosY();
      var11 = ImageManager.getImage(Images.line_32_off1).getHeight();
      var10 = this.getWidth();
      var6.draw(var1, var8 - var9 + var2, var7 + 1 - var11 + var3, CFG.PADDING * 2 + var10, 1);
      var6 = ImageManager.getImage(Images.line_32_off1);
      var11 = this.getPosX();
      var10 = CFG.PADDING;
      int var12 = this.getPosY();
      var9 = this.getHeight();
      var8 = ImageManager.getImage(Images.line_32_off1).getHeight();
      var7 = this.getWidth();
      var6.draw(var1, var11 - var10 + var2, var12 + var9 - 2 - var8 + var3, CFG.PADDING * 2 + var7, 1);
      var1.setColor(new Color(0.0F, 0.0F, 0.0F, 0.175F));
      ImageManager.getImage(Images.line_32_off1)
         .draw(
            var1,
            this.getPosX() + var2,
            this.getPosY() + this.getHeight() - CFG.PADDING - this.getSliderHeight() - ImageManager.getImage(Images.line_32_off1).getHeight() + var3,
            this.getWidth(),
            this.getSliderHeight()
         );
      var1.setColor(new Color(this.getColorLEFT().r, this.getColorLEFT().g, this.getColorLEFT().b, this.getColorLEFT().a));
      var6 = ImageManager.getImage(Images.pix255_255_255);
      var12 = this.getPosX();
      int var13 = this.getPosY();
      var10 = this.getHeight();
      var7 = CFG.PADDING;
      var11 = this.getSliderHeight();
      var8 = ImageManager.getImage(Images.pix255_255_255).getHeight();
      var9 = this.iCurrentPosX;
      var6.draw(var1, var12 + var2, var13 + var10 - var7 - var11 - var8 + var3, this.iDifference_CurrentPosX + var9, this.getSliderHeight());
      var6 = ImageManager.getImage(Images.gradient);
      var10 = this.getPosX();
      var7 = this.getPosY();
      var12 = this.getHeight();
      var9 = CFG.PADDING;
      var11 = this.getSliderHeight();
      var13 = ImageManager.getImage(Images.gradient).getHeight();
      var8 = this.iCurrentPosX;
      var6.draw(var1, var10 + var2, var7 + var12 - var9 - var11 - var13 + var3, this.iDifference_CurrentPosX + var8, this.getSliderHeight());
      var1.setColor(new Color(this.getColorLEFT().r, this.getColorLEFT().g, this.getColorLEFT().b, this.getColorLEFT().a * 0.92F));
      var6 = ImageManager.getImage(Images.line_32_off1);
      var11 = this.getPosX();
      var7 = this.getPosY();
      var13 = this.getHeight();
      var8 = CFG.PADDING;
      var10 = this.getSliderHeight();
      var12 = ImageManager.getImage(Images.line_32_off1).getHeight();
      var9 = this.iCurrentPosX;
      var6.draw(var1, var11 + var2, var7 + var13 - var8 - var10 - var12 + var3, this.iDifference_CurrentPosX + var9, this.getSliderHeight());

      for (int var40 = 1; var40 < 10; var40++) {
         var1.setColor(new Color(1.0F, 1.0F, 1.0F, 0.04F));
         ImageManager.getImage(Images.line_32_vertical)
            .draw2(
               var1,
               this.getPosX() + this.getWidth() / 10 * var40 + var2,
               this.getPosY() + this.getHeight() - CFG.PADDING - this.getSliderHeight() - ImageManager.getImage(Images.line_32_vertical).getHeight() + var3,
               1,
               this.getSliderHeight()
            );
      }

      var1.setColor(new Color(CFG.COLOR_NEW_GAME_EDGE_LINE.r, CFG.COLOR_NEW_GAME_EDGE_LINE.g, CFG.COLOR_NEW_GAME_EDGE_LINE.b, 0.675F));
      ImageManager.getImage(Images.line_32_off1)
         .draw(
            var1,
            this.getPosX() + this.iCurrentPosX + this.iDifference_CurrentPosX + var2,
            this.getPosY() + this.getHeight() - CFG.PADDING - this.getSliderHeight() + 1 - ImageManager.getImage(Images.line_32_off1).getHeight() + var3,
            1,
            this.getSliderHeight() - 2
         );
      var1.setColor(new Color(0.0F, 0.0F, 0.0F, 0.55F));
      ImageManager.getImage(Images.line_32_off1)
         .draw(
            var1,
            this.getPosX() - 1 + this.iCurrentPosX + this.iDifference_CurrentPosX + var2,
            this.getPosY() + this.getHeight() - CFG.PADDING - this.getSliderHeight() + 1 - ImageManager.getImage(Images.line_32_off1).getHeight() + var3,
            1,
            this.getSliderHeight() - 2
         );
      ImageManager.getImage(Images.line_32_off1)
         .draw(
            var1,
            this.getPosX() + 1 + this.iCurrentPosX + this.iDifference_CurrentPosX + var2,
            this.getPosY() + this.getHeight() - CFG.PADDING - this.getSliderHeight() + 1 - ImageManager.getImage(Images.line_32_off1).getHeight() + var3,
            1,
            this.getSliderHeight() - 2
         );
      var1.setColor(new Color(0.0F, 0.0F, 0.0F, 0.65F));
      ImageManager.getImage(Images.gradient)
         .draw(
            var1,
            this.getPosX() + var2,
            this.getPosY() + this.getHeight() - CFG.PADDING - this.getSliderHeight() - ImageManager.getImage(Images.gradient).getHeight() + var3,
            this.getWidth(),
            CFG.PADDING
         );
      ImageManager.getImage(Images.gradient)
         .draw(
            var1,
            this.getPosX() + var2,
            this.getPosY() + this.getHeight() - CFG.PADDING - CFG.PADDING + 1 - ImageManager.getImage(Images.gradient).getHeight() + var3,
            this.getWidth(),
            CFG.PADDING,
            false,
            true
         );
      var1.setColor(new Color(CFG.COLOR_NEW_GAME_EDGE_LINE.r, CFG.COLOR_NEW_GAME_EDGE_LINE.g, CFG.COLOR_NEW_GAME_EDGE_LINE.b, 0.7F));
      ImageManager.getImage(Images.pix255_255_255)
         .draw(
            var1,
            this.getPosX() + var2,
            this.getPosY() + this.getHeight() - CFG.PADDING - this.getSliderHeight() - ImageManager.getImage(Images.pix255_255_255).getHeight() + var3,
            this.getWidth(),
            1
         );
      ImageManager.getImage(Images.pix255_255_255)
         .draw(
            var1,
            this.getPosX() + var2,
            this.getPosY() + this.getHeight() - CFG.PADDING - ImageManager.getImage(Images.pix255_255_255).getHeight() + var3,
            this.getWidth(),
            1
         );
      if (var4) {
         var1.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.045F));
         ImageManager.getImage(Images.line_32_off1)
            .draw(
               var1,
               this.getPosX() + var2,
               this.getPosY() + this.getHeight() - CFG.PADDING - this.getSliderHeight() - ImageManager.getImage(Images.line_32_off1).getHeight() + var3,
               this.getWidth(),
               1
            );
         ImageManager.getImage(Images.line_32_off1)
            .draw(
               var1,
               this.getPosX() + var2,
               this.getPosY() + this.getHeight() - CFG.PADDING - ImageManager.getImage(Images.line_32_off1).getHeight() + var3,
               this.getWidth(),
               1
            );
      }

      var1.setColor(new Color(0.0F, 0.0F, 0.0F, 0.65F));
      ImageManager.getImage(Images.line_32_off1)
         .draw(
            var1,
            this.getPosX() + var2,
            this.getPosY() + this.getHeight() - CFG.PADDING - this.getSliderHeight() + 1 - ImageManager.getImage(Images.line_32_off1).getHeight() + var3,
            this.getWidth(),
            1
         );
      ImageManager.getImage(Images.line_32_off1)
         .draw(
            var1,
            this.getPosX() + var2,
            this.getPosY() + this.getHeight() - CFG.PADDING - 1 - ImageManager.getImage(Images.line_32_off1).getHeight() + var3,
            this.getWidth(),
            1
         );
      var1.setColor(new Color(0.0F, 0.0F, 0.0F, 0.65F));
      ImageManager.getImage(Images.pix255_255_255)
         .draw(
            var1,
            this.getPosX() + var2,
            this.getPosY() + this.getHeight() - CFG.PADDING - this.getSliderHeight() - 1 - ImageManager.getImage(Images.pix255_255_255).getHeight() + var3,
            this.getWidth(),
            1
         );
      ImageManager.getImage(Images.pix255_255_255)
         .draw(
            var1,
            this.getPosX() + var2,
            this.getPosY() + this.getHeight() - CFG.PADDING + 1 - ImageManager.getImage(Images.pix255_255_255).getHeight() + var3,
            this.getWidth(),
            1
         );
      var1.setColor(new Color(0.0F, 0.0F, 0.0F, 0.8F));
      ImageManager.getImage(Images.slider_gradient)
         .draw(
            var1,
            this.getPosX() + var2,
            this.getPosY() + this.getHeight() - CFG.PADDING - this.getSliderHeight() - ImageManager.getImage(Images.slider_gradient).getHeight() + var3,
            this.getWidth() / 8,
            1
         );
      ImageManager.getImage(Images.slider_gradient)
         .draw(
            var1,
            this.getPosX() + var2,
            this.getPosY() + this.getHeight() - CFG.PADDING - ImageManager.getImage(Images.slider_gradient).getHeight() + var3,
            this.getWidth() / 8,
            1
         );
      ImageManager.getImage(Images.slider_gradient)
         .draw(
            var1,
            this.getPosX() + this.getWidth() - this.getWidth() / 8 + var2,
            this.getPosY() + this.getHeight() - CFG.PADDING - this.getSliderHeight() - ImageManager.getImage(Images.slider_gradient).getHeight() + var3,
            this.getWidth() / 8,
            1,
            true,
            false
         );
      ImageManager.getImage(Images.slider_gradient)
         .draw(
            var1,
            this.getPosX() + this.getWidth() - this.getWidth() / 8 + var2,
            this.getPosY() + this.getHeight() - CFG.PADDING - ImageManager.getImage(Images.slider_gradient).getHeight() + var3,
            this.getWidth() / 8,
            1,
            true,
            false
         );
      var1.setColor(Color.WHITE);
      ImageManager.getImage(Images.Resource)
         .draw(
            var1,
            this.getPosX()
               + this.getWidth()
               - CFG.PADDING * 2
               - (int)(this.getTextWidth() * this.FONT_SCALE)
               - (int)(ImageManager.getImage(Images.Resource).getWidth() * this.getImageScale(this.FONT_SCALE, Images.Resource))
               + var2,
            this.getPosY()
               + this.getHeight()
               - CFG.PADDING * 2
               - this.getSliderHeight()
               - (int)(ImageManager.getImage(Images.Resource).getHeight() * this.getImageScale(this.FONT_SCALE, Images.Resource))
               - ImageManager.getImage(Images.Resource).getHeight()
               + var3,
            (int)(ImageManager.getImage(Images.Resource).getWidth() * this.getImageScale(this.FONT_SCALE, Images.Resource)),
            (int)(ImageManager.getImage(Images.Resource).getHeight() * this.getImageScale(this.FONT_SCALE, Images.Resource))
         );
      CFG.fontMain.getData().setScale(this.FONT_SCALE);
      CFG.drawTextWithShadow(
         var1,
         this.getText(),
         this.getPosX() + CFG.PADDING + var2,
         this.getPosY() + this.getHeight() - CFG.PADDING * 2 - this.getSliderHeight() - (int)(CFG.TEXT_HEIGHT * this.FONT_SCALE) + var3,
         this.getColor(var4)
      );
      String var14 = this.getDrawText();
      int var15 = this.getPosX();
      int var16 = this.getWidth();
      var7 = CFG.PADDING;
      var10 = (int)(this.getTextWidth() * this.FONT_SCALE);
      var8 = this.getPosY();
      var11 = this.getHeight();
      var13 = CFG.PADDING;
      var9 = this.getSliderHeight();
      var12 = (int)(CFG.TEXT_HEIGHT * this.FONT_SCALE);
      Color var24;
      if (this.getCurrent() == 0) {
         var24 = CFG.COLOR_TEXT_MODIFIER_NEUTRAL;
      } else {
         var24 = CFG.COLOR_INGAME_GOLD;
      }

      CFG.drawTextWithShadow(var1, var14, var15 + var16 - var7 - var10 + var2, var8 + var11 - var13 * 2 - var9 - var12 + var3, var24);
      CFG.fontMain.getData().setScale(1.0F);
   }

   protected Color getColor(boolean var1) {
      Color var2;
      if (var1) {
         var2 = new Color(0.71F, 0.71F, 0.71F, 1.0F);
      } else if (this.getIsHovered()) {
         var2 = new Color(0.82F, 0.82F, 0.82F, 1.0F);
      } else {
         var2 = Color.WHITE;
      }

      return var2;
   }

   @Override
   protected Color getColorLEFT() {
      return new Color(CFG.COLOR_GRADIENT_TITLE_BLUE.r, CFG.COLOR_GRADIENT_TITLE_BLUE.g, CFG.COLOR_GRADIENT_TITLE_BLUE.b, 0.75F);
   }

   @Override
   protected String getDrawText() {
      return "" + this.getCurrent();
   }

   protected final float getImageScale(float var1, int var2) {
      return this.getTextHeight() * var1 / ImageManager.getImage(var2).getHeight();
   }

   protected int getSliderHeight() {
      return CFG.PADDING * 3;
   }
}
