package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

public class Button_Menu_Classic_YT extends Button_Menu {
   public Button_Menu_Classic_YT(int iPosX, int iPosY, int iWidth, int iHeight, boolean isClickable) {
      super(null, 0, iPosX, iPosY, iWidth, iHeight, isClickable);
   }

   public Button_Menu_Classic_YT(int nID, int iPosX, int iPosY, int iWidth, int iHeight, boolean isClickable) {
      super(null, nID, iPosX, iPosY, iWidth, iHeight, isClickable);
   }

   @Override
   public void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
      if (!isActive && !this.getIsHovered()) {
         ImageManager.getImage(Images.btn_menu_h).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), true, false);
      } else {
         ImageManager.getImage(Images.btnh_menu_h).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), true, false);
      }

      oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.65F));
      ImageManager.getImage(Images.line_32_vertical)
         .draw(
            oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.line_32_vertical).getHeight() + iTranslateY, 1, this.getHeight()
         );
      if (this.getClickable()) {
         if (isActive) {
            oSB.setColor(new Color(1.0F, 1.0F, 1.0F, 1.0F));
         } else if (this.getIsHovered()) {
            oSB.setColor(new Color(1.0F, 1.0F, 1.0F, 0.65F));
         } else {
            oSB.setColor(new Color(1.0F, 1.0F, 1.0F, Menu_Main.ICONS_ALPHA));
         }
      } else {
         oSB.setColor(new Color(1.0F, 1.0F, 1.0F, 0.05F));
      }

      ImageManager.getImage(Images.logo_yt)
         .draw(
            oSB,
            this.getPosX() + this.getWidth() / 2 - ImageManager.getImage(Images.logo_yt).getWidth() / 2 + iTranslateX,
            this.getPosY() + this.getHeight() / 2 - ImageManager.getImage(Images.logo_yt).getHeight() / 2 + iTranslateY
         );
      oSB.setColor(Color.WHITE);
   }

   @Override
   public void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
   }

   @Override
   public void buildElementHover() {
      ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
      ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
      nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("YouTube") + ".", CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
      nElements.add(new MenuElement_Hover_v2_Element2(nData));
      nData.clear();
      this.menuElementHover = new MenuElement_Hover_v2(nElements);
   }

   @Override
   public int getCurrent() {
      return this.iTextPositionX;
   }
}
