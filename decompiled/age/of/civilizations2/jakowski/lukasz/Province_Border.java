package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.GdxRuntimeException;
import java.util.ArrayList;
import java.util.List;

public class Province_Border {
   public List<Province_Border_Line> provinceBorderLine = new ArrayList<>();
   public int iProvinceBorderLineSize;
   public int iLineWidth = 0;
   public short withProvinceID;
   public boolean civilizationBorder = false;
   public boolean wastelandBorder = false;
   public Province_Border.DrawProvince_Border drawProvince_Border;
   public List<Short> lPointsX = new ArrayList<>();
   public List<Short> lPointsY = new ArrayList<>();

   public Province_Border(int withProvinceID, List<Short> nPointsX, List<Short> nPointsY) {
      int iSize = nPointsX.size() - 1;

      for (int i = 0; i < iSize; i++) {
         this.provinceBorderLine
            .add(
               new Province_Border_Line(
                  nPointsX.get(i) * CFG.map.getMapBG().getMapScale(),
                  nPointsY.get(i) * CFG.map.getMapBG().getMapScale(),
                  nPointsX.get(i + 1) * CFG.map.getMapBG().getMapScale(),
                  nPointsY.get(i + 1) * CFG.map.getMapBG().getMapScale()
               )
            );
      }

      iSize = nPointsX.size();

      for (int var6 = 0; var6 < iSize; var6++) {
         this.lPointsX.add(nPointsX.get(var6));
         this.lPointsY.add(nPointsY.get(var6));
      }

      this.iProvinceBorderLineSize = this.provinceBorderLine.size();
      this.withProvinceID = (short)withProvinceID;

      for (int var7 = 0; var7 < this.iProvinceBorderLineSize; var7++) {
         this.iLineWidth = this.iLineWidth + this.provinceBorderLine.get(var7).getWidth();
      }
   }

   public final void updateDrawProvinceBorder(int nProvinceID) {
      try {
         try {
            if (CFG.game.getProvince(this.withProvinceID).getWasteland() >= 0 && CFG.game.getProvince(nProvinceID).getWasteland() < 0
               || CFG.game.getProvince(nProvinceID).getWasteland() >= 0 && CFG.game.getProvince(this.withProvinceID).getWasteland() < 0) {
               if (!CFG.getMetProvince(nProvinceID) && !CFG.getMetProvince(nProvinceID)) {
                  this.wastelandBorder = false;
                  this.civilizationBorder = false;
               } else {
                  this.wastelandBorder = true;
                  this.civilizationBorder = true;
               }
            }
         } catch (IndexOutOfBoundsException var3) {
         }

         if (this.getIsWastelandBorder()) {
            this.drawProvince_Border = this.getIsCivilizationBorder() ? new Province_Border.DrawProvince_Border() {
               @Override
               public void draw(SpriteBatch oSB, int nTranslateProvincePosX) {
                  oSB.setColor(CFG.COLOR_PROVINCE_STRAIGHT);
                  Province_Border.this.drawStraightBorder(oSB, nTranslateProvincePosX);
               }
            } : new Province_Border.DrawProvince_Border() {
               @Override
               public void draw(SpriteBatch oSB, int nTranslateProvincePosX) {
               }
            };
         } else if (this.getIsCivilizationBorder()) {
            this.drawProvince_Border = new Province_Border.DrawProvince_Border() {
               @Override
               public void draw(SpriteBatch oSB, int nTranslateProvincePosX) {
                  oSB.setColor(CFG.COLOR_PROVINCE_STRAIGHT);
                  Province_Border.this.drawStraightBorder(oSB, nTranslateProvincePosX);
               }
            };
         } else {
            this.updateDrawProvinceBorder_Inner(nProvinceID);
         }
      } catch (GdxRuntimeException var4) {
      }
   }

