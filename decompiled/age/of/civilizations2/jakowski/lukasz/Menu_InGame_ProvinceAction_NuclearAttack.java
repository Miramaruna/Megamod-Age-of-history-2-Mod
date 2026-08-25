package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.utils.ScissorStack;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

class Menu_InGame_ProvinceAction_NuclearAttack extends SliderMenu {
   protected Menu_InGame_ProvinceAction_NuclearAttack() {
      ArrayList var1 = new ArrayList();
      var1.add(
         new Button_Game_NuclearAttack(
            this,
            null,
            CFG.game.getActiveProvinceID(),
            CFG.PADDING,
            CFG.PADDING,
            Game_Calendar.getCanColonize_TechLevel(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
         ) {
            final Menu_InGame_ProvinceAction_NuclearAttack this$0;

            {
               this.this$0 = var1;
            }

            @Override
            protected void drawMenuElementHover2(SpriteBatch var1, int var2, int var3, boolean var4) {
               if (this.menuElementHover != null) {
                  this.menuElementHover
                     .drawAlwaysOver(var1, Touch.getMousePosX(), CFG.GAME_HEIGHT - CFG.map.getMapBG().getMinimapHeight() - CFG.BUTTON_HEIGHT - CFG.PADDING * 2);
               }
            }
         }
      );
      if (CFG.SPECTATOR_MODE) {
         ((MenuElement)var1.get(0)).setClickable(false);
      }

      this.initMenu(
         null,
         0,
         CFG.GAME_HEIGHT - CFG.map.getMapBG().getMinimapHeight() - CFG.BUTTON_HEIGHT - CFG.PADDING * 2,
         CFG.GAME_WIDTH,
         CFG.BUTTON_HEIGHT + CFG.PADDING * 2,
         var1,
         false,
         false
      );
      this.updateLanguage();
      CFG.fMOVE_MENU_PERCENTAGE = 5.0F;
      CFG.lMOVE_MENU_TIME = System.currentTimeMillis();
   }

   // $VF: Duplicated exception handlers to handle obfuscated exceptions
   @Override
   protected final void actionElement(int var1) {
      switch (var1) {
         case 0:
            if (CFG.game.getProvince(CFG.game.getActiveProvinceID()).getLevelOfBunker() <= 0) {
               int var2;
               int var3;
               try {
                  var2 = DiplomacyManager.getNuclearAttackCost(CFG.game.getActiveProvinceID(), CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
                  var3 = DiplomacyManager.getColonizeCost_Movement(CFG.game.getActiveProvinceID(), CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
                  CFG.game
                     .getProvince(CFG.game.getActiveProvinceID())
                     .setEconomy(
                        CFG.game.getProvince(CFG.game.getActiveProvinceID()).getEconomy()
                           - (
                              CFG.game.getProvince(CFG.game.getActiveProvinceID()).getEconomy() / 2
                                 + CFG.game.getProvince(CFG.game.getActiveProvinceID()).getEconomy() / 3
                           )
                     );
               } catch (IndexOutOfBoundsException var43) {
                  CFG.exceptionStack(var43);
                  var43.printStackTrace();
                  return;
               }

               var1 = 0;

               while (true) {
                  try {
                     if (var1 >= CFG.game.getProvince(CFG.game.getActiveProvinceID()).getPopulationData().getNationalitiesSize()) {
                        break;
                     }

                     int var4 = CFG.game.getProvince(CFG.game.getActiveProvinceID()).getPopulationData().getNational(var1).getCivID();
                     int var5 = CFG.game.getProvince(CFG.game.getActiveProvinceID()).getPopulationData().getNational(var1).getPopulation();
                     int var6 = CFG.game.getProvince(CFG.game.getActiveProvinceID()).getPopulationData().getNational(var1).getPopulation() / 2;
                     int var7 = CFG.game.getProvince(CFG.game.getActiveProvinceID()).getPopulationData().getNational(var1).getPopulation() / 3;
                     CFG.game.getProvince(CFG.game.getActiveProvinceID()).getPopulationData().setPopulationOfCivID(var4, var5 - (var6 + var7));
                  } catch (IndexOutOfBoundsException var41) {
                     CFG.exceptionStack(var41);
                     var41.printStackTrace();
                     return;
                  }

                  var1++;
               }

               var1 = 0;

               while (true) {
                  try {
                     if (var1 >= CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).getNumOfProvinces()) {
                        break;
                     }

                     Civilization var8 = CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID());
                     if (!CFG.game.getProvince(var8.getProvinceID(var1)).isOccupied()) {
                        CFG.game
                           .getProvince(var8.getProvinceID(var1))
                           .setHappiness(
                              CFG.game.getProvince(var8.getProvinceID(var1)).getHappiness()
                                 - CFG.game.getProvince(var8.getProvinceID(var1)).getHappiness() / 2.0F
                           );
                        CFG.game
                           .getProvince(var8.getProvinceID(var1))
                           .setProvinceStability(
                              CFG.game.getProvince(var8.getProvinceID(var1)).getProvinceStability()
                                 - CFG.game.getProvince(var8.getProvinceID(var1)).getProvinceStability() / 3.0F
                           );
                     }
                  } catch (IndexOutOfBoundsException var40) {
                     CFG.exceptionStack(var40);
                     var40.printStackTrace();
                     return;
                  }

                  var1++;
               }

               try {
                  CFG.game.getProvince(CFG.game.getActiveProvinceID()).setHappiness(0.0F);
                  CFG.game
                     .getProvince(CFG.game.getActiveProvinceID())
                     .setProvinceStability(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getProvinceStability() / 3.0F);
                  CFG.game.getProvince(CFG.game.getActiveProvinceID()).setLevelOfArmoury(0);
                  CFG.game.getProvince(CFG.game.getActiveProvinceID()).setLevelOfNuclearReactor(0);
                  if (CFG.game.getProvince(CFG.game.getActiveProvinceID()).getLevelOfFarm() > 0) {
                     CFG.game.getProvince(CFG.game.getActiveProvinceID()).setLevelOfFarm(0);
                  }
               } catch (IndexOutOfBoundsException var39) {
                  CFG.exceptionStack(var39);
                  var39.printStackTrace();
                  return;
               }

               try {
                  if (CFG.game.getProvince(CFG.game.getActiveProvinceID()).getLevelOfFort() > 0) {
                     CFG.game.getProvince(CFG.game.getActiveProvinceID()).setLevelOfFort(0);
                  }
               } catch (IndexOutOfBoundsException var38) {
                  CFG.exceptionStack(var38);
                  var38.printStackTrace();
                  return;
               }

               try {
                  if (CFG.game.getProvince(CFG.game.getActiveProvinceID()).getLevelOfPort() > 0) {
                     CFG.game.getProvince(CFG.game.getActiveProvinceID()).setLevelOfPort(0);
                  }
               } catch (IndexOutOfBoundsException var37) {
                  CFG.exceptionStack(var37);
                  var37.printStackTrace();
                  return;
               }

               try {
                  if (CFG.game.getProvince(CFG.game.getActiveProvinceID()).getLevelOfLibrary() > 0) {
                     CFG.game.getProvince(CFG.game.getActiveProvinceID()).setLevelOfLibrary(0);
                  }
               } catch (IndexOutOfBoundsException var36) {
                  CFG.exceptionStack(var36);
                  var36.printStackTrace();
                  return;
               }

               try {
                  CFG.game.getProvince(CFG.game.getActiveProvinceID()).setLevelOfWatchTower(0);
                  CFG.game.getProvince(CFG.game.getActiveProvinceID()).setLevelOfSupply(0);
                  if (CFG.game.getProvince(CFG.game.getActiveProvinceID()).getLevelOfWorkshop() > 0) {
                     CFG.game.getProvince(CFG.game.getActiveProvinceID()).setLevelOfWorkshop(0);
                  }
               } catch (IndexOutOfBoundsException var35) {
                  CFG.exceptionStack(var35);
                  var35.printStackTrace();
                  return;
               }

               try {
                  Game_Render_Province.updateDrawProvinces();
               } catch (IndexOutOfBoundsException var34) {
                  CFG.exceptionStack(var34);
                  var34.printStackTrace();
                  return;
               }

               var1 = 0;

               while (true) {
                  try {
                     if (var1 >= CFG.game.getProvince(CFG.game.getActiveProvinceID()).getArmySize()) {
                        break;
                     }

                     if (CFG.game.getProvince(CFG.game.getActiveProvinceID()).getArmy(var1) > 0) {
                        CFG.game
                           .getProvince(CFG.game.getActiveProvinceID())
                           .updateArmy(
                              CFG.game.getProvince(CFG.game.getActiveProvinceID()).getArmy_Obj(var1).getCivID(),
                              CFG.game.getProvince(CFG.game.getActiveProvinceID()).getArmy_Obj(var1).getArmy()
                                 - (
                                    CFG.game.getProvince(CFG.game.getActiveProvinceID()).getArmy_Obj(var1).getArmy() / 2
                                       + CFG.game.getProvince(CFG.game.getActiveProvinceID()).getArmy_Obj(var1).getArmy() / 3
                                 )
                           );
                     }
                  } catch (IndexOutOfBoundsException var33) {
                     CFG.exceptionStack(var33);
                     var33.printStackTrace();
                     return;
                  }

                  var1++;
               }

               try {
                  CFG.game
                     .getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
                     .setNuclearWeapons(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getNuclearWeapons() - 1);
                  CFG.game
                     .getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
                     .setMoney(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoney() - var2);
                  CFG.game
                     .getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
                     .setMovePoints(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMovePoints() - var3);
                  CFG.gameAction.updateInGame_ProvinceInfo();
                  CFG.toast.setInView(CFG.langManager.get("NuclearAttacked"));
                  if (!CFG.game.getCivsAtWar(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID(), CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
                     )
                   {
                     CFG.game
                        .declareWar(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID(), false);
                     CFG.toast.setInView(CFG.langManager.get("IsWar"), Color.RED);
                     CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).setAtNuclearWar();
                     CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).setAtNuclearWar();
                  }
               } catch (IndexOutOfBoundsException var32) {
                  CFG.exceptionStack(var32);
                  var32.printStackTrace();
                  return;
               }

               Event_GameData var60;
               label333: {
                  try {
                     CFG.soundsManager
                        .playSound(
                           SoundsManager.SOUND_NUCLEAR_EXPLOSION.get(ThreadLocalRandom.current().nextInt(0, SoundsManager.SOUND_NUCLEAR_EXPLOSION.size()))
                        );
                     CFG.game.getProvince(CFG.game.getActiveProvinceID()).setDrawNuclearExplosion(true);
                     var60 = new Event_GameData();
                     StringBuilder var9 = new StringBuilder();
                     var60.setEventName(
                        var9.append(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getName())
                           .append(" ")
                           .append(CFG.langManager.get("InRuines"))
                           .toString()
                     );
                     var1 = ThreadLocalRandom.current().nextInt(0, 100000);
                     var9 = new StringBuilder();
                     var60.setEventTag(var9.append("NuclearExplosionA").append(var1).toString());
                     var60.getEvent_PopUp().sText = "Test?";
                     var60.getEvent_PopUp().showPopUp = true;
                     if (Integer.parseInt(Game_Calendar.getYear()) > 1600) {
                        var9 = new StringBuilder();
                        var60.setEventPicture(
                           var9.append("/nuclear_event/nuclearExplosion").append(ThreadLocalRandom.current().nextInt(1, 5)).append(".png").toString()
                        );
                        break label333;
                     }
                  } catch (IndexOutOfBoundsException var31) {
                     CFG.exceptionStack(var31);
                     var31.printStackTrace();
                     return;
                  }

                  try {
                     var60.setEventPicture("/nuclear_event/secret.png");
                  } catch (IndexOutOfBoundsException var30) {
                     CFG.exceptionStack(var30);
                     var30.printStackTrace();
                     return;
                  }
               }

               label334: {
                  try {
                     Event_PopUp var71 = var60.getEvent_PopUp();
                     LanguageManager var10 = CFG.langManager;
                     StringBuilder var11 = new StringBuilder();
                     var71.sText = var10.get(var11.append("NuclearAttacker").append(ThreadLocalRandom.current().nextInt(1, 2)).toString())
                        .replace("%cDefender", CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).getCivName())
                        .replace("%cAttacker", CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCivName())
                        .replace("%cName", CFG.game.getProvince(CFG.game.getActiveProvinceID()).getName());
                     Event_Decision var72 = new Event_Decision();
                     var72.sTitle = CFG.langManager.get("ENOkay").split(";")[ThreadLocalRandom.current()
                        .nextInt(1, CFG.langManager.get("ENOkay").split(";").length)];
                     var60.lDecisions.add(var72);
                     var60.setCivID(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
                     var60.setRepeatable(false);
                     var60.setEventDate_Since(Game_Calendar.currentDay, Game_Calendar.currentMonth, Game_Calendar.currentYear);
                     var60.setEventDate_Until(Game_Calendar.currentDay + 1, Game_Calendar.currentMonth, Game_Calendar.currentYear);
                     var60.setWasTriedToRunOnce(true);
                     var60.setWasFired(false);
                     System.out.println("Hey!");
                     CFG.eventsManager.addEvent(var60);
                     EventsManager var61 = CFG.eventsManager;
                     StringBuilder var73 = new StringBuilder();
                     var61.runEvent_Tag(var73.append("NuclearExplosionA").append(var1).toString());
                     var60 = new Event_GameData();
                     StringBuilder var74 = new StringBuilder();
                     var60.setEventName(
                        var74.append(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getName())
                           .append(" ")
                           .append(CFG.langManager.get("InRuines"))
                           .toString()
                     );
                     var1 = ThreadLocalRandom.current().nextInt(0, 100000);
                     StringBuilder var75 = new StringBuilder();
                     var60.setEventTag(var75.append("NuclearExplosionD").append(var1).toString());
                     var60.getEvent_PopUp().sText = "Test?";
                     var60.getEvent_PopUp().showPopUp = true;
                     if (Integer.parseInt(Game_Calendar.getYear()) > 1600) {
                        StringBuilder var76 = new StringBuilder();
                        var60.setEventPicture(
                           var76.append("/nuclear_event/nuclearExplosion").append(ThreadLocalRandom.current().nextInt(1, 5)).append(".png").toString()
                        );
                        break label334;
                     }
                  } catch (IndexOutOfBoundsException var29) {
                     CFG.exceptionStack(var29);
                     var29.printStackTrace();
                     return;
                  }

                  try {
                     var60.setEventPicture("/nuclear_event/secret.png");
                  } catch (IndexOutOfBoundsException var28) {
                     CFG.exceptionStack(var28);
                     var28.printStackTrace();
                     return;
                  }
               }

               try {
                  Event_PopUp var77 = var60.getEvent_PopUp();
                  LanguageManager var92 = CFG.langManager;
                  StringBuilder var95 = new StringBuilder();
                  var77.sText = var92.get(var95.append("NuclearDefender").append(ThreadLocalRandom.current().nextInt(1, 2)).toString())
                     .replace("%cDefender", CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).getCivName())
                     .replace("%cAttacker", CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCivName())
                     .replace("%cName", CFG.game.getProvince(CFG.game.getActiveProvinceID()).getName());
                  Event_Decision var78 = new Event_Decision();
                  var78.sTitle = CFG.langManager.get("ENBad").split(";")[ThreadLocalRandom.current().nextInt(1, CFG.langManager.get("ENBad").split(";").length)];
                  var60.lDecisions.add(var78);
                  var60.setCivID(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID());
                  var60.setRepeatable(false);
                  var60.setEventDate_Since(Game_Calendar.currentDay, Game_Calendar.currentMonth, Game_Calendar.currentYear);
                  var60.setEventDate_Until(Game_Calendar.currentDay + 1, Game_Calendar.currentMonth, Game_Calendar.currentYear);
                  var60.setWasTriedToRunOnce(true);
                  var60.setWasFired(false);
                  CFG.eventsManager.addEvent(var60);
                  EventsManager var79 = CFG.eventsManager;
                  StringBuilder var63 = new StringBuilder();
                  var79.runEvent_Tag(var63.append("NuclearExplosionD").append(var1).toString());
                  CFG.menuManager.updateInGame_TOP_All(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
                  CFG.menuManager.setVisible_InGame_ProvinceAction_NuclearAttack(false);
                  CFG.menuManager.setVisible_InGame_ProvinceAction_NuclearAttack(true);
                  CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).setAtNuclearWar();
                  CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).setAtNuclearWar();
               } catch (IndexOutOfBoundsException var13) {
                  CFG.exceptionStack(var13);
                  var13.printStackTrace();
               }
            } else {
               int var54;
               int var55;
               try {
                  var54 = DiplomacyManager.getNuclearAttackCost(CFG.game.getActiveProvinceID(), CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
                  var55 = DiplomacyManager.getColonizeCost_Movement(CFG.game.getActiveProvinceID(), CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
                  CFG.game
                     .getProvince(CFG.game.getActiveProvinceID())
                     .setEconomy(
                        CFG.game.getProvince(CFG.game.getActiveProvinceID()).getEconomy()
                           - (
                              CFG.game.getProvince(CFG.game.getActiveProvinceID()).getEconomy() / 2
                                 + CFG.game.getProvince(CFG.game.getActiveProvinceID()).getEconomy() / 3
                           )
                     );
               } catch (IndexOutOfBoundsException var42) {
                  CFG.exceptionStack(var42);
                  var42.printStackTrace();
                  return;
               }

               var1 = 0;

               while (true) {
                  try {
                     if (var1 >= CFG.game.getProvince(CFG.game.getActiveProvinceID()).getPopulationData().getNationalitiesSize()) {
                        break;
                     }

                     int var57 = CFG.game.getProvince(CFG.game.getActiveProvinceID()).getPopulationData().getNational(var1).getCivID();
                     int var58 = CFG.game.getProvince(CFG.game.getActiveProvinceID()).getPopulationData().getNational(var1).getPopulation();
                     int var56 = CFG.game.getProvince(CFG.game.getActiveProvinceID()).getPopulationData().getNational(var1).getPopulation() / 3;
                     int var59 = CFG.game.getProvince(CFG.game.getActiveProvinceID()).getPopulationData().getNational(var1).getPopulation() / 4;
                     CFG.game.getProvince(CFG.game.getActiveProvinceID()).getPopulationData().setPopulationOfCivID(var57, var58 - (var56 + var59));
                  } catch (IndexOutOfBoundsException var27) {
                     CFG.exceptionStack(var27);
                     var27.printStackTrace();
                     return;
                  }

                  var1++;
               }

               var1 = 0;

               while (true) {
                  try {
                     if (var1 >= CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).getNumOfProvinces()) {
                        break;
                     }

                     Civilization var64 = CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID());
                     if (!CFG.game.getProvince(var64.getProvinceID(var1)).isOccupied()) {
                        CFG.game
                           .getProvince(var64.getProvinceID(var1))
                           .setHappiness(
                              CFG.game.getProvince(var64.getProvinceID(var1)).getHappiness()
                                 - CFG.game.getProvince(var64.getProvinceID(var1)).getHappiness() / 3.0F
                           );
                        CFG.game
                           .getProvince(var64.getProvinceID(var1))
                           .setProvinceStability(
                              CFG.game.getProvince(var64.getProvinceID(var1)).getProvinceStability()
                                 - CFG.game.getProvince(var64.getProvinceID(var1)).getProvinceStability() / 3.0F
                           );
                     }
                  } catch (IndexOutOfBoundsException var26) {
                     CFG.exceptionStack(var26);
                     var26.printStackTrace();
                     return;
                  }

                  var1++;
               }

               try {
                  CFG.game.getProvince(CFG.game.getActiveProvinceID()).setHappiness(0.0F);
                  CFG.game
                     .getProvince(CFG.game.getActiveProvinceID())
                     .setProvinceStability(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getProvinceStability() / 3.0F);
                  CFG.game.getProvince(CFG.game.getActiveProvinceID()).setLevelOfArmoury(0);
                  CFG.game.getProvince(CFG.game.getActiveProvinceID()).setLevelOfNuclearReactor(0);
                  if (CFG.game.getProvince(CFG.game.getActiveProvinceID()).getLevelOfFarm() > 0) {
                     CFG.game
                        .getProvince(CFG.game.getActiveProvinceID())
                        .setLevelOfFarm(ThreadLocalRandom.current().nextInt(0, CFG.game.getProvince(CFG.game.getActiveProvinceID()).getLevelOfFarm()));
                  }
               } catch (IndexOutOfBoundsException var25) {
                  CFG.exceptionStack(var25);
                  var25.printStackTrace();
                  return;
               }

               try {
                  if (CFG.game.getProvince(CFG.game.getActiveProvinceID()).getLevelOfFort() > 0) {
                     CFG.game
                        .getProvince(CFG.game.getActiveProvinceID())
                        .setLevelOfFort(ThreadLocalRandom.current().nextInt(0, CFG.game.getProvince(CFG.game.getActiveProvinceID()).getLevelOfFort()));
                  }
               } catch (IndexOutOfBoundsException var24) {
                  CFG.exceptionStack(var24);
                  var24.printStackTrace();
                  return;
               }

               try {
                  if (CFG.game.getProvince(CFG.game.getActiveProvinceID()).getLevelOfPort() > 0) {
                     CFG.game.getProvince(CFG.game.getActiveProvinceID()).setLevelOfPort(0);
                  }
               } catch (IndexOutOfBoundsException var23) {
                  CFG.exceptionStack(var23);
                  var23.printStackTrace();
                  return;
               }

               try {
                  if (CFG.game.getProvince(CFG.game.getActiveProvinceID()).getLevelOfLibrary() > 0) {
                     CFG.game
                        .getProvince(CFG.game.getActiveProvinceID())
                        .setLevelOfLibrary(ThreadLocalRandom.current().nextInt(0, CFG.game.getProvince(CFG.game.getActiveProvinceID()).getLevelOfLibrary()));
                  }
               } catch (IndexOutOfBoundsException var22) {
                  CFG.exceptionStack(var22);
                  var22.printStackTrace();
                  return;
               }

               try {
                  CFG.game.getProvince(CFG.game.getActiveProvinceID()).setLevelOfWatchTower(0);
                  CFG.game.getProvince(CFG.game.getActiveProvinceID()).setLevelOfSupply(0);
                  if (CFG.game.getProvince(CFG.game.getActiveProvinceID()).getLevelOfWorkshop() > 0) {
                     CFG.game
                        .getProvince(CFG.game.getActiveProvinceID())
                        .setLevelOfWorkshop(ThreadLocalRandom.current().nextInt(0, CFG.game.getProvince(CFG.game.getActiveProvinceID()).getLevelOfWorkshop()));
                  }
               } catch (IndexOutOfBoundsException var21) {
                  CFG.exceptionStack(var21);
                  var21.printStackTrace();
                  return;
               }

               try {
                  Game_Render_Province.updateDrawProvinces();
               } catch (IndexOutOfBoundsException var20) {
                  CFG.exceptionStack(var20);
                  var20.printStackTrace();
                  return;
               }

               var1 = 0;

               while (true) {
                  try {
                     if (var1 >= CFG.game.getProvince(CFG.game.getActiveProvinceID()).getArmySize()) {
                        break;
                     }

                     if (CFG.game.getProvince(CFG.game.getActiveProvinceID()).getArmy(var1) > 0) {
                        CFG.game
                           .getProvince(CFG.game.getActiveProvinceID())
                           .updateArmy(
                              CFG.game.getProvince(CFG.game.getActiveProvinceID()).getArmy_Obj(var1).getCivID(),
                              CFG.game.getProvince(CFG.game.getActiveProvinceID()).getArmy_Obj(var1).getArmy()
                                 - (
                                    CFG.game.getProvince(CFG.game.getActiveProvinceID()).getArmy_Obj(var1).getArmy() / 3
                                       + CFG.game.getProvince(CFG.game.getActiveProvinceID()).getArmy_Obj(var1).getArmy() / 4
                                 )
                           );
                     }
                  } catch (IndexOutOfBoundsException var19) {
                     CFG.exceptionStack(var19);
                     var19.printStackTrace();
                     return;
                  }

                  var1++;
               }

               try {
                  CFG.game
                     .getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
                     .setNuclearWeapons(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getNuclearWeapons() - 1);
                  CFG.game
                     .getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
                     .setMoney(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoney() - var54);
                  CFG.game
                     .getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
                     .setMovePoints(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMovePoints() - var55);
                  CFG.gameAction.updateInGame_ProvinceInfo();
                  CFG.toast.setInView(CFG.langManager.get("NuclearAttacked"));
                  if (!CFG.game.getCivsAtWar(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID(), CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
                     )
                   {
                     CFG.game
                        .declareWar(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID(), false);
                     CFG.toast.setInView(CFG.langManager.get("IsWar"), Color.RED);
                     CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).setAtNuclearWar();
                     CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).setAtNuclearWar();
                  }
               } catch (IndexOutOfBoundsException var18) {
                  CFG.exceptionStack(var18);
                  var18.printStackTrace();
                  return;
               }

