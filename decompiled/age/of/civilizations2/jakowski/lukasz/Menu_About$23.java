package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;

class Menu_About$23 extends Text_Scrollable {
   Menu_About$23(Menu_About this$0, String sText, int iPosX, int iPosY, int iWidth, Color textColor) {
      super(sText, iPosX, iPosY, iWidth, textColor);
      this.this$0 = this$0;
   }

   @Override
   public Color getColor(boolean isActive) {
      return isActive
         ? CFG.COLOR_TEXT_CIV_NAME_ACTIVE
         : (
            this.getClickable()
               ? (this.getIsHovered() ? CFG.COLOR_TEXT_CIV_NAME_HOVERED : CFG.COLOR_TEXT_MODIFIER_NEUTRAL)
               : new Color(0.78F, 0.78F, 0.78F, 0.7F)
         );
   }
}
