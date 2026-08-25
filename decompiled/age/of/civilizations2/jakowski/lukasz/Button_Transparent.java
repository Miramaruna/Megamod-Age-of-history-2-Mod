package age.of.civilizations2.jakowski.lukasz;

public class Button_Transparent extends Button {
   public Button_Transparent(int iPosX, int iPosY, int iWidth, int iHeight, boolean isClickable) {
      super.init("", 0, iPosX, iPosY, iWidth, iHeight, isClickable, true, false, false, null);
      this.typeOfElement = MenuElement.TypeOfElement.BUTTON_TRANSPARENT;
   }

   public Button_Transparent(int iTextPos, int iPosX, int iPosY, int iWidth, int iHeight, boolean isClickable) {
      super.init("", iTextPos, iPosX, iPosY, iWidth, iHeight, isClickable, true, false, false, null);
      this.typeOfElement = MenuElement.TypeOfElement.BUTTON_TRANSPARENT;
   }
}
