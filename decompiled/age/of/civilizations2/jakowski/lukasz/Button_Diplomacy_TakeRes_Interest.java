package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

class Button_Diplomacy_TakeRes_Interest extends Button_Statistics {
   private static final float FONT_SCALE = 0.7F;
   private static final float FONT_SCALE2 = 0.6F;
   private int iDiploCostWidth;
   private int iInterestGoldWidth;
   private int iInterestWidth = 0;
   private String sDiploCost;
   private String sInterest;
   private String sInterestGold;

   protected Button_Diplomacy_TakeRes_Interest(String var1, String var2, int var3, int var4, int var5) {
      super(var1, 0, var3, var4, var5, Math.max(CFG.TEXT_HEIGHT + CFG.PADDING * 4, (int)(CFG.BUTTON_HEIGHT * 0.6F)));
      this.iInterestGoldWidth = 0;
      this.iDiploCostWidth = 0;
      this.sDiploCost = var2;
      CFG.glyphLayout.setText(CFG.fontMain, this.sDiploCost);
      this.iDiploCostWidth = (int)(CFG.glyphLayout.width * 0.6F);
      this.setCurrent(0);
      this.setMin(0);
   }

   private final float getImageScale(int var1) {
      float var2 = 1.0F;
      if ((float)CFG.TEXT_HEIGHT / ImageManager.getImage(var1).getHeight() < 1.0F) {
         var2 = (float)CFG.TEXT_HEIGHT / ImageManager.getImage(var1).getHeight();
      }

      return var2;
   }

   @Override
   protected void buildElementHover() {
      this.menuElementHover = null;
   }

   @Override
   protected void drawButtonBG(SpriteBatch var1, int var2, int var3, boolean var4) {
      var1.setColor(new Color(0.0F, 0.0F, 0.0F, 0.45F));
      ImageManager.getImage(Images.slider_gradient)
         .draw(
            var1,
            this.getPosX() + Button_Diplomacy.iDiploWidth - CFG.PADDING * 2 + var2,
            this.getPosY() + 1 - ImageManager.getImage(Images.slider_gradient).getHeight() + var3,
            CFG.PADDING * 2,
            this.getHeight() - 2,
            true,
            false
         );
      ImageManager.getImage(Images.slider_gradient)
         .draw(
            var1,
            this.getPosX() + var2,
            this.getPosY() + 1 - ImageManager.getImage(Images.slider_gradient).getHeight() + var3,
            CFG.PADDING * 2,
            this.getHeight() - 2,
            false,
            false
         );
      var1.setColor(new Color(CFG.COLOR_GRADIENT_TITLE_BLUE.r, CFG.COLOR_GRADIENT_TITLE_BLUE.g, CFG.COLOR_GRADIENT_TITLE_BLUE.b, 0.35F));
      ImageManager.getImage(Images.line_32_vertical)
         .draw(
            var1,
            this.getPosX() + Button_Diplomacy.iDiploWidth + var2,
            this.getPosY() + 1 - ImageManager.getImage(Images.line_32_vertical).getHeight() + var3,
            1,
            this.getHeight() - 2
         );
      var1.setColor(new Color(CFG.COLOR_GRADIENT_TITLE_BLUE.r, CFG.COLOR_GRADIENT_TITLE_BLUE.g, CFG.COLOR_GRADIENT_TITLE_BLUE.b, 0.25F));
      ImageManager.getImage(Images.line_32_off1)
         .draw(var1, this.getPosX() + var2, this.getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() + var3, this.getWidth(), this.getHeight());
      var1.setColor(Color.WHITE);
      var1.setColor(new Color(0.0F, 0.0F, 0.0F, 0.55F));
      ImageManager.getImage(Images.gradient)
         .draw(
            var1,
            this.getPosX() + var2,
            this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + var3,
            this.getWidth(),
            this.getHeight() * 3 / 5,
            false,
            false
         );
      var1.setColor(new Color(0.0F, 0.0F, 0.0F, 0.275F));
      ImageManager.getImage(Images.slider_gradient)
         .draw(
            var1,
            this.getPosX() + var2,
            this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + var3,
            this.getWidth() / 4,
            this.getHeight(),
            false,
            false
         );
      ImageManager.getImage(Images.slider_gradient)
         .draw(
            var1,
            this.getPosX() + this.getWidth() - this.getWidth() / 4 + var2,
            this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + var3,
            this.getWidth() / 4,
            this.getHeight(),
            true,
            false
         );
      super.drawButtonBG(var1, var2, var3, var4);
      var1.setColor(new Color(CFG.COLOR_GRADIENT_TITLE_BLUE.r, CFG.COLOR_GRADIENT_TITLE_BLUE.g, CFG.COLOR_GRADIENT_TITLE_BLUE.b, 0.3F));
      ImageManager.getImage(Images.gradient)
         .draw(
            var1, this.getPosX() + var2, this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + var3, this.getWidth(), CFG.PADDING, false, false
         );
      ImageManager.getImage(Images.gradient)
         .draw(
            var1,
            this.getPosX() + var2,
            this.getPosY() + this.getHeight() - CFG.PADDING - ImageManager.getImage(Images.gradient).getHeight() + var3,
            this.getWidth(),
            CFG.PADDING,
            false,
            true
         );
      var1.setColor(new Color(CFG.COLOR_GRADIENT_TITLE_BLUE.r, CFG.COLOR_GRADIENT_TITLE_BLUE.g, CFG.COLOR_GRADIENT_TITLE_BLUE.b, 0.45F));
      ImageManager.getImage(Images.line_32_off1)
         .draw(
            var1,
            this.getPosX() + var2,
            this.getPosY() + this.getHeight() - 1 - ImageManager.getImage(Images.line_32_off1).getHeight() + var3,
            this.getWidth() - 4,
            1
         );
      var1.setColor(new Color(0.0F, 0.0F, 0.0F, 0.7F));
      ImageManager.getImage(Images.line_32_off1)
         .draw(
            var1,
            this.getPosX() + var2,
            this.getPosY() + this.getHeight() - 2 - ImageManager.getImage(Images.line_32_off1).getHeight() + var3,
            this.getWidth() - 4,
            1
         );
      var1.setColor(Color.WHITE);
   }

