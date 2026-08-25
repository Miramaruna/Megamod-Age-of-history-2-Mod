package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

public class Menu_RandomGame_Players extends SliderMenu {
   public static final int ANIMATION_TIME = 250;
   public static long lTime = 0L;

   public Menu_RandomGame_Players() {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      menuElements.add(new Button_New_Game_Players(null, -1, CFG.PADDING + 2, CFG.PADDING, CFG.CIV_INFO_MENU_WIDTH - CFG.PADDING * 2 - 2, true) {
         @Override
         public void buildElementHover() {
            ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
            ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("AddaPlayerToTheGame"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
            if (CFG.game.getCiv(CFG.getActiveCivInfo()).getControlledByPlayer()) {
               nData.add(new MenuElement_Hover_v2_Element_Type_Flag(-1, CFG.PADDING, 0));
            } else {
               nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.getActiveCivInfo(), CFG.PADDING, 0));
            }

            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
            this.menuElementHover = new MenuElement_Hover_v2(nElements);
         }
      });
      menuElements.add(
         new Button_RandomGame_Players_LEFT(
            null,
            -1,
            CFG.PADDING + 2,
            CFG.PADDING + menuElements.get(0).getHeight() + CFG.PADDING,
            CFG.CIV_INFO_MENU_WIDTH - CFG.PADDING * 2 - 2 - (int)(CFG.BUTTON_HEIGHT * 0.6F),
            true,
            0
         )
      );
      menuElements.add(
         new Button_RandomGame_Players_Localization(
            null,
            -1,
            CFG.CIV_INFO_MENU_WIDTH - CFG.PADDING - (int)(CFG.BUTTON_HEIGHT * 0.6F),
            CFG.PADDING + menuElements.get(0).getHeight() + CFG.PADDING,
            (int)(CFG.BUTTON_HEIGHT * 0.6F),
            true
         ) {
            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("SetCapital"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
         }
      );

      for (int i = 1; i < CFG.randomGameManager.getPlayersSize(); i++) {
         menuElements.add(
            new Button_RandomGame_Players_LEFT(
               null,
               -1,
               CFG.PADDING + 2,
               CFG.PADDING + (menuElements.get(0).getHeight() + CFG.PADDING) * (i + 1),
               CFG.CIV_INFO_MENU_WIDTH - CFG.PADDING * 2 - 2 - (int)(CFG.BUTTON_HEIGHT * 0.6F) * 2,
               true,
               i
            )
         );
         menuElements.add(
            new Button_RandomGame_Players_Localization_Middle(
               null,
               -1,
               CFG.CIV_INFO_MENU_WIDTH - CFG.PADDING - (int)(CFG.BUTTON_HEIGHT * 0.6F) * 2,
               CFG.PADDING + (menuElements.get(0).getHeight() + CFG.PADDING) * (i + 1),
               (int)(CFG.BUTTON_HEIGHT * 0.6F),
               true
            ) {
               @Override
               public void buildElementHover() {
                  ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
                  ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
                  nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("SetCapital"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                  nElements.add(new MenuElement_Hover_v2_Element2(nData));
                  nData.clear();
                  this.menuElementHover = new MenuElement_Hover_v2(nElements);
               }
            }
         );
         menuElements.add(
            new Button_New_Game_Players_Players_RIGHT(
               null,
               -1,
               CFG.CIV_INFO_MENU_WIDTH - CFG.PADDING - (int)(CFG.BUTTON_HEIGHT * 0.6F),
               CFG.PADDING + (menuElements.get(0).getHeight() + CFG.PADDING) * (i + 1),
               (int)(CFG.BUTTON_HEIGHT * 0.6F),
               true
            ) {
               @Override
               public void buildElementHover() {
                  ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
                  ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
                  nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Delete"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                  nElements.add(new MenuElement_Hover_v2_Element2(nData));
                  nData.clear();
                  this.menuElementHover = new MenuElement_Hover_v2(nElements);
               }
            }
         );
      }

      this.initMenu(
         new SliderMenuTitle(null, CFG.BUTTON_HEIGHT * 3 / 4, false, false) {
            @Override
            public void draw(SpriteBatch oSB, int iTranslateX, int nPosX, int nPosY, int nWidth, boolean sliderMenuIsActive) {
               ImageManager.getImage(Images.new_game_top_edge_title)
                  .draw2(
                     oSB,
                     Menu_RandomGame_Players.this.getPosX() + iTranslateX,
                     Menu_RandomGame_Players.this.getPosY() - ImageManager.getImage(Images.new_game_top_edge_title).getHeight() - this.getHeight(),
                     Menu_RandomGame_Players.this.getWidth() + 2,
                     this.getHeight(),
                     false,
                     false
                  );
               oSB.setColor(new Color(0.011F, 0.014F, 0.019F, 0.25F));
               ImageManager.getImage(Images.gradient)
                  .draw(
                     oSB,
                     Menu_RandomGame_Players.this.getPosX() + iTranslateX,
                     Menu_RandomGame_Players.this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() - this.getHeight() * 3 / 4,
                     Menu_RandomGame_Players.this.getWidth(),
                     this.getHeight() * 3 / 4,
                     false,
                     true
                  );
               oSB.setColor(new Color(0.451F, 0.329F, 0.11F, 1.0F));
               ImageManager.getImage(Images.pix255_255_255)
                  .draw(
                     oSB,
                     Menu_RandomGame_Players.this.getPosX() + iTranslateX,
                     Menu_RandomGame_Players.this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight(),
                     Menu_RandomGame_Players.this.getWidth()
                  );
               oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.4F));
               ImageManager.getImage(Images.line_32_off1)
                  .draw(
                     oSB,
                     Menu_RandomGame_Players.this.getPosX() + iTranslateX,
                     Menu_RandomGame_Players.this.getPosY()
                        - ImageManager.getImage(Images.pix255_255_255).getHeight()
                        - ImageManager.getImage(Images.line_32_off1).getHeight(),
                     Menu_RandomGame_Players.this.getWidth(),
                     1
                  );
               oSB.setColor(Color.WHITE);
               CFG.fontMain.getData().setScale(0.8F);
               CFG.drawText(
                  oSB,
                  this.getText(),
                  nPosX + nWidth / 2 - (int)(this.getTextWidth() * 0.8F / 2.0F) + iTranslateX,
                  nPosY - this.getHeight() + this.getHeight() / 2 + 1 - (int)(this.getTextHeight() * 0.8F / 2.0F),
                  CFG.COLOR_TEXT_OPTIONS_LEFT_NS
               );
               CFG.fontMain.getData().setScale(1.0F);
            }
         },
         CFG.GAME_WIDTH - CFG.CIV_INFO_MENU_WIDTH,
         ImageManager.getImage(Images.top_left).getHeight() + CFG.PADDING * 2 + CFG.BUTTON_HEIGHT * 3 / 4,
         CFG.CIV_INFO_MENU_WIDTH,
         menuElements.get(menuElements.size() - 1).getPosY() + menuElements.get(menuElements.size() - 1).getHeight()
               > CFG.GAME_HEIGHT
                  - CFG.BUTTON_HEIGHT
                  - CFG.PADDING * 4
                  - (ImageManager.getImage(Images.top_left).getHeight() + CFG.PADDING * 2 + CFG.BUTTON_HEIGHT * 3 / 4)
            ? CFG.GAME_HEIGHT
               - CFG.BUTTON_HEIGHT
               - CFG.PADDING * 4
               - (ImageManager.getImage(Images.top_left).getHeight() + CFG.PADDING * 2 + CFG.BUTTON_HEIGHT * 3 / 4)
            : menuElements.get(menuElements.size() - 1).getPosY() + menuElements.get(menuElements.size() - 1).getHeight(),
         menuElements,
         true,
         true
      );
      this.updateLanguage();
   }

   @Override
   public void updateLanguage() {
      this.getTitle().setText(CFG.langManager.get("Players") + ": " + CFG.randomGameManager.getPlayersSize());
      this.getMenuElement(0).setText(CFG.langManager.get("AddPlayer"));
   }

   @Override
   public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      if (lTime + 250L >= System.currentTimeMillis()) {
         iTranslateX += this.getWidth() - (int)(this.getWidth() * ((float)(System.currentTimeMillis() - lTime) / 250.0F));
         CFG.setRender_3(true);
      }

      ImageManager.getImage(Images.new_game_top_edge)
         .draw2(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.new_game_top_edge).getHeight(),
            this.getWidth(),
            this.getHeight() + CFG.PADDING + 2,
            false,
            true
         );
      this.beginClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
      this.drawMenu(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
      this.endClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
   }

   @Override
   public void actionElement(int iID) {
      switch (iID) {
         case 0:
            CFG.randomGameManager.addPlayer();
            CFG.menuManager.rebuildCreateRandomGame_Players();
            CFG.toast.setInView(CFG.langManager.get("Added"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE);
            return;
         case 1:
            CFG.MANAGE_DIPLOMACY_CUSTOMIZE_ALLIANCE_ID = 0;
            CFG.menuManager.setViewID(Menu.eCREATE_RANDOM_GAME_CIVILIZATIONS_SELECT);
            return;
         case 2:
            if (CFG.randomGameManager.getPlayer(0).getCapitalProvinceID() == CFG.game.getActiveProvinceID()) {
               CFG.randomGameManager.getPlayer(0).setCapitalProvinceID(-1);
               CFG.toast.setInView(CFG.langManager.get("Random"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE);
            } else if (!CFG.game.getProvince(CFG.game.getActiveProvinceID()).getSeaProvince()
               && CFG.game.getProvince(CFG.game.getActiveProvinceID()).getWasteland() < 0) {
               for (int i = 0; i < CFG.randomGameManager.getPlayersSize(); i++) {
                  if (CFG.randomGameManager.getPlayer(i).getCapitalProvinceID() == CFG.game.getActiveProvinceID()) {
                     CFG.randomGameManager.getPlayer(i).setCapitalProvinceID(-1);
                  }
               }

               CFG.randomGameManager.getPlayer(0).setCapitalProvinceID(CFG.game.getActiveProvinceID());
               CFG.toast.setInView(CFG.langManager.get("Updated"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE);
            }

            return;
         default:
            if ((iID - 3) % 3 == 0) {
               CFG.MANAGE_DIPLOMACY_CUSTOMIZE_ALLIANCE_ID = (iID - 3) / 3 + 1;
               CFG.menuManager.setViewID(Menu.eCREATE_RANDOM_GAME_CIVILIZATIONS_SELECT);
            } else if ((iID - 3) % 3 == 1) {
               if (CFG.randomGameManager.getPlayer((iID - 3) / 3 + 1).getCapitalProvinceID() == CFG.game.getActiveProvinceID()) {
                  CFG.randomGameManager.getPlayer((iID - 3) / 3 + 1).setCapitalProvinceID(-1);
                  CFG.toast.setInView(CFG.langManager.get("Random"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE);
               } else if (!CFG.game.getProvince(CFG.game.getActiveProvinceID()).getSeaProvince()
                  && CFG.game.getProvince(CFG.game.getActiveProvinceID()).getWasteland() < 0) {
                  for (int ix = 0; ix < CFG.randomGameManager.getPlayersSize(); ix++) {
                     if (CFG.randomGameManager.getPlayer(ix).getCapitalProvinceID() == CFG.game.getActiveProvinceID()) {
                        CFG.randomGameManager.getPlayer(ix).setCapitalProvinceID(-1);
                     }
                  }

                  CFG.randomGameManager.getPlayer((iID - 3) / 3 + 1).setCapitalProvinceID(CFG.game.getActiveProvinceID());
                  CFG.toast.setInView(CFG.langManager.get("Updated"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE);
               }
            } else if ((iID - 3) % 3 == 2) {
               CFG.randomGameManager.removePlayer((iID - 3) / 3 + 1);
               CFG.menuManager.rebuildCreateRandomGame_Players();
               CFG.toast.setInView(CFG.langManager.get("Deleted"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE);
            }
      }
   }

   @Override
   public void drawScrollPos(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      if (sliderMenuIsActive) {
         super.drawScrollPos(oSB, iTranslateX - 2, iTranslateY, sliderMenuIsActive);
      }
   }

   @Override
   public void setVisible(boolean visible) {
      if (visible && !this.getVisible()) {
         lTime = System.currentTimeMillis();
      }

      super.setVisible(visible);
   }
}
