package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.GdxRuntimeException;
import java.util.ArrayList;
import java.util.List;

public class Menu_CreateScenario_Cores_AddCore_List extends SliderMenu {
   public List<String> lCivsTags = null;
   public List<Image> lFlags = new ArrayList<>();
   public List<Integer> lLoadedFlags_TagsIDs = new ArrayList<>();

   public Menu_CreateScenario_Cores_AddCore_List() {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      this.lCivsTags = new ArrayList<>();
      ArrayList<String> lTempNames = new ArrayList<>();
      ArrayList<String> lTempTags = new ArrayList<>();
      menuElements.add(new Button_Menu_LR_Line(CFG.langManager.get("AddNewCivilization"), -1, 0, CFG.PADDING, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT, true));
      menuElements.add(
         new Slider_BG(
            CFG.langManager.get("PercentageOfPopulation"),
            0,
            CFG.BUTTON_HEIGHT + CFG.PADDING * 3,
            CFG.GAME_WIDTH,
            CFG.BUTTON_HEIGHT,
            1,
            99,
            CFG.MANAGE_DIPLOMACY_CUSTOMIZE_ALLIANCE_ID
         ) {
            @Override
            public String getDrawText() {
               return this.getText() + ": " + this.getCurrent() + "%";
            }
         }
      );
      if (CFG.sSearch != null && CFG.sSearch.length() > 0) {
         for (int i = 1; i < CFG.game.getCivsSize(); i++) {
            if (CFG.langManager.getCiv(CFG.game.getCiv(i).getCivName()).toLowerCase().indexOf(CFG.sSearch.toLowerCase()) >= 0) {
               lTempNames.add(CFG.langManager.getCiv(CFG.game.getCiv(i).getCivName()));
               lTempTags.add(CFG.game.getCiv(i).getCivTag());
            }
         }

         int nPosY = 0;

         for (int tID = 0; lTempNames.size() > 0; nPosY++) {
            int toAddID = 0;

            for (int ix = 1; ix < lTempNames.size(); ix++) {
               if (CFG.compareAlphabetic_TwoString(lTempNames.get(toAddID), lTempNames.get(ix))) {
                  toAddID = ix;
               }
            }

            menuElements.add(
               new Button_Menu(
                  CFG.langManager.getCiv(lTempTags.get(toAddID)),
                  (int)(50.0F * CFG.GUI_SCALE),
                  0,
                  CFG.BUTTON_HEIGHT * (nPosY + 2) + CFG.PADDING * (nPosY + 4),
                  CFG.GAME_WIDTH - (CFG.BUTTON_WIDTH + CFG.BUTTON_WIDTH / 2),
                  CFG.BUTTON_HEIGHT,
                  true
               )
            );
            menuElements.add(
               new Button_Menu_Classic_Wiki(
                  tID++,
                  CFG.GAME_WIDTH - (CFG.BUTTON_WIDTH + CFG.BUTTON_WIDTH / 2),
                  CFG.BUTTON_HEIGHT * (nPosY + 2) + CFG.PADDING * (nPosY + 4),
                  CFG.BUTTON_WIDTH + CFG.BUTTON_WIDTH / 2,
                  CFG.BUTTON_HEIGHT,
                  true
               ) {
                  @Override
                  public void buildElementHover() {
                     try {
                        ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
                        ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Wiki") + ": "));
                        nData.add(
                           new MenuElement_Hover_v2_Element_Type_Text(
                              CFG.getWikiInormationsLink_Clear(Menu_CreateScenario_Cores_AddCore_List.this.lCivsTags.get(this.getTextPos())),
                              CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                           )
                        );
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                        this.menuElementHover = new MenuElement_Hover_v2(nElements);
                     } catch (IndexOutOfBoundsException var3) {
                        super.buildElementHover();
                     }
                  }
               }
            );
            this.lCivsTags.add(lTempTags.get(toAddID));
            lTempNames.remove(toAddID);
            lTempTags.remove(toAddID);
         }
      } else if (CFG.chosen_AlphabetCharachter == null) {
         for (int ixx = 1; ixx < CFG.game.getCivsSize(); ixx++) {
            lTempNames.add(CFG.langManager.getCiv(CFG.game.getCiv(ixx).getCivName()));
            lTempTags.add(CFG.game.getCiv(ixx).getCivTag());
         }

         int nPosY = 0;

         for (int tID = 0; lTempNames.size() > 0; nPosY++) {
            int toAddID = 0;

            for (int ixx = 1; ixx < lTempNames.size(); ixx++) {
               if (CFG.compareAlphabetic_TwoString(lTempNames.get(toAddID), lTempNames.get(ixx))) {
                  toAddID = ixx;
               }
            }

            menuElements.add(
               new Button_Menu(
                  CFG.langManager.getCiv(lTempTags.get(toAddID)),
                  (int)(50.0F * CFG.GUI_SCALE),
                  0,
                  CFG.BUTTON_HEIGHT * (nPosY + 2) + CFG.PADDING * (nPosY + 4),
                  CFG.GAME_WIDTH - (CFG.BUTTON_WIDTH + CFG.BUTTON_WIDTH / 2),
                  CFG.BUTTON_HEIGHT,
                  true
               )
            );
            menuElements.add(
               new Button_Menu_Classic_Wiki(
                  tID++,
                  CFG.GAME_WIDTH - (CFG.BUTTON_WIDTH + CFG.BUTTON_WIDTH / 2),
                  CFG.BUTTON_HEIGHT * (nPosY + 2) + CFG.PADDING * (nPosY + 4),
                  CFG.BUTTON_WIDTH + CFG.BUTTON_WIDTH / 2,
                  CFG.BUTTON_HEIGHT,
                  true
               ) {
                  @Override
                  public void buildElementHover() {
                     try {
                        ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
                        ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Wiki") + ": "));
                        nData.add(
                           new MenuElement_Hover_v2_Element_Type_Text(
                              CFG.getWikiInormationsLink_Clear(Menu_CreateScenario_Cores_AddCore_List.this.lCivsTags.get(this.getTextPos())),
                              CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                           )
                        );
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                        this.menuElementHover = new MenuElement_Hover_v2(nElements);
                     } catch (IndexOutOfBoundsException var3) {
                        super.buildElementHover();
                     }
                  }
               }
            );
            this.lCivsTags.add(lTempTags.get(toAddID));
            lTempNames.remove(toAddID);
            lTempTags.remove(toAddID);
         }
      } else {
         for (int ixxx = 1; ixxx < CFG.game.getCivsSize(); ixxx++) {
            if (CFG.langManager.getCiv(CFG.game.getCiv(ixxx).getCivName()).charAt(0) == CFG.chosen_AlphabetCharachter.charAt(0)) {
               lTempNames.add(CFG.langManager.getCiv(CFG.game.getCiv(ixxx).getCivName()));
               lTempTags.add(CFG.game.getCiv(ixxx).getCivTag());
            }
         }

         int nPosY = 0;

         for (int tID = 0; lTempNames.size() > 0; nPosY++) {
            int toAddID = 0;

            for (int ixxxx = 1; ixxxx < lTempNames.size(); ixxxx++) {
               if (CFG.compareAlphabetic_TwoString(lTempNames.get(toAddID), lTempNames.get(ixxxx))) {
                  toAddID = ixxxx;
               }
            }

            menuElements.add(
               new Button_Menu(
                  CFG.langManager.getCiv(lTempTags.get(toAddID)),
                  (int)(50.0F * CFG.GUI_SCALE),
                  0,
                  CFG.BUTTON_HEIGHT * (nPosY + 2) + CFG.PADDING * (nPosY + 4),
                  CFG.GAME_WIDTH - (CFG.BUTTON_WIDTH + CFG.BUTTON_WIDTH / 2),
                  CFG.BUTTON_HEIGHT,
                  true
               )
            );
            menuElements.add(
               new Button_Menu_Classic_Wiki(
                  tID++,
                  CFG.GAME_WIDTH - (CFG.BUTTON_WIDTH + CFG.BUTTON_WIDTH / 2),
                  CFG.BUTTON_HEIGHT * (nPosY + 2) + CFG.PADDING * (nPosY + 4),
                  CFG.BUTTON_WIDTH + CFG.BUTTON_WIDTH / 2,
                  CFG.BUTTON_HEIGHT,
                  true
               ) {
                  @Override
                  public void buildElementHover() {
                     try {
                        ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
                        ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Wiki") + ": "));
                        nData.add(
                           new MenuElement_Hover_v2_Element_Type_Text(
                              CFG.getWikiInormationsLink_Clear(Menu_CreateScenario_Cores_AddCore_List.this.lCivsTags.get(this.getTextPos())),
                              CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                           )
                        );
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                        this.menuElementHover = new MenuElement_Hover_v2(nElements);
                     } catch (IndexOutOfBoundsException var3) {
                        super.buildElementHover();
                     }
                  }
               }
            );
            this.lCivsTags.add(lTempTags.get(toAddID));
            lTempNames.remove(toAddID);
            lTempTags.remove(toAddID);
         }
      }

      this.initMenu(
         null,
         0,
         CFG.BUTTON_HEIGHT * 3 / 4 + CFG.BUTTON_HEIGHT + CFG.PADDING,
         CFG.GAME_WIDTH,
         CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT * 3 / 4 - CFG.BUTTON_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING * 2,
         menuElements,
         true,
         false
      );
      this.updateLanguage();
   }

