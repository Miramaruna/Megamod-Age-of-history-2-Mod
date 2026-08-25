package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Button_Menu_Classic_ReflectedCheckbox extends Button_Menu_Classic {
   public Button_Menu_Classic_ReflectedCheckbox(String sText, int iTextPositionX, int iPosX, int iPosY, int iWidth, int iHeight, boolean isClickable) {
      super(sText, iTextPositionX, iPosX, iPosY, iWidth, iHeight, isClickable);
   }

   public Button_Menu_Classic_ReflectedCheckbox(
      String sText, int iTextPositionX, int iPosX, int iPosY, int iWidth, int iHeight, boolean isClickable, boolean checkboxState
   ) {
      super(sText, iTextPositionX, iPosX, iPosY, iWidth, iHeight, isClickable, checkboxState);
   }

   @Override
   public Button.Checkbox buildCheckbox() {
      return this.checkbox
         ? new Button.Checkbox() {
            @Override
            public void drawCheckBox(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean scrollableY) {
               if (Button_Menu_Classic_ReflectedCheckbox.this.getCheckboxState()) {
                  oSB.setColor(new Color(0.55F, 0.8F, 0.0F, 0.25F));
               } else {
                  oSB.setColor(new Color(0.8F, 0.137F, 0.0F, 0.25F));
               }

               ImageManager.getImage(Images.slider_gradient)
                  .draw(
                     oSB,
                     Button_Menu_Classic_ReflectedCheckbox.this.getPosX()
                        + Button_Menu_Classic_ReflectedCheckbox.this.getWidth()
                        - Button_Menu_Classic_ReflectedCheckbox.this.getWidth() / 4
                        + iTranslateX,
                     Button_Menu_Classic_ReflectedCheckbox.this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + 1 + iTranslateY,
                     Button_Menu_Classic_ReflectedCheckbox.this.getWidth() / 4,
                     Button_Menu_Classic_ReflectedCheckbox.this.getHeight() - 2,
                     true,
                     false
                  );
               oSB.setColor(Color.WHITE);
            }
         }
         : new Button.Checkbox() {
            @Override
            public void drawCheckBox(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean scrollableY) {
            }
         };
   }
}
