package age.of.civilizations2.jakowski.lukasz;

import java.util.ArrayList;
import java.util.List;

public class Menu_CreateScenario_Assign_Select_Alphabet extends SliderMenu {
   public List<Character> lCharacters;

   public Menu_CreateScenario_Assign_Select_Alphabet() {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      if (CFG.chosen_AlphabetCharachter == null) {
         menuElements.add(new Button_Menu_Active(null, -1, 0, CFG.PADDING, CFG.BUTTON_HEIGHT, CFG.BUTTON_HEIGHT, true) {
            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("AllCivilizations")));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
         });
      } else {
         menuElements.add(new Button_Menu_Classic(null, -1, 0, CFG.PADDING, CFG.BUTTON_HEIGHT, CFG.BUTTON_HEIGHT, true) {
            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("AllCivilizations")));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
         });
      }

      this.lCharacters = new ArrayList<>();

      for (int i = 1; i < CFG.game.getCivsSize(); i++) {
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
                  "[" + this.lCharacters.get(var7) + "]", -1, CFG.BUTTON_HEIGHT * (var7 + 1), CFG.PADDING, CFG.BUTTON_HEIGHT, CFG.BUTTON_HEIGHT, true
               )
            );
         } else {
            menuElements.add(
               new Button_Menu_Classic(
                  "[" + this.lCharacters.get(var7) + "]", -1, CFG.BUTTON_HEIGHT * (var7 + 1), CFG.PADDING, CFG.BUTTON_HEIGHT, CFG.BUTTON_HEIGHT, true
               )
            );
         }
      }

      if (menuElements.get(menuElements.size() - 1).getPosX() + menuElements.get(menuElements.size() - 1).getWidth() < CFG.GAME_WIDTH) {
         int tempElementWidth = CFG.GAME_WIDTH / menuElements.size();
         int tempPosX = 0;

         for (int i2 = 0; i2 < menuElements.size() - 1; i2++) {
            menuElements.get(i2).setPosX(tempPosX);
            menuElements.get(i2).setWidth(tempElementWidth);
            tempPosX += tempElementWidth;
         }

         menuElements.get(menuElements.size() - 1).setPosX(tempPosX);
         menuElements.get(menuElements.size() - 1).setWidth(CFG.GAME_WIDTH - tempPosX);
      }

      this.initMenu(
         new SliderMenuTitle(null, CFG.BUTTON_HEIGHT * 3 / 4, false, false),
         0,
         CFG.BUTTON_HEIGHT * 3 / 4,
         CFG.GAME_WIDTH,
         CFG.BUTTON_HEIGHT + CFG.PADDING * 2,
         menuElements,
         true,
         false
      );
      this.updateLanguage();
   }

   @Override
   public void updateLanguage() {
      this.getMenuElement(0).setText("[" + CFG.langManager.get("ALL") + "]");
      this.getTitle().setText(CFG.langManager.get("SelectCivilization"));
   }

   @Override
   public final void actionElement(int iID) {
      switch (iID) {
         case 0:
            CFG.chosen_AlphabetCharachter = null;
            CFG.menuManager.setViewID(Menu.eCREATE_SCENARIO_ASSIGN_SELECT);
            return;
         default:
            CFG.chosen_AlphabetCharachter = "" + this.lCharacters.get(iID - 1);
            CFG.menuManager.setViewID(Menu.eCREATE_SCENARIO_ASSIGN_SELECT);
      }
   }
}
