package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.GdxRuntimeException;
import java.util.ArrayList;
import java.util.List;

public class Menu_CreateScenario_Assign_List extends SliderMenu {
   public List<String> lCivsTags = null;
   public List<Image> lFlags = new ArrayList<>();
   public List<Integer> lLoadedFlags_TagsIDs = new ArrayList<>();

   public Menu_CreateScenario_Assign_List() {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      int tPosX = CFG.PADDING;
      this.lCivsTags = new ArrayList<>();

      for (int i = 0; i < CFG.game.getCivsSize(); i++) {
         menuElements.add(
            new Button_EditorFlag(i, tPosX, CFG.PADDING, true) {
               @Override
               public void buildElementHover() {
                  ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
                  ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
                  nData.add(new MenuElement_Hover_v2_Element_Type_Flag(this.getCurrent()));
                  nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.game.getCiv(this.getCurrent()).getCivName(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                  nElements.add(new MenuElement_Hover_v2_Element2(nData));
                  nData.clear();
                  nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Provinces") + ": "));
                  nData.add(
                     new MenuElement_Hover_v2_Element_Type_Text("" + CFG.game.getCiv(this.getCurrent()).getNumOfProvinces(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE)
                  );
                  nElements.add(new MenuElement_Hover_v2_Element2(nData));
                  nData.clear();
                  this.menuElementHover = new MenuElement_Hover_v2(nElements);
               }
            }
         );
         tPosX += ImageManager.getImage(Images.top_flag_frame).getWidth() + CFG.PADDING;
         this.lCivsTags.add(CFG.game.getCiv(i).getCivTag());
      }

      CFG.glyphLayout.setText(CFG.fontMain, CFG.langManager.get("SelectCivilization"));
      int var4 = 0;
      var4 = CFG.glyphLayout.width + CFG.PADDING * 4 > CFG.BUTTON_WIDTH ? (int)(CFG.glyphLayout.width + CFG.PADDING * 4) : CFG.BUTTON_WIDTH + CFG.PADDING * 4;
      menuElements.add(
         new Button_Transparent(
            0,
            0,
            menuElements.get(menuElements.size() - 1).getPosX() + menuElements.get(menuElements.size() - 1).getWidth(),
            CFG.BUTTON_HEIGHT + CFG.PADDING * 2,
            true
         )
      );
      this.initMenu(
         null,
         var4 + CFG.PADDING * 2,
         CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING * 2,
         CFG.GAME_WIDTH - CFG.map.getMapBG().getMinimapWidth() - var4 - CFG.PADDING * 2,
         CFG.BUTTON_HEIGHT + CFG.PADDING * 2,
         menuElements,
         true,
         false
      );
      this.updateLanguage();
   }

   @Override
   public void updateLanguage() {
   }

   @Override
   public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      ImageManager.getImage(Images.editor_line)
         .draw2(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.editor_line).getHeight() + iTranslateY,
            this.getWidth(),
            CFG.BUTTON_HEIGHT + CFG.PADDING * 2
         );
      oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.65F));
      ImageManager.getImage(Images.line_32_off1)
         .draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth(), 1);
      oSB.setColor(Color.WHITE);
      this.beginClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);

      try {
         for (int i = 0; i < this.getMenuElementsSize(); i++) {
            if (this.getMenuElement(i).getIsInView()) {
               try {
                  this.lFlags
                     .get(this.getFlagID(i))
                     .draw(
                        oSB,
                        this.getMenuPosX() + this.getMenuElement(i).getPosX() + iTranslateX,
                        this.getPosY()
                           + CFG.BUTTON_HEIGHT / 2
                           - ImageManager.getImage(Images.top_flag_frame).getHeight() / 2
                           - this.lFlags.get(this.getFlagID(i)).getHeight()
                           + iTranslateY,
                        ImageManager.getImage(Images.top_flag_frame).getWidth(),
                        ImageManager.getImage(Images.top_flag_frame).getHeight()
                     );
               } catch (NullPointerException var7) {
                  if (CFG.game.getCiv(i).getCivTag().equals("ran")) {
                     oSB.setColor(new Color(CFG.game.getCiv(i).getR() / 255.0F, CFG.game.getCiv(i).getG() / 255.0F, CFG.game.getCiv(i).getB() / 255.0F, 1.0F));
                     CFG.game
                        .getCiv(i)
                        .getFlag()
                        .draw(
                           oSB,
                           this.getMenuPosX() + this.getMenuElement(i).getPosX() + iTranslateX,
                           this.getPosY()
                              + CFG.BUTTON_HEIGHT / 2
                              - ImageManager.getImage(Images.top_flag_frame).getHeight() / 2
                              + iTranslateY
                              - CFG.game.getCiv(i).getFlag().getHeight(),
                           ImageManager.getImage(Images.top_flag_frame).getWidth(),
                           ImageManager.getImage(Images.top_flag_frame).getHeight()
                        );
                     oSB.setColor(Color.WHITE);
                  } else {
                     CFG.game
                        .getCiv(i)
                        .getFlag()
                        .draw(
                           oSB,
                           this.getMenuPosX() + this.getMenuElement(i).getPosX() + iTranslateX,
                           this.getPosY()
                              + CFG.BUTTON_HEIGHT / 2
                              - ImageManager.getImage(Images.top_flag_frame).getHeight() / 2
                              + iTranslateY
                              - CFG.game.getCiv(i).getFlag().getHeight(),
                           ImageManager.getImage(Images.top_flag_frame).getWidth(),
                           ImageManager.getImage(Images.top_flag_frame).getHeight()
                        );
                  }

                  if (this.getMenuElement(i).getIsHovered()) {
                     oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.0375F));
                     ImageManager.getImage(Images.pix255_255_255)
                        .draw(
                           oSB,
                           this.getMenuPosX() + this.getMenuElement(i).getPosX() + iTranslateX,
                           this.getPosY()
                              - ImageManager.getImage(Images.pix255_255_255).getHeight()
                              + CFG.BUTTON_HEIGHT / 2
                              - ImageManager.getImage(Images.top_flag_frame).getHeight() / 2
                              + iTranslateY,
                           ImageManager.getImage(Images.top_flag_frame).getWidth(),
                           ImageManager.getImage(Images.top_flag_frame).getHeight()
                        );
                     oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.575F));
                     ImageManager.getImage(Images.gradient)
                        .draw(
                           oSB,
                           this.getMenuPosX() + this.getMenuElement(i).getPosX() + iTranslateX,
                           this.getPosY()
                              - ImageManager.getImage(Images.gradient).getHeight()
                              + CFG.BUTTON_HEIGHT / 2
                              - ImageManager.getImage(Images.top_flag_frame).getHeight() / 2
                              + iTranslateY,
                           ImageManager.getImage(Images.top_flag_frame).getWidth(),
                           ImageManager.getImage(Images.top_flag_frame).getHeight() / 4
                        );
                     ImageManager.getImage(Images.gradient)
                        .draw(
                           oSB,
                           this.getMenuPosX() + this.getMenuElement(i).getPosX() + iTranslateX,
                           this.getPosY()
                              - ImageManager.getImage(Images.gradient).getHeight()
                              + ImageManager.getImage(Images.top_flag_frame).getHeight()
                              - ImageManager.getImage(Images.top_flag_frame).getHeight() / 4
                              + CFG.BUTTON_HEIGHT / 2
                              - ImageManager.getImage(Images.top_flag_frame).getHeight() / 2
                              + iTranslateY,
                           ImageManager.getImage(Images.top_flag_frame).getWidth(),
                           ImageManager.getImage(Images.top_flag_frame).getHeight() / 4,
                           false,
                           true
                        );
                     oSB.setColor(Color.WHITE);
                  }
               }

               if (!this.getMenuElement(i).getIsHovered() && i != CFG.iCreateScenario_AssignProvinces_Civ) {
                  ImageManager.getImage(Images.top_flag_frame)
                     .draw(
                        oSB,
                        this.getMenuPosX() + this.getMenuElement(i).getPosX() + iTranslateX,
                        this.getPosY() + CFG.BUTTON_HEIGHT / 2 - ImageManager.getImage(Images.top_flag_frame).getHeight() / 2 + iTranslateY
                     );
               } else {
                  ImageManager.getImage(Images.top_flag_frame_h)
                     .draw(
                        oSB,
                        this.getMenuPosX() + this.getMenuElement(i).getPosX() + iTranslateX,
                        this.getPosY() + CFG.BUTTON_HEIGHT / 2 - ImageManager.getImage(Images.top_flag_frame).getHeight() / 2 + iTranslateY
                     );
               }

               oSB.setColor(new Color(CFG.game.getCiv(i).getR() / 255.0F, CFG.game.getCiv(i).getG() / 255.0F, CFG.game.getCiv(i).getB() / 255.0F, 1.0F));
               ImageManager.getImage(Images.gradient)
                  .draw(
                     oSB,
                     this.getMenuPosX() + this.getMenuElement(i).getPosX() + iTranslateX,
                     this.getPosY()
                        - ImageManager.getImage(Images.gradient).getHeight()
                        + CFG.BUTTON_HEIGHT / 2
                        + ImageManager.getImage(Images.top_flag_frame).getHeight() / 2
                        + iTranslateY,
                     ImageManager.getImage(Images.top_flag_frame).getWidth(),
                     CFG.CIV_COLOR_WIDTH
                  );
               ImageManager.getImage(Images.line_32_off1)
                  .draw(
                     oSB,
                     this.getMenuPosX() + this.getMenuElement(i).getPosX() + iTranslateX,
                     this.getPosY()
                        - ImageManager.getImage(Images.line_32_off1).getHeight()
                        + CFG.BUTTON_HEIGHT / 2
                        + ImageManager.getImage(Images.top_flag_frame).getHeight() / 2
                        + iTranslateY,
                     ImageManager.getImage(Images.top_flag_frame).getWidth(),
                     1
                  );
               oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.475F));
               ImageManager.getImage(Images.slider_gradient)
                  .draw(
                     oSB,
                     this.getMenuPosX() + this.getMenuElement(i).getPosX() + iTranslateX,
                     this.getPosY()
                        - ImageManager.getImage(Images.slider_gradient).getHeight()
                        + CFG.BUTTON_HEIGHT / 2
                        + ImageManager.getImage(Images.top_flag_frame).getHeight() / 2
                        + iTranslateY,
                     ImageManager.getImage(Images.top_flag_frame).getWidth() / 4,
                     CFG.CIV_COLOR_WIDTH
                  );
               ImageManager.getImage(Images.slider_gradient)
                  .draw(
                     oSB,
                     this.getMenuPosX()
                        + ImageManager.getImage(Images.top_flag_frame).getWidth()
                        - ImageManager.getImage(Images.top_flag_frame).getWidth() / 4
                        + this.getMenuElement(i).getPosX()
                        + iTranslateX,
                     this.getPosY()
                        - ImageManager.getImage(Images.slider_gradient).getHeight()
                        + CFG.BUTTON_HEIGHT / 2
                        + ImageManager.getImage(Images.top_flag_frame).getHeight() / 2
                        + iTranslateY,
                     ImageManager.getImage(Images.top_flag_frame).getWidth() / 4,
                     CFG.CIV_COLOR_WIDTH,
                     true,
                     false
                  );
               oSB.setColor(CFG.COLOR_FLAG_FRAME);
               ImageManager.getImage(Images.gradient)
                  .draw(
                     oSB,
                     this.getMenuPosX() + this.getMenuElement(i).getPosX() + iTranslateX,
                     this.getPosY()
                        - ImageManager.getImage(Images.gradient).getHeight()
                        + CFG.BUTTON_HEIGHT / 2
                        + ImageManager.getImage(Images.top_flag_frame).getHeight() / 2
                        + iTranslateY,
                     1,
                     CFG.CIV_COLOR_WIDTH
                  );
               ImageManager.getImage(Images.gradient)
                  .draw(
                     oSB,
                     this.getMenuPosX() + ImageManager.getImage(Images.top_flag_frame).getWidth() - 1 + this.getMenuElement(i).getPosX() + iTranslateX,
                     this.getPosY()
                        - ImageManager.getImage(Images.gradient).getHeight()
                        + CFG.BUTTON_HEIGHT / 2
                        + ImageManager.getImage(Images.top_flag_frame).getHeight() / 2
                        + iTranslateY,
                     1,
                     CFG.CIV_COLOR_WIDTH
                  );
               oSB.setColor(Color.WHITE);
            }
         }
      } catch (IndexOutOfBoundsException var8) {
      } catch (NullPointerException var9) {
      }

      this.endClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
      oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.6F));
      ImageManager.getImage(Images.slider_gradient)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + 1 + iTranslateY,
            CFG.PADDING * 2,
            this.getHeight() - 1
         );
      ImageManager.getImage(Images.slider_gradient)
         .draw(
            oSB,
            this.getPosX() + this.getWidth() - CFG.PADDING * 2 + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + 1 + iTranslateY,
            CFG.PADDING * 2,
            this.getHeight() - 1,
            true,
            false
         );
      oSB.setColor(CFG.COLOR_MINIMAP_BORDER);
      ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, 1, this.getHeight());
      oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.7F));
      ImageManager.getImage(Images.gradient)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY,
            1,
            this.getHeight() / 2,
            false,
            true
         );
      ImageManager.getImage(Images.gradient)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + this.getHeight() - this.getHeight() / 2 + iTranslateY,
            1,
            this.getHeight() / 2,
            false,
            false
         );
      oSB.setColor(Color.WHITE);
   }

   @Override
   public void updateMenuElements_IsInView() {
      super.updateMenuElements_IsInView();

      for (int i = 0; i < this.getMenuElementsSize() - 1; i++) {
         int tempTagID = this.getIsLoaded(this.lCivsTags.get(i));
         if (this.getMenuElement(i).getIsInView()) {
            if (tempTagID < 0) {
               this.loadFlag(i);
            }
         } else if (tempTagID >= 0) {
            this.lFlags.get(tempTagID).getTexture().dispose();
            this.lFlags.set(tempTagID, null);
            this.lFlags.remove(tempTagID);
            this.lLoadedFlags_TagsIDs.remove(tempTagID);
         }
      }
   }

   public final int getIsLoaded(String nCivTag) {
      for (int i = 0; i < this.lLoadedFlags_TagsIDs.size(); i++) {
         if (this.lCivsTags.get(this.lLoadedFlags_TagsIDs.get(i)).equals(nCivTag)) {
            return i;
         }
      }

      return -1;
   }

   public final int getFlagID(int nCivTagID) {
      for (int i = 0; i < this.lLoadedFlags_TagsIDs.size(); i++) {
         if (this.lLoadedFlags_TagsIDs.get(i) == nCivTagID) {
            return i;
         }
      }

      return 0;
   }

   public final void loadFlag(int nCivTagID) {
      try {
         try {
            this.lFlags.add(new Image(new Texture(Gdx.files.internal("game/flagsH/" + this.lCivsTags.get(nCivTagID) + ".png")), Texture.TextureFilter.Linear));
         } catch (GdxRuntimeException var7) {
            try {
               this.lFlags
                  .add(
                     new Image(
                        new Texture(Gdx.files.internal("game/flagsH/" + CFG.ideologiesManager.getRealTag(this.lCivsTags.get(nCivTagID)) + ".png")),
                        Texture.TextureFilter.Linear
                     )
                  );
            } catch (GdxRuntimeException var6) {
               if (CFG.isAndroid()) {
                  try {
                     this.lFlags
                        .add(
                           new Image(
                              new Texture(
                                 Gdx.files
                                    .local(
                                       "game/civilizations_editor/"
                                          + CFG.ideologiesManager.getRealTag(this.lCivsTags.get(nCivTagID))
                                          + "/"
                                          + CFG.ideologiesManager.getRealTag(this.lCivsTags.get(nCivTagID))
                                          + "_FLH.png"
                                    )
                              ),
                              Texture.TextureFilter.Linear
                           )
                        );
                  } catch (GdxRuntimeException var5) {
                     this.lFlags
                        .add(
                           new Image(
                              new Texture(
                                 Gdx.files
                                    .internal(
                                       "game/civilizations_editor/"
                                          + CFG.ideologiesManager.getRealTag(this.lCivsTags.get(nCivTagID))
                                          + "/"
                                          + CFG.ideologiesManager.getRealTag(this.lCivsTags.get(nCivTagID))
                                          + "_FLH.png"
                                    )
                              ),
                              Texture.TextureFilter.Linear
                           )
                        );
                  }
               } else {
                  this.lFlags
                     .add(
                        new Image(
                           new Texture(
                              Gdx.files
                                 .internal(
                                    "game/civilizations_editor/"
                                       + CFG.ideologiesManager.getRealTag(this.lCivsTags.get(nCivTagID))
                                       + "/"
                                       + CFG.ideologiesManager.getRealTag(this.lCivsTags.get(nCivTagID))
                                       + "_FLH.png"
                                 )
                           ),
                           Texture.TextureFilter.Linear
                        )
                     );
               }
            }
         }
      } catch (GdxRuntimeException var8) {
         this.lFlags.add(null);
      } catch (OutOfMemoryError var9) {
         this.lFlags.add(null);
      }

      this.lLoadedFlags_TagsIDs.add(nCivTagID);
   }

   @Override
   public final void actionElement(int iID) {
      if (iID != this.getMenuElementsSize() - 1) {
         if (CFG.iCreateScenario_AssignProvinces_Civ != this.getMenuElement(iID).getCurrent()) {
            CFG.game.disableDrawCivilizationRegions(CFG.iCreateScenario_AssignProvinces_Civ);
            CFG.game.enableDrawCivilizationRegions(this.getMenuElement(iID).getCurrent(), 0);
         } else if (this.getMenuElement(iID).getCurrent() > 0) {
            CFG.map.getMapCoordinates().centerToProvinceID(CFG.game.getCiv(this.getMenuElement(iID).getCurrent()).getCapitalProvinceID());
         }

         CFG.iCreateScenario_AssignProvinces_Civ = this.getMenuElement(iID).getCurrent();
      }
   }

   @Override
   public void onBackPressed() {
      try {
         for (int i = 0; i < this.lFlags.size(); i++) {
            this.lFlags.get(i).getTexture().dispose();
         }

         this.lFlags.clear();
         this.lLoadedFlags_TagsIDs.clear();
         this.lCivsTags.clear();
      } catch (NullPointerException var2) {
      }
   }
}
