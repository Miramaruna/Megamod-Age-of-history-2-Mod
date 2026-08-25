package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Text_Investemnts_SliderDescGoods extends Text {
   public static final float TEXT2_SCALE = 0.65F;
   public String sText2;
   public int iText2Width;
   public String sPop;

   public Text_Investemnts_SliderDescGoods(String sText, String sText2, String sPop, int iPosX, int iPosY, int iWidth, int iHeight) {
      super(sText, CFG.PADDING, iPosX, iPosY, iWidth, iHeight);
      this.sText2 = sText2;
      CFG.glyphLayout.setText(CFG.fontMain, sText2);
      this.iText2Width = (int)(CFG.glyphLayout.width * 0.65F);
      this.sPop = sPop;
   }

   @Override
   public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
      oSB.setColor(new Color(CFG.COLOR_GRADIENT_TITLE_BLUE.r, CFG.COLOR_GRADIENT_TITLE_BLUE.g, CFG.COLOR_GRADIENT_TITLE_BLUE.b, 0.15F));
      ImageManager.getImage(Images.pix255_255_255)
         .draw(
            oSB,
            this.getPosX() - CFG.PADDING + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY,
            this.getWidth() + CFG.PADDING * 2,
            this.getHeight()
         );
      oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.55F));
      ImageManager.getImage(Images.gradient)
         .draw(
            oSB,
            this.getPosX() - CFG.PADDING + iTranslateX,
            this.getPosY() + this.getHeight() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY,
            this.getWidth() + CFG.PADDING * 2,
            this.getHeight() * 2 / 5,
            false,
            false
         );
      ImageManager.getImage(Images.gradient)
         .draw(
            oSB,
            this.getPosX() - CFG.PADDING + iTranslateX,
            this.getPosY() + this.getHeight() - this.getHeight() * 2 / 5 - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY,
            this.getWidth() + CFG.PADDING * 2,
            this.getHeight() * 2 / 5,
            false,
            true
         );
      oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.275F));
      ImageManager.getImage(Images.slider_gradient)
         .draw(
            oSB,
            this.getPosX() - CFG.PADDING + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY,
            this.getWidth() / 4,
            this.getHeight(),
            false,
            false
         );
      ImageManager.getImage(Images.slider_gradient)
         .draw(
            oSB,
            this.getPosX() - CFG.PADDING + this.getWidth() + CFG.PADDING * 2 - this.getWidth() / 4 + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY,
            this.getWidth() / 4,
            this.getHeight(),
            true,
            false
         );
      oSB.setColor(new Color(CFG.COLOR_GRADIENT_TITLE_BLUE.r, CFG.COLOR_GRADIENT_TITLE_BLUE.g, CFG.COLOR_GRADIENT_TITLE_BLUE.b, 0.65F));
      ImageManager.getImage(Images.line_32_off1)
         .draw(
            oSB,
            this.getPosX() - CFG.PADDING + iTranslateX,
            this.getPosY() + 1 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
            this.getWidth() + CFG.PADDING * 2,
            1
         );
      ImageManager.getImage(Images.line_32_off1)
         .draw(
            oSB,
            this.getPosX() - CFG.PADDING + iTranslateX,
            this.getPosY() + this.getHeight() - 2 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
            this.getWidth() + CFG.PADDING * 2,
            1
         );
      oSB.setColor(Color.WHITE);
      ImageManager.getImage(Images.population)
         .draw(
            oSB,
            this.getPosX() + CFG.PADDING * 2 + iTranslateX,
            this.getPosY()
               - ImageManager.getImage(Images.population).getHeight()
               + (this.getHeight() - (int)(ImageManager.getImage(Images.population).getHeight() * this.getImageScale(0.65F, Images.population))) / 2
               + iTranslateY,
            (int)(ImageManager.getImage(Images.population).getWidth() * this.getImageScale(0.65F, Images.population)),
            (int)(ImageManager.getImage(Images.population).getHeight() * this.getImageScale(0.65F, Images.population))
         );
      ImageManager.getImage(Images.population_growth)
         .draw(
            oSB,
            this.getPosX()
               + this.getWidth()
               - CFG.PADDING * 2
               - (int)(ImageManager.getImage(Images.population_growth).getWidth() * this.getImageScale(0.65F, Images.population_growth))
               + iTranslateX,
            this.getPosY()
               - ImageManager.getImage(Images.population_growth).getHeight()
               + (this.getHeight() - (int)(ImageManager.getImage(Images.population_growth).getHeight() * this.getImageScale(0.65F, Images.population_growth)))
                  / 2
               + iTranslateY,
            (int)(ImageManager.getImage(Images.population_growth).getWidth() * this.getImageScale(0.65F, Images.population_growth)),
            (int)(ImageManager.getImage(Images.population_growth).getHeight() * this.getImageScale(0.65F, Images.population_growth))
         );
      CFG.fontMain.getData().setScale(0.65F);
      CFG.drawTextWithShadow(
         oSB,
         this.sText2,
         this.getPosX()
            + CFG.PADDING * 3
            + (int)(ImageManager.getImage(Images.population).getWidth() * this.getImageScale(0.65F, Images.population))
            + iTranslateX,
         this.getPosY() + (int)((this.getHeight() - this.iTextHeight * 0.65F) / 2.0F) + iTranslateY,
         this.getColor(isActive)
      );
      CFG.drawTextWithShadow(
         oSB,
         this.sPop,
         this.getPosX()
            + CFG.PADDING * 3
            + this.iText2Width
            + (int)(ImageManager.getImage(Images.population).getWidth() * this.getImageScale(0.65F, Images.population))
            + iTranslateX,
         this.getPosY() + (int)((this.getHeight() - this.iTextHeight * 0.65F) / 2.0F) + iTranslateY,
         CFG.COLOR_TEXT_POPULATION
      );
      CFG.drawTextWithShadow(
         oSB,
         this.getText(),
         this.getPosX()
            + this.getWidth()
            - CFG.PADDING * 3
            - (int)(ImageManager.getImage(Images.population_growth).getWidth() * this.getImageScale(0.65F, Images.population_growth))
            - (int)(this.getTextWidth() * 0.65F)
            + iTranslateX,
         this.getPosY() + (int)((this.getHeight() - this.iTextHeight * 0.65F) / 2.0F) + iTranslateY,
         CFG.COLOR_TEXT_MODIFIER_NEUTRAL
      );
      CFG.fontMain.getData().setScale(1.0F);
   }

   public final float getImageScale(float fScale, int nImageID) {
      return this.iTextHeight * fScale / ImageManager.getImage(nImageID).getHeight();
   }
}
