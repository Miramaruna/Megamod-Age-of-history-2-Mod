package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Scenario_GameData_Diplomacy2 implements Serializable {
   public static final long serialVersionUID = 0L;
   public List<Scenario_GameData_Diplomacy_AlliancesData> lAlliances;
   public List<Scenario_GameData_Diplomacy_Data> lRelations;
   public List<Scenario_GameData_Diplomacy_Data> lPacts;
   public List<Scenario_GameData_Diplomacy_VassalsData> lVassals;
   public List<Scenario_GameData_Diplomacy_Data> lMilitaryAccess;
   public List<Scenario_GameData_Diplomacy_Data> lDefensivePacts;
   public List<Scenario_GameData_Diplomacy_Data> lGuarantee;
   public List<Scenario_GameData_Diplomacy_Data> lTruces;

   Scenario_GameData_Diplomacy2() {
   }

   public final void buildData() {
      this.lAlliances = new ArrayList<>();
      this.lRelations = new ArrayList<>();
      this.lPacts = new ArrayList<>();
      this.lVassals = new ArrayList<>();
      this.lMilitaryAccess = new ArrayList<>();
      this.lDefensivePacts = new ArrayList<>();
      this.lGuarantee = new ArrayList<>();
      this.lTruces = new ArrayList<>();

      for (int i = 1; i < CFG.game.getAlliancesSize(); i++) {
         this.lAlliances
            .add(
               new Scenario_GameData_Diplomacy_AlliancesData(
                  CFG.game.getAlliance(i).getAllianceName(),
                  new Color_GameData(
                     CFG.game.getAlliance(i).getColorOfAlliance().getR(),
                     CFG.game.getAlliance(i).getColorOfAlliance().getG(),
                     CFG.game.getAlliance(i).getColorOfAlliance().getB()
                  )
               )
            );

         for (int j = 0; j < CFG.game.getAlliance(i).getCivilizationsSize(); j++) {
            this.lAlliances.get(i - 1).addCiv(CFG.game.getAlliance(i).getCivilization(j));
         }
      }

      for (int var5 = 1; var5 < CFG.game.getCivsSize(); var5++) {
         for (int j = 1; j < CFG.game.getCivsSize(); j++) {
            if (var5 != j) {
               if (CFG.game.getCivRelation_OfCivB(var5, j) != 0.0F) {
                  this.lRelations.add(new Scenario_GameData_Diplomacy_Data(var5, j, (int)CFG.game.getCivRelation_OfCivB(var5, j)));
               }

               if (CFG.game.getGuarantee(var5, j) > 0) {
                  this.lGuarantee.add(new Scenario_GameData_Diplomacy_Data(var5, j, CFG.game.getGuarantee(var5, j)));
               }

               if (CFG.game.getMilitaryAccess(var5, j) > 0) {
                  this.lMilitaryAccess.add(new Scenario_GameData_Diplomacy_Data(var5, j, CFG.game.getMilitaryAccess(var5, j)));
               }
            }
         }

         if (CFG.game.getCiv(var5).getCivID() != CFG.game.getCiv(var5).getPuppetOfCivID()) {
            this.lVassals.add(new Scenario_GameData_Diplomacy_VassalsData(CFG.game.getCiv(var5).getCivID(), CFG.game.getCiv(var5).getPuppetOfCivID()));
         }
      }

      for (int var6 = 1; var6 < CFG.game.getCivsSize() - 1; var6++) {
         for (int jx = var6 + 1; jx < CFG.game.getCivsSize(); jx++) {
            if (CFG.game.getCivNonAggressionPact(var6, jx) > 0) {
               this.lPacts.add(new Scenario_GameData_Diplomacy_Data(var6, jx, CFG.game.getCivNonAggressionPact(var6, jx)));
            }

            if (CFG.game.getDefensivePact(var6, jx) > 0) {
               this.lDefensivePacts.add(new Scenario_GameData_Diplomacy_Data(var6, jx, CFG.game.getDefensivePact(var6, jx)));
            }

            if (CFG.game.getCivTruce(var6, jx) > 0) {
               this.lTruces.add(new Scenario_GameData_Diplomacy_Data(var6, jx, CFG.game.getCivTruce(var6, jx)));
            }
         }
      }
   }

   public final List<Scenario_GameData_Diplomacy_AlliancesData> getAlliances() {
      return this.lAlliances;
   }

   public final List<Scenario_GameData_Diplomacy_Data> getRelations() {
      return this.lRelations;
   }

   public final List<Scenario_GameData_Diplomacy_Data> getPacts() {
      return this.lPacts;
   }

   public final List<Scenario_GameData_Diplomacy_Data> getTruces() {
      return this.lTruces;
   }

   public final List<Scenario_GameData_Diplomacy_VassalsData> getVassals() {
      return this.lVassals;
   }

   public final List<Scenario_GameData_Diplomacy_Data> getGuarantee() {
      return this.lGuarantee;
   }

   public final List<Scenario_GameData_Diplomacy_Data> getMilitaryAccess() {
      return this.lMilitaryAccess;
   }

   public final List<Scenario_GameData_Diplomacy_Data> getDefensivePacts() {
      return this.lDefensivePacts;
   }
}
