package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;
import java.util.Random;

public class Menu_FlagEditor extends SliderMenu {
   public Menu_FlagEditor() {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      menuElements.add(new Button_Menu(null, -1, 0, 0, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT, true));
      menuElements.add(new Slider("", CFG.PADDING, CFG.PADDING, (CFG.GAME_WIDTH - CFG.PADDING * 4) / 3, CFG.BUTTON_HEIGHT, 0, 255, 128));
      menuElements.add(
         new Slider(
            "", CFG.PADDING + (CFG.GAME_WIDTH - CFG.PADDING * 4) / 3, CFG.PADDING, (CFG.GAME_WIDTH - CFG.PADDING * 4) / 3, CFG.BUTTON_HEIGHT, 0, 255, 128
         )
      );
      menuElements.add(
         new Slider(
            "", CFG.PADDING + (CFG.GAME_WIDTH - CFG.PADDING * 4) / 3 * 2, CFG.PADDING, (CFG.GAME_WIDTH - CFG.PADDING * 4) / 3, CFG.BUTTON_HEIGHT, 0, 255, 128
         )
      );
      menuElements.add(new Button_Menu(null, (int)(50.0F * CFG.GUI_SCALE), 0, CFG.PADDING * 2 + CFG.BUTTON_HEIGHT, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT, true));
      menuElements.add(
         new Button_Menu("Tool: Pencil", (int)(50.0F * CFG.GUI_SCALE), 0, CFG.PADDING * 3 + CFG.BUTTON_HEIGHT * 2, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT, true)
      );
      this.initMenuWithBackButton(
         new SliderMenuTitle("Flag Editor", CFG.BUTTON_HEIGHT, false, false),
         0,
         CFG.BUTTON_HEIGHT + (CFG.GAME_WIDTH - CFG.PADDING * 6) / CFG.CIV_FLAG_WIDTH * CFG.CIV_FLAG_HEIGHT + CFG.PADDING + CFG.PADDING * 2,
         CFG.GAME_WIDTH,
         CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - (CFG.GAME_WIDTH - CFG.PADDING * 6) / CFG.CIV_FLAG_WIDTH * CFG.CIV_FLAG_HEIGHT - CFG.PADDING - CFG.PADDING * 2,
         menuElements
      );
      this.updateLanguage();
   }

   @Override
   public void updateLanguage() {
      this.getMenuElement(0).setText(CFG.langManager.get("Back"));
      this.getMenuElement(4).setText(CFG.langManager.get("RandomFlag"));
   }

   @Override
   public final void drawTitle(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive, int nPosY) {
      super.drawTitle(oSB, iTranslateX, iTranslateY, sliderMenuIsActive, this.getTitle().getHeight());
      this.drawIconFlag(
         oSB,
         iTranslateX,
         CFG.GAME_WIDTH / 2 - this.getTitle().getTextWidth() / 2 - CFG.PADDING - CFG.CIV_FLAG_WIDTH,
         this.getTitle().getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2
      );
   }

   public final void drawIconFlag(SpriteBatch oSB, int iTranslateX, int nPosX, int nPosY) {
      int x = 0;
      int y = 0;

      for (int i = 0; i < CFG.CIV_FLAG_WIDTH * CFG.CIV_FLAG_HEIGHT; i++) {
         oSB.setColor(CFG.FlagPixelColor.getR(i), CFG.FlagPixelColor.getG(i), CFG.FlagPixelColor.getB(i), CFG.FlagPixelColor.getA(i));
         ImageManager.getImage(Images.pix255_255_255).draw(oSB, nPosX + iTranslateX + 1 * x++, nPosY + 1 * y);
         oSB.setColor(Color.WHITE);
         if (x >= CFG.CIV_FLAG_WIDTH) {
            y++;
            x = 0;
         }
      }
   }

   @Override
   public final void actionElement(int iID) {
      switch (iID) {
         case 0:
            this.onBackPressed();
            break;
         case 1:
            CFG.flagR = this.getMenuElement(iID).getCurrent();
            break;
         case 2:
            CFG.flagG = this.getMenuElement(iID).getCurrent();
            break;
         case 3:
            CFG.flagB = this.getMenuElement(iID).getCurrent();
            break;
         case 4:
            this.randomFlag();
            break;
         case 5:
            CFG.flagEditorMode = CFG.flagEditorMode == CFG.FlagEditorMode.PENCIL ? CFG.FlagEditorMode.PAINT_BUCKET : CFG.FlagEditorMode.PENCIL;
            this.getMenuElement(iID).setText("Tool: " + (CFG.flagEditorMode == CFG.FlagEditorMode.PENCIL ? "Pencil" : "Paint bucket"));
      }
   }

   @Override
   public final void onBackPressed() {
      CFG.menuManager.setViewID(Menu.eMAINMENU);
      CFG.menuManager.setBackAnimation(true);
   }

