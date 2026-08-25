package age.of.civilizations2.jakowski.lukasz;

import java.util.ArrayList;
import java.util.List;

public class AI_Build {
   public List<List<Integer>> lProvincesToBuild = new ArrayList<>();
   public int iProvincesToBuild_NumOfElements = 0;
   public int iMaxDangerLevel = 0;

   public AI_Build(int nCivID, long nMoney) {
   }

   public boolean build(int nCivID, int iteration, boolean out) {
      return false;
   }

   public int getNumOfAlreadyBuilt(int nCivID) {
      return 0;
   }

   public long getMoney(int nCivID) {
      if (AI_Assistant.ENABLED && nCivID == CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) {
         return CFG.game.getCiv(nCivID).getMoney();
      }

      return CFG.game.getCiv(nCivID).getMoney() < AI_Style.getMoney_MinReserve(nCivID)
         ? 0L
         : CFG.game.getCiv(nCivID).getMoney() - AI_Style.getMoney_MinReserve(nCivID);
   }
}