   public final void updateDrawProvinceBorder_OwnerAnimation(boolean newState_IsCivBorder, int nProvinceID) {
      try {
         if (this.getIsWastelandBorder()) {
            this.updateDrawProvinceBorder(nProvinceID);
         } else if (newState_IsCivBorder == this.civilizationBorder) {
            this.updateDrawProvinceBorder(nProvinceID);
            CFG.PROVINCE_BORDER_ANIMATION_TIME.remove("" + this.getWithProvinceID() + "_" + this.iLineWidth);
         } else if (this.iProvinceBorderLineSize == 1) {
            this.civilizationBorder = newState_IsCivBorder;
            this.updateDrawProvinceBorder(nProvinceID);
         } else if (newState_IsCivBorder) {
            CFG.PROVINCE_BORDER_ANIMATION_TIME.put("" + this.getWithProvinceID() + "_" + this.iLineWidth, System.currentTimeMillis());
            this.drawProvince_Border = new Province_Border.DrawProvince_Border() {
               @Override
               public void draw(SpriteBatch oSB, int nTranslateProvincePosX) {
                  long tempTime = CFG.getPROVINCE_BORDER_ANIMATION_TIME("" + Province_Border.this.getWithProvinceID() + "_" + Province_Border.this.iLineWidth);
                  float tempPerc = (float)(System.currentTimeMillis() - tempTime) / 725.0F;
                  if (tempPerc >= 1.0F) {
                     CFG.PROVINCE_BORDER_ANIMATION_TIME.remove("" + Province_Border.this.getWithProvinceID() + "_" + Province_Border.this.iLineWidth);
                     tempPerc = 0.99F;
                     Province_Border.this.updateDrawProvinceBorder(-1);
                  }

                  Province_Border.this.drawDashedBorder_PercentageWidth_Full_Straight(
                     oSB,
                     tempPerc,
                     nTranslateProvincePosX,
                     CFG.COLOR_PROVINCE_STRAIGHT,
                     CFG.COLOR_PROVINCE_DASHED,
                     Province_Border.this.getDashedImage(),
                     Images.pix255_255_255
                  );
               }
            };
         } else {
            CFG.PROVINCE_BORDER_ANIMATION_TIME.put("" + this.getWithProvinceID() + "_" + this.iLineWidth, System.currentTimeMillis());
            this.drawProvince_Border = new Province_Border.DrawProvince_Border() {
               @Override
               public void draw(SpriteBatch oSB, int nTranslateProvincePosX) {
                  long tempTime = CFG.getPROVINCE_BORDER_ANIMATION_TIME("" + Province_Border.this.getWithProvinceID() + "_" + Province_Border.this.iLineWidth);
                  float tempPerc = (float)(System.currentTimeMillis() - tempTime) / 725.0F;
                  if (tempPerc >= 1.0F) {
                     CFG.PROVINCE_BORDER_ANIMATION_TIME.remove("" + Province_Border.this.getWithProvinceID() + "_" + Province_Border.this.iLineWidth);
                     tempPerc = 0.99F;
                     Province_Border.this.updateDrawProvinceBorder(-1);
                  }

                  Province_Border.this.drawDashedBorder_PercentageWidth_Full_Straight(
                     oSB,
                     tempPerc,
                     nTranslateProvincePosX,
                     CFG.COLOR_PROVINCE_DASHED,
                     CFG.COLOR_PROVINCE_STRAIGHT,
                     Images.pix255_255_255,
                     Province_Border.this.getDashedImage()
                  );
               }
            };
         }
      } catch (GdxRuntimeException var4) {
      }
   }

   public final void updateDrawProvinceBorder_Inner(int nProvinceID) {
      try {
         this.drawProvince_Border = CFG.settingsManager.ENABLE_INNER_BORDERS ? new Province_Border.DrawProvince_Border() {
            @Override
            public void draw(SpriteBatch oSB, int nTranslateProvincePosX) {
               oSB.setColor(CFG.COLOR_PROVINCE_DASHED);
               Province_Border.this.drawInnerBorder(oSB, nTranslateProvincePosX);
            }
         } : new Province_Border.DrawProvince_Border() {
            @Override
            public void draw(SpriteBatch oSB, int nTranslateProvincePosX) {
            }
         };
      } catch (GdxRuntimeException var3) {
      }
   }

   public final void updateDrawProvinceBorderSeaBySea() {
      try {
         this.drawProvince_Border = new Province_Border.DrawProvince_Border() {
            @Override
            public void draw(SpriteBatch oSB, int nTranslateProvincePosX) {
               oSB.setColor(CFG.COLOR_PROVINCE_SEABYSEA);
               Province_Border.this.drawStraightBorder(oSB, nTranslateProvincePosX);
            }
         };
      } catch (GdxRuntimeException var2) {
      }
   }

   public final void updateDrawProvinceBorder_SelectedProvinces() {
      try {
         this.drawProvince_Border = new Province_Border.DrawProvince_Border() {
            @Override
            public void draw(SpriteBatch oSB, int nTranslateProvincePosX) {
               oSB.setColor(new Color(0.9411765F, 0.7529412F, 0.15294118F, 1.0F));
               Province_Border.this.drawStraightBorder(oSB, nTranslateProvincePosX);
            }
         };
      } catch (GdxRuntimeException var2) {
      }
   }

   public final void updateDrawProvinceBorder_CivilizationRegion() {
      try {
         this.drawProvince_Border = new Province_Border.DrawProvince_Border() {
            @Override
            public void draw(SpriteBatch oSB, int nTranslateProvincePosX) {
               oSB.setColor(CFG.COLOR_PROVINCE_BORDER_CIV_REGION);
               Province_Border.this.drawStraightBorder(oSB, nTranslateProvincePosX);
            }
         };
      } catch (GdxRuntimeException var2) {
      }
   }

   public final void updateDrawProvinceBorder_CivilizationRegion2() {
      try {
         this.drawProvince_Border = new Province_Border.DrawProvince_Border() {
            @Override
            public void draw(SpriteBatch oSB, int nTranslateProvincePosX) {
               oSB.setColor(
                  new Color(
                     CFG.COLOR_PROVINCE_ACTIVE_PROVINCE_BORDER.r,
                     CFG.COLOR_PROVINCE_ACTIVE_PROVINCE_BORDER.g,
                     CFG.COLOR_PROVINCE_ACTIVE_PROVINCE_BORDER.b,
                     CFG.game.getProvinceAnimation_Active_Data().getBorderAlpha() / 255.0F
                  )
               );
               Province_Border.this.drawStraightBorder(oSB, nTranslateProvincePosX);
            }
         };
      } catch (GdxRuntimeException var2) {
      }
   }

