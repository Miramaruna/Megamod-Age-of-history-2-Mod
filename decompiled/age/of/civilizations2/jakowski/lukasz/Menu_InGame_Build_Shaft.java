package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.utils.ScissorStack;
import java.util.ArrayList;

public class Menu_InGame_Build_Shaft extends SliderMenu {
   private int iProvinceID = -1;

   protected Menu_InGame_Build_Shaft() {
      ArrayList var1 = new ArrayList();
      int var2 = CFG.CIV_INFO_MENU_WIDTH * 2;
      int var3 = CFG.PADDING;
      var1.add(new Button_Flag_JustFrame(CFG.PADDING, var3, true));
      ((MenuElement)var1.get(var1.size() - 1)).getHeight();
      int var4 = ImageManager.getImage(Images.top_left2).getHeight() + CFG.PADDING * 2 + CFG.BUTTON_HEIGHT * 3 / 5;
      SliderMenuTitle var5 = new SliderMenuTitle(CFG.langManager.get("Shaft"), CFG.BUTTON_HEIGHT * 3 / 5, true, true);
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

   protected Menu_InGame_Build_Shaft(int var1) {
      ArrayList var2 = new ArrayList();
      this.iProvinceID = var1;
      int var3 = CFG.CIV_INFO_MENU_WIDTH * 2;
      String var4 = CFG.langManager.get("BuildShaftIn") + ": ";
      String var5;
      if (CFG.game.getProvince(this.iProvinceID).getName().length() > 0) {
         var5 = CFG.game.getProvince(this.iProvinceID).getName();
      } else {
         var5 = CFG.langManager.get("Province");
      }

      var2.add(
         new Button_Build_Building(
            this,
            var4,
            var5,
            Images.b_shaft,
            Shaft.getShaft_BuildCost(CFG.game.getProvince(this.iProvinceID).getLevelOfShaft() + 1, this.iProvinceID),
            Shaft.getShaft_BuildMovementCost(CFG.game.getProvince(this.iProvinceID).getLevelOfShaft() + 1),
            0,
            0,
            CFG.BUTTON_WIDTH * 2
         ) {
            final Menu_InGame_Build_Shaft this$0;

            {
               this.this$0 = var1;
            }

            @Override
            protected void buildElementHover() {
               ArrayList var1x = new ArrayList();
               ArrayList var2x = new ArrayList();
               var2x.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("MineMineMineAndMoreMine") + ": "));
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
      ((MenuElement)var2.get(var2.size() - 1)).setMin((int)(Shaft.getShaft_TechLevel(CFG.game.getProvince(this.iProvinceID).getLevelOfShaft() + 1) * 100.0F));
      int var6 = ((MenuElement)var2.get(var2.size() - 1)).getHeight();
      var5 = CFG.langManager.get("Cancel");
      var1 = CFG.PADDING;
      var6 = 0 + var6 + CFG.PADDING;
      var2.add(new Button_FlagActionSliderStyle(this, var5, -1, var1 + 2, var6, CFG.BUTTON_WIDTH, true) {
         final Menu_InGame_Build_Shaft this$0;

         {
            this.this$0 = var1;
         }

         @Override
         protected int getWidth() {
            return this.this$0.getElementW() - CFG.PADDING - CFG.PADDING / 2;
         }
      });
      var2.add(
         new Button_FlagActionSliderStyle(this, CFG.langManager.get("Construct"), -1, 2, var6, CFG.BUTTON_WIDTH, true) {
            final Menu_InGame_Build_Shaft this$0;

            {
               this.this$0 = var1;
            }

            @Override
            protected void buildElementHover() {
               ArrayList var1 = new ArrayList();
               ArrayList var2 = new ArrayList();
               var2.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("") + ": ", CFG.COLOR_TEXT_NUM_OF_PROVINCES));
               String var3;
               if (CFG.game.getProvince(this.this$0.iProvinceID).getName().length() > 0) {
                  var3 = CFG.game.getProvince(this.this$0.iProvinceID).getName();
               } else {
                  var3 = CFG.langManager.get("Province");
               }

               var2.add(new MenuElement_Hover_v2_Element_Type_Text(var3));
               var2.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getProvince(this.this$0.iProvinceID).getCivID(), CFG.PADDING, 0));
               var1.add(new MenuElement_Hover_v2_Element2(var2));
               var2.clear();
               var2.add(new MenuElement_Hover_v2_Element_Type_Space());
               var1.add(new MenuElement_Hover_v2_Element2(var2));
               var2.clear();
               var2.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Cost") + ": "));
               String var4 = "" + Shaft.getShaft_BuildCost(CFG.game.getProvince(this.this$0.iProvinceID).getLevelOfShaft() + 1, this.this$0.iProvinceID);
               Color var6;
               if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoney()
                  >= Shaft.getShaft_BuildCost(CFG.game.getProvince(this.this$0.iProvinceID).getLevelOfShaft() + 1, this.this$0.iProvinceID)) {
                  var6 = CFG.COLOR_INGAME_GOLD;
               } else {
                  var6 = CFG.COLOR_TEXT_MODIFIER_NEGATIVE2;
               }

               var2.add(new MenuElement_Hover_v2_Element_Type_Text(var4, var6));
               var2.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_gold, CFG.PADDING, 0));
               var1.add(new MenuElement_Hover_v2_Element2(var2));
               var2.clear();
               var2.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("MovementPoints") + ": "));
               var4 = "" + Shaft.getShaft_BuildMovementCost(CFG.game.getProvince(this.this$0.iProvinceID).getLevelOfShaft() + 1) / 10.0F;
               Color var7;
               if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMovePoints()
                  >= Shaft.getShaft_BuildMovementCost(CFG.game.getProvince(this.this$0.iProvinceID).getLevelOfShaft() + 1)) {
                  var7 = CFG.COLOR_INGAME_MOVEMENT;
               } else {
                  var7 = CFG.COLOR_TEXT_MODIFIER_NEGATIVE2;
               }

               var2.add(new MenuElement_Hover_v2_Element_Type_Text(var4, var7));
               var2.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_movement_points, CFG.PADDING, 0));
               var1.add(new MenuElement_Hover_v2_Element2(var2));
               var2.clear();
               var2.add(
                  new MenuElement_Hover_v2_Element_Type_Text(
                     CFG.langManager
                        .get("ConstructionWillTakeXurns", Shaft.getShaft_Construction(CFG.game.getProvince(this.this$0.iProvinceID).getLevelOfShaft() + 1))
                  )
               );
               var2.add(new MenuElement_Hover_v2_Element_Type_Image(Images.time, CFG.PADDING, 0));
               var1.add(new MenuElement_Hover_v2_Element2(var2));
               var2.clear();
               var2.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("RequiredTechnologyLevel") + ": "));
               var4 = "" + (int)(Shaft.getShaft_TechLevel(CFG.game.getProvince(this.this$0.iProvinceID).getLevelOfShaft() + 1) * 100.0F) / 100.0F;
               Color var8;
               if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getTechnologyLevel()
                  >= Shaft.getShaft_TechLevel(CFG.game.getProvince(this.this$0.iProvinceID).getLevelOfShaft() + 1)) {
                  var8 = CFG.COLOR_TEXT_TECHNOLOGY;
               } else {
                  var8 = CFG.COLOR_TEXT_MODIFIER_NEGATIVE2;
               }

               var2.add(new MenuElement_Hover_v2_Element_Type_Text(var4, var8));
               var2.add(new MenuElement_Hover_v2_Element_Type_Image(Images.technology, CFG.PADDING, 0));
               int var5;
               if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getTechnologyLevel()
                  >= Shaft.getShaft_TechLevel(CFG.game.getProvince(this.this$0.iProvinceID).getLevelOfShaft() + 1)) {
                  var5 = Images.icon_check_true;
               } else {
                  var5 = Images.icon_check_false;
               }

               var2.add(new MenuElement_Hover_v2_Element_Type_Image(var5, CFG.PADDING, 0));
               var1.add(new MenuElement_Hover_v2_Element2(var2));
               var2.clear();
               this.menuElementHover = new MenuElement_Hover_v2(var1);
            }

            @Override
            protected void drawText(SpriteBatch var1, int var2x, int var3x, boolean var4x) {
               CFG.fontMain.getData().setScale(0.8F);
               CFG.drawText(
                  var1,
                  this.getText(),
                  this.getPosX() + this.getWidth() / 2 - (int)(this.getTextWidth() * 0.8F / 2.0F) + var2x,
                  this.getPosY() + this.getHeight() / 2 - (int)(this.getTextHeight() * 0.8F / 2.0F) + var3x,
                  this.getColor(var4x)
               );
               CFG.fontMain.getData().setScale(1.0F);
            }

            @Override
            protected boolean getClickable() {
               boolean var1x;
               if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMovePoints()
                     >= Shaft.getShaft_BuildMovementCost(CFG.game.getProvince(this.this$0.iProvinceID).getLevelOfShaft() + 1)
                  && CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoney()
                     >= Shaft.getShaft_BuildCost(CFG.game.getProvince(this.this$0.iProvinceID).getLevelOfShaft() + 1, this.this$0.iProvinceID)
                  && CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getTechnologyLevel()
                     >= Shaft.getShaft_TechLevel(CFG.game.getProvince(this.this$0.iProvinceID).getLevelOfShaft() + 1)) {
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
               return SoundsManager.SOUND_BUILD;
            }

            @Override
            protected int getWidth() {
               return this.this$0.getElementW() - CFG.PADDING - CFG.PADDING / 2;
            }
         }
      );
      int var7 = ImageManager.getImage(Images.top_left2).getHeight() + CFG.PADDING * 2 + CFG.BUTTON_HEIGHT * 3 / 5;
      SliderMenuTitle var14 = new SliderMenuTitle(
         this, CFG.langManager.get(Shaft.getShaft_Name(CFG.game.getProvince(this.iProvinceID).getLevelOfShaft() + 1)), CFG.BUTTON_HEIGHT * 3 / 5, true, true
      ) {
         final Menu_InGame_Build_Shaft this$0;

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
            var1.setColor(new Color(0.8784314F, 0.54901963F, 0.14901961F, 0.165F));
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
            var1.setColor(new Color(0.8784314F, 0.54901963F, 0.14901961F, 0.375F));
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
            CFG.game
               .getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
               .getFlag()
               .draw(
                  var1,
                  this.this$0.getPosX() + CFG.PADDING * 2 + var2x,
                  this.this$0.getPosY()
                     - this.getHeight() / 2
                     - CFG.CIV_FLAG_HEIGHT / 2
                     - CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getFlag().getHeight(),
                  CFG.CIV_FLAG_WIDTH,
                  CFG.CIV_FLAG_HEIGHT
               );
            ImageManager.getImage(Images.flag_rect)
               .draw(var1, this.this$0.getPosX() + CFG.PADDING * 2 + var2x, this.this$0.getPosY() - this.getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2);
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
      int var8 = CFG.GAME_WIDTH / 2;
      var6 = var3 / 2;
      var1 = ((MenuElement)var2.get(var2.size() - 1)).getPosY();
      if (((MenuElement)var2.get(var2.size() - 1)).getHeight() + var1 + CFG.PADDING + var7 > CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING * 2) {
         var1 = Math.max(CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING * 2 - var7, (CFG.TEXT_HEIGHT + CFG.PADDING * 2) * 6);
      } else {
         var1 = ((MenuElement)var2.get(var2.size() - 1)).getPosY();
         var1 = ((MenuElement)var2.get(var2.size() - 1)).getHeight() + var1 + CFG.PADDING;
      }

      this.initMenu(var14, var8 - var6, var7, var3, var1, var2, true, true);
      this.updateLanguage();
      Menu_InGame_OfferAlliance.lTime = System.currentTimeMillis();
   }

   @Override
   protected final void actionElement(int var1) {
      if (var1 == this.getMenuElementsSize() - 1) {
         if (Shaft.constructShaft(this.iProvinceID, CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())) {
            CFG.toast.setInView(CFG.langManager.get("") + "!", CFG.COLOR_TEXT_MODIFIER_POSITIVE);
            CFG.toast.setTimeInView(4500);
            CFG.gameAction.updateInGame_ProvinceInfo();
            if (CFG.menuManager.getInGame_ProvinceBuild_Visible()) {
               CFG.menuManager.setVisible_InGame_ProvinceBuild(true, true);
            }

            if (CFG.viewsManager.getActiveViewID() == ViewsManager.VIEW_GROWTH_RATE_MODE) {
               CFG.game.getProvince(this.iProvinceID).viewBool = true;
               if (CFG.menuManager.getVisible_InGame_View_Stats()) {
                  CFG.menuManager.setVisible_InGame_ViewGrowthRate(true);
               }
            }

            CFG.soundsManager.playSound(SoundsManager.SOUND_SHAFT);
         }

         CFG.menuManager.updateInGame_TOP_All(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
         this.setVisible(false);
      } else if (var1 == this.getMenuElementsSize() - 2) {
         this.setVisible(false);
      } else {
         CFG.game.setActiveProvinceID(this.iProvinceID);
         CFG.map.getMapCoordinates().centerToProvinceID(CFG.game.getActiveProvinceID());
         CFG.viewsManager.setActiveViewID(ViewsManager.VIEW_BUILDINGS_MODE);
         if (CFG.viewsManager.getActiveViewID() == ViewsManager.VIEW_BUILDINGS_MODE) {
            CFG.toast.setInView(CFG.langManager.get("Buildings"), CFG.COLOR_TEXT_NUM_OF_PROVINCES);
         }
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
         var10 = this.getPosX();
         var11 = this.getWidth();
         var6 = ImageManager.getImage(Images.new_game_top_edge).getWidth();
         var7 = this.getPosY();
         var8 = ImageManager.getImage(Images.new_game_top_edge).getHeight();
         int var12 = ImageManager.getImage(Images.new_game_top_edge).getWidth();
         var9 = this.getHeight();
         var14.draw2(var1, var10 + 2 + var11 - var6 + var2, var7 - var8 + var3, var12, CFG.PADDING + var9, true, true);
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
         int var18 = this.getPosX();
         int var27 = this.getPosY();
         int var33 = ImageManager.getImage(Images.new_game_top_edge).getHeight();
         int var30 = this.getWidth();
         int var24 = ImageManager.getImage(Images.new_game_top_edge).getWidth();
         int var21 = this.getHeight();
         var15.draw2(var1, var18 - 2 + var2, var27 - var33 + var3, var30 - var24 + 4, CFG.PADDING + var21, false, true);
         var15 = ImageManager.getImage(Images.new_game_top_edge);
         int var35 = this.getPosX();
         var33 = this.getWidth();
         var18 = ImageManager.getImage(Images.new_game_top_edge).getWidth();
         var27 = this.getPosY();
         var30 = ImageManager.getImage(Images.new_game_top_edge).getHeight();
         var21 = ImageManager.getImage(Images.new_game_top_edge).getWidth();
         var24 = this.getHeight();
         var15.draw2(var1, var35 + 2 + var33 - var18 + var2, var27 - var30 + var3, var21, CFG.PADDING + var24, true, true);
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
