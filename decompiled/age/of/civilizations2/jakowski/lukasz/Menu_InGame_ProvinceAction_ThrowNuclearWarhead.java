package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.utils.ScissorStack;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Menu_InGame_ProvinceAction_ThrowNuclearWarhead extends SliderMenu {
   public Menu_InGame_ProvinceAction_ThrowNuclearWarhead() {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      menuElements.add(
         new Button_Game_ThrowNuclearWarhead(
            CFG.langManager.get("ThrowNuclearWarhead"),
            CFG.game.getActiveProvinceID(),
            CFG.PADDING,
            CFG.PADDING,
            Game_Calendar.getCanColonize_TechLevel(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
         ) {
            @Override
            public void drawMenuElementHover2(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
               if (this.menuElementHover != null) {
                  this.menuElementHover
                     .drawAlwaysOver(oSB, Touch.getMousePosX(), CFG.GAME_HEIGHT - CFG.map.getMapBG().getMinimapHeight() - CFG.BUTTON_HEIGHT - CFG.PADDING * 2);
               }
            }
         }
      );
      if (CFG.SPECTATOR_MODE) {
         menuElements.get(0).setClickable(false);
      }

      this.initMenu(
         null,
         0,
         CFG.GAME_HEIGHT - CFG.map.getMapBG().getMinimapHeight() - CFG.BUTTON_HEIGHT - CFG.PADDING * 2,
         CFG.GAME_WIDTH,
         CFG.BUTTON_HEIGHT + CFG.PADDING * 2,
         menuElements,
         false,
         false
      );
      this.updateLanguage();
      CFG.fMOVE_MENU_PERCENTAGE = 5.0F;
      CFG.lMOVE_MENU_TIME = System.currentTimeMillis();
   }

   @Override
   public void updateLanguage() {
      this.getMenuElement(0).setText(CFG.langManager.get("ThrowNuclearWarhead"));
      this.updatedButtonsWidth(CFG.PADDING, CFG.BUTTON_WIDTH + CFG.BUTTON_WIDTH / 2);
   }

   @Override
   public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      if ((CFG.fMOVE_MENU_PERCENTAGE = CFG.fMOVE_MENU_PERCENTAGE + (float)(System.currentTimeMillis() - CFG.lMOVE_MENU_TIME) / 300.0F * 95.0F) > 100.0F) {
         CFG.fMOVE_MENU_PERCENTAGE = 100.0F;
      } else {
         CFG.setRender_3(true);
      }

      CFG.lMOVE_MENU_TIME = System.currentTimeMillis();
      Rectangle clipBounds = new Rectangle(
         this.getPosX() + iTranslateX, CFG.GAME_HEIGHT - this.getPosY() + 1 - iTranslateY, this.getWidth(), -this.getHeight() - 1
      );
      oSB.flush();
      ScissorStack.pushScissors(clipBounds);
      ImageManager.getImage(Images.bg_game_action)
         .draw2(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY()
               - ImageManager.getImage(Images.bg_game_action).getHeight()
               + (int)(this.getHeight() * (100.0F - CFG.fMOVE_MENU_PERCENTAGE) / 100.0F)
               - 1
               + iTranslateY,
            this.getMenuElement(this.getMenuElementsSize() - 1).getPosX() + this.getMenuElement(this.getMenuElementsSize() - 1).getWidth() + CFG.PADDING + 1,
            this.getHeight() + 1,
            true,
            false
         );
      super.draw(oSB, iTranslateX, (int)(this.getHeight() * (100.0F - CFG.fMOVE_MENU_PERCENTAGE) / 100.0F) + iTranslateY, sliderMenuIsActive);
   }

   @Override
   public void beginClip(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
   }

   @Override
   public final void actionElement(int iID) {
      switch (iID) {
         case 0:
            CFG.setDialogType(Dialog.THROW_NUCLEAR_WARHEAD);
      }
   }

   public static void nuclearBOOM() {
      int i = 0;
      int multiply = 3;
      if (CFG.game.getProvince(CFG.game.getActiveProvinceID()).getLevelOfShelter() > 0) {
      }

      new Event_GameData();
      new Event_GameData();
      int nuclearAttackCost = DiplomacyManager.getNuclearAttackCost(CFG.game.getActiveProvinceID(), CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
      int colonizeCost_Movement = DiplomacyManager.getColonizeCost_Movement(CFG.game.getActiveProvinceID(), CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
      CFG.game
         .getProvince(CFG.game.getActiveProvinceID())
         .setEconomy(
            CFG.game.getProvince(CFG.game.getActiveProvinceID()).getEconomy()
               - (
                  CFG.game.getProvince(CFG.game.getActiveProvinceID()).getEconomy() / 2
                     + CFG.game.getProvince(CFG.game.getActiveProvinceID()).getEconomy() / multiply
               )
         );

      for (int var12 = 0; var12 < CFG.game.getProvince(CFG.game.getActiveProvinceID()).getPopulationData().getNationalitiesSize(); var12++) {
         CFG.game
            .getProvince(CFG.game.getActiveProvinceID())
            .getPopulationData()
            .setPopulationOfCivID(
               CFG.game.getProvince(CFG.game.getActiveProvinceID()).getPopulationData().getNational(var12).getCivID(),
               CFG.game.getProvince(CFG.game.getActiveProvinceID()).getPopulationData().getNational(var12).getPopulation()
                  - (
                     CFG.game.getProvince(CFG.game.getActiveProvinceID()).getPopulationData().getNational(var12).getPopulation() / 2
                        + CFG.game.getProvince(CFG.game.getActiveProvinceID()).getPopulationData().getNational(var12).getPopulation() / multiply
                  )
            );
      }

      for (int var13 = 0; var13 < CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).getNumOfProvinces(); var13++) {
         Civilization civ = CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID());
         if (!CFG.game.getProvince(civ.getProvinceID(var13)).isOccupied()) {
            CFG.game
               .getProvince(civ.getProvinceID(var13))
               .setHappiness(
                  CFG.game.getProvince(civ.getProvinceID(var13)).getHappiness() - CFG.game.getProvince(civ.getProvinceID(var13)).getHappiness() / 6.0F
               );
            CFG.game
               .getProvince(civ.getProvinceID(var13))
               .setProvinceStability(
                  CFG.game.getProvince(civ.getProvinceID(var13)).getProvinceStability()
                     - CFG.game.getProvince(civ.getProvinceID(var13)).getProvinceStability() / multiply
               );
         }
      }

      CFG.game.getProvince(CFG.game.getActiveProvinceID()).setHappiness(0.0F);
      CFG.game
         .getProvince(CFG.game.getActiveProvinceID())
         .setProvinceStability(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getProvinceStability() / multiply);
      CFG.game.getProvince(CFG.game.getActiveProvinceID()).setLevelOfArmoury(0);
      CFG.game.getProvince(CFG.game.getActiveProvinceID()).setLevelOfNuclearReactor(0);
      if (CFG.game.getProvince(CFG.game.getActiveProvinceID()).getLevelOfFarm() > 0) {
         CFG.game.getProvince(CFG.game.getActiveProvinceID()).setLevelOfFarm(0);
      }

      if (CFG.game.getProvince(CFG.game.getActiveProvinceID()).getLevelOfFort() > 0) {
         CFG.game.getProvince(CFG.game.getActiveProvinceID()).setLevelOfFort(0);
      }

      if (CFG.game.getProvince(CFG.game.getActiveProvinceID()).getLevelOfPort() > 0) {
         CFG.game.getProvince(CFG.game.getActiveProvinceID()).setLevelOfPort(0);
      }

      if (CFG.game.getProvince(CFG.game.getActiveProvinceID()).getLevelOfLibrary() > 0) {
         CFG.game.getProvince(CFG.game.getActiveProvinceID()).setLevelOfLibrary(0);
      }

      CFG.game.getProvince(CFG.game.getActiveProvinceID()).setLevelOfWatchTower(0);
      CFG.game.getProvince(CFG.game.getActiveProvinceID()).setLevelOfSupply(0);
      if (CFG.game.getProvince(CFG.game.getActiveProvinceID()).getLevelOfWorkshop() > 0) {
         CFG.game.getProvince(CFG.game.getActiveProvinceID()).setLevelOfWorkshop(0);
      }

      Game_Render_Province.updateDrawProvinces();

      for (int var14 = 0; var14 < CFG.game.getProvince(CFG.game.getActiveProvinceID()).getArmySize(); var14++) {
         if (CFG.game.getProvince(CFG.game.getActiveProvinceID()).getArmy(var14) > 0) {
            CFG.game
               .getProvince(CFG.game.getActiveProvinceID())
               .updateArmy(
                  CFG.game.getProvince(CFG.game.getActiveProvinceID()).getArmy_Obj(var14).getCivID(),
                  CFG.game.getProvince(CFG.game.getActiveProvinceID()).getArmy_Obj(var14).getArmy()
                     - (
                        CFG.game.getProvince(CFG.game.getActiveProvinceID()).getArmy_Obj(var14).getArmy() / 2
                           + CFG.game.getProvince(CFG.game.getActiveProvinceID()).getArmy_Obj(var14).getArmy() / multiply
                     )
               );
         }
      }

      CFG.game
         .getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
         .setNuclearWeapons(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getNuclearWeapons() - 1);
      CFG.game
         .getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
         .setMoney(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoney() - nuclearAttackCost);
      CFG.game
         .getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
         .setMovePoints(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMovePoints() - colonizeCost_Movement);
      CFG.gameAction.updateInGame_ProvinceInfo();
      CFG.toast.setInView(CFG.langManager.get("NuclearAttacked"));
      if (!CFG.game.getCivsAtWar(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID(), CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())) {
         CFG.game.declareWar(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID(), false);
         CFG.toast.setInView(CFG.langManager.get("IsWar"), Color.RED);
         CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).setAtNuclearWar();
         CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).setAtNuclearWar();
      }

      CFG.soundsManager
         .playSound(SoundsManager.SOUND_NUCLEAR_EXPLOSION.get(ThreadLocalRandom.current().nextInt(0, SoundsManager.SOUND_NUCLEAR_EXPLOSION.size())));
      CFG.game.getProvince(CFG.game.getActiveProvinceID()).setDrawNuclearExplosion(true);
      Event_GameData event_GameData = new Event_GameData();
      event_GameData.setEventName(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getName() + " " + CFG.langManager.get("InRuines"));
      i = ThreadLocalRandom.current().nextInt(0, 100000);
      event_GameData.setEventTag("NuclearExplosionA" + i);
      event_GameData.getEvent_PopUp().sText = "Test?";
      event_GameData.getEvent_PopUp().showPopUp = true;
      if (Game_Calendar.getYear() > 1922 || CFG.SANDBOX_MODE || CFG.FREEPLAY_MODE) {
         event_GameData.setEventPicture("/nuclear_event/nuclearExplosion" + ThreadLocalRandom.current().nextInt(1, 5) + ".png");
         event_GameData.getEvent_PopUp().sText = CFG.langManager
            .get("NuclearAttacker" + ThreadLocalRandom.current().nextInt(1, 2))
            .replace("%cDefender", CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).getCivName())
            .replace("%cAttacker", CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCivName())
            .replace("%cName", CFG.game.getProvince(CFG.game.getActiveProvinceID()).getName());
         Event_Decision event_Decision = new Event_Decision();
         event_Decision.sTitle = CFG.langManager.get("ENOkay").split(";")[ThreadLocalRandom.current()
            .nextInt(1, CFG.langManager.get("ENOkay").split(";").length)];
         event_GameData.lDecisions.add(event_Decision);
         event_GameData.setCivID(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
         event_GameData.setRepeatable(false);
         event_GameData.setEventDate_Since(Game_Calendar.currentDay, Game_Calendar.currentMonth, Game_Calendar.currentYear);
         event_GameData.setEventDate_Until(Game_Calendar.currentDay + 1, Game_Calendar.currentMonth, Game_Calendar.currentYear);
         event_GameData.setWasTriedToRunOnce(true);
         event_GameData.setWasFired(false);
         System.out.println("Hey!");
         CFG.eventsManager.addEvent(event_GameData);
         CFG.eventsManager.runEvent_Tag("NuclearExplosionA" + i);
         Event_GameData event_GameData2 = new Event_GameData();
         event_GameData2.setEventName(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getName() + " " + CFG.langManager.get("InRuines"));
         i = ThreadLocalRandom.current().nextInt(0, 100000);
         event_GameData2.setEventTag("NuclearExplosionD" + i);
         event_GameData2.getEvent_PopUp().sText = "Test?";
         event_GameData2.getEvent_PopUp().showPopUp = true;
         if (Game_Calendar.getYear() > 1600 || CFG.SANDBOX_MODE || CFG.FREEPLAY_MODE) {
            event_GameData2.setEventPicture("/nuclear_event/nuclearExplosion" + ThreadLocalRandom.current().nextInt(1, 5) + ".png");
            event_GameData2.getEvent_PopUp().sText = CFG.langManager
               .get("NuclearDefender" + ThreadLocalRandom.current().nextInt(1, 2))
               .replace("%cDefender", CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).getCivName())
               .replace("%cAttacker", CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCivName())
               .replace("%cName", CFG.game.getProvince(CFG.game.getActiveProvinceID()).getName());
            Event_Decision event_Decision2 = new Event_Decision();
            event_Decision2.sTitle = CFG.langManager.get("ENBad").split(";")[ThreadLocalRandom.current()
               .nextInt(1, CFG.langManager.get("ENBad").split(";").length)];
            event_GameData2.lDecisions.add(event_Decision2);
            event_GameData2.setCivID(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID());
            event_GameData2.setRepeatable(false);
            event_GameData2.setEventDate_Since(Game_Calendar.currentDay, Game_Calendar.currentMonth, Game_Calendar.currentYear);
            event_GameData2.setEventDate_Until(Game_Calendar.currentDay + 1, Game_Calendar.currentMonth, Game_Calendar.currentYear);
            event_GameData2.setWasTriedToRunOnce(true);
            event_GameData2.setWasFired(false);
            CFG.eventsManager.addEvent(event_GameData2);
            CFG.eventsManager.runEvent_Tag("NuclearExplosionD" + i);
            CFG.menuManager.updateInGame_TOP_All(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
            CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).setAtNuclearWar();
            CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).setAtNuclearWar();
            float sievertEmitted = CFG.game.getProvince(CFG.game.getActiveProvinceID()).getZiverts()
               + 8.0F
               - (CFG.game.getProvince(CFG.game.getActiveProvinceID()).getZiverts() + 8.0F) / 4.0F;
            float sievertEmittedToNeighbouringProvinces = (CFG.game.getProvince(CFG.game.getActiveProvinceID()).getZiverts() + 8.0F) / 4.0F;
            CFG.game.getProvince(CFG.game.getActiveProvinceID()).setZiverts(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getZiverts() + sievertEmitted);

            for (int p = 0; p < CFG.game.getProvince(CFG.game.getActiveProvinceID()).getNeighboringProvincesSize(); p++) {
               CFG.game
                  .getProvince(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getNeighboringProvinces(p))
                  .setZiverts(
                     CFG.game.getProvince(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getNeighboringProvinces(p)).getZiverts()
                        + sievertEmittedToNeighbouringProvinces / CFG.game.getProvince(CFG.game.getActiveProvinceID()).getNeighboringProvincesSize()
                  );
            }
         }
      }
   }

   @Override
   public void setVisible(boolean visible) {
      if (visible && this.getVisible() != visible) {
         CFG.fMOVE_MENU_PERCENTAGE = 5.0F;
         CFG.lMOVE_MENU_TIME = System.currentTimeMillis();
      }

      super.setVisible(visible);
   }

   @Override
   public void onHovered() {
      CFG.menuManager.setOrderOfMenu_InGame();
   }
}
