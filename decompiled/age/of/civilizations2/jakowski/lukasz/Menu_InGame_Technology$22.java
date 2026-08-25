package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;

class Menu_InGame_Technology$22 extends Slider_FlagAction_Clear_Tech {
   Menu_InGame_Technology$22(
      Menu_InGame_Technology this$0, float fModifier, String sText, int iPosX, int iPosY, int iWidth, int iHeight, int iMin, int iMax, int iCurrent
   ) {
      super(fModifier, sText, iPosX, iPosY, iWidth, iHeight, iMin, iMax, iCurrent);
      this.this$0 = this$0;
   }

   @Override
   protected int getWidth() {
      return Math.max(this.this$0.getElementW() * 2 - CFG.BUTTON_WIDTH * 3 / 4 - CFG.PADDING * 4 - Button_Diplomacy.iDiploWidth, 0);
   }

   @Override
   protected Color getColorLEFT() {
      return new Color(CFG.COLOR_TEXT_RESEARCH.r, CFG.COLOR_TEXT_RESEARCH.g, CFG.COLOR_TEXT_RESEARCH.b, 1.0F);
   }

   @Override
   protected void actionElement(int iID) {
   }
}