               Event_GameData var65;
               label338: {
                  try {
                     CFG.soundsManager
                        .playSound(
                           SoundsManager.SOUND_NUCLEAR_EXPLOSION.get(ThreadLocalRandom.current().nextInt(0, SoundsManager.SOUND_NUCLEAR_EXPLOSION.size()))
                        );
                     CFG.game.getProvince(CFG.game.getActiveProvinceID()).setDrawNuclearExplosion(true);
                     var65 = new Event_GameData();
                     StringBuilder var80 = new StringBuilder();
                     var65.setEventName(
                        var80.append(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getName())
                           .append(" ")
                           .append(CFG.langManager.get("InRuines"))
                           .toString()
                     );
                     var1 = ThreadLocalRandom.current().nextInt(0, 100000);
                     var80 = new StringBuilder();
                     var65.setEventTag(var80.append("NuclearExplosionA").append(var1).toString());
                     var65.getEvent_PopUp().sText = "Test?";
                     var65.getEvent_PopUp().showPopUp = true;
                     if (Integer.parseInt(Game_Calendar.getYear()) > 1600) {
                        var80 = new StringBuilder();
                        var65.setEventPicture(
                           var80.append("/nuclear_event/nuclearExplosion").append(ThreadLocalRandom.current().nextInt(1, 5)).append(".png").toString()
                        );
                        break label338;
                     }
                  } catch (IndexOutOfBoundsException var17) {
                     CFG.exceptionStack(var17);
                     var17.printStackTrace();
                     return;
                  }

                  try {
                     var65.setEventPicture("/nuclear_event/secret.png");
                  } catch (IndexOutOfBoundsException var16) {
                     CFG.exceptionStack(var16);
                     var16.printStackTrace();
                     return;
                  }
               }

