package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.utils.ScissorStack;
import java.util.ArrayList;

public class Menu_InGame_TakeLoan extends SliderMenu {
   public int iOnCivID = -1;
   public static boolean hideAnimation = true;

   public Menu_InGame_TakeLoan() {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      int tempWidth = CFG.CIV_INFO_MENU_WIDTH * 2;
      int tY = CFG.PADDING;
      menuElements.add(new Button_Flag_JustFrame(CFG.PADDING, tY, true));
      tY += menuElements.get(menuElements.size() - 1).getHeight();
      int tempMenuPosY = ImageManager.getImage(Images.top_left2).getHeight() + CFG.PADDING * 2 + CFG.BUTTON_HEIGHT * 3 / 5;
      this.initMenu(
         new SliderMenuTitle(CFG.langManager.get("TakeLoan"), CFG.BUTTON_HEIGHT * 3 / 5, true, true),
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

   public Menu_InGame_TakeLoan(int onCivID) {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      this.iOnCivID = onCivID;
      int tempWidth = CFG.CIV_INFO_MENU_WIDTH * 2;
      int tY = 0;
      menuElements.add(
         new Button_Diplomacy_TakeLoan_Interest(CFG.langManager.get("Interest") + ": ", "0.6", 2, tY, CFG.BUTTON_WIDTH * 2) {
            @Override
            public int getWidth() {
               return Menu_InGame_TakeLoan.this.getElementW() * 2;
            }

            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Interest") + ": "));
               nData.add(
                  new MenuElement_Hover_v2_Element_Type_Text(
                     ""
                        + DiplomacyManager.takeLoan_InterestRate(
                           CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(),
                           Menu_InGame_TakeLoan.this.getMenuElement(1).getCurrent(),
                           Menu_InGame_TakeLoan.this.getMenuElement(2).getCurrent()
                        )
                        + "% ",
                     CFG.COLOR_TEXT_MODIFIER_NEUTRAL2
                  )
               );
               nData.add(new MenuElement_Hover_v2_Element_Type_Text("[", CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
               nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + Menu_InGame_TakeLoan.this.getMenuElement(1).getCurrent(), CFG.COLOR_INGAME_GOLD));
               nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_gold, CFG.PADDING, CFG.PADDING));
               nData.add(
                  new MenuElement_Hover_v2_Element_Type_Text(
                     "+ "
                        + (int)(
                           Menu_InGame_TakeLoan.this.getMenuElement(1).getCurrent()
                              * DiplomacyManager.takeLoan_InterestRate(
                                 CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(),
                                 Menu_InGame_TakeLoan.this.getMenuElement(1).getCurrent(),
                                 Menu_InGame_TakeLoan.this.getMenuElement(2).getCurrent()
                              )
                              / 100.0F
                        ),
                     CFG.COLOR_TEXT_MODIFIER_NEGATIVE2
                  )
               );
               nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_gold, CFG.PADDING, 0));
               nData.add(new MenuElement_Hover_v2_Element_Type_Text("]", CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
         }
      );
      tY += menuElements.get(menuElements.size() - 1).getHeight();
      menuElements.add(
         new Slider_FlagAction_Gold(
            CFG.langManager.get("Gold"),
            CFG.PADDING * 2,
            menuElements.get(menuElements.size() - 1).getPosY() + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING,
            tempWidth - CFG.PADDING * 3 - CFG.BUTTON_WIDTH,
            CFG.TEXT_HEIGHT + CFG.PADDING * 2 + CFG.PADDING * 4,
            30,
            DiplomacyManager.takeLoan_MaxValue(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()),
            30,
            0.65F,
            Images.top_gold
         ) {
            @Override
            public int getWidth() {
               return Menu_InGame_TakeLoan.this.getElementW() * 2 - CFG.PADDING * 4;
            }

            @Override
            public int getSliderHeight() {
               return CFG.PADDING * 2;
            }

            @Override
            public Color getColorLEFT() {
               return new Color(CFG.COLOR_INGAME_GOLD.r, CFG.COLOR_INGAME_GOLD.g, CFG.COLOR_INGAME_GOLD.b, 0.65F);
            }

            @Override
            public void actionElement(int iID) {
               Menu_InGame_TakeLoan.this.getMenuElement(0)
                  .setCurrent(
                     (int)(
                        DiplomacyManager.takeLoan_InterestRate(
                              CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(),
                              Menu_InGame_TakeLoan.this.getMenuElement(1).getCurrent(),
                              Menu_InGame_TakeLoan.this.getMenuElement(2).getCurrent()
                           )
                           * 100.0F
                     )
                  );
               Menu_InGame_TakeLoan.this.getMenuElement(0)
                  .setMin(
                     (int)(
                        Menu_InGame_TakeLoan.this.getMenuElement(1).getCurrent()
                           * DiplomacyManager.takeLoan_InterestRate(
                              CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(),
                              Menu_InGame_TakeLoan.this.getMenuElement(1).getCurrent(),
                              Menu_InGame_TakeLoan.this.getMenuElement(2).getCurrent()
                           )
                           / 100.0F
                     )
                  );
            }
         }
      );
      tY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
      menuElements.add(
         new Slider_FlagAction_Date(
            CFG.langManager.get("Duration"),
            CFG.PADDING * 2,
            menuElements.get(menuElements.size() - 1).getPosY() + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING,
            tempWidth - CFG.PADDING * 3 - CFG.BUTTON_WIDTH,
            CFG.TEXT_HEIGHT + CFG.PADDING * 2 + CFG.PADDING * 4,
            5,
            30,
            17,
            0.65F
         ) {
            @Override
            public int getWidth() {
               return Menu_InGame_TakeLoan.this.getElementW() * 2 - CFG.PADDING * 4;
            }

            @Override
            public String getDrawText() {
               return CFG.langManager.get("TurnsX", this.getCurrent());
            }

            @Override
            public int getSliderHeight() {
               return CFG.PADDING * 2;
            }

            @Override
            public void actionElement(int iID) {
               Menu_InGame_TakeLoan.this.getMenuElement(0)
                  .setCurrent(
                     (int)(
                        DiplomacyManager.takeLoan_InterestRate(
                              CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(),
                              Menu_InGame_TakeLoan.this.getMenuElement(1).getCurrent(),
                              Menu_InGame_TakeLoan.this.getMenuElement(2).getCurrent()
                           )
                           * 100.0F
                     )
                  );
               Menu_InGame_TakeLoan.this.getMenuElement(0)
                  .setMin(
                     (int)(
                        Menu_InGame_TakeLoan.this.getMenuElement(1).getCurrent()
                           * DiplomacyManager.takeLoan_InterestRate(
                              CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(),
                              Menu_InGame_TakeLoan.this.getMenuElement(1).getCurrent(),
                              Menu_InGame_TakeLoan.this.getMenuElement(2).getCurrent()
                           )
                           / 100.0F
                     )
                  );
            }
         }
      );
      tY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
      int var10;
      menuElements.add(new Button_FlagActionSliderStyle(CFG.langManager.get("Cancel"), -1, 2 + CFG.PADDING, var10 = tY + CFG.PADDING, CFG.BUTTON_WIDTH, true) {
         @Override
         public int getWidth() {
            return Menu_InGame_TakeLoan.this.getElementW() - CFG.PADDING - CFG.PADDING / 2;
         }
      });
      menuElements.add(
         new Button_FlagActionSliderStyle(CFG.langManager.get("Confirm"), -1, 2, var10, CFG.BUTTON_WIDTH, true) {
            @Override
            public int getPosX() {
               return Menu_InGame_TakeLoan.this.getElementW() + CFG.PADDING / 2;
            }

            @Override
            public int getWidth() {
               return Menu_InGame_TakeLoan.this.getElementW() - CFG.PADDING - CFG.PADDING / 2;
            }

            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getLoansSize() < 5) {
                  nData.add(new MenuElement_Hover_v2_Element_Type_Flag(Menu_InGame_TakeLoan.this.iOnCivID, 0, CFG.PADDING));
                  nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("TakeLoan"), CFG.COLOR_TEXT_NUM_OF_PROVINCES));
                  nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.diplo_loan, CFG.PADDING, 0));
                  nElements.add(new MenuElement_Hover_v2_Element2(nData));
                  nData.clear();
                  nData.add(new MenuElement_Hover_v2_Element_Type_Space());
                  nElements.add(new MenuElement_Hover_v2_Element2(nData));
                  nData.clear();
                  nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Treasury") + ": "));
                  nData.add(new MenuElement_Hover_v2_Element_Type_Text("+" + Menu_InGame_TakeLoan.this.getMenuElement(1).getCurrent(), CFG.COLOR_INGAME_GOLD));
                  nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_gold, CFG.PADDING, 0));
                  nElements.add(new MenuElement_Hover_v2_Element2(nData));
                  nData.clear();
                  nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Interest") + ": "));
                  nData.add(
                     new MenuElement_Hover_v2_Element_Type_Text(
                        ""
                           + DiplomacyManager.takeLoan_InterestRate(
                              CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(),
                              Menu_InGame_TakeLoan.this.getMenuElement(1).getCurrent(),
                              Menu_InGame_TakeLoan.this.getMenuElement(2).getCurrent()
                           )
                           + "% ",
                        CFG.COLOR_TEXT_MODIFIER_NEUTRAL2
                     )
                  );
                  nData.add(new MenuElement_Hover_v2_Element_Type_Text("[", CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
                  nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + Menu_InGame_TakeLoan.this.getMenuElement(1).getCurrent(), CFG.COLOR_INGAME_GOLD));
                  nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_gold, CFG.PADDING, CFG.PADDING));
                  nData.add(
                     new MenuElement_Hover_v2_Element_Type_Text(
                        "+ "
                           + (int)(
                              Menu_InGame_TakeLoan.this.getMenuElement(1).getCurrent()
                                 * DiplomacyManager.takeLoan_InterestRate(
                                    CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(),
                                    Menu_InGame_TakeLoan.this.getMenuElement(1).getCurrent(),
                                    Menu_InGame_TakeLoan.this.getMenuElement(2).getCurrent()
                                 )
                                 / 100.0F
                           ),
                        CFG.COLOR_TEXT_MODIFIER_NEGATIVE2
                     )
                  );
                  nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_gold, CFG.PADDING, 0));
                  nData.add(new MenuElement_Hover_v2_Element_Type_Text("]", CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
                  nElements.add(new MenuElement_Hover_v2_Element2(nData));
                  nData.clear();
                  nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("MovementPoints") + ": "));
                  nData.add(new MenuElement_Hover_v2_Element_Type_Text("-0.6", CFG.COLOR_TEXT_MODIFIER_NEGATIVE2));
                  nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_movement_points, CFG.PADDING, 0));
                  nElements.add(new MenuElement_Hover_v2_Element2(nData));
                  nData.clear();
               } else {
                  nData.add(new MenuElement_Hover_v2_Element_Type_Flag(Menu_InGame_TakeLoan.this.iOnCivID));
                  nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("YouCantHaveMoreThanXLoans", 5), CFG.COLOR_TEXT_MODIFIER_NEGATIVE2));
                  nElements.add(new MenuElement_Hover_v2_Element2(nData));
                  nData.clear();
               }

               this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }

            @Override
            public void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
               ImageManager.getImage(Images.diplo_loan)
                  .draw(
                     oSB,
                     this.getPosX()
                        + this.getWidth() / 2
                        - (int)((this.getTextWidth() * 0.8F + ImageManager.getImage(Images.diplo_loan).getWidth() + CFG.PADDING) / 2.0F)
                        + iTranslateX,
                     this.getPosY() + this.getHeight() / 2 - ImageManager.getImage(Images.diplo_loan).getHeight() / 2 + iTranslateY
                  );
               CFG.fontMain.getData().setScale(0.8F);
               CFG.drawText(
                  oSB,
                  this.getText(),
                  this.getPosX()
                     + (
                        this.getTextPos() < 0
                           ? this.getWidth() / 2
                              - (int)((this.getTextWidth() * 0.8F + ImageManager.getImage(Images.diplo_loan).getWidth() + CFG.PADDING) / 2.0F)
                              + ImageManager.getImage(Images.diplo_loan).getWidth()
                              + CFG.PADDING
                           : this.getTextPos()
                     )
                     + iTranslateX,
                  this.getPosY() + this.getHeight() / 2 - (int)(this.getTextHeight() * 0.8F / 2.0F) + iTranslateY,
                  this.getColor(isActive)
               );
               CFG.fontMain.getData().setScale(1.0F);
            }

            @Override
            public boolean getClickable() {
               return CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMovePoints() >= 6
                  && CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getLoansSize() < 5;
            }

            @Override
            public int getSFX() {
               return this.getClickable() ? SoundsManager.SOUND_GOLD : super.getSFX();
            }
         }
      );
      int tempMenuPosY = ImageManager.getImage(Images.top_left2).getHeight() + CFG.PADDING * 2 + CFG.BUTTON_HEIGHT * 3 / 5;
      this.initMenu(
         new SliderMenuTitle(CFG.langManager.get("TakeLoan"), CFG.BUTTON_HEIGHT * 3 / 5, true, true) {
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
               oSB.setColor(new Color(0.75686276F, 0.54901963F, 0.36862746F, 0.165F));
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
               oSB.setColor(new Color(0.75686276F, 0.54901963F, 0.36862746F, 0.375F));
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
               ImageManager.getImage(Images.top_gold)
                  .draw(
                     oSB,
                     nPosX + (int)(nWidth - (this.getTextWidth() * 0.8F + ImageManager.getImage(Images.top_gold).getWidth() + CFG.PADDING)) / 2 + iTranslateX,
                     Menu_InGame_TakeLoan.this.getPosY() - this.getHeight() / 2 - ImageManager.getImage(Images.top_gold).getHeight() / 2
                  );
               CFG.fontMain.getData().setScale(0.8F);
               CFG.drawText(
                  oSB,
                  this.getText(),
                  nPosX
                     + (int)(nWidth - (this.getTextWidth() * 0.8F + ImageManager.getImage(Images.top_gold).getWidth() + CFG.PADDING)) / 2
                     + ImageManager.getImage(Images.top_gold).getWidth()
                     + CFG.PADDING
                     + iTranslateX,
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

      for (int i = 1; i < this.getMenuElementsSize(); i++) {
         this.getMenuElement(i).setCurrent(this.getMenuElement(i).getCurrent());
      }

      this.getMenuElement(0)
         .setCurrent(
            (int)(
               DiplomacyManager.takeLoan_InterestRate(
                     CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), this.getMenuElement(1).getCurrent(), this.getMenuElement(2).getCurrent()
                  )
                  * 100.0F
            )
         );
      this.getMenuElement(0)
         .setMin(
            (int)(
               this.getMenuElement(1).getCurrent()
                  * DiplomacyManager.takeLoan_InterestRate(
                     CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), this.getMenuElement(1).getCurrent(), this.getMenuElement(2).getCurrent()
                  )
                  / 100.0F
            )
         );
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
      if (iID == this.getMenuElementsSize() - 1) {
         DiplomacyManager.takeLoan(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), this.getMenuElement(1).getCurrent(), this.getMenuElement(2).getCurrent());
         CFG.menuManager.updateInGame_TOP_All(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
         CFG.toast.setInView("+" + this.getMenuElement(1).getCurrent(), CFG.COLOR_INGAME_GOLD);
         CFG.toast.setTimeInView(4500);
         this.setVisible(false);
      } else if (iID == this.getMenuElementsSize() - 2) {
         this.setVisible(false);
      } else {
         this.getMenuElement(iID).actionElement(iID);
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
