package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

class Menu_InGame_TradeRequest$21 extends Button_FlagActionSliderStyle {
   final Menu_InGame_TradeRequest this$0;

   Menu_InGame_TradeRequest$21(Menu_InGame_TradeRequest var1, String var2, int var3, int var4, int var5, int var6, boolean var7) {
      super(var2, var3, var4, var5, var6, var7);
      this.this$0 = var1;
   }

   @Override
   protected void buildElementHover() {
      ArrayList var1 = new ArrayList();
      ArrayList var2 = new ArrayList();
      var2.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("SendProposal") + ":", CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
      var2.add(new MenuElement_Hover_v2_Element_Type_Flag(Menu_InGame_TradeRequest.iOnCivID, CFG.PADDING, CFG.PADDING));
      var2.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.game.getCiv(Menu_InGame_TradeRequest.iOnCivID).getCivName()));
      var1.add(new MenuElement_Hover_v2_Element2(var2));
      var2.clear();
      var2.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("DiplomacyPoints") + ": ", CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
      var2.add(new MenuElement_Hover_v2_Element_Type_Text("-1.0", CFG.COLOR_TEXT_MODIFIER_NEGATIVE2));
      var2.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_diplomacy_points, CFG.PADDING, 0));
      var1.add(new MenuElement_Hover_v2_Element2(var2));
      var2.clear();
      this.menuElementHover = new MenuElement_Hover_v2(var1);
   }

   @Override
   protected void drawText(SpriteBatch var1, int var2, int var3, boolean var4) {
      ImageManager.getImage(Images.diplo_trade)
         .draw(
            var1,
            this.getPosX()
               + this.getWidth() / 2
               - (int)((this.getTextWidth() * 0.8F + ImageManager.getImage(Images.diplo_trade).getWidth() + CFG.PADDING) / 2.0F)
               + var2,
            this.getPosY() + this.getHeight() / 2 - ImageManager.getImage(Images.diplo_trade).getHeight() / 2 + var3
         );
      CFG.fontMain.getData().setScale(0.8F);
      String var5 = this.getText();
      int var6 = this.getPosX();
      int var7;
      if (this.getTextPos() < 0) {
         var7 = this.getWidth() / 2
            - (int)((this.getTextWidth() * 0.8F + ImageManager.getImage(Images.diplo_trade).getWidth() + CFG.PADDING) / 2.0F)
            + ImageManager.getImage(Images.diplo_trade).getWidth()
            + CFG.PADDING;
      } else {
         var7 = this.getTextPos();
      }

      CFG.drawText(
         var1, var5, var7 + var6 + var2, this.getPosY() + this.getHeight() / 2 - (int)(this.getTextHeight() * 0.8F / 2.0F) + var3, this.getColor(var4)
      );
      CFG.fontMain.getData().setScale(1.0F);
   }

   @Override
   protected boolean getClickable() {
      boolean var1;
      if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getDiplomacyPoints() >= 10 && CFG.tradeRequest.canBeSend()) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   @Override
   protected int getPosX() {
      return this.this$0.getElementW() + CFG.PADDING / 2;
   }

   @Override
   protected int getPosY() {
      int var1;
      if (this.this$0.getH() - this.getHeight() - CFG.PADDING > super.getPosY()) {
         var1 = this.this$0.getH() - this.getHeight() - CFG.PADDING;
      } else {
         var1 = super.getPosY();
      }

      return var1;
   }

   @Override
   protected int getSFX() {
      return SoundsManager.getSend();
   }

   @Override
   protected int getWidth() {
      return this.this$0.getElementW() - CFG.PADDING - CFG.PADDING / 2;
   }
}
