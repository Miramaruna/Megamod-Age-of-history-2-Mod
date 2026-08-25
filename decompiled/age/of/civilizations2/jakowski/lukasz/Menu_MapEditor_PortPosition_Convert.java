package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import java.util.ArrayList;

public class Menu_MapEditor_PortPosition_Convert extends SliderMenu {
   public Menu_MapEditor_PortPosition_Convert() {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      menuElements.add(new Button_Menu(null, -1, 0, 0, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT, true));
      FileHandle tempFileT = Gdx.files.internal("map/" + CFG.map.getFile_ActiveMap_Path() + "data/scales/provinces/Age_of_Civilizations");
      String tempT = tempFileT.readString();
      String[] tagsSPLITED = tempT.split(";");
      ArrayList<Integer> tempScales = new ArrayList<>();

      for (int i = 0; i < tagsSPLITED.length; i++) {
         tempScales.add(Integer.parseInt(tagsSPLITED[i]));
      }

      for (int var7 = 0; var7 < tempScales.size(); var7++) {
         if (CFG.map.getMapScale(CFG.map.getActiveMapID()) == tempScales.get(var7)) {
            menuElements.add(
               new Button_Menu(
                  CFG.langManager.get("Scale")
                     + " x"
                     + tempScales.get(var7)
                     + " - ["
                     + CFG.map.getMapBG().getWidth() / CFG.map.getMapScale(CFG.map.getActiveMapID()) * tempScales.get(var7)
                     + "x"
                     + CFG.map.getMapBG().getHeight() / CFG.map.getMapScale(CFG.map.getActiveMapID()) * tempScales.get(var7)
                     + "]",
                  (int)(50.0F * CFG.GUI_SCALE),
                  0,
                  CFG.BUTTON_HEIGHT * var7 + CFG.PADDING * (var7 + 1),
                  CFG.GAME_WIDTH,
                  CFG.BUTTON_HEIGHT,
                  true,
                  true
               )
            );
         } else {
            menuElements.add(
               new Button_Menu(
                  CFG.langManager.get("Scale")
                     + " x"
                     + tempScales.get(var7)
                     + " - ["
                     + CFG.map.getMapBG().getWidth() / CFG.map.getMapScale(CFG.map.getActiveMapID()) * tempScales.get(var7)
                     + "x"
                     + CFG.map.getMapBG().getHeight() / CFG.map.getMapScale(CFG.map.getActiveMapID()) * tempScales.get(var7)
                     + "]",
                  (int)(50.0F * CFG.GUI_SCALE),
                  0,
                  CFG.BUTTON_HEIGHT * var7 + CFG.PADDING * (var7 + 1),
                  CFG.GAME_WIDTH,
                  CFG.BUTTON_HEIGHT,
                  true
               )
            );
         }
      }

      this.initMenuWithBackButton(
         new SliderMenuTitle(null, CFG.BUTTON_HEIGHT * 3 / 4, false, false),
         0,
         CFG.BUTTON_HEIGHT * 3 / 4,
         CFG.GAME_WIDTH,
         CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT * 3 / 4,
         menuElements
      );
      this.updateLanguage();
   }

   @Override
   public void updateLanguage() {
      this.getMenuElement(0).setText(CFG.langManager.get("Back"));
      this.getTitle().setText(CFG.langManager.get("ConvertToAnotherScale"));
   }

   @Override
   public final void actionElement(int iID) {
      switch (iID) {
         case 0:
            this.onBackPressed();
            return;
         default:
            FileHandle tempFileT = Gdx.files.internal("map/" + CFG.map.getFile_ActiveMap_Path() + "data/scales/provinces/Age_of_Civilizations");
            String tempT = tempFileT.readString();
            String[] tagsSPLITED = tempT.split(";");
            CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV2 = Integer.parseInt(tagsSPLITED[iID - 1]);
            if (CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV2 != CFG.map.getMapScale(CFG.map.getActiveMapID())) {
               CFG.setDialogType(Dialog.CONVERT_PORT_POSITION_TO_ANOTHER_SCALE);
            }
      }
   }

   @Override
   public final void onBackPressed() {
      CFG.menuManager.setViewID(Menu.eMAP_EDITOR_PORT_POSITION);
      CFG.menuManager.setBackAnimation(true);
      CFG.editorManager.setInUse(Editors.eSHIFT_PORT);
   }
}
