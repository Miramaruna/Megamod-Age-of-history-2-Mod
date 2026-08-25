package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

public class Button_View_Population extends Button {
   public static final float FONT_SIZE = 0.65F;
   public static final float FONT_SIZE2 = 0.6F;
   public boolean row = false;
   public int iProvinceID = 0;
   public String sPopulation;
   public int iPopulationWidth = 0;
   public String sPopulationPerc;
   public int iPopulationPercWidth = 0;
   public int iLargestNationality = 0;
   public boolean isAssimiliate = false;
   public String sLevel = "";
   public int iLevelWidth = 0;

   public Button_View_Population(int iRow, String sText, int nProvinceID, int totalPop, int iPosX, int iPosY, int iWidth, boolean isAssimiliate) {
      super.init(sText, 0, iPosX, iPosY, iWidth, Menu_InGame_View_Army.getButtonHeight(), true, true, false, false);
      this.row = iRow % 2 == 0;
      this.iProvinceID = nProvinceID;

      for (int i = 1; i < CFG.game.getProvince(this.iProvinceID).getPopulationData().getNationalitiesSize(); i++) {
         if (CFG.game.getProvince(this.iProvinceID).getPopulationData().getPopulationID(this.iLargestNationality)
            < CFG.game.getProvince(this.iProvinceID).getPopulationData().getPopulationID(i)) {
            this.iLargestNationality = i;
         }
      }

      this.iLargestNationality = CFG.game.getProvince(this.iProvinceID).getPopulationData().getCivID(this.iLargestNationality);
      this.sPopulation = "" + CFG.getNumberWithSpaces("" + CFG.game.getProvince(this.iProvinceID).getPopulationData().getPopulation());
      CFG.glyphLayout.setText(CFG.fontMain, "" + this.sPopulation);
      this.iPopulationWidth = (int)(CFG.glyphLayout.width * 0.65F);
      this.sPopulationPerc = "" + (int)((float)CFG.game.getProvince(this.iProvinceID).getPopulationData().getPopulation() / totalPop * 10000.0F) / 100.0F + "%";
      CFG.glyphLayout.setText(CFG.fontMain, this.sPopulationPerc);
      this.iPopulationPercWidth = (int)(CFG.glyphLayout.width * 0.6F);
      this.isAssimiliate = isAssimiliate;
      if (CFG.game.getProvince(nProvinceID).getLevelOfLibrary() > 0) {
         this.sLevel = "" + CFG.game.getProvince(nProvinceID).getLevelOfLibrary();
         CFG.glyphLayout.setText(CFG.fontMain, this.sLevel);
         this.iLevelWidth = (int)(CFG.glyphLayout.width * 0.6F);
      }
   }

