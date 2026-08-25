package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.utils.ScissorStack;
import java.util.ArrayList;

class Menu_InGame_TakeResources extends SliderMenu {
   private int iLoanID;
   private int iLoanValue;
   private int iOnCivID = -1;

   protected Menu_InGame_TakeResources() {
      this.iLoanValue = 0;
      ArrayList var1 = new ArrayList();
      int var2 = CFG.CIV_INFO_MENU_WIDTH * 2;
      int var3 = CFG.PADDING;
      var1.add(new Button_Flag_JustFrame(CFG.PADDING, var3, true));
      ((MenuElement)var1.get(var1.size() - 1)).getHeight();
      int var4 = ImageManager.getImage(Images.top_left2).getHeight() + CFG.PADDING * 2 + CFG.BUTTON_HEIGHT * 3 / 5;
      SliderMenuTitle var5 = new SliderMenuTitle(CFG.langManager.get("Обменять золото на ресурсы"), CFG.BUTTON_HEIGHT * 3 / 5, true, true);
      int var6 = CFG.GAME_WIDTH / 2;
      int var7 = var2 / 2;
      var3 = ((MenuElement)var1.get(var1.size() - 1)).getPosY();
      if (((MenuElement)var1.get(var1.size() - 1)).getHeight() + var3 + CFG.PADDING + var4 > CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING * 2) {
         var3 = Math.max(CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING * 2 - var4, (CFG.TEXT_HEIGHT + CFG.PADDING * 2) * 6);
      } else {
         int var8 = ((MenuElement)var1.get(var1.size() - 1)).getPosY();
         var3 = ((MenuElement)var1.get(var1.size() - 1)).getHeight();
         var3 = CFG.PADDING + var3 + var8;
      }

      this.initMenu(var5, var6 - var7, var4, var2, var3, var1, false, true);
      this.updateLanguage();
   }

