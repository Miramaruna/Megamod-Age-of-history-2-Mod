package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.GdxRuntimeException;
import java.util.ArrayList;
import java.util.List;

public class Player {
   public Save_Player_GameData savePlayer = new Save_Player_GameData();
   public Image flagOfCivilization = null;
   public boolean noOrders;
   public List<Boolean> fogOfWar;
   public Statistics_Civ_GameData statistics_Civ_GameData;
   public long iTurnPopulation = 0L;
   public int iTurnEconomy = 0;
   public int iBefore_PosX;
   public int iBefore_PosY = -999999;
   public float fBefore_Scale;
   public int iBefore_ActiveProvince;
   public int iACTIVE_VIEW_MODE = -1;
   public int visible_CivInfo = -1;
   public boolean visible_Outliner = false;
   public int visible_CensusOfProvince = -1;
   public boolean visible_Wars = false;
   public int visible_WarStats = -1;
   public boolean visible_Alliances = false;
   public int visible_Alliance = -1;
   public boolean visible_Rank = false;
   public boolean visible_WorldPop = false;
   public boolean visible_VictoryConditions = false;
   public boolean visible_UpgradingArmy = false;
   public boolean visible_ConqueredProvinces = false;
   public boolean visible_BuildingsConstructed = false;
   public boolean visible_RecruitedArmy = false;
   public boolean visible_Army = false;
   public boolean visible_Tribute = false;
   public boolean visible_Technology = false;
   public boolean visible_MapModes = false;
   public boolean visible_BuildingsMore = false;
   public boolean visible_History = false;
   public boolean visible_HRE = false;

   public Player(int iCivID) {
      this.setCivID(iCivID);
      this.noOrders = true;
      this.initFogOfWar();
      this.initMetProvince(true);
      this.initMetCivilization(true);
   }

   public Player(Save_Player_GameData savedPlayer) {
      this.setCivID(savedPlayer.iCivID);
      this.savePlayer = savedPlayer;
      this.noOrders = true;
      this.initFogOfWar();
   }

   public final float buildPlayerScore() {
      float out = 1.0F;

      for (int i = 0; i < CFG.game.getCiv(this.getCivID()).getNumOfProvinces(); i++) {
         out += 2.45F
            * CFG.game.getProvince(CFG.game.getCiv(this.getCivID()).getProvinceID(i)).getPopulationData().getPopulation()
            / CFG.game.getGameScenarios().getScenario_StartingPopulation();
         out += 2.25F
            * CFG.game.getProvince(CFG.game.getCiv(this.getCivID()).getProvinceID(i)).getEconomy()
            / CFG.game.getGameScenarios().getScenario_StartingEconomy();
      }

      return out + 0.075F * CFG.game.getCiv(this.getCivID()).civGameData.iNumOfConqueredProvinces;
   }

   public final void initMetProvince(boolean nValue) {
      this.savePlayer.metProvince = new ArrayList<>();

      for (int i = 0; i < CFG.game.getProvincesSize(); i++) {
         this.savePlayer.metProvince.add(nValue);
      }
   }

   public final void initMetCivilization(boolean nValue) {
      this.savePlayer.metCivilization = new ArrayList<>();

      for (int i = 0; i < CFG.game.getCivsSize(); i++) {
         this.savePlayer.metCivilization.add(nValue);
      }
   }

   public final void initFogOfWar() {
      this.fogOfWar = new ArrayList<>();

      for (int i = 0; i < CFG.game.getProvincesSize(); i++) {
         this.fogOfWar.add(false);
      }
   }

