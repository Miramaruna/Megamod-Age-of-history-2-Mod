package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Button_Diplomacy_MoveCapital extends Button_Statistics {
   public static final float FONT_SCALE = 0.7F;
   public static final float FONT_SCALE2 = 0.6F;
   public int iCivA;
   public String sProvinceName;
   public String sCost;
   public int iCostWidth = 0;

   public Button_Diplomacy_MoveCapital(String sText, int nCivID, int nProvinceID, int nCost, int iPosX, int iPosY, int iWidth) {
      super(sText, 0, iPosX, iPosY, iWidth, Math.max(CFG.TEXT_HEIGHT + CFG.PADDING * 4, (int)(CFG.BUTTON_HEIGHT * 0.6F)));
      this.iCivA = nCivID;
      this.sCost = "" + CFG.getNumberWithSpaces("" + nCost);
      CFG.glyphLayout.setText(CFG.fontMain, this.sCost);
      this.iCostWidth = (int)(CFG.glyphLayout.width * 0.6F);
      this.sProvinceName = ""
         + (
            CFG.game.getProvince(nProvinceID).getCitiesSize() > 0
               ? CFG.game.getProvince(nProvinceID).getCity(0).getCityName()
               : CFG.game.getProvince(nProvinceID).getName()
         );
   }

   @Override
   public void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
      oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.45F));
      ImageManager.getImage(Images.slider_gradient)
         .draw(
            oSB,
            this.getPosX() + Button_Diplomacy.iDiploWidth - CFG.PADDING * 2 + iTranslateX,
            this.getPosY() + 1 - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY,
            CFG.PADDING * 2,
            this.getHeight() - 2,
            true,
            false
         );
      ImageManager.getImage(Images.slider_gradient)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() + 1 - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY,
            CFG.PADDING * 2,
            this.getHeight() - 2,
            false,
            false
         );
      oSB.setColor(new Color(CFG.COLOR_GRADIENT_TITLE_BLUE.r, CFG.COLOR_GRADIENT_TITLE_BLUE.g, CFG.COLOR_GRADIENT_TITLE_BLUE.b, 0.35F));
      ImageManager.getImage(Images.line_32_vertical)
         .draw(
            oSB,
            this.getPosX() + Button_Diplomacy.iDiploWidth + iTranslateX,
            this.getPosY() + 1 - ImageManager.getImage(Images.line_32_vertical).getHeight() + iTranslateY,
            1,
            this.getHeight() - 2
         );
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
      oSB.setColor(Color.WHITE);
      ImageManager.getImage(Images.editor_city)
         .draw(
            oSB,
            this.getPosX() + Button_Diplomacy.iDiploWidth / 2 - ImageManager.getImage(Images.editor_city).getWidth() / 2 + iTranslateX,
            this.getPosY() + this.getHeight() / 2 - ImageManager.getImage(Images.editor_city).getHeight() / 2 + iTranslateY
         );

      try {
         oSB.setColor(
            new Color(
               CFG.game.getCiv(this.iCivA).getR() / 255.0F, CFG.game.getCiv(this.iCivA).getG() / 255.0F, CFG.game.getCiv(this.iCivA).getB() / 255.0F, 1.0F
            )
         );
      } catch (IndexOutOfBoundsException var6) {
         oSB.setColor(
            new Color(
               CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_ALLIANCE.getR(),
               CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_ALLIANCE.getG(),
               CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_ALLIANCE.getB(),
               1.0F
            )
         );
      }

      ImageManager.getImage(Images.pix255_255_255)
         .draw(
            oSB,
            this.getPosX() + CFG.PADDING + Button_Diplomacy.iDiploWidth + iTranslateX,
            this.getPosY()
               + this.getHeight() / 2
               - (int)(ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect)) / 2
               - ImageManager.getImage(Images.pix255_255_255).getHeight()
               + iTranslateY,
            2,
            (int)(ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect))
         );
      oSB.setColor(Color.WHITE);
      CFG.game
         .getCiv(this.iCivA)
         .getFlag()
         .draw(
            oSB,
            this.getPosX() + CFG.PADDING + Button_Diplomacy.iDiploWidth + 2 + iTranslateX,
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
            this.getPosX() + CFG.PADDING + Button_Diplomacy.iDiploWidth + 2 + iTranslateX,
            this.getPosY()
               + this.getHeight() / 2
               - (int)(ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect)) / 2
               - ImageManager.getImage(Images.flag_rect).getHeight()
               + iTranslateY,
            (int)(ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(Images.flag_rect)),
            (int)(ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect))
         );
      CFG.fontMain.getData().setScale(0.7F);
      CFG.drawTextWithShadow(
         oSB,
         this.getText(),
         this.getPosX()
            + CFG.PADDING * 2
            + Button_Diplomacy.iDiploWidth
            + (int)(ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(Images.flag_rect))
            + 2
            + iTranslateX,
         this.getPosY() + this.getHeight() / 2 - (int)(CFG.TEXT_HEIGHT * 0.7F) / 2 + iTranslateY,
         CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
      );
      CFG.drawTextWithShadow(
         oSB,
         this.sProvinceName,
         this.getPosX()
            + CFG.PADDING * 2
            + Button_Diplomacy.iDiploWidth
            + (int)(ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(Images.flag_rect))
            + 2
            + (int)(this.getTextWidth() * 0.7F)
            + iTranslateX,
         this.getPosY() + this.getHeight() / 2 - (int)(CFG.TEXT_HEIGHT * 0.7F) / 2 + iTranslateY,
         this.getColor(isActive)
      );
      CFG.fontMain.getData().setScale(0.6F);
      ImageManager.getImage(Images.top_gold)
         .draw(
            oSB,
            this.getPosX()
               + this.getWidth()
               - CFG.PADDING * 2
               - (int)(ImageManager.getImage(Images.top_gold).getWidth() * this.getImageScale(Images.top_gold))
               + iTranslateX,
            this.getPosY()
               + this.getHeight() / 2
               - (int)(ImageManager.getImage(Images.top_gold).getHeight() * this.getImageScale(Images.top_gold)) / 2
               - ImageManager.getImage(Images.top_gold).getHeight()
               + iTranslateY,
            (int)(ImageManager.getImage(Images.top_gold).getWidth() * this.getImageScale(Images.top_gold)),
            (int)(ImageManager.getImage(Images.top_gold).getHeight() * this.getImageScale(Images.top_gold))
         );
      CFG.drawTextWithShadow(
         oSB,
         this.sCost,
         this.getPosX()
            + this.getWidth()
            - CFG.PADDING * 3
            - this.iCostWidth
            - (int)(ImageManager.getImage(Images.top_gold).getWidth() * this.getImageScale(Images.top_gold))
            + iTranslateX,
         this.getPosY() + this.getHeight() / 2 - (int)(CFG.TEXT_HEIGHT * 0.6F) / 2 + iTranslateY,
         CFG.COLOR_INGAME_GOLD
      );
      CFG.fontMain.getData().setScale(1.0F);
      oSB.setColor(Color.WHITE);
   }

   public final float getImageScale(int nImageID) {
      return (float)CFG.TEXT_HEIGHT / ImageManager.getImage(nImageID).getHeight() < 1.0F
         ? (float)CFG.TEXT_HEIGHT / ImageManager.getImage(nImageID).getHeight()
         : 1.0F;
   }

   @Override
   public Color getColor(boolean isActive) {
      return this.getClickable()
         ? (isActive ? CFG.COLOR_TEXT_OPTIONS_NS_ACTIVE : (this.getIsHovered() ? CFG.COLOR_TEXT_OPTIONS_NS_HOVER : CFG.COLOR_TEXT_MODIFIER_NEUTRAL))
         : CFG.COLOR_BUTTON_GAME_TEXT_NOT_CLICKABLE;
   }

   @Override
   public void buildElementHover() {
      this.menuElementHover = null;
   }

   @Override
   public int getSFX() {
      return SoundsManager.SOUND_CLICK2;
   }
}
