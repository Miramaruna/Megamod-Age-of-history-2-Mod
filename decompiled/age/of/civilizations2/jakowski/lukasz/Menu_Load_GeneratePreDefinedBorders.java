package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

public class Menu_Load_GeneratePreDefinedBorders extends SliderMenu {
   public Menu_Load_GeneratePreDefinedBorders() {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1 = 0;
      CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV2 = 1;
      CFG.game.setScenarioID(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1);
      CFG.game.loadScenario(true);
      this.initMenu(null, 0, 0, CFG.GAME_WIDTH, CFG.GAME_HEIGHT, menuElements);
   }

   @Override
   public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.6F));
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
      CFG.drawLoading(
         oSB,
         (int)(CFG.GAME_WIDTH * CFG.getLoadingPadding()) + iTranslateX,
         CFG.GAME_HEIGHT - (int)(CFG.BUTTON_HEIGHT * 0.6F) * 2 - CFG.PADDING + iTranslateY,
         (int)(CFG.GAME_WIDTH * (1.0F - CFG.getLoadingPadding() * 2.0F)),
         (int)(CFG.BUTTON_HEIGHT * 0.6F),
         (float)CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1 / (Game_Scenarios.SCENARIOS_SIZE + CFG.map.getMapNumOfProvinces(CFG.map.getActiveMapID()) * 2)
      );
      CFG.drawJakowskiGames_RIGHT_BOT(oSB, iTranslateX);
      new Thread(new Runnable() {
         @Override
         public void run() {
            Gdx.app.postRunnable(new Runnable() {
               @Override
               public void run() {
                  Menu_Load_GeneratePreDefinedBorders.this.loadData();
               }
            });
         }
      }).start();
      CFG.drawVersion_LEFT_BOT(oSB, iTranslateX);
      CFG.setRender_3(true);
   }

   public final void loadData() {
      if (CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1 < Game_Scenarios.SCENARIOS_SIZE) {
         if (CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV2 < CFG.game.getCivsSize()) {
            CFG.game.build_PreDefinedCivsBorders(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV2);
            CFG.sLoading = CFG.langManager.get("Loading") + " #" + CFG.game.getCiv(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV2).getCivName();
            CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV2++;
         } else {
            CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV2 = 1;
            if (++CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1 < Game_Scenarios.SCENARIOS_SIZE) {
               CFG.game.setScenarioID(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1);
               CFG.game.loadScenario(true);
            }
         }
      } else {
         CFG.sLoading = CFG.langManager.get("Loading");
         CFG.menuManager.setViewID(Menu.eMAP_EDITOR_EDIT);
         CFG.map.getMapCoordinates().centerToRandomMapPosition();
      }
   }
}
