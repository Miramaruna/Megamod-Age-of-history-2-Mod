package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;
import java.util.List;

public class Toast {
   public static final int TIME_INVIEW_SHORT = 1500;
   public static final int TIME_INVIEW_STANDARD = 2000;
   public static final int TIME_INVIEW_LONG = 3000;
   public static final int TIME_INVIEW_VERY_LONG = 4500;
   public static final int TIME_INVIEW_VERY_VERY_LONG = 6000;
   public static final float TIME_START_OPACITY_PERCENTAGE = 0.4F;
   public boolean inView = false;
   public List<String> lMessage = new ArrayList<>();
   public List<Integer> lMessageWidth = new ArrayList<>();
   public List<Color> lMessageColor = new ArrayList<>();
   public int iMessagesSize = 0;
   public int iMaxWidth = 0;
   public int iTimeInView = 2000;
   public long lTime = 0L;
   public float fAlpha = 1.0F;
   public static float FONT_SCALE = 0.9F;

   public final void draw(SpriteBatch oSB) {
      if (this.lTime + this.iTimeInView < System.currentTimeMillis()) {
         this.inView = false;
      } else if (this.lTime + (int)(this.iTimeInView * 0.4F) < System.currentTimeMillis()) {
         this.fAlpha = CFG.getColorStep(
            255, 0, (int)(System.currentTimeMillis() - this.lTime - (int)(this.iTimeInView * 0.4F)), this.iTimeInView - (int)(this.iTimeInView * 0.4F)
         );
         if (this.fAlpha < 0.0F) {
            this.fAlpha = 0.0F;
         }
      }

      oSB.setColor(1.0F, 1.0F, 1.0F, this.fAlpha);
      ImageManager.getImage(Images.new_game_box)
         .draw2(
            oSB,
            this.getPosX() - CFG.PADDING * 3,
            this.getPosY()
               - (CFG.TEXT_HEIGHT + CFG.PADDING) * (this.iMessagesSize - 1)
               - CFG.PADDING * 2
               - ImageManager.getImage(Images.new_game_box).getHeight(),
            this.getWidth() + CFG.PADDING * 6 - ImageManager.getImage(Images.new_game_box).getWidth(),
            (int)Math.ceil((this.getHeight() + CFG.PADDING * 4 + (CFG.TEXT_HEIGHT + CFG.PADDING) * (this.iMessagesSize - 1)) / 2.0F)
         );
      ImageManager.getImage(Images.new_game_box)
         .draw2(
            oSB,
            this.getPosX() + this.getWidth() + CFG.PADDING * 3 - ImageManager.getImage(Images.new_game_box).getWidth(),
            this.getPosY()
               - (CFG.TEXT_HEIGHT + CFG.PADDING) * (this.iMessagesSize - 1)
               - CFG.PADDING * 2
               - ImageManager.getImage(Images.new_game_box).getHeight(),
            ImageManager.getImage(Images.new_game_box).getWidth(),
            (int)Math.ceil((this.getHeight() + CFG.PADDING * 4 + (CFG.TEXT_HEIGHT + CFG.PADDING) * (this.iMessagesSize - 1)) / 2.0F),
            true,
            false
         );
      ImageManager.getImage(Images.new_game_box)
         .draw2(
            oSB,
            this.getPosX() - CFG.PADDING * 3,
            this.getPosY()
               + this.getHeight()
               + CFG.PADDING * 2
               - (int)Math.floor((this.getHeight() + CFG.PADDING * 4 + (CFG.TEXT_HEIGHT + CFG.PADDING) * (this.iMessagesSize - 1)) / 2.0F)
               - ImageManager.getImage(Images.new_game_box).getHeight(),
            this.getWidth() + CFG.PADDING * 6 - ImageManager.getImage(Images.new_game_box).getWidth(),
            (int)Math.floor((this.getHeight() + CFG.PADDING * 4 + (CFG.TEXT_HEIGHT + CFG.PADDING) * (this.iMessagesSize - 1)) / 2.0F),
            false,
            true
         );
      ImageManager.getImage(Images.new_game_box)
         .draw2(
            oSB,
            this.getPosX() + this.getWidth() + CFG.PADDING * 3 - ImageManager.getImage(Images.new_game_box).getWidth(),
            this.getPosY()
               + this.getHeight()
               + CFG.PADDING * 2
               - (int)Math.floor((this.getHeight() + CFG.PADDING * 4 + (CFG.TEXT_HEIGHT + CFG.PADDING) * (this.iMessagesSize - 1)) / 2.0F)
               - ImageManager.getImage(Images.new_game_box).getHeight(),
            ImageManager.getImage(Images.new_game_box).getWidth(),
            (int)Math.floor((this.getHeight() + CFG.PADDING * 4 + (CFG.TEXT_HEIGHT + CFG.PADDING) * (this.iMessagesSize - 1)) / 2.0F),
            true,
            true
         );
      CFG.fontMain.getData().setScale(FONT_SCALE);

      for (int i = 0; i < this.iMessagesSize; i++) {
         try {
            CFG.drawTextWithShadow(
               oSB,
               this.lMessage.get(i),
               CFG.GAME_WIDTH / 2 - this.lMessageWidth.get(i) / 2,
               this.getPosY() - (CFG.TEXT_HEIGHT + CFG.PADDING) * (this.iMessagesSize - 1 - i),
               new Color(this.lMessageColor.get(i).r, this.lMessageColor.get(i).g, this.lMessageColor.get(i).b, this.fAlpha)
            );
         } catch (IndexOutOfBoundsException var4) {
            CFG.drawText(
               oSB,
               this.lMessage.get(i),
               CFG.GAME_WIDTH / 2 - this.lMessageWidth.get(i) / 2,
               this.getPosY() - (CFG.TEXT_HEIGHT + CFG.PADDING) * (this.iMessagesSize - 1 - i) + (int)((CFG.TEXT_HEIGHT - CFG.TEXT_HEIGHT * FONT_SCALE) / 2.0F),
               new Color(0.925F, 0.925F, 0.925F, this.fAlpha)
            );
         }
      }

      CFG.fontMain.getData().setScale(1.0F);
      oSB.setColor(Color.WHITE);
      CFG.setRender_3(true);
   }