   public final void updateDrawProvinceBorder_Active() {
      try {
         this.drawProvince_Border = new Province_Border.DrawProvince_Border() {
            @Override
            public void draw(SpriteBatch oSB, int nTranslateProvincePosX) {
               oSB.setColor(
                  new Color(
                     CFG.COLOR_PROVINCE_ACTIVE_PROVINCE_BORDER.r,
                     CFG.COLOR_PROVINCE_ACTIVE_PROVINCE_BORDER.g,
                     CFG.COLOR_PROVINCE_ACTIVE_PROVINCE_BORDER.b,
                     CFG.game.getProvinceAnimation_Active_Data().getBorderAlpha() / 255.0F
                  )
               );
               Province_Border.this.drawStraightBorder(oSB, nTranslateProvincePosX);
            }
         };
      } catch (GdxRuntimeException var2) {
      }
   }

   public final void updateDrawProvinceBorder_ActiveDashed() {
      try {
         this.drawProvince_Border = new Province_Border.DrawProvince_Border() {
            @Override
            public void draw(SpriteBatch oSB, int nTranslateProvincePosX) {
               oSB.setColor(
                  new Color(
                     CFG.COLOR_PROVINCE_ACTIVE_PROVINCE_BORDER.r,
                     CFG.COLOR_PROVINCE_ACTIVE_PROVINCE_BORDER.g,
                     CFG.COLOR_PROVINCE_ACTIVE_PROVINCE_BORDER.b,
                     CFG.game.getProvinceAnimation_Active_Data().getBorderAlpha() / 255.0F
                  )
               );
               Province_Border.this.drawDashedBorder(
                  oSB, Images.line_33, CFG.game.getProvinceAnimation_Highlighted_Data().getLineOffset(), nTranslateProvincePosX
               );
            }
         };
      } catch (GdxRuntimeException var2) {
      }
   }

   public final void updateDrawProvinceBorder_Active_Percentage() {
      try {
         this.drawProvince_Border = new Province_Border.DrawProvince_Border() {
            @Override
            public void draw(SpriteBatch oSB, int nTranslateProvincePosX) {
               if (Province_Border.this.getIsCivilizationBorder()) {
                  Province_Border.this.drawStraightBorder_PercentageWidth_Full_Straight(
                     oSB,
                     (100.0F - CFG.game.fDashedLine_Percentage_HighlitedProvinceBorder) / 100.0F,
                     nTranslateProvincePosX,
                     new Color(
                        CFG.COLOR_PROVINCE_ACTIVE_PROVINCE_BORDER.r,
                        CFG.COLOR_PROVINCE_ACTIVE_PROVINCE_BORDER.g,
                        CFG.COLOR_PROVINCE_ACTIVE_PROVINCE_BORDER.b,
                        CFG.game.getProvinceAnimation_Active_Data().getBorderAlpha() / 255.0F
                     ),
                     CFG.COLOR_PROVINCE_STRAIGHT
                  );
               } else {
                  Province_Border.this.drawStraightBorder_PercentageWidth_Full_Dashed(
                     oSB,
                     (100.0F - CFG.game.fDashedLine_Percentage_HighlitedProvinceBorder) / 100.0F,
                     nTranslateProvincePosX,
                     new Color(
                        CFG.COLOR_PROVINCE_ACTIVE_PROVINCE_BORDER.r,
                        CFG.COLOR_PROVINCE_ACTIVE_PROVINCE_BORDER.g,
                        CFG.COLOR_PROVINCE_ACTIVE_PROVINCE_BORDER.b,
                        CFG.game.getProvinceAnimation_Active_Data().getBorderAlpha() / 255.0F
                     ),
                     CFG.COLOR_PROVINCE_DASHED
                  );
               }
            }
         };
      } catch (GdxRuntimeException var2) {
      }
   }

   public final void updateDrawProvinceBorder_ActiveSea() {
      try {
         this.drawProvince_Border = new Province_Border.DrawProvince_Border() {
            @Override
            public void draw(SpriteBatch oSB, int nTranslateProvincePosX) {
               oSB.setColor(new Color(0.9411765F, 0.7529412F, 0.15294118F, CFG.game.getProvinceAnimation_Active_Data().getBorderAlpha() / 255.0F));
               Province_Border.this.drawDashedBorder(
                  oSB, Images.line_44, CFG.game.getProvinceAnimation_Highlighted_Data().getLineOffset(), nTranslateProvincePosX
               );
            }
         };
      } catch (GdxRuntimeException var2) {
      }
   }

   public final void updateDrawProvinceBorder_ActiveSea_Dashed() {
      try {
         this.drawProvince_Border = new Province_Border.DrawProvince_Border() {
            @Override
            public void draw(SpriteBatch oSB, int nTranslateProvincePosX) {
               oSB.setColor(new Color(0.9411765F, 0.7529412F, 0.15294118F, CFG.game.getProvinceAnimation_Active_Data().getBorderAlpha() / 255.0F));
               Province_Border.this.drawDashedBorder(
                  oSB, Images.line_44, CFG.game.getProvinceAnimation_Highlighted_Data().getLineOffset(), nTranslateProvincePosX
               );
            }
         };
      } catch (GdxRuntimeException var2) {
      }
   }

   public final void updateDrawProvinceBorder_ActiveSeaBySea_Percentage() {
      try {
         this.drawProvince_Border = new Province_Border.DrawProvince_Border() {
            @Override
            public void draw(SpriteBatch oSB, int nTranslateProvincePosX) {
               Province_Border.this.drawDashedBorder_PercentageWidth_Full_Straight(
                  oSB,
                  Images.line_44,
                  CFG.game.getProvinceAnimation_Highlighted_Data().getLineOffset(),
                  (100.0F - CFG.game.fDashedLine_Percentage_HighlitedProvinceBorder) / 100.0F,
                  nTranslateProvincePosX,
                  new Color(0.9411765F, 0.7529412F, 0.15294118F, CFG.game.getProvinceAnimation_Active_Data().getBorderAlpha() / 255.0F),
                  CFG.COLOR_PROVINCE_SEABYSEA
               );
            }
         };
      } catch (GdxRuntimeException var2) {
      }
   }

