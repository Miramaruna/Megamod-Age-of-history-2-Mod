package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.GdxRuntimeException;
import java.util.ArrayList;
import java.util.List;

public class Menu_ChooseScenario_Title extends SliderMenu {
   public static boolean drawBigPreview = false;
   public static boolean drawPreview = false;
   public static int iPreviewScenarioID = 0;
   public static Image previewImage = null;
   public static List<String> sTexts;
   public static final int BIGGER_SCALE = 2;

   public Menu_ChooseScenario_Title() {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      int tempCenterPosition = (CFG.BUTTON_HEIGHT * 3 - CFG.BUTTON_HEIGHT * 2 - CFG.PADDING) / 2;
      int nBUTTON_WIDTH = CFG.isAndroid() && !CFG.LANDSCAPE ? CFG.BUTTON_WIDTH : CFG.BUTTON_WIDTH * 2;
      menuElements.add(new Button_Game(null, -1, CFG.GAME_WIDTH - nBUTTON_WIDTH - CFG.PADDING * 2, CFG.PADDING + tempCenterPosition, nBUTTON_WIDTH, true) {
         @Override
         public Color getColor(boolean isActive) {
            return isActive ? CFG.COLOR_TEXT_CIV_NAME : (this.getClickable() ? CFG.COLOR_TEXT_RANK : new Color(0.674F, 0.09F, 0.066F, 0.5F));
         }
      });
      menuElements.add(
         new Button_Game(
            null, -1, CFG.GAME_WIDTH - nBUTTON_WIDTH - CFG.PADDING * 2, tempCenterPosition + CFG.BUTTON_HEIGHT + CFG.PADDING * 2, nBUTTON_WIDTH, true
         ) {
            @Override
            public Color getColor(boolean isActive) {
               return isActive ? CFG.COLOR_TEXT_CIV_NAME : (this.getClickable() ? CFG.COLOR_TEXT_RANK : new Color(0.674F, 0.09F, 0.066F, 0.5F));
            }
         }
      );
      menuElements.add(new Button_Transparent(CFG.PADDING, CFG.PADDING, CFG.PADDING, CFG.PADDING, true) {
         @Override
         public int getWidth() {
            try {
               return (int)(Menu_ChooseScenario_Title.previewImage.getWidth() * CFG.GUI_SCALE);
            } catch (NullPointerException var2) {
               return super.getWidth();
            }
         }

         @Override
         public int getHeight() {
            try {
               return (int)(Menu_ChooseScenario_Title.previewImage.getHeight() * CFG.GUI_SCALE);
            } catch (NullPointerException var2) {
               return super.getHeight();
            }
         }
      });
      this.initMenu(
         new SliderMenuTitle(null, CFG.BUTTON_HEIGHT * 3 / 4, false, false),
         0,
         CFG.BUTTON_HEIGHT * 3 / 4,
         CFG.GAME_WIDTH,
         CFG.BUTTON_HEIGHT * 3 + CFG.PADDING,
         menuElements
      );
      this.updateLanguage();
   }

   @Override
   public void updateLanguage() {
      this.getMenuElement(0).setText(CFG.langManager.get("LoadScenario"));
      this.getMenuElement(1).setText(CFG.langManager.get("Back"));
      this.getTitle().setText(CFG.langManager.get("ChooseScenario"));
   }

   public static final void loadPreview(int nPreviewID) {
      if (iPreviewScenarioID != nPreviewID) {
         iPreviewScenarioID = nPreviewID;
         loadPreview();
      }
   }

