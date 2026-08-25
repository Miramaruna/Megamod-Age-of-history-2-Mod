package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;
import java.util.List;

public class Menu_InGame_Generals extends SliderMenu {
   public Menu_InGame_Generals() {
      List<General_Data> tMyGenerals = GeneralsManager.getPlayerGenerals();
      int tNumOfCards = tMyGenerals.size() + 1;
      int tempWidth = CFG.GAME_WIDTH;
      int tCardW = Math.min(260, (tempWidth - CFG.PADDING * 4) / Math.max(tNumOfCards, 1));
      int tCardH = CFG.BUTTON_HEIGHT * 3 / 5;
      int tempHeight = tCardH * 2 + CFG.PADDING * 3;

      ArrayList<MenuElement> menuElements = new ArrayList<>();
      int tPosX = CFG.PADDING * 2;

      for (int i = 0; i < tMyGenerals.size(); i++) {
         General_Data tGen = tMyGenerals.get(i);
         final int tGeneralID = GeneralsManager.lGenerals.indexOf(tGen);
         menuElements.add(
            new Button_FlagActionSliderStyle(
               tGen.sName + "  ур." + tGen.iLevel + "  (+" + (int)(tGen.getBonus() * 100.0F) + "%)  ⚔" + tGen.lProvinces.size(),
               -1,
               tPosX,
               CFG.PADDING,
               tCardW - CFG.PADDING * 2,
               true
            ) {
               @Override
               public void actionElement(int iID) {
                  GeneralsManager.SELECTED_GENERAL = tGeneralID;
                  CFG.game.getPlayer(CFG.PLAYER_TURNID).iACTIVE_VIEW_MODE = CFG.viewsManager.getActiveViewID();
                  CFG.viewsManager.disableAllViews();
                  CFG.game.setActiveProvinceID(-1);
                  Menu_InGame_SelectProvinces.typeOfAction = Menu_InGame_SelectProvinces.TypeOfAction.GENERAL_ASSIGN;
                  CFG.VIEW_SHOW_VALUES = false;
                  CFG.selectMode = true;
                  CFG.game.getSelectedProvinces().clearSelectedProvinces();
                  General_Data tSelGen = GeneralsManager.getGeneral(tGeneralID);

                  for (int p = 0; p < tSelGen.lProvinces.size(); p++) {
                     CFG.game.getSelectedProvinces().addProvince(tSelGen.lProvinces.get(p));
                  }

                  CFG.menuManager.setViewID(Menu.eINGAME_SELECT_PROVINCES);
                  Game_Render_Province.updateDrawProvinces();
               }

               @Override
               public void buildElementHover() {
                  ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
                  ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
                  nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("General_AssignHint"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                  nElements.add(new MenuElement_Hover_v2_Element2(nData));
                  nData.clear();
                  nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("General_BonusHint")));
                  nElements.add(new MenuElement_Hover_v2_Element2(nData));
                  this.menuElementHover = new MenuElement_Hover_v2(nElements);
               }

               @Override
               public boolean getClickable() {
                  return true;
               }

               @Override
               public int getSFX() {
                  return SoundsManager.getSend();
               }
            }
         );
         final int tDismissID = GeneralsManager.lGenerals.indexOf(tGen);
         menuElements.add(
            new Button_Game("[X]", -1, tPosX, CFG.PADDING * 2 + tCardH, 60, true) {
               @Override
               public void actionElement(int iID) {
                  GeneralsManager.dismissGeneral(tDismissID);
                  CFG.menuManager.setVisible_InGame_Generals(true);
               }
            }
         );
         tPosX += tCardW;
      }

      String tHireText = "+ " + CFG.langManager.get("HireGeneral") + " (" + GeneralsManager.getHireCost() + ")";
      menuElements.add(
         new Button_FlagActionSliderStyle(tHireText, -1, tPosX, CFG.PADDING, Math.max(140, tCardW - CFG.PADDING), true) {
            @Override
            public void actionElement(int iID) {
               GeneralsManager.hireGeneral();
               CFG.menuManager.setVisible_InGame_Generals(true);
            }

            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("HireGeneral_Info"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }

            @Override
            public boolean getClickable() {
               return true;
            }

            @Override
            public int getSFX() {
               return SoundsManager.getSend();
            }
         }
      );

      this.initMenu(null, 0, CFG.GAME_HEIGHT - tempHeight, tempWidth, tempHeight, menuElements, false, false);
   }

   @Override
   public void actionElement(int iID) {
      if (iID >= 0 && iID < this.getMenuElementsSize()) {
         this.getMenuElement(iID).actionElement(iID);
      }
   }

   @Override
   public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.72F));
      ImageManager.getImage(Images.pix255_255_255)
         .draw(oSB, this.getPosX(), this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight(), this.getWidth(), this.getHeight());
      oSB.setColor(CFG.COLOR_NEW_GAME_EDGE_LINE);
      ImageManager.getImage(Images.pix255_255_255)
         .draw(oSB, this.getPosX(), this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight(), this.getWidth(), 1);
      oSB.setColor(Color.WHITE);
      super.draw(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
   }
}
