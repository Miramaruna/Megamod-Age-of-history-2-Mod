package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.PolygonRegion;
import com.badlogic.gdx.graphics.g2d.PolygonSprite;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.EarClippingTriangulator;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ShortArray;
import java.util.List;

public class Graph_CircleDraw {
   public PolygonSpriteBatch oPB;
   public TextureRegion texture;
   public Image textureOver;
   public Image circleFrame;
   public Vector2 center;
   public Vector2 centerTop;
   public Vector2 leftTop;
   public Vector2 leftBottom;
   public Vector2 rightBottom;
   public Vector2 rightTop;
   public float[] fv;
   public Graph_CircleDraw.IntersectAt intersectAt;

   public Graph_CircleDraw(String nFileName, String fileNameOver, String nFlieNameFrame) {
      this.texture = new TextureRegion(new Texture(Gdx.files.internal("UI/" + CFG.getRescouresPath() + "graph/" + nFileName), Pixmap.Format.RGBA8888, true));
      this.textureOver = new Image(
         new Texture(Gdx.files.internal("UI/" + CFG.getRescouresPath() + "graph/" + fileNameOver), Pixmap.Format.RGBA8888, true), Texture.TextureFilter.Linear
      );
      this.circleFrame = new Image(
         new Texture(Gdx.files.internal("UI/" + CFG.getRescouresPath() + "graph/" + nFlieNameFrame), Pixmap.Format.RGBA8888, true),
         Texture.TextureFilter.Linear
      );
      this.oPB = new PolygonSpriteBatch();
      this.center = new Vector2(this.texture.getRegionWidth() / 2, this.texture.getRegionHeight() / 2);
      this.centerTop = new Vector2(this.texture.getRegionWidth() / 2, this.texture.getRegionHeight());
      this.leftTop = new Vector2(0.0F, this.texture.getRegionHeight());
      this.leftBottom = new Vector2(0.0F, 0.0F);
      this.rightBottom = new Vector2(this.texture.getRegionWidth(), 0.0F);
      this.rightTop = new Vector2(this.texture.getRegionWidth(), this.texture.getRegionHeight());
      this.setPercentage(0.0F);
   }

   public final void drawCircleUpgArmy(SpriteBatch oSB, int nPosX, int nPosY, List<Graph_CircleData> nData, boolean isActive) {
      try {
         this.drawCircle100(oSB, nPosX, nPosY, new Color(CFG.COLOR_TEXT_REVOLUTION_MAX));
      } catch (IndexOutOfBoundsException var7) {
         this.drawCircle100(
            oSB,
            nPosX,
            nPosY,
            new Color(
               CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getR(),
               CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getG(),
               CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getB(),
               1.0F
            )
         );
      }

      oSB.end();
      this.drawGraphUpgradingArmy(nPosX, nPosY, nData);
      oSB.begin();
      this.textureOver.draw(oSB, nPosX, nPosY);
      if (isActive) {
         oSB.setColor(new Color(1.0F, 1.0F, 1.0F, 0.335F));
         this.textureOver.draw(oSB, nPosX, nPosY);
         oSB.setColor(Color.WHITE);
      }

      this.circleFrame.draw(oSB, nPosX, nPosY);
   }

   public final void drawGraphUpgradingArmy(int nPosX, int nPosY, List<Graph_CircleData> nData) {
      try {
         this.oPB.begin();
         float drawnPercentage = nData.get(0).getPercentage();

         for (int i = 1; i < nData.size(); i++) {
            this.setPercentage(drawnPercentage);

            try {
               this.drawCircle(nPosX, nPosY, new Color(CFG.COLOR_TEXT_MODIFIER_POSITIVE));
            } catch (IndexOutOfBoundsException var7) {
               this.drawCircle(
                  nPosX,
                  nPosY,
                  new Color(
                     CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getR(),
                     CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getG(),
                     CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getB(),
                     1.0F
                  )
               );
            }

            drawnPercentage += nData.get(i).getPercentage();
         }

         this.oPB.end();
      } catch (IllegalStateException var8) {
      }
   }

