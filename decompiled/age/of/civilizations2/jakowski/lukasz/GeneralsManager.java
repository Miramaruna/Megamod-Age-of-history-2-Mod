package age.of.civilizations2.jakowski.lukasz;

import java.util.ArrayList;
import java.util.List;

public class GeneralsManager {
   public static List<General_Data> lGenerals = new ArrayList<>();
   public static int SELECTED_GENERAL = -1;

   private static final String[] NAMES = new String[]{
      "Суворов",
      "Кутузов",
      "Багратион",
      "Барклай",
      "Румянцев",
      "Потёмкин",
      "Наполеон",
      "Мюрат",
      "Веллингтон",
      "Жуков",
      "Конев",
      "Рокоссовский",
      "Ватутин",
      "Черняховский",
      "Монтгомери",
      "Роммель",
      "Гудериан",
      "Манштейн",
      "Паттон",
      "Макартур"
   };

   public static final General_Data getGeneral(int nID) {
      return nID >= 0 && nID < lGenerals.size() ? lGenerals.get(nID) : null;
   }

   public static final List<General_Data> getPlayerGenerals() {
      ArrayList<General_Data> tOut = new ArrayList<>();
      int tPlayerCivID = CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID();

      for (General_Data tGen : lGenerals) {
         if (tGen.iCivID == tPlayerCivID) {
            tOut.add(tGen);
         }
      }

      return tOut;
   }

   public static final long getHireCost() {
      return 1000L * (long)(getPlayerGenerals().size() + 1);
   }

   public static final boolean hireGeneral() {
      int tPlayerCivID = CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID();
      if (getPlayerGenerals().size() >= 8) {
         CFG.toast.setInView(CFG.langManager.get("Generals_Max"), CFG.COLOR_TEXT_MODIFIER_NEGATIVE2);
         CFG.toast.setTimeInView(3000);
         return false;
      }

      long tCost = getHireCost();
      if ((float)CFG.game.getCiv(tPlayerCivID).getMoney() < (float)tCost) {
         CFG.toast.setInView(CFG.langManager.get("NotEnoughCoins") + ": " + tCost, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2);
         CFG.toast.setTimeInView(3000);
         return false;
      }

      String tName = null;

      for (int tries = 0; tries < 50 && tName == null; tries++) {
         String tCandidate = NAMES[CFG.oR.nextInt(NAMES.length)];
         boolean tUsed = false;

         for (General_Data tGen : lGenerals) {
            if (tGen.sName.equals(tCandidate)) {
               tUsed = true;
               break;
            }
         }

         if (!tUsed) {
            tName = tCandidate;
         }
      }

      if (tName == null) {
         tName = "Генерал #" + (lGenerals.size() + 1);
      }

      CFG.game.getCiv(tPlayerCivID).setMoney(CFG.game.getCiv(tPlayerCivID).getMoney() - tCost);
      lGenerals.add(new General_Data(tPlayerCivID, tName));
      CFG.menuManager.updateInGame_TOP_All(tPlayerCivID);
      CFG.toast.setInView(CFG.langManager.get("Hired") + ": " + tName, CFG.COLOR_TEXT_MODIFIER_POSITIVE);
      CFG.toast.setTimeInView(3000);
      return true;
   }

   public static final void dismissGeneral(int nID) {
      General_Data tGen = getGeneral(nID);
      if (tGen != null && tGen.iCivID == CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) {
         lGenerals.remove(nID);
      }
   }

   public static final float getAttackBonus(int nCivID, int nProvinceID) {
      for (General_Data tGen : lGenerals) {
         if (tGen.iCivID == nCivID && tGen.lProvinces.contains(nProvinceID)) {
            return tGen.getBonus();
         }
      }

      return 0.0F;
   }
}
