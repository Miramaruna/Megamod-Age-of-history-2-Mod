package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

public class Menu_InGame_GameSaved extends SliderMenu {
   public static final float FONT_SCALE2 = 0.75F;
   public static final float FONT_SCALE = 0.75F;
   public String sWar;
   public int iWarWidth;
   public long lTime = -1L;
   public int TIME_IN_VIEW = 1750;
   public int TIME_IN_VIEW_HIDE_ANIMATION = 475;

   public Menu_InGame_GameSaved() {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      menuElements.add(new Button_Transparent(0, 0, 1, 1, false));
      this.initMenu(null, 0, 0, CFG.GAME_WIDTH, CFG.GAME_HEIGHT, menuElements);
      this.setVisible(false);
   }

   public Menu_InGame_GameSaved(int init) {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      menuElements.add(new Button_Transparent(0, 0, 1, 1, false));
      this.initMenu(null, 0, 0, CFG.GAME_WIDTH, CFG.GAME_HEIGHT, menuElements);
      this.sWar = CFG.langManager.get("GameSaved");
      CFG.glyphLayout.setText(CFG.fontMain, this.sWar);
      this.iWarWidth = (int)(CFG.glyphLayout.width * 0.75F);
      this.lTime = -1L;
   }

   public final int getPosX2() {
      return CFG.GAME_WIDTH / 2 - this.getWidth2() / 2;
   }

   public final int getPosY2() {
      return CFG.GAME_HEIGHT - CFG.map.getMapBG().getMinimapHeight() - CFG.PADDING - this.getHeight2();
   }

   public final int getWidth2() {
      return (int)((this.iWarWidth + CFG.PADDING * 2) * 1.65F);
   }

   public final int getHeight2() {
      return CFG.TEXT_HEIGHT + CFG.PADDING * 2 + CFG.PADDING * 2;
   }

   @Override
   public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      if (this.lTime < 0L) {
         this.lTime = System.currentTimeMillis();
      }

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
      oSB.setColor(Color.WHITE);
      CFG.fontMain.getData().setScale(0.75F);
      CFG.drawText(
         oSB,
         this.sWar,
         this.getPosX2() + this.getWidth2() / 2 - this.iWarWidth / 2 + iTranslateX,
         this.getPosY2() + CFG.PADDING * 2 + (int)((CFG.TEXT_HEIGHT - CFG.TEXT_HEIGHT * 0.75F) / 2.0F) + iTranslateY,
         new Color(CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE.r, CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE.g, CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE.b, 1.0F * tAlpha)
      );
      CFG.fontMain.getData().setScale(1.0F);
      CFG.setRender_3(true);
      if (System.currentTimeMillis() > this.lTime + this.TIME_IN_VIEW) {
         this.setVisible(false);
      }
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
