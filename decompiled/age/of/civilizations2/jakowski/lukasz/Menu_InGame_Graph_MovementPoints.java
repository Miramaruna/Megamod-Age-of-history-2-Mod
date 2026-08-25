package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

public class Menu_InGame_Graph_MovementPoints extends SliderMenu {
   public Menu_InGame_Graph_MovementPoints() {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      this.initMenu(
         new SliderMenuTitle(CFG.langManager.get("Statistics"), CFG.BUTTON_HEIGHT / 2, true, false) {
            @Override
            public void draw(SpriteBatch oSB, int iTranslateX, int nPosX, int nPosY, int nWidth, boolean sliderMenuIsActive) {
               ImageManager.getImage(Images.new_game_top_edge_title)
                  .draw2(
                     oSB,
                     Menu_InGame_Graph_MovementPoints.this.getPosX() + iTranslateX,
                     Menu_InGame_Graph_MovementPoints.this.getPosY() - ImageManager.getImage(Images.new_game_top_edge_title).getHeight() - this.getHeight(),
                     Menu_InGame_Graph_MovementPoints.this.getWidth() - ImageManager.getImage(Images.new_game_top_edge_title).getWidth(),
                     this.getHeight(),
                     false,
                     false
                  );
               ImageManager.getImage(Images.new_game_top_edge_title)
                  .draw2(
                     oSB,
                     Menu_InGame_Graph_MovementPoints.this.getPosX()
                        + Menu_InGame_Graph_MovementPoints.this.getWidth()
                        - ImageManager.getImage(Images.new_game_top_edge_title).getWidth()
                        + iTranslateX,
                     Menu_InGame_Graph_MovementPoints.this.getPosY() - ImageManager.getImage(Images.new_game_top_edge_title).getHeight() - this.getHeight(),
                     ImageManager.getImage(Images.new_game_top_edge_title).getWidth(),
                     this.getHeight(),
                     true,
                     false
                  );
               oSB.setColor(new Color(0.0627451F, 0.09411765F, 0.25490198F, 0.45F));
               ImageManager.getImage(Images.gradient)
                  .draw(
                     oSB,
                     Menu_InGame_Graph_MovementPoints.this.getPosX() + iTranslateX,
                     Menu_InGame_Graph_MovementPoints.this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() - this.getHeight() * 3 / 4,
                     Menu_InGame_Graph_MovementPoints.this.getWidth(),
                     this.getHeight() * 3 / 4,
                     false,
                     true
                  );
               oSB.setColor(new Color(0.451F, 0.329F, 0.11F, 1.0F));
               ImageManager.getImage(Images.pix255_255_255)
                  .draw(
                     oSB,
                     Menu_InGame_Graph_MovementPoints.this.getPosX() + 2 + iTranslateX,
                     Menu_InGame_Graph_MovementPoints.this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight(),
                     Menu_InGame_Graph_MovementPoints.this.getWidth() - 4
                  );
               oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.4F));
               ImageManager.getImage(Images.line_32_off1)
                  .draw(
                     oSB,
                     Menu_InGame_Graph_MovementPoints.this.getPosX() + iTranslateX,
                     Menu_InGame_Graph_MovementPoints.this.getPosY()
                        - ImageManager.getImage(Images.pix255_255_255).getHeight()
                        - ImageManager.getImage(Images.line_32_off1).getHeight(),
                     Menu_InGame_Graph_MovementPoints.this.getWidth(),
                     1
                  );
               oSB.setColor(Color.WHITE);
               CFG.fontMain.getData().setScale(0.6F);
               CFG.drawText(
                  oSB,
                  this.getText(),
                  nPosX + nWidth / 2 - (int)(this.getTextWidth() * 0.6F / 2.0F) + iTranslateX,
                  nPosY - this.getHeight() + this.getHeight() / 2 - (int)(this.getTextHeight() * 0.6F / 2.0F),
                  Color.WHITE
               );
               CFG.fontMain.getData().setScale(1.0F);
            }
         },
         150,
         150,
         500,
         325,
         menuElements,
         true,
         true
      );
      this.updateLanguage();
      this.getMenuElement(0).setCheckboxState(true);
   }

   @Override
   public void updateLanguage() {
   }

   @Override
   public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      oSB.setColor(Color.WHITE);
      ImageManager.getImage(Images.new_game_box)
         .draw2(
            oSB,
            this.getPosX() + iTranslateX,
            -ImageManager.getImage(Images.new_game_box).getHeight() + this.getMenuPosY() + iTranslateY,
            this.getW() - ImageManager.getImage(Images.new_game_box).getWidth(),
            this.getH(),
            false,
            true
         );
      ImageManager.getImage(Images.new_game_box)
         .draw2(
            oSB,
            this.getPosX() + this.getW() - ImageManager.getImage(Images.new_game_box).getWidth() + iTranslateX,
            -ImageManager.getImage(Images.new_game_box).getHeight() + this.getMenuPosY() + iTranslateY,
            ImageManager.getImage(Images.new_game_box).getWidth(),
            this.getH(),
            true,
            true
         );
      super.draw(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
   }

   @Override
   public final void actionElement(int iID) {
      switch (iID) {
      }
   }

   public final int getW() {
      return this.getWidth();
   }

   public final int getH() {
      return this.getHeight();
   }

   @Override
   public boolean setWidth(int iWidth) {
      boolean out = super.setWidth(iWidth);
      this.getMenuElement(0).setCheckboxState(true);
      return out;
   }
}
