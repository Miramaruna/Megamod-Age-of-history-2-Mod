package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

public class Menu_InGame_History extends SliderMenu {
   public String sNumofDays;
   public int iNumofDaysWidth;
   public static final float FONT_SCALE = 0.55F;

   public Menu_InGame_History(int iClear) {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      int tempWidth = CFG.CIV_INFO_MENU_WIDTH * 3;
      boolean tY = false;
      menuElements.add(new Text_Scale(CFG.langManager.get("----"), -1, 0, CFG.PADDING, tempWidth - CFG.PADDING * 4, CFG.BUTTON_HEIGHT * 3 / 4, 0.75F) {
         @Override
         public int getWidth() {
            return Menu_InGame_History.this.getElementW();
         }
      });
      menuElements.get(menuElements.size() - 1).setClickable(false);
      int tempMenuPosY = ImageManager.getImage(Images.top_flag_frame).getHeight()
         + CFG.PADDING * 4
         + CFG.BUTTON_HEIGHT * 3 / 5
         + CFG.PADDING * 2
         + CFG.BUTTON_HEIGHT * 3 / 4;
      this.initMenu(
         new SliderMenuTitle(CFG.langManager.get("History"), CFG.BUTTON_HEIGHT * 3 / 5, true, true) {
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
               oSB.setColor(new Color(0.24313726F, 0.22352941F, 0.20784314F, 0.165F));
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
               oSB.setColor(new Color(0.24313726F, 0.22352941F, 0.20784314F, 0.375F));
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
               ImageManager.getImage(Images.top_diplomacy_points)
                  .draw(
                     oSB,
                     nPosX
                        + (int)(nWidth - this.getTextWidth() * 0.7F) / 2
                        + (ImageManager.getImage(Images.top_diplomacy_points).getWidth() + CFG.PADDING) / 2
                        - CFG.PADDING
                        - ImageManager.getImage(Images.top_diplomacy_points).getWidth()
                        + iTranslateX,
                     2 + nPosY - this.getHeight() + this.getHeight() / 2 - ImageManager.getImage(Images.top_diplomacy_points).getHeight() / 2
                  );
               CFG.fontMain.getData().setScale(0.7F);
               CFG.drawText(
                  oSB,
                  this.getText(),
                  nPosX
                     + (int)(nWidth - this.getTextWidth() * 0.7F) / 2
                     + (ImageManager.getImage(Images.top_diplomacy_points).getWidth() + CFG.PADDING) / 2
                     + iTranslateX,
                  2 + nPosY - this.getHeight() + (int)(this.getHeight() - this.getTextHeight() * 0.7F) / 2,
                  Color.WHITE
               );
               CFG.fontMain.getData().setScale(1.0F);
            }
         },
         CFG.GAME_WIDTH / 2 - tempWidth / 2,
         tempMenuPosY,
         tempWidth,
         menuElements.get(menuElements.size() - 1).getPosY() + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING + tempMenuPosY
               > CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING * 2
            ? Math.max(CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING * 2 - tempMenuPosY, (CFG.TEXT_HEIGHT + CFG.PADDING * 2) * 6)
            : menuElements.get(menuElements.size() - 1).getPosY() + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING,
         menuElements,
         false,
         true
      );
      this.updateLanguage();

