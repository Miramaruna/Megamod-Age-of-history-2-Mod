package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

public class Menu_InGame_RTO_Bot2 extends SliderMenu {
   public Menu_InGame_RTO_Bot2() {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      int tempW = CFG.CIV_INFO_MENU_WIDTH;
      String tempText = "";
      ArrayList<Integer> lSortedPos = new ArrayList<>();
      ArrayList<Integer> lPos = new ArrayList<>();

      for (int i = 0; i < CFG.game.getPlayersSize(); i++) {
         if (CFG.game.getCiv(CFG.game.getPlayer(i).getCivID()).getNumOfProvinces() > 0) {
            lSortedPos.add(CFG.game.getRTO().getPositionInRTOOfCiv(CFG.game.getPlayer(i).getCivID()));
            lPos.add(i);
         } else {
            lSortedPos.add(-1);
            lPos.add(i);
         }
      }

      for (int var9 = 0; var9 < CFG.game.getPlayersSize(); var9++) {
         for (int j = var9; j < CFG.game.getPlayersSize(); j++) {
            if (lSortedPos.get(lPos.get(var9)) > lSortedPos.get(lPos.get(j))) {
               int tempA = lPos.get(var9);
               lPos.set(var9, lPos.get(j));
               lPos.set(j, tempA);
            }
         }
      }

      for (int var10 = 0; var10 < lPos.size(); var10++) {
         if (CFG.game.getCiv(CFG.game.getPlayer(lPos.get(var10)).getCivID()).getNumOfProvinces() > 0) {
            tempText = tempText
               + CFG.game.getCiv(CFG.game.getPlayer(lPos.get(var10)).getCivID()).getCivName()
               + ": "
               + CFG.game.getRTO().getPositionInRTOOfCiv(CFG.game.getPlayer(lPos.get(var10)).getCivID())
               + " - ";
         }
      }

      menuElements.add(
         new Text_Scrollable(
            CFG.langManager.get("Position") + ": [" + tempText.substring(0, tempText.length() - 3 > 0 ? tempText.length() - 3 : tempText.length()) + "]",
            CFG.PADDING * 2,
            CFG.PADDING * 2,
            tempW - CFG.PADDING * 2,
            CFG.COLOR_TEXT_CIV_INFO_TITLE,
            0.8F
         )
      );
      this.initMenu(null, CFG.GAME_WIDTH - tempW, 0, tempW, CFG.TEXT_HEIGHT + CFG.PADDING * 4, menuElements, false, false);
      this.updateLanguage();
   }

   @Override
   public void updateLanguage() {
   }

   @Override
   public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      if (Menu_InGame_RTO2.lTime + 250L >= System.currentTimeMillis()) {
         iTranslateX += this.getWidth() - (int)(this.getWidth() * ((float)(System.currentTimeMillis() - Menu_InGame_RTO2.lTime) / 250.0F));
      }

      ImageManager.getImage(Images.new_game_box)
         .draw2(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.new_game_box).getHeight() + iTranslateY,
            this.getWidth(),
            this.getHeight(),
            false,
            true
         );
      oSB.setColor(new Color(0.451F, 0.329F, 0.11F, 1.0F));
      ImageManager.getImage(Images.pix255_255_255)
         .draw(oSB, this.getPosX() + 2 + iTranslateX, this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight(), this.getWidth() - 4);
      oSB.setColor(Color.WHITE);
      super.draw(oSB, iTranslateX, 1 + iTranslateY, sliderMenuIsActive);
   }

   @Override
   public void actionElement(int iID) {
      switch (iID) {
      }
   }
}
