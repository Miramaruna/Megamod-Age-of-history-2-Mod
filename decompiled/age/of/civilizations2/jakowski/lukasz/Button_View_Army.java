package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

public class Button_View_Army extends Button {
   public static final float FONT_SIZE = 0.7F;
   public static final float FONT_SIZE2 = 0.6F;
   public boolean row = false;
   public int iProvinceID = 0;
   public int iPopulation;
   public int iPopulationWidth = 0;
   public int iPopulationPerc;
   public int iPopulationPercWidth = 0;
   public boolean drawSupply = false;

   public Button_View_Army(int iRow, String sText, int nProvinceID, int iCivID, int upkeepCost, int iPosX, int iPosY, int iWidth) {
      super.init(sText, 0, iPosX, iPosY, iWidth, Menu_InGame_View_Army.getButtonHeight(), true, true, false, false);
      this.row = iRow % 2 == 0;
      this.iProvinceID = nProvinceID;
      this.drawSupply = CFG.game.getProvince(this.iProvinceID).getLevelOfSupply() > 0;
      this.iPopulation = CFG.game.getProvince(this.iProvinceID).getArmyCivID(iCivID);
      CFG.glyphLayout.setText(CFG.fontMain, "" + this.iPopulation);
      this.iPopulationWidth = (int)(CFG.glyphLayout.width * 0.7F);
      this.iPopulationPerc = upkeepCost;
      CFG.glyphLayout.setText(CFG.fontMain, "" + this.iPopulationPerc);
      this.iPopulationPercWidth = (int)(CFG.glyphLayout.width * 0.6F);
   }

