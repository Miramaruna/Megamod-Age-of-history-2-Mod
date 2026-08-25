package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.Gdx;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CivPlans implements Serializable {
   public static final long serialVersionUID = 0L;
   public List<AI_WarPreparations> warPreparations = new ArrayList<>();
   public int iWarPreparationsSize = 0;
   public List<AI_WarPreparations> casusBelli = new ArrayList<>();
   public int iCasusBelliSize = 0;
   public List<CivArmyMission> lArmiesMissions = new ArrayList<>();

   CivPlans() {
   }

   public final void addNewWarPreparations(int iLeaderCivID, int iCivID, int onCivID, int numOfTurns) {
      if (!CFG.game.getCivsAtWar(iCivID, onCivID)) {
         if (this.isPreparingForTheWar(onCivID)) {
            this.updatePreparationsTime(onCivID, numOfTurns);
            return;
         }

         Gdx.app.log("AoC", "Plans: addNewWarPreparations: " + CFG.game.getCiv(iCivID).getCivName() + " -> " + CFG.game.getCiv(onCivID).getCivName());
         this.warPreparations.add(new AI_WarPreparations(iLeaderCivID, onCivID, true, numOfTurns));
         this.iWarPreparationsSize = this.warPreparations.size();
      }
   }

   public final void addNewCasusBelli(int iCivID, int onCivID, int numOfTurns) {
      if (!CFG.game.getCivsAtWar(iCivID, onCivID)) {
         if (this.isCasusBelli(onCivID)) {
            this.updateCasusBelliTime(onCivID, numOfTurns);
            return;
         }

         Gdx.app.log("AoC", "Plans: addNewWarPreparations: " + CFG.game.getCiv(iCivID).getCivName() + " -> " + CFG.game.getCiv(onCivID).getCivName());
         this.casusBelli.add(new AI_WarPreparations(iCivID, onCivID, true, numOfTurns));
         this.iCasusBelliSize = this.casusBelli.size();
      }
   }

   public final boolean isPreparingForTheWar() {
      return this.warPreparations.size() > 0;
   }

   public final boolean isCasusBelli() {
      return this.casusBelli.size() > 0;
   }

   public final boolean checkWarPreparations(int nCivID) {
      for (int i = 0; i < CFG.game.getCiv(nCivID).civGameData.civPlans.warPreparations.size(); i++) {
         if (CFG.game.getCivsAtWar(nCivID, CFG.game.getCiv(nCivID).civGameData.civPlans.warPreparations.get(i).onCivID)) {
            CFG.game.getCiv(nCivID).civGameData.civPlans.warPreparations.remove(i--);
            this.iWarPreparationsSize = this.warPreparations.size();
         }
      }

      return this.warPreparations.size() > 0;
   }

   public final boolean checkCasusBelli(int nCivID) {
      for (int i = 0; i < CFG.game.getCiv(nCivID).civGameData.civPlans.casusBelli.size(); i++) {
         if (CFG.game.getCivsAtWar(nCivID, CFG.game.getCiv(nCivID).civGameData.civPlans.casusBelli.get(i).onCivID)) {
            CFG.game.getCiv(nCivID).civGameData.civPlans.casusBelli.remove(i--);
            this.iCasusBelliSize = this.casusBelli.size();
         }
      }

      return this.casusBelli.size() > 0;
   }

   public final boolean isPreparingForTheWar(int onCivID) {
      for (int i = 0; i < this.warPreparations.size(); i++) {
         if (this.warPreparations.get(i).onCivID == onCivID) {
            return true;
         }
      }

      return false;
   }

   public final boolean isPreparingForTheWar(int iWarLeaderID, int onCivID) {
      for (int i = 0; i < this.warPreparations.size(); i++) {
         if (this.warPreparations.get(i).onCivID == onCivID && this.warPreparations.get(i).iLeaderCivID == iWarLeaderID) {
            return true;
         }
      }

      return false;
   }

   public final boolean isCasusBelli(int onCivID) {
      for (int i = 0; i < this.casusBelli.size(); i++) {
         if (this.casusBelli.get(i).onCivID == onCivID) {
            return true;
         }
      }

      return false;
   }

   public final boolean isCasusBelli(int iWarLeaderID, int onCivID) {
      for (int i = 0; i < this.casusBelli.size(); i++) {
         if (this.casusBelli.get(i).onCivID == onCivID && this.casusBelli.get(i).iLeaderCivID == iWarLeaderID) {
            return true;
         }
      }

      return false;
   }

   public final void updateCasusBelliTime(int onCivID, int numOfTurns) {
      for (int i = 0; i < this.casusBelli.size(); i++) {
         if (this.casusBelli.get(i).onCivID == onCivID) {
            this.casusBelli.get(i).iNumOfTurnsLeft = numOfTurns;
            return;
         }
      }
   }

   public final int getCasusBelliTime(int onCivID) {
      for (int i = 0; i < this.casusBelli.size(); i++) {
         if (this.casusBelli.get(i).onCivID == onCivID) {
            return this.casusBelli.get(i).iNumOfTurnsLeft;
         }
      }

      return 0;
   }

   public final int getCasusBelli_LeaderCivID(int onCivID) {
      for (int i = 0; i < this.casusBelli.size(); i++) {
         if (this.casusBelli.get(i).onCivID == onCivID) {
            return this.casusBelli.get(i).iLeaderCivID;
         }
      }

      return 0;
   }

   public final void updatePreparationsTime(int onCivID, int numOfTurns) {
      for (int i = 0; i < this.warPreparations.size(); i++) {
         if (this.warPreparations.get(i).onCivID == onCivID) {
            this.warPreparations.get(i).iNumOfTurnsLeft = numOfTurns;
            return;
         }
      }
   }

   public final int getPreparationsTime(int onCivID) {
      for (int i = 0; i < this.warPreparations.size(); i++) {
         if (this.warPreparations.get(i).onCivID == onCivID) {
            return this.warPreparations.get(i).iNumOfTurnsLeft;
         }
      }

      return 0;
   }

   public final int getPreparations_LeaderCivID(int onCivID) {
      for (int i = 0; i < this.warPreparations.size(); i++) {
         if (this.warPreparations.get(i).onCivID == onCivID) {
            return this.warPreparations.get(i).iLeaderCivID;
         }
      }

      return 0;
   }

   public final boolean addNewArmyMission(int nProvinceID, CivArmyMission nMission) {
      for (int i = this.lArmiesMissions.size() - 1; i >= 0; i--) {
         if (this.lArmiesMissions.get(i).iProvinceID == nProvinceID) {
            return false;
         }
      }

      this.lArmiesMissions.add(nMission);
      return true;
   }

   public final void checkArmyMissions(int nCivID) {
      for (int i = 0; i < this.lArmiesMissions.size(); i++) {
         if (CFG.game.getProvince(this.lArmiesMissions.get(i).iProvinceID).getArmyCivID(nCivID) <= 0) {
            this.lArmiesMissions.remove(i--);
         }
      }
   }

   public final void removeMission(int nProvinceID) {
      for (int i = 0; i < this.lArmiesMissions.size(); i++) {
         if (this.lArmiesMissions.get(nProvinceID).iProvinceID == nProvinceID) {
            this.lArmiesMissions.remove(i--);
            return;
         }
      }
   }

   public final boolean haveMission(int nProvinceID) {
      for (int i = 0; i < this.lArmiesMissions.size(); i++) {
         if (this.lArmiesMissions.get(i).iProvinceID == nProvinceID) {
            return true;
         }
      }

      return false;
   }

   public final int haveMission_Army(int nProvinceID) {
      int out = 0;

      for (int i = 0; i < this.lArmiesMissions.size(); i++) {
         if (this.lArmiesMissions.get(i).iProvinceID == nProvinceID) {
            out += this.lArmiesMissions.get(i).iArmy;
         }
      }

      return out;
   }

   public final int haveMission_Army_ToProvinceID(int nProvinceID) {
      for (int i = 0; i < this.lArmiesMissions.size(); i++) {
         if (this.lArmiesMissions.get(i).toProvinceID == nProvinceID) {
            return this.lArmiesMissions.get(i).iArmy;
         }
      }

      return 0;
   }

   public final void updateObsolateMissions() {
      for (int i = 0; i < this.lArmiesMissions.size(); i++) {
         if (this.lArmiesMissions.get(i).iObsolate-- <= 0) {
            this.lArmiesMissions.remove(i--);
         }
      }
   }
}
