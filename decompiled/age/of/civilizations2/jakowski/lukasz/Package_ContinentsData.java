package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Package_ContinentsData implements Serializable {
   public static final long serialVersionUID = 0L;
   public String sPackageName = "";
   public List<String> lContinentsTags = new ArrayList<>();

   public final String getPackageName() {
      return this.sPackageName;
   }

   public final void setPackageName(String sPackageName) {
      this.sPackageName = sPackageName;
   }

   public final String getContinentTag(int i) {
      return this.lContinentsTags.get(i);
   }

   public final int getContinentsTagsSize() {
      return this.lContinentsTags.size();
   }

   public final void addContinentTag(String sTag) {
      this.lContinentsTags.add(sTag);
   }

   public final void removeContinentTag(int i) {
      this.lContinentsTags.remove(i);
   }
}
