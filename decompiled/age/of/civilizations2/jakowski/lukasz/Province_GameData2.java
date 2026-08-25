package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Province_GameData2 implements Serializable {
   public static final long serialVersionUID = 0L;
   public List<Short> lPointsX;
   public List<Short> lPointsY;
   public List<Province_Border_GameData> lProvinceBorder;
   public int iLevelOfPort;
   public List<Short> lNeighboringProvinces;
   public List<Short> lNeighboringSeaProvinces;
   public Province_Info_GameData3 provinceInfo = new Province_Info_GameData3();
   public int iPort_ShiftX = 0;
   public int iPort_ShiftY = 0;

   public Province_GameData2() {
      this.lPointsX = new ArrayList<>();
      this.lPointsY = new ArrayList<>();
      this.lProvinceBorder = new ArrayList<>();
      this.lNeighboringProvinces = new ArrayList<>();
      this.lNeighboringSeaProvinces = new ArrayList<>();
   }

   public Province_GameData2(
      int iLevelOfPort,
      List<Short> lPointsX,
      List<Short> lPointsY,
      List<Province_Border_GameData> lProvinceBorder,
      List<Short> lNeighboringProvinces,
      List<Short> lNeighboringSeaProvinces
   ) {
      this.iLevelOfPort = iLevelOfPort;
      this.lPointsX = lPointsX;
      this.lPointsY = lPointsY;
      this.lProvinceBorder = lProvinceBorder;
      this.lNeighboringProvinces = lNeighboringProvinces;
      this.lNeighboringSeaProvinces = lNeighboringSeaProvinces;
   }

   public final List<Short> getPointsX() {
      return this.lPointsX;
   }

   public final List<Short> getPointsY() {
      return this.lPointsY;
   }

   public final int getLevelOfPort() {
      return this.iLevelOfPort;
   }

   public final List<Province_Border_GameData> getProvinceBorder() {
      return this.lProvinceBorder;
   }

   public final List<Short> getNeighboringProvinces() {
      return this.lNeighboringProvinces;
   }

   public final List<Short> getNeighboringSeaProvinces() {
      return this.lNeighboringSeaProvinces;
   }
}
