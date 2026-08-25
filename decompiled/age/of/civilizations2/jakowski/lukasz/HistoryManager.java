package age.of.civilizations2.jakowski.lukasz;

import java.util.ArrayList;
import java.util.List;

public class HistoryManager {
   public static List<String> lHistoryDates = new ArrayList<>();
   public static List<Integer> lHistoryDatesWidth = new ArrayList<>();
   public static int HISTORY_LIMIT = 200;

   public HistoryManager() {
      CFG.timelapseManager.timelapseStatsGD.lHistory = new ArrayList<>();
      this.addNewTurn();
      HistoryLog.ICON_WIDTH = (int)(ImageManager.getImage(Images.diplo_war).getWidth() * HistoryLog.getImageScale(Images.diplo_war));
      if (HistoryLog.ICON_WIDTH < (int)(ImageManager.getImage(Images.diplo_war).getWidth() * HistoryLog.getImageScale(Images.diplo_truce))) {
         HistoryLog.ICON_WIDTH = (int)(ImageManager.getImage(Images.diplo_war).getWidth() * HistoryLog.getImageScale(Images.diplo_truce));
      }

      if (HistoryLog.ICON_WIDTH < (int)(ImageManager.getImage(Images.diplo_war).getWidth() * HistoryLog.getImageScale(Images.diplo_alliance))) {
         HistoryLog.ICON_WIDTH = (int)(ImageManager.getImage(Images.diplo_war).getWidth() * HistoryLog.getImageScale(Images.diplo_alliance));
      }

      for (int i = 0; i < CFG.ideologiesManager.getIdeologiesSize(); i++) {
         if (HistoryLog.ICON_WIDTH < (int)(CFG.ideologiesManager.getIdeology(i).getiCrownVassalImage().getWidth() * HistoryLog.getImageScale_CrownVassal(i))) {
            HistoryLog.ICON_WIDTH = (int)(CFG.ideologiesManager.getIdeology(i).getiCrownVassalImage().getWidth() * HistoryLog.getImageScale_CrownVassal(i));
         }
      }

      HistoryLog.ICON_WIDTH = HistoryLog.ICON_WIDTH + CFG.PADDING * 3;
      HISTORY_LIMIT = CFG.isDesktop() ? 200 : 50;
   }

   public final void updateLanguage() {
      for (int i = 0; i < CFG.timelapseManager.timelapseStatsGD.lHistory.size(); i++) {
         for (int j = 0; j < CFG.timelapseManager.timelapseStatsGD.lHistory.get(j).size(); j++) {
            CFG.timelapseManager.timelapseStatsGD.lHistory.get(i).get(j).updateLanguage();
         }
      }
   }

   public static final void buildHistoryDates() {
      clearHistoryDates();

      for (int i = 1; i < Game_Calendar.TURN_ID; i++) {
         lHistoryDates.add(Game_Calendar.getDate_ByTurnID(i));
      }

      lHistoryDates.add(Game_Calendar.getCurrentDate());
      int iSize = lHistoryDates.size();

      for (int var2 = 0; var2 < iSize; var2++) {
         CFG.glyphLayout.setText(CFG.fontMain, lHistoryDates.get(var2) + ": ");
         lHistoryDatesWidth.add((int)(CFG.glyphLayout.width * 0.7F));
      }
   }

   public static final void clearHistoryDates() {
      lHistoryDates.clear();
      lHistoryDatesWidth.clear();
   }

   public final boolean haveHistory() {
      for (int i = 0; i < CFG.timelapseManager.timelapseStatsGD.lHistory.size(); i++) {
         if (CFG.timelapseManager.timelapseStatsGD.lHistory.get(i).size() > 0) {
            return true;
         }
      }

      return false;
   }

   public final void addNewTurn() {
      ArrayList turnHistory = new ArrayList();
      CFG.timelapseManager.timelapseStatsGD.lHistory.add(turnHistory);
      if (CFG.timelapseManager.timelapseStatsGD.lHistory.size() > HISTORY_LIMIT) {
         CFG.timelapseManager.timelapseStatsGD.lHistory.remove(0);
      }
   }

   public final void addHistoryLog(HistoryLog tHL) {
      try {
         CFG.timelapseManager.timelapseStatsGD.lHistory.get(CFG.timelapseManager.timelapseStatsGD.lHistory.size() - 1).add(tHL);
         if (CFG.menuManager.getVisibleInGame_History()) {
            CFG.menuManager.rebuildInGame_History();
         }
      } catch (IndexOutOfBoundsException var3) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var3);
         }
      } catch (NullPointerException var4) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var4);
         }
      }
   }

   public final void addHistory(int iTurnID, HistoryLog nHistory) {
      CFG.timelapseManager.timelapseStatsGD.lHistory.get(iTurnID).add(nHistory);
   }

   public final HistoryLog getHistory(int iTurnID, int i) {
      return CFG.timelapseManager.timelapseStatsGD.lHistory.get(iTurnID).get(i);
   }

   public final void clearHistory() {
      CFG.timelapseManager.timelapseStatsGD.lHistory.clear();
   }

   public final int getHistorySize() {
      return CFG.timelapseManager.timelapseStatsGD.lHistory.size();
   }

   public final int getHistoryTurnSize(int iTurnID) {
      return CFG.timelapseManager.timelapseStatsGD.lHistory.get(iTurnID).size();
   }
}
