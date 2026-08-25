package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;
import java.util.List;

public class Keyboard extends SliderMenu {
   public List<String> lKeys = new ArrayList<>();
   public List<String> lKeysRUS = new ArrayList<>();
   public List<String> lKeysSHIFT = new ArrayList<>();
   public List<String> lKeysNUM = new ArrayList<>();
   public List<String> lKeys123 = new ArrayList<>();
   public int animationStepID = 0;
   public int animationChangePosY;
   public boolean closeMenu = false;
   public long barTime;
   public boolean drawBar;
   public static boolean shift = false;
   public static boolean rus = false;
   public static boolean numbers = false;
   public int iTextWidth;
   public int iTextHeight;
   public static boolean colorPickerMode = false;
   public static int activeColor_RGB_ID = -1;
   public static boolean commandsMode = false;
   public static int changeCivilizationNameMode = 0;
   public static int changeProvinceNameMode = -1;
   public static int changeCityNameIDToo = -1;
   protected static int allianceID;
   protected static boolean allianceRenameMode = false;
   protected static SliderMenuTitle menuElementAlTitle;

   public Keyboard() {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      if (CFG.settingsManager.LANGUAGE_TAG.equals("ru")) {
         this.lKeys.add("й");
         this.lKeys.add("ц");
         this.lKeys.add("у");
         this.lKeys.add("к");
         this.lKeys.add("е");
         this.lKeys.add("н");
         this.lKeys.add("г");
         this.lKeys.add("ш");
         this.lKeys.add("щ");
         this.lKeys.add("з");
         this.lKeys.add("ф");
         this.lKeys.add("ы");
         this.lKeys.add("в");
         this.lKeys.add("а");
         this.lKeys.add("п");
         this.lKeys.add("р");
         this.lKeys.add("о");
         this.lKeys.add("л");
         this.lKeys.add("д");
         this.lKeys.add("я");
         this.lKeys.add("ч");
         this.lKeys.add("с");
         this.lKeys.add("м");
         this.lKeys.add("и");
         this.lKeys.add("т");
         this.lKeys.add("ь");
         this.lKeys.add("SH");
         this.lKeys.add("<<");
         this.lKeys.add("123");
         this.lKeys.add("Space");
         this.lKeys.add(",");
         this.lKeys.add(".");
         this.lKeysSHIFT.add("Й");
         this.lKeysSHIFT.add("Ц");
         this.lKeysSHIFT.add("У");
         this.lKeysSHIFT.add("К");
         this.lKeysSHIFT.add("Е");
         this.lKeysSHIFT.add("Н");
         this.lKeysSHIFT.add("Г");
         this.lKeysSHIFT.add("Ш");
         this.lKeysSHIFT.add("Щ");
         this.lKeysSHIFT.add("З");
         this.lKeysSHIFT.add("Ф");
         this.lKeysSHIFT.add("Ы");
         this.lKeysSHIFT.add("В");
         this.lKeysSHIFT.add("А");
         this.lKeysSHIFT.add("П");
         this.lKeysSHIFT.add("Р");
         this.lKeysSHIFT.add("О");
         this.lKeysSHIFT.add("Л");
         this.lKeysSHIFT.add("Д");
         this.lKeysSHIFT.add("Я");
         this.lKeysSHIFT.add("Ч");
         this.lKeysSHIFT.add("С");
         this.lKeysSHIFT.add("М");
         this.lKeysSHIFT.add("И");
         this.lKeysSHIFT.add("Т");
         this.lKeysSHIFT.add("Ь");
         this.lKeysNUM.add("1");
         this.lKeysNUM.add("2");
         this.lKeysNUM.add("3");
         this.lKeysNUM.add("4");
         this.lKeysNUM.add("5");
         this.lKeysNUM.add("6");
         this.lKeysNUM.add("7");
         this.lKeysNUM.add("8");
         this.lKeysNUM.add("9");
         this.lKeysNUM.add("0");
         this.lKeys123.add("х");
         this.lKeys123.add("Х");
         this.lKeys123.add("ж");
         this.lKeys123.add("Ж");
         this.lKeys123.add("э");
         this.lKeys123.add("Э");
         this.lKeys123.add("б");
         this.lKeys123.add("Б");
         this.lKeys123.add("ю");
         this.lKeys123.add("Ю");
         this.lKeys123.add("-");
         this.lKeys123.add("+");
         this.lKeys123.add("(");
         this.lKeys123.add(")");
         this.lKeys123.add("!");
         this.lKeys123.add("?");
      } else {
         this.lKeys.add("q");
         this.lKeys.add("w");
         this.lKeys.add("e");
         this.lKeys.add("r");
         this.lKeys.add("t");
         this.lKeys.add("y");
         this.lKeys.add("u");
         this.lKeys.add("i");
         this.lKeys.add("o");
         this.lKeys.add("p");
         this.lKeys.add("a");
         this.lKeys.add("s");
         this.lKeys.add("d");
         this.lKeys.add("f");
         this.lKeys.add("g");
         this.lKeys.add("h");
         this.lKeys.add("j");
         this.lKeys.add("k");
         this.lKeys.add("l");
         this.lKeys.add("z");
         this.lKeys.add("x");
         this.lKeys.add("c");
         this.lKeys.add("v");
         this.lKeys.add("b");
         this.lKeys.add("n");
         this.lKeys.add("m");
         this.lKeys.add("SH");
         this.lKeys.add("<<");
         this.lKeys.add("123");
         this.lKeys.add("Space");
         this.lKeys.add(",");
         this.lKeys.add(".");
         this.lKeysSHIFT.add("Q");
         this.lKeysSHIFT.add("W");
         this.lKeysSHIFT.add("E");
         this.lKeysSHIFT.add("R");
         this.lKeysSHIFT.add("T");
         this.lKeysSHIFT.add("Y");
         this.lKeysSHIFT.add("U");
         this.lKeysSHIFT.add("I");
         this.lKeysSHIFT.add("O");
         this.lKeysSHIFT.add("P");
         this.lKeysSHIFT.add("A");
         this.lKeysSHIFT.add("S");
         this.lKeysSHIFT.add("D");
         this.lKeysSHIFT.add("F");
         this.lKeysSHIFT.add("G");
         this.lKeysSHIFT.add("H");
         this.lKeysSHIFT.add("J");
         this.lKeysSHIFT.add("K");
         this.lKeysSHIFT.add("L");
         this.lKeysSHIFT.add("Z");
         this.lKeysSHIFT.add("X");
         this.lKeysSHIFT.add("C");
         this.lKeysSHIFT.add("V");
         this.lKeysSHIFT.add("B");
         this.lKeysSHIFT.add("N");
         this.lKeysSHIFT.add("M");
         this.lKeysNUM.add("1");
         this.lKeysNUM.add("2");
         this.lKeysNUM.add("3");
         this.lKeysNUM.add("4");
         this.lKeysNUM.add("5");
         this.lKeysNUM.add("6");
         this.lKeysNUM.add("7");
         this.lKeysNUM.add("8");
         this.lKeysNUM.add("9");
         this.lKeysNUM.add("0");
         this.lKeys123.add("@");
         this.lKeys123.add("*");
         this.lKeys123.add("#");
         this.lKeys123.add(":");
         this.lKeys123.add(";");
         this.lKeys123.add("&");
         this.lKeys123.add("_");
         this.lKeys123.add("(");
         this.lKeys123.add(")");
         this.lKeys123.add("-");
         this.lKeys123.add("+");
         this.lKeys123.add("'");
         this.lKeys123.add("\"");
         this.lKeys123.add("%");
         this.lKeys123.add("!");
         this.lKeys123.add("?");
      }

      for (int i = 0; i < 10; i++) {
         menuElements.add(
            new Button_Keyboard(
               this.lKeys.get(i),
               (CFG.GAME_WIDTH - CFG.PADDING * 11) / 10 * i + CFG.PADDING * i,
               CFG.PADDING * 2 + CFG.PADDING * 2 + (int)(CFG.BUTTON_HEIGHT * 0.8F),
               (CFG.GAME_WIDTH - CFG.PADDING * 11) / 10,
               CFG.BUTTON_HEIGHT,
               Button.TypeOfButton.KEYBOARD,
               true
            )
         );
      }

      for (int var3 = 0; var3 < 10; var3++) {
         menuElements.get(var3)
            .setPosX(
               menuElements.get(var3).getPosX()
                  + (CFG.GAME_WIDTH - menuElements.get(menuElements.size() - 1).getPosX() - menuElements.get(menuElements.size() - 1).getWidth()) / 2
            );
      }

      for (int var4 = 0; var4 < 9; var4++) {
         menuElements.add(
            new Button_Keyboard(
               this.lKeys.get(var4 + 10),
               menuElements.get(0).getWidth() * var4 + CFG.PADDING * var4,
               CFG.PADDING * 2 + CFG.PADDING * 4 + CFG.BUTTON_HEIGHT + (int)(CFG.BUTTON_HEIGHT * 0.8F),
               menuElements.get(0).getWidth(),
               CFG.BUTTON_HEIGHT,
               Button.TypeOfButton.KEYBOARD,
               true
            )
         );
      }

      for (int var5 = 10; var5 < 19; var5++) {
         menuElements.get(var5)
            .setPosX(
               menuElements.get(var5).getPosX()
                  + (CFG.GAME_WIDTH - menuElements.get(menuElements.size() - 1).getPosX() - menuElements.get(menuElements.size() - 1).getWidth()) / 2
            );
      }

      for (int var6 = 0; var6 < 7; var6++) {
         menuElements.add(
            new Button_Keyboard(
               this.lKeys.get(var6 + 19),
               menuElements.get(0).getWidth() * var6 + CFG.PADDING * var6,
               CFG.PADDING * 2 + CFG.PADDING * 6 + CFG.BUTTON_HEIGHT * 2 + (int)(CFG.BUTTON_HEIGHT * 0.8F),
               menuElements.get(0).getWidth(),
               CFG.BUTTON_HEIGHT,
               Button.TypeOfButton.KEYBOARD,
               true
            )
         );
      }

      for (int var7 = 19; var7 < 26; var7++) {
         menuElements.get(var7)
            .setPosX(
               menuElements.get(var7).getPosX()
                  + (CFG.GAME_WIDTH - menuElements.get(menuElements.size() - 1).getPosX() - menuElements.get(menuElements.size() - 1).getWidth()) / 2
            );
      }

      menuElements.add(
         new Button_Keyboard(
            this.lKeys.get(26),
            CFG.PADDING,
            CFG.PADDING * 2 + CFG.PADDING * 6 + CFG.BUTTON_HEIGHT * 2 + (int)(CFG.BUTTON_HEIGHT * 0.8F),
            menuElements.get(19).getPosX() - CFG.PADDING * 2,
            CFG.BUTTON_HEIGHT,
            Button.TypeOfButton.KEYBOARD_OPTIONS,
            true
         )
      );
      menuElements.add(
         new Button_Keyboard(
            this.lKeys.get(27),
            menuElements.get(25).getPosX() + menuElements.get(25).getWidth() + CFG.PADDING,
            CFG.PADDING * 2 + CFG.PADDING * 6 + CFG.BUTTON_HEIGHT * 2 + (int)(CFG.BUTTON_HEIGHT * 0.8F),
            CFG.GAME_WIDTH - menuElements.get(25).getPosX() - menuElements.get(25).getWidth() - CFG.PADDING * 2,
            CFG.BUTTON_HEIGHT,
            Button.TypeOfButton.KEYBOARD_OPTIONS,
            true
         )
      );
      menuElements.add(
         new Button_Keyboard(
            this.lKeys.get(28),
            CFG.PADDING,
            CFG.PADDING * 2 + CFG.PADDING * 8 + CFG.BUTTON_HEIGHT * 3 + (int)(CFG.BUTTON_HEIGHT * 0.8F),
            menuElements.get(0).getWidth() * 2,
            (int)(CFG.BUTTON_HEIGHT * 0.8F),
            Button.TypeOfButton.KEYBOARD_OPTIONS,
            true
         )
      );
      menuElements.add(
         new Button_Keyboard(
            this.lKeys.get(29),
            CFG.PADDING * 2 + menuElements.get(0).getWidth() * 2,
            CFG.PADDING * 2 + CFG.PADDING * 8 + CFG.BUTTON_HEIGHT * 3 + (int)(CFG.BUTTON_HEIGHT * 0.8F),
            CFG.GAME_WIDTH - menuElements.get(0).getWidth() * 4 - CFG.PADDING * 5,
            (int)(CFG.BUTTON_HEIGHT * 0.8F),
            Button.TypeOfButton.KEYBOARD,
            true
         )
      );
      menuElements.add(
         new Button_Keyboard(
            this.lKeys.get(30),
            CFG.PADDING * 3 + menuElements.get(0).getWidth() * 2 + CFG.GAME_WIDTH - menuElements.get(0).getWidth() * 4 - CFG.PADDING * 5,
            CFG.PADDING * 2 + CFG.PADDING * 8 + CFG.BUTTON_HEIGHT * 3 + (int)(CFG.BUTTON_HEIGHT * 0.8F),
            menuElements.get(0).getWidth(),
            (int)(CFG.BUTTON_HEIGHT * 0.8F),
            Button.TypeOfButton.KEYBOARD_OPTIONS,
            true
         )
      );
      menuElements.add(
         new Button_Keyboard(
            this.lKeys.get(31),
            CFG.PADDING * 4 + menuElements.get(0).getWidth() * 3 + CFG.GAME_WIDTH - menuElements.get(0).getWidth() * 4 - CFG.PADDING * 5,
            CFG.PADDING * 2 + CFG.PADDING * 8 + CFG.BUTTON_HEIGHT * 3 + (int)(CFG.BUTTON_HEIGHT * 0.8F),
            menuElements.get(0).getWidth(),
            (int)(CFG.BUTTON_HEIGHT * 0.8F),
            Button.TypeOfButton.KEYBOARD_OPTIONS,
            true
         )
      );
      menuElements.add(
         new Button_Keyboard(
            null,
            CFG.GAME_WIDTH - menuElements.get(0).getWidth() * 2 - CFG.PADDING,
            CFG.PADDING,
            menuElements.get(0).getWidth() * 2,
            (int)(CFG.BUTTON_HEIGHT * 0.8F),
            Button.TypeOfButton.KEYBOARD_SAVE,
            true
         )
      );
      this.initMenu(
         null,
         0,
         CFG.GAME_HEIGHT - CFG.PADDING * 2 - menuElements.get(menuElements.size() - 2).getPosY() - menuElements.get(menuElements.size() - 2).getHeight(),
         CFG.GAME_WIDTH,
         CFG.PADDING * 2 + menuElements.get(menuElements.size() - 2).getPosY() + menuElements.get(menuElements.size() - 2).getHeight(),
         menuElements,
         false,
         false
      );
      this.updateLanguage();
      CFG.updateKeyboard_Actions();
   }

