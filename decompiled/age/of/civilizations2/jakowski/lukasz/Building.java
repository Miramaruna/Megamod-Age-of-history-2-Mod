package age.of.civilizations2.jakowski.lukasz;

public class Building {
   private String buildingName;
   private int icon;
   private int minTechLevel;

   public Building(String buildingName, int icon, int minTechLevel) {
      this.buildingName = buildingName;
      this.icon = icon;
      this.minTechLevel = minTechLevel;
   }

   public String getBuildingName() {
      return this.buildingName;
   }

   public void setBuildingName(String buildingName) {
      this.buildingName = buildingName;
   }

   public int getIcon() {
      return this.icon;
   }

   public void setIcon(int icon) {
      this.icon = icon;
   }

   public int getMinTechLevel() {
      return this.minTechLevel;
   }

   public void setMinTechLevel(int minTechLevel) {
      this.minTechLevel = minTechLevel;
   }
}
