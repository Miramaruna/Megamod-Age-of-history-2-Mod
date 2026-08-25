package age.of.civilizations2.jakowski.lukasz;

class Menu_About$24 extends Button_Transparent {
   Menu_About$24(Menu_About this$0, int arg0, int arg1, int arg2, int arg3, boolean arg4) {
      super(arg0, arg1, arg2, arg3, arg4);
      this.this$0 = this$0;
   }

   @Override
   public void actionElement(int iID) {
      this.this$0.onBackPressed();
   }
}
