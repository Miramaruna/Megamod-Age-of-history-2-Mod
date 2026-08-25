package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

interface MenuElement_Hover {
   void draw(SpriteBatch var1, int var2, int var3);

   void drawAlwaysBelow(SpriteBatch var1, int var2, int var3);

   void drawAlwaysOver(SpriteBatch var1, int var2, int var3);

   void drawAlwaysOver_Mobile(SpriteBatch var1, int var2, int var3);

   void drawProvinceInfo(SpriteBatch var1, int var2, int var3);

   void draw_Hover(SpriteBatch var1, int var2, int var3);

   void draw_HoverWithoutAnimation(SpriteBatch var1, int var2, int var3);
}