   @Override
   protected void drawText(SpriteBatch var1, int var2, int var3, boolean var4) {
      var1.setColor(Color.WHITE);
      ImageManager.getImage(Images.diplo_loan)
         .draw(
            var1,
            this.getPosX() + Button_Diplomacy.iDiploWidth / 2 - ImageManager.getImage(Images.diplo_loan).getWidth() / 2 + var2,
            this.getPosY() + this.getHeight() / 2 - ImageManager.getImage(Images.diplo_loan).getHeight() / 2 + var3
         );
      CFG.fontMain.getData().setScale(0.7F);
      CFG.drawTextWithShadow(
         var1,
         this.getText(),
         this.getPosX() + Button_Diplomacy.iDiploWidth + CFG.PADDING + var2,
         this.getPosY() + this.getHeight() / 2 - (int)(CFG.TEXT_HEIGHT * 0.7F / 2.0F) + var3,
         CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
      );
      CFG.drawTextWithShadow(
         var1,
         this.sInterest,
         this.getPosX() + Button_Diplomacy.iDiploWidth + CFG.PADDING + (int)(this.getTextWidth() * 0.7F) + var2,
         this.getPosY() + this.getHeight() / 2 - (int)(CFG.TEXT_HEIGHT * 0.7F / 2.0F) + var3,
         CFG.COLOR_TEXT_MODIFIER_NEUTRAL2
      );
      CFG.drawTextWithShadow(
         var1,
         this.sInterestGold,
         this.getPosX() + Button_Diplomacy.iDiploWidth + CFG.PADDING + (int)(this.getTextWidth() * 0.7F) + this.iInterestWidth + var2,
         this.getPosY() + this.getHeight() / 2 - (int)(CFG.TEXT_HEIGHT * 0.7F / 2.0F) + var3,
         CFG.COLOR_TEXT_MODIFIER_NEUTRAL
      );
      ImageManager.getImage(Images.Resource)
         .draw(
            var1,
            this.getPosX()
               + Button_Diplomacy.iDiploWidth
               + CFG.PADDING
               + (int)(this.getTextWidth() * 0.7F)
               + this.iInterestWidth
               + this.iInterestGoldWidth
               + var2,
            this.getPosY()
               + this.getHeight() / 2
               - (int)(ImageManager.getImage(Images.Resource).getHeight() * this.getImageScale(Images.Resource)) / 2
               - ImageManager.getImage(Images.Resource).getHeight()
               + var3,
            (int)(ImageManager.getImage(Images.Resource).getWidth() * this.getImageScale(Images.Resource)),
            (int)(ImageManager.getImage(Images.Resource).getHeight() * this.getImageScale(Images.Resource))
         );
      CFG.drawTextWithShadow(
         var1,
         "]",
         this.getPosX()
            + Button_Diplomacy.iDiploWidth
            + CFG.PADDING
            + (int)(this.getTextWidth() * 0.7F)
            + this.iInterestWidth
            + this.iInterestGoldWidth
            + (int)(ImageManager.getImage(Images.Resource).getWidth() * this.getImageScale(Images.Resource))
            + var2,
         this.getPosY() + this.getHeight() / 2 - (int)(CFG.TEXT_HEIGHT * 0.7F / 2.0F) + var3,
         CFG.COLOR_TEXT_MODIFIER_NEUTRAL
      );
      CFG.fontMain.getData().setScale(0.6F);
      ImageManager.getImage(Images.top_movement_points)
         .draw(
            var1,
            this.getPosX()
               + this.getWidth()
               - CFG.PADDING * 2
               - (int)(ImageManager.getImage(Images.top_movement_points).getWidth() * this.getImageScale(Images.top_movement_points))
               + var2,
            this.getPosY()
               + this.getHeight() / 2
               - (int)(ImageManager.getImage(Images.top_movement_points).getHeight() * this.getImageScale(Images.top_movement_points)) / 2
               - ImageManager.getImage(Images.top_movement_points).getHeight()
               + var3,
            (int)(ImageManager.getImage(Images.top_movement_points).getWidth() * this.getImageScale(Images.top_movement_points)),
            (int)(ImageManager.getImage(Images.top_movement_points).getHeight() * this.getImageScale(Images.top_movement_points))
         );
      String var5 = this.sDiploCost;
      int var6 = this.getPosX();
      int var7 = this.getWidth();
      int var8 = CFG.PADDING;
      int var9 = this.iDiploCostWidth;
      int var10 = (int)(ImageManager.getImage(Images.top_movement_points).getWidth() * this.getImageScale(Images.top_movement_points));
      int var11 = this.getPosY();
      int var12 = this.getHeight() / 2;
      int var13 = (int)(CFG.TEXT_HEIGHT * 0.6F) / 2;
      Color var14;
      if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMovePoints() >= 6) {
         var14 = CFG.COLOR_INGAME_MOVEMENT;
      } else {
         var14 = CFG.COLOR_TEXT_MODIFIER_NEGATIVE2;
      }

