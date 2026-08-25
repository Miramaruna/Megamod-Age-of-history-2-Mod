package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Button_Build extends Button {
   public long lTime = 0L;
   public float fAlphaMod = 0.0F;
   public boolean backAnimation = false;
   public static final float FONTSIZE = 0.7F;
   public static final float TEXT_COST_SCALE = 0.6F;
   public static final float TEXT_MOVEMENT_COST_SCALE = 0.6F;
   public int iImageID;
   public boolean row = false;
   public String sCost;
   public int iCostWidth;
   public boolean canBuild_MoneyCost;
   public String sMovementCost;
   public int iMovementCostWidth;
   public boolean canBuild_Movement;
   public boolean inConstruction;
   public String sConstruction;
   public int iConstructionWidth = 0;
   public String sTech;
   public int iTechWidth = 0;

   public Button_Build(
      String sText,
      int nImageID,
      int nCost,
      int nMovementCost,
      int iPosX,
      int iPosY,
      int iWidth,
      boolean isClickable,
      boolean isBuildMax,
      int inConstruction,
      float fTech
   ) {
      super.init(CFG.langManager.get(sText), 0, iPosX, iPosY, iWidth, CFG.BUTTON_HEIGHT * 4 / 5, isClickable, true, true, isBuildMax);
      this.iImageID = nImageID;
      boolean bl = this.inConstruction = inConstruction > 0;
      if (inConstruction > 0) {
         this.sConstruction = CFG.langManager.get("TurnsX", inConstruction);
         CFG.glyphLayout.setText(CFG.fontMain, "" + this.sConstruction);
         this.iConstructionWidth = (int)(CFG.glyphLayout.width * 0.6F);
      }

      this.canBuild_MoneyCost = CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoney() >= nCost;
      this.sCost = nCost > 0 ? "" + nCost : "";
      this.canBuild_Movement = CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMovePoints() >= nMovementCost;
      this.sMovementCost = nMovementCost > 0 ? "" + nMovementCost / 10.0F : "";
      CFG.fontMain.getData().setScale(0.6F);
      CFG.glyphLayout.setText(CFG.fontMain, "" + this.sCost);
      this.iCostWidth = (int)CFG.glyphLayout.width;
      CFG.fontMain.getData().setScale(0.6F);
      CFG.glyphLayout.setText(CFG.fontMain, "" + this.sMovementCost);
      this.iMovementCostWidth = (int)CFG.glyphLayout.width;
      if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getTechnologyLevel() < fTech) {
         this.sTech = "" + (int)(fTech * 100.0F) / 100.0F;
         CFG.glyphLayout.setText(CFG.fontMain, "" + this.sTech);
         this.iTechWidth = (int)CFG.glyphLayout.width;
      }

      CFG.fontMain.getData().setScale(1.0F);
   }

   @Override
   public Button.Checkbox buildCheckbox() {
      return this.checkbox
         ? new Button.Checkbox() {
            @Override
            public void drawCheckBox(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean scrollableY) {
               if (Button_Build.this.getCheckboxState()) {
                  oSB.setColor(new Color(CFG.COLOR_TEXT_MODIFIER_POSITIVE.r, CFG.COLOR_TEXT_MODIFIER_POSITIVE.g, CFG.COLOR_TEXT_MODIFIER_POSITIVE.b, 0.2F));
                  ImageManager.getImage(Images.patt_square)
                     .draw2(
                        oSB,
                        Button_Build.this.getPosX() + iTranslateX,
                        Button_Build.this.getPosY() - ImageManager.getImage(Images.patt_square).getHeight() + 1 + iTranslateY,
                        Button_Diplomacy.iDiploWidth,
                        Button_Build.this.getHeight() - 2,
                        true,
                        false
                     );
                  oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.3F));
                  ImageManager.getImage(Images.gradient)
                     .draw(
                        oSB,
                        Button_Build.this.getPosX() + iTranslateX,
                        Button_Build.this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + 1 + iTranslateY,
                        Button_Diplomacy.iDiploWidth,
                        Button_Build.this.getHeight() / 4,
                        false,
                        false
                     );
                  ImageManager.getImage(Images.gradient)
                     .draw(
                        oSB,
                        Button_Build.this.getPosX() + iTranslateX,
                        Button_Build.this.getPosY()
                           - ImageManager.getImage(Images.gradient).getHeight()
                           + Button_Build.this.getHeight()
                           - 1
                           + iTranslateY
                           - Button_Build.this.getHeight() / 4,
                        Button_Diplomacy.iDiploWidth,
                        Button_Build.this.getHeight() / 4,
                        false,
                        true
                     );
                  oSB.setColor(Color.WHITE);
               }
            }
         }
         : new Button.Checkbox() {
            @Override
            public void drawCheckBox(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean scrollableY) {
            }
         };
   }

   @Override
   public void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
      oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.45F));
      ImageManager.getImage(Images.slider_gradient)
         .draw(
            oSB,
            this.getPosX() + Button_Diplomacy.iDiploWidth - CFG.PADDING * 2 + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY,
            CFG.PADDING * 2,
            this.getHeight(),
            true,
            false
         );
      ImageManager.getImage(Images.slider_gradient)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY,
            CFG.PADDING * 2,
            this.getHeight(),
            false,
            false
         );
      oSB.setColor(new Color(CFG.COLOR_GRADIENT_TITLE_BLUE.r, CFG.COLOR_GRADIENT_TITLE_BLUE.g, CFG.COLOR_GRADIENT_TITLE_BLUE.b, 0.35F));
      ImageManager.getImage(Images.line_32_vertical)
         .draw(
            oSB,
            this.getPosX() + Button_Diplomacy.iDiploWidth + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.line_32_vertical).getHeight() + iTranslateY,
            1,
            this.getHeight()
         );
      if (this.inConstruction) {
         oSB.setColor(new Color(1.0F, 1.0F, 1.0F, 0.525F));
         ImageManager.getImage(Images.patt)
            .draw2(
               oSB,
               this.getPosX() + Button_Diplomacy.iDiploWidth + iTranslateX,
               this.getPosY() - ImageManager.getImage(Images.patt).getHeight() + iTranslateY,
               this.getWidth() - Button_Diplomacy.iDiploWidth,
               this.getHeight()
            );
      }

      if (this.row) {
         oSB.setColor(new Color(CFG.COLOR_GRADIENT_DIPLOMACY.r, CFG.COLOR_GRADIENT_DIPLOMACY.g, CFG.COLOR_GRADIENT_DIPLOMACY.b, 0.4F));
         ImageManager.getImage(Images.slider_gradient)
            .draw(
               oSB,
               this.getPosX() + iTranslateX,
               this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY,
               this.getWidth() / 2,
               this.getHeight()
            );
         ImageManager.getImage(Images.slider_gradient)
            .draw(
               oSB,
               this.getPosX() + this.getWidth() - this.getWidth() / 2 + iTranslateX,
               this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY,
               this.getWidth() / 2,
               this.getHeight(),
               true,
               false
            );
         oSB.setColor(new Color(CFG.COLOR_INFO_BOX_GRADIENT.r, CFG.COLOR_INFO_BOX_GRADIENT.g, CFG.COLOR_INFO_BOX_GRADIENT.b, 0.35F));
         ImageManager.getImage(Images.gradient)
            .draw(
               oSB,
               this.getPosX() + iTranslateX,
               this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY,
               this.getWidth(),
               this.getHeight() / 4
            );
         ImageManager.getImage(Images.gradient)
            .draw(
               oSB,
               this.getPosX() + iTranslateX,
               this.getPosY() + this.getHeight() - this.getHeight() / 4 - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY,
               this.getWidth(),
               this.getHeight() / 4,
               false,
               true
            );
         oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.6F));
         ImageManager.getImage(Images.line_32_off1)
            .draw(
               oSB,
               this.getPosX() + iTranslateX,
               this.getPosY() + this.getHeight() - 1 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
               this.getWidth(),
               1
            );
         ImageManager.getImage(Images.line_32_off1)
            .draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth(), 1);
         if (isActive || this.getIsHovered()) {
            oSB.setColor(new Color(CFG.COLOR_GRADIENT_DIPLOMACY.r, CFG.COLOR_GRADIENT_DIPLOMACY.g, CFG.COLOR_GRADIENT_DIPLOMACY.b, 0.35F));
            ImageManager.getImage(Images.slider_gradient)
               .draw(
                  oSB,
                  this.getPosX() + iTranslateX,
                  this.getPosY() + 1 - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY,
                  this.getWidth(),
                  this.getHeight() - 2,
                  true,
                  false
               );
         }
      } else {
         oSB.setColor(new Color(CFG.COLOR_GRADIENT_DIPLOMACY.r, CFG.COLOR_GRADIENT_DIPLOMACY.g, CFG.COLOR_GRADIENT_DIPLOMACY.b, 0.6F));
         ImageManager.getImage(Images.slider_gradient)
            .draw(
               oSB,
               this.getPosX() + iTranslateX,
               this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY,
               this.getWidth() / 2,
               this.getHeight()
            );
         ImageManager.getImage(Images.slider_gradient)
            .draw(
               oSB,
               this.getPosX() + this.getWidth() - this.getWidth() / 2 + iTranslateX,
               this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY,
               this.getWidth() / 2,
               this.getHeight(),
               true,
               false
            );
         oSB.setColor(new Color(CFG.COLOR_GRADIENT_DARK_BLUE.r, CFG.COLOR_GRADIENT_DARK_BLUE.g, CFG.COLOR_GRADIENT_DARK_BLUE.b, 0.45F));
         ImageManager.getImage(Images.gradient)
            .draw(
               oSB,
               this.getPosX() + iTranslateX,
               this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY,
               this.getWidth(),
               this.getHeight() / 4
            );
         ImageManager.getImage(Images.gradient)
            .draw(
               oSB,
               this.getPosX() + iTranslateX,
               this.getPosY() + this.getHeight() - this.getHeight() / 4 - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY,
               this.getWidth(),
               this.getHeight() / 4,
               false,
               true
            );
         oSB.setColor(new Color(CFG.COLOR_GRADIENT_DIPLOMACY.r, CFG.COLOR_GRADIENT_DIPLOMACY.g, CFG.COLOR_GRADIENT_DIPLOMACY.b, 0.85F));
         ImageManager.getImage(Images.line_32_off1)
            .draw(
               oSB,
               this.getPosX() + iTranslateX,
               this.getPosY() + this.getHeight() - 1 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
               this.getWidth(),
               1
            );
         ImageManager.getImage(Images.line_32_off1)
            .draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth(), 1);
         if (isActive || this.getIsHovered()) {
            oSB.setColor(new Color(CFG.COLOR_GRADIENT_DIPLOMACY.r, CFG.COLOR_GRADIENT_DIPLOMACY.g, CFG.COLOR_GRADIENT_DIPLOMACY.b, 0.45F));
            ImageManager.getImage(Images.slider_gradient)
               .draw(
                  oSB,
                  this.getPosX() + iTranslateX,
                  this.getPosY() + 1 - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY,
                  this.getWidth(),
                  this.getHeight() - 2,
                  true,
                  false
               );
         }
      }

      oSB.setColor(Color.WHITE);
   }

   @Override
   public void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
      if (this.getIsHovered()) {
         if (this.lTime < System.currentTimeMillis() - 30L) {
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

         oSB.setColor(new Color(1.0F, 1.0F, 1.0F, 1.0F - this.fAlphaMod));
         CFG.setRender_3(true);
      } else {
         this.backAnimation = false;
         this.fAlphaMod = 0.0F;
         this.lTime = System.currentTimeMillis();
      }

      ImageManager.getImage(this.iImageID)
         .draw(
            oSB,
            this.getPosX() + Button_Diplomacy.iDiploWidth / 2 - ImageManager.getImage(this.iImageID).getWidth() / 2 + iTranslateX,
            this.getPosY() + this.getHeight() / 2 - ImageManager.getImage(this.iImageID).getHeight() / 2 + iTranslateY
         );
      oSB.setColor(Color.WHITE);
      if (!this.inConstruction) {
         if (this.iTechWidth > 0) {
            ImageManager.getImage(Images.technology)
               .draw(
                  oSB,
                  this.getPosX()
                     + this.getWidth()
                     - CFG.PADDING * 2
                     - (int)(ImageManager.getImage(Images.technology).getWidth() * this.getImageScale(Images.technology, 0.6F))
                     + iTranslateX,
                  this.getPosY()
                     + this.getHeight() / 2
                     - ImageManager.getImage(Images.technology).getHeight()
                     - (int)(ImageManager.getImage(Images.technology).getHeight() * this.getImageScale(Images.technology, 0.6F)) / 2
                     + iTranslateY,
                  (int)(ImageManager.getImage(Images.technology).getWidth() * this.getImageScale(Images.technology, 0.6F)),
                  (int)(ImageManager.getImage(Images.technology).getHeight() * this.getImageScale(Images.technology, 0.6F))
               );
            CFG.fontMain.getData().setScale(0.6F);
            CFG.drawTextWithShadow(
               oSB,
               this.sTech,
               this.getPosX()
                  + this.getWidth()
                  - CFG.PADDING * 2
                  - this.iTechWidth
                  - (int)(ImageManager.getImage(Images.technology).getWidth() * this.getImageScale(Images.technology, 0.6F))
                  - CFG.PADDING
                  + iTranslateX,
               this.getPosY() + this.getHeight() / 2 - (int)(this.getTextHeight() * 0.6F) / 2 + iTranslateY,
               CFG.COLOR_TEXT_MODIFIER_NEGATIVE2
            );
         } else if (this.sCost.length() > 0 && this.sMovementCost.length() > 0) {
            if (this.sCost.length() > 0) {
               ImageManager.getImage(Images.top_gold)
                  .draw(
                     oSB,
                     this.getPosX()
                        + this.getWidth()
                        - CFG.PADDING * 2
                        - (int)(ImageManager.getImage(Images.top_gold).getWidth() * this.getImageScale(Images.top_gold, 0.6F))
                        + iTranslateX,
                     this.getPosY()
                        + this.getHeight() / 2
                        - (int)(ImageManager.getImage(Images.top_gold).getHeight() * this.getImageScale(Images.top_gold, 0.6F))
                        - ImageManager.getImage(Images.top_gold).getHeight()
                        - CFG.PADDING / 2
                        + iTranslateY,
                     (int)(ImageManager.getImage(Images.top_gold).getWidth() * this.getImageScale(Images.top_gold, 0.6F)),
                     (int)(ImageManager.getImage(Images.top_gold).getHeight() * this.getImageScale(Images.top_gold, 0.6F))
                  );
               CFG.fontMain.getData().setScale(0.6F);
               CFG.drawTextWithShadow(
                  oSB,
                  this.sCost,
                  this.getPosX()
                     + this.getWidth()
                     - CFG.PADDING * 2
                     - Math.max(
                        (int)(ImageManager.getImage(Images.top_movement_points).getWidth() * this.getImageScale(Images.top_movement_points, 0.6F)),
                        (int)(ImageManager.getImage(Images.top_gold).getWidth() * this.getImageScale(Images.top_gold, 0.6F))
                     )
                     - CFG.PADDING
                     - this.iCostWidth
                     + iTranslateX,
                  this.getPosY() + this.getHeight() / 2 - CFG.PADDING / 2 - (int)(this.getTextHeight() * 0.6F) + iTranslateY,
                  this.canBuild_MoneyCost ? CFG.COLOR_INGAME_GOLD : CFG.COLOR_TEXT_MODIFIER_NEGATIVE2
               );
            }

            if (this.sMovementCost.length() > 0) {
               ImageManager.getImage(Images.top_movement_points)
                  .draw(
                     oSB,
                     this.getPosX()
                        + this.getWidth()
                        - CFG.PADDING * 2
                        - (int)(ImageManager.getImage(Images.top_movement_points).getWidth() * this.getImageScale(Images.top_movement_points, 0.6F))
                        + iTranslateX,
                     this.getPosY() + this.getHeight() / 2 - ImageManager.getImage(Images.top_movement_points).getHeight() + CFG.PADDING / 2 + iTranslateY,
                     (int)(ImageManager.getImage(Images.top_movement_points).getWidth() * this.getImageScale(Images.top_movement_points, 0.6F)),
                     (int)(ImageManager.getImage(Images.top_movement_points).getHeight() * this.getImageScale(Images.top_movement_points, 0.6F))
                  );
               CFG.fontMain.getData().setScale(0.6F);
               CFG.drawTextWithShadow(
                  oSB,
                  this.sMovementCost,
                  this.getPosX()
                     + this.getWidth()
                     - CFG.PADDING * 2
                     - this.iMovementCostWidth
                     - Math.max(
                        (int)(ImageManager.getImage(Images.top_movement_points).getWidth() * this.getImageScale(Images.top_movement_points, 0.6F)),
                        (int)(ImageManager.getImage(Images.top_gold).getWidth() * this.getImageScale(Images.top_gold, 0.6F))
                     )
                     - CFG.PADDING
                     + iTranslateX,
                  this.getPosY() + this.getHeight() / 2 + CFG.PADDING / 2 + iTranslateY,
                  this.canBuild_Movement ? CFG.COLOR_INGAME_MOVEMENT : CFG.COLOR_TEXT_MODIFIER_NEGATIVE2
               );
            }
         } else if (this.sMovementCost.length() > 0) {
            ImageManager.getImage(Images.top_movement_points)
               .draw(
                  oSB,
                  this.getPosX()
                     + this.getWidth()
                     - CFG.PADDING * 2
                     - (int)(ImageManager.getImage(Images.top_movement_points).getWidth() * this.getImageScale(Images.top_movement_points, 0.6F))
                     + iTranslateX,
                  this.getPosY()
                     + this.getHeight() / 2
                     - ImageManager.getImage(Images.top_movement_points).getHeight()
                     - (int)(ImageManager.getImage(Images.top_movement_points).getHeight() * this.getImageScale(Images.top_movement_points, 0.6F)) / 2
                     + iTranslateY,
                  (int)(ImageManager.getImage(Images.top_movement_points).getWidth() * this.getImageScale(Images.top_movement_points, 0.6F)),
                  (int)(ImageManager.getImage(Images.top_movement_points).getHeight() * this.getImageScale(Images.top_movement_points, 0.6F))
               );
            CFG.fontMain.getData().setScale(0.6F);
            CFG.drawTextWithShadow(
               oSB,
               this.sMovementCost,
               this.getPosX()
                  + this.getWidth()
                  - CFG.PADDING * 2
                  - this.iMovementCostWidth
                  - (int)(ImageManager.getImage(Images.top_movement_points).getWidth() * this.getImageScale(Images.top_movement_points, 0.6F))
                  - CFG.PADDING
                  + iTranslateX,
               this.getPosY() + this.getHeight() / 2 - (int)(this.getTextHeight() * 0.6F) / 2 + iTranslateY,
               this.canBuild_Movement ? CFG.COLOR_INGAME_MOVEMENT : CFG.COLOR_TEXT_MODIFIER_NEGATIVE2
            );
         } else if (this.getCheckboxState()) {
            ImageManager.getImage(Images.icon_check_true)
               .draw(
                  oSB,
                  this.getPosX()
                     + this.getWidth()
                     - CFG.PADDING * 2
                     - (int)(ImageManager.getImage(Images.icon_check_true).getWidth() * this.getImageScale(Images.icon_check_true, 0.6F))
                     + iTranslateX,
                  this.getPosY()
                     + this.getHeight() / 2
                     - ImageManager.getImage(Images.icon_check_true).getHeight()
                     - (int)(ImageManager.getImage(Images.icon_check_true).getHeight() * this.getImageScale(Images.icon_check_true, 0.6F)) / 2
                     + iTranslateY,
                  (int)(ImageManager.getImage(Images.icon_check_true).getWidth() * this.getImageScale(Images.icon_check_true, 0.6F)),
                  (int)(ImageManager.getImage(Images.icon_check_true).getHeight() * this.getImageScale(Images.icon_check_true, 0.6F))
               );
         }
      } else {
         ImageManager.getImage(Images.time)
            .draw(
               oSB,
               this.getPosX()
                  + this.getWidth()
                  - CFG.PADDING * 2
                  - (int)(ImageManager.getImage(Images.time).getWidth() * this.getImageScale(Images.time, 0.6F))
                  + iTranslateX,
               this.getPosY()
                  + this.getHeight() / 2
                  - ImageManager.getImage(Images.time).getHeight()
                  - (int)(ImageManager.getImage(Images.time).getHeight() * this.getImageScale(Images.time, 0.6F)) / 2
                  + iTranslateY,
               (int)(ImageManager.getImage(Images.time).getWidth() * this.getImageScale(Images.time, 0.6F)),
               (int)(ImageManager.getImage(Images.time).getHeight() * this.getImageScale(Images.time, 0.6F))
            );
         CFG.fontMain.getData().setScale(0.6F);
         CFG.drawTextWithShadow(
            oSB,
            this.sConstruction,
            this.getPosX()
               + this.getWidth()
               - CFG.PADDING * 2
               - this.iConstructionWidth
               - (int)(ImageManager.getImage(Images.time).getWidth() * this.getImageScale(Images.time, 0.6F))
               - CFG.PADDING
               + iTranslateX,
            this.getPosY() + this.getHeight() / 2 - (int)(this.getTextHeight() * 0.6F) / 2 + iTranslateY,
            CFG.COLOR_TEXT_MODIFIER_NEUTRAL
         );
      }

      CFG.fontMain.getData().setScale(0.7F);
      CFG.drawTextWithShadow(
         oSB,
         this.getText(),
         this.getPosX() + CFG.PADDING + Button_Diplomacy.iDiploWidth + iTranslateX,
         this.getPosY() + this.getHeight() / 2 - (int)(this.getTextHeight() * 0.7F / 2.0F) + iTranslateY,
         this.getColor(isActive)
      );
      CFG.fontMain.getData().setScale(1.0F);
   }

   public float getImageScale(int nImageID, float nTextScale) {
      return CFG.TEXT_HEIGHT * nTextScale / ImageManager.getImage(nImageID).getHeight();
   }

   @Override
   public Color getColor(boolean isActive) {
      return isActive
         ? CFG.COLOR_TEXT_OPTIONS_NS_ACTIVE
         : (
            this.getClickable()
               ? (this.getIsHovered() ? CFG.COLOR_TEXT_OPTIONS_NS_HOVER : CFG.COLOR_TEXT_OPTIONS_NS)
               : new Color(CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.r, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.g, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.b, 0.525F)
         );
   }

   @Override
   public void setCurrent(int nCurrent) {
      this.row = nCurrent == 1;
   }
}
