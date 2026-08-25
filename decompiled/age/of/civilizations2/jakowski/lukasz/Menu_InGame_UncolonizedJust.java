package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

public class Menu_InGame_UncolonizedJust extends SliderMenu {
   public static final float FONT_SCALE2 = 0.7F;
   public String sUncolonized;
   public int iUncolonizedWidth;

   public Menu_InGame_UncolonizedJust() {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      menuElements.add(new Button_Transparent(0, 0, 1, 1, false));
      this.initMenu(null, 0, 0, CFG.GAME_WIDTH, CFG.GAME_HEIGHT, menuElements);
      this.sUncolonized = CFG.langManager.get("UncolonizedProvince");
      CFG.glyphLayout.setText(CFG.fontMain, this.sUncolonized);
      this.iUncolonizedWidth = (int)(CFG.glyphLayout.width * 0.7F);
   }

   public final int getPosX2() {
      return CFG.GAME_WIDTH / 2 - this.getWidth2() / 2;
   }

   public final int getPosY2() {
      return CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING * 2 - CFG.PADDING - this.getHeight2();
   }

   public final int getWidth2() {
      return (int)((this.iUncolonizedWidth + CFG.PADDING * 2) * 1.5F);
   }

   public final int getHeight2() {
      return CFG.TEXT_HEIGHT + CFG.PADDING * 4;
   }

   @Override
   public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      float tAlpha = 1.0F;
      oSB.setColor(new Color(CFG.COLOR_GRADIENT_DARK_BLUE.r, CFG.COLOR_GRADIENT_DARK_BLUE.g, CFG.COLOR_GRADIENT_DARK_BLUE.b, 0.85F * tAlpha));
      ImageManager.getImage(Images.line_32_off1)
         .draw(
            oSB,
            this.getPosX2() + iTranslateX,
            this.getPosY2() - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
            this.getWidth2(),
            this.getHeight2()
         );
      oSB.setColor(
         new Color(CFG.COLOR_CREATE_NEW_GAME_BOX_PLAYERS.r, CFG.COLOR_CREATE_NEW_GAME_BOX_PLAYERS.g, CFG.COLOR_CREATE_NEW_GAME_BOX_PLAYERS.b, 0.8F * tAlpha)
      );
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
      CFG.fontMain.getData().setScale(0.7F);
      CFG.drawText(
         oSB,
         this.sUncolonized,
         this.getPosX2() + this.getWidth2() / 2 - this.iUncolonizedWidth / 2 + iTranslateX,
         this.getPosY2() + CFG.PADDING * 2 + (int)((CFG.TEXT_HEIGHT - CFG.TEXT_HEIGHT * 0.7F) / 2.0F) + iTranslateY,
         new Color(1.0F, 1.0F, 1.0F, 1.0F * tAlpha)
      );
      CFG.fontMain.getData().setScale(1.0F);
   }
}
