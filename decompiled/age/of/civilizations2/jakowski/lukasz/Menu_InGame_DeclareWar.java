package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.utils.ScissorStack;
import java.util.ArrayList;

public class Menu_InGame_DeclareWar extends SliderMenu {
   public int iOnCivID = -1;
   public static boolean hideAnimation = true;

   public Menu_InGame_DeclareWar() {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      int tempWidth = CFG.CIV_INFO_MENU_WIDTH * 2;
      int tY = CFG.PADDING;
      menuElements.add(new Button_Flag_JustFrame(CFG.PADDING, tY, true));
      tY += menuElements.get(menuElements.size() - 1).getHeight();
      int tempMenuPosY = ImageManager.getImage(Images.top_left2).getHeight() + CFG.PADDING * 2 + CFG.BUTTON_HEIGHT * 3 / 5;
      this.initMenu(
         new SliderMenuTitle(CFG.langManager.get("DeclareWar"), CFG.BUTTON_HEIGHT * 3 / 5, true, true),
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

   public Menu_InGame_DeclareWar(int onCivID) {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      this.iOnCivID = onCivID;
      int tempWidth = CFG.CIV_INFO_MENU_WIDTH * 2;
      int tY = 0;
      menuElements.add(
         new CasusBelliButton(
            CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(),
            CFG.getActiveCivInfo(),
            2,
            tY,
            CFG.BUTTON_WIDTH * 2,
            DiplomacyManager.CasusBelliTurns(this.iOnCivID, CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
         ) {
            @Override
            public int getWidth() {
               return Menu_InGame_DeclareWar.this.getElementW() * 2;
            }
         }
      );
      tY += menuElements.get(menuElements.size() - 1).getHeight();
      ArrayList<Integer> lAlliesAggressor = new ArrayList<>();
      ArrayList<Integer> lAlliesDefender = new ArrayList<>();

      for (int i = 1; i < CFG.game.getCivsSize(); i++) {
         if (i != this.iOnCivID && i != CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID() && CFG.game.getCiv(i).getNumOfProvinces() > 0) {
            if (CFG.game.getCiv(i).getPuppetOfCivID() == CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()
               || CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getPuppetOfCivID() == i) {
               lAlliesAggressor.add(i);
            } else if (CFG.game.getCiv(i).getPuppetOfCivID() == this.iOnCivID) {
               lAlliesDefender.add(i);
            } else if (i == CFG.game.getCiv(this.iOnCivID).getPuppetOfCivID()) {
               lAlliesDefender.add(i);
            } else if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID() > 0
               && CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID() == CFG.game.getCiv(i).getAllianceID()) {
               lAlliesAggressor.add(i);
            } else if (CFG.game.getCiv(this.iOnCivID).getAllianceID() > 0
               && CFG.game.getCiv(this.iOnCivID).getAllianceID() == CFG.game.getCiv(i).getAllianceID()) {
               lAlliesDefender.add(i);
            } else if (CFG.game.getDefensivePact(this.iOnCivID, i) > 0) {
               lAlliesDefender.add(i);
            } else if (CFG.game.getGuarantee(i, this.iOnCivID) > 0) {
               lAlliesDefender.add(i);
            }
         }
      }

      if (lAlliesDefender.size() > 0 || lAlliesAggressor.size() > 0) {
         int var11;
         menuElements.add(
            new Text_AlliesNotInWar(
               CFG.langManager.get("Allies"), -1, CFG.PADDING, var11 = tY + CFG.PADDING, tempWidth - CFG.PADDING * 2, CFG.TEXT_HEIGHT + CFG.PADDING * 3
            ) {
               @Override
               public int getPosX() {
                  return 0;
               }

               @Override
               public int getWidth() {
                  return Menu_InGame_DeclareWar.this.getW() + 4;
               }
            }
         );
         int tempAdded = 0;
         int tempYAllies = tY = var11 + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;

         for (int ix = 0; ix < lAlliesAggressor.size(); ix++) {
            menuElements.add(new Button_Statistics_CallAlly(lAlliesAggressor.get(ix), 0, tY, CFG.BUTTON_WIDTH * 2, false, false) {
               @Override
               public int getWidth() {
                  return Menu_InGame_DeclareWar.this.getElementW();
               }
            });
            menuElements.get(menuElements.size() - 1).setCurrent(tempAdded++ % 2);
            tY += menuElements.get(menuElements.size() - 1).getHeight();
         }

         tempAdded = 0;
         tY = tempYAllies;

         for (int var15 = 0; var15 < lAlliesDefender.size(); var15++) {
            menuElements.add(new Button_Statistics_CallAlly_Right(lAlliesDefender.get(var15), 0, tY, CFG.BUTTON_WIDTH * 2, false, true) {
               @Override
               public int getPosX() {
                  return Menu_InGame_DeclareWar.this.getElementW();
               }

               @Override
               public int getWidth() {
                  return Menu_InGame_DeclareWar.this.getElementW();
               }

               @Override
               public void buildElementHover() {
                  ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
                  ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
                  nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("DeclareWarOn") + ":", CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                  nData.add(new MenuElement_Hover_v2_Element_Type_Flag(this.getCurrent(), CFG.PADDING, CFG.PADDING));
                  nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.game.getCiv(this.getCurrent()).getCivName()));
                  nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.diplo_war, CFG.PADDING, 0));
                  nElements.add(new MenuElement_Hover_v2_Element2(nData));
                  nData.clear();
                  this.menuElementHover = new MenuElement_Hover_v2(nElements);
               }
            });
            menuElements.get(menuElements.size() - 1).setCurrent(tempAdded++ % 2);
            tY += menuElements.get(menuElements.size() - 1).getHeight();
         }

