package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;
import java.util.List;

public class Menu_InGame_Graph extends SliderMenu {
   public Menu_InGame_Graph() {
      ArrayList<MenuElement> menuElements = new ArrayList<>();
      int tMenuWidth = CFG.GAME_WIDTH / 2;
      int tMenuHeight = CFG.GAME_WIDTH / 4;

      try {
         ArrayList<Boolean> tAdded = new ArrayList<>();

         for (int i4 = 0; i4 < CFG.game.getCivsSize(); i4++) {
            tAdded.add(CFG.FOG_OF_WAR >= 2 && !CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetCivilization(i4));
         }

         ArrayList<Integer> tempCivs = new ArrayList<>();
         int nLoad = 1;
         tAdded.set(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), true);
         tempCivs.add(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());

         for (int i3 = 1; i3 < CFG.game.getCivsSize(); i3++) {
            if (!tAdded.get(i3) && CFG.game.getCivsAtWar(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), i3)) {
               tAdded.set(i3, true);
               tempCivs.add(i3);
               nLoad++;
            }
         }

         for (int var24 = 0; var24 < CFG.game.getPlayersSize(); var24++) {
            if (var24 != CFG.PLAYER_TURNID
               && CFG.game.getCiv(CFG.game.getPlayer(var24).getCivID()).getNumOfProvinces() > 0
               && (CFG.FOG_OF_WAR != 2 || CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetCivilization(CFG.game.getPlayer(var24).getCivID()))) {
               tAdded.set(CFG.game.getPlayer(var24).getCivID(), true);
               tempCivs.add(CFG.game.getPlayer(var24).getCivID());
               nLoad++;
            }
         }

         ArrayList<Integer> tempNeighboors = new ArrayList<>();

         for (int i2 = 0; i2 < CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getNumOfProvinces(); i2++) {
            for (int j = 0;
               j < CFG.game.getProvince(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getProvinceID(i2)).getNeighboringProvincesSize();
               j++
            ) {
               if (CFG.game
                        .getProvince(
                           CFG.game.getProvince(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getProvinceID(i2)).getNeighboringProvinces(j)
                        )
                        .getCivID()
                     > 0
                  && !tAdded.get(
                     CFG.game
                        .getProvince(
                           CFG.game.getProvince(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getProvinceID(i2)).getNeighboringProvinces(j)
                        )
                        .getCivID()
                  )) {
                  tempNeighboors.add(
                     CFG.game
                        .getProvince(
                           CFG.game.getProvince(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getProvinceID(i2)).getNeighboringProvinces(j)
                        )
                        .getCivID()
                  );
                  tAdded.set(
                     CFG.game
                        .getProvince(
                           CFG.game.getProvince(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getProvinceID(i2)).getNeighboringProvinces(j)
                        )
                        .getCivID(),
                     true
                  );
                  nLoad++;
               }
            }
         }

         while (tempNeighboors.size() > 0) {
            int tBest = 0;

            for (int i = 1; i < tempNeighboors.size(); i++) {
               if (CFG.game.getCiv(tempNeighboors.get(tBest)).getNumOfProvinces() < CFG.game.getCiv(tempNeighboors.get(i)).getNumOfProvinces()) {
                  tBest = i;
               }
            }

            tempCivs.add(tempNeighboors.get(tBest));
            tempNeighboors.remove(tBest);
         }

         tempNeighboors.clear();

         for (int var23 = 1; var23 < CFG.game.getCivsSize(); var23++) {
            if (!tAdded.get(var23)) {
               tempNeighboors.add(var23);
            }
         }

