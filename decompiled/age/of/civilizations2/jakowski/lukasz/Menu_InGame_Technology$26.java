package age.of.civilizations2.jakowski.lukasz;

class Menu_InGame_Technology$26 extends Button_FlagActionSliderStyle {
   Menu_InGame_Technology$26(Menu_InGame_Technology this$0, String sText, int iTextPositionX, int iPosX, int iPosY, int iWidth, boolean isClickable) {
      super(sText, iTextPositionX, iPosX, iPosY, iWidth, isClickable);
      this.this$0 = this$0;
   }

   @Override
   protected int getWidth() {
      return this.this$0.getElementW() * 2 - CFG.PADDING * 2;
   }
}