   public final void draw(SpriteBatch oSB, int nPosX, int nPosY, List<Graph_CircleData> nData, boolean isActive) {
      try {
         this.drawCircle100(
            oSB,
            nPosX,
            nPosY,
            new Color(
               CFG.game.getCiv(nData.get(0).getDataID()).getR() / 255.0F,
               CFG.game.getCiv(nData.get(0).getDataID()).getG() / 255.0F,
               CFG.game.getCiv(nData.get(0).getDataID()).getB() / 255.0F,
               1.0F
            )
         );
      } catch (IndexOutOfBoundsException var7) {
         this.drawCircle100(
            oSB,
            nPosX,
            nPosY,
            new Color(
               CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getR(),
               CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getG(),
               CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getB(),
               1.0F
            )
         );
      }

      oSB.end();
      this.drawGraph(nPosX, nPosY, nData);
      oSB.begin();
      this.textureOver.draw(oSB, nPosX, nPosY);
      if (isActive) {
         oSB.setColor(new Color(1.0F, 1.0F, 1.0F, 0.335F));
         this.textureOver.draw(oSB, nPosX, nPosY);
         oSB.setColor(Color.WHITE);
      }

      this.circleFrame.draw(oSB, nPosX, nPosY);
   }

   public final void drawGraph(int nPosX, int nPosY, List<Graph_CircleData> nData) {
      try {
         this.oPB.begin();
         float drawnPercentage = nData.get(0).getPercentage();

         for (int i = 1; i < nData.size(); i++) {
            this.setPercentage(drawnPercentage);

            try {
               this.drawCircle(
                  nPosX,
                  nPosY,
                  new Color(
                     CFG.game.getCiv(nData.get(i).getDataID()).getR() / 255.0F,
                     CFG.game.getCiv(nData.get(i).getDataID()).getG() / 255.0F,
                     CFG.game.getCiv(nData.get(i).getDataID()).getB() / 255.0F,
                     1.0F
                  )
               );
            } catch (IndexOutOfBoundsException var7) {
               this.drawCircle(
                  nPosX,
                  nPosY,
                  new Color(
                     CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getR(),
                     CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getG(),
                     CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getB(),
                     1.0F
                  )
               );
            }

            drawnPercentage += nData.get(i).getPercentage();
         }

         this.oPB.end();
      } catch (IllegalStateException var8) {
      }
   }

   public final void drawCircle100(SpriteBatch oSB, int nPosX, int nPosY, Color nColor) {
      oSB.setColor(nColor);
      oSB.draw(
         this.texture.getTexture(),
         nPosX,
         -nPosY - this.texture.getRegionHeight(),
         0.0F,
         0.0F,
         this.texture.getRegionWidth(),
         this.texture.getRegionHeight(),
         1.0F,
         1.0F,
         0.0F,
         0,
         0,
         this.texture.getRegionWidth(),
         this.texture.getRegionHeight(),
         false,
         false
      );
      oSB.setColor(Color.WHITE);
   }

   public final void drawCircle(int nPosX, int nPosY, Color nColor) {
      if (this.fv != null) {
         EarClippingTriangulator e = new EarClippingTriangulator();
         ShortArray sv = e.computeTriangles(this.fv);
         PolygonRegion polyReg = new PolygonRegion(this.texture, this.fv, sv.toArray());
         PolygonSprite poly = new PolygonSprite(polyReg);
         poly.setOrigin(0.0F, 0.0F);
         poly.setPosition(nPosX, CFG.GAME_HEIGHT - this.texture.getRegionHeight() - nPosY);
         poly.setRotation(0.0F);
         poly.setColor(nColor);
         poly.draw(this.oPB);
      }
   }

