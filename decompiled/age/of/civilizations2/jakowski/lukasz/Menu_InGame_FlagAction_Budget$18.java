package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;

class Menu_InGame_FlagAction_Budget$18 extends Slider_FlagAction_Goods {
   final Menu_InGame_FlagAction_Budget this$0;

   Menu_InGame_FlagAction_Budget$18(Menu_InGame_FlagAction_Budget var1, String var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9) {
      super(var2, var3, var4, var5, var6, var7, var8, var9);
      this.this$0 = var1;
   }

   @Override
   protected boolean getClickable() {
      boolean var1;
      if (CFG.SPECTATOR_MODE) {
         var1 = false;
      } else {
         var1 = super.getClickable();
      }

      return var1;
   }

   @Override
   protected Color getColor(boolean var1) {
      Color var2;
      if (this.getCurrent()
         >= (int)(
            CFG.ideologiesManager
                  .getIdeology(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getIdeologyID())
                  .getMin_Goods(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
               * 100.0F
         )) {
         var2 = super.getColor(var1);
      } else {
         var2 = CFG.COLOR_TEXT_MODIFIER_NEGATIVE2;
      }

      return var2;
   }

   @Override
   protected Color getColorLEFT() {
      Color var1;
      if (this.getCurrent()
         < (int)(
            CFG.ideologiesManager
                  .getIdeology(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getIdeologyID())
                  .getMin_Goods(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
               * 100.0F
         )) {
         var1 = CFG.getColorStep(
            new Color(0.54901963F, 0.078431375F, 0.078431375F, 1.0F),
            new Color(0.7058824F, 0.078431375F, 0.078431375F, 1.0F),
            (int)(
                  CFG.ideologiesManager
                        .getIdeology(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getIdeologyID())
                        .getMin_Goods(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
                     * 100.0F
               )
               - this.getCurrent(),
            (int)(
               CFG.ideologiesManager
                     .getIdeology(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getIdeologyID())
                     .getMin_Goods(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
                  * 100.0F
            ),
            1.0F
         );
      } else {
         var1 = CFG.getColorStep(
            new Color(0.019607844F, 0.39215687F, 0.1764706F, 1.0F), new Color(0.039215688F, 0.5686275F, 0.29411766F, 1.0F), this.getCurrent(), 100, 1.0F
         );
      }

      return var1;
   }

   @Override
   protected String getDrawText() {
      StringBuilder var1 = new StringBuilder();
      var1.append(this.getCurrent());
      var1.append("%");
      return var1.toString();
   }
}
