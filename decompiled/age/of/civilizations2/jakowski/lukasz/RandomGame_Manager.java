package age.of.civilizations2.jakowski.lukasz;

import java.util.ArrayList;
import java.util.List;

public class RandomGame_Manager {
   public int iCivilizationsSize = 0;
   public int iNeutralArmy = 0;
   public List<RandomGame_Player> lPlayers = new ArrayList<>();

   public RandomGame_Manager() {
      this.lPlayers.add(new RandomGame_Player(null, -1));
   }

   public final void addPlayer() {
      this.lPlayers.add(new RandomGame_Player(null, -1));
   }

   public final void removePlayer(int i) {
      try {
         this.lPlayers.remove(i);
      } catch (IndexOutOfBoundsException var3) {
      }
   }

   public final RandomGame_Player getPlayer(int i) {
      return this.lPlayers.get(i);
   }

   public final int getPlayersSize() {
      return this.lPlayers.size();
   }

   public final boolean isTagTaken(String nTag) {
      for (int i = 0; i < this.getPlayersSize(); i++) {
         if (this.lPlayers.get(i).getTag() != null && nTag.equals(this.lPlayers.get(i).getTag())) {
            return true;
         }
      }

      return false;
   }

   public final void checkCapitals() {
      for (int i = 0; i < this.getPlayersSize(); i++) {
         if (this.getPlayer(i).getCapitalProvinceID() >= 0) {
            try {
               if (CFG.game.getProvince(i).getSeaProvince() || CFG.game.getProvince(i).getWasteland() >= 0) {
                  this.getPlayer(i).setCapitalProvinceID(-1);
               }
            } catch (IndexOutOfBoundsException var3) {
               this.getPlayer(i).setCapitalProvinceID(-1);
            }
         }
      }
   }

   public final int getCivilizationsSize() {
      return this.iCivilizationsSize;
   }

   public final void setCivilizationsSize(int iCivilizationsSize) {
      this.iCivilizationsSize = iCivilizationsSize;
   }

   public final int getNeutralArmy() {
      return this.iNeutralArmy;
   }

   public final void setNeutralArmy(int iNeutralArmy) {
      this.iNeutralArmy = iNeutralArmy;
   }
}
