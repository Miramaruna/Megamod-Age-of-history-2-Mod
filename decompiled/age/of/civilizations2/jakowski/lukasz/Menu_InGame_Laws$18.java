package age.of.civilizations2.jakowski.lukasz;

class Menu_InGame_Laws$18 extends Button_FlagActionSliderStyle {
   final Menu_InGame_Laws this$0;

   Menu_InGame_Laws$18(Menu_InGame_Laws var1, String var2, int var3, int var4, int var5, int var6, boolean var7) {
      super(var2, var3, var4, var5, var6, var7);
      this.this$0 = var1;
   }

   @Override
   protected int getWidth() {
      return this.this$0.getElementW() * 2 - CFG.PADDING * 2;
   }
}
