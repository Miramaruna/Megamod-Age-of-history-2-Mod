package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.GdxRuntimeException;
import java.util.ArrayList;
import java.util.List;

public class Menu_InGame_Event extends SliderMenu {
   public static int EVENT_ID = 0;
   public Image lPicture = null;
   public int fPictureWidth;
   public int fPictureHeight;
   public static final float DATE_FONT_SCALE = 0.65F;
   public String sEventDate = "";
   public int iEventDateWidth = 0;

   public Menu_InGame_Event(int tInit) {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      int tempWidth = (int)(600.0F * CFG.GUI_SCALE) + CFG.PADDING * 2;
      if (tempWidth > CFG.GAME_WIDTH) {
         tempWidth = CFG.GAME_WIDTH - CFG.PADDING * 4;
      }

      int tempMenuPosY = ImageManager.getImage(Images.top_flag_frame).getHeight() + CFG.PADDING * 4 + CFG.BUTTON_HEIGHT * 3 / 5 + CFG.PADDING * 2;
      this.initMenu(null, CFG.GAME_WIDTH / 2 - tempWidth / 2, tempMenuPosY, tempWidth, 5, menuElements, false, false);
   }

   public Menu_InGame_Event() {
      CFG.soundsManager.playNewsSFX();
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      int tempWidth = (int)(600.0F * CFG.GUI_SCALE) + CFG.PADDING * 2;
      if (tempWidth > CFG.GAME_WIDTH) {
         tempWidth = CFG.GAME_WIDTH - CFG.PADDING * 4;
      }

      int tY = CFG.PADDING;
      menuElements.add(new Button_Transparent(CFG.PADDING, tY, (int)(600.0F * CFG.GUI_SCALE), (int)(120.0F * CFG.GUI_SCALE), true));
      int var11;
      menuElements.add(
         new TextSlider(
            CFG.PADDING,
            var11 = tY + menuElements.get(menuElements.size() - 1).getHeight(),
            tempWidth - CFG.PADDING * 2,
            CFG.TEXT_HEIGHT + CFG.PADDING * 4,
            CFG.BUTTON_HEIGHT * 2,
            0.8F
         ) {
            @Override
            public Color getColor(boolean isActive) {
               return isActive
                  ? CFG.COLOR_BUTTON_GAME_TEXT_IMPORTANT_ACTIVE
                  : (this.getIsHovered() ? CFG.COLOR_BUTTON_GAME_TEXT_IMPORTANT_HOVER : CFG.COLOR_BUTTON_GAME_TEXT_IMPORTANT);
            }

            @Override
            public void drawBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
               oSB.setColor(new Color(CFG.COLOR_GRADIENT_TITLE_BLUE.r, CFG.COLOR_GRADIENT_TITLE_BLUE.g, CFG.COLOR_GRADIENT_TITLE_BLUE.b, 0.25F));
               ImageManager.getImage(Images.line_32_off1)
                  .draw(
                     oSB,
                     this.getPosX() + iTranslateX,
                     this.getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
                     this.getWidth(),
                     this.getHeight()
                  );
               oSB.setColor(Color.WHITE);
               oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.55F));
               ImageManager.getImage(Images.gradient)
                  .draw(
                     oSB,
                     this.getPosX() + iTranslateX,
                     this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY,
                     this.getWidth(),
                     this.getHeight() * 3 / 5,
                     false,
                     false
                  );
               oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.275F));
               ImageManager.getImage(Images.slider_gradient)
                  .draw(
                     oSB,
                     this.getPosX() + iTranslateX,
                     this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY,
                     this.getWidth() / 4,
                     this.getHeight(),
                     false,
                     false
                  );
               ImageManager.getImage(Images.slider_gradient)
                  .draw(
                     oSB,
                     this.getPosX() + this.getWidth() - this.getWidth() / 4 + iTranslateX,
                     this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY,
                     this.getWidth() / 4,
                     this.getHeight(),
                     true,
                     false
                  );
               oSB.setColor(new Color(CFG.COLOR_GRADIENT_TITLE_BLUE.r, CFG.COLOR_GRADIENT_TITLE_BLUE.g, CFG.COLOR_GRADIENT_TITLE_BLUE.b, 0.3F));
               ImageManager.getImage(Images.gradient)
                  .draw(
                     oSB,
                     this.getPosX() + iTranslateX,
                     this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY,
                     this.getWidth(),
                     CFG.PADDING,
                     false,
                     false
                  );
               ImageManager.getImage(Images.gradient)
                  .draw(
                     oSB,
                     this.getPosX() + iTranslateX,
                     this.getPosY() + this.getHeight() - CFG.PADDING - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY,
                     this.getWidth(),
                     CFG.PADDING,
                     false,
                     true
                  );
               oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.15F));
               ImageManager.getImage(Images.line_32_off1)
                  .draw(
                     oSB,
                     this.getPosX() + iTranslateX,
                     this.getPosY() + this.getHeight() - 1 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
                     this.getWidth() - 4,
                     1
                  );
               oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.7F));
               ImageManager.getImage(Images.line_32_off1)
                  .draw(
                     oSB,
                     this.getPosX() + iTranslateX,
                     this.getPosY() + this.getHeight() - 2 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
                     this.getWidth() - 4,
                     1
                  );
               oSB.setColor(Color.BLACK);
               ImageManager.getImage(Images.line_32_vertical)
                  .draw(
                     oSB,
                     this.getPosX() + iTranslateX,
                     this.getPosY() - ImageManager.getImage(Images.line_32_vertical).getHeight() + iTranslateY,
                     1,
                     this.getHeight()
                  );
               ImageManager.getImage(Images.line_32_vertical)
                  .draw(
                     oSB,
                     this.getPosX() + this.getWidth() - 1 + iTranslateX,
                     this.getPosY() - ImageManager.getImage(Images.line_32_vertical).getHeight() + iTranslateY,
                     1,
                     this.getHeight()
                  );
               oSB.setColor(Color.WHITE);
            }
         }
      );
      String tNewsText = CFG.langManager.get(CFG.eventsManager.getEvent(EVENT_ID).getEvent_PopUp().sText);

      for (String tPart : tNewsText.split("\n")) {
         menuElements.get(1).addText(tPart, CFG.PADDING * 3 / 2);
      }
      tY = var11 + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING * 2;

      for (int i = 0; i < CFG.eventsManager.getEvent(EVENT_ID).lDecisions.size(); i++) {
         menuElements.add(
            new Button_New_Game_Players_Anim(
               CFG.langManager.get(CFG.eventsManager.getEvent(EVENT_ID).lDecisions.get(i).sTitle),
               -1,
               CFG.PADDING,
               tY,
               tempWidth - CFG.PADDING * 2,
               CFG.BUTTON_HEIGHT / 2,
               true
            ) {
               int iCurrent = 0;

               @Override
               public Color getColor(boolean isActive) {
                  return isActive
                     ? CFG.COLOR_TEXT_OPTIONS_LEFT_NS_ACTIVE
                     : (
                        this.getClickable()
                           ? (this.getIsHovered() ? CFG.COLOR_TEXT_OPTIONS_LEFT_NS_HOVER : CFG.COLOR_TEXT_OPTIONS_LEFT_NS)
                           : CFG.COLOR_BUTTON_MENU_TEXT_NOT_CLICKABLE
                     );
               }

               @Override
               public void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                  oSB.setColor(new Color(1.0F, 1.0F, 1.0F, this.getIsHovered() ? 1.0F : 0.8F));
                  super.drawButtonBG(oSB, iTranslateX, iTranslateY, isActive);
                  oSB.setColor(Color.WHITE);
               }

               @Override
               public int getCurrent() {
                  return this.iCurrent;
               }

               @Override
               public void setCurrent(int nCurrent) {
                  this.iCurrent = nCurrent;
               }

               @Override
               public void buildElementHover() {
                  ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
                  ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
                  nData.add(new MenuElement_Hover_v2_Element_Type_Text(this.getText(), CFG.COLOR_TEXT_NUM_OF_PROVINCES));
                  nElements.add(new MenuElement_Hover_v2_Element2(nData));
                  nData.clear();

                  for (int ix = 0; ix < CFG.eventsManager.getEvent(Menu_InGame_Event.EVENT_ID).lDecisions.get(this.getCurrent()).lOutcomes.size(); ix++) {
                     List<MenuElement_Hover_v2_Element2> tempElements = CFG.eventsManager
                           .getEvent(Menu_InGame_Event.EVENT_ID)
                           .lDecisions
                           .get(this.getCurrent())
                        .lOutcomes
                        .get(ix)
                        .getHoverText();

                     for (int j = 0; j < tempElements.size(); j++) {
                        nElements.add(tempElements.get(j));
                     }

                     tempElements.clear();
                     Object var6 = null;
                  }

                  this.menuElementHover = new MenuElement_Hover_v2(nElements);
               }
            }
         );
         menuElements.get(menuElements.size() - 1).setCurrent(i);
         tY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
      }

      int tempMenuPosY = ImageManager.getImage(Images.top_flag_frame).getHeight() + CFG.PADDING * 4 + CFG.BUTTON_HEIGHT * 3 / 5 + CFG.PADDING * 2;
      this.initMenu(
         new SliderMenuTitle("", CFG.BUTTON_HEIGHT * 3 / 5, true, false) {
            @Override
            public void draw(SpriteBatch oSB, int iTranslateX, int nPosX, int nPosY, int nWidth, boolean sliderMenuIsActive) {
               ImageManager.getImage(Images.dialog_title)
                  .draw2(
                     oSB,
                     nPosX - 2 + iTranslateX,
                     nPosY - this.getHeight() - ImageManager.getImage(Images.dialog_title).getHeight(),
                     nWidth + 4 - ImageManager.getImage(Images.dialog_title).getWidth(),
                     this.getHeight()
                  );
               ImageManager.getImage(Images.dialog_title)
                  .draw2(
                     oSB,
                     nPosX + nWidth + 2 - ImageManager.getImage(Images.dialog_title).getWidth() + iTranslateX,
                     nPosY - this.getHeight() - ImageManager.getImage(Images.dialog_title).getHeight(),
                     ImageManager.getImage(Images.dialog_title).getWidth(),
                     this.getHeight(),
                     true,
                     false
                  );
               oSB.setColor(new Color(0.06666667F, 0.3764706F, 0.7529412F, 0.165F));
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
               oSB.setColor(new Color(0.06666667F, 0.3764706F, 0.7529412F, 0.375F));
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
               CFG.game
                  .getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
                  .getFlag()
                  .draw(
                     oSB,
                     nPosX + (int)(nWidth - (this.getTextWidth() * 0.8F + ImageManager.getImage(Images.flag_rect).getWidth() + CFG.PADDING)) / 2 + iTranslateX,
                     2
                        + nPosY
                        - this.getHeight()
                        + this.getHeight() / 2
                        - ImageManager.getImage(Images.flag_rect).getHeight() / 2
                        - CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getFlag().getHeight(),
                     CFG.CIV_FLAG_WIDTH,
                     CFG.CIV_FLAG_HEIGHT
                  );
               ImageManager.getImage(Images.flag_rect)
                  .draw(
                     oSB,
                     nPosX + (int)(nWidth - (this.getTextWidth() * 0.8F + ImageManager.getImage(Images.flag_rect).getWidth() + CFG.PADDING)) / 2 + iTranslateX,
                     2 + nPosY - this.getHeight() + this.getHeight() / 2 - ImageManager.getImage(Images.flag_rect).getHeight() / 2
                  );
               CFG.fontMain.getData().setScale(0.8F);
               CFG.drawText(
                  oSB,
                  this.getText(),
                  nPosX
                     + (int)(nWidth - (this.getTextWidth() * 0.8F + ImageManager.getImage(Images.flag_rect).getWidth() + CFG.PADDING)) / 2
                     + ImageManager.getImage(Images.flag_rect).getWidth()
                     + CFG.PADDING
                     + iTranslateX,
                  2 + nPosY - this.getHeight() + (int)(this.getHeight() - this.getTextHeight() * 0.8F) / 2,
                  Color.WHITE
               );
               CFG.fontMain.getData().setScale(1.0F);
            }
         },
         CFG.GAME_WIDTH / 2 - tempWidth / 2,
         tempMenuPosY,
         tempWidth,
         menuElements.get(menuElements.size() - 1).getPosY() + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING,
         menuElements,
         true,
         false
      );
      this.updateLanguage();

      try {
         this.lPicture = CFG.eventsManager.getEvent(EVENT_ID).getEventPicture().length() == 0
            ? new Image(
               new Texture(
                  Gdx.files
                     .internal(
                        "map/" + CFG.map.getFile_ActiveMap_Path() + "scenarios/" + CFG.game.getGameScenarios().sActiveScenarioTag + "/events/default.png"
                     )
               ),
               Texture.TextureFilter.Linear
            )
            : new Image(
               new Texture(
                  Gdx.files
                     .internal(
                        "map/"
                           + CFG.map.getFile_ActiveMap_Path()
                           + "scenarios/"
                           + CFG.game.getGameScenarios().sActiveScenarioTag
                           + "/events/"
                           + CFG.eventsManager.getEvent(EVENT_ID).getEventPicture()
                     )
               ),
               Texture.TextureFilter.Linear
            );
      } catch (GdxRuntimeException var10) {
         try {
            this.lPicture = new Image(
               new Texture(Gdx.files.internal("UI/events/" + CFG.eventsManager.getEvent(EVENT_ID).getEventPicture())), Texture.TextureFilter.Linear
            );
         } catch (GdxRuntimeException var9) {
            try {
               this.lPicture = new Image(new Texture(Gdx.files.internal("UI/events/default.png")), Texture.TextureFilter.Linear);
            } catch (GdxRuntimeException var8) {
               this.lPicture = null;
            }
         }
      }
   }

   @Override
   public void updateLanguage() {
      try {
         this.getTitle().setText(CFG.langManager.get(CFG.eventsManager.getEvent(EVENT_ID).getEventName()));
      } catch (NullPointerException var4) {
         try {
            this.getTitle().setText(CFG.langManager.get("Event"));
         } catch (NullPointerException var3) {
         }
      }

      if (CFG.eventsManager.getEvent(EVENT_ID).getEventDate_Until().iEventYear == 9999999) {
         if (CFG.eventsManager.getEvent(EVENT_ID).getEventDate_Since().iEventYear == 9999999) {
            this.sEventDate = Game_Calendar.getCurrentDate();
         } else {
            CFG.eventsManager.iCreateEvent_Day = CFG.eventsManager.getEvent(EVENT_ID).getEventDate_Since().iEventDay;
            CFG.eventsManager.iCreateEvent_Month = CFG.eventsManager.getEvent(EVENT_ID).getEventDate_Since().iEventMonth;
            CFG.eventsManager.iCreateEvent_Year = CFG.eventsManager.getEvent(EVENT_ID).getEventDate_Since().iEventYear;
            this.sEventDate = Game_Calendar.getCurrentDate_CreateEvent();
         }
      } else {
         this.sEventDate = Game_Calendar.getCurrentDate();
      }

      CFG.glyphLayout.setText(CFG.fontMain, this.sEventDate);
      this.iEventDateWidth = (int)(CFG.glyphLayout.width * 0.65F);
   }

   @Override
   public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      oSB.setColor(Color.WHITE);
      ImageManager.getImage(Images.new_game_top_edge)
         .draw2(
            oSB,
            this.getPosX() - 2 + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.new_game_top_edge).getHeight() + iTranslateY,
            this.getWidth() - ImageManager.getImage(Images.new_game_top_edge).getWidth() + 4,
            this.getHeight() + CFG.PADDING,
            false,
            true
         );
      ImageManager.getImage(Images.new_game_top_edge)
         .draw2(
            oSB,
            this.getPosX() + 2 + this.getWidth() - ImageManager.getImage(Images.new_game_top_edge).getWidth() + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.new_game_top_edge).getHeight() + iTranslateY,
            ImageManager.getImage(Images.new_game_top_edge).getWidth(),
            this.getHeight() + CFG.PADDING,
            true,
            true
         );
      oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.45F));
      ImageManager.getImage(Images.gradient)
         .draw(
            oSB,
            this.getPosX() + 2 + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY,
            this.getWidth() - 4,
            this.getHeight() / 4
         );
      ImageManager.getImage(Images.pix255_255_255)
         .draw(
            oSB,
            this.getPosX() + 2 + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY,
            this.getWidth() - 4,
            1
         );
      oSB.setColor(Color.WHITE);
      this.beginClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
      oSB.setColor(Color.WHITE);

      try {
         if (this.getMenuElement(0).getIsHovered()) {
            oSB.setColor(new Color(1.0F, 1.0F, 1.0F, 0.9F));
         }

         this.lPicture
            .draw(
               oSB,
               this.getPosX()
                  + this.getMenuElement(0).getPosX()
                  + this.getMenuElement(0).getWidth() / 2
                  - (int)(this.lPicture.getWidth() * CFG.GUI_SCALE) / 2
                  + iTranslateX,
               this.getPosY()
                  + this.getMenuElement(0).getPosY()
                  + this.getMenuElement(0).getHeight() / 2
                  - (int)(this.lPicture.getHeight() * CFG.GUI_SCALE) / 2
                  - this.lPicture.getHeight()
                  + iTranslateY,
               (int)(this.lPicture.getWidth() * CFG.GUI_SCALE),
               (int)(this.lPicture.getHeight() * CFG.GUI_SCALE)
            );
         oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.55F));
         ImageManager.getImage(Images.gradient)
            .draw(
               oSB,
               this.getPosX() + this.getMenuElement(0).getPosX() + iTranslateX,
               this.getPosY() + this.getMenuElement(0).getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY,
               this.getMenuElement(0).getWidth(),
               (int)(CFG.TEXT_HEIGHT * 0.65F) + CFG.PADDING * 2
            );
         oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.75F));
         ImageManager.getImage(Images.slider_gradient)
            .draw(
               oSB,
               this.getPosX() + this.getMenuElement(0).getPosX() + iTranslateX,
               this.getPosY() + this.getMenuElement(0).getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY,
               this.getMenuElement(0).getWidth() / 3,
               (int)(CFG.TEXT_HEIGHT * 0.65F) + CFG.PADDING * 2
            );
         ImageManager.getImage(Images.slider_gradient)
            .draw(
               oSB,
               this.getPosX() + this.getMenuElement(0).getPosX() + this.getMenuElement(0).getWidth() - this.getMenuElement(0).getWidth() / 3 + iTranslateX,
               this.getPosY() + this.getMenuElement(0).getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY,
               this.getMenuElement(0).getWidth() / 3,
               (int)(CFG.TEXT_HEIGHT * 0.65F) + CFG.PADDING * 2,
               true,
               false
            );
         oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.8F));
         ImageManager.getImage(Images.slider_gradient)
            .draw(
               oSB,
               this.getPosX() + this.getMenuElement(0).getPosX() + iTranslateX,
               this.getPosY()
                  + (int)(CFG.TEXT_HEIGHT * 0.65F)
                  + CFG.PADDING * 2
                  - 1
                  + this.getMenuElement(0).getPosY()
                  - ImageManager.getImage(Images.slider_gradient).getHeight()
                  + iTranslateY,
               this.getMenuElement(0).getWidth() / 3,
               1
            );
         ImageManager.getImage(Images.slider_gradient)
            .draw(
               oSB,
               this.getPosX() + this.getMenuElement(0).getPosX() + this.getMenuElement(0).getWidth() - this.getMenuElement(0).getWidth() / 3 + iTranslateX,
               this.getPosY()
                  + (int)(CFG.TEXT_HEIGHT * 0.65F)
                  + CFG.PADDING * 2
                  - 1
                  + this.getMenuElement(0).getPosY()
                  - ImageManager.getImage(Images.slider_gradient).getHeight()
                  + iTranslateY,
               this.getMenuElement(0).getWidth() / 3,
               1,
               true,
               false
            );
         oSB.setColor(Color.WHITE);
         CFG.fontMain.getData().setScale(0.65F);
         if (CFG.eventsManager.getEvent(EVENT_ID).getCivID() > 0
            && CFG.eventsManager.getEvent(EVENT_ID).getCivID() < CFG.game.getCivsSize()
            && CFG.game.getCiv(CFG.eventsManager.getEvent(EVENT_ID).getCivID()).getCapitalProvinceID() >= 0
            && CFG.game.getProvince(CFG.game.getCiv(CFG.eventsManager.getEvent(EVENT_ID).getCivID()).getCapitalProvinceID()).getCitiesSize() > 0) {
            CFG.drawText(
               oSB,
               CFG.game.getProvince(CFG.game.getCiv(CFG.eventsManager.getEvent(EVENT_ID).getCivID()).getCapitalProvinceID()).getCity(0).getCityName(),
               this.getPosX() + this.getMenuElement(0).getPosX() + CFG.PADDING + iTranslateX,
               this.getPosY() + this.getMenuElement(0).getPosY() + CFG.PADDING + iTranslateY,
               new Color(1.0F, 1.0F, 1.0F, 0.8F)
            );
         }

         CFG.drawText(
            oSB,
            this.sEventDate,
            this.getPosX() + this.getMenuElement(0).getPosX() + this.getMenuElement(0).getWidth() - CFG.PADDING - this.iEventDateWidth + iTranslateX,
            this.getPosY() + this.getMenuElement(0).getPosY() + CFG.PADDING + iTranslateY,
            new Color(1.0F, 1.0F, 1.0F, 0.8F)
         );
         CFG.fontMain.getData().setScale(1.0F);
      } catch (NullPointerException var6) {
      }

      oSB.setColor(Color.WHITE);
      this.drawMenu(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
      oSB.setColor(Color.WHITE);
      this.endClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
   }

   @Override
   public void drawScrollPos(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      if (sliderMenuIsActive) {
         super.drawScrollPos(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
      }
   }

   @Override
   public final void actionElement(int iID) {
      switch (iID) {
         case 0:
            CFG.toast.setInView(this.getTitle().getText(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE);
            return;
         case 1:
            CFG.toast.setInView(CFG.langManager.get(CFG.eventsManager.getEvent(EVENT_ID).getEvent_PopUp().sText), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE);
            return;
         default:
            CFG.toast.setInView(this.getMenuElement(iID).getText(), CFG.COLOR_TEXT_MODIFIER_NEUTRAL2);
            iID -= 2;

            try {
               if (CFG.eventsManager.getEvent(EVENT_ID).getCivID() >= 0) {
                  CFG.game
                     .getCiv(CFG.eventsManager.getEvent(EVENT_ID).getCivID())
                     .addEvent_DecisionTaken(CFG.eventsManager.getEvent(EVENT_ID).getEventTag() + "_" + iID);
               }

               CFG.eventsManager.getEvent(EVENT_ID).lDecisions.get(iID).executeDecision();
            } catch (IndexOutOfBoundsException var3) {
            }

            CFG.menuManager.setVisibleInGame_Event(false);
            CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).runNextEvent();
      }
   }

   @Override
   public void setVisible(boolean visible) {
      super.setVisible(visible);
      if (!visible && this.lPicture != null) {
         this.lPicture.getTexture().dispose();
         this.lPicture = null;
      }
   }
}
