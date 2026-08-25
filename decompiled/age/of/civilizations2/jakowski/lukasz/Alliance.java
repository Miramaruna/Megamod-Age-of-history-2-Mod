package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Alliance implements Serializable {
   public static final long serialVersionUID = 0L;
   public String sAllianceName;
   public Color_GameData allianceColor;
   public List<Integer> lCivilizations;
   public int iCivilizationsSize;
   public int iFormationTurnID = 1;

   public Alliance(String sAllianceName) {
      this.sAllianceName = sAllianceName;
      this.lCivilizations = new ArrayList<>();
      this.iCivilizationsSize = 0;
      this.allianceColor = CFG.getRandomColorGameData();
   }

   public final void addCivilization(int nCivID) {
      for (int i = 0; i < this.iCivilizationsSize; i++) {
         if (this.lCivilizations.get(i) == nCivID) {
            return;
         }
      }

      this.lCivilizations.add(nCivID);
      this.iCivilizationsSize = this.lCivilizations.size();

      for (int var3 = 0; var3 < this.iCivilizationsSize - 1; var3++) {
         CFG.game
            .setCivRelation_OfCivB(
               this.lCivilizations.get(var3), nCivID, Math.min(CFG.game.getCivRelation_OfCivB(this.lCivilizations.get(var3), nCivID), 65.0F)
            );
         CFG.game
            .setCivRelation_OfCivB(
               nCivID, this.lCivilizations.get(var3), Math.min(CFG.game.getCivRelation_OfCivB(nCivID, this.lCivilizations.get(var3)), 65.0F)
            );
      }

      if (CFG.isDesktop() && (CFG.menuManager.getInGameView() || CFG.menuManager.getInNextPlayerTurn()) && CFG.game.getCiv(nCivID).getControlledByPlayer()) {
      }
   }

   public final void removeCivilization(int nCivID) {
      for (int i = 0; i < this.iCivilizationsSize; i++) {
         if (this.lCivilizations.get(i) == nCivID) {
            this.lCivilizations.remove(i);
            this.iCivilizationsSize = this.lCivilizations.size();

            for (int a = 0; a < this.iCivilizationsSize; a++) {
               if (CFG.game.getCivRelation_OfCivB(this.lCivilizations.get(a), nCivID) > 0.0F
                  || CFG.game.getCivRelation_OfCivB(nCivID, this.lCivilizations.get(a)) > 0.0F) {
                  CFG.game.setCivRelation_OfCivB(this.lCivilizations.get(a), nCivID, 0.0F);
                  CFG.game.setCivRelation_OfCivB(nCivID, this.lCivilizations.get(a), 0.0F);
               }
            }

            return;
         }
      }
   }

   public final void updateCivilizationID(int i, int nNewCivID) {
      try {
         this.lCivilizations.set(i, nNewCivID);
      } catch (IndexOutOfBoundsException var4) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var4);
         }
      }
   }

   public final void moveUp(int iID) {
      if (iID != 0) {
         int tempCivID = this.lCivilizations.get(iID - 1);
         this.lCivilizations.set(iID - 1, this.lCivilizations.get(iID));
         this.lCivilizations.set(iID, tempCivID);
      }
   }

   public final void moveDown(int iID) {
      int tempCivID = this.lCivilizations.get(iID + 1);
      this.lCivilizations.set(iID + 1, this.lCivilizations.get(iID));
      this.lCivilizations.set(iID, tempCivID);
   }

   public final void updateCivsIDs_AfterRemoveCiv(int nRemovedCivID) {
      for (int i = 0; i < this.getCivilizationsSize(); i++) {
         if (this.getCivilization(i) > nRemovedCivID) {
            this.lCivilizations.set(i, this.lCivilizations.get(i) - 1);
         }
      }
   }

   public final String getAllianceName() {
      return this.sAllianceName;
   }

   public final void setAllianceName(String sAllianceName) {
      this.sAllianceName = sAllianceName;
   }

   public final int getCivilization(int iID) {
      return this.lCivilizations.get(iID);
   }

   public final int getCivilizationsSize() {
      return this.iCivilizationsSize;
   }

   public final Color_GameData getColorOfAlliance() {
      return this.allianceColor;
   }

   public final void setColorOfAlliance(Color_GameData allianceColor) {
      this.allianceColor = allianceColor;
   }

   public final int getFormationTurnID() {
      return this.iFormationTurnID;
   }

   public final void setFormationTurnID(int iFormationTurnID) {
      this.iFormationTurnID = iFormationTurnID;
   }

   public final int countProvinces() {
      int out = 0;

      for (int i = 0; i < this.getCivilizationsSize(); i++) {
         out += CFG.game.getCiv(this.getCivilization(i)).getNumOfProvinces();
      }

      return out;
   }

   public final int countPopulation() {
      int out = 0;

      for (int i = 0; i < this.getCivilizationsSize(); i++) {
         out = (int)(out + CFG.game.getCiv(this.getCivilization(i)).countPopulation());
      }

      return out;
   }

   public final int countEconomy() {
      int out = 0;

      for (int i = 0; i < this.getCivilizationsSize(); i++) {
         out += CFG.game.getCiv(this.getCivilization(i)).countEconomy();
      }

      return out;
   }
}
