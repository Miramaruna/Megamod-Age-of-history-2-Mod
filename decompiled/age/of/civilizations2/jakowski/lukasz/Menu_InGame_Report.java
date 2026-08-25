package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.utils.ScissorStack;
import java.util.ArrayList;

public class Menu_InGame_Report extends SliderMenu {
   public final float TITILE_GRADIENT_ALPHA = 0.2F;
   public static final int ANIMATION_TIME = 225;
   public long lTime = 0L;

   public Menu_InGame_Report() {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      int tempWidth = (int)(CFG.BUTTON_WIDTH * 6.5F);
      int tempHeight = CFG.BUTTON_HEIGHT * 4 + CFG.BUTTON_HEIGHT * 3 / 4;
      if (tempWidth > CFG.GAME_WIDTH) {
         tempWidth = CFG.GAME_WIDTH - CFG.PADDING * 4;
      }

      CFG.reportData.checkReport();
      menuElements.add(new Button_Report_Units(2, 0, tempWidth - 4, CFG.BUTTON_HEIGHT / 3) {
         @Override
         public int getWidth() {
            return Menu_InGame_Report.this.getW() - 4;
         }
      });
      menuElements.add(
         new Button_Report_ProvinceLosses(
            CFG.PADDING * 2,
            menuElements.get(0).getHeight() + CFG.PADDING * 2,
            tempWidth - CFG.PADDING * 4,
            CFG.reportData.iPopulationLosses,
            CFG.reportData.iEconomyLosses
         ) {
            @Override
            public int getWidth() {
               return Menu_InGame_Report.this.getW() - CFG.PADDING * 4;
            }
         }
      );
      int tH = 0;

      for (int i = 0; i < CFG.reportData.lAttackers_IDs.size(); i++) {
         menuElements.add(
            new Button_Report_Armies(
               CFG.PADDING * 2,
               menuElements.get(1).getPosY() + menuElements.get(1).getHeight() + CFG.PADDING + tH,
               (tempWidth - CFG.PADDING * 6) / 2,
               CFG.reportData.lAttackers_IDs.get(i),
               CFG.reportData.lAttackers_Armies.get(i),
               CFG.reportData.lAttackers_Armies_Lost.get(i),
               CFG.reportData.iMilitaryAttackPoints
            ) {
               @Override
               public int getWidth() {
                  return (Menu_InGame_Report.this.getW() - CFG.PADDING * 6) / 2;
               }
            }
         );
         tH += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
      }

      tH = 0;

      for (int var8 = 0; var8 < CFG.reportData.lDefenders_IDs.size(); var8++) {
         menuElements.add(
            new Button_Report_Armies_Right(
               tempWidth - CFG.PADDING * 2 - (tempWidth - CFG.PADDING * 6) / 2,
               menuElements.get(1).getPosY() + menuElements.get(1).getHeight() + CFG.PADDING + tH,
               (tempWidth - CFG.PADDING * 6) / 2,
               CFG.reportData.lDefenders_IDs.get(var8),
               CFG.reportData.lDefenders_Armies.get(var8),
               CFG.reportData.lDefenders_ArmiesLost.get(var8),
               CFG.reportData.iMilitaryDefendersPoints
            ) {
               @Override
               public int getPosX() {
                  return Menu_InGame_Report.this.getW() - CFG.PADDING * 2 - (Menu_InGame_Report.this.getW() - CFG.PADDING * 6) / 2;
               }

               @Override
               public int getWidth() {
                  return (Menu_InGame_Report.this.getW() - CFG.PADDING * 6) / 2;
               }
            }
         );
         tH += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
      }

      int tempMaxH = 0;

      for (int i2 = 0; i2 < menuElements.size(); i2++) {
         if (menuElements.get(i2).getPosY() + menuElements.get(i2).getHeight() > tempMaxH) {
            tempMaxH = menuElements.get(i2).getPosY() + menuElements.get(i2).getHeight();
         }
      }

      menuElements.add(
         new Button_New_Game_Players_Report(
            CFG.reportData.attackersWon ? CFG.reportData.lAttackers_IDs.get(0) : CFG.reportData.lDefenders_IDs.get(0),
            CFG.langManager
                  .get(
                     "IsVictorious",
                     CFG.game.getCiv(CFG.reportData.attackersWon ? CFG.reportData.lAttackers_IDs.get(0) : CFG.reportData.lDefenders_IDs.get(0)).getCivName()
                  )
               + "!",
            -1,
            CFG.PADDING,
            tempMaxH + CFG.PADDING * 2,
            tempWidth - CFG.PADDING * 2,
            CFG.BUTTON_HEIGHT / 2,
            true
         ) {
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
               return Menu_InGame_Report.this.getW() - CFG.PADDING * 2;
            }
         }
      );
      if (tempHeight > menuElements.get(menuElements.size() - 1).getPosY() + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING) {
         tempHeight = menuElements.get(menuElements.size() - 1).getPosY() + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
      }

