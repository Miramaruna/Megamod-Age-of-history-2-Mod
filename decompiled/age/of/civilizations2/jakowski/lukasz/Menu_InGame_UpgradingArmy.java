package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;
import java.util.List;

public class Menu_InGame_UpgradingArmy extends SliderMenu {
   public int iCivID;

   public Menu_InGame_UpgradingArmy(int tInit) {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      int tempWidth = (int)(CFG.CIV_INFO_MENU_WIDTH * 1.75F);
      int tempMenuPosY = ImageManager.getImage(Images.top_flag_frame).getHeight()
         + CFG.PADDING * 4
         + CFG.BUTTON_HEIGHT * 3 / 5
         + CFG.PADDING * 2
         + CFG.BUTTON_HEIGHT * 3 / 4;
      if (tempWidth > CFG.GAME_WIDTH) {
         tempWidth = CFG.GAME_WIDTH - CFG.PADDING * 2;
      }

      this.initMenu(null, CFG.GAME_WIDTH / 2 - tempWidth / 2, tempMenuPosY, tempWidth, CFG.GAME_HEIGHT * 4 / 5, menuElements, false, false);
   }

   public Menu_InGame_UpgradingArmy() {
      this.iCivID = CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID();
      boolean tRow = false;
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      int tPosY = CFG.PADDING + CFG.TEXT_HEIGHT + CFG.PADDING * 4;
      int tempWidth = (int)(CFG.CIV_INFO_MENU_WIDTH * 1.5F);
      if (tempWidth > CFG.GAME_WIDTH) {
         tempWidth = CFG.GAME_WIDTH - CFG.PADDING * 2;
      }

      int tElemHeight = CFG.TEXT_HEIGHT + CFG.PADDING * 4;
      if (CFG.isAndroid()) {
         int var10000 = CFG.TEXT_HEIGHT + CFG.PADDING * 2;
      } else {
         int var24 = CFG.TEXT_HEIGHT + CFG.PADDING * 2;
      }

      int tY = 0;
      ArrayList<Integer> nData2 = new ArrayList<>();
      ArrayList<Integer> nCivs2 = new ArrayList<>();
      nData2.add(CFG.game.getCiv(this.iCivID).civGameData.skills.POINTS_ATTACK);
      nCivs2.add(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
      nData2.add(200 - CFG.game.getCiv(this.iCivID).civGameData.skills.POINTS_ATTACK);
      nCivs2.add(0);
      tRow = !tRow;
      menuElements.add(
         new Graph_Circle_UpgradingArmy(
            false,
            Images.diplo_war,
            tRow,
            CFG.langManager.get("AttackBonus"),
            new Color(0.627451F, 0.09803922F, 0.078431375F, 1.0F),
            2,
            tY,
            nData2,
            nCivs2,
            CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(),
            "" + CFG.getNumberWithSpaces("" + CFG.game.getCiv(this.iCivID).civGameData.skills.POINTS_ATTACK),
            " / 200",
            "" + CFG.getPercentage(CFG.game.getCiv(this.iCivID).civGameData.skills.POINTS_ATTACK, 200, 4) + "%"
         ) {
            @Override
            public int getWidth() {
               return Menu_InGame_UpgradingArmy.this.getElementW() - 4;
            }

            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("AttackBonus"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
               nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.diplo_war, CFG.PADDING, 0));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMilitaryPoints() < 25) {
                  nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("ItTakesXMilitaryExperience", 25), CFG.COLOR_TEXT_MODIFIER_NEGATIVE));
                  nElements.add(new MenuElement_Hover_v2_Element2(nData));
                  nData.clear();
               }

