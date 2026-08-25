package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Leader_GameData implements Serializable {
   public static final long serialVersionUID = 0L;
   public LeaderOfCiv_GameData leaderOfCiv = new LeaderOfCiv_GameData();
   public List<String> sCivs = new ArrayList<>();

   Leader_GameData() {
   }

   public final void addCiv(String nTag) {
      if (this.sCivs == null || !this.sCivs.equals(nTag)) {
         for (int i = 0; i < this.sCivs.size(); i++) {
            if (this.sCivs.get(i).equals(nTag)) {
               return;
            }
         }

         this.sCivs.add(nTag);
      }
   }

   public final void removeCiv(int i) {
      this.sCivs.remove(i);
   }

   public final String getCiv(int i) {
      return this.sCivs.get(i);
   }

   public final int getCivsSize() {
      return this.sCivs.size();
   }

   public final LeaderOfCiv_GameData getLeaderOfCiv() {
      return this.leaderOfCiv;
   }

   public final void setLeaderOfCiv(LeaderOfCiv_GameData leaderOfCiv) {
      this.leaderOfCiv = leaderOfCiv;
   }
}
