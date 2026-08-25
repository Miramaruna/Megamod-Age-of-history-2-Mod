package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;

public class LeaderOfCiv_GameData implements Serializable {
   public static final long serialVersionUID = 0L;
   public String sTag = "";
   public String sName = "";
   public String sImage = "";
   public String sWiki = "";
   public int Year = 3;
   public int Month = 2;
   public int Day = 1;
   public float fModifier_PopGrowth = 0.0F;
   public float fModifier_EconomyGrowth = 0.0F;
   public float fModifier_IncomeTaxation = 0.0F;
   public float fModifier_IncomeProduction = 0.0F;
   public float fModifier_Administration = 0.0F;
   public float fModifier_Research = 0.0F;
   public float fModifier_MilitaryUpkeep = 0.0F;
   public float fModifier_AttackBonus = 0.0F;
   public float fModifier_DefenseBonus = 0.0F;
   public float fModifier_MovementPoints = 0.0F;

   LeaderOfCiv_GameData() {
   }

   public final String getName() {
      return this.sName;
   }

   public final void setName(String sName) {
      this.sName = sName;
   }

   public final String getImage() {
      return this.sImage;
   }

   public final void setImage(String sImage) {
      this.sImage = sImage;
   }

   public final String getWiki() {
      return this.sWiki;
   }

   public final void setWiki(String sWiki) {
      this.sWiki = sWiki;
   }

   public final int getMonth() {
      return this.Month;
   }

   public final void setMonth(int month) {
      this.Month = month;
   }

   public final int getYear() {
      return this.Year;
   }

   public final void setYear(int year) {
      this.Year = year;
   }

   public final int getDay() {
      return this.Day;
   }

   public final void setDay(int day) {
      this.Day = day;
   }

   public final String getTag() {
      return this.sTag;
   }

   public final void setTag(String sTag) {
      this.sTag = sTag;
   }
}
