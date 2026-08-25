package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

public class Menu_Flag extends SliderMenu {
   public Menu_Flag() {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      int pixelWidth = (CFG.GAME_WIDTH - CFG.PADDING * 6) / CFG.CIV_FLAG_WIDTH;

      for (int i = 0; i < CFG.CIV_FLAG_HEIGHT; i++) {
         for (int j = 0; j < CFG.CIV_FLAG_WIDTH; j++) {
            menuElements.add(
               new Menu_FlagPixel(
                  CFG.PADDING * 3 + pixelWidth * j + (CFG.GAME_WIDTH - pixelWidth * CFG.CIV_FLAG_WIDTH - CFG.PADDING * 6) / 2,
                  pixelWidth * i + CFG.PADDING,
                  pixelWidth,
                  pixelWidth
               )
            );
         }
      }

      CFG.FlagPixelColor = new Menu_FlagPixel_Color();
      this.initMenu(null, 0, CFG.BUTTON_HEIGHT + CFG.PADDING, CFG.GAME_WIDTH, pixelWidth * CFG.CIV_FLAG_HEIGHT + CFG.PADDING * 2, menuElements);
   }

   @Override
   public final void draw(SpriteBatch oSB, int iTranslateX, boolean sliderMenuIsActive) {
      oSB.setColor(0.0F, 0.0F, 0.0F, 0.2F);
      ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() + iTranslateX, this.getPosY(), this.getWidth(), this.getHeight());
      oSB.setColor(Color.WHITE);

      for (int i = this.getMenuElementsSize() - 1; i >= 0; i--) {
         this.getMenuElement(i).draw(oSB, this.getMenuPosX() + iTranslateX, this.getMenuPosY(), i);
      }

      oSB.setColor(0.196F, 0.196F, 0.196F, 1.0F);
      ImageManager.getImage(Images.pix255_255_255)
         .draw(
            oSB,
            this.getMenuElement(0).getPosX() + this.getPosX() + iTranslateX,
            this.getMenuElement(0).getPosY() + this.getPosY(),
            this.getMenuElement(0).getWidth() * CFG.CIV_FLAG_WIDTH,
            1
         );
      ImageManager.getImage(Images.pix255_255_255)
         .draw(
            oSB,
            this.getMenuElement(0).getPosX() + this.getPosX() + iTranslateX,
            this.getMenuElement(0).getPosY() + this.getPosY(),
            1,
            this.getMenuElement(0).getHeight() * CFG.CIV_FLAG_HEIGHT
         );
      ImageManager.getImage(Images.pix255_255_255)
         .draw(
            oSB,
            this.getMenuElement(0).getPosX() + this.getPosX() + iTranslateX,
            this.getMenuElement(0).getPosY() + this.getMenuElement(0).getHeight() * CFG.CIV_FLAG_HEIGHT + this.getPosY(),
            this.getMenuElement(0).getWidth() * CFG.CIV_FLAG_WIDTH,
            1
         );
      ImageManager.getImage(Images.pix255_255_255)
         .draw(
            oSB,
            this.getMenuElement(0).getPosX() + this.getMenuElement(0).getWidth() * CFG.CIV_FLAG_WIDTH + this.getPosX() + iTranslateX,
            this.getMenuElement(0).getPosY() + this.getPosY(),
            1,
            this.getMenuElement(0).getHeight() * CFG.CIV_FLAG_HEIGHT
         );
      oSB.setColor(0.196F, 0.196F, 0.196F, 0.15F);

      for (int var5 = 1; var5 < CFG.CIV_FLAG_HEIGHT; var5++) {
         ImageManager.getImage(Images.pix255_255_255)
            .draw(
               oSB,
               this.getMenuElement(0).getPosX() + this.getPosX() + iTranslateX,
               this.getMenuElement(0).getPosY() + this.getMenuElement(0).getHeight() * var5 + this.getPosY(),
               this.getMenuElement(0).getWidth() * CFG.CIV_FLAG_WIDTH,
               1
            );
      }

      for (int var6 = 1; var6 < CFG.CIV_FLAG_WIDTH; var6++) {
         ImageManager.getImage(Images.pix255_255_255)
            .draw(
               oSB,
               this.getMenuElement(0).getPosX() + this.getMenuElement(0).getWidth() * var6 + this.getPosX() + iTranslateX,
               this.getMenuElement(0).getPosY() + this.getPosY(),
               1,
               this.getMenuElement(0).getHeight() * CFG.CIV_FLAG_HEIGHT
            );
      }

