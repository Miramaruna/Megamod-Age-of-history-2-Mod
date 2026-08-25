package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.utils.ScissorStack;
import java.util.ArrayList;
import java.util.List;

public class Graph_Vertical_Info {
   public List<String> lTexts = null;
   public int iTextsSize = 0;
   public List<Integer> lTextWidths = null;
   public List<Color> lColors = null;
   public boolean moveable = false;
   public boolean moveRight = false;
   public int iTextWidth = 0;
   public int iTextPosX = 0;
   public long lTime = 0L;
   public List<Integer> lSortedIDs = null;

   public Graph_Vertical_Info(List<String> nTexts, List<Color> nColors, int iWidth, boolean nSortText) {
      this.iTextsSize = nTexts.size();
      this.lTexts = new ArrayList<>();
      this.lColors = new ArrayList<>();
      this.lSortedIDs = new ArrayList<>();
      ArrayList<Boolean> tempAdded = new ArrayList<>();

      for (int i = 0; i < this.iTextsSize; i++) {
         this.lSortedIDs.add(i);
         tempAdded.add(false);
      }

      if (!nSortText) {
         this.lTexts = nTexts;
         this.lColors = nColors;
      } else {
         while (nTexts.size() != this.lTexts.size()) {
            int nMinID = 0;

            for (int i2 = 0; i2 < this.iTextsSize; i2++) {
               if (!tempAdded.get(i2)) {
                  nMinID = i2;
                  break;
               }
            }

            for (int var10 = nMinID + 1; var10 < this.iTextsSize; var10++) {
               if (!tempAdded.get(var10) && CFG.compareAlphabetic_TwoString(nTexts.get(nMinID), nTexts.get(var10))) {
                  nMinID = var10;
               }
            }

            this.lTexts.add(nTexts.get(nMinID));
            this.lColors.add(nColors.get(nMinID));
            tempAdded.set(nMinID, true);
            this.lSortedIDs.set(nMinID, this.lTexts.size() - 1);
         }
      }

      this.lTextWidths = new ArrayList<>();
      CFG.fontMain.getData().setScale(0.7F);

      for (int var9 = 0; var9 < this.iTextsSize; var9++) {
         CFG.glyphLayout.setText(CFG.fontMain, this.lTexts.get(var9));
         this.iTextWidth = this.iTextWidth + (int)CFG.glyphLayout.width;
         this.lTextWidths.add((int)CFG.glyphLayout.width);
      }

      CFG.fontMain.getData().setScale(1.0F);
      this.iTextWidth = this.iTextWidth + CFG.PADDING * this.iTextsSize + CFG.PADDING * (this.iTextsSize - 1) + (int)(CFG.TEXT_HEIGHT * 0.7F * this.iTextsSize);
      this.updateMoveable(iWidth);
   }

   public final void updateMoveable(int iWidth) {
      if (this.iTextWidth > iWidth) {
         this.moveable = true;
         this.resetMoveable();
      } else {
         this.resetMoveable();
         this.moveable = false;
         this.iTextPosX = iWidth / 2 - this.iTextWidth / 2;
      }
   }

   public final void draw(SpriteBatch oSB, int nPosX, int nPosY, int nWidth) {
      if (this.moveable) {
         Rectangle clipBounds = new Rectangle(nPosX, CFG.GAME_HEIGHT - nPosY, nWidth, -((int)(CFG.TEXT_HEIGHT * 0.7F)) - CFG.PADDING);
         oSB.flush();
         ScissorStack.pushScissors(clipBounds);
         if (this.lTime < System.currentTimeMillis() - 45L) {
            this.lTime = System.currentTimeMillis();
            if (this.moveRight) {
               this.iTextPosX--;
               if (-this.iTextPosX + nWidth >= this.iTextWidth + CFG.PADDING) {
                  this.moveRight = !this.moveRight;
               }

               CFG.setRender_3(true);
            } else {
               this.iTextPosX++;
               if (this.iTextPosX >= 0) {
                  this.moveRight = !this.moveRight;
               }

               CFG.setRender_3(true);
            }
         } else {
            CFG.setRender_3(true);
         }
      }

      int tempOffsetX = 0;

      for (int i = 0; i < this.iTextsSize; i++) {
         oSB.setColor(this.lColors.get(i));
         ImageManager.getImage(Images.pix255_255_255)
            .draw(
               oSB,
               nPosX + tempOffsetX + this.iTextPosX,
               nPosY - ImageManager.getImage(Images.pix255_255_255).getHeight(),
               (int)(CFG.TEXT_HEIGHT * 0.7F),
               (int)(CFG.TEXT_HEIGHT * 0.7F)
            );
         oSB.setColor(new Color(this.lColors.get(i).r, this.lColors.get(i).g, this.lColors.get(i).b, 0.7F));
         ImageManager.getImage(Images.gradient)
            .draw(
               oSB,
               nPosX + tempOffsetX + this.iTextPosX,
               nPosY - ImageManager.getImage(Images.gradient).getHeight(),
               (int)(CFG.TEXT_HEIGHT * 0.7F),
               (int)(CFG.TEXT_HEIGHT * 0.7F)
            );
         int var9;
         CFG.drawTextWithShadow(
            oSB,
            this.lTexts.get(i),
            nPosX + (var9 = tempOffsetX + (int)(CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING) + this.iTextPosX,
            nPosY,
            new Color(this.lColors.get(i).r, this.lColors.get(i).g, this.lColors.get(i).b, 0.7F)
         );
         tempOffsetX = var9 + this.lTextWidths.get(i) + CFG.PADDING;
      }

      if (this.moveable) {
         try {
            oSB.flush();
            ScissorStack.popScissors();
         } catch (IllegalStateException var7) {
         }
      }
   }

   public final void resetMoveable() {
      this.iTextPosX = 0;
      this.moveRight = true;
   }

   public final int getTextSize() {
      return this.iTextsSize;
   }

   public final String getText(int i) {
      return this.lTexts.get(i);
   }

   public final int getSortedID(int i) {
      return this.lSortedIDs.get(i);
   }

   public final List<Color> getColors() {
      return this.lColors;
   }
}
