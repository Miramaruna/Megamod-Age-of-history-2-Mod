package age.of.civilizations2.jakowski.lukasz;

public class Map_Scale {
   public static float MINSCALE = 0.25F;
   public static final float MAXSCALE = 500.0F;
   public static float STANDARD_SCALE = 1.0F;
   public float currentScale = 1.0F;
   public float newScale = -1.0F;
   public float startScale = -1.0F;
   public boolean scaleByYAxis = true;
   public int iStartScalePosY;
   public int iStartScalePosY2;
   public int iStartScalePosX;
   public int iStartScalePosX2;
   public int iStartScaleMapPosX = -1;
   public int iStartScaleMapPosY = -1;
   public boolean scaleMode = false;
   public float fMinimapScaleX;
   public float fMinimapScaleY;
   public static final short REQUIRED_TIME_TO_RESET_SCALE = 175;
   public float fScaleBeforeReset = 1.5F;
   public float fDiffrenceScale;
   public boolean scaleChangeByTouch = true;
   public static int SCALE_ANIMATION_TIME = 125;
   public long iScaleAnimationTime = 0L;
   public float fStartScaling_Scale = 1.0F;
   public float fScaleAnimation_PercX = 1.0F;
   public float fScaleAnimation_PercY = 1.0F;

   public final void update() {
      if (this.fDiffrenceScale != 0.0F) {
         this.updateScale();
      }
   }

   public final void startScaleTheMap2(int nX, int nX2, int nY, int nY2) {
      this.scaleMode = true;
      this.iStartScalePosX = nX;
      this.iStartScalePosX2 = nX2;
      this.iStartScalePosY = nY;
      this.iStartScalePosY2 = nY2;
   }

   public final void scaleTheMap2(int nX, int nX2, int nY, int nY2) {
      if (this.iStartScalePosX != nX || this.iStartScalePosY != nY) {
         float fScaleDifference = 0.0F;
         if (this.iStartScalePosX != nX) {
            fScaleDifference += (this.iStartScalePosX - nX) / 150.0F / CFG.GUI_SCALE;
         } else if (this.iStartScalePosY != nY) {
            fScaleDifference += (this.iStartScalePosY - nY) / 150.0F / CFG.GUI_SCALE;
         }

         this.setNewScale_ByTouch2(fScaleDifference, nX2, nY2);
      } else if (this.iStartScalePosX2 != nX2 || this.iStartScalePosY2 != nY2) {
         float fScaleDifference = 0.0F;
         if (this.iStartScalePosX2 != nX2) {
            this.iStartScalePosX2 = (int)(this.iStartScalePosX2 + (this.iStartScalePosX2 - nX) / 150.0F / CFG.GUI_SCALE);
         } else if (this.iStartScalePosY2 != nY) {
            fScaleDifference += (this.iStartScalePosY2 - nY) / 150.0F / CFG.GUI_SCALE;
         }

         this.setNewScale_ByTouch2(fScaleDifference, nX2, nY2);
      }

      this.iStartScalePosX = nX;
      this.iStartScalePosX2 = nX2;
      this.iStartScalePosY = nY;
      this.iStartScalePosY2 = nY2;
      CFG.map.getMapTouchManager().setUpdateStartMovePosX(true);
      CFG.map.getMapTouchManager().setUpdateStartMovePosY(true);
      this.resetScaleAnimation();
   }

   public final void setNewScale_ByTouch2(float nDifference, int nXCenter, int nYCenter) {
      if (nDifference != 0.0F) {
         this.newScale = this.currentScale + nDifference;
         if (this.newScale > 500.0F) {
            this.newScale = 500.0F;
         } else if (this.newScale < MINSCALE) {
            this.newScale = MINSCALE;
         }

         this.scaleChangeByTouch = true;
         if (this.newScale > 0.0F) {
            if (this.currentScale != this.newScale) {
               this.currentScale = this.newScale;
               this.newScale = 0.0F;
            }

            CFG.map.getMapCoordinates().checkPositionOfMapY();
            CFG.map.getMapCoordinates().checkPositionOfMapX();
            CFG.map.getMapCoordinates().updateSecondSideOfMap();
         }
      }
   }

   public final void startScaleTheMap(int nX, int nX2, int nY, int nY2) {
      this.scaleMode = true;
      if (Math.max(nX, nX2) - Math.min(nX, nX2) > Math.max(nY, nY2) - Math.min(nY, nY2)) {
         this.scaleByYAxis = false;
         this.iStartScalePosY = nX;
         this.iStartScalePosY2 = nX2;
      } else {
         this.scaleByYAxis = true;
         this.iStartScalePosY = nY;
         this.iStartScalePosY2 = nY2;
      }
   }

   public final void scaleTheMap(int nX, int nX2, int nY, int nY2) {
      if (this.scaleByYAxis) {
         this.scaleTheMap(nY, nY2, Math.abs((nX + nX2) / 2.0F), Math.abs((nY + nY2) / 2.0F));
      } else {
         this.scaleTheMap(nX, nX2, Math.abs((nX + nX2) / 2.0F), Math.abs((nY + nY2) / 2.0F));
      }
   }

