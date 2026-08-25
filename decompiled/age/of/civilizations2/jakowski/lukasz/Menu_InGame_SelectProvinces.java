package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

public class Menu_InGame_SelectProvinces extends SliderMenu {
   public static Menu_InGame_SelectProvinces.TypeOfAction typeOfAction = Menu_InGame_SelectProvinces.TypeOfAction.TRADE_LEFT;

   public Menu_InGame_SelectProvinces() {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      menuElements.add(
         new Button_Game(null, -1, CFG.PADDING, CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING, true) {
            @Override
            public Color getColor(boolean isActive) {
               return isActive
                  ? CFG.COLOR_BUTTON_GAME_TEXT_IMPORTANT_ACTIVE
                  : (
                     this.getClickable()
                        ? (this.getIsHovered() ? CFG.COLOR_BUTTON_GAME_TEXT_IMPORTANT_HOVER : CFG.COLOR_BUTTON_GAME_TEXT_IMPORTANT)
                        : CFG.COLOR_BUTTON_GAME_TEXT_NOT_CLICKABLE
                  );
            }
         }
      );
      menuElements.add(new Button_Game_Checkbox(null, -1, CFG.PADDING, CFG.PADDING, CFG.BUTTON_WIDTH * 2, true, false) {
         @Override
         public boolean getCheckboxState() {
            return CFG.brushTool;
         }
      });
      menuElements.add(new Button_Game_Checkbox(null, -1, CFG.BUTTON_WIDTH * 2 + CFG.PADDING * 2, CFG.PADDING, CFG.BUTTON_WIDTH, true, true) {
         @Override
         public boolean getCheckboxState() {
            return CFG.selectMode;
         }
      });
      menuElements.add(new Button_Game(null, -1, CFG.BUTTON_WIDTH * 3 + CFG.PADDING * 3, CFG.PADDING, CFG.BUTTON_WIDTH, false) {
         @Override
         public boolean getClickable() {
            return CFG.game.getSelectedProvinces().getProvincesSize() > 0;
         }
      });
      menuElements.add(new Button_Game(null, -1, CFG.BUTTON_WIDTH * 3 + CFG.PADDING * 3, CFG.PADDING, CFG.BUTTON_WIDTH, false) {
         @Override
         public boolean getClickable() {
            return CFG.game.getSelectedProvinces().getProvincesSize() > 0;
         }
      });
      menuElements.add(new Button_Game_Checkbox(null, -1, CFG.BUTTON_WIDTH * 2 + CFG.PADDING * 2, CFG.PADDING, CFG.BUTTON_WIDTH, true, true) {
         @Override
         public boolean getCheckboxState() {
            return CFG.VIEW_SHOW_VALUES;
         }
      });
      menuElements.add(
         new Button_CivName(
            CFG.MANAGE_DIPLOMACY_CUSTOMIZE_ALLIANCE_ID,
            0,
            CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING * 3 - Math.max(CFG.TEXT_HEIGHT + CFG.PADDING * 4, CFG.CIV_FLAG_HEIGHT + CFG.PADDING * 2),
            CFG.CIV_INFO_MENU_WIDTH * 3 / 4,
            Math.max(CFG.TEXT_HEIGHT + CFG.PADDING * 4, CFG.CIV_FLAG_HEIGHT + CFG.PADDING * 2),
            true
         )
      );
      this.initMenu(null, 0, 0, CFG.GAME_WIDTH, CFG.GAME_HEIGHT, menuElements);
      this.updateLanguage();
   }

   @Override
   public void updateLanguage() {
      this.getMenuElement(0).setText(CFG.langManager.get("Save"));
      this.getMenuElement(1).setText(CFG.langManager.get("Brush"));
      this.getMenuElement(2).setText(CFG.langManager.get("Select"));
      this.getMenuElement(3).setText(CFG.langManager.get("DeselectAll"));
      this.getMenuElement(4).setText(CFG.langManager.get("Undo"));
      this.getMenuElement(5).setText(CFG.langManager.get("Map"));
      this.updateButtonWidth(4, CFG.PADDING, CFG.BUTTON_WIDTH * 2);

      for (int i = 2; i < 6; i++) {
         this.updateButtonWidth(i, CFG.PADDING, CFG.BUTTON_WIDTH);
      }

      this.updateButtonWidth(0, CFG.PADDING, CFG.BUTTON_WIDTH * 2);
      int tempX = CFG.GAME_WIDTH - this.getMenuElement(2).getWidth() - CFG.PADDING;
      this.getMenuElement(2).setPosX(tempX);
      tempX = tempX - this.getMenuElement(1).getWidth() - CFG.PADDING;
      this.getMenuElement(1).setPosX(tempX);
      tempX = tempX - this.getMenuElement(3).getWidth() - CFG.PADDING;
      this.getMenuElement(3).setPosX(tempX);
      tempX = tempX - this.getMenuElement(4).getWidth() - CFG.PADDING;
      this.getMenuElement(4).setPosX(tempX);
      tempX = tempX - this.getMenuElement(5).getWidth() - CFG.PADDING;
      this.getMenuElement(5).setPosX(tempX);
   }

