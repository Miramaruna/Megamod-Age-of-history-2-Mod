package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

public class Menu_CustomizeAlliance_Options extends SliderMenu {
   public String sName;
   public String sOptional;

   public Menu_CustomizeAlliance_Options() {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      menuElements.add(
         new Button_Menu(null, (int)(50.0F * CFG.GUI_SCALE), 0, CFG.PADDING, CFG.GAME_WIDTH - CFG.BUTTON_WIDTH, CFG.BUTTON_HEIGHT, true) {
            @Override
            public String getTextToDraw() {
               return Menu_CustomizeAlliance_Options.this.sName
                  + (super.getText().equals("") ? "(" + Menu_CustomizeAlliance_Options.this.sOptional + ")" : "")
                  + ": "
                  + super.getText();
            }

            @Override
            public void setText(String sText) {
               if (sText != null) {
                  CFG.game.getAlliance(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_ALLIANCE_ID).setAllianceName(sText);
               }

               super.setText(sText);
            }

            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("AllianceName") + ".", CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
         }
      );
      menuElements.add(new Button_Menu_ArrowRight(CFG.GAME_WIDTH - CFG.BUTTON_WIDTH, CFG.PADDING, CFG.BUTTON_WIDTH, CFG.BUTTON_HEIGHT, true) {
         @Override
         public void buildElementHover() {
            ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
            ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("RandomAllianceName") + ".", CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
            this.menuElementHover = new MenuElement_Hover_v2(nElements);
         }
      });
      menuElements.add(
         new Button_Menu_ReflectedBG(null, -1, CFG.GAME_WIDTH / 2, CFG.BUTTON_HEIGHT + CFG.PADDING * 2, CFG.GAME_WIDTH / 2, CFG.BUTTON_HEIGHT, true) {
            @Override
            public void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
               super.drawText(oSB, iTranslateX, iTranslateY, isActive);
               if (CFG.MANAGE_DIPLOMACY_CUSTOMIZE_ALLIANCE_ID < CFG.game.getAlliancesSize()) {
                  oSB.setColor(
                     new Color(
                        CFG.game.getAlliance(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_ALLIANCE_ID).getColorOfAlliance().getR(),
                        CFG.game.getAlliance(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_ALLIANCE_ID).getColorOfAlliance().getG(),
                        CFG.game.getAlliance(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_ALLIANCE_ID).getColorOfAlliance().getB(),
                        1.0F
                     )
                  );
                  ImageManager.getImage(Images.pix255_255_255)
                     .draw(
                        oSB,
                        this.getPosX() + this.getWidth() / 2 - this.getTextWidth() / 2 + iTranslateX,
                        this.getPosY() + this.getHeight() / 2 + this.getTextHeight() / 2 + CFG.PADDING - 1 + iTranslateY,
                        this.getTextWidth(),
                        CFG.CIV_COLOR_WIDTH
                     );
                  oSB.setColor(Color.WHITE);
               }
            }

            @Override
            public void buildElementHover() {
               if (CFG.MANAGE_DIPLOMACY_CUSTOMIZE_ALLIANCE_ID < CFG.game.getAlliancesSize()) {
                  ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
                  ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
                  nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("ColorOfAlliance") + ".", CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                  nElements.add(new MenuElement_Hover_v2_Element2(nData));
                  nData.clear();
                  this.menuElementHover = new MenuElement_Hover_v2(nElements);
               } else {
                  this.menuElementHover = null;
               }
            }
         }
      );
      menuElements.add(new Button_Menu(null, -1, 0, CFG.BUTTON_HEIGHT + CFG.PADDING * 2, CFG.GAME_WIDTH / 2, CFG.BUTTON_HEIGHT, true) {
         @Override
         public void buildElementHover() {
            ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
            ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("AddNewCivilization") + ".", CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
            this.menuElementHover = new MenuElement_Hover_v2(nElements);
         }
      });

