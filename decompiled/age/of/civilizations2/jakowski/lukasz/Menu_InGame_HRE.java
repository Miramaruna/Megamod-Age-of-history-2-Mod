package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.utils.ScissorStack;
import java.util.ArrayList;

public class Menu_InGame_HRE extends SliderMenu {
   public static long lTime = 0L;
   public int iProvinceID = -1;

   public Menu_InGame_HRE() {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      int tempWidth = CFG.CIV_INFO_MENU_WIDTH + CFG.CIV_INFO_MENU_WIDTH * 3 / 4;
      int tY = CFG.PADDING;
      menuElements.add(new Button_Flag_JustFrame(CFG.PADDING, tY, true));
      tY += menuElements.get(menuElements.size() - 1).getHeight();
      int tempMenuPosY = ImageManager.getImage(Images.top_left2).getHeight() + CFG.PADDING * 2 + CFG.BUTTON_HEIGHT * 3 / 5;
      this.initMenu(
         new SliderMenuTitle(CFG.langManager.get("HolyRomanEmpire"), CFG.BUTTON_HEIGHT * 3 / 5, true, true),
         CFG.GAME_WIDTH / 2 - tempWidth / 2,
         tempMenuPosY,
         tempWidth,
         menuElements.get(menuElements.size() - 1).getPosY() + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING + tempMenuPosY
               > CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING * 2
            ? Math.max(CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING * 2 - tempMenuPosY, (CFG.TEXT_HEIGHT + CFG.PADDING * 2) * 6)
            : menuElements.get(menuElements.size() - 1).getPosY() + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING,
         menuElements,
         false,
         true
      );
      this.updateLanguage();
   }