      oSB.setColor(0.196F, 0.196F, 0.196F, 0.4F);
      ImageManager.getImage(Images.pix255_255_255)
         .draw(
            oSB,
            this.getMenuElement(0).getPosX() + this.getPosX() + iTranslateX,
            this.getMenuElement(0).getPosY() + this.getMenuElement(0).getHeight() * (CFG.CIV_FLAG_HEIGHT / 2) + this.getPosY(),
            this.getMenuElement(0).getWidth() * CFG.CIV_FLAG_WIDTH,
            1
         );
      ImageManager.getImage(Images.pix255_255_255)
         .draw(
            oSB,
            this.getMenuElement(0).getPosX() + this.getPosX() + iTranslateX,
            this.getMenuElement(0).getPosY() + this.getMenuElement(0).getHeight() * (CFG.CIV_FLAG_HEIGHT / 3) + this.getPosY(),
            this.getMenuElement(0).getWidth() * CFG.CIV_FLAG_WIDTH,
            1
         );
      ImageManager.getImage(Images.pix255_255_255)
         .draw(
            oSB,
            this.getMenuElement(0).getPosX() + this.getPosX() + iTranslateX,
            this.getMenuElement(0).getPosY() + this.getMenuElement(0).getHeight() * (CFG.CIV_FLAG_HEIGHT / 3 * 2 + 1) + this.getPosY(),
            this.getMenuElement(0).getWidth() * CFG.CIV_FLAG_WIDTH,
            1
         );
      ImageManager.getImage(Images.pix255_255_255)
         .draw(
            oSB,
            this.getMenuElement(0).getPosX() + this.getMenuElement(0).getWidth() * (CFG.CIV_FLAG_WIDTH / 2) + this.getPosX() + iTranslateX,
            this.getMenuElement(0).getPosY() + this.getPosY(),
            1,
            this.getMenuElement(0).getHeight() * CFG.CIV_FLAG_HEIGHT
         );
      ImageManager.getImage(Images.pix255_255_255)
         .draw(
            oSB,
            this.getMenuElement(0).getPosX() + this.getMenuElement(0).getWidth() * (CFG.CIV_FLAG_WIDTH / 3) + this.getPosX() + iTranslateX,
            this.getMenuElement(0).getPosY() + this.getPosY(),
            1,
            this.getMenuElement(0).getHeight() * CFG.CIV_FLAG_HEIGHT
         );
      ImageManager.getImage(Images.pix255_255_255)
         .draw(
            oSB,
            this.getMenuElement(0).getPosX() + this.getMenuElement(0).getWidth() * CFG.CIV_FLAG_WIDTH / 3 * 2 + this.getPosX() + iTranslateX,
            this.getMenuElement(0).getPosY() + this.getPosY(),
            1,
            this.getMenuElement(0).getHeight() * CFG.CIV_FLAG_HEIGHT
         );
      oSB.setColor(Color.WHITE);
   }

   @Override
   public final void actionElement(int iID) {
      if (CFG.flagEditorMode == CFG.FlagEditorMode.PENCIL) {
         CFG.FlagPixelColor.setR(iID, CFG.flagR / 2.55F / 100.0F);
         CFG.FlagPixelColor.setG(iID, CFG.flagG / 2.55F / 100.0F);
         CFG.FlagPixelColor.setB(iID, CFG.flagB / 2.55F / 100.0F);
      } else if (CFG.flagEditorMode == CFG.FlagEditorMode.PAINT_BUCKET
         && (
            CFG.FlagPixelColor.getR(iID) != CFG.flagR / 2.55F / 100.0F
               || CFG.FlagPixelColor.getG(iID) != CFG.flagG / 2.55F / 100.0F
               || CFG.FlagPixelColor.getB(iID) != CFG.flagB / 2.55F / 100.0F
         )) {
         float tempR = CFG.FlagPixelColor.getR(iID);
         float tempG = CFG.FlagPixelColor.getG(iID);
         float tempB = CFG.FlagPixelColor.getB(iID);

         for (int i = 0; i < CFG.CIV_FLAG_HEIGHT * CFG.CIV_FLAG_WIDTH; i++) {
            if (CFG.FlagPixelColor.getR(i) == tempR && CFG.FlagPixelColor.getG(i) == tempG && CFG.FlagPixelColor.getB(i) == tempB) {
               CFG.FlagPixelColor.setR(i, CFG.flagR / 2.55F / 100.0F);
               CFG.FlagPixelColor.setG(i, CFG.flagG / 2.55F / 100.0F);
               CFG.FlagPixelColor.setB(i, CFG.flagB / 2.55F / 100.0F);
            }
         }
      }
   }
}