      for (int i = 0; i < CFG.game.getAlliance(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_ALLIANCE_ID).getCivilizationsSize(); i++) {
         menuElements.add(
            new Button_Menu_CivilizationAndFlag(
               CFG.game.getAlliance(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_ALLIANCE_ID).getCivilization(i),
               (int)(50.0F * CFG.GUI_SCALE),
               0,
               CFG.BUTTON_HEIGHT * 2 + CFG.BUTTON_HEIGHT * i + CFG.PADDING * i + CFG.PADDING * 3,
               CFG.GAME_WIDTH - CFG.BUTTON_WIDTH * 2,
               CFG.BUTTON_HEIGHT,
               true
            ) {
               @Override
               public void buildElementHover() {
                  ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
                  ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
                  nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.game.getCiv(this.getCurrent()).getCivName(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                  nData.add(new MenuElement_Hover_v2_Element_Type_Flag(this.getCurrent(), CFG.PADDING, 0));
                  nElements.add(new MenuElement_Hover_v2_Element2(nData));
                  nData.clear();
                  nData.add(new MenuElement_Hover_v2_Element_Type_Space());
                  nElements.add(new MenuElement_Hover_v2_Element2(nData));
                  nData.clear();
                  nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Provinces") + ": "));
                  nData.add(
                     new MenuElement_Hover_v2_Element_Type_Text("" + CFG.game.getCiv(this.getCurrent()).getNumOfProvinces(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE)
                  );
                  nData.add(
                     new MenuElement_Hover_v2_Element_Type_Text(
                        " ["
                           + CFG.getPercentage(
                              CFG.game.getCiv(this.getCurrent()).getNumOfProvinces(),
                              CFG.game.countAlliance_Provinces(CFG.game.getCiv(this.getCurrent()).getAllianceID()),
                              5
                           )
                           + "%]",
                        CFG.COLOR_TEXT_MODIFIER_NEUTRAL
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
                  nData.add(
                     new MenuElement_Hover_v2_Element_Type_Text(
                        " ["
                           + CFG.getPercentage(
                              (float)CFG.game.getCiv(this.getCurrent()).countPopulation(),
                              (float)CFG.game.countAlliance_Population(CFG.game.getCiv(this.getCurrent()).getAllianceID()),
                              5
                           )
                           + "%]",
                        CFG.COLOR_TEXT_MODIFIER_NEUTRAL
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
                  nData.add(
                     new MenuElement_Hover_v2_Element_Type_Text(
                        " ["
                           + CFG.getPercentage(
                              CFG.game.getCiv(this.getCurrent()).countEconomy(),
                              CFG.game.countAlliance_Economy(CFG.game.getCiv(this.getCurrent()).getAllianceID()),
                              5
                           )
                           + "%]",
                        CFG.COLOR_TEXT_MODIFIER_NEUTRAL
                     )
                  );
                  nElements.add(new MenuElement_Hover_v2_Element2(nData));
                  nData.clear();
                  nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("TechnologyLevel") + ": "));
                  nData.add(
                     new MenuElement_Hover_v2_Element_Type_Text("" + CFG.game.getCiv(this.getCurrent()).getTechnologyLevel(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE)
                  );
                  nElements.add(new MenuElement_Hover_v2_Element2(nData));
                  nData.clear();
                  this.menuElementHover = new MenuElement_Hover_v2(nElements);
               }
            }
         );
         menuElements.add(
            new Button_Menu_UP(
               CFG.GAME_WIDTH - CFG.BUTTON_WIDTH * 2,
               CFG.BUTTON_HEIGHT * 2 + CFG.BUTTON_HEIGHT * i + CFG.PADDING * i + CFG.PADDING * 3,
               CFG.BUTTON_WIDTH,
               CFG.BUTTON_HEIGHT,
               true
            )
         );
         menuElements.add(
            new Button_Menu_Remove(
               CFG.GAME_WIDTH - CFG.BUTTON_WIDTH,
               CFG.BUTTON_HEIGHT * 2 + CFG.BUTTON_HEIGHT * i + CFG.PADDING * i + CFG.PADDING * 3,
               CFG.BUTTON_WIDTH,
               CFG.BUTTON_HEIGHT,
               true
            ) {
               @Override
               public void buildElementHover() {
                  ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
                  ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
                  nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Delete"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                  nElements.add(new MenuElement_Hover_v2_Element2(nData));
                  nData.clear();
                  this.menuElementHover = new MenuElement_Hover_v2(nElements);
               }
            }
         );
      }

      this.initMenu(
         new SliderMenuTitle(null, CFG.BUTTON_HEIGHT * 3 / 4, false, false),
         0,
         CFG.BUTTON_HEIGHT * 3 / 4,
         CFG.GAME_WIDTH,
         CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.BUTTON_HEIGHT * 3 / 4 - CFG.PADDING,
         menuElements,
         true,
         false
      );
      this.updateLanguage();
   }

   @Override
   public void updateLanguage() {
      this.sName = CFG.langManager.get("AllianceName");
      this.sOptional = CFG.langManager.get("Optional");
      this.getMenuElement(0).setText("" + CFG.game.getAlliance(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_ALLIANCE_ID).getAllianceName());
      this.getMenuElement(2).setText(CFG.langManager.get("ColorOfAlliance"));
      this.getMenuElement(3).setText(CFG.langManager.get("AddCivilization"));
      this.getTitle().setText(CFG.langManager.get("CustomizeAlliance"));
   }

   @Override
   public void actionElement(int iID) {
      switch (iID) {
         case 0:
            CFG.showKeyboard();
            return;
         case 1:
            CFG.game.getAlliance(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_ALLIANCE_ID).setAllianceName(CFG.getRandomAllianceName(0));
            this.getMenuElement(0).setText("" + CFG.game.getAlliance(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_ALLIANCE_ID).getAllianceName());
            return;
         case 2:
            if (CFG.menuManager.getColorPicker().getVisible()) {
               CFG.menuManager.getColorPicker().setVisible(false, null);
            } else {
               CFG.menuManager.getColorPicker().setPosX(CFG.PADDING * 4);
               CFG.menuManager.getColorPicker().setPosY(this.getPosY() + this.getMenuElement(iID).getPosY());
               CFG.menuManager
                  .getColorPicker()
                  .setActiveRGBColor(
                     (int)(CFG.game.getAlliance(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_ALLIANCE_ID).getColorOfAlliance().getR() * 255.0F),
                     (int)(CFG.game.getAlliance(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_ALLIANCE_ID).getColorOfAlliance().getG() * 255.0F),
                     (int)(CFG.game.getAlliance(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_ALLIANCE_ID).getColorOfAlliance().getB() * 255.0F)
                  );
               CFG.menuManager.getColorPicker().setVisible(true, ColorPicker_AoC.PickerAction.CUSTOMIZE_ALLIANCE_COLOR);
            }

            return;
         case 3:
            CFG.menuManager.getColorPicker().setVisible(false, null);
            CFG.chosen_AlphabetCharachter = null;
            CFG.menuManager.setViewID(Menu.eCUSTOMIZE_ALLIANCE_ADD_CIVILIZATION);
            return;
         default:
            if ((iID - 4) % 3 == 2) {
               CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1 = CFG.game.getAlliance(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_ALLIANCE_ID).getCivilization((iID - 4) / 3);
               CFG.setDialogType(Dialog.MANAGE_DIPLOMACY_REMOVE_CIVILIZATION_FROM_ALLIANCE);
            } else if ((iID - 4) % 3 == 1) {
               CFG.game.getAlliance(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_ALLIANCE_ID).moveUp((iID - 4) / 3);
               CFG.menuManager.setViewIDWithoutAnimation(Menu.eCUSTOMIZE_ALLIANCE);
            } else {
               try {
                  CFG.map
                     .getMapCoordinates()
                     .centerToProvinceID(
                        CFG.game.getCiv(CFG.game.getAlliance(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_ALLIANCE_ID).getCivilization((iID - 4) / 3)).getCapitalProvinceID()
                     );
                  CFG.game
                     .setActiveProvinceID(
                        CFG.game.getCiv(CFG.game.getAlliance(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_ALLIANCE_ID).getCivilization((iID - 4) / 3)).getCapitalProvinceID()
                     );
                  CFG.toast
                     .setInView(
                        "" + CFG.game.getCiv(CFG.game.getAlliance(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_ALLIANCE_ID).getCivilization((iID - 4) / 3)).getCivName()
                     );
               } catch (IndexOutOfBoundsException var3) {
               }
            }
      }
   }
}
