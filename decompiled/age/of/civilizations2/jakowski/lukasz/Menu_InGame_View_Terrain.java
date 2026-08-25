package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

public class Menu_InGame_View_Terrain extends SliderMenu {
   public static final int ANIMATION_TIME = 175;
   public static long lTime = 0L;
   public static boolean hideAnimation = true;
   public int iCivID = 0;

   public Menu_InGame_View_Terrain() {
      int tempW = CFG.CIV_INFO_MENU_WIDTH;
      int tY = 0;
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      this.iCivID = CFG.getActiveCivInfo_BasedOnActiveProvinceID(CFG.game.getActiveProvinceID());
      ArrayList<Integer> tempNum = new ArrayList<>();
      ArrayList<Integer> tempIDs = new ArrayList<>();
      ArrayList tempSorted = new ArrayList();

      for (int i = 0; i < CFG.terrainTypesManager.getTerrainsSize(); i++) {
         tempNum.add(0);
         tempIDs.add(i);
      }

      for (int var10 = 0; var10 < CFG.game.getProvincesSize(); var10++) {
         if (!CFG.game.getProvince(var10).getSeaProvince()
            && CFG.game.getProvince(var10).getWasteland() < 0
            && (CFG.FOG_OF_WAR != 2 || CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetProvince(var10))) {
            tempNum.set(CFG.game.getProvince(var10).getTerrainTypeID(), tempNum.get(CFG.game.getProvince(var10).getTerrainTypeID()) + 1);
         }
      }

      while (tempIDs.size() > 0) {
         int tBest = 0;

         for (int i2 = 0; i2 < tempIDs.size(); i2++) {
            if (tempNum.get(tempIDs.get(i2)) > tempNum.get(tempIDs.get(tBest))) {
               tBest = i2;
            }
         }

         tempSorted.add(tempIDs.get(tBest));
         tempIDs.remove(tBest);
      }

      for (int var11 = 0; var11 < tempSorted.size(); var11++) {
         if (tempNum.get((Integer)tempSorted.get(var11)) == 0) {
            tempSorted.remove(var11--);
         }
      }

      if (tempSorted.size() > 0) {
         for (int var12 = 0; var12 < tempSorted.size(); var12++) {
            menuElements.add(
               new Button_View_Terrain(var12, (Integer)tempSorted.get(var12), tempNum.get((Integer)tempSorted.get(var12)), 0, tY, CFG.CIV_INFO_MENU_WIDTH) {}
            );
            tY += menuElements.get(menuElements.size() - 1).getHeight();
         }
      } else {
         menuElements.add(new Text_Scale(CFG.langManager.get("NoData"), -1, 0, tY, tempW, CFG.BUTTON_HEIGHT * 3 / 4, 0.75F));
         tY += menuElements.get(menuElements.size() - 1).getHeight();
      }

      this.initMenu(
         new SliderMenuTitle(CFG.langManager.get("Terrain"), CFG.BUTTON_HEIGHT * 3 / 5, false, false) {
            @Override
            public void draw(SpriteBatch oSB, int iTranslateX, int nPosX, int nPosY, int nWidth, boolean sliderMenuIsActive) {
               ImageManager.getImage(Images.new_game_top_edge_title)
                  .draw2(
                     oSB,
                     Menu_InGame_View_Terrain.this.getPosX() + iTranslateX,
                     Menu_InGame_View_Terrain.this.getPosY() - ImageManager.getImage(Images.new_game_top_edge_title).getHeight() - this.getHeight(),
                     Menu_InGame_View_Terrain.this.getWidth() + 2,
                     this.getHeight(),
                     true,
                     false
                  );
               oSB.setColor(new Color(0.29411766F, 0.54901963F, 0.47058824F, 0.165F));
               ImageManager.getImage(Images.line_32_off1)
                  .draw(
                     oSB,
                     nPosX + iTranslateX,
                     nPosY - this.getHeight() + 2 - ImageManager.getImage(Images.line_32_off1).getHeight(),
                     nWidth,
                     this.getHeight() - 2,
                     false,
                     true
                  );
               oSB.setColor(new Color(0.29411766F, 0.54901963F, 0.47058824F, 0.375F));
               ImageManager.getImage(Images.gradient)
                  .draw(
                     oSB,
                     nPosX + iTranslateX,
                     nPosY - this.getHeight() * 2 / 3 - ImageManager.getImage(Images.gradient).getHeight(),
                     nWidth,
                     this.getHeight() * 2 / 3,
                     false,
                     true
                  );
               oSB.setColor(new Color(0.451F, 0.329F, 0.11F, 1.0F));
               ImageManager.getImage(Images.pix255_255_255)
                  .draw(
                     oSB,
                     Menu_InGame_View_Terrain.this.getPosX() + iTranslateX,
                     Menu_InGame_View_Terrain.this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight(),
                     Menu_InGame_View_Terrain.this.getWidth()
                  );
               oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.4F));
               ImageManager.getImage(Images.line_32_off1)
                  .draw(
                     oSB,
                     Menu_InGame_View_Terrain.this.getPosX() + iTranslateX,
                     Menu_InGame_View_Terrain.this.getPosY()
                        - ImageManager.getImage(Images.pix255_255_255).getHeight()
                        - ImageManager.getImage(Images.line_32_off1).getHeight(),
                     Menu_InGame_View_Terrain.this.getWidth(),
                     1
                  );
               oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.6F));
               ImageManager.getImage(Images.line_32_off1)
                  .draw(
                     oSB,
                     Menu_InGame_View_Terrain.this.getPosX() + iTranslateX,
                     Menu_InGame_View_Terrain.this.getPosY()
                        - ImageManager.getImage(Images.pix255_255_255).getHeight()
                        - ImageManager.getImage(Images.line_32_off1).getHeight()
                        - 1,
                     Menu_InGame_View_Terrain.this.getWidth(),
                     1
                  );
               oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.8F));
               ImageManager.getImage(Images.slider_gradient)
                  .draw(
                     oSB,
                     Menu_InGame_View_Terrain.this.getPosX() + iTranslateX,
                     Menu_InGame_View_Terrain.this.getPosY()
                        - ImageManager.getImage(Images.pix255_255_255).getHeight()
                        - ImageManager.getImage(Images.slider_gradient).getHeight(),
                     Menu_InGame_View_Terrain.this.getWidth() / 4,
                     1
                  );
               ImageManager.getImage(Images.slider_gradient)
                  .draw(
                     oSB,
                     Menu_InGame_View_Terrain.this.getPosX()
                        + Menu_InGame_View_Terrain.this.getWidth()
                        - Menu_InGame_View_Terrain.this.getWidth() / 4
                        + iTranslateX,
                     Menu_InGame_View_Terrain.this.getPosY()
                        - ImageManager.getImage(Images.pix255_255_255).getHeight()
                        - ImageManager.getImage(Images.slider_gradient).getHeight(),
                     Menu_InGame_View_Terrain.this.getWidth() / 4,
                     1,
                     true,
                     false
                  );
               oSB.setColor(Color.WHITE);
               CFG.fontMain.getData().setScale(0.8F);
               CFG.drawText(
                  oSB,
                  this.getText(),
                  nPosX + nWidth / 2 - (int)(this.getTextWidth() * 0.8F / 2.0F) + iTranslateX,
                  nPosY - this.getHeight() + this.getHeight() / 2 + 1 - (int)(this.getTextHeight() * 0.8F / 2.0F),
                  Color.WHITE
               );
               CFG.fontMain.getData().setScale(1.0F);
            }
         },
         0,
         ImageManager.getImage(Images.top_left).getHeight() + CFG.PADDING * 3 + CFG.BUTTON_HEIGHT * 3 / 5,
         tempW,
         Math.min(
            tY + 1,
            CFG.isAndroid() && !CFG.LANDSCAPE
               ? (
                     CFG.GAME_HEIGHT
                        - (
                           ImageManager.getImage(Images.top_left).getHeight()
                              + CFG.PADDING * 3
                              + CFG.BUTTON_HEIGHT * 3 / 4
                              + (CFG.PADDING * 2 + CFG.BUTTON_HEIGHT) * 2
                        )
                  )
                  / 2
               : CFG.GAME_HEIGHT
                  - (
                     ImageManager.getImage(Images.top_left).getHeight()
                        + CFG.PADDING * 3
                        + CFG.BUTTON_HEIGHT * 3 / 4
                        + (CFG.PADDING * 2 + CFG.BUTTON_HEIGHT) * 2
                  )
         ),
         menuElements,
         false,
         true
      );
      this.updateLanguage();
   }

   @Override
   public void updateLanguage() {
   }

   @Override
   public void drawScrollPos(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      if (sliderMenuIsActive) {
         super.drawScrollPos(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
      }
   }

   @Override
   public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      if (lTime + 175L >= System.currentTimeMillis()) {
         int var5;
         int var6;
         iTranslateX = hideAnimation
            ? (var5 = iTranslateX - (int)(this.getWidth() * ((float)(System.currentTimeMillis() - lTime) / 175.0F)))
            : (var6 = iTranslateX + -this.getWidth() + (int)(this.getWidth() * ((float)(System.currentTimeMillis() - lTime) / 175.0F)));
         CFG.setRender_3(true);
      } else if (hideAnimation) {
         super.setVisible(false);
         return;
      }

      ImageManager.getImage(Images.new_game_top_edge_line)
         .draw2(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.new_game_top_edge_line).getHeight() + iTranslateY,
            this.getWidth() + 2,
            this.getHeight() + CFG.PADDING,
            true,
            true
         );
      oSB.setColor(new Color(0.09803922F, 0.05882353F, 0.37254903F, 0.25F));
      ImageManager.getImage(Images.gradient)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY,
            this.getWidth(),
            CFG.PADDING * 4
         );
      oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.65F));
      ImageManager.getImage(Images.gradient)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY,
            this.getWidth(),
            CFG.PADDING * 2
         );
      oSB.setColor(Color.WHITE);
      super.draw(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
      oSB.setColor(CFG.COLOR_CREATE_NEW_GAME_BOX_PLAYERS);
      ImageManager.getImage(Images.pix255_255_255)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + this.getHeight() + CFG.PADDING,
            this.getWidth()
         );
      oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.4F));
      ImageManager.getImage(Images.slider_gradient)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY()
               - ImageManager.getImage(Images.pix255_255_255).getHeight()
               - ImageManager.getImage(Images.slider_gradient).getHeight()
               + this.getHeight()
               + CFG.PADDING,
            this.getWidth(),
            1
         );
      oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.5F));
      ImageManager.getImage(Images.pix255_255_255)
         .draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() + CFG.PADDING, this.getWidth() + 2);
      oSB.setColor(Color.WHITE);
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

   public final void setHideAnimation(boolean nHideAnimation) {
      if (nHideAnimation != hideAnimation) {
         lTime = lTime > System.currentTimeMillis() - 175L
            ? System.currentTimeMillis() - (175L - (System.currentTimeMillis() - lTime))
            : System.currentTimeMillis();
         CFG.setRender_3(true);
      }

      hideAnimation = nHideAnimation;
   }
}
