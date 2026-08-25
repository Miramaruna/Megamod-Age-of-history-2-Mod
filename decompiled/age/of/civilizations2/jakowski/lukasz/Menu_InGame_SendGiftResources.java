package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.utils.ScissorStack;
import java.util.ArrayList;

class Menu_InGame_SendGiftResources extends SliderMenu {
   private int iOnCivID = -1;

   protected Menu_InGame_SendGiftResources() {
      ArrayList var1 = new ArrayList();
      int var2 = CFG.CIV_INFO_MENU_WIDTH * 2;
      int var3 = CFG.PADDING;
      var1.add(new Button_Flag_JustFrame(CFG.PADDING, var3, true));
      ((MenuElement)var1.get(var1.size() - 1)).getHeight();
      int var4 = ImageManager.getImage(Images.top_left2).getHeight() + CFG.PADDING * 2 + CFG.BUTTON_HEIGHT * 3 / 5;
      SliderMenuTitle var5 = new SliderMenuTitle(CFG.langManager.get("SendGift"), CFG.BUTTON_HEIGHT * 3 / 5, true, true);
      int var6 = CFG.GAME_WIDTH / 2;
      int var7 = var2 / 2;
      var3 = ((MenuElement)var1.get(var1.size() - 1)).getPosY();
      if (((MenuElement)var1.get(var1.size() - 1)).getHeight() + var3 + CFG.PADDING + var4 > CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING * 2) {
         var3 = Math.max(CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING * 2 - var4, (CFG.TEXT_HEIGHT + CFG.PADDING * 2) * 6);
      } else {
         var3 = ((MenuElement)var1.get(var1.size() - 1)).getPosY();
         int var8 = ((MenuElement)var1.get(var1.size() - 1)).getHeight();
         var3 = CFG.PADDING + var8 + var3;
      }

      this.initMenu(var5, var6 - var7, var4, var2, var3, var1, false, true);
      this.updateLanguage();
   }

