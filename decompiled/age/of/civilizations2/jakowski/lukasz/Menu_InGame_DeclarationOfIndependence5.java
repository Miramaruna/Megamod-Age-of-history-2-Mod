package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.utils.ScissorStack;
import java.util.ArrayList;

class Menu_InGame_DeclarationOfIndependence5 extends SliderMenu {
   private int iOnCivID = -1;

   protected Menu_InGame_DeclarationOfIndependence5(int var1) {
      ArrayList var2 = new ArrayList();
      this.iOnCivID = CFG.game.getCiv(var1).getPuppetOfCivID();
      int var3 = CFG.CIV_INFO_MENU_WIDTH * 2;
      var1 = CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID();
      var2.add(
         new Button_NS_Opinion(
            this, var1, CFG.game.getCiv(CFG.getActiveCivInfo()).getPuppetOfCivID(), Images.icon_move_attack, 0, 0, 0, 0, CFG.BUTTON_WIDTH * 0
         ) {
            final Menu_InGame_DeclarationOfIndependence5 this$0;

            {
               this.this$0 = var1;
            }

            @Override
            protected int getWidth() {
               return this.this$0.getElementW() * 0;
            }
         }
      );
      int var4 = 0 + ((MenuElement)var2.get(var2.size() - 1)).getHeight() + CFG.PADDING;
      var2.add(new Button_FlagActionSliderStyle(this, CFG.langManager.get("Cancel"), -1, CFG.PADDING + 2, var4, CFG.BUTTON_WIDTH, true) {
         final Menu_InGame_DeclarationOfIndependence5 this$0;

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
            final Menu_InGame_DeclarationOfIndependence5 this$0;

            {
               this.this$0 = var1;
            }

            @Override
            protected void buildElementHover() {
               ArrayList var1x = new ArrayList();
               ArrayList var2x = new ArrayList();
               var2x.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("SendProposal") + ":", CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
               var2x.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), 0, 0));
               CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCivName();
               var2x.add(new MenuElement_Hover_v2_Element_Type_Text(null));
               var1x.add(new MenuElement_Hover_v2_Element2(var2x));
               var2x.clear();
               var2x.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get(null) + ": ", CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
               var2x.add(new MenuElement_Hover_v2_Element_Type_Text("-0.2", CFG.COLOR_TEXT_MODIFIER_NEGATIVE2));
               var2x.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_diplomacy_points, CFG.PADDING, 0));
               var1x.add(new MenuElement_Hover_v2_Element2(var2x));
               var2x.clear();
               this.menuElementHover = new MenuElement_Hover_v2(var1x);
            }

            @Override
            protected void drawText(SpriteBatch var1, int var2x, int var3x, boolean var4x) {
               ImageManager.getImage(Images.icon_move_attack)
                  .draw(
                     var1,
                     this.getPosX()
                        + this.getWidth() / 2
                        - (int)((this.getTextWidth() * 0.8F + ImageManager.getImage(Images.icon_move_attack).getWidth() + CFG.PADDING) / 2.0F)
                        + var2x,
                     this.getPosY() + this.getHeight() / 2 - ImageManager.getImage(Images.icon_move_attack).getHeight() / 2 + var3x
                  );
               CFG.fontMain.getData().setScale(0.8F);
               String var5 = this.getText();
               int var6 = this.getPosX();
               int var7;
               if (this.getTextPos() < 0) {
                  var7 = this.getWidth() / 2
                     - (int)((this.getTextWidth() * 0.8F + ImageManager.getImage(Images.icon_move_attack).getWidth() + CFG.PADDING) / 2.0F)
                     + ImageManager.getImage(Images.icon_move_attack).getWidth()
                     + CFG.PADDING;
               } else {
                  var7 = this.getTextPos();
               }

               CFG.drawText(
                  var1,
                  var5,
                  var7 + var6 + var2x,
                  this.getPosY() + this.getHeight() / 2 - (int)(this.getTextHeight() * 0.8F / 2.0F) + var3x,
                  this.getColor(var4x)
               );
               CFG.fontMain.getData().setScale(1.0F);
            }

            @Override
            protected boolean getClickable() {
               return true;
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
      var4 = ImageManager.getImage(Images.top_left2).getHeight() + CFG.PADDING * 0 + CFG.BUTTON_HEIGHT * 0 / 5;
      LanguageManager var5;
      String var6;
      if (CFG.game.getCiv(var1).isMoveAtWarPlayer <= 0) {
         var5 = CFG.langManager;
         var6 = "autoplan";
      } else {
         var5 = CFG.langManager;
         var6 = "noautoplan";
      }

      SliderMenuTitle var14 = new SliderMenuTitle(this, var5.get(var6), CFG.BUTTON_HEIGHT * 3 / 5, true, true) {
         final Menu_InGame_DeclarationOfIndependence5 this$0;

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
            var1.setColor(new Color(0.3372549F, 0.34509805F, 0.6666667F, 0.165F));
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
            var1.setColor(new Color(0.3372549F, 0.34509805F, 0.6666667F, 0.375F));
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
            CFG.fontMain.getData().setScale(0.8F);
            CFG.drawText(
               var1,
               this.getText(),
               (int)(var5x - this.getTextWidth() * 0.8F) / 2 + var3x + var2x,
               var4x + 2 - this.getHeight() + (int)(this.getHeight() - this.getTextHeight() * 0.8F) / 2,
               Color.WHITE
            );
            CFG.fontMain.getData().setScale(1.0F);
         }
      };
      int var7 = CFG.GAME_WIDTH / 2;
      int var8 = var3 / 2;
      var1 = ((MenuElement)var2.get(var2.size() - 1)).getPosY();
      if (((MenuElement)var2.get(var2.size() - 1)).getHeight() + var1 + CFG.PADDING + var4 > CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING * 2) {
         var1 = Math.max(CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING * 2 - var4, (CFG.TEXT_HEIGHT + CFG.PADDING * 2) * 6);
      } else {
         var1 = ((MenuElement)var2.get(var2.size() - 1)).getPosY();
         var1 = ((MenuElement)var2.get(var2.size() - 1)).getHeight() + var1 + CFG.PADDING;
      }

      this.initMenu(var14, var7 - var8, var4, var3, var1, var2, true, true);
      this.updateLanguage();
      Menu_InGame_OfferAlliance.lTime = System.currentTimeMillis();
   }

   @Override
   protected final void actionElement(int var1) {
      if (var1 == this.getMenuElementsSize() - 1) {
         var1 = CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID();
         if (CFG.game.getCiv(var1).isMoveAtWarPlayer <= 0) {
            CFG.oAI.getAI_Style(CFG.game.getCiv(var1).getAI_Style()).moveAtWarPlayer(var1);
            CFG.game.getCiv(var1).isMoveAtWarPlayer = 1;
            CFG.menuManager.updateInGame_TOP_All(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
         } else {
            CFG.game.getCiv(var1).isMoveAtWarPlayer = 0;
         }

         CFG.toast.setInView(CFG.langManager.get("Sent") + "!", CFG.COLOR_TEXT_MODIFIER_POSITIVE);
         CFG.toast.setTimeInView(4500);
         CFG.menuManager.rebuildInGame_Messages();
         CFG.menuManager.setVisible_Menu_InGame_CurrentWars(true);
         this.setVisible(false);
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
         var9 = this.getPosX();
         var7 = this.getWidth();
         var10 = ImageManager.getImage(Images.new_game_top_edge).getWidth();
         var8 = this.getPosY();
         var11 = ImageManager.getImage(Images.new_game_top_edge).getHeight();
         var6 = ImageManager.getImage(Images.new_game_top_edge).getWidth();
         int var12 = this.getHeight();
         var14.draw2(var1, var9 + 2 + var7 - var10 + var2, var8 - var11 + var3, var6, CFG.PADDING + var12, true, true);
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
         CFG.setRender_3(true);
         this.endClip(var1, var2, var3, var4);
      } else {
         var1.setColor(Color.WHITE);
         Image var15 = ImageManager.getImage(Images.new_game_top_edge);
         int var33 = this.getPosX();
         int var18 = this.getPosY();
         int var30 = ImageManager.getImage(Images.new_game_top_edge).getHeight();
         int var24 = this.getWidth();
         int var21 = ImageManager.getImage(Images.new_game_top_edge).getWidth();
         int var27 = this.getHeight();
         var15.draw2(var1, var33 - 2 + var2, var18 - var30 + var3, var24 - var21 + 4, CFG.PADDING + var27, false, true);
         var15 = ImageManager.getImage(Images.new_game_top_edge);
         var24 = this.getPosX();
         var30 = this.getWidth();
         var27 = ImageManager.getImage(Images.new_game_top_edge).getWidth();
         int var35 = this.getPosY();
         var18 = ImageManager.getImage(Images.new_game_top_edge).getHeight();
         var33 = ImageManager.getImage(Images.new_game_top_edge).getWidth();
         var21 = this.getHeight();
         var15.draw2(var1, var24 + 2 + var30 - var27 + var2, var35 - var18 + var3, var33, CFG.PADDING + var21, true, true);
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
