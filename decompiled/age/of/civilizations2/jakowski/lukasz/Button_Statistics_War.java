package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

public class Button_Statistics_War extends Button_Statistics {
   public static long lTime;
   public static final float FONT_SCALE = 0.7F;
   public static final float FONT_SCALE2 = 0.6F;
   public int iAggressor;
   public int iDefender;
   public String sDefenderName;
   public int iWarID = 0;
   public float fAttackersPerc;
   public String sCasualtiesTotal;
   public Color oColorCasualtiesTotal;
   public String sWarDate;
   public int iWarDateWidth = 0;

   public Button_Statistics_War(int nAggressor, int nDefender, int nWarID, int iPosX, int iPosY, int iWidth) {
      super(
         CFG.FOG_OF_WAR == 2
            ? (
               CFG.game.getWar(nWarID).getAggressorsSize() == 1
                  ? (
                     CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetCivilization(nAggressor)
                        ? CFG.game.getCiv(nAggressor).getCivName()
                        : CFG.langManager.get("Undiscovered")
                  )
                  : (
                     CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetAlliance(CFG.game.getCiv(nAggressor).getAllianceID())
                        ? CFG.game.getAlliance(CFG.game.getCiv(nAggressor).getAllianceID()).getAllianceName()
                        : CFG.langManager.get("Undiscovered")
                  )
            )
            : (
               CFG.game.getWar(nWarID).getAggressorsSize() == 1
                  ? CFG.game.getCiv(nAggressor).getCivName()
                  : CFG.game.getAlliance(CFG.game.getCiv(nAggressor).getAllianceID()).getAllianceName()
            ),
         0,
         iPosX,
         iPosY,
         iWidth,
         CFG.TEXT_HEIGHT + CFG.PADDING * 3 + CFG.PADDING * 2 + CFG.PADDING
      );
      if (CFG.FOG_OF_WAR == 2) {
         this.iAggressor = CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetCivilization(nAggressor) ? nAggressor : -1;
         this.iDefender = CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetCivilization(nDefender) ? nDefender : -1;
         this.sDefenderName = CFG.game.getWar(nWarID).getDefendersSize() == 1
            ? (
               CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetCivilization(nDefender)
                  ? CFG.game.getCiv(nDefender).getCivName()
                  : CFG.langManager.get("Undiscovered")
            )
            : (
               CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetAlliance(CFG.game.getCiv(nDefender).getAllianceID())
                  ? CFG.game.getAlliance(CFG.game.getCiv(nDefender).getAllianceID()).getAllianceName()
                  : CFG.langManager.get("Undiscovered")
            );
      } else {
         this.iAggressor = nAggressor;
         this.iDefender = nDefender;
         this.sDefenderName = CFG.game.getWar(nWarID).getDefendersSize() == 1
            ? CFG.game.getCiv(nDefender).getCivName()
            : CFG.game.getAlliance(CFG.game.getCiv(nDefender).getAllianceID()).getAllianceName();
      }

      this.iWarID = nWarID;
      int tempCas = CFG.game.getWar(this.iWarID).getCasualties_Aggressors() + CFG.game.getWar(this.iWarID).getCasualties_Defenders();
      this.oColorCasualtiesTotal = tempCas == 0 ? CFG.COLOR_TEXT_MODIFIER_NEUTRAL2 : CFG.COLOR_TEXT_MODIFIER_NEGATIVE2;
      this.sCasualtiesTotal = CFG.getNumber_SHORT(tempCas);
      this.sWarDate = Game_Calendar.getDate_ByTurnID(CFG.game.getWar(this.iWarID).getWarTurnID());
      CFG.glyphLayout.setText(CFG.fontMain, this.sWarDate);
      this.iWarDateWidth = (int)(CFG.glyphLayout.width * 0.6F);
      this.fAttackersPerc = 0.5F - 0.5F * (CFG.game.getWar(this.iWarID).getWarScore() / 100.0F);
      lTime = System.currentTimeMillis();
   }

