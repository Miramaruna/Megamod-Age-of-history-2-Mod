package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;
import java.util.List;

public class Menu_CreateScenario_SetUpArmy_Civs extends SliderMenu {
   public List<Integer> lCivs = new ArrayList<>();

   public final void addCiv(int nCivID) {
      for (int i = 0; i < this.lCivs.size(); i++) {
         if (this.lCivs.get(i) == nCivID) {
            return;
         }
      }

      this.lCivs.add(nCivID);
   }

   public Menu_CreateScenario_SetUpArmy_Civs() {
      int tempW = CFG.CIV_INFO_MENU_WIDTH * 4 / 5;
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      if (CFG.game.getSelectedProvinces().getProvincesSize() == 0
         && CFG.game.getActiveProvinceID() >= 0
         && CFG.game.getProvince(CFG.game.getActiveProvinceID()).getSeaProvince()) {
         for (int i = 1; i < CFG.game.getCivsSize(); i++) {
            this.lCivs.add(CFG.game.getSortedCivsAZ(i - 1));
         }
      } else {
         for (int i = 0; i < CFG.game.getSelectedProvinces().getProvincesSize(); i++) {
            if (CFG.game.getCiv(CFG.game.getProvince(CFG.game.getSelectedProvinces().getProvince(i)).getCivID()).getAllianceID() > 0) {
               for (int j = 0;
                  j
                     < CFG.game
                        .getAlliance(CFG.game.getCiv(CFG.game.getProvince(CFG.game.getSelectedProvinces().getProvince(i)).getCivID()).getAllianceID())
                        .getCivilizationsSize();
                  j++
               ) {
                  this.addCiv(
                     CFG.game
                        .getAlliance(CFG.game.getCiv(CFG.game.getProvince(CFG.game.getSelectedProvinces().getProvince(i)).getCivID()).getAllianceID())
                        .getCivilization(j)
                  );
               }
            }

            if (CFG.game.getProvince(CFG.game.getSelectedProvinces().getProvince(i)).getCivID()
               != CFG.game.getCiv(CFG.game.getProvince(CFG.game.getSelectedProvinces().getProvince(i)).getCivID()).getPuppetOfCivID()) {
               this.addCiv(CFG.game.getCiv(CFG.game.getProvince(CFG.game.getSelectedProvinces().getProvince(i)).getCivID()).getPuppetOfCivID());
            }

            for (int j = 1; j < CFG.game.getCivsSize(); j++) {
               if (CFG.game.getCiv(j).getPuppetOfCivID() != CFG.game.getProvince(CFG.game.getSelectedProvinces().getProvince(i)).getCivID()) {
               }
            }
         }
      }

      for (int var6 = 0; var6 < this.lCivs.size(); var6++) {
         menuElements.add(
            new Button_New_Game_Players_CivID(
               this.lCivs.get(var6),
               CFG.game.getCiv(this.lCivs.get(var6)).getCivName(),
               CFG.PADDING,
               CFG.PADDING,
               var6 > 0 ? menuElements.get(var6 - 1).getPosY() + menuElements.get(var6 - 1).getHeight() + CFG.PADDING : CFG.PADDING,
               tempW - CFG.PADDING * 2,
               true
            )
         );
      }

      this.initMenu(
         new SliderMenuTitle(null, CFG.BUTTON_HEIGHT * 3 / 4, false, false) {
            @Override
            public void draw(SpriteBatch oSB, int iTranslateX, int nPosX, int nPosY, int nWidth, boolean sliderMenuIsActive) {
               ImageManager.getImage(Images.dialog_title)
                  .draw2(
                     oSB,
                     nPosX - 2 + iTranslateX,
                     nPosY - this.getHeight() - ImageManager.getImage(Images.dialog_title).getHeight(),
                     nWidth + 2,
                     this.getHeight()
                  );
               oSB.setColor(new Color(0.23529412F, 0.3137255F, 0.4117647F, 0.165F));
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
               oSB.setColor(new Color(0.23529412F, 0.3137255F, 0.4117647F, 0.375F));
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
               CFG.fontMain.getData().setScale(0.8F);
               CFG.drawText(
                  oSB,
                  this.getText(),
                  nPosX + (int)(nWidth - this.getTextWidth() * 0.8F) / 2 + iTranslateX,
                  2 + nPosY - this.getHeight() + (int)(this.getHeight() - this.getTextHeight() * 0.8F) / 2,
                  Color.WHITE
               );
               CFG.fontMain.getData().setScale(1.0F);
            }
         },
         CFG.GAME_WIDTH - tempW,
         CFG.BUTTON_HEIGHT * 2 + CFG.PADDING * 4 + CFG.PADDING + CFG.BUTTON_HEIGHT * 3 / 4,
         tempW,
         Math.min(
            menuElements.size() > 0
               ? menuElements.get(menuElements.size() - 1).getPosY() + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING
               : CFG.PADDING,
            CFG.GAME_HEIGHT
               - (CFG.BUTTON_HEIGHT * 2 + CFG.PADDING * 4 + CFG.PADDING * 2 + CFG.BUTTON_HEIGHT / 2)
               - CFG.BUTTON_HEIGHT * 2
               - CFG.PADDING * 4
               - CFG.BUTTON_HEIGHT
               - CFG.PADDING * 2
         ),
         menuElements,
         true,
         true
      );
      if (this.lCivs.size() == 0) {
         this.setVisible(false);
      }

      this.updateLanguage();
   }