   public final void updateDrawProvinceBorder_ActiveLandBySea_Percentage() {
      try {
         this.drawProvince_Border = new Province_Border.DrawProvince_Border() {
            @Override
            public void draw(SpriteBatch oSB, int nTranslateProvincePosX) {
               oSB.setColor(new Color(0.9411765F, 0.7529412F, 0.15294118F, CFG.game.getProvinceAnimation_Active_Data().getBorderAlpha() / 255.0F));
               Province_Border.this.drawDashedBorder_PercentageWidth(
                  oSB,
                  Images.line_44,
                  CFG.game.getProvinceAnimation_Highlighted_Data().getLineOffset(),
                  (100.0F - CFG.game.fDashedLine_Percentage_HighlitedProvinceBorder) / 100.0F,
                  nTranslateProvincePosX
               );
            }
         };
      } catch (GdxRuntimeException var2) {
      }
   }

   public final void updateDrawProvinceBorder_MoveUnits() {
      try {
         this.drawProvince_Border = new Province_Border.DrawProvince_Border() {
            @Override
            public void draw(SpriteBatch oSB, int nTranslateProvincePosX) {
               oSB.setColor(
                  new Color(
                     CFG.getColorStep(224, 250, CFG.game.getProvinceAnimation_Active_Data().getColorStepID(), 60),
                     CFG.getColorStep(206, 234, CFG.game.getProvinceAnimation_Active_Data().getColorStepID(), 60),
                     CFG.getColorStep(91, 4, CFG.game.getProvinceAnimation_Active_Data().getColorStepID(), 60),
                     CFG.game.getProvinceAnimation_Active_Data().getBorderAlpha() / 255.0F * 0.6F
                        + CFG.game.getProvinceAnimation_Active_Data().getBorderAlpha()
                           / 255.0F
                           * 0.4F
                           * CFG.game.fDashedLine_Percentage_HighlitedProvinceBorder
                           / 100.0F
                  )
               );
               Province_Border.this.drawStraightBorder(oSB, nTranslateProvincePosX);
            }
         };
      } catch (GdxRuntimeException var2) {
      }
   }

   public final void updateDrawProvinceBorder_MoveUnits_Percentage() {
      try {
         this.drawProvince_Border = new Province_Border.DrawProvince_Border() {
            @Override
            public void draw(SpriteBatch oSB, int nTranslateProvincePosX) {
               if (Province_Border.this.getIsCivilizationBorder()) {
                  Province_Border.this.drawStraightBorder_PercentageWidth_Full_Straight(
                     oSB,
                     CFG.game.fDashedLine_Percentage_HighlitedProvinceBorder / 100.0F,
                     nTranslateProvincePosX,
                     new Color(
                        CFG.getColorStep(224, 250, CFG.game.getProvinceAnimation_Active_Data().getColorStepID(), 60),
                        CFG.getColorStep(206, 234, CFG.game.getProvinceAnimation_Active_Data().getColorStepID(), 60),
                        CFG.getColorStep(91, 4, CFG.game.getProvinceAnimation_Active_Data().getColorStepID(), 60),
                        CFG.game.getProvinceAnimation_Active_Data().getBorderAlpha() / 255.0F * 0.6F
                           + CFG.game.getProvinceAnimation_Active_Data().getBorderAlpha()
                              / 255.0F
                              * 0.4F
                              * CFG.game.fDashedLine_Percentage_HighlitedProvinceBorder
                              / 100.0F
                     ),
                     CFG.COLOR_PROVINCE_DASHED
                  );
               } else {
                  Province_Border.this.drawStraightBorder_PercentageWidth_Full_Dashed(
                     oSB,
                     CFG.game.fDashedLine_Percentage_HighlitedProvinceBorder / 100.0F,
                     nTranslateProvincePosX,
                     new Color(
                        CFG.getColorStep(224, 250, CFG.game.getProvinceAnimation_Active_Data().getColorStepID(), 60),
                        CFG.getColorStep(206, 234, CFG.game.getProvinceAnimation_Active_Data().getColorStepID(), 60),
                        CFG.getColorStep(91, 4, CFG.game.getProvinceAnimation_Active_Data().getColorStepID(), 60),
                        CFG.game.getProvinceAnimation_Active_Data().getBorderAlpha() / 255.0F * 0.6F
                           + CFG.game.getProvinceAnimation_Active_Data().getBorderAlpha()
                              / 255.0F
                              * 0.4F
                              * CFG.game.fDashedLine_Percentage_HighlitedProvinceBorder
                              / 100.0F
                     ),
                     CFG.COLOR_PROVINCE_DASHED
                  );
               }
            }
         };
      } catch (GdxRuntimeException var2) {
      }
   }