         if (Menu_InGame_FlagAction_Bot_Right_Right.iViewMode == 0) {
            while (tempNeighboors.size() > 0) {
               int tBest = 0;

               for (int ix = 1; ix < tempNeighboors.size(); ix++) {
                  if (CFG.game.getCiv(tempNeighboors.get(tBest)).getNumOfProvinces() < CFG.game.getCiv(tempNeighboors.get(ix)).getNumOfProvinces()) {
                     tBest = ix;
                  }
               }

               tempCivs.add(tempNeighboors.get(tBest));
               tempNeighboors.remove(tBest);
            }
         } else if (Menu_InGame_FlagAction_Bot_Right_Right.iViewMode == 1) {
            ArrayList<Integer> tempPop = new ArrayList<>();

            for (int ixx = 0; ixx < tempNeighboors.size(); ixx++) {
               tempPop.add((int)CFG.game.getCiv(tempNeighboors.get(ixx)).countPopulation());
            }

            while (tempNeighboors.size() > 0) {
               int tBest2 = 0;

               for (int i5 = 1; i5 < tempNeighboors.size(); i5++) {
                  if (tempPop.get(tBest2) < tempPop.get(i5)) {
                     tBest2 = i5;
                  }
               }

               tempCivs.add(tempNeighboors.get(tBest2));
               tempNeighboors.remove(tBest2);
               tempPop.remove(tBest2);
            }
         } else if (Menu_InGame_FlagAction_Bot_Right_Right.iViewMode == 2) {
            while (tempNeighboors.size() > 0) {
               int tBest = 0;

               for (int ixx = 1; ixx < tempNeighboors.size(); ixx++) {
                  if (CFG.game.getCiv(tempNeighboors.get(tBest)).getTechnologyLevel() < CFG.game.getCiv(tempNeighboors.get(ixx)).getTechnologyLevel()) {
                     tBest = ixx;
                  }
               }

               tempCivs.add(tempNeighboors.get(tBest));
               tempNeighboors.remove(tBest);
            }
         } else if (Menu_InGame_FlagAction_Bot_Right_Right.iViewMode == 3) {
            while (tempNeighboors.size() > 0) {
               int tBest = 0;

               for (int ixxx = 1; ixxx < tempNeighboors.size(); ixxx++) {
                  if (CFG.game.getCiv(tempNeighboors.get(tBest)).getRankScore() < CFG.game.getCiv(tempNeighboors.get(ixxx)).getRankScore()) {
                     tBest = ixxx;
                  }
               }

               tempCivs.add(tempNeighboors.get(tBest));
               tempNeighboors.remove(tBest);
            }
         }

