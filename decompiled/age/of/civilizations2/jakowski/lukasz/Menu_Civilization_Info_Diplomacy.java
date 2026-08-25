package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

public class Menu_Civilization_Info_Diplomacy extends SliderMenu {
   public Menu_Civilization_Info_Diplomacy() {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      int tPosY = 0;
      ArrayList<Integer> tData = new ArrayList<>();
      if (CFG.getActiveCivInfo() > 0) {
         menuElements.add(
            new Button_Diplomacy_Wiki_Civ(
               CFG.getActiveCivInfo(),
               (int)CFG.game.getCivRelation_OfCivB(CFG.getActiveCivInfo(), CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()),
               0,
               2,
               tPosY,
               CFG.CIV_INFO_MENU_WIDTH - 2,
               CFG.TEXT_HEIGHT + CFG.PADDING * 4,
               true
            ) {}
         );
         tPosY += menuElements.get(menuElements.size() - 1).getHeight();
         tData.clear();

         for (int i2 = 1; i2 < CFG.game.getCivsSize(); i2++) {
            if (i2 != CFG.getActiveCivInfo()
               && (int)CFG.game.getCivRelation_OfCivB(i2, CFG.getActiveCivInfo()) == -100
               && CFG.game.getCiv(i2).getNumOfProvinces() > 0) {
               tData.add(i2);
            }
         }

         if (tData.size() > 0) {
            menuElements.add(new Button_Diplomacy(Images.diplo_war, tData, 2, tPosY, CFG.CIV_INFO_MENU_WIDTH - 2));
            tPosY += menuElements.get(menuElements.size() - 1).getHeight();
         }

         if (CFG.game.getCiv(CFG.getActiveCivInfo()).getAllianceID() > 0) {
            tData.clear();

            for (int var15 = 0; var15 < CFG.game.getAlliance(CFG.game.getCiv(CFG.getActiveCivInfo()).getAllianceID()).getCivilizationsSize(); var15++) {
               if (CFG.game.getAlliance(CFG.game.getCiv(CFG.getActiveCivInfo()).getAllianceID()).getCivilization(var15) != CFG.getActiveCivInfo()) {
                  tData.add(CFG.game.getAlliance(CFG.game.getCiv(CFG.getActiveCivInfo()).getAllianceID()).getCivilization(var15));
               }
            }

            if (tData.size() > 0) {
               menuElements.add(new Button_Diplomacy(Images.diplo_alliance, tData, 2, tPosY, CFG.CIV_INFO_MENU_WIDTH - 2));
               tPosY += menuElements.get(menuElements.size() - 1).getHeight();
            }
         }

         tData.clear();

         for (int var16 = 1; var16 < CFG.game.getCivsSize(); var16++) {
            if (var16 != CFG.getActiveCivInfo()
               && CFG.game.getCiv(var16).getNumOfProvinces() > 0
               && CFG.game.getCiv(var16).getPuppetOfCivID() == CFG.getActiveCivInfo()) {
               tData.add(var16);
            }
         }

         if (tData.size() > 0) {
            menuElements.add(new Button_Diplomacy_InGame(Images.diplo_vassal, tData, 2, tPosY, CFG.CIV_INFO_MENU_WIDTH - 2));
            tPosY += menuElements.get(menuElements.size() - 1).getHeight();
         }

         tData.clear();

         for (int var17 = 1; var17 < CFG.game.getCivsSize(); var17++) {
            if (var17 != CFG.getActiveCivInfo() && CFG.game.getCiv(var17).getNumOfProvinces() > 0 && CFG.game.getCivTruce(var17, CFG.getActiveCivInfo()) > 0) {
               tData.add(var17);
            }
         }

         if (tData.size() > 0) {
            menuElements.add(new Button_Diplomacy(Images.diplo_truce, tData, 2, tPosY, CFG.CIV_INFO_MENU_WIDTH - 2));
            tPosY += menuElements.get(menuElements.size() - 1).getHeight();
         }

         tData.clear();

         for (int var18 = 1; var18 < CFG.game.getCivsSize(); var18++) {
            if (var18 != CFG.getActiveCivInfo()
               && CFG.game.getCiv(var18).getNumOfProvinces() > 0
               && CFG.game.getDefensivePact(var18, CFG.getActiveCivInfo()) > 0) {
               tData.add(var18);
            }
         }

         if (tData.size() > 0) {
            menuElements.add(new Button_Diplomacy(Images.diplo_defensive_pact, tData, 2, tPosY, CFG.CIV_INFO_MENU_WIDTH - 2));
            tPosY += menuElements.get(menuElements.size() - 1).getHeight();
         }

         tData.clear();

         for (int var19 = 1; var19 < CFG.game.getCivsSize(); var19++) {
            if (var19 != CFG.getActiveCivInfo()
               && CFG.game.getCiv(var19).getNumOfProvinces() > 0
               && CFG.game.getCivNonAggressionPact(var19, CFG.getActiveCivInfo()) > 0) {
               tData.add(var19);
            }
         }

         if (tData.size() > 0) {
            menuElements.add(new Button_Diplomacy(Images.diplo_non_aggression, tData, 2, tPosY, CFG.CIV_INFO_MENU_WIDTH - 2));
            tPosY += menuElements.get(menuElements.size() - 1).getHeight();
         }

         tData.clear();

         for (int var20 = 1; var20 < CFG.game.getCivsSize(); var20++) {
            if (var20 != CFG.getActiveCivInfo() && CFG.game.getCiv(var20).getNumOfProvinces() > 0 && CFG.game.getGuarantee(var20, CFG.getActiveCivInfo()) > 0) {
               tData.add(var20);
            }
         }

         if (tData.size() > 0) {
            menuElements.add(new Button_Diplomacy(Images.diplo_guarantee_has, tData, 2, tPosY, CFG.CIV_INFO_MENU_WIDTH - 2));
            tPosY += menuElements.get(menuElements.size() - 1).getHeight();
         }

         tData.clear();

         for (int var21 = 1; var21 < CFG.game.getCivsSize(); var21++) {
            if (var21 != CFG.getActiveCivInfo() && CFG.game.getCiv(var21).getNumOfProvinces() > 0 && CFG.game.getGuarantee(CFG.getActiveCivInfo(), var21) > 0) {
               tData.add(var21);
            }
         }

         if (tData.size() > 0) {
            menuElements.add(new Button_Diplomacy(Images.diplo_guarantee_gives, tData, 2, tPosY, CFG.CIV_INFO_MENU_WIDTH - 2));
            tPosY += menuElements.get(menuElements.size() - 1).getHeight();
         }

         tData.clear();
         tData.clear();

         for (int var22 = 1; var22 < CFG.game.getCivsSize(); var22++) {
            if (var22 != CFG.getActiveCivInfo()
               && CFG.game.getCiv(var22).getNumOfProvinces() > 0
               && CFG.game.getMilitaryAccess(var22, CFG.getActiveCivInfo()) > 0) {
               tData.add(var22);
            }
         }

         if (tData.size() > 0) {
            menuElements.add(new Button_Diplomacy(Images.diplo_access_has, tData, 2, tPosY, CFG.CIV_INFO_MENU_WIDTH - 2));
            tPosY += menuElements.get(menuElements.size() - 1).getHeight();
         }

         tData.clear();

         for (int var23 = 1; var23 < CFG.game.getCivsSize(); var23++) {
            if (var23 != CFG.getActiveCivInfo()
               && CFG.game.getCiv(var23).getNumOfProvinces() > 0
               && CFG.game.getMilitaryAccess(CFG.getActiveCivInfo(), var23) > 0) {
               tData.add(var23);
            }
         }

         if (tData.size() > 0) {
            menuElements.add(new Button_Diplomacy(Images.diplo_guarantee_gives, tData, 2, tPosY, CFG.CIV_INFO_MENU_WIDTH - 2));
            tPosY += menuElements.get(menuElements.size() - 1).getHeight();
         }

         ArrayList<Integer> tempOpinions = new ArrayList<>();
         ArrayList tempSortedIDs = new ArrayList();

         for (int i = 1; i < CFG.game.getCivsSize(); i++) {
            if (CFG.game.getCiv(i).getNumOfProvinces() > 0 && CFG.getActiveCivInfo() != i) {
               tempOpinions.add(i);
            }
         }

         while (tempOpinions.size() > 0) {
            int highestID = 0;

            for (int i3 = 1; i3 < tempOpinions.size(); i3++) {
               if (CFG.game.getCivRelation_OfCivB(CFG.getActiveCivInfo(), tempOpinions.get(highestID))
                  > CFG.game.getCivRelation_OfCivB(CFG.getActiveCivInfo(), tempOpinions.get(i3))) {
                  highestID = i3;
               }
            }

            tempSortedIDs.add(tempOpinions.get(highestID));
            tempOpinions.remove(highestID);
         }

         tData.clear();
         int var12 = tempSortedIDs.size() - 1;

         for (int j = 0; var12 >= 0 && j < 4 && !(CFG.game.getCivRelation_OfCivB(CFG.getActiveCivInfo(), (Integer)tempSortedIDs.get(var12)) < 25.0F); j++) {
            tData.add((Integer)tempSortedIDs.get(var12));
            var12--;
         }

         if (tData.size() > 0) {
            menuElements.add(new Button_Diplomacy(Images.diplo_heart, tData, 2, tPosY, CFG.CIV_INFO_MENU_WIDTH - 2));
            tPosY += menuElements.get(menuElements.size() - 1).getHeight();
         }

         tData.clear();

         for (int var13 = 0; var13 < CFG.game.getCiv(CFG.getActiveCivInfo()).getHatedCivsSize(); var13++) {
            tData.add(CFG.game.getCiv(CFG.getActiveCivInfo()).getHatedCiv(var13).iCivID);
         }

         if (tData.size() > 0) {
            menuElements.add(new Button_Diplomacy_InGame(Images.diplo_rivals, tData, 2, tPosY, CFG.CIV_INFO_MENU_WIDTH - 2));
            tPosY += menuElements.get(menuElements.size() - 1).getHeight();
         }
      }

      this.initMenu(
         new SliderMenuTitle(null, CFG.TEXT_HEIGHT + CFG.PADDING * 2, false, false) {
            @Override
            public void draw(SpriteBatch oSB, int iTranslateX, int nPosX, int nPosY, int nWidth, boolean sliderMenuIsActive) {
               ImageManager.getImage(Images.new_game_top_edge_line)
                  .draw2(
                     oSB,
                     Menu_Civilization_Info_Diplomacy.this.getPosX() + iTranslateX,
                     Menu_Civilization_Info_Diplomacy.this.getPosY() - ImageManager.getImage(Images.new_game_top_edge_line).getHeight() - this.getHeight(),
                     Menu_Civilization_Info_Diplomacy.this.getWidth(),
                     this.getHeight()
                  );
               CFG.drawRect_InfoBox_Right_Title(
                  oSB,
                  Menu_Civilization_Info_Diplomacy.this.getPosX() + 2 + iTranslateX,
                  Menu_Civilization_Info_Diplomacy.this.getPosY() - this.getHeight(),
                  Menu_Civilization_Info_Diplomacy.this.getWidth(),
                  this.getHeight()
               );
               CFG.fontMain.getData().setScale(0.7F);
               CFG.drawTextWithShadow(
                  oSB,
                  this.getText(),
                  nPosX + nWidth / 2 - (int)(this.getTextWidth() * 0.7F) / 2 + iTranslateX,
                  nPosY - this.getHeight() + this.getHeight() / 2 - (int)(this.getTextHeight() * 0.7F) / 2,
                  CFG.COLOR_TEXT_CIV_INFO_TITLE
               );
               CFG.fontMain.getData().setScale(1.0F);
            }
         },
         CFG.GAME_WIDTH - CFG.CIV_INFO_MENU_WIDTH,
         ImageManager.getImage(Images.new_game_top).getHeight()
            + CFG.PADDING * 4
            + (int)(CFG.TEXT_HEIGHT * 0.6F)
            + ImageManager.getImage(Images.top_flag_frame).getHeight()
            + CFG.PADDING * 4,
         CFG.CIV_INFO_MENU_WIDTH,
         (CFG.CIV_FLAG_HEIGHT + CFG.PADDING * 2) * 3,
         menuElements,
         false,
         false
      );
      this.updateLanguage();

      for (int ix = 0; ix < this.getMenuElementsSize(); ix++) {
         this.getMenuElement(ix).setMax(ix % 2);
      }
   }

   @Override
   public void updateLanguage() {
      this.getTitle().setText(CFG.langManager.get("Diplomacy"));
   }

   @Override
   public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      if (Menu_Civilization_Info.lTime + 250L >= System.currentTimeMillis()) {
         iTranslateX += this.getWidth() - (int)(this.getWidth() * ((float)(System.currentTimeMillis() - Menu_Civilization_Info.lTime) / 250.0F));
         CFG.setRender_3(true);
      }

      ImageManager.getImage(Images.new_game_top_edge_line)
         .draw2(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.new_game_top_edge_line).getHeight() + iTranslateY,
            this.getWidth(),
            this.getHeight() + 2
         );
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
      CFG.menuManager.setOrderOfMenu_CreateNewGame_CivInfo();
   }

   @Override
   public void actionElement(int iID) {
      switch (iID) {
         case 0:
            this.getMenuElement(iID).actionElement(iID);
      }
   }
}
