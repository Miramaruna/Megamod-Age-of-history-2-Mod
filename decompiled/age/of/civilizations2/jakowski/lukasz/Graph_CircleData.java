package age.of.civilizations2.jakowski.lukasz;

public class Graph_CircleData {
   public int iDataID;
   public float fPercentage;

   public Graph_CircleData(int iDataID, float fPercentage) {
      this.iDataID = iDataID;
      this.fPercentage = fPercentage;
   }

   public final int getDataID() {
      return this.iDataID;
   }

   public final float getPercentage() {
      return this.fPercentage;
   }

   public final void setPercentage(float fPercentage) {
      this.fPercentage = fPercentage;
   }
}
