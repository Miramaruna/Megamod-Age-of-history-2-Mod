package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

public class Menu_ManageDiplomacy_Guarantee_List extends SliderMenu {
   public Menu_ManageDiplomacy_Guarantee_List() {
      int tempW = CFG.CIV_INFO_MENU_WIDTH + CFG.CIV_INFO_MENU_WIDTH / 4;
      int tempElemH = CFG.BUTTON_HEIGHT;
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      if (CFG.MANAGE_DIPLOMACY_CUSTOMIZE_ALLIANCE_ID > 0) {
         int multiplePosY = 0;

         for (int i = 1; i < CFG.game.getCivsSize(); i++) {
            if (i != CFG.MANAGE_DIPLOMACY_CUSTOMIZE_ALLIANCE_ID && CFG.game.getGuarantee(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_ALLIANCE_ID, i) > 0) {
               menuElements.add(
                  new Slider_Guarantee(
                     i,
                     CFG.MANAGE_DIPLOMACY_CUSTOMIZE_ALLIANCE_ID,
                     CFG.PADDING,
                     CFG.PADDING * (multiplePosY + 1) + CFG.BUTTON_HEIGHT * multiplePosY + CFG.PADDING,
                     tempW - CFG.BUTTON_HEIGHT * 3 / 4 - CFG.PADDING * 2,
                     CFG.BUTTON_HEIGHT - CFG.PADDING * 2,
                     1,
                     100,
                     CFG.game.getGuarantee(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_ALLIANCE_ID, i)
                  )
               );
               menuElements.add(
                  new Button_Menu_Remove(
                     tempW - CFG.BUTTON_HEIGHT * 3 / 4 - CFG.PADDING,
                     CFG.PADDING * (multiplePosY + 1) + CFG.BUTTON_HEIGHT * multiplePosY,
                     CFG.BUTTON_HEIGHT * 3 / 4,
                     CFG.BUTTON_HEIGHT,
                     true
                  ) {
                     @Override
                     public void buildElementHover() {
                        ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
                        ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Delete"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                        this.menuElementHover = new MenuElement_Hover_v2(nElements);
                     }
                  }
               );
               multiplePosY++;
            }
         }
      }

      this.initMenu(
         new SliderMenuTitle(null, CFG.BUTTON_HEIGHT * 3 / 4, false, false) {
            @Override
            public void draw(SpriteBatch oSB, int iTranslateX, int nPosX, int nPosY, int nWidth, boolean sliderMenuIsActive) {
               ImageManager.getImage(Images.new_game_top_edge_title)
                  .draw2(
                     oSB,
                     Menu_ManageDiplomacy_Guarantee_List.this.getPosX() + iTranslateX,
                     Menu_ManageDiplomacy_Guarantee_List.this.getPosY() - ImageManager.getImage(Images.new_game_top_edge_title).getHeight() - this.getHeight(),
                     Menu_ManageDiplomacy_Guarantee_List.this.getWidth() + 2,
                     this.getHeight(),
                     true,
                     false
                  );
               oSB.setColor(new Color(0.011F, 0.014F, 0.019F, 0.25F));
               ImageManager.getImage(Images.gradient)
                  .draw(
                     oSB,
                     Menu_ManageDiplomacy_Guarantee_List.this.getPosX() + iTranslateX,
                     Menu_ManageDiplomacy_Guarantee_List.this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() - this.getHeight() * 3 / 4,
                     Menu_ManageDiplomacy_Guarantee_List.this.getWidth(),
                     this.getHeight() * 3 / 4,
                     false,
                     true
                  );
               oSB.setColor(new Color(0.451F, 0.329F, 0.11F, 1.0F));
               ImageManager.getImage(Images.pix255_255_255)
                  .draw(
                     oSB,
                     Menu_ManageDiplomacy_Guarantee_List.this.getPosX() + iTranslateX,
                     Menu_ManageDiplomacy_Guarantee_List.this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight(),
                     Menu_ManageDiplomacy_Guarantee_List.this.getWidth()
                  );
               oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.4F));
               ImageManager.getImage(Images.line_32_off1)
                  .draw(
                     oSB,
                     Menu_ManageDiplomacy_Guarantee_List.this.getPosX() + iTranslateX,
                     Menu_ManageDiplomacy_Guarantee_List.this.getPosY()
                        - ImageManager.getImage(Images.pix255_255_255).getHeight()
                        - ImageManager.getImage(Images.line_32_off1).getHeight(),
                     Menu_ManageDiplomacy_Guarantee_List.this.getWidth(),
                     1
                  );
               oSB.setColor(Color.WHITE);

               try {
                  CFG.game
                     .getCiv(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_ALLIANCE_ID)
                     .getFlag()
                     .draw(
                        oSB,
                        nPosX + CFG.PADDING * 2 + iTranslateX,
                        nPosY
                           - CFG.game.getCiv(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_ALLIANCE_ID).getFlag().getHeight()
                           - this.getHeight()
                           + this.getHeight() / 2
                           + 1
                           - CFG.CIV_FLAG_HEIGHT / 2,
                        CFG.CIV_FLAG_WIDTH,
                        CFG.CIV_FLAG_HEIGHT
                     );
                  ImageManager.getImage(Images.flag_rect)
                     .draw(oSB, nPosX + CFG.PADDING * 2 + iTranslateX, nPosY - this.getHeight() + this.getHeight() / 2 + 1 - CFG.CIV_FLAG_HEIGHT / 2);
               } catch (IndexOutOfBoundsException var8) {
               }

               CFG.fontMain.getData().setScale(0.75F);
               CFG.drawText(
                  oSB,
                  this.getText(),
                  nPosX + nWidth / 2 - (int)(this.getTextWidth() * 0.75F / 2.0F) + iTranslateX,
                  nPosY - this.getHeight() + this.getHeight() / 2 + 1 - (int)(this.getTextHeight() * 0.75F / 2.0F),
                  CFG.COLOR_TEXT_MODIFIER_NEUTRAL
               );
               CFG.fontMain.getData().setScale(1.0F);
            }
         },
         0,
         CFG.BUTTON_HEIGHT * 2 + CFG.PADDING * 4 + CFG.PADDING * 2 + CFG.BUTTON_HEIGHT * 3 / 4,
         tempW,
         Math.min(
            (tempElemH + CFG.PADDING) * (menuElements.size() / 2) + CFG.PADDING,
            CFG.GAME_HEIGHT - (CFG.BUTTON_HEIGHT * 2 + CFG.PADDING * 4 + CFG.PADDING * 2 + CFG.BUTTON_HEIGHT * 3 / 4) - CFG.BUTTON_HEIGHT - CFG.PADDING * 2
         ),
         menuElements,
         false,
         true
      );
      this.updateLanguage();
   }

   @Override
   public void updateLanguage() {
      this.getTitle().setText(CFG.langManager.get("ProclaimsIndependence"));
   }

   @Override
   public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      ImageManager.getImage(Images.new_game_top_edge_line)
         .draw2(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.new_game_top_edge_line).getHeight() + iTranslateY,
            this.getWidth() + 2,
            this.getHeight(),
            true,
            true
         );
      super.draw(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
      oSB.setColor(CFG.COLOR_CREATE_NEW_GAME_BOX_PLAYERS);
      ImageManager.getImage(Images.pix255_255_255)
         .draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + this.getHeight(), this.getWidth());
      oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.4F));
      ImageManager.getImage(Images.line_32_off1)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY()
               - ImageManager.getImage(Images.pix255_255_255).getHeight()
               - ImageManager.getImage(Images.line_32_off1).getHeight()
               + this.getHeight(),
            this.getWidth(),
            1
         );
      oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.5F));
      ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight(), this.getWidth() + 2);
      oSB.setColor(Color.WHITE);
   }

   @Override
   public void drawScrollPos(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      if (sliderMenuIsActive) {
         super.drawScrollPos(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
      }
   }

   @Override
   public void actionElement(int iID) {
      if (iID % 2 == 0) {
         this.updateGuarantee(iID / 2, this.getMenuElement(iID).getCurrent());
      } else if (iID % 2 == 1) {
         this.updateGuarantee(iID / 2, 0);
         CFG.menuManager.rebuildManageDiplomacy_Guarantee_List();
      }
   }

   public final void updateGuarantee(int pactID, int iNumOfTurns) {
      if (CFG.MANAGE_DIPLOMACY_CUSTOMIZE_ALLIANCE_ID > 0) {
         int foundPacts = 0;

         for (int i = 1; i < CFG.game.getCivsSize(); i++) {
            if (i != CFG.MANAGE_DIPLOMACY_CUSTOMIZE_ALLIANCE_ID && CFG.game.getGuarantee(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_ALLIANCE_ID, i) > 0) {
               if (foundPacts == pactID) {
                  CFG.game.setGuarantee(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_ALLIANCE_ID, i, iNumOfTurns);
                  return;
               }

               foundPacts++;
            }
         }
      }
   }
}
