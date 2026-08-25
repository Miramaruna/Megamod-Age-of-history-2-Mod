package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

public class Menu_CreateNewGame_Scenarios extends SliderMenu {
   public static final int ANIMATION_TIME = 250;
   public static long lTime = 0L;

   public Menu_CreateNewGame_Scenarios() {
      int tempW = CFG.CIV_INFO_MENU_WIDTH;
      int tempMaxH = CFG.GAME_HEIGHT
         - (ImageManager.getImage(Images.top_left).getHeight() + CFG.PADDING * 2 + CFG.BUTTON_HEIGHT * 3 / 4)
         - (CFG.BUTTON_HEIGHT + CFG.PADDING * 2)
         - CFG.PADDING;
      int tempElemH = CFG.BUTTON_HEIGHT * 3 / 4;
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      menuElements.add(new Button_CNG_Options(null, -1, 0, 0, tempW, tempElemH, true));
      int tH = tempElemH;

      for (int i = 0; i < Game_Scenarios.SCENARIOS_SIZE; i++) {
         menuElements.add(
            new Button_CNG_Scenario(i, CFG.PADDING * 2, 0, tH, tempW, true) {
               @Override
               public void buildElementHover() {
                  ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
                  ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
                  nData.add(
                     new MenuElement_Hover_v2_Element_Type_Text(
                        CFG.langManager.get(CFG.game.getGameScenarios().getScenarioName(this.getCurrent())), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                     )
                  );
                  nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.time, CFG.PADDING, CFG.PADDING));
                  nData.add(
                     new MenuElement_Hover_v2_Element_Type_Text(
                        CFG.game.getGameScenarios().getScenarioDay(this.getCurrent())
                           + " "
                           + Game_Calendar.getMonthName(CFG.game.getGameScenarios().getScenarioMonth(this.getCurrent()))
                           + " "
                           + CFG.gameAges.getYear(CFG.game.getGameScenarios().getScenarioYear(this.getCurrent())),
                        CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                     )
                  );
                  nElements.add(new MenuElement_Hover_v2_Element2(nData));
                  nData.clear();
                  nData.add(new MenuElement_Hover_v2_Element_Type_Space());
                  nElements.add(new MenuElement_Hover_v2_Element2(nData));
                  nData.clear();
                  nData.add(
                     new MenuElement_Hover_v2_Element_Type_Text(CFG.gameAges.getAge(CFG.game.getGameScenarios().getScenarioAge(this.getCurrent())).getName())
                  );
                  nElements.add(new MenuElement_Hover_v2_Element2(nData));
                  nData.clear();
                  nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Civilizations") + ": "));
                  nData.add(
                     new MenuElement_Hover_v2_Element_Type_Text(
                        "" + CFG.game.getGameScenarios().getNumOfCivs(this.getCurrent()), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                     )
                  );
                  nElements.add(new MenuElement_Hover_v2_Element2(nData));
                  nData.clear();
                  nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Author") + ": "));
                  nData.add(
                     new MenuElement_Hover_v2_Element_Type_Text(
                        "" + CFG.game.getGameScenarios().getScenarioAuthor(this.getCurrent()), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                     )
                  );
                  nElements.add(new MenuElement_Hover_v2_Element2(nData));
                  nData.clear();
                  this.menuElementHover = new MenuElement_Hover_v2(nElements);
               }
            }
         );
         tH += menuElements.get(menuElements.size() - 1).getHeight();
      }

      menuElements.add(
         new Button_CNG_Options(
            null, -1, 0, menuElements.get(menuElements.size() - 1).getPosY() + menuElements.get(menuElements.size() - 1).getHeight(), tempW, tempElemH, true
         )
      );
      this.initMenu(
         new SliderMenuTitle(null, CFG.BUTTON_HEIGHT * 3 / 4, false, false) {
            @Override
            public void draw(SpriteBatch oSB, int iTranslateX, int nPosX, int nPosY, int nWidth, boolean sliderMenuIsActive) {
               ImageManager.getImage(Images.new_game_top_edge_title)
                  .draw2(
                     oSB,
                     Menu_CreateNewGame_Scenarios.this.getPosX() + iTranslateX,
                     Menu_CreateNewGame_Scenarios.this.getPosY() - ImageManager.getImage(Images.new_game_top_edge_title).getHeight() - this.getHeight(),
                     Menu_CreateNewGame_Scenarios.this.getWidth() + 2,
                     this.getHeight(),
                     true,
                     false
                  );
               oSB.setColor(new Color(0.011F, 0.014F, 0.019F, 0.25F));
               ImageManager.getImage(Images.gradient)
                  .draw(
                     oSB,
                     Menu_CreateNewGame_Scenarios.this.getPosX() + iTranslateX,
                     Menu_CreateNewGame_Scenarios.this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() - this.getHeight() * 3 / 4,
                     Menu_CreateNewGame_Scenarios.this.getWidth(),
                     this.getHeight() * 3 / 4,
                     false,
                     true
                  );
               oSB.setColor(new Color(0.451F, 0.329F, 0.11F, 1.0F));
               ImageManager.getImage(Images.pix255_255_255)
                  .draw(
                     oSB,
                     Menu_CreateNewGame_Scenarios.this.getPosX() + iTranslateX,
                     Menu_CreateNewGame_Scenarios.this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight(),
                     Menu_CreateNewGame_Scenarios.this.getWidth()
                  );
               oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.4F));
               ImageManager.getImage(Images.line_32_off1)
                  .draw(
                     oSB,
                     Menu_CreateNewGame_Scenarios.this.getPosX() + iTranslateX,
                     Menu_CreateNewGame_Scenarios.this.getPosY()
                        - ImageManager.getImage(Images.pix255_255_255).getHeight()
                        - ImageManager.getImage(Images.line_32_off1).getHeight(),
                     Menu_CreateNewGame_Scenarios.this.getWidth(),
                     1
                  );
               oSB.setColor(Color.WHITE);
               CFG.fontMain.getData().setScale(0.8F);
               CFG.drawText(
                  oSB,
                  this.getText(),
                  nPosX + nWidth / 2 - (int)(this.getTextWidth() * 0.8F / 2.0F) + iTranslateX,
                  nPosY - this.getHeight() + this.getHeight() / 2 + 1 - (int)(this.getTextHeight() * 0.8F / 2.0F),
                  CFG.COLOR_TEXT_OPTIONS_LEFT_NS
               );
               CFG.fontMain.getData().setScale(1.0F);
            }
         },
         0,
         ImageManager.getImage(Images.top_left).getHeight() + CFG.PADDING * 2 + CFG.BUTTON_HEIGHT * 3 / 4,
         tempW,
         tempMaxH < menuElements.get(menuElements.size() - 1).getPosY() + menuElements.get(menuElements.size() - 1).getHeight()
            ? tempMaxH
            : menuElements.get(menuElements.size() - 1).getPosY() + menuElements.get(menuElements.size() - 1).getHeight(),
         menuElements
      );
      this.setVisible(false);
      this.updateLanguage();
   }

   @Override
   public void updateLanguage() {
      this.getMenuElement(0).setText(CFG.langManager.get("DownloadMore"));
      this.getMenuElement(this.getMenuElementsSize() - 1).setText(CFG.langManager.get("Back"));
      this.getTitle().setText(CFG.langManager.get("Scenarios"));
   }

   @Override
   public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      if (lTime + 250L >= System.currentTimeMillis()) {
         iTranslateX += -this.getWidth() + (int)(this.getWidth() * ((float)(System.currentTimeMillis() - lTime) / 250.0F));
         CFG.setRender_3(true);
      }

      ImageManager.getImage(Images.new_game_top_edge_line)
         .draw2(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.new_game_top_edge_line).getHeight() + iTranslateY,
            this.getWidth() + 2,
            this.getHeight(),
            true,
            true
         );
      super.draw(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
      oSB.setColor(CFG.COLOR_CREATE_NEW_GAME_BOX_PLAYERS);
      ImageManager.getImage(Images.pix255_255_255)
         .draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + this.getHeight(), this.getWidth());
      oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.4F));
      ImageManager.getImage(Images.line_32_off1)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY()
               - ImageManager.getImage(Images.pix255_255_255).getHeight()
               - ImageManager.getImage(Images.line_32_off1).getHeight()
               + this.getHeight(),
            this.getWidth(),
            1
         );
      oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.5F));
      ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight(), this.getWidth() + 2);
      oSB.setColor(Color.WHITE);
   }

   @Override
   public void drawScrollPos(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      if ((sliderMenuIsActive || this.getScrollModeY()) && !CFG.menuManager.getSliderMode()) {
         super.drawScrollPos(oSB, iTranslateX - 2, iTranslateY, sliderMenuIsActive);
      }
   }

   @Override
   public void actionElement(int iID) {
      if (iID == this.getMenuElementsSize() - 1 && !CFG.FREEPLAY_MODE) {
         CFG.menuManager.setVisible_CreateNewGame_Options(true);
      } else if (iID != 0 && CFG.game.getScenarioID() != iID - 1) {
         CFG.game.disableDrawCivlizationsRegions_Players();
         CFG.viewsManager.disableAllViews();
         CFG.game.setScenarioID(iID - 1);
         CFG.game.loadScenario(false);
         CFG.game.initPlayers();
         CFG.setActiveCivInfo(CFG.game.getPlayer(0).getCivID());
         CFG.updateActiveCivInfo_CreateNewGame();
         CFG.game.enableDrawCivlizationsRegions_Players();
         CFG.menuManager.rebuildCivilizations_Info_Players();
         CFG.menuManager.getCreateNewGame_CivInfo_updateLanguage();
         ArrayList<String> tempMess = new ArrayList<>();
         tempMess.add(CFG.langManager.get(CFG.game.getGameScenarios().getScenarioName(CFG.game.getScenarioID())));
         tempMess.add(Game_Calendar.getCurrentDate());
         CFG.toast.setInView(tempMess);
      }
   }

   @Override
   public void setVisible(boolean visible) {
      super.setVisible(visible);
      lTime = System.currentTimeMillis();
   }
}
