package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.GdxRuntimeException;
import java.util.ArrayList;
import java.util.List;

public class Menu_SelectLanguage extends SliderMenu {
   public List<Image> lFlags = null;
   public String sText = null;
   public int iTextWidth = 0;

   public Menu_SelectLanguage() {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      this.lFlags = new ArrayList<>();

      try {
         FileHandle tempFileT = Gdx.files.internal("game/languages/Age_of_Civilizations");
         String tempT = tempFileT.readString();
         String[] tagsSPLITED = tempT.split(";");
         int tPosX = (CFG.GAME_WIDTH - ImageManager.getImage(Images.top_flag_frame).getWidth() * (tagsSPLITED.length / 2) - CFG.PADDING * tagsSPLITED.length)
            / 2;
         int tPosY = CFG.GAME_HEIGHT * 3 / 5 - ImageManager.getImage(Images.top_flag_frame).getHeight() / 2;
         int tX = tPosX + CFG.PADDING;

         for (int i = 0; i < tagsSPLITED.length; i += 2) {
            menuElements.add(new Button_Flag_JustFrame(tX, tPosY, true) {
               @Override
               public int getSFX() {
                  return SoundsManager.SOUND_CLICK3;
               }
            });
            tX += ImageManager.getImage(Images.top_flag_frame).getWidth() + CFG.PADDING * 2;

            try {
               this.lFlags.add(new Image(new Texture(Gdx.files.internal("game/flagsH/" + tagsSPLITED[i + 1] + ".png")), Texture.TextureFilter.Nearest));
            } catch (GdxRuntimeException var13) {
               try {
                  this.lFlags.add(new Image(new Texture(Gdx.files.internal("game/flags/" + tagsSPLITED[i + 1] + ".png")), Texture.TextureFilter.Nearest));
               } catch (GdxRuntimeException var11) {
                  this.lFlags.add(new Image(new Texture(Gdx.files.internal("game/flags/ran.png")), Texture.TextureFilter.Nearest));
               } catch (OutOfMemoryError var12) {
               }
            } catch (OutOfMemoryError var14) {
            }
         }

         menuElements.add(new Button_Transparent(tX, tPosY, CFG.BUTTON_WIDTH / 2, ImageManager.getImage(Images.top_flag_frame).getHeight(), true));
         tX = CFG.BUTTON_WIDTH / 2;
         if (menuElements.get(0).getPosX() <= 0) {
            for (int var17 = 0; var17 < menuElements.size(); var17++) {
               menuElements.get(var17).setPosX(tX);
               tX += ImageManager.getImage(Images.top_flag_frame).getWidth() + CFG.PADDING * 2;
            }
         }
      } catch (IndexOutOfBoundsException var15) {
         menuElements.add(
            new Button_Flag_JustFrame(
               CFG.GAME_WIDTH / 2 - ImageManager.getImage(Images.top_flag_frame).getWidth() / 2,
               CFG.GAME_HEIGHT / 2 - ImageManager.getImage(Images.top_flag_frame).getHeight() / 2,
               true
            )
         );
      } catch (GdxRuntimeException var16) {
         menuElements.add(
            new Button_Flag_JustFrame(
               CFG.GAME_WIDTH / 2 - ImageManager.getImage(Images.top_flag_frame).getWidth() / 2,
               CFG.GAME_HEIGHT / 2 - ImageManager.getImage(Images.top_flag_frame).getHeight() / 2,
               true
            )
         );
      }

      this.updateLanguage();
      this.initMenu(null, 0, 0, CFG.GAME_WIDTH, CFG.GAME_HEIGHT, menuElements);
      this.updateLanguage();
   }

   @Override
   public void updateLanguage() {
      this.sText = CFG.langManager.get("SelectLanguage");
      CFG.glyphLayout.setText(CFG.fontMain, this.sText);
      this.iTextWidth = (int)CFG.glyphLayout.width;
   }

