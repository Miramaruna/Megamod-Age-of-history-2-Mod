package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

public class Menu_InGame_CivilizationView extends SliderMenu {
   public static int iCivID = 0;

   public Menu_InGame_CivilizationView() {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      menuElements.add(new Button_Transparent(0, 0, CFG.GAME_WIDTH, CFG.GAME_HEIGHT, true));
      this.initMenu(null, 0, 0, CFG.GAME_WIDTH, CFG.GAME_HEIGHT, menuElements);
      this.updateLanguage();
   }

   @Override
   public void updateLanguage() {
   }

   @Override
   public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.45F));
      ImageManager.getImage(Images.gradient)
         .draw(oSB, iTranslateX, -ImageManager.getImage(Images.gradient).getHeight() + iTranslateY, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT / 2);
      ImageManager.getImage(Images.gradient)
         .draw(
            oSB,
            iTranslateX,
            CFG.GAME_HEIGHT - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY,
            CFG.GAME_WIDTH,
            CFG.BUTTON_HEIGHT / 2,
            false,
            true
         );
      oSB.setColor(new Color(1.0F, 1.0F, 1.0F, 0.125F));
      ImageManager.getImage(Images.gameLogo)
         .draw(
            oSB,
            this.getPosX() + this.getWidth() - CFG.PADDING - ImageManager.getImage(Images.gameLogo).getWidth() + iTranslateX,
            this.getPosY() + this.getHeight() - CFG.PADDING - ImageManager.getImage(Images.gameLogo).getHeight()
         );
      oSB.setColor(Color.WHITE);
   }

   @Override
   public final void actionElement(int iID) {
      this.onBackPressed();
   }

   @Override
   public final void onBackPressed() {
      CFG.menuManager.setViewID(Menu.eINGAME);
      CFG.map.getMapScroll().stopScrollingTheMap();
      CFG.map.getMapBG().updateWorldMap_Shaders();
      CFG.game.disableDrawCivilizationRegions(iCivID);
      CFG.map.getMapScale().setCurrentScale(CFG.game.getPlayer(CFG.PLAYER_TURNID).fBefore_Scale);
      CFG.map.getMapCoordinates().setStartingPosX(CFG.game.getPlayer(CFG.PLAYER_TURNID).iBefore_PosX);
      CFG.map.getMapCoordinates().setStartingPosY(CFG.game.getPlayer(CFG.PLAYER_TURNID).iBefore_PosY);
      CFG.map.getMapCoordinates().updateSecondSideOfMap();
      CFG.game.setActiveProvinceID(CFG.game.getPlayer(CFG.PLAYER_TURNID).iBefore_ActiveProvince);
      CFG.viewsManager.setActiveViewID(CFG.game.getPlayer(CFG.PLAYER_TURNID).iACTIVE_VIEW_MODE);
   }

   @Override
   public void onMenuPressed() {
      this.onBackPressed();
   }
}
