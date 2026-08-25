package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.utils.ScissorStack;
import java.util.ArrayList;
import java.util.List;

public class GraphData {
   public static final float ALPHA_CIV_LINE = 0.8F;
   public int iCivID;
   public List<Integer> lPointsY;
   public int iPointsSize = 0;
   public List<GraphLine> lGraphLines;
   public int iBeginTurnID;
   public boolean drawData = true;
   public boolean visible = true;
   public boolean backAnimation = false;
   public static final int ANIMATION_TIME = 750;
   public long lTime = 0L;

   public GraphData(int iCivID, List<Integer> nPointsY, int iBeginTurnID) {
      this.iCivID = iCivID;
      this.iPointsSize = nPointsY.size();
      this.lPointsY = new ArrayList<>();
      this.lGraphLines = new ArrayList<>();

      for (int i = 0; i < this.iPointsSize; i++) {
         this.lPointsY.add(nPointsY.get(i));
      }

      this.iBeginTurnID = iBeginTurnID;
      this.drawData = false;
   }

   public final void draw(SpriteBatch oSB, int iPosX, int iPosY, int iWidth, int iHeight, List<Integer> nPointsPosX, int id, boolean active, int iFixPosY) {
      if (this.lTime + 750L >= System.currentTimeMillis()) {
         this.drawAnimation(oSB, iPosX, iPosY, iWidth, iHeight, nPointsPosX, id, active, iFixPosY);
      } else {
         this.drawGraphData(oSB, iPosX, iPosY - iFixPosY, nPointsPosX, id, active);
      }
   }

   public final void drawAnimation(
      SpriteBatch oSB, int iPosX, int iPosY, int iWidth, int iHeight, List<Integer> nPointsPosX, int id, boolean active, int iFixPosY
   ) {
      Rectangle clipBounds = this.backAnimation
         ? new Rectangle(iPosX, CFG.GAME_HEIGHT - iPosY, iWidth - (int)(iWidth * ((float)(System.currentTimeMillis() - this.lTime) / 750.0F)), -iHeight)
         : new Rectangle(iPosX, CFG.GAME_HEIGHT - iPosY, (int)(iWidth * ((float)(System.currentTimeMillis() - this.lTime) / 750.0F)), -iHeight);
      oSB.flush();
      ScissorStack.pushScissors(clipBounds);
      this.drawGraphData(oSB, iPosX, iPosY - iFixPosY, nPointsPosX, id, true);
      CFG.setRender_3(true);

      try {
         oSB.flush();
         ScissorStack.popScissors();
      } catch (IllegalStateException var12) {
      }
   }

   public final void drawGraphData(SpriteBatch oSB, int iPosX, int iPosY, List<Integer> nPointsPosX, int id, boolean active) {
      try {
         oSB.setColor(
            new Color(
               CFG.game.getCiv(this.iCivID).getR() / 255.0F,
               CFG.game.getCiv(this.iCivID).getG() / 255.0F,
               CFG.game.getCiv(this.iCivID).getB() / 255.0F,
               active ? 1.0F : 0.8F
            )
         );
      } catch (IndexOutOfBoundsException var8) {
         oSB.setColor(new Color(0.05882353F, 0.05882353F, 0.05882353F, active ? 1.0F : 0.8F));
      }

      for (int i = 0; i < this.iPointsSize - 1; i++) {
         this.lGraphLines.get(i).draw(oSB, iPosX + nPointsPosX.get(this.iBeginTurnID + i), iPosY, id);
      }
   }

