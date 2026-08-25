package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

class Menu_PeaceTreaty_Response_Demands extends SliderMenu {
   protected Menu_PeaceTreaty_Response_Demands() {
      int tempW = CFG.CIV_INFO_MENU_WIDTH * 4 / 5;
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      int tY = 0;

      for (int i = 0; i < CFG.peaceTreatyData.peaceTreatyGameData.lCivsDemands_Aggressors.size(); i++) {
         if (CFG.peaceTreatyData.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).lDemands.size() > 0
            || CFG.peaceTreatyData.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).lWarReparationsFromCivsID.size() > 0
            || CFG.peaceTreatyData.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).lReleasableCivs_TakeControl.size() > 0) {
            boolean addCiv = CFG.peaceTreatyData.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).lWarReparationsFromCivsID.size() > 0;

            for (int j = 0; j < CFG.peaceTreatyData.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).lDemands.size(); j++) {
               if (CFG.game.getProvince(CFG.peaceTreatyData.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).lDemands.get(j)).getTrueOwnerOfProvince()
                  != CFG.peaceTreatyData.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).iCivID) {
                  addCiv = true;
                  break;
               }
            }

            if (addCiv || CFG.peaceTreatyData.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).lReleasableCivs_TakeControl.size() > 0) {
               int var10008 = CFG.TEXT_HEIGHT + CFG.PADDING * 4;
               menuElements.add(
                  new Button_PeaceTreaty_Demands_TakeAll(
                     CFG.peaceTreatyData.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).iCivID, 0, tY, tempW, var10008, true
                  ) {
                     @Override
                     public void actionElement(int iID) {
                     }
                  }
               );
               tY += menuElements.get(menuElements.size() - 1).getHeight();

               for (int var8 = 0; var8 < CFG.peaceTreatyData.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).lReleasableCivs_TakeControl.size(); var8++) {
                  menuElements.add(
                     new Button_PeaceTreaty_Demands_ReleaseVassal(
                        CFG.peaceTreatyData.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).iCivID,
                        CFG.peaceTreatyData.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).lReleasableCivs_TakeControl.get(var8).iVassalCivID,
                        CFG.peaceTreatyData.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).lReleasableCivs_TakeControl.get(var8).iFromCivID,
                        this.countPoints(
                           CFG.peaceTreatyData.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).iCivID,
                           CFG.peaceTreatyData.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).lReleasableCivs_TakeControl.get(var8).iVassalCivID,
                           CFG.peaceTreatyData.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).lReleasableCivs_TakeControl.get(var8).iFromCivID
                        ),
                        0,
                        tY,
                        tempW,
                        CFG.TEXT_HEIGHT + CFG.PADDING * 5,
                        true
                     ) {
                        @Override
                        public void actionElement(int iID) {
                        }
                     }
                  );
                  tY += menuElements.get(menuElements.size() - 1).getHeight();
               }

               for (int var9 = 0; var9 < CFG.peaceTreatyData.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).lWarReparationsFromCivsID.size(); var9++) {
                  menuElements.add(
                     new Button_PeaceTreaty_Demands_WarReparations(
                        CFG.peaceTreatyData.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).lWarReparationsFromCivsID.get(var9),
                        CFG.peaceTreatyData.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).lWarReparationsFromCivsID.get(var9),
                        0,
                        tY,
                        tempW,
                        CFG.TEXT_HEIGHT + CFG.PADDING * 4,
                        true
                     ) {
                        @Override
                        public void actionElement(int iID) {
                        }
                     }
                  );
                  tY += menuElements.get(menuElements.size() - 1).getHeight();
               }

               for (int var10 = 0; var10 < CFG.peaceTreatyData.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).lDemands.size(); var10++) {
                  if (CFG.game.getProvince(CFG.peaceTreatyData.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).lDemands.get(var10)).getTrueOwnerOfProvince()
                     != CFG.peaceTreatyData.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).iCivID) {
                     menuElements.add(
                        new Button_PeaceTreaty_Demands_Province2(
                           CFG.peaceTreatyData.peaceTreatyGameData.lCivsDemands_Aggressors.get(i).lDemands.get(var10),
                           0,
                           tY,
                           tempW,
                           CFG.TEXT_HEIGHT + CFG.PADDING * 4,
                           true
                        ) {
                           @Override
                           public void actionElement(int iID) {
                              CFG.game.setActiveProvinceID(this.getCurrent());
                              CFG.map.getMapCoordinates().centerToProvinceID(CFG.game.getActiveProvinceID());
                           }
                        }
                     );
                     tY += menuElements.get(menuElements.size() - 1).getHeight();
                  }
               }
            }
         }
      }

      for (int var16 = 0; var16 < CFG.peaceTreatyData.peaceTreatyGameData.lCivsDemands_Defenders.size(); var16++) {
         if (CFG.peaceTreatyData.peaceTreatyGameData.lCivsDemands_Defenders.get(var16).lDemands.size() > 0
            || CFG.peaceTreatyData.peaceTreatyGameData.lCivsDemands_Defenders.get(var16).lWarReparationsFromCivsID.size() > 0
            || CFG.peaceTreatyData.peaceTreatyGameData.lCivsDemands_Defenders.get(var16).lReleasableCivs_TakeControl.size() > 0) {
            boolean addCiv = CFG.peaceTreatyData.peaceTreatyGameData.lCivsDemands_Defenders.get(var16).lWarReparationsFromCivsID.size() > 0;

            for (int jx = 0; jx < CFG.peaceTreatyData.peaceTreatyGameData.lCivsDemands_Defenders.get(var16).lDemands.size(); jx++) {
               if (CFG.game.getProvince(CFG.peaceTreatyData.peaceTreatyGameData.lCivsDemands_Defenders.get(var16).lDemands.get(jx)).getTrueOwnerOfProvince()
                  != CFG.peaceTreatyData.peaceTreatyGameData.lCivsDemands_Defenders.get(var16).iCivID) {
                  addCiv = true;
                  break;
               }
            }

            if (addCiv || CFG.peaceTreatyData.peaceTreatyGameData.lCivsDemands_Defenders.get(var16).lReleasableCivs_TakeControl.size() > 0) {
               int var18 = CFG.TEXT_HEIGHT + CFG.PADDING * 4;
               menuElements.add(
                  new Button_PeaceTreaty_Demands_TakeAll(
                     CFG.peaceTreatyData.peaceTreatyGameData.lCivsDemands_Defenders.get(var16).iCivID, 0, tY, tempW, var18, true
                  ) {
                     @Override
                     public void actionElement(int iID) {
                     }
                  }
               );
               tY += menuElements.get(menuElements.size() - 1).getHeight();

               for (int var12 = 0;
                  var12 < CFG.peaceTreatyData.peaceTreatyGameData.lCivsDemands_Defenders.get(var16).lReleasableCivs_TakeControl.size();
                  var12++
               ) {
                  menuElements.add(
                     new Button_PeaceTreaty_Demands_ReleaseVassal(
                        CFG.peaceTreatyData.peaceTreatyGameData.lCivsDemands_Defenders.get(var16).iCivID,
                        CFG.peaceTreatyData.peaceTreatyGameData.lCivsDemands_Defenders.get(var16).lReleasableCivs_TakeControl.get(var12).iVassalCivID,
                        CFG.peaceTreatyData.peaceTreatyGameData.lCivsDemands_Defenders.get(var16).lReleasableCivs_TakeControl.get(var12).iFromCivID,
                        this.countPoints(
                           CFG.peaceTreatyData.peaceTreatyGameData.lCivsDemands_Defenders.get(var16).iCivID,
                           CFG.peaceTreatyData.peaceTreatyGameData.lCivsDemands_Defenders.get(var16).lReleasableCivs_TakeControl.get(var12).iVassalCivID,
                           CFG.peaceTreatyData.peaceTreatyGameData.lCivsDemands_Defenders.get(var16).lReleasableCivs_TakeControl.get(var12).iFromCivID
                        ),
                        0,
                        tY,
                        tempW,
                        CFG.TEXT_HEIGHT + CFG.PADDING * 5,
                        true
                     ) {
                        @Override
                        public void actionElement(int iID) {
                        }
                     }
                  );
                  tY += menuElements.get(menuElements.size() - 1).getHeight();
               }

               for (int var13 = 0; var13 < CFG.peaceTreatyData.peaceTreatyGameData.lCivsDemands_Defenders.get(var16).lWarReparationsFromCivsID.size(); var13++) {
                  menuElements.add(
                     new Button_PeaceTreaty_Demands_WarReparations(
                        CFG.peaceTreatyData.peaceTreatyGameData.lCivsDemands_Defenders.get(var16).lWarReparationsFromCivsID.get(var13),
                        CFG.peaceTreatyData.peaceTreatyGameData.lCivsDemands_Defenders.get(var16).lWarReparationsFromCivsID.get(var13),
                        0,
                        tY,
                        tempW,
                        CFG.TEXT_HEIGHT + CFG.PADDING * 4,
                        true
                     ) {
                        @Override
                        public void actionElement(int iID) {
                        }
                     }
                  );
                  tY += menuElements.get(menuElements.size() - 1).getHeight();
               }

               for (int var14 = 0; var14 < CFG.peaceTreatyData.peaceTreatyGameData.lCivsDemands_Defenders.get(var16).lDemands.size(); var14++) {
                  if (CFG.game
                        .getProvince(CFG.peaceTreatyData.peaceTreatyGameData.lCivsDemands_Defenders.get(var16).lDemands.get(var14))
                        .getTrueOwnerOfProvince()
                     != CFG.peaceTreatyData.peaceTreatyGameData.lCivsDemands_Defenders.get(var16).iCivID) {
                     menuElements.add(
                        new Button_PeaceTreaty_Demands_Province2(
                           CFG.peaceTreatyData.peaceTreatyGameData.lCivsDemands_Defenders.get(var16).lDemands.get(var14),
                           0,
                           tY,
                           tempW,
                           CFG.TEXT_HEIGHT + CFG.PADDING * 4,
                           true
                        ) {
                           @Override
                           public void actionElement(int iID) {
                              CFG.game.setActiveProvinceID(this.getCurrent());
                              CFG.map.getMapCoordinates().centerToProvinceID(CFG.game.getActiveProvinceID());
                           }
                        }
                     );
                     tY += menuElements.get(menuElements.size() - 1).getHeight();
                  }
               }
            }
         }
      }

      for (int var17 = 0; var17 < menuElements.size(); var17++) {
         menuElements.get(var17).setCurrent(var17 % 2);
      }

      int tempPosY = Math.max(
         Math.max(CFG.BUTTON_HEIGHT * 4 / 5, Math.max(CFG.CIV_FLAG_HEIGHT + CFG.PADDING * 4, (CFG.TEXT_HEIGHT + CFG.PADDING) * 2 + CFG.PADDING))
            + CFG.PADDING * 2,
         CFG.BUTTON_HEIGHT + CFG.PADDING
      );
      this.initMenu(
         new SliderMenuTitle(null, CFG.BUTTON_HEIGHT * 3 / 5, false, false) {
            @Override
            public void draw(SpriteBatch oSB, int iTranslateX, int nPosX, int nPosY, int nWidth, boolean sliderMenuIsActive) {
               ImageManager.getImage(Images.dialog_title)
                  .draw2(
                     oSB,
                     nPosX - 2 + iTranslateX,
                     nPosY - this.getHeight() - ImageManager.getImage(Images.dialog_title).getHeight(),
                     nWidth + 2,
                     this.getHeight()
                  );
               oSB.setColor(new Color(0.27450982F, 0.43137255F, 0.64705884F, 0.165F));
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
               oSB.setColor(new Color(0.27450982F, 0.43137255F, 0.64705884F, 0.375F));
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
         CFG.GAME_WIDTH - tempW,
         tempPosY + CFG.BUTTON_HEIGHT * 3 / 5,
         tempW,
         Math.min(
            menuElements.size() > 0
               ? menuElements.get(menuElements.size() - 1).getPosY() + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING
               : CFG.PADDING,
            CFG.GAME_HEIGHT
               - (CFG.BUTTON_HEIGHT * 2 + CFG.PADDING * 4 + CFG.PADDING * 2 + CFG.BUTTON_HEIGHT / 2)
               - CFG.BUTTON_HEIGHT * 2
               - CFG.PADDING * 4
               - CFG.BUTTON_HEIGHT
               - CFG.PADDING * 2
         ),
         menuElements,
         true,
         true
      );
      this.updateLanguage();
   }

   @Override
   public void updateLanguage() {
      this.getTitle().setText(CFG.langManager.get("Demands"));
   }

   public final int countPoints(int iCivID, int iReleaseCivID, int toReleaseByCivID) {
      int out = 0;

      for (int i = 0; i < CFG.peaceTreatyData.peaceTreatyGameData.lCivsDemands_Defenders.size(); i++) {
         if (CFG.peaceTreatyData.peaceTreatyGameData.lCivsDemands_Defenders.get(i).iCivID == toReleaseByCivID) {
            for (int j = 0; j < CFG.peaceTreatyData.peaceTreatyGameData.lCivsDemands_Defenders.get(i).lReleasableCivs.size(); j++) {
               if (CFG.peaceTreatyData.peaceTreatyGameData.lCivsDemands_Defenders.get(i).lReleasableCivs.get(j).iCivID == iReleaseCivID) {
                  for (int k = 0; k < CFG.peaceTreatyData.peaceTreatyGameData.lCivsDemands_Defenders.get(i).lReleasableCivs.get(j).lProvinces.size(); k++) {
                     out += CFG.game
                        .getProvinceValue(CFG.peaceTreatyData.peaceTreatyGameData.lCivsDemands_Defenders.get(i).lReleasableCivs.get(j).lProvinces.get(k));
                  }
               }
            }
         }
      }

      for (int var10 = 0; var10 < CFG.peaceTreatyData.peaceTreatyGameData.lCivsDemands_Aggressors.size(); var10++) {
         if (CFG.peaceTreatyData.peaceTreatyGameData.lCivsDemands_Aggressors.get(var10).iCivID == toReleaseByCivID) {
            for (int jx = 0; jx < CFG.peaceTreatyData.peaceTreatyGameData.lCivsDemands_Aggressors.get(var10).lReleasableCivs.size(); jx++) {
               if (CFG.peaceTreatyData.peaceTreatyGameData.lCivsDemands_Aggressors.get(var10).lReleasableCivs.get(jx).iCivID == iReleaseCivID) {
                  for (int k = 0; k < CFG.peaceTreatyData.peaceTreatyGameData.lCivsDemands_Aggressors.get(var10).lReleasableCivs.get(jx).lProvinces.size(); k++) {
                     out += CFG.game
                        .getProvinceValue(CFG.peaceTreatyData.peaceTreatyGameData.lCivsDemands_Aggressors.get(var10).lReleasableCivs.get(jx).lProvinces.get(k));
                  }
               }
            }
         }
      }

      return out;
   }

   @Override
   public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      if (Menu_Civilization_Info.lTime + 250L >= System.currentTimeMillis()) {
         iTranslateX += this.getWidth() - (int)(this.getWidth() * ((float)(System.currentTimeMillis() - Menu_Civilization_Info.lTime) / 250.0F));
         CFG.setRender_3(true);
      }

      ImageManager.getImage(Images.new_game_top_edge_line)
         .draw2(
            oSB,
            this.getPosX() - 2 + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.new_game_top_edge_line).getHeight() + iTranslateY,
            this.getWidth() + 4,
            this.getHeight(),
            false,
            true
         );
      super.draw(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
      oSB.setColor(CFG.COLOR_CREATE_NEW_GAME_BOX_PLAYERS);
      ImageManager.getImage(Images.pix255_255_255)
         .draw(
            oSB,
            this.getPosX() - 2 + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + this.getHeight(),
            this.getWidth() + 2
         );
      oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.4F));
      ImageManager.getImage(Images.line_32_off1)
         .draw(
            oSB,
            this.getPosX() - 2 + iTranslateX,
            this.getPosY()
               - ImageManager.getImage(Images.pix255_255_255).getHeight()
               - ImageManager.getImage(Images.line_32_off1).getHeight()
               + this.getHeight(),
            this.getWidth() + 2,
            1
         );
      oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.5F));
      ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() - 2 + iTranslateX, this.getPosY() + this.getHeight(), this.getWidth() + 2);
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
      this.getMenuElement(iID).actionElement(iID);
   }

   @Override
   public void actionClose() {
      this.setVisible(false);
      CFG.menuManager.hidePeaceTreaty_ResponseProvinces();
   }

   @Override
   public void setVisible(boolean visible) {
      if (visible && !this.getVisible()) {
         Menu_Civilization_Info.lTime = System.currentTimeMillis();
      }

      super.setVisible(visible);
   }
}
