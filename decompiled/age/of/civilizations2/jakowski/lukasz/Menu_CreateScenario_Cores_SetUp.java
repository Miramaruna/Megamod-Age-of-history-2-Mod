package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;
import java.util.List;

public class Menu_CreateScenario_Cores_SetUp extends SliderMenu {
   public List<Integer> brushCivs = new ArrayList<>();

   public Menu_CreateScenario_Cores_SetUp() {
      int tempW = CFG.CIV_INFO_MENU_WIDTH + CFG.CIV_INFO_MENU_WIDTH * 2 / 5;
      int tempElemH = CFG.BUTTON_HEIGHT * 3 / 4;
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      int tPosY = CFG.PADDING;
      menuElements.add(
         new Button_NewGameStyle(CFG.langManager.get("AddCore"), -1, CFG.PADDING, tPosY, tempW - CFG.PADDING * 2, (int)(CFG.BUTTON_HEIGHT * 0.6F), true)
      );
      tPosY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
      this.brushCivs.clear();
      if (CFG.game.getSelectedProvinces().getProvincesSize() < 2) {
         try {
            try {
               if (CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCore().getCivsSize() > 0) {
                  menuElements.add(
                     new Text_BudgetTitle(CFG.langManager.get("Population"), -1, 0, tPosY, tempW, CFG.TEXT_HEIGHT + CFG.PADDING * 4) {
                        @Override
                        public Color getColor(boolean isActive) {
                           return isActive
                              ? CFG.COLOR_TEXT_OPTIONS_NS_HOVER
                              : (this.getClickable() ? (this.getIsHovered() ? CFG.COLOR_TEXT_OPTIONS_NS : Color.WHITE) : new Color(0.78F, 0.78F, 0.78F, 0.7F));
                        }

                        @Override
                        public void buildElementHover() {
                           ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
                           ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
                           nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()));
                           nData.add(
                              new MenuElement_Hover_v2_Element_Type_Text(
                                 CFG.langManager.get("XOfPopulationIsRequiredToGetACore", 18), CFG.COLOR_TEXT_NUM_OF_PROVINCES
                              )
                           );
                           nElements.add(new MenuElement_Hover_v2_Element2(nData));
                           nData.clear();
                           this.menuElementHover = new MenuElement_Hover_v2(nElements);
                        }
                     }
                  );
                  tPosY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
               }

               for (int i = 0; i < CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCore().getCivsSize(); i++) {
                  menuElements.add(
                     new Slider_FlagAction_Clear_Flag(
                        CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCore().getCivID(i),
                        "" + CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCore().getCivID(i)).getCivName(),
                        CFG.PADDING,
                        tPosY,
                        tempW - CFG.PADDING * 2 - CFG.PADDING - tempElemH,
                        tempElemH,
                        1,
                        100,
                        (int)(
                           CFG.province_Cores_GameData
                                 .getPercOfPop(CFG.game.getActiveProvinceID(), CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCore().getCivID(i))
                              * 100.0F
                        )
                     ) {
                        @Override
                        public String getDrawText() {
                           return this.getCurrent() + "%";
                        }
                     }
                  );
                  menuElements.add(
                     new Button_FlagActionSliderStyle("", -1, tempW - CFG.PADDING - tempElemH, tPosY, tempElemH, tempElemH, i != 0) {
                        @Override
                        public void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                           super.drawButtonBG(oSB, iTranslateX, iTranslateY, isActive);
                           if (!this.getClickable()) {
                              oSB.setColor(1.0F, 1.0F, 1.0F, 0.3F);
                           } else if (isActive) {
                              oSB.setColor(1.0F, 1.0F, 1.0F, 0.5F);
                           } else if (this.getIsHovered()) {
                              oSB.setColor(1.0F, 1.0F, 1.0F, 0.7F);
                           } else {
                              oSB.setColor(Color.WHITE);
                           }

                           ImageManager.getImage(Images.btn_remove)
                              .draw(
                                 oSB,
                                 this.getPosX() + this.getWidth() / 2 - ImageManager.getImage(Images.btn_remove).getWidth() / 2 + iTranslateX,
                                 this.getPosY() + this.getHeight() / 2 - ImageManager.getImage(Images.btn_remove).getHeight() / 2 + iTranslateY
                              );
                           oSB.setColor(Color.WHITE);
                        }

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
                  tPosY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
               }
            } catch (IndexOutOfBoundsException var9) {
            }
         } catch (NullPointerException var10) {
            menuElements.add(
               new Slider_FlagAction_Clear_Flag(
                  CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID(),
                  "" + CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).getCivName(),
                  CFG.PADDING,
                  tPosY,
                  tempW - CFG.PADDING * 2,
                  tempElemH,
                  1,
                  100,
                  100
               ) {
                  @Override
                  public String getDrawText() {
                     return this.getCurrent() + "%";
                  }
               }
            );
            menuElements.get(menuElements.size() - 1).setClickable(false);
            tPosY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
         }
      } else {
         for (int i = 0; i < CFG.game.getSelectedProvinces().getProvincesSize(); i++) {
            for (int j = 0; j < CFG.game.getProvince(CFG.game.getSelectedProvinces().getProvince(i)).getCore().getCivsSize(); j++) {
               boolean add = true;

               for (int k = 0; k < this.brushCivs.size(); k++) {
                  if (this.brushCivs.get(k) == CFG.game.getProvince(CFG.game.getSelectedProvinces().getProvince(i)).getCore().getCivID(j)) {
                     add = false;
                     break;
                  }
               }

               if (add) {
                  this.brushCivs.add(CFG.game.getProvince(CFG.game.getSelectedProvinces().getProvince(i)).getCore().getCivID(j));
               }
            }
         }

         for (int var13 = 0; var13 < this.brushCivs.size(); var13++) {
            menuElements.add(
               new Button_New_Game_Players_CivID_LEFT(
                  this.brushCivs.get(var13),
                  "" + CFG.game.getCiv(this.brushCivs.get(var13)).getCivName(),
                  -1,
                  CFG.PADDING + 2,
                  tPosY,
                  tempW - CFG.PADDING * 2 - 2 - (int)(CFG.BUTTON_HEIGHT * 0.6F),
                  true
               )
            );
            menuElements.add(
               new Button_New_Game_Players_Players_RIGHT(
                  null, -1, tempW - CFG.PADDING - (int)(CFG.BUTTON_HEIGHT * 0.6F), tPosY, (int)(CFG.BUTTON_HEIGHT * 0.6F), true
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
            tPosY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
         }
      }

      this.initMenu(
         new SliderMenuTitle(null, CFG.BUTTON_HEIGHT * 3 / 5, false, false) {
            @Override
            public void draw(SpriteBatch oSB, int iTranslateX, int nPosX, int nPosY, int nWidth, boolean sliderMenuIsActive) {
               ImageManager.getImage(Images.dialog_title)
                  .draw2(
                     oSB,
                     nPosX - 2 + iTranslateX,
                     nPosY - this.getHeight() - ImageManager.getImage(Images.dialog_title).getHeight(),
                     nWidth + 4,
                     this.getHeight()
                  );
               oSB.setColor(new Color(0.003921569F, 0.32941177F, 0.50980395F, 0.165F));
               ImageManager.getImage(Images.line_32_off1)
                  .draw(
                     oSB,
                     nPosX + iTranslateX,
                     nPosY - this.getHeight() + 2 - ImageManager.getImage(Images.line_32_off1).getHeight(),
                     nWidth,
                     this.getHeight() - 2,
                     false,
                     true
                  );
               oSB.setColor(new Color(0.003921569F, 0.32941177F, 0.50980395F, 0.375F));
               ImageManager.getImage(Images.gradient)
                  .draw(
                     oSB,
                     nPosX + iTranslateX,
                     nPosY - this.getHeight() * 2 / 3 - ImageManager.getImage(Images.gradient).getHeight(),
                     nWidth,
                     this.getHeight() * 2 / 3,
                     false,
                     true
                  );
               oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.65F));
               ImageManager.getImage(Images.gradient)
                  .draw(oSB, nPosX + iTranslateX, nPosY - CFG.PADDING - ImageManager.getImage(Images.gradient).getHeight(), nWidth, CFG.PADDING, false, true);
               oSB.setColor(CFG.COLOR_NEW_GAME_EDGE_LINE);
               ImageManager.getImage(Images.pix255_255_255)
                  .draw(oSB, nPosX + iTranslateX, nPosY - 1 - ImageManager.getImage(Images.pix255_255_255).getHeight(), nWidth, 1);
               oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.55F));
               ImageManager.getImage(Images.line_32_off1)
                  .draw(oSB, nPosX + iTranslateX, nPosY - 2 - ImageManager.getImage(Images.line_32_off1).getHeight(), nWidth, 1);
               oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.8F));
               ImageManager.getImage(Images.line_32_off1)
                  .draw(oSB, nPosX + iTranslateX, nPosY - 1 - ImageManager.getImage(Images.line_32_off1).getHeight(), nWidth, 1);
               oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.45F));
               ImageManager.getImage(Images.slider_gradient)
                  .draw(oSB, nPosX + iTranslateX, nPosY - 1 - ImageManager.getImage(Images.slider_gradient).getHeight(), nWidth / 2, 1);
               ImageManager.getImage(Images.slider_gradient)
                  .draw(
                     oSB,
                     nPosX + nWidth - nWidth / 2 + iTranslateX,
                     nPosY - 1 - ImageManager.getImage(Images.slider_gradient).getHeight(),
                     nWidth / 2,
                     1,
                     true,
                     false
                  );
               oSB.setColor(Color.WHITE);
               CFG.fontMain.getData().setScale(0.8F);
               CFG.drawText(
                  oSB,
                  this.getText(),
                  nPosX + (int)(nWidth - this.getTextWidth() * 0.8F) / 2 + iTranslateX,
                  2 + nPosY - this.getHeight() + (int)(this.getHeight() - this.getTextHeight() * 0.8F) / 2,
                  Color.WHITE
               );
               CFG.fontMain.getData().setScale(1.0F);
            }
         },
         CFG.GAME_WIDTH - tempW,
         CFG.BUTTON_HEIGHT * 2 + CFG.PADDING * 4 + CFG.PADDING * 2 + CFG.BUTTON_HEIGHT * 3 / 5,
         tempW,
         Math.min(
            tPosY,
            CFG.GAME_HEIGHT - (CFG.BUTTON_HEIGHT * 2 + CFG.PADDING * 4 + CFG.PADDING * 2 + CFG.BUTTON_HEIGHT * 3 / 5) - CFG.BUTTON_HEIGHT - CFG.PADDING * 2
         ),
         menuElements
      );
      this.setVisible(false);
      this.updateLanguage();
   }

   @Override
   public void updateLanguage() {
      try {
         this.getTitle()
            .setText(
               CFG.game.getProvince(CFG.game.getActiveProvinceID()).getName().length() > 0
                  ? CFG.game.getProvince(CFG.game.getActiveProvinceID()).getName()
                  : CFG.langManager.get("Cores")
            );
      } catch (IndexOutOfBoundsException var2) {
         CFG.langManager.get("Cores");
      }
   }

   @Override
   public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      ImageManager.getImage(Images.new_game_top_edge_line)
         .draw2(
            oSB,
            this.getPosX() - 2 + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.new_game_top_edge_line).getHeight() + iTranslateY,
            this.getWidth() + 2,
            this.getHeight(),
            false,
            true
         );
      super.draw(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
      oSB.setColor(CFG.COLOR_CREATE_NEW_GAME_BOX_PLAYERS);
      ImageManager.getImage(Images.pix255_255_255)
         .draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + this.getHeight(), this.getWidth());
      oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.4F));
      ImageManager.getImage(Images.line_32_off1)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY()
               - ImageManager.getImage(Images.pix255_255_255).getHeight()
               - ImageManager.getImage(Images.line_32_off1).getHeight()
               + this.getHeight(),
            this.getWidth(),
            1
         );
      oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.5F));
      ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() - 2 + iTranslateX, this.getPosY() + this.getHeight(), this.getWidth() + 2);
      oSB.setColor(Color.WHITE);
   }

   @Override
   public void actionElement(int iID) {
      if (iID == 0) {
         CFG.MANAGE_DIPLOMACY_CUSTOMIZE_ALLIANCE_ID = 35;
         CFG.menuManager.setViewID(Menu.eCREATE_SCENARIO_CORES_ADD_CORE);
      }

      iID -= 2;

      try {
         if (this.brushCivs.size() > 0) {
            if (++iID % 2 == 0) {
               CFG.toast.setInView(this.getMenuElement(iID + 2).getText(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE);
            } else {
               for (int i = 0; i < CFG.game.getSelectedProvinces().getProvincesSize(); i++) {
                  if (CFG.game.getProvince(CFG.game.getSelectedProvinces().getProvince(i)).getCivID() != this.brushCivs.get(iID / 2)) {
                     CFG.game.getProvince(CFG.game.getSelectedProvinces().getProvince(i)).getCore().removeCore(this.brushCivs.get(iID / 2));
                     CFG.province_Cores_GameData.removeCore(CFG.game.getSelectedProvinces().getProvince(i), this.brushCivs.get(iID / 2));
                     CFG.province_Cores_GameData
                        .updatePercOfPopulation(
                           CFG.game.getSelectedProvinces().getProvince(i),
                           CFG.game.getProvince(CFG.game.getSelectedProvinces().getProvince(i)).getCore().getCivID(0),
                           (int)(
                              CFG.province_Cores_GameData
                                    .getPercOfPop(
                                       CFG.game.getSelectedProvinces().getProvince(i),
                                       CFG.game.getProvince(CFG.game.getSelectedProvinces().getProvince(i)).getCore().getCivID(0)
                                    )
                                 * 100.0F
                           )
                        );
                  }
               }

               CFG.menuManager.rebuildCreateScenario_Cores_SetUp();
            }
         } else if (iID % 2 == 0) {
            CFG.province_Cores_GameData
               .updatePercOfPopulation(
                  CFG.game.getActiveProvinceID(),
                  CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCore().getCivID(iID / 2),
                  this.getMenuElement(iID + 2).getCurrent()
               );

            for (int ix = 0; ix < CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCore().getCivsSize(); ix++) {
               this.getMenuElement(2 + ix * 2)
                  .setCurrent(
                     (int)(
                        CFG.province_Cores_GameData
                              .getPercOfPop(CFG.game.getActiveProvinceID(), CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCore().getCivID(ix))
                           * 100.0F
                     )
                  );
            }
         } else if (this.getMenuElement(iID + 2).getClickable()) {
            if (CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCore().getCivID(iID / 2)
               != CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()) {
               CFG.province_Cores_GameData
                  .removeCore(CFG.game.getActiveProvinceID(), CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCore().getCivID(iID / 2));
               CFG.game
                  .getProvince(CFG.game.getActiveProvinceID())
                  .getCore()
                  .removeCore(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCore().getCivID(iID / 2));
               CFG.province_Cores_GameData
                  .updatePercOfPopulation(
                     CFG.game.getActiveProvinceID(),
                     CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCore().getCivID(0),
                     (int)(
                        CFG.province_Cores_GameData
                              .getPercOfPop(CFG.game.getActiveProvinceID(), CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCore().getCivID(0))
                           * 100.0F
                     )
                  );
            }

            CFG.menuManager.rebuildCreateScenario_Cores_SetUp();
         }
      } catch (IndexOutOfBoundsException var3) {
      } catch (NullPointerException var4) {
      }
   }
}
