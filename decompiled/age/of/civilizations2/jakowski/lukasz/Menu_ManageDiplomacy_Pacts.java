package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

public class Menu_ManageDiplomacy_Pacts extends SliderMenu {
   public Menu_ManageDiplomacy_Pacts() {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      int multiplePosY = 0;

      for (int i = 1; i < CFG.game.getCivsSize(); i++) {
         for (int j = i + 1; j < CFG.game.getCivsSize(); j++) {
            if (CFG.game.getCivNonAggressionPact(i, j) > 0) {
               menuElements.add(
                  new Button_Flag(
                     i,
                     0,
                     CFG.PADDING * (multiplePosY + 2) + CFG.BUTTON_HEIGHT * (multiplePosY + 1),
                     CFG.CIV_FLAG_WIDTH + CFG.PADDING * 4,
                     CFG.BUTTON_HEIGHT,
                     Button_Flag.ButtonFlagType.FLAG
                  )
               );
               menuElements.add(
                  new Button_Flag(
                     j,
                     CFG.CIV_FLAG_WIDTH + CFG.PADDING * 4,
                     CFG.PADDING * (multiplePosY + 2) + CFG.BUTTON_HEIGHT * (multiplePosY + 1),
                     CFG.CIV_FLAG_WIDTH + CFG.PADDING * 4,
                     CFG.BUTTON_HEIGHT,
                     Button_Flag.ButtonFlagType.FLAG
                  )
               );
               menuElements.add(
                  new Button_Menu_Classic(
                     "-",
                     -1,
                     (CFG.CIV_FLAG_WIDTH + CFG.PADDING * 4) * 2,
                     CFG.PADDING * (multiplePosY + 2) + CFG.BUTTON_HEIGHT * (multiplePosY + 1),
                     CFG.BUTTON_HEIGHT,
                     CFG.BUTTON_HEIGHT,
                     true
                  )
               );
               menuElements.add(
                  new Slider_Pact(
                     i,
                     j,
                     (CFG.CIV_FLAG_WIDTH + CFG.PADDING * 4) * 2 + CFG.BUTTON_HEIGHT,
                     CFG.PADDING * (multiplePosY + 2) + CFG.BUTTON_HEIGHT * (multiplePosY + 1) + CFG.PADDING,
                     CFG.GAME_WIDTH - (CFG.CIV_FLAG_WIDTH + CFG.PADDING * 4) * 2 - CFG.BUTTON_HEIGHT * 3,
                     CFG.BUTTON_HEIGHT - CFG.PADDING * 2,
                     1,
                     200,
                     CFG.game.getCivNonAggressionPact(i, j)
                  )
               );
               menuElements.add(
                  new Button_Menu_Classic(
                     "+",
                     -1,
                     CFG.GAME_WIDTH - CFG.BUTTON_HEIGHT * 2,
                     CFG.PADDING * (multiplePosY + 2) + CFG.BUTTON_HEIGHT * (multiplePosY + 1),
                     CFG.BUTTON_HEIGHT,
                     CFG.BUTTON_HEIGHT,
                     true
                  )
               );
               menuElements.add(
                  new Button_Menu_Remove(
                     CFG.GAME_WIDTH - CFG.BUTTON_HEIGHT,
                     CFG.PADDING * (multiplePosY + 2) + CFG.BUTTON_HEIGHT * (multiplePosY + 1),
                     CFG.BUTTON_HEIGHT,
                     CFG.BUTTON_HEIGHT,
                     true
                  )
               );
               multiplePosY++;
            }
         }
      }

      menuElements.add(new Button_Menu(null, -1, 0, CFG.PADDING, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT, true));
      this.initMenu(
         new SliderMenuTitle(null, CFG.BUTTON_HEIGHT * 3 / 4, false, false),
         0,
         CFG.BUTTON_HEIGHT * 3 / 4,
         CFG.GAME_WIDTH,
         (CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT * 3 / 4 - CFG.BUTTON_HEIGHT - CFG.PADDING * 2) / 2,
         menuElements,
         false,
         false
      );
      this.updateLanguage();
   }

