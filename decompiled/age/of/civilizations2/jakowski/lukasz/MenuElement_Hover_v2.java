package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.List;

public class MenuElement_Hover_v2 implements MenuElement_Hover {
   public static final float TEXT_SCALE = 0.75F;
   public List<MenuElement_Hover_v2_Element2> lElements;
   public int iElementsSize = 0;
   public static long ANIMATION_TIME;
   public static float ANIMATION_ALPHA;
   public static float ANIMATION_PADDING;
   public static int ANIMATION_INTERVAL = 2450;
   public int iHeight = 0;
   public int iWidth = 0;
   public int iWidthOver = 0;
   public int iScrollPosX = 0;
   public boolean backAnimation = true;
   public long lTime = 0L;

   public static final void resetAnimation() {
      ANIMATION_TIME = System.currentTimeMillis();
      ANIMATION_ALPHA = 0.01F;
      ANIMATION_PADDING = CFG.PADDING;
   }

   public static final void resetAnimation_2() {
      ANIMATION_TIME = System.currentTimeMillis();
      ANIMATION_ALPHA = 0.3F;
      ANIMATION_PADDING = CFG.PADDING;
   }

   public MenuElement_Hover_v2(List<MenuElement_Hover_v2_Element2> nElements) {
      this.lElements = nElements;
      this.iElementsSize = this.lElements.size();
      this.iWidth = 0;

      for (int i = 0; i < this.iElementsSize; i++) {
         if (this.lElements.get(i).getWidth() > CFG.GAME_WIDTH - CFG.PADDING * 2
            && this.lElements.get(i).getWidth() - CFG.GAME_WIDTH - CFG.PADDING * 2 > this.iWidthOver) {
            this.iWidthOver = this.lElements.get(i).getWidth() - CFG.GAME_WIDTH - CFG.PADDING * 2;
         }
      }

      if (this.iWidthOver > 0) {
         this.iScrollPosX = this.iWidthOver + CFG.PADDING * 10;
         this.lTime = System.currentTimeMillis();
      }

      for (int var3 = 0; var3 < this.iElementsSize; var3++) {
         if (this.lElements.get(var3).getWidth() > this.iWidth) {
            this.iWidth = this.lElements.get(var3).getWidth();
         }
      }

      this.iWidth = this.iWidth + CFG.PADDING * 6;
      this.iHeight = CFG.TEXT_HEIGHT * this.iElementsSize + CFG.PADDING * (this.iElementsSize - 1) + CFG.PADDING * 4;
   }

   @Override
   public final void draw(SpriteBatch oSB, int nPosX, int nPosY) {
      if ((nPosX = (int)(nPosX + ANIMATION_PADDING)) + this.iWidth > CFG.GAME_WIDTH - CFG.PADDING) {
         nPosX = CFG.GAME_WIDTH - this.iWidth - CFG.PADDING;
      }

      if (nPosY < 0) {
         nPosY = CFG.PADDING;
      } else if (nPosY + this.iHeight > CFG.GAME_HEIGHT) {
         nPosY = CFG.GAME_HEIGHT - this.iHeight - CFG.PADDING;
      }

      this.draw_Hover(oSB, nPosX, nPosY);
   }

   @Override
   public final void drawAlwaysOver(SpriteBatch oSB, int nPosX, int nPosY) {
      nPosX = (int)(nPosX + ANIMATION_PADDING);
      nPosY = nPosY - this.iHeight - CFG.PADDING;
      if ((nPosX = nPosX + CFG.PADDING) + this.iWidth > CFG.GAME_WIDTH - CFG.PADDING) {
         nPosX = CFG.GAME_WIDTH - this.iWidth - CFG.PADDING;
      }

      if (nPosY < 0) {
         nPosY = CFG.PADDING;
      } else if (nPosY + this.iHeight > CFG.GAME_HEIGHT) {
         nPosY = CFG.GAME_HEIGHT - this.iHeight - CFG.PADDING;
      }

      this.draw_Hover(oSB, nPosX, nPosY);
   }

   @Override
   public final void drawAlwaysOver_Mobile(SpriteBatch oSB, int nPosX, int nPosY) {
      nPosX = (int)(nPosX + ANIMATION_PADDING);
      nPosY = nPosY - this.iHeight - CFG.PADDING * 4;
      if ((nPosX = nPosX - this.iWidth / 4) < CFG.PADDING) {
         nPosX = CFG.PADDING;
      }

      if (nPosX + this.iWidth > CFG.GAME_WIDTH - CFG.PADDING) {
         nPosX = CFG.GAME_WIDTH - this.iWidth - CFG.PADDING;
      }

      if (nPosY < 0) {
         nPosY = CFG.PADDING;
      } else if (nPosY + this.iHeight > CFG.GAME_HEIGHT) {
         nPosY = CFG.GAME_HEIGHT - this.iHeight - CFG.PADDING;
      }

      this.draw_Hover(oSB, nPosX, nPosY);
   }

