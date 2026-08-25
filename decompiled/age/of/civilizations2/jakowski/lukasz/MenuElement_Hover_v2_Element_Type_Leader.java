package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.GdxRuntimeException;

public class MenuElement_Hover_v2_Element_Type_Leader implements MenuElement_Hover_v2_Element_Type {
   @Override
   public void draw(SpriteBatch oSB, int nPosX, int nPosY, float nAlpha) {
      try {
         if (CFG.activeCivLeader != null) {
            oSB.setColor(new Color(1.0F, 1.0F, 1.0F, nAlpha));
            CFG.activeCivLeader.draw(oSB, nPosX, nPosY + CFG.PADDING);
            oSB.setColor(Color.WHITE);
         }
      } catch (NullPointerException var6) {
      } catch (GdxRuntimeException var7) {
      }
   }

   @Override
   public int getWidth() {
      try {
         return CFG.activeCivLeader != null ? CFG.activeCivLeader.getWidth() : 0;
      } catch (NullPointerException var2) {
      } catch (GdxRuntimeException var3) {
      }

      return 0;
   }
}
