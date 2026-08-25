package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.GdxRuntimeException;
import java.util.ArrayList;
import java.util.List;

public class Menu_Editor_Unions_List extends SliderMenu {
   public List<String> lCivsTags = null;
   public List<Image> lFlags = new ArrayList<>();
   public List<Integer> lLoadedFlags_TagsIDs = new ArrayList<>();

   public Menu_Editor_Unions_List() {
      this.lCivsTags = new ArrayList<>();
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      menuElements.add(new Button_Menu_LR_Line(null, -1, 0, CFG.PADDING, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT, true));

      for (int i = 0; i < CFG.unionsManager.unions.lUnions.size(); i++) {
         String tTags = ", ";

         for (int j = 0; j < CFG.unionsManager.unions.lUnions.get(i).lCivsTags.size(); j++) {
            tTags = tTags
               + ""
               + CFG.langManager.getCiv(CFG.unionsManager.unions.lUnions.get(i).lCivsTags.get(j))
               + (CFG.unionsManager.unions.lUnions.get(i).lCivsTags.size() - 1 == j ? "" : "-");
         }

         menuElements.add(
            new Button_Menu(
               CFG.langManager.get("Civilization") + ": " + CFG.langManager.getCiv(CFG.unionsManager.unions.lUnions.get(i).lCreateCivTag) + tTags,
               (int)(50.0F * CFG.GUI_SCALE),
               0,
               CFG.PADDING * (i + 2) + CFG.BUTTON_HEIGHT * (i + 1),
               CFG.GAME_WIDTH - CFG.BUTTON_WIDTH - CFG.BUTTON_WIDTH / 2,
               CFG.BUTTON_HEIGHT,
               true
            )
         );
         menuElements.add(
            new Button_Menu_Remove(
               CFG.GAME_WIDTH - CFG.BUTTON_WIDTH - CFG.BUTTON_WIDTH / 2,
               CFG.PADDING * (i + 2) + CFG.BUTTON_HEIGHT * (i + 1),
               CFG.BUTTON_WIDTH + CFG.BUTTON_WIDTH / 2,
               CFG.BUTTON_HEIGHT,
               true
            )
         );
         this.lCivsTags.add(CFG.unionsManager.unions.lUnions.get(i).lCreateCivTag);
      }

      this.initMenu(
         null, 0, CFG.BUTTON_HEIGHT * 3 / 4, CFG.GAME_WIDTH, CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT * 3 / 4 - (CFG.BUTTON_HEIGHT + CFG.PADDING), menuElements
      );
      this.updateLanguage();
   }

   @Override
   public void updateLanguage() {
      this.getMenuElement(0).setText(CFG.langManager.get("AddUnion"));
   }

   @Override
   public void updateMenuElements_IsInView() {
      super.updateMenuElements_IsInView();
      int tempRandomButton = 1;

      for (int i = 1; i < this.getMenuElementsSize(); i += 2) {
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
         } catch (GdxRuntimeException var3) {
            this.lFlags
               .add(
                  new Image(
                     new Texture(Gdx.files.internal("game/flags/" + CFG.ideologiesManager.getRealTag(this.lCivsTags.get(nCivTagID)) + ".png")),
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
      int tempRandomButton = 1;

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
                        - this.lFlags.get(this.getFlagID((i - tempRandomButton) / 2)).getHeight()
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
            CFG.unionsManager.createUnion_Data = new Union_GameData();
            CFG.MANAGE_DIPLOMACY_CUSTOMIZE_ALLIANCE_ID = -1;
            CFG.menuManager.setViewID(Menu.eEDITOR_UNIONS_EDIT);
            return;
         default:
            if (--iID % 2 == 0) {
               CFG.MANAGE_DIPLOMACY_CUSTOMIZE_ALLIANCE_ID = iID / 2;
               CFG.unionsManager.createUnion_Data = CFG.unionsManager.unions.lUnions.get(iID / 2);
               CFG.unionsManager.saveUnions();
               CFG.menuManager.setViewID(Menu.eEDITOR_UNIONS_EDIT);
            } else {
               CFG.unionsManager.unions.lUnions.remove(iID / 2);
               CFG.unionsManager.saveUnions();
               CFG.menuManager.setViewID(Menu.eEDITOR_UNIONS);
            }
      }
   }

   @Override
   public void onBackPressed() {
      CFG.menuManager.setViewID(Menu.eEDITOR);
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
