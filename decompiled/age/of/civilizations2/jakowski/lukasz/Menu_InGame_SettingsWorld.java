package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.utils.ScissorStack;
import java.util.ArrayList;

public class Menu_InGame_SettingsWorld extends SliderMenu {
   public int iOnCivID = -1;

   public Menu_InGame_SettingsWorld() {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      int tempWidth = CFG.CIV_INFO_MENU_WIDTH * 2;
      int tY = CFG.PADDING;
      menuElements.add(new Button_Flag_JustFrame(CFG.PADDING, tY, true));
      tY += menuElements.get(menuElements.size() - 1).getHeight();
      int tempMenuPosY = ImageManager.getImage(Images.top_left2).getHeight() + CFG.PADDING * 2 + CFG.BUTTON_HEIGHT * 3 / 5;
      this.initMenu(
         new SliderMenuTitle(CFG.langManager.get("WorldSettings"), CFG.BUTTON_HEIGHT * 3 / 5, true, true),
         CFG.GAME_WIDTH / 2 - tempWidth / 2,
         tempMenuPosY,
         tempWidth,
         menuElements.get(menuElements.size() - 1).getPosY() + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING + tempMenuPosY
               > CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING * 2
            ? Math.max(CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING * 2 - tempMenuPosY, (CFG.TEXT_HEIGHT + CFG.PADDING * 2) * 6)
            : menuElements.get(menuElements.size() - 1).getPosY() + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING,
         menuElements,
         false,
         true
      );
      this.updateLanguage();
   }

