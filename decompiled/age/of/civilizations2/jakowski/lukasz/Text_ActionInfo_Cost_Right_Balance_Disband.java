package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

public class Text_ActionInfo_Cost_Right_Balance_Disband extends Text_ActionInfo {
   public int iNumOfUnits = 0;
   public String sBalance;
   public int iBalanceWidth = 0;
   public int iBalance = 0;
   public Color oColorBalance = CFG.COLOR_TEXT_MODIFIER_POSITIVE;

   public Text_ActionInfo_Cost_Right_Balance_Disband(String sText, int iPosX, int iPosY) {
      super(sText, iPosX, iPosY);
      this.iBalance = (int)CFG.game_NextTurnUpdate.getIncome(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
         - (int)CFG.game_NextTurnUpdate.getExpenses(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
      this.setCurrent(0);
   }

   @Override
   public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
      oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.9F));
      int var6;
      ImageManager.getImage(Images.civ_name_bg)
         .draw2(
            oSB,
            this.getPosX() + (var6 = iTranslateX + (int)(this.getWidth() - this.getWidth() * CFG.fMOVE_MENU_PERCENTAGE / 100.0F)),
            this.getPosY() - ImageManager.getImage(Images.civ_name_bg).getHeight() + iTranslateY,
            this.getWidth(),
            this.getHeight() - ImageManager.getImage(Images.civ_name_bg).getHeight(),
            false,
            false
         );
      ImageManager.getImage(Images.civ_name_bg)
         .draw2(
            oSB,
            this.getPosX() + var6,
            this.getPosY()
               + this.getHeight()
               - ImageManager.getImage(Images.civ_name_bg).getHeight()
               - ImageManager.getImage(Images.civ_name_bg).getHeight()
               + iTranslateY,
            this.getWidth(),
            ImageManager.getImage(Images.civ_name_bg).getHeight(),
            false,
            true
         );
      oSB.setColor(new Color(CFG.COLOR_CREATE_NEW_GAME_BOX_PLAYERS.r, CFG.COLOR_CREATE_NEW_GAME_BOX_PLAYERS.g, CFG.COLOR_CREATE_NEW_GAME_BOX_PLAYERS.b, 0.75F));
      ImageManager.getImage(Images.civ_name_bg)
         .draw2(
            oSB,
            this.getPosX() + var6,
            this.getPosY() - 1 - ImageManager.getImage(Images.civ_name_bg).getHeight() + iTranslateY,
            this.getWidth(),
            1,
            false,
            false
         );
      ImageManager.getImage(Images.civ_name_bg)
         .draw2(
            oSB,
            this.getPosX() + var6,
            this.getPosY() + this.getHeight() - ImageManager.getImage(Images.civ_name_bg).getHeight() + iTranslateY,
            this.getWidth(),
            1,
            false,
            false
         );
      oSB.setColor(Color.WHITE);
      CFG.fontMain.getData().setScale(0.8F);
      CFG.drawTextWithShadow(
         oSB,
         this.sBalance,
         this.getPosX() + CFG.PADDING * 2 + (int)(this.iTextWidth * 0.8F) + var6,
         this.getPosY() + (int)((this.getHeight() - this.iTextHeight * 0.8F) / 2.0F) + iTranslateY,
         this.oColorBalance
      );
      CFG.drawTextWithShadow(
         oSB,
         this.sText,
         this.getPosX() + CFG.PADDING * 2 + var6,
         this.getPosY() + (int)((this.getHeight() - this.iTextHeight * 0.8F) / 2.0F) + iTranslateY,
         this.getColor(isActive)
      );
      CFG.fontMain.getData().setScale(1.0F);
      ImageManager.getImage(Images.top_gold)
         .draw(
            oSB,
            this.getPosX() + CFG.PADDING * 3 + (int)(this.iTextWidth * 0.8F) + this.iBalanceWidth + var6,
            this.getPosY()
               + (int)(
                     this.getHeight()
                        - ImageManager.getImage(Images.top_gold).getHeight() * this.getImageScale(ImageManager.getImage(Images.top_gold).getHeight())
                  )
                  / 2
               - ImageManager.getImage(Images.top_gold).getHeight(),
            (int)(ImageManager.getImage(Images.top_gold).getWidth() * this.getImageScale(ImageManager.getImage(Images.top_gold).getHeight())),
            (int)(ImageManager.getImage(Images.top_gold).getHeight() * this.getImageScale(ImageManager.getImage(Images.top_gold).getHeight()))
         );
   }

   public final float getImageScale(int nImageHeight) {
      return CFG.TEXT_HEIGHT * 0.8F / nImageHeight < 1.0F ? CFG.TEXT_HEIGHT * 0.8F / nImageHeight : 1.0F;
   }

   @Override
   public int getPosX() {
      return CFG.GAME_WIDTH - this.getWidth();
   }

   @Override
   public int getWidth() {
      return (int)(this.iTextWidth * 0.8F)
         + this.iBalanceWidth
         + CFG.PADDING * 4
         + (int)(ImageManager.getImage(Images.top_gold).getWidth() * this.getImageScale(ImageManager.getImage(Images.top_gold).getHeight()));
   }

   @Override
   public int getSFX() {
      return SoundsManager.SOUND_GOLD;
   }

   @Override
   public void setCurrent(int nCurrent) {
      this.iNumOfUnits = nCurrent;
      int tempNewBalance = (int)(
         this.iBalance
            - CFG.game_NextTurnUpdate
               .getMilitaryUpkeep_WithAllRecruitmentsInProcess_Disband(
                  CFG.game.getActiveProvinceID(), this.iNumOfUnits, CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()
               )
      );
      this.sBalance = CFG.getNumberWithSpaces("" + tempNewBalance);
      CFG.glyphLayout.setText(CFG.fontMain, this.sBalance);
      this.iBalanceWidth = (int)(CFG.glyphLayout.width * 0.8F);
      this.oColorBalance = tempNewBalance > 0
         ? CFG.COLOR_TEXT_MODIFIER_POSITIVE
         : (tempNewBalance == 0 ? CFG.COLOR_TEXT_MODIFIER_NEUTRAL : CFG.COLOR_TEXT_MODIFIER_NEGATIVE2);
   }

   @Override
   public void buildElementHover() {
      ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
      ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
      int tBalance = (int)CFG.game_NextTurnUpdate.getIncome(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
         - (int)CFG.game_NextTurnUpdate.getExpenses(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
      nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Balance") + ": "));
      nData.add(
         new MenuElement_Hover_v2_Element_Type_Text(
            CFG.getNumberWithSpaces("" + tBalance),
            tBalance > 0 ? CFG.COLOR_TEXT_MODIFIER_POSITIVE : (tBalance == 0 ? CFG.COLOR_TEXT_MODIFIER_NEUTRAL2 : CFG.COLOR_TEXT_MODIFIER_NEGATIVE2)
         )
      );
      nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_gold, CFG.PADDING, 0));
      nElements.add(new MenuElement_Hover_v2_Element2(nData));
      nData.clear();
      int nUpkeep = (int)CFG.game_NextTurnUpdate
         .getMilitaryUpkeep(CFG.game.getActiveProvinceID(), this.iNumOfUnits, CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
      nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("MilitaryUpkeep") + ": "));
      nData.add(
         new MenuElement_Hover_v2_Element_Type_Text(
            CFG.getNumberWithSpaces("" + nUpkeep),
            nUpkeep > 0 ? CFG.COLOR_TEXT_MODIFIER_NEGATIVE2 : (nUpkeep == 0 ? CFG.COLOR_TEXT_MODIFIER_NEUTRAL2 : CFG.COLOR_TEXT_MODIFIER_NEGATIVE2)
         )
      );
      nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_gold, CFG.PADDING, 0));
      nElements.add(new MenuElement_Hover_v2_Element2(nData));
      nData.clear();
      this.menuElementHover = new MenuElement_Hover_v2(nElements);
   }

   @Override
   public Color getColor(boolean isActive) {
      return isActive ? CFG.COLOR_TEXT_MODIFIER_NEUTRAL : (this.getClickable() ? Color.WHITE : new Color(0.78F, 0.78F, 0.78F, 0.7F));
   }

   @Override
   public void actionElement(int iID) {
      int tBalance = (int)CFG.game_NextTurnUpdate.getIncome(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
         - (int)CFG.game_NextTurnUpdate.getExpenses(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
      CFG.toast.setInView(CFG.langManager.get("Balance: ") + CFG.getNumberWithSpaces("" + tBalance), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE);
   }
}