   public final void updateDrawProvinceBorder_MoveUnits_Sea() {
      try {
         this.drawProvince_Border = new Province_Border.DrawProvince_Border() {
            @Override
            public void draw(SpriteBatch oSB, int nTranslateProvincePosX) {
               oSB.setColor(
                  new Color(
                     CFG.getColorStep(224, 250, CFG.game.getProvinceAnimation_Active_Data().getColorStepID(), 60),
                     CFG.getColorStep(206, 234, CFG.game.getProvinceAnimation_Active_Data().getColorStepID(), 60),
                     CFG.getColorStep(91, 4, CFG.game.getProvinceAnimation_Active_Data().getColorStepID(), 60),
                     0.49019608F * CFG.game.fDashedLine_Percentage_HighlitedProvinceBorder / 100.0F
                  )
               );
               Province_Border.this.drawDashedBorder(
                  oSB, Images.line_44, CFG.game.getProvinceAnimation_Highlighted_Data().getLineOffset(), nTranslateProvincePosX
               );
            }
         };
      } catch (GdxRuntimeException var2) {
      }
   }

   public final void updateDrawProvinceBorder_MoveUnits_Percentage_LandBySea() {
      try {
         this.drawProvince_Border = new Province_Border.DrawProvince_Border() {
            @Override
            public void draw(SpriteBatch oSB, int nTranslateProvincePosX) {
               oSB.setColor(
                  new Color(
                     CFG.getColorStep(224, 250, CFG.game.getProvinceAnimation_Active_Data().getColorStepID(), 60),
                     CFG.getColorStep(206, 234, CFG.game.getProvinceAnimation_Active_Data().getColorStepID(), 60),
                     CFG.getColorStep(91, 4, CFG.game.getProvinceAnimation_Active_Data().getColorStepID(), 60),
                     0.49019608F * CFG.game.fDashedLine_Percentage_HighlitedProvinceBorder / 100.0F
                  )
               );
               Province_Border.this.drawDashedBorder_PercentageWidth(
                  oSB,
                  Images.line_44,
                  CFG.game.getProvinceAnimation_Highlighted_Data().getLineOffset(),
                  CFG.game.fDashedLine_Percentage_HighlitedProvinceBorder / 100.0F,
                  nTranslateProvincePosX
               );
            }
         };
      } catch (GdxRuntimeException var2) {
      }
   }

   public final void updateDrawProvinceBorder_MoveUnits_Percentage_Sea() {
      try {
         this.drawProvince_Border = new Province_Border.DrawProvince_Border() {
            @Override
            public void draw(SpriteBatch oSB, int nTranslateProvincePosX) {
               Province_Border.this.drawDashedBorder_PercentageWidth_Full_SeaBySea(
                  oSB,
                  Images.line_44,
                  CFG.game.getProvinceAnimation_Highlighted_Data().getLineOffset(),
                  CFG.game.fDashedLine_Percentage_HighlitedProvinceBorder / 100.0F,
                  nTranslateProvincePosX,
                  new Color(
                     CFG.getColorStep(224, 250, CFG.game.getProvinceAnimation_Active_Data().getColorStepID(), 60),
                     CFG.getColorStep(206, 234, CFG.game.getProvinceAnimation_Active_Data().getColorStepID(), 60),
                     CFG.getColorStep(91, 4, CFG.game.getProvinceAnimation_Active_Data().getColorStepID(), 60),
                     0.49019608F * CFG.game.fDashedLine_Percentage_HighlitedProvinceBorder / 100.0F
                  )
               );
            }
         };
      } catch (GdxRuntimeException var2) {
      }
   }

   public final void drawStraightBorder(SpriteBatch oSB, int nTranslateProvincePosX) {
      for (int i = 0; i < this.iProvinceBorderLineSize; i++) {
         ImageManager.getImage(Images.pix255_255_255)
            .draw(
               oSB,
               nTranslateProvincePosX + this.provinceBorderLine.get(i).getPosX(),
               CFG.map.getMapCoordinates().getPosY() + this.provinceBorderLine.get(i).getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight(),
               (int)(this.provinceBorderLine.get(i).getWidth() * CFG.settingsManager.BORDER_WIDTH),
               (int)(ImageManager.getImage(Images.pix255_255_255).getHeight() * CFG.settingsManager.BORDER_HEIGHT * CFG.PROVINCE_BORDER_THICKNESS),
               this.provinceBorderLine.get(i).getAngle()
            );
      }
   }

   public final void drawStraightBorder_PercentageWidth(SpriteBatch oSB, float fPercent, int nTranslateProvincePosX) {
      int lineWidth = (int)(this.iLineWidth * fPercent);
      int i = 0;

      for (int currentWidth = 0; i < this.iProvinceBorderLineSize && currentWidth <= lineWidth; i++) {
         ImageManager.getImage(Images.pix255_255_255)
            .draw(
               oSB,
               nTranslateProvincePosX + this.provinceBorderLine.get(i).getPosX(),
               CFG.map.getMapCoordinates().getPosY() + this.provinceBorderLine.get(i).getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight(),
               currentWidth + this.provinceBorderLine.get(i).getWidth() <= lineWidth ? this.provinceBorderLine.get(i).getWidth() : lineWidth - currentWidth,
               ImageManager.getImage(Images.pix255_255_255).getHeight() * CFG.PROVINCE_BORDER_THICKNESS,
               this.provinceBorderLine.get(i).getAngle()
            );
         currentWidth += this.provinceBorderLine.get(i).getWidth();
      }
   }

