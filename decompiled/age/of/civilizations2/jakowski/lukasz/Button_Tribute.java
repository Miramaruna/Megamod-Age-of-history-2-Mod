package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Button_Tribute extends Button_Build {
   public static final float FONTSIZE2 = 0.6F;
   public String sProvinceName;
   public String sDate;
   public int iDateWidth;
   public String sEconomy;
   public int iEconomyWidth;
   public Color oColor = Color.WHITE;

   public Button_Tribute(String sText, int nImageID, int iPosX, int iPosY, int iWidth) {
      super(sText, nImageID, 0, 0, iPosX, iPosY, iWidth, true, false, 0, 0.0F);
      this.sDate = CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCivName();
      CFG.glyphLayout.setText(CFG.fontMain, this.sDate);
      this.iDateWidth = (int)(CFG.glyphLayout.width * 0.6F);
      this.setMin(40);
   }

   @Override
   public void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
      CFG.ideologiesManager
         .getIdeology(this.iImageID)
         .getCrownImageScaled()
         .draw(
            oSB,
            this.getPosX()
               + Button_Diplomacy.iDiploWidth / 2
               - CFG.ideologiesManager.getIdeology(this.iImageID).getCrownImageScaled().getWidth() / 2
               + iTranslateX,
            this.getPosY() + this.getHeight() / 2 - CFG.ideologiesManager.getIdeology(this.iImageID).getCrownImageScaled().getHeight() / 2 + iTranslateY
         );

      try {
         CFG.game
            .getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
            .getFlag()
            .draw(
               oSB,
               this.getPosX() + this.getWidth() - CFG.PADDING * 2 - CFG.CIV_FLAG_WIDTH + iTranslateX,
               this.getPosY()
                  + this.getHeight() / 2
                  - CFG.CIV_FLAG_HEIGHT / 2
                  - CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getFlag().getHeight()
                  + iTranslateY,
               CFG.CIV_FLAG_WIDTH,
               CFG.CIV_FLAG_HEIGHT
            );
      } catch (IndexOutOfBoundsException var6) {
         ImageManager.getImage(Images.randomCivilizationFlag)
            .draw(
               oSB,
               this.getPosX() + this.getWidth() - CFG.PADDING * 2 - CFG.CIV_FLAG_WIDTH + iTranslateX,
               this.getPosY() + this.getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 - ImageManager.getImage(Images.randomCivilizationFlag).getHeight() + iTranslateY,
               CFG.CIV_FLAG_WIDTH,
               CFG.CIV_FLAG_HEIGHT
            );
      }

      ImageManager.getImage(Images.flag_rect)
         .draw(
            oSB,
            this.getPosX() + this.getWidth() - CFG.PADDING * 2 - CFG.CIV_FLAG_WIDTH + iTranslateX,
            this.getPosY() + this.getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 - ImageManager.getImage(Images.flag_rect).getHeight() + iTranslateY,
            CFG.CIV_FLAG_WIDTH,
            CFG.CIV_FLAG_HEIGHT
         );
      CFG.fontMain.getData().setScale(0.7F);
      CFG.drawTextWithShadow(
         oSB,
         this.getText(),
         this.getPosX() + CFG.PADDING + Button_Diplomacy.iDiploWidth + iTranslateX,
         this.getPosY() + this.getHeight() / 2 - (int)(this.getTextHeight() * 0.7F) - CFG.PADDING / 2 + iTranslateY,
         this.getColor(isActive)
      );
      CFG.drawTextWithShadow(
         oSB,
         this.sEconomy,
         this.getPosX() + CFG.PADDING + Button_Diplomacy.iDiploWidth + (int)(this.getTextWidth() * 0.7F) + iTranslateX,
         this.getPosY() + this.getHeight() / 2 - (int)(this.getTextHeight() * 0.7F) - CFG.PADDING / 2 + iTranslateY,
         this.oColor
      );
      ImageManager.getImage(Images.top_gold)
         .draw(
            oSB,
            this.getPosX() + CFG.PADDING + Button_Diplomacy.iDiploWidth + (int)(this.getTextWidth() * 0.7F) + CFG.PADDING + this.iEconomyWidth + iTranslateX,
            this.getPosY()
               + this.getHeight() / 2
               - (int)(this.getTextHeight() * 0.7F) / 2
               - (int)(ImageManager.getImage(Images.top_gold).getHeight() * this.getImageScale(Images.top_gold, 0.7F)) / 2
               - CFG.PADDING / 2
               - ImageManager.getImage(Images.top_gold).getHeight()
               + iTranslateY,
            (int)(ImageManager.getImage(Images.top_gold).getWidth() * this.getImageScale(Images.top_gold, 0.7F)),
            (int)(ImageManager.getImage(Images.top_gold).getHeight() * this.getImageScale(Images.top_gold, 0.7F))
         );
      CFG.fontMain.getData().setScale(0.6F);
      CFG.drawTextWithShadow(
         oSB,
         this.sDate,
         this.getPosX() + CFG.PADDING + Button_Diplomacy.iDiploWidth + iTranslateX,
         this.getPosY() + this.getHeight() / 2 + CFG.PADDING / 2 + iTranslateY,
         CFG.COLOR_TEXT_OPTIONS_NS_HOVER
      );
      CFG.fontMain.getData().setScale(1.0F);
   }

   @Override
   public void setMin(int iMin) {
      this.sEconomy = (iMin > 0 ? "+" : "") + CFG.getNumberWithSpaces("" + iMin);
      this.oColor = iMin > -100 ? CFG.COLOR_INGAME_GOLD : CFG.COLOR_TEXT_MODIFIER_NEUTRAL;
      CFG.glyphLayout.setText(CFG.fontMain, this.sEconomy);
      this.iEconomyWidth = (int)(CFG.glyphLayout.width * 0.7F);
   }
}