   public final void buildMetProvincesAndCivs() {
      this.initMetProvince(false);
      this.initMetCivilization(false);

      for (int i = 0; i < CFG.game.getProvincesSize(); i++) {
         if (this.getFogOfWar(i)) {
            this.savePlayer.metProvince.set(i, true);
            this.savePlayer.metCivilization.set(CFG.game.getProvince(i).getCivID(), true);
            if (!CFG.game.getProvince(i).getSeaProvince()
                  && (
                     CFG.game.getProvince(i).getCivID() == CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()
                        || CFG.game.getCiv(CFG.game.getProvince(i).getCivID()).getPuppetOfCivID() == CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()
                        || CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID() > 0
                           && CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID()
                              == CFG.game.getCiv(CFG.game.getProvince(i).getCivID()).getAllianceID()
                  )
               || CFG.gameAction.hasArmyInProvince(i, this.getCivID())
               || CFG.gameAction.hasArmyInProvince_AllianceID(i, CFG.game.getCiv(this.getCivID()).getAllianceID())) {
               for (int j = 0; j < CFG.game.getProvince(i).getNeighboringProvincesSize(); j++) {
                  this.savePlayer.metProvince.set(CFG.game.getProvince(i).getNeighboringProvinces(j), true);
                  this.savePlayer.metCivilization.set(CFG.game.getProvince(CFG.game.getProvince(i).getNeighboringProvinces(j)).getCivID(), true);
               }
            }
         }
      }

      if (CFG.game.getCiv(this.getCivID()).getIsPartOfHolyRomanEmpire()) {
         for (int var9 = 0; var9 < CFG.holyRomanEmpire_Manager.getHRE().getPrincesSize(); var9++) {
            for (int j = 0; j < CFG.game.getCiv(CFG.holyRomanEmpire_Manager.getHRE().getPrince(var9)).getNumOfProvinces(); j++) {
               if (CFG.game.getProvince(CFG.game.getCiv(CFG.holyRomanEmpire_Manager.getHRE().getPrince(var9)).getProvinceID(j)).getIsPartOfHolyRomanEmpire()) {
                  this.savePlayer.metProvince.set(CFG.game.getCiv(CFG.holyRomanEmpire_Manager.getHRE().getPrince(var9)).getProvinceID(j), true);
               }
            }

            this.savePlayer.metCivilization.set(CFG.holyRomanEmpire_Manager.getHRE().getPrince(var9), true);
         }
      }

      this.buildMetProvinces_BasedOnDistance();

      for (int var10 = 1; var10 < CFG.game.getCivsSize(); var10++) {
         for (int jx = 0; jx < CFG.game.getCiv(var10).getCivRegionsSize(); jx++) {
            int regionMet = 0;
            int regionNotMet = 0;

            for (int k = 0; k < CFG.game.getCiv(var10).getCivRegion(jx).getProvincesSize(); k++) {
               if (this.getMetProvince(CFG.game.getCiv(var10).getCivRegion(jx).getProvince(k))) {
                  regionMet++;
               } else {
                  regionNotMet++;
               }
            }

            if (regionMet > 0 && regionNotMet < 4) {
               for (int var13 = 0; var13 < CFG.game.getCiv(var10).getCivRegion(jx).getProvincesSize(); var13++) {
                  this.savePlayer.metProvince.set(CFG.game.getCiv(var10).getCivRegion(jx).getProvince(var13), true);
               }
            }
         }
      }

      for (int var11 = 1; var11 < CFG.game.getCivsSize(); var11++) {
         if (CFG.game.getCiv(var11).getNumOfProvinces() == 0) {
            this.savePlayer.metCivilization.set(var11, true);
         }
      }

      for (int var12 = 0; var12 < CFG.game.getCiv(this.getCivID()).getNumOfProvinces(); var12++) {
         for (int jx = 0; jx < CFG.game.getProvince(CFG.game.getCiv(this.getCivID()).getProvinceID(var12)).getPopulationData().getNationalitiesSize(); jx++) {
            this.savePlayer
               .metCivilization
               .set(CFG.game.getProvince(CFG.game.getCiv(this.getCivID()).getProvinceID(var12)).getPopulationData().getCivID(jx), true);
         }
      }
   }

   public final void buildMetProvinces_BasedOnDistance() {
      float tempDis = CFG.gameAges.getAge_FogOfWarDiscovery_MetProvinces(Game_Calendar.CURRENT_AGEID);

      for (int i = 0; i < CFG.game.getProvincesSize(); i++) {
         if (!this.getMetProvince(i)) {
            if (!Game_Calendar.getColonizationOfWastelandIsEnabled() && CFG.game.getProvince(i).getWasteland() >= 0) {
               this.savePlayer.metProvince.set(i, true);
               this.savePlayer.metCivilization.set(CFG.game.getProvince(i).getCivID(), true);
            }

            for (int j = 0; j < CFG.game.getCiv(this.getCivID()).getNumOfProvinces(); j++) {
               float f = CFG.game_NextTurnUpdate.getDistanceFromAToB_PercOfMax(CFG.game.getCiv(this.getCivID()).getProvinceID(j), i);
               float f2 = CFG.game.getProvince(CFG.game.getCiv(this.getCivID()).getProvinceID(j)).getContinent() == CFG.game.getProvince(i).getContinent()
                  ? 0.715F
                  : 1.0F;
               if (f * f2
                  < (
                        tempDis
                           + tempDis
                              * 0.325F
                              * (
                                 1.0F
                                    - Math.min(
                                       Math.abs(
                                             CFG.game.getProvince(CFG.game.getCiv(this.getCivID()).getProvinceID(j)).getCenterY()
                                                - CFG.game.getProvince(i).getCenterY()
                                          )
                                          / (CFG.map.getMapBG().getHeight() / 10.0F),
                                       1.0F
                                    )
                              )
                     )
                     * CFG.game.getCiv(this.getCivID()).getTechnologyLevel()) {
                  this.savePlayer.metProvince.set(i, true);
                  this.savePlayer.metCivilization.set(CFG.game.getProvince(i).getCivID(), true);
                  break;
               }
            }
         }
      }
   }