   public final void drawDashedBorder_PercentageWidth_Full_Straight(
      SpriteBatch oSB, int iImageID, int offsetX, float fPercent, int nTranslateProvincePosX, Color activeColor, Color oldColor
   ) {
      int lineWidth = (int)(this.iLineWidth * fPercent);
      int iBeginDraw_ID = 0;
      int currentWidth = 0;

      for (int i = 0; i < this.iProvinceBorderLineSize; i++) {
         if ((currentWidth += this.provinceBorderLine.get(i).getWidth()) >= lineWidth) {
            if (i > 0) {
               iBeginDraw_ID = i - 1;
            }
            break;
         }
      }

      oSB.setColor(oldColor);

      while (iBeginDraw_ID < this.iProvinceBorderLineSize) {
         ImageManager.getImage(Images.pix255_255_255)
            .draw(
               oSB,
               nTranslateProvincePosX + this.provinceBorderLine.get(iBeginDraw_ID).getPosX(),
               CFG.map.getMapCoordinates().getPosY()
                  + this.provinceBorderLine.get(iBeginDraw_ID).getPosY()
                  - ImageManager.getImage(Images.pix255_255_255).getHeight(),
               this.provinceBorderLine.get(iBeginDraw_ID).getWidth(),
               ImageManager.getImage(Images.pix255_255_255).getHeight() * CFG.PROVINCE_BORDER_THICKNESS,
               this.provinceBorderLine.get(iBeginDraw_ID).getAngle()
            );
         iBeginDraw_ID++;
      }

      oSB.setColor(activeColor);
      currentWidth = 0;

      for (int var12 = 0; var12 < this.iProvinceBorderLineSize && currentWidth <= lineWidth; var12++) {
         ImageManager.getImage(iImageID)
            .draw(
               oSB,
               nTranslateProvincePosX + this.provinceBorderLine.get(var12).getPosX(),
               CFG.map.getMapCoordinates().getPosY() + this.provinceBorderLine.get(var12).getPosY() - ImageManager.getImage(iImageID).getHeight(),
               currentWidth + this.provinceBorderLine.get(var12).getWidth() <= lineWidth
                  ? this.provinceBorderLine.get(var12).getWidth()
                  : lineWidth - currentWidth,
               ImageManager.getImage(iImageID).getHeight() * CFG.PROVINCE_BORDER_DASHED_THICKNESS,
               this.provinceBorderLine.get(var12).getAngle(),
               offsetX
            );
         offsetX += this.provinceBorderLine.get(var12).getWidth();
         currentWidth += this.provinceBorderLine.get(var12).getWidth();
      }
   }

   public final void drawStraightBorder_PercentageWidth_Full_Straight(
      SpriteBatch oSB, float fPercent, int nTranslateProvincePosX, Color activeColor, Color oldColor
   ) {
      int lineWidth = (int)(this.iLineWidth * fPercent);
      int iBeginDraw_ID = 0;
      int currentWidth = 0;

      for (int i = 0; i < this.iProvinceBorderLineSize; i++) {
         if ((currentWidth += this.provinceBorderLine.get(i).getWidth()) >= lineWidth) {
            if (i > 0) {
               iBeginDraw_ID = i - 1;
            }
            break;
         }
      }

      oSB.setColor(oldColor);

      while (iBeginDraw_ID < this.iProvinceBorderLineSize) {
         ImageManager.getImage(Images.pix255_255_255)
            .draw(
               oSB,
               nTranslateProvincePosX + this.provinceBorderLine.get(iBeginDraw_ID).getPosX(),
               CFG.map.getMapCoordinates().getPosY()
                  + this.provinceBorderLine.get(iBeginDraw_ID).getPosY()
                  - ImageManager.getImage(Images.pix255_255_255).getHeight(),
               this.provinceBorderLine.get(iBeginDraw_ID).getWidth(),
               ImageManager.getImage(Images.pix255_255_255).getHeight() * CFG.PROVINCE_BORDER_THICKNESS,
               this.provinceBorderLine.get(iBeginDraw_ID).getAngle()
            );
         iBeginDraw_ID++;
      }

      oSB.setColor(activeColor);
      currentWidth = 0;

      for (int var10 = 0; var10 < this.iProvinceBorderLineSize && currentWidth <= lineWidth; var10++) {
         ImageManager.getImage(Images.pix255_255_255)
            .draw(
               oSB,
               nTranslateProvincePosX + this.provinceBorderLine.get(var10).getPosX(),
               CFG.map.getMapCoordinates().getPosY() + this.provinceBorderLine.get(var10).getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight(),
               currentWidth + this.provinceBorderLine.get(var10).getWidth() <= lineWidth
                  ? this.provinceBorderLine.get(var10).getWidth()
                  : lineWidth - currentWidth,
               ImageManager.getImage(Images.pix255_255_255).getHeight() * CFG.PROVINCE_BORDER_THICKNESS,
               this.provinceBorderLine.get(var10).getAngle()
            );
         currentWidth += this.provinceBorderLine.get(var10).getWidth();
      }
   }

