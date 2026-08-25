package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Text_Clear extends Text {
   public int iTurnID = 0;
   public int iLogID = 0;

   public Text_Clear(int iTurnID, int iLogID, int iPosX, int iPosY, int iWidth, int iHeight) {
      this.typeOfElement = MenuElement.TypeOfElement.TEXT;
      this.iTurnID = iTurnID;
      this.iLogID = iLogID;
      this.setPosX(iPosX);
      this.setPosY(iPosY);
      this.setWidth(iWidth);
      this.setHeight(iHeight);
      this.setText("");
   }

   @Override
   public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
   }

   @Override
   public int getCurrent() {
      return this.iLogID;
   }

   @Override
   public int getTextPos() {
      return this.iTurnID;
   }
}
