package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.GdxRuntimeException;
import java.util.ArrayList;

public class Menu_InGame_FormableCiv_Provinces extends SliderMenu {
   public static Image lFlag = null;

   public Menu_InGame_FormableCiv_Provinces() {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      menuElements.add(
         new Button_FormCivTitle(
            CFG.langManager.get("FormX", CFG.langManager.getCiv(CFG.formableCivs_GameData.getFormableCivTag())),
            0,
            0,
            CFG.GAME_WIDTH,
            Math.max(CFG.BUTTON_HEIGHT * 4 / 5, Math.max(CFG.CIV_FLAG_HEIGHT + CFG.PADDING * 4, (CFG.TEXT_HEIGHT + CFG.PADDING) * 2 + CFG.PADDING)),
            true,
            CFG.canFormACiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), CFG.formableCivs_GameData.getFormableCivTag(), false)
         ) {
            @Override
            public void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
               super.drawText(oSB, iTranslateX, iTranslateY, isActive);

               try {
                  Menu_InGame_FormableCiv_Provinces.lFlag
                     .draw(
                        oSB,
                        this.getPosX()
                           + (int)((this.getWidth() - this.getTextWidth() * 0.85F) / 2.0F)
                           - ImageManager.getImage(Images.flag_rect).getWidth()
                           - CFG.PADDING
                           + iTranslateX,
                        this.getPosY()
                           - Menu_InGame_FormableCiv_Provinces.lFlag.getHeight()
                           + this.getHeight() / 2
                           - ImageManager.getImage(Images.flag_rect).getHeight() / 2
                           + iTranslateY,
                        ImageManager.getImage(Images.flag_rect).getWidth(),
                        ImageManager.getImage(Images.flag_rect).getHeight()
                     );
                  ImageManager.getImage(Images.flag_rect)
                     .draw(
                        oSB,
                        this.getPosX()
                           + (int)((this.getWidth() - this.getTextWidth() * 0.85F) / 2.0F)
                           - ImageManager.getImage(Images.flag_rect).getWidth()
                           - CFG.PADDING
                           + iTranslateX,
                        this.getPosY() + this.getHeight() / 2 - ImageManager.getImage(Images.flag_rect).getHeight() / 2 + iTranslateY
                     );
               } catch (NullPointerException var6) {
               }
            }

