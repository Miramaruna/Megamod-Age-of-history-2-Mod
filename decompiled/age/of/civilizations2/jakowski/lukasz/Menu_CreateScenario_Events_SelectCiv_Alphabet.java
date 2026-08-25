package age.of.civilizations2.jakowski.lukasz;

import java.util.ArrayList;
import java.util.List;

public class Menu_CreateScenario_Events_SelectCiv_Alphabet extends SliderMenu {
   public List<Character> lCharacters;
   public String nSearch = null;

   public Menu_CreateScenario_Events_SelectCiv_Alphabet() {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      this.nSearch = CFG.langManager.get("Search");
      menuElements.add(new Button_Menu_Classic_Search("", CFG.PADDING * 2, 0, CFG.PADDING, CFG.BUTTON_WIDTH * 2, CFG.BUTTON_HEIGHT, true) {
         @Override
         public void buildElementHover() {
            ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
            ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Search"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
            this.menuElementHover = new MenuElement_Hover_v2(nElements);
         }

         @Override
         public String getTextToDraw() {
            return Menu_CreateScenario_Events_SelectCiv_Alphabet.this.nSearch + ": " + super.getTextToDraw();
         }
      });
      if (CFG.chosen_AlphabetCharachter == null) {
         menuElements.add(new Button_Menu_Active(null, -1, CFG.BUTTON_WIDTH * 2, CFG.PADDING, CFG.BUTTON_HEIGHT, CFG.BUTTON_HEIGHT, true) {
            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("AllCivilizations"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
         });
      } else {
         menuElements.add(new Button_Menu_Classic(null, -1, CFG.BUTTON_WIDTH * 2, CFG.PADDING, CFG.BUTTON_HEIGHT, CFG.BUTTON_HEIGHT, true) {
            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("AllCivilizations"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
         });
      }

      this.lCharacters = new ArrayList<>();

      for (int i = 0; i < CFG.game.getCivsSize(); i++) {
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

      for (int var6 = 0; var6 < this.lCharacters.size() - 1; var6++) {
         for (int j = var6 + 1; j < this.lCharacters.size(); j++) {
            if (this.lCharacters.get(var6) > this.lCharacters.get(j)) {
               char temp = this.lCharacters.get(var6);
               this.lCharacters.set(var6, this.lCharacters.get(j));
               this.lCharacters.set(j, temp);
            }
         }
      }

      for (int var7 = 0; var7 < this.lCharacters.size(); var7++) {
         if (CFG.chosen_AlphabetCharachter != null && this.lCharacters.get(var7) == CFG.chosen_AlphabetCharachter.charAt(0)) {
            menuElements.add(
               new Button_Menu_Active(
                  "[" + this.lCharacters.get(var7) + "]",
                  -1,
                  CFG.BUTTON_HEIGHT * (var7 + 1) + CFG.BUTTON_WIDTH * 2,
                  CFG.PADDING,
                  CFG.BUTTON_HEIGHT,
                  CFG.BUTTON_HEIGHT,
                  true
               )
            );
         } else {
            menuElements.add(
               new Button_Menu_Classic(
                  "[" + this.lCharacters.get(var7) + "]",
                  -1,
                  CFG.BUTTON_HEIGHT * (var7 + 1) + CFG.BUTTON_WIDTH * 2,
                  CFG.PADDING,
                  CFG.BUTTON_HEIGHT,
                  CFG.BUTTON_HEIGHT,
                  true
               )
            );
         }
      }

      if (menuElements.get(menuElements.size() - 1).getPosX() + menuElements.get(menuElements.size() - 1).getWidth() < CFG.GAME_WIDTH) {
         int tempElementWidth = (CFG.GAME_WIDTH - CFG.BUTTON_WIDTH * 2) / (menuElements.size() - 1);
         int tempPosX = 0;

         for (int i2 = 0; i2 < menuElements.size() - 1; i2++) {
            if (i2 == 0) {
               menuElements.get(i2).setPosX(tempPosX);
               menuElements.get(i2).setWidth(CFG.BUTTON_WIDTH * 2);
               tempPosX += menuElements.get(i2).getWidth();
            } else {
               menuElements.get(i2).setPosX(tempPosX);
               menuElements.get(i2).setWidth(tempElementWidth);
               tempPosX += menuElements.get(i2).getWidth();
            }
         }

         menuElements.get(menuElements.size() - 1).setPosX(tempPosX);
         menuElements.get(menuElements.size() - 1).setWidth(CFG.GAME_WIDTH - tempPosX);
      }

      this.initMenu(null, 0, CFG.BUTTON_HEIGHT * 3 / 4, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT + CFG.PADDING * 2, menuElements, true, false);
      this.updateLanguage();
   }

   @Override
   public void updateLanguage() {
      if (CFG.sSearch != null) {
         this.getMenuElement(0).setText(CFG.sSearch);
      }

      this.getMenuElement(1).setText("[" + CFG.langManager.get("ALL") + "]");
   }

   @Override
   public final void actionElement(int iID) {
      switch (iID) {
         case 0:
            CFG.showKeyboard();
            return;
         case 1:
            if (CFG.chosen_AlphabetCharachter != null || CFG.sSearch != null) {
               CFG.chosen_AlphabetCharachter = null;
               CFG.sSearch = null;
               CFG.menuManager.setViewID(Menu.eCREATE_SCENARIO_EVENTS_SELECT_CIV);
            }

            return;
         default:
            if (CFG.chosen_AlphabetCharachter == null || CFG.sSearch != null || CFG.chosen_AlphabetCharachter.charAt(0) != this.lCharacters.get(iID - 2)) {
               CFG.chosen_AlphabetCharachter = "" + this.lCharacters.get(iID - 2);
               CFG.sSearch = null;
               CFG.menuManager.setViewID(Menu.eCREATE_SCENARIO_EVENTS_SELECT_CIV);
            }
      }
   }
}
