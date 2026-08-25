package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.utils.ScissorStack;
import java.util.ArrayList;

public class Menu_InGame_ArmyPriority extends SliderMenu {
   public static long lTime = 0L;

   public Menu_InGame_ArmyPriority() {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      int tempWidth = CFG.CIV_INFO_MENU_WIDTH * 2;
      int tY = 0;

      for (int i = 1; i < CFG.game.getCivsSize(); i++) {
         if (CFG.game.getCiv(i).getNumOfProvinces() > 0 && i != CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) {
            final int tCivID = i;
            menuElements.add(
               new Button_FlagActionSliderStyle(
                  (AI_Assistant.PRIORITY_COUNTRIES.contains(tCivID) ? "[X]  " : "[    ]  ") + CFG.game.getCiv(i).getCivName(),
                  -1,
                  2 + CFG.PADDING,
                  tY,
                  CFG.BUTTON_WIDTH * 2,
                  true
               ) {
                  @Override
                  public int getWidth() {
                     return Menu_InGame_ArmyPriority.this.getElementW() * 2 - CFG.PADDING * 2;
                  }

                  @Override
                  public void actionElement(int iID) {
                     if (AI_Assistant.PRIORITY_COUNTRIES.contains(tCivID)) {
                        AI_Assistant.PRIORITY_COUNTRIES.remove((Integer)tCivID);
                     } else {
                        AI_Assistant.PRIORITY_COUNTRIES.add(tCivID);
                     }

                     CFG.menuManager.rebuildInGame_ArmyPriority();
                  }

                  @Override
                  public void buildElementHover() {
                     ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
                     ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("ArmyPriority_Info"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                     nData.add(new MenuElement_Hover_v2_Element_Type_Flag(tCivID, CFG.PADDING, 0));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     this.menuElementHover = new MenuElement_Hover_v2(nElements);
                  }

                  @Override
                  public boolean getClickable() {
                     return true;
                  }

                  @Override
                  public int getSFX() {
                     return SoundsManager.getSend();
                  }
               }
            );
            tY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING / 2;
         }
      }

      if (menuElements.isEmpty()) {
         menuElements.add(new Button_Transparent(0, 0, tempWidth, CFG.BUTTON_HEIGHT, true));
      }

      int tempMenuPosY = ImageManager.getImage(Images.top_left2).getHeight() + CFG.PADDING * 2 + CFG.BUTTON_HEIGHT * 3 / 5;
      this.initMenu(
         new SliderMenuTitle(CFG.langManager.get("ArmyPriority"), CFG.BUTTON_HEIGHT * 3 / 5, true, true) {
            @Override
            public void draw(SpriteBatch oSB, int iTranslateX, int nPosX, int nPosY, int nWidth, boolean sliderMenuIsActive) {
               ImageManager.getImage(Images.dialog_title)
                  .draw2(
                     oSB,
                     nPosX - 2 + iTranslateX,
                     nPosY - this.getHeight() - ImageManager.getImage(Images.dialog_title).getHeight(),
                     nWidth + 4 - ImageManager.getImage(Images.dialog_title).getWidth(),
                     this.getHeight()
                  );
               ImageManager.getImage(Images.dialog_title)
                  .draw2(
                     oSB,
                     nPosX + nWidth + 2 - ImageManager.getImage(Images.dialog_title).getWidth() + iTranslateX,
                     nPosY - this.getHeight() - ImageManager.getImage(Images.dialog_title).getHeight(),
                     ImageManager.getImage(Images.dialog_title).getWidth(),
                     this.getHeight(),
                     true,
                     false
                  );
               oSB.setColor(new Color(0.3372549F, 0.34509805F, 0.6666667F, 0.165F));
               ImageManager.getImage(Images.line_32_off1)
                  .draw(
                     oSB,
                     nPosX + iTranslateX,
                     nPosY - this.getHeight() + 2 - ImageManager.getImage(Images.line_32_off1).getHeight(),
                     nWidth,
                     this.getHeight() - 2,
                     false,
                     true
                  );
               oSB.setColor(Color.WHITE);
               CFG.fontMain.getData().setScale(0.8F);
               CFG.drawText(
                  oSB,
                  this.getText(),
                  nPosX + (int)(nWidth - this.getTextWidth() * 0.8F) / 2 + iTranslateX,
                  2 + nPosY - this.getHeight() + (int)(this.getHeight() - this.getTextHeight() * 0.8F) / 2,
                  Color.WHITE
               );
               CFG.fontMain.getData().setScale(1.0F);
            }
         },
         CFG.GAME_WIDTH / 2 - tempWidth / 2,
         tempMenuPosY,
         tempWidth,
         Math.max((CFG.TEXT_HEIGHT + CFG.PADDING * 2) * 6, Math.min(tY + CFG.PADDING, CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT * 3)),
         menuElements,
         true,
         true
      );
      lTime = System.currentTimeMillis();
   }

   public final int getElementW() {
      return (this.getWidth() - 4) / 2;
   }

   @Override
   public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      if (lTime + 200L >= System.currentTimeMillis()) {
         Rectangle clipBounds = new Rectangle(
            this.getPosX() - 2,
            CFG.GAME_HEIGHT - this.getPosY(),
            this.getWidth() + 4,
            -((int)((this.getHeight() + CFG.PADDING) * ((float)(System.currentTimeMillis() - lTime) / 200.0F)))
         );
         oSB.flush();
         ScissorStack.pushScissors(clipBounds);
         oSB.setColor(Color.WHITE);
         this.drawMenu(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
         oSB.setColor(Color.WHITE);
         CFG.setRender_3(true);
         this.endClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
      } else {
         oSB.setColor(Color.WHITE);
         this.beginClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
         this.drawMenu(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
         oSB.setColor(Color.WHITE);
         this.endClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
      }
   }

   @Override
   public void setVisible(boolean visible) {
      super.setVisible(visible);
      if (!visible) {
         for (int i = 0; i < this.getMenuElementsSize(); i++) {
            this.getMenuElement(i).setVisible(false);
         }
      }
   }
}