            @Override
            public void buildElementHover() {
               try {
                  ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
                  ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
                  nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()));
                  nData.add(
                     new MenuElement_Hover_v2_Element_Type_Text(
                        CFG.langManager.get("CivilizationChangesToX", CFG.langManager.getCiv(CFG.formableCivs_GameData.getFormableCivTag())),
                        CFG.COLOR_TEXT_NUM_OF_PROVINCES
                     )
                  );
                  nElements.add(new MenuElement_Hover_v2_Element2(nData));
                  nData.clear();
                  if (CFG.game.getProvince(CFG.formableCivs_GameData.getCapitalProvinceID()).getWasteland() < 0
                     && CFG.game.getProvince(CFG.formableCivs_GameData.getCapitalProvinceID()).getCitiesSize() > 0) {
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Image(
                           CFG.game.getProvince(CFG.formableCivs_GameData.getCapitalProvinceID()).getCivID()
                                 == CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()
                              ? Images.icon_check_true
                              : Images.icon_check_false
                        )
                     );
                     nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Capital") + ": "));
                     nData.add(
                        new MenuElement_Hover_v2_Element_Type_Text(
                           CFG.game.getProvince(CFG.formableCivs_GameData.getCapitalProvinceID()).getCity(0).getCityName(), CFG.COLOR_TEXT_NUM_OF_PROVINCES
                        )
                     );
                     nElements.add(new MenuElement_Hover_v2_Element2(nData));
                     nData.clear();
                  }

                  nData.add(
                     new MenuElement_Hover_v2_Element_Type_Image(
                        CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoney()
                              >= CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getSanboxMovePoints()
                           ? Images.icon_check_true
                           : Images.icon_check_false
                     )
                  );
                  nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Cost") + ": "));
                  nData.add(
                     new MenuElement_Hover_v2_Element_Type_Text(
                        Integer.toString(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getSanboxMovePoints()),
                        CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoney()
                              >= CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getSanboxMovePoints()
                           ? CFG.COLOR_INGAME_GOLD
                           : CFG.COLOR_TEXT_MODIFIER_NEGATIVE2
                     )
                  );
                  nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_gold, CFG.PADDING, 0));
                  nElements.add(new MenuElement_Hover_v2_Element2(nData));
                  nData.clear();
                  nData.add(
                     new MenuElement_Hover_v2_Element_Type_Image(
                        CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getDiplomacyPoints() >= 24
                           ? Images.icon_check_true
                           : Images.icon_check_false
                     )
                  );
                  nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("DiplomacyPoints") + ": "));
                  nData.add(
                     new MenuElement_Hover_v2_Element_Type_Text(
                        "2.4",
                        CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getDiplomacyPoints() >= 24
                           ? CFG.COLOR_INGAME_DIPLOMACY_POINTS
                           : CFG.COLOR_TEXT_MODIFIER_NEGATIVE2
                     )
                  );
                  nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_diplomacy_points, CFG.PADDING, 0));
                  nElements.add(new MenuElement_Hover_v2_Element2(nData));
                  nData.clear();
                  nData.add(
                     new MenuElement_Hover_v2_Element_Type_Image(
                        CFG.game.isAtPeace(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCivID())
                           ? Images.icon_check_true
                           : Images.icon_check_false
                     )
                  );
                  nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("AtPeace")));
                  nElements.add(new MenuElement_Hover_v2_Element2(nData));
                  nData.clear();
                  nData.add(
                     new MenuElement_Hover_v2_Element_Type_Image(
                        CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCivID()
                              == CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getPuppetOfCivID()
                           ? Images.icon_check_true
                           : Images.icon_check_false
                     )
                  );
                  nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("IsNotAVassal")));
                  nElements.add(new MenuElement_Hover_v2_Element2(nData));
                  nData.clear();
                  nData.add(
                     new MenuElement_Hover_v2_Element_Type_Image(
                        CFG.ownAllProvinces_FormableCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) ? Images.icon_check_true : Images.icon_check_false
                     )
                  );
                  nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("OwnsAllProvinces")));
                  nElements.add(new MenuElement_Hover_v2_Element2(nData));
                  nData.clear();
                  nData.add(
                     new MenuElement_Hover_v2_Element_Type_Image(
                        CFG.doesNotExists_FormableCiv(CFG.formableCivs_GameData.getFormableCivTag()) ? Images.icon_check_true : Images.icon_check_false
                     )
                  );
                  nData.add(
                     new MenuElement_Hover_v2_Element_Type_Text(
                        CFG.langManager.get("XDoesNotExist", CFG.langManager.getCiv(CFG.formableCivs_GameData.getFormableCivTag()))
                     )
                  );
                  nElements.add(new MenuElement_Hover_v2_Element2(nData));
                  nData.clear();
                  this.menuElementHover = new MenuElement_Hover_v2(nElements);
               } catch (IndexOutOfBoundsException var3) {
                  this.menuElementHover = null;
               }
            }
         }
      );
      menuElements.add(new Button_Game(null, -1, CFG.PADDING, CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING, CFG.BUTTON_WIDTH + CFG.BUTTON_WIDTH / 2));
      menuElements.add(
         new Button_Game(
            null, -1, CFG.PADDING * 2 + CFG.BUTTON_WIDTH + CFG.BUTTON_WIDTH / 2, CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING, CFG.BUTTON_WIDTH
         ) {
            @Override
            public void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
               if (this.getIsHovered()) {
                  oSB.setColor(new Color(1.0F, 1.0F, 1.0F, 0.75F));
               }

               ImageManager.getImage(Images.wikipedia)
                  .draw(
                     oSB,
                     this.getPosX() + this.getWidth() / 2 - ImageManager.getImage(Images.wikipedia).getWidth() / 2 + iTranslateX,
                     this.getPosY() + this.getHeight() / 2 - ImageManager.getImage(Images.wikipedia).getHeight() / 2 + iTranslateY
                  );
               oSB.setColor(Color.WHITE);
            }

            @Override
            public void buildElementHover() {
               try {
                  ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
                  ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
                  nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Wiki") + ": "));
                  nData.add(
                     new MenuElement_Hover_v2_Element_Type_Text(
                        CFG.langManager.getCiv(CFG.formableCivs_GameData.getFormableCivTag()), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                     )
                  );
                  nElements.add(new MenuElement_Hover_v2_Element2(nData));
                  nData.clear();
                  this.menuElementHover = new MenuElement_Hover_v2(nElements);
               } catch (IndexOutOfBoundsException var3) {
                  this.menuElementHover = null;
               }
            }
         }
      );
      int tempPop = 0;

      for (int i = 0; i < CFG.formableCivs_GameData.getProvincesSize(); i++) {
         tempPop += CFG.game.getProvince(CFG.formableCivs_GameData.getProvinceID(i)).getPopulationData().getPopulation();
      }

      menuElements.add(
         new Button_TodayPartOf_Population(
            "" + CFG.getNumberWithSpaces("" + tempPop),
            CFG.getPercentage(tempPop, CFG.game.countWorld_Population(), 4),
            0,
            CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING * 3 - Math.max(CFG.TEXT_HEIGHT + CFG.PADDING * 4, CFG.CIV_FLAG_HEIGHT + CFG.PADDING * 2),
            CFG.CIV_INFO_MENU_WIDTH * 3 / 4,
            Math.max(CFG.TEXT_HEIGHT + CFG.PADDING * 4, CFG.CIV_FLAG_HEIGHT + CFG.PADDING * 2),
            true
         )
      );
      this.initMenu(null, 0, 0, CFG.GAME_WIDTH, CFG.GAME_HEIGHT, menuElements);
      this.updateLanguage();
      this.loadFlag(CFG.formableCivs_GameData.getFormableCivTag());
   }

   @Override
   public void updateLanguage() {
      this.getMenuElement(1).setText(CFG.langManager.get("Back"));
   }

   @Override
   public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.45F));
      ImageManager.getImage(Images.gradient)
         .draw(oSB, iTranslateX, -ImageManager.getImage(Images.gradient).getHeight() + iTranslateY, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT / 2);
      ImageManager.getImage(Images.gradient)
         .draw(
            oSB,
            iTranslateX,
            CFG.GAME_HEIGHT - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY,
            CFG.GAME_WIDTH,
            CFG.BUTTON_HEIGHT / 2,
            false,
            true
         );
      oSB.setColor(Color.WHITE);
      CFG.drawEditorButtons_Bot_Edge_R(
         oSB,
         iTranslateX,
         this.getMenuElement(2).getPosY() - CFG.PADDING - 1 + iTranslateY,
         this.getMenuElement(2).getPosX() + this.getMenuElement(2).getWidth() + CFG.PADDING + 1,
         CFG.BUTTON_HEIGHT + CFG.PADDING * 2 + 1
      );
      super.draw(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
   }

   @Override
   public final void actionElement(int iID) {
      switch (iID) {
         case 0:
            if (this.getMenuElement(iID).getCheckboxState()) {
               CFG.setDialogType(Dialog.FORM_A_CIV);
            } else {
               try {
                  if (!CFG.ownAllProvinces_FormableCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())) {
                     CFG.map.getMapCoordinates().centerToProvinceID(CFG.formableCivs_GameData.getCapitalProvinceID());
                  }
               } catch (IndexOutOfBoundsException var3) {
               }
            }
            break;
         case 1:
            this.onBackPressed();
            break;
         case 2:
            CFG.EDITOR_ACTIVE_GAMEDATA_TAG = CFG.formableCivs_GameData.getFormableCivTag();
            CFG.setDialogType(Dialog.GO_TO_WIKI);
      }
   }

   @Override
   public final void onBackPressed() {
      this.disposeFlag();
      CFG.menuManager.setViewID(Menu.eINGAME);
      CFG.game.setActiveProvinceID(CFG.game.getPlayer(CFG.PLAYER_TURNID).iBefore_ActiveProvince);
      CFG.game.checkProvinceActionMenu();
      CFG.map.getMapBG().updateWorldMap_Shaders();
      CFG.viewsManager.setActiveViewID(CFG.game.getPlayer(CFG.PLAYER_TURNID).iACTIVE_VIEW_MODE);
   }

   @Override
   public void onMenuPressed() {
      this.onBackPressed();
   }

   public final void loadFlag(String nTag) {
      this.disposeFlag();

      try {
         try {
            lFlag = new Image(new Texture(Gdx.files.internal("game/flags/" + nTag + ".png")), Texture.TextureFilter.Nearest);
         } catch (GdxRuntimeException var7) {
            try {
               lFlag = new Image(
                  new Texture(Gdx.files.internal("game/flags/" + CFG.ideologiesManager.getRealTag(nTag) + ".png")), Texture.TextureFilter.Nearest
               );
            } catch (GdxRuntimeException var6) {
               if (CFG.isAndroid()) {
                  try {
                     lFlag = new Image(
                        new Texture(Gdx.files.local("game/civilizations_editor/" + nTag + "/" + nTag + "_FL.png")), Texture.TextureFilter.Nearest
                     );
                  } catch (GdxRuntimeException var5) {
                     lFlag = new Image(
                        new Texture(Gdx.files.internal("game/civilizations_editor/" + nTag + "/" + nTag + "_FL.png")), Texture.TextureFilter.Nearest
                     );
                  }
               } else {
                  lFlag = new Image(
                     new Texture(Gdx.files.internal("game/civilizations_editor/" + nTag + "/" + nTag + "_FL.png")), Texture.TextureFilter.Nearest
                  );
               }
            }
         }
      } catch (GdxRuntimeException var8) {
         lFlag = null;
      } catch (OutOfMemoryError var9) {
         lFlag = null;
      }
   }

   public final void disposeFlag() {
      if (lFlag != null) {
         lFlag.getTexture().dispose();
         lFlag = null;
      }
   }
}