   @Override
   public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.45F));
      ImageManager.getImage(Images.gradient)
         .draw(oSB, iTranslateX, -ImageManager.getImage(Images.gradient).getHeight() + iTranslateY, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT / 2);
      ImageManager.getImage(Images.gradient)
         .draw(
            oSB,
            iTranslateX,
            CFG.GAME_HEIGHT - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY,
            CFG.GAME_WIDTH,
            CFG.BUTTON_HEIGHT / 2,
            false,
            true
         );
      oSB.setColor(CFG.COLOR_GRADIENT_DARK_BLUE);
      ImageManager.getImage(Images.line_32_off1)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getMenuElement(0).getPosY()
               + this.getMenuElement(0).getHeight() / 2
               - ImageManager.getImage(Images.top_flag_frame).getHeight()
               - ImageManager.getImage(Images.line_32_off1).getHeight()
               + iTranslateY,
            CFG.GAME_WIDTH,
            ImageManager.getImage(Images.top_flag_frame).getHeight() * 2
         );
      oSB.setColor(CFG.COLOR_FLAG_FRAME);
      ImageManager.getImage(Images.line_32_off1)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getMenuElement(0).getPosY()
               + this.getMenuElement(0).getHeight() / 2
               - ImageManager.getImage(Images.top_flag_frame).getHeight()
               - ImageManager.getImage(Images.line_32_off1).getHeight()
               + iTranslateY,
            CFG.GAME_WIDTH,
            1
         );
      ImageManager.getImage(Images.line_32_off1)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getMenuElement(0).getPosY()
               + this.getMenuElement(0).getHeight() / 2
               + ImageManager.getImage(Images.top_flag_frame).getHeight()
               - ImageManager.getImage(Images.line_32_off1).getHeight()
               + iTranslateY,
            CFG.GAME_WIDTH,
            1
         );
      CFG.drawTextWithShadow(
         oSB,
         this.sText,
         this.getWidth() / 2 - this.iTextWidth / 2 + iTranslateX,
         this.getMenuElement(0).getPosY()
            + this.getMenuElement(0).getHeight() / 2
            - ImageManager.getImage(Images.top_flag_frame).getHeight()
            - CFG.PADDING
            - CFG.PADDING / 2
            - CFG.TEXT_HEIGHT
            + iTranslateY,
         CFG.COLOR_FLAG_FRAME
      );
      oSB.setColor(Color.WHITE);
      ImageManager.getImage(Images.gameLogo)
         .draw(oSB, CFG.PADDING + iTranslateX, CFG.GAME_HEIGHT - ImageManager.getImage(Images.gameLogo).getHeight() - CFG.PADDING);

      try {
         for (int i = 0; i < this.lFlags.size(); i++) {
            if (this.getMenuElement(i).getIsInView()) {
               this.lFlags
                  .get(i)
                  .draw(
                     oSB,
                     this.getMenuElement(i).getPosX() + this.getMenuPosX() + iTranslateX,
                     this.getMenuElement(i).getPosY() - this.lFlags.get(i).getHeight() + this.getMenuPosY() + iTranslateY,
                     ImageManager.getImage(Images.top_flag_frame).getWidth(),
                     ImageManager.getImage(Images.top_flag_frame).getHeight()
                  );
            }
         }
      } catch (NullPointerException var7) {
         oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.35F));

         for (int ix = 0; ix < this.getMenuElementsSize(); ix++) {
            ImageManager.getImage(Images.pix255_255_255)
               .draw(
                  oSB,
                  this.getMenuElement(ix).getPosX() + this.getMenuPosX() + iTranslateX,
                  this.getMenuElement(ix).getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + this.getMenuPosY() + iTranslateY,
                  ImageManager.getImage(Images.top_flag_frame).getWidth(),
                  ImageManager.getImage(Images.top_flag_frame).getHeight()
               );
         }

         oSB.setColor(Color.WHITE);
      }

      super.draw(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
   }

   @Override
   public final void actionElement(int iID) {
      if (iID != this.getMenuElementsSize() - 1) {
         try {
            FileHandle tempFileT = Gdx.files.internal("game/languages/Age_of_Civilizations");
            String tempT = tempFileT.readString();
            String[] tagsSPLITED = tempT.split(";");
            CFG.langManager.dispose();
            CFG.langManager = null;
            CFG.langManager = new LanguageManager(tagsSPLITED[iID * 2]);
            CFG.settingsManager.LANGUAGE_TAG = tagsSPLITED[iID * 2];
            CFG.loadFontMain();
            CFG.loadFontBorder();
            CFG.RANDOM_CIVILIZATION = CFG.langManager.get("RandomCivilization");
            CFG.saveSettings();
            if (CFG.VIEW_SHOW_VALUES) {
               CFG.menuManager.updateLanguage();
               CFG.toast.setInView(CFG.langManager.get("LANGUAGENAME"), CFG.COLOR_TEXT_NUM_OF_PROVINCES);
            }
         } catch (IndexOutOfBoundsException var5) {
            CFG.exceptionStack(var5);
         }

         if (CFG.backToMenu == Menu.eMAINMENU) {
            CFG.setDialogType(Dialog.CONFIRM_LANGUAGE);
         } else {
            this.onBackPressed();
            Menu_Editor.reloadScenario();
         }
      }
   }

   @Override
   public final void onBackPressed() {
      for (int i = 0; i < CFG.game.getCivsSize(); i++) {
         CFG.game.getCiv(i).setCivName(CFG.langManager.getCiv(CFG.game.getCiv(i).getCivTag()));
      }

      try {
         for (int var4 = 0; var4 < this.lFlags.size(); var4++) {
            this.lFlags.get(var4).getTexture().dispose();
         }

         this.lFlags.clear();
         this.lFlags = null;
      } catch (NullPointerException var3) {
         CFG.exceptionStack(var3);
      }

      CFG.map.getMapCoordinates().centerToRandomMapPosition();
      CFG.menuManager.setViewID(CFG.backToMenu);
      CFG.menuManager.setBackAnimation(true);
      CFG.map.getMapBG().updateWorldMap_Shaders();
   }
}