         for (int var16 = 0; var16 < menuElements.size(); var16++) {
            if (menuElements.get(var16).getPosY() + menuElements.get(var16).getHeight() > tY) {
               tY = menuElements.get(var16).getPosY() + menuElements.get(var16).getHeight();
            }
         }
      }

      int var13;
      menuElements.add(new Button_FlagActionSliderStyle(CFG.langManager.get("Cancel"), -1, 2 + CFG.PADDING, var13 = tY + CFG.PADDING, CFG.BUTTON_WIDTH, true) {
         @Override
         public int getWidth() {
            return Menu_InGame_DeclareWar.this.getElementW() - CFG.PADDING - CFG.PADDING / 2;
         }
      });
      menuElements.add(
         new Button_FlagActionSliderStyle_War(CFG.langManager.get("DeclareWar"), -1, 2, var13, CFG.BUTTON_WIDTH, true) {
            @Override
            public int getPosX() {
               return Menu_InGame_DeclareWar.this.getElementW() + CFG.PADDING / 2;
            }

            @Override
            public int getWidth() {
               return Menu_InGame_DeclareWar.this.getElementW() - CFG.PADDING - CFG.PADDING / 2;
            }

            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               if (Game_Calendar.TURN_ID > Game_Calendar.PeaceAfterGameStarts) {
                  nData.add(
                     new MenuElement_Hover_v2_Element_Type_Text(
                        CFG.langManager
                              .get(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getDeclareWarStatus(Menu_InGame_DeclareWar.this.iOnCivID))
                           + ":",
                        CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                     )
                  );
                  nData.add(new MenuElement_Hover_v2_Element_Type_Flag(Menu_InGame_DeclareWar.this.iOnCivID, CFG.PADDING, CFG.PADDING));
                  nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.game.getCiv(Menu_InGame_DeclareWar.this.iOnCivID).getCivName()));
                  nElements.add(new MenuElement_Hover_v2_Element2(nData));
                  nData.clear();

                  for (int i = 1; i < CFG.game.getCivsSize(); i++) {
                     if (i != Menu_InGame_DeclareWar.this.iOnCivID
                        && i != CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()
                        && CFG.game.getCiv(i).getNumOfProvinces() > 0
                        && CFG.game.getDefensivePact(i, Menu_InGame_DeclareWar.this.iOnCivID) > 0) {
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("DefensivePact") + ":"));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Flag(i, CFG.PADDING, CFG.PADDING));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.game.getCiv(i).getCivName(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.diplo_defensive_pact, CFG.PADDING, 0));
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                     }
                  }
               } else {
                  nData.add(
                     new MenuElement_Hover_v2_Element_Type_Text(
                        CFG.langManager.get("AWarCantBeDeclaredInFirstXTurns", Game_Calendar.PeaceAfterGameStarts) + ".", CFG.COLOR_TEXT_MODIFIER_NEGATIVE2
                     )
                  );
                  nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.diplo_war, CFG.PADDING, 0));
                  nElements.add(new MenuElement_Hover_v2_Element2(nData));
                  nData.clear();
               }

               this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }

            @Override
            public boolean getClickable() {
               return CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getDeclareWarBooleanStatus(Menu_InGame_DeclareWar.this.iOnCivID);
            }

            @Override
            public int getSFX() {
               return this.getClickable() ? SoundsManager.SOUND_WAR2 : super.getSFX();
            }
         }
      );
      int tempMenuPosY = ImageManager.getImage(Images.top_left2).getHeight() + CFG.PADDING * 2 + CFG.BUTTON_HEIGHT * 3 / 5;
      this.initMenu(
         new SliderMenuTitle(CFG.langManager.get("DeclareWar"), CFG.BUTTON_HEIGHT * 3 / 5, true, true) {
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
               oSB.setColor(
                  new Color(
                     CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_AT_WAR.getR(),
                     CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_AT_WAR.getG(),
                     CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_AT_WAR.getB(),
                     0.165F
                  )
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
                  new Color(
                     CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_AT_WAR.getR(),
                     CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_AT_WAR.getG(),
                     CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_AT_WAR.getB(),
                     0.375F
                  )
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
         menuElements.get(menuElements.size() - 1).getPosY() + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING + tempMenuPosY
               > CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING * 2
            ? Math.max(CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING * 2 - tempMenuPosY, (CFG.TEXT_HEIGHT + CFG.PADDING * 2) * 6)
            : menuElements.get(menuElements.size() - 1).getPosY() + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING,
         menuElements,
         true,
         true
      );
      this.updateLanguage();
      Menu_InGame_OfferAlliance.lTime = System.currentTimeMillis();
   }

   @Override
   public void updateLanguage() {
   }

   @Override
   public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      if (Menu_InGame_OfferAlliance.lTime + 100L >= System.currentTimeMillis()) {
         int var6;
         int var7;
         iTranslateY = hideAnimation
            ? (var6 = iTranslateY - (int)(this.getHeight() / 2.0F * ((float)(System.currentTimeMillis() - Menu_InGame_OfferAlliance.lTime) / 100.0F)))
            : (
               var7 = iTranslateY
                  + -this.getHeight() / 2
                  + (int)(this.getHeight() / 2.0F * ((float)(System.currentTimeMillis() - Menu_InGame_OfferAlliance.lTime) / 100.0F))
            );
         CFG.setRender_3(true);
      } else if (hideAnimation) {
         super.setVisible(false);
         return;
      }

      Rectangle clipBounds = new Rectangle(
         this.getPosX() - 2,
         CFG.GAME_HEIGHT - this.getPosY(),
         this.getWidth() + 4,
         -((int)((this.getHeight() + CFG.PADDING) * ((float)(System.currentTimeMillis() - Menu_InGame_OfferAlliance.lTime) / 200.0F)))
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

   @Override
   public void drawScrollPos(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      if (sliderMenuIsActive) {
         super.drawScrollPos(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
      }
   }

   @Override
   public final void actionElement(int iID) {
      if (iID != this.getMenuElementsSize() - 1) {
         if (iID == this.getMenuElementsSize() - 2) {
            this.setVisible(false);
         } else {
            this.getMenuElement(iID).setCheckboxState(!this.getMenuElement(iID).getCheckboxState());
         }
      } else {
         if (AI_Assistant.ENABLED) {
            AI_Assistant.PRIORITY_ENEMY = this.iOnCivID;
            Gdx.app.log("AoC", "AI Assistant: PRIORITY ENEMY SET: " + CFG.game.getCiv(this.iOnCivID).getCivName());
         }

         DiplomacyManager.sendCasusBelli(
            CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(),
            this.iOnCivID,
            DiplomacyManager.CasusBelliTurns(this.iOnCivID, CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
         );

         for (int i = 2; i < this.getMenuElementsSize() - 2; i++) {
            if (this.getMenuElement(i).getCheckboxState() && this.getMenuElement(i).getClickable()) {
               if (CFG.game.getCiv(this.getMenuElement(i).getCurrent()).getPuppetOfCivID() != CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()
                  && (
                     CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID() <= 0
                        || CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getAllianceID()
                           != CFG.game.getCiv(this.getMenuElement(i).getCurrent()).getAllianceID()
                  )) {
                  DiplomacyManager.sendCasusBelli(
                     CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(),
                     this.getMenuElement(i).getCurrent(),
                     DiplomacyManager.CasusBelliTurns(this.getMenuElement(i).getCurrent(), CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
                  );
               } else {
                  DiplomacyManager.sendCallToArms(this.getMenuElement(i).getCurrent(), CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), this.iOnCivID);
               }
            }
         }

         CFG.updateActiveCivInfo_InGame();

         for (int var3 = 0; var3 < CFG.game.getCiv(this.iOnCivID).getNumOfProvinces(); var3++) {
            CFG.game.getProvince(CFG.game.getCiv(this.iOnCivID).getProvinceID(var3)).updateDrawArmy();
         }

         CFG.menuManager.rebuildMenu_InGame_War(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), this.iOnCivID);
         CFG.menuManager.setVisible_Menu_InGame_CurrentWars(true);
         if (CFG.viewsManager.getActiveViewID() == ViewsManager.VIEW_DIPLOMACY_MODE) {
            CFG.viewsManager.disableAllViews();
         }

         if (CFG.menuManager.getVisibleInGame_WarDetails()) {
            CFG.menuManager.rebuildInGame_WarDetails();
         }

         if (CFG.menuManager.getVisibleInGame_WarPreparations()) {
            CFG.menuManager.setVisibleInGame_WarPreparations(false);
         }

         this.setVisible(false);
      }
   }

   public final int getW() {
      return this.getWidth() - 4;
   }

   public final int getElementW() {
      return this.getW() / 2;
   }

   @Override
   public void setVisible(boolean visible) {
      super.setVisible(visible);
      this.setHideAnimation(false);
      if (!visible) {
         for (int i = 0; i < this.getMenuElementsSize(); i++) {
            this.getMenuElement(i).setVisible(false);
         }
      }
   }

   public final void setHideAnimation(boolean nHideAnimation) {
      if (nHideAnimation != hideAnimation) {
         Menu_InGame_OfferAlliance.lTime = Menu_InGame_OfferAlliance.lTime > System.currentTimeMillis() - 100L
            ? System.currentTimeMillis() - (100L - (System.currentTimeMillis() - Menu_InGame_OfferAlliance.lTime))
            : System.currentTimeMillis();
         CFG.setRender_3(true);
      }

      hideAnimation = nHideAnimation;
   }
}
