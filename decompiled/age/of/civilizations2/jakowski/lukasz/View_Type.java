package age.of.civilizations2.jakowski.lukasz;

public class View_Type {
   public boolean drawCivNamesOver = false;
   public boolean canMoveArmy = false;
   public Game_Render.Renderer oRenderer;
   public Game_Render_Province.DrawProvinces drawProvinces;

   View_Type() {
   }

   public void enableViewAction() {
   }

   public void disableViewAction() {
   }

   public void updateActiveCivInfo_ExtraAction(int newCivID) {
   }

   public void updateActiveProvinceID_ExtraAction(int oldProvince, int newProvince) {
   }

   public void setActiveProvinceAction() {
   }

   public MenuElement_Hover getProvinceInformations() {
      return null;
   }
}
