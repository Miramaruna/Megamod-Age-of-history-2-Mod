package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

public class Menu_Civilizations_Info_Difficulty extends SliderMenu {
   public Menu_Civilizations_Info_Difficulty() {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      menuElements.add(
         new Difficulty_Level(
            ImageManager.getImage(Images.difficulty_heaven).getWidth() + CFG.PADDING * 2,
            0,
            CFG.CIV_INFO_MENU_WIDTH
               - CFG.PADDING * 2
               - CFG.PADDING * 4
               - ImageManager.getImage(Images.difficulty_heaven).getWidth()
               - ImageManager.getImage(Images.difficulty_hell).getWidth(),
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
         }
      );
      this.initMenu(
         new SliderMenuTitle(null, CFG.TEXT_HEIGHT + CFG.PADDING * 2, false, false) {
            @Override
            public void draw(SpriteBatch oSB, int iTranslateX, int nPosX, int nPosY, int nWidth, boolean sliderMenuIsActive) {
               oSB.setColor(new Color(0.011F, 0.014F, 0.019F, 0.2F));
               ImageManager.getImage(Images.gradient)
                  .draw(
                     oSB,
                     Menu_Civilizations_Info_Difficulty.this.getPosX() - CFG.PADDING + 2 + iTranslateX,
                     Menu_Civilizations_Info_Difficulty.this.getPosY()
                        + 2
                        - ImageManager.getImage(Images.gradient).getHeight()
                        - (this.getHeight() + CFG.PADDING * 2),
                     Menu_Civilizations_Info_Difficulty.this.getWidth() + CFG.PADDING * 2 - 2,
                     (this.getHeight() + CFG.PADDING * 2 - 4) * 3 / 4,
                     false,
                     false
                  );
               oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.25F));
               ImageManager.getImage(Images.line_32_off1)
                  .draw(
                     oSB,
                     Menu_Civilizations_Info_Difficulty.this.getPosX() - CFG.PADDING + 2 + iTranslateX,
                     Menu_Civilizations_Info_Difficulty.this.getPosY()
                        - this.getHeight()
                        - ImageManager.getImage(Images.line_32_off1).getHeight()
                        + 1
                        - CFG.PADDING * 2,
                     Menu_Civilizations_Info_Difficulty.this.getWidth() + CFG.PADDING * 2 - 2,
                     ImageManager.getImage(Images.line_32_off1).getHeight(),
                     false,
                     true
                  );
               oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.4F));
               ImageManager.getImage(Images.line_32_off1)
                  .draw(
                     oSB,
                     Menu_Civilizations_Info_Difficulty.this.getPosX() - CFG.PADDING + 2 + iTranslateX,
                     Menu_Civilizations_Info_Difficulty.this.getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() - 2 - CFG.PADDING * 2,
                     Menu_Civilizations_Info_Difficulty.this.getWidth() + CFG.PADDING * 2 - 2,
                     ImageManager.getImage(Images.line_32_off1).getHeight(),
                     false,
                     true
                  );
               oSB.setColor(Color.WHITE);
               CFG.fontMain.getData().setScale(0.85F);
               CFG.drawTextWithShadow(
                  oSB,
                  this.getText(),
                  nPosX + nWidth / 2 - (int)(this.getTextWidth() * 0.85F) / 2 + iTranslateX,
                  nPosY - this.getHeight() + this.getHeight() / 2 - (int)(this.getTextHeight() * 0.85F) / 2 - CFG.PADDING * 2,
                  CFG.COLOR_TEXT_CIV_INFO_TITLE
               );
               CFG.fontMain.getData().setScale(1.0F);
            }
         },
         CFG.GAME_WIDTH - CFG.CIV_INFO_MENU_WIDTH + CFG.PADDING,
         ImageManager.getImage(Images.new_game_top).getHeight()
            + CFG.PADDING * 4
            + (int)(CFG.TEXT_HEIGHT * 0.6F)
            + ImageManager.getImage(Images.top_flag_frame).getHeight()
            + CFG.PADDING * 4
            + CFG.TEXT_HEIGHT
            + CFG.PADDING * 2
            + CFG.PADDING * 2,
         CFG.CIV_INFO_MENU_WIDTH - CFG.PADDING * 2,
         CFG.TEXT_HEIGHT * 2 + CFG.PADDING * 3 - CFG.PADDING * 4,
         menuElements,
         false,
         false
      );
      this.updateLanguage();
   }

   @Override
   public void updateLanguage() {
      this.getTitle().setText(CFG.langManager.get("Difficulty"));
   }

   @Override
   public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      if (Menu_Civilization_Info.lTime + 250L >= System.currentTimeMillis()) {
         iTranslateX += this.getWidth() - (int)(this.getWidth() * ((float)(System.currentTimeMillis() - Menu_Civilization_Info.lTime) / 250.0F));
      }

      ImageManager.getImage(Images.new_game_top_edge_line)
         .draw2(
            oSB,
            this.getPosX() - CFG.PADDING + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.new_game_top_edge_line).getHeight() - this.getTitle().getHeight() - CFG.PADDING * 2,
            this.getWidth() + CFG.PADDING * 2,
            this.getHeight() + this.getTitle().getHeight() + CFG.PADDING * 4
         );
      super.draw(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
      ImageManager.getImage(Images.difficulty_heaven)
         .draw(
            oSB,
            this.getPosX() + CFG.PADDING / 2 + iTranslateX,
            this.getPosY() + this.getHeight() / 2 - ImageManager.getImage(Images.difficulty_heaven).getHeight() / 2 + iTranslateY
         );
      ImageManager.getImage(Images.difficulty_hell)
         .draw(
            oSB,
            this.getPosX() - CFG.PADDING / 2 - ImageManager.getImage(Images.difficulty_hell).getWidth() + this.getWidth() + iTranslateX,
            this.getPosY() + this.getHeight() / 2 - ImageManager.getImage(Images.difficulty_hell).getHeight() / 2 + iTranslateY
         );
   }

   @Override
   public void actionElement(int iID) {
      switch (iID) {
      }
   }

   @Override
   public void drawScrollPos(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      super.drawScrollPos(oSB, iTranslateX - 2, iTranslateY, sliderMenuIsActive);
   }
}
