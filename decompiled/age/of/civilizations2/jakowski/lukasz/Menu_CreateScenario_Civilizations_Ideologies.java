package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.utils.ScissorStack;
import com.badlogic.gdx.utils.GdxRuntimeException;
import java.util.ArrayList;
import java.util.List;

public class Menu_CreateScenario_Civilizations_Ideologies extends SliderMenu {
   public String sCivsTag;
   public List<Image> lFlags = new ArrayList<>();
   public List<Integer> lLoadedFlags_TagsIDs = new ArrayList<>();

   public Menu_CreateScenario_Civilizations_Ideologies() {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      if (CFG.game.getActiveProvinceID() >= 0
         && CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID() > 0
         && CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).getCapitalProvinceID() == CFG.game.getActiveProvinceID()
         && !CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).getCivTag().equals("ran")) {
         try {
            this.sCivsTag = CFG.ideologiesManager.getRealTag(CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).getCivTag());

            for (int i = 0; i < CFG.ideologiesManager.getIdeologiesSize(); i++) {
               menuElements.add(
                  new Button_Game_Ideology(
                     "" + CFG.langManager.getCiv(this.sCivsTag + CFG.ideologiesManager.getIdeology(i).getExtraTag()),
                     i,
                     -1,
                     CFG.GAME_WIDTH + CFG.PADDING,
                     CFG.PADDING,
                     CFG.BUTTON_WIDTH,
                     CFG.game.isCivTagAvailable(this.sCivsTag + CFG.ideologiesManager.getIdeology(i).getExtraTag())
                  )
               );
            }
         } catch (GdxRuntimeException var3) {
         }
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
         int tempTagID = this.getIsLoaded(i);
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

   public final int getIsLoaded(int nCivTag) {
      for (int i = 0; i < this.lLoadedFlags_TagsIDs.size(); i++) {
         if (this.lLoadedFlags_TagsIDs.get(i) == nCivTag) {
            return i;
         }
      }

      return -1;
   }

   public final void loadFlag(int nCivTagID) {
      try {
         try {
            this.lFlags
               .add(
                  new Image(
                     new Texture(Gdx.files.internal("game/flags/" + this.sCivsTag + CFG.ideologiesManager.getIdeology(nCivTagID).getExtraTag() + ".png")),
                     Texture.TextureFilter.Nearest
                  )
               );
         } catch (GdxRuntimeException var8) {
            boolean isDone = false;
            if (CFG.ideologiesManager.getIdeology(nCivTagID).REVOLUTIONARY) {
               this.lFlags
                  .add(new Image(new Texture(Gdx.files.internal("game/flags/rb" + this.sCivsTag.charAt(0) % 6 + ".png")), Texture.TextureFilter.Nearest));
               isDone = true;
            }

            if (!isDone) {
               try {
                  this.lFlags.add(new Image(new Texture(Gdx.files.internal("game/flags/" + this.sCivsTag + ".png")), Texture.TextureFilter.Nearest));
               } catch (GdxRuntimeException var7) {
                  if (CFG.isAndroid()) {
                     try {
                        this.lFlags
                           .add(
                              new Image(
                                 new Texture(Gdx.files.local("game/civilizations_editor/" + this.sCivsTag + "/" + this.sCivsTag + "_FL.png")),
                                 Texture.TextureFilter.Linear
                              )
                           );
                     } catch (GdxRuntimeException var6) {
                        this.lFlags
                           .add(
                              new Image(
                                 new Texture(Gdx.files.internal("game/civilizations_editor/" + this.sCivsTag + "/" + this.sCivsTag + "_FL.png")),
                                 Texture.TextureFilter.Linear
                              )
                           );
                     }
                  } else {
                     this.lFlags
                        .add(
                           new Image(
                              new Texture(Gdx.files.internal("game/civilizations_editor/" + this.sCivsTag + "/" + this.sCivsTag + "_FL.png")),
                              Texture.TextureFilter.Linear
                           )
                        );
                  }
               }
            }
         }
      } catch (GdxRuntimeException var9) {
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
                     + (this.getMenuElement(i).getWidth() - (this.getMenuElement(i).getTextPos() + CFG.PADDING + CFG.CIV_FLAG_WIDTH)) / 2
                     + this.getMenuPosX()
                     + iTranslateX,
                  this.getMenuElement(i).getPosY()
                     - this.lFlags.get(this.getFlagID(i)).getHeight()
                     + this.getMenuPosY()
                     + this.getMenuElement(i).getHeight() / 2
                     - CFG.PADDING / 2
                     - this.getMenuElement(i).getTextHeight() / 2
                     - CFG.CIV_FLAG_HEIGHT / 2
                     + var7,
                  CFG.CIV_FLAG_WIDTH,
                  CFG.CIV_FLAG_HEIGHT
               );
            ImageManager.getImage(Images.flag_rect)
               .draw(
                  oSB,
                  this.getMenuElement(i).getPosX()
                     + (this.getMenuElement(i).getWidth() - (this.getMenuElement(i).getTextPos() + CFG.PADDING + CFG.CIV_FLAG_WIDTH)) / 2
                     + this.getMenuPosX()
                     + iTranslateX,
                  this.getMenuElement(i).getPosY()
                     + this.getMenuPosY()
                     + this.getMenuElement(i).getHeight() / 2
                     - CFG.PADDING / 2
                     - this.getMenuElement(i).getTextHeight() / 2
                     - CFG.CIV_FLAG_HEIGHT / 2
                     + var7
               );
         }
      }

      super.endClip(oSB, iTranslateX, var7, sliderMenuIsActive);
   }

   @Override
   public final void actionElement(int iID) {
      if (CFG.game.getActiveProvinceID() >= 0 && CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID() > 0) {
         CFG.game
            .updateCivilizationIdeology(
               CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID(), this.sCivsTag + CFG.ideologiesManager.getIdeology(iID).getExtraTag()
            );
      }

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
            this.sCivsTag = null;
         } catch (NullPointerException var3) {
         }
      }

      super.setVisible(visible);
   }
}
