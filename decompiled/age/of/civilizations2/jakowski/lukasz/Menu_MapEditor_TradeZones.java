package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

public class Menu_MapEditor_TradeZones extends SliderMenu {
   public Menu_MapEditor_TradeZones() {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      menuElements.add(new Button_Game(null, -1, CFG.PADDING, CFG.GAME_HEIGHT - CFG.PADDING - CFG.BUTTON_HEIGHT, CFG.BUTTON_WIDTH * 2));
      menuElements.add(new Button_Game("-", -1, CFG.BUTTON_WIDTH * 2 + CFG.PADDING * 2, CFG.GAME_HEIGHT - CFG.PADDING - CFG.BUTTON_HEIGHT, true));
      menuElements.add(
         new Slider_Age(
            "",
            CFG.BUTTON_WIDTH * 3 + CFG.PADDING * 3,
            CFG.GAME_HEIGHT - CFG.PADDING - CFG.BUTTON_HEIGHT,
            CFG.GAME_WIDTH - CFG.BUTTON_WIDTH * 4 - CFG.PADDING * 5,
            CFG.BUTTON_HEIGHT,
            0,
            CFG.gameAges.getAgesSize() - 1,
            0
         ) {
            @Override
            public String getDrawText() {
               return CFG.gameAges.getAge(this.getCurrent()).getName()
                  + ": ["
                  + CFG.gameAges.getYear(CFG.gameAges.getAge(this.getCurrent()).getBeginningYear())
                  + " - "
                  + CFG.gameAges.getYear(CFG.gameAges.getAge(this.getCurrent()).getEndYear())
                  + "]";
            }
         }
      );
      menuElements.add(new Button_Game("+", -1, CFG.GAME_WIDTH - CFG.BUTTON_WIDTH - CFG.PADDING, CFG.GAME_HEIGHT - CFG.PADDING - CFG.BUTTON_HEIGHT, true));
      menuElements.add(new Button_Game(null, -1, CFG.GAME_WIDTH - CFG.BUTTON_WIDTH * 4 - CFG.PADDING * 3, CFG.PADDING, CFG.BUTTON_WIDTH * 2));
      menuElements.add(new Button_Game(null, -1, CFG.GAME_WIDTH - CFG.BUTTON_WIDTH * 2 - CFG.PADDING * 2, CFG.PADDING, CFG.BUTTON_WIDTH));
      menuElements.add(new Button_Game(null, -1, CFG.GAME_WIDTH - CFG.BUTTON_WIDTH - CFG.PADDING, CFG.PADDING, CFG.BUTTON_WIDTH));
      this.initMenu(null, 0, 0, CFG.GAME_WIDTH, CFG.GAME_HEIGHT, menuElements);
      this.updateLanguage();
   }

   @Override
   public void updateLanguage() {
      this.getMenuElement(0).setText(CFG.langManager.get("Back"));
      this.getMenuElement(4).setText(CFG.langManager.get("AddNewTradeZone"));
      this.getMenuElement(5).setText(CFG.langManager.get("Edit"));
      this.getMenuElement(6).setText(CFG.langManager.get("Remove"));
   }

   @Override
   public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      super.beginClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
      ImageManager.getImage(Images.editor_line)
         .draw2(
            oSB,
            iTranslateX,
            this.getMenuElement(0).getPosY() - CFG.PADDING - ImageManager.getImage(Images.editor_line).getHeight() + iTranslateY,
            CFG.GAME_WIDTH,
            CFG.BUTTON_HEIGHT + CFG.PADDING * 2
         );
      CFG.drawEditorButtons_Top_Edge_R_Reflected(
         oSB,
         this.getMenuElement(4).getPosX() - CFG.PADDING + iTranslateX,
         this.getPosY() + iTranslateY,
         CFG.GAME_WIDTH - this.getMenuElement(4).getPosX() + CFG.PADDING * 2,
         CFG.BUTTON_HEIGHT + CFG.PADDING * 2
      );
      super.drawMenu(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
      super.endClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
   }

   @Override
   public final void actionElement(int iID) {
   }

   @Override
   public void onBackPressed() {
      CFG.menuManager.setViewID(Menu.eMAP_EDITOR_EDIT);
      CFG.menuManager.setBackAnimation(true);
      Game_Render_Province.updateDrawProvinces();
   }
}
