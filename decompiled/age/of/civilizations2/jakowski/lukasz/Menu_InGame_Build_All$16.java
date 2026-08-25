package age.of.civilizations2.jakowski.lukasz;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

class Menu_InGame_Build_All$16 implements Runnable {
   final Menu_InGame_Build_All this$0;
   final ArrayList val$sounds;

   Menu_InGame_Build_All$16(Menu_InGame_Build_All var1, ArrayList var2) {
      this.this$0 = var1;
      this.val$sounds = var2;
   }

   @Override
   public void run() {
      for (int var1 = 0; var1 < this.val$sounds.size(); var1++) {
         try {
            TimeUnit.SECONDS.sleep(1L);
            CFG.soundsManager.playSound((Integer)this.val$sounds.get(var1));
         } catch (InterruptedException var3) {
            throw new RuntimeException(var3);
         }
      }
   }
}
