package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.GdxRuntimeException;
import java.util.ArrayList;
import java.util.List;

public class Menu_MapEditor_FormableCivs_SelectFormable_Alphabet extends SliderMenu {
   public List<Character> lCharacters;
   public String nSearch = null;

   public Menu_MapEditor_FormableCivs_SelectFormable_Alphabet() {
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
            return Menu_MapEditor_FormableCivs_SelectFormable_Alphabet.this.nSearch + ": " + super.getTextToDraw();
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

      String tempTADDED = null;
      String[] tagsSPLITED_ADDED = new String[0];
      int tagsSPLITED_ADDEDLength = 0;

      try {
         FileHandle tempFileTADDED = CFG.readLocalFiles()
            ? Gdx.files.local("map/" + CFG.map.getFile_ActiveMap_Path() + "formable_civs/Age_of_Civilizations")
            : Gdx.files.internal("map/" + CFG.map.getFile_ActiveMap_Path() + "formable_civs/Age_of_Civilizations");
         tempTADDED = tempFileTADDED.readString();
         tagsSPLITED_ADDED = tempTADDED.split(";");
         tagsSPLITED_ADDEDLength = tagsSPLITED_ADDED.length;
      } catch (GdxRuntimeException var19) {
      }

      FileHandle tempFileT = Gdx.files.internal("game/civilizations/Age_of_Civilizations");
      String tempT = tempFileT.readString();
      String[] tagsSPLITED = tempT.split(";");
      String[] tagsSPLITED_ED = new String[0];

      try {
         FileHandle tempFileT_ED = null;
         tempFileT_ED = CFG.isAndroid()
            ? Gdx.files.local("game/civilizations_editor/Age_of_Civilizations")
            : Gdx.files.internal("game/civilizations_editor/Age_of_Civilizations");
         String tempT_ED = tempFileT_ED.readString();
         tagsSPLITED_ED = tempT_ED.split(";");
      } catch (GdxRuntimeException var18) {
      }

      this.lCharacters = new ArrayList<>();
      int iSize = tagsSPLITED.length;

      for (int i = 0; i < iSize; i++) {
         if (!CFG.isInFormableCivs(tagsSPLITED[i])) {
            boolean add = true;

            for (int j = 0; j < tagsSPLITED_ADDEDLength; j++) {
               if (tagsSPLITED_ADDED[j].equals(tagsSPLITED[i])) {
                  add = false;
                  break;
               }
            }

            if (add) {
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
      }

      iSize = tagsSPLITED_ED.length;

      for (int var24 = 0; var24 < iSize; var24++) {
         if (!CFG.isInFormableCivs(tagsSPLITED_ED[var24])) {
            boolean add = true;

            for (int jx = 0; jx < tagsSPLITED_ADDEDLength; jx++) {
               if (tagsSPLITED_ADDED[jx].equals(tagsSPLITED_ED[var24])) {
                  add = false;
                  break;
               }
            }

            if (add) {
               boolean addChar = true;

               for (int ax = 0; ax < this.lCharacters.size(); ax++) {
                  if (this.lCharacters.get(ax) == CFG.langManager.getCiv(tagsSPLITED_ED[var24]).charAt(0)) {
                     addChar = false;
                     break;
                  }
               }

               if (addChar) {
                  this.lCharacters.add(CFG.langManager.getCiv(tagsSPLITED_ED[var24]).charAt(0));
               }
            }
         }
      }

      for (int var25 = 0; var25 < this.lCharacters.size() - 1; var25++) {
         for (int j2 = var25 + 1; j2 < this.lCharacters.size(); j2++) {
            if (this.lCharacters.get(var25) > this.lCharacters.get(j2)) {
               char temp = this.lCharacters.get(var25);
               this.lCharacters.set(var25, this.lCharacters.get(j2));
               this.lCharacters.set(j2, temp);
            }
         }
      }

      for (int var26 = 0; var26 < this.lCharacters.size(); var26++) {
         if (CFG.chosen_AlphabetCharachter != null && this.lCharacters.get(var26) == CFG.chosen_AlphabetCharachter.charAt(0)) {
            menuElements.add(
               new Button_Menu_Active(
                  "[" + this.lCharacters.get(var26) + "]",
                  -1,
                  CFG.BUTTON_HEIGHT * (var26 + 1) + CFG.BUTTON_WIDTH * 2,
                  CFG.PADDING,
                  CFG.BUTTON_HEIGHT,
                  CFG.BUTTON_HEIGHT,
                  true
               )
            );
         } else {
            menuElements.add(
               new Button_Menu_Classic(
                  "[" + this.lCharacters.get(var26) + "]",
                  -1,
                  CFG.BUTTON_HEIGHT * (var26 + 1) + CFG.BUTTON_WIDTH * 2,
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
               CFG.menuManager.setViewID(Menu.eMAP_EDITOR_FORMABLE_CIVS_SELECT_FORMABLE);
            }

            return;
         default:
            if (CFG.chosen_AlphabetCharachter == null || CFG.sSearch != null || CFG.chosen_AlphabetCharachter.charAt(0) != this.lCharacters.get(iID - 2)) {
               CFG.chosen_AlphabetCharachter = "" + this.lCharacters.get(iID - 2);
               CFG.sSearch = null;
               CFG.menuManager.setViewID(Menu.eMAP_EDITOR_FORMABLE_CIVS_SELECT_FORMABLE);
            }
      }
   }
}
