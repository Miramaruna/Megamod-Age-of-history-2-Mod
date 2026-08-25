package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

public class Menu_ManageDiplomacy_DefensivePact extends SliderMenu {
   public Menu_ManageDiplomacy_DefensivePact() {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      menuElements.add(
         new Button_Add("", -1, CFG.PADDING, CFG.PADDING, (CFG.GAME_WIDTH - CFG.PADDING * 4) / 3, CFG.BUTTON_HEIGHT + CFG.BUTTON_HEIGHT / 2, true) {
            @Override
            public void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
               super.drawText(oSB, iTranslateX, iTranslateY, isActive);
               if (CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1 > 0) {
                  CFG.game
                     .getCiv(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1)
                     .getFlag()
                     .draw(
                        oSB,
                        this.getPosX() + this.getWidth() / 2 - this.getTextWidth() / 2 - CFG.PADDING - CFG.CIV_FLAG_WIDTH + iTranslateX,
                        this.getPosY()
                           - CFG.game.getCiv(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getFlag().getHeight()
                           + this.getHeight() / 2
                           - CFG.CIV_FLAG_HEIGHT / 2
                           + iTranslateY,
                        CFG.CIV_FLAG_WIDTH,
                        CFG.CIV_FLAG_HEIGHT
                     );
                  ImageManager.getImage(Images.flag_rect)
                     .draw(
                        oSB,
                        this.getPosX() + this.getWidth() / 2 - this.getTextWidth() / 2 - CFG.PADDING - CFG.CIV_FLAG_WIDTH + iTranslateX,
                        this.getPosY() + this.getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 + iTranslateY
                     );
               }
            }
         }
      );
      menuElements.add(
         new Button_Add(
            "",
            -1,
            CFG.PADDING * 2 + (CFG.GAME_WIDTH - CFG.PADDING * 4) / 3,
            CFG.PADDING,
            (CFG.GAME_WIDTH - CFG.PADDING * 4) / 3,
            CFG.BUTTON_HEIGHT + CFG.BUTTON_HEIGHT / 2,
            true
         ) {
            @Override
            public void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
               super.drawText(oSB, iTranslateX, iTranslateY, isActive);
               if (CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV2 > 0) {
                  CFG.game
                     .getCiv(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV2)
                     .getFlag()
                     .draw(
                        oSB,
                        this.getPosX() + this.getWidth() / 2 - this.getTextWidth() / 2 - CFG.PADDING - CFG.CIV_FLAG_WIDTH + iTranslateX,
                        this.getPosY()
                           - CFG.game.getCiv(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV2).getFlag().getHeight()
                           + this.getHeight() / 2
                           - CFG.CIV_FLAG_HEIGHT / 2
                           + iTranslateY,
                        CFG.CIV_FLAG_WIDTH,
                        CFG.CIV_FLAG_HEIGHT
                     );
                  ImageManager.getImage(Images.flag_rect)
                     .draw(
                        oSB,
                        this.getPosX() + this.getWidth() / 2 - this.getTextWidth() / 2 - CFG.PADDING - CFG.CIV_FLAG_WIDTH + iTranslateX,
                        this.getPosY() + this.getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 + iTranslateY
                     );
               }
            }
         }
      );
      menuElements.add(
         new Button_Add_V(
            "",
            -1,
            CFG.PADDING * 3 + (CFG.GAME_WIDTH - CFG.PADDING * 4) / 3 * 2,
            CFG.PADDING,
            (CFG.GAME_WIDTH - CFG.PADDING * 4) / 3,
            CFG.BUTTON_HEIGHT + CFG.BUTTON_HEIGHT / 2,
            false
         )
      );
      this.initMenu(null, 0, 0, CFG.GAME_WIDTH, CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING * 2, menuElements, false, false);
      this.updateLanguage();
   }

   @Override
   public void updateLanguage() {
   }

   @Override
   public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      ImageManager.getImage(Images.editor_line)
         .draw2(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.editor_line).getHeight() + iTranslateY,
            this.getWidth(),
            this.getMenuElement(0).getHeight() + CFG.PADDING * 2,
            false,
            true
         );
      oSB.setColor(new Color(CFG.COLOR_GRADIENT_DARK_BLUE.r, CFG.COLOR_GRADIENT_DARK_BLUE.g, CFG.COLOR_GRADIENT_DARK_BLUE.b, 0.575F));
      ImageManager.getImage(Images.gradient)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY,
            this.getWidth(),
            (this.getMenuElement(0).getHeight() + CFG.PADDING * 2) / 4
         );
      oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.65F));
      ImageManager.getImage(Images.line_32_off1)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() + this.getMenuElement(0).getHeight() + CFG.PADDING * 2 - 1 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
            this.getWidth(),
            1
         );
      oSB.setColor(new Color(0.0425F, 0.0475F, 0.06F, 0.7F));
      ImageManager.getImage(Images.pix255_255_255)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() + this.getMenuElement(0).getHeight() + CFG.PADDING * 2 - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY,
            this.getWidth(),
            1
         );
      oSB.setColor(Color.WHITE);
      super.draw(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
      if (CFG.MANAGE_DIPLOMACY_DRAW_HELP_LINE && CFG.game.getActiveProvinceID() >= 0 && CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID() != 0) {
         int tempProvincePosX = CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCenterX()
            + CFG.game.getProvince(CFG.game.getActiveProvinceID()).getShiftX()
            + CFG.game.getProvince(CFG.game.getActiveProvinceID()).getTranslateProvincePosX();
         int tempButtonPosX = this.getMenuElement(0).getPosX() + this.getMenuElement(0).getWidth() / 2 + this.getMenuPosX() + iTranslateX;
         int tempProvincePosY = CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCenterY()
            + CFG.game.getProvince(CFG.game.getActiveProvinceID()).getShiftY()
            + CFG.map.getMapCoordinates().getPosY();
         int tempButtonPosY = this.getMenuElement(0).getPosY() + this.getMenuElement(0).getHeight() / 2 + this.getMenuPosY() + iTranslateY;
         tempProvincePosX = (int)(tempProvincePosX * CFG.map.getMapScale().getCurrentScale());
         tempProvincePosY = (int)(tempProvincePosY * CFG.map.getMapScale().getCurrentScale());
         int iWidth = (int)Math.ceil(
            Math.sqrt(
               (tempButtonPosX - tempProvincePosX) * (tempButtonPosX - tempProvincePosX)
                  + (tempProvincePosY - tempButtonPosY) * (tempProvincePosY - tempButtonPosY)
            )
         );
         float fAngle = (float)(Math.atan2(tempProvincePosY - tempButtonPosY, -tempProvincePosX + tempButtonPosX) * 180.0 / Math.PI);
         float tempAngle = fAngle > 90.0F ? 90.0F - fAngle % 90.0F : (fAngle < -90.0F ? -(90.0F + fAngle % 90.0F) : fAngle);
         int offsetX = -((int)(ImageManager.getImage(Images.line_32).getHeight() / 2.0F * (tempAngle / 90.0F)));
         int offsetY = -((int)(ImageManager.getImage(Images.line_32).getHeight() / 2.0F * ((90.0F - Math.abs(fAngle)) / 90.0F)));
         oSB.setColor(new Color(1.0F, 1.0F, 1.0F, 0.45F));
         ImageManager.getImage(Images.line_32)
            .draw(oSB, tempProvincePosX + offsetX, tempProvincePosY + offsetY, iWidth, ImageManager.getImage(Images.line_32).getHeight(), fAngle, 0);
         oSB.setColor(Color.WHITE);
      }
   }

   @Override
   public final void actionElement(int iID) {
      switch (iID) {
         case 2:
            CFG.game.setDefensivePact(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1, CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV2, 5);
            CFG.game.setActiveProvinceID(CFG.game.getCiv(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getCapitalProvinceID());
            CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1 = -1;
            CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV2 = -1;
            if (CFG.game.getActiveProvinceID() >= 0 && CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID() > 0) {
               CFG.MANAGE_DIPLOMACY_CUSTOMIZE_ALLIANCE_ID = CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID();
            }

            CFG.menuManager.rebuildManageDiplomacy_Defensive();
      }
   }
}