   protected Menu_InGame_TakeResources(int var1) {
      this.iLoanValue = 0;
      ArrayList var2 = new ArrayList();
      this.iOnCivID = var1;
      int var3 = CFG.CIV_INFO_MENU_WIDTH * 2;
      var2.add(
         new Button_Diplomacy_TakeRes_Interest(this, CFG.langManager.get("Обменять золото на ресурсы") + " ", "0.6", 2, 0, CFG.BUTTON_WIDTH * 2) {
            final Menu_InGame_TakeResources this$0;

            {
               this.this$0 = var1;
            }

            @Override
            protected void buildElementHover() {
               ArrayList var1x = new ArrayList();
               ArrayList var2x = new ArrayList();
               var2x.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Interest") + ": "));
               var2x.add(
                  new MenuElement_Hover_v2_Element_Type_Text(
                     ""
                        + DiplomacyManager.takeResources_InterestRate(
                           CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(),
                           this.this$0.getMenuElement(1).getCurrent(),
                           this.this$0.getMenuElement(2).getCurrent()
                        )
                        + "% ",
                     CFG.COLOR_TEXT_MODIFIER_NEUTRAL2
                  )
               );
               var2x.add(new MenuElement_Hover_v2_Element_Type_Text("[", CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
               var2x.add(new MenuElement_Hover_v2_Element_Type_Text("" + this.this$0.getMenuElement(1).getCurrent(), CFG.COLOR_TEXT_NUM_OF_PROVINCES));
               var2x.add(new MenuElement_Hover_v2_Element_Type_Image(Images.res, CFG.PADDING, CFG.PADDING));
               var2x.add(
                  new MenuElement_Hover_v2_Element_Type_Text(
                     "+ "
                        + (int)(
                           this.this$0.getMenuElement(1).getCurrent()
                              * DiplomacyManager.takeResources_InterestRate(
                                 CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(),
                                 this.this$0.getMenuElement(1).getCurrent(),
                                 this.this$0.getMenuElement(2).getCurrent()
                              )
                              / 100.0F
                        ),
                     CFG.COLOR_TEXT_MODIFIER_NEGATIVE2
                  )
               );
               var2x.add(new MenuElement_Hover_v2_Element_Type_Image(Images.res, CFG.PADDING, 0));
               var2x.add(new MenuElement_Hover_v2_Element_Type_Text("]", CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
               var1x.add(new MenuElement_Hover_v2_Element2(var2x));
               var2x.clear();
               this.menuElementHover = new MenuElement_Hover_v2(var1x);
            }

            @Override
            protected int getWidth() {
               return this.this$0.getElementW() * 2;
            }
         }
      );
      var1 = ((MenuElement)var2.get(var2.size() - 1)).getHeight();
      String var4 = CFG.langManager.get("Количество ресурсов:");
      int var5 = CFG.PADDING;
      int var6 = ((MenuElement)var2.get(var2.size() - 1)).getPosY();
      var2.add(
         new Slider_FlagAction_Res(
            this,
            var4,
            var5 * 2,
            ((MenuElement)var2.get(var2.size() - 1)).getHeight() + var6 + CFG.PADDING,
            var3 - CFG.PADDING * 3 - CFG.BUTTON_WIDTH,
            CFG.TEXT_HEIGHT + CFG.PADDING * 2 + CFG.PADDING * 4,
            30,
            DiplomacyManager.takeResources_MaxValue(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()),
            30,
            0.8F
         ) {
            final Menu_InGame_TakeResources this$0;

            {
               this.this$0 = var1;
            }

            @Override
            protected void actionElement(int var1) {
               this.this$0
                  .getMenuElement(0)
                  .setCurrent(
                     (int)(
                        DiplomacyManager.takeResources_InterestRate(
                              CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(),
                              this.this$0.getMenuElement(1).getCurrent(),
                              this.this$0.getMenuElement(2).getCurrent()
                           )
                           * 100.0F
                     )
                  );
               this.this$0
                  .getMenuElement(0)
                  .setMin(
                     (int)(
                        this.this$0.getMenuElement(1).getCurrent()
                           * DiplomacyManager.takeResources_InterestRate(
                              CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(),
                              this.this$0.getMenuElement(1).getCurrent(),
                              this.this$0.getMenuElement(2).getCurrent()
                           )
                           / 100.0F
                     )
                  );
            }

            @Override
            protected Color getColorLEFT() {
               return new Color(CFG.COLOR_FORT_1.r, CFG.COLOR_FORT_1.g, CFG.COLOR_FORT_1.b, 0.65F);
            }

            @Override
            protected int getSliderHeight() {
               return CFG.PADDING * 2;
            }

            @Override
            protected int getWidth() {
               return this.this$0.getElementW() * 2 - CFG.PADDING * 4;
            }
         }
      );
      var6 = ((MenuElement)var2.get(var2.size() - 1)).getHeight();
      var5 = CFG.PADDING;
      var4 = CFG.langManager.get("Duration");
      int var7 = CFG.PADDING;
      int var8 = ((MenuElement)var2.get(var2.size() - 1)).getPosY();
      var2.add(
         new Slider_FlagAction_Date(
            this,
            var4,
            var7 * 2,
            ((MenuElement)var2.get(var2.size() - 1)).getHeight() + var8 + CFG.PADDING,
            var3 - CFG.PADDING * 3 - CFG.BUTTON_WIDTH,
            CFG.TEXT_HEIGHT + CFG.PADDING * 2 + CFG.PADDING * 4,
            5,
            30,
            17,
            0.65F
         ) {
            final Menu_InGame_TakeResources this$0;

            {
               this.this$0 = var1;
            }

            @Override
            protected void actionElement(int var1) {
               this.this$0
                  .getMenuElement(0)
                  .setCurrent(
                     (int)(
                        DiplomacyManager.takeResources_InterestRate(
                              CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(),
                              this.this$0.getMenuElement(1).getCurrent(),
                              this.this$0.getMenuElement(2).getCurrent()
                           )
                           * 100.0F
                     )
                  );
               this.this$0
                  .getMenuElement(0)
                  .setMin(
                     (int)(
                        this.this$0.getMenuElement(1).getCurrent()
                           * DiplomacyManager.takeResources_InterestRate(
                              CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(),
                              this.this$0.getMenuElement(1).getCurrent(),
                              this.this$0.getMenuElement(2).getCurrent()
                           )
                           / 100.0F
                     )
                  );
            }

            @Override
            protected String getDrawText() {
               return CFG.langManager.get("TurnsX", this.getCurrent());
            }

            @Override
            protected int getSliderHeight() {
               return CFG.PADDING * 2;
            }

            @Override
            protected int getWidth() {
               return this.this$0.getElementW() * 2 - CFG.PADDING * 4;
            }
         }
      );
      var8 = ((MenuElement)var2.get(var2.size() - 1)).getHeight();
      int var9 = CFG.PADDING;
      var4 = CFG.langManager.get("Cancel");
      var7 = CFG.PADDING;
      var1 = 0 + var1 + var6 + var5 + var8 + var9 + CFG.PADDING;
      var2.add(new Button_FlagActionSliderStyle(this, var4, -1, var7 + 2, var1, CFG.BUTTON_WIDTH, true) {
         final Menu_InGame_TakeResources this$0;

         {
            this.this$0 = var1;
         }

         @Override
         protected int getWidth() {
            return this.this$0.getElementW() - CFG.PADDING - CFG.PADDING / 2;
         }
      });
      var2.add(
         new Button_FlagActionSliderStyle(this, CFG.langManager.get("Confirm"), -1, 2, var1, CFG.BUTTON_WIDTH, true) {
            final Menu_InGame_TakeResources this$0;

            {
               this.this$0 = var1;
            }

            @Override
            protected void buildElementHover() {
               ArrayList var1 = new ArrayList();
               ArrayList var2 = new ArrayList();
               if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getLoansSize() < 10) {
                  var2.add(new MenuElement_Hover_v2_Element_Type_Flag(this.this$0.iOnCivID, 0, CFG.PADDING));
                  var2.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("TakeLoan"), CFG.COLOR_TEXT_NUM_OF_PROVINCES));
                  var2.add(new MenuElement_Hover_v2_Element_Type_Image(Images.res, CFG.PADDING, 0));
                  var1.add(new MenuElement_Hover_v2_Element2(var2));
                  var2.clear();
                  var2.add(new MenuElement_Hover_v2_Element_Type_Space());
                  var1.add(new MenuElement_Hover_v2_Element2(var2));
                  var2.clear();
                  var2.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Treasury") + ": "));
                  var2.add(new MenuElement_Hover_v2_Element_Type_Text("+" + this.this$0.getMenuElement(1).getCurrent(), CFG.COLOR_TEXT_NUM_OF_PROVINCES));
                  var2.add(new MenuElement_Hover_v2_Element_Type_Image(Images.res, CFG.PADDING, 0));
                  var1.add(new MenuElement_Hover_v2_Element2(var2));
                  var2.clear();
                  var2.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Interest") + ": "));
                  var2.add(
                     new MenuElement_Hover_v2_Element_Type_Text(
                        ""
                           + DiplomacyManager.takeResources_InterestRate(
                              CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(),
                              this.this$0.getMenuElement(1).getCurrent(),
                              this.this$0.getMenuElement(2).getCurrent()
                           )
                           + "% ",
                        CFG.COLOR_TEXT_MODIFIER_NEUTRAL2
                     )
                  );
                  var2.add(new MenuElement_Hover_v2_Element_Type_Text("[", CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
                  var2.add(new MenuElement_Hover_v2_Element_Type_Text("" + this.this$0.getMenuElement(1).getCurrent(), CFG.COLOR_TEXT_NUM_OF_PROVINCES));
                  var2.add(new MenuElement_Hover_v2_Element_Type_Image(Images.res, CFG.PADDING, CFG.PADDING));
                  var2.add(
                     new MenuElement_Hover_v2_Element_Type_Text(
                        "+ "
                           + (int)(
                              this.this$0.getMenuElement(1).getCurrent()
                                 * DiplomacyManager.takeResources_InterestRate(
                                    CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(),
                                    this.this$0.getMenuElement(1).getCurrent(),
                                    this.this$0.getMenuElement(2).getCurrent()
                                 )
                                 / 100.0F
                           ),
                        CFG.COLOR_TEXT_MODIFIER_NEGATIVE2
                     )
                  );
                  var2.add(new MenuElement_Hover_v2_Element_Type_Image(Images.res, CFG.PADDING, 0));
                  var2.add(new MenuElement_Hover_v2_Element_Type_Text("]", CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
                  var1.add(new MenuElement_Hover_v2_Element2(var2));
                  var2.clear();
                  var2.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("MovementPoints") + ": "));
                  var2.add(new MenuElement_Hover_v2_Element_Type_Text("-0.6", CFG.COLOR_TEXT_MODIFIER_NEGATIVE2));
                  var2.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_movement_points, CFG.PADDING, 0));
                  var1.add(new MenuElement_Hover_v2_Element2(var2));
                  var2.clear();
               } else {
                  var2.add(new MenuElement_Hover_v2_Element_Type_Flag(this.this$0.iOnCivID));
                  var2.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("YouCantHaveMoreThanXLoans", 5), CFG.COLOR_TEXT_MODIFIER_NEGATIVE2));
                  var1.add(new MenuElement_Hover_v2_Element2(var2));
                  var2.clear();
               }

               this.menuElementHover = new MenuElement_Hover_v2(var1);
            }

            @Override
            protected void drawText(SpriteBatch var1, int var2x, int var3x, boolean var4x) {
               ImageManager.getImage(Images.res)
                  .draw(
                     var1,
                     this.getPosX()
                        + this.getWidth() / 2
                        - (int)((this.getTextWidth() * 0.8F + ImageManager.getImage(Images.res).getWidth() + CFG.PADDING) / 2.0F)
                        + var2x,
                     this.getPosY() + this.getHeight() / 2 - ImageManager.getImage(Images.res).getHeight() / 2 + var3x
                  );
               CFG.fontMain.getData().setScale(0.8F);
               String var5x = this.getText();
               int var6x = this.getPosX();
               int var7x;
               if (this.getTextPos() < 0) {
                  var7x = this.getWidth() / 2
                     - (int)((this.getTextWidth() * 0.8F + ImageManager.getImage(Images.res).getWidth() + CFG.PADDING) / 2.0F)
                     + ImageManager.getImage(Images.res).getWidth()
                     + CFG.PADDING;
               } else {
                  var7x = this.getTextPos();
               }

               CFG.drawText(
                  var1,
                  var5x,
                  var7x + var6x + var2x,
                  this.getPosY() + this.getHeight() / 2 - (int)(this.getTextHeight() * 0.8F / 2.0F) + var3x,
                  this.getColor(var4x)
               );
               CFG.fontMain.getData().setScale(1.0F);
            }

            @Override
            protected boolean getClickable() {
               boolean var1x;
               if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMovePoints() >= 6
                  && CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getLoansSize() < 10) {
                  var1x = true;
               } else {
                  var1x = false;
               }

               return var1x;
            }

            @Override
            protected int getPosX() {
               return this.this$0.getElementW() + CFG.PADDING / 2;
            }

            @Override
            protected int getSFX() {
               int var1x;
               if (this.getClickable()) {
                  var1x = SoundsManager.SOUND_GOLD;
               } else {
                  var1x = super.getSFX();
               }

               return var1x;
            }

            @Override
            protected int getWidth() {
               return this.this$0.getElementW() - CFG.PADDING - CFG.PADDING / 2;
            }
         }
      );
      var5 = ImageManager.getImage(Images.top_left2).getHeight() + CFG.PADDING * 2 + CFG.BUTTON_HEIGHT * 3 / 5;
      SliderMenuTitle var18 = new SliderMenuTitle(this, CFG.langManager.get("TakeResources"), CFG.BUTTON_HEIGHT * 3 / 5, true, true) {
         final Menu_InGame_TakeResources this$0;

         {
            this.this$0 = var1;
         }

         @Override
         protected void draw(SpriteBatch var1, int var2x, int var3x, int var4x, int var5x, boolean var6x) {
            ImageManager.getImage(Images.dialog_title)
               .draw2(
                  var1,
                  var3x - 2 + var2x,
                  var4x - this.getHeight() - ImageManager.getImage(Images.dialog_title).getHeight(),
                  var5x + 4 - ImageManager.getImage(Images.dialog_title).getWidth(),
                  this.getHeight()
               );
            ImageManager.getImage(Images.dialog_title)
               .draw2(
                  var1,
                  var3x + var5x + 2 - ImageManager.getImage(Images.dialog_title).getWidth() + var2x,
                  var4x - this.getHeight() - ImageManager.getImage(Images.dialog_title).getHeight(),
                  ImageManager.getImage(Images.dialog_title).getWidth(),
                  this.getHeight(),
                  true,
                  false
               );
            var1.setColor(new Color(0.75686276F, 0.54901963F, 0.36862746F, 0.165F));
            ImageManager.getImage(Images.line_32_off1)
               .draw(
                  var1,
                  var3x + var2x,
                  var4x - this.getHeight() + 2 - ImageManager.getImage(Images.line_32_off1).getHeight(),
                  var5x,
                  this.getHeight() - 2,
                  false,
                  true
               );
            var1.setColor(new Color(0.75686276F, 0.54901963F, 0.36862746F, 0.375F));
            ImageManager.getImage(Images.gradient)
               .draw(
                  var1,
                  var3x + var2x,
                  var4x - this.getHeight() * 2 / 3 - ImageManager.getImage(Images.gradient).getHeight(),
                  var5x,
                  this.getHeight() * 2 / 3,
                  false,
                  true
               );
            var1.setColor(new Color(0.0F, 0.0F, 0.0F, 0.65F));
            ImageManager.getImage(Images.gradient)
               .draw(var1, var3x + var2x, var4x - CFG.PADDING - ImageManager.getImage(Images.gradient).getHeight(), var5x, CFG.PADDING, false, true);
            var1.setColor(CFG.COLOR_NEW_GAME_EDGE_LINE);
            ImageManager.getImage(Images.pix255_255_255)
               .draw(var1, var3x + var2x, var4x - 1 - ImageManager.getImage(Images.pix255_255_255).getHeight(), var5x, 1);
            var1.setColor(new Color(0.0F, 0.0F, 0.0F, 0.55F));
            ImageManager.getImage(Images.line_32_off1).draw(var1, var3x + var2x, var4x - 2 - ImageManager.getImage(Images.line_32_off1).getHeight(), var5x, 1);
            var1.setColor(new Color(0.0F, 0.0F, 0.0F, 0.8F));
            ImageManager.getImage(Images.line_32_off1).draw(var1, var3x + var2x, var4x - 1 - ImageManager.getImage(Images.line_32_off1).getHeight(), var5x, 1);
            var1.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.45F));
            ImageManager.getImage(Images.slider_gradient)
               .draw(var1, var3x + var2x, var4x - 1 - ImageManager.getImage(Images.slider_gradient).getHeight(), var5x / 2, 1);
            ImageManager.getImage(Images.slider_gradient)
               .draw(var1, var3x + var5x - var5x / 2 + var2x, var4x - 1 - ImageManager.getImage(Images.slider_gradient).getHeight(), var5x / 2, 1, true, false);
            var1.setColor(Color.WHITE);
            ImageManager.getImage(Images.res)
               .draw(
                  var1,
                  (int)(var5x - (this.getTextWidth() * 0.8F + ImageManager.getImage(Images.res).getWidth() + CFG.PADDING)) / 2 + var3x + var2x,
                  this.this$0.getPosY() - this.getHeight() / 2 - ImageManager.getImage(Images.res).getHeight() / 2
               );
            CFG.fontMain.getData().setScale(0.8F);
            CFG.drawText(
               var1,
               this.getText(),
               (int)(var5x - (this.getTextWidth() * 0.8F + ImageManager.getImage(Images.res).getWidth() + CFG.PADDING)) / 2
                  + var3x
                  + ImageManager.getImage(Images.res).getWidth()
                  + CFG.PADDING
                  + var2x,
               var4x + 2 - this.getHeight() + (int)(this.getHeight() - this.getTextHeight() * 0.8F) / 2,
               Color.WHITE
            );
            CFG.fontMain.getData().setScale(1.0F);
         }
      };
      var7 = CFG.GAME_WIDTH / 2;
      var6 = var3 / 2;
      var1 = ((MenuElement)var2.get(var2.size() - 1)).getPosY();
      if (((MenuElement)var2.get(var2.size() - 1)).getHeight() + var1 + CFG.PADDING + var5 > CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING * 2) {
         var1 = Math.max(CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING * 2 - var5, (CFG.TEXT_HEIGHT + CFG.PADDING * 2) * 6);
      } else {
         var1 = ((MenuElement)var2.get(var2.size() - 1)).getPosY();
         var1 = ((MenuElement)var2.get(var2.size() - 1)).getHeight() + var1 + CFG.PADDING;
      }

      this.initMenu(var18, var7 - var6, var5, var3, var1, var2, true, true);
      this.updateLanguage();

      for (int var15 = 1; var15 < this.getMenuElementsSize(); var15++) {
         this.getMenuElement(var15).setCurrent(this.getMenuElement(var15).getCurrent());
      }

      this.getMenuElement(0)
         .setCurrent(
            (int)(
               DiplomacyManager.takeResources_InterestRate(
                     CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), this.getMenuElement(1).getCurrent(), this.getMenuElement(2).getCurrent()
                  )
                  * 100.0F
            )
         );
      this.getMenuElement(0)
         .setMin(
            (int)(
               this.getMenuElement(1).getCurrent()
                  * DiplomacyManager.takeResources_InterestRate(
                     CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), this.getMenuElement(1).getCurrent(), this.getMenuElement(2).getCurrent()
                  )
                  / 100.0F
            )
         );
      Menu_InGame_OfferAlliance.lTime = System.currentTimeMillis();
   }

   @Override
   protected final void actionElement(int var1) {
      if (var1 == this.getMenuElementsSize() - 1) {
         DiplomacyManager.takeResources(
            CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), this.getMenuElement(1).getCurrent(), this.getMenuElement(2).getCurrent()
         );
         CFG.menuManager.updateInGame_TOP_All(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
         CFG.toast.setInView("+" + this.getMenuElement(1).getCurrent(), CFG.COLOR_INGAME_GOLD);
         CFG.toast.setTimeInView(4500);
         this.setVisible(false);
      } else if (var1 == this.getMenuElementsSize() - 2) {
         this.setVisible(false);
      } else {
         this.getMenuElement(var1).actionElement(var1);
      }
   }

   @Override
   protected void draw(SpriteBatch var1, int var2, int var3, boolean var4) {
      if (Menu_InGame_OfferAlliance.lTime + 200L >= System.currentTimeMillis()) {
         Rectangle var5 = new Rectangle(
            this.getPosX() - 2,
            CFG.GAME_HEIGHT - this.getPosY(),
            this.getWidth() + 4,
            -((int)((this.getHeight() + CFG.PADDING) * ((float)(System.currentTimeMillis() - Menu_InGame_OfferAlliance.lTime) / 200.0F)))
         );
         var1.flush();
         ScissorStack.pushScissors(var5);
         var1.setColor(Color.WHITE);
         Image var13 = ImageManager.getImage(Images.new_game_top_edge);
         int var6 = this.getPosX();
         int var7 = this.getPosY();
         int var8 = ImageManager.getImage(Images.new_game_top_edge).getHeight();
         int var9 = this.getWidth();
         int var10 = ImageManager.getImage(Images.new_game_top_edge).getWidth();
         int var11 = this.getHeight();
         var13.draw2(var1, var6 - 2 + var2, var7 - var8 + var3, var9 - var10 + 4, CFG.PADDING + var11, false, true);
         Image var14 = ImageManager.getImage(Images.new_game_top_edge);
         var6 = this.getPosX();
         var9 = this.getWidth();
         var11 = ImageManager.getImage(Images.new_game_top_edge).getWidth();
         var10 = this.getPosY();
         var8 = ImageManager.getImage(Images.new_game_top_edge).getHeight();
         int var12 = ImageManager.getImage(Images.new_game_top_edge).getWidth();
         var7 = this.getHeight();
         var14.draw2(var1, var6 + 2 + var9 - var11 + var2, var10 - var8 + var3, var12, CFG.PADDING + var7, true, true);
         var1.setColor(new Color(0.0F, 0.0F, 0.0F, 0.45F));
         ImageManager.getImage(Images.gradient)
            .draw(
               var1,
               this.getPosX() + 2 + var2,
               this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + var3,
               this.getWidth() - 4,
               this.getHeight() / 4
            );
         ImageManager.getImage(Images.pix255_255_255)
            .draw(var1, this.getPosX() + 2 + var2, this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + var3, this.getWidth() - 4, 1);
         var1.setColor(Color.WHITE);
         this.drawMenu(var1, var2, var3, var4);
         var1.setColor(Color.WHITE);
         var1.setColor(Color.WHITE);
         CFG.setRender_3(true);
         this.endClip(var1, var2, var3, var4);
      } else {
         var1.setColor(Color.WHITE);
         Image var15 = ImageManager.getImage(Images.new_game_top_edge);
         int var24 = this.getPosX();
         int var27 = this.getPosY();
         int var30 = ImageManager.getImage(Images.new_game_top_edge).getHeight();
         int var21 = this.getWidth();
         int var18 = ImageManager.getImage(Images.new_game_top_edge).getWidth();
         int var33 = this.getHeight();
         var15.draw2(var1, var24 - 2 + var2, var27 - var30 + var3, var21 - var18 + 4, CFG.PADDING + var33, false, true);
         var15 = ImageManager.getImage(Images.new_game_top_edge);
         var30 = this.getPosX();
         var33 = this.getWidth();
         var24 = ImageManager.getImage(Images.new_game_top_edge).getWidth();
         var21 = this.getPosY();
         var18 = ImageManager.getImage(Images.new_game_top_edge).getHeight();
         var27 = ImageManager.getImage(Images.new_game_top_edge).getWidth();
         int var35 = this.getHeight();
         var15.draw2(var1, var30 + 2 + var33 - var24 + var2, var21 - var18 + var3, var27, CFG.PADDING + var35, true, true);
         var1.setColor(new Color(0.0F, 0.0F, 0.0F, 0.45F));
         ImageManager.getImage(Images.gradient)
            .draw(
               var1,
               this.getPosX() + 2 + var2,
               this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + var3,
               this.getWidth() - 4,
               this.getHeight() / 4
            );
         ImageManager.getImage(Images.pix255_255_255)
            .draw(var1, this.getPosX() + 2 + var2, this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + var3, this.getWidth() - 4, 1);
         var1.setColor(Color.WHITE);
         this.beginClip(var1, var2, var3, var4);
         this.drawMenu(var1, var2, var3, var4);
         var1.setColor(Color.WHITE);
         this.endClip(var1, var2, var3, var4);
      }
   }

   @Override
   protected void drawScrollPos(SpriteBatch var1, int var2, int var3, boolean var4) {
      if (var4) {
         super.drawScrollPos(var1, var2, var3, var4);
      }
   }

   protected final int getElementW() {
      return this.getW() / 2;
   }

   protected final int getW() {
      return this.getWidth() - 4;
   }

   @Override
   protected void setVisible(boolean var1) {
      super.setVisible(var1);
      if (!var1) {
         for (int var2 = 0; var2 < this.getMenuElementsSize(); var2++) {
            this.getMenuElement(var2).setVisible(false);
         }
      }
   }

   @Override
   protected void updateLanguage() {
   }
}
