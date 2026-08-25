package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import java.util.ArrayList;
import java.util.List;

public class Menu_GameEditor_Regions extends SliderMenu {
   public static List<Color> lColors = new ArrayList<>();

   public Menu_GameEditor_Regions() {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      this.initMenu(null, 0, 0, CFG.PADDING, CFG.PADDING, menuElements);

      for (int i = 0; i < CFG.game.getRegions().size(); i++) {
         lColors.add(CFG.getRandomColor());
      }
   }

   @Override
   public final void actionElement(int iID) {
      switch (iID) {
      }
   }

   @Override
   public final void onBackPressed() {
      CFG.menuManager.setViewID(Menu.eMAP_EDITOR_EDIT);
      CFG.menuManager.setBackAnimation(true);
      Editor_Regions.lUndo.clear();
      CFG.brushTool = false;
      CFG.editorManager.resetInUseEditors();
      Game_Render_Province.updateDrawProvinces();

      for (int i = 0; i < CFG.game.getProvincesSize(); i++) {
         CFG.game.getProvince(i).getArmy_Obj(0).updateArmyWidth_Just(i);
      }
   }
}