   public final void drawDashedBorder_PercentageWidth_Full_Straight(
      SpriteBatch oSB, float fPercent, int nTranslateProvincePosX, Color activeColor, Color oldColor, int nImageIDActive, int nImageIDOld
   ) {
      int lineWidth = (int)(this.iLineWidth * fPercent);
      int iBeginDraw_ID = 0;
      int currentWidth = 0;
      int offsetX = 0;

      for (int i = 0; i < this.iProvinceBorderLineSize; i++) {
         if ((currentWidth += this.provinceBorderLine.get(i).getWidth()) >= lineWidth) {
            if (i > 0) {
               iBeginDraw_ID = i - 1;
            }
            break;
         }
      }

      oSB.setColor(oldColor);

      while (iBeginDraw_ID < this.iProvinceBorderLineSize) {
         ImageManager.getImage(nImageIDActive)
            .draw(
               oSB,
               nTranslateProvincePosX + this.provinceBorderLine.get(iBeginDraw_ID).getPosX(),
               CFG.map.getMapCoordinates().getPosY() + this.provinceBorderLine.get(iBeginDraw_ID).getPosY() - ImageManager.getImage(nImageIDActive).getHeight(),
               this.provinceBorderLine.get(iBeginDraw_ID).getWidth(),
               ImageManager.getImage(nImageIDActive).getHeight() * CFG.PROVINCE_BORDER_THICKNESS,
               this.provinceBorderLine.get(iBeginDraw_ID).getAngle()
            );
         iBeginDraw_ID++;
      }

      oSB.setColor(activeColor);
      currentWidth = 0;

      for (int var13 = 0; var13 < this.iProvinceBorderLineSize && currentWidth <= lineWidth && var13 < iBeginDraw_ID; var13++) {
         ImageManager.getImage(nImageIDOld)
            .draw(
               oSB,
               nTranslateProvincePosX + this.provinceBorderLine.get(var13).getPosX(),
               CFG.map.getMapCoordinates().getPosY() + this.provinceBorderLine.get(var13).getPosY() - ImageManager.getImage(nImageIDOld).getHeight(),
               currentWidth + this.provinceBorderLine.get(var13).getWidth() <= lineWidth
                  ? this.provinceBorderLine.get(var13).getWidth()
                  : lineWidth - currentWidth,
               ImageManager.getImage(nImageIDOld).getHeight() * CFG.PROVINCE_BORDER_THICKNESS,
               this.provinceBorderLine.get(var13).getAngle(),
               offsetX
            );
         currentWidth += this.provinceBorderLine.get(var13).getWidth();
         offsetX += this.provinceBorderLine.get(var13).getWidth();
      }
   }

   public final void drawStraightBorder_PercentageWidth_Full_Dashed(
      SpriteBatch oSB, float fPercent, int nTranslateProvincePosX, Color activeColor, Color oldColor
   ) {
      int lineWidth = (int)(this.iLineWidth * fPercent);
      int iBeginDraw_ID = 0;
      int currentWidth = 0;

      for (int i = 0; i < this.iProvinceBorderLineSize; i++) {
         if ((currentWidth += this.provinceBorderLine.get(i).getWidth()) >= lineWidth) {
            if (i > 0) {
               iBeginDraw_ID = i - 1;
               currentWidth -= this.provinceBorderLine.get(i).getWidth();
               currentWidth -= this.provinceBorderLine.get(i - 1).getWidth();
            } else {
               currentWidth = 0;
            }
            break;
         }
      }

      oSB.setColor(oldColor);

      while (iBeginDraw_ID < this.iProvinceBorderLineSize) {
         ImageManager.getImage(this.getDashedImage())
            .draw(
               oSB,
               nTranslateProvincePosX + this.provinceBorderLine.get(iBeginDraw_ID).getPosX(),
               CFG.map.getMapCoordinates().getPosY()
                  + this.provinceBorderLine.get(iBeginDraw_ID).getPosY()
                  - ImageManager.getImage(this.getDashedImage()).getHeight(),
               this.provinceBorderLine.get(iBeginDraw_ID).getWidth(),
               ImageManager.getImage(this.getDashedImage()).getHeight() * CFG.PROVINCE_BORDER_DASHED_THICKNESS,
               this.provinceBorderLine.get(iBeginDraw_ID).getAngle(),
               currentWidth
            );
         currentWidth += this.provinceBorderLine.get(iBeginDraw_ID).getWidth();
         iBeginDraw_ID++;
      }

      oSB.setColor(activeColor);
      currentWidth = 0;

      for (int var10 = 0; var10 < this.iProvinceBorderLineSize && currentWidth <= lineWidth; var10++) {
         ImageManager.getImage(Images.pix255_255_255)
            .draw(
               oSB,
               nTranslateProvincePosX + this.provinceBorderLine.get(var10).getPosX(),
               CFG.map.getMapCoordinates().getPosY() + this.provinceBorderLine.get(var10).getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight(),
               currentWidth + this.provinceBorderLine.get(var10).getWidth() <= lineWidth
                  ? this.provinceBorderLine.get(var10).getWidth()
                  : lineWidth - currentWidth,
               ImageManager.getImage(Images.pix255_255_255).getHeight() * CFG.PROVINCE_BORDER_THICKNESS,
               this.provinceBorderLine.get(var10).getAngle()
            );
         currentWidth += this.provinceBorderLine.get(var10).getWidth();
      }
   }

   public final int getDashedImage() {
      return Images.line_32;
   }

   public final void drawInnerBorder(SpriteBatch oSB, int nTranslateProvincePosX) {
      this.drawDashedBorder(oSB, this.getDashedImage(), 0, nTranslateProvincePosX);
   }

   public final void drawDashedBorder(SpriteBatch oSB, int iImageID, int offsetX, int nTranslateProvincePosX) {
      for (int i = 0; i < this.iProvinceBorderLineSize; i++) {
         ImageManager.getImage(iImageID)
            .draw(
               oSB,
               nTranslateProvincePosX + this.provinceBorderLine.get(i).getPosX(),
               CFG.map.getMapCoordinates().getPosY() + this.provinceBorderLine.get(i).getPosY() - ImageManager.getImage(iImageID).getHeight(),
               this.provinceBorderLine.get(i).getWidth(),
               ImageManager.getImage(iImageID).getHeight() * CFG.PROVINCE_BORDER_DASHED_THICKNESS,
               this.provinceBorderLine.get(i).getAngle(),
               offsetX
            );
         offsetX += this.provinceBorderLine.get(i).getWidth();
      }
   }

