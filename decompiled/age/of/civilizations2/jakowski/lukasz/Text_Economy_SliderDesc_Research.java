package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Text_Economy_SliderDesc_Research extends Text {
   public static final float TEXT2_SCALE = 0.65F;
   public static final float TEXT3_SCALE = 0.65F;
   public String sText2;
   public int iText2Width;
   public String sText_Progress;
   public int iText_ProgressWidth;
   public String sText_ProgressPerc;
   public String sText_CurrentProgress;
   public int iText_CurrentProgressWidth;
   public String sText_Spendings = "";
   public int iText_SpendingsWidth = 0;
   public Color colorSpendings = Color.WHITE;
   public float fResearchPerc;

   public Text_Economy_SliderDesc_Research(String sText, String sText2, int iPosX, int iPosY, int iWidth, int iHeight) {
      super(sText2, CFG.PADDING, iPosX, iPosY, iWidth, iHeight);
      this.sText2 = sText;
   }

   public Text_Economy_SliderDesc_Research(
      String sText,
      String sText_CurrentProgress,
      String sText_Progress,
      String sText_ProgressPerc,
      String sText2,
      int iPosX,
      int iPosY,
      int iWidth,
      int iHeight
   ) {
      super(sText2, CFG.PADDING, iPosX, iPosY, iWidth, iHeight);
      this.fResearchPerc = CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getResearchProgress()
         / TechnologyManager.getResearch_NextLevel(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
      this.sText2 = sText;
      this.sText_Progress = sText_Progress;
      this.sText_ProgressPerc = sText_ProgressPerc;
      this.sText_CurrentProgress = sText_CurrentProgress;
      CFG.glyphLayout.setText(CFG.fontMain, this.sText2);
      this.iText2Width = (int)(CFG.glyphLayout.width * 0.65F);
      CFG.glyphLayout.setText(CFG.fontMain, this.sText_Progress);
      this.iText_ProgressWidth = (int)(CFG.glyphLayout.width * 0.65F);
      CFG.glyphLayout.setText(CFG.fontMain, this.sText_CurrentProgress);
      this.iText_CurrentProgressWidth = (int)(CFG.glyphLayout.width * 0.65F);
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
      oSB.setColor(new Color(1.0F, 1.0F, 1.0F, 0.95F));
      ImageManager.getImage(Images.patt)
         .draw2(
            oSB,
            this.getPosX() - CFG.PADDING + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.patt).getHeight() + iTranslateY,
            (int)((this.getWidth() + CFG.PADDING * 2) * this.fResearchPerc),
            this.getHeight()
         );
      oSB.setColor(new Color(1.0F, 1.0F, 1.0F, 0.7F));
      float spendingsProgress = CFG.game_NextTurnUpdate
            .getResearchSpendings(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).iBudget)
         / TechnologyManager.getResearch_NextLevel(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
      if (this.fResearchPerc + spendingsProgress > 1.0F) {
         spendingsProgress = 1.0F - this.fResearchPerc;
      }

      ImageManager.getImage(Images.patt)
         .draw2(
            oSB,
            this.getPosX() - CFG.PADDING + (int)((this.getWidth() + CFG.PADDING * 2) * this.fResearchPerc) + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.patt).getHeight() + iTranslateY,
            (int)((this.getWidth() + CFG.PADDING * 2) * spendingsProgress),
            this.getHeight()
         );
      oSB.setColor(Color.WHITE);
      ImageManager.getImage(Images.research)
         .draw(
            oSB,
            this.getPosX() + CFG.PADDING * 2 + iTranslateX,
            this.getPosY()
               - 1
               - ImageManager.getImage(Images.research).getHeight()
               + (this.getHeight() - (int)(ImageManager.getImage(Images.research).getHeight() * this.getImageScale(0.65F, Images.research))) / 2
               + iTranslateY,
            (int)(ImageManager.getImage(Images.research).getWidth() * this.getImageScale(0.65F, Images.research)),
            (int)(ImageManager.getImage(Images.research).getHeight() * this.getImageScale(0.65F, Images.research))
         );
      ImageManager.getImage(Images.technology)
         .draw(
            oSB,
            this.getPosX()
               + this.getWidth()
               - CFG.PADDING * 2
               - (int)(ImageManager.getImage(Images.technology).getWidth() * this.getImageScale(0.65F, Images.technology))
               + iTranslateX,
            this.getPosY()
               - 1
               - ImageManager.getImage(Images.technology).getHeight()
               + (this.getHeight() - (int)(ImageManager.getImage(Images.technology).getHeight() * this.getImageScale(0.65F, Images.technology))) / 2
               + iTranslateY,
            (int)(ImageManager.getImage(Images.technology).getWidth() * this.getImageScale(0.65F, Images.technology)),
            (int)(ImageManager.getImage(Images.technology).getHeight() * this.getImageScale(0.65F, Images.technology))
         );
      CFG.fontMain.getData().setScale(0.65F);
      CFG.drawTextWithShadow(
         oSB,
         this.sText2,
         this.getPosX() + CFG.PADDING * 3 + (int)(ImageManager.getImage(Images.research).getWidth() * this.getImageScale(0.65F, Images.research)) + iTranslateX,
         this.getPosY() + (int)((this.getHeight() - this.iTextHeight * 0.65F) / 2.0F) + iTranslateY,
         this.getColor(isActive)
      );
      CFG.drawTextWithShadow(
         oSB,
         this.sText_CurrentProgress,
         this.getPosX()
            + this.iText2Width
            + CFG.PADDING * 3
            + (int)(ImageManager.getImage(Images.research).getWidth() * this.getImageScale(0.65F, Images.research))
            + iTranslateX,
         this.getPosY() + (int)((this.getHeight() - this.iTextHeight * 0.65F) / 2.0F) + iTranslateY,
         CFG.COLOR_TEXT_NUM_OF_PROVINCES
      );
      CFG.drawTextWithShadow(
         oSB,
         this.sText_Spendings,
         this.getPosX()
            + this.iText2Width
            + this.iText_CurrentProgressWidth
            + CFG.PADDING * 3
            + (int)(ImageManager.getImage(Images.research).getWidth() * this.getImageScale(0.65F, Images.research))
            + iTranslateX,
         this.getPosY() + (int)((this.getHeight() - this.iTextHeight * 0.65F) / 2.0F) + iTranslateY,
         this.colorSpendings
      );
      CFG.drawTextWithShadow(
         oSB,
         this.sText_Progress,
         this.getPosX()
            + this.iText2Width
            + this.iText_SpendingsWidth
            + this.iText_CurrentProgressWidth
            + CFG.PADDING * 3
            + (int)(ImageManager.getImage(Images.research).getWidth() * this.getImageScale(0.65F, Images.research))
            + iTranslateX,
         this.getPosY() + (int)((this.getHeight() - this.iTextHeight * 0.65F) / 2.0F) + iTranslateY,
         CFG.COLOR_TEXT_NUM_OF_PROVINCES
      );
      CFG.drawTextWithShadow(
         oSB,
         this.getText(),
         this.getPosX()
            + this.getWidth()
            - CFG.PADDING * 3
            - (int)(this.getTextWidth() * 0.65F)
            - (int)(ImageManager.getImage(Images.technology).getWidth() * this.getImageScale(0.65F, Images.technology))
            + iTranslateX,
         this.getPosY() + (int)((this.getHeight() - this.iTextHeight * 0.65F) / 2.0F) + iTranslateY,
         CFG.COLOR_TEXT_MODIFIER_NEUTRAL
      );
      CFG.fontMain.getData().setScale(0.65F);
      CFG.drawTextWithShadow(
         oSB,
         this.sText_ProgressPerc,
         this.getPosX()
            + this.iText2Width
            + this.iText_SpendingsWidth
            + this.iText_CurrentProgressWidth
            + this.iText_ProgressWidth
            + CFG.PADDING * 3
            + (int)(ImageManager.getImage(Images.research).getWidth() * this.getImageScale(0.65F, Images.research))
            + iTranslateX,
         this.getPosY() + (int)((this.getHeight() - this.iTextHeight * 0.65F) / 2.0F) + iTranslateY,
         CFG.COLOR_TEXT_MODIFIER_NEUTRAL
      );
      CFG.fontMain.getData().setScale(1.0F);
   }

   public final float getImageScale(float fScale, int nImageID) {
      return this.iTextHeight * fScale / ImageManager.getImage(nImageID).getHeight();
   }

   @Override
   public void setMin(int iMin) {
      this.sText_Spendings = " + " + iMin;
      this.colorSpendings = iMin <= 0 ? CFG.COLOR_TEXT_MODIFIER_NEUTRAL : CFG.COLOR_TEXT_RESEARCH;
      CFG.glyphLayout.setText(CFG.fontMain, this.sText_Spendings);
      this.iText_SpendingsWidth = (int)(CFG.glyphLayout.width * 0.65F);
   }
}
