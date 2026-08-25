package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

public class Menu_MapEditor_Connections_IDs extends SliderMenu {
   public Menu_MapEditor_Connections_IDs(int nProvinceID) {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      int tempY = 0;
      if (nProvinceID >= 0) {
         for (int i = 0; i < CFG.game.getProvince(nProvinceID).getNeighboringProvincesSize(); i++) {
            menuElements.add(
               new Button_Game(
                  "" + CFG.game.getProvince(nProvinceID).getNeighboringProvinces(i),
                  -1,
                  CFG.PADDING,
                  CFG.PADDING * (tempY + 1) + CFG.BUTTON_HEIGHT * tempY,
                  CFG.BUTTON_WIDTH
               )
            );
            tempY++;
         }

         for (int var5 = 0; var5 < CFG.game.getProvince(nProvinceID).getNeighboringSeaProvincesSize(); var5++) {
            menuElements.add(
               new Button_Game(
                  "" + CFG.game.getProvince(nProvinceID).getNeighboringSeaProvinces(var5),
                  -1,
                  CFG.PADDING,
                  CFG.PADDING * (tempY + 1) + CFG.BUTTON_HEIGHT * tempY,
                  CFG.BUTTON_WIDTH
               )
            );
            tempY++;
         }
      }

      this.initMenu(
         new SliderMenuTitle("ACT: " + nProvinceID, CFG.BUTTON_HEIGHT * 3 / 4, menuElements.size() > 0, false) {
            @Override
            public void draw(SpriteBatch oSB, int iTranslateX, int nPosX, int nPosY, int nWidth, boolean sliderMenuIsActive) {
               ImageManager.getImage(Images.new_game_top_edge)
                  .draw2(
                     oSB,
                     nPosX - 2 + iTranslateX,
                     nPosY - ImageManager.getImage(Images.new_game_top_edge).getHeight() - this.getHeight(),
                     Menu_MapEditor_Connections_IDs.this.getWidth() + 2,
                     this.getHeight()
                  );
               oSB.setColor(CFG.COLOR_CREATE_NEW_GAME_BOX_PLAYERS);
               ImageManager.getImage(Images.pix255_255_255)
                  .draw2(
                     oSB,
                     nPosX - 1 + iTranslateX,
                     nPosY - ImageManager.getImage(Images.pix255_255_255).getHeight(),
                     Menu_MapEditor_Connections_IDs.this.getWidth() + 1,
                     1
                  );
               oSB.setColor(Color.WHITE);
               super.drawText(oSB, iTranslateX, nPosX, nPosY, nWidth, sliderMenuIsActive);
            }
         },
         CFG.GAME_WIDTH - CFG.BUTTON_WIDTH - CFG.PADDING * 2,
         CFG.BUTTON_HEIGHT * 3 / 4,
         CFG.BUTTON_WIDTH + CFG.PADDING * 2,
         CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT * 3 / 4,
         menuElements
      );
   }

   @Override
   public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      if (this.getMenuElementsSize() > 0) {
         ImageManager.getImage(Images.new_game_box)
            .draw2(
               oSB,
               this.getPosX() - 2 + iTranslateX,
               this.getPosY() - ImageManager.getImage(Images.new_game_box).getHeight(),
               this.getWidth() + 2,
               this.getMenuElement(this.getMenuElementsSize() - 1).getPosY() + this.getMenuElement(this.getMenuElementsSize() - 1).getHeight() + CFG.PADDING,
               false,
               true
            );
      }

      super.draw(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
   }

   @Override
   public final void actionElement(int iID) {
      try {
         CFG.game.setActiveProvinceID(Integer.parseInt(this.getMenuElement(iID).getText()));
         CFG.map.getMapCoordinates().centerToProvinceID(Integer.parseInt(this.getMenuElement(iID).getText()));
         CFG.toast.setInView(" --" + CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV2 + " - " + CFG.game.getActiveProvinceID() + "-- ");
      } catch (IllegalArgumentException var3) {
      }
   }
}