   public final void loadPlayersFlag(Image tFlag) {
      this.disposePlayersFlag();
      this.flagOfCivilization = tFlag;
   }

   public final void loadPlayersFlag() {
      this.disposePlayersFlag();
      if (CFG.game.getCiv(this.savePlayer.iCivID).getCivTag().indexOf(59) > 0) {
         CFG.unionFlagsToGenerate_Manager.lFlags.add(new UnionFlagsToGenerate());
         int tGenerateID = CFG.unionFlagsToGenerate_Manager.lFlags.size() - 1;
         String[] tempD = CFG.game.getCiv(this.savePlayer.iCivID).getCivTag().split(";");

         for (int i = 0; i < tempD.length; i++) {
            CFG.unionFlagsToGenerate_Manager.lFlags.get(tGenerateID).lTags.add(tempD[i]);
         }

         CFG.unionFlagsToGenerate_Manager.lFlags.get(tGenerateID).typeOfAction = UnionFlagsToGenerate_TypesOfAction.PLAYER_ID;
         CFG.unionFlagsToGenerate_Manager.lFlags.get(tGenerateID).iID = this.getCivID();
      } else {
         try {
            try {
               this.flagOfCivilization = new Image(
                  new Texture(Gdx.files.internal("game/flagsH/" + CFG.game.getCiv(this.savePlayer.iCivID).getCivTag() + ".png")), Texture.TextureFilter.Linear
               );
            } catch (GdxRuntimeException var6) {
               if (CFG.ideologiesManager.getIdeology(CFG.game.getCiv(this.savePlayer.iCivID).getIdeologyID()).REVOLUTIONARY) {
                  this.flagOfCivilization = new Image(
                     new Texture(
                        Gdx.files
                           .internal(
                              "game/flagsH/rb"
                                 + (CFG.game.getCiv(this.savePlayer.iCivID).getCivID() + CFG.game.getCiv(this.savePlayer.iCivID).getCivTag().charAt(0)) % 6
                                 + ".png"
                           )
                     ),
                     Texture.TextureFilter.Nearest
                  );
                  return;
               }

               try {
                  this.flagOfCivilization = new Image(
                     new Texture(
                        Gdx.files.internal("game/flagsH/" + CFG.ideologiesManager.getRealTag(CFG.game.getCiv(this.savePlayer.iCivID).getCivTag()) + ".png")
                     ),
                     Texture.TextureFilter.Linear
                  );
               } catch (GdxRuntimeException var5) {
                  if (CFG.isAndroid()) {
                     try {
                        this.flagOfCivilization = new Image(
                           new Texture(
                              Gdx.files
                                 .local(
                                    "game/civilizations_editor/"
                                       + CFG.ideologiesManager.getRealTag(CFG.game.getCiv(this.savePlayer.iCivID).getCivTag())
                                       + "/"
                                       + CFG.ideologiesManager.getRealTag(CFG.game.getCiv(this.savePlayer.iCivID).getCivTag())
                                       + "_FLH.png"
                                 )
                           ),
                           Texture.TextureFilter.Linear
                        );
                     } catch (GdxRuntimeException var4) {
                        this.flagOfCivilization = new Image(
                           new Texture(
                              Gdx.files
                                 .internal(
                                    "game/civilizations_editor/"
                                       + CFG.ideologiesManager.getRealTag(CFG.game.getCiv(this.savePlayer.iCivID).getCivTag())
                                       + "/"
                                       + CFG.ideologiesManager.getRealTag(CFG.game.getCiv(this.savePlayer.iCivID).getCivTag())
                                       + "_FLH.png"
                                 )
                           ),
                           Texture.TextureFilter.Linear
                        );
                     }
                  } else {
                     this.flagOfCivilization = new Image(
                        new Texture(
                           Gdx.files
                              .internal(
                                 "game/civilizations_editor/"
                                    + CFG.ideologiesManager.getRealTag(CFG.game.getCiv(this.savePlayer.iCivID).getCivTag())
                                    + "/"
                                    + CFG.ideologiesManager.getRealTag(CFG.game.getCiv(this.savePlayer.iCivID).getCivTag())
                                    + "_FLH.png"
                              )
                        ),
                        Texture.TextureFilter.Linear
                     );
                  }
               }
            }
         } catch (GdxRuntimeException var7) {
            this.disposePlayersFlag();
         } catch (OutOfMemoryError var8) {
            this.disposePlayersFlag();
         } catch (RuntimeException var9) {
            this.disposePlayersFlag();
         }
      }
   }