   @Override
   public final void drawAlwaysBelow(SpriteBatch oSB, int nPosX, int nPosY) {
      nPosX = (int)(nPosX + ANIMATION_PADDING);
      nPosY += CFG.PADDING;
      if ((nPosX = nPosX + CFG.PADDING) + this.iWidth > CFG.GAME_WIDTH - CFG.PADDING) {
         nPosX = CFG.GAME_WIDTH - this.iWidth - CFG.PADDING;
      }

      if (nPosY < 0) {
         nPosY = CFG.PADDING;
      } else if (nPosY + this.iHeight > CFG.GAME_HEIGHT) {
         nPosY = CFG.GAME_HEIGHT - this.iHeight - CFG.PADDING;
      }

      this.draw_Hover(oSB, nPosX, nPosY);
   }

   @Override
   public final void drawProvinceInfo(SpriteBatch oSB, int nPosX, int nPosY) {
      nPosX = (int)(nPosX + ANIMATION_PADDING);
      this.draw_Hover(oSB, nPosX, nPosY);
   }

   public final int getScrollPosX() {
      if (this.iWidthOver > 0) {
         if (this.backAnimation) {
            if (this.lTime + 1500L < System.currentTimeMillis() && this.iScrollPosX-- < -CFG.PADDING) {
               this.backAnimation = !this.backAnimation;
               this.lTime = System.currentTimeMillis();
            }
         } else if (this.lTime + 1000L < System.currentTimeMillis() && this.iScrollPosX++ > this.iWidthOver + CFG.PADDING * 10) {
            this.backAnimation = !this.backAnimation;
            this.lTime = System.currentTimeMillis();
         }

         CFG.setRender_3(true);
         return this.iScrollPosX;
      } else {
         return 0;
      }
   }

   @Override
   public final void draw_Hover(SpriteBatch oSB, int nPosX, int nPosY) {
      int tempScrollX = this.getScrollPosX();
      oSB.setColor(new Color(1.0F, 1.0F, 1.0F, ANIMATION_ALPHA));
      CFG.drawRect_NewGameBox(oSB, nPosX + tempScrollX, nPosY, this.iWidth, this.iHeight);
      oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.15F * ANIMATION_ALPHA));
      ImageManager.getImage(Images.line_32_off1)
         .draw(oSB, nPosX + tempScrollX, nPosY + 1 - ImageManager.getImage(Images.line_32_off1).getHeight(), this.iWidth, 1);
      ImageManager.getImage(Images.line_32_off1)
         .draw(oSB, nPosX + tempScrollX, nPosY + this.iHeight - 2 - ImageManager.getImage(Images.line_32_off1).getHeight(), this.iWidth, 1);
      oSB.setColor(new Color(1.0F, 1.0F, 1.0F, ANIMATION_ALPHA));
      CFG.fontMain.getData().setScale(0.75F);

      for (int i = 0; i < this.iElementsSize; i++) {
         this.lElements.get(i).draw(oSB, nPosX + tempScrollX + CFG.PADDING * 3, nPosY + CFG.PADDING + CFG.TEXT_HEIGHT * i + CFG.PADDING * i, ANIMATION_ALPHA);
      }

      CFG.fontMain.getData().setScale(1.0F);
      oSB.setColor(Color.WHITE);
   }

   @Override
   public final void draw_HoverWithoutAnimation(SpriteBatch oSB, int nPosX, int nPosY) {
      int tempScrollX = this.getScrollPosX();
      if (nPosY + this.iHeight > CFG.GAME_HEIGHT - CFG.PADDING * 2) {
         nPosY = CFG.GAME_HEIGHT - CFG.PADDING * 2 - this.iHeight;
      }

      oSB.setColor(new Color(1.0F, 1.0F, 1.0F, 1.0F));
      CFG.drawRect_NewGameBox_EDGE(oSB, nPosX + tempScrollX, nPosY, this.iWidth, this.iHeight);
      oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.15F));
      ImageManager.getImage(Images.line_32_off1)
         .draw(oSB, nPosX + tempScrollX, nPosY + 1 - ImageManager.getImage(Images.line_32_off1).getHeight(), this.iWidth, 1);
      ImageManager.getImage(Images.line_32_off1)
         .draw(oSB, nPosX + tempScrollX, nPosY + this.iHeight - 2 - ImageManager.getImage(Images.line_32_off1).getHeight(), this.iWidth, 1);
      oSB.setColor(new Color(1.0F, 1.0F, 1.0F, 1.0F));
      CFG.fontMain.getData().setScale(0.75F);

      for (int i = 0; i < this.iElementsSize; i++) {
         this.lElements.get(i).draw(oSB, nPosX + tempScrollX + CFG.PADDING * 3, nPosY + CFG.PADDING + CFG.TEXT_HEIGHT * i + CFG.PADDING * i, 1.0F);
      }

      CFG.fontMain.getData().setScale(1.0F);
      oSB.setColor(Color.WHITE);
   }
}
