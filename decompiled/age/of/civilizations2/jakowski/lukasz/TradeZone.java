package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import java.util.ArrayList;
import java.util.List;

public class TradeZone {
   public String sName;
   public List<Integer> lProvinces;
   public int iCenterOfTradeProvinceID;
   public int iProvincesSize;
   public int iAgeID;
   public Color cColor;

   public TradeZone(int nCenterOfTrade) {
      this.lProvinces = new ArrayList<>();
      this.lProvinces.add(nCenterOfTrade);
      this.iCenterOfTradeProvinceID = 0;
      this.iProvincesSize = this.lProvinces.size();
      Color tempColor = CFG.getRandomColor();
      this.cColor = new Color(tempColor.r, tempColor.g, tempColor.b, 0.65F);
   }

   public TradeZone(String sName, List<Integer> lProvinces, int iCenterOfTradeProvinceID, int iAgeID, Color cColor) {
      this.lProvinces = lProvinces;
      this.iCenterOfTradeProvinceID = iCenterOfTradeProvinceID;
      this.iAgeID = iAgeID;
      this.loadName(sName);
      this.cColor = cColor;
      this.iProvincesSize = lProvinces.size();
   }

   public final void loadName(String sName) {
      if (sName != null && sName.length() != 0) {
         this.sName = sName;
      } else {
         sName = "???";
      }
   }

   public final void addProvinceID(int nProvinceID) {
   }

   public final boolean isConnected(int nProvinceID) {
      if (CFG.game.getProvince(nProvinceID).getNeighboringSeaProvincesSize() > 0) {
         for (int i = 0; i < this.iProvincesSize; i++) {
            if (CFG.game.getProvince(this.getProvince(i)).getNeighboringSeaProvincesSize() > 0) {
               return true;
            }
         }
      }

      for (int ix = 0; ix < this.iProvincesSize; ix++) {
         for (int j = 0; j < CFG.game.getProvince(this.getProvince(ix)).getNeighboringProvincesSize(); j++) {
            if (CFG.game.getProvince(this.getProvince(ix)).getNeighboringProvinces(j) == nProvinceID) {
               return true;
            }
         }
      }

      return false;
   }

   public final void removeProvinceID(int nProvinceID) {
      for (int i = 0; i < this.iProvincesSize; i++) {
         if (this.lProvinces.get(i) == nProvinceID) {
            if (i != this.iCenterOfTradeProvinceID) {
               this.lProvinces.remove(i);
               if (this.iCenterOfTradeProvinceID > i) {
                  this.iCenterOfTradeProvinceID--;
               }
            }
            break;
         }
      }

      this.iProvincesSize = this.lProvinces.size();
   }

   public final List<Integer> getProvinces() {
      return this.lProvinces;
   }

   public final int getProvince(int i) {
      return this.lProvinces.get(i);
   }

   public final int getCenterOfTradeProvinceID_Real() {
      return this.iCenterOfTradeProvinceID;
   }

   public final int getCenterOfTradeProvinceID() {
      return this.lProvinces.get(this.iCenterOfTradeProvinceID);
   }

   public final void setCenterOfTrade(int nProvinceID) {
      for (int i = 0; i < this.iProvincesSize; i++) {
         if (nProvinceID == this.lProvinces.get(i)) {
            this.iCenterOfTradeProvinceID = i;
            return;
         }
      }
   }

   public final int getProvincesSize() {
      return this.iProvincesSize;
   }

   public final int getAge() {
      return this.iAgeID;
   }

   public final void setAgeID(int iAgeID) {
      this.iAgeID = iAgeID;
   }

   public final String getName() {
      return this.sName;
   }

   public final void setName(String sName) {
      this.sName = sName;
   }

   public final Color getColor() {
      return this.cColor;
   }

   public final void setColor(Color cColor) {
      this.cColor = cColor;
   }
}
