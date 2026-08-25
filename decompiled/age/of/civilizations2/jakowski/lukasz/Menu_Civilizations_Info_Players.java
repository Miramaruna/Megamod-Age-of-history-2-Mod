package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

public class Menu_Civilizations_Info_Players extends SliderMenu {
   public Menu_Civilizations_Info_Players() {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      menuElements.add(
         new Button_New_Game_Players(null, -1, CFG.PADDING + 2, CFG.PADDING, CFG.CIV_INFO_MENU_WIDTH - CFG.PADDING * 2 - 2, true) {
            @Override
            public void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
               super.drawButtonBG(oSB, iTranslateX, iTranslateY, isActive);
               if (CFG.game.getCiv(CFG.getActiveCivInfo()).getControlledByPlayer()) {
                  ImageManager.getImage(Images.randomCivilizationFlag)
                     .draw(
                        oSB,
                        this.getPosX() + CFG.PADDING * 2 + iTranslateX,
                        this.getPosY()
                           + this.getHeight() / 2
                           - CFG.CIV_FLAG_HEIGHT / 2
                           - ImageManager.getImage(Images.randomCivilizationFlag).getHeight()
                           + iTranslateY,
                        CFG.CIV_FLAG_WIDTH,
                        CFG.CIV_FLAG_HEIGHT
                     );
               } else {
                  CFG.game
                     .getCiv(CFG.getActiveCivInfo())
                     .getFlag()
                     .draw(
                        oSB,
                        this.getPosX() + CFG.PADDING * 2 + iTranslateX,
                        this.getPosY()
                           + this.getHeight() / 2
                           - CFG.CIV_FLAG_HEIGHT / 2
                           - CFG.game.getCiv(CFG.getActiveCivInfo()).getFlag().getHeight()
                           + iTranslateY,
                        CFG.CIV_FLAG_WIDTH,
                        CFG.CIV_FLAG_HEIGHT
                     );
               }

               ImageManager.getImage(Images.flag_rect)
                  .draw(oSB, this.getPosX() + CFG.PADDING * 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 + iTranslateY);
            }

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
         }
      );
      menuElements.add(
         new Button_New_Game_Players_Player(
            null, -1, CFG.PADDING + 2, CFG.PADDING + menuElements.get(0).getHeight() + CFG.PADDING, CFG.CIV_INFO_MENU_WIDTH - CFG.PADDING * 2 - 2, true
         ) {
            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getPlayer(0).getCivID()));
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Player") + " 1"));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
         }
      );

      for (int i = 1; i < CFG.game.getPlayersSize(); i++) {
         menuElements.add(
            new Button_New_Game_Players_Players_LEFT(
               null,
               -1,
               CFG.PADDING + 2,
               CFG.PADDING + (menuElements.get(0).getHeight() + CFG.PADDING) * (i + 1),
               CFG.CIV_INFO_MENU_WIDTH - CFG.PADDING * 2 - 2 - (int)(CFG.BUTTON_HEIGHT * 0.6F),
               true,
               i
            ) {
               @Override
               public void buildElementHover() {
                  ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
                  ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
                  nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getPlayer(this.getCurrent()).getCivID()));
                  nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Player") + " " + (this.getCurrent() + 1)));
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
         new SliderMenuTitle(null, CFG.TEXT_HEIGHT + CFG.PADDING * 2, false, false) {
            @Override
            public void draw(SpriteBatch oSB, int iTranslateX, int nPosX, int nPosY, int nWidth, boolean sliderMenuIsActive) {
               ImageManager.getImage(Images.new_game_top_edge_line)
                  .draw2(
                     oSB,
                     Menu_Civilizations_Info_Players.this.getPosX() + iTranslateX,
                     Menu_Civilizations_Info_Players.this.getPosY() - ImageManager.getImage(Images.new_game_top_edge_line).getHeight() - this.getHeight(),
                     Menu_Civilizations_Info_Players.this.getWidth(),
                     this.getHeight()
                  );
               CFG.drawRect_InfoBox_Right_Title(
                  oSB,
                  Menu_Civilizations_Info_Players.this.getPosX() + 2 + iTranslateX,
                  Menu_Civilizations_Info_Players.this.getPosY() - this.getHeight(),
                  Menu_Civilizations_Info_Players.this.getWidth(),
                  this.getHeight()
               );
               CFG.fontMain.getData().setScale(0.7F);
               CFG.drawTextWithShadow(
                  oSB,
                  this.getText() + CFG.game.getPlayersSize(),
                  nPosX + nWidth / 2 - (int)(this.getTextWidth() * 0.7F) / 2 + iTranslateX,
                  nPosY - this.getHeight() + this.getHeight() / 2 - (int)(this.getTextHeight() * 0.7F) / 2,
                  CFG.COLOR_TEXT_CIV_INFO_TITLE
               );
               CFG.fontMain.getData().setScale(1.0F);
            }
         },
         CFG.GAME_WIDTH - CFG.CIV_INFO_MENU_WIDTH,
         ImageManager.getImage(Images.new_game_top).getHeight()
            + CFG.PADDING * 4
            + (int)(CFG.TEXT_HEIGHT * 0.6F)
            + ImageManager.getImage(Images.top_flag_frame).getHeight()
            + CFG.PADDING * 4
            + CFG.TEXT_HEIGHT
            + CFG.PADDING * 2
            + CFG.PADDING * 2,
         CFG.CIV_INFO_MENU_WIDTH,
         CFG.BUTTON_HEIGHT * 2 + CFG.PADDING * 2 > menuElements.get(menuElements.size() - 1).getPosY() + menuElements.get(menuElements.size() - 1).getHeight()
            ? menuElements.get(menuElements.size() - 1).getPosY() + menuElements.get(menuElements.size() - 1).getHeight()
            : CFG.BUTTON_HEIGHT * 2 + CFG.PADDING * 2,
         menuElements,
         false,
         false
      );
      this.updateLanguage();
   }

   @Override
   public void updateLanguage() {
      this.getTitle().setText(CFG.langManager.get("Players") + ": ");
      this.getMenuElement(0).setText(CFG.langManager.get("AddPlayer"));
   }

   @Override
   public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      if (Menu_Civilization_Info.lTime + 250L >= System.currentTimeMillis()) {
         iTranslateX += this.getWidth() - (int)(this.getWidth() * ((float)(System.currentTimeMillis() - Menu_Civilization_Info.lTime) / 250.0F));
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
            boolean bRandomCiv = false;
            if (CFG.getActiveCivInfo() > 0) {
               for (int ix = 0; ix < CFG.game.getPlayersSize(); ix++) {
                  if (CFG.game.getPlayer(ix).getCivID() == CFG.getActiveCivInfo()) {
                     bRandomCiv = true;
                     break;
                  }
               }
            }

            CFG.game.addPlayer();
            if (!bRandomCiv) {
               CFG.game.getPlayer(CFG.game.getPlayersSize() - 1).setCivID(CFG.getActiveCivInfo());
            }

            CFG.toast
               .setInView(CFG.game.getCiv(CFG.game.getPlayer(CFG.game.getPlayersSize() - 1).getCivID()).getCivName() + " - " + CFG.langManager.get("Added"));
            return;
         case 1:
            if (!CFG.game.getCiv(CFG.getActiveCivInfo()).getControlledByPlayer()) {
               CFG.game.disableDrawCivilizationRegions(CFG.game.getPlayer(0).getCivID());
               CFG.game.getPlayer(0).setCivID(CFG.getActiveCivInfo());
               CFG.game.enableDrawCivilizationRegions(CFG.game.getPlayer(0).getCivID(), 0);
            } else if (CFG.game.getPlayer(0).getCivID() != CFG.getActiveCivInfo()) {
               for (int i = 1; i < CFG.game.getPlayersSize(); i++) {
                  if (CFG.game.getPlayer(i).getCivID() == CFG.getActiveCivInfo()) {
                     int tempCiv = CFG.game.getPlayer(0).getCivID();
                     CFG.game.getPlayer(0).setCivID(CFG.game.getPlayer(i).getCivID());
                     CFG.game.getPlayer(i).setCivID(tempCiv);
                     if (CFG.game.getPlayer(0).getCivID() > 0) {
                        CFG.game.getCiv(CFG.game.getPlayer(0).getCivID()).setControlledByPlayer(true);
                     }

                     if (CFG.game.getPlayer(i).getCivID() > 0) {
                        CFG.game.getCiv(CFG.game.getPlayer(i).getCivID()).setControlledByPlayer(true);
                     }

                     return;
                  }
               }
            } else if (CFG.game.getPlayer(0).getCivID() > 0) {
               CFG.game.disableDrawCivilizationRegions(CFG.game.getPlayer(0).getCivID());
               if (CFG.game.getPlayer(0).getCivID() > 0) {
                  CFG.game.getCiv(CFG.game.getPlayer(0).getCivID()).setControlledByPlayer(false);
               }

               CFG.game.getPlayer(0).setCivID(-1);
            }

            return;
         default:
            try {
               if (iID % 2 == 0) {
                  if (!CFG.game.getCiv(CFG.getActiveCivInfo()).getControlledByPlayer()) {
                     CFG.game.disableDrawCivilizationRegions(CFG.game.getPlayer(iID / 2).getCivID());
                     CFG.game.getPlayer(iID / 2).setCivID(CFG.getActiveCivInfo());
                     CFG.game.enableDrawCivilizationRegions(CFG.game.getPlayer(iID / 2).getCivID(), 0);
                  } else if (CFG.game.getPlayer(iID / 2).getCivID() != CFG.getActiveCivInfo()) {
                     for (int ixx = 0; ixx < CFG.game.getPlayersSize(); ixx++) {
                        if (ixx != iID / 2 && CFG.game.getPlayer(ixx).getCivID() == CFG.getActiveCivInfo()) {
                           int tempCivx = CFG.game.getPlayer(iID / 2).getCivID();
                           CFG.game.getPlayer(iID / 2).setCivID(CFG.game.getPlayer(ixx).getCivID());
                           CFG.game.getPlayer(ixx).setCivID(tempCivx);
                           if (CFG.game.getPlayer(iID / 2).getCivID() > 0) {
                              CFG.game.getCiv(CFG.game.getPlayer(iID / 2).getCivID()).setControlledByPlayer(true);
                           }

                           if (CFG.game.getPlayer(ixx).getCivID() > 0) {
                              CFG.game.getCiv(CFG.game.getPlayer(ixx).getCivID()).setControlledByPlayer(true);
                           }

                           return;
                        }
                     }
                  } else if (CFG.game.getPlayer(iID / 2).getCivID() > 0) {
                     CFG.game.disableDrawCivilizationRegions(CFG.game.getPlayer(iID / 2).getCivID());
                     if (CFG.game.getPlayer(iID / 2).getCivID() > 0) {
                        CFG.game.getCiv(CFG.game.getPlayer(iID / 2).getCivID()).setControlledByPlayer(false);
                     }

                     CFG.game.getPlayer(iID / 2).setCivID(-1);
                  }
               } else {
                  CFG.game.disableDrawCivilizationRegions(CFG.game.getPlayer(iID / 2).getCivID());
                  CFG.game.removePlayer(iID / 2);
               }
            } catch (IndexOutOfBoundsException var4) {
               CFG.menuManager.rebuildCivilizations_Info_Players();
            }
      }
   }

   @Override
   public void drawScrollPos(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      if (sliderMenuIsActive) {
         super.drawScrollPos(oSB, iTranslateX - 2, iTranslateY, sliderMenuIsActive);
      }
   }
}
