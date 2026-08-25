package age.of.civilizations2.jakowski.lukasz;

public class Province_Airbase {
   private int fighters;
   private int bombers;
   private int helicopters;
   private int region;
   private float fightersInvest;
   private float bombersInvest;
   private float helicoptersInvest;
   private float fightersInvested;
   private float bombersInvested;
   private float helicoptersInvested;

   public Province_Airbase(int region, int fighters, int bombers, int helicopters) {
      this.fighters = fighters;
      this.bombers = bombers;
      this.helicopters = helicopters;
      this.region = region;
   }

   public int getFighters() {
      return this.fighters;
   }

   public void setFighters(int fighters) {
      this.fighters = fighters;
   }

   public int getBombers() {
      return this.bombers;
   }

   public void setBombers(int bombers) {
      this.bombers = bombers;
   }

   public int getHelicopters() {
      return this.helicopters;
   }

   public void setHelicopters(int helicopters) {
      this.helicopters = helicopters;
   }

   public int getRegion() {
      return this.region;
   }

   public void setRegion(int region) {
      this.region = region;
   }

   public float getFightersInvest() {
      return this.fightersInvest;
   }

   public void setFightersInvest(float fightersInvest) {
      this.fightersInvest = fightersInvest;
   }

   public float getBombersInvest() {
      return this.bombersInvest;
   }

   public void setBombersInvest(float bombersInvest) {
      this.bombersInvest = bombersInvest;
   }

   public float getHelicoptersInvest() {
      return this.helicoptersInvest;
   }

   public void setHelicoptersInvest(float helicoptersInvest) {
      this.helicoptersInvest = helicoptersInvest;
   }

   public float getFightersInvested() {
      return this.fightersInvested;
   }

   public void setFightersInvested(float fightersInvested) {
      this.fightersInvested = fightersInvested;
   }

   public float getBombersInvested() {
      return this.bombersInvested;
   }

   public void setBombersInvested(float bombersInvested) {
      this.bombersInvested = bombersInvested;
   }

   public float getHelicoptersInvested() {
      return this.helicoptersInvested;
   }

   public void setHelicoptersInvested(float helicoptersInvested) {
      this.helicoptersInvested = helicoptersInvested;
   }
}
