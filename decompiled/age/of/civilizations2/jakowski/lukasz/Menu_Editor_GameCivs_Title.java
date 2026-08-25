package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import java.util.ArrayList;

public class Menu_Editor_GameCivs_Title extends SliderMenu {
   public Menu_Editor_GameCivs_Title() {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      menuElements.add(new Button_Menu_LR_Line(null, -1, 0, CFG.PADDING, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT, true));
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
      FileHandle tempFileT = Gdx.files.internal("game/civilizations/Age_of_Civilizations");
      String tempT = tempFileT.readString();
      String[] tagsSPLITED = tempT.split(";");
      this.getTitle().setText("Age of Civilizations II - " + CFG.langManager.get("GameCivilizations") + " [" + tagsSPLITED.length + "]");
   }

   @Override
   public final void actionElement(int iID) {
      switch (iID) {
         case 0:
            this.onBackPressed();
         default:
            Game_Render_Province.updateDrawProvinces();
      }
   }

   @Override
   public final void onBackPressed() {
      CFG.menuManager.setViewID(Menu.eGAME_EDITOR);
      CFG.menuManager.setBackAnimation(true);
   }
}
