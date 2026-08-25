package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.GdxRuntimeException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Text_AchievementCiv extends Text {
   public static final float FONT_SCALE = 0.8F;
   public Image civFlag;
   public String sTag;
   public boolean gameWon = false;
   public int iSRID = 0;
   public List<Color> lColors = new ArrayList<>();

   public Text_AchievementCiv(String sTag, int iPosX, int iPosY, int iWidth, String nTagID, boolean gameWon) {
      super(CFG.langManager.getCiv(sTag), 0, iPosX, iPosY, iWidth, Math.max(CFG.CIV_FLAG_HEIGHT + CFG.PADDING * 4, CFG.TEXT_HEIGHT + CFG.PADDING * 4));
      this.loadFlag(this.sTag = sTag);
      this.gameWon = gameWon;
      boolean modified = false;
      Civilization_GameData3 tempSR = null;
      String tempTag = nTagID;

      while (tempTag.indexOf(";") <= 0) {
      }

      String[] tData = tempTag.split(";");
      tempTag = tData[0];

      try {
         try {
            FileHandle fileSR = Gdx.files.internal("game/civilizations/" + tempTag);
            tempSR = (Civilization_GameData3)CFG.deserialize(fileSR.readBytes());
         } catch (GdxRuntimeException var21) {
            try {
               FileHandle fileSR2 = Gdx.files.internal("game/civilizations/" + CFG.ideologiesManager.getRealTag(tempTag));
               tempSR = (Civilization_GameData3)CFG.deserialize(fileSR2.readBytes());
            } catch (GdxRuntimeException var20) {
               try {
                  FileHandle fileSR3 = Gdx.files.local("game/civilizations_editor/" + tempTag + "/" + tempTag);
                  tempSR = (Civilization_GameData3)CFG.deserialize(fileSR3.readBytes());
               } catch (GdxRuntimeException var19) {
                  try {
                     FileHandle fileSR4 = Gdx.files.internal("game/civilizations_editor/" + tempTag + "/" + tempTag);
                     tempSR = (Civilization_GameData3)CFG.deserialize(fileSR4.readBytes());
                  } catch (GdxRuntimeException var18) {
                     try {
                        FileHandle fileSR5 = Gdx.files
                           .local("game/civilizations_editor/" + CFG.ideologiesManager.getRealTag(tempTag) + "/" + CFG.ideologiesManager.getRealTag(tempTag));
                        tempSR = (Civilization_GameData3)CFG.deserialize(fileSR5.readBytes());
                     } catch (GdxRuntimeException var17) {
                        FileHandle fileSR6 = Gdx.files
                           .internal("game/civilizations_editor/" + CFG.ideologiesManager.getRealTag(tempTag) + "/" + CFG.ideologiesManager.getRealTag(tempTag));
                        tempSR = (Civilization_GameData3)CFG.deserialize(fileSR6.readBytes());
                     }
                  }
               }
            }
         }
      } catch (ClassNotFoundException var22) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var22);
         }
      } catch (IOException var23) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var23);
         }
      }

      if (tempSR.sr_GameData != null) {
         this.iSRID = CFG.serviceRibbon_Manager.getSRID(tempSR.sr_GameData.getSRTAG());

         for (int i = 0; i < tempSR.sr_GameData.getColors().size(); i++) {
            this.lColors
               .add(new Color(tempSR.sr_GameData.getColor(i).getR(), tempSR.sr_GameData.getColor(i).getG(), tempSR.sr_GameData.getColor(i).getB(), 1.0F));
         }
      }
   }

   @Override
   public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
      oSB.setColor(new Color(CFG.COLOR_GRADIENT_TITLE_BLUE.r, CFG.COLOR_GRADIENT_TITLE_BLUE.g, CFG.COLOR_GRADIENT_TITLE_BLUE.b, 0.55F));
      ImageManager.getImage(Images.patt2)
         .draw2(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.patt2).getHeight() + iTranslateY,
            this.getWidth(),
            this.getHeight()
         );
      oSB.setColor(new Color(CFG.COLOR_GRADIENT_DARK_BLUE.r, CFG.COLOR_GRADIENT_DARK_BLUE.g, CFG.COLOR_GRADIENT_DARK_BLUE.b, 0.175F));
      ImageManager.getImage(Images.slider_gradient)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY,
            this.getWidth() / 2,
            this.getHeight()
         );
      ImageManager.getImage(Images.slider_gradient)
         .draw(
            oSB,
            this.getPosX() + this.getWidth() / 2 + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY,
            this.getWidth() / 2,
            this.getHeight(),
            true,
            false
         );
      oSB.setColor(new Color(CFG.COLOR_CREATE_NEW_GAME_BOX_PLAYERS.r, CFG.COLOR_CREATE_NEW_GAME_BOX_PLAYERS.g, CFG.COLOR_CREATE_NEW_GAME_BOX_PLAYERS.b, 0.625F));
      ImageManager.getImage(Images.pix255_255_255)
         .draw(
            oSB,
            this.getPosX() + CFG.CIV_FLAG_WIDTH + CFG.PADDING * 4 + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY,
            1,
            this.getHeight()
         );
      oSB.setColor(new Color(CFG.COLOR_CREATE_NEW_GAME_BOX_PLAYERS.r, CFG.COLOR_CREATE_NEW_GAME_BOX_PLAYERS.g, CFG.COLOR_CREATE_NEW_GAME_BOX_PLAYERS.b, 0.275F));
      ImageManager.getImage(Images.pix255_255_255)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() + this.getHeight() - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY,
            this.getWidth(),
            1
         );
      ImageManager.getImage(Images.pix255_255_255)
         .draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY, this.getWidth(), 1);
      oSB.setColor(new Color(CFG.COLOR_CREATE_NEW_GAME_BOX_PLAYERS.r, CFG.COLOR_CREATE_NEW_GAME_BOX_PLAYERS.g, CFG.COLOR_CREATE_NEW_GAME_BOX_PLAYERS.b, 0.475F));
      ImageManager.getImage(Images.slider_gradient)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() + this.getHeight() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY,
            this.getWidth() / 2,
            1
         );
      ImageManager.getImage(Images.slider_gradient)
         .draw(
            oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY, this.getWidth() / 2, 1
         );
      ImageManager.getImage(Images.slider_gradient)
         .draw(
            oSB,
            this.getPosX() + this.getWidth() / 2 + iTranslateX,
            this.getPosY() + this.getHeight() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY,
            this.getWidth() / 2,
            1,
            true,
            false
         );
      ImageManager.getImage(Images.slider_gradient)
         .draw(
            oSB,
            this.getPosX() + this.getWidth() / 2 + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY,
            this.getWidth() / 2,
            1,
            true,
            false
         );
      oSB.setColor(Color.WHITE);
      CFG.serviceRibbon_Manager
         .drawSRLevel(
            oSB,
            this.getPosX() + this.getWidth() - CFG.SERVICE_RIBBON_WIDTH - CFG.PADDING * 2 + iTranslateX,
            this.getPosY() + this.getHeight() / 2 - CFG.SERVICE_RIBBON_HEIGHT / 2 + iTranslateY,
            5,
            0,
            0,
            this.iSRID,
            this.lColors
         );
      if (!this.gameWon) {
         oSB.setColor(new Color(CFG.COLOR_GRADIENT_TITLE_BLUE.r, CFG.COLOR_GRADIENT_TITLE_BLUE.g, CFG.COLOR_GRADIENT_TITLE_BLUE.b, 0.425F));
         ImageManager.getImage(Images.pix255_255_255)
            .draw(
               oSB,
               this.getPosX() + this.getWidth() - CFG.SERVICE_RIBBON_WIDTH - CFG.PADDING * 2 + iTranslateX,
               this.getPosY() + this.getHeight() / 2 - CFG.SERVICE_RIBBON_HEIGHT / 2 - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY,
               CFG.SERVICE_RIBBON_WIDTH,
               CFG.SERVICE_RIBBON_HEIGHT
            );
         oSB.setColor(Color.WHITE);
      }

      this.civFlag
         .draw(
            oSB,
            this.getPosX() + CFG.PADDING * 2 + iTranslateX,
            this.getPosY() + this.getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 - this.civFlag.getHeight() + iTranslateY,
            CFG.CIV_FLAG_WIDTH,
            CFG.CIV_FLAG_HEIGHT
         );
      ImageManager.getImage(Images.flag_rect)
         .draw(oSB, this.getPosX() + CFG.PADDING * 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 + iTranslateY);
      CFG.fontMain.getData().setScale(0.8F);
      CFG.drawText(
         oSB,
         this.getText(),
         this.getPosX() + CFG.PADDING * 2 + CFG.CIV_FLAG_WIDTH + CFG.PADDING * 4 + iTranslateX,
         this.getPosY() + this.getHeight() / 2 - (int)(this.getTextHeight() * 0.8F) / 2 + iTranslateY,
         this.getColor(isActive)
      );
      CFG.fontMain.getData().setScale(1.0F);
   }

   public final void loadFlag(String nTag) {
      this.disposeFlag();

      try {
         try {
            this.civFlag = new Image(new Texture(Gdx.files.internal("game/flags/" + nTag + ".png")), Texture.TextureFilter.Nearest);
         } catch (GdxRuntimeException var7) {
            try {
               this.civFlag = new Image(
                  new Texture(Gdx.files.internal("game/flags/" + CFG.ideologiesManager.getRealTag(nTag) + ".png")), Texture.TextureFilter.Nearest
               );
            } catch (GdxRuntimeException var6) {
               if (CFG.isAndroid()) {
                  try {
                     this.civFlag = new Image(
                        new Texture(
                           Gdx.files
                              .local(
                                 "game/civilizations_editor/"
                                    + CFG.ideologiesManager.getRealTag(nTag)
                                    + "/"
                                    + CFG.ideologiesManager.getRealTag(nTag)
                                    + "_FL.png"
                              )
                        ),
                        Texture.TextureFilter.Nearest
                     );
                  } catch (GdxRuntimeException var5) {
                     this.civFlag = new Image(
                        new Texture(
                           Gdx.files
                              .internal(
                                 "game/civilizations_editor/"
                                    + CFG.ideologiesManager.getRealTag(nTag)
                                    + "/"
                                    + CFG.ideologiesManager.getRealTag(nTag)
                                    + "_FL.png"
                              )
                        ),
                        Texture.TextureFilter.Nearest
                     );
                  }

                  return;
               }

               this.civFlag = new Image(
                  new Texture(
                     Gdx.files
                        .internal(
                           "game/civilizations_editor/" + CFG.ideologiesManager.getRealTag(nTag) + "/" + CFG.ideologiesManager.getRealTag(nTag) + "_FL.png"
                        )
                  ),
                  Texture.TextureFilter.Nearest
               );
            }
         }
      } catch (GdxRuntimeException var8) {
         this.civFlag = new Image(new Texture(Gdx.files.internal("game/flags/ran.png")), Texture.TextureFilter.Nearest);
      }
   }

   public final void disposeFlag() {
      if (this.civFlag != null) {
         this.civFlag.getTexture().dispose();
         this.civFlag = null;
      }
   }

   @Override
   public Color getColor(boolean isActive) {
      return isActive
         ? CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
         : (this.getClickable() ? (this.getIsHovered() ? CFG.COLOR_BUTTON_GAME_TEXT_HOVERED : Color.WHITE) : CFG.COLOR_BUTTON_GAME_TEXT_NOT_CLICKABLE);
   }

   @Override
   public void buildElementHover() {
      List<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
      List<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
      nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.wikipedia));
      nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Wiki") + ": "));
      nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.getWikiInormationsLink_Clear(this.sTag), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
      nElements.add(new MenuElement_Hover_v2_Element2(nData));
      nData.clear();
      this.menuElementHover = new MenuElement_Hover_v2(nElements);
   }

   @Override
   public void actionElement(int iID) {
      CFG.EDITOR_ACTIVE_GAMEDATA_TAG = this.sTag;
      CFG.setDialogType(Dialog.GO_TO_WIKI);
   }
}
