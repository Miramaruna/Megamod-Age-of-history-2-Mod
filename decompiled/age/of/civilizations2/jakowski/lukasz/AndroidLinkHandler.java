package age.of.civilizations2.jakowski.lukasz;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;

public class AndroidLinkHandler implements LinkHandler {
   Activity mActivity;

   public AndroidLinkHandler(Activity var1) {
      this.mActivity = var1;
   }

   @Override
   public void openPage(String var1, String var2) {
      label12: {
         Intent var3;
         try {
            this.mActivity.getPackageManager().getPackageInfo(var1, 0);
            var3 = new Intent("android.intent.action.VIEW", Uri.parse(var1));
         } catch (NameNotFoundException var4) {
            var5 = new Intent("android.intent.action.VIEW", Uri.parse(var2));
            break label12;
         }

         var5 = var3;
      }

      this.mActivity.startActivity(var5);
   }
}
