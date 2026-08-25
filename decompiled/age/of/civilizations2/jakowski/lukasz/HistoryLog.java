package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.io.Serializable;

public class HistoryLog implements Serializable {
   public static final long serialVersionUID = 0L;
   public static final float FONT_SCALE = 0.7F;
   public static int ICON_WIDTH = 0;
   public HistoryLog_Types historyLog_Type = HistoryLog_Types.WAR_DECLARAION;
   public int iCivA;
   public int iCivB;

   HistoryLog() {
   }

   public void updateLanguage() {
   }

   public void draw(SpriteBatch oSB, int nTurnID, int iPosX, int iPosY, int iWidth, int iHeight, boolean isActive) {
      CFG.fontMain.getData().setScale(0.7F);
      CFG.drawTextWithShadow(
         oSB,
         HistoryManager.lHistoryDates.get(nTurnID) + ": ",
         iPosX + ICON_WIDTH + CFG.PADDING,
         iPosY + (int)((iHeight - CFG.TEXT_HEIGHT * 0.7F) / 2.0F),
         CFG.COLOR_TEXT_RANK
      );
      CFG.fontMain.getData().setScale(1.0F);
   }

   public final void drawLeftIconBG(SpriteBatch oSB, int iPosX, int iPosY, int iWidth, int iHeight, boolean isActive) {
      oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.375F));
      ImageManager.getImage(Images.slider_gradient)
         .draw(
            oSB, iPosX + ICON_WIDTH - ICON_WIDTH / 2, iPosY - ImageManager.getImage(Images.slider_gradient).getHeight(), ICON_WIDTH / 2, iHeight, true, false
         );
      oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.225F));
      ImageManager.getImage(Images.slider_gradient)
         .draw(oSB, iPosX + ICON_WIDTH, iPosY - ImageManager.getImage(Images.slider_gradient).getHeight(), CFG.PADDING, iHeight);
      oSB.setColor(new Color(CFG.COLOR_GRADIENT_TITLE_BLUE.r, CFG.COLOR_GRADIENT_TITLE_BLUE.g, CFG.COLOR_GRADIENT_TITLE_BLUE.b, 0.7F));
      ImageManager.getImage(Images.line_32_vertical)
         .draw(oSB, iPosX + ICON_WIDTH - 1, iPosY - ImageManager.getImage(Images.line_32_vertical).getHeight(), 1, iHeight, true, false);
      oSB.setColor(Color.WHITE);
   }

   public final void drawLeftIcon(SpriteBatch oSB, int nImageID, int iPosX, int iPosY, int iWidth, int iHeight, boolean isActive) {
      this.drawLeftIconBG(oSB, iPosX, iPosY, iWidth, iHeight, isActive);
      ImageManager.getImage(nImageID)
         .draw(
            oSB,
            iPosX + (int)((ICON_WIDTH - ImageManager.getImage(nImageID).getWidth() * getImageScale(nImageID)) / 2.0F),
            iPosY
               + (int)((iHeight - ImageManager.getImage(nImageID).getHeight() * getImageScale(nImageID)) / 2.0F)
               - ImageManager.getImage(nImageID).getHeight(),
            (int)(ImageManager.getImage(nImageID).getWidth() * getImageScale(nImageID)),
            (int)(ImageManager.getImage(nImageID).getHeight() * getImageScale(nImageID))
         );
   }

   public static final float getImageScale(int nImageID) {
      return CFG.TEXT_HEIGHT * 0.7F / ImageManager.getImage(nImageID).getHeight() < 1.0F
         ? CFG.TEXT_HEIGHT * 0.7F / ImageManager.getImage(nImageID).getHeight()
         : 1.0F;
   }

   public static final float getImageScale_CrownVassal(int nIdelogyID) {
      return CFG.TEXT_HEIGHT * 0.7F / CFG.ideologiesManager.getIdeology(nIdelogyID).getiCrownVassalImage().getHeight() < 1.0F
         ? CFG.TEXT_HEIGHT * 0.7F / CFG.ideologiesManager.getIdeology(nIdelogyID).getiCrownVassalImage().getHeight()
         : 1.0F;
   }

   public String getName() {
      return "";
   }
}
