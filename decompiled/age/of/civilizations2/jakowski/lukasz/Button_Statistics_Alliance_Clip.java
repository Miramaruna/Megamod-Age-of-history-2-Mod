package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.utils.ScissorStack;
import java.util.ArrayList;

public class Button_Statistics_Alliance_Clip extends Button_Statistics_Flag {
   public Button_Statistics_Alliance_Clip(int iAllianceID, String sText, int iTextPosX, int iPosX, int iPosY, int iWidth, int iHeight) {
      super(iAllianceID, sText, iTextPosX, iPosX, iPosY, iWidth, iHeight);
   }

   @Override
   public void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
      Rectangle clipBounds = new Rectangle(this.getPosX() + iTranslateX, CFG.GAME_HEIGHT - this.getPosY() - iTranslateY, this.getWidth(), -this.getHeight());
      oSB.flush();
      ScissorStack.pushScissors(clipBounds);

      try {
         oSB.setColor(
            new Color(
               CFG.game.getAlliance(this.iCivID).getColorOfAlliance().getR(),
               CFG.game.getAlliance(this.iCivID).getColorOfAlliance().getG(),
               CFG.game.getAlliance(this.iCivID).getColorOfAlliance().getB(),
               0.85F
            )
         );
         ImageManager.getImage(Images.pix255_255_255)
            .draw(
               oSB,
               this.getPosX() + this.getTextPos() + iTranslateX,
               this.getPosY()
                  - ImageManager.getImage(Images.pix255_255_255).getHeight()
                  + this.getHeight() / 2
                  - (int)(ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale()) / 2
                  + iTranslateY,
               2,
               (int)(ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale())
            );
         oSB.setColor(Color.WHITE);
         if (CFG.FOG_OF_WAR == 2) {
            for (int i = CFG.game.getAlliance(this.iCivID).getCivilizationsSize() - 1 > 5 ? 5 : CFG.game.getAlliance(this.iCivID).getCivilizationsSize() - 1;
               i >= 0;
               i--
            ) {
               if (CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetCivilization(CFG.game.getAlliance(this.iCivID).getCivilization(i))) {
                  CFG.game
                     .getCiv(CFG.game.getAlliance(this.iCivID).getCivilization(i))
                     .getFlag()
                     .draw(
                        oSB,
                        2
                           + (int)(ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale()) * 3 / 4 * i
                           + this.getPosX()
                           + this.getTextPos()
                           + iTranslateX,
                        this.getPosY()
                           + this.getHeight() / 2
                           - (int)(ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale()) / 2
                           - CFG.game.getCiv(CFG.game.getAlliance(this.iCivID).getCivilization(i)).getFlag().getHeight()
                           + iTranslateY,
                        (int)(ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale()),
                        (int)(ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale())
                     );
               } else {
                  ImageManager.getImage(Images.randomCivilizationFlag)
                     .draw(
                        oSB,
                        2
                           + (int)(ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale()) * 3 / 4 * i
                           + this.getPosX()
                           + this.getTextPos()
                           + iTranslateX,
                        this.getPosY()
                           + this.getHeight() / 2
                           - (int)(ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale()) / 2
                           - ImageManager.getImage(Images.randomCivilizationFlag).getHeight()
                           + iTranslateY,
                        (int)(ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale()),
                        (int)(ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale())
                     );
               }

               ImageManager.getImage(Images.flag_rect)
                  .draw(
                     oSB,
                     2
                        + (int)(ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale()) * 3 / 4 * i
                        + this.getPosX()
                        + this.getTextPos()
                        + iTranslateX,
                     this.getPosY()
                        + this.getHeight() / 2
                        - (int)(ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale()) / 2
                        - ImageManager.getImage(Images.flag_rect).getHeight()
                        + iTranslateY,
                     (int)(ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale()),
                     (int)(ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale())
                  );
            }
         } else {
            for (int i = CFG.game.getAlliance(this.iCivID).getCivilizationsSize() - 1 > 5 ? 5 : CFG.game.getAlliance(this.iCivID).getCivilizationsSize() - 1;
               i >= 0;
               i--
            ) {
               CFG.game
                  .getCiv(CFG.game.getAlliance(this.iCivID).getCivilization(i))
                  .getFlag()
                  .draw(
                     oSB,
                     2
                        + (int)(ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale()) * 3 / 4 * i
                        + this.getPosX()
                        + this.getTextPos()
                        + iTranslateX,
                     this.getPosY()
                        + this.getHeight() / 2
                        - (int)(ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale()) / 2
                        - CFG.game.getCiv(CFG.game.getAlliance(this.iCivID).getCivilization(i)).getFlag().getHeight()
                        + iTranslateY,
                     (int)(ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale()),
                     (int)(ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale())
                  );
               ImageManager.getImage(Images.flag_rect)
                  .draw(
                     oSB,
                     2
                        + (int)(ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale()) * 3 / 4 * i
                        + this.getPosX()
                        + this.getTextPos()
                        + iTranslateX,
                     this.getPosY()
                        + this.getHeight() / 2
                        - (int)(ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale()) / 2
                        - ImageManager.getImage(Images.flag_rect).getHeight()
                        + iTranslateY,
                     (int)(ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale()),
                     (int)(ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale())
                  );
            }
         }

         CFG.fontMain.getData().setScale(0.7F);
         CFG.drawTextWithShadow(
            oSB,
            this.getTextToDraw(),
            this.getPosX()
               + this.textPosition.getTextPosition()
               + (int)(ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale())
                  * 3
                  / 4
                  * (CFG.game.getAlliance(this.iCivID).getCivilizationsSize() - 1 > 5 ? 5 : CFG.game.getAlliance(this.iCivID).getCivilizationsSize() - 1)
               + 2
               + CFG.PADDING
               + (int)(ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale())
               + iTranslateX,
            this.getPosY() + this.getHeight() / 2 - (int)(this.iTextHeight * 0.7F / 2.0F) + iTranslateY,
            this.getColor(isActive)
         );
         CFG.fontMain.getData().setScale(1.0F);
      } catch (IndexOutOfBoundsException var9) {
         oSB.setColor(new Color(CFG.RANDOM_CIVILIZATION_COLOR.r, CFG.RANDOM_CIVILIZATION_COLOR.g, CFG.RANDOM_CIVILIZATION_COLOR.b, 0.85F));
         ImageManager.getImage(Images.pix255_255_255)
            .draw(
               oSB,
               this.getPosX() + this.getTextPos() + iTranslateX,
               this.getPosY()
                  - ImageManager.getImage(Images.pix255_255_255).getHeight()
                  + this.getHeight() / 2
                  - (int)(ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale()) / 2
                  + iTranslateY,
               2,
               (int)(ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale())
            );
         oSB.setColor(Color.WHITE);
         ImageManager.getImage(Images.randomCivilizationFlag)
            .draw(
               oSB,
               2 + this.getPosX() + this.getTextPos() + iTranslateX,
               this.getPosY()
                  + this.getHeight() / 2
                  - (int)(ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale()) / 2
                  - ImageManager.getImage(Images.randomCivilizationFlag).getHeight()
                  + iTranslateY,
               (int)(ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale()),
               (int)(ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale())
            );
         ImageManager.getImage(Images.flag_rect)
            .draw(
               oSB,
               2 + this.getPosX() + this.getTextPos() + iTranslateX,
               this.getPosY()
                  + this.getHeight() / 2
                  - (int)(ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale()) / 2
                  - ImageManager.getImage(Images.flag_rect).getHeight()
                  + iTranslateY,
               (int)(ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale()),
               (int)(ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale())
            );
         CFG.fontMain.getData().setScale(0.7F);
         CFG.drawTextWithShadow(
            oSB,
            this.getTextToDraw(),
            this.getPosX()
               + this.textPosition.getTextPosition()
               + 2
               + CFG.PADDING
               + (int)(ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale())
               + iTranslateX,
            this.getPosY() + this.getHeight() / 2 - (int)(this.iTextHeight * 0.7F / 2.0F) + iTranslateY,
            this.getColor(isActive)
         );
         CFG.fontMain.getData().setScale(1.0F);
      }

      try {
         oSB.flush();
         ScissorStack.popScissors();
      } catch (IllegalStateException var8) {
      }
   }

   public final float getImageScale() {
      return (float)CFG.TEXT_HEIGHT / CFG.CIV_FLAG_HEIGHT;
   }

   @Override
   public void buildElementHover() {
      try {
         ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
         ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
         nData.add(new MenuElement_Hover_v2_Element_Type_Text(this.getText(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
         nElements.add(new MenuElement_Hover_v2_Element2(nData));
         nData.clear();
         this.menuElementHover = new MenuElement_Hover_v2(nElements);
      } catch (IndexOutOfBoundsException var3) {
      }
   }
}