   @Override
   public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      CFG.drawEditorButtons_Top_Edge_R_Reflected(
         oSB,
         this.getMenuElement(5).getPosX() - CFG.PADDING + iTranslateX,
         this.getMenuPosY() + iTranslateY,
         CFG.GAME_WIDTH - (this.getMenuElement(5).getPosX() - CFG.PADDING),
         CFG.BUTTON_HEIGHT + CFG.PADDING * 2
      );
      CFG.drawEditorButtons_Bot_Edge_R(
         oSB,
         this.getMenuElement(0).getPosX() - CFG.PADDING + iTranslateX,
         this.getMenuPosY() + this.getMenuElement(0).getPosY() - CFG.PADDING + iTranslateY,
         this.getMenuElement(0).getPosX() + this.getMenuElement(0).getWidth() + CFG.PADDING,
         this.getMenuElement(0).getHeight() + CFG.PADDING * 2
      );
      super.draw(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
   }

   @Override
   public final void actionElement(int iID) {
      switch (iID) {
         case 0:
            this.onBackPressed();
            return;
         case 1:
            CFG.brushTool = !CFG.brushTool;
            break;
         case 2:
            CFG.selectMode = !CFG.selectMode;
            break;
         case 3:
            CFG.setDialogType(Dialog.DESELET_ALL_SELECTED_PROVINCES);
            break;
         case 4:
            CFG.game.getSelectedProvinces().popProvince();
            if (CFG.game.getSelectedProvinces().getProvincesSize() == 0) {
               CFG.selectMode = true;
            }
            break;
         case 5:
            CFG.VIEW_SHOW_VALUES = !CFG.VIEW_SHOW_VALUES;
      }
   }