   public final void drawDashedBorder_PercentageWidth(SpriteBatch oSB, int iImageID, int offsetX, float fPercent, int nTranslateProvincePosX) {
      int lineWidth = (int)(this.iLineWidth * fPercent);
      int i = 0;

      for (int currentWidth = 0; i < this.iProvinceBorderLineSize && currentWidth <= lineWidth; i++) {
         ImageManager.getImage(iImageID)
            .draw(
               oSB,
               nTranslateProvincePosX + this.provinceBorderLine.get(i).getPosX(),
               CFG.map.getMapCoordinates().getPosY() + this.provinceBorderLine.get(i).getPosY() - ImageManager.getImage(iImageID).getHeight(),
               currentWidth + this.provinceBorderLine.get(i).getWidth() <= lineWidth ? this.provinceBorderLine.get(i).getWidth() : lineWidth - currentWidth,
               ImageManager.getImage(iImageID).getHeight() * CFG.PROVINCE_BORDER_DASHED_THICKNESS,
               this.provinceBorderLine.get(i).getAngle(),
               offsetX
            );
         offsetX += this.provinceBorderLine.get(i).getWidth();
         currentWidth += this.provinceBorderLine.get(i).getWidth();
      }
   }

   public final void drawDashedBorder_PercentageWidth_Full_SeaBySea(
      SpriteBatch oSB, int iImageID, int offsetX, float fPercent, int nTranslateProvincePosX, Color activeColor
   ) {
      int lineWidth = (int)(this.iLineWidth * fPercent);
      int iBeginDraw_ID = 0;
      int currentWidth = 0;

      for (int i = 0; i < this.iProvinceBorderLineSize; i++) {
         if ((currentWidth += this.provinceBorderLine.get(i).getWidth()) >= lineWidth) {
            if (i > 0) {
               iBeginDraw_ID = i - 1;
            }
            break;
         }
      }

      oSB.setColor(CFG.COLOR_PROVINCE_SEABYSEA);

      while (iBeginDraw_ID < this.iProvinceBorderLineSize) {
         ImageManager.getImage(Images.pix255_255_255)
            .draw(
               oSB,
               nTranslateProvincePosX + this.provinceBorderLine.get(iBeginDraw_ID).getPosX(),
               CFG.map.getMapCoordinates().getPosY()
                  + this.provinceBorderLine.get(iBeginDraw_ID).getPosY()
                  - ImageManager.getImage(Images.pix255_255_255).getHeight(),
               this.provinceBorderLine.get(iBeginDraw_ID).getWidth(),
               ImageManager.getImage(Images.pix255_255_255).getHeight() * CFG.PROVINCE_BORDER_THICKNESS,
               this.provinceBorderLine.get(iBeginDraw_ID).getAngle()
            );
         iBeginDraw_ID++;
      }

      oSB.setColor(activeColor);
      currentWidth = 0;

      for (int var11 = 0; var11 < this.iProvinceBorderLineSize && currentWidth <= lineWidth; var11++) {
         ImageManager.getImage(iImageID)
            .draw(
               oSB,
               nTranslateProvincePosX + this.provinceBorderLine.get(var11).getPosX(),
               CFG.map.getMapCoordinates().getPosY() + this.provinceBorderLine.get(var11).getPosY() - ImageManager.getImage(iImageID).getHeight(),
               currentWidth + this.provinceBorderLine.get(var11).getWidth() <= lineWidth
                  ? this.provinceBorderLine.get(var11).getWidth()
                  : lineWidth - currentWidth,
               ImageManager.getImage(iImageID).getHeight() * CFG.PROVINCE_BORDER_DASHED_THICKNESS,
               this.provinceBorderLine.get(var11).getAngle(),
               offsetX
            );
         offsetX += this.provinceBorderLine.get(var11).getWidth();
         currentWidth += this.provinceBorderLine.get(var11).getWidth();
      }
   }

   public final int getWithProvinceID() {
      return this.withProvinceID;
   }

   public final boolean getIsWastelandBorder() {
      return this.wastelandBorder;
   }

   public final boolean getIsCivilizationBorder() {
      return this.civilizationBorder;
   }

   public final void setIsCivilizationBorder(boolean civilizationBorder, int iProvinceID) {
      this.civilizationBorder = civilizationBorder;
      this.updateDrawProvinceBorder(iProvinceID);
   }

   public final void setIsCivilizationBorder_Just(boolean civilizationBorder, int iProvinceID) {
      this.civilizationBorder = civilizationBorder;
   }

   public final void setIsCivilizationBorder_OwnerAnimation(boolean civilizationBorder, int iProvinceID) {
      this.updateDrawProvinceBorder_OwnerAnimation(civilizationBorder, iProvinceID);
      this.civilizationBorder = civilizationBorder;
   }

   public final void setIsWastelandBorder(boolean wastelandBorder, int iProvinceID) {
      this.wastelandBorder = wastelandBorder;
      this.updateDrawProvinceBorder(iProvinceID);
   }

   public interface DrawProvince_Border {
      void draw(SpriteBatch var1, int var2);
   }
}
