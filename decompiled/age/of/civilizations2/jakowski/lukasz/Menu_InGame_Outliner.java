package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

public class Menu_InGame_Outliner extends SliderMenu {
   public final float FONT_SCALE = 0.7F;
   public static final int ANIMATION_TIME = 135;
   public static long lTime = 0L;
   public static boolean hideAnimation = true;

   public Menu_InGame_Outliner() {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      int tMenuWidth = CFG.CIV_INFO_MENU_WIDTH / 2;
      int tElementH = CFG.isAndroid() ? Math.max(CFG.BUTTON_HEIGHT / 2, CFG.TEXT_HEIGHT + CFG.PADDING * 4) : CFG.TEXT_HEIGHT + CFG.PADDING * 3;
      int tPosY = 0;
      menuElements.add(new Text_Outliner(null, CFG.PADDING * 2, 2, tPosY, tMenuWidth - 2, tElementH) {
         @Override
         public void actionElement(int iID) {
            if (CFG.menuManager.getVisibleInGame_VictoryConditions()) {
               CFG.menuManager.setVisibleInGame_VictoryConditions(false);
            } else {
               CFG.menuManager.rebuildInGame_VictoryConditions();
            }
         }
      });
      int var6;
      menuElements.add(new Text_Outliner(null, CFG.PADDING * 2, 2, var6 = tPosY + tElementH, tMenuWidth - 2, tElementH) {
         @Override
         public void actionElement(int iID) {
            if (CFG.menuManager.getVisibleInGame_Wars()) {
               CFG.menuManager.setVisibleInGame_Wars(false);
            } else {
               CFG.menuManager.rebuildInGame_Wars();
            }
         }

         @Override
         public void buildElementHover() {
            if (CFG.isDesktop()) {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Shortcut") + ": "));
               nData.add(new MenuElement_Hover_v2_Element_Type_Text("F6", CFG.COLOR_TEXT_NUM_OF_PROVINCES));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               this.menuElementHover = new MenuElement_Hover_v2(nElements);
            } else {
               this.menuElementHover = null;
            }
         }
      });
      menuElements.add(new Text_Outliner(null, CFG.PADDING * 2, 2, tPosY = var6 + tElementH, tMenuWidth - 2, tElementH) {
         @Override
         public void actionElement(int iID) {
            if (CFG.menuManager.getVisibleInGame_MilitaryAlliances()) {
               CFG.menuManager.setVisibleInGame_MilitaryAlliances(false);
            } else {
               CFG.menuManager.rebuildInGame_MilitaryAlliances();
            }
         }

         @Override
         public void buildElementHover() {
            if (CFG.isDesktop()) {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Shortcut") + ": "));
               nData.add(new MenuElement_Hover_v2_Element_Type_Text("F7", CFG.COLOR_TEXT_NUM_OF_PROVINCES));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               this.menuElementHover = new MenuElement_Hover_v2(nElements);
            } else {
               this.menuElementHover = null;
            }
         }
      });
      int var8;
      menuElements.add(new Text_Outliner(null, CFG.PADDING * 2, 2, var8 = tPosY + tElementH, tMenuWidth - 2, tElementH) {
         @Override
         public void actionElement(int iID) {
            if (CFG.menuManager.getVisibleInGame_Rank()) {
               CFG.menuManager.setVisibleInGame_Rank(false);
            } else {
               CFG.menuManager.rebuildInGame_Rank();
            }
         }

         @Override
         public void buildElementHover() {
            if (CFG.isDesktop()) {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Shortcut") + ": "));
               nData.add(new MenuElement_Hover_v2_Element_Type_Text("F9", CFG.COLOR_TEXT_NUM_OF_PROVINCES));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               this.menuElementHover = new MenuElement_Hover_v2(nElements);
            } else {
               this.menuElementHover = null;
            }
         }
      });
      menuElements.add(new Text_Outliner(null, CFG.PADDING * 2, 2, tPosY = var8 + tElementH, tMenuWidth - 2, tElementH) {
         @Override
         public void actionElement(int iID) {
            if (CFG.menuManager.getVisibleInGame_Wonders()) {
               CFG.menuManager.setVisibleInGame_Wonders(false);
            } else {
               CFG.menuManager.rebuildInGame_Wonders();
            }
         }
      });
      int var10;
      menuElements.add(new Text_Outliner(null, CFG.PADDING * 2, 2, var10 = tPosY + tElementH, tMenuWidth - 2, tElementH) {
         @Override
         public void actionElement(int iID) {
            if (CFG.menuManager.getVisibleInGame_WorldPopulation()) {
               CFG.menuManager.setVisibleInGame_WorldPopulation(false);
            } else {
               CFG.menuManager.rebuildInGame_WorldPopulation();
            }
         }
      });
      menuElements.add(new Text_Outliner(null, CFG.PADDING * 2, 2, tPosY = var10 + tElementH, tMenuWidth - 2, tElementH) {
         @Override
         public void actionElement(int iID) {
            if (CFG.menuManager.getVisibleInGame_HRE()) {
               CFG.menuManager.setVisibleInGame_HRE(false);
            } else {
               CFG.menuManager.rebuildInGame_HRE();
            }
         }
      });
      int var12;
      menuElements.add(new Text_Outliner(null, CFG.PADDING * 2, 2, var12 = tPosY + tElementH, tMenuWidth - 2, tElementH) {
         @Override
         public void actionElement(int iID) {
            if (CFG.menuManager.getVisibleInGame_ConquredProvinces()) {
               CFG.menuManager.setVisibleInGame_ConquredProvinces(false);
            } else {
               CFG.menuManager.rebuildInGame_ConqueredProvinces();
            }
         }
      });
      menuElements.add(new Text_Outliner(null, CFG.PADDING * 2, 2, tPosY = var12 + tElementH, tMenuWidth - 2, tElementH) {
         @Override
         public void actionElement(int iID) {
            if (CFG.menuManager.getVisibleInGame_BuildingsConstructed()) {
               CFG.menuManager.setVisibleInGame_BuildingsConstructed(false);
            } else {
               CFG.menuManager.rebuildInGame_BuildingsConstrcuted();
            }
         }
      });
      int var14;
      menuElements.add(new Text_Outliner(null, CFG.PADDING * 2, 2, var14 = tPosY + tElementH, tMenuWidth - 2, tElementH) {
         @Override
         public void actionElement(int iID) {
            if (CFG.menuManager.getVisibleInGame_Army()) {
               CFG.menuManager.setVisibleInGame_Army(false);
            } else {
               CFG.menuManager.rebuildInGame_Army();
            }
         }
      });
      menuElements.add(new Text_Outliner(null, CFG.PADDING * 2, 2, tPosY = var14 + tElementH, tMenuWidth - 2, tElementH) {
         @Override
         public void actionElement(int iID) {
            if (CFG.menuManager.getVisibleInGame_RecruitedArmy()) {
               CFG.menuManager.setVisibleInGame_RecruitedArmy(false);
            } else {
               CFG.menuManager.rebuildInGame_RecruitedArmy();
            }
         }
      });
      int var16;
      menuElements.add(new Text_Outliner(null, CFG.PADDING * 2, 2, var16 = tPosY + tElementH, tMenuWidth - 2, tElementH) {
         @Override
         public void actionElement(int iID) {
            if (CFG.menuManager.getVisibleInGame_CensusOfProvince()) {
               CFG.menuManager.setVisibleInGame_CensusOfProvince(false);
            } else if (CFG.game.getActiveProvinceID() >= 0) {
               CFG.menuManager.rebuildInGame_CensusOfProvince(CFG.game.getActiveProvinceID());
            }
         }
      });
      menuElements.add(new Text_Outliner(null, CFG.PADDING * 2, 2, tPosY = var16 + tElementH, tMenuWidth - 2, tElementH) {
         @Override
         public void actionElement(int iID) {
            if (CFG.menuManager.getVisibleInGame_History()) {
               CFG.menuManager.setVisibleInGame_History(false);
            } else {
               CFG.menuManager.rebuildInGame_History();
            }
         }
      });
      int var18;
      menuElements.add(new Text_Outliner(null, CFG.PADDING * 2, 2, var18 = tPosY + tElementH, tMenuWidth - 2, tElementH) {
         @Override
         public void actionElement(int iID) {
            CFG.game.resetChooseProvinceData_Immediately();
            CFG.game.resetRegroupArmyData();
            CFG.menuManager.setViewID(Menu.eTIMELINE);
         }
      });
      menuElements.add(new Text_Outliner(null, CFG.PADDING * 2, 2, tPosY = var18 + tElementH, tMenuWidth - 2, tElementH) {
         @Override
         public void actionElement(int iID) {
            if (Menu_InGame_FlagAction_Bot_Right_Right.iViewMode == 0 && CFG.menuManager.getVisible_Menu_InGame_Graph()) {
               CFG.menuManager.setVisible_Menu_InGame_Graph(false);
            } else {
               Menu_InGame_FlagAction_Bot_Right_Right.iViewMode = 0;
               CFG.menuManager.rebuildInGame_Graph();
            }
         }
      });
      int var20;
      menuElements.add(new Text_Outliner(null, CFG.PADDING * 2, 2, var20 = tPosY + tElementH, tMenuWidth - 2, tElementH) {
         @Override
         public void actionElement(int iID) {
            if (Menu_InGame_FlagAction_Bot_Right_Right.iViewMode == 1 && CFG.menuManager.getVisible_Menu_InGame_Graph()) {
               CFG.menuManager.setVisible_Menu_InGame_Graph(false);
            } else {
               Menu_InGame_FlagAction_Bot_Right_Right.iViewMode = 1;
               CFG.menuManager.rebuildInGame_Graph();
            }
         }
      });
      menuElements.add(new Text_Outliner(null, CFG.PADDING * 2, 2, tPosY = var20 + tElementH, tMenuWidth - 2, tElementH) {
         @Override
         public void actionElement(int iID) {
            if (Menu_InGame_FlagAction_Bot_Right_Right.iViewMode == 2 && CFG.menuManager.getVisible_Menu_InGame_Graph()) {
               CFG.menuManager.setVisible_Menu_InGame_Graph(false);
            } else {
               Menu_InGame_FlagAction_Bot_Right_Right.iViewMode = 2;
               CFG.menuManager.rebuildInGame_Graph();
            }
         }
      });
      int var22;
      menuElements.add(new Text_Outliner(null, CFG.PADDING * 2, 2, var22 = tPosY + tElementH, tMenuWidth - 2, tElementH) {
         @Override
         public void actionElement(int iID) {
            if (Menu_InGame_FlagAction_Bot_Right_Right.iViewMode == 3 && CFG.menuManager.getVisible_Menu_InGame_Graph()) {
               CFG.menuManager.setVisible_Menu_InGame_Graph(false);
            } else {
               Menu_InGame_FlagAction_Bot_Right_Right.iViewMode = 3;
               CFG.menuManager.rebuildInGame_Graph();
            }
         }
      });
      menuElements.add(new Text_Outliner(null, CFG.PADDING * 2, 2, tPosY = var22 + tElementH, tMenuWidth - 2, tElementH) {
         @Override
         public void buildElementHover() {
            if (CFG.isDesktop()) {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Shortcut") + ": "));
               nData.add(new MenuElement_Hover_v2_Element_Type_Text("F12", CFG.COLOR_TEXT_NUM_OF_PROVINCES));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               this.menuElementHover = new MenuElement_Hover_v2(nElements);
            } else {
               this.menuElementHover = null;
            }
         }

         @Override
         public void actionElement(int iID) {
            CFG.menuManager.setVisibleInGame_Playlist(!CFG.menuManager.getVisibleInGame_Playlist());
         }
      });
      tPosY += tElementH;
      this.initMenu(
         new SliderMenuTitle(null, CFG.BUTTON_HEIGHT / 2, false, false) {
            @Override
            public void draw(SpriteBatch oSB, int iTranslateX, int nPosX, int nPosY, int nWidth, boolean sliderMenuIsActive) {
               oSB.setColor(new Color(CFG.COLOR_GRADIENT_TITLE_BLUE.r, CFG.COLOR_GRADIENT_TITLE_BLUE.g, CFG.COLOR_GRADIENT_TITLE_BLUE.b, 1.0F));
               ImageManager.getImage(Images.slider_gradient)
                  .draw(
                     oSB,
                     nPosX + iTranslateX,
                     nPosY - this.getHeight() - ImageManager.getImage(Images.slider_gradient).getHeight(),
                     nWidth,
                     this.getHeight(),
                     true,
                     false
                  );
               oSB.setColor(new Color(CFG.COLOR_GRADIENT_TITLE_BLUE.r, CFG.COLOR_GRADIENT_TITLE_BLUE.g, CFG.COLOR_GRADIENT_TITLE_BLUE.b, 0.4F));
               ImageManager.getImage(Images.slider_gradient)
                  .draw(
                     oSB,
                     nPosX + iTranslateX,
                     nPosY - this.getHeight() - ImageManager.getImage(Images.slider_gradient).getHeight(),
                     nWidth,
                     this.getHeight(),
                     true,
                     false
                  );
               oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.35F));
               ImageManager.getImage(Images.slider_gradient)
                  .draw(
                     oSB,
                     nPosX + nWidth - nWidth / 2 + iTranslateX,
                     nPosY - this.getHeight() - ImageManager.getImage(Images.slider_gradient).getHeight(),
                     nWidth / 2,
                     this.getHeight(),
                     true,
                     false
                  );
               oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.65F));
               ImageManager.getImage(Images.slider_gradient)
                  .draw(
                     oSB, nPosX + iTranslateX, nPosY + 1 - this.getHeight() - ImageManager.getImage(Images.slider_gradient).getHeight(), nWidth, 1, true, false
                  );
               oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.7F));
               ImageManager.getImage(Images.slider_gradient)
                  .draw(
                     oSB, nPosX + iTranslateX, nPosY + 1 - this.getHeight() - ImageManager.getImage(Images.slider_gradient).getHeight(), nWidth, 1, true, false
                  );
               oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.65F));
               ImageManager.getImage(Images.slider_gradient)
                  .draw(
                     oSB, nPosX + iTranslateX, nPosY + 2 - this.getHeight() - ImageManager.getImage(Images.slider_gradient).getHeight(), nWidth, 1, true, false
                  );
               ImageManager.getImage(Images.slider_gradient)
                  .draw(oSB, nPosX + iTranslateX, nPosY - 1 - ImageManager.getImage(Images.slider_gradient).getHeight(), nWidth, 1, true, false);
               oSB.setColor(Color.WHITE);
               ImageManager.getImage(Images.stats)
                  .draw(
                     oSB,
                     nPosX + (int)(nWidth - (this.getTextWidth() * 0.7F + ImageManager.getImage(Images.stats).getWidth() + CFG.PADDING)) / 2 + iTranslateX,
                     2 + nPosY - this.getHeight() + (this.getHeight() - ImageManager.getImage(Images.stats).getHeight()) / 2
                  );
               CFG.fontMain.getData().setScale(0.7F);
               CFG.drawText(
                  oSB,
                  this.getText(),
                  nPosX
                     + (int)(nWidth - (this.getTextWidth() * 0.7F + ImageManager.getImage(Images.stats).getWidth() + CFG.PADDING)) / 2
                     + CFG.PADDING
                     + ImageManager.getImage(Images.stats).getWidth()
                     + iTranslateX,
                  2 + nPosY - this.getHeight() + (int)(this.getHeight() - this.getTextHeight() * 0.7F) / 2,
                  Color.WHITE
               );
               CFG.fontMain.getData().setScale(1.0F);
            }
         },
         CFG.GAME_WIDTH - tMenuWidth,
         ImageManager.getImage(Images.top_left2).getHeight() + CFG.PADDING * 2 + CFG.BUTTON_HEIGHT / 2,
         tMenuWidth,
         Math.min(
            tElementH * (CFG.isAndroid() ? 4 : 5), menuElements.get(menuElements.size() - 1).getPosY() + menuElements.get(menuElements.size() - 1).getHeight()
         ),
         menuElements,
         false,
         true
      );

      for (int i = 0; i < this.getMenuElementsSize(); i++) {
         this.getMenuElement(i).setCurrent(i % 2);
      }

      this.updateLanguage();
   }

   @Override
   public void updateLanguage() {
      this.getTitle().setText(CFG.langManager.get("Stats"));
      int tID = 0;
      this.getMenuElement(tID++).setText(CFG.langManager.get("VictoryConditions"));
      this.getMenuElement(tID++).setText(CFG.langManager.get("Wars"));
      this.getMenuElement(tID++).setText(CFG.langManager.get("Alliances"));
      this.getMenuElement(tID++).setText(CFG.langManager.get("Ranking"));
      this.getMenuElement(tID++).setText(CFG.langManager.get("Wonders"));
      this.getMenuElement(tID++).setText(CFG.langManager.get("Population"));
      this.getMenuElement(tID++).setText(CFG.langManager.get("HolyRomanEmpire"));
      this.getMenuElement(tID++).setText(CFG.langManager.get("ConqueredProvinces"));
      this.getMenuElement(tID++).setText(CFG.langManager.get("ConstructedBuildings"));
      this.getMenuElement(tID++).setText(CFG.langManager.get("Army"));
      this.getMenuElement(tID++).setText(CFG.langManager.get("RecruitedArmy"));
      this.getMenuElement(tID++).setText(CFG.langManager.get("Demography"));
      this.getMenuElement(tID++).setText(CFG.langManager.get("History"));
      this.getMenuElement(tID++).setText(CFG.langManager.get("Timeline"));
      this.getMenuElement(tID++).setText(CFG.langManager.get("Provinces"));
      this.getMenuElement(tID++).setText(CFG.langManager.get("Population"));
      this.getMenuElement(tID++).setText(CFG.langManager.get("TechnologyLevel"));
      this.getMenuElement(tID++).setText(CFG.langManager.get("RankScore"));
      this.getMenuElement(tID++).setText(CFG.langManager.get("Audio"));
   }

   @Override
   public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      if (lTime + 135L >= System.currentTimeMillis()) {
         int var5;
         int var6;
         iTranslateX = hideAnimation
            ? (var5 = iTranslateX + (int)(this.getWidth() * ((float)(System.currentTimeMillis() - lTime) / 135.0F)))
            : (var6 = iTranslateX + (this.getWidth() - (int)(this.getWidth() * ((float)(System.currentTimeMillis() - lTime) / 135.0F))));
         CFG.setRender_3(true);
      } else if (hideAnimation) {
         super.setVisible(false);
         CFG.menuManager.getMenu_InGame_CurrentWars().setPosY(ImageManager.getImage(Images.top_left2).getHeight() + CFG.PADDING * 2);
         CFG.menuManager
            .getMenu_InGame_CurrentWars_Info()
            .setPosY(CFG.menuManager.getMenu_InGame_CurrentWars().getPosY() - 1 + CFG.menuManager.getMenu_InGame_CurrentWars().getHeight());
         Menu_InGame_CurrentWars.lTime = System.currentTimeMillis();
         return;
      }

      super.draw(oSB, iTranslateX, 1 + iTranslateY, sliderMenuIsActive);
      oSB.setColor(Color.WHITE);
   }

   @Override
   public void drawScrollPos(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      if (sliderMenuIsActive) {
         super.drawScrollPos(oSB, iTranslateX + CFG.PADDING, iTranslateY, sliderMenuIsActive);
      }
   }

   @Override
   public void drawCloseButton(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      this.getCloseButtonImage(sliderMenuIsActive)
         .draw(
            oSB,
            this.getPosX() + this.getWidth() - ImageManager.getImage(Images.btn_close).getWidth() * 3 / 5 + iTranslateX,
            this.getPosY() - this.getTitle().getHeight() - ImageManager.getImage(Images.btn_close).getHeight() + iTranslateY,
            ImageManager.getImage(Images.btn_close).getWidth() * 3 / 5,
            ImageManager.getImage(Images.btn_close).getHeight() * 3 / 5
         );
   }

   @Override
   public void actionElement(int iID) {
      this.getMenuElement(iID).actionElement(iID);
   }

   @Override
   public void setVisible(boolean visible) {
      if (visible) {
         super.setVisible(visible);
         this.setHideAnimation(false);
         CFG.menuManager.getMenu_InGame_CurrentWars().setPosY(this.getPosY() + this.getHeight());
         CFG.menuManager
            .getMenu_InGame_CurrentWars_Info()
            .setPosY(CFG.menuManager.getMenu_InGame_CurrentWars().getPosY() - 1 + CFG.menuManager.getMenu_InGame_CurrentWars().getHeight());
      } else {
         this.setHideAnimation(true);
      }
   }

   public final void setHideAnimation(boolean hideAnimation) {
      if (hideAnimation != Menu_InGame_Outliner.hideAnimation) {
         lTime = lTime > System.currentTimeMillis() - 135L
            ? System.currentTimeMillis() - (135L - (System.currentTimeMillis() - lTime))
            : System.currentTimeMillis();
         CFG.setRender_3(true);
      }

      Menu_InGame_Outliner.hideAnimation = hideAnimation;
   }
}
