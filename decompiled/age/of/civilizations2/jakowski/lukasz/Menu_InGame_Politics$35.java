package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;

class Menu_InGame_Politics$35 extends Button_FlagActionSliderStyle {
   Menu_InGame_Politics$35(Menu_InGame_Politics this$0, String sText, int iTextPositionX, int iPosX, int iPosY, int iWidth, boolean isClickable) {
      super(sText, iTextPositionX, iPosX, iPosY, iWidth, isClickable);
      this.this$0 = this$0;
   }

   @Override
   public int getWidth() {
      return (this.this$0.getW() - CFG.PADDING * 4) / 3;
   }

   @Override
   public Color getColor(boolean isActive) {
      return this.getClickable()
         ? (
            1 == CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).LevelMilitaryTactic
               ? CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
               : (this.getIsHovered() ? CFG.COLOR_TEXT_CIV_INFO_HOVER : CFG.COLOR_TEXT_CIV_INFO)
         )
         : new Color(CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.r, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.g, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.b, 0.65F);
   }
}
