package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.utils.ScissorStack;
import java.util.ArrayList;
import java.util.List;

public class Graph extends MenuElement {
   public static final Color GRAPH_BG_COLOR = new Color(0.04F, 0.02F, 0.03F, 0.4F);
   public static final Color GRAPH_BORDERS_COLOR = new Color(0.95F, 0.95F, 0.95F, 0.75F);
   public static final Color GRAPH_LINES_COLOR = new Color(0.9F, 0.9F, 0.9F, 0.125F);
   public static final Color GRAPH_LINES_DESC = new Color(0.9F, 0.9F, 0.9F, 0.1F);
   public static final Color TEXT_COLOR = new Color(0.9F, 0.9F, 0.9F, 1.0F);
   public static final Color DATA_COLOR = new Color(0.8F, 0.8F, 0.8F, 1.0F);
   public static float POINTS_TEXT_SCALE = 0.65F;
   public List<GraphData> lData;
   public int iDataSize;
   public List<Integer> lSortedData;
   public List<Integer> lPointsPosX;
   public int iPointsPosXSize;
   public int iMaxSize = 0;
   public int iFixPosY;
   public int iHoveredID = -1;
   public int iZeroPosY;
   public int iMinPoint;
   public int iMinTextWidth;
   public int iWorstCivID;
   public int iMaxPoint;
   public int iMaxTextWidth;
   public int iBestCivID;
   public float fAvaragePoint;
   public int iAvaragePosY;
   public byte bDecimal = 0;
   public boolean lessThanTen = false;
   public int iDescOfTurnID = 0;
   public int iWorstDescDataID;
   public int iWorstDescDataTextWidth;
   public int iBestDescDataID;
   public int iBestDescDataTextWidth;
   public String sTextX;
   public String sTextX2;
   public String sTextY;
   public int iWidthTextX;
   public int iWidthTextX2;
   public int iWidthTextY;
   public static final int ANIMATION_TIME = 950;
   public long lTime = 0L;
   public static final int AUTO_MOVE_TURN_TIME = 1450;
   public long lAuto_Move_Turn_Time = 0L;
   public boolean moveable = false;
   public int iButtonsPosY = 0;
   public int iActiveButtonID = -1;

   public static final int getGraphButtonWidth() {
      return CFG.BUTTON_WIDTH / 2;
   }

   public static final int getGraphButtonHeight() {
      return CFG.BUTTON_HEIGHT / 2;
   }

   public Graph(String sTextX, String sTextY, int iPosX, int iPosY, int iWidth, int iHeight, boolean visible, List<Integer> nCivs, int nLoadSize) {
      this.sTextX = sTextX;
      this.sTextY = sTextY;
      CFG.fontMain.getData().setScale(0.7F);
      CFG.glyphLayout.setText(CFG.fontMain, sTextX);
      this.iWidthTextX = (int)CFG.glyphLayout.width;
      CFG.glyphLayout.setText(CFG.fontMain, sTextY);
      this.iWidthTextY = (int)CFG.glyphLayout.width;
      CFG.fontMain.getData().setScale(1.0F);
      this.setPosX(iPosX);
      this.setPosY(iPosY);
      this.setWidth(iWidth);
      this.setHeight(iHeight);
      this.setVisible(visible);
      this.lData = new ArrayList<>();
      this.lSortedData = new ArrayList<>();
      this.lPointsPosX = new ArrayList<>();
      this.iFixPosY = 0;
      this.typeOfElement = MenuElement.TypeOfElement.GRAPH;

      for (int i = 0; i < nCivs.size(); i++) {
         this.addData(new GraphData(nCivs.get(i), new ArrayList<>(), 0));
      }

      for (int var11 = 0; var11 < nLoadSize && var11 < this.lData.size(); var11++) {
         this.loadData(var11);
      }

      this.iDataSize = this.lData.size();
   }

   @Override
   public void updateHover(int nPosX, int nPosY, int menuPosX, int menuPosY) {
      for (int i = 0; i < this.iDataSize; i++) {
         if (this.getPosX() + this.getWidth() - getGraphButtonWidth() + menuPosX <= nPosX
            && this.getPosX() + this.getWidth() + menuPosX >= nPosX
            && this.getButtonsPosY(i) + this.iButtonsPosY + menuPosY <= nPosY
            && this.getButtonsPosY(i) + getGraphButtonHeight() + this.iButtonsPosY + menuPosY >= nPosY) {
            this.setHoveredID(this.lSortedData.get(i));
            return;
         }
      }

      this.setHoveredID(-1);
   }

   public final void setHoveredID(int nHoveredID) {
      if (this.iHoveredID != nHoveredID) {
         this.iHoveredID = nHoveredID;
         this.buildElementHover();
      }
   }

