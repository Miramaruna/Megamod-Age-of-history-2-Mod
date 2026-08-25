package age.of.civilizations2.jakowski.lukasz;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

class AoCGame$4 implements Runnable {
   final AoCGame this$0;

   AoCGame$4(AoCGame var1) {
      this.this$0 = var1;
   }

   // $VF: Duplicated exception handlers to handle obfuscated exceptions
   @Override
   public void run() {
      while (true) {
         try {
            TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(2, 7));
            Images.backgroundLast = Images.background;
            Images.background = ThreadLocalRandom.current().nextInt(0, 15);
            Images.backgroundAlpha = 0.0F;
         } catch (InterruptedException var3) {
            throw new RuntimeException(var3);
         }

         while (true) {
            try {
               if (!(Images.backgroundAlpha <= 1.0F)) {
                  break;
               }

               TimeUnit.MILLISECONDS.sleep(10L);
               Images.backgroundAlpha += 0.01F;
            } catch (InterruptedException var2) {
               throw new RuntimeException(var2);
            }
         }
      }
   }
}