   @Override
   public void updateLanguage() {
      this.getTitle().setText(CFG.langManager.get("Civilizations"));
   }

   @Override
   public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      ImageManager.getImage(Images.new_game_top_edge_line)
         .draw2(
            oSB,
            this.getPosX() - 2 + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.new_game_top_edge_line).getHeight() + iTranslateY,
            this.getWidth() + 4,
            this.getHeight(),
            false,
            true
         );
      super.draw(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
      oSB.setColor(CFG.COLOR_CREATE_NEW_GAME_BOX_PLAYERS);
      ImageManager.getImage(Images.pix255_255_255)
         .draw(
            oSB,
            this.getPosX() - 2 + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + this.getHeight(),
            this.getWidth() + 2
         );
      oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.4F));
      ImageManager.getImage(Images.line_32_off1)
         .draw(
            oSB,
            this.getPosX() - 2 + iTranslateX,
            this.getPosY()
               - ImageManager.getImage(Images.pix255_255_255).getHeight()
               - ImageManager.getImage(Images.line_32_off1).getHeight()
               + this.getHeight(),
            this.getWidth() + 2,
            1
         );
      oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.5F));
      ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() - 2 + iTranslateX, this.getPosY() + this.getHeight(), this.getWidth() + 2);
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
      boolean rebuildSliders = false;
      if (CFG.game.getSelectedProvinces().getProvincesSize() == 0
         && CFG.game.getActiveProvinceID() >= 0
         && CFG.game.getProvince(CFG.game.getActiveProvinceID()).getSeaProvince()) {
         if (this.tryAddArmy(CFG.game.getActiveProvinceID(), this.getMenuElement(iID).getCurrent())) {
            rebuildSliders = true;
         }
      } else {
         for (int i = 0; i < CFG.game.getSelectedProvinces().getProvincesSize(); i++) {
            if (this.tryAddArmy(CFG.game.getSelectedProvinces().getProvince(i), this.getMenuElement(iID).getCurrent())) {
               rebuildSliders = true;
            }
         }
      }

      if (rebuildSliders) {
         CFG.menuManager.rebuildCreateScenario_SetUpArmies_Sliders();
      }
   }

   public final boolean tryAddArmy(int nProvinceID, int nCivID) {
      if (CFG.game.getProvince(nProvinceID).getSeaProvince()) {
         if (CFG.game.getProvince(nProvinceID).getArmyCivID(nCivID) == 0) {
            CFG.game.getProvince(nProvinceID).updateArmy(nCivID, 500);
            return true;
         }
      } else {
         boolean addArmy = false;
         if (CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getAllianceID() > 0
            && CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getAllianceID() == CFG.game.getCiv(nCivID).getAllianceID()) {
            addArmy = true;
         }

         if (CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getPuppetOfCivID() == nCivID) {
            addArmy = true;
         }

         if (addArmy && CFG.game.getProvince(nProvinceID).getArmyCivID(nCivID) == 0) {
            CFG.game.getProvince(nProvinceID).updateArmy(nCivID, 500);
            return true;
         }
      }

      return false;
   }
}