   public static final void loadPreview() {
      disposePreview();

      try {
         previewImage = CFG.game.getGameScenarios().getScenarioIsInternal(iPreviewScenarioID)
            ? new Image(
               new Texture(
                  Gdx.files
                     .internal(
                        "map/"
                           + CFG.map.getFile_ActiveMap_Path()
                           + "scenarios/"
                           + CFG.game.getGameScenarios().getScenarioTag(iPreviewScenarioID)
                           + "/preview.png"
                     )
               ),
               Texture.TextureFilter.Linear
            )
            : new Image(
               new Texture(
                  Gdx.files
                     .local(
                        "map/"
                           + CFG.map.getFile_ActiveMap_Path()
                           + "scenarios/"
                           + CFG.game.getGameScenarios().getScenarioTag(iPreviewScenarioID)
                           + "/preview.png"
                     )
               ),
               Texture.TextureFilter.Linear
            );
      } catch (GdxRuntimeException var1) {
      }

      sTexts = new ArrayList<>();
      sTexts.add(CFG.langManager.get(CFG.game.getGameScenarios().getScenarioName(iPreviewScenarioID)));
      sTexts.add(
         CFG.gameAges.getAge(CFG.game.getGameScenarios().getScenarioAge(iPreviewScenarioID)).getName()
            + " | "
            + CFG.game.getGameScenarios().getScenarioDay(iPreviewScenarioID)
            + " "
            + Game_Calendar.getMonthName(CFG.game.getGameScenarios().getScenarioMonth(iPreviewScenarioID))
            + " "
            + CFG.gameAges.getYear(CFG.game.getGameScenarios().getScenarioYear(iPreviewScenarioID))
      );
      sTexts.add(CFG.langManager.get("Civilizations") + ": " + CFG.game.getGameScenarios().getNumOfCivs(iPreviewScenarioID));
      sTexts.add(CFG.langManager.get("Events") + ": " + CFG.langManager.get("No"));
      sTexts.add(CFG.langManager.get("Author") + ": " + CFG.game.getGameScenarios().getScenarioAuthor(iPreviewScenarioID));
      if (CFG.game.getGameScenarios().getScenarioWiki(iPreviewScenarioID).length() > 0) {
         sTexts.add(CFG.langManager.get("Wiki") + ": " + CFG.game.getGameScenarios().getScenarioWiki(iPreviewScenarioID).replace('_', ' '));
      }

      drawBigPreview = false;
   }

   public static final void disposePreview() {
      if (previewImage != null) {
         previewImage.getTexture().dispose();
         previewImage = null;
         sTexts.clear();
         sTexts = null;
      }
   }

