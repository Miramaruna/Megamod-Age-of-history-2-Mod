package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

public class Button_Diplomacy_DecreaseRelations extends Button_Statistics {
   public static final float FONT_SCALE = 0.7F;
   public int iCivA;
   public int iCivB;
   public String sCivBName;
   public String sRelation;
   public int iRelationWidth = 0;
   public String sCurrentRelation;
   public int iCurrentRelationWidth = 0;
   public String sDiploCost;
   public int iDiploCostWidth = 0;

   public Button_Diplomacy_DecreaseRelations(String sText, float iRelationDiff, int nAggressor, int nDefender, int iPosX, int iPosY, int iWidth) {
      super(sText, 0, iPosX, iPosY, iWidth, CFG.TEXT_HEIGHT + CFG.PADDING * 4);
      this.iCivA = nAggressor;
      this.iCivB = nDefender;
      this.sCivBName = CFG.game.getCiv(nDefender).getCivName();
      this.sDiploCost = "0.2";
      CFG.glyphLayout.setText(CFG.fontMain, this.sDiploCost);
      this.iDiploCostWidth = (int)(CFG.glyphLayout.width * 0.7F);
      this.sRelation = "" + iRelationDiff;
      CFG.glyphLayout.setText(CFG.fontMain, this.sRelation);
      this.iRelationWidth = (int)(CFG.glyphLayout.width * 0.7F);
      this.sCurrentRelation = ""
         + (CFG.game.getCivRelation_OfCivB(this.iCivB, this.iCivA) > 0.0F ? "+" : "")
         + (int)(CFG.game.getCivRelation_OfCivB(this.iCivB, this.iCivA) * 10.0F) / 10.0F
         + " ";
      CFG.glyphLayout.setText(CFG.fontMain, this.sCurrentRelation);
      this.iCurrentRelationWidth = (int)(CFG.glyphLayout.width * 0.7F);
   }

