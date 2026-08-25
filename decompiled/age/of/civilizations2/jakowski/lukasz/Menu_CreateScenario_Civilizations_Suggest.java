package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.utils.ScissorStack;
import com.badlogic.gdx.utils.GdxRuntimeException;
import java.util.ArrayList;
import java.util.List;

public class Menu_CreateScenario_Civilizations_Suggest extends SliderMenu {
   public List<String> lCivsTags;
   public List<Image> lFlags = new ArrayList<>();
   public List<Integer> lLoadedFlags_TagsIDs = new ArrayList<>();

   public Menu_CreateScenario_Civilizations_Suggest() {
      ArrayList<MenuElement> menuElements = new ArrayList<>();

      try {
         FileHandle file = Gdx.files.internal("map/" + CFG.map.getFile_ActiveMap_Path() + "suggested_owners/" + CFG.game.getActiveProvinceID());
         String sOwners = file.readString();
         String[] sRes = sOwners.split(";");
         this.lCivsTags = new ArrayList<>();

         for (int i = 0; i < sRes.length; i += 2) {
            boolean bContinue = false;

            for (int j = 0; j < CFG.game.getCivsSize(); j++) {
               if (CFG.game.getCiv(j).getCivTag().equals(sRes[i])) {
                  bContinue = true;
                  break;
               }
            }

            if (!bContinue) {
               menuElements.add(new Button_Game("" + CFG.langManager.getCiv(sRes[i]), -1, CFG.GAME_WIDTH + CFG.PADDING, CFG.PADDING, CFG.BUTTON_WIDTH, true) {
                  @Override
                  public void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                     super.drawText(oSB, iTranslateX + CFG.PADDING + CFG.CIV_FLAG_WIDTH, iTranslateY, isActive);
                  }

                  @Override
                  public int getTextWidth() {
                     return super.getTextWidth() + CFG.PADDING + CFG.CIV_FLAG_WIDTH;
                  }

                  @Override
                  public void buildElementHover() {
                     ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
                     ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("AddCivilization") + ".", CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                     this.menuElementHover = new MenuElement_Hover_v2(nElements);
                  }
               });
               this.lCivsTags.add(sRes[i]);
            }
         }
      } catch (GdxRuntimeException var8) {
      }

