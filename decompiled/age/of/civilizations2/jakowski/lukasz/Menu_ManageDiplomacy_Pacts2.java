package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

public class Menu_ManageDiplomacy_Pacts2 extends SliderMenu {
   public Menu_ManageDiplomacy_Pacts2() {
      int tempW = CFG.CIV_INFO_MENU_WIDTH;
      int tempElemH = CFG.BUTTON_HEIGHT * 3 / 4;
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      int nAddedNum = 1;

      for (int i = 1; i < CFG.game.getCivsSize(); i++) {
         for (int j = i + 1; j < CFG.game.getCivsSize(); j++) {
            if (CFG.game.getCivNonAggressionPact(i, j) > 0) {
               menuElements.add(
                  new Slider_BG_CNG_Pact(
                     i,
                     j,
                     CFG.langManager.get("Turns") + ": ",
                     CFG.PADDING * 2,
                     CFG.PADDING + tempElemH * nAddedNum,
                     tempW - CFG.PADDING * 4,
                     tempElemH - CFG.PADDING * 2,
                     1,
                     200,
                     CFG.game.getCivNonAggressionPact(i, j)
                  )
               );
               nAddedNum++;
            }
         }
      }

      menuElements.add(new Button_CNG_Options(null, -1, 0, 0, tempW, tempElemH, true));
      this.initMenu(
         new SliderMenuTitle(null, CFG.BUTTON_HEIGHT * 3 / 4, false, false) {
            @Override
            public void draw(SpriteBatch oSB, int iTranslateX, int nPosX, int nPosY, int nWidth, boolean sliderMenuIsActive) {
               ImageManager.getImage(Images.new_game_top_edge_title)
                  .draw2(
                     oSB,
                     Menu_ManageDiplomacy_Pacts2.this.getPosX() + iTranslateX,
                     Menu_ManageDiplomacy_Pacts2.this.getPosY() - ImageManager.getImage(Images.new_game_top_edge_title).getHeight() - this.getHeight(),
                     Menu_ManageDiplomacy_Pacts2.this.getWidth() + 2,
                     this.getHeight(),
                     true,
                     false
                  );
               oSB.setColor(new Color(0.011F, 0.014F, 0.019F, 0.25F));
               ImageManager.getImage(Images.gradient)
                  .draw(
                     oSB,
                     Menu_ManageDiplomacy_Pacts2.this.getPosX() + iTranslateX,
                     Menu_ManageDiplomacy_Pacts2.this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() - this.getHeight() * 3 / 4,
                     Menu_ManageDiplomacy_Pacts2.this.getWidth(),
                     this.getHeight() * 3 / 4,
                     false,
                     true
                  );
               oSB.setColor(new Color(0.451F, 0.329F, 0.11F, 1.0F));
               ImageManager.getImage(Images.pix255_255_255)
                  .draw(
                     oSB,
                     Menu_ManageDiplomacy_Pacts2.this.getPosX() + iTranslateX,
                     Menu_ManageDiplomacy_Pacts2.this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight(),
                     Menu_ManageDiplomacy_Pacts2.this.getWidth()
                  );
               oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.4F));
               ImageManager.getImage(Images.line_32_off1)
                  .draw(
                     oSB,
                     Menu_ManageDiplomacy_Pacts2.this.getPosX() + iTranslateX,
                     Menu_ManageDiplomacy_Pacts2.this.getPosY()
                        - ImageManager.getImage(Images.pix255_255_255).getHeight()
                        - ImageManager.getImage(Images.line_32_off1).getHeight(),
                     Menu_ManageDiplomacy_Pacts2.this.getWidth(),
                     1
                  );
               oSB.setColor(Color.WHITE);
               CFG.fontMain.getData().setScale(0.75F);
               CFG.drawText(
                  oSB,
                  this.getText(),
                  nPosX + nWidth / 2 - (int)(this.getTextWidth() * 0.75F / 2.0F) + iTranslateX,
                  nPosY - this.getHeight() + this.getHeight() / 2 + 1 - (int)(this.getTextHeight() * 0.75F / 2.0F),
                  Color.WHITE
               );
               CFG.fontMain.getData().setScale(1.0F);
            }
         },
         0,
         CFG.BUTTON_HEIGHT,
         tempW,
         Math.min(tempElemH * menuElements.size(), CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT * 2 - CFG.PADDING * 3),
         menuElements
      );
      this.setVisible(false);
      this.updateLanguage();
   }

   @Override
   public void updateLanguage() {
      this.getTitle().setText(CFG.langManager.get("NonAggressionPact"));
      this.getMenuElement(this.getMenuElementsSize() - 1).setText(CFG.langManager.get("AddNewPact"));
   }

   @Override
   public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      ImageManager.getImage(Images.new_game_top_edge_line)
         .draw2(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.new_game_top_edge_line).getHeight() + iTranslateY,
            this.getWidth() + 2,
            this.getHeight(),
            true,
            true
         );
      super.draw(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
      oSB.setColor(CFG.COLOR_CREATE_NEW_GAME_BOX_PLAYERS);
      ImageManager.getImage(Images.pix255_255_255)
         .draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + this.getHeight(), this.getWidth());
      oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.4F));
      ImageManager.getImage(Images.line_32_off1)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY()
               - ImageManager.getImage(Images.pix255_255_255).getHeight()
               - ImageManager.getImage(Images.line_32_off1).getHeight()
               + this.getHeight(),
            this.getWidth(),
            1
         );
      oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.5F));
      ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight(), this.getWidth() + 2);
      oSB.setColor(Color.WHITE);
   }

   @Override
   public final void actionElement(int iID) {
      if (iID == this.getMenuElementsSize() - 1) {
         CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1 = -1;
         CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV2 = -1;
         Game_Render_Province.updateDrawProvinces();
         CFG.map.getMapTouchManager().update_ExtraAction();
      } else {
         this.updateNonAggressionPact(iID, this.getMenuElement(iID).getCurrent());
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
