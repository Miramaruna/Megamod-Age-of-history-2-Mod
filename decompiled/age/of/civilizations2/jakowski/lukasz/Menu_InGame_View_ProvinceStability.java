package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

public class Menu_InGame_View_ProvinceStability extends SliderMenu {
   public static final int ANIMATION_TIME = 175;
   public static long lTime = 0L;
   public static boolean hideAnimation = true;
   public int iCivID = 0;

   public Menu_InGame_View_ProvinceStability() {
      int tempW = CFG.CIV_INFO_MENU_WIDTH;
      int tY = 0;
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      this.iCivID = CFG.getActiveCivInfo_BasedOnActiveProvinceID(CFG.game.getActiveProvinceID());
      ArrayList tempProvincesSorted = new ArrayList();
      ArrayList<Integer> tempProvs = new ArrayList<>();

      for (int i = 0; i < CFG.game.getCiv(this.iCivID).getNumOfProvinces(); i++) {
         if (CFG.FOG_OF_WAR != 2 || CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetProvince(CFG.game.getCiv(this.iCivID).getProvinceID(i))) {
            tempProvs.add(CFG.game.getCiv(this.iCivID).getProvinceID(i));
         }
      }

      while (tempProvs.size() > 0) {
         int tBest = 0;

         for (int i2 = 1; i2 < tempProvs.size(); i2++) {
            if (CFG.game.getProvince(tempProvs.get(tBest)).getProvinceStability() < CFG.game.getProvince(tempProvs.get(i2)).getProvinceStability()) {
               tBest = i2;
            }
         }

         tempProvincesSorted.add(tempProvs.get(tBest));
         tempProvs.remove(tBest);
      }

      if (tempProvincesSorted.size() > 0) {
         menuElements.add(
            new Button_NS_Population(
               new Color(
                  CFG.game.getCiv(this.iCivID).getR() / 255.0F,
                  CFG.game.getCiv(this.iCivID).getG() / 255.0F,
                  CFG.game.getCiv(this.iCivID).getB() / 255.0F,
                  1.0F
               ),
               CFG.game.getCiv(this.iCivID).getCivName(),
               this.iCivID,
               CFG.langManager.get("Stability") + ": ",
               (int)(CFG.game.getCiv(this.iCivID).getStability() * 100.0F) + "%",
               Images.diplo_popstability,
               CFG.getColorStep(
                  CFG.COLOR_TEXT_PROVINCE_STABILITY_MIN,
                  CFG.COLOR_TEXT_PROVINCE_STABILITY_MAX,
                  (int)(CFG.game.getCiv(this.iCivID).getStability() * 100.0F),
                  100,
                  1.0F
               ),
               0,
               tY,
               tempW
            ) {}
         );
         tY += menuElements.get(menuElements.size() - 1).getHeight();

         for (int var9 = tempProvincesSorted.size() - 1; var9 >= 0; var9--) {
            menuElements.add(
               new Button_View_ProvinceStability(
                  var9,
                  CFG.game.getProvince((Integer)tempProvincesSorted.get(var9)).getName().length() > 0
                     ? CFG.game.getProvince((Integer)tempProvincesSorted.get(var9)).getName()
                     : CFG.game.getCiv(this.iCivID).getCivName(),
                  (Integer)tempProvincesSorted.get(var9),
                  0,
                  tY,
                  CFG.CIV_INFO_MENU_WIDTH,
                  (CFG.SPECTATOR_MODE || CFG.game.isAlly(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), this.iCivID))
                     && CFG.game
                        .getCiv(CFG.game.getProvince((Integer)tempProvincesSorted.get(var9)).getCivID())
                        .isAssimilateOrganized((Integer)tempProvincesSorted.get(var9))
               ) {
                  @Override
                  public void actionElement(int iID) {
                     if (CFG.gameAction.getActiveTurnState() != Game_Action.TurnStates.INPUT_ORDERS
                        || CFG.SPECTATOR_MODE
                        || this.getCurrent() != CFG.game.getActiveProvinceID()
                        || CFG.game.getProvince(this.getCurrent()).getCivID() != CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()
                        || CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).isAssimilateOrganized(this.getCurrent())) {
                        CFG.game.setActiveProvinceID(this.getCurrent());
                        CFG.map.getMapCoordinates().centerToProvinceID(CFG.game.getActiveProvinceID());
                        if (CFG.game.getProvince(CFG.game.getActiveProvinceID()).getName().length() > 0) {
                           CFG.toast.setInView(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getName(), CFG.COLOR_TEXT_NUM_OF_PROVINCES);
                        }
                     } else if (this.getCurrent() >= 0) {
                        CFG.menuManager.rebuildInGame_Assimilate(this.getCurrent());
                     }
                  }
               }
            );
            tY += menuElements.get(menuElements.size() - 1).getHeight();
         }
      } else {
         menuElements.add(new Text_Scale(CFG.langManager.get("NoData"), -1, 0, tY, tempW, CFG.BUTTON_HEIGHT * 3 / 4, 0.75F));
         tY += menuElements.get(menuElements.size() - 1).getHeight();
      }

      this.initMenu(
         new SliderMenuTitle(CFG.game.getCiv(this.iCivID).getCivName(), CFG.BUTTON_HEIGHT * 3 / 5, false, false) {
            @Override
            public void draw(SpriteBatch oSB, int iTranslateX, int nPosX, int nPosY, int nWidth, boolean sliderMenuIsActive) {
               ImageManager.getImage(Images.new_game_top_edge_title)
                  .draw2(
                     oSB,
                     Menu_InGame_View_ProvinceStability.this.getPosX() + iTranslateX,
                     Menu_InGame_View_ProvinceStability.this.getPosY() - ImageManager.getImage(Images.new_game_top_edge_title).getHeight() - this.getHeight(),
                     Menu_InGame_View_ProvinceStability.this.getWidth() + 2,
                     this.getHeight(),
                     true,
                     false
                  );
               oSB.setColor(
                  new Color(CFG.COLOR_TEXT_PROVINCE_STABILITY_MAX.r, CFG.COLOR_TEXT_PROVINCE_STABILITY_MAX.g, CFG.COLOR_TEXT_PROVINCE_STABILITY_MAX.b, 0.165F)
               );
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
               oSB.setColor(
                  new Color(CFG.COLOR_TEXT_PROVINCE_STABILITY_MAX.r, CFG.COLOR_TEXT_PROVINCE_STABILITY_MAX.g, CFG.COLOR_TEXT_PROVINCE_STABILITY_MAX.b, 0.375F)
               );
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
                     Menu_InGame_View_ProvinceStability.this.getPosX() + iTranslateX,
                     Menu_InGame_View_ProvinceStability.this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight(),
                     Menu_InGame_View_ProvinceStability.this.getWidth()
                  );
               oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.4F));
               ImageManager.getImage(Images.line_32_off1)
                  .draw(
                     oSB,
                     Menu_InGame_View_ProvinceStability.this.getPosX() + iTranslateX,
                     Menu_InGame_View_ProvinceStability.this.getPosY()
                        - ImageManager.getImage(Images.pix255_255_255).getHeight()
                        - ImageManager.getImage(Images.line_32_off1).getHeight(),
                     Menu_InGame_View_ProvinceStability.this.getWidth(),
                     1
                  );
               oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.6F));
               ImageManager.getImage(Images.line_32_off1)
                  .draw(
                     oSB,
                     Menu_InGame_View_ProvinceStability.this.getPosX() + iTranslateX,
                     Menu_InGame_View_ProvinceStability.this.getPosY()
                        - ImageManager.getImage(Images.pix255_255_255).getHeight()
                        - ImageManager.getImage(Images.line_32_off1).getHeight()
                        - 1,
                     Menu_InGame_View_ProvinceStability.this.getWidth(),
                     1
                  );
               oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.8F));
               ImageManager.getImage(Images.slider_gradient)
                  .draw(
                     oSB,
                     Menu_InGame_View_ProvinceStability.this.getPosX() + iTranslateX,
                     Menu_InGame_View_ProvinceStability.this.getPosY()
                        - ImageManager.getImage(Images.pix255_255_255).getHeight()
                        - ImageManager.getImage(Images.slider_gradient).getHeight(),
                     Menu_InGame_View_ProvinceStability.this.getWidth() / 4,
                     1
                  );
               ImageManager.getImage(Images.slider_gradient)
                  .draw(
                     oSB,
                     Menu_InGame_View_ProvinceStability.this.getPosX()
                        + Menu_InGame_View_ProvinceStability.this.getWidth()
                        - Menu_InGame_View_ProvinceStability.this.getWidth() / 4
                        + iTranslateX,
                     Menu_InGame_View_ProvinceStability.this.getPosY()
                        - ImageManager.getImage(Images.pix255_255_255).getHeight()
                        - ImageManager.getImage(Images.slider_gradient).getHeight(),
                     Menu_InGame_View_ProvinceStability.this.getWidth() / 4,
                     1,
                     true,
                     false
                  );
               oSB.setColor(Color.WHITE);
               ImageManager.getImage(Images.diplo_popstability)
                  .draw(
                     oSB,
                     nPosX + CFG.PADDING * 2 + iTranslateX,
                     Menu_InGame_View_ProvinceStability.this.getPosY()
                        - this.getHeight() / 2
                        - ImageManager.getImage(Images.diplo_popstability).getHeight() / 2
                  );
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
