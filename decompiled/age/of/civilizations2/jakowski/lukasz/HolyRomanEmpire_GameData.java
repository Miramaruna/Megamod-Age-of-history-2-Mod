package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class HolyRomanEmpire_GameData implements Serializable {
   public static final long serialVersionUID = 0L;
   public List<Integer> lProvinces = new ArrayList<>();
   public int iProvincesSize = 0;
   public int iEmperorID = -1;
   public int iEmperorAuthority = 0;
   public List<Integer> lElectors = new ArrayList<>();
   public int iElectorsSize = 0;
   public List<Integer> lPrinces = new ArrayList<>();
   public int iPrincesSize = 0;
   public List<Integer> lVotesFor = new ArrayList<>();
   public int iNextElectionsIn = 30;

   public final void updateHRE_AfterRemoveCivilization(int nCivID) {
      for (int i = 0; i < this.getPrincesSize(); i++) {
         if (this.getPrince(i) == nCivID) {
            this.removePrince(nCivID);
            i--;
         } else if (this.getPrince(i) > nCivID) {
            this.lPrinces.set(i, this.lPrinces.get(i) - 1);
         }
      }
   }

   public final int getProvinces(int i) {
      return this.lProvinces.get(i);
   }

   public final boolean addProvince(int nProvinceID) {
      for (int i = 0; i < this.getProvincesSize(); i++) {
         if (this.getProvinces(i) == nProvinceID) {
            return false;
         }
      }

      this.lProvinces.add(nProvinceID);
      this.iProvincesSize = this.lProvinces.size();
      CFG.game.getProvince(nProvinceID).setIsPartOfHolyRomanEmpire(true);
      return true;
   }

   public final boolean removeProvince(int nProvinceID) {
      for (int i = 0; i < this.getProvincesSize(); i++) {
         if (this.getProvinces(i) == nProvinceID) {
            CFG.game.getProvince(nProvinceID).setIsPartOfHolyRomanEmpire(false);
            this.lProvinces.remove(i);
            this.iProvincesSize = this.lProvinces.size();
            return true;
         }
      }

      return false;
   }

   public final int getProvincesSize() {
      return this.iProvincesSize;
   }

   public final boolean getIsImperialProvince(int nProvinceID) {
      for (int i = 0; i < this.getPrincesSize(); i++) {
         if (this.getProvinces(i) == nProvinceID) {
            return true;
         }
      }

      return false;
   }

   public final int getPrince(int i) {
      return this.lPrinces.get(i);
   }

   public final void addPrince(int nCivID) {
      for (int i = 0; i < this.getPrincesSize(); i++) {
         if (this.getPrince(i) == nCivID) {
            return;
         }
      }

      this.lPrinces.add(nCivID);
      this.iPrincesSize = this.lPrinces.size();
      CFG.game.getCiv(nCivID).setIsPartOfHolyRomanEmpire(true);
   }

   public final void removePrinceID(int nID) {
      this.removePrince(this.getPrince(nID));
   }

   public final void removePrince(int nCivID) {
      for (int i = 0; i < this.getPrincesSize(); i++) {
         if (this.getPrince(i) == nCivID) {
            CFG.game.getCiv(nCivID).setIsPartOfHolyRomanEmpire(false);
            this.removeElector(nCivID);
            this.lPrinces.remove(i);
            this.iPrincesSize = this.lPrinces.size();

            for (int j = 0; j < this.getElectorsSize(); j++) {
               if (this.lElectors.get(j) > i) {
                  this.lElectors.set(j, this.lElectors.get(j) - 1);
               }
            }

            if (this.iEmperorID == i) {
               this.iEmperorID = this.getElectorsSize() > 0 ? this.getElector(0) : -1;
            } else if (this.iEmperorID > i) {
               this.iEmperorID--;
            }

            return;
         }
      }
   }

   public final int getPrincesSize() {
      return this.iPrincesSize;
   }

   public final int getPrincesSize_True() {
      int out = 0;

      for (int i = 0; i < this.getPrincesSize(); i++) {
         if (CFG.game.getCiv(this.getPrince(i)).getNumOfProvinces() > 0) {
            out++;
         }
      }

      return out;
   }

   public final boolean getIsPrince(int nCivID) {
      for (int i = 0; i < this.getPrincesSize(); i++) {
         if (this.getPrince(i) == nCivID) {
            return true;
         }
      }

      return false;
   }

   public final int getEmperor() {
      return this.lPrinces.get(this.iEmperorID);
   }

   public final void setEmperor(int nCivID) {
      for (int i = 0; i < this.getPrincesSize(); i++) {
         if (this.getPrince(i) == nCivID) {
            this.iEmperorID = i;
            this.removeElector(nCivID);
            return;
         }
      }
   }

   public final void setEmperorID(int nID) {
      this.iEmperorID = this.iEmperorID != nID && nID < this.getPrincesSize() ? nID : -1;
   }

   public final int getEmperorAuthority() {
      return this.iEmperorAuthority;
   }

   public final void setEmperorAuthority(int iEmperorAuthority) {
      this.iEmperorAuthority = iEmperorAuthority;
   }

   public boolean getIsEmperor(int nCivID) {
      return this.iEmperorID >= 0 ? this.getPrince(this.iEmperorID) == nCivID : false;
   }

   public final int getElector(int i) {
      return this.lElectors.get(i);
   }

   public final void addElector(int nCivID) {
      if (this.getElectorsSize() < 9) {
         for (int i = 0; i < this.getPrincesSize(); i++) {
            if (this.getPrince(i) == nCivID) {
               this.lElectors.add(i);
               this.iElectorsSize = this.lElectors.size();
               this.buildVotesFor();
               return;
            }
         }
      }
   }

   public final void removeElector(int nCivID) {
      for (int i = 0; i < this.getElectorsSize(); i++) {
         if (this.getPrince(this.lElectors.get(i)) == nCivID) {
            this.lElectors.remove(i);
            this.iElectorsSize = this.lElectors.size();
            this.buildVotesFor();
            return;
         }
      }
   }

   public final int getElectorsSize() {
      return this.iElectorsSize;
   }

   public final boolean getIsElector(int nCivID) {
      for (int i = 0; i < this.getElectorsSize(); i++) {
         if (this.getPrince(this.getElector(i)) == nCivID) {
            return true;
         }
      }

      return false;
   }

   public final void setElectorID(int nID) {
      if (nID < this.getPrincesSize()) {
         if (this.getIsElector(this.getPrince(nID))) {
            this.removeElector(this.getPrince(nID));
         } else {
            this.addElector(this.getPrince(nID));
         }
      }
   }

   public final void addStrongestPrinceAsElector() {
      ArrayList<Integer> tPossibleElectors = new ArrayList<>();

      for (int i = 0; i < this.getPrincesSize(); i++) {
         if (CFG.game.getCiv(this.getPrince(i)).getNumOfProvinces() > 0 && !this.getIsElector(this.getPrince(i)) && !this.getIsEmperor(this.getPrince(i))) {
            tPossibleElectors.add(this.getPrince(i));
         }
      }

      if (tPossibleElectors.size() > 0) {
         int tBest = 0;

         for (int ix = 0; ix < tPossibleElectors.size(); ix++) {
            if (CFG.game.getCiv(tPossibleElectors.get(ix)).countPopulation() > CFG.game.getCiv(tPossibleElectors.get(tBest)).countPopulation()) {
               tBest = ix;
            }
         }

         this.addElector(tPossibleElectors.get(tBest));
      }
   }

   public final void buildVotesFor() {
      if (this.lVotesFor == null) {
         this.lVotesFor = new ArrayList<>();
      }

      if (this.lVotesFor.size() == 0) {
         for (int i = 0; i < this.getElectorsSize(); i++) {
            this.lVotesFor.add(this.getPrince(this.getElector(i)));
         }
      } else {
         ArrayList<Integer> oldVotes = new ArrayList<>();

         for (int i = 0; i < this.lVotesFor.size(); i++) {
            oldVotes.add(this.lVotesFor.get(i));
         }

         this.lVotesFor.clear();

         for (int var6 = 0; var6 < this.getElectorsSize(); var6++) {
            if (CFG.game.getCiv(this.getPrince(this.getElector(var6))).getControlledByPlayer()) {
               try {
                  this.lVotesFor.add(oldVotes.get(var6));
               } catch (IndexOutOfBoundsException var4) {
                  this.lVotesFor.add(this.getPrince(this.getElector(var6)));
               }
            } else {
               this.lVotesFor.add(this.getPrince(this.getElector(var6)));
            }
         }
      }
   }

   public final int getNextElectionsIn() {
      return this.iNextElectionsIn;
   }

   public final void setNextElectionsIn(int iNextElectionsIn) {
      this.iNextElectionsIn = iNextElectionsIn;
   }

   public final void randomNextElections() {
      this.iNextElectionsIn = 32 + CFG.oR.nextInt(60);
   }
}
