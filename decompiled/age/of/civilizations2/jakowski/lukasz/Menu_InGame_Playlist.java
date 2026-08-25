package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

class Menu_InGame_Playlist extends SliderMenu {
   protected Menu_InGame_Playlist() {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      int tempWidth = CFG.CIV_INFO_MENU_WIDTH * 2;
      menuElements.add(
         new Button_FlagActionSliderStyle(
            ">>",
            -1,
            tempWidth - CFG.BUTTON_WIDTH * 3 / 4 - CFG.PADDING,
            CFG.PADDING,
            CFG.BUTTON_WIDTH * 3 / 4,
            CFG.TEXT_HEIGHT + CFG.PADDING * 2 + CFG.PADDING * 4,
            true
         ) {
            @Override
            public int getPosX() {
               return Menu_InGame_Playlist.this.getElementW() - this.getWidth() - CFG.PADDING;
            }

            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Next"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("NowPlaying") + ": "));
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.soundsManager.getCurrentMusicTittle(), CFG.COLOR_TEXT_MODIFIER_NEUTRAL2));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("NextMusic") + ": "));
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.soundsManager.getNextMusicTittle(), CFG.COLOR_TEXT_MODIFIER_NEUTRAL2));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
         }
      );
      menuElements.add(
         new Button_FlagActionSliderStyle(
            "<<",
            -1,
            tempWidth - CFG.BUTTON_WIDTH * 3 / 4 - CFG.PADDING,
            menuElements.get(menuElements.size() - 1).getPosY() + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING,
            CFG.BUTTON_WIDTH * 3 / 4,
            CFG.TEXT_HEIGHT + CFG.PADDING * 2 + CFG.PADDING * 4,
            true
         ) {
            @Override
            public int getPosX() {
               return Menu_InGame_Playlist.this.getElementW() - this.getWidth() - CFG.PADDING;
            }

            @Override
            public void buildElementHover() {
               ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Previous"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("NowPlaying") + ": "));
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.soundsManager.getCurrentMusicTittle(), CFG.COLOR_TEXT_MODIFIER_NEUTRAL2));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("PreviousMusic") + ": "));
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.soundsManager.getLastMusicTittle(), CFG.COLOR_TEXT_MODIFIER_NEUTRAL2));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
         }
      );
      menuElements.add(
         new Slider_FlagAction_Clear(
            CFG.langManager.get("MusicVolume"),
            CFG.PADDING,
            CFG.PADDING,
            tempWidth - CFG.PADDING * 3 - CFG.BUTTON_WIDTH,
            CFG.TEXT_HEIGHT + CFG.PADDING * 2 + CFG.PADDING * 4,
            0,
            100,
            (int)(CFG.soundsManager.getMusicVolume() * 100.0F)
         ) {
            @Override
            public String getDrawText() {
               return super.getDrawText() + "%";
            }

            @Override
            public int getWidth() {
               return Menu_InGame_Playlist.this.getElementW() - CFG.PADDING * 3 - CFG.BUTTON_WIDTH * 3 / 4;
            }

            @Override
            public int getSliderHeight() {
               return CFG.PADDING * 2;
            }
         }
      );
      menuElements.add(
         new Slider_FlagAction_Clear(
            CFG.langManager.get("EffectVolume"),
            CFG.PADDING,
            menuElements.get(menuElements.size() - 1).getPosY() + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING,
            tempWidth - CFG.PADDING * 3 - CFG.BUTTON_WIDTH,
            CFG.TEXT_HEIGHT + CFG.PADDING * 2 + CFG.PADDING * 4,
            0,
            100,
            (int)(CFG.soundsManager.getSoundsVolume() * 100.0F)
         ) {
            @Override
            public String getDrawText() {
               return super.getDrawText() + "%";
            }

            @Override
            public int getWidth() {
               return Menu_InGame_Playlist.this.getElementW() - CFG.PADDING * 3 - CFG.BUTTON_WIDTH * 3 / 4;
            }

            @Override
            public int getSliderHeight() {
               return CFG.PADDING * 2;
            }
         }
      );
      menuElements.add(
         new Slider_FlagAction_Clear(
            CFG.langManager.get("MasterVolume"),
            CFG.PADDING,
            menuElements.get(menuElements.size() - 1).getPosY() + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING,
            tempWidth - CFG.PADDING * 3 - CFG.BUTTON_WIDTH,
            CFG.TEXT_HEIGHT + CFG.PADDING * 2 + CFG.PADDING * 4,
            0,
            100,
            (int)(CFG.soundsManager.getMasterVolume() * 100.0F)
         ) {
            @Override
            public String getDrawText() {
               return super.getDrawText() + "%";
            }

            @Override
            public int getWidth() {
               return Menu_InGame_Playlist.this.getElementW() - CFG.PADDING * 2;
            }

            @Override
            public int getSliderHeight() {
               return CFG.PADDING * 2;
            }
         }
      );
      menuElements.add(
         new Button_Options_NS(
            CFG.langManager.get("MusicList"),
            -1,
            CFG.PADDING,
            menuElements.get(menuElements.size() - 1).getPosY() + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING,
            470,
            CFG.BUTTON_HEIGHT * 3 / 5,
            true
         ) {}
      );
      int tempMenuPosY = ImageManager.getImage(Images.top_left2).getHeight() + CFG.PADDING * 2 + CFG.BUTTON_HEIGHT * 3 / 5;
      this.initMenu(
         new SliderMenuTitle(CFG.langManager.get("Audio"), CFG.BUTTON_HEIGHT * 3 / 5, true, false) {
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
               oSB.setColor(new Color(0.2627451F, 0.30980393F, 0.45490196F, 0.165F));
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
               oSB.setColor(new Color(0.2627451F, 0.30980393F, 0.45490196F, 0.375F));
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
         CFG.GAME_WIDTH - tempWidth - CFG.PADDING * 2 - CFG.CIV_INFO_MENU_WIDTH / 2,
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

      for (int i = 0; i < this.getMenuElementsSize(); i++) {
         this.getMenuElement(i).setCurrent(this.getMenuElement(i).getCurrent());
      }
   }

   @Override
   public void updateLanguage() {
   }

   @Override
   public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
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

   @Override
   public void drawScrollPos(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      if (sliderMenuIsActive) {
         super.drawScrollPos(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
      }
   }

   @Override
   public final void actionElement(int iID) {
      switch (iID) {
         case 0:
            CFG.soundsManager.loadNextMusic();
            break;
         case 1:
            CFG.soundsManager.loadPreviousMusic();
            break;
         case 2:
            CFG.soundsManager.setMusicVolume(this.getMenuElement(iID).getCurrent() / 100.0F);
            CFG.settingsManager.VOLUME_MUSIC = CFG.soundsManager.getMusicVolume();
            CFG.saveSettings();
            break;
         case 3:
            CFG.soundsManager.setSoundsVolume(this.getMenuElement(iID).getCurrent() / 100.0F);
            CFG.settingsManager.VOLUME_SOUNDS = CFG.soundsManager.getSoundsVolume();
            CFG.saveSettings();
            break;
         case 4:
            CFG.soundsManager.setMasterVolume(this.getMenuElement(iID).getCurrent() / 100.0F);
            CFG.settingsManager.VOLUME_MASTER = CFG.soundsManager.getMasterVolume();
            CFG.saveSettings();
            break;
         case 5:
            try {
               CFG.menuManager.setPMMLMenu(CFG.menuManager.getPMMLMenu());
               CFG.menuManager.setViewID(Menu.eMUSICLIST);
            } catch (Exception var3) {
               var3.printStackTrace();
            }
      }
   }

   protected final int getW() {
      return this.getWidth();
   }

   protected final int getElementW() {
      return this.getW();
   }
}
