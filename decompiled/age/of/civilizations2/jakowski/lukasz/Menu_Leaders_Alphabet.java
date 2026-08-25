package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.GdxRuntimeException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Menu_Leaders_Alphabet extends SliderMenu {
   public List<Character> lCharacters;
   public String nSearch = null;

   public Menu_Leaders_Alphabet() {
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
            return Menu_Leaders_Alphabet.this.nSearch + ": " + super.getTextToDraw();
         }
      });
      if (CFG.chosen_AlphabetCharachter == null) {
         menuElements.add(new Button_Menu_Active(null, -1, CFG.BUTTON_WIDTH * 2, CFG.PADDING, CFG.BUTTON_HEIGHT, CFG.BUTTON_HEIGHT, true) {
            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("All"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
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
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("All"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
         });
      }

      try {
         String[] tagsSPLITED = null;
         if (!CFG.isDesktop()) {
            FileHandle tempFileT = Gdx.files.internal("game/leaders/Age_of_Civilizations");
            String tempT = tempFileT.readString();
            tagsSPLITED = tempT.split(";");
         } else {
            List<String> tempFiles = CFG.getFileNames("game/leaders/");
            int iSize = tempFiles.size();

            for (int i2 = 0; i2 < iSize; i2++) {
               if (tempFiles.get(i2).equals("Age_of_Civilizations")) {
                  tempFiles.remove(i2);
                  break;
               }
            }

            tagsSPLITED = new String[tempFiles.size()];
            iSize = tempFiles.size();

            for (int var15 = 0; var15 < iSize; var15++) {
               tagsSPLITED[var15] = tempFiles.get(var15);
            }
         }

         this.lCharacters = new ArrayList<>();
         int iSize = tagsSPLITED.length;

         for (int i = 0; i < iSize; i++) {
            boolean addChar = true;

            try {
               try {
                  FileHandle file = Gdx.files.local("game/leaders/" + tagsSPLITED[i]);
                  CFG.leader_GameData = (Leader_GameData)CFG.deserialize(file.readBytes());
               } catch (GdxRuntimeException var8) {
                  FileHandle filex = Gdx.files.internal("game/leaders/" + tagsSPLITED[i]);
                  CFG.leader_GameData = (Leader_GameData)CFG.deserialize(filex.readBytes());
               }
            } catch (ClassNotFoundException var9) {
            } catch (IOException var10) {
            }

            if (CFG.leader_GameData.getLeaderOfCiv().getName().length() > 0) {
               for (int a = 0; a < this.lCharacters.size(); a++) {
                  if (this.lCharacters.get(a) == CFG.leader_GameData.getLeaderOfCiv().getName().charAt(0)) {
                     addChar = false;
                     break;
                  }
               }

               if (addChar) {
                  this.lCharacters.add(CFG.leader_GameData.getLeaderOfCiv().getName().charAt(0));
               }
            }
         }

         for (int var12 = 0; var12 < this.lCharacters.size() - 1; var12++) {
            for (int j = var12 + 1; j < this.lCharacters.size(); j++) {
               if (this.lCharacters.get(var12) > this.lCharacters.get(j)) {
                  char temp = this.lCharacters.get(var12);
                  this.lCharacters.set(var12, this.lCharacters.get(j));
                  this.lCharacters.set(j, temp);
               }
            }
         }

         for (int var13 = 0; var13 < this.lCharacters.size(); var13++) {
            if (CFG.chosen_AlphabetCharachter != null && this.lCharacters.get(var13) == CFG.chosen_AlphabetCharachter.charAt(0)) {
               menuElements.add(
                  new Button_Menu_Active(
                     "[" + this.lCharacters.get(var13) + "]",
                     -1,
                     CFG.BUTTON_HEIGHT * (var13 + 1) + CFG.BUTTON_WIDTH * 2,
                     CFG.PADDING,
                     CFG.BUTTON_HEIGHT,
                     CFG.BUTTON_HEIGHT,
                     true
                  )
               );
            } else {
               menuElements.add(
                  new Button_Menu_Classic(
                     "[" + this.lCharacters.get(var13) + "]",
                     -1,
                     CFG.BUTTON_HEIGHT * (var13 + 1) + CFG.BUTTON_WIDTH * 2,
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
      } catch (GdxRuntimeException var11) {
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
               CFG.menuManager.setViewID(Menu.eGAME_LEADERS);
            }

            return;
         default:
            if (CFG.chosen_AlphabetCharachter == null || CFG.sSearch != null || CFG.chosen_AlphabetCharachter.charAt(0) != this.lCharacters.get(iID - 2)) {
               CFG.chosen_AlphabetCharachter = "" + this.lCharacters.get(iID - 2);
               CFG.sSearch = null;
               CFG.menuManager.setViewID(Menu.eGAME_LEADERS);
            }
      }
   }
}
