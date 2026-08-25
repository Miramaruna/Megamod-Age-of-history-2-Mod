package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.utils.ScissorStack;
import java.util.ArrayList;
import java.util.List;

public class Menu_InGame_Politics extends SliderMenu {

   public int aiButtonsStart = -1;
   public int aiButtonsEnd = -1;

   private final int aiPolicyGet(int nSection) {
      switch (nSection) {
         case 0:
            return AI_Assistant.FOREIGN_POLICY;
         case 1:
            return AI_Assistant.MIGRATION_POLICY;
         case 2:
            return AI_Assistant.MINORITY_TAX;
         default:
            return AI_Assistant.NUCLEAR_DOCTRINE;
      }
   }

   private final void aiPolicySet(int nSection, int nValue) {
      switch (nSection) {
         case 0:
            AI_Assistant.FOREIGN_POLICY = nValue;
            break;
         case 1:
            AI_Assistant.MIGRATION_POLICY = nValue;
            break;
         case 2:
            AI_Assistant.MINORITY_TAX = nValue;
            break;
         default:
            AI_Assistant.NUCLEAR_DOCTRINE = nValue;
            break;
      }
   }
   public static final int MAX_SHOW = 9;

   public Menu_InGame_Politics() {
      boolean tempHeight = false;
      int tempWidth = 0;
      int tY = 0;
      tempWidth = CFG.isAndroid() && !CFG.LANDSCAPE ? CFG.GAME_WIDTH - CFG.PADDING * 4 : (int)(CFG.GAME_WIDTH / 1.7 - CFG.PADDING * 2);
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      CFG.game_NextTurnUpdate.getBalance_UpdateBudget_Prepare(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
      menuElements.add(new Text_EconomyTitle(CFG.langManager.get(""), -1, 0, 0, 0, 0));
      menuElements.add(new Text_EconomyTitle(CFG.langManager.get(""), -1, 0, 0, 0, 0));
      menuElements.add(
         new Button_FlagActionSliderStyle(
            CFG.langManager.get("EconomicPolicies"), -1, 2, CFG.PADDING * 2, tempWidth - 2, CFG.TEXT_HEIGHT + CFG.PADDING * 4, true
         ) {
            @Override
            public int getWidth() {
               return Menu_InGame_Politics.this.getW();
            }

            @Override
            public void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
               oSB.setColor(new Color(new Color(CFG.COLOR_INGAME_GOLD.r, CFG.COLOR_INGAME_GOLD.g, CFG.COLOR_INGAME_GOLD.b, 0.375F)));
               ImageManager.getImage(Images.gradient)
                  .draw(
                     oSB,
                     this.getPosX() + iTranslateX,
                     this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY,
                     this.getWidth(),
                     this.getHeight()
                  );
               oSB.setColor(new Color(new Color(CFG.COLOR_INGAME_GOLD.r, CFG.COLOR_INGAME_GOLD.g, CFG.COLOR_INGAME_GOLD.b, 0.375F)));
               ImageManager.getImage(Images.line_32_off1)
                  .draw(
                     oSB,
                     this.getPosX() + iTranslateX,
                     this.getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
                     this.getWidth(),
                     this.getHeight()
                  );
               oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.45F));
               ImageManager.getImage(Images.gradient)
                  .draw(
                     oSB,
                     this.getPosX() + iTranslateX,
                     this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY,
                     this.getWidth(),
                     CFG.PADDING
                  );
               oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.2F));
               ImageManager.getImage(Images.gradient)
                  .draw(
                     oSB,
                     this.getPosX() + iTranslateX,
                     this.getPosY() + this.getHeight() - CFG.PADDING - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY,
                     this.getWidth(),
                     CFG.PADDING,
                     false,
                     true
                  );
               oSB.setColor(new Color(new Color(CFG.COLOR_INGAME_GOLD.r, CFG.COLOR_INGAME_GOLD.g, CFG.COLOR_INGAME_GOLD.b, 0.375F)));
               ImageManager.getImage(Images.pix255_255_255)
                  .draw(
                     oSB,
                     this.getPosX() + iTranslateX,
                     this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY,
                     this.getWidth(),
                     1
                  );
               oSB.setColor(CFG.COLOR_NEW_GAME_EDGE_LINE);
               ImageManager.getImage(Images.slider_gradient)
                  .draw(
                     oSB,
                     this.getPosX() + iTranslateX,
                     this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY,
                     this.getWidth() / 2,
                     1
                  );
               ImageManager.getImage(Images.slider_gradient)
                  .draw(
                     oSB,
                     this.getPosX() + this.getWidth() - this.getWidth() / 2 + iTranslateX,
                     this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY,
                     this.getWidth() / 2,
                     1,
                     true,
                     false
                  );
               oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.65F));
               ImageManager.getImage(Images.slider_gradient)
                  .draw(
                     oSB,
                     this.getPosX() + iTranslateX,
                     this.getPosY() + 1 - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY,
                     this.getWidth() / 2,
                     1
                  );
               ImageManager.getImage(Images.slider_gradient)
                  .draw(
                     oSB,
                     this.getPosX() + this.getWidth() - this.getWidth() / 2 + iTranslateX,
                     this.getPosY() + 1 - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY,
                     this.getWidth() / 2,
                     1,
                     true,
                     false
                  );
               oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.55F));
               ImageManager.getImage(Images.slider_gradient)
                  .draw(
                     oSB,
                     this.getPosX() + iTranslateX,
                     this.getPosY() + this.getHeight() - 1 - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY,
                     this.getWidth() / 2,
                     1
                  );
               ImageManager.getImage(Images.slider_gradient)
                  .draw(
                     oSB,
                     this.getPosX() + this.getWidth() - this.getWidth() / 2 + iTranslateX,
                     this.getPosY() + this.getHeight() - 1 - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY,
                     this.getWidth() / 2,
                     1,
                     true,
                     false
                  );
               oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.105F));
               ImageManager.getImage(Images.gradient)
                  .draw(
                     oSB,
                     this.getPosX() + iTranslateX,
                     this.getPosY() + this.getHeight() - this.getHeight() / 3 - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY,
                     this.getWidth(),
                     this.getHeight() / 3,
                     false,
                     true
                  );
               oSB.setColor(Color.WHITE);
               CFG.fontMain.getData().setScale(0.85F);
               CFG.fontMain.getData().setScale(1.15F);
               oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.7F));
               ImageManager.getImage(Images.line_32_off1)
                  .draw(
                     oSB,
                     this.getPosX() + iTranslateX,
                     this.getPosY() + this.getHeight() - ImageManager.getImage(Images.line_32_off1).getHeight() - 1 + iTranslateY,
                     this.getWidth(),
                     1
                  );
               oSB.setColor(Color.WHITE);
            }

            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               nData.add(
                  new MenuElement_Hover_v2_Element_Type_Text(
                     CFG.langManager.get("EconomicPoliciesText"), new Color(CFG.COLOR_INGAME_GOLD.r, CFG.COLOR_INGAME_GOLD.g, CFG.COLOR_INGAME_GOLD.b, 0.375F)
                  )
               );
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("EconomicPoliciesText2"), CFG.COLOR_INGAME_DIPLOMACY_POINTS));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("EconomicPoliciesText3"), CFG.COLOR_INGAME_DIPLOMACY_POINTS));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }

            @Override
            public Color getColor(boolean isActive) {
               return CFG.COLOR_INGAME_DIPLOMACY_POINTS;
            }
         }
      );
      int var6;
      menuElements.add(
         new Button_EconomicPolitic(
            CFG.langManager.get("CivilianEconomy"),
            -1,
            CFG.PADDING,
            var6 = tY + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING * 3,
            CFG.BUTTON_WIDTH,
            CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoney() >= this.costChangePolitic(1.1F)
               && CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getDiplomacyPoints() >= 7.0,
            1
         ) {
            @Override
            public int getWidth() {
               return (Menu_InGame_Politics.this.getW() - CFG.PADDING * 4) / 3;
            }

            @Override
            public Color getColor(boolean isActive) {
               return 1 == CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).civGameData.EconomyType
                  ? CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                  : (
                     this.getClickable()
                        ? (
                           1 == CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).civGameData.EconomyType
                              ? CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                              : (this.getIsHovered() ? CFG.COLOR_TEXT_CIV_INFO_HOVER : CFG.COLOR_TEXT_CIV_INFO)
                        )
                        : new Color(CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.r, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.g, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.b, 0.65F)
                  );
            }

            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).civGameData.EconomyType != 1) {
                  if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoney() <= Menu_InGame_Politics.this.costChangePolitic(1.1F)) {
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           CFG.langManager.get("NotEnoughCoins")
                              + ": "
                              + Math.abs(
                                 CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoney()
                                    - Menu_InGame_Politics.this.costChangePolitic(1.1F)
                              ),
                           CFG.COLOR_TEXT_NUM_OF_PROVINCES
                        )
                     );
                     nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_gold, CFG.PADDING, 0));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                     this.menuElementHover = new MenuElement_Hover_v2(nElements);
                  }

                  if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getDiplomacyPoints() <= 7.0) {
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           CFG.langManager.get("NotEnoughDiplomaticPoints")
                              + ": "
                              + (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getDiplomacyPoints() - 7),
                           CFG.COLOR_TEXT_NUM_OF_PROVINCES
                        )
                     );
                     nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_diplomacy_points, CFG.PADDING, 0));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                     this.menuElementHover = new MenuElement_Hover_v2(nElements);
                  }

                  nData.add(
                     new MenuElement_Hover_v2_Element_Type_Text(
                        CFG.langManager.get("EconomicPoliciesTooltipCivilian"),
                        new Color(CFG.COLOR_INGAME_GOLD.r, CFG.COLOR_INGAME_GOLD.g, CFG.COLOR_INGAME_GOLD.b, 0.375F)
                     )
                  );
                  nElements.add(new MenuElement_Hover_v2_Element2(nData));
                  nData.clear();
                  this.menuElementHover = new MenuElement_Hover_v2(nElements);
               } else {
                  nData.add(
                     new MenuElement_Hover_v2_Element_Type_Text(
                        CFG.langManager.get("EconomicPoliciesTooltipCivilian"),
                        new Color(CFG.COLOR_INGAME_GOLD.r, CFG.COLOR_INGAME_GOLD.g, CFG.COLOR_INGAME_GOLD.b, 0.375F)
                     )
                  );
                  nElements.add(new MenuElement_Hover_v2_Element2(nData));
                  nData.clear();
                  this.menuElementHover = new MenuElement_Hover_v2(nElements);
               }
            }
         }
      );
      menuElements.add(
         new Button_EconomicPolitic(
            CFG.langManager.get("MilitaryEconomy"),
            -1,
            2,
            var6,
            CFG.BUTTON_WIDTH,
            CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoney() >= this.costChangePolitic(1.1F)
               && CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getDiplomacyPoints() >= 7.0,
            2
         ) {
            @Override
            public int getPosX() {
               return CFG.PADDING * 2 + (Menu_InGame_Politics.this.getW() - CFG.PADDING * 4) / 3;
            }

            @Override
            public int getWidth() {
               return (Menu_InGame_Politics.this.getW() - CFG.PADDING * 4) / 3;
            }

            @Override
            public Color getColor(boolean isActive) {
               return 2 == CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).civGameData.EconomyType
                  ? CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                  : (
                     this.getClickable()
                        ? (
                           1 == CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).civGameData.EconomyType
                              ? CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                              : (this.getIsHovered() ? CFG.COLOR_TEXT_CIV_INFO_HOVER : CFG.COLOR_TEXT_CIV_INFO)
                        )
                        : new Color(CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.r, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.g, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.b, 0.65F)
                  );
            }

            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).civGameData.EconomyType != 2) {
                  if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoney() <= Menu_InGame_Politics.this.costChangePolitic(1.1F)) {
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           CFG.langManager.get("NotEnoughCoins")
                              + ": "
                              + Math.abs(
                                 CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoney()
                                    - Menu_InGame_Politics.this.costChangePolitic(1.1F)
                              ),
                           CFG.COLOR_TEXT_NUM_OF_PROVINCES
                        )
                     );
                     nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_gold, CFG.PADDING, 0));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                     this.menuElementHover = new MenuElement_Hover_v2(nElements);
                  }

                  if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getDiplomacyPoints() <= 7.0) {
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           CFG.langManager.get("NotEnoughDiplomaticPoints")
                              + ": "
                              + (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getDiplomacyPoints() - 7),
                           CFG.COLOR_TEXT_NUM_OF_PROVINCES
                        )
                     );
                     nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_diplomacy_points, CFG.PADDING, 0));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                     this.menuElementHover = new MenuElement_Hover_v2(nElements);
                  }

                  nData.add(
                     new MenuElement_Hover_v2_Element_Type_Text(
                        CFG.langManager.get("EconomicPoliciesTooltipMilitary"),
                        new Color(CFG.COLOR_INGAME_GOLD.r, CFG.COLOR_INGAME_GOLD.g, CFG.COLOR_INGAME_GOLD.b, 0.375F)
                     )
                  );
                  nElements.add(new MenuElement_Hover_v2_Element2(nData));
                  nData.clear();
                  this.menuElementHover = new MenuElement_Hover_v2(nElements);
               } else {
                  nData.add(
                     new MenuElement_Hover_v2_Element_Type_Text(
                        CFG.langManager.get("EconomicPoliciesTooltipMilitary"),
                        new Color(CFG.COLOR_INGAME_GOLD.r, CFG.COLOR_INGAME_GOLD.g, CFG.COLOR_INGAME_GOLD.b, 0.375F)
                     )
                  );
                  nElements.add(new MenuElement_Hover_v2_Element2(nData));
                  nData.clear();
                  this.menuElementHover = new MenuElement_Hover_v2(nElements);
               }
            }
         }
      );
      menuElements.add(
         new Button_EconomicPolitic(
            CFG.langManager.get("TechnocraticEconomy"),
            -1,
            2,
            var6,
            CFG.BUTTON_WIDTH,
            CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoney() >= this.costChangePolitic(1.1F)
               && CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getDiplomacyPoints() >= 7.0,
            3
         ) {
            @Override
            public int getPosX() {
               return Menu_InGame_Politics.this.getW() - (Menu_InGame_Politics.this.getW() - CFG.PADDING * 4) / 3 - CFG.PADDING;
            }

            @Override
            public int getWidth() {
               return (Menu_InGame_Politics.this.getW() - CFG.PADDING * 4) / 3;
            }

            @Override
            public Color getColor(boolean isActive) {
               return 3 == CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).civGameData.EconomyType
                  ? CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                  : (
                     this.getClickable()
                        ? (
                           1 == CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).civGameData.EconomyType
                              ? CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                              : (this.getIsHovered() ? CFG.COLOR_TEXT_CIV_INFO_HOVER : CFG.COLOR_TEXT_CIV_INFO)
                        )
                        : new Color(CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.r, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.g, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.b, 0.65F)
                  );
            }

            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).civGameData.EconomyType != 3) {
                  if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoney() <= Menu_InGame_Politics.this.costChangePolitic(1.1F)) {
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           CFG.langManager.get("NotEnoughCoins")
                              + ": "
                              + Math.abs(
                                 CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoney()
                                    - Menu_InGame_Politics.this.costChangePolitic(1.1F)
                              ),
                           CFG.COLOR_TEXT_NUM_OF_PROVINCES
                        )
                     );
                     nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_gold, CFG.PADDING, 0));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                     this.menuElementHover = new MenuElement_Hover_v2(nElements);
                  }

                  if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getDiplomacyPoints() <= 7.0) {
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           CFG.langManager.get("NotEnoughDiplomaticPoints")
                              + ": "
                              + (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getDiplomacyPoints() - 7),
                           CFG.COLOR_TEXT_NUM_OF_PROVINCES
                        )
                     );
                     nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_diplomacy_points, CFG.PADDING, 0));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                     this.menuElementHover = new MenuElement_Hover_v2(nElements);
                  }

                  nData.add(
                     new MenuElement_Hover_v2_Element_Type_Text(
                        CFG.langManager.get("EconomicPoliciesTooltipTechnocratic"),
                        new Color(CFG.COLOR_INGAME_GOLD.r, CFG.COLOR_INGAME_GOLD.g, CFG.COLOR_INGAME_GOLD.b, 0.375F)
                     )
                  );
                  nElements.add(new MenuElement_Hover_v2_Element2(nData));
                  nData.clear();
                  this.menuElementHover = new MenuElement_Hover_v2(nElements);
               } else {
                  nData.add(
                     new MenuElement_Hover_v2_Element_Type_Text(
                        CFG.langManager.get("EconomicPoliciesTooltipTechnocratic"),
                        new Color(CFG.COLOR_INGAME_GOLD.r, CFG.COLOR_INGAME_GOLD.g, CFG.COLOR_INGAME_GOLD.b, 0.375F)
                     )
                  );
                  nElements.add(new MenuElement_Hover_v2_Element2(nData));
                  nData.clear();
                  this.menuElementHover = new MenuElement_Hover_v2(nElements);
               }
            }
         }
      );
      menuElements.add(
         new Button_FlagActionSliderStyle(
            CFG.langManager.get("Centralization"),
            -1,
            2,
            tY = var6 + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING,
            tempWidth - 2,
            CFG.TEXT_HEIGHT + CFG.PADDING * 4,
            true
         ) {
            @Override
            public int getWidth() {
               return Menu_InGame_Politics.this.getW();
            }

            @Override
            public void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
               oSB.setColor(new Color(new Color(CFG.COLOR_INGAME_GOLD.r, CFG.COLOR_INGAME_GOLD.g, CFG.COLOR_INGAME_GOLD.b, 0.375F)));
               ImageManager.getImage(Images.gradient)
                  .draw(
                     oSB,
                     this.getPosX() + iTranslateX,
                     this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY,
                     this.getWidth(),
                     this.getHeight()
                  );
               oSB.setColor(new Color(new Color(CFG.COLOR_INGAME_GOLD.r, CFG.COLOR_INGAME_GOLD.g, CFG.COLOR_INGAME_GOLD.b, 0.375F)));
               ImageManager.getImage(Images.line_32_off1)
                  .draw(
                     oSB,
                     this.getPosX() + iTranslateX,
                     this.getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
                     this.getWidth(),
                     this.getHeight()
                  );
               oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.45F));
               ImageManager.getImage(Images.gradient)
                  .draw(
                     oSB,
                     this.getPosX() + iTranslateX,
                     this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY,
                     this.getWidth(),
                     CFG.PADDING
                  );
               oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.2F));
               ImageManager.getImage(Images.gradient)
                  .draw(
                     oSB,
                     this.getPosX() + iTranslateX,
                     this.getPosY() + this.getHeight() - CFG.PADDING - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY,
                     this.getWidth(),
                     CFG.PADDING,
                     false,
                     true
                  );
               oSB.setColor(new Color(new Color(CFG.COLOR_INGAME_GOLD.r, CFG.COLOR_INGAME_GOLD.g, CFG.COLOR_INGAME_GOLD.b, 0.375F)));
               ImageManager.getImage(Images.pix255_255_255)
                  .draw(
                     oSB,
                     this.getPosX() + iTranslateX,
                     this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY,
                     this.getWidth(),
                     1
                  );
               oSB.setColor(CFG.COLOR_NEW_GAME_EDGE_LINE);
               ImageManager.getImage(Images.slider_gradient)
                  .draw(
                     oSB,
                     this.getPosX() + iTranslateX,
                     this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY,
                     this.getWidth() / 2,
                     1
                  );
               ImageManager.getImage(Images.slider_gradient)
                  .draw(
                     oSB,
                     this.getPosX() + this.getWidth() - this.getWidth() / 2 + iTranslateX,
                     this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY,
                     this.getWidth() / 2,
                     1,
                     true,
                     false
                  );
               oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.65F));
               ImageManager.getImage(Images.slider_gradient)
                  .draw(
                     oSB,
                     this.getPosX() + iTranslateX,
                     this.getPosY() + 1 - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY,
                     this.getWidth() / 2,
                     1
                  );
               ImageManager.getImage(Images.slider_gradient)
                  .draw(
                     oSB,
                     this.getPosX() + this.getWidth() - this.getWidth() / 2 + iTranslateX,
                     this.getPosY() + 1 - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY,
                     this.getWidth() / 2,
                     1,
                     true,
                     false
                  );
               oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.55F));
               ImageManager.getImage(Images.slider_gradient)
                  .draw(
                     oSB,
                     this.getPosX() + iTranslateX,
                     this.getPosY() + this.getHeight() - 1 - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY,
                     this.getWidth() / 2,
                     1
                  );
               ImageManager.getImage(Images.slider_gradient)
                  .draw(
                     oSB,
                     this.getPosX() + this.getWidth() - this.getWidth() / 2 + iTranslateX,
                     this.getPosY() + this.getHeight() - 1 - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY,
                     this.getWidth() / 2,
                     1,
                     true,
                     false
                  );
               oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.105F));
               ImageManager.getImage(Images.gradient)
                  .draw(
                     oSB,
                     this.getPosX() + iTranslateX,
                     this.getPosY() + this.getHeight() - this.getHeight() / 3 - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY,
                     this.getWidth(),
                     this.getHeight() / 3,
                     false,
                     true
                  );
               oSB.setColor(Color.WHITE);
               CFG.fontMain.getData().setScale(0.85F);
               CFG.fontMain.getData().setScale(1.15F);
               oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.7F));
               ImageManager.getImage(Images.line_32_off1)
                  .draw(
                     oSB,
                     this.getPosX() + iTranslateX,
                     this.getPosY() + this.getHeight() - ImageManager.getImage(Images.line_32_off1).getHeight() - 1 + iTranslateY,
                     this.getWidth(),
                     1
                  );
               oSB.setColor(Color.WHITE);
            }

            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("CentralizationPoliciesText"), CFG.COLOR_AIR_YELLOW));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("CentralizationPoliciesText2"), CFG.COLOR_INGAME_DIPLOMACY_POINTS));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("CentralizationPoliciesText3"), CFG.COLOR_INGAME_DIPLOMACY_POINTS));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }

            @Override
            public Color getColor(boolean isActive) {
               return CFG.COLOR_INGAME_DIPLOMACY_POINTS;
            }
         }
      );
      int var8;
      menuElements.add(
         new Button_FlagActionSliderStyle(
            CFG.langManager.get("FullDecentralization"),
            -1,
            CFG.PADDING,
            var8 = tY + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING * 2,
            CFG.BUTTON_WIDTH,
            CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).civGameData.LevelCentralization == 2
               && CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoney() >= this.costChangePolitic(0.43F)
               && CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getDiplomacyPoints() >= 6.0
         ) {
            @Override
            public int getWidth() {
               return (Menu_InGame_Politics.this.getW() - CFG.PADDING * 4) / 3;
            }

            @Override
            public Color getColor(boolean isActive) {
               return 1 == CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).civGameData.LevelCentralization
                  ? CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                  : (
                     this.getClickable()
                        ? (
                           1 == CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).civGameData.LevelCentralization
                              ? CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                              : (this.getIsHovered() ? CFG.COLOR_TEXT_CIV_INFO_HOVER : CFG.COLOR_TEXT_CIV_INFO)
                        )
                        : new Color(CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.r, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.g, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.b, 0.65F)
                  );
            }

            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).civGameData.LevelCentralization != 1) {
                  if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoney() <= Menu_InGame_Politics.this.costChangePolitic(0.43F)) {
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           CFG.langManager.get("NotEnoughCoins")
                              + ": "
                              + Math.abs(
                                 CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoney()
                                    - Menu_InGame_Politics.this.costChangePolitic(0.43F)
                              ),
                           CFG.COLOR_TEXT_NUM_OF_PROVINCES
                        )
                     );
                     nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_gold, CFG.PADDING, 0));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                     this.menuElementHover = new MenuElement_Hover_v2(nElements);
                  }

                  if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getDiplomacyPoints() <= 6.0) {
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           CFG.langManager.get("NotEnoughDiplomaticPoints")
                              + ": "
                              + (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getDiplomacyPoints() - 6),
                           CFG.COLOR_TEXT_NUM_OF_PROVINCES
                        )
                     );
                     nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_diplomacy_points, CFG.PADDING, 0));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                     this.menuElementHover = new MenuElement_Hover_v2(nElements);
                  }
               }
            }
         }
      );
      menuElements.add(
         new Button_FlagActionSliderStyle(
            CFG.langManager.get("PartialCentralization"),
            -1,
            2,
            var8,
            CFG.BUTTON_WIDTH,
            (
                  CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).civGameData.LevelCentralization == 1
                     || CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).civGameData.LevelCentralization == 3
               )
               && CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoney() >= this.costChangePolitic(0.55F)
               && CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getDiplomacyPoints() >= 6.0
         ) {
            @Override
            public int getPosX() {
               return CFG.PADDING * 2 + (Menu_InGame_Politics.this.getW() - CFG.PADDING * 4) / 3;
            }

            @Override
            public int getWidth() {
               return (Menu_InGame_Politics.this.getW() - CFG.PADDING * 4) / 3;
            }

            @Override
            public Color getColor(boolean isActive) {
               return 2 == CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).civGameData.LevelCentralization
                  ? CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                  : (
                     this.getClickable()
                        ? (
                           2 == CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).civGameData.LevelCentralization
                              ? CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                              : (this.getIsHovered() ? CFG.COLOR_TEXT_CIV_INFO_HOVER : CFG.COLOR_TEXT_CIV_INFO)
                        )
                        : new Color(CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.r, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.g, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.b, 0.65F)
                  );
            }

            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).civGameData.LevelCentralization != 2) {
                  if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoney() <= Menu_InGame_Politics.this.costChangePolitic(0.55F)) {
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           CFG.langManager.get("NotEnoughCoins")
                              + ": "
                              + Math.abs(
                                 CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoney()
                                    - Menu_InGame_Politics.this.costChangePolitic(0.55F)
                              ),
                           CFG.COLOR_TEXT_NUM_OF_PROVINCES
                        )
                     );
                     nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_gold, CFG.PADDING, 0));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                     this.menuElementHover = new MenuElement_Hover_v2(nElements);
                  }

                  if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getDiplomacyPoints() <= 6.0) {
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           CFG.langManager.get("NotEnoughDiplomaticPoints")
                              + ": "
                              + (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getDiplomacyPoints() - 6),
                           CFG.COLOR_TEXT_NUM_OF_PROVINCES
                        )
                     );
                     nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_diplomacy_points, CFG.PADDING, 0));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                     this.menuElementHover = new MenuElement_Hover_v2(nElements);
                  }
               }
            }
         }
      );
      menuElements.add(
         new Button_FlagActionSliderStyle(
            CFG.langManager.get("MediumCentralization"),
            -1,
            2,
            var8,
            CFG.BUTTON_WIDTH,
            (
                  CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).civGameData.LevelCentralization == 2
                     || CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).civGameData.LevelCentralization == 4
               )
               && CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoney() >= this.costChangePolitic(0.75F)
               && CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getDiplomacyPoints() >= 6.0
         ) {
            @Override
            public int getPosX() {
               return Menu_InGame_Politics.this.getW() - (Menu_InGame_Politics.this.getW() - CFG.PADDING * 4) / 3 - CFG.PADDING;
            }

            @Override
            public int getWidth() {
               return (Menu_InGame_Politics.this.getW() - CFG.PADDING * 4) / 3;
            }

            @Override
            public Color getColor(boolean isActive) {
               return 3 == CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).civGameData.LevelCentralization
                  ? CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                  : (
                     this.getClickable()
                        ? (
                           3 == CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).civGameData.LevelCentralization
                              ? CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                              : (this.getIsHovered() ? CFG.COLOR_TEXT_CIV_INFO_HOVER : CFG.COLOR_TEXT_CIV_INFO)
                        )
                        : new Color(CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.r, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.g, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.b, 0.65F)
                  );
            }

            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).civGameData.LevelCentralization != 3) {
                  if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoney() <= Menu_InGame_Politics.this.costChangePolitic(0.75F)) {
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           CFG.langManager.get("NotEnoughCoins")
                              + ": "
                              + Math.abs(
                                 CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoney()
                                    - Menu_InGame_Politics.this.costChangePolitic(0.75F)
                              ),
                           CFG.COLOR_TEXT_NUM_OF_PROVINCES
                        )
                     );
                     nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_gold, CFG.PADDING, 0));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                     this.menuElementHover = new MenuElement_Hover_v2(nElements);
                  }

                  if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getDiplomacyPoints() <= 6.0) {
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           CFG.langManager.get("NotEnoughDiplomaticPoints")
                              + ": "
                              + (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getDiplomacyPoints() - 6),
                           CFG.COLOR_TEXT_NUM_OF_PROVINCES
                        )
                     );
                     nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_diplomacy_points, CFG.PADDING, 0));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                     this.menuElementHover = new MenuElement_Hover_v2(nElements);
                  }
               }
            }
         }
      );
      menuElements.add(
         new Button_FlagActionSliderStyle(
            CFG.langManager.get("HighCentralization"),
            -1,
            2,
            tY = var8 + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING,
            CFG.BUTTON_WIDTH,
            (
                  CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).civGameData.LevelCentralization == 3
                     || CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).civGameData.LevelCentralization == 5
               )
               && CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoney() >= this.costChangePolitic(0.9F)
               && CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getDiplomacyPoints() >= 6.0
         ) {
            @Override
            public int getWidth() {
               return (Menu_InGame_Politics.this.getW() - CFG.PADDING * 4) / 3;
            }

            @Override
            public Color getColor(boolean isActive) {
               return 4 == CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).civGameData.LevelCentralization
                  ? CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                  : (
                     this.getClickable()
                        ? (
                           4 == CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).civGameData.LevelCentralization
                              ? CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                              : (this.getIsHovered() ? CFG.COLOR_TEXT_CIV_INFO_HOVER : CFG.COLOR_TEXT_CIV_INFO)
                        )
                        : new Color(CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.r, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.g, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.b, 0.65F)
                  );
            }

            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).civGameData.LevelCentralization != 4) {
                  if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoney() <= Menu_InGame_Politics.this.costChangePolitic(0.9F)) {
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           CFG.langManager.get("NotEnoughCoins")
                              + ": "
                              + Math.abs(
                                 CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoney()
                                    - Menu_InGame_Politics.this.costChangePolitic(0.9F)
                              ),
                           CFG.COLOR_TEXT_NUM_OF_PROVINCES
                        )
                     );
                     nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_gold, CFG.PADDING, 0));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                     this.menuElementHover = new MenuElement_Hover_v2(nElements);
                  }

                  if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getDiplomacyPoints() <= 6.0) {
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           CFG.langManager.get("NotEnoughDiplomaticPoints")
                              + ": "
                              + (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getDiplomacyPoints() - 6),
                           CFG.COLOR_TEXT_NUM_OF_PROVINCES
                        )
                     );
                     nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_diplomacy_points, CFG.PADDING, 0));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                     this.menuElementHover = new MenuElement_Hover_v2(nElements);
                  }
               }
            }
         }
      );
      menuElements.add(
         new Button_FlagActionSliderStyle(
            CFG.langManager.get("MaximumCentralization"),
            -1,
            2,
            tY,
            CFG.BUTTON_WIDTH,
            CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).civGameData.LevelCentralization == 4
               && CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoney() >= this.costChangePolitic(1.03F)
               && CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getDiplomacyPoints() >= 6.0
         ) {
            @Override
            public int getPosX() {
               return CFG.PADDING * 2 + (Menu_InGame_Politics.this.getW() - CFG.PADDING * 4) / 3;
            }

            @Override
            public int getWidth() {
               return (Menu_InGame_Politics.this.getW() - CFG.PADDING * 4) / 3;
            }

            @Override
            public Color getColor(boolean isActive) {
               return 5 == CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).civGameData.LevelCentralization
                  ? CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                  : (
                     this.getClickable()
                        ? (
                           5 == CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).civGameData.LevelCentralization
                              ? CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                              : (this.getIsHovered() ? CFG.COLOR_TEXT_CIV_INFO_HOVER : CFG.COLOR_TEXT_CIV_INFO)
                        )
                        : new Color(CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.r, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.g, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.b, 0.65F)
                  );
            }

            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).civGameData.LevelCentralization != 5) {
                  if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoney() <= Menu_InGame_Politics.this.costChangePolitic(1.03F)) {
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           CFG.langManager.get("NotEnoughCoins")
                              + ": "
                              + Math.abs(
                                 CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoney()
                                    - Menu_InGame_Politics.this.costChangePolitic(1.03F)
                              ),
                           CFG.COLOR_TEXT_NUM_OF_PROVINCES
                        )
                     );
                     nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_gold, CFG.PADDING, 0));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                     this.menuElementHover = new MenuElement_Hover_v2(nElements);
                  }

                  if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getDiplomacyPoints() <= 6.0) {
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           CFG.langManager.get("NotEnoughDiplomaticPoints")
                              + ": "
                              + (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getDiplomacyPoints() - 6),
                           CFG.COLOR_TEXT_NUM_OF_PROVINCES
                        )
                     );
                     nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_diplomacy_points, CFG.PADDING, 0));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                     this.menuElementHover = new MenuElement_Hover_v2(nElements);
                  }
               }
            }
         }
      );
      int var10;
      menuElements.add(
         new Button_FlagActionSliderStyle(
            CFG.langManager.get("Medicine"),
            -1,
            2,
            var10 = tY + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING,
            tempWidth - 2,
            CFG.TEXT_HEIGHT + CFG.PADDING * 4,
            true
         ) {
            @Override
            public int getWidth() {
               return Menu_InGame_Politics.this.getW();
            }

            @Override
            public void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
               oSB.setColor(
                  new Color(new Color(CFG.COLOR_TEXT_MODIFIER_POSITIVE.r, CFG.COLOR_TEXT_MODIFIER_POSITIVE.g, CFG.COLOR_TEXT_MODIFIER_POSITIVE.b, 0.375F))
               );
               ImageManager.getImage(Images.gradient)
                  .draw(
                     oSB,
                     this.getPosX() + iTranslateX,
                     this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY,
                     this.getWidth(),
                     this.getHeight()
                  );
               oSB.setColor(
                  new Color(new Color(CFG.COLOR_TEXT_MODIFIER_POSITIVE.r, CFG.COLOR_TEXT_MODIFIER_POSITIVE.g, CFG.COLOR_TEXT_MODIFIER_POSITIVE.b, 0.375F))
               );
               ImageManager.getImage(Images.line_32_off1)
                  .draw(
                     oSB,
                     this.getPosX() + iTranslateX,
                     this.getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
                     this.getWidth(),
                     this.getHeight()
                  );
               oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.45F));
               ImageManager.getImage(Images.gradient)
                  .draw(
                     oSB,
                     this.getPosX() + iTranslateX,
                     this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY,
                     this.getWidth(),
                     CFG.PADDING
                  );
               oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.2F));
               ImageManager.getImage(Images.gradient)
                  .draw(
                     oSB,
                     this.getPosX() + iTranslateX,
                     this.getPosY() + this.getHeight() - CFG.PADDING - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY,
                     this.getWidth(),
                     CFG.PADDING,
                     false,
                     true
                  );
               oSB.setColor(
                  new Color(new Color(CFG.COLOR_TEXT_MODIFIER_POSITIVE.r, CFG.COLOR_TEXT_MODIFIER_POSITIVE.g, CFG.COLOR_TEXT_MODIFIER_POSITIVE.b, 0.375F))
               );
               ImageManager.getImage(Images.pix255_255_255)
                  .draw(
                     oSB,
                     this.getPosX() + iTranslateX,
                     this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY,
                     this.getWidth(),
                     1
                  );
               oSB.setColor(CFG.COLOR_NEW_GAME_EDGE_LINE);
               ImageManager.getImage(Images.slider_gradient)
                  .draw(
                     oSB,
                     this.getPosX() + iTranslateX,
                     this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY,
                     this.getWidth() / 2,
                     1
                  );
               ImageManager.getImage(Images.slider_gradient)
                  .draw(
                     oSB,
                     this.getPosX() + this.getWidth() - this.getWidth() / 2 + iTranslateX,
                     this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY,
                     this.getWidth() / 2,
                     1,
                     true,
                     false
                  );
               oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.65F));
               ImageManager.getImage(Images.slider_gradient)
                  .draw(
                     oSB,
                     this.getPosX() + iTranslateX,
                     this.getPosY() + 1 - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY,
                     this.getWidth() / 2,
                     1
                  );
               ImageManager.getImage(Images.slider_gradient)
                  .draw(
                     oSB,
                     this.getPosX() + this.getWidth() - this.getWidth() / 2 + iTranslateX,
                     this.getPosY() + 1 - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY,
                     this.getWidth() / 2,
                     1,
                     true,
                     false
                  );
               oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.55F));
               ImageManager.getImage(Images.slider_gradient)
                  .draw(
                     oSB,
                     this.getPosX() + iTranslateX,
                     this.getPosY() + this.getHeight() - 1 - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY,
                     this.getWidth() / 2,
                     1
                  );
               ImageManager.getImage(Images.slider_gradient)
                  .draw(
                     oSB,
                     this.getPosX() + this.getWidth() - this.getWidth() / 2 + iTranslateX,
                     this.getPosY() + this.getHeight() - 1 - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY,
                     this.getWidth() / 2,
                     1,
                     true,
                     false
                  );
               oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.105F));
               ImageManager.getImage(Images.gradient)
                  .draw(
                     oSB,
                     this.getPosX() + iTranslateX,
                     this.getPosY() + this.getHeight() - this.getHeight() / 3 - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY,
                     this.getWidth(),
                     this.getHeight() / 3,
                     false,
                     true
                  );
               oSB.setColor(Color.WHITE);
               CFG.fontMain.getData().setScale(0.85F);
               CFG.fontMain.getData().setScale(1.15F);
               oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.7F));
               ImageManager.getImage(Images.line_32_off1)
                  .draw(
                     oSB,
                     this.getPosX() + iTranslateX,
                     this.getPosY() + this.getHeight() - ImageManager.getImage(Images.line_32_off1).getHeight() - 1 + iTranslateY,
                     this.getWidth(),
                     1
                  );
               oSB.setColor(Color.WHITE);
            }

            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               nData.add(
                  new MenuElement_Hover_v2_Element_Type_Text(
                     CFG.langManager.get("MedicinePoliciesText"),
                     new Color(CFG.COLOR_TEXT_MODIFIER_POSITIVE.r, CFG.COLOR_TEXT_MODIFIER_POSITIVE.g, CFG.COLOR_TEXT_MODIFIER_POSITIVE.b, 0.375F)
                  )
               );
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("MedicinePoliciesText2"), CFG.COLOR_INGAME_DIPLOMACY_POINTS));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("MedicinePoliciesText3"), CFG.COLOR_INGAME_DIPLOMACY_POINTS));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }

            @Override
            public Color getColor(boolean isActive) {
               return CFG.COLOR_INGAME_DIPLOMACY_POINTS;
            }
         }
      );
      menuElements.add(
         new Button_FlagActionSliderStyle(
            CFG.langManager.get("FolkMedicine"),
            -1,
            CFG.PADDING,
            tY = var10 + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING * 2,
            CFG.BUTTON_WIDTH,
            (
                  CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).civGameData.LevelMedicine == 2
                     || CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).civGameData.LevelMedicine == 3
               )
               && CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoney() >= this.costChangePolitic(0.65F)
               && CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getDiplomacyPoints() >= 6.0
         ) {
            @Override
            public int getWidth() {
               return (Menu_InGame_Politics.this.getW() - CFG.PADDING * 4) / 2;
            }

            @Override
            public Color getColor(boolean isActive) {
               return 1 == CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).civGameData.LevelMedicine
                  ? CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                  : (
                     this.getClickable()
                        ? (
                           1 == CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).civGameData.LevelMedicine
                              ? CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                              : (this.getIsHovered() ? CFG.COLOR_TEXT_CIV_INFO_HOVER : CFG.COLOR_TEXT_CIV_INFO)
                        )
                        : new Color(CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.r, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.g, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.b, 0.65F)
                  );
            }

            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).civGameData.LevelMedicine != 1) {
                  if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoney() <= Menu_InGame_Politics.this.costChangePolitic(0.65F)) {
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           CFG.langManager.get("NotEnoughCoins")
                              + ": "
                              + Math.abs(
                                 CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoney()
                                    - Menu_InGame_Politics.this.costChangePolitic(0.65F)
                              ),
                           CFG.COLOR_TEXT_NUM_OF_PROVINCES
                        )
                     );
                     nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_gold, CFG.PADDING, 0));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                     this.menuElementHover = new MenuElement_Hover_v2(nElements);
                  }

                  if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getDiplomacyPoints() <= 5.0) {
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           CFG.langManager.get("NotEnoughDiplomaticPoints")
                              + ": "
                              + (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getDiplomacyPoints() - 5),
                           CFG.COLOR_TEXT_NUM_OF_PROVINCES
                        )
                     );
                     nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_diplomacy_points, CFG.PADDING, 0));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                     this.menuElementHover = new MenuElement_Hover_v2(nElements);
                  }
               }
            }
         }
      );
      menuElements.add(
         new Button_FlagActionSliderStyle(
            CFG.langManager.get("LowLevelOfMedicine"),
            -1,
            2,
            tY,
            CFG.BUTTON_WIDTH,
            CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoney() >= this.costChangePolitic(0.85F)
               && CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getDiplomacyPoints() >= 6.0
         ) {
            @Override
            public int getPosX() {
               return CFG.PADDING * 2 + (Menu_InGame_Politics.this.getW() - CFG.PADDING * 4) / 2;
            }

            @Override
            public int getWidth() {
               return (Menu_InGame_Politics.this.getW() - CFG.PADDING * 4) / 2;
            }

            @Override
            public Color getColor(boolean isActive) {
               return 2 == CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).civGameData.LevelMedicine
                  ? CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                  : (
                     this.getClickable()
                        ? (
                           2 == CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).civGameData.LevelMedicine
                              ? CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                              : (this.getIsHovered() ? CFG.COLOR_TEXT_CIV_INFO_HOVER : CFG.COLOR_TEXT_CIV_INFO)
                        )
                        : new Color(CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.r, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.g, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.b, 0.65F)
                  );
            }

            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).civGameData.LevelMedicine != 2) {
                  if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoney() <= Menu_InGame_Politics.this.costChangePolitic(0.85F)) {
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           CFG.langManager.get("NotEnoughCoins")
                              + ": "
                              + Math.abs(
                                 CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoney()
                                    - Menu_InGame_Politics.this.costChangePolitic(0.85F)
                              ),
                           CFG.COLOR_TEXT_NUM_OF_PROVINCES
                        )
                     );
                     nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_gold, CFG.PADDING, 0));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                     this.menuElementHover = new MenuElement_Hover_v2(nElements);
                  }

                  if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getDiplomacyPoints() <= 5.0) {
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           CFG.langManager.get("NotEnoughDiplomaticPoints")
                              + ": "
                              + (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getDiplomacyPoints() - 5),
                           CFG.COLOR_TEXT_NUM_OF_PROVINCES
                        )
                     );
                     nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_diplomacy_points, CFG.PADDING, 0));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                     this.menuElementHover = new MenuElement_Hover_v2(nElements);
                  }
               }
            }
         }
      );
      int var12;
      menuElements.add(
         new Button_FlagActionSliderStyle(
            CFG.langManager.get("MediumLevelOfMedicine"),
            -1,
            2,
            var12 = tY + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING,
            CFG.BUTTON_WIDTH,
            CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoney() >= this.costChangePolitic(1.0F)
               && CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getDiplomacyPoints() >= 6.0
         ) {
            @Override
            public int getWidth() {
               return (Menu_InGame_Politics.this.getW() - CFG.PADDING * 4) / 2;
            }

            @Override
            public Color getColor(boolean isActive) {
               return 3 == CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).civGameData.LevelMedicine
                  ? CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                  : (
                     this.getClickable()
                        ? (
                           3 == CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).civGameData.LevelMedicine
                              ? CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                              : (this.getIsHovered() ? CFG.COLOR_TEXT_CIV_INFO_HOVER : CFG.COLOR_TEXT_CIV_INFO)
                        )
                        : new Color(CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.r, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.g, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.b, 0.65F)
                  );
            }

            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).civGameData.LevelMedicine != 3) {
                  if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoney() <= Menu_InGame_Politics.this.costChangePolitic(1.0F)) {
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           CFG.langManager.get("NotEnoughCoins")
                              + ": "
                              + Math.abs(
                                 CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoney()
                                    - Menu_InGame_Politics.this.costChangePolitic(1.0F)
                              ),
                           CFG.COLOR_TEXT_NUM_OF_PROVINCES
                        )
                     );
                     nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_gold, CFG.PADDING, 0));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                     this.menuElementHover = new MenuElement_Hover_v2(nElements);
                  }

                  if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getDiplomacyPoints() <= 5.0) {
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           CFG.langManager.get("NotEnoughDiplomaticPoints")
                              + ": "
                              + (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getDiplomacyPoints() - 5),
                           CFG.COLOR_TEXT_NUM_OF_PROVINCES
                        )
                     );
                     nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_diplomacy_points, CFG.PADDING, 0));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                     this.menuElementHover = new MenuElement_Hover_v2(nElements);
                  }
               }
            }
         }
      );
      menuElements.add(
         new Button_FlagActionSliderStyle(
            CFG.langManager.get("HighLevelOfMedicine"),
            -1,
            2,
            var12,
            CFG.BUTTON_WIDTH,
            (
                  CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).civGameData.LevelMedicine == 2
                     || CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).civGameData.LevelMedicine == 3
               )
               && CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoney() >= this.costChangePolitic(1.097F)
               && CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getDiplomacyPoints() >= 6.0
         ) {
            @Override
            public int getPosX() {
               return CFG.PADDING * 2 + (Menu_InGame_Politics.this.getW() - CFG.PADDING * 4) / 2;
            }

            @Override
            public int getWidth() {
               return (Menu_InGame_Politics.this.getW() - CFG.PADDING * 4) / 2;
            }

            @Override
            public Color getColor(boolean isActive) {
               return 4 == CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).civGameData.LevelMedicine
                  ? CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                  : (
                     this.getClickable()
                        ? (
                           4 == CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).civGameData.LevelMedicine
                              ? CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                              : (this.getIsHovered() ? CFG.COLOR_TEXT_CIV_INFO_HOVER : CFG.COLOR_TEXT_CIV_INFO)
                        )
                        : new Color(CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.r, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.g, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.b, 0.65F)
                  );
            }

            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).civGameData.LevelMedicine != 4) {
                  if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoney() <= Menu_InGame_Politics.this.costChangePolitic(1.097F)) {
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           CFG.langManager.get("NotEnoughCoins")
                              + ": "
                              + Math.abs(
                                 CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoney()
                                    - Menu_InGame_Politics.this.costChangePolitic(1.097F)
                              ),
                           CFG.COLOR_TEXT_NUM_OF_PROVINCES
                        )
                     );
                     nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_gold, CFG.PADDING, 0));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                     this.menuElementHover = new MenuElement_Hover_v2(nElements);
                  }

                  if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getDiplomacyPoints() <= 5.0) {
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           CFG.langManager.get("NotEnoughDiplomaticPoints")
                              + ": "
                              + (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getDiplomacyPoints() - 5),
                           CFG.COLOR_TEXT_NUM_OF_PROVINCES
                        )
                     );
                     nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_diplomacy_points, CFG.PADDING, 0));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                     this.menuElementHover = new MenuElement_Hover_v2(nElements);
                  }
               }
            }
         }
      );
      menuElements.add(
         new Button_FlagActionSliderStyle(
            CFG.langManager.get("EducationBudget"),
            -1,
            2,
            tY = var12 + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING,
            tempWidth - 2,
            CFG.TEXT_HEIGHT + CFG.PADDING * 4,
            true
         ) {
            @Override
            public int getWidth() {
               return Menu_InGame_Politics.this.getW();
            }

            @Override
            public void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
               oSB.setColor(new Color(new Color(220.0F, 20.0F, 60.0F, 0.275F)));
               ImageManager.getImage(Images.gradient)
                  .draw(
                     oSB,
                     this.getPosX() + iTranslateX,
                     this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY,
                     this.getWidth(),
                     this.getHeight()
                  );
               oSB.setColor(new Color(new Color(220.0F, 20.0F, 60.0F, 0.275F)));
               ImageManager.getImage(Images.line_32_off1)
                  .draw(
                     oSB,
                     this.getPosX() + iTranslateX,
                     this.getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
                     this.getWidth(),
                     this.getHeight()
                  );
               oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.45F));
               ImageManager.getImage(Images.gradient)
                  .draw(
                     oSB,
                     this.getPosX() + iTranslateX,
                     this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY,
                     this.getWidth(),
                     CFG.PADDING
                  );
               oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.2F));
               ImageManager.getImage(Images.gradient)
                  .draw(
                     oSB,
                     this.getPosX() + iTranslateX,
                     this.getPosY() + this.getHeight() - CFG.PADDING - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY,
                     this.getWidth(),
                     CFG.PADDING,
                     false,
                     true
                  );
               oSB.setColor(new Color(new Color(220.0F, 20.0F, 60.0F, 0.275F)));
               ImageManager.getImage(Images.pix255_255_255)
                  .draw(
                     oSB,
                     this.getPosX() + iTranslateX,
                     this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY,
                     this.getWidth(),
                     1
                  );
               oSB.setColor(CFG.COLOR_NEW_GAME_EDGE_LINE);
               ImageManager.getImage(Images.slider_gradient)
                  .draw(
                     oSB,
                     this.getPosX() + iTranslateX,
                     this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY,
                     this.getWidth() / 2,
                     1
                  );
               ImageManager.getImage(Images.slider_gradient)
                  .draw(
                     oSB,
                     this.getPosX() + this.getWidth() - this.getWidth() / 2 + iTranslateX,
                     this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY,
                     this.getWidth() / 2,
                     1,
                     true,
                     false
                  );
               oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.65F));
               ImageManager.getImage(Images.slider_gradient)
                  .draw(
                     oSB,
                     this.getPosX() + iTranslateX,
                     this.getPosY() + 1 - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY,
                     this.getWidth() / 2,
                     1
                  );
               ImageManager.getImage(Images.slider_gradient)
                  .draw(
                     oSB,
                     this.getPosX() + this.getWidth() - this.getWidth() / 2 + iTranslateX,
                     this.getPosY() + 1 - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY,
                     this.getWidth() / 2,
                     1,
                     true,
                     false
                  );
               oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.55F));
               ImageManager.getImage(Images.slider_gradient)
                  .draw(
                     oSB,
                     this.getPosX() + iTranslateX,
                     this.getPosY() + this.getHeight() - 1 - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY,
                     this.getWidth() / 2,
                     1
                  );
               ImageManager.getImage(Images.slider_gradient)
                  .draw(
                     oSB,
                     this.getPosX() + this.getWidth() - this.getWidth() / 2 + iTranslateX,
                     this.getPosY() + this.getHeight() - 1 - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY,
                     this.getWidth() / 2,
                     1,
                     true,
                     false
                  );
               oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.105F));
               ImageManager.getImage(Images.gradient)
                  .draw(
                     oSB,
                     this.getPosX() + iTranslateX,
                     this.getPosY() + this.getHeight() - this.getHeight() / 3 - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY,
                     this.getWidth(),
                     this.getHeight() / 3,
                     false,
                     true
                  );
               oSB.setColor(Color.WHITE);
               CFG.fontMain.getData().setScale(0.85F);
               CFG.fontMain.getData().setScale(1.15F);
               oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.7F));
               ImageManager.getImage(Images.line_32_off1)
                  .draw(
                     oSB,
                     this.getPosX() + iTranslateX,
                     this.getPosY() + this.getHeight() - ImageManager.getImage(Images.line_32_off1).getHeight() - 1 + iTranslateY,
                     this.getWidth(),
                     1
                  );
               oSB.setColor(Color.WHITE);
            }

            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("EducationPoliciesText"), CFG.COLOR_INGAME_DIPLOMACY_POINTS));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("EducationPoliciesText2"), CFG.COLOR_INGAME_DIPLOMACY_POINTS));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("EducationPoliciesText3"), CFG.COLOR_INGAME_DIPLOMACY_POINTS));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }

            @Override
            public Color getColor(boolean isActive) {
               return CFG.COLOR_INGAME_DIPLOMACY_POINTS;
            }
         }
      );
      int var14;
      menuElements.add(
         new Button_FlagActionSliderStyle(
            CFG.langManager.get("LackOfEducation"),
            -1,
            CFG.PADDING,
            var14 = tY + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING * 2,
            CFG.BUTTON_WIDTH,
            (
                  CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).civGameData.LevelEducation == 2
                     || CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).civGameData.LevelEducation == 3
               )
               && CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoney() >= this.costChangePolitic(0.4F)
               && CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getDiplomacyPoints() >= 6.0
         ) {
            @Override
            public int getWidth() {
               return (Menu_InGame_Politics.this.getW() - CFG.PADDING * 4) / 2;
            }

            @Override
            public Color getColor(boolean isActive) {
               return 1 == CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).civGameData.LevelEducation
                  ? CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                  : (
                     this.getClickable()
                        ? (
                           1 == CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).civGameData.LevelEducation
                              ? CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                              : (this.getIsHovered() ? CFG.COLOR_TEXT_CIV_INFO_HOVER : CFG.COLOR_TEXT_CIV_INFO)
                        )
                        : new Color(CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.r, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.g, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.b, 0.65F)
                  );
            }

            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).civGameData.LevelEducation != 1) {
                  if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoney() <= Menu_InGame_Politics.this.costChangePolitic(0.4F)) {
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           CFG.langManager.get("NotEnoughCoins")
                              + ": "
                              + Math.abs(
                                 CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoney()
                                    - Menu_InGame_Politics.this.costChangePolitic(0.4F)
                              ),
                           CFG.COLOR_TEXT_NUM_OF_PROVINCES
                        )
                     );
                     nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_gold, CFG.PADDING, 0));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                     this.menuElementHover = new MenuElement_Hover_v2(nElements);
                  }

                  if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getDiplomacyPoints() <= 5.0) {
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           CFG.langManager.get("NotEnoughDiplomaticPoints")
                              + ": "
                              + (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getDiplomacyPoints() - 5),
                           CFG.COLOR_TEXT_NUM_OF_PROVINCES
                        )
                     );
                     nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_diplomacy_points, CFG.PADDING, 0));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                     this.menuElementHover = new MenuElement_Hover_v2(nElements);
                  }
               }
            }
         }
      );
      menuElements.add(
         new Button_FlagActionSliderStyle(
            CFG.langManager.get("BasicLiteracyAndKnowledge"),
            -1,
            2,
            var14,
            CFG.BUTTON_WIDTH,
            CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoney() >= this.costChangePolitic(0.65F)
               && CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getDiplomacyPoints() >= 6.0
         ) {
            @Override
            public int getPosX() {
               return CFG.PADDING * 2 + (Menu_InGame_Politics.this.getW() - CFG.PADDING * 4) / 2;
            }

            @Override
            public int getWidth() {
               return (Menu_InGame_Politics.this.getW() - CFG.PADDING * 4) / 2;
            }

            @Override
            public Color getColor(boolean isActive) {
               return 2 == CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).civGameData.LevelEducation
                  ? CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                  : (
                     this.getClickable()
                        ? (
                           2 == CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).civGameData.LevelEducation
                              ? CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                              : (this.getIsHovered() ? CFG.COLOR_TEXT_CIV_INFO_HOVER : CFG.COLOR_TEXT_CIV_INFO)
                        )
                        : new Color(CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.r, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.g, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.b, 0.65F)
                  );
            }

            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).civGameData.LevelEducation != 2) {
                  if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoney() <= Menu_InGame_Politics.this.costChangePolitic(0.65F)) {
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           CFG.langManager.get("NotEnoughCoins")
                              + ": "
                              + Math.abs(
                                 CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoney()
                                    - Menu_InGame_Politics.this.costChangePolitic(0.65F)
                              ),
                           CFG.COLOR_TEXT_NUM_OF_PROVINCES
                        )
                     );
                     nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_gold, CFG.PADDING, 0));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                     this.menuElementHover = new MenuElement_Hover_v2(nElements);
                  }

                  if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getDiplomacyPoints() <= 5.0) {
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           CFG.langManager.get("NotEnoughDiplomaticPoints")
                              + ": "
                              + (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getDiplomacyPoints() - 5),
                           CFG.COLOR_TEXT_NUM_OF_PROVINCES
                        )
                     );
                     nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_diplomacy_points, CFG.PADDING, 0));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                     this.menuElementHover = new MenuElement_Hover_v2(nElements);
                  }
               }
            }
         }
      );
      menuElements.add(
         new Button_FlagActionSliderStyle(
            CFG.langManager.get("BasicEducation"),
            -1,
            2,
            tY = var14 + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING * 2,
            CFG.BUTTON_WIDTH,
            CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoney() >= this.costChangePolitic(0.8F)
               && CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getDiplomacyPoints() >= 5.0
         ) {
            @Override
            public int getWidth() {
               return (Menu_InGame_Politics.this.getW() - CFG.PADDING * 4) / 2;
            }

            @Override
            public Color getColor(boolean isActive) {
               return 3 == CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).civGameData.LevelEducation
                  ? CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                  : (
                     this.getClickable()
                        ? (
                           3 == CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).civGameData.LevelEducation
                              ? CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                              : (this.getIsHovered() ? CFG.COLOR_TEXT_CIV_INFO_HOVER : CFG.COLOR_TEXT_CIV_INFO)
                        )
                        : new Color(CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.r, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.g, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.b, 0.65F)
                  );
            }

            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).civGameData.LevelEducation != 3) {
                  if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoney() <= Menu_InGame_Politics.this.costChangePolitic(0.8F)) {
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           CFG.langManager.get("NotEnoughCoins")
                              + ": "
                              + Math.abs(
                                 CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoney()
                                    - Menu_InGame_Politics.this.costChangePolitic(0.8F)
                              ),
                           CFG.COLOR_TEXT_NUM_OF_PROVINCES
                        )
                     );
                     nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_gold, CFG.PADDING, 0));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                     this.menuElementHover = new MenuElement_Hover_v2(nElements);
                  }

                  if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getDiplomacyPoints() <= 5.0) {
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           CFG.langManager.get("NotEnoughDiplomaticPoints")
                              + ": "
                              + (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getDiplomacyPoints() - 5),
                           CFG.COLOR_TEXT_NUM_OF_PROVINCES
                        )
                     );
                     nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_diplomacy_points, CFG.PADDING, 0));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                     this.menuElementHover = new MenuElement_Hover_v2(nElements);
                  }
               }
            }
         }
      );
      menuElements.add(
         new Button_FlagActionSliderStyle(
            CFG.langManager.get("SupportForHigherEducation"),
            -1,
            2,
            tY,
            CFG.BUTTON_WIDTH,
            (
                  CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).civGameData.LevelEducation == 2
                     || CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).civGameData.LevelEducation == 3
               )
               && CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoney() >= this.costChangePolitic(1.0F)
               && CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getDiplomacyPoints() >= 5.0
         ) {
            @Override
            public int getPosX() {
               return CFG.PADDING * 2 + (Menu_InGame_Politics.this.getW() - CFG.PADDING * 4) / 2;
            }

            @Override
            public int getWidth() {
               return (Menu_InGame_Politics.this.getW() - CFG.PADDING * 4) / 2;
            }

            @Override
            public Color getColor(boolean isActive) {
               return 4 == CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).civGameData.LevelEducation
                  ? CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                  : (
                     this.getClickable()
                        ? (
                           4 == CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).civGameData.LevelEducation
                              ? CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                              : (this.getIsHovered() ? CFG.COLOR_TEXT_CIV_INFO_HOVER : CFG.COLOR_TEXT_CIV_INFO)
                        )
                        : new Color(CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.r, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.g, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.b, 0.65F)
                  );
            }

            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).civGameData.LevelEducation != 4) {
                  if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoney() <= Menu_InGame_Politics.this.costChangePolitic(1.0F)) {
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           CFG.langManager.get("NotEnoughCoins")
                              + ": "
                              + Math.abs(
                                 CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoney()
                                    - Menu_InGame_Politics.this.costChangePolitic(1.0F)
                              ),
                           CFG.COLOR_TEXT_NUM_OF_PROVINCES
                        )
                     );
                     nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_gold, CFG.PADDING, 0));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                     this.menuElementHover = new MenuElement_Hover_v2(nElements);
                  }

                  if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getDiplomacyPoints() <= 5.0) {
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           CFG.langManager.get("NotEnoughDiplomaticPoints")
                              + ": "
                              + (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getDiplomacyPoints() - 5),
                           CFG.COLOR_TEXT_NUM_OF_PROVINCES
                        )
                     );
                     nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_diplomacy_points, CFG.PADDING, 0));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                     this.menuElementHover = new MenuElement_Hover_v2(nElements);
                  }
               }
            }
         }
      );
      int var16;
      menuElements.add(
         new Button_FlagActionSliderStyle(
            CFG.langManager.get("MilitaryExpenditures"),
            -1,
            2,
            var16 = tY + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING,
            tempWidth - 2,
            CFG.TEXT_HEIGHT + CFG.PADDING * 4,
            true
         ) {
            @Override
            public int getWidth() {
               return Menu_InGame_Politics.this.getW();
            }

            @Override
            public void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
               oSB.setColor(
                  new Color(new Color(CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.r, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.g, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.b, 0.375F))
               );
               ImageManager.getImage(Images.gradient)
                  .draw(
                     oSB,
                     this.getPosX() + iTranslateX,
                     this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY,
                     this.getWidth(),
                     this.getHeight()
                  );
               oSB.setColor(
                  new Color(new Color(CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.r, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.g, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.b, 0.375F))
               );
               ImageManager.getImage(Images.line_32_off1)
                  .draw(
                     oSB,
                     this.getPosX() + iTranslateX,
                     this.getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
                     this.getWidth(),
                     this.getHeight()
                  );
               oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.45F));
               ImageManager.getImage(Images.gradient)
                  .draw(
                     oSB,
                     this.getPosX() + iTranslateX,
                     this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY,
                     this.getWidth(),
                     CFG.PADDING
                  );
               oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.2F));
               ImageManager.getImage(Images.gradient)
                  .draw(
                     oSB,
                     this.getPosX() + iTranslateX,
                     this.getPosY() + this.getHeight() - CFG.PADDING - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY,
                     this.getWidth(),
                     CFG.PADDING,
                     false,
                     true
                  );
               oSB.setColor(
                  new Color(new Color(CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.r, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.g, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.b, 0.375F))
               );
               ImageManager.getImage(Images.pix255_255_255)
                  .draw(
                     oSB,
                     this.getPosX() + iTranslateX,
                     this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY,
                     this.getWidth(),
                     1
                  );
               oSB.setColor(CFG.COLOR_NEW_GAME_EDGE_LINE);
               ImageManager.getImage(Images.slider_gradient)
                  .draw(
                     oSB,
                     this.getPosX() + iTranslateX,
                     this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY,
                     this.getWidth() / 2,
                     1
                  );
               ImageManager.getImage(Images.slider_gradient)
                  .draw(
                     oSB,
                     this.getPosX() + this.getWidth() - this.getWidth() / 2 + iTranslateX,
                     this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY,
                     this.getWidth() / 2,
                     1,
                     true,
                     false
                  );
               oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.65F));
               ImageManager.getImage(Images.slider_gradient)
                  .draw(
                     oSB,
                     this.getPosX() + iTranslateX,
                     this.getPosY() + 1 - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY,
                     this.getWidth() / 2,
                     1
                  );
               ImageManager.getImage(Images.slider_gradient)
                  .draw(
                     oSB,
                     this.getPosX() + this.getWidth() - this.getWidth() / 2 + iTranslateX,
                     this.getPosY() + 1 - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY,
                     this.getWidth() / 2,
                     1,
                     true,
                     false
                  );
               oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.55F));
               ImageManager.getImage(Images.slider_gradient)
                  .draw(
                     oSB,
                     this.getPosX() + iTranslateX,
                     this.getPosY() + this.getHeight() - 1 - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY,
                     this.getWidth() / 2,
                     1
                  );
               ImageManager.getImage(Images.slider_gradient)
                  .draw(
                     oSB,
                     this.getPosX() + this.getWidth() - this.getWidth() / 2 + iTranslateX,
                     this.getPosY() + this.getHeight() - 1 - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY,
                     this.getWidth() / 2,
                     1,
                     true,
                     false
                  );
               oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.105F));
               ImageManager.getImage(Images.gradient)
                  .draw(
                     oSB,
                     this.getPosX() + iTranslateX,
                     this.getPosY() + this.getHeight() - this.getHeight() / 3 - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY,
                     this.getWidth(),
                     this.getHeight() / 3,
                     false,
                     true
                  );
               oSB.setColor(Color.WHITE);
               CFG.fontMain.getData().setScale(0.85F);
               CFG.fontMain.getData().setScale(1.15F);
               oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.7F));
               ImageManager.getImage(Images.line_32_off1)
                  .draw(
                     oSB,
                     this.getPosX() + iTranslateX,
                     this.getPosY() + this.getHeight() - ImageManager.getImage(Images.line_32_off1).getHeight() - 1 + iTranslateY,
                     this.getWidth(),
                     1
                  );
               oSB.setColor(Color.WHITE);
            }

            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               nData.add(
                  new MenuElement_Hover_v2_Element_Type_Text(
                     CFG.langManager.get("MilitaryExpendituresPoliciesText"),
                     new Color(CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.r, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.g, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.b, 0.375F)
                  )
               );
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               nData.add(
                  new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("MilitaryExpendituresPoliciesText2"), CFG.COLOR_INGAME_DIPLOMACY_POINTS)
               );
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               nData.add(
                  new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("MilitaryExpendituresPoliciesText3"), CFG.COLOR_INGAME_DIPLOMACY_POINTS)
               );
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }

            @Override
            public Color getColor(boolean isActive) {
               return CFG.COLOR_INGAME_DIPLOMACY_POINTS;
            }
         }
      );
      menuElements.add(
         new Button_FlagActionSliderStyle(
            CFG.langManager.get("MinimumMilitaryExpenditures"),
            -1,
            CFG.PADDING,
            tY = var16 + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING * 2,
            CFG.BUTTON_WIDTH,
            (
                  CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).civGameData.LevelMilitarySpending == 2
                     || CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).civGameData.LevelMilitarySpending == 3
               )
               && CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoney() >= this.costChangePolitic(0.4F)
               && CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getDiplomacyPoints() >= 5.0
         ) {
            @Override
            public int getWidth() {
               return (Menu_InGame_Politics.this.getW() - CFG.PADDING * 4) / 3;
            }

            @Override
            public Color getColor(boolean isActive) {
               return 1 == CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).civGameData.LevelMilitarySpending
                  ? CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                  : (
                     this.getClickable()
                        ? (
                           1 == CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).civGameData.LevelMilitarySpending
                              ? CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                              : (this.getIsHovered() ? CFG.COLOR_TEXT_CIV_INFO_HOVER : CFG.COLOR_TEXT_CIV_INFO)
                        )
                        : new Color(CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.r, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.g, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.b, 0.65F)
                  );
            }

            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).civGameData.LevelMilitarySpending != 1) {
                  if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoney() <= Menu_InGame_Politics.this.costChangePolitic(0.4F)) {
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           CFG.langManager.get("NotEnoughCoins")
                              + ": "
                              + Math.abs(
                                 CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoney()
                                    - Menu_InGame_Politics.this.costChangePolitic(0.4F)
                              ),
                           CFG.COLOR_TEXT_NUM_OF_PROVINCES
                        )
                     );
                     nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_gold, CFG.PADDING, 0));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                     this.menuElementHover = new MenuElement_Hover_v2(nElements);
                  }

                  if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getDiplomacyPoints() <= 5.0) {
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           CFG.langManager.get("NotEnoughDiplomaticPoints")
                              + ": "
                              + (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getDiplomacyPoints() - 5),
                           CFG.COLOR_TEXT_NUM_OF_PROVINCES
                        )
                     );
                     nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_diplomacy_points, CFG.PADDING, 0));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                     this.menuElementHover = new MenuElement_Hover_v2(nElements);
                  }
               }
            }
         }
      );
      menuElements.add(
         new Button_FlagActionSliderStyle(
            CFG.langManager.get("MinorMilitaryExpenditures"),
            -1,
            2,
            tY,
            CFG.BUTTON_WIDTH,
            (
                  CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).civGameData.LevelMilitarySpending == 1
                     || CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).civGameData.LevelMilitarySpending == 3
                     || CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).civGameData.LevelMilitarySpending == 4
               )
               && CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoney() >= this.costChangePolitic(0.55F)
               && CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getDiplomacyPoints() >= 5.0
         ) {
            @Override
            public int getPosX() {
               return CFG.PADDING * 2 + (Menu_InGame_Politics.this.getW() - CFG.PADDING * 4) / 3;
            }

            @Override
            public int getWidth() {
               return (Menu_InGame_Politics.this.getW() - CFG.PADDING * 4) / 3;
            }

            @Override
            public Color getColor(boolean isActive) {
               return 2 == CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).civGameData.LevelMilitarySpending
                  ? CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                  : (
                     this.getClickable()
                        ? (
                           2 == CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).civGameData.LevelMilitarySpending
                              ? CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                              : (this.getIsHovered() ? CFG.COLOR_TEXT_CIV_INFO_HOVER : CFG.COLOR_TEXT_CIV_INFO)
                        )
                        : new Color(CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.r, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.g, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.b, 0.65F)
                  );
            }

            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).civGameData.LevelMilitarySpending != 2) {
                  if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoney() <= Menu_InGame_Politics.this.costChangePolitic(0.55F)) {
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           CFG.langManager.get("NotEnoughCoins")
                              + ": "
                              + Math.abs(
                                 CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoney()
                                    - Menu_InGame_Politics.this.costChangePolitic(0.55F)
                              ),
                           CFG.COLOR_TEXT_NUM_OF_PROVINCES
                        )
                     );
                     nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_gold, CFG.PADDING, 0));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                     this.menuElementHover = new MenuElement_Hover_v2(nElements);
                  }

                  if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getDiplomacyPoints() <= 5.0) {
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           CFG.langManager.get("NotEnoughDiplomaticPoints")
                              + ": "
                              + (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getDiplomacyPoints() - 5),
                           CFG.COLOR_TEXT_NUM_OF_PROVINCES
                        )
                     );
                     nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.technology, CFG.PADDING, 0));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                     this.menuElementHover = new MenuElement_Hover_v2(nElements);
                  }
               }
            }
         }
      );
      menuElements.add(
         new Button_FlagActionSliderStyle(
            CFG.langManager.get("AverageExpenses"),
            -1,
            2,
            tY,
            CFG.BUTTON_WIDTH,
            (
                  CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).civGameData.LevelMilitarySpending == 1
                     || CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).civGameData.LevelMilitarySpending == 2
                     || CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).civGameData.LevelMilitarySpending == 4
                     || CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).civGameData.LevelMilitarySpending == 5
               )
               && CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoney() >= this.costChangePolitic(0.7F)
               && CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getDiplomacyPoints() >= 5.0
         ) {
            @Override
            public int getPosX() {
               return Menu_InGame_Politics.this.getW() - (Menu_InGame_Politics.this.getW() - CFG.PADDING * 4) / 3 - CFG.PADDING;
            }

            @Override
            public int getWidth() {
               return (Menu_InGame_Politics.this.getW() - CFG.PADDING * 4) / 3;
            }

            @Override
            public Color getColor(boolean isActive) {
               return 3 == CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).civGameData.LevelMilitarySpending
                  ? CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                  : (
                     this.getClickable()
                        ? (
                           3 == CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).civGameData.LevelMilitarySpending
                              ? CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                              : (this.getIsHovered() ? CFG.COLOR_TEXT_CIV_INFO_HOVER : CFG.COLOR_TEXT_CIV_INFO)
                        )
                        : new Color(CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.r, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.g, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.b, 0.65F)
                  );
            }

            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).civGameData.LevelMilitarySpending != 3) {
                  if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoney() <= Menu_InGame_Politics.this.costChangePolitic(0.7F)) {
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           CFG.langManager.get("NotEnoughCoins")
                              + ": "
                              + Math.abs(
                                 CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoney()
                                    - Menu_InGame_Politics.this.costChangePolitic(0.7F)
                              ),
                           CFG.COLOR_TEXT_NUM_OF_PROVINCES
                        )
                     );
                     nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_gold, CFG.PADDING, 0));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                     this.menuElementHover = new MenuElement_Hover_v2(nElements);
                  }

                  if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getDiplomacyPoints() <= 5.0) {
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           CFG.langManager.get("NotEnoughDiplomaticPoints")
                              + ": "
                              + (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getDiplomacyPoints() - 5),
                           CFG.COLOR_TEXT_NUM_OF_PROVINCES
                        )
                     );
                     nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_diplomacy_points, CFG.PADDING, 0));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                     this.menuElementHover = new MenuElement_Hover_v2(nElements);
                  }
               }
            }
         }
      );
      int var18;
      menuElements.add(
         new Button_FlagActionSliderStyle(
            CFG.langManager.get("SignificantExpenses"),
            -1,
            2,
            var18 = tY + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING,
            CFG.BUTTON_WIDTH,
            (
                  CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).civGameData.LevelMilitarySpending == 2
                     || CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).civGameData.LevelMilitarySpending == 3
                     || CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).civGameData.LevelMilitarySpending == 5
                     || CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).civGameData.LevelMilitarySpending == 6
               )
               && CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoney() >= this.costChangePolitic(0.85F)
               && CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getDiplomacyPoints() >= 5.0
         ) {
            @Override
            public int getWidth() {
               return (Menu_InGame_Politics.this.getW() - CFG.PADDING * 4) / 3;
            }

            @Override
            public Color getColor(boolean isActive) {
               return 4 == CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).civGameData.LevelMilitarySpending
                  ? CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                  : (
                     this.getClickable()
                        ? (
                           4 == CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).civGameData.LevelMilitarySpending
                              ? CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                              : (this.getIsHovered() ? CFG.COLOR_TEXT_CIV_INFO_HOVER : CFG.COLOR_TEXT_CIV_INFO)
                        )
                        : new Color(CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.r, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.g, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.b, 0.65F)
                  );
            }

            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).civGameData.LevelMilitarySpending != 4) {
                  if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoney() <= Menu_InGame_Politics.this.costChangePolitic(0.85F)) {
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           CFG.langManager.get("NotEnoughCoins")
                              + ": "
                              + Math.abs(
                                 CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoney()
                                    - Menu_InGame_Politics.this.costChangePolitic(0.85F)
                              ),
                           CFG.COLOR_TEXT_NUM_OF_PROVINCES
                        )
                     );
                     nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_gold, CFG.PADDING, 0));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                     this.menuElementHover = new MenuElement_Hover_v2(nElements);
                  }

                  if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getDiplomacyPoints() <= 5.0) {
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           CFG.langManager.get("NotEnoughDiplomaticPoints")
                              + ": "
                              + (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getDiplomacyPoints() - 5),
                           CFG.COLOR_TEXT_NUM_OF_PROVINCES
                        )
                     );
                     nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_diplomacy_points, CFG.PADDING, 0));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                     this.menuElementHover = new MenuElement_Hover_v2(nElements);
                  }
               }
            }
         }
      );
      menuElements.add(
         new Button_FlagActionSliderStyle(
            CFG.langManager.get("MajorExpenditures"),
            -1,
            2,
            var18,
            CFG.BUTTON_WIDTH,
            (
                  CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).civGameData.LevelMilitarySpending == 3
                     || CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).civGameData.LevelMilitarySpending == 4
                     || CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).civGameData.LevelMilitarySpending == 6
               )
               && CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoney() >= this.costChangePolitic(1.0F)
               && CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getDiplomacyPoints() >= 5.0
         ) {
            @Override
            public int getPosX() {
               return CFG.PADDING * 2 + (Menu_InGame_Politics.this.getW() - CFG.PADDING * 4) / 3;
            }

            @Override
            public int getWidth() {
               return (Menu_InGame_Politics.this.getW() - CFG.PADDING * 4) / 3;
            }

            @Override
            public Color getColor(boolean isActive) {
               return 5 == CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).civGameData.LevelMilitarySpending
                  ? CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                  : (
                     this.getClickable()
                        ? (
                           5 == CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).civGameData.LevelMilitarySpending
                              ? CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                              : (this.getIsHovered() ? CFG.COLOR_TEXT_CIV_INFO_HOVER : CFG.COLOR_TEXT_CIV_INFO)
                        )
                        : new Color(CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.r, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.g, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.b, 0.65F)
                  );
            }

            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).civGameData.LevelMilitarySpending != 5) {
                  if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoney() <= Menu_InGame_Politics.this.costChangePolitic(1.0F)) {
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           CFG.langManager.get("NotEnoughCoins")
                              + ": "
                              + Math.abs(
                                 CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoney()
                                    - Menu_InGame_Politics.this.costChangePolitic(1.0F)
                              ),
                           CFG.COLOR_TEXT_NUM_OF_PROVINCES
                        )
                     );
                     nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_gold, CFG.PADDING, 0));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                     this.menuElementHover = new MenuElement_Hover_v2(nElements);
                  }

                  if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getDiplomacyPoints() <= 5.0) {
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           CFG.langManager.get("NotEnoughDiplomaticPoints")
                              + ": "
                              + (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getDiplomacyPoints() - 5),
                           CFG.COLOR_TEXT_NUM_OF_PROVINCES
                        )
                     );
                     nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_diplomacy_points, CFG.PADDING, 0));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                     this.menuElementHover = new MenuElement_Hover_v2(nElements);
                  }
               }
            }
         }
      );
      menuElements.add(
         new Button_FlagActionSliderStyle(
            CFG.langManager.get("HugeExpenses"),
            -1,
            2,
            var18,
            CFG.BUTTON_WIDTH,
            (
                  CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).civGameData.LevelMilitarySpending == 4
                     || CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).civGameData.LevelMilitarySpending == 5
               )
               && CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoney() >= this.costChangePolitic(1.1F)
               && CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getDiplomacyPoints() >= 5.0
         ) {
            @Override
            public int getPosX() {
               return Menu_InGame_Politics.this.getW() - (Menu_InGame_Politics.this.getW() - CFG.PADDING * 4) / 3 - CFG.PADDING;
            }

            @Override
            public int getWidth() {
               return (Menu_InGame_Politics.this.getW() - CFG.PADDING * 4) / 3;
            }

            @Override
            public Color getColor(boolean isActive) {
               return 6 == CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).civGameData.LevelMilitarySpending
                  ? CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                  : (
                     this.getClickable()
                        ? (
                           6 == CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).civGameData.LevelMilitarySpending
                              ? CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                              : (this.getIsHovered() ? CFG.COLOR_TEXT_CIV_INFO_HOVER : CFG.COLOR_TEXT_CIV_INFO)
                        )
                        : new Color(CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.r, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.g, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.b, 0.65F)
                  );
            }

            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).civGameData.LevelMilitarySpending != 6) {
                  if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoney() <= Menu_InGame_Politics.this.costChangePolitic(1.1F)) {
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           CFG.langManager.get("NotEnoughCoins")
                              + ": "
                              + Math.abs(
                                 CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoney()
                                    - Menu_InGame_Politics.this.costChangePolitic(1.1F)
                              ),
                           CFG.COLOR_TEXT_NUM_OF_PROVINCES
                        )
                     );
                     nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_gold, CFG.PADDING, 0));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                     this.menuElementHover = new MenuElement_Hover_v2(nElements);
                  }

                  if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getDiplomacyPoints() <= 5.0) {
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           CFG.langManager.get("NotEnoughDiplomaticPoints")
                              + ": "
                              + (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getDiplomacyPoints() - 5),
                           CFG.COLOR_TEXT_NUM_OF_PROVINCES
                        )
                     );
                     nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_diplomacy_points, CFG.PADDING, 0));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                     this.menuElementHover = new MenuElement_Hover_v2(nElements);
                  }
               }
            }
         }
      );
      menuElements.add(
         new Button_FlagActionSliderStyle(
            CFG.langManager.get("MilitaryTactics"),
            -1,
            2,
            tY = var18 + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING,
            tempWidth - 2,
            CFG.TEXT_HEIGHT + CFG.PADDING * 4,
            true
         ) {
            @Override
            public int getWidth() {
               return Menu_InGame_Politics.this.getW();
            }

            @Override
            public void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
               oSB.setColor(
                  new Color(new Color(CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.r, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.g, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.b, 0.375F))
               );
               ImageManager.getImage(Images.gradient)
                  .draw(
                     oSB,
                     this.getPosX() + iTranslateX,
                     this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY,
                     this.getWidth(),
                     this.getHeight()
                  );
               oSB.setColor(
                  new Color(new Color(CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.r, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.g, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.b, 0.375F))
               );
               ImageManager.getImage(Images.line_32_off1)
                  .draw(
                     oSB,
                     this.getPosX() + iTranslateX,
                     this.getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
                     this.getWidth(),
                     this.getHeight()
                  );
               oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.45F));
               ImageManager.getImage(Images.gradient)
                  .draw(
                     oSB,
                     this.getPosX() + iTranslateX,
                     this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY,
                     this.getWidth(),
                     CFG.PADDING
                  );
               oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.2F));
               ImageManager.getImage(Images.gradient)
                  .draw(
                     oSB,
                     this.getPosX() + iTranslateX,
                     this.getPosY() + this.getHeight() - CFG.PADDING - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY,
                     this.getWidth(),
                     CFG.PADDING,
                     false,
                     true
                  );
               oSB.setColor(
                  new Color(new Color(CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.r, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.g, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.b, 0.375F))
               );
               ImageManager.getImage(Images.pix255_255_255)
                  .draw(
                     oSB,
                     this.getPosX() + iTranslateX,
                     this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY,
                     this.getWidth(),
                     1
                  );
               oSB.setColor(CFG.COLOR_NEW_GAME_EDGE_LINE);
               ImageManager.getImage(Images.slider_gradient)
                  .draw(
                     oSB,
                     this.getPosX() + iTranslateX,
                     this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY,
                     this.getWidth() / 2,
                     1
                  );
               ImageManager.getImage(Images.slider_gradient)
                  .draw(
                     oSB,
                     this.getPosX() + this.getWidth() - this.getWidth() / 2 + iTranslateX,
                     this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY,
                     this.getWidth() / 2,
                     1,
                     true,
                     false
                  );
               oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.65F));
               ImageManager.getImage(Images.slider_gradient)
                  .draw(
                     oSB,
                     this.getPosX() + iTranslateX,
                     this.getPosY() + 1 - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY,
                     this.getWidth() / 2,
                     1
                  );
               ImageManager.getImage(Images.slider_gradient)
                  .draw(
                     oSB,
                     this.getPosX() + this.getWidth() - this.getWidth() / 2 + iTranslateX,
                     this.getPosY() + 1 - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY,
                     this.getWidth() / 2,
                     1,
                     true,
                     false
                  );
               oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.55F));
               ImageManager.getImage(Images.slider_gradient)
                  .draw(
                     oSB,
                     this.getPosX() + iTranslateX,
                     this.getPosY() + this.getHeight() - 1 - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY,
                     this.getWidth() / 2,
                     1
                  );
               ImageManager.getImage(Images.slider_gradient)
                  .draw(
                     oSB,
                     this.getPosX() + this.getWidth() - this.getWidth() / 2 + iTranslateX,
                     this.getPosY() + this.getHeight() - 1 - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY,
                     this.getWidth() / 2,
                     1,
                     true,
                     false
                  );
               oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.105F));
               ImageManager.getImage(Images.gradient)
                  .draw(
                     oSB,
                     this.getPosX() + iTranslateX,
                     this.getPosY() + this.getHeight() - this.getHeight() / 3 - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY,
                     this.getWidth(),
                     this.getHeight() / 3,
                     false,
                     true
                  );
               oSB.setColor(Color.WHITE);
               CFG.fontMain.getData().setScale(0.85F);
               CFG.fontMain.getData().setScale(1.15F);
               oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.7F));
               ImageManager.getImage(Images.line_32_off1)
                  .draw(
                     oSB,
                     this.getPosX() + iTranslateX,
                     this.getPosY() + this.getHeight() - ImageManager.getImage(Images.line_32_off1).getHeight() - 1 + iTranslateY,
                     this.getWidth(),
                     1
                  );
               oSB.setColor(Color.WHITE);
            }

            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               nData.add(
                  new MenuElement_Hover_v2_Element_Type_Text(
                     CFG.langManager.get("MilitaryTacticsPoliciesText"),
                     new Color(CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.r, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.g, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.b, 0.375F)
                  )
               );
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("MilitaryTacticsPoliciesText2"), CFG.COLOR_INGAME_DIPLOMACY_POINTS));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("MilitaryTacticsPoliciesText3"), CFG.COLOR_INGAME_DIPLOMACY_POINTS));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }

            @Override
            public Color getColor(boolean isActive) {
               return CFG.COLOR_INGAME_DIPLOMACY_POINTS;
            }
         }
      );
      int var20;
      menuElements.add(
         new Button_FlagActionSliderStyle(
            CFG.langManager.get("FastAttackTactics"),
            -1,
            CFG.PADDING,
            var20 = tY + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING * 2,
            CFG.BUTTON_WIDTH,
            CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoney() >= this.costChangePolitic(0.4F)
               && CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getDiplomacyPoints() >= 3.0
               && CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMilitaryPoints() >= 50.0
         ) {
            @Override
            public int getWidth() {
               return (Menu_InGame_Politics.this.getW() - CFG.PADDING * 4) / 3;
            }

            @Override
            public Color getColor(boolean isActive) {
               return 1 == CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).civGameData.LevelMilitaryTactic
                  ? CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                  : (
                     this.getClickable()
                        ? (
                           1 == CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).civGameData.LevelMilitaryTactic
                              ? CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                              : (this.getIsHovered() ? CFG.COLOR_TEXT_CIV_INFO_HOVER : CFG.COLOR_TEXT_CIV_INFO)
                        )
                        : new Color(CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.r, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.g, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.b, 0.65F)
                  );
            }

            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).civGameData.LevelMilitaryTactic != 1) {
                  if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoney() <= Menu_InGame_Politics.this.costChangePolitic(0.4F)) {
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           CFG.langManager.get("NotEnoughCoins")
                              + ": "
                              + Math.abs(
                                 CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoney()
                                    - Menu_InGame_Politics.this.costChangePolitic(0.4F)
                              ),
                           CFG.COLOR_TEXT_NUM_OF_PROVINCES
                        )
                     );
                     nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_gold, CFG.PADDING, 0));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                     this.menuElementHover = new MenuElement_Hover_v2(nElements);
                  }

                  if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getDiplomacyPoints() <= 3.0) {
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           CFG.langManager.get("NotEnoughDiplomaticPoints")
                              + ": "
                              + Math.abs(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getDiplomacyPoints() - 3),
                           CFG.COLOR_TEXT_NUM_OF_PROVINCES
                        )
                     );
                     nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_diplomacy_points, CFG.PADDING, 0));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                     this.menuElementHover = new MenuElement_Hover_v2(nElements);
                  }

                  if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMilitaryPoints() <= 50.0) {
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           CFG.langManager.get("NotEnoughMilitaryPoints")
                              + ": "
                              + Math.abs(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMilitaryPoints() - 50),
                           CFG.COLOR_TEXT_NUM_OF_PROVINCES
                        )
                     );
                     nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.militaryxp, CFG.PADDING, 0));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                     this.menuElementHover = new MenuElement_Hover_v2(nElements);
                  }
               }
            }
         }
      );
      menuElements.add(
         new Button_FlagActionSliderStyle(
            CFG.langManager.get("DefenseTactics"),
            -1,
            2,
            var20,
            CFG.BUTTON_WIDTH,
            CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoney() >= this.costChangePolitic(0.4F)
               && CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getDiplomacyPoints() >= 3.0
               && CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMilitaryPoints() >= 50.0
         ) {
            @Override
            public int getPosX() {
               return CFG.PADDING * 2 + (Menu_InGame_Politics.this.getW() - CFG.PADDING * 4) / 3;
            }

            @Override
            public int getWidth() {
               return (Menu_InGame_Politics.this.getW() - CFG.PADDING * 4) / 3;
            }

            @Override
            public Color getColor(boolean isActive) {
               return 2 == CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).civGameData.LevelMilitaryTactic
                  ? CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                  : (
                     this.getClickable()
                        ? (
                           2 == CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).civGameData.LevelMilitaryTactic
                              ? CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                              : (this.getIsHovered() ? CFG.COLOR_TEXT_CIV_INFO_HOVER : CFG.COLOR_TEXT_CIV_INFO)
                        )
                        : new Color(CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.r, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.g, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.b, 0.65F)
                  );
            }

            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).civGameData.LevelMilitaryTactic != 2) {
                  if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoney() <= Menu_InGame_Politics.this.costChangePolitic(0.4F)) {
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           CFG.langManager.get("NotEnoughCoins")
                              + ": "
                              + Math.abs(
                                 CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoney()
                                    - Menu_InGame_Politics.this.costChangePolitic(0.4F)
                              ),
                           CFG.COLOR_TEXT_NUM_OF_PROVINCES
                        )
                     );
                     nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_gold, CFG.PADDING, 0));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                     this.menuElementHover = new MenuElement_Hover_v2(nElements);
                  }

                  if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getDiplomacyPoints() <= 3.0) {
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           CFG.langManager.get("NotEnoughDiplomaticPoints")
                              + ": "
                              + Math.abs(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getDiplomacyPoints() - 3),
                           CFG.COLOR_TEXT_NUM_OF_PROVINCES
                        )
                     );
                     nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_diplomacy_points, CFG.PADDING, 0));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                     this.menuElementHover = new MenuElement_Hover_v2(nElements);
                  }

                  if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMilitaryPoints() <= 50.0) {
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           CFG.langManager.get("NotEnoughMilitaryPoints")
                              + ": "
                              + Math.abs(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMilitaryPoints() - 50),
                           CFG.COLOR_TEXT_NUM_OF_PROVINCES
                        )
                     );
                     nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.militaryxp, CFG.PADDING, 0));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                     this.menuElementHover = new MenuElement_Hover_v2(nElements);
                  }
               }
            }
         }
      );
      menuElements.add(
         new Button_FlagActionSliderStyle(
            CFG.langManager.get("BalancedTactics"),
            -1,
            2,
            var20,
            CFG.BUTTON_WIDTH,
            CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoney() >= this.costChangePolitic(0.4F)
               && CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getDiplomacyPoints() >= 3.0
               && CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMilitaryPoints() >= 50.0
         ) {
            @Override
            public int getPosX() {
               return Menu_InGame_Politics.this.getW() - (Menu_InGame_Politics.this.getW() - CFG.PADDING * 4) / 3 - CFG.PADDING;
            }

            @Override
            public int getWidth() {
               return (Menu_InGame_Politics.this.getW() - CFG.PADDING * 4) / 3;
            }

            @Override
            public Color getColor(boolean isActive) {
               return 3 == CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).civGameData.LevelMilitaryTactic
                  ? CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                  : (
                     this.getClickable()
                        ? (
                           3 == CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).civGameData.LevelMilitaryTactic
                              ? CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                              : (this.getIsHovered() ? CFG.COLOR_TEXT_CIV_INFO_HOVER : CFG.COLOR_TEXT_CIV_INFO)
                        )
                        : new Color(CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.r, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.g, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.b, 0.65F)
                  );
            }

            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).civGameData.LevelMilitaryTactic != 3) {
                  if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoney() <= Menu_InGame_Politics.this.costChangePolitic(0.4F)) {
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           CFG.langManager.get("NotEnoughCoins")
                              + ": "
                              + Math.abs(
                                 CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoney()
                                    - Menu_InGame_Politics.this.costChangePolitic(0.4F)
                              ),
                           CFG.COLOR_TEXT_NUM_OF_PROVINCES
                        )
                     );
                     nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_gold, CFG.PADDING, 0));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                     this.menuElementHover = new MenuElement_Hover_v2(nElements);
                  }

                  if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getDiplomacyPoints() <= 3.0) {
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           CFG.langManager.get("NotEnoughDiplomaticPoints")
                              + ": "
                              + Math.abs(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getDiplomacyPoints() - 3),
                           CFG.COLOR_TEXT_NUM_OF_PROVINCES
                        )
                     );
                     nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_diplomacy_points, CFG.PADDING, 0));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                     this.menuElementHover = new MenuElement_Hover_v2(nElements);
                  }

                  if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMilitaryPoints() <= 50.0) {
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           CFG.langManager.get("NotEnoughMilitaryPoints")
                              + ": "
                              + Math.abs(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMilitaryPoints() - 50),
                           CFG.COLOR_TEXT_NUM_OF_PROVINCES
                        )
                     );
                     nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.militaryxp, CFG.PADDING, 0));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                     this.menuElementHover = new MenuElement_Hover_v2(nElements);
                  }
               }
            }
         }
      );
      tY = var20 + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING * 2;
      {
         this.aiButtonsStart = menuElements.size();
         String[][] tSections = new String[][]{
            new String[]{"Policy_Label", "Policy_Info", "Policy_Friendly", "Policy_Neutral", "Policy_Aggressive"},
            new String[]{"SP_Migration", "SP_Migration_Info", "SP_OpenDoor", "SP_Neutral", "SP_ClosedBorders"},
            new String[]{"SP_MinorityTax", "SP_Tax_Info", "SP_Lenient", "SP_Equal", "SP_Tribute"},
            new String[]{"SP_Nuclear", "SP_Nuclear_Info", "SP_Standard", "SP_FirstStrike", "SP_Deterrence"}
         };
         int[][] tValues = new int[][]{new int[]{1, 0, 2}, new int[]{0, 1, 2}, new int[]{0, 1, 2}, new int[]{0, 1, 2}};
         int tW3 = (tempWidth - CFG.PADDING * 2) / 3;

         for (int tSection = 0; tSection < 4; tSection++) {
            final int tSectionID = tSection;
            final String[] tKeysF = tSections[tSection];
            final int[] tValsF = tValues[tSection];
            menuElements.add(
               new Button_PolicyTitle(
                  CFG.langManager.get(tKeysF[0]) + ": " + CFG.langManager.get(tKeysF[2 + this.aiPolicyGet(tSection)]),
                  2,
                  tY,
                  tempWidth - 2
               )
            );
            int varOpt = tY + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;

            for (int tOpt = 0; tOpt < 3; tOpt++) {
               final int tOptionID = tOpt;
               final int tPosXFinal = CFG.PADDING + tOpt * (tW3 + CFG.PADDING);
               menuElements.add(
                  new Button_EconomicPolitic(CFG.langManager.get(tKeysF[2 + tOptionID]), -1, tPosXFinal, varOpt, tW3, true, tOptionID) {
                     @Override
                     public Color getColor(boolean isActive) {
                        return aiPolicyGet(tSectionID) == tValsF[tOptionID]
                           ? CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                           : (this.getIsHovered() ? CFG.COLOR_TEXT_CIV_INFO_HOVER : CFG.COLOR_TEXT_CIV_INFO);
                     }

                     @Override
                     public void buildElementHover() {
                        ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
                        ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get(tKeysF[1])));
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        this.menuElementHover = new MenuElement_Hover_v2(nElements);
                     }

                     @Override
                     public void actionElement(int iIDX) {
                        aiPolicySet(tSectionID, tValsF[tOptionID]);
                        CFG.toast.setInView(
                           CFG.langManager.get(tKeysF[0]) + ": " + CFG.langManager.get(tKeysF[2 + tOptionID]),
                           CFG.COLOR_TEXT_MODIFIER_POSITIVE
                        );
                        CFG.toast.setTimeInView(2500);
                        CFG.menuManager.setVisible_InGame_Politics(true);
                     }
                  }
               );
            }

            tY = varOpt + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
         }

         this.aiButtonsEnd = menuElements.size();
      }

      Menu_InGame_FlagAction_Bot.lTime = System.currentTimeMillis();
      menuElements.add(
         new Button_Transparent(
            0, 0, tempWidth, menuElements.get(menuElements.size() - 1).getPosY() + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING, true
         )
      );
      this.initMenu(
         new SliderMenuTitle(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCivName(), (int)(CFG.BUTTON_HEIGHT * 3 / 4.5), true, true) {
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
               oSB.setColor(
                  new Color(
                     CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getR() / 255.0F,
                     CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getG() / 255.0F,
                     CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getB() / 255.0F,
                     0.165F
                  )
               );
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
               oSB.setColor(
                  new Color(
                     CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getR() / 255.0F,
                     CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getG() / 255.0F,
                     CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getB() / 255.0F,
                     0.375F
                  )
               );
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
               oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.425F));
               ImageManager.getImage(Images.slider_gradient)
                  .draw(
                     oSB,
                     nPosX + CFG.PADDING * 2 + iTranslateX,
                     nPosY + 1 - this.getHeight() / 2 - ImageManager.getImage(Images.slider_gradient).getHeight(),
                     (int)((nWidth - CFG.PADDING * 6 - this.getTextWidth() * 0.8F) / 2.0F),
                     1,
                     true,
                     false
                  );
               ImageManager.getImage(Images.slider_gradient)
                  .draw(
                     oSB,
                     nPosX + nWidth / 2 + CFG.PADDING + (int)(this.getTextWidth() * 0.8F / 2.0F) + iTranslateX,
                     nPosY + 1 - this.getHeight() / 2 - ImageManager.getImage(Images.slider_gradient).getHeight(),
                     (int)((nWidth - CFG.PADDING * 6 - this.getTextWidth() * 0.8F) / 2.0F),
                     1
                  );
               oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.325F));
               ImageManager.getImage(Images.slider_gradient)
                  .draw(
                     oSB,
                     nPosX + CFG.PADDING * 2 + iTranslateX,
                     nPosY + 2 - this.getHeight() / 2 - ImageManager.getImage(Images.slider_gradient).getHeight(),
                     (int)((nWidth - CFG.PADDING * 6 - this.getTextWidth() * 0.8F) / 2.0F),
                     1,
                     true,
                     false
                  );
               ImageManager.getImage(Images.slider_gradient)
                  .draw(
                     oSB,
                     nPosX + nWidth / 2 + CFG.PADDING + (int)(this.getTextWidth() * 0.8F / 2.0F) + iTranslateX,
                     nPosY + 2 - this.getHeight() / 2 - ImageManager.getImage(Images.slider_gradient).getHeight(),
                     (int)((nWidth - CFG.PADDING * 6 - this.getTextWidth() * 0.8F) / 2.0F),
                     1
                  );
               ImageManager.getImage(Images.slider_gradient)
                  .draw(
                     oSB,
                     nPosX + CFG.PADDING * 2 + iTranslateX,
                     nPosY - this.getHeight() / 2 - ImageManager.getImage(Images.slider_gradient).getHeight(),
                     (int)((nWidth - CFG.PADDING * 6 - this.getTextWidth() * 0.8F) / 2.0F),
                     1,
                     true,
                     false
                  );
               ImageManager.getImage(Images.slider_gradient)
                  .draw(
                     oSB,
                     nPosX + nWidth / 2 + CFG.PADDING + (int)(this.getTextWidth() * 0.8F / 2.0F) + iTranslateX,
                     nPosY - this.getHeight() / 2 - ImageManager.getImage(Images.slider_gradient).getHeight(),
                     (int)((nWidth - CFG.PADDING * 6 - this.getTextWidth() * 0.8F) / 2.0F),
                     1
                  );
               oSB.setColor(Color.WHITE);
               CFG.game
                  .getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
                  .getFlag()
                  .draw(
                     oSB,
                     nPosX + CFG.PADDING * 2 + iTranslateX,
                     nPosY
                        - this.getHeight()
                        + this.getHeight() / 2
                        + 1
                        - CFG.CIV_FLAG_HEIGHT / 2
                        - CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getFlag().getHeight(),
                     CFG.CIV_FLAG_WIDTH,
                     CFG.CIV_FLAG_HEIGHT
                  );
               ImageManager.getImage(Images.flag_rect)
                  .draw(oSB, nPosX + CFG.PADDING * 2 + iTranslateX, nPosY - this.getHeight() + this.getHeight() / 2 + 1 - CFG.CIV_FLAG_HEIGHT / 2);
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
         CFG.PADDING * 2,
         ImageManager.getImage(Images.top_left).getHeight() + CFG.PADDING + CFG.BUTTON_HEIGHT * 3 / 3,
         tempWidth,
         (int)Math.min(
            (double)(menuElements.get(menuElements.size() - 1).getPosY() + menuElements.get(menuElements.size() - 1).getHeight() + 1),
            CFG.GAME_HEIGHT / 1.3 - (ImageManager.getImage(Images.top_left).getHeight() + CFG.PADDING + CFG.BUTTON_HEIGHT * 3 / 5) - CFG.PADDING * 2
         ),
         menuElements,
         false,
         true
      );
   }

   @Override
   public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      if (Menu_InGame_FlagAction_Bot.lTime + 225L >= System.currentTimeMillis()) {
         oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.15F));
         ImageManager.getImage(Images.patt2)
            .draw2(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.patt2).getHeight(), this.getWidth(), this.getHeight());
         Rectangle clipBounds = new Rectangle(
            this.getPosX() - 2,
            CFG.GAME_HEIGHT - this.getPosY(),
            this.getWidth() + 4,
            -((int)((this.getHeight() + CFG.PADDING) * ((float)(System.currentTimeMillis() - Menu_InGame_FlagAction_Bot.lTime) / 225.0F)))
         );
         oSB.flush();
         ScissorStack.pushScissors(clipBounds);
         oSB.setColor(Color.WHITE);
         ImageManager.getImage(Images.new_game_top_edge)
            .draw2(
               oSB,
               this.getPosX() - 2 + iTranslateX,
               this.getPosY() - ImageManager.getImage(Images.new_game_top_edge).getHeight() + iTranslateY,
               this.getWidth() + 4 - ImageManager.getImage(Images.new_game_top_edge).getWidth(),
               this.getHeight() + CFG.PADDING,
               false,
               true
            );
         ImageManager.getImage(Images.new_game_top_edge)
            .draw2(
               oSB,
               this.getPosX() - 2 + this.getWidth() + 4 - ImageManager.getImage(Images.new_game_top_edge).getWidth() + iTranslateX,
               this.getPosY() - ImageManager.getImage(Images.new_game_top_edge).getHeight() + iTranslateY,
               ImageManager.getImage(Images.new_game_top_edge).getWidth(),
               this.getHeight() + CFG.PADDING,
               true,
               true
            );
         oSB.setColor(new Color(0.025F, 0.025F, 0.025F, 0.25F));
         ImageManager.getImage(Images.slider_gradient)
            .draw(
               oSB,
               this.getPosX() + iTranslateX,
               this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY,
               this.getWidth(),
               this.getHeight() + CFG.PADDING - 2,
               true,
               false
            );
         oSB.setColor(new Color(0.025F, 0.025F, 0.025F, 0.75F));
         ImageManager.getImage(Images.gradient)
            .draw(
               oSB,
               this.getPosX() + 2 + iTranslateX,
               this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY,
               this.getWidth() - 2,
               CFG.BUTTON_HEIGHT / 4
            );
         oSB.setColor(Color.WHITE);
         super.draw(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
         CFG.setRender_3(true);
         this.endClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
      } else {
         oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.15F));
         ImageManager.getImage(Images.patt2)
            .draw2(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.patt2).getHeight(), this.getWidth(), this.getHeight());
         oSB.setColor(Color.WHITE);
         ImageManager.getImage(Images.new_game_top_edge)
            .draw2(
               oSB,
               this.getPosX() - 2 + iTranslateX,
               this.getPosY() - ImageManager.getImage(Images.new_game_top_edge).getHeight() + iTranslateY,
               this.getWidth() + 4 - ImageManager.getImage(Images.new_game_top_edge).getWidth(),
               this.getHeight() + CFG.PADDING,
               false,
               true
            );
         ImageManager.getImage(Images.new_game_top_edge)
            .draw2(
               oSB,
               this.getPosX() - 2 + this.getWidth() + 4 - ImageManager.getImage(Images.new_game_top_edge).getWidth() + iTranslateX,
               this.getPosY() - ImageManager.getImage(Images.new_game_top_edge).getHeight() + iTranslateY,
               ImageManager.getImage(Images.new_game_top_edge).getWidth(),
               this.getHeight() + CFG.PADDING,
               true,
               true
            );
         oSB.setColor(new Color(0.025F, 0.025F, 0.025F, 0.25F));
         ImageManager.getImage(Images.slider_gradient)
            .draw(
               oSB,
               this.getPosX() + iTranslateX,
               this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY,
               this.getWidth(),
               this.getHeight() + CFG.PADDING - 2,
               true,
               false
            );
         oSB.setColor(new Color(0.025F, 0.025F, 0.025F, 0.75F));
         ImageManager.getImage(Images.gradient)
            .draw(
               oSB,
               this.getPosX() + 2 + iTranslateX,
               this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY,
               this.getWidth() - 2,
               CFG.BUTTON_HEIGHT / 4
            );
         oSB.setColor(Color.WHITE);
         super.draw(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
      }
   }

   @Override
   public void onHovered() {
      CFG.menuManager.setOrderOfMenu_InGame_FlagAction();
   }

   private long costChangePolitic(float percentage) {
      return (long)((float)CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).countPopulation() * 0.2F * percentage);
   }

   public List<String> getTacticBonuses(int tacticLevel) {
      List<String> bonuses = new ArrayList<>();
      switch (tacticLevel) {
         case 1:
            bonuses.add("incomeProductionModifier: -10.0");
            bonuses.add("popGrowthModifier: 0.0");
            bonuses.add("movementPointsModifier: 15.0");
            bonuses.add("defenseBonusModifier: -30.0");
            bonuses.add("genocidePowerModifier: 30.0");
            bonuses.add("attackBonusModifier: 40.0");
            bonuses.add("militaryUpkeepModifier: 17.0");
            bonuses.add("researchModifier: 0.0");
            bonuses.add("administrationModifier: 0.0");
            bonuses.add("incomeTaxationModifier: -5.0");
            bonuses.add("economyGrowthModifier: 0.0");
            break;
         case 2:
            bonuses.add("incomeProductionModifier: 0.0");
            bonuses.add("popGrowthModifier: 0.0");
            bonuses.add("movementPointsModifier: 30.0");
            bonuses.add("defenseBonusModifier: 40.0");
            bonuses.add("genocidePowerModifier: -10.0");
            bonuses.add("attackBonusModifier: -30.0");
            bonuses.add("militaryUpkeepModifier: -15.0");
            bonuses.add("researchModifier: 0.0");
            bonuses.add("administrationModifier: 0.0");
            bonuses.add("incomeTaxationModifier: 0.0");
            bonuses.add("economyGrowthModifier: 0.0");
            break;
         case 3:
            bonuses.add("incomeProductionModifier: 0.0");
            bonuses.add("popGrowthModifier: 0.0");
            bonuses.add("movementPointsModifier: 0.0");
            bonuses.add("defenseBonusModifier: 0.0");
            bonuses.add("genocidePowerModifier: 0.0");
            bonuses.add("attackBonusModifier: 0.0");
            bonuses.add("militaryUpkeepModifier: 0.0");
            bonuses.add("researchModifier: 0.0");
            bonuses.add("administrationModifier: 0.0");
            bonuses.add("incomeTaxationModifier: 0.0");
            bonuses.add("economyGrowthModifier: 0.0");
      }

      return bonuses;
   }

   @Override
   public void actionElement(int iID) {
      switch (iID) {
         case 0:
         case 1:
         case 2:
         case 6:
         case 12:
         case 17:
         case 22:
          case 29:
          default:
             if (this.aiButtonsStart >= 0 && iID >= this.aiButtonsStart && iID < this.aiButtonsEnd) {
                this.getMenuElement(iID).actionElement(iID);
             }

             break;
         case 3:
            if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).civGameData.EconomyType == 1) {
               return;
            }

            if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoney() <= this.costChangePolitic(1.1F)) {
               CFG.toast.setInView(CFG.langManager.get("не хватает: " + this.costChangePolitic(1.1F) + "монет"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE);
               return;
            }

            if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getDiplomacyPoints() <= 7.0) {
               CFG.toast.setInView(CFG.langManager.get("не хватает дипломатических: 7 очков"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE);
               return;
            }

            CFG.game
               .getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
               .setDiplomacyPoints(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getDiplomacyPoints() - 5);
            CFG.game
               .getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
               .setMoney(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoney() - this.costChangePolitic(1.1F));
            CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).civGameData.EconomyType = 1;
            CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).setEconomyType(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
            CFG.menuManager.updateInGame_TOP_All(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
            CFG.toast.setInView(CFG.langManager.get("NewModeHasBeenSet"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE);
            this.setVisible(false);
            break;
         case 4:
            if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).civGameData.EconomyType == 2) {
               return;
            }

            if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoney() <= this.costChangePolitic(1.1F)) {
               CFG.toast.setInView(CFG.langManager.get("не хватает денег: " + this.costChangePolitic(1.1F)), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE);
               return;
            }

            if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getDiplomacyPoints() <= 7.0) {
               CFG.toast.setInView(CFG.langManager.get("не хватает дипломатических: 7 очков"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE);
               return;
            }

            CFG.game
               .getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
               .setDiplomacyPoints(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getDiplomacyPoints() - 5);
            CFG.game
               .getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
               .setMoney(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoney() - this.costChangePolitic(1.1F));
            CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).civGameData.EconomyType = 2;
            CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).setEconomyType(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
            CFG.menuManager.updateInGame_TOP_All(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
            CFG.toast.setInView(CFG.langManager.get("NewModeHasBeenSet"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE);
            this.setVisible(false);
            break;
         case 5:
            if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).civGameData.EconomyType == 3) {
               return;
            }

            if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoney() <= this.costChangePolitic(1.1F)) {
               CFG.toast.setInView(CFG.langManager.get("не хватает денег: " + this.costChangePolitic(1.1F)), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE);
               return;
            }

            if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getDiplomacyPoints() <= 7.0) {
               CFG.toast.setInView(CFG.langManager.get("не хватает дипломатических: 7 очков"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE);
               return;
            }

            CFG.game
               .getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
               .setDiplomacyPoints(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getDiplomacyPoints() - 5);
            CFG.game
               .getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
               .setMoney(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoney() - this.costChangePolitic(1.1F));
            CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).civGameData.EconomyType = 3;
            CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).setEconomyType(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
            CFG.menuManager.updateInGame_TOP_All(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
            CFG.toast.setInView(CFG.langManager.get("NewModeHasBeenSet"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE);
            this.setVisible(false);
            break;
         case 7:
            if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).civGameData.LevelCentralization == 1) {
               return;
            }

            if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoney() <= this.costChangePolitic(0.43F)) {
               CFG.toast.setInView(CFG.langManager.get("не хватает денег: " + this.costChangePolitic(0.43F)), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE);
               return;
            }

            if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getDiplomacyPoints() <= 5.0) {
               CFG.toast.setInView(CFG.langManager.get("не хватает дипломатических: 5 очков"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE);
               return;
            }

            CFG.game
               .getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
               .setDiplomacyPoints(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getDiplomacyPoints() - 5);
            CFG.game
               .getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
               .setMoney(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoney() - this.costChangePolitic(0.43F));
            CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).civGameData.LevelCentralization = 1;
            CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).setCentralization(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
            CFG.menuManager.updateInGame_TOP_All(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
            CFG.toast.setInView(CFG.langManager.get("NewModeHasBeenSet"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE);
            this.setVisible(false);
            break;
         case 8:
            if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).civGameData.LevelCentralization == 2) {
               return;
            }

            if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoney() <= this.costChangePolitic(0.55F)) {
               CFG.toast.setInView(CFG.langManager.get("не хватает денег: " + this.costChangePolitic(0.55F)), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE);
               return;
            }

            if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getDiplomacyPoints() <= 5.0) {
               CFG.toast.setInView(CFG.langManager.get("не хватает дипломатических: 5 очков"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE);
               return;
            }

            CFG.game
               .getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
               .setDiplomacyPoints(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getDiplomacyPoints() - 5);
            CFG.game
               .getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
               .setMoney(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoney() - this.costChangePolitic(0.55F));
            CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).civGameData.LevelCentralization = 2;
            CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).setCentralization(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
            CFG.menuManager.updateInGame_TOP_All(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
            CFG.toast.setInView(CFG.langManager.get("NewModeHasBeenSet"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE);
            this.setVisible(false);
            break;
         case 9:
            if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).civGameData.LevelCentralization == 3) {
               return;
            }

            if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoney() <= this.costChangePolitic(0.75F)) {
               CFG.toast.setInView(CFG.langManager.get("не хватает денег: " + this.costChangePolitic(0.75F)), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE);
               return;
            }

            if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getDiplomacyPoints() <= 5.0) {
               CFG.toast.setInView(CFG.langManager.get("не хватает дипломатических: 5 очков"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE);
               return;
            }

            CFG.game
               .getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
               .setDiplomacyPoints(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getDiplomacyPoints() - 5);
            CFG.game
               .getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
               .setMoney(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoney() - this.costChangePolitic(0.75F));
            CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).civGameData.LevelCentralization = 3;
            CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).setCentralization(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
            CFG.menuManager.updateInGame_TOP_All(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
            CFG.toast.setInView(CFG.langManager.get("NewModeHasBeenSet"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE);
            this.setVisible(false);
            break;
         case 10:
            if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).civGameData.LevelCentralization == 4) {
               return;
            }

            if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoney() <= this.costChangePolitic(0.9F)) {
               CFG.toast.setInView(CFG.langManager.get("не хватает денег: " + this.costChangePolitic(0.9F)), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE);
               return;
            }

            if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getDiplomacyPoints() <= 5.0) {
               CFG.toast.setInView(CFG.langManager.get("не хватает дипломатических: 5 очков"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE);
               return;
            }

            CFG.game
               .getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
               .setDiplomacyPoints(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getDiplomacyPoints() - 5);
            CFG.game
               .getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
               .setMoney(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoney() - this.costChangePolitic(0.9F));
            CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).civGameData.LevelCentralization = 4;
            CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).setCentralization(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
            CFG.menuManager.updateInGame_TOP_All(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
            CFG.toast.setInView(CFG.langManager.get("NewModeHasBeenSet"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE);
            this.setVisible(false);
            break;
         case 11:
            if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).civGameData.LevelCentralization == 5) {
               return;
            }

            if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoney() <= this.costChangePolitic(1.03F)) {
               CFG.toast.setInView(CFG.langManager.get("не хватает денег: " + this.costChangePolitic(1.03F)), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE);
               return;
            }

            if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getDiplomacyPoints() <= 5.0) {
               CFG.toast.setInView(CFG.langManager.get("не хватает дипломатических: 5 очков"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE);
               return;
            }

            CFG.game
               .getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
               .setDiplomacyPoints(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getDiplomacyPoints() - 5);
            CFG.game
               .getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
               .setMoney(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoney() - this.costChangePolitic(1.03F));
            CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).civGameData.LevelCentralization = 5;
            CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).setCentralization(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
            CFG.menuManager.updateInGame_TOP_All(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
            CFG.toast.setInView(CFG.langManager.get("NewModeHasBeenSet"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE);
            this.setVisible(false);
            break;
         case 13:
            if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).civGameData.LevelMedicine == 1) {
               return;
            }

            if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoney() <= this.costChangePolitic(0.65F)) {
               CFG.toast.setInView(CFG.langManager.get("не хватает денег: " + this.costChangePolitic(0.65F)), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE);
               return;
            }

            if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getDiplomacyPoints() <= 5.0) {
               CFG.toast.setInView(CFG.langManager.get("не хватает дипломатических: 5 очков"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE);
               return;
            }

            CFG.game
               .getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
               .setDiplomacyPoints(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getDiplomacyPoints() - 5);
            CFG.game
               .getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
               .setMoney(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoney() - this.costChangePolitic(0.65F));
            CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).civGameData.LevelMedicine = 1;
            CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).setMedicene(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
            CFG.toast.setInView(CFG.langManager.get("NewModeHasBeenSet"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE);
            this.setVisible(false);
            break;
         case 14:
            if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).civGameData.LevelMedicine == 2) {
               return;
            }

            if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoney() <= this.costChangePolitic(0.85F)) {
               CFG.toast.setInView(CFG.langManager.get("не хватает денег: " + this.costChangePolitic(0.85F)), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE);
               return;
            }

            if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getDiplomacyPoints() <= 5.0) {
               CFG.toast.setInView(CFG.langManager.get("не хватает дипломатических: 5 очков"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE);
               return;
            }

            CFG.game
               .getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
               .setDiplomacyPoints(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getDiplomacyPoints() - 5);
            CFG.game
               .getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
               .setMoney(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoney() - this.costChangePolitic(0.85F));
            CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).civGameData.LevelMedicine = 2;
            CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).setMedicene(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
            CFG.toast.setInView(CFG.langManager.get("NewModeHasBeenSet"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE);
            this.setVisible(false);
            break;
         case 15:
            if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).civGameData.LevelMedicine == 3) {
               return;
            }

            if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoney() <= this.costChangePolitic(1.0F)) {
               CFG.toast.setInView(CFG.langManager.get("не хватает денег: " + this.costChangePolitic(1.0F)), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE);
               return;
            }

            if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getDiplomacyPoints() <= 5.0) {
               CFG.toast.setInView(CFG.langManager.get("не хватает дипломатических: 5 очков"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE);
               return;
            }

            CFG.game
               .getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
               .setDiplomacyPoints(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getDiplomacyPoints() - 5);
            CFG.game
               .getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
               .setMoney(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoney() - this.costChangePolitic(1.0F));
            CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).civGameData.LevelMedicine = 3;
            CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).setMedicene(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
            CFG.toast.setInView(CFG.langManager.get("NewModeHasBeenSet"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE);
            this.setVisible(false);
            break;
         case 16:
            if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).civGameData.LevelMedicine == 4) {
               return;
            }

            if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoney() <= this.costChangePolitic(1.095F)) {
               CFG.toast.setInView(CFG.langManager.get("не хватает денег: " + this.costChangePolitic(1.095F)), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE);
               return;
            }

            if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getDiplomacyPoints() <= 5.0) {
               CFG.toast.setInView(CFG.langManager.get("не хватает дипломатических: 5 очков"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE);
               return;
            }

            CFG.game
               .getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
               .setDiplomacyPoints(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getDiplomacyPoints() - 5);
            CFG.game
               .getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
               .setMoney(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoney() - this.costChangePolitic(1.095F));
            CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).civGameData.LevelMedicine = 4;
            CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).setMedicene(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
            CFG.toast.setInView(CFG.langManager.get("NewModeHasBeenSet"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE);
            this.setVisible(false);
            break;
         case 18:
            if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).civGameData.LevelEducation == 1) {
               return;
            }

            CFG.game
               .getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
               .setDiplomacyPoints(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getDiplomacyPoints() - 5);
            CFG.game
               .getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
               .setMoney(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoney() - this.costChangePolitic(0.65F));
            CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).civGameData.LevelEducation = 1;
            CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).setEducation(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
            CFG.toast.setInView(CFG.langManager.get("NewModeHasBeenSet"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE);
            this.setVisible(false);
            break;
         case 19:
            if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).civGameData.LevelEducation == 2) {
               return;
            }

            CFG.game
               .getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
               .setDiplomacyPoints(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getDiplomacyPoints() - 5);
            CFG.game
               .getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
               .setMoney(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoney() - this.costChangePolitic(0.65F));
            CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).civGameData.LevelEducation = 2;
            CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).setEducation(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
            CFG.toast.setInView(CFG.langManager.get("NewModeHasBeenSet"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE);
            this.setVisible(false);
            break;
         case 20:
            if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).civGameData.LevelEducation == 3) {
               return;
            }

            CFG.game
               .getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
               .setDiplomacyPoints(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getDiplomacyPoints() - 5);
            CFG.game
               .getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
               .setMoney(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoney() - this.costChangePolitic(0.8F));
            CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).civGameData.LevelEducation = 3;
            CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).setEducation(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
            CFG.toast.setInView(CFG.langManager.get("NewModeHasBeenSet"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE);
            this.setVisible(false);
            break;
         case 21:
            if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).civGameData.LevelEducation == 4) {
               return;
            }

            CFG.game
               .getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
               .setDiplomacyPoints(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getDiplomacyPoints() - 5);
            CFG.game
               .getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
               .setMoney(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoney() - this.costChangePolitic(1.0F));
            CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).civGameData.LevelEducation = 4;
            CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).setEducation(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
            CFG.toast.setInView(CFG.langManager.get("NewModeHasBeenSet"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE);
            this.setVisible(false);
            break;
         case 23:
            if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).civGameData.LevelMilitarySpending == 1) {
               return;
            }

            CFG.game
               .getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
               .setDiplomacyPoints(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getDiplomacyPoints() - 5);
            CFG.game
               .getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
               .setMoney(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoney() - this.costChangePolitic(0.4F));
            CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).civGameData.LevelMilitarySpending = 1;
            CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).setMilitarySpending(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
            CFG.toast.setInView(CFG.langManager.get("NewModeHasBeenSet"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE);
            this.setVisible(false);
            break;
         case 24:
            if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).civGameData.LevelMilitarySpending == 2) {
               return;
            }

            CFG.game
               .getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
               .setDiplomacyPoints(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getDiplomacyPoints() - 5);
            CFG.game
               .getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
               .setMoney(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoney() - this.costChangePolitic(0.55F));
            CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).civGameData.LevelMilitarySpending = 2;
            CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).setMilitarySpending(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
            CFG.toast.setInView(CFG.langManager.get("NewModeHasBeenSet"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE);
            this.setVisible(false);
            break;
         case 25:
            if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).civGameData.LevelMilitarySpending == 3) {
               return;
            }

            CFG.game
               .getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
               .setDiplomacyPoints(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getDiplomacyPoints() - 5);
            CFG.game
               .getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
               .setMoney(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoney() - this.costChangePolitic(0.7F));
            CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).civGameData.LevelMilitarySpending = 3;
            CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).setMilitarySpending(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
            CFG.toast.setInView(CFG.langManager.get("NewModeHasBeenSet"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE);
            this.setVisible(false);
            break;
         case 26:
            if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).civGameData.LevelMilitarySpending == 4) {
               return;
            }

            CFG.game
               .getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
               .setDiplomacyPoints(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getDiplomacyPoints() - 5);
            CFG.game
               .getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
               .setMoney(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoney() - this.costChangePolitic(0.85F));
            CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).civGameData.LevelMilitarySpending = 4;
            CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).setMilitarySpending(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
            CFG.toast.setInView(CFG.langManager.get("NewModeHasBeenSet"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE);
            this.setVisible(false);
            break;
         case 27:
            if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).civGameData.LevelMilitarySpending == 5) {
               return;
            }

            CFG.game
               .getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
               .setDiplomacyPoints(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getDiplomacyPoints() - 5);
            CFG.game
               .getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
               .setMoney(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoney() - this.costChangePolitic(1.0F));
            CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).civGameData.LevelMilitarySpending = 5;
            CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).setMilitarySpending(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
            CFG.toast.setInView(CFG.langManager.get("NewModeHasBeenSet"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE);
            this.setVisible(false);
            break;
         case 28:
            if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).civGameData.LevelMilitarySpending == 6) {
               return;
            }

            CFG.game
               .getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
               .setDiplomacyPoints(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getDiplomacyPoints() - 5);
            CFG.game
               .getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
               .setMoney(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoney() - this.costChangePolitic(1.1F));
            CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).civGameData.LevelMilitarySpending = 6;
            CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).setMilitarySpending(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
            CFG.toast.setInView(CFG.langManager.get("NewModeHasBeenSet"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE);
            this.setVisible(false);
            break;
         case 30:
            if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).civGameData.LevelMilitaryTactic == 1) {
               return;
            }

            CFG.game
               .getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
               .setDiplomacyPoints(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getDiplomacyPoints() - 5);
            CFG.game
               .getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
               .setMoney(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoney() - this.costChangePolitic(0.4F));
            CFG.game
               .getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
               .setMilitaryPoints(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMilitaryPoints() - 50);
            CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).civGameData.LevelMilitaryTactic = 1;
            CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).setMilitaryTactic(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
            CFG.toast.setInView(CFG.langManager.get("NewModeHasBeenSet"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE);
            this.setVisible(false);
            break;
         case 31:
            if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).civGameData.LevelMilitaryTactic == 2) {
               return;
            }

            CFG.game
               .getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
               .setDiplomacyPoints(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getDiplomacyPoints() - 5);
            CFG.game
               .getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
               .setMoney(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoney() - this.costChangePolitic(0.4F));
            CFG.game
               .getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
               .setMilitaryPoints(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMilitaryPoints() - 50);
            CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).civGameData.LevelMilitaryTactic = 2;
            CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).setMilitaryTactic(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
            CFG.toast.setInView(CFG.langManager.get("NewModeHasBeenSet"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE);
            this.setVisible(false);
            break;
         case 32:
            if (CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).civGameData.LevelMilitaryTactic == 3) {
               return;
            }

            CFG.game
               .getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
               .setDiplomacyPoints(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getDiplomacyPoints() - 5);
            CFG.game
               .getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
               .setMoney(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoney() - this.costChangePolitic(0.4F));
            CFG.game
               .getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
               .setMilitaryPoints(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMilitaryPoints() - 50);
            CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).civGameData.LevelMilitaryTactic = 3;
            CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).setMilitaryTactic(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
            CFG.toast.setInView(CFG.langManager.get("NewModeHasBeenSet"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE);
            this.setVisible(false);
      }
   }

   @Override
   public void drawScrollPos(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      if (sliderMenuIsActive) {
         super.drawScrollPos(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
      }
   }

   public final int getW() {
      return this.getWidth() - 4;
   }

   public final int getElementW() {
      return this.getW() / 2;
   }
}
