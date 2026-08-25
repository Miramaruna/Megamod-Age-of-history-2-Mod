package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

public class Menu_InGame_ActionArmy extends SliderMenu {
   public static final int ANIMATION_TIME = 175;
   public static long lTime = 0L;
   public static boolean hideAnimation = true;
   private CustomBuildingsManager customBuildingsManager = CFG.game.getGame_CustomBuildingsManager();

   public Menu_InGame_ActionArmy() {
      int tempW = CFG.MENU_WIDTH_ACTION_ARMY;
      int tPosY = 0;
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      if (BuildingsManager.iBuildInProvinceID >= 0) {
         boolean canDestroy = true;
         int tRow = 0;
         int tempBuildings = menuElements.size();
         menuElements.add(
            new Button_ActionArmy(
               CFG.langManager.get("Mobilator"),
               Images.editor_leaders,
               DiplomacyManager.festivalCost(BuildingsManager.iBuildInProvinceID),
               8,
               0,
               tPosY,
               tempW,
               true,
               false,
               CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).isFestivalOrganized_TurnsLeft(BuildingsManager.iBuildInProvinceID),
               0.0F,
               CFG.COLOR_TEXT_MODIFIER_POSITIVE
            ) {
               @Override
               public void actionElement(int iID) {
                  CFG.menuManager.rebuildInGame_mobilization(CFG.getActiveCivInfo());
               }

               @Override
               public void buildElementHover() {
                  ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
                  ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
                  nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Mobilisation"), CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
                  nElements.add(new MenuElement_Hover_v2_Element2(nData));
                  nData.clear();
                  this.menuElementHover = new MenuElement_Hover_v2(nElements);
               }
            }
         );
         menuElements.get(menuElements.size() - 1).setHeight(CFG.BUTTON_HEIGHT * 3 / 5);
         int var8;
         menuElements.add(
            new Button_ActionArmy(
               CFG.langManager.get("TroopDistribution"),
               Images.provinces,
               DiplomacyManager.assimilateCost(BuildingsManager.iBuildInProvinceID, 1),
               6,
               0,
               var8 = tPosY + menuElements.get(menuElements.size() - 1).getHeight(),
               tempW,
               !CFG.game.getProvince(BuildingsManager.iBuildInProvinceID).isOccupied(),
               false,
               CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).isAssimialateOrganized_TurnsLeft(BuildingsManager.iBuildInProvinceID),
               0.0F,
               CFG.COLOR_BUTTON_GAME_TEXT
            ) {
               @Override
               public void actionElement(int iID) {
                  CFG.menuManager.rebuildInGame_troopDistribution(CFG.getActiveCivInfo());
               }

               @Override
               public void buildElementHover() {
                  ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
                  ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
                  nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("TroopDistribution"), CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
                  nElements.add(new MenuElement_Hover_v2_Element2(nData));
                  nData.clear();
                  this.menuElementHover = new MenuElement_Hover_v2(nElements);
               }
            }
         );
         menuElements.get(menuElements.size() - 1).setHeight(CFG.BUTTON_HEIGHT * 3 / 5);
         menuElements.add(
            new Button_ActionArmy(
               CFG.langManager.get("autoplan"),
               Images.act_moveto,
               0,
               12,
               0,
               tPosY = var8 + menuElements.get(menuElements.size() - 1).getHeight(),
               tempW,
               true,
               false,
               CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).isInvestOrganized_TurnsLeft(BuildingsManager.iBuildInProvinceID),
               0.0F,
               CFG.COLOR_FORT_2
            ) {
               @Override
               public void actionElement(int iID) {
                  CFG.menuManager.rebuildInGame_AutoPlan(CFG.game.getCiv(CFG.game.getActiveProvinceID()).iCivID);
               }

               @Override
               public void buildElementHover() {
                  ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
                  ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
                  nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("autoplan"), CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
                  nElements.add(new MenuElement_Hover_v2_Element2(nData));
                  nData.clear();
                  this.menuElementHover = new MenuElement_Hover_v2(nElements);
               }
            }
         );
         menuElements.get(menuElements.size() - 1).setHeight(CFG.BUTTON_HEIGHT * 3 / 5);
         int var10;
         menuElements.add(
            new Button_ActionArmy(
               CFG.langManager.get("deMobilator"),
               Images.editor_leaders,
               DiplomacyManager.festivalCost(BuildingsManager.iBuildInProvinceID),
               8,
               0,
               var10 = tPosY + menuElements.get(menuElements.size() - 1).getHeight(),
               tempW,
               true,
               false,
               CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).isFestivalOrganized_TurnsLeft(BuildingsManager.iBuildInProvinceID),
               0.0F,
               new Color(0.627451F, 0.09803922F, 0.078431375F, 1.0F)
            ) {
               @Override
               public void actionElement(int iID) {
                  CFG.menuManager.rebuildInGame_demobilization(CFG.game.getCiv(CFG.game.getActiveProvinceID()).iCivID);
               }

               @Override
               public void buildElementHover() {
                  ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
                  ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
                  nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("demobilisation"), CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
                  nElements.add(new MenuElement_Hover_v2_Element2(nData));
                  nData.clear();
                  this.menuElementHover = new MenuElement_Hover_v2(nElements);
               }
            }
         );
         menuElements.get(menuElements.size() - 1).setHeight(CFG.BUTTON_HEIGHT * 3 / 5);
         menuElements.add(
            new Button_ActionArmy(
               CFG.langManager.get("Genocide"),
               Images.skull,
               0,
               8,
               0,
               tPosY = var10 + menuElements.get(menuElements.size() - 1).getHeight(),
               tempW,
               true,
               false,
               CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).isInvestOrganized_TurnsLeft_Development(BuildingsManager.iBuildInProvinceID),
               0.0F,
               CFG.COLOR_ARMY_BG
            ) {
               @Override
               public void actionElement(int iID) {
                  if (CFG.game.getActiveProvinceID() >= 0) {
                     if (Menu_InGame_Genocide.iProvinceID == CFG.game.getActiveProvinceID() && CFG.menuManager.getInGame_Genocide().getVisible()) {
                        CFG.menuManager.getInGame_Genocide().actionElement(CFG.menuManager.getInGame_Genocide().getMenuElementsSize() - 1);
                     } else {
                        CFG.menuManager.rebuildInGame_Genocide(CFG.game.getActiveProvinceID());
                     }
                  }
               }

               @Override
               public void buildElementHover() {
                  ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
                  ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
                  nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Genocide"), CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
                  nElements.add(new MenuElement_Hover_v2_Element2(nData));
                  nData.clear();
                  this.menuElementHover = new MenuElement_Hover_v2(nElements);
               }
            }
         );
         menuElements.get(menuElements.size() - 1).setHeight(CFG.BUTTON_HEIGHT * 3 / 5);
         tPosY += menuElements.get(menuElements.size() - 1).getHeight();

         if (AI_Assistant.ENABLED) {
            menuElements.add(
               new Button_ActionArmy(
                  CFG.langManager.get("ArmyPriority"),
                  Images.act_moveto,
                  0,
                  12,
                  0,
                  tPosY,
                  tempW,
                  true,
                  false,
                  0,
                  0.0F,
                  CFG.COLOR_TEXT_MODIFIER_POSITIVE
               ) {
                  @Override
                  public void actionElement(int iID) {
                     CFG.game.getPlayer(CFG.PLAYER_TURNID).iACTIVE_VIEW_MODE = CFG.viewsManager.getActiveViewID();
                     CFG.viewsManager.disableAllViews();
                     CFG.game.setActiveProvinceID(-1);
                     Menu_InGame_SelectProvinces.typeOfAction = Menu_InGame_SelectProvinces.TypeOfAction.ARMY_PRIORITY;
                     CFG.VIEW_SHOW_VALUES = false;
                     CFG.selectMode = true;
                     CFG.game.getSelectedProvinces().clearSelectedProvinces();

                     for (int tCivID : AI_Assistant.PRIORITY_COUNTRIES) {
                        for (int p = 0; p < CFG.game.getCiv(tCivID).getNumOfProvinces(); p++) {
                           CFG.game.getSelectedProvinces().addProvince(CFG.game.getCiv(tCivID).getProvinceID(p));
                        }
                     }

                     CFG.menuManager.setViewID(Menu.eINGAME_SELECT_PROVINCES);
                     Game_Render_Province.updateDrawProvinces();
                  }

                  @Override
                  public void buildElementHover() {
                     ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
                     ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("ArmyPriority_Info"), CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     this.menuElementHover = new MenuElement_Hover_v2(nElements);
                  }
               }
            );
            menuElements.get(menuElements.size() - 1).setHeight(CFG.BUTTON_HEIGHT * 3 / 5);
            tPosY += menuElements.get(menuElements.size() - 1).getHeight();
         }

         {
            menuElements.add(
               new Button_ActionArmy(
                  CFG.langManager.get("FortStrip") + (AI_Assistant.FORT_STRIP_PROVINCES.isEmpty() ? "" : ": " + AI_Assistant.FORT_STRIP_PROVINCES.size()),
                  Images.act_moveto,
                  0,
                  12,
                  0,
                  tPosY,
                  tempW,
                  true,
                  false,
                  0,
                  0.0F,
                  CFG.COLOR_BUTTON_GAME_TEXT
               ) {
                  @Override
                  public void actionElement(int iID) {
                     CFG.game.getPlayer(CFG.PLAYER_TURNID).iACTIVE_VIEW_MODE = CFG.viewsManager.getActiveViewID();
                     CFG.viewsManager.disableAllViews();
                     CFG.game.setActiveProvinceID(-1);
                     Menu_InGame_SelectProvinces.typeOfAction = Menu_InGame_SelectProvinces.TypeOfAction.FORT_BORDER;
                     CFG.brushTool = true;
                     CFG.VIEW_SHOW_VALUES = false;
                     CFG.selectMode = true;
                     CFG.game.getSelectedProvinces().clearSelectedProvinces();

                     for (int tp = 0; tp < AI_Assistant.FORT_STRIP_PROVINCES.size(); tp++) {
                        int tProvID = AI_Assistant.FORT_STRIP_PROVINCES.get(tp);
                        if (CFG.game.getProvince(tProvID).getCivID() == CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) {
                           CFG.game.getSelectedProvinces().addProvince(tProvID);
                        }
                     }

                     CFG.menuManager.setViewID(Menu.eINGAME_SELECT_PROVINCES);
                     Game_Render_Province.updateDrawProvinces();
                  }

                  @Override
                  public void buildElementHover() {
                     ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
                     ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("FortStrip_Info2")));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     this.menuElementHover = new MenuElement_Hover_v2(nElements);
                  }
               }
            );
            menuElements.get(menuElements.size() - 1).setHeight(CFG.BUTTON_HEIGHT * 3 / 5);
            tPosY += menuElements.get(menuElements.size() - 1).getHeight();

            menuElements.add(
               new Button_ActionArmy(
                  CFG.langManager.get("Garrison") + (AI_Assistant.GARRISON_PROVINCES.isEmpty() ? "" : ": " + AI_Assistant.GARRISON_PROVINCES.size()),
                  Images.act_moveto,
                  0,
                  12,
                  0,
                  tPosY,
                  tempW,
                  true,
                  false,
                  0,
                  0.0F,
                  CFG.COLOR_TEXT_MODIFIER_POSITIVE
               ) {
                  @Override
                  public void actionElement(int iID) {
                     CFG.game.getPlayer(CFG.PLAYER_TURNID).iACTIVE_VIEW_MODE = CFG.viewsManager.getActiveViewID();
                     CFG.viewsManager.disableAllViews();
                     CFG.game.setActiveProvinceID(-1);
                     Menu_InGame_SelectProvinces.typeOfAction = Menu_InGame_SelectProvinces.TypeOfAction.GARRISON_SELECT;
                     CFG.brushTool = true;
                     CFG.VIEW_SHOW_VALUES = false;
                     CFG.selectMode = true;
                     CFG.game.getSelectedProvinces().clearSelectedProvinces();

                     for (int tp = 0; tp < AI_Assistant.GARRISON_PROVINCES.size(); tp++) {
                        int tProvID = AI_Assistant.GARRISON_PROVINCES.get(tp);
                        if (CFG.game.getProvince(tProvID).getCivID() == CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) {
                           CFG.game.getSelectedProvinces().addProvince(tProvID);
                        }
                     }

                     CFG.menuManager.setViewID(Menu.eINGAME_SELECT_PROVINCES);
                     Game_Render_Province.updateDrawProvinces();
                  }

                  @Override
                  public void buildElementHover() {
                     ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
                     ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Garrison_Info")));
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     this.menuElementHover = new MenuElement_Hover_v2(nElements);
                  }
               }
            );
            menuElements.get(menuElements.size() - 1).setHeight(CFG.BUTTON_HEIGHT * 3 / 5);
            tPosY += menuElements.get(menuElements.size() - 1).getHeight();
         }

         for (int i = tempBuildings; i < menuElements.size(); i++) {
            menuElements.get(i).setCurrent(i % 2);
         }

         this.initMenu(
            new SliderMenuTitle(null, CFG.BUTTON_HEIGHT * 3 / 5, false, false) {
               @Override
               public void draw(SpriteBatch oSB, int iTranslateX, int nPosX, int nPosY, int nWidth, boolean sliderMenuIsActive) {
                  ImageManager.getImage(Images.new_game_top_edge_title)
                     .draw2(
                        oSB,
                        Menu_InGame_ActionArmy.this.getPosX() + iTranslateX,
                        Menu_InGame_ActionArmy.this.getPosY() - ImageManager.getImage(Images.new_game_top_edge_title).getHeight() - this.getHeight(),
                        Menu_InGame_ActionArmy.this.getWidth() + 2,
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
                  oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.6F));
                  ImageManager.getImage(Images.line_32_off1)
                     .draw(
                        oSB,
                        Menu_InGame_ActionArmy.this.getPosX() + iTranslateX,
                        Menu_InGame_ActionArmy.this.getPosY()
                           - ImageManager.getImage(Images.pix255_255_255).getHeight()
                           - ImageManager.getImage(Images.line_32_off1).getHeight(),
                        Menu_InGame_ActionArmy.this.getWidth(),
                        1
                     );
                  oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.8F));
                  ImageManager.getImage(Images.slider_gradient)
                     .draw(
                        oSB,
                        Menu_InGame_ActionArmy.this.getPosX() + iTranslateX,
                        Menu_InGame_ActionArmy.this.getPosY()
                           - ImageManager.getImage(Images.pix255_255_255).getHeight()
                           - ImageManager.getImage(Images.slider_gradient).getHeight(),
                        Menu_InGame_ActionArmy.this.getWidth() / 4,
                        1
                     );
                  ImageManager.getImage(Images.slider_gradient)
                     .draw(
                        oSB,
                        Menu_InGame_ActionArmy.this.getPosX()
                           + Menu_InGame_ActionArmy.this.getWidth()
                           - Menu_InGame_ActionArmy.this.getWidth() / 4
                           + iTranslateX,
                        Menu_InGame_ActionArmy.this.getPosY()
                           - ImageManager.getImage(Images.pix255_255_255).getHeight()
                           - ImageManager.getImage(Images.slider_gradient).getHeight(),
                        Menu_InGame_ActionArmy.this.getWidth() / 4,
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
                        Menu_InGame_ActionArmy.this.getPosX()
                           + CFG.PADDING
                           + Button_Diplomacy.iDiploWidth / 2
                           - ImageManager.getImage(Images.flag_rect).getWidth() / 2
                           + iTranslateX,
                        Menu_InGame_ActionArmy.this.getPosY()
                           - this.getHeight() / 2
                           - CFG.CIV_FLAG_HEIGHT / 2
                           - CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getFlag().getHeight(),
                        CFG.CIV_FLAG_WIDTH,
                        CFG.CIV_FLAG_HEIGHT
                     );
                  ImageManager.getImage(Images.flag_rect)
                     .draw(
                        oSB,
                        Menu_InGame_ActionArmy.this.getPosX()
                           + CFG.PADDING
                           + Button_Diplomacy.iDiploWidth / 2
                           - ImageManager.getImage(Images.flag_rect).getWidth() / 2
                           + iTranslateX,
                        Menu_InGame_ActionArmy.this.getPosY() - this.getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2
                     );
                  CFG.fontMain.getData().setScale(0.8F);
                  CFG.fontMain.getData().setScale(1.0F);
               }
            },
            0,
            ImageManager.getImage(Images.top_left).getHeight() + CFG.PADDING * 3 + CFG.BUTTON_HEIGHT * 3 / 5,
            tempW,
            Math.min(
               tPosY,
               CFG.GAME_HEIGHT
                  - (
                     ImageManager.getImage(Images.top_left).getHeight()
                        + CFG.PADDING * 3
                        + CFG.BUTTON_HEIGHT * 3 / 4
                        + (CFG.PADDING * 2 + CFG.BUTTON_HEIGHT) * 2
                  )
            ),
            menuElements,
            false,
            true
         );
         if (BuildingsManager.iBuildInProvinceID < 0) {
            this.setVisible(false);
         }

         this.updateLanguage();
      }
   }

   @Override
   public void updateLanguage() {
      this.getTitle()
         .setText(
            CFG.game.getProvince(BuildingsManager.iBuildInProvinceID).getName().length() > 0
               ? CFG.game.getProvince(BuildingsManager.iBuildInProvinceID).getName()
               : CFG.langManager.get("More")
         );
   }

   @Override
   public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      if (lTime + 175L >= System.currentTimeMillis()) {
         int var5;
         int var6;
         iTranslateX = hideAnimation
            ? (var5 = iTranslateX - (int)(this.getWidth() * ((float)(System.currentTimeMillis() - lTime) / 175.0F)))
            : (var6 = iTranslateX + -this.getWidth() + (int)(this.getWidth() * ((float)(System.currentTimeMillis() - lTime) / 175.0F)));
         CFG.setRender_3(true);
      } else if (hideAnimation) {
         super.setVisible(false);
         return;
      }

      ImageManager.getImage(Images.new_game_top_edge_line)
         .draw2(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.new_game_top_edge_line).getHeight() + iTranslateY,
            this.getWidth() + 2,
            this.getHeight() + CFG.PADDING,
            true,
            true
         );
      oSB.setColor(new Color(0.09803922F, 0.05882353F, 0.37254903F, 0.25F));
      ImageManager.getImage(Images.gradient)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY,
            this.getWidth(),
            CFG.PADDING * 4
         );
      oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.65F));
      ImageManager.getImage(Images.gradient)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY,
            this.getWidth(),
            CFG.PADDING * 2
         );
      oSB.setColor(Color.WHITE);
      super.draw(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
   }

   @Override
   public void drawScrollPos(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      if (sliderMenuIsActive) {
         super.drawScrollPos(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
      }
   }

   @Override
   public void actionElement(int iID) {
      if (!CFG.SPECTATOR_MODE) {
         this.getMenuElement(iID).actionElement(iID);
      }
   }

   @Override
   public void setVisible(boolean visible) {
      if (visible) {
         super.setVisible(visible);
         this.setHideAnimation(false);
      } else {
         this.setHideAnimation(true);
      }
   }

   @Override
   public void drawCloseButton(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
   }

   public final void setHideAnimation(boolean nHideAnimation) {
      if (nHideAnimation != hideAnimation) {
         lTime = lTime > System.currentTimeMillis() - 175L
            ? System.currentTimeMillis() - (175L - (System.currentTimeMillis() - lTime))
            : System.currentTimeMillis();
         CFG.setRender_3(true);
      }

      hideAnimation = nHideAnimation;
   }
}
