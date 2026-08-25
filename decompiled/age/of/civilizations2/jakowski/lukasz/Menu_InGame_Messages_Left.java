package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;
import java.util.HashMap;

class Menu_InGame_Messages_Left extends SliderMenu {
   protected static long ANIMATION_TIME = 0L;
   protected static long ANIMATION_TIMER = 325L;
   protected static boolean IN_ANIMATION = false;
   protected static boolean START_ANIMATION = false;
   protected static int VIEW_BEFORE = -1;
   public static HashMap<Integer, Integer> map = new HashMap<>();

   protected Menu_InGame_Messages_Left() {
      ArrayList var1 = new ArrayList();
      int var2 = CFG.GAME_WIDTH;
      if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCivilization_Diplomacy_GameData().messageBox.getMessagesSize() != 1) {
         for (int var3 = 0;
            var3
               < (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCivilization_Diplomacy_GameData().messageBox.getMessagesSize() - 1) / 2;
            var3++
         ) {
            if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCivilization_Diplomacy_GameData().messageBox.getMessage(var3).messageType
                  == Message_Type.PEACE_TREATY_LIST_OF_DEMANDS
               && CFG.game
                     .getPeaceTreaty_GameDataID(
                        CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCivilization_Diplomacy_GameData().messageBox.getMessage(var3).TAG
                     )
                  < 0) {
               CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCivilization_Diplomacy_GameData().messageBox.removeMessage(var3);
            }
         }

