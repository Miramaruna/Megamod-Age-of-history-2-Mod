package age.of.civilizations2.jakowski.lukasz;

public class VicotryManager {
   public static int VICTORY_CONTROL_PROVINCES_PERC = 100;
   public static int VICTORY_LIMIT_OF_TURNS = 0;
   public static float VICTORY_TECHNOLOGY = 0.0F;
   public static int domination_NumOfCivsInGame = 1;
   public static int update_AttackBonusCivs = 1;
   public static int controlProvinces_NumOfProvinces = 1;

   VicotryManager() {
   }

   public static float getDefault_VcitoryTechnology() {
      return 0.0F;
   }

   public static final void checkVictoryConditions() {
      updateVictoryConditions();
      if (VICTORY_TECHNOLOGY > 0.0F) {
         for (int i = 1; i < CFG.game.getCivsSize(); i++) {
            if (VICTORY_TECHNOLOGY <= CFG.game.getCiv(i).getTechnologyLevel()) {
               VICTORY_TECHNOLOGY = CFG.game.getCiv(i).getTechnologyLevel() + 0.01F;
               if (CFG.game.getCiv(i).getTechnologyLevel() >= 1.0F) {
                  VICTORY_TECHNOLOGY = 0.0F;
                  break;
               }
            }
         }
      }

      if (VICTORY_CONTROL_PROVINCES_PERC < 100) {
         for (int ix = 1; ix < CFG.game.getCivsSize(); ix++) {
            if (controlProvinces_GetCivScore(ix) >= VICTORY_CONTROL_PROVINCES_PERC) {
               VICTORY_CONTROL_PROVINCES_PERC = (int)Math.ceil(controlProvinces_GetCivScore(ix)) + 1;
            }
         }
      }
   }

   public static final void updateVictoryConditions() {
      domination_UpdateNumOfCivs();
      controlProvinces_UpdateNumOfProvinces();
      UpdateAttackBonus();
   }

   public static final void domination_UpdateNumOfCivs() {
      domination_NumOfCivsInGame = 0;

      for (int i = 1; i < CFG.game.getCivsSize(); i++) {
         if (CFG.game.getCiv(i).getNumOfProvinces() > 0) {
            domination_NumOfCivsInGame++;
         }
      }

      domination_NumOfCivsInGame = Math.max(domination_NumOfCivsInGame, 1);
   }

   public static final void UpdateAttackBonus() {
      update_AttackBonusCivs = 0;

      for (int i = 1; i < CFG.game.getCivsSize(); i++) {
         if (CFG.game.getCiv(i).getNumOfProvinces() > 0) {
            update_AttackBonusCivs++;
         }
      }

      update_AttackBonusCivs = Math.max(update_AttackBonusCivs, 1);
   }

   public static final int domination_CivScore(int nCivID) {
      if (CFG.game.getCiv(nCivID).getNumOfProvinces() <= 0) {
         return CFG.game.getCiv(nCivID).getNumOfProvinces();
      } else {
         int out = 1;

         for (int i = CFG.game.getCiv(nCivID).civGameData.lVassals.size() - 1; i >= 0; i--) {
            if (CFG.game.getCiv(CFG.game.getCiv(nCivID).civGameData.lVassals.get(i).iCivID).getNumOfProvinces() > 0) {
               out++;
            }
         }

         return out;
      }
   }

   public static final int PowerXP_CivScore(int nCivID) {
      if (CFG.game.getCiv(nCivID).getNumOfProvinces() <= 0) {
         return CFG.game.getCiv(nCivID).getNumOfProvinces();
      } else {
         int out = 1;

         for (int i = CFG.game.getCiv(nCivID).civGameData.lVassals.size() - 1; i >= 0; i--) {
            if (CFG.game.getCiv(CFG.game.getCiv(nCivID).civGameData.lVassals.get(i).iCivID).getNumOfProvinces() > 0) {
               out++;
            }
         }

         return out;
      }
   }

   public static final void controlProvinces_UpdateNumOfProvinces() {
      controlProvinces_NumOfProvinces = 0;

      for (int i = 1; i < CFG.game.getCivsSize(); i++) {
         controlProvinces_NumOfProvinces = controlProvinces_NumOfProvinces + CFG.game.getCiv(i).getNumOfProvinces();
      }

      controlProvinces_NumOfProvinces = Math.max(controlProvinces_NumOfProvinces, 1);
   }

   public static final float controlProvinces_GetCivScore(int nCivID) {
      return (float)CFG.game.getCiv(nCivID).getNumOfProvinces() / controlProvinces_NumOfProvinces * 100.0F;
   }

   public static int turnsLimit_TurnsLeft() {
      return VICTORY_LIMIT_OF_TURNS - Game_Calendar.TURN_ID;
   }

   public static int technology_BestCiv() {
      int iBest = 0;

      for (int i = 1; i < CFG.game.getCivsSize(); i++) {
         if (CFG.game.getCiv(i).getNumOfProvinces() > 0
            && (CFG.game.getCiv(iBest).getTechnologyLevel() < CFG.game.getCiv(i).getTechnologyLevel() || iBest == 0)) {
            iBest = i;
         }
      }

      return iBest;
   }
}
