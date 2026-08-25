package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.GdxRuntimeException;
import java.util.ArrayList;
import java.util.List;

public class Menu_Editor_Civilizations extends SliderMenu {
   public List<String> lCivsTags = null;
   public List<Image> lFlags = new ArrayList<>();
   public List<Integer> lLoadedFlags_TagsIDs = new ArrayList<>();

   public Menu_Editor_Civilizations() {
      this.lCivsTags = new ArrayList<>();
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      menuElements.add(new Button_Menu_LR_Line(null, -1, 0, 0, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT, true));
      menuElements.add(new Button_Menu_LR_Line(null, -1, 0, CFG.PADDING, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT, true));

      try {
         FileHandle tempFileT = null;
         tempFileT = CFG.readLocalFiles()
            ? Gdx.files.local("game/civilizations_editor/Age_of_Civilizations")
            : Gdx.files.internal("game/civilizations_editor/Age_of_Civilizations");
         String tempT = tempFileT.readString();
         String[] tagsSPLITED = tempT.split(";");
         int iSize = tagsSPLITED.length;

         for (int i = 0; i < iSize; i++) {
            try {
               if (CFG.readLocalFiles()) {
                  try {
                     FileHandle file = Gdx.files.local("game/civilizations_editor/" + tagsSPLITED[i] + "/" + tagsSPLITED[i] + "_NM");
                     menuElements.add(
                        new Button_Menu(
                           file.readString(),
                           (int)(50.0F * CFG.GUI_SCALE),
                           0,
                           CFG.BUTTON_HEIGHT * (i + 1) + CFG.PADDING * (i + 2),
                           CFG.GAME_WIDTH,
                           CFG.BUTTON_HEIGHT,
                           true
                        )
                     );
                  } catch (GdxRuntimeException var10) {
                     FileHandle file2 = Gdx.files.internal("game/civilizations_editor/" + tagsSPLITED[i] + "/" + tagsSPLITED[i] + "_NM");
                     menuElements.add(
                        new Button_Menu(
                           file2.readString(),
                           (int)(50.0F * CFG.GUI_SCALE),
                           0,
                           CFG.BUTTON_HEIGHT * (i + 1) + CFG.PADDING * (i + 2),
                           CFG.GAME_WIDTH,
                           CFG.BUTTON_HEIGHT,
                           true
                        )
                     );
                  }
               } else {
                  FileHandle file = Gdx.files.internal("game/civilizations_editor/" + tagsSPLITED[i] + "/" + tagsSPLITED[i] + "_NM");
                  menuElements.add(
                     new Button_Menu(
                        file.readString(),
                        (int)(50.0F * CFG.GUI_SCALE),
                        0,
                        CFG.BUTTON_HEIGHT * (i + 1) + CFG.PADDING * (i + 2),
                        CFG.GAME_WIDTH,
                        CFG.BUTTON_HEIGHT,
                        true
                     )
                  );
               }

               this.lCivsTags.add(tagsSPLITED[i]);
            } catch (GdxRuntimeException var11) {
               menuElements.add(
                  new Button_Menu(
                     tagsSPLITED[i],
                     (int)(50.0F * CFG.GUI_SCALE),
                     0,
                     CFG.BUTTON_HEIGHT * (i + 1) + CFG.PADDING * (i + 2),
                     CFG.GAME_WIDTH,
                     CFG.BUTTON_HEIGHT,
                     true
                  )
               );
               this.lCivsTags.add(tagsSPLITED[i]);
            }
         }
      } catch (GdxRuntimeException var12) {
      }

      this.initMenuWithBackButton(
         new SliderMenuTitle(null, CFG.BUTTON_HEIGHT * 3 / 4, false, false),
         0,
         CFG.BUTTON_HEIGHT * 3 / 4,
         CFG.GAME_WIDTH,
         CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT * 3 / 4,
         menuElements
      );
      this.updateLanguage();
   }

   @Override
   public void updateLanguage() {
      this.getMenuElement(0).setText(CFG.langManager.get("Back"));
      this.getMenuElement(1).setText(CFG.langManager.get("CreateNewCivilization"));
      this.getTitle().setText(CFG.langManager.get("CivilizationEditor"));
   }

