package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.GdxRuntimeException;
import java.io.IOException;
import java.util.ArrayList;

public class Menu_PalletOfCivilizationsColors extends SliderMenu {
   public Menu_PalletOfCivilizationsColors() {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      menuElements.add(new Button_Game(null, -1, CFG.PADDING, CFG.PADDING, true));
      menuElements.add(
         new Button_Menu(
            "", -1, CFG.BUTTON_WIDTH + CFG.PADDING * 2, 0, CFG.GAME_WIDTH - (CFG.BUTTON_WIDTH + CFG.PADDING * 2) * 2, CFG.BUTTON_HEIGHT + CFG.PADDING * 2, true
         ) {
            @Override
            public Color getColor(boolean isActive) {
               return isActive
                  ? new Color(0.82F, 0.82F, 0.82F, 1.0F)
                  : (this.getClickable() ? new Color(1.0F, 1.0F, 1.0F, 1.0F) : new Color(0.84F, 0.84F, 0.84F, 0.7F));
            }

            @Override
            public void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
            }
         }
      );
      menuElements.add(new Button_Game(null, -1, CFG.GAME_WIDTH - CFG.BUTTON_WIDTH - CFG.PADDING, CFG.PADDING, true));
      menuElements.add(new Button_Game_ColorPicker(CFG.PADDING, CFG.BUTTON_HEIGHT + CFG.PADDING * 3, CFG.BUTTON_WIDTH * 2, true) {
         @Override
         public void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
            if (CFG.game.getActiveProvinceID() < 0) {
               oSB.setColor(new Color(1.0F, 1.0F, 1.0F, 0.5F));
            }

            super.drawButtonBG(oSB, iTranslateX, iTranslateY, isActive);
            oSB.setColor(Color.WHITE);
         }
      });
      menuElements.add(
         new Button_Game_Checkbox(null, -1, CFG.PADDING * 2 + CFG.BUTTON_WIDTH * 2, CFG.BUTTON_HEIGHT + CFG.PADDING * 3, CFG.BUTTON_WIDTH * 2, true, true)
      );
      CFG.menuManager.getColorPicker().setPosX(CFG.PADDING * 3);
      CFG.menuManager.getColorPicker().setPosY(CFG.BUTTON_HEIGHT * 2 + CFG.PADDING * 7);
      this.initMenu(null, 0, 0, CFG.GAME_WIDTH, CFG.GAME_HEIGHT, menuElements);
      this.updateLanguage();
   }

   @Override
   public void updateLanguage() {
      this.getMenuElement(0).setText(CFG.langManager.get("Back"));
      this.getMenuElement(1)
         .setText(CFG.langManager.get("Scenario") + ": " + CFG.langManager.get(CFG.game.getGameScenarios().getScenarioName(CFG.game.getScenarioID())));
      this.getMenuElement(2).setText(CFG.langManager.get("Save"));
      this.getMenuElement(4).setText(CFG.langManager.get("Colors"));
   }

   @Override
   public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      CFG.drawEditorTitle_Edge_R(oSB, iTranslateX, iTranslateY, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT + CFG.PADDING * 2);
      CFG.drawEditorButtons_Top_Edge_R(
         oSB,
         this.getMenuElement(3).getPosX() - CFG.PADDING + iTranslateX + iTranslateY,
         this.getMenuElement(3).getPosY() - CFG.PADDING,
         this.getMenuElement(4).getPosX() + this.getMenuElement(4).getWidth() + CFG.PADDING,
         this.getMenuElement(3).getHeight() + CFG.PADDING * 2
      );
      if (CFG.game.getActiveProvinceID() >= 0) {
         CFG.game
            .getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID())
            .getFlag()
            .draw(
               oSB,
               this.getMenuElement(1).getPosX()
                  + this.getMenuElement(1).getWidth() / 2
                  - this.getMenuElement(1).getTextWidth() / 2
                  - CFG.PADDING
                  - CFG.CIV_FLAG_WIDTH
                  + iTranslateX,
               this.getMenuPosY()
                  + this.getMenuElement(1).getPosY()
                  + this.getMenuElement(1).getHeight() / 2
                  - CFG.CIV_FLAG_HEIGHT / 2
                  - CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).getFlag().getHeight()
                  + iTranslateY,
               CFG.CIV_FLAG_WIDTH,
               CFG.CIV_FLAG_HEIGHT
            );
         ImageManager.getImage(Images.flag_rect)
            .draw(
               oSB,
               this.getMenuElement(1).getPosX()
                  + this.getMenuElement(1).getWidth() / 2
                  - this.getMenuElement(1).getTextWidth() / 2
                  - CFG.PADDING
                  - CFG.CIV_FLAG_WIDTH
                  + iTranslateX,
               this.getMenuPosY() + this.getMenuElement(1).getPosY() + this.getMenuElement(1).getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 + iTranslateY
            );
      }

      super.draw(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
   }

   @Override
   public final void actionElement(int iID) {
      switch (iID) {
         case 0:
            this.onBackPressed();
            return;
         case 1:
            CFG.menuManager.getColorPicker().setVisible(false, null);
            CFG.menuManager.setViewID(Menu.eCHOOSE_SCENARIO);
            CFG.backToMenu = Menu.eGAME_EDITOR_PALLETS_OF_CIVS_COLORS_PACKAGES_EDIT;
            CFG.goToMenu = Menu.eGAME_EDITOR_PALLETS_OF_CIVS_COLORS_PACKAGES_EDIT;
            return;
         case 2:
            CFG.menuManager.getColorPicker().setVisible(false, null);
            CFG.editorPalletOfCivsColors_Data.saveData();
            CFG.palletManager.updatePalletsOfCivsColorsTags();
            this.onBackPressed();
            return;
         case 3:
            if (!CFG.menuManager.getColorPicker().getVisible() && CFG.game.getActiveProvinceID() >= 0) {
               CFG.menuManager.getColorPicker().setVisible(true, ColorPicker_AoC.PickerAction.PALLET_OF_COLORS);
            } else {
               CFG.menuManager.getColorPicker().setVisible(false, null);
            }

            return;
         case 4:
            if (this.getMenuElement(iID).getCheckboxState()) {
               CFG.editorPalletOfCivsColors_Data.saveData();

               for (int i = 1; i < CFG.game.getCivsSize(); i++) {
                  FileHandle file = null;

                  try {
                     file = Gdx.files.internal("game/pallets_of_civs_colors/" + CFG.EDITOR_ACTIVE_GAMEDATA_TAG + "/" + CFG.game.getCiv(i).getCivTag());

                     try {
                        PalletOfCivsColors_Civ_GameData nCivColor = (PalletOfCivsColors_Civ_GameData)CFG.deserialize(file.readBytes());
                        CFG.game.getCiv(i).setR((int)(nCivColor.getColor().getR() * 255.0F));
                        CFG.game.getCiv(i).setG((int)(nCivColor.getColor().getG() * 255.0F));
                        CFG.game.getCiv(i).setB((int)(nCivColor.getColor().getB() * 255.0F));
                     } catch (ClassNotFoundException var8) {
                        CFG.game.getCiv(i).setR(0);
                        CFG.game.getCiv(i).setG(1);
                        CFG.game.getCiv(i).setB(2);
                     } catch (IOException var9) {
                        CFG.game.getCiv(i).setR(0);
                        CFG.game.getCiv(i).setG(1);
                        CFG.game.getCiv(i).setB(2);
                     }
                  } catch (GdxRuntimeException var10) {
                     CFG.game.getCiv(i).setR(0);
                     CFG.game.getCiv(i).setG(1);
                     CFG.game.getCiv(i).setB(2);
                  }
               }

               this.getMenuElement(iID).setCheckboxState(!this.getMenuElement(iID).getCheckboxState());
            } else {
               CFG.editorPalletOfCivsColors_Data.saveData();

               for (int i = 1; i < CFG.game.getCivsSize(); i++) {
                  FileHandle file = null;

                  try {
                     file = Gdx.files.internal("game/pallets_of_civs_colors/" + CFG.EDITOR_ACTIVE_GAMEDATA_TAG + "/" + CFG.game.getCiv(i).getCivTag());

                     try {
                        PalletOfCivsColors_Civ_GameData nCivColor = (PalletOfCivsColors_Civ_GameData)CFG.deserialize(file.readBytes());
                        CFG.game.getCiv(i).setR((int)(nCivColor.getColor().getR() * 255.0F));
                        CFG.game.getCiv(i).setG((int)(nCivColor.getColor().getG() * 255.0F));
                        CFG.game.getCiv(i).setB((int)(nCivColor.getColor().getB() * 255.0F));
                     } catch (ClassNotFoundException var5) {
                        CFG.palletManager.loadCivilizationStandardColor(i);
                     } catch (IOException var6) {
                        CFG.palletManager.loadCivilizationStandardColor(i);
                     }
                  } catch (GdxRuntimeException var7) {
                     CFG.palletManager.loadCivilizationStandardColor(i);
                  }
               }

               this.getMenuElement(iID).setCheckboxState(!this.getMenuElement(iID).getCheckboxState());
            }
      }
   }

   @Override
   public void onBackPressed() {
      CFG.menuManager.setViewID(Menu.eGAME_EDITOR_PALLETS_OF_CIVS_COLORS_PACKAGES);
      CFG.menuManager.setBackAnimation(true);
      CFG.menuManager.getColorPicker().setVisible(false, null);
   }
}
