package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

public class Menu_Games_Title extends SliderMenu {
   public Menu_Games_Title() {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      int tempMenuWidth = getMenuWidth();
      menuElements.add(new Button_Menu_LR_Line(null, -1, 0, CFG.PADDING, tempMenuWidth, CFG.BUTTON_HEIGHT, true));
      this.initMenuWithBackButton(null, CFG.GAME_WIDTH - tempMenuWidth, 0, tempMenuWidth, CFG.GAME_HEIGHT, menuElements);
      this.updateLanguage();
   }

   public static final int getMenuWidth() {
      int out = CFG.CIV_INFO_MENU_WIDTH + CFG.CIV_INFO_MENU_WIDTH / 2;
      if (CFG.isAndroid() && !CFG.LANDSCAPE) {
         out = CFG.CIV_INFO_MENU_WIDTH;
      }

      return out;
   }

   @Override
   public void updateLanguage() {
      this.getMenuElement(0).setText(CFG.langManager.get("Back"));
   }

   @Override
   public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.6F));
      ImageManager.getImage(Images.gradient)
         .draw(oSB, iTranslateX, -ImageManager.getImage(Images.gradient).getHeight() + iTranslateY, CFG.GAME_WIDTH, CFG.PADDING * 3);
      ImageManager.getImage(Images.gradient)
         .draw(
            oSB,
            iTranslateX,
            CFG.GAME_HEIGHT - ImageManager.getImage(Images.gradient).getHeight() - CFG.PADDING * 3 + iTranslateY,
            CFG.GAME_WIDTH,
            CFG.PADDING * 3,
            false,
            true
         );
      oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.1F));
      ImageManager.getImage(Images.patt2).draw(oSB, iTranslateX, -ImageManager.getImage(Images.patt2).getHeight(), CFG.GAME_WIDTH, CFG.GAME_HEIGHT, 0.0F, 0);
      oSB.setColor(1.0F, 1.0F, 1.0F, 1.0F);
      ImageManager.getImage(Images.gameLogo)
         .draw(oSB, CFG.PADDING * 2 + iTranslateX, CFG.GAME_HEIGHT - CFG.PADDING * 2 - ImageManager.getImage(Images.gameLogo).getHeight() + iTranslateY);
      oSB.setColor(1.0F, 1.0F, 1.0F, 0.85F);
      ImageManager.getImage(Images.new_game_top_edge_line)
         .draw2(
            oSB,
            this.getPosX() - 2 + iTranslateX,
            -ImageManager.getImage(Images.new_game_top_edge_line).getHeight() + iTranslateY,
            this.getWidth() + 2,
            CFG.GAME_HEIGHT
         );
      oSB.setColor(CFG.COLOR_GRADIENT_TITLE_BLUE.r, CFG.COLOR_GRADIENT_TITLE_BLUE.g, CFG.COLOR_GRADIENT_TITLE_BLUE.b, 0.275F);
      ImageManager.getImage(Images.line_32_off1)
         .draw(oSB, this.getPosX() + iTranslateX, -ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth(), CFG.GAME_HEIGHT);
      oSB.setColor(Color.WHITE);
      super.draw(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
   }

   @Override
   public final void actionElement(int iID) {
      switch (iID) {
         case 0:
            this.onBackPressed();
      }
   }

   @Override
   public final void onBackPressed() {
      CFG.menuManager.setViewID(Menu.eMAINMENU);
      CFG.menuManager.setBackAnimation(true);
   }

   @Override
   public void onHovered() {
      CFG.menuManager.setOrderOfMenu_Games();
   }
}