   @Override
   public void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
      if (this.row) {
         oSB.setColor(new Color(CFG.COLOR_GRADIENT_TITLE_BLUE.r, CFG.COLOR_GRADIENT_TITLE_BLUE.g, CFG.COLOR_GRADIENT_TITLE_BLUE.b, 0.1F));
         ImageManager.getImage(Images.pix255_255_255)
            .draw(
               oSB,
               this.getPosX() + iTranslateX,
               this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY,
               this.getWidth(),
               this.getHeight()
            );
         if (isActive || this.getIsHovered()) {
            oSB.setColor(new Color(CFG.COLOR_GRADIENT_DIPLOMACY.r, CFG.COLOR_GRADIENT_DIPLOMACY.g, CFG.COLOR_GRADIENT_DIPLOMACY.b, 0.65F));
            ImageManager.getImage(Images.line_32_off1)
               .draw(
                  oSB,
                  this.getPosX() + iTranslateX,
                  this.getPosY() + 1 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
                  this.getWidth(),
                  this.getHeight() - 2,
                  true,
                  false
               );
         }

         oSB.setColor(new Color(CFG.COLOR_INFO_BOX_GRADIENT.r, CFG.COLOR_INFO_BOX_GRADIENT.g, CFG.COLOR_INFO_BOX_GRADIENT.b, 0.275F));
         ImageManager.getImage(Images.slider_gradient)
            .draw(
               oSB,
               this.getPosX() + iTranslateX,
               this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY,
               this.getWidth() / 2,
               this.getHeight()
            );
         ImageManager.getImage(Images.slider_gradient)
            .draw(
               oSB,
               this.getPosX() + this.getWidth() - this.getWidth() / 2 + iTranslateX,
               this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY,
               this.getWidth() / 2,
               this.getHeight(),
               true,
               false
            );
         oSB.setColor(new Color(CFG.COLOR_INFO_BOX_GRADIENT.r, CFG.COLOR_INFO_BOX_GRADIENT.g, CFG.COLOR_INFO_BOX_GRADIENT.b, 0.35F));
         ImageManager.getImage(Images.gradient)
            .draw(
               oSB,
               this.getPosX() + iTranslateX,
               this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY,
               this.getWidth(),
               this.getHeight() / 4
            );
         ImageManager.getImage(Images.gradient)
            .draw(
               oSB,
               this.getPosX() + iTranslateX,
               this.getPosY() + this.getHeight() - this.getHeight() / 4 - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY,
               this.getWidth(),
               this.getHeight() / 4,
               false,
               true
            );
         oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.55F));
         ImageManager.getImage(Images.pix255_255_255)
            .draw(
               oSB,
               this.getPosX() + iTranslateX,
               this.getPosY() + this.getHeight() - 1 - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY,
               this.getWidth(),
               1
            );
         ImageManager.getImage(Images.pix255_255_255)
            .draw(
               oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY, this.getWidth(), 1
            );
         oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.4F));
         ImageManager.getImage(Images.line_32_off1)
            .draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth(), 1);
         ImageManager.getImage(Images.line_32_off1)
            .draw(
               oSB,
               this.getPosX() + iTranslateX,
               this.getPosY() + this.getHeight() - 1 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
               this.getWidth(),
               1
            );
      } else {
         oSB.setColor(new Color(CFG.COLOR_GRADIENT_DIPLOMACY.r, CFG.COLOR_GRADIENT_DIPLOMACY.g, CFG.COLOR_GRADIENT_DIPLOMACY.b, 0.6F));
         ImageManager.getImage(Images.slider_gradient)
            .draw(
               oSB,
               this.getPosX() + iTranslateX,
               this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY,
               this.getWidth() / 2,
               this.getHeight()
            );
         ImageManager.getImage(Images.slider_gradient)
            .draw(
               oSB,
               this.getPosX() + this.getWidth() - this.getWidth() / 2 + iTranslateX,
               this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY,
               this.getWidth() / 2,
               this.getHeight(),
               true,
               false
            );
         if (isActive || this.getIsHovered()) {
            oSB.setColor(new Color(CFG.COLOR_GRADIENT_DIPLOMACY.r, CFG.COLOR_GRADIENT_DIPLOMACY.g, CFG.COLOR_GRADIENT_DIPLOMACY.b, 0.45F));
            ImageManager.getImage(Images.line_32_off1)
               .draw(
                  oSB,
                  this.getPosX() + iTranslateX,
                  this.getPosY() + 1 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
                  this.getWidth(),
                  this.getHeight() - 2,
                  true,
                  false
               );
         }

         oSB.setColor(new Color(CFG.COLOR_GRADIENT_DARK_BLUE.r, CFG.COLOR_GRADIENT_DARK_BLUE.g, CFG.COLOR_GRADIENT_DARK_BLUE.b, 0.45F));
         ImageManager.getImage(Images.gradient)
            .draw(
               oSB,
               this.getPosX() + iTranslateX,
               this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY,
               this.getWidth(),
               this.getHeight() / 4
            );
         ImageManager.getImage(Images.gradient)
            .draw(
               oSB,
               this.getPosX() + iTranslateX,
               this.getPosY() + this.getHeight() - this.getHeight() / 4 - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY,
               this.getWidth(),
               this.getHeight() / 4,
               false,
               true
            );
         oSB.setColor(new Color(CFG.COLOR_GRADIENT_DIPLOMACY.r, CFG.COLOR_GRADIENT_DIPLOMACY.g, CFG.COLOR_GRADIENT_DIPLOMACY.b, 0.85F));
         ImageManager.getImage(Images.line_32_off1)
            .draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth(), 1);
         ImageManager.getImage(Images.line_32_off1)
            .draw(
               oSB,
               this.getPosX() + iTranslateX,
               this.getPosY() + this.getHeight() - 1 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
               this.getWidth(),
               1
            );
      }

      if (this.isAssimiliate) {
         oSB.setColor(new Color(1.0F, 1.0F, 1.0F, 0.525F));
         ImageManager.getImage(Images.patt)
            .draw2(
               oSB,
               this.getPosX() + iTranslateX,
               this.getPosY() - ImageManager.getImage(Images.patt).getHeight() + iTranslateY,
               this.getWidth(),
               this.getHeight()
            );
      }

      if (this.iProvinceID == CFG.game.getActiveProvinceID()) {
         oSB.setColor(new Color(CFG.COLOR_GRADIENT_TITLE_BLUE.r, CFG.COLOR_GRADIENT_TITLE_BLUE.g, CFG.COLOR_GRADIENT_TITLE_BLUE.b, 0.825F));
         ImageManager.getImage(Images.slider_gradient)
            .draw(
               oSB,
               this.getPosX() + iTranslateX,
               this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY,
               this.getWidth(),
               this.getHeight()
            );
      }

      if (CFG.game.getProvince(this.iProvinceID).getLevelOfLibrary() > 0) {
         oSB.setColor(Color.WHITE);
         ImageManager.getImage(Images.b_library)
            .draw(
               oSB,
               this.getPosX()
                  + this.getWidth()
                  - CFG.PADDING * 2
                  - this.iPopulationPercWidth
                  - (int)(ImageManager.getImage(Images.b_library).getWidth() * this.getImageScale2(ImageManager.getImage(Images.b_library).getHeight()))
                  + iTranslateX,
               this.getPosY()
                  + this.getHeight() / 2
                  - (int)(ImageManager.getImage(Images.b_library).getHeight() * this.getImageScale2(ImageManager.getImage(Images.b_library).getHeight())) / 2
                  + iTranslateY
                  - ImageManager.getImage(Images.b_library).getHeight(),
               (int)(ImageManager.getImage(Images.b_library).getWidth() * this.getImageScale2(ImageManager.getImage(Images.b_library).getHeight())),
               (int)(ImageManager.getImage(Images.b_library).getHeight() * this.getImageScale2(ImageManager.getImage(Images.b_library).getHeight()))
            );
         CFG.fontMain.getData().setScale(0.6F);
         CFG.drawTextWithShadow(
            oSB,
            this.sLevel,
            this.getPosX()
               + this.getWidth()
               - CFG.PADDING * 2
               - this.iPopulationPercWidth
               - (int)(ImageManager.getImage(Images.b_library).getWidth() * this.getImageScale2(ImageManager.getImage(Images.b_library).getHeight()))
               - CFG.PADDING
               - this.iLevelWidth
               + iTranslateX,
            this.getPosY() + this.getHeight() / 2 - (int)(this.getTextHeight() * 0.6F / 2.0F) + iTranslateY,
            CFG.COLOR_TEXT_NUM_OF_PROVINCES
         );
         CFG.fontMain.getData().setScale(1.0F);
      }

      oSB.setColor(Color.WHITE);
   }

   @Override
   public void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
      CFG.game
         .getCiv(this.iLargestNationality)
         .getFlag()
         .draw(
            oSB,
            this.getPosX() + CFG.PADDING + iTranslateX,
            this.getPosY()
               + this.getHeight() / 2
               - (int)(ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(ImageManager.getImage(Images.flag_rect).getHeight())) / 2
               - CFG.game.getCiv(this.iLargestNationality).getFlag().getHeight()
               + iTranslateY,
            (int)(ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(ImageManager.getImage(Images.flag_rect).getHeight())),
            (int)(ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(ImageManager.getImage(Images.flag_rect).getHeight()))
         );
      ImageManager.getImage(Images.flag_rect)
         .draw(
            oSB,
            this.getPosX() + CFG.PADDING + iTranslateX,
            this.getPosY()
               + this.getHeight() / 2
               - (int)(ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(ImageManager.getImage(Images.flag_rect).getHeight())) / 2
               + iTranslateY
               - ImageManager.getImage(Images.flag_rect).getHeight(),
            (int)(ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(ImageManager.getImage(Images.flag_rect).getHeight())),
            (int)(ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(ImageManager.getImage(Images.flag_rect).getHeight()))
         );
      CFG.fontMain.getData().setScale(0.65F);
      CFG.drawText(
         oSB,
         this.getText(),
         this.getPosX()
            + (int)(ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(ImageManager.getImage(Images.flag_rect).getHeight()))
            + CFG.PADDING * 2
            + iTranslateX,
         this.getPosY() + this.getHeight() / 2 - (int)(this.getTextHeight() * 0.65F / 2.0F) + iTranslateY,
         this.getColor(isActive)
      );
      CFG.drawText(
         oSB,
         "" + this.sPopulation,
         this.getPosX()
            + (int)(ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(ImageManager.getImage(Images.flag_rect).getHeight()))
            + CFG.PADDING * 2
            + (int)(this.getTextWidth() * 0.65F)
            + iTranslateX,
         this.getPosY() + this.getHeight() / 2 - (int)(this.getTextHeight() * 0.65F / 2.0F) + iTranslateY,
         CFG.COLOR_TEXT_POPULATION
      );
      CFG.fontMain.getData().setScale(0.6F);
      CFG.drawText(
         oSB,
         this.sPopulationPerc,
         this.getPosX() + this.getWidth() - CFG.PADDING - this.iPopulationPercWidth + iTranslateX,
         this.getPosY() + this.getHeight() / 2 - (int)(this.getTextHeight() * 0.6F / 2.0F) + iTranslateY,
         CFG.COLOR_TEXT_OPTIONS_NS_ACTIVE
      );
      CFG.fontMain.getData().setScale(1.0F);
   }

   @Override
   public Color getColor(boolean isActive) {
      return isActive
         ? CFG.COLOR_TEXT_OPTIONS_NS_ACTIVE
         : (
            this.getClickable()
               ? (this.getIsHovered() ? CFG.COLOR_TEXT_OPTIONS_NS_HOVER : CFG.COLOR_TEXT_OPTIONS_NS)
               : new Color(CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.r, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.g, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.b, 0.6F)
         );
   }

   @Override
   public int getCurrent() {
      return this.iProvinceID;
   }

   public final float getImageScale(int nHeight) {
      return (float)CFG.TEXT_HEIGHT / nHeight;
   }

   public final float getImageScale2(int nHeight) {
      return (float)CFG.TEXT_HEIGHT / nHeight;
   }

   @Override
   public void buildElementHover() {
      try {
         ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
         ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
         if (this.iProvinceID < 0) {
            this.menuElementHover = null;
            return;
         }

         nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getProvince(this.iProvinceID).getCivID()));
         nData.add(new MenuElement_Hover_v2_Element_Type_Text(this.getText(), CFG.COLOR_TEXT_NUM_OF_PROVINCES));
         nData.add(
            new MenuElement_Hover_v2_Element_Type_Text(
               CFG.getNumberWithSpaces("" + CFG.game.getProvince(this.iProvinceID).getPopulationData().getPopulation()), CFG.COLOR_TEXT_POPULATION
            )
         );
         if (CFG.game.showTurnChangesInformation(CFG.game.getProvince(this.iProvinceID).getCivID())) {
            nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.population, CFG.PADDING, CFG.PADDING));
            if (CFG.game.getProvince(this.iProvinceID).saveProvinceData.turnChange_Population > 0) {
               nData.add(
                  new MenuElement_Hover_v2_Element_Type_Text(
                     "+" + CFG.getNumberWithSpaces("" + CFG.game.getProvince(this.iProvinceID).saveProvinceData.turnChange_Population),
                     CFG.COLOR_TEXT_MODIFIER_POSITIVE
                  )
               );
            } else if (CFG.game.getProvince(this.iProvinceID).saveProvinceData.turnChange_Population < 0) {
               nData.add(
                  new MenuElement_Hover_v2_Element_Type_Text(
                     CFG.getNumberWithSpaces("" + CFG.game.getProvince(this.iProvinceID).saveProvinceData.turnChange_Population),
                     CFG.COLOR_TEXT_MODIFIER_NEGATIVE2
                  )
               );
            } else {
               nData.add(
                  new MenuElement_Hover_v2_Element_Type_Text(
                     "+" + CFG.game.getProvince(this.iProvinceID).saveProvinceData.turnChange_Population, CFG.COLOR_TEXT_MODIFIER_NEUTRAL
                  )
               );
            }

            nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.time, CFG.PADDING, 0));
         } else {
            nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.population, CFG.PADDING, 0));
         }

         nElements.add(new MenuElement_Hover_v2_Element2(nData));
         nData.clear();
         nData.add(new MenuElement_Hover_v2_Element_Type_Space());
         nElements.add(new MenuElement_Hover_v2_Element2(nData));
         nData.clear();
         ArrayList<Integer> tSortedCivs = new ArrayList<>();
         ArrayList<Integer> tSortedPop = new ArrayList<>();

         for (int i = 0; i < CFG.game.getProvince(this.iProvinceID).getPopulationData().getNationalitiesSize(); i++) {
            tSortedCivs.add(CFG.game.getProvince(this.iProvinceID).getPopulationData().getCivID(i));
            tSortedPop.add(CFG.game.getProvince(this.iProvinceID).getPopulationData().getPopulationID(i));
         }

         for (int var9 = 0; var9 < tSortedCivs.size() - 1; var9++) {
            for (int j = var9 + 1; j < tSortedCivs.size(); j++) {
               if (tSortedPop.get(var9) < tSortedPop.get(j)) {
                  int tempD = tSortedCivs.get(var9);
                  tSortedCivs.set(var9, tSortedCivs.get(j));
                  tSortedCivs.set(j, tempD);
                  tempD = tSortedPop.get(var9);
                  tSortedPop.set(var9, tSortedPop.get(j));
                  tSortedPop.set(j, tempD);
               }
            }
         }

         if (CFG.FOG_OF_WAR == 2) {
            for (int var11 = 0; var11 < tSortedCivs.size(); var11++) {
               nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.getMetCiv(tSortedCivs.get(var11)) ? tSortedCivs.get(var11) : -(var11 + 1)));
               nData.add(
                  new MenuElement_Hover_v2_Element_Type_Text("" + CFG.getNumberWithSpaces("" + tSortedPop.get(var11)), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE)
               );
               nData.add(
                  new MenuElement_Hover_v2_Element_Type_Text(
                     " [" + CFG.getPercentage(tSortedPop.get(var11), CFG.game.getProvince(this.iProvinceID).getPopulationData().getPopulation(), 5) + "%]",
                     CFG.COLOR_TEXT_MODIFIER_NEUTRAL
                  )
               );
               nData.add(
                  new MenuElement_Hover_v2_Element_Type_Text(
                     " " + (CFG.getMetCiv(tSortedCivs.get(var11)) ? CFG.game.getCiv(tSortedCivs.get(var11)).getCivName() : CFG.langManager.get("Unknown")),
                     Color.WHITE
                  )
               );
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
            }
         } else {
            for (int var10 = 0; var10 < tSortedCivs.size(); var10++) {
               nData.add(new MenuElement_Hover_v2_Element_Type_Flag(tSortedCivs.get(var10)));
               nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + CFG.getNumberWithSpaces("" + tSortedPop.get(var10)), CFG.COLOR_TEXT_POPULATION));
               nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.population, CFG.PADDING, CFG.PADDING));
               nData.add(
                  new MenuElement_Hover_v2_Element_Type_Text(
                     "[" + CFG.getPercentage(tSortedPop.get(var10), CFG.game.getProvince(this.iProvinceID).getPopulationData().getPopulation(), 5) + "%]",
                     CFG.COLOR_TEXT_MODIFIER_NEUTRAL
                  )
               );
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(" " + CFG.game.getCiv(tSortedCivs.get(var10)).getCivName(), CFG.COLOR_TEXT_RANK_HOVER));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
            }
         }

         this.menuElementHover = new MenuElement_Hover_v2(nElements);
      } catch (IndexOutOfBoundsException var8) {
         this.menuElementHover = null;
      }
   }
}