      this.initMenu(
         new SliderMenuTitle(
            CFG.game.getProvince(CFG.reportData.iBattleOfProvinceID).getName().length() > 0
               ? this.getRandomBattleName(CFG.game.getProvince(CFG.reportData.iBattleOfProvinceID).getName())
               : CFG.langManager.get("Battle"),
            CFG.BUTTON_HEIGHT * 3 / 5,
            true,
            true
         ) {
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
               oSB.setColor(new Color(CFG.COLOR_GRADIENT_TITLE_BLUE));
               ImageManager.getImage(Images.gradient)
                  .draw(
                     oSB,
                     nPosX + 2 + iTranslateX,
                     nPosY - (this.getHeight() - 2) * 2 / 3 - ImageManager.getImage(Images.gradient).getHeight(),
                     nWidth - 4,
                     (this.getHeight() - 2) * 2 / 3,
                     false,
                     true
                  );
               oSB.setColor(new Color(CFG.COLOR_GRADIENT_TITLE_BLUE.r, CFG.COLOR_GRADIENT_TITLE_BLUE.g, CFG.COLOR_GRADIENT_TITLE_BLUE.b, 0.5F));
               ImageManager.getImage(Images.gradient)
                  .draw(
                     oSB,
                     nPosX + 2 + iTranslateX,
                     nPosY - (int)(this.getHeight() / 2.5F) - ImageManager.getImage(Images.gradient).getHeight() + 2,
                     nWidth - 4,
                     (int)(this.getHeight() / 2.5F) - 2,
                     false,
                     true
                  );
               oSB.setColor(CFG.COLOR_NEW_GAME_EDGE_LINE);
               ImageManager.getImage(Images.pix255_255_255)
                  .draw2(
                     oSB,
                     nPosX + 2 + iTranslateX,
                     nPosY - ImageManager.getImage(Images.pix255_255_255).getHeight() * 2,
                     nWidth - 4,
                     ImageManager.getImage(Images.pix255_255_255).getHeight()
                  );
               oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.65F));
               ImageManager.getImage(Images.line_32_off1)
                  .draw(oSB, nPosX + 2 + iTranslateX, nPosY - ImageManager.getImage(Images.pix255_255_255).getHeight() * 2, nWidth - 4, 1);
               oSB.setColor(Color.WHITE);

               try {
                  if (CFG.game.getProvince(CFG.reportData.iBattleOfProvinceID).getSeaProvince()) {
                     ImageManager.getImage(Images.icon_move_sea)
                        .draw(
                           oSB,
                           nPosX
                              + nWidth / 2
                              - (int)(this.getTextWidth() * 0.7F) / 2
                              - CFG.PADDING
                              - ImageManager.getImage(Images.icon_move_sea).getWidth()
                              + iTranslateX,
                           nPosY - this.getHeight() + this.getHeight() / 2 - ImageManager.getImage(Images.icon_move_sea).getHeight() / 2
                        );
                  } else {
                     ImageManager.getImage(Images.diplo_rivals)
                        .draw(
                           oSB,
                           nPosX
                              + nWidth / 2
                              - (int)(this.getTextWidth() * 0.7F) / 2
                              - CFG.PADDING
                              - ImageManager.getImage(Images.diplo_rivals).getWidth()
                              + iTranslateX,
                           nPosY - this.getHeight() + this.getHeight() / 2 - ImageManager.getImage(Images.diplo_rivals).getHeight() / 2
                        );
                  }
               } catch (IndexOutOfBoundsException var8) {
               } catch (NullPointerException var9) {
               }

