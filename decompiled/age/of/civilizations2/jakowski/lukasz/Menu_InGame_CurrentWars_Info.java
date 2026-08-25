package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

public class Menu_InGame_CurrentWars_Info extends SliderMenu {
   public final float FONT_SCALE = 0.7F;
   public static final int ANIMATION_TIME = 135;
   public static long lTime = 0L;
   public static boolean hideAnimation = true;

   public Menu_InGame_CurrentWars_Info(int init) {
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
            ? Math.max(
               CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING * 2 - CFG.PADDING * 2 - tempMenuPosY,
               (CFG.TEXT_HEIGHT + CFG.PADDING * 2) * (CFG.isDesktop() ? 10 : 6)
            )
            : menuElements.get(menuElements.size() - 1).getPosY() + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING,
         menuElements,
         false,
         true
      );
      this.updateLanguage();
   }

   public Menu_InGame_CurrentWars_Info() {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      int tempMaxTextW = 1;

      try {
         CFG.glyphLayout.setText(CFG.fontMain, "+100% ");
         tempMaxTextW = (int)(CFG.glyphLayout.width * 0.7F);
      } catch (NullPointerException var10) {
      }

      int tMenuWidth = ImageManager.getImage(Images.diplo_war).getWidth() / 2 + CFG.PADDING + CFG.CIV_FLAG_WIDTH + CFG.PADDING + tempMaxTextW + CFG.PADDING;
      int tPosY = 0;
      if (CFG.historyManager.getHistorySize() > 0) {
         int iSize;
         int i = iSize = CFG.historyManager.getHistorySize() - 1;

         for (int tTurn = 0; i > iSize - 3 && i >= 0; tTurn++) {
            int jSize = CFG.historyManager.getHistoryTurnSize(i);

            for (int j = 0; j < jSize; j++) {
               if (CFG.historyManager.getHistory(i, j).historyLog_Type == HistoryLog_Types.UNION
                  && CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetCivilization(CFG.historyManager.getHistory(i, j).iCivA)) {
                  int var10004 = Game_Calendar.TURN_ID - tTurn;
                  menuElements.add(new Text_Outliner_DiploInfo_Union(CFG.historyManager.getHistory(i, j).iCivA, var10004, 2, tPosY, tMenuWidth - 2));
                  tPosY += menuElements.get(menuElements.size() - 1).getHeight();
               }
            }

            jSize = CFG.historyManager.getHistoryTurnSize(i);

            for (int var12 = 0; var12 < jSize; var12++) {
               if (CFG.historyManager.getHistory(i, var12).historyLog_Type == HistoryLog_Types.WAR_DECLARAION
                  && (
                     CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetCivilization(CFG.historyManager.getHistory(i, var12).iCivA)
                        || CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetCivilization(CFG.historyManager.getHistory(i, var12).iCivB)
                  )) {
                  int var10005 = Game_Calendar.TURN_ID - tTurn;
                  menuElements.add(
                     new Text_Outliner_DiploInfo_War(
                        CFG.historyManager.getHistory(i, var12).iCivA, CFG.historyManager.getHistory(i, var12).iCivB, var10005, 2, tPosY, tMenuWidth - 2
                     )
                  );
                  tPosY += menuElements.get(menuElements.size() - 1).getHeight();
               }
            }

            jSize = CFG.historyManager.getHistoryTurnSize(i);

            for (int var13 = 0; var13 < jSize; var13++) {
               if (CFG.historyManager.getHistory(i, var13).historyLog_Type == HistoryLog_Types.TRUCE
                  && (
                     CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetCivilization(CFG.historyManager.getHistory(i, var13).iCivA)
                        || CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetCivilization(CFG.historyManager.getHistory(i, var13).iCivB)
                  )) {
                  int var40 = Game_Calendar.TURN_ID - tTurn;
                  menuElements.add(
                     new Text_Outliner_DiploInfo_Truce(
                        CFG.historyManager.getHistory(i, var13).iCivA, CFG.historyManager.getHistory(i, var13).iCivB, var40, 2, tPosY, tMenuWidth - 2
                     )
                  );
                  tPosY += menuElements.get(menuElements.size() - 1).getHeight();
               }
            }

            jSize = CFG.historyManager.getHistoryTurnSize(i);

            for (int var14 = 0; var14 < jSize; var14++) {
               if (CFG.historyManager.getHistory(i, var14).historyLog_Type == HistoryLog_Types.NEW_COONY
                  && CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetCivilization(CFG.historyManager.getHistory(i, var14).iCivA)) {
                  int var41 = Game_Calendar.TURN_ID - tTurn;
                  menuElements.add(
                     new Text_Outliner_DiploInfo_NewColony(
                        CFG.historyManager.getHistory(i, var14).iCivA, CFG.historyManager.getHistory(i, var14).iCivB, var41, 2, tPosY, tMenuWidth - 2
                     )
                  );
                  tPosY += menuElements.get(menuElements.size() - 1).getHeight();
               }
            }

            jSize = CFG.historyManager.getHistoryTurnSize(i);

            for (int var15 = 0; var15 < jSize; var15++) {
               if (CFG.historyManager.getHistory(i, var15).historyLog_Type == HistoryLog_Types.ANNEXATION
                  && (
                     CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetCivilization(CFG.historyManager.getHistory(i, var15).iCivA)
                        || CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetCivilization(CFG.historyManager.getHistory(i, var15).iCivB)
                  )) {
                  int var42 = Game_Calendar.TURN_ID - tTurn;
                  menuElements.add(
                     new Text_Outliner_DiploInfo_Annexation(
                        CFG.historyManager.getHistory(i, var15).iCivA, CFG.historyManager.getHistory(i, var15).iCivB, var42, 2, tPosY, tMenuWidth - 2
                     )
                  );
                  tPosY += menuElements.get(menuElements.size() - 1).getHeight();
               }
            }

            jSize = CFG.historyManager.getHistoryTurnSize(i);

            for (int var16 = 0; var16 < jSize; var16++) {
               if (CFG.historyManager.getHistory(i, var16).historyLog_Type == HistoryLog_Types.DISEASE
                  && CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetCivilization(CFG.historyManager.getHistory(i, var16).iCivA)) {
                  menuElements.add(
                     new Text_Outliner_DiploInfo_Disease(
                        CFG.historyManager.getHistory(i, var16).iCivA,
                        CFG.historyManager.getHistory(i, var16).iCivB,
                        CFG.historyManager.getHistory(i, var16).getName(),
                        Game_Calendar.TURN_ID - tTurn,
                        2,
                        tPosY,
                        tMenuWidth - 2
                     )
                  );
                  tPosY += menuElements.get(menuElements.size() - 1).getHeight();
               }
            }

            jSize = CFG.historyManager.getHistoryTurnSize(i);

            for (int var17 = 0; var17 < jSize; var17++) {
               if (CFG.historyManager.getHistory(i, var17).historyLog_Type == HistoryLog_Types.JOINS_ALLIANCE
                  && CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetCivilization(CFG.historyManager.getHistory(i, var17).iCivA)) {
                  int var43 = Game_Calendar.TURN_ID - tTurn;
                  menuElements.add(
                     new Text_Outliner_DiploInfo_Alliance(
                        CFG.historyManager.getHistory(i, var17).iCivA, CFG.historyManager.getHistory(i, var17).iCivB, var43, 2, tPosY, tMenuWidth - 2
                     )
                  );
                  tPosY += menuElements.get(menuElements.size() - 1).getHeight();
               }
            }

            jSize = CFG.historyManager.getHistoryTurnSize(i);

            for (int var18 = 0; var18 < jSize; var18++) {
               if (CFG.historyManager.getHistory(i, var18).historyLog_Type == HistoryLog_Types.LEAVES_ALLIANCE
                  && CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetCivilization(CFG.historyManager.getHistory(i, var18).iCivA)) {
                  menuElements.add(
                     new Text_Outliner_DiploInfo_AllianceLeaves(
                        CFG.historyManager.getHistory(i, var18).iCivA,
                        CFG.historyManager.getHistory(i, var18).getName(),
                        Game_Calendar.TURN_ID - tTurn,
                        2,
                        tPosY,
                        tMenuWidth - 2
                     )
                  );
                  tPosY += menuElements.get(menuElements.size() - 1).getHeight();
               }
            }

            jSize = CFG.historyManager.getHistoryTurnSize(i);

            for (int var19 = 0; var19 < jSize; var19++) {
               if (CFG.historyManager.getHistory(i, var19).historyLog_Type == HistoryLog_Types.IS_VASSAL
                  && (
                     CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetCivilization(CFG.historyManager.getHistory(i, var19).iCivA)
                        || CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetCivilization(CFG.historyManager.getHistory(i, var19).iCivB)
                  )) {
                  int var44 = Game_Calendar.TURN_ID - tTurn;
                  menuElements.add(
                     new Text_Outliner_DiploInfo_IsVassal(
                        CFG.historyManager.getHistory(i, var19).iCivA, CFG.historyManager.getHistory(i, var19).iCivB, var44, 2, tPosY, tMenuWidth - 2
                     )
                  );
                  tPosY += menuElements.get(menuElements.size() - 1).getHeight();
               }
            }

            jSize = CFG.historyManager.getHistoryTurnSize(i);

            for (int var20 = 0; var20 < jSize; var20++) {
               if (CFG.historyManager.getHistory(i, var20).historyLog_Type == HistoryLog_Types.IS_NOT_VASSAL
                  && (
                     CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetCivilization(CFG.historyManager.getHistory(i, var20).iCivA)
                        || CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetCivilization(CFG.historyManager.getHistory(i, var20).iCivB)
                  )) {
                  tPosY += menuElements.get(menuElements.size() - 1).getHeight();
               }
            }

            jSize = CFG.historyManager.getHistoryTurnSize(i);

            for (int var21 = 0; var21 < jSize; var21++) {
               if (CFG.historyManager.getHistory(i, var21).historyLog_Type == HistoryLog_Types.FRIENDLY_CIVS
                  && (
                     CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetCivilization(CFG.historyManager.getHistory(i, var21).iCivA)
                        || CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetCivilization(CFG.historyManager.getHistory(i, var21).iCivB)
                  )) {
                  int var45 = Game_Calendar.TURN_ID - tTurn;
                  menuElements.add(
                     new Text_Outliner_DiploInfo_FriendlyCivs(
                        CFG.historyManager.getHistory(i, var21).iCivA, CFG.historyManager.getHistory(i, var21).iCivB, var45, 2, tPosY, tMenuWidth - 2
                     )
                  );
                  tPosY += menuElements.get(menuElements.size() - 1).getHeight();
               }
            }

            jSize = CFG.historyManager.getHistoryTurnSize(i);

            for (int var22 = 0; var22 < jSize; var22++) {
               if (CFG.historyManager.getHistory(i, var22).historyLog_Type == HistoryLog_Types.SIGNED_DEFENSIVE_PACT
                  && (
                     CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetCivilization(CFG.historyManager.getHistory(i, var22).iCivA)
                        || CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetCivilization(CFG.historyManager.getHistory(i, var22).iCivB)
                  )) {
                  int var46 = Game_Calendar.TURN_ID - tTurn;
                  menuElements.add(
                     new Text_Outliner_DiploInfo_SignedDefensivePact(
                        CFG.historyManager.getHistory(i, var22).iCivA, CFG.historyManager.getHistory(i, var22).iCivB, var46, 2, tPosY, tMenuWidth - 2
                     )
                  );
                  tPosY += menuElements.get(menuElements.size() - 1).getHeight();
               }
            }

            jSize = CFG.historyManager.getHistoryTurnSize(i);

            for (int var23 = 0; var23 < jSize; var23++) {
               if (CFG.historyManager.getHistory(i, var23).historyLog_Type == HistoryLog_Types.SIGNED_NON_AGGRESSION_PACT
                  && (
                     CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetCivilization(CFG.historyManager.getHistory(i, var23).iCivA)
                        || CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetCivilization(CFG.historyManager.getHistory(i, var23).iCivB)
                  )) {
                  int var47 = Game_Calendar.TURN_ID - tTurn;
                  menuElements.add(
                     new Text_Outliner_DiploInfo_SignedNonAggressionPact(
                        CFG.historyManager.getHistory(i, var23).iCivA, CFG.historyManager.getHistory(i, var23).iCivB, var47, 2, tPosY, tMenuWidth - 2
                     )
                  );
                  tPosY += menuElements.get(menuElements.size() - 1).getHeight();
               }
            }

            jSize = CFG.historyManager.getHistoryTurnSize(i);

            for (int var24 = 0; var24 < jSize; var24++) {
               if (CFG.historyManager.getHistory(i, var24).historyLog_Type == HistoryLog_Types.GUARANTEE
                  && (
                     CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetCivilization(CFG.historyManager.getHistory(i, var24).iCivA)
                        || CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetCivilization(CFG.historyManager.getHistory(i, var24).iCivB)
                  )) {
                  int var48 = Game_Calendar.TURN_ID - tTurn;
                  menuElements.add(
                     new Text_Outliner_DiploInfo_Guarantee(
                        CFG.historyManager.getHistory(i, var24).iCivA, CFG.historyManager.getHistory(i, var24).iCivB, var48, 2, tPosY, tMenuWidth - 2
                     )
                  );
                  tPosY += menuElements.get(menuElements.size() - 1).getHeight();
               }
            }

            jSize = CFG.historyManager.getHistoryTurnSize(i);

            for (int var25 = 0; var25 < jSize; var25++) {
               if (CFG.historyManager.getHistory(i, var25).historyLog_Type == HistoryLog_Types.HAVE_MILITARY_ACCESS
                  && (
                     CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetCivilization(CFG.historyManager.getHistory(i, var25).iCivA)
                        || CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetCivilization(CFG.historyManager.getHistory(i, var25).iCivB)
                  )) {
                  int var49 = Game_Calendar.TURN_ID - tTurn;
                  menuElements.add(
                     new Text_Outliner_DiploInfo_HaveMilitaryAccess(
                        CFG.historyManager.getHistory(i, var25).iCivA, CFG.historyManager.getHistory(i, var25).iCivB, var49, 2, tPosY, tMenuWidth - 2
                     )
                  );
                  tPosY += menuElements.get(menuElements.size() - 1).getHeight();
               }
            }

            i--;
         }
      }

      this.initMenu(
         null,
         CFG.GAME_WIDTH - tMenuWidth,
         -1
            + (
               CFG.menuManager.getVisible_Menu_InGame_CurrentWars()
                  ? CFG.menuManager.getMenu_InGame_CurrentWars().getPosY() + CFG.menuManager.getMenu_InGame_CurrentWars().getHeight()
                  : (
                     CFG.menuManager.getVisible_Menu_InGame_Outliner()
                        ? CFG.menuManager.getMenu_InGame_Outliner().getPosY() + CFG.menuManager.getMenu_InGame_Outliner().getHeight()
                        : ImageManager.getImage(Images.top_left2).getHeight() + CFG.PADDING * 2
                  )
            ),
         tMenuWidth,
         Math.min(
               (CFG.CIV_FLAG_HEIGHT + CFG.PADDING * 2) * (CFG.isDesktop() ? 5 : 3),
               menuElements.size() > 0 ? menuElements.get(menuElements.size() - 1).getPosY() + menuElements.get(menuElements.size() - 1).getHeight() : 0
            )
            + 1,
         menuElements,
         false,
         false
      );

      for (int i = 0; i < this.getMenuElementsSize(); i++) {
         this.getMenuElement(i).setCurrent((CFG.menuManager.getMenu_InGame_CurrentWars().getMenuElementsSize() + i) % 2);
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
      if (hideAnimation != Menu_InGame_CurrentWars_Info.hideAnimation) {
         lTime = lTime > System.currentTimeMillis() - 135L
            ? System.currentTimeMillis() - (135L - (System.currentTimeMillis() - lTime))
            : System.currentTimeMillis();
         CFG.setRender_3(true);
      }

      Menu_InGame_CurrentWars_Info.hideAnimation = hideAnimation;
   }
}
