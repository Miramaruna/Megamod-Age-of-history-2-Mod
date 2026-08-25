package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

public class Menu_InGame_CapitalMoved extends SliderMenu {
   public static final float FONT_SCALE2 = 0.8F;
   public static final float FONT_SCALE = 0.7F;
   public int iCivA;
   public String sWar;
   public int iWarWidth;
   public String sDate;
   public int iDateWidth;
   public long lTime;
   public int TIME_IN_VIEW = 4750;
   public int TIME_IN_VIEW_HIDE_ANIMATION = 500;

   public Menu_InGame_CapitalMoved() {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      menuElements.add(new Button_Transparent(0, 0, 1, 1, false));
      this.initMenu(null, 0, 0, CFG.GAME_WIDTH, CFG.GAME_HEIGHT, menuElements);
      this.setVisible(false);
   }

   public Menu_InGame_CapitalMoved(int nProvinceID, int nCivID) {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      menuElements.add(new Button_Transparent(0, 0, 1, 1, false));
      this.initMenu(null, 0, 0, CFG.GAME_WIDTH, CFG.GAME_HEIGHT, menuElements);
      this.iCivA = nCivID;
      this.sWar = CFG.game.getProvince(nProvinceID).getCitiesSize() > 0
         ? CFG.langManager.get("CapitalMoved") + ": " + CFG.game.getProvince(nProvinceID).getCity(0).getCityName()
         : CFG.langManager.get("CapitalMoved");
      CFG.glyphLayout.setText(CFG.fontMain, this.sWar);
      this.iWarWidth = (int)(CFG.glyphLayout.width * 0.8F);
      this.sDate = Game_Calendar.getCurrentDate();
      CFG.glyphLayout.setText(CFG.fontMain, this.sDate);
      this.iDateWidth = (int)(CFG.glyphLayout.width * 0.7F);
      this.lTime = System.currentTimeMillis();
   }

   public final int getPosX2() {
      return CFG.GAME_WIDTH / 2 - this.getWidth2() / 2;
   }

   public final int getPosY2() {
      return CFG.BUTTON_HEIGHT * 3 / 4;
   }

   public final int getWidth2() {
      return (int)(
         Math.max(
               this.iWarWidth
                  + CFG.PADDING * 2
                  + (int)(ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(Images.flag_rect))
                  + CFG.PADDING,
               this.iDateWidth + CFG.PADDING * 2
            )
            * 1.3F
      );
   }

   public final int getHeight2() {
      return (CFG.TEXT_HEIGHT + CFG.PADDING * 2) * 2 + CFG.PADDING * 2;
   }

