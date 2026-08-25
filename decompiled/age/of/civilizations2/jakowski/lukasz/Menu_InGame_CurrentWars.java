package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

public class Menu_InGame_CurrentWars extends SliderMenu {
   public final float FONT_SCALE = 0.7F;
   public static final int ANIMATION_TIME = 135;
   public static long lTime = 0L;
   public static boolean hideAnimation = true;

   public Menu_InGame_CurrentWars(int init) {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      int tempWidth = CFG.CIV_INFO_MENU_WIDTH * 2;
      int tY = CFG.PADDING;
      menuElements.add(new Button_Flag_JustFrame(CFG.PADDING, tY, true));
      tY += menuElements.get(menuElements.size() - 1).getHeight();
      int tempMenuPosY = ImageManager.getImage(Images.top_left2).getHeight() + CFG.PADDING * 2;
      this.initMenu(
         new SliderMenuTitle(CFG.langManager.get("Wars"), CFG.BUTTON_HEIGHT * 3 / 5, true, true),
         CFG.GAME_WIDTH / 2 - tempWidth / 2,
         tempMenuPosY,
         tempWidth,
         menuElements.get(menuElements.size() - 1).getPosY() + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING + tempMenuPosY
               > CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING * 2
            ? Math.max(CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING * 2 - tempMenuPosY, (CFG.TEXT_HEIGHT + CFG.PADDING * 2) * 6)
            : menuElements.get(menuElements.size() - 1).getPosY() + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING,
         menuElements,
         false,
         true
      );
      this.updateLanguage();
   }

