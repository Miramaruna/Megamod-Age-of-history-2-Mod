package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Button_Chatbox extends Button {
   public String sExtraMessage;
   public int iMenuElementID;

   public Button_Chatbox(
      String nExtramessage, int nElementID, String sText, int iTextPositionX, int iPosX, int iPosY, int iWidth, int iHeight, boolean isClickable
   ) {
      super.init(sText, iTextPositionX, iPosX, iPosY, iWidth, iHeight, isClickable, true, false, false, null);
      this.iMenuElementID = nElementID;
      this.sExtraMessage = nExtramessage;
   }

   @Override
   public void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
      oSB.setColor(new Color(0.023F, 0.023F, 0.023F, 0.45F));
      ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - 1 + iTranslateY, this.getWidth(), this.getHeight());
      oSB.setColor(new Color(CFG.COLOR_INFO_BOX_GRADIENT.r, CFG.COLOR_INFO_BOX_GRADIENT.g, CFG.COLOR_INFO_BOX_GRADIENT.b, this.getIsHovered() ? 0.6F : 0.425F));
      ImageManager.getImage(Images.gradient)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY,
            this.getWidth(),
            this.getHeight(),
            false,
            true
         );
      oSB.setColor(CFG.COLOR_CREATE_NEW_GAME_BOX_PLAYERS);
      CFG.drawRect(oSB, this.getPosX() + iTranslateX, this.getPosY() - 1 + iTranslateY, this.getWidth(), this.getHeight());
      oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.65F));
      CFG.drawRect(oSB, this.getPosX() + 1 + iTranslateX, this.getPosY() + iTranslateY, this.getWidth() - 2, this.getHeight() - 2);
      oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.325F));
      ImageManager.getImage(Images.line_32_off1)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() + this.getHeight() - 1 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
            this.getWidth(),
            1
         );
      oSB.setColor(Color.WHITE);
   }

   @Override
   public void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
      CFG.fontMain.getData().setScale(0.75F);
      if (!CFG.menuManager.getKeyboard().getVisible() || CFG.menuManager.getKeyboardActiveMenuElementID() != this.iMenuElementID) {
         CFG.drawText(
            oSB,
            this.sExtraMessage,
            this.getPosX() + this.getTextPos() + iTranslateX,
            this.getPosY() + this.getHeight() / 2 - (int)(this.getTextHeight() * 0.75F / 2.0F) + iTranslateY,
            this.getColor(isActive)
         );
      } else if (isActive) {
         CFG.drawText(
            oSB,
            CFG.keyboardMessage,
            this.getPosX() + this.getTextPos() + iTranslateX,
            this.getPosY() + this.getHeight() / 2 - (int)(this.getTextHeight() * 0.75F / 2.0F) + iTranslateY,
            this.getColor(isActive)
         );
      } else {
         CFG.drawText(
            oSB,
            CFG.keyboardMessage,
            this.getPosX() + this.getTextPos() + iTranslateX,
            this.getPosY() + this.getHeight() / 2 - (int)(this.getTextHeight() * 0.75F / 2.0F) + iTranslateY,
            this.getColor(isActive)
         );
      }

      CFG.fontMain.getData().setScale(1.0F);
   }

   @Override
   public Color getColor(boolean isActive) {
      return isActive
         ? CFG.COLOR_TEXT_OPTIONS_LEFT_NS_ACTIVE
         : (
            this.getClickable()
               ? (this.getIsHovered() ? CFG.COLOR_TEXT_OPTIONS_LEFT_NS_ACTIVE : CFG.COLOR_TEXT_OPTIONS_LEFT_NS_HOVER)
               : new Color(0.78F, 0.78F, 0.78F, 0.7F)
         );
   }
}
