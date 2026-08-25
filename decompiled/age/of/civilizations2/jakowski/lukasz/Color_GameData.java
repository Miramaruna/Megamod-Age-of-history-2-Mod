package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import java.io.Serializable;

public class Color_GameData implements Serializable {
   public static final long serialVersionUID = 0L;
   public float fR;
   public float fG;
   public float fB;

   public Color_GameData(float fR, float fG, float fB) {
      this.fR = fR;
      this.fB = fB;
      this.fG = fG;
   }

   public final float getR() {
      return this.fR;
   }

   public final void setR(float fR) {
      this.fR = fR;
   }

   public final float getG() {
      return this.fG;
   }

   public final void setG(float fG) {
      this.fG = fG;
   }

   public final float getB() {
      return this.fB;
   }

   public final void setB(float fB) {
      this.fB = fB;
   }

   protected Color getColor() {
      return new Color(this.fR, this.fG, this.fB, 1.0F);
   }
}