   @Override
   public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      super.beginClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
      oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.65F));
      ImageManager.getImage(Images.slider_gradient)
         .draw2(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() + CFG.PADDING - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY,
            this.getWidth() * 3 / 4,
            this.getHeight() - CFG.PADDING
         );
      ImageManager.getImage(Images.slider_gradient)
         .draw2(
            oSB,
            this.getPosX() + this.getWidth() - this.getWidth() * 3 / 4 + iTranslateX,
            this.getPosY() + CFG.PADDING - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY,
            this.getWidth() * 3 / 4,
            this.getHeight() - CFG.PADDING,
            true,
            false
         );
      oSB.setColor(new Color(0.0F, 0.01F, 0.012F, 0.25F));
      ImageManager.getImage(Images.pix255_255_255)
         .draw2(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() + CFG.PADDING - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY,
            this.getWidth(),
            this.getHeight() - CFG.PADDING
         );
      oSB.setColor(new Color(CFG.COLOR_GRADIENT_DARK_BLUE.r, CFG.COLOR_GRADIENT_DARK_BLUE.g, CFG.COLOR_GRADIENT_DARK_BLUE.b, 0.55F));
      ImageManager.getImage(Images.slider_gradient)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() + 3 + CFG.PADDING - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY,
            this.getWidth(),
            this.getHeight() - CFG.PADDING - 6
         );
      oSB.setColor(new Color(0.0F, 0.01F, 0.012F, 0.3F));
      ImageManager.getImage(Images.patt2)
         .draw2(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() + CFG.PADDING - ImageManager.getImage(Images.patt2).getHeight() + iTranslateY,
            this.getWidth(),
            this.getHeight() - CFG.PADDING
         );
      oSB.setColor(new Color(0.0F, 0.01F, 0.012F, 0.65F));
      ImageManager.getImage(Images.gradient)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() + CFG.PADDING - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY,
            this.getWidth(),
            CFG.PADDING * 3
         );
      ImageManager.getImage(Images.gradient)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() + this.getHeight() - CFG.PADDING * 3 - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY,
            this.getWidth(),
            CFG.PADDING * 3,
            false,
            true
         );
      oSB.setColor(CFG.COLOR_CREATE_NEW_GAME_BOX_PLAYERS);
      ImageManager.getImage(Images.pix255_255_255)
         .draw2(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() + CFG.PADDING - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY,
            this.getWidth(),
            1
         );
      ImageManager.getImage(Images.pix255_255_255)
         .draw2(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() + this.getHeight() - 2 - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY,
            this.getWidth(),
            1
         );
      oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.75F));
      ImageManager.getImage(Images.line_32_off1)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() + CFG.PADDING - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
            this.getWidth(),
            1
         );
      ImageManager.getImage(Images.line_32_off1)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() + this.getHeight() - 2 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
            this.getWidth(),
            1
         );
      oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.6F));
      ImageManager.getImage(Images.pix255_255_255)
         .draw2(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() + CFG.PADDING - ImageManager.getImage(Images.pix255_255_255).getHeight() - 1 + iTranslateY,
            this.getWidth(),
            1
         );
      oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.75F));
      ImageManager.getImage(Images.line_32_vertical)
         .draw(
            oSB,
            this.getPosX() + this.getMenuElement(0).getPosX() - CFG.PADDING * 2 + iTranslateX,
            this.getPosY() + this.getMenuElement(0).getPosY() - ImageManager.getImage(Images.line_32_vertical).getHeight() + iTranslateY,
            1,
            this.getMenuElement(1).getPosY() + this.getMenuElement(1).getHeight() - this.getMenuElement(0).getPosY()
         );
      oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.6F));
      ImageManager.getImage(Images.line_32_vertical)
         .draw(
            oSB,
            this.getPosX() - 1 + this.getMenuElement(0).getPosX() - CFG.PADDING * 2 + iTranslateX,
            this.getPosY() + this.getMenuElement(0).getPosY() - ImageManager.getImage(Images.line_32_vertical).getHeight() + iTranslateY,
            1,
            this.getMenuElement(1).getPosY() + this.getMenuElement(1).getHeight() - this.getMenuElement(0).getPosY()
         );
      ImageManager.getImage(Images.line_32_vertical)
         .draw(
            oSB,
            this.getPosX() + 1 + this.getMenuElement(0).getPosX() - CFG.PADDING * 2 + iTranslateX,
            this.getPosY() + this.getMenuElement(0).getPosY() - ImageManager.getImage(Images.line_32_vertical).getHeight() + iTranslateY,
            1,
            this.getMenuElement(1).getPosY() + this.getMenuElement(1).getHeight() - this.getMenuElement(0).getPosY()
         );
      oSB.setColor(Color.WHITE);
      if (drawPreview && !drawBigPreview) {
         oSB.setColor(Color.BLACK);
         ImageManager.getImage(Images.pix255_255_255)
            .draw(
               oSB,
               this.getPosX() + CFG.PADDING + iTranslateX,
               this.getPosY() + CFG.PADDING * 2 - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY,
               (int)(previewImage.getWidth() * CFG.GUI_SCALE),
               (int)(previewImage.getHeight() * CFG.GUI_SCALE)
            );
         oSB.setColor(Color.WHITE);
         previewImage.draw(
            oSB,
            this.getPosX() + CFG.PADDING + iTranslateX,
            this.getPosY() + CFG.PADDING * 2 - previewImage.getHeight() + iTranslateY,
            (int)(previewImage.getWidth() * CFG.GUI_SCALE),
            (int)(previewImage.getHeight() * CFG.GUI_SCALE)
         );
         CFG.map
            .getMapBG()
            .getMinimap()
            .draw2(
               oSB,
               this.getPosX() + CFG.PADDING + iTranslateX,
               this.getPosY() + CFG.PADDING * 2 - CFG.map.getMapBG().getMinimap().getHeight() + iTranslateY,
               (int)(previewImage.getWidth() * CFG.GUI_SCALE) - CFG.map.getMapBG().getMinimap().getWidth(),
               (int)(previewImage.getHeight() * CFG.GUI_SCALE) - CFG.map.getMapBG().getMinimap().getHeight()
            );
         CFG.map
            .getMapBG()
            .getMinimap()
            .draw2(
               oSB,
               this.getPosX() + CFG.PADDING + (int)(previewImage.getWidth() * CFG.GUI_SCALE) - CFG.map.getMapBG().getMinimap().getWidth() + iTranslateX,
               this.getPosY() + CFG.PADDING * 2 - CFG.map.getMapBG().getMinimap().getHeight() + iTranslateY,
               CFG.map.getMapBG().getMinimap().getWidth(),
               (int)(previewImage.getHeight() * CFG.GUI_SCALE) - CFG.map.getMapBG().getMinimap().getHeight(),
               true
            );
         CFG.map
            .getMapBG()
            .getMinimap()
            .draw2(
               oSB,
               this.getPosX() + CFG.PADDING + iTranslateX,
               this.getPosY()
                  + CFG.PADDING * 2
                  - CFG.map.getMapBG().getMinimap().getHeight()
                  + (int)(previewImage.getHeight() * CFG.GUI_SCALE)
                  - CFG.map.getMapBG().getMinimap().getHeight()
                  + iTranslateY,
               (int)(previewImage.getWidth() * CFG.GUI_SCALE) - CFG.map.getMapBG().getMinimap().getWidth(),
               CFG.map.getMapBG().getMinimap().getHeight(),
               false,
               true
            );
         CFG.map
            .getMapBG()
            .getMinimap()
            .draw2(
               oSB,
               this.getPosX() + CFG.PADDING + (int)(previewImage.getWidth() * CFG.GUI_SCALE) - CFG.map.getMapBG().getMinimap().getWidth() + iTranslateX,
               this.getPosY()
                  + CFG.PADDING * 2
                  - CFG.map.getMapBG().getMinimap().getHeight()
                  + (int)(previewImage.getHeight() * CFG.GUI_SCALE)
                  - CFG.map.getMapBG().getMinimap().getHeight()
                  + iTranslateY,
               CFG.map.getMapBG().getMinimap().getWidth(),
               CFG.map.getMapBG().getMinimap().getHeight(),
               true,
               true
            );
         oSB.setColor(CFG.COLOR_MINIMAP_BORDER);
         CFG.drawRect(
            oSB,
            this.getPosX() + CFG.PADDING + iTranslateX,
            this.getPosY() + CFG.PADDING * 2 - 1 + iTranslateY,
            (int)(previewImage.getWidth() * CFG.GUI_SCALE),
            (int)(previewImage.getHeight() * CFG.GUI_SCALE)
         );
         oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.45F));
         CFG.drawRect(
            oSB,
            this.getPosX() + 1 + CFG.PADDING + iTranslateX,
            this.getPosY() + CFG.PADDING * 2 + iTranslateY,
            (int)(previewImage.getWidth() * CFG.GUI_SCALE) - 2,
            (int)(previewImage.getHeight() * CFG.GUI_SCALE) - 2
         );
         oSB.setColor(Color.WHITE);
         CFG.drawTextWithShadow(
            oSB,
            sTexts.get(0),
            this.getPosX() + CFG.PADDING * 3 + (int)(previewImage.getWidth() * CFG.GUI_SCALE) + iTranslateX,
            this.getPosY() + CFG.PADDING * 4 + iTranslateY,
            CFG.COLOR_TEXT_NUM_OF_PROVINCES
         );
         CFG.fontMain.getData().setScale(0.9F);

         for (int i = 1; i < sTexts.size(); i++) {
            CFG.drawTextWithShadow(
               oSB,
               sTexts.get(i),
               this.getPosX() + CFG.PADDING * 3 + (int)(previewImage.getWidth() * CFG.GUI_SCALE) + iTranslateX,
               this.getPosY() + CFG.PADDING * 6 + ((int)(CFG.TEXT_HEIGHT * 0.9F) + CFG.PADDING) * i + CFG.PADDING * i + iTranslateY,
               CFG.COLOR_TEXT_MODIFIER_NEUTRAL
            );
         }

         CFG.fontMain.getData().setScale(1.0F);
      }

      super.drawMenu(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
      super.endClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
      if (drawPreview && drawBigPreview) {
         oSB.setColor(Color.BLACK);
         ImageManager.getImage(Images.pix255_255_255)
            .draw(
               oSB,
               this.getPosX() + CFG.PADDING + iTranslateX,
               this.getPosY() + CFG.PADDING * 2 - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY,
               (int)(previewImage.getWidth() * 2 * CFG.GUI_SCALE),
               (int)(previewImage.getHeight() * 2 * CFG.GUI_SCALE)
            );
         oSB.setColor(Color.WHITE);
         previewImage.draw(
            oSB,
            this.getPosX() + CFG.PADDING + iTranslateX,
            this.getPosY() + CFG.PADDING * 2 - previewImage.getHeight() + iTranslateY,
            (int)(previewImage.getWidth() * 2 * CFG.GUI_SCALE),
            (int)(previewImage.getHeight() * 2 * CFG.GUI_SCALE)
         );
         CFG.map
            .getMapBG()
            .getMinimap()
            .draw2(
               oSB,
               this.getPosX() + CFG.PADDING + iTranslateX,
               this.getPosY() + CFG.PADDING * 2 - CFG.map.getMapBG().getMinimap().getHeight() + iTranslateY,
               (int)(previewImage.getWidth() * 2 * CFG.GUI_SCALE) - CFG.map.getMapBG().getMinimap().getWidth(),
               (int)(previewImage.getHeight() * 2 * CFG.GUI_SCALE) - CFG.map.getMapBG().getMinimap().getHeight()
            );
         CFG.map
            .getMapBG()
            .getMinimap()
            .draw2(
               oSB,
               this.getPosX() + CFG.PADDING + (int)(previewImage.getWidth() * 2 * CFG.GUI_SCALE) - CFG.map.getMapBG().getMinimap().getWidth() + iTranslateX,
               this.getPosY() + CFG.PADDING * 2 - CFG.map.getMapBG().getMinimap().getHeight() + iTranslateY,
               CFG.map.getMapBG().getMinimap().getWidth(),
               (int)(previewImage.getHeight() * 2 * CFG.GUI_SCALE) - CFG.map.getMapBG().getMinimap().getHeight(),
               true
            );
         CFG.map
            .getMapBG()
            .getMinimap()
            .draw2(
               oSB,
               this.getPosX() + CFG.PADDING + iTranslateX,
               this.getPosY()
                  + CFG.PADDING * 2
                  - CFG.map.getMapBG().getMinimap().getHeight()
                  + (int)(previewImage.getHeight() * 2 * CFG.GUI_SCALE)
                  - CFG.map.getMapBG().getMinimap().getHeight()
                  + iTranslateY,
               (int)(previewImage.getWidth() * 2 * CFG.GUI_SCALE) - CFG.map.getMapBG().getMinimap().getWidth(),
               CFG.map.getMapBG().getMinimap().getHeight(),
               false,
               true
            );
         CFG.map
            .getMapBG()
            .getMinimap()
            .draw2(
               oSB,
               this.getPosX() + CFG.PADDING + (int)(previewImage.getWidth() * 2 * CFG.GUI_SCALE) - CFG.map.getMapBG().getMinimap().getWidth() + iTranslateX,
               this.getPosY()
                  + CFG.PADDING * 2
                  - CFG.map.getMapBG().getMinimap().getHeight()
                  + (int)(previewImage.getHeight() * 2 * CFG.GUI_SCALE)
                  - CFG.map.getMapBG().getMinimap().getHeight()
                  + iTranslateY,
               CFG.map.getMapBG().getMinimap().getWidth(),
               CFG.map.getMapBG().getMinimap().getHeight(),
               true,
               true
            );
         oSB.setColor(CFG.COLOR_MINIMAP_BORDER);
         CFG.drawRect(
            oSB,
            this.getPosX() + CFG.PADDING + iTranslateX,
            this.getPosY() + CFG.PADDING * 2 - 1 + iTranslateY,
            (int)(previewImage.getWidth() * 2 * CFG.GUI_SCALE),
            (int)(previewImage.getHeight() * 2 * CFG.GUI_SCALE)
         );
         oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.45F));
         CFG.drawRect(
            oSB,
            this.getPosX() + 1 + CFG.PADDING + iTranslateX,
            this.getPosY() + CFG.PADDING * 2 + iTranslateY,
            (int)(previewImage.getWidth() * 2 * CFG.GUI_SCALE) - 2,
            (int)(previewImage.getHeight() * 2 * CFG.GUI_SCALE) - 2
         );
         oSB.setColor(Color.WHITE);
      }
   }

   public static final void clickLoadScenario() {
      CFG.game.setActiveProvinceID(-1);
      CFG.viewsManager.disableAllViews();
      CFG.game.setScenarioID(iPreviewScenarioID);
      CFG.game.loadScenario(false);
      CFG.game.initPlayers();
      CFG.menuManager.setViewID(CFG.goToMenu);
      drawPreview = false;
      disposePreview();
      CFG.menuManager.disposeChooseScenarioFlags();
      CFG.menuManager.rebuildCivilizations_Info_Players();
   }

   @Override
   public final void actionElement(int iID) {
      switch (iID) {
         case 0:
            clickLoadScenario();
            break;
         case 1:
            this.onBackPressed();
            drawPreview = false;
            disposePreview();
            CFG.menuManager.disposeChooseScenarioFlags();
            break;
         case 2:
            drawBigPreview = !drawBigPreview;
      }

      Game_Render_Province.updateDrawProvinces();
   }

   @Override
   public final void onBackPressed() {
      CFG.game.setActiveProvinceID(-1);
      CFG.menuManager.setViewID(CFG.backToMenu);
      CFG.menuManager.setBackAnimation(true);
   }

   @Override
   public void setVisible(boolean visible) {
      if (visible) {
         drawPreview = true;
         iPreviewScenarioID = CFG.game.getScenarioID();
         loadPreview();
      } else {
         drawPreview = false;
         disposePreview();
         CFG.menuManager.disposeChooseScenarioFlags();
      }

      drawBigPreview = false;
      super.setVisible(visible);
   }
}