   @Override
   public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      float tAlpha = this.getAlpha();
      oSB.setColor(new Color(CFG.COLOR_GRADIENT_DARK_BLUE.r, CFG.COLOR_GRADIENT_DARK_BLUE.g, CFG.COLOR_GRADIENT_DARK_BLUE.b, 0.85F * tAlpha));
      ImageManager.getImage(Images.line_32_off1)
         .draw(
            oSB,
            this.getPosX2() + iTranslateX,
            this.getPosY2() - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
            this.getWidth2(),
            this.getHeight2()
         );
      oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 1.0F * tAlpha));
      ImageManager.getImage(Images.line_32_off1)
         .draw(
            oSB, this.getPosX2() + iTranslateX, this.getPosY2() + 1 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth2(), 1
         );
      ImageManager.getImage(Images.line_32_off1)
         .draw(
            oSB,
            this.getPosX2() + iTranslateX,
            this.getPosY2() - 2 + this.getHeight2() - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
            this.getWidth2(),
            1
         );
      oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.65F * tAlpha));
      ImageManager.getImage(Images.line_32_off1)
         .draw(oSB, this.getPosX2() + iTranslateX, this.getPosY2() - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth2(), 1);
      ImageManager.getImage(Images.line_32_off1)
         .draw(
            oSB,
            this.getPosX2() + iTranslateX,
            this.getPosY2() - 1 + this.getHeight2() - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
            this.getWidth2(),
            1
         );
      oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.3F * tAlpha));
      ImageManager.getImage(Images.line_32_off1)
         .draw(
            oSB,
            this.getPosX2() + iTranslateX,
            this.getPosY2() + CFG.PADDING * 2 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
            this.getWidth2(),
            CFG.TEXT_HEIGHT
         );
      oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.5F * tAlpha));
      ImageManager.getImage(Images.line_32_off1)
         .draw(
            oSB,
            this.getPosX2() + iTranslateX,
            this.getPosY2() - 1 + CFG.PADDING * 2 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
            this.getWidth2(),
            1
         );
      ImageManager.getImage(Images.line_32_off1)
         .draw(
            oSB,
            this.getPosX2() + iTranslateX,
            this.getPosY2() + CFG.TEXT_HEIGHT + CFG.PADDING * 2 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
            this.getWidth2(),
            1
         );
      oSB.setColor(new Color(1.0F, 1.0F, 1.0F, tAlpha));
      CFG.game
         .getCiv(this.iCivA)
         .getFlag()
         .draw(
            oSB,
            this.getPosX2()
               + this.getWidth2() / 2
               - (this.iWarWidth + (int)(ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(Images.flag_rect)) + CFG.PADDING) / 2
               + iTranslateX,
            this.getPosY2()
               + CFG.PADDING * 2
               + (CFG.TEXT_HEIGHT - (int)(ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect))) / 2
               + iTranslateY
               - CFG.game.getCiv(this.iCivA).getFlag().getHeight(),
            (int)(ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(Images.flag_rect)),
            (int)(ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect))
         );
      ImageManager.getImage(Images.flag_rect)
         .draw(
            oSB,
            this.getPosX2()
               + this.getWidth2() / 2
               - (this.iWarWidth + (int)(ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(Images.flag_rect)) + CFG.PADDING) / 2
               + iTranslateX,
            this.getPosY2()
               + CFG.PADDING * 2
               + (CFG.TEXT_HEIGHT - (int)(ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect))) / 2
               + iTranslateY
               - ImageManager.getImage(Images.flag_rect).getHeight(),
            (int)(ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(Images.flag_rect)),
            (int)(ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect))
         );
      oSB.setColor(Color.WHITE);
      CFG.fontMain.getData().setScale(0.8F);
      CFG.drawText(
         oSB,
         this.sWar,
         this.getPosX2()
            + this.getWidth2() / 2
            - (this.iWarWidth + (int)(ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(Images.flag_rect)) + CFG.PADDING) / 2
            + (int)(ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(Images.flag_rect))
            + CFG.PADDING
            + iTranslateX,
         this.getPosY2() + CFG.PADDING * 2 + (int)((CFG.TEXT_HEIGHT - CFG.TEXT_HEIGHT * 0.8F) / 2.0F) + iTranslateY,
         new Color(CFG.COLOR_TEXT_MODIFIER_NEUTRAL2.r, CFG.COLOR_TEXT_MODIFIER_NEUTRAL2.g, CFG.COLOR_TEXT_MODIFIER_NEUTRAL2.b, 1.0F * tAlpha)
      );
      CFG.fontMain.getData().setScale(0.7F);
      CFG.drawText(
         oSB,
         this.sDate,
         this.getPosX2() + this.getWidth2() / 2 - this.iDateWidth / 2 + iTranslateX,
         this.getPosY2() + this.getHeight2() / 2 + CFG.PADDING + (int)((CFG.TEXT_HEIGHT - CFG.TEXT_HEIGHT * 0.7F) / 2.0F) + iTranslateY,
         new Color(
            CFG.COLOR_TEXT_MODIFIER_NEUTRAL.r, CFG.COLOR_TEXT_MODIFIER_NEUTRAL.g, CFG.COLOR_TEXT_MODIFIER_NEUTRAL.b, CFG.COLOR_TEXT_NUM_OF_PROVINCES.a * tAlpha
         )
      );
      CFG.fontMain.getData().setScale(1.0F);
      CFG.setRender_3(true);
      if (System.currentTimeMillis() > this.lTime + this.TIME_IN_VIEW) {
         this.setVisible(false);
      }
   }

   public final float getImageScale(int nImageID) {
      return (float)CFG.TEXT_HEIGHT / ImageManager.getImage(nImageID).getHeight() < 1.0F
         ? (float)CFG.TEXT_HEIGHT / ImageManager.getImage(nImageID).getHeight()
         : 1.0F;
   }

   public final float getAlpha() {
      return System.currentTimeMillis() > this.lTime + this.TIME_IN_VIEW - this.TIME_IN_VIEW_HIDE_ANIMATION
         ? Math.max(
            0.0F,
            1.0F - (float)(System.currentTimeMillis() - (this.lTime + this.TIME_IN_VIEW - this.TIME_IN_VIEW_HIDE_ANIMATION)) / this.TIME_IN_VIEW_HIDE_ANIMATION
         )
         : 1.0F;
   }
}
