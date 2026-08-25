package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

public class Menu_Dialog extends SliderMenu {
   public int iBackgroundAlpha = 5;
   public int animationStepID = 0;
   public int animationChangePosY;
   public boolean closeMenu = false;

   public Menu_Dialog() {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      int tWidth = CFG.CIV_INFO_MENU_WIDTH * 2 + CFG.CIV_INFO_MENU_WIDTH / 2;
      if (CFG.GAME_WIDTH <= tWidth - CFG.PADDING * 8) {
         tWidth = CFG.GAME_WIDTH - CFG.PADDING * 8;
      }

      menuElements.add(
         new Button_Close(
            CFG.GAME_WIDTH / 2 + tWidth / 2 + CFG.PADDING * 3 - ImageManager.getImage(Images.btn_close).getWidth(),
            CFG.GAME_HEIGHT / 2 - CFG.BUTTON_HEIGHT - ImageManager.getImage(Images.btn_close).getHeight() / 2 + 1,
            ImageManager.getImage(Images.btn_close).getWidth(),
            ImageManager.getImage(Images.btn_close).getHeight()
         ) {
            @Override
            public int getPosY() {
               return Menu_Dialog.this.getMenuElement(3).getPosY();
            }
         }
      );
      menuElements.add(
         new Button_DialogAgree(
            null, -1, CFG.GAME_WIDTH / 2 - CFG.PADDING * 3 - tWidth / 2, CFG.GAME_HEIGHT / 2, tWidth / 2 + CFG.PADDING * 3, CFG.BUTTON_HEIGHT, false
         ) {
            @Override
            public int getPosY() {
               return Menu_Dialog.this.getMenuElement(3).getPosY() + Menu_Dialog.this.getMenuElement(3).getHeight();
            }
         }
      );
      menuElements.add(new Button_DialogDisagree(null, -1, CFG.GAME_WIDTH / 2, CFG.GAME_HEIGHT / 2, tWidth / 2 + CFG.PADDING * 3, CFG.BUTTON_HEIGHT, false) {
         @Override
         public int getPosY() {
            return Menu_Dialog.this.getMenuElement(3).getPosY() + Menu_Dialog.this.getMenuElement(3).getHeight();
         }
      });
      menuElements.add(
         new Text_Scrollable("", CFG.GAME_WIDTH / 2 - tWidth / 2, CFG.GAME_HEIGHT / 2 - CFG.BUTTON_HEIGHT, tWidth, CFG.BUTTON_HEIGHT, Color.WHITE, 0.8F) {
            @Override
            public Color getColor(boolean isActive) {
               return isActive
                  ? CFG.COLOR_TEXT_OPTIONS_LEFT_NS_ACTIVE
                  : (this.getClickable() ? CFG.COLOR_TEXT_OPTIONS_LEFT_NS : new Color(0.78F, 0.78F, 0.78F, 0.7F));
            }
         }
      );
      this.initMenu(null, 0, 0, CFG.GAME_WIDTH, CFG.GAME_HEIGHT, menuElements, false, false);
      this.updateLanguage();
   }

   @Override
   public void updateLanguage() {
      this.getMenuElement(1).setText(CFG.langManager.get("Yes"));
      this.getMenuElement(2).setText(CFG.langManager.get("No"));
   }

