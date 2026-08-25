package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

public class Menu_CreateScenario_Cores extends Menu_CreateScenario {
   public String assignProvinces;
   public int iStepWidth;
   public String assignProvinces2;
   public int iStepWidth2;

   public Menu_CreateScenario_Cores() {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      menuElements.add(new Button_Game(null, -1, CFG.PADDING, CFG.PADDING, true));
      menuElements.add(new Button_Game(null, -1, CFG.GAME_WIDTH - CFG.BUTTON_WIDTH - CFG.PADDING, CFG.PADDING, true));
      menuElements.add(new Button_Game_Checkbox(null, -1, CFG.PADDING, CFG.BUTTON_HEIGHT + CFG.PADDING * 3, CFG.BUTTON_WIDTH * 2, true, false) {
         @Override
         public boolean getCheckboxState() {
            return CFG.brushTool;
         }
      });
      menuElements.add(
         new Button_Game_Checkbox(
            null, -1, CFG.BUTTON_WIDTH * 2 + CFG.PADDING * 2, CFG.BUTTON_HEIGHT + CFG.PADDING * 2 + CFG.PADDING, CFG.BUTTON_WIDTH, true, true
         ) {
            @Override
            public boolean getCheckboxState() {
               return CFG.selectMode;
            }
         }
      );
      menuElements.add(
         new Button_Game(null, -1, CFG.BUTTON_WIDTH * 2 + CFG.PADDING * 2, CFG.BUTTON_HEIGHT + CFG.PADDING * 2 + CFG.PADDING, CFG.BUTTON_WIDTH, false) {
            @Override
            public boolean getClickable() {
               return CFG.game.getSelectedProvinces().getProvincesSize() > 0;
            }
         }
      );
      menuElements.add(
         new Button_Game(null, -1, CFG.BUTTON_WIDTH * 3 + CFG.PADDING * 3, CFG.BUTTON_HEIGHT + CFG.PADDING * 2 + CFG.PADDING, CFG.BUTTON_WIDTH, false) {
            @Override
            public boolean getClickable() {
               return CFG.game.getSelectedProvinces().getProvincesSize() > 0;
            }
         }
      );
      this.initMenu(null, 0, 0, CFG.GAME_WIDTH, CFG.GAME_HEIGHT, menuElements);
      this.updateLanguage();
   }

   @Override
   public void updateLanguage() {
      super.updateLanguage();
      this.getMenuElement(2).setText(CFG.langManager.get("Brush"));
      this.getMenuElement(3).setText(CFG.langManager.get("Select"));
      this.getMenuElement(4).setText(CFG.langManager.get("DeselectAll"));
      this.getMenuElement(5).setText(CFG.langManager.get("Undo"));
      int extraX = this.updateButtonWidth(2, CFG.PADDING, CFG.BUTTON_WIDTH * 2) + CFG.PADDING;

      for (int i = 3; i < 6; i++) {
         extraX += this.updateButtonWidth(i, extraX + CFG.PADDING, CFG.BUTTON_WIDTH) + CFG.PADDING;
      }

      int tempX = CFG.GAME_WIDTH - this.getMenuElement(3).getWidth() - CFG.PADDING;
      this.getMenuElement(3).setPosX(tempX);
      tempX = tempX - this.getMenuElement(2).getWidth() - CFG.PADDING;
      this.getMenuElement(2).setPosX(tempX);
      tempX = tempX - this.getMenuElement(4).getWidth() - CFG.PADDING;
      this.getMenuElement(4).setPosX(tempX);
      tempX = tempX - this.getMenuElement(5).getWidth() - CFG.PADDING;
      this.getMenuElement(5).setPosX(tempX);
      this.assignProvinces = CFG.langManager.get("SetUpCores");
      CFG.glyphLayout.setText(CFG.fontMain, this.assignProvinces);
      this.iStepWidth = (int)CFG.glyphLayout.width;
      this.assignProvinces2 = CFG.langManager.get("ClickAprovinceOnTheMapToAddOrRemoveCore") + ".";
      CFG.glyphLayout.setText(CFG.fontMain, this.assignProvinces2);
      this.iStepWidth2 = (int)CFG.glyphLayout.width;
   }

