package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.GdxRuntimeException;
import java.io.IOException;

public class HolyRomanEmpire_Manager {
   public static final String HOLY_ROMAN_EMPRIE_TAG = "holy";
   public static final int MAX_NUM_OF_ELECTORS = 7;
   public HolyRomanEmpire_GameData holyRomanEmpire;
   public static final Color oColorHRE = new Color(1.0F, 0.8F, 0.11764706F, 1.0F);
   public static final Color oColorHRE_BG = new Color(1.0F, 1.0F, 0.0F, 1.0F);
   public static final Color oColorHRE_Emperor = new Color(1.0F, 1.0F, 0.0F, 1.0F);
   public static final Color oColorHRE_Electors = new Color(0.91764706F, 0.74509805F, 0.1764706F, 1.0F);
   public static final Color oColorHRE_NotControledByEmpire = new Color(0.92156863F, 0.039215688F, 0.039215688F, 1.0F);
   public String sHRE_Name;

   public HolyRomanEmpire_Manager() {
      this.updateHREName();
   }

   public final void initHolyRomanEmpire() {
      this.holyRomanEmpire = null;
      this.holyRomanEmpire = new HolyRomanEmpire_GameData();
   }

   public final void loadHolyRomanEmpire_ScenarioData() {
      this.holyRomanEmpire = null;
      FileHandle file = CFG.game.getGameScenarios().getScenarioIsInternal(CFG.game.getScenarioID())
         ? Gdx.files
            .internal(
               "map/"
                  + CFG.map.getFile_ActiveMap_Path()
                  + "scenarios/"
                  + CFG.game.getGameScenarios().getScenarioTag(CFG.game.getScenarioID())
                  + "/"
                  + CFG.game.getGameScenarios().getScenarioTag(CFG.game.getScenarioID())
                  + "_HRE"
            )
         : Gdx.files
            .local(
               "map/"
                  + CFG.map.getFile_ActiveMap_Path()
                  + "scenarios/"
                  + CFG.game.getGameScenarios().getScenarioTag(CFG.game.getScenarioID())
                  + "/"
                  + CFG.game.getGameScenarios().getScenarioTag(CFG.game.getScenarioID())
                  + "_HRE"
            );

      try {
         this.holyRomanEmpire = (HolyRomanEmpire_GameData)CFG.deserialize(file.readBytes());

         for (int i = 0; i < CFG.holyRomanEmpire_Manager.getHRE().getProvincesSize(); i++) {
            try {
               if (!CFG.game.getProvince(CFG.holyRomanEmpire_Manager.getHRE().getProvinces(i)).getSeaProvince()) {
                  CFG.game.getProvince(CFG.holyRomanEmpire_Manager.getHRE().getProvinces(i)).setIsPartOfHolyRomanEmpire(true);
               }
            } catch (IndexOutOfBoundsException var5) {
            }
         }

         for (int var9 = 0; var9 < CFG.holyRomanEmpire_Manager.getHRE().getPrincesSize(); var9++) {
            try {
               CFG.game.getCiv(CFG.holyRomanEmpire_Manager.getHRE().getPrince(var9)).setIsPartOfHolyRomanEmpire(true);
            } catch (IndexOutOfBoundsException var4) {
               CFG.holyRomanEmpire_Manager.getHRE().removePrinceID(var9--);
            }
         }

         return;
      } catch (ClassNotFoundException var6) {
      } catch (IOException var7) {
      } catch (GdxRuntimeException var8) {
      }

      this.initHolyRomanEmpire();
   }

   public final HolyRomanEmpire_GameData getHRE() {
      return this.holyRomanEmpire;
   }

   public final boolean addProvince(int nProvinceID) {
      if (this.holyRomanEmpire.addProvince(nProvinceID)
         && CFG.game.getProvince(nProvinceID).getCivID() > 0
         && CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getCapitalProvinceID() == nProvinceID) {
         if (CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getPuppetOfCivID() != CFG.game.getProvince(nProvinceID).getCivID()) {
            if (this.holyRomanEmpire.getIsPrince(CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getPuppetOfCivID())) {
               for (int i2 = 0; i2 < this.holyRomanEmpire.getPrincesSize(); i2++) {
                  if (this.holyRomanEmpire.getPrince(i2) == CFG.game.getProvince(nProvinceID).getCivID()) {
                     return false;
                  }
               }

               this.holyRomanEmpire.addPrince(CFG.game.getProvince(nProvinceID).getCivID());
               return true;
            } else {
               return false;
            }
         } else {
            for (int i = 0; i < this.holyRomanEmpire.getPrincesSize(); i++) {
               if (this.holyRomanEmpire.getPrince(i) == CFG.game.getProvince(nProvinceID).getCivID()) {
                  return false;
               }
            }

            for (int var4 = 0; var4 < this.holyRomanEmpire.getProvincesSize(); var4++) {
               if (CFG.game.getProvince(this.holyRomanEmpire.getProvinces(var4)).getCivID() != CFG.game.getProvince(nProvinceID).getCivID()
                  && CFG.game.getCiv(CFG.game.getProvince(this.holyRomanEmpire.getProvinces(var4)).getCivID()).getPuppetOfCivID()
                     == CFG.game.getProvince(nProvinceID).getCivID()
                  && CFG.game.getProvince(this.holyRomanEmpire.getProvinces(var4)).getCivID() > 0
                  && this.holyRomanEmpire
                     .getIsImperialProvince(CFG.game.getCiv(CFG.game.getProvince(this.holyRomanEmpire.getProvinces(var4)).getCivID()).getCapitalProvinceID())) {
                  this.holyRomanEmpire.addPrince(CFG.game.getProvince(this.holyRomanEmpire.getProvinces(var4)).getCivID());
               }
            }

            this.holyRomanEmpire.addPrince(CFG.game.getProvince(nProvinceID).getCivID());
            return true;
         }
      } else {
         return false;
      }
   }

   public final boolean removeProvince(int nProvinceID) {
      if (this.holyRomanEmpire.removeProvince(nProvinceID)
         && CFG.game.getProvince(nProvinceID).getCivID() > 0
         && CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getCapitalProvinceID() == nProvinceID) {
         for (int i = 0; i < this.holyRomanEmpire.getPrincesSize(); i++) {
            if (this.holyRomanEmpire.getPrince(i) == CFG.game.getProvince(nProvinceID).getCivID()) {
               this.holyRomanEmpire.removePrince(CFG.game.getProvince(nProvinceID).getCivID());
               return true;
            }
         }
      }

      return false;
   }

   public final String getHRE_Name() {
      return this.sHRE_Name;
   }

   public final void updateHREName() {
      this.sHRE_Name = CFG.langManager.getCiv("holy");
   }
}
