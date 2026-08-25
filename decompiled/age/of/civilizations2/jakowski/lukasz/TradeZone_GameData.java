package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TradeZone_GameData implements Serializable {
   public static final long serialVersionUID = 0L;
   public String sName;
   public List<Integer> lProvinces;
   public int iCenterOfTradeProvinceID;
   public int iAgeID;
   public Color_GameData oColor;

   public TradeZone_GameData() {
      this.lProvinces = new ArrayList<>();
      this.sName = "";
   }

   public TradeZone_GameData(String sName, List<Integer> lProvinces, int iCenterOfTradeProvinceID, int iAgeID, Color_GameData oColor) {
      this.lProvinces = lProvinces;
      this.iCenterOfTradeProvinceID = iCenterOfTradeProvinceID;
      this.sName = sName;
      this.iAgeID = iAgeID;
      this.oColor = oColor;
   }

   public final int getProvince(int i) {
      return this.lProvinces.get(i);
   }

   public final int getCenterOfTradeProvinceID() {
      return this.iCenterOfTradeProvinceID;
   }

   public final int getProvincesSize() {
      return this.lProvinces.size();
   }

   public final int getAge() {
      return this.iAgeID;
   }

   public final void setAge(int iAgeID) {
      this.iAgeID = iAgeID;
   }

   public final Color_GameData getColor() {
      return this.oColor;
   }

   public final void setColor(Color_GameData oColor) {
      this.oColor = oColor;
   }

   public final String getName() {
      return this.sName;
   }

   public final void setName(String sName) {
      this.sName = sName;
   }

   public final List<Integer> getProvinces() {
      return this.lProvinces;
   }
}
