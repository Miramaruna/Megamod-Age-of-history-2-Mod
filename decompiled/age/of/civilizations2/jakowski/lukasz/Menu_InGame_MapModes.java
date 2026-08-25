package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.utils.ScissorStack;
import java.util.ArrayList;

public class Menu_InGame_MapModes extends SliderMenu {
   public static final int ANIMATION_TIME = 155;
   public long lTime = 0L;

   public Menu_InGame_MapModes() {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      int tempElemH = CFG.isAndroid()
         ? CFG.BUTTON_HEIGHT / 2 + CFG.PADDING
         : (int)Math.max(CFG.BUTTON_HEIGHT * 0.6F, (float)(CFG.BUTTON_HEIGHT / 2 + CFG.PADDING));

      for (int i = 0; i < 30; i++) {
         if (i == 28) {
            if (i % 2 == 0) {
               menuElements.add(
                  new Button_Options_NS_MapModes(-2, null, -1, CFG.PADDING * 2, tempElemH * i, CFG.BUTTON_WIDTH - CFG.PADDING * 4, tempElemH, true, true) {
                     @Override
                     public int getWidth() {
                        return Menu_InGame_MapModes.this.getW();
                     }

                     @Override
                     public boolean getClickable() {
                        return CFG.SPECTATOR_MODE;
                     }
                  }
               );
            } else {
               menuElements.add(
                  new Button_Options_NS_MapModes2(-2, null, -1, CFG.PADDING * 2, tempElemH * i, CFG.BUTTON_WIDTH - CFG.PADDING * 4, tempElemH, true, true) {
                     @Override
                     public int getWidth() {
                        return Menu_InGame_MapModes.this.getW();
                     }

                     @Override
                     public boolean getClickable() {
                        return CFG.SPECTATOR_MODE;
                     }
                  }
               );
            }
         } else if (i % 2 == 0) {
            menuElements.add(
               new Button_Options_NS_MapModes(-2, null, -1, CFG.PADDING * 2, tempElemH * i, CFG.BUTTON_WIDTH - CFG.PADDING * 4, tempElemH, true, true) {
                  @Override
                  public int getWidth() {
                     return Menu_InGame_MapModes.this.getW();
                  }
               }
            );
         } else {
            menuElements.add(
               new Button_Options_NS_MapModes2(-2, null, -1, CFG.PADDING * 2, tempElemH * i, CFG.BUTTON_WIDTH - CFG.PADDING * 4, tempElemH, true, true) {
                  @Override
                  public int getWidth() {
                     return Menu_InGame_MapModes.this.getW();
                  }
               }
            );
         }
      }

      menuElements.add(
         new Button_Options_NS_MapModes(-2, null, -1, CFG.PADDING * 2, tempElemH * 30, CFG.BUTTON_WIDTH - CFG.PADDING * 4, tempElemH, true, true) {
            @Override
            public int getWidth() {
               return Menu_InGame_MapModes.this.getW();
            }

            @Override
            public void actionElement(int iIDX) {
               AI_Assistant.PARTISAN_MAP_ON = !AI_Assistant.PARTISAN_MAP_ON;
               this.setText(CFG.langManager.get("Partisans_Mode") + ": " + CFG.langManager.get(AI_Assistant.PARTISAN_MAP_ON ? "AI_Set_On" : "AI_Set_Off"));
               CFG.toast.setInView(
                  CFG.langManager.get("Partisans_Mode") + ": " + CFG.langManager.get(AI_Assistant.PARTISAN_MAP_ON ? "AI_Set_On" : "AI_Set_Off"),
                  AI_Assistant.PARTISAN_MAP_ON ? CFG.COLOR_TEXT_MODIFIER_NEGATIVE2 : CFG.COLOR_TEXT_MODIFIER_NEUTRAL
               );
               CFG.toast.setTimeInView(2500);
               Game_Render_Province.updateDrawProvinces();
            }
         }
      );

      this.initMenu(
         new SliderMenuTitle(null, CFG.BUTTON_HEIGHT / 2, true, true) {
            @Override
            public void draw(SpriteBatch oSB, int iTranslateX, int nPosX, int nPosY, int nWidth, boolean sliderMenuIsActive) {
               ImageManager.getImage(Images.dialog_title)
                  .draw2(
                     oSB,
                     nPosX + iTranslateX,
                     nPosY - this.getHeight() - ImageManager.getImage(Images.dialog_title).getHeight(),
                     nWidth - ImageManager.getImage(Images.dialog_title).getWidth(),
                     this.getHeight()
                  );
               ImageManager.getImage(Images.dialog_title)
                  .draw2(
                     oSB,
                     nPosX + nWidth - ImageManager.getImage(Images.dialog_title).getWidth() + iTranslateX,
                     nPosY - this.getHeight() - ImageManager.getImage(Images.dialog_title).getHeight(),
                     ImageManager.getImage(Images.dialog_title).getWidth(),
                     this.getHeight(),
                     true,
                     false
                  );
               oSB.setColor(
                  new Color(
                     CFG.COLOR_GRADIENT_TITLE_BLUE_LIGHT_ALLIANCE.r,
                     CFG.COLOR_GRADIENT_TITLE_BLUE_LIGHT_ALLIANCE.g,
                     CFG.COLOR_GRADIENT_TITLE_BLUE_LIGHT_ALLIANCE.b,
                     0.075F
                  )
               );
               ImageManager.getImage(Images.line_32_off1)
                  .draw(
                     oSB,
                     nPosX + 2 + iTranslateX,
                     nPosY - this.getHeight() + 2 - ImageManager.getImage(Images.line_32_off1).getHeight(),
                     nWidth - 4,
                     this.getHeight() - 2,
                     false,
                     true
                  );
               oSB.setColor(
                  new Color(
                     CFG.COLOR_GRADIENT_TITLE_BLUE_LIGHT_ALLIANCE.r,
                     CFG.COLOR_GRADIENT_TITLE_BLUE_LIGHT_ALLIANCE.g,
                     CFG.COLOR_GRADIENT_TITLE_BLUE_LIGHT_ALLIANCE.b,
                     0.175F
                  )
               );
               ImageManager.getImage(Images.gradient)
                  .draw(
                     oSB,
                     nPosX + 2 + iTranslateX,
                     nPosY - this.getHeight() * 2 / 3 - ImageManager.getImage(Images.gradient).getHeight(),
                     nWidth - 4,
                     this.getHeight() * 2 / 3,
                     false,
                     true
                  );
               oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.5F));
               ImageManager.getImage(Images.gradient)
                  .draw(
                     oSB,
                     nPosX + 2 + iTranslateX,
                     nPosY - CFG.PADDING - ImageManager.getImage(Images.gradient).getHeight(),
                     nWidth - 4,
                     CFG.PADDING,
                     false,
                     true
                  );
               oSB.setColor(CFG.COLOR_NEW_GAME_EDGE_LINE);
               ImageManager.getImage(Images.pix255_255_255)
                  .draw(oSB, nPosX + 2 + iTranslateX, nPosY - 1 - ImageManager.getImage(Images.pix255_255_255).getHeight(), nWidth - 4, 1);
               oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.45F));
               ImageManager.getImage(Images.slider_gradient)
                  .draw(oSB, nPosX + 2 + iTranslateX, nPosY - 1 - ImageManager.getImage(Images.slider_gradient).getHeight(), (nWidth - 4) / 2, 1);
               ImageManager.getImage(Images.slider_gradient)
                  .draw(
                     oSB,
                     nPosX + 2 + (nWidth - 4) - (nWidth - 4) / 2 + iTranslateX,
                     nPosY - 1 - ImageManager.getImage(Images.slider_gradient).getHeight(),
                     (nWidth - 4) / 2,
                     1,
                     true,
                     false
                  );
               oSB.setColor(Color.WHITE);
               CFG.fontMain.getData().setScale(0.6F);
               CFG.drawText(
                  oSB,
                  this.getText(),
                  nPosX + nWidth / 2 - (int)(this.getTextWidth() * 0.6F / 2.0F) + iTranslateX,
                  nPosY - this.getHeight() + this.getHeight() / 2 - (int)(this.getTextHeight() * 0.6F / 2.0F),
                  Color.WHITE
               );
               CFG.fontMain.getData().setScale(1.0F);
            }
         },
         -1,
         -1,
         CFG.BUTTON_WIDTH + CFG.BUTTON_WIDTH / 2,
         Math.min(Math.min(tempElemH * 4 + tempElemH / 2, CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT), menuElements.size() * tempElemH),
         menuElements,
         false,
         true
      );
      this.updateLanguage();
   }

   @Override
   public void updateLanguage() {
      this.getTitle().setText(CFG.langManager.get("MapModes"));
      this.getMenuElement(0).setText(CFG.langManager.get("Political"));
      this.getMenuElement(0).setCurrent(-1);
      this.getMenuElement(1).setText(CFG.langManager.get("Army"));
      this.getMenuElement(1).setCurrent(ViewsManager.VIEW_ARMY_MODE);
      this.getMenuElement(2).setText(CFG.langManager.get("Income"));
      this.getMenuElement(2).setCurrent(ViewsManager.VIEW_INCOME_MODE);
      this.getMenuElement(3).setText(CFG.langManager.get("Technology"));
      this.getMenuElement(3).setCurrent(ViewsManager.VIEW_TECHNOLOGY_MODE);
      this.getMenuElement(4).setText(CFG.langManager.get("Population"));
      this.getMenuElement(4).setCurrent(ViewsManager.VIEW_POPULATION_MODE);
      this.getMenuElement(5).setText(CFG.langManager.get("Economy"));
      this.getMenuElement(5).setCurrent(ViewsManager.VIEW_ECONOMY_MODE);
      this.getMenuElement(6).setText(CFG.langManager.get("Radiation"));
      this.getMenuElement(6).setCurrent(ViewsManager.VIEW_RADIATION_MODE);
      this.getMenuElement(7).setText(CFG.langManager.get("Development"));
      this.getMenuElement(7).setCurrent(ViewsManager.VIEW_DEVELOPMENT_MODE);
      this.getMenuElement(8).setText(CFG.langManager.get("ProvinceStability"));
      this.getMenuElement(8).setCurrent(ViewsManager.VIEW_PROVINCE_STABILITY_MODE);
      this.getMenuElement(9).setText(CFG.langManager.get("Diseases"));
      this.getMenuElement(9).setCurrent(ViewsManager.VIEW_DISEASES_MODE);
      this.getMenuElement(10).setText(CFG.langManager.get("Buildings"));
      this.getMenuElement(10).setCurrent(ViewsManager.VIEW_BUILDINGS_MODE);
      this.getMenuElement(11).setText(CFG.langManager.get("DistanceFromCapital"));
      this.getMenuElement(11).setCurrent(ViewsManager.VIEW_DISTANCE_MODE);
      this.getMenuElement(12).setText(CFG.langManager.get("RecruitablePopulation"));
      this.getMenuElement(12).setCurrent(ViewsManager.VIEW_RECRUITABLE_ARMY_MODE);
      this.getMenuElement(13).setText(CFG.langManager.get("TerrainType"));
      this.getMenuElement(13).setCurrent(ViewsManager.VIEW_TERRAIN_TYPE_MODE);
      this.getMenuElement(14).setText(CFG.langManager.get("GrowthRate"));
      this.getMenuElement(14).setCurrent(ViewsManager.VIEW_GROWTH_RATE_MODE);
      this.getMenuElement(15).setText(CFG.langManager.get("Supplies"));
      this.getMenuElement(15).setCurrent(ViewsManager.VIEW_SUPPLIES_MODE);
      this.getMenuElement(16).setText(CFG.langManager.get("Happiness"));
      this.getMenuElement(16).setCurrent(ViewsManager.VIEW_HAPPINESS_MODE);
      this.getMenuElement(17).setText(CFG.langManager.get("Unrest"));
      this.getMenuElement(17).setCurrent(ViewsManager.VIEW_REVOLUTION_MODE);
      this.getMenuElement(18).setText(CFG.langManager.get("Governments"));
      this.getMenuElement(18).setCurrent(ViewsManager.VIEW_IDEOLOGIES_MODE);
      this.getMenuElement(19).setText(CFG.langManager.get("ImperialView"));
      this.getMenuElement(19).setCurrent(ViewsManager.VIEW_IMPERIAL_MODE);
      this.getMenuElement(20).setText(CFG.langManager.get("Cores"));
      this.getMenuElement(20).setCurrent(ViewsManager.VIEW_CORES_MODE);
      this.getMenuElement(21).setText(CFG.langManager.get("ProvinceValue"));
      this.getMenuElement(21).setCurrent(ViewsManager.VIEW_PROVINCE_VALUE_MODE);
      this.getMenuElement(22).setText(CFG.langManager.get("Diplomacy"));
      this.getMenuElement(22).setCurrent(ViewsManager.VIEW_DIPLOMACY_MODE);
      this.getMenuElement(23).setText(CFG.langManager.get("Alliances"));
      this.getMenuElement(23).setCurrent(ViewsManager.VIEW_ALLIANCES_MODE);
      this.getMenuElement(24).setText(CFG.langManager.get("Fortifications"));
      this.getMenuElement(24).setCurrent(ViewsManager.VIEW_LEVEL_OF_FORTIFICATIONS_MODE);
      this.getMenuElement(25).setText(CFG.langManager.get("WatchTowers"));
      this.getMenuElement(25).setCurrent(ViewsManager.VIEW_LEVEL_OF_WATCH_TOWER_MODE);
      this.getMenuElement(26).setText(CFG.langManager.get("Ports"));
      this.getMenuElement(26).setCurrent(ViewsManager.VIEW_LEVEL_OF_PORT_MODE);
      this.getMenuElement(27).setText(CFG.langManager.get("Continents"));
      this.getMenuElement(27).setCurrent(ViewsManager.VIEW_CONTINENT_MODE);
      this.getMenuElement(28).setText(CFG.langManager.get("Regions"));
      this.getMenuElement(28).setCurrent(ViewsManager.VIEW_REGIONS_MODE);
      this.getMenuElement(29).setText(CFG.langManager.get("Balance"));
      this.getMenuElement(29).setCurrent(ViewsManager.VIEW_BALANCE_MODE);
      this.getMenuElement(30).setText(
         CFG.langManager.get("Partisans_Mode") + ": " + CFG.langManager.get(AI_Assistant.PARTISAN_MAP_ON ? "AI_Set_On" : "AI_Set_Off")
      );
   }

   @Override
   public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      if (this.lTime + 155L >= System.currentTimeMillis()) {
         Rectangle clipBounds = new Rectangle(
            this.getPosX(),
            CFG.GAME_HEIGHT - this.getPosY(),
            this.getWidth(),
            -((int)(this.getHeight() * ((float)(System.currentTimeMillis() - this.lTime) / 155.0F)))
         );
         oSB.flush();
         ScissorStack.pushScissors(clipBounds);
         ImageManager.getImage(Images.new_game_top_edge)
            .draw2(
               oSB,
               this.getPosX() + iTranslateX,
               this.getPosY() - ImageManager.getImage(Images.new_game_top_edge).getHeight() + iTranslateY,
               this.getWidth() - ImageManager.getImage(Images.new_game_top_edge).getWidth(),
               this.getHeight() + 2,
               false,
               true
            );
         ImageManager.getImage(Images.new_game_top_edge)
            .draw2(
               oSB,
               this.getPosX() + this.getWidth() - ImageManager.getImage(Images.new_game_top_edge).getWidth() + iTranslateX,
               this.getPosY() - ImageManager.getImage(Images.new_game_top_edge).getHeight() + iTranslateY,
               ImageManager.getImage(Images.new_game_top_edge).getWidth(),
               this.getHeight() + 2,
               true,
               true
            );
         super.drawMenu(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
         CFG.setRender_3(true);

         try {
            oSB.flush();
            ScissorStack.popScissors();
         } catch (IllegalStateException var7) {
         }

         super.endClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
      } else {
         ImageManager.getImage(Images.new_game_top_edge)
            .draw2(
               oSB,
               this.getPosX() + iTranslateX,
               this.getPosY() - ImageManager.getImage(Images.new_game_top_edge).getHeight() + iTranslateY,
               this.getWidth() - ImageManager.getImage(Images.new_game_top_edge).getWidth(),
               this.getHeight() + 2,
               false,
               true
            );
         ImageManager.getImage(Images.new_game_top_edge)
            .draw2(
               oSB,
               this.getPosX() + this.getWidth() - ImageManager.getImage(Images.new_game_top_edge).getWidth() + iTranslateX,
               this.getPosY() - ImageManager.getImage(Images.new_game_top_edge).getHeight() + iTranslateY,
               ImageManager.getImage(Images.new_game_top_edge).getWidth(),
               this.getHeight() + 2,
               true,
               true
            );
         super.draw(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
      }
   }

   @Override
   public void drawScrollPos(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      if (sliderMenuIsActive) {
         super.drawScrollPos(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
      }
   }

   @Override
   public final void actionElement(int iID) {
      if (iID == 30) {
         this.getMenuElement(iID).actionElement(iID);
         return;
      }

      CFG.viewsManager.setActiveViewID(this.getMenuElement(iID).getCurrent(), false);
   }

   public final int getW() {
      return this.getWidth() - CFG.PADDING * 4;
   }

   @Override
   public void setVisible(boolean visible) {
      super.setVisible(visible);
      this.lTime = System.currentTimeMillis();
   }
}
