package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

public class Menu_MapEditor_TradeZones_Edit extends SliderMenu {
   public String sName;
   public int iNameWidth;

   public Menu_MapEditor_TradeZones_Edit() {
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
            public String getTextToDraw() {
               return Menu_MapEditor_TradeZones_Edit.this.sName + ": " + super.getText();
            }

            @Override
            public int getTextWidth() {
               return super.getTextWidth() + Menu_MapEditor_TradeZones_Edit.this.iNameWidth;
            }

            @Override
            public void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
            }
         }
      );
      menuElements.add(new Button_Game(null, -1, CFG.GAME_WIDTH - CFG.BUTTON_WIDTH - CFG.PADDING, CFG.PADDING, true));
      menuElements.add(new Button_Game(null, -1, CFG.PADDING, CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING, CFG.BUTTON_WIDTH * 2, true));
      menuElements.add(new Button_Game_Checkbox(null, -1, CFG.PADDING, CFG.BUTTON_HEIGHT + CFG.PADDING * 3, CFG.BUTTON_WIDTH * 2, true, CFG.brushTool));
      menuElements.add(
         new Button_Game_Checkbox(null, -1, CFG.BUTTON_WIDTH * 2 + CFG.PADDING * 2, CFG.BUTTON_HEIGHT + CFG.PADDING * 3, CFG.BUTTON_WIDTH, true, CFG.selectMode)
      );
      menuElements.add(new Button_Game_ColorPicker(CFG.BUTTON_WIDTH * 3 + CFG.PADDING * 3, CFG.BUTTON_HEIGHT + CFG.PADDING * 3, true));
      this.initMenu(null, 0, 0, CFG.GAME_WIDTH, CFG.GAME_HEIGHT, menuElements);
      this.updateLanguage();
   }

   @Override
   public void updateLanguage() {
      this.sName = CFG.langManager.get("TheNameOfTradeZone");
      CFG.glyphLayout.setText(CFG.fontMain, this.sName + ": ");
      this.iNameWidth = (int)CFG.glyphLayout.width;
      this.getMenuElement(0).setText(CFG.langManager.get("Back"));
      this.getMenuElement(2).setText(CFG.langManager.get("Save"));
      this.getMenuElement(3).setText(CFG.langManager.get("CenterOfTrade"));
      this.getMenuElement(4).setText(CFG.langManager.get("Brush"));
      this.getMenuElement(5).setText(CFG.langManager.get("Add"));
      this.updateButtonWidth(3, CFG.PADDING, CFG.BUTTON_WIDTH * 2);
   }

   @Override
   public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      CFG.drawEditorTitle_Edge_R(oSB, iTranslateX, iTranslateY, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT + CFG.PADDING * 2);
      CFG.drawEditorButtons_Top_Edge_R(
         oSB,
         this.getMenuElement(4).getPosX() - CFG.PADDING + iTranslateX,
         this.getMenuElement(4).getPosY() - CFG.PADDING + iTranslateY,
         this.getMenuElement(6).getPosX() + this.getMenuElement(6).getWidth() + CFG.PADDING,
         this.getMenuElement(4).getHeight() + CFG.PADDING * 2
      );
      CFG.drawEditorButtons_Bot_Edge_R(
         oSB,
         this.getMenuElement(3).getPosX() - CFG.PADDING + iTranslateX,
         this.getMenuElement(3).getPosY() + this.getMenuPosY() - CFG.PADDING + iTranslateY,
         this.getMenuElement(3).getWidth() + CFG.PADDING * 2,
         this.getMenuElement(3).getHeight() + CFG.PADDING * 2
      );
      super.draw(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
   }

   @Override
   public final void actionElement(int iID) {
   }

   @Override
   public void onBackPressed() {
      CFG.menuManager.setViewID(Menu.eMAP_EDITOR_TRADE_ZONES);
      CFG.menuManager.setBackAnimation(true);
      CFG.brushTool = false;
      CFG.menuManager.getColorPicker().setVisible(false, null);
      Game_Render_Province.updateDrawProvinces();
   }
}
