package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;
import java.util.List;

public class Menu_CreateScenario_TechnologyLevels_Continents extends SliderMenu {
   public List<Integer> lContinents = this.getContinentsOfCiv();

   public Menu_CreateScenario_TechnologyLevels_Continents() {
      int tempW = CFG.CIV_INFO_MENU_WIDTH;
      int tempElemH = CFG.BUTTON_HEIGHT * 3 / 4;
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      if (CFG.iCreateScenario_AssignProvinces_Civ > 0) {
         this.lContinents = this.getContinentsOfCiv();

         for (int i = 0; i < this.lContinents.size(); i++) {
            menuElements.add(
               new Slider_FlagAction_Clear(
                  CFG.map.getMapRegions().getName(this.lContinents.get(i)),
                  CFG.PADDING * 2,
                  CFG.PADDING + tempElemH * i,
                  tempW - CFG.PADDING * 4,
                  tempElemH - CFG.PADDING * 2,
                  5,
                  150,
                  CFG.getCreateScenario_TechnologyLevelsByContinents_Continent(CFG.iCreateScenario_AssignProvinces_Civ - 1, this.lContinents.get(i))
               ) {
                  @Override
                  public String getDrawText() {
                     return "" + (int)(CFG.game.getCiv(CFG.iCreateScenario_AssignProvinces_Civ).getTechnologyLevel() * this.getCurrent()) / 100.0F;
                  }

                  @Override
                  public void buildElementHover() {
                     try {
                        ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
                        ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
                        nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.iCreateScenario_AssignProvinces_Civ));
                        nData.add(
                           new MenuElement_Hover_v2_Element_Type_Text(
                              CFG.langManager.get("DevelopmentLevelIn", this.getText()) + ".", CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                           )
                        );
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                        this.menuElementHover = new MenuElement_Hover_v2(nElements);
                     } catch (IndexOutOfBoundsException var3) {
                        this.menuElementHover = null;
                     }
                  }
               }
            );
         }
      }

      this.initMenu(
         new SliderMenuTitle(null, CFG.BUTTON_HEIGHT * 3 / 4, false, false) {
            @Override
            public void draw(SpriteBatch oSB, int iTranslateX, int nPosX, int nPosY, int nWidth, boolean sliderMenuIsActive) {
               ImageManager.getImage(Images.new_game_top_edge_title)
                  .draw2(
                     oSB,
                     Menu_CreateScenario_TechnologyLevels_Continents.this.getPosX() - 2 + iTranslateX,
                     Menu_CreateScenario_TechnologyLevels_Continents.this.getPosY()
                        - ImageManager.getImage(Images.new_game_top_edge_title).getHeight()
                        - this.getHeight(),
                     Menu_CreateScenario_TechnologyLevels_Continents.this.getWidth() + 2,
                     this.getHeight(),
                     false,
                     false
                  );
               oSB.setColor(new Color(0.011F, 0.014F, 0.019F, 0.25F));
               ImageManager.getImage(Images.gradient)
                  .draw(
                     oSB,
                     Menu_CreateScenario_TechnologyLevels_Continents.this.getPosX() + iTranslateX,
                     Menu_CreateScenario_TechnologyLevels_Continents.this.getPosY()
                        - ImageManager.getImage(Images.gradient).getHeight()
                        - this.getHeight() * 3 / 4,
                     Menu_CreateScenario_TechnologyLevels_Continents.this.getWidth(),
                     this.getHeight() * 3 / 4,
                     false,
                     true
                  );
               oSB.setColor(new Color(0.451F, 0.329F, 0.11F, 1.0F));
               ImageManager.getImage(Images.pix255_255_255)
                  .draw(
                     oSB,
                     Menu_CreateScenario_TechnologyLevels_Continents.this.getPosX() + iTranslateX,
                     Menu_CreateScenario_TechnologyLevels_Continents.this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight(),
                     Menu_CreateScenario_TechnologyLevels_Continents.this.getWidth()
                  );
               oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.4F));
               ImageManager.getImage(Images.line_32_off1)
                  .draw(
                     oSB,
                     Menu_CreateScenario_TechnologyLevels_Continents.this.getPosX() + iTranslateX,
                     Menu_CreateScenario_TechnologyLevels_Continents.this.getPosY()
                        - ImageManager.getImage(Images.pix255_255_255).getHeight()
                        - ImageManager.getImage(Images.line_32_off1).getHeight(),
                     Menu_CreateScenario_TechnologyLevels_Continents.this.getWidth(),
                     1
                  );
               oSB.setColor(Color.WHITE);
               CFG.game
                  .getCiv(CFG.iCreateScenario_AssignProvinces_Civ)
                  .getFlag()
                  .draw(
                     oSB,
                     nPosX + CFG.PADDING * 2 + iTranslateX,
                     nPosY
                        - this.getHeight()
                        + this.getHeight() / 2
                        + 1
                        - CFG.CIV_FLAG_HEIGHT / 2
                        - CFG.game.getCiv(CFG.iCreateScenario_AssignProvinces_Civ).getFlag().getHeight(),
                     CFG.CIV_FLAG_WIDTH,
                     CFG.CIV_FLAG_HEIGHT
                  );
               ImageManager.getImage(Images.flag_rect)
                  .draw(oSB, nPosX + CFG.PADDING * 2 + iTranslateX, nPosY - this.getHeight() + this.getHeight() / 2 + 1 - CFG.CIV_FLAG_HEIGHT / 2);
               CFG.fontMain.getData().setScale(0.75F);
               CFG.drawText(
                  oSB,
                  this.getText(),
                  nPosX + nWidth / 2 - (int)(this.getTextWidth() * 0.75F / 2.0F) + iTranslateX,
                  nPosY - this.getHeight() + this.getHeight() / 2 + 1 - (int)(this.getTextHeight() * 0.75F / 2.0F),
                  CFG.COLOR_TEXT_MODIFIER_NEUTRAL
               );
               CFG.fontMain.getData().setScale(1.0F);
            }
         },
         CFG.GAME_WIDTH - tempW,
         CFG.BUTTON_HEIGHT * 2 + CFG.PADDING * 4 + CFG.PADDING * 2 + CFG.BUTTON_HEIGHT * 3 / 4,
         tempW,
         Math.min(
            tempElemH * menuElements.size(),
            CFG.GAME_HEIGHT - (CFG.BUTTON_HEIGHT * 2 + CFG.PADDING * 4 + CFG.PADDING * 2 + CFG.BUTTON_HEIGHT * 3 / 4) - CFG.BUTTON_HEIGHT - CFG.PADDING * 2
         ),
         menuElements
      );
      this.updateLanguage();
   }

   @Override
   public void updateLanguage() {
      this.getTitle().setText(CFG.langManager.get("Development"));
   }

   public final List<Integer> getContinentsOfCiv() {
      ArrayList<Integer> tempContinents = new ArrayList<>();

      for (int i = 0; i < CFG.game.getCiv(CFG.iCreateScenario_AssignProvinces_Civ).getNumOfProvinces(); i++) {
         boolean addN = true;

         for (int j = 0; j < tempContinents.size(); j++) {
            if (tempContinents.get(j) == CFG.game.getProvince(CFG.game.getCiv(CFG.iCreateScenario_AssignProvinces_Civ).getProvinceID(i)).getRegion()) {
               addN = false;
               break;
            }
         }

         if (addN) {
            tempContinents.add(CFG.game.getProvince(CFG.game.getCiv(CFG.iCreateScenario_AssignProvinces_Civ).getProvinceID(i)).getRegion());
         }
      }

      return tempContinents;
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
      CFG.setCreateScenario_TechnologyLevelsByContinents_Continent(
         CFG.iCreateScenario_AssignProvinces_Civ - 1, this.lContinents.get(iID), this.getMenuElement(iID).getCurrent()
      );
   }
}
