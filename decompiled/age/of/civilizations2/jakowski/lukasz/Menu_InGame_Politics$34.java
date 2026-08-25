package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;

class Menu_InGame_Politics$34 extends Title_CountryMenu {
   Menu_InGame_Politics$34(Menu_InGame_Politics this$0, String sText, int iTextPositionX, int iPosX, int iPosY, int iWidth, int iHeight, Color color) {
      super(sText, iTextPositionX, iPosX, iPosY, iWidth, iHeight, color);
      this.this$0 = this$0;
   }

   @Override
   public Color getColor(boolean isActive) {
      return CFG.COLOR_INGAME_DIPLOMACY_POINTS;
   }
}