               this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }

            @Override
            public void actionElement(int iID) {
               SkillsManager.add_Attack(Menu_InGame_UpgradingArmy.this.iCivID);
               CFG.menuManager.updateInGame_TOP_All(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
               CFG.menuManager.rebuildInGame_UpgradingArmy();
               Menu_InGame_UpgradingArmy.this.rebuildBudgetView();
               Menu_InGame.updateOverBudget();
            }
         }
      );
      tY += menuElements.get(menuElements.size() - 1).getHeight();
      ArrayList<Integer> nData3 = new ArrayList<>();
      ArrayList<Integer> nCivs3 = new ArrayList<>();
      nData3.add(150 - CFG.game.getCiv(this.iCivID).civGameData.skills.POINTS_Defense);
      nCivs3.add(0);
      nData3.add(CFG.game.getCiv(this.iCivID).civGameData.skills.POINTS_Defense);
      nCivs3.add(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
      tRow = !tRow;
      menuElements.add(
         new Graph_Circle_UpgradingArmy(
            false,
            Images.defensive_position,
            tRow,
            CFG.langManager.get("DefenseBonus"),
            CFG.COLOR_FORT_2,
            2,
            tY,
            nData3,
            nCivs3,
            CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(),
            "" + CFG.getNumberWithSpaces("" + CFG.game.getCiv(this.iCivID).civGameData.skills.POINTS_Defense),
            " / 150",
            "" + CFG.getPercentage(CFG.game.getCiv(this.iCivID).civGameData.skills.POINTS_Defense, 150, 4) + "%"
         ) {
            @Override
            public int getWidth() {
               return Menu_InGame_UpgradingArmy.this.getElementW() - 4;
            }

            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("DefenseBonus"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
               nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.diplo_war, CFG.PADDING, 0));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMilitaryPoints() < 25) {
                  nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("ItTakesXMilitaryExperience", 25), CFG.COLOR_TEXT_MODIFIER_NEGATIVE));
                  nElements.add(new MenuElement_Hover_v2_Element2(nData));
                  nData.clear();
               }

               this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }

            @Override
            public void actionElement(int iID) {
               SkillsManager.add_Defense(Menu_InGame_UpgradingArmy.this.iCivID);
               CFG.menuManager.updateInGame_TOP_All(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
               CFG.menuManager.rebuildInGame_UpgradingArmy();
               Menu_InGame_UpgradingArmy.this.rebuildBudgetView();
               Menu_InGame.updateOverBudget();
            }
         }
      );
      tY += menuElements.get(menuElements.size() - 1).getHeight();
      ArrayList<Integer> nData4 = new ArrayList<>();
      ArrayList<Integer> nCivs4 = new ArrayList<>();
      nData4.add(50 - CFG.game.getCiv(this.iCivID).civGameData.skills.POINTS_BONUS_GENOCIDE);
      nCivs4.add(0);
      nData4.add(CFG.game.getCiv(this.iCivID).civGameData.skills.POINTS_BONUS_GENOCIDE);
      nCivs4.add(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
      tRow = !tRow;
      menuElements.add(
         new Graph_Circle_UpgradingArmy(
            false,
            Images.act_plunder,
            tRow,
            CFG.langManager.get("PowerGenocide"),
            CFG.COLOR_INGAME_GOLD,
            2,
            tY,
            nData4,
            nCivs4,
            CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(),
            "" + CFG.getNumberWithSpaces("" + CFG.game.getCiv(this.iCivID).civGameData.skills.POINTS_BONUS_GENOCIDE),
            " / 50",
            "" + CFG.getPercentage(CFG.game.getCiv(this.iCivID).civGameData.skills.POINTS_BONUS_GENOCIDE, 50, 4) + "%"
         ) {
            @Override
            public int getWidth() {
               return Menu_InGame_UpgradingArmy.this.getElementW() - 4;
            }

            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("PowerGenocide"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
               nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.diplo_war, CFG.PADDING, 0));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMilitaryPoints() < 25) {
                  nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("ItTakesXMilitaryExperience", 25), CFG.COLOR_TEXT_MODIFIER_NEGATIVE));
                  nElements.add(new MenuElement_Hover_v2_Element2(nData));
                  nData.clear();
               }

               this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }

            @Override
            public void actionElement(int iID) {
               SkillsManager.add_BonusGenocidePower(Menu_InGame_UpgradingArmy.this.iCivID);
               CFG.menuManager.updateInGame_TOP_All(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
               CFG.menuManager.rebuildInGame_UpgradingArmy();
               Menu_InGame_UpgradingArmy.this.rebuildBudgetView();
               Menu_InGame.updateOverBudget();
            }
         }
      );
      tY += menuElements.get(menuElements.size() - 1).getHeight();
      int tempMenuPosY = ImageManager.getImage(Images.top_flag_frame).getHeight() + CFG.PADDING * 4 + CFG.BUTTON_HEIGHT * 3 / 5 + CFG.PADDING * 2;
      this.initMenu(
         new SliderMenuTitle(null, CFG.BUTTON_HEIGHT * 3 / 5, true, true) {
            @Override
            public void draw(SpriteBatch oSB, int iTranslateX, int nPosX, int nPosY, int nWidth, boolean sliderMenuIsActive) {
               ImageManager.getImage(Images.dialog_title)
                  .draw2(
                     oSB,
                     nPosX + iTranslateX,
                     nPosY - this.getHeight() - ImageManager.getImage(Images.dialog_title).getHeight(),
                     nWidth - ImageManager.getImage(Images.dialog_title).getWidth(),
                     this.getHeight()
                  );
               ImageManager.getImage(Images.dialog_title)
                  .draw2(
                     oSB,
                     nPosX + nWidth - ImageManager.getImage(Images.dialog_title).getWidth() + iTranslateX,
                     nPosY - this.getHeight() - ImageManager.getImage(Images.dialog_title).getHeight(),
                     ImageManager.getImage(Images.dialog_title).getWidth(),
                     this.getHeight(),
                     true,
                     false
                  );
               oSB.setColor(new Color(0.25882354F, 0.32941177F, 0.4627451F, 0.165F));
               ImageManager.getImage(Images.line_32_off1)
                  .draw(
                     oSB,
                     nPosX + 2 + iTranslateX,
                     nPosY - this.getHeight() + 2 - ImageManager.getImage(Images.line_32_off1).getHeight(),
                     nWidth - 4,
                     this.getHeight() - 2,
                     false,
                     true
                  );
               oSB.setColor(new Color(0.25882354F, 0.32941177F, 0.4627451F, 0.375F));
               ImageManager.getImage(Images.gradient)
                  .draw(
                     oSB,
                     nPosX + 2 + iTranslateX,
                     nPosY - this.getHeight() * 2 / 3 - ImageManager.getImage(Images.gradient).getHeight(),
                     nWidth - 4,
                     this.getHeight() * 2 / 3,
                     false,
                     true
                  );
               oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.5F));
               ImageManager.getImage(Images.gradient)
                  .draw(
                     oSB,
                     nPosX + 2 + iTranslateX,
                     nPosY - CFG.PADDING - ImageManager.getImage(Images.gradient).getHeight(),
                     nWidth - 4,
                     CFG.PADDING,
                     false,
                     true
                  );
               oSB.setColor(CFG.COLOR_NEW_GAME_EDGE_LINE);
               ImageManager.getImage(Images.pix255_255_255)
                  .draw(oSB, nPosX + 2 + iTranslateX, nPosY - 1 - ImageManager.getImage(Images.pix255_255_255).getHeight(), nWidth - 4, 1);
               oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.45F));
               ImageManager.getImage(Images.slider_gradient)
                  .draw(oSB, nPosX + 2 + iTranslateX, nPosY - 1 - ImageManager.getImage(Images.slider_gradient).getHeight(), (nWidth - 4) / 2, 1);
               ImageManager.getImage(Images.slider_gradient)
                  .draw(
                     oSB,
                     nPosX + 2 + (nWidth - 4) - (nWidth - 4) / 2 + iTranslateX,
                     nPosY - 1 - ImageManager.getImage(Images.slider_gradient).getHeight(),
                     (nWidth - 4) / 2,
                     1,
                     true,
                     false
                  );
               oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.425F));
               ImageManager.getImage(Images.slider_gradient)
                  .draw(
                     oSB,
                     nPosX + CFG.PADDING * 2 + iTranslateX,
                     nPosY + 1 - this.getHeight() / 2 - ImageManager.getImage(Images.slider_gradient).getHeight(),
                     (int)((nWidth - CFG.PADDING * 6 - this.getTextWidth() * 0.8F) / 2.0F),
                     1,
                     true,
                     false
                  );
               ImageManager.getImage(Images.slider_gradient)
                  .draw(
                     oSB,
                     nPosX + nWidth / 2 + CFG.PADDING + (int)(this.getTextWidth() * 0.8F / 2.0F) + iTranslateX,
                     nPosY + 1 - this.getHeight() / 2 - ImageManager.getImage(Images.slider_gradient).getHeight(),
                     (int)((nWidth - CFG.PADDING * 6 - this.getTextWidth() * 0.8F) / 2.0F),
                     1
                  );
               oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.325F));
               ImageManager.getImage(Images.slider_gradient)
                  .draw(
                     oSB,
                     nPosX + CFG.PADDING * 2 + iTranslateX,
                     nPosY + 2 - this.getHeight() / 2 - ImageManager.getImage(Images.slider_gradient).getHeight(),
                     (int)((nWidth - CFG.PADDING * 6 - this.getTextWidth() * 0.8F) / 2.0F),
                     1,
                     true,
                     false
                  );
               ImageManager.getImage(Images.slider_gradient)
                  .draw(
                     oSB,
                     nPosX + nWidth / 2 + CFG.PADDING + (int)(this.getTextWidth() * 0.8F / 2.0F) + iTranslateX,
                     nPosY + 2 - this.getHeight() / 2 - ImageManager.getImage(Images.slider_gradient).getHeight(),
                     (int)((nWidth - CFG.PADDING * 6 - this.getTextWidth() * 0.8F) / 2.0F),
                     1
                  );
               ImageManager.getImage(Images.slider_gradient)
                  .draw(
                     oSB,
                     nPosX + CFG.PADDING * 2 + iTranslateX,
                     nPosY - this.getHeight() / 2 - ImageManager.getImage(Images.slider_gradient).getHeight(),
                     (int)((nWidth - CFG.PADDING * 6 - this.getTextWidth() * 0.8F) / 2.0F),
                     1,
                     true,
                     false
                  );
               ImageManager.getImage(Images.slider_gradient)
                  .draw(
                     oSB,
                     nPosX + nWidth / 2 + CFG.PADDING + (int)(this.getTextWidth() * 0.8F / 2.0F) + iTranslateX,
                     nPosY - this.getHeight() / 2 - ImageManager.getImage(Images.slider_gradient).getHeight(),
                     (int)((nWidth - CFG.PADDING * 6 - this.getTextWidth() * 0.8F) / 2.0F),
                     1
                  );
               oSB.setColor(Color.WHITE);
               CFG.fontMain.getData().setScale(0.8F);
               CFG.drawText(
                  oSB,
                  this.getText(),
                  nPosX + (int)(nWidth - this.getTextWidth() * 0.8F) / 2 + iTranslateX,
                  2 + nPosY - this.getHeight() + (int)(this.getHeight() - this.getTextHeight() * 0.8F) / 2,
                  Color.WHITE
               );
               CFG.fontMain.getData().setScale(1.0F);
            }
         },
         CFG.GAME_WIDTH / 2 - tempWidth / 2,
         tempMenuPosY,
         tempWidth,
         menuElements.get(menuElements.size() - 1).getPosY() + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING + tempMenuPosY
               > CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING * 2
            ? CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING * 2 - tempMenuPosY
            : menuElements.get(menuElements.size() - 1).getPosY() + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING,
         menuElements,
         true,
         true
      );
      this.updateLanguage();
      int i = 0;

      for (int j = 0; i < this.getMenuElementsSize(); j++) {
         this.getMenuElement(i).setCurrent(j % 2);
         i += 2;
      }
   }

   @Override
   public void updateLanguage() {
      try {
         this.getTitle().setText(CFG.langManager.get("UpgradingArmy"));
      } catch (NullPointerException var2) {
      }
   }

   @Override
   public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      oSB.setColor(Color.WHITE);
      ImageManager.getImage(Images.new_game_top_edge)
         .draw2(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.new_game_top_edge).getHeight() + iTranslateY,
            this.getWidth() - ImageManager.getImage(Images.new_game_top_edge).getWidth(),
            this.getHeight() + 2,
            false,
            true
         );
      ImageManager.getImage(Images.new_game_top_edge)
         .draw2(
            oSB,
            this.getPosX() + this.getWidth() - ImageManager.getImage(Images.new_game_top_edge).getWidth() + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.new_game_top_edge).getHeight() + iTranslateY,
            ImageManager.getImage(Images.new_game_top_edge).getWidth(),
            this.getHeight() + 2,
            true,
            true
         );
      oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.45F));
      ImageManager.getImage(Images.gradient)
         .draw(
            oSB,
            this.getPosX() + 2 + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY,
            this.getWidth() - 4,
            this.getHeight() / 4
         );
      ImageManager.getImage(Images.pix255_255_255)
         .draw(
            oSB,
            this.getPosX() + 2 + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY,
            this.getWidth() - 4,
            1
         );
      oSB.setColor(Color.WHITE);
      this.beginClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
      this.drawMenu(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
      oSB.setColor(Color.WHITE);
      this.endClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
   }

   @Override
   public void drawScrollPos(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      if (sliderMenuIsActive) {
         super.drawScrollPos(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
      }
   }

   @Override
   public final void actionElement(int iID) {
      this.getMenuElement(iID).actionElement(iID);
   }

   public final int getW() {
      return this.getWidth();
   }

   public final int getElementW() {
      return this.getW();
   }

   @Override
   public void setVisible(boolean visible) {
      super.setVisible(visible);
      if (!visible) {
         for (int i = 0; i < this.getMenuElementsSize(); i++) {
            this.getMenuElement(i).setVisible(false);
         }
      }
   }

   public void rebuildBudgetView() {
      if (CFG.menuManager.getVisible_InGame_Budget()) {
         CFG.menuManager.setVisible_InGame_Budget(true);
         Menu_InGame_FlagAction_Bot.lTime = 1L;
      } else if (CFG.menuManager.getVisible_InGame_FlagAction()) {
         CFG.menuManager.rebuildInGame_FlagActionLeft();
      } else {
         CFG.game_NextTurnUpdate.getBalance_UpdateBudget_Prepare(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
      }

      CFG.menuManager.setOrderOfTechPoints();
   }
}
