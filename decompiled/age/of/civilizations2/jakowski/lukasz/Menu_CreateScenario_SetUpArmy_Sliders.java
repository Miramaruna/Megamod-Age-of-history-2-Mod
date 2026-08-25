package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;
import java.util.List;

public class Menu_CreateScenario_SetUpArmy_Sliders extends SliderMenu {
   public List<Integer> lCivs = new ArrayList<>();

   public Menu_CreateScenario_SetUpArmy_Sliders() {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      ArrayList<Integer> tempArmies = new ArrayList<>();
      if (CFG.game.getSelectedProvinces().getProvincesSize() == 0
         && CFG.game.getActiveProvinceID() >= 0
         && CFG.game.getProvince(CFG.game.getActiveProvinceID()).getSeaProvince()) {
         for (int i = 1; i < CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivsSize(); i++) {
            this.lCivs.add(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID(i));
            tempArmies.add(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getArmy(i));
         }
      } else {
         for (int i = 0; i < CFG.game.getSelectedProvinces().getProvincesSize(); i++) {
            for (int j = 0; j < CFG.game.getProvince(CFG.game.getSelectedProvinces().getProvince(i)).getCivsSize(); j++) {
               boolean tAdd = true;

               for (int k = 0; k < this.lCivs.size(); k++) {
                  if (this.lCivs.get(k) == CFG.game.getProvince(CFG.game.getSelectedProvinces().getProvince(i)).getCivID(j)) {
                     tAdd = false;
                     break;
                  }
               }

               if (tAdd) {
                  this.lCivs.add(CFG.game.getProvince(CFG.game.getSelectedProvinces().getProvince(i)).getCivID(j));
                  tempArmies.add(CFG.game.getProvince(CFG.game.getSelectedProvinces().getProvince(i)).getArmy(j));
               }
            }
         }
      }

      for (int var8 = 0; var8 < this.lCivs.size(); var8++) {
         menuElements.add(
            new Slider_LR_Flag(
               this.lCivs.get(var8),
               CFG.PADDING,
               CFG.PADDING + CFG.BUTTON_HEIGHT * var8 + CFG.PADDING * var8,
               CFG.GAME_WIDTH - CFG.BUTTON_WIDTH - CFG.PADDING * 3,
               CFG.BUTTON_HEIGHT,
               0,
               CFG.MANAGE_DIPLOMACY_CUSTOMIZE_ALLIANCE_ID / 50,
               tempArmies.get(var8) / 50
            ) {
               @Override
               public String getDrawText() {
                  return "" + this.getCurrent() * 50;
               }
            }
         );
         menuElements.add(
            new Button_Game_Decline2(CFG.GAME_WIDTH - CFG.BUTTON_WIDTH - CFG.PADDING, CFG.PADDING + CFG.BUTTON_HEIGHT * var8 + CFG.PADDING * var8, true)
         );
      }

      this.initMenu(
         null,
         0,
         CFG.GAME_HEIGHT
            - CFG.map.getMapBG().getMinimapHeight()
            - (CFG.BUTTON_HEIGHT + CFG.PADDING * 2) * (this.lCivs.size() > 1 ? 2 : 1)
            + (this.lCivs.size() == 2 ? CFG.PADDING : 0),
         CFG.GAME_WIDTH,
         (CFG.BUTTON_HEIGHT + CFG.PADDING * 2) * (this.lCivs.size() > 1 ? 2 : 1) + (this.lCivs.size() == 2 ? -CFG.PADDING : 0),
         menuElements
      );
      if (this.lCivs.size() == 0) {
         this.setVisible(false);
      }
   }

   @Override
   public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.6F));
      ImageManager.getImage(Images.line_32_off1)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() - CFG.PADDING - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
            this.getWidth()
         );
      oSB.setColor(Color.WHITE);
      ImageManager.getImage(Images.editor_line)
         .draw2(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.editor_line).getHeight() + iTranslateY,
            this.getWidth(),
            this.getHeight()
         );
      super.draw(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
      oSB.setColor(CFG.COLOR_MINIMAP_BORDER);
      ImageManager.getImage(Images.pix255_255_255)
         .draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY - ImageManager.getImage(Images.pix255_255_255).getHeight(), this.getWidth(), 1);
      oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.65F));
      ImageManager.getImage(Images.slider_gradient)
         .draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY - ImageManager.getImage(Images.slider_gradient).getHeight(), this.getWidth(), 1);
      ImageManager.getImage(Images.slider_gradient)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() + iTranslateY - ImageManager.getImage(Images.slider_gradient).getHeight(),
            this.getWidth(),
            1,
            true,
            false
         );
      oSB.setColor(Color.WHITE);
   }

   @Override
   public void drawScrollPos(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      if (sliderMenuIsActive) {
         super.drawScrollPos(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
      }
   }

   @Override
   public void actionElement(int iID) {
      if (iID % 2 == 0) {
         if (CFG.game.getSelectedProvinces().getProvincesSize() == 0
            && CFG.game.getActiveProvinceID() >= 0
            && CFG.game.getProvince(CFG.game.getActiveProvinceID()).getSeaProvince()) {
            CFG.game.getProvince(CFG.game.getActiveProvinceID()).updateArmy(this.lCivs.get(iID / 2), this.getMenuElement(iID).getCurrent() * 50);
         } else {
            CFG.game.getSelectedProvinces().updateArmies_CivID(this.lCivs.get(iID / 2), this.getMenuElement(iID).getCurrent() * 50);
         }
      } else {
         this.getMenuElement(iID - 1).setCurrent(0);
         if (CFG.game.getSelectedProvinces().getProvincesSize() == 0
            && CFG.game.getActiveProvinceID() >= 0
            && CFG.game.getProvince(CFG.game.getActiveProvinceID()).getSeaProvince()) {
            CFG.game.getProvince(CFG.game.getActiveProvinceID()).updateArmy(this.lCivs.get((iID - 1) / 2), this.getMenuElement(iID - 1).getCurrent() * 50);
         } else {
            CFG.game.getSelectedProvinces().updateArmies_CivID(this.lCivs.get((iID - 1) / 2), this.getMenuElement(iID - 1).getCurrent() * 50);
         }
      }
   }
}
