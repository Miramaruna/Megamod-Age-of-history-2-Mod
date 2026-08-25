package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;

public class Civ_Mission_ChangeTypeOfGoverment implements Serializable {
   public static final long serialVersionUID = 0L;
   public int iToIdeologyID;
   public int iCost;

   public Civ_Mission_ChangeTypeOfGoverment(int iToIdeologyID, int nCivID) {
      this.iToIdeologyID = iToIdeologyID;
      this.iCost = Ideologies_Manager.getChangeGovernmentCost(nCivID);
      this.action(nCivID);
   }

   public final boolean action(int nCivID) {
      if (CFG.game.getCiv(nCivID).getMoney() >= this.iCost) {
         if (!CFG.ideologiesManager.canBeAdded(nCivID, this.iToIdeologyID)) {
            return true;
         } else if (DiplomacyManager.changeGovernmentType(nCivID, this.iToIdeologyID)) {
            return true;
         } else {
            this.iCost = Ideologies_Manager.getChangeGovernmentCost(nCivID);
            return false;
         }
      } else {
         if (DiplomacyManager.canTakeMoreLoans(nCivID) && CFG.game.getCiv(nCivID).getMoney() + DiplomacyManager.takeLoan_MaxValue(nCivID) >= this.iCost) {
            DiplomacyManager.takeLoan(nCivID, (int)(this.iCost - CFG.game.getCiv(nCivID).getMoney()), 5);
            if (!CFG.ideologiesManager.canBeAdded(nCivID, this.iToIdeologyID)) {
               return true;
            }

            if (DiplomacyManager.changeGovernmentType(nCivID, this.iToIdeologyID)) {
               return true;
            }
         }

         return false;
      }
   }
}
