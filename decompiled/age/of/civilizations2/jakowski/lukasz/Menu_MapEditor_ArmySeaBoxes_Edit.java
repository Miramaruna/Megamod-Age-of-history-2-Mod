package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

public class Menu_MapEditor_ArmySeaBoxes_Edit extends SliderMenu {
   public Menu_MapEditor_ArmySeaBoxes_Edit() {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      menuElements.add(new Button_Game(null, -1, CFG.PADDING, CFG.GAME_HEIGHT - CFG.PADDING - CFG.BUTTON_HEIGHT, CFG.BUTTON_WIDTH * 2));
      menuElements.add(
         new Button_Game(null, -1, CFG.GAME_WIDTH - CFG.BUTTON_WIDTH * 2 - CFG.PADDING, CFG.GAME_HEIGHT - CFG.PADDING - CFG.BUTTON_HEIGHT, CFG.BUTTON_WIDTH * 2)
      );
      menuElements.add(
         new Text(
            null,
            -1,
            CFG.BUTTON_WIDTH * 2 + CFG.PADDING * 2,
            CFG.GAME_HEIGHT - CFG.PADDING - CFG.BUTTON_HEIGHT,
            CFG.GAME_WIDTH - (CFG.BUTTON_WIDTH * 2 + CFG.PADDING * 2) * 2,
            CFG.BUTTON_HEIGHT
         ) {
            @Override
            public Color getColor(boolean isActive) {
               return isActive
                  ? new Color(0.82F, 0.82F, 0.82F, 1.0F)
                  : (this.getClickable() ? new Color(1.0F, 1.0F, 1.0F, 1.0F) : new Color(0.84F, 0.84F, 0.84F, 0.7F));
            }
         }
      );
      this.initMenu(null, 0, 0, CFG.GAME_WIDTH, CFG.GAME_HEIGHT, menuElements);
      this.updateLanguage();
   }

   @Override
   public void updateLanguage() {
      this.getMenuElement(0).setText(CFG.langManager.get("Back"));
      this.getMenuElement(1).setText(CFG.langManager.get("AddNewBox"));
      this.getMenuElement(2).setText(CFG.langManager.get("SeaArmyBoxesEditor") + ": " + CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1);
   }

   @Override
   public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      super.beginClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
      CFG.drawEditorButtons_Bot_Edge_R(
         oSB, iTranslateX, this.getMenuElement(0).getPosY() - CFG.PADDING + iTranslateY, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT + CFG.PADDING * 2
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
            CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV2 = -1;
            Menu_MapEditor_ArmySeaBoxes_Add.oFirstPoint = new Point_XY(-1, -1);
            Menu_MapEditor_ArmySeaBoxes_Add.oSecondPoint = new Point_XY(-1, -1);
            CFG.menuManager.setViewID(Menu.eMAP_EDITOR_ARMY_SEA_BOXES_ADD);
            return;
      }
   }

   @Override
   public void onBackPressed() {
      CFG.menuManager.setViewID(Menu.eMAP_EDITOR_ARMY_SEA_BOXES);
      CFG.menuManager.setBackAnimation(true);
      if (CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes() != null
         && CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().size() > 0) {
         FileHandle file = Gdx.files.local("map/" + CFG.map.getFile_ActiveMap_Path() + "army_boxes/" + CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1);
         String sInput = "";

         for (int i = 0; i < CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().size(); i++) {
            if (CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getBelowZero()) {
               if (CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().get(i).getStartPosX()
                  >= CFG.map.getMapBG().getWidth() / 2) {
                  CFG.game
                     .getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1)
                     .getProvinceArmyBoxes()
                     .set(
                        i,
                        new Province_ArmyBox(
                           CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().get(i).getStartPosX()
                              - CFG.map.getMapBG().getWidth(),
                           CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().get(i).getStartPosY(),
                           CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().get(i).getEndPosX(),
                           CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().get(i).getEndPosY()
                        )
                     );
               }

               if (CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().get(i).getEndPosX() >= CFG.map.getMapBG().getWidth() / 2
                  )
                {
                  CFG.game
                     .getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1)
                     .getProvinceArmyBoxes()
                     .set(
                        i,
                        new Province_ArmyBox(
                           CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().get(i).getStartPosX(),
                           CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().get(i).getStartPosY(),
                           CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().get(i).getEndPosX()
                              - CFG.map.getMapBG().getWidth(),
                           CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().get(i).getEndPosY()
                        )
                     );
               }
            } else {
               if (CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().get(i).getStartPosX() > CFG.map.getMapBG().getWidth()) {
                  CFG.game
                     .getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1)
                     .getProvinceArmyBoxes()
                     .set(
                        i,
                        new Province_ArmyBox(
                           CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().get(i).getStartPosX()
                              - CFG.map.getMapBG().getWidth(),
                           CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().get(i).getStartPosY(),
                           CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().get(i).getEndPosX(),
                           CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().get(i).getEndPosY()
                        )
                     );
               }

               if (CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().get(i).getEndPosX() > CFG.map.getMapBG().getWidth()) {
                  CFG.game
                     .getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1)
                     .getProvinceArmyBoxes()
                     .set(
                        i,
                        new Province_ArmyBox(
                           CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().get(i).getStartPosX(),
                           CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().get(i).getStartPosY(),
                           CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().get(i).getEndPosX()
                              - CFG.map.getMapBG().getWidth(),
                           CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().get(i).getEndPosY()
                        )
                     );
               }
            }

            sInput = sInput
               + ""
               + CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().get(i).getStartPosX() / CFG.map.getMapBG().getMapScale()
               + ";";
            sInput = sInput
               + ""
               + CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().get(i).getStartPosY() / CFG.map.getMapBG().getMapScale()
               + ";";
            sInput = sInput
               + ""
               + CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().get(i).getEndPosX() / CFG.map.getMapBG().getMapScale()
               + ";";
            sInput = sInput
               + ""
               + CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().get(i).getEndPosY() / CFG.map.getMapBG().getMapScale()
               + ";";
         }

         file.writeString(sInput, false);
      } else {
         Gdx.files.local("map/" + CFG.map.getFile_ActiveMap_Path() + "army_boxes/" + CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).delete();
      }
   }
}
