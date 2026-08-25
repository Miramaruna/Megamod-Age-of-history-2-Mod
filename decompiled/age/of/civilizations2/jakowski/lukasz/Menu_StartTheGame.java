package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

public class Menu_StartTheGame extends SliderMenu {
   public String s1 = "";
   public int iWidth1;
   public String s2 = "";
   public int iWidth2;
   public Turn_CivsInRange turn_civsInRange;

   public Menu_StartTheGame() {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      menuElements.add(new Button_Transparent(1, 1, CFG.GAME_WIDTH - 2, CFG.GAME_HEIGHT - 2, true));
      this.initMenu(null, 0, 0, CFG.GAME_WIDTH, CFG.GAME_HEIGHT, menuElements);

      try {
         this.s1 = CFG.EDITOR_ACTIVE_GAMEDATA_TAG;
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
         this.s2 = Game_Calendar.getCurrentDate();
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
      this.turn_civsInRange = new Turn_CivsInRange();
      this.turn_civsInRange.start();
   }

   @Override
   public void updateLanguage() {
   }

   @Override
   public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      try {
         oSB.setColor(new Color(0.1F, 0.1F, 0.1F, 0.2F - 0.2F * CFG.startTheGameData.getProvincesAlpha()));
         ImageManager.getImage(Images.patt).draw(oSB, iTranslateX, -ImageManager.getImage(Images.patt).getHeight(), CFG.GAME_WIDTH, CFG.GAME_HEIGHT, 0.0F, 0);
         oSB.setColor(new Color(CFG.COLOR_GRADIENT_DARK_BLUE.r, CFG.COLOR_GRADIENT_DARK_BLUE.g, CFG.COLOR_GRADIENT_DARK_BLUE.b, 0.4F));
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
         oSB.setColor(new Color(CFG.COLOR_GRADIENT_DARK_BLUE.r, CFG.COLOR_GRADIENT_DARK_BLUE.g, CFG.COLOR_GRADIENT_DARK_BLUE.b, 0.65F));
         ImageManager.getImage(Images.gradient)
            .draw(
               oSB,
               this.getPosX() + iTranslateX,
               this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY,
               this.getWidth(),
               CFG.BUTTON_HEIGHT
            );
         oSB.setColor(CFG.COLOR_GRADIENT_DARK_BLUE);
         ImageManager.getImage(Images.line_32_off1)
            .draw(
               oSB,
               this.getPosX() + iTranslateX,
               this.getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
               this.getWidth(),
               CFG.BUTTON_HEIGHT
            );
         oSB.setColor(CFG.COLOR_FLAG_FRAME);
         ImageManager.getImage(Images.line_32_off1)
            .draw(
               oSB,
               this.getPosX() + CFG.PADDING * 2 + iTranslateX,
               this.getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() + CFG.BUTTON_HEIGHT - 2 + iTranslateY,
               this.getWidth() - CFG.PADDING * 4,
               1
            );
         oSB.setColor(Color.WHITE);
         oSB.setColor(new Color(1.0F, 1.0F, 1.0F, 0.35F));
         ImageManager.getImage(Images.gameLogo)
            .draw2(
               oSB,
               CFG.GAME_WIDTH - ImageManager.getImage(Images.gameLogo).getWidth() - CFG.PADDING + iTranslateX,
               CFG.GAME_HEIGHT - ImageManager.getImage(Images.gameLogo).getHeight() * 2 - CFG.PADDING,
               ImageManager.getImage(Images.gameLogo).getWidth(),
               ImageManager.getImage(Images.gameLogo).getHeight()
            );
         oSB.setColor(Color.WHITE);
         ImageManager.getImage(Images.gameLogo)
            .draw2(
               oSB,
               CFG.GAME_WIDTH - ImageManager.getImage(Images.gameLogo).getWidth() - CFG.PADDING + iTranslateX,
               CFG.GAME_HEIGHT - ImageManager.getImage(Images.gameLogo).getHeight() * 2 - CFG.PADDING,
               (int)(ImageManager.getImage(Images.gameLogo).getWidth() * CFG.startTheGameData.getProvincesAlpha() / 100.0F),
               ImageManager.getImage(Images.gameLogo).getHeight()
            );
         CFG.drawText(
            oSB,
            this.s1,
            CFG.GAME_WIDTH / 2 - this.iWidth1 / 2 + iTranslateX,
            CFG.BUTTON_HEIGHT / 2 - CFG.TEXT_HEIGHT - CFG.PADDING / 2 + iTranslateY,
            CFG.COLOR_TEXT_CNG_TOP_SCENARIO_NAME
         );
         CFG.fontMain.getData().setScale(0.8F);
         CFG.drawText(
            oSB,
            this.s2,
            CFG.GAME_WIDTH / 2 - (int)(this.iWidth2 * 0.8F / 2.0F) + iTranslateX,
            CFG.BUTTON_HEIGHT / 2 + CFG.PADDING + iTranslateY,
            CFG.COLOR_TEXT_CNG_TOP_SCENARIO_INFO
         );
         CFG.fontMain.getData().setScale(1.0F);
         super.draw(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
         if (CFG.startTheGameData.getIsDone()) {
            this.onBackPressed();
         }

         try {
            if (Turn_CivsInRange.DONE_CIVS < CFG.game.getCivsSize()) {
               CFG.fontMain.getData().setScale(0.8F);
               CFG.drawText(
                  oSB,
                  "" + Turn_CivsInRange.DONE_CIVS + " / " + CFG.game.getCivsSize(),
                  CFG.PADDING + iTranslateX,
                  CFG.GAME_HEIGHT - (int)(CFG.TEXT_HEIGHT * 0.8F) - CFG.PADDING + iTranslateY,
                  CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
               );
            }
         } catch (NullPointerException var6) {
         }

         CFG.fontMain.getData().setScale(1.0F);
      } catch (NullPointerException var7) {
         this.onBackPressed();
      } catch (IndexOutOfBoundsException var8) {
         this.onBackPressed();
      }

      oSB.setColor(Color.WHITE);
      CFG.setRender_3(true);
   }

   @Override
   public final void actionElement(int iID) {
      try {
         if (Turn_CivsInRange.DONE_CIVS >= CFG.game.getCivsSize()) {
            done();
         }
      } catch (NullPointerException var3) {
      }
   }

   @Override
   public final void onBackPressed() {
   }

   public static final void done() {
      CFG.soundsManager.playNewGameSFX();
      CFG.gameAction.hideExtraViews();
      CFG.menuManager.setViewID(Menu.eINGAME);
      CFG.menuManager.setVisible_InGame_Options(false);
      CFG.menuManager.setVisible_InGame_EndOfGame(false);
      CFG.menuManager.setVisible_InGame_ActionInfo(false);
      CFG.menuManager.setVisible_InGame_View(false);
      CFG.gameAction.updateInGame_ProvinceInfo();
      CFG.menuManager.updateInGame_TOP_All(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
      CFG.menuManager.rebuildInGame_Messages();
      CFG.menuManager.setVisible_Menu_InGame_CurrentWars(true);
      Game_Render_Province.updateDrawProvinces();
      CFG.game.checkProvinceActionMenu();
      CFG.menuManager.setOrderOfMenu_InGame();
      CFG.game.updateDrawMoveUnitsArmy();
   }
}
