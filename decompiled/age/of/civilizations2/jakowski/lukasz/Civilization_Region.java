package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.CatmullRomSpline;
import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.math.Vector2;
import java.util.ArrayList;
import java.util.List;

public class Civilization_Region {
   public int iRegionID;
   public List<Integer> lProvinces;
   public int iProvincesSize;
   public boolean isSupplied = false;
   public boolean seaAccess = false;
   public boolean seaAccess_HavePort = false;
   public boolean haveNotOccupiedProvince = false;
   public boolean isKeyRegion = false;
   public int iAveragePotential = 0;
   public List<Integer> lCostalineProvinces;
   public List<Integer> shortestLine = null;
   public int iMinX = 0;
   public int iMaxX = 0;
   public int iMinY = 0;
   public int iMaxY = 0;
   public float fontScale = 1.0F;
   public float fAngle = 0.0F;
   public int iCharMaxWidth = 0;
   public int iCharMaxHeight = 0;
   public List<Point_XY> lPoints = new ArrayList<>();
   public List<Float> lPointsAngle = new ArrayList<>();
   public boolean drawName = true;
   public int iAvaragePointPosX = 0;
   public int iAvaragePointPosY = 0;
   public List<Boolean> triedToUse = new ArrayList<>();

   public Civilization_Region() {
   }

   public Civilization_Region(int nProvinceID, int iRegionID) {
      this.lProvinces = new ArrayList<>();
      this.shortestLine = new ArrayList<>();
      this.lCostalineProvinces = new ArrayList<>();
      this.iRegionID = iRegionID;
      this.addProvince(nProvinceID);
   }

   public final boolean checkRegionBordersWithEnemy(int nCivID) {
      for (int i = 0; i < this.getProvincesSize(); i++) {
         if (CFG.game.getProvince(this.getProvince(i)).getBordersWithEnemy()) {
            return true;
         }
      }

      return false;
   }

   public final void addProvince(int n) {
      this.lProvinces.add(n);
      this.iProvincesSize = this.lProvinces.size();
      if (CFG.game.getProvince(n).getNeighboringSeaProvincesSize() > 0) {
         this.lCostalineProvinces.add(n);
      }

      if (CFG.game.getProvince(n).getIsCapital()) {
         this.isKeyRegion = true;
      }

      CFG.game.getProvince(n).setCivRegionID(this.iRegionID);
      if (!this.seaAccess) {
         for (int n2 = 0; n2 < CFG.game.getProvince(n).getNeighboringSeaProvincesSize(); n2++) {
            if (CFG.game.getProvince(CFG.game.getProvince(n).getNeighboringSeaProvinces(n2)).getLevelOfPort() == -2) {
               this.seaAccess = true;
               break;
            }
         }
      }

      if (this.seaAccess && !this.seaAccess_HavePort && CFG.game.getProvince(n).getLevelOfPort() > 0) {
         this.seaAccess_HavePort = true;
      }

      if (!this.haveNotOccupiedProvince) {
         int n2x = CFG.game.getProvince(n).getCivID();
         if (!CFG.game.getCiv(n2x).civGameData.haveNotMoney
            && (CFG.game.getProvince(n).getLevelOfSupply() > 0 || this.seaAccess_HavePort || CFG.game.getProvince(n).getIsCapital())) {
            this.haveNotOccupiedProvince = true;
         }
      }
   }

   public final void removeProvinceID(int nProvinceID) {
      for (int i = 0; i < this.iProvincesSize; i++) {
         if (this.lProvinces.get(i) == nProvinceID) {
            this.lProvinces.remove(i);
            this.iProvincesSize = this.lProvinces.size();

            for (int j = 0; j < this.lCostalineProvinces.size(); j++) {
               if (this.lCostalineProvinces.get(j) == nProvinceID) {
                  this.lCostalineProvinces.remove(j);
                  break;
               }
            }

            CFG.game.getProvince(nProvinceID).setCivRegionID(-1);
            break;
         }
      }

      if (this.seaAccess) {
         this.seaAccess = false;

         for (int k = 0; k < this.iProvincesSize; k++) {
            for (int ix = 0; ix < CFG.game.getProvince(this.getProvince(k)).getNeighboringSeaProvincesSize(); ix++) {
               if (CFG.game.getProvince(CFG.game.getProvince(this.getProvince(k)).getNeighboringSeaProvinces(ix)).getLevelOfPort() == -2) {
                  this.seaAccess = true;
                  k = this.iProvincesSize;
                  break;
               }
            }
         }

         if (!this.seaAccess) {
            this.seaAccess_HavePort = false;
         } else if (this.seaAccess_HavePort) {
            this.seaAccess_HavePort = false;

            for (int var5 = 0; var5 < this.iProvincesSize; var5++) {
               if (CFG.game.getProvince(this.getProvince(var5)).getLevelOfPort() > 0) {
                  this.seaAccess_HavePort = true;
                  break;
               }
            }
         }
      }

      if (this.haveNotOccupiedProvince) {
         this.haveNotOccupiedProvince = false;

         for (int k = 0; k < this.iProvincesSize; k++) {
            if (!CFG.game.getProvince(this.getProvince(k)).isOccupied()) {
               this.haveNotOccupiedProvince = true;
               break;
            }
         }
      }
   }

   public final void removeProvince(int i) {
      CFG.game.getProvince(this.lProvinces.get(i)).setCivRegionID(-1);

      for (int j = 0; j < this.lCostalineProvinces.size(); j++) {
         if (this.lCostalineProvinces.get(j) == this.lProvinces.get(i)) {
            this.lCostalineProvinces.remove(j);
            break;
         }
      }

      this.lProvinces.remove(i);
      this.iProvincesSize = this.lProvinces.size();
   }

   public final boolean containsProvince(int nProvinceID) {
      for (int i = 0; i < this.iProvincesSize; i++) {
         if (this.lProvinces.get(i) == nProvinceID) {
            return true;
         }
      }

      return false;
   }

