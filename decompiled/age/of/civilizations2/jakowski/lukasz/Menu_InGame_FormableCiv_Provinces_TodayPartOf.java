package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

public class Menu_InGame_FormableCiv_Provinces_TodayPartOf extends SliderMenu {
   public Menu_InGame_FormableCiv_Provinces_TodayPartOf() {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      int tMenuWidth = CFG.CIV_INFO_MENU_WIDTH * 3 / 4;
      int tElementH = Math.max(CFG.TEXT_HEIGHT + CFG.PADDING * 4, CFG.CIV_FLAG_HEIGHT + CFG.PADDING * 2);
      int tPosY = 0;
      ArrayList<Integer> tempPartOf = new ArrayList<>();
      ArrayList<Integer> tempProvinces = new ArrayList<>();
      int nFormableCivMapProvinces = 0;
      if (CFG.FOG_OF_WAR == 2) {
         int iPartOfSize = 0;

         for (int i = 0; i < CFG.formableCivs_GameData.getProvincesSize(); i++) {
            if (!CFG.game.getProvince(CFG.formableCivs_GameData.getProvinceID(i)).getSeaProvince()
               && CFG.game.getProvince(CFG.formableCivs_GameData.getProvinceID(i)).getWasteland() < 0
               && CFG.game.getProvince(CFG.formableCivs_GameData.getProvinceID(i)).getCivID() > 0) {
               boolean tAdd = true;
               int tempCivID = CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetProvince(CFG.formableCivs_GameData.getProvinceID(i))
                  ? CFG.game.getProvince(CFG.formableCivs_GameData.getProvinceID(i)).getCivID()
                  : -1;

               for (int j = 0; j < iPartOfSize; j++) {
                  if (tempPartOf.get(j) == tempCivID) {
                     tAdd = false;
                     tempProvinces.set(j, tempProvinces.get(j) + 1);
                     break;
                  }
               }

               if (tAdd) {
                  tempPartOf.add(tempCivID);
                  tempProvinces.add(1);
                  iPartOfSize++;
               }

               nFormableCivMapProvinces++;
            }
         }
      } else {
         int iPartOfSize = 0;

         for (int ix = 0; ix < CFG.formableCivs_GameData.getProvincesSize(); ix++) {
            if (!CFG.game.getProvince(CFG.formableCivs_GameData.getProvinceID(ix)).getSeaProvince()
               && CFG.game.getProvince(CFG.formableCivs_GameData.getProvinceID(ix)).getWasteland() < 0
               && CFG.game.getProvince(CFG.formableCivs_GameData.getProvinceID(ix)).getCivID() > 0) {
               boolean tAdd = true;

               for (int jx = 0; jx < iPartOfSize; jx++) {
                  if (tempPartOf.get(jx) == CFG.game.getProvince(CFG.formableCivs_GameData.getProvinceID(ix)).getCivID()) {
                     tAdd = false;
                     tempProvinces.set(jx, tempProvinces.get(jx) + 1);
                     break;
                  }
               }

               if (tAdd) {
                  tempPartOf.add(CFG.game.getProvince(CFG.formableCivs_GameData.getProvinceID(ix)).getCivID());
                  tempProvinces.add(1);
                  iPartOfSize++;
               }

               nFormableCivMapProvinces++;
            }
         }
      }

      int iSize = tempPartOf.size();

      for (int var16 = 0; var16 < iSize; var16++) {
         for (int jxx = var16 + 1; jxx < iSize; jxx++) {
            if (tempProvinces.get(var16) < tempProvinces.get(jxx)) {
               int tRev = tempPartOf.get(var16);
               tempPartOf.set(var16, tempPartOf.get(jxx));
               tempPartOf.set(jxx, tRev);
               tRev = tempProvinces.get(var16);
               tempProvinces.set(var16, tempProvinces.get(jxx));
               tempProvinces.set(jxx, tRev);
            }
         }
      }

      for (int var17 = 0; var17 < tempPartOf.size(); var17++) {
         menuElements.add(
            new Button_TodayPartOf(
               tempPartOf.get(var17), CFG.getPercentage(tempProvinces.get(var17), nFormableCivMapProvinces, 4), 0, tPosY, tMenuWidth, tElementH, true
            )
         );
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

      for (int var18 = 0; var18 < this.getMenuElementsSize(); var18++) {
         this.getMenuElement(var18).setCurrent(var18 % 2);
      }

      this.updateLanguage();
   }

   @Override
   public void updateLanguage() {
      this.getTitle().setText(CFG.langManager.get("TodayPartOf"));
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
         CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID = CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID == this.getMenuElement(iID).getCurrent()
            ? CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()
            : this.getMenuElement(iID).getCurrent();

         for (int i = 0; i < CFG.game.getCiv(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID).getNumOfProvinces(); i++) {
            CFG.game.getProvince(CFG.game.getCiv(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID).getProvinceID(i)).setFromCivID(0);
         }

         CFG.toast.setInView(CFG.game.getCiv(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID).getCivName());
      } catch (IndexOutOfBoundsException var3) {
         CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID = CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID();
         CFG.toast.setInView(CFG.langManager.get("Undiscovered"), CFG.COLOR_TEXT_NUM_OF_PROVINCES);
      }
   }
}
