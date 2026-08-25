package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.utils.ScissorStack;
import java.util.ArrayList;

public class Menu_InGame_ProvinceAction_Uncivilized extends SliderMenu {
   public Menu_InGame_ProvinceAction_Uncivilized() {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      menuElements.add(
         new Button_Game(null, -1, CFG.PADDING, CFG.PADDING, true) {
            @Override
            public int getTextWidth() {
               return super.getTextWidth() + CFG.PADDING + CFG.CIV_FLAG_WIDTH;
            }

            @Override
            public void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
               CFG.game
                  .getCiv(CFG.game.getActiveCivID())
                  .getFlag()
                  .draw(
                     oSB,
                     this.getPosX() + this.getWidth() / 2 - this.getTextWidth() / 2 + iTranslateX,
                     this.getPosY()
                        + this.getHeight() / 2
                        - CFG.CIV_FLAG_HEIGHT / 2
                        - CFG.game.getCiv(CFG.game.getActiveCivID()).getFlag().getHeight()
                        + iTranslateY,
                     CFG.CIV_FLAG_WIDTH,
                     CFG.CIV_FLAG_HEIGHT
                  );
               ImageManager.getImage(Images.flag_rect)
                  .draw(
                     oSB,
                     this.getPosX() + this.getWidth() / 2 - this.getTextWidth() / 2 + iTranslateX,
                     this.getPosY() + this.getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 + iTranslateY
                  );
               if (isActive) {
                  CFG.drawText(
                     oSB,
                     this.getTextToDraw(),
                     this.getPosX() + this.getWidth() / 2 - this.getTextWidth() / 2 + CFG.PADDING + CFG.CIV_FLAG_WIDTH + iTranslateX,
                     this.getPosY() + this.getHeight() / 2 - this.getTextHeight() / 2 + iTranslateY,
                     this.getColor(isActive)
                  );
               } else {
                  CFG.drawTextWithShadow(
                     oSB,
                     this.getTextToDraw(),
                     this.getPosX() + this.getWidth() / 2 - this.getTextWidth() / 2 + CFG.PADDING + CFG.CIV_FLAG_WIDTH + iTranslateX,
                     this.getPosY() + this.getHeight() / 2 - this.getTextHeight() / 2 + iTranslateY,
                     this.getColor(isActive)
                  );
               }
            }

            @Override
            public boolean getClickable() {
               return Game.uncivilizedCanMigrate(CFG.game.getActiveProvinceID(), CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
            }
         }
      );
      menuElements.add(
         new Button_Game(null, -1, CFG.PADDING, CFG.PADDING, true) {
            @Override
            public int getPosX() {
               return Menu_InGame_ProvinceAction_Uncivilized.this.getMenuElement(0).getPosX()
                  + Menu_InGame_ProvinceAction_Uncivilized.this.getMenuElement(0).getWidth()
                  + CFG.PADDING;
            }

            @Override
            public void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
               if (isActive) {
                  CFG.drawText(
                     oSB,
                     this.getTextToDraw(),
                     this.getPosX() + this.getWidth() / 2 - this.getTextWidth() / 2 + iTranslateX,
                     this.getPosY() + this.getHeight() / 2 - this.getTextHeight() / 2 + iTranslateY,
                     this.getColor(isActive)
                  );
               } else {
                  CFG.drawTextWithShadow(
                     oSB,
                     this.getTextToDraw(),
                     this.getPosX() + this.getWidth() / 2 - this.getTextWidth() / 2 + iTranslateX,
                     this.getPosY() + this.getHeight() / 2 - this.getTextHeight() / 2 + iTranslateY,
                     this.getColor(isActive)
                  );
               }
            }
         }
      );
      this.initMenu(
         null,
         0,
         CFG.GAME_HEIGHT - CFG.map.getMapBG().getMinimapHeight() - CFG.BUTTON_HEIGHT - CFG.PADDING * 2,
         CFG.GAME_WIDTH,
         CFG.BUTTON_HEIGHT + CFG.PADDING * 2,
         menuElements,
         false,
         false
      );
      this.updateLanguage();
      CFG.fMOVE_MENU_PERCENTAGE = 5.0F;
      CFG.lMOVE_MENU_TIME = System.currentTimeMillis();
   }

   @Override
   public void updateLanguage() {
      this.getMenuElement(0).setText(CFG.langManager.get("Migrate"));
      this.getMenuElement(1).setText(CFG.langManager.get("Hunt"));
      this.updatedButtonsWidth(CFG.PADDING, CFG.BUTTON_WIDTH);
   }

   @Override
   public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      if ((CFG.fMOVE_MENU_PERCENTAGE = CFG.fMOVE_MENU_PERCENTAGE + (float)(System.currentTimeMillis() - CFG.lMOVE_MENU_TIME) / 300.0F * 95.0F) > 100.0F) {
         CFG.fMOVE_MENU_PERCENTAGE = 100.0F;
      } else {
         CFG.setRender_3(true);
      }

      CFG.lMOVE_MENU_TIME = System.currentTimeMillis();
      Rectangle clipBounds = new Rectangle(
         this.getPosX() + iTranslateX, CFG.GAME_HEIGHT - this.getPosY() + 1 - iTranslateY, this.getWidth(), -this.getHeight() - 1
      );
      oSB.flush();
      ScissorStack.pushScissors(clipBounds);
      ImageManager.getImage(Images.bg_game_action)
         .draw2(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY()
               - ImageManager.getImage(Images.bg_game_action).getHeight()
               + (int)(this.getHeight() * (100.0F - CFG.fMOVE_MENU_PERCENTAGE) / 100.0F)
               - 1
               + iTranslateY,
            this.getMenuElement(this.getMenuElementsSize() - 1).getPosX() + this.getMenuElement(this.getMenuElementsSize() - 1).getWidth() + CFG.PADDING + 1,
            this.getHeight() + 1,
            true,
            false
         );
      super.draw(oSB, iTranslateX, (int)(this.getHeight() * (100.0F - CFG.fMOVE_MENU_PERCENTAGE) / 100.0F) + iTranslateY, sliderMenuIsActive);
   }

   @Override
   public void beginClip(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
   }

   @Override
   public final void actionElement(int iID) {
      switch (iID) {
      }
   }

   @Override
   public void setVisible(boolean visible) {
      if (visible && this.getVisible() != visible) {
         CFG.fMOVE_MENU_PERCENTAGE = 5.0F;
         CFG.lMOVE_MENU_TIME = System.currentTimeMillis();
      }

      super.setVisible(visible);
   }

   @Override
   public void onHovered() {
      CFG.menuManager.setOrderOfMenu_InGame();
   }
}
