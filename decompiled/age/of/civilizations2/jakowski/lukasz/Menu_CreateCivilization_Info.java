package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

public class Menu_CreateCivilization_Info extends SliderMenu {
   public String sName;
   public int iSRID = 0;

   public Menu_CreateCivilization_Info() {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      menuElements.add(new Button_Menu("", (int)(50.0F * CFG.GUI_SCALE), 0, CFG.PADDING, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT, true) {
         @Override
         public String getTextToDraw() {
            return Menu_CreateCivilization_Info.this.sName + ": " + super.getTextToDraw();
         }
      });
      menuElements.add(new Button_Menu(null, (int)(50.0F * CFG.GUI_SCALE), 0, CFG.BUTTON_HEIGHT + CFG.PADDING * 2, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT, true));
      menuElements.add(new Button_Menu(null, (int)(50.0F * CFG.GUI_SCALE), 0, CFG.BUTTON_HEIGHT * 2 + CFG.PADDING * 3, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT, true));
      menuElements.add(new Button_Menu("<<", -1, 0, CFG.BUTTON_HEIGHT * 3 + CFG.PADDING * 4, CFG.BUTTON_WIDTH * 2, CFG.BUTTON_HEIGHT, true));
      menuElements.add(
         new Button_Menu_Classic(
            "", -1, CFG.BUTTON_WIDTH * 2, CFG.BUTTON_HEIGHT * 3 + CFG.PADDING * 4, CFG.GAME_WIDTH - CFG.BUTTON_WIDTH * 4, CFG.BUTTON_HEIGHT, true
         )
      );
      menuElements.add(
         new Button_Menu_ReflectedBG(
            ">>", -1, CFG.GAME_WIDTH - CFG.BUTTON_WIDTH * 2, CFG.BUTTON_HEIGHT * 3 + CFG.PADDING * 4, CFG.BUTTON_WIDTH * 2, CFG.BUTTON_HEIGHT, true
         )
      );
      this.iSRID = CFG.serviceRibbon_Manager.getSRID(CFG.editorCivilization_GameData.sr_GameData.getSRTAG());
      int tempSRColorsSize = CFG.serviceRibbon_Manager.getSR(CFG.editorCivilization_GameData.sr_GameData.getSRTAG()).getSize();

      for (int i = 0; i < tempSRColorsSize; i++) {
         menuElements.add(
            new Button_Menu(
               CFG.langManager.get("ServiceRibbon") + " - " + CFG.langManager.get("Color") + ": " + (i + 1),
               -1,
               0,
               CFG.BUTTON_HEIGHT * (4 + i) + CFG.PADDING * (5 + i),
               CFG.GAME_WIDTH,
               CFG.BUTTON_HEIGHT,
               true
            ) {
               int iCurrent;

               @Override
               public void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                  super.drawText(oSB, iTranslateX, iTranslateY, isActive);
                  oSB.setColor(
                     CFG.editorCivilization_GameData.sr_GameData.getColor(this.iCurrent).getR(),
                     CFG.editorCivilization_GameData.sr_GameData.getColor(this.iCurrent).getG(),
                     CFG.editorCivilization_GameData.sr_GameData.getColor(this.iCurrent).getB(),
                     1.0F
                  );
                  ImageManager.getImage(Images.pix255_255_255)
                     .draw(
                        oSB,
                        this.getWidth() / 2 - this.getTextWidth() / 2 + iTranslateX,
                        this.getPosY()
                           + Menu_CreateCivilization_Info.this.getMenuPosY()
                           + this.getHeight() / 2
                           + this.getTextHeight() / 2
                           + CFG.CIV_COLOR_WIDTH,
                        this.getTextWidth(),
                        CFG.CIV_COLOR_WIDTH
                     );
                  oSB.setColor(Color.WHITE);
               }

               @Override
               public void setCurrent(int nCurrent) {
                  this.iCurrent = nCurrent;
               }
            }
         );
         menuElements.get(menuElements.size() - 1).setCurrent(i);
      }

      for (int var5 = CFG.editorCivilization_GameData.sr_GameData.getColors().size(); var5 < tempSRColorsSize; var5++) {
         if (var5 == 0) {
            CFG.editorCivilization_GameData.sr_GameData.getColors().add(new Color_GameData(0.9843137F, 0.015686275F, 0.0F));
         } else if (var5 == 1) {
            CFG.editorCivilization_GameData.sr_GameData.getColors().add(new Color_GameData(1.0F, 1.0F, 1.0F));
         } else if (var5 == 2) {
            CFG.editorCivilization_GameData.sr_GameData.getColors().add(new Color_GameData(0.15294118F, 0.3019608F, 0.60784316F));
         } else if (var5 == 3) {
            CFG.editorCivilization_GameData.sr_GameData.getColors().add(new Color_GameData(0.08627451F, 0.14901961F, 0.4509804F));
         } else {
            Color tempColor = CFG.getRandomColor();
            CFG.editorCivilization_GameData.sr_GameData.getColors().add(new Color_GameData(tempColor.r, tempColor.g, tempColor.b));
         }
      }

      this.initMenu(
         null, CFG.PADDING, CFG.BUTTON_HEIGHT + CFG.PADDING * 2, CFG.GAME_WIDTH, CFG.GAME_HEIGHT - (CFG.BUTTON_HEIGHT + CFG.PADDING * 2), menuElements
      );
      this.updateLanguage();
   }

   @Override
   public void updateLanguage() {
      this.sName = CFG.langManager.get("CivilizationName");
      this.getMenuElement(1).setText(CFG.langManager.get("Flag"));
      this.getMenuElement(2).setText(CFG.langManager.get("CivilizationColor"));
   }

   @Override
   public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      super.beginClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
      super.drawMenu(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
      ArrayList<Color> tempColors = new ArrayList<>();

      for (int i = 0; i < CFG.editorCivilization_GameData.sr_GameData.getColors().size(); i++) {
         tempColors.add(
            new Color(
               CFG.editorCivilization_GameData.sr_GameData.getColors().get(i).getR(),
               CFG.editorCivilization_GameData.sr_GameData.getColors().get(i).getG(),
               CFG.editorCivilization_GameData.sr_GameData.getColors().get(i).getB(),
               1.0F
            )
         );
      }

      int tempWidth = CFG.SERVICE_RIBBON_WIDTH * 6 + CFG.PADDING * 5;

      for (int j = 0; j < 6; j++) {
         CFG.serviceRibbon_Manager
            .drawSRLevel(
               oSB,
               CFG.GAME_WIDTH / 2 - tempWidth / 2 + (CFG.SERVICE_RIBBON_WIDTH + CFG.PADDING) * j + iTranslateX,
               this.getMenuElement(4).getPosY() + this.getMenuElement(4).getHeight() / 2 - CFG.SERVICE_RIBBON_HEIGHT / 2 + this.getMenuPosY(),
               j,
               0,
               0,
               this.iSRID,
               tempColors
            );
      }

      super.endClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
   }

   @Override
   public final void actionElement(int iID) {
      switch (iID) {
         case 0:
            CFG.showKeyboard();
            return;
      }
   }
}
