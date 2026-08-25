package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.utils.ScissorStack;
import com.badlogic.gdx.utils.GdxRuntimeException;
import java.io.IOException;
import java.util.ArrayList;

public class Menu_Main extends SliderMenu {
   public int iTitleOffset = 0;
   public long lTime = 0L;
   public int ANIMATION_TIME = 850;
   public static float ICONS_ALPHA_PC = 0.75F;
   public static float ICONS_ALPHA = 0.625F;
   public static final float LOGO_APLHA_DEFAULT = 0.95F;
   public static boolean RATE_THE_GAME = false;

   public Menu_Main() {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      RATE_THE_GAME = false;
      int numOfProvinces = 0;
      if (!CFG.settingsManager.gameRated && CFG.isAndroid()) {
         try {
            FileHandle file = Gdx.files.local("saves/stats/civ/Age_of_Civilizations");
            String tempTags = file.readString();
            String[] tData = tempTags.split(";");

            for (int i2 = 0; i2 < tData.length; i2++) {
               try {
                  Statistics_Civ_GameData tempData = (Statistics_Civ_GameData)CFG.deserialize(Gdx.files.local("saves/stats/civ/" + tData[i2]).readBytes());
                  if ((numOfProvinces += tempData.getConqueredProvinces()) >= 50) {
                     break;
                  }
               } catch (GdxRuntimeException var9) {
               } catch (ClassNotFoundException var10) {
               } catch (IOException var11) {
               }
            }
         } catch (GdxRuntimeException var12) {
            RATE_THE_GAME = false;
         }

         RATE_THE_GAME = numOfProvinces >= 50;
      } else {
         RATE_THE_GAME = false;
      }

      int tempH = CFG.GAME_HEIGHT / 2 - (CFG.BUTTON_HEIGHT * 8 + CFG.PADDING * 7) / 2 + (CFG.BUTTON_HEIGHT + CFG.PADDING * 2) / 2;
      menuElements.add(new Button_Menu_LR_MainMenu_Games(null, -1, CFG.PADDING * 8, tempH, CFG.GAME_WIDTH / 4, CFG.BUTTON_HEIGHT, true));
      menuElements.add(
         new Button_Menu_LR_MainMenu(null, -1, CFG.PADDING * 8, tempH + CFG.BUTTON_HEIGHT + CFG.PADDING, CFG.GAME_WIDTH / 4, CFG.BUTTON_HEIGHT, true)
      );
      menuElements.add(
         new Button_Menu_LR_MainMenu(null, -1, CFG.PADDING * 8, tempH + CFG.BUTTON_HEIGHT * 2 + CFG.PADDING * 2, CFG.GAME_WIDTH / 4, CFG.BUTTON_HEIGHT, true)
      );
      if (RATE_THE_GAME) {
         menuElements.add(
            new Button_Menu_LR_MainMenu_Rate(
               null, -1, CFG.PADDING * 8, tempH + CFG.BUTTON_HEIGHT * 3 + CFG.PADDING * 3, CFG.GAME_WIDTH / 4, CFG.BUTTON_HEIGHT, true
            ) {
               @Override
               public void actionElement(int iID) {
                  try {
                     CFG.settingsManager.gameRated = true;
                     CFG.saveSettings();
                     Gdx.net.openURI("https://play.google.com/store/apps/details?id=age.of.civilizations2.jakowski.lukasz");
                  } catch (GdxRuntimeException var3) {
                     CFG.toast.setInView(CFG.langManager.get("NoData"));
                  }
               }
            }
         );
      } else {
         menuElements.add(
            new Button_Menu_LR_MainMenu(null, -1, CFG.PADDING * 8, tempH + CFG.BUTTON_HEIGHT * 3 + CFG.PADDING * 3, CFG.GAME_WIDTH / 4, CFG.BUTTON_HEIGHT, true) {
               @Override
               public void actionElement(int iID) {
                  CFG.setDialogType(Dialog.START_TUTORIAL);
               }
            }
         );
      }

      menuElements.add(
         new Button_Menu_LR_MainMenu(null, -1, CFG.PADDING * 8, tempH + CFG.BUTTON_HEIGHT * 4 + CFG.PADDING * 4, CFG.GAME_WIDTH / 4, CFG.BUTTON_HEIGHT, true)
      );
      menuElements.add(
         new Button_Menu_LR_MainMenu(
            null, -1, CFG.PADDING * 8, tempH + CFG.BUTTON_HEIGHT * 5 + CFG.PADDING * 5, CFG.GAME_WIDTH / 4, CFG.isAndroid() ? 0 : CFG.BUTTON_HEIGHT, true
         ) {
            @Override
            public void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
               if (!CFG.isAndroid()) {
                  oSB.setColor(1.0F, 1.0F, 1.0F, 0.55F);
                  if (!this.getClickable()) {
                     oSB.setColor(new Color(1.0F, 1.0F, 1.0F, 0.4F));
                     ImageManager.getImage(Images.btn_menu_h)
                        .draw(
                           oSB,
                           this.getPosX() + iTranslateX,
                           this.getPosY() + iTranslateY,
                           this.getWidth() - ImageManager.getImage(Images.btn_menu_h).getWidth()
                        );
                     ImageManager.getImage(Images.btn_menu_h)
                        .draw(
                           oSB,
                           this.getPosX() + this.getWidth() - ImageManager.getImage(Images.btn_menu_h).getWidth() + iTranslateX,
                           this.getPosY() + iTranslateY,
                           true
                        );
                  } else if (isActive) {
                     ImageManager.getImage(Images.btnh_menu_h)
                        .draw(
                           oSB,
                           this.getPosX() + iTranslateX,
                           this.getPosY() + iTranslateY,
                           this.getWidth() - ImageManager.getImage(Images.btnh_menu_h).getWidth()
                        );
                     ImageManager.getImage(Images.btnh_menu_h)
                        .draw(
                           oSB,
                           this.getPosX() + this.getWidth() - ImageManager.getImage(Images.btnh_menu_h).getWidth() + iTranslateX,
                           this.getPosY() + iTranslateY,
                           true
                        );
                  } else if (this.getIsHovered() && this.getClickable()) {
                     oSB.setColor(new Color(1.0F, 1.0F, 1.0F, 0.485F));
                     ImageManager.getImage(Images.btn_menu_h)
                        .draw(
                           oSB,
                           this.getPosX() + iTranslateX,
                           this.getPosY() + iTranslateY,
                           this.getWidth() - ImageManager.getImage(Images.btn_menu_h).getWidth()
                        );
                     ImageManager.getImage(Images.btn_menu_h)
                        .draw(
                           oSB,
                           this.getPosX() + this.getWidth() - ImageManager.getImage(Images.btn_menu_h).getWidth() + iTranslateX,
                           this.getPosY() + iTranslateY,
                           true
                        );
                  } else {
                     ImageManager.getImage(Images.btn_menu_h)
                        .draw(
                           oSB,
                           this.getPosX() + iTranslateX,
                           this.getPosY() + iTranslateY,
                           this.getWidth() - ImageManager.getImage(Images.btn_menu_h).getWidth()
                        );
                     ImageManager.getImage(Images.btn_menu_h)
                        .draw(
                           oSB,
                           this.getPosX() + this.getWidth() - ImageManager.getImage(Images.btn_menu_h).getWidth() + iTranslateX,
                           this.getPosY() + iTranslateY,
                           true
                        );
                  }

                  if (this.getClickable() && this.getIsHovered() && !isActive && animationState >= 0 || this.freePlayButton) {
                     if (animationState == 0) {
                        float drawPerc = Math.min(1.0F * (float)(System.currentTimeMillis() - lTimeAnimation) / 750.0F, 1.0F);
                        oSB.setColor(Button_Menu_LR_MainMenu.getColorLine());
                        ImageManager.getImage(Images.line_32_off1)
                           .draw(
                              oSB,
                              this.getPosX() + CFG.PADDING + iTranslateX,
                              this.getPosY() + 1 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
                              (int)((this.getWidth() - CFG.PADDING * 2) * drawPerc),
                              1
                           );
                        ImageManager.getImage(Images.line_32_off1)
                           .draw(
                              oSB,
                              this.getPosX() + CFG.PADDING + iTranslateX,
                              this.getPosY() + this.getHeight() - 2 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
                              (int)((this.getWidth() - CFG.PADDING * 2) * drawPerc),
                              1
                           );
                        if (lTimeAnimation < System.currentTimeMillis() - 750L) {
                           animationState++;
                           lTimeAnimation = System.currentTimeMillis();
                        }
                     } else {
                        float drawPerc = Math.min(1.0F * (float)(System.currentTimeMillis() - lTimeAnimation) / 750.0F, 1.0F);
                        oSB.setColor(Button_Menu_LR_MainMenu.getColorLine());
                        ImageManager.getImage(Images.line_32_off1)
                           .draw(
                              oSB,
                              this.getPosX() + CFG.PADDING + (int)((this.getWidth() - CFG.PADDING * 2) * drawPerc) + iTranslateX,
                              this.getPosY() + 1 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
                              this.getWidth() - CFG.PADDING * 2 - (int)((this.getWidth() - CFG.PADDING * 2) * drawPerc),
                              1
                           );
                        ImageManager.getImage(Images.line_32_off1)
                           .draw(
                              oSB,
                              this.getPosX() + CFG.PADDING + (int)((this.getWidth() - CFG.PADDING * 2) * drawPerc) + iTranslateX,
                              this.getPosY() + this.getHeight() - 2 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
                              this.getWidth() - CFG.PADDING * 2 - (int)((this.getWidth() - CFG.PADDING * 2) * drawPerc),
                              1
                           );
                        if (lTimeAnimation < System.currentTimeMillis() - 750L) {
                           animationState = 0;
                           lTimeAnimation = System.currentTimeMillis();
                        }
                     }

                     CFG.setRender_3(true);
                     oSB.setColor(Color.WHITE);
                  }
               }
            }
         }
      );
      menuElements.add(
         new Button_Menu_LR_MainMenu(
            null,
            -1,
            CFG.PADDING * 8,
            tempH + CFG.BUTTON_HEIGHT * (CFG.isAndroid() ? 5 : 6) + CFG.PADDING * (CFG.isAndroid() ? 5 : 6),
            CFG.GAME_WIDTH / 4,
            CFG.BUTTON_HEIGHT,
            true
         )
      );
      menuElements.add(
         new Text(
            "",
            -1,
            CFG.GAME_WIDTH / 2 - ImageManager.getImage(Images.gameLogo).getWidth() / 2 - CFG.PADDING * 2,
            menuElements.get(0).getPosY() - ImageManager.getImage(Images.gameLogo).getHeight() - CFG.PADDING,
            ImageManager.getImage(Images.gameLogo).getWidth() + CFG.PADDING * 4,
            CFG.BUTTON_HEIGHT
         ) {
            @Override
            public int getPosX() {
               return Menu_Main.this.getMenuElement(0).getPosX() + Menu_Main.this.getMenuElement(0).getWidth() / 2 - this.getWidth() / 2;
            }

            @Override
            public int getWidth() {
               return ImageManager.getImage(Images.gameLogo).getWidth();
            }

            @Override
            public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
               CFG.setRender_3(true);
               if (isActive) {
                  oSB.setColor(new Color(1.0F, 1.0F, 1.0F, 0.75F));
               } else if (this.getIsHovered()) {
                  oSB.setColor(new Color(1.0F, 1.0F, 1.0F, 1.0F));
               } else {
                  oSB.setColor(new Color(1.0F, 1.0F, 1.0F, 0.95F));
               }

               ImageManager.getImage(Images.gameLogo).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY);
               oSB.setColor(Color.WHITE);
            }

            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text("Bloody Europe", new Color(0.7F, 0.0F, 0.0F, 1.0F)));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
         }
      );
      int tRebuildPosY = 0;

      for (int i = 0; i < menuElements.size(); i++) {
         if (tRebuildPosY > menuElements.get(i).getPosY()) {
            tRebuildPosY = menuElements.get(i).getPosY();
         }
      }

      if (tRebuildPosY < 0) {
         for (int var13 = 0; var13 < menuElements.size(); var13++) {
            menuElements.get(var13).setPosY(menuElements.get(var13).getPosY() - tRebuildPosY + CFG.PADDING * 4);
         }
      }

      menuElements.add(
         new Button_Menu(null, -1, CFG.GAME_WIDTH - CFG.BUTTON_HEIGHT, CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT, CFG.BUTTON_HEIGHT, CFG.BUTTON_HEIGHT, true) {
            @Override
            public void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
               if (isActive) {
                  oSB.setColor(new Color(1.0F, 1.0F, 1.0F, 1.0F));
               } else if (this.getIsHovered()) {
                  oSB.setColor(new Color(1.0F, 1.0F, 1.0F, 0.65F));
               } else {
                  oSB.setColor(new Color(1.0F, 1.0F, 1.0F, Menu_Main.ICONS_ALPHA_PC));
               }

               ImageManager.getImage(Images.line_32_vertical)
                  .draw(
                     oSB,
                     this.getPosX() + iTranslateX,
                     this.getPosY() + this.getHeight() / 4 - ImageManager.getImage(Images.line_32_vertical).getHeight() + iTranslateY,
                     1,
                     this.getHeight() / 2
                  );
               ImageManager.getImage(Images.logo_steam)
                  .draw(
                     oSB,
                     this.getPosX() + this.getWidth() / 2 - ImageManager.getImage(Images.logo_steam).getWidth() / 2 + iTranslateX,
                     this.getPosY() + this.getHeight() / 2 - ImageManager.getImage(Images.logo_steam).getHeight() / 2 + iTranslateY
                  );
               oSB.setColor(Color.WHITE);
            }

            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text("http://lukaszjakowski.pl", CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }

            @Override
            public void drawMenuElementHover2(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
               if (this.menuElementHover != null) {
                  this.menuElementHover.drawAlwaysOver(oSB, Touch.getMousePosX(), this.getPosY());
               }
            }
         }
      );
      menuElements.add(
         new Button_Menu(null, -1, CFG.GAME_WIDTH - CFG.BUTTON_HEIGHT, CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT * 2, CFG.BUTTON_HEIGHT, CFG.BUTTON_HEIGHT, true) {
            @Override
            public void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
               if (isActive) {
                  oSB.setColor(new Color(1.0F, 1.0F, 1.0F, 1.0F));
               } else if (this.getIsHovered()) {
                  oSB.setColor(new Color(1.0F, 1.0F, 1.0F, 0.65F));
               } else {
                  oSB.setColor(new Color(1.0F, 1.0F, 1.0F, Menu_Main.ICONS_ALPHA));
               }

               ImageManager.getImage(Images.line_32_vertical)
                  .draw(
                     oSB,
                     this.getPosX() + iTranslateX,
                     this.getPosY() + this.getHeight() / 4 - ImageManager.getImage(Images.line_32_vertical).getHeight() + iTranslateY,
                     1,
                     this.getHeight() / 2
                  );
               ImageManager.getImage(Images.logo_android)
                  .draw(
                     oSB,
                     this.getPosX() + this.getWidth() / 2 - ImageManager.getImage(Images.logo_android).getWidth() / 2 + iTranslateX,
                     this.getPosY() + this.getHeight() / 2 - ImageManager.getImage(Images.logo_android).getHeight() / 2 + iTranslateY
                  );
               oSB.setColor(Color.WHITE);
            }

            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               nData.add(
                  new MenuElement_Hover_v2_Element_Type_Text(
                     "https://play.google.com/store/apps/details?id=age.of.civilizations2.jakowski.lukasz", CFG.COLOR_TEXT_MODIFIER_NEUTRAL
                  )
               );
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }

            @Override
            public void drawMenuElementHover2(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
               if (this.menuElementHover != null) {
                  this.menuElementHover.drawAlwaysOver(oSB, Touch.getMousePosX(), this.getPosY());
               }
            }
         }
      );
      menuElements.add(
         new Button_Menu(null, -1, CFG.GAME_WIDTH - CFG.BUTTON_HEIGHT, CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT * 3, CFG.BUTTON_HEIGHT, CFG.BUTTON_HEIGHT, true) {
            @Override
            public void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
               if (isActive) {
                  oSB.setColor(new Color(1.0F, 1.0F, 1.0F, 1.0F));
               } else if (this.getIsHovered()) {
                  oSB.setColor(new Color(1.0F, 1.0F, 1.0F, 0.65F));
               } else {
                  oSB.setColor(new Color(1.0F, 1.0F, 1.0F, Menu_Main.ICONS_ALPHA));
               }

               ImageManager.getImage(Images.line_32_vertical)
                  .draw(
                     oSB,
                     this.getPosX() + iTranslateX,
                     this.getPosY() + this.getHeight() / 4 - ImageManager.getImage(Images.line_32_vertical).getHeight() + iTranslateY,
                     1,
                     this.getHeight() / 2
                  );
               ImageManager.getImage(Images.logo_app)
                  .draw(
                     oSB,
                     this.getPosX() + this.getWidth() / 2 - ImageManager.getImage(Images.logo_app).getWidth() / 2 + iTranslateX,
                     this.getPosY() + this.getHeight() / 2 - ImageManager.getImage(Images.logo_app).getHeight() / 2 + iTranslateY
                  );
               oSB.setColor(Color.WHITE);
            }

            @Override
            public void drawMenuElementHover2(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
               if (this.menuElementHover != null) {
                  this.menuElementHover.drawAlwaysOver(oSB, Touch.getMousePosX(), this.getPosY());
               }
            }

            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text("www.AgeOfCivilizationsGame.com", CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
         }
      );
      menuElements.add(
         new Button_Menu(null, -1, CFG.GAME_WIDTH - CFG.BUTTON_HEIGHT, CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT * 4, CFG.BUTTON_HEIGHT, CFG.BUTTON_HEIGHT, true) {
            @Override
            public void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
               if (isActive) {
                  oSB.setColor(new Color(1.0F, 1.0F, 1.0F, 1.0F));
               } else if (this.getIsHovered()) {
                  oSB.setColor(new Color(1.0F, 1.0F, 1.0F, 0.65F));
               } else {
                  oSB.setColor(new Color(1.0F, 1.0F, 1.0F, Menu_Main.ICONS_ALPHA));
               }

               ImageManager.getImage(Images.line_32_vertical)
                  .draw(
                     oSB,
                     this.getPosX() + iTranslateX,
                     this.getPosY() + this.getHeight() / 4 - ImageManager.getImage(Images.line_32_vertical).getHeight() + iTranslateY,
                     1,
                     this.getHeight() / 2
                  );
               ImageManager.getImage(Images.logo_fb)
                  .draw(
                     oSB,
                     this.getPosX() + this.getWidth() / 2 - ImageManager.getImage(Images.logo_fb).getWidth() / 2 + iTranslateX,
                     this.getPosY() + this.getHeight() / 2 - ImageManager.getImage(Images.logo_fb).getHeight() / 2 + iTranslateY
                  );
               oSB.setColor(Color.WHITE);
            }

            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text("https://facebook.com/AgeofCivilizationsJakowski/", CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }

            @Override
            public void drawMenuElementHover2(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
               if (this.menuElementHover != null) {
                  this.menuElementHover.drawAlwaysOver(oSB, Touch.getMousePosX(), this.getPosY());
               }
            }
         }
      );
      menuElements.add(
         new Button_Menu(null, -1, CFG.GAME_WIDTH - CFG.BUTTON_HEIGHT, CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT * 5, CFG.BUTTON_HEIGHT, CFG.BUTTON_HEIGHT, true) {
            @Override
            public void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
               if (isActive) {
                  oSB.setColor(new Color(1.0F, 1.0F, 1.0F, 1.0F));
               } else if (this.getIsHovered()) {
                  oSB.setColor(new Color(1.0F, 1.0F, 1.0F, 0.65F));
               } else {
                  oSB.setColor(new Color(1.0F, 1.0F, 1.0F, Menu_Main.ICONS_ALPHA));
               }

               ImageManager.getImage(Images.line_32_vertical)
                  .draw(
                     oSB,
                     this.getPosX() + iTranslateX,
                     this.getPosY() + this.getHeight() / 4 - ImageManager.getImage(Images.line_32_vertical).getHeight() + iTranslateY,
                     1,
                     this.getHeight() / 2
                  );
               ImageManager.getImage(Images.logo_twit)
                  .draw(
                     oSB,
                     this.getPosX() + this.getWidth() / 2 - ImageManager.getImage(Images.logo_twit).getWidth() / 2 + iTranslateX,
                     this.getPosY() + this.getHeight() / 2 - ImageManager.getImage(Images.logo_twit).getHeight() / 2 + iTranslateY
                  );
               oSB.setColor(Color.WHITE);
            }

            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text("https://twitter.com/jakowskidev", CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }

            @Override
            public void drawMenuElementHover2(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
               if (this.menuElementHover != null) {
                  this.menuElementHover.drawAlwaysOver(oSB, Touch.getMousePosX(), this.getPosY());
               }
            }
         }
      );
      menuElements.add(
         new Button_Menu(null, -1, CFG.GAME_WIDTH - CFG.BUTTON_HEIGHT, CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT * 6, CFG.BUTTON_HEIGHT, CFG.BUTTON_HEIGHT, true) {
            @Override
            public void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
               if (isActive) {
                  oSB.setColor(new Color(1.0F, 1.0F, 1.0F, 1.0F));
               } else if (this.getIsHovered()) {
                  oSB.setColor(new Color(1.0F, 1.0F, 1.0F, 0.65F));
               } else {
                  oSB.setColor(new Color(1.0F, 1.0F, 1.0F, Menu_Main.ICONS_ALPHA));
               }

               ImageManager.getImage(Images.line_32_vertical)
                  .draw(
                     oSB,
                     this.getPosX() + iTranslateX,
                     this.getPosY() + this.getHeight() / 4 - ImageManager.getImage(Images.line_32_vertical).getHeight() + iTranslateY,
                     1,
                     this.getHeight() / 2
                  );
               ImageManager.getImage(Images.logo_yt)
                  .draw(
                     oSB,
                     this.getPosX() + this.getWidth() / 2 - ImageManager.getImage(Images.logo_yt).getWidth() / 2 + iTranslateX,
                     this.getPosY() + this.getHeight() / 2 - ImageManager.getImage(Images.logo_yt).getHeight() / 2 + iTranslateY
                  );
               oSB.setColor(Color.WHITE);
            }

            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text("https://www.YouTube.com/user/jakowskiuki", CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }

            @Override
            public void drawMenuElementHover2(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
               if (this.menuElementHover != null) {
                  this.menuElementHover.drawAlwaysOver(oSB, Touch.getMousePosX(), this.getPosY());
               }
            }
         }
      );
      this.initMenu(null, 0, 0, CFG.GAME_WIDTH, CFG.GAME_HEIGHT, menuElements);
      this.updateLanguage();
      this.iTitleOffset = CFG.XXXXHDPI ? 7 : (CFG.XXXHDPI ? 7 : (CFG.XXHDPI ? 7 : (CFG.XHDPI ? 7 : 7)));
   }

   @Override
   public final void updateLanguage() {
      this.getMenuElement(0).setText(CFG.langManager.get("Games"));
      this.getMenuElement(1).setText(CFG.langManager.get("Editor"));
      this.getMenuElement(2).setText(CFG.langManager.get("Settings"));
      this.getMenuElement(3).setText(RATE_THE_GAME ? CFG.langManager.get("Rate") + " Bloody Europe II" : CFG.langManager.get("Tutorial"));
      this.getMenuElement(4).setText(CFG.langManager.get("About"));
      this.getMenuElement(5).setText(CFG.isAndroid() ? CFG.langManager.get("") : CFG.langManager.get("Mods"));
      this.getMenuElement(6).setText(CFG.langManager.get("ExitGame"));
      CFG.sTOTAL = CFG.langManager.get("Total");
      CFG.sTOTAL_WORLDS_POPULATION = CFG.langManager.get("WorldsPopulation");
      CFG.glyphLayout.setText(CFG.fontMain, CFG.sLoading);
      CFG.iLoadingWidth = (int)CFG.glyphLayout.width;
      CFG.glyphLayout.setText(CFG.fontMain, CFG.getLukaszJakowskiGames());
      CFG.iJakowskiGamesWidth = (int)CFG.glyphLayout.width;
      CFG.glyphLayout.setText(CFG.fontMain, "presents");
      CFG.iJakowskiGames_PresentsWidth = (int)CFG.glyphLayout.width;
      CFG.glyphLayout.setText(CFG.fontMain, "Bloody Europe II");
      CFG.iAgeOfCivilizationsWidth = (int)CFG.glyphLayout.width;
      this.getMenuElement(7).setText("Bloody Europe II");
   }

   @Override
   public void drawScrollPos(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      if (sliderMenuIsActive) {
         super.drawScrollPos(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
      }
   }

   @Override
   public void beginClip(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      if (this.lTime + this.ANIMATION_TIME > System.currentTimeMillis()) {
         Rectangle clipBounds = new Rectangle(
            this.getPosX() + iTranslateX,
            CFG.GAME_HEIGHT - this.getPosY() - iTranslateY,
            this.getWidth(),
            -((int)(this.getHeight() * ((float)(System.currentTimeMillis() - this.lTime) / this.ANIMATION_TIME)))
         );
         oSB.flush();
         ScissorStack.pushScissors(clipBounds);
         CFG.setRender_3(true);
      } else {
         super.beginClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
      }
   }

   @Override
   public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      oSB.setColor(new Color(1.0F, 1.0F, 1.0F, 255 + iTranslateX / 2 < 0 ? 0.0F : 255 + iTranslateX / 2));
      ImageManager.getImage(Images.backgrounds.get(Images.backgroundLast)).draw2(oSB, 0 + iTranslateX, this.getMenuPosY(), CFG.GAME_WIDTH, CFG.GAME_HEIGHT);
      oSB.setColor(1.0F, 1.0F, 1.0F, Images.backgroundAlpha);
      ImageManager.getImage(Images.backgrounds.get(Images.background)).draw2(oSB, 0 + iTranslateX, this.getMenuPosY(), CFG.GAME_WIDTH, CFG.GAME_HEIGHT);
      oSB.setColor(new Color(1.0F, 1.0F, 1.0F, 255 + iTranslateX / 2 < 0 ? 0.0F : 255 + iTranslateX / 2));
      oSB.draw(
         ImageManager.getImage(Images.backgrounds.get(Images.backgroundLast)).getTexture(),
         0.0F,
         (float)(0 - CFG.GAME_HEIGHT + iTranslateY),
         (float)CFG.GAME_WIDTH,
         (float)CFG.GAME_HEIGHT
      );
      oSB.setColor(1.0F, 1.0F, 1.0F, Images.backgroundAlpha);
      oSB.draw(
         ImageManager.getImage(Images.backgrounds.get(Images.background)).getTexture(),
         0.0F,
         (float)(0 - CFG.GAME_HEIGHT + iTranslateY),
         (float)CFG.GAME_WIDTH,
         (float)CFG.GAME_HEIGHT
      );
      oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.6F));
      ImageManager.getImage(Images.gradient)
         .draw(oSB, iTranslateX, -ImageManager.getImage(Images.gradient).getHeight() + iTranslateY, CFG.GAME_WIDTH, CFG.PADDING * 3);
      ImageManager.getImage(Images.gradient)
         .draw(
            oSB,
            iTranslateX,
            CFG.GAME_HEIGHT - ImageManager.getImage(Images.gradient).getHeight() - CFG.PADDING * 3 + iTranslateY,
            CFG.GAME_WIDTH,
            CFG.PADDING * 3,
            false,
            true
         );
      oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.1F));
      ImageManager.getImage(Images.patt2).draw(oSB, iTranslateX, -ImageManager.getImage(Images.patt2).getHeight(), CFG.GAME_WIDTH, CFG.GAME_HEIGHT, 0.0F, 0);
      this.beginClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
      oSB.setColor(Color.WHITE);
      ImageManager.getImage(Images.main_menu_edge)
         .draw2(
            oSB,
            this.getMenuElement(7).getPosX() - ImageManager.getImage(Images.main_menu_edge).getWidth() + iTranslateX,
            this.getMenuElement(7).getPosY() - ImageManager.getImage(Images.main_menu_edge).getHeight() - CFG.PADDING + iTranslateY,
            this.getMenuElement(7).getWidth() + ImageManager.getImage(Images.main_menu_edge).getWidth(),
            this.getMenuElement(0).getPosY() - this.getMenuElement(7).getPosY() + CFG.PADDING
         );
      ImageManager.getImage(Images.main_menu_edge)
         .draw2(
            oSB,
            this.getMenuElement(7).getPosX() + this.getMenuElement(7).getWidth() + iTranslateX,
            this.getMenuElement(7).getPosY() - ImageManager.getImage(Images.main_menu_edge).getHeight() - CFG.PADDING + iTranslateY,
            ImageManager.getImage(Images.main_menu_edge).getWidth(),
            this.getMenuElement(0).getPosY() - this.getMenuElement(7).getPosY() + CFG.PADDING,
            true
         );
      ImageManager.getImage(Images.main_menu_edge)
         .draw2(
            oSB,
            this.getMenuElement(0).getPosX() + iTranslateX,
            this.getMenuElement(0).getPosY() - ImageManager.getImage(Images.main_menu_edge).getHeight() + iTranslateY,
            this.getMenuElement(0).getWidth() - ImageManager.getImage(Images.main_menu_edge).getWidth(),
            this.getMenuElement(6).getPosY()
               + this.getMenuElement(6).getHeight()
               - this.getMenuElement(0).getPosY()
               - ImageManager.getImage(Images.main_menu_edge).getHeight()
         );
      ImageManager.getImage(Images.main_menu_edge)
         .draw2(
            oSB,
            this.getMenuElement(0).getPosX() + this.getMenuElement(0).getWidth() - ImageManager.getImage(Images.main_menu_edge2).getWidth() + 2 + iTranslateX,
            this.getMenuElement(0).getPosY() - ImageManager.getImage(Images.main_menu_edge).getHeight() + iTranslateY,
            ImageManager.getImage(Images.main_menu_edge2).getWidth(),
            this.getMenuElement(6).getPosY()
               + this.getMenuElement(6).getHeight()
               - this.getMenuElement(0).getPosY()
               - ImageManager.getImage(Images.main_menu_edge).getHeight(),
            true
         );
      ImageManager.getImage(Images.main_menu_edge)
         .draw2(
            oSB,
            this.getMenuElement(0).getPosX() + iTranslateX,
            this.getMenuElement(6).getPosY() + this.getMenuElement(6).getHeight() - ImageManager.getImage(Images.main_menu_edge).getHeight() * 2 + iTranslateY,
            this.getMenuElement(0).getWidth() - ImageManager.getImage(Images.main_menu_edge).getWidth() + 2,
            ImageManager.getImage(Images.main_menu_edge).getHeight(),
            false,
            true
         );
      ImageManager.getImage(Images.main_menu_edge)
         .draw2(
            oSB,
            this.getMenuElement(0).getPosX() + this.getMenuElement(0).getWidth() - ImageManager.getImage(Images.main_menu_edge).getWidth() + iTranslateX,
            this.getMenuElement(6).getPosY() + this.getMenuElement(6).getHeight() - ImageManager.getImage(Images.main_menu_edge).getHeight() * 2 + iTranslateY,
            ImageManager.getImage(Images.main_menu_edge2).getWidth(),
            ImageManager.getImage(Images.main_menu_edge).getHeight(),
            true,
            true
         );
      this.drawMenu(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
      this.endClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
      CFG.drawVersion_LEFT_BOT(oSB, iTranslateX);
      oSB.setColor(Color.WHITE);
      CFG.setRender_3(true);
      if ((Map.GAME_CRASHED_LOADED_MIN_SCALE || CFG.map.getMapBG().getMapScale() <= 1) && CFG.map.getMapBG().getMapScale() == 1 && !CFG.toast.getInView()) {
         ArrayList<String> nMess = new ArrayList<>();
         ArrayList<Color> nCol = new ArrayList<>();
         nMess.add("Game crashed while loading");
         nCol.add(CFG.COLOR_TEXT_MODIFIER_NEGATIVE2);
         nMess.add("-- Loaded minimum scale of map --");
         nCol.add(CFG.COLOR_TEXT_MODIFIER_NEGATIVE2);
         nMess.add("Go to: Games -> Map: XX -> Earth: -> Scale X5");
         nCol.add(Color.WHITE);
         CFG.toast.setInView(nMess, nCol);
         CFG.toast.setTimeInView(6000);
      }
   }

   @Override
   public final void actionElement(int iID) {
      switch (iID) {
         case 0:
            CFG.menuManager.setViewID(Menu.eGAMES);
            CFG.menuManager.setOrderOfMenu_Games();
            break;
         case 1:
            SaveManager.gameCanBeContinued = false;
            CFG.menuManager.setViewID(Menu.eEDITOR);
            break;
         case 2:
            CFG.goToMenu2 = Menu.eMAINMENU;
            CFG.menuManager.setViewID(Menu.eSETTINGS);
            break;
         case 3:
            this.getMenuElement(iID).actionElement(iID);
            return;
         case 4:
            CFG.menuManager.setViewID(Menu.eABOUT);
            CFG.map.getMapScale().setNewCurrentScaleByButton2(0.175F);
            return;
         case 5:
            CFG.menuManager.setViewID(Menu.eMODS);
            return;
         case 6:
            CFG.setDialogType(Dialog.EXIT_GAME);
            return;
         case 7:
            CFG.GO_TO_LINK = "http://lukaszjakowski.pl";
            CFG.setDialogType(Dialog.GO_TO_LINK);
            return;
         case 8:
            CFG.GO_TO_LINK = "https://play.google.com/store/apps/details?id=age.of.civilizations2.jakowski.lukasz";
            CFG.setDialogType(Dialog.GO_TO_LINK);
            return;
         case 9:
            CFG.GO_TO_LINK = "http://ageofcivilizationsGame.com";
            CFG.setDialogType(Dialog.GO_TO_LINK);
            return;
         case 10:
            CFG.GO_TO_LINK = "https://www.facebook.com/AgeofCivilizationsJakowski/";
            CFG.setDialogType(Dialog.GO_TO_LINK);
            return;
         case 11:
            CFG.GO_TO_LINK = "https://twitter.com/jakowskidev";
            CFG.setDialogType(Dialog.GO_TO_LINK);
            return;
         case 12:
            CFG.GO_TO_LINK = "https://www.youtube.com/user/jakowskiuki";
            CFG.setDialogType(Dialog.GO_TO_LINK);
            return;
      }

      for (int i = 0; i < CFG.game.getProvincesSize(); i++) {
         if (!CFG.game.getProvince(i).getSeaProvince()) {
            CFG.game.getProvince(i).setFromCivID(0);
         }
      }

      CFG.map.getMapCoordinates().centerToRandomMapPosition();
   }

   @Override
   public final void onBackPressed() {
   }

   @Override
   public void setVisible(boolean visible) {
      super.setVisible(visible);
      this.lTime = System.currentTimeMillis();
   }
}
