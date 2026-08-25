package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;
import java.util.List;

public class Menu_CreateScenario_TechnologyLevels_Civs extends SliderMenu {
   public List<Integer> lCivs;

   public Menu_CreateScenario_TechnologyLevels_Civs() {
      int tempW = CFG.CIV_INFO_MENU_WIDTH;
      int tempElemH = CFG.BUTTON_HEIGHT * 3 / 4;
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      this.lCivs = new ArrayList<>();
      ArrayList<Integer> tempCivs = new ArrayList<>();

      for (int i2 = 1; i2 < CFG.game.getCivsSize(); i2++) {
         tempCivs.add(i2);
      }

      while (tempCivs.size() > 0) {
         int tBest = 0;

         for (int i = 1; i < tempCivs.size(); i++) {
            if (CFG.game.getCiv(tempCivs.get(i)).getTechnologyLevel() > CFG.game.getCiv(tempCivs.get(tBest)).getTechnologyLevel()) {
               tBest = i;
            }
         }

         this.lCivs.add(tempCivs.get(tBest));
         tempCivs.remove(tBest);
      }

      int tY = CFG.PADDING;

      for (int ix = 0; ix < this.lCivs.size(); ix++) {
         menuElements.add(
            new Text(CFG.game.getCiv(this.lCivs.get(ix)).getCivName(), -1, 0, tY, tempW, CFG.TEXT_HEIGHT + CFG.PADDING * 2) {
               @Override
               public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
                  CFG.drawRect_InfoBox_Right_Title(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight());
                  CFG.fontMain.getData().setScale(0.6F);
                  CFG.drawTextWithShadow(
                     oSB,
                     this.getText(),
                     this.getPosX() + this.getWidth() / 2 - (int)(this.getTextWidth() * 0.6F) / 2 + iTranslateX,
                     this.getPosY() + this.getHeight() / 2 - (int)(this.getTextHeight() * 0.6F) / 2 + iTranslateY,
                     CFG.COLOR_TEXT_CIV_INFO_TITLE
                  );
                  CFG.fontMain.getData().setScale(1.0F);
               }
            }
         );
         int var10;
         menuElements.add(
            new Slider_FlagAction_Clear(
               CFG.game.getCiv(this.lCivs.get(ix)).getCivName(),
               CFG.PADDING * 2,
               var10 = tY + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING,
               tempW - CFG.PADDING * 4,
               tempElemH - CFG.PADDING * 2,
               5,
               200,
               (int)(CFG.game.getCiv(this.lCivs.get(ix)).getTechnologyLevel() * 100.0F)
            ) {
               @Override
               public String getDrawText() {
                  return "" + this.getCurrent() / 100.0F;
               }
            }
         );
         tY = var10 + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
      }

      this.initMenu(
         new SliderMenuTitle(null, CFG.BUTTON_HEIGHT * 3 / 4, false, false) {
            @Override
            public void draw(SpriteBatch oSB, int iTranslateX, int nPosX, int nPosY, int nWidth, boolean sliderMenuIsActive) {
               ImageManager.getImage(Images.new_game_top_edge_title)
                  .draw2(
                     oSB,
                     Menu_CreateScenario_TechnologyLevels_Civs.this.getPosX() - 2 + iTranslateX,
                     Menu_CreateScenario_TechnologyLevels_Civs.this.getPosY()
                        - ImageManager.getImage(Images.new_game_top_edge_title).getHeight()
                        - this.getHeight(),
                     Menu_CreateScenario_TechnologyLevels_Civs.this.getWidth() + 2,
                     this.getHeight(),
                     false,
                     false
                  );
               oSB.setColor(new Color(0.011F, 0.014F, 0.019F, 0.25F));
               ImageManager.getImage(Images.gradient)
                  .draw(
                     oSB,
                     Menu_CreateScenario_TechnologyLevels_Civs.this.getPosX() + iTranslateX,
                     Menu_CreateScenario_TechnologyLevels_Civs.this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() - this.getHeight() * 3 / 4,
                     Menu_CreateScenario_TechnologyLevels_Civs.this.getWidth(),
                     this.getHeight() * 3 / 4,
                     false,
                     true
                  );
               oSB.setColor(new Color(0.451F, 0.329F, 0.11F, 1.0F));
               ImageManager.getImage(Images.pix255_255_255)
                  .draw(
                     oSB,
                     Menu_CreateScenario_TechnologyLevels_Civs.this.getPosX() + iTranslateX,
                     Menu_CreateScenario_TechnologyLevels_Civs.this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight(),
                     Menu_CreateScenario_TechnologyLevels_Civs.this.getWidth()
                  );
               oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.4F));
               ImageManager.getImage(Images.line_32_off1)
                  .draw(
                     oSB,
                     Menu_CreateScenario_TechnologyLevels_Civs.this.getPosX() + iTranslateX,
                     Menu_CreateScenario_TechnologyLevels_Civs.this.getPosY()
                        - ImageManager.getImage(Images.pix255_255_255).getHeight()
                        - ImageManager.getImage(Images.line_32_off1).getHeight(),
                     Menu_CreateScenario_TechnologyLevels_Civs.this.getWidth(),
                     1
                  );
               oSB.setColor(Color.WHITE);
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
      this.getTitle().setText(CFG.langManager.get("TechnologyLevel"));
   }

   @Override
   public void drawScrollPos(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      if (sliderMenuIsActive) {
         super.drawScrollPos(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
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
      if (iID % 2 == 1) {
         CFG.game.getCiv(this.lCivs.get(iID / 2)).setTechnologyLevel(this.getMenuElement(iID).getCurrent() / 100.0F);
      } else if (CFG.game.getCiv(this.lCivs.get(iID / 2)).getCapitalProvinceID() >= 0) {
         CFG.map.getMapCoordinates().centerToProvinceID(CFG.game.getCiv(this.lCivs.get(iID / 2)).getCapitalProvinceID());
      }
   }
}