   public final int getPosX() {
      return CFG.GAME_WIDTH / 2 - this.iMaxWidth / 2;
   }

   public final int getPosY() {
      return CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT / 2 - CFG.TEXT_HEIGHT;
   }

   public final int getWidth() {
      return this.iMaxWidth;
   }

   public final int getHeight() {
      return CFG.TEXT_HEIGHT;
   }

   public final boolean getInView() {
      return this.inView;
   }

   public final void setInView(boolean inView) {
      this.inView = inView;
   }

   public final void setInView(String sMessage) {
      this.lMessage.clear();
      this.lMessageWidth.clear();
      this.lMessageColor.clear();
      this.lMessage.add(sMessage);
      CFG.glyphLayout.setText(CFG.fontMain, sMessage);
      this.lMessageWidth.add((int)(CFG.glyphLayout.width * FONT_SCALE));
      this.iMaxWidth = this.lMessageWidth.get(0);
      this.iMessagesSize = this.lMessage.size();
      this.setInView();
   }

   public final void setInView(String sMessage, Color tColor) {
      try {
         this.lMessage.clear();
         this.lMessageWidth.clear();
         this.lMessageColor.clear();
         this.lMessage.add(sMessage);
         this.lMessageColor.add(tColor);

         try {
            CFG.glyphLayout.setText(CFG.fontMain, sMessage);
            this.lMessageWidth.add((int)(CFG.glyphLayout.width * FONT_SCALE));
         } catch (NullPointerException var6) {
            try {
               CFG.glyphLayout.setText(CFG.fontMain, sMessage);
               this.lMessageWidth.add((int)(CFG.glyphLayout.width * FONT_SCALE));
            } catch (NullPointerException var5) {
            }
         }

         this.iMaxWidth = this.lMessageWidth.get(0);
         this.iMessagesSize = this.lMessage.size();
         this.setInView();
      } catch (IndexOutOfBoundsException var7) {
      }
   }

   public final void setInView(List<String> nMessages) {
      this.lMessage.clear();
      this.lMessageWidth.clear();
      this.lMessageColor.clear();

      for (int i = 0; i < nMessages.size(); i++) {
         this.lMessage.add(nMessages.get(i));

         try {
            CFG.glyphLayout.setText(CFG.fontMain, nMessages.get(i));
            this.lMessageWidth.add((int)(CFG.glyphLayout.width * FONT_SCALE));
         } catch (NullPointerException var6) {
            try {
               CFG.glyphLayout.setText(CFG.fontMain, nMessages.get(i));
               this.lMessageWidth.add((int)(CFG.glyphLayout.width * FONT_SCALE));
            } catch (NullPointerException var5) {
            }
         }
      }

      this.iMaxWidth = 0;
      this.iMessagesSize = this.lMessage.size();

      for (int var7 = 0; var7 < this.iMessagesSize; var7++) {
         if (this.iMaxWidth < this.lMessageWidth.get(var7)) {
            this.iMaxWidth = this.lMessageWidth.get(var7);
         }
      }

      this.setInView();
   }

   public final void setInView(List<String> nMessages, List<Color> nColor) {
      this.lMessage.clear();
      this.lMessageWidth.clear();
      this.lMessageColor = nColor;

      for (int i = 0; i < nMessages.size(); i++) {
         this.lMessage.add(nMessages.get(i));

         try {
            CFG.glyphLayout.setText(CFG.fontMain, nMessages.get(i));
            this.lMessageWidth.add((int)(CFG.glyphLayout.width * FONT_SCALE));
         } catch (NullPointerException var7) {
            try {
               CFG.glyphLayout.setText(CFG.fontMain, nMessages.get(i));
               this.lMessageWidth.add((int)(CFG.glyphLayout.width * FONT_SCALE));
            } catch (NullPointerException var6) {
            }
         }
      }

      this.iMaxWidth = 0;
      this.iMessagesSize = this.lMessage.size();

      for (int var8 = 0; var8 < this.iMessagesSize; var8++) {
         if (this.iMaxWidth < this.lMessageWidth.get(var8)) {
            this.iMaxWidth = this.lMessageWidth.get(var8);
         }
      }

      this.setInView();
   }

   public final void setInView() {
      this.inView = true;
      this.fAlpha = 1.0F;
      this.lTime = System.currentTimeMillis();
      this.iTimeInView = 2000;
   }

   public final void setTimeInView(int iTimeInView) {
      this.iTimeInView = iTimeInView;
   }
}