      CFG.drawTextWithShadow(var1, var5, var6 + var7 - var8 * 3 - var9 - var10 + var2, var11 + var12 - var13 + var3, var14);
      CFG.fontMain.getData().setScale(1.0F);
      var1.setColor(Color.WHITE);
   }

   @Override
   protected Color getColor(boolean var1) {
      Color var2;
      if (var1) {
         var2 = CFG.COLOR_TEXT_OPTIONS_NS_ACTIVE;
      } else if (this.getIsHovered()) {
         var2 = CFG.COLOR_TEXT_OPTIONS_NS_HOVER;
      } else {
         var2 = CFG.COLOR_TEXT_OPTIONS_NS;
      }

      return var2;
   }

   @Override
   protected int getSFX() {
      return SoundsManager.SOUND_CLICK2;
   }

   @Override
   protected void setCurrent(int var1) {
      this.sInterest = "" + var1 / 100.0F + "% ";
      CFG.glyphLayout.setText(CFG.fontMain, this.sInterest);
      this.iInterestWidth = (int)(CFG.glyphLayout.width * 0.7F);
   }

   @Override
   protected void setMin(int var1) {
      this.sInterestGold = " [+" + var1 + " ";
      CFG.glyphLayout.setText(CFG.fontMain, this.sInterestGold);
      this.iInterestGoldWidth = (int)(CFG.glyphLayout.width * 0.7F);
   }
}