   public Menu_InGame_CurrentWars() {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      int tempMaxTextW = 1;

      try {
         CFG.glyphLayout.setText(CFG.fontMain, "+100% ");
         tempMaxTextW = (int)(CFG.glyphLayout.width * 0.7F);
      } catch (NullPointerException var11) {
      }

      int tMenuWidth = ImageManager.getImage(Images.diplo_war).getWidth() / 2 + CFG.PADDING + CFG.CIV_FLAG_WIDTH + CFG.PADDING + tempMaxTextW + CFG.PADDING;
      int tPosY = 0;
      ArrayList<Integer> tempWars = new ArrayList<>();

      for (int i2 = 1; i2 < CFG.game.getCivsSize(); i2++) {
         int tWarID;
         if (i2 != CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()
            && CFG.game.getCivsAtWar(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), i2)
            && (tWarID = CFG.game.getWarID(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), i2)) >= 0) {
            boolean added = false;

            for (int j = 0; j < tempWars.size(); j++) {
               if (tempWars.get(j) == tWarID) {
                  added = true;
                  break;
               }
            }

            if (!added) {
               tempWars.add(tWarID);
            }
         }
      }

      ArrayList tempSorted = new ArrayList();

      while (tempWars.size() > 0) {
         int tBest = 0;

         for (int i = 1; i < tempWars.size(); i++) {
            if (CFG.game.getWar(tempWars.get(i)).getCasualties_Aggressors() + CFG.game.getWar(tempWars.get(i)).getCasualties_Defenders()
               > CFG.game.getWar(tempWars.get(tBest)).getCasualties_Aggressors() + CFG.game.getWar(tempWars.get(tBest)).getCasualties_Defenders()) {
               tBest = i;
            }
         }

         tempSorted.add(tempWars.get(tBest));
         tempWars.remove(tBest);
      }

      if (SaveManager.gameWillBeSavedInNextTurn()) {
         menuElements.add(new Text_Outliner_SaveGame(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), 2, tPosY, tMenuWidth - 2) {});
         tPosY += menuElements.get(menuElements.size() - 1).getHeight();
      }

      boolean research = true;

      for (int ix = 0;
         ix < CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCivilization_Diplomacy_GameData().messageBox.getMessagesSize();
         ix++
      ) {
         if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCivilization_Diplomacy_GameData().messageBox.getMessage(ix).messageType
            == Message_Type.TECHNOLOGY_RESEARCHED) {
            menuElements.add(
               new Text_Outliner_TechLevel(
                  CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(),
                  "" + (int)(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getTechnologyLevel() * 100.0F) / 100.0F,
                  2,
                  tPosY,
                  tMenuWidth - 2
               ) {}
            );
            tPosY += menuElements.get(menuElements.size() - 1).getHeight();
            break;
         }
      }

      if (research) {
         String BadBoy = String.format("%.3f", CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).civGameData.civAggresionLevel * 100.0F);
         menuElements.add(new Text_Outliner_BadBoy(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), "" + BadBoy, 2, tPosY, tMenuWidth - 2) {});
         tPosY += menuElements.get(menuElements.size() - 1).getHeight();
      }

      if (research) {
         menuElements.add(
            new Text_Outliner_ResearchProgress(
               CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(),
               ""
                  + CFG.getPercentage_Max100(
                     (int)CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getResearchProgress(),
                     TechnologyManager.getResearch_NextLevel(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()),
                     4
                  )
                  + "%",
               2,
               tPosY,
               tMenuWidth - 2
            ) {}
         );
         tPosY += menuElements.get(menuElements.size() - 1).getHeight();
      }

      if (!research
         && tempSorted.size() <= 0
         && CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).civGameData.civPlans.warPreparations.size() <= 0
         && !CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getIsPartOfHolyRomanEmpire()) {
         menuElements.add(new Text_Outliner("NoWars", CFG.PADDING * 2, 2, tPosY, tMenuWidth - 2, CFG.TEXT_HEIGHT + CFG.PADDING * 2) {});
         tPosY += menuElements.get(menuElements.size() - 1).getHeight();
         menuElements.get(menuElements.size() - 1).setVisible(false);
      } else {
         for (int var13 = 0; var13 < tempSorted.size(); var13++) {
            menuElements.add(new Text_Outliner_CurrentWar((Integer)tempSorted.get(var13), 2, tPosY, tMenuWidth - 2) {});
            tPosY += menuElements.get(menuElements.size() - 1).getHeight();
         }

         for (int var14 = 0; var14 < CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).civGameData.civPlans.iWarPreparationsSize; var14++) {
            menuElements.add(
               new Text_Outliner_WarPreparations(
                  CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).civGameData.civPlans.warPreparations.get(var14).onCivID,
                  CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).civGameData.civPlans.warPreparations.get(var14).iNumOfTurnsLeft,
                  2,
                  tPosY,
                  tMenuWidth - 2
               ) {}
            );
            tPosY += menuElements.get(menuElements.size() - 1).getHeight();
         }

         for (int var15 = 0; var15 < CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).civGameData.civPlans.iCasusBelliSize; var15++) {
            menuElements.add(
               new Text_Outliner_CB(
                  CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).civGameData.civPlans.casusBelli.get(var15).onCivID,
                  CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).civGameData.civPlans.casusBelli.get(var15).iNumOfTurnsLeft,
                  2,
                  tPosY,
                  tMenuWidth - 2
               ) {}
            );
            tPosY += menuElements.get(menuElements.size() - 1).getHeight();
         }

         if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getIsPartOfHolyRomanEmpire()) {
            menuElements.add(new Text_Outliner_PartOfHRE(CFG.holyRomanEmpire_Manager.getHRE().getEmperor(), 2, tPosY, tMenuWidth - 2) {});
            tPosY += menuElements.get(menuElements.size() - 1).getHeight();
         }
      }

      this.initMenu(
         null,
         CFG.GAME_WIDTH - tMenuWidth,
         CFG.menuManager.getVisible_Menu_InGame_Outliner()
            ? CFG.menuManager.getMenu_InGame_Outliner().getPosY() + CFG.menuManager.getMenu_InGame_Outliner().getHeight()
            : ImageManager.getImage(Images.top_left2).getHeight() + CFG.PADDING * 2,
         tMenuWidth,
         Math.min(
               Math.max(CFG.TEXT_HEIGHT + CFG.PADDING * 4, CFG.CIV_FLAG_HEIGHT + CFG.PADDING * 4) * (CFG.isDesktop() ? 6 : 5),
               menuElements.size() > 0 ? menuElements.get(menuElements.size() - 1).getPosY() + menuElements.get(menuElements.size() - 1).getHeight() : 0
            )
            + 1,
         menuElements,
         false,
         false
      );

      for (int var16 = 0; var16 < this.getMenuElementsSize(); var16++) {
         this.getMenuElement(var16).setCurrent(var16 % 2);
      }

      this.updateLanguage();
   }

   @Override
   public void updateLanguage() {
   }

   @Override
   public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      if (lTime + 135L >= System.currentTimeMillis()) {
         int var5;
         int var6;
         iTranslateX = hideAnimation
            ? (var5 = iTranslateX + (int)(this.getWidth() * ((float)(System.currentTimeMillis() - lTime) / 135.0F)))
            : (var6 = iTranslateX + (this.getWidth() - (int)(this.getWidth() * ((float)(System.currentTimeMillis() - lTime) / 135.0F))));
         CFG.setRender_3(true);
      } else if (hideAnimation) {
         super.setVisible(false);
         return;
      }

      super.draw(oSB, iTranslateX, 1 + iTranslateY, sliderMenuIsActive);
      oSB.setColor(Color.WHITE);
   }

   @Override
   public void drawScrollPos(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      if (sliderMenuIsActive) {
         super.drawScrollPos(oSB, iTranslateX + CFG.PADDING, iTranslateY, sliderMenuIsActive);
      }
   }

   @Override
   public void drawCloseButton(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      this.getCloseButtonImage(sliderMenuIsActive)
         .draw(
            oSB,
            this.getPosX() + this.getWidth() - ImageManager.getImage(Images.btn_close).getWidth() * 3 / 5 + iTranslateX,
            this.getPosY() - this.getTitle().getHeight() - ImageManager.getImage(Images.btn_close).getHeight() + iTranslateY,
            ImageManager.getImage(Images.btn_close).getWidth() * 3 / 5,
            ImageManager.getImage(Images.btn_close).getHeight() * 3 / 5
         );
   }

   @Override
   public void actionElement(int iID) {
      this.getMenuElement(iID).actionElement(iID);
   }

   @Override
   public void setVisible(boolean visible) {
      if (visible) {
         super.setVisible(visible);
         this.setHideAnimation(false);
      } else {
         this.setHideAnimation(true);
      }
   }

   public final void setHideAnimation(boolean hideAnimation) {
      if (hideAnimation != Menu_InGame_CurrentWars.hideAnimation) {
         lTime = lTime > System.currentTimeMillis() - 135L
            ? System.currentTimeMillis() - (135L - (System.currentTimeMillis() - lTime))
            : System.currentTimeMillis();
         CFG.setRender_3(true);
      }

      Menu_InGame_CurrentWars.hideAnimation = hideAnimation;
   }
}
