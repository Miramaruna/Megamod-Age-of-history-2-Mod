package age.of.civilizations2.jakowski.lukasz;

public class Start_The_Game_Data {
   public long lTime;
   public float fProvincesAlpha;
   public float fCapitalsAlpha;
   public float fWastelandAlpha;
   public float fStaightLinePercentage;
   public float fDashedLinePercentage;
   public boolean ready = false;
   public boolean reverse = false;
   public final int TIME_PROVINCE_ALPHA = 3250;
   public final int TIME_CAPITALS_PROVINCE_ALPHA = 1500;
   public final int TIME_STRAIGHT_LINE = 1250;
   public final int TIME_DASHED_LINE = 3500;
   public final float TIMER_MODFIER_END_GAME = 1.3F;

   public Start_The_Game_Data(boolean reverse) {
      if (reverse) {
         this.lTime = System.currentTimeMillis();
         this.fProvincesAlpha = CFG.settingsManager.PROVINCE_ALPHA;
         this.fCapitalsAlpha = CFG.settingsManager.PROVINCE_ALPHA;
         this.fWastelandAlpha = CFG.settingsManager.PROVINCE_ALPHA_WASTELAND * 255.0F;
         this.fStaightLinePercentage = 100.0F;
         this.fDashedLinePercentage = 100.0F;
      } else {
         this.lTime = System.currentTimeMillis();
         this.fProvincesAlpha = 0.0F;
         this.fCapitalsAlpha = 0.0F;
         this.fWastelandAlpha = 0.0F;
         this.fStaightLinePercentage = 2.0F;
         this.fDashedLinePercentage = 2.0F;
      }

      this.reverse = reverse;
   }

   public final void updateData() {
      if (this.reverse) {
         this.fProvincesAlpha = this.fProvincesAlpha
            - Math.abs((float)(System.currentTimeMillis() - this.lTime) / 4225.0F * CFG.settingsManager.PROVINCE_ALPHA);
         this.fCapitalsAlpha = this.fProvincesAlpha;
         if (this.fProvincesAlpha < 0.0F) {
            this.fProvincesAlpha = 0.0F;
            this.fCapitalsAlpha = 0.0F;
         }

         this.fWastelandAlpha = this.fWastelandAlpha
            - Math.abs((float)(System.currentTimeMillis() - this.lTime) / 4225.0F * (CFG.settingsManager.PROVINCE_ALPHA_WASTELAND * 255.0F));
         if (this.fWastelandAlpha < 0.0F) {
            this.fWastelandAlpha = 0.0F;
         }

         this.fStaightLinePercentage = this.fStaightLinePercentage - (float)(System.currentTimeMillis() - this.lTime) / 4550.0F * 98.0F;
         if (this.fStaightLinePercentage < 0.0F) {
            this.fStaightLinePercentage = 0.0F;
            this.ready = true;
         }

         this.fDashedLinePercentage = this.fDashedLinePercentage - (float)(System.currentTimeMillis() - this.lTime) / 1625.0F * 98.0F;
         if (this.fDashedLinePercentage < 0.0F) {
            this.fDashedLinePercentage = 0.0F;
         }

         this.lTime = System.currentTimeMillis();
      } else {
         this.fProvincesAlpha = this.fProvincesAlpha
            + Math.abs((float)(System.currentTimeMillis() - this.lTime) / 3250.0F * CFG.settingsManager.PROVINCE_ALPHA);
         if (this.fProvincesAlpha > CFG.settingsManager.PROVINCE_ALPHA) {
            this.fProvincesAlpha = CFG.settingsManager.PROVINCE_ALPHA;
         }

         this.fWastelandAlpha = this.fWastelandAlpha
            + Math.abs((float)(System.currentTimeMillis() - this.lTime) / 3250.0F * (CFG.settingsManager.PROVINCE_ALPHA_WASTELAND * 255.0F));
         if (this.fWastelandAlpha > CFG.settingsManager.PROVINCE_ALPHA_WASTELAND * 255.0F) {
            this.fWastelandAlpha = CFG.settingsManager.PROVINCE_ALPHA_WASTELAND * 255.0F;
         }

         this.fCapitalsAlpha = this.fCapitalsAlpha + Math.abs((float)(System.currentTimeMillis() - this.lTime) / 1500.0F * CFG.settingsManager.PROVINCE_ALPHA);
         if (this.fCapitalsAlpha > CFG.settingsManager.PROVINCE_ALPHA) {
            this.fCapitalsAlpha = CFG.settingsManager.PROVINCE_ALPHA;
         }

         this.fStaightLinePercentage = this.fStaightLinePercentage + (float)(System.currentTimeMillis() - this.lTime) / 1250.0F * 98.0F;
         if (this.fStaightLinePercentage > 100.0F) {
            this.fStaightLinePercentage = 100.0F;
         }

         this.fDashedLinePercentage = this.fDashedLinePercentage + (float)(System.currentTimeMillis() - this.lTime) / 3500.0F * 98.0F;
         if (this.fDashedLinePercentage > 100.0F) {
            this.fDashedLinePercentage = 100.0F;
            this.ready = true;
            Menu_StartTheGame.done();
         }

         this.lTime = System.currentTimeMillis();
      }
   }

   public final int getProvincesAlpha() {
      return (int)Math.abs(this.fProvincesAlpha);
   }

   public final int getCapitalsAlpha() {
      return (int)Math.abs(this.fCapitalsAlpha);
   }

   public final int getWastelandAlpha() {
      return (int)Math.abs(this.fWastelandAlpha);
   }

   public final float getStraightLinePercentage() {
      return this.fStaightLinePercentage / 100.0F;
   }

   public final float getDashedLinePercentage() {
      return this.fDashedLinePercentage / 100.0F;
   }

   public final boolean getIsDone() {
      return this.ready;
   }
}
