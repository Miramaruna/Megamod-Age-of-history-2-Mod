package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

public class Menu_CreateScenario_Events_Edit extends SliderMenu {
   public String sEventTitle;
   public int iEventTitleWidth = 0;
   public String sPicture;
   public float iWidth;
   public float iHeight;
   public int iPictureWidth = 0;

   public Menu_CreateScenario_Events_Edit() {
      int tempW = CFG.CIV_INFO_MENU_WIDTH + CFG.CIV_INFO_MENU_WIDTH * 3 / 4;
      int tempElemH = CFG.BUTTON_HEIGHT * 3 / 4;
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      int tPosY = CFG.PADDING;
      menuElements.add(new Button_NewGameStyle_Left(null, -1, CFG.PADDING, tPosY, (tempW - CFG.PADDING * 2) / 2 + 1, (int)(CFG.BUTTON_HEIGHT * 0.5F), true));
      menuElements.add(
         new Button_NewGameStyle_Right(
            null, -1, tempW - (tempW - CFG.PADDING * 2) / 2 - CFG.PADDING, tPosY, (tempW - CFG.PADDING * 2) / 2, (int)(CFG.BUTTON_HEIGHT * 0.5F), true
         )
      );
      int var7;
      menuElements.add(
         new Button_NewGameStyle(
            CFG.eventsManager.lCreateScenario_Event.getEventName(),
            CFG.PADDING * 2,
            CFG.PADDING,
            var7 = tPosY + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING,
            tempW - CFG.PADDING * 2,
            true
         ) {
            @Override
            public void buildElementHover() {
               if (this.getText().length() > 0) {
                  ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
                  ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
                  nData.add(new MenuElement_Hover_v2_Element_Type_Text(this.getText(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                  nElements.add(new MenuElement_Hover_v2_Element2(nData));
                  nData.clear();
                  this.menuElementHover = new MenuElement_Hover_v2(nElements);
               } else {
                  this.menuElementHover = null;
               }
            }

            @Override
            public void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
               CFG.fontMain.getData().setScale(0.8F);
               CFG.drawText(
                  oSB,
                  Menu_CreateScenario_Events_Edit.this.sEventTitle,
                  this.getPosX() + CFG.PADDING * 2 + iTranslateX,
                  this.getPosY() + this.getHeight() / 2 - (int)(this.getTextHeight() * 0.8F / 2.0F) + iTranslateY,
                  CFG.COLOR_TEXT_OPTIONS_NS
               );
               CFG.fontMain.getData().setScale(1.0F);
               super.drawText(oSB, iTranslateX, iTranslateY, isActive);
            }

            @Override
            public int getTextPos() {
               return super.getTextPos() + Menu_CreateScenario_Events_Edit.this.iEventTitleWidth;
            }
         }
      );
      menuElements.add(
         new Button_New_Game_Players_CivID(
            CFG.eventsManager.lCreateScenario_Event.getCivID(),
            "",
            CFG.PADDING * 2,
            CFG.PADDING,
            tPosY = var7 + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING,
            tempW - CFG.PADDING * 2,
            true
         ) {
            @Override
            public String getTextToDraw() {
               return this.getText() + (this.getCurrent() > 0 ? ": " + CFG.game.getCiv(this.getCurrent()).getCivName() : "");
            }

            @Override
            public void buildElementHover() {
               if (this.getCurrent() > 0) {
                  try {
                     ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
                     ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Recipient") + ": "));
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.game.getCiv(this.getCurrent()).getCivName(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                     nData.add(new MenuElement_Hover_v2_Element_Type_Flag(this.getCurrent(), CFG.PADDING, 0));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                     this.menuElementHover = new MenuElement_Hover_v2(nElements);
                  } catch (IndexOutOfBoundsException var4) {
                     ArrayList<MenuElement_Hover_v2_Element2> nElementsx = new ArrayList<>();
                     ArrayList<MenuElement_Hover_v2_Element_Type> nDatax = new ArrayList<>();
                     nDatax.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("SelectCivilization"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                     nElementsx.add(new MenuElement_Hover_v2_Element2(nDatax));
                     nDatax.clear();
                     this.menuElementHover = new MenuElement_Hover_v2(nElementsx);
                  }
               } else {
                  ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
                  ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
                  nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("SelectCivilization"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                  nElements.add(new MenuElement_Hover_v2_Element2(nData));
                  nData.clear();
                  this.menuElementHover = new MenuElement_Hover_v2(nElements);
               }
            }
         }
      );
      int var9;
      menuElements.add(
         new Button_NewGameStyle_Left(
            null,
            -1,
            CFG.PADDING,
            var9 = tPosY + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING,
            (tempW - CFG.PADDING * 2) / 2 + 1,
            (int)(CFG.BUTTON_HEIGHT * 0.5F),
            true
         )
      );
      menuElements.add(
         new Button_NewGameStyle_Right(
            null, -1, tempW - (tempW - CFG.PADDING * 2) / 2 - CFG.PADDING, var9, (tempW - CFG.PADDING * 2) / 2, (int)(CFG.BUTTON_HEIGHT * 0.5F), true
         )
      );
      menuElements.add(
         new Button_NewGameStyle(
            CFG.langManager.get("Repeatable"),
            -1,
            CFG.PADDING,
            tPosY = var9 + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING,
            tempW - CFG.PADDING * 2,
            true,
            CFG.eventsManager.lCreateScenario_Event.getRepeatable()
         ) {
            @Override
            public boolean getCheckboxState() {
               return CFG.eventsManager.lCreateScenario_Event.getRepeatable();
            }
         }
      );
      int var11;
      menuElements.add(
         new Button_NewGameStyle(
            CFG.langManager.get("WorldEvent"),
            -1,
            CFG.PADDING,
            var11 = tPosY + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING,
            tempW - CFG.PADDING * 2,
            true,
            CFG.eventsManager.lCreateScenario_Event.getRepeatable()
         ) {
            @Override
            public boolean getCheckboxState() {
               return CFG.eventsManager.lCreateScenario_Event.getWorldEvent();
            }
         }
      );
      menuElements.add(
         new Text_BudgetTitle(
            CFG.langManager.get("Triggers"),
            -1,
            0,
            tPosY = var11 + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING,
            tempW,
            CFG.TEXT_HEIGHT + CFG.PADDING * 4
         ) {
            @Override
            public Color getColor(boolean isActive) {
               return isActive
                  ? CFG.COLOR_TEXT_OPTIONS_NS_HOVER
                  : (this.getClickable() ? (this.getIsHovered() ? CFG.COLOR_TEXT_OPTIONS_NS : Color.WHITE) : new Color(0.78F, 0.78F, 0.78F, 0.7F));
            }
         }
      );
      int var13;
      menuElements.add(
         new Button_NewGameStyle(
            CFG.langManager.get("AddNewTrigger"),
            -1,
            CFG.PADDING,
            var13 = tPosY + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING,
            tempW - CFG.PADDING * 2,
            (int)(CFG.BUTTON_HEIGHT * 0.6F),
            true
         )
      );
      tPosY = var13 + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;

      for (int i = 0; i < CFG.eventsManager.lCreateScenario_Event.getTriggersSize(); i++) {
         menuElements.add(
            new Button_NewGameStyle_Left(
               CFG.eventsManager.lCreateScenario_Event.getTrigger(i).getTriggerText(),
               CFG.PADDING * 2,
               CFG.PADDING,
               tPosY,
               tempW - CFG.PADDING * 2 - (int)(CFG.BUTTON_HEIGHT * 0.6F) * 2,
               (int)(CFG.BUTTON_HEIGHT * 0.6F),
               true
            )
         );
         menuElements.add(
            new Button_NewGameStyle_Middle(
               CFG.eventsManager.getEventTypeText(CFG.eventsManager.lCreateScenario_Event.getTrigger(i).triggerType),
               -1,
               tempW - CFG.PADDING - (int)(CFG.BUTTON_HEIGHT * 0.6F) * 2,
               tPosY,
               (int)(CFG.BUTTON_HEIGHT * 0.6F),
               (int)(CFG.BUTTON_HEIGHT * 0.6F),
               true
            ) {
               @Override
               public void buildElementHover() {
                  ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
                  ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
                  nData.add(new MenuElement_Hover_v2_Element_Type_Text(this.getText(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                  nElements.add(new MenuElement_Hover_v2_Element2(nData));
                  nData.clear();
                  this.menuElementHover = new MenuElement_Hover_v2(nElements);
               }
            }
         );
         menuElements.add(
            new Button_Game_NewGameBoxStyle_RIGHT_Remove(
               tempW - CFG.PADDING - (int)(CFG.BUTTON_HEIGHT * 0.6F), tPosY, (int)(CFG.BUTTON_HEIGHT * 0.6F), (int)(CFG.BUTTON_HEIGHT * 0.6F), true
            ) {
               @Override
               public void buildElementHover() {
                  ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
                  ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
                  nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Delete"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                  nElements.add(new MenuElement_Hover_v2_Element2(nData));
                  nData.clear();
                  this.menuElementHover = new MenuElement_Hover_v2(nElements);
               }
            }
         );
         tPosY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
      }

      menuElements.add(
         new Text_BudgetTitle(CFG.langManager.get("PopUp"), -1, 0, tPosY, tempW, CFG.TEXT_HEIGHT + CFG.PADDING * 4) {
            @Override
            public Color getColor(boolean isActive) {
               return isActive
                  ? CFG.COLOR_TEXT_OPTIONS_NS_HOVER
                  : (this.getClickable() ? (this.getIsHovered() ? CFG.COLOR_TEXT_OPTIONS_NS : Color.WHITE) : new Color(0.78F, 0.78F, 0.78F, 0.7F));
            }
         }
      );
      int var15;
      menuElements.add(
         new Button_NewGameStyle(
            CFG.langManager.get("ShowPopUp"),
            -1,
            CFG.PADDING,
            var15 = tPosY + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING,
            tempW - CFG.PADDING * 2,
            true,
            CFG.eventsManager.lCreateScenario_Event.getEvent_PopUp().showPopUp
         ) {
            @Override
            public boolean getCheckboxState() {
               return CFG.eventsManager.lCreateScenario_Event.getEvent_PopUp().showPopUp;
            }
         }
      );
      menuElements.add(
         new Text_Scrollable(
            CFG.eventsManager.lCreateScenario_Event.getEvent_PopUp().sText,
            CFG.PADDING * 2,
            tPosY = var15 + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING,
            tempW - CFG.PADDING * 4,
            (int)(CFG.BUTTON_HEIGHT * 0.6F),
            CFG.COLOR_TEXT_MODIFIER_NEUTRAL,
            0.8F
         ) {
            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Description") + ": ", CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get(this.getText())));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
         }
      );
      int var17;
      menuElements.add(
         new Button_NewGameStyle(
            CFG.eventsManager.lCreateScenario_Event.getEventPicture(),
            CFG.PADDING * 2,
            CFG.PADDING,
            var17 = tPosY + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING,
            tempW - CFG.PADDING * 2,
            true
         ) {
            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Path") + ": ", CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
               nData.add(new MenuElement_Hover_v2_Element_Type_Text("map/" + CFG.map.getFile_ActiveMap_Path() + "scenarios/SCENARIO_TAG/events/"));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Path") + ": ", CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
               nData.add(new MenuElement_Hover_v2_Element_Type_Text("UI/events/" + this.getText()));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }

            @Override
            public void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
               CFG.fontMain.getData().setScale(0.8F);
               CFG.drawText(
                  oSB,
                  Menu_CreateScenario_Events_Edit.this.sPicture,
                  this.getPosX() + CFG.PADDING * 2 + iTranslateX,
                  this.getPosY() + this.getHeight() / 2 - (int)(this.getTextHeight() * 0.8F / 2.0F) + iTranslateY,
                  CFG.COLOR_TEXT_OPTIONS_NS
               );
               CFG.fontMain.getData().setScale(1.0F);
               super.drawText(oSB, iTranslateX, iTranslateY, isActive);
            }

            @Override
            public int getTextPos() {
               return super.getTextPos() + Menu_CreateScenario_Events_Edit.this.iPictureWidth;
            }
         }
      );
      menuElements.add(
         new Button_NewGameStyle(
            Integer.toString(CFG.eventsManager.lCreateScenario_Event.getEventPictureWidth()),
            CFG.PADDING,
            CFG.PADDING,
            tPosY = var17 + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING,
            (tempW - CFG.PADDING * 2) / 2,
            true
         ) {
            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               nData.add(
                  new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Увы не получилось сделать") + " :( ", CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE)
               );
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }

            @Override
            public void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
               CFG.fontMain.getData().setScale(0.8F);
               CFG.drawText(
                  oSB,
                  CFG.langManager.get("Width") + ":",
                  this.getPosX() + CFG.PADDING * 2 + iTranslateX,
                  this.getPosY() + this.getHeight() / 2 - (int)(this.getTextHeight() * 0.8F / 2.0F) + iTranslateY,
                  CFG.COLOR_TEXT_OPTIONS_NS
               );
               CFG.fontMain.getData().setScale(1.0F);
               super.drawText(oSB, iTranslateX, iTranslateY, isActive);
            }

            @Override
            public int getTextPos() {
               return super.getTextPos() + Menu_CreateScenario_Events_Edit.this.iPictureWidth;
            }
         }
      );
      menuElements.add(
         new Button_NewGameStyle(
            Integer.toString(CFG.eventsManager.lCreateScenario_Event.getEventPictureHeight()),
            CFG.PADDING * 2,
            tempW - (tempW - CFG.PADDING * 2) / 2 - CFG.PADDING,
            tPosY,
            (tempW - CFG.PADDING * 2) / 2 + 1,
            true
         ) {
            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Path") + ": ", CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
               nData.add(new MenuElement_Hover_v2_Element_Type_Text("map/" + CFG.map.getFile_ActiveMap_Path() + "scenarios/SCENARIO_TAG/events/"));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Path") + ": ", CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
               nData.add(new MenuElement_Hover_v2_Element_Type_Text("UI/events/" + this.getText()));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }

            @Override
            public void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
               CFG.fontMain.getData().setScale(0.8F);
               CFG.drawText(
                  oSB,
                  CFG.langManager.get("Height") + ":",
                  this.getPosX() + CFG.PADDING * 2 + iTranslateX,
                  this.getPosY() + this.getHeight() / 2 - (int)(this.getTextHeight() * 0.8F / 2.0F) + iTranslateY,
                  CFG.COLOR_TEXT_OPTIONS_NS
               );
               CFG.fontMain.getData().setScale(1.0F);
               super.drawText(oSB, iTranslateX, iTranslateY, isActive);
            }

            @Override
            public int getTextPos() {
               return super.getTextPos() + Menu_CreateScenario_Events_Edit.this.iPictureWidth;
            }
         }
      );
      int var19;
      menuElements.add(
         new Text_BudgetTitle(
            CFG.langManager.get("Outcomes: "),
            -1,
            0,
            var19 = tPosY + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING,
            tempW,
            CFG.TEXT_HEIGHT + CFG.PADDING * 4
         ) {
            @Override
            public Color getColor(boolean isActive) {
               return isActive
                  ? CFG.COLOR_TEXT_OPTIONS_NS_HOVER
                  : (this.getClickable() ? (this.getIsHovered() ? CFG.COLOR_TEXT_OPTIONS_NS : Color.WHITE) : new Color(0.78F, 0.78F, 0.78F, 0.7F));
            }
         }
      );
      menuElements.add(
         new Button_NewGameStyle(
            CFG.langManager.get("AddNewOutcome"),
            -1,
            CFG.PADDING,
            tPosY = var19 + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING,
            tempW - CFG.PADDING * 2,
            (int)(CFG.BUTTON_HEIGHT * 0.6F),
            true
         )
      );
      tPosY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;

      for (int var6 = 0; var6 < CFG.eventsManager.lCreateScenario_Event.lDecisions.size(); var6++) {
         int var10004 = CFG.PADDING * 2;
         int var10007 = tempW - CFG.PADDING * 2 - (int)(CFG.BUTTON_HEIGHT * 0.6F);
         menuElements.add(
            new Button_NewGameStyle_Left(
               CFG.eventsManager.lCreateScenario_Event.lDecisions.get(var6).sTitle,
               var10004,
               CFG.PADDING,
               tPosY,
               var10007,
               (int)(CFG.BUTTON_HEIGHT * 0.6F),
               true
            )
         );
         menuElements.add(
            new Button_Game_NewGameBoxStyle_RIGHT_Remove(
               tempW - CFG.PADDING - (int)(CFG.BUTTON_HEIGHT * 0.6F), tPosY, (int)(CFG.BUTTON_HEIGHT * 0.6F), (int)(CFG.BUTTON_HEIGHT * 0.6F), true
            )
         );
         tPosY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
      }

      this.initMenu(
         new SliderMenuTitle(null, CFG.BUTTON_HEIGHT * 3 / 5, false, false) {
            @Override
            public void draw(SpriteBatch oSB, int iTranslateX, int nPosX, int nPosY, int nWidth, boolean sliderMenuIsActive) {
               ImageManager.getImage(Images.dialog_title)
                  .draw2(
                     oSB,
                     nPosX - 2 + iTranslateX,
                     nPosY - this.getHeight() - ImageManager.getImage(Images.dialog_title).getHeight(),
                     nWidth + 4,
                     this.getHeight()
                  );
               oSB.setColor(new Color(0.78431374F, 0.0F, 0.0F, 0.165F));
               ImageManager.getImage(Images.line_32_off1)
                  .draw(
                     oSB,
                     nPosX + iTranslateX,
                     nPosY - this.getHeight() + 2 - ImageManager.getImage(Images.line_32_off1).getHeight(),
                     nWidth,
                     this.getHeight() - 2,
                     false,
                     true
                  );
               oSB.setColor(new Color(0.78431374F, 0.0F, 0.0F, 0.375F));
               ImageManager.getImage(Images.gradient)
                  .draw(
                     oSB,
                     nPosX + iTranslateX,
                     nPosY - this.getHeight() * 2 / 3 - ImageManager.getImage(Images.gradient).getHeight(),
                     nWidth,
                     this.getHeight() * 2 / 3,
                     false,
                     true
                  );
               oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.65F));
               ImageManager.getImage(Images.gradient)
                  .draw(oSB, nPosX + iTranslateX, nPosY - CFG.PADDING - ImageManager.getImage(Images.gradient).getHeight(), nWidth, CFG.PADDING, false, true);
               oSB.setColor(CFG.COLOR_NEW_GAME_EDGE_LINE);
               ImageManager.getImage(Images.pix255_255_255)
                  .draw(oSB, nPosX + iTranslateX, nPosY - 1 - ImageManager.getImage(Images.pix255_255_255).getHeight(), nWidth, 1);
               oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.55F));
               ImageManager.getImage(Images.line_32_off1)
                  .draw(oSB, nPosX + iTranslateX, nPosY - 2 - ImageManager.getImage(Images.line_32_off1).getHeight(), nWidth, 1);
               oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.8F));
               ImageManager.getImage(Images.line_32_off1)
                  .draw(oSB, nPosX + iTranslateX, nPosY - 1 - ImageManager.getImage(Images.line_32_off1).getHeight(), nWidth, 1);
               oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.45F));
               ImageManager.getImage(Images.slider_gradient)
                  .draw(oSB, nPosX + iTranslateX, nPosY - 1 - ImageManager.getImage(Images.slider_gradient).getHeight(), nWidth / 2, 1);
               ImageManager.getImage(Images.slider_gradient)
                  .draw(
                     oSB,
                     nPosX + nWidth - nWidth / 2 + iTranslateX,
                     nPosY - 1 - ImageManager.getImage(Images.slider_gradient).getHeight(),
                     nWidth / 2,
                     1,
                     true,
                     false
                  );
               oSB.setColor(Color.WHITE);
               CFG.fontMain.getData().setScale(0.8F);
               CFG.drawText(
                  oSB,
                  this.getText(),
                  nPosX + (int)(nWidth - this.getTextWidth() * 0.8F) / 2 + iTranslateX,
                  2 + nPosY - this.getHeight() + (int)(this.getHeight() - this.getTextHeight() * 0.8F) / 2,
                  Color.WHITE
               );
               CFG.fontMain.getData().setScale(1.0F);
            }
         },
         CFG.GAME_WIDTH - tempW,
         CFG.BUTTON_HEIGHT + CFG.PADDING * 3 + CFG.BUTTON_HEIGHT * 3 / 5,
         tempW,
         Math.min(tPosY, CFG.GAME_HEIGHT - (CFG.BUTTON_HEIGHT + CFG.PADDING * 3 + CFG.BUTTON_HEIGHT * 3 / 5) - CFG.PADDING * 2),
         menuElements
      );
      this.updateLanguage();
      this.setVisible(false);
   }

   @Override
   public void updateLanguage() {
      this.getTitle().setText(CFG.langManager.get("AddNewEvent"));
      this.sEventTitle = CFG.langManager.get("EventTitle") + ": ";
      CFG.glyphLayout.setText(CFG.fontMain, this.sEventTitle);
      this.iEventTitleWidth = (int)(CFG.glyphLayout.width * 0.8F);
      this.sPicture = CFG.langManager.get("Picture") + ": ";
      CFG.glyphLayout.setText(CFG.fontMain, this.sPicture);
      this.iPictureWidth = (int)(CFG.glyphLayout.width * 0.8F);
      this.getMenuElement(0).setText(CFG.langManager.get("Back"));
      this.getMenuElement(1).setText(CFG.langManager.get("SaveEvent"));
      this.getMenuElement(3).setText(CFG.langManager.get("Recipient"));
      CFG.eventsManager.iCreateEvent_Day = CFG.eventsManager.lCreateScenario_Event.getEventDate_Since().iEventDay;
      CFG.eventsManager.iCreateEvent_Month = CFG.eventsManager.lCreateScenario_Event.getEventDate_Since().iEventMonth;
      CFG.eventsManager.iCreateEvent_Year = CFG.eventsManager.lCreateScenario_Event.getEventDate_Since().iEventYear;
      this.getMenuElement(4)
         .setText(
            CFG.langManager.get("Since")
               + ": "
               + (CFG.eventsManager.iCreateEvent_Year == 9999999 ? CFG.langManager.get("NoDate") : Game_Calendar.getCurrentDate_CreateEvent())
         );
      CFG.eventsManager.iCreateEvent_Day = CFG.eventsManager.lCreateScenario_Event.getEventDate_Until().iEventDay;
      CFG.eventsManager.iCreateEvent_Month = CFG.eventsManager.lCreateScenario_Event.getEventDate_Until().iEventMonth;
      CFG.eventsManager.iCreateEvent_Year = CFG.eventsManager.lCreateScenario_Event.getEventDate_Until().iEventYear;
      this.getMenuElement(5)
         .setText(
            CFG.langManager.get("Until")
               + ": "
               + (CFG.eventsManager.iCreateEvent_Year == 9999999 ? CFG.langManager.get("NoDate") : Game_Calendar.getCurrentDate_CreateEvent())
         );
   }

   @Override
   public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      ImageManager.getImage(Images.new_game_top_edge_line)
         .draw2(
            oSB,
            this.getPosX() - 2 + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.new_game_top_edge_line).getHeight() + iTranslateY,
            this.getWidth() + 2,
            this.getHeight(),
            false,
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
      ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() - 2 + iTranslateX, this.getPosY() + this.getHeight(), this.getWidth() + 2);
      oSB.setColor(Color.WHITE);
   }

   public final void saveEditData() {
      CFG.eventsManager.lCreateScenario_Event.setEventName(this.getMenuElement(2).getText());
      CFG.eventsManager.lCreateScenario_Event.getEvent_PopUp().sText = this.getMenuElement(12 + CFG.eventsManager.lCreateScenario_Event.getTriggersSize() * 3)
         .getText();
      CFG.eventsManager
         .lCreateScenario_Event
         .setEventPicture(this.getMenuElement(13 + CFG.eventsManager.lCreateScenario_Event.getTriggersSize() * 3).getText());
      CFG.eventsManager
         .lCreateScenario_Event
         .setEventPictureWidth(Integer.valueOf(this.getMenuElement(14 + CFG.eventsManager.lCreateScenario_Event.getTriggersSize() * 3).getText()));
      CFG.eventsManager
         .lCreateScenario_Event
         .setEventPictureHeight(Integer.valueOf(this.getMenuElement(15 + CFG.eventsManager.lCreateScenario_Event.getTriggersSize() * 3).getText()));
   }

   @Override
   public void actionElement(int iID) {
      if (iID >= 10 && iID < 10 + CFG.eventsManager.lCreateScenario_Event.getTriggersSize() * 3) {
         iID -= 10;
         if (iID % 3 == 0) {
            CFG.eventsManager.iCreateEvent_EditTriggerID = iID / 3;
            CFG.menuManager.setViewID(Menu.eCREATE_SCENARIO_EVENTS_TRIGGER);
         } else if (iID % 3 == 1) {
            CFG.eventsManager.iCreateEvent_EditTriggerID = iID / 3;
            CFG.eventsManager.lCreateScenario_Event.getTrigger(CFG.eventsManager.iCreateEvent_EditTriggerID).triggerType = CFG.eventsManager
                     .lCreateScenario_Event
                     .getTrigger(CFG.eventsManager.iCreateEvent_EditTriggerID)
                     .triggerType
                  == Event_Type.AND
               ? Event_Type.NOT
               : (
                  CFG.eventsManager.lCreateScenario_Event.getTrigger(CFG.eventsManager.iCreateEvent_EditTriggerID).triggerType == Event_Type.NOT
                     ? Event_Type.OR
                     : Event_Type.AND
               );
            this.getMenuElement(iID + 10)
               .setText(
                  CFG.eventsManager
                     .getEventTypeText(CFG.eventsManager.lCreateScenario_Event.getTrigger(CFG.eventsManager.iCreateEvent_EditTriggerID).triggerType)
               );
            CFG.toast.setInView(this.getMenuElement(iID + 10).getText(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE);
         } else {
            CFG.eventsManager.lCreateScenario_Event.removeTrigger(iID / 3);
            this.saveEditData();
            CFG.menuManager.setViewID(Menu.eCREATE_SCENARIO_EVENTS);
            CFG.menuManager.setVisibleCreateScenario_Events_Edit(true);
         }
      } else if (iID == 11 + CFG.eventsManager.lCreateScenario_Event.getTriggersSize() * 3) {
         CFG.eventsManager.lCreateScenario_Event.getEvent_PopUp().showPopUp = !CFG.eventsManager.lCreateScenario_Event.getEvent_PopUp().showPopUp;
      } else if (iID == 12 + CFG.eventsManager.lCreateScenario_Event.getTriggersSize() * 3) {
         CFG.showKeyboard();
      } else if (iID == 13 + CFG.eventsManager.lCreateScenario_Event.getTriggersSize() * 3) {
         CFG.showKeyboard();
      } else if (iID == 14 + CFG.eventsManager.lCreateScenario_Event.getTriggersSize() * 3) {
         CFG.showKeyboard();
      } else if (iID == 15 + CFG.eventsManager.lCreateScenario_Event.getTriggersSize() * 3) {
         CFG.showKeyboard();
      } else if (iID == 17 + CFG.eventsManager.lCreateScenario_Event.getTriggersSize() * 3) {
         this.saveEditData();
         CFG.eventsManager.lCreateScenario_Event.lDecisions.add(new Event_Decision());
         CFG.eventsManager.iCreateEvent_EditTriggerID = CFG.eventsManager.lCreateScenario_Event.lDecisions.size() - 1;
         CFG.menuManager.setViewID(Menu.eCREATE_SCENARIO_EVENTS_DECISION);
      } else if (iID >= 18 + CFG.eventsManager.lCreateScenario_Event.getTriggersSize() * 3) {
         if ((iID = iID - (19 + CFG.eventsManager.lCreateScenario_Event.getTriggersSize() * 3)) % 2 == 0) {
            this.saveEditData();
            CFG.eventsManager.iCreateEvent_EditTriggerID = iID / 2;
            CFG.menuManager.setViewID(Menu.eCREATE_SCENARIO_EVENTS_DECISION);
         } else {
            this.saveEditData();
            CFG.eventsManager.iCreateEvent_EditTriggerID = iID / 2;
            CFG.eventsManager.lCreateScenario_Event.lDecisions.remove(CFG.eventsManager.iCreateEvent_EditTriggerID);
            CFG.menuManager.setViewID(Menu.eCREATE_SCENARIO_EVENTS);
            CFG.menuManager.setVisibleCreateScenario_Events_Edit(true);
         }
      } else {
         switch (iID) {
            case 0:
               CFG.menuManager.getKeyboard().setVisible(false);
               this.saveEditData();
               CFG.setDialogType(Dialog.CREATE_SCENARIO_EVENTS_EDIT_BACK);
               break;
            case 1:
               CFG.menuManager.getKeyboard().setVisible(false);
               this.saveEditData();
               CFG.setDialogType(Dialog.CREATE_SCENARIO_EVENTS_EDIT_SAVE);
               break;
            case 2:
               CFG.showKeyboard();
               break;
            case 3:
               this.saveEditData();
               CFG.eventsManager.eSelectCivAction = Event_SelectCivAction.SELECT_RECIPENT;
               CFG.menuManager.setViewID(Menu.eCREATE_SCENARIO_EVENTS_SELECT_CIV);
               break;
            case 4:
               this.saveEditData();
               CFG.eventsManager.setSinceDate = true;
               CFG.eventsManager.iCreateEvent_Day = CFG.eventsManager.lCreateScenario_Event.getEventDate_Since().iEventDay;
               CFG.eventsManager.iCreateEvent_Month = CFG.eventsManager.lCreateScenario_Event.getEventDate_Since().iEventMonth;
               CFG.eventsManager.iCreateEvent_Year = CFG.eventsManager.lCreateScenario_Event.getEventDate_Since().iEventYear;
               CFG.eventsManager.iCreateEvent_Age = CFG.eventsManager.lCreateScenario_Event.getEventDate_Since().iEventYear == 9999999
                  ? Game_Calendar.CURRENT_AGEID
                  : CFG.gameAges.getAgeOfYear(CFG.eventsManager.lCreateScenario_Event.getEventDate_Since().iEventYear);
               CFG.menuManager.setViewID(Menu.eCREATE_SCENARIO_EVENTS_DATE);
               CFG.menuManager.updateCreateScanerio_Events_Slider();
               break;
            case 5:
               this.saveEditData();
               CFG.eventsManager.setSinceDate = false;
               CFG.eventsManager.iCreateEvent_Day = CFG.eventsManager.lCreateScenario_Event.getEventDate_Until().iEventDay;
               CFG.eventsManager.iCreateEvent_Month = CFG.eventsManager.lCreateScenario_Event.getEventDate_Until().iEventMonth;
               CFG.eventsManager.iCreateEvent_Year = CFG.eventsManager.lCreateScenario_Event.getEventDate_Until().iEventYear;
               CFG.eventsManager.iCreateEvent_Age = CFG.eventsManager.lCreateScenario_Event.getEventDate_Until().iEventYear == 9999999
                  ? Game_Calendar.CURRENT_AGEID
                  : CFG.gameAges.getAgeOfYear(CFG.eventsManager.lCreateScenario_Event.getEventDate_Until().iEventYear);
               CFG.menuManager.setViewID(Menu.eCREATE_SCENARIO_EVENTS_DATE);
               CFG.menuManager.updateCreateScanerio_Events_Slider();
               break;
            case 6:
               CFG.eventsManager.lCreateScenario_Event.setRepeatable(!CFG.eventsManager.lCreateScenario_Event.getRepeatable());
               break;
            case 7:
               CFG.eventsManager.lCreateScenario_Event.setWorldEvent(!CFG.eventsManager.lCreateScenario_Event.getWorldEvent());
               if (CFG.eventsManager.lCreateScenario_Event.getWorldEvent()) {
                  CFG.eventsManager.lCreateScenario_Event.setCivID(0);
               }
            case 8:
            default:
               break;
            case 9:
               CFG.eventsManager.lCreateScenario_Event.addNewTrigger();
               CFG.eventsManager.iCreateEvent_EditTriggerID = CFG.eventsManager.lCreateScenario_Event.getTriggersSize() - 1;
               CFG.menuManager.setViewID(Menu.eCREATE_SCENARIO_EVENTS_TRIGGER);
         }
      }
   }
}
