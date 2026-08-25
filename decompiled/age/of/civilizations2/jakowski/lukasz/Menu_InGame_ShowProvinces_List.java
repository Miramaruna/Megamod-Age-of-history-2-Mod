package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

public class Menu_InGame_ShowProvinces_List extends SliderMenu {
   public Menu_InGame_ShowProvinces_List() {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      int tMenuWidth = CFG.CIV_INFO_MENU_WIDTH * 3 / 4;
      int tElementH = Math.max(CFG.TEXT_HEIGHT + CFG.PADDING * 4, CFG.CIV_FLAG_HEIGHT + CFG.PADDING * 2);
      int tPosY = 0;

      for (int i = 0; i < CFG.game.getSelectedProvinces().getProvincesSize(); i++) {
         menuElements.add(new Button_ShowProvincesList(CFG.game.getSelectedProvinces().getProvince(i), 0, tPosY, tMenuWidth, tElementH, true));
         tPosY += menuElements.get(menuElements.size() - 1).getHeight();
      }

      this.initMenu(
         new SliderMenuTitle(null, CFG.BUTTON_HEIGHT / 2, false, false) {
            @Override
            public void draw(SpriteBatch oSB, int iTranslateX, int nPosX, int nPosY, int nWidth, boolean sliderMenuIsActive) {
               oSB.setColor(CFG.COLOR_GRADIENT_DARK_BLUE);
               ImageManager.getImage(Images.slider_gradient)
                  .draw(
                     oSB,
                     nPosX + iTranslateX,
                     nPosY - this.getHeight() - ImageManager.getImage(Images.slider_gradient).getHeight(),
                     nWidth,
                     this.getHeight(),
                     true,
                     false
                  );
               oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.35F));
               ImageManager.getImage(Images.slider_gradient)
                  .draw(
                     oSB,
                     nPosX + nWidth - nWidth / 2 + iTranslateX,
                     nPosY - this.getHeight() - ImageManager.getImage(Images.slider_gradient).getHeight(),
                     nWidth / 2,
                     this.getHeight(),
                     true,
                     false
                  );
               oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.65F));
               ImageManager.getImage(Images.slider_gradient)
                  .draw(
                     oSB, nPosX + iTranslateX, nPosY + 2 - this.getHeight() - ImageManager.getImage(Images.slider_gradient).getHeight(), nWidth, 1, true, false
                  );
               ImageManager.getImage(Images.slider_gradient)
                  .draw(oSB, nPosX + iTranslateX, nPosY - 1 - ImageManager.getImage(Images.slider_gradient).getHeight(), nWidth, 1, true, false);
               oSB.setColor(Color.WHITE);
               CFG.fontMain.getData().setScale(0.8F);
               CFG.drawText(
                  oSB,
                  this.getText(),
                  nPosX + nWidth / 2 - (int)(this.getTextWidth() * 0.8F) / 2 + iTranslateX,
                  2 + nPosY - this.getHeight() + (int)(this.getHeight() - this.getTextHeight() * 0.8F) / 2,
                  Color.WHITE
               );
               CFG.fontMain.getData().setScale(1.0F);
            }
         },
         CFG.GAME_WIDTH - tMenuWidth,
         Math.max(CFG.BUTTON_HEIGHT * 4 / 5, Math.max(CFG.CIV_FLAG_HEIGHT + CFG.PADDING * 4, (CFG.TEXT_HEIGHT + CFG.PADDING) * 2 + CFG.PADDING))
            + CFG.PADDING * 2
            + CFG.BUTTON_HEIGHT / 2,
         tMenuWidth,
         Math.min(
            CFG.GAME_HEIGHT
               - (
                  Math.max(CFG.BUTTON_HEIGHT * 4 / 5, Math.max(CFG.CIV_FLAG_HEIGHT + CFG.PADDING * 4, (CFG.TEXT_HEIGHT + CFG.PADDING) * 2 + CFG.PADDING))
                     + CFG.PADDING * 2
                     + CFG.BUTTON_HEIGHT / 2
               ),
            menuElements.get(menuElements.size() - 1).getPosY() + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING
         ),
         menuElements,
         true,
         false
      );

      for (int var6 = 0; var6 < this.getMenuElementsSize(); var6++) {
         this.getMenuElement(var6).setCurrent(var6 % 2);
      }

      this.updateLanguage();
   }

   @Override
   public void updateLanguage() {
      this.getTitle().setText(CFG.langManager.get("Provinces"));
   }

   @Override
   public void drawScrollPos(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      if (sliderMenuIsActive) {
         super.drawScrollPos(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
      }
   }

   @Override
   public void actionElement(int iID) {
      try {
         CFG.game.setActiveProvinceID(this.getMenuElement(iID).getCurrent());
         CFG.map.getMapCoordinates().centerToProvinceID(CFG.game.getActiveProvinceID());
         if (this.getMenuElement(iID).getText().length() > 0) {
            CFG.toast.setInView(this.getMenuElement(iID).getText(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE);
         }
      } catch (IndexOutOfBoundsException var3) {
      }
   }
}
