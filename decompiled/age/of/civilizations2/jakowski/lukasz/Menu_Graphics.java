package age.of.civilizations2.jakowski.lukasz;

import java.util.ArrayList;

public class Menu_Graphics extends SliderMenu {
   public Menu_Graphics() {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      int tY = CFG.PADDING;
      menuElements.add(new Button_Menu(null, (int)(50.0F * CFG.GUI_SCALE), 0, tY, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT, true) {
         @Override
         public void actionElement(int iID) {
            CFG.menuManager.setViewID(Menu.eSETTINGS_RESOLUTION);
         }
      });
      int var3;
      menuElements.add(
         new Button_Menu(
            null,
            (int)(50.0F * CFG.GUI_SCALE),
            0,
            var3 = tY + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING,
            CFG.GAME_WIDTH,
            CFG.BUTTON_HEIGHT,
            true,
            ConfigINI.fullscreen
         ) {
            @Override
            public boolean getCheckboxState() {
               return ConfigINI.fullscreen;
            }

            @Override
            public void actionElement(int iID) {
               ConfigINI.fullscreen = !ConfigINI.fullscreen;
               ConfigINI.saveConfig();
               CFG.toast.setInView(CFG.langManager.get("GameNeedsToBeRestartedToApplyTheChanges"));
               CFG.toast.setTimeInView(6000);
            }
         }
      );
      menuElements.add(
         new Button_Menu(
            "-",
            -1,
            0,
            tY = var3 + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING,
            CFG.BUTTON_WIDTH + CFG.BUTTON_WIDTH / 2,
            CFG.BUTTON_HEIGHT,
            true
         ) {
            @Override
            public void actionElement(int iID) {
               if (--ConfigINI.iSamples <= 0) {
                  ConfigINI.iSamples = -1;
               }

               ConfigINI.saveConfig();
               Menu_Graphics.this.updateLanguage();
               CFG.toast.setInView(CFG.langManager.get("GameNeedsToBeRestartedToApplyTheChanges"));
               CFG.toast.setTimeInView(6000);
            }
         }
      );
      menuElements.add(
         new Button_Menu_Classic(
            null, -1, CFG.BUTTON_WIDTH + CFG.BUTTON_WIDTH / 2, tY, CFG.GAME_WIDTH - (CFG.BUTTON_WIDTH + CFG.BUTTON_WIDTH / 2) * 2, CFG.BUTTON_HEIGHT, true
         ) {
            @Override
            public void actionElement(int iID) {
               ConfigINI.iSamples = -1;
               ConfigINI.saveConfig();
               Menu_Graphics.this.updateLanguage();
               CFG.toast.setInView(CFG.langManager.get("GameNeedsToBeRestartedToApplyTheChanges"));
               CFG.toast.setTimeInView(6000);
            }
         }
      );
      menuElements.add(
         new Button_Menu_ReflectedBG(
            "+", -1, CFG.GAME_WIDTH - (CFG.BUTTON_WIDTH + CFG.BUTTON_WIDTH / 2), tY, CFG.BUTTON_WIDTH + CFG.BUTTON_WIDTH / 2, CFG.BUTTON_HEIGHT, true
         ) {
            @Override
            public void actionElement(int iID) {
               ConfigINI.iSamples = ConfigINI.iSamples <= 0 ? 1 : ++ConfigINI.iSamples;
               ConfigINI.saveConfig();
               Menu_Graphics.this.updateLanguage();
               CFG.toast.setInView(CFG.langManager.get("GameNeedsToBeRestartedToApplyTheChanges"));
               CFG.toast.setTimeInView(6000);
            }
         }
      );
      int var5;
      menuElements.add(
         new Button_Menu(
            null,
            (int)(50.0F * CFG.GUI_SCALE),
            0,
            var5 = tY + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING,
            CFG.GAME_WIDTH,
            CFG.BUTTON_HEIGHT,
            true,
            ConfigINI.vSync
         ) {
            @Override
            public boolean getCheckboxState() {
               return ConfigINI.vSync;
            }

            @Override
            public void actionElement(int iID) {
               ConfigINI.vSync = !ConfigINI.vSync;
               ConfigINI.saveConfig();
               CFG.toast.setInView(CFG.langManager.get("GameNeedsToBeRestartedToApplyTheChanges"));
               CFG.toast.setTimeInView(6000);
            }
         }
      );
      menuElements.add(
         new Button_Menu(
            null,
            (int)(50.0F * CFG.GUI_SCALE),
            0,
            tY = var5 + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING,
            CFG.GAME_WIDTH,
            CFG.BUTTON_HEIGHT,
            true
         ) {
            @Override
            public void actionElement(int iID) {
               CFG.menuManager.setViewID(Menu.eSELECT_UI_SCALE);
            }
         }
      );
      tY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
      this.initMenu(
         null, 0, CFG.BUTTON_HEIGHT * 3 / 4, CFG.GAME_WIDTH, CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT * 3 / 4 - CFG.BUTTON_HEIGHT - CFG.PADDING, menuElements
      );
      this.updateLanguage();
   }

   @Override
   public void updateLanguage() {
      if (ConfigINI.iWidth > 0 && ConfigINI.iHeight > 0) {
         this.getMenuElement(0).setText(CFG.langManager.get("Resolution") + ": " + ConfigINI.iWidth + "x" + ConfigINI.iHeight);
      } else {
         this.getMenuElement(0).setText(CFG.langManager.get("Resolution") + ": " + CFG.GAME_WIDTH + "x" + CFG.GAME_HEIGHT);
      }

      this.getMenuElement(1).setText(CFG.langManager.get("Fullscreen"));
      this.getMenuElement(3)
         .setText(CFG.langManager.get("Antialliasng") + ": " + (ConfigINI.iSamples <= 0 ? CFG.langManager.get("Disabled") : ConfigINI.iSamples));
      this.getMenuElement(5).setText(CFG.langManager.get("VSync"));
      this.getMenuElement(6).setText(CFG.langManager.get("UIScale"));
   }

   @Override
   public final void actionElement(int iID) {
      this.getMenuElement(iID).actionElement(iID);
   }
}