   @Override
   public final void draw(SpriteBatch oSB, int iTranslateX, boolean sliderMenuIsActive) {
      if (this.closeMenu) {
         this.iBackgroundAlpha -= 8;
         if (this.iBackgroundAlpha <= 0) {
            this.iBackgroundAlpha = 0;
         }

         this.updateChangePosY();
      } else if (this.iBackgroundAlpha < 100) {
         this.iBackgroundAlpha += 4;
         this.updateChangePosY();
      }

      oSB.setColor(new Color(CFG.COLOR_GRADIENT_DARK_BLUE.r, CFG.COLOR_GRADIENT_DARK_BLUE.g, CFG.COLOR_GRADIENT_DARK_BLUE.b, this.iBackgroundAlpha / 255.0F));
      ImageManager.getImage(Images.slider_gradient)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight(),
            CFG.CIV_INFO_MENU_WIDTH,
            this.getHeight()
         );
      ImageManager.getImage(Images.slider_gradient)
         .draw(
            oSB,
            this.getPosX() + this.getWidth() - CFG.CIV_INFO_MENU_WIDTH + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight(),
            CFG.CIV_INFO_MENU_WIDTH,
            this.getHeight(),
            true,
            false
         );
      ImageManager.getImage(Images.gradient)
         .draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.gradient).getHeight(), this.getWidth(), CFG.CIV_INFO_MENU_WIDTH);
      ImageManager.getImage(Images.gradient)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() + this.getHeight() - CFG.CIV_INFO_MENU_WIDTH - ImageManager.getImage(Images.gradient).getHeight(),
            this.getWidth(),
            CFG.CIV_INFO_MENU_WIDTH,
            false,
            true
         );
      oSB.setColor(new Color(0.0125F, 0.0125F, 0.0125F, this.iBackgroundAlpha * 1.45F / 255.0F));
      ImageManager.getImage(Images.patt)
         .draw(oSB, iTranslateX, -ImageManager.getImage(Images.pix255_255_255).getHeight(), CFG.GAME_WIDTH, CFG.GAME_HEIGHT, 0.0F, 0);
      oSB.setColor(new Color(0.1F, 0.1F, 0.1F, 0.3F));
      ImageManager.getImage(Images.patt).draw(oSB, iTranslateX, -ImageManager.getImage(Images.patt).getHeight(), CFG.GAME_WIDTH, CFG.GAME_HEIGHT, 0.0F, 0);
      oSB.setColor(new Color(1.0F, 1.0F, 1.0F, this.iBackgroundAlpha * 0.85F / 255.0F));
      ImageManager.getImage(Images.gameLogo)
         .draw(
            oSB,
            this.getPosX() + CFG.PADDING * 2 + iTranslateX,
            this.getPosY() + this.getHeight() - ImageManager.getImage(Images.gameLogo).getHeight() - CFG.PADDING * 2
         );
      oSB.setColor(Color.WHITE);
      ImageManager.getImage(Images.dialog_title)
         .draw2(
            oSB,
            this.getMenuElement(3).getPosX() - CFG.PADDING * 3 + iTranslateX,
            this.getMenuPosY() + this.getMenuElement(3).getPosY() - ImageManager.getImage(Images.dialog_title).getHeight() + this.animationChangePosY,
            this.getMenuElement(3).getWidth() + CFG.PADDING * 6 - ImageManager.getImage(Images.dialog_title).getWidth(),
            this.getMenuElement(3).getHeight()
         );
      ImageManager.getImage(Images.dialog_title)
         .draw2(
            oSB,
            this.getMenuElement(3).getPosX()
               - CFG.PADDING * 3
               + this.getMenuElement(3).getWidth()
               + CFG.PADDING * 6
               - ImageManager.getImage(Images.dialog_title).getWidth()
               + iTranslateX,
            this.getMenuElement(3).getPosY() - ImageManager.getImage(Images.dialog_title).getHeight() + this.animationChangePosY,
            ImageManager.getImage(Images.dialog_title).getWidth(),
            this.getMenuElement(3).getHeight(),
            true,
            false
         );
      oSB.setColor(new Color(CFG.COLOR_GRADIENT_TITLE_BLUE.r, CFG.COLOR_GRADIENT_TITLE_BLUE.g, CFG.COLOR_GRADIENT_TITLE_BLUE.b, 0.55F));
      ImageManager.getImage(Images.gradient)
         .draw(
            oSB,
            this.getMenuElement(3).getPosX() - CFG.PADDING * 3 + 2 + iTranslateX,
            this.getMenuPosY()
               + this.getMenuElement(3).getPosY()
               - ImageManager.getImage(Images.gradient).getHeight()
               + this.getMenuElement(3).getHeight()
               - this.getMenuElement(3).getHeight() * 3 / 5
               + this.animationChangePosY,
            this.getMenuElement(3).getWidth() + CFG.PADDING * 6 - 4,
            this.getMenuElement(3).getHeight() * 3 / 5,
            false,
            true
         );
      oSB.setColor(0.0F, 0.0F, 0.0F, 0.65F);
      ImageManager.getImage(Images.gradient)
         .draw(
            oSB,
            this.getMenuElement(3).getPosX() - CFG.PADDING * 3 + 2 + iTranslateX,
            this.getMenuPosY()
               + this.getMenuElement(3).getPosY()
               - ImageManager.getImage(Images.gradient).getHeight()
               + this.getMenuElement(3).getHeight()
               - CFG.PADDING * 2
               + this.animationChangePosY,
            this.getMenuElement(3).getWidth() + CFG.PADDING * 6 - 4,
            CFG.PADDING * 2,
            false,
            true
         );
      oSB.setColor(0.0F, 0.0F, 0.0F, 0.65F);
      ImageManager.getImage(Images.pix255_255_255)
         .draw2(
            oSB,
            this.getMenuElement(3).getPosX() + 2 - CFG.PADDING * 3 + iTranslateX,
            this.getMenuPosY()
               + this.getMenuElement(3).getHeight()
               + this.getMenuElement(3).getPosY()
               - 2
               - ImageManager.getImage(Images.pix255_255_255).getHeight()
               + this.animationChangePosY,
            this.getMenuElement(3).getWidth() + CFG.PADDING * 6 - 4,
            1
         );
      oSB.setColor(CFG.COLOR_NEW_GAME_EDGE_LINE);
      ImageManager.getImage(Images.pix255_255_255)
         .draw2(
            oSB,
            this.getMenuElement(3).getPosX() + 2 - CFG.PADDING * 3 + iTranslateX,
            this.getMenuPosY()
               + this.getMenuElement(3).getHeight()
               + this.getMenuElement(3).getPosY()
               - 1
               - ImageManager.getImage(Images.pix255_255_255).getHeight()
               + this.animationChangePosY,
            this.getMenuElement(3).getWidth() + CFG.PADDING * 6 - 4,
            1
         );
      oSB.setColor(new Color(CFG.COLOR_GRADIENT_DARK_BLUE.r, CFG.COLOR_GRADIENT_DARK_BLUE.g, CFG.COLOR_GRADIENT_DARK_BLUE.b, 0.55F));
      ImageManager.getImage(Images.line_32_off1)
         .draw(
            oSB,
            this.getMenuElement(3).getPosX() + 2 - CFG.PADDING * 3 + iTranslateX,
            this.getMenuPosY()
               + this.getMenuElement(3).getHeight()
               + this.getMenuElement(3).getPosY()
               - 1
               - ImageManager.getImage(Images.line_32_off1).getHeight()
               + this.animationChangePosY,
            this.getMenuElement(3).getWidth() + CFG.PADDING * 6 - 4,
            1
         );
      oSB.setColor(Color.WHITE);
      super.drawMenuElements(oSB, iTranslateX, this.animationChangePosY, sliderMenuIsActive);
   }

   @Override
   public final void actionElement(int iID) {
      switch (iID) {
         case 0:
            this.getMenuElement(1).setClickable(false);
            this.getMenuElement(2).setClickable(false);
            this.closeMenu();
            break;
         case 1:
            this.getMenuElement(1).setClickable(false);
            this.getMenuElement(2).setClickable(false);
            CFG.dialog_True();
            this.closeMenu();
            break;
         case 2:
            this.getMenuElement(1).setClickable(false);
            this.getMenuElement(2).setClickable(false);
            CFG.dialog_False();
            this.closeMenu();
            break;
         case 3:
            CFG.toast.setInView(this.getMenuElement(iID).getText());
      }
   }

   @Override
   public final void onBackPressed() {
      this.closeMenu();
   }

   public final void updateChangePosY() {
      switch (this.animationStepID) {
         case 0:
         case 1:
         case 12:
            this.animationChangePosY = (int)(this.animationChangePosY - (CFG.GAME_HEIGHT / 2 + CFG.BUTTON_HEIGHT) * 2.5F / 100.0F * (this.closeMenu ? -1 : 1));
            break;
         case 2:
         case 3:
         case 10:
         case 11:
            this.animationChangePosY = (int)(this.animationChangePosY - (CFG.GAME_HEIGHT / 2 + CFG.BUTTON_HEIGHT) * 5.0F / 100.0F * (this.closeMenu ? -1 : 1));
            break;
         case 4:
         case 5:
         case 8:
         case 9:
            this.animationChangePosY = (int)(this.animationChangePosY - (CFG.GAME_HEIGHT / 2 + CFG.BUTTON_HEIGHT) * 10.0F / 100.0F * (this.closeMenu ? -1 : 1));
            break;
         case 6:
         case 7:
            this.animationChangePosY = (int)(this.animationChangePosY - (CFG.GAME_HEIGHT / 2 + CFG.BUTTON_HEIGHT) * 15.0F / 100.0F * (this.closeMenu ? -1 : 1));
            break;
         case 13:
            this.animationChangePosY = 0;
      }

      if (CFG.iNumOfFPS < 22) {
         this.animationStepID = 13;
         this.animationChangePosY = 0;
      }

      if (this.closeMenu && this.animationStepID == 13) {
         this.animationChangePosY = CFG.GAME_HEIGHT / 2 + CFG.BUTTON_HEIGHT;
         this.setVisible(false);
      }

      this.animationStepID++;
      CFG.setRender_3(true);
   }

   public final void closeMenu() {
      this.closeMenu = true;
      this.resetAnimation();
   }

   @Override
   public final void setVisible(boolean visible) {
      super.setVisible(visible);
      this.closeMenu = !visible;
      this.iBackgroundAlpha = 5;
      this.resetAnimation();
   }

   public final void resetAnimation() {
      this.animationStepID = 0;
      if (!this.closeMenu) {
         this.animationChangePosY = CFG.GAME_HEIGHT / 2 + CFG.BUTTON_HEIGHT;
      }
   }
}
