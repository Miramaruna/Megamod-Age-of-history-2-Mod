package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

class Menu_InGame_Destroy_All$15 extends SliderMenuTitle {
   final Menu_InGame_Destroy_All this$0;

   Menu_InGame_Destroy_All$15(Menu_InGame_Destroy_All var1, String var2, int var3, boolean var4, boolean var5) {
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
      ImageManager.getImage(Images.dialog_title)
         .draw2(
            var1,
            var3 + var5 + 2 - ImageManager.getImage(Images.dialog_title).getWidth() + var2,
            var4 - this.getHeight() - ImageManager.getImage(Images.dialog_title).getHeight(),
            ImageManager.getImage(Images.dialog_title).getWidth(),
            this.getHeight(),
            true,
            false
         );
      var1.setColor(new Color(CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.r, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.g, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.b, 0.165F));
      ImageManager.getImage(Images.line_32_off1)
         .draw(var1, var3 + var2, var4 - this.getHeight() + 2 - ImageManager.getImage(Images.line_32_off1).getHeight(), var5, this.getHeight() - 2, false, true);
      var1.setColor(new Color(CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.r, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.g, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.b, 0.375F));
      ImageManager.getImage(Images.gradient)
         .draw(
            var1,
            var3 + var2,
            var4 - this.getHeight() * 2 / 3 - ImageManager.getImage(Images.gradient).getHeight(),
            var5,
            this.getHeight() * 2 / 3,
            false,
            true
         );
      var1.setColor(new Color(0.0F, 0.0F, 0.0F, 0.65F));
      ImageManager.getImage(Images.gradient)
         .draw(var1, var3 + var2, var4 - CFG.PADDING - ImageManager.getImage(Images.gradient).getHeight(), var5, CFG.PADDING, false, true);
      var1.setColor(CFG.COLOR_NEW_GAME_EDGE_LINE);
      ImageManager.getImage(Images.pix255_255_255).draw(var1, var3 + var2, var4 - 1 - ImageManager.getImage(Images.pix255_255_255).getHeight(), var5, 1);
      var1.setColor(new Color(0.0F, 0.0F, 0.0F, 0.55F));
      ImageManager.getImage(Images.line_32_off1).draw(var1, var3 + var2, var4 - 2 - ImageManager.getImage(Images.line_32_off1).getHeight(), var5, 1);
      var1.setColor(new Color(0.0F, 0.0F, 0.0F, 0.8F));
      ImageManager.getImage(Images.line_32_off1).draw(var1, var3 + var2, var4 - 1 - ImageManager.getImage(Images.line_32_off1).getHeight(), var5, 1);
      var1.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.45F));
      ImageManager.getImage(Images.slider_gradient).draw(var1, var3 + var2, var4 - 1 - ImageManager.getImage(Images.slider_gradient).getHeight(), var5 / 2, 1);
      ImageManager.getImage(Images.slider_gradient)
         .draw(var1, var3 + var5 - var5 / 2 + var2, var4 - 1 - ImageManager.getImage(Images.slider_gradient).getHeight(), var5 / 2, 1, true, false);
      var1.setColor(Color.WHITE);
      CFG.game
         .getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
         .getFlag()
         .draw(
            var1,
            this.this$0.getPosX() + CFG.PADDING * 2 + var2,
            this.this$0.getPosY()
               - this.getHeight() / 2
               - CFG.CIV_FLAG_HEIGHT / 2
               - CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getFlag().getHeight(),
            CFG.CIV_FLAG_WIDTH,
            CFG.CIV_FLAG_HEIGHT
         );
      ImageManager.getImage(Images.flag_rect)
         .draw(var1, this.this$0.getPosX() + CFG.PADDING * 2 + var2, this.this$0.getPosY() - this.getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2);
      CFG.fontMain.getData().setScale(0.8F);
      CFG.drawText(
         var1,
         this.getText(),
         (int)(var5 - this.getTextWidth() * 0.8F) / 2 + var3 + var2,
         var4 + 2 - this.getHeight() + (int)(this.getHeight() - this.getTextHeight() * 0.8F) / 2,
         Color.WHITE
      );
      CFG.fontMain.getData().setScale(1.0F);
   }
}
