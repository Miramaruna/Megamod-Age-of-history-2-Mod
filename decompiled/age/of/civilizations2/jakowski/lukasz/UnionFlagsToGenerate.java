package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.utils.ScissorStack;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.ScreenUtils;
import java.util.ArrayList;
import java.util.List;

public class UnionFlagsToGenerate {
   public int iID = -1;
   public List<String> lTags = new ArrayList<>();
   public UnionFlagsToGenerate_TypesOfAction typeOfAction = UnionFlagsToGenerate_TypesOfAction.ACTIVE_CIV_INFO;

   UnionFlagsToGenerate() {
   }

   public final boolean generateFlag(SpriteBatch oSB) {
      try {
         ArrayList<Image> tempFlags = new ArrayList<>();

         for (int i = 0; i < this.lTags.size(); i++) {
            try {
               try {
                  tempFlags.add(new Image(new Texture(Gdx.files.internal("game/flagsH/" + this.lTags.get(i) + ".png")), Texture.TextureFilter.Linear));
               } catch (GdxRuntimeException var17) {
                  try {
                     tempFlags.add(
                        new Image(
                           new Texture(Gdx.files.internal("game/flagsH/" + CFG.ideologiesManager.getRealTag(this.lTags.get(i)) + ".png")),
                           Texture.TextureFilter.Linear
                        )
                     );
                  } catch (GdxRuntimeException var16) {
                     if (CFG.isAndroid()) {
                        try {
                           tempFlags.add(
                              new Image(
                                 new Texture(
                                    Gdx.files
                                       .local(
                                          "game/civilizations_editor/"
                                             + CFG.ideologiesManager.getRealTag(this.lTags.get(i))
                                             + "/"
                                             + CFG.ideologiesManager.getRealTag(this.lTags.get(i))
                                             + "_FLH.png"
                                       )
                                 ),
                                 Texture.TextureFilter.Linear
                              )
                           );
                        } catch (GdxRuntimeException var12) {
                           tempFlags.add(
                              new Image(
                                 new Texture(
                                    Gdx.files
                                       .internal(
                                          "game/civilizations_editor/"
                                             + CFG.ideologiesManager.getRealTag(this.lTags.get(i))
                                             + "/"
                                             + CFG.ideologiesManager.getRealTag(this.lTags.get(i))
                                             + "_FLH.png"
                                       )
                                 ),
                                 Texture.TextureFilter.Linear
                              )
                           );
                        }
                     } else {
                        tempFlags.add(
                           new Image(
                              new Texture(
                                 Gdx.files
                                    .internal(
                                       "game/civilizations_editor/"
                                          + CFG.ideologiesManager.getRealTag(this.lTags.get(i))
                                          + "/"
                                          + CFG.ideologiesManager.getRealTag(this.lTags.get(i))
                                          + "_FLH.png"
                                    )
                              ),
                              Texture.TextureFilter.Linear
                           )
                        );
                     }
                  }
               }
            } catch (GdxRuntimeException var18) {
               try {
                  try {
                     tempFlags.add(new Image(new Texture(Gdx.files.internal("game/flags/" + this.lTags.get(i) + ".png")), Texture.TextureFilter.Nearest));
                  } catch (GdxRuntimeException var14) {
                     try {
                        tempFlags.add(
                           new Image(
                              new Texture(Gdx.files.internal("game/flags/" + CFG.ideologiesManager.getRealTag(this.lTags.get(i)) + ".png")),
                              Texture.TextureFilter.Nearest
                           )
                        );
                     } catch (GdxRuntimeException var13) {
                        if (CFG.isAndroid()) {
                           try {
                              tempFlags.add(
                                 new Image(
                                    new Texture(
                                       Gdx.files
                                          .local(
                                             "game/civilizations_editor/"
                                                + CFG.ideologiesManager.getRealTag(this.lTags.get(i))
                                                + "/"
                                                + CFG.ideologiesManager.getRealTag(this.lTags.get(i))
                                                + "_FL.png"
                                          )
                                    ),
                                    Texture.TextureFilter.Nearest
                                 )
                              );
                           } catch (GdxRuntimeException var11) {
                              tempFlags.add(
                                 new Image(
                                    new Texture(
                                       Gdx.files
                                          .internal(
                                             "game/civilizations_editor/"
                                                + CFG.ideologiesManager.getRealTag(this.lTags.get(i))
                                                + "/"
                                                + CFG.ideologiesManager.getRealTag(this.lTags.get(i))
                                                + "_FL.png"
                                          )
                                    ),
                                    Texture.TextureFilter.Nearest
                                 )
                              );
                           }
                        } else {
                           tempFlags.add(
                              new Image(
                                 new Texture(
                                    Gdx.files
                                       .internal(
                                          "game/civilizations_editor/"
                                             + CFG.ideologiesManager.getRealTag(this.lTags.get(i))
                                             + "/"
                                             + CFG.ideologiesManager.getRealTag(this.lTags.get(i))
                                             + "_FL.png"
                                       )
                                 ),
                                 Texture.TextureFilter.Nearest
                              )
                           );
                        }
                     }
                  }
               } catch (GdxRuntimeException var15) {
                  tempFlags.add(new Image(new Texture(Gdx.files.internal("game/flags/ran.png")), Texture.TextureFilter.Nearest));
               }
            } catch (OutOfMemoryError var19) {
            }
         }

         if (this.typeOfAction == UnionFlagsToGenerate_TypesOfAction.CIV_ID_SMALL) {
            oSB.setColor(Color.BLACK);
            ImageManager.getImage(Images.pix255_255_255).draw(oSB, 0, ImageManager.getImage(Images.pix255_255_255).getHeight(), 27, 18);
            oSB.setColor(Color.WHITE);

            for (int var21 = 0; var21 < tempFlags.size() && var21 < 4; var21++) {
               oSB.setShader(AoCGame.shaderAlpha);
               CFG.unionFlagsToGenerate_Manager.lFlags_Small.get(var21).getTexture().bind(2);
               tempFlags.get(var21).getTexture().bind(1);
               Gdx.gl.glActiveTexture(33984);
               CFG.unionFlagsToGenerate_Manager.lFlags_Small.get(var21).draw(oSB, 0, 0, false, true);
               oSB.setShader(AoCGame.defaultShader);
            }

            Image tGenerated = new Image(new Texture(ScreenUtils.getFrameBufferPixmap(0, CFG.GAME_HEIGHT - 18, 27, 18)));
            tGenerated.draw(oSB, 0, 0, false, true);

            try {
               oSB.flush();
               ScissorStack.popScissors();
            } catch (IllegalStateException var10) {
            }

            oSB.end();
            oSB.begin();
            oSB.setColor(Color.WHITE);
            tGenerated.getTexture().dispose();
            Image var25 = null;
            var25 = new Image(new Texture(ScreenUtils.getFrameBufferPixmap(0, CFG.GAME_HEIGHT - 18, 27, 18)));
            CFG.game.getCiv(this.iID).setFlag((Image)var25);
         } else if (this.typeOfAction == UnionFlagsToGenerate_TypesOfAction.ACTIVE_CIV_INFO) {
            oSB.setColor(Color.BLACK);
            ImageManager.getImage(Images.pix255_255_255).draw(oSB, 0, ImageManager.getImage(Images.pix255_255_255).getHeight(), 27, 18);
            oSB.setColor(Color.WHITE);

            for (int var22 = 0; var22 < tempFlags.size() && var22 < 4; var22++) {
               oSB.setShader(AoCGame.shaderAlpha);
               CFG.unionFlagsToGenerate_Manager.lFlags_H.get(var22).getTexture().bind(2);
               tempFlags.get(var22).getTexture().bind(1);
               Gdx.gl.glActiveTexture(33984);
               CFG.unionFlagsToGenerate_Manager.lFlags_H.get(var22).draw(oSB, 0, 0, false, true);
               oSB.setShader(AoCGame.defaultShader);
            }

            Image tGenerated = new Image(new Texture(ScreenUtils.getFrameBufferPixmap(0, CFG.GAME_HEIGHT - 44, 68, 44)));
            tGenerated.draw(oSB, 0, 0, false, true);

            try {
               oSB.flush();
               ScissorStack.popScissors();
            } catch (IllegalStateException var9) {
            }

            oSB.end();
            oSB.begin();
            oSB.setColor(Color.WHITE);
            tGenerated.getTexture().dispose();
            Image var28 = null;
            var28 = new Image(new Texture(ScreenUtils.getFrameBufferPixmap(0, CFG.GAME_HEIGHT - 44, 68, 44)));
            CFG.setActiveCivInfoFlag((Image)var28);
         } else if (this.typeOfAction == UnionFlagsToGenerate_TypesOfAction.PLAYER_ID) {
            oSB.setColor(Color.BLACK);
            ImageManager.getImage(Images.pix255_255_255).draw(oSB, 0, ImageManager.getImage(Images.pix255_255_255).getHeight(), 27, 18);
            oSB.setColor(Color.WHITE);

            for (int var23 = 0; var23 < tempFlags.size() && var23 < 4; var23++) {
               oSB.setShader(AoCGame.shaderAlpha);
               CFG.unionFlagsToGenerate_Manager.lFlags_H.get(var23).getTexture().bind(2);
               tempFlags.get(var23).getTexture().bind(1);
               Gdx.gl.glActiveTexture(33984);
               CFG.unionFlagsToGenerate_Manager.lFlags_H.get(var23).draw(oSB, 0, 0, false, true);
               oSB.setShader(AoCGame.defaultShader);
            }

            Image tGenerated = new Image(new Texture(ScreenUtils.getFrameBufferPixmap(0, CFG.GAME_HEIGHT - 44, 68, 44)));
            tGenerated.draw(oSB, 0, 0, false, true);

            try {
               oSB.flush();
               ScissorStack.popScissors();
            } catch (IllegalStateException var8) {
            }

            oSB.end();
            oSB.begin();
            oSB.setColor(Color.WHITE);
            tGenerated.getTexture().dispose();
            Image var31 = null;
            var31 = new Image(new Texture(ScreenUtils.getFrameBufferPixmap(0, CFG.GAME_HEIGHT - 44, 68, 44)));

            for (int i2 = 0; i2 < CFG.game.getPlayersSize(); i2++) {
               if (CFG.game.getPlayer(i2).getCivID() == this.iID) {
                  CFG.game.getPlayer(i2).loadPlayersFlag((Image)var31);
                  break;
               }
            }
         }

         for (int i3 = 0; i3 < tempFlags.size(); i3++) {
            tempFlags.get(i3).getTexture().dispose();
         }

         tempFlags.clear();
         ArrayList<Image> var24 = null;
         return true;
      } catch (RuntimeException var20) {
         return false;
      }
   }
}
