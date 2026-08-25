package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

public class Menu_ReligionEditor_Add extends SliderMenu {
   public String sName;
   public int iNameWidth;
   public String sIconFileName;
   public final String sIconFileNameType = ".png";

   public Menu_ReligionEditor_Add() {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      this.initMenu(null, 0, 0, CFG.GAME_WIDTH, CFG.GAME_HEIGHT, menuElements);
      this.updateLanguage();
   }

   @Override
   public void updateLanguage() {
   }

   @Override
   public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      CFG.drawEditorTitle_Edge_R(oSB, iTranslateX, iTranslateY, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT + CFG.PADDING * 2);
      super.draw(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
   }

   @Override
   public final void actionElement(int iID) {
   }

   @Override
   public void onBackPressed() {
      CFG.menuManager.getColorPicker().setVisible(false, null);
      CFG.menuManager.setViewID(Menu.eEDITOR_RELIGION);
      CFG.menuManager.setBackAnimation(true);
      Game_Render_Province.updateDrawProvinces();
   }
}