         for (int var4 = (
                  CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCivilization_Diplomacy_GameData().messageBox.getMessagesSize() - 1
               )
               / 2;
            var4 >= 0;
            var4--
         ) {
            var1.add(
               new Button_Message(
                  var2,
                  0,
                  var4,
                  CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCivilization_Diplomacy_GameData().messageBox.getMessage(var4).iFromCivID,
                  CFG.game
                     .getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
                     .getCivilization_Diplomacy_GameData()
                     .messageBox
                     .getMessage(var4)
                     .getImageID(),
                  CFG.game
                     .getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
                     .getCivilization_Diplomacy_GameData()
                     .messageBox
                     .getMessage(var4)
                     .getBGImageID()
               )
            );
            map.put(var1.size() - 1, var4);
            var2 -= ((MenuElement)var1.get(var1.size() - 1)).getWidth() + CFG.PADDING;
         }
      }

      this.initMenu(
         null,
         Gdx.graphics.getWidth() / 2
            - ImageManager.getImage(Images.top_left).getWidth()
            - CFG.PADDING
            - (CFG.GAME_WIDTH + Button_Diplomacy.iDiploWidth + ImageManager.getImage(Images.flag_rect).getWidth() + CFG.PADDING * 4),
         ImageManager.getImage(Images.top_left2_sha).getHeight() + CFG.PADDING,
         CFG.GAME_WIDTH + Button_Diplomacy.iDiploWidth + ImageManager.getImage(Images.flag_rect).getWidth() + CFG.PADDING * 4,
         (int)(CFG.BUTTON_HEIGHT * 0.6F) + 1,
         var1
      );
      this.updateLanguage();
   }

   // $VF: Duplicated exception handlers to handle obfuscated exceptions
   @Override
   protected final void actionElement(int var1) {
      if (CFG.gameAction.getActiveTurnState() == Game_Action.TurnStates.INPUT_ORDERS && !CFG.SPECTATOR_MODE) {
         int var2;
         Message_Type var3;
         var2 = map.get(var1);
         System.out.println("LEFT!");
         var3 = CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCivilization_Diplomacy_GameData().messageBox.getMessage(var2).messageType;
         label69:
         if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCivilization_Diplomacy_GameData().messageBox.getMessage(var2).iFromCivID
               != CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()
            && var3 != Message_Type.HIGH_INFLATION) {
            label98: {
               label104: {
                  try {
                     if (CFG.game
                           .getCiv(
                              CFG.game
                                 .getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
                                 .getCivilization_Diplomacy_GameData()
                                 .messageBox
                                 .getMessage(var2)
                                 .iFromCivID
                           )
                           .getNumOfProvinces()
                        <= 0) {
                        break label69;
                     }

                     if (CFG.FOG_OF_WAR == 2) {
                        if (CFG.game
                              .getProvince(
                                 CFG.game
                                    .getCiv(
                                       CFG.game
                                          .getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
                                          .getCivilization_Diplomacy_GameData()
                                          .messageBox
                                          .getMessage(var2)
                                          .iFromCivID
                                    )
                                    .getCapitalProvinceID()
                              )
                              .getCivID()
                           != CFG.game
                              .getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
                              .getCivilization_Diplomacy_GameData()
                              .messageBox
                              .getMessage(var2)
                              .iFromCivID) {
                           break label104;
                        }

                        CFG.game
                           .getPlayer(CFG.PLAYER_TURNID)
                           .setMetProvince(
                              CFG.game
                                 .getCiv(
                                    CFG.game
                                       .getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
                                       .getCivilization_Diplomacy_GameData()
                                       .messageBox
                                       .getMessage(var2)
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
                                 .getMessage(var2)
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
                                       .getMessage(var2)
                                       .iFromCivID
                                 )
                                 .getCapitalProvinceID()
                           );
                        break label98;
                     }
                  } catch (IndexOutOfBoundsException var11) {
                     break label69;
                  }

                  try {
                     if (CFG.game
                           .getProvince(
                              CFG.game
                                 .getCiv(
                                    CFG.game
                                       .getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
                                       .getCivilization_Diplomacy_GameData()
                                       .messageBox
                                       .getMessage(var2)
                                       .iFromCivID
                                 )
                                 .getCapitalProvinceID()
                           )
                           .getCivID()
                        == CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCivilization_Diplomacy_GameData().messageBox.getMessage(var2).iFromCivID
                        )
                      {
                        CFG.game
                           .setActiveProvinceID(
                              CFG.game
                                 .getCiv(
                                    CFG.game
                                       .getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
                                       .getCivilization_Diplomacy_GameData()
                                       .messageBox
                                       .getMessage(var2)
                                       .iFromCivID
                                 )
                                 .getCapitalProvinceID()
                           );
                        break label98;
                     }
                  } catch (IndexOutOfBoundsException var10) {
                     break label69;
                  }

                  try {
                     CFG.game
                        .setActiveProvinceID(
                           CFG.game
                              .getCiv(
                                 CFG.game
                                    .getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
                                    .getCivilization_Diplomacy_GameData()
                                    .messageBox
                                    .getMessage(var2)
                                    .iFromCivID
                              )
                              .getProvinceID(0)
                        );
                     break label98;
                  } catch (IndexOutOfBoundsException var8) {
                     break label69;
                  }
               }

               try {
                  CFG.game
                     .getPlayer(CFG.PLAYER_TURNID)
                     .setMetProvince(
                        CFG.game
                           .getCiv(
                              CFG.game
                                 .getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
                                 .getCivilization_Diplomacy_GameData()
                                 .messageBox
                                 .getMessage(var2)
                                 .iFromCivID
                           )
                           .getProvinceID(0),
                        true
                     );
                  CFG.game
                     .getPlayer(CFG.PLAYER_TURNID)
                     .setMetCivilization(
                        CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCivilization_Diplomacy_GameData().messageBox.getMessage(var2).iFromCivID,
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
                                 .getMessage(var2)
                                 .iFromCivID
                           )
                           .getProvinceID(0)
                     );
               } catch (IndexOutOfBoundsException var9) {
                  break label69;
               }
            }

            try {
               VIEW_BEFORE = CFG.viewsManager.getActiveViewID();
               if (CFG.viewsManager.getActiveViewID() != ViewsManager.VIEW_DIPLOMACY_MODE) {
                  CFG.viewsManager.setActiveViewID(ViewsManager.VIEW_DIPLOMACY_MODE);
               }
            } catch (IndexOutOfBoundsException var6) {
            }
         }

         label51:
         if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCivilization_Diplomacy_GameData().messageBox.getMessage(var2).iFromCivID
               != CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()
            && var3 != Message_Type.HIGH_INFLATION) {
            try {
               if (CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()
                  != CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCivilization_Diplomacy_GameData().messageBox.getMessage(var2).iFromCivID
                  )
                {
                  break label51;
               }

               CFG.setActiveCivInfo(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID());
               CFG.updateActiveCivInfo_InGame();
               CFG.game.disableDrawCivilizationRegions_Active();
               if (CFG.FOG_OF_WAR == 2) {
                  CFG.game.enableDrawCivilizationRegions_FogOfWar(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID(), 0);
                  break label51;
               }
            } catch (IndexOutOfBoundsException var7) {
               break label51;
            }

            try {
               CFG.game.enableDrawCivilizationRegions(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID(), 0);
            } catch (IndexOutOfBoundsException var5) {
            }
         }

         CFG.game
            .getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
            .getCivilization_Diplomacy_GameData()
            .messageBox
            .getMessage(var2)
            .onAction(map.get(var1));
      }
   }

   @Override
   protected void draw(SpriteBatch var1, int var2, int var3, boolean var4) {
      this.beginClip(var1, var2, var3, var4);
      if (Menu_InGame_Messages.START_ANIMATION) {
         Menu_InGame_Messages.ANIMATION_TIME = System.currentTimeMillis() - Menu_InGame_Messages.ANIMATION_TIMER * 2L / 5L;
         Menu_InGame_Messages.IN_ANIMATION = true;
         Menu_InGame_Messages.START_ANIMATION = false;
      }

      int var5 = var3;
      if (Menu_InGame_Messages.IN_ANIMATION) {
         if (Menu_InGame_Messages.ANIMATION_TIME + Menu_InGame_Messages.ANIMATION_TIMER >= System.currentTimeMillis()) {
            var5 = var3
               + -this.getHeight()
               + (int)(
                  this.getHeight() * ((float)(System.currentTimeMillis() - Menu_InGame_Messages.ANIMATION_TIME) / (float)Menu_InGame_Messages.ANIMATION_TIMER)
               );
            CFG.setRender_3(true);
         } else {
            Menu_InGame_Messages.IN_ANIMATION = false;
            var5 = var3;
         }
      }

      this.drawMenu(var1, var2, var5, var4);
      this.endClip(var1, var2, var5, var4);
   }

   @Override
   protected void updateLanguage() {
   }
}
