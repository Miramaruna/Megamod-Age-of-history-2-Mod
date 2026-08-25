package age.of.civilizations2.jakowski.lukasz;

public class PlusicModLoaderEventListener extends PlusicEventListener {
   private PlusicModLoader instance;

   public PlusicModLoaderEventListener(PlusicModLoader instance) {
      this.instance = instance;
   }

   @Override
   public void changedMenu(Menu menu) {
      this.instance.activeMenu = menu;
   }
}
