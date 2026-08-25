package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.Json;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Game_Scenarios {
   public static int SCENARIOS_SIZE;
   public List<String> lScenarios_TagsList = new ArrayList<>();
   public List<Boolean> isInternal = new ArrayList<>();
   public List<String> lScenarios_Names = new ArrayList<>();
   public List<Integer> lScenarios_CivNum = new ArrayList<>();
   public List<String> lScenarios_Authors = new ArrayList<>();
   public List<Integer> lScenarios_Age = new ArrayList<>();
   public List<Integer> lScenarios_Year = new ArrayList<>();
   public List<Integer> lScenarios_Month = new ArrayList<>();
   public List<Integer> lScenarios_Day = new ArrayList<>();
   public List<String> lScenarios_Wikis = new ArrayList<>();
   public int iScenario_StartingArmyInCapitals = 750;
   public int iScenario_NeutralArmy = 150;
   public int iScenario_StartingPopulation = 65000;
   public int iScenario_StartingEconomy = 32000;
   public int iScenario_StartingMoney = 4500;
   public float iScenario_PopulationGrowthRate_Modifier = 0.0F;
   public float iScenario_EconomyGrowthRate_Modifier = 0.0F;
   public float iScenario_DiseasesDeathRate_Modifier = 0.0F;
   public String sScenario_ActivePallet_TAG = null;
   public String sActiveScenarioTag = "";
   public static final float PERC_OF_POPULATION_REQUIRED_TO_GET_A_CORE = 0.18F;

   Game_Scenarios() {
   }

   public final void loadGame_Scenarios(boolean initMap) {
      if (SCENARIOS_SIZE > 0 || this.lScenarios_TagsList.size() > 0) {
         this.disposeScenarios();
      }

      String defaultScenario = null;
      String[] tagsSPLITED = null;
      if (CFG.isDesktop()) {
         List<String> tempFiles = CFG.getFileNames("map/" + CFG.map.getFile_ActiveMap_Path() + "scenarios/");
         int iSize = tempFiles.size();

         for (int i2 = 0; i2 < iSize; i2++) {
            if (tempFiles.get(i2).equals("Age_of_Civilizations")) {
               tempFiles.remove(i2);
               break;
            }
         }

         tagsSPLITED = new String[tempFiles.size()];
         iSize = tempFiles.size();

         for (int var33 = 0; var33 < iSize; var33++) {
            tagsSPLITED[var33] = tempFiles.get(var33);
         }
      } else {
         FileHandle tempFileT = Gdx.files.internal("map/" + CFG.map.getFile_ActiveMap_Path() + "scenarios/Age_of_Civilizations");
         String tempT = tempFileT.readString();
         tagsSPLITED = tempT.split(";");
      }

      ArrayList<String> tempScenarios_TagsList = new ArrayList<>();
      ArrayList<Boolean> tempIsInternal = new ArrayList<>();
      ArrayList<String> tempScenarios_Names = new ArrayList<>();
      ArrayList<Integer> tempScenarios_CivNum = new ArrayList<>();
      ArrayList<String> tempScenarios_Authors = new ArrayList<>();
      ArrayList<Integer> tempScenarios_Age = new ArrayList<>();
      ArrayList<Integer> tempScenarios_Year = new ArrayList<>();
      ArrayList<Integer> tempScenarios_Month = new ArrayList<>();
      ArrayList<Integer> tempScenarios_Day = new ArrayList<>();
      ArrayList<String> tempScenarios_Wikis = new ArrayList<>();
      int iSize = tagsSPLITED.length;

      for (int i = 0; i < iSize; i++) {
         tempScenarios_TagsList.add(tagsSPLITED[i]);
         tempIsInternal.add(true);
      }

      for (int var31 = 0; var31 < tempScenarios_TagsList.size(); var31++) {
         try {
            new CFG.ConfigScenarioInfo();
            Json json = new Json();
            json.setElementType(CFG.ConfigScenarioInfo.class, "Data_Scenario_Info", CFG.Data_Scenario_Info.class);
            CFG.ConfigScenarioInfo i5 = json.fromJson(
               CFG.ConfigScenarioInfo.class,
               Gdx.files
                  .internal(
                     "map/"
                        + CFG.map.getFile_ActiveMap_Path()
                        + "scenarios/"
                        + tempScenarios_TagsList.get(var31)
                        + "/"
                        + tempScenarios_TagsList.get(var31)
                        + "_INFO.json"
                  )
                  .reader("UTF8")
            );
            Iterator iterator = i5.Data_Scenario_Info.iterator();
            if (iterator.hasNext()) {
               Object e = iterator.next();
               CFG.Data_Scenario_Info tempData = (CFG.Data_Scenario_Info)e;
               tempScenarios_CivNum.add(tempData.Civs);
               tempScenarios_Names.add(tempData.Name);
               tempScenarios_Authors.add(tempData.Author);
               tempScenarios_Wikis.add(tempData.Wiki);
               tempScenarios_Age.add(tempData.Age);
               tempScenarios_Year.add(tempData.Year);
               tempScenarios_Month.add(tempData.Month);
               tempScenarios_Day.add(tempData.Day);
            }
         } catch (GdxRuntimeException var30) {
            if (CFG.LOGS) {
               CFG.exceptionStack(var30);
            }

            tempScenarios_CivNum.add(0);
            tempScenarios_Names.add("ERROR");
            tempScenarios_Authors.add("ERROR");
            tempScenarios_Wikis.add("");
            tempScenarios_Age.add(0);
            tempScenarios_Year.add(0);
            tempScenarios_Month.add(0);
            tempScenarios_Day.add(0);
         }
      }

      if (CFG.readLocalFiles()) {
         try {
            FileHandle tempFileT2 = Gdx.files.local("map/" + CFG.map.getFile_ActiveMap_Path() + "scenarios/Age_of_Civilizations");
            String tempT2 = tempFileT2.readString();
            String[] tagsSPLITED2 = tempT2.split(";");
            int nStart = tempScenarios_TagsList.size();
            int iSize2 = tagsSPLITED2.length;

            for (int i3 = 0; i3 < iSize2; i3++) {
               tempScenarios_TagsList.add(tagsSPLITED2[i3]);
               tempIsInternal.add(false);
            }

            for (int var41 = nStart; var41 < tempScenarios_TagsList.size(); var41++) {
               FileHandle file = Gdx.files
                  .local(
                     "map/"
                        + CFG.map.getFile_ActiveMap_Path()
                        + "scenarios/"
                        + tempScenarios_TagsList.get(var41)
                        + "/"
                        + tempScenarios_TagsList.get(var41)
                        + "_INFO.json"
                  );
               String fileContent = file.readString();
               Json json = new Json();
               json.setElementType(CFG.ConfigScenarioInfo.class, "Data_Scenario_Info", CFG.Data_Scenario_Info.class);
               new CFG.ConfigScenarioInfo();
               CFG.ConfigScenarioInfo data = json.fromJson(CFG.ConfigScenarioInfo.class, fileContent);
               Iterator iterator = data.Data_Scenario_Info.iterator();
               if (iterator.hasNext()) {
                  Object e = iterator.next();
                  CFG.Data_Scenario_Info tempData = (CFG.Data_Scenario_Info)e;
                  tempScenarios_CivNum.add(tempData.Civs);
                  tempScenarios_Names.add(tempData.Name);
                  tempScenarios_Authors.add(tempData.Author);
                  tempScenarios_Wikis.add(tempData.Wiki);
                  tempScenarios_Age.add(tempData.Age);
                  tempScenarios_Year.add(tempData.Year);
                  tempScenarios_Month.add(tempData.Month);
                  tempScenarios_Day.add(tempData.Day);
               }
            }
         } catch (GdxRuntimeException var29) {
         }
      }

      if (CFG.game.getScenarioID() == -1) {
         defaultScenario = tempScenarios_TagsList.get(0);
         CFG.game.setScenarioID(0);
      }

      while (tempScenarios_TagsList.size() > 0) {
         int nAdd = 0;

         for (int i4 = 1; i4 < tempScenarios_TagsList.size(); i4++) {
            if (tempScenarios_Year.get(nAdd) < tempScenarios_Year.get(i4)) {
               nAdd = i4;
            }
         }

         this.lScenarios_TagsList.add(tempScenarios_TagsList.get(nAdd));
         tempScenarios_TagsList.remove(nAdd);
         this.isInternal.add(tempIsInternal.get(nAdd));
         tempIsInternal.remove(nAdd);
         this.lScenarios_CivNum.add(tempScenarios_CivNum.get(nAdd));
         tempScenarios_CivNum.remove(nAdd);
         this.lScenarios_Names.add(tempScenarios_Names.get(nAdd));
         tempScenarios_Names.remove(nAdd);
         this.lScenarios_Authors.add(tempScenarios_Authors.get(nAdd));
         tempScenarios_Authors.remove(nAdd);
         this.lScenarios_Wikis.add(tempScenarios_Wikis.get(nAdd));
         tempScenarios_Wikis.remove(nAdd);
         this.lScenarios_Age.add(tempScenarios_Age.get(nAdd));
         tempScenarios_Age.remove(nAdd);
         this.lScenarios_Year.add(tempScenarios_Year.get(nAdd));
         tempScenarios_Year.remove(nAdd);
         this.lScenarios_Month.add(tempScenarios_Month.get(nAdd));
         tempScenarios_Month.remove(nAdd);
         this.lScenarios_Day.add(tempScenarios_Day.get(nAdd));
         tempScenarios_Day.remove(nAdd);
      }

      if (defaultScenario != null) {
         for (int i5 = 0; i5 < this.lScenarios_TagsList.size(); i5++) {
            if (defaultScenario.equals(this.lScenarios_TagsList.get(i5))) {
               CFG.game.setScenarioID(i5);
               break;
            }
         }
      }

      SCENARIOS_SIZE = this.lScenarios_TagsList.size();
      if (initMap) {
         CFG.game.updateDaultScenarioID_ForMap();
      }
   }

   public final void disposeScenarios() {
      this.lScenarios_TagsList.clear();
      this.lScenarios_TagsList = new ArrayList<>();
      this.lScenarios_Names.clear();
      this.lScenarios_Names = new ArrayList<>();
      this.lScenarios_CivNum.clear();
      this.lScenarios_CivNum = new ArrayList<>();
      this.lScenarios_Authors.clear();
      this.lScenarios_Authors = new ArrayList<>();
      this.lScenarios_Wikis.clear();
      this.lScenarios_Wikis = new ArrayList<>();
      this.lScenarios_Age.clear();
      this.lScenarios_Age = new ArrayList<>();
      this.lScenarios_Year.clear();
      this.lScenarios_Year = new ArrayList<>();
      this.lScenarios_Month.clear();
      this.lScenarios_Month = new ArrayList<>();
      this.lScenarios_Day.clear();
      this.lScenarios_Day = new ArrayList<>();
      this.isInternal.clear();
      this.isInternal = new ArrayList<>();
      SCENARIOS_SIZE = 0;
   }

   public final List<Civilization> loadCivilizations_RandomGame() {
      Random oR = new Random();
      ArrayList<Civilization> lCivs = new ArrayList<>();
      lCivs.add(CFG.game.getNeutralCivilization());
      lCivs.get(0).setCivID(0);
      ArrayList<String> lRandomGameCivsTags = new ArrayList<>();
      if (CFG.RANDOM_PLACMENT) {
         FileHandle tempFileT = Gdx.files.internal("game/civilizations/Age_of_Civilizations");
         String tempT = tempFileT.readString();
         String[] tagsSPLITED = tempT.split(";");
         String[] tagsSPLITED_ED = new String[0];

         try {
            FileHandle tempFileT_ED = null;
            tempFileT_ED = CFG.isAndroid()
               ? Gdx.files.local("game/civilizations_editor/Age_of_Civilizations")
               : Gdx.files.internal("game/civilizations_editor/Age_of_Civilizations");
            String tempT_ED = tempFileT_ED.readString();
            tagsSPLITED_ED = tempT_ED.split(";");
         } catch (GdxRuntimeException var52) {
         }

         ArrayList<String> nCivsTags = new ArrayList<>();
         int iSize = tagsSPLITED.length;

         for (int i = 0; i < iSize; i++) {
            if (!CFG.randomGameManager.isTagTaken(tagsSPLITED[i])) {
               nCivsTags.add(tagsSPLITED[i]);
            }
         }

         iSize = tagsSPLITED_ED.length;

         for (int var68 = 0; var68 < iSize; var68++) {
            if (!CFG.randomGameManager.isTagTaken(tagsSPLITED_ED[var68])) {
               nCivsTags.add(tagsSPLITED[var68]);
            }
         }

         for (int var69 = 0; var69 < CFG.randomGameManager.getPlayersSize(); var69++) {
            if (CFG.randomGameManager.getPlayer(var69).getTag() != null) {
               lRandomGameCivsTags.add(CFG.randomGameManager.getPlayer(var69).getTag());
            } else {
               int nR = oR.nextInt(nCivsTags.size());
               lRandomGameCivsTags.add(nCivsTags.get(nR));
               nCivsTags.remove(nR);
            }
         }

         try {
            int nR = 0;

            for (int var70 = 0; var70 < CFG.randomGameManager.getCivilizationsSize(); var70++) {
               nR = oR.nextInt(nCivsTags.size());
               lRandomGameCivsTags.add(nCivsTags.get(nR));
               nCivsTags.remove(nR);
            }
         } catch (IllegalArgumentException var62) {
         }

         try {
            String tempTag = null;
            boolean add = true;
            int iSize2 = lRandomGameCivsTags.size();

            for (int i3 = 0; i3 < iSize2; i3++) {
               int nRandIdeology = oR.nextInt(CFG.ideologiesManager.getIdeologiesSize());
               int nNumOfTries = 0;

               while (
                  (CFG.ideologiesManager.getIdeology(nRandIdeology).REVOLUTIONARY || CFG.ideologiesManager.getIdeology(nRandIdeology).CAN_BECOME_CIVILIZED >= 0)
                     && nNumOfTries++ < 8
               ) {
                  nRandIdeology = oR.nextInt(CFG.ideologiesManager.getIdeologiesSize());
               }

               add = true;
               tempTag = CFG.ideologiesManager.getRealTag(lRandomGameCivsTags.get(i3)) + CFG.ideologiesManager.getIdeology(nRandIdeology).getExtraTag();

               for (int j = i3 + 1; j < iSize2; j++) {
                  if (tempTag.equals(lRandomGameCivsTags.get(j))) {
                     add = false;
                     break;
                  }
               }

               if (add) {
                  for (int var112 = i3 - 1; var112 >= 0; var112--) {
                     if (tempTag.equals(lRandomGameCivsTags.get(var112))) {
                        add = false;
                        break;
                     }
                  }

                  if (add) {
                     lRandomGameCivsTags.set(i3, tempTag);
                  }
               }

               Civilization_GameData3 tempCivData;
               try {
                  try {
                     FileHandle fileCiv = Gdx.files.internal("game/civilizations/" + lRandomGameCivsTags.get(i3));
                     tempCivData = (Civilization_GameData3)CFG.deserialize(fileCiv.readBytes());
                  } catch (GdxRuntimeException var50) {
                     FileHandle fileCivx = Gdx.files.internal("game/civilizations/" + CFG.ideologiesManager.getRealTag(lRandomGameCivsTags.get(i3)));
                     tempCivData = (Civilization_GameData3)CFG.deserialize(fileCivx.readBytes());
                     int tempIdeologyID = CFG.ideologiesManager.getIdeologyID(lRandomGameCivsTags.get(i3));
                     Color tempColor = CFG.getColorMixed(
                        new Color(tempCivData.getR() / 255.0F, tempCivData.getG() / 255.0F, tempCivData.getB() / 255.0F, 0.775F),
                        new Color(
                           CFG.ideologiesManager.getIdeology(tempIdeologyID).getColor().r,
                           CFG.ideologiesManager.getIdeology(tempIdeologyID).getColor().g,
                           CFG.ideologiesManager.getIdeology(tempIdeologyID).getColor().b,
                           0.225F
                        )
                     );
                     tempCivData.setR((int)(tempColor.r * 255.0F));
                     tempCivData.setG((int)(tempColor.g * 255.0F));
                     tempCivData.setB((int)(tempColor.b * 255.0F));
                  }
               } catch (GdxRuntimeException var51) {
                  try {
                     FileHandle fileCiv = Gdx.files.local("game/civilizations/" + lRandomGameCivsTags.get(i3));
                     tempCivData = (Civilization_GameData3)CFG.deserialize(fileCiv.readBytes());
                  } catch (GdxRuntimeException var49) {
                     try {
                        FileHandle fileCivx = Gdx.files.local("game/civilizations/" + CFG.ideologiesManager.getRealTag(lRandomGameCivsTags.get(i3)));
                        tempCivData = (Civilization_GameData3)CFG.deserialize(fileCivx.readBytes());
                        int tempIdeologyID = CFG.ideologiesManager.getIdeologyID(lRandomGameCivsTags.get(i3));
                        Color tempColor = CFG.getColorMixed(
                           new Color(tempCivData.getR() / 255.0F, tempCivData.getG() / 255.0F, tempCivData.getB() / 255.0F, 0.775F),
                           new Color(
                              CFG.ideologiesManager.getIdeology(tempIdeologyID).getColor().r,
                              CFG.ideologiesManager.getIdeology(tempIdeologyID).getColor().g,
                              CFG.ideologiesManager.getIdeology(tempIdeologyID).getColor().b,
                              0.225F
                           )
                        );
                        tempCivData.setR((int)(tempColor.r * 255.0F));
                        tempCivData.setG((int)(tempColor.g * 255.0F));
                        tempCivData.setB((int)(tempColor.b * 255.0F));
                     } catch (GdxRuntimeException var48) {
                        try {
                           if (CFG.isAndroid()) {
                              try {
                                 FileHandle fileCivxx = Gdx.files
                                    .local(
                                       "game/civilizations_editor/"
                                          + CFG.ideologiesManager.getRealTag(lRandomGameCivsTags.get(i3))
                                          + "/"
                                          + CFG.ideologiesManager.getRealTag(lRandomGameCivsTags.get(i3))
                                    );
                                 tempCivData = (Civilization_GameData3)CFG.deserialize(fileCivxx.readBytes());
                              } catch (GdxRuntimeException var46) {
                                 FileHandle fileCivxxx = Gdx.files
                                    .internal(
                                       "game/civilizations_editor/"
                                          + CFG.ideologiesManager.getRealTag(lRandomGameCivsTags.get(i3))
                                          + "/"
                                          + CFG.ideologiesManager.getRealTag(lRandomGameCivsTags.get(i3))
                                    );
                                 tempCivData = (Civilization_GameData3)CFG.deserialize(fileCivxxx.readBytes());
                              }
                           } else {
                              FileHandle fileCivxx = Gdx.files
                                 .internal(
                                    "game/civilizations_editor/"
                                       + CFG.ideologiesManager.getRealTag(lRandomGameCivsTags.get(i3))
                                       + "/"
                                       + CFG.ideologiesManager.getRealTag(lRandomGameCivsTags.get(i3))
                                 );
                              tempCivData = (Civilization_GameData3)CFG.deserialize(fileCivxx.readBytes());
                           }
                        } catch (GdxRuntimeException var47) {
                           Color tempC = CFG.getRandomColor();
                           tempCivData = new Civilization_GameData3("ran", (int)(tempC.r * 255.0F), (int)(tempC.g * 255.0F), (int)(tempC.b * 255.0F));
                        }
                     }
                  }
               }

               int tCapital = 0;
               tCapital = i3 < CFG.randomGameManager.getPlayersSize() && CFG.randomGameManager.getPlayer(i3).getCapitalProvinceID() >= 0
                  ? CFG.randomGameManager.getPlayer(i3).getCapitalProvinceID()
                  : -1;
               lCivs.add(new Civilization(lRandomGameCivsTags.get(i3), tempCivData.getR(), tempCivData.getG(), tempCivData.getB(), tCapital, i3 + 1));
               lCivs.get(i3 + 1).setCivID(i3 + 1);
               lCivs.get(i3 + 1).setTechnologyLevel((20 + Math.min(5 * Game_Calendar.CURRENT_AGEID, 25) + oR.nextInt(10)) / 100.0F);
               lCivs.get(i3 + 1).setHappiness(68 + oR.nextInt(16));
               if (tCapital >= 0) {
                  CFG.game.getProvince(lCivs.get(i3 + 1).getCapitalProvinceID()).setCivID_LoadScenario(i3 + 1);
               }

               lCivs.get(i3 + 1).setMoney(CFG.game.getGameScenarios().getScenario_StartingMoney());
            }
         } catch (ClassNotFoundException var60) {
         } catch (IOException var61) {
         }
      } else {
         FileHandle tempFileT = Gdx.files.internal("game/civilizations/Age_of_Civilizations");
         String tempT = tempFileT.readString();
         String[] tagsSPLITED = tempT.split(";");
         String[] tagsSPLITED_ED = new String[0];

         try {
            FileHandle tempFileT_ED = null;
            tempFileT_ED = CFG.isAndroid()
               ? Gdx.files.local("game/civilizations_editor/Age_of_Civilizations")
               : Gdx.files.internal("game/civilizations_editor/Age_of_Civilizations");
            String tempT_ED = tempFileT_ED.readString();
            tagsSPLITED_ED = tempT_ED.split(";");
         } catch (GdxRuntimeException var45) {
         }

         ArrayList<String> nCivsTags = new ArrayList<>();
         ArrayList<Game_Scenarios.RandomGame_AoCMode> civsToAdd = new ArrayList<>();
         int iSize = tagsSPLITED.length;

         for (int i4 = 0; i4 < iSize; i4++) {
            if (!CFG.randomGameManager.isTagTaken(tagsSPLITED[i4])) {
               nCivsTags.add(tagsSPLITED[i4]);
            }
         }

         iSize = tagsSPLITED_ED.length;

         for (int var72 = 0; var72 < iSize; var72++) {
            if (!CFG.randomGameManager.isTagTaken(tagsSPLITED_ED[var72])) {
               nCivsTags.add(tagsSPLITED[var72]);
            }
         }

         for (int var73 = 0; var73 < CFG.randomGameManager.getPlayersSize(); var73++) {
            if (CFG.randomGameManager.getPlayer(var73).getTag() != null) {
               civsToAdd.add(
                  new Game_Scenarios.RandomGame_AoCMode(
                     CFG.randomGameManager.getPlayer(var73).getTag(), CFG.randomGameManager.getPlayer(var73).getCapitalProvinceID()
                  )
               );
            } else if (CFG.randomGameManager.getPlayer(var73).getCapitalProvinceID() >= 0) {
               int nR = oR.nextInt(nCivsTags.size());
               civsToAdd.add(new Game_Scenarios.RandomGame_AoCMode(nCivsTags.get(nR), CFG.randomGameManager.getPlayer(var73).getCapitalProvinceID()));
               nCivsTags.remove(nR);
            }
         }

         for (int o = 0; o < civsToAdd.size(); o++) {
            try {
               Civilization_GameData3 tempCivDatax;
               try {
                  try {
                     FileHandle fileCiv = Gdx.files.internal("game/civilizations/" + civsToAdd.get(o).sTag);
                     tempCivDatax = (Civilization_GameData3)CFG.deserialize(fileCiv.readBytes());
                  } catch (GdxRuntimeException var41) {
                     FileHandle fileCivx = Gdx.files.internal("game/civilizations/" + CFG.ideologiesManager.getRealTag(civsToAdd.get(o).sTag));
                     tempCivDatax = (Civilization_GameData3)CFG.deserialize(fileCivx.readBytes());
                     int tempIdeologyID = CFG.ideologiesManager.getIdeologyID(civsToAdd.get(o).sTag);
                     Color tempColor = CFG.getColorMixed(
                        new Color(tempCivDatax.getR() / 255.0F, tempCivDatax.getG() / 255.0F, tempCivDatax.getB() / 255.0F, 0.775F),
                        new Color(
                           CFG.ideologiesManager.getIdeology(tempIdeologyID).getColor().r,
                           CFG.ideologiesManager.getIdeology(tempIdeologyID).getColor().g,
                           CFG.ideologiesManager.getIdeology(tempIdeologyID).getColor().b,
                           0.225F
                        )
                     );
                     tempCivDatax.setR((int)(tempColor.r * 255.0F));
                     tempCivDatax.setG((int)(tempColor.g * 255.0F));
                     tempCivDatax.setB((int)(tempColor.b * 255.0F));
                  }
               } catch (GdxRuntimeException var42) {
                  try {
                     FileHandle fileCiv = Gdx.files.local("game/civilizations/" + civsToAdd.get(o).sTag);
                     tempCivDatax = (Civilization_GameData3)CFG.deserialize(fileCiv.readBytes());
                  } catch (GdxRuntimeException var40) {
                     try {
                        FileHandle fileCivx = Gdx.files.local("game/civilizations/" + CFG.ideologiesManager.getRealTag(civsToAdd.get(o).sTag));
                        tempCivDatax = (Civilization_GameData3)CFG.deserialize(fileCivx.readBytes());
                        int tempIdeologyID = CFG.ideologiesManager.getIdeologyID(civsToAdd.get(o).sTag);
                        Color tempColor = CFG.getColorMixed(
                           new Color(tempCivDatax.getR() / 255.0F, tempCivDatax.getG() / 255.0F, tempCivDatax.getB() / 255.0F, 0.775F),
                           new Color(
                              CFG.ideologiesManager.getIdeology(tempIdeologyID).getColor().r,
                              CFG.ideologiesManager.getIdeology(tempIdeologyID).getColor().g,
                              CFG.ideologiesManager.getIdeology(tempIdeologyID).getColor().b,
                              0.225F
                           )
                        );
                        tempCivDatax.setR((int)(tempColor.r * 255.0F));
                        tempCivDatax.setG((int)(tempColor.g * 255.0F));
                        tempCivDatax.setB((int)(tempColor.b * 255.0F));
                     } catch (GdxRuntimeException var39) {
                        try {
                           if (CFG.isAndroid()) {
                              try {
                                 FileHandle fileCivxx = Gdx.files
                                    .local(
                                       "game/civilizations_editor/"
                                          + CFG.ideologiesManager.getRealTag(civsToAdd.get(o).sTag)
                                          + "/"
                                          + CFG.ideologiesManager.getRealTag(civsToAdd.get(o).sTag)
                                    );
                                 tempCivDatax = (Civilization_GameData3)CFG.deserialize(fileCivxx.readBytes());
                              } catch (GdxRuntimeException var37) {
                                 FileHandle fileCivxxx = Gdx.files
                                    .internal(
                                       "game/civilizations_editor/"
                                          + CFG.ideologiesManager.getRealTag(civsToAdd.get(o).sTag)
                                          + "/"
                                          + CFG.ideologiesManager.getRealTag(civsToAdd.get(o).sTag)
                                    );
                                 tempCivDatax = (Civilization_GameData3)CFG.deserialize(fileCivxxx.readBytes());
                              }
                           } else {
                              FileHandle fileCivxx = Gdx.files
                                 .internal(
                                    "game/civilizations_editor/"
                                       + CFG.ideologiesManager.getRealTag(civsToAdd.get(o).sTag)
                                       + "/"
                                       + CFG.ideologiesManager.getRealTag(civsToAdd.get(o).sTag)
                                 );
                              tempCivDatax = (Civilization_GameData3)CFG.deserialize(fileCivxx.readBytes());
                           }
                        } catch (GdxRuntimeException var38) {
                           Color tempC = CFG.getRandomColor();
                           tempCivDatax = new Civilization_GameData3("ran", (int)(tempC.r * 255.0F), (int)(tempC.g * 255.0F), (int)(tempC.b * 255.0F));
                        }
                     }
                  }
               }

               int tCapital = civsToAdd.get(o).iCapitalID;
               lCivs.add(new Civilization(civsToAdd.get(o).sTag, tempCivDatax.getR(), tempCivDatax.getG(), tempCivDatax.getB(), tCapital, o + 1));
               lCivs.get(o + 1).setCivID(o + 1);
               lCivs.get(o + 1).setTechnologyLevel((20 + Math.min(5 * Game_Calendar.CURRENT_AGEID, 25) + oR.nextInt(10)) / 100.0F);
               lCivs.get(o + 1).setHappiness(68 + oR.nextInt(16));
               if (tCapital >= 0) {
                  CFG.game.getProvince(lCivs.get(o + 1).getCapitalProvinceID()).setCivID_LoadScenario(o + 1);
               }

               lCivs.get(o + 1).setMoney(CFG.game.getGameScenarios().getScenario_StartingMoney());
            } catch (ClassNotFoundException var43) {
            } catch (IOException var44) {
            }
         }

         ArrayList<Integer> lPossibleCapitals = new ArrayList<>();

         for (int ix = 0; ix < CFG.game.getProvincesSize(); ix++) {
            if (!CFG.game.getProvince(ix).getSeaProvince()) {
               CFG.game.getProvince(ix).setIsCapital(false);
            }
         }

         for (int var66 = 0; var66 < CFG.randomGameManager.getPlayersSize(); var66++) {
            if (CFG.randomGameManager.getPlayer(var66).getCapitalProvinceID() >= 0) {
               CFG.game.getProvince(CFG.randomGameManager.getPlayer(var66).getCapitalProvinceID()).setIsCapital(true);
            }
         }

         for (int var67 = 0; var67 < CFG.game.getProvincesSize(); var67++) {
            if (!CFG.game.getProvince(var67).getSeaProvince() && CFG.game.getProvince(var67).getWasteland() < 0 && !CFG.game.getProvince(var67).getIsCapital()) {
               try {
                  if (Gdx.files.internal("map/" + CFG.map.getFile_ActiveMap_Path() + "suggested_owners/" + var67).exists()) {
                     lPossibleCapitals.add(var67);
                  }
               } catch (GdxRuntimeException var36) {
               }
            }
         }

         try {
            int extraToAddForPlayers = civsToAdd.size() - CFG.randomGameManager.getPlayersSize();
            boolean nR = false;

            for (int i5 = 0; i5 < CFG.randomGameManager.getCivilizationsSize() + extraToAddForPlayers && lPossibleCapitals.size() > 0; i5++) {
               try {
                  int tempCapitalID = 0;
                  int iNumOfItterations = 0;

                  while (true) {
                     int tRandID = CFG.oR.nextInt(lPossibleCapitals.size());
                     tempCapitalID = lPossibleCapitals.get(tRandID);
                     iNumOfItterations++;
                     if (!CFG.game.getProvince(tempCapitalID).getIsCapital()) {
                        boolean found = true;

                        for (int o = 0; o < CFG.game.getProvince(tempCapitalID).getNeighboringProvincesSize(); o++) {
                           if (CFG.game.getProvince(CFG.game.getProvince(tempCapitalID).getNeighboringProvinces(o)).getIsCapital_Just()) {
                              found = false;
                              break;
                           }
                        }

                        if (found || iNumOfItterations > 18) {
                           found = false;
                           ArrayList<String> lPossibleCapitals_Tags = new ArrayList<>();

                           try {
                              FileHandle file = Gdx.files
                                 .internal("map/" + CFG.map.getFile_ActiveMap_Path() + "suggested_owners/" + lPossibleCapitals.get(tRandID));
                              String sOwners = file.readString();
                              String[] sRes = sOwners.split(";");

                              for (int jx = 0; jx < sRes.length; jx += 2) {
                                 int nIdeology = CFG.ideologiesManager.getIdeologyID(sRes[jx]);
                                 if (CFG.ideologiesManager.getIdeology(nIdeology).CAN_BECOME_CIVILIZED >= 0) {
                                    lPossibleCapitals_Tags.add(CFG.ideologiesManager.getRealTag(sRes[jx]));
                                 } else {
                                    lPossibleCapitals_Tags.add(sRes[jx]);
                                 }
                              }

                              for (int var129 = lPossibleCapitals_Tags.size() - 1; var129 >= 0; var129--) {
                                 for (int k = civsToAdd.size() - 1; k >= 0; k--) {
                                    if (civsToAdd.get(k).sTag.equals(lPossibleCapitals_Tags.get(var129))) {
                                       lPossibleCapitals_Tags.remove(var129);
                                       break;
                                    }
                                 }
                              }

                              if (lPossibleCapitals_Tags.size() == 0) {
                                 lPossibleCapitals.remove(tRandID);
                                 continue;
                              }

                              found = true;
                           } catch (GdxRuntimeException var55) {
                              lPossibleCapitals.remove(tRandID);
                              continue;
                           }

                           if (!found) {
                              break;
                           }

                           try {
                              int nTagIDToAdd = CFG.oR.nextInt(lPossibleCapitals_Tags.size());

                              Civilization_GameData3 tempCivDataxx;
                              try {
                                 try {
                                    FileHandle fileCiv = Gdx.files.internal("game/civilizations/" + lPossibleCapitals_Tags.get(nTagIDToAdd));
                                    tempCivDataxx = (Civilization_GameData3)CFG.deserialize(fileCiv.readBytes());
                                 } catch (GdxRuntimeException var34) {
                                    FileHandle fileCivx = Gdx.files
                                       .internal("game/civilizations/" + CFG.ideologiesManager.getRealTag(lPossibleCapitals_Tags.get(nTagIDToAdd)));
                                    tempCivDataxx = (Civilization_GameData3)CFG.deserialize(fileCivx.readBytes());
                                    int tempIdeologyID = CFG.ideologiesManager.getIdeologyID(lPossibleCapitals_Tags.get(nTagIDToAdd));
                                    Color tempColor = CFG.getColorMixed(
                                       new Color(tempCivDataxx.getR() / 255.0F, tempCivDataxx.getG() / 255.0F, tempCivDataxx.getB() / 255.0F, 0.775F),
                                       new Color(
                                          CFG.ideologiesManager.getIdeology(tempIdeologyID).getColor().r,
                                          CFG.ideologiesManager.getIdeology(tempIdeologyID).getColor().g,
                                          CFG.ideologiesManager.getIdeology(tempIdeologyID).getColor().b,
                                          0.225F
                                       )
                                    );
                                    tempCivDataxx.setR((int)(tempColor.r * 255.0F));
                                    tempCivDataxx.setG((int)(tempColor.g * 255.0F));
                                    tempCivDataxx.setB((int)(tempColor.b * 255.0F));
                                 }
                              } catch (GdxRuntimeException var35) {
                                 try {
                                    FileHandle fileCiv = Gdx.files.local("game/civilizations/" + lPossibleCapitals_Tags.get(nTagIDToAdd));
                                    tempCivDataxx = (Civilization_GameData3)CFG.deserialize(fileCiv.readBytes());
                                 } catch (GdxRuntimeException var33) {
                                    try {
                                       FileHandle fileCivx = Gdx.files
                                          .local("game/civilizations/" + CFG.ideologiesManager.getRealTag(lPossibleCapitals_Tags.get(nTagIDToAdd)));
                                       tempCivDataxx = (Civilization_GameData3)CFG.deserialize(fileCivx.readBytes());
                                       int tempIdeologyID = CFG.ideologiesManager.getIdeologyID(lPossibleCapitals_Tags.get(nTagIDToAdd));
                                       Color tempColor = CFG.getColorMixed(
                                          new Color(tempCivDataxx.getR() / 255.0F, tempCivDataxx.getG() / 255.0F, tempCivDataxx.getB() / 255.0F, 0.775F),
                                          new Color(
                                             CFG.ideologiesManager.getIdeology(tempIdeologyID).getColor().r,
                                             CFG.ideologiesManager.getIdeology(tempIdeologyID).getColor().g,
                                             CFG.ideologiesManager.getIdeology(tempIdeologyID).getColor().b,
                                             0.225F
                                          )
                                       );
                                       tempCivDataxx.setR((int)(tempColor.r * 255.0F));
                                       tempCivDataxx.setG((int)(tempColor.g * 255.0F));
                                       tempCivDataxx.setB((int)(tempColor.b * 255.0F));
                                    } catch (GdxRuntimeException var32) {
                                       try {
                                          if (CFG.isAndroid()) {
                                             try {
                                                FileHandle fileCivxx = Gdx.files
                                                   .local(
                                                      "game/civilizations_editor/"
                                                         + CFG.ideologiesManager.getRealTag(lPossibleCapitals_Tags.get(nTagIDToAdd))
                                                         + "/"
                                                         + CFG.ideologiesManager.getRealTag(lPossibleCapitals_Tags.get(nTagIDToAdd))
                                                   );
                                                tempCivDataxx = (Civilization_GameData3)CFG.deserialize(fileCivxx.readBytes());
                                             } catch (GdxRuntimeException var30) {
                                                FileHandle fileCivxxx = Gdx.files
                                                   .internal(
                                                      "game/civilizations_editor/"
                                                         + CFG.ideologiesManager.getRealTag(lPossibleCapitals_Tags.get(nTagIDToAdd))
                                                         + "/"
                                                         + CFG.ideologiesManager.getRealTag(lPossibleCapitals_Tags.get(nTagIDToAdd))
                                                   );
                                                tempCivDataxx = (Civilization_GameData3)CFG.deserialize(fileCivxxx.readBytes());
                                             }
                                          } else {
                                             FileHandle fileCivxx = Gdx.files
                                                .internal(
                                                   "game/civilizations_editor/"
                                                      + CFG.ideologiesManager.getRealTag(lPossibleCapitals_Tags.get(nTagIDToAdd))
                                                      + "/"
                                                      + CFG.ideologiesManager.getRealTag(lPossibleCapitals_Tags.get(nTagIDToAdd))
                                                );
                                             tempCivDataxx = (Civilization_GameData3)CFG.deserialize(fileCivxx.readBytes());
                                          }
                                       } catch (GdxRuntimeException var31) {
                                          Color tempC = CFG.getRandomColor();
                                          tempCivDataxx = new Civilization_GameData3(
                                             "ran", (int)(tempC.r * 255.0F), (int)(tempC.g * 255.0F), (int)(tempC.b * 255.0F)
                                          );
                                       }
                                    }
                                 }
                              }

                              int tCapital = lPossibleCapitals.get(tRandID);
                              civsToAdd.add(new Game_Scenarios.RandomGame_AoCMode(lPossibleCapitals_Tags.get(nTagIDToAdd), tCapital));
                              int tCivID = lCivs.size();
                              lCivs.add(
                                 new Civilization(
                                    lPossibleCapitals_Tags.get(nTagIDToAdd), tempCivDataxx.getR(), tempCivDataxx.getG(), tempCivDataxx.getB(), tCapital, tCivID
                                 )
                              );
                              lCivs.get(tCivID).setCivID(tCivID);
                              lCivs.get(tCivID).setTechnologyLevel((20 + Math.min(5 * Game_Calendar.CURRENT_AGEID, 25) + oR.nextInt(10)) / 100.0F);
                              lCivs.get(tCivID).setHappiness(68 + oR.nextInt(16));
                              if (tCapital >= 0) {
                                 CFG.game.getProvince(lCivs.get(tCivID).getCapitalProvinceID()).setCivID_LoadScenario(tCivID);
                                 CFG.game.getProvince(tCapital).setIsCapital(true);
                              }

                              lCivs.get(tCivID).setMoney(CFG.game.getGameScenarios().getScenario_StartingMoney());
                              lPossibleCapitals.remove(tRandID);
                              break;
                           } catch (ClassNotFoundException var53) {
                              lPossibleCapitals.remove(tRandID);
                           } catch (IOException var54) {
                              lPossibleCapitals.remove(tRandID);
                           }
                        }
                     } else {
                        lPossibleCapitals.remove(tRandID);
                     }
                  }
               } catch (StackOverflowError var56) {
                  CFG.exceptionStack(var56);
               }
            }
         } catch (IllegalArgumentException var57) {
            CFG.exceptionStack(var57);
         } catch (IndexOutOfBoundsException var58) {
            CFG.exceptionStack(var58);
         } catch (NullPointerException var59) {
            CFG.exceptionStack(var59);
         }
      }

      return lCivs;
   }

   public final List<Civilization> loadCivilizationsLoadGame(List<Save_Civ_GameData> nCivsData) {
      Game_Calendar.updateAge(false);
      ArrayList<Civilization> lCivs = new ArrayList<>();
      lCivs.add(CFG.game.getNeutralCivilization());
      lCivs.get(0).setCivID(0);

      for (int i = 0; i < nCivsData.size(); i++) {
         lCivs.add(new Civilization(nCivsData.get(i), i + 1));
      }

      CFG.map.getMapBG().disposeMinimapOfCivilizations();
      return lCivs;
   }

   public final List<Civilization> loadCivilizations(boolean nEditor) {
      CFG.FILL_THE_MAP = true;
      Game_Calendar.CURRENT_AGEID = this.getScenarioAge(CFG.game.getScenarioID());
      ArrayList<Civilization> lCivs = new ArrayList<>();
      lCivs.add(CFG.game.getNeutralCivilization());
      lCivs.get(0).setCivID(0);
      FileHandle fileProvince;
      FileHandle file;
      if (this.isInternal.get(CFG.game.getScenarioID())) {
         file = Gdx.files
            .internal(
               "map/"
                  + CFG.map.getFile_ActiveMap_Path()
                  + "scenarios/"
                  + this.lScenarios_TagsList.get(CFG.game.getScenarioID())
                  + "/"
                  + this.lScenarios_TagsList.get(CFG.game.getScenarioID())
            );
         fileProvince = Gdx.files
            .internal(
               "map/"
                  + CFG.map.getFile_ActiveMap_Path()
                  + "scenarios/"
                  + this.lScenarios_TagsList.get(CFG.game.getScenarioID())
                  + "/"
                  + this.lScenarios_TagsList.get(CFG.game.getScenarioID())
                  + "_PD"
            );
      } else {
         file = Gdx.files
            .local(
               "map/"
                  + CFG.map.getFile_ActiveMap_Path()
                  + "scenarios/"
                  + this.lScenarios_TagsList.get(CFG.game.getScenarioID())
                  + "/"
                  + this.lScenarios_TagsList.get(CFG.game.getScenarioID())
            );
         fileProvince = Gdx.files
            .local(
               "map/"
                  + CFG.map.getFile_ActiveMap_Path()
                  + "scenarios/"
                  + this.lScenarios_TagsList.get(CFG.game.getScenarioID())
                  + "/"
                  + this.lScenarios_TagsList.get(CFG.game.getScenarioID())
                  + "_PD"
            );
      }

      try {
         Scenario_GameData tempScenarioGameData = (Scenario_GameData)CFG.deserialize(file.readBytes());
         this.setScenario_StartingArmyInCapitals(tempScenarioGameData.getStartingArmyInCapitals());
         this.setScenario_NeutralArmy(tempScenarioGameData.getNeutralArmy());
         this.setScenario_StartingPopulation(tempScenarioGameData.getStartingPopulation());
         this.setScenario_StartingEconomy(tempScenarioGameData.getStartingEconomy());
         this.setScenario_StartingMoney(tempScenarioGameData.getStartingMoney());
         this.setScenario_PopulationGrowthRate_Modifier(tempScenarioGameData.getPopulationGrowthRate_Modifier());
         this.setScenario_EconomyGrowthRate_Modifier(tempScenarioGameData.getEconomyGrowthRate_Modifier());
         this.setScenario_DiseasesDeathRate_Modifier(tempScenarioGameData.getDiseasesDeathRate_Modifier());
         this.setScenario_ActivePallet_TAG(tempScenarioGameData.getActivePalletOfColors_TAG());
         Game_Calendar.ENABLE_COLONIZATION = tempScenarioGameData.getColonization();
         Game_Calendar.ENABLE_COLONIZATION_NEUTRAL_PROVINCES = tempScenarioGameData.ENABLE_COLONIZATION_NEUTRAL_PROVINCES;
         Game_Calendar.COLONIZATION_TECH_LEVEL = tempScenarioGameData.COLONIZATION_TECH_LEVEL;

         for (int i = 0; i < tempScenarioGameData.getCivSize(); i++) {
            Civilization_GameData3 tempCivData;
            try {
               try {
                  FileHandle fileCiv = Gdx.files.internal("game/civilizations/" + tempScenarioGameData.getCivTag(i));
                  tempCivData = (Civilization_GameData3)CFG.deserialize(fileCiv.readBytes());
               } catch (GdxRuntimeException var23) {
                  FileHandle fileCivx = Gdx.files.internal("game/civilizations/" + CFG.ideologiesManager.getRealTag(tempScenarioGameData.getCivTag(i)));
                  tempCivData = (Civilization_GameData3)CFG.deserialize(fileCivx.readBytes());
                  int tempIdeologyID = CFG.ideologiesManager.getIdeologyID(tempScenarioGameData.getCivTag(i));
                  Color tempColor = CFG.getColorMixed(
                     new Color(tempCivData.getR() / 255.0F, tempCivData.getG() / 255.0F, tempCivData.getB() / 255.0F, 0.775F),
                     new Color(
                        CFG.ideologiesManager.getIdeology(tempIdeologyID).getColor().r,
                        CFG.ideologiesManager.getIdeology(tempIdeologyID).getColor().g,
                        CFG.ideologiesManager.getIdeology(tempIdeologyID).getColor().b,
                        0.225F
                     )
                  );
                  tempCivData.setR((int)(tempColor.r * 255.0F));
                  tempCivData.setG((int)(tempColor.g * 255.0F));
                  tempCivData.setB((int)(tempColor.b * 255.0F));
               }
            } catch (GdxRuntimeException var24) {
               try {
                  FileHandle fileCiv = Gdx.files.local("game/civilizations/" + tempScenarioGameData.getCivTag(i));
                  tempCivData = (Civilization_GameData3)CFG.deserialize(fileCiv.readBytes());
               } catch (GdxRuntimeException var22) {
                  try {
                     FileHandle fileCivx = Gdx.files.local("game/civilizations/" + CFG.ideologiesManager.getRealTag(tempScenarioGameData.getCivTag(i)));
                     tempCivData = (Civilization_GameData3)CFG.deserialize(fileCivx.readBytes());
                     int tempIdeologyID = CFG.ideologiesManager.getIdeologyID(tempScenarioGameData.getCivTag(i));
                     Color tempColor = CFG.getColorMixed(
                        new Color(tempCivData.getR() / 255.0F, tempCivData.getG() / 255.0F, tempCivData.getB() / 255.0F, 0.775F),
                        new Color(
                           CFG.ideologiesManager.getIdeology(tempIdeologyID).getColor().r,
                           CFG.ideologiesManager.getIdeology(tempIdeologyID).getColor().g,
                           CFG.ideologiesManager.getIdeology(tempIdeologyID).getColor().b,
                           0.225F
                        )
                     );
                     tempCivData.setR((int)(tempColor.r * 255.0F));
                     tempCivData.setG((int)(tempColor.g * 255.0F));
                     tempCivData.setB((int)(tempColor.b * 255.0F));
                  } catch (GdxRuntimeException var21) {
                     try {
                        if (CFG.isAndroid()) {
                           try {
                              FileHandle fileCivxx = Gdx.files
                                 .local(
                                    "game/civilizations_editor/"
                                       + CFG.ideologiesManager.getRealTag(tempScenarioGameData.getCivTag(i))
                                       + "/"
                                       + CFG.ideologiesManager.getRealTag(tempScenarioGameData.getCivTag(i))
                                 );
                              tempCivData = (Civilization_GameData3)CFG.deserialize(fileCivxx.readBytes());
                           } catch (GdxRuntimeException var19) {
                              FileHandle fileCivxxx = Gdx.files
                                 .internal(
                                    "game/civilizations_editor/"
                                       + CFG.ideologiesManager.getRealTag(tempScenarioGameData.getCivTag(i))
                                       + "/"
                                       + CFG.ideologiesManager.getRealTag(tempScenarioGameData.getCivTag(i))
                                 );
                              tempCivData = (Civilization_GameData3)CFG.deserialize(fileCivxxx.readBytes());
                           }
                        } else {
                           FileHandle fileCivxx = Gdx.files
                              .internal(
                                 "game/civilizations_editor/"
                                    + CFG.ideologiesManager.getRealTag(tempScenarioGameData.getCivTag(i))
                                    + "/"
                                    + CFG.ideologiesManager.getRealTag(tempScenarioGameData.getCivTag(i))
                              );
                           tempCivData = (Civilization_GameData3)CFG.deserialize(fileCivxx.readBytes());
                        }
                     } catch (GdxRuntimeException var20) {
                        Color tempC = CFG.getRandomColor();
                        tempCivData = new Civilization_GameData3("ran", (int)(tempC.r * 255.0F), (int)(tempC.g * 255.0F), (int)(tempC.b * 255.0F));
                     }
                  }
               }
            }

            lCivs.add(
               new Civilization(
                  tempScenarioGameData.getCivTag(i), tempCivData.getR(), tempCivData.getG(), tempCivData.getB(), tempScenarioGameData.getCivCapital(i), i + 1
               )
            );
            lCivs.get(i + 1).setCivID(i + 1);
            lCivs.get(i + 1).setTechnologyLevel(tempScenarioGameData.getTechnologyLevel(i));
            lCivs.get(i + 1).setHappiness(tempScenarioGameData.getHappiness(i));
            if (nEditor) {
               lCivs.get(i + 1).setMoney(-999999 == tempScenarioGameData.getStartingMoneyCiv(i) ? -999999L : tempScenarioGameData.getStartingMoneyCiv(i));
            } else {
               lCivs.get(i + 1)
                  .setMoney(
                     -999999 == tempScenarioGameData.getStartingMoneyCiv(i)
                        ? (
                           CFG.ideologiesManager.getIdeology(lCivs.get(i + 1).getIdeologyID()).CAN_BECOME_CIVILIZED >= 0
                              ? tempScenarioGameData.getStartingMoney() / 10
                              : tempScenarioGameData.getStartingMoney()
                        )
                        : tempScenarioGameData.getStartingMoneyCiv(i)
                  );
            }

            if (lCivs.get(i + 1).getCapitalProvinceID() >= 0) {
               CFG.game.getProvince(lCivs.get(i + 1).getCapitalProvinceID()).setCivID_LoadScenario(i + 1);
            }
         }

         CFG.initCreateScenario_TechnologyLevelsByContinents_Civ();

         for (int var28 = 0; var28 < tempScenarioGameData.getCivSize(); var28++) {
            CFG.addCreateScenario_TechnologyLevelsByContinents_Civ(tempScenarioGameData.getTechnologyByContinents(var28));
         }

         Scenario_GameData var30 = null;
         Scenario_GameData_Province2 scenario_GameData_Province = (Scenario_GameData_Province2)CFG.deserialize(fileProvince.readBytes());
         if (scenario_GameData_Province.getProvinceOwners() != null) {
            try {
               int iSize = scenario_GameData_Province.getProvinceOwners().size();

               for (int i2 = 0; i2 < iSize; i2++) {
                  CFG.game.getProvince(i2).setCivID_LoadScenario(scenario_GameData_Province.getProvinceOwners().get(i2));
               }
            } catch (IndexOutOfBoundsException var25) {
            }
         }

         scenario_GameData_Province = null;
      } catch (ClassNotFoundException var26) {
      } catch (IOException var27) {
      }

      if (!nEditor) {
         boolean foundRandomCivilization = false;
         int iSize = lCivs.size();

         for (int i = 1; i < iSize; i++) {
            if (lCivs.get(i).getCivTag().equals("ran")) {
               foundRandomCivilization = true;
               break;
            }
         }

         if (foundRandomCivilization) {
            FileHandle tempFileT = Gdx.files.internal("game/civilizations/Age_of_Civilizations");
            String tempT = tempFileT.readString();
            String[] tagsSPLITED = tempT.split(";");
            Random oR = new Random();
            int iSize2 = lCivs.size();

            for (int ix = 1; ix < iSize2; ix++) {
               if (lCivs.get(ix).getCivTag().equals("ran")) {
                  int tempTagID;
                  do {
                     while (tagsSPLITED[tempTagID = oR.nextInt(tagsSPLITED.length)].equals("ran")) {
                     }
                  } while (CFG.isInTheGame(tagsSPLITED[tempTagID]));

                  FileHandle fileCiv = Gdx.files.internal("game/civilizations/" + tagsSPLITED[tempTagID]);

                  try {
                     Civilization_GameData3 tempCivDatax = (Civilization_GameData3)CFG.deserialize(fileCiv.readBytes());
                     lCivs.get(ix).setCivTag(tempCivDatax.getCivTag());
                     lCivs.get(ix).setCivName(CFG.langManager.getCiv(tempCivDatax.getCivTag()));
                     lCivs.get(ix).setR(tempCivDatax.getR());
                     lCivs.get(ix).setG(tempCivDatax.getG());
                     lCivs.get(ix).setB(tempCivDatax.getB());
                     lCivs.get(ix).disposeFlag();
                     lCivs.get(ix).loadFlag();
                     tempCivDatax = null;
                  } catch (ClassNotFoundException var17) {
                  } catch (IOException var18) {
                  }
               }
            }
         }
      }

      CFG.map.getMapBG().disposeMinimapOfCivilizations();

      try {
         this.sActiveScenarioTag = this.getScenarioTag(CFG.game.getScenarioID());
      } catch (IndexOutOfBoundsException var16) {
         this.sActiveScenarioTag = "";
      }

      return lCivs;
   }

   public final void loadProvincesData(boolean nEditor) {
      FileHandle file = this.isInternal.get(CFG.game.getScenarioID())
         ? Gdx.files
            .internal(
               "map/"
                  + CFG.map.getFile_ActiveMap_Path()
                  + "scenarios/"
                  + this.lScenarios_TagsList.get(CFG.game.getScenarioID())
                  + "/"
                  + this.lScenarios_TagsList.get(CFG.game.getScenarioID())
                  + "_W"
            )
         : Gdx.files
            .local(
               "map/"
                  + CFG.map.getFile_ActiveMap_Path()
                  + "scenarios/"
                  + this.lScenarios_TagsList.get(CFG.game.getScenarioID())
                  + "/"
                  + this.lScenarios_TagsList.get(CFG.game.getScenarioID())
                  + "_W"
            );

      try {
         Scenario_WastelandProvinces_GameData scenario_WastelandProvinces_GameData = (Scenario_WastelandProvinces_GameData)CFG.deserialize(file.readBytes());
         int iSize = scenario_WastelandProvinces_GameData.getWastelandProvincesSize();

         for (int i = 0; i < iSize; i++) {
            CFG.game.getProvince(scenario_WastelandProvinces_GameData.getWastelandProvinceID(i)).setWasteland(0);
         }

         Object var8 = null;
      } catch (ClassNotFoundException var6) {
      } catch (IOException var7) {
      }

      this.buildProvincePopulationAndEconomy(true, nEditor);
   }

   public final void loadEventsData() {
      try {
         FileHandle file = this.isInternal.get(CFG.game.getScenarioID())
            ? Gdx.files
               .internal(
                  "map/"
                     + CFG.map.getFile_ActiveMap_Path()
                     + "scenarios/"
                     + this.lScenarios_TagsList.get(CFG.game.getScenarioID())
                     + "/events/"
                     + this.lScenarios_TagsList.get(CFG.game.getScenarioID())
                     + "_E"
               )
            : Gdx.files
               .local(
                  "map/"
                     + CFG.map.getFile_ActiveMap_Path()
                     + "scenarios/"
                     + this.lScenarios_TagsList.get(CFG.game.getScenarioID())
                     + "/events/"
                     + this.lScenarios_TagsList.get(CFG.game.getScenarioID())
                     + "_E"
               );

         try {
            CFG.eventsManager.eventsGD = (Events_GameData)CFG.deserialize(file.readBytes());
         } catch (ClassNotFoundException var3) {
         } catch (IOException var4) {
         }
      } catch (GdxRuntimeException var5) {
         CFG.eventsManager.eventsGD = new Events_GameData();
      }
   }

   public final void loadCoresData() {
      try {
         FileHandle file = this.isInternal.get(CFG.game.getScenarioID())
            ? Gdx.files
               .internal(
                  "map/"
                     + CFG.map.getFile_ActiveMap_Path()
                     + "scenarios/"
                     + this.lScenarios_TagsList.get(CFG.game.getScenarioID())
                     + "/"
                     + this.lScenarios_TagsList.get(CFG.game.getScenarioID())
                     + "_C"
               )
            : Gdx.files
               .local(
                  "map/"
                     + CFG.map.getFile_ActiveMap_Path()
                     + "scenarios/"
                     + this.lScenarios_TagsList.get(CFG.game.getScenarioID())
                     + "/"
                     + this.lScenarios_TagsList.get(CFG.game.getScenarioID())
                     + "_C"
               );

         try {
            CFG.province_Cores_GameData = (Province_Cores_GameData)CFG.deserialize(file.readBytes());
         } catch (ClassNotFoundException var3) {
         } catch (IOException var4) {
         }
      } catch (GdxRuntimeException var5) {
         CFG.province_Cores_GameData = new Province_Cores_GameData();
      }
   }

   public final void loadCoresData_Editor() {
      try {
         FileHandle file = this.isInternal.get(CFG.game.getScenarioID())
            ? Gdx.files
               .internal(
                  "map/"
                     + CFG.map.getFile_ActiveMap_Path()
                     + "scenarios/"
                     + this.lScenarios_TagsList.get(CFG.game.getScenarioID())
                     + "/"
                     + this.lScenarios_TagsList.get(CFG.game.getScenarioID())
                     + "_C"
               )
            : Gdx.files
               .local(
                  "map/"
                     + CFG.map.getFile_ActiveMap_Path()
                     + "scenarios/"
                     + this.lScenarios_TagsList.get(CFG.game.getScenarioID())
                     + "/"
                     + this.lScenarios_TagsList.get(CFG.game.getScenarioID())
                     + "_C"
               );

         try {
            CFG.province_Cores_GameData = (Province_Cores_GameData)CFG.deserialize(file.readBytes());

            for (int i = 0; i < CFG.province_Cores_GameData.getProvincesSize(); i++) {
               CFG.game.getProvince(CFG.province_Cores_GameData.lProvinces.get(i).iProvinceID).buildProvinceCore();

               for (int j = 0; j < CFG.province_Cores_GameData.lProvinces.get(i).lCores.size(); j++) {
                  CFG.game
                     .getProvince(CFG.province_Cores_GameData.lProvinces.get(i).iProvinceID)
                     .getCore()
                     .addNewCore(CFG.province_Cores_GameData.lProvinces.get(i).lCores.get(j).iCivID, Game_Calendar.TURN_ID);
               }
            }
         } catch (ClassNotFoundException var4) {
         } catch (IOException var5) {
         }
      } catch (GdxRuntimeException var6) {
         CFG.province_Cores_GameData = new Province_Cores_GameData();
      }
   }

   public final void buildDiplomacy() {
      CFG.game.buildAlliances();
      CFG.game.buildWars();

      for (int i = 1; i < CFG.game.getCivsSize(); i++) {
         CFG.game.getCiv(i).buildDiplomacy(true);
      }
   }

   public final void loadDiplomacyData(boolean editor) {
      this.buildDiplomacy();
      FileHandle file = this.isInternal.get(CFG.game.getScenarioID())
         ? Gdx.files
            .internal(
               "map/"
                  + CFG.map.getFile_ActiveMap_Path()
                  + "scenarios/"
                  + this.lScenarios_TagsList.get(CFG.game.getScenarioID())
                  + "/"
                  + this.lScenarios_TagsList.get(CFG.game.getScenarioID())
                  + "_D"
            )
         : Gdx.files
            .local(
               "map/"
                  + CFG.map.getFile_ActiveMap_Path()
                  + "scenarios/"
                  + this.lScenarios_TagsList.get(CFG.game.getScenarioID())
                  + "/"
                  + this.lScenarios_TagsList.get(CFG.game.getScenarioID())
                  + "_D"
            );

      try {
         Scenario_GameData_Diplomacy2 scenario_GameData_Diplomacy = (Scenario_GameData_Diplomacy2)CFG.deserialize(file.readBytes());

         for (int i = 0; i < scenario_GameData_Diplomacy.getVassals().size(); i++) {
            if (scenario_GameData_Diplomacy.getVassals().get(i).getCivLordID() < CFG.game.getCivsSize()) {
               CFG.game
                  .getCiv(scenario_GameData_Diplomacy.getVassals().get(i).getCivID())
                  .setPuppetOfCivID(scenario_GameData_Diplomacy.getVassals().get(i).getCivLordID());
            }
         }

         for (int var11 = 0; var11 < scenario_GameData_Diplomacy.getAlliances().size(); var11++) {
            Gdx.app.log("AoC", scenario_GameData_Diplomacy.getAlliances().get(var11).getName());
            CFG.game.addAlliance(scenario_GameData_Diplomacy.getAlliances().get(var11).getName());

            for (int j = 0; j < scenario_GameData_Diplomacy.getAlliances().get(var11).getCivs().size(); j++) {
               CFG.game.getAlliance(var11 + 1).addCivilization(scenario_GameData_Diplomacy.getAlliances().get(var11).getCivs().get(j));
               CFG.game.getCiv(scenario_GameData_Diplomacy.getAlliances().get(var11).getCivs().get(j)).setAllianceID(var11 + 1);
            }

            CFG.game
               .getAlliance(var11 + 1)
               .setColorOfAlliance(
                  new Color_GameData(
                     scenario_GameData_Diplomacy.getAlliances().get(var11).getColor().getR(),
                     scenario_GameData_Diplomacy.getAlliances().get(var11).getColor().getG(),
                     scenario_GameData_Diplomacy.getAlliances().get(var11).getColor().getB()
                  )
               );
         }

         if (editor) {
            for (int var14 = 0; var14 < scenario_GameData_Diplomacy.getRelations().size(); var14++) {
               CFG.game
                  .setCivRelation_OfCivB(
                     scenario_GameData_Diplomacy.getRelations().get(var14).getCivA(),
                     scenario_GameData_Diplomacy.getRelations().get(var14).getCivB(),
                     scenario_GameData_Diplomacy.getRelations().get(var14).getValue()
                  );
            }
         } else {
            for (int var12 = 0; var12 < scenario_GameData_Diplomacy.getRelations().size(); var12++) {
               CFG.game
                  .setCivRelation_OfCivB(
                     scenario_GameData_Diplomacy.getRelations().get(var12).getCivA(),
                     scenario_GameData_Diplomacy.getRelations().get(var12).getCivB(),
                     scenario_GameData_Diplomacy.getRelations().get(var12).getValue()
                  );
            }

            for (int var13 = 1; var13 < CFG.game.getCivsSize() - 1; var13++) {
               for (int j = var13 + 1; j < CFG.game.getCivsSize(); j++) {
                  if ((int)CFG.game.getCivRelation_OfCivB(var13, j) == 0) {
                     CFG.game.setCivRelation_OfCivB(var13, j, CFG.oR.nextInt(20) - 10);
                  }

                  if ((int)CFG.game.getCivRelation_OfCivB(j, var13) == 0) {
                     CFG.game.setCivRelation_OfCivB(j, var13, CFG.oR.nextInt(20) - 10);
                  }
               }
            }
         }

         for (int var15 = 0; var15 < scenario_GameData_Diplomacy.getMilitaryAccess().size(); var15++) {
            CFG.game
               .setMilitaryAccess(
                  scenario_GameData_Diplomacy.getMilitaryAccess().get(var15).getCivA(),
                  scenario_GameData_Diplomacy.getMilitaryAccess().get(var15).getCivB(),
                  scenario_GameData_Diplomacy.getMilitaryAccess().get(var15).getValue()
               );
         }

         for (int var16 = 0; var16 < scenario_GameData_Diplomacy.getGuarantee().size(); var16++) {
            CFG.game
               .setGuarantee(
                  scenario_GameData_Diplomacy.getGuarantee().get(var16).getCivA(),
                  scenario_GameData_Diplomacy.getGuarantee().get(var16).getCivB(),
                  scenario_GameData_Diplomacy.getGuarantee().get(var16).getValue()
               );
         }

         for (int var17 = 0; var17 < scenario_GameData_Diplomacy.getPacts().size(); var17++) {
            CFG.game
               .setCivNonAggressionPact(
                  scenario_GameData_Diplomacy.getPacts().get(var17).getCivA(),
                  scenario_GameData_Diplomacy.getPacts().get(var17).getCivB(),
                  scenario_GameData_Diplomacy.getPacts().get(var17).getValue()
               );
         }

         for (int var18 = 0; var18 < scenario_GameData_Diplomacy.getDefensivePacts().size(); var18++) {
            CFG.game
               .setDefensivePact(
                  scenario_GameData_Diplomacy.getDefensivePacts().get(var18).getCivA(),
                  scenario_GameData_Diplomacy.getDefensivePacts().get(var18).getCivB(),
                  scenario_GameData_Diplomacy.getDefensivePacts().get(var18).getValue()
               );
         }

         for (int var19 = 0; var19 < scenario_GameData_Diplomacy.getTruces().size(); var19++) {
            CFG.game
               .setCivTruce(
                  scenario_GameData_Diplomacy.getTruces().get(var19).getCivA(),
                  scenario_GameData_Diplomacy.getTruces().get(var19).getCivB(),
                  scenario_GameData_Diplomacy.getTruces().get(var19).getValue()
               );
         }

         scenario_GameData_Diplomacy = null;
      } catch (ClassNotFoundException var6) {
         CFG.toast.setInView("Error - Diplomacy Data");
      } catch (IOException var7) {
      } catch (GdxRuntimeException var8) {
      } catch (NullPointerException var9) {
      }

      DiplomacyManager.buildFriendlyCivs();
   }

   public final void loadArmiesData() {
      for (int i = 0; i < CFG.game.getProvincesSize(); i++) {
         CFG.game.getProvince(i).resetArmies_NewGame(0);
         if (!CFG.game.getProvince(i).getSeaProvince() && CFG.game.getProvince(i).getWasteland() < 0) {
            if (CFG.game.getProvince(i).getCivID() == 0) {
               CFG.game.getProvince(i).updateArmy(this.getScenario_NeutralArmy());
            } else if (CFG.game.getProvince(i).getIsCapital()) {
               if (CFG.ideologiesManager.getIdeology(CFG.game.getCiv(CFG.game.getProvince(i).getCivID()).getIdeologyID()).CAN_BECOME_CIVILIZED >= 0) {
                  CFG.game.getProvince(i).updateArmy(this.getScenario_StartingArmyInCapitals() / 10);
               } else {
                  CFG.game.getProvince(i).updateArmy(this.getScenario_StartingArmyInCapitals());
               }
            }
         }
      }

      try {
         FileHandle file = this.isInternal.get(CFG.game.getScenarioID())
            ? Gdx.files
               .internal(
                  "map/"
                     + CFG.map.getFile_ActiveMap_Path()
                     + "scenarios/"
                     + this.lScenarios_TagsList.get(CFG.game.getScenarioID())
                     + "/"
                     + this.lScenarios_TagsList.get(CFG.game.getScenarioID())
                     + "_A"
               )
            : Gdx.files
               .local(
                  "map/"
                     + CFG.map.getFile_ActiveMap_Path()
                     + "scenarios/"
                     + this.lScenarios_TagsList.get(CFG.game.getScenarioID())
                     + "/"
                     + this.lScenarios_TagsList.get(CFG.game.getScenarioID())
                     + "_A"
               );

         try {
            Scenario_GameData_Armies scenario_GameData_Armies = (Scenario_GameData_Armies)CFG.deserialize(file.readBytes());
            int iSize = scenario_GameData_Armies.lArmies.size();

            for (int ix = 0; ix < iSize; ix++) {
               try {
                  if (CFG.game.getProvince(scenario_GameData_Armies.lArmies.get(ix).getProvinceID()).getWasteland() < 0
                     && (
                        CFG.game.getProvince(scenario_GameData_Armies.lArmies.get(ix).getProvinceID()).getCivID()
                              == scenario_GameData_Armies.lArmies.get(ix).getCivID()
                           || CFG.game.getProvince(scenario_GameData_Armies.lArmies.get(ix).getProvinceID()).getSeaProvince()
                           || CFG.game.getCiv(scenario_GameData_Armies.lArmies.get(ix).getCivID()).getAllianceID() > 0
                              && CFG.game.getCiv(scenario_GameData_Armies.lArmies.get(ix).getCivID()).getAllianceID() > 0
                                 == CFG.game.getCiv(CFG.game.getProvince(scenario_GameData_Armies.lArmies.get(ix).getProvinceID()).getCivID()).getAllianceID()
                                    > 0
                           || CFG.game.getCiv(CFG.game.getProvince(scenario_GameData_Armies.lArmies.get(ix).getProvinceID()).getCivID()).getPuppetOfCivID()
                              == scenario_GameData_Armies.lArmies.get(ix).getCivID()
                           || CFG.game.getProvince(scenario_GameData_Armies.lArmies.get(ix).getProvinceID()).getCivID()
                              == CFG.game.getCiv(scenario_GameData_Armies.lArmies.get(ix).getCivID()).getPuppetOfCivID()
                           || CFG.game
                                 .getMilitaryAccess(
                                    scenario_GameData_Armies.lArmies.get(ix).getCivID(),
                                    CFG.game.getProvince(scenario_GameData_Armies.lArmies.get(ix).getProvinceID()).getCivID()
                                 )
                              > 0
                     )) {
                     CFG.game
                        .getProvince(scenario_GameData_Armies.lArmies.get(ix).getProvinceID())
                        .updateArmy(scenario_GameData_Armies.lArmies.get(ix).getCivID(), scenario_GameData_Armies.lArmies.get(ix).getArmy());
                  }
               } catch (IndexOutOfBoundsException var6) {
               } catch (NullPointerException var7) {
               }
            }

            Object var13 = null;
         } catch (ClassNotFoundException var8) {
         } catch (IOException var9) {
         } catch (GdxRuntimeException var10) {
         }
      } catch (GdxRuntimeException var11) {
      }
   }

   public final void buildProvincePopulationAndEconomy(boolean loadCoresData, boolean nEditor) {
      Random oR = new Random();
      CFG.game.getCiv(0).setTechnologyLevel(0.1F);

      for (int i = 0; i < CFG.game.getProvincesSize(); i++) {
         if (!CFG.game.getProvince(i).getSeaProvince()) {
            CFG.game.getProvince(i).getPopulationData().clearData();
            CFG.game.getProvince(i).setEconomy(0);
            CFG.game.getProvince(i).iIncome_Taxation = 1.0F;
            CFG.game.getProvince(i).iIncome_Production = 1.0F;
            CFG.game.getProvince(i).iAdministrationCost = 0.0F;
            CFG.game.getProvince(i).saveProvinceData.iNumOfTurnsWithBalanceOnMinus = 0;
         }

         CFG.game.getProvince(i).setIsPartOfHolyRomanEmpire(false);
         CFG.game.getProvince(i).saveProvinceData.resetData();
      }

      for (int var9 = 0; var9 < CFG.game.getProvincesSize(); var9++) {
         CFG.game.getProvince(var9).buildProvinceCore();
      }

      if (loadCoresData) {
         CFG.game.getGameScenarios().loadCoresData();

         for (int var10 = 0; var10 < CFG.province_Cores_GameData.getProvincesSize(); var10++) {
            try {
               if (!CFG.game.getProvince(CFG.province_Cores_GameData.lProvinces.get(var10).iProvinceID).getSeaProvince()
                  && CFG.game.getProvince(CFG.province_Cores_GameData.lProvinces.get(var10).iProvinceID).getWasteland() < 0
                  && CFG.game.getProvince(CFG.province_Cores_GameData.lProvinces.get(var10).iProvinceID).getCivID() > 0) {
                  for (int j = 0; j < CFG.province_Cores_GameData.lProvinces.get(var10).lCores.size(); j++) {
                     CFG.game
                        .getProvince(CFG.province_Cores_GameData.lProvinces.get(var10).iProvinceID)
                        .getCore()
                        .addNewCore(CFG.province_Cores_GameData.lProvinces.get(var10).lCores.get(j).iCivID, 1);
                  }
               }
            } catch (IndexOutOfBoundsException var8) {
            }
         }
      }

      if (CFG.province_Cores_GameData == null) {
         CFG.province_Cores_GameData = new Province_Cores_GameData();
      }

      for (int var11 = 0; var11 < CFG.game.getProvincesSize(); var11++) {
         if (!CFG.game.getProvince(var11).getSeaProvince()) {
            float tDevelopment = CFG.game.getCiv(CFG.game.getProvince(var11).getCivID()).getTechnologyLevel();
            tDevelopment = tDevelopment
                  * (
                     (1.0F - CFG.gameAges.getAge_StartingDevelopment(Game_Calendar.CURRENT_AGEID))
                        * (CFG.game.getProvince(var11).getIsCapital() ? 0.7646841F : 0.5746985F)
                  )
               + tDevelopment * CFG.gameAges.getAge_StartingDevelopment(Game_Calendar.CURRENT_AGEID) * CFG.game.getProvince(var11).getGrowthRate_Population();
            if (CFG.game.getProvince(var11).getCivID() > 0) {
               tDevelopment = tDevelopment
                  * CFG.getCreateScenario_TechnologyLevelsByContinents_Continent(
                     CFG.game.getProvince(var11).getCivID() - 1, CFG.game.getProvince(var11).getRegion()
                  )
                  / 100.0F;
            }

            CFG.game
               .getProvince(var11)
               .setDevelopmentLevel(
                  tDevelopment
                     * (
                        0.875F
                           + CFG.oR.nextInt(2000) / 10000.0F
                           + CFG.terrainTypesManager.getBaseDevelopmentModifier(CFG.game.getProvince(var11).getTerrainTypeID())
                     )
               );
            if (CFG.game.getProvince(var11).getCivID() == 0) {
               CFG.game
                  .getProvince(var11)
                  .getPopulationData()
                  .setPopulationOfCivID(
                     CFG.game.getProvince(var11).getCivID(),
                     (int)(
                           this.getScenario_StartingPopulation()
                              * 0.18275F
                              * (
                                 CFG.game.getProvince(var11).getGrowthRate_Population()
                                    * (1.0F + CFG.terrainTypesManager.getPopulationGrowth(CFG.game.getProvince(var11).getTerrainTypeID()))
                              )
                        )
                        + oR.nextInt(
                              1
                                 + (int)Math.ceil(
                                    this.getScenario_StartingPopulation()
                                       * (oR.nextInt(25) / 100.0F)
                                       * (
                                          CFG.game.getProvince(var11).getGrowthRate_Population()
                                             * (1.0F + CFG.terrainTypesManager.getPopulationGrowth(CFG.game.getProvince(var11).getTerrainTypeID()))
                                       )
                                 )
                           )
                           / 4
                  );
               CFG.game
                  .getProvince(var11)
                  .setEconomy(
                     (int)(
                           this.getScenario_StartingEconomy()
                              * (0.05275F + CFG.game.getProvince(var11).getNeighboringSeaProvincesSize() * 0.0015F)
                              * (
                                 CFG.game.getProvince(var11).getGrowthRate_Population()
                                    * (1.0F + CFG.terrainTypesManager.getEconomyGrowth(CFG.game.getProvince(var11).getTerrainTypeID()))
                              )
                        )
                        + oR.nextInt(
                           1
                              + (int)Math.ceil(
                                 this.getScenario_StartingEconomy()
                                    * (oR.nextInt(10) / 100.0F)
                                    * (
                                       CFG.game.getProvince(var11).getGrowthRate_Population()
                                          * (1.0F + CFG.terrainTypesManager.getEconomyGrowth(CFG.game.getProvince(var11).getTerrainTypeID()))
                                          * CFG.game.getProvince(var11).getDevelopmentLevel()
                                    )
                              )
                        )
                  );
               CFG.game.getProvince(var11).setHappiness(0.48F + oR.nextInt(2400) / 10000.0F);
            } else {
               if (CFG.game.getProvince(var11).getCore().getCivsSize() < 1) {
                  CFG.game
                     .getProvince(var11)
                     .getPopulationData()
                     .setPopulationOfCivID(
                        CFG.game.getProvince(var11).getCivID(),
                        (int)(
                           (
                                 (int)(
                                       this.getScenario_StartingPopulation()
                                          * (0.85F + (CFG.game.getProvince(var11).getIsCapital() ? 0.05F : 0.0F))
                                          * (
                                             CFG.game.getProvince(var11).getGrowthRate_Population()
                                                * (1.0F + CFG.terrainTypesManager.getPopulationGrowth(CFG.game.getProvince(var11).getTerrainTypeID()))
                                          )
                                    )
                                    + oR.nextInt(
                                       1
                                          + (int)Math.ceil(
                                             this.getScenario_StartingPopulation()
                                                * 0.15F
                                                * (
                                                   CFG.game.getProvince(var11).getGrowthRate_Population()
                                                      * (1.0F + CFG.terrainTypesManager.getPopulationGrowth(CFG.game.getProvince(var11).getTerrainTypeID()))
                                                )
                                          )
                                    )
                              )
                              * (
                                 CFG.ideologiesManager.getIdeology(CFG.game.getCiv(CFG.game.getProvince(var11).getCivID()).getIdeologyID()).CAN_BECOME_CIVILIZED
                                       >= 0
                                    ? (CFG.game.getCiv(CFG.game.getProvince(var11).getCivID()).getCapitalProvinceID() == var11 ? 0.4F : 0.275F)
                                    : 1.0F
                              )
                              * (
                                 0.725F
                                    + 0.275F
                                       * CFG.getCreateScenario_TechnologyLevelsByContinents_Continent(
                                          CFG.game.getProvince(var11).getCivID() - 1, CFG.game.getProvince(var11).getRegion()
                                       )
                                       / 100.0F
                              )
                        )
                     );
               } else {
                  int tempPop = (int)(
                     (
                           (int)(
                                 this.getScenario_StartingPopulation()
                                    * (0.85F + (CFG.game.getProvince(var11).getIsCapital() ? 0.0725F : 0.0F))
                                    * (
                                       (
                                             CFG.game.getProvince(var11).getIsCapital()
                                                ? Math.max(0.2675F, CFG.game.getProvince(var11).getGrowthRate_Population())
                                                : CFG.game.getProvince(var11).getGrowthRate_Population()
                                          )
                                          * (1.0F + CFG.terrainTypesManager.getPopulationGrowth(CFG.game.getProvince(var11).getTerrainTypeID()))
                                    )
                              )
                              + oR.nextInt(
                                 1
                                    + (int)Math.ceil(
                                       this.getScenario_StartingPopulation()
                                          * 0.15F
                                          * (
                                             CFG.game.getProvince(var11).getGrowthRate_Population()
                                                * (1.0F + CFG.terrainTypesManager.getPopulationGrowth(CFG.game.getProvince(var11).getTerrainTypeID()))
                                          )
                                    )
                              )
                        )
                        * (
                           CFG.ideologiesManager.getIdeology(CFG.game.getCiv(CFG.game.getProvince(var11).getCivID()).getIdeologyID()).CAN_BECOME_CIVILIZED >= 0
                              ? (CFG.game.getCiv(CFG.game.getProvince(var11).getCivID()).getCapitalProvinceID() == var11 ? 0.4F : 0.275F)
                              : 1.0F
                        )
                        * (
                           0.725F
                              + 0.275F
                                 * CFG.getCreateScenario_TechnologyLevelsByContinents_Continent(
                                    CFG.game.getProvince(var11).getCivID() - 1, CFG.game.getProvince(var11).getRegion()
                                 )
                                 / 100.0F
                        )
                  );
                  CFG.game.getProvince(var11).getPopulationData().clearData();

                  for (int j = 0; j < CFG.game.getProvince(var11).getCore().getCivsSize(); j++) {
                     CFG.game
                        .getProvince(var11)
                        .getPopulationData()
                        .setPopulationOfCivID(
                           CFG.game.getProvince(var11).getCore().getCivID(j),
                           (int)(tempPop * CFG.province_Cores_GameData.getPercOfPop(var11, CFG.game.getProvince(var11).getCore().getCivID(j)))
                        );
                  }

                  for (int var17 = 0; var17 < CFG.game.getProvince(var11).getCore().getCivsSize() && var17 < 1; var17++) {
                     if (CFG.province_Cores_GameData.getPercOfPop(var11, CFG.game.getProvince(var11).getCore().getCivID(var17)) < 0.18F) {
                        CFG.game.getProvince(var11).getCore().removeCore(CFG.game.getProvince(var11).getCore().getCivID(var17));
                     }
                  }
               }

               CFG.game
                  .getProvince(var11)
                  .setEconomy(
                     (int)(
                        (int)(
                              this.getScenario_StartingEconomy()
                                 * (
                                    CFG.game.getProvince(var11).getDevelopmentLevel() * 1.064498F
                                       + CFG.game.getProvince(var11).getNeighboringSeaProvincesSize() * 0.035F
                                 )
                                 * (
                                    CFG.game.getProvince(var11).getGrowthRate_Population()
                                       * (1.0F + CFG.terrainTypesManager.getEconomyGrowth(CFG.game.getProvince(var11).getTerrainTypeID()))
                                 )
                           )
                           + oR.nextInt(
                                 1
                                    + Math.max(
                                       (int)Math.ceil(
                                          this.getScenario_StartingEconomy()
                                             * (1.0F - CFG.game.getProvince(var11).getDevelopmentLevel())
                                             * (
                                                CFG.game.getProvince(var11).getGrowthRate_Population()
                                                   * (1.0F + CFG.terrainTypesManager.getEconomyGrowth(CFG.game.getProvince(var11).getTerrainTypeID()))
                                                   * CFG.game.getProvince(var11).getDevelopmentLevel()
                                             )
                                       ),
                                       0
                                    )
                              )
                              * (
                                 CFG.ideologiesManager.getIdeology(CFG.game.getCiv(CFG.game.getProvince(var11).getCivID()).getIdeologyID()).CAN_BECOME_CIVILIZED
                                       >= 0
                                    ? (CFG.game.getCiv(CFG.game.getProvince(var11).getCivID()).getCapitalProvinceID() == var11 ? 0.95F : 0.725F)
                                    : 1.0F
                              )
                     )
                  );
               CFG.game.getProvince(var11).setHappiness((CFG.game.getCiv(CFG.game.getProvince(var11).getCivID()).getHappiness() + oR.nextInt(12) - 6) / 100.0F);
            }

            for (int j = 0; j < CFG.game.getProvince(var11).getNeighboringProvincesSize(); j++) {
               if (CFG.game.getProvince(CFG.game.getProvince(var11).getNeighboringProvinces(j)).getCivID() > 0
                  && CFG.game.getProvince(CFG.game.getProvince(var11).getNeighboringProvinces(j)).getCivID() != CFG.game.getProvince(var11).getCivID()) {
                  CFG.game
                     .getProvince(var11)
                     .getPopulationData()
                     .setPopulationOfCivID(
                        CFG.game.getProvince(CFG.game.getProvince(var11).getNeighboringProvinces(j)).getCivID(),
                        (int)(CFG.game.getProvince(var11).getPopulationData().getPopulation() * (0.00874F + CFG.oR.nextInt(345) / 10000.0F))
                     );
               }
            }
         }
      }

      if (!nEditor) {
         for (int var12 = 1; var12 < CFG.game.getCivsSize(); var12++) {
            if (CFG.game.getCiv(var12).getNumOfProvinces() > 0
               && CFG.ideologiesManager.getIdeology(CFG.game.getCiv(var12).getIdeologyID()).CAN_BECOME_CIVILIZED >= 0
               && CFG.game.getCiv(var12).getCapitalProvinceID() >= 0) {
               for (int jx = 0; jx < CFG.game.getProvince(CFG.game.getCiv(var12).getCapitalProvinceID()).getNeighboringProvincesSize(); jx++) {
                  if (CFG.game.getProvince(CFG.game.getProvince(CFG.game.getCiv(var12).getCapitalProvinceID()).getNeighboringProvinces(jx)).getWasteland() < 0
                     && (
                        CFG.game.getProvince(CFG.game.getProvince(CFG.game.getCiv(var12).getCapitalProvinceID()).getNeighboringProvinces(jx)).getCivID() == 0
                           || CFG.ideologiesManager
                                 .getIdeology(
                                    CFG.game
                                       .getCiv(
                                          CFG.game
                                             .getProvince(CFG.game.getProvince(CFG.game.getCiv(var12).getCapitalProvinceID()).getNeighboringProvinces(jx))
                                             .getCivID()
                                       )
                                       .getIdeologyID()
                                 )
                                 .CAN_BECOME_CIVILIZED
                              >= 0
                     )) {
                     CFG.game
                        .getProvince(CFG.game.getProvince(CFG.game.getCiv(var12).getCapitalProvinceID()).getNeighboringProvinces(jx))
                        .getCore()
                        .addNewCore(var12, 1);
                  }
               }

               int tRan = CFG.oR.nextInt(10);

               for (int a = 0; a < tRan; a++) {
                  CFG.game
                     .getProvince(CFG.game.getCiv(var12).getCapitalProvinceID())
                     .getCore()
                     .increaseOwnership(var12, CFG.game.getCiv(var12).getCapitalProvinceID());
               }
            }
         }
      }

      CFG.province_Cores_GameData = null;
      CFG.game_NextTurnUpdate.updateCities();
   }

   public final void disableFillTheMap() {
      for (int i = 0; i < CFG.game.getProvincesSize(); i++) {
         if (!CFG.game.getProvince(i).getIsCapital()) {
            CFG.game.getProvince(i).setCivID_LoadScenario(0);
            CFG.game.getProvince(i).setCivRegionID(-1);
         }
      }

      for (int var3 = 1; var3 < CFG.game.getCivsSize(); var3++) {
         CFG.game.getCiv(var3).clearProvinces_FillTheMap(CFG.game.getCiv(var3).getNumOfProvinces() > 0);
      }

      for (int var4 = 0; var4 < CFG.game.getProvincesSize(); var4++) {
         for (int j = 0; j < CFG.game.getProvince(var4).getProvinceBordersLandByLandSize(); j++) {
            CFG.game.getProvince(var4).getProvinceBordersLandByLand().get(j).setIsCivilizationBorder(false, var4);
         }
      }

      for (int var5 = 1; var5 < CFG.game.getCivsSize(); var5++) {
         CFG.game.getProvince(CFG.game.getCiv(var5).getCapitalProvinceID()).updateProvinceBorder();
      }

      CFG.game.buildCivilizationsRegions();
      CFG.map.getMapBG().disposeMinimapOfCivilizations();
   }

   public final void enableFillTheMap() {
      for (int i = 1; i < CFG.game.getCivsSize(); i++) {
         CFG.game.getCiv(i).clearProvinces_FillTheMap(false);
      }

      FileHandle file = Gdx.files
         .internal(
            "map/"
               + CFG.map.getFile_ActiveMap_Path()
               + "scenarios/"
               + this.lScenarios_TagsList.get(CFG.game.getScenarioID())
               + "/"
               + this.lScenarios_TagsList.get(CFG.game.getScenarioID())
         );
      FileHandle fileProvince = Gdx.files
         .internal(
            "map/"
               + CFG.map.getFile_ActiveMap_Path()
               + "scenarios/"
               + this.lScenarios_TagsList.get(CFG.game.getScenarioID())
               + "/"
               + this.lScenarios_TagsList.get(CFG.game.getScenarioID())
               + "_PD"
         );

      try {
         Scenario_GameData tempScenarioGameData = (Scenario_GameData)CFG.deserialize(file.readBytes());

         for (int i2 = 0; i2 < tempScenarioGameData.getCivSize(); i2++) {
            CFG.game.getCiv(i2 + 1).setCapitalProvinceID(tempScenarioGameData.getCivCapital(i2));
         }

         Scenario_GameData_Province2 scenario_GameData_Province = (Scenario_GameData_Province2)CFG.deserialize(fileProvince.readBytes());
         if (scenario_GameData_Province.getProvinceOwners() != null) {
            int iSize = scenario_GameData_Province.getProvinceOwners().size();

            for (int i = 0; i < iSize; i++) {
               CFG.game.getProvince(i).setCivID_LoadScenario(scenario_GameData_Province.getProvinceOwners().get(i));
               CFG.game.getCiv(scenario_GameData_Province.getProvinceOwners().get(i)).addProvince_Just(i);
            }
         }

         for (int i = 0; i < CFG.game.getProvincesSize(); i++) {
            for (int j = 0; j < CFG.game.getProvince(i).getProvinceBordersLandByLandSize(); j++) {
               CFG.game
                  .getProvince(i)
                  .getProvinceBordersLandByLand()
                  .get(j)
                  .setIsCivilizationBorder(
                     CFG.game.getProvince(i).getCivID()
                        != CFG.game.getProvince(CFG.game.getProvince(i).getProvinceBordersLandByLand().get(j).getWithProvinceID()).getCivID(),
                     i
                  );
            }
         }

         CFG.game.buildCivilizationsRegions();
      } catch (ClassNotFoundException var7) {
      } catch (IOException var8) {
      } catch (GdxRuntimeException var9) {
      }

      CFG.map.getMapBG().disposeMinimapOfCivilizations();
   }

   public final void editScenario(int iID) {
      Game_Calendar.TURN_ID = 1;
      CFG.game.setScenarioID(iID);
      CFG.game.loadScenario(true);
      CFG.game.getGameScenarios().loadCoresData_Editor();
      CFG.CREATE_SCENARIO_GAME_DATA_TAG = this.lScenarios_TagsList.get(CFG.game.getScenarioID());
      CFG.CREATE_SCENARIO_NAME = this.getScenarioName(CFG.game.getScenarioID());
      CFG.CREATE_SCENARIO_AUTHOR = this.getScenarioAuthor(CFG.game.getScenarioID());
      CFG.CREATE_SCENARIO_AGE = this.getScenarioAge(CFG.game.getScenarioID());
      CFG.CREATE_SCENARIO_WIKI = this.getScenarioWiki(CFG.game.getScenarioID());
      Game_Calendar.currentYear = this.getScenarioYear(CFG.game.getScenarioID());
      Game_Calendar.currentMonth = this.getScenarioMonth(CFG.game.getScenarioID());
      Game_Calendar.currentDay = this.getScenarioDay(CFG.game.getScenarioID());
   }

   public final int getNumOfCivs(int i) {
      return this.lScenarios_CivNum.get(i);
   }

   public final void setNumOfCivs(int i, int nNumCivs) {
      try {
         this.lScenarios_CivNum.set(i, nNumCivs);
      } catch (IndexOutOfBoundsException var4) {
      }
   }

   public final String getScenarioName(int i) {
      return this.lScenarios_Names.get(i);
   }

   public final void setScenarioName(int i, String nName) {
      try {
         this.lScenarios_Names.set(i, nName);
      } catch (IndexOutOfBoundsException var4) {
      }
   }

   public final String getScenarioWiki(int i) {
      return this.lScenarios_Wikis.get(i);
   }

   public final String getScenarioAuthor(int i) {
      return this.lScenarios_Authors.get(i);
   }

   public final void setScenarioAuthor(int i, String nAuthor) {
      try {
         this.lScenarios_Authors.set(i, nAuthor);
      } catch (IndexOutOfBoundsException var4) {
      }
   }

   public final String getScenarioTag(int i) {
      return this.lScenarios_TagsList.get(i);
   }

   public final int getScenarioAge(int i) {
      return this.lScenarios_Age.get(i);
   }

   public final void setScenarioAge(int i, int nAge) {
      try {
         this.lScenarios_Age.set(i, nAge);
      } catch (IndexOutOfBoundsException var4) {
      }
   }

   public final int getScenarioYear(int i) {
      return this.lScenarios_Year.get(i);
   }

   public final int getScenarioMonth(int i) {
      return this.lScenarios_Month.get(i);
   }

   public final int getScenarioDay(int i) {
      return this.lScenarios_Day.get(i);
   }

   public final void setScenarioDay(int i, int nDay) {
      try {
         this.lScenarios_Day.set(i, nDay);
      } catch (IndexOutOfBoundsException var4) {
      }
   }

   public final void setScenarioMonth(int i, int nMonth) {
      try {
         this.lScenarios_Month.set(i, nMonth);
      } catch (IndexOutOfBoundsException var4) {
      }
   }

   public final void setScenarioYear(int i, int nYear) {
      try {
         this.lScenarios_Year.set(i, nYear);
      } catch (IndexOutOfBoundsException var4) {
      }
   }

   public final int getScenario_StartingArmyInCapitals() {
      return this.iScenario_StartingArmyInCapitals;
   }

   public final void setScenario_StartingArmyInCapitals(int iScenario_StartingArmyInCapitals) {
      this.iScenario_StartingArmyInCapitals = iScenario_StartingArmyInCapitals;
   }

   public final float getScenario_PopulationGrowthRate_Modifier() {
      return this.iScenario_PopulationGrowthRate_Modifier;
   }

   public final void setScenario_PopulationGrowthRate_Modifier(float iScenario_PopulationGrowthRate_Modifier) {
      this.iScenario_PopulationGrowthRate_Modifier = iScenario_PopulationGrowthRate_Modifier;
   }

   public final float getScenario_EconomyGrowthRate_Modifier() {
      return this.iScenario_EconomyGrowthRate_Modifier;
   }

   public final void setScenario_EconomyGrowthRate_Modifier(float iScenario_EconomyGrowthRate_Modifier) {
      this.iScenario_EconomyGrowthRate_Modifier = iScenario_EconomyGrowthRate_Modifier;
   }

   public final float getScenario_DiseasesDeathRate_Modifier() {
      return this.iScenario_DiseasesDeathRate_Modifier;
   }

   public final void setScenario_DiseasesDeathRate_Modifier(float iScenario_DiseasesDeathRate_Modifier) {
      this.iScenario_DiseasesDeathRate_Modifier = iScenario_DiseasesDeathRate_Modifier;
   }

   public final int getScenario_NeutralArmy() {
      return this.iScenario_NeutralArmy;
   }

   public final void setScenario_NeutralArmy(int iScenario_NeutralArmy) {
      this.iScenario_NeutralArmy = iScenario_NeutralArmy;
   }

   public final int getScenario_StartingPopulation() {
      return this.iScenario_StartingPopulation;
   }

   public final void setScenario_StartingPopulation(int iScenario_StartingPopulation) {
      this.iScenario_StartingPopulation = iScenario_StartingPopulation;
   }

   public final int getScenario_StartingEconomy() {
      return this.iScenario_StartingEconomy;
   }

   public final void setScenario_StartingEconomy(int iScenario_StartingEconomy) {
      this.iScenario_StartingEconomy = iScenario_StartingEconomy;
   }

   public final int getScenario_StartingMoney() {
      return this.iScenario_StartingMoney;
   }

   public final void setScenario_StartingMoney(int iScenario_StartingMoney) {
      this.iScenario_StartingMoney = iScenario_StartingMoney;
   }

   public final String getScenario_ActivePallet_TAG() {
      return this.sScenario_ActivePallet_TAG;
   }

   public void setScenario_ActivePallet_TAG(String sScenario_ActivePallet_TAG) {
      this.sScenario_ActivePallet_TAG = sScenario_ActivePallet_TAG;
   }

   public final boolean getScenarioIsInternal(int i) {
      return this.isInternal.get(i);
   }

   public final void loadCivilizationsManPower() {
      for (int b = 1; b < CFG.game.getCivsSize(); b++) {
         CFG.game.getCiv(b).updateStartingManPower();
      }
   }

   public final void loadCivilizationsFood() {
      for (int b = 1; b < CFG.game.getCivsSize(); b++) {
         CFG.game.getCiv(b).updateStartingFood();
      }
   }

   public class RandomGame_AoCMode {
      public String sTag;
      public int iCapitalID = -1;

      public RandomGame_AoCMode(String sTag) {
         this.sTag = sTag;
         this.iCapitalID = -1;
      }

      public RandomGame_AoCMode(String sTag, int iCapitalID) {
         this.sTag = sTag;
         this.iCapitalID = iCapitalID;
      }
   }
}