      for (int i = 0; i < this.getMenuElementsSize(); i++) {
         this.getMenuElement(i).setCurrent(i % 2);
      }
   }

   public Menu_InGame_History() {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      int tempWidth = CFG.CIV_INFO_MENU_WIDTH * 3;
      int tY = 0;
      if (CFG.historyManager.haveHistory()) {
         HistoryManager.buildHistoryDates();
         int iSize = CFG.historyManager.getHistorySize();

         for (int i = 0; i < iSize; i++) {
            int jSize = CFG.historyManager.getHistoryTurnSize(i);

            for (int j = 0; j < jSize; j++) {
               menuElements.add(
                  new Text_Clear(i, j, 0, tY, tempWidth, CFG.TEXT_HEIGHT + CFG.PADDING * 2) {
                     @Override
                     public int getWidth() {
                        return Menu_InGame_History.this.getElementW();
                     }

                     @Override
                     public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
                        CFG.historyManager
                           .getHistory(this.getTextPos(), this.getCurrent())
                           .draw(
                              oSB, this.getTextPos(), this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight(), isActive
                           );
                     }
                  }
               );
               tY += menuElements.get(menuElements.size() - 1).getHeight();
            }
         }
      }

      if (menuElements.size() == 0) {
         menuElements.add(new Text_Scale(CFG.langManager.get("----"), -1, 0, CFG.PADDING, tempWidth - CFG.PADDING * 4, CFG.BUTTON_HEIGHT * 3 / 4, 0.75F) {
            @Override
            public int getWidth() {
               return Menu_InGame_History.this.getElementW();
            }
         });
         menuElements.get(menuElements.size() - 1).setClickable(false);
      }

      int tempMenuPosY = ImageManager.getImage(Images.top_flag_frame).getHeight()
         + CFG.PADDING * 4
         + CFG.BUTTON_HEIGHT * 3 / 5
         + CFG.PADDING * 2
         + CFG.BUTTON_HEIGHT * 3 / 4;
      this.initMenu(
         new SliderMenuTitle(CFG.langManager.get("History"), CFG.BUTTON_HEIGHT * 3 / 5, true, true) {
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
               oSB.setColor(new Color(0.24313726F, 0.22352941F, 0.20784314F, 0.165F));
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
               oSB.setColor(new Color(0.24313726F, 0.22352941F, 0.20784314F, 0.375F));
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
               ImageManager.getImage(Images.top_diplomacy_points)
                  .draw(
                     oSB,
                     nPosX
                        + (int)(nWidth - this.getTextWidth() * 0.7F) / 2
                        + (ImageManager.getImage(Images.top_diplomacy_points).getWidth() + CFG.PADDING) / 2
                        - CFG.PADDING
                        - ImageManager.getImage(Images.top_diplomacy_points).getWidth()
                        + iTranslateX,
                     2 + nPosY - this.getHeight() + this.getHeight() / 2 - ImageManager.getImage(Images.top_diplomacy_points).getHeight() / 2
                  );
               CFG.fontMain.getData().setScale(0.7F);
               CFG.drawText(
                  oSB,
                  this.getText(),
                  nPosX
                     + (int)(nWidth - this.getTextWidth() * 0.7F) / 2
                     + (ImageManager.getImage(Images.top_diplomacy_points).getWidth() + CFG.PADDING) / 2
                     + iTranslateX,
                  2 + nPosY - this.getHeight() + (int)(this.getHeight() - this.getTextHeight() * 0.7F) / 2,
                  Color.WHITE
               );
               ImageManager.getImage(Images.time)
                  .draw(
                     oSB,
                     nPosX
                        + nWidth
                        - CFG.PADDING
                        - 2
                        - (int)(ImageManager.getImage(Images.time).getWidth() * Menu_InGame_History.this.getImageScale2(Images.time))
                        + iTranslateX,
                     nPosY
                        - CFG.PADDING
                        - (int)(ImageManager.getImage(Images.time).getHeight() * Menu_InGame_History.this.getImageScale2(Images.time))
                        - ImageManager.getImage(Images.time).getHeight(),
                     (int)(ImageManager.getImage(Images.time).getWidth() * Menu_InGame_History.this.getImageScale2(Images.time)),
                     (int)(ImageManager.getImage(Images.time).getHeight() * Menu_InGame_History.this.getImageScale2(Images.time))
                  );
               CFG.fontMain.getData().setScale(0.55F);
               CFG.drawText(
                  oSB,
                  Menu_InGame_History.this.sNumofDays,
                  nPosX
                     + nWidth
                     - Menu_InGame_History.this.iNumofDaysWidth
                     - CFG.PADDING * 2
                     - (int)(ImageManager.getImage(Images.time).getWidth() * Menu_InGame_History.this.getImageScale2(Images.time))
                     - 2
                     + iTranslateX,
                  nPosY - CFG.PADDING - (int)(CFG.TEXT_HEIGHT * 0.55F),
                  CFG.COLOR_TEXT_MODIFIER_NEUTRAL
               );
               CFG.drawText(
                  oSB,
                  CFG.gameAges.getAge(Game_Calendar.CURRENT_AGEID).getName(),
                  nPosX + CFG.PADDING + iTranslateX,
                  nPosY - CFG.PADDING - (int)(CFG.TEXT_HEIGHT * 0.55F),
                  CFG.COLOR_TEXT_MODIFIER_NEUTRAL
               );
               CFG.fontMain.getData().setScale(1.0F);
            }
         },
         CFG.GAME_WIDTH / 2 - tempWidth / 2,
         tempMenuPosY,
         tempWidth,
         menuElements.get(menuElements.size() - 1).getPosY() + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING + tempMenuPosY
               > CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING * 2
            ? Math.max(CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING * 2 - tempMenuPosY, (CFG.TEXT_HEIGHT + CFG.PADDING * 2) * 6)
            : menuElements.get(menuElements.size() - 1).getPosY() + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING,
         menuElements,
         false,
         true
      );
      this.updateLanguage();

      for (int i = 0; i < this.getMenuElementsSize(); i++) {
         this.getMenuElement(i).setCurrent(i % 2);
      }
   }

   @Override
   public void updateLanguage() {
      this.getTitle().setText(CFG.langManager.get("History"));
      this.sNumofDays = Game_Calendar.TURN_ID == 1 ? Game_Calendar.getCurrentDate() : Game_Calendar.getNumOfDates_ByTurnID(1);
      CFG.glyphLayout.setText(CFG.fontMain, this.sNumofDays);
      this.iNumofDaysWidth = (int)(CFG.glyphLayout.width * 0.55F);
   }

   public final float getImageScale2(int nImageID) {
      return CFG.TEXT_HEIGHT * 0.55F / ImageManager.getImage(nImageID).getHeight() < 1.0F
         ? CFG.TEXT_HEIGHT * 0.55F / ImageManager.getImage(nImageID).getHeight()
         : 1.0F;
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
            this.getHeight() + 2,
            false,
            true
         );
      ImageManager.getImage(Images.new_game_top_edge)
         .draw2(
            oSB,
            this.getPosX() + 2 + this.getWidth() - ImageManager.getImage(Images.new_game_top_edge).getWidth() + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.new_game_top_edge).getHeight() + iTranslateY,
            ImageManager.getImage(Images.new_game_top_edge).getWidth(),
            this.getHeight() + 2,
            true,
            true
         );
      this.beginClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
      this.drawMenu(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
      this.endClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
   }

   @Override
   public void drawScrollPos(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      if (sliderMenuIsActive) {
         super.drawScrollPos(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
      }
   }

   @Override
   public void drawCloseButton(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      this.getCloseButtonImage(sliderMenuIsActive)
         .draw(
            oSB,
            this.getPosX() + this.getWidth() - ImageManager.getImage(Images.btn_close).getWidth() * 3 / 5 + iTranslateX,
            this.getPosY() - this.getTitle().getHeight() - ImageManager.getImage(Images.btn_close).getHeight() + iTranslateY,
            ImageManager.getImage(Images.btn_close).getWidth() * 3 / 5,
            ImageManager.getImage(Images.btn_close).getHeight() * 3 / 5
         );
   }

   @Override
   public final void actionElement(int iID) {
   }

   public final int getW() {
      return this.getWidth();
   }

   public final int getElementW() {
      return this.getW();
   }
}