      this.initMenu(null, 0, CFG.BUTTON_HEIGHT + CFG.PADDING * 2, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT + CFG.PADDING * 2 + 1, menuElements);
      this.updatedButtonsWidth(CFG.PADDING, CFG.BUTTON_WIDTH);
      this.updateMenuElements_IsInView();
      CFG.fMOVE_MENU_PERCENTAGE = 5.0F;
      CFG.lMOVE_MENU_TIME = System.currentTimeMillis();
   }

   @Override
   public void updateMenuElements_IsInView() {
      super.updateMenuElements_IsInView_X();

      for (int i = 0; i < this.getMenuElementsSize(); i++) {
         int tempTagID = this.getIsLoaded(this.lCivsTags.get(i));
         if (this.getMenuElement(i).getIsInView()) {
            if (tempTagID < 0) {
               this.loadFlag(i);
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

   public final void loadFlag(int nCivTagID) {
      try {
         try {
            this.lFlags.add(new Image(new Texture(Gdx.files.internal("game/flags/" + this.lCivsTags.get(nCivTagID) + ".png")), Texture.TextureFilter.Nearest));
         } catch (GdxRuntimeException var5) {
            try {
               this.lFlags
                  .add(
                     new Image(
                        new Texture(Gdx.files.internal("game/flags/" + CFG.ideologiesManager.getRealTag(this.lCivsTags.get(nCivTagID)) + ".png")),
                        Texture.TextureFilter.Nearest
                     )
                  );
            } catch (GdxRuntimeException var4) {
               if (CFG.isAndroid()) {
                  this.lFlags
                     .add(
                        new Image(
                           new Texture(
                              Gdx.files.local("game/civilizations_editor/" + this.lCivsTags.get(nCivTagID) + "/" + this.lCivsTags.get(nCivTagID) + "_FL.png")
                           ),
                           Texture.TextureFilter.Linear
                        )
                     );
               } else {
                  this.lFlags
                     .add(
                        new Image(
                           new Texture(
                              Gdx.files
                                 .internal("game/civilizations_editor/" + this.lCivsTags.get(nCivTagID) + "/" + this.lCivsTags.get(nCivTagID) + "_FL.png")
                           ),
                           Texture.TextureFilter.Linear
                        )
                     );
               }
            }
         }
      } catch (GdxRuntimeException var6) {
         this.lFlags.add(new Image(new Texture(Gdx.files.internal("game/flags/ran.png")), Texture.TextureFilter.Nearest));
      }

      this.lLoadedFlags_TagsIDs.add(nCivTagID);
   }

   public final int getFlagID(int nCivTagID) {
      for (int i = 0; i < this.lLoadedFlags_TagsIDs.size(); i++) {
         if (this.lLoadedFlags_TagsIDs.get(i) == nCivTagID) {
            return i;
         }
      }

      return 0;
   }

   @Override
   public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      if ((CFG.fMOVE_MENU_PERCENTAGE = CFG.fMOVE_MENU_PERCENTAGE + (float)(System.currentTimeMillis() - CFG.lMOVE_MENU_TIME) / 225.0F * 95.0F) > 100.0F) {
         CFG.fMOVE_MENU_PERCENTAGE = 100.0F;
      } else {
         CFG.setRender_3(true);
      }

      CFG.lMOVE_MENU_TIME = System.currentTimeMillis();
      Rectangle clipBounds = new Rectangle(this.getPosX() + iTranslateX, CFG.GAME_HEIGHT - this.getPosY() - iTranslateY, this.getWidth(), -this.getHeight());
      oSB.flush();
      ScissorStack.pushScissors(clipBounds);
      if (this.getMenuElementsSize() > 0) {
         CFG.drawEditorButtons_Top_Edge_R(
            oSB,
            iTranslateX,
            this.getMenuPosY() - (int)(this.getHeight() * (100.0F - CFG.fMOVE_MENU_PERCENTAGE) / 100.0F) + iTranslateY,
            this.getMenuElement(this.getMenuElementsSize() - 1).getPosX() + this.getMenuElement(this.getMenuElementsSize() - 1).getWidth() + CFG.PADDING,
            CFG.BUTTON_HEIGHT + CFG.PADDING * 2
         );
      }

      int var7;
      super.drawMenu(oSB, iTranslateX, var7 = iTranslateY + -((int)(this.getHeight() * (100.0F - CFG.fMOVE_MENU_PERCENTAGE) / 100.0F)), sliderMenuIsActive);

      for (int i = 0; i < this.getMenuElementsSize(); i++) {
         if (this.getMenuElement(i).getIsInView()) {
            this.lFlags
               .get(this.getFlagID(i))
               .draw(
                  oSB,
                  this.getMenuElement(i).getPosX()
                     + this.getMenuElement(i).getWidth() / 2
                     - this.getMenuElement(i).getTextWidth() / 2
                     + this.getMenuPosX()
                     + iTranslateX,
                  this.getMenuElement(i).getPosY()
                     + this.getMenuPosY()
                     + this.getMenuElement(i).getHeight() / 2
                     - CFG.CIV_FLAG_HEIGHT / 2
                     - this.lFlags.get(this.getFlagID(i)).getHeight()
                     + var7,
                  CFG.CIV_FLAG_WIDTH,
                  CFG.CIV_FLAG_HEIGHT
               );
            ImageManager.getImage(Images.flag_rect)
               .draw(
                  oSB,
                  this.getMenuElement(i).getPosX()
                     + this.getMenuElement(i).getWidth() / 2
                     - this.getMenuElement(i).getTextWidth() / 2
                     + this.getMenuPosX()
                     + iTranslateX,
                  this.getMenuElement(i).getPosY() + this.getMenuPosY() + this.getMenuElement(i).getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 + var7
               );
         }
      }

      super.endClip(oSB, iTranslateX, var7, sliderMenuIsActive);
   }

   @Override
   public final void actionElement(int iID) {
      if (CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID() > 0) {
         CFG.game.disableDrawCivilizationRegions(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID());
      }

      CFG.game.createScenarioAddCivilization(this.lCivsTags.get(iID), CFG.game.getActiveProvinceID(), true);
      CFG.game.enableDrawCivilizationRegions(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID(), 0);
      this.setVisible(false);
      CFG.updateCreateScenario_Civilizations();
   }

   @Override
   public void setVisible(boolean visible) {
      if (!visible) {
         try {
            for (int i = 0; i < this.lFlags.size(); i++) {
               this.lFlags.get(i).getTexture().dispose();
            }

            this.lFlags.clear();
            this.lLoadedFlags_TagsIDs.clear();
            this.lCivsTags.clear();
         } catch (NullPointerException var3) {
         }
      }

      super.setVisible(visible);
   }
}