   public final void scaleTheMap(int nY, int nY2, float fCenterX, float fCenterY) {
      if (this.startScale < 0.0F) {
         this.iStartScaleMapPosX = CFG.map.getMapCoordinates().getPosX();
         this.iStartScaleMapPosY = CFG.map.getMapCoordinates().getPosY();
         this.startScale = this.currentScale;
      }

      if (this.iStartScalePosY != nY) {
         this.setNewCurrentScaleByTouch(
            this.currentScale + (nY < nY2 ? this.iStartScalePosY - nY : nY - this.iStartScalePosY) / 150.0F / CFG.GUI_SCALE, fCenterX, fCenterY
         );
         this.iStartScalePosY = nY;
         CFG.map.getMapTouchManager().setUpdateStartMovePosX(true);
         CFG.map.getMapTouchManager().setUpdateStartMovePosY(true);
         this.resetScaleAnimation();
      }

      if (this.iStartScalePosY2 != nY2) {
         this.setNewCurrentScaleByTouch(
            this.currentScale + (nY > nY2 ? this.iStartScalePosY2 - nY2 : nY2 - this.iStartScalePosY2) / 150.0F / CFG.GUI_SCALE, fCenterX, fCenterY
         );
         this.iStartScalePosY2 = nY2;
         CFG.map.getMapTouchManager().setUpdateStartMovePosX(true);
         CFG.map.getMapTouchManager().setUpdateStartMovePosY(true);
         this.resetScaleAnimation();
      }
   }

   public final void resetScaleOfMap(long nActionDownTime) {
      if (nActionDownTime > 0L
         && this.fDiffrenceScale == 0.0F
         && System.currentTimeMillis() < CFG.map.getMapTouchManager().getActionDownTime() + 175L
         && !CFG.map.getMapCoordinates().getDisableMovingMap()) {
         this.resetScaleAnimation();
         this.scaleChangeByTouch = true;
         this.fStartScaling_Scale = this.currentScale;
         if (this.currentScale != STANDARD_SCALE) {
            this.fScaleBeforeReset = this.currentScale;
            this.fDiffrenceScale = STANDARD_SCALE - this.currentScale;
         } else {
            this.fDiffrenceScale = this.fScaleBeforeReset - this.currentScale;
            this.fScaleBeforeReset = STANDARD_SCALE;
         }

         this.iScaleAnimationTime = System.currentTimeMillis();
         this.updateScaleAnimation_PercXY(Touch.getMousePosX(), Touch.getMousePosY());
         SCALE_ANIMATION_TIME = 135;
         CFG.map.getMapTouchManager().setUpdateStartMovePosX(true);
         CFG.map.getMapTouchManager().setUpdateStartMovePosY(true);
         CFG.map.getMapScroll().resetScrollInfo();
      }

      CFG.map.getMapTouchManager().setActionDownTime(nActionDownTime);
   }

   public final void resetStartScalePosition() {
      this.iStartScalePosY2 = -1;
      this.iStartScalePosY = -1;
   }

   public final void resetScaleInfo() {
      this.resetStartScalePosition();
      this.scaleMode = false;
      this.startScale = -1.0F;
   }

   public final void setNewCurrentScaleByTouch(float nCurrentScale, float fCenterX, float fCenterY) {
      this.newScale = nCurrentScale > 500.0F ? 500.0F : (nCurrentScale < MINSCALE ? MINSCALE : nCurrentScale);
      this.scaleChangeByTouch = true;
      if (this.newScale > 0.0F) {
         if (this.currentScale != this.newScale) {
            this.fScaleAnimation_PercX = fCenterX / CFG.GAME_WIDTH;
            this.fScaleAnimation_PercY = fCenterY / CFG.GAME_HEIGHT;
            if (this.startScale < this.currentScale) {
               CFG.map
                  .getMapCoordinates()
                  .setNewPosX(this.iStartScaleMapPosX - (int)((CFG.GAME_WIDTH / this.startScale - CFG.GAME_WIDTH / this.newScale) * this.fScaleAnimation_PercX));
               CFG.map
                  .getMapCoordinates()
                  .setNewPosY(
                     this.iStartScaleMapPosY - (int)((CFG.GAME_HEIGHT / this.startScale - CFG.GAME_HEIGHT / this.newScale) * this.fScaleAnimation_PercY)
                  );
            } else {
               CFG.map
                  .getMapCoordinates()
                  .setNewPosX(this.iStartScaleMapPosX - (int)((CFG.GAME_WIDTH / this.startScale - CFG.GAME_WIDTH / this.newScale) / 2.0F));
               CFG.map
                  .getMapCoordinates()
                  .setNewPosY(this.iStartScaleMapPosY - (int)((CFG.GAME_HEIGHT / this.startScale - CFG.GAME_HEIGHT / this.newScale) / 2.0F));
            }

            this.currentScale = this.newScale;
            this.newScale = 0.0F;
         }

         CFG.map.getMapCoordinates().checkPositionOfMapY();
         CFG.map.getMapCoordinates().checkPositionOfMapX();
         CFG.map.getMapCoordinates().updateSecondSideOfMap();
      }
   }

