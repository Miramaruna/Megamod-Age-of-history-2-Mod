package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

public class Menu_MapEditor_ArmySeaBoxes_Add extends SliderMenu {
   public static Point_XY oFirstPoint = null;
   public static Point_XY oSecondPoint = null;

   public Menu_MapEditor_ArmySeaBoxes_Add() {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      menuElements.add(new Button_Game(null, -1, CFG.PADDING, CFG.GAME_HEIGHT - CFG.PADDING - CFG.BUTTON_HEIGHT, CFG.BUTTON_WIDTH * 2));
      menuElements.add(
         new Button_Game(null, -1, CFG.GAME_WIDTH - CFG.BUTTON_WIDTH * 2 - CFG.PADDING, CFG.GAME_HEIGHT - CFG.PADDING - CFG.BUTTON_HEIGHT, CFG.BUTTON_WIDTH * 2) {
            @Override
            public final Color getColor(boolean isActive) {
               return isActive
                  ? new Color(0.75F, 0.8F, 0.03F, 1.0F)
                  : (this.getClickable() ? new Color(0.941F, 1.0F, 0.0F, 1.0F) : new Color(0.674F, 0.09F, 0.066F, 0.5F));
            }
         }
      );
      menuElements.add(new Button_Game(null, -1, CFG.PADDING, CFG.PADDING, CFG.GAME_WIDTH / 2 - CFG.PADDING - CFG.PADDING / 2));
      menuElements.add(new Button_Game(null, -1, CFG.GAME_WIDTH / 2 + CFG.PADDING / 2, CFG.PADDING, CFG.GAME_WIDTH / 2 - CFG.PADDING - CFG.PADDING / 2));
      this.initMenu(null, 0, 0, CFG.GAME_WIDTH, CFG.GAME_HEIGHT, menuElements);
      this.updateLanguage();
   }

   @Override
   public void updateLanguage() {
      this.getMenuElement(0).setText(CFG.langManager.get("Back"));
      this.getMenuElement(1).setText(CFG.langManager.get("Save"));
      this.getMenuElement(2).setText(CFG.langManager.get("Reset") + " [1]");
      this.getMenuElement(3).setText(CFG.langManager.get("Reset") + " [2]");
   }

   @Override
   public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      super.beginClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
      CFG.drawEditorButtons_Bot_Edge_R(
         oSB, iTranslateX, this.getMenuElement(0).getPosY() - CFG.PADDING + iTranslateY, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT + CFG.PADDING * 2
      );
      CFG.drawEditorTitle_Edge_LR(
         oSB, iTranslateX, this.getMenuElement(2).getPosY() - CFG.PADDING + iTranslateY, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT + CFG.PADDING * 2
      );
      super.drawMenu(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
      super.endClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
   }

   @Override
   public final void actionElement(int iID) {
      switch (iID) {
         case 0:
            this.onBackPressed();
            return;
         case 1:
            if (oFirstPoint.getPosY() >= 0 && oSecondPoint.getPosY() >= 0) {
               if (oFirstPoint.getPosX() > oSecondPoint.getPosX()) {
                  int tempPoint = oFirstPoint.getPosX();
                  oFirstPoint.setPosX(oSecondPoint.getPosX());
                  oSecondPoint.setPosX(tempPoint);
               }

               if (oFirstPoint.getPosY() > oSecondPoint.getPosY()) {
                  int tempPoint = oFirstPoint.getPosY();
                  oFirstPoint.setPosY(oSecondPoint.getPosY());
                  oSecondPoint.setPosY(tempPoint);
               }

               if (CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV2 >= 0) {
                  CFG.game
                     .getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1)
                     .getProvinceArmyBoxes()
                     .set(
                        CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV2,
                        new Province_ArmyBox(oFirstPoint.getPosX(), oFirstPoint.getPosY(), oSecondPoint.getPosX(), oSecondPoint.getPosY())
                     );
               } else {
                  if (CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes() == null) {
                     CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).initProvinceArmyBoxes();
                  }

                  ArrayList<Province_ArmyBox> nSet = new ArrayList<>();
                  nSet.add(new Province_ArmyBox(oFirstPoint.getPosX(), oFirstPoint.getPosY(), oSecondPoint.getPosX(), oSecondPoint.getPosY()));

                  for (int i = 0; i < CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().size(); i++) {
                     nSet.add(CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().get(i));
                  }

                  CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).setProvinceArmyBoxes(nSet);
               }

               this.onBackPressed();
            } else {
               CFG.toast.setInView("UPDATE POINTS!");
            }

            return;
         case 2:
            oFirstPoint.setPosX(-1);
            oFirstPoint.setPosY(-1);
            return;
         case 3:
            oSecondPoint.setPosX(-1);
            oSecondPoint.setPosY(-1);
            return;
      }
   }

   @Override
   public void onBackPressed() {
      CFG.menuManager.setViewID(Menu.eMAP_EDITOR_ARMY_SEA_BOXES_EDIT);
      CFG.menuManager.setBackAnimation(true);
      CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).updateDrawArmy();
   }
}
