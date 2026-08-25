package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Save_Civ_GameData implements Serializable {
   public static final long serialVersionUID = 0L;
   public boolean haveNotMoney;
   public float iNuclearProgress = 0.0F;
   public int iManPower;
   public int EconomyType = CFG.settingsManager.EXPERIMENTAL_GAMEPLAY ? CFG.oR.nextInt(1, 4) : 0;
   public int ConscriptionLaw = CFG.settingsManager.EXPERIMENTAL_GAMEPLAY ? CFG.oR.nextInt(1, 6) : 0;
   public int LevelCentralization = CFG.settingsManager.EXPERIMENTAL_GAMEPLAY ? CFG.oR.nextInt(1, 6) : 0;
   public int LevelMedicine = CFG.settingsManager.EXPERIMENTAL_GAMEPLAY ? CFG.oR.nextInt(1, 5) : 0;
   public int LevelEducation = CFG.settingsManager.EXPERIMENTAL_GAMEPLAY ? CFG.oR.nextInt(1, 5) : 0;
   public int LevelMilitarySpending = CFG.settingsManager.EXPERIMENTAL_GAMEPLAY ? CFG.oR.nextInt(1, 7) : 0;
   public int LevelMilitaryTactic = CFG.settingsManager.EXPERIMENTAL_GAMEPLAY ? CFG.oR.nextInt(1, 4) : 0;
   public int iFood;
   public int iMilitaryPoints;
   public int iСapitulationPoints;
   public int iNuclearWeapons;
   public int iManPowerIncreasing_PerTurn = 0;
   public int iManPower_InThisTurn = 0;
   public int iPuppetOfCivID;
   public float fVassalLiberityDisere = 0.0F;
   public LeaderOfCiv_GameData leaderData;
   public List<Vassal_GameData> lVassals = new ArrayList<>();
   public int iVassalsSize = 0;
   public int iAI_Style = 0;
   public CivPersonality civPersonality = new CivPersonality();
   public CivPlans civPlans = new CivPlans();
   public int iRegroupArmyAtPeace_CheckTurnID = 0;
   public String sCivTag;
   public String sCivName;
   public int iCapitalProvinceID;
   public int iCoreCapitalProvinceID = -1;
   public int iCapitalMoved_LastTurnID = -50;
   public boolean isPartOfHolyRomaEmpire = false;
   public List<String> lEvents_DecisionsTaken = new ArrayList<>();
   public Civilization_Diplomacy_GameData civilization_Diplomacy_GameData;
   public short iR;
   public short iG;
   public short iB;
   public List<RegroupArmy_Data> lRegroupArmy;
   public int iRegroupArmySize;
   public List<Civilization_Colonies> lColonies_Founded = new ArrayList<>();
   public int iLockColonization_UntilTurnID = 1;
   public Civ_Mission_ChangeTypeOfGoverment changeTypeOfGoverment = null;
   public int iExpandNeutralProvinces_RangeCheck = 6;
   public int iNextPossibleNavalInvastionTurnID = 0;
   public List<AI_CivsInRange> civsInRange = new ArrayList<>();
   public int nextBuildCivsInRange_TurnID = 0;
   public int holdLookingForEnemy_UntilTurnID = 0;
   public int holdLookingForFriends_UntilTurnID = 1;
   public List<AI_Influence> civsInfluenced = new ArrayList<>();
   public int civsInfluencedSize = 0;
   public List<AI_Rival> civRivals = new ArrayList<>();
   public int civRivalsSize = 0;
   public int declareWar_CheckNextTurnID = 5;
   public int nextArmyRestREgroupment_TurnID = 0;
   public int numOfUnions = 0;
   public float civAggresionLevel = 0.0F;
   public int CasusBelliModificator = 0;
   public int allianceCheck_TurnID = 0;
   public int allianceUpdate_TurnID = 0;
   public int circledVassals_TurnID = 0;
   public int circledUltimatum_TurnID = 0;
   public int checkFormCiv_TurnID = 0;
   public int iPlunder_LastTurnID = 0;
   public int iNextCheckMilitaryAccessTurnID = 0;
   public int iNextCheckMilitaryAccessSeaTurnID = 0;
   public int iSendGift_LastTurnID = 5;
   public int iNumOfConqueredProvinces = 0;
   public int iNumOfBuildingsConstructed = 0;
   public int iRecruitedArmy = 0;
   public int iLockTreasury = 1;
   public int iNumOfRevolutions = 0;
   public boolean moveAtWar_ArmyFullyRecruitedLastTurn = false;
   public int moveAtWar_ProvincesLostAndConquered_LastTurn = 0;
   public Skills_GameData skills = new Skills_GameData();
   public float fModifier_PopGrowth = 0.0F;
   public float fModifier_EconomyGrowth = 0.0F;
   public float fModifier_IncomeTaxation = 0.0F;
   public float fModifier_IncomeProduction = 0.0F;
   public float fModifier_RecruitablePopulation = 0.1F;
   public float fModifier_Administration = 0.0F;
   public float fModifier_Research = 0.0F;
   public float fModifier_MilitaryUpkeep = 0.0F;
   public float fModifier_AttackBonus = 0.0F;
   public float fModifier_GenocidePower = 1.0F;
   public float fModifier_DefenseBonus = 0.0F;
   public float fModifier_MovementPoints = 0.0F;
   public float fModifier_ColonizationCost = 0.0F;
   public List<CivBonus_GameData> lBonuses = new ArrayList<>();
   public int iGoldenAge_Prosperity = 0;
   public int iGoldenAge_Military = 0;
   public int iGoldenAge_Science = 0;
   public int iNumOfTurnsAtWar = 0;
   public float fWarWeariness = 0.0F;
   public float fHunger = 0.0F;
   public long iMoney;
   public List<Loan_GameData> lLoansTaken;
   public List<WarReparations> lWarReparationsPay;
   public List<WarReparations> lWarReparationsGets;
   public int iDiplomacyPoints;
   public int fTechnologyLevel;
   public float fSpendings_Research = 0.0F;
   public float fSpendings_Investments = 0.15F;
   public float fSpendings_Goods = 0.2F;
   public float fResearchProgress = 0.0F;
   public float fTaxationLevel = 0.1F;
   public List<CivFestival> lFestivals = new ArrayList<>();
   public List<CivFestival> lAssimilates = new ArrayList<>();
   public List<CivInvest> lInvest = new ArrayList<>();
   public List<CivInvest_Development> lInvest_Development = new ArrayList<>();
   public List<Construction_GameData> lConstructions = new ArrayList<>();
   public int iAllianceID = 0;
   public List<Float> lRelation = new ArrayList<>();
   public int iRevolt_SinceTurn = 1;
   public int iRevolt_LastTurnLostProvince = 1;
   public List<Civilization_Hated_GameData> lHatedCivs = new ArrayList<>();
   public int iHatedCivsSize = 0;
   public List<Integer> lHatedCivs_By = new ArrayList<>();
   public int iHatedCivs_BySize = 0;
   public List<Civilization_Friends_GameData> lFriendlyCivs = new ArrayList<>();
   public List<Civilization_SentMessages> lSentMessages = new ArrayList<>();
   public List<Civ_Gift_GameData> lGifts_Received = new ArrayList<>();

   Save_Civ_GameData() {
   }

   public final void addGift_Received(int iCivID) {
      for (int i = this.lGifts_Received.size() - 1; i >= 0; i--) {
         if (this.lGifts_Received.get(i).iFromCivID == iCivID) {
            this.lGifts_Received.get(i).iTurnID = Game_Calendar.TURN_ID;
            return;
         }
      }

      this.lGifts_Received.add(new Civ_Gift_GameData(iCivID, Game_Calendar.TURN_ID));
   }

   public final void updateGift_Received() {
      for (int i = this.lGifts_Received.size() - 1; i >= 0; i--) {
         if (this.lGifts_Received.get(i).iTurnID + 5 < Game_Calendar.TURN_ID) {
            this.lGifts_Received.remove(i);
         }
      }
   }

   public final void setLeader(LeaderOfCiv_GameData nLeaderData) {
      if (this.leaderData != null) {
         this.fModifier_PopGrowth = this.fModifier_PopGrowth - this.leaderData.fModifier_PopGrowth;
         this.fModifier_EconomyGrowth = this.fModifier_EconomyGrowth - this.leaderData.fModifier_EconomyGrowth;
         this.fModifier_IncomeTaxation = this.fModifier_IncomeTaxation - this.leaderData.fModifier_IncomeTaxation;
         this.fModifier_IncomeProduction = this.fModifier_IncomeProduction - this.leaderData.fModifier_IncomeProduction;
         this.fModifier_Administration = this.fModifier_Administration - this.leaderData.fModifier_Administration;
         this.fModifier_Research = this.fModifier_Research - this.leaderData.fModifier_Research;
         this.fModifier_MilitaryUpkeep = this.fModifier_MilitaryUpkeep - this.leaderData.fModifier_MilitaryUpkeep;
         this.fModifier_AttackBonus = this.fModifier_AttackBonus - this.leaderData.fModifier_AttackBonus;
         this.fModifier_DefenseBonus = this.fModifier_DefenseBonus - this.leaderData.fModifier_DefenseBonus;
         this.fModifier_MovementPoints = this.fModifier_MovementPoints - this.leaderData.fModifier_MovementPoints;
      }

      if (nLeaderData != null) {
         if (nLeaderData.fModifier_PopGrowth > 0.25F) {
            nLeaderData.fModifier_PopGrowth = 0.25F;
         } else if (nLeaderData.fModifier_PopGrowth < -0.25F) {
            nLeaderData.fModifier_PopGrowth = -0.25F;
         }

         if (nLeaderData.fModifier_EconomyGrowth > 0.25F) {
            nLeaderData.fModifier_EconomyGrowth = 0.25F;
         } else if (nLeaderData.fModifier_EconomyGrowth < -0.25F) {
            nLeaderData.fModifier_EconomyGrowth = -0.25F;
         }

         if (nLeaderData.fModifier_IncomeTaxation > 0.25F) {
            nLeaderData.fModifier_IncomeTaxation = 0.25F;
         } else if (nLeaderData.fModifier_IncomeTaxation < -0.25F) {
            nLeaderData.fModifier_IncomeTaxation = -0.25F;
         }

         if (nLeaderData.fModifier_IncomeProduction > 0.25F) {
            nLeaderData.fModifier_IncomeProduction = 0.25F;
         } else if (nLeaderData.fModifier_IncomeProduction < -0.25F) {
            nLeaderData.fModifier_IncomeProduction = -0.25F;
         }

         if (nLeaderData.fModifier_Administration > 0.25F) {
            nLeaderData.fModifier_Administration = 0.25F;
         } else if (nLeaderData.fModifier_Administration < -0.25F) {
            nLeaderData.fModifier_Administration = -0.25F;
         }

         if (nLeaderData.fModifier_Research > 0.25F) {
            nLeaderData.fModifier_Research = 0.25F;
         } else if (nLeaderData.fModifier_Research < -0.25F) {
            nLeaderData.fModifier_Research = -0.25F;
         }

         if (nLeaderData.fModifier_MilitaryUpkeep > 0.25F) {
            nLeaderData.fModifier_MilitaryUpkeep = 0.25F;
         } else if (nLeaderData.fModifier_MilitaryUpkeep < -0.25F) {
            nLeaderData.fModifier_MilitaryUpkeep = -0.25F;
         }

         if (nLeaderData.fModifier_AttackBonus > 0.25F) {
            nLeaderData.fModifier_AttackBonus = 0.25F;
         } else if (nLeaderData.fModifier_AttackBonus < -0.25F) {
            nLeaderData.fModifier_AttackBonus = -0.25F;
         }

         if (nLeaderData.fModifier_DefenseBonus > 0.25F) {
            nLeaderData.fModifier_DefenseBonus = 0.25F;
         } else if (nLeaderData.fModifier_DefenseBonus < -0.25F) {
            nLeaderData.fModifier_DefenseBonus = -0.25F;
         }

         if (nLeaderData.fModifier_MovementPoints > 0.25F) {
            nLeaderData.fModifier_MovementPoints = 0.25F;
         } else if (nLeaderData.fModifier_MovementPoints < -0.25F) {
            nLeaderData.fModifier_MovementPoints = -0.25F;
         }

         this.fModifier_PopGrowth = this.fModifier_PopGrowth + nLeaderData.fModifier_PopGrowth;
         this.fModifier_EconomyGrowth = this.fModifier_EconomyGrowth + nLeaderData.fModifier_EconomyGrowth;
         this.fModifier_IncomeTaxation = this.fModifier_IncomeTaxation + nLeaderData.fModifier_IncomeTaxation;
         this.fModifier_IncomeProduction = this.fModifier_IncomeProduction + nLeaderData.fModifier_IncomeProduction;
         this.fModifier_Administration = this.fModifier_Administration + nLeaderData.fModifier_Administration;
         this.fModifier_Research = this.fModifier_Research + nLeaderData.fModifier_Research;
         this.fModifier_MilitaryUpkeep = this.fModifier_MilitaryUpkeep + nLeaderData.fModifier_MilitaryUpkeep;
         this.fModifier_AttackBonus = this.fModifier_AttackBonus + nLeaderData.fModifier_AttackBonus;
         this.fModifier_DefenseBonus = this.fModifier_DefenseBonus + nLeaderData.fModifier_DefenseBonus;
         this.fModifier_MovementPoints = this.fModifier_MovementPoints + nLeaderData.fModifier_MovementPoints;
      }

      this.leaderData = nLeaderData;
   }
}
