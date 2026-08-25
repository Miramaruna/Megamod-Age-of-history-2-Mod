package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;

class Menu_About$20 extends Text_Scale {
   Menu_About$20(Menu_About this$0, String sText, int iTextPositionX, int iPosX, int iPosY, float nScale) {
      super(sText, iTextPositionX, iPosX, iPosY, nScale);
      this.this$0 = this$0;
   }

   @Override
   public Color getColor(boolean isActive) {
      return isActive
         ? CFG.COLOR_BUTTON_GAME_TEXT_IMPORTANT_ACTIVE
         : (
            this.getClickable()
               ? (this.getIsHovered() ? CFG.COLOR_BUTTON_GAME_TEXT_IMPORTANT_HOVER : CFG.COLOR_TEXT_MODIFIER_NEUTRAL)
               : new Color(0.78F, 0.78F, 0.78F, 0.7F)
         );
   }
}