   public final void disposePlayersFlag() {
      try {
         if (this.flagOfCivilization != null) {
            this.flagOfCivilization.getTexture().dispose();
            this.flagOfCivilization = null;
         }
      } catch (RuntimeException var2) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var2);
         }
      }
   }

   public final int getCivID() {
      return this.savePlayer.iCivID;
   }

   public final void setCivID(int nCivID) {
      try {
         if (this.savePlayer.iCivID >= 0 && this.savePlayer.iCivID < CFG.game.getCivsSize()) {
            CFG.game.getCiv(this.savePlayer.iCivID).setControlledByPlayer(false);
         }

         this.savePlayer.iCivID = nCivID;
         if (this.savePlayer.iCivID >= 0 && this.savePlayer.iCivID < CFG.game.getCivsSize()) {
            CFG.game.getCiv(this.savePlayer.iCivID).setControlledByPlayer(true);
            this.statistics_Civ_GameData = CFG.serviceRibbon_Manager.loadStatistics_Civ(CFG.game.getCiv(this.savePlayer.iCivID).getCivTag());
         }
      } catch (IndexOutOfBoundsException var3) {
         this.savePlayer.iCivID = nCivID;
      } catch (NullPointerException var4) {
         this.savePlayer.iCivID = nCivID;
      }
   }

   public final void tryLoadStats() {
      this.statistics_Civ_GameData = CFG.serviceRibbon_Manager.loadStatistics_Civ(CFG.game.getCiv(this.savePlayer.iCivID).getCivTag());
   }

   public final boolean getNoOrders() {
      return this.noOrders;
   }

   public final void setNoOrders(boolean noOrders) {
      this.noOrders = noOrders;
   }

   public final Image getFlag() {
      return this.flagOfCivilization == null ? CFG.game.getCiv(this.savePlayer.iCivID).getFlag() : this.flagOfCivilization;
   }

   public final boolean getMetProvince(int i) {
      try {
         return this.savePlayer.metProvince.get(i);
      } catch (IndexOutOfBoundsException var3) {
         return true;
      }
   }

   public final void setMetProvince(int i, boolean met) {
      try {
         this.savePlayer.metProvince.set(i, met);
      } catch (IndexOutOfBoundsException var4) {
      }
   }

   public final boolean getMetCivilization(int i) {
      try {
         return this.savePlayer.metCivilization.get(i);
      } catch (IndexOutOfBoundsException var3) {
         return true;
      }
   }

   public final void setMetCivilization(int i, boolean met) {
      try {
         this.savePlayer.metCivilization.set(i, met);
      } catch (IndexOutOfBoundsException var4) {
      }
   }

   public final void addMetCivilization(boolean metCiv) {
      this.savePlayer.metCivilization.add(metCiv);
   }

   public final boolean getMetAlliance(int nAllianceID) {
      for (int i = 0; i < CFG.game.getAlliance(nAllianceID).getCivilizationsSize(); i++) {
         if (this.getMetCivilization(CFG.game.getAlliance(nAllianceID).getCivilization(i))) {
            return true;
         }
      }

      return false;
   }

   public final boolean getFogOfWar(int i) {
      try {
         return this.fogOfWar.get(i);
      } catch (IndexOutOfBoundsException var3) {
         CFG.exceptionStack(var3);
         return true;
      }
   }

   public final void setFogOfWar(int i, boolean isVisible) {
      try {
         this.fogOfWar.set(i, isVisible);
      } catch (IndexOutOfBoundsException var4) {
         CFG.exceptionStack(var4);
      }
   }

   public final void setFogOfWar_ExtraCheck(int i, boolean isVisible) {
      try {
         this.fogOfWar.set(i, isVisible || CFG.game.getProvince(i).getArmyCivID(this.getCivID()) > 0);
      } catch (IndexOutOfBoundsException var4) {
         CFG.exceptionStack(var4);
      }
   }
}
