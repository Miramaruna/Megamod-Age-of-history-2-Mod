package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.GdxRuntimeException;
import java.util.ArrayList;
import java.util.List;

public class Menu_MapEditor_FormableCivs_SelectClaimant_List extends SliderMenu {
   public List<String> lCivsTags = null;
   public List<Image> lFlags = new ArrayList<>();
   public List<Integer> lLoadedFlags_TagsIDs = new ArrayList<>();

   public Menu_MapEditor_FormableCivs_SelectClaimant_List() {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
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
      } catch (GdxRuntimeException var13) {
      }

      this.lCivsTags = new ArrayList<>();
      ArrayList<String> lTempNames = new ArrayList<>();
      ArrayList<String> lTempTags = new ArrayList<>();
      if (CFG.sSearch != null && CFG.sSearch.length() > 0) {
         int iSize = tagsSPLITED.length;

         for (int i = 0; i < iSize; i++) {
            if (CFG.langManager.getCiv(tagsSPLITED[i]).toLowerCase().indexOf(CFG.sSearch.toLowerCase()) >= 0 && !CFG.isInFormableCivs(tagsSPLITED[i])) {
               lTempNames.add(CFG.langManager.getCiv(tagsSPLITED[i]));
               lTempTags.add(tagsSPLITED[i]);
            }
         }

         iSize = tagsSPLITED_ED.length;

         for (int var21 = 0; var21 < iSize; var21++) {
            if (CFG.langManager.getCiv(tagsSPLITED_ED[var21]).toLowerCase().indexOf(CFG.sSearch.toLowerCase()) >= 0
               && !CFG.isInFormableCivs(tagsSPLITED_ED[var21])) {
               lTempNames.add(CFG.langManager.getCiv(tagsSPLITED_ED[var21]));
               lTempTags.add(tagsSPLITED_ED[var21]);
            }
         }

         for (int nPosY = 0; lTempNames.size() > 0; nPosY++) {
            int toAddID = 0;

            for (int i2 = 1; i2 < lTempNames.size(); i2++) {
               if (CFG.compareAlphabetic_TwoString(lTempNames.get(toAddID), lTempNames.get(i2))) {
                  toAddID = i2;
               }
            }

            menuElements.add(
               new Button_Menu(
                  CFG.langManager.getCiv(lTempTags.get(toAddID)),
                  (int)(50.0F * CFG.GUI_SCALE),
                  0,
                  CFG.BUTTON_HEIGHT * nPosY + CFG.PADDING * (nPosY + 1),
                  CFG.GAME_WIDTH - (CFG.BUTTON_WIDTH + CFG.BUTTON_WIDTH / 2),
                  CFG.BUTTON_HEIGHT,
                  true
               )
            );
            menuElements.add(
               new Button_Menu_Classic_Wiki(
                  CFG.GAME_WIDTH - (CFG.BUTTON_WIDTH + CFG.BUTTON_WIDTH / 2),
                  CFG.BUTTON_HEIGHT * nPosY + CFG.PADDING * (nPosY + 1),
                  CFG.BUTTON_WIDTH + CFG.BUTTON_WIDTH / 2,
                  CFG.BUTTON_HEIGHT,
                  true
               )
            );
            this.lCivsTags.add(lTempTags.get(toAddID));
            lTempNames.remove(toAddID);
            lTempTags.remove(toAddID);
         }
      } else if (CFG.chosen_AlphabetCharachter == null) {
         int iSize = tagsSPLITED.length;

         for (int ix = 0; ix < iSize; ix++) {
            if (!CFG.isInFormableCivs(tagsSPLITED[ix])) {
               lTempNames.add(CFG.langManager.getCiv(tagsSPLITED[ix]));
               lTempTags.add(tagsSPLITED[ix]);
            }
         }

         iSize = tagsSPLITED_ED.length;

         for (int var17 = 0; var17 < iSize; var17++) {
            if (!CFG.isInFormableCivs(tagsSPLITED_ED[var17])) {
               lTempNames.add(CFG.langManager.getCiv(tagsSPLITED_ED[var17]));
               lTempTags.add(tagsSPLITED_ED[var17]);
            }
         }

         for (int nPosY = 0; lTempNames.size() > 0; nPosY++) {
            int toAddID = 0;

            for (int i3 = 1; i3 < lTempNames.size(); i3++) {
               if (CFG.compareAlphabetic_TwoString(lTempNames.get(toAddID), lTempNames.get(i3))) {
                  toAddID = i3;
               }
            }

            menuElements.add(
               new Button_Menu(
                  CFG.langManager.getCiv(lTempTags.get(toAddID)),
                  (int)(50.0F * CFG.GUI_SCALE),
                  0,
                  CFG.BUTTON_HEIGHT * nPosY + CFG.PADDING * (nPosY + 1),
                  CFG.GAME_WIDTH - (CFG.BUTTON_WIDTH + CFG.BUTTON_WIDTH / 2),
                  CFG.BUTTON_HEIGHT,
                  true
               )
            );
            menuElements.add(
               new Button_Menu_Classic_Wiki(
                  CFG.GAME_WIDTH - (CFG.BUTTON_WIDTH + CFG.BUTTON_WIDTH / 2),
                  CFG.BUTTON_HEIGHT * nPosY + CFG.PADDING * (nPosY + 1),
                  CFG.BUTTON_WIDTH + CFG.BUTTON_WIDTH / 2,
                  CFG.BUTTON_HEIGHT,
                  true
               )
            );
            this.lCivsTags.add(lTempTags.get(toAddID));
            lTempNames.remove(toAddID);
            lTempTags.remove(toAddID);
         }
      } else {
         int iSize = tagsSPLITED.length;

         for (int ixx = 0; ixx < iSize; ixx++) {
            if (CFG.langManager.getCiv(tagsSPLITED[ixx]).charAt(0) == CFG.chosen_AlphabetCharachter.charAt(0) && !CFG.isInFormableCivs(tagsSPLITED[ixx])) {
               lTempNames.add(CFG.langManager.getCiv(tagsSPLITED[ixx]));
               lTempTags.add(tagsSPLITED[ixx]);
            }
         }

         iSize = tagsSPLITED_ED.length;

         for (int var19 = 0; var19 < iSize; var19++) {
            if (CFG.langManager.getCiv(tagsSPLITED_ED[var19]).charAt(0) == CFG.chosen_AlphabetCharachter.charAt(0)
               && !CFG.isInFormableCivs(tagsSPLITED_ED[var19])) {
               lTempNames.add(CFG.langManager.getCiv(tagsSPLITED_ED[var19]));
               lTempTags.add(tagsSPLITED_ED[var19]);
            }
         }

         for (int nPosY = 0; lTempNames.size() > 0; nPosY++) {
            int toAddID = 0;

            for (int i4 = 1; i4 < lTempNames.size(); i4++) {
               if (CFG.compareAlphabetic_TwoString(lTempNames.get(toAddID), lTempNames.get(i4))) {
                  toAddID = i4;
               }
            }

            menuElements.add(
               new Button_Menu(
                  CFG.langManager.getCiv(lTempTags.get(toAddID)),
                  (int)(50.0F * CFG.GUI_SCALE),
                  0,
                  CFG.BUTTON_HEIGHT * nPosY + CFG.PADDING * (nPosY + 1),
                  CFG.GAME_WIDTH - (CFG.BUTTON_WIDTH + CFG.BUTTON_WIDTH / 2),
                  CFG.BUTTON_HEIGHT,
                  true
               )
            );
            menuElements.add(
               new Button_Menu_Classic_Wiki(
                  CFG.GAME_WIDTH - (CFG.BUTTON_WIDTH + CFG.BUTTON_WIDTH / 2),
                  CFG.BUTTON_HEIGHT * nPosY + CFG.PADDING * (nPosY + 1),
                  CFG.BUTTON_WIDTH + CFG.BUTTON_WIDTH / 2,
                  CFG.BUTTON_HEIGHT,
                  true
               )
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
      int tempRandomButton = 0;

      for (int i = 0; i < this.getMenuElementsSize(); i += 2) {
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

      try {
         for (int i = 0; i < this.getMenuElementsSize(); i += 2) {
            if (this.getMenuElement(i).getIsInView()) {
               this.lFlags
                  .get(this.getFlagID(i / 2))
                  .draw(
                     oSB,
                     this.getMenuElement(i).getPosX() + this.getMenuElement(i).getTextPos() / 2 - CFG.CIV_FLAG_WIDTH / 2 + iTranslateX,
                     this.getMenuElement(i).getPosY()
                        + this.getMenuPosY()
                        - this.lFlags.get(this.getFlagID(i / 2)).getHeight()
                        + this.getMenuElement(i).getHeight() / 2
                        - CFG.CIV_FLAG_HEIGHT / 2
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
      } catch (IndexOutOfBoundsException var6) {
      } catch (NullPointerException var7) {
      }

      super.endClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
   }

   @Override
   public final void actionElement(int iID) {
      if (iID % 2 == 0) {
         CFG.formableCivs_GameData.addClaimant(this.lCivsTags.get(iID / 2));
         this.onBack();
         CFG.chosen_AlphabetCharachter = null;
         CFG.sSearch = null;
         this.onBackPressed();
      } else {
         CFG.wikiInormationsLink(this.lCivsTags.get(iID / 2));
      }
   }

   public final void onBack() {
      CFG.menuManager.setViewID(Menu.eMAP_EDITOR_FORMABLE_CIVS_EDIT);
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