               CFG.fontMain.getData().setScale(0.7F);
               CFG.drawTextWithShadow(
                  oSB,
                  this.getText(),
                  nPosX + nWidth / 2 - (int)(this.getTextWidth() * 0.7F) / 2 + iTranslateX,
                  nPosY - this.getHeight() + this.getHeight() / 2 - (int)(this.getTextHeight() * 0.7F / 2.0F),
                  Color.WHITE
               );
               CFG.fontMain.getData().setScale(1.0F);
            }
         },
         (CFG.GAME_WIDTH - tempWidth) / 2,
         ImageManager.getImage(Images.top_left).getHeight() + CFG.PADDING * 2 + CFG.BUTTON_HEIGHT * 3 / 5,
         tempWidth,
         tempHeight,
         menuElements,
         false,
         true
      );
   }

   public final String getRandomBattleName(String sBattleOf) {
      int nR = CFG.oR.nextInt(1000);
      switch (nR % 4) {
         case 1:
            return CFG.langManager.get("ScrambleFor", sBattleOf);
         case 2:
            return CFG.langManager.get("InvasionOf", sBattleOf);
         case 3:
            return CFG.langManager.get("AttackOn", sBattleOf);
         default:
            return CFG.langManager.get("BattleOf", sBattleOf);
      }
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
            this.getHeight() + CFG.PADDING,
            false,
            true
         );
      ImageManager.getImage(Images.new_game_top_edge)
         .draw2(
            oSB,
            this.getPosX() + this.getWidth() - ImageManager.getImage(Images.new_game_top_edge).getWidth() + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.new_game_top_edge).getHeight() + iTranslateY,
            ImageManager.getImage(Images.new_game_top_edge).getWidth(),
            this.getHeight() + CFG.PADDING,
            true,
            true
         );
      this.beginClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
      if (CFG.reportData.attackersWon) {
         oSB.setColor(new Color(CFG.COLOR_TEXT_CHECKBOX_TRUE.r, CFG.COLOR_TEXT_CHECKBOX_TRUE.g, CFG.COLOR_TEXT_CHECKBOX_TRUE.b, 0.325F));
      } else {
         oSB.setColor(new Color(CFG.COLOR_TEXT_CHECKBOX_FALSE.r, CFG.COLOR_TEXT_CHECKBOX_FALSE.g, CFG.COLOR_TEXT_CHECKBOX_FALSE.b, 0.325F));
      }

      ImageManager.getImage(Images.gradient)
         .draw(
            oSB,
            this.getPosX() + 2 + iTranslateX,
            this.getMenuPosY() + this.getMenuElement(0).getHeight() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY,
            (this.getWidth() - 4) / 2,
            (int)(this.getMenuElement(0).getHeight() * 1.45F)
         );
      if (CFG.reportData.attackersWon) {
         oSB.setColor(new Color(CFG.COLOR_TEXT_CHECKBOX_FALSE.r, CFG.COLOR_TEXT_CHECKBOX_FALSE.g, CFG.COLOR_TEXT_CHECKBOX_FALSE.b, 0.325F));
      } else {
         oSB.setColor(new Color(CFG.COLOR_TEXT_CHECKBOX_TRUE.r, CFG.COLOR_TEXT_CHECKBOX_TRUE.g, CFG.COLOR_TEXT_CHECKBOX_TRUE.b, 0.325F));
      }

      ImageManager.getImage(Images.gradient)
         .draw(
            oSB,
            this.getPosX() + 2 + (this.getWidth() - 4) / 2 + iTranslateX,
            this.getMenuPosY() + this.getMenuElement(0).getHeight() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY,
            (this.getWidth() - 4) / 2,
            (int)(this.getMenuElement(0).getHeight() * 1.45F)
         );
      oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.475F));
      ImageManager.getImage(Images.slider_gradient)
         .draw(
            oSB,
            this.getPosX() + 2 + (this.getWidth() - 4) / 2 - (this.getWidth() - 4) / 8 + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY,
            (this.getWidth() - 4) / 8,
            this.getHeight(),
            true,
            false
         );
      ImageManager.getImage(Images.slider_gradient)
         .draw(
            oSB,
            this.getPosX() + 2 + (this.getWidth() - 4) / 2 + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY,
            (this.getWidth() - 4) / 8,
            this.getHeight()
         );
      oSB.setColor(Color.WHITE);
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
