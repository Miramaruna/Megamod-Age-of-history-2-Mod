package age.of.civilizations2.jakowski.lukasz;

import java.util.ArrayList;
import java.util.List;

public class Menu_FlagPixel_Color {
   public List<Float> lR = new ArrayList<>();
   public List<Float> lG = new ArrayList<>();
   public List<Float> lB = new ArrayList<>();
   public List<Float> lA = new ArrayList<>();

   public Menu_FlagPixel_Color() {
      for (int i = 0; i < CFG.CIV_FLAG_WIDTH; i++) {
         for (int j = 0; j < CFG.CIV_FLAG_HEIGHT; j++) {
            this.lR.add(1.0F);
            this.lG.add(1.0F);
            this.lB.add(1.0F);
            this.lA.add(1.0F);
         }
      }

      this.lA.set(0, 0.0F);
      this.lA.set(CFG.CIV_FLAG_WIDTH - 1, 0.0F);
      this.lA.set(CFG.CIV_FLAG_WIDTH * CFG.CIV_FLAG_HEIGHT - 1, 0.0F);
      this.lA.set(CFG.CIV_FLAG_WIDTH * CFG.CIV_FLAG_HEIGHT - 1 - CFG.CIV_FLAG_WIDTH + 1, 0.0F);
   }

   public final float getR(int ID) {
      return this.lR.get(ID);
   }

   public final void setR(int ID, float nR) {
      this.lR.set(ID, nR);
   }

   public final float getG(int ID) {
      return this.lG.get(ID);
   }

   public final void setG(int ID, float nG) {
      this.lG.set(ID, nG);
   }

   public final float getB(int ID) {
      return this.lB.get(ID);
   }

   public final void setB(int ID, float nB) {
      this.lB.set(ID, nB);
   }

   public final float getA(int ID) {
      return this.lA.get(ID);
   }

   public final void setA(int ID, float nA) {
      this.lA.set(ID, nA);
   }
}
