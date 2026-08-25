package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.utils.ScissorStack;
import java.util.ArrayList;

public class Menu_InGame_BuildConfirm extends SliderMenu {
   public static final int ANIMATION_TIME = 225;
   public long lTime = 0L;

   public Menu_InGame_BuildConfirm() {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      int tempWidth = (int)(CFG.BUTTON_WIDTH * 4.75F);
      int tPosY = CFG.PADDING;
      if (tempWidth > CFG.GAME_WIDTH) {
         tempWidth = CFG.GAME_WIDTH - CFG.PADDING * 4;
      }

      if (BuildingsManager.ACTIVE_BUILDING == Buildings.FORT) {
         menuElements.add(new Text_Build("+20% Defense bonus", CFG.PADDING * 2, tPosY) {
            @Override
            public Color getColor(boolean isActive) {
               return CFG.COLOR_TEXT_MODIFIER_POSITIVE;
            }
         });
         int var4;
         menuElements.add(
            new Text_Build("+20% Defense bonus", CFG.PADDING * 2, var4 = tPosY + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING) {
               @Override
               public Color getColor(boolean isActive) {
                  return CFG.COLOR_TEXT_MODIFIER_NEGATIVE;
               }
            }
         );
         tPosY = var4 + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
      } else if (BuildingsManager.ACTIVE_BUILDING == Buildings.WATCH_TOWER) {
      }

      menuElements.add(
         new Button_NewGameStyle_Left(CFG.langManager.get("Cancel"), -1, CFG.PADDING, tPosY, tempWidth - CFG.PADDING * 2, CFG.BUTTON_HEIGHT / 2, true) {
            @Override
            public Color getColor(boolean isActive) {
               return isActive
                  ? CFG.COLOR_TEXT_CIV_INFO_ACTIVE
                  : (this.getIsHovered() ? CFG.COLOR_TEXT_CIV_INFO_HOVER : new Color(0.4509804F, 0.45882353F, 0.4745098F, 1.0F));
            }

            @Override
            public void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
               oSB.setColor(new Color(1.0F, 1.0F, 1.0F, this.getIsHovered() ? 0.75F : 0.5F));
               super.drawButtonBG(oSB, iTranslateX, iTranslateY, isActive);
               oSB.setColor(Color.WHITE);
            }

            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Ok"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }

            @Override
            public int getWidth() {
               return Menu_InGame_BuildConfirm.this.getW() / 2 - CFG.PADDING - CFG.PADDING / 2;
            }
         }
      );
      menuElements.add(
         new Button_NewGameStyle_Right(CFG.langManager.get("Build"), -1, CFG.PADDING, tPosY, tempWidth - CFG.PADDING * 2, CFG.BUTTON_HEIGHT / 2, true) {
            @Override
            public Color getColor(boolean isActive) {
               return isActive
                  ? CFG.COLOR_TEXT_CIV_INFO_ACTIVE
                  : (this.getIsHovered() ? CFG.COLOR_TEXT_CIV_INFO_HOVER : new Color(0.4509804F, 0.45882353F, 0.4745098F, 1.0F));
            }

            @Override
            public void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
               oSB.setColor(new Color(1.0F, 1.0F, 1.0F, this.getIsHovered() ? 0.75F : 0.5F));
               super.drawButtonBG(oSB, iTranslateX, iTranslateY, isActive);
               oSB.setColor(Color.WHITE);
            }

            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Ok"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }

            @Override
            public int getPosX() {
               return Menu_InGame_BuildConfirm.this.getW() / 2 + CFG.PADDING / 2;
            }

            @Override
            public int getWidth() {
               return Menu_InGame_BuildConfirm.this.getW() / 2 - CFG.PADDING;
            }
         }
      );
      int var5;
      this.initMenu(
         new SliderMenuTitle(CFG.langManager.get("Build"), CFG.BUTTON_HEIGHT * 3 / 5, true, true) {
            @Override
            public void draw(SpriteBatch oSB, int iTranslateX, int nPosX, int nPosY, int nWidth, boolean sliderMenuIsActive) {
               ImageManager.getImage(Images.new_game_top_edge_title)
                  .draw2(
                     oSB,
                     nPosX + iTranslateX,
                     nPosY - ImageManager.getImage(Images.new_game_top_edge_title).getHeight() - this.getHeight(),
                     nWidth - ImageManager.getImage(Images.new_game_top_edge_title).getWidth(),
                     this.getHeight()
                  );
               ImageManager.getImage(Images.new_game_top_edge_title)
                  .draw2(
                     oSB,
                     nPosX + nWidth - ImageManager.getImage(Images.new_game_top_edge_title).getWidth() + iTranslateX,
                     nPosY - ImageManager.getImage(Images.new_game_top_edge_title).getHeight() - this.getHeight(),
                     ImageManager.getImage(Images.new_game_top_edge_title).getWidth(),
                     this.getHeight(),
                     true
                  );
               oSB.setColor(new Color(0.09803922F, 0.05882353F, 0.37254903F, 0.55F));
               ImageManager.getImage(Images.gradient)
                  .draw(
                     oSB,
                     Menu_InGame_BuildConfirm.this.getPosX() + 2 + iTranslateX,
                     Menu_InGame_BuildConfirm.this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() - this.getHeight() * 3 / 5,
                     Menu_InGame_BuildConfirm.this.getWidth() - 4,
                     this.getHeight() * 3 / 5,
                     false,
                     true
                  );
               oSB.setColor(new Color(CFG.COLOR_GRADIENT_TITLE_BLUE));
               ImageManager.getImage(Images.gradient)
                  .draw(
                     oSB,
                     Menu_InGame_BuildConfirm.this.getPosX() + 2 + iTranslateX,
                     Menu_InGame_BuildConfirm.this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() - this.getHeight() / 4,
                     Menu_InGame_BuildConfirm.this.getWidth() - 4,
                     this.getHeight() / 4,
                     false,
                     true
                  );
               oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.4F));
               ImageManager.getImage(Images.gradient)
                  .draw(
                     oSB,
                     Menu_InGame_BuildConfirm.this.getPosX() + 2 + iTranslateX,
                     Menu_InGame_BuildConfirm.this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() - CFG.PADDING * 2,
                     Menu_InGame_BuildConfirm.this.getWidth() - 4,
                     CFG.PADDING * 2,
                     false,
                     true
                  );
               oSB.setColor(new Color(0.451F, 0.329F, 0.11F, 1.0F));
               ImageManager.getImage(Images.pix255_255_255)
                  .draw(
                     oSB,
                     Menu_InGame_BuildConfirm.this.getPosX() + 2 + iTranslateX,
                     Menu_InGame_BuildConfirm.this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight(),
                     Menu_InGame_BuildConfirm.this.getWidth() - 4
                  );
               oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.4F));
               ImageManager.getImage(Images.line_32_off1)
                  .draw(
                     oSB,
                     Menu_InGame_BuildConfirm.this.getPosX() + 2 + iTranslateX,
                     Menu_InGame_BuildConfirm.this.getPosY()
                        - ImageManager.getImage(Images.pix255_255_255).getHeight()
                        - ImageManager.getImage(Images.line_32_off1).getHeight(),
                     Menu_InGame_BuildConfirm.this.getWidth() - 4,
                     1
                  );
               oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.8F));
               ImageManager.getImage(Images.slider_gradient)
                  .draw(
                     oSB,
                     Menu_InGame_BuildConfirm.this.getPosX() + 2 + iTranslateX,
                     Menu_InGame_BuildConfirm.this.getPosY()
                        - ImageManager.getImage(Images.pix255_255_255).getHeight()
                        - ImageManager.getImage(Images.slider_gradient).getHeight(),
                     Menu_InGame_BuildConfirm.this.getWidth() / 4 - 4,
                     1
                  );
               ImageManager.getImage(Images.slider_gradient)
                  .draw(
                     oSB,
                     Menu_InGame_BuildConfirm.this.getPosX()
                        + 2
                        + Menu_InGame_BuildConfirm.this.getWidth()
                        - Menu_InGame_BuildConfirm.this.getWidth() / 4
                        + iTranslateX,
                     Menu_InGame_BuildConfirm.this.getPosY()
                        - ImageManager.getImage(Images.pix255_255_255).getHeight()
                        - ImageManager.getImage(Images.slider_gradient).getHeight(),
                     Menu_InGame_BuildConfirm.this.getWidth() / 4 - 4,
                     1,
                     true,
                     false
                  );
               oSB.setColor(Color.WHITE);
               CFG.fontMain.getData().setScale(0.8F);
               CFG.drawText(
                  oSB,
                  this.getText(),
                  nPosX + nWidth / 2 - (int)(this.getTextWidth() * 0.8F / 2.0F) + iTranslateX,
                  nPosY - this.getHeight() + this.getHeight() / 2 + 1 - (int)(this.getTextHeight() * 0.8F / 2.0F),
                  Color.WHITE
               );
               CFG.fontMain.getData().setScale(1.0F);
            }
         },
         CFG.GAME_WIDTH / 2 - tempWidth / 2,
         ImageManager.getImage(Images.top_left).getHeight() + CFG.PADDING * 2 + CFG.BUTTON_HEIGHT * 3 / 5,
         tempWidth,
         var5 = tPosY + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING,
         menuElements,
         false,
         true
      );
   }

   @Override
   public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      if (this.lTime + 225L >= System.currentTimeMillis()) {
         Rectangle clipBounds = new Rectangle(
            this.getPosX(),
            CFG.GAME_HEIGHT - this.getPosY(),
            this.getWidth(),
            -((int)(this.getHeight() * ((float)(System.currentTimeMillis() - this.lTime) / 225.0F)))
         );
         oSB.flush();
         ScissorStack.pushScissors(clipBounds);
         CFG.setRender_3(true);
      }

      ImageManager.getImage(Images.new_game_top_edge)
         .draw2(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.new_game_top_edge).getHeight() + iTranslateY,
            this.getWidth() - ImageManager.getImage(Images.new_game_top_edge).getWidth(),
            this.getHeight(),
            false,
            true
         );
      ImageManager.getImage(Images.new_game_top_edge)
         .draw2(
            oSB,
            this.getPosX() + this.getWidth() - ImageManager.getImage(Images.new_game_top_edge).getWidth() + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.new_game_top_edge).getHeight() + iTranslateY,
            ImageManager.getImage(Images.new_game_top_edge).getWidth(),
            this.getHeight(),
            true,
            true
         );
      this.beginClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
      this.drawMenu(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
      if (this.lTime + 225L >= System.currentTimeMillis()) {
         try {
            oSB.flush();
            ScissorStack.popScissors();
         } catch (IllegalStateException var6) {
         }
      }

      this.endClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
   }

   @Override
   public void drawScrollPos(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      if (sliderMenuIsActive) {
         super.drawScrollPos(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
      }
   }

   @Override
   public final void actionElement(int iID) {
      if (iID == this.getMenuElementsSize() - 1) {
         this.setVisible(false);
      }
   }

   @Override
   public void setVisible(boolean visible) {
      super.setVisible(visible);
      this.lTime = System.currentTimeMillis();
   }

   public final int getW() {
      return this.getWidth();
   }
}
