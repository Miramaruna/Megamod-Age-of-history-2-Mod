package age.of.civilizations2.jakowski.lukasz;

import java.util.ArrayList;

public class Menu_GameEditor extends SliderMenu {
   public Menu_GameEditor() {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      menuElements.add(new Button_Menu(null, (int)(50.0F * CFG.GUI_SCALE), 0, CFG.PADDING, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT, true));
      menuElements.add(new Button_Menu(null, (int)(50.0F * CFG.GUI_SCALE), 0, CFG.BUTTON_HEIGHT + CFG.PADDING * 2, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT, true));
      menuElements.add(new Button_Menu(null, (int)(50.0F * CFG.GUI_SCALE), 0, CFG.BUTTON_HEIGHT * 2 + CFG.PADDING * 3, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT, true));
      menuElements.add(new Button_Menu(null, (int)(50.0F * CFG.GUI_SCALE), 0, CFG.BUTTON_HEIGHT * 3 + CFG.PADDING * 4, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT, true));
      menuElements.add(new Button_Menu(null, (int)(50.0F * CFG.GUI_SCALE), 0, CFG.BUTTON_HEIGHT * 4 + CFG.PADDING * 5, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT, true));
      menuElements.add(new Button_Menu(null, (int)(50.0F * CFG.GUI_SCALE), 0, CFG.BUTTON_HEIGHT * 5 + CFG.PADDING * 6, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT, true));
      menuElements.add(new Button_Menu(null, (int)(50.0F * CFG.GUI_SCALE), 0, CFG.BUTTON_HEIGHT * 6 + CFG.PADDING * 7, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT, true));
      menuElements.add(new Button_Menu(null, (int)(50.0F * CFG.GUI_SCALE), 0, CFG.BUTTON_HEIGHT * 7 + CFG.PADDING * 8, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT, true));
      menuElements.add(new Button_Menu(null, (int)(50.0F * CFG.GUI_SCALE), 0, CFG.BUTTON_HEIGHT * 8 + CFG.PADDING * 9, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT, true));
      menuElements.add(
         new Button_Menu(null, (int)(50.0F * CFG.GUI_SCALE), 0, CFG.BUTTON_HEIGHT * 9 + CFG.PADDING * 10, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT, true)
      );
      this.initMenu(
         null, 0, CFG.BUTTON_HEIGHT * 3 / 4, CFG.GAME_WIDTH, CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT * 3 / 4 - CFG.BUTTON_HEIGHT - CFG.PADDING, menuElements
      );
      this.updateLanguage();
      CFG.lCreateScenario_UndoAssignProvincesCivID = new ArrayList<>();
      CFG.lCreateScenario_UndoWastelandProvinces = new ArrayList<>();
   }

   @Override
   public void updateLanguage() {
      this.getMenuElement(0).setText(CFG.langManager.get("TerrainTypesEditor"));
      this.getMenuElement(1).setText(CFG.langManager.get("GameCivilizations"));
      this.getMenuElement(2).setText(CFG.langManager.get("Unions"));
      this.getMenuElement(3).setText(CFG.langManager.get("PalletCivColorsPackages"));
      this.getMenuElement(4).setText(CFG.langManager.get("ServiceRibbonEditor"));
      this.getMenuElement(5).setText(CFG.langManager.get("ContinentsPackages"));
      this.getMenuElement(6).setText(CFG.langManager.get("RegionsPackages"));
      this.getMenuElement(7).setText(CFG.langManager.get("RandomAlliancesNamesPackages"));
      this.getMenuElement(8).setText(CFG.langManager.get("DiplomacyColorsPackages"));
      this.getMenuElement(9).setText(CFG.langManager.get("Lines"));
   }

   @Override
   public final void actionElement(int iID) {
      switch (iID) {
         case 0:
            CFG.menuManager.setViewID(Menu.eTERRAIN_TYPES_EDITOR);
            break;
         case 1:
            CFG.menuManager.setViewID(Menu.eEDITOR_GAME_CIVS);
            break;
         case 2:
            CFG.menuManager.setViewID(Menu.eEDITOR_UNIONS);
            break;
         case 3:
            CFG.menuManager.setViewID(Menu.eGAME_EDITOR_PALLETS_OF_CIVS_COLORS_PACKAGES);
            break;
         case 4:
            CFG.menuManager.setViewID(Menu.eGAME_EDITOR_SERVICE_RIBBON);
            break;
         case 5:
            CFG.menuManager.setViewID(Menu.eMAP_EDITOR_PACKAGES_CONTINENTS);
            break;
         case 6:
            CFG.menuManager.setViewID(Menu.eMAP_EDITOR_PACKAGES_REGIONS);
            break;
         case 7:
            CFG.menuManager.setViewID(Menu.eGAME_EDITOR_ALLIANCE_NAMES_PACKAGE);
            break;
         case 8:
            CFG.menuManager.setViewID(Menu.eGAME_EDITOR_DIPLOMACY_COLORS_PACKAGES);
            break;
         case 9:
            CFG.menuManager.setViewID(Menu.eGAME_EDITOR_LINES);
      }
   }
}