   public Menu_InGame_SettingsWorld(int onCivID) {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      this.iOnCivID = onCivID;
      int tempWidth = CFG.CIV_INFO_MENU_WIDTH * 2;
      int tY = 0;
      menuElements.add(
         new Button_NS_Opinion(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), CFG.getActiveCivInfo(), Images.b_armoury, 0, 0, 2, tY, CFG.BUTTON_WIDTH * 2) {
            @Override
            public int getWidth() {
               return Menu_InGame_SettingsWorld.this.getElementW() * 2;
            }
         }
      );
      tY += menuElements.get(menuElements.size() - 1).getHeight();
      menuElements.add(
         new Slider_FlagAction_Gold(
            CFG.langManager.get("AIAggressiveness"),
            CFG.PADDING * 2,
            menuElements.get(menuElements.size() - 1).getPosY() + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING,
            tempWidth - CFG.PADDING * 3 - CFG.BUTTON_WIDTH,
            CFG.TEXT_HEIGHT + CFG.PADDING * 2 + CFG.PADDING * 4,
            0,
            1000,
            0,
            0.65F,
            Images.diplo_war
         ) {
            @Override
            public int getWidth() {
               return Menu_InGame_SettingsWorld.this.getElementW() * 2 - CFG.PADDING * 4;
            }

            @Override
            public int getSliderHeight() {
               return CFG.PADDING * 2;
            }

            @Override
            public Color getColorLEFT() {
               return new Color(CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.r, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.g, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.b, 0.65F);
            }
         }
      );
      tY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
      menuElements.add(
         new Slider_FlagAction_Gold(
            CFG.langManager.get("GameSpeed"),
            CFG.PADDING * 2,
            menuElements.get(menuElements.size() - 1).getPosY() + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING,
            tempWidth - CFG.PADDING * 3 - CFG.BUTTON_WIDTH,
            CFG.TEXT_HEIGHT + CFG.PADDING * 2 + CFG.PADDING * 4,
            -200,
            800,
            0,
            0.65F,
            Images.dice
         ) {
            @Override
            public int getWidth() {
               return Menu_InGame_SettingsWorld.this.getElementW() * 2 - CFG.PADDING * 4;
            }

            @Override
            public int getSliderHeight() {
               return CFG.PADDING * 2;
            }

            @Override
            public Color getColorLEFT() {
               return new Color(CFG.COLOR_INGAME_GOLD.r, CFG.COLOR_INGAME_GOLD.g, CFG.COLOR_INGAME_GOLD.b, 0.65F);
            }
         }
      );
      tY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
      menuElements.add(
         new Slider_FlagAction_Gold(
            CFG.langManager.get("LossesInWars"),
            CFG.PADDING * 2,
            menuElements.get(menuElements.size() - 1).getPosY() + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING,
            tempWidth - CFG.PADDING * 3 - CFG.BUTTON_WIDTH,
            CFG.TEXT_HEIGHT + CFG.PADDING * 2 + CFG.PADDING * 4,
            0,
            1000,
            0,
            0.65F,
            Images.population
         ) {
            @Override
            public int getWidth() {
               return Menu_InGame_SettingsWorld.this.getElementW() * 2 - CFG.PADDING * 4;
            }

            @Override
            public int getSliderHeight() {
               return CFG.PADDING * 2;
            }

            @Override
            public Color getColorLEFT() {
               return new Color(0.20392157F, 0.383F, 0.26666668F, 1.0F);
            }
         }
      );
      tY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
      menuElements.add(
         new Slider_FlagAction_Gold(
            CFG.langManager.get("PowerRebels"),
            CFG.PADDING * 2,
            menuElements.get(menuElements.size() - 1).getPosY() + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING,
            tempWidth - CFG.PADDING * 3 - CFG.BUTTON_WIDTH,
            CFG.TEXT_HEIGHT + CFG.PADDING * 2 + CFG.PADDING * 4,
            0,
            500,
            0,
            0.65F,
            Images.diplo_revolution
         ) {
            @Override
            public int getWidth() {
               return Menu_InGame_SettingsWorld.this.getElementW() * 2 - CFG.PADDING * 4;
            }

            @Override
            public int getSliderHeight() {
               return CFG.PADDING * 2;
            }

            @Override
            public Color getColorLEFT() {
               return new Color(0.5905882F, 0.58882356F, 0.12941177F, 1.0F);
            }
         }
      );
      tY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
      menuElements.add(
         new Slider_FlagAction_Gold(
            CFG.langManager.get("SurrenderLimit"),
            CFG.PADDING * 2,
            menuElements.get(menuElements.size() - 1).getPosY() + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING,
            tempWidth - CFG.PADDING * 3 - CFG.BUTTON_WIDTH,
            CFG.TEXT_HEIGHT + CFG.PADDING * 2 + CFG.PADDING * 4,
            0,
            200,
            0,
            0.65F,
            Images.diplo_union
         ) {
            @Override
            public int getWidth() {
               return Menu_InGame_SettingsWorld.this.getElementW() * 2 - CFG.PADDING * 4;
            }

            @Override
            public int getSliderHeight() {
               return CFG.PADDING * 2;
            }

            @Override
            public Color getColorLEFT() {
               return new Color(CFG.COLOR_TEXT_RANK.r, CFG.COLOR_TEXT_RANK.g, CFG.COLOR_TEXT_RANK.b, 0.55F);
            }
         }
      );
      tY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
      menuElements.add(
         new Slider_FlagAction_Gold(
            CFG.langManager.get("TroopBreakthroughChance"),
            CFG.PADDING * 2,
            menuElements.get(menuElements.size() - 1).getPosY() + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING,
            tempWidth - CFG.PADDING * 3 - CFG.BUTTON_WIDTH,
            CFG.TEXT_HEIGHT + CFG.PADDING * 2 + CFG.PADDING * 4,
            0,
            200,
            0,
            0.65F,
            Images.diplo_army
         ) {
            @Override
            public int getWidth() {
               return Menu_InGame_SettingsWorld.this.getElementW() * 2 - CFG.PADDING * 4;
            }

            @Override
            public int getSliderHeight() {
               return CFG.PADDING * 2;
            }

            @Override
            public Color getColorLEFT() {
               return new Color(0.44F, 0.44F, 0.44F, 0.35F);
            }
         }
      );
      tY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
      menuElements.add(
         new Slider_FlagAction_Gold(
            CFG.langManager.get("AICreatingVassals"),
            CFG.PADDING * 2,
            menuElements.get(menuElements.size() - 1).getPosY() + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING,
            tempWidth - CFG.PADDING * 3 - CFG.BUTTON_WIDTH,
            CFG.TEXT_HEIGHT + CFG.PADDING * 2 + CFG.PADDING * 4,
            0,
            100,
            0,
            0.65F,
            Images.diplo_vassal
         ) {
            @Override
            public int getWidth() {
               return Menu_InGame_SettingsWorld.this.getElementW() * 2 - CFG.PADDING * 4;
            }

            @Override
            public int getSliderHeight() {
               return CFG.PADDING * 2;
            }

            @Override
            public Color getColorLEFT() {
               return new Color(CFG.COLOR_PORT_1.r, CFG.COLOR_PORT_1.g, CFG.COLOR_PORT_1.b, 0.35F);
            }
         }
      );
      tY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
      menuElements.add(
         new Slider_FlagAction_Gold(
            CFG.langManager.get("AICreatingAlliance"),
            CFG.PADDING * 2,
            menuElements.get(menuElements.size() - 1).getPosY() + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING,
            tempWidth - CFG.PADDING * 3 - CFG.BUTTON_WIDTH,
            CFG.TEXT_HEIGHT + CFG.PADDING * 2 + CFG.PADDING * 4,
            0,
            100,
            0,
            0.65F,
            Images.diplo_alliance
         ) {
            @Override
            public int getWidth() {
               return Menu_InGame_SettingsWorld.this.getElementW() * 2 - CFG.PADDING * 4;
            }

            @Override
            public int getSliderHeight() {
               return CFG.PADDING * 2;
            }

            @Override
            public Color getColorLEFT() {
               return new Color(new Color(CFG.COLOR_TEXT_POPULATION.r, CFG.COLOR_TEXT_POPULATION.g, CFG.COLOR_TEXT_POPULATION.b, 0.35F));
            }
         }
      );
      tY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
      int var15;
      menuElements.add(new Button_FlagActionSliderStyle(CFG.langManager.get("Cancel"), -1, 2 + CFG.PADDING, var15 = tY + CFG.PADDING, CFG.BUTTON_WIDTH, true) {
         @Override
         public int getWidth() {
            return Menu_InGame_SettingsWorld.this.getElementW() - CFG.PADDING - CFG.PADDING / 2;
         }
      });
      menuElements.add(
         new Button_FlagActionSliderStyle(CFG.langManager.get("Confirm"), -1, 2, var15, CFG.BUTTON_WIDTH, true) {
            @Override
            public int getPosX() {
               return Menu_InGame_SettingsWorld.this.getElementW() + CFG.PADDING / 2;
            }

            @Override
            public int getWidth() {
               return Menu_InGame_SettingsWorld.this.getElementW() - CFG.PADDING - CFG.PADDING / 2;
            }

            @Override
            public void buildElementHover() {
            }

            @Override
            public void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
               CFG.drawText(
                  oSB,
                  this.getText(),
                  this.getPosX()
                     + (
                        this.getTextPos() < 0
                           ? this.getWidth() / 2
                              - (int)((this.getTextWidth() * 0.8F + ImageManager.getImage(Images.diplo_gift).getWidth() + CFG.PADDING) / 2.0F)
                              + ImageManager.getImage(Images.diplo_gift).getWidth()
                              + CFG.PADDING
                           : this.getTextPos()
                     )
                     + iTranslateX,
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
               return SoundsManager.getSend();
            }
         }
      );
      int tempMenuPosY = ImageManager.getImage(Images.top_left2).getHeight() + CFG.PADDING * 2 + CFG.BUTTON_HEIGHT * 3 / 5;
      this.initMenu(
         new SliderMenuTitle(CFG.langManager.get("WorldSettings"), CFG.BUTTON_HEIGHT * 3 / 5, true, true) {
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
               oSB.setColor(new Color(0.101960786F, 0.5882353F, 0.33333334F, 0.165F));
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
               oSB.setColor(new Color(0.101960786F, 0.5882353F, 0.33333334F, 0.375F));
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
               ImageManager.getImage(Images.editor_game)
                  .draw(
                     oSB,
                     nPosX + (int)(nWidth - (this.getTextWidth() * 0.8F + ImageManager.getImage(Images.b_armoury).getWidth() + CFG.PADDING)) / 2 + iTranslateX,
                     Menu_InGame_SettingsWorld.this.getPosY() - this.getHeight() / 2 - ImageManager.getImage(Images.top_gold).getHeight() / 2
                  );
               CFG.fontMain.getData().setScale(0.8F);
               CFG.drawText(
                  oSB,
                  this.getText(),
                  nPosX
                     + (int)(nWidth - (this.getTextWidth() * 0.8F + ImageManager.getImage(Images.b_armoury).getWidth() + CFG.PADDING)) / 2
                     + ImageManager.getImage(Images.b_armoury).getWidth()
                     + CFG.PADDING
                     + iTranslateX,
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
      this.getMenuElement(1).setCurrent((int)Game_Calendar.AI_AGGRESSIVNESS * 100);
      this.getMenuElement(2).setCurrent((int)Game_Calendar.GAME_SPEED * 10);
      this.getMenuElement(3).setCurrent((int)Game_Calendar.LOSSESINWAR * 100);
      this.getMenuElement(4).setCurrent((int)Game_Calendar.POWERREBELS * 100);
      this.getMenuElement(5).setCurrent(Game_Calendar.SURRENDERLIMIT);
      this.getMenuElement(6).setCurrent((int)(100.0F * Game_Action.fTroopBreakthroughChance));
      this.getMenuElement(7).setCurrent(100 - CFG.AI_CREATING_VASSALS);
      this.getMenuElement(8).setCurrent(CFG.AI_CREATING_ALLIANCE);
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
   public final void actionElement(int iID) {
      if (iID == this.getMenuElementsSize() - 1) {
         Game_Calendar.AI_AGGRESSIVNESS = this.getMenuElement(this.getMenuElementsSize() - 10).getCurrent() / 100.0F;
         Game_Calendar.GAME_SPEED = this.getMenuElement(this.getMenuElementsSize() - 9).getCurrent() / 10.0F;
         Game_Calendar.LOSSESINWAR = this.getMenuElement(this.getMenuElementsSize() - 8).getCurrent() / 100.0F;
         Game_Calendar.POWERREBELS = this.getMenuElement(this.getMenuElementsSize() - 7).getCurrent() / 100.0F;
         Game_Calendar.SURRENDERLIMIT = this.getMenuElement(this.getMenuElementsSize() - 6).getCurrent();
         Game_Action.fTroopBreakthroughChance = this.getMenuElement(this.getMenuElementsSize() - 5).getCurrent() / 100.0F;
         CFG.AI_CREATING_VASSALS = 100 - this.getMenuElement(this.getMenuElementsSize() - 4).getCurrent();
         CFG.AI_CREATING_ALLIANCE = this.getMenuElement(this.getMenuElementsSize() - 3).getCurrent();
         CFG.toast.setInView(CFG.langManager.get("Бедни мир") + "..", CFG.COLOR_TEXT_MODIFIER_POSITIVE);
         CFG.toast.setTimeInView(4500);
         this.setVisible(false);
      } else if (iID == this.getMenuElementsSize() - 2) {
         this.setVisible(false);
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
