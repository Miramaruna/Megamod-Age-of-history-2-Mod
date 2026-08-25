package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.GdxRuntimeException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Menu_GameEditor_Lines extends SliderMenu {
   public List<Image> lImages = new ArrayList<>();
   public List<Boolean> lFlipX = new ArrayList<>();

   public Menu_GameEditor_Lines() {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      menuElements.add(new Button_Menu_LR_Line(null, -1, 0, CFG.PADDING, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT, true));
      FileHandle tempFileT = Gdx.files.internal("game/lines/Age_of_Civilizations");
      String tempT = tempFileT.readString();
      String[] tagsSPLITED = tempT.split(";");

      for (int i = 0; i < tagsSPLITED.length; i++) {
         FileHandle tGameData = Gdx.files.internal("game/lines/" + tagsSPLITED[i]);

         try {
            CFG.editorLine_GameData = (Line_GameData)CFG.deserialize(tGameData.readBytes());
            if (CFG.editorLine_GameData.getRapeatImage()) {
               this.lImages
                  .add(
                     new Image(
                        new Texture(Gdx.files.internal("game/lines/" + CFG.editorLine_GameData.getImageName() + ".png"), Pixmap.Format.RGBA8888, true),
                        Texture.TextureFilter.Linear,
                        Texture.TextureWrap.Repeat
                     )
                  );
            } else {
               this.lImages
                  .add(
                     new Image(
                        new Texture(Gdx.files.internal("game/lines/" + CFG.editorLine_GameData.getImageName() + ".png"), Pixmap.Format.RGBA8888, true),
                        Texture.TextureFilter.Linear,
                        Texture.TextureWrap.ClampToEdge
                     )
                  );
            }

            this.lFlipX.add(CFG.editorLine_GameData.getFlipX());
            menuElements.add(
               new Button_Menu_LR(
                  "", (int)(50.0F * CFG.GUI_SCALE), 0, CFG.BUTTON_HEIGHT * (i + 1) + CFG.PADDING * (i + 2), CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT, true
               )
            );
         } catch (ClassNotFoundException var8) {
         } catch (IOException var9) {
         } catch (GdxRuntimeException var10) {
         }
      }

      this.initMenu(
         null, 0, CFG.BUTTON_HEIGHT * 3 / 4, CFG.GAME_WIDTH, CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT * 3 / 4 - CFG.BUTTON_HEIGHT - CFG.PADDING, menuElements
      );
      this.updateLanguage();
   }

   @Override
   public void updateLanguage() {
      this.getMenuElement(0).setText(CFG.langManager.get("AddNewStyle"));
   }

   @Override
   public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      super.beginClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
      super.drawMenu(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);

      try {
         for (int i = 0; i < this.getMenuElementsSize() - 1; i++) {
            this.lImages
               .get(i)
               .draw2(
                  oSB,
                  this.getPosX()
                     + this.getMenuElement(i + 1).getPosX()
                     + this.getMenuElement(i + 1).getWidth() / 2
                     - this.getMenuElement(i + 1).getWidth() / 4
                     + iTranslateX,
                  this.getMenuPosY()
                     + this.getMenuElement(i + 1).getPosY()
                     + this.getMenuElement(i + 1).getHeight() / 2
                     - this.lImages.get(i).getHeight() / 2
                     - this.lImages.get(i).getHeight(),
                  this.getMenuElement(i + 1).getWidth() / 2,
                  this.lImages.get(i).getHeight(),
                  this.lFlipX.get(i),
                  false
               );
         }
      } catch (NullPointerException var6) {
      } catch (IndexOutOfBoundsException var7) {
      }

      super.endClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
   }

   @Override
   public final void actionElement(int iID) {
      switch (iID) {
         case 0:
            CFG.editorLine_GameData = new Line_GameData();
            CFG.editorLine_GameData.setImageName("");
            CFG.menuManager.setViewID(Menu.eGAME_EDITOR_LINES_EDIT);
            this.onBackPressed();
            break;
         default:
            FileHandle tempFileT = Gdx.files.internal("game/lines/Age_of_Civilizations");
            String tempT = tempFileT.readString();
            String[] tagsSPLITED = tempT.split(";");
            FileHandle tGameData = Gdx.files.internal("game/lines/" + tagsSPLITED[iID - 1]);

            try {
               CFG.editorLine_GameData = (Line_GameData)CFG.deserialize(tGameData.readBytes());
               CFG.menuManager.setViewID(Menu.eGAME_EDITOR_LINES_EDIT);
               this.onBackPressed();
            } catch (ClassNotFoundException var7) {
            } catch (IOException var8) {
            } catch (GdxRuntimeException var9) {
            }
      }
   }

   @Override
   public void onBackPressed() {
      for (int i = 0; i < this.lImages.size(); i++) {
         this.lImages.get(i).getTexture().dispose();
         this.lImages.set(i, null);
      }

      this.lImages.clear();
      this.lImages = null;
      this.lFlipX.clear();
   }
}
