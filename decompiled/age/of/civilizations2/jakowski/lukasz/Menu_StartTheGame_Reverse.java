package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

public class Menu_StartTheGame_Reverse extends SliderMenu {
   public static boolean END_GAME_MODE = true;
   public String s1 = "";
   public int iWidth1;
   public String s2 = "";
   public int iWidth2;

   public Menu_StartTheGame_Reverse() {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      menuElements.add(new Button_Transparent(1, 1, CFG.GAME_WIDTH - 2, CFG.GAME_HEIGHT - 2, true));
      this.initMenu(null, 0, 0, CFG.GAME_WIDTH, CFG.GAME_HEIGHT, menuElements);

      try {
         this.s1 = CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCivName();
         CFG.glyphLayout.setText(CFG.fontMain, this.s1);
         this.iWidth1 = (int)CFG.glyphLayout.width;
      } catch (IndexOutOfBoundsException var6) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var6);
         }
      } catch (NullPointerException var7) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var7);
         }
      } catch (IllegalStateException var8) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var8);
         }
      }

      try {
         this.s2 = Game_Calendar.getDate_ByTurnID(1) + " - " + Game_Calendar.getCurrentDate();
         CFG.glyphLayout.setText(CFG.fontMain, this.s2);
         this.iWidth2 = (int)CFG.glyphLayout.width;
      } catch (IndexOutOfBoundsException var3) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var3);
         }
      } catch (NullPointerException var4) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var4);
         }
      } catch (IllegalStateException var5) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var5);
         }
      }

      this.updateLanguage();
   }

   @Override
   public void updateLanguage() {
   }

   @Override
   public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      try {
         oSB.setColor(new Color(0.1F, 0.1F, 0.1F, 0.2F - 0.2F * CFG.startTheGameData.getProvincesAlpha()));
         ImageManager.getImage(Images.patt).draw(oSB, iTranslateX, -ImageManager.getImage(Images.patt).getHeight(), CFG.GAME_WIDTH, CFG.GAME_HEIGHT, 0.0F, 0);
         float nAlpha = (float)CFG.startTheGameData.getProvincesAlpha() / CFG.settingsManager.PROVINCE_ALPHA;
         oSB.setColor(new Color(CFG.COLOR_GRADIENT_DARK_BLUE.r, CFG.COLOR_GRADIENT_DARK_BLUE.g, CFG.COLOR_GRADIENT_DARK_BLUE.b, nAlpha * 0.4F));
         ImageManager.getImage(Images.gradient)
            .draw(oSB, iTranslateX, -ImageManager.getImage(Images.gradient).getHeight() + iTranslateY, CFG.GAME_WIDTH, CFG.PADDING * 3);
         ImageManager.getImage(Images.gradient)
            .draw(
               oSB,
               iTranslateX,
               CFG.GAME_HEIGHT - ImageManager.getImage(Images.gradient).getHeight() - CFG.PADDING * 3 + iTranslateY,
               CFG.GAME_WIDTH,
               CFG.PADDING * 3,
               false,
               true
            );
         oSB.setColor(new Color(CFG.COLOR_GRADIENT_DARK_BLUE.r, CFG.COLOR_GRADIENT_DARK_BLUE.g, CFG.COLOR_GRADIENT_DARK_BLUE.b, nAlpha * 0.65F));
         ImageManager.getImage(Images.gradient)
            .draw(
               oSB,
               this.getPosX() + iTranslateX,
               this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY,
               this.getWidth(),
               CFG.BUTTON_HEIGHT
            );
         oSB.setColor(
            new Color(CFG.COLOR_GRADIENT_DARK_BLUE.r, CFG.COLOR_GRADIENT_DARK_BLUE.g, CFG.COLOR_GRADIENT_DARK_BLUE.b, nAlpha * CFG.COLOR_GRADIENT_DARK_BLUE.a)
         );
         ImageManager.getImage(Images.line_32_off1)
            .draw(
               oSB,
               this.getPosX() + iTranslateX,
               this.getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
               this.getWidth(),
               CFG.BUTTON_HEIGHT
            );
         ImageManager.getImage(Images.line_32_off1)
            .draw(
               oSB,
               this.getPosX() + CFG.PADDING * 2 + iTranslateX,
               this.getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() + CFG.BUTTON_HEIGHT - 3 + iTranslateY,
               this.getWidth() - CFG.PADDING * 4,
               1
            );
         oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, nAlpha * CFG.COLOR_FLAG_FRAME.a));
         ImageManager.getImage(Images.line_32_off1)
            .draw(
               oSB,
               this.getPosX() + CFG.PADDING * 2 + iTranslateX,
               this.getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() + CFG.BUTTON_HEIGHT - 2 + iTranslateY,
               this.getWidth() - CFG.PADDING * 4,
               1
            );
         oSB.setColor(Color.WHITE);
         oSB.setColor(new Color(1.0F, 1.0F, 1.0F, nAlpha * 0.35F));
         ImageManager.getImage(Images.gameLogo)
            .draw2(
               oSB,
               CFG.PADDING + iTranslateX,
               CFG.GAME_HEIGHT - ImageManager.getImage(Images.gameLogo).getHeight() * 2 - CFG.PADDING,
               ImageManager.getImage(Images.gameLogo).getWidth(),
               ImageManager.getImage(Images.gameLogo).getHeight()
            );
         oSB.setColor(new Color(1.0F, 1.0F, 1.0F, nAlpha * 1.0F));
         ImageManager.getImage(Images.gameLogo)
            .draw2(
               oSB,
               CFG.PADDING + iTranslateX,
               CFG.GAME_HEIGHT - ImageManager.getImage(Images.gameLogo).getHeight() * 2 - CFG.PADDING,
               (int)(ImageManager.getImage(Images.gameLogo).getWidth() * CFG.startTheGameData.getProvincesAlpha() / 100.0F),
               ImageManager.getImage(Images.gameLogo).getHeight()
            );
         CFG.drawText(
            oSB,
            this.s1,
            CFG.GAME_WIDTH / 2 - this.iWidth1 / 2 + iTranslateX,
            CFG.BUTTON_HEIGHT / 2 - CFG.TEXT_HEIGHT - CFG.PADDING / 2 + iTranslateY,
            new Color(
               CFG.COLOR_TEXT_NUM_OF_PROVINCES.r,
               CFG.COLOR_TEXT_NUM_OF_PROVINCES.g,
               CFG.COLOR_TEXT_NUM_OF_PROVINCES.b,
               nAlpha * CFG.COLOR_TEXT_NUM_OF_PROVINCES.a
            )
         );
         CFG.fontMain.getData().setScale(0.8F);
         CFG.drawText(
            oSB,
            this.s2,
            CFG.GAME_WIDTH / 2 - (int)(this.iWidth2 * 0.8F / 2.0F) + iTranslateX,
            CFG.BUTTON_HEIGHT / 2 + CFG.PADDING + iTranslateY,
            new Color(
               CFG.COLOR_TEXT_CNG_TOP_SCENARIO_INFO.r,
               CFG.COLOR_TEXT_CNG_TOP_SCENARIO_INFO.g,
               CFG.COLOR_TEXT_CNG_TOP_SCENARIO_INFO.b,
               nAlpha * CFG.COLOR_TEXT_CNG_TOP_SCENARIO_INFO.a
            )
         );
         CFG.fontMain.getData().setScale(1.0F);
         super.draw(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
         if (CFG.startTheGameData.getIsDone()) {
            this.onBackPressed();
         }
      } catch (NullPointerException var6) {
         this.onBackPressed();
      } catch (IndexOutOfBoundsException var7) {
         this.onBackPressed();
      }

      oSB.setColor(Color.WHITE);
      CFG.setRender_3(true);
   }

   @Override
   public final void actionElement(int iID) {
      if (CFG.startTheGameData.getIsDone()) {
         this.onBackPressed();
      }
   }

   @Override
   public final void onBackPressed() {
      done();
   }

   public static final void done() {
      if (END_GAME_MODE) {
         CFG.menuManager.setViewID(Menu.eGAMES);
         CFG.map.getMapBG().updateWorldMap_Shaders();
         CFG.viewsManager.disableAllViews();
         CFG.tutorialManager.updateDrawTutorial(false);
         SaveManager.gameCanBeContinued = false;
         CFG.PLAYER_TURNID = 0;
         CFG.FOG_OF_WAR = 2;
         Game_Render_Province.updateDrawProvinces();
         CFG.game.loadScenario(false);
         CFG.game.initPlayers();
      } else {
         try {
            if (!CFG.SPECTATOR_MODE) {
               int tNumOfPlayersInGame = 0;

               for (int i = 0; i < CFG.game.getPlayersSize(); i++) {
                  if (CFG.game.getCiv(CFG.game.getPlayer(i).getCivID()).getNumOfProvinces() > 0 && i != CFG.PLAYER_TURNID) {
                     tNumOfPlayersInGame++;
                  }
               }

               if (tNumOfPlayersInGame == 0) {
                  CFG.menuManager.setViewID(Menu.eINGAME);
                  CFG.gameAction.hideExtraViews();
                  CFG.viewsManager.disableAllViews();
                  CFG.gameNewGame.newGame_InitPlayers_SpectatorMode();
                  CFG.SPECTATOR_MODE = true;
                  CFG.PLAYER_TURNID = 0;
                  Game_Render.updateDrawCivRegionNames_FogOfWar();
                  Game_Render.updateDrawMoveUnits();
                  CFG.game.updateDrawMoveUnitsArmy();

                  for (int var4 = 0; var4 < CFG.game.getProvincesSize(); var4++) {
                     CFG.game.getProvince(var4).updateProvinceBorder();
                     CFG.game.getProvince(var4).updateDrawArmy();
                  }

                  CFG.map.getMapBG().disposeMinimapOfCivilizations();
                  CFG.menuManager.updateInGame_TOP_All(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
               }
            }
         } catch (IndexOutOfBoundsException var2) {
            CFG.exceptionStack(var2);
         } catch (NullPointerException var3) {
            CFG.exceptionStack(var3);
         }

         Menu_InGame_EndOfGame.clickBack();
      }
   }
}
