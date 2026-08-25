package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Text extends MenuElement {
   public String sText = null;
   public int iTextWidth = -1;
   public int iTextHeight = -1;
   public int iTextPositionX;
   public Text.TextPosition textPosition;

   public Text() {
   }

   public Text(String sText, int iPosX, int iPosY) {
      this.typeOfElement = MenuElement.TypeOfElement.TEXT;
      this.setPosX(iPosX);
      this.setPosY(iPosY);
      this.setHeight(CFG.TEXT_HEIGHT);
      this.setText(sText);
      this.textPosition = new Text.TextPosition() {
         @Override
         public int getTextPosition() {
            return 0;
         }
      };
   }

   public Text(String sText, int iTextPositionX, int iPosX, int iPosY, int iHeight) {
      this.typeOfElement = MenuElement.TypeOfElement.TEXT;
      this.iTextPositionX = iTextPositionX;
      this.setPosX(iPosX);
      this.setPosY(iPosY);
      this.setHeight(iHeight);
      this.setText(sText);
      this.updateTextPosition();
   }

   public Text(String sText, int iTextPositionX, int iPosX, int iPosY, int iWidth, int iHeight) {
      this.typeOfElement = MenuElement.TypeOfElement.TEXT;
      this.iTextPositionX = iTextPositionX;
      this.setPosX(iPosX);
      this.setPosY(iPosY);
      this.setWidth(iWidth);
      this.setHeight(iHeight);
      this.setText(sText);
      this.updateTextPosition();
   }

   public void updateTextPosition() {
      this.textPosition = this.iTextPositionX < 0 ? new Text.TextPosition() {
         @Override
         public int getTextPosition() {
            return Text.this.getWidth() / 2 - Text.this.iTextWidth / 2;
         }
      } : new Text.TextPosition() {
         @Override
         public int getTextPosition() {
            return Text.this.iTextPositionX;
         }
      };
   }

   @Override
   public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
      CFG.drawTextWithShadow(
         oSB,
         this.sText,
         this.getPosX() + this.textPosition.getTextPosition() + iTranslateX,
         this.getPosY() + (this.getHeight() - CFG.TEXT_HEIGHT) / 2 + iTranslateY,
         this.getColor(isActive)
      );
   }

   public Color getColor(boolean isActive) {
      return isActive
         ? new Color(0.56F, 0.56F, 0.56F, 1.0F)
         : (
            this.getClickable()
               ? (this.getIsHovered() ? new Color(0.68F, 0.68F, 0.68F, 1.0F) : new Color(0.82F, 0.82F, 0.82F, 1.0F))
               : new Color(0.78F, 0.78F, 0.78F, 0.7F)
         );
   }

   @Override
   public final String getText() {
      return this.sText;
   }

   @Override
   public void setText(String sText) {
      this.sText = sText;

      try {
         CFG.glyphLayout.setText(CFG.fontMain, sText);
         this.iTextWidth = (int)CFG.glyphLayout.width;
         this.iTextHeight = (int)CFG.glyphLayout.height;
         if (super.getWidth() < this.iTextWidth) {
            this.setWidth(this.iTextWidth);
         }

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
      } catch (IllegalArgumentException var5) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var5);
         }
      }
   }

   @Override
   public int getTextWidth() {
      return this.iTextWidth;
   }

   @Override
   public int getTextHeight() {
      return this.iTextHeight;
   }

   interface TextPosition {
      int getTextPosition();
   }
}
