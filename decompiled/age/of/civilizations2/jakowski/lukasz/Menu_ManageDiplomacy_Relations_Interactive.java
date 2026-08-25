package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

public class Menu_ManageDiplomacy_Relations_Interactive extends SliderMenu {
   public Menu_ManageDiplomacy_Relations_Interactive() {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      menuElements.add(
         new Button_Add("", -1, CFG.PADDING, CFG.PADDING, CFG.GAME_WIDTH - CFG.PADDING * 2, CFG.BUTTON_HEIGHT, true) {
            @Override
            public void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
               super.drawText(oSB, iTranslateX, iTranslateY, isActive);
               if (CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID > 0) {
                  CFG.game
                     .getCiv(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID)
                     .getFlag()
                     .draw(
                        oSB,
                        this.getPosX() + this.getWidth() / 2 - this.getTextWidth() / 2 - CFG.PADDING - CFG.CIV_FLAG_WIDTH + iTranslateX,
                        this.getPosY()
                           + this.getHeight() / 2
                           - CFG.CIV_FLAG_HEIGHT / 2
                           - CFG.game.getCiv(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID).getFlag().getHeight()
                           + iTranslateY,
                        CFG.CIV_FLAG_WIDTH,
                        CFG.CIV_FLAG_HEIGHT
                     );
                  ImageManager.getImage(Images.flag_rect)
                     .draw(
                        oSB,
                        this.getPosX() + this.getWidth() / 2 - this.getTextWidth() / 2 - CFG.PADDING - CFG.CIV_FLAG_WIDTH + iTranslateX,
                        this.getPosY() + this.getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 + iTranslateY
                     );
               }
            }
         }
      );
      menuElements.add(new Button_Game("-", -1, CFG.PADDING, CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT * 2 - CFG.PADDING * 3, true));
      menuElements.add(
         new Slider_Relations(
            CFG.BUTTON_WIDTH + CFG.PADDING * 2,
            CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT * 2 - CFG.PADDING * 3,
            CFG.GAME_WIDTH - CFG.BUTTON_WIDTH * 2 - CFG.PADDING * 4,
            CFG.BUTTON_HEIGHT,
            -100,
            100,
            0
         ) {
            @Override
            public void buildElementHover() {
               if (CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID > 0 && CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID2 > 0) {
                  ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
                  ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
                  nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID));
                  nData.add(
                     new MenuElement_Hover_v2_Element_Type_Text(
                        CFG.game.getCiv(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID).getCivName()
                           + " - "
                           + CFG.game.getCiv(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID2).getCivName(),
                        CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                     )
                  );
                  nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID2, CFG.PADDING, 0));
                  nElements.add(new MenuElement_Hover_v2_Element2(nData));
                  nData.clear();
                  nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Opinion") + ": "));
                  nData.add(
                     new MenuElement_Hover_v2_Element_Type_Text(
                        "" + (this.getCurrent() > 0 ? "+" + this.getCurrent() : this.getCurrent()),
                        this.getCurrent() == 0
                           ? CFG.COLOR_TEXT_MODIFIER_NEUTRAL
                           : (this.getCurrent() > 0 ? CFG.COLOR_TEXT_MODIFIER_POSITIVE : CFG.COLOR_TEXT_MODIFIER_NEGATIVE)
                     )
                  );
                  nElements.add(new MenuElement_Hover_v2_Element2(nData));
                  nData.clear();
                  this.menuElementHover = new MenuElement_Hover_v2(nElements);
               } else {
                  this.menuElementHover = null;
               }
            }

            @Override
            public void updateSlider(int nX) {
               super.updateSlider(nX);
               this.menuElementHover = null;
            }
         }
      );
      menuElements.add(
         new Button_Game("+", -1, CFG.GAME_WIDTH - CFG.BUTTON_WIDTH - CFG.PADDING, CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT * 2 - CFG.PADDING * 3, true)
      );
      menuElements.add(new Button_Game("-", -1, CFG.PADDING, CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT * 3 - CFG.PADDING * 4, true));
      menuElements.add(
         new Slider_Relations2(
            CFG.BUTTON_WIDTH + CFG.PADDING * 2,
            CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT * 3 - CFG.PADDING * 4,
            CFG.GAME_WIDTH - CFG.BUTTON_WIDTH * 2 - CFG.PADDING * 4,
            CFG.BUTTON_HEIGHT,
            -100,
            100,
            0
         ) {
            @Override
            public void buildElementHover() {
               if (CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID > 0 && CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID2 > 0) {
                  ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
                  ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
                  nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID2));
                  nData.add(
                     new MenuElement_Hover_v2_Element_Type_Text(
                        CFG.game.getCiv(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID2).getCivName()
                           + " - "
                           + CFG.game.getCiv(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID).getCivName(),
                        CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                     )
                  );
                  nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID, CFG.PADDING, 0));
                  nElements.add(new MenuElement_Hover_v2_Element2(nData));
                  nData.clear();
                  nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Opinion") + ": "));
                  nData.add(
                     new MenuElement_Hover_v2_Element_Type_Text(
                        "" + (this.getCurrent() > 0 ? "+" + this.getCurrent() : this.getCurrent()),
                        this.getCurrent() == 0
                           ? CFG.COLOR_TEXT_MODIFIER_NEUTRAL
                           : (this.getCurrent() > 0 ? CFG.COLOR_TEXT_MODIFIER_POSITIVE : CFG.COLOR_TEXT_MODIFIER_NEGATIVE)
                     )
                  );
                  nElements.add(new MenuElement_Hover_v2_Element2(nData));
                  nData.clear();
                  this.menuElementHover = new MenuElement_Hover_v2(nElements);
               } else {
                  this.menuElementHover = null;
               }
            }

            @Override
            public void updateSlider(int nX) {
               super.updateSlider(nX);
               this.menuElementHover = null;
            }
         }
      );
      menuElements.add(
         new Button_Game("+", -1, CFG.GAME_WIDTH - CFG.BUTTON_WIDTH - CFG.PADDING, CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT * 3 - CFG.PADDING * 4, true)
      );
      this.initMenu(null, 0, 0, CFG.GAME_WIDTH, CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT, menuElements, false, false);
      this.updateLanguage();
   }

   @Override
   public void updateLanguage() {
   }

   @Override
   public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      ImageManager.getImage(Images.editor_line)
         .draw2(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.editor_line).getHeight() + iTranslateY,
            this.getWidth(),
            this.getMenuElement(0).getHeight() + CFG.PADDING * 2,
            false,
            true
         );
      oSB.setColor(new Color(CFG.COLOR_GRADIENT_DARK_BLUE.r, CFG.COLOR_GRADIENT_DARK_BLUE.g, CFG.COLOR_GRADIENT_DARK_BLUE.b, 0.575F));
      ImageManager.getImage(Images.gradient)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY,
            this.getWidth(),
            (this.getMenuElement(0).getHeight() + CFG.PADDING * 2) / 4
         );
      oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.65F));
      ImageManager.getImage(Images.line_32_off1)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() + this.getMenuElement(0).getHeight() + CFG.PADDING * 2 - 1 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
            this.getWidth(),
            1
         );
      oSB.setColor(new Color(0.0425F, 0.0475F, 0.06F, 0.7F));
      ImageManager.getImage(Images.pix255_255_255)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() + this.getMenuElement(0).getHeight() + CFG.PADDING * 2 - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY,
            this.getWidth(),
            1
         );
      oSB.setColor(Color.WHITE);
      ImageManager.getImage(Images.bg_game_menu)
         .draw2(
            oSB,
            this.getPosX() + iTranslateX,
            this.getMenuElement(4).getPosY() - CFG.PADDING - ImageManager.getImage(Images.bg_game_menu).getHeight() + iTranslateY,
            this.getWidth(),
            this.getMenuElement(4).getHeight() + CFG.PADDING * 2
         );
      ImageManager.getImage(Images.bg_game_menu)
         .draw2(
            oSB,
            this.getPosX() + iTranslateX,
            this.getMenuElement(1).getPosY() - CFG.PADDING - ImageManager.getImage(Images.bg_game_menu).getHeight() + iTranslateY,
            this.getWidth(),
            this.getMenuElement(1).getHeight() + CFG.PADDING * 2
         );
      oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.475F));
      ImageManager.getImage(Images.slider_gradient)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getMenuElement(1).getPosY() - CFG.PADDING - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY,
            this.getWidth() / 4,
            1
         );
      ImageManager.getImage(Images.slider_gradient)
         .draw(
            oSB,
            this.getPosX() + this.getWidth() - this.getWidth() / 4 + iTranslateX,
            this.getMenuElement(1).getPosY() - CFG.PADDING - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY,
            this.getWidth() / 4,
            1,
            true,
            false
         );
      oSB.setColor(new Color(0.0425F, 0.0475F, 0.06F, 0.7F));
      ImageManager.getImage(Images.pix255_255_255)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getMenuElement(1).getPosY() - CFG.PADDING - ImageManager.getImage(Images.pix255_255_255).getHeight() - 1 + iTranslateY,
            this.getWidth(),
            1
         );
      oSB.setColor(Color.WHITE);
      super.draw(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
   }

   @Override
   public final void actionElement(int iID) {
      switch (iID) {
         case 0:
         default:
            break;
         case 1:
            this.getMenuElement(2).setCurrent(this.getMenuElement(2).getCurrent() - 1);
            this.updateRelation();
            break;
         case 2:
            this.updateRelation();
            break;
         case 3:
            this.getMenuElement(2).setCurrent(this.getMenuElement(2).getCurrent() + 1);
            this.updateRelation();
            break;
         case 4:
            this.getMenuElement(5).setCurrent(this.getMenuElement(5).getCurrent() - 1);
            this.updateRelation();
            break;
         case 5:
            this.updateRelation();
            break;
         case 6:
            this.getMenuElement(5).setCurrent(this.getMenuElement(5).getCurrent() + 1);
            this.updateRelation();
      }
   }

   public final void updateRelation() {
      CFG.game
         .setCivRelation_OfCivB(
            CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID, CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID2, this.getMenuElement(2).getCurrent()
         );
      CFG.game
         .setCivRelation_OfCivB(
            CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID2, CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID, this.getMenuElement(5).getCurrent()
         );
   }
}