         menuElements.add(
            new Graph(
               CFG.langManager.get("Turn"),
               Menu_InGame_FlagAction_Bot_Right_Right.getViewName(),
               CFG.PADDING,
               CFG.PADDING * 2,
               150,
               225,
               true,
               tempCivs,
               Math.min(nLoad, 1)
            ) {
               @Override
               public void loadData(int i) {
                  if (Menu_InGame_FlagAction_Bot_Right_Right.iViewMode == 0) {
                     super.loadData(i);
                  } else if (Menu_InGame_FlagAction_Bot_Right_Right.iViewMode == 1) {
                     int nStartTurnID = -1;
                     int jSize = CFG.timelapseManager.timelapseStatsGD.lPopulation.size();

                     for (int jx = 0; jx < jSize; jx++) {
                        if (CFG.timelapseManager.timelapseStatsGD.lPopulation.get(jx).size() > this.lData.get(i).getCivID()) {
                           nStartTurnID = jx;
                           break;
                        }
                     }

                     ArrayList<Integer> tempPoints = new ArrayList<>();
                     if (nStartTurnID >= 0) {
                        int jSize2 = CFG.timelapseManager.timelapseStatsGD.lPopulation.size();

                        for (int jx = nStartTurnID; jx < jSize2; jx++) {
                           tempPoints.add(CFG.timelapseManager.timelapseStatsGD.lPopulation.get(jx).get(this.lData.get(i).getCivID()));
                        }
                     }

                     if (tempPoints.size() > 0) {
                        this.lData.set(i, new GraphData(this.lData.get(i).getCivID(), tempPoints, nStartTurnID));
                        this.lData.get(i).setDrawData(true);
                        this.updateMoveable();
                        this.buildGraph();
                     }
                  } else if (Menu_InGame_FlagAction_Bot_Right_Right.iViewMode == 2) {
                     int nStartTurnID = -1;
                     int jSize = CFG.timelapseManager.timelapseStatsGD.lTechnologyLevel.size();

                     for (int jx = 0; jx < jSize; jx++) {
                        if (CFG.timelapseManager.timelapseStatsGD.lTechnologyLevel.get(jx).size() > this.lData.get(i).getCivID()) {
                           nStartTurnID = jx;
                           break;
                        }
                     }

                     ArrayList<Integer> tempPointsx = new ArrayList<>();
                     if (nStartTurnID >= 0) {
                        int jSize3 = CFG.timelapseManager.timelapseStatsGD.lTechnologyLevel.size();

                        for (int jxx = nStartTurnID; jxx < jSize3; jxx++) {
                           tempPointsx.add(CFG.timelapseManager.timelapseStatsGD.lTechnologyLevel.get(jxx).get(this.lData.get(i).getCivID()));
                        }
                     }

                     if (tempPointsx.size() > 0) {
                        this.lData.set(i, new GraphData(this.lData.get(i).getCivID(), tempPointsx, nStartTurnID));
                        this.lData.get(i).setDrawData(true);
                        this.updateMoveable();
                        this.buildGraph();
                     }
                  } else if (Menu_InGame_FlagAction_Bot_Right_Right.iViewMode == 3) {
                     int nStartTurnID = -1;
                     int jSize = CFG.timelapseManager.timelapseStatsGD.lRank.size();

                     for (int jxx = 0; jxx < jSize; jxx++) {
                        if (CFG.timelapseManager.timelapseStatsGD.lRank.get(jxx).size() > this.lData.get(i).getCivID()) {
                           nStartTurnID = jxx;
                           break;
                        }
                     }

                     ArrayList<Integer> tempPointsxx = new ArrayList<>();
                     if (nStartTurnID >= 0) {
                        int jSize4 = CFG.timelapseManager.timelapseStatsGD.lRank.size();

                        for (int jxxx = nStartTurnID; jxxx < jSize4; jxxx++) {
                           tempPointsxx.add(CFG.timelapseManager.timelapseStatsGD.lRank.get(jxxx).get(this.lData.get(i).getCivID()));
                        }
                     }

                     if (tempPointsxx.size() > 0) {
                        this.lData.set(i, new GraphData(this.lData.get(i).getCivID(), tempPointsxx, nStartTurnID));
                        this.lData.get(i).setDrawData(true);
                        this.updateMoveable();
                        this.buildGraph();
                     }
                  }
               }

               @Override
               public int getWidth() {
                  return Menu_InGame_Graph.this.getW() - CFG.PADDING * 2;
               }

               @Override
               public int getHeight() {
                  return Menu_InGame_Graph.this.getH() - CFG.PADDING * 4;
               }
            }
         );
      } catch (IndexOutOfBoundsException var15) {
         CFG.exceptionStack(var15);
      }

      this.initMenu(
         new SliderMenuTitle(Menu_InGame_FlagAction_Bot_Right_Right.getViewName(), CFG.BUTTON_HEIGHT / 2, true, true) {
            @Override
            public void draw(SpriteBatch oSB, int iTranslateX, int nPosX, int nPosY, int nWidth, boolean sliderMenuIsActive) {
               ImageManager.getImage(Images.dialog_title)
                  .draw2(
                     oSB,
                     nPosX + iTranslateX,
                     nPosY - this.getHeight() - ImageManager.getImage(Images.dialog_title).getHeight(),
                     nWidth - ImageManager.getImage(Images.dialog_title).getWidth(),
                     this.getHeight()
                  );
               ImageManager.getImage(Images.dialog_title)
                  .draw2(
                     oSB,
                     nPosX + nWidth - ImageManager.getImage(Images.dialog_title).getWidth() + iTranslateX,
                     nPosY - this.getHeight() - ImageManager.getImage(Images.dialog_title).getHeight(),
                     ImageManager.getImage(Images.dialog_title).getWidth(),
                     this.getHeight(),
                     true,
                     false
                  );
               oSB.setColor(new Color(0.22745098F, 0.4509804F, 0.4509804F, 0.165F));
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
               oSB.setColor(new Color(0.22745098F, 0.4509804F, 0.4509804F, 0.375F));
               ImageManager.getImage(Images.gradient)
                  .draw(
                     oSB,
                     nPosX + 2 + iTranslateX,
                     nPosY - this.getHeight() * 2 / 3 - ImageManager.getImage(Images.gradient).getHeight(),
                     nWidth - 4,
                     this.getHeight() * 2 / 3,
                     false,
                     true
                  );
               oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.5F));
               ImageManager.getImage(Images.gradient)
                  .draw(
                     oSB,
                     nPosX + 2 + iTranslateX,
                     nPosY - CFG.PADDING - ImageManager.getImage(Images.gradient).getHeight(),
                     nWidth - 4,
                     CFG.PADDING,
                     false,
                     true
                  );
               oSB.setColor(CFG.COLOR_NEW_GAME_EDGE_LINE);
               ImageManager.getImage(Images.slider_gradient)
                  .draw(oSB, nPosX + 2 + iTranslateX, nPosY - 1 - ImageManager.getImage(Images.slider_gradient).getHeight(), (nWidth - 4) / 2, 1);
               ImageManager.getImage(Images.slider_gradient)
                  .draw(
                     oSB,
                     nPosX + 2 + (nWidth - 4) - (nWidth - 4) / 2 + iTranslateX,
                     nPosY - 1 - ImageManager.getImage(Images.slider_gradient).getHeight(),
                     (nWidth - 4) / 2,
                     1,
                     true,
                     false
                  );
               oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.425F));
               ImageManager.getImage(Images.slider_gradient)
                  .draw(
                     oSB,
                     nPosX + CFG.PADDING * 2 + iTranslateX,
                     nPosY + 1 - this.getHeight() / 2 - ImageManager.getImage(Images.slider_gradient).getHeight(),
                     (int)((nWidth - CFG.PADDING * 6 - this.getTextWidth() * 0.8F) / 2.0F),
                     1,
                     true,
                     false
                  );
               ImageManager.getImage(Images.slider_gradient)
                  .draw(
                     oSB,
                     nPosX + nWidth / 2 + CFG.PADDING + (int)(this.getTextWidth() * 0.8F / 2.0F) + iTranslateX,
                     nPosY + 1 - this.getHeight() / 2 - ImageManager.getImage(Images.slider_gradient).getHeight(),
                     (int)((nWidth - CFG.PADDING * 6 - this.getTextWidth() * 0.8F) / 2.0F),
                     1
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
         CFG.GAME_WIDTH / 2 - tMenuWidth / 2,
         ImageManager.getImage(Images.top_left).getHeight() + CFG.PADDING * 4,
         tMenuWidth,
         tMenuHeight,
         menuElements,
         false,
         true
      );
      this.updateLanguage();
      this.getMenuElement(0).setCheckboxState(true);
   }

   @Override
   public void updateLanguage() {
   }

   @Override
   public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      oSB.setColor(Color.WHITE);
      ImageManager.getImage(Images.new_game_box)
         .draw2(
            oSB,
            this.getPosX() + iTranslateX,
            -ImageManager.getImage(Images.new_game_box).getHeight() + this.getMenuPosY() + iTranslateY,
            this.getW() - ImageManager.getImage(Images.new_game_box).getWidth(),
            this.getH(),
            false,
            true
         );
      ImageManager.getImage(Images.new_game_box)
         .draw2(
            oSB,
            this.getPosX() + this.getW() - ImageManager.getImage(Images.new_game_box).getWidth() + iTranslateX,
            -ImageManager.getImage(Images.new_game_box).getHeight() + this.getMenuPosY() + iTranslateY,
            ImageManager.getImage(Images.new_game_box).getWidth(),
            this.getH(),
            true,
            true
         );
      super.draw(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
   }

   @Override
   public final void actionElement(int iID) {
      switch (iID) {
      }
   }

   public final int getW() {
      return this.getWidth();
   }

   public final int getH() {
      return this.getHeight();
   }

   @Override
   public boolean setWidth(int iWidth) {
      boolean out = super.setWidth(iWidth);
      this.getMenuElement(0).setCheckboxState(true);
      return out;
   }
}
