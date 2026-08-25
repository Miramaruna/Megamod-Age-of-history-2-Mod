package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

public class Menu_InGame_Wars extends SliderMenu {
   public static int iSort = 0;

   public Menu_InGame_Wars(int tInit) {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      int tempWidth = CFG.CIV_INFO_MENU_WIDTH * 3;
      int tempMenuPosY = ImageManager.getImage(Images.top_flag_frame).getHeight() + CFG.PADDING * 4 + CFG.BUTTON_HEIGHT * 3 / 5 + CFG.PADDING * 2;
      this.initMenu(null, CFG.GAME_WIDTH / 2 - tempWidth / 2, tempMenuPosY, tempWidth, 5, menuElements, false, false);
   }

   public Menu_InGame_Wars() {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      int tempWidth = CFG.CIV_INFO_MENU_WIDTH * 3;
      int tY = 0;

      for (int i = 0; i < CFG.game.getWarsSize(); i++) {
         boolean rebels = false;

         for (int a = 0; a < CFG.game.getWar(i).getDefendersSize(); a++) {
            if (CFG.ideologiesManager.getIdeology(CFG.game.getCiv(CFG.game.getWar(i).getDefenderID(a).getCivID()).getIdeologyID()).REVOLUTIONARY) {
               rebels = true;
               break;
            }
         }

         if (!rebels) {
            for (int var8 = 0; var8 < CFG.game.getWar(i).getAggressorsSize(); var8++) {
               if (CFG.ideologiesManager.getIdeology(CFG.game.getCiv(CFG.game.getWar(i).getAggressorID(var8).getCivID()).getIdeologyID()).REVOLUTIONARY) {
                  rebels = true;
                  break;
               }
            }
         }

         if (!rebels) {
            menuElements.add(
               new Button_Statistics_War(
                  CFG.game.getWar(i).getAggressorID(0).getCivID(), CFG.game.getWar(i).getDefenderID(0).getCivID(), i, 0, tY, CFG.BUTTON_WIDTH * 2
               ) {
                  @Override
                  public int getWidth() {
                     return Menu_InGame_Wars.this.getElementW();
                  }

                  @Override
                  public Color getColor(boolean isActive) {
                     return isActive
                        ? CFG.COLOR_TEXT_OPTIONS_LEFT_NS_ACTIVE
                        : (
                           this.getClickable()
                              ? (this.getIsHovered() ? CFG.COLOR_TEXT_OPTIONS_LEFT_NS_HOVER : CFG.COLOR_TEXT_OPTIONS_LEFT_NS)
                              : CFG.COLOR_BUTTON_MENU_TEXT_NOT_CLICKABLE
                        );
                  }
               }
            );
            tY += menuElements.get(menuElements.size() - 1).getHeight();
         }
      }

      if (menuElements.size() == 0) {
         menuElements.add(new Text_Scale(CFG.langManager.get("NoWars"), -1, 0, CFG.PADDING, tempWidth - CFG.PADDING * 4, CFG.BUTTON_HEIGHT * 3 / 4, 0.75F) {
            @Override
            public int getWidth() {
               return Menu_InGame_Wars.this.getElementW();
            }
         });
         menuElements.get(menuElements.size() - 1).setClickable(false);
      }

      int tempMenuPosY = ImageManager.getImage(Images.top_flag_frame).getHeight() + CFG.PADDING * 4 + CFG.BUTTON_HEIGHT * 3 / 5 + CFG.PADDING * 2;
      this.initMenu(
         new SliderMenuTitle(CFG.langManager.get("CurrentWars"), CFG.BUTTON_HEIGHT * 3 / 5, true, true) {
            @Override
            public void draw(SpriteBatch oSB, int iTranslateX, int nPosX, int nPosY, int nWidth, boolean sliderMenuIsActive) {
               ImageManager.getImage(Images.dialog_title)
                  .draw2(
                     oSB,
                     nPosX - 2 + iTranslateX,
                     nPosY - this.getHeight() - ImageManager.getImage(Images.dialog_title).getHeight(),
                     nWidth + 4 - ImageManager.getImage(Images.dialog_title).getWidth(),
                     this.getHeight()
                  );
               ImageManager.getImage(Images.dialog_title)
                  .draw2(
                     oSB,
                     nPosX + nWidth + 2 - ImageManager.getImage(Images.dialog_title).getWidth() + iTranslateX,
                     nPosY - this.getHeight() - ImageManager.getImage(Images.dialog_title).getHeight(),
                     ImageManager.getImage(Images.dialog_title).getWidth(),
                     this.getHeight(),
                     true,
                     false
                  );
               oSB.setColor(new Color(0.64705884F, 0.05882353F, 0.05882353F, 0.165F));
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
               oSB.setColor(new Color(0.64705884F, 0.05882353F, 0.05882353F, 0.375F));
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
               ImageManager.getImage(Images.diplo_war)
                  .draw(
                     oSB,
                     nPosX + (int)(nWidth - this.getTextWidth() * 0.8F) / 2 - CFG.PADDING - ImageManager.getImage(Images.diplo_war).getWidth() + iTranslateX,
                     2 + nPosY - this.getHeight() + this.getHeight() / 2 - ImageManager.getImage(Images.diplo_war).getHeight() / 2
                  );
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
         CFG.GAME_WIDTH / 2 - tempWidth / 2,
         tempMenuPosY,
         tempWidth,
         menuElements.get(menuElements.size() - 1).getPosY() + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING + tempMenuPosY
               > CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING * 2
            ? Math.max(CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING * 2 - tempMenuPosY, (CFG.TEXT_HEIGHT + CFG.PADDING * 2) * 6)
            : menuElements.get(menuElements.size() - 1).getPosY() + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING,
         menuElements,
         true,
         true
      );
      this.updateLanguage();

      for (int i = 0; i < this.getMenuElementsSize(); i++) {
         this.getMenuElement(i).setCurrent(i % 2);
      }
   }

   @Override
   public void updateLanguage() {
   }

   @Override
   public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      oSB.setColor(Color.WHITE);
      ImageManager.getImage(Images.new_game_top_edge)
         .draw2(
            oSB,
            this.getPosX() - 2 + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.new_game_top_edge).getHeight() + iTranslateY,
            this.getWidth() - ImageManager.getImage(Images.new_game_top_edge).getWidth() + 4,
            this.getHeight() + CFG.PADDING,
            false,
            true
         );
      ImageManager.getImage(Images.new_game_top_edge)
         .draw2(
            oSB,
            this.getPosX() + 2 + this.getWidth() - ImageManager.getImage(Images.new_game_top_edge).getWidth() + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.new_game_top_edge).getHeight() + iTranslateY,
            ImageManager.getImage(Images.new_game_top_edge).getWidth(),
            this.getHeight() + CFG.PADDING,
            true,
            true
         );
      oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.45F));
      ImageManager.getImage(Images.gradient)
         .draw(
            oSB,
            this.getPosX() + 2 + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY,
            this.getWidth() - 4,
            this.getHeight() / 4
         );
      ImageManager.getImage(Images.pix255_255_255)
         .draw(
            oSB,
            this.getPosX() + 2 + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY,
            this.getWidth() - 4,
            1
         );
      oSB.setColor(Color.WHITE);
      this.beginClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
      this.drawMenu(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
      oSB.setColor(Color.WHITE);
      this.endClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
   }

   @Override
   public void drawScrollPos(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      if (sliderMenuIsActive) {
         super.drawScrollPos(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
      }
   }

   @Override
   public final void actionElement(int iID) {
      if (this.getMenuElement(iID).getClickable()) {
         Menu_InGame_WarDetails.WAR_ID = this.getMenuElement(iID).getCurrent();
         CFG.menuManager.rebuildInGame_WarDetails();
      }
   }

   public final int getW() {
      return this.getWidth();
   }

   public final int getElementW() {
      return this.getW();
   }
}
