package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

class Menu_InGame_Laws$19 extends SliderMenuTitle {
   final Menu_InGame_Laws this$0;

   Menu_InGame_Laws$19(Menu_InGame_Laws var1, String var2, int var3, boolean var4, boolean var5) {
      super(var2, var3, var4, var5);
      this.this$0 = var1;
   }

   @Override
   protected void draw(SpriteBatch var1, int var2, int var3, int var4, int var5, boolean var6) {
      ImageManager.getImage(Images.dialog_title)
         .draw2(
            var1,
            var3 - 2 + var2,
            var4 - this.getHeight() - ImageManager.getImage(Images.dialog_title).getHeight(),
            var5 + 4 - ImageManager.getImage(Images.dialog_title).getWidth(),
            this.getHeight()
         );
      Image var7 = ImageManager.getImage(Images.dialog_title);
      int var8 = var3 + var5;
      var7.draw2(
         var1,
         var8 + 2 - ImageManager.getImage(Images.dialog_title).getWidth() + var2,
         var4 - this.getHeight() - ImageManager.getImage(Images.dialog_title).getHeight(),
         ImageManager.getImage(Images.dialog_title).getWidth(),
         this.getHeight(),
         true,
         false
      );
      var1.setColor(
         new Color(
            CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getR() / 255.0F,
            CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getG() / 255.0F,
            CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getB() / 255.0F,
            0.165F
         )
      );
      var7 = ImageManager.getImage(Images.line_32_off1);
      int var9 = var3 + var2;
      var7.draw(var1, var9, var4 - this.getHeight() + 2 - ImageManager.getImage(Images.line_32_off1).getHeight(), var5, this.getHeight() - 2, false, true);
      var1.setColor(
         new Color(
            CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getR() / 255.0F,
            CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getG() / 255.0F,
            CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getB() / 255.0F,
            0.375F
         )
      );
      ImageManager.getImage(Images.gradient)
         .draw(var1, var9, var4 - this.getHeight() * 2 / 3 - ImageManager.getImage(Images.gradient).getHeight(), var5, this.getHeight() * 2 / 3, false, true);
      var1.setColor(new Color(0.0F, 0.0F, 0.0F, 1.0F));
      ImageManager.getImage(Images.gradient)
         .draw(var1, var9, var4 - CFG.PADDING - ImageManager.getImage(Images.gradient).getHeight(), var5, CFG.PADDING, false, true);
      var1.setColor(CFG.COLOR_NEW_GAME_EDGE_LINE);
      var7 = ImageManager.getImage(Images.pix255_255_255);
      int var10 = var4 - 1;
      var7.draw(var1, var9, var10 - ImageManager.getImage(Images.pix255_255_255).getHeight(), var5, 1);
      var1.setColor(new Color(0.0F, 0.0F, 0.0F, 0.55F));
      ImageManager.getImage(Images.line_32_off1).draw(var1, var9, var4 - 2 - ImageManager.getImage(Images.line_32_off1).getHeight(), var5, 1);
      var1.setColor(new Color(0.0F, 0.0F, 0.0F, 0.8F));
      ImageManager.getImage(Images.line_32_off1).draw(var1, var9, var10 - ImageManager.getImage(Images.line_32_off1).getHeight(), var5, 1);
      var1.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.45F));
      var7 = ImageManager.getImage(Images.slider_gradient);
      int var11 = ImageManager.getImage(Images.slider_gradient).getHeight();
      int var12 = var5 / 2;
      var7.draw(var1, var9, var10 - var11, var12, 1);
      ImageManager.getImage(Images.slider_gradient)
         .draw(var1, var8 - var12 + var2, var10 - ImageManager.getImage(Images.slider_gradient).getHeight(), var12, 1, true, false);
      var1.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.425F));
      var7 = ImageManager.getImage(Images.slider_gradient);
      var10 = CFG.PADDING;
      var8 = var4 + 1;
      var7.draw(
         var1,
         var3 + var10 * 2 + var2,
         var8 - this.getHeight() / 2 - ImageManager.getImage(Images.slider_gradient).getHeight(),
         (int)((var5 - CFG.PADDING * 6 - this.getTextWidth() * 0.8F) / 2.0F),
         1,
         true,
         false
      );
      var7 = ImageManager.getImage(Images.slider_gradient);
      var12 = var3 + var12;
      var7.draw(
         var1,
         CFG.PADDING + var12 + (int)(this.getTextWidth() * 0.8F / 2.0F) + var2,
         var8 - this.getHeight() / 2 - ImageManager.getImage(Images.slider_gradient).getHeight(),
         (int)((var5 - CFG.PADDING * 6 - this.getTextWidth() * 0.8F) / 2.0F),
         1
      );
      var1.setColor(new Color(0.0F, 0.0F, 0.0F, 0.325F));
      var7 = ImageManager.getImage(Images.slider_gradient);
      var10 = CFG.PADDING;
      var8 = var4 + 2;
      var7.draw(
         var1,
         var3 + var10 * 2 + var2,
         var8 - this.getHeight() / 2 - ImageManager.getImage(Images.slider_gradient).getHeight(),
         (int)((var5 - CFG.PADDING * 6 - this.getTextWidth() * 0.8F) / 2.0F),
         1,
         true,
         false
      );
      ImageManager.getImage(Images.slider_gradient)
         .draw(
            var1,
            CFG.PADDING + var12 + (int)(this.getTextWidth() * 0.8F / 2.0F) + var2,
            var8 - this.getHeight() / 2 - ImageManager.getImage(Images.slider_gradient).getHeight(),
            (int)((var5 - CFG.PADDING * 6 - this.getTextWidth() * 0.8F) / 2.0F),
            1
         );
      ImageManager.getImage(Images.slider_gradient)
         .draw(
            var1,
            var3 + CFG.PADDING * 2 + var2,
            var4 - this.getHeight() / 2 - ImageManager.getImage(Images.slider_gradient).getHeight(),
            (int)((var5 - CFG.PADDING * 6 - this.getTextWidth() * 0.8F) / 2.0F),
            1,
            true,
            false
         );
      ImageManager.getImage(Images.slider_gradient)
         .draw(
            var1,
            var12 + CFG.PADDING + (int)(this.getTextWidth() * 0.8F / 2.0F) + var2,
            var4 - this.getHeight() / 2 - ImageManager.getImage(Images.slider_gradient).getHeight(),
            (int)((var5 - CFG.PADDING * 6 - this.getTextWidth() * 0.8F) / 2.0F),
            1
         );
      var1.setColor(Color.WHITE);
      CFG.fontMain.getData().setScale(0.8F);
      CFG.drawText(
         var1,
         this.getText(),
         var3 + (int)(var5 - this.getTextWidth() * 0.8F) / 2 + var2,
         var8 - this.getHeight() + (int)(this.getHeight() - this.getTextHeight() * 0.8F) / 2,
         Color.WHITE
      );
      CFG.fontMain.getData().setScale(1.0F);
   }
}
