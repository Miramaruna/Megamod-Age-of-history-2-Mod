package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

public class Button_View_Technology extends Button {
   public static final float FONT_SIZE = 0.8F;
   public static final float FONT_SIZE2 = 0.7F;
   public boolean row = false;
   public int iCivID = 0;
   public String sCivName;

   public Button_View_Technology(int iRow, int iCivID, int iPosX, int iPosY, int iWidth) {
      super.init(
         "" + (int)(CFG.game.getCiv(Math.abs(iCivID)).getTechnologyLevel() * 100.0F) / 100.0F,
         0,
         iPosX,
         iPosY,
         iWidth,
         Menu_InGame_View_Army.getButtonHeight(),
         true,
         true,
         false,
         false
      );
      this.row = iRow % 2 == 0;
      this.iCivID = iCivID;
      this.sCivName = iCivID >= 0 ? CFG.game.getCiv(iCivID).getCivName() : CFG.langManager.get("Undiscovered");
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

      oSB.setColor(Color.WHITE);
   }

   @Override
   public void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
      try {
         CFG.game
            .getCiv(this.iCivID)
            .getFlag()
            .draw(
               oSB,
               this.getPosX() + CFG.PADDING * 2 + iTranslateX,
               this.getPosY()
                  + this.getHeight() / 2
                  - (int)(ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(ImageManager.getImage(Images.flag_rect).getHeight())) / 2
                  - CFG.game.getCiv(this.iCivID).getFlag().getHeight()
                  + iTranslateY,
               (int)(ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(ImageManager.getImage(Images.flag_rect).getHeight())),
               (int)(ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(ImageManager.getImage(Images.flag_rect).getHeight()))
            );
         ImageManager.getImage(Images.flag_rect)
            .draw(
               oSB,
               this.getPosX() + CFG.PADDING * 2 + iTranslateX,
               this.getPosY()
                  + this.getHeight() / 2
                  - (int)(ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(ImageManager.getImage(Images.flag_rect).getHeight())) / 2
                  + iTranslateY
                  - ImageManager.getImage(Images.flag_rect).getHeight(),
               (int)(ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(ImageManager.getImage(Images.flag_rect).getHeight())),
               (int)(ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(ImageManager.getImage(Images.flag_rect).getHeight()))
            );
      } catch (IndexOutOfBoundsException var6) {
         ImageManager.getImage(Images.randomCivilizationFlag)
            .draw(
               oSB,
               this.getPosX() + CFG.PADDING * 2 + iTranslateX,
               this.getPosY()
                  + this.getHeight() / 2
                  - (int)(ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(ImageManager.getImage(Images.flag_rect).getHeight())) / 2
                  - ImageManager.getImage(Images.randomCivilizationFlag).getHeight()
                  + iTranslateY,
               (int)(ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(ImageManager.getImage(Images.flag_rect).getHeight())),
               (int)(ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(ImageManager.getImage(Images.flag_rect).getHeight()))
            );
         ImageManager.getImage(Images.flag_rect)
            .draw(
               oSB,
               this.getPosX() + CFG.PADDING * 2 + iTranslateX,
               this.getPosY()
                  + this.getHeight() / 2
                  - (int)(ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(ImageManager.getImage(Images.flag_rect).getHeight())) / 2
                  + iTranslateY
                  - ImageManager.getImage(Images.flag_rect).getHeight(),
               (int)(ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(ImageManager.getImage(Images.flag_rect).getHeight())),
               (int)(ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(ImageManager.getImage(Images.flag_rect).getHeight()))
            );
      }

      CFG.fontMain.getData().setScale(0.8F);
      CFG.drawText(
         oSB,
         this.sCivName,
         this.getPosX()
            + CFG.PADDING * 3
            + (int)(ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(ImageManager.getImage(Images.flag_rect).getHeight()))
            + iTranslateX,
         this.getPosY() + this.getHeight() / 2 - (int)(this.getTextHeight() * 0.8F / 2.0F) + iTranslateY,
         this.getColor(isActive)
      );
      ImageManager.getImage(Images.technology)
         .draw(
            oSB,
            this.getPosX()
               + this.getWidth()
               - CFG.PADDING * 2
               - (int)(ImageManager.getImage(Images.technology).getWidth() * this.getImageScale2(ImageManager.getImage(Images.technology).getHeight()))
               + iTranslateX,
            this.getPosY()
               + this.getHeight() / 2
               - (int)(ImageManager.getImage(Images.technology).getHeight() * this.getImageScale2(ImageManager.getImage(Images.technology).getHeight())) / 2
               + iTranslateY
               - ImageManager.getImage(Images.technology).getHeight(),
            (int)(ImageManager.getImage(Images.technology).getWidth() * this.getImageScale2(ImageManager.getImage(Images.technology).getHeight())),
            (int)(ImageManager.getImage(Images.technology).getHeight() * this.getImageScale2(ImageManager.getImage(Images.technology).getHeight()))
         );
      CFG.fontMain.getData().setScale(0.7F);
      CFG.drawText(
         oSB,
         this.getText(),
         this.getPosX()
            + this.getWidth()
            - CFG.PADDING * 3
            - (int)(ImageManager.getImage(Images.technology).getWidth() * this.getImageScale2(ImageManager.getImage(Images.technology).getHeight()))
            - (int)(this.getTextWidth() * 0.7F)
            + iTranslateX,
         this.getPosY() + this.getHeight() / 2 - (int)(this.getTextHeight() * 0.7F / 2.0F) + iTranslateY,
         CFG.COLOR_TEXT_OPTIONS_NS_ACTIVE
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
      return this.iCivID;
   }

   public final float getImageScale(int nHeight) {
      return (float)CFG.TEXT_HEIGHT / nHeight;
   }

   public final float getImageScale2(int nHeight) {
      return CFG.TEXT_HEIGHT * 0.7F / nHeight;
   }

   @Override
   public void buildElementHover() {
      if (this.iCivID >= 0) {
         this.menuElementHover = CFG.game.getHover_TechnologyLevel(this.iCivID);
      } else {
         ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
         ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
         nData.add(new MenuElement_Hover_v2_Element_Type_Flag(-1));
         nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Undiscovered"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
         nElements.add(new MenuElement_Hover_v2_Element2(nData));
         nData.clear();
         this.menuElementHover = new MenuElement_Hover_v2(nElements);
      }
   }

   @Override
   public void actionElement(int iID) {
      if (this.iCivID >= 0) {
         CFG.map.getMapCoordinates().centerToCapital_OrMetProvinceCivID(this.iCivID);
         CFG.toast.setInView(CFG.game.getCiv(this.iCivID).getCivName(), CFG.COLOR_TEXT_NUM_OF_PROVINCES);
      }
   }
}
