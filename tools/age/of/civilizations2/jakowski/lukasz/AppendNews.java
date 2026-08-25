package age.of.civilizations2.jakowski.lukasz;

import java.io.*;
import java.lang.reflect.Field;
import sun.misc.Unsafe;
import java.util.ArrayList;
import java.nio.file.*;

public class AppendNews {
   public static void main(String[] args) throws Exception {
      String path = args[0];
      byte[] raw = Files.readAllBytes(Paths.get(path));
      Events_GameData egd;

      try (ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(raw))) {
         egd = (Events_GameData)in.readUnshared();
      }

      String[][] news = new String[][]{
         new String[]{
            "Газета: Хроника эпохи", "7", "3", "1936",
            "Специальный выпуск газеты «Хроника эпохи».\nМировые державы наращивают армии, дипломаты заключают пакты,\nа над Европой сгущаются тучи."
         },
         new String[]{
            "Газета: Вестник войны", "1", "9", "1939",
            "Экстренный выпуск!\nГермания начала военные действия против Польши.\nПодробности — в следующих номерах."
         },
         new String[]{
            "Газета: Время перемен", "22", "6", "1941",
            "Самый мрачный выпуск за всю историю печати:\nвойна пришла на Восток. Народы готовятся к долгой борьбе."
         }
      };

      Field fU = Unsafe.class.getDeclaredField("theUnsafe");
      fU.setAccessible(true);
      Unsafe tUnsafe = (Unsafe)fU.get(null);
      Field tWorld = Event_GameData.class.getDeclaredField("worldEvent");
      tWorld.setAccessible(true);

      int tBefore = egd.lEvents.size();

      for (String[] n : news) {
         Event_GameData e = (Event_GameData)tUnsafe.allocateInstance(Event_GameData.class);
         e.sEventTag = "gazeta_" + n[0].hashCode() + "_" + System.nanoTime();
         e.sEventName = n[0];
         e.sEventPicture = "";
         e.fEventPictureWidth = 512;
         e.fEventPictureHeight = 96;
         e.eventDate_Since = new Event_Date();
         e.eventDate_Since.iEventDay = Integer.parseInt(n[1]);
         e.eventDate_Since.iEventMonth = Integer.parseInt(n[2]);
         e.eventDate_Since.iEventYear = Integer.parseInt(n[3]);
         e.eventDate_Until = new Event_Date();
         e.eventDate_Until.iEventDay = 31;
         e.eventDate_Until.iEventMonth = 12;
         e.eventDate_Until.iEventYear = 9999;
         e.lTriggers = new ArrayList<>();
         e.lDecisions = new ArrayList<>();
         e.event_PopUp = new Event_PopUp();
         e.event_PopUp.showPopUp = true;
         e.event_PopUp.sText = n[4];

         Event_Decision d = new Event_Decision();
         d.sTitle = "Далее";
         d.iAIChance = 100;
         e.lDecisions.add(d);

         tWorld.setBoolean(e, true);

         egd.lEvents.add(e);
      }

      egd.iEventsSize = egd.lEvents.size();

      try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(path))) {
         out.writeObject(egd);
      }

      System.out.println("APPENDED: " + (egd.lEvents.size() - tBefore) + ", total=" + egd.iEventsSize);
   }
}