               label339: {
                  try {
                     Event_PopUp var93 = var65.getEvent_PopUp();
                     LanguageManager var83 = CFG.langManager;
                     StringBuilder var96 = new StringBuilder();
                     var93.sText = var83.get(var96.append("NuclearAttacker").append(ThreadLocalRandom.current().nextInt(1, 2)).toString())
                        .replace("%cDefender", CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).getCivName())
                        .replace("%cAttacker", CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCivName())
                        .replace("%cName", CFG.game.getProvince(CFG.game.getActiveProvinceID()).getName());
                     Event_Decision var84 = new Event_Decision();
                     var84.sTitle = CFG.langManager.get("ENOkay").split(";")[ThreadLocalRandom.current()
                        .nextInt(1, CFG.langManager.get("ENOkay").split(";").length)];
                     var65.lDecisions.add(var84);
                     var65.setCivID(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
                     var65.setRepeatable(false);
                     var65.setEventDate_Since(Game_Calendar.currentDay, Game_Calendar.currentMonth, Game_Calendar.currentYear);
                     var65.setEventDate_Until(Game_Calendar.currentDay + 1, Game_Calendar.currentMonth, Game_Calendar.currentYear);
                     var65.setWasTriedToRunOnce(true);
                     var65.setWasFired(false);
                     System.out.println("Hey!");
                     CFG.eventsManager.addEvent(var65);
                     EventsManager var85 = CFG.eventsManager;
                     StringBuilder var66 = new StringBuilder();
                     var85.runEvent_Tag(var66.append("NuclearExplosionA").append(var1).toString());
                     var65 = new Event_GameData();
                     StringBuilder var86 = new StringBuilder();
                     var65.setEventName(
                        var86.append(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getName())
                           .append(" ")
                           .append(CFG.langManager.get("InRuines"))
                           .toString()
                     );
                     var1 = ThreadLocalRandom.current().nextInt(0, 100000);
                     StringBuilder var87 = new StringBuilder();
                     var65.setEventTag(var87.append("NuclearExplosionD").append(var1).toString());
                     var65.getEvent_PopUp().sText = "Test?";
                     var65.getEvent_PopUp().showPopUp = true;
                     if (Integer.parseInt(Game_Calendar.getYear()) > 1600) {
                        StringBuilder var88 = new StringBuilder();
                        var65.setEventPicture(
                           var88.append("/nuclear_event/nuclearExplosion").append(ThreadLocalRandom.current().nextInt(1, 5)).append(".png").toString()
                        );
                        break label339;
                     }
                  } catch (IndexOutOfBoundsException var15) {
                     CFG.exceptionStack(var15);
                     var15.printStackTrace();
                     return;
                  }

                  try {
                     var65.setEventPicture("/nuclear_event/secret.png");
                  } catch (IndexOutOfBoundsException var14) {
                     CFG.exceptionStack(var14);
                     var14.printStackTrace();
                     return;
                  }
               }

