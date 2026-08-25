package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

class Menu_InGame_Technology$27 extends SliderMenuTitle {
   Menu_InGame_Technology$27(Menu_InGame_Technology this$0, String sText, int iHeight, boolean moveable, boolean resizable) {
      super(sText, iHeight, moveable, resizable);
      this.this$0 = this$0;
   }

   @Override
   protected void draw(SpriteBatch oSB, int iTranslateX, int nPosX, int nPosY, int nWidth, boolean sliderMenuIsActive) {
      ImageManager.getImage(Images.dialog_title)
         .draw2(
            oSB,
            nPosX - 2 + iTranslateX,
            nPosY - this.getHeight() - ImageManager.getImage(Images.dialog_title).getHeight(),
            nWidth + 4 - ImageManager.getImage(Images.dialog_title).getWidth(),
            this.getHeight()
         );
      ImageManager.getImage(Images.dialog_title)
         .draw2(
            oSB,
            nPosX + nWidth + 2 - ImageManager.getImage(Images.dialog_title).getWidth() + iTranslateX,
            nPosY - this.getHeight() - ImageManager.getImage(Images.dialog_title).getHeight(),
            ImageManager.getImage(Images.dialog_title).getWidth(),
            this.getHeight(),
            true,
            false
         );
      oSB.setColor(
         new Color(
            CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getR() / 255.0F,
            CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getG() / 255.0F,
            CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getB() / 255.0F,
            0.165F
         )
      );
      ImageManager.getImage(Images.line_32_off1)
         .draw(
            oSB,
            nPosX + iTranslateX,
            nPosY - this.getHeight() + 2 - ImageManager.getImage(Images.line_32_off1).getHeight(),
            nWidth,
            this.getHeight() - 2,
            false,
            true
         );
      oSB.setColor(
         new Color(
            CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getR() / 255.0F,
            CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getG() / 255.0F,
            CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getB() / 255.0F,
            0.375F
         )
      );
      ImageManager.getImage(Images.gradient)
         .draw(
            oSB,
            nPosX + iTranslateX,
            nPosY - this.getHeight() * 2 / 3 - ImageManager.getImage(Images.gradient).getHeight(),
            nWidth,
            this.getHeight() * 2 / 3,
            false,
            true
         );
      oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.65F));
      ImageManager.getImage(Images.gradient)
         .draw(oSB, nPosX + iTranslateX, nPosY - CFG.PADDING - ImageManager.getImage(Images.gradient).getHeight(), nWidth, CFG.PADDING, false, true);
      oSB.setColor(CFG.COLOR_NEW_GAME_EDGE_LINE);
      ImageManager.getImage(Images.pix255_255_255)
         .draw(oSB, nPosX + iTranslateX, nPosY - 1 - ImageManager.getImage(Images.pix255_255_255).getHeight(), nWidth, 1);
      oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.55F));
      ImageManager.getImage(Images.line_32_off1).draw(oSB, nPosX + iTranslateX, nPosY - 2 - ImageManager.getImage(Images.line_32_off1).getHeight(), nWidth, 1);
      oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.8F));
      ImageManager.getImage(Images.line_32_off1).draw(oSB, nPosX + iTranslateX, nPosY - 1 - ImageManager.getImage(Images.line_32_off1).getHeight(), nWidth, 1);
      oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.45F));
      ImageManager.getImage(Images.slider_gradient)
         .draw(oSB, nPosX + iTranslateX, nPosY - 1 - ImageManager.getImage(Images.slider_gradient).getHeight(), nWidth / 2, 1);
      ImageManager.getImage(Images.slider_gradient)
         .draw(
            oSB, nPosX + nWidth - nWidth / 2 + iTranslateX, nPosY - 1 - ImageManager.getImage(Images.slider_gradient).getHeight(), nWidth / 2, 1, true, false
         );
      oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.425F));
      ImageManager.getImage(Images.slider_gradient)
         .draw(
            oSB,
            nPosX + CFG.PADDING * 2 + iTranslateX,
            nPosY + 1 - this.getHeight() / 2 - ImageManager.getImage(Images.slider_gradient).getHeight(),
            (int)((nWidth - CFG.PADDING * 6 - this.getTextWidth() * 0.8F) / 2.0F),
            1,
            true,
            false
         );
      ImageManager.getImage(Images.slider_gradient)
         .draw(
            oSB,
            nPosX + nWidth / 2 + CFG.PADDING + (int)(this.getTextWidth() * 0.8F / 2.0F) + iTranslateX,
            nPosY + 1 - this.getHeight() / 2 - ImageManager.getImage(Images.slider_gradient).getHeight(),
            (int)((nWidth - CFG.PADDING * 6 - this.getTextWidth() * 0.8F) / 2.0F),
            1
         );
      oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.325F));
      ImageManager.getImage(Images.slider_gradient)
         .draw(
            oSB,
            nPosX + CFG.PADDING * 2 + iTranslateX,
            nPosY + 2 - this.getHeight() / 2 - ImageManager.getImage(Images.slider_gradient).getHeight(),
            (int)((nWidth - CFG.PADDING * 6 - this.getTextWidth() * 0.8F) / 2.0F),
            1,
            true,
            false
         );
      ImageManager.getImage(Images.slider_gradient)
         .draw(
            oSB,
            nPosX + nWidth / 2 + CFG.PADDING + (int)(this.getTextWidth() * 0.8F / 2.0F) + iTranslateX,
            nPosY + 2 - this.getHeight() / 2 - ImageManager.getImage(Images.slider_gradient).getHeight(),
            (int)((nWidth - CFG.PADDING * 6 - this.getTextWidth() * 0.8F) / 2.0F),
            1
         );
      ImageManager.getImage(Images.slider_gradient)
         .draw(
            oSB,
            nPosX + CFG.PADDING * 2 + iTranslateX,
            nPosY - this.getHeight() / 2 - ImageManager.getImage(Images.slider_gradient).getHeight(),
            (int)((nWidth - CFG.PADDING * 6 - this.getTextWidth() * 0.8F) / 2.0F),
            1,
            true,
            false
         );
      ImageManager.getImage(Images.slider_gradient)
         .draw(
            oSB,
            nPosX + nWidth / 2 + CFG.PADDING + (int)(this.getTextWidth() * 0.8F / 2.0F) + iTranslateX,
            nPosY - this.getHeight() / 2 - ImageManager.getImage(Images.slider_gradient).getHeight(),
            (int)((nWidth - CFG.PADDING * 6 - this.getTextWidth() * 0.8F) / 2.0F),
            1
         );
      oSB.setColor(Color.WHITE);
      CFG.fontMain.getData().setScale(0.8F);
      CFG.drawText(
         oSB,
         this.getText(),
         nPosX + (int)(nWidth - this.getTextWidth() * 0.8F) / 2 + iTranslateX,
         2 + nPosY - this.getHeight() + (int)(this.getHeight() - this.getTextHeight() * 0.8F) / 2,
         Color.WHITE
      );
      CFG.fontMain.getData().setScale(1.0F);
   }
}