   public final void setPercentage(float percent) {
      float angle = this.convertToRadians(90.0F);
      float len = this.texture.getRegionWidth() > this.texture.getRegionHeight() ? this.texture.getRegionWidth() : this.texture.getRegionHeight();
      float var8;
      float dy = (float)(Math.sin(var8 = angle - this.convertToRadians(percent * 360.0F / 100.0F)) * len);
      float dx = (float)(Math.cos(var8) * len);
      Vector2 line = new Vector2(this.center.x + dx, this.center.y + dy);
      Vector2 v = this.IntersectPoint(line);
      this.fv = this.intersectAt == Graph_CircleDraw.IntersectAt.TOP
         ? (
            v.x >= this.texture.getRegionWidth() / 2
               ? new float[]{
                  this.center.x,
                  this.center.y,
                  this.centerTop.x,
                  this.centerTop.y,
                  this.leftTop.x,
                  this.leftTop.y,
                  this.leftBottom.x,
                  this.leftBottom.y,
                  this.rightBottom.x,
                  this.rightBottom.y,
                  this.rightTop.x,
                  this.rightTop.y,
                  v.x,
                  v.y
               }
               : new float[]{this.center.x, this.center.y, this.centerTop.x, this.centerTop.y, v.x, v.y}
         )
         : (
            this.intersectAt == Graph_CircleDraw.IntersectAt.BOTTOM
               ? new float[]{
                  this.center.x,
                  this.center.y,
                  this.centerTop.x,
                  this.centerTop.y,
                  this.leftTop.x,
                  this.leftTop.y,
                  this.leftBottom.x,
                  this.leftBottom.y,
                  v.x,
                  v.y
               }
               : (
                  this.intersectAt == Graph_CircleDraw.IntersectAt.LEFT
                     ? new float[]{this.center.x, this.center.y, this.centerTop.x, this.centerTop.y, this.leftTop.x, this.leftTop.y, v.x, v.y}
                     : (
                        this.intersectAt == Graph_CircleDraw.IntersectAt.RIGHT
                           ? new float[]{
                              this.center.x,
                              this.center.y,
                              this.centerTop.x,
                              this.centerTop.y,
                              this.leftTop.x,
                              this.leftTop.y,
                              this.leftBottom.x,
                              this.leftBottom.y,
                              this.rightBottom.x,
                              this.rightBottom.y,
                              v.x,
                              v.y
                           }
                           : null
                     )
               )
         );
   }

   public final Vector2 IntersectPoint(Vector2 line) {
      Vector2 v = new Vector2();
      boolean isIntersect = Intersector.intersectSegments(this.leftTop, this.rightTop, this.center, line, v);
      if (isIntersect) {
         this.intersectAt = Graph_CircleDraw.IntersectAt.TOP;
         return v;
      } else {
         isIntersect = Intersector.intersectSegments(this.leftBottom, this.rightBottom, this.center, line, v);
         if (isIntersect) {
            this.intersectAt = Graph_CircleDraw.IntersectAt.BOTTOM;
            return v;
         } else {
            isIntersect = Intersector.intersectSegments(this.leftTop, this.leftBottom, this.center, line, v);
            if (isIntersect) {
               this.intersectAt = Graph_CircleDraw.IntersectAt.LEFT;
               return v;
            } else {
               isIntersect = Intersector.intersectSegments(this.rightTop, this.rightBottom, this.center, line, v);
               if (isIntersect) {
                  this.intersectAt = Graph_CircleDraw.IntersectAt.RIGHT;
                  return v;
               } else {
                  this.intersectAt = Graph_CircleDraw.IntersectAt.NONE;
                  return null;
               }
            }
         }
      }
   }

   public final float convertToRadians(float angleInDegrees) {
      return angleInDegrees * (float) (Math.PI / 180.0);
   }

   public final int getWidth() {
      return this.circleFrame.getWidth();
   }

   public static enum IntersectAt {
      NONE,
      TOP,
      BOTTOM,
      LEFT,
      RIGHT;
   }
}