   @Override
   public void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
      oSB.setColor(new Color(CFG.COLOR_GRADIENT_TITLE_BLUE.r, CFG.COLOR_GRADIENT_TITLE_BLUE.g, CFG.COLOR_GRADIENT_TITLE_BLUE.b, 0.25F));
      ImageManager.getImage(Images.line_32_off1)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
            this.getWidth(),
            this.getHeight()
         );
      oSB.setColor(Color.WHITE);
      oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.55F));
      ImageManager.getImage(Images.gradient)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY,
            this.getWidth(),
            this.getHeight() * 3 / 5,
            false,
            false
         );
      oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.275F));
      ImageManager.getImage(Images.slider_gradient)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY,
            this.getWidth() / 4,
            this.getHeight(),
            false,
            false
         );
      ImageManager.getImage(Images.slider_gradient)
         .draw(
            oSB,
            this.getPosX() + this.getWidth() - this.getWidth() / 4 + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY,
            this.getWidth() / 4,
            this.getHeight(),
            true,
            false
         );
      super.drawButtonBG(oSB, iTranslateX, iTranslateY, isActive);
      oSB.setColor(new Color(CFG.COLOR_GRADIENT_TITLE_BLUE.r, CFG.COLOR_GRADIENT_TITLE_BLUE.g, CFG.COLOR_GRADIENT_TITLE_BLUE.b, 0.3F));
      ImageManager.getImage(Images.gradient)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY,
            this.getWidth(),
            CFG.PADDING,
            false,
            false
         );
      ImageManager.getImage(Images.gradient)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() + this.getHeight() - CFG.PADDING - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY,
            this.getWidth(),
            CFG.PADDING,
            false,
            true
         );
      oSB.setColor(new Color(CFG.COLOR_GRADIENT_TITLE_BLUE.r, CFG.COLOR_GRADIENT_TITLE_BLUE.g, CFG.COLOR_GRADIENT_TITLE_BLUE.b, 0.45F));
      ImageManager.getImage(Images.line_32_off1)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() + this.getHeight() - 1 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
            this.getWidth() - 4,
            1
         );
      oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.7F));
      ImageManager.getImage(Images.line_32_off1)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() + this.getHeight() - 2 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
            this.getWidth() - 4,
            1
         );
      oSB.setColor(Color.WHITE);
   }

   @Override
   public void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
      try {
         oSB.setColor(
            new Color(
               CFG.game.getCiv(this.iCivB).getR() / 255.0F, CFG.game.getCiv(this.iCivB).getG() / 255.0F, CFG.game.getCiv(this.iCivB).getB() / 255.0F, 1.0F
            )
         );
      } catch (IndexOutOfBoundsException var6) {
         oSB.setColor(
            new Color(
               CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_ALLIANCE.getR(),
               CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_ALLIANCE.getG(),
               CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_ALLIANCE.getB(),
               1.0F
            )
         );
      }

      ImageManager.getImage(Images.pix255_255_255)
         .draw(
            oSB,
            this.getPosX() + this.iRelationWidth + this.iCurrentRelationWidth + CFG.PADDING * 3 + (int)(this.getTextWidth() * 0.7F) + iTranslateX,
            this.getPosY() + CFG.PADDING * 2 - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY,
            2,
            (int)(ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect))
         );
      oSB.setColor(Color.WHITE);
      CFG.game
         .getCiv(this.iCivB)
         .getFlag()
         .draw(
            oSB,
            this.getPosX() + this.iRelationWidth + this.iCurrentRelationWidth + CFG.PADDING * 3 + (int)(this.getTextWidth() * 0.7F) + 2 + iTranslateX,
            this.getPosY()
               + CFG.PADDING * 2
               + (CFG.TEXT_HEIGHT - (int)(ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect))) / 2
               - CFG.game.getCiv(this.iCivB).getFlag().getHeight()
               + iTranslateY,
            (int)(ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(Images.flag_rect)),
            (int)(ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect))
         );
      ImageManager.getImage(Images.flag_rect)
         .draw(
            oSB,
            this.getPosX() + this.iRelationWidth + this.iCurrentRelationWidth + CFG.PADDING * 3 + (int)(this.getTextWidth() * 0.7F) + 2 + iTranslateX,
            this.getPosY()
               + CFG.PADDING * 2
               + (CFG.TEXT_HEIGHT - (int)(ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect))) / 2
               - ImageManager.getImage(Images.flag_rect).getHeight()
               + iTranslateY,
            (int)(ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(Images.flag_rect)),
            (int)(ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect))
         );
      CFG.fontMain.getData().setScale(0.7F);
      CFG.drawTextWithShadow(
         oSB,
         this.getText(),
         this.getPosX() + CFG.PADDING * 2 + iTranslateX,
         this.getPosY() + CFG.PADDING * 2 + (int)((CFG.TEXT_HEIGHT - CFG.TEXT_HEIGHT * 0.7F) / 2.0F) + iTranslateY,
         CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
      );
      CFG.drawTextWithShadow(
         oSB,
         this.sRelation,
         this.getPosX() + this.iCurrentRelationWidth + CFG.PADDING * 2 + (int)(this.getTextWidth() * 0.7F) + iTranslateX,
         this.getPosY() + CFG.PADDING * 2 + (int)((CFG.TEXT_HEIGHT - CFG.TEXT_HEIGHT * 0.7F) / 2.0F) + iTranslateY,
         CFG.COLOR_TEXT_MODIFIER_NEGATIVE2
      );
      CFG.drawTextWithShadow(
         oSB,
         this.sCurrentRelation,
         this.getPosX() + CFG.PADDING * 2 + (int)(this.getTextWidth() * 0.7F) + iTranslateX,
         this.getPosY() + CFG.PADDING * 2 + (int)((CFG.TEXT_HEIGHT - CFG.TEXT_HEIGHT * 0.7F) / 2.0F) + iTranslateY,
         CFG.COLOR_TEXT_MODIFIER_NEUTRAL
      );
      CFG.drawTextWithShadow(
         oSB,
         this.sCivBName,
         this.getPosX()
            + this.iRelationWidth
            + this.iCurrentRelationWidth
            + CFG.PADDING * 4
            + (int)(ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(Images.flag_rect))
            + (int)(this.getTextWidth() * 0.7F)
            + 2
            + iTranslateX,
         this.getPosY() + CFG.PADDING * 2 + (int)((CFG.TEXT_HEIGHT - CFG.TEXT_HEIGHT * 0.7F) / 2.0F) + iTranslateY,
         this.getColor(isActive)
      );
      ImageManager.getImage(Images.top_diplomacy_points)
         .draw(
            oSB,
            this.getPosX()
               + this.getWidth()
               - CFG.PADDING * 2
               - (int)(ImageManager.getImage(Images.top_diplomacy_points).getWidth() * this.getImageScale(Images.top_diplomacy_points))
               + iTranslateX,
            this.getPosY()
               + CFG.PADDING * 2
               + (CFG.TEXT_HEIGHT - (int)(ImageManager.getImage(Images.top_diplomacy_points).getHeight() * this.getImageScale(Images.top_diplomacy_points)))
                  / 2
               - ImageManager.getImage(Images.top_diplomacy_points).getHeight()
               + iTranslateY,
            (int)(ImageManager.getImage(Images.top_diplomacy_points).getWidth() * this.getImageScale(Images.top_diplomacy_points)),
            (int)(ImageManager.getImage(Images.top_diplomacy_points).getHeight() * this.getImageScale(Images.top_diplomacy_points))
         );
      CFG.drawTextWithShadow(
         oSB,
         this.sDiploCost,
         this.getPosX()
            + this.getWidth()
            - CFG.PADDING * 3
            - this.iDiploCostWidth
            - (int)(ImageManager.getImage(Images.top_diplomacy_points).getWidth() * this.getImageScale(Images.top_diplomacy_points))
            + iTranslateX,
         this.getPosY() + CFG.PADDING * 2 + (int)((CFG.TEXT_HEIGHT - CFG.TEXT_HEIGHT * 0.7F) / 2.0F) + iTranslateY,
         CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getDiplomacyPoints() >= 5 ? Color.WHITE : CFG.COLOR_TEXT_MODIFIER_NEGATIVE2
      );
      CFG.fontMain.getData().setScale(1.0F);
      oSB.setColor(Color.WHITE);
   }

   public final float getImageScale(int nImageID) {
      return (float)CFG.TEXT_HEIGHT / ImageManager.getImage(nImageID).getHeight() < 1.0F
         ? (float)CFG.TEXT_HEIGHT / ImageManager.getImage(nImageID).getHeight()
         : 1.0F;
   }

   @Override
   public Color getColor(boolean isActive) {
      return isActive ? CFG.COLOR_TEXT_OPTIONS_NS_ACTIVE : (this.getIsHovered() ? CFG.COLOR_TEXT_OPTIONS_NS_HOVER : CFG.COLOR_TEXT_OPTIONS_NS);
   }

   @Override
   public void buildElementHover() {
      ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
      ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
      nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("DecreaseRelation"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
      nData.add(new MenuElement_Hover_v2_Element_Type_Flag(this.iCivA, 0, CFG.PADDING));
      nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.game.getCiv(this.iCivA).getCivName()));
      nData.add(new MenuElement_Hover_v2_Element_Type_Text(" - ", CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
      nData.add(new MenuElement_Hover_v2_Element_Type_Flag(this.iCivB, 0, CFG.PADDING));
      nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.game.getCiv(this.iCivB).getCivName()));
      nElements.add(new MenuElement_Hover_v2_Element2(nData));
      nData.clear();
      nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("DiplomacyPoints") + ": ", CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
      nData.add(new MenuElement_Hover_v2_Element_Type_Text("-" + this.sDiploCost, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2));
      nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_diplomacy_points, CFG.PADDING, 0));
      nElements.add(new MenuElement_Hover_v2_Element2(nData));
      nData.clear();
      this.menuElementHover = new MenuElement_Hover_v2(nElements);
   }

   @Override
   public int getSFX() {
      return SoundsManager.SOUND_CLICK2;
   }
}
