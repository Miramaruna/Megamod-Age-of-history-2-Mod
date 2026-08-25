package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.utils.ScissorStack;
import java.util.ArrayList;
import java.util.List;

public class Menu_InGame_FlagAction_Bot_Right_Left extends SliderMenu {
   public static long lTime = 0L;

   public Menu_InGame_FlagAction_Bot_Right_Left() {
      int tempHeight = 0;
      int tempWidth = 0;
      if (CFG.isAndroid() && CFG.LANDSCAPE) {
         tempHeight = CFG.GAME_HEIGHT
            - (
               ImageManager.getImage(Images.top_left).getHeight()
                  + CFG.PADDING * 2
                  + ImageManager.getImage(Images.top_flag_frame).getHeight()
                  + CFG.PADDING * 4
                  + CFG.TEXT_HEIGHT
                  + CFG.PADDING * 4
            )
            - CFG.PADDING * 2
            - CFG.BUTTON_HEIGHT / 2;
         tempWidth = CFG.GAME_WIDTH - CFG.GAME_WIDTH * 2 / 5 - CFG.PADDING * 2;
      } else {
         tempHeight = CFG.GAME_HEIGHT
            - (
               ImageManager.getImage(Images.top_left).getHeight()
                  + CFG.PADDING * 2
                  + ImageManager.getImage(Images.top_flag_frame).getHeight()
                  + CFG.PADDING * 4
                  + CFG.TEXT_HEIGHT
                  + CFG.PADDING * 4
            )
            - CFG.map.getMapBG().getMinimapHeight()
            - CFG.PADDING * 2
            - CFG.BUTTON_HEIGHT / 2;
         tempWidth = CFG.GAME_WIDTH - CFG.GAME_WIDTH * 2 / 5 - CFG.PADDING * 2;
      }

      ArrayList<MenuElement> menuElements = new ArrayList<>();
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

      for (int var23 = 0; var23 < CFG.game.getPlayersSize(); var23++) {
         if (var23 != CFG.PLAYER_TURNID
            && CFG.game.getCiv(CFG.game.getPlayer(var23).getCivID()).getNumOfProvinces() > 0
            && (CFG.FOG_OF_WAR != 2 || CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetCivilization(CFG.game.getPlayer(var23).getCivID()))) {
            tAdded.set(CFG.game.getPlayer(var23).getCivID(), true);
            tempCivs.add(CFG.game.getPlayer(var23).getCivID());
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

      for (int var22 = 1; var22 < CFG.game.getCivsSize(); var22++) {
         if (!tAdded.get(var22)) {
            tempNeighboors.add(var22);
         }
      }

      if (Menu_InGame_FlagAction_Bot_Right_Right.iViewMode != 0) {
         if (Menu_InGame_FlagAction_Bot_Right_Right.iViewMode == 1) {
            ArrayList<Integer> tempPop = new ArrayList<>();

            for (int ix = 0; ix < tempNeighboors.size(); ix++) {
               tempPop.add((int)CFG.game.getCiv(tempNeighboors.get(ix)).countPopulation());
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

               for (int ix = 1; ix < tempNeighboors.size(); ix++) {
                  if (CFG.game.getCiv(tempNeighboors.get(tBest)).getTechnologyLevel() < CFG.game.getCiv(tempNeighboors.get(ix)).getTechnologyLevel()) {
                     tBest = ix;
                  }
               }

               tempCivs.add(tempNeighboors.get(tBest));
               tempNeighboors.remove(tBest);
            }
         } else if (Menu_InGame_FlagAction_Bot_Right_Right.iViewMode == 3) {
            while (tempNeighboors.size() > 0) {
               int tBest = 0;

               for (int ixx = 1; ixx < tempNeighboors.size(); ixx++) {
                  if (CFG.game.getCiv(tempNeighboors.get(tBest)).getRankScore() < CFG.game.getCiv(tempNeighboors.get(ixx)).getRankScore()) {
                     tBest = ixx;
                  }
               }

               tempCivs.add(tempNeighboors.get(tBest));
               tempNeighboors.remove(tBest);
            }
         }
      } else {
         while (tempNeighboors.size() > 0) {
            int tBest = 0;

            for (int ixxx = 1; ixxx < tempNeighboors.size(); ixxx++) {
               if (CFG.game.getCiv(tempNeighboors.get(tBest)).getNumOfProvinces() < CFG.game.getCiv(tempNeighboors.get(ixxx)).getNumOfProvinces()) {
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
            CFG.PADDING * 2,
            CFG.PADDING * 2,
            tempWidth * 7 / 10 - CFG.PADDING * 4,
            tempHeight - tempHeight / 2 - CFG.PADDING * 3,
            true,
            tempCivs,
            Math.min(nLoad, 5)
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
         }
      );
      menuElements.add(new Button_Transparent(0, 0, tempWidth * 7 / 10, tempHeight - tempHeight / 2, true));
      this.initMenu(
         null,
         CFG.GAME_WIDTH - CFG.GAME_WIDTH * 3 / 5,
         tempHeight / 2
            + ImageManager.getImage(Images.top_left).getHeight()
            + CFG.PADDING * 2
            + ImageManager.getImage(Images.top_flag_frame).getHeight()
            + CFG.PADDING * 4
            + CFG.TEXT_HEIGHT
            + CFG.PADDING * 4
            + CFG.BUTTON_HEIGHT / 2,
         tempWidth * 7 / 10,
         tempHeight - tempHeight / 2,
         menuElements,
         false,
         false
      );
   }

   @Override
   public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      if (lTime + 225L >= System.currentTimeMillis()) {
         Rectangle clipBounds = new Rectangle(
            this.getPosX(),
            CFG.GAME_HEIGHT - this.getPosY(),
            this.getWidth(),
            -((int)(this.getHeight() * ((float)(System.currentTimeMillis() - lTime) / 225.0F)))
         );
         oSB.flush();
         ScissorStack.pushScissors(clipBounds);
         oSB.setColor(Color.WHITE);
         ImageManager.getImage(Images.new_game_top_edge_line_horizontal)
            .draw2(
               oSB,
               this.getPosX() + iTranslateX,
               this.getPosY() - ImageManager.getImage(Images.new_game_top_edge_line_horizontal).getHeight() + iTranslateY,
               this.getWidth(),
               this.getHeight(),
               true,
               true
            );
         oSB.setColor(new Color(0.025F, 0.025F, 0.025F, 0.25F));
         ImageManager.getImage(Images.line_32_off1)
            .draw(
               oSB,
               this.getPosX() + iTranslateX,
               this.getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
               this.getWidth(),
               this.getHeight()
            );
         oSB.setColor(new Color(0.025F, 0.025F, 0.025F, 0.75F));
         ImageManager.getImage(Images.gradient)
            .draw(
               oSB,
               this.getPosX() + iTranslateX,
               this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY,
               this.getWidth() - 2,
               CFG.BUTTON_HEIGHT / 4
            );
         ImageManager.getImage(Images.slider_gradient)
            .draw(
               oSB,
               this.getPosX() + iTranslateX,
               this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY,
               CFG.BUTTON_HEIGHT / 4,
               this.getHeight() - 2
            );
         oSB.setColor(new Color(CFG.COLOR_NEW_GAME_EDGE_LINE));
         ImageManager.getImage(Images.pix255_255_255)
            .draw(
               oSB,
               this.getPosX() + iTranslateX,
               this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY,
               1,
               this.getHeight() - 2
            );
         oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.375F));
         ImageManager.getImage(Images.gradient)
            .draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY, 1, this.getHeight() - 2);
         oSB.setColor(Color.WHITE);
         super.draw(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
         CFG.setRender_3(true);

         try {
            oSB.flush();
            ScissorStack.popScissors();
         } catch (IllegalStateException var7) {
         }
      } else {
         oSB.setColor(Color.WHITE);
         ImageManager.getImage(Images.new_game_top_edge_line_horizontal)
            .draw2(
               oSB,
               this.getPosX() + iTranslateX,
               this.getPosY() - ImageManager.getImage(Images.new_game_top_edge_line_horizontal).getHeight() + iTranslateY,
               this.getWidth(),
               this.getHeight(),
               true,
               true
            );
         oSB.setColor(new Color(0.025F, 0.025F, 0.025F, 0.25F));
         ImageManager.getImage(Images.line_32_off1)
            .draw(
               oSB,
               this.getPosX() + iTranslateX,
               this.getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
               this.getWidth(),
               this.getHeight()
            );
         oSB.setColor(new Color(0.025F, 0.025F, 0.025F, 0.75F));
         ImageManager.getImage(Images.gradient)
            .draw(
               oSB,
               this.getPosX() + iTranslateX,
               this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY,
               this.getWidth() - 2,
               CFG.BUTTON_HEIGHT / 4
            );
         ImageManager.getImage(Images.slider_gradient)
            .draw(
               oSB,
               this.getPosX() + iTranslateX,
               this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY,
               CFG.BUTTON_HEIGHT / 4,
               this.getHeight() - 2
            );
         oSB.setColor(new Color(CFG.COLOR_NEW_GAME_EDGE_LINE));
         ImageManager.getImage(Images.pix255_255_255)
            .draw(
               oSB,
               this.getPosX() + iTranslateX,
               this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY,
               1,
               this.getHeight() - 2
            );
         oSB.setColor(Color.WHITE);
         super.draw(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
      }
   }

   @Override
   public void onHovered() {
      CFG.menuManager.setOrderOfMenu_InGame_FlagAction();
   }

   @Override
   public void actionElement(int iID) {
      switch (iID) {
      }
   }

   @Override
   public void setVisible(boolean visible) {
      super.setVisible(visible);
      lTime = System.currentTimeMillis();
   }

   @Override
   public boolean getVisible() {
      return CFG.isAndroid() && !CFG.LANDSCAPE ? false : super.getVisible();
   }
}
