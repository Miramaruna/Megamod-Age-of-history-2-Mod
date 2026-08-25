package age.of.civilizations2.jakowski.lukasz;

import java.io.*;
import java.lang.reflect.Field;
import java.util.*;
import sun.misc.Unsafe;

public class MakeEvents1936 {
   static Unsafe UNSAFE;

   static {
      try {
         Field f = Unsafe.class.getDeclaredField("theUnsafe");
         f.setAccessible(true);
         UNSAFE = (Unsafe)f.get(null);
      } catch (Exception var1) {
         throw new RuntimeException(var1);
      }
   }

   static HashMap<String,Integer> byTag = new HashMap<>();
   static HashMap<Integer,List<Integer>> provsByCiv = new HashMap<>();

   static int civ(String tag) {
      Integer id = byTag.get(tag);
      return id == null ? -1 : id;
   }

   static List<Integer> provsOf(int civID) {
      List<Integer> out = provsByCiv.get(civID);
      return out == null ? new ArrayList<>() : out;
   }

   static Event_Outcome_DeclareWar war(int a, int b) {
      Event_Outcome_DeclareWar o = new Event_Outcome_DeclareWar();
      o.iCivID = a; o.iCivID2 = b;
      return o;
   }

   static Event_Outcome_IncreaseRelation rel(int a, int b, int v) {
      Event_Outcome_IncreaseRelation o = new Event_Outcome_IncreaseRelation();
      o.iCivID = a; o.iCivID2 = b; o.iValue = v;
      return o;
   }

   static Event_Outcome_DecreaseRelation decRel(int a, int b, int v) {
      Event_Outcome_DecreaseRelation o = new Event_Outcome_DecreaseRelation();
      o.iCivID = a; o.iCivID2 = b; o.iValue = v;
      return o;
   }

   static Event_Outcome_NonAggression nAgg(int a, int b, int months) {
      Event_Outcome_NonAggression o = new Event_Outcome_NonAggression();
      o.iCivID = a; o.iCivID2 = b; o.iValue = months;
      return o;
   }

   static Event_Outcome_ChangeOwner chgOwner(int from, int to, List<Integer> provs) {
      Event_Outcome_ChangeOwner o = new Event_Outcome_ChangeOwner();
      o.iCivID = from; o.iCivID_ControlledBy = to; o.lProvinces.addAll(provs);
      return o;
   }

   static Event_Outcome_RemoveCore rmCore(int civ, List<Integer> provs) {
      Event_Outcome_RemoveCore o = new Event_Outcome_RemoveCore();
      o.iCivID = civ; o.lProvinces.addAll(provs);
      return o;
   }

   static Event_Outcome_AddArmy addArmy(int civ, List<Integer> provs, int v) {
      Event_Outcome_AddArmy o = new Event_Outcome_AddArmy();
      o.iCivID = civ; o.lProvinces.addAll(provs); o.iValue = v;
      return o;
   }

   static Event_GameData evExec(String name, int d, int m, int y, int recipient, List<Event_Outcome> outs)
      throws Exception {
      Event_GameData e = raw(name, d, m, y);
      e.setCivID(recipient > 0 ? recipient : -1);
      e.lDecisions.add(dec("[EXEC] " + name, outs));
      return e;
   }

   static Event_GameData evNews(String name, int d, int m, int y, String text) throws Exception {
      Event_GameData e = raw(name + " (новость)", d, m, y);
      e.event_PopUp.showPopUp = true;
      e.event_PopUp.sText = text;
      e.lDecisions.add(dec("Далее", new ArrayList<>()));
      return e;
   }

   private static Event_GameData raw(String name, int d, int m, int y) throws Exception {
      Event_GameData e = (Event_GameData)UNSAFE.allocateInstance(Event_GameData.class);
      e.sEventTag = "hist_" + Integer.toHexString(name.hashCode()) + "_" + System.nanoTime();
      e.sEventName = name;
      e.sEventPicture = "";
      e.fEventPictureWidth = 512;
      e.fEventPictureHeight = 96;
      e.eventDate_Since = new Event_Date();
      e.eventDate_Since.iEventDay = d;
      e.eventDate_Since.iEventMonth = m;
      e.eventDate_Since.iEventYear = y;
      e.eventDate_Until = new Event_Date();
      e.eventDate_Until.iEventDay = 31;
      e.eventDate_Until.iEventMonth = 12;
      e.eventDate_Until.iEventYear = 9999;
      e.lTriggers = new ArrayList<>();
      e.lDecisions = new ArrayList<>();
      e.event_PopUp = new Event_PopUp();

      try {
         Field tWorld = Event_GameData.class.getDeclaredField("worldEvent");
         tWorld.setAccessible(true);
         tWorld.setBoolean(e, true);
      } catch (Exception ignored) {
      }

      return e;
   }

   static Event_Decision dec(String title, List<Event_Outcome> outs) {
      Event_Decision d = new Event_Decision();
      d.sTitle = title;
      for (Event_Outcome o : outs) {
         if (o != null) d.lOutcomes.add(o);
      }

      if (!d.sTitle.equals("") || !d.lOutcomes.isEmpty()) {
         return d;
      }

      d.sTitle = "…";
      return d;
   }

