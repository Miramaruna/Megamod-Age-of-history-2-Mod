package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Button_Menu_Scenario extends Button_Menu {
   public int iScenarioID;
   public String sScenarioName;
   public String sScenarioDate;

   public Button_Menu_Scenario(int iScenarioID, String sText, int iTextPositionX, int iPosX, int iPosY, int iWidth, int iHeight, boolean isClickable) {
      super(sText, iTextPositionX, iPosX, iPosY, iWidth, iHeight, isClickable);
      this.init(iScenarioID);
   }

   public Button_Menu_Scenario(
      int iScenarioID, String sText, int iTextPositionX, int iPosX, int iPosY, int iWidth, int iHeight, boolean isClickable, boolean checkboxState
   ) {
      super(sText, iTextPositionX, iPosX, iPosY, iWidth, iHeight, isClickable, checkboxState);
      this.init(iScenarioID);
   }

   public final void init(int iScenarioID) {
      this.iScenarioID = iScenarioID;
      this.sScenarioName = ""
         + CFG.game.getGameScenarios().getScenarioDay(iScenarioID)
         + " "
         + Game_Calendar.getMonthName(CFG.game.getGameScenarios().getScenarioMonth(iScenarioID))
         + " "
         + CFG.gameAges.getYear(CFG.game.getGameScenarios().getScenarioYear(iScenarioID));
      this.sScenarioDate = CFG.game.getGameScenarios().getNumOfCivs(iScenarioID)
         + " "
         + CFG.langManager.get("Civilizations")
         + " - "
         + CFG.gameAges.getAge(CFG.game.getGameScenarios().getScenarioAge(iScenarioID)).getName();
   }

   @Override
   public void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
      ImageManager.getImage(Images.time)
         .draw(
            oSB,
            this.getPosX() + this.getTextPos() / 2 - ImageManager.getImage(Images.time).getWidth() / 2 + iTranslateX,
            this.getPosY() + this.getHeight() / 2 - ImageManager.getImage(Images.time).getHeight() / 2 + iTranslateY
         );
      CFG.fontMain.getData().setScale(0.9F);
      CFG.drawText(
         oSB,
         this.getText(),
         this.getPosX() + this.getTextPos() + iTranslateX,
         this.getPosY() + this.getHeight() / 2 - (int)(CFG.TEXT_HEIGHT * 0.9F + CFG.PADDING + CFG.TEXT_HEIGHT * 0.7F) / 2 + iTranslateY,
         this.getIsHovered() ? CFG.COLOR_TEXT_CNG_TOP_SCENARIO_NAME_HOVER : CFG.COLOR_TEXT_CNG_TOP_SCENARIO_NAME
      );
      CFG.fontMain.getData().setScale(0.7F);
      CFG.drawText(
         oSB,
         this.sScenarioName,
         this.getPosX() + this.getTextPos() + CFG.PADDING + (int)(this.getTextWidth() * 0.9F) + iTranslateX,
         this.getPosY()
            + this.getHeight() / 2
            - (int)(CFG.TEXT_HEIGHT * 0.9F + CFG.PADDING + CFG.TEXT_HEIGHT * 0.7F) / 2
            + (int)(CFG.TEXT_HEIGHT * 0.9F - CFG.TEXT_HEIGHT * 0.7F)
            + iTranslateY,
         new Color(0.67F, 0.67F, 0.67F, 1.0F)
      );
      CFG.drawText(
         oSB,
         this.sScenarioDate,
         this.getPosX() + this.getTextPos() + iTranslateX,
         this.getPosY()
            + this.getHeight() / 2
            - (int)(CFG.TEXT_HEIGHT * 0.9F + CFG.PADDING + CFG.TEXT_HEIGHT * 0.7F) / 2
            + CFG.PADDING
            + (int)(CFG.TEXT_HEIGHT * 0.9F)
            + iTranslateY,
         new Color(0.58F, 0.58F, 0.58F, 1.0F)
      );
      CFG.fontMain.getData().setScale(1.0F);
   }

   @Override
   public int getCurrent() {
      return this.iScenarioID;
   }
}
