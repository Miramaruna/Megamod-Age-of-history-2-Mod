package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

public class Menu_CreateScenario_Events_Date_Calendar extends SliderMenu {
   public Menu_CreateScenario_Events_Date_Calendar() {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      int tempW = CFG.PADDING * 4 + CFG.BUTTON_HEIGHT * 2 / 3 * 7 + CFG.PADDING * 6;
      menuElements.add(
         new Button_ArrowLeft(0, CFG.PADDING, CFG.BUTTON_HEIGHT, CFG.BUTTON_HEIGHT * 2 / 3) {
            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               nData.add(
                  new MenuElement_Hover_v2_Element_Type_Text(
                     Game_Calendar.getMonthName(CFG.eventsManager.iCreateEvent_Month - 1), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                  )
               );
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
         }
      );
      menuElements.add(
         new Button_ArrowRight(tempW - CFG.BUTTON_HEIGHT, CFG.PADDING, CFG.BUTTON_HEIGHT, CFG.BUTTON_HEIGHT * 2 / 3) {
            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               nData.add(
                  new MenuElement_Hover_v2_Element_Type_Text(
                     Game_Calendar.getMonthName(CFG.eventsManager.iCreateEvent_Month + 1), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                  )
               );
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
         }
      );
      menuElements.add(
         new Text(
            Game_Calendar.getMonthName(CFG.eventsManager.iCreateEvent_Month),
            -1,
            CFG.BUTTON_HEIGHT,
            CFG.PADDING,
            tempW - CFG.BUTTON_HEIGHT * 2,
            CFG.BUTTON_HEIGHT * 2 / 3
         ) {
            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(Game_Calendar.getCurrentDate_CreateEvent(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
         }
      );
      int tX = 0;
      int tH = CFG.BUTTON_HEIGHT * 2 / 3 + CFG.PADDING * 2;

      for (int i = 0; i < Game_Calendar.getNumOfDaysInMonth(CFG.eventsManager.iCreateEvent_Month); i++) {
         menuElements.add(
            new Button_CalendarDay(i + 1, CFG.PADDING * 2 + CFG.BUTTON_HEIGHT * 2 / 3 * tX + CFG.PADDING * tX++, tH) {
               @Override
               public Color getColor(boolean isActive) {
                  return !isActive && CFG.eventsManager.iCreateEvent_Day != this.getCurrent()
                     ? (this.getClickable() ? new Color(0.38F, 0.38F, 0.38F, 1.0F) : new Color(0.49F, 0.49F, 0.49F, 0.5F))
                     : CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE;
               }
            }
         );
         if (tX == 7) {
            tH += CFG.PADDING + CFG.BUTTON_HEIGHT / 2;
            tX = 0;
         }
      }

      menuElements.add(
         new Button_Transparent(0, 0, tempW, menuElements.get(menuElements.size() - 1).getPosY() + menuElements.get(menuElements.size() - 1).getHeight(), true)
      );
      this.initMenu(
         new SliderMenuTitle(CFG.langManager.get("Date"), CFG.BUTTON_HEIGHT * 3 / 5, true, false) {
            @Override
            public void draw(SpriteBatch oSB, int iTranslateX, int nPosX, int nPosY, int nWidth, boolean sliderMenuIsActive) {
               ImageManager.getImage(Images.new_game_top_edge_title)
                  .draw2(
                     oSB,
                     nPosX + iTranslateX,
                     nPosY - ImageManager.getImage(Images.new_game_top_edge_title).getHeight() - this.getHeight(),
                     nWidth - ImageManager.getImage(Images.new_game_top_edge_title).getWidth(),
                     this.getHeight()
                  );
               ImageManager.getImage(Images.new_game_top_edge_title)
                  .draw2(
                     oSB,
                     nPosX + nWidth - ImageManager.getImage(Images.new_game_top_edge_title).getWidth() + iTranslateX,
                     nPosY - ImageManager.getImage(Images.new_game_top_edge_title).getHeight() - this.getHeight(),
                     ImageManager.getImage(Images.new_game_top_edge_title).getWidth(),
                     this.getHeight(),
                     true
                  );
               oSB.setColor(new Color(0.05490196F, 0.07058824F, 0.14901961F, 0.775F));
               ImageManager.getImage(Images.gradient)
                  .draw(
                     oSB,
                     nPosX + 2 + iTranslateX,
                     nPosY - (this.getHeight() - 2) * 2 / 3 - ImageManager.getImage(Images.gradient).getHeight(),
                     nWidth - 4,
                     (this.getHeight() - 2) * 2 / 3,
                     false,
                     true
                  );
               oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.6F));
               ImageManager.getImage(Images.gradient)
                  .draw(
                     oSB,
                     nPosX + 2 + iTranslateX,
                     nPosY - CFG.PADDING * 2 - ImageManager.getImage(Images.gradient).getHeight(),
                     nWidth - 4,
                     CFG.PADDING * 2,
                     false,
                     true
                  );
               oSB.setColor(CFG.COLOR_CREATE_NEW_GAME_BOX_PLAYERS);
               ImageManager.getImage(Images.pix255_255_255)
                  .draw2(
                     oSB,
                     nPosX + 2 + iTranslateX,
                     nPosY - ImageManager.getImage(Images.pix255_255_255).getHeight() * 2,
                     nWidth - 4,
                     ImageManager.getImage(Images.pix255_255_255).getHeight()
                  );
               oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.8F));
               ImageManager.getImage(Images.line_32_off1)
                  .draw(oSB, nPosX + 2 + iTranslateX, nPosY - ImageManager.getImage(Images.pix255_255_255).getHeight() * 2, nWidth - 4, 1);
               oSB.setColor(Color.WHITE);
               CFG.fontMain.getData().setScale(0.8F);
               CFG.drawText(
                  oSB,
                  this.getText(),
                  nPosX + nWidth / 2 - (int)(this.getTextWidth() * 0.8F / 2.0F) + iTranslateX,
                  nPosY - this.getHeight() + this.getHeight() / 2 - (int)(this.getTextHeight() * 0.8F / 2.0F),
                  Color.WHITE
               );
               CFG.fontMain.getData().setScale(1.0F);
            }
         },
         CFG.GAME_WIDTH - tempW - CFG.PADDING * 2,
         CFG.BUTTON_HEIGHT * 3 / 5 + CFG.BUTTON_HEIGHT * 2 + CFG.PADDING * 8,
         tempW,
         menuElements.get(menuElements.size() - 1).getPosY() + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING * 2,
         menuElements,
         false,
         true
      );
   }

   @Override
   public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      ImageManager.getImage(Images.new_game_top_edge)
         .draw2(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.new_game_top_edge).getHeight() + iTranslateY,
            this.getWidth() - ImageManager.getImage(Images.new_game_top_edge).getWidth(),
            this.getHeight(),
            false,
            true
         );
      ImageManager.getImage(Images.new_game_top_edge)
         .draw2(
            oSB,
            this.getPosX() + this.getWidth() - ImageManager.getImage(Images.new_game_top_edge).getWidth() + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.new_game_top_edge).getHeight() + iTranslateY,
            ImageManager.getImage(Images.new_game_top_edge).getWidth(),
            this.getHeight(),
            true,
            true
         );
      oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.6F));
      ImageManager.getImage(Images.gradient)
         .draw(
            oSB,
            this.getPosX() + 2 + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY,
            this.getWidth() - 4,
            CFG.PADDING * 3
         );
      oSB.setColor(Color.WHITE);
      super.draw(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
   }

   @Override
   public void actionElement(int iID) {
      if (iID != this.getMenuElementsSize() - 1) {
         if (iID == 0) {
            Game_Calendar.minusMonth_CreateEvent();
            CFG.menuManager.rebuildCreateScenario_Events_Calendar();
            CFG.menuManager.updateCreateScenario_Events_Age_Date();
            ArrayList<String> tMess = new ArrayList<>();
            ArrayList<Color> tColor = new ArrayList<>();
            tMess.add(CFG.gameAges.getAge(CFG.eventsManager.iCreateEvent_Age).getName());
            tColor.add(Color.WHITE);
            tMess.add(Game_Calendar.getCurrentDate_CreateEvent());
            tColor.add(CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE);
            CFG.toast.setInView(tMess, tColor);
         } else if (iID == 1) {
            Game_Calendar.plusMonth_CreateEvent();
            CFG.menuManager.rebuildCreateScenario_Events_Calendar();
            CFG.menuManager.updateCreateScenario_Events_Age_Date();
            ArrayList<String> tMess = new ArrayList<>();
            ArrayList<Color> tColor = new ArrayList<>();
            tMess.add(CFG.gameAges.getAge(CFG.eventsManager.iCreateEvent_Age).getName());
            tColor.add(Color.WHITE);
            tMess.add(Game_Calendar.getCurrentDate_CreateEvent());
            tColor.add(CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE);
            CFG.toast.setInView(tMess, tColor);
         } else if (iID == 2) {
            CFG.toast.setInView(Game_Calendar.getCurrentDate_CreateEvent());
            ArrayList<String> tMess = new ArrayList<>();
            ArrayList<Color> tColor = new ArrayList<>();
            tMess.add(CFG.gameAges.getAge(CFG.eventsManager.iCreateEvent_Age).getName());
            tColor.add(Color.WHITE);
            tMess.add(Game_Calendar.getCurrentDate_CreateEvent());
            tColor.add(CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE);
            CFG.toast.setInView(tMess, tColor);
         } else {
            CFG.eventsManager.iCreateEvent_Day = iID - 2;
            CFG.menuManager.updateCreateScenario_Events_Age_Date();
            ArrayList<String> tMess = new ArrayList<>();
            ArrayList<Color> tColor = new ArrayList<>();
            tMess.add(CFG.gameAges.getAge(CFG.eventsManager.iCreateEvent_Age).getName());
            tColor.add(Color.WHITE);
            tMess.add(Game_Calendar.getCurrentDate_CreateEvent());
            tColor.add(CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE);
            CFG.toast.setInView(tMess, tColor);
         }
      }
   }
}
