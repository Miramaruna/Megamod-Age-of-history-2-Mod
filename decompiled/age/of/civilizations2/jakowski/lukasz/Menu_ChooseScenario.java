package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.GdxRuntimeException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Menu_ChooseScenario extends SliderMenu {
   public List<List<Image>> lFlags = new ArrayList<>();
   public List<Integer> lLoadedFlags_TagsIDs = new ArrayList<>();

   public Menu_ChooseScenario() {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      menuElements.add(new Button_Menu_ReflectedBG(null, -1, 0, CFG.PADDING, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT, true));

      for (int i = 0; i < Game_Scenarios.SCENARIOS_SIZE; i++) {
         menuElements.add(
            new Button_Menu_Scenario(
               i,
               null,
               (int)(50.0F * CFG.GUI_SCALE),
               0,
               CFG.BUTTON_HEIGHT * (i + 1) + CFG.PADDING * (i + 2),
               CFG.GAME_WIDTH - CFG.BUTTON_WIDTH,
               CFG.BUTTON_HEIGHT,
               true,
               Menu_ChooseScenario_Title.iPreviewScenarioID == i
            ) {
               @Override
               public void buildElementHover() {
                  try {
                     ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
                     ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           CFG.langManager.get(CFG.game.getGameScenarios().getScenarioName(this.getCurrent())), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                        )
                     );
                     nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.time, CFG.PADDING, CFG.PADDING));
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           CFG.game.getGameScenarios().getScenarioDay(this.getCurrent())
                              + " "
                              + Game_Calendar.getMonthName(CFG.game.getGameScenarios().getScenarioMonth(this.getCurrent()))
                              + " "
                              + CFG.gameAges.getYear(CFG.game.getGameScenarios().getScenarioYear(this.getCurrent())),
                           CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                        )
                     );
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                     nData.add(new MenuElement_Hover_v2_Element_Type_Space());
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(CFG.gameAges.getAge(CFG.game.getGameScenarios().getScenarioAge(this.getCurrent())).getName())
                     );
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Civilizations") + ": "));
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           "" + CFG.game.getGameScenarios().getNumOfCivs(this.getCurrent()), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                        )
                     );
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Author") + ": "));
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           "" + CFG.game.getGameScenarios().getScenarioAuthor(this.getCurrent()), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                        )
                     );
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                     this.menuElementHover = new MenuElement_Hover_v2(nElements);
                  } catch (IndexOutOfBoundsException var3) {
                     this.menuElementHover = null;
                  }
               }

               @Override
               public boolean getCheckboxState() {
                  return this.getCurrent() == Menu_ChooseScenario_Title.iPreviewScenarioID;
               }
            }
         );
         menuElements.add(
            new Button_Menu_Classic_Wiki(
               i,
               CFG.GAME_WIDTH - CFG.BUTTON_WIDTH,
               CFG.BUTTON_HEIGHT * (i + 1) + CFG.PADDING * (i + 2),
               CFG.BUTTON_WIDTH,
               CFG.BUTTON_HEIGHT,
               CFG.game.getGameScenarios().getScenarioWiki(i).length() > 0
            ) {
               @Override
               public void buildElementHover() {
                  if (this.getClickable()) {
                     ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
                     ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Wiki") + ": ", CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.game.getGameScenarios().getScenarioWiki(this.getCurrent())));
                     nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.wikipedia, CFG.PADDING, 0));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                     this.menuElementHover = new MenuElement_Hover_v2(nElements);
                  } else {
                     this.menuElementHover = null;
                  }
               }
            }
         );
      }

      this.initMenu(
         null,
         0,
         CFG.BUTTON_HEIGHT * 3 / 4 + CFG.BUTTON_HEIGHT * 3 + CFG.PADDING,
         CFG.GAME_WIDTH,
         CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT * 3 / 4 - CFG.BUTTON_HEIGHT * 3 - CFG.PADDING,
         menuElements
      );
      this.updateLanguage();
   }

   @Override
   public void updateLanguage() {
      this.getMenuElement(0).setText(CFG.langManager.get("RandomScenario"));

      for (int i = 0; i < Game_Scenarios.SCENARIOS_SIZE; i++) {
         this.getMenuElement(i * 2 + 1).setText(CFG.langManager.get(CFG.game.getGameScenarios().getScenarioName(i)));
         this.getMenuElement(i * 2 + 1).setCurrent(i);
      }
   }

   @Override
   public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      super.beginClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
      super.drawMenu(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);

      try {
         for (int i = 1; i < this.getMenuElementsSize(); i += 2) {
            if (this.getMenuElement(i).getIsInView()) {
               int nFlagsID = this.getFlagID((i - 1) / 2);

               for (int j = this.lFlags.get(nFlagsID).size() - 1; j >= 0; j--) {
                  this.lFlags
                     .get(nFlagsID)
                     .get(j)
                     .draw(
                        oSB,
                        this.getMenuElement(i).getPosX()
                           + this.getMenuElement(i).getWidth()
                           - CFG.CIV_FLAG_WIDTH * 2
                           - CFG.CIV_FLAG_WIDTH * 4 / 5 * (j - 1)
                           - CFG.PADDING
                           + iTranslateX,
                        this.getMenuElement(i).getPosY()
                           + this.getMenuPosY()
                           + this.getMenuElement(i).getHeight() / 2
                           - CFG.CIV_FLAG_HEIGHT / 2
                           - this.lFlags.get(nFlagsID).get(j).getHeight()
                           + iTranslateY,
                        CFG.CIV_FLAG_WIDTH,
                        CFG.CIV_FLAG_HEIGHT
                     );
                  ImageManager.getImage(Images.flag_rect)
                     .draw(
                        oSB,
                        this.getMenuElement(i).getPosX()
                           + this.getMenuElement(i).getWidth()
                           - CFG.CIV_FLAG_WIDTH * 2
                           - CFG.CIV_FLAG_WIDTH * 4 / 5 * (j - 1)
                           - CFG.PADDING
                           + iTranslateX,
                        this.getMenuElement(i).getPosY() + this.getMenuPosY() + this.getMenuElement(i).getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 + iTranslateY
                     );
               }
            }
         }
      } catch (IndexOutOfBoundsException var8) {
      }

      super.endClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
   }

   @Override
   public void updateMenuElements_IsInView() {
      super.updateMenuElements_IsInView();

      for (int i = 1; i < this.getMenuElementsSize(); i += 2) {
         int tempTagID = this.getIsLoaded(CFG.game.getGameScenarios().getScenarioTag((i - 1) / 2));
         if (this.getMenuElement(i).getIsInView()) {
            if (tempTagID < 0) {
               this.loadFlag((i - 1) / 2);
            }
         } else if (tempTagID >= 0) {
            int j = 0;

            while (j < this.lFlags.get(tempTagID).size()) {
               this.lFlags.get(tempTagID).get(j).getTexture().dispose();
               this.lFlags.get(tempTagID).set(j, null);
               this.lFlags.get(tempTagID).remove(j);
            }

            this.lFlags.remove(tempTagID);
            this.lLoadedFlags_TagsIDs.remove(tempTagID);
         }
      }
   }

   public final int getIsLoaded(String nTag) {
      for (int i = 0; i < this.lLoadedFlags_TagsIDs.size(); i++) {
         if (CFG.game.getGameScenarios().getScenarioTag(this.lLoadedFlags_TagsIDs.get(i)).equals(nTag)) {
            return i;
         }
      }

      return -1;
   }

   public final int getFlagID(int nCivTagID) {
      for (int i = 0; i < this.lLoadedFlags_TagsIDs.size(); i++) {
         if (this.lLoadedFlags_TagsIDs.get(i) == nCivTagID) {
            return i;
         }
      }

      return 0;
   }

   public final void loadFlag(int nTag) {
      this.lFlags.add(new ArrayList<>());

      try {
         Scenario_GameData tempScenarioGameData;
         try {
            if (CFG.game.getGameScenarios().getScenarioIsInternal(nTag)) {
               FileHandle file = Gdx.files
                  .internal(
                     "map/"
                        + CFG.map.getFile_ActiveMap_Path()
                        + "scenarios/"
                        + CFG.game.getGameScenarios().getScenarioTag(nTag)
                        + "/"
                        + CFG.game.getGameScenarios().getScenarioTag(nTag)
                  );
               tempScenarioGameData = (Scenario_GameData)CFG.deserialize(file.readBytes());
            } else {
               FileHandle file = Gdx.files
                  .local(
                     "map/"
                        + CFG.map.getFile_ActiveMap_Path()
                        + "scenarios/"
                        + CFG.game.getGameScenarios().getScenarioTag(nTag)
                        + "/"
                        + CFG.game.getGameScenarios().getScenarioTag(nTag)
                  );
               tempScenarioGameData = (Scenario_GameData)CFG.deserialize(file.readBytes());
            }
         } catch (GdxRuntimeException var8) {
            FileHandle file = Gdx.files
               .local(
                  "map/"
                     + CFG.map.getFile_ActiveMap_Path()
                     + "scenarios/"
                     + CFG.game.getGameScenarios().getScenarioTag(0)
                     + "/"
                     + CFG.game.getGameScenarios().getScenarioTag(0)
               );
            tempScenarioGameData = (Scenario_GameData)CFG.deserialize(file.readBytes());
         }

         for (int i = 0; i < tempScenarioGameData.getCivSize() && i < 10; i++) {
            try {
               try {
                  this.lFlags
                     .get(this.lFlags.size() - 1)
                     .add(new Image(new Texture(Gdx.files.internal("game/flags/" + tempScenarioGameData.getCivTag(i) + ".png")), Texture.TextureFilter.Nearest));
               } catch (GdxRuntimeException var10) {
                  try {
                     this.lFlags
                        .get(this.lFlags.size() - 1)
                        .add(
                           new Image(
                              new Texture(Gdx.files.internal("game/flags/" + CFG.ideologiesManager.getRealTag(tempScenarioGameData.getCivTag(i)) + ".png")),
                              Texture.TextureFilter.Nearest
                           )
                        );
                  } catch (GdxRuntimeException var9) {
                     if (CFG.isAndroid()) {
                        try {
                           this.lFlags
                              .get(this.lFlags.size() - 1)
                              .add(
                                 new Image(
                                    new Texture(
                                       Gdx.files
                                          .local(
                                             "game/civilizations_editor/"
                                                + CFG.ideologiesManager.getRealTag(tempScenarioGameData.getCivTag(i))
                                                + "/"
                                                + CFG.ideologiesManager.getRealTag(tempScenarioGameData.getCivTag(i))
                                                + "_FL.png"
                                          )
                                    ),
                                    Texture.TextureFilter.Nearest
                                 )
                              );
                        } catch (GdxRuntimeException var7) {
                           this.lFlags
                              .get(this.lFlags.size() - 1)
                              .add(
                                 new Image(
                                    new Texture(
                                       Gdx.files
                                          .internal(
                                             "game/civilizations_editor/"
                                                + CFG.ideologiesManager.getRealTag(tempScenarioGameData.getCivTag(i))
                                                + "/"
                                                + CFG.ideologiesManager.getRealTag(tempScenarioGameData.getCivTag(i))
                                                + "_FL.png"
                                          )
                                    ),
                                    Texture.TextureFilter.Nearest
                                 )
                              );
                        }
                     } else {
                        this.lFlags
                           .get(this.lFlags.size() - 1)
                           .add(
                              new Image(
                                 new Texture(
                                    Gdx.files
                                       .internal(
                                          "game/civilizations_editor/"
                                             + CFG.ideologiesManager.getRealTag(tempScenarioGameData.getCivTag(i))
                                             + "/"
                                             + CFG.ideologiesManager.getRealTag(tempScenarioGameData.getCivTag(i))
                                             + "_FL.png"
                                       )
                                 ),
                                 Texture.TextureFilter.Nearest
                              )
                           );
                     }
                  }
               }
            } catch (GdxRuntimeException var11) {
               this.lFlags.get(this.lFlags.size() - 1).add(new Image(new Texture(Gdx.files.internal("game/flags/ran.png")), Texture.TextureFilter.Nearest));
            }
         }

         Object var18 = null;
      } catch (ClassNotFoundException var12) {
      } catch (IOException var13) {
      } catch (OutOfMemoryError var14) {
      } catch (NullPointerException var15) {
      }

      this.lLoadedFlags_TagsIDs.add(nTag);
   }

   @Override
   public final void actionElement(int iID) {
      switch (iID) {
         case 0:
            Random oR = new Random();
            if (Game_Scenarios.SCENARIOS_SIZE > 1) {
               int nScenarioID;
               while ((nScenarioID = oR.nextInt(Game_Scenarios.SCENARIOS_SIZE)) == CFG.game.getScenarioID()) {
               }

               CFG.game.setScenarioID(nScenarioID);
            }

            CFG.viewsManager.disableAllViews();
            CFG.game.loadScenario(false);
            CFG.game.initPlayers();
            CFG.game.setActiveProvinceID(-1);
            CFG.menuManager.setViewID(CFG.backToMenu);
            CFG.menuManager.setBackAnimation(true);
            Menu_ChooseScenario_Title.disposePreview();
            Menu_ChooseScenario_Title.drawPreview = false;
            this.onBackPressed();
            CFG.menuManager.rebuildCivilizations_Info_Players();
            break;
         default:
            if ((iID - 1) % 2 == 0) {
               try {
                  this.getMenuElement(Menu_ChooseScenario_Title.iPreviewScenarioID * 2 + 1).setCheckboxState(false);
               } catch (IndexOutOfBoundsException var5) {
               }

               Menu_ChooseScenario_Title.loadPreview((iID - 1) / 2);

               try {
                  this.getMenuElement(Menu_ChooseScenario_Title.iPreviewScenarioID * 2 + 1).setCheckboxState(true);
               } catch (IndexOutOfBoundsException var4) {
               }
            } else if (CFG.game.getGameScenarios().getScenarioWiki((iID - 1) / 2).length() > 0) {
               CFG.EDITOR_ACTIVE_GAMEDATA_TAG = CFG.game.getGameScenarios().getScenarioWiki((iID - 1) / 2);
               CFG.setDialogType(Dialog.GO_TO_WIKI_SCENARIO);
            } else {
               CFG.toast.setInView(CFG.langManager.get("NoData"), CFG.COLOR_TEXT_MODIFIER_NEGATIVE2);
            }
      }
   }

   @Override
   public void onBackPressed() {
      for (int j = 0; j < this.lFlags.size(); j++) {
         for (int i = 0; i < this.lFlags.get(j).size(); i++) {
            this.lFlags.get(j).get(i).getTexture().dispose();
         }
      }

      this.lFlags.clear();
      this.lLoadedFlags_TagsIDs.clear();
   }
}
