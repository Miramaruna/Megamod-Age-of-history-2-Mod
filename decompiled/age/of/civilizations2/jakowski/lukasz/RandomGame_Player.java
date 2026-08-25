package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.GdxRuntimeException;

public class RandomGame_Player {
   public String sTag = null;
   public int iCapitalProvinceID;
   public Image flagOfCivilization = null;

   public RandomGame_Player(String sTag, int iCapitalProvinceID) {
      this.sTag = sTag;
      this.iCapitalProvinceID = iCapitalProvinceID;
   }

   public final String getTag() {
      return this.sTag;
   }

   public final void setTag(String sTag) {
      this.sTag = sTag;
      if (sTag == null) {
         this.disposePlayersFlag();
      } else {
         this.loadPlayersFlag();
      }
   }

   public final int getCapitalProvinceID() {
      return this.iCapitalProvinceID;
   }

   public final void setCapitalProvinceID(int iCapitalProvinceID) {
      this.iCapitalProvinceID = iCapitalProvinceID;
   }

   public final void loadPlayersFlag() {
      this.disposePlayersFlag();

      try {
         try {
            this.flagOfCivilization = new Image(new Texture(Gdx.files.internal("game/flags/" + this.sTag + ".png")), Texture.TextureFilter.Nearest);
         } catch (GdxRuntimeException var6) {
            try {
               this.flagOfCivilization = new Image(
                  new Texture(Gdx.files.internal("game/flags/" + CFG.ideologiesManager.getRealTag(this.sTag) + ".png")), Texture.TextureFilter.Nearest
               );
            } catch (GdxRuntimeException var5) {
               if (CFG.isAndroid()) {
                  try {
                     this.flagOfCivilization = new Image(
                        new Texture(
                           Gdx.files
                              .local(
                                 "game/civilizations_editor/"
                                    + CFG.ideologiesManager.getRealTag(this.sTag)
                                    + "/"
                                    + CFG.ideologiesManager.getRealTag(this.sTag)
                                    + "_FL.png"
                              )
                        ),
                        Texture.TextureFilter.Nearest
                     );
                  } catch (GdxRuntimeException var4) {
                     this.flagOfCivilization = new Image(
                        new Texture(
                           Gdx.files
                              .internal(
                                 "game/civilizations_editor/"
                                    + CFG.ideologiesManager.getRealTag(this.sTag)
                                    + "/"
                                    + CFG.ideologiesManager.getRealTag(this.sTag)
                                    + "_FL.png"
                              )
                        ),
                        Texture.TextureFilter.Nearest
                     );
                  }
               } else {
                  this.flagOfCivilization = new Image(
                     new Texture(
                        Gdx.files
                           .internal(
                              "game/civilizations_editor/"
                                 + CFG.ideologiesManager.getRealTag(this.sTag)
                                 + "/"
                                 + CFG.ideologiesManager.getRealTag(this.sTag)
                                 + "_FL.png"
                           )
                     ),
                     Texture.TextureFilter.Nearest
                  );
               }
            }
         }
      } catch (GdxRuntimeException var7) {
         this.disposePlayersFlag();
      }
   }

   public final void disposePlayersFlag() {
      if (this.flagOfCivilization != null) {
         this.flagOfCivilization.getTexture().dispose();
         this.flagOfCivilization = null;
      }
   }

   public final Image getFlag() {
      return this.flagOfCivilization == null ? ImageManager.getImage(Images.randomCivilizationFlag) : this.flagOfCivilization;
   }
}