   public Menu_InGame_HRE(int nProvinceID) {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      this.iProvinceID = nProvinceID;
      int tempWidth = CFG.CIV_INFO_MENU_WIDTH + CFG.CIV_INFO_MENU_WIDTH * 3 / 4;
      int tY = 0;
      menuElements.add(
         new Button_HRE_Emperor(
            CFG.FOG_OF_WAR >= 2 && !CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetCivilization(CFG.holyRomanEmpire_Manager.getHRE().getEmperor())
               ? -1
               : CFG.holyRomanEmpire_Manager.getHRE().getEmperor(),
            0,
            tY,
            tempWidth
         ) {
            @Override
            public int getWidth() {
               return Menu_InGame_HRE.this.getW();
            }
         }
      );
      int var13;
      menuElements.add(
         new Button_Statistics_Elections(
            CFG.langManager.get("NextElections") + ": ",
            Game_Calendar.getDate_ByTurnID(Game_Calendar.TURN_ID + CFG.holyRomanEmpire_Manager.getHRE().getNextElectionsIn()),
            "" + CFG.langManager.get("TurnsX", CFG.holyRomanEmpire_Manager.getHRE().getNextElectionsIn()) + "",
            0,
            var13 = tY + menuElements.get(menuElements.size() - 1).getHeight(),
            tempWidth,
            CFG.TEXT_HEIGHT + CFG.PADDING * 3
         ) {
            @Override
            public int getWidth() {
               return Menu_InGame_HRE.this.getW();
            }
         }
      );
      menuElements.add(
         new Text_AlliesNotInWar(
            CFG.langManager.get("Electors"),
            -1,
            0,
            tY = var13 + menuElements.get(menuElements.size() - 1).getHeight(),
            tempWidth,
            CFG.TEXT_HEIGHT + CFG.PADDING * 3
         ) {
            @Override
            public int getWidth() {
               return Menu_InGame_HRE.this.getW();
            }
         }
      );
      tY += menuElements.get(menuElements.size() - 1).getHeight();
      if (CFG.holyRomanEmpire_Manager.getHRE().lVotesFor == null
         || CFG.holyRomanEmpire_Manager.getHRE().lVotesFor.size() != CFG.holyRomanEmpire_Manager.getHRE().getElectorsSize()) {
         CFG.holyRomanEmpire_Manager.getHRE().buildVotesFor();
         CFG.gameAction.updateHRE_VotesFor();
      }

      for (int i2 = 0; i2 < CFG.holyRomanEmpire_Manager.getHRE().getElectorsSize(); i2++) {
         menuElements.add(
            new Button_HRE_Elector(
               CFG.FOG_OF_WAR >= 2
                     && !CFG.game
                        .getPlayer(CFG.PLAYER_TURNID)
                        .getMetCivilization(CFG.holyRomanEmpire_Manager.getHRE().getPrince(CFG.holyRomanEmpire_Manager.getHRE().getElector(i2)))
                  ? -1
                  : CFG.holyRomanEmpire_Manager.getHRE().getPrince(CFG.holyRomanEmpire_Manager.getHRE().getElector(i2)),
               CFG.FOG_OF_WAR >= 2 && !CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetCivilization(CFG.holyRomanEmpire_Manager.getHRE().lVotesFor.get(i2))
                  ? -1
                  : CFG.holyRomanEmpire_Manager.getHRE().lVotesFor.get(i2),
               0,
               tY,
               tempWidth
            ) {
               @Override
               public int getWidth() {
                  return Menu_InGame_HRE.this.getW();
               }
            }
         );
         tY += menuElements.get(menuElements.size() - 1).getHeight();
         menuElements.get(menuElements.size() - 1).setCurrent(i2 % 2);
      }

      int tempMenuPosY = ImageManager.getImage(Images.top_left2).getHeight() + CFG.PADDING * 2 + CFG.BUTTON_HEIGHT * 3 / 5;
      int tempMenuHeight = menuElements.get(menuElements.size() - 1).getPosY()
               + menuElements.get(menuElements.size() - 1).getHeight()
               + CFG.PADDING
               + tempMenuPosY
            > CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING * 2
         ? Math.max(CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING * 2 - tempMenuPosY, (CFG.TEXT_HEIGHT + CFG.PADDING * 2) * 6)
         : menuElements.get(menuElements.size() - 1).getPosY() + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
      menuElements.add(
         new Text_AlliesNotInWar(
            CFG.langManager.get("Princes") + " - " + CFG.holyRomanEmpire_Manager.getHRE().getPrincesSize_True(),
            -1,
            0,
            tY,
            tempWidth,
            CFG.TEXT_HEIGHT + CFG.PADDING * 3
         ) {
            @Override
            public int getWidth() {
               return Menu_InGame_HRE.this.getW();
            }
         }
      );
      tY += menuElements.get(menuElements.size() - 1).getHeight();
      ArrayList<Integer> tempPrinces = new ArrayList<>();
      ArrayList tempSortedPrinces = new ArrayList();

      for (int i = 0; i < CFG.holyRomanEmpire_Manager.getHRE().getPrincesSize(); i++) {
         if (CFG.game.getCiv(CFG.holyRomanEmpire_Manager.getHRE().getPrince(i)).getNumOfProvinces() > 0) {
            tempPrinces.add(CFG.holyRomanEmpire_Manager.getHRE().getPrince(i));
         }
      }

      while (tempPrinces.size() > 0) {
         int tBest = 0;

         for (int i3 = 1; i3 < tempPrinces.size(); i3++) {
            if (CFG.game.getCiv(tempPrinces.get(i3)).countPopulation() > CFG.game.getCiv(tempPrinces.get(tBest)).countPopulation()) {
               tBest = i3;
            }
         }

         tempSortedPrinces.add(tempPrinces.get(tBest));
         tempPrinces.remove(tBest);
      }

      for (int var12 = 0; var12 < tempSortedPrinces.size(); var12++) {
         menuElements.add(
            new Button_Statistics_Flag_HREPrince(
               CFG.FOG_OF_WAR >= 2 && !CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetCivilization((Integer)tempSortedPrinces.get(var12))
                  ? -1
                  : (Integer)tempSortedPrinces.get(var12),
               CFG.FOG_OF_WAR >= 2 && !CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetCivilization((Integer)tempSortedPrinces.get(var12))
                  ? CFG.langManager.get("Undiscovered")
                  : CFG.game.getCiv((Integer)tempSortedPrinces.get(var12)).getCivName(),
               CFG.PADDING * 2,
               0,
               tY,
               tempWidth,
               CFG.TEXT_HEIGHT + CFG.PADDING * 3
            ) {
               @Override
               public Color getColor(boolean isActive) {
                  return isActive
                     ? CFG.COLOR_TEXT_OPTIONS_LEFT_NS_ACTIVE
                     : (
                        this.getClickable()
                           ? (this.getIsHovered() ? CFG.COLOR_TEXT_OPTIONS_LEFT_NS_HOVER : CFG.COLOR_TEXT_OPTIONS_LEFT_NS)
                           : CFG.COLOR_BUTTON_MENU_TEXT_NOT_CLICKABLE
                     );
               }

               @Override
               public void actionElement(int iID) {
                  try {
                     if (CFG.FOG_OF_WAR < 2 || CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetProvince(CFG.game.getCiv(this.getCurrent()).getCapitalProvinceID())) {
                        CFG.toast.setInView(CFG.game.getCiv(this.getCurrent()).getCivName(), CFG.COLOR_TEXT_NUM_OF_PROVINCES);
                        CFG.game.setActiveProvinceID(CFG.game.getCiv(this.getCurrent()).getCapitalProvinceID());
                        if (CFG.viewsManager.getActiveViewID() == ViewsManager.VIEW_DIPLOMACY_MODE) {
                           CFG.game.disableDrawCivilizationRegions_Active();
                           CFG.game.enableDrawCivilizationRegions_ActiveProvince();
                        }

                        if (CFG.menuManager.getVisible_InGame_CivInfo()) {
                           CFG.setActiveCivInfo(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID());
                           CFG.updateActiveCivInfo_InGame();
                        }

                        if (!CFG.game.getProvince(CFG.game.getActiveProvinceID()).getDrawProvince()) {
                           CFG.map.getMapCoordinates().centerToProvinceID(CFG.game.getActiveProvinceID());
                        }
                     }
                  } catch (IndexOutOfBoundsException var3) {
                  }
               }

               @Override
               public int getWidth() {
                  return Menu_InGame_HRE.this.getW();
               }
            }
         );
         tY += menuElements.get(menuElements.size() - 1).getHeight();
         menuElements.get(menuElements.size() - 1).setCurrent(var12 % 2);
      }

      this.initMenu(
         new SliderMenuTitle(CFG.langManager.get("HolyRomanEmpire"), CFG.BUTTON_HEIGHT * 3 / 5, true, true) {
            @Override
            public void draw(SpriteBatch oSB, int iTranslateX, int nPosX, int nPosY, int nWidth, boolean sliderMenuIsActive) {
               ImageManager.getImage(Images.dialog_title)
                  .draw2(
                     oSB,
                     nPosX - 2 + iTranslateX,
                     nPosY - this.getHeight() - ImageManager.getImage(Images.dialog_title).getHeight(),
                     nWidth + 4 - ImageManager.getImage(Images.dialog_title).getWidth(),
                     this.getHeight()
                  );
               ImageManager.getImage(Images.dialog_title)
                  .draw2(
                     oSB,
                     nPosX + nWidth + 2 - ImageManager.getImage(Images.dialog_title).getWidth() + iTranslateX,
                     nPosY - this.getHeight() - ImageManager.getImage(Images.dialog_title).getHeight(),
                     ImageManager.getImage(Images.dialog_title).getWidth(),
                     this.getHeight(),
                     true,
                     false
                  );
               oSB.setColor(new Color(0.96862745F, 0.81960785F, 0.09411765F, 0.165F));
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
               oSB.setColor(new Color(0.96862745F, 0.81960785F, 0.09411765F, 0.375F));
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
               oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.65F));
               ImageManager.getImage(Images.gradient)
                  .draw(oSB, nPosX + iTranslateX, nPosY - CFG.PADDING - ImageManager.getImage(Images.gradient).getHeight(), nWidth, CFG.PADDING, false, true);
               oSB.setColor(CFG.COLOR_NEW_GAME_EDGE_LINE);
               ImageManager.getImage(Images.pix255_255_255)
                  .draw(oSB, nPosX + iTranslateX, nPosY - 1 - ImageManager.getImage(Images.pix255_255_255).getHeight(), nWidth, 1);
               oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.55F));
               ImageManager.getImage(Images.line_32_off1)
                  .draw(oSB, nPosX + iTranslateX, nPosY - 2 - ImageManager.getImage(Images.line_32_off1).getHeight(), nWidth, 1);
               oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.8F));
               ImageManager.getImage(Images.line_32_off1)
                  .draw(oSB, nPosX + iTranslateX, nPosY - 1 - ImageManager.getImage(Images.line_32_off1).getHeight(), nWidth, 1);
               oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.45F));
               ImageManager.getImage(Images.slider_gradient)
                  .draw(oSB, nPosX + iTranslateX, nPosY - 1 - ImageManager.getImage(Images.slider_gradient).getHeight(), nWidth / 2, 1);
               ImageManager.getImage(Images.slider_gradient)
                  .draw(
                     oSB,
                     nPosX + nWidth - nWidth / 2 + iTranslateX,
                     nPosY - 1 - ImageManager.getImage(Images.slider_gradient).getHeight(),
                     nWidth / 2,
                     1,
                     true,
                     false
                  );
               oSB.setColor(Color.WHITE);
               oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.425F));
               ImageManager.getImage(Images.slider_gradient)
                  .draw(
                     oSB,
                     nPosX + CFG.PADDING * 2 + iTranslateX,
                     nPosY + 1 - this.getHeight() / 2 - ImageManager.getImage(Images.slider_gradient).getHeight(),
                     (int)((nWidth - CFG.PADDING * 6 - this.getTextWidth() * 0.8F) / 2.0F),
                     1,
                     true,
                     false
                  );
               ImageManager.getImage(Images.slider_gradient)
                  .draw(
                     oSB,
                     nPosX + nWidth / 2 + CFG.PADDING + (int)(this.getTextWidth() * 0.8F / 2.0F) + iTranslateX,
                     nPosY + 1 - this.getHeight() / 2 - ImageManager.getImage(Images.slider_gradient).getHeight(),
                     (int)((nWidth - CFG.PADDING * 6 - this.getTextWidth() * 0.8F) / 2.0F),
                     1
                  );
               oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.325F));
               ImageManager.getImage(Images.slider_gradient)
                  .draw(
                     oSB,
                     nPosX + CFG.PADDING * 2 + iTranslateX,
                     nPosY + 2 - this.getHeight() / 2 - ImageManager.getImage(Images.slider_gradient).getHeight(),
                     (int)((nWidth - CFG.PADDING * 6 - this.getTextWidth() * 0.8F) / 2.0F),
                     1,
                     true,
                     false
                  );
               ImageManager.getImage(Images.slider_gradient)
                  .draw(
                     oSB,
                     nPosX + nWidth / 2 + CFG.PADDING + (int)(this.getTextWidth() * 0.8F / 2.0F) + iTranslateX,
                     nPosY + 2 - this.getHeight() / 2 - ImageManager.getImage(Images.slider_gradient).getHeight(),
                     (int)((nWidth - CFG.PADDING * 6 - this.getTextWidth() * 0.8F) / 2.0F),
                     1
                  );
               ImageManager.getImage(Images.slider_gradient)
                  .draw(
                     oSB,
                     nPosX + CFG.PADDING * 2 + iTranslateX,
                     nPosY - this.getHeight() / 2 - ImageManager.getImage(Images.slider_gradient).getHeight(),
                     (int)((nWidth - CFG.PADDING * 6 - this.getTextWidth() * 0.8F) / 2.0F),
                     1,
                     true,
                     false
                  );
               ImageManager.getImage(Images.slider_gradient)
                  .draw(
                     oSB,
                     nPosX + nWidth / 2 + CFG.PADDING + (int)(this.getTextWidth() * 0.8F / 2.0F) + iTranslateX,
                     nPosY - this.getHeight() / 2 - ImageManager.getImage(Images.slider_gradient).getHeight(),
                     (int)((nWidth - CFG.PADDING * 6 - this.getTextWidth() * 0.8F) / 2.0F),
                     1
                  );
               oSB.setColor(Color.WHITE);
               ImageManager.getImage(Images.hre_icon)
                  .draw(
                     oSB,
                     Menu_InGame_HRE.this.getPosX() + CFG.PADDING * 2 + iTranslateX,
                     Menu_InGame_HRE.this.getPosY() - this.getHeight() / 2 - ImageManager.getImage(Images.hre_icon).getHeight() / 2
                  );
               CFG.fontMain.getData().setScale(0.8F);
               CFG.drawText(
                  oSB,
                  this.getText(),
                  nPosX + (int)(nWidth - this.getTextWidth() * 0.8F) / 2 + iTranslateX,
                  2 + nPosY - this.getHeight() + (int)(this.getHeight() - this.getTextHeight() * 0.8F) / 2,
                  Color.WHITE
               );
               CFG.fontMain.getData().setScale(1.0F);
            }
         },
         CFG.GAME_WIDTH / 2 - tempWidth / 2,
         tempMenuPosY,
         tempWidth,
         tempMenuHeight,
         menuElements,
         true,
         true
      );
      this.updateLanguage();
      lTime = System.currentTimeMillis();
   }

   @Override
   public void updateLanguage() {
   }

   @Override
   public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      if (lTime + 200L >= System.currentTimeMillis()) {
         Rectangle clipBounds = new Rectangle(
            this.getPosX() - 2,
            CFG.GAME_HEIGHT - this.getPosY(),
            this.getWidth() + 4,
            -((int)((this.getHeight() + CFG.PADDING) * ((float)(System.currentTimeMillis() - lTime) / 200.0F)))
         );
         oSB.flush();
         ScissorStack.pushScissors(clipBounds);
         oSB.setColor(Color.WHITE);
         ImageManager.getImage(Images.new_game_top_edge)
            .draw2(
               oSB,
               this.getPosX() - 2 + iTranslateX,
               this.getPosY() - ImageManager.getImage(Images.new_game_top_edge).getHeight() + iTranslateY,
               this.getWidth() - ImageManager.getImage(Images.new_game_top_edge).getWidth() + 4,
               this.getHeight() + CFG.PADDING,
               false,
               true
            );
         ImageManager.getImage(Images.new_game_top_edge)
            .draw2(
               oSB,
               this.getPosX() + 2 + this.getWidth() - ImageManager.getImage(Images.new_game_top_edge).getWidth() + iTranslateX,
               this.getPosY() - ImageManager.getImage(Images.new_game_top_edge).getHeight() + iTranslateY,
               ImageManager.getImage(Images.new_game_top_edge).getWidth(),
               this.getHeight() + CFG.PADDING,
               true,
               true
            );
         oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.45F));
         ImageManager.getImage(Images.gradient)
            .draw(
               oSB,
               this.getPosX() + 2 + iTranslateX,
               this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY,
               this.getWidth() - 4,
               this.getHeight() / 4
            );
         ImageManager.getImage(Images.pix255_255_255)
            .draw(
               oSB,
               this.getPosX() + 2 + iTranslateX,
               this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY,
               this.getWidth() - 4,
               1
            );
         oSB.setColor(Color.WHITE);
         this.drawMenu(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
         oSB.setColor(Color.WHITE);
         CFG.setRender_3(true);
         this.endClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
      } else {
         oSB.setColor(Color.WHITE);
         ImageManager.getImage(Images.new_game_top_edge)
            .draw2(
               oSB,
               this.getPosX() - 2 + iTranslateX,
               this.getPosY() - ImageManager.getImage(Images.new_game_top_edge).getHeight() + iTranslateY,
               this.getWidth() - ImageManager.getImage(Images.new_game_top_edge).getWidth() + 4,
               this.getHeight() + CFG.PADDING,
               false,
               true
            );
         ImageManager.getImage(Images.new_game_top_edge)
            .draw2(
               oSB,
               this.getPosX() + 2 + this.getWidth() - ImageManager.getImage(Images.new_game_top_edge).getWidth() + iTranslateX,
               this.getPosY() - ImageManager.getImage(Images.new_game_top_edge).getHeight() + iTranslateY,
               ImageManager.getImage(Images.new_game_top_edge).getWidth(),
               this.getHeight() + CFG.PADDING,
               true,
               true
            );
         oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.45F));
         ImageManager.getImage(Images.gradient)
            .draw(
               oSB,
               this.getPosX() + 2 + iTranslateX,
               this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY,
               this.getWidth() - 4,
               this.getHeight() / 4
            );
         ImageManager.getImage(Images.pix255_255_255)
            .draw(
               oSB,
               this.getPosX() + 2 + iTranslateX,
               this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY,
               this.getWidth() - 4,
               1
            );
         oSB.setColor(Color.WHITE);
         this.beginClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
         this.drawMenu(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
         oSB.setColor(Color.WHITE);
         this.endClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
      }
   }

   @Override
   public void drawScrollPos(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      if (sliderMenuIsActive) {
         super.drawScrollPos(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
      }
   }

   @Override
   public final void actionElement(int iID) {
      this.getMenuElement(iID).actionElement(iID);
   }

   @Override
   public void setVisible(boolean visible) {
      super.setVisible(visible);
      if (!visible) {
         for (int i = 0; i < this.getMenuElementsSize(); i++) {
            this.getMenuElement(i).setVisible(false);
         }
      }
   }

   public final int getW() {
      return this.getWidth();
   }
}
