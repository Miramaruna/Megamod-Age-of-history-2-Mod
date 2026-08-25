package age.of.civilizations2.jakowski.lukasz;

import java.util.ArrayList;
import java.util.List;

public class Civilization_Region_Active {
   public int iCivID;
   public List<Integer> lActiveRegions;
   public List<Integer> lRegionStyle;

   public Civilization_Region_Active(int iCivID, int iRegionID, int iRegionStyle) {
      this.iCivID = iCivID;
      this.lActiveRegions = new ArrayList<>();
      this.lActiveRegions.add(iRegionID);
      this.lRegionStyle = new ArrayList<>();
      this.lRegionStyle.add(iRegionStyle);
   }

   public final int getCivID() {
      return this.iCivID;
   }

   public final boolean isActive_RegionID(int nCivRegionID) {
      for (int i = 0; i < this.lActiveRegions.size(); i++) {
         if (this.lActiveRegions.get(i) == nCivRegionID) {
            return true;
         }
      }

      return false;
   }

   public final void addRegion(int nCivRegionID, int nRegionStyle) {
      for (int i = 0; i < this.lActiveRegions.size(); i++) {
         if (this.lActiveRegions.get(i) == nCivRegionID) {
            return;
         }
      }

      this.lActiveRegions.add(nCivRegionID);
      this.lRegionStyle.add(nRegionStyle);
   }

   public final void removeRegion(int nCivRegionID) {
      for (int i = 0; i < this.lActiveRegions.size(); i++) {
         if (this.lActiveRegions.get(i) == nCivRegionID) {
            this.lActiveRegions.remove(i);
            this.lRegionStyle.remove(i);
            return;
         }
      }
   }

   public final int getActiveRegionsSize() {
      return this.lActiveRegions.size();
   }

   public final int getRegionStyleID(int nCivRegionID) {
      for (int i = 0; i < this.lActiveRegions.size(); i++) {
         if (this.lActiveRegions.get(i) == nCivRegionID) {
            return this.lRegionStyle.get(i);
         }
      }

      return this.lRegionStyle.get(0);
   }
}
