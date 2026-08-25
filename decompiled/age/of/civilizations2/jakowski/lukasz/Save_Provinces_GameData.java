package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Save_Provinces_GameData implements Serializable {
   public static final long serialVersionUID = 0L;
   public float fZiverts = 0.0F;
   public String sProvinceName;
   public List<Province_Army> lArmies = new ArrayList<>();
   public int iCivsSize;
   public List<City> lCities = new ArrayList<>();
   public int iTrueOwnerOfProvince = 0;
   public boolean isCapital = false;
   public boolean isPartOfHolyRomaEmpire = false;
   public PlagueProvince_GameData provincePlague = null;
   public int iNumOfPlaguesTotal = 0;
   public int iPlaguesDeaths = 0;
   public int iLastPlagueTurnID = 0;
   public int iNumOfTurnsWithBalanceOnMinus = 0;
   public int iNewColonyBonus = 0;
   public List<Province_SupportRebels> lSupportRebels = new ArrayList<>();
   public int iSupportRebelsSize = 0;
   public int turnChange_Population = 0;
   public int turnChange_Economy = 0;
   public float turnChange_Development = 0.0F;
   public float turnChange_Happiness = 0.0F;
   public float turnChange_Stability = 0.0F;
   public float turnChange_RevRisk = 0.0F;
   public int iNumOfRevolutions = 0;
   public Province_Population oPopulation;
   public int iEconomy;
   public float fDevelopmentLevel;
   public float fHappiness = 0.85F;
   public float fRevolutionaryRisk = 0.0F;
   public Province_Core oProvinceCore = null;
   public int isNotSuppliedForXTurns = -1;
   public int iDefensivePosition = 0;
   public int iWatchTower;
   public int iFort;
   public int iPort;
   public int iFarm;
   public int iLibrary;
   public int iNuclearReactor;
   public int iShelter;
   public int iArmoury;
   public int iWorkshop;
   public int iSupply;
   public int iAirBase;
   public int[] iCustomBuilding;
   public byte wasConquered;
   public byte wasAttacked;
   public byte neighbooringProvinceOfCivWasLost;
   public int wastelandLevel;
   public Province_Airbase AirBase;

   Save_Provinces_GameData() {
      CFG.game.getGame_CustomBuildingsManager();
      this.iCustomBuilding = new int[CustomBuildingsManager.getCustomBuildingsAmount()];
      this.wasConquered = 0;
      this.wasAttacked = 0;
      this.neighbooringProvinceOfCivWasLost = 0;
      this.wastelandLevel = -1;
   }

   public final void resetData() {
      this.iLastPlagueTurnID = -19;
      this.iPlaguesDeaths = 0;
      this.iNumOfPlaguesTotal = 0;
      this.provincePlague = null;
      this.turnChange_Population = 0;
      this.turnChange_Economy = 0;
      this.turnChange_Development = 0.0F;
      this.turnChange_Happiness = 0.0F;
      this.turnChange_Stability = 0.0F;
      this.turnChange_RevRisk = 0.0F;
      this.iNewColonyBonus = 0;
      this.iNumOfTurnsWithBalanceOnMinus = 0;
      this.iNumOfRevolutions = 0;
      this.lSupportRebels = new ArrayList<>();
      this.iSupportRebelsSize = 0;
      CFG.game.getGame_CustomBuildingsManager();
      this.iCustomBuilding = new int[CustomBuildingsManager.getCustomBuildingsAmount()];
   }
}
