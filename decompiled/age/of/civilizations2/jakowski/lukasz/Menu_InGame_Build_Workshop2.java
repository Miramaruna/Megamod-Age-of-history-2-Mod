package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.utils.ScissorStack;
import java.util.ArrayList;

class Menu_InGame_Build_Workshop2 extends SliderMenu {
   private int iProvinceID = -1;

   protected Menu_InGame_Build_Workshop2() {
      ArrayList var1 = new ArrayList();
      int var2 = CFG.CIV_INFO_MENU_WIDTH * 2;
      int var3 = CFG.PADDING;
      var1.add(new Button_Flag_JustFrame(CFG.PADDING, var3, true));
      ((MenuElement)var1.get(var1.size() - 1)).getHeight();
      int var4 = ImageManager.getImage(Images.top_left2).getHeight() + CFG.PADDING * 2 + CFG.BUTTON_HEIGHT * 3 / 5;
      SliderMenuTitle var5 = new SliderMenuTitle(CFG.langManager.get("Workshop2"), CFG.BUTTON_HEIGHT * 3 / 5, true, true);
      int var6 = CFG.GAME_WIDTH / 2;
      int var7 = var2 / 2;
      if (((MenuElement)var1.get(var1.size() - 1)).getPosY() + ((MenuElement)var1.get(var1.size() - 1)).getHeight() + CFG.PADDING + var4
         > CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING * 2) {
         var3 = Math.max(CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING * 2 - var4, (CFG.TEXT_HEIGHT + CFG.PADDING * 2) * 6);
      } else {
         var3 = ((MenuElement)var1.get(var1.size() - 1)).getPosY() + ((MenuElement)var1.get(var1.size() - 1)).getHeight() + CFG.PADDING;
      }

      this.initMenu(var5, var6 - var7, var4, var2, var3, var1, false, true);
      this.updateLanguage();
   }

