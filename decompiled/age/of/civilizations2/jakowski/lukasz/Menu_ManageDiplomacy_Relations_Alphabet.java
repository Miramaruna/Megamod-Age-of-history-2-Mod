package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;
import java.util.List;

public class Menu_ManageDiplomacy_Relations_Alphabet extends SliderMenu {
   public List<Character> lCharacters;

   public Menu_ManageDiplomacy_Relations_Alphabet() {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      String sSelectOnMap = CFG.langManager.get("SelectOnMap");
      CFG.glyphLayout.setText(CFG.fontMain, sSelectOnMap);
      int iSelectOnMapWidth = (int)CFG.glyphLayout.width + CFG.PADDING * 4;
      menuElements.add(new Button_Menu_Classic(null, -1, 0, CFG.PADDING, iSelectOnMapWidth, CFG.BUTTON_HEIGHT, true));
      if (CFG.chosen_AlphabetCharachter == null) {
         menuElements.add(new Button_Menu_Active(null, -1, iSelectOnMapWidth, CFG.PADDING, CFG.BUTTON_HEIGHT, CFG.BUTTON_HEIGHT, true));
      } else {
         menuElements.add(new Button_Menu_Classic(null, -1, iSelectOnMapWidth, CFG.PADDING, CFG.BUTTON_HEIGHT, CFG.BUTTON_HEIGHT, true));
      }

      this.lCharacters = new ArrayList<>();

      for (int i = 1; i < CFG.game.getCivsSize(); i++) {
         if (i != CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID) {
            boolean addChar = true;

            for (int a = 0; a < this.lCharacters.size(); a++) {
               if (this.lCharacters.get(a) == CFG.game.getCiv(i).getCivName().charAt(0)) {
                  addChar = false;
                  break;
               }
            }

            if (addChar) {
               this.lCharacters.add(CFG.game.getCiv(i).getCivName().charAt(0));
            }
         }
      }

      for (int var7 = 0; var7 < this.lCharacters.size() - 1; var7++) {
         for (int j = var7 + 1; j < this.lCharacters.size(); j++) {
            if (this.lCharacters.get(var7) > this.lCharacters.get(j)) {
               char temp = this.lCharacters.get(var7);
               this.lCharacters.set(var7, this.lCharacters.get(j));
               this.lCharacters.set(j, temp);
            }
         }
      }

      for (int var8 = 0; var8 < this.lCharacters.size(); var8++) {
         if (CFG.chosen_AlphabetCharachter != null && this.lCharacters.get(var8) == CFG.chosen_AlphabetCharachter.charAt(0)) {
            menuElements.add(
               new Button_Menu_Active(
                  "[" + this.lCharacters.get(var8) + "]",
                  -1,
                  iSelectOnMapWidth + CFG.BUTTON_HEIGHT * (var8 + 1),
                  CFG.PADDING,
                  CFG.BUTTON_HEIGHT,
                  CFG.BUTTON_HEIGHT,
                  true
               )
            );
         } else {
            menuElements.add(
               new Button_Menu_Classic(
                  "[" + this.lCharacters.get(var8) + "]",
                  -1,
                  iSelectOnMapWidth + CFG.BUTTON_HEIGHT * (var8 + 1),
                  CFG.PADDING,
                  CFG.BUTTON_HEIGHT,
                  CFG.BUTTON_HEIGHT,
                  true
               )
            );
         }
      }

      this.initMenu(
         new SliderMenuTitle(null, CFG.BUTTON_HEIGHT * 3 / 4, false, false),
         0,
         CFG.BUTTON_HEIGHT * 3 / 4,
         CFG.GAME_WIDTH,
         CFG.BUTTON_HEIGHT + CFG.PADDING * 2,
         menuElements,
         false,
         false
      );
      this.updateLanguage();
   }

   @Override
   public void updateLanguage() {
      this.getMenuElement(0).setText(CFG.langManager.get("SelectOnMap"));
      this.getMenuElement(1).setText("[" + CFG.langManager.get("ALL") + "]");
      this.getTitle().setText(CFG.langManager.get("CustomizeRelations") + " " + CFG.game.getCiv(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID).getCivName());
   }

   @Override
   public void drawTitle(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive, int nPosY) {
      super.drawTitle(oSB, iTranslateX, iTranslateY, sliderMenuIsActive, nPosY);
      CFG.game
         .getCiv(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID)
         .getFlag()
         .draw(
            oSB,
            this.getWidth() / 2 + this.getTitle().getTextWidth() / 2 + CFG.PADDING + iTranslateX,
            this.getTitle().getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2
         );
      ImageManager.getImage(Images.flag_rect)
         .draw(
            oSB,
            this.getWidth() / 2 + this.getTitle().getTextWidth() / 2 + CFG.PADDING + iTranslateX,
            this.getTitle().getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2
         );
   }

   @Override
   public final void actionElement(int iID) {
      switch (iID) {
         case 0:
            return;
         case 1:
            if (CFG.chosen_AlphabetCharachter != null) {
               for (int i = 0; i < this.lCharacters.size(); i++) {
                  if (this.lCharacters.get(i) == CFG.chosen_AlphabetCharachter.charAt(0)) {
                     this.setMenuElement(
                        i + 2,
                        new Button_Menu_Classic(
                           this.getMenuElement(i + 2).getText(),
                           -1,
                           this.getMenuElement(i + 2).getPosX(),
                           this.getMenuElement(i + 2).getPosY(),
                           this.getMenuElement(i + 2).getWidth(),
                           this.getMenuElement(i + 2).getHeight(),
                           true
                        )
                     );
                     this.setMenuElement(
                        iID,
                        new Button_Menu_Active(
                           this.getMenuElement(iID).getText(),
                           -1,
                           this.getMenuElement(iID).getPosX(),
                           this.getMenuElement(iID).getPosY(),
                           this.getMenuElement(iID).getWidth(),
                           this.getMenuElement(iID).getHeight(),
                           true
                        )
                     );
                     break;
                  }
               }
            }

            CFG.chosen_AlphabetCharachter = null;
            return;
         default:
            int toDisable = 0;
            if (CFG.chosen_AlphabetCharachter == null) {
               toDisable = 1;
            } else {
               for (int ix = 0; ix < this.lCharacters.size(); ix++) {
                  if (this.lCharacters.get(ix) == CFG.chosen_AlphabetCharachter.charAt(0)) {
                     toDisable = ix + 2;
                     break;
                  }
               }
            }

            this.setMenuElement(
               toDisable,
               new Button_Menu_Classic(
                  this.getMenuElement(toDisable).getText(),
                  -1,
                  this.getMenuElement(toDisable).getPosX(),
                  this.getMenuElement(toDisable).getPosY(),
                  this.getMenuElement(toDisable).getWidth(),
                  this.getMenuElement(toDisable).getHeight(),
                  true
               )
            );
            this.setMenuElement(
               iID,
               new Button_Menu_Active(
                  this.getMenuElement(iID).getText(),
                  -1,
                  this.getMenuElement(iID).getPosX(),
                  this.getMenuElement(iID).getPosY(),
                  this.getMenuElement(iID).getWidth(),
                  this.getMenuElement(iID).getHeight(),
                  true
               )
            );
            CFG.chosen_AlphabetCharachter = "" + this.lCharacters.get(iID - 2);
      }
   }
}
