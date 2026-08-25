package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

public class Menu_InGame_CivInfo_Stats_DiploActions extends SliderMenu {
   public Menu_InGame_CivInfo_Stats_DiploActions() {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      menuElements.add(new Button_Diplo_Actions(null, -1, 0, 1, CFG.CIV_INFO_MENU_WIDTH / 2, CFG.TEXT_HEIGHT + CFG.PADDING * 3 - 2, true) {
         @Override
         public void buildElementHover() {
            ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
            ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
            nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_diplomacy_points));
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("DiplomaticActions"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
            this.menuElementHover = new MenuElement_Hover_v2(nElements);
         }
      });
      menuElements.add(
         new Button_Diplo_Opinions(null, -1, CFG.CIV_INFO_MENU_WIDTH / 2, 1, CFG.CIV_INFO_MENU_WIDTH / 2, CFG.TEXT_HEIGHT + CFG.PADDING * 3 - 2, true) {
            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.diplo_relations));
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("ShowOpinions"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
         }
      );
      this.initMenu(
         null,
         0,
         ImageManager.getImage(Images.new_game_top).getHeight()
            + CFG.PADDING * 4
            + (int)(CFG.TEXT_HEIGHT * 0.6F)
            + ImageManager.getImage(Images.top_flag_frame).getHeight()
            + CFG.PADDING * 4,
         CFG.CIV_INFO_MENU_WIDTH,
         CFG.TEXT_HEIGHT + CFG.PADDING * 3,
         menuElements,
         false,
         false
      );
      this.updateLanguage();
   }

   @Override
   public void updateLanguage() {
      this.getMenuElement(0).setText(CFG.langManager.get("Actions"));
      this.getMenuElement(1).setText(CFG.langManager.get("Opinions"));
   }

   @Override
   public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      if (Menu_InGame_CivInfo.lTime + 175L >= System.currentTimeMillis()) {
         int var5;
         int var6;
         iTranslateX = Menu_InGame_CivInfo.hideAnimation
            ? (var5 = iTranslateX - (int)(this.getWidth() * ((float)(System.currentTimeMillis() - Menu_InGame_CivInfo.lTime) / 175.0F)))
            : (var6 = iTranslateX + -this.getWidth() + (int)(this.getWidth() * ((float)(System.currentTimeMillis() - Menu_InGame_CivInfo.lTime) / 175.0F)));
         CFG.setRender_3(true);
      } else if (Menu_InGame_CivInfo.hideAnimation) {
         super.setVisible(false);
         return;
      }

      ImageManager.getImage(Images.new_game_top_edge_line)
         .draw2(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.new_game_top_edge_line).getHeight() + iTranslateY,
            this.getWidth(),
            this.getHeight() + 2,
            true,
            false
         );
      CFG.drawRect_InfoBox_Left_Title(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth() - 2, this.getHeight());
      this.beginClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
      oSB.setColor(Color.WHITE);
      this.drawMenu(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
      this.endClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
   }

   @Override
   public void drawScrollPos(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      if (sliderMenuIsActive) {
         super.drawScrollPos(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
      }
   }

   @Override
   public void onHovered() {
      CFG.menuManager.setOrderOfMenu_InGame_CivInfo();
   }

   @Override
   public void actionElement(int iID) {
      switch (iID) {
         case 0:
            CFG.menuManager.setVisible_InGame_CivInfo_Stats_Opinions(false);
            break;
         case 1:
            CFG.menuManager.setVisible_InGame_CivInfo_Stats_Opinions(true);
      }
   }

   @Override
   public void setVisible(boolean visible) {
      if (visible) {
         super.setVisible(visible);
      }
   }

   @Override
   public void actionClose() {
      super.setVisible(false);
   }
}