   @Override
   public void onBackPressed() {
      CFG.brushTool = false;
      CFG.menuManager.setViewID(Menu.eINGAME);
      Game_Render_Province.updateDrawProvinces();
      if (typeOfAction == Menu_InGame_SelectProvinces.TypeOfAction.TRADE_LEFT) {
         CFG.tradeRequest.listLEFT.lProvinces.clear();

         for (int i = 0; i < CFG.game.getSelectedProvinces().getProvincesSize(); i++) {
            CFG.tradeRequest.listLEFT.lProvinces.add(CFG.game.getSelectedProvinces().getProvince(i));
         }

         CFG.menuManager.rebuildInGame_TradeRequest_Just();
      } else if (typeOfAction == Menu_InGame_SelectProvinces.TypeOfAction.BUILDING) {
         CFG.game.buildInProvinces.clear();

         for (int i = 0; i < CFG.game.getSelectedProvinces().getProvincesSize(); i++) {
            CFG.game.buildInProvinces.add(CFG.game.getSelectedProvinces().getProvince(i));
         }

         Menu_InGame_Build_All.provincesCount = CFG.game.buildInProvinces.size();
         CFG.menuManager.rebuildInGame_BuildAll(Menu_InGame_Build_All.iLastProvince);
      } else if (typeOfAction == Menu_InGame_SelectProvinces.TypeOfAction.MOBOLIZATION) {
         CFG.game.recruitInProvinces.clear();

         for (int i = 0; i < CFG.game.getSelectedProvinces().getProvincesSize(); i++) {
            CFG.game.recruitInProvinces.add(CFG.game.getSelectedProvinces().getProvince(i));
         }

         Menu_InGame_mobila.provincesCount = CFG.game.recruitInProvinces.size();
         CFG.menuManager.rebuildInGame_mobilization(Menu_InGame_mobila.iLastProvince);
         int x = 0;

         for (int provinceIndex : CFG.game.recruitInProvinces) {
            int recruitableArmy = CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).lProvincesWithMoreRecruitableArmy.get(x);
            Menu_InGame_mobila.recruitCount += recruitableArmy;
            x++;
         }
      } else if (typeOfAction == Menu_InGame_SelectProvinces.TypeOfAction.TROOPDISTIBUTION) {
         CFG.game.TroopDistributionInProvinces.clear();

         for (int i = 0; i < CFG.game.getSelectedProvinces().getProvincesSize(); i++) {
            CFG.game.TroopDistributionInProvinces.add(CFG.game.getSelectedProvinces().getProvince(i));
         }

         Menu_InGame_TroopDistribution.provincesCount = CFG.game.TroopDistributionInProvinces.size();
         CFG.menuManager.rebuildInGame_troopDistribution(Menu_InGame_TroopDistribution.iLastProvince);
      } else if (typeOfAction == Menu_InGame_SelectProvinces.TypeOfAction.TRADE_RIGHT) {
         CFG.tradeRequest.listRight.lProvinces.clear();

         for (int i = 0; i < CFG.game.getSelectedProvinces().getProvincesSize(); i++) {
            CFG.tradeRequest.listRight.lProvinces.add(CFG.game.getSelectedProvinces().getProvince(i));
         }

         CFG.menuManager.rebuildInGame_TradeRequest_Just();
      } else if (typeOfAction == Menu_InGame_SelectProvinces.TypeOfAction.ULTIMATUM) {
         CFG.ultimatum.demandProvinces.clear();

         for (int i = 0; i < CFG.game.getSelectedProvinces().getProvincesSize(); i++) {
            CFG.ultimatum.demandProvinces.add(CFG.game.getSelectedProvinces().getProvince(i));
         }
      } else if (typeOfAction == Menu_InGame_SelectProvinces.TypeOfAction.ARMY_PRIORITY) {
         AI_Assistant.PRIORITY_COUNTRIES.clear();
         int tPlayerCivID = CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID();

         for (int i = 0; i < CFG.game.getSelectedProvinces().getProvincesSize(); i++) {
            int tCivID = CFG.game.getProvince(CFG.game.getSelectedProvinces().getProvince(i)).getCivID();
            if (tCivID > 0 && tCivID != tPlayerCivID && !AI_Assistant.PRIORITY_COUNTRIES.contains(tCivID)) {
               AI_Assistant.PRIORITY_COUNTRIES.add(tCivID);
            }
         }

         CFG.toast.setInView(
            CFG.langManager.get("ArmyPriority")
               + ": "
               + AI_Assistant.PRIORITY_COUNTRIES.size(),
            CFG.COLOR_TEXT_MODIFIER_POSITIVE
         );
         CFG.toast.setTimeInView(4000);
      } else if (typeOfAction == Menu_InGame_SelectProvinces.TypeOfAction.GENERAL_ASSIGN) {
         GeneralsManager.SELECTED_GENERAL = -1;
         CFG.menuManager.setVisible_InGame_Generals(true);
      } else if (typeOfAction == Menu_InGame_SelectProvinces.TypeOfAction.GARRISON_SELECT) {
         AI_Assistant.GARRISON_PROVINCES.clear();
         int tOwnCiv = CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID();

         for (int i = 0; i < CFG.game.getSelectedProvinces().getProvincesSize(); i++) {
            int tProvID = CFG.game.getSelectedProvinces().getProvince(i);
            if (CFG.game.getProvince(tProvID).getCivID() == tOwnCiv && !AI_Assistant.GARRISON_PROVINCES.contains(tProvID)) {
               AI_Assistant.GARRISON_PROVINCES.add(tProvID);
            }
         }

         CFG.toast.setInView(
            CFG.langManager.get("Garrison") + ": " + AI_Assistant.GARRISON_PROVINCES.size(),
            CFG.COLOR_TEXT_MODIFIER_POSITIVE
         );
         CFG.toast.setTimeInView(4000);
      } else if (typeOfAction == Menu_InGame_SelectProvinces.TypeOfAction.FORT_BORDER) {
         CFG.toast.setInView(
            CFG.langManager.get("FortStrip") + ": " + AI_Assistant.FORT_STRIP_PROVINCES.size() + " "
               + CFG.langManager.get("Provinces"),
            CFG.COLOR_TEXT_MODIFIER_POSITIVE
         );
         CFG.toast.setTimeInView(4000);
      }

      CFG.viewsManager.setActiveViewID(CFG.game.getPlayer(CFG.PLAYER_TURNID).iACTIVE_VIEW_MODE);
   }

   public static enum TypeOfAction {
      TRADE_LEFT,
      TRADE_RIGHT,
      MOBOLIZATION,
      TROOPDISTIBUTION,
      TRADE_LEFT_DECLAREWAR,
      TRADE_RIGHT_DECLAREWAR,
      TRADE_LEFT_COALITION,
      TRADE_RIGHT_COALITION,
      ULTIMATUM,
      BUILDING,
      ARMY_PRIORITY,
      GENERAL_ASSIGN,
      FORT_BORDER,
      GARRISON_SELECT;
   }
}
