package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class HistoryLog_IsVassal extends HistoryLog {
   public String sMess;
   public int iMessWidth;
   public int iCivAWidth;

   public HistoryLog_IsVassal(int iCivA, int iCivB) {
      this.historyLog_Type = HistoryLog_Types.IS_VASSAL;
      this.iCivA = iCivB;
      this.iCivB = iCivA;
      this.updateLanguage();
   }

   @Override
   public void updateLanguage() {
      this.sMess = CFG.langManager.get("CivBIsNowAPuppetStateOfCivA");
      CFG.glyphLayout.setText(CFG.fontMain, this.sMess);
      this.iMessWidth = (int)(CFG.glyphLayout.width * 0.7F);

      try {
         CFG.glyphLayout.setText(CFG.fontMain, CFG.game.getCiv(this.iCivA).getCivName() + " ");
         this.iCivAWidth = (int)(CFG.glyphLayout.width * 0.7F);
      } catch (IndexOutOfBoundsException var2) {
         this.iCivAWidth = 0;
      }
   }

   @Override
   public void draw(SpriteBatch oSB, int nTurnID, int iPosX, int iPosY, int iWidth, int iHeight, boolean isActive) {
      this.drawLeftIconBG(oSB, iPosX, iPosY, iWidth, iHeight, isActive);

      try {
         CFG.ideologiesManager
            .getIdeology(CFG.game.getCiv(this.iCivA).getIdeologyID())
            .getiCrownVassalImage()
            .draw(
               oSB,
               iPosX
                  + (int)(
                     (
                           ICON_WIDTH
                              - CFG.ideologiesManager.getIdeology(CFG.game.getCiv(this.iCivA).getIdeologyID()).getiCrownVassalImage().getWidth()
                                 * getImageScale_CrownVassal(CFG.game.getCiv(this.iCivA).getIdeologyID())
                        )
                        / 2.0F
                  ),
               iPosY
                  + (int)(
                     (
                           iHeight
                              - CFG.ideologiesManager.getIdeology(CFG.game.getCiv(this.iCivA).getIdeologyID()).getiCrownVassalImage().getHeight()
                                 * getImageScale_CrownVassal(CFG.game.getCiv(this.iCivA).getIdeologyID())
                        )
                        / 2.0F
                  )
                  - CFG.ideologiesManager.getIdeology(CFG.game.getCiv(this.iCivA).getIdeologyID()).getiCrownVassalImage().getHeight(),
               (int)(
                  CFG.ideologiesManager.getIdeology(CFG.game.getCiv(this.iCivA).getIdeologyID()).getiCrownVassalImage().getWidth()
                     * getImageScale_CrownVassal(CFG.game.getCiv(this.iCivA).getIdeologyID())
               ),
               (int)(
                  CFG.ideologiesManager.getIdeology(CFG.game.getCiv(this.iCivA).getIdeologyID()).getiCrownVassalImage().getHeight()
                     * getImageScale_CrownVassal(CFG.game.getCiv(this.iCivA).getIdeologyID())
               )
            );
      } catch (IndexOutOfBoundsException var13) {
         CFG.ideologiesManager
            .getIdeology(0)
            .getiCrownVassalImage()
            .draw(
               oSB,
               iPosX + (int)((ICON_WIDTH - CFG.ideologiesManager.getIdeology(0).getiCrownVassalImage().getWidth() * getImageScale_CrownVassal(0)) / 2.0F),
               iPosY
                  + (int)((iHeight - CFG.ideologiesManager.getIdeology(0).getiCrownVassalImage().getHeight() * getImageScale_CrownVassal(0)) / 2.0F)
                  - CFG.ideologiesManager.getIdeology(0).getiCrownVassalImage().getHeight(),
               (int)(CFG.ideologiesManager.getIdeology(0).getiCrownVassalImage().getWidth() * getImageScale_CrownVassal(0)),
               (int)(CFG.ideologiesManager.getIdeology(0).getiCrownVassalImage().getHeight() * getImageScale_CrownVassal(0))
            );
      }

      super.draw(oSB, nTurnID, iPosX, iPosY, iWidth, iHeight, isActive);

      try {
         oSB.setColor(
            new Color(
               CFG.game.getCiv(this.iCivA).getR() / 255.0F, CFG.game.getCiv(this.iCivA).getG() / 255.0F, CFG.game.getCiv(this.iCivA).getB() / 255.0F, 0.85F
            )
         );
      } catch (IndexOutOfBoundsException var12) {
         oSB.setColor(new Color(CFG.RANDOM_CIVILIZATION_COLOR.r, CFG.RANDOM_CIVILIZATION_COLOR.g, CFG.RANDOM_CIVILIZATION_COLOR.b, 0.85F));
      }

      ImageManager.getImage(Images.pix255_255_255)
         .draw(
            oSB,
            iPosX + ICON_WIDTH + CFG.PADDING + HistoryManager.lHistoryDatesWidth.get(nTurnID),
            iPosY
               + (int)((iHeight - ImageManager.getImage(Images.flag_rect).getHeight() * getImageScale(Images.flag_rect)) / 2.0F)
               - ImageManager.getImage(Images.pix255_255_255).getHeight(),
            2,
            (int)(ImageManager.getImage(Images.flag_rect).getHeight() * getImageScale(Images.flag_rect))
         );
      oSB.setColor(Color.WHITE);
      CFG.fontMain.getData().setScale(0.7F);

      try {
         CFG.game
            .getCiv(this.iCivA)
            .getFlag()
            .draw(
               oSB,
               iPosX + 2 + ICON_WIDTH + CFG.PADDING + HistoryManager.lHistoryDatesWidth.get(nTurnID),
               iPosY
                  + (int)((iHeight - ImageManager.getImage(Images.flag_rect).getHeight() * getImageScale(Images.flag_rect)) / 2.0F)
                  - CFG.game.getCiv(this.iCivA).getFlag().getHeight(),
               (int)(ImageManager.getImage(Images.flag_rect).getWidth() * getImageScale(Images.flag_rect)),
               (int)(ImageManager.getImage(Images.flag_rect).getHeight() * getImageScale(Images.flag_rect))
            );
         ImageManager.getImage(Images.flag_rect)
            .draw(
               oSB,
               iPosX + 2 + ICON_WIDTH + CFG.PADDING + HistoryManager.lHistoryDatesWidth.get(nTurnID),
               iPosY
                  + (int)((iHeight - ImageManager.getImage(Images.flag_rect).getHeight() * getImageScale(Images.flag_rect)) / 2.0F)
                  - ImageManager.getImage(Images.flag_rect).getHeight(),
               (int)(ImageManager.getImage(Images.flag_rect).getWidth() * getImageScale(Images.flag_rect)),
               (int)(ImageManager.getImage(Images.flag_rect).getHeight() * getImageScale(Images.flag_rect))
            );
         CFG.drawText(
            oSB,
            CFG.game.getCiv(this.iCivA).getCivName(),
            iPosX
               + 2
               + ICON_WIDTH
               + CFG.PADDING
               + HistoryManager.lHistoryDatesWidth.get(nTurnID)
               + CFG.PADDING
               + (int)(ImageManager.getImage(Images.flag_rect).getWidth() * getImageScale(Images.flag_rect)),
            iPosY + (int)((iHeight - CFG.TEXT_HEIGHT * 0.7F) / 2.0F),
            CFG.COLOR_TEXT_CIV_NAME
         );
      } catch (IndexOutOfBoundsException var11) {
         ImageManager.getImage(Images.randomCivilizationFlag)
            .draw(
               oSB,
               iPosX + 2 + ICON_WIDTH + CFG.PADDING + HistoryManager.lHistoryDatesWidth.get(nTurnID),
               iPosY
                  + (int)((iHeight - ImageManager.getImage(Images.flag_rect).getHeight() * getImageScale(Images.flag_rect)) / 2.0F)
                  - ImageManager.getImage(Images.randomCivilizationFlag).getHeight(),
               (int)(ImageManager.getImage(Images.flag_rect).getWidth() * getImageScale(Images.flag_rect)),
               (int)(ImageManager.getImage(Images.flag_rect).getHeight() * getImageScale(Images.flag_rect))
            );
         ImageManager.getImage(Images.flag_rect)
            .draw(
               oSB,
               iPosX + 2 + ICON_WIDTH + CFG.PADDING + HistoryManager.lHistoryDatesWidth.get(nTurnID),
               iPosY
                  + (int)((iHeight - ImageManager.getImage(Images.flag_rect).getHeight() * getImageScale(Images.flag_rect)) / 2.0F)
                  - ImageManager.getImage(Images.flag_rect).getHeight(),
               (int)(ImageManager.getImage(Images.flag_rect).getWidth() * getImageScale(Images.flag_rect)),
               (int)(ImageManager.getImage(Images.flag_rect).getHeight() * getImageScale(Images.flag_rect))
            );
      }

      CFG.drawText(
         oSB,
         this.sMess,
         iPosX
            + 2
            + ICON_WIDTH
            + CFG.PADDING
            + HistoryManager.lHistoryDatesWidth.get(nTurnID)
            + CFG.PADDING
            + (int)(ImageManager.getImage(Images.flag_rect).getWidth() * getImageScale(Images.flag_rect))
            + this.iCivAWidth,
         iPosY + (int)((iHeight - CFG.TEXT_HEIGHT * 0.7F) / 2.0F),
         CFG.COLOR_TEXT_RANK
      );

      try {
         oSB.setColor(
            new Color(
               CFG.game.getCiv(this.iCivB).getR() / 255.0F, CFG.game.getCiv(this.iCivA).getG() / 255.0F, CFG.game.getCiv(this.iCivA).getB() / 255.0F, 0.85F
            )
         );
      } catch (IndexOutOfBoundsException var10) {
         oSB.setColor(new Color(CFG.RANDOM_CIVILIZATION_COLOR.r, CFG.RANDOM_CIVILIZATION_COLOR.g, CFG.RANDOM_CIVILIZATION_COLOR.b, 0.85F));
      }

      ImageManager.getImage(Images.pix255_255_255)
         .draw(
            oSB,
            iPosX
               + 2
               + ICON_WIDTH
               + CFG.PADDING * 2
               + HistoryManager.lHistoryDatesWidth.get(nTurnID)
               + CFG.PADDING
               + (int)(ImageManager.getImage(Images.flag_rect).getWidth() * getImageScale(Images.flag_rect))
               + this.iCivAWidth
               + this.iMessWidth,
            iPosY
               + (int)((iHeight - ImageManager.getImage(Images.flag_rect).getHeight() * getImageScale(Images.flag_rect)) / 2.0F)
               - ImageManager.getImage(Images.pix255_255_255).getHeight(),
            2,
            (int)(ImageManager.getImage(Images.flag_rect).getHeight() * getImageScale(Images.flag_rect))
         );
      oSB.setColor(Color.WHITE);

      try {
         CFG.game
            .getCiv(this.iCivB)
            .getFlag()
            .draw(
               oSB,
               iPosX
                  + 4
                  + ICON_WIDTH
                  + CFG.PADDING * 2
                  + HistoryManager.lHistoryDatesWidth.get(nTurnID)
                  + CFG.PADDING
                  + (int)(ImageManager.getImage(Images.flag_rect).getWidth() * getImageScale(Images.flag_rect))
                  + this.iCivAWidth
                  + this.iMessWidth,
               iPosY
                  + (int)((iHeight - ImageManager.getImage(Images.flag_rect).getHeight() * getImageScale(Images.flag_rect)) / 2.0F)
                  - CFG.game.getCiv(this.iCivA).getFlag().getHeight(),
               (int)(ImageManager.getImage(Images.flag_rect).getWidth() * getImageScale(Images.flag_rect)),
               (int)(ImageManager.getImage(Images.flag_rect).getHeight() * getImageScale(Images.flag_rect))
            );
         ImageManager.getImage(Images.flag_rect)
            .draw(
               oSB,
               iPosX
                  + 4
                  + ICON_WIDTH
                  + CFG.PADDING * 2
                  + HistoryManager.lHistoryDatesWidth.get(nTurnID)
                  + CFG.PADDING
                  + (int)(ImageManager.getImage(Images.flag_rect).getWidth() * getImageScale(Images.flag_rect))
                  + this.iCivAWidth
                  + this.iMessWidth,
               iPosY
                  + (int)((iHeight - ImageManager.getImage(Images.flag_rect).getHeight() * getImageScale(Images.flag_rect)) / 2.0F)
                  - ImageManager.getImage(Images.flag_rect).getHeight(),
               (int)(ImageManager.getImage(Images.flag_rect).getWidth() * getImageScale(Images.flag_rect)),
               (int)(ImageManager.getImage(Images.flag_rect).getHeight() * getImageScale(Images.flag_rect))
            );
         CFG.drawText(
            oSB,
            CFG.game.getCiv(this.iCivB).getCivName(),
            iPosX
               + 4
               + ICON_WIDTH
               + CFG.PADDING * 2
               + HistoryManager.lHistoryDatesWidth.get(nTurnID)
               + CFG.PADDING * 2
               + (int)(ImageManager.getImage(Images.flag_rect).getWidth() * getImageScale(Images.flag_rect)) * 2
               + this.iCivAWidth
               + this.iMessWidth,
            iPosY + (int)((iHeight - CFG.TEXT_HEIGHT * 0.7F) / 2.0F),
            CFG.COLOR_TEXT_CIV_NAME
         );
      } catch (IndexOutOfBoundsException var9) {
         ImageManager.getImage(Images.randomCivilizationFlag)
            .draw(
               oSB,
               iPosX
                  + 4
                  + ICON_WIDTH
                  + CFG.PADDING * 2
                  + HistoryManager.lHistoryDatesWidth.get(nTurnID)
                  + CFG.PADDING
                  + (int)(ImageManager.getImage(Images.flag_rect).getWidth() * getImageScale(Images.flag_rect))
                  + this.iCivAWidth
                  + this.iMessWidth,
               iPosY
                  + (int)((iHeight - ImageManager.getImage(Images.flag_rect).getHeight() * getImageScale(Images.flag_rect)) / 2.0F)
                  - ImageManager.getImage(Images.randomCivilizationFlag).getHeight(),
               (int)(ImageManager.getImage(Images.flag_rect).getWidth() * getImageScale(Images.flag_rect)),
               (int)(ImageManager.getImage(Images.flag_rect).getHeight() * getImageScale(Images.flag_rect))
            );
         ImageManager.getImage(Images.flag_rect)
            .draw(
               oSB,
               iPosX
                  + 4
                  + ICON_WIDTH
                  + CFG.PADDING * 2
                  + HistoryManager.lHistoryDatesWidth.get(nTurnID)
                  + CFG.PADDING
                  + (int)(ImageManager.getImage(Images.flag_rect).getWidth() * getImageScale(Images.flag_rect))
                  + this.iCivAWidth
                  + this.iMessWidth,
               iPosY
                  + (int)((iHeight - ImageManager.getImage(Images.flag_rect).getHeight() * getImageScale(Images.flag_rect)) / 2.0F)
                  - ImageManager.getImage(Images.flag_rect).getHeight(),
               (int)(ImageManager.getImage(Images.flag_rect).getWidth() * getImageScale(Images.flag_rect)),
               (int)(ImageManager.getImage(Images.flag_rect).getHeight() * getImageScale(Images.flag_rect))
            );
      }

      CFG.fontMain.getData().setScale(1.0F);
   }
}