   protected Menu_InGame_Build_Workshop2(int var1) {
      ArrayList var2 = new ArrayList();
      this.iProvinceID = var1;
      int var3 = CFG.CIV_INFO_MENU_WIDTH * 2;
      StringBuilder var4 = new StringBuilder();
      var4.append(CFG.langManager.get("BuildWorkshop2In"));
      var4.append(": ");
      String var5 = var4.toString();
      String var11;
      if (CFG.game.getProvince(this.iProvinceID).getName().length() > 0) {
         var11 = CFG.game.getProvince(this.iProvinceID).getName();
      } else {
         var11 = CFG.langManager.get("Province");
      }

      var2.add(
         new Button_Build_Building(
            var5,
            var11,
            Images.b_workshop,
            BuildingsManager.getWorkshop2_BuildCost(CFG.game.getProvince(this.iProvinceID).getLevelOfWorkshop2() + 1, this.iProvinceID),
            BuildingsManager.getWorkshop2_BuildMovementCost(CFG.game.getProvince(this.iProvinceID).getLevelOfWorkshop2() + 1),
            0,
            0,
            CFG.BUTTON_WIDTH * 2
         ) {
            @Override
            protected void buildElementHover() {
               ArrayList var1x = new ArrayList();
               ArrayList var2x = new ArrayList();
               StringBuilder var3x = new StringBuilder();
               var3x.append(CFG.langManager.get("IncomeProduction"));
               var3x.append(": ");
               var2x.add(new MenuElement_Hover_v2_Element_Type_Text(var3x.toString()));
               var3x = new StringBuilder();
               var3x.append("+");
               var3x.append(
                  (int)(
                     BuildingsManager.getWorkshop2_IncomeProduction(
                           CFG.game.getProvince(Menu_InGame_Build_Workshop2.this.iProvinceID).getLevelOfWorkshop2() + 1
                        )
                        * 100.0F
                  )
               );
               var3x.append("%");
               var2x.add(new MenuElement_Hover_v2_Element_Type_Text(var3x.toString(), CFG.COLOR_TEXT_MODIFIER_POSITIVE));
               var1x.add(new MenuElement_Hover_v2_Element2(var2x));
               var2x.clear();
               this.menuElementHover = new MenuElement_Hover_v2(var1x);
            }

            @Override
            protected int getWidth() {
               return Menu_InGame_Build_Workshop2.this.getElementW() * 2;
            }
         }
      );
      ((MenuElement)var2.get(var2.size() - 1))
         .setMin((int)(BuildingsManager.getWorkshop2_TechLevel(CFG.game.getProvince(this.iProvinceID).getLevelOfWorkshop2() + 1) * 100.0F));
      var1 = ((MenuElement)var2.get(var2.size() - 1)).getHeight() + 0 + CFG.PADDING;
      var2.add(new Button_FlagActionSliderStyle(CFG.langManager.get("Cancel"), -1, CFG.PADDING + 2, var1, CFG.BUTTON_WIDTH, true) {
         @Override
         protected int getWidth() {
            return Menu_InGame_Build_Workshop2.this.getElementW() - CFG.PADDING - CFG.PADDING / 2;
         }
      });
      var2.add(
         new Button_FlagActionSliderStyle(CFG.langManager.get("Construct"), -1, 2, var1, CFG.BUTTON_WIDTH, true) {
            @Override
            protected void buildElementHover() {
               ArrayList var1 = new ArrayList();
               ArrayList var2 = new ArrayList();
               StringBuilder var3 = new StringBuilder();
               var3.append(CFG.langManager.get("BuildWorkshop2In"));
               var3.append(": ");
               var2.add(new MenuElement_Hover_v2_Element_Type_Text(var3.toString(), CFG.COLOR_TEXT_NUM_OF_PROVINCES));
               String var6;
               if (CFG.game.getProvince(Menu_InGame_Build_Workshop2.this.iProvinceID).getName().length() > 0) {
                  var6 = CFG.game.getProvince(Menu_InGame_Build_Workshop2.this.iProvinceID).getName();
               } else {
                  var6 = CFG.langManager.get("Province");
               }

               var2.add(new MenuElement_Hover_v2_Element_Type_Text(var6));
               var2.add(
                  new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getProvince(Menu_InGame_Build_Workshop2.this.iProvinceID).getCivID(), CFG.PADDING, 0)
               );
               var1.add(new MenuElement_Hover_v2_Element2(var2));
               var2.clear();
               var2.add(new MenuElement_Hover_v2_Element_Type_Text(" - "));
               var3 = new StringBuilder();
               var3.append(CFG.langManager.get("IncomeProduction"));
               var3.append(": ");
               var2.add(new MenuElement_Hover_v2_Element_Type_Text(var3.toString()));
               var3 = new StringBuilder();
               var3.append("+");
               var3.append(
                  (int)(
                     BuildingsManager.getWorkshop2_IncomeProduction(
                           CFG.game.getProvince(Menu_InGame_Build_Workshop2.this.iProvinceID).getLevelOfWorkshop2() + 1
                        )
                        * 100.0F
                  )
               );
               var3.append("%");
               var2.add(new MenuElement_Hover_v2_Element_Type_Text(var3.toString(), CFG.COLOR_TEXT_MODIFIER_POSITIVE));
               var1.add(new MenuElement_Hover_v2_Element2(var2));
               var2.clear();
               var2.add(new MenuElement_Hover_v2_Element_Type_Space());
               var1.add(new MenuElement_Hover_v2_Element2(var2));
               var2.clear();
               var3 = new StringBuilder();
               var3.append(CFG.langManager.get("Cost"));
               var3.append(": ");
               var2.add(new MenuElement_Hover_v2_Element_Type_Text(var3.toString()));
               var3 = new StringBuilder();
               var3.append("");
               var3.append(
                  BuildingsManager.getWorkshop2_BuildCost(
                     CFG.game.getProvince(Menu_InGame_Build_Workshop2.this.iProvinceID).getLevelOfWorkshop2() + 1, Menu_InGame_Build_Workshop2.this.iProvinceID
                  )
               );
               String var4 = var3.toString();
               Color var11;
               if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoney()
                  >= BuildingsManager.getWorkshop2_BuildCost(
                     CFG.game.getProvince(Menu_InGame_Build_Workshop2.this.iProvinceID).getLevelOfWorkshop2() + 1, Menu_InGame_Build_Workshop2.this.iProvinceID
                  )) {
                  var11 = CFG.COLOR_INGAME_GOLD;
               } else {
                  var11 = CFG.COLOR_TEXT_MODIFIER_NEGATIVE2;
               }

               var2.add(new MenuElement_Hover_v2_Element_Type_Text(var4, var11));
               var2.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_gold, CFG.PADDING, 0));
               var1.add(new MenuElement_Hover_v2_Element2(var2));
               var2.clear();
               var3 = new StringBuilder();
               var3.append(CFG.langManager.get("MovementPoints"));
               var3.append(": ");
               var2.add(new MenuElement_Hover_v2_Element_Type_Text(var3.toString()));
               var3 = new StringBuilder();
               var3.append("");
               var3.append(
                  BuildingsManager.getWorkshop2_BuildMovementCost(CFG.game.getProvince(Menu_InGame_Build_Workshop2.this.iProvinceID).getLevelOfWorkshop2() + 1)
                     / 10.0F
               );
               var4 = var3.toString();
               Color var14;
               if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMovePoints()
                  >= BuildingsManager.getWorkshop2_BuildMovementCost(
                     CFG.game.getProvince(Menu_InGame_Build_Workshop2.this.iProvinceID).getLevelOfWorkshop2() + 1
                  )) {
                  var14 = CFG.COLOR_INGAME_MOVEMENT;
               } else {
                  var14 = CFG.COLOR_TEXT_MODIFIER_NEGATIVE2;
               }

               var2.add(new MenuElement_Hover_v2_Element_Type_Text(var4, var14));
               var2.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_movement_points, CFG.PADDING, 0));
               var1.add(new MenuElement_Hover_v2_Element2(var2));
               var2.clear();
               var2.add(
                  new MenuElement_Hover_v2_Element_Type_Text(
                     CFG.langManager
                        .get(
                           "ConstructionWillTakeXurns",
                           BuildingsManager.getWorkshop2_Construction(
                              CFG.game.getProvince(Menu_InGame_Build_Workshop2.this.iProvinceID).getLevelOfWorkshop2() + 1
                           )
                        )
                  )
               );
               var2.add(new MenuElement_Hover_v2_Element_Type_Image(Images.time, CFG.PADDING, 0));
               var1.add(new MenuElement_Hover_v2_Element2(var2));
               var2.clear();
               var3 = new StringBuilder();
               var3.append(CFG.langManager.get("RequiredTechnologyLevel"));
               var3.append(": ");
               var2.add(new MenuElement_Hover_v2_Element_Type_Text(var3.toString()));
               var3 = new StringBuilder();
               var3.append("");
               var3.append(
                  (int)(
                        BuildingsManager.getWorkshop2_TechLevel(CFG.game.getProvince(Menu_InGame_Build_Workshop2.this.iProvinceID).getLevelOfWorkshop2() + 1)
                           * 100.0F
                     )
                     / 100.0F
               );
               var4 = var3.toString();
               Color var17;
               if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getTechnologyLevel()
                  >= BuildingsManager.getWorkshop2_TechLevel(CFG.game.getProvince(Menu_InGame_Build_Workshop2.this.iProvinceID).getLevelOfWorkshop2() + 1)) {
                  var17 = CFG.COLOR_TEXT_TECHNOLOGY;
               } else {
                  var17 = CFG.COLOR_TEXT_MODIFIER_NEGATIVE2;
               }

               var2.add(new MenuElement_Hover_v2_Element_Type_Text(var4, var17));
               var2.add(new MenuElement_Hover_v2_Element_Type_Image(Images.technology, CFG.PADDING, 0));
               int var5;
               if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getTechnologyLevel()
                  >= BuildingsManager.getWorkshop2_TechLevel(CFG.game.getProvince(Menu_InGame_Build_Workshop2.this.iProvinceID).getLevelOfWorkshop2() + 1)) {
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
               int var1x = CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMovePoints();
               int var2x = CFG.game.getProvince(Menu_InGame_Build_Workshop2.this.iProvinceID).getLevelOfWorkshop2();
               boolean var3x = true;
               if (var1x < BuildingsManager.getWorkshop2_BuildMovementCost(var2x + 1)
                  || CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoney()
                     < BuildingsManager.getWorkshop2_BuildCost(
                        CFG.game.getProvince(Menu_InGame_Build_Workshop2.this.iProvinceID).getLevelOfWorkshop2() + 1,
                        Menu_InGame_Build_Workshop2.this.iProvinceID
                     )
                  || !(
                     CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getTechnologyLevel()
                        >= BuildingsManager.getWorkshop2_TechLevel(CFG.game.getProvince(Menu_InGame_Build_Workshop2.this.iProvinceID).getLevelOfWorkshop2() + 1)
                  )) {
                  var3x = false;
               }

               return var3x;
            }

            @Override
            protected int getPosX() {
               return Menu_InGame_Build_Workshop2.this.getElementW() + CFG.PADDING / 2;
            }

            @Override
            protected int getSFX() {
               return SoundsManager.SOUND_BUILD;
            }

            @Override
            protected int getWidth() {
               return Menu_InGame_Build_Workshop2.this.getElementW() - CFG.PADDING - CFG.PADDING / 2;
            }
         }
      );
      int var6 = ImageManager.getImage(Images.top_left2).getHeight() + CFG.PADDING * 2 + CFG.BUTTON_HEIGHT * 3 / 5;
      SliderMenuTitle var12 = new SliderMenuTitle(
         CFG.langManager.get(BuildingsManager.getWorkshop2_Name(CFG.game.getProvince(this.iProvinceID).getLevelOfWorkshop2() + 1)),
         CFG.BUTTON_HEIGHT * 3 / 5,
         true,
         true
      ) {
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
            Image var7 = ImageManager.getImage(Images.dialog_title);
            int var8 = var3x + var5x;
            var7.draw2(
               var1,
               var8 + 2 - ImageManager.getImage(Images.dialog_title).getWidth() + var2x,
               var4x - this.getHeight() - ImageManager.getImage(Images.dialog_title).getHeight(),
               ImageManager.getImage(Images.dialog_title).getWidth(),
               this.getHeight(),
               true,
               false
            );
            var1.setColor(new Color(0.42745098F, 0.35686275F, 0.30588236F, 0.165F));
            var7 = ImageManager.getImage(Images.line_32_off1);
            int var9 = var3x + var2x;
            var7.draw(
               var1, var9, var4x - this.getHeight() + 2 - ImageManager.getImage(Images.line_32_off1).getHeight(), var5x, this.getHeight() - 2, false, true
            );
            var1.setColor(new Color(0.42745098F, 0.35686275F, 0.30588236F, 0.375F));
            ImageManager.getImage(Images.gradient)
               .draw(
                  var1,
                  var9,
                  var4x - this.getHeight() * 2 / 3 - ImageManager.getImage(Images.gradient).getHeight(),
                  var5x,
                  this.getHeight() * 2 / 3,
                  false,
                  true
               );
            var1.setColor(new Color(0.0F, 0.0F, 0.0F, 0.65F));
            ImageManager.getImage(Images.gradient)
               .draw(var1, var9, var4x - CFG.PADDING - ImageManager.getImage(Images.gradient).getHeight(), var5x, CFG.PADDING, false, true);
            var1.setColor(CFG.COLOR_NEW_GAME_EDGE_LINE);
            var7 = ImageManager.getImage(Images.pix255_255_255);
            int var10 = var4x - 1;
            var7.draw(var1, var9, var10 - ImageManager.getImage(Images.pix255_255_255).getHeight(), var5x, 1);
            var1.setColor(new Color(0.0F, 0.0F, 0.0F, 0.55F));
            ImageManager.getImage(Images.line_32_off1).draw(var1, var9, var4x - 2 - ImageManager.getImage(Images.line_32_off1).getHeight(), var5x, 1);
            var1.setColor(new Color(0.0F, 0.0F, 0.0F, 0.8F));
            ImageManager.getImage(Images.line_32_off1).draw(var1, var9, var10 - ImageManager.getImage(Images.line_32_off1).getHeight(), var5x, 1);
            var1.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.45F));
            var7 = ImageManager.getImage(Images.slider_gradient);
            int var11x = ImageManager.getImage(Images.slider_gradient).getHeight();
            int var12x = var5x / 2;
            var7.draw(var1, var9, var10 - var11x, var12x, 1);
            ImageManager.getImage(Images.slider_gradient)
               .draw(var1, var8 - var12x + var2x, var10 - ImageManager.getImage(Images.slider_gradient).getHeight(), var12x, 1, true, false);
            var1.setColor(Color.WHITE);
            CFG.game
               .getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
               .getFlag()
               .draw(
                  var1,
                  Menu_InGame_Build_Workshop2.this.getPosX() + CFG.PADDING * 2 + var2x,
                  Menu_InGame_Build_Workshop2.this.getPosY()
                     - this.getHeight() / 2
                     - CFG.CIV_FLAG_HEIGHT / 2
                     - CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getFlag().getHeight(),
                  CFG.CIV_FLAG_WIDTH,
                  CFG.CIV_FLAG_HEIGHT
               );
            ImageManager.getImage(Images.flag_rect)
               .draw(
                  var1,
                  Menu_InGame_Build_Workshop2.this.getPosX() + CFG.PADDING * 2 + var2x,
                  Menu_InGame_Build_Workshop2.this.getPosY() - this.getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2
               );
            CFG.fontMain.getData().setScale(0.8F);
            CFG.drawText(
               var1,
               this.getText(),
               var3x + (int)(var5x - this.getTextWidth() * 0.8F) / 2 + var2x,
               var4x + 2 - this.getHeight() + (int)(this.getHeight() - this.getTextHeight() * 0.8F) / 2,
               Color.WHITE
            );
            CFG.fontMain.getData().setScale(1.0F);
         }
      };
      int var7 = CFG.GAME_WIDTH / 2;
      int var8 = var3 / 2;
      if (((MenuElement)var2.get(var2.size() - 1)).getPosY() + ((MenuElement)var2.get(var2.size() - 1)).getHeight() + CFG.PADDING + var6
         > CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING * 2) {
         var1 = Math.max(CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING * 2 - var6, (CFG.TEXT_HEIGHT + CFG.PADDING * 2) * 6);
      } else {
         var1 = ((MenuElement)var2.get(var2.size() - 1)).getPosY() + ((MenuElement)var2.get(var2.size() - 1)).getHeight() + CFG.PADDING;
      }

      this.initMenu(var12, var7 - var8, var6, var3, var1, var2, true, true);
      this.updateLanguage();
      Menu_InGame_OfferAlliance.lTime = System.currentTimeMillis();
   }

   @Override
   protected final void actionElement(int var1) {
      if (var1 == this.getMenuElementsSize() - 1) {
         if (BuildingsManager.constructWorkshop2(this.iProvinceID, CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())) {
            Toast var2 = CFG.toast;
            StringBuilder var3 = new StringBuilder();
            var3.append(CFG.langManager.get("Ok"));
            var3.append("!");
            var2.setInView(var3.toString(), CFG.COLOR_TEXT_MODIFIER_POSITIVE);
            CFG.toast.setTimeInView(4500);
            CFG.gameAction.updateInGame_ProvinceInfo();
            if (CFG.menuManager.getInGame_ProvinceBuild_Visible()) {
               CFG.menuManager.setVisible_InGame_ProvinceBuild(true, true);
            }

            if (CFG.viewsManager.getActiveViewID() == ViewsManager.VIEW_DEVELOPMENT_MODE) {
               CFG.game.getProvince(this.iProvinceID).viewBool = true;
               if (CFG.menuManager.getVisible_InGame_View_Stats()) {
                  CFG.menuManager.setVisible_InGame_ViewDevelopment(true);
               }
            } else if (CFG.viewsManager.getActiveViewID() == ViewsManager.VIEW_INCOME_MODE) {
               CFG.game.getProvince(this.iProvinceID).viewBool = true;
               if (CFG.menuManager.getVisible_InGame_View_Stats()) {
                  CFG.menuManager.setVisible_InGame_ViewIncome(true);
               }
            }

            CFG.soundsManager.playSound(SoundsManager.SOUND_WORKSHOP);
         }

         CFG.menuManager.updateInGame_TOP_All(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
         this.setVisible(false);
      } else if (var1 == this.getMenuElementsSize() - 2) {
         this.setVisible(false);
      } else {
         CFG.game.setActiveProvinceID(this.iProvinceID);
         CFG.map.getMapCoordinates().centerToProvinceID(CFG.game.getActiveProvinceID());
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
         var7 = this.getPosX();
         var8 = this.getWidth();
         var10 = ImageManager.getImage(Images.new_game_top_edge).getWidth();
         var9 = this.getPosY();
         var11 = ImageManager.getImage(Images.new_game_top_edge).getHeight();
         var6 = ImageManager.getImage(Images.new_game_top_edge).getWidth();
         int var12 = this.getHeight();
         var14.draw2(var1, var7 + 2 + var8 - var10 + var2, var9 - var11 + var3, var6, CFG.PADDING + var12, true, true);
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
         int var30 = this.getPosX();
         int var27 = this.getPosY();
         int var21 = ImageManager.getImage(Images.new_game_top_edge).getHeight();
         int var24 = this.getWidth();
         int var33 = ImageManager.getImage(Images.new_game_top_edge).getWidth();
         int var18 = this.getHeight();
         var15.draw2(var1, var30 - 2 + var2, var27 - var21 + var3, var24 - var33 + 4, CFG.PADDING + var18, false, true);
         var15 = ImageManager.getImage(Images.new_game_top_edge);
         var30 = this.getPosX();
         var18 = this.getWidth();
         var21 = ImageManager.getImage(Images.new_game_top_edge).getWidth();
         var33 = this.getPosY();
         int var35 = ImageManager.getImage(Images.new_game_top_edge).getHeight();
         var24 = ImageManager.getImage(Images.new_game_top_edge).getWidth();
         var27 = this.getHeight();
         var15.draw2(var1, var30 + 2 + var18 - var21 + var2, var33 - var35 + var3, var24, CFG.PADDING + var27, true, true);
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