   @Override
   public void updateMenuElements_IsInView() {
      super.updateMenuElements_IsInView();
      int tempRandomButton = 2;

      for (int i = 2; i < this.getMenuElementsSize(); i++) {
         int tempTagID = this.getIsLoaded(this.lCivsTags.get(i - tempRandomButton));
         if (this.getMenuElement(i).getIsInView()) {
            if (tempTagID < 0) {
               this.loadFlag(i - tempRandomButton);
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
         if (CFG.readLocalFiles()) {
            try {
               this.lFlags
                  .add(
                     new Image(
                        new Texture(
                           Gdx.files.local("game/civilizations_editor/" + this.lCivsTags.get(nCivTagID) + "/" + this.lCivsTags.get(nCivTagID) + "_FL.png")
                        ),
                        Texture.TextureFilter.Nearest
                     )
                  );
            } catch (GdxRuntimeException var3) {
               this.lFlags
                  .add(
                     new Image(
                        new Texture(
                           Gdx.files.internal("game/civilizations_editor/" + this.lCivsTags.get(nCivTagID) + "/" + this.lCivsTags.get(nCivTagID) + "_FL.png")
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
                        Gdx.files.internal("game/civilizations_editor/" + this.lCivsTags.get(nCivTagID) + "/" + this.lCivsTags.get(nCivTagID) + "_FL.png")
                     ),
                     Texture.TextureFilter.Nearest
                  )
               );
         }
      } catch (GdxRuntimeException var4) {
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
         for (int i = tempRandomButton; i < this.getMenuElementsSize(); i++) {
            if (this.getMenuElement(i).getIsInView()) {
               this.lFlags
                  .get(this.getFlagID(i - tempRandomButton))
                  .draw(
                     oSB,
                     this.getMenuElement(i).getPosX() + this.getMenuElement(i).getTextPos() / 2 - CFG.CIV_FLAG_WIDTH / 2 + iTranslateX,
                     this.getMenuElement(i).getPosY()
                        - this.lFlags.get(this.getFlagID(i - tempRandomButton)).getHeight()
                        + this.getMenuPosY()
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
      } catch (IndexOutOfBoundsException var7) {
      } catch (NullPointerException var8) {
      }

      super.endClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
   }

   @Override
   public final void actionElement(int iID) {
      switch (iID) {
         case 0:
            this.onBackPressed();
            return;
         case 1:
            CFG.backToMenu = Menu.eEDITOR_CIVILIZATIONS;
            CFG.menuManager.getColorPicker().setPosX(CFG.CIV_INFO_MENU_WIDTH + CFG.CIV_INFO_MENU_WIDTH * 3 / 4 + CFG.PADDING * 4);
            CFG.flagManager.loadData();
            CFG.flagManager.initFlagEdit();
            CFG.EDITOR_ACTIVE_GAMEDATA_TAG = "" + System.currentTimeMillis() + CFG.extraRandomTag();
            CFG.editorCivilization_GameData = new Civilization_GameData3();
            CFG.menuManager.setViewID(Menu.eCREATE_CIVILIZATION);
            Game_Render_Province.updateDrawProvinces();
            return;
         default:
            CFG.backToMenu = Menu.eEDITOR_CIVILIZATIONS;
            CFG.menuManager.getColorPicker().setPosX(CFG.CIV_INFO_MENU_WIDTH + CFG.CIV_INFO_MENU_WIDTH * 3 / 4 + CFG.PADDING * 4);
            CFG.EDITOR_ACTIVE_GAMEDATA_TAG = this.lCivsTags.get(iID - 2);
            CFG.flagManager.loadData();
            CFG.flagManager.loadFlagEdit();
            CFG.menuManager.setViewID(Menu.eCREATE_CIVILIZATION);
            Game_Render_Province.updateDrawProvinces();
      }
   }

   @Override
   public void onBackPressed() {
      CFG.menuManager.setViewID(Menu.eEDITOR);
      CFG.menuManager.setBackAnimation(true);
      this.disposeData();
   }

   @Override
   public void setVisible(boolean visible) {
      super.setVisible(visible);
      if (!visible) {
         this.disposeData();
      }
   }

   public void disposeData() {
      for (int i = 0; i < this.lFlags.size(); i++) {
         this.lFlags.get(i).getTexture().dispose();
      }

      this.lFlags.clear();
      this.lLoadedFlags_TagsIDs.clear();
      this.lCivsTags.clear();
   }
}