   protected Menu_InGame_SendGiftResources(int var1) {
      ArrayList var2 = new ArrayList();
      this.iOnCivID = var1;
      int var3 = CFG.CIV_INFO_MENU_WIDTH * 2;
      var2.add(
         new Button_NS_Opinion(
            this, CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), CFG.getActiveCivInfo(), Images.diplo_gift, 0, 8, 2, 0, CFG.BUTTON_WIDTH * 2
         ) {
            final Menu_InGame_SendGiftResources this$0;

            {
               this.this$0 = var1;
            }

            @Override
            protected int getWidth() {
               return this.this$0.getElementW() * 2;
            }
         }
      );
      int var4 = ((MenuElement)var2.get(var2.size() - 1)).getHeight();
      String var5 = CFG.langManager.get("SelectTheAmountOfResourcesToSend");
      int var6 = CFG.PADDING;
      int var7 = ((MenuElement)var2.get(var2.size() - 1)).getPosY();
      int var8 = ((MenuElement)var2.get(var2.size() - 1)).getHeight();
      int var9 = CFG.PADDING;
      int var10 = CFG.PADDING;
      int var11 = CFG.BUTTON_WIDTH;
      int var12 = CFG.TEXT_HEIGHT;
      int var13 = CFG.PADDING;
      int var14 = CFG.PADDING;
      if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getManPower() > 0L) {
         var1 = (int)(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getManPower() * 0.25F);
      } else {
         var1 = 0;
      }

      var2.add(
         new Slider_FlagAction_Gold(this, var5, var6 * 2, var8 + var7 + var9, var3 - var10 * 3 - var11, var12 + var13 * 2 + var14 * 4, 0, var1, 0, 0.65F) {
            final Menu_InGame_SendGiftResources this$0;

            {
               this.this$0 = var1;
            }

            @Override
            protected Color getColorLEFT() {
               return new Color(CFG.COLOR_INGAME_GOLD.r, CFG.COLOR_INGAME_GOLD.g, CFG.COLOR_INGAME_GOLD.b, 0.65F);
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
      var7 = ((MenuElement)var2.get(var2.size() - 1)).getHeight();
      var12 = CFG.PADDING;
      var5 = CFG.langManager.get("Cancel");
      var1 = CFG.PADDING;
      var4 = 0 + var4 + var7 + var12 + CFG.PADDING;
      var2.add(new Button_FlagActionSliderStyle(this, var5, -1, var1 + 2, var4, CFG.BUTTON_WIDTH, true) {
         final Menu_InGame_SendGiftResources this$0;

         {
            this.this$0 = var1;
         }

         @Override
         protected int getWidth() {
            return this.this$0.getElementW() - CFG.PADDING - CFG.PADDING / 2;
         }
      });
      var2.add(
         new Button_FlagActionSliderStyle(this, CFG.langManager.get("Confirm"), -1, 2, var4, CFG.BUTTON_WIDTH, true) {
            final Menu_InGame_SendGiftResources this$0;

            {
               this.this$0 = var1;
            }

            @Override
            protected void buildElementHover() {
               ArrayList var1x = new ArrayList();
               ArrayList var2x = new ArrayList();
               var2x.add(new MenuElement_Hover_v2_Element_Type_Flag(this.this$0.iOnCivID, 0, CFG.PADDING));
               var2x.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.game.getCiv(this.this$0.iOnCivID).getCivName()));
               var1x.add(new MenuElement_Hover_v2_Element2(var2x));
               var2x.clear();
               var2x.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("DiplomacyPoints") + ": ", CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
               var2x.add(new MenuElement_Hover_v2_Element_Type_Text("-0.8", CFG.COLOR_TEXT_MODIFIER_NEGATIVE2));
               var2x.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_diplomacy_points, CFG.PADDING, 0));
               var1x.add(new MenuElement_Hover_v2_Element2(var2x));
               var2x.clear();
               this.menuElementHover = new MenuElement_Hover_v2(var1x);
            }

            @Override
            protected void drawText(SpriteBatch var1, int var2x, int var3x, boolean var4x) {
               ImageManager.getImage(Images.diplo_gift)
                  .draw(
                     var1,
                     this.getPosX()
                        + this.getWidth() / 2
                        - (int)((this.getTextWidth() * 0.8F + ImageManager.getImage(Images.diplo_gift).getWidth() + CFG.PADDING) / 2.0F)
                        + var2x,
                     this.getPosY() + this.getHeight() / 2 - ImageManager.getImage(Images.diplo_gift).getHeight() / 2 + var3x
                  );
               CFG.fontMain.getData().setScale(0.8F);
               String var5x = this.getText();
               int var6x = this.getPosX();
               int var7x;
               if (this.getTextPos() < 0) {
                  var7x = this.getWidth() / 2
                     - (int)((this.getTextWidth() * 0.8F + ImageManager.getImage(Images.diplo_gift).getWidth() + CFG.PADDING) / 2.0F)
                     + ImageManager.getImage(Images.diplo_gift).getWidth()
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
               if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getDiplomacyPoints() >= 8) {
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
               return SoundsManager.getSend();
            }

            @Override
            protected int getWidth() {
               return this.this$0.getElementW() - CFG.PADDING - CFG.PADDING / 2;
            }
         }
      );
      var4 = ImageManager.getImage(Images.top_left2).getHeight() + CFG.PADDING * 2 + CFG.BUTTON_HEIGHT * 3 / 5;
      SliderMenuTitle var23 = new SliderMenuTitle(this, CFG.langManager.get("SendGiftResources"), CFG.BUTTON_HEIGHT * 3 / 5, true, true) {
         final Menu_InGame_SendGiftResources this$0;

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
            var1.setColor(new Color(0.101960786F, 0.5882353F, 0.33333334F, 0.165F));
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
            var1.setColor(new Color(0.101960786F, 0.5882353F, 0.33333334F, 0.375F));
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
            ImageManager.getImage(Images.top_gold)
               .draw(
                  var1,
                  (int)(var5x - (this.getTextWidth() * 0.8F + ImageManager.getImage(Images.top_gold).getWidth() + CFG.PADDING)) / 2 + var3x + var2x,
                  this.this$0.getPosY() - this.getHeight() / 2 - ImageManager.getImage(Images.top_gold).getHeight() / 2
               );
            CFG.fontMain.getData().setScale(0.8F);
            CFG.drawText(
               var1,
               this.getText(),
               (int)(var5x - (this.getTextWidth() * 0.8F + ImageManager.getImage(Images.top_gold).getWidth() + CFG.PADDING)) / 2
                  + var3x
                  + ImageManager.getImage(Images.top_gold).getWidth()
                  + CFG.PADDING
                  + var2x,
               var4x + 2 - this.getHeight() + (int)(this.getHeight() - this.getTextHeight() * 0.8F) / 2,
               Color.WHITE
            );
            CFG.fontMain.getData().setScale(1.0F);
         }
      };
      var12 = CFG.GAME_WIDTH / 2;
      var7 = var3 / 2;
      var1 = ((MenuElement)var2.get(var2.size() - 1)).getPosY();
      if (((MenuElement)var2.get(var2.size() - 1)).getHeight() + var1 + CFG.PADDING + var4 > CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING * 2) {
         var1 = Math.max(CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING * 2 - var4, (CFG.TEXT_HEIGHT + CFG.PADDING * 2) * 6);
      } else {
         var1 = ((MenuElement)var2.get(var2.size() - 1)).getPosY();
         var1 = ((MenuElement)var2.get(var2.size() - 1)).getHeight() + var1 + CFG.PADDING;
      }

      this.initMenu(var23, var12 - var7, var4, var3, var1, var2, true, true);
      this.updateLanguage();
      this.getMenuElement(1).setCurrent(25);
      this.getMenuElement(1).setCurrent(25);
      Menu_InGame_OfferAlliance.lTime = System.currentTimeMillis();
   }

   @Override
   protected final void actionElement(int var1) {
      if (var1 == this.getMenuElementsSize() - 1) {
         if (this.getMenuElement(this.getMenuElementsSize() - 3).getCurrent() <= 0) {
            CFG.toast.setInView(CFG.langManager.get("SelectTheAmountOfResourcesToSend") + "!", CFG.COLOR_TEXT_MODIFIER_NEGATIVE2);
            CFG.toast.setTimeInView(4500);
         } else {
            DiplomacyManager.sendGiftResources(
               this.iOnCivID, CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), this.getMenuElement(this.getMenuElementsSize() - 3).getCurrent()
            );
            CFG.menuManager.updateInGame_TOP_All(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
            CFG.toast.setInView(CFG.langManager.get("Sent") + "!", CFG.COLOR_TEXT_MODIFIER_POSITIVE);
            CFG.toast.setTimeInView(4500);
            this.setVisible(false);
         }
      } else if (var1 == this.getMenuElementsSize() - 2) {
         this.setVisible(false);
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
         var10 = this.getWidth();
         int var12 = ImageManager.getImage(Images.new_game_top_edge).getWidth();
         var9 = this.getPosY();
         var7 = ImageManager.getImage(Images.new_game_top_edge).getHeight();
         var8 = ImageManager.getImage(Images.new_game_top_edge).getWidth();
         var11 = this.getHeight();
         var14.draw2(var1, var6 + 2 + var10 - var12 + var2, var9 - var7 + var3, var8, CFG.PADDING + var11, true, true);
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
         int var18 = ImageManager.getImage(Images.new_game_top_edge).getHeight();
         int var21 = this.getWidth();
         int var33 = ImageManager.getImage(Images.new_game_top_edge).getWidth();
         int var30 = this.getHeight();
         var15.draw2(var1, var24 - 2 + var2, var27 - var18 + var3, var21 - var33 + 4, CFG.PADDING + var30, false, true);
         var15 = ImageManager.getImage(Images.new_game_top_edge);
         var24 = this.getPosX();
         int var35 = this.getWidth();
         var33 = ImageManager.getImage(Images.new_game_top_edge).getWidth();
         var18 = this.getPosY();
         var30 = ImageManager.getImage(Images.new_game_top_edge).getHeight();
         var21 = ImageManager.getImage(Images.new_game_top_edge).getWidth();
         var27 = this.getHeight();
         var15.draw2(var1, var24 + 2 + var35 - var33 + var2, var18 - var30 + var3, var21, CFG.PADDING + var27, true, true);
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
