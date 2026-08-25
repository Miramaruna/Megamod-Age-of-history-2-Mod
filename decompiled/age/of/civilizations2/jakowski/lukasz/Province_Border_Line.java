package age.of.civilizations2.jakowski.lukasz;

public class Province_Border_Line {
   public int iPosX;
   public int iPosY;
   public int iWidth;
   public float fAngle;

   public Province_Border_Line(int nPosX, int nPosY, int nPosX2, int nPosY2) {
      this.iPosX = nPosX;
      this.iPosY = nPosY;
      this.iWidth = (int)Math.ceil(Math.sqrt((nPosX2 - nPosX) * (nPosX2 - nPosX) + (nPosY - nPosY2) * (nPosY - nPosY2)));
      this.fAngle = (float)(Math.atan2(nPosY - nPosY2, -nPosX + nPosX2) * 180.0 / Math.PI);
   }

   public int getPosX() {
      return this.iPosX;
   }

   public int getPosY() {
      return this.iPosY;
   }

   public int getWidth() {
      return this.iWidth;
   }

   public float getAngle() {
      return this.fAngle;
   }
}
