package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;
import java.util.List;

public class Selected_Provinces {
   public long lTime = 0L;
   public int iAlpha = 50;
   public int iStepID = 0;
   public boolean backAnimation = false;
   public long lTimeBorder = 0L;
   public int iStepIDBorder = 0;
   public int iBorderAlpha = 255;
   public boolean backAnimationBorder = false;
   public List<Integer> lProvincesID = new ArrayList<>();
   public int iProvincesSize;

   public final void update() {
      this.updateProvinceAlpha();
      this.updateBorderAlpha();
   }

   public void updateProvinceAlpha() {
      if (this.lTime < System.currentTimeMillis() - 25L) {
         this.iStepID++;
         this.iAlpha = this.backAnimation ? ++this.iAlpha : --this.iAlpha;
         this.lTime = System.currentTimeMillis();
         if (this.iStepID == 30) {
            this.iStepID = 0;
            this.backAnimation = !this.backAnimation;
            this.lTime = this.lTime + (this.backAnimation ? 450L : 600L);
         }
      }
   }

   public void updateBorderAlpha() {
      if (this.lTimeBorder < System.currentTimeMillis() - 30L) {
         this.iStepIDBorder++;
         this.iBorderAlpha = this.backAnimationBorder ? (this.iBorderAlpha += 3) : (this.iBorderAlpha -= 3);
         this.lTimeBorder = System.currentTimeMillis();
         if (this.iStepIDBorder == 45) {
            this.iStepIDBorder = 0;
            this.backAnimationBorder = !this.backAnimationBorder;
            this.lTimeBorder = this.lTimeBorder + (this.backAnimationBorder ? 225L : 300L);
         }
      }
   }

   public void updateColor(SpriteBatch oSB) {
      oSB.setColor(new Color(1.0F, 1.0F, 1.0F, this.iAlpha * 1.6F / 255.0F));
   }

   public final void draw(SpriteBatch oSB) {
      this.update();
      this.updateColor(oSB);

      for (int i = 0; i < this.iProvincesSize; i++) {
         if (CFG.game.getProvince(this.lProvincesID.get(i)).getDrawProvince()) {
            CFG.game.getProvince(this.lProvincesID.get(i)).drawProvince_ActiveProvince(oSB);
         }
      }
   }

   public final void draw_CreateAVassal(SpriteBatch oSB) {
      this.update();
      oSB.setColor(new Color(CFG.createVassal_Data.oColor.r, CFG.createVassal_Data.oColor.g, CFG.createVassal_Data.oColor.b, this.iAlpha * 1.6F / 255.0F));

      for (int i = 0; i < this.iProvincesSize; i++) {
         if (CFG.game.getProvince(this.lProvincesID.get(i)).getDrawProvince()) {
            CFG.game.getProvince(this.lProvincesID.get(i)).drawProvince_ActiveProvince(oSB);
         }
      }
   }

   public final void draw_HolyRomanEmpire(SpriteBatch oSB) {
      this.update();
      oSB.setColor(
         new Color(
            HolyRomanEmpire_Manager.oColorHRE.r,
            HolyRomanEmpire_Manager.oColorHRE.g,
            HolyRomanEmpire_Manager.oColorHRE.b,
            this.iAlpha * (CFG.VIEW_SHOW_VALUES ? 3.0F : 2.4F) / 255.0F
         )
      );

      for (int i = 0; i < this.iProvincesSize; i++) {
         if (CFG.game.getProvince(this.lProvincesID.get(i)).getDrawProvince()) {
            CFG.game.getProvince(this.lProvincesID.get(i)).drawProvince_ActiveProvince(oSB);
         }
      }
   }

   public final boolean addProvince(int nProvinceID) {
      for (int i = 0; i < this.iProvincesSize; i++) {
         if (this.lProvincesID.get(i) == nProvinceID) {
            return false;
         }
      }

      this.lProvincesID.add(nProvinceID);
      this.iProvincesSize = this.lProvincesID.size();
      return true;
   }

   public final void popProvince() {
      if (this.lProvincesID.size() > 0) {
         this.removeProvince(this.lProvincesID.get(this.getProvincesSize() - 1));
      }
   }

   public final boolean removeProvince(int nProvinceID) {
      for (int i = 0; i < this.iProvincesSize; i++) {
         if (this.lProvincesID.get(i) == nProvinceID) {
            this.lProvincesID.remove(i);
            this.iProvincesSize = this.lProvincesID.size();
            return true;
         }
      }

      return false;
   }

   public final void clearSelectedProvinces() {
      this.lProvincesID.clear();
      this.iProvincesSize = 0;
   }

   public final boolean checkIfExists(int nProvinceID) {
      for (int i = 0; i < this.iProvincesSize; i++) {
         if (this.lProvincesID.get(i) == nProvinceID) {
            return true;
         }
      }

      return false;
   }

   public final void updateArmies_CivID(int nCivID, int nArmy) {
      for (int i = 0; i < this.getProvincesSize(); i++) {
         if ((CFG.gameAction.hasArmyInProvince(this.getProvince(i), nCivID) || this.canAddArmy(nCivID, this.getProvince(i)))
            && nArmy != CFG.game.getProvince(this.getProvince(i)).getArmyCivID(nCivID)) {
            CFG.game.getProvince(this.getProvince(i)).updateArmy(nCivID, nArmy);
         }
      }
   }

   public final boolean canAddArmy(int nCivID, int nProvinceID) {
      return CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getAllianceID() > 0
            && CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getAllianceID() == CFG.game.getCiv(nCivID).getAllianceID()
         ? true
         : CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getPuppetOfCivID() == nCivID;
   }

   public final boolean canBeReleasedAsVassal(int nCivID, int nProvinceID) {
      return CFG.game.getCiv(nCivID).getCapitalProvinceID() == nProvinceID ? false : CFG.game.getProvince(nProvinceID).getCivID() == nCivID;
   }

   public final List<Integer> getProvinces() {
      return this.lProvincesID;
   }

   public final int getProvince(int i) {
      return this.lProvincesID.get(i);
   }

   public final int getProvincesSize() {
      return this.iProvincesSize;
   }
}