   @Override
   public void buildElementHover() {
      if (this.iHoveredID >= 0) {
         ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
         ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
         if (this.lData.get(this.iHoveredID).getCivID() < 0) {
            nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.randomCivilizationFlag));
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Undiscovered"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
         } else {
            nData.add(new MenuElement_Hover_v2_Element_Type_Flag(this.lData.get(this.iHoveredID).getCivID()));
            nData.add(
               new MenuElement_Hover_v2_Element_Type_Text(
                  CFG.game.getCiv(this.lData.get(this.iHoveredID).getCivID()).getCivName(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
               )
            );
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
         }

         this.menuElementHover = new MenuElement_Hover_v2(nElements);
      } else {
         MenuElement_Hover_v2.resetAnimation_2();
         this.menuElementHover = null;
      }
   }

   @Override
   public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
      if (this.lAuto_Move_Turn_Time + 1450L < System.currentTimeMillis()) {
         this.incrementTurnDescInfo();
      }

      oSB.setColor(GRAPH_BG_COLOR);
      ImageManager.getImage(Images.pix255_255_255)
         .draw(
            oSB,
            this.getPosX() + (int)(CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2 + iTranslateX,
            this.getPosY() + iTranslateY,
            this.getGraphWidth() - (int)(CFG.TEXT_HEIGHT * 0.7F) - CFG.PADDING * 2,
            this.getHeight() - (int)(CFG.TEXT_HEIGHT * 0.7F) - CFG.PADDING * 2
         );
      CFG.fontMain.getData().setScale(0.7F);
      CFG.drawTextRotated(
         oSB,
         this.sTextY,
         this.getPosX() + CFG.PADDING + iTranslateX,
         this.getPosY() + this.getHeight() / 2 + this.iWidthTextY / 2 + iTranslateY,
         TEXT_COLOR,
         90.0F
      );
      CFG.drawText(
         oSB,
         this.sTextX,
         this.getPosX()
            + (int)(CFG.TEXT_HEIGHT * 0.7F)
            + CFG.PADDING * 2
            + (this.getGraphWidth() - (int)(CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2) / 2
            - (int)((this.iWidthTextX + this.iWidthTextX2) * 0.7F / 2.0F)
            + iTranslateX,
         this.getPosY() + this.getHeight() - CFG.PADDING - (int)(CFG.TEXT_HEIGHT * 0.7F) + iTranslateY,
         TEXT_COLOR
      );
      CFG.drawText(
         oSB,
         this.sTextX2,
         this.getPosX()
            + (int)(CFG.TEXT_HEIGHT * 0.7F)
            + CFG.PADDING * 2
            + (this.getGraphWidth() - (int)(CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2) / 2
            - (int)((this.iWidthTextX + this.iWidthTextX2) * 0.7F / 2.0F)
            + (int)(this.iWidthTextX * 0.7F)
            + iTranslateX,
         this.getPosY() + this.getHeight() - CFG.PADDING - (int)(CFG.TEXT_HEIGHT * 0.7F) + iTranslateY,
         CFG.COLOR_TEXT_OPTIONS_NS_HOVER
      );
      CFG.fontMain.getData().setScale(1.0F);
      oSB.setColor(GRAPH_LINES_DESC);
      ImageManager.getImage(Images.line_33)
         .draw(
            oSB,
            this.getPosX() + (int)(CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2 + iTranslateX,
            this.getPosY() - this.iFixPosY + this.iAvaragePosY + iTranslateY,
            this.getGraphWidth() - (int)(CFG.TEXT_HEIGHT * 0.7F) - CFG.PADDING * 2
         );
      oSB.setColor(GRAPH_LINES_COLOR);

      try {
         ImageManager.getImage(Images.line_44)
            .draw(
               oSB,
               this.getPosX() + (int)(CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2 + 1 + iTranslateX,
               (int)(
                     this.getPosY() - this.iFixPosY + this.getHeight() - ((int)(CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2)
                        - (this.getHeight() - ((int)(CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2))
                           * (
                              100.0F
                                 * this.lData.get(this.iBestDescDataID).getPointY(this.iDescOfTurnID - this.lData.get(this.iBestDescDataID).getBeginTurnID())
                           )
                           / (this.iMaxPoint - this.getMinPoint())
                           / 100.0F
                  )
                  + iTranslateY,
               this.getGraphWidth() - (int)(CFG.TEXT_HEIGHT * 0.7F) - CFG.PADDING * 2 - 1
            );
         ImageManager.getImage(Images.line_44)
            .draw(
               oSB,
               this.getPosX() + (int)(CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2 + 1 + iTranslateX,
               (int)(
                     this.getPosY() - this.iFixPosY + this.getHeight() - ((int)(CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2)
                        - (this.getHeight() - ((int)(CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2))
                           * (
                              100.0F
                                 * this.lData.get(this.iWorstDescDataID).getPointY(this.iDescOfTurnID - this.lData.get(this.iWorstDescDataID).getBeginTurnID())
                           )
                           / (this.iMaxPoint - this.getMinPoint())
                           / 100.0F
                  )
                  + iTranslateY,
               this.getGraphWidth() - (int)(CFG.TEXT_HEIGHT * 0.7F) - CFG.PADDING * 2 - 1
            );
         oSB.setColor(GRAPH_LINES_DESC);
         ImageManager.getImage(Images.pix255_255_255)
            .draw(
               oSB,
               this.getPosX() + (int)(CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2 + this.lPointsPosX.get(this.iDescOfTurnID) + iTranslateX,
               (int)(
                     this.getPosY() - this.iFixPosY + this.getHeight() - ((int)(CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2)
                        - (this.getHeight() - ((int)(CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2))
                           * (
                              100.0F
                                 * this.lData.get(this.iBestDescDataID).getPointY(this.iDescOfTurnID - this.lData.get(this.iBestDescDataID).getBeginTurnID())
                           )
                           / (this.iMaxPoint - this.getMinPoint())
                           / 100.0F
                  )
                  + iTranslateY,
               1,
               -(
                  (int)(
                     (this.getHeight() - ((int)(CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2))
                           * (
                              100.0F
                                 * this.lData.get(this.iWorstDescDataID).getPointY(this.iDescOfTurnID - this.lData.get(this.iWorstDescDataID).getBeginTurnID())
                           )
                           / (this.iMaxPoint - this.getMinPoint())
                           / 100.0F
                        + (this.getHeight() - ((int)(CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2))
                           * (
                              100.0F
                                 * this.lData.get(this.iBestDescDataID).getPointY(this.iDescOfTurnID - this.lData.get(this.iBestDescDataID).getBeginTurnID())
                           )
                           / (this.iMaxPoint - this.getMinPoint())
                           / 100.0F
                  )
               )
            );
      } catch (ArithmeticException var24) {
      }

      if (this.getMinPoint() < 0 && this.iMaxPoint > 0) {
         oSB.setColor(GRAPH_LINES_COLOR);
         ImageManager.getImage(Images.pix255_255_255)
            .draw(
               oSB,
               this.getPosX() + (int)(CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2 + 1 + iTranslateX,
               this.getPosY() - this.iFixPosY + this.iZeroPosY + iTranslateY,
               this.getGraphWidth() - (int)(CFG.TEXT_HEIGHT * 0.7F) - CFG.PADDING * 2 - 1
            );
         oSB.setColor(GRAPH_BORDERS_COLOR);
         ImageManager.getImage(Images.pix255_255_255)
            .draw(
               oSB,
               this.getPosX() + (int)(CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2 + 1 + iTranslateX,
               this.getPosY() - 1 - this.iFixPosY + this.iZeroPosY + iTranslateY,
               CFG.PADDING - 1
            );
         CFG.fontMain.getData().setScale(POINTS_TEXT_SCALE);
         CFG.drawText(
            oSB,
            "0",
            this.getPosX() + (int)(CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2 + 1 + (int)(2.0F * CFG.GUI_SCALE) + iTranslateX,
            this.getPosY() - (int)(2.0F * CFG.GUI_SCALE) - (int)(CFG.TEXT_HEIGHT * POINTS_TEXT_SCALE) - this.iFixPosY + this.iZeroPosY - 1 + iTranslateY,
            DATA_COLOR
         );
         CFG.fontMain.getData().setScale(1.0F);
      }

      if (this.lTime + 950L > System.currentTimeMillis()) {
         Rectangle clipBounds = new Rectangle(
            this.getPosX() + iTranslateX,
            CFG.GAME_HEIGHT - this.getPosY() - iTranslateY,
            (int)(this.getGraphWidth() * ((float)(System.currentTimeMillis() - this.lTime) / 950.0F)),
            -this.getHeight()
         );
         oSB.flush();
         ScissorStack.pushScissors(clipBounds);
         this.drawGraphData(oSB, iTranslateX, iTranslateY);
         CFG.setRender_3(true);

         try {
            oSB.flush();
            ScissorStack.popScissors();
         } catch (IllegalStateException var23) {
         }
      } else {
         this.drawGraphData(oSB, iTranslateX, iTranslateY);
      }

      try {
         for (int i = 0; i < this.iDataSize; i++) {
            if (this.lData.get(i).getDrawData()
               && this.iDescOfTurnID >= this.lData.get(i).getBeginTurnID()
               && this.iDescOfTurnID < this.lData.get(i).getBeginTurnID() + this.lData.get(i).getPointsSize()) {
               try {
                  oSB.setColor(
                     new Color(
                        CFG.game.getCiv(this.lData.get(i).getCivID()).getR() / 255.0F,
                        CFG.game.getCiv(this.lData.get(i).getCivID()).getG() / 255.0F,
                        CFG.game.getCiv(this.lData.get(i).getCivID()).getB() / 255.0F,
                        0.75F
                     )
                  );
               } catch (IndexOutOfBoundsException var22) {
                  oSB.setColor(new Color(0.05882353F, 0.05882353F, 0.05882353F, 0.75F));
               }

               ImageManager.getImage(Images.circle_55)
                  .draw(
                     oSB,
                     this.getPosX()
                        + (int)(CFG.TEXT_HEIGHT * 0.7F)
                        + CFG.PADDING * 2
                        + this.lPointsPosX.get(this.iDescOfTurnID)
                        - ImageManager.getImage(Images.circle_55).getWidth() / 2
                        + iTranslateX,
                     (int)(
                           this.getPosY() - this.iFixPosY + this.getHeight() - ((int)(CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2)
                              - (this.getHeight() - ((int)(CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2))
                                 * (100.0F * this.lData.get(i).getPointY(this.iDescOfTurnID - this.lData.get(i).getBeginTurnID()))
                                 / (this.iMaxPoint - this.getMinPoint())
                                 / 100.0F
                              - ImageManager.getImage(Images.circle_55).getHeight() / 2
                        )
                        + iTranslateY
                  );
            }
         }
      } catch (ArithmeticException var25) {
      }

      oSB.setColor(GRAPH_BORDERS_COLOR);
      ImageManager.getImage(Images.pix255_255_255)
         .draw(oSB, this.getPosX() + (int)(CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2 + 1 + iTranslateX, this.getPosY() + 1 + iTranslateY, CFG.PADDING - 1);
      ImageManager.getImage(Images.pix255_255_255)
         .draw(
            oSB,
            this.getPosX() + (int)(CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2 + 1 + iTranslateX,
            this.getPosY() - 1 - this.iFixPosY + this.iAvaragePosY + iTranslateY,
            CFG.PADDING - 1
         );
      ImageManager.getImage(Images.pix255_255_255)
         .draw(
            oSB,
            this.getPosX() + this.getGraphWidth() - 1 + iTranslateX,
            this.getPosY() + this.getHeight() - ((int)(CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2) - CFG.PADDING + 1 + iTranslateY,
            1,
            CFG.PADDING - 1
         );
      CFG.fontMain.getData().setScale(POINTS_TEXT_SCALE);
      CFG.drawText(
         oSB,
         "" + this.getMinPoint(),
         this.getPosX() + (int)(CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2 + 1 + (int)(2.0F * CFG.GUI_SCALE) + iTranslateX,
         this.getPosY()
            + this.getHeight()
            - ((int)(CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2)
            - (int)(2.0F * CFG.GUI_SCALE)
            - (int)(CFG.TEXT_HEIGHT * POINTS_TEXT_SCALE)
            + iTranslateY,
         DATA_COLOR
      );
      CFG.drawText(
         oSB,
         "" + this.iMaxPoint,
         this.getPosX() + (int)(CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2 + 1 + (int)(2.0F * CFG.GUI_SCALE) + iTranslateX,
         this.getPosY() + 1 + (int)(2.0F * CFG.GUI_SCALE) + iTranslateY,
         DATA_COLOR
      );
      CFG.drawText(
         oSB,
         this.bDecimal == 0 ? "" + (int)this.fAvaragePoint : "" + (int)this.fAvaragePoint + "." + (this.lessThanTen ? "0" + this.bDecimal : this.bDecimal),
         this.getPosX() + (int)(CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2 + 1 + (int)(2.0F * CFG.GUI_SCALE) + iTranslateX,
         this.getPosY() - (int)(2.0F * CFG.GUI_SCALE) - (int)(CFG.TEXT_HEIGHT * POINTS_TEXT_SCALE) - this.iFixPosY + this.iAvaragePosY - 1 + iTranslateY,
         DATA_COLOR
      );
      oSB.setColor(Color.WHITE);

      try {
         if (-this.iFixPosY + this.getHeight() - ((int)(CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2)
               - (this.getHeight() - ((int)(CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2))
                  * (100.0F * this.lData.get(this.iBestDescDataID).getPointY(this.iDescOfTurnID - this.lData.get(this.iBestDescDataID).getBeginTurnID()))
                  / (this.iMaxPoint - this.getMinPoint())
                  / 100.0F
            < (int)(CFG.TEXT_HEIGHT * POINTS_TEXT_SCALE) + CFG.PADDING * 2) {
            CFG.drawText(
               oSB,
               "" + this.lData.get(this.iBestDescDataID).getPointY(this.iDescOfTurnID - this.lData.get(this.iBestDescDataID).getBeginTurnID()),
               this.getPosX() + this.getGraphWidth() - (int)(2.0F * CFG.GUI_SCALE) - this.iBestDescDataTextWidth + iTranslateX,
               (int)(
                     this.getPosY() - this.iFixPosY + this.getHeight() - ((int)(CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2)
                        - (this.getHeight() - ((int)(CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2))
                           * (
                              100.0F
                                 * this.lData.get(this.iBestDescDataID).getPointY(this.iDescOfTurnID - this.lData.get(this.iBestDescDataID).getBeginTurnID())
                           )
                           / (this.iMaxPoint - this.getMinPoint())
                           / 100.0F
                        + (int)(2.0F * CFG.GUI_SCALE)
                  )
                  + iTranslateY,
               DATA_COLOR
            );
            if ((int)(CFG.TEXT_HEIGHT * POINTS_TEXT_SCALE) < CFG.CIV_FLAG_HEIGHT) {
               try {
                  CFG.game
                     .getCiv(this.lData.get(this.iBestDescDataID).getCivID())
                     .getFlag()
                     .draw(
                        oSB,
                        this.getPosX()
                           + this.getGraphWidth()
                           - (int)(2.0F * CFG.GUI_SCALE) * 2
                           - this.iBestDescDataTextWidth
                           - (int)Math.ceil(CFG.CIV_FLAG_WIDTH * (CFG.TEXT_HEIGHT * POINTS_TEXT_SCALE / CFG.CIV_FLAG_HEIGHT))
                           + iTranslateX,
                        (int)(
                              this.getPosY() - this.iFixPosY + this.getHeight() - ((int)(CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2)
                                 - (this.getHeight() - ((int)(CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2))
                                    * (
                                       100.0F
                                          * this.lData
                                             .get(this.iBestDescDataID)
                                             .getPointY(this.iDescOfTurnID - this.lData.get(this.iBestDescDataID).getBeginTurnID())
                                    )
                                    / (this.iMaxPoint - this.getMinPoint())
                                    / 100.0F
                                 + (int)(2.0F * CFG.GUI_SCALE)
                                 - CFG.game.getCiv(this.lData.get(this.iBestDescDataID).getCivID()).getFlag().getHeight()
                           )
                           + iTranslateY,
                        (int)Math.ceil(CFG.CIV_FLAG_WIDTH * (CFG.TEXT_HEIGHT * POINTS_TEXT_SCALE / CFG.CIV_FLAG_HEIGHT)),
                        (int)Math.ceil(CFG.CIV_FLAG_HEIGHT * (CFG.TEXT_HEIGHT * POINTS_TEXT_SCALE / CFG.CIV_FLAG_HEIGHT))
                     );
               } catch (IndexOutOfBoundsException var20) {
                  ImageManager.getImage(Images.randomCivilizationFlag)
                     .draw(
                        oSB,
                        this.getPosX()
                           + this.getGraphWidth()
                           - (int)(2.0F * CFG.GUI_SCALE) * 2
                           - this.iBestDescDataTextWidth
                           - (int)Math.ceil(CFG.CIV_FLAG_WIDTH * (CFG.TEXT_HEIGHT * POINTS_TEXT_SCALE / CFG.CIV_FLAG_HEIGHT))
                           + iTranslateX,
                        (int)(
                              this.getPosY() - this.iFixPosY + this.getHeight() - ((int)(CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2)
                                 - (this.getHeight() - ((int)(CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2))
                                    * (
                                       100.0F
                                          * this.lData
                                             .get(this.iBestDescDataID)
                                             .getPointY(this.iDescOfTurnID - this.lData.get(this.iBestDescDataID).getBeginTurnID())
                                    )
                                    / (this.iMaxPoint - this.getMinPoint())
                                    / 100.0F
                                 + (int)(2.0F * CFG.GUI_SCALE)
                                 - ImageManager.getImage(Images.randomCivilizationFlag).getHeight()
                           )
                           + iTranslateY,
                        (int)Math.ceil(CFG.CIV_FLAG_WIDTH * (CFG.TEXT_HEIGHT * POINTS_TEXT_SCALE / CFG.CIV_FLAG_HEIGHT)),
                        (int)Math.ceil(CFG.CIV_FLAG_HEIGHT * (CFG.TEXT_HEIGHT * POINTS_TEXT_SCALE / CFG.CIV_FLAG_HEIGHT))
                     );
               }

               ImageManager.getImage(Images.flag_rect)
                  .draw(
                     oSB,
                     this.getPosX()
                        + this.getGraphWidth()
                        - (int)(2.0F * CFG.GUI_SCALE) * 2
                        - this.iBestDescDataTextWidth
                        - (int)Math.ceil(CFG.CIV_FLAG_WIDTH * (CFG.TEXT_HEIGHT * POINTS_TEXT_SCALE / CFG.CIV_FLAG_HEIGHT))
                        + iTranslateX,
                     (int)(
                           this.getPosY() - this.iFixPosY + this.getHeight() - ((int)(CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2)
                              - (this.getHeight() - ((int)(CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2))
                                 * (
                                    100.0F
                                       * this.lData
                                          .get(this.iBestDescDataID)
                                          .getPointY(this.iDescOfTurnID - this.lData.get(this.iBestDescDataID).getBeginTurnID())
                                 )
                                 / (this.iMaxPoint - this.getMinPoint())
                                 / 100.0F
                              + (int)(2.0F * CFG.GUI_SCALE)
                              - CFG.CIV_FLAG_HEIGHT
                        )
                        + iTranslateY,
                     (int)Math.ceil(CFG.CIV_FLAG_WIDTH * (CFG.TEXT_HEIGHT * POINTS_TEXT_SCALE / CFG.CIV_FLAG_HEIGHT)),
                     (int)Math.ceil(CFG.CIV_FLAG_HEIGHT * (CFG.TEXT_HEIGHT * POINTS_TEXT_SCALE / CFG.CIV_FLAG_HEIGHT))
                  );
            } else {
               try {
                  CFG.game
                     .getCiv(this.lData.get(this.iBestDescDataID).getCivID())
                     .getFlag()
                     .draw(
                        oSB,
                        this.getPosX()
                           + this.getGraphWidth()
                           - (int)(2.0F * CFG.GUI_SCALE) * 2
                           - this.iBestDescDataTextWidth
                           - CFG.CIV_FLAG_WIDTH
                           + iTranslateX,
                        (int)(
                              this.getPosY() - this.iFixPosY + this.getHeight() - ((int)(CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2)
                                 - (this.getHeight() - ((int)(CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2))
                                    * (
                                       100.0F
                                          * this.lData
                                             .get(this.iBestDescDataID)
                                             .getPointY(this.iDescOfTurnID - this.lData.get(this.iBestDescDataID).getBeginTurnID())
                                    )
                                    / (this.iMaxPoint - this.getMinPoint())
                                    / 100.0F
                                 + (int)(2.0F * CFG.GUI_SCALE)
                                 + (int)(CFG.TEXT_HEIGHT * POINTS_TEXT_SCALE) / 2
                                 - CFG.CIV_FLAG_HEIGHT / 2
                           )
                           + iTranslateY
                     );
               } catch (IndexOutOfBoundsException var19) {
                  ImageManager.getImage(Images.randomCivilizationFlag)
                     .draw(
                        oSB,
                        this.getPosX()
                           + this.getGraphWidth()
                           - (int)(2.0F * CFG.GUI_SCALE) * 2
                           - this.iBestDescDataTextWidth
                           - CFG.CIV_FLAG_WIDTH
                           + iTranslateX,
                        (int)(
                              this.getPosY() - this.iFixPosY + this.getHeight() - ((int)(CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2)
                                 - (this.getHeight() - ((int)(CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2))
                                    * (
                                       100.0F
                                          * this.lData
                                             .get(this.iBestDescDataID)
                                             .getPointY(this.iDescOfTurnID - this.lData.get(this.iBestDescDataID).getBeginTurnID())
                                    )
                                    / (this.iMaxPoint - this.getMinPoint())
                                    / 100.0F
                                 + (int)(2.0F * CFG.GUI_SCALE)
                                 + (int)(CFG.TEXT_HEIGHT * POINTS_TEXT_SCALE) / 2
                                 - CFG.CIV_FLAG_HEIGHT / 2
                           )
                           + iTranslateY
                     );
               }

               ImageManager.getImage(Images.flag_rect)
                  .draw(
                     oSB,
                     this.getPosX() + this.getGraphWidth() - (int)(2.0F * CFG.GUI_SCALE) * 2 - this.iBestDescDataTextWidth - CFG.CIV_FLAG_WIDTH + iTranslateX,
                     (int)(
                           this.getPosY() - this.iFixPosY + this.getHeight() - ((int)(CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2)
                              - (this.getHeight() - ((int)(CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2))
                                 * (
                                    100.0F
                                       * this.lData
                                          .get(this.iBestDescDataID)
                                          .getPointY(this.iDescOfTurnID - this.lData.get(this.iBestDescDataID).getBeginTurnID())
                                 )
                                 / (this.iMaxPoint - this.getMinPoint())
                                 / 100.0F
                              + (int)(2.0F * CFG.GUI_SCALE)
                              + (int)(CFG.TEXT_HEIGHT * POINTS_TEXT_SCALE) / 2
                              - CFG.CIV_FLAG_HEIGHT / 2
                        )
                        + iTranslateY
                  );
            }
         } else {
            CFG.drawText(
               oSB,
               "" + this.lData.get(this.iBestDescDataID).getPointY(this.iDescOfTurnID - this.lData.get(this.iBestDescDataID).getBeginTurnID()),
               this.getPosX() + this.getGraphWidth() - (int)(2.0F * CFG.GUI_SCALE) - this.iBestDescDataTextWidth + iTranslateX,
               (int)(
                     this.getPosY() - this.iFixPosY + this.getHeight() - ((int)(CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2)
                        - (this.getHeight() - ((int)(CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2))
                           * (
                              100.0F
                                 * this.lData.get(this.iBestDescDataID).getPointY(this.iDescOfTurnID - this.lData.get(this.iBestDescDataID).getBeginTurnID())
                           )
                           / (this.iMaxPoint - this.getMinPoint())
                           / 100.0F
                        - (int)(2.0F * CFG.GUI_SCALE)
                        - (int)(CFG.TEXT_HEIGHT * POINTS_TEXT_SCALE)
                  )
                  + iTranslateY,
               DATA_COLOR
            );
            if ((int)(CFG.TEXT_HEIGHT * POINTS_TEXT_SCALE) < CFG.CIV_FLAG_HEIGHT) {
               try {
                  CFG.game
                     .getCiv(this.lData.get(this.iBestDescDataID).getCivID())
                     .getFlag()
                     .draw(
                        oSB,
                        this.getPosX()
                           + this.getGraphWidth()
                           - (int)(2.0F * CFG.GUI_SCALE) * 2
                           - this.iBestDescDataTextWidth
                           - (int)Math.ceil(CFG.CIV_FLAG_WIDTH * (CFG.TEXT_HEIGHT * POINTS_TEXT_SCALE / CFG.CIV_FLAG_HEIGHT))
                           + iTranslateX,
                        (int)(
                              this.getPosY() - this.iFixPosY + this.getHeight() - ((int)(CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2)
                                 - (this.getHeight() - ((int)(CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2))
                                    * (
                                       100.0F
                                          * this.lData
                                             .get(this.iBestDescDataID)
                                             .getPointY(this.iDescOfTurnID - this.lData.get(this.iBestDescDataID).getBeginTurnID())
                                    )
                                    / (this.iMaxPoint - this.getMinPoint())
                                    / 100.0F
                                 - (int)(2.0F * CFG.GUI_SCALE)
                                 - (int)(CFG.TEXT_HEIGHT * POINTS_TEXT_SCALE)
                                 - CFG.game.getCiv(this.lData.get(this.iBestDescDataID).getCivID()).getFlag().getHeight()
                           )
                           + iTranslateY,
                        (int)Math.ceil(CFG.CIV_FLAG_WIDTH * (CFG.TEXT_HEIGHT * POINTS_TEXT_SCALE / CFG.CIV_FLAG_HEIGHT)),
                        (int)Math.ceil(CFG.CIV_FLAG_HEIGHT * (CFG.TEXT_HEIGHT * POINTS_TEXT_SCALE / CFG.CIV_FLAG_HEIGHT))
                     );
               } catch (IndexOutOfBoundsException var18) {
                  ImageManager.getImage(Images.randomCivilizationFlag)
                     .draw(
                        oSB,
                        this.getPosX()
                           + this.getGraphWidth()
                           - (int)(2.0F * CFG.GUI_SCALE) * 2
                           - this.iBestDescDataTextWidth
                           - (int)Math.ceil(CFG.CIV_FLAG_WIDTH * (CFG.TEXT_HEIGHT * POINTS_TEXT_SCALE / CFG.CIV_FLAG_HEIGHT))
                           + iTranslateX,
                        (int)(
                              this.getPosY() - this.iFixPosY + this.getHeight() - ((int)(CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2)
                                 - (this.getHeight() - ((int)(CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2))
                                    * (
                                       100.0F
                                          * this.lData
                                             .get(this.iBestDescDataID)
                                             .getPointY(this.iDescOfTurnID - this.lData.get(this.iBestDescDataID).getBeginTurnID())
                                    )
                                    / (this.iMaxPoint - this.getMinPoint())
                                    / 100.0F
                                 - (int)(2.0F * CFG.GUI_SCALE)
                                 - (int)(CFG.TEXT_HEIGHT * POINTS_TEXT_SCALE)
                                 - ImageManager.getImage(Images.randomCivilizationFlag).getHeight()
                           )
                           + iTranslateY,
                        (int)Math.ceil(CFG.CIV_FLAG_WIDTH * (CFG.TEXT_HEIGHT * POINTS_TEXT_SCALE / CFG.CIV_FLAG_HEIGHT)),
                        (int)Math.ceil(CFG.CIV_FLAG_HEIGHT * (CFG.TEXT_HEIGHT * POINTS_TEXT_SCALE / CFG.CIV_FLAG_HEIGHT))
                     );
               }

               ImageManager.getImage(Images.flag_rect)
                  .draw(
                     oSB,
                     this.getPosX()
                        + this.getGraphWidth()
                        - (int)(2.0F * CFG.GUI_SCALE) * 2
                        - this.iBestDescDataTextWidth
                        - (int)Math.ceil(CFG.CIV_FLAG_WIDTH * (CFG.TEXT_HEIGHT * POINTS_TEXT_SCALE / CFG.CIV_FLAG_HEIGHT))
                        + iTranslateX,
                     (int)(
                           this.getPosY() - this.iFixPosY + this.getHeight() - ((int)(CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2)
                              - (this.getHeight() - ((int)(CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2))
                                 * (
                                    100.0F
                                       * this.lData
                                          .get(this.iBestDescDataID)
                                          .getPointY(this.iDescOfTurnID - this.lData.get(this.iBestDescDataID).getBeginTurnID())
                                 )
                                 / (this.iMaxPoint - this.getMinPoint())
                                 / 100.0F
                              - (int)(2.0F * CFG.GUI_SCALE)
                              - (int)(CFG.TEXT_HEIGHT * POINTS_TEXT_SCALE)
                              - CFG.CIV_FLAG_HEIGHT
                        )
                        + iTranslateY,
                     (int)Math.ceil(CFG.CIV_FLAG_WIDTH * (CFG.TEXT_HEIGHT * POINTS_TEXT_SCALE / CFG.CIV_FLAG_HEIGHT)),
                     (int)Math.ceil(CFG.CIV_FLAG_HEIGHT * (CFG.TEXT_HEIGHT * POINTS_TEXT_SCALE / CFG.CIV_FLAG_HEIGHT))
                  );
            } else {
               try {
                  CFG.game
                     .getCiv(this.lData.get(this.iBestDescDataID).getCivID())
                     .getFlag()
                     .draw(
                        oSB,
                        this.getPosX()
                           + this.getGraphWidth()
                           - (int)(2.0F * CFG.GUI_SCALE) * 2
                           - this.iBestDescDataTextWidth
                           - CFG.CIV_FLAG_WIDTH
                           + iTranslateX,
                        (int)(
                              this.getPosY()
                                    - CFG.game.getCiv(this.lData.get(this.iBestDescDataID).getCivID()).getFlag().getHeight()
                                    - this.iFixPosY
                                    + this.getHeight()
                                    - ((int)(CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2)
                                 - (this.getHeight() - ((int)(CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2))
                                    * (
                                       100.0F
                                          * this.lData
                                             .get(this.iBestDescDataID)
                                             .getPointY(this.iDescOfTurnID - this.lData.get(this.iBestDescDataID).getBeginTurnID())
                                    )
                                    / (this.iMaxPoint - this.getMinPoint())
                                    / 100.0F
                                 - (int)(2.0F * CFG.GUI_SCALE)
                                 - (int)(CFG.TEXT_HEIGHT * POINTS_TEXT_SCALE) / 2
                                 - CFG.CIV_FLAG_HEIGHT / 2
                           )
                           + iTranslateY,
                        CFG.CIV_FLAG_WIDTH,
                        CFG.CIV_FLAG_HEIGHT
                     );
               } catch (IndexOutOfBoundsException var17) {
                  ImageManager.getImage(Images.randomCivilizationFlag)
                     .draw(
                        oSB,
                        this.getPosX()
                           + this.getGraphWidth()
                           - (int)(2.0F * CFG.GUI_SCALE) * 2
                           - this.iBestDescDataTextWidth
                           - CFG.CIV_FLAG_WIDTH
                           + iTranslateX,
                        (int)(
                              this.getPosY()
                                    - ImageManager.getImage(Images.randomCivilizationFlag).getHeight()
                                    - this.iFixPosY
                                    + this.getHeight()
                                    - ((int)(CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2)
                                 - (this.getHeight() - ((int)(CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2))
                                    * (
                                       100.0F
                                          * this.lData
                                             .get(this.iBestDescDataID)
                                             .getPointY(this.iDescOfTurnID - this.lData.get(this.iBestDescDataID).getBeginTurnID())
                                    )
                                    / (this.iMaxPoint - this.getMinPoint())
                                    / 100.0F
                                 - (int)(2.0F * CFG.GUI_SCALE)
                                 - (int)(CFG.TEXT_HEIGHT * POINTS_TEXT_SCALE) / 2
                                 - CFG.CIV_FLAG_HEIGHT / 2
                           )
                           + iTranslateY,
                        CFG.CIV_FLAG_WIDTH,
                        CFG.CIV_FLAG_HEIGHT
                     );
               }

               ImageManager.getImage(Images.flag_rect)
                  .draw(
                     oSB,
                     this.getPosX() + this.getGraphWidth() - (int)(2.0F * CFG.GUI_SCALE) * 2 - this.iBestDescDataTextWidth - CFG.CIV_FLAG_WIDTH + iTranslateX,
                     (int)(
                           this.getPosY() - this.iFixPosY + this.getHeight() - ((int)(CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2)
                              - (this.getHeight() - ((int)(CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2))
                                 * (
                                    100.0F
                                       * this.lData
                                          .get(this.iBestDescDataID)
                                          .getPointY(this.iDescOfTurnID - this.lData.get(this.iBestDescDataID).getBeginTurnID())
                                 )
                                 / (this.iMaxPoint - this.getMinPoint())
                                 / 100.0F
                              - (int)(2.0F * CFG.GUI_SCALE)
                              - (int)(CFG.TEXT_HEIGHT * POINTS_TEXT_SCALE) / 2
                              - CFG.CIV_FLAG_HEIGHT / 2
                        )
                        + iTranslateY
                  );
            }
         }

         if (-this.iFixPosY + this.getHeight() - ((int)(CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2)
               - (this.getHeight() - ((int)(CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2))
                  * (100.0F * this.lData.get(this.iWorstDescDataID).getPointY(this.iDescOfTurnID - this.lData.get(this.iWorstDescDataID).getBeginTurnID()))
                  / (this.iMaxPoint - this.getMinPoint())
                  / 100.0F
            > this.getHeight() - ((int)(CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2) - (int)(CFG.TEXT_HEIGHT * POINTS_TEXT_SCALE) - CFG.PADDING * 2) {
            CFG.drawText(
               oSB,
               "" + this.lData.get(this.iWorstDescDataID).getPointY(this.iDescOfTurnID - this.lData.get(this.iWorstDescDataID).getBeginTurnID()),
               this.getPosX() + this.getGraphWidth() - (int)(2.0F * CFG.GUI_SCALE) - this.iWorstDescDataTextWidth + iTranslateX,
               (int)(
                     this.getPosY() - this.iFixPosY + this.getHeight() - ((int)(CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2)
                        - (this.getHeight() - ((int)(CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2))
                           * (
                              100.0F
                                 * this.lData.get(this.iWorstDescDataID).getPointY(this.iDescOfTurnID - this.lData.get(this.iWorstDescDataID).getBeginTurnID())
                           )
                           / (this.iMaxPoint - this.getMinPoint())
                           / 100.0F
                        - (int)(2.0F * CFG.GUI_SCALE)
                        - (int)(CFG.TEXT_HEIGHT * POINTS_TEXT_SCALE)
                  )
                  + iTranslateY,
               DATA_COLOR
            );
            if ((int)(CFG.TEXT_HEIGHT * POINTS_TEXT_SCALE) < CFG.CIV_FLAG_HEIGHT) {
               try {
                  CFG.game
                     .getCiv(this.lData.get(this.iWorstDescDataID).getCivID())
                     .getFlag()
                     .draw(
                        oSB,
                        this.getPosX()
                           + this.getGraphWidth()
                           - (int)(2.0F * CFG.GUI_SCALE) * 2
                           - this.iWorstDescDataTextWidth
                           - (int)Math.ceil(CFG.CIV_FLAG_WIDTH * (CFG.TEXT_HEIGHT * POINTS_TEXT_SCALE / CFG.CIV_FLAG_HEIGHT))
                           + iTranslateX,
                        (int)(
                              this.getPosY() - this.iFixPosY + this.getHeight() - ((int)(CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2)
                                 - (this.getHeight() - ((int)(CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2))
                                    * (
                                       100.0F
                                          * this.lData
                                             .get(this.iWorstDescDataID)
                                             .getPointY(this.iDescOfTurnID - this.lData.get(this.iWorstDescDataID).getBeginTurnID())
                                    )
                                    / (this.iMaxPoint - this.getMinPoint())
                                    / 100.0F
                                 - (int)(2.0F * CFG.GUI_SCALE)
                                 - (int)(CFG.TEXT_HEIGHT * POINTS_TEXT_SCALE)
                                 - CFG.game.getCiv(this.lData.get(this.iWorstDescDataID).getCivID()).getFlag().getHeight()
                           )
                           + iTranslateY,
                        (int)Math.ceil(CFG.CIV_FLAG_WIDTH * (CFG.TEXT_HEIGHT * POINTS_TEXT_SCALE / CFG.CIV_FLAG_HEIGHT)),
                        (int)Math.ceil(CFG.CIV_FLAG_HEIGHT * (CFG.TEXT_HEIGHT * POINTS_TEXT_SCALE / CFG.CIV_FLAG_HEIGHT))
                     );
               } catch (IndexOutOfBoundsException var16) {
                  ImageManager.getImage(Images.randomCivilizationFlag)
                     .draw(
                        oSB,
                        this.getPosX()
                           + this.getGraphWidth()
                           - (int)(2.0F * CFG.GUI_SCALE) * 2
                           - this.iWorstDescDataTextWidth
                           - (int)Math.ceil(CFG.CIV_FLAG_WIDTH * (CFG.TEXT_HEIGHT * POINTS_TEXT_SCALE / CFG.CIV_FLAG_HEIGHT))
                           + iTranslateX,
                        (int)(
                              this.getPosY() - this.iFixPosY + this.getHeight() - ((int)(CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2)
                                 - (this.getHeight() - ((int)(CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2))
                                    * (
                                       100.0F
                                          * this.lData
                                             .get(this.iWorstDescDataID)
                                             .getPointY(this.iDescOfTurnID - this.lData.get(this.iWorstDescDataID).getBeginTurnID())
                                    )
                                    / (this.iMaxPoint - this.getMinPoint())
                                    / 100.0F
                                 - (int)(2.0F * CFG.GUI_SCALE)
                                 - (int)(CFG.TEXT_HEIGHT * POINTS_TEXT_SCALE)
                                 - ImageManager.getImage(Images.randomCivilizationFlag).getHeight()
                           )
                           + iTranslateY,
                        (int)Math.ceil(CFG.CIV_FLAG_WIDTH * (CFG.TEXT_HEIGHT * POINTS_TEXT_SCALE / CFG.CIV_FLAG_HEIGHT)),
                        (int)Math.ceil(CFG.CIV_FLAG_HEIGHT * (CFG.TEXT_HEIGHT * POINTS_TEXT_SCALE / CFG.CIV_FLAG_HEIGHT))
                     );
               }

               ImageManager.getImage(Images.flag_rect)
                  .draw(
                     oSB,
                     this.getPosX()
                        + this.getGraphWidth()
                        - (int)(2.0F * CFG.GUI_SCALE) * 2
                        - this.iWorstDescDataTextWidth
                        - (int)Math.ceil(CFG.CIV_FLAG_WIDTH * (CFG.TEXT_HEIGHT * POINTS_TEXT_SCALE / CFG.CIV_FLAG_HEIGHT))
                        + iTranslateX,
                     (int)(
                           this.getPosY() - this.iFixPosY + this.getHeight() - ((int)(CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2)
                              - (this.getHeight() - ((int)(CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2))
                                 * (
                                    100.0F
                                       * this.lData
                                          .get(this.iWorstDescDataID)
                                          .getPointY(this.iDescOfTurnID - this.lData.get(this.iWorstDescDataID).getBeginTurnID())
                                 )
                                 / (this.iMaxPoint - this.getMinPoint())
                                 / 100.0F
                              - (int)(2.0F * CFG.GUI_SCALE)
                              - (int)(CFG.TEXT_HEIGHT * POINTS_TEXT_SCALE)
                              - CFG.CIV_FLAG_HEIGHT
                        )
                        + iTranslateY,
                     (int)Math.ceil(CFG.CIV_FLAG_WIDTH * (CFG.TEXT_HEIGHT * POINTS_TEXT_SCALE / CFG.CIV_FLAG_HEIGHT)),
                     (int)Math.ceil(CFG.CIV_FLAG_HEIGHT * (CFG.TEXT_HEIGHT * POINTS_TEXT_SCALE / CFG.CIV_FLAG_HEIGHT))
                  );
            } else {
               try {
                  CFG.game
                     .getCiv(this.lData.get(this.iWorstDescDataID).getCivID())
                     .getFlag()
                     .draw(
                        oSB,
                        this.getPosX()
                           + this.getGraphWidth()
                           - (int)(2.0F * CFG.GUI_SCALE) * 2
                           - this.iWorstDescDataTextWidth
                           - CFG.CIV_FLAG_WIDTH
                           + iTranslateX,
                        (int)(
                              this.getPosY()
                                    - CFG.game.getCiv(this.lData.get(this.iWorstDescDataID).getCivID()).getFlag().getHeight()
                                    - this.iFixPosY
                                    + this.getHeight()
                                    - ((int)(CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2)
                                 - (this.getHeight() - ((int)(CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2))
                                    * (
                                       100.0F
                                          * this.lData
                                             .get(this.iWorstDescDataID)
                                             .getPointY(this.iDescOfTurnID - this.lData.get(this.iWorstDescDataID).getBeginTurnID())
                                    )
                                    / (this.iMaxPoint - this.getMinPoint())
                                    / 100.0F
                                 - (int)(2.0F * CFG.GUI_SCALE)
                                 - (int)(CFG.TEXT_HEIGHT * POINTS_TEXT_SCALE) / 2
                                 - CFG.CIV_FLAG_HEIGHT / 2
                           )
                           + iTranslateY,
                        CFG.CIV_FLAG_WIDTH,
                        CFG.CIV_FLAG_HEIGHT
                     );
               } catch (IndexOutOfBoundsException var15) {
                  ImageManager.getImage(Images.randomCivilizationFlag)
                     .draw(
                        oSB,
                        this.getPosX()
                           + this.getGraphWidth()
                           - (int)(2.0F * CFG.GUI_SCALE) * 2
                           - this.iWorstDescDataTextWidth
                           - CFG.CIV_FLAG_WIDTH
                           + iTranslateX,
                        (int)(
                              this.getPosY()
                                    - ImageManager.getImage(Images.randomCivilizationFlag).getHeight()
                                    - this.iFixPosY
                                    + this.getHeight()
                                    - ((int)(CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2)
                                 - (this.getHeight() - ((int)(CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2))
                                    * (
                                       100.0F
                                          * this.lData
                                             .get(this.iWorstDescDataID)
                                             .getPointY(this.iDescOfTurnID - this.lData.get(this.iWorstDescDataID).getBeginTurnID())
                                    )
                                    / (this.iMaxPoint - this.getMinPoint())
                                    / 100.0F
                                 - (int)(2.0F * CFG.GUI_SCALE)
                                 - (int)(CFG.TEXT_HEIGHT * POINTS_TEXT_SCALE) / 2
                                 - CFG.CIV_FLAG_HEIGHT / 2
                           )
                           + iTranslateY,
                        CFG.CIV_FLAG_WIDTH,
                        CFG.CIV_FLAG_HEIGHT
                     );
               }

               ImageManager.getImage(Images.flag_rect)
                  .draw(
                     oSB,
                     this.getPosX() + this.getGraphWidth() - (int)(2.0F * CFG.GUI_SCALE) * 2 - this.iWorstDescDataTextWidth - CFG.CIV_FLAG_WIDTH + iTranslateX,
                     (int)(
                           this.getPosY() - this.iFixPosY + this.getHeight() - ((int)(CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2)
                              - (this.getHeight() - ((int)(CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2))
                                 * (
                                    100.0F
                                       * this.lData
                                          .get(this.iWorstDescDataID)
                                          .getPointY(this.iDescOfTurnID - this.lData.get(this.iWorstDescDataID).getBeginTurnID())
                                 )
                                 / (this.iMaxPoint - this.getMinPoint())
                                 / 100.0F
                              - (int)(2.0F * CFG.GUI_SCALE)
                              - (int)(CFG.TEXT_HEIGHT * POINTS_TEXT_SCALE) / 2
                              - CFG.CIV_FLAG_HEIGHT / 2
                        )
                        + iTranslateY
                  );
            }
         } else {
            CFG.drawText(
               oSB,
               "" + this.lData.get(this.iWorstDescDataID).getPointY(this.iDescOfTurnID - this.lData.get(this.iWorstDescDataID).getBeginTurnID()),
               this.getPosX() + this.getGraphWidth() - (int)(2.0F * CFG.GUI_SCALE) - this.iWorstDescDataTextWidth + iTranslateX,
               (int)(
                     this.getPosY() - this.iFixPosY + this.getHeight() - ((int)(CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2)
                        - (this.getHeight() - ((int)(CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2))
                           * (
                              100.0F
                                 * this.lData.get(this.iWorstDescDataID).getPointY(this.iDescOfTurnID - this.lData.get(this.iWorstDescDataID).getBeginTurnID())
                           )
                           / (this.iMaxPoint - this.getMinPoint())
                           / 100.0F
                        + (int)(2.0F * CFG.GUI_SCALE)
                  )
                  + iTranslateY,
               DATA_COLOR
            );
            if ((int)(CFG.TEXT_HEIGHT * POINTS_TEXT_SCALE) < CFG.CIV_FLAG_HEIGHT) {
               try {
                  CFG.game
                     .getCiv(this.lData.get(this.iWorstDescDataID).getCivID())
                     .getFlag()
                     .draw(
                        oSB,
                        this.getPosX()
                           + this.getGraphWidth()
                           - (int)(2.0F * CFG.GUI_SCALE) * 2
                           - this.iWorstDescDataTextWidth
                           - (int)Math.ceil(CFG.CIV_FLAG_WIDTH * (CFG.TEXT_HEIGHT * POINTS_TEXT_SCALE / CFG.CIV_FLAG_HEIGHT))
                           + iTranslateX,
                        (int)(
                              this.getPosY() - this.iFixPosY + this.getHeight() - ((int)(CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2)
                                 - (this.getHeight() - ((int)(CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2))
                                    * (
                                       100.0F
                                          * this.lData
                                             .get(this.iWorstDescDataID)
                                             .getPointY(this.iDescOfTurnID - this.lData.get(this.iWorstDescDataID).getBeginTurnID())
                                    )
                                    / (this.iMaxPoint - this.getMinPoint())
                                    / 100.0F
                                 + (int)(2.0F * CFG.GUI_SCALE)
                                 - CFG.game.getCiv(this.lData.get(this.iWorstDescDataID).getCivID()).getFlag().getHeight()
                           )
                           + iTranslateY,
                        (int)Math.ceil(CFG.CIV_FLAG_WIDTH * (CFG.TEXT_HEIGHT * POINTS_TEXT_SCALE / CFG.CIV_FLAG_HEIGHT)),
                        (int)Math.ceil(CFG.CIV_FLAG_HEIGHT * (CFG.TEXT_HEIGHT * POINTS_TEXT_SCALE / CFG.CIV_FLAG_HEIGHT))
                     );
               } catch (IndexOutOfBoundsException var14) {
                  ImageManager.getImage(Images.randomCivilizationFlag)
                     .draw(
                        oSB,
                        this.getPosX()
                           + this.getGraphWidth()
                           - (int)(2.0F * CFG.GUI_SCALE) * 2
                           - this.iWorstDescDataTextWidth
                           - (int)Math.ceil(CFG.CIV_FLAG_WIDTH * (CFG.TEXT_HEIGHT * POINTS_TEXT_SCALE / CFG.CIV_FLAG_HEIGHT))
                           + iTranslateX,
                        (int)(
                              this.getPosY() - this.iFixPosY + this.getHeight() - ((int)(CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2)
                                 - (this.getHeight() - ((int)(CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2))
                                    * (
                                       100.0F
                                          * this.lData
                                             .get(this.iWorstDescDataID)
                                             .getPointY(this.iDescOfTurnID - this.lData.get(this.iWorstDescDataID).getBeginTurnID())
                                    )
                                    / (this.iMaxPoint - this.getMinPoint())
                                    / 100.0F
                                 + (int)(2.0F * CFG.GUI_SCALE)
                                 - ImageManager.getImage(Images.randomCivilizationFlag).getHeight()
                           )
                           + iTranslateY,
                        (int)Math.ceil(CFG.CIV_FLAG_WIDTH * (CFG.TEXT_HEIGHT * POINTS_TEXT_SCALE / CFG.CIV_FLAG_HEIGHT)),
                        (int)Math.ceil(CFG.CIV_FLAG_HEIGHT * (CFG.TEXT_HEIGHT * POINTS_TEXT_SCALE / CFG.CIV_FLAG_HEIGHT))
                     );
               }

               ImageManager.getImage(Images.flag_rect)
                  .draw(
                     oSB,
                     this.getPosX()
                        + this.getGraphWidth()
                        - (int)(2.0F * CFG.GUI_SCALE) * 2
                        - this.iWorstDescDataTextWidth
                        - (int)Math.ceil(CFG.CIV_FLAG_WIDTH * (CFG.TEXT_HEIGHT * POINTS_TEXT_SCALE / CFG.CIV_FLAG_HEIGHT))
                        + iTranslateX,
                     (int)(
                           this.getPosY() - this.iFixPosY + this.getHeight() - ((int)(CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2)
                              - (this.getHeight() - ((int)(CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2))
                                 * (
                                    100.0F
                                       * this.lData
                                          .get(this.iWorstDescDataID)
                                          .getPointY(this.iDescOfTurnID - this.lData.get(this.iWorstDescDataID).getBeginTurnID())
                                 )
                                 / (this.iMaxPoint - this.getMinPoint())
                                 / 100.0F
                              + (int)(2.0F * CFG.GUI_SCALE)
                              - CFG.CIV_FLAG_HEIGHT
                        )
                        + iTranslateY,
                     (int)Math.ceil(CFG.CIV_FLAG_WIDTH * (CFG.TEXT_HEIGHT * POINTS_TEXT_SCALE / CFG.CIV_FLAG_HEIGHT)),
                     (int)Math.ceil(CFG.CIV_FLAG_HEIGHT * (CFG.TEXT_HEIGHT * POINTS_TEXT_SCALE / CFG.CIV_FLAG_HEIGHT))
                  );
            } else {
               try {
                  CFG.game
                     .getCiv(this.lData.get(this.iWorstDescDataID).getCivID())
                     .getFlag()
                     .draw(
                        oSB,
                        this.getPosX()
                           + this.getGraphWidth()
                           - (int)(2.0F * CFG.GUI_SCALE) * 2
                           - this.iWorstDescDataTextWidth
                           - CFG.CIV_FLAG_WIDTH
                           + iTranslateX,
                        (int)(
                              this.getPosY()
                                    - CFG.game.getCiv(this.lData.get(this.iWorstDescDataID).getCivID()).getFlag().getHeight()
                                    - this.iFixPosY
                                    + this.getHeight()
                                    - ((int)(CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2)
                                 - (this.getHeight() - ((int)(CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2))
                                    * (
                                       100.0F
                                          * this.lData
                                             .get(this.iWorstDescDataID)
                                             .getPointY(this.iDescOfTurnID - this.lData.get(this.iWorstDescDataID).getBeginTurnID())
                                    )
                                    / (this.iMaxPoint - this.getMinPoint())
                                    / 100.0F
                                 + (int)(2.0F * CFG.GUI_SCALE)
                                 + (int)(CFG.TEXT_HEIGHT * POINTS_TEXT_SCALE) / 2
                                 - CFG.CIV_FLAG_HEIGHT / 2
                           )
                           + iTranslateY,
                        CFG.CIV_FLAG_WIDTH,
                        CFG.CIV_FLAG_HEIGHT
                     );
               } catch (IndexOutOfBoundsException var13) {
                  ImageManager.getImage(Images.randomCivilizationFlag)
                     .draw(
                        oSB,
                        this.getPosX()
                           + this.getGraphWidth()
                           - (int)(2.0F * CFG.GUI_SCALE) * 2
                           - this.iWorstDescDataTextWidth
                           - CFG.CIV_FLAG_WIDTH
                           + iTranslateX,
                        (int)(
                              this.getPosY()
                                    - ImageManager.getImage(Images.randomCivilizationFlag).getHeight()
                                    - this.iFixPosY
                                    + this.getHeight()
                                    - ((int)(CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2)
                                 - (this.getHeight() - ((int)(CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2))
                                    * (
                                       100.0F
                                          * this.lData
                                             .get(this.iWorstDescDataID)
                                             .getPointY(this.iDescOfTurnID - this.lData.get(this.iWorstDescDataID).getBeginTurnID())
                                    )
                                    / (this.iMaxPoint - this.getMinPoint())
                                    / 100.0F
                                 + (int)(2.0F * CFG.GUI_SCALE)
                                 + (int)(CFG.TEXT_HEIGHT * POINTS_TEXT_SCALE) / 2
                                 - CFG.CIV_FLAG_HEIGHT / 2
                           )
                           + iTranslateY,
                        CFG.CIV_FLAG_WIDTH,
                        CFG.CIV_FLAG_HEIGHT
                     );
               }

               ImageManager.getImage(Images.flag_rect)
                  .draw(
                     oSB,
                     this.getPosX() + this.getGraphWidth() - (int)(2.0F * CFG.GUI_SCALE) * 2 - this.iWorstDescDataTextWidth - CFG.CIV_FLAG_WIDTH + iTranslateX,
                     (int)(
                           this.getPosY() - this.iFixPosY + this.getHeight() - ((int)(CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2)
                              - (this.getHeight() - ((int)(CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2))
                                 * (
                                    100.0F
                                       * this.lData
                                          .get(this.iWorstDescDataID)
                                          .getPointY(this.iDescOfTurnID - this.lData.get(this.iWorstDescDataID).getBeginTurnID())
                                 )
                                 / (this.iMaxPoint - this.getMinPoint())
                                 / 100.0F
                              + (int)(2.0F * CFG.GUI_SCALE)
                              + (int)(CFG.TEXT_HEIGHT * POINTS_TEXT_SCALE) / 2
                              - CFG.CIV_FLAG_HEIGHT / 2
                        )
                        + iTranslateY
                  );
            }
         }
      } catch (ArithmeticException var21) {
      }

      CFG.fontMain.getData().setScale(1.0F);
      oSB.setColor(Color.WHITE);
      if ((int)(CFG.TEXT_HEIGHT * POINTS_TEXT_SCALE) < CFG.CIV_FLAG_HEIGHT) {
         if (this.iMinPoint <= 0) {
            try {
               CFG.game
                  .getCiv(this.iWorstCivID)
                  .getFlag()
                  .draw(
                     oSB,
                     this.getPosX() + (int)(CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2 + 1 + (int)(2.0F * CFG.GUI_SCALE) * 2 + this.iMinTextWidth + iTranslateX,
                     this.getPosY()
                        + this.getHeight()
                        - ((int)(CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2)
                        - (int)(2.0F * CFG.GUI_SCALE)
                        - (int)(CFG.TEXT_HEIGHT * POINTS_TEXT_SCALE)
                        - CFG.game.getCiv(this.iWorstCivID).getFlag().getHeight()
                        + iTranslateY,
                     (int)Math.ceil(CFG.CIV_FLAG_WIDTH * (CFG.TEXT_HEIGHT * POINTS_TEXT_SCALE / CFG.CIV_FLAG_HEIGHT)),
                     (int)Math.ceil(CFG.CIV_FLAG_HEIGHT * (CFG.TEXT_HEIGHT * POINTS_TEXT_SCALE / CFG.CIV_FLAG_HEIGHT))
                  );
            } catch (IndexOutOfBoundsException var12) {
               ImageManager.getImage(Images.randomCivilizationFlag)
                  .draw(
                     oSB,
                     this.getPosX() + (int)(CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2 + 1 + (int)(2.0F * CFG.GUI_SCALE) * 2 + this.iMinTextWidth + iTranslateX,
                     this.getPosY()
                        + this.getHeight()
                        - ((int)(CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2)
                        - (int)(2.0F * CFG.GUI_SCALE)
                        - (int)(CFG.TEXT_HEIGHT * POINTS_TEXT_SCALE)
                        - ImageManager.getImage(Images.randomCivilizationFlag).getHeight()
                        + iTranslateY,
                     (int)Math.ceil(CFG.CIV_FLAG_WIDTH * (CFG.TEXT_HEIGHT * POINTS_TEXT_SCALE / CFG.CIV_FLAG_HEIGHT)),
                     (int)Math.ceil(CFG.CIV_FLAG_HEIGHT * (CFG.TEXT_HEIGHT * POINTS_TEXT_SCALE / CFG.CIV_FLAG_HEIGHT))
                  );
            }

            ImageManager.getImage(Images.flag_rect)
               .draw(
                  oSB,
                  this.getPosX() + (int)(CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2 + 1 + (int)(2.0F * CFG.GUI_SCALE) * 2 + this.iMinTextWidth + iTranslateX,
                  this.getPosY()
                     + this.getHeight()
                     - ((int)(CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2)
                     - (int)(2.0F * CFG.GUI_SCALE)
                     - (int)(CFG.TEXT_HEIGHT * POINTS_TEXT_SCALE)
                     - CFG.CIV_FLAG_HEIGHT
                     + iTranslateY,
                  (int)Math.ceil(CFG.CIV_FLAG_WIDTH * (CFG.TEXT_HEIGHT * POINTS_TEXT_SCALE / CFG.CIV_FLAG_HEIGHT)),
                  (int)Math.ceil(CFG.CIV_FLAG_HEIGHT * (CFG.TEXT_HEIGHT * POINTS_TEXT_SCALE / CFG.CIV_FLAG_HEIGHT))
               );
         }

         try {
            CFG.game
               .getCiv(this.iBestCivID)
               .getFlag()
               .draw(
                  oSB,
                  this.getPosX() + (int)(CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2 + 1 + (int)(2.0F * CFG.GUI_SCALE) * 2 + this.iMaxTextWidth + iTranslateX,
                  this.getPosY() + 1 + (int)(2.0F * CFG.GUI_SCALE) - CFG.game.getCiv(this.iBestCivID).getFlag().getHeight() + iTranslateY,
                  (int)Math.ceil(CFG.CIV_FLAG_WIDTH * (CFG.TEXT_HEIGHT * POINTS_TEXT_SCALE / CFG.CIV_FLAG_HEIGHT)),
                  (int)Math.ceil(CFG.CIV_FLAG_HEIGHT * (CFG.TEXT_HEIGHT * POINTS_TEXT_SCALE / CFG.CIV_FLAG_HEIGHT))
               );
         } catch (IndexOutOfBoundsException var11) {
            ImageManager.getImage(Images.randomCivilizationFlag)
               .draw(
                  oSB,
                  this.getPosX() + (int)(CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2 + 1 + (int)(2.0F * CFG.GUI_SCALE) * 2 + this.iMaxTextWidth + iTranslateX,
                  this.getPosY() + 1 + (int)(2.0F * CFG.GUI_SCALE) - ImageManager.getImage(Images.randomCivilizationFlag).getHeight() + iTranslateY,
                  (int)Math.ceil(CFG.CIV_FLAG_WIDTH * (CFG.TEXT_HEIGHT * POINTS_TEXT_SCALE / CFG.CIV_FLAG_HEIGHT)),
                  (int)Math.ceil(CFG.CIV_FLAG_HEIGHT * (CFG.TEXT_HEIGHT * POINTS_TEXT_SCALE / CFG.CIV_FLAG_HEIGHT))
               );
         }

         ImageManager.getImage(Images.flag_rect)
            .draw(
               oSB,
               this.getPosX() + (int)(CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2 + 1 + (int)(2.0F * CFG.GUI_SCALE) * 2 + this.iMaxTextWidth + iTranslateX,
               this.getPosY() + 1 + (int)(2.0F * CFG.GUI_SCALE) - CFG.CIV_FLAG_HEIGHT + iTranslateY,
               (int)Math.ceil(CFG.CIV_FLAG_WIDTH * (CFG.TEXT_HEIGHT * POINTS_TEXT_SCALE / CFG.CIV_FLAG_HEIGHT)),
               (int)Math.ceil(CFG.CIV_FLAG_HEIGHT * (CFG.TEXT_HEIGHT * POINTS_TEXT_SCALE / CFG.CIV_FLAG_HEIGHT))
            );
      } else {
         if (this.iMinPoint <= 0) {
            try {
               CFG.game
                  .getCiv(this.iWorstCivID)
                  .getFlag()
                  .draw(
                     oSB,
                     this.getPosX() + (int)(CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2 + 1 + (int)(2.0F * CFG.GUI_SCALE) * 2 + this.iMinTextWidth + iTranslateX,
                     this.getPosY()
                        - CFG.game.getCiv(this.iWorstCivID).getFlag().getHeight()
                        + this.getHeight()
                        - ((int)(CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2)
                        - (int)(2.0F * CFG.GUI_SCALE)
                        - (int)(CFG.TEXT_HEIGHT * POINTS_TEXT_SCALE) / 2
                        - CFG.CIV_FLAG_HEIGHT / 2
                        + iTranslateY,
                     CFG.CIV_FLAG_WIDTH,
                     CFG.CIV_FLAG_HEIGHT
                  );
            } catch (IndexOutOfBoundsException var10) {
               ImageManager.getImage(Images.randomCivilizationFlag)
                  .draw(
                     oSB,
                     this.getPosX() + (int)(CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2 + 1 + (int)(2.0F * CFG.GUI_SCALE) * 2 + this.iMinTextWidth + iTranslateX,
                     this.getPosY()
                        - ImageManager.getImage(Images.randomCivilizationFlag).getHeight()
                        + this.getHeight()
                        - ((int)(CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2)
                        - (int)(2.0F * CFG.GUI_SCALE)
                        - (int)(CFG.TEXT_HEIGHT * POINTS_TEXT_SCALE) / 2
                        - CFG.CIV_FLAG_HEIGHT / 2
                        + iTranslateY,
                     CFG.CIV_FLAG_WIDTH,
                     CFG.CIV_FLAG_HEIGHT
                  );
            }

            ImageManager.getImage(Images.flag_rect)
               .draw(
                  oSB,
                  this.getPosX() + (int)(CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2 + 1 + (int)(2.0F * CFG.GUI_SCALE) * 2 + this.iMinTextWidth + iTranslateX,
                  this.getPosY()
                     + this.getHeight()
                     - ((int)(CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2)
                     - (int)(2.0F * CFG.GUI_SCALE)
                     - (int)(CFG.TEXT_HEIGHT * POINTS_TEXT_SCALE) / 2
                     - CFG.CIV_FLAG_HEIGHT / 2
                     + iTranslateY
               );
         }

         try {
            CFG.game
               .getCiv(this.iBestCivID)
               .getFlag()
               .draw(
                  oSB,
                  this.getPosX() + (int)(CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2 + 1 + (int)(2.0F * CFG.GUI_SCALE) * 2 + this.iMaxTextWidth + iTranslateX,
                  this.getPosY()
                     - CFG.game.getCiv(this.iBestCivID).getFlag().getHeight()
                     + 1
                     + (int)(2.0F * CFG.GUI_SCALE)
                     + (int)(CFG.TEXT_HEIGHT * POINTS_TEXT_SCALE) / 2
                     - CFG.CIV_FLAG_HEIGHT / 2
                     + iTranslateY,
                  CFG.CIV_FLAG_WIDTH,
                  CFG.CIV_FLAG_HEIGHT
               );
         } catch (IndexOutOfBoundsException var9) {
            ImageManager.getImage(Images.randomCivilizationFlag)
               .draw(
                  oSB,
                  this.getPosX() + (int)(CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2 + 1 + (int)(2.0F * CFG.GUI_SCALE) * 2 + this.iMaxTextWidth + iTranslateX,
                  this.getPosY()
                     - ImageManager.getImage(Images.randomCivilizationFlag).getHeight()
                     + 1
                     + (int)(2.0F * CFG.GUI_SCALE)
                     + (int)(CFG.TEXT_HEIGHT * POINTS_TEXT_SCALE) / 2
                     - CFG.CIV_FLAG_HEIGHT / 2
                     + iTranslateY,
                  CFG.CIV_FLAG_WIDTH,
                  CFG.CIV_FLAG_HEIGHT
               );
         }

         ImageManager.getImage(Images.flag_rect)
            .draw(
               oSB,
               this.getPosX() + (int)(CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2 + 1 + (int)(2.0F * CFG.GUI_SCALE) * 2 + this.iMaxTextWidth + iTranslateX,
               this.getPosY() + 1 + (int)(2.0F * CFG.GUI_SCALE) + (int)(CFG.TEXT_HEIGHT * POINTS_TEXT_SCALE) / 2 - CFG.CIV_FLAG_HEIGHT / 2 + iTranslateY
            );
      }

      oSB.setColor(GRAPH_BORDERS_COLOR);
      ImageManager.getImage(Images.pix255_255_255)
         .draw(
            oSB,
            this.getPosX() + (int)(CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2 + iTranslateX,
            this.getPosY() + iTranslateY,
            1,
            this.getHeight() - (int)(CFG.TEXT_HEIGHT * 0.7F) - CFG.PADDING * 2 + CFG.PADDING
         );
      ImageManager.getImage(Images.pix255_255_255)
         .draw(
            oSB,
            this.getPosX() + (int)(CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2 - CFG.PADDING + iTranslateX,
            this.getPosY() + this.getHeight() - (int)(CFG.TEXT_HEIGHT * 0.7F) - CFG.PADDING * 2 + iTranslateY,
            this.getGraphWidth() - (int)(CFG.TEXT_HEIGHT * 0.7F) - CFG.PADDING * 2 + CFG.PADDING,
            1
         );
      ImageManager.getImage(Images.pix255_255_255)
         .draw(oSB, this.getPosX() + this.getGraphWidth() - CFG.PADDING + iTranslateX, this.getPosY() + 1 + iTranslateY, CFG.PADDING);
      ImageManager.getImage(Images.pix255_255_255)
         .draw(oSB, this.getPosX() - 1 + this.getGraphWidth() + iTranslateX, this.getPosY() + 1 + iTranslateY, 1, CFG.PADDING - 1);
      if (this.iDescOfTurnID > 0 && this.iDescOfTurnID < this.iPointsPosXSize - 1) {
         ImageManager.getImage(Images.pix255_255_255)
            .draw(
               oSB,
               this.getPosX() + (int)(CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2 + this.lPointsPosX.get(this.iDescOfTurnID) + iTranslateX,
               this.getPosY() + this.getHeight() - (int)(CFG.TEXT_HEIGHT * 0.7F) - CFG.PADDING * 3 + 1 + iTranslateY,
               1,
               CFG.PADDING - 1
            );
      }

      Rectangle clipBounds = new Rectangle(
         this.getPosX() + iTranslateX,
         CFG.GAME_HEIGHT - this.getPosY() - iTranslateY,
         this.getWidth(),
         -(this.getHeight() - (int)(CFG.TEXT_HEIGHT * 0.7F) - CFG.PADDING * 2 + 2)
      );
      oSB.flush();
      ScissorStack.pushScissors(clipBounds);

      for (int ix = 0; ix < this.iDataSize; ix++) {
         if (this.lData.get(this.lSortedData.get(ix)).getVisible()) {
            this.lData
               .get(this.lSortedData.get(ix))
               .drawCivButton(
                  oSB,
                  this.getPosX() + this.getWidth() - getGraphButtonWidth() + iTranslateX,
                  this.getPosY() + (getGraphButtonHeight() + CFG.PADDING) * ix + this.iButtonsPosY + iTranslateY,
                  this.iActiveButtonID == ix
               );
         }
      }

      try {
         oSB.flush();
         ScissorStack.popScissors();
      } catch (IllegalStateException var8) {
      }

      oSB.setColor(Color.WHITE);
   }

   public final void drawGraphData(SpriteBatch oSB, int iTranslateX, int iTranslateY) {
      int tempFixPosY = this.getMinPoint() > 0 ? this.iFixPosY : this.iFixPosY;

      for (int i = 0; i < this.iDataSize; i++) {
         if (this.lData.get(i).getDrawData()) {
            this.lData
               .get(i)
               .draw(
                  oSB,
                  this.getPosX() + (int)(CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2 + iTranslateX,
                  this.getPosY() + iTranslateY,
                  this.getGraphWidth(),
                  this.getHeight(),
                  this.lPointsPosX,
                  i,
                  this.iActiveButtonID >= 0
                     ? this.lSortedData.get(this.iActiveButtonID) == i
                     : (this.iHoveredID >= 0 ? this.lSortedData.get(this.iHoveredID) == i : false),
                  tempFixPosY
               );
         } else if (this.lData.get(i).getBackAnimation()) {
            if (this.lData.get(i).getTime() + 750L <= System.currentTimeMillis()) {
               this.lData.get(i).setBackAnimation(false);
            } else {
               this.lData
                  .get(i)
                  .drawAnimation(
                     oSB,
                     this.getPosX() + (int)(CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2 + iTranslateX,
                     this.getPosY() + iTranslateY,
                     this.getGraphWidth(),
                     this.getHeight(),
                     this.lPointsPosX,
                     i,
                     this.iActiveButtonID == this.lSortedData.get(i) || this.iHoveredID == this.lSortedData.get(i),
                     tempFixPosY
                  );
            }
         }
      }
   }

   @Override
   public final void setData(List<GraphData> nData) {
      this.lData.clear();

      for (int i = 0; i < nData.size(); i++) {
         this.lData.add(nData.get(i));
      }

      this.iDataSize = this.lData.size();
      this.buildGraph();
   }

   @Override
   public final void addData(GraphData nData) {
      for (int i = 0; i < this.iDataSize; i++) {
         if (this.lData.get(i).getCivID() == nData.getCivID()) {
            return;
         }
      }

      this.lData.add(nData);
      this.iDataSize = this.lData.size();
      this.updateMoveable();
      this.buildGraph();
      this.sortCivsByLastPoint();
   }

   @Override
   public final void removeData(int iCivID) {
      if (this.iDataSize > 1) {
         for (int i = 0; i < this.iDataSize; i++) {
            if (this.lData.get(i).getCivID() == iCivID) {
               this.lData.remove(i);
               this.iDataSize = this.lData.size();
               this.updateMoveable();
               this.buildGraph();
               this.updateButtonsInView();
               return;
            }
         }
      }

      this.sortCivsByLastPoint();
   }

   @Override
   public void setMin(int nCivID) {
      for (int i = 0; i < this.lData.size(); i++) {
         if (this.lData.get(i).getCivID() == nCivID) {
            this.lData.get(i).setDrawData(!this.lData.get(i).getDrawData());
            if (this.lData.get(i).getDrawData()) {
               this.loadData(i);
            }
            break;
         }
      }
   }

   public void loadData(int i) {
      int nStartTurnID = -1;
      int jSize = CFG.timelapseManager.timelapseStatsGD.lProvinces.size();

      for (int j = 0; j < jSize; j++) {
         if (CFG.timelapseManager.timelapseStatsGD.lProvinces.get(j).size() > this.lData.get(i).getCivID()) {
            nStartTurnID = j;
            break;
         }
      }

      ArrayList<Integer> tempPoints = new ArrayList<>();
      if (nStartTurnID >= 0) {
         int jSize2 = CFG.timelapseManager.timelapseStatsGD.lProvinces.size();

         for (int jx = nStartTurnID; jx < jSize2; jx++) {
            tempPoints.add(CFG.timelapseManager.timelapseStatsGD.lProvinces.get(jx).get(this.lData.get(i).getCivID()));
         }
      }

      if (tempPoints.size() > 0) {
         this.lData.set(i, new GraphData(this.lData.get(i).getCivID(), tempPoints, nStartTurnID));
         this.lData.get(i).setDrawData(true);
         this.updateMoveable();
         this.buildGraph();
      }
   }

   public final void sortCivsByLastPoint() {
      this.lSortedData.clear();

      for (int i = 0; i < this.iDataSize; i++) {
         this.lSortedData.add(i);
      }
   }

   public final int getDataLastPoint(int id) {
      try {
         return this.lData.get(id).getPointY(this.iPointsPosXSize - 1 - this.lData.get(id).getBeginTurnID());
      } catch (IndexOutOfBoundsException var3) {
         return 0;
      }
   }

   @Override
   public void updateSlider(int nPosX) {
      this.updateMoveTurnTime();
      if (CFG.menuManager.getGraphButtonMode2()) {
         this.actionUp(nPosX);
      } else {
         if (nPosX < this.getPosX()) {
            this.iDescOfTurnID = 0;
            this.updateDescInfo();
            return;
         }

         if (nPosX > this.getPosX() + this.getGraphWidth()) {
            this.iDescOfTurnID = this.iPointsPosXSize - 1;
            this.updateDescInfo();
            return;
         }

         float tempWidth = (float)(this.getGraphWidth() - (int)(CFG.TEXT_HEIGHT * 0.7F) - CFG.PADDING * 2) / (this.iPointsPosXSize - 1);
         float tempX = this.getPosX() + (int)(CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2;

         for (int i = 0; i < this.iPointsPosXSize; i++) {
            float var5;
            float var6;
            tempX = i == 0 ? (var5 = tempX + tempWidth / 2.0F) : (var6 = tempX + tempWidth);
            if (nPosX <= tempX) {
               this.iDescOfTurnID = i;
               this.updateDescInfo();
               return;
            }
         }
      }
   }

   public final void updateDescInfo() {
      int tempBestResult = this.getMinPoint();
      int tempWorstResult = this.iMaxPoint;

      for (int i = 0; i < this.iDataSize; i++) {
         if (this.lData.get(i).getDrawData()
            && this.iDescOfTurnID >= this.lData.get(i).getBeginTurnID()
            && this.iDescOfTurnID < this.lData.get(i).getBeginTurnID() + this.lData.get(i).getPointsSize()) {
            if (this.lData.get(i).getPointY(this.iDescOfTurnID - this.lData.get(i).getBeginTurnID()) > tempBestResult) {
               tempBestResult = this.lData.get(i).getPointY(this.iDescOfTurnID - this.lData.get(i).getBeginTurnID());
               this.iBestDescDataID = i;
            }

            if (this.lData.get(i).getPointY(this.iDescOfTurnID - this.lData.get(i).getBeginTurnID()) <= tempWorstResult) {
               tempWorstResult = this.lData.get(i).getPointY(this.iDescOfTurnID - this.lData.get(i).getBeginTurnID());
               this.iWorstDescDataID = i;
            }
         }
      }

      CFG.fontMain.getData().setScale(POINTS_TEXT_SCALE);
      CFG.glyphLayout
         .setText(
            CFG.fontMain, "" + this.lData.get(this.iWorstDescDataID).getPointY(this.iDescOfTurnID - this.lData.get(this.iWorstDescDataID).getBeginTurnID())
         );
      this.iWorstDescDataTextWidth = (int)CFG.glyphLayout.width;
      CFG.glyphLayout
         .setText(CFG.fontMain, "" + this.lData.get(this.iBestDescDataID).getPointY(this.iDescOfTurnID - this.lData.get(this.iBestDescDataID).getBeginTurnID()));
      this.iBestDescDataTextWidth = (int)CFG.glyphLayout.width;
      CFG.fontMain.getData().setScale(1.0F);
      int tempRealTurnID = 1;
      tempRealTurnID = this.iPointsPosXSize < Game_Calendar.TURN_ID
         ? Game_Calendar.TURN_ID - this.iPointsPosXSize - 1 + this.iDescOfTurnID + 1
         : this.iDescOfTurnID + 1;
      this.sTextX = Game_Calendar.getDate_ByTurnID(tempRealTurnID);
      this.sTextX2 = " [" + CFG.langManager.get("Turn") + ": " + tempRealTurnID + "]";
      CFG.glyphLayout.setText(CFG.fontMain, this.sTextX);
      this.iWidthTextX = (int)CFG.glyphLayout.width;
      CFG.glyphLayout.setText(CFG.fontMain, this.sTextX2);
      this.iWidthTextX2 = (int)CFG.glyphLayout.width;
      this.updateMoveTurnTime();
      CFG.setRender_3(true);
   }

   public final void buildGraph() {
      this.iMinPoint = this.iMaxPoint = this.lData.get(0).getPointY(0);
      this.fAvaragePoint = 0.0F;
      this.iBestCivID = this.iWorstCivID = this.lData.get(0).getCivID();
      int tempAvarageSize = 0;
      this.iMaxSize = 0;

      for (int i2 = 0; i2 < this.iDataSize; i2++) {
         if (this.lData.get(i2).getDrawData()) {
            float tempAverage = 0.0F;

            for (int j = 0; j < this.lData.get(i2).getPointsSize(); j++) {
               if (this.lData.get(i2).getPointY(j) > this.iMaxPoint) {
                  this.iMaxPoint = this.lData.get(i2).getPointY(j);
                  this.iBestCivID = this.lData.get(i2).getCivID();
               }

               if (this.lData.get(i2).getPointY(j) <= this.iMinPoint) {
                  this.iMinPoint = this.lData.get(i2).getPointY(j);
                  this.iWorstCivID = this.lData.get(i2).getCivID();
               }

               tempAverage += this.lData.get(i2).getPointY(j);
            }

            this.fAvaragePoint = this.fAvaragePoint + tempAverage / this.lData.get(i2).getPointsSize();
            tempAvarageSize++;
            if (this.iMaxSize < this.lData.get(i2).getPointsSize() + this.lData.get(i2).getBeginTurnID()) {
               this.iMaxSize = this.lData.get(i2).getPointsSize() + this.lData.get(i2).getBeginTurnID();
            }
         } else {
            for (int j = 0; j < this.lData.get(i2).getPointsSize(); j++) {
               if (this.lData.get(i2).getPointY(j) > this.iMaxPoint) {
                  this.iMaxPoint = this.lData.get(i2).getPointY(j);
                  this.iBestCivID = this.lData.get(i2).getCivID();
               }

               if (this.lData.get(i2).getPointY(j) <= this.iMinPoint) {
                  this.iMinPoint = this.lData.get(i2).getPointY(j);
                  this.iWorstCivID = this.lData.get(i2).getCivID();
               }
            }

            if (this.iMaxSize < this.lData.get(i2).getPointsSize() + this.lData.get(i2).getBeginTurnID()) {
               this.iMaxSize = this.lData.get(i2).getPointsSize() + this.lData.get(i2).getBeginTurnID();
            }
         }
      }

      this.fAvaragePoint /= tempAvarageSize;

      try {
         if (this.iMinPoint < 0) {
            this.iFixPosY = -(
               (int)(
                  (this.getHeight() - (int)(CFG.TEXT_HEIGHT * 0.7F) - CFG.PADDING * 2)
                     * (100.0F * this.getMinPoint())
                     / (this.iMaxPoint - this.getMinPoint())
                     / 100.0F
               )
            );
            this.iZeroPosY = (int)(
               this.getHeight() - (int)(CFG.TEXT_HEIGHT * 0.7F) - CFG.PADDING * 2
                  - (this.getHeight() - (int)(CFG.TEXT_HEIGHT * 0.7F) - CFG.PADDING * 2) * 0.0F / (this.iMaxPoint - this.getMinPoint()) / 100.0F
            );
         } else {
            this.iFixPosY = this.iMinPoint > 0
               ? (int)(
                  this.getHeight() - (int)(CFG.TEXT_HEIGHT * 0.7F) - CFG.PADDING * 2
                     - (this.getHeight() - (int)(CFG.TEXT_HEIGHT * 0.7F) - CFG.PADDING * 2)
                        * (100.0F * this.getMinPoint())
                        / (this.iMaxPoint - this.getMinPoint())
                        / 100.0F
                     - (this.getHeight() - (int)(CFG.TEXT_HEIGHT * 0.7F) - CFG.PADDING * 2)
               )
               : 0;
         }
      } catch (ArithmeticException var6) {
         this.iFixPosY = 0;
      }

      this.iAvaragePosY = (int)(
         this.getHeight() - (int)(CFG.TEXT_HEIGHT * 0.7F) - CFG.PADDING * 2
            - (this.getHeight() - (int)(CFG.TEXT_HEIGHT * 0.7F) - CFG.PADDING * 2)
               * (100.0F * this.fAvaragePoint)
               / (this.iMaxPoint - this.getMinPoint())
               / 100.0F
      );
      this.roundAverage();
      this.lPointsPosX.clear();
      this.lPointsPosX.add(0);

      for (int i = 1; i < this.iMaxSize - 1; i++) {
         this.lPointsPosX.add((int)((this.getGraphWidth() - (int)(CFG.TEXT_HEIGHT * 0.7F) - CFG.PADDING * 2) * (100.0F * i) / (this.iMaxSize - 1) / 100.0F));
      }

      this.lPointsPosX.add(this.getGraphWidth() - (int)(CFG.TEXT_HEIGHT * 0.7F) - CFG.PADDING * 2);
      this.iPointsPosXSize = this.lPointsPosX.size();

      for (int var7 = 0; var7 < this.iDataSize; var7++) {
         this.lData
            .get(var7)
            .buildGraph(this.getHeight() - (int)(CFG.TEXT_HEIGHT * 0.7F) - CFG.PADDING * 2, this.getMinPoint(), this.iMaxPoint, this.lPointsPosX);
      }

      CFG.fontMain.getData().setScale(POINTS_TEXT_SCALE);
      CFG.glyphLayout.setText(CFG.fontMain, "" + this.iMinPoint);
      this.iMinTextWidth = (int)CFG.glyphLayout.width;
      CFG.glyphLayout.setText(CFG.fontMain, "" + this.iMaxPoint);
      this.iMaxTextWidth = (int)CFG.glyphLayout.width;
      CFG.fontMain.getData().setScale(1.0F);
      this.updateDescInfo();
   }

   public final void updateButtonsInView() {
      for (int i = 0; i < this.iDataSize; i++) {
         if (this.getButtonsPosY(i) + this.iButtonsPosY >= 0 && this.getButtonsPosY(i) + this.iButtonsPosY <= this.getHeight()) {
            this.lData.get(this.lSortedData.get(i)).setVisible(true);
         } else if (this.getButtonsPosY(i) + getGraphButtonHeight() + this.iButtonsPosY >= 0
            && this.getButtonsPosY(i) + getGraphButtonHeight() + this.iButtonsPosY <= this.getHeight()) {
            this.lData.get(this.lSortedData.get(i)).setVisible(true);
         } else {
            this.lData.get(this.lSortedData.get(i)).setVisible(false);
         }
      }
   }

   public final void updateMoveable() {
      if (this.getButtonsHeight() > this.getHeight()) {
         this.moveable = true;
      } else {
         this.moveable = false;
         this.iButtonsPosY = 0;
      }
   }

   @Override
   public final void setScrollPosY(int nPosY) {
      nPosY -= this.getPosY();

      for (int i = 0; i < this.iDataSize; i++) {
         if (this.getButtonsPosY(i) + this.iButtonsPosY <= nPosY && this.getButtonsPosY(i) + getGraphButtonHeight() + this.iButtonsPosY >= nPosY) {
            this.iActiveButtonID = i;
            Gdx.app.log("AoC", "" + this.iActiveButtonID);
            break;
         }
      }
   }

   public final void actionUp(int nPosY) {
      if (this.iActiveButtonID >= 0
         && this.getButtonsPosY(this.iActiveButtonID) + this.iButtonsPosY <= (nPosY = nPosY - this.getPosY())
         && this.getButtonsPosY(this.iActiveButtonID) + getGraphButtonHeight() + this.iButtonsPosY >= nPosY) {
         if (!this.lData.get(this.lSortedData.get(this.iActiveButtonID)).getDrawData()) {
            this.lData.get(this.lSortedData.get(this.iActiveButtonID)).setDrawData(!this.lData.get(this.lSortedData.get(this.iActiveButtonID)).getDrawData());
            if (this.lData.get(this.lSortedData.get(this.iActiveButtonID)).getDrawData()) {
               this.loadData(this.lSortedData.get(this.iActiveButtonID));
            }

            this.buildGraph();
         } else {
            int numOfActiveDatas = 0;

            for (int j = 0; j < this.iDataSize; j++) {
               if (this.lData.get(j).getDrawData()) {
                  numOfActiveDatas++;
               }
            }

            if (numOfActiveDatas > 1) {
               this.lData
                  .get(this.lSortedData.get(this.iActiveButtonID))
                  .setDrawData(!this.lData.get(this.lSortedData.get(this.iActiveButtonID)).getDrawData());
               if (this.lData.get(this.lSortedData.get(this.iActiveButtonID)).getDrawData()) {
                  this.loadData(this.lSortedData.get(this.iActiveButtonID));
               }

               this.buildGraph();
            }
         }
      }

      this.iActiveButtonID = -1;
   }

   @Override
   public int getCurrent() {
      return this.iButtonsPosY;
   }

   @Override
   public void setCurrent(int nButtonsPosY) {
      if (nButtonsPosY >= 0) {
         nButtonsPosY = 0;
      } else if (nButtonsPosY <= -(this.getButtonsHeight() - (this.getHeight() - (int)(CFG.TEXT_HEIGHT * 0.7F) - CFG.PADDING * 2))) {
         nButtonsPosY = -(this.getButtonsHeight() - (this.getHeight() - (int)(CFG.TEXT_HEIGHT * 0.7F) - CFG.PADDING * 2));
      }

      if (this.iButtonsPosY != nButtonsPosY) {
         this.iButtonsPosY = nButtonsPosY;
         this.updateButtonsInView();
         CFG.setRender_3(true);
      }
   }

   @Override
   public boolean getMoveable() {
      return this.moveable;
   }

   public final int getButtonsPosY(int i) {
      return getGraphButtonHeight() * i + CFG.PADDING * i;
   }

   public final int getButtonsHeight() {
      return getGraphButtonHeight() * this.iDataSize + CFG.PADDING * (this.iDataSize - 1);
   }

   public final void roundAverage() {
      if (this.fAvaragePoint - (int)this.fAvaragePoint != 0.0F) {
         this.bDecimal = (byte)Math.round((this.fAvaragePoint - (int)this.fAvaragePoint) * 100.0F);
         this.fAvaragePoint = this.fAvaragePoint - (this.fAvaragePoint - (int)this.fAvaragePoint);
         this.lessThanTen = false;
         if (this.bDecimal % 10 == 0) {
            this.bDecimal = (byte)(this.bDecimal / 10);
         } else if (this.bDecimal < 10) {
            this.lessThanTen = true;
         }
      } else {
         this.bDecimal = 0;
      }
   }

   @Override
   public void setVisible(boolean isVisible) {
      if (isVisible) {
         if (this.iDescOfTurnID != 0) {
            this.updateSlider(0);
         }

         this.lTime = System.currentTimeMillis() - 1L;
         CFG.setRender_3(true);
         this.updateMoveTurnTime();
      } else {
         this.lTime = 0L;
         this.iButtonsPosY = 0;
      }

      super.setVisible(isVisible);
      this.setHoveredID(-1);
   }

   public final int getGraphWidth() {
      return this.getWidth() - getGraphButtonWidth() - CFG.PADDING;
   }

   public final int getMinPoint() {
      return this.iMinPoint > 0 ? 0 : this.iMinPoint;
   }

   public final void updateMoveTurnTime() {
      this.lAuto_Move_Turn_Time = System.currentTimeMillis();
   }

   public final void incrementTurnDescInfo() {
      this.iDescOfTurnID++;
      if (this.iDescOfTurnID >= this.iMaxSize) {
         this.iDescOfTurnID = 0;
      }

      this.updateDescInfo();
      CFG.setRender_3(true);
   }

   @Override
   public void setCheckboxState(boolean checkboxState) {
      this.buildGraph();
      this.updateMoveable();
      this.updateButtonsInView();
   }
}
