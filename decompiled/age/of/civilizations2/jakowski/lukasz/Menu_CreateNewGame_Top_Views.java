package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.utils.ScissorStack;
import java.util.ArrayList;

public class Menu_CreateNewGame_Top_Views extends SliderMenu {
   public Menu_CreateNewGame_Top_Views() {
      new ArrayList();
      this.updateLanguage();
   }

   @Override
   public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
      int var6;
      ImageManager.getImage(Images.new_game_box)
         .draw2(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY()
               - ImageManager.getImage(Images.new_game_box).getHeight()
               + (var6 = iTranslateY - (int)(CFG.GAME_HEIGHT * (100.0F - Menu_CreateNewGame_Top.fMovePercentage) / 100.0F)),
            this.getWidth() - ImageManager.getImage(Images.new_game_box).getWidth(),
            this.getHeight(),
            false,
            true
         );
      ImageManager.getImage(Images.new_game_box)
         .draw2(
            oSB,
            this.getPosX() + this.getWidth() - ImageManager.getImage(Images.new_game_box).getWidth() + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.new_game_box).getHeight() + var6,
            ImageManager.getImage(Images.new_game_box).getWidth(),
            this.getHeight(),
            true,
            true
         );
      this.drawBackgroundMode(oSB, sliderMenuIsActive);
      Rectangle clipBounds = new Rectangle(this.getPosX() + 2 + iTranslateX, CFG.GAME_HEIGHT - this.getPosY() - var6, this.getWidth() - 4, -this.getHeight());
      oSB.flush();
      ScissorStack.pushScissors(clipBounds);
      super.drawMenu(oSB, iTranslateX, var6, sliderMenuIsActive);
      super.endClip(oSB, iTranslateX, var6, sliderMenuIsActive);
   }

   @Override
   public void actionElement(int iID) {
      switch (iID) {
      }
   }
}