   public static void main(String[] args) throws Exception {
      String dir = args[0];

      Scenario_GameData scen;

      try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(dir + "/1936"))) {
         scen = (Scenario_GameData) in.readObject();
      }

      List<Integer> owners;

      try (ObjectInputStream in2 = new ObjectInputStream(new FileInputStream(dir + "/1936_PD"))) {
         Scenario_GameData_Province2 pd = (Scenario_GameData_Province2) in2.readObject();
         owners = pd.getProvinceOwners();
      }

      String[] tagsArr = scen.lCivsTags.toArray(new String[0]);

      for (int i = 0; i < tagsArr.length; i++) {
         byTag.put(tagsArr[i], i + 1);
      }

      for (int p = 0; p < owners.size(); p++) {
         Integer owner = owners.get(p);
         if (owner != null && owner > 0) {
            provsByCiv.computeIfAbsent(owner, k -> new ArrayList<>()).add(p);
         }
      }

      int GER = civ("ger_n");
      int AUT = civ("atr_\u2079");
      int POL = civ("pol3_5");
      int USSR = civ("rus_c");
      int FRA = civ("fra_d");
      int ENG = civ("uni_\u00df");
      int JAP = civ("jap_f");
      int ITA = civ("ita_f");
      int FIN = civ("fin_1");
      int CZE = civ("czsl_w");
      int USA = civ("usa_d");
      int SPA = civ("spa_w");
      int BEL = civ("bel_\u00df");

      System.out.println("MAP: GER=" + GER + " AUT=" + AUT + " POL=" + POL + " USSR=" + USSR
         + " FRA=" + FRA + " ENG=" + ENG + " JAP=" + JAP + " ITA=" + ITA
         + " FIN=" + FIN + " CZE=" + CZE + " USA=" + USA + " SPA=" + SPA + " BEL=" + BEL);

      Events_GameData egd = new Events_GameData();

      // ===== 1. Ремилитаризация Рейнской области =====
      egd.lEvents.add(evNews("Ремилитаризация Рейнской области", 7, 3, 1936,
         "7 марта 1936 года германские войска вошли в демилитаризованную Рейнскую зону. Версальская система дала трещину."));
      if (GER > 0 && FRA > 0) {
         egd.lEvents.add(evExec("Ремилитаризация [GER]", 7, 3, 1936, GER,
            Arrays.asList(decRel(GER, FRA, 25), BEL > 0 ? decRel(GER, BEL, 25) : null)));
      }

      // ===== 2. Мятеж в Испании =====
      egd.lEvents.add(evNews("Мятеж в Испании", 18, 7, 1936,
         "18 июля 1936 года в Испании вспыхнул военный мятеж. Страна раскалывается — начинается гражданская война."));
      if (SPA > 0) {
         egd.lEvents.add(evExec("Мятеж в Испании [SPA]", 18, 7, 1936, SPA,
            Collections.singletonList(addArmy(SPA, provsOf(SPA), 30000))));
      }

      // ===== 3. Ось Берлин — Рим =====
      egd.lEvents.add(evNews("Ось Берлин — Рим", 25, 10, 1936,
         "25 октября 1936 года Германия и Италия заключили соглашение о сотрудничестве. Складывается «ось Берлин — Рим»."));
      if (GER > 0 && ITA > 0) {
         egd.lEvents.add(evExec("Ось Берлин — Рим [GER]", 25, 10, 1936, GER,
            Arrays.asList(nAgg(GER, ITA, 120), rel(GER, ITA, 30))));
      }

      // ===== 4. Антикоминтерновский пакт =====
      egd.lEvents.add(evNews("Антикоминтерновский пакт", 6, 11, 1937,
         "6 ноября 1937 года Германия и Япония подписали Антикоминтерновский пакт, направленный против коммунизма."));
      if (GER > 0 && JAP > 0) {
         egd.lEvents.add(evExec("Антикоминтерновский пакт [GER]", 6, 11, 1937, GER,
            Arrays.asList(nAgg(GER, JAP, 120), rel(GER, JAP, 25),
               ITA > 0 ? rel(ITA, JAP, 20) : null)));
      }

      // ===== 5. Аншлюс Австрии =====
      egd.lEvents.add(evNews("Аншлюс Австрии", 12, 3, 1938,
         "12 марта 1938 года вермахт вступил на территорию Австрии. Объявлен аншлюс — воссоединение германоязычных земель под флагом Рейха."));
      if (GER > 0 && AUT > 0 && !provsOf(AUT).isEmpty()) {
         egd.lEvents.add(evExec("Аншлюс [GER]", 12, 3, 1938, GER,
            Arrays.asList(chgOwner(AUT, GER, provsOf(AUT)), rmCore(AUT, provsOf(AUT)))));
      }

      // ===== 6. Мюнхенский сговор =====
      egd.lEvents.add(evNews("Мюнхенский сговор", 30, 9, 1938,
         "30 сентября 1938 года по Мюнхенскому соглашению Германия получила Судетскую область. Чехословакия брошена союзниками."));
      if (GER > 0 && CZE > 0 && !provsOf(CZE).isEmpty()) {
         ArrayList<Integer> sudeten = new ArrayList<>();
         List<Integer> czeProvs = provsOf(CZE);

         for (int i = 0; i < czeProvs.size(); i += 2) {
            sudeten.add(czeProvs.get(i));
         }

         egd.lEvents.add(evExec("Мюнхен [GER]", 30, 9, 1938, GER,
            Arrays.asList(chgOwner(CZE, GER, sudeten),
               ENG > 0 ? decRel(CZE, ENG, 30) : null,
               FRA > 0 ? decRel(CZE, FRA, 30) : null)));
      }

      // ===== 7. Пакт Молотова — Риббентропа =====
      egd.lEvents.add(evNews("Пакт Молотова — Риббентропа", 23, 8, 1939,
         "23 августа 1939 года СССР и Германия подписали договор о ненападении с секретным протоколом о разделе сфер влияния в Восточной Европе."));
      if (GER > 0 && USSR > 0) {
         egd.lEvents.add(evExec("Пакт М-Р [SOV-GER]", 23, 8, 1939, USSR,
            Arrays.asList(nAgg(GER, USSR, 60), rel(GER, USSR, 40), rel(USSR, GER, 40))));
      }

      // ===== 8. Вторжение в Польшу =====
      egd.lEvents.add(evNews("Вторжение в Польшу", 1, 9, 1939,
         "1 сентября 1939 года в 4:45 германский линкор «Шлезвиг-Гольштейн» открыл огонь по Вестерплятте. Началась Вторая мировая война."));
      if (GER > 0 && POL > 0) {
         egd.lEvents.add(evExec("Вторжение в Польшу [GER]", 1, 9, 1939, GER,
            Collections.singletonList(war(GER, POL))));
      }

      // ===== 9. Британия и Франция объявляют войну =====
      egd.lEvents.add(evNews("Британия и Франция объявляют войну", 3, 9, 1939,
         "3 сентября 1939 года Великобритания и Франция выполнили свои гарантии Польше и объявили войну Германии. Европа в огне."));
      if (ENG > 0 && GER > 0 && FRA > 0) {
         egd.lEvents.add(evExec("Вторая мировая [ENG-FRA]", 3, 9, 1939, ENG,
            Arrays.asList(war(ENG, GER), war(FRA, GER))));
      }

      // ===== 10. Освободительный поход Красной армии =====
      egd.lEvents.add(evNews("Освободительный поход Красной армии", 17, 9, 1939,
         "17 сентября 1939 года части Красной армии перешли восточную границу Польши. Польское государство прекратило существование."));
      if (USSR > 0 && POL > 0) {
         egd.lEvents.add(evExec("Поход в Польшу [SOV]", 17, 9, 1939, USSR,
            Collections.singletonList(war(USSR, POL))));
      }

      // ===== 11. Зимняя война =====
      egd.lEvents.add(evNews("Зимняя война", 30, 11, 1939,
         "30 ноября 1939 года советские войска пересекли финскую границу. Началась Зимняя война."));
      if (USSR > 0 && FIN > 0) {
         egd.lEvents.add(evExec("Зимняя война [SOV]", 30, 11, 1939, USSR,
            Collections.singletonList(war(USSR, FIN))));
      }

      // ===== 12. План Барбаросса =====
      egd.lEvents.add(evNews("План «Барбаросса»", 22, 6, 1941,
         "22 июня 1941 года, в 4 часа утра, без объявления войны Германия и её союзники вторглись в СССР. Началась Великая Отечественная война."));
      if (GER > 0 && USSR > 0) {
         egd.lEvents.add(evExec("Барбаросса [GER]", 22, 6, 1941, GER,
            Arrays.asList(war(GER, USSR), ITA > 0 ? war(ITA, USSR) : null)));
      }

      // ===== 13. Нападение на Пёрл-Харбор =====
      egd.lEvents.add(evNews("Нападение на Пёрл-Харбор", 7, 12, 1941,
         "7 декабря 1941 года японская авиация атаковала американскую базу Пёрл-Харбор. В войну вступают Соединённые Штаты."));
      if (JAP > 0 && USA > 0) {
         egd.lEvents.add(evExec("Пёрл-Харбор [JAP]", 7, 12, 1941, JAP,
            Collections.singletonList(war(JAP, USA))));
      }

      for (Event_GameData e : egd.lEvents) {
         e.checkDecisions();
      }

      egd.iEventsSize = egd.lEvents.size();

      File outDir = new File(dir + "/events");
      outDir.mkdirs();

      try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(outDir.getPath() + "/1936_E"))) {
         out.writeObject(egd);
      }

      System.out.println("EVENTS WRITTEN: " + egd.lEvents.size() + " (iEventsSize=" + egd.iEventsSize + ")");
   }
}