   @Override
   public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      CFG.drawEditorTitle_Edge_R(oSB, iTranslateX, this.getMenuPosY() + iTranslateY, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT + CFG.PADDING * 2);
      CFG.drawEditorButtons_Top_Edge_R_Reflected(
         oSB,
         this.getMenuElement(5).getPosX() - CFG.PADDING + iTranslateX,
         CFG.BUTTON_HEIGHT + CFG.PADDING * 2 + this.getMenuPosY() + iTranslateY,
         CFG.GAME_WIDTH - (this.getMenuElement(5).getPosX() - CFG.PADDING),
         CFG.BUTTON_HEIGHT + CFG.PADDING * 2
      );
      CFG.drawTextWithShadow(
         oSB,
         this.assignProvinces,
         CFG.GAME_WIDTH / 2 - (this.iStepWidth + CFG.CIV_FLAG_WIDTH + CFG.PADDING) / 2 + CFG.PADDING + CFG.CIV_FLAG_WIDTH + iTranslateX,
         CFG.PADDING + CFG.BUTTON_HEIGHT / 2 - CFG.TEXT_HEIGHT - CFG.PADDING / 2 + this.getMenuPosY() + iTranslateY,
         new Color(CFG.COLOR_TEXT_CNG_TOP_SCENARIO_NAME.r, CFG.COLOR_TEXT_CNG_TOP_SCENARIO_NAME.g, CFG.COLOR_TEXT_CNG_TOP_SCENARIO_NAME.b, 0.95F)
      );
      CFG.fontMain.getData().setScale(0.8F);
      CFG.drawTextWithShadow(
         oSB,
         this.assignProvinces2,
         CFG.GAME_WIDTH / 2 - (int)(this.iStepWidth2 * 0.8F / 2.0F) + iTranslateX,
         CFG.PADDING + CFG.BUTTON_HEIGHT / 2 + CFG.PADDING + this.getMenuPosY() + iTranslateY,
         new Color(CFG.COLOR_TEXT_CNG_TOP_SCENARIO_INFO.r, CFG.COLOR_TEXT_CNG_TOP_SCENARIO_INFO.g, CFG.COLOR_TEXT_CNG_TOP_SCENARIO_INFO.b, 0.75F)
      );
      CFG.fontMain.getData().setScale(1.0F);

      try {
         CFG.game
            .getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID())
            .getFlag()
            .draw(
               oSB,
               CFG.GAME_WIDTH / 2 - (this.iStepWidth + CFG.CIV_FLAG_WIDTH + CFG.PADDING) / 2 + iTranslateX,
               CFG.PADDING
                  + CFG.BUTTON_HEIGHT / 2
                  - CFG.CIV_FLAG_HEIGHT
                  - CFG.PADDING / 2
                  + this.getMenuPosY()
                  - CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).getFlag().getHeight()
                  + iTranslateY,
               CFG.CIV_FLAG_WIDTH,
               CFG.CIV_FLAG_HEIGHT
            );
         ImageManager.getImage(Images.flag_rect)
            .draw(
               oSB,
               CFG.GAME_WIDTH / 2 - (this.iStepWidth + CFG.CIV_FLAG_WIDTH + CFG.PADDING) / 2 + iTranslateX,
               CFG.PADDING + CFG.BUTTON_HEIGHT / 2 - CFG.CIV_FLAG_HEIGHT - CFG.PADDING / 2 + this.getMenuPosY() + iTranslateY
            );
      } catch (IndexOutOfBoundsException var6) {
         ImageManager.getImage(Images.randomCivilizationFlag)
            .draw(
               oSB,
               CFG.GAME_WIDTH / 2 - (this.iStepWidth + CFG.CIV_FLAG_WIDTH + CFG.PADDING) / 2 + iTranslateX,
               CFG.PADDING
                  + CFG.BUTTON_HEIGHT / 2
                  - CFG.CIV_FLAG_HEIGHT
                  - CFG.PADDING / 2
                  + this.getMenuPosY()
                  - ImageManager.getImage(Images.randomCivilizationFlag).getHeight()
                  + iTranslateY,
               CFG.CIV_FLAG_WIDTH,
               CFG.CIV_FLAG_HEIGHT
            );
         ImageManager.getImage(Images.flag_rect)
            .draw(
               oSB,
               CFG.GAME_WIDTH / 2 - (this.iStepWidth + CFG.CIV_FLAG_WIDTH + CFG.PADDING) / 2 + iTranslateX,
               CFG.PADDING + CFG.BUTTON_HEIGHT / 2 - CFG.CIV_FLAG_HEIGHT - CFG.PADDING / 2 + this.getMenuPosY() + iTranslateY
            );
      }

      super.draw(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
   }

   @Override
   public final void actionElement(int iID) {
      switch (iID) {
         case 0:
         case 1:
            this.onBackPressed();
            return;
         case 2:
            CFG.brushTool = !CFG.brushTool;
            this.getMenuElement(iID).setCheckboxState(CFG.brushTool);
            if (CFG.brushTool && CFG.game.getSelectedProvinces().getProvincesSize() < 2) {
               CFG.game.getSelectedProvinces().clearSelectedProvinces();
            }

            return;
         case 3:
            CFG.selectMode = !CFG.selectMode;
            return;
         case 4:
            CFG.game.getSelectedProvinces().clearSelectedProvinces();
            CFG.selectMode = true;
            CFG.menuManager.rebuildCreateScenario_Cores_SetUp();
            return;
         case 5:
            CFG.game.getSelectedProvinces().popProvince();
            if (CFG.game.getSelectedProvinces().getProvincesSize() == 0) {
               CFG.selectMode = true;
            }

            CFG.menuManager.rebuildCreateScenario_Cores_SetUp();
            return;
         default:
            super.actionElement(iID);
      }
   }

   @Override
   public void onBackPressed() {
      CFG.menuManager.setViewID(Menu.eCREATE_SCENARIO_SETTINGS);
      CFG.game.setActiveProvinceID(-1);
      CFG.game.getSelectedProvinces().clearSelectedProvinces();
   }
}