   public final boolean canBeUsedInPath(int id) {
      int iNumOfCivProvinces = 0;
      int iNumOfNonCivProvinces = 0;

      for (int i = 0; i < CFG.game.getProvince(this.lProvinces.get(id)).getNeighboringProvincesSize(); i++) {
         if (CFG.game.getProvince(this.lProvinces.get(id)).getCivID()
            == CFG.game.getProvince(CFG.game.getProvince(this.lProvinces.get(id)).getNeighboringProvinces(i)).getCivID()) {
            iNumOfCivProvinces++;
         } else {
            iNumOfNonCivProvinces++;
         }
      }

      return iNumOfNonCivProvinces > 0 ? iNumOfCivProvinces > 1 : true;
   }

   public final boolean buildRegionPath() {
      this.drawName = false;
      this.buildMinMaxBounds();
      if (this.lProvinces.size() == 1) {
         return false;
      } else {
         if (this.lProvinces.size() > 2) {
            if (!CFG.settingsManager.DRAW_CIVILIZATIONS_NAMES_OVER_PRPOVINCES_IN_GAME) {
               return false;
            }

            if (this.triedToUse.size() == 0) {
               for (int i = 0; i < this.iProvincesSize; i++) {
                  this.triedToUse.add(false);
               }
            }

            for (int i = 0; i < this.iProvincesSize; i++) {
               if (CFG.game.getProvince(this.lProvinces.get(i)).getBelowZero()) {
                  return false;
               }
            }

            int startID = -1;

            for (int i2 = 0; i2 < this.iProvincesSize; i2++) {
               if (!this.triedToUse.get(i2)) {
                  startID = i2;
                  break;
               }
            }

            if (startID == -1) {
               return false;
            }

            int fromProvinceID_LEFTRIGHT = startID;
            int toProvinceID_LEFTRIGHT = startID;
            int fromProvinceID_RIGHTLEFT = startID;
            int toProvinceID_RIGHTLEFT = startID;
            int fromProvinceID_BOTTOM = startID;
            int toProvinceID_TOP = startID;
            int fromProvinceID_LR = startID;
            int toProvinceID_LR = startID;
            int leftBottomDistance = (int)Math.sqrt(
               Math.pow(this.iMinX - CFG.game.getProvince(this.lProvinces.get(startID)).getCenterX(), 2.0)
                  + Math.pow(this.iMaxY - CFG.game.getProvince(this.lProvinces.get(startID)).getCenterY(), 2.0)
            );
            int rightTopDistance = (int)Math.sqrt(
               Math.pow(this.iMaxX - CFG.game.getProvince(this.lProvinces.get(startID)).getCenterX(), 2.0)
                  + Math.pow(this.iMinY - CFG.game.getProvince(this.lProvinces.get(startID)).getCenterY(), 2.0)
            );
            int rightBottomDistance = (int)Math.sqrt(
               Math.pow(this.iMaxX - CFG.game.getProvince(this.lProvinces.get(startID)).getCenterX(), 2.0)
                  + Math.pow(this.iMaxY - CFG.game.getProvince(this.lProvinces.get(startID)).getCenterY(), 2.0)
            );
            int leftTopDistance = (int)Math.sqrt(
               Math.pow(this.iMinX - CFG.game.getProvince(this.lProvinces.get(startID)).getCenterX(), 2.0)
                  + Math.pow(this.iMinY - CFG.game.getProvince(this.lProvinces.get(startID)).getCenterY(), 2.0)
            );

            for (int i3 = startID + 1; i3 < this.iProvincesSize; i3++) {
               if (!this.triedToUse.get(i3)) {
                  int tempDistance = this.getLineWidth(
                     this.iMinX,
                     this.iMaxY,
                     CFG.game.getProvince(this.lProvinces.get(i3)).getCenterX() + CFG.game.getProvince(this.lProvinces.get(i3)).getShiftX(),
                     CFG.game.getProvince(this.lProvinces.get(i3)).getCenterY() + CFG.game.getProvince(this.lProvinces.get(i3)).getShiftY()
                  );
                  if (tempDistance < leftBottomDistance) {
                     leftBottomDistance = tempDistance;
                     fromProvinceID_LEFTRIGHT = i3;
                  }

                  if ((
                        tempDistance = this.getLineWidth(
                           this.iMaxX,
                           this.iMinY,
                           CFG.game.getProvince(this.lProvinces.get(i3)).getCenterX() + CFG.game.getProvince(this.lProvinces.get(i3)).getShiftX(),
                           CFG.game.getProvince(this.lProvinces.get(i3)).getCenterY() + CFG.game.getProvince(this.lProvinces.get(i3)).getShiftY()
                        )
                     )
                     < rightTopDistance) {
                     rightTopDistance = tempDistance;
                     toProvinceID_LEFTRIGHT = i3;
                  }

                  if ((
                        tempDistance = this.getLineWidth(
                           this.iMaxX,
                           this.iMaxY,
                           CFG.game.getProvince(this.lProvinces.get(i3)).getCenterX() + CFG.game.getProvince(this.lProvinces.get(i3)).getShiftX(),
                           CFG.game.getProvince(this.lProvinces.get(i3)).getCenterY() + CFG.game.getProvince(this.lProvinces.get(i3)).getShiftY()
                        )
                     )
                     < rightBottomDistance) {
                     rightBottomDistance = tempDistance;
                     fromProvinceID_RIGHTLEFT = i3;
                  }

                  if ((
                        tempDistance = this.getLineWidth(
                           this.iMinX,
                           this.iMinY,
                           CFG.game.getProvince(this.lProvinces.get(i3)).getCenterX() + CFG.game.getProvince(this.lProvinces.get(i3)).getShiftX(),
                           CFG.game.getProvince(this.lProvinces.get(i3)).getCenterY() + CFG.game.getProvince(this.lProvinces.get(i3)).getShiftY()
                        )
                     )
                     < leftTopDistance) {
                     leftTopDistance = tempDistance;
                     toProvinceID_RIGHTLEFT = i3;
                  }

                  if (CFG.game.getProvince(this.lProvinces.get(fromProvinceID_BOTTOM)).getCenterY()
                        + CFG.game.getProvince(this.lProvinces.get(fromProvinceID_BOTTOM)).getShiftY()
                     < CFG.game.getProvince(this.lProvinces.get(i3)).getCenterY() + CFG.game.getProvince(this.lProvinces.get(i3)).getShiftY()) {
                     fromProvinceID_BOTTOM = i3;
                  }

                  if (CFG.game.getProvince(this.lProvinces.get(toProvinceID_TOP)).getCenterY()
                        + CFG.game.getProvince(this.lProvinces.get(toProvinceID_TOP)).getShiftY()
                     > CFG.game.getProvince(this.lProvinces.get(i3)).getCenterY() + CFG.game.getProvince(this.lProvinces.get(i3)).getShiftY()) {
                     toProvinceID_TOP = i3;
                  }

                  if (CFG.game.getProvince(this.lProvinces.get(fromProvinceID_LR)).getCenterX()
                           + CFG.game.getProvince(this.lProvinces.get(fromProvinceID_LR)).getShiftX()
                        > CFG.game.getProvince(this.lProvinces.get(i3)).getCenterX() + CFG.game.getProvince(this.lProvinces.get(i3)).getShiftX()
                     && CFG.game.getProvince(this.lProvinces.get(i3)).getCenterY() + CFG.game.getProvince(this.lProvinces.get(i3)).getShiftY()
                        >= this.iMinY + (this.iMaxY - this.iMinY) / 2) {
                     fromProvinceID_LR = i3;
                  }

                  if (CFG.game.getProvince(this.lProvinces.get(toProvinceID_LR)).getCenterX()
                           + CFG.game.getProvince(this.lProvinces.get(toProvinceID_LR)).getShiftX()
                        < CFG.game.getProvince(this.lProvinces.get(i3)).getCenterX() + CFG.game.getProvince(this.lProvinces.get(i3)).getShiftX()
                     && CFG.game.getProvince(this.lProvinces.get(i3)).getCenterY() + CFG.game.getProvince(this.lProvinces.get(i3)).getShiftY()
                        <= this.iMinY + (this.iMaxY - this.iMinY) / 2) {
                     toProvinceID_LR = i3;
                  }
               }
            }

            if (this.getLineWidth(fromProvinceID_LEFTRIGHT, toProvinceID_LEFTRIGHT) > this.getLineWidth(fromProvinceID_RIGHTLEFT, toProvinceID_RIGHTLEFT)) {
               if (this.getLineWidth(fromProvinceID_LEFTRIGHT, toProvinceID_LEFTRIGHT) > this.getLineWidth(fromProvinceID_BOTTOM, toProvinceID_TOP)) {
                  if (this.getLineWidth(fromProvinceID_LEFTRIGHT, toProvinceID_LEFTRIGHT) > this.getLineWidth(fromProvinceID_LR, toProvinceID_LR)) {
                     this.shortestLine.add(fromProvinceID_LEFTRIGHT);
                     this.shortestLine.add(toProvinceID_LEFTRIGHT);
                  } else {
                     this.shortestLine.add(fromProvinceID_LR);
                     this.shortestLine.add(toProvinceID_LR);
                  }
               } else if (this.getLineWidth(fromProvinceID_BOTTOM, toProvinceID_TOP) > this.getLineWidth(fromProvinceID_LR, toProvinceID_LR)) {
                  this.shortestLine.add(fromProvinceID_BOTTOM);
                  this.shortestLine.add(toProvinceID_TOP);
               } else {
                  this.shortestLine.add(fromProvinceID_LR);
                  this.shortestLine.add(toProvinceID_LR);
               }
            } else if (this.getLineWidth(fromProvinceID_RIGHTLEFT, toProvinceID_RIGHTLEFT) > this.getLineWidth(fromProvinceID_BOTTOM, toProvinceID_TOP)) {
               if (this.getLineWidth(fromProvinceID_RIGHTLEFT, toProvinceID_RIGHTLEFT) > this.getLineWidth(fromProvinceID_LR, toProvinceID_LR)) {
                  this.shortestLine.add(fromProvinceID_RIGHTLEFT);
                  this.shortestLine.add(toProvinceID_RIGHTLEFT);
               } else {
                  this.shortestLine.add(fromProvinceID_LR);
                  this.shortestLine.add(toProvinceID_LR);
               }
            } else if (this.getLineWidth(fromProvinceID_BOTTOM, toProvinceID_TOP) > this.getLineWidth(fromProvinceID_LR, toProvinceID_LR)) {
               this.shortestLine.add(fromProvinceID_BOTTOM);
               this.shortestLine.add(toProvinceID_TOP);
            } else {
               this.shortestLine.add(fromProvinceID_LR);
               this.shortestLine.add(toProvinceID_LR);
            }

            if (CFG.game.getProvince(this.lProvinces.get(this.shortestLine.get(0))).getCenterX()
               > CFG.game.getProvince(this.lProvinces.get(this.shortestLine.get(1))).getCenterX()) {
               int tempS = this.shortestLine.get(0);
               this.shortestLine.set(0, this.shortestLine.get(1));
               this.shortestLine.set(1, tempS);
            }

            if (this.shortestLine.size() == 0 || this.shortestLine.get(0) == this.shortestLine.get(1)) {
               this.shortestLine.clear();
               this.triedToUse.clear();
               return false;
            }

            Point_XY tD = this.canDrawTextProperly(this.lProvinces.get(this.shortestLine.get(0)), this.lProvinces.get(this.shortestLine.get(1)));
            if (tD != null) {
               if (this.getLineWidth(
                     tD.getPosX(),
                     tD.getPosY(),
                     CFG.game.getProvince(this.lProvinces.get(this.shortestLine.get(0))).getCenterX()
                        + CFG.game.getProvince(this.lProvinces.get(this.shortestLine.get(0))).getShiftX(),
                     CFG.game.getProvince(this.lProvinces.get(this.shortestLine.get(0))).getCenterY()
                        + CFG.game.getProvince(this.lProvinces.get(this.shortestLine.get(0))).getShiftY()
                  )
                  < this.getLineWidth(
                     tD.getPosX(),
                     tD.getPosY(),
                     CFG.game.getProvince(this.lProvinces.get(this.shortestLine.get(1))).getCenterX()
                        + CFG.game.getProvince(this.lProvinces.get(this.shortestLine.get(1))).getShiftX(),
                     CFG.game.getProvince(this.lProvinces.get(this.shortestLine.get(1))).getCenterY()
                        + CFG.game.getProvince(this.lProvinces.get(this.shortestLine.get(1))).getShiftY()
                  )) {
                  this.triedToUse.set(this.shortestLine.get(0), true);
               } else {
                  this.triedToUse.set(this.shortestLine.get(1), true);
               }

               this.shortestLine.clear();
               return this.buildRegionPath();
            }

            tD = null;
            this.triedToUse.clear();
            this.buildScaleOfText();
         }

         this.updateDrawRegionName();
         return true;
      }
   }

