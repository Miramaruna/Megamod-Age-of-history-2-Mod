package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Commands {
   public static final int CONSOLE_LIMIT = 650;
   public static List<String> sConsole = new ArrayList<>();
   public static List<Point_XY> lShit = new ArrayList<>();
   public static long lShitTime = 0L;

   Commands() {
   }

   public static final void addMessage(String nMess) {
      sConsole.add(nMess);
      if (sConsole.size() > 650) {
         sConsole.remove(0);
      }
   }

   public static final void execute(String nCommand) {
      if (nCommand.length() != 0) {
         addMessage("");
         addMessage("#" + nCommand);
         String[] tempCommand = nCommand.toLowerCase().split(" ");

         try {
            label1325:
            if (tempCommand.length > 0) {
               if (!tempCommand[0].equals("console") && !tempCommand[0].equals("консось")) {
                  if (!tempCommand[0].equals("info") && !tempCommand[0].equals("инфо")) {
                     if (!tempCommand[0].equals("debug") && !tempCommand[0].equals("откладка")) {
                        if (!tempCommand[0].equals("neutral") && !tempCommand[0].equals("нейтрал")) {
                           if (!tempCommand[0].equals("center") && !tempCommand[0].equals("центер")) {
                              if (!tempCommand[0].equals("centerciv") && !tempCommand[0].equals("центрцив")) {
                                 if (!tempCommand[0].equals("scale") && !tempCommand[0].equals("размер")) {
                                    if (!tempCommand[0].equals("close")
                                       && !tempCommand[0].equals("bye")
                                       && !tempCommand[0].equals("пака")
                                       && !tempCommand[0].equals("пока")
                                       && !tempCommand[0].equals("бай")
                                       && !tempCommand[0].equals("пакеда")) {
                                       if (!tempCommand[0].equals("fps") && !tempCommand[0].equals("фпс")) {
                                          if (!tempCommand[0].equals("hi") && !tempCommand[0].equals("hello")) {
                                             if (!tempCommand[0].equals("прив")
                                                && !tempCommand[0].equals("привет")
                                                && !tempCommand[0].equals("читы")
                                                && !tempCommand[0].equals("ку")) {
                                                if (!tempCommand[0].equals("spin")
                                                   && !tempCommand[0].equals("iss")
                                                   && !tempCommand[0].equals("wheee")
                                                   && !tempCommand[0].equals("вуии")
                                                   && !tempCommand[0].equals("крутилка")
                                                   && !tempCommand[0].equals("спин")
                                                   && !tempCommand[0].equals("зачо")
                                                   && !tempCommand[0].equals("крути")
                                                   && !tempCommand[0].equals("крут")) {
                                                   if (!tempCommand[0].equals("help") && !tempCommand[0].equals("commands") && !tempCommand[0].equals("помощь")
                                                      )
                                                    {
                                                      if (!tempCommand[0].equals("party")
                                                         && !tempCommand[0].equals("fuck")
                                                         && !tempCommand[0].equals("fuk")
                                                         && !tempCommand[0].equals("flags")
                                                         && !tempCommand[0].equals("блядь")
                                                         && !tempCommand[0].equals("флаги")
                                                         && !tempCommand[0].equals("пздц")) {
                                                         if (!CFG.menuManager.getVisible_InGame_FlagAction_Console()) {
                                                            break label1325;
                                                         }

                                                         if (!tempCommand[0].equals("clear")
                                                            && !tempCommand[0].equals("чистка")
                                                            && !tempCommand[0].equals("очистить")) {
                                                            if (!tempCommand[0].equals("Drew Durnil")
                                                               && !tempCommand[0].equals("drew durnil")
                                                               && !tempCommand[0].equals("drewdurnil")
                                                               && !tempCommand[0].equals("drew")
                                                               && !tempCommand[0].equals("Drew")
                                                               && !tempCommand[0].equals("Durnil")
                                                               && !tempCommand[0].equals("durnil")
                                                               && !tempCommand[0].equals("observe")
                                                               && !tempCommand[0].equals("noob")
                                                               && !tempCommand[0].equals("Spectator")
                                                               && !tempCommand[0].equals("spectator")
                                                               && !tempCommand[0].equals("наблюдатель")
                                                               && !tempCommand[0].equals("смотреть")
                                                               && !tempCommand[0].equals("историк")) {
                                                               if (!tempCommand[0].equals("civs")
                                                                  && !tempCommand[0].equals("tags")
                                                                  && !tempCommand[0].equals("цивы")
                                                                  && !tempCommand[0].equals("теги")) {
                                                                  if (!tempCommand[0].equals("civ") && !tempCommand[0].equals("цив")) {
                                                                     if (!tempCommand[0].equals("province") && !tempCommand[0].equals("провинция")) {
                                                                        if (!tempCommand[0].equals("showids") && !tempCommand[0].equals("показать")) {
                                                                           if (!tempCommand[0].equals("showarmy") && !tempCommand[0].equals("показатьармию")) {
                                                                              if ((CFG.SPECTATOR_MODE || !tempCommand[0].equals("addplayer"))
                                                                                 && !tempCommand[0].equals("добавитьигрока")
                                                                                 && !tempCommand[0].equals("игрок")
                                                                                 && !tempCommand[0].equals("бот")) {
                                                                                 if (!tempCommand[0].equals("addciv") && !tempCommand[0].equals("добавитьцив")) {
                                                                                    if (!tempCommand[0].equals("technology") && !tempCommand[0].equals("техн")) {
                                                                                       if (!tempCommand[0].equals("pop") && !tempCommand[0].equals("поп")) {
                                                                                          if (!tempCommand[0].equals("civarmy")
                                                                                             && !tempCommand[0].equals("цивармия")) {
                                                                                             if (!tempCommand[0].equals("morearmy")
                                                                                                && !tempCommand[0].equals("ещёармия")) {
                                                                                                if (tempCommand[0].equals("delarmy")
                                                                                                   || tempCommand[0].equals("удалитьармию")
                                                                                                   || tempCommand[0].equals("убитвсех")
                                                                                                   || tempCommand[0].equals("минусармия")
                                                                                                   || tempCommand[0].equals("ойармияпропала")) {
                                                                                                   if (CFG.game
                                                                                                         .getCiv(
                                                                                                            CFG.game
                                                                                                               .getProvince(CFG.game.getActiveProvinceID())
                                                                                                               .getCivID()
                                                                                                         )
                                                                                                         .getNumOfProvinces()
                                                                                                      > 0) {
                                                                                                      for (int i = 0;
                                                                                                         i
                                                                                                            < CFG.game
                                                                                                               .getCiv(
                                                                                                                  CFG.game
                                                                                                                     .getProvince(
                                                                                                                        CFG.game.getActiveProvinceID()
                                                                                                                     )
                                                                                                                     .getCivID()
                                                                                                               )
                                                                                                               .getNumOfProvinces();
                                                                                                         i++
                                                                                                      ) {
                                                                                                         CFG.game
                                                                                                            .getProvince(
                                                                                                               CFG.game
                                                                                                                  .getCiv(
                                                                                                                     CFG.game
                                                                                                                        .getProvince(
                                                                                                                           CFG.game.getActiveProvinceID()
                                                                                                                        )
                                                                                                                        .getCivID()
                                                                                                                  )
                                                                                                                  .getProvinceID(i)
                                                                                                            )
                                                                                                            .updateArmy(0);
                                                                                                      }

                                                                                                      addMessage(cheatMess() + "Army deleted");
                                                                                                      return;
                                                                                                   }

                                                                                                   IllegalCommand();
                                                                                                }

                                                                                                if (!tempCommand[0].equals("buildciv")
                                                                                                   && !tempCommand[0].equals("построитьцив")) {
                                                                                                   if (!tempCommand[0].equals("build")
                                                                                                      && !tempCommand[0].equals("построить")) {
                                                                                                      if (!tempCommand[0].equals("annexall")
                                                                                                         && !tempCommand[0].equals("анексалл")
                                                                                                         && !tempCommand[0].equals("анексвсе")
                                                                                                         && !tempCommand[0].equals("анексвсё")
                                                                                                         && !tempCommand[0].equals("аннексвсе")
                                                                                                         && !tempCommand[0].equals("аннексвсё")
                                                                                                         && !tempCommand[0].equals("аннексалл")
                                                                                                         && !tempCommand[0].equals("анексолл")
                                                                                                         && !tempCommand[0].equals("аннексолл")) {
                                                                                                         if (!tempCommand[0].equals("popciv")
                                                                                                            && !tempCommand[0].equals("попуцив")) {
                                                                                                            if (!tempCommand[0].equals("ideology")
                                                                                                               && !tempCommand[0].equals("правительство")) {
                                                                                                               if (!tempCommand[0].equals("ideologyid")
                                                                                                                  && !tempCommand[0].equals("правительствокод")
                                                                                                                  )
                                                                                                                {
                                                                                                                  if (!tempCommand[0].equals("flag")
                                                                                                                     && !tempCommand[0].equals("флаг")) {
                                                                                                                     if (!tempCommand[0].equals("delciv")
                                                                                                                        && !tempCommand[0].equals("удалитьцив")
                                                                                                                        && !tempCommand[0]
                                                                                                                           .equals("удалитьстрану")) {
                                                                                                                        if ((
                                                                                                                              tempCommand[0].equals("annex")
                                                                                                                                 || tempCommand[0]
                                                                                                                                    .equals("аннекс")
                                                                                                                                 || tempCommand[0]
                                                                                                                                    .equals("анекс")
                                                                                                                           )
                                                                                                                           && CFG.game.getActiveProvinceID()
                                                                                                                              >= 0) {
                                                                                                                           int numOfProvs = CFG.game
                                                                                                                              .getCiv(
                                                                                                                                 CFG.game
                                                                                                                                    .getProvince(
                                                                                                                                       CFG.game
                                                                                                                                          .getActiveProvinceID()
                                                                                                                                    )
                                                                                                                                    .getCivID()
                                                                                                                              )
                                                                                                                              .getNumOfProvinces();
                                                                                                                           int[] provsID = new int[numOfProvs];

                                                                                                                           for (int k = 0; k < numOfProvs; k++) {
                                                                                                                              CFG.game
                                                                                                                                 .getProvince(
                                                                                                                                    CFG.game
                                                                                                                                       .getCiv(
                                                                                                                                          CFG.game
                                                                                                                                             .getProvince(
                                                                                                                                                CFG.game
                                                                                                                                                   .getActiveProvinceID()
                                                                                                                                             )
                                                                                                                                             .getCivID()
                                                                                                                                       )
                                                                                                                                       .getProvinceID(k)
                                                                                                                                 )
                                                                                                                                 .setTrueOwnerOfProvince(
                                                                                                                                    CFG.game
                                                                                                                                       .getPlayer(
                                                                                                                                          CFG.PLAYER_TURNID
                                                                                                                                       )
                                                                                                                                       .getCivID()
                                                                                                                                 );
                                                                                                                              provsID[k] = CFG.game
                                                                                                                                 .getCiv(
                                                                                                                                    CFG.game
                                                                                                                                       .getProvince(
                                                                                                                                          CFG.game
                                                                                                                                             .getActiveProvinceID()
                                                                                                                                       )
                                                                                                                                       .getCivID()
                                                                                                                                 )
                                                                                                                                 .getProvinceID(k);
                                                                                                                           }

                                                                                                                           for (int l = 0; l < numOfProvs; l++) {
                                                                                                                              CFG.game
                                                                                                                                 .getProvince(provsID[l])
                                                                                                                                 .setCivID(
                                                                                                                                    CFG.game
                                                                                                                                       .getPlayer(
                                                                                                                                          CFG.PLAYER_TURNID
                                                                                                                                       )
                                                                                                                                       .getCivID(),
                                                                                                                                    false
                                                                                                                                 );
                                                                                                                           }

                                                                                                                           addMessage(
                                                                                                                              cheatMess() + "CountryAnnexed"
                                                                                                                           );
                                                                                                                           return;
                                                                                                                        }

                                                                                                                        if (!tempCommand[0].equals("gpt")
                                                                                                                           && !tempCommand[0]
                                                                                                                              .equals("чатджипити")
                                                                                                                           && !tempCommand[0].equals("гпт")) {
                                                                                                                           if (!tempCommand[0].equals("rev")
                                                                                                                              && !tempCommand[0].equals("рев")
                                                                                                                              && !tempCommand[0].equals("пук")
                                                                                                                              && !tempCommand[0]
                                                                                                                                 .equals("революция")) {
                                                                                                                              if (!tempCommand[0]
                                                                                                                                    .equals("immortal")
                                                                                                                                 && !tempCommand[0]
                                                                                                                                    .equals("имортал")
                                                                                                                                 && !tempCommand[0]
                                                                                                                                    .equals("качалка")
                                                                                                                                 && !tempCommand[0]
                                                                                                                                    .equals("немясо")
                                                                                                                                 && !tempCommand[0]
                                                                                                                                    .equals("качки")) {
                                                                                                                                 if (!tempCommand[0]
                                                                                                                                       .equals("annexprov")
                                                                                                                                    && !tempCommand[0]
                                                                                                                                       .equals("аннекспров")
                                                                                                                                    && !tempCommand[0]
                                                                                                                                       .equals("анекспров")) {
                                                                                                                                    if (!tempCommand[0]
                                                                                                                                          .equals("armyset")
                                                                                                                                       && !tempCommand[0]
                                                                                                                                          .equals("setarmy")
                                                                                                                                       && !tempCommand[0]
                                                                                                                                          .equals("мясо")
                                                                                                                                       && !tempCommand[0]
                                                                                                                                          .equals("войска")) {
                                                                                                                                       if (!tempCommand[0]
                                                                                                                                             .equals(
                                                                                                                                                "noliberity"
                                                                                                                                             )
                                                                                                                                          && !tempCommand[0]
                                                                                                                                             .equals(
                                                                                                                                                "нетсвобод"
                                                                                                                                             )
                                                                                                                                          && !tempCommand[0]
                                                                                                                                             .equals(
                                                                                                                                                "нетсвободы"
                                                                                                                                             )
                                                                                                                                          && !tempCommand[0]
                                                                                                                                             .equals(
                                                                                                                                                "беднивассалы"
                                                                                                                                             )) {
                                                                                                                                          if (!tempCommand[0]
                                                                                                                                                .equals("id")
                                                                                                                                             && !tempCommand[0]
                                                                                                                                                .equals("код")) {
                                                                                                                                             if (!tempCommand[0]
                                                                                                                                                   .equals(
                                                                                                                                                      "war"
                                                                                                                                                   )
                                                                                                                                                && !tempCommand[0]
                                                                                                                                                   .equals(
                                                                                                                                                      "война"
                                                                                                                                                   )
                                                                                                                                                && !tempCommand[0]
                                                                                                                                                   .equals(
                                                                                                                                                      "вар"
                                                                                                                                                   )
                                                                                                                                                && !tempCommand[0]
                                                                                                                                                   .equals(
                                                                                                                                                      "битвамачииговна"
                                                                                                                                                   )) {
                                                                                                                                                if (!tempCommand[0]
                                                                                                                                                      .equals(
                                                                                                                                                         "peace"
                                                                                                                                                      )
                                                                                                                                                   && !tempCommand[0]
                                                                                                                                                      .equals(
                                                                                                                                                         "мир"
                                                                                                                                                      )
                                                                                                                                                   && !tempCommand[0]
                                                                                                                                                      .equals(
                                                                                                                                                         "пис"
                                                                                                                                                      )
                                                                                                                                                   && !tempCommand[0]
                                                                                                                                                      .equals(
                                                                                                                                                         "да"
                                                                                                                                                      )
                                                                                                                                                   && !tempCommand[0]
                                                                                                                                                      .equals(
                                                                                                                                                         "место"
                                                                                                                                                      )) {
                                                                                                                                                   if (!tempCommand[0]
                                                                                                                                                         .equals(
                                                                                                                                                            "buildport"
                                                                                                                                                         )
                                                                                                                                                      && !tempCommand[0]
                                                                                                                                                         .equals(
                                                                                                                                                            "строитьпорт"
                                                                                                                                                         )) {
                                                                                                                                                      if (!tempCommand[0]
                                                                                                                                                            .equals(
                                                                                                                                                               "buildfort"
                                                                                                                                                            )
                                                                                                                                                         && !tempCommand[0]
                                                                                                                                                            .equals(
                                                                                                                                                               "строитьфорт"
                                                                                                                                                            )) {
                                                                                                                                                         if (!tempCommand[0]
                                                                                                                                                               .equals(
                                                                                                                                                                  "buildtower"
                                                                                                                                                               )
                                                                                                                                                            && !tempCommand[0]
                                                                                                                                                               .equals(
                                                                                                                                                                  "строитьбашню"
                                                                                                                                                               )
                                                                                                                                                            )
                                                                                                                                                          {
                                                                                                                                                            if (!tempCommand[0]
                                                                                                                                                                  .equals(
                                                                                                                                                                     "eco"
                                                                                                                                                                  )
                                                                                                                                                               && !tempCommand[0]
                                                                                                                                                                  .equals(
                                                                                                                                                                     "эко"
                                                                                                                                                                  )
                                                                                                                                                               && !tempCommand[0]
                                                                                                                                                                  .equals(
                                                                                                                                                                     "экономия"
                                                                                                                                                                  )
                                                                                                                                                               && !tempCommand[0]
                                                                                                                                                                  .equals(
                                                                                                                                                                     "экономика"
                                                                                                                                                                  )
                                                                                                                                                               && !tempCommand[0]
                                                                                                                                                                  .equals(
                                                                                                                                                                     "чудо"
                                                                                                                                                                  )
                                                                                                                                                               )
                                                                                                                                                             {
                                                                                                                                                               if (!tempCommand[0]
                                                                                                                                                                     .equals(
                                                                                                                                                                        "ecociv"
                                                                                                                                                                     )
                                                                                                                                                                  && !tempCommand[0]
                                                                                                                                                                     .equals(
                                                                                                                                                                        "экоцив"
                                                                                                                                                                     )
                                                                                                                                                                  && !tempCommand[0]
                                                                                                                                                                     .equals(
                                                                                                                                                                        "инвестициивговно"
                                                                                                                                                                     )
                                                                                                                                                                  && !tempCommand[0]
                                                                                                                                                                     .equals(
                                                                                                                                                                        "экочудо"
                                                                                                                                                                     )
                                                                                                                                                                  && !tempCommand[0]
                                                                                                                                                                     .equals(
                                                                                                                                                                        "типабогатая"
                                                                                                                                                                     )
                                                                                                                                                                  )
                                                                                                                                                                {
                                                                                                                                                                  if (!tempCommand[0]
                                                                                                                                                                        .equals(
                                                                                                                                                                           "army"
                                                                                                                                                                        )
                                                                                                                                                                     && !tempCommand[0]
                                                                                                                                                                        .equals(
                                                                                                                                                                           "армия"
                                                                                                                                                                        )
                                                                                                                                                                     )
                                                                                                                                                                   {
                                                                                                                                                                     if (!tempCommand[0]
                                                                                                                                                                           .equals(
                                                                                                                                                                              "money"
                                                                                                                                                                           )
                                                                                                                                                                        && !tempCommand[0]
                                                                                                                                                                           .equals(
                                                                                                                                                                              "взлом"
                                                                                                                                                                           )
                                                                                                                                                                        && !tempCommand[0]
                                                                                                                                                                           .equals(
                                                                                                                                                                              "мани"
                                                                                                                                                                           )
                                                                                                                                                                        && !tempCommand[0]
                                                                                                                                                                           .equals(
                                                                                                                                                                              "монеты"
                                                                                                                                                                           )
                                                                                                                                                                        && !tempCommand[0]
                                                                                                                                                                           .equals(
                                                                                                                                                                              "дайденег"
                                                                                                                                                                           )
                                                                                                                                                                        )
                                                                                                                                                                      {
                                                                                                                                                                        if (!tempCommand[0]
                                                                                                                                                                              .equals(
                                                                                                                                                                                 "movement"
                                                                                                                                                                              )
                                                                                                                                                                           && !tempCommand[0]
                                                                                                                                                                              .equals(
                                                                                                                                                                                 "бегать"
                                                                                                                                                                              )
                                                                                                                                                                           && !tempCommand[0]
                                                                                                                                                                              .equals(
                                                                                                                                                                                 "движ"
                                                                                                                                                                              )
                                                                                                                                                                           && !tempCommand[0]
                                                                                                                                                                              .equals(
                                                                                                                                                                                 "очкидвиг"
                                                                                                                                                                              )
                                                                                                                                                                           )
                                                                                                                                                                         {
                                                                                                                                                                           if (!tempCommand[0]
                                                                                                                                                                                 .equals(
                                                                                                                                                                                    "mil"
                                                                                                                                                                                 )
                                                                                                                                                                              && !tempCommand[0]
                                                                                                                                                                                 .equals(
                                                                                                                                                                                    "военныеочки"
                                                                                                                                                                                 )
                                                                                                                                                                              && !tempCommand[0]
                                                                                                                                                                                 .equals(
                                                                                                                                                                                    "мил"
                                                                                                                                                                                 )
                                                                                                                                                                              && !tempCommand[0]
                                                                                                                                                                                 .equals(
                                                                                                                                                                                    "опыт"
                                                                                                                                                                                 )
                                                                                                                                                                              )
                                                                                                                                                                            {
                                                                                                                                                                              if (!tempCommand[0]
                                                                                                                                                                                    .equals(
                                                                                                                                                                                       "nucler"
                                                                                                                                                                                    )
                                                                                                                                                                                 && !tempCommand[0]
                                                                                                                                                                                    .equals(
                                                                                                                                                                                       "пультотядерки"
                                                                                                                                                                                    )
                                                                                                                                                                                 && !tempCommand[0]
                                                                                                                                                                                    .equals(
                                                                                                                                                                                       "пульт"
                                                                                                                                                                                    )
                                                                                                                                                                                 && !tempCommand[0]
                                                                                                                                                                                    .equals(
                                                                                                                                                                                       "ядерка"
                                                                                                                                                                                    )
                                                                                                                                                                                 && !tempCommand[0]
                                                                                                                                                                                    .equals(
                                                                                                                                                                                       "ядер"
                                                                                                                                                                                    )
                                                                                                                                                                                 )
                                                                                                                                                                               {
                                                                                                                                                                                 if (!tempCommand[0]
                                                                                                                                                                                       .equals(
                                                                                                                                                                                          "food"
                                                                                                                                                                                       )
                                                                                                                                                                                    && !tempCommand[0]
                                                                                                                                                                                       .equals(
                                                                                                                                                                                          "едааа"
                                                                                                                                                                                       )
                                                                                                                                                                                    && !tempCommand[0]
                                                                                                                                                                                       .equals(
                                                                                                                                                                                          "жрать"
                                                                                                                                                                                       )
                                                                                                                                                                                    && !tempCommand[0]
                                                                                                                                                                                       .equals(
                                                                                                                                                                                          "кака"
                                                                                                                                                                                       )
                                                                                                                                                                                    )
                                                                                                                                                                                  {
                                                                                                                                                                                    if (!tempCommand[0]
                                                                                                                                                                                          .equals(
                                                                                                                                                                                             "manpower"
                                                                                                                                                                                          )
                                                                                                                                                                                       && !tempCommand[0]
                                                                                                                                                                                          .equals(
                                                                                                                                                                                             "mans"
                                                                                                                                                                                          )
                                                                                                                                                                                       && !tempCommand[0]
                                                                                                                                                                                          .equals(
                                                                                                                                                                                             "мужыки"
                                                                                                                                                                                          )
                                                                                                                                                                                       && !tempCommand[0]
                                                                                                                                                                                          .equals(
                                                                                                                                                                                             "муж"
                                                                                                                                                                                          )
                                                                                                                                                                                       && !tempCommand[0]
                                                                                                                                                                                          .equals(
                                                                                                                                                                                             "рекруты"
                                                                                                                                                                                          )
                                                                                                                                                                                       && !tempCommand[0]
                                                                                                                                                                                          .equals(
                                                                                                                                                                                             "нужномясо"
                                                                                                                                                                                          )
                                                                                                                                                                                       )
                                                                                                                                                                                     {
                                                                                                                                                                                       if (!tempCommand[0]
                                                                                                                                                                                             .equals(
                                                                                                                                                                                                "diplomacy"
                                                                                                                                                                                             )
                                                                                                                                                                                          && !tempCommand[0]
                                                                                                                                                                                             .equals(
                                                                                                                                                                                                "дипл"
                                                                                                                                                                                             )
                                                                                                                                                                                          && !tempCommand[0]
                                                                                                                                                                                             .equals(
                                                                                                                                                                                                "дипломатия"
                                                                                                                                                                                             )
                                                                                                                                                                                          && !tempCommand[0]
                                                                                                                                                                                             .equals(
                                                                                                                                                                                                "очкидипломата"
                                                                                                                                                                                             )
                                                                                                                                                                                          )
                                                                                                                                                                                        {
                                                                                                                                                                                          if (!tempCommand[0]
                                                                                                                                                                                                .equals(
                                                                                                                                                                                                   "survival"
                                                                                                                                                                                                )
                                                                                                                                                                                             && !tempCommand[0]
                                                                                                                                                                                                .equals(
                                                                                                                                                                                                   "пиздец"
                                                                                                                                                                                                )
                                                                                                                                                                                             && !tempCommand[0]
                                                                                                                                                                                                .equals(
                                                                                                                                                                                                   "выжить"
                                                                                                                                                                                                )
                                                                                                                                                                                             && !tempCommand[0]
                                                                                                                                                                                                .equals(
                                                                                                                                                                                                   "выживание"
                                                                                                                                                                                                )
                                                                                                                                                                                             && !tempCommand[0]
                                                                                                                                                                                                .equals(
                                                                                                                                                                                                   "всемудачивсемпока"
                                                                                                                                                                                                )
                                                                                                                                                                                             )
                                                                                                                                                                                           {
                                                                                                                                                                                             if (tempCommand[0]
                                                                                                                                                                                                .equals(
                                                                                                                                                                                                   "reloadprovince"
                                                                                                                                                                                                )
                                                                                                                                                                                                )
                                                                                                                                                                                              {
                                                                                                                                                                                                try {
                                                                                                                                                                                                   int tempID = Integer.parseInt(
                                                                                                                                                                                                      tempCommand[1]
                                                                                                                                                                                                   );
                                                                                                                                                                                                   if (tempID
                                                                                                                                                                                                      < CFG.game
                                                                                                                                                                                                         .getProvincesSize()
                                                                                                                                                                                                      )
                                                                                                                                                                                                    {
                                                                                                                                                                                                      Editor_NeighboringProvinces.updateProvince(
                                                                                                                                                                                                         tempID
                                                                                                                                                                                                      );
                                                                                                                                                                                                      CFG.game
                                                                                                                                                                                                         .setActiveProvinceID(
                                                                                                                                                                                                            tempID
                                                                                                                                                                                                         );
                                                                                                                                                                                                      CFG.toast
                                                                                                                                                                                                         .setInView(
                                                                                                                                                                                                            CFG.game
                                                                                                                                                                                                               .getProvince(
                                                                                                                                                                                                                  tempID
                                                                                                                                                                                                               )
                                                                                                                                                                                                               .getName()
                                                                                                                                                                                                         );
                                                                                                                                                                                                   } else {
                                                                                                                                                                                                      IllegalCommand();
                                                                                                                                                                                                   }

                                                                                                                                                                                                   return;
                                                                                                                                                                                                } catch (IllegalArgumentException var6) {
                                                                                                                                                                                                   IllegalCommand();
                                                                                                                                                                                                } catch (IndexOutOfBoundsException var7) {
                                                                                                                                                                                                   IllegalCommand();
                                                                                                                                                                                                }

                                                                                                                                                                                                return;
                                                                                                                                                                                             }
                                                                                                                                                                                             break label1325;
                                                                                                                                                                                          }

                                                                                                                                                                                          for (int i = 0;
                                                                                                                                                                                             i
                                                                                                                                                                                                < CFG.game
                                                                                                                                                                                                   .getCivsSize();
                                                                                                                                                                                             i++
                                                                                                                                                                                          ) {
                                                                                                                                                                                             CFG.game
                                                                                                                                                                                                .declareWar(
                                                                                                                                                                                                   i,
                                                                                                                                                                                                   CFG.game
                                                                                                                                                                                                      .getProvince(
                                                                                                                                                                                                         CFG.game
                                                                                                                                                                                                            .getActiveProvinceID()
                                                                                                                                                                                                      )
                                                                                                                                                                                                      .getCivID(),
                                                                                                                                                                                                   true
                                                                                                                                                                                                );
                                                                                                                                                                                          }

                                                                                                                                                                                          addMessage(
                                                                                                                                                                                             CFG.game
                                                                                                                                                                                                   .getCiv(
                                                                                                                                                                                                      CFG.game
                                                                                                                                                                                                         .getProvince(
                                                                                                                                                                                                            CFG.game
                                                                                                                                                                                                               .getActiveProvinceID()
                                                                                                                                                                                                         )
                                                                                                                                                                                                         .getCivID()
                                                                                                                                                                                                   )
                                                                                                                                                                                                   .getCivName()
                                                                                                                                                                                                + ": пиздец.."
                                                                                                                                                                                          );
                                                                                                                                                                                          return;
                                                                                                                                                                                       }

                                                                                                                                                                                       CFG.game
                                                                                                                                                                                          .getCiv(
                                                                                                                                                                                             CFG.game
                                                                                                                                                                                                .getPlayer(
                                                                                                                                                                                                   CFG.PLAYER_TURNID
                                                                                                                                                                                                )
                                                                                                                                                                                                .getCivID()
                                                                                                                                                                                          )
                                                                                                                                                                                          .setDiplomacyPoints(
                                                                                                                                                                                             CFG.game
                                                                                                                                                                                                   .getCiv(
                                                                                                                                                                                                      CFG.game
                                                                                                                                                                                                         .getPlayer(
                                                                                                                                                                                                            CFG.PLAYER_TURNID
                                                                                                                                                                                                         )
                                                                                                                                                                                                         .getCivID()
                                                                                                                                                                                                   )
                                                                                                                                                                                                   .getDiplomacyPoints()
                                                                                                                                                                                                + CFG.ideologiesManager
                                                                                                                                                                                                      .getIdeology(
                                                                                                                                                                                                         CFG.game
                                                                                                                                                                                                            .getCiv(
                                                                                                                                                                                                               CFG.game
                                                                                                                                                                                                                  .getPlayer(
                                                                                                                                                                                                                     CFG.PLAYER_TURNID
                                                                                                                                                                                                                  )
                                                                                                                                                                                                                  .getCivID()
                                                                                                                                                                                                            )
                                                                                                                                                                                                            .getIdeologyID()
                                                                                                                                                                                                      )
                                                                                                                                                                                                      .COST_OF_MOVE
                                                                                                                                                                                                   * 3
                                                                                                                                                                                                   / 4
                                                                                                                                                                                          );
                                                                                                                                                                                       addMessage(
                                                                                                                                                                                          cheatMess()
                                                                                                                                                                                             + CFG.langManager
                                                                                                                                                                                                .get(
                                                                                                                                                                                                   "DiplomacyPoints"
                                                                                                                                                                                                )
                                                                                                                                                                                             + ": +"
                                                                                                                                                                                             + CFG.ideologiesManager
                                                                                                                                                                                                      .getIdeology(
                                                                                                                                                                                                         CFG.game
                                                                                                                                                                                                            .getCiv(
                                                                                                                                                                                                               CFG.game
                                                                                                                                                                                                                  .getPlayer(
                                                                                                                                                                                                                     CFG.PLAYER_TURNID
                                                                                                                                                                                                                  )
                                                                                                                                                                                                                  .getCivID()
                                                                                                                                                                                                            )
                                                                                                                                                                                                            .getIdeologyID()
                                                                                                                                                                                                      )
                                                                                                                                                                                                      .COST_OF_MOVE
                                                                                                                                                                                                   * 3
                                                                                                                                                                                                   / 4
                                                                                                                                                                                                / 10.0F
                                                                                                                                                                                       );
                                                                                                                                                                                       addMessage(
                                                                                                                                                                                          ""
                                                                                                                                                                                       );
                                                                                                                                                                                       CFG.toast
                                                                                                                                                                                          .setInView(
                                                                                                                                                                                             cheatMess()
                                                                                                                                                                                                + CFG.langManager
                                                                                                                                                                                                   .get(
                                                                                                                                                                                                      "diplomacy"
                                                                                                                                                                                                   )
                                                                                                                                                                                          );
                                                                                                                                                                                       CFG.menuManager
                                                                                                                                                                                          .updateInGame_TOP_All(
                                                                                                                                                                                             CFG.game
                                                                                                                                                                                                .getPlayer(
                                                                                                                                                                                                   CFG.PLAYER_TURNID
                                                                                                                                                                                                )
                                                                                                                                                                                                .getCivID()
                                                                                                                                                                                          );
                                                                                                                                                                                       return;
                                                                                                                                                                                    }

                                                                                                                                                                                    CFG.game
                                                                                                                                                                                       .getCiv(
                                                                                                                                                                                          CFG.game
                                                                                                                                                                                             .getPlayer(
                                                                                                                                                                                                CFG.PLAYER_TURNID
                                                                                                                                                                                             )
                                                                                                                                                                                             .getCivID()
                                                                                                                                                                                       )
                                                                                                                                                                                       .setManPower(
                                                                                                                                                                                          CFG.game
                                                                                                                                                                                                .getCiv(
                                                                                                                                                                                                   CFG.game
                                                                                                                                                                                                      .getPlayer(
                                                                                                                                                                                                         CFG.PLAYER_TURNID
                                                                                                                                                                                                      )
                                                                                                                                                                                                      .getCivID()
                                                                                                                                                                                                )
                                                                                                                                                                                                .getManPower()
                                                                                                                                                                                             + CFG.ideologiesManager
                                                                                                                                                                                                   .getIdeology(
                                                                                                                                                                                                      CFG.game
                                                                                                                                                                                                         .getCiv(
                                                                                                                                                                                                            CFG.game
                                                                                                                                                                                                               .getPlayer(
                                                                                                                                                                                                                  CFG.PLAYER_TURNID
                                                                                                                                                                                                               )
                                                                                                                                                                                                               .getCivID()
                                                                                                                                                                                                         )
                                                                                                                                                                                                         .getIdeologyID()
                                                                                                                                                                                                   )
                                                                                                                                                                                                   .COST_OF_MOVE
                                                                                                                                                                                                * 2709
                                                                                                                                                                                       );
                                                                                                                                                                                    addMessage(
                                                                                                                                                                                       cheatMess()
                                                                                                                                                                                          + CFG.langManager
                                                                                                                                                                                             .get(
                                                                                                                                                                                                "ManPower"
                                                                                                                                                                                             )
                                                                                                                                                                                          + ": +"
                                                                                                                                                                                          + CFG.ideologiesManager
                                                                                                                                                                                                .getIdeology(
                                                                                                                                                                                                   CFG.game
                                                                                                                                                                                                      .getCiv(
                                                                                                                                                                                                         CFG.game
                                                                                                                                                                                                            .getPlayer(
                                                                                                                                                                                                               CFG.PLAYER_TURNID
                                                                                                                                                                                                            )
                                                                                                                                                                                                            .getCivID()
                                                                                                                                                                                                      )
                                                                                                                                                                                                      .getIdeologyID()
                                                                                                                                                                                                )
                                                                                                                                                                                                .COST_OF_MOVE
                                                                                                                                                                                             * 999
                                                                                                                                                                                    );
                                                                                                                                                                                    addMessage(
                                                                                                                                                                                       ""
                                                                                                                                                                                    );
                                                                                                                                                                                    CFG.toast
                                                                                                                                                                                       .setInView(
                                                                                                                                                                                          cheatMess()
                                                                                                                                                                                             + CFG.langManager
                                                                                                                                                                                                .get(
                                                                                                                                                                                                   "ManPower"
                                                                                                                                                                                                )
                                                                                                                                                                                       );
                                                                                                                                                                                    CFG.menuManager
                                                                                                                                                                                       .updateInGame_TOP_All(
                                                                                                                                                                                          CFG.game
                                                                                                                                                                                             .getPlayer(
                                                                                                                                                                                                CFG.PLAYER_TURNID
                                                                                                                                                                                             )
                                                                                                                                                                                             .getCivID()
                                                                                                                                                                                       );
                                                                                                                                                                                    return;
                                                                                                                                                                                 }

                                                                                                                                                                                 CFG.game
                                                                                                                                                                                    .getCiv(
                                                                                                                                                                                       CFG.game
                                                                                                                                                                                          .getPlayer(
                                                                                                                                                                                             CFG.PLAYER_TURNID
                                                                                                                                                                                          )
                                                                                                                                                                                          .getCivID()
                                                                                                                                                                                    )
                                                                                                                                                                                    .setFood(
                                                                                                                                                                                       CFG.game
                                                                                                                                                                                             .getCiv(
                                                                                                                                                                                                CFG.game
                                                                                                                                                                                                   .getPlayer(
                                                                                                                                                                                                      CFG.PLAYER_TURNID
                                                                                                                                                                                                   )
                                                                                                                                                                                                   .getCivID()
                                                                                                                                                                                             )
                                                                                                                                                                                             .getFood()
                                                                                                                                                                                          - CFG.ideologiesManager
                                                                                                                                                                                                .getIdeology(
                                                                                                                                                                                                   CFG.game
                                                                                                                                                                                                      .getCiv(
                                                                                                                                                                                                         CFG.game
                                                                                                                                                                                                            .getPlayer(
                                                                                                                                                                                                               CFG.PLAYER_TURNID
                                                                                                                                                                                                            )
                                                                                                                                                                                                            .getCivID()
                                                                                                                                                                                                      )
                                                                                                                                                                                                      .getIdeologyID()
                                                                                                                                                                                                )
                                                                                                                                                                                                .COST_OF_MOVE
                                                                                                                                                                                             * 1049
                                                                                                                                                                                    );
                                                                                                                                                                                 addMessage(
                                                                                                                                                                                    cheatMess()
                                                                                                                                                                                       + CFG.langManager
                                                                                                                                                                                          .get(
                                                                                                                                                                                             "FOOD"
                                                                                                                                                                                          )
                                                                                                                                                                                       + ": +"
                                                                                                                                                                                       + CFG.ideologiesManager
                                                                                                                                                                                             .getIdeology(
                                                                                                                                                                                                CFG.game
                                                                                                                                                                                                   .getCiv(
                                                                                                                                                                                                      CFG.game
                                                                                                                                                                                                         .getPlayer(
                                                                                                                                                                                                            CFG.PLAYER_TURNID
                                                                                                                                                                                                         )
                                                                                                                                                                                                         .getCivID()
                                                                                                                                                                                                   )
                                                                                                                                                                                                   .getIdeologyID()
                                                                                                                                                                                             )
                                                                                                                                                                                             .COST_OF_MOVE
                                                                                                                                                                                          * 999
                                                                                                                                                                                 );
                                                                                                                                                                                 addMessage(
                                                                                                                                                                                    ""
                                                                                                                                                                                 );
                                                                                                                                                                                 CFG.toast
                                                                                                                                                                                    .setInView(
                                                                                                                                                                                       cheatMess()
                                                                                                                                                                                          + CFG.langManager
                                                                                                                                                                                             .get(
                                                                                                                                                                                                "FOOD"
                                                                                                                                                                                             )
                                                                                                                                                                                    );
                                                                                                                                                                                 CFG.menuManager
                                                                                                                                                                                    .updateInGame_TOP_All(
                                                                                                                                                                                       CFG.game
                                                                                                                                                                                          .getPlayer(
                                                                                                                                                                                             CFG.PLAYER_TURNID
                                                                                                                                                                                          )
                                                                                                                                                                                          .getCivID()
                                                                                                                                                                                    );
                                                                                                                                                                                 return;
                                                                                                                                                                              }

                                                                                                                                                                              CFG.game
                                                                                                                                                                                 .getCiv(
                                                                                                                                                                                    CFG.game
                                                                                                                                                                                       .getPlayer(
                                                                                                                                                                                          CFG.PLAYER_TURNID
                                                                                                                                                                                       )
                                                                                                                                                                                       .getCivID()
                                                                                                                                                                                 )
                                                                                                                                                                                 .setNuclearWeapons(
                                                                                                                                                                                    CFG.game
                                                                                                                                                                                          .getCiv(
                                                                                                                                                                                             CFG.game
                                                                                                                                                                                                .getPlayer(
                                                                                                                                                                                                   CFG.PLAYER_TURNID
                                                                                                                                                                                                )
                                                                                                                                                                                                .getCivID()
                                                                                                                                                                                          )
                                                                                                                                                                                          .getNuclearWeapons()
                                                                                                                                                                                       + 999
                                                                                                                                                                                 );
                                                                                                                                                                              addMessage(
                                                                                                                                                                                 cheatMess()
                                                                                                                                                                                    + CFG.langManager
                                                                                                                                                                                       .get(
                                                                                                                                                                                          "nucl"
                                                                                                                                                                                       )
                                                                                                                                                                                    + ": +"
                                                                                                                                                                                    + CFG.ideologiesManager
                                                                                                                                                                                          .getIdeology(
                                                                                                                                                                                             CFG.game
                                                                                                                                                                                                .getCiv(
                                                                                                                                                                                                   CFG.game
                                                                                                                                                                                                      .getPlayer(
                                                                                                                                                                                                         CFG.PLAYER_TURNID
                                                                                                                                                                                                      )
                                                                                                                                                                                                      .getCivID()
                                                                                                                                                                                                )
                                                                                                                                                                                                .getIdeologyID()
                                                                                                                                                                                          )
                                                                                                                                                                                          .COST_OF_MOVE
                                                                                                                                                                                       * 999
                                                                                                                                                                              );
                                                                                                                                                                              addMessage(
                                                                                                                                                                                 ""
                                                                                                                                                                              );
                                                                                                                                                                              CFG.toast
                                                                                                                                                                                 .setInView(
                                                                                                                                                                                    cheatMess()
                                                                                                                                                                                       + CFG.langManager
                                                                                                                                                                                          .get(
                                                                                                                                                                                             "nucl"
                                                                                                                                                                                          )
                                                                                                                                                                                 );
                                                                                                                                                                              CFG.menuManager
                                                                                                                                                                                 .updateInGame_TOP_All(
                                                                                                                                                                                    CFG.game
                                                                                                                                                                                       .getPlayer(
                                                                                                                                                                                          CFG.PLAYER_TURNID
                                                                                                                                                                                       )
                                                                                                                                                                                       .getCivID()
                                                                                                                                                                                 );
                                                                                                                                                                              return;
                                                                                                                                                                           }

                                                                                                                                                                           CFG.game
                                                                                                                                                                              .getCiv(
                                                                                                                                                                                 CFG.game
                                                                                                                                                                                    .getPlayer(
                                                                                                                                                                                       CFG.PLAYER_TURNID
                                                                                                                                                                                    )
                                                                                                                                                                                    .getCivID()
                                                                                                                                                                              )
                                                                                                                                                                              .setMilitaryPoints(
                                                                                                                                                                                 CFG.game
                                                                                                                                                                                       .getCiv(
                                                                                                                                                                                          CFG.game
                                                                                                                                                                                             .getPlayer(
                                                                                                                                                                                                CFG.PLAYER_TURNID
                                                                                                                                                                                             )
                                                                                                                                                                                             .getCivID()
                                                                                                                                                                                       )
                                                                                                                                                                                       .getMilitaryPoints()
                                                                                                                                                                                    + 999
                                                                                                                                                                              );
                                                                                                                                                                           addMessage(
                                                                                                                                                                              cheatMess()
                                                                                                                                                                                 + CFG.langManager
                                                                                                                                                                                    .get(
                                                                                                                                                                                       "MilPoints"
                                                                                                                                                                                    )
                                                                                                                                                                                 + ": +"
                                                                                                                                                                                 + 999
                                                                                                                                                                           );
                                                                                                                                                                           addMessage(
                                                                                                                                                                              ""
                                                                                                                                                                           );
                                                                                                                                                                           CFG.toast
                                                                                                                                                                              .setInView(
                                                                                                                                                                                 cheatMess()
                                                                                                                                                                                    + CFG.langManager
                                                                                                                                                                                       .get(
                                                                                                                                                                                          "Mil"
                                                                                                                                                                                       )
                                                                                                                                                                              );
                                                                                                                                                                           CFG.menuManager
                                                                                                                                                                              .updateInGame_TOP_All(
                                                                                                                                                                                 CFG.game
                                                                                                                                                                                    .getPlayer(
                                                                                                                                                                                       CFG.PLAYER_TURNID
                                                                                                                                                                                    )
                                                                                                                                                                                    .getCivID()
                                                                                                                                                                              );
                                                                                                                                                                           return;
                                                                                                                                                                        }

                                                                                                                                                                        CFG.game
                                                                                                                                                                           .getCiv(
                                                                                                                                                                              CFG.game
                                                                                                                                                                                 .getPlayer(
                                                                                                                                                                                    CFG.PLAYER_TURNID
                                                                                                                                                                                 )
                                                                                                                                                                                 .getCivID()
                                                                                                                                                                           )
                                                                                                                                                                           .setMovePoints(
                                                                                                                                                                              CFG.game
                                                                                                                                                                                    .getCiv(
                                                                                                                                                                                       CFG.game
                                                                                                                                                                                          .getPlayer(
                                                                                                                                                                                             CFG.PLAYER_TURNID
                                                                                                                                                                                          )
                                                                                                                                                                                          .getCivID()
                                                                                                                                                                                    )
                                                                                                                                                                                    .getMovePoints()
                                                                                                                                                                                 + CFG.ideologiesManager
                                                                                                                                                                                       .getIdeology(
                                                                                                                                                                                          CFG.game
                                                                                                                                                                                             .getCiv(
                                                                                                                                                                                                CFG.game
                                                                                                                                                                                                   .getPlayer(
                                                                                                                                                                                                      CFG.PLAYER_TURNID
                                                                                                                                                                                                   )
                                                                                                                                                                                                   .getCivID()
                                                                                                                                                                                             )
                                                                                                                                                                                             .getIdeologyID()
                                                                                                                                                                                       )
                                                                                                                                                                                       .COST_OF_MOVE
                                                                                                                                                                                    / 2
                                                                                                                                                                           );
                                                                                                                                                                        addMessage(
                                                                                                                                                                           cheatMess()
                                                                                                                                                                              + CFG.langManager
                                                                                                                                                                                 .get(
                                                                                                                                                                                    "MovementPoints"
                                                                                                                                                                                 )
                                                                                                                                                                              + ": +"
                                                                                                                                                                              + CFG.ideologiesManager
                                                                                                                                                                                    .getIdeology(
                                                                                                                                                                                       CFG.game
                                                                                                                                                                                          .getCiv(
                                                                                                                                                                                             CFG.game
                                                                                                                                                                                                .getPlayer(
                                                                                                                                                                                                   CFG.PLAYER_TURNID
                                                                                                                                                                                                )
                                                                                                                                                                                                .getCivID()
                                                                                                                                                                                          )
                                                                                                                                                                                          .getIdeologyID()
                                                                                                                                                                                    )
                                                                                                                                                                                    .COST_OF_MOVE
                                                                                                                                                                                 / 2
                                                                                                                                                                        );
                                                                                                                                                                        addMessage(
                                                                                                                                                                           ""
                                                                                                                                                                        );
                                                                                                                                                                        CFG.toast
                                                                                                                                                                           .setInView(
                                                                                                                                                                              cheatMess()
                                                                                                                                                                                 + CFG.langManager
                                                                                                                                                                                    .get(
                                                                                                                                                                                       "movement"
                                                                                                                                                                                    )
                                                                                                                                                                           );
                                                                                                                                                                        CFG.menuManager
                                                                                                                                                                           .updateInGame_TOP_All(
                                                                                                                                                                              CFG.game
                                                                                                                                                                                 .getPlayer(
                                                                                                                                                                                    CFG.PLAYER_TURNID
                                                                                                                                                                                 )
                                                                                                                                                                                 .getCivID()
                                                                                                                                                                           );
                                                                                                                                                                        return;
                                                                                                                                                                     }

                                                                                                                                                                     int tMoney = Integer.parseInt(
                                                                                                                                                                        tempCommand[1]
                                                                                                                                                                     );
                                                                                                                                                                     CFG.game
                                                                                                                                                                        .getCiv(
                                                                                                                                                                           CFG.game
                                                                                                                                                                              .getPlayer(
                                                                                                                                                                                 CFG.PLAYER_TURNID
                                                                                                                                                                              )
                                                                                                                                                                              .getCivID()
                                                                                                                                                                        )
                                                                                                                                                                        .setMoney(
                                                                                                                                                                           CFG.game
                                                                                                                                                                                 .getCiv(
                                                                                                                                                                                    CFG.game
                                                                                                                                                                                       .getPlayer(
                                                                                                                                                                                          CFG.PLAYER_TURNID
                                                                                                                                                                                       )
                                                                                                                                                                                       .getCivID()
                                                                                                                                                                                 )
                                                                                                                                                                                 .getMoney()
                                                                                                                                                                              + tMoney
                                                                                                                                                                        );
                                                                                                                                                                     addMessage(
                                                                                                                                                                        cheatMess()
                                                                                                                                                                           + CFG.langManager
                                                                                                                                                                              .get(
                                                                                                                                                                                 "Money"
                                                                                                                                                                              )
                                                                                                                                                                           + ": +"
                                                                                                                                                                           + tMoney
                                                                                                                                                                     );
                                                                                                                                                                     addMessage(
                                                                                                                                                                        ""
                                                                                                                                                                     );
                                                                                                                                                                     CFG.toast
                                                                                                                                                                        .setInView(
                                                                                                                                                                           cheatMess()
                                                                                                                                                                              + CFG.langManager
                                                                                                                                                                                 .get(
                                                                                                                                                                                    "Money"
                                                                                                                                                                                 )
                                                                                                                                                                        );
                                                                                                                                                                     CFG.menuManager
                                                                                                                                                                        .updateInGame_TOP_All(
                                                                                                                                                                           CFG.game
                                                                                                                                                                              .getPlayer(
                                                                                                                                                                                 CFG.PLAYER_TURNID
                                                                                                                                                                              )
                                                                                                                                                                              .getCivID()
                                                                                                                                                                        );
                                                                                                                                                                     return;
                                                                                                                                                                  }

                                                                                                                                                                  if (CFG.game
                                                                                                                                                                        .getActiveProvinceID()
                                                                                                                                                                     >= 0
                                                                                                                                                                     )
                                                                                                                                                                   {
                                                                                                                                                                     CFG.game
                                                                                                                                                                        .getProvince(
                                                                                                                                                                           CFG.game
                                                                                                                                                                              .getActiveProvinceID()
                                                                                                                                                                        )
                                                                                                                                                                        .updateArmy(
                                                                                                                                                                           CFG.game
                                                                                                                                                                              .getProvince(
                                                                                                                                                                                 CFG.game
                                                                                                                                                                                    .getActiveProvinceID()
                                                                                                                                                                              )
                                                                                                                                                                              .getCivID(
                                                                                                                                                                                 CFG.activeCivilizationArmyID
                                                                                                                                                                              ),
                                                                                                                                                                           CFG.game
                                                                                                                                                                                 .getProvince(
                                                                                                                                                                                    CFG.game
                                                                                                                                                                                       .getActiveProvinceID()
                                                                                                                                                                                 )
                                                                                                                                                                                 .getArmy(
                                                                                                                                                                                    CFG.activeCivilizationArmyID
                                                                                                                                                                                 )
                                                                                                                                                                              + 300
                                                                                                                                                                        );
                                                                                                                                                                     addMessage(
                                                                                                                                                                        cheatMess()
                                                                                                                                                                           + CFG.langManager
                                                                                                                                                                              .get(
                                                                                                                                                                                 "Army"
                                                                                                                                                                              )
                                                                                                                                                                           + ": +"
                                                                                                                                                                           + 300
                                                                                                                                                                     );
                                                                                                                                                                     addMessage(
                                                                                                                                                                        ""
                                                                                                                                                                     );
                                                                                                                                                                     int tActiveProvince = CFG.game
                                                                                                                                                                        .getActiveProvinceID();
                                                                                                                                                                     CFG.game
                                                                                                                                                                        .setActiveProvinceID(
                                                                                                                                                                           -1
                                                                                                                                                                        );
                                                                                                                                                                     CFG.game
                                                                                                                                                                        .setActiveProvinceID(
                                                                                                                                                                           tActiveProvince
                                                                                                                                                                        );
                                                                                                                                                                     CFG.toast
                                                                                                                                                                        .setInView(
                                                                                                                                                                           cheatMess()
                                                                                                                                                                              + CFG.langManager
                                                                                                                                                                                 .get(
                                                                                                                                                                                    "Army"
                                                                                                                                                                                 )
                                                                                                                                                                        );
                                                                                                                                                                     if (CFG.menuManager
                                                                                                                                                                        .getVisibleInGame_CensusOfProvince()
                                                                                                                                                                        )
                                                                                                                                                                      {
                                                                                                                                                                        CFG.menuManager
                                                                                                                                                                           .rebuildInGame_CensusOfProvince(
                                                                                                                                                                              CFG.game
                                                                                                                                                                                 .getActiveProvinceID()
                                                                                                                                                                           );
                                                                                                                                                                     }
                                                                                                                                                                  } else {
                                                                                                                                                                     IllegalCommand();
                                                                                                                                                                     CFG.toast
                                                                                                                                                                        .setInView(
                                                                                                                                                                           CFG.langManager
                                                                                                                                                                              .get(
                                                                                                                                                                                 "ChooseAProvince"
                                                                                                                                                                              ),
                                                                                                                                                                           CFG.COLOR_TEXT_MODIFIER_NEGATIVE2
                                                                                                                                                                        );
                                                                                                                                                                     addMessage(
                                                                                                                                                                        CFG.langManager
                                                                                                                                                                           .get(
                                                                                                                                                                              CFG.langManager
                                                                                                                                                                                 .get(
                                                                                                                                                                                    "ChooseAProvince"
                                                                                                                                                                                 )
                                                                                                                                                                           )
                                                                                                                                                                     );
                                                                                                                                                                     addMessage(
                                                                                                                                                                        ""
                                                                                                                                                                     );
                                                                                                                                                                  }

                                                                                                                                                                  return;
                                                                                                                                                               }

                                                                                                                                                               int tEconomy = Integer.parseInt(
                                                                                                                                                                  tempCommand[1]
                                                                                                                                                               );

                                                                                                                                                               for (int i = 0;
                                                                                                                                                                  i
                                                                                                                                                                     < CFG.game
                                                                                                                                                                        .getCiv(
                                                                                                                                                                           CFG.game
                                                                                                                                                                              .getProvince(
                                                                                                                                                                                 CFG.game
                                                                                                                                                                                    .getActiveProvinceID()
                                                                                                                                                                              )
                                                                                                                                                                              .getCivID()
                                                                                                                                                                        )
                                                                                                                                                                        .getNumOfProvinces();
                                                                                                                                                                  i++
                                                                                                                                                               ) {
                                                                                                                                                                  CFG.game
                                                                                                                                                                     .getProvince(
                                                                                                                                                                        CFG.game
                                                                                                                                                                           .getCiv(
                                                                                                                                                                              CFG.game
                                                                                                                                                                                 .getProvince(
                                                                                                                                                                                    CFG.game
                                                                                                                                                                                       .getActiveProvinceID()
                                                                                                                                                                                 )
                                                                                                                                                                                 .getCivID()
                                                                                                                                                                           )
                                                                                                                                                                           .getProvinceID(
                                                                                                                                                                              i
                                                                                                                                                                           )
                                                                                                                                                                     )
                                                                                                                                                                     .setEconomy(
                                                                                                                                                                        CFG.game
                                                                                                                                                                              .getProvince(
                                                                                                                                                                                 CFG.game
                                                                                                                                                                                    .getCiv(
                                                                                                                                                                                       CFG.game
                                                                                                                                                                                          .getProvince(
                                                                                                                                                                                             CFG.game
                                                                                                                                                                                                .getActiveProvinceID()
                                                                                                                                                                                          )
                                                                                                                                                                                          .getCivID()
                                                                                                                                                                                    )
                                                                                                                                                                                    .getProvinceID(
                                                                                                                                                                                       i
                                                                                                                                                                                    )
                                                                                                                                                                              )
                                                                                                                                                                              .getEconomy()
                                                                                                                                                                           + tEconomy
                                                                                                                                                                     );
                                                                                                                                                               }

                                                                                                                                                               addMessage(
                                                                                                                                                                  cheatMess()
                                                                                                                                                                     + tEconomy
                                                                                                                                                                     + " - economy points added"
                                                                                                                                                               );
                                                                                                                                                               return;
                                                                                                                                                            }

                                                                                                                                                            int tEconomy = Integer.parseInt(
                                                                                                                                                               tempCommand[1]
                                                                                                                                                            );
                                                                                                                                                            if (CFG.game
                                                                                                                                                                     .getActiveProvinceID()
                                                                                                                                                                  >= 0
                                                                                                                                                               && CFG.game
                                                                                                                                                                     .getProvince(
                                                                                                                                                                        CFG.game
                                                                                                                                                                           .getActiveProvinceID()
                                                                                                                                                                     )
                                                                                                                                                                     .getWasteland()
                                                                                                                                                                  < 0
                                                                                                                                                               && !CFG.game
                                                                                                                                                                  .getProvince(
                                                                                                                                                                     CFG.game
                                                                                                                                                                        .getActiveProvinceID()
                                                                                                                                                                  )
                                                                                                                                                                  .getSeaProvince()
                                                                                                                                                               )
                                                                                                                                                             {
                                                                                                                                                               CFG.game
                                                                                                                                                                  .getProvince(
                                                                                                                                                                     CFG.game
                                                                                                                                                                        .getActiveProvinceID()
                                                                                                                                                                  )
                                                                                                                                                                  .setEconomy(
                                                                                                                                                                     CFG.game
                                                                                                                                                                           .getProvince(
                                                                                                                                                                              CFG.game
                                                                                                                                                                                 .getActiveProvinceID()
                                                                                                                                                                           )
                                                                                                                                                                           .getEconomy()
                                                                                                                                                                        + tEconomy
                                                                                                                                                                  );
                                                                                                                                                               addMessage(
                                                                                                                                                                  cheatMess()
                                                                                                                                                                     + CFG.langManager
                                                                                                                                                                        .get(
                                                                                                                                                                           "Economy"
                                                                                                                                                                        )
                                                                                                                                                                     + ": +"
                                                                                                                                                                     + tEconomy
                                                                                                                                                               );
                                                                                                                                                               addMessage(
                                                                                                                                                                  ""
                                                                                                                                                               );
                                                                                                                                                               int tActiveProvince = CFG.game
                                                                                                                                                                  .getActiveProvinceID();
                                                                                                                                                               CFG.game
                                                                                                                                                                  .setActiveProvinceID(
                                                                                                                                                                     -1
                                                                                                                                                                  );
                                                                                                                                                               CFG.game
                                                                                                                                                                  .setActiveProvinceID(
                                                                                                                                                                     tActiveProvince
                                                                                                                                                                  );
                                                                                                                                                               CFG.toast
                                                                                                                                                                  .setInView(
                                                                                                                                                                     cheatMess()
                                                                                                                                                                        + CFG.langManager
                                                                                                                                                                           .get(
                                                                                                                                                                              "Economy"
                                                                                                                                                                           )
                                                                                                                                                                  );
                                                                                                                                                               if (CFG.menuManager
                                                                                                                                                                  .getVisibleInGame_CensusOfProvince()
                                                                                                                                                                  )
                                                                                                                                                                {
                                                                                                                                                                  CFG.menuManager
                                                                                                                                                                     .rebuildInGame_CensusOfProvince(
                                                                                                                                                                        CFG.game
                                                                                                                                                                           .getActiveProvinceID()
                                                                                                                                                                     );
                                                                                                                                                               }
                                                                                                                                                            } else {
                                                                                                                                                               IllegalCommand();
                                                                                                                                                               CFG.toast
                                                                                                                                                                  .setInView(
                                                                                                                                                                     CFG.langManager
                                                                                                                                                                        .get(
                                                                                                                                                                           "ChooseAProvince"
                                                                                                                                                                        ),
                                                                                                                                                                     CFG.COLOR_TEXT_MODIFIER_NEGATIVE2
                                                                                                                                                                  );
                                                                                                                                                               addMessage(
                                                                                                                                                                  CFG.langManager
                                                                                                                                                                     .get(
                                                                                                                                                                        CFG.langManager
                                                                                                                                                                           .get(
                                                                                                                                                                              "ChooseAProvince"
                                                                                                                                                                           )
                                                                                                                                                                     )
                                                                                                                                                               );
                                                                                                                                                               addMessage(
                                                                                                                                                                  ""
                                                                                                                                                               );
                                                                                                                                                            }

                                                                                                                                                            return;
                                                                                                                                                         }

                                                                                                                                                         if (CFG.game
                                                                                                                                                                  .getActiveProvinceID()
                                                                                                                                                               >= 0
                                                                                                                                                            && CFG.game
                                                                                                                                                                  .getProvince(
                                                                                                                                                                     CFG.game
                                                                                                                                                                        .getActiveProvinceID()
                                                                                                                                                                  )
                                                                                                                                                                  .getWasteland()
                                                                                                                                                               < 0
                                                                                                                                                            )
                                                                                                                                                          {
                                                                                                                                                            CFG.game
                                                                                                                                                               .getProvince(
                                                                                                                                                                  CFG.game
                                                                                                                                                                     .getActiveProvinceID()
                                                                                                                                                               )
                                                                                                                                                               .setLevelOfWatchTower(
                                                                                                                                                                  1
                                                                                                                                                               );
                                                                                                                                                            CFG.game
                                                                                                                                                               .getProvince(
                                                                                                                                                                  CFG.game
                                                                                                                                                                     .getActiveProvinceID()
                                                                                                                                                               )
                                                                                                                                                               .updateDrawArmy();
                                                                                                                                                            addMessage(
                                                                                                                                                               cheatMess()
                                                                                                                                                                  + "Tower built"
                                                                                                                                                            );
                                                                                                                                                            addMessage(
                                                                                                                                                               ""
                                                                                                                                                            );
                                                                                                                                                            int tActiveProvince = CFG.game
                                                                                                                                                               .getActiveProvinceID();
                                                                                                                                                            CFG.game
                                                                                                                                                               .setActiveProvinceID(
                                                                                                                                                                  -1
                                                                                                                                                               );
                                                                                                                                                            CFG.game
                                                                                                                                                               .setActiveProvinceID(
                                                                                                                                                                  tActiveProvince
                                                                                                                                                               );
                                                                                                                                                            CFG.toast
                                                                                                                                                               .setInView(
                                                                                                                                                                  cheatMess()
                                                                                                                                                                     + CFG.langManager
                                                                                                                                                                        .get(
                                                                                                                                                                           "Tower built"
                                                                                                                                                                        )
                                                                                                                                                               );
                                                                                                                                                         } else {
                                                                                                                                                            IllegalCommand();
                                                                                                                                                            CFG.toast
                                                                                                                                                               .setInView(
                                                                                                                                                                  CFG.langManager
                                                                                                                                                                     .get(
                                                                                                                                                                        "ChooseAProvince"
                                                                                                                                                                     ),
                                                                                                                                                                  CFG.COLOR_TEXT_MODIFIER_NEGATIVE2
                                                                                                                                                               );
                                                                                                                                                            addMessage(
                                                                                                                                                               CFG.langManager
                                                                                                                                                                  .get(
                                                                                                                                                                     CFG.langManager
                                                                                                                                                                        .get(
                                                                                                                                                                           "ChooseAProvince"
                                                                                                                                                                        )
                                                                                                                                                                  )
                                                                                                                                                            );
                                                                                                                                                            addMessage(
                                                                                                                                                               ""
                                                                                                                                                            );
                                                                                                                                                         }

                                                                                                                                                         return;
                                                                                                                                                      }

                                                                                                                                                      if (CFG.game
                                                                                                                                                               .getActiveProvinceID()
                                                                                                                                                            >= 0
                                                                                                                                                         && CFG.game
                                                                                                                                                               .getProvince(
                                                                                                                                                                  CFG.game
                                                                                                                                                                     .getActiveProvinceID()
                                                                                                                                                               )
                                                                                                                                                               .getWasteland()
                                                                                                                                                            < 0
                                                                                                                                                         )
                                                                                                                                                       {
                                                                                                                                                         CFG.game
                                                                                                                                                            .getProvince(
                                                                                                                                                               CFG.game
                                                                                                                                                                  .getActiveProvinceID()
                                                                                                                                                            )
                                                                                                                                                            .setLevelOfFort(
                                                                                                                                                               1
                                                                                                                                                            );
                                                                                                                                                         CFG.game
                                                                                                                                                            .getProvince(
                                                                                                                                                               CFG.game
                                                                                                                                                                  .getActiveProvinceID()
                                                                                                                                                            )
                                                                                                                                                            .updateDrawArmy();
                                                                                                                                                         addMessage(
                                                                                                                                                            cheatMess()
                                                                                                                                                               + "Fort built"
                                                                                                                                                         );
                                                                                                                                                         addMessage(
                                                                                                                                                            ""
                                                                                                                                                         );
                                                                                                                                                         int tActiveProvince = CFG.game
                                                                                                                                                            .getActiveProvinceID();
                                                                                                                                                         CFG.game
                                                                                                                                                            .setActiveProvinceID(
                                                                                                                                                               -1
                                                                                                                                                            );
                                                                                                                                                         CFG.game
                                                                                                                                                            .setActiveProvinceID(
                                                                                                                                                               tActiveProvince
                                                                                                                                                            );
                                                                                                                                                         CFG.toast
                                                                                                                                                            .setInView(
                                                                                                                                                               cheatMess()
                                                                                                                                                                  + CFG.langManager
                                                                                                                                                                     .get(
                                                                                                                                                                        "Fort built"
                                                                                                                                                                     )
                                                                                                                                                            );
                                                                                                                                                      } else {
                                                                                                                                                         IllegalCommand();
                                                                                                                                                         CFG.toast
                                                                                                                                                            .setInView(
                                                                                                                                                               CFG.langManager
                                                                                                                                                                  .get(
                                                                                                                                                                     "ChooseAProvince"
                                                                                                                                                                  ),
                                                                                                                                                               CFG.COLOR_TEXT_MODIFIER_NEGATIVE2
                                                                                                                                                            );
                                                                                                                                                         addMessage(
                                                                                                                                                            CFG.langManager
                                                                                                                                                               .get(
                                                                                                                                                                  CFG.langManager
                                                                                                                                                                     .get(
                                                                                                                                                                        "ChooseAProvince"
                                                                                                                                                                     )
                                                                                                                                                               )
                                                                                                                                                         );
                                                                                                                                                         addMessage(
                                                                                                                                                            ""
                                                                                                                                                         );
                                                                                                                                                      }

                                                                                                                                                      return;
                                                                                                                                                   }

                                                                                                                                                   if (CFG.game
                                                                                                                                                            .getActiveProvinceID()
                                                                                                                                                         >= 0
                                                                                                                                                      && CFG.game
                                                                                                                                                            .getProvince(
                                                                                                                                                               CFG.game
                                                                                                                                                                  .getActiveProvinceID()
                                                                                                                                                            )
                                                                                                                                                            .getWasteland()
                                                                                                                                                         < 0
                                                                                                                                                      && CFG.game
                                                                                                                                                            .getProvince(
                                                                                                                                                               CFG.game
                                                                                                                                                                  .getActiveProvinceID()
                                                                                                                                                            )
                                                                                                                                                            .getLevelOfPort()
                                                                                                                                                         >= 0) {
                                                                                                                                                      CFG.game
                                                                                                                                                         .getProvince(
                                                                                                                                                            CFG.game
                                                                                                                                                               .getActiveProvinceID()
                                                                                                                                                         )
                                                                                                                                                         .setLevelOfPort(
                                                                                                                                                            1
                                                                                                                                                         );
                                                                                                                                                      addMessage(
                                                                                                                                                         cheatMess()
                                                                                                                                                            + "Port built"
                                                                                                                                                      );
                                                                                                                                                      addMessage(
                                                                                                                                                         ""
                                                                                                                                                      );
                                                                                                                                                      int tActiveProvince = CFG.game
                                                                                                                                                         .getActiveProvinceID();
                                                                                                                                                      CFG.game
                                                                                                                                                         .setActiveProvinceID(
                                                                                                                                                            -1
                                                                                                                                                         );
                                                                                                                                                      CFG.game
                                                                                                                                                         .setActiveProvinceID(
                                                                                                                                                            tActiveProvince
                                                                                                                                                         );
                                                                                                                                                      CFG.toast
                                                                                                                                                         .setInView(
                                                                                                                                                            cheatMess()
                                                                                                                                                               + CFG.langManager
                                                                                                                                                                  .get(
                                                                                                                                                                     "Port built"
                                                                                                                                                                  )
                                                                                                                                                         );
                                                                                                                                                   } else {
                                                                                                                                                      IllegalCommand();
                                                                                                                                                      CFG.toast
                                                                                                                                                         .setInView(
                                                                                                                                                            CFG.langManager
                                                                                                                                                               .get(
                                                                                                                                                                  "ChooseAProvince"
                                                                                                                                                               ),
                                                                                                                                                            CFG.COLOR_TEXT_MODIFIER_NEGATIVE2
                                                                                                                                                         );
                                                                                                                                                      addMessage(
                                                                                                                                                         CFG.langManager
                                                                                                                                                            .get(
                                                                                                                                                               CFG.langManager
                                                                                                                                                                  .get(
                                                                                                                                                                     "ChooseAProvince"
                                                                                                                                                                  )
                                                                                                                                                            )
                                                                                                                                                      );
                                                                                                                                                      addMessage(
                                                                                                                                                         ""
                                                                                                                                                      );
                                                                                                                                                   }

                                                                                                                                                   return;
                                                                                                                                                }

                                                                                                                                                int civA = Integer.parseInt(
                                                                                                                                                   tempCommand[1]
                                                                                                                                                );
                                                                                                                                                int civB = Integer.parseInt(
                                                                                                                                                   tempCommand[2]
                                                                                                                                                );
                                                                                                                                                if (civA >= 0
                                                                                                                                                   && civB >= 0
                                                                                                                                                   && CFG.game
                                                                                                                                                      .getCivsAtWar(
                                                                                                                                                         civA,
                                                                                                                                                         civB
                                                                                                                                                      )) {
                                                                                                                                                   CFG.game
                                                                                                                                                      .getCiv(
                                                                                                                                                         civB
                                                                                                                                                      )
                                                                                                                                                      .civGameData
                                                                                                                                                      .civilization_Diplomacy_GameData
                                                                                                                                                      .messageBox
                                                                                                                                                      .addMessage(
                                                                                                                                                         new Message_WeCanSignPeace(
                                                                                                                                                            civA
                                                                                                                                                         )
                                                                                                                                                      );
                                                                                                                                                   addMessage(
                                                                                                                                                      cheatMess()
                                                                                                                                                         + CFG.langManager
                                                                                                                                                            .get(
                                                                                                                                                               "Added"
                                                                                                                                                            )
                                                                                                                                                         + ": "
                                                                                                                                                         + CFG.game
                                                                                                                                                            .getCiv(
                                                                                                                                                               civA
                                                                                                                                                            )
                                                                                                                                                            .getCivName()
                                                                                                                                                         + " -> "
                                                                                                                                                         + CFG.game
                                                                                                                                                            .getCiv(
                                                                                                                                                               civB
                                                                                                                                                            )
                                                                                                                                                            .getCivName()
                                                                                                                                                   );
                                                                                                                                                   addMessage(
                                                                                                                                                      ""
                                                                                                                                                   );
                                                                                                                                                   int tActiveProvince = CFG.game
                                                                                                                                                      .getActiveProvinceID();
                                                                                                                                                   CFG.game
                                                                                                                                                      .setActiveProvinceID(
                                                                                                                                                         -1
                                                                                                                                                      );
                                                                                                                                                   CFG.game
                                                                                                                                                      .setActiveProvinceID(
                                                                                                                                                         tActiveProvince
                                                                                                                                                      );
                                                                                                                                                   CFG.toast
                                                                                                                                                      .setInView(
                                                                                                                                                         cheatMess()
                                                                                                                                                            + CFG.langManager
                                                                                                                                                               .get(
                                                                                                                                                                  "Added"
                                                                                                                                                               )
                                                                                                                                                      );
                                                                                                                                                } else {
                                                                                                                                                   IllegalCommand();
                                                                                                                                                   CFG.toast
                                                                                                                                                      .setInView(
                                                                                                                                                         CFG.langManager
                                                                                                                                                            .get(
                                                                                                                                                               "Error"
                                                                                                                                                            ),
                                                                                                                                                         CFG.COLOR_TEXT_MODIFIER_NEGATIVE2
                                                                                                                                                      );
                                                                                                                                                   addMessage(
                                                                                                                                                      CFG.langManager
                                                                                                                                                         .get(
                                                                                                                                                            CFG.langManager
                                                                                                                                                               .get(
                                                                                                                                                                  "Error"
                                                                                                                                                               )
                                                                                                                                                         )
                                                                                                                                                   );
                                                                                                                                                   addMessage(
                                                                                                                                                      ""
                                                                                                                                                   );
                                                                                                                                                }

                                                                                                                                                return;
                                                                                                                                             }

                                                                                                                                             int civA = Integer.parseInt(
                                                                                                                                                tempCommand[1]
                                                                                                                                             );
                                                                                                                                             int civB = Integer.parseInt(
                                                                                                                                                tempCommand[2]
                                                                                                                                             );
                                                                                                                                             if (civA >= 0
                                                                                                                                                && civB >= 0
                                                                                                                                                && CFG.game
                                                                                                                                                      .getCiv(
                                                                                                                                                         civA
                                                                                                                                                      )
                                                                                                                                                      .getNumOfProvinces()
                                                                                                                                                   > 0
                                                                                                                                                && CFG.game
                                                                                                                                                      .getCiv(
                                                                                                                                                         civB
                                                                                                                                                      )
                                                                                                                                                      .getNumOfProvinces()
                                                                                                                                                   > 0) {
                                                                                                                                                CFG.game
                                                                                                                                                   .declareWar(
                                                                                                                                                      civA,
                                                                                                                                                      civB,
                                                                                                                                                      true
                                                                                                                                                   );
                                                                                                                                                addMessage(
                                                                                                                                                   cheatMess()
                                                                                                                                                      + CFG.langManager
                                                                                                                                                         .get(
                                                                                                                                                            "War"
                                                                                                                                                         )
                                                                                                                                                      + ": "
                                                                                                                                                      + CFG.game
                                                                                                                                                         .getCiv(
                                                                                                                                                            civA
                                                                                                                                                         )
                                                                                                                                                         .getCivName()
                                                                                                                                                      + " -> "
                                                                                                                                                      + CFG.game
                                                                                                                                                         .getCiv(
                                                                                                                                                            civB
                                                                                                                                                         )
                                                                                                                                                         .getCivName()
                                                                                                                                                );
                                                                                                                                                addMessage("");
                                                                                                                                                int tActiveProvince = CFG.game
                                                                                                                                                   .getActiveProvinceID();
                                                                                                                                                CFG.game
                                                                                                                                                   .setActiveProvinceID(
                                                                                                                                                      -1
                                                                                                                                                   );
                                                                                                                                                CFG.game
                                                                                                                                                   .setActiveProvinceID(
                                                                                                                                                      tActiveProvince
                                                                                                                                                   );
                                                                                                                                                CFG.toast
                                                                                                                                                   .setInView(
                                                                                                                                                      cheatMess()
                                                                                                                                                         + CFG.langManager
                                                                                                                                                            .get(
                                                                                                                                                               "War"
                                                                                                                                                            )
                                                                                                                                                   );
                                                                                                                                             } else {
                                                                                                                                                IllegalCommand();
                                                                                                                                                CFG.toast
                                                                                                                                                   .setInView(
                                                                                                                                                      CFG.langManager
                                                                                                                                                         .get(
                                                                                                                                                            "Error"
                                                                                                                                                         ),
                                                                                                                                                      CFG.COLOR_TEXT_MODIFIER_NEGATIVE2
                                                                                                                                                   );
                                                                                                                                                addMessage(
                                                                                                                                                   CFG.langManager
                                                                                                                                                      .get(
                                                                                                                                                         CFG.langManager
                                                                                                                                                            .get(
                                                                                                                                                               "Error"
                                                                                                                                                            )
                                                                                                                                                      )
                                                                                                                                                );
                                                                                                                                                addMessage("");
                                                                                                                                             }

                                                                                                                                             return;
                                                                                                                                          }

                                                                                                                                          if (CFG.game
                                                                                                                                                .getActiveProvinceID()
                                                                                                                                             >= 0) {
                                                                                                                                             addMessage(
                                                                                                                                                cheatMess()
                                                                                                                                                   + CFG.langManager
                                                                                                                                                      .get(
                                                                                                                                                         "Province"
                                                                                                                                                      )
                                                                                                                                                   + ": "
                                                                                                                                                   + CFG.game
                                                                                                                                                      .getActiveProvinceID()
                                                                                                                                             );
                                                                                                                                             addMessage(
                                                                                                                                                cheatMess()
                                                                                                                                                   + CFG.langManager
                                                                                                                                                      .get(
                                                                                                                                                         "Civilization"
                                                                                                                                                      )
                                                                                                                                                   + ": "
                                                                                                                                                   + CFG.game
                                                                                                                                                      .getCiv(
                                                                                                                                                         CFG.game
                                                                                                                                                            .getProvince(
                                                                                                                                                               CFG.game
                                                                                                                                                                  .getActiveProvinceID()
                                                                                                                                                            )
                                                                                                                                                            .getCivID()
                                                                                                                                                      )
                                                                                                                                                      .getCivName()
                                                                                                                                                   + ": "
                                                                                                                                                   + CFG.game
                                                                                                                                                      .getCiv(
                                                                                                                                                         CFG.game
                                                                                                                                                            .getProvince(
                                                                                                                                                               CFG.game
                                                                                                                                                                  .getActiveProvinceID()
                                                                                                                                                            )
                                                                                                                                                            .getCivID()
                                                                                                                                                      )
                                                                                                                                                      .getCivID()
                                                                                                                                             );
                                                                                                                                             addMessage("");
                                                                                                                                             int tActiveProvince = CFG.game
                                                                                                                                                .getActiveProvinceID();
                                                                                                                                             CFG.game
                                                                                                                                                .setActiveProvinceID(
                                                                                                                                                   -1
                                                                                                                                                );
                                                                                                                                             CFG.game
                                                                                                                                                .setActiveProvinceID(
                                                                                                                                                   tActiveProvince
                                                                                                                                                );
                                                                                                                                             CFG.toast
                                                                                                                                                .setInView(
                                                                                                                                                   cheatMess()
                                                                                                                                                      + CFG.langManager
                                                                                                                                                         .get(
                                                                                                                                                            "War"
                                                                                                                                                         )
                                                                                                                                                );
                                                                                                                                          } else {
                                                                                                                                             IllegalCommand();
                                                                                                                                             CFG.toast
                                                                                                                                                .setInView(
                                                                                                                                                   CFG.langManager
                                                                                                                                                      .get(
                                                                                                                                                         "ChooseAProvince"
                                                                                                                                                      ),
                                                                                                                                                   CFG.COLOR_TEXT_MODIFIER_NEGATIVE2
                                                                                                                                                );
                                                                                                                                             addMessage(
                                                                                                                                                CFG.langManager
                                                                                                                                                   .get(
                                                                                                                                                      CFG.langManager
                                                                                                                                                         .get(
                                                                                                                                                            "ChooseAProvince"
                                                                                                                                                         )
                                                                                                                                                   )
                                                                                                                                             );
                                                                                                                                             addMessage("");
                                                                                                                                          }

                                                                                                                                          return;
                                                                                                                                       }

                                                                                                                                       CFG.NO_LIBERITY = !CFG.NO_LIBERITY;
                                                                                                                                       addMessage(
                                                                                                                                          cheatMess()
                                                                                                                                             + CFG.langManager
                                                                                                                                                .get(
                                                                                                                                                   "Liberation"
                                                                                                                                                )
                                                                                                                                             + ": "
                                                                                                                                             + (
                                                                                                                                                CFG.NO_LIBERITY
                                                                                                                                                   ? CFG.langManager
                                                                                                                                                      .get(
                                                                                                                                                         "Disabled"
                                                                                                                                                      )
                                                                                                                                                   : CFG.langManager
                                                                                                                                                      .get(
                                                                                                                                                         "Enabled"
                                                                                                                                                      )
                                                                                                                                             )
                                                                                                                                       );
                                                                                                                                       addMessage("");
                                                                                                                                       CFG.toast
                                                                                                                                          .setInView(
                                                                                                                                             cheatMess()
                                                                                                                                                + CFG.langManager
                                                                                                                                                   .get(
                                                                                                                                                      "Liberation"
                                                                                                                                                   )
                                                                                                                                                + ": "
                                                                                                                                                + (
                                                                                                                                                   CFG.NO_LIBERITY
                                                                                                                                                      ? CFG.langManager
                                                                                                                                                         .get(
                                                                                                                                                            "Disabled"
                                                                                                                                                         )
                                                                                                                                                      : CFG.langManager
                                                                                                                                                         .get(
                                                                                                                                                            "Enabled"
                                                                                                                                                         )
                                                                                                                                                )
                                                                                                                                          );
                                                                                                                                       return;
                                                                                                                                    }

                                                                                                                                    int tArmy = Integer.parseInt(
                                                                                                                                       tempCommand[1]
                                                                                                                                    );
                                                                                                                                    if (tArmy >= 0
                                                                                                                                       && CFG.game
                                                                                                                                             .getActiveProvinceID()
                                                                                                                                          >= 0
                                                                                                                                       && CFG.game
                                                                                                                                             .getProvince(
                                                                                                                                                CFG.game
                                                                                                                                                   .getActiveProvinceID()
                                                                                                                                             )
                                                                                                                                             .getWasteland()
                                                                                                                                          < 0
                                                                                                                                       && !CFG.game
                                                                                                                                          .getProvince(
                                                                                                                                             CFG.game
                                                                                                                                                .getActiveProvinceID()
                                                                                                                                          )
                                                                                                                                          .getSeaProvince()) {
                                                                                                                                       CFG.game
                                                                                                                                          .getCiv(
                                                                                                                                             CFG.game
                                                                                                                                                .getProvince(
                                                                                                                                                   CFG.game
                                                                                                                                                      .getActiveProvinceID()
                                                                                                                                                )
                                                                                                                                                .getCivID()
                                                                                                                                          )
                                                                                                                                          .setNumOfUnits(
                                                                                                                                             CFG.game
                                                                                                                                                   .getCiv(
                                                                                                                                                      CFG.game
                                                                                                                                                         .getProvince(
                                                                                                                                                            CFG.game
                                                                                                                                                               .getActiveProvinceID()
                                                                                                                                                         )
                                                                                                                                                         .getCivID()
                                                                                                                                                   )
                                                                                                                                                   .getNumOfUnits()
                                                                                                                                                - CFG.game
                                                                                                                                                   .getProvince(
                                                                                                                                                      CFG.game
                                                                                                                                                         .getActiveProvinceID()
                                                                                                                                                   )
                                                                                                                                                   .getArmy(0)
                                                                                                                                          );
                                                                                                                                       CFG.game
                                                                                                                                          .getProvince(
                                                                                                                                             CFG.game
                                                                                                                                                .getActiveProvinceID()
                                                                                                                                          )
                                                                                                                                          .updateArmy(tArmy);
                                                                                                                                       CFG.game
                                                                                                                                          .getCiv(
                                                                                                                                             CFG.game
                                                                                                                                                .getProvince(
                                                                                                                                                   CFG.game
                                                                                                                                                      .getActiveProvinceID()
                                                                                                                                                )
                                                                                                                                                .getCivID()
                                                                                                                                          )
                                                                                                                                          .setNumOfUnits(
                                                                                                                                             CFG.game
                                                                                                                                                   .getCiv(
                                                                                                                                                      CFG.game
                                                                                                                                                         .getProvince(
                                                                                                                                                            CFG.game
                                                                                                                                                               .getActiveProvinceID()
                                                                                                                                                         )
                                                                                                                                                         .getCivID()
                                                                                                                                                   )
                                                                                                                                                   .getNumOfUnits()
                                                                                                                                                + tArmy
                                                                                                                                          );
                                                                                                                                       addMessage(
                                                                                                                                          cheatMess()
                                                                                                                                             + CFG.langManager
                                                                                                                                                .get("Army")
                                                                                                                                             + ": "
                                                                                                                                             + tArmy
                                                                                                                                       );
                                                                                                                                       addMessage("");
                                                                                                                                       int tActiveProvince = CFG.game
                                                                                                                                          .getActiveProvinceID();
                                                                                                                                       CFG.game
                                                                                                                                          .setActiveProvinceID(
                                                                                                                                             -1
                                                                                                                                          );
                                                                                                                                       CFG.game
                                                                                                                                          .setActiveProvinceID(
                                                                                                                                             tActiveProvince
                                                                                                                                          );
                                                                                                                                       CFG.toast
                                                                                                                                          .setInView(
                                                                                                                                             cheatMess()
                                                                                                                                                + CFG.langManager
                                                                                                                                                   .get("Army")
                                                                                                                                          );
                                                                                                                                    } else {
                                                                                                                                       IllegalCommand();
                                                                                                                                       CFG.toast
                                                                                                                                          .setInView(
                                                                                                                                             CFG.langManager
                                                                                                                                                .get(
                                                                                                                                                   "ChooseAProvince"
                                                                                                                                                ),
                                                                                                                                             CFG.COLOR_TEXT_MODIFIER_NEGATIVE2
                                                                                                                                          );
                                                                                                                                       addMessage(
                                                                                                                                          CFG.langManager
                                                                                                                                             .get(
                                                                                                                                                CFG.langManager
                                                                                                                                                   .get(
                                                                                                                                                      "ChooseAProvince"
                                                                                                                                                   )
                                                                                                                                             )
                                                                                                                                       );
                                                                                                                                       addMessage("");
                                                                                                                                    }

                                                                                                                                    return;
                                                                                                                                 }

                                                                                                                                 CFG.game
                                                                                                                                    .getProvince(
                                                                                                                                       CFG.game
                                                                                                                                          .getActiveProvinceID()
                                                                                                                                    )
                                                                                                                                    .setTrueOwnerOfProvince(
                                                                                                                                       CFG.game
                                                                                                                                          .getActiveCivID()
                                                                                                                                    );
                                                                                                                                 CFG.game
                                                                                                                                    .getProvince(
                                                                                                                                       CFG.game
                                                                                                                                          .getActiveProvinceID()
                                                                                                                                    )
                                                                                                                                    .setCivID(
                                                                                                                                       CFG.game
                                                                                                                                          .getActiveCivID(),
                                                                                                                                       true
                                                                                                                                    );
                                                                                                                                 addMessage(
                                                                                                                                    cheatMess()
                                                                                                                                       + CFG.langManager
                                                                                                                                          .get(
                                                                                                                                             "Province annexed"
                                                                                                                                          )
                                                                                                                                 );
                                                                                                                                 addMessage("");
                                                                                                                                 return;
                                                                                                                              }

                                                                                                                              CFG.game
                                                                                                                                 .getCiv(
                                                                                                                                    CFG.game
                                                                                                                                       .getPlayer(
                                                                                                                                          CFG.PLAYER_TURNID
                                                                                                                                       )
                                                                                                                                       .getCivID()
                                                                                                                                 )
                                                                                                                                 .setModifier_AttackBonus(2.0F);
                                                                                                                              CFG.game
                                                                                                                                 .getCiv(
                                                                                                                                    CFG.game
                                                                                                                                       .getPlayer(
                                                                                                                                          CFG.PLAYER_TURNID
                                                                                                                                       )
                                                                                                                                       .getCivID()
                                                                                                                                 )
                                                                                                                                 .setModifier_DefenseBonus(2.0F);
                                                                                                                              addMessage(
                                                                                                                                 cheatMess()
                                                                                                                                    + CFG.langManager
                                                                                                                                       .get(
                                                                                                                                          "Now your army is stronger!"
                                                                                                                                       )
                                                                                                                              );
                                                                                                                              return;
                                                                                                                           }

                                                                                                                           for (int i = 0;
                                                                                                                              i
                                                                                                                                 < CFG.game
                                                                                                                                    .getCiv(
                                                                                                                                       CFG.game
                                                                                                                                          .getProvince(
                                                                                                                                             CFG.game
                                                                                                                                                .getActiveProvinceID()
                                                                                                                                          )
                                                                                                                                          .getCivID()
                                                                                                                                    )
                                                                                                                                    .getNumOfProvinces();
                                                                                                                              i++
                                                                                                                           ) {
                                                                                                                              CFG.game
                                                                                                                                 .getProvince(
                                                                                                                                    CFG.game
                                                                                                                                       .getCiv(
                                                                                                                                          CFG.game
                                                                                                                                             .getProvince(
                                                                                                                                                CFG.game
                                                                                                                                                   .getActiveProvinceID()
                                                                                                                                             )
                                                                                                                                             .getCivID()
                                                                                                                                       )
                                                                                                                                       .getProvinceID(i)
                                                                                                                                 )
                                                                                                                                 .setRevolutionaryRisk(100.0F);
                                                                                                                           }

                                                                                                                           addMessage(
                                                                                                                              cheatMess()
                                                                                                                                 + CFG.langManager
                                                                                                                                    .get("REVOLUTION!!!")
                                                                                                                           );
                                                                                                                           return;
                                                                                                                        }

                                                                                                                        String message = tempCommand[1];
                                                                                                                        addMessage(
                                                                                                                           "ChatGPT: " + GPT.chatGPT(message)
                                                                                                                        );
                                                                                                                        return;
                                                                                                                     }

                                                                                                                     int tCiv = CFG.game
                                                                                                                        .getProvince(
                                                                                                                           CFG.game.getActiveProvinceID()
                                                                                                                        )
                                                                                                                        .getCivID();
                                                                                                                     int NumOfProvs = CFG.game
                                                                                                                        .getCiv(tCiv)
                                                                                                                        .getNumOfProvinces();
                                                                                                                     int[] ProvsID = new int[NumOfProvs];

                                                                                                                     for (int k = 0; k < NumOfProvs; k++) {
                                                                                                                        CFG.game
                                                                                                                           .getProvince(
                                                                                                                              CFG.game
                                                                                                                                 .getCiv(tCiv)
                                                                                                                                 .getProvinceID(k)
                                                                                                                           )
                                                                                                                           .setTrueOwnerOfProvince(0);
                                                                                                                        ProvsID[k] = CFG.game
                                                                                                                           .getCiv(tCiv)
                                                                                                                           .getProvinceID(k);
                                                                                                                     }

                                                                                                                     for (int l = 0; l < NumOfProvs; l++) {
                                                                                                                        CFG.game
                                                                                                                           .getProvince(ProvsID[l])
                                                                                                                           .setCivID(0, false);
                                                                                                                     }

                                                                                                                     addMessage(cheatMess() + "CountryDeleted");
                                                                                                                     return;
                                                                                                                  }

                                                                                                                  String tCiv = tempCommand[1];

                                                                                                                  try {
                                                                                                                     CFG.game
                                                                                                                        .getPlayer(0)
                                                                                                                        .loadPlayersFlag(
                                                                                                                           new Image(
                                                                                                                              new Texture(
                                                                                                                                 Gdx.files
                                                                                                                                    .internal(
                                                                                                                                       "game/flagsH/"
                                                                                                                                          + tCiv
                                                                                                                                          + ".png"
                                                                                                                                    )
                                                                                                                              ),
                                                                                                                              Texture.TextureFilter.Nearest
                                                                                                                           )
                                                                                                                        );
                                                                                                                     CFG.game
                                                                                                                        .getCiv(
                                                                                                                           CFG.game
                                                                                                                              .getPlayer(CFG.PLAYER_TURNID)
                                                                                                                              .getCivID()
                                                                                                                        )
                                                                                                                        .setFlag(
                                                                                                                           new Image(
                                                                                                                              new Texture(
                                                                                                                                 Gdx.files
                                                                                                                                    .internal(
                                                                                                                                       "game/flagsH/"
                                                                                                                                          + tCiv
                                                                                                                                          + ".png"
                                                                                                                                    )
                                                                                                                              ),
                                                                                                                              Texture.TextureFilter.Nearest
                                                                                                                           )
                                                                                                                        );
                                                                                                                  } catch (Exception var8) {
                                                                                                                     addMessage(
                                                                                                                        cheatMess() + "Flag not found :("
                                                                                                                     );
                                                                                                                  }

                                                                                                                  return;
                                                                                                               }

                                                                                                               for (int i = 0;
                                                                                                                  i < CFG.ideologiesManager.getIdeologiesSize();
                                                                                                                  i++
                                                                                                               ) {
                                                                                                                  addMessage(
                                                                                                                     cheatMess()
                                                                                                                        + CFG.ideologiesManager
                                                                                                                           .getIdeology(i)
                                                                                                                           .getName()
                                                                                                                        + ": "
                                                                                                                        + i
                                                                                                                  );
                                                                                                               }

                                                                                                               return;
                                                                                                            }

                                                                                                            int tIdeology = Integer.parseInt(tempCommand[1]);
                                                                                                            CFG.game
                                                                                                               .getCiv(
                                                                                                                  CFG.game
                                                                                                                     .getProvince(
                                                                                                                        CFG.game.getActiveProvinceID()
                                                                                                                     )
                                                                                                                     .getCivID()
                                                                                                               )
                                                                                                               .setIdeologyID(tIdeology);
                                                                                                            addMessage(cheatMess() + "Ideology chosen");
                                                                                                            return;
                                                                                                         }

                                                                                                         int tPopulation = Integer.parseInt(tempCommand[1]);
                                                                                                         int populationAdded = 0;

                                                                                                         for (int i = 0;
                                                                                                            i
                                                                                                               < CFG.game
                                                                                                                  .getCiv(
                                                                                                                     CFG.game
                                                                                                                        .getProvince(
                                                                                                                           CFG.game.getActiveProvinceID()
                                                                                                                        )
                                                                                                                        .getCivID()
                                                                                                                  )
                                                                                                                  .getNumOfProvinces();
                                                                                                            i++
                                                                                                         ) {
                                                                                                            int tProv = CFG.game
                                                                                                               .getCiv(
                                                                                                                  CFG.game
                                                                                                                     .getProvince(
                                                                                                                        CFG.game.getActiveProvinceID()
                                                                                                                     )
                                                                                                                     .getCivID()
                                                                                                               )
                                                                                                               .getProvinceID(i);
                                                                                                            CFG.game
                                                                                                               .getProvince(tProv)
                                                                                                               .getPopulationData()
                                                                                                               .setPopulationOfCivID(
                                                                                                                  CFG.game.getProvince(tProv).getCivID(),
                                                                                                                  tPopulation
                                                                                                                     + CFG.game
                                                                                                                        .getProvince(tProv)
                                                                                                                        .getPopulationData()
                                                                                                                        .getPopulationOfCivID(
                                                                                                                           CFG.game
                                                                                                                              .getProvince(tProv)
                                                                                                                              .getCivID()
                                                                                                                        )
                                                                                                               );
                                                                                                            populationAdded += tPopulation;
                                                                                                         }

                                                                                                         addMessage(
                                                                                                            cheatMess() + populationAdded + " - people added"
                                                                                                         );
                                                                                                         return;
                                                                                                      }

                                                                                                      for (int i = 0;
                                                                                                         i
                                                                                                            < CFG.map
                                                                                                               .getMapNumOfProvinces(CFG.map.getActiveMapID());
                                                                                                         i++
                                                                                                      ) {
                                                                                                         CFG.game
                                                                                                            .getProvince(i)
                                                                                                            .setTrueOwnerOfProvince(
                                                                                                               CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()
                                                                                                            );
                                                                                                         CFG.game
                                                                                                            .getProvince(i)
                                                                                                            .setCivID(
                                                                                                               CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(),
                                                                                                               true
                                                                                                            );
                                                                                                      }

                                                                                                      return;
                                                                                                   }

                                                                                                   int tempProv = CFG.game.getActiveProvinceID();
                                                                                                   if (!tempCommand[1].equals("farm")
                                                                                                      && !tempCommand[1].equals("ферма")) {
                                                                                                      if (!tempCommand[1].equals("fort")
                                                                                                         && !tempCommand[1].equals("форт")) {
                                                                                                         if (!tempCommand[1].equals("tower")
                                                                                                            && !tempCommand[1].equals("башня")) {
                                                                                                            if (!tempCommand[1].equals("arm")
                                                                                                               && !tempCommand[1].equals("арм")) {
                                                                                                               if (!tempCommand[1].equals("lib")
                                                                                                                  && !tempCommand[1].equals("учеба")) {
                                                                                                                  if (!tempCommand[1].equals("work")
                                                                                                                     && !tempCommand[1].equals("работа")) {
                                                                                                                     if (!tempCommand[1].equals("supp")
                                                                                                                        && !tempCommand[1].equals("снабжение")) {
                                                                                                                        if (!tempCommand[1].equals("all")
                                                                                                                           && !tempCommand[1].equals("все")
                                                                                                                           && !tempCommand[1].equals("всё")) {
                                                                                                                           addMessage(
                                                                                                                              cheatMess()
                                                                                                                                 + "What? You mean farm/fort/tower/arm/lib/work/supp/all ?"
                                                                                                                           );
                                                                                                                           return;
                                                                                                                        }

                                                                                                                        CFG.game
                                                                                                                           .getProvince(tempProv)
                                                                                                                           .setLevelOfFarm(
                                                                                                                              BuildingsManager.getFarm_MaxLevel()
                                                                                                                           );
                                                                                                                        CFG.game
                                                                                                                           .getProvince(tempProv)
                                                                                                                           .setLevelOfFort(
                                                                                                                              BuildingsManager.getFort_MaxLevel()
                                                                                                                           );
                                                                                                                        CFG.game
                                                                                                                           .getProvince(tempProv)
                                                                                                                           .setLevelOfWatchTower(
                                                                                                                              BuildingsManager.getTower_MaxLevel()
                                                                                                                           );
                                                                                                                        CFG.game
                                                                                                                           .getProvince(tempProv)
                                                                                                                           .setLevelOfArmoury(
                                                                                                                              BuildingsManager.getArmoury_MaxLevel()
                                                                                                                           );
                                                                                                                        CFG.game
                                                                                                                           .getProvince(tempProv)
                                                                                                                           .setLevelOfLibrary(
                                                                                                                              BuildingsManager.getLibrary_MaxLevel()
                                                                                                                           );
                                                                                                                        CFG.game
                                                                                                                           .getProvince(tempProv)
                                                                                                                           .setLevelOfWorkshop(
                                                                                                                              BuildingsManager.getWorkshop_MaxLevel()
                                                                                                                           );
                                                                                                                        CFG.game
                                                                                                                           .getProvince(tempProv)
                                                                                                                           .setLevelOfSupply(
                                                                                                                              BuildingsManager.getSupply_MaxLevel()
                                                                                                                           );
                                                                                                                        return;
                                                                                                                     }

                                                                                                                     CFG.game
                                                                                                                        .getProvince(tempProv)
                                                                                                                        .setLevelOfSupply(
                                                                                                                           BuildingsManager.getSupply_MaxLevel()
                                                                                                                        );
                                                                                                                     return;
                                                                                                                  }

                                                                                                                  CFG.game
                                                                                                                     .getProvince(tempProv)
                                                                                                                     .setLevelOfWorkshop(
                                                                                                                        BuildingsManager.getWorkshop_MaxLevel()
                                                                                                                     );
                                                                                                                  return;
                                                                                                               }

                                                                                                               CFG.game
                                                                                                                  .getProvince(tempProv)
                                                                                                                  .setLevelOfLibrary(
                                                                                                                     BuildingsManager.getLibrary_MaxLevel()
                                                                                                                  );
                                                                                                               return;
                                                                                                            }

                                                                                                            CFG.game
                                                                                                               .getProvince(tempProv)
                                                                                                               .setLevelOfArmoury(
                                                                                                                  BuildingsManager.getArmoury_MaxLevel()
                                                                                                               );
                                                                                                            return;
                                                                                                         }

                                                                                                         CFG.game
                                                                                                            .getProvince(tempProv)
                                                                                                            .setLevelOfWatchTower(
                                                                                                               BuildingsManager.getTower_MaxLevel()
                                                                                                            );
                                                                                                         return;
                                                                                                      }

                                                                                                      CFG.game
                                                                                                         .getProvince(tempProv)
                                                                                                         .setLevelOfFort(BuildingsManager.getFort_MaxLevel());
                                                                                                      return;
                                                                                                   }

                                                                                                   CFG.game
                                                                                                      .getProvince(tempProv)
                                                                                                      .setLevelOfFarm(BuildingsManager.getFarm_MaxLevel());
                                                                                                   return;
                                                                                                }

                                                                                                for (int i = 0;
                                                                                                   i
                                                                                                      < CFG.game
                                                                                                         .getCiv(
                                                                                                            CFG.game
                                                                                                               .getProvince(CFG.game.getActiveProvinceID())
                                                                                                               .getCivID()
                                                                                                         )
                                                                                                         .getNumOfProvinces();
                                                                                                   i++
                                                                                                ) {
                                                                                                   if (tempCommand[1].equals("farm")
                                                                                                      || tempCommand[1].equals("ферма")) {
                                                                                                      CFG.game
                                                                                                         .getProvince(
                                                                                                            CFG.game
                                                                                                               .getCiv(
                                                                                                                  CFG.game
                                                                                                                     .getProvince(
                                                                                                                        CFG.game.getActiveProvinceID()
                                                                                                                     )
                                                                                                                     .getCivID()
                                                                                                               )
                                                                                                               .getProvinceID(i)
                                                                                                         )
                                                                                                         .setLevelOfFarm(BuildingsManager.getFarm_MaxLevel());
                                                                                                   }

                                                                                                   if (tempCommand[1].equals("fort")
                                                                                                      || tempCommand[1].equals("форт")) {
                                                                                                      CFG.game
                                                                                                         .getProvince(
                                                                                                            CFG.game
                                                                                                               .getCiv(
                                                                                                                  CFG.game
                                                                                                                     .getProvince(
                                                                                                                        CFG.game.getActiveProvinceID()
                                                                                                                     )
                                                                                                                     .getCivID()
                                                                                                               )
                                                                                                               .getProvinceID(i)
                                                                                                         )
                                                                                                         .setLevelOfFort(BuildingsManager.getFort_MaxLevel());
                                                                                                   }

                                                                                                   if (tempCommand[1].equals("tower")
                                                                                                      || tempCommand[1].equals("башня")) {
                                                                                                      CFG.game
                                                                                                         .getProvince(
                                                                                                            CFG.game
                                                                                                               .getCiv(
                                                                                                                  CFG.game
                                                                                                                     .getProvince(
                                                                                                                        CFG.game.getActiveProvinceID()
                                                                                                                     )
                                                                                                                     .getCivID()
                                                                                                               )
                                                                                                               .getProvinceID(i)
                                                                                                         )
                                                                                                         .setLevelOfWatchTower(
                                                                                                            BuildingsManager.getTower_MaxLevel()
                                                                                                         );
                                                                                                   }

                                                                                                   if (tempCommand[1].equals("arm")
                                                                                                      || tempCommand[1].equals("арм")) {
                                                                                                      CFG.game
                                                                                                         .getProvince(
                                                                                                            CFG.game
                                                                                                               .getCiv(
                                                                                                                  CFG.game
                                                                                                                     .getProvince(
                                                                                                                        CFG.game.getActiveProvinceID()
                                                                                                                     )
                                                                                                                     .getCivID()
                                                                                                               )
                                                                                                               .getProvinceID(i)
                                                                                                         )
                                                                                                         .setLevelOfArmoury(
                                                                                                            BuildingsManager.getArmoury_MaxLevel()
                                                                                                         );
                                                                                                   }

                                                                                                   if (tempCommand[1].equals("lib")
                                                                                                      || tempCommand[1].equals("учеба")) {
                                                                                                      CFG.game
                                                                                                         .getProvince(
                                                                                                            CFG.game
                                                                                                               .getCiv(
                                                                                                                  CFG.game
                                                                                                                     .getProvince(
                                                                                                                        CFG.game.getActiveProvinceID()
                                                                                                                     )
                                                                                                                     .getCivID()
                                                                                                               )
                                                                                                               .getProvinceID(i)
                                                                                                         )
                                                                                                         .setLevelOfLibrary(
                                                                                                            BuildingsManager.getLibrary_MaxLevel()
                                                                                                         );
                                                                                                   }

                                                                                                   if (tempCommand[1].equals("work")
                                                                                                      || tempCommand[1].equals("работа")) {
                                                                                                      CFG.game
                                                                                                         .getProvince(
                                                                                                            CFG.game
                                                                                                               .getCiv(
                                                                                                                  CFG.game
                                                                                                                     .getProvince(
                                                                                                                        CFG.game.getActiveProvinceID()
                                                                                                                     )
                                                                                                                     .getCivID()
                                                                                                               )
                                                                                                               .getProvinceID(i)
                                                                                                         )
                                                                                                         .setLevelOfWorkshop(
                                                                                                            BuildingsManager.getWorkshop_MaxLevel()
                                                                                                         );
                                                                                                   }

                                                                                                   if (tempCommand[1].equals("supp")
                                                                                                      || tempCommand[1].equals("снабжение")) {
                                                                                                      CFG.game
                                                                                                         .getProvince(
                                                                                                            CFG.game
                                                                                                               .getCiv(
                                                                                                                  CFG.game
                                                                                                                     .getProvince(
                                                                                                                        CFG.game.getActiveProvinceID()
                                                                                                                     )
                                                                                                                     .getCivID()
                                                                                                               )
                                                                                                               .getProvinceID(i)
                                                                                                         )
                                                                                                         .setLevelOfSupply(
                                                                                                            BuildingsManager.getSupply_MaxLevel()
                                                                                                         );
                                                                                                   }

                                                                                                   if (!tempCommand[1].equals("all")
                                                                                                      && !tempCommand[1].equals("всё")
                                                                                                      && !tempCommand[1].equals("все")) {
                                                                                                      addMessage(
                                                                                                         cheatMess()
                                                                                                            + "What? You mean farm/fort/tower/arm/lib/work/supp/all ?"
                                                                                                      );
                                                                                                   } else {
                                                                                                      CFG.game
                                                                                                         .getProvince(
                                                                                                            CFG.game
                                                                                                               .getCiv(
                                                                                                                  CFG.game
                                                                                                                     .getProvince(
                                                                                                                        CFG.game.getActiveProvinceID()
                                                                                                                     )
                                                                                                                     .getCivID()
                                                                                                               )
                                                                                                               .getProvinceID(i)
                                                                                                         )
                                                                                                         .setLevelOfFarm(BuildingsManager.getFarm_MaxLevel());
                                                                                                      CFG.game
                                                                                                         .getProvince(
                                                                                                            CFG.game
                                                                                                               .getCiv(
                                                                                                                  CFG.game
                                                                                                                     .getProvince(
                                                                                                                        CFG.game.getActiveProvinceID()
                                                                                                                     )
                                                                                                                     .getCivID()
                                                                                                               )
                                                                                                               .getProvinceID(i)
                                                                                                         )
                                                                                                         .setLevelOfFort(BuildingsManager.getFort_MaxLevel());
                                                                                                      CFG.game
                                                                                                         .getProvince(
                                                                                                            CFG.game
                                                                                                               .getCiv(
                                                                                                                  CFG.game
                                                                                                                     .getProvince(
                                                                                                                        CFG.game.getActiveProvinceID()
                                                                                                                     )
                                                                                                                     .getCivID()
                                                                                                               )
                                                                                                               .getProvinceID(i)
                                                                                                         )
                                                                                                         .setLevelOfWatchTower(
                                                                                                            BuildingsManager.getTower_MaxLevel()
                                                                                                         );
                                                                                                      CFG.game
                                                                                                         .getProvince(
                                                                                                            CFG.game
                                                                                                               .getCiv(
                                                                                                                  CFG.game
                                                                                                                     .getProvince(
                                                                                                                        CFG.game.getActiveProvinceID()
                                                                                                                     )
                                                                                                                     .getCivID()
                                                                                                               )
                                                                                                               .getProvinceID(i)
                                                                                                         )
                                                                                                         .setLevelOfArmoury(
                                                                                                            BuildingsManager.getArmoury_MaxLevel()
                                                                                                         );
                                                                                                      CFG.game
                                                                                                         .getProvince(
                                                                                                            CFG.game
                                                                                                               .getCiv(
                                                                                                                  CFG.game
                                                                                                                     .getProvince(
                                                                                                                        CFG.game.getActiveProvinceID()
                                                                                                                     )
                                                                                                                     .getCivID()
                                                                                                               )
                                                                                                               .getProvinceID(i)
                                                                                                         )
                                                                                                         .setLevelOfLibrary(
                                                                                                            BuildingsManager.getLibrary_MaxLevel()
                                                                                                         );
                                                                                                      CFG.game
                                                                                                         .getProvince(
                                                                                                            CFG.game
                                                                                                               .getCiv(
                                                                                                                  CFG.game
                                                                                                                     .getProvince(
                                                                                                                        CFG.game.getActiveProvinceID()
                                                                                                                     )
                                                                                                                     .getCivID()
                                                                                                               )
                                                                                                               .getProvinceID(i)
                                                                                                         )
                                                                                                         .setLevelOfWorkshop(
                                                                                                            BuildingsManager.getWorkshop_MaxLevel()
                                                                                                         );
                                                                                                      CFG.game
                                                                                                         .getProvince(
                                                                                                            CFG.game
                                                                                                               .getCiv(
                                                                                                                  CFG.game
                                                                                                                     .getProvince(
                                                                                                                        CFG.game.getActiveProvinceID()
                                                                                                                     )
                                                                                                                     .getCivID()
                                                                                                               )
                                                                                                               .getProvinceID(i)
                                                                                                         )
                                                                                                         .setLevelOfSupply(
                                                                                                            BuildingsManager.getSupply_MaxLevel()
                                                                                                         );
                                                                                                   }
                                                                                                }

                                                                                                return;
                                                                                             }

                                                                                             int tArmy = Integer.parseInt(tempCommand[1]);

                                                                                             for (int i = 0;
                                                                                                i
                                                                                                   < CFG.game
                                                                                                      .getCiv(
                                                                                                         CFG.game
                                                                                                            .getProvince(CFG.game.getActiveProvinceID())
                                                                                                            .getCivID()
                                                                                                      )
                                                                                                      .getNumOfProvinces();
                                                                                                i++
                                                                                             ) {
                                                                                                CFG.game
                                                                                                   .getProvince(
                                                                                                      CFG.game
                                                                                                         .getCiv(
                                                                                                            CFG.game
                                                                                                               .getProvince(CFG.game.getActiveProvinceID())
                                                                                                               .getCivID()
                                                                                                         )
                                                                                                         .getProvinceID(i)
                                                                                                   )
                                                                                                   .updateArmy(tArmy);
                                                                                             }

                                                                                             addMessage(cheatMess() + "Army added");
                                                                                             return;
                                                                                          }

                                                                                          int iCivID = Integer.parseInt(tempCommand[1]);
                                                                                          CFG.game
                                                                                             .getProvince(CFG.game.getActiveProvinceID())
                                                                                             .updateArmy(iCivID, 500);
                                                                                          addMessage(cheatMess() + "Army added");
                                                                                          return;
                                                                                       }

                                                                                       int tPopulation = Integer.parseInt(tempCommand[1]);
                                                                                       if (CFG.game.getActiveProvinceID() >= 0
                                                                                          && CFG.game
                                                                                                .getProvince(CFG.game.getActiveProvinceID())
                                                                                                .getWasteland()
                                                                                             < 0
                                                                                          && !CFG.game
                                                                                             .getProvince(CFG.game.getActiveProvinceID())
                                                                                             .getSeaProvince()) {
                                                                                          CFG.game
                                                                                             .getProvince(CFG.game.getActiveProvinceID())
                                                                                             .getPopulationData()
                                                                                             .setPopulationOfCivID(
                                                                                                CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID(),
                                                                                                tPopulation
                                                                                                   + CFG.game
                                                                                                      .getProvince(CFG.game.getActiveProvinceID())
                                                                                                      .getPopulationData()
                                                                                                      .getPopulationOfCivID(
                                                                                                         CFG.game
                                                                                                            .getProvince(CFG.game.getActiveProvinceID())
                                                                                                            .getCivID()
                                                                                                      )
                                                                                             );
                                                                                          addMessage(
                                                                                             cheatMess()
                                                                                                + CFG.langManager.get("Population")
                                                                                                + ": +"
                                                                                                + tPopulation
                                                                                          );
                                                                                          addMessage("");
                                                                                          int tActiveProvince = CFG.game.getActiveProvinceID();
                                                                                          CFG.game.setActiveProvinceID(-1);
                                                                                          CFG.game.setActiveProvinceID(tActiveProvince);
                                                                                          CFG.toast.setInView(cheatMess() + CFG.langManager.get("Population"));
                                                                                          if (CFG.menuManager.getVisibleInGame_CensusOfProvince()) {
                                                                                             CFG.menuManager
                                                                                                .rebuildInGame_CensusOfProvince(CFG.game.getActiveProvinceID());
                                                                                          }
                                                                                       } else {
                                                                                          IllegalCommand();
                                                                                          CFG.toast
                                                                                             .setInView(
                                                                                                CFG.langManager.get("ChooseAProvince"),
                                                                                                CFG.COLOR_TEXT_MODIFIER_NEGATIVE2
                                                                                             );
                                                                                          addMessage(
                                                                                             CFG.langManager.get(CFG.langManager.get("ChooseAProvince"))
                                                                                          );
                                                                                          addMessage("");
                                                                                       }

                                                                                       return;
                                                                                    }

                                                                                    if (CFG.game.getActiveProvinceID() < 0
                                                                                       || CFG.game.getProvince(CFG.game.getActiveProvinceID()).getWasteland()
                                                                                          >= 0
                                                                                       || CFG.game.getProvince(CFG.game.getActiveProvinceID()).getSeaProvince()
                                                                                       )
                                                                                     {
                                                                                       IllegalCommand();
                                                                                       CFG.toast
                                                                                          .setInView(
                                                                                             CFG.langManager.get("ChooseAProvince"),
                                                                                             CFG.COLOR_TEXT_MODIFIER_NEGATIVE2
                                                                                          );
                                                                                       addMessage(CFG.langManager.get(CFG.langManager.get("ChooseAProvince")));
                                                                                       addMessage("");
                                                                                    } else if (tempCommand.length > 1) {
                                                                                       try {
                                                                                          int tempTech = Integer.parseInt(tempCommand[1]);
                                                                                          if (tempTech > 100) {
                                                                                             tempTech = 100;
                                                                                          } else if (tempTech < 1) {
                                                                                             tempTech = 1;
                                                                                          }

                                                                                          CFG.game
                                                                                             .getCiv(
                                                                                                CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()
                                                                                             )
                                                                                             .setTechnologyLevel(tempTech / 100.0F);
                                                                                          addMessage(
                                                                                             cheatMess()
                                                                                                + CFG.langManager.get("Technology")
                                                                                                + ": "
                                                                                                + CFG.game
                                                                                                   .getCiv(
                                                                                                      CFG.game
                                                                                                         .getProvince(CFG.game.getActiveProvinceID())
                                                                                                         .getCivID()
                                                                                                   )
                                                                                                   .getTechnologyLevel()
                                                                                                + ", "
                                                                                                + CFG.game
                                                                                                   .getCiv(
                                                                                                      CFG.game
                                                                                                         .getProvince(CFG.game.getActiveProvinceID())
                                                                                                         .getCivID()
                                                                                                   )
                                                                                                   .getCivName()
                                                                                          );
                                                                                          addMessage("");
                                                                                          int tActiveProvince = CFG.game.getActiveProvinceID();
                                                                                          CFG.game.setActiveProvinceID(-1);
                                                                                          CFG.game.setActiveProvinceID(tActiveProvince);
                                                                                          CFG.toast.setInView(cheatMess() + CFG.langManager.get("Technology"));
                                                                                       } catch (IllegalArgumentException var9) {
                                                                                          IllegalCommand();
                                                                                       }
                                                                                    } else {
                                                                                       IllegalCommand();
                                                                                    }

                                                                                    return;
                                                                                 }

                                                                                 if (tempCommand.length <= 1) {
                                                                                    IllegalCommand();
                                                                                 } else if (CFG.game.getActiveProvinceID() >= 0
                                                                                    && !CFG.game.getProvince(CFG.game.getActiveProvinceID()).getSeaProvince()
                                                                                    && CFG.game.getProvince(CFG.game.getActiveProvinceID()).getWasteland() < 0
                                                                                    && !CFG.game.getProvince(CFG.game.getActiveProvinceID()).getIsCapital()) {
                                                                                    for (int i = 1; i < CFG.game.getCivsSize(); i++) {
                                                                                       if (CFG.game.getCiv(i).getCivTag().equals(tempCommand[1])) {
                                                                                          IllegalCommand();
                                                                                          addMessage(CFG.game.getCiv(i).getCivName() + ": IS IN THE GAME");
                                                                                          addMessage("");
                                                                                          return;
                                                                                       }
                                                                                    }

                                                                                    CFG.game
                                                                                       .getProvince(CFG.game.getActiveProvinceID())
                                                                                       .updateArmy(
                                                                                          CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID(0), 0
                                                                                       );
                                                                                    CFG.game
                                                                                       .createScenarioAddCivilization(
                                                                                          tempCommand[1], CFG.game.getActiveProvinceID(), false, true, true
                                                                                       );
                                                                                    if (CFG.FOG_OF_WAR == 2) {
                                                                                       for (int var24 = 0; var24 < CFG.game.getPlayersSize(); var24++) {
                                                                                          CFG.game.getPlayer(var24).addMetCivilization(true);
                                                                                       }
                                                                                    }

                                                                                    int tempPop = CFG.game
                                                                                       .getProvince(CFG.game.getActiveProvinceID())
                                                                                       .getPopulationData()
                                                                                       .getPopulation();
                                                                                    CFG.game
                                                                                       .getProvince(CFG.game.getActiveProvinceID())
                                                                                       .getPopulationData()
                                                                                       .clearData();
                                                                                    CFG.game
                                                                                       .getProvince(CFG.game.getActiveProvinceID())
                                                                                       .getPopulationData()
                                                                                       .setPopulationOfCivID(
                                                                                          CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID(),
                                                                                          tempPop
                                                                                       );
                                                                                    CFG.game
                                                                                       .getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID())
                                                                                       .setMoney(100L);
                                                                                    CFG.gameAction
                                                                                       .updateCivsMovementPoints(
                                                                                          CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()
                                                                                       );
                                                                                    CFG.gameAction
                                                                                       .updateCivsDiplomacyPoints(
                                                                                          CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()
                                                                                       );
                                                                                    CFG.gameAction
                                                                                       .buildRank_Score(
                                                                                          CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()
                                                                                       );
                                                                                    int tActiveProvince = CFG.game.getActiveProvinceID();
                                                                                    CFG.game.setActiveProvinceID(-1);
                                                                                    CFG.game.setActiveProvinceID(tActiveProvince);
                                                                                    addMessage(
                                                                                       CFG.langManager.get("Added")
                                                                                          + ": "
                                                                                          + CFG.game
                                                                                             .getCiv(
                                                                                                CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()
                                                                                             )
                                                                                             .getCivName()
                                                                                    );
                                                                                 } else {
                                                                                    IllegalCommand();
                                                                                    CFG.toast
                                                                                       .setInView(
                                                                                          CFG.langManager.get("ChooseAProvince"),
                                                                                          CFG.COLOR_TEXT_MODIFIER_NEGATIVE2
                                                                                       );
                                                                                    addMessage(CFG.langManager.get(CFG.langManager.get("ChooseAProvince")));
                                                                                    addMessage("");
                                                                                 }

                                                                                 return;
                                                                              }

                                                                              if (CFG.game.getActiveProvinceID() >= 0
                                                                                 && !CFG.game.getProvince(CFG.game.getActiveProvinceID()).getSeaProvince()
                                                                                 && CFG.game.getProvince(CFG.game.getActiveProvinceID()).getWasteland() < 0
                                                                                 && CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID() > 0
                                                                                 && !CFG.game
                                                                                    .getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID())
                                                                                    .getControlledByPlayer()) {
                                                                                 if (CFG.SPECTATOR_MODE) {
                                                                                    CFG.SPECTATOR_MODE = false;
                                                                                    if (CFG.game.getPlayersSize() == 1) {
                                                                                       CFG.game.removePlayer(0);
                                                                                    }
                                                                                 }

                                                                                 CFG.game
                                                                                    .addPlayer(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID());
                                                                                 CFG.gameAction.buildFogOfWar(CFG.game.getPlayersSize() - 1);
                                                                                 if (CFG.FOG_OF_WAR == 2) {
                                                                                    CFG.game
                                                                                       .getPlayer(CFG.game.getPlayersSize() - 1)
                                                                                       .buildMetProvincesAndCivs();
                                                                                 }

                                                                                 CFG.game.getPlayer(CFG.game.getPlayersSize() - 1).loadPlayersFlag();
                                                                                 addMessage(
                                                                                    CFG.langManager.get("Added")
                                                                                       + ": "
                                                                                       + CFG.game
                                                                                          .getCiv(
                                                                                             CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()
                                                                                          )
                                                                                          .getCivName()
                                                                                 );
                                                                                 return;
                                                                              }

                                                                              IllegalCommand();
                                                                              CFG.toast
                                                                                 .setInView(
                                                                                    CFG.langManager.get("ChooseAProvince"), CFG.COLOR_TEXT_MODIFIER_NEGATIVE2
                                                                                 );
                                                                              addMessage(CFG.langManager.get(CFG.langManager.get("ChooseAProvince")));
                                                                              addMessage("");
                                                                              break label1325;
                                                                           }

                                                                           CFG.game.buildDrawArmy();
                                                                           return;
                                                                        }

                                                                        CFG.game.buildDrawArmy_ShowIDs();
                                                                        CFG.toast.setInView("showarmy");
                                                                        CFG.toast.setTimeInView(4500);
                                                                        addMessage(CFG.langManager.get("Disable") + ": showarmy");
                                                                        return;
                                                                     }

                                                                     if (CFG.game.getActiveProvinceID() >= 0
                                                                        && !CFG.game.getProvince(CFG.game.getActiveProvinceID()).getSeaProvince()
                                                                        && CFG.game.getProvince(CFG.game.getActiveProvinceID()).getWasteland() < 0) {
                                                                        addMessage(
                                                                           "PROVINCE ID: "
                                                                              + CFG.game.getActiveProvinceID()
                                                                              + ", CIV TAG"
                                                                              + CFG.game
                                                                                 .getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID())
                                                                                 .getCivTag()
                                                                        );
                                                                        addMessage(
                                                                           "POPULATION: "
                                                                              + CFG.game
                                                                                 .getProvince(CFG.game.getActiveProvinceID())
                                                                                 .getPopulationData()
                                                                                 .getPopulation()
                                                                              + ", ECONOMY"
                                                                              + CFG.game.getProvince(CFG.game.getActiveProvinceID()).getEconomy()
                                                                        );
                                                                     } else {
                                                                        IllegalCommand();
                                                                        CFG.toast
                                                                           .setInView(CFG.langManager.get("ChooseAProvince"), CFG.COLOR_TEXT_MODIFIER_NEGATIVE2);
                                                                        addMessage(CFG.langManager.get(CFG.langManager.get("ChooseAProvince")));
                                                                        addMessage("");
                                                                     }

                                                                     return;
                                                                  }

                                                                  if (CFG.game.getActiveProvinceID() >= 0
                                                                     && !CFG.game.getProvince(CFG.game.getActiveProvinceID()).getSeaProvince()
                                                                     && CFG.game.getProvince(CFG.game.getActiveProvinceID()).getWasteland() < 0) {
                                                                     addMessage(
                                                                        "CIV ID: "
                                                                           + CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()
                                                                           + ", TAG: "
                                                                           + CFG.game
                                                                              .getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID())
                                                                              .getCivTag()
                                                                           + ", "
                                                                           + CFG.game
                                                                              .getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID())
                                                                              .getCivName()
                                                                     );
                                                                  } else {
                                                                     IllegalCommand();
                                                                     CFG.toast
                                                                        .setInView(CFG.langManager.get("ChooseAProvince"), CFG.COLOR_TEXT_MODIFIER_NEGATIVE2);
                                                                     addMessage(CFG.langManager.get(CFG.langManager.get("ChooseAProvince")));
                                                                     addMessage("");
                                                                  }

                                                                  return;
                                                               }

                                                               for (int ix = 1; ix < CFG.game.getCivsSize(); ix++) {
                                                                  addMessage(
                                                                     "CIV ID: "
                                                                        + ix
                                                                        + ", TAG: "
                                                                        + CFG.game.getCiv(ix).getCivTag()
                                                                        + ", "
                                                                        + CFG.game.getCiv(ix).getCivName()
                                                                  );
                                                               }

                                                               return;
                                                            }

                                                            CFG.toast.setInView("Games -> New Game -> Options -> Spectactor Mode");
                                                            CFG.toast.setTimeInView(4500);
                                                            addMessage("Games -> New Game -> Options -> Spectator Mode");
                                                            return;
                                                         }

                                                         sConsole.clear();
                                                         lShit.clear();
                                                         return;
                                                      }

                                                      if (!CFG.menuManager.getVisible_InGame_FlagAction_Console()) {
                                                         CFG.menuManager.setVisible_InGame_FlagAction_Console(true);
                                                      }

                                                      Random oR = new Random();
                                                      lShit.clear();

                                                      for (int ix = 0; ix < CFG.GAME_WIDTH + CFG.GAME_HEIGHT; ix++) {
                                                         lShit.add(new Point_XY(oR.nextInt(CFG.GAME_WIDTH), oR.nextInt(CFG.GAME_HEIGHT)));
                                                      }

                                                      lShitTime = System.currentTimeMillis();
                                                      CFG.toast.setInView(CFG.langManager.get("clear"));
                                                      CFG.menuManager.getKeyboard().setVisible(false);
                                                      return;
                                                   }

                                                   if (!CFG.menuManager.getVisible_InGame_FlagAction_Console()) {
                                                      CFG.menuManager.setVisible_InGame_FlagAction_Console(true);
                                                   }

                                                   CFG.toast.setInView(CFG.langManager.get("Help"));
                                                   addMessage("#" + CFG.sVERSION + ": 1.3.1");
                                                   addMessage("");
                                                   addMessage("console");
                                                   addMessage("close");
                                                   addMessage("civ");
                                                   addMessage("civs");
                                                   addMessage("province");
                                                   addMessage("center X");
                                                   addMessage("centerciv X");
                                                   addMessage("scale X");
                                                   return;
                                                }

                                                CFG.map.getMapScroll().setScrollPos(125000, 10);
                                                CFG.map.getMapScroll().setScrollPos(10, 10);
                                                CFG.menuManager.getKeyboard().setVisible(false);
                                                CFG.menuManager.setVisible_InGame_FlagAction(false);
                                                CFG.map.getMapScroll().startScrollingTheMap();
                                                CFG.toast.setInView(CFG.langManager.get("Wheee") + "!");
                                                addMessage(CFG.langManager.get("Wheee") + "!");
                                                return;
                                             }

                                             if (!CFG.menuManager.getVisible_InGame_FlagAction_Console()) {
                                                CFG.menuManager.setVisible_InGame_FlagAction_Console(true);
                                             }

                                             CFG.toast
                                                .setInView(
                                                   CFG.langManager.get("Ну привет мистир игрок") + "!\nТолько не сломай мир, пжпжпж :(",
                                                   CFG.COLOR_TEXT_MODIFIER_NEGATIVE2
                                                );
                                             addMessage(CFG.langManager.get("Только не сломай мир, пжпжпж :("));
                                             addMessage(CFG.langManager.get("Ну привет мистир игрок") + "!)");
                                             return;
                                          }

                                          if (!CFG.menuManager.getVisible_InGame_FlagAction_Console()) {
                                             CFG.menuManager.setVisible_InGame_FlagAction_Console(true);
                                          }

                                          CFG.toast.setInView(CFG.langManager.get("Hi mr. ProPlayer") + "!");
                                          addMessage(CFG.langManager.get("Hi mr.ProPlayer") + "!)");
                                          return;
                                       }

                                       AoCGame.drawFPS = !AoCGame.drawFPS;
                                       return;
                                    }

                                    if (CFG.menuManager.getVisible_InGame_FlagAction_Console()) {
                                       CFG.menuManager.setVisible_InGame_FlagAction_Console(false);
                                    }

                                    CFG.menuManager.getKeyboard().setVisible(false);
                                    return;
                                 }

                                 if (tempCommand.length > 1) {
                                    try {
                                       tempCommand[1] = tempCommand[1].replace(',', '.');
                                       float tempS = Float.parseFloat(tempCommand[1]);
                                       CFG.map.getMapScale().setCurrentScale(tempS);
                                       return;
                                    } catch (IllegalArgumentException var12) {
                                       IllegalCommand();
                                    }
                                 } else {
                                    CFG.map.getMapScale().setCurrentScale(1.0F);
                                 }

                                 return;
                              }

                              if (tempCommand.length > 1) {
                                 try {
                                    int tempID = Integer.parseInt(tempCommand[1]);
                                    if (tempID < CFG.game.getCivsSize() && tempID > 0) {
                                       CFG.map.getMapCoordinates().centerToCivilizationBox(tempID, true);
                                       CFG.toast.setInView(CFG.game.getCiv(tempID).getCivName());
                                    }
                                 } catch (IllegalArgumentException var13) {
                                    for (int ix = 1; ix < CFG.game.getCivsSize(); ix++) {
                                       if (tempCommand[1].equals(CFG.game.getCiv(ix).getCivName()) || tempCommand[1].equals(CFG.game.getCiv(ix).getCivTag())) {
                                          CFG.map.getMapCoordinates().centerToCivilizationBox(ix, true);
                                          CFG.toast.setInView(CFG.game.getCiv(ix).getCivName());
                                          return;
                                       }
                                    }

                                    IllegalCommand();
                                 } catch (IndexOutOfBoundsException var14) {
                                    IllegalCommand();
                                 }
                              } else {
                                 IllegalCommand();
                              }

                              return;
                           }

                           if (tempCommand.length > 1) {
                              try {
                                 int tempID = Integer.parseInt(tempCommand[1]);
                                 if (tempID < CFG.game.getProvincesSize()) {
                                    CFG.map.getMapCoordinates().centerToProvinceID(tempID);
                                    CFG.game.setActiveProvinceID(tempID);
                                    CFG.toast.setInView(CFG.game.getProvince(tempID).getName());
                                 } else {
                                    IllegalCommand();
                                 }

                                 return;
                              } catch (IllegalArgumentException var10) {
                                 IllegalCommand();
                              } catch (IndexOutOfBoundsException var11) {
                                 IllegalCommand();
                              }
                           } else {
                              CFG.map.getMapScroll().stopScrollingTheMap();
                              CFG.map.getMapScale().setCurrentScale(Map_Scale.MINSCALE);
                              CFG.map.getMapCoordinates().setNewPosX(-((int)(CFG.map.getMapBG().getWidth() / 2 - CFG.GAME_WIDTH / Map_Scale.MINSCALE / 2.0F)));
                              CFG.map
                                 .getMapCoordinates()
                                 .setNewPosY(-((int)(CFG.map.getMapBG().getHeight() / 2 - CFG.GAME_HEIGHT / Map_Scale.MINSCALE / 2.0F)));
                           }

                           return;
                        }

                        for (int ixx = 0; ixx < CFG.game.getProvincesSize(); ixx++) {
                           if (CFG.game.getProvince(ixx).getWasteland() < 0
                              && CFG.game.getProvince(ixx).getCivID() == 0
                              && !CFG.game.getProvince(ixx).getSeaProvince()) {
                              CFG.game.setActiveProvinceID(ixx);
                              CFG.map.getMapCoordinates().centerToProvinceID(CFG.game.getActiveProvinceID());
                              break;
                           }
                        }

                        return;
                     }

                     CFG.DEBUG_MODE = !CFG.DEBUG_MODE;
                     addMessage(
                        CFG.langManager
                           .get(CFG.langManager.get("DEBUG") + ": " + (CFG.DEBUG_MODE ? CFG.langManager.get("Enabled") : CFG.langManager.get("Disabled")))
                     );
                     CFG.toast
                        .setInView(
                           CFG.langManager
                              .get(CFG.langManager.get("DEBUG") + ": " + (CFG.DEBUG_MODE ? CFG.langManager.get("Enabled") : CFG.langManager.get("Disabled")))
                        );
                     return;
                  }

                  addMessage("FramesPerSecond: " + Gdx.graphics.getFramesPerSecond());
                  addMessage("Width: " + Gdx.graphics.getWidth());
                  addMessage("Height: " + Gdx.graphics.getHeight());
                  addMessage("PpiX: " + Gdx.graphics.getPpiX());
                  addMessage("PpiY: " + Gdx.graphics.getPpiY());
                  addMessage("Density: " + Gdx.graphics.getDensity());
                  addMessage("XHDPI: " + CFG.XHDPI);
                  addMessage("XXHDPI: " + CFG.XXHDPI);
                  addMessage("XXXHDPI: " + CFG.XXXHDPI);
                  addMessage("XXXXHDPI: " + CFG.XXXXHDPI);
                  return;
               }

               CFG.menuManager.setVisible_InGame_FlagAction_Console(!CFG.menuManager.getVisible_InGame_FlagAction_Console());
               if (CFG.menuManager.getVisible_InGame_FlagAction_Console()) {
                  CFG.toast.setInView("Hello");
               }

               return;
            }
         } catch (IndexOutOfBoundsException var15) {
            CFG.exceptionStack(var15);
         } catch (NumberFormatException var16) {
            CFG.exceptionStack(var16);
         } catch (IllegalArgumentException var17) {
            CFG.exceptionStack(var17);
         }

         IllegalCommand();
      }
   }

   public static final String cheatMess() {
      return "[" + CFG.langManager.get("Cheat") + "] ";
   }

   public static final void IllegalCommand() {
      addMessage("# -- " + CFG.langManager.get("UnknownCommand"));
      CFG.toast.setInView("# -- " + CFG.langManager.get("UnknownCommand"), CFG.COLOR_TEXT_MODIFIER_NEGATIVE2);
      addMessage("");
   }
}
