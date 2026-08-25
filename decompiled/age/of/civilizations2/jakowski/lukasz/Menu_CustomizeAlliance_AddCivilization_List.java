package age.of.civilizations2.jakowski.lukasz;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Menu_CustomizeAlliance_AddCivilization_List extends SliderMenu {
   public List<Integer> lCivID;

   public Menu_CustomizeAlliance_AddCivilization_List() {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      this.lCivID = new ArrayList<>();
      if (CFG.chosen_AlphabetCharachter == null) {
         menuElements.add(new Button_Menu(null, -1, 0, CFG.PADDING, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT, true));
         int nPosY = 0;

         for (int i = 1; i < CFG.game.getCivsSize(); i++) {
            if (CFG.game.getCiv(i).getAllianceID() == 0) {
               menuElements.add(
                  new Button_Menu_CivilizationAndFlag(
                     i, (int)(50.0F * CFG.GUI_SCALE), 0, CFG.BUTTON_HEIGHT * (nPosY + 1) + CFG.PADDING * (nPosY + 2), CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT, true
                  ) {
                     @Override
                     public void buildElementHover() {
                        ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
                        ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
                        nData.add(
                           new MenuElement_Hover_v2_Element_Type_Text(CFG.game.getCiv(this.getCurrent()).getCivName(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE)
                        );
                        nData.add(new MenuElement_Hover_v2_Element_Type_Flag(this.getCurrent(), CFG.PADDING, 0));
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                        nData.add(new MenuElement_Hover_v2_Element_Type_Space());
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Provinces") + ": "));
                        nData.add(
                           new MenuElement_Hover_v2_Element_Type_Text(
                              "" + CFG.game.getCiv(this.getCurrent()).getNumOfProvinces(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                           )
                        );
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Population") + ": "));
                        nData.add(
                           new MenuElement_Hover_v2_Element_Type_Text(
                              "" + CFG.getNumberWithSpaces("" + CFG.game.getCiv(this.getCurrent()).countPopulation()), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                           )
                        );
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Economy") + ": "));
                        nData.add(
                           new MenuElement_Hover_v2_Element_Type_Text(
                              "" + CFG.getNumberWithSpaces("" + CFG.game.getCiv(this.getCurrent()).countEconomy()), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                           )
                        );
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("TechnologyLevel") + ": "));
                        nData.add(
                           new MenuElement_Hover_v2_Element_Type_Text(
                              "" + CFG.game.getCiv(this.getCurrent()).getTechnologyLevel(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                           )
                        );
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                        this.menuElementHover = new MenuElement_Hover_v2(nElements);
                     }
                  }
               );
               this.lCivID.add(i);
               nPosY++;
            }
         }
      } else {
         int nPosY = 0;

         for (int ix = 1; ix < CFG.game.getCivsSize(); ix++) {
            if (CFG.game.getCiv(ix).getAllianceID() == 0 && CFG.game.getCiv(ix).getCivName().charAt(0) == CFG.chosen_AlphabetCharachter.charAt(0)) {
               menuElements.add(
                  new Button_Menu_CivilizationAndFlag(
                     ix, (int)(50.0F * CFG.GUI_SCALE), 0, CFG.BUTTON_HEIGHT * nPosY + CFG.PADDING * (nPosY + 1), CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT, true
                  ) {
                     @Override
                     public void buildElementHover() {
                        ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
                        ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
                        nData.add(
                           new MenuElement_Hover_v2_Element_Type_Text(CFG.game.getCiv(this.getCurrent()).getCivName(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE)
                        );
                        nData.add(new MenuElement_Hover_v2_Element_Type_Flag(this.getCurrent(), CFG.PADDING, 0));
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                        nData.add(new MenuElement_Hover_v2_Element_Type_Space());
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Provinces") + ": "));
                        nData.add(
                           new MenuElement_Hover_v2_Element_Type_Text(
                              "" + CFG.game.getCiv(this.getCurrent()).getNumOfProvinces(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                           )
                        );
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Population") + ": "));
                        nData.add(
                           new MenuElement_Hover_v2_Element_Type_Text(
                              "" + CFG.getNumberWithSpaces("" + CFG.game.getCiv(this.getCurrent()).countPopulation()), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                           )
                        );
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Economy") + ": "));
                        nData.add(
                           new MenuElement_Hover_v2_Element_Type_Text(
                              "" + CFG.getNumberWithSpaces("" + CFG.game.getCiv(this.getCurrent()).countEconomy()), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                           )
                        );
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("TechnologyLevel") + ": "));
                        nData.add(
                           new MenuElement_Hover_v2_Element_Type_Text(
                              "" + CFG.game.getCiv(this.getCurrent()).getTechnologyLevel(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                           )
                        );
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                        this.menuElementHover = new MenuElement_Hover_v2(nElements);
                     }
                  }
               );
               this.lCivID.add(ix);
               nPosY++;
            }
         }
      }

      this.initMenu(
         null,
         0,
         CFG.BUTTON_HEIGHT * 3 / 4 + CFG.BUTTON_HEIGHT + CFG.PADDING,
         CFG.GAME_WIDTH,
         CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT * 3 / 4 - CFG.BUTTON_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING * 2,
         menuElements,
         true,
         false
      );
      this.updateLanguage();
   }

   @Override
   public void updateLanguage() {
      if (CFG.chosen_AlphabetCharachter == null) {
         this.getMenuElement(0).setText(CFG.langManager.get("RandomCivilization"));
      }
   }

   @Override
   public void actionElement(int iID) {
      switch (iID) {
         case 0:
            if (CFG.chosen_AlphabetCharachter == null) {
               Random oR = new Random();
               int tempRandom = oR.nextInt(this.lCivID.size());
               CFG.game.getCiv(tempRandom).setAllianceID(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_ALLIANCE_ID);
               CFG.game.getAlliance(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_ALLIANCE_ID).addCivilization(tempRandom);
               break;
            }
         default:
            CFG.game.getCiv(this.lCivID.get(iID - (CFG.chosen_AlphabetCharachter == null ? 1 : 0))).setAllianceID(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_ALLIANCE_ID);
            CFG.game
               .getAlliance(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_ALLIANCE_ID)
               .addCivilization(this.lCivID.get(iID - (CFG.chosen_AlphabetCharachter == null ? 1 : 0)));
      }

      CFG.menuManager.setViewID(Menu.eCUSTOMIZE_ALLIANCE);
   }
}
