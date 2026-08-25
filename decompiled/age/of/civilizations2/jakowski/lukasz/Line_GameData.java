package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;

public class Line_GameData implements Serializable {
   public static final long serialVersionUID = 0L;
   public String sImageName;
   public boolean reapeatImage = true;
   public boolean flipX = false;
   public boolean movable = true;

   public final String getImageName() {
      return this.sImageName;
   }

   public final void setImageName(String sImageName) {
      this.sImageName = sImageName;
   }

   public final boolean getRapeatImage() {
      return this.reapeatImage;
   }

   public final void setReapeatImage(boolean reapeatImage) {
      this.reapeatImage = reapeatImage;
   }

   public final boolean getFlipX() {
      return this.flipX;
   }

   public final void setFlipX(boolean flipX) {
      this.flipX = flipX;
   }

   public final boolean getMovable() {
      return this.movable;
   }

   public final void setMovable(boolean movable) {
      this.movable = movable;
   }
}
