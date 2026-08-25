package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;

public class Skills_GameData implements Serializable {
   public static final long serialVersionUID = 0L;
   public int POINTS_DefenseLaws = 0;
   public int POINTS_VASSALS = 0;
   public int POINTS_ATTACKLAWS = 0;
   public int POINTS_POP_GROWTH = 0;
   public int POINTS_ECONOMY_GROWTH = 0;
   public int POINTS_INCOME_TAXATION = 0;
   public int POINTS_INCOME_PRODUCTION = 0;
   public int POINTS_ADMINISTRATION = 0;
   public int POINTS_MILITARY_UPKEEP = 0;
   public int POINTS_RESEARCH = 0;
   public int POINTS_ATTACK = 0;
   public int POINTS_Defense = 0;
   public int POINTS_BONUS_GENOCIDE = 0;
   public int POINTS_Budget = 0;
   public int POINTS_Education = 0;
   public int POINTS_COLONIZATION = 0;

   Skills_GameData() {
   }

   public final int getPointsLeft(int nCivID) {
      return CFG.game.getCiv(nCivID).getTechnologyLevel_INT()
         - this.POINTS_POP_GROWTH
         - this.POINTS_ECONOMY_GROWTH
         - this.POINTS_INCOME_TAXATION
         - this.POINTS_INCOME_PRODUCTION
         - this.POINTS_ADMINISTRATION
         - this.POINTS_MILITARY_UPKEEP
         - this.POINTS_RESEARCH
         - this.POINTS_Education
         - this.POINTS_COLONIZATION;
   }

   public final int getPointsLeftLaws(int nCivID) {
      return CFG.game.getCiv(nCivID).getDiplomacyPoints() - this.POINTS_Budget - this.POINTS_ATTACKLAWS - this.POINTS_DefenseLaws - this.POINTS_VASSALS;
   }

   public final int getPointsUpgradingArmy(int nCivID) {
      return CFG.game.getCiv(nCivID).getMilitaryPoints();
   }
}