   public final void updateDrawRegionName() {
      this.drawName = true;
      if (CFG.FOG_OF_WAR == 2) {
         for (int i = 0; i < this.lProvinces.size(); i++) {
            if (!CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetProvince(this.lProvinces.get(i))) {
               this.drawName = false;
               break;
            }
         }
      }
   }

   public final void buildMinMaxBounds() {
      try {
         this.iMinX = CFG.game.getProvince(this.lProvinces.get(0)).getMinX();
         this.iMaxX = CFG.game.getProvince(this.lProvinces.get(0)).getMaxX();
         this.iMinY = CFG.game.getProvince(this.lProvinces.get(0)).getMinY();
         this.iMaxY = CFG.game.getProvince(this.lProvinces.get(0)).getMaxY();

         for (int i = 1; i < this.iProvincesSize; i++) {
            if (CFG.game.getProvince(this.lProvinces.get(i)).getMinX() < this.iMinX) {
               this.iMinX = CFG.game.getProvince(this.lProvinces.get(i)).getMinX();
            }

            if (CFG.game.getProvince(this.lProvinces.get(i)).getMaxX() > this.iMaxX) {
               this.iMaxX = CFG.game.getProvince(this.lProvinces.get(i)).getMaxX();
            }

            if (CFG.game.getProvince(this.lProvinces.get(i)).getMinY() < this.iMinY) {
               this.iMinY = CFG.game.getProvince(this.lProvinces.get(i)).getMinY();
            }

            if (CFG.game.getProvince(this.lProvinces.get(i)).getMaxY() > this.iMaxY) {
               this.iMaxY = CFG.game.getProvince(this.lProvinces.get(i)).getMaxY();
            }
         }
      } catch (IndexOutOfBoundsException var2) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var2);
         }
      }
   }

   public final void buildScaleOfText() {
      try {
         if (this.shortestLine.size() > 1) {
            int iDistance = (int)Math.sqrt(
               Math.pow(
                     CFG.game.getProvince(this.lProvinces.get(this.shortestLine.get(0))).getCenterX()
                        + CFG.game.getProvince(this.lProvinces.get(this.shortestLine.get(0))).getShiftX()
                        - CFG.game.getProvince(this.lProvinces.get(this.shortestLine.get(1))).getCenterX()
                        - CFG.game.getProvince(this.lProvinces.get(this.shortestLine.get(1))).getShiftX(),
                     2.0
                  )
                  + Math.pow(
                     CFG.game.getProvince(this.lProvinces.get(this.shortestLine.get(0))).getCenterY()
                        + CFG.game.getProvince(this.lProvinces.get(this.shortestLine.get(0))).getShiftY()
                        - CFG.game.getProvince(this.lProvinces.get(this.shortestLine.get(1))).getCenterY()
                        - CFG.game.getProvince(this.lProvinces.get(this.shortestLine.get(1))).getShiftY(),
                     2.0
                  )
            );
            CFG.glyphLayout
               .setText(CFG.fontBorder, CFG.game.getCiv(CFG.game.getProvince(this.lProvinces.get(this.shortestLine.get(0))).getCivID()).getCivName());
            int tempNumOfInterations = 0;

            try {
               do {
                  if (iDistance > CFG.glyphLayout.width) {
                     CFG.fontBorder.getData().setScale(CFG.fontBorder.getData().scaleX + 0.1F);
                     CFG.glyphLayout
                        .setText(CFG.fontBorder, CFG.game.getCiv(CFG.game.getProvince(this.lProvinces.get(this.shortestLine.get(0))).getCivID()).getCivName());
                     if (iDistance < CFG.glyphLayout.width) {
                        this.fontScale = CFG.fontBorder.getData().scaleX - 0.1F;
                        break;
                     }
                  } else {
                     CFG.fontBorder.getData().setScale(CFG.fontBorder.getData().scaleX - 0.1F);
                     CFG.glyphLayout
                        .setText(CFG.fontBorder, CFG.game.getCiv(CFG.game.getProvince(this.lProvinces.get(this.shortestLine.get(0))).getCivID()).getCivName());
                     if (iDistance > CFG.glyphLayout.width) {
                        this.fontScale = CFG.fontBorder.getData().scaleX + 0.1F;
                        break;
                     }
                  }
               } while (tempNumOfInterations++ != 1000);
            } catch (IndexOutOfBoundsException var9) {
               this.fontScale = 0.1F;
            } catch (NullPointerException var10) {
               this.fontScale = 0.1F;

               try {
                  CFG.game.getCiv(CFG.game.getProvince(this.lProvinces.get(0)).getCivID()).setUpdateRegions(true);
               } catch (IndexOutOfBoundsException var7) {
               } catch (NullPointerException var8) {
               }
            } catch (IllegalStateException var11) {
               this.fontScale = 0.1F;
            }

            this.fontScale = this.fontScale > 20.0F
               ? (this.fontScale *= 0.2F)
               : (
                  this.fontScale > 15.0F
                     ? (this.fontScale *= 0.225F)
                     : (
                        this.fontScale > 10.0F
                           ? (this.fontScale *= 0.25F)
                           : (
                              this.fontScale > 7.5
                                 ? (this.fontScale *= 0.3F)
                                 : (
                                    this.fontScale > 5.0F
                                       ? (this.fontScale *= 0.325F)
                                       : (
                                          this.fontScale > 3.5
                                             ? (this.fontScale *= 0.35F)
                                             : (
                                                this.fontScale > 2.5
                                                   ? (this.fontScale *= 0.375F)
                                                   : (
                                                      this.fontScale > 2.0F
                                                         ? (this.fontScale *= 0.4F)
                                                         : (
                                                            this.fontScale > 1.75
                                                               ? (this.fontScale *= 0.45F)
                                                               : (this.fontScale > 1.5 ? (this.fontScale *= 0.475F) : (this.fontScale *= 0.5F))
                                                         )
                                                   )
                                             )
                                       )
                                 )
                           )
                     )
               );
            CFG.fontBorder.getData().setScale(1.0F);
            this.buildAvaragePoint();
            this.buildDrawData();
         }
      } catch (NullPointerException var12) {
         this.fontScale = 0.1F;

         try {
            CFG.game.getCiv(CFG.game.getProvince(this.lProvinces.get(0)).getCivID()).setUpdateRegions(true);
         } catch (IndexOutOfBoundsException var5) {
         } catch (NullPointerException var6) {
         }
      }
   }

   public final Point_XY canDrawTextProperly(int fromProvinceID, int toProvinceID) {
      this.buildAvaragePoint();
      ArrayList<Point_XY> tempPoints = new ArrayList<>();
      int tX = CFG.game.getProvince(fromProvinceID).getCenterX() + CFG.game.getProvince(fromProvinceID).getShiftX();
      int tX2 = CFG.game.getProvince(toProvinceID).getCenterX() + CFG.game.getProvince(toProvinceID).getShiftX();
      int extra10X = tX + (int)Math.abs((tX2 - tX) * 0.15F) * (tX > tX2 ? -1 : 1);
      int extra10X2 = tX2 + (int)Math.abs((tX2 - tX) * 0.15F) * (tX2 > tX ? -1 : 1);
      int tY = CFG.game.getProvince(fromProvinceID).getCenterY() + CFG.game.getProvince(fromProvinceID).getShiftY();
      int tY2 = CFG.game.getProvince(toProvinceID).getCenterY() + CFG.game.getProvince(toProvinceID).getShiftY();
      int extra10Y = tY + (int)Math.abs((tY2 - tY) * 0.15F) * (tY > tY2 ? -1 : 1);
      int extra10Y2 = tY2 + (int)Math.abs((tY2 - tY) * 0.15F) * (tY2 > tY ? -1 : 1);
      int iPrecission = CFG.game.getCiv(CFG.game.getProvince(fromProvinceID).getCivID()).getCivNameLength() * 10;
      Vector2[] vPoints = new Vector2[iPrecission];
      Vector[] dataSet = new Vector2[]{
         new Vector2(extra10X, extra10Y),
         new Vector2(extra10X, extra10Y),
         new Vector2(this.iAvaragePointPosX, this.iAvaragePointPosY),
         new Vector2(extra10X2, extra10Y2),
         new Vector2(extra10X2, extra10Y2)
      };
      CatmullRomSpline oCatmull = new CatmullRomSpline(dataSet, false);

      for (int i2 = 0; i2 < iPrecission; i2++) {
         vPoints[i2] = new Vector2();
         oCatmull.valueAt(vPoints[i2], i2 / (iPrecission - 1.0F));
      }

      int tempPrecissionWidth = 0;

      for (int i3 = 0; i3 < iPrecission - 1; i3++) {
         tempPrecissionWidth += this.getLineWidth((int)vPoints[i3].x, (int)vPoints[i3].y, (int)vPoints[i3 + 1].x, (int)vPoints[i3 + 1].y);
      }

      tempPoints.add(new Point_XY((int)vPoints[0].x, (int)vPoints[0].y));
      int aceptableWidth = 0;

      try {
         aceptableWidth = tempPrecissionWidth / (CFG.game.getCiv(CFG.game.getProvince(fromProvinceID).getCivID()).getCivNameLength() - 1);
      } catch (ArithmeticException var20) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var20);
         }
      }

      tX = 0;

      for (int i = 1; i < CFG.game.getCiv(CFG.game.getProvince(fromProvinceID).getCivID()).getCivNameLength(); i++) {
         for (int currentPointsWidth = 0; tX < iPrecission - 1; tX++) {
            extra10X = this.getLineWidth((int)vPoints[tX].x, (int)vPoints[tX].y, (int)vPoints[tX + 1].x, (int)vPoints[tX + 1].y);
            if (currentPointsWidth + extra10X >= aceptableWidth && currentPointsWidth <= aceptableWidth) {
               tempPoints.add(new Point_XY((int)vPoints[tX].x, (int)vPoints[tX].y));
               tX2 = currentPointsWidth + extra10X;
               break;
            }

            currentPointsWidth += extra10X;
         }
      }

      for (int var21 = tempPoints.size() - 1; var21 >= 0; var21--) {
         tX2 = CFG.game.setProvinceID_Point(tempPoints.get(var21).getPosX(), tempPoints.get(var21).getPosY() - CFG.TEXT_HEIGHT / 2);
         if (tX2 >= 0 && !CFG.game.getProvince(tX2).getSeaProvince() && CFG.game.getProvince(fromProvinceID).getCivID() != CFG.game.getProvince(tX2).getCivID()
            )
          {
            return tempPoints.get(var21);
         }
      }

      return null;
   }

   public final void buildDrawData() {
      CFG.fontBorder.getData().setScale(this.fontScale);
      this.iCharMaxWidth = 1;
      this.iCharMaxHeight = 1;

      try {
         for (int i2 = 0; i2 < CFG.game.getCiv(CFG.game.getProvince(this.shortestLine.get(0)).getCivID()).getCivNameLength(); i2++) {
            CFG.glyphLayout.setText(CFG.fontBorder, "" + CFG.game.getCiv(CFG.game.getProvince(this.shortestLine.get(0)).getCivID()).getCivNameCharacter(i2));
            if (CFG.glyphLayout.width > this.iCharMaxWidth) {
               this.iCharMaxWidth = (int)CFG.glyphLayout.width;
            }

            if (CFG.glyphLayout.height > this.iCharMaxWidth) {
               this.iCharMaxHeight = (int)CFG.glyphLayout.height;
            }
         }
      } catch (IndexOutOfBoundsException var35) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var35);
         }
      } catch (NullPointerException var36) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var36);
         }

         try {
            CFG.game.getCiv(CFG.game.getProvince(this.lProvinces.get(0)).getCivID()).setUpdateRegions(true);
         } catch (IndexOutOfBoundsException var27) {
         } catch (NullPointerException var28) {
         }
      } catch (IllegalStateException var37) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var37);
         }
      }

      this.fAngle = (float)(
         Math.atan2(
               CFG.game.getProvince(this.getProvince(this.shortestLine.get(0))).getCenterY()
                  + CFG.game.getProvince(this.getProvince(this.shortestLine.get(0))).getShiftY()
                  - (
                     CFG.game.getProvince(this.getProvince(this.shortestLine.get(1))).getCenterY()
                        + CFG.game.getProvince(this.getProvince(this.shortestLine.get(1))).getShiftY()
                  ),
               -(
                     CFG.game.getProvince(this.getProvince(this.shortestLine.get(0))).getCenterX()
                        + CFG.game.getProvince(this.getProvince(this.shortestLine.get(0))).getShiftX()
                  )
                  + CFG.game.getProvince(this.getProvince(this.shortestLine.get(1))).getCenterX()
                  + CFG.game.getProvince(this.getProvince(this.shortestLine.get(1))).getShiftX()
            )
            * 180.0
            / Math.PI
      );
      this.lPoints.clear();
      this.lPointsAngle.clear();
      int fromProvinceID = this.lProvinces.get(this.shortestLine.get(0));
      int toProvinceID = this.lProvinces.get(this.shortestLine.get(1));
      int tX = CFG.game.getProvince(fromProvinceID).getCenterX() + CFG.game.getProvince(fromProvinceID).getShiftX();
      int tX2 = CFG.game.getProvince(toProvinceID).getCenterX() + CFG.game.getProvince(toProvinceID).getShiftX();
      int extra10X = tX + (int)Math.abs((tX2 - tX) * 0.15F) * (tX > tX2 ? -1 : 1);
      int extra10X2 = tX2 + (int)Math.abs((tX2 - tX) * 0.15F) * (tX2 > tX ? -1 : 1);
      int tY = CFG.game.getProvince(fromProvinceID).getCenterY() + CFG.game.getProvince(fromProvinceID).getShiftY();
      int tY2 = CFG.game.getProvince(toProvinceID).getCenterY() + CFG.game.getProvince(toProvinceID).getShiftY();
      int extra10Y = tY + (int)Math.abs((tY2 - tY) * 0.15F) * (tY > tY2 ? -1 : 1);
      int extra10Y2 = tY2 + (int)Math.abs((tY2 - tY) * 0.15F) * (tY2 > tY ? -1 : 1);
      int iPrecission = CFG.game.getCiv(CFG.game.getProvince(fromProvinceID).getCivID()).getCivNameLength() * 10;
      Vector2[] vPoints = new Vector2[iPrecission];
      Vector[] dataSet = new Vector2[]{
         new Vector2(extra10X, extra10Y),
         new Vector2(extra10X, extra10Y),
         new Vector2(this.iAvaragePointPosX, this.iAvaragePointPosY),
         new Vector2(extra10X2, extra10Y2),
         new Vector2(extra10X2, extra10Y2)
      };
      CatmullRomSpline oCatmull = new CatmullRomSpline(dataSet, false);

      for (int i3 = 0; i3 < iPrecission; i3++) {
         vPoints[i3] = new Vector2();
         oCatmull.valueAt(vPoints[i3], i3 / (iPrecission - 1.0F));
      }

      int tempPrecissionWidth = 0;

      for (int i4 = 0; i4 < iPrecission - 1; i4++) {
         tempPrecissionWidth += this.getLineWidth((int)vPoints[i4].x, (int)vPoints[i4].y, (int)vPoints[i4 + 1].x, (int)vPoints[i4 + 1].y);
      }

      this.lPoints.add(new Point_XY((int)vPoints[0].x, (int)vPoints[0].y));
      int aceptableWidth = 0;

      try {
         aceptableWidth = tempPrecissionWidth / (CFG.game.getCiv(CFG.game.getProvince(fromProvinceID).getCivID()).getCivNameLength() - 1);
      } catch (ArithmeticException var34) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var34);
         }
      }

      toProvinceID = 0;

      for (int i = 1; i < CFG.game.getCiv(CFG.game.getProvince(fromProvinceID).getCivID()).getCivNameLength(); i++) {
         for (int currentPointsWidth = 0; toProvinceID < iPrecission - 1; toProvinceID++) {
            tX2 = this.getLineWidth(
               (int)vPoints[toProvinceID].x, (int)vPoints[toProvinceID].y, (int)vPoints[toProvinceID + 1].x, (int)vPoints[toProvinceID + 1].y
            );
            if (currentPointsWidth + tX2 >= aceptableWidth && currentPointsWidth <= aceptableWidth) {
               this.lPoints.add(new Point_XY((int)vPoints[toProvinceID].x, (int)vPoints[toProvinceID].y));
               tX = currentPointsWidth + tX2;
               break;
            }

            currentPointsWidth += tX2;
         }
      }

      try {
         for (int var38 = 0; var38 < CFG.game.getCiv(CFG.game.getProvince(fromProvinceID).getCivID()).getCivNameLength(); var38++) {
            CFG.glyphLayout.setText(CFG.fontBorder, "" + CFG.game.getCiv(CFG.game.getProvince(fromProvinceID).getCivID()).getCivNameCharacter(var38));
            this.lPoints.get(var38).setPosX(this.lPoints.get(var38).getPosX() - (int)(CFG.glyphLayout.width / 2.0F));
            float tempPointsAngle = 0.0F;

            try {
               float var45;
               if (var38 < CFG.game.getCiv(CFG.game.getProvince(fromProvinceID).getCivID()).getCivNameLength() - 1) {
                  float var44 = this.getLinesAngle(
                     this.lPoints.get(var38).getPosX(),
                     this.lPoints.get(var38).getPosY(),
                     this.lPoints.get(var38 + 1).getPosX(),
                     this.lPoints.get(var38 + 1).getPosY()
                  );
                  var45 = (
                        var44
                           + this.getLinesAngle(
                              this.lPoints.get(var38 - 1).getPosX(),
                              this.lPoints.get(var38 - 1).getPosY(),
                              this.lPoints.get(var38).getPosX(),
                              this.lPoints.get(var38).getPosY()
                           )
                     )
                     / 2.0F;
               } else {
                  var45 = this.getLinesAngle(
                     this.lPoints.get(var38 - 1).getPosX(),
                     this.lPoints.get(var38 - 1).getPosY(),
                     this.lPoints.get(var38).getPosX(),
                     this.lPoints.get(var38).getPosY()
                  );
               }

               this.lPointsAngle.add(var45);
            } catch (IndexOutOfBoundsException var29) {
               if (CFG.LOGS) {
                  CFG.exceptionStack(var29);
               }

               if (var38 == 0) {
                  try {
                     this.lPointsAngle
                        .add(
                           this.getLinesAngle(
                              this.lPoints.get(var38).getPosX(),
                              this.lPoints.get(var38).getPosY(),
                              this.lPoints.get(var38 + 1).getPosX(),
                              this.lPoints.get(var38 + 1).getPosY()
                           )
                        );
                  } catch (IndexOutOfBoundsException var26) {
                     this.lPointsAngle.add(this.fAngle);
                  }
               } else {
                  try {
                     this.lPointsAngle
                        .add(
                           this.getLinesAngle(
                              this.lPoints.get(var38 - 1).getPosX(),
                              this.lPoints.get(var38 - 1).getPosY(),
                              this.lPoints.get(var38).getPosX(),
                              this.lPoints.get(var38).getPosY()
                           )
                        );
                  } catch (IndexOutOfBoundsException var25) {
                     this.lPointsAngle.add(this.fAngle);
                  }
               }
            } catch (NullPointerException var30) {
               if (CFG.LOGS) {
                  CFG.exceptionStack(var30);
               }

               if (var38 == 0) {
                  try {
                     this.lPointsAngle
                        .add(
                           this.getLinesAngle(
                              this.lPoints.get(var38).getPosX(),
                              this.lPoints.get(var38).getPosY(),
                              this.lPoints.get(var38 + 1).getPosX(),
                              this.lPoints.get(var38 + 1).getPosY()
                           )
                        );
                  } catch (IndexOutOfBoundsException var24) {
                     this.lPointsAngle.add(this.fAngle);
                  }
               } else {
                  try {
                     this.lPointsAngle
                        .add(
                           this.getLinesAngle(
                              this.lPoints.get(var38 - 1).getPosX(),
                              this.lPoints.get(var38 - 1).getPosY(),
                              this.lPoints.get(var38).getPosX(),
                              this.lPoints.get(var38).getPosY()
                           )
                        );
                  } catch (IndexOutOfBoundsException var23) {
                     this.lPointsAngle.add(this.fAngle);
                  }
               }

               try {
                  CFG.game.getCiv(CFG.game.getProvince(this.lProvinces.get(0)).getCivID()).setUpdateRegions(true);
               } catch (IndexOutOfBoundsException var21) {
               } catch (NullPointerException var22) {
               }
            }
         }
      } catch (IndexOutOfBoundsException var31) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var31);
         }
      } catch (NullPointerException var32) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var32);
         }

         try {
            CFG.game.getCiv(CFG.game.getProvince(this.lProvinces.get(0)).getCivID()).setUpdateRegions(true);
         } catch (IndexOutOfBoundsException var19) {
         } catch (NullPointerException var20) {
         }
      } catch (IllegalStateException var33) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var33);
         }
      }
   }

   public final void buildAvaragePoint() {
      long lAvarageX = 0L;
      long lAvarageY = 0L;
      int tempMinX = CFG.game.getProvince(this.lProvinces.get(this.shortestLine.get(0))).getMinX();
      int tempMaxX = CFG.game.getProvince(this.lProvinces.get(this.shortestLine.get(0))).getMaxX();
      int tempMinY = CFG.game.getProvince(this.lProvinces.get(this.shortestLine.get(0))).getMinY();
      int tempMaxY = CFG.game.getProvince(this.lProvinces.get(this.shortestLine.get(0))).getMaxY();
      if (CFG.game.getProvince(this.lProvinces.get(this.shortestLine.get(1))).getMinX() < tempMinX) {
         tempMinX = CFG.game.getProvince(this.lProvinces.get(this.shortestLine.get(1))).getMinX();
      }

      if (CFG.game.getProvince(this.lProvinces.get(this.shortestLine.get(1))).getMaxX() > tempMaxX) {
         tempMaxX = CFG.game.getProvince(this.lProvinces.get(this.shortestLine.get(1))).getMaxX();
      }

      if (CFG.game.getProvince(this.lProvinces.get(this.shortestLine.get(1))).getMinY() < tempMinY) {
         tempMinY = CFG.game.getProvince(this.lProvinces.get(this.shortestLine.get(1))).getMinY();
      }

      if (CFG.game.getProvince(this.lProvinces.get(this.shortestLine.get(1))).getMaxY() > tempMaxY) {
         tempMaxY = CFG.game.getProvince(this.lProvinces.get(this.shortestLine.get(1))).getMaxY();
      }

      int tSize = 0;

      for (int i = 0; i < this.getProvincesSize(); i++) {
         if (CFG.game.getProvince(this.getProvince(i)).getCenterX() + CFG.game.getProvince(this.getProvince(i)).getShiftX() >= tempMinX
            && CFG.game.getProvince(this.getProvince(i)).getCenterX() + CFG.game.getProvince(this.getProvince(i)).getShiftX() <= tempMaxX) {
            if (CFG.game.getProvince(this.getProvince(i)).getCenterY() + CFG.game.getProvince(this.getProvince(i)).getShiftY() >= tempMinY
               && CFG.game.getProvince(this.getProvince(i)).getCenterY() + CFG.game.getProvince(this.getProvince(i)).getShiftY() <= tempMaxY) {
               lAvarageX += CFG.game.getProvince(this.getProvince(i)).getCenterX() + CFG.game.getProvince(this.getProvince(i)).getShiftX();
               lAvarageY += CFG.game.getProvince(this.getProvince(i)).getCenterY() + CFG.game.getProvince(this.getProvince(i)).getShiftY();
               tSize++;
            }
         } else if ((
               CFG.game.getProvince(this.getProvince(i)).getMinX() > tempMinX && CFG.game.getProvince(this.getProvince(i)).getMinX() <= tempMaxX
                  || CFG.game.getProvince(this.getProvince(i)).getMaxX() > tempMinX && CFG.game.getProvince(this.getProvince(i)).getMaxX() <= tempMaxX
            )
            && (
               CFG.game.getProvince(this.getProvince(i)).getMinY() >= tempMinY && CFG.game.getProvince(this.getProvince(i)).getMinY() <= tempMaxY
                  || CFG.game.getProvince(this.getProvince(i)).getMaxY() >= tempMinY && CFG.game.getProvince(this.getProvince(i)).getMaxY() <= tempMaxY
            )) {
            lAvarageX += CFG.game.getProvince(this.getProvince(i)).getCenterX() + CFG.game.getProvince(this.getProvince(i)).getShiftX();
            lAvarageY += CFG.game.getProvince(this.getProvince(i)).getCenterY() + CFG.game.getProvince(this.getProvince(i)).getShiftY();
            tSize++;
         }
      }

      if (tSize == 0) {
         tSize = 1;
      }

      this.iAvaragePointPosX = (int)(lAvarageX / tSize);
      this.iAvaragePointPosY = (int)(lAvarageY / tSize);
   }

   public final void drawCivilizationName(
      SpriteBatch oSB, int fromProvinceID, int toProvinceID, float fontScale, float nAngle, int nCharMaxWidth, int nCharMaxHeight
   ) {
      CFG.fontBorder.getData().setScale(fontScale);

      for (int i = 0; i < CFG.game.getCiv(CFG.game.getProvince(fromProvinceID).getCivID()).getCivNameLength(); i++) {
         CFG.drawTextRotatedBorder(
            oSB,
            "" + CFG.game.getCiv(CFG.game.getProvince(fromProvinceID).getCivID()).getCivNameCharacter(i),
            CFG.map.getMapCoordinates().getPosX() + this.lPoints.get(i).getPosX(),
            CFG.map.getMapCoordinates().getPosY() + this.lPoints.get(i).getPosY() - nCharMaxHeight / 2,
            new Color(1.0F, 1.0F, 1.0F, Game_Render.CIVILIZATION_NAMES_ALPHA),
            this.lPointsAngle.get(i)
         );
      }
   }

   public final void drawCivilizationName_SecondSideOfMap(
      SpriteBatch oSB, int fromProvinceID, int toProvinceID, float fontScale, float nAngle, int nCharMaxWidth, int nCharMaxHeight
   ) {
      if (CFG.game.getProvince(fromProvinceID).getTranslateProvincePosX() > 0) {
         CFG.fontBorder.getData().setScale(fontScale);

         for (int i = 0; i < CFG.game.getCiv(CFG.game.getProvince(fromProvinceID).getCivID()).getCivNameLength(); i++) {
            CFG.drawTextRotatedBorder(
               oSB,
               "" + CFG.game.getCiv(CFG.game.getProvince(fromProvinceID).getCivID()).getCivNameCharacter(i),
               CFG.map.getMapCoordinates().getSecondSideOfMap_MoveX() + CFG.map.getMapCoordinates().getPosX() + this.lPoints.get(i).getPosX(),
               CFG.map.getMapCoordinates().getPosY() + this.lPoints.get(i).getPosY() - nCharMaxHeight / 2,
               new Color(1.0F, 1.0F, 1.0F, Game_Render.CIVILIZATION_NAMES_ALPHA),
               this.lPointsAngle.get(i)
            );
         }
      }
   }

   public float getLinesAngle(int fromPosX, int fromPosY, int toPosX, int toPosY) {
      return (float)(Math.atan2(fromPosY - toPosY, -fromPosX + toPosX) * 180.0 / Math.PI);
   }

   public int getLineWidth(int fromCenterPosProvinceID, int toCenterPosProvinceID) {
      return this.getLineWidth(
         CFG.game.getProvince(this.lProvinces.get(fromCenterPosProvinceID)).getCenterX()
            + CFG.game.getProvince(this.lProvinces.get(fromCenterPosProvinceID)).getShiftX(),
         CFG.game.getProvince(this.lProvinces.get(fromCenterPosProvinceID)).getCenterY()
            + CFG.game.getProvince(this.lProvinces.get(fromCenterPosProvinceID)).getShiftY(),
         CFG.game.getProvince(this.lProvinces.get(toCenterPosProvinceID)).getCenterX()
            + CFG.game.getProvince(this.lProvinces.get(toCenterPosProvinceID)).getShiftX(),
         CFG.game.getProvince(this.lProvinces.get(toCenterPosProvinceID)).getCenterY()
            + CFG.game.getProvince(this.lProvinces.get(toCenterPosProvinceID)).getShiftY()
      );
   }

   public int getLineWidth(int fromPosX, int fromPosY, int toPosX, int toPosY) {
      return (int)Math.sqrt(Math.pow(fromPosX - toPosX, 2.0) + Math.pow(fromPosY - toPosY, 2.0));
   }

   public final int getProvince(int i) {
      return this.lProvinces.get(i);
   }

   public final int getProvincesSize() {
      return this.iProvincesSize;
   }

   public final boolean getSeaAccess() {
      return this.seaAccess;
   }

   public final boolean getSeaAccess_HavePort() {
      return this.seaAccess_HavePort;
   }

   public final boolean getSeaAccess_HavePort_Check() {
      for (int i = 0; i < this.getProvincesSize(); i++) {
         if (CFG.game.getProvince(this.getProvince(i)).getLevelOfPort() > 0) {
            return true;
         }
      }

      return false;
   }

   public final void setSeaAccess_HavePort(boolean seaAccess_HavePort) {
      this.seaAccess_HavePort = seaAccess_HavePort;
   }

   public final boolean getHaveNotOccupiedProvince() {
      return this.haveNotOccupiedProvince;
   }

   public final List<Integer> getShortestPath() {
      return this.shortestLine;
   }

   public final float getFontScale() {
      return this.fontScale;
   }

   public final int getRegionID() {
      return this.iRegionID;
   }

   public final void setRegionID(int iRegionID) {
      this.iRegionID = iRegionID;

      for (int i = 0; i < this.iProvincesSize; i++) {
         CFG.game.getProvince(i).setCivRegionID(iRegionID);
      }
   }

   public final float getAngle() {
      return this.fAngle;
   }

   public final int getCharMaxWidth() {
      return this.iCharMaxWidth;
   }

   public final int getCharMaxHeight() {
      return this.iCharMaxHeight;
   }

   public final boolean getIsSupplied() {
      return this.isSupplied;
   }

   public final boolean setIsSupplied(boolean isSupplied) {
      this.isSupplied = isSupplied;
      return this.getIsSupplied();
   }
}