   @Override
   public void updateLanguage() {
      this.getMenuElement(this.getMenuElementsSize() - 1).setText(CFG.langManager.get("AddNewPact"));
      this.getTitle().setText(CFG.langManager.get("CustomizePacts"));
   }

   @Override
   public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      super.draw(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
      if (this.getScrollableY()) {
         oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.85F));
         ImageManager.getImage(Images.pix255_255_255)
            .draw(
               oSB,
               iTranslateX,
               this.getPosY() + this.getHeight() - 1 - ImageManager.getImage(Images.pix255_255_255).getHeight(),
               this.getWidth(),
               1,
               false,
               true
            );
         ImageManager.getImage(Images.gradient)
            .draw(
               oSB,
               iTranslateX,
               this.getPosY() + this.getHeight() - CFG.PADDING * 2 - ImageManager.getImage(Images.gradient).getHeight(),
               this.getWidth(),
               CFG.PADDING * 2,
               false,
               true
            );
         oSB.setColor(Color.WHITE);
      }
   }

   @Override
   public final void actionElement(int iID) {
      if (iID == this.getMenuElementsSize() - 1) {
         CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1 = -1;
         CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV2 = -1;
         Game_Render_Province.updateDrawProvinces();
         CFG.map.getMapTouchManager().update_ExtraAction();
      } else {
         if (iID % 6 == 0) {
            this.centerToPactID(iID / 6, true);
         } else if (iID % 6 == 1) {
            this.centerToPactID(iID / 6, false);
         } else if (iID % 6 == 2) {
            this.getMenuElement(iID + 1).setCurrent(this.getMenuElement(iID + 1).getCurrent() - 1);
            this.updateNonAggressionPact(iID / 6, this.getMenuElement(iID + 1).getCurrent());
         } else if (iID % 6 == 3) {
            this.updateNonAggressionPact(iID / 6, this.getMenuElement(iID).getCurrent());
         } else if (iID % 6 == 4) {
            this.getMenuElement(iID - 1).setCurrent(this.getMenuElement(iID - 1).getCurrent() + 1);
            this.updateNonAggressionPact(iID / 6, this.getMenuElement(iID - 1).getCurrent());
         } else if (iID % 6 == 5) {
            this.updateNonAggressionPact(iID / 6, 0);
         }
      }
   }

   public final void centerToPactID(int pactID, boolean civA) {
      int foundPacts = 0;

      for (int i = 1; i < CFG.game.getCivsSize(); i++) {
         for (int j = i + 1; j < CFG.game.getCivsSize(); j++) {
            if (CFG.game.getCivNonAggressionPact(i, j) > 0) {
               if (foundPacts == pactID) {
                  if (civA) {
                     CFG.game.setActiveProvinceID(CFG.game.getCiv(i).getCapitalProvinceID());
                     CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1 = i;
                     CFG.map.getMapCoordinates().centerToProvinceID(CFG.game.getActiveProvinceID());
                     CFG.toast.setInView(CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).getCivName());
                  } else {
                     CFG.game.setActiveProvinceID(CFG.game.getCiv(j).getCapitalProvinceID());
                     CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1 = j;
                     CFG.map.getMapCoordinates().centerToProvinceID(CFG.game.getActiveProvinceID());
                     CFG.toast.setInView(CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).getCivName());
                  }

                  return;
               }

               foundPacts++;
            }
         }
      }
   }

   public final void updateNonAggressionPact(int pactID, int iNumOfTurns) {
      int foundPacts = 0;

      for (int i = 1; i < CFG.game.getCivsSize(); i++) {
         for (int j = i + 1; j < CFG.game.getCivsSize(); j++) {
            if (CFG.game.getCivNonAggressionPact(i, j) > 0) {
               if (foundPacts == pactID) {
                  CFG.game.setCivNonAggressionPact(i, j, iNumOfTurns);
                  if (CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1 != i && CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1 != j) {
                     CFG.game.setActiveProvinceID(CFG.game.getCiv(i).getCapitalProvinceID());
                     CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1 = i;
                     CFG.map.getMapCoordinates().centerToProvinceID(CFG.game.getActiveProvinceID());
                  }

                  return;
               }

               foundPacts++;
            }
         }
      }
   }
}
