package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Pixmap;
import java.util.ArrayList;

public class BPicture {
   public String name;
   public int width;
   public int height;
   public ArrayList<Integer> pixmap;
   public Pixmap.Format format;

   public BPicture(String name, int width, Pixmap.Format format, int height, ArrayList<Integer> pixmap) {
      this.name = name;
      this.width = width;
      this.height = height;
      this.pixmap = pixmap;
      this.format = format;
   }

   public String getName() {
      return this.name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public int getWidth() {
      return this.width;
   }

   public void setWidth(int width) {
      this.width = width;
   }

   public int getHeight() {
      return this.height;
   }

   public void setHeight(int height) {
      this.height = height;
   }

   public ArrayList<Integer> getPixmap() {
      return this.pixmap;
   }

   public void setPixmap(ArrayList<Integer> pixmap) {
      this.pixmap = pixmap;
   }

   public Pixmap.Format getFormat() {
      return this.format;
   }

   public void setFormat(Pixmap.Format format) {
      this.format = format;
   }
}