   @Override
   public void updateLanguage() {
      this.lKeys.set(26, CFG.langManager.get("Shift"));
      this.getMenuElement(26).setText(this.lKeys.get(26));
      this.getMenuElement(32).setText(CFG.langManager.get("Save"));
   }

   @Override
   public final void draw(SpriteBatch oSB, int iTranslateX, boolean sliderMenuIsActive) {
      this.updateChangePosY();
      oSB.setColor(Color.WHITE);
      ImageManager.getImage(Images.new_game_top_edge_line)
         .draw2(
            oSB,
            this.getPosX(),
            this.getPosY() - ImageManager.getImage(Images.new_game_top_edge_line).getHeight() + this.animationChangePosY,
            this.getWidth() - ImageManager.getImage(Images.new_game_top_edge_line).getWidth(),
            this.getHeight()
         );
      ImageManager.getImage(Images.new_game_top_edge_line)
         .draw2(
            oSB,
            this.getPosX() + this.getWidth() - ImageManager.getImage(Images.new_game_top_edge_line).getWidth(),
            this.getPosY() - ImageManager.getImage(Images.new_game_top_edge_line).getHeight() + this.animationChangePosY,
            ImageManager.getImage(Images.new_game_top_edge_line).getWidth(),
            this.getHeight(),
            true,
            false
         );
      oSB.setColor(new Color(0.025F, 0.03F, 0.092F, 0.4F));
      ImageManager.getImage(Images.line_32_off1)
         .draw(
            oSB,
            this.getPosX(),
            this.getPosY()
               - ImageManager.getImage(Images.line_32_off1).getHeight()
               + this.animationChangePosY
               + this.getMenuElement(32).getHeight()
               + CFG.PADDING * 2,
            this.getWidth(),
            this.getHeight() - this.getMenuElement(32).getHeight() - CFG.PADDING * 2
         );
      oSB.setColor(new Color(0.14901961F, 0.1764706F, 0.21568628F, 0.65F));
      ImageManager.getImage(Images.patt)
         .draw2(
            oSB,
            this.getPosX(),
            this.getPosY() - ImageManager.getImage(Images.patt).getHeight() + this.animationChangePosY + this.getMenuElement(32).getHeight() + CFG.PADDING * 2,
            this.getWidth(),
            this.getHeight() - this.getMenuElement(32).getHeight() - CFG.PADDING * 2
         );
      oSB.setColor(Color.WHITE);
      oSB.setColor(new Color(CFG.COLOR_INFO_BOX_GRADIENT.r, CFG.COLOR_INFO_BOX_GRADIENT.g, CFG.COLOR_INFO_BOX_GRADIENT.b, 0.28F));
      ImageManager.getImage(Images.line_32_off1)
         .draw(
            oSB,
            this.getPosX(),
            this.getPosY() + this.animationChangePosY + 2 - ImageManager.getImage(Images.slider_gradient).getHeight(),
            this.getWidth(),
            this.getMenuElement(32).getHeight() + CFG.PADDING * 2 - 4
         );
      oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.425F));
      ImageManager.getImage(Images.gradient)
         .draw(
            oSB,
            this.getPosX() + 2,
            this.getPosY() + this.animationChangePosY + 2 - ImageManager.getImage(Images.gradient).getHeight(),
            this.getWidth() - 4,
            CFG.PADDING * 2
         );
      ImageManager.getImage(Images.gradient)
         .draw(
            oSB,
            this.getPosX() + 2,
            this.getPosY()
               + this.getMenuElement(32).getHeight()
               + CFG.PADDING * 2
               - 4
               - CFG.PADDING * 2
               + this.animationChangePosY
               + 2
               - ImageManager.getImage(Images.gradient).getHeight(),
            this.getWidth() - 4,
            CFG.PADDING * 2,
            false,
            true
         );
      oSB.setColor(new Color(CFG.COLOR_NEW_GAME_EDGE_LINE.r, CFG.COLOR_NEW_GAME_EDGE_LINE.g, CFG.COLOR_NEW_GAME_EDGE_LINE.b, 1.0F));
      ImageManager.getImage(Images.pix255_255_255)
         .draw(
            oSB, this.getPosX(), this.getPosY() + this.animationChangePosY - ImageManager.getImage(Images.pix255_255_255).getHeight() + 1, this.getWidth(), 1
         );
      ImageManager.getImage(Images.pix255_255_255)
         .draw(
            oSB,
            this.getPosX() + 2,
            this.getPosY()
               + this.animationChangePosY
               - ImageManager.getImage(Images.pix255_255_255).getHeight()
               + this.getMenuElement(32).getHeight()
               + CFG.PADDING * 2
               - 2,
            this.getWidth() - 4,
            1
         );
      oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.65F));
      ImageManager.getImage(Images.line_32_off1)
         .draw(oSB, this.getPosX(), this.getPosY() + this.animationChangePosY - ImageManager.getImage(Images.line_32_off1).getHeight() + 1, this.getWidth(), 1);
      oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.75F));
      ImageManager.getImage(Images.line_32_off1)
         .draw(
            oSB,
            this.getPosX(),
            this.getPosY()
               + this.animationChangePosY
               - ImageManager.getImage(Images.line_32_off1).getHeight()
               + this.getMenuElement(32).getHeight()
               + CFG.PADDING * 2
               - 2,
            this.getWidth(),
            1
         );
      oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.4F));
      ImageManager.getImage(Images.line_32_off1)
         .draw(
            oSB,
            this.getPosX() + this.getWidth() / 4,
            this.getPosY()
               + this.animationChangePosY
               - ImageManager.getImage(Images.line_32_off1).getHeight()
               + this.getMenuElement(32).getHeight()
               + CFG.PADDING * 2
               - 2,
            this.getWidth() / 2,
            1
         );
      oSB.setColor(Color.WHITE);
      this.drawMenuElements(oSB, 0, this.animationChangePosY, sliderMenuIsActive);
      CFG.drawText(
         oSB,
         CFG.keyboardMessage,
         CFG.PADDING * 2 + iTranslateX,
         this.getMenuElement(this.getMenuElementsSize() - 1).getPosY()
            + this.getMenuElement(this.getMenuElementsSize() - 1).getHeight() / 2
            - this.iTextHeight / 2
            + this.animationChangePosY
            + this.getPosY(),
         new Color(0.8156863F, 0.67058825F, 0.44313726F, 1.0F)
      );
      if (this.barTime + (this.drawBar ? 700 : 650) < System.currentTimeMillis()) {
         this.drawBar = !this.drawBar;
         this.barTime = System.currentTimeMillis();
         CFG.setRender_3(true);
      }

      if (this.drawBar) {
         CFG.drawText(
            oSB,
            "|",
            CFG.PADDING * 2 + this.iTextWidth + iTranslateX,
            this.getMenuElement(this.getMenuElementsSize() - 1).getPosY()
               + this.getMenuElement(this.getMenuElementsSize() - 1).getHeight() / 2
               - this.iTextHeight / 2
               + this.animationChangePosY
               + this.getPosY(),
            Color.WHITE
         );
      }
   }

   @Override
   public final void actionElement(int iID) {
      switch (iID) {
         case 26:
            this.shiftAction();
            return;
         case 27:
            CFG.keyboardDelete.action();
            break;
         case 28:
            if (activeColor_RGB_ID < 0) {
               numbers = !numbers;
               this.actionClose();
            }

            return;
         case 29:
            if (CFG.keyboardMessage.length() > 0 && CFG.keyboardMessage.charAt(CFG.keyboardMessage.length() - 1) != ' ') {
               CFG.keyboardWrite.action(" ");
            }
            break;
         case 30:
         case 31:
         default:
            if (shift && iID < 26) {
               if (numbers) {
                  if (iID < 10) {
                     this.writeNumber(iID);
                  } else if (activeColor_RGB_ID < 0) {
                     CFG.keyboardWrite.action(this.lKeys123.get(iID - 10));
                  }
               } else if (activeColor_RGB_ID < 0) {
                  CFG.keyboardWrite.action(this.lKeysSHIFT.get(iID));
                  if (shift) {
                     this.shiftAction();
                  }
               }
            } else if (numbers) {
               if (iID < 10) {
                  this.writeNumber(iID);
               } else if (activeColor_RGB_ID < 0) {
                  CFG.keyboardWrite.action(this.lKeys123.get(iID - 10));
               }
            } else if (activeColor_RGB_ID < 0) {
               CFG.keyboardWrite.action(this.lKeys.get(iID));
               if (shift) {
                  this.shiftAction();
               }
            }
            break;
         case 32:
            CFG.keyboardSave.action();
            this.closeMenu();
            activeColor_RGB_ID = -1;
            return;
      }

      CFG.glyphLayout.setText(CFG.fontMain, CFG.keyboardMessage);
      this.iTextWidth = (int)CFG.glyphLayout.width;
      this.iTextHeight = (int)CFG.glyphLayout.height;
      this.barTime = System.currentTimeMillis();
      this.drawBar = true;
   }

   protected final void shiftAction() {
      shift = !shift;
      if (numbers) {
         numbers = false;
         this.actionClose();
      }

      this.getMenuElement(26).setTypeOfButton(shift ? Button.TypeOfButton.KEYBOARD_ACTIVE : Button.TypeOfButton.KEYBOARD_OPTIONS);
      if (shift) {
         for (int i = numbers ? this.lKeysNUM.size() : 0; i < this.lKeysSHIFT.size(); i++) {
            this.getMenuElement(i).setText(this.lKeysSHIFT.get(i));
         }
      } else {
         for (int i = numbers ? this.lKeysNUM.size() : 0; i < this.lKeysSHIFT.size(); i++) {
            this.getMenuElement(i).setText(this.lKeys.get(i));
         }
      }
   }

   private final void writeNumber(int iID) {
      CFG.keyboardWrite.action(this.lKeysNUM.get(iID));
   }

   @Override
   public final void onBackPressed() {
      this.closeMenu();
   }

   @Override
   public void actionClose() {
      this.getMenuElement(28).setTypeOfButton(numbers ? Button.TypeOfButton.KEYBOARD_ACTIVE : Button.TypeOfButton.KEYBOARD_OPTIONS);
      if (numbers) {
         for (int i = 0; i < this.lKeysNUM.size(); i++) {
            this.getMenuElement(i).setText(this.lKeysNUM.get(i));
            this.getMenuElement(i).setTypeOfButton(Button.TypeOfButton.KEYBOARD_NUM);
         }

         int keysNum = this.lKeysNUM.size();

         for (int var3 = 0; var3 < this.lKeys123.size(); var3++) {
            this.getMenuElement(keysNum + var3).setText(this.lKeys123.get(var3));
         }
      } else {
         for (int i = 0; i < this.lKeysNUM.size(); i++) {
            this.getMenuElement(i).setText(shift ? this.lKeysSHIFT.get(i) : this.lKeys.get(i));
            this.getMenuElement(i).setTypeOfButton(Button.TypeOfButton.KEYBOARD);
         }

         for (int var5 = this.lKeysNUM.size(); var5 < this.lKeysNUM.size() + this.lKeys123.size(); var5++) {
            this.getMenuElement(var5).setText(shift ? this.lKeysSHIFT.get(var5) : this.lKeys.get(var5));
            this.getMenuElement(var5).setTypeOfButton(Button.TypeOfButton.KEYBOARD);
         }
      }
   }

   private final void updateChangePosY() {
      switch (this.animationStepID) {
         case 0:
         case 1:
         case 12:
            this.animationChangePosY = (int)(this.animationChangePosY - this.getHeight() * 2.5F / 100.0F * (this.closeMenu ? -1 : 1));
            break;
         case 2:
         case 3:
         case 10:
         case 11:
            this.animationChangePosY = (int)(this.animationChangePosY - this.getHeight() * 5.0F / 100.0F * (this.closeMenu ? -1 : 1));
            break;
         case 4:
         case 5:
         case 8:
         case 9:
            this.animationChangePosY = (int)(this.animationChangePosY - this.getHeight() * 10.0F / 100.0F * (this.closeMenu ? -1 : 1));
            break;
         case 6:
         case 7:
            this.animationChangePosY = (int)(this.animationChangePosY - this.getHeight() * 15.0F / 100.0F * (this.closeMenu ? -1 : 1));
            break;
         case 13:
            this.animationChangePosY = 0;
      }

      if (CFG.iNumOfFPS < 22) {
         this.animationStepID = 13;
         this.animationChangePosY = 0;
      }

      if (this.closeMenu && this.animationStepID == 13) {
         this.animationChangePosY = this.getHeight();
         super.setVisible(false);
      }

      this.animationStepID++;
      CFG.setRender_3(true);
   }

   protected final void closeMenu() {
      this.closeMenu = true;
      this.resetAnimation();
   }

   @Override
   public void onMenuPressed() {
      CFG.glyphLayout.setText(CFG.fontMain, CFG.keyboardMessage);
      this.iTextWidth = (int)CFG.glyphLayout.width;
   }

   @Override
   public final void setVisible(boolean visible) {
      if (visible) {
         CFG.glyphLayout.setText(CFG.fontMain, CFG.keyboardMessage);
         this.iTextWidth = (int)CFG.glyphLayout.width;
         this.iTextHeight = (int)CFG.glyphLayout.height;
         this.barTime = System.currentTimeMillis();
         this.drawBar = true;
         super.setVisible(visible);
      }

      this.closeMenu = !visible;
      this.resetAnimation();
   }

   private final void resetAnimation() {
      this.animationStepID = 0;
      if (!this.closeMenu) {
         this.animationChangePosY = this.getHeight();
      }
   }
}
