package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;
import java.util.List;

public class Menu_Civilization_Info_Stats extends SliderMenu {
   public Menu_Civilization_Info_Stats() {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      menuElements.add(new Text_CivInfo(null, CFG.PADDING * 3 + 2, CFG.PADDING * 3) {
         @Override
         public void buildElementHover() {
            this.menuElementHover = CFG.game.getHover_PopulationOfCiv(CFG.getActiveCivInfo());
         }
      });
      menuElements.add(
         new Text_LeftSide_Icon("", CFG.CIV_INFO_MENU_WIDTH - CFG.PADDING * 2, CFG.PADDING * 3, Images.population) {
            @Override
            public void buildElementHover() {
               this.menuElementHover = CFG.game.getHover_PopulationOfCiv(CFG.getActiveCivInfo());
            }

            @Override
            public Color getColor(boolean isActive) {
               return isActive
                  ? CFG.COLOR_TEXT_POPULATION_ACTIVE
                  : (
                     this.getClickable()
                        ? (this.getIsHovered() ? CFG.COLOR_TEXT_POPULATION_HOVER : CFG.COLOR_TEXT_POPULATION)
                        : new Color(0.78F, 0.78F, 0.78F, 0.7F)
                  );
            }
         }
      );
      menuElements.add(new Text_CivInfo(null, CFG.PADDING * 3 + 2, CFG.PADDING * 3 + CFG.TEXT_HEIGHT + CFG.PADDING) {
         @Override
         public void buildElementHover() {
            this.menuElementHover = CFG.game.getHover_CapitalCity(CFG.getActiveCivInfo());
         }
      });
      menuElements.add(new Text_LeftSide_Icon("", CFG.CIV_INFO_MENU_WIDTH - CFG.PADDING * 2, CFG.PADDING * 3 + CFG.TEXT_HEIGHT + CFG.PADDING, Images.city) {
         @Override
         public void buildElementHover() {
            this.menuElementHover = CFG.game.getHover_CapitalCity(CFG.getActiveCivInfo());
         }
      });
      menuElements.add(new Text_CivInfo(null, CFG.PADDING * 3 + 2, CFG.PADDING * 3 + (CFG.TEXT_HEIGHT + CFG.PADDING) * 2) {
         @Override
         public void buildElementHover() {
            this.menuElementHover = CFG.game.getHover_LargestCity(Menu_Civilization_Info_Stats.this.getMenuElement(7).getCurrent());
         }
      });
      menuElements.add(new Text_LeftSide("", CFG.CIV_INFO_MENU_WIDTH - CFG.PADDING * 2, CFG.PADDING * 3 + (CFG.TEXT_HEIGHT + CFG.PADDING) * 2) {
         int iCurrent;

         @Override
         public int getCurrent() {
            return this.iCurrent;
         }

         @Override
         public void setCurrent(int nCurrent) {
            this.iCurrent = nCurrent;
         }

         @Override
         public void buildElementHover() {
            this.menuElementHover = CFG.game.getHover_LargestCity(this.getCurrent());
         }
      });
      ArrayList<Integer> lData = new ArrayList<>();
      ArrayList<Integer> lCivs = new ArrayList<>();
      lData.add(18);
      lCivs.add(1);
      lData.add(7);
      lCivs.add(2);
      menuElements.add(new Graph_Circle(CFG.PADDING * 3 + 2, CFG.PADDING * 3 + (CFG.TEXT_HEIGHT + CFG.PADDING) * 3 + CFG.PADDING, lData, lCivs, null) {
         @Override
         public void buildElementHover() {
            this.menuElementHover = CFG.game.getHover_PopulationOfCiv(CFG.getActiveCivInfo());
         }
      });
      menuElements.add(
         new Text_CivInfo(
            null, CFG.PADDING * 3 + 2, CFG.PADDING * 3 + (CFG.TEXT_HEIGHT + CFG.PADDING) * 3 + CFG.PADDING + CFG.graphCircleDraw.getWidth() + CFG.PADDING * 3
         ) {
            @Override
            public void buildElementHover() {
               this.menuElementHover = CFG.game.getHover_TechnologyLevel(CFG.getActiveCivInfo());
            }
         }
      );
      menuElements.add(
         new Text_LeftSide_Icon(
            "",
            CFG.CIV_INFO_MENU_WIDTH - CFG.PADDING * 2,
            CFG.PADDING * 3 + (CFG.TEXT_HEIGHT + CFG.PADDING) * 3 + CFG.PADDING + CFG.graphCircleDraw.getWidth() + CFG.PADDING * 3,
            Images.technology
         ) {
            @Override
            public void buildElementHover() {
               this.menuElementHover = CFG.game.getHover_TechnologyLevel(CFG.getActiveCivInfo());
            }
         }
      );
      menuElements.add(
         new Text_CivInfo(
            null, CFG.PADDING * 3 + 2, CFG.PADDING * 3 + (CFG.TEXT_HEIGHT + CFG.PADDING) * 4 + CFG.PADDING + CFG.graphCircleDraw.getWidth() + CFG.PADDING * 3
         )
      );
      menuElements.add(
         new Text_LeftSide_Icon(
            "",
            CFG.CIV_INFO_MENU_WIDTH - CFG.PADDING * 2,
            CFG.PADDING * 3 + (CFG.TEXT_HEIGHT + CFG.PADDING) * 4 + CFG.PADDING + CFG.graphCircleDraw.getWidth() + CFG.PADDING * 3,
            Images.economy
         ) {
            @Override
            public Color getColor(boolean isActive) {
               return isActive
                  ? CFG.COLOR_TEXT_ECONOMY_ACTIVE
                  : (this.getClickable() ? (this.getIsHovered() ? CFG.COLOR_TEXT_ECONOMY_HOVER : CFG.COLOR_TEXT_ECONOMY) : new Color(0.78F, 0.78F, 0.78F, 0.7F));
            }
         }
      );
      menuElements.add(
         new Text(
            null,
            -1,
            0,
            CFG.PADDING * 3 + (CFG.TEXT_HEIGHT + CFG.PADDING) * 5 + CFG.PADDING + CFG.graphCircleDraw.getWidth() + CFG.PADDING * 4,
            CFG.CIV_INFO_MENU_WIDTH,
            CFG.TEXT_HEIGHT + CFG.PADDING * 2
         ) {
            @Override
            public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
               CFG.drawRect_InfoBox_Right_Title(oSB, this.getPosX() + 2 + iTranslateX, this.getPosY() + iTranslateY, this.getWidth() - 2, this.getHeight());
               CFG.fontMain.getData().setScale(0.7F);
               CFG.drawTextWithShadow(
                  oSB,
                  this.getText(),
                  this.getPosX() + this.getWidth() / 2 - (int)(this.getTextWidth() * 0.7F) / 2 + iTranslateX,
                  this.getPosY() + this.getHeight() / 2 - (int)(this.getTextHeight() * 0.7F) / 2 + iTranslateY,
                  CFG.COLOR_TEXT_CIV_INFO_TITLE
               );
               CFG.fontMain.getData().setScale(1.0F);
            }
         }
      );
      menuElements.add(
         new Difficulty_Level(
            ImageManager.getImage(Images.difficulty_heaven).getWidth() + CFG.PADDING * 3 + 2,
            CFG.PADDING * 4 + (CFG.TEXT_HEIGHT + CFG.PADDING) * 6 + CFG.PADDING + CFG.graphCircleDraw.getWidth() + CFG.PADDING * 6,
            CFG.CIV_INFO_MENU_WIDTH
               - (ImageManager.getImage(Images.difficulty_heaven).getWidth() + CFG.PADDING * 3 + 2)
               - ImageManager.getImage(Images.difficulty_hell).getWidth()
               - CFG.PADDING * 3,
            CFG.TEXT_HEIGHT * 2 + CFG.PADDING * 3 - CFG.PADDING * 4,
            0.65F
         ) {
            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("DifficultyLevel") + ": "));
               nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + this.getCurrent() + "%", CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }

            @Override
            public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
               ImageManager.getImage(Images.difficulty_heaven)
                  .draw(
                     oSB,
                     this.getPosX() - CFG.PADDING - ImageManager.getImage(Images.difficulty_heaven).getWidth() + iTranslateX,
                     this.getPosY() + this.getHeight() / 2 - ImageManager.getImage(Images.difficulty_heaven).getHeight() / 2 + iTranslateY
                  );
               ImageManager.getImage(Images.difficulty_hell)
                  .draw(
                     oSB,
                     this.getPosX() + this.getWidth() + CFG.PADDING + iTranslateX,
                     this.getPosY() + this.getHeight() / 2 - ImageManager.getImage(Images.difficulty_hell).getHeight() / 2 + iTranslateY
                  );
               super.draw(oSB, iTranslateX, iTranslateY, isActive, scrollableY);
            }
         }
      );
      menuElements.add(
         new Text_LeftSide_Happiness(
            "", CFG.CIV_INFO_MENU_WIDTH - CFG.PADDING * 2, menuElements.get(6).getPosY() + menuElements.get(6).getHeight() - CFG.TEXT_HEIGHT * 2 - CFG.PADDING
         ) {
            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.getActiveCivInfo()));
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Happiness") + ": "));
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(this.getText(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
         }
      );
      menuElements.add(
         new Text_LeftSide_Ideology(
            "", CFG.CIV_INFO_MENU_WIDTH - CFG.PADDING * 2, menuElements.get(6).getPosY() + menuElements.get(6).getHeight() - CFG.TEXT_HEIGHT
         ) {
            @Override
            public void buildElementHover() {
               this.menuElementHover = CFG.ideologiesManager.getIdeologyHover(CFG.getActiveCivInfo());
            }
         }
      );
      menuElements.add(
         new Button_Transparent(
            0,
            0,
            CFG.CIV_INFO_MENU_WIDTH,
            menuElements.get(menuElements.size() - 3).getPosY() + menuElements.get(menuElements.size() - 3).getHeight() + CFG.PADDING,
            true
         )
      );
      this.initMenu(
         null,
         CFG.GAME_WIDTH - CFG.CIV_INFO_MENU_WIDTH,
         (
               CFG.isAndroid() && !CFG.LANDSCAPE
                  ? ImageManager.getImage(Images.new_game_top).getHeight() + CFG.PADDING * 4 + (int)(CFG.TEXT_HEIGHT * 0.6F)
                  : ImageManager.getImage(Images.top_left2).getHeight() + CFG.PADDING * 2
            )
            + ImageManager.getImage(Images.top_flag_frame).getHeight()
            + CFG.PADDING * 4,
         CFG.CIV_INFO_MENU_WIDTH,
         CFG.PADDING * 4 + (CFG.TEXT_HEIGHT + CFG.PADDING) * 7 + CFG.PADDING + CFG.graphCircleDraw.getWidth() + CFG.PADDING * 8,
         menuElements,
         false,
         false
      );
      this.updateLanguage();
   }

   @Override
   public void updateLanguage() {
      this.getMenuElement(0).setText(CFG.langManager.get("Population"));
      this.getMenuElement(2).setText(CFG.langManager.get("Capital"));
      this.getMenuElement(4).setText(CFG.langManager.get("LargestCity"));
      this.getMenuElement(7).setText(CFG.langManager.get("TechnologyLevel"));
      this.getMenuElement(9).setText(CFG.langManager.get("Economy"));
      this.getMenuElement(11).setText(CFG.langManager.get("Difficulty"));
   }

   @Override
   public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      if (Menu_Civilization_Info.lTime + 250L >= System.currentTimeMillis()) {
         iTranslateX += this.getWidth() - (int)(this.getWidth() * ((float)(System.currentTimeMillis() - Menu_Civilization_Info.lTime) / 250.0F));
         CFG.setRender_3(true);
      }

      ImageManager.getImage(Images.new_game_top_edge_line)
         .draw2(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.new_game_top_edge_line).getHeight() + iTranslateY,
            this.getWidth(),
            this.getHeight() + 2
         );
      this.beginClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
      oSB.setColor(Color.WHITE);
      CFG.drawRect_InfoBox_Right(
         oSB,
         this.getPosX() + this.getMenuElement(0).getPosX() - CFG.PADDING + iTranslateX,
         this.getMenuPosY() + this.getMenuElement(0).getPosY() - CFG.PADDING + iTranslateY,
         this.getMenuElement(1).getPosX() - this.getMenuElement(0).getPosX() + this.getMenuElement(1).getWidth() + CFG.PADDING * 2,
         this.getMenuElement(6).getPosY() + this.getMenuElement(6).getHeight() - this.getMenuElement(0).getPosY() + CFG.PADDING * 2
      );

      try {
         if (CFG.activeCivLeader != null) {
            float fScale = (this.getMenuElement(6).getPosY() + this.getMenuElement(6).getHeight() - this.getMenuElement(0).getPosY() + CFG.PADDING * 2 - 2.0F)
               / CFG.activeCivLeader.getHeight();
            CFG.activeCivLeader
               .draw(
                  oSB,
                  this.getPosX() + this.getMenuElement(0).getPosX() - CFG.PADDING + 1 + iTranslateX,
                  this.getMenuPosY() + this.getMenuElement(0).getPosY() - CFG.PADDING - CFG.activeCivLeader.getHeight() + 1 + iTranslateY,
                  (int)(CFG.activeCivLeader.getWidth() * fScale),
                  this.getMenuElement(6).getPosY() + this.getMenuElement(6).getHeight() - this.getMenuElement(0).getPosY() + CFG.PADDING * 2 - 2
               );
            oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.575F));
            ImageManager.getImage(Images.gradient)
               .draw(
                  oSB,
                  this.getPosX() + this.getMenuElement(0).getPosX() - CFG.PADDING + 1 + iTranslateX,
                  this.getMenuPosY() + this.getMenuElement(0).getPosY() - CFG.PADDING - ImageManager.getImage(Images.gradient).getHeight() + 1 + iTranslateY,
                  (int)(CFG.activeCivLeader.getWidth() * fScale),
                  CFG.PADDING
               );
            ImageManager.getImage(Images.gradient)
               .draw(
                  oSB,
                  this.getPosX() + this.getMenuElement(0).getPosX() - CFG.PADDING + 1 + iTranslateX,
                  this.getMenuPosY()
                     + this.getMenuElement(0).getPosY()
                     - CFG.PADDING
                     - ImageManager.getImage(Images.gradient).getHeight()
                     + 1
                     + (this.getMenuElement(6).getPosY() + this.getMenuElement(6).getHeight() - this.getMenuElement(0).getPosY() + CFG.PADDING * 2 - 2)
                     - CFG.PADDING
                     + iTranslateY,
                  (int)(CFG.activeCivLeader.getWidth() * fScale),
                  CFG.PADDING,
                  false,
                  true
               );
            ImageManager.getImage(Images.slider_gradient)
               .draw(
                  oSB,
                  this.getPosX() + this.getMenuElement(0).getPosX() - CFG.PADDING + 1 + iTranslateX,
                  this.getMenuPosY()
                     + this.getMenuElement(0).getPosY()
                     - CFG.PADDING
                     - ImageManager.getImage(Images.slider_gradient).getHeight()
                     + 1
                     + iTranslateY,
                  CFG.PADDING,
                  this.getMenuElement(6).getPosY() + this.getMenuElement(6).getHeight() - this.getMenuElement(0).getPosY() + CFG.PADDING * 2 - 2
               );
            ImageManager.getImage(Images.slider_gradient)
               .draw(
                  oSB,
                  this.getPosX()
                     + this.getMenuElement(0).getPosX()
                     - CFG.PADDING
                     + 1
                     + (int)(CFG.activeCivLeader.getWidth() * fScale)
                     - CFG.PADDING
                     + iTranslateX,
                  this.getMenuPosY()
                     + this.getMenuElement(0).getPosY()
                     - CFG.PADDING
                     - ImageManager.getImage(Images.slider_gradient).getHeight()
                     + 1
                     + iTranslateY,
                  CFG.PADDING,
                  this.getMenuElement(6).getPosY() + this.getMenuElement(6).getHeight() - this.getMenuElement(0).getPosY() + CFG.PADDING * 2 - 2,
                  true,
                  false
               );
            oSB.setColor(new Color(CFG.COLOR_TEXT_NUM_OF_PROVINCES.r, CFG.COLOR_TEXT_NUM_OF_PROVINCES.g, CFG.COLOR_TEXT_NUM_OF_PROVINCES.b, 0.175F));
            CFG.drawRect(
               oSB,
               this.getMenuElement(0).getPosX() - CFG.PADDING + 1 + iTranslateX,
               this.getMenuPosY() + this.getMenuElement(0).getPosY() - CFG.PADDING + iTranslateY,
               (int)(CFG.activeCivLeader.getWidth() * fScale) - 1,
               this.getMenuElement(6).getPosY() + this.getMenuElement(6).getHeight() - this.getMenuElement(0).getPosY() + CFG.PADDING * 2 - 2
            );
            oSB.setColor(Color.WHITE);
         }
      } catch (NullPointerException var6) {
      }

      CFG.drawRect_InfoBox_Right(
         oSB,
         this.getPosX() + this.getMenuElement(7).getPosX() - CFG.PADDING + iTranslateX,
         this.getMenuPosY() + this.getMenuElement(7).getPosY() - CFG.PADDING + iTranslateY,
         this.getMenuElement(8).getPosX() - this.getMenuElement(7).getPosX() + this.getMenuElement(8).getWidth() + CFG.PADDING * 2,
         this.getMenuElement(9).getPosY() + this.getMenuElement(9).getHeight() - this.getMenuElement(7).getPosY() + CFG.PADDING * 2
      );
      CFG.drawRect_InfoBox_Right(
         oSB,
         this.getPosX() + CFG.PADDING + 2 + iTranslateX,
         this.getMenuPosY() + this.getMenuElement(12).getPosY() - CFG.PADDING + iTranslateY,
         this.getWidth() - CFG.PADDING * 2 - 2,
         this.getMenuElement(12).getHeight() + CFG.PADDING * 2
      );
      this.drawMenu(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
      this.endClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
   }

   @Override
   public void onHovered() {
      CFG.menuManager.setOrderOfMenu_CreateNewGame_CivInfo();
   }

   @Override
   public void actionElement(int iID) {
      switch (iID) {
         case 0:
         case 1:
         default:
            break;
         case 2:
         case 3:
            CFG.map.getMapCoordinates().centerToProvinceID(CFG.game.getCiv(CFG.getActiveCivInfo()).getCapitalProvinceID());
            CFG.game.setActiveProvinceID(CFG.game.getCiv(CFG.getActiveCivInfo()).getCapitalProvinceID());
            break;
         case 4:
         case 5:
            int nLargestCity = CFG.game.getCiv(CFG.getActiveCivInfo()).getProvinceID(0);

            for (int i = 1; i < CFG.game.getCiv(CFG.getActiveCivInfo()).getNumOfProvinces(); i++) {
               if (CFG.game.getProvince(nLargestCity).getPopulationData().getPopulation()
                  < CFG.game.getProvince(CFG.game.getCiv(CFG.getActiveCivInfo()).getProvinceID(i)).getPopulationData().getPopulation()) {
                  nLargestCity = CFG.game.getCiv(CFG.getActiveCivInfo()).getProvinceID(i);
               }
            }

            CFG.map.getMapCoordinates().centerToProvinceID(nLargestCity);
            CFG.game.setActiveProvinceID(nLargestCity);
            break;
         case 6:
            if (CFG.activeCivLeader != null) {
               this.getMenuElement(iID).setVisible(false);
               this.getMenuElement(iID).menuElementHover = null;
            }
      }
   }
}