   public final void setNewCurrentScaleByButton2(float newScale) {
      if (this.fDiffrenceScale != 0.0F) {
         newScale += this.fDiffrenceScale;
      }

      if ((newScale = this.currentScale + newScale) >= 0.995F && newScale <= 1.005F) {
         newScale = 1.0F;
      }

      if (newScale != this.currentScale && newScale >= MINSCALE) {
         if (System.currentTimeMillis() - this.iScaleAnimationTime > SCALE_ANIMATION_TIME) {
            this.resetScaleAnimation();
            this.scaleChangeByTouch = false;
            SCALE_ANIMATION_TIME = 50;
            this.fStartScaling_Scale = this.currentScale;
            this.fDiffrenceScale = newScale - this.currentScale;
            this.fScaleBeforeReset = newScale;
            this.iScaleAnimationTime = System.currentTimeMillis();
            this.updateScaleAnimation_PercXY(Touch.getMousePosX(), Touch.getMousePosY());
         }

         CFG.map.getMapScroll().resetScrollInfo();
      }
   }

   public final void updateScaleAnimation_PercXY(int nPosX, int nPosY) {
      this.fScaleAnimation_PercX = (float)nPosX / CFG.GAME_WIDTH;
      this.fScaleAnimation_PercY = (float)nPosY / CFG.GAME_HEIGHT;
   }

   public final void updateScale() {
      float tempScale = this.currentScale;
      this.setCurrentScale(
         this.fStartScaling_Scale + this.fDiffrenceScale * (float)(System.currentTimeMillis() - this.iScaleAnimationTime) / SCALE_ANIMATION_TIME
      );
      if (System.currentTimeMillis() - this.iScaleAnimationTime > SCALE_ANIMATION_TIME) {
         if (this.fScaleBeforeReset != STANDARD_SCALE && this.scaleChangeByTouch) {
            this.setCurrentScale(STANDARD_SCALE);
         }

         this.resetScaleAnimation();
      }

      if (this.fStartScaling_Scale < this.currentScale) {
         CFG.map
            .getMapCoordinates()
            .setNewPosX(
               CFG.map.getMapCoordinates().getPosX() - (int)((CFG.GAME_WIDTH / tempScale - CFG.GAME_WIDTH / this.currentScale) * this.fScaleAnimation_PercX)
            );
         CFG.map
            .getMapCoordinates()
            .setNewPosY(
               CFG.map.getMapCoordinates().getPosY() - (int)((CFG.GAME_HEIGHT / tempScale - CFG.GAME_HEIGHT / this.currentScale) * this.fScaleAnimation_PercY)
            );
      } else {
         CFG.map
            .getMapCoordinates()
            .setNewPosX(CFG.map.getMapCoordinates().getPosX() - (int)((CFG.GAME_WIDTH / tempScale - CFG.GAME_WIDTH / this.currentScale) / 2.0F));
         CFG.map
            .getMapCoordinates()
            .setNewPosY(CFG.map.getMapCoordinates().getPosY() - (int)((CFG.GAME_HEIGHT / tempScale - CFG.GAME_HEIGHT / this.currentScale) / 2.0F));
      }
   }

   public final void resetScaleAnimation() {
      this.fDiffrenceScale = 0.0F;
      this.iScaleAnimationTime = 0L;
   }

   public final void setCurrentScale(float currentScale) {
      if (500.0F < currentScale) {
         currentScale = 500.0F;
      } else if (MINSCALE > currentScale) {
         currentScale = MINSCALE;
      }

      this.currentScale = currentScale;
      CFG.setRender_3(true);
      CFG.game.setUpdateProvincesInView(true);
   }

   public final float getCurrentScale() {
      return this.currentScale;
   }

   public final float getMinimapScaleX() {
      return this.fMinimapScaleX;
   }

   public final float getMinimapScaled_ScaleX() {
      return CFG.map.getMapBG().iMinimapScaled_Width / (CFG.map.getMapBG().getMinimapWidth() - 2.0F);
   }

   public final float getMinimapScaled_ScaleY() {
      return CFG.map.getMapBG().iMinimapScaled_Height / (CFG.map.getMapBG().getMinimapHeight() - 2.0F);
   }

   public final void updateMinimapScaleXY() {
      this.fMinimapScaleX = CFG.map.getMapBG().getWidth() / (CFG.map.getMapBG().getMinimapWidth() - 2.0F);
      this.fMinimapScaleY = CFG.map.getMapBG().getHeight() / (CFG.map.getMapBG().getMinimapHeight() - 2.0F);
   }

   public final float getMinimapScaleY() {
      return this.fMinimapScaleY;
   }

   public final boolean getScaleMode() {
      return this.scaleMode;
   }

   public final void setScaleMode(boolean scaleMode) {
      this.scaleMode = scaleMode;
   }

   public final int getStartScalePosY() {
      return this.iStartScalePosY;
   }

   public final void setScaleBeforeReset(float fScaleBeforeReset) {
      this.fScaleBeforeReset = fScaleBeforeReset;
   }
}