   public final void drawCivButton(SpriteBatch oSB, int iPosX, int iPosY, boolean active) {
      oSB.setColor(
         new Color(
            Graph.GRAPH_BG_COLOR.r,
            Graph.GRAPH_BG_COLOR.g,
            Graph.GRAPH_BG_COLOR.b,
            active ? Graph.GRAPH_BG_COLOR.a * 2.0F : (this.drawData ? Graph.GRAPH_BG_COLOR.a : Graph.GRAPH_BG_COLOR.a / 4.0F)
         )
      );
      ImageManager.getImage(Images.pix255_255_255).draw(oSB, iPosX, iPosY, Graph.getGraphButtonWidth(), Graph.getGraphButtonHeight());
      oSB.setColor(
         new Color(Graph.GRAPH_BORDERS_COLOR.r, Graph.GRAPH_BORDERS_COLOR.g, Graph.GRAPH_BORDERS_COLOR.b, this.drawData ? Graph.GRAPH_BORDERS_COLOR.a : 0.25F)
      );

      try {
         oSB.setColor(
            new Color(
               CFG.game.getCiv(this.iCivID).getR() / 255.0F,
               CFG.game.getCiv(this.iCivID).getG() / 255.0F,
               CFG.game.getCiv(this.iCivID).getB() / 255.0F,
               this.drawData ? 0.8F : 0.4F
            )
         );
      } catch (IndexOutOfBoundsException var7) {
         oSB.setColor(new Color(0.05882353F, 0.05882353F, 0.05882353F, this.drawData ? 0.8F : 0.4F));
      }

      ImageManager.getImage(Images.pix255_255_255).draw(oSB, iPosX, iPosY, CFG.CIV_COLOR_WIDTH, Graph.getGraphButtonHeight());
      oSB.setColor(this.drawData ? Color.WHITE : new Color(1.0F, 1.0F, 1.0F, 0.25F));

      try {
         CFG.game
            .getCiv(this.iCivID)
            .getFlag()
            .draw(
               oSB,
               iPosX + Graph.getGraphButtonWidth() / 2 - CFG.CIV_FLAG_WIDTH / 2,
               iPosY + Graph.getGraphButtonHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 - CFG.game.getCiv(this.iCivID).getFlag().getHeight(),
               CFG.CIV_FLAG_WIDTH,
               CFG.CIV_FLAG_HEIGHT
            );
      } catch (IndexOutOfBoundsException var6) {
         ImageManager.getImage(Images.randomCivilizationFlag)
            .draw(
               oSB,
               iPosX + Graph.getGraphButtonWidth() / 2 - CFG.CIV_FLAG_WIDTH / 2,
               iPosY + Graph.getGraphButtonHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 - ImageManager.getImage(Images.randomCivilizationFlag).getHeight(),
               CFG.CIV_FLAG_WIDTH,
               CFG.CIV_FLAG_HEIGHT
            );
      }

      ImageManager.getImage(Images.flag_rect)
         .draw(oSB, iPosX + Graph.getGraphButtonWidth() / 2 - CFG.CIV_FLAG_WIDTH / 2, iPosY + Graph.getGraphButtonHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2);
      oSB.setColor(Color.WHITE);
   }

   public final void buildGraph(int iHeight, int nMinPoint, int nMaxPoint, List<Integer> nPointsPosX) {
      this.lGraphLines.clear();

      for (int i = 0; i < this.iPointsSize - 1; i++) {
         this.lGraphLines
            .add(
               new GraphLine(
                  nPointsPosX.get(this.iBeginTurnID + i),
                  (int)(iHeight - iHeight * (100.0F * this.lPointsY.get(i).intValue()) / (nMaxPoint - nMinPoint) / 100.0F),
                  nPointsPosX.get(this.iBeginTurnID + i + 1),
                  (int)(iHeight - iHeight * (100.0F * this.lPointsY.get(i + 1).intValue()) / (nMaxPoint - nMinPoint) / 100.0F)
               )
            );
      }
   }

   public final int getPointY(int i) {
      try {
         return this.lPointsY.get(i);
      } catch (IndexOutOfBoundsException var3) {
         return 0;
      }
   }

   public final int getPointsSize() {
      return this.iPointsSize;
   }

   public final int getCivID() {
      return this.iCivID;
   }

   public final int getBeginTurnID() {
      return this.iBeginTurnID;
   }

   public final boolean getDrawData() {
      return this.drawData;
   }

   public final void setDrawData(boolean drawData) {
      if (drawData != this.drawData) {
         this.lTime = this.lTime <= System.currentTimeMillis() - 750L || !this.drawData && !this.backAnimation
            ? System.currentTimeMillis()
            : System.currentTimeMillis() - (750L - (System.currentTimeMillis() - this.lTime));
         CFG.setRender_3(true);
         this.backAnimation = !drawData;
      }

      this.drawData = drawData;
   }

   public final boolean getVisible() {
      return this.visible;
   }

   public final void setVisible(boolean visible) {
      this.visible = visible;
   }

   public final boolean getBackAnimation() {
      return this.backAnimation;
   }

   public final void setBackAnimation(boolean backAnimation) {
      this.backAnimation = backAnimation;
   }

   public final long getTime() {
      return this.lTime;
   }
}
