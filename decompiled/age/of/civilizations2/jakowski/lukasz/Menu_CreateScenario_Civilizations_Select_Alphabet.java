package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.GdxRuntimeException;
import java.util.ArrayList;
import java.util.List;

public class Menu_CreateScenario_Civilizations_Select_Alphabet extends SliderMenu {
   public List<Character> lCharacters;
   public String nSearch = null;

   public Menu_CreateScenario_Civilizations_Select_Alphabet() {
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
            return Menu_CreateScenario_Civilizations_Select_Alphabet.this.nSearch + ": " + super.getTextToDraw();
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

      String[] tagsSPLITED = null;
      if (CFG.isDesktop()) {
         List<String> tempFiles = CFG.getFileNames("game/civilizations/");
         int iSize = tempFiles.size();

         for (int i2 = 0; i2 < iSize; i2++) {
            if (tempFiles.get(i2).equals("Age_of_Civilizations")) {
               tempFiles.remove(i2);
               break;
            }
         }

         tagsSPLITED = new String[tempFiles.size()];
         iSize = tempFiles.size();

         for (int var18 = 0; var18 < iSize; var18++) {
            tagsSPLITED[var18] = tempFiles.get(var18);
         }
      } else {
         FileHandle tempFileT = Gdx.files.internal("game/civilizations/Age_of_Civilizations");
         String tempT = tempFileT.readString();
         tagsSPLITED = tempT.split(";");
      }

      String[] tagsSPLITED_ED = new String[0];

      try {
         FileHandle tempFileT_ED = null;
         tempFileT_ED = CFG.isAndroid()
            ? Gdx.files.local("game/civilizations_editor/Age_of_Civilizations")
            : Gdx.files.internal("game/civilizations_editor/Age_of_Civilizations");
         String tempT_ED = tempFileT_ED.readString();
         tagsSPLITED_ED = tempT_ED.split(";");
      } catch (GdxRuntimeException var11) {
      }

      this.lCharacters = new ArrayList<>();
      int iSize = tagsSPLITED.length;

      for (int i = 0; i < iSize; i++) {
         if (!CFG.isInTheGame(tagsSPLITED[i])) {
            boolean addChar = true;

            for (int a = 0; a < this.lCharacters.size(); a++) {
               if (this.lCharacters.get(a) == CFG.langManager.getCiv(tagsSPLITED[i]).charAt(0)) {
                  addChar = false;
                  break;
               }
            }

            if (addChar) {
               this.lCharacters.add(CFG.langManager.getCiv(tagsSPLITED[i]).charAt(0));
            }
         }
      }

      iSize = tagsSPLITED_ED.length;

      for (int var14 = 0; var14 < iSize; var14++) {
         if (!CFG.isInTheGame(tagsSPLITED_ED[var14])) {
            boolean addChar = true;

            for (int ax = 0; ax < this.lCharacters.size(); ax++) {
               if (this.lCharacters.get(ax) == CFG.langManager.getCiv(tagsSPLITED_ED[var14]).charAt(0)) {
                  addChar = false;
                  break;
               }
            }

            if (addChar) {
               this.lCharacters.add(CFG.langManager.getCiv(tagsSPLITED_ED[var14]).charAt(0));
            }
         }
      }

      for (int var15 = 0; var15 < this.lCharacters.size() - 1; var15++) {
         for (int j = var15 + 1; j < this.lCharacters.size(); j++) {
            if (this.lCharacters.get(var15) > this.lCharacters.get(j)) {
               char temp = this.lCharacters.get(var15);
               this.lCharacters.set(var15, this.lCharacters.get(j));
               this.lCharacters.set(j, temp);
            }
         }
      }

      for (int var16 = 0; var16 < this.lCharacters.size(); var16++) {
         if (CFG.chosen_AlphabetCharachter != null && this.lCharacters.get(var16) == CFG.chosen_AlphabetCharachter.charAt(0)) {
            menuElements.add(
               new Button_Menu_Active(
                  "[" + this.lCharacters.get(var16) + "]",
                  -1,
                  CFG.BUTTON_HEIGHT * (var16 + 1) + CFG.BUTTON_WIDTH * 2,
                  CFG.PADDING,
                  CFG.BUTTON_HEIGHT,
                  CFG.BUTTON_HEIGHT,
                  true
               )
            );
         } else {
            menuElements.add(
               new Button_Menu_Classic(
                  "[" + this.lCharacters.get(var16) + "]",
                  -1,
                  CFG.BUTTON_HEIGHT * (var16 + 1) + CFG.BUTTON_WIDTH * 2,
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

         for (int i3 = 0; i3 < menuElements.size() - 1; i3++) {
            if (i3 == 0) {
               menuElements.get(i3).setPosX(tempPosX);
               menuElements.get(i3).setWidth(CFG.BUTTON_WIDTH * 2);
               tempPosX += menuElements.get(i3).getWidth();
            } else {
               menuElements.get(i3).setPosX(tempPosX);
               menuElements.get(i3).setWidth(tempElementWidth);
               tempPosX += menuElements.get(i3).getWidth();
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
               CFG.menuManager.setViewID(Menu.eCREATE_SCENARIO_CIVILIZATIONS_SELECT);
            }

            return;
         default:
            if (CFG.chosen_AlphabetCharachter == null || CFG.sSearch != null || CFG.chosen_AlphabetCharachter.charAt(0) != this.lCharacters.get(iID - 2)) {
               CFG.chosen_AlphabetCharachter = "" + this.lCharacters.get(iID - 2);
               CFG.sSearch = null;
               CFG.menuManager.setViewID(Menu.eCREATE_SCENARIO_CIVILIZATIONS_SELECT);
            }
      }
   }
}