   @Override
   public void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
      if (this.row) {
         oSB.setColor(new Color(CFG.COLOR_GRADIENT_TITLE_BLUE.r, CFG.COLOR_GRADIENT_TITLE_BLUE.g, CFG.COLOR_GRADIENT_TITLE_BLUE.b, 0.1F));
         ImageManager.getImage(Images.pix255_255_255)
            .draw(
               oSB,
               this.getPosX() + iTranslateX,
               this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY,
               this.getWidth(),
               this.getHeight()
            );
         if (isActive || this.getIsHovered()) {
            oSB.setColor(new Color(CFG.COLOR_GRADIENT_DIPLOMACY.r, CFG.COLOR_GRADIENT_DIPLOMACY.g, CFG.COLOR_GRADIENT_DIPLOMACY.b, 0.65F));
            ImageManager.getImage(Images.line_32_off1)
               .draw(
                  oSB,
                  this.getPosX() + iTranslateX,
                  this.getPosY() + 1 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
                  this.getWidth(),
                  this.getHeight() - 2,
                  true,
                  false
               );
         }

         oSB.setColor(new Color(CFG.COLOR_INFO_BOX_GRADIENT.r, CFG.COLOR_INFO_BOX_GRADIENT.g, CFG.COLOR_INFO_BOX_GRADIENT.b, 0.275F));
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
         oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.55F));
         ImageManager.getImage(Images.pix255_255_255)
            .draw(
               oSB,
               this.getPosX() + iTranslateX,
               this.getPosY() + this.getHeight() - 1 - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY,
               this.getWidth(),
               1
            );
         ImageManager.getImage(Images.pix255_255_255)
            .draw(
               oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY, this.getWidth(), 1
            );
         oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.4F));
         ImageManager.getImage(Images.line_32_off1)
            .draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth(), 1);
         ImageManager.getImage(Images.line_32_off1)
            .draw(
               oSB,
               this.getPosX() + iTranslateX,
               this.getPosY() + this.getHeight() - 1 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
               this.getWidth(),
               1
            );
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
         if (isActive || this.getIsHovered()) {
            oSB.setColor(new Color(CFG.COLOR_GRADIENT_DIPLOMACY.r, CFG.COLOR_GRADIENT_DIPLOMACY.g, CFG.COLOR_GRADIENT_DIPLOMACY.b, 0.45F));
            ImageManager.getImage(Images.line_32_off1)
               .draw(
                  oSB,
                  this.getPosX() + iTranslateX,
                  this.getPosY() + 1 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
                  this.getWidth(),
                  this.getHeight() - 2,
                  true,
                  false
               );
         }

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
            .draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth(), 1);
         ImageManager.getImage(Images.line_32_off1)
            .draw(
               oSB,
               this.getPosX() + iTranslateX,
               this.getPosY() + this.getHeight() - 1 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
               this.getWidth(),
               1
            );
      }

      if (this.iProvinceID == CFG.game.getActiveProvinceID()) {
         oSB.setColor(new Color(CFG.COLOR_GRADIENT_TITLE_BLUE.r, CFG.COLOR_GRADIENT_TITLE_BLUE.g, CFG.COLOR_GRADIENT_TITLE_BLUE.b, 0.7F));
         ImageManager.getImage(Images.slider_gradient)
            .draw(
               oSB,
               this.getPosX() + iTranslateX,
               this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY,
               this.getWidth(),
               this.getHeight()
            );
      }

      oSB.setColor(Color.WHITE);
   }

   @Override
   public void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
      CFG.game
         .getCiv(CFG.game.getProvince(this.iProvinceID).getCivID())
         .getFlag()
         .draw(
            oSB,
            this.getPosX() + CFG.PADDING + iTranslateX,
            this.getPosY()
               + this.getHeight() / 2
               - (int)(ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(ImageManager.getImage(Images.flag_rect).getHeight())) / 2
               - CFG.game.getCiv(CFG.game.getProvince(this.iProvinceID).getCivID()).getFlag().getHeight()
               + iTranslateY,
            (int)(ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(ImageManager.getImage(Images.flag_rect).getHeight())),
            (int)(ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(ImageManager.getImage(Images.flag_rect).getHeight()))
         );
      ImageManager.getImage(Images.flag_rect)
         .draw(
            oSB,
            this.getPosX() + CFG.PADDING + iTranslateX,
            this.getPosY()
               + this.getHeight() / 2
               - (int)(ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(ImageManager.getImage(Images.flag_rect).getHeight())) / 2
               + iTranslateY
               - ImageManager.getImage(Images.flag_rect).getHeight(),
            (int)(ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(ImageManager.getImage(Images.flag_rect).getHeight())),
            (int)(ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(ImageManager.getImage(Images.flag_rect).getHeight()))
         );
      CFG.fontMain.getData().setScale(0.7F);
      CFG.drawText(
         oSB,
         this.getText(),
         this.getPosX()
            + CFG.PADDING * 2
            + (int)(ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(ImageManager.getImage(Images.flag_rect).getHeight()))
            + iTranslateX,
         this.getPosY() + this.getHeight() / 2 - (int)(this.getTextHeight() * 0.7F / 2.0F) + iTranslateY,
         this.getColor(isActive)
      );
      CFG.drawText(
         oSB,
         "" + this.iPopulation,
         this.getPosX()
            + CFG.PADDING * 2
            + (int)(ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(ImageManager.getImage(Images.flag_rect).getHeight()))
            + (int)(this.getTextWidth() * 0.7F)
            + iTranslateX,
         this.getPosY() + this.getHeight() / 2 - (int)(this.getTextHeight() * 0.7F / 2.0F) + iTranslateY,
         CFG.COLOR_TEXT_NUM_OF_PROVINCES
      );
      ImageManager.getImage(Images.top_gold)
         .draw(
            oSB,
            this.getPosX()
               + this.getWidth()
               - CFG.PADDING
               - (int)(ImageManager.getImage(Images.top_gold).getWidth() * this.getImageScale2(ImageManager.getImage(Images.top_gold).getHeight()))
               + iTranslateX,
            this.getPosY()
               + this.getHeight() / 2
               - (int)(ImageManager.getImage(Images.top_gold).getHeight() * this.getImageScale2(ImageManager.getImage(Images.top_gold).getHeight())) / 2
               + iTranslateY
               - ImageManager.getImage(Images.top_gold).getHeight(),
            (int)(ImageManager.getImage(Images.top_gold).getWidth() * this.getImageScale2(ImageManager.getImage(Images.top_gold).getHeight())),
            (int)(ImageManager.getImage(Images.top_gold).getHeight() * this.getImageScale2(ImageManager.getImage(Images.top_gold).getHeight()))
         );
      if (this.drawSupply) {
         ImageManager.getImage(Images.b_supply)
            .draw(
               oSB,
               this.getPosX()
                  + this.getWidth()
                  - CFG.PADDING * 3
                  - this.iPopulationPercWidth
                  - (int)(ImageManager.getImage(Images.top_gold).getWidth() * this.getImageScale2(ImageManager.getImage(Images.top_gold).getHeight()))
                  - (int)(ImageManager.getImage(Images.b_supply).getWidth() * this.getImageScale(ImageManager.getImage(Images.b_supply).getHeight()))
                  + iTranslateX,
               this.getPosY()
                  + this.getHeight() / 2
                  - (int)(ImageManager.getImage(Images.b_supply).getHeight() * this.getImageScale(ImageManager.getImage(Images.b_supply).getHeight())) / 2
                  + iTranslateY
                  - ImageManager.getImage(Images.b_supply).getHeight(),
               (int)(ImageManager.getImage(Images.b_supply).getWidth() * this.getImageScale(ImageManager.getImage(Images.b_supply).getHeight())),
               (int)(ImageManager.getImage(Images.b_supply).getHeight() * this.getImageScale(ImageManager.getImage(Images.b_supply).getHeight()))
            );
      }

      CFG.fontMain.getData().setScale(0.6F);
      CFG.drawText(
         oSB,
         "" + this.iPopulationPerc,
         this.getPosX()
            + this.getWidth()
            - CFG.PADDING * 2
            - this.iPopulationPercWidth
            - (int)(ImageManager.getImage(Images.top_gold).getWidth() * this.getImageScale2(ImageManager.getImage(Images.top_gold).getHeight()))
            + iTranslateX,
         this.getPosY() + this.getHeight() / 2 - (int)(this.getTextHeight() * 0.6F / 2.0F) + iTranslateY,
         CFG.COLOR_TEXT_MODIFIER_NEGATIVE2
      );
      CFG.fontMain.getData().setScale(1.0F);
   }

   @Override
   public Color getColor(boolean isActive) {
      return isActive
         ? CFG.COLOR_TEXT_OPTIONS_NS_ACTIVE
         : (
            this.getClickable()
               ? (this.getIsHovered() ? CFG.COLOR_TEXT_OPTIONS_NS_HOVER : CFG.COLOR_TEXT_OPTIONS_NS)
               : new Color(CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.r, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.g, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.b, 0.6F)
         );
   }

   @Override
   public int getCurrent() {
      return this.iProvinceID;
   }

   public final float getImageScale(int nHeight) {
      return (float)CFG.TEXT_HEIGHT / nHeight;
   }

   public final float getImageScale2(int nHeight) {
      return CFG.TEXT_HEIGHT * 0.6F / nHeight;
   }

   @Override
   public void buildElementHover() {
      ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
      ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
      nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()));
      nData.add(
         new MenuElement_Hover_v2_Element_Type_Text(
            CFG.game.getProvince(this.iProvinceID).getName().length() > 0
               ? CFG.game.getProvince(this.iProvinceID).getName()
               : CFG.game.getCiv(CFG.game.getProvince(this.iProvinceID).getCivID()).getCivName(),
            CFG.COLOR_TEXT_NUM_OF_PROVINCES
         )
      );
      nElements.add(new MenuElement_Hover_v2_Element2(nData));
      nData.clear();
      nData.add(new MenuElement_Hover_v2_Element_Type_Space());
      nElements.add(new MenuElement_Hover_v2_Element2(nData));
      nData.clear();
      nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Army") + ": "));
      nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + CFG.getNumberWithSpaces("" + this.iPopulation), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
      nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), CFG.PADDING, 0));
      nElements.add(new MenuElement_Hover_v2_Element2(nData));
      nData.clear();
      nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("MilitaryUpkeep") + ": "));
      nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + this.iPopulationPerc, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2));
      nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_gold, CFG.PADDING, 0));
      nElements.add(new MenuElement_Hover_v2_Element2(nData));
      nData.clear();
      nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + (int)((float)this.iPopulationPerc / this.iPopulation * 100.0F) / 100.0F, CFG.COLOR_INGAME_GOLD));
      nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_gold, CFG.PADDING, CFG.PADDING));
      nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("PerUnit")));
      nElements.add(new MenuElement_Hover_v2_Element2(nData));
      nData.clear();
      nData.add(new MenuElement_Hover_v2_Element_Type_Space());
      nElements.add(new MenuElement_Hover_v2_Element2(nData));
      nData.clear();
      nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Development") + ": "));
      nData.add(
         new MenuElement_Hover_v2_Element_Type_Text(
            "" + (int)(CFG.game.getProvince(this.iProvinceID).getDevelopmentLevel() * 100.0F) / 100.0F, CFG.COLOR_TEXT_MODIFIER_NEUTRAL2
         )
      );
      nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.development, CFG.PADDING, 0));
      nElements.add(new MenuElement_Hover_v2_Element2(nData));
      nData.clear();
      if (CFG.terrainTypesManager.getMilitaryUpkeep(CFG.game.getProvince(this.iProvinceID).getTerrainTypeID()) != 0.0F) {
         nData.add(new MenuElement_Hover_v2_Element_Type_Terrain(CFG.game.getProvince(this.iProvinceID).getTerrainTypeID()));
         nData.add(
            new MenuElement_Hover_v2_Element_Type_Text(
               CFG.terrainTypesManager.getName(CFG.game.getProvince(this.iProvinceID).getTerrainTypeID()), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
            )
         );
         nElements.add(new MenuElement_Hover_v2_Element2(nData));
         nData.clear();
         nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("MilitaryUpkeep") + ": "));
         nData.add(
            new MenuElement_Hover_v2_Element_Type_Text(
               ""
                  + (CFG.terrainTypesManager.getMilitaryUpkeep(CFG.game.getProvince(this.iProvinceID).getTerrainTypeID()) > 0.0F ? "+" : "")
                  + (int)(CFG.terrainTypesManager.getMilitaryUpkeep(CFG.game.getProvince(this.iProvinceID).getTerrainTypeID()) * 100.0F)
                  + "%",
               CFG.terrainTypesManager.getMilitaryUpkeep(CFG.game.getProvince(this.iProvinceID).getTerrainTypeID()) == 0.0F
                  ? CFG.COLOR_TEXT_MODIFIER_NEUTRAL
                  : (
                     CFG.terrainTypesManager.getMilitaryUpkeep(CFG.game.getProvince(this.iProvinceID).getTerrainTypeID()) < 0.0F
                        ? CFG.COLOR_TEXT_MODIFIER_POSITIVE
                        : CFG.COLOR_TEXT_MODIFIER_NEGATIVE2
                  )
            )
         );
         nElements.add(new MenuElement_Hover_v2_Element2(nData));
         nData.clear();
      }

      this.menuElementHover = new MenuElement_Hover_v2(nElements);
   }
}
