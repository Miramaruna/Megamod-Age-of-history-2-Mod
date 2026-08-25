package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.utils.ScissorStack;
import java.util.ArrayList;

public class Menu_InGame_mobila extends SliderMenu {
   public static int provincesCount = 0;
   public static int recruitCount = 0;
   public static int iLastProvince;
   public int iProvinceID;
   public int iOnCivID = -1;

   public Menu_InGame_mobila(int iProvinceID) {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      this.iProvinceID = iProvinceID;
      iLastProvince = iProvinceID;
      int tempWidth = CFG.CIV_INFO_MENU_WIDTH * 2;
      int tY = 0;
      menuElements.add(
         new Button_Build_Building(
            CFG.langManager.get("MobilisationIn") + " ",
            CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCivName(),
            Images.editor_leaders,
            0,
            0,
            0,
            tY,
            CFG.BUTTON_WIDTH * 2
         ) {
            @Override
            public void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
               ImageManager.getImage(this.iImageID)
                  .draw(
                     oSB,
                     this.getPosX() + Button_Diplomacy.iDiploWidth / 2 - ImageManager.getImage(this.iImageID).getWidth() / 2 + iTranslateX,
                     this.getPosY() + this.getHeight() / 2 - ImageManager.getImage(this.iImageID).getHeight() / 2 + iTranslateY
                  );
               if (this.sCost.length() > 0 && this.sMovementCost.length() > 0) {
                  if (this.sCost.length() > 0) {
                     ImageManager.getImage(Images.top_gold)
                        .draw(
                           oSB,
                           this.getPosX()
                              + this.getWidth()
                              - CFG.PADDING * 2
                              - (int)(ImageManager.getImage(Images.top_gold).getWidth() * this.getImageScale(Images.top_gold, 0.6F))
                              + iTranslateX,
                           this.getPosY()
                              + this.getHeight() / 2
                              - (int)(ImageManager.getImage(Images.top_gold).getHeight() * this.getImageScale(Images.top_gold, 0.6F))
                              - ImageManager.getImage(Images.top_gold).getHeight()
                              - CFG.PADDING / 2
                              + iTranslateY,
                           (int)(ImageManager.getImage(Images.top_gold).getWidth() * this.getImageScale(Images.top_gold, 0.6F)),
                           (int)(ImageManager.getImage(Images.top_gold).getHeight() * this.getImageScale(Images.top_gold, 0.6F))
                        );
                     CFG.fontMain.getData().setScale(0.6F);
                     CFG.drawTextWithShadow(
                        oSB,
                        this.sCost,
                        this.getPosX()
                           + this.getWidth()
                           - CFG.PADDING * 2
                           - (int)(ImageManager.getImage(Images.top_gold).getWidth() * this.getImageScale(Images.top_gold, 0.6F))
                           - CFG.PADDING
                           - this.iCostWidth
                           + iTranslateX,
                        this.getPosY() + this.getHeight() / 2 - CFG.PADDING / 2 - (int)(this.getTextHeight() * 0.6F) + iTranslateY,
                        this.canBuild_MoneyCost ? CFG.COLOR_INGAME_GOLD : CFG.COLOR_TEXT_MODIFIER_NEGATIVE2
                     );
                  }

                  if (this.sMovementCost.length() > 0) {
                     ImageManager.getImage(Images.top_movement_points)
                        .draw(
                           oSB,
                           this.getPosX()
                              + this.getWidth()
                              - CFG.PADDING * 2
                              - (int)(ImageManager.getImage(Images.top_movement_points).getWidth() * this.getImageScale(Images.top_movement_points, 0.6F))
                              + iTranslateX,
                           this.getPosY()
                              + this.getHeight() / 2
                              - ImageManager.getImage(Images.top_movement_points).getHeight()
                              + CFG.PADDING / 2
                              + iTranslateY,
                           (int)(ImageManager.getImage(Images.top_movement_points).getWidth() * this.getImageScale(Images.top_movement_points, 0.6F)),
                           (int)(ImageManager.getImage(Images.top_movement_points).getHeight() * this.getImageScale(Images.top_movement_points, 0.6F))
                        );
                     CFG.fontMain.getData().setScale(0.6F);
                     CFG.drawTextWithShadow(
                        oSB,
                        this.sMovementCost,
                        this.getPosX()
                           + this.getWidth()
                           - CFG.PADDING * 2
                           - this.iMovementCostWidth
                           - (int)(ImageManager.getImage(Images.top_movement_points).getWidth() * this.getImageScale(Images.top_movement_points, 0.6F))
                           - CFG.PADDING
                           + iTranslateX,
                        this.getPosY() + this.getHeight() / 2 + CFG.PADDING / 2 + iTranslateY,
                        this.canBuild_Movement ? CFG.COLOR_INGAME_MOVEMENT : CFG.COLOR_TEXT_MODIFIER_NEGATIVE
                     );
                  }
               } else if (this.sMovementCost.length() > 0) {
                  ImageManager.getImage(Images.top_movement_points)
                     .draw(
                        oSB,
                        this.getPosX()
                           + this.getWidth()
                           - CFG.PADDING * 2
                           - (int)(ImageManager.getImage(Images.top_movement_points).getWidth() * this.getImageScale(Images.top_movement_points, 0.6F))
                           + iTranslateX,
                        this.getPosY()
                           + this.getHeight() / 2
                           - ImageManager.getImage(Images.top_movement_points).getHeight()
                           - (int)(ImageManager.getImage(Images.top_movement_points).getHeight() * this.getImageScale(Images.top_movement_points, 0.6F)) / 2
                           + iTranslateY,
                        (int)(ImageManager.getImage(Images.top_movement_points).getWidth() * this.getImageScale(Images.top_movement_points, 0.6F)),
                        (int)(ImageManager.getImage(Images.top_movement_points).getHeight() * this.getImageScale(Images.top_movement_points, 0.6F))
                     );
                  CFG.fontMain.getData().setScale(0.6F);
                  CFG.drawTextWithShadow(
                     oSB,
                     this.sMovementCost,
                     this.getPosX()
                        + this.getWidth()
                        - CFG.PADDING * 2
                        - this.iMovementCostWidth
                        - (int)(ImageManager.getImage(Images.top_movement_points).getWidth() * this.getImageScale(Images.top_movement_points, 0.6F))
                        - CFG.PADDING
                        + iTranslateX,
                     this.getPosY() + this.getHeight() / 2 - (int)(this.getTextHeight() * 0.6F) / 2 + iTranslateY,
                     this.canBuild_Movement ? CFG.COLOR_INGAME_MOVEMENT : CFG.COLOR_TEXT_MODIFIER_NEGATIVE
                  );
               }

               CFG.fontMain.getData().setScale(0.7F);
               CFG.drawTextWithShadow(
                  oSB,
                  this.getText(),
                  this.getPosX() + CFG.PADDING + Button_Diplomacy.iDiploWidth + iTranslateX,
                  this.getPosY() + this.getHeight() / 2 - (int)(this.getTextHeight() * 0.7F) - CFG.PADDING / 2 + iTranslateY,
                  this.getColor(isActive)
               );
               CFG.drawTextWithShadow(
                  oSB,
                  this.sProvinceName,
                  this.getPosX() + CFG.PADDING + Button_Diplomacy.iDiploWidth + (int)(this.getTextWidth() * 0.7F) + iTranslateX,
                  this.getPosY() + this.getHeight() / 2 - (int)(this.getTextHeight() * 0.7F) - CFG.PADDING / 2 + iTranslateY,
                  CFG.COLOR_TEXT_NUM_OF_PROVINCES
               );
               CFG.drawTextWithShadow(
                  oSB,
                  CFG.langManager.get("MobilisationThis"),
                  this.getPosX() + CFG.PADDING + Button_Diplomacy.iDiploWidth + iTranslateX,
                  this.getPosY() + this.getHeight() / 2 + CFG.PADDING / 2 + iTranslateY,
                  this.getColor(isActive)
               );
               CFG.fontMain.getData().setScale(1.0F);
            }

            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> list = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type_Text> list2 = new ArrayList<>();
               list2.add(
                  new MenuElement_Hover_v2_Element_Type_Text(
                     CFG.langManager.get("ReducesTheCostOfRecruitmentPerUnitByOneGold"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                  )
               );
               list.add(new MenuElement_Hover_v2_Element2(list2));
               list2.clear();
               this.menuElementHover = new MenuElement_Hover_v2(list);
            }

            @Override
            public int getWidth() {
               return Menu_InGame_mobila.this.getElementW() * 2;
            }
         }
      );
      tY += menuElements.get(menuElements.size() - 1).getHeight();
      menuElements.add(
         new Slider_CrossTheBorder(
            CFG.langManager.get("SelectThePercentageOfConscription", 0),
            CFG.PADDING * 2,
            menuElements.get(menuElements.size() - 1).getPosY() + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING,
            tempWidth - CFG.PADDING * 3 - CFG.BUTTON_WIDTH,
            CFG.TEXT_HEIGHT + CFG.PADDING * 2 + CFG.PADDING * 4,
            0,
            100,
            0,
            0.65F
         ) {
            @Override
            public int getWidth() {
               return Menu_InGame_mobila.this.getElementW() * 2 - CFG.PADDING * 4;
            }

            @Override
            public void updateSlider(int nX) {
               super.updateSlider(nX);
               this.setText(CFG.langManager.get("SelectThePercentageOfConscription", this.getCurrent()));
            }

            @Override
            public int getSliderHeight() {
               return CFG.PADDING * 2;
            }

            @Override
            public Color getColorLEFT() {
               return new Color(
                  CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_AT_WAR.getR(),
                  CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_AT_WAR.getG(),
                  CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_AT_WAR.getB(),
                  0.65F
               );
            }
         }
      );
      tY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
      int var8;
      menuElements.add(new Button_FlagActionSliderStyle(CFG.langManager.get("Cancel"), -1, 2 + CFG.PADDING, var8 = tY + CFG.PADDING, CFG.BUTTON_WIDTH, true) {
         @Override
         public int getPosX() {
            return CFG.PADDING;
         }

         @Override
         public int getWidth() {
            return Menu_InGame_mobila.this.getWidth() / 3;
         }
      });
      menuElements.add(
         new Button_FlagActionSliderStyle(CFG.langManager.get("Mobilisation"), -1, 2, var8, CFG.BUTTON_WIDTH, true) {
            @Override
            public int getPosX() {
               return CFG.PADDING + Menu_InGame_mobila.this.getWidth() / 3 * 2 - CFG.PADDING;
            }

            @Override
            public int getWidth() {
               return Menu_InGame_mobila.this.getWidth() / 3 - CFG.PADDING;
            }

            @Override
            public void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
               CFG.fontMain.getData().setScale(0.8F);
               CFG.drawText(
                  oSB,
                  this.getText(),
                  this.getPosX() + this.getWidth() / 2 - (int)(this.getTextWidth() * 0.8F / 2.0F) + iTranslateX,
                  this.getPosY() + this.getHeight() / 2 - (int)(this.getTextHeight() * 0.8F / 2.0F) + iTranslateY,
                  this.getColor(isActive)
               );
               CFG.fontMain.getData().setScale(1.0F);
            }

            @Override
            public boolean getClickable() {
               return true;
            }

            @Override
            public int getSFX() {
               return SoundsManager.SOUND_CLICK3;
            }

            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Mobilisation") + "!", CFG.COLOR_TEXT_MODIFIER_NEGATIVE2));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
         }
      );
      menuElements.add(
         new Button_FlagActionSliderStyle(CFG.langManager.get("Provinces") + ": " + provincesCount, -1, iProvinceID + 2, var8, CFG.BUTTON_WIDTH, true) {
            @Override
            public int getPosX() {
               return CFG.PADDING * 2 + Menu_InGame_mobila.this.getWidth() / 3;
            }

            @Override
            public int getWidth() {
               return Menu_InGame_mobila.this.getWidth() / 3 - CFG.PADDING * 3;
            }
         }
      );
      int tempMenuPosY = ImageManager.getImage(Images.top_left2).getHeight() + CFG.PADDING * 2 + CFG.BUTTON_HEIGHT * 3 / 5;
      this.initMenu(
         new SliderMenuTitle(CFG.langManager.get("Mobilisation"), CFG.BUTTON_HEIGHT * 3 / 5, true, true) {
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
               oSB.setColor(new Color(CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.r, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.g, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.b, 0.165F));
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
               oSB.setColor(new Color(CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.r, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.g, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.b, 0.375F));
               ImageManager.getImage(Images.gradient)
                  .draw(
                     oSB,
                     nPosX + iTranslateX,
                     nPosY - this.getHeight() * 2 / 3 - ImageManager.getImage(Images.gradient).getHeight(),
                     nWidth,
                     this.getHeight() * 2 / 3,
                     false,
                     true
                  );
               oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.65F));
               ImageManager.getImage(Images.gradient)
                  .draw(oSB, nPosX + iTranslateX, nPosY - CFG.PADDING - ImageManager.getImage(Images.gradient).getHeight(), nWidth, CFG.PADDING, false, true);
               oSB.setColor(CFG.COLOR_NEW_GAME_EDGE_LINE);
               ImageManager.getImage(Images.pix255_255_255)
                  .draw(oSB, nPosX + iTranslateX, nPosY - 1 - ImageManager.getImage(Images.pix255_255_255).getHeight(), nWidth, 1);
               oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.55F));
               ImageManager.getImage(Images.line_32_off1)
                  .draw(oSB, nPosX + iTranslateX, nPosY - 2 - ImageManager.getImage(Images.line_32_off1).getHeight(), nWidth, 1);
               oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.8F));
               ImageManager.getImage(Images.line_32_off1)
                  .draw(oSB, nPosX + iTranslateX, nPosY - 1 - ImageManager.getImage(Images.line_32_off1).getHeight(), nWidth, 1);
               oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.45F));
               ImageManager.getImage(Images.slider_gradient)
                  .draw(oSB, nPosX + iTranslateX, nPosY - 1 - ImageManager.getImage(Images.slider_gradient).getHeight(), nWidth / 2, 1);
               ImageManager.getImage(Images.slider_gradient)
                  .draw(
                     oSB,
                     nPosX + nWidth - nWidth / 2 + iTranslateX,
                     nPosY - 1 - ImageManager.getImage(Images.slider_gradient).getHeight(),
                     nWidth / 2,
                     1,
                     true,
                     false
                  );
               oSB.setColor(Color.WHITE);
               CFG.game
                  .getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
                  .getFlag()
                  .draw(
                     oSB,
                     Menu_InGame_mobila.this.getPosX() + CFG.PADDING * 2 + iTranslateX,
                     Menu_InGame_mobila.this.getPosY()
                        - this.getHeight() / 2
                        - CFG.CIV_FLAG_HEIGHT / 2
                        - CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getFlag().getHeight(),
                     CFG.CIV_FLAG_WIDTH,
                     CFG.CIV_FLAG_HEIGHT
                  );
               ImageManager.getImage(Images.flag_rect)
                  .draw(
                     oSB,
                     Menu_InGame_mobila.this.getPosX() + CFG.PADDING * 2 + iTranslateX,
                     Menu_InGame_mobila.this.getPosY() - this.getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2
                  );
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
         menuElements.get(menuElements.size() - 1).getPosY() + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING + tempMenuPosY
               > CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING * 2
            ? Math.max(CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING * 2 - tempMenuPosY, (CFG.TEXT_HEIGHT + CFG.PADDING * 2) * 6)
            : menuElements.get(menuElements.size() - 1).getPosY() + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING,
         menuElements,
         true,
         true
      );
      this.updateLanguage();
      Menu_InGame_OfferAlliance.lTime = System.currentTimeMillis();
   }

   @Override
   public void updateLanguage() {
   }

   @Override
   public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      if (Menu_InGame_OfferAlliance.lTime + 200L >= System.currentTimeMillis()) {
         Rectangle clipBounds = new Rectangle(
            this.getPosX() - 2,
            CFG.GAME_HEIGHT - this.getPosY(),
            this.getWidth() + 4,
            -((int)((this.getHeight() + CFG.PADDING) * ((float)(System.currentTimeMillis() - Menu_InGame_OfferAlliance.lTime) / 200.0F)))
         );
         oSB.flush();
         ScissorStack.pushScissors(clipBounds);
         oSB.setColor(Color.WHITE);
         ImageManager.getImage(Images.new_game_top_edge)
            .draw2(
               oSB,
               this.getPosX() - 2 + iTranslateX,
               this.getPosY() - ImageManager.getImage(Images.new_game_top_edge).getHeight() + iTranslateY,
               this.getWidth() - ImageManager.getImage(Images.new_game_top_edge).getWidth() + 4,
               this.getHeight() + CFG.PADDING,
               false,
               true
            );
         ImageManager.getImage(Images.new_game_top_edge)
            .draw2(
               oSB,
               this.getPosX() + 2 + this.getWidth() - ImageManager.getImage(Images.new_game_top_edge).getWidth() + iTranslateX,
               this.getPosY() - ImageManager.getImage(Images.new_game_top_edge).getHeight() + iTranslateY,
               ImageManager.getImage(Images.new_game_top_edge).getWidth(),
               this.getHeight() + CFG.PADDING,
               true,
               true
            );
         oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.45F));
         ImageManager.getImage(Images.gradient)
            .draw(
               oSB,
               this.getPosX() + 2 + iTranslateX,
               this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY,
               this.getWidth() - 4,
               this.getHeight() / 4
            );
         ImageManager.getImage(Images.pix255_255_255)
            .draw(
               oSB,
               this.getPosX() + 2 + iTranslateX,
               this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY,
               this.getWidth() - 4,
               1
            );
         oSB.setColor(Color.WHITE);
         this.drawMenu(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
         oSB.setColor(Color.WHITE);
         CFG.setRender_3(true);
         this.endClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
      } else {
         oSB.setColor(Color.WHITE);
         ImageManager.getImage(Images.new_game_top_edge)
            .draw2(
               oSB,
               this.getPosX() - 2 + iTranslateX,
               this.getPosY() - ImageManager.getImage(Images.new_game_top_edge).getHeight() + iTranslateY,
               this.getWidth() - ImageManager.getImage(Images.new_game_top_edge).getWidth() + 4,
               this.getHeight() + CFG.PADDING,
               false,
               true
            );
         ImageManager.getImage(Images.new_game_top_edge)
            .draw2(
               oSB,
               this.getPosX() + 2 + this.getWidth() - ImageManager.getImage(Images.new_game_top_edge).getWidth() + iTranslateX,
               this.getPosY() - ImageManager.getImage(Images.new_game_top_edge).getHeight() + iTranslateY,
               ImageManager.getImage(Images.new_game_top_edge).getWidth(),
               this.getHeight() + CFG.PADDING,
               true,
               true
            );
         oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.45F));
         ImageManager.getImage(Images.gradient)
            .draw(
               oSB,
               this.getPosX() + 2 + iTranslateX,
               this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY,
               this.getWidth() - 4,
               this.getHeight() / 4
            );
         ImageManager.getImage(Images.pix255_255_255)
            .draw(
               oSB,
               this.getPosX() + 2 + iTranslateX,
               this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY,
               this.getWidth() - 4,
               1
            );
         oSB.setColor(Color.WHITE);
         this.beginClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
         this.drawMenu(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
         oSB.setColor(Color.WHITE);
         this.endClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
      }
   }

   @Override
   public void drawScrollPos(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      if (sliderMenuIsActive) {
         super.drawScrollPos(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
      }
   }

   @Override
   public final void actionElement(int paramInt) {
      if (paramInt >= 0 && paramInt < this.getMenuElementsSize()) {
         if (paramInt == this.getMenuElementsSize() - 1) {
            CFG.MANAGE_DIPLOMACY_CUSTOMIZE_ALLIANCE_ID = CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID();
            CFG.game.getPlayer(CFG.PLAYER_TURNID).iACTIVE_VIEW_MODE = CFG.viewsManager.getActiveViewID();
            CFG.viewsManager.disableAllViews();
            CFG.game.setActiveProvinceID(-1);
            Menu_InGame_SelectProvinces.typeOfAction = Menu_InGame_SelectProvinces.TypeOfAction.MOBOLIZATION;
            CFG.VIEW_SHOW_VALUES = false;
            CFG.selectMode = true;
            CFG.game.getSelectedProvinces().clearSelectedProvinces();

            for (int i = 0; i < CFG.game.recruitInProvinces.size(); i++) {
               CFG.game.getSelectedProvinces().addProvince(CFG.game.recruitInProvinces.get(i));
            }

            CFG.menuManager.setViewID(Menu.eINGAME_SELECT_PROVINCES);
            Game_Render_Province.updateDrawProvinces();
         } else if (paramInt == this.getMenuElementsSize() - 2) {
            try {
               CFG.game_NextTurnUpdate.updateCivRecruitableArmy(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());

               for (int provinceIndex : CFG.game.recruitInProvinces) {
                  if (CFG.game
                     .getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
                     .recruitArmy(
                        provinceIndex,
                        CFG.gameAction.getRecruitableArmy(provinceIndex, CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
                           * this.getMenuElement(this.getMenuElementsSize() - 4).getCurrent()
                           / 100
                     )) {
                  }
               }

               CFG.toast.setInView(CFG.langManager.get("Sent") + "!", CFG.COLOR_TEXT_MODIFIER_POSITIVE);
               CFG.toast.setTimeInView(4500);
               CFG.menuManager.updateInGame_TOP_All(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
               CFG.menuManager.rebuildInGame_Messages();
               CFG.menuManager.setVisible_Menu_InGame_CurrentWars(true);
            } catch (Exception var4) {
            }
         } else if (paramInt == this.getMenuElementsSize() - 3) {
            this.setVisible(false);
         }
      }
   }

   public final int getW() {
      return this.getWidth() - 4;
   }

   public final int getElementW() {
      return this.getW() / 2;
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
