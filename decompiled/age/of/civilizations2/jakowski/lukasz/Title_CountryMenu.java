package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Title_CountryMenu extends Text_EconomyTitle {
   Color colorTitle;

   public Title_CountryMenu(String sText, int iTextPositionX, int iPosX, int iPosY, int iWidth, int iHeight, Color color) {
      super(sText, iTextPositionX, iPosX, iPosY, iWidth, iHeight);
      this.colorTitle = color;
   }

   @Override
   public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
      oSB.setColor(new Color(this.colorTitle));
      ImageManager.getImage(Images.gradient)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY,
            this.getWidth(),
            this.getHeight()
         );
      oSB.setColor(new Color(this.colorTitle));
      ImageManager.getImage(Images.line_32_off1)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
            this.getWidth(),
            this.getHeight()
         );
      oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.45F));
      ImageManager.getImage(Images.gradient)
         .draw(
            oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY, this.getWidth(), CFG.PADDING
         );
      oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.2F));
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
      oSB.setColor(new Color(this.colorTitle));
      ImageManager.getImage(Images.pix255_255_255)
         .draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY, this.getWidth(), 1);
      oSB.setColor(CFG.COLOR_NEW_GAME_EDGE_LINE);
      ImageManager.getImage(Images.slider_gradient)
         .draw(
            oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY, this.getWidth() / 2, 1
         );
      ImageManager.getImage(Images.slider_gradient)
         .draw(
            oSB,
            this.getPosX() + this.getWidth() - this.getWidth() / 2 + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY,
            this.getWidth() / 2,
            1,
            true,
            false
         );
      oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.65F));
      ImageManager.getImage(Images.slider_gradient)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() + 1 - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY,
            this.getWidth() / 2,
            1
         );
      ImageManager.getImage(Images.slider_gradient)
         .draw(
            oSB,
            this.getPosX() + this.getWidth() - this.getWidth() / 2 + iTranslateX,
            this.getPosY() + 1 - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY,
            this.getWidth() / 2,
            1,
            true,
            false
         );
      oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.55F));
      ImageManager.getImage(Images.slider_gradient)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() + this.getHeight() - 1 - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY,
            this.getWidth() / 2,
            1
         );
      ImageManager.getImage(Images.slider_gradient)
         .draw(
            oSB,
            this.getPosX() + this.getWidth() - this.getWidth() / 2 + iTranslateX,
            this.getPosY() + this.getHeight() - 1 - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY,
            this.getWidth() / 2,
            1,
            true,
            false
         );
      oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.105F));
      ImageManager.getImage(Images.gradient)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() + this.getHeight() - this.getHeight() / 3 - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY,
            this.getWidth(),
            this.getHeight() / 3,
            false,
            true
         );
      oSB.setColor(Color.WHITE);
      CFG.fontMain.getData().setScale(0.85F);
      CFG.drawTextWithShadow(
         oSB,
         this.sText,
         this.getPosX() + (int)((this.getWidth() - this.getTextWidth() * 0.7F) / 2.0F) + iTranslateX,
         this.getPosY() + (int)((this.getHeight() - this.iTextHeight * 0.7F) / 2.0F) + iTranslateY,
         this.getColor(isActive)
      );
      CFG.fontMain.getData().setScale(1.15F);
      oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.7F));
      ImageManager.getImage(Images.line_32_off1)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() + this.getHeight() - ImageManager.getImage(Images.line_32_off1).getHeight() - 1 + iTranslateY,
            this.getWidth(),
            1
         );
      oSB.setColor(Color.WHITE);
   }
}
