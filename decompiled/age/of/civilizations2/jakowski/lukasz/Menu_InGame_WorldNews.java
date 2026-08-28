package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

public class Menu_InGame_WorldNews extends SliderMenu {
   private int tPicID;

   public Menu_InGame_WorldNews() {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      int tempWidth = Math.min(720, CFG.GAME_WIDTH - CFG.PADDING * 4);
      int tPicH = CFG.TEXT_HEIGHT * 4;
      int tY = tPicH;

      menuElements.add(
         new Button_FlagActionSliderStyle(WorldNews.HEADER, -1, 2 + CFG.PADDING, tY, tempWidth - CFG.PADDING * 2, false) {
            @Override
            public Color getColor(boolean isActive) {
               return CFG.COLOR_INGAME_GOLD;
            }
         }
      );
      tY += menuElements.get(menuElements.size() - 1).getHeight();

      for (int i = 0; i < WorldNews.LINES.size(); i++) {
         final String tLine = WorldNews.LINES.get(i);
         menuElements.add(
            new Button_FlagActionSliderStyle(tLine, -1, 2 + CFG.PADDING * 2, tY, tempWidth - CFG.PADDING * 6, false) {
               @Override
               public Color getColor(boolean isActive) {
                  return tLine.startsWith("⚔") ? CFG.COLOR_TEXT_MODIFIER_NEGATIVE2 : CFG.COLOR_TEXT_CIV_INFO;
               }
            }
         );
         tY += menuElements.get(menuElements.size() - 1).getHeight();
      }

      tPicID = WorldNews.PIC;
      int varOk = tY + tPicH + CFG.PADDING * 2;
      menuElements.add(
         new Button_Game("ОК", -1, tempWidth / 2 - (CFG.BUTTON_WIDTH + CFG.BUTTON_WIDTH / 2) / 2, varOk,
            CFG.BUTTON_WIDTH + CFG.BUTTON_WIDTH / 2, true) {
            @Override
            public void actionElement(int iIDX) {
               this.setVisible(false);
            }
         }
      );

      int tempHeight = varOk + CFG.BUTTON_HEIGHT + CFG.PADDING * 2;
      this.initMenu(
         null,
         CFG.GAME_WIDTH / 2 - tempWidth / 2,
         CFG.GAME_HEIGHT / 2 - tempHeight / 2 + CFG.BUTTON_HEIGHT,
         tempWidth,
         tempHeight,
         menuElements,
         false,
         true
      );
   }

   public final int getW() {
      return this.getWidth() - CFG.PADDING * 4;
   }

   @Override
   public void actionElement(int iID) {
      if (iID >= 0 && iID < this.getMenuElementsSize()) {
         this.getMenuElement(iID).actionElement(iID);
      }
   }

   @Override
   public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      oSB.setColor(new Color(0.03F, 0.03F, 0.06F, 0.93F));
      ImageManager.getImage(Images.pix255_255_255)
         .draw(oSB, this.getPosX(), this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight(), this.getWidth(), this.getHeight());
      oSB.setColor(CFG.COLOR_NEW_GAME_EDGE_LINE);
      ImageManager.getImage(Images.pix255_255_255)
         .draw(oSB, this.getPosX(), this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight(), this.getWidth(), 1);

      float tScale = CFG.map != null ? CFG.map.getMapScale().getCurrentScale() : 1.0F;
      int tIconSize = CFG.TEXT_HEIGHT * 3;
      int tX = this.getPosX() + this.getWidth() / 2 - tIconSize / 2;
      int tY = this.getPosY() - CFG.PADDING - tIconSize;
      oSB.setColor(new Color(1.0F, 0.85F, 0.4F, 0.9F));
      Image tPic = ImageManager.getImage(tPicID);
      if (tPic != null && tPic.texture != null) {
         tPic.draw(oSB, tX, tY, tIconSize, tIconSize);
      }
      oSB.setColor(Color.WHITE);

      super.draw(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
   }
}