   @Override
   public void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
      oSB.setColor(new Color(CFG.COLOR_GRADIENT_TITLE_BLUE.r, CFG.COLOR_GRADIENT_TITLE_BLUE.g, CFG.COLOR_GRADIENT_TITLE_BLUE.b, 0.25F));
      ImageManager.getImage(Images.line_32_off1)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
            this.getWidth(),
            this.getHeight()
         );
      oSB.setColor(Color.WHITE);
      oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.55F));
      ImageManager.getImage(Images.gradient)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY,
            this.getWidth(),
            this.getHeight() * 3 / 5,
            false,
            false
         );
      oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.275F));
      ImageManager.getImage(Images.slider_gradient)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY,
            this.getWidth() / 4,
            this.getHeight(),
            false,
            false
         );
      ImageManager.getImage(Images.slider_gradient)
         .draw(
            oSB,
            this.getPosX() + this.getWidth() - this.getWidth() / 4 + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY,
            this.getWidth() / 4,
            this.getHeight(),
            true,
            false
         );
      super.drawButtonBG(oSB, iTranslateX, iTranslateY, isActive);
      oSB.setColor(new Color(CFG.COLOR_GRADIENT_TITLE_BLUE.r, CFG.COLOR_GRADIENT_TITLE_BLUE.g, CFG.COLOR_GRADIENT_TITLE_BLUE.b, 0.3F));
      ImageManager.getImage(Images.gradient)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY,
            this.getWidth(),
            CFG.PADDING,
            false,
            false
         );
      ImageManager.getImage(Images.gradient)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() + this.getHeight() - CFG.PADDING - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY,
            this.getWidth(),
            CFG.PADDING,
            false,
            true
         );
      oSB.setColor(new Color(CFG.COLOR_GRADIENT_TITLE_BLUE.r, CFG.COLOR_GRADIENT_TITLE_BLUE.g, CFG.COLOR_GRADIENT_TITLE_BLUE.b, 0.45F));
      ImageManager.getImage(Images.line_32_off1)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() + this.getHeight() - 1 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
            this.getWidth() - 4,
            1
         );
      oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.7F));
      ImageManager.getImage(Images.line_32_off1)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() + this.getHeight() - 2 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
            this.getWidth() - 4,
            1
         );
      oSB.setColor(Color.WHITE);
   }

   @Override
   public void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
      float drawPerc = this.fAttackersPerc;
      if (lTime + 375L > System.currentTimeMillis()) {
         drawPerc = 0.5F - (0.5F - this.fAttackersPerc) * (float)(System.currentTimeMillis() - lTime) / 375.0F;
         CFG.setRender_3(true);
      }

      ImageManager.getImage(Images.diplo_rivals)
         .draw(
            oSB,
            this.getPosX()
               + this.getWidth() / 2
               - (int)(ImageManager.getImage(Images.diplo_rivals).getWidth() * this.getImageScale(Images.diplo_rivals)) / 2
               + iTranslateX,
            this.getPosY()
               + CFG.PADDING * 2
               + (CFG.TEXT_HEIGHT - (int)(ImageManager.getImage(Images.diplo_rivals).getHeight() * this.getImageScale(Images.diplo_rivals))) / 2
               - ImageManager.getImage(Images.diplo_rivals).getHeight()
               + iTranslateY,
            (int)(ImageManager.getImage(Images.diplo_rivals).getWidth() * this.getImageScale(Images.diplo_rivals)),
            (int)(ImageManager.getImage(Images.diplo_rivals).getHeight() * this.getImageScale(Images.diplo_rivals))
         );

      try {
         if (this.iAggressor >= 0) {
            oSB.setColor(
               new Color(
                  CFG.game.getCiv(this.iAggressor).getR() / 255.0F,
                  CFG.game.getCiv(this.iAggressor).getG() / 255.0F,
                  CFG.game.getCiv(this.iAggressor).getB() / 255.0F,
                  1.0F
               )
            );
         } else {
            oSB.setColor(
               new Color(
                  CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_AT_WAR.getR(),
                  CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_AT_WAR.getG(),
                  CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_AT_WAR.getB(),
                  1.0F
               )
            );
         }
      } catch (IndexOutOfBoundsException var10) {
         oSB.setColor(
            new Color(
               CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_AT_WAR.getR(),
               CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_AT_WAR.getG(),
               CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_AT_WAR.getB(),
               1.0F
            )
         );
      }

      ImageManager.getImage(Images.pix255_255_255)
         .draw(
            oSB,
            this.getPosX()
               + this.getWidth() / 2
               - (int)(ImageManager.getImage(Images.diplo_rivals).getWidth() * this.getImageScale(Images.diplo_rivals)) / 2
               - CFG.PADDING
               - 2
               + iTranslateX,
            this.getPosY() + CFG.PADDING * 2 - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY,
            2,
            (int)(ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect))
         );

      try {
         if (this.iDefender >= 0) {
            oSB.setColor(
               new Color(
                  CFG.game.getCiv(this.iDefender).getR() / 255.0F,
                  CFG.game.getCiv(this.iDefender).getG() / 255.0F,
                  CFG.game.getCiv(this.iDefender).getB() / 255.0F,
                  1.0F
               )
            );
         } else {
            oSB.setColor(
               new Color(
                  CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_ALLIANCE.getR(),
                  CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_ALLIANCE.getG(),
                  CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_ALLIANCE.getB(),
                  1.0F
               )
            );
         }
      } catch (IndexOutOfBoundsException var9) {
         oSB.setColor(
            new Color(
               CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_ALLIANCE.getR(),
               CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_ALLIANCE.getG(),
               CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_ALLIANCE.getB(),
               1.0F
            )
         );
      }

      ImageManager.getImage(Images.pix255_255_255)
         .draw(
            oSB,
            this.getPosX()
               + this.getWidth() / 2
               + (int)(ImageManager.getImage(Images.diplo_rivals).getWidth() * this.getImageScale(Images.diplo_rivals)) / 2
               + CFG.PADDING
               + iTranslateX,
            this.getPosY() + CFG.PADDING * 2 - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY,
            2,
            (int)(ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect))
         );
      oSB.setColor(Color.WHITE);
      if (CFG.FOG_OF_WAR == 2) {
         for (int i = CFG.game.getWar(this.iWarID).getAggressorsSize() - 1; i >= 0; i--) {
            if (CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetCivilization(CFG.game.getWar(this.iWarID).getAggressorID(i).getCivID())) {
               CFG.game
                  .getCiv(CFG.game.getWar(this.iWarID).getAggressorID(i).getCivID())
                  .getFlag()
                  .draw(
                     oSB,
                     this.getPosX()
                        + this.getWidth() / 2
                        - (int)(ImageManager.getImage(Images.diplo_rivals).getWidth() * this.getImageScale(Images.diplo_rivals)) / 2
                        - CFG.PADDING
                        - 2
                        - (int)(ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(Images.flag_rect))
                        - (int)(ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(Images.flag_rect)) * 3 / 4 * i
                        + iTranslateX,
                     this.getPosY()
                        + CFG.PADDING * 2
                        + (CFG.TEXT_HEIGHT - (int)(ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect))) / 2
                        - CFG.game.getCiv(CFG.game.getWar(this.iWarID).getAggressorID(i).getCivID()).getFlag().getHeight()
                        + iTranslateY,
                     (int)(ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(Images.flag_rect)),
                     (int)(ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect))
                  );
            } else {
               ImageManager.getImage(Images.randomCivilizationFlag)
                  .draw(
                     oSB,
                     this.getPosX()
                        + this.getWidth() / 2
                        - (int)(ImageManager.getImage(Images.diplo_rivals).getWidth() * this.getImageScale(Images.diplo_rivals)) / 2
                        - CFG.PADDING
                        - 2
                        - (int)(ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(Images.flag_rect))
                        - (int)(ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(Images.flag_rect)) * 3 / 4 * i
                        + iTranslateX,
                     this.getPosY()
                        + CFG.PADDING * 2
                        + (CFG.TEXT_HEIGHT - (int)(ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect))) / 2
                        - ImageManager.getImage(Images.randomCivilizationFlag).getHeight()
                        + iTranslateY,
                     (int)(ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(Images.flag_rect)),
                     (int)(ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect))
                  );
            }

            ImageManager.getImage(Images.flag_rect)
               .draw(
                  oSB,
                  this.getPosX()
                     + this.getWidth() / 2
                     - (int)(ImageManager.getImage(Images.diplo_rivals).getWidth() * this.getImageScale(Images.diplo_rivals)) / 2
                     - CFG.PADDING
                     - 2
                     - (int)(ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(Images.flag_rect))
                     - (int)(ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(Images.flag_rect)) * 3 / 4 * i
                     + iTranslateX,
                  this.getPosY()
                     + CFG.PADDING * 2
                     + (CFG.TEXT_HEIGHT - (int)(ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect))) / 2
                     - ImageManager.getImage(Images.flag_rect).getHeight()
                     + iTranslateY,
                  (int)(ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(Images.flag_rect)),
                  (int)(ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect))
               );
         }

         for (int var11 = CFG.game.getWar(this.iWarID).getDefendersSize() - 1; var11 >= 0; var11--) {
            if (CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetCivilization(CFG.game.getWar(this.iWarID).getDefenderID(var11).getCivID())) {
               CFG.game
                  .getCiv(CFG.game.getWar(this.iWarID).getDefenderID(var11).getCivID())
                  .getFlag()
                  .draw(
                     oSB,
                     this.getPosX()
                        + this.getWidth() / 2
                        + (int)(ImageManager.getImage(Images.diplo_rivals).getWidth() * this.getImageScale(Images.diplo_rivals)) / 2
                        + CFG.PADDING
                        + 2
                        + (int)(ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(Images.flag_rect)) * 3 / 4 * var11
                        + iTranslateX,
                     this.getPosY()
                        + CFG.PADDING * 2
                        + (CFG.TEXT_HEIGHT - (int)(ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect))) / 2
                        - CFG.game.getCiv(CFG.game.getWar(this.iWarID).getDefenderID(var11).getCivID()).getFlag().getHeight()
                        + iTranslateY,
                     (int)(ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(Images.flag_rect)),
                     (int)(ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect))
                  );
            } else {
               ImageManager.getImage(Images.randomCivilizationFlag)
                  .draw(
                     oSB,
                     this.getPosX()
                        + this.getWidth() / 2
                        + (int)(ImageManager.getImage(Images.diplo_rivals).getWidth() * this.getImageScale(Images.diplo_rivals)) / 2
                        + CFG.PADDING
                        + 2
                        + (int)(ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(Images.flag_rect)) * 3 / 4 * var11
                        + iTranslateX,
                     this.getPosY()
                        + CFG.PADDING * 2
                        + (CFG.TEXT_HEIGHT - (int)(ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect))) / 2
                        - ImageManager.getImage(Images.randomCivilizationFlag).getHeight()
                        + iTranslateY,
                     (int)(ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(Images.flag_rect)),
                     (int)(ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect))
                  );
            }

            ImageManager.getImage(Images.flag_rect)
               .draw(
                  oSB,
                  this.getPosX()
                     + this.getWidth() / 2
                     + (int)(ImageManager.getImage(Images.diplo_rivals).getWidth() * this.getImageScale(Images.diplo_rivals)) / 2
                     + CFG.PADDING
                     + 2
                     + (int)(ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(Images.flag_rect)) * 3 / 4 * var11
                     + iTranslateX,
                  this.getPosY()
                     + CFG.PADDING * 2
                     + (CFG.TEXT_HEIGHT - (int)(ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect))) / 2
                     - ImageManager.getImage(Images.flag_rect).getHeight()
                     + iTranslateY,
                  (int)(ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(Images.flag_rect)),
                  (int)(ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect))
               );
         }
      } else {
         for (int i = CFG.game.getWar(this.iWarID).getAggressorsSize() - 1; i >= 0; i--) {
            CFG.game
               .getCiv(CFG.game.getWar(this.iWarID).getAggressorID(i).getCivID())
               .getFlag()
               .draw(
                  oSB,
                  this.getPosX()
                     + this.getWidth() / 2
                     - (int)(ImageManager.getImage(Images.diplo_rivals).getWidth() * this.getImageScale(Images.diplo_rivals)) / 2
                     - CFG.PADDING
                     - 2
                     - (int)(ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(Images.flag_rect))
                     - (int)(ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(Images.flag_rect)) * 3 / 4 * i
                     + iTranslateX,
                  this.getPosY()
                     + CFG.PADDING * 2
                     + (CFG.TEXT_HEIGHT - (int)(ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect))) / 2
                     - CFG.game.getCiv(CFG.game.getWar(this.iWarID).getAggressorID(i).getCivID()).getFlag().getHeight()
                     + iTranslateY,
                  (int)(ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(Images.flag_rect)),
                  (int)(ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect))
               );
            ImageManager.getImage(Images.flag_rect)
               .draw(
                  oSB,
                  this.getPosX()
                     + this.getWidth() / 2
                     - (int)(ImageManager.getImage(Images.diplo_rivals).getWidth() * this.getImageScale(Images.diplo_rivals)) / 2
                     - CFG.PADDING
                     - 2
                     - (int)(ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(Images.flag_rect))
                     - (int)(ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(Images.flag_rect)) * 3 / 4 * i
                     + iTranslateX,
                  this.getPosY()
                     + CFG.PADDING * 2
                     + (CFG.TEXT_HEIGHT - (int)(ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect))) / 2
                     - ImageManager.getImage(Images.flag_rect).getHeight()
                     + iTranslateY,
                  (int)(ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(Images.flag_rect)),
                  (int)(ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect))
               );
         }

         for (int var13 = CFG.game.getWar(this.iWarID).getDefendersSize() - 1; var13 >= 0; var13--) {
            CFG.game
               .getCiv(CFG.game.getWar(this.iWarID).getDefenderID(var13).getCivID())
               .getFlag()
               .draw(
                  oSB,
                  this.getPosX()
                     + this.getWidth() / 2
                     + (int)(ImageManager.getImage(Images.diplo_rivals).getWidth() * this.getImageScale(Images.diplo_rivals)) / 2
                     + CFG.PADDING
                     + 2
                     + (int)(ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(Images.flag_rect)) * 3 / 4 * var13
                     + iTranslateX,
                  this.getPosY()
                     + CFG.PADDING * 2
                     + (CFG.TEXT_HEIGHT - (int)(ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect))) / 2
                     - CFG.game.getCiv(CFG.game.getWar(this.iWarID).getDefenderID(var13).getCivID()).getFlag().getHeight()
                     + iTranslateY,
                  (int)(ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(Images.flag_rect)),
                  (int)(ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect))
               );
            ImageManager.getImage(Images.flag_rect)
               .draw(
                  oSB,
                  this.getPosX()
                     + this.getWidth() / 2
                     + (int)(ImageManager.getImage(Images.diplo_rivals).getWidth() * this.getImageScale(Images.diplo_rivals)) / 2
                     + CFG.PADDING
                     + 2
                     + (int)(ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(Images.flag_rect)) * 3 / 4 * var13
                     + iTranslateX,
                  this.getPosY()
                     + CFG.PADDING * 2
                     + (CFG.TEXT_HEIGHT - (int)(ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect))) / 2
                     - ImageManager.getImage(Images.flag_rect).getHeight()
                     + iTranslateY,
                  (int)(ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(Images.flag_rect)),
                  (int)(ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect))
               );
         }
      }

      CFG.fontMain.getData().setScale(0.7F);
      CFG.drawTextWithShadow(
         oSB,
         this.getText(),
         this.getPosX()
            + this.getWidth() / 2
            - (int)(ImageManager.getImage(Images.diplo_rivals).getWidth() * this.getImageScale(Images.diplo_rivals)) / 2
            - CFG.PADDING * 2
            - (int)(this.getTextWidth() * 0.7F)
            - 2
            - (int)(ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(Images.flag_rect))
            - (int)(ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(Images.flag_rect))
               * 3
               / 4
               * (CFG.game.getWar(this.iWarID).getAggressorsSize() - 1)
            + iTranslateX,
         this.getPosY() + CFG.PADDING * 2 + (int)((CFG.TEXT_HEIGHT - CFG.TEXT_HEIGHT * 0.7F) / 2.0F) + iTranslateY,
         this.getColor(isActive)
      );
      CFG.drawTextWithShadow(
         oSB,
         this.sDefenderName,
         this.getPosX()
            + this.getWidth() / 2
            + (int)(ImageManager.getImage(Images.diplo_rivals).getWidth() * this.getImageScale(Images.diplo_rivals)) / 2
            + CFG.PADDING * 2
            + 2
            + (int)(ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(Images.flag_rect))
            + (int)(ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(Images.flag_rect))
               * 3
               / 4
               * (CFG.game.getWar(this.iWarID).getDefendersSize() - 1)
            + iTranslateX,
         this.getPosY() + CFG.PADDING * 2 + (int)((CFG.TEXT_HEIGHT - CFG.TEXT_HEIGHT * 0.7F) / 2.0F) + iTranslateY,
         this.getColor(isActive)
      );
      CFG.fontMain.getData().setScale(1.0F);
      CFG.fontMain.getData().setScale(0.6F);
      ImageManager.getImage(Images.difficulty_hell)
         .draw(
            oSB,
            this.getPosX() + CFG.PADDING * 2 + iTranslateX,
            this.getPosY()
               + CFG.PADDING * 2
               + (CFG.TEXT_HEIGHT - (int)(ImageManager.getImage(Images.difficulty_hell).getHeight() * this.getImageScale2(Images.difficulty_hell))) / 2
               - ImageManager.getImage(Images.difficulty_hell).getHeight()
               + iTranslateY,
            (int)(ImageManager.getImage(Images.difficulty_hell).getWidth() * this.getImageScale2(Images.difficulty_hell)),
            (int)(ImageManager.getImage(Images.difficulty_hell).getHeight() * this.getImageScale2(Images.difficulty_hell))
         );
      CFG.drawTextWithShadow(
         oSB,
         this.sCasualtiesTotal,
         this.getPosX()
            + (int)(ImageManager.getImage(Images.difficulty_hell).getWidth() * this.getImageScale2(Images.difficulty_hell))
            + CFG.PADDING * 3
            + iTranslateX,
         this.getPosY() + CFG.PADDING * 2 + (int)((CFG.TEXT_HEIGHT - CFG.TEXT_HEIGHT * 0.6F) / 2.0F) + iTranslateY,
         this.oColorCasualtiesTotal
      );
      ImageManager.getImage(Images.time)
         .draw(
            oSB,
            this.getPosX()
               + this.getWidth()
               - CFG.PADDING * 2
               - (int)(ImageManager.getImage(Images.time).getWidth() * this.getImageScale2(Images.time))
               + iTranslateX,
            this.getPosY()
               + CFG.PADDING * 2
               + (CFG.TEXT_HEIGHT - (int)(ImageManager.getImage(Images.time).getHeight() * this.getImageScale2(Images.time))) / 2
               - ImageManager.getImage(Images.time).getHeight()
               + iTranslateY,
            (int)(ImageManager.getImage(Images.time).getWidth() * this.getImageScale2(Images.time)),
            (int)(ImageManager.getImage(Images.time).getHeight() * this.getImageScale2(Images.time))
         );
      CFG.drawTextWithShadow(
         oSB,
         this.sWarDate,
         this.getPosX()
            + this.getWidth()
            - (int)(ImageManager.getImage(Images.time).getWidth() * this.getImageScale2(Images.time))
            - CFG.PADDING * 3
            - this.iWarDateWidth
            + iTranslateX,
         this.getPosY() + CFG.PADDING * 2 + (int)((CFG.TEXT_HEIGHT - CFG.TEXT_HEIGHT * 0.6F) / 2.0F) + iTranslateY,
         CFG.COLOR_TEXT_MODIFIER_NEUTRAL
      );
      CFG.fontMain.getData().setScale(1.0F);
      oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.65F));
      ImageManager.getImage(Images.gradient)
         .draw(
            oSB,
            this.getPosX() + CFG.PADDING + iTranslateX,
            this.getPosY() + this.getHeight() - CFG.PADDING - CFG.PADDING * 2 - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY,
            this.getWidth() - CFG.PADDING * 2,
            CFG.PADDING * 2,
            false,
            true
         );

      try {
         if (this.iAggressor >= 0) {
            oSB.setColor(
               new Color(
                  CFG.game.getCiv(this.iAggressor).getR() / 255.0F,
                  CFG.game.getCiv(this.iAggressor).getG() / 255.0F,
                  CFG.game.getCiv(this.iAggressor).getB() / 255.0F,
                  0.45F
               )
            );
         } else {
            oSB.setColor(
               new Color(
                  CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_AT_WAR.getR(),
                  CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_AT_WAR.getG(),
                  CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_AT_WAR.getB(),
                  0.45F
               )
            );
         }
      } catch (IndexOutOfBoundsException var8) {
         oSB.setColor(
            new Color(
               CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_AT_WAR.getR(),
               CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_AT_WAR.getG(),
               CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_AT_WAR.getB(),
               0.45F
            )
         );
      }

      ImageManager.getImage(Images.pix255_255_255)
         .draw(
            oSB,
            this.getPosX() + CFG.PADDING + iTranslateX,
            this.getPosY() + this.getHeight() - CFG.PADDING - CFG.PADDING * 2 - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY,
            (int)((this.getWidth() - CFG.PADDING * 2) * drawPerc),
            CFG.PADDING * 2,
            false,
            true
         );
      ImageManager.getImage(Images.gradient)
         .draw(
            oSB,
            this.getPosX() + CFG.PADDING + iTranslateX,
            this.getPosY() + this.getHeight() - CFG.PADDING - CFG.PADDING * 2 - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY,
            (int)((this.getWidth() - CFG.PADDING * 2) * drawPerc),
            CFG.PADDING,
            false,
            false
         );
      ImageManager.getImage(Images.gradient)
         .draw(
            oSB,
            this.getPosX() + CFG.PADDING + iTranslateX,
            this.getPosY() + this.getHeight() - CFG.PADDING - CFG.PADDING - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY,
            (int)((this.getWidth() - CFG.PADDING * 2) * drawPerc),
            CFG.PADDING,
            false,
            true
         );
      ImageManager.getImage(Images.slider_gradient)
         .draw(
            oSB,
            this.getPosX() + CFG.PADDING + iTranslateX,
            this.getPosY() + this.getHeight() - CFG.PADDING - CFG.PADDING * 2 - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY,
            (int)((this.getWidth() - CFG.PADDING * 2) * drawPerc),
            CFG.PADDING * 2,
            true,
            true
         );

      try {
         if (this.iDefender >= 0) {
            oSB.setColor(
               new Color(
                  CFG.game.getCiv(this.iDefender).getR() / 255.0F,
                  CFG.game.getCiv(this.iDefender).getG() / 255.0F,
                  CFG.game.getCiv(this.iDefender).getB() / 255.0F,
                  0.45F
               )
            );
         } else {
            oSB.setColor(
               new Color(
                  CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_ALLIANCE.getR(),
                  CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_ALLIANCE.getG(),
                  CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_ALLIANCE.getB(),
                  0.45F
               )
            );
         }
      } catch (IndexOutOfBoundsException var7) {
         oSB.setColor(
            new Color(
               CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_ALLIANCE.getR(),
               CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_ALLIANCE.getG(),
               CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_ALLIANCE.getB(),
               0.45F
            )
         );
      }

      ImageManager.getImage(Images.pix255_255_255)
         .draw(
            oSB,
            this.getPosX() + CFG.PADDING + (int)((this.getWidth() - CFG.PADDING * 2) * drawPerc) + iTranslateX,
            this.getPosY() + this.getHeight() - CFG.PADDING - CFG.PADDING * 2 - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY,
            this.getWidth() - CFG.PADDING * 2 - (int)((this.getWidth() - CFG.PADDING * 2) * drawPerc),
            CFG.PADDING * 2,
            false,
            true
         );
      ImageManager.getImage(Images.gradient)
         .draw(
            oSB,
            this.getPosX() + CFG.PADDING + (int)((this.getWidth() - CFG.PADDING * 2) * drawPerc) + iTranslateX,
            this.getPosY() + this.getHeight() - CFG.PADDING - CFG.PADDING * 2 - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY,
            this.getWidth() - CFG.PADDING * 2 - (int)((this.getWidth() - CFG.PADDING * 2) * drawPerc),
            CFG.PADDING,
            false,
            false
         );
      ImageManager.getImage(Images.gradient)
         .draw(
            oSB,
            this.getPosX() + CFG.PADDING + (int)((this.getWidth() - CFG.PADDING * 2) * drawPerc) + iTranslateX,
            this.getPosY() + this.getHeight() - CFG.PADDING - CFG.PADDING - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY,
            this.getWidth() - CFG.PADDING * 2 - (int)((this.getWidth() - CFG.PADDING * 2) * drawPerc),
            CFG.PADDING,
            false,
            true
         );
      ImageManager.getImage(Images.slider_gradient)
         .draw(
            oSB,
            this.getPosX() + CFG.PADDING + (int)((this.getWidth() - CFG.PADDING * 2) * drawPerc) + iTranslateX,
            this.getPosY() + this.getHeight() - CFG.PADDING - CFG.PADDING * 2 - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY,
            this.getWidth() - CFG.PADDING * 2 - (int)((this.getWidth() - CFG.PADDING * 2) * drawPerc),
            CFG.PADDING * 2,
            false,
            true
         );
      oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.25F));
      ImageManager.getImage(Images.patt)
         .draw2(
            oSB,
            this.getPosX() + CFG.PADDING + iTranslateX,
            this.getPosY() + this.getHeight() - CFG.PADDING - CFG.PADDING * 2 - ImageManager.getImage(Images.patt).getHeight() + iTranslateY,
            (int)((this.getWidth() - CFG.PADDING * 2) * drawPerc),
            CFG.PADDING * 2,
            false,
            true
         );
      ImageManager.getImage(Images.patt)
         .draw2(
            oSB,
            this.getPosX() + CFG.PADDING + (int)((this.getWidth() - CFG.PADDING * 2) * drawPerc) + iTranslateX,
            this.getPosY() + this.getHeight() - CFG.PADDING - CFG.PADDING * 2 - ImageManager.getImage(Images.patt).getHeight() + iTranslateY,
            this.getWidth() - CFG.PADDING * 2 - (int)((this.getWidth() - CFG.PADDING * 2) * drawPerc),
            CFG.PADDING * 2,
            true,
            true
         );
      oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.785F));
      ImageManager.getImage(Images.pix255_255_255)
         .draw(
            oSB,
            this.getPosX() + CFG.PADDING + (int)((this.getWidth() - CFG.PADDING * 2) * drawPerc) + iTranslateX,
            this.getPosY() + this.getHeight() - CFG.PADDING - CFG.PADDING * 2 - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY,
            1,
            CFG.PADDING * 2
         );
      oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.25F));
      ImageManager.getImage(Images.gradient)
         .draw(
            oSB,
            this.getPosX() + CFG.PADDING + iTranslateX,
            this.getPosY() + this.getHeight() - CFG.PADDING - CFG.PADDING * 2 - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY,
            this.getWidth() - CFG.PADDING * 2,
            CFG.PADDING * 2,
            false,
            true
         );
      oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.7F));
      ImageManager.getImage(Images.pix255_255_255)
         .draw(
            oSB,
            this.getPosX() + CFG.PADDING + iTranslateX,
            this.getPosY() + this.getHeight() - CFG.PADDING - 1 - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY,
            this.getWidth() - CFG.PADDING * 2,
            1
         );
      ImageManager.getImage(Images.pix255_255_255)
         .draw(
            oSB,
            this.getPosX() + CFG.PADDING + iTranslateX,
            this.getPosY() + this.getHeight() - CFG.PADDING - CFG.PADDING * 2 - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY,
            this.getWidth() - CFG.PADDING * 2,
            1
         );
      oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.4F));
      ImageManager.getImage(Images.line_32_off1)
         .draw(
            oSB,
            this.getPosX() + CFG.PADDING + iTranslateX,
            this.getPosY() + this.getHeight() - CFG.PADDING - 1 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
            this.getWidth() - CFG.PADDING * 2,
            1
         );
      ImageManager.getImage(Images.line_32_off1)
         .draw(
            oSB,
            this.getPosX() + CFG.PADDING + iTranslateX,
            this.getPosY() + this.getHeight() - CFG.PADDING - CFG.PADDING * 2 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
            this.getWidth() - CFG.PADDING * 2,
            1
         );
      oSB.setColor(Color.WHITE);
   }

   public final float getImageScale(int nImageID) {
      return (float)CFG.TEXT_HEIGHT / ImageManager.getImage(nImageID).getHeight() < 1.0F
         ? (float)CFG.TEXT_HEIGHT / ImageManager.getImage(nImageID).getHeight()
         : 1.0F;
   }

   public final float getImageScale2(int nImageID) {
      return CFG.TEXT_HEIGHT * 0.7F / ImageManager.getImage(nImageID).getHeight() < 1.0F
         ? CFG.TEXT_HEIGHT * 0.7F / ImageManager.getImage(nImageID).getHeight()
         : 1.0F;
   }

   @Override
   public Color getColor(boolean isActive) {
      return isActive ? CFG.COLOR_TEXT_OPTIONS_NS_ACTIVE : (this.getIsHovered() ? CFG.COLOR_TEXT_OPTIONS_NS_HOVER : CFG.COLOR_TEXT_OPTIONS_NS);
   }

   @Override
   public void buildElementHover() {
      ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
      ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
      if (CFG.FOG_OF_WAR == 2) {
         for (int i = 0; i < CFG.game.getWar(this.iWarID).getAggressorsSize() && i < 6; i++) {
            if (CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetCivilization(CFG.game.getWar(this.iWarID).getAggressorID(i).getCivID())) {
               nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getWar(this.iWarID).getAggressorID(i).getCivID(), 0, 0));
            } else {
               nData.add(new MenuElement_Hover_v2_Element_Type_Flag(-1, 0, 0));
            }
         }

         nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.diplo_rivals, CFG.PADDING, CFG.PADDING));

         for (int var5 = 0; var5 < CFG.game.getWar(this.iWarID).getDefendersSize() && var5 < 6; var5++) {
            if (CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetCivilization(CFG.game.getWar(this.iWarID).getDefenderID(var5).getCivID())) {
               nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getWar(this.iWarID).getDefenderID(var5).getCivID(), 0, 0));
            } else {
               nData.add(new MenuElement_Hover_v2_Element_Type_Flag(-1, 0, 0));
            }
         }

         nElements.add(new MenuElement_Hover_v2_Element2(nData));
         nData.clear();
      } else {
         for (int ix = 0; ix < CFG.game.getWar(this.iWarID).getAggressorsSize() && ix < 6; ix++) {
            nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getWar(this.iWarID).getAggressorID(ix).getCivID(), 0, 0));
         }

         nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.diplo_rivals, CFG.PADDING, CFG.PADDING));

         for (int var7 = 0; var7 < CFG.game.getWar(this.iWarID).getDefendersSize() && var7 < 6; var7++) {
            nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getWar(this.iWarID).getDefenderID(var7).getCivID(), 0, 0));
         }

         nElements.add(new MenuElement_Hover_v2_Element2(nData));
         nData.clear();
      }

      int tempScore = CFG.game.getWar(this.iWarID).getWarScore();
      nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Score") + ": "));
      nData.add(
         new MenuElement_Hover_v2_Element_Type_Text(
            ""
               + (
                  tempScore == 0
                     ? CFG.langManager.get("Balanced")
                     : (
                        tempScore < 0
                           ? CFG.langManager.get("XInFavorOfAggressors", Math.abs(tempScore) + "%")
                           : CFG.langManager.get("XInFavorOfDefenders", Math.abs(tempScore) + "%")
                     )
               ),
            CFG.COLOR_TEXT_NUM_OF_PROVINCES
         )
      );
      nElements.add(new MenuElement_Hover_v2_Element2(nData));
      nData.clear();
      nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Casualties") + ": "));
      nData.add(new MenuElement_Hover_v2_Element_Type_Text(this.sCasualtiesTotal, this.oColorCasualtiesTotal));
      nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.difficulty_hell, CFG.PADDING, 0));
      nElements.add(new MenuElement_Hover_v2_Element2(nData));
      nData.clear();
      nData.add(new MenuElement_Hover_v2_Element_Type_Text(this.sWarDate, CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
      nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.time, CFG.PADDING, 0));
      nElements.add(new MenuElement_Hover_v2_Element2(nData));
      nData.clear();
      this.menuElementHover = new MenuElement_Hover_v2(nElements);
   }

   @Override
   public int getCurrent() {
      return this.iWarID;
   }

   @Override
   public int getSFX() {
      return SoundsManager.SOUND_CLICK2;
   }
}
