package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;
import java.util.List;

public class MenuElement_Hover_v2_Element2 {
   public List<MenuElement_Hover_v2_Element_Type> lElements = new ArrayList<>();

   public MenuElement_Hover_v2_Element2(List<MenuElement_Hover_v2_Element_Type> nElements) {
      for (int i = 0; i < nElements.size(); i++) {
         this.lElements.add(nElements.get(i));
      }
   }

   public final void draw(SpriteBatch oSB, int nPosX, int nPosY, float nAlpha) {
      int tX = 0;

      for (int i = 0; i < this.lElements.size(); i++) {
         this.lElements.get(i).draw(oSB, nPosX + tX, nPosY, nAlpha);
         tX += this.lElements.get(i).getWidth();
      }
   }

   public final int getWidth() {
      int out = 0;

      for (int i = 0; i < this.lElements.size(); i++) {
         out += this.lElements.get(i).getWidth();
      }

      return out;
   }
}
