package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.GdxRuntimeException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Text_ServiceRibbon extends Text {
   public static final float FONT_SCALE = 0.7F;
   public static final float FONT_SCALE2 = 0.6F;
   public int iSRID = 0;
   public int iLevelID = 0;
   public List<Color> lColors;
   public String sLevel;
   public int iLevelWidth;
   public String sLevel2;
   public int iLevelWidth2;
   public int iNum;

   public Text_ServiceRibbon(String sText, int iPosX, int iPosY, int iWidth, String nTagID, int nLevelID, int nNum, int modified) {
      super(sText, 0, iPosX, iPosY, iWidth, Math.max(CFG.TEXT_HEIGHT + CFG.PADDING * 4, CFG.SERVICE_RIBBON_HEIGHT + CFG.PADDING * 4));
      this.iLevelID = nLevelID;
      this.lColors = new ArrayList<>();
      this.iNum = nNum;
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
         } catch (GdxRuntimeException var22) {
            try {
               FileHandle fileSR2 = Gdx.files.internal("game/civilizations/" + CFG.ideologiesManager.getRealTag(tempTag));
               tempSR = (Civilization_GameData3)CFG.deserialize(fileSR2.readBytes());
            } catch (GdxRuntimeException var21) {
               try {
                  FileHandle fileSR3 = Gdx.files.local("game/civilizations_editor/" + tempTag + "/" + tempTag);
                  tempSR = (Civilization_GameData3)CFG.deserialize(fileSR3.readBytes());
               } catch (GdxRuntimeException var20) {
                  try {
                     FileHandle fileSR4 = Gdx.files.internal("game/civilizations_editor/" + tempTag + "/" + tempTag);
                     tempSR = (Civilization_GameData3)CFG.deserialize(fileSR4.readBytes());
                  } catch (GdxRuntimeException var19) {
                     try {
                        FileHandle fileSR5 = Gdx.files
                           .local("game/civilizations_editor/" + CFG.ideologiesManager.getRealTag(tempTag) + "/" + CFG.ideologiesManager.getRealTag(tempTag));
                        tempSR = (Civilization_GameData3)CFG.deserialize(fileSR5.readBytes());
                     } catch (GdxRuntimeException var18) {
                        FileHandle fileSR6 = Gdx.files
                           .internal("game/civilizations_editor/" + CFG.ideologiesManager.getRealTag(tempTag) + "/" + CFG.ideologiesManager.getRealTag(tempTag));
                        tempSR = (Civilization_GameData3)CFG.deserialize(fileSR6.readBytes());
                     }
                  }
               }
            }
         }
      } catch (ClassNotFoundException var23) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var23);
         }
      } catch (IOException var24) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var24);
         }
      }

      if (tempSR.sr_GameData != null) {
         this.iSRID = CFG.serviceRibbon_Manager.getSRID(tempSR.sr_GameData.getSRTAG());
         if (modified != 0) {
            this.iSRID -= modified;
            if (this.iSRID >= CFG.serviceRibbon_Manager.getSRSize()) {
               this.iSRID = CFG.serviceRibbon_Manager.getSRSize() - 1;
            }

            if (this.iSRID < 0) {
               this.iSRID = CFG.serviceRibbon_Manager.getSRSize() - Math.abs(modified);
            }

            if (this.iSRID < 0) {
               this.iSRID = CFG.serviceRibbon_Manager.getSRSize() - 1;
            }
         }

         for (int i = 0; i < tempSR.sr_GameData.getColors().size(); i++) {
            this.lColors
               .add(new Color(tempSR.sr_GameData.getColor(i).getR(), tempSR.sr_GameData.getColor(i).getG(), tempSR.sr_GameData.getColor(i).getB(), 1.0F));
         }

         if (modified != 0 && this.lColors.size() < CFG.serviceRibbon_Manager.getSR(this.iSRID).getSize()) {
            int iLeft = CFG.serviceRibbon_Manager.getSR(this.iSRID).getSize() - this.lColors.size();
            this.lColors.add(new Color(1.0F, 1.0F, 1.0F, 1.0F));
            int n = --iLeft;
            iLeft--;
            if (n > 0) {
               this.lColors.add(new Color(0.3137255F, 0.3137255F, 0.3137255F, 1.0F));
            }

            if (iLeft-- > 0) {
               this.lColors.add(new Color(0.2509804F, 0.32941177F, 0.5882353F, 1.0F));
            }

            if (iLeft-- > 0) {
               this.lColors.add(new Color(0.88235295F, 0.8156863F, 0.27058825F, 1.0F));
            }

            while (iLeft-- > 0) {
               this.lColors.add(CFG.getRandomColor());
            }
         }
      }

      this.sLevel = CFG.langManager.get("Level") + ": ";
      CFG.glyphLayout.setText(CFG.fontMain, this.sLevel);
      this.iLevelWidth = (int)(CFG.glyphLayout.width * 0.6F);
      this.sLevel2 = "" + (this.iLevelID + 1);
      CFG.glyphLayout.setText(CFG.fontMain, this.sLevel2);
      this.iLevelWidth2 = (int)(CFG.glyphLayout.width * 0.6F);
   }

   @Override
   public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
      oSB.setColor(new Color(CFG.COLOR_GRADIENT_TITLE_BLUE.r, CFG.COLOR_GRADIENT_TITLE_BLUE.g, CFG.COLOR_GRADIENT_TITLE_BLUE.b, 0.175F));
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
      oSB.setColor(new Color(CFG.COLOR_GRADIENT_TITLE_BLUE.r, CFG.COLOR_GRADIENT_TITLE_BLUE.g, CFG.COLOR_GRADIENT_TITLE_BLUE.b, 0.75F));
      ImageManager.getImage(Images.pix255_255_255)
         .draw(
            oSB,
            this.getPosX() + CFG.SERVICE_RIBBON_WIDTH + CFG.PADDING * 4 + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY,
            1,
            this.getHeight()
         );
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
      oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.8F));
      ImageManager.getImage(Images.slider_gradient)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() + this.getHeight() - 1 - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY,
            this.getWidth() / 2,
            1
         );
      ImageManager.getImage(Images.slider_gradient)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() + 1 - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY,
            this.getWidth() / 2,
            1
         );
      ImageManager.getImage(Images.slider_gradient)
         .draw(
            oSB,
            this.getPosX() + this.getWidth() / 2 + iTranslateX,
            this.getPosY() + this.getHeight() - 1 - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY,
            this.getWidth() / 2,
            1,
            true,
            false
         );
      ImageManager.getImage(Images.slider_gradient)
         .draw(
            oSB,
            this.getPosX() + this.getWidth() / 2 + iTranslateX,
            this.getPosY() + 1 - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY,
            this.getWidth() / 2,
            1,
            true,
            false
         );
      oSB.setColor(Color.WHITE);
      CFG.serviceRibbon_Manager
         .drawSRLevel(
            oSB,
            this.getPosX() + CFG.PADDING * 2 + iTranslateX,
            this.getPosY() + this.getHeight() / 2 - CFG.SERVICE_RIBBON_HEIGHT / 2 + iTranslateY,
            this.iLevelID,
            0,
            0,
            this.iSRID,
            this.lColors
         );
      CFG.fontMain.getData().setScale(0.6F);
      CFG.drawText(
         oSB,
         this.sLevel,
         this.getPosX() + this.getWidth() - CFG.PADDING * 2 - this.iLevelWidth - this.iLevelWidth2 + iTranslateX,
         this.getPosY() + this.getHeight() / 2 - (int)(this.getTextHeight() * 0.6F) / 2 + iTranslateY,
         CFG.COLOR_TEXT_OPTIONS_NS_HOVER
      );
      CFG.drawText(
         oSB,
         this.sLevel2,
         this.getPosX() + this.getWidth() - CFG.PADDING * 2 - this.iLevelWidth2 + iTranslateX,
         this.getPosY() + this.getHeight() / 2 - (int)(this.getTextHeight() * 0.6F) / 2 + iTranslateY,
         CFG.COLOR_TEXT_MODIFIER_NEUTRAL
      );
      CFG.fontMain.getData().setScale(0.7F);
      CFG.drawText(
         oSB,
         this.getText(),
         this.getPosX() + CFG.PADDING * 6 + CFG.SERVICE_RIBBON_WIDTH + iTranslateX,
         this.getPosY() + this.getHeight() / 2 - (int)(this.getTextHeight() * 0.7F) / 2 + iTranslateY,
         this.getColor(isActive)
      );
      CFG.drawText(
         oSB,
         "" + this.iNum,
         this.getPosX() + CFG.PADDING * 6 + CFG.SERVICE_RIBBON_WIDTH + (int)(this.getTextWidth() * 0.7F) + iTranslateX,
         this.getPosY() + this.getHeight() / 2 - (int)(this.getTextHeight() * 0.7F) / 2 + iTranslateY,
         this.getIsHovered() ? CFG.COLOR_TEXT_MODIFIER_NEUTRAL2 : CFG.COLOR_TEXT_NUM_OF_PROVINCES
      );
      CFG.fontMain.getData().setScale(1.0F);
   }

   @Override
   public Color getColor(boolean isActive) {
      return isActive
         ? CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
         : (
            this.getClickable()
               ? (this.getIsHovered() ? CFG.COLOR_BUTTON_GAME_TEXT_HOVERED : CFG.COLOR_TEXT_MODIFIER_NEUTRAL)
               : CFG.COLOR_BUTTON_GAME_TEXT_NOT_CLICKABLE
         );
   }
}