   @Override
   public void updateLanguage() {
   }

   @Override
   public void updateMenuElements_IsInView() {
      super.updateMenuElements_IsInView();
      int tempRandomButton = 2;

      for (int i = 2; i < this.getMenuElementsSize(); i += 2) {
         int tempTagID = this.getIsLoaded(this.lCivsTags.get((i - tempRandomButton) / 2));
         if (this.getMenuElement(i).getIsInView()) {
            if (tempTagID < 0) {
               this.loadFlag((i - tempRandomButton) / 2);
            }
         } else if (tempTagID >= 0) {
            this.lFlags.get(tempTagID).getTexture().dispose();
            this.lFlags.set(tempTagID, null);
            this.lFlags.remove(tempTagID);
            this.lLoadedFlags_TagsIDs.remove(tempTagID);
         }
      }
   }

   public final int getIsLoaded(String nCivTag) {
      for (int i = 0; i < this.lLoadedFlags_TagsIDs.size(); i++) {
         if (this.lCivsTags.get(this.lLoadedFlags_TagsIDs.get(i)).equals(nCivTag)) {
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

   public final void loadFlag(int nCivTagID) {
      try {
         try {
            this.lFlags.add(new Image(new Texture(Gdx.files.internal("game/flags/" + this.lCivsTags.get(nCivTagID) + ".png")), Texture.TextureFilter.Nearest));
         } catch (GdxRuntimeException var7) {
            try {
               this.lFlags
                  .add(
                     new Image(
                        new Texture(Gdx.files.internal("game/flags/" + CFG.ideologiesManager.getRealTag(this.lCivsTags.get(nCivTagID)) + ".png")),
                        Texture.TextureFilter.Nearest
                     )
                  );
            } catch (GdxRuntimeException var6) {
               if (CFG.isAndroid()) {
                  try {
                     this.lFlags
                        .add(
                           new Image(
                              new Texture(
                                 Gdx.files
                                    .local("game/civilizations_editor/" + this.lCivsTags.get(nCivTagID) + "/" + this.lCivsTags.get(nCivTagID) + "_FL.png")
                              ),
                              Texture.TextureFilter.Nearest
                           )
                        );
                  } catch (GdxRuntimeException var5) {
                     this.lFlags
                        .add(
                           new Image(
                              new Texture(
                                 Gdx.files
                                    .internal("game/civilizations_editor/" + this.lCivsTags.get(nCivTagID) + "/" + this.lCivsTags.get(nCivTagID) + "_FL.png")
                              ),
                              Texture.TextureFilter.Nearest
                           )
                        );
                  }
               } else {
                  this.lFlags
                     .add(
                        new Image(
                           new Texture(
                              Gdx.files
                                 .internal("game/civilizations_editor/" + this.lCivsTags.get(nCivTagID) + "/" + this.lCivsTags.get(nCivTagID) + "_FL.png")
                           ),
                           Texture.TextureFilter.Nearest
                        )
                     );
               }
            }
         }
      } catch (GdxRuntimeException var8) {
         this.lFlags.add(new Image(new Texture(Gdx.files.internal("game/flags/ran.png")), Texture.TextureFilter.Nearest));
      }

      this.lLoadedFlags_TagsIDs.add(nCivTagID);
   }

   @Override
   public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      super.beginClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
      super.drawMenu(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
      int tempRandomButton = 2;

      try {
         for (int i = tempRandomButton; i < this.getMenuElementsSize(); i += 2) {
            if (this.getMenuElement(i).getIsInView()) {
               this.lFlags
                  .get(this.getFlagID((i - tempRandomButton) / 2))
                  .draw(
                     oSB,
                     this.getMenuElement(i).getPosX() + this.getMenuElement(i).getTextPos() / 2 - CFG.CIV_FLAG_WIDTH / 2 + iTranslateX,
                     this.getMenuElement(i).getPosY()
                        + this.getMenuPosY()
                        + this.getMenuElement(i).getHeight() / 2
                        - CFG.CIV_FLAG_HEIGHT / 2
                        - this.lFlags.get(this.getFlagID((i - tempRandomButton) / 2)).getHeight()
                        + iTranslateY,
                     CFG.CIV_FLAG_WIDTH,
                     CFG.CIV_FLAG_HEIGHT
                  );
               ImageManager.getImage(Images.flag_rect)
                  .draw(
                     oSB,
                     this.getMenuElement(i).getPosX() + this.getMenuElement(i).getTextPos() / 2 - CFG.CIV_FLAG_WIDTH / 2 + iTranslateX,
                     this.getMenuElement(i).getPosY() + this.getMenuPosY() + this.getMenuElement(i).getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 + iTranslateY
                  );
            }
         }
      } catch (IndexOutOfBoundsException var7) {
      } catch (NullPointerException var8) {
      }

      super.endClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
   }

   @Override
   public final void actionElement(int iID) {
      switch (iID) {
         case 0:
            CFG.menuManager.setViewID(Menu.eCREATE_SCENARIO_CORES_ADD_CIV);
            return;
         case 1:
            CFG.MANAGE_DIPLOMACY_CUSTOMIZE_ALLIANCE_ID = this.getMenuElement(iID).getCurrent();
            return;
         default:
            iID -= 2;
            if (iID % 2 == 0) {
               CFG.chosen_AlphabetCharachter = null;
               CFG.sSearch = null;

               for (int i = 0; i < CFG.game.getSelectedProvinces().getProvincesSize(); i++) {
                  CFG.province_Cores_GameData
                     .addCore(
                        CFG.game.getSelectedProvinces().getProvince(i), CFG.game.getProvince(CFG.game.getSelectedProvinces().getProvince(i)).getCivID(), 100
                     );
                  int tID = this.getCivID(this.lCivsTags.get(iID / 2));
                  CFG.game.getProvince(CFG.game.getSelectedProvinces().getProvince(i)).getCore().addNewCore(tID, 1);
                  CFG.province_Cores_GameData.addCore(CFG.game.getSelectedProvinces().getProvince(i), tID, CFG.MANAGE_DIPLOMACY_CUSTOMIZE_ALLIANCE_ID);
               }

               CFG.game.getSelectedProvinces().clearSelectedProvinces();
               if (CFG.game.getActiveProvinceID() >= 0
                  && !CFG.game.getProvince(CFG.game.getActiveProvinceID()).getSeaProvince()
                  && CFG.game.getProvince(CFG.game.getActiveProvinceID()).getWasteland() < 0) {
                  CFG.game.getSelectedProvinces().addProvince(CFG.game.getActiveProvinceID());
               }

               this.onBack();
               this.onBackPressed();
            } else {
               CFG.EDITOR_ACTIVE_GAMEDATA_TAG = this.lCivsTags.get(iID / 2);
               CFG.setDialogType(Dialog.GO_TO_WIKI);
            }
      }
   }

   public final int getCivID(String nTag) {
      for (int i = 1; i < CFG.game.getCivsSize(); i++) {
         if (CFG.game.getCiv(i).getCivTag().equals(nTag)) {
            return i;
         }
      }

      return 0;
   }

   public final void onBack() {
      CFG.menuManager.setViewID(Menu.eCREATE_SCENARIO_CORES);
      CFG.menuManager.rebuildCreateScenario_Cores_SetUp();
   }

   @Override
   public void onBackPressed() {
      for (int i = 0; i < this.lFlags.size(); i++) {
         this.lFlags.get(i).getTexture().dispose();
      }

      this.lFlags.clear();
      this.lLoadedFlags_TagsIDs.clear();
      this.lCivsTags.clear();
   }
}
