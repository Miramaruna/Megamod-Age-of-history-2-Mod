package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Text_ActionInfo_Cost extends Text_ActionInfo {
   public Text_ActionInfo_Cost(String sText, int iPosX, int iPosY) {
      super(sText, iPosX, iPosY);
   }

   @Override
   public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
      oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.9F));
      int var6;
      ImageManager.getImage(Images.civ_name_bg)
         .draw2(
            oSB,
            this.getPosX() + (var6 = iTranslateX - (int)(this.getWidth() - this.getWidth() * CFG.fMOVE_MENU_PERCENTAGE / 100.0F)),
            this.getPosY() - ImageManager.getImage(Images.civ_name_bg).getHeight() + iTranslateY,
            (int)(this.getWidth() * 0.8F)
               + CFG.PADDING * 4
               + (int)(ImageManager.getImage(Images.top_gold).getWidth() * this.getImageScale(ImageManager.getImage(Images.top_gold).getHeight())),
            this.getHeight() - ImageManager.getImage(Images.civ_name_bg).getHeight(),
            true,
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
            (int)(this.getWidth() * 0.8F)
               + CFG.PADDING * 4
               + (int)(ImageManager.getImage(Images.top_gold).getWidth() * this.getImageScale(ImageManager.getImage(Images.top_gold).getHeight())),
            ImageManager.getImage(Images.civ_name_bg).getHeight(),
            true,
            true
         );
      oSB.setColor(new Color(CFG.COLOR_CREATE_NEW_GAME_BOX_PLAYERS.r, CFG.COLOR_CREATE_NEW_GAME_BOX_PLAYERS.g, CFG.COLOR_CREATE_NEW_GAME_BOX_PLAYERS.b, 0.75F));
      ImageManager.getImage(Images.civ_name_bg)
         .draw2(
            oSB,
            this.getPosX() + var6,
            this.getPosY() - 1 - ImageManager.getImage(Images.civ_name_bg).getHeight() + iTranslateY,
            (int)(this.getWidth() * 0.8F)
               + CFG.PADDING * 4
               + (int)(ImageManager.getImage(Images.top_gold).getWidth() * this.getImageScale(ImageManager.getImage(Images.top_gold).getHeight())),
            1,
            true,
            false
         );
      ImageManager.getImage(Images.civ_name_bg)
         .draw2(
            oSB,
            this.getPosX() + var6,
            this.getPosY() + this.getHeight() - ImageManager.getImage(Images.civ_name_bg).getHeight() + iTranslateY,
            (int)(this.getWidth() * 0.8F)
               + CFG.PADDING * 4
               + (int)(ImageManager.getImage(Images.top_gold).getWidth() * this.getImageScale(ImageManager.getImage(Images.top_gold).getHeight())),
            1,
            true,
            false
         );
      oSB.setColor(Color.WHITE);
      CFG.fontMain.getData().setScale(0.8F);
      CFG.drawTextWithShadow(
         oSB,
         this.sText,
         this.getPosX() + this.textPosition.getTextPosition() + var6,
         this.getPosY() + (int)((this.getHeight() - this.iTextHeight * 0.8F) / 2.0F) + iTranslateY,
         this.getColor(isActive)
      );
      CFG.fontMain.getData().setScale(1.0F);
      ImageManager.getImage(Images.top_gold)
         .draw(
            oSB,
            this.getPosX() + (int)(this.getWidth() * 0.8F) + CFG.PADDING + this.textPosition.getTextPosition() + var6,
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
}
