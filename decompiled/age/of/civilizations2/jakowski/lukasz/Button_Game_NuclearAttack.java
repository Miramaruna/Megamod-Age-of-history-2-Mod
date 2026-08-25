package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

class Button_Game_NuclearAttack extends Button_Game {
   protected static final int ANIMATION_T = 1000;
   protected static float TEXT_COST_SCALE = 0.6F;
   protected static final float TEXT_MAIN_SCALE = 0.9F;
   protected static final float TEXT_TERRAIN_SCALE = 0.8F;
   private int animationState;
   private boolean backAnimation;
   private Color colorDiplomacy;
   private Color colorGold;
   private Color colorMovement;
   private float fAlphaMod;
   private int iDiplomacyWidth;
   private int iGoldWidth;
   private int iLeftWidth;
   private int iMovementWidth;
   private int iProvinceID;
   private int iRightIconWidth;
   private int iRightWidth;
   private int iTerrainWidth;
   private long lTime = 0L;
   private long lTimeAnimation;
   private String sDiplomacy;
   private String sGold;
   private String sMovement;
   private String sTerrain;

   // $VF: Duplicated exception handlers to handle obfuscated exceptions
   protected Button_Game_NuclearAttack(String var1, int var2, int var3, int var4, boolean var5) {
      super(var1, 0, var3, var4, var5);
      this.fAlphaMod = 0.0F;
      this.backAnimation = false;
      this.lTimeAnimation = System.currentTimeMillis();
      this.animationState = 0;
      this.iProvinceID = 0;
      this.iLeftWidth = 0;
      this.iRightWidth = 0;
      this.iRightIconWidth = 0;
      this.setWidth(CFG.BUTTON_WIDTH + CFG.BUTTON_WIDTH / 2);
      var2 = Math.max(var2, 0);

      try {
         this.iProvinceID = var2;
         this.sTerrain = CFG.terrainTypesManager.getName(CFG.game.getProvince(var2).getTerrainTypeID());
         CFG.glyphLayout.setText(CFG.fontMain, this.sTerrain);
         this.iTerrainWidth = (int)(CFG.glyphLayout.width * 0.8F);
         this.iLeftWidth = (int)(Math.max(super.getTextWidth() * 0.9F, (float)this.iTerrainWidth) + CFG.PADDING * 7 + CFG.CIV_FLAG_WIDTH);
         TEXT_COST_SCALE = 0.7F;
      } catch (IndexOutOfBoundsException var14) {
         CFG.exceptionStack(var14);
         this.setVisible(false);
         this.setClickable(false);
         return;
      }

      while (true) {
         try {
            if (!(TEXT_COST_SCALE > 0.25F) || this.getHeight() - CFG.PADDING * 2 >= CFG.TEXT_HEIGHT * TEXT_COST_SCALE * 3.0F + CFG.PADDING * 2) {
               break;
            }

            TEXT_COST_SCALE -= 0.01F;
         } catch (IndexOutOfBoundsException var15) {
            CFG.exceptionStack(var15);
            this.setVisible(false);
            this.setClickable(false);
            return;
         }
      }

      label111: {
         try {
            var2 = DiplomacyManager.getNuclearAttackCost(this.iProvinceID, CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
            var3 = DiplomacyManager.getColonizeCost_Movement(this.iProvinceID, CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
            if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoney() >= var2) {
               var16 = CFG.COLOR_INGAME_GOLD;
               break label111;
            }
         } catch (IndexOutOfBoundsException var13) {
            CFG.exceptionStack(var13);
            this.setVisible(false);
            this.setClickable(false);
            return;
         }

         try {
            var16 = CFG.COLOR_TEXT_MODIFIER_NEGATIVE2;
         } catch (IndexOutOfBoundsException var12) {
            CFG.exceptionStack(var12);
            this.setVisible(false);
            this.setClickable(false);
            return;
         }
      }

      label112: {
         try {
            this.colorGold = var16;
            if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getDiplomacyPoints()
               >= CFG.gameAges.getAge(Game_Calendar.CURRENT_AGEID).COLONIZE_COST_DIPLOMACY_POINTS) {
               var17 = CFG.COLOR_INGAME_DIPLOMACY_POINTS;
               break label112;
            }
         } catch (IndexOutOfBoundsException var11) {
            CFG.exceptionStack(var11);
            this.setVisible(false);
            this.setClickable(false);
            return;
         }

         try {
            var17 = CFG.COLOR_TEXT_MODIFIER_NEGATIVE2;
         } catch (IndexOutOfBoundsException var10) {
            CFG.exceptionStack(var10);
            this.setVisible(false);
            this.setClickable(false);
            return;
         }
      }

      label113: {
         try {
            this.colorDiplomacy = var17;
            if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMovePoints() >= var3 * 2) {
               var18 = CFG.COLOR_INGAME_MOVEMENT;
               break label113;
            }
         } catch (IndexOutOfBoundsException var9) {
            CFG.exceptionStack(var9);
            this.setVisible(false);
            this.setClickable(false);
            return;
         }

         try {
            var18 = CFG.COLOR_TEXT_MODIFIER_NEGATIVE2;
         } catch (IndexOutOfBoundsException var8) {
            CFG.exceptionStack(var8);
            this.setVisible(false);
            this.setClickable(false);
            return;
         }
      }

