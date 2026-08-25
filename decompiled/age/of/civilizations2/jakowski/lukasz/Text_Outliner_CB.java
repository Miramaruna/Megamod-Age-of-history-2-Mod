package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

public class Text_Outliner_CB extends Text {
   public static final float FONT_SCALE = 0.7F;
   public boolean row = false;
   public int iCivID;
   public Color colorText;

   public Text_Outliner_CB(int nWarAgainst, int numOfTurns, int iPosX, int iPosY, int iWidth) {
      super("", CFG.PADDING * 2, iPosX, iPosY, iWidth, Math.max(CFG.TEXT_HEIGHT + CFG.PADDING * 4, CFG.CIV_FLAG_HEIGHT + CFG.PADDING * 4));
      this.iCivID = nWarAgainst;
      this.setText(CFG.langManager.get("TurnsX", numOfTurns));
      this.colorText = numOfTurns > 1 ? CFG.COLOR_TEXT_MODIFIER_NEUTRAL : CFG.COLOR_TEXT_NUM_OF_PROVINCES;
      if (this.getWidth() > iWidth) {
         this.setWidth(iWidth);
      }
   }

   @Override
   public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
      if (this.row) {
         if (!isActive && !this.getIsHovered()) {
            oSB.setColor(
               new Color(CFG.COLOR_TEXT_MODIFIER_NEGATIVE_HOVER.r, CFG.COLOR_TEXT_MODIFIER_NEGATIVE_HOVER.g, CFG.COLOR_TEXT_MODIFIER_NEGATIVE_HOVER.b, 0.9F)
            );
         } else {
            oSB.setColor(
               new Color(
                  CFG.COLOR_TEXT_MODIFIER_NEGATIVE_ACTTIVE.r, CFG.COLOR_TEXT_MODIFIER_NEGATIVE_ACTTIVE.g, CFG.COLOR_TEXT_MODIFIER_NEGATIVE_ACTTIVE.b, 0.8F
               )
            );
         }

         ImageManager.getImage(Images.slider_gradient)
            .draw(
               oSB,
               this.getPosX() + iTranslateX,
               this.getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
               this.getWidth(),
               this.getHeight(),
               true,
               false
            );
         oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.35F));
         ImageManager.getImage(Images.slider_gradient)
            .draw(
               oSB,
               this.getPosX() + this.getWidth() - this.getWidth() / 2 + iTranslateX,
               this.getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
               this.getWidth() / 2,
               this.getHeight(),
               true,
               false
            );
         oSB.setColor(CFG.COLOR_CREATE_NEW_GAME_BOX_PLAYERS);
         ImageManager.getImage(Images.slider_gradient)
            .draw(
               oSB,
               this.getPosX() + iTranslateX,
               this.getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
               this.getWidth(),
               1,
               true,
               false
            );
         ImageManager.getImage(Images.slider_gradient)
            .draw(
               oSB,
               this.getPosX() + iTranslateX,
               this.getPosY() + this.getHeight() - 1 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
               this.getWidth(),
               1,
               true,
               false
            );
         oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.65F));
         ImageManager.getImage(Images.slider_gradient)
            .draw(
               oSB,
               this.getPosX() + iTranslateX,
               this.getPosY() + 1 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
               this.getWidth(),
               1,
               true,
               false
            );
         ImageManager.getImage(Images.slider_gradient)
            .draw(
               oSB,
               this.getPosX() + iTranslateX,
               this.getPosY() + this.getHeight() - 2 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
               this.getWidth(),
               1,
               true,
               false
            );
         oSB.setColor(Color.WHITE);
      } else {
         if (!isActive && !this.getIsHovered()) {
            oSB.setColor(
               new Color(CFG.COLOR_TEXT_MODIFIER_NEGATIVE_HOVER.r, CFG.COLOR_TEXT_MODIFIER_NEGATIVE_HOVER.g, CFG.COLOR_TEXT_MODIFIER_NEGATIVE_HOVER.b, 0.75F)
            );
         } else {
            oSB.setColor(
               new Color(CFG.COLOR_TEXT_MODIFIER_NEGATIVE_HOVER.r, CFG.COLOR_TEXT_MODIFIER_NEGATIVE_HOVER.g, CFG.COLOR_TEXT_MODIFIER_NEGATIVE_HOVER.b, 0.65F)
            );
         }

         ImageManager.getImage(Images.slider_gradient)
            .draw(
               oSB,
               this.getPosX() + iTranslateX,
               this.getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
               this.getWidth(),
               this.getHeight(),
               true,
               false
            );
         oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.35F));
         ImageManager.getImage(Images.slider_gradient)
            .draw(
               oSB,
               this.getPosX() + this.getWidth() - this.getWidth() / 2 + iTranslateX,
               this.getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
               this.getWidth() / 2,
               this.getHeight(),
               true,
               false
            );
         oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.65F));
         ImageManager.getImage(Images.slider_gradient)
            .draw(
               oSB,
               this.getPosX() + iTranslateX,
               this.getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
               this.getWidth(),
               1,
               true,
               false
            );
         ImageManager.getImage(Images.slider_gradient)
            .draw(
               oSB,
               this.getPosX() + iTranslateX,
               this.getPosY() + this.getHeight() - 1 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
               this.getWidth(),
               1,
               true,
               false
            );
         oSB.setColor(Color.WHITE);
      }

      oSB.setColor(
         new Color(CFG.COLOR_TEXT_MODIFIER_NEGATIVE_ACTTIVE.r, CFG.COLOR_TEXT_MODIFIER_NEGATIVE_ACTTIVE.g, CFG.COLOR_TEXT_MODIFIER_NEGATIVE_ACTTIVE.b, 0.325F)
      );
      ImageManager.getImage(Images.slider_gradient)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() + this.getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
            this.getWidth() - ImageManager.getImage(Images.diplo_war).getWidth() / 2 - CFG.PADDING - CFG.CIV_FLAG_WIDTH,
            CFG.CIV_FLAG_HEIGHT,
            true,
            false
         );
      ImageManager.getImage(Images.slider_gradient)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() + this.getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
            this.getWidth() - ImageManager.getImage(Images.diplo_war).getWidth() / 2 - CFG.PADDING - CFG.CIV_FLAG_WIDTH,
            1,
            true,
            false
         );
      ImageManager.getImage(Images.slider_gradient)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY()
               + this.getHeight() / 2
               - CFG.CIV_FLAG_HEIGHT / 2
               - ImageManager.getImage(Images.line_32_off1).getHeight()
               + CFG.CIV_FLAG_HEIGHT
               - 1
               + iTranslateY,
            this.getWidth() - ImageManager.getImage(Images.diplo_war).getWidth() / 2 - CFG.PADDING - CFG.CIV_FLAG_WIDTH,
            1,
            true,
            false
         );
      if (isActive) {
         oSB.setColor(new Color(1.0F, 1.0F, 1.0F, 0.7F));
      } else if (this.getIsHovered()) {
         oSB.setColor(new Color(1.0F, 1.0F, 1.0F, 0.775F));
      } else {
         oSB.setColor(Color.WHITE);
      }

      ImageManager.getImage(Images.diplo_war)
         .draw(
            oSB,
            this.getPosX() + this.getWidth() - ImageManager.getImage(Images.diplo_war).getWidth() / 2 + iTranslateX,
            this.getPosY() + this.getHeight() / 2 - ImageManager.getImage(Images.research).getHeight() / 2 + iTranslateY
         );
      CFG.game
         .getCiv(this.iCivID)
         .getFlag()
         .draw(
            oSB,
            this.getPosX() + this.getWidth() - ImageManager.getImage(Images.diplo_war).getWidth() / 2 - CFG.PADDING - CFG.CIV_FLAG_WIDTH + iTranslateX,
            this.getPosY() + this.getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 - CFG.game.getCiv(this.iCivID).getFlag().getHeight() + iTranslateY,
            CFG.CIV_FLAG_WIDTH,
            CFG.CIV_FLAG_HEIGHT
         );
      ImageManager.getImage(Images.flag_rect)
         .draw(
            oSB,
            this.getPosX() + this.getWidth() - ImageManager.getImage(Images.diplo_war).getWidth() / 2 - CFG.PADDING - CFG.CIV_FLAG_WIDTH + iTranslateX,
            this.getPosY() + this.getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 + iTranslateY
         );
      oSB.setColor(Color.WHITE);
      CFG.fontMain.getData().setScale(0.7F);
      CFG.drawTextWithShadow(
         oSB,
         this.sText,
         this.getPosX()
            + this.getWidth()
            - ImageManager.getImage(Images.diplo_war).getWidth() / 2
            - CFG.PADDING
            - (int)(this.getTextWidth() * 0.7F)
            - CFG.CIV_FLAG_WIDTH
            - CFG.PADDING
            + iTranslateX,
         this.getPosY() + this.getHeight() / 2 - (int)(this.iTextHeight * 0.7F / 2.0F) + iTranslateY,
         isActive ? Color.WHITE : (this.getIsHovered() ? Color.WHITE : Color.WHITE)
      );
      CFG.fontMain.getData().setScale(1.0F);
   }

   @Override
   public Color getColor(boolean isActive) {
      return isActive
         ? CFG.COLOR_INGAME_GOLD
         : (this.getClickable() ? (this.getIsHovered() ? CFG.COLOR_INGAME_GOLD : CFG.COLOR_INGAME_GOLD) : new Color(0.78F, 0.78F, 0.78F, 0.7F));
   }

   @Override
   public void buildElementHover() {
      ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
      ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
      nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("WarPreparationsAgainst") + ":"));
      nData.add(new MenuElement_Hover_v2_Element_Type_Flag(this.iCivID, CFG.PADDING, CFG.PADDING));
      nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.game.getCiv(this.iCivID).getCivName(), CFG.COLOR_TEXT_NUM_OF_PROVINCES));
      nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.diplo_war_preparations, CFG.PADDING, 0));
      nElements.add(new MenuElement_Hover_v2_Element2(nData));
      nData.clear();
      this.menuElementHover = new MenuElement_Hover_v2(nElements);
   }

   @Override
   public void setCurrent(int nCurrent) {
      this.row = nCurrent == 0;
   }

   @Override
   public int getSFX() {
      return SoundsManager.SOUND_CLICK2;
   }

   @Override
   public void setText(String sText) {
      this.sText = sText;

      try {
         CFG.glyphLayout.setText(CFG.fontMain, sText);
         this.iTextWidth = (int)CFG.glyphLayout.width;
         this.iTextHeight = (int)CFG.glyphLayout.height;
         if (this.getHeight() < this.iTextHeight) {
            this.setHeight(this.iTextHeight);
         }
      } catch (NullPointerException var3) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var3);
         }
      } catch (IndexOutOfBoundsException var4) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var4);
         }
      }
   }

   @Override
   public void actionElement(int iID) {
      if (CFG.menuManager.getVisibleInGame_CasusBelli()) {
         CFG.menuManager.setVisibleInGame_CasusBelli(false);
      } else {
         CFG.toast.setInView(CFG.langManager.get("PreparationTime") + ": " + this.getText(), CFG.COLOR_TEXT_NUM_OF_PROVINCES);
         CFG.toast.setTimeInView(2000);
         CFG.menuManager
            .rebuildInGame_CasusBelli(
               CFG.game
                  .getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
                  .civGameData
                  .civPlans
                  .getCasusBelli_LeaderCivID(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()),
               this.iCivID,
               CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).civGameData.civPlans.getCasusBelliTime(this.iCivID)
            );
      }
   }
}