   public final void randomFlag() {
      Random oR = new Random();
      int res = oR.nextInt(3);
      switch (res) {
         case 0:
            this.randomFlag0();
            break;
         case 1:
            this.randomFlag1();
            break;
         case 2:
            this.randomFlag2();
      }
   }

   public final void randomFlag0() {
      Random oR = new Random();
      int tempR = oR.nextInt(255);
      int tempG = oR.nextInt(255);
      int tempB = oR.nextInt(255);

      for (int i = 0; i < CFG.CIV_FLAG_WIDTH * CFG.CIV_FLAG_HEIGHT / 2; i++) {
         CFG.FlagPixelColor.setR(i, tempR / 2.55F / 100.0F);
         CFG.FlagPixelColor.setG(i, tempG / 2.55F / 100.0F);
         CFG.FlagPixelColor.setB(i, tempB / 2.55F / 100.0F);
      }

      tempR = oR.nextInt(255);
      tempG = oR.nextInt(255);
      tempB = oR.nextInt(255);

      for (int var6 = CFG.CIV_FLAG_WIDTH * CFG.CIV_FLAG_HEIGHT / 2; var6 < CFG.CIV_FLAG_WIDTH * CFG.CIV_FLAG_HEIGHT; var6++) {
         CFG.FlagPixelColor.setR(var6, tempR / 2.55F / 100.0F);
         CFG.FlagPixelColor.setG(var6, tempG / 2.55F / 100.0F);
         CFG.FlagPixelColor.setB(var6, tempB / 2.55F / 100.0F);
      }
   }

   public final void randomFlag1() {
      Random oR = new Random();
      int tempR = oR.nextInt(255);
      int tempG = oR.nextInt(255);
      int tempB = oR.nextInt(255);

      for (int i = 0; i < CFG.CIV_FLAG_WIDTH * CFG.CIV_FLAG_HEIGHT; i++) {
         CFG.FlagPixelColor.setR(i, tempR / 2.55F / 100.0F);
         CFG.FlagPixelColor.setG(i, tempG / 2.55F / 100.0F);
         CFG.FlagPixelColor.setB(i, tempB / 2.55F / 100.0F);
         if (i % (CFG.CIV_FLAG_WIDTH / 3) == CFG.CIV_FLAG_WIDTH / 3 - 1) {
            i += CFG.CIV_FLAG_WIDTH - CFG.CIV_FLAG_WIDTH / 3;
         }
      }

      if (oR.nextInt(100) % 5 != 3) {
         tempR = oR.nextInt(255);
         tempG = oR.nextInt(255);
         tempB = oR.nextInt(255);
      }

      for (int var6 = CFG.CIV_FLAG_WIDTH / 3 * 2; var6 < CFG.CIV_FLAG_WIDTH * CFG.CIV_FLAG_HEIGHT; var6++) {
         CFG.FlagPixelColor.setR(var6, tempR / 2.55F / 100.0F);
         CFG.FlagPixelColor.setG(var6, tempG / 2.55F / 100.0F);
         CFG.FlagPixelColor.setB(var6, tempB / 2.55F / 100.0F);
         if (var6 % CFG.CIV_FLAG_WIDTH == CFG.CIV_FLAG_WIDTH - 1) {
            var6 += CFG.CIV_FLAG_WIDTH - CFG.CIV_FLAG_WIDTH / 3;
         }
      }

      tempR = oR.nextInt(255);
      tempG = oR.nextInt(255);
      tempB = oR.nextInt(255);

      for (int var7 = CFG.CIV_FLAG_WIDTH / 3; var7 < CFG.CIV_FLAG_WIDTH * CFG.CIV_FLAG_HEIGHT; var7++) {
         CFG.FlagPixelColor.setR(var7, tempR / 2.55F / 100.0F);
         CFG.FlagPixelColor.setG(var7, tempG / 2.55F / 100.0F);
         CFG.FlagPixelColor.setB(var7, tempB / 2.55F / 100.0F);
         if (var7 % CFG.CIV_FLAG_WIDTH == CFG.CIV_FLAG_WIDTH / 3 * 2 - 1) {
            var7 += CFG.CIV_FLAG_WIDTH - CFG.CIV_FLAG_WIDTH / 3;
         }
      }
   }

   public final void randomFlag2() {
      Random oR = new Random();
      int tempR = oR.nextInt(255);
      int tempG = oR.nextInt(255);
      int tempB = oR.nextInt(255);

      for (int i = 0; i < CFG.CIV_FLAG_WIDTH * CFG.CIV_FLAG_HEIGHT; i++) {
         CFG.FlagPixelColor.setR(i, tempR / 2.55F / 100.0F);
         CFG.FlagPixelColor.setG(i, tempG / 2.55F / 100.0F);
         CFG.FlagPixelColor.setB(i, tempB / 2.55F / 100.0F);
         if (i % (CFG.CIV_FLAG_WIDTH / 2 - 1) == CFG.CIV_FLAG_WIDTH / 2 - 2) {
            i += CFG.CIV_FLAG_WIDTH - CFG.CIV_FLAG_WIDTH / 2 + 2;
         }
      }
   }
}