      label62: {
         label61: {
            try {
               this.colorMovement = var18;
               StringBuilder var19 = new StringBuilder();
               this.sGold = CFG.getNumberWithSpaces(var19.append("").append(var2).toString());
               CFG.glyphLayout.setText(CFG.fontMain, this.sGold);
               this.iGoldWidth = (int)(CFG.glyphLayout.width * TEXT_COST_SCALE);
               StringBuilder var20 = new StringBuilder();
               this.sMovement = var20.append("").append(var3 / 10.0F * 2.0F).toString();
               CFG.glyphLayout.setText(CFG.fontMain, this.sMovement);
               this.iMovementWidth = (int)(CFG.glyphLayout.width * TEXT_COST_SCALE);
               this.sDiplomacy = "1";
               CFG.glyphLayout.setText(CFG.fontMain, this.sDiplomacy);
               this.iDiplomacyWidth = (int)(CFG.glyphLayout.width * TEXT_COST_SCALE);
               this.iRightIconWidth = (int)Math.max(
                  Math.max(
                     ImageManager.getImage(Images.top_gold).getWidth() * this.getImageScale(Images.top_gold, TEXT_COST_SCALE),
                     ImageManager.getImage(Images.top_nuclear_weapons).getWidth() * this.getImageScale(Images.top_nuclear_weapons, TEXT_COST_SCALE)
                  ),
                  ImageManager.getImage(Images.top_movement_points).getWidth() * this.getImageScale(Images.top_movement_points, TEXT_COST_SCALE)
               );
               this.iRightWidth = Math.max(Math.max(this.iGoldWidth, this.iMovementWidth), this.iDiplomacyWidth) + CFG.PADDING * 3 + this.iRightIconWidth;
               if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoney() >= var2
                  && CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMovePoints() >= var3
                  && CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getNuclearWeapons() >= 1) {
                  break label61;
               }
            } catch (IndexOutOfBoundsException var7) {
               CFG.exceptionStack(var7);
               this.setVisible(false);
               this.setClickable(false);
               return;
            }

            var5 = false;
            break label62;
         }

