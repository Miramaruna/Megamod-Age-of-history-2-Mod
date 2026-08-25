package age.of.civilizations2.jakowski.lukasz;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;

public class WorldNews {
   public static boolean PENDING = false;
   public static int FRESH_WARS_COUNT = 0;
   public static ArrayList<String> LINES = new ArrayList<>();
   public static String HEADER = "Газета";
   public static String DATELINE = "";
   public static int PIC = Images.diplo_war;
   public static String PIC_NAME = "";
   public static HashSet<String> ANNOUNCED_WARS = new HashSet<>();

   private static final String[] HEADERS = {
      "МИРОВЫЕ НОВОСТИ",
      "ХРОНИКА ЭПОХИ",
      "ВЕСТНИК ВОЙНЫ И МИРА",
      "СВОДКА С ФРОНТОВ",
      "Газета «Время перемен»"
   };

   private static final String[] PEACE_LINES = {
      "Штыки воткнуты в землю — крупные державы наслаждаются затишьем.",
      "Дипломаты трудятся не покладая рук: новых войн не объявлено.",
      "Страны наращивают армии, но порох остаётся сухим.",
      "Народы живут и торгуют. Пока."
   };

   private static final int[] PICS = {
      Images.diplo_war,
      Images.diplo_rivals,
      Images.diplo_army,
      Images.diplo_message,
      Images.diplo_lord
   };

   public static void prepare() {
      LINES.clear();
      HEADER = HEADERS[CFG.oR.nextInt(HEADERS.length)];
      DATELINE = Game_Calendar.getDate_ByTurnID(Game_Calendar.TURN_ID);
      PIC = PICS[CFG.oR.nextInt(PICS.length)];

      FRESH_WARS_COUNT = 0;
      int tFreshWars = 0;

      try {
         for (int w = 0; w < CFG.game.getWarsSize(); w++) {
            War_GameData tWar = CFG.game.getWar(w);
            if (tWar.getAggressorsSize() > 0 && tWar.getDefendersSize() > 0) {
               String tKey = tWar.getAggressorID(0).getCivID() + "->" + tWar.getDefenderID(0).getCivID() + "#" + tWar.getWarTurnID();
               if (!ANNOUNCED_WARS.contains(tKey)) {
                  String tAgg = CFG.game.getCiv(tWar.getAggressorID(0).getCivID()).getCivName();
                  String tDef = CFG.game.getCiv(tWar.getDefenderID(0).getCivID()).getCivName();
                  LINES.add("⚔ ВОЙНА: " + tAgg + " ➤ " + tDef);
                  ANNOUNCED_WARS.add(tKey);
                  tFreshWars++;
                  if (LINES.size() >= 7) break;
               }
            }
         }
      } catch (IndexOutOfBoundsException ignored) {
      }

      FRESH_WARS_COUNT = tFreshWars;

      if (tFreshWars == 0) {
         LINES.add(PEACE_LINES[CFG.oR.nextInt(PEACE_LINES.length)]);
      }

      String[] tClosing = new String[]{
         "Ну и что дальше?",
         "Это будет интересно…",
         "Кто кого?",
         "Это было непредсказуемо!",
         "Продолжение следует…",
         "Мир никогда не будет прежним",
         "История пишется победителями",
         "Запомните этот день",
         "Поживём — увидим",
         "Такова цена амбиций",
         "Часы истории тикают",
         "Ничто не предвещало… а потом",
         "Дипломаты в панике",
         "Армии не ждут",
         "Народы затаили дыхание",
         "Великие державы нервничают",
         "Слышен далёкий гром войны",
         "Карты перетасованы",
         "Ставки сделаны",
         "Ход за вами",
         "Летопись пополняется",
         "Будущее туманно",
         "Грядут великие перемены",
         "Никто не хотел войны. Но…",
         "Слово за генералами",
         "Экономисты бьют тревогу",
         "Тень падает на континент",
         "Затишье перед бурей",
         "Ответ будет жёстким",
         "Следите за развитием событий"
      };
      LINES.add(tClosing[CFG.oR.nextInt(tClosing.length)]);
      LINES.add(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCivName() + ": ход " + Game_Calendar.TURN_ID);

      try {
         File tDir = new File("UI/events");
         ArrayList<String> tImgs = new ArrayList<>();

         for (File f : tDir.listFiles()) {
            String n = f.getName().toLowerCase();
            if (n.endsWith(".png") || n.endsWith(".jpg")) {
               tImgs.add(f.getName());
            }
         }

         if (!tImgs.isEmpty()) {
            PIC_NAME = tImgs.get(CFG.oR.nextInt(tImgs.size()));
         }
      } catch (Exception ignored) {
      }
   }

   public static int registerRuntimeEvent(int nPlayerCivID) throws Exception {
      Event_GameData e = new Event_GameData();
      e.setEventName(HEADER);
      e.setEventDate_Since(
         Game_Calendar.currentDay, Game_Calendar.currentMonth, Game_Calendar.currentYear
      );
      e.getEvent_PopUp().showPopUp = true;
      StringBuilder sb = new StringBuilder();

      for (String ln : LINES) {
         sb.append(ln).append("\n");
      }

      e.getEvent_PopUp().sText = sb.toString();
      e.setEventPicture(PIC_NAME);

      Event_Decision d = new Event_Decision();
      d.sTitle = new String[]{
         "Далее",
         "Понятно",
         "История продолжается…",
         "Таково время",
         "Читать окончено"
      }[CFG.oR.nextInt(5)];
      d.iAIChance = 100;
      e.lDecisions.add(d);

      CFG.eventsManager.eventsGD.lEvents.add(e);
      CFG.eventsManager.eventsGD.iEventsSize = CFG.eventsManager.eventsGD.lEvents.size();
      int tID = CFG.eventsManager.eventsGD.lEvents.size() - 1;
      e.setWasFired(true);
      return tID;
   }
}
