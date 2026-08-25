package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

public class Menu_InGame_Messages extends SliderMenu {
   public static int VIEW_BEFORE = -1;
   public static long ANIMATION_TIME = 0L;
   public static long ANIMATION_TIMER = 325L;
   public static boolean START_ANIMATION = false;
   public static boolean IN_ANIMATION = false;

   public Menu_InGame_Messages() {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      int tY = 0;
      int tX = 0;

      for (int i = 0;
         i < CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCivilization_Diplomacy_GameData().messageBox.getMessagesSize();
         i++
      ) {
         if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).messageType
               == Message_Type.PEACE_TREATY_LIST_OF_DEMANDS
            && CFG.game
                  .getPeaceTreaty_GameDataID(
                     CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCivilization_Diplomacy_GameData().messageBox.getMessage(i).TAG
                  )
               < 0) {
            CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCivilization_Diplomacy_GameData().messageBox.removeMessage(i);
         }
      }

      for (int var5 = CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCivilization_Diplomacy_GameData().messageBox.getMessagesSize() - 1;
         var5 >= 0;
         var5--
      ) {
         menuElements.add(
            new Button_Message(
               tX,
               tY,
               var5,
               CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCivilization_Diplomacy_GameData().messageBox.getMessage(var5).iFromCivID,
               CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCivilization_Diplomacy_GameData().messageBox.getMessage(var5).getImageID(),
               CFG.game
                  .getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
                  .getCivilization_Diplomacy_GameData()
                  .messageBox
                  .getMessage(var5)
                  .getBGImageID()
            )
         );
         tX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
      }

      this.initMenu(
         null,
         CFG.topBox.iFlagX * 2 + ImageManager.getImage(Images.top_flag_frame).getWidth() + CFG.PADDING,
         ImageManager.getImage(Images.top_left2_sha).getHeight() + CFG.PADDING,
         CFG.GAME_WIDTH - (CFG.topBox.iFlagX * 2 + ImageManager.getImage(Images.top_flag_frame).getWidth() + CFG.PADDING),
         (int)(CFG.BUTTON_HEIGHT * 0.6F) + 1,
         menuElements
      );
      this.updateLanguage();
   }

   @Override
   public void updateLanguage() {
   }

   @Override
   public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      this.beginClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
      if (START_ANIMATION) {
         ANIMATION_TIME = System.currentTimeMillis() - ANIMATION_TIMER * 2L / 5L;
         IN_ANIMATION = true;
         START_ANIMATION = false;
      }

      if (IN_ANIMATION) {
         if (ANIMATION_TIME + ANIMATION_TIMER >= System.currentTimeMillis()) {
            iTranslateY += -this.getHeight() + (int)(this.getHeight() * ((float)(System.currentTimeMillis() - ANIMATION_TIME) / (float)ANIMATION_TIMER));
            CFG.setRender_3(true);
         } else {
            IN_ANIMATION = false;
         }
      }

      this.drawMenu(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
      this.endClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
   }

   @Override
   public final void actionElement(int iID) {
      if (CFG.gameAction.getActiveTurnState() == Game_Action.TurnStates.INPUT_ORDERS) {
         Message_Type tempMessageType = CFG.game
            .getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
            .getCivilization_Diplomacy_GameData()
            .messageBox
            .getMessage(
               CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCivilization_Diplomacy_GameData().messageBox.getMessagesSize() - 1 - iID
            )
            .messageType;
         if (CFG.game
                  .getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
                  .getCivilization_Diplomacy_GameData()
                  .messageBox
                  .getMessage(
                     CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCivilization_Diplomacy_GameData().messageBox.getMessagesSize()
                        - 1
                        - iID
                  )
                  .iFromCivID
               != CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()
            && tempMessageType != Message_Type.HIGH_INFLATION) {
            try {
               if (CFG.game
                     .getCiv(
                        CFG.game
                           .getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
                           .getCivilization_Diplomacy_GameData()
                           .messageBox
                           .getMessage(
                              CFG.game
                                    .getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
                                    .getCivilization_Diplomacy_GameData()
                                    .messageBox
                                    .getMessagesSize()
                                 - 1
                                 - iID
                           )
                           .iFromCivID
                     )
                     .getNumOfProvinces()
                  > 0) {
                  if (CFG.FOG_OF_WAR == 2) {
                     if (CFG.game
                           .getProvince(
                              CFG.game
                                 .getCiv(
                                    CFG.game
                                       .getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
                                       .getCivilization_Diplomacy_GameData()
                                       .messageBox
                                       .getMessage(
                                          CFG.game
                                                .getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
                                                .getCivilization_Diplomacy_GameData()
                                                .messageBox
                                                .getMessagesSize()
                                             - 1
                                             - iID
                                       )
                                       .iFromCivID
                                 )
                                 .getCapitalProvinceID()
                           )
                           .getCivID()
                        == CFG.game
                           .getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
                           .getCivilization_Diplomacy_GameData()
                           .messageBox
                           .getMessage(
                              CFG.game
                                    .getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
                                    .getCivilization_Diplomacy_GameData()
                                    .messageBox
                                    .getMessagesSize()
                                 - 1
                                 - iID
                           )
                           .iFromCivID) {
                        CFG.game
                           .getPlayer(CFG.PLAYER_TURNID)
                           .setMetProvince(
                              CFG.game
                                 .getCiv(
                                    CFG.game
                                       .getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
                                       .getCivilization_Diplomacy_GameData()
                                       .messageBox
                                       .getMessage(
                                          CFG.game
                                                .getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
                                                .getCivilization_Diplomacy_GameData()
                                                .messageBox
                                                .getMessagesSize()
                                             - 1
                                             - iID
                                       )
                                       .iFromCivID
                                 )
                                 .getCapitalProvinceID(),
                              true
                           );
                        CFG.game
                           .getPlayer(CFG.PLAYER_TURNID)
                           .setMetCivilization(
                              CFG.game
                                 .getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
                                 .getCivilization_Diplomacy_GameData()
                                 .messageBox
                                 .getMessage(
                                    CFG.game
                                          .getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
                                          .getCivilization_Diplomacy_GameData()
                                          .messageBox
                                          .getMessagesSize()
                                       - 1
                                       - iID
                                 )
                                 .iFromCivID,
                              true
                           );
                        CFG.game
                           .setActiveProvinceID(
                              CFG.game
                                 .getCiv(
                                    CFG.game
                                       .getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
                                       .getCivilization_Diplomacy_GameData()
                                       .messageBox
                                       .getMessage(
                                          CFG.game
                                                .getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
                                                .getCivilization_Diplomacy_GameData()
                                                .messageBox
                                                .getMessagesSize()
                                             - 1
                                             - iID
                                       )
                                       .iFromCivID
                                 )
                                 .getCapitalProvinceID()
                           );
                     } else {
                        CFG.game
                           .getPlayer(CFG.PLAYER_TURNID)
                           .setMetProvince(
                              CFG.game
                                 .getCiv(
                                    CFG.game
                                       .getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
                                       .getCivilization_Diplomacy_GameData()
                                       .messageBox
                                       .getMessage(
                                          CFG.game
                                                .getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
                                                .getCivilization_Diplomacy_GameData()
                                                .messageBox
                                                .getMessagesSize()
                                             - 1
                                             - iID
                                       )
                                       .iFromCivID
                                 )
                                 .getProvinceID(0),
                              true
                           );
                        CFG.game
                           .getPlayer(CFG.PLAYER_TURNID)
                           .setMetCivilization(
                              CFG.game
                                 .getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
                                 .getCivilization_Diplomacy_GameData()
                                 .messageBox
                                 .getMessage(
                                    CFG.game
                                          .getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
                                          .getCivilization_Diplomacy_GameData()
                                          .messageBox
                                          .getMessagesSize()
                                       - 1
                                       - iID
                                 )
                                 .iFromCivID,
                              true
                           );
                        CFG.game
                           .setActiveProvinceID(
                              CFG.game
                                 .getCiv(
                                    CFG.game
                                       .getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
                                       .getCivilization_Diplomacy_GameData()
                                       .messageBox
                                       .getMessage(
                                          CFG.game
                                                .getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
                                                .getCivilization_Diplomacy_GameData()
                                                .messageBox
                                                .getMessagesSize()
                                             - 1
                                             - iID
                                       )
                                       .iFromCivID
                                 )
                                 .getProvinceID(0)
                           );
                     }
                  } else if (CFG.game
                        .getProvince(
                           CFG.game
                              .getCiv(
                                 CFG.game
                                    .getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
                                    .getCivilization_Diplomacy_GameData()
                                    .messageBox
                                    .getMessage(
                                       CFG.game
                                             .getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
                                             .getCivilization_Diplomacy_GameData()
                                             .messageBox
                                             .getMessagesSize()
                                          - 1
                                          - iID
                                    )
                                    .iFromCivID
                              )
                              .getCapitalProvinceID()
                        )
                        .getCivID()
                     == CFG.game
                        .getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
                        .getCivilization_Diplomacy_GameData()
                        .messageBox
                        .getMessage(
                           CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCivilization_Diplomacy_GameData().messageBox.getMessagesSize()
                              - 1
                              - iID
                        )
                        .iFromCivID) {
                     CFG.game
                        .setActiveProvinceID(
                           CFG.game
                              .getCiv(
                                 CFG.game
                                    .getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
                                    .getCivilization_Diplomacy_GameData()
                                    .messageBox
                                    .getMessage(
                                       CFG.game
                                             .getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
                                             .getCivilization_Diplomacy_GameData()
                                             .messageBox
                                             .getMessagesSize()
                                          - 1
                                          - iID
                                    )
                                    .iFromCivID
                              )
                              .getCapitalProvinceID()
                        );
                  } else {
                     CFG.game
                        .setActiveProvinceID(
                           CFG.game
                              .getCiv(
                                 CFG.game
                                    .getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
                                    .getCivilization_Diplomacy_GameData()
                                    .messageBox
                                    .getMessage(
                                       CFG.game
                                             .getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
                                             .getCivilization_Diplomacy_GameData()
                                             .messageBox
                                             .getMessagesSize()
                                          - 1
                                          - iID
                                    )
                                    .iFromCivID
                              )
                              .getProvinceID(0)
                        );
                  }

                  VIEW_BEFORE = CFG.viewsManager.getActiveViewID();
                  if (CFG.viewsManager.getActiveViewID() != ViewsManager.VIEW_DIPLOMACY_MODE) {
                     CFG.viewsManager.setActiveViewID(ViewsManager.VIEW_DIPLOMACY_MODE);
                  }
               }
            } catch (IndexOutOfBoundsException var5) {
            }
         }

         if (CFG.game
                  .getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
                  .getCivilization_Diplomacy_GameData()
                  .messageBox
                  .getMessage(
                     CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCivilization_Diplomacy_GameData().messageBox.getMessagesSize()
                        - 1
                        - iID
                  )
                  .iFromCivID
               != CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()
            && tempMessageType != Message_Type.HIGH_INFLATION) {
            try {
               if (CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()
                  == CFG.game
                     .getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
                     .getCivilization_Diplomacy_GameData()
                     .messageBox
                     .getMessage(
                        CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCivilization_Diplomacy_GameData().messageBox.getMessagesSize()
                           - 1
                           - iID
                     )
                     .iFromCivID) {
                  CFG.setActiveCivInfo(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID());
                  CFG.updateActiveCivInfo_InGame();
                  CFG.game.disableDrawCivilizationRegions_Active();
                  if (CFG.FOG_OF_WAR == 2) {
                     CFG.game.enableDrawCivilizationRegions_FogOfWar(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID(), 0);
                  } else {
                     CFG.game.enableDrawCivilizationRegions(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID(), 0);
                  }
               }
            } catch (IndexOutOfBoundsException var4) {
            }
         }

         CFG.game
            .getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
            .getCivilization_Diplomacy_GameData()
            .messageBox
            .getMessage(
               CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCivilization_Diplomacy_GameData().messageBox.getMessagesSize() - 1 - iID
            )
            .onAction(
               CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCivilization_Diplomacy_GameData().messageBox.getMessagesSize() - 1 - iID
            );
      }
   }
}