         var5 = true;
      }

      try {
         this.setClickable(var5);
      } catch (IndexOutOfBoundsException var6) {
         CFG.exceptionStack(var6);
         this.setVisible(false);
         this.setClickable(false);
      }
   }

   @Override
   protected void buildElementHover() {
      ArrayList var1 = new ArrayList();
      ArrayList var2 = new ArrayList();
      var2.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Cost") + ": "));
      var2.add(
         new MenuElement_Hover_v2_Element_Type_Text(
            CFG.getNumberWithSpaces("" + DiplomacyManager.getNuclearAttackCost(this.iProvinceID, CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())),
            CFG.COLOR_INGAME_GOLD
         )
      );
      var2.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_gold, CFG.PADDING, 0));
      var1.add(new MenuElement_Hover_v2_Element2(var2));
      var2.clear();
      var2.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("MovementPoints") + ": "));
      var2.add(
         new MenuElement_Hover_v2_Element_Type_Text(
            "" + DiplomacyManager.getColonizeCost_Movement(this.iProvinceID, CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) / 10.0F * 2.0F,
            CFG.COLOR_INGAME_MOVEMENT
         )
      );
      var2.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_movement_points, CFG.PADDING, 0));
      var1.add(new MenuElement_Hover_v2_Element2(var2));
      var2.clear();
      var2.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("NuclearWeapon") + ": "));
      var2.add(new MenuElement_Hover_v2_Element_Type_Text("1", Color.YELLOW));
      var2.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_nuclear_weapons, CFG.PADDING, 0));
      var1.add(new MenuElement_Hover_v2_Element2(var2));
      var2.clear();
      var2.add(new MenuElement_Hover_v2_Element_Type_Space());
      var1.add(new MenuElement_Hover_v2_Element2(var2));
      var2.clear();
      var2.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Terrain") + ": "));
      var2.add(
         new MenuElement_Hover_v2_Element_Type_Text(
            CFG.terrainTypesManager.getName(CFG.game.getProvince(this.iProvinceID).getTerrainTypeID()), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
         )
      );
      var2.add(new MenuElement_Hover_v2_Element_Type_Terrain(CFG.game.getProvince(this.iProvinceID).getTerrainTypeID(), CFG.PADDING, 0));
      var1.add(new MenuElement_Hover_v2_Element2(var2));
      var2.clear();
      this.menuElementHover = new MenuElement_Hover_v2(var1);
   }

   @Override
   protected void drawButtonBG(SpriteBatch var1, int var2, int var3, boolean var4) {
      super.drawButtonBG(var1, var2, var3, var4);
      if (this.lTime < System.currentTimeMillis() - 26L) {
         if (this.backAnimation) {
            this.fAlphaMod -= 0.02F;
            if (this.fAlphaMod < 0.0F) {
               this.backAnimation = false;
            }
         } else {
            this.fAlphaMod += 0.02F;
            if (this.fAlphaMod > 0.4F) {
               this.backAnimation = true;
            }
         }

         this.lTime = System.currentTimeMillis();
      }

      var1.setColor(new Color(CFG.COLOR_GRADIENT_TITLE_BLUE.r, CFG.COLOR_GRADIENT_TITLE_BLUE.g, CFG.COLOR_GRADIENT_TITLE_BLUE.b, 0.45F - this.fAlphaMod));
      CFG.setRender_3(true);
      ImageManager.getImage(Images.line_32_off1)
         .draw(
            var1,
            this.getPosX() + var2,
            this.getPosY() + 1 - ImageManager.getImage(Images.line_32_off1).getHeight() + var3,
            this.getWidth(),
            this.getHeight() - 2
         );
      if (this.animationState >= 0) {
         if (this.animationState == 0) {
            float var5 = Math.min(1.0F * (float)(System.currentTimeMillis() - this.lTimeAnimation) / 1000.0F, 1.0F);
            float var6 = CFG.COLOR_FLAG_FRAME.r;
            float var7 = CFG.COLOR_FLAG_FRAME.g;
            float var8 = CFG.COLOR_FLAG_FRAME.b;
            float var9;
            if (this.getIsHovered()) {
               var9 = 0.625F;
            } else {
               var9 = 0.525F;
            }

            var1.setColor(new Color(var6, var7, var8, var9));
            ImageManager.getImage(Images.line_32_off1)
               .draw(
                  var1,
                  this.getPosX() + CFG.PADDING + var2,
                  this.getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() + var3,
                  (int)((this.getWidth() - CFG.PADDING * 2) * var5),
                  1
               );
            ImageManager.getImage(Images.line_32_off1)
               .draw(
                  var1,
                  this.getPosX() + CFG.PADDING + var2,
                  this.getPosY() + this.getHeight() - 1 - ImageManager.getImage(Images.line_32_off1).getHeight() + var3,
                  (int)((this.getWidth() - CFG.PADDING * 2) * var5),
                  1
               );
            if (this.lTimeAnimation < System.currentTimeMillis() - 1000L) {
               this.animationState++;
               this.lTimeAnimation = System.currentTimeMillis();
            }
         } else {
            float var13 = Math.min(1.0F * (float)(System.currentTimeMillis() - this.lTimeAnimation) / 1000.0F, 1.0F);
            float var12 = CFG.COLOR_FLAG_FRAME.r;
            float var10 = CFG.COLOR_FLAG_FRAME.g;
            float var11 = CFG.COLOR_FLAG_FRAME.b;
            float var14;
            if (this.getIsHovered()) {
               var14 = 0.625F;
            } else {
               var14 = 0.525F;
            }

            var1.setColor(new Color(var12, var10, var11, var14));
            ImageManager.getImage(Images.line_32_off1)
               .draw(
                  var1,
                  this.getPosX() + CFG.PADDING + (int)((this.getWidth() - CFG.PADDING * 2) * var13) + var2,
                  this.getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() + var3,
                  this.getWidth() - CFG.PADDING * 2 - (int)((this.getWidth() - CFG.PADDING * 2) * var13),
                  1
               );
            ImageManager.getImage(Images.line_32_off1)
               .draw(
                  var1,
                  this.getPosX() + CFG.PADDING + (int)((this.getWidth() - CFG.PADDING * 2) * var13) + var2,
                  this.getPosY() + this.getHeight() - 1 - ImageManager.getImage(Images.line_32_off1).getHeight() + var3,
                  this.getWidth() - CFG.PADDING * 2 - (int)((this.getWidth() - CFG.PADDING * 2) * var13),
                  1
               );
            if (this.lTimeAnimation < System.currentTimeMillis() - 1000L) {
               this.animationState = 0;
               this.lTimeAnimation = System.currentTimeMillis();
            }
         }

         CFG.setRender_3(true);
      }

      var1.setColor(Color.WHITE);
      CFG.game
         .getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
         .getFlag()
         .draw(
            var1,
            this.getPosX() + CFG.PADDING * 2 + var2,
            this.getPosY()
               + this.getHeight() / 2
               - CFG.PADDING / 2
               - CFG.CIV_FLAG_HEIGHT
               - CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getFlag().getHeight()
               + var3,
            CFG.CIV_FLAG_WIDTH,
            CFG.CIV_FLAG_HEIGHT
         );
      ImageManager.getImage(Images.flag_rect)
         .draw(var1, this.getPosX() + CFG.PADDING * 2 + var2, this.getPosY() + this.getHeight() / 2 - CFG.PADDING / 2 - CFG.CIV_FLAG_HEIGHT + var3);
      CFG.fontMain.getData().setScale(0.9F);
      if (var4) {
         CFG.drawText(
            var1,
            this.getTextToDraw(),
            this.getPosX() + CFG.PADDING * 3 + CFG.CIV_FLAG_WIDTH + var2,
            this.getPosY() + this.getHeight() / 2 - CFG.PADDING / 2 - CFG.CIV_FLAG_HEIGHT / 2 - (int)(this.getTextHeight() * 0.9F / 2.0F) + var3,
            this.getColor(var4)
         );
      } else {
         CFG.drawTextWithShadow(
            var1,
            this.getTextToDraw(),
            this.getPosX() + CFG.PADDING * 3 + CFG.CIV_FLAG_WIDTH + var2,
            this.getPosY() + this.getHeight() / 2 - CFG.PADDING / 2 - CFG.CIV_FLAG_HEIGHT / 2 - (int)(this.getTextHeight() * 0.9F / 2.0F) + var3,
            this.getColor(var4)
         );
      }

      CFG.terrainTypesManager
         .getIcon(CFG.game.getProvince(this.iProvinceID).getTerrainTypeID())
         .draw(
            var1,
            this.getPosX() + CFG.PADDING * 2 + var2,
            this.getPosY()
               + this.getHeight() / 2
               + CFG.PADDING / 2
               - CFG.terrainTypesManager.getIcon(CFG.game.getProvince(this.iProvinceID).getTerrainTypeID()).getHeight()
               + var3,
            CFG.CIV_FLAG_WIDTH,
            CFG.CIV_FLAG_HEIGHT
         );
      CFG.fontMain.getData().setScale(0.8F);
      CFG.drawTextWithShadow(
         var1,
         this.sTerrain,
         this.getPosX() + CFG.PADDING * 3 + CFG.CIV_FLAG_WIDTH + var2,
         this.getPosY() + this.getHeight() / 2 + CFG.PADDING / 2 + CFG.CIV_FLAG_HEIGHT / 2 - (int)(this.getTextHeight() * 0.8F / 2.0F) + var3,
         CFG.COLOR_BUTTON_GAME_TEXT
      );
      CFG.fontMain.getData().setScale(TEXT_COST_SCALE);
      CFG.drawTextWithShadow(
         var1,
         this.sMovement,
         this.getPosX() + this.getWidth() - this.iRightIconWidth - CFG.PADDING * 2 - this.iMovementWidth - CFG.PADDING + var2,
         this.getPosY() + this.getHeight() / 2 - (int)(this.getTextHeight() * TEXT_COST_SCALE / 2.0F) + var3,
         this.colorMovement
      );
      CFG.drawTextWithShadow(
         var1,
         this.sGold,
         this.getPosX() + this.getWidth() - this.iRightIconWidth - CFG.PADDING * 2 - this.iGoldWidth - CFG.PADDING + var2,
         this.getPosY()
            + this.getHeight() / 2
            - (int)(this.getTextHeight() * TEXT_COST_SCALE / 2.0F)
            - (int)(this.getTextHeight() * TEXT_COST_SCALE)
            - CFG.PADDING
            + var3,
         this.colorGold
      );
      CFG.drawTextWithShadow(
         var1,
         this.sDiplomacy,
         this.getPosX() + this.getWidth() - this.iRightIconWidth - CFG.PADDING * 2 - this.iDiplomacyWidth - CFG.PADDING + var2,
         this.getPosY() + this.getHeight() / 2 + (int)(this.getTextHeight() * TEXT_COST_SCALE / 2.0F) + CFG.PADDING + var3,
         Color.YELLOW
      );
      ImageManager.getImage(Images.top_gold)
         .draw(
            var1,
            this.getPosX()
               + this.getWidth()
               - CFG.PADDING * 2
               - (int)(ImageManager.getImage(Images.top_gold).getWidth() * this.getImageScale(Images.top_gold, TEXT_COST_SCALE))
               + var2,
            this.getPosY()
               + this.getHeight() / 2
               - CFG.PADDING
               - (int)(this.getTextHeight() * TEXT_COST_SCALE) / 2
               - (int)(this.getTextHeight() * TEXT_COST_SCALE) / 2
               - (int)(ImageManager.getImage(Images.top_gold).getHeight() * this.getImageScale(Images.top_gold, TEXT_COST_SCALE) / 2.0F)
               - ImageManager.getImage(Images.top_gold).getHeight()
               + var3,
            (int)(ImageManager.getImage(Images.top_gold).getWidth() * this.getImageScale(Images.top_gold, TEXT_COST_SCALE)),
            (int)(ImageManager.getImage(Images.top_gold).getHeight() * this.getImageScale(Images.top_gold, TEXT_COST_SCALE))
         );
      ImageManager.getImage(Images.top_movement_points)
         .draw(
            var1,
            this.getPosX()
               + this.getWidth()
               - CFG.PADDING * 2
               - (int)(ImageManager.getImage(Images.top_movement_points).getWidth() * this.getImageScale(Images.top_movement_points, TEXT_COST_SCALE))
               + var2,
            this.getPosY()
               + this.getHeight() / 2
               - (int)(ImageManager.getImage(Images.top_movement_points).getHeight() * this.getImageScale(Images.top_movement_points, TEXT_COST_SCALE) / 2.0F)
               - ImageManager.getImage(Images.top_movement_points).getHeight()
               + var3,
            (int)(ImageManager.getImage(Images.top_movement_points).getWidth() * this.getImageScale(Images.top_movement_points, TEXT_COST_SCALE)),
            (int)(ImageManager.getImage(Images.top_movement_points).getHeight() * this.getImageScale(Images.top_movement_points, TEXT_COST_SCALE))
         );
      ImageManager.getImage(Images.top_nuclear_weapons)
         .draw(
            var1,
            this.getPosX()
               + this.getWidth()
               - CFG.PADDING * 2
               - (int)(ImageManager.getImage(Images.top_nuclear_weapons).getWidth() * this.getImageScale(Images.top_nuclear_weapons, TEXT_COST_SCALE))
               + var2,
            this.getPosY()
               + this.getHeight() / 2
               + CFG.PADDING
               + (int)(this.getTextHeight() * TEXT_COST_SCALE) / 2
               + (int)(this.getTextHeight() * TEXT_COST_SCALE)
               - (int)(ImageManager.getImage(Images.top_nuclear_weapons).getHeight() * this.getImageScale(Images.top_nuclear_weapons, TEXT_COST_SCALE))
               - ImageManager.getImage(Images.top_nuclear_weapons).getHeight()
               + var3,
            (int)(ImageManager.getImage(Images.top_nuclear_weapons).getWidth() * this.getImageScale(Images.top_nuclear_weapons, TEXT_COST_SCALE)),
            (int)(ImageManager.getImage(Images.top_nuclear_weapons).getHeight() * this.getImageScale(Images.top_nuclear_weapons, TEXT_COST_SCALE))
         );
      CFG.fontMain.getData().setScale(1.0F);
   }

   @Override
   protected void drawText(SpriteBatch var1, int var2, int var3, boolean var4) {
   }

   @Override
   protected Color getColor(boolean var1) {
      Color var2;
      if (var1) {
         var2 = CFG.COLOR_BUTTON_GAME_TEXT_IMPORTANT_ACTIVE;
      } else if (this.getClickable()) {
         if (this.getIsHovered()) {
            var2 = CFG.COLOR_BUTTON_GAME_TEXT_IMPORTANT_HOVER;
         } else {
            var2 = CFG.COLOR_BUTTON_GAME_TEXT_IMPORTANT;
         }
      } else {
         var2 = CFG.COLOR_BUTTON_GAME_TEXT_NOT_CLICKABLE;
      }

      return var2;
   }

   protected float getImageScale(int var1, float var2) {
      return CFG.TEXT_HEIGHT * var2 / ImageManager.getImage(var1).getHeight();
   }

   @Override
   protected int getWidth() {
      return Math.max(this.iLeftWidth + this.iRightWidth + CFG.PADDING * 4, super.getWidth());
   }
}