               try {
                  Event_PopUp var89 = var65.getEvent_PopUp();
                  LanguageManager var94 = CFG.langManager;
                  StringBuilder var97 = new StringBuilder();
                  var89.sText = var94.get(var97.append("NuclearDefender").append(ThreadLocalRandom.current().nextInt(1, 2)).toString())
                     .replace("%cDefender", CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).getCivName())
                     .replace("%cAttacker", CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCivName())
                     .replace("%cName", CFG.game.getProvince(CFG.game.getActiveProvinceID()).getName());
                  Event_Decision var90 = new Event_Decision();
                  var90.sTitle = CFG.langManager.get("ENBad").split(";")[ThreadLocalRandom.current().nextInt(1, CFG.langManager.get("ENBad").split(";").length)];
                  var65.lDecisions.add(var90);
                  var65.setCivID(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID());
                  var65.setRepeatable(false);
                  var65.setEventDate_Since(Game_Calendar.currentDay, Game_Calendar.currentMonth, Game_Calendar.currentYear);
                  var65.setEventDate_Until(Game_Calendar.currentDay + 1, Game_Calendar.currentMonth, Game_Calendar.currentYear);
                  var65.setWasTriedToRunOnce(true);
                  var65.setWasFired(false);
                  CFG.eventsManager.addEvent(var65);
                  EventsManager var68 = CFG.eventsManager;
                  StringBuilder var91 = new StringBuilder();
                  var68.runEvent_Tag(var91.append("NuclearExplosionD").append(var1).toString());
                  CFG.menuManager.updateInGame_TOP_All(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
                  CFG.menuManager.setVisible_InGame_ProvinceAction_NuclearAttack(false);
                  CFG.menuManager.setVisible_InGame_ProvinceAction_NuclearAttack(true);
                  CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).setAtNuclearWar();
                  CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).setAtNuclearWar();
               } catch (IndexOutOfBoundsException var12) {
                  CFG.exceptionStack(var12);
                  var12.printStackTrace();
               }
            }
      }
   }

   @Override
   protected void beginClip(SpriteBatch var1, int var2, int var3, boolean var4) {
   }

   @Override
   protected void draw(SpriteBatch var1, int var2, int var3, boolean var4) {
      float var5 = CFG.fMOVE_MENU_PERCENTAGE + (float)(System.currentTimeMillis() - CFG.lMOVE_MENU_TIME) / 300.0F * 95.0F;
      CFG.fMOVE_MENU_PERCENTAGE = var5;
      if (var5 > 100.0F) {
         CFG.fMOVE_MENU_PERCENTAGE = 100.0F;
      } else {
         CFG.setRender_3(true);
      }

      CFG.lMOVE_MENU_TIME = System.currentTimeMillis();
      Rectangle var6 = new Rectangle(this.getPosX() + var2, CFG.GAME_HEIGHT - this.getPosY() + 1 - var3, this.getWidth(), -this.getHeight() - 1);
      var1.flush();
      ScissorStack.pushScissors(var6);
      ImageManager.getImage(Images.bg_game_action)
         .draw2(
            var1,
            this.getPosX() + var2,
            this.getPosY()
               - ImageManager.getImage(Images.bg_game_action).getHeight()
               + (int)(this.getHeight() * (100.0F - CFG.fMOVE_MENU_PERCENTAGE) / 100.0F)
               - 1
               + var3,
            this.getMenuElement(this.getMenuElementsSize() - 1).getPosX() + this.getMenuElement(this.getMenuElementsSize() - 1).getWidth() + CFG.PADDING + 1,
            this.getHeight() + 1,
            true,
            false
         );
      super.draw(var1, var2, (int)(this.getHeight() * (100.0F - CFG.fMOVE_MENU_PERCENTAGE) / 100.0F) + var3, var4);
   }

   @Override
   protected void onHovered() {
      CFG.menuManager.setOrderOfMenu_InGame();
   }

   @Override
   protected void setVisible(boolean var1) {
      if (var1 && this.getVisible() != var1) {
         CFG.fMOVE_MENU_PERCENTAGE = 5.0F;
         CFG.lMOVE_MENU_TIME = System.currentTimeMillis();
      }

      super.setVisible(var1);
   }

   @Override
   protected void updateLanguage() {
      this.getMenuElement(0).setText(CFG.langManager.get("NuclearAttack"));
      this.updatedButtonsWidth(CFG.PADDING, CFG.BUTTON_WIDTH + CFG.BUTTON_WIDTH / 2);
   }
}
